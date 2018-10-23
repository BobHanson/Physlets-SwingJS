package symantec.itools.awt.util.spinner;


import java.awt.*;

import java.awt.AWTEventMulticaster;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import symantec.itools.awt.Orientation;


//	05/30/97	RKM	Made it compile with some of the 1.1 component changes (added catches)
// 	06/02/97	MSH	Updated to Java 1.1 and "Good Bean" Spec
//	06/03/97	LAB Polished update to Java 1.1.
//					Changed the package to symantec.itools.awt.util.spinner.
//	07/11/97	LAB Updated calls to the deprecated methods enable() and disable() in
//					DirectionButton to the new call setEnabled(boolean).
//					Deprecated enable and disable functions, and replaced them with
//					setters and getters.
//	07/18/97	LAB Added add/removeNotify to handle event listener registration.
//  07/30/97    CAR inner adaptor class implements java.io.Serializable
//                  implemented readObject
//	08/28/97	LAB Changed the default look of the direction buttons to be more spinner oriented.
//  02/05/98    DS  Re-write of GUI

/**
 * This component provides the up/down or right/left buttons used in
 * spinners. It is used by abstract class Spinner and in the HorizontalSpinButtonPanel
 * and VerticalSpinButtonPanel components.
 *
 * @see symantec.itools.awt.Spinner
 * @see symantec.itools.awt.HorizontalSpinButton
 * @see symantec.itools.awt.VerticalSpinButton
 * @version 1.1, Nov 26, 1996
 * @author Symantec
 */
