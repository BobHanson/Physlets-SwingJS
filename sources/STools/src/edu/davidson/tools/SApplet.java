//Physlets are small scriptable applets with physics content.
package edu.davidson.tools;

//import java.applet.Applet; // BH
//import a2s.*;
import circuitsimulator.Circuit;

import java.applet.Applet;
import java.awt.Font;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import edu.davidson.display.Format;



/**
 * SApplet is  designed to provide inter-applet communication and common functionality for Physlets.
 *
 * Physlets usually subclass from SApplet in order to access the animation clock, to register
 * data sources and data listeners, and to enable JavaScript for data-connections.
 */
public class SApplet extends Applet {

  /** Field dataSources
   *  @y.exclude
  */
  public static Hashtable dataSources      = new Hashtable(50);  // needs to be created before clock.

  /** Field dataListeners
   *  @y.exclude
   */
  public static Hashtable dataListeners    = new Hashtable(20);
  static Object           runningID        = null;
  /**
   * @y.exclude
   */
  public static int       appletCount      = 0;  // the number of applets that have been started

  // local and master clocks
  // private static SClock masterClock=null;
  private SClock          localClock       = new SClock(this);

  /** Field clock
   * @y.exclude
   */
  public SClock           clock            = localClock;

  /** Field lock
   * @y.exclude
   */
  public BusyFlag         lock             = new BusyFlag();

  /** Field dataConnections
   * @y.exclude
   */
  public Vector           dataConnections  = new Vector();
  protected String        oneShotMsg       = null;
  protected boolean       autoRefresh      = true;
  protected boolean       firstTime        = true;  // is this the first time the applet has run?
  /** Field debugLevel
   * @y.exclude
   */
  public int              debugLevel       = 0;				  // a flag to turn debugging on and off; default = 0
  protected String        formatString     = "%-+6.2g";
  protected Format        formatD          = new Format(formatString);
  protected Format        formatI          = new Format("%-+6d");
  protected boolean       independentClock = false;
  protected boolean       started=false;
  public boolean           destroyed=false;
  protected Crypt         crypt = new Crypt();
  protected boolean isStandalone = false;
  protected Properties    localProperties = new Properties();
  protected OnloadThread   loadThread;
  protected String         onloadStr = "";
  protected String         appletNames = "";
  protected boolean        scriptHasRun=false;

  /** Field staticDebugLevel
   *  @y.exclude
   */
  static public int       staticDebugLevel = 0;                  // a flag to turn debugging on and off.

  /**
   * Constructor SApplet
   *
   * @y.exclude
  */
  public SApplet() {
    //if(masterClock==null) masterClock=new SClock();
  }

