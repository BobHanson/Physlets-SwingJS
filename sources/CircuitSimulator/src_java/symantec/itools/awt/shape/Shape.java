package symantec.itools.awt.shape;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import symantec.itools.awt.BevelStyle;

//	06/02/97	LAB	Updated to support Java 1.1
//  07/31/97	LAB	Made lightweight.  Added an override to setBackground to calculate bevel
//					colors.  Updated preferredSize to getPreferredSize.
//  08/13/97	LAB	Added getMinimumSize which returns a 10 by 10 dimension.  Addresses Mac
//					Bug #7138.  Updated the color calculations to use new methods in ColorUtils.
//	08/15/97	LAB	Reworked the way colors were calculated to avoid NullPointerExceptions,
//					and potential redraw problems.  Now colors are recalculated in paint,
//					if needed.
//	09/16/97	RKM	Defaulted fillColor to Color.black
//	10/01/98	EB  Replaced size by getSize; removed some wrong comments

/**
 * Abstract class for shape components.
 * This is the parent Shape class for the various
 * shape components.
 * @see symantec.itools.awt.shape.Ellipse
 * @see symantec.itools.awt.shape.Circle
 * @see symantec.itools.awt.shape.Rectangle
 * @see symantec.itools.awt.shape.Square
 * @see symantec.itools.awt.shape.VericalLine
 * @see symantec.itools.awt.shape.HorizontalLine
 * @version 1.1, June 2, 1997
 * @author Symantec
 */
public abstract class Shape extends Component implements BevelStyle
{
    /**
     * Construct default Shape with BEVEL_LINE style.
     */
    protected Shape()
    {
        style = BEVEL_LINE;
        cachedBackground = getBackground();
    }

    /**
     * Sets the border style of the shape.
     * @see #getBevelStyle
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setBevelStyle(int s) throws PropertyVetoException
    {
    	if(style != s)
    	{
	    	Integer oldValue = new Integer(style);
	    	Integer newValue = new Integer(s);

			vetos.fireVetoableChange("BevelStyle", oldValue, newValue);

	        style = s;
	        repaint();

			changes.firePropertyChange("BevelStyle", oldValue, newValue);
		}
    }

    /**
     * Returns the current style of the shape.
     * @see #setBevelStyle
     */
    public int getBevelStyle()
    {
        return style;
    }

    /**
     * Sets the fill mode of the shape.
     * @see #isFillMode
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setFillMode(boolean f) throws PropertyVetoException
    {
    	if(fill != f)
    	{

	    	Boolean oldValue = new Boolean(fill);
	    	Boolean newValue = new Boolean(f);

			vetos.fireVetoableChange("FillMode", oldValue, newValue);

	        fill = f;
	        repaint();

			changes.firePropertyChange("FillMode", oldValue, newValue);
		}
    }

    /**
     * Returns the current fill mode of the shape.
     * @see #setFillMode
     */
    public boolean isFillMode()
    {
        return fill;
    }

    /**
     * @deprecated
     * @see #isFillMode
     */
    public boolean getFillMode()
    {
        return isFillMode();
    }

    /**
     * Sets the fill color of the shape.
     * @see #getFillColor
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setFillColor(Color color) throws PropertyVetoException
    {
    	if(!symantec.itools.util.GeneralUtils.objectsEqual(fillColor, color))
    	{
	    	Boolean oldValue = new Boolean(fill);

			vetos.fireVetoableChange("FillColor", oldValue, color);

	        fillColor = color;
	        repaint();

			changes.firePropertyChange("FillColor", oldValue, color);
		}
    }

    /**
     * Returns the current fill color of the shape.
     * @see #setFillColor
     */
    public Color getFillColor()
    {
        return fillColor;
    }

    /**
     * Moves and/or resizes this component.
     * This is a standard Java AWT method which gets called to move and/or
     * resize this component. Components that are in containers with layout
     * managers should not call this method, but rely on the layout manager
     * instead.
     *
     * @param x horizontal position in the parent's coordinate space
     * @param y vertical position in the parent's coordinate space
     * @param width the new width
     * @param height the new height
     */
    public void reshape(int x, int y, int width, int height)
    {
        this.width  = width;
        this.height = height;

        super.reshape(x, y, width, height);
    }

