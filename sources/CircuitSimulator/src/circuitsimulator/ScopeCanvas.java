package circuitsimulator;

import java.awt.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Point;


import edu.davidson.display.Format;

/**
 * Used by Oscillodialog. Canvas displaying the voltage signal of the pointed element.
 * 
 * @author Toon Van Hoecke
 */
public class ScopeCanvas extends Canvas
{
    Color bgColor = new Color(77,57,7);
    Color gridColor = new Color(162,145,117);
    Graphics g0;
    Image grid;
    int width = 351, height = 281;
    CircuitBuilder cb;
    OscilloDialog oscilloDialog;
    int elementID; 
    CircuitElement ce;
    Format format = new Format("%6.4f");
    
    ScopeCanvas() {
        super();
		setBackground(bgColor);
		setBounds(20,20,width,height);
		//{{INIT_CONTROLS
		setSize(0,0);
		//}}
	}
	
	ScopeCanvas(CircuitBuilder cirbuilder, OscilloDialog oscdiag) {
	    this();
	    cb = cirbuilder;
	    oscilloDialog = oscdiag;
	    elementID = cb.currentElement.hashCode();
        ce = cb.currentElement;
	}
	
	public void setCircuit(CircuitBuilder cirbuilder, OscilloDialog oscdiag)
	{
	    cb = cirbuilder;
	    oscilloDialog = oscdiag;
	    elementID = cb.currentElement.hashCode();
        ce = cb.currentElement;
	}
	
    public void paint(Graphics g) {
        double[] Y;
        Y = new double[cb.numberofdt];
        Y=cb.getVoltage(elementID);
        
        int int1;
        int int2;
        int int3;
        
        int sign = (ce.direction != ce.vequation.direction)? -1 : 1;
        if ((cb.debugLevel&cb.DEBUG_IO)>0) 
            System.out.println(sign+" "+ce.direction+" "+ce.vequation.direction);
             
        g.setColor( gridColor );
        int3 = 0; int1 = 0;
        do {g.drawLine( 0, int3, 350, int3 ); int3 += 35;} while( ++int1 < 9 );
        int2 = 0; int1 = 0;
        do {g.drawLine( int2, 0, int2, 280 ); int2 += 35;} while( ++int1 < 11 );
        int3 = 7; int1 = 1;
        do {g.drawLine( 173, int3, 178, int3 ); int3 += 7;} while( ++int1 < 40 );
        int2 = 7; int1 = 1;
        do {g.drawLine( int2, 138, int2, 143 ); int2 += 7;} while( ++int1 < 50 );

        double vertScale = height/(8*oscilloDialog.voltageScaling);
        double horScale = width*cb.dt/(10*oscilloDialog.timeScaling);
        int v = (int)oscilloDialog.verOffset, h = (int)oscilloDialog.horOffset;
        double dc = 0.0;
                
        g.setColor(java.awt.Color.yellow);
        int m = 1;
        if (oscilloDialog.mode.equals("Ground")) { 
            g.drawLine(h,height/2-v,h+width,height/2-v);
        } else {
            if (oscilloDialog.mode.equals("AC"))
                dc = cb.getDCLevel(elementID)*sign;
            if ((cb.debugLevel&cb.DEBUG_IO)>0) System.out.println("DC Level = "+dc);
            for (int n=1;n<cb.numberofdt;n++) 
            g.drawLine(h+(int)((n-1)*horScale),height/2-v-(int)((Y[n-1]*sign-dc)*vertScale),
                       h+(int)(n*horScale),height/2-v-(int)((Y[n]*sign-dc)*vertScale));
            while (m*cb.dt*cb.numberofdt < 10*oscilloDialog.timeScaling) {
                double lastpt = Y[cb.numberofdt-1]*sign-dc;
                int os = m * cb.numberofdt;
                m++;
                cb.calculateCircuit();
                Y=cb.getVoltage(elementID);
                if (oscilloDialog.mode.equals("AC"))
                    dc = cb.getDCLevel(elementID)*sign;
                g.drawLine(h+(int)((os-1)*horScale),height/2-v-(int)(lastpt*vertScale),
                           h+(int)((os)*horScale),height/2-v-(int)((Y[0]*sign-dc)*vertScale));
                for (int n=1;n<cb.numberofdt;n++) 
                g.drawLine(h+(int)((os+n-1)*horScale),height/2-v-(int)((Y[n-1]*sign-dc)*vertScale),
                           h+(int)((os+n)*horScale),height/2-v-(int)((Y[n]*sign-dc)*vertScale));
            }
        }
	}
	
	String getCoords(Point p) {
        double vertScale = (height-1)/(8*oscilloDialog.voltageScaling);
        double horScale = (width-1)/(10*oscilloDialog.timeScaling);
        double pt = ((double)p.x - oscilloDialog.horOffset) / horScale;
        double pv = (height/2 - p.y - oscilloDialog.verOffset) / vertScale;
        return scaled(pt,"s")+", "+scaled(pv,"V");
    }
	
    String scaled(double v, String type) {
        if (Math.abs(v) < 1E-8) {return format.form(v*1e9)+ " n"+type;} 
        else if (Math.abs(v) < 1E-4) {return format.form(v*1e6)+ " �"+type;}
        else if (Math.abs(v) < 1E-1) {return format.form(v*1e3)+ " m"+type;}
        else if (Math.abs(v) < 1E3) {return format.form(v)+ " "+type;}
        else if (Math.abs(v) < 1E6) {return format.form(v*1e-3)+ " k"+type;}
        else {return format.form(v*1e-6)+ " M"+type;}
    }
	
	//{{DECLARE_CONTROLS
	//}}
}
    