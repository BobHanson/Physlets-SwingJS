package circuitsimulator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.Timer;

import java.lang.Math;
import edu.davidson.tools.*;

/**
 * Main applet to use in exercises, displays the circuit build by a script.
 *
 * @author Toon Van Hoecke
 */
public class Circuit extends edu.davidson.tools.SApplet implements SStepable, Runnable {
	

	  final private static String[] components = {
			  "ameter_"
			  ,"batteryh"
			  ,"btbt"
			  ,"btsv"
			  ,"btmode"
			  ,"bulbh"
			  ,"capacitor_"
			  ,"currentsource_"
			  ,"diode_"
			  ,"fire"
			  ,"igeneral_"
			  ,"inductor_"
			  ,"inductor_1"
			  ,"pot_"
			  ,"resistor_"
			  ,"scope_"
			  ,"sinwave_"
			  ,"source_"
			  ,"source_1"
			  ,"squarewave_"
			  ,"switchh"
			  ,"vgeneral_"
			  ,"vmeter_"
			  ,"wireh"
	 
	  };

	private void loadImages() {
		for (int i = 0; i < components.length; i++) {
			String c = components[i];
			loadImage(c.replace('_','h'));
			loadImage(c.replace('_','v'));
		}
	}

  private static Map<String, Image> htImages = new Hashtable<>();
  private void loadImage(String name) {
	  String filename="";
	  try {
		Image i = htImages.get(name);
		if (i != null) return;
	    filename = imagedir + name + ".gif";
		//System.out.println("loading image " + filename);
		i = SwingJSUtils.getImage(this, filename);
		htImages.put(name,  i);
	  } catch (Throwable t) {
		  try {
				filename = imagedir + name + ".gif";
				Image i = getImage(getDocumentBase(), filename);
				htImages.put(name,  i);
			  } catch (Throwable tt) {
				  System.out.println("Error getting image.  Filename= "+filename);
				  tt.printStackTrace();
			  }
	  }
	}
  
  public Image getCachedImage(String name) {
		return htImages.get(name);
  }

//static boolean isJS = /** @j2sNative true || */ false;
  static public  boolean isJS = true; // for debugging
  Timer timer;
  int sleepTime=100;

  static short      DEBUG_IO   = 1, DEBUG_NUM = 2;

  /**
   * <p>Number of time steps. </p>
   *
   * The whole time interval is determined by numberofdt times dt.
   */
  public int        numberofdt = 400;

  /**
   * time step width in the calculation of the V and I functions.
   */
  public double     dt         = 0.001;

  /**
   * number of calculations (dt time steps) in one clock step
   */
  public int        noc        = 1;

  /**
   * frames (clock steps) per second
   */
  public double     fps        = 10.0;

  /**
   * Real time for the circuit when the clock is running
   */
  public double     realt      = 0.0;
  double[][]        x;
  int               interGrid = 54;
  Dimension         gridZone;
  Thread            runner;
  String            localization;
  CircuitProperties cirProp = new CircuitProperties(new CircuitProperties());

  /**
   * Circuit calculation object
   */
  CircuitGrid       cirgrid = new CircuitGrid(2, 2, this);

  /**
   * Circuit visualization object
   */
  CircuitCanvas     circanvas;
//  private URL       imagebase; BH unused

  /** Field imagedir           */
  public static String     imagedir    = "";
  boolean           parsed      = false;
  boolean           showCurrent = false;
  static boolean    DEBUG       = false;

  /**
   * Counts the number of applets on a page.
   *
   *
   * @return
   */
  public int getAppletCount() {
    if(firstTime) {
      return 0;
    } else {
      return super.getAppletCount();
    }
  }

  /**
   * <p>Start is called by the browser whenever the html page is entered. DO NOT SCRIPT.</p>
   *
   * <p>Since start runs after to that ALL html elements have been loaded, it is a good place to do initialization.
   * This version of start is used for testing only since we can't script fron the JDK.</p>
   * Exclude the javadoc because this method should not be scripted.
 * @y.exclude
   */
  public void start() {  // this is called after when the html page has loaded.  Use this function to simulate scripting.
    super.start();
    if(firstTime) {
      firstTime = false;
    }
    if(DEBUG) {
      build();
      clock.startClock();
    }
    if(runner == null) {
      runner = new Thread(this);
      if(isJS) {
    	 runTimer();
      }else {
    	  runner.start();
      }
    }
  }
  
