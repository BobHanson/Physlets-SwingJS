// Superposition  JBuilder version.  Uses Physlet class.
// version 1.3a
package superposition;

import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import java.awt.*;

/**
 * Class Superposition
 */
public class Superposition extends SApplet implements Runnable {

  String                      button_reset       = "Reset";
  String                      button_start       = "Forward";
  String                      button_reverse     = "Reverse";
  String                      button_stop        = "Stop";
  String                      button_forward     = "Step>>";
  String                      button_back        = "<<Step";
  String                      label_time         = "Time:";
  Thread                      m_Superposition    = null;
  // STANDALONE APPLICATION SUPPORT:
  //              m_fStandAlone will be set to true if applet is run standalone
  //--------------------------------------------------------------------------
  boolean                     m_fStandAlone      = false;
  private boolean             runOnStart         = false;
  //private int                 m_numPoints        = 100;
  private double              m_pixPerX          = 10;
  private double              m_pixPerY          = 10;
  private double              m_gridX            = 1;
  private double              m_gridY            = 1;
  private String              m_func1            = "sin(x-t)";
  private String              m_func2            = "sin(x+t)";
  private int                 m_FPS              = 10;
  private boolean             m_showControls     = true;
  private double              m_dt               = 0.1;
  private int                 m_numGraphs        = 3;
  // Parameter names.  To change a name of a parameter, you need only make
  // a single change.  Simply modify the value of the parameter string below.
  //--------------------------------------------------------------------------
  private final String        PARAM_numPoints    = "numPoints";
  private final String        PARAM_numGraphs    = "numGraphs";
  private final String        PARAM_dt           = "dt";
  private final String        PARAM_pixPerX      = "pixPerX";
  private final String        PARAM_pixPerY      = "pixPerY";
  private final String        PARAM_gridX        = "gridX";
  private final String        PARAM_gridY        = "gridY";
  private final String        PARAM_func1        = "func1";
  private final String        PARAM_func2        = "func2";
  private final String        PARAM_showControls = "showControls";
  private final String        PARAM_FPS          = "FPS";
  private final String        PARAM_helpFile     = "helpFile";
  private SuperpositionCanvas sc1, sc2, sc3;
  //private boolean             noRun     = false;
  private int                 sleepTime = 100;
  private boolean             interval  = false;
  private double              startTime = -1;
  private double              endTime   = 1;
  private double              t         = 0;  // time
  Button                      forwardBtn;
  Button                      stopBtn;
  Button                      reverseBtn;
  Button                      stepFBtn;
  Button                      stepBBtn;
  Button                      resetBtn;

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
    param = GetParameter(PARAM_numGraphs, args);
    if(param!=null) {
      m_numGraphs = Integer.parseInt(param);
    }
    param = GetParameter(PARAM_dt, args);
    if(param!=null) {
      m_dt = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_pixPerX, args);
    if(param!=null) {
      m_pixPerX = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_pixPerY, args);
    if(param!=null) {
      m_pixPerY = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_gridX, args);
    if(param!=null) {
      m_gridX = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_gridY, args);
    if(param!=null) {
      m_gridY = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_func1, args);
    if(param!=null) {
      m_func1 = param;
    }
    param = GetParameter(PARAM_func2, args);
    if(param!=null) {
      m_func2 = param;
    }
    param = GetParameter(PARAM_FPS, args);
    if(param!=null) {
      m_FPS = Integer.parseInt(param);
    }
    param = GetParameter(PARAM_showControls, args);
    if(param!=null) {
      m_showControls = Boolean.valueOf(param).booleanValue();
    }
  }

  /**
   * Constructor Superposition
   * @y.exclude
   */
  public Superposition() {}

  /**
   * Method getAppletInfo
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Name: Superposition Ver 1.3a\r\n"+"Author: Wolfgang Christian\r\n"+"Email: wochristian@davidson.edu";
  }

  /**
   * Method getParameterInfo
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String[][] info = {
      {PARAM_numPoints, "int", "Number of points to use for plot."},
      {PARAM_numGraphs, "int", "Number of graphs to display (1, 2, or 3)."}, {PARAM_dt, "double", "Time step"},
      {PARAM_pixPerX, "double", "X scale"}, {PARAM_pixPerY, "double", "Y Scale"},
      {PARAM_gridX, "double", "X grid marks"}, {PARAM_gridY, "double", "Y grid marks"},
      {PARAM_func1, "String", "Function of x and t"}, {PARAM_func2, "String", "Function of x and t."},
      {PARAM_showControls, "boolean", "Show control buttons."}, {PARAM_FPS, "int", "Frames per second."},
      {PARAM_helpFile, "String", "Applet help file URL"},
    };
    return info;
  }

  protected void setResources() {
    button_start   = localProperties.getProperty("button.start", button_start);
    button_stop    = localProperties.getProperty("button.stop", button_stop);
    button_reverse = localProperties.getProperty("button.reverse", button_reverse);
    button_reset   = localProperties.getProperty("button.reset", button_reset);
    button_forward = localProperties.getProperty("button.forward", button_forward);
    button_back    = localProperties.getProperty("button.back", button_back);
    label_time     = localProperties.getProperty("label.time", label_time);
  }

  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    runOnStart = false;
    if(!m_fStandAlone) {
      GetParameters(null);
    }
    if(m_numGraphs<1) {
      m_numGraphs = 1;
    }
    if(m_numGraphs>3) {
      m_numGraphs = 3;
    }
    /** j2sNative */{
    	  resize(400, 400);
    }
    setLayout(new BorderLayout());
    Panel p = new Panel();  // South will be buttons.  Center will be graphs.
    p.setLayout(new BorderLayout());
    Panel p1 = new Panel();  // controls
    p1.setLayout(new GridLayout(1, 6));
    p1.add(forwardBtn = new Button(button_start));
    p1.add(stopBtn = new Button(button_stop));
    p1.add(reverseBtn = new Button(button_reverse));
    p1.add(stepBBtn = new Button(button_back));
    p1.add(stepFBtn = new Button(button_forward));
    p1.add(resetBtn = new Button(button_reset));
    if(m_showControls) {
      p.add("South", p1);
      add("Center", p);
    } else {
      add("Center", p);
    }
    Panel p2 = new Panel();
    p2.setLayout(new GridLayout(m_numGraphs, 1));
    p.add("Center", p2);  // panel will contain three drawing canvases.
    p2.add(new EtchedBorder(sc1 = new SuperpositionCanvas(this)));
    if(m_numGraphs>1) {
      p2.add(new EtchedBorder(sc2 = new SuperpositionCanvas(this)));
    }
    if(m_numGraphs>2) {
      p2.add(new EtchedBorder(sc3 = new SuperpositionCanvas(this)));
    }
    /*
     * Date today=new Date();
     * Date expire=new Date(98,12,30);
     *        if (today.after(expire))
     * {
     *    noRun=true;
     *    p.add("Center",new Label("Beta applet is out of date. Reload from http:Webphysics.Davidson.Edu"));
     * }
     */
    sc1.setPixPerX(m_pixPerX);
    sc1.setPixPerY(m_pixPerY);
    sc1.setGridX(m_gridX);
    sc1.setGridY(m_gridY);
    sc1.setShowTime(true);
    sc1.label_time = label_time;
    sc1.setFuncStr(m_func1);
    sc1.setCaption("f(x,t)");
    if(m_numGraphs>1) {
      sc2.setPixPerX(m_pixPerX);
      sc2.setPixPerY(m_pixPerY);
      sc2.setGridX(m_gridX);
      sc2.setGridY(m_gridY);
      sc2.setFuncStr(m_func2);
      sc2.setCaption("g(x,t)");
    }
    if(m_numGraphs>2) {
      sc3.setPixPerX(m_pixPerX);
      sc3.setPixPerY(m_pixPerY);
      sc3.setGridX(m_gridX);
      sc3.setGridY(m_gridY);
      if(m_func2.length()>0) {
        if((m_func2.charAt(0)=='+')||(m_func2.charAt(0)=='-')) {
          sc3.setFuncStr(m_func1+m_func2);
        } else {
          sc3.setFuncStr(m_func1+'+'+m_func2);
        }
      }
      sc3.setCaption("f(x,t)+g(x,t)");
    }
    sleepTime = (int) Math.round(1000/m_FPS);
  }

  /**
   * Method destroy
   * @y.exclude
   */
  public void destroy() {
    destroyed       = true;
    m_Superposition = null;
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
    if(firstTime) {
      firstTime = false;
      startThread();
    }
    super.start();
  }

  private void startThread() {
    this.setRunningID(this);
    if(m_Superposition==null) {
      m_Superposition = new Thread(this);
      m_Superposition.start();
    }
  }

  /**
   * Method stop
   * @y.exclude
   */
  public void stop() {
    m_Superposition = null;
    super.stop();
  }

  /**
   * Method run
   * @y.exclude
   */
  public void run() {
    while(runOnStart&&(m_Superposition!=null)&&(this.getRunningID()==this)) {
      try {
        t = t+m_dt;
        if(interval&&(t>endTime)) {
          t = startTime;
        }
        if(m_Superposition!=null) {
          sc1.setTime(t);
        }
        if((m_numGraphs>1)&&(m_Superposition!=null)) {
          sc2.setTime(t);
        }
        if((m_numGraphs>2)&&(m_Superposition!=null)) {
          sc3.setTime(t);
        }
        if(m_Superposition!=null) {
          Thread.sleep(sleepTime);
        }
      } catch(InterruptedException e) {
        stop();
      }
    }
    m_Superposition = null;
  }

  /**
   * Method setCaption
   *
   * @param s
   */
  public void setCaption(String s) {
    sc1.setCaption(s);
  }

  /**
   * Sets the caption.
   *
   * @param n
   * @param s
   */
  public void setCaption(int n, String s) {
    if(n==1) {
      sc1.setCaption(s);
    } else if((n==2)&&(m_numGraphs>1)) {
      sc2.setCaption(s);
    } else if((n==3)&&(m_numGraphs>2)) {
      sc3.setCaption(s);
    }
  }

  /**
   * Sets the RGB color.
   *
   * @param r
   * @param g
   * @param b
   */
  public void setRGB(int r, int g, int b) {
    sc1.setRGB(r, g, b);
    if(m_numGraphs>1) {
      sc2.setRGB(r, g, b);
    }
    if(m_numGraphs>2) {
      sc3.setRGB(r, g, b);
    }
  }

  /**
   * Sets the color for the n-th graph.
   *
   * @param n
   * @param r
   * @param g
   * @param b
   */
  public void setRGB(int n, int r, int g, int b) {
    if(n==1) {
      sc1.setRGB(r, g, b);
    } else if((n==2)&&(m_numGraphs>1)) {
      sc2.setRGB(r, g, b);
    } else if((n==3)&&(m_numGraphs>2)) {
      sc3.setRGB(r, g, b);
    }
  }

  /**
   * Shows the simulation time.
   *
   * @param  show true will show
   */
  public void setShowTime(boolean show) {
    sc1.setShowTime(show);
  }

  /*
   *   public void setShowTime(int st) {
   *
   *       if (st == 0) {
   *           sc1.setShowTime(false);
   *       } else {
   *           sc1.setShowTime(true);
   *       }
   *   }
   */

  /**
   * Resets to a given time.
   *
   * @param t_
   */
  public void reset(double t_) {
    t    = t_;
    m_dt = Math.abs(m_dt);
    sc1.setTime(t);
    if(m_numGraphs>1) {
      sc2.setTime(t);
    }
    if(m_numGraphs>2) {
      sc3.setTime(t);
    }
  }

  /**
   * Sets the default state.
   */
  public void setDefault() {
    t    = 0;
    m_dt = Math.abs(m_dt);
    sc1.setTime(t);
    if(m_numGraphs>1) {
      sc2.setTime(t);
    }
    if(m_numGraphs>2) {
      sc3.setTime(t);
    }
  }

  /**
   * Pauses the animation.
   */
  public void pause() {
    runOnStart = false;
    stop();
  }

  /**
   * Starts the animation.
   */
  public void forward() {
    this.setRunningID(this);
    runOnStart = true;
    m_dt       = Math.abs(m_dt);
    startThread();
  }

  /**
   * Reverse the animation
   */
  public void reverse() {
    this.setRunningID(this);
    runOnStart = true;
    m_dt       = -Math.abs(m_dt);
    startThread();
  }

  /**
   * Steps forward.
   *
   */
  public void stepForward() {
    stop();
    t += Math.abs(m_dt);
    sc1.setTime(t);
    if(m_numGraphs>1) {
      sc2.setTime(t);
    }
    if(m_numGraphs>2) {
      sc3.setTime(t);
    }
  }

  /**
   * Steps backward.
   *
   */
  public void stepBack() {
    stop();
    t -= Math.abs(m_dt);
    sc1.setTime(t);
    if(m_numGraphs>1) {
      sc2.setTime(t);
    }
    if(m_numGraphs>2) {
      sc3.setTime(t);
    }
  }

  /**
   * Method action
   *
   * @param evt
   * @param arg
   *
   * @return tre if successful
   * @y.exclude
   */
  public boolean action(Event evt, Object arg) {
    if(evt.target.equals(stopBtn)) {
      pause();
      return true;
    } else if(evt.target.equals(forwardBtn)) {
      forward();
      return true;
    } else if(evt.target.equals(reverseBtn)) {
      reverse();
      return true;
    } else if(evt.target.equals(resetBtn)) {
      reset();
      return true;
    } else if(evt.target.equals(stepFBtn)) {
      stepForward();
      return true;
    } else if(evt.target.equals(stepBBtn)) {
      stepBack();
      return true;
    } else {
      return false;
    }
  }

  /**
   * Sets the first function.
   *
   * @param s
   */
  public void setFunc1(String s) {
    m_func1 = s;
    sc1.setFuncStr(s);
    if((m_numGraphs>2)&&(m_func2.length()>0)) {
      if((m_func2.charAt(0)=='+')||(m_func2.charAt(0)=='-')) {
        sc3.setFuncStr(m_func1+m_func2);
      } else {
        sc3.setFuncStr(m_func1+'+'+m_func2);
      }
    }
    reset();
  }

  /**
   * Sets the second function.
   *
   * @param s
   */
  public void setFunc2(String s) {
    m_func2 = s;
    if(m_numGraphs>1) {
      sc2.setFuncStr(s);
    }
    if((m_numGraphs>2)&&(m_func2.length()>0)) {
      if((m_func2.charAt(0)=='+')||(m_func2.charAt(0)=='-')) {
        sc3.setFuncStr(m_func1+m_func2);
      } else {
        sc3.setFuncStr(m_func1+'+'+m_func2);
      }
    }
    reset();
  }
}
