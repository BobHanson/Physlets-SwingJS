// JBuilder version 1.2
package ripple;

import java.util.Enumeration;
import java.awt.*;


import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;
import java.util.Vector;

import javax.swing.JFrame;

/**
 * Class Ripple
 */
public class Ripple extends SApplet implements Runnable {
  static boolean isJS = /** @j2sNative true || */ false;
  String               button_start        = "Forward";
  String               button_stop         = "Stop";
  String               button_forward      = "Step>>";
  String               button_back         = "<<Step";
  String               button_edit         = "Edit";
  String               button_cancel       = "Cancel";
  String               button_calculate    = "Calculate";
  String               button_add          = "Add";
  String               button_delete       = "Delete";
  String               label_stopped       = "Stopped";
  String               label_drag          = "Click-Drag any source.";
  String               label_wait          = "Please Wait...";
  String               label_wavelength    = "Wavelength=";
  String               label_nosource      = "No Sources.";
  String               label_running       = "running";
  String               label_frame         = "frame:";
  String               label_clickdrag     = "Click and drag.";
  boolean              m_fStandAlone       = false;
  //boolean isStandalone = false;
  private double       zscale              = 0;
  private int          m_numSources        = 2;
  private int          m_pixPerUnit        = 20;
  private double       m_wavelength        = 2.0;
  private boolean      m_canDrag           = true;
  private boolean      m_showControls      = true;
  private boolean      m_showIntensity     = false;
  boolean              m_animate           = false;  // this is the runOnStart boolean.
  private int          m_fps               = 20;
  //private String       m_caption           = "Ripple Tank";
  // Parameter names.  To change a name of a parameter, you need only make
  // a single change.  Simply modify the value of the parameter string below.
  //--------------------------------------------------------------------------
  private final String PARAM_numOfSources  = "numberOfSources";
  private final String PARAM_pixPerUnit    = "pixPerUnit";
  private final String PARAM_wavelength    = "wavelength";
  private final String PARAM_showControls  = "showControls";
  private final String PARAM_showIntensity = "showIntensity";
  private final String PARAM_animate       = "animate";
  private final String PARAM_fps           = "fps";
  private final String PARAM_caption       = "caption";
  private final String PARAM_helpFile      = "helpFile";
  private int          iwidth              = 0;      // the ripple tank image width
  private int          iheight             = 0;      // the ripple tank image height
  //SizedButton calcBtn, animateBtn;
  Button               startBtn;
  Button               stopBtn;
  Button               stepFBtn;
  Button               stepBBtn;
  Button               editBtn;
  private RippleCanvas rc;
  private Thread       calcThread = null;
  int                  M_RED[]    = new int[256];
  int                  M_GREEN[]  = new int[256];
  int                  M_BLUE[]   = new int[256];
  double               SIN[]      = new double[100];
  int                  pixPerWave;
  double               timePhase = 0;
  double               maxAmp    = 0;
  int                  imgCount  = 0;
  int                  c[]       = new int[4];
  int                  numPix    = 4;
  int                  pixels[]  = new int[numPix];
  int				  nimages=12;  //number of images in movie

  /**
   * Method GetParameter
   *
   * @param strName
   * @param args
   *
   * @return
   */
  String GetParameter(String strName, String args[]) {
    if(args==null) {
      return getParameter(strName);
    }
    int    i;
    String strArg   = strName+"=";
    String strValue = null;
    for(i = 0; i<args.length; i++) {
      if(strArg.equalsIgnoreCase(args[i].substring(0, strArg.length()))) {
        // Found matching parameter on command line, so extract its value.
        // If in double quotes, remove the quotes.
        //---------------------------------------------------------------
        strValue = args[i].substring(strArg.length());
        if(strValue.startsWith("\"")) {
          strValue = strValue.substring(1);
          if(strValue.endsWith("\"")) {
            strValue = strValue.substring(0, strValue.length()-1);
          }
        }
      }
    }
    return strValue;
  }

