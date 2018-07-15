package symantec.itools.awt.shape;

import java.awt.Graphics;
import java.awt.Color;

//  07/30/97	LAB	Updated version to 1.1.  Added contains method.  Modified paint to use the
//				calculated bevel colors if it was in BEVEL_RAISED or BEVEL_LOWERED mode.
//	08/15/97	LAB	Made paint call super.paint to ensure the colors would get updated if needed.

/**
 * This class forms the Ellipse shape component.
 * @see symantec.itools.awt.shape.Circle
 * @version 1.1, July 28, 1997
 * @author Symantec
 */
public class Ellipse extends Shape
{
    /**
     * Constructs a default Ellipse.
     */
    public Ellipse()
    {
    }

    /**  
     * Checks whether this component "contains" the specified (x, y)
     * location, where x and y are defined to be relative to the 
     * coordinate system of this component.  
     * @param x the x coordinate 
     * @param y the y coordinate
     * @see java.awt.Component#getComponentAt
     */
    public boolean contains(int x, int y)
    {
    	//If the coordinate is outside the enclosing rectangle, then it's outside the ellipse.
    	if(! super.contains(x, y))
    		return false;
   
    	double a		= (double) width / 2;
    	double b		= (double) height / 2;
        double dx		= (x - a) * ((double) height / width);
        double dy		= (y - b);

    	if (fill)
    	{
	        return (dx * dx + dy * dy <= b * b);
		}
		else
		{
			// Our calculations for the ellipse are too precice to exactly match
			// the pixels that are drawn, so we need to fudge it a little.
			return (Math.abs((dx * dx + dy * dy + 30) - (b * b)) < 60);
		}
    }

	/**
	 * Paints the ellipse using the given graphics context.
     * This is a standard Java AWT method which typically gets called
     * by the AWT to handle painting this component. It paints this component
     * using the given graphics context. The graphics context clipping region
     * is set to the bounding rectangle of this component and its <0,0>
     * coordinate is this component's top-left corner.
     *
     * @param g the graphics context used for painting
     * @see java.awt.Component#repaint
     * @see java.awt.Component#update
	 */
    public void paint(Graphics g)
    {
    	super.paint(g);
    	
        g.clipRect(0, 0, width, height);

        int w = width - 1, h = height - 1;

        switch (style)
        {
            case BEVEL_LINE :
 		        if (fill)
		        {
		            g.setColor(fillColor);
		            g.fillOval(0, 0, width, height);
		        }
               	g.setColor(getForeground());
                g.drawOval(0, 0, w, h);
                break;

            case BEVEL_LOWERED :
 		        if (fill)
		        {
		            g.setColor(fillColor);
		            g.fillOval(0, 0, width, height);
		        }
                g.setColor(bevelDarkerColor);
                g.drawArc(0, 0, w, h, 45, 180);

                g.setColor(bevelLighterColor);
                g.drawArc(0, 0, w, h, 225, 180);
                break;

            case BEVEL_RAISED :
 		        if (fill)
		        {
		            g.setColor(fillColor);
		            g.fillOval(0, 0, width, height);
		        }
                g.setColor(bevelLighterColor);
                g.drawArc(0, 0, w, h, 45, 180);

                g.setColor(bevelDarkerColor);
                g.drawArc(0, 0, w, h, 225, 180);
                break;
                
            default :
		        if (fill)
		        {
		            g.setColor(fillColor);
		            g.fillOval(0, 0, width, height);
		        }
		        else
		        {
	                g.setColor(getForeground());
	                g.drawOval(0, 0, w, h);
		        }
        }
    }
}

