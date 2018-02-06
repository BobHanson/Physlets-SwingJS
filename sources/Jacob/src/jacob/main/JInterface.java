//==============================================================================
//
//  Copyright (C) 2000  Vojko Valencic <Vojko.Valencic@fe.uni-lj.si>
//                      Savin Zlobec <savin@torina.fe.uni-lj.si>
//
//  This program is free software; you can redistribute it and/or
//  modify it under the terms of the GNU General Public License
//  as published by the Free Software Foundation; either version 2
//  of the License, or (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details
//
//  You should have received a copy of the GNU General Public License
//  along with this program; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//
//==============================================================================

package jacob.main;

import jacob.scene.*;
import jacob.geometry.*;
import jacob.property.*;
import jacob.system.*;

public class JInterface
{
    private MainPanel mainPanel;

    private Scene scene;

    private SystemMgr systemMgr;

//------------------------------------------------------------------------------
//    Constructor Methods
//------------------------------------------------------------------------------

    public JInterface( SystemMgr systemMgr, MainPanel mainPanel )
    {
        this.mainPanel = mainPanel;
        this.systemMgr = systemMgr;
        this.scene = mainPanel.getScene();
    }

//------------------------------------------------------------------------------
//    Scene Methods
//------------------------------------------------------------------------------

    public Scene getScene()
    {
        return scene;
    }

    public void readExperiment( String file )
    {
        mainPanel.readExperiment( file );
    }

    public void newScene()
    {
        scene.newScene();
    }

//------------------------------------------------------------------------------
//    Element Methods
//------------------------------------------------------------------------------

    public Element createCircleElement( String name, double x, double y,
                                        double r )
    {
        return createCircleElement( name, x, y, r, null );
    }

    public Element createCircleElement( String name, double x, double y,
                                        double r, String pname )
    {
        Element e;
        Element parent = scene.getElement( pname );

        e = new Element( scene, new Circle2d( x, y, r ) );
        e.setName( name );

        synchronized( scene.getSceneLock() )
        {
            if ( parent == null )
                scene.getView().addChild( e );
            else
                parent.addChild( e );
        }
        scene.redraw();
        return e;
    }

    public Element createRingElement( String name, double x, double y,
                                      double r1, double r2 )
    {
        return createRingElement( name, x, y, r1, r2, null );
    }

    public Element createRingElement( String name, double x, double y,
                                      double r1, double r2, String pname )
    {
        Element e;
        Element parent = scene.getElement( pname );

        e = new Element( scene, new Ring2d( x, y, r1, r2 ) );
        e.setName( name );

        synchronized( scene.getSceneLock() )
        {
            if ( parent == null )
                scene.getView().addChild( e );
            else
                parent.addChild( e );
        }
        scene.redraw();
        return e;
    }

    public Element createPolygonElement( String name, double[] xpoints,
                                         double ypoints[], int npoints )
    {
        return createPolygonElement( name, xpoints, ypoints, npoints, null );
    }

    public Element createPolygonElement( String name, double[] xpoints,
                                         double ypoints[], int npoints,
                                         String pname )
    {
        Element e;
        Element parent = scene.getElement( pname );

        e = new Element( scene, new Polygon2d( xpoints, ypoints, npoints ) );
        e.setName( name );

        synchronized( scene.getSceneLock() )
        {
            if ( parent == null )
                scene.getView().addChild( e );
            else
                parent.addChild( e );
        }
        scene.redraw();
        return e;
    }

    public Element createRectangleElement( String name, double x, double y,
                                           double width, double height )
    {
        return createRectangleElement( name, x, y, width, height, null );
    }

    public Element createRectangleElement( String name, double x, double y,
                                           double width, double height,
                                           String pname )
    {
        double xpoints[] = { x, x + width, x, x + width };
        double ypoints[] = { y, y, y + height, y + height };

        return createPolygonElement( name, xpoints, ypoints, 4, pname );
    }

    public Element getElement( String name )
    {
        return scene.getElement( name );
    }

    public Point2d getLocation( String name )
    {
        Element e = scene.getElement( name );

        if ( e != null )
            return e.getLocation();
        else
            return null;
    }

    public double getX( String name )
    {
        Element e = scene.getElement( name );

        if ( e != null )
            return e.getLocation().x;
        else
            return 0;
    }

    public double getY( String name )
    {
        Element e = scene.getElement( name );

        if ( e != null )
            return e.getLocation().y;
        else
            return 0;
    }

