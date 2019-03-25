package symantec.itools.util;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;

import circuit.Circuit;

import java.awt.AWTEventMulticaster;

//	05/30/97	LAB	Updated to support Java 1.1
//	06/06/97	LAB	Removed deprecated (unusable in 1.1) functions:
//					public void setEventType(int type)
//					public int getEventType()
//					public void setTarget(Component t)
//					public Component getTarget()
//	06/24/97	LAB	Changed the behavior of the start and stop
//					methods to actually start and stop the thread
//					instead of suspending and resuming it.  Added
//					pause() and resume() for this purpose.
//  07/23/97    CAR implemented readObject
//	08/19/97	LAB	Fixed a bug where Timer would keep going even if repeat was set to false.
//					Fixed resume to only resume if currently paused.  Changed property
//					names used by firePropertyChange to follow the naming conventions set
//					forth in the Bean Spec. (lowwer case first letter, unless second letter
//					is capitalized too).  Made package level data protected.
//	10/11/97	LAB	Fixed a problem where if stop was called before the original delay
//					elapsed it would wait until the delay elapsed (Thanks KSB.  Addresses
//					Mac Bug #9172).  Fixed resume to start the timer if it was not
//					started (also avoids Exception if the thread was dead).	Now no
//					threads are created or accessed at design time (Addresses Mac Bug
//					#9187).  Fixed a problem when calling stop on a stopped timer would
//					cause an exception (Addresses Mac Bug #9178).  Added restart method.
//					Fixed stoping a timer would reset the repeat property (Addresses Mac
//					Bug #9171).
//	10/14/97	LAB	Added isEnabled and getEnabled for compatability with the backrunner
//					(Addresses Mac Bug #9268).
//	10/16/97	LAB	Made methods synchronized as needed.  Fixed a problem where stopping,
//					then starting would sometimes result in no events being fired (Addresses
//					Mac Bug #9376).
//	10/17/97	LAB	Made thread return when it was interrupted, and check in start for interrupted
//					thread before resuming it (re-fixes #9376).

/**
 *
 * Sets a timer to wait before an action event is posted to a component.
 * The caller can specify the target component, the event to send to the
 * component, and the time delay.
 *
 * The timer is implemented as a thread.  The one of the start(...) methods should
 * be called to start the thread.
 *
 *
 * @version 1.0, Nov 26, 1996
 *
 * @author	Symantec
 *
 */
public class Timer implements Runnable, java.io.Serializable
{
    /**
     * Creates a timer with the default delay.
     * After 1000 miliseconds this timer will fire an ActionEvent.
	 * It will not repeat.
     */
    public Timer()
    {
		this(1000, false);
    }

    /**
     * Creates a timer with specified delay.
     * After the specified delay this timer will fire an ActionEvent.
	 * It will not repeat.
     * @param d the delay in milliseconds
     */
    public Timer(int d)
    {
		this(d, false);
    }

    /**
     * Creates a timer with specified repeat setting and the default delay.
     * After 1000 miliseconds this timer will fire an ActionEvent.
	 * It may repeat, depending on r.
     * @param r if true, reset and repeat after generating the event
     */
    public Timer(boolean r)
    {
		this(1000, r);
    }

    /**
     * Creates a timer with specified delay and repeat setting.
     * After the specified delay this timer will fire an ActionEvent.
	 * It may repeat, depending on r.
     * @param d the delay in milliseconds
     * @param r if true, reset and repeat after generating the event
     */
    public Timer(int d, boolean r)
    {
        delay			= d;
        repeat			= r;
        execute			= false;
        live			= false;
        isDesignTime	= java.beans.Beans.isDesignTime();
        if(!isDesignTime)
	        thread = new Thread(this);
    }

    /**
	 * @deprecated
	 * @see Timer#Timer(int, boolean)
     */
	public Timer(Component t)
    {
        this(1000);
    }

    /**
	 * @deprecated
	 * @see Timer#Timer(int, boolean)
     */
    public Timer(Component t, int d)
    {
        this(d, false);
    }

    /**
	 * @deprecated
	 * @see Timer#Timer(int, boolean)
     */
    public Timer(Component t, int d, boolean r)
    {
        this(d, r);
    }

    /**
	 * @deprecated
	 * @see Timer#Timer(int, boolean)
     */
    public Timer(Component t, int d, boolean r, int e)
    {
		this(d, r);
	}