  /**
   * @y.exclude
   */
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
        (getParameter(key) != null ? getParameter(key) : def);
  }

  /**
   * @y.exclude
   */
  public void runJavaScript(){
    try { onloadStr = getParameter("OnLoad", ""); } catch (Exception e) { e.printStackTrace(); }
    try { appletNames = getParameter("AppletNames", ""); } catch (Exception e) { e.printStackTrace(); }
    if(!onloadStr.equals("")){
      scriptHasRun=false;
      if(debugLevel>127)System.out.println("starting new OnLoadThread");
      //loadThread = new OnloadThread(this, onloadStr, appletNames);
    } else scriptHasRun=true;  // set to true because there is no script!
  }

	/**
	 * @y.exclude
	 */
	public boolean initResources(String resourceFile) {
		boolean loaded = false;
		try {
			debugLevel = Integer.parseInt(this.getParameter("Debug", "0"));
			staticDebugLevel = debugLevel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String cryptokey = getParameter("Key", "");
			if (!cryptokey.equals(""))
				crypt.setKey(cryptokey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resourceFile == null || resourceFile.equals("")) {
			try {
				resourceFile = getParameter("Resources", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (resourceFile != null && !resourceFile.equals("")) {
			try { // load resources from code base.
				loaded = true;
				localProperties.load(new URL(getCodeBase(), resourceFile).openStream());
			} catch (Exception ex) {
				loaded = false;
			}
			if (!loaded) { // look for resources in document base
				try { // load resources from code base.
					loaded = true;
					localProperties.load(new URL(getDocumentBase(), resourceFile).openStream());
				} catch (Exception ex) {
					loaded = false;
				}
			}
			if (!loaded) { // look for resources in jar file
				try {
					loaded = true;
					String resourcePath = resourceFile;
					if (!resourceFile.startsWith("/"))
						resourcePath = "/" + resourceFile;
					localProperties.load(SApplet.class.getResource(resourcePath).openStream());
				} catch (Exception e) {
					loaded = false;
				}
			}
			if (!loaded) {
				System.out.println("Can't load resource: " + resourceFile);
			} else {
				setResources();
				String cryptokey = localProperties.getProperty("key", "");
				if (!cryptokey.equals(""))
					crypt.setKey(cryptokey);
			}
			loaded = true;
		}
		return loaded;
	}

/**
   * @y.exclude
   */
  protected void setResources(){
      // override this method to set the resource strings within an applet.
  }

  /**
   * @y.exclude
   */
  synchronized public void destroy() {
    destroyed=false;
    autoRefresh=false;
    setAutoRefresh(false);
    if(loadThread!=null)loadThread.stop();
    started=false;
    appletCount=0;
    //System.out.println("Begin Num DataListeners:"+DataListeners.size());
    //System.out.println("Begin Num DataSources:"+DataSources.size());
    deleteDataConnections();
    localClock.panicStopClock();
    //if(masterClock!=null) masterClock.panicStopClock();
    SDataSource   ds = null;
    SDataListener dl = null;
    Object        key;
    Hashtable     ht;
    synchronized(dataSources) {
      ht = (Hashtable) dataSources.clone();
    }
    for(Enumeration e = ht.keys(); e.hasMoreElements(); ) {  // get the keys
      key = e.nextElement();
      ds  = (SDataSource) dataSources.get(key);
      if((ds != null) && (ds.getOwner() == this)) {
        dataSources.remove(key);
      }
    }
    synchronized(dataListeners) {
      ht = (Hashtable) dataListeners.clone();
    }
    for(Enumeration e = ht.keys(); e.hasMoreElements(); ) {  //get the keys
      key = e.nextElement();
      dl  = (SDataListener) dataListeners.get(key);
      if((dl != null) && (dl.getOwner() == this)) {
        dataListeners.remove(key);
      }
    }
    localClock.removeAllClockListeners();
    //     System.out.println("End of destroy # DataListeners:"+dataListeners.size());
    //     System.out.println("End of destroy # DataSources:"+dataSources.size());
    //Thread.currentThread().getThreadGroup().stop();
  }

  private SDataConnection getExistingConnection(int sourceID, int listenerID, int seriesID) {
    return null;
  }  // needs to be written!


  /**
   *   Sends an (x,y) data point to the listener.
   */
  public void sendDataToListener(int listenerID, int sid, double x, double y){
    SDataListener   dl = getDataListener(listenerID);
    if(dl==null){
     System.out.println("DataListener not found in sendData method.");
     return;
    }
    dl.addDatum(clock, sid, x, y);  // the clock is the default data source.
  }

  /**
   *    Have the applet make a new data connection.
   *
   *    @param sourceID    The id of the data source.
   *    @param listenerID  The id of the data listener.   This is usually an applet.
   *    @param seriesID    The id of the series in the data listener.
   *    @param xStr        The function of the data source variables to be plotted on the horizontal axis.
   *    @param xStr        The function of the data source variables to be plotted on the vertical axis.
   * @param yStr
   *
   *    @return SDataConnection The hasCode id of the newly created data connection.
   */
  synchronized public int makeDataConnection(int sourceID, int listenerID, int seriesID, String xStr, String yStr) {
    if(xStr == null || xStr.equals("")) {
      xStr = "0";
    }
    if(yStr == null || yStr.equals("")) {
      yStr = "0";
    }
    if(debugLevel > 7) {
      System.out.println("making connection. SourceID=" + sourceID + "ListenerID=" + listenerID);
    }
    SDataListener   dl = getDataListener(listenerID);
    SDataSource     ds = getDataSource(sourceID);
    SDataConnection dc = null;
    if((ds != null) && (dl != null)) {
      dc = getExistingConnection(sourceID, listenerID, seriesID);
      if(dc == null) {
        if(debugLevel > 7) {
          System.out.println("xStr=" + xStr + " yStr=" + yStr);
        }
        dc = new SDataConnection(ds, dl, seriesID, xStr, yStr);
        dataConnections.addElement(dc);
      } else {
        System.out.println("Warning: Data connection already exists.");
      }
    } else {
      System.out.println("DataConnection not made.  Listener:" + dl + "  Source:" + ds);
      return 0;
    }
    return dc.hashCode();
  }

  /**
   *    Delete all the data that has been sent to the DataConnections.
   *
   */
  public void clearAllData() {
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      SDataConnection dc = (SDataConnection) e.nextElement();
      dc.clearData();
    }
  }

  /**
   *    Delete all the data that has been sent to the DataConnections.
   *
   *    @param id int The hashCode id of the data source.
   */
  public void clearData(int id) {
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      SDataConnection dc = (SDataConnection) e.nextElement();
      if(dc.getDataSource().hashCode() == id) {
        dc.clearData();
      }
    }
  }

  /**
   *    Have a single data source update its data connections.
   *
   *    @param id  The integer id of the data source.
   */
  public void updateDataConnection(int id) {
    if(destroyed) return;
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      SDataConnection dc = (SDataConnection) e.nextElement();
      if(dc.getDataSource().hashCode() == id) {
        if(destroyed) return;
        dc.registerDatum();
      }
    }
  }

  /**
   *    Have all data sources update their data connections.
   *
   */
  public void updateDataConnections() {
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      SDataConnection dc = (SDataConnection) e.nextElement();
      if(dc.getDataSource() != this.clock) {  // The clock will update its own connections after the tick.
        if(destroyed) return;
        dc.registerDatum();
      }
    }
  }

  /**
   *    Break all data connections for this applet.
   *
   */
  synchronized public void deleteDataConnections() {
    dataConnections.removeAllElements();
  }

  /**
   *    Break the data connection identified by the id.
   *
   *    @param id       The data connection id.
   */
   synchronized public void deleteDataConnection(int id) {  // loop throught the vector to find the right one to delete.
    SDataConnection dc = null;
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      dc = (SDataConnection) e.nextElement();
      if(id == dc.hashCode()) {
        dataConnections.removeElement(dc);
      }
    }
  }

  /**
   *
   *  Find the data connection with the given ID.
   *
   *  @pram id  SDataSource
   *  @returns  SDataConnection
   *
   */
  private SDataConnection getDataConnection(int id) {
    SDataConnection dc = null;
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      dc = (SDataConnection) e.nextElement();
      if(id == dc.hashCode()) {
        return dc;
      }
    }
    return null;
  }

  /**
   *
   *  Find the first data connection in the list that has a specific data source.
   *
   *  @pram ds  SDataSource
   *  @returns  SDataConnection
   *
   * @param ds
   *
   * @return
   * @y.exclude
   */
  public SDataConnection getDataConnectionFromDS(SDataSource ds) {
    SDataConnection dc = null;
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      dc = (SDataConnection) e.nextElement();
      if(ds == dc.getDataSource()) {
        return dc;
      }
    }
    return null;
  }

  /**
   *
   *  Return the data as a string.
   *
   *  @pram ds  int  The id of the data source.
   *  @returns  string  The variables and the data.
   *
   * @param id
   *
   * @return
   */
  public String getDataFromDS(int id) {
    Format      format = new Format("%10.5g");
    SDataSource ds     = getDataSource(id);
    String[]    vStr   = ds.getVarStrings();
    double[][]  v      = ds.getVariables();
    if((v == null) || (vStr == null)) {
      return "";
    }
    int          n      = vStr.length;                              // number of variables
    int          numPts = v.length;
    StringBuffer sb     = new StringBuffer((n + 1) * numPts * 10);  //n rough guess.  Assume the average double has over 30 characters.
    for(int i = 0; i < n; i++) {
      sb.append(vStr[i]);
      for(int j = 0; j < numPts; j++) {
        sb.append(",");
        sb.append(format.form(v[j][i]));
      }
      sb.append('\n');
    }
    return sb.toString();
  }

  /**
   *
   *  Find the first data connection in the list that has a specific data listener.
   *
   *  @pram dl  SDataListener
   *  @returns  SDataConnection
   *
   * @param dl
   *
   * @return
   * @y.exclude
   */
  public SDataConnection getDataConnectionFromDL(SDataListener dl) {
    SDataConnection dc = null;
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      dc = (SDataConnection) e.nextElement();
      if(dl == dc.getDataListener()) {
        return dc;
      }
    }
    return null;
  }

  /**
   * Check to see if a function is syntactically correct.
   *
   *
   * @param func
   * @param vars
   *
   * @return
   */
  public final boolean isValidFunction(String func, String vars) {
    return edu.davidson.tools.SUtil.isValidFunction(func, vars);
  }


  /**
   * Counts the number of applets on a page.
   *
   * @param func
   * @param vars
   *
   * @return the count
   * @y.exclude
   */
  public int getAppletCount() {
    int n=0;
    if(appletCount<=0) return 0;
    if(appletCount==1) return 1;
    try{
      Enumeration en = getAppletContext().getApplets();
      while (en.hasMoreElements()) {
        n++;
        en.nextElement();
      }
    } catch(Exception ex){System.err.println("Error while counting applets: "+ex.getMessage()); n=-1;}
    return n;
  }

  /**
   * Gets the name that was used to embed this applet in the html page.
   *
   * @return the name
 */
  public String getAppletName() {
    if(appletCount<=0) return "";
    return getParameter("name");
  }

