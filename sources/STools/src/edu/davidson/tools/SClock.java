////////////////////////////////////////////////////////////////////////////////
//      SClock.java
//      Wolfgang Christian
package edu.davidson.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Timer;

/**
 * A runnable class designed to provide animation for Physlets.  Objects wishing
 * to receive notification of clock ticks must implement the SStepable interface.
 * SStepable objects must register themselves using the addClockListener method.
 * The stepping thread is created by the constructor and put into a wait state.
 * StartClock() will notify the thread to start running.
 *
 * SClock is also a SDataSourceso that other applets can be notified of clock
 * ticks using JavaScript. DataConnections and inter-applet communication is
 * described in the book "Teaching with Physlets."
 */
public final class SClock extends Object implements Runnable, SDataSource {
  public static boolean isJS = /** @j2sNative true || */ false;
  Vector<SStepable> clockListeners = new Vector<SStepable>();      // The list of all SStepable objects.
  private String[]   varStrings     = new String[]{"t"};
  private double[][] ds             = new double[1][1];  // the datasource state variable t
  private Object     runLock        = new Object();
  private boolean    shouldRun      = true;              // boolean to to keep the while loop in the run method going.
  private boolean    running        = false;             // signal the thread to go into a wait state.
  private double     time           = 0.0;
  // Be careful.
  // the following values can be read within the package but should not be written unless the clock is stopped.
  int                delay          = 100;
  double             maxTime        = 100;
  double             minTime        = 0;
  double             dt             = 0.1;
  boolean            oneShot        = false;
  boolean            cycle          = false;
  SApplet            owner          = null;
  
protected Timer      swingTimer;
protected Thread     thread         = null;
long t0 = System.currentTimeMillis();  