  /**
   * Method GetParameters
   *
   * @param args
   */
  void GetParameters(String args[]) {
    String param;
    param = GetParameter(PARAM_numOfSources, args);
    if(param!=null) {
      m_numSources = Integer.parseInt(param);
    }
    param = GetParameter(PARAM_pixPerUnit, args);
    if(param!=null) {
      m_pixPerUnit = Integer.parseInt(param);
    }
    param = GetParameter(PARAM_wavelength, args);
    if(param!=null) {
      m_wavelength = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_showControls, args);
    if(param!=null) {
      m_showControls = Boolean.valueOf(param).booleanValue();
    }
    param = GetParameter(PARAM_showIntensity, args);
    if(param!=null) {
      m_showIntensity = Boolean.valueOf(param).booleanValue();
    }
    param = GetParameter(PARAM_animate, args);
    if(param!=null) {
      m_animate = Boolean.valueOf(param).booleanValue();
    }
    param = GetParameter(PARAM_fps, args);
    if(param!=null) {
      m_fps = Integer.parseInt(param);
    }
    param = GetParameter(PARAM_caption, args);
  }

  /*
   *   public static void main(String args[]){
   *           RippleFrame frame = new RippleFrame("Ripple");
   *
   *               // Must show Frame before we size it so insets() will return valid values
   *               //----------------------------------------------------------------------
   *           frame.show();
   *           frame.hide();
   *           frame.setSize(frame.getInsets().left + frame.getInsets().right  + 320,
   *                                        frame.getInsets().top  + frame.getInsets().bottom + 370);
   *
   *           Ripple applet_Ripple = new Ripple();
   *
   *           frame.add("Center", applet_Ripple);
   *           applet_Ripple.m_fStandAlone = true;
   *           applet_Ripple.GetParameters(args);
   *           applet_Ripple.init();
   *           applet_Ripple.start();
   *           frame.show();
   *   }
   */

  /**
   * Main method
   * @param args
   * @y.exclude
   */
  public static void main(String[] args) {
    Ripple applet = new Ripple();
    applet.isStandalone = true;
    applet.m_fStandAlone = true;
    JFrame frame;
    frame = new JFrame() {

      /**
       * put your documentation comment here
       * @param e
       */
      protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID()==WindowEvent.WINDOW_CLOSING) {
          System.exit(0);
        }
      }

