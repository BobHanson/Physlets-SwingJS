//******************************************************************************
// Sync.java:   Applet
//
//******************************************************************************
package sync;

//import java.applet.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;

/**
 * Sync models the electric field from an accelerating charge.
 *
 * @author Wolfgang Christian
 */
public class Sync extends SApplet implements SStepable {

  private String       button_stop    = "Stop";
  private String       button_start   = "Run ";
  private String       label_speed    = "v=";
  private String       label_running  = "Running";
  private String       label_wiggler  = "Wiggler";
  private String       label_sho      = "SHO";
  private String       label_sync     = "Sync";
  private String       label_inertial = "Inertial";
  private String       label_caption  = "Electric Fields";
  private Scrollbar    speedBar;
  private Label        speedLabel;
  private Choice       dynaChoice;

  /** Field dc */
  protected SyncCanvas    dc;
  private Vector       dynaSys = new Vector();
  private Button       runBtn;
  private boolean      showControls = true;
  private int          numSteps     = 100;
  //--------------------------------------------------------------------------
  private Graphics     m_Graphics;
  // STANDALONE APPLICATION SUPPORT:
  //          m_fStandAlone will be set to true if applet is run standalone
  //--------------------------------------------------------------------------
  boolean              m_fStandAlone      = false;
  private int          m_FPS              = 20;
  private double       m_speed            = 0.5;
  private String       m_dynamics         = "Wiggler";
  private int          m_resolution       = 2;
  //private int sleepTime = 50;
  // Parameter names.  To change a name of a parameter, you need only make
  // a single change.  Simply modify the value of the parameter string below.
  //--------------------------------------------------------------------------
  private final String PARAM_FPS          = "FPS";
  private final String PARAM_showControls = "ShowControls";
  private final String PARAM_speed        = "Speed";
  private final String PARAM_dynamics     = "Dynamics";
  private final String PARAM_resolution   = "Resolution";
  private final String PARAM_numSteps     = "NumSteps";

  /**
   * Handles the internal scroll bar.  Do not script.
   * @param evt
   * @return true if successful
   * @y.exclude
   */
  public boolean handleEvent(Event evt) {
    if((evt.id==Event.SCROLL_ABSOLUTE)||(evt.id==Event.SCROLL_LINE_DOWN)||(evt.id==Event.SCROLL_LINE_UP)
        ||(evt.id==Event.SCROLL_PAGE_DOWN)||(evt.id==Event.SCROLL_PAGE_UP)) {
      speedLabel.setText(label_speed+speedBar.getValue()/100.0);
      m_speed = speedBar.getValue()/100.0;
      boolean isRunning = clock.isRunning();
      clock.stopClock();
      dc.setSpeed(m_speed);
      if(isRunning) {
        clock.startClock();
      }
      return true;
    }
    return super.handleEvent(evt);
  }

  /**
   * Performs a user interface action such as a button click.  Do not script.
   * @param evt
   * @param arg
   * @return true if successful
   * @y.exclude
   */
  public boolean action(Event evt, Object arg) {
    if(evt.target.equals(dynaChoice)) {
      boolean isRunning = clock.isRunning();
      clock.stopClock();
      if(((String) arg).equalsIgnoreCase(label_sho)) {
        dc.setState(new SHOState());
      } else if(((String) arg).equalsIgnoreCase(label_sync)) {
        dc.setState(new SyncState());
      } else if(((String) arg).equalsIgnoreCase(label_inertial)) {
        dc.setState(new InertialState());
      } else if(((String) arg).equalsIgnoreCase(label_wiggler)) {
        dc.setState(new WigglerState());
      }
      dc.setSpeed(m_speed);
      if(isRunning) {
        clock.startClock();
      } else {
        dc.repaint();
      }
    } else if(evt.target.equals(runBtn)) {
      if(!clock.isRunning()) {
        clock.startClock();
        dc.message = label_running;
        runBtn.setLabel(button_stop);
      } else {
        clock.stopClock();
        runBtn.setLabel(button_start);
        dc.message = "";
        dc.repaint();
      }
    } else {
      return false;  //let the container handle the event
    }
    return true;
  }

  /**
   * Constructor Sync
   * @y.exclude
   */
  public Sync() {}

