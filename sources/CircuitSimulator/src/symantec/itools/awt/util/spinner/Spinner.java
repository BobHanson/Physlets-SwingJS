package symantec.itools.awt.util.spinner;


import a2s.*;

import java.awt.AWTEventMulticaster;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.ResourceBundle;
import symantec.itools.awt.Orientation;


// 	01/29/97	TWB	Integrated changes from Macintosh
//	01/30/97	RKM	Changed updateText to check if the text has changed, to avoid flicker
//					Also changed paint to first call updateText and removed ifdef MAC
//	06/02/97	MSH Updated to Java 1.1
//	06/03/97	LAB Polished update to Java 1.1.
//					Changed the package to symantec.itools.awt.util.spinner.
//	07/18/97	LAB Added add/removeNotify to handle event listener registration.
//					Made sourceActionEvent protected instead of public.
//  07/30/97    CAR Inner adaptor class implements java.io.Serializable
//	09/02/97	RKM	Better code in updateText()
//					Added getEntryFieldText() as per request from newsgroup
//  08/24/87    CAR hooked up actionlistener to textfield to listen for changes to the text
//                  call updateText() from actionPerformed when textField's text changes
//                  call invalidate/validate in setOrientation
//  08/28/97	LAB	Made the spinner buttons disable and enable according to the value.  Implemented
//					getPreferredSize and getMinimumSize (Addresses Mac Bug #7518).  Changed the
//					property name strings in bound and constrained event messages to match the Bean
//					Spec naming conventions.
//  09/11/97	LAB	Added listener registration code to setOrientation to remove and add our
//					listener to the newly created horizontal or vertical spin button panel.
//					Fixes a problem when you switched orientations and the spinner no longer
//					spun (Addresses Mac Bug#7822).
//  10/05/97	LAB	Added mechanism for internally constrained properties to be validated
//					after the component is added to the form to avoid code-gen order specific
//					dependencies.
//  10/06/97	LAB	Changed addNotify to call it's super after listeners are hooked up so
//					verifyContstrainedPropertyValues will be called after the listeners
//					are hooked up.  Changed order of calls in verifyContstrainedPropertyValues
//					method to reflect dependancy correctly.  Changed setMax, setMin, and
//					setCurrent to set the temp values to the current values when changing the
//					current values.  Changed getMax, getMin, and getCurrent to return the temp
//					values if the component is not added. This fixes a problem at design time
//					where the component would revert to it's default state after a back run.
//  10/12/97	LAB	Changed doLayout so if the component is sized smaller than the preferred
//					size for the textfield and buttons, the textfield is shortened (Addresses
//					Mac Bug #9051).  When the textField fires an action event, the focus is
//					now requested by the Spinner (this takes the focus away from the textField).
//					Rearranged addNotify to correctly add the action listener to the textField
//					(Addresses Mac Bug #9200).
//  02/05/98    DS  Re-write of GUI
//  09/04/98    MSH Edit box now filters user input, #62836
//  09/09/98    MSH Spinner can no longer be edited when editable is set to false, #56076
//  09/25/98    MSH Edit box no longer obliterates value if return is hit, #64895

/**
 * This abstract class is used to create spinners. A spinner is a component
 * with two small direction buttons that lets the user scroll a list of
 * predetermined values to select one, or possibly enter a new legal value.
 * @see symantec.itools.awt.util.spinner.ListSpinner
 * @see symantec.itools.awt.util.spinner.NumericSpinner
 * @version 1.1, June 2, 1997
 * @author Symantec
 */