    /**
	 * Returns the minimum dimensions to properly display this component.
     * This is a standard Java AWT method which gets called to determine
     * the minimum size of this component.
     * @see #getPreferredSize
	 */
    public Dimension getMinimumSize()
    {
    	return new Dimension(10, 10);
    }

    /**
	 * Returns the recommended dimensions to properly display this component.
     * This is a standard Java AWT method which gets called to determine
     * the recommended size of this component.
     * @see java.awt.Component#minimumSize
	 */
    public Dimension getPreferredSize()
    {
        Dimension dim = getSize();
        Dimension min = getMinimumSize();
        return new Dimension(Math.max(dim.width, min.width), Math.max(dim.height, min.height));
    }

    /**
	 * @deprecated
     * @see getPreferredSize
	 */
    public Dimension preferredSize()
    {
        return getPreferredSize();
    }

	/**
	 * Paints this component using the given graphics context.
     * This is a standard Java AWT method which typically gets called
     * by the AWT to handle painting this component. It paints this component
     * using the given graphics context. The graphics context clipping region
     * is set to the bounding rectangle of this component and its [0,0]
     * coordinate is this component's top-left corner.
     *
     * @param g the graphics context used for painting
     * @see java.awt.Component#repaint
     * @see java.awt.Component#update
	 */
	public synchronized void paint(Graphics g)
	{
		//Make sure cached colors are correct.
		Color curBackground = getBackground();
		if (!symantec.itools.util.GeneralUtils.objectsEqual(curBackground, cachedBackground))
		{
			cachedBackground = curBackground;
			updateBevelColors(curBackground);
		}
	}

    /**
     * Adds a listener for all property change events.
     * @param listener the listener to add
     * @see #removePropertyChangeListener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener)
    {
    	changes.addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener for all property change events.
     * @param listener the listener to remove
     * @see #addPropertyChangeListener
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
    {
    	changes.removePropertyChangeListener(listener);
    }

    /**
     * Adds a listener for all vetoable property change events.
     * @param listener the listener to add
     * @see #removeVetoableChangeListener
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener listener)
    {
		vetos.addVetoableChangeListener(listener);
    }

    /**
     * Removes a listener for all vetoable property change events.
     * @param listener the listener to remove
     * @see #addVetoableChangeListener
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener)
    {
    	vetos.removeVetoableChangeListener(listener);
    }

	/**
	 * Used to calculate the colors for the different bevel styles.
	 * @param c The color of the background to make bevel colors from.
	 * @see java.awt.Component#addNotify
	 * @see java.awt.Component#setBackground
	 */
	protected void updateBevelColors(Color c)
	{
		bevelDarkerColor	= symantec.itools.awt.util.ColorUtils.calculateShadowColor(c);
		bevelLighterColor	= symantec.itools.awt.util.ColorUtils.calculateHilightColor(c);
	}

    /**
     * Width of this shape.
     */
    protected int width;

    /**
     * Height of this shape.
     */
    protected int height;

    /**
     * Border style of this shape.
     */
    protected int style;

    /**
     * Shape is filled if true.
     */
    protected boolean fill;

    /**
     * Color to fill shape with if fill is true.
     */
    protected Color fillColor = Color.black;

    /**
     * The color to use as a hilight when BevelStyle is BEVEL_RAISED or BEVEL_LOWERED.
     */
    protected Color bevelLighterColor;
    /**
     * The color to use as a hilight when BevelStyle is BEVEL_RAISED or BEVEL_LOWERED.
     */
    protected Color bevelDarkerColor;
	/**
     * Cached value of the background color.  Used to determine if calculated colors need to be updated.
	 */
	protected Color cachedBackground = null;

	/**
	 * Handles tracking vetoable change listeners and notifying them of each change
	 * to this component's properties.
	 */
	protected symantec.itools.beans.VetoableChangeSupport vetos = new symantec.itools.beans.VetoableChangeSupport(this);
	/**
	 * Handles tracking non-vetoable change listeners and notifying them of each change
	 * to this component's properties.
	 */
    protected symantec.itools.beans.PropertyChangeSupport changes = new symantec.itools.beans.PropertyChangeSupport(this);
}