    public boolean setName( String name, String newname )
    {
        Element e = scene.getElement( name );

        if ( e != null )
            return e.setName( newname );
        else
            return false;
    }

    public void moveTo( String name, double x, double y )
    {
        Element e = scene.getElement( name );
        if ( e == null ) return;

        synchronized( scene.getSceneLock() )
        {
            e.move( new Point2d( x, y ) );
        }
        scene.redraw();
    }

    public void moveBy( String name, double x, double y )
    {
        Element e = scene.getElement( name );
        if ( e == null ) return;

        Point2d loc = e.getLocation();
        synchronized( scene.getSceneLock() )
        {
            e.move( new Point2d( loc.x + x, loc.y + y ) );
        }
        scene.redraw();
    }

    public void scale( String name, double scale )
    {
        Element e = scene.getElement( name );
        if ( e == null ) return;

        synchronized( scene.getSceneLock() )
        {
            e.scale( scale );
        }
        scene.redraw();
    }

    public void rotate( String name, double angle )
    {
        Element e = scene.getElement( name );
        if ( e == null ) return;

        synchronized( scene.getSceneLock() )
        {
            e.rotate( angle * Math.PI / 180 );
        }
        scene.redraw();
    }

    public void rotate( String name, double pivotx, double pivoty,
                        double angle )
    {
        Element e = scene.getElement( name );
        if ( e == null ) return;

        synchronized( scene.getSceneLock() )
        {
            e.rotate( new Point2d( pivotx, pivoty ), angle * Math.PI / 180 );
        }
        scene.redraw();
    }

//------------------------------------------------------------------------------
//    Element Animation Methods
//------------------------------------------------------------------------------

    public void moveBy( String name, double xstep, double ystep, int count,
                        int sleep, boolean block )
    {
        class A extends Thread
        {
            Element e;
            double xstep, ystep;
            int count, sleep;

            public A( Element e, double xstep, double ystep,
                      int count, int sleep )
            {
                this.e = e;
                this.xstep = xstep;
                this.ystep = ystep;
                this.count = count;
                this.sleep = sleep;
            }

            public void run()
            {
                setPriority( Thread.MIN_PRIORITY );
                for ( int i = 0; i < count; i++ )
                {
                    Point2d loc = e.getLocation();
                    synchronized( scene.getSceneLock() )
                    {
                        e.move( new Point2d( loc.x + xstep, loc.y + ystep ) );
                    }
                    scene.animationRedraw();
                    try {sleep(sleep);} catch (InterruptedException ex) {}
                }
            }
        };

        Element e = scene.getElement( name );
        if ( e == null ) return;

        A anim = new A( e, xstep, ystep, count, sleep );
        anim.start();

        if ( block )
        {
            while ( anim.isAlive() )
            {
                try { Thread.currentThread();
				Thread.sleep( 10 ); }
                catch (InterruptedException ex) {}
            }
        }
    }

    public void scale( String name, double step, int count,
                       int sleep, boolean block )
    {
        class A extends Thread
        {
            Element e;
            double step;
            int count, sleep;

            public A( Element e, double step, int count, int sleep )
            {
                this.e     = e;
                this.step  = step;
                this.count = count;
                this.sleep = sleep;
            }

            public void run()
            {
                setPriority( Thread.MIN_PRIORITY );
                for ( int i = 0; i < count; i++ )
                {
                    synchronized( scene.getSceneLock() )
                    {
                        e.scale( step );
                    }
                    scene.animationRedraw();
                    try {sleep(sleep);} catch (InterruptedException ex) {}
                }
            }
        };

        Element e = scene.getElement( name );
        if ( e == null ) return;

        A anim = new A( e, step, count, sleep );
        anim.start();

        if ( block )
        {
            while ( anim.isAlive() )
            {
                try { Thread.currentThread();
				Thread.sleep( 10 ); }
                catch (InterruptedException ex) {}
            }
        }
    }

    public void rotate( String name, double step, int count,
                        int sleep, boolean block )
    {
        class A extends Thread
        {
            Element e;
            double step;
            int count, sleep;

            public A( Element e, double step, int count, int sleep )
            {
                this.e = e;
                this.step = step;
                this.count = count;
                this.sleep = sleep;
            }

            public void run()
            {
                setPriority( Thread.MIN_PRIORITY );
                for ( int i = 0; i < count; i++ )
                {
                    synchronized( scene.getSceneLock() )
                    {
                        e.rotate( step );
                    }
                    scene.animationRedraw();
                    try {sleep(sleep);} catch (InterruptedException ex) {}
                }
            }
        };

        Element e = scene.getElement( name );
        if ( e == null ) return;

        A anim = new A( e, step, count, sleep );
        anim.start();

        if ( block )
        {
            while ( anim.isAlive() )
            {
                try { Thread.currentThread();
				Thread.sleep( 10 ); }
                catch (InterruptedException ex) {}
            }
        }
    }