    /**
     * Sets the delay time for this timer.
     * @param d the delay in milliseconds.  This delay will be used starting
     *          after the current delay elapses
     *
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     * @see #getDelay()
     */
    public void setDelay(int d) throws PropertyVetoException
    {
    	Integer newValue = new Integer(d);
    	Integer oldValue = new Integer(delay);

		getVetos().fireVetoableChange("delay", oldValue, newValue);

        delay = d;

		getChanges().firePropertyChange("delay", oldValue, newValue);
    }

    /**
     * Obtains the delay time setting for this timer.
     * @return the current delay setting for this timer, in milliseconds
     * @see #setDelay(int)
     */
    public int getDelay()
    {
        return delay;
    }


    /**
     * Changes the repeat setting of the timer.
     * If the repeat setting is false a single event will be generated.  When
     * set to true the timer produces a series of events.
     *
     * @param f reset and repeat after generating the event
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     * @see #isRepeat
     */
    public void setRepeat(boolean f) throws PropertyVetoException
    {
    	Boolean newValue = new Boolean(f);
    	Boolean oldValue = new Boolean(repeat);

		getVetos().fireVetoableChange("repeat", oldValue, newValue);

		repeat = f;

		getChanges().firePropertyChange("repeat", oldValue, newValue);
    }

    /**
     * Obtains the repeat setting of the timer.
     * @return true if this timer is set to repeat, false if this timer does not repeat
     * @see #setRepeat
     */
    public boolean isRepeat()
    {
        return repeat;
    }

    /**
	 * @deprecated
	 * @see #isRepeat()
     */
    public boolean getRepeat()
    {
        return isRepeat();
    }

	/**
	 * Is the timer currently enabled
	 * @return true if the timer is running.
	 */
	 public boolean isEnabled()
	 {
	 	return live;
	 }

	/**
	 * @deprecated
	 * Needed for compatability with backrunner.
	 */
	 public boolean getEnabled()
	 {
	 	return isEnabled();
	 }

    /**
     * Pauses the timer.
     * Differs from stop in that the timer
     * is continued from whatever state it was in before
     * pausing.
     * <p>
     * start() and stop() overrule this function.
     * @see #resume
     * @see #start
     * @see #stop
     */
    synchronized public void pause()
    {
    	execute = false;
    	if(thread.isAlive())
    		thread.suspend();

    	if(thread.isAlive())
    		thread.suspend();
    }

    /**
     * Resumes the timer.
     * Differs from start in that the timer
     * is continued from whatever state it was in before
     * pausing.
     * <p>
     * start() and stop() overrule this function
     * @see #pause
     * @see #start
     * @see #stop
     */
    synchronized public void resume()
    {
    	if(!execute)
    	{
	    	execute = true;
	    	if(thread.isAlive())
			    thread.resume();
			else
				start();
		}
	}

    /**
     * Starts the timer with existing settings.
     * @see #start(int)
     * @see #start(boolean)
     * @see #start(int, boolean)
     * @see #stop
     * @see #run
     */
    synchronized public void start()
    {
	    execute = true;
	    live	= true;
	    if(!isDesignTime)
	    {
		    if(thread.isAlive() && !thread.isInterrupted())
		    {
		    	if(thread.isAlive())
		    		thread.resume();
		    }
		    else
			{
		    	thread	= new Thread(this);
		    	if(circuitsimulator.Circuit.isJS) thread.start();
			}
		}
    }

    /**
     * Starts the timer using the specified delay.
     * @param d the delay in milliseconds
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     * @see #start()
     * @see #start(boolean)
     * @see #start(int, boolean)
     * @see #stop
     * @see #run
     */
    synchronized public void start(int d) throws PropertyVetoException
    {
	    setDelay(d);

	    start();
    }

    /**
     * Starts the timer using the specified repeat setting.
     * @param r reset and repeat after generating the event
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     * @see #start()
     * @see #start(int)
     * @see #start(int, boolean)
     * @see #stop
     * @see #run
     */
    synchronized public void start(boolean r) throws PropertyVetoException
    {
	    setRepeat(r);

	    start();
    }

