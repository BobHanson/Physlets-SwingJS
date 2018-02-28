package qTime;

//import java.awt.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import a2s.*;


/**
 * Class QTime
 */
public class QTime extends SApplet implements SStepable {

  private String       button_start       = "Run";
  private String       button_stop        = "Stop";
  private String       button_reset       = "Reset";
  private String       button_submit      = "Submit Functions";
  String               label_qtime        = "QTime";
  String               label_qm           = "QM";
  String               label_help         = "Help";
  String               label_wavefunction = "Wavefunction";
  String               label_time         = "Time:";
  String               label_energy       = "Energy";
  String               label_real         = "Real";
  String               label_imaginary    = "Imaginary";
  String               label_potential    = "Potential";
  String               label_new          = "New System";
  String               label_magnitude    = "|Psi|";
  String               label_phase        = "alpha:";
  private double       t                  = 0;                               // time
  //private boolean interval=false;
  //private double  endTime=1;
  //private double       m_dt               = 0.1;
  //private int     sleepTime=50;
  Button               runBtn             = new Button("Run");               // Button to plot it.
  Button               resetBtn           = new Button("Reset");             // Button to plot it.
  Button               parseBtn           = new Button("Submit Functions");  // Button to parse analytic functions.
  TextField            potInput           = new TextField(30);               // Input for the potential
  TextField            reInput            = new TextField(30);               // Input for the real part of psi
  TextField            imInput            = new TextField(30);               // Input for the imaginary part of psi
  QTimeGraph           graph;                                                // Graph class to do the plotting
  // STANDALONE APPLICATION SUPPORT:
  //            m_fStandAlone will be set to true if applet is run standalone
  //--------------------------------------------------------------------------
  boolean              m_fStandAlone      = false;
  private String       m_potential        = "20*(step(1+x)-step(x-1))";
  private String       m_real             = "cos(2*pi*x)*exp(-(x+4)*(x+4))";
  private String       m_imaginary        = "sin(2*pi*x)*exp(-(x+4)*(x+4))";
  private int          m_numPts           = 256;
  private double       m_minX             = -10;
  private double       m_maxX             = 10;
  private boolean      m_showControls     = true;
  private int          m_FPS              = 10;
  //private boolean runOnStart=false;
  //boolean stopped=false;
  // Parameter names.  To change a name of a parameter, you need only make
  // a single change.  Simply modify the value of the parameter string below.
  //--------------------------------------------------------------------------
  private final String PARAM_FPS          = "FPS";
  private final String PARAM_dt           = "dt";
  private final String PARAM_potential    = "potential";
  private final String PARAM_real         = "real";
  private final String PARAM_imaginary    = "imaginary";
  private final String PARAM_numPts       = "numPts";
  private final String PARAM_minX         = "minX";
  private final String PARAM_maxX         = "maxX";
  private final String PARAM_showControls = "showControls";

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
      return this.getParameter(strName);
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
    param = GetParameter(PARAM_potential, args);
    if(param!=null) {
      m_potential = param;
    }
    param = GetParameter(PARAM_real, args);
    if(param!=null) {
      m_real = param;
    }
    param = GetParameter(PARAM_imaginary, args);
    if(param!=null) {
      m_imaginary = param;
    }
    param = GetParameter(PARAM_FPS, args);
    if(param!=null) {
      m_FPS = Integer.parseInt(param);
    }
    param = GetParameter(PARAM_numPts, args);
    if(param!=null) {
      m_numPts = Integer.parseInt(param);
    }
    param = GetParameter(PARAM_minX, args);
    if(param!=null) {
      m_minX = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_maxX, args);
    if(param!=null) {
      m_maxX = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_showControls, args);
    if(param!=null) {
      m_showControls = Boolean.valueOf(param).booleanValue();
    }
    param = GetParameter(PARAM_dt, args);
    if(param!=null) {
      double dt = Double.valueOf(param).doubleValue();
      clock.setDt(dt);
    }
  }

  /**
   * Constructor QTime
   * @y.exclude
   */
  public QTime() {}

  /**
   * Method getAppletInfo
   *
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Name: QTime Ver 1.2\r\n"+"Author: Wolfgang Christian\r\n"+"EMail: wochristian@davidson.edu";
  }

  /**
   * Method getParameterInfo
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String[][] info = {
      {PARAM_potential, "String", "Potential"}, {PARAM_real, "String", "Real part of wavefunction"},
      {PARAM_imaginary, "String", "Imaginary part of wavefunction"}, {PARAM_FPS, "int", "Frames per second."},
      {PARAM_dt, "double", "Time step per frame."}, {PARAM_numPts, "int", "Number of points to plot"},
      {PARAM_minX, "double", "Minimum x value"}, {PARAM_maxX, "double", "Maximum x value"},
      {PARAM_showControls, "boolean", "Show controls on screen"},
    };
    return info;
  }

  protected void setResources() {
    button_start       = localProperties.getProperty("button.start", button_start);
    button_stop        = localProperties.getProperty("button.stop", button_stop);
    button_reset       = localProperties.getProperty("button.reset", button_reset);
    button_submit      = localProperties.getProperty("button.submit", button_submit);
    label_wavefunction = localProperties.getProperty("label.wavefunction", label_wavefunction);
    label_qtime        = localProperties.getProperty("label.qtime", label_qtime);
    label_qm           = localProperties.getProperty("label.qm", label_qm);
    label_help         = localProperties.getProperty("label.help", label_help);
    label_time         = localProperties.getProperty("label.time", label_time);
    label_energy       = localProperties.getProperty("label.energy", label_energy);
    label_real         = localProperties.getProperty("label.real", label_real);
    label_imaginary    = localProperties.getProperty("label.imaginary", label_imaginary);
    label_potential    = localProperties.getProperty("label.potential", label_potential);
    label_new          = localProperties.getProperty("label.new", label_new);
    label_magnitude    = localProperties.getProperty("label.magnitude", label_magnitude);
    label_phase        = localProperties.getProperty("label.phase", label_phase);
  }

  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    runBtn.setLabel(button_start+" ");
    resetBtn.setLabel(button_reset);
    parseBtn.setLabel(button_submit);
    //runOnStart=false;
    if(!m_fStandAlone) {
      GetParameters(null);
    }
    //sleepTime=(int)Math.round(1000/m_FPS);
    /** j2sNative */{
    	  resize(400, 400);
    }
    setLayout(new BorderLayout());
    Panel p = new Panel();
    p.setLayout(new BorderLayout());
    graph = new QTimeGraph(this, m_numPts);
    if(!graph.setPotential(m_potential)) {
      System.out.println("Failed to parse potential!");
    }
    if(!graph.setReal(m_real)) {
      System.out.println("Failed to real psi!");
    }
    if(!graph.setImaginary(m_imaginary)) {
      System.out.println("Failed to parse imaginary psi!");
    }
    graph.setXMinMax(m_minX, m_maxX);
    p.add("Center", graph);
    potInput.setText(m_potential);
    reInput.setText(m_real);
    imInput.setText(m_imaginary);
    if(m_showControls) {
      TabbedPanel t = new TabbedPanel();
      add("Center", t);
      Panel p11 = new Panel();
      p11.setLayout(new GridLayout(1, 2));
      p11.add(runBtn);
      p11.add(resetBtn);
      p.add("South", p11);
      t.addItem(label_wavefunction, p);
      Panel p2 = new Panel();
      p2.setBackground(Color.lightGray);
      p2.setLayout(new GridLayout(4, 1, 10, 30));
      p2.add(new Box(reInput, label_real));
      p2.add(new Box(imInput, label_imaginary));
      p2.add(new Box(potInput, label_potential));
      p2.add(new Box(parseBtn, label_new));
      t.addItem(label_qm, p2);
      QTimeHelp hc = new QTimeHelp(this);
      t.addItem(label_help, hc);
    } else {
      add("Center", p);
    }
    reset();  // this sets the energy and the normalization.
    clock.setFPS(this.m_FPS);
    this.clock.addClockListener(this);
  }

  /**
   * Method destroy
   * @y.exclude
   */
  public void destroy() {
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
  public synchronized void start() {
    super.start();
    if(firstTime) {
      firstTime = false;
      //runBtn.setLabel(button_stop);
      //clock.startClock();
    }
  }

  /**
   * Starts the animation.
   *
   */
  public void forward() {
    runBtn.setLabel(button_stop);
    clock.startClock();
  }

  /**
   * Pauses the animation.
   */
  public void pause() {
    runBtn.setLabel(button_start+" ");
    if(clock.isRunning()) {
      clock.stopClock();
    }
  }

  /**
   * Used by clock to step the simulation.  DO NOT SCRIPT.
   *
   * @param dt
   * @param time
   * @y.exclude
   */
  public void step(double dt, double time) {
    t = t+dt;
    graph.stepState(dt);
    updateDataConnections();
  }

  /**
   * Sets the time step.
   *
   * @param dt
   */
  public void setDt(double dt) {
    clock.setDt(dt);
  }



  /**
   * Steps the time by dt.
   *
   */
  public void stepForward() {
    if(clock.isRunning()) {
      pause();
      return;
    }
    boolean isNegative = false;
    if(clock.getDt()<0) {
      isNegative = true;
    }
    clock.setDt(Math.abs(clock.getDt()));
    clock.doStep();
    if(isNegative) {
      clock.setDt(-Math.abs(clock.getDt()));  // make dt negative since it started out that way.
    }
  }

  /**
   * Step the time backward by dt.
   *
   * @see                #setDt
   */
  public void stepTimeBack() {
    if(clock.isRunning()) {
      pause();
      return;
    }
    boolean isNegative = false;
    if(clock.getDt()<0) {
      isNegative = true;
    }
    clock.setDt(-Math.abs(clock.getDt()));
    clock.doStep();
    if(!isNegative) {
      clock.setDt(Math.abs(clock.getDt()));  // make dt positive since it started out that way.
    }
  }

  /**
   * Method action
   *
   * @param ev
   * @param a
   *
   * @return if successful
   * @y.exclude
   */
  public boolean action(Event ev, Object a) {
    if(ev.target.equals(runBtn)) {
      if(clock.isRunning()) {
        runBtn.setLabel(button_start+" ");
        clock.stopClock();
      } else {
        runBtn.setLabel(button_stop);
        clock.startClock();
      }
      return true;
    }
    if(ev.target.equals(resetBtn)) {
      reset();
      // System.out.println("in Reset");
      return true;
    }
    if(ev.target.equals(parseBtn)) {
      String str;
      str = potInput.getText();
      setPotential(str);
      str = reInput.getText();
      setReal(str);
      str = imInput.getText();
      setImaginary(str);
      reset();
      return true;
    }
    return false;
  }

  /**
   * Resets the animation.
   * @deprecated
   * @y.exclude
   */
  public void reset() {
    runBtn.setLabel(button_start+" ");
    clock.stopClock();
    clock.setTime(0);
    graph.reset();
    t = 0;
    graph.repaint();
    this.updateDataConnections();
  }

  /**
   * Sets the default state.
   */
  public void setDefault(){
    reset();
  }

  /**
   * Sets the potential energy.
   *
   * @param f
   */
  public synchronized void setPotential(String f) {
    m_potential = f;
    potInput.setText(m_potential);
    if(!graph.setPotential(m_potential)) {
      potInput.setBackground(Color.red);
      System.out.println("Failed to parse potential!");
    } else {
      potInput.setBackground(Color.white);
    }
    potInput.setText(m_potential);  // this resets the color
  }

  /**
   * Sets the real component of the initial wave function.
   *
   * @param f
   */
  public synchronized void setReal(String f) {
    m_real = f;
    reInput.setText(m_real);
    if(!graph.setReal(m_real)) {
      reInput.setBackground(Color.red);
      System.out.println("Failed to parse real!");
    } else {
      reInput.setBackground(Color.white);
    }
    reInput.setText(m_real);  // this resets the color
  }

  /**
   * Sets the imaginary component of the initial wave function.
   *
   * @param f
   */
  public synchronized void setImaginary(String f) {
    m_imaginary = f;
    imInput.setText(m_imaginary);
    if(!graph.setImaginary(m_imaginary)) {
      imInput.setBackground(Color.red);
      System.out.println("Failed to parse imaginary!");
    } else {
      imInput.setBackground(Color.white);
    }
    imInput.setText(m_imaginary);  // this resets the color
  }

  /**
   * Offsets the wavefunction by the energy eigenvalue on the graph.
   *
   * @param set
   */
  public void setEnergyOffset(boolean set) {
    graph.offsetFunction = set;
    graph.repaint();
  }

  /**
   *    Show or hide the the X and Y axes on the graph. May need to set the gutters to zero seliminate white space.
   *
   *    @param show       Show the axis?
   */
  public synchronized void setShowAxes(boolean show) {
    if(graph.isShowAxis()==show) {
      return;
    }
    if(show) {
      graph.setGutters(20, 20, 20, 20);
      graph.drawgrid = true;
    } else {
      graph.setGutters(0, 0, 0, 0);
      graph.drawgrid = false;
    }
    graph.setShowAxis(show);
    graph.repaint();
  }

  /**
   * Gets the id for the wave function.
   *
   * The id is used for scripting and to make data connections.
   *
   * @return the id
   */
  public int getWavefunctionID() {
    return graph.getState().getID();
  }

  /**
   *    Show or hide the potential energy function on the graph. May need to set the gutters to zero seliminate white space.
   *
   * @param show
   */
  public synchronized void setShowPotential(boolean show) {
    if(graph.showPotential==show) {
      return;
    }
    graph.showPotential = show;
    graph.parsePotential();
    graph.repaint();
  }

  /**
   * Sets the graph color using RGB components.
   *
   * @param r
   * @param g
   * @param b
   */
  public void setRGBColor(int r, int g, int b) {
    graph.setRGBColor(r, g, b);
  }

  /**
   * Method setYMinMax
   *
   * @param min
   * @param max
   */
  public synchronized void setYMinMax(double min, double max) {
    graph.setYMinMax(min, max);
    reset();
  }

  /**
   * Sets the y autoscale to true.
   *
   */
  public synchronized void setYAutoscaleOn() {
    graph.setYManualRange(false);
  }

  /**
   * Sets the y autoscale to false.
   *
   */
  public synchronized void setYAutoscaleOff() {
    graph.setYManualRange(true);
  }

  /**
   * Sets the min and max along the x axis.
   *
   * @param min
   * @param max
   */
  public synchronized void setXMinMax(double min, double max) {
    m_minX = min;
    m_maxX = max;
    graph.setXMinMax(min, max);
  }

  /**
   * Sets the caption.
   *
   * @param s
   */
  public void setCaption(String s) {
    graph.setCaption(s);
  }

  /**
   * Sets the label on the x axis.
   *
   * @param s
   */
  public void setXTitle(String s) {
    graph.setXTitle(s);
  }

  /**
   * Sets the label on the y axis.
   *
   * @param s
   */
  public void setYTitle(String s) {
    graph.setYTitle(s);
  }
}

/**
 * Class QTimeHelp
 */
class QTimeHelp extends Panel {

  QTime                applet;
  static LayoutManager dcLayout = new FlowLayout(FlowLayout.CENTER, 10, 5);

  /**
   * Constructor QTimeHelp
   *
   * @param app
   */
  public QTimeHelp(QTime app) {
    applet = app;
    setLayout(dcLayout);
    setBackground(Color.lightGray);
    Panel p = new Panel();
    p.setLayout(new GridLayout(3, 1));
    p.add(new Label("by W. Christian.", Label.LEFT));
    p.add(new Label("Davidson College", Label.LEFT));
    p.add(new Label("email: wochristian@davidson.edu", Label.LEFT));
    Box helpBox = new Box(p, "QTime Ver 1.3");  // put into an eteched box
    add(helpBox);
  }
}
