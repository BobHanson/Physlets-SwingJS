package symantec.itools.awt.shape;

import java.awt.Graphics;
import java.awt.Color;

//  07/30/97	LAB	Updated version to 1.1.  Added contains method.  Modified paint to use the
//				calculated bevel colors if it was in BEVEL_RAISED or BEVEL_LOWERED mode.
//	08/15/97	LAB	Made paint call super.paint to ensure the colors would get updated if needed.

/**
 * This class forms the Rectangle shape component.
 * @see symantec.itools.awt.shape.HorizontalLine
 * @see symantec.itools.awt.shape.Square
 * @see symantec.itools.awt.shape.VerticalLine
 * @version 1.1, July 30, 1997
 * @author Symantec
 */
public class Rect extends Shape
{
    /**
     * Constructs a default Rectangle.
     */
    public Rect()
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
    	if (fill)
    	{
	        return super.contains(x, y);
		}
		else
		{
			if ((y == 0) || (y == height - 1))
				return ((x >= 0) && (x < width));
			
			if ((x == 0) || (x == width - 1))
				return ((y >= 0) && (y < height));
				
			return false;
		}
    }

	/**
	 * Paints the rectangle using the given graphics context.
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
                    g.fillRect(0, 0, w, h);
                }
	            g.setColor(getForeground());
	            g.drawRect(0, 0, w, h);
	            break;

            case BEVEL_LOWERED :
                if (fill)
                {
                    g.setColor(fillColor);
                    g.fillRect(1, 1, w - 1, h - 1);
                }

                g.setColor(bevelDarkerColor);
                g.drawLine(0, h, 0, 0);
                g.drawLine(0, 0, w, 0);

                g.setColor(bevelLighterColor);
                g.drawLine(w, 0, w, h);
                g.drawLine(w, h, 0, h);

                break;

            case BEVEL_RAISED :
                if (fill)
                {
                    g.setColor(fillColor);
                    g.fillRect(1, 1, w - 1, h - 1);
                }
                
               	g.setColor(bevelLighterColor);
                g.drawLine(0, h, 0, 0);
                g.drawLine(0, 0, w, 0);

                g.setColor(bevelDarkerColor);
                g.drawLine(w, 0, w, h);
                g.drawLine(w, h, 0, h);

                break;
                
	        default :
                if (fill)
                {
                    g.setColor(fillColor);
                    g.fillRect(0, 0, width, height);
                }
                else
                {
	                g.setColor(getForeground());
	                g.drawRect(0, 0, w, h);
				}
        }
    }
}
