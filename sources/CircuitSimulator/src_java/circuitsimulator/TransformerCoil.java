 package circuitsimulator;
import java.awt.*;
import edu.davidson.tools.*;

/**
 * Circuit element, representing an inductor.
 * 
 * @author Toon Van Hoecke
 */
public class TransformerCoil extends CircuitElement
{
    int position;
    double ratio;
    
    TransformerCoil(Circuit circuit, double v, int r, int c, String t, int p) {
        super(circuit,r,c,t);
        numberOfNodes=4;
        value = v;
        position = p;
        ratio = 1;
        unity = "H";
        setValueVisible(false);
	}

    TransformerCoil(Circuit circuit, double v, int r, int c, String t, int p, double rat) {
        this(circuit,v,r,c,t,p);
        ratio= position==1?Math.sqrt(otherElem.value/value):Math.sqrt(value/otherElem.value);
	}
    
    TransformerCoil() {
        super();
        position = 1;
        ratio = 1;
		setBounds(0,1,52,40);
    }
    
    public double impedance(){return value/circuit.dt;}
    
    public double differential(){return value/circuit.dt;}
    
    public double impedanceCoupled(){return Math.sqrt(value*otherElem.value)/circuit.dt;}
    
    public double differentialCoupled(){return Math.sqrt(value*otherElem.value)/circuit.dt;}

    public String getStringAdditions() {
        return ",position="+position+",l="+value+",ratio="+ratio;
    }
    
    public boolean set(String list) {
        boolean ret=super.set(list);
        if (SUtil.parameterExist(list,"l=")) value=SUtil.getParam(list,"l=");
        ratio= position==1?Math.sqrt(otherElem.value/value):Math.sqrt(value/otherElem.value);
        if (SUtil.parameterExist(list,"ratio=")) {
            ratio=SUtil.getParam(list,"ratio=");
            otherElem.value = value*(position==1?ratio*ratio:1/(ratio*ratio)); 
            ((TransformerCoil)otherElem).ratio = ratio;
        }
        return ret;
    }
    
    public void coupledTo(CircuitElement other) {
        super.coupledTo(other);
        ratio= position==1?Math.sqrt(otherElem.value/value):Math.sqrt(value/otherElem.value);
    }
    
    public void loadImage(Graphics g) {}//overriding
    
    void showValue(Graphics g) {//display value and ratio
        g.setColor(java.awt.Color.blue);
        if (to.equals("h")) g.drawString(valueStr(), x+circuit.interGrid/2-17, y+3);
        else g.drawString(valueStr(), x-7, y+circuit.interGrid/2-1);
    }
    
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(java.awt.Color.red);
        int q = (position==1)? 0:90;
        int r = (position==1)? -1:1;
        int p = (position==1)? circuit.interGrid/2-4:4-circuit.interGrid/2;
        int xcig = x+circuit.interGrid/2, ycig = y+circuit.interGrid/2;
        int xs = (position==1)? x+circuit.interGrid/2-20:x-circuit.interGrid/2+10;
        int ys = (position==1)? y+circuit.interGrid/2-20:y-circuit.interGrid/2+10;
        if (canvasElement) {
            if (to.equals("h")) {
                g.drawLine(x, y-3*r, x, ys+5);
                g.drawLine(x, ys+5, xcig-23, ys+5);
                g.drawArc(xcig-23,ys,10,10,180,-225*r);
                g.drawArc(xcig-16,ys,10,10,135+q,-270*r);
                g.drawArc(xcig-9,ys,10,10,135+q,-270*r);
                g.drawArc(xcig-2,ys,10,10,135+q,-270*r);
                g.drawArc(xcig+5,ys,10,10,135+q,-270*r);
                g.drawArc(xcig+12,ys,10,10,135+q,-225*r);
                g.drawLine(xcig+23, ys+5, x+circuit.interGrid, ys+5);
                g.drawLine(x+circuit.interGrid, y-3*r, x+circuit.interGrid, ys+5);
                g.drawLine(xcig-20, y+p, xcig+20, y+p);
            } else {
                g.drawLine(x-3*r, y, xs+5, y);
                g.drawLine(xs+5, y, xs+5, ycig-23);
                g.drawArc(xs,ycig-23,10,10,90,225*r);
                g.drawArc(xs,ycig-16,10,10,135-q,270*r);
                g.drawArc(xs,ycig-9,10,10,135-q,270*r);
                g.drawArc(xs,ycig-2,10,10,135-q,270*r);
                g.drawArc(xs,ycig+5,10,10,135-q,270*r);
                g.drawArc(xs,ycig+12,10,10,135-q,225*r);
                g.drawLine(xs+5, ycig+23, xs+5, y+circuit.interGrid);
                g.drawLine(x-3*r, y+circuit.interGrid, xs+5, y+circuit.interGrid);
                g.drawLine(x+p, ycig-20, x+p, ycig+20);
            }
            return;
        } else {
            g.drawLine(2, 5, 4, 5);
            g.drawArc(4,0,10,10,180,225);
            g.drawArc(11,0,10,10,135,270);
            g.drawArc(18,0,10,10,135,270);
            g.drawArc(25,0,10,10,135,270);
            g.drawArc(32,0,10,10,135,270);
            g.drawArc(39,0,10,10,135,225);
            g.drawLine(50, 5, 52, 5);
            
            g.drawLine(4, 15, 48, 15);
            g.drawLine(4, 20, 48, 20);
            
            g.drawLine(2, 30, 4, 30);
            g.drawArc(4,25,10,10,180,-225);
            g.drawArc(11,25,10,10,225,-270);
            g.drawArc(18,25,10,10,225,-270);
            g.drawArc(25,25,10,10,225,-270);
            g.drawArc(32,25,10,10,225,-270);
            g.drawArc(39,25,10,10,225,-225);
            g.drawLine(50, 30, 52, 30);
        }
    }
}