	/**
	 * Create a new SClock. A clock thread is created but it immediately enters
	 * a wait state. A clock will update its clock listeners after the
	 * startClock method has been called.
	 */
	@SuppressWarnings("unused")
	public SClock() {

		// BH: I would recommend removing this constructor or making it private.
		// Otherwise owner could be null in methods below

		newThread(true, true);
		try {
			SApplet.addDataSource(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * @j2sNative
		 * 
		 */
		{
			if (true)
				return;
		}
		// JavaScript only -- preload needed classes
		// so that there is no delay for the first tick
		new ActionEvent(null, 0, null);
		createSwingTimer();
	}

  /**
   *       Create a new SClock to animate the given SApplet. The normal behavior is for
   * every SApplet to have one clock.    The static variable runningID in SApplet is set to the
   * clock's owner when a clock starts running.  Other SApplets can check this
   * variable to determine if they should stop their animation in order to improve
   * performance.
   *
   *       @param              owner the SApplet that uses this clock for animation.
   *
   *       @see                SClock#SClock()
   */
  public SClock(SApplet owner) {
    this();
    // BH if you get rid of this(), you can move this line up so that owner is defined prior to starting the thread.
    this.owner = owner;  
    
  }

	/**
	 * Add an object to the list of objects that will be stepped at every clock
	 * tick. A clock listener can only be added once.
	 *
	 * @param cl
	 *            the clock listener
	 *
	 */
	public synchronized void addClockListener(SStepable cl) {
		if (thread == null) { // start the clock in order to be ready to go.
								// We'll call start clock later.
			synchronized (runLock) {
				newThread(false, true);
			}
		}
		if (clockListeners.contains(cl)) {
			return;
		} else {
			clockListeners.addElement(cl);
		}
	}

	/**
	 * collected resource
	 * 
	 * @param startWaiting   start the thread in a wait state
	 */
	private void newThread(boolean asDaemon, boolean startWaiting) {
		shouldRun = true;
		running = !startWaiting;
		thread = new Thread(this);
		/**
		 * @j2sNative
		 * 
		 * 			return this.run();
		 * 
		 */
		if (asDaemon)
			thread.setDaemon(true);
		if(!isJS) thread.start();  // only start Thread in Java mode
	}

/**
   *       Remove an object from the list of objects that will be stepped at every clock tick.
   *
   *       @param              cl the clock listener
   *
   */
  public void removeClockListener(SStepable cl) {
    synchronized(runLock) {  // make sure we have finisthed the last step and are in a wait state
      if(!clockListeners.contains(cl)) {
        return;
      }
      clockListeners.removeElement(cl);
    }
  }

  /**
   *       Remove all objects from the list that will be stepped at every clock tick.
   *
   * @see                SClock#removeClockListener(SStepable)
   */
  public void removeAllClockListeners() {
    synchronized(runLock) {  // make sure we have finisthed the last step and are in a wait state
      clockListeners.removeAllElements();
    }
  }

  /**
   * Do one time step and update all clock listeners.  This method is designed to
   * single step animation when the clock is stopped.
   *
   * This method will check
   * to see if there are any restrictions on the time, such as oneShot or cycle, before
   * proceeding to step the clock listeners.
   *
   */
  public void doStep() {
    if(running) {
      stopClock();
      return;
    }
    boolean didCycle = false;
    // no need to step if the clock is running or if the time is out of range.
    if(isRunning() || (oneShot && (dt > 0) && (time + 0.49 * dt >= maxTime))
        || (oneShot && (dt < 0) && (time + 0.49 * dt <= minTime)) || (cycle && (dt < 0) && (time + dt < minTime))) {
      return;
    }
    if(cycle && (time >= maxTime) && (dt > 0)) {  // cycle if time is > max time
      time     = minTime;
      didCycle = true;
    }
    if(cycle && (time < minTime) && (dt < 0)) {  // cycle if time is <min time
      time     = maxTime;
      didCycle = true;
    }
    for(Enumeration<SStepable> e = clockListeners.elements(); e.hasMoreElements(); ) {
      SStepable clockListener = e.nextElement();
      if(shouldRun) {
        clockListener.step(dt, time);
      }
    }
    if((owner != null) && didCycle) {
      owner.cyclingClock();
    }
    if((owner != null) && shouldRun) {
      if(owner.destroyed) return;
      owner.updateDataConnection(hashCode());  // see if anyone is listening to this clock.
    }
    time += dt;
  }

	/**
	 * Do one time step and update all clock listeners. This private method
	 * should only be called by the clock thread.
	 *
	 */
	private void runningStep() {
		// System.out.println("step clock.");
		boolean didCycle = false;
		if (cycle && (time + 0.49 * dt >= maxTime) && (dt > 0)) {
			// check to see if we are past the maximum time.
			time = minTime;
			didCycle = true;
		}
		if (shouldRun) // BH simplified
			for (Enumeration<SStepable> e = clockListeners.elements(); e.hasMoreElements();) {
				e.nextElement().step(dt, time);
			}
		if ((owner != null) && shouldRun) {
			if (owner.destroyed)
				return;
			owner.updateDataConnection(hashCode()); // see if someone is
													// listening to this clock
													// using a data connection.
		}
		time += dt;
		if ((owner != null) && (SApplet.runningID != null) && (owner != SApplet.runningID)) {
			// stop running if another SApplet starts running.
			running = false;
			owner.pausingClock();
		}
		if ((cycle && (dt < 0) && (time + 0.49 * dt < minTime))
				|| (oneShot && (dt < 0) && (time + 0.49 * dt <= minTime))
				|| (oneShot && (dt > 0) && (time + 0.49 * dt >= maxTime))) {
			running = false; // pause the thread if another applet is running or
								// we are past.
			owner.stoppingClock();
		}
		if ((owner != null) && didCycle) {
			owner.cyclingClock();
		}
	}

  /**
   * Returns the animation time.
   *
   * @return             the time.
   */
  public final double getTime() {
    return time;
  }

  /**
   *       Returns the minimum time for cycle mode.
   *
   *       @return             minTime.
   */
  public final double getMinTime() {
    return minTime;
  }

  /**
   *       Returns the maximum time for cycle and one-shot mode.
   *
   *       @return             maxTime.
   */
  public final double getMaxTime() {
    return maxTime;
  }

  /**
   * Sets the animation time.
   *
   * @param             t the time.
   */
  public void setTime(double t) {
    boolean oldRunning = running;
    running = false;
    synchronized(runLock) {  // make sure we are in a wait state.
      time = t;
      for(Enumeration<SStepable> e = clockListeners.elements(); e.hasMoreElements(); ) {
    	  e.nextElement().step(0, time); // BH simplified
      }
    }
    if(oldRunning) {
      startClock();
    } else {
      if(owner.destroyed) return;
      owner.updateDataConnection(hashCode());
    }
  }

  /**
   * Returns the time step.
   *
   * @return            the time step.
   * @depreciated       use getTimeStep(double).
   */
  public final double getDt() {
    return dt;
  }

  /**
   * Set the time step.
   *
   * @param             newDt the time step for each clock tick.
   * @depreciated       use setTimeStep(double).
   */
  public void setDt(double newDt) {
    boolean oldRunning = running;
    running = false;
    synchronized(runLock) {  // make sure we are in a wait state.
      dt = newDt;
    }
    if(oldRunning) {
      startClock();
    }
  }

  /**
   * Returns the time step.
   *
   * @return             the time step.
   */
  public final double getTimeStep() {
    return getDt();
  }

  /**
   * Set the time step.
   *
   * @param             newDt the time step for each clock tick.
   */
  public void setTimeStep(double newDt) {
    setDt(newDt);
  }

  /**
   * Set the clock to always increment the time.
   *
   * @see                SClock#setCycle(double, double)
   * @see                SClock#setOneShot(double, double)
   */
  public void setContinuous() {
    cycle   = false;
    oneShot = false;
  }

  /**
   * Set the clock to cycle between min and max time values.
   *
   * @param              min the time minimum.
   * @param              max the time maximum.
   * @see                SClock#setContinuous()
   * @see                SClock#setOneShot(double, double)
   */
  public void setCycle(double min, double max) {
    boolean oldRunning = running;
    double  oldTime    = time;
    running = false;
    synchronized(runLock) {               // make sure we are in a wait state.
      minTime = Math.min(min, max);       // find the minimum
      maxTime = Math.max(min, max);
      time    = Math.max(time, minTime);  // make sure we aren't before the minimum time.
      time    = Math.min(time, maxTime);  // make sure we aren't past the maximum time.
      oneShot = false;
      cycle   = true;
    }
    if(oldRunning) {
      startClock();
    } else if(oldTime != time) {
      if(owner.destroyed) return;
      owner.updateDataConnection(hashCode());
    }
  }

  /**
   * Set the clock to run one time from the min time to the max time.
   *
   * @param              min the time minimum.
   * @param              max the time maximum.
   * @see                SClock#setContinuous()
   * @see                SClock#setCycle(double, double)
   */
  public void setOneShot(double min, double max) {
    boolean oldRunning = running;
    double  oldTime    = time;
    running = false;
    synchronized(runLock) {               // make sure we are in a wait state.
      minTime = Math.min(min, max);       // find the minimum
      maxTime = Math.max(min, max);
      time    = Math.max(time, minTime);  // make sure we aren't before the minimum time.
      time    = Math.min(time, maxTime);  // make sure we aren't past the maximum time.
      oneShot = true;
      cycle   = false;
    }
    if(oldRunning) {
      startClock();
    } else if(oldTime != time) {
      if(owner.destroyed) return;
      owner.updateDataConnection(hashCode());
    }
  }
  
  public void startClock() {
    if(isJS){
		Timer timer = new Timer(5, new ActionListener() {  // delay start of clock to give calling function time to finish
			public void actionPerformed(ActionEvent e) {
				startClockLater();
			}
		});
		timer.setRepeats(false);
		t0 = System.currentTimeMillis();
		timer.start();
    }else{
    	startClockLater();
    }
  }

  /**
   *       Notify the thread to start running in order to produce clock ticks.
   *
   * @see                SClock#stopClock()
   */
  synchronized public void startClockLater() {
    if(owner != null) {
      SApplet.runningID = owner;
    }
    if(running && (thread != null) || ( swingTimer!=null && swingTimer.isRunning())) {  // clock is already running
      return;
    }
    if(oneShot && (dt > 0) && (time + 0.49 * dt >= maxTime)) {
      running = false;  // pause the thread if another applet is running or we are past.
      owner.stoppingClock();
      return;
    }
    if((oneShot || cycle) && (dt < 0) && (time + 0.49 * dt <= minTime)) {
      running = false;  // pause the thread if another applet is running or we are past.
      owner.stoppingClock();
      return;
    }
    if (thread != null) {
			/**
			 * @j2sNative
			 * 
			 * 			this.thread.stop(); this.thread = null;
			 * 
			 */
    }
    if(thread == null) {
    	newThread(false, false);
    } else {
      synchronized(runLock) {
        running = true;
        runLock.notifyAll();
      }
    }
  }

  /**
   * Stop the thread at all costs.
   *
   * Should only be called by an applet's destroy() method or to clear an error condition.
   *
   * @see SClock#stopClock()
   */
  public synchronized void panicStopClock() {
    //clockListeners.removeAllElements();  // testing to see if this fixes jsript bug.
    shouldRun = false;
    
    if (swingTimer == null && thread == null)
    	return;
    /**
     * @j2sNative
     * 
     * this.swingTimer.stop();
     * this.thread = null;
     * return;
     * 
     */
    
    startClock();
    Thread tempThread = thread;
    if(tempThread != null) {
      try {
        tempThread.interrupt();
        tempThread.join(1000);
      } catch(Exception e) {
        tempThread.stop();
      }  // just in case clock does not stop normally.
    }
    thread = null;
  }

	/**
	 * Notify the thread to stop running. All SStepable objects must complete
	 * their last step before the clock can stop.
	 *
	 * @see SClock#startClock()
	 */
	public void stopClock() {
		if (swingTimer != null)
			swingTimer.stop();
		swingTimer = null;
		if (!running) {
			return; // we are not running so return.
		}
		running = false;
		synchronized (runLock) {
			// make sure we are in a wait state before we return.
			;
		}
	}

  /**
   *       Estimate the frames per second, FPS, for the animation. The frames per second
   * is in real time. Each frame will advance the clock by a time step, dt.
   *
   * @see                SClock#getTimeStep()
   *
   * @return
   */
  public double getFPS() {
    return 1000.0 / delay;
  }

  /**
   *       Set the frames per second, FPS, for the animation. The frames per second
   * is in real time. Each frame will advance the clock by a time step, dt.
   *
   * @see                SClock#setTimeStep(double)
   * @param fps
   */
  public void setFPS(double fps) {
    //delay=Math.max(10,(int)(1000/fps) );
    delay = Math.max(5, (int) (1000 / fps));  // wait at least 5 ms
  }

  /**
   *       Determine if the clock is in cycle mode.
   *
   * @return
   */
  public boolean isCycle() {
    return cycle;
  }

  /**
   *       Determine if the clock is in oneShot mode.
   *
   * @return
   */
  public boolean isOneShot() {
    return oneShot;
  }

  /**
   *       Determine if the clock is in continuous mode.
   *
   * @return
   */
  public boolean isContinous() {
    return !(oneShot || cycle);
  }

  /**
   *       Determine if the clock is running.  That is, are SStepable objects being updated
   * every clock tick.
   *
   * @return
   */
  public boolean isRunning() {
    return running;
  }

	/**
	 * The run method passed to the thread. DO NOT access this method.
	 */
	public void run() {
		while (shouldRun) {
			synchronized (runLock) {
				while (running == false) {
					/**
					 * @j2sNative
					 * 
					 * 			return;
					 */
					{
						try {
							runLock.wait();
						} catch (InterruptedException ie) {
							return;
						}
					}
				}
				// BH running is true and we have been notified
				notified();
			}
			if (!doDelay())
				return;
			/**
			 * @j2sNative
			 * 
			 * break;
			 */
		}
	}


	// BH: for JavasScript and Java:
  
	private void notified() {
		if (shouldRun) {
			//long t0 = System.currentTimeMillis();
			runningStep();
			//System.out.println("creaetime " + (System.currentTimeMillis() - t0) + " " + delay);
		}
	}

	/**
	 * 
	 * @return true to continue
	 */
	@SuppressWarnings("unused")
	private boolean doDelay() {
		if (!shouldRun)
			return true;
		/**
		 * @j2sNative
		 * 
		 */
		{
			// Java only
			try {
				Thread.sleep(delay);
				if (true) 
					return true;
			} catch (InterruptedException ie) {
				return false;
			}
		}
		// JavaScript only
		createSwingTimer();
		swingTimer.start();
		return true;
	}

	/**
	 * Pre-create the timer so there is no file loading before the first tick
	 */
	private void createSwingTimer() {
		if (thread == null) thread = new Thread(this);
		long t1=System.currentTimeMillis();
		int myDelay=(int)(t1 -t0); 
		myDelay=Math.min(delay, myDelay);  // do not wait longer than requested delay
		myDelay=Math.max(10,myDelay);      // wait a minimum of 10 ms.
		t0=t1;  
		System.out.println("my delay ="+myDelay);
		swingTimer = new Timer(myDelay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				swingTimer = null;
				if  (thread != null)
					thread.stop();
				thread = null;
				if (shouldRun)
					run();
			}

		});
		swingTimer.setRepeats(false);
	}

