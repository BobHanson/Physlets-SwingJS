package symantec.itools.awt;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.awt.AWTEventMulticaster;

//	01/02/97	RKM	Changed paintComponent to not cause recursive drawing on overlap
//	01/02/97	RKM	Changed paintComponent to flush image after it is used
//	01/16/97	RKM	Added override of update to avoid flicker
//	01/18/07	RKM	Added more documentation and checked return from img.getGraphics
//	01/29/97	TWB	Integrated changes from Windows and RKM's changes
// 	01/29/97	TWB	Integrated changes from Macintosh
// 	02/01/97	RKM	Extracted transparency drawing trick into TransparencyTrickUtils
// 	03/21/97	LAB	Updated to Java 1.1.
// 	07/14/97	LAB	Added add/removeNotify for event listener registration.
//					Removed use of TransparencyTrick and made Lightweight.
//					Added version and author tags.
//					Removed paint() and update() as they are not needed.
//  07/29/97    CAR marked fields transient as needed
//                  inner adaptor classes implement java.io.Serializable

/**
 * Use this component to create an invisible area, usually within an image,
 * that initiates an action when clicked. Specifically, use InvisibleButton to
 * <UL>
 * <DT>� Create a "hot spot" on an image or on a component.</DT>
 * <DT>� Accept or yield focus.</DT>
 * <DT>� Respond to a user event.</DT>
 * <DT>� Send an action event to another component.</DT>
 * </UL>
 * Button tips:
 * <UL>
 * <DT>� Buttons accept and yield focus automatically by default.</DT>
 * <DT>� Buttons accept clicked events automatically by default.</DT>
 * <DT>� To send an action event to another component, use the Interaction
 * Wizard. Optionally, you can override the InvisibleButton�s click event in
 * project source code.</DT>
 * <DT>� Overlapping components - browsers handle components layered on each
 * other differently. Some browsers display/layer the InvisibleButtons on top
 * of a particular component, while others display them underneath. Therefore,
 * it often requires two sets of InvisibleButtons to ensure that one is "on top";
 * one on top of the particular component and the other underneath.
 * </UL>
 *
 * @version 1.1, July 14, 1997
 * @author Symantec
 */
public class InvisibleButton extends Component
{
	/**
	 * Constructs an InvisibleButton.
	 */
	public InvisibleButton()
	{
	    pressed = false;
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
		if (mouse == null)
		{
			mouse = new Mouse();
			addMouseListener(mouse);
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
		if (mouse != null)
		{
			removeMouseListener(mouse);
			mouse = null;
		}
		super.removeNotify();
	}

    /**
     * Sets the command name of the action event fired by this button.
     * @param command the name of the action event command fired by this button
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setActionCommand(String command) throws PropertyVetoException
    {
    	String oldValue = actionCommand;

		getVetos().fireVetoableChange("ActionCommand", oldValue, command);
        actionCommand = command;
		getChanges().firePropertyChange("ActionCommand", oldValue, command);
    }

    /**
     * @return the command name of the action event fired by this button.
     */
    public String getActionCommand()
    {
        return actionCommand;
    }

    /**
     * Adds the specified action listener to receive action events
     * from this button.
     * @param l the action listener
     */
	public synchronized void addActionListener(ActionListener l)
	{
		actionListener = AWTEventMulticaster.add(actionListener, l);
	}

    /**
     * Removes the specified action listener so it no longer receives
     * action events from this button.
     * @param l the action listener
     */
	public synchronized void removeActionListener(ActionListener l)
	{
		actionListener = AWTEventMulticaster.remove(actionListener, l);
	}

    /**
     * Adds a listener for all event getChanges().
     * @param PropertyChangeListener listener the listener to add.
     * @see #removePropertyChangeListener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener)
    {
    	getChanges().addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener for all event getChanges().
     * @param PropertyChangeListener listener the listener to remove.
     * @see #addPropertyChangeListener
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
    {
    	getChanges().removePropertyChangeListener(listener);
    }

    /**
     * Adds a vetoable listener for all event getChanges().
     * @param VetoableChangeListener listener the listener to add.
     * @see #removeVetoableChangeListener
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener listener)
    {
		getVetos().addVetoableChangeListener(listener);
    }

    /**
     * Removes a vetoable listener for all event getChanges().
     * @param VetoableChangeListener listener the listener to remove.
     * @see #addVetoableChangeListener
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener)
    {
    	getVetos().removeVetoableChangeListener(listener);
    }

	/**
	 * This is the Mouse Event handling innerclass.
	 */
	class Mouse extends java.awt.event.MouseAdapter implements java.io.Serializable
	{
		/**
		 * Handles Mouse Pressed events
		 * @param e the MouseEvent
		 */
		public void mousePressed(MouseEvent e)
		{
			pressed = true;
		}

		/**
		 * Handles Mouse Released events
		 * @param e the MouseEvent
		 */
		public void mouseReleased(MouseEvent e)
		{
		    if (pressed)
		    {
		        pressed = false;
		        sourceActionEvent();
		    }
		}
	}

    /**
     * Fire an action event to the listeners
     */
	protected void sourceActionEvent()
	{
		if (actionListener != null)
			actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, actionCommand));
	}

    String actionCommand = "ButtonPressed";
	ActionListener actionListener = null;
	/**
	 * True if the button is currently pressed.
	 */
	transient protected boolean pressed;
	private Mouse mouse = null;
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

