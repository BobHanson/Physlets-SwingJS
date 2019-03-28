package circuitsimulator;
import java.awt.Graphics;

import edu.davidson.tools.*;

/**
 * Circuit element, representing a battery (special case of source)
 * Usage: addObject("battery","row=1,col=1,v=1500");</li>
 * 
 * @author Toon Van Hoecke
 */
public class Battery extends Source
{
    public Battery (Circuit circ, int pol, int r, int c, String t, double v){
        super(circ,pol,r,c,t);
        if (v<0) {v=-v; changePolarity();}
        value = v;
        function = ""+Double.toString(value);
        setValueVisible(true);
    }
    
    Battery(Circuit circuit) {
        super(circuit);
        polarity = "p";
    }
    
    public void paintImage(Graphics g) {}//overriding
    
    public void paint(Graphics g){
        super.paint(g);
        int p = polarity.equals("p")? circuit.interGrid/2+1:circuit.interGrid/2-2;
        int p2 = polarity.equals("p")? circuit.interGrid/2+2:circuit.interGrid/2-3;
        g.setColor(java.awt.Color.red);
        if (to.equals("h")){ 
            g.drawLine(x+3, y, x+circuit.interGrid/2-2, y);
            g.drawLine(x+circuit.interGrid/2+1, y, x+circuit.interGrid-4, y);
            g.drawLine(x+p, y-6, x+p, y+6);
            g.drawLine(x+circuit.interGrid-1-p, y-3, x+circuit.interGrid-1-p, y+3);
            g.drawLine(x+circuit.interGrid-1-p2, y-3, x+circuit.interGrid-1-p2, y+3);
        } else {
            g.drawLine(x, y+3, x, y+circuit.interGrid/2-2);
            g.drawLine(x, y+circuit.interGrid/2+1, x, y+circuit.interGrid-4);
            g.drawLine(x-6, y+p, x+6, y+p);
            g.drawLine(x-3, y+circuit.interGrid-1-p, x+3, y+circuit.interGrid-1-p);
            g.drawLine(x-3, y+circuit.interGrid-1-p2, x+3, y+circuit.interGrid-1-p2);
        }
    }
    
    public double getV(double t) {
        return value;
    }
    
    public String valueStr(){
        return format.form(value);
    }
    
    public String getStringAdditions() {
        return ",v="+Double.toString(value)+",r="+internalResistance;
    }
    
    public void parsefunction() {} //overriding
    
    public boolean set(String list) {
        boolean ret=super.set(list);
        if (SUtil.parameterExist(list,"v=")) {
            double v=SUtil.getParam(list,"v=");
            if (v<0) {v=-v; changePolarity();}
            value = v;
            function = ""+Double.toString(value);
        }
        return ret;
    }
    
    public void setvalue(String s) {
        double v = (Double.valueOf(s)).doubleValue();
        if (v<0) {v=-v; changePolarity();}
        value = v;
        function = ""+Double.toString(value);
    }
}
