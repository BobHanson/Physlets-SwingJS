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

import java.awt.Graphics;
import java.awt.Color;

public class ClockIndicator extends Indicator
{

//------------------------------------------------------------------------------
//    Constructor Methods
//------------------------------------------------------------------------------

    public ClockIndicator() {}

//------------------------------------------------------------------------------
//    Paint Methods
//------------------------------------------------------------------------------
   
//FIXME: rewrite this
    public void paint( Graphics g )
    {
        int height = getSize().height;
        int width  = getSize().width;
        double val = -(getValue() * getScale());

        g.setColor( getBackground() );

        g.fill3DRect( 0, 0, width, height, true );

        g.setColor( Color.blue );
        g.fillRect( 1, 1, (width - 2) / 2, height - 2 );
        g.setColor( Color.red );
        g.fillRect( (width - 2) / 2, 1, width - 1 - (width - 2) / 2, 
                    height - 2 );

        int pivotx = (width - 2) / 2;
        int pivoty = height - 1;

        int px = (width - 2) / 2;
        int py = (height - 2) / 2;

        if ( val > Math.PI/2 ) val = Math.PI/2;
        else if ( val < -Math.PI/2 ) val = -Math.PI/2;

        double cosf = Math.cos( val );
        double sinf = Math.sin( val );

        int npx = (int)((px - pivotx) * cosf + (py - pivoty) * sinf + pivotx);
        int npy = (int)(-(px - pivotx) * sinf + (py - pivoty) * cosf + pivoty);

        g.setColor( Color.white );
        g.drawLine( pivotx, pivoty, npx, npy );

        g.drawLine( 3, 5, 7, 5 );
        g.drawLine( width - 3, 5, width - 7, 5 ); 
        g.drawLine( width - 5, 3, width - 5, 7 ); 
    }
}
