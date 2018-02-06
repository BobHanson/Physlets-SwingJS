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

package jacob.scene.modifiers;

import jacob.scene.*;
import jacob.geometry.*;
import jacob.data.*;

public class Rotator extends VectorModifier
{

    private double ex = 0;
 
    private double ey = 0;

//------------------------------------------------------------------------------
//    Constructor Methods
//------------------------------------------------------------------------------

    public Rotator() {} 

//------------------------------------------------------------------------------
//    Status Methods
//------------------------------------------------------------------------------

    public void added()
    {
        ex = ey = 0;
    }

//------------------------------------------------------------------------------
//    Component Modification Methods
//------------------------------------------------------------------------------

    public void preIntegrationModify( long time )
    {
        Point2d loc = getComponent().getLocation();
        Point2d pivot = getHotspotAbsAt( 0 );

        double rconst = getProperties().rotationConst.getValue();

        ex *= rconst;
        ey *= rconst;

        double dx = pivot.x - loc.x;
        double dy = pivot.y - loc.y;

        double d = Math.sqrt( dx * dx + dy * dy );
 
        double fi = ( dx * ey - dy * ex ) / d;

        getComponent().rotate( pivot, fi );

        /* we have to fix pivot point because of numerical errors */
        moveHotspotAbsAt( 0, pivot );
 
        ex = ey = 0;
    } 

    public void postIntegrationModify( long time )
    {
        ParticleIterator iterator;

        iterator = new ParticleIterator( getComponent().createIterator() );

        while( !iterator.isDone() )
        {
//REMIND: this could be done in Integrator (for speed reasons)
            Particle p = iterator.getCurrentParticle();
            ex += p.getEx();
            ey += p.getEy();
            iterator.next();
        }
    }

//------------------------------------------------------------------------------
//    Paint Methods
//------------------------------------------------------------------------------

    public void paintFg( SceneGraphics g )
    {
//FIXME: we should always draw the pivot when editing
        if ( !getProperties().showRotatorPivot.getValue() )
            return;

        Point2d loc  = getComponent().getLocation();
        Point2d pivot = getHotspotAbsAt( 0 );

        g.setColor( getProperties().pivotFg.getValue() );
        g.drawLine( loc.x, loc.y, pivot.x, pivot.y );
        g.drawLine( pivot.x - 4, pivot.y - 4, pivot.x + 4, pivot.y + 4 );
        g.drawLine( pivot.x - 4, pivot.y + 4, pivot.x + 4, pivot.y - 4 );
    } 

//------------------------------------------------------------------------------
//    Data R/W Methods
//------------------------------------------------------------------------------

    public void readData( DataElement data )
        throws DataParseException
    {
        Point2d pivot = new Point2d();

        pivot.x = data.getDoubleAttribute( "x" );
        pivot.y = data.getDoubleAttribute( "y" );

        moveHotspotAbsAt( 0, pivot ); 
    }
 
    public void writeData( DataElement data )
    {
        DataElement rdata = data.newElement( "rotator" );

        Point2d pivot = getHotspotAbsAt( 0 );

        rdata.setAttribute( "x", pivot.x );
        rdata.setAttribute( "y", pivot.y );
    }
}
