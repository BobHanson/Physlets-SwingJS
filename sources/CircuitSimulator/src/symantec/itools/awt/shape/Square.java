package symantec.itools.awt.shape;

//  08/13/97	LAB	Fixed reshape to set the size of the component to the min of the width and
//					height. This addresses Mac Bug #7139.  Changed the version to 1.1.
//  08/30/97	LAB	Fixed rehape problem when only one dimension changed (Addresses Mac Bug #7686)

/**
 * This is a square shape component.
 * @version 1.1, August 13, 1997
 * @author Symantec
 */
public class Square extends Rect
{
    /**
     * Constructs a default Square.
     */
    public Square()
    {
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
    	if(this.width == width)
    		this.width = this.height = height;
    	else if(this.height == height)
    		this.width = this.height = width;
    	else
    		this.width = this.height = Math.min(width, height);

        super.reshape(x, y, this.width, this.height);
    }
}

