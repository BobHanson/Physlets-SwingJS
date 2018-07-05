package symantec.itools.awt.util.spinner;

import java.util.Enumeration;
import java.util.Vector;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.awt.event.FocusEvent;
import java.awt.FontMetrics;


//	06/03/97	LAB	Updated to support Java 1.1.
//					Changed the package to symantec.itools.awt.util.spinner
//  08/24/97    CAR overrode updateText to handle changes to the text in textField
//                  added calls to invalidate/validate in addItem so that textField
//                  will be properly sized after text items are added
//  08/28/97	LAB	Added allowDynamicResizing property.  Made setMax and setMin work correctly.
//					Changed the property name strings in bound and constrained event messages to
//					match the Bean Spec naming conventions.
//  08/29/97    CAR removed calls to setMax from addNotify and addItem as this negates the
//                  value set via the prop list or a preceding call to setMax.  this fixes a bug
//                  re: setting max at design time does not effect runtime max
//  10/01/97	LAB	Updated version to 1.1.  Changed internal workings to solve problems caused by
//					allowing users to specify a max value that does not get changed by the component
//					dynamically when items are added (Addresses Mac Bug #8086).  Set max to 10 by
//					default.
//  10/05/97	LAB	Changed setListItems to erase the vector before adding items so setting
//					the list to a subset of the original list works properly (Addresses Mac
//					Bug #8942).  setListItems now updates the state of the component correctly.
//					Fixed updateText to set the edit box to an empty string if the internal
//					vector is empty.
//  10/06/97	LAB	Fixed several issues with max and min.  (fixed Mac Bug #8086 again...)
//					Changed setMax to set the temp value to the current value when changing
//					the current value.  This fixes a problem at design time where the
//					component would revert to it's default state after a back run.
//  10/12/97	LAB	Changed addItem to set the size of the component to the preferred size if
//					dynamic resizing is on and if a change in size is needed.  Changed
//					updateText to update spinner's internal status correctly. These changes
//					address Mac bug #9200. Changed getCurrentText to return an empty string
//					rather than null if there is no current text.  Now if list items are too
//					long for the size of the textfield, they are truncated with a '...'
//  02/05/98    DS  Re-write of the GUI to allow for resizing

/**
 * Creates a text box, containing a list of items, with up and down arrows.
 * Use this component to allow your users to move through a set of fixed
 * values or type a valid value in the box.
 * <p>
 * At run time only the selected value is displayed in the text box.
 * <p>
 * @see symantec.itools.awt.Spinner
 * @version 1.1, October 1, 1997
 * @author Symantec
 */