public abstract class Spinner
    extends    Panel
    implements Orientation,
               Serializable
{
	class Action
	    implements ActionListener,
	               java.io.Serializable
	{
		public void actionPerformed(ActionEvent e)
		{
		    if(e.getSource() instanceof TextField)
		    {
       			if(((TextField)e.getSource()) == textFld)
   	    		{
   	    		    if ( validateText() )
    	                current = (new Integer( textFld.getText() )).intValue();

    	            //Take the focus away from the edit box
    	            updateText(false);
    				requestFocus();
   	    		    return;
		    	}
		    }

			String cmdStr = "";
			String actionCommand = e.getActionCommand();

			if(actionCommand.equals("Increment"))
			{
				scrollUp();
				cmdStr = "ScrollUp";
			    sourceActionEvent(cmdStr);
			}
			else if(actionCommand.equals("Decrement"))
			{
				scrollDown();
				cmdStr = "ScrollDown";
			    sourceActionEvent(cmdStr);
			}
		}
	}

	/**
	 * This is the PropertyChangeEvent handling inner class for the constrained Current property.
	 * Handles vetoing Current values that are not valid.
	 */
	class CurrentVeto
	    implements VetoableChangeListener,
	               java.io.Serializable
	{
	    /**
	     * This method gets called when an attempt to change the constrained Current property is made.
	     * Ensures the given Current value is valid for this component.
	     *
	     * @param     e a <code>PropertyChangeEvent</code> object describing the
	     *   	      event source and the property that has changed.
	     * @exception PropertyVetoException if the recipient wishes the property
	     *              change to be rolled back.
	     */
	    public void vetoableChange(PropertyChangeEvent e)
	        throws PropertyVetoException
	    {
	    	int i = ((Integer)e.getNewValue()).intValue();

	        if(!isValidCurrentValue(i))
	        {
	            throw new PropertyVetoException(errors.getString("InvalidCurrentValue") + i, e);
	        }
	    }
	}

	/**
	 * This is the PropertyChangeEvent handling inner class for the constrained Max property.
	 * Handles vetoing Max values that are not valid.
	 */
	class MaxVeto
	    implements VetoableChangeListener,
	               Serializable
	{
	    /**
	     * This method gets called when an attempt to change the constrained Current property is made.
	     * Ensures the given Max value is valid for this component.
	     *
	     * @param     e a <code>PropertyChangeEvent</code> object describing the
	     *   	      event source and the property that has changed.
	     * @exception PropertyVetoException if the recipient wishes the property
	     *              change to be rolled back.
	     */
	    public void vetoableChange(PropertyChangeEvent e)
	        throws PropertyVetoException
	    {
	    	int i = ((Integer)e.getNewValue()).intValue();

	        if(!isValidMaxValue(i))
	        {
	            throw new PropertyVetoException(errors.getString("InvalidMaxValue") + i, e);
	        }
	    }
	}

	/**
	 * This is the PropertyChangeEvent handling inner class for the constrained Min property.
	 * Handles vetoing Min values that are not valid.
	 */
	class MinVeto
	    implements VetoableChangeListener,
	               java.io.Serializable
	{
	    /**
	     * This method gets called when an attempt to change the constrained Current property is made.
	     * Ensures the given Min value is valid for this component.
	     *
	     * @param     e a <code>PropertyChangeEvent</code> object describing the
	     *   	      event source and the property that has changed.
	     * @exception PropertyVetoException if the recipient wishes the property
	     *              change to be rolled back.
	     */
	    public void vetoableChange(PropertyChangeEvent e)
	        throws PropertyVetoException
	    {
	    	int i = ((Integer)e.getNewValue()).intValue();

	        if(!isValidMinValue(i))
	        {
	            throw new PropertyVetoException(errors.getString("InvalidMinValue") + i, e);
	        }
	    }
	}

    /**
     * The default spinner orientation.
     */
    protected static int ORIENTATION_DEFAULT = ORIENTATION_VERTICAL;

    /**
     * Reserved.
     */
    protected String                text;
    /**
     * Reserved.
     */
    protected int                   textWidth;
    /**
     * Reserved.
     * @see #getOrientation
     * @see #setOrientation
     */
    protected int                   orientation;
    /**
     * Whether the value can wrap from max to min, and from min to max.
     * @see #isWrappable
     * @see #setWrappable
     */
    protected boolean               wrappable;
    /**
     * Whether the Spinner's TextField is editable.
     * @see #isEditable
     * @see #setEditable
     */
    protected boolean               editable;
    /**
     * The minimum value the spinner may have.
     * @see #getMin
     * @see #setMin
     */
    protected int                   min;
    /**
     * The maximum value the spinner may have.
     * @see #getMax
     * @see #setMax
     */
    protected int                   max;
    /**
     * The current spinner value.
     * @see #getCurrent
     * @see #setCurrent
     */
    protected int                   current;
    /**
     * The amount the spinner's value is changed when scrolling step is taken.
     * @see #scrollUp
     * @see #scrollDown
     */
    protected int                   increment;
    /**
     * Tracks listeners for all vetoable property change events.
     * @see #addVetoableChangeListener
     * @see #removeVetoableChangeListener
     */
	protected symantec.itools.beans.VetoableChangeSupport vetos;
    /**
     * Tracks listeners for all property change events.
     * @see #addPropertyChangeListener
     * @see #removePropertyChangeListener
     */
    protected symantec.itools.beans.PropertyChangeSupport changes;
    /**
     * Error reporting strings.
     */
    protected ResourceBundle        errors;
  	/**
     * The action listener which will receive action events
     * from this component.
     * @see #addActionListener
     * @see #removeActionListener
     */
    protected ActionListener        actionListener;
	/**
	 * A PropertyChangeEvent listener that constrains the "current" property as needed.
	 * @see #current
	 * @see #maxVeto
	 * @see #minVeto
	 */
    protected CurrentVeto		    currentVeto;
	/**
	 * A PropertyChangeEvent listener that constrains the "max" property as needed.
	 * @see #max
	 * @see #currentVeto
	 * @see #minVeto
	 */
    protected MaxVeto			    maxVeto;
	/**
	 * A PropertyChangeEvent listener that constrains the "min" property as needed.
	 * @see #min
	 * @see #currentVeto
	 * @see #maxVeto
	 */
    protected MinVeto			    minVeto;
    /**
     * An ActionEvent listener that handles typical spinner actions.
     */
    protected Action				action;
    /**
	 * Tracks whether this component has been added to a container.
     */
    protected boolean               added;

    {
        min       = 0;
        max       = 0;
        increment = 1;
		current   = 0;
	    textWidth = 0;
	    vetos     = new symantec.itools.beans.VetoableChangeSupport(this);
        changes   = new symantec.itools.beans.PropertyChangeSupport(this);
        added     = false;
    }

    /**
     * Constructs a default spinner. The current value is 0,
     * the minimum value is 0, the maximum value is 1, the increment is 1,
     * it is not wrappable, and has a vertical orientation.
     */
    public Spinner()
    {
        //{{INIT_CONTROLS
		GridBagLayout gridBagLayout;
		gridBagLayout = new GridBagLayout();
		super.setLayout(gridBagLayout);
		setSize(61,20);
		textFld = new TextField();
		textFld.setBounds(0,0,100,20);
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)getLayout()).setConstraints(textFld, gbc);
		add(textFld);
		buttons = new symantec.itools.awt.util.spinner.SpinButtonPanel();
		buttons.setLayout(new GridLayout(2,1,0,0));
		buttons.setBounds(100,0,3,20);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.05;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)getLayout()).setConstraints(buttons, gbc);
		add(buttons);
		//}}
        textFld.setEditable(false);

        try
        {
	        setWrappable(false);
	        setOrientation(ORIENTATION_DEFAULT);
	    }
	    catch(PropertyVetoException e)
	    {
	    }
    }

	//{{DECLARE_CONTROLS
	TextField textFld;
	symantec.itools.awt.util.spinner.SpinButtonPanel buttons;
	//}}

	public void setEnabled( boolean f )
    {
        buttons.setEnabled( f );
        textFld.setEnabled( f );
    }

    /**
     * Conditionally enables editing of the Spinner's TextField.
     * @param f true = allow editing;
     *          false = disallow editing
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setEditable(boolean f) throws PropertyVetoException
    {
    	if(editable != f)
    	{
	    	Boolean oldValue;
	    	Boolean newValue;

	    	oldValue = new Boolean(editable);
	    	newValue = new Boolean(f);
	    	vetos.fireVetoableChange("editable", oldValue, newValue);
	        editable = f;
            textFld.setEditable(editable);

            changes.firePropertyChange("editable", oldValue, newValue);
		}
    }

    /**
     * @deprecated
     * @see #isEditable
     */
    public boolean getEditable()
    {
        return editable;
    }

    /**
     * Returns whether the Spinner's TextField is editable.
     * @return  <code>true</code> if the TextField can be edited,
     *          <code>false</code> otherwise
     * @see #setEditable
     */
    public boolean isEditable()
    {
        return editable;
    }

    /**
     * Sets whether the spinner buttons are laid out one above the other or
     * one beside the other.
     * @param o the new orientation, one of: ORIENTATION_VERTICAL, or ORIENTATION_HORIZONTAL
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     * @see #getOrientation
     * @see symantec.itools.awt.Orientation#ORIENTATION_VERTICAL
     * @see symantec.itools.awt.Orientation#ORIENTATION_HORIZONTAL
    */
	public void setOrientation(int o)
	    throws PropertyVetoException
	{
	    if(orientation != o)
	    {
    		Integer oldValue;
	    	Integer newValue;

    		oldValue = new Integer(orientation);
	    	newValue = new Integer(o);
		    vetos.fireVetoableChange("orientation", oldValue, newValue);
		    orientation = o;
    	    buttons.setOrientation(orientation);
    		changes.firePropertyChange("orientation", oldValue, newValue);
		}
	}

    /**
     * Gets whether the spinner buttons are laid out one above the other or
     * one beside the other.
     * @return the orientation, one of: ORIENTATION_VERTICAL, or ORIENTATION_HORIZONTAL
     * @see #setOrientation
     * @see symantec.itools.awt.Orientation#ORIENTATION_VERTICAL
     * @see symantec.itools.awt.Orientation#ORIENTATION_HORIZONTAL
    */
	public int getOrientation()
	{
	    return (orientation);
	}

    /**
     * Sets whether the value can wrap from max to min, and from min to max.
     * @param f <code>true</code> to allow the value to wrap
     * @see #isWrappable
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
	public void setWrappable(boolean f)
	    throws PropertyVetoException
	{
    	if(wrappable != f)
    	{
	    	Boolean oldValue;
	    	Boolean newValue;

	    	oldValue = new Boolean(wrappable);
	    	newValue = new Boolean(f);
	    	vetos.fireVetoableChange("wrappable", oldValue, newValue);
	        wrappable = f;
	        updateButtonStatus();
	        changes.firePropertyChange("wrappable", oldValue, newValue);
		}
	}

    /**
     * @deprecated
     * @see #isWrappable
     */
	public boolean getWrappable()
	{
	    return (wrappable);
	}

    /**
     * Gets whether the value can wrap from max to min, and from min to max.
     * @return <code>true</code> if the value is allowed to wrap
     * @see #setWrappable
     */
	public boolean isWrappable()
	{
	    return (wrappable);
	}

    public Dimension getPreferredSize()
    {
        Dimension   textFldDim;
        Dimension   btnsDim;

        textFldDim  = textFld.getPreferredSize();
        btnsDim     = buttons.getPreferredSize();

        return (new Dimension(textFldDim.width + btnsDim.width, Math.max(textFldDim.height, btnsDim.height)));
    }

    public Dimension getMinimumSize()
    {
        return (getPreferredSize());
    }

    /**
     * Sets the minimum value the spinner may have.
     * @param i the new minimum value
     * @see #getMin
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setMin(int i)
        throws PropertyVetoException
    {
	    if(min != i)
        {
			Integer oldValue;
			Integer newValue;

			oldValue = new Integer(min);
			newValue = new Integer(i);
		    vetos.fireVetoableChange("min", oldValue, newValue);
		    min = i;

		    if(getCurrent() < min)
		    {
			   	setCurrent(min);
			}
			else
			{
			    updateButtonStatus();
            }

			changes.firePropertyChange("min", oldValue, newValue);
	    }
    }

    /**
     * Gets the current minimum value the spinner may have.
     * @return the current minimum value
     * @see #setMin
     */
    public int getMin()
    {
        return (min);
    }

    /**
     * Sets the maximum value the spinner may have.
     * @param i the new maximum value
     * @see #getMax
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setMax(int i) throws PropertyVetoException
    {
	    if(max != i)
	    {
	    	Integer oldValue;
	    	Integer newValue;

	    	oldValue = new Integer(max);
	    	newValue = new Integer(i);
	    	vetos.fireVetoableChange("max", oldValue, newValue);
	        max = i;

	        if(getCurrent() > max)
	        {
	    		setCurrent(max);
	    	}
	    	else
	    	{
		        updateButtonStatus();
            }

	        changes.firePropertyChange("max", oldValue, newValue);
		}
    }

    /**
     * Gets the current maximum value the spinner may have.
     * @return the current maximum value
     * @see #setMax
     */
    public int getMax()
    {
        return (max);
    }

    /**
     * Sets the value of the spinner.
     * @param i the new value
     * @see #getCurrent
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setCurrent(int i) throws PropertyVetoException
    {
	    if(current != i)
	    {
		   	Integer oldValue;
	    	Integer newValue;

		   	oldValue = new Integer(current);
	    	newValue = new Integer(i);
	    	vetos.fireVetoableChange("current", oldValue, newValue);
	        current = i;
	        updateText(false);
	        updateButtonStatus();
	        changes.firePropertyChange("current", oldValue, newValue);
		}
    }

    /**
     * Gets the current value of the spinner.
     * @return the current spinner value
     * @see #setCurrent
     */
    public int getCurrent()
    {
        if ( validateText() )
        {
    	    Integer i = new Integer( textFld.getText() );
    	    current =  i.intValue();
    	}
	    return (current);
    }

    /**
     * Sets whether the spinner buttons will continually post notify events
     * while pressed.
     * @param f true = send messages; false = do not send messages
     * @see #isNotifyWhilePressed
     * @see #setDelay
     * @see #getDelay
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setNotifyWhilePressed(boolean f)
        throws PropertyVetoException
    {
        if(f != buttons.getNotifyWhilePressed())
        {
        	Boolean oldValue;
        	Boolean newValue;

    	    oldValue = new Boolean(getNotifyWhilePressed());
        	newValue = new Boolean(f);
        	vetos.fireVetoableChange("notifyWhilePressed", oldValue, newValue);
            buttons.setNotifyWhilePressed(f);
            changes.firePropertyChange("notifyWhilePressed", oldValue, newValue);
        }
    }

    /**
     * Gets the current notifyWhilePressed status.
     * @return true if notify events posted while pressed, false otherwise
     * @see #setNotifyWhilePressed
     * @see #setDelay
     * @see #getDelay
     */
    public boolean isNotifyWhilePressed()
    {
        return (buttons.isNotifyWhilePressed());
    }

    /**
     * @deprecated
     * @see #isNotifyWhilePressed
     */
    public boolean getNotifyWhilePressed()
    {
        return isNotifyWhilePressed();
    }

    /**
     * Sets the notification event delay of the spinner buttons in milliseconds.
     * @param d the delay between notification events in milliseconds
     * @see #setNotifyWhilePressed
     * @see #getDelay
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setDelay(int d) throws PropertyVetoException
    {
        if(d != buttons.getDelay())
        {
    	    Integer oldValue;
        	Integer newValue;

    	    oldValue = new Integer(buttons.getDelay());
        	newValue = new Integer(d);

        	vetos.fireVetoableChange("delay", oldValue, newValue);
            buttons.setDelay(d);
            changes.firePropertyChange("delay", oldValue, newValue);
        }
    }

    /**
     * Returns the current delay between notification events of the spinner
     * buttons in milliseconds.
     * @see #setNotifyWhilePressed
     * @see #setDelay
     */
    public int getDelay()
    {
        return buttons.getDelay();
    }

    /**
     * Returns the text that is in the entry TextField.
     */
	public String getEntryFieldText()
	{
        return (textFld.getText());
	}

	/**
	 * Takes no action.
	 * This is a standard Java AWT method which gets called to specify
	 * which layout manager should be used to layout the components in
	 * standard containers.
	 *
	 * Since layout managers CANNOT BE USED with this container the standard
	 * setLayout has been OVERRIDDEN for this container and does nothing.
	 *
	 * @param lm the layout manager to use to layout this container's components
	 * (IGNORED)
	 * @see java.awt.Container#getLayout
	 **/
    public void setLayout(LayoutManager lm)
    {
    }

	/**
	 * Tells this component that it has been added to a container.
	 * This is a standard Java AWT method which gets called by the AWT when
	 * this component is added to a container. Typically, it is used to
	 * create this component's peer.
	 *
	 * It has been overridden here to hook-up event listeners.
	 * It is also used to setup the component, creating the TextField as needed.
	 *
	 * @see #removeNotify
	 */
	public synchronized void addNotify()
	{
		super.addNotify();
		added = true;
        errors = ResourceBundle.getBundle("symantec.itools.resources.ErrorsBundle");

		//Hook up listeners
		if(action == null)
		{
			action = new Action();
			buttons.addActionListener(action);
			textFld.addActionListener(action);
		}

		if(currentVeto == null)
		{
			currentVeto = new CurrentVeto();
			addCurrentListener(currentVeto);
		}

		if(maxVeto == null)
		{
			maxVeto = new MaxVeto();
			addMaxListener(maxVeto);
		}

		if(minVeto == null)
		{
			minVeto = new MinVeto();
			addMinListener(minVeto);
		}

        updateText(true);
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
		if(action != null)
		{
			textFld.removeActionListener(action);
			buttons.removeActionListener(action);
			action = null;
		}

		if(currentVeto != null)
		{
			removeCurrentListener(currentVeto);
			currentVeto = null;
		}

		if(maxVeto != null)
		{
			removeMaxListener(maxVeto);
			maxVeto = null;
		}

		if(minVeto != null)
		{
			removeMinListener(minVeto);
			minVeto = null;
		}

		super.removeNotify();
	}

  	/**
     * Adds the specified action listener to receive action events
     * from this component.
     * @param l the action listener
     * @see #removeActionListener
     */
	public synchronized void addActionListener(ActionListener l)
	{
		actionListener = AWTEventMulticaster.add(actionListener, l);
	}

    /**
     * Removes the specified action listener so it no longer receives
     * action events from this component.
     * @param l the action listener
     * @see #addActionListener
     */
	public synchronized void removeActionListener(ActionListener l)
	{
		actionListener = AWTEventMulticaster.remove(actionListener, l);
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
     * Adds a listener for the current property changes.
     * @param listener the listener to add.
     * @see #removeCurrentListener(java.beans.PropertyChangeListener)
     */
    public synchronized void addCurrentListener(PropertyChangeListener listener)
    {
    	changes.addPropertyChangeListener("current", listener);
    }

    /**
     * Removes a listener for the current property changes.
     * @param listener the listener to remove.
     * @see #addCurrentListener(java.beans.PropertyChangeListener)
     */
    public synchronized void removeCurrentListener(PropertyChangeListener listener)
    {
    	changes.removePropertyChangeListener("current", listener);
    }

    /**
     * Adds a vetoable listener for the current property changes.
     * @param listener the listener to add.
     * @see #removeCurrentListener(java.beans.VetoableChangeListener)
     */
    public synchronized void addCurrentListener(VetoableChangeListener listener)
    {
    	vetos.addVetoableChangeListener("current", listener);
    }

    /**
     * Removes a vetoable listener for the current property changes.
     * @param listener the listener to remove.
     * @see #addCurrentListener(java.beans.VetoableChangeListener)
     */
    public synchronized void removeCurrentListener(VetoableChangeListener listener)
    {
    	vetos.removeVetoableChangeListener("current", listener);
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
     * Adds a listener for the min property changes.
     * @param listener the listener to add.
     * @see #removeMinListener(java.beans.PropertyChangeListener)
     */
    public synchronized void addMinListener(PropertyChangeListener listener)
    {
    	changes.addPropertyChangeListener("min", listener);
    }

    /**
     * Removes a listener for the min property changes.
     * @param listener the listener to remove.
     * @see #addMinListener(java.beans.PropertyChangeListener)
     */
    public synchronized void removeMinListener(PropertyChangeListener listener)
    {
    	changes.removePropertyChangeListener("min", listener);
    }

    /**
     * Adds a vetoable listener for the min property changes.
     * @param listener the listener to add.
     * @see #removeMinListener(java.beans.VetoableChangeListener)
     */
    public synchronized void addMinListener(VetoableChangeListener listener)
    {
    	vetos.addVetoableChangeListener("min", listener);
    }

    /**
     * Removes a vetoable listener for the min property changes.
     * @param listener the listener to remove.
     * @see #addMinListener(java.beans.VetoableChangeListener)
     */
    public synchronized void removeMinListener(VetoableChangeListener listener)
    {
    	vetos.removeVetoableChangeListener("min", listener);
    }

    /**
     * Is the given value valid for the Current property .
     * @param i the given value
     * @return true if the given value is acceptable, false if not.
     */
    protected boolean isValidCurrentValue(int i)
    {
        if (i > max || i < min)
            return false;
        else
        	return true;
    }

    /**
     * Is the given value valid for the Max property .
     * @param i the given value
     * @return true if the given value is acceptable, false if not.
     */
    protected boolean isValidMaxValue(int i)
    {
        return (i >= min);
    }

    /**
     * Is the given value valid for the Min property .
     * @param i the given value
     * @return true if the given value is acceptable, false if not.
     */
    protected boolean isValidMinValue(int i)
    {
        return (i <= max);
    }

	/**
	* Fire an action event to the listeners
	*/
	protected void sourceActionEvent( String s)
	{
		if (actionListener != null)
			actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, s));
	}

    /**
     * Increments the spinner's value and handles wrapping as needed.
     * @see #scrollDown
     * @see #increment
     */
    protected void scrollUp()
    {
    	try
    	{
    	    if ( validateText() )
    	    {
    	        Integer i = new Integer( textFld.getText() );
    	        current =  i.intValue();
    	    }
    		setCurrent(current + increment);
    	}
    	catch (PropertyVetoException exc)
    	{
            if(wrappable)
            {
    			try { setCurrent(min); } catch (PropertyVetoException ex) {}
            }
            else
            {
    			try { setCurrent(max); } catch (PropertyVetoException ex) {}
            }
    	}

        updateText(false);
    }

    /**
     * Decrements the spinner's value and handles wrapping as needed.
     * @see #scrollUp
     * @see #increment
     */
    protected void scrollDown()
    {
    	try
    	{
    	    if ( validateText() )
    	    {
    	        Integer i = new Integer( textFld.getText() );
    	        current =  i.intValue();
    	    }
    		setCurrent(current - increment);
    	}
    	catch (PropertyVetoException exc)
    	{
            if (wrappable)
            {
    			try { setCurrent(max); } catch (PropertyVetoException exc1) {}
            }
            else
            {
    			try { setCurrent(min); } catch (PropertyVetoException exc1) {}
            }
    	}

        updateText(false);
    }

	/**
	 * Updates the text field with the current text, as needed or depending on the force flag.
	 * @param force If true, causes the text field to update even if the value has not changed.
	 * @see #getCurrentText
	 */
    protected void updateText(boolean force)
    {
        String currentText;

        currentText = getCurrentText();

        //If the text has changed, put the new text into the text field
        if(force || !textFld.getText().equals(currentText))
        {
          	textFld.setText(currentText);
        }
    }

	/**
	 * Handles enabling or disabling the spinner buttons as needed.
	 */
	protected void updateButtonStatus()
	{
		if(buttons != null)
        {
		    if(wrappable)
		    {
				buttons.setUpButtonEnabled(true);
				buttons.setDownButtonEnabled(true);
			}
		    else
			{
				if(current == max && current == min)
				{
				    buttons.setUpButtonEnabled(false);
				    buttons.setDownButtonEnabled(false);
				}
			    else if(current == max)
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
     * Gets the currently selected string from the list.
     * @return the string currently visible in the Spinner
	 * @see #updateText(boolean)
     */
    protected abstract String getCurrentText();

    /**
     * returns boolean if text in field is a valid entry
     * @return true if text is valid
     */
    protected boolean validateText( )
    {
        return false;
    }
}

