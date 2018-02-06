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

import java.awt.event.KeyListener; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent; 
import java.awt.event.MouseEvent; 
import java.awt.Graphics; 
import jacob.scene.*;
import jacob.geometry.*;

public class SceneEditor implements 
    KeyListener, 
    MouseListener, 
    MouseMotionListener
{
    private Scene scene;

    private SceneAction sceneAction;

    private Point2d mousesloc = null;

    private Point2d mouseloc = null;

//------------------------------------------------------------------------------
//    Constructor Methods
//------------------------------------------------------------------------------

    public SceneEditor( Scene scene ) 
    {
        this.scene = scene;
//FIXME
        sceneAction = new NullAction();
        sceneAction.setEditor( this );
        sceneAction.added();

        scene.addMouseListener( this );
        scene.addMouseMotionListener( this );
        scene.addKeyListener( this ); 
    }

//------------------------------------------------------------------------------
//    Scene Methods
//------------------------------------------------------------------------------

    public Scene getScene()
    {
        return scene;
    }

//------------------------------------------------------------------------------
//    Scene Action Methods
//------------------------------------------------------------------------------

    public void setAction( String saction ) 
        throws MalformedActionException, UnknownActionException
    {
        SceneAction action;

        if ( saction == null )
            action = null;
        else        
            action = ActionFactory.createAction( saction );

        if ( sceneAction != null )
        {
            sceneAction.removed();
            sceneAction.setEditor( null );
        }

        if ( action == null )
            sceneAction = new NullAction();
        else
            sceneAction = action;

        sceneAction.setEditor( this );
        sceneAction.added();
    }

//------------------------------------------------------------------------------
//    Editor Methods
//------------------------------------------------------------------------------

    public Point2d getMouseLocation()
    {
        return mouseloc;
    }

    public boolean isMouseInside()
    {
        return (mouseloc == null ? false : true );
    }

//------------------------------------------------------------------------------
//    Key Events 
//------------------------------------------------------------------------------

//FIXME
    public void keyTyped( KeyEvent e ) {} 

    public void keyPressed( KeyEvent e ) {}

    public void keyReleased( KeyEvent e ) {}

//------------------------------------------------------------------------------
//    AWT Mouse Events 
//------------------------------------------------------------------------------

    public void mouseClicked( MouseEvent e ) 
    {
        sceneAction.mouseClicked( new SceneMouseEvent( scene, e ) );
    }

    public void mousePressed( MouseEvent e ) 
    {
        sceneAction.mousePressed( new SceneMouseEvent( scene, e ) );
    }

    public void mouseReleased( MouseEvent e ) 
    {
        sceneAction.mouseReleased( new SceneMouseEvent( scene, e ) );
    }

    public void mouseEntered( MouseEvent e ) 
    {
        sceneAction.mouseEntered( new SceneMouseEvent( scene, e ) );
    }

    public void mouseExited( MouseEvent e ) 
    {
        mousesloc = mouseloc = null;
        sceneAction.mouseExited( new SceneMouseEvent( scene, e ) );
        scene.redraw();
    }

    public void mouseDragged( MouseEvent e ) 
    {
        SceneMouseEvent se = new SceneMouseEvent( scene, e );
        mousesloc = se.getSnappedLocation();
        mouseloc = se.getLocation();
        sceneAction.mouseDragged( se );
    }

    public void mouseMoved( MouseEvent e ) 
    {
        SceneMouseEvent se = new SceneMouseEvent( scene, e );
        mousesloc = se.getSnappedLocation();
        mouseloc = se.getLocation();
        sceneAction.mouseMoved( se );
    }

//------------------------------------------------------------------------------
//    Paint Methods 
//------------------------------------------------------------------------------

    public void paintFg( SceneGraphics g ) 
    {
        sceneAction.paintFg( g );
    } 

    public void paintBg( SceneGraphics g ) 
    {
        sceneAction.paintBg( g );
    }

    public void paintSnappedPointer( SceneGraphics g )
    {
        Graphics gg = g.getGraphics();

        if ( mousesloc == null || 
             !scene.getProperties().snapToGrid.getValue() )
            return; 

        double scale = g.getScale();
        int size = scene.getProperties().snapMarkSize.getValue();

        gg.setColor( scene.getProperties().snapMarkFg.getValue() );

        int mx = (int)(mousesloc.x * scale);
        int my = (int)(mousesloc.y * scale);
        gg.drawLine( mx - size, my, mx + size, my ); 
        gg.drawLine( mx, my - size, mx, my + size ); 
    }
}