      /**
       * put your documentation comment here
       * @param title
       */
      public synchronized void setTitle(String title) {
        super.setTitle(title);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      }
    };
    frame.setTitle("Applet Frame");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    /** @j2sNative */{
      frame.setSize(400, 320);
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
      frame.setLocation((d.width-frame.getSize().width)/2, (d.height-frame.getSize().height)/2);
      frame.setVisible(true);
    }
  }

  /**
   * Method getAppletInfo
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Name: Ripple Ver 1.1\r\n"+"Author: Wolfgang Christian\r\n"+"Email: WOCHRISTIAN@Davidson.edu";
  }

  /**
   * Method getParameterInfo
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String[][] info = {
      {PARAM_numOfSources, "int", "Number of sources."}, 
      {PARAM_pixPerUnit, "int", "Pixels per unit."},
      {PARAM_wavelength, "double", "Wavelength"}, 
      {PARAM_wavelength, "boolean", "Wavelength."},
      {PARAM_showIntensity, "boolean", "Show intensity instead of amplitude."},
      {PARAM_animate, "boolean", "Animate pattern."},
      {PARAM_fps, "int", "Frames per second for during animation."},
      {PARAM_caption, "String", "Applet caption"},
      {PARAM_helpFile, "String", "Applet help file URL"},
    };
    return info;
  }

  protected void setResources() {
    button_start     = localProperties.getProperty("button.start", button_start);
    button_stop      = localProperties.getProperty("button.stop", button_stop);
    button_forward   = localProperties.getProperty("button.forward", button_forward);
    button_back      = localProperties.getProperty("button.back", button_back);
    button_edit      = localProperties.getProperty("button.edit", button_edit);
    button_cancel    = localProperties.getProperty("button.cancel", button_cancel);
    button_calculate = localProperties.getProperty("button.calculate", button_calculate);
    button_add       = localProperties.getProperty("button.add", button_add);
    button_delete    = localProperties.getProperty("button.delete", button_delete);
    label_stopped    = localProperties.getProperty("label.stopped", label_stopped);
    label_drag       = localProperties.getProperty("label.drag", label_drag);
    label_wait       = localProperties.getProperty("label.wait", label_wait);
    label_wavelength = localProperties.getProperty("label.wavelength", label_wavelength);
    label_nosource   = localProperties.getProperty("label.nosource", label_nosource);
    label_running    = localProperties.getProperty("label.running", label_running);
    label_frame      = localProperties.getProperty("label.frame", label_frame);
    label_clickdrag  = localProperties.getProperty("label.clickdrag", label_clickdrag);
  }

  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    if(!m_fStandAlone) {
      GetParameters(null);
    }
    pixPerWave = (int) (m_pixPerUnit*m_wavelength);
    /** @j2sNative */{
    	  resize(320, 370);
    }
    generatePallete();
    setLayout(new BorderLayout());
    setBackground(Color.lightGray);
    Panel p1 = new Panel();  //panel for the button
    p1.setBackground(Color.lightGray);
    p1.setLayout(new GridLayout(1, 4));
    p1.add(startBtn = new Button(button_start));
    p1.add(stopBtn = new Button(button_stop));
    p1.add(stepBBtn = new Button(button_back));
    p1.add(stepFBtn = new Button(button_forward));
    p1.add(editBtn = new Button(button_edit));
    if(m_showControls) {
      add("South", p1);
    }
    Panel p4 = new Panel();
    p4.setLayout(new GridLayout(1, 1));
    rc = new RippleCanvas(this);
    rc.setBackground(Color.white);
    p4.add(rc);
    // add("Center",new Border(p4));
    add("Center", p4);
    rc.setPixPerUnit(m_pixPerUnit);
    rc.setWavelength(m_wavelength);
    rc.setSleepTime((int) Math.round(m_wavelength*1000/m_fps));
    rc.setShowInfo(m_showControls);
    boolean temp = m_animate;
    for(int i = 0; i<m_numSources; i++) {
      addSource(-m_numSources/2+i, 0, 1, 0);
    }
    m_animate = temp;
    if(!m_animate) {  // frames have not been calculated so we might as well kill the calculation
      rc.msgStr = label_drag;
      rc.setImage(null);
      rc.frames.removeAllElements();
      startBtn.setLabel(button_calculate);
    }
  }

  /**
   * Sets the default drag option.  Affects subsequent objects.
   * @param drag true to drag new objects
   */
  public void setDrag(boolean drag) {
    m_canDrag = drag;
  }

  /**
   * Sets the intensity scale.
   *
   * @param s
   */
  public void setZScale(double s) {
    zscale = s;
  }

  /**
   * Sets the wavelength.
   *
   * @param w
   */
  public void setWavelength(double w) {
    if(w<0.1) {
      return;
    }
    if(w>10) {
      return;
    }
    stop();
    rc.stop();
    rc.setImage(null);
    rc.frames.removeAllElements();
    m_wavelength = w;
    pixPerWave   = (int) (m_pixPerUnit*m_wavelength);
    rc.setSleepTime((int) Math.round(w*1000/m_fps));
    rc.setWavelength(m_wavelength);
  }

  /**
   * Offsets the object's position on the screen from its default drawing
   * position.
   *
   * @param              id The id of the object.
   * @param xOff
   * @param yOff
   * @return             True if successful.
   */
  public boolean setDisplayOffset(int id, int xOff, int yOff) {
    Thing t = rc.getThing(id);
    if(t==null) {
      return false;
    }
    t.setDisplayOff(xOff, yOff);
    return true;
  }

  /**
   *    Sets the red, green, and blue color values for a wire or coil that has already been created. Color values
   *    must be in the range 0..255.
   *
   *    @param id       The id for the wire or loop.
   *    @param r        red.
   *    @param g        green.
   *    @param b        blue.
   *
   * @return true if successful
   */
  public boolean setRGB(int id, int r, int g, int b) {
    return rc.setColor(id, new Color(r, g, b));
  }

  /**
   *    Changes the drag property.
   *
   *    @param id        the object identifier
   *    @param drag      true if dragable
   *
   *    @return true if successful
   */
  public boolean setDragable(int id, boolean drag) {
    return rc.setDragable(id, drag);
  }

  /**
   * Shows the intensity or the amplitude.
   *
   * @param val true for intensity; false for amplitude
   */
  public void showIntensity(boolean val) {
    m_showIntensity = val;
    stop();
    rc.stop();
    rc.setImage(null);
    rc.frames.removeAllElements();
  }

  /**
   * Cancels the generation of the animation frames.
   */
  public void cancel() {
    if(calcThread==null) {
      stopAnimate();  // we want to stop the animation but not kill the frames;
    } else {          // frames have not been calculated so we might as well kill the calculation
      stop();         // stop the calculation thread
      rc.stop();      // stop the anmination thread if it happens to be running. (It shouldn't!)
      rc.msgStr = label_clickdrag;
      rc.setImage(null);
      rc.frames.removeAllElements();
      startBtn.setLabel(button_calculate);
      stepFBtn.setLabel(button_add);
      stepBBtn.setLabel(button_delete);
      m_animate = false;
    }
  }

  /**
   * Pauses the animation.
   *
   */
  public void pause() {
    stopAnimate();
  }

  protected void pausingClock() {
    pause();
  }

  /**
   * Stops the animation.
   *
   */
  public void stopAnimate() {
    m_animate = false;  // we want to stop the animation if it is going on.
    if(calcThread!=null) {
      return;  // frames have not been calculated so we do not have a movie
    }
    if(rc.isRunning())  // animation is runnning so stop it
    {
      rc.stop();
      rc.msgStr = label_stopped;
    }
  }

  /**
   * Starts the animation.
   */
  public synchronized void startAnimate() {
    setRunningID(this);
    m_animate = true;
    if(calcThread!=null || rc.isRunning() ) {
      return;  // Calculation or movie is running. Images will animate when calculation is done;
    }
    if(rc.getImage()==null)  //no image and no calcthread
    {
      rc.msgStr = label_wait;
      rc.repaint();
      start();               // start new calculations
      startBtn.setLabel(button_cancel);
      stepFBtn.setLabel("--");
      stepBBtn.setLabel("--");
      return;
    }
    if(rc.getImageNumber()<nimages) {
      start();     // finish old calculations
      startBtn.setLabel(button_cancel);
      stepFBtn.setLabel("--");
      stepBBtn.setLabel("--");
    } else {
      rc.start();  // images are OK so just animate.
    }
  }

  //Destroy the applet

  /**
   * Method destroy
   * @y.exclude
   */
  public void destroy() {
    stop();
    this.deleteAll();
    super.destroy();
  }

  /**
   * Counts the number of applets on a page.
   * @return the count
   * @y.exclude
   */
  public int getAppletCount() {
    if(firstTime) {
      return 0;
    } else {
      return super.getAppletCount();
    }
  }

  /**
   * Method start
   * @y.exclude
   */
  public void start() {
    super.start();
    if(firstTime) {
      firstTime = false;
    } else if((rc.getImage()!=null)&&(calcThread==null)&&(rc.getImageNumber()==nimages)) {
      // we already have a movie so don't calculate!
      rc.start();
      return;
    }
    if((calcThread==null)&&m_animate) {
      calcThread = new Thread(this);
      calcThread.start();
      setControlBtn(true);
    }
    //System.out.println("first time");
  }

  /**
   * Method stop
   * @y.exclude
   */
  public void stop() {
    super.stop();
    Thread myThread = calcThread;
    try {
      if(myThread!=null) {
        //myThread.stop();
        calcThread = null;
        try {
        	/** j2sNative */{
          myThread.join();
        	}
        } catch(InterruptedException e) {}
        //calcThread=null;
        startBtn.setLabel(button_calculate);
      }
    } catch(Exception e) {}
    calcThread = null;  // calculation of images in now stopped
    rc.stop();
  }

  /**
   * Step the movie forward by one frame.
   */
  public void stepTimeForward() {
    if(rc.isRunning()) {
      // we already have a running movie so stop it.
      rc.stop();
      return;
    } else {
      rc.step(+1);
    }
  }

  /**
   * Steps forward one frame.
   *
   */
  public void stepForward() {
    stepTimeForward();
  }

  /**
   * Steps the movie back by one frame.
   */
  public void stepTimeBack() {
    if(rc.isRunning()) {
      // we already have a running movie so stop it.
      rc.stop();
      return;
    } else {
      rc.step(-1);
    }
  }

  /**
   * Steps back one frame.
   */
  public void stepBack() {
    stepTimeBack();
  }

  /**
   * Recalculates the animation frames.
   */
  synchronized public void recalculate() {
    stop();
    rc.stop();
    rc.msgStr = "";
    rc.setImage(null);
    rc.frames.removeAllElements();
    start();
  }

  private void setControlBtn(boolean calculating) {
    if(calculating) {  // frames not ready to be shown.
      if(calcThread!=null) {
        startBtn.setLabel(button_cancel);
        stepFBtn.setLabel("--");
        stepBBtn.setLabel("--");
      } else {
        startBtn.setLabel(button_calculate);
        stepFBtn.setLabel(button_add);
        stepBBtn.setLabel(button_delete);
      }
      stopBtn.setLabel("--");
      editBtn.setLabel("--");
    } else {
      startBtn.setLabel(button_start);
      stopBtn.setLabel(button_stop);
      stepFBtn.setLabel(button_forward);
      stepBBtn.setLabel(button_back);
      editBtn.setLabel(button_edit);
    }
  }

  /**
   * Method run
   * @y.exclude
   */
  public void run() {
	System.out.println("run");
    Thread me = Thread.currentThread();
    if(calcThread!=me && !isJS) {
      //return;
    }  // check to make sure this is the right thread.
    rc.stop();
    int    width;
    int    height;
    double scale;
    if(pixPerWave>0) {
      scale = 16.0/pixPerWave;
    } else {
      scale = 1;
    }
    if(scale>1) {
      scale = 1;
    }
    width  = (int) (rc.getSize().width*scale);
    height = (int) (rc.getSize().height*scale);
    // see if the size has changed.  Clear the old frames if it has.
    if((rc.getSize().width!=iwidth)||(rc.getSize().height!=iheight)) {
      iwidth  = rc.getSize().width;
      iheight = rc.getSize().height;
      rc.setImage(null);
      rc.frames.removeAllElements();
    }
    // allocate a new array for every image to fix bug on the Mac.
    /*
     * if (numPix!=width * height){
     *   pixels=new int[width * height];
     *   numPix=width*height;
     * }
     */
    int count        = 0;
    int sleepCounter = 0;
    // rc.frames.removeAllElements();
    //rc.msgStr = "Please Wait...";
    count = rc.getImageNumber();
    calculate:  // label the calculate while statement
    while((count<nimages)&&(calcThread!=null)) {
    	 //System.out.println("image ="+count);
      pixels = new int[width*height];             // try a new array for every frame on the Mac.
      numPix = width*height;
      Vector sources = (Vector) rc.sources.clone();
      if(rc.isImageLoaded()||(sleepCounter>100))  // sometimes imageLoaded doesn't work.
      {
        int index = 0;
        sleepCounter = 0;
        timePhase    = count*100.0/nimages;
        for(int j = 0; j<height; j++) {
          for(int i = 0; i<width; i++) {
            if(calcThread==null) {
              break calculate;
            }
            c[0] = c[1] = c[2] = 0;
            c[3] = 255;
            ampFunction(c, i, j, width, height, scale, timePhase, sources);
            pixels[index++] = ((c[3]<<24)|(c[0]<<16)|(c[1]<<8)|(c[2]<<0));
          }
        }
        //System.out.println("Ripple.run: "+count+" w: "+width+" h: "+height);
        if(rc.addImage(width, height, pixels, 0, width, count)) {
        	  //System.out.println("image ="+count);
          count++;
        }
      }
      //System.out.println("Ripple.run: sleep ");
      sleepCounter++;
      try {
        if(!isJS) Thread.sleep(20);
      } catch(InterruptedException e) {}
    }
    calcThread = null;
    setControlBtn(false);
    rc.msgStr = label_stopped;
    if(m_animate) {
      rc.start();
    }
  }

  /**
   * Starts the animation.
   */
  public void forward() {
    if(calcThread==null) {
      startAnimate();
    }
  }

  /**
   * Edit mode.
   */
  public void edit() {
    //cancel();
    stop();     // stop the calculation thread
    rc.stop();  // stop the anmination thread if it happens to be running. (It shouldn't!)
    rc.msgStr = label_drag;
    rc.setImage(null);
    rc.frames.removeAllElements();
    startBtn.setLabel(button_calculate);
    stepFBtn.setLabel(button_add);
    stepBBtn.setLabel(button_delete);
    m_animate = false;
    editSources();
  }

  /**
   * Method action
   *
   * @param ev
   * @param arg
   *
   * @return true if successful
   * @y.exclude
   */
  public boolean action(Event ev, Object arg) {
    if(ev.target.equals(startBtn)) {  // this button changes depending of the state of the calculation.
      if(calcThread==null) {
        startAnimate();
      } else {
        cancel();
      }
      return true;
    } else if(ev.target.equals(stopBtn)) {
      stopAnimate();
      return true;
    } else if(ev.target.equals(stepFBtn)) {
      if(startBtn.getLabel().equals(button_calculate)) {
        appendSource();
        return true;
      }
      m_animate = false;
      if(rc.isRunning()) {
        if(calcThread==null) {
          rc.stop();
        }
      }
      rc.step(+1);
      return true;
    } else if(ev.target.equals(stepBBtn)) {
      if(startBtn.getLabel().equals(button_calculate)) {
        deleteAll();
        return true;
      }
      m_animate = false;
      if(rc.isRunning()) {
        if(calcThread==null) {
          rc.stop();
        }
      }
      rc.step(-1);
      return true;
    } else if(ev.target.equals(editBtn)) {
      cancel();
      editSources();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method ampFunction
   *
   * @param c
   * @param i
   * @param j
   * @param width
   * @param height
   * @param scale
   * @param tp
   * @param sources
   */
  void ampFunction(int c[], int i, int j, int width, int height, double scale, double tp, Vector sources) {
    int    w   = width;
    int    h   = height;
    int    xo  = w/2;
    int    yo  = h/2;
    int    val = 0;
    double x, y, r2;
    Source source;
    int    phase;
    double sourcePhase = 0;
    double phaseScale  = 100.0/pixPerWave/scale;
    int    n           = sources.size();
    double amp         = 0;
    for(int k = 0; k<n; k++) {
      source = (Source) sources.elementAt(k);
      //source.setDisplayCoordinates(m_pixPerUnit*scale);
      source.setDisplayCoordinates(m_pixPerUnit);
    }
    for(int k = 0; k<n; k++) {
      source      = (Source) sources.elementAt(k);
      x           = i-xo-source.displayX*m_pixPerUnit*scale;
      y           = j-yo+source.displayY*m_pixPerUnit*scale;
      sourcePhase = (source.phase/0.360)/2.0;  // hack to fix bug
      r2          = x*x+y*y;
      phase       = (int) (-tp-sourcePhase+Math.sqrt(r2)*phaseScale)%100;
      if(phase<0) {
        amp = amp-SIN[-phase]*source.amp;
      } else {
        amp = amp+SIN[phase]*source.getAmp();
      }
    }
    if(maxAmp<=0) {
      val = 0;
    } else if(m_showIntensity) {
      val = (int) Math.round(amp*amp/maxAmp/maxAmp/255);
    } else {
      val = (int) Math.round((255+amp/maxAmp)/2);
    }
    val  = Math.min(Math.max(0, val), 255);
    c[0] = M_RED[val];
    c[1] = M_GREEN[val];
    c[2] = M_BLUE[val];
  }

  /**
   * Method generatePallete
   *
   */
  void generatePallete() {
    //red
    for(int i = 0; i<256; i++) {
      M_RED[i] = i;
    }
    //green
    for(int i = 0; i<256; i++) {
      M_GREEN[i] = i;
    }
    //blue
    for(int i = 0; i<256; i++) {
      M_BLUE[i] = i;
    }
    //sin lookup table
    for(int i = 0; i<100; i++) {
      SIN[i] = 255*Math.sin(2*Math.PI*i/100);
    }
  }

  /**
   * Edit source mode.
   */
  public void editSources() {
    stop();
    rc.stop();
    rc.setImage(null);
    rc.frames.removeAllElements();
    rc.msgStr = label_clickdrag;
    rc.repaint();
    setControlBtn(true);
    m_animate = false;
  }

  /**
   * Adds a source.
   *
   * @return the id
   */
  public int appendSource() {
    stopAnimate();
    int id = 0;
    if(rc.sources.size()>0) {
      Source s     = (Source) rc.sources.lastElement();
      double x     = s.getX();
      double y     = s.getY();
      double amp   = s.getAmp();
      double phase = s.getPhase();
      id = addSource(x+1, y-1, amp, phase);
    } else {
      id = addSource(0, 0, 1, 0);
    }
    return id;
  }

  /**
   * Add a sinusoidal source to the ripple tank.
   *
   * @param x     the x position
   * @param y     the y position
   * @param amp   the amplitude
   * @param phase the phase angle
   *
   * @return the id
   */
  public int addSource(double x, double y, double amp, double phase) {
    amp    = Math.abs(amp);
    maxAmp = maxAmp+amp;
    if(zscale!=0) {
      maxAmp = zscale;
    }
    stop();
    rc.stop();
    int id = rc.addSource(x, y, amp, phase, 5);
    this.setDragable(id, m_canDrag);
    //s.canDrag=m_canDrag;
    setControlBtn(true);
    m_animate = false;
    return id;
  }

  /**
   * Create an object and add it to the Physlet.
   * The first argument is the name of the object to be added and the second is a
   * comma-delimited list of parameters.  For example, a circle can be added a follows:
   * <p>
   * <code>addObject ("circle", "x = 0, y = -1.0, r = 10");</code>
   * </p>
   * See the supplemental documentation for a list of
   * <a href="doc-files/EField_addObject.html">object names and parameters.</a>
   *
   * @param              name the type of object to be created.
   * @param              parList a list of parameters to be set
   * @return             id that identifies the object.
   */
  public synchronized int addObject(String name, String parList) {
    stopAnimate();
    Thing  t      = null;
    double x      = 0;
    double y      = 0;
    int    width  = 20;
    int    height = 20;
    int    r      = 10;
    int    s      = 10;
    name = name.toLowerCase().trim();
    name = SUtil.removeWhitespace(name);
    String parList2 = parList.trim();
    ;
    parList = SUtil.removeWhitespace(parList);
    if(name.equals("source")) {
      double amp   = 1;
      double phase = 0;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "amp=")) {
        amp = SUtil.getParam(parList, "amp=");
      }
      if(SUtil.parameterExist(parList, "phase=")) {
        phase = SUtil.getParam(parList, "phase=");
      }
      int id = this.addSource(x, y, amp, phase);
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
        t = rc.getThing(id);
        t.setWidth(r);
        t.setHeight(r);
      }
      return id;
    } else if(name.equals("box")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "w=")) {
        width = (int) SUtil.getParam(parList, "w=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        height = (int) SUtil.getParam(parList, "h=");
      }
      t = new BoxThing(this, rc, x, y, width, height);
    } else if(name.equals("protractor")) {
      s = 40;  // the protractor length.
      double theta = 0, theta0 = 0;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "theta=")) {
        theta = SUtil.getParam(parList, "theta=");
      }
      if(SUtil.parameterExist(parList, "theta0=")) {
        theta0 = SUtil.getParam(parList, "theta0=");
      }
      if(SUtil.parameterExist(parList, "s=")) {
        s = (int) SUtil.getParam(parList, "s=");
      }
      ProtractorThing p = new ProtractorThing(this, rc, s, theta, theta0, x, y);
      if(SUtil.parameterExist(parList, "fixedbase")) {
        p.fixedBase = true;
      }
      if(SUtil.parameterExist(parList, "fixedlength")) {
        p.fixedlength = true;
      }
      t = p;
    } else if(name.equals("rectangle")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "w=")) {
        width = (int) SUtil.getParam(parList, "w=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        height = (int) SUtil.getParam(parList, "h=");
      }
      t = new RectangleThing(this, rc, x, y, width, height);
    } else if(name.equals("circle")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      t = new CircleThing(this, rc, x, y, r);
    } else if(name.equals("cursor")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      t = new MarkerThing(this, rc, 2*r+1, x, y);
    } else if(name.equals("shell")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      t = new ShellThing(this, rc, x, y, r);
    } else if(name.equals("arrow")) {
      double horz = 1, vert = 1;
      s = 4;   // the size of the arrowhead
      if(SUtil.parameterExist(parList, "s=")) {
        s = (int) SUtil.getParam(parList, "s=");
      }
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        horz = SUtil.getParam(parList, "h=");
      }
      if(SUtil.parameterExist(parList, "v=")) {
        vert = SUtil.getParam(parList, "v=");
      }
      t = new ArrowThing(this, rc, s, horz, vert, x, y);
    } else if(name.equals("text")) {
      String txt  = "";
      String calc = null;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "txt=")) {
        txt = SUtil.getParamStr(parList2, "txt=");
      }
      if(SUtil.parameterExist(parList, "text=")) {
        txt = SUtil.getParamStr(parList2, "text=");
      }
      //if( SUtil.parameterExist(parList,"calc=")) calc=SUtil.getParamStr(parList,"calc=");
      t = new TextThing(this, rc, txt, x, y);
    } else if(name.equals("caption")) {
      String txt = "";
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "txt=")) {
        txt = SUtil.getParamStr(parList2, "txt=");
      }
      if(SUtil.parameterExist(parList, "text=")) {
        txt = SUtil.getParamStr(parList2, "text=");
      }
      t = new CaptionThing(this, rc, txt, x, y);
    } else if(name.equals("image")) {
      String file = " ";
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "gif=")) {
        file = SUtil.getParamStr(parList, "gif=");
      }
      if(SUtil.parameterExist(parList, "file=")) {
        file = SUtil.getParamStr(parList, "file=");
      }
      if(file==null) {
        return 0;
      }
      Image im = edu.davidson.graphics.Util.getImage(file, this);
      if(im!=null) {
        t = new ImageThing(this, rc, im, x, y);
      } else {
        t = null;
      }
    } else if(name.equals("constraint")) {
      double xmin = 0, xmax = 0, ymin = 0, ymax = 0;
      if(SUtil.parameterExist(parList, "xmin=")) {
        xmin = SUtil.getParam(parList, "xmin=");
      }
      if(SUtil.parameterExist(parList, "ymin=")) {
        ymin = SUtil.getParam(parList, "ymin=");
      }
      if(SUtil.parameterExist(parList, "xmax=")) {
        xmax = SUtil.getParam(parList, "xmax=");
      }
      if(SUtil.parameterExist(parList, "ymax=")) {
        ymax = SUtil.getParam(parList, "ymax=");
      }
      t = new Constraint(this, rc, xmin, xmax, ymin, ymax);
    }
    if((t!=null)&&SUtil.parameterExist(parList, "label=")) {
      t.setLabel(SUtil.getParamStr(parList2, "label="));
    }
    if(t==null) {
      System.out.println("Object not created. name:"+name+"parameter list:"+parList);
      return 0;
    }
    t.setDragable(m_canDrag);
    rc.addThing(t);
    rc.repaint();
    return t.hashCode();
  }

  /**
   *   Forces an object to follow another object on the screen.
   *
   *   @param              masterID The id of the master object.
   *   @param              slaveID The id of the slave object.
   *   @return             true if successful.
   */
  public boolean setAnimationSlave(int masterID, int slaveID) {
    Thing master = rc.getThing(masterID);
    Thing slave  = rc.getThing(slaveID);
    if((master==null)||(slave==null)) {
      return false;
    }
    master.addSlave(slave);
    return true;
  }

  /**
   *  Sets a motion constraint on an object.
   *
   *  @param              id the ID of the object.
   *  @param              constraintID the ID of the constraint.
   *
   *  @return             <code>true</code> if successful.
   */
  public boolean setConstraint(int id, int constraintID) {
    Thing t = rc.getThing(id);
    Thing c = rc.getThing(constraintID);
    if(t==null) {
      return false;
    }
    if(c==null) {
      return false;
    }
    if(!(c instanceof Constraint)) {
      return false;
    }
    t.setConstraint((Constraint) c);
    return true;
  }

  /**
   *   Sets the ripple tank default.
   *   Delete sources.
   *   Delete drawable objects.
   */
  public void setDefault() {
    m_canDrag = true;
    deleteAll();
  }

  /**
   * Removes all sources.
   */
  public void deleteAll() {
    stopAnimate();
    rc.sources.removeAllElements();
    rc.frames.removeAllElements();
    Thing t = null;
    for(Enumeration e = rc.things.elements(); e.hasMoreElements(); ) {
      t = (Thing) e.nextElement();
      this.removeDataSource(t.hashCode());
    }
    rc.things.removeAllElements();
    this.deleteDataConnections();
    maxAmp = 0;
    recalculate();
  }
}