/**
 * Checks a list of names to determine if the applets in this list have started.
 *
 * @param names a comma delimited list of applet names
 *
 * @return true if the names match
 */
  public boolean checkAppletNames(String names) {
    if(debugLevel>127)System.out.println("checkAppletNames:"+names);
    if(!isActive() || appletCount<=0 ) return false;
    names=names.trim();
    if(appletCount==1  && getAppletName().equals(names)) return true; // there is only one applet and this is the one
    Vector appletNames  = new Vector();
    try{
      Enumeration en = getAppletContext().getApplets();
      while (en.hasMoreElements()) {
        Applet applet= (Applet) en.nextElement();
        if(applet instanceof SApplet ){
          appletNames.addElement(((SApplet) applet).getAppletName());
          if(debugLevel > 127) {
            System.out.println("Applet named: "+((SApplet) applet).getAppletName());
          }
        }
      }
      } catch(Exception ex){
        //System.err.println("Error reading applet names: "+ex.getMessage());
        return false;
      }
      StringTokenizer ptokens=new StringTokenizer(names,",;");
      for(int i=0, n=ptokens.countTokens(); i<n; i++){
        if(!appletNames.contains(ptokens.nextToken().trim()))return false;
      }
      return true;
  }

  /**
   *
   *  Removes dataConnections if either the source or the listener no longer exist.
   *
   */
  public final void cleanupDataConnections() {
    SDataConnection dc = null;
    SDataSource     ds = null;
    SDataListener   dl = null;
    for(Enumeration e = dataConnections.elements(); e.hasMoreElements(); ) {
      dc = (SDataConnection) e.nextElement();
      dl = dc.getDataListener();
      ds = dc.getDataSource();
      if(!dataListeners.contains(dl) ||!dataSources.contains(ds)) {  // either the source or the listener is missing
        dataConnections.removeElement(dc);
      }
    }
  }

  /**
   * Gets the applet that should be running.
   *
   * Always returns this applet if the independentClock is true.
   *
   * @return the SApplet that is currently running the clock
   * @y.exclude
   */
  public final Object getRunningID() {
    if(independentClock) {
      return this;
    }
    return runningID;
  }

  /**
   * Method setRunningID
   *
   *
   * @param id
   * @y.exclude
   */
  public final void setRunningID(Object id) {
    runningID = id;
  }

  /**
   * Method setRunningID
   * @y.exclude
   */
  public final void setRunningID() {
    runningID = this;
  }

  /**
   *  Get the debug level.
   *
   *  A debug level of 1 turns off all debugging.  Higher levels produce more error messages.
   *
   *  @return the debug level
   */
  public final int getDebugLevel() {
    return debugLevel;
  }

  /**
   * Set the debug leve.
   *
   * A debug level of 1 turns off all debugging.  Higher levels produce more error messages.
   *
   * @param level
   */
  public final void setDebugLevel(int level) {
    debugLevel       = level;
    staticDebugLevel = level;
  }

  /**
   * Method object identifier.
   *
   * The id is usually the hash code of the object.
   *
   *
   * @return the id
   */
  public final int getID() {
    return hashCode();
  }

  /**
   *   Have the data connection smooth the data before it is sent to the data listener.
   *
   *   @param id int  The id of of the data connection.
   *   @param num int The number of data points to smooth.
   *
   *   @return boolean Return true if data connection found.
   */
  public final boolean setConnectionSmoothing(int id, int num) {
    SDataConnection dc = this.getDataConnection(id);
    if(dc == null) {
      System.out.println("Error: DataConnection not found in setConnectionSmoothing.");
      return false;
    }
    dc.setSmooth(num);
    return true;
  }

  /**
   *   Have the data connection reject data unless xmin <= x <= xmax.
   *
   *   @param id    the id of of the data connection
   *   @param xmin  the minimum value to pass
   *   @param xmax  the maximum value to pass
   *
   *   @return boolean  true if data connection found.
   */
  public final boolean setConnectionWindowX(int id, double xmin, double xmax) {
    SDataConnection dc = this.getDataConnection(id);
    if(dc == null) {
      System.out.println("Error: DataConnection not found in setConnectionWindowX.");
      return false;
    }
    dc.setWindowX(xmin, xmax);
    return true;
  }

  /**
   *   Have the data connection reject data unless ymin <= y <= ymax.
   *
   *   @param id    the id of of the data connection
   *   @param ymin  the minimum value to pass
   *   @param ymax  the maximum value to pass
   *
   *   @return boolean  true if data connection found.
   */
  public final boolean setConnectionWindowY(int id, double ymin, double ymax) {
    SDataConnection dc = this.getDataConnection(id);
    if(dc == null) {
      System.out.println("Error: DataConnection not found in setConnectionWindowY.");
      return false;
    }
    dc.setWindowY(ymin, ymax);
    return true;
  }

  /**
   *   Have the data connection skip every num-1 data points.
   * @param id
   * @param num
   *
   * @return
   */
  public final boolean setConnectionStride(int id, int num) {
    SDataConnection dc = this.getDataConnection(id);
    if(dc == null) {
      System.out.println("Error: DataConnection not found in setConnectionStride.");
      return false;
    }
    dc.setStride(num);
    return true;
  }

  /**
   *   Block data from passing through the data connection.
   * @param id
   * @param block
   *
   * @return
   */
  public final boolean setConnectionBlock(int id, boolean block) {
    SDataConnection dc = this.getDataConnection(id);
    if(dc == null) {
      System.out.println("Error: DataConnection not found in setConnectionBlock.");
      return false;
    }
    dc.block = block;
    return true;
  }

  /**
   *   Set the data source for the data connection.
   * @param id
   *
   * @return
   */
  public final boolean setConnectionSource(int id) {
    SDataConnection dc = this.getDataConnection(id);
    if(dc == null) {
      System.out.println("Error: DataConnection not found in setConnectionSource.");
      return false;
    }
    dc.ds = getDataSource(id);
    return true;
  }

  /**
   *   Set the data listener for the data connection.
   * @param id
   *
   * @return
   */
  public final boolean setConnectionListener(int id) {
    SDataConnection dc = this.getDataConnection(id);
    if(dc == null) {
      System.out.println("Error: DataConnection not found in setConnectionSource.");
      return false;
    }
    dc.dl = getDataListener(id);
    return true;
  }

  /**
   *   Repaint whenever the system parameters are changed.
   *
   *
   * @param ar
   */
  public void setAutoRefresh(boolean ar) {
    autoRefresh = ar;
  }

  /**
   *  Convert a number to a string.  Use Unix printf format convention.
   *
   *
   * @param val  the number to format
   * @param chop return zero if abs(val) < chop.
   * @param str the new format string
   *
   * @return the string
   */
  public String formatValue(double val, double chop, String str) {
    if(str==null)str="";
    str.trim();
    if(!str.equals("") &&!formatString.equals(str)) {
      // new format if the formatString has changed
      formatString = str;
      formatD      = new Format(formatString);
    }
    if(Math.abs(val) < chop) {
      return "0";
    }
    if(Math.abs(val - (int) val) <= chop) {
      return formatI.form((int) val);
    }
    return formatD.form(val);
  }

  /**
   * @y.exclude
   * @param f Font
   */
  public void setFont(Font f){
    // bug fix for Mozilla and Java 1.4 plug-in
    if(started && debugLevel>0 && !Font.class.isInstance(f)){
      f=super.getFont();
      System.out.println("SCRIPT ERROR: Java 1.4 plug-in does not support method overloading.");
      System.out.println("Method: setFont");
      System.out.println("Alternate method: setObjectFont.");
      System.out.println("");
    }
    super.setFont(f);
  }

  /**
   * @y.exclude
   * @return int
   */
  public int getX(){
    // bug fix for Mozilla and Java 1.4 plug-in
    if(started && debugLevel>0){
      System.out.println("SCRIPT ERROR: Java 1.4 plug-in does not support method overloading.");
      System.out.println("Method: getX");
      System.out.println("Alternate method: getXPos.");
      System.out.println("");
    }
    return super.getX();
  }

  /**
   * @y.exclude
   * @return int
   */
  public int getY(){
    // bug fix for Mozilla and Java 1.4 plug-in
    if(started && debugLevel>0){
      System.out.println("SCRIPT ERROR: Java 1.4 plug-in does not support method overloading.");
      System.out.println("Method: getY");
      System.out.println("Alternate method: getYPos.");
      System.out.println("");
    }
    return super.getY();
  }

  // clock stuff

  /**
   *   Start clock.
   */
  public void forward() {
    setRunningID();
    clock.dt = Math.abs(clock.dt);
    clock.startClock();
  }

  /**
   *   Stop the clock.
   */
  public void reverse() {
    setRunningID();
    clock.dt = -Math.abs(clock.dt);
    clock.startClock();
  }

  /**
   *   Pause the clock.
   */
  public void pause() {
    clock.stopClock();
  }

  /**
   *   Reset the clock to time=0.
   */
  public void reset() {
    pause();
    clock.setTime(0);
  }

  /**
   *   Reset the clock to time=0.
   */
  public void setDefault() {
    pause();
    clock.setTime(0);
    this.deleteDataConnections();
  }

  /**
   *   Set the time increment.
   * @param dt
   */
  public void setDt(double dt) {
    clock.setDt(dt);
  }

  /**
   *   Set the frames per second.
   * @param fps
   */
  public void setFPS(double fps) {
    clock.setFPS(fps);
  }

  /**
   *   Set the time increment.
   */
  public void setTimeContinuous() {
    clock.setContinuous();
  }

  /**
   *   Get the clock id for use as a data source.
   *
   * @return
   */
  public final int getClockID() {
    return clock.hashCode();
  }

  /**
   * Sets the clock so that this applet does not stop running when another applet starts running.
   *
   * @param independent true will make clock independent; false otherwise
   */
  public void setIndependentClock(boolean independent) {
    independentClock = independent;
  }

  /**
   *  Sets the animatation clock to the clock in another applet.
   *
   *
   * @param id the clock in another applet.
   *
   * @return true if successful, false otherwise
   */
  public final boolean setExternalClock(int id) {
    SDataSource ds = SApplet.getDataSource(id);
    if(ds == null) {
      return false;
    }
    SClock newClock = null;
    if(ds instanceof SClock) {
      newClock = (SClock) ds;
    } else {
      return false;
    }
    clock.stopClock();
    newClock.stopClock();
    for(Enumeration e = clock.clockListeners.elements(); e.hasMoreElements(); ) {
      SStepable clockListener = (SStepable) e.nextElement();
      newClock.addClockListener(clockListener);
    }
    this.removeDataSource(clock.getID());
    clock = newClock;
    return true;
  }

  /**
   *
   * Step the clock by one tick, dt.
   *
   *
   */
  public void stepClock() {
    clock.doStep();
  }

  /**
   *  Step the clock forward by dt.
   *
   */
  public void stepTimeForward() {
    clock.setDt(Math.abs(clock.getDt()));
    clock.doStep();
  }

  /**
   *  Step the clock back by dt.
   *
   */
  public void stepTimeBack() {
    clock.setDt(-Math.abs(clock.getDt()));
    clock.doStep();
  }

  /**
   *  Step the clock by dt.
   *
   */
  public void stepTime() {
    clock.doStep();
  }

  /**
   *
   *  Sets applet to use the master clock shared by all applets.  This process cannot be reversed!
   *
   *
   *
   * public synchronized void useMasterClock(){
   *    Object cl;
   *    if(masterClock==null) masterClock=new SClock();
   *    localClock.stopClock();
   *    removeDataSource( localClock.hashCode());
   *    clock=masterClock;
   *    for(Enumeration e=localClock.clockListeners.elements() ; e.hasMoreElements(); ){
   *         cl=(SStepable)e.nextElement();
   *         masterClock.addClockListener( (SStepable)cl);
   *    }
   *    localClock.removeAllClockListeners();
   * }
   *
   * @return
   */

  /**
   *
   * Get the time from the animation clock.
   *
   * @return double the time
   *
   */
  public double getClockTime() {
    return clock.getTime();
  }

  /**
   *
   *  Set the clock time.
   *
   *  @param t The clock time.
   *
   */
  public void setClockTime(double t) {
    clock.setTime(t);
  }

  /**
   *
   *  Set the clock to run from minTime to maxTime and stop.
   *
   *  @param maxTime The maximum time.
   * @param msg
   *
   */
  public void setTimeOneShot(double maxTime, String msg) {
    oneShotMsg = msg;
    clock.setOneShot(0, maxTime);
    clock.setTime(0);
  }

  /**
   *
   *  Set the clock to run from minTime to maxTime and stop.
   *
   *  @param minTime The maximum time.
   *  @param maxTime The maximum time.
   *
   */
  public void setClockOneShot(double minTime, double maxTime) {
    clock.setOneShot(minTime, maxTime);
    clock.setTime(minTime);
  }

  /**
   *
   *  Set the clock to run continously.
   *
   *  @see  #setClockOneShot
   *  @see  #setClockCycle
   *
   */
  public void setClockContinous() {
    clock.oneShot = false;
    clock.cycle   = false;
  }

  /**
   *
   *  Sets the encription key.
   *  @y.exclude
   */
  public void setKey(String str) {
    crypt.setKey(str);
  }

  /**
   * @y.exclude
   */
  public String decrypt(String str) {
    return crypt.decrypt(str);
  }

  /**
   *
   *  Set the clock to cycle between the 0 and maxTime.
   *
   *  @param maxTime The maximum time.
   *
   */
  public void setTimeCycle(double maxTime) {
    this.clearAllData();
    clock.setCycle(0, maxTime);
    clock.setTime(0);
  }

  /**
   *
   *  Set the clock to cycle between the minTime and maxTime.
   *
   *  @param minTime The maximum time.
   *  @param maxTime The maximum time.
   *
   */
  public void setClockCycle(double minTime, double maxTime) {
    this.clearAllData();
    clock.setCycle(minTime, maxTime);
    clock.setTime(minTime);
  }

  /**
   *  Invoked by the browser when the html page is entered.
   * @y.exclude
   */
  public void start() {
    //super.start();  // no need to call because this is empty in the Applet class
    started=true;
    appletCount++;  // count the number of applets that have started
    if(!scriptHasRun)runJavaScript();
  }

  /**
   *  Invoked by the browser when the html page is exited.
   */
  public void stop() {
    if(loadThread!=null){
      loadThread.halt=true;
    }
    super.stop();
    started=false;
    appletCount=0;
  }

  /**
   *  Starts the clock
   */
  public void startClock() {
    setRunningID();
    clock.startClock();
  }

  /**
   *  @y.exclude
   */
  public boolean isAutoRefresh() {
    return autoRefresh;
  }

  /**
   *   Test to see if the clock is running.
   *
   *   @return true if clock is running.
   */
  public boolean isClockRunning() {
    return clock.isRunning();
  }

  /**
   *
   *  Override this method if special action needs to be taken when the clock is stopped by the SApplet.
   *
   *
   */
  protected void stoppingClock() {}

  /**
   *
   *  Override this method if special action needs to be taken when the clock is cycled by the SApplet.
   *
   *
   */
  protected void cyclingClock() {}

  /**
   *
   * Override this method if special action needs to be taken when the clock is paused because another SApplet begins to run.
   *
   *
   */
  protected void pausingClock() {}

  /**
   *
   * Stop the clock
   *
   *
   */
  public void stopClock() {
    clock.stopClock();
  }

  // data source stuff

  /**
   *
   *  Add a data source to the list.
   *
   *  @param ds  The data source object.
   *
   */
  synchronized static public final void addDataSource(Object ds) {
    if((ds instanceof SDataSource) &&!dataSources.contains(ds)) {
      dataSources.put(Integer.toString(ds.hashCode()), ds);
      if(staticDebugLevel > 8) {
        System.out.println("data source added: " + ds.hashCode());
      }
    } else {
      System.out.println("Error: ds is not a DataSource in SApplet.addDataSource:" + ds.hashCode());
    }
  }

  /**
   *
   *  Remove the data source with the given id.  Look for and remove data connections to this data source.
   *
   *  @param id  The integer id that identifies the data source.
   *
   */
  public final void removeDataSource(int id) {
    SDataSource     ds = getDataSource(id);
    SDataConnection dc = null;
    do {  // first see if any data connections use this source.
      dc = getDataConnectionFromDS(ds);
      if(dc != null) {
        dataConnections.removeElement(dc);
      }
    } while(dc != null);
    dataSources.remove(Integer.toString(id));
  }

  /**
   *
   *  Find the data source with the given key.  The key is the String representation of the hash code.
   *
   *  @param key  The string id that identifies the data source.
   *
   *
   * @return
   */
  static public final SDataSource getDataSource(String key) {
    return (SDataSource) dataSources.get(key);
  }

  /**
   *
   *  Find the data source with the given id.
   *
   *  @param id  The integer id that identifies the data source.
   *
   *
   * @return
   */
  static public final SDataSource getDataSource(int id) {
    return (SDataSource) dataSources.get(Integer.toString(id));
  }

  /**
   *
   *  Return a comma delimited list of the data source variables.
   *
   *  @param id  the integer identifier of the data source.
   *
   *  @return string a list of variables
   *
   */
  public String getSourceVariables(int id) {
    String      str = "";
    SDataSource ds  = (SDataSource) dataSources.get(Integer.toString(id));
    if(ds == null) {
      return "Data source not found.";
    }
    String[] varStr = ds.getVarStrings();
    if(varStr == null) {
      return "Variables not found.";
    }
    int num = varStr.length;
    for(int i = 0; i < num - 1; i++) {
      str = str + varStr[i] + ", ";
    }
    str = str + varStr[num - 1];
    return str;
  }



  /**
   *  Returns a comma delimited list of the datasource data.
   *
   *  @param id  the integer identifier of the data source.
   *  @param vStr
   *
   *  @return string a list of doubles
   *
   */
  public String getSourceData(int id, String vStr) {
    int         index = -1;
    SDataSource ds    = (SDataSource) dataSources.get(Integer.toString(id));
    if(ds == null) {
      return "Data source not found.";
    }
    String[] varStr = ds.getVarStrings();
    if(varStr == null) {
      return "Variables not found.";
    }
    int num = varStr.length;
    for(int i = 0; i < num; i++) {
      if(varStr[i].equals(vStr)) {
        index = i;
        break;
      }
    }
    if(index == -1) {
      return "Data source variable not found.";
    }
    double[][] vals = ds.getVariables();
    if(vals == null) {
      return "Values not found.";
    }
    int          len = vals.length;
    StringBuffer str = new StringBuffer(len * 10);
    for(int j = 0; j < len - 1; j++) {
      str = str.append(vals[j][index]);
      str.append(", ");
    }
    if(len <= 0) {
      return "";
    }
    str.append(vals[len - 1][index]);
    return str.toString();
  }

  // data listener stuff

  /**
   * Method removeDataListener
   *
   *
   * @param id the hash code of the data listener
   *
   */
  public final void removeDataListener(int id) {
    SDataListener   dl = getDataListener(id);
    SDataConnection dc = null;
    do {
      dc = getDataConnectionFromDL(dl);
      if(dc != null) {
        dataConnections.removeElement(dc);
      }
    } while(dc != null);
    dataListeners.remove(Integer.toString(id));
  }

  /**
   * Method addDataListener
   *
   *
   * @param dl
   *
   */
  synchronized static public final void addDataListener(Object dl) {
    if(dl instanceof SDataListener) {
      dataListeners.put(Integer.toString(dl.hashCode()), dl);
      if(staticDebugLevel > 8) {
        System.out.println("data listener added: " + dl.hashCode());
      }
    } else {
      System.out.println("Error: dl is not a DataListener in SApplet.addDataListener:" + dl.hashCode());
    }
  }

  /**
   * Gets the data listener from the key.
   *
   * @param key
   *
   * @return the data listener
   *
   */
  static public final SDataListener getDataListener(String key) {
    return (SDataListener) dataListeners.get(key);
  }

  /**
   * Gets the data listener from the integer id.
   *
   * @param id
   *
   * @return the data listener
   * @y.exclude
   */
  static public final SDataListener getDataListener(int id) {
    return (SDataListener) dataListeners.get(Integer.toString(id));
  }
}
