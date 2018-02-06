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

import java.awt.BorderLayout;

import jacob.main.*;
import jacob.lib.*;

public abstract class Gauge extends Gadget
    implements Runnable
{
    private Thread thread = null;

    private DoubleBufferPanel panel;
 
    private Indicator indicator;

//------------------------------------------------------------------------------
//    Connect Methods
//------------------------------------------------------------------------------

    public void connect( JInterface jint )
    {
        super.connect( jint );
        startGauge();
    }

//------------------------------------------------------------------------------
//    Applet Methods
//------------------------------------------------------------------------------

    public void init()
    {
        setLayout( new BorderLayout() );
        panel = new DoubleBufferPanel();
        panel.setLayout( new BorderLayout() );
        add( panel );
        setType( "bar" );
    }

//------------------------------------------------------------------------------
//    Attributes Methods
//------------------------------------------------------------------------------

    public void setType( String type )
    {
        type = type.toLowerCase();

        if ( indicator != null )
            panel.remove( indicator );

        if ( type.equals( "bar" ) )
            indicator = new BarIndicator();
        else if ( type.equals( "clock" ) )
            indicator = new ClockIndicator();
        else if ( type.equals( "graph" ) )
            indicator = new GraphIndicator();
        else
            indicator = new BarIndicator(); 

        panel.add( indicator );
        panel.doLayout();
    } 

    public void setValue( double value )
    {
        indicator.setValue( value );
    }

    public void setScale( double scale )
    {
        indicator.setScale( scale );
    }

    protected abstract double getValue();

//------------------------------------------------------------------------------
//    Thread Methods
//------------------------------------------------------------------------------

    public void startGauge()
    {
        if ( thread == null )
        {
            thread = new Thread( this );
            thread.start();
        }
    }
 
    public void stopGauge()
    {
        if ( thread != null && thread.isAlive() )
        {
            thread.stop();
        }
        thread = null;
    }
 
    public void run()
    {
        thread.setPriority( Thread.MIN_PRIORITY );
 
        while ( thread != null )
        {                             
            indicator.setValue( getValue() );
            try
            {
//FIXME: make sleep time a variable
                Thread.sleep( 200 ); 
            }
            catch (InterruptedException e) {} 
        }
    }
}
