package circuitsimulator;

import java.awt.Graphics;

/**
 * Dummy Circuit element.
 * 
 * @author Toon Van Hoecke
 */
public class Nothing extends CircuitElement
{
    Nothing (Circuit circuit, int r, int c, String t) {super(circuit,r,c,t);
	}
    
    public void paint(Graphics g){}
}
