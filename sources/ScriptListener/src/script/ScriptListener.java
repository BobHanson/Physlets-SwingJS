package script;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import edu.davidson.tools.*;
import netscape.javascript.JSObject;


public class ScriptListener extends SApplet implements SDataListener {
	boolean isJS = /** @j2sNative true || */ false;
	//boolean isJS = true;  // for debugging
	private  Timer timer;					   // Swing timer when in JavaScript
	private final static int STATE_WAITING = 0;
	private final static int STATE_EXECUTE = 1;
	public int state = STATE_WAITING;
	JSObject jso = null;
	String jsFunction;
	
    boolean respondToAddData=true;
    boolean respondToAddDatum=true;
    boolean respondToDeleteSeries=true;
    boolean respondToClearSeries=true;

    Object               lock = new Object();
    Dispatcher           dispatcherThread;
    Create               createThread;
    boolean              newdata=false;
    boolean              appletRunning = true;

    //Initialize the applet
    public void init() {
     initResources(null);
     try{SApplet.addDataListener(this); }catch (Exception e){e.printStackTrace();}
    }

    public void start(){
        super.start();
        if(firstTime){
          firstTime=false;
        }
    }

    
	public void runTimer() {
		switch (state) {
			case STATE_WAITING:
				timer = new Timer(50, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						    //System.out.println("Timer waiting");
							runTimer();
					}
	
				});
				timer.setRepeats(false);
				timer.start();
				break;
			case STATE_EXECUTE:
				timer = new Timer(05, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String myFunction =jsFunction;
					    //System.out.println("STATE_EXECUTE function="+myFunction);
					    /** @j2sNative  
					     *  
					     *  x= eval(myFunction);
					     */
					    state=STATE_WAITING;
						runTimer();
					}
			
				});
				timer.setRepeats(false);
				timer.start();
				break;
			}	
	}

  /**
   * Counts the number of applets on a page.
   *
   * @param func
   * @param vars
   *
   * @return
   */
  public int getAppletCount() {
    if(firstTime) return 0;
    else return super.getAppletCount();
  }


    /**
     * Destroy all threads and cleanup the applet.
     */
    public void destroy(){
      appletRunning = false;
      appletRunning = false;
      createThread=null;
      super.destroy();
    }


  /**
   * Get the applet information.
   *
   */
    public String getAppletInfo() {
        return "Physlet data listener for JScript.  by Wolfgang Christian";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        return null;
    }

    /**
     * Set default values and deletes all data connections.
     */

    public void setDefault() {
      Create temp = createThread;
      Dispatcher temp2 = dispatcherThread;
      if (temp != null) { // stop the old threads if they exisit
        temp.shouldRun = false;
      }
      if (temp2 != null) { // stop the old threads if they exists
        temp2.shouldRun = false;
      }
      deleteDataConnections(); // we are going to delete all the things so we might as well kill the connections too.
    }

    /**
     * Change the javascript function that should be called whenever data changes in a data source.
     *
     * @param str The javascript function
     */
    public void setJSFunction(String str) {
      Create temp = createThread;
      Dispatcher temp2 = dispatcherThread;
      if (temp != null) { // stop the old threads if they exist
        temp.shouldRun = false;
      }
      if (temp2 != null) { // stop the old threads if they exist
        temp2.shouldRun = false;
      }
      if (appletRunning)
        if(isJS) {
            jsFunction=str;
            if(jsFunction==null || jsFunction.trim().equals("")) return;
            jso = JSObject.getWindow(ScriptListener.this);
        	runTimer();
        }else {
        	createThread = new Create(str);
        }
    }

  /**
   * Enable or disable response to an addData notification from a data source.
   *
   * @param respond true will enable the response
   */
  public void setRespondToAddData(boolean respond){
     respondToAddData=respond;
  }

  /**
   * Enable or disable response to an addDatum notification from a data source.
   *
   * @param respond true will enable the response
   */
  public void setRespondToAddDatum(boolean respond){
     respondToAddDatum=respond;
  }

  /**
   * Enable or disable response to a deleteSeries notification from a data source.
   *
   * @param respond true will enable the response
   */
  public void setRespondToDeleteSeries(boolean respond){
     respondToDeleteSeries=respond;
  }

  /**
   * Enable or disable response to a clearSeries notification from a data source.
   *
   * @param respond true will enable the response
   */
  public void setRespondToClearSeries(boolean respond){
     respondToClearSeries=respond;
  }

  // data listener methods

  /**
   * Set the object's owner.  This method has been disabled since an applet cannot have an owner.
   *
   */
  public void setOwner(SApplet o){;}

  /**
   * Get the object's owner.
   *
   * This method is used by to establish a data connection and should not be called by javascript.
   *
   * @return SApplet  a reference to this applet
   */
  public SApplet getOwner(){return this;}

  public void addDatum(SDataSource s, int id, double x, double y) {
    try {
      synchronized (lock) {
        newdata = true;
        lock.notify();
      }
      state=STATE_EXECUTE;
    }
    catch (Exception ex) {}
  }

  public void addData(SDataSource s,int id, double x[], double y[] ){
      try{
        synchronized (lock) {
          newdata = true;
          lock.notify();
        }
      }catch(Exception ex){}
  }

  public void deleteSeries(int id){
      try{
        synchronized (lock) {
          newdata = true;
          lock.notify();
        }
      }catch(Exception ex){}
  }
  //synchronized
  public void clearSeries(int id){
      try{
        synchronized (lock) {
          newdata = true;
          lock.notify();
        }
      }catch(Exception ex){}
  }

  class Dispatcher extends Thread {
    boolean shouldRun=true;
    String jsFunction;
    JSObject jso;

    Dispatcher(String  str, JSObject obj ){
       jsFunction=str;
       jso=obj;
       if(jso==null || jsFunction==null || jsFunction.trim().equals("")) return;
       setDaemon(true);
       start();
    }

    public void run() {
      while (appletRunning  && shouldRun) {
        synchronized (lock) {
          if (!newdata)
            try {
              lock.wait();
            }catch (InterruptedException ie) {}
            newdata = false;
        }
        if(debugLevel >0)System.out.println("evaluating");
        if(appletRunning && shouldRun) jso.eval(jsFunction);
        try{Thread.sleep(20);} catch(InterruptedException ex){}
      }
    }
  }

  class Create extends Thread {
    boolean shouldRun=true;
    String jsFunction;
    Create(String str) {
      jsFunction=str;
      if(jsFunction==null || jsFunction.trim().equals("")) return;
      setDaemon(true);
      start();
    }

    public void run() {
      JSObject jso = null;
      while((jso==null)&&appletRunning && shouldRun) {
        try {
          Thread.sleep(10);
          jso = JSObject.getWindow(ScriptListener.this);
        } catch(InterruptedException ex) {}
        catch(Exception e) {
          System.out.println("JSObject not created.");
          return;
        }
      }
      // we have a js window object so start thread
      if(debugLevel >0) System.out.println("creating dispatcher");
      if(appletRunning && shouldRun)dispatcherThread = new Dispatcher(jsFunction, jso);
    }
  }


}