public class ListSpinner
    extends Spinner
{
    /**
     * Constructs an empty ListSpinner.
     */
    public ListSpinner()
    {
        list = new Vector();
        try
        {
	        setAllowDynamicResizing(false);
		}
	    catch(PropertyVetoException exc){}
	    max		= 10;
	    updateInternalMax();
        updateButtonStatus();
    }

    /**
     * Adds the given string array to the end of the list.
     * @param items the items to add to the list
     * @see #getListItems
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setListItems(String[] items) throws PropertyVetoException
    {
    	String[] oldValue = getListItems();

    	if(!symantec.itools.util.GeneralUtils.objectsEqual(oldValue, items))
    	{
   			vetos.fireVetoableChange("listItems", oldValue, items);

			list = new Vector();
	        for (int i = 0; i < items.length; ++i)
	        {
	            addItem(items[i]);
	        }

			updateInternalMax();
	        if(!isValidCurrentValue(getCurrent()))
	        	setCurrent(internalMax);
	        updateButtonStatus();
	        updateText(false);

			changes.firePropertyChange("listItems", oldValue, items);
		}
    }

    /**
     * Returns the current list as an array of Strings.
     * @return the current list
     * @see #setListItems
     */
    public String[] getListItems()
    {
        int len = list.size();
        String[] items = new String[len];
        for (int i = 0; i < len; ++i)
        {
            items[i] = (String)list.elementAt(i);
        }
        return items;
    }

    /**
     * Conditionally allows resizing of the edit box if needed when new values are entered.
     * @param f if true the edit box will resize if needed when new values are entered.
     * @see #isAllowDynamicResizing
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setAllowDynamicResizing(boolean f) throws PropertyVetoException
    {
    	if(dynamicResizing != f)
    	{
	    	Boolean oldValue = new Boolean( dynamicResizing );
	    	Boolean newValue = new Boolean( f );

	    	vetos.fireVetoableChange("allowDynamicResizing", oldValue, newValue);

	        dynamicResizing = f;

	        changes.firePropertyChange("allowDynamicResizing", oldValue, newValue);
		}
    }

    /**
     * Gets whether the current value can wrap around from maximum
     * to minimum and from minimum to maximum.
     * @return true if the edit box will resize if needed when new values are entered.
     * @see #setAllowDynamicResizing
     */
    public boolean isAllowDynamicResizing()
    {
        return dynamicResizing;
    }

    /**
     * Gets the currently selected string from the list.
     * @return the string currently visible in the Spinner
     */
    public String getCurrentText()
    {
        return list.size() > 0 ? (String)list.elementAt(current) : "";
    }

    /**
     * Sets the maximum value the spinner may have.
     * @param i the new maximum value
     * @see symantec.itools.awt.util.spinner.Spinner#getMax
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setMax(int i) throws PropertyVetoException
    {
    	if(max != i)
    	{
	    	Integer oldValue = new Integer( max );
	    	Integer newValue = new Integer( i );

	    	vetos.fireVetoableChange("max", oldValue, newValue);

	        max = i;

	        if(getCurrent() > max)
	    		setCurrent(max);
	    	else
	    	{
				updateInternalMax();
		        updateButtonStatus();
			}

	        changes.firePropertyChange("max", oldValue, newValue);
		}
    }

    /**
     * Tells this component that it has been added to a container.
     * This is a standard Java AWT method which gets called by the AWT when
     * this component is added to a container. Typically, it is used to
     * create this component's peer.
     * Here it's used to get the length of the largest string in the list.
     *
     * @see java.awt.Container#removeNotify
     */
    public void addNotify()
    {
        super.addNotify();

		//Hook up listeners
		if (focus == null)
		{
			focus = new Focus();
			textFld.addFocusListener(focus);
		}

        if (list.size() > 0)
        {
	    	int oldTextWidth = textWidth;

            for (Enumeration e = list.elements(); e.hasMoreElements();)
            {
                textWidth = Math.max(textWidth, ((String)e.nextElement()).length());
            }

            text = (String)list.elementAt(current);
        }
    }

	/**
	 * Tells this component that it is being removed from a container.
	 * This is a standard Java AWT method which gets called by the AWT when
	 * this component is removed from a container. Typically, it is used to
	 * destroy the peers of this component and all its subcomponents.
	 *
	 * It has been overridden here to unhook event listeners.
	 *
	 * @see #addNotify
	 */
	public synchronized void removeNotify()
	{
		//Unhook listeners
		if (focus != null)
		{
			textFld.removeFocusListener(focus);
			focus = null;
		}

		super.removeNotify();
	}


    /**
     * Adds a string to the end of the list.
     * @param s the string to be appended to the list
     * @see #setListItems
     */
    public void addItem(String s)
    {
    	int oldTextWidth = textWidth;

        list.addElement(s);
        textWidth = Math.max(textWidth, s.length());
    }

    /**
     * Adds a listener for all property change events.
     * @param listener the listener to add
     * @see #removePropertyChangeListener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener)
    {
    	super.addPropertyChangeListener(listener);
    	changes.addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener for all property change events.
     * @param listener the listener to remove
     * @see #addPropertyChangeListener
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
    {
    	super.removePropertyChangeListener(listener);
    	changes.removePropertyChangeListener(listener);
    }

    /**
     * Adds a listener for all vetoable property change events.
     * @param listener the listener to add
     * @see #removeVetoableChangeListener
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener listener)
    {
		super.addVetoableChangeListener(listener);
		vetos.addVetoableChangeListener(listener);
    }

    /**
     * Removes a listener for all vetoable property change events.
     * @param listener the listener to remove
     * @see #addVetoableChangeListener
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener)
    {
    	super.removeVetoableChangeListener(listener);
    	vetos.removeVetoableChangeListener(listener);
    }

    /**
     * Adds a listener for the max property changes.
     * @param listener the listener to add.
     * @see #removeMaxListener(java.beans.PropertyChangeListener)
     */
    public synchronized void addMaxListener(PropertyChangeListener listener)
    {
    	changes.addPropertyChangeListener("max", listener);
    }

    /**
     * Removes a listener for the max property changes.
     * @param listener the listener to remove.
     * @see #addMaxListener(java.beans.PropertyChangeListener)
     */
    public synchronized void removeMaxListener(PropertyChangeListener listener)
    {
    	changes.removePropertyChangeListener("max", listener);
    }

    /**
     * Adds a vetoable listener for the max property changes.
     * @param listener the listener to add.
     * @see #removeMaxListener(java.beans.VetoableChangeListener)
     */
    public synchronized void addMaxListener(VetoableChangeListener listener)
    {
    	vetos.addVetoableChangeListener("max", listener);
    }

    /**
     * Removes a vetoable listener for the max property changes.
     * @param listener the listener to remove.
     * @see #addMaxListener(java.beans.VetoableChangeListener)
     */
    public synchronized void removeMaxListener(VetoableChangeListener listener)
    {
    	vetos.removeVetoableChangeListener("max", listener);
    }

	/**
	 * This is the Focus Event handling innerclass.
	 */
	class Focus implements java.awt.event.FocusListener
	{
		/**
		 * Handles Focus Gained events
		 * @param e the FocusEvent
		 */
	    public void focusGained(FocusEvent e)
		{
			if(textFld.isEditable())
			{
				isPossibleEdit = true;
				oldText = textFld.getText();
				textFld.setText(getCurrentText());
			}
		}

		/**
		 * Handles Focus Lost events
		 * @param e the FocusEvent
		 */
	    public void focusLost(FocusEvent e)
		{
			updateText(false);
			isPossibleEdit = false;
		}
	}

	/**
	 * Gets the current contets and truncates the string appropriately.
	 * This assumes it is geting called from updateText
	 * @param contents the string to truncate as if it were to be placed in
	 * the textField
	 * @return The truncated string.  This may not be truncated if it was not needed.
	 */
	protected String truncateContents(String contents)
	{
	    if(added)
	    {
    		FontMetrics fm;
	        fm = getFontMetrics(textFld.getFont());
    		int		stringWidth			= fm.stringWidth(contents);

	    	if(stringWidth <= 0)
		    	return "";

    		int		charWidth			= fm.stringWidth("W");
	    	int		textFieldPadWidth	= textFld.getPreferredSize().width - charWidth;
		    int		maxTextWidth		= textFld.getSize().width - textFieldPadWidth;

    		if(maxTextWidth < 0)
    			maxTextWidth = 0;

    		String testString = contents;
    		int stringIndex = testString.length();
    		while(stringIndex > 0 && fm.stringWidth(testString) > maxTextWidth - charWidth)
    		{
    			testString = contents.substring(0, stringIndex);
    			--stringIndex;
    		}
    		if(stringIndex != contents.length())
    		{
    			testString += "...";
    			while(stringIndex > 0 && fm.stringWidth(testString) > maxTextWidth - charWidth)
    			{
    				testString = contents.substring(0, stringIndex) + "...";
    				--stringIndex;
    			}
    		}

    		return (testString);
    	}

    	return ("");
	}

    /**
     * Increments the spinner's value and handles wrapping as needed.
     * @see #scrollDown
     * @see symantec.itools.awt.util.spinner.Spinner#increment
     */
    protected void scrollUp()
    {
    	try
    	{
    		setCurrent(current + increment);
    	}
    	catch (PropertyVetoException exc)
    	{
            if (isWrappable())
            {
    			try { setCurrent(min); } catch (PropertyVetoException exc1) {}
            }
            else
            {
    			try { setCurrent(internalMax); } catch (PropertyVetoException exc1) {}
            }
    	}

        updateText(true);
    }

    /**
     * Decrements the spinner's value and handles wrapping as needed.
     * @see #scrollUp
     * @see symantec.itools.awt.util.spinner.Spinner#increment
     */
    protected void scrollDown()
    {
    	try
    	{
    		setCurrent(current - increment);
    	}
    	catch (PropertyVetoException exc)
    	{
            if (isWrappable())
            {
    			try { setCurrent(internalMax); } catch (PropertyVetoException exc1) {}
            }
            else
            {
    			try { setCurrent(min); } catch (PropertyVetoException exc1) {}
            }
    	}

        updateText(false);
    }

	protected void updateText(boolean force)
	{
		String		currentText			= getCurrentText();
		String		truncContents		= truncateContents(currentText);
		String		textFieldContents	= textFld.getText();
		boolean	isListEmpty			= (list == null || list.size() <= 0);

		//If the text has changed, put the new text into the text field
		if (!textFieldContents.equals(truncContents))
		{
			if(isPossibleEdit && !list.contains(textFieldContents) && !textFieldContents.equals(""))
			{
				editAdding = true;
				addItem(textFieldContents);
				updateInternalMax();
				updateButtonStatus();
				truncContents	= truncateContents(getCurrentText());
				isListEmpty		= false;
			}
			else
				editAdding = false;

			if(!isListEmpty)
				textFld.setText(truncContents);
		}

		if(isListEmpty)
			textFld.setText("");
	}

    /**
     * Is the given value valid for the Current property .
     * @param i the given value
     * @return true if the given value is acceptable, false if not.
     */
    protected boolean isValidCurrentValue(int i)
    {
        return (i >= min && i <= internalMax);
    }

    /**
     * Is the given value valid for the Max property .
     * @param i the given value
     * @return true if the given value is acceptable, false if not.
     */
    protected boolean isValidMaxValue(int i)
    {
        return (i >= min && i >= 0);
    }

    /**
     * Is the given value valid for the Min property .
     * @param i the given value
     * @return true if the given value is acceptable, false if not.
     */
    protected boolean isValidMinValue(int i)
    {
        return (i <= internalMax && i >= 0);
    }

	/**
	 * Keeps track of the maximum value the list is allowed to go to.
	 */
    protected void updateInternalMax()
    {
    	internalMax = 0;
    	int listSize = list.size();
    	if(max >= listSize)
    	{
    		if(listSize > 0)
    		{
    			internalMax = listSize - 1;
    		}
    	}
    	else
    	{
    		internalMax = max;
    	}
    }

	/**
	 * Handles enabling or disabling the spinner buttons as needed.
	 */
	protected void updateButtonStatus()
	{
		if(buttons != null)
        {
		    if(isWrappable())
		    {
				buttons.setUpButtonEnabled(true);
				buttons.setDownButtonEnabled(true);
			}
		    else
			{
				if(current == internalMax && current == min)
				{
				    buttons.setUpButtonEnabled(false);
				    buttons.setDownButtonEnabled(false);
				}
			    else if(current == internalMax)
			    {
				    buttons.setUpButtonEnabled(false);
				    buttons.setDownButtonEnabled(true);
			    }
			    else if(current == min)
			    {
				    buttons.setUpButtonEnabled(true);
				    buttons.setDownButtonEnabled(false);
			    }
			    else
			    {
					buttons.setUpButtonEnabled(true);
					buttons.setDownButtonEnabled(true);
				}
			}
		}
	}

    /**
     * The list of strings that get displayed in the spinner.
     */
    protected Vector list;

    /**
     * Flag to keep track of allowance of dynamic resizing of the edit box as new values are entered.
     */
	protected boolean dynamicResizing;

	/**
	 * The maximum value the list is allowed to go to.
	 */
	protected int internalMax;

	/**
	 * Text value at the time focus was gained.
	 */
	protected String oldText = "";
	/**
	 * Is possible to edit the component. True when it has the focus.
	 */
	protected boolean isPossibleEdit = false;

	private boolean editAdding = false;

    private Focus focus = null;

	private symantec.itools.beans.VetoableChangeSupport vetos = new symantec.itools.beans.VetoableChangeSupport(this);
    private symantec.itools.beans.PropertyChangeSupport changes = new symantec.itools.beans.PropertyChangeSupport(this);
}