  /**
   * Method getAppletInfo
   *
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Name: Sync Ver 1.1c\r\n"+"Author: Wolfgang Christian\r\n"+"Email: wochristian@davidson.edu";
  }

  /**
   * Method getParameterInfo
   *
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String[][] info = {
      {PARAM_FPS, "int", "FPS for screen redraws."}, {PARAM_speed, "double", "Maximum speed of charge."},
      {PARAM_dynamics, "String", "Type of motion."}, {PARAM_resolution, "int", "Drawing resolution for field lines."},
    };
    return info;
  }

  protected void setResources() {
    button_stop    = localProperties.getProperty("button.stop", button_stop);
    button_start   = localProperties.getProperty("button.start", button_start);
    label_speed    = localProperties.getProperty("label.speed", label_speed);
    label_running  = localProperties.getProperty("label.running", label_running);
    label_wiggler  = localProperties.getProperty("label.wiggler", label_wiggler);
    label_sho      = localProperties.getProperty("label.sho", label_sho);
    label_sync     = localProperties.getProperty("label.sync", label_sync);
    label_inertial = localProperties.getProperty("label.inertial", label_inertial);
    label_caption  = localProperties.getProperty("label.caption", label_caption);
  }

  /**
   * Initializes the applet.  Do not script.
   * @y.exclude
   */
  public void init() {
    initResources(null);
    dynaSys.addElement(new String(label_wiggler));
    dynaSys.addElement(new String(label_sho));
    dynaSys.addElement(new String(label_sync));
    dynaSys.addElement(new String(label_inertial));
    try {
      showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      m_speed = Double.valueOf(this.getParameter("Speed", "0.5")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      m_FPS = Integer.parseInt(this.getParameter("FPS", "20"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      numSteps = Integer.parseInt(this.getParameter("NumSteps", "100"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      m_resolution = Integer.parseInt(this.getParameter("Resolution", "2"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      m_dynamics = this.getParameter("Dynamics", label_wiggler);
    } catch(Exception e) {
      e.printStackTrace();
    }
    /** @j2sNative */{
    	  resize(320, 360);
    }
    //sleepTime = (int)Math.round(1000/m_FPS);
    setLayout(new BorderLayout());
    Panel p = new Panel();
    p.setLayout(new GridLayout(1, 2));
    p.setBackground(Color.lightGray);
    Border pb = new Border(p, 1, 10);  //place the panel inside a border
    pb.setBackground(Color.lightGray);
    if(showControls) {
      add("South", pb);
    }
    Panel p1 = new Panel();
    p1.setLayout(new GridLayout(1, 1));
    speedBar = new Scrollbar(Scrollbar.HORIZONTAL, (int) (100*m_speed), 4, 0, 95);
    p1.add(new Border(speedBar, 1, 5));
    p.add(p1);
    //Panel p2=new Panel();
    //p2.setLayout(new GridLayout(1,2));
    //p2.add(new Label("Resolution"));
    //p2.add(resFld=new TextField(""+m_resolution,3));
    //p.add(p2);
    Panel p3 = new Panel();
    p3.setLayout(new GridLayout(1, 2));
    p3.add(speedLabel = new Label(label_speed+m_speed));
    p3.add(runBtn = new Button(button_start));
    p.add(p3);
    Panel p4 = new Panel();
    p4.setLayout(new GridLayout(1, 3));
    p4.add(new Label(label_caption));
    p4.add(new Label(""));
    p4.add(dynaChoice = new Choice());
    for(int i = 0; i<dynaSys.size(); i++) {
      dynaChoice.addItem((String) dynaSys.elementAt(i));
    }
    if(showControls) {
      add("North", p4);
    }
    dc = new SyncCanvas(this);
    dc.setBackground(Color.white);
    add("Center", dc);
    dc.setResolution(m_resolution);
    setDynamics(m_dynamics);
    dc.setSpeed(m_speed);
    dc.setNumSteps(numSteps);
    clock.setDt(m_resolution);
    clock.setFPS(m_FPS);
    clock.addClockListener(this);  // have the clock call the step function in this applet at every tick.
  }

  /**
   * Destroys the applet when the html page is destroyed.  Do not script.
   * @y.exclude
   */
  public void destroy() {
    destroyed = true;
    clock.stopClock();
    super.destroy();
  }

  /**
   * Counts the number of applets on the html page.
   *
   * @return the number of applets
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
   * Starts the applet when the html pages is loaded.  Do not script.
   * @y.exclude
   */
  public void start() {
    super.start();
    if(firstTime) {
      firstTime = false;
      setDynamics(this.m_dynamics);
      //clock.startClock();
    }
  }

  /**
   * Starts the animation.
   */
  public void forward() {
    runBtn.setLabel(button_stop);
    clock.startClock();
    dc.message = label_running;
  }

  /**
   * Pauses the animation.
   */
  public void pause() {
    runBtn.setLabel(button_start);
    clock.stopClock();
    dc.message = "";
    dc.repaint();
  }

  /**
   * Pauses the animation.
   *
   * Same as pause() method.
   */
  protected void pausingClock() {
    pause();
  }

  /**
   * Advance the time by dt and then update data connections. Do not script.
   *
   * @param dt the time increment.
   * @param t
   * @y.exclude
   */
  public void step(double dt, double t) {
    for(int c = 0; c<5; c++) {
      dc.incTime();  // this will also draw;
    }
    updateDataConnections();
    dc.repaint();
  }

  /**
   *   Sets the default conditions and the clock to time=0.
   */
  public void setDefault() {
    pause();
    clock.setTime(0);
    this.deleteDataConnections();
    dc.resetSource();
  }

  /**
   * Sets the dynamics of the charge.
   *
   * Charge dynamics can be any of the following: Wiggler, SHO, Sync, Inertial
   *
   * @param dyna the dynamics
   */
  public void setDynamics(String dyna) {
    m_dynamics = dyna;
    if(dyna.equals("SHO")) {
      dc.setState(new SHOState());
      dynaChoice.select(label_sho);
    } else if(dyna.equals("Sync")) {
      dc.setState(new SyncState());
      dynaChoice.select(label_sync);
    } else if(dyna.equals("Inertial")) {
      dc.setState(new InertialState());
      dynaChoice.select(label_inertial);
    } else if(dyna.equals("Wiggler")) {
      dc.setState(new WigglerState());
      dynaChoice.select(label_wiggler);
    }
  }

  /**
   * Sets the caption.
   *
   * @param s
   */
  public void setCaption(String s) {
    dc.caption = s;
  }

  /**
   * Sets the maximum speed of the charge.
   *
   * @param v the speed
   */
  public void setSpeed(double v) {
    m_speed = v;
    speedLabel.setText(label_speed+m_speed);
    speedBar.setValue((int) (m_speed*100));
    boolean isRunning = clock.isRunning();
    clock.stopClock();
    dc.setSpeed(m_speed);
    if(isRunning) {
      clock.startClock();
    } else {
      dc.repaint();
    }
  }
}
