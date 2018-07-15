package circuitsimulator;
import java.awt.*;

/**
 * Circuit element, representing a wire with no resistance
 * 
 * @author Toon Van Hoecke
 */
public class Wire extends CircuitElement
{
    Wire(Circuit circuit, int r, int c, String t) {
        super(circuit,r,c,t);
        setValueVisible(false);
        maxCurrentValue=100;
    }
    
    Wire() {super();}
    
    public void loadImage(Graphics g) {}//overriding
    
    public void paint(Graphics g){
        super.paint(g);
        int cig = (circuit != null) ? circuit.interGrid : 27;
        g.setColor(java.awt.Color.red);
        if (to.equals("h")) g.drawLine(x+3, y, x+cig-4, y);
        else g.drawLine(x, y+3, x, y+cig-4);
    }
    
    public void overload(Graphics g, int ix, int iy) {}
    
}
