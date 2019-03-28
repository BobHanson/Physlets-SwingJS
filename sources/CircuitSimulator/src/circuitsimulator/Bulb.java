package circuitsimulator;
//import a2s.*;
import java.awt.*;

import java.awt.Color;
import java.awt.Graphics;

import edu.davidson.tools.*;

/**
 * Circuit element, representing a resistor
 * 
 * @author Toon Van Hoecke
 */
public class Bulb extends CircuitElement
{
    double volt,watt;
    int colorR, colorG, colorB;
    int ri=0;
    
    Bulb(Circuit circuit, double v, double w, int r, int c, String t) {
        super(circuit,r,c,t);
        variableImage=true;
        setValueVisible(false);
        volt = v;
        watt = w;
        value = volt*volt/watt;
        maxCurrentValue = watt/volt+1.0;
        colorB = colorG = colorR = 0;
    }

    Bulb(Circuit circuit) {super(circuit);}

    public double impedance() {return value;}
    
    public String getStringAdditions() {
        return ",v="+Double.toString(volt)+",w="+Double.toString(watt);
    }
    
    public boolean set(String list) {
        boolean ret=super.set(list);
        if (SUtil.parameterExist(list,"v=")) volt=SUtil.getParam(list,"v=");
        if (SUtil.parameterExist(list,"w=")) watt=SUtil.getParam(list,"w=");
        value = volt*volt/watt;
        maxCurrentValue = watt/volt+1.0;
        return ret;
    }
    
    public void paintImage(Graphics g) {}//overriding
    
    public String valueStr(){return Double.toString(volt)+"V,"+Double.toString(watt)+"W";}

    public void repaintImage(Graphics g){
        super.repaintImage(g);
        int cig = circuit.interGrid/2;
        colorG = (circuit.parsed?Math.min(255,(int)(255.0 * Math.abs(getI()*volt/watt))):0);
        colorR = (colorG<128)?colorG*2:255;
        colorB = (colorG<128)?0:2*(colorG-128);
        g.setColor(new Color(colorR,colorG,colorB));
        if (to.equals("h")){ 
            g.fillOval(x+cig-7,y-6,13,13);
            g.setColor(java.awt.Color.red);
            g.drawOval(x+cig-7,y-6,13,13);
            g.drawLine(x+cig-5, y-4, x+cig+4, y+5);
            g.drawLine(x+cig+4, y-4, x+cig-5, y+5);
        } else {
            g.fillOval(x-6,y+cig-7,13,13);
            g.setColor(java.awt.Color.red);
            g.drawOval(x-6,y+cig-7,13,13);
            g.drawLine(x-4, y+cig-5, x+5, y+cig+4);
            g.drawLine(x-4, y+cig+4, x+5, y+cig-5);
        }
    }
    
   public void paint(Graphics g){
        super.paint(g);
        g.setColor(java.awt.Color.red);
        int cig = circuit.interGrid/2;
        if (to.equals("h")){ 
            g.drawLine(x+3, y, x+cig-7, y);
            g.drawLine(x+cig+6, y, x+circuit.interGrid-4, y);
        } else {
            g.drawLine(x, y+3, x, y+cig-7);
            g.drawLine(x, y+cig+6, x, y+circuit.interGrid -4);
        }
        if (circuit.parsed && canvasElement) repaintImage(g);
        else {
            if (to.equals("h")){ 
                g.drawOval(x+cig-7,y-6,13,13);
                g.drawLine(x+cig-5, y-4, x+cig+4, y+5);
                g.drawLine(x+cig+4, y-4, x+cig-5, y+5);
            } else {
                g.drawOval(x-6,y+cig-7,13,13);
                g.drawLine(x-4, y+cig-5, x+5, y+cig+4);
                g.drawLine(x-4, y+cig+4, x+5, y+cig-5);
            }
        }
    }
}
