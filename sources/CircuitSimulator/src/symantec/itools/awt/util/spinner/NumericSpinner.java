package symantec.itools.awt.util.spinner;


import java.beans.PropertyVetoException;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;


// 	02/15/97	RKM	Merged in change to set min and max as in the DESC file
//	06/03/97	MSH	Incorporated Java 1.1 changes
//	06/03/97	LAB Polished update to Java 1.1.
//					Changed the package to symantec.itools.awt.util.spinner.
//	10/06/97	LAB Updated constructor to set min, max, tempMin, and tempMax directly
//					rather than call their setters so it would get introspected properly,
//					since the setters only actually set after addNotify has been called.
//  02/05/98    DS  added changes for Spinner re-write
//  09/04/98    MSH Fixed editable support, #62836

/**
 * Creates a text box, containing a list of numbers, with up and down arrows.
 * Use this component to allow your users to move through a set of fixed
 * values or type a valid value in the box.
 * <p>
 * At run time only the selected value is displayed in the text box.
 * <p>
 * @see symantec.itools.awt.Spinner
 * @version 1.1, June 2, 1997
 * @author Symantec
 */
public class NumericSpinner
    extends    Spinner
    implements java.io.Serializable
{
    /**
     * Constructs an empty NumericSpinner.
     */
    public NumericSpinner()
    {
    	try
    	{
	        setIncrement(1);
		}
		catch( PropertyVetoException e ){}
		min		= 0;
		max		= 10;
    }

    /**
     * Sets the minimum value the spinner may have.
     *
     * Overriden here to set the size of the text area.
     *
     * @param i the new minimum value
     * @see Spinner#getMin
     * @see #setMax
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setMin(int i)  throws PropertyVetoException
    {
    	super.setMin(i);

        if(added)
        {
        	int oldValue = textWidth;

	        textWidth = Math.max(Integer.toString(min).length(), Integer.toString(max).length());
		}
    }

    /**
     * Sets the maximum value the spinner may have.
     *
     * Overridden here to set the size of the text area.
     *
     * @param i the new maximum value
     * @see Spinner#getMax
     * @see #setMin
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setMax(int i) throws PropertyVetoException
    {
    	super.setMax(i);

        if(added)
        {
        	int oldValue = textWidth;

	        textWidth = Math.max(Integer.toString(min).length(), Integer.toString(max).length());
		}
    }

    /**
     * Sets the value to increment/decrement the Spinner by.
     * @param int i the increment/decrement value
     * @see #getIncrement
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setIncrement(int i) throws PropertyVetoException
    {
        if (increment != i)
        {
        	Integer oldValue = new Integer( increment );
        	Integer newValue = new Integer( i );

        	getVetos().fireVetoableChange("increment", oldValue, newValue);
            increment = i;
            getChanges().firePropertyChange("increment", oldValue, newValue);
        }
    }

    /**
     * Gets the increment/decrement value.
     * @return the increment/decrement value
     * @see #setIncrement
     */
    public int getIncrement()
    {
        return increment;
    }

    /**
     * Gets the current text from the Spinner.
     * @return the text of the currently selected Spinner value
     */
    public String getCurrentText()
    {
        return Integer.toString(current);
    }

    /**
     * Tells this component that it has been added to a container.
     * This is a standard Java AWT method which gets called by the AWT when
     * this component is added to a container. Typically, it is used to
     * create this component's peer.
     * Here it's used to set maximum text width and note the text of the
     * current selection.
     *
     * @see java.awt.Container#removeNotify
     */
    public void addNotify()
    {
        textWidth = Math.max(Integer.toString(min).length(), Integer.toString(max).length());
        text = Integer.toString(current);
        super.addNotify();
        updateText(false);
    }

    /**
     * Adds a listener for all property change events.
     * @param listener the listener to add
     * @see #removePropertyChangeListener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener)
    {
    	super.addPropertyChangeListener(listener);
    	getChanges().addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener for all property change events.
     * @param listener the listener to remove
     * @see #addPropertyChangeListener
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
    {
    	super.removePropertyChangeListener(listener);
    	getChanges().removePropertyChangeListener(listener);
    }

    /**
     * Adds a listener for all vetoable property change events.
     * @param listener the listener to add
     * @see #removeVetoableChangeListener
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener listener)
    {
     	super.addVetoableChangeListener(listener);
		getVetos().addVetoableChangeListener(listener);
    }

    /**
     * Removes a listener for all vetoable property change events.
     * @param listener the listener to remove
     * @see #addVetoableChangeListener
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener)
    {
    	super.removeVetoableChangeListener(listener);
    	getVetos().removeVetoableChangeListener(listener);
    }

    /**
     * returns boolean if text in field is a valid entry
     * @return true if text is valid
     */
    protected boolean validateText( )
    {
        boolean result = false;

        try
        {
            String  s = textFld.getText();
            Integer i = new Integer( s );

            if ( i.intValue() >= min && (int) i.intValue() <= max )
                result = true;
        }
        catch ( Exception e )
        {
        }
        return result;
    }

	protected symantec.itools.beans.VetoableChangeSupport vetos;
	/**
	 * Handles tracking non-vetoable change listeners and notifying them of each change
	 * to this component's properties.
	 */
    protected symantec.itools.beans.PropertyChangeSupport changes;

	private symantec.itools.beans.PropertyChangeSupport getChanges() {
		return (changes == null ? (changes = new symantec.itools.beans.PropertyChangeSupport(this)) : changes);
	}

	private symantec.itools.beans.VetoableChangeSupport getVetos() {
		return (vetos == null ? (vetos = new symantec.itools.beans.VetoableChangeSupport(this)) : vetos);
	}


}
