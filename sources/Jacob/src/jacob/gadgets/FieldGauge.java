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

package jacob.gadgets;

import java.util.Vector;

import jacob.geometry.*;

public class FieldGauge extends Gauge
{
 
//------------------------------------------------------------------------------
//    Attributes Methods
//------------------------------------------------------------------------------

    protected double getValue()
    {
        Point2d loc = getScene().getMouseLocation();
        if ( loc == null ) return 0;

        Vector pdata = getParticlesData();

        double pot = 0;

//FIXME: implement zero location 
        double rzero = Math.sqrt( ( loc.x - 0 ) * ( loc.x - 0 ) +
                                  ( loc.y - 0 ) * ( loc.y - 0 ) ); 
 
        for ( int i = 0; i < pdata.size(); i++) 
        {
            ParticleData p = (ParticleData) pdata.elementAt( i );
            double r = Math.sqrt( ( p.x - loc.x ) * ( p.x - loc.x ) +
                                  ( p.y - loc.y ) * ( p.y - loc.y ) );
 
            if ( rzero > 0 )
                pot += p.charge * Math.log( r / rzero );
        }           

        return -pot;
    }
}