	// SDataSource Methods

/**
   *       Get the variables for use by a data connection.  SClock has a single variable, t.
   *
   *       @return            the variables.
   */
  public double[][] getVariables() {
    ds[0][0] = time;
    return ds;
  }

  /**
   *       Get the variable string for use by a data connection. SClock has a single variable string, "t".
   *
   * @return
   */
  public String[] getVarStrings() {
    return varStrings;
  }

  /**
   *      Get the integer id of the object.
   *
   * @return            the variable strings.
   */
  public int getID() {
    return hashCode();
  }

  /**
   *      Set the SApplet owner of the clock.
   *
   * @param  owner the owner.
   */
  public void setOwner(SApplet owner) {
    this.owner = owner;
  }

  /**
   *      Get the SApplet owner of the clock.
   *
   * @return            the SApplet.
   */
  public SApplet getOwner() {
    return owner;
  }

  class Tester implements SStepable {

	@Override
	public void step(double dt, double time) {
		System.out.println("SClock.Tester.step(" + dt + "," + time + ")");
		if (time >= 5) {
			System.out.println("done");
			System.out.flush();
			System.exit(0);
		}
	}
	  
  }
  
  public static void main(String[] args) {
	SClock sc = new SClock();
	Tester tester = sc.new Tester();
	sc.addClockListener(tester);
	sc.setContinuous();
	sc.setDt(0.5);
	sc.startClock();
	// Because the Java clock thread is a daemon, and there are no other threads in this
	// thread group, exiting of the thread running main() closes the timer as well.  
	// So in Java we must sleep. 
	//
	// It's a SwingJS bug to ignore the distinction between user and daemon threads
	/**
	 * @j2sNative
	 * 
	 *  
	 */ 
	{
		 try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
  }
  
}