    public void rotate( String name, double pivotx, double pivoty,
                        double step, int count,
                        int sleep, boolean block )
    {
        class A extends Thread
        {
            Element e;
            Point2d pivot;
            double step;
            int count, sleep;

            public A( Element e, Point2d pivot, double step,
                      int count, int sleep )
            {
                this.e = e;
                this.pivot = pivot;
                this.step  = step;
                this.count = count;
                this.sleep = sleep;
            }

            public void run()
            {
                setPriority( Thread.MIN_PRIORITY );
                for ( int i = 0; i < count; i++ )
                {
                    synchronized( scene.getSceneLock() )
                    {
                        e.rotate( pivot, step );
                    }
                    scene.animationRedraw();
                    try {sleep(sleep);} catch (InterruptedException ex) {}
                }
            }
        };

        Element e = scene.getElement( name );
        if ( e == null ) return;

        A anim = new A( e, new Point2d( pivotx, pivoty ), step, count, sleep );
        anim.start();

        if ( block )
        {
            while ( anim.isAlive() )
            {
                try { Thread.currentThread();
				Thread.sleep( 10 ); }
                catch (InterruptedException ex) {}
            }
        }
    }

//------------------------------------------------------------------------------
//    Particle Methods
//------------------------------------------------------------------------------

    public Particle createParticle( String pname, double charge,
                                    double x, double y )
    {
        Element parent = scene.getElement( pname );
        if ( parent == null ) return null;

        Particle p;

        p = new Particle( scene, charge );
        p.move( new Point2d( x, y ) );

        synchronized( scene.getSceneLock() )
        {
            parent.addChild( p );
        }
        scene.redraw();
        return p;
    }

    public Particle createParticle( String pname, double charge,
                                    double x, double y,
                                    double xoff, double yoff )
    {
        xoff *= (1 - Math.random() * 2);
        yoff *= (1 - Math.random() * 2);

        return createParticle( pname, charge, x + xoff, y + yoff );
    }

//------------------------------------------------------------------------------
//    Particle Animation Methods
//------------------------------------------------------------------------------

    public void createParticles( String pname, double charge,
                                 double x, double y,
                                 double xoff, double yoff, int step,
                                 int count, int sleep, boolean block )
    {
        class A extends Thread
        {
            Element parent;
            double charge, x, y, xoff, yoff;
            int step, count, sleep;

            public A( Element parent, double charge,
                      double x, double y,
                      double xoff, double yoff,
                      int step, int count, int sleep )
            {
                this.parent = parent;
                this.charge = charge;
                this.x = x;
                this.y = y;
                this.xoff  = xoff;
                this.yoff  = yoff;
                this.step  = step;
                this.count = count;
                this.sleep = sleep;
            }

            public void run()
            {
                setPriority( Thread.MIN_PRIORITY );
                for ( int i = 0; i < count; i++ )
                {
                    double px = x + xoff * (1 - Math.random() * 2);
                    double py = y + yoff * (1 - Math.random() * 2);
                    synchronized( scene.getSceneLock() )
                    {
                        for ( int j = 0; j < step; j++ )
                        {
                            Particle p = new Particle( scene, charge );
                            p.move( new Point2d( px, py ) );
                            parent.addChild( p );
                        }
                    }
                    scene.animationRedraw();
                    try {sleep(sleep);} catch (InterruptedException ex) {}
                }
            }
        };

        Element parent = scene.getElement( pname );
        if ( parent == null ) return;

        A anim = new A( parent, charge, x, y, xoff, yoff, step, count, sleep );
        anim.start();

        if ( block )
        {
            while ( anim.isAlive() )
            {
                try { Thread.currentThread();
				Thread.sleep( 10 ); }
                catch (InterruptedException ex) {}
            }
        }
    }

//------------------------------------------------------------------------------
//    Property Methods
//------------------------------------------------------------------------------

    public void setProperty( String name, String value )
    {
        PropertyMgr pm = systemMgr.getPropertyMgr();

        if ( pm.isDefined( name ) )
        {
            pm.getProperty( name ).setValue( value );
        }

        pm.notifyObservers( this );
    }

//REMIND: implement 'getProperty' methods
}