    /**
     * Starts the timer using the specified delay and repeat settings.
     * @param d the delay in milliseconds
     * @param r reset and repeat after generating the event
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     * @see #start()
     * @see #start(int)
     * @see #start(boolean)
     * @see #stop
     * @see #run
     */
    synchronized public void start(int d, boolean r) throws PropertyVetoException
    {
	    setDelay(d);
	    setRepeat(r);

	    start();
    }

	/**
	 * Restarts the timer immediately with the current delay value.
	 * This will start a stopped timer.
	 * @see #start()
	 * @see #stop()
	 */
	synchronized public void restart()
	{
		stop();
		start();
	}

    /**
     * Stops the timer.  After return the timer will generate no more events.
     * @see #start
     */
    synchronized public void stop()
    {
	    execute		= false;
	    live		= false;
	    if(!isDesignTime && thread.isAlive())
	    {
	    	thread.interrupt();
	    }
    }

    /**
     * The thread body.  This method is called by the Java virtual machine in response to a
     * start call by the user.
     * @see #start()
     * @see #start(int)
     * @see #start(boolean)
     * @see #start(int, boolean)
     * @see #stop
     */
    public void run()
    {
		if(!execute)
		{
			thread.suspend();
		}
		while(live)
		{
			do
			{
				repeating = repeat;
				try
				{
					if(circuitsimulator.Circuit.isJS)thread.sleep(delay);
					if (execute)
					{
						sourceActionEvent();
					}
				}
				catch (InterruptedException e) { return; }
			}
			while (repeating && live);

			if((!execute && live) || !repeating)
			{
				thread.suspend();
			}
		}
	}

    /**
     * Sets the command name of the action event fired by this button.
     * @param command Tthe name of the action event command fired by this button
     * @see #getActionCommand
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
    public void setActionCommand(String command) throws PropertyVetoException
    {
    	String oldValue = actionCommand;

		getVetos().fireVetoableChange("actionCommand", oldValue, command);
        actionCommand = command;
		getChanges().firePropertyChange("actionCommand", oldValue, command);
    }

    /**
     * Returns the command name of the action event fired by this button.
     * @see #setActionCommand
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
	public void addActionListener(ActionListener l)
	{
		actionListener = AWTEventMulticaster.add(actionListener, l);
	}

    /**
     * Removes the specified action listener so it no longer receives
     * action events from this button.
     * @param l the action listener
     */
	public void removeActionListener(ActionListener l)
	{
		actionListener = AWTEventMulticaster.remove(actionListener, l);
	}

    /**
     * Fires an action event to the listeners.
     * @see #setActionCommand
     */
	public void sourceActionEvent()
	{
		if (actionListener != null)
			actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, actionCommand));
	}

    /**
     * Adds a listener for all property change events.
     * @param listener the listener to add
     * @see #removePropertyChangeListener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
    	getChanges().addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener for all property change events.
     * @param listener the listener to remove
     * @see #addPropertyChangeListener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
    	getChanges().removePropertyChangeListener(listener);
    }

    /**
     * Adds a listener for all vetoable property change events.
     * @param listener the listener to add
     * @see #removeVetoableChangeListener
     */
    public void addVetoableChangeListener(VetoableChangeListener listener)
    {
		getVetos().addVetoableChangeListener(listener);
    }

    /**
     * Removes a listener for all vetoable property change events.
     * @param listener the listener to remove
     * @see #addVetoableChangeListener
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener)
    {
    	getVetos().removeVetoableChangeListener(listener);
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();

        execute   = false;
        thread    = new Thread(this);
    }

    /**
     * Reserved.
     */
    protected Component			target;
    /**
     * Reserved.
     */
    protected int					eventType;
    /**
     * The value of the Repeat property.
     */
   	protected boolean				repeat;
    /**
     * Internal use. True if Repeat property true, and Timer is in its repeat timing loop.
     */
    protected boolean				repeating;
    /**
     * True while timer thread is running.
     */
    protected boolean				execute;
    /**
     * True if the timer is enabled and running.
     */
    protected boolean				live;
    /**
     * True if we're in the Java development environment.
     */
    protected boolean				isDesignTime;
    /**
     * The value of the Delay property.
     */
    protected int					delay;
    /**
     * The value of the Action Command property.
     */
    protected String				actionCommand;
    /**
     * Listener(s) that get notified when an ActionEvent is generated.
     */
	protected ActionListener		actionListener = null;
    /**
     * The timer's thread.
     */
    transient protected Thread	thread;
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
