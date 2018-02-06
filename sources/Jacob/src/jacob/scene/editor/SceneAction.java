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
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//
//==============================================================================

package jacob.scene.editor;

import java.awt.PopupMenu; 
import java.awt.event.MouseEvent; 

import jacob.main.*;
import jacob.scene.*;
import jacob.scene.modifiers.*;
import jacob.geometry.*;

public abstract class SceneAction
{
    protected SceneEditor sceneEditor = null;

//------------------------------------------------------------------------------
//    Constructor Methods
//------------------------------------------------------------------------------

    public SceneAction() {}

//------------------------------------------------------------------------------
//    Status Methods
//------------------------------------------------------------------------------

    public void added() {}

    public void removed() {}

//------------------------------------------------------------------------------
//    Editor Methods
//------------------------------------------------------------------------------

    public void setEditor( SceneEditor e )
    {
        sceneEditor = e;
    }

    public SceneEditor getEditor()
    {
        return sceneEditor;
    }

    public Scene getScene()
    {
        return sceneEditor.getScene();
    }

    public Properties getProperties()
    {
        return sceneEditor.getScene().getProperties();
    }

//------------------------------------------------------------------------------
//    Scene Mouse Events 
//------------------------------------------------------------------------------

    public void mouseClicked( SceneMouseEvent e ) 
    {
         MouseEvent ee = e.getMouseEvent();
         if ( !ee.isMetaDown() )
         {
            mouseNormalClicked( e );
         }
    }

    public void mousePressed( SceneMouseEvent e ) 
    {
         MouseEvent ee = e.getMouseEvent();
         if ( ee.isMetaDown() )
         {
             PopupMenu popup = getScene().getPopupMenu();

             if ( popup != null )
                 popup.show( getScene(), ee.getX(), ee.getY() );
         }
         else
         { 
            mouseNormalPressed( e );
         }
    }

    public void mouseReleased( SceneMouseEvent e ) 
    {
         MouseEvent ee = e.getMouseEvent();
         if ( !ee.isMetaDown() )
            mouseNormalReleased( e );
    } 

    public void mouseNormalClicked( SceneMouseEvent e ) {} 

    public void mouseNormalPressed( SceneMouseEvent e ) {}

    public void mouseNormalReleased( SceneMouseEvent e ) {} 

    public void mouseEntered( SceneMouseEvent e ) {}

    public void mouseExited( SceneMouseEvent e ) {} 

    public void mouseDragged( SceneMouseEvent e ) 
    {
        if ( !e.isMetaDown() )
            mouseNormalDragged( e );
    }

    public void mouseNormalDragged( SceneMouseEvent e ) {}

    public void mouseMoved( SceneMouseEvent e ) {}

//------------------------------------------------------------------------------
//    Paint Methods 
//------------------------------------------------------------------------------

    public void paintFg( SceneGraphics g ) {} 

    public void paintBg( SceneGraphics g ) {}

//FIXME
    public void paintSnappedPointer( SceneGraphics g ) 
    {
        sceneEditor.paintSnappedPointer( g );
    }

//------------------------------------------------------------------------------
//    Get Component Methods
//------------------------------------------------------------------------------

    public SceneComponent getComponentAt( String type, Point2d p )
    {
//FIXME
        if ( type.equals( "element" ) )
            return getElementAt( p );
        else if ( type.equals( "particle" ) )
            return getParticleAt( p );
        else if ( type.equals( "elementorparticle" ) )
            return getElementOrParticleAt( p );
        else
            return null;
    }

    public SceneComponent getElementAt( Point2d p )
    {
        SceneIterator iterator;
        SceneComponent closest = null;
        double mind = Double.MAX_VALUE;

        iterator = new ElementIterator( getScene().createIterator() );

        while ( !iterator.isDone() )
        {
            SceneComponent c = iterator.getCurrentComponent();

            if ( c.contains( p ) )
            {
                double d = c.getBorderDistance( p );

//System.out.println( "contains d = " + d );
                if ( d < mind )
                {
                    mind = d;
                    closest = c;
                }
            }
            iterator.next();
        }
        return closest;
    }

    public SceneComponent getParticleAt( Point2d p )
    {
        SceneIterator iterator;
        SceneComponent closest = null;
        double mind = getScene().getProperties().touchOffset.getValue();

        iterator = new ParticleIterator( getScene().createIterator() );

        while ( !iterator.isDone() )
        {
            SceneComponent c = iterator.getCurrentComponent();

            double d = c.getCenterDistance( p );

            if ( d < mind )
            {
                mind = d;
                closest = c;
            }
            iterator.next();
        }
        return closest;
    }

    public SceneComponent getElementOrParticleAt( Point2d p )
    {
        SceneIterator iterator;
        SceneComponent closest = null;
        double toff = getScene().getProperties().touchOffset.getValue();
        double mind = Double.MAX_VALUE;

        iterator = getScene().createIterator();

        while ( !iterator.isDone() )
        {
            SceneComponent c = iterator.getCurrentComponent();

            if ( c instanceof Element )
            {
                if ( c.contains( p ) )
                {
                    double d = c.getBorderDistance( p );

                    if ( d < mind )
                    {
                        mind = d;
                        closest = c;
                    }
                }
            }
            else if ( c instanceof Particle )
            {
                double d = c.getCenterDistance( p );

                if ( d < toff && d < mind )
                {
                    mind = d;
                    closest = c;
                }
            }
            iterator.next();
        }
        return closest;
    }

    public Object getElementOrModifierAt( Point2d p, Class modtype )
    {
        ElementIterator iterator;
        Element  closest = null;
        ComponentModifier closestmod = null;
        int closesthspt = -1;
        double mind = Double.MAX_VALUE;
        double minhsptd = getScene().getProperties().touchOffset.getValue();

        iterator = new ElementIterator( getScene().createIterator() );

        while ( !iterator.isDone() )
        {
            Element e = iterator.getCurrentElement();

            if ( e.contains( p ) )
            {
                double d = e.getBorderDistance( p );

                if ( d < mind )
                {
                    mind = d;
                    closest = e;
                }
            }
            if ( e.hasModifiers() )
            {
                ComponentModifier m = e.getModifiers().getModifier( modtype );

                if ( m != null )
                {
                    Point2d loc = e.getLocation();
                    for ( int i = 0; i < m.countHotspots(); i++ )
                    {
                        Point2d hotspot = m.getHotspotAbsAt( i );
                        double d = hotspot.distance( p );

                        if ( d < minhsptd )
                        {
                            minhsptd = d;
                            closesthspt = i;
                            closestmod = m;
                        }
                    }
                }
            }
            iterator.next();
        }
        if ( closestmod != null )
//FIXME return hotspot data
            return closestmod;
        else
            return closest;
    }
}