  public void runTimer() {
		timer = new Timer(sleepTime, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("timer running");
				if(parsed) {
					circanvas.repaint();
				}else {
					parse();
				}
				runTimer();
			}
		});
		timer.setRepeats(false);
		timer.start();
  }

  /**
 * Exclude the javadoc because this method should not be scripted.
 * @y.exclude
     */
  public void run() {
    if(Circuit.isJS) {
    	System.err.println("Error.  Thread should not start when using JavaScript.");
    	return;
    }
    while(runner==Thread.currentThread()) {
      if(parsed) {
        circanvas.repaint();
      }
      try {
        Thread.sleep(sleepTime);
      } catch(InterruptedException e) {}
    }
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void stop() {
    runner = null;
    super.stop();
  }

  /**
   * Method reset
   *
   */
  public void reset() {
    super.reset();
    clearAllData();
    clock.setTime(0);
    cirgrid.reset();
  }

  /**
   * <p>The constructor.  DO NO SCRIPT!</p>
   *
   * <p>Register the applet as a clock listener so that we can step the solution in time.</p>
   *
   */
  public Circuit() {
    clock.setDt(dt * noc);
    clock.setFPS(fps);
    clock.addClockListener(this);
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void init() {
    initResources(null);
    // BH note: This is not the final size of the applet,
    // as it has not been packed yet.
    gridZone = new Dimension(getSize());
    String s = getParameter("debugLevel");
    if(s != null) {
      debugLevel = Integer.parseInt(s);
    }
    s = getParameter("intergrid");
    if(s == null) {
      interGrid = 54;
    } else {
      interGrid = Integer.parseInt(s);
    }
    if(interGrid < 54) {
      interGrid = 54;  // interGrid can not be smaller than 54 !
      System.out.println("interGrid = 54, because it can not be smaller");
    }
    s = getParameter("numberofdt");
    if(s == null) {
      numberofdt = 10;
    } else {
      numberofdt = Integer.parseInt(s);
    }
    s = getParameter("dt");
    if(s == null) {
      dt = 0.001;
    } else {
      dt = (Double.valueOf(s)).doubleValue();
    }
    s = getParameter("noc");
    if(s == null) {
      noc = 1;
    } else {
      noc = Integer.parseInt(s);
    }
    if(numberofdt < noc) {
      numberofdt = noc;  // numberofdt can not be smaller than noc !
      System.out.println("numberofdt = noc, because it can not be smaller");
    }
    s = getParameter("fps");
    if(s == null) {
      fps = 1.0;
    } else {
      fps = (Double.valueOf(s)).doubleValue();
    }
    resetTiming();
    s = getParameter("localization");
    if(s == null) {
      s = getParameter("Resources");
    }
    if(s != null) {
      localization = "" + s;
      readCircuitProperties();
    }
    s = getParameter("imagedir", "circuitimages");
	imagedir = getDocumentPath() + s + (s.endsWith("/") ? "" : "/");
//    try {
//      imagebase = new URL(getCodeBase().toString() + imagedir);
//    } catch(MalformedURLException e) {
//      System.out.println("Bad URL");
//    }
    System.out.println("code base: "+getCodeBase().toString() );
    System.out.println("doc base: "+getDocumentBase().toString() );
    loadImages();
    circanvas = new CircuitCanvas(this);
    circanvas.setBounds(1, 1, 1, 1);
    add(circanvas);
  }

  /**
   * Reads a circuit properties file.
   *
   */
  void readCircuitProperties() {
    try {
      URL url = new URL(getCodeBase(), localization);
      InputStream is = url.openStream();
      cirProp.load(is);
      if((debugLevel & DEBUG_IO) > 0) {
        System.out.println(cirProp.toString());
      }
    } catch(Exception ex) {
      System.out.println("problem with circuit properties: " + ex.getMessage());
    }
  }

  /**
   * Set the size of the circuit grid. The vertical size is defined by the number of rows
   * and the horizontal size by the number of columns.
   *
   * @param r number of rows
   * @param c number of columns
   */
  public void setGrid(int r, int c) {
    parsed = false;
    circanvas.setBounds(1, 1, 1, 1);
    cirgrid = new CircuitGrid(r, c, this);
    circanvas.reconnect();
  }

  /**
   * Sets the size of the circuit grid. The vertical size is defined by the number of rows
   * and the horizontal size by the number of columns.
   *
   * @param list String : "rows=***,cols=***"
   */
  public void setGrid(String list) {
    list = SUtil.removeWhitespace(list);
    int r = (int) SUtil.getParam(list, "rows=");
    int c = (int) SUtil.getParam(list, "cols=");
    setGrid(r, c);
  }

  /**
   * Calculates all Voltage and Current parameters that are defined after a parse action for a whole time interval.
   */
  public void calculateCircuit() {
    if(parsed) {
      for(int n = 0; n < numberofdt; n++) {
        cirgrid.calculateStep(n * dt);
        for(int i = 0; i < cirgrid.numberOfPars; i++) {
          x[i][n] = cirgrid.y[i];
        }
      }
    } else if((debugLevel & DEBUG_IO) > 0) {
      System.out.println("Circuit not parsed");
    }
  }

  /**
   * The step method that will be called at every clock step.  DO NOT SCRIPT.
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   * @param dt
   * @param time
   */
  public void step(double dt, double time) {
    if(dt==0) return;  // added by W. Christian
    if(parsed) {
      realt = time * this.dt * noc / dt;
      cirgrid.calculateStep(realt);
      this.updateDataConnections();                  //notify all data listeners
      for(int i = 1; i < noc; i++) {
        cirgrid.calculateStep(realt + i * this.dt);  // do noc steps at one time step
      }
    } else if((debugLevel & DEBUG_IO) > 0) {
      System.out.println("Circuit not parsed");
    }
    // added by W. Christian to check for overload!
    boolean change=false;
    for (Enumeration<CircuitElement> e=cirgrid.cirElemList.elements();e.hasMoreElements();) {
      CircuitElement cirelem = e.nextElement();
      if (cirelem.maxCurrentValue <= Math.abs(cirelem.getI()) && !cirelem.overloaded) {
        change=true;
        //System.out.println("overloaded");
        break;
      }
      if (cirelem.maxCurrentValue <= Math.abs(cirelem.getI()) && !cirelem.overloaded) {
        change=true;
        //System.out.println("not overloaded");
        break;
      }
    }
    if(change)circanvas.redraw();
  }

  /**
   * Method setIntergrid
   *
   * @param ig
   */
  public void setIntergrid(int ig) {
    this.interGrid = ig;
    circanvas.reconnect();
  }

// BH unused
//  /**
//   * Gets the image base for the circuit element images.
//   *
//   *
//   * @return
//   */
//  public URL getImageBase() {
//    return imagebase;
//  }

  /**
   * Interprets the whole circuit and translates this into a set of equations.
   * Use this method when the circuit is (re)built or when new elements are added !
   */
  public void parse() {
    if(cirgrid.constructEquationBase() == false) {
      return;
    }
    x = new double[cirgrid.numberOfPars][numberofdt];
    cirgrid.buildEquations();
    parsed = true;
    circanvas.redraw();
  }

  /**
   * Method setDT
   *
   * @param dt
   */
  public void setDT(double dt) {
    this.dt = dt;
    resetTiming();
  }

  /**
   * Sets the number of calculations.
   *
   * @param noc
   */
  public void setNOC(int noc) {
    this.noc = noc;
    resetTiming();
  }

  /**
   * Sets the number to time steps.
   *
   * @param numberofdt
   */
  public void setNumberOfDT(int numberofdt) {
    this.numberofdt = numberofdt;
  }

  /**
   * Method setFPS
   *
   * @param fps
   */
  public void setFPS(double fps) {
    this.fps = fps;
    resetTiming();
  }

  /**
   * Method resetTiming
   *
   */
  public void resetTiming() {
    clock.removeAllClockListeners();
    clock.setDt(dt * noc);
    clock.setFPS(fps);
    clock.addClockListener(this);
    cirgrid.buildEquations();
  }

  /**
   * Enables/disables the display of current arrows.
   * The arrows indicate the chosen current directions in a parsed circuit
   *
   * @param sc 0 disables the display, all other values enable the display
   */
  public void setShowCurrent(int sc) {
    showCurrent = (sc == 0)
                  ? false
                  : true;
  }

  /**
   * @return time array
   */
  public double[] gett() {
    double[] t = new double[numberofdt];
    for(int j = 0; j < numberofdt; j++) {
      t[j] = j * dt;  // time array for the whole time interval
    }
    return t;
  }

  /**
   * @return String representing the element list
   */
  public String getScript() {
    return cirgrid.getcomponentList();
  }

  /**
   * Method getPar
   *
   * @param par
   *
   * @return
   */
  public double[] getPar(int par) {
    return x[par];
  }

  /**
   * Gets the ID of an element to row "R", column "C", direction "to" horizontal ("h") or vertical ("h")
   *
   * @param r row
   * @param c column
   * @param to direction
   *
   * @return
   */
  public int getID(int r, int c, String to) {
    return cirgrid.getCircuitElement(r, c, to).hashCode();
  }

  /**
   * Method getCoupledID
   *
   * @param id
   *
   * @return
   */
  public int getCoupledID(int id) {
    return cirgrid.getCircuitElement(id).getCoupledID();
  }

  /**
   * Read the Voltage curve between two points of the circuit for the whole time interval.
   *
   * @param br row for the negative probe point
   * @param bc column for the negative probe point
   * @param er row for the positive probe point
   * @param ec column for the positive probe point
   *
   * @return
   */
  public double[] getVoltage(int br, int bc, int er, int ec) {
    double[] v = new double[numberofdt];
    int      b = cirgrid.element[br][bc].getVIndex();
    int      e = cirgrid.element[er][ec].getVIndex();
    if((debugLevel & DEBUG_IO) > 0) {
      System.out.println("b,e=" + b + "," + e);
    }
    for(int j = 0; j < numberofdt; j++) {
      v[j] = x[e][j] - x[b][j];
    }
    return v;
  }

  /**
   * Reads the Voltage curve of an object for the whole time interval.
   *
   * @param id the object identifier
   *
   * @return
   */
  public double[] getVoltage(int id) {
    double[]  v  = new double[numberofdt];
    VEquation ve = (cirgrid.getCircuitElement(id)).vequation;
    for(int j = 0; j < numberofdt; j++) {
      v[j] = x[ve.indexV2][j] - x[ve.indexV1][j];
    }
    return v;
  }

  /**
   * Reads the Current curve of an object for the whole time interval.
   *
   * @param id the object identifier
   *
   * @return
   */
  public double[] getCurrent(int id) {
    double[] current = new double[numberofdt];
    int      i       = (cirgrid.getCircuitElement(id)).vequation.indexI1;
    for(int j = 0; j < numberofdt; j++) {
      current[j] = x[cirgrid.numberOfV + i][j];
    }
    return current;
  }

  /**
   * Reads the amplitude of the Voltage curve for the whole time interval.
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getVAmplitude(int id) {
    double    ulimit = -1e20, llimit = 1e20;
    VEquation ve     = (cirgrid.getCircuitElement(id)).vequation;
    for(int j = 0; j < numberofdt; j++) {
      ulimit = Math.max(x[ve.indexV2][j] - x[ve.indexV1][j], ulimit);
      llimit = Math.min(x[ve.indexV2][j] - x[ve.indexV1][j], llimit);
    }
    return (ulimit - llimit) / 2;
  }

  /**
   * Reads the amplitude of the Current curve for the whole time interval.
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getIAmplitude(int id) {
    double ulimit = -1e20, llimit = 1e20;
    int    i      = (cirgrid.getCircuitElement(id)).vequation.indexI1;
    for(int j = 0; j < numberofdt; j++) {
      ulimit = Math.max(x[cirgrid.numberOfV + i][j], ulimit);
      llimit = Math.min(x[cirgrid.numberOfV + i][j], llimit);
    }
    return (ulimit - llimit) / 2;
  }

  /**
   * Reads the time of the maximum Voltage value of an object for a whole time interval
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getVMaxT(int id) {
    int       vMax = 0;
    double    vTemp;
    VEquation ve = (cirgrid.getCircuitElement(id)).vequation;
    for(int j = 1; j < numberofdt; j++) {
      vTemp = x[ve.indexV2][j] - x[ve.indexV1][j];
      vMax  = (vTemp > x[ve.indexV2][vMax] - x[ve.indexV1][vMax])
              ? j
              : vMax;
    }
    return (vMax * dt);
  }

  /**
   * Read the time of the minimum Voltage value of an object for a whole time interval
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getVMinT(int id) {
    int       vMin = 0;
    double    vTemp;
    VEquation ve = (cirgrid.getCircuitElement(id)).vequation;
    for(int j = 1; j < numberofdt; j++) {
      vTemp = x[ve.indexV2][j] - x[ve.indexV1][j];
      vMin  = (vTemp < x[ve.indexV2][vMin] - x[ve.indexV1][vMin])
              ? j
              : vMin;
    }
    return (vMin * dt);
  }

  /**
   * Reads the RMS value of the Voltage curve for the whole time interval.
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getVrms(int id) {
    VEquation ve = (cirgrid.getCircuitElement(id)).vequation;
    double    vtemp, vrms = 0.0;
    for(int j = 0; j < numberofdt; j++) {
      vtemp = x[ve.indexV2][j] - x[ve.indexV1][j];
      vrms  += vtemp * vtemp;
    }
    return Math.sqrt(vrms / numberofdt);
  }

  /**
   * Read the time of the maximum Current value of an object for a whole time interval
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getIMaxT(int id) {
    int vMax = 0;
    int i    = (cirgrid.getCircuitElement(id)).vequation.indexI1;
    for(int j = 1; j < numberofdt; j++) {
      vMax = (x[cirgrid.numberOfV + i][j] > x[cirgrid.numberOfV + i][vMax])
             ? j
             : vMax;
    }
    return (vMax * dt);
  }

  /**
   * Reads the time of the minimum Current value of an object for a whole time interval
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getIMinT(int id) {
    int vMin = 0;
    int i    = (cirgrid.getCircuitElement(id)).vequation.indexI1;
    for(int j = 1; j < numberofdt; j++) {
      vMin = (x[cirgrid.numberOfV + i][j] < x[cirgrid.numberOfV + i][vMin])
             ? j
             : vMin;
    }
    return (vMin * dt);
  }

  /**
   * Read the RMS value of the Current curve for the whole time interval.
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getIrms(int id) {
    double itemp, irms = 0.0;
    int    i = (cirgrid.getCircuitElement(id)).vequation.indexI1;
    for(int j = 0; j < numberofdt; j++) {
      itemp = x[cirgrid.numberOfV + i][j];
      irms  += itemp * itemp;
    }
    return Math.sqrt(irms / numberofdt);
  }

  /**
   * Calculates the mean Voltage level for a whole time interval.
   *
   * @param id the object identifier
   *
   * @return
   */
  public double getDCLevel(int id) {
    double    dcLevel = 0;
    VEquation ve      = (cirgrid.getCircuitElement(id)).vequation;
    for(int j = 1; j < numberofdt; j++) {
      dcLevel += x[ve.indexV2][j] - x[ve.indexV1][j];
    }
    dcLevel = dcLevel / numberofdt;
    return dcLevel;
  }

  /**
   * Adds objects to the circuit.
   *
   * <p>The element objects (wire, resistor, capacitor, inductor, source, general) can be added at the wanted coordinates:</p>
   *  <ul><li>row = row index (0 is the upper point)</li>
   *  <li>col = column index (0 is the most left point)</li>
   *  <li>to =        "h": horizontal direction to the right; "v": vertical direction down</li>
   * </ul></p>
   * <p>The 'source' has additional parameters:
   *  <ul><li>d = 1 (positive part of the source signal down or right)
   *          or -1 (positive part of the source signal up or left)</li>
   *
   *  <li>func = string representing a function of time (parameter t is necessary!)</li>
   *  <li>freq = frequency of periodic signals (in Hz)</li>
   *  <li>r = internal Resistance (in Ohm)</li>
   *                  <p>---- OR ---- Instead of the func-parameter, the following predefined functions can be used:</li>
   *  <li>st = source type :
   *                          <ul>
   *              <li>0 = constant value (starting at phase) with an amplitude of 'amp'</li>
   *                          <li>1 = square-wave (period is determined by 'freq' (frequency in Hz))</li>
   *                          <li>2 = sine</li>
   *                          <li>3 = cosine</li>
   *                      </ul></li>
   *  <li>phase = phase shift of periodic signals 'f(t+phase)' (in seconds)</li>
   *  <li>freq = frequency of periodic signals (in Hz)</li>
   *  <li>amp = amplitude of periodic signals (in V)</li>
   *  </ul>
   * <p>Other objects have following parameters:</p>
   *  <ul><li>resistor: r = resistance (in Ohm)</li>
   *      <li>capacitor: c = capacity (in F)</li>
   *      <li>inductor: l = inductance (in H)</li>
   *      <li>general elements: func = string representing a function of v and i</li>
   *      <li>polarity sensititive elements: d = 1 (positive part of the element down or right)
   *          or -1 (positive part of the element up or left)</li>
   *      <li>bulbs: v = voltage (in V), and w = power (in W)</li>
   *      <li>battery: v = voltage (in V)</li>
   *      <li>switch: open = 1 (true) or 0 (false)</li>
   *      <li>sinWave, squareWave: amp = amplitude of voltage signal (in V)</li>
   *      <li>sinWave: phase = phase shift 'sin(2*pi*freq*(t+phase))' (in seconds)</li>
   *      <li>squareWave: dutyfactor = fraction of a period the signal is up (in V)</li>
   *      <li>currentsource: a = current (in A)</li>
   *  </ul>
   * <p> A probe is set as in following example: <br>
   * addObject("probe","row=1,col=0,row2=3,col2=4");</p>
   * <p>Examples:</p>
   * <ul><li>addObject("resistor","r=1.0E3,row=2,col=0,to=h");</li>
   * <li>addObject("wire","row=1,col=1,to=v");</li>
   * <li>addObject("capacitor","c=1.0E-7,row=0,col=1,to=v");</li>
   * <li>addObject("source","row=0,col=0,d=1,to=v,func=1.0*sin(t*2*pi*f),freq=1000");
   * <br> In this example the f used in the function description represents the frequency</li>
   * </ul>
   * @param name the object to be added
   * @param list a list of settable parameters
   * @return object identifier: the hashCode() or -1 if 'Out of Bounds'
   */
  public int addObject(String name, String list) {
    int      //      type = 0,
    		r = 0, c = 0, d = 0, st = 0;
    String         to, func = "", label = "";
    double         value     = 0.0, amp = 1.0, phase = 0.0, freq = 1.0, duty = 0.5;
//    String         imageName = "";
    CircuitElement cirelem   = null;
    parsed = false;
    name   = name.toLowerCase().trim();
    name   = SUtil.removeWhitespace(name);
    name   = cirProp.getkey(name);
    list   = SUtil.removeWhitespace(list);
    r      = (int) SUtil.getParam(list, "row=");
    c      = (int) SUtil.getParam(list, "col=");
    to     = "" + SUtil.getParamStr(list, "to=");
    if(SUtil.parameterExist(list, "label=")) {
      label += SUtil.getParamStr(list, "label=");
    }
    if(SUtil.parameterExist(list, "d=")) {
      d = (int) SUtil.getParam(list, "d=");
    }
    if(SUtil.parameterExist(list, "r=")) {
      value = SUtil.getParam(list, "r=");
    }
    if(SUtil.parameterExist(list, "c=")) {
      value = SUtil.getParam(list, "c=");
    }
    if(SUtil.parameterExist(list, "l=")) {
      value = SUtil.getParam(list, "l=");
    }
    if(SUtil.parameterExist(list, "func=")) {
      func = "" + SUtil.getParamStr(list, "func=");
    }
    if(SUtil.parameterExist(list, "freq=")) {
      freq = SUtil.getParam(list, "freq=");
    }
//    imageName += name + to;
    if(name.equals("nothing")) {
      cirelem = new Nothing(this, r, c, to);
    } else if(name.equals("wire")) {
      cirelem = new Wire(this, r, c, to);
    } else if(name.equals("source")) {
      if(SUtil.parameterExist(list, "st=")) {
        st    = (int) SUtil.getParam(list, "st=");
        amp   = SUtil.getParam(list, "amp=");
        phase = SUtil.getParam(list, "phase=");
        if(st != 0) {
          freq = SUtil.getParam(list, "freq=");
        }
        cirelem = new Source(this, d, r, c, to, st, amp, phase, freq);
      } else if(SUtil.parameterExist(list, "freq=")) {
        cirelem = new Source(this, d, r, c, to, func, freq);
      } else {
        cirelem = new Source(this, d, r, c, to, func);
      }
      if(SUtil.parameterExist(list, "r=")) {
        ((Source) cirelem).internalResistance = SUtil.getParam(list, "r=");
      }
    } else if(name.equals("battery")) {
      if(SUtil.parameterExist(list, "v=")) {
        value = SUtil.getParam(list, "v=");
      }
      cirelem = new Battery(this, d, r, c, to, value);
      if(SUtil.parameterExist(list, "r=")) {
        ((Battery) cirelem).internalResistance = SUtil.getParam(list, "r=");
      }
    } else if(name.equals("resistor")) {
      cirelem = new Resistor(this, value, r, c, to);
    } else if(name.equals("switch")) {
      boolean o = ((int) SUtil.getParam(list, "open=") == 1)
                  ? true
                  : false;
      cirelem = new Switch(this, o, r, c, to);
    } else if(name.equals("scope")) {
      cirelem = new Scope(this, value, d, r, c, to);
    } else if(name.equals("ameter")) {
      cirelem = new Ameter(this, value, d, r, c, to);
    } else if(name.equals("vmeter")) {
      cirelem = new Vmeter(this, value, d, r, c, to);
    } else if(name.equals("bulb")) {
      double v = SUtil.getParam(list, "v=");
      double w = SUtil.getParam(list, "w=");
      cirelem = new Bulb(this, v, w, r, c, to);
    } else if(name.equals("resistori")) {
      cirelem = new ResistorI(this, value, r, c, to);
    } else if(name.equals("capacitor")) {
      cirelem = new Capacitor(this, value, r, c, to);
    } else if(name.equals("inductor")) {
      cirelem = new Inductor(this, value, r, c, to);
    } else if(name.equals("vgeneral")) {
      cirelem = new VGeneral(this, func, r, c, to);
    } else if(name.equals("igeneral")) {
      cirelem = new IGeneral(this, func, r, c, to);
    } else if(name.equals("diode")) {
      value   = SUtil.getParam(list, "isat=");
      cirelem = new Diode(this, value, d, r, c, to);
    } else if(name.equals("currentsource")) {
      value   = SUtil.getParam(list, "a=");
      cirelem = new CurrentSource(this, value, d, r, c, to);
    } else if(name.equals("sinwave")) {
      if(SUtil.parameterExist(list, "amp=")) {
        amp = SUtil.getParam(list, "amp=");
      }
      if(SUtil.parameterExist(list, "phase=")) {
        phase = SUtil.getParam(list, "phase=");
      }
      cirelem = new SinWave(this, d, r, c, to, amp, phase, freq);
      if(SUtil.parameterExist(list, "r=")) {
        ((SinWave) cirelem).internalResistance = SUtil.getParam(list, "r=");
      }
    } else if(name.equals("squarewave")) {
      if(SUtil.parameterExist(list, "amp=")) {
        amp = SUtil.getParam(list, "amp=");
      }
      if(SUtil.parameterExist(list, "dutyfactor=")) {
        duty = SUtil.getParam(list, "dutyfactor=");
      }
      cirelem = new SquareWave(this, d, r, c, to, amp, duty, freq);
      if(SUtil.parameterExist(list, "r=")) {
        ((SquareWave) cirelem).internalResistance = SUtil.getParam(list, "r=");
      }
    } else if(name.equals("probe")) {
      int    re    = 0, ce = 0;
      String ptype = "voltage";
      if(SUtil.parameterExist(list, "type=")) {
        ptype = "" + SUtil.getParamStr(list, "type=");
      }
      re      = (int) SUtil.getParam(list, "row2=");
      ce      = (int) SUtil.getParam(list, "col2=");
      cirelem = new Probe(this, ptype, r, c, re, ce);
    } else if(name.equals("transformercoil")) {
      int position = 1;
      if(SUtil.parameterExist(list, "position=")) {
        position = (int) SUtil.getParam(list, "position=");
      }
      cirelem = new TransformerCoil(this, value, r, c, to, position);
    } else if(name.equals("transformer")) {
      int    id1, id2;
      double l1 = value, l2, ratio;
      if(SUtil.parameterExist(list, "l1=")) {
        l1 = SUtil.getParam(list, "l1=");
      }
      l2 = l1;
      if(SUtil.parameterExist(list, "l2=")) {
        l2 = SUtil.getParam(list, "l1=");
      }
      if(SUtil.parameterExist(list, "ratio=")) {
        ratio = SUtil.getParam(list, "ratio=");
        l2    = ratio * ratio * l1;
      } else {
        ratio = Math.sqrt(l2 / l1);
      }
      id1 = addObject("transformercoil",
                      "row=" + Integer.toString(r) + ",col=" + Integer.toString(c) + ",to=" + to + ",position=1,l="
                      + Double.toString(l1) + ",ratio=" + Double.toString(ratio));
      int r2 = r, c2 = c;
      if(to.equals("h")) {
        r2++;
      } else {
        c2++;
      }
      id2 = addObject("transformercoil",
                      "row=" + Integer.toString(r2) + ",col=" + Integer.toString(c2) + ",to=" + to + ",position=2,l="
                      + Double.toString(l2) + ",ratio=" + Double.toString(ratio));
      couple(id1, id2);
      return id1;
    }
    if(!label.equals("")) {
      cirelem.setlabel(label);
    }
    if((debugLevel & DEBUG_IO) > 0) {
      System.out.println("element made of type " + cirelem.getMyName() + ", label: " + cirelem.label);
    }
    int hashcode;
    try {
      hashcode = cirgrid.addCircuitElement(cirelem);
    } catch(java.lang.ArrayIndexOutOfBoundsException e) {
      return -1;
    }
    circanvas.redraw();
    return hashcode;
  }

  /**
   * Couples two elements together
   *
   * @param id1
   * @param id2
   *
   * @return
   */
  public boolean couple(int id1, int id2) {
    CircuitElement t1 = cirgrid.getCircuitElement(id1);
    CircuitElement t2 = cirgrid.getCircuitElement(id2);
    if((t1 == null) || (t2 == null)) {
      return false;
    }
    t1.coupledTo(t2);
    t2.coupledTo(t1);
    cirgrid.buildEquations();
    circanvas.redraw();
    if((debugLevel & DEBUG_IO) > 0) {
      System.out.println("elements coupled: " + Integer.toString(id1) + " and " + Integer.toString(id2));
    }
    return true;
  }

  /**
   * Sets properties of an element
   *
   * @param id the object identifier
   * @param s the property list
   *
   * @return
   */
  public boolean set(int id, String s) {
    CircuitElement t = cirgrid.getCircuitElement(id);
    if(t == null) {
      return false;
    }
    t.set(s);
    cirgrid.buildEquations();
    circanvas.redraw();
    return true;
  }

  /**
   * Gets the property list of an element
   *
   * @param id the object identifier
   *
   * @return
   */
  public String get(int id) {
    CircuitElement t = cirgrid.getCircuitElement(id);
    if(t == null) {
      return "";
    }
    return t.get();
  }

  /**
   * Sets the value of a passive element (resistor, capacitor, inductor)
   *
   * @param id the object identifier
   * @param s
   *
   * @return
   */
  public boolean setValue(int id, String s) {
    CircuitElement t = cirgrid.getCircuitElement(id);
    if(t == null) {
      return false;
    }
    t.setvalue(s);
    cirgrid.buildEquations();
    circanvas.redraw();
    return true;
  }

  /**
   * Sets the value for the highest possible current for the component
   *
   * @param id the object identifier
   * @param s
   *
   * @return
   */
  public boolean setMaxCurrentValue(int id, String s) {
    CircuitElement t = cirgrid.getCircuitElement(id);
    if(t == null) {
      return false;
    }
    t.setMaxCurrentValue(s);
    cirgrid.buildEquations();
    circanvas.redraw();
    return true;
  }

  /**
   * Changes the polarity of an element
   *
   * @param id the object identifier
   *
   * @return
   */
  public boolean changePolarity(int id) {
    CircuitElement t = cirgrid.getCircuitElement(id);
    if(t == null) {
      return false;
    }
    t.changePolarity();
    circanvas.redraw();
    return true;
  }

  /**
   * Changes the label of an element
   *
   * @param id the object identifier
   * @param l the new label
   *
   * @return
   */
  public boolean setLabel(int id, String l) {
    CircuitElement t = cirgrid.getCircuitElement(id);
    if(t == null) {
      return false;
    }
    t.setlabel(l);
    circanvas.redraw();
    return true;
  }

  /**
   * Changes the visibility of an element's value or function
   *
   * @param id the object identifier
   * @param status the value is visible if status = true
   *
   * @return
   */
  public boolean setValueVisible(int id, boolean status) {
    CircuitElement t = cirgrid.getCircuitElement(id);
    if(t == null) {
      return false;
    }
    t.setValueVisible(status);
    circanvas.redraw();
    return true;
  }

//BH not used
///**
//* Changes the visibility of the element's default image
//*
//* @param id the object identifier
//* @param status the default image is visible if status = true
//*
//* @return
//*/
//public boolean setImageVisible(int id, boolean status) {
// CircuitElement t = cirgrid.getCircuitElement(id);
// if(t == null) {
//   return false;
// }
// t.setImageVisible(status);
// circanvas.redraw();
// return true;
//}
//
//  /**
//   * Changes the element's default image. If gifname is empty then the default image is not visible.
//   *
//   * @param id the object identifier
//   * @param gifname the name of the image new gif file
//   *
//   * @return
//   */
//  public boolean setImage(int id, String gifname) {
//    CircuitElement t = cirgrid.getCircuitElement(id);
//    if(t == null) {
//      return false;
//    }
//    t.setImage(gifname);
//    circanvas.redraw();
//    return true;
//  }

  /**
   * Changes the numeric format of an element. Use the C-style printf formatting (e.g. "%6.2g")
   *
   * @param id the object identifier
   * @param fstr the format string
   *
   * @return
   */
  public boolean setFormat(int id, String fstr) {
    CircuitElement t = cirgrid.getCircuitElement(id);
    if(t == null) {
      return false;
    }
    t.setFormat(fstr);
    circanvas.redraw();
    return true;
  }

  /**
   * Changes the font style of an element
   *
   * @param id the object identifier
   * @param family The font family: Helvetica, Times.
   * @param style The style: 0=plain, 1=bold.
   * @param size The size of the font.
   *
   * @return
   */
  public boolean setFont(int id, String family, int style, int size) {
    Font           font = new Font(family, style, size);
    CircuitElement t    = cirgrid.getCircuitElement(id);
    if(t == null) {
      return false;
    }
    t.setFont(font);
    circanvas.redraw();
    return true;
  }

  /**
   * Moves an element to row "R", column "C", direction "to" horizontal ("h") or vertical ("h")
   *
   * @param id the object identifier
   * @param r row
   * @param c column
   * @param to direction
   *
   * @return
   */
  public boolean setRCTo(int id, int r, int c, String to) {
    boolean b = cirgrid.moveCircuitElement(id, r, c, to);
    parsed = false;
    circanvas.redraw();
    return b;
  }

  /* ----------------------Circuit Manipulation Methods, DO NOT SCRIPT --------------------*/

  /**
   * Method getComponent
  * Exclude the javadoc because this method should not be scripted.
  * @y.exclude
   * @param posString
   *
   * @return
   */
  CircuitElement getComponent(String posString) {
    int    r = 0, c = 0;
    String p = "";
    r = (int) SUtil.getParam(posString, "row=");
    c = (int) SUtil.getParam(posString, "col=");
    p += SUtil.getParamStr(posString, "to=");
    if((debugLevel & DEBUG_IO) > 0) {
      System.out.println(r + " " + c + " " + p);
    }
    return cirgrid.getCircuitElement(r, c, p);
  }

  /**
   * Method moveComponent
   * Exclude the javadoc because this method should not be scripted.
  * @y.exclude
   * @param cirelem
   * @param posString
   *
   * @return
   */
  boolean moveComponent(CircuitElement cirelem, String posString) {
    if(cirelem == null) {
      return false;  // added by wc
    }
    int     r  = 0, c = 0;
    String  p  = "";
    boolean ok = true;
    r = (int) SUtil.getParam(posString, "row=");
    c = (int) SUtil.getParam(posString, "col=");
    p += SUtil.getParamStr(posString, "to=");
    if((debugLevel & DEBUG_IO) > 0) {
      System.out.println(r + " " + c + " " + p);
    }
    if(setRCTo(cirelem.hashCode(), r, c, p) == false) {
      System.out.println("Moving failed");
      ok = false;
    }
    return ok;
  }

  /**
   * Method removeObject
   *
   * @param id
   *
   * @return
   */
  public boolean removeObject(int id) {
    boolean b = cirgrid.removeCircuitElement(id);
    parsed = false;
    circanvas.redraw();
    return b;
  }

  /* ----------------------End of Circuit Manipulation Methods  ---------------------------*/
  // test routine to create a simple circuit.

  /**
   * Method build
   *
   */
  void build() {
    setGrid(3, 2);
    addObject("wire", "row=0,col=0,to=h");
    addObject("resistor", "r=1.0E3,row=2,col=0,to=h");
    addObject("wire", "row=1,col=1,to=v");
    addObject("capacitor", "c=1.0E-7,row=0,col=1,to=v");
    addObject("wire", "row=1,col=0,to=v");
    addObject("source", "row=1,col=1,to=v,d=1,v=0,func=round(1-(t*1000-floor(t*1000)))");
    parse();
  }
}
