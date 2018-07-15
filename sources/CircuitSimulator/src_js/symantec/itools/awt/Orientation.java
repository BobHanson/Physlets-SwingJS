package symantec.itools.awt;

import java.beans.PropertyVetoException;

//	06/03/97	MSH Updated to Java 1.1
//	06/03/97	LAB Made the interface public, for the spinners.

/**
 * Orientation is an interface for components that can be oriented either
 * vertically or horizontally.
 */
public interface Orientation
{
    /**
     * Constant specifying vertical orientation.
     */
    public final int ORIENTATION_VERTICAL   = 0;
    /**
     * Constant specifying horizontal orientation.
     */
    public final int ORIENTATION_HORIZONTAL = 1;

    /**
     * Sets the component's new orientation.
     * @param o desired orientation, either ORIENTATION_VERTICAL or
     * ORIENTATION_HORIZONTAL
     * @see #getOrientation
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setOrientation(int o) throws PropertyVetoException;
    /**
     * Gets the component's current orientation.
     * @return the current orientation, either ORIENTATION_VERTICAL or
     * ORIENTATION_HORIZONTAL
     * @see #setOrientation
     */
    public int getOrientation();
}