public class SpinButtonPanel
    extends    Panel
    implements Orientation
{
	/**
	 * This is the Adjustment Event handling innerclass.
	 */
    class Action
        implements ActionListener,
                   java.io.Serializable
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		Object source;

    		source = e.getSource();

    		if(source == incBtn)
    		{
    			sourceActionEvent("Increment");
    		}
    		else if(source == decBtn)
    		{
    			sourceActionEvent("Decrement");
    		}
    	}
    }

    /**
     * Current spinner button layout.
     * @see #getOrientation
     * @see #setOrientation
    */
    protected int     orientation;
    /**
     * Whether the spinner buttons will continually post notify events
     * while pressed.
     * @see #getNotifyWhilePressed
     * @see #setNotifyWhilePressed
     */
    protected boolean notifyWhilePressed;
    /**
     * Reserved.
     */
    protected int     delay;

    /**
     * Constructs a SpinButtonPanel.
     */
    public SpinButtonPanel()
    {
        //{{INIT_CONTROLS
		super.setLayout(new GridLayout(2,1,0,0));
		setSize(104,51);
		incBtn = new symantec.itools.awt.DirectionButton();

		try
		{
			incBtn.setDirection(symantec.itools.awt.DirectionButton.UP);
		}
		catch(java.beans.PropertyVetoException e) { }
		incBtn.setBounds(0,0,104,25);
		add(incBtn);
		decBtn = new symantec.itools.awt.DirectionButton();
		try {
			decBtn.setDirection(symantec.itools.awt.DirectionButton.DOWN);
		}
		catch(java.beans.PropertyVetoException e) { }
		decBtn.setBounds(0,25,104,25);
		add(decBtn);
		//}}
	}

	//{{DECLARE_CONTROLS
	symantec.itools.awt.DirectionButton incBtn;
	symantec.itools.awt.DirectionButton decBtn;
	//}}

    /**
     * Sets whether the spinner buttons are laid out one above the other or
     * one beside the other.
     * @param o the new orientation, one of: ORIENTATION_VERTICAL, or ORIENTATION_HORIZONTAL
     * @see #getOrientation
     * @see symantec.itools.awt.Orientation#ORIENTATION_VERTICAL
     * @see symantec.itools.awt.Orientation#ORIENTATION_HORIZONTAL
    */
	public void setOrientation(int o)
	{
	    if(o != orientation)
	    {
    	    orientation = o;

	        switch(orientation)
	        {
	            case ORIENTATION_VERTICAL :
    	        {
            		super.setLayout(new GridLayout(2,1,0,0));
	                break;
	            }
    	        case ORIENTATION_HORIZONTAL :
	            {
            		super.setLayout(new GridLayout(1,2,0,0));
	                break;
    	        }
	        }

    	    invalidate();
	        validate();
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
        incBtn.setNotifyWhilePressed(f);
        decBtn.setNotifyWhilePressed(f);
    }

    /**
     * Gets whether the spinner buttons will continually post notify events
     * while pressed.
     * @return true if notify events posted while pressed, false otherwise
     * @see #setNotifyWhilePressed
     * @see #setDelay
     * @see #getDelay
     */
    public boolean isNotifyWhilePressed()
    {
        return incBtn.isNotifyWhilePressed();
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
    public void setDelay(int d)
        throws PropertyVetoException
    {
        incBtn.setNotifyDelay(d);
        decBtn.setNotifyDelay(d);
    }

    /**
     * Returns the current delay between notification events of the spinner
     * buttons in milliseconds.
     * @see #setNotifyWhilePressed
     * @see #setDelay
     */
    public int getDelay()
    {
        return incBtn.getNotifyDelay();
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
	 * @param l the layout manager to use to layout this container's components
	 * (IGNORED)
	 * @see java.awt.Container#getLayout
	 **/
    public void setLayout(LayoutManager l)
    {
    }

    /**
     * Enables or disables this component so that it will respond to user input
     * or not.
     * This is a standard Java AWT method which gets called to enable or disable
     * this component. Once enabled this component will respond to user input.
     * @param flag true if the component is to be enabled,
     * false if it is to be disabled.
     *
     * @see java.awt.Component#isEnabled
     */
    public synchronized void setEnabled(boolean flag)
    {
    	if(isEnabled() != flag)
    	{
	    	if(flag)
	    	{
	    		super.enable();
		        incBtn.setEnabled(true);
		        decBtn.setEnabled(true);
			}
			else
			{
				super.disable();
		        incBtn.setEnabled(false);
		        decBtn.setEnabled(false);
			}
		}
    }

    /**
     * @deprecated
     * @see #setEnabled
     */
    public synchronized void enable()
    {
        setEnabled(true);
    }

    /**
     * @deprecated
     * @see #setEnabled
     */
    public synchronized void disable()
    {
        setEnabled(false);
    }

    /**
     * This enables or disables the incrementing button only.
     * @param flag true if the incrementing button is to be enabled,
     * false if it is to be disabled.
     * @see #isUpButtonEnabled
     */
    public synchronized void setUpButtonEnabled(boolean flag)
    {
    	if(isUpButtonEnabled() != flag)
    	{
	    	if(flag)
	    	{
	    		incBtn.setEnabled(true);
	    	}
	    	else
	    	{
	    		incBtn.setEnabled(false);
	    	}
	    }
    }

    /**
     * The enabled state of the incrementing button.
     * @return true if the incrementing button is enabled,
     * false if it is disabled.
     * @see #setUpButtonEnabled
     */
    public boolean isUpButtonEnabled()
    {
    	return incBtn.isEnabled();
    }

    /**
     * This enables or disables the decrementing button only.
     * @param flag true if the decrementing button is to be enabled,
     * false if it is to be disabled.
     * @see #isDownButtonEnabled
     */
    public synchronized void setDownButtonEnabled(boolean flag)
    {
    	if(isDownButtonEnabled() != flag)
    	{
	    	if(flag)
	    	{
	    		decBtn.setEnabled(true);
	    	}
	    	else
	    	{
	    		decBtn.setEnabled(false);
	    	}
	    }
    }

    /**
     * The enabled state of the decrementing button.
     * @return true if the decrementing button is enabled,
     * false if it is disabled.
     * @see #setDownButtonEnabled
     */
    public boolean isDownButtonEnabled()
    {
    	return decBtn.isEnabled();
    }

    /**
     * @deprecated
     * @see #setUpButtonEnabled
     */
    public synchronized void enableUpButton()
    {
        setUpButtonEnabled(true);
    }

    /**
     * @deprecated
     * @see #setDownButtonEnabled
     */
    public synchronized void enableDownButton()
    {
        setDownButtonEnabled(true);
    }

    /**
     * @deprecated
     * @see #setUpButtonEnabled
     */
    public synchronized void disableUpButton()
    {
        setUpButtonEnabled(false);
    }

    /**
     * @deprecated
     * @see #setDownButtonEnabled
     */
    public synchronized void disableDownButton()
    {
        setDownButtonEnabled(false);
    }

	/**
	 * Tells this component that it has been added to a container.
	 * This is a standard Java AWT method which gets called by the AWT when
	 * this component is added to a container. Typically, it is used to
	 * create this component's peer.
	 *
	 * It has been overridden here to hook-up event listeners.
	 *
	 * @see #removeNotify
	 */
	public synchronized void addNotify()
	{
		super.addNotify();

		//Hook up listeners
		if(action == null)
		{
			action = new Action();
        	incBtn.addActionListener(action);
        	decBtn.addActionListener(action);
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
		if(action != null)
		{
			incBtn.removeActionListener(action);
        	decBtn.removeActionListener(action);
			action = null;
		}

		super.removeNotify();
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
     * Adds the specified action listener to receive action events.
     * The ActionCommand will be either "Increment" or "Decrement"
     * depending on which spinner button was pressed.
     * @param l the action listener
     */
	public synchronized void addActionListener(ActionListener l)
	{
		actionListener = AWTEventMulticaster.add(actionListener, l);
	}

    /**
     * Removes the specified action listener so it no longer receives
     * action events from this component.
     * @param l the action listener
     */
	public synchronized void removeActionListener(ActionListener l)
	{
		actionListener = AWTEventMulticaster.remove(actionListener, l);
	}

	public Dimension getPreferredSize()
	{
	    int h;
	    int w;

	    h = 0;
	    w = 0;

	    switch(orientation)
	    {
            case ORIENTATION_VERTICAL :
	        {
	            w = Math.max(incBtn.getPreferredSize().width, decBtn.getPreferredSize().width);
	            h = incBtn.getPreferredSize().height + decBtn.getPreferredSize().height;
	            break;
            }
	        case ORIENTATION_HORIZONTAL :
	        {
	            w = incBtn.getPreferredSize().width + decBtn.getPreferredSize().width;
	            h = Math.max(incBtn.getPreferredSize().height, decBtn.getPreferredSize().height);
	            break;
	        }
	    }

        return (new Dimension(w, h));
	}

    /**
     * Fire an action event to the listeners
     */
	protected void sourceActionEvent(String actionCommand)
	{
		if(actionListener != null)
		{
			actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, actionCommand));
	    }
	}

    /**
     * The action listener to keep track of listeners for our action event.
     */
    protected ActionListener actionListener = null;

	private Action action = null;
	private symantec.itools.beans.VetoableChangeSupport vetos = new symantec.itools.beans.VetoableChangeSupport(this);
	private symantec.itools.beans.PropertyChangeSupport changes = new symantec.itools.beans.PropertyChangeSupport(this);
}
