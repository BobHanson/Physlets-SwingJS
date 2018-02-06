/**
 **************************************************************************
 **
 **                      Class  faraday.Faraday
 **
 **************************************************************************
 **
 ** class Faraday extends Physlet
 **
 ** The main entry point and Faraday applet.
 **
 ************************************************************************
 */

package faraday;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.display.*;

/**
 * Simulates the Faraday effect using a sliding wire.
 *
 * @author   Wolfgang Christian
 * @version  $Revision: 1.1 $
 */

public class Faraday
    extends SApplet
    implements SStepable {
  String button_reset = "Clear";
  String button_start = "Run";
  String button_stop = "Stop";
  String button_forward = ">>";
  String label_udrag = "U Drag";
  String label_yaxis = "Voltage (mV)";
  String label_xaxis = "Time(s)";
  String label_bxt = "B(x,t)";
  String label_xt = "x(t)";
  String label_time = "Time:";
  String label_current = "Current";

  int pixPerUnit;
  int fps;
  boolean showControls;
  String fieldStr;
  String posStr;
  String stopMessage = null;
  boolean showGraph;
  EtchedBorder etchedBorder1 = new EtchedBorder();
  EtchedBorder etchedBorder2 = new EtchedBorder();
  EtchedBorder etchedBorder3 = new EtchedBorder();
  Button runBtn = new Button();
  Button resetBtn = new Button();
  EtchedBorder etchedBorder4 = new EtchedBorder();
  Panel panel1 = new Panel();
  Panel panel2 = new Panel();
  Label label1 = new Label();
  Label label2 = new Label();
  TextField posField = new TextField();
  TextField fieldField = new TextField();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  EtchedBorder etchedBorder7 = new EtchedBorder();
  SGraph graph = new SGraph();
  Schematic schematic = new Schematic(this);
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout7 = new BorderLayout();

  private boolean dragMode = true;
  private double maxTime = 10; // max time in seconds.
  private boolean uwireExists = false;
  private int schematicWidth = 200;
  private boolean showSchematic = true;
  private boolean showGrid = true;
  private boolean showColor = true;
  private boolean showCurrentArrow = true;
  Button forwardBtn = new Button();
  Checkbox dragBox = new Checkbox();

  /**
   * @y.exclude
   */
  public Faraday() {
  }

  protected void setResources() {
    button_reset = localProperties.getProperty("button.reset", button_reset);
    button_start = localProperties.getProperty("button.start", button_start);
    button_stop = localProperties.getProperty("button.stop", button_stop);
    button_forward = localProperties.getProperty("button.forward",
                                                 button_forward);
    label_udrag = localProperties.getProperty("label.udrag", label_udrag);
    label_yaxis = localProperties.getProperty("label.yaxis", label_yaxis);
    label_xaxis = localProperties.getProperty("label.xaxis", label_xaxis);
    label_bxt = localProperties.getProperty("label.bxt", label_bxt);
    label_xt = localProperties.getProperty("label.xt", label_xt);
    label_time = localProperties.getProperty("label.time", label_time);
    label_current = localProperties.getProperty("label.current", label_current);
  }

  /**
   * @y.exclude
   */
  public void init() {
    initResources(null);
    double dt = 0.1;
    boolean showMeter = true;
    ;
    try {
      maxTime = Double.valueOf(this.getParameter("MaxTime", "10")).doubleValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      pixPerUnit = Integer.parseInt(this.getParameter("PixPerUnit", "10"));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      schematicWidth = Integer.parseInt(this.getParameter("SchematicWidth",
          "200"));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      fps = Integer.parseInt(this.getParameter("FPS", "10"));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).
          booleanValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      fieldStr = this.getParameter("FieldFunction", "10*sin(pi*x/5)");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      posStr = this.getParameter("PositionFunction", "1.0+3.0*t");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      showSchematic = Boolean.valueOf(this.getParameter("ShowSchematic", "true")).
          booleanValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      showGraph = Boolean.valueOf(this.getParameter("ShowGraph", "true")).
          booleanValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      showGrid = Boolean.valueOf(this.getParameter("ShowGrid", "true")).
          booleanValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      showColor = Boolean.valueOf(this.getParameter("ShowColor", "true")).
          booleanValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      showCurrentArrow = Boolean.valueOf(this.getParameter("ShowCurrentArrow",
          "true")).booleanValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      showMeter = Boolean.valueOf(this.getParameter("ShowMeter", "true")).
          booleanValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      dragMode = Boolean.valueOf(this.getParameter("DragMode", "true")).
          booleanValue();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    schematic.showSchematic = showSchematic;
    clock.setDt(dt);
    clock.setFPS(fps);
    clock.setCycle(0, maxTime);
    clock.addClockListener(this);

    if (!showControls)
      etchedBorder1.setVisible(false);
    this.autoRefresh = false;
    posField.setText(posStr);
    posField.setEnabled(!dragMode);
    dragBox.setState(dragMode);
    fieldField.setText(fieldStr);
    graph.setEnableMouse(true);
    setBackground(Color.lightGray);
    graph.setBackground(Color.white);
    graph.setVisible(showGraph);
    setRunningID(this);
    graph.deleteAllSeries();
    graph.setAutoscaleX(false);
    graph.setAutoscaleY(false);
    graph.setBorders("0,10,10,5");
    graph.setSeriesStyle(1, Color.red, true, 0);
    graph.setMinMaxX(0, maxTime);
    //graph.setMinMaxY(-250,250);
    graph.setAutoscaleY(true);
    schematic.parsePosFunction(posStr);
    schematic.parseFieldFunction(fieldStr);
    schematic.pixPerUnit = pixPerUnit;
    schematic.setDragMode(dragMode);
    schematic.fillApplet = !showGraph;
    schematic.setPreferredWidth(schematicWidth);
    schematic.showGrid = showGrid;
    schematic.showColor = showColor;
    schematic.setShowCurrentArrow(showCurrentArrow);
    schematic.setShowMeter(showMeter);
    this.autoRefresh = true;
  }

  //Component initialization
  private void jbInit() throws Exception {
    this.setSize(new Dimension(669, 401));
    runBtn.setLabel(button_start);
    runBtn.addActionListener(new Faraday_runBtn_actionAdapter(this));
    resetBtn.setLabel(button_reset);
    resetBtn.addActionListener(new Faraday_resetBtn_actionAdapter(this));
    label1.setAlignment(2);
    label1.setText(label_bxt);
    label2.setAlignment(2);
    label2.setText("   " + label_xt);
    graph.setSampleData(false);
    graph.setLabelY(label_yaxis);
    forwardBtn.setLabel(button_forward);
    dragBox.setLabel(label_udrag);
    dragBox.setBackground(Color.lightGray);
    dragBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        dragBox_itemStateChanged(e);
      }
    });
    fieldField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fieldField_actionPerformed(e);
      }
    });
    posField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        posField_actionPerformed(e);
      }
    });
    forwardBtn.addActionListener(new Faraday_forwardBtn_actionAdapter(this));
    graph.setLabelX(label_xaxis);
    etchedBorder4.setLayout(borderLayout4);
    panel2.setLayout(borderLayout1);
    panel1.setLayout(borderLayout2);
    etchedBorder3.setLayout(borderLayout6);
    etchedBorder1.setLayout(borderLayout3);
    etchedBorder2.setLayout(flowLayout1);
    etchedBorder7.setLayout(borderLayout7);
    this.setLayout(borderLayout5);
    this.add(etchedBorder4, BorderLayout.CENTER);
    etchedBorder4.add(graph, BorderLayout.CENTER);
    etchedBorder7.add(schematic, BorderLayout.CENTER);
    etchedBorder4.add(etchedBorder7, BorderLayout.WEST);
    this.add(etchedBorder1, BorderLayout.SOUTH);
    etchedBorder1.add(etchedBorder2, BorderLayout.WEST);
    etchedBorder2.add(dragBox, null);
    etchedBorder2.add(runBtn, null);
    etchedBorder2.add(resetBtn, null);
    etchedBorder2.add(forwardBtn, null);
    etchedBorder1.add(etchedBorder3, BorderLayout.CENTER);
    etchedBorder3.add(panel1, BorderLayout.NORTH);
    panel1.add(label2, BorderLayout.WEST);
    panel1.add(posField, BorderLayout.CENTER);
    etchedBorder3.add(panel2, BorderLayout.CENTER);
    panel2.add(label1, BorderLayout.WEST);
    panel2.add(fieldField, BorderLayout.CENTER);
  }

  /**
   *    Starts the animation.  Animation is in real time using the computer's internal clock.
   *
   */
  public void forward() {
    schematic.setMessage(null);
    if (clock.getDt() < 0)
      clock.setDt( -clock.getDt()); // make sure dt is positive
    runBtn.setLabel(button_stop);
    clock.startClock();
  }

  /**
   * Resumes the simulation with a negative time step.
   */
  public void reverse() {
    schematic.setMessage(null);
    if (clock.getDt() > 0)
      clock.setDt( -clock.getDt()); // make sure dt is negative
      //schematic.setMessage(null);
    runBtn.setLabel(button_stop);
    clock.startClock();
  }

  /**
   * Steps one positive time step.
   */
  public void stepTimeForward() {
    if (clock.isRunning()) {
      clock.stopClock();
    }
    double dt = clock.getDt();
    if (dt < 0) {
      clock.setDt( -dt); // make sure dt is positive
    }
    //odePanel.setMessage(null);
    clock.doStep();
  }

  /**
   * Steps forward one time step.
   */
  public void stepForward() {
    stepTimeForward();
  }

  /**
   * Steps the simulation one negative time step.
   */
  public void stepTimeBack() {
    if (clock.isRunning()) {
      clock.stopClock();
    }
    double dt = clock.getDt();
    if (dt > 0) {
      clock.setDt( -dt); // make sure dt is negative
    }
    //odePanel.setMessage(null);
    clock.doStep();
  }

  public void stepBack() {
    stepTimeBack();
  }

  /**
   * Destroys all threads and cleanup the applet.
   * @y.exclude
   */
  public void destroy() {
    clock.stopClock();
    graph.destroy();
    super.destroy();
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
    if (firstTime)
      return 0;
    else
      return super.getAppletCount();
  }

  /**
   * @y.exclude
   */
  public void start() {
    if (firstTime) {
      firstTime = false;
      graph.setOwner(this);
      schematic.paint(); // this will set the iwidth and iheight
      schematic.invalidateOSI();
      int id = 0;
      if (showSchematic) {
        id = this.addObject("uwire", "x=0");
        this.setDragable(id, dragMode);
        forward();
      }
    }
    super.start();
  }

  public void step(double dt, double time) {
    double v = schematic.step(dt, time);
    if (time > 0)
      graph.addDatum(1, time + dt, v);
  }

  public void cyclingClock() {
    schematic.resetTime();
    graph.clearSeriesData(1);
    clearAllData();
  }

  /**
   * Gets the applet info.
   * @y.exclude
   * @return the info
   */
  public String getAppletInfo() {
    return "Applet Information";
  }

  /**
   *    Gets the ID for the graph. The graph is a data listener.
   *
   */
  public int getGraphID() {
    return graph.getID();
  }

  /**
   * Gets the parameter info.
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String pinfo[][] = {
        {
        "MaxTime", "double",
        "Length of time the animation should run in seconds."}
        , {
        "PixPerUnit", "int", "Pixesl per unit"}
        , {
        "FPS", "int", "Frames per second in anmination"}
        , {
        "ShowControls", "boolean", "Show controls"}
        , {
        "FieldFunction", "String", "Magnetic Field: B(x,t)"}
        , {
        "PositionFunction", "String", "The postion of the wire: x(t)"}
        , {
        "ShowSchematic", "boolean", "Show the schematic at start up."}
        , {
        "ShowGraph", "boolean", "Show the voltage graph: V(t)."}
        , {
        "DragMode", "boolean", "Let the user drag the wire."}
        ,
    };
    return pinfo;
  }

  void runBtn_actionPerformed(ActionEvent e) {
    // time=0;
    //  graph.clearSeriesData(1);
    fieldField_actionPerformed(e);
    posField_actionPerformed(e);
    if (clock.isRunning()) {
      clock.stopClock();
      runBtn.setLabel(button_start);
    }
    else {
      clock.startClock();
      runBtn.setLabel(button_stop);
    }
  }

  /**
   *    Stops the applet, clears the graph, and puts the applet into a predfined state.
   *
   */
  public synchronized void setDefault() {
    this.deleteDataConnections();
    clock.stopClock();
    schematic.setDefault();
    uwireExists = false;
    runBtn.setLabel(button_start);
    clock.setTime(0);
    setPosFunction(null);
    showSchematic = true;
    schematic.showSchematic = true;
    graph.clearSeriesData(1);
    if (autoRefresh)
      schematic.repaint();
  }

  /**
   *    Stops the applet, clears the graph, and puts the applet into a predfined state.
   *
   */
  public synchronized void reset() {
    clock.stopClock();
    runBtn.setLabel(button_start);
    clock.setTime(0);
    schematic.resetTime();
    graph.clearSeriesData(1);
    clearAllData();
    if (autoRefresh)
      schematic.repaint();
  }

  void resetBtn_actionPerformed(ActionEvent e) {
    reset();
  }

  /**
   * Creates an object and add it to the Physlet.
   * The first argument is the name of the object to be added and the second is a
   * comma-delimited list of parameters.  For example, a circle can be added a follows:
   *<p>
   * <code>addObject ("circle", "x = 0, y = -1.0, r = 10");</code>
   * </p>
   * See the supplemental documentation for a list of
   * <a href="doc-files/faraday_addobject.html">object names and parameters.</a>
   *
   * @param              name the type of object to be created.
   * @param              parList a list of parameters to be set
   * @return             id that identifies the object.
   */
  public synchronized int addObject(String name, String parList) {
    Thing t = null;
    double x = 0;
    double y = 0;
    int width = 20;
    int height = 20;
    int r = 10;
    name = name.toLowerCase().trim();
    name = SUtil.removeWhitespace(name);
    String parList2 = parList.trim(); ;
    parList = SUtil.removeWhitespace(parList);
    if (name.equals("uwire")) {
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (!uwireExists)
        t = new UWire(this, schematic, x);
      else
        uwireExists = true;
    }
    else if (name.equals("constraint")) {
      double xmin = 0, xmax = 0, ymin = 0, ymax = 0;
      if (SUtil.parameterExist(parList, "xmin="))
        xmin = SUtil.getParam(parList, "xmin=");
      if (SUtil.parameterExist(parList, "ymin="))
        ymin = SUtil.getParam(parList, "ymin=");
      if (SUtil.parameterExist(parList, "xmax="))
        xmax = SUtil.getParam(parList, "xmax=");
      if (SUtil.parameterExist(parList, "ymax="))
        ymax = SUtil.getParam(parList, "ymax=");
      t = new Constraint(this, schematic, xmin, xmax, ymin, ymax);
    }
    else if (name.equals("box")) {
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "w="))
        width = (int) SUtil.getParam(parList, "w=");
      if (SUtil.parameterExist(parList, "h="))
        height = (int) SUtil.getParam(parList, "h=");
      t = new FluxBox(this, schematic, x, y, width, height);
    }
    else if (name.equals("rectangle")) {
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "w="))
        width = (int) SUtil.getParam(parList, "w=");
      if (SUtil.parameterExist(parList, "h="))
        height = (int) SUtil.getParam(parList, "h=");
      t = new FluxRectangle(this, schematic, x, y, width, height);
    }
    else if (name.equals("circle")) {
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "r="))
        r = (int) SUtil.getParam(parList, "r=");
      t = new FluxCircle(this, schematic, x, y, r);
    }
    else if (name.equals("cursor")) {
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "r="))
        r = (int) SUtil.getParam(parList, "r=");
      t = new MarkerThing(this, schematic, 2 * r + 1, x, y);
    }
    else if (name.equals("shell")) {
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "r="))
        r = (int) SUtil.getParam(parList, "r=");
      t = new FluxShell(this, schematic, x, y, r);
    }
    else if (name.equals("arrow")) {
      double h = 1, v = 1;
      int s = 4;
      if (SUtil.parameterExist(parList, "s="))
        s = (int) SUtil.getParam(parList, "s=");
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "h="))
        h = SUtil.getParam(parList, "h=");
      if (SUtil.parameterExist(parList, "v="))
        v = SUtil.getParam(parList, "v=");
      t = new ArrowThing(this, schematic, s, h, v, x, y);
    }
    else if (name.equals("text")) {
      String txt = "";
      String calc = null;
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "txt="))
        txt = SUtil.getParamStr(parList2, "txt=");
      if (SUtil.parameterExist(parList, "text="))
        txt = SUtil.getParamStr(parList2, "text=");
      if (SUtil.parameterExist(parList, "calc="))
        calc = SUtil.getParamStr(parList, "calc=");
      t = new CalcThing(this, schematic, txt, calc, x, y);
    }
    else if (name.equals("image")) {
      String file = " ";
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "gif="))
        file = SUtil.getParamStr(parList, "gif=");
      if (SUtil.parameterExist(parList, "file="))
        file = SUtil.getParamStr(parList, "file=");
      if (file == null)
        return 0;
      Image im = edu.davidson.graphics.Util.getImage(file, this);
      if (im != null)
        t = new ImageThing(this, schematic, im, x, y);
      else
        t = null;
    }
    else if (name.equals("caption")) {
      String txt = "";
      if (SUtil.parameterExist(parList, "x="))
        x = SUtil.getParam(parList, "x=");
      if (SUtil.parameterExist(parList, "y="))
        y = SUtil.getParam(parList, "y=");
      if (SUtil.parameterExist(parList, "txt="))
        txt = SUtil.getParamStr(parList2, "txt=");
      if (SUtil.parameterExist(parList, "text="))
        txt = SUtil.getParamStr(parList2, "text=");
      t = new CaptionThing(this, schematic, txt, x, y);
    }
    if (t == null) {
      System.out.println("Object not created. name:" + name + "parameter list:" +
                         parList);
      return 0;
    }
    schematic.addThing(t);
    if (autoRefresh)
      schematic.repaint();
    return t.hashCode();
  }

  /**
   * Sets a motion constraint on an object.
   *
   * @param              id the ID of the object.
   * @param              constraintID the ID of the constraint.
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setConstraint(int id, int constraintID) {
    Thing t = schematic.getThing(id);
    Thing c = schematic.getThing(constraintID);
    if (t == null)
      return false;
    if (c == null)
      return false;
    if (! (c instanceof Constraint))
      return false;
    t.setConstraint( (Constraint) c);
    return true;
  }

  /**
   * Sets the autoRefresh property. AutoRefresh will repaint the graph whenever
   * data changes but slows down the system and may cause flicker.
   *
   * @param              auto AutoRefresh the graph?
   */
  public synchronized void setAutoRefresh(boolean auto) {
    if (autoRefresh == auto)
      return;
    autoRefresh = auto;
    graph.setAutoRefresh(auto);
    if (!autoRefresh)
      return;
    schematic.invalidateOSI();
    schematic.paint();
    graph.repaint();
  }

  /**
       *    Show a color corresponding to the local magnetic field on the schematic.
   *
   *    @param show         Show the color.
   */
  public void setShowColor(boolean show) {
    showColor = show;
    schematic.showColor = showColor;
    if (autoRefresh)
      schematic.repaint();
  }

  /**
   *    Show a dot or an x corresponding to the local magnetic field on the schematic.
   *
   *    @param show         Show the grid.
   */
  public void setShowGrid(boolean show) {
    showGrid = show;
    schematic.showGrid = showGrid;
    if (autoRefresh)
      schematic.repaint();
  }

  /**
   *    Show the Magnetic field on mouse drag.
   *
   *    @param show         Show B.
   */
  public void setShowBOnDrag(boolean show) {
    schematic.showBOnDrag = show;
  }

  /**
   *    Show the current arrow on the schematic.
   *
   *    @param id           the object identifier
   *    @param show         true to show the current arrow.
   */
  public void setShowCurrentArrow(int id, boolean show) {
    showCurrentArrow = show;
    schematic.setShowCurrentArrow(id, show);
    if (autoRefresh)
      schematic.repaint();
  }

  /**
   * Have the object show its coordinates on screen.
   *
   * @param              id The id of the object.
   * @param              show Show the coordinates?
   * @return             True if successful.
   */
  public boolean setShowCoordinates(int id, boolean show) {
    if (id == 0 || id == this.schematic.hashCode()) {
      schematic.coordDisplay = show;
      return true;
    }
    return false;
  }

  /**
   *    Show the meter on the UWire schematic.
   *
   *    @param id       the object identifier of the UWire
   *    @param show     true to show the meter.
   */
  public void setShowMeter(int id, boolean show) {
    schematic.setShowMeter(id, show);
    if (autoRefresh)
      schematic.repaint();
  }

  /**
   *    Sets the drag mode on the wire.  Will disable the postion function
   *
   *    @param dm         Boolean drag mode.
   */
  public void setDragMode(boolean dm) {
    dragMode = dm;
    dragBox.setState(dragMode);
    posField.setEnabled(!dm);
    schematic.setDragMode(dm);
  }

  /**
   *    Sets the range on the meter and the graph.
   *
   *    @param min         The minimum value.
   *    @param max         The maximum value.
   */
  public void setMeterMinMax(double min, double max) {
    schematic.setMeterMinMax(min, max);
    graph.setMinMaxY(min, max);
    if (autoRefresh) {
      graph.repaint();
      schematic.repaint();
    }
  }

  /**
   *    Sets the scale.  All functions and readings will use this scale factor.
   *
   *    @param ppu         pPxels per unit.
   */
  public void setPixPerUnit(int ppu) {
    pixPerUnit = ppu;
    schematic.pixPerUnit = pixPerUnit;
  }

  /**
   *    Display the graph, V(t).
   *
   *    @param sg         True will display V(t).
   */
  public void setShowGraph(boolean sg) {
    if (graph.isVisible() == sg)
      return;
    showGraph = sg;
    schematic.fillApplet = !showGraph;
    graph.setVisible(sg);
    invalidate();
    validate();
  }

  /**
   *    Sets the slider postion as a function of time.
   *
   *    @param str         The position of the slider, x(t).
   *
       *    @return            boolean  true if the functions is valid, false otherwise
   *
   *    @see #setDragMode
   */
  public synchronized boolean setPosFunction(String str) {
    boolean shouldRun = clock.isRunning();
    clock.stopClock();
    if (str == null)
      str = "";
    posStr = str;
    posField.setText(posStr);
    boolean err = schematic.parsePosFunction(posStr);
    if (shouldRun)
      clock.startClock();
    else if (autoRefresh)
      schematic.repaint();
    return err;
  }

  /**
   * Sets the trajectory of an object on the screen.
   *
   * @param              id the id of the thing
   * @param              xStr the function x(t)
   * @return             boolean  True if the function is valid and the
   *                     trajectory has been set.
   */
  public boolean setTrajectory(int id, String xStr) {
    Thing t = schematic.getThing(id);
    if (t == null)
      return false;
    schematic.setDefaultCircuit(t);
    return setPosFunction(xStr);
  }

  /**
   *    Sets the magnetic field, B(x,t), into or out of the wire loop. Can be a function of x but not y.
   *
   *    @param str       The magnetic field, B(x,t).
   */
  public synchronized void setFieldFunction(String str) {
    boolean shouldRun = clock.isRunning();
    clock.stopClock();
    fieldStr = str;
    fieldField.setText(fieldStr);
    schematic.parseFieldFunction(fieldStr);
    if (shouldRun)
      clock.startClock();
    else if (autoRefresh)
      schematic.repaint();
  }

  /**
   * Sets the range corresponding to blue and red for the B field color.
   *
   * @param              min the bfield minimum.
   * @param              max the bfield maximum.
   */
  public void setBScale(double min, double max) {
    schematic.setBScale(min, max);
    if (autoRefresh)
      schematic.repaint();
  }

  /**
   *    Sets the maximum time for the graph and for cycle mode.
   *
   *    @param tm        maximum time
   *
   * @see                #setTimeContinuous
   * @see                #setTimeCycle
   *
   */
  public void setMaxTime(double tm) {
    maxTime = tm;
    clock.setCycle(0, maxTime);
    graph.setMinMaxX(0, maxTime);
  }

  /**
   * Sets a time loop for the animation from 0 to max. Animation will run continuously.
   *
   * @param              max The ending time for the loop.
   * @see                #setTimeContinuous
   * @see                #setTimeInterval
   */
  public void setTimeCycle(double max) {
    setTimeInterval(0, max);
  }

  /**
   * Sets a time loop for the animation. Animation will run continuously.
   *
   * @param              min The starting time value for the loop
   * @param              max The ending time for the loop.
   * @see                #setTimeContinuous
   */
  public void setTimeInterval(double min, double max) {
    clock.setCycle(min, max);
    schematic.setTime(min);
    //schematic.setMessage(null);
  }

  /**
   * Let the animation time increase indefinitely.  May overflow for very long
   * times.
   *
   * @see                #setTimeOneShot
   * @see                #setTimeInterval
   */
  public void setTimeContinuous() {
    clock.setContinuous();
    //schematic.setMessage(null);
  }

  /**
   * Run the simulation one time.
   *
   * @param     max Reset the simulation to t=0 when t>=max and stop the simulation.
   * @param     string the message to be displayed on the screen when the max time is reached.
   */
  public void setTimeOneShot(double max, String msg) {
    clock.setOneShot(0, max);
    stopMessage = msg;
  }

  protected void stoppingClock() {
    schematic.setMessage(stopMessage);
    //System.out.println(stopMessage);
  }

  /**
   * Sets the object's font.  Invoke setObjectFont for Java 1.4 compatibility.
   */
  public boolean setFont(int id, String family, int style, int size) {
    Font font = new Font(family, style, size);
    //if(id==this.animatorCanvas.hashCode()){animatorCanvas.font=font; return true;}
    Thing t = schematic.getThing(id);
    if (t == null || font == null)
      return false;
    t.setFont(font);
    if (autoRefresh)
      schematic.repaint();
    return true;
  }

  /**
   * Sets the object's font if the object has text that can be displayed.
   *
   * @param              id The id of the object.
   * @param              family The font family: Helvetica, Times.
   * @param              style The style, 0=plain, 1=bold.
   * @param              size The size of the font;
   * @return             true if successful.
   */
  public boolean setObjectFont(int id, String family, int style, int size) {
    return setFont(id, family, style, size);
  }

  /**
   * Change the object's format for the display of numeric data.
   *
   * Us this method to control the number of significant digits in calculations with text objects.
   * Use Unix printf conventions.  For example fstr="%6.3f"
   *
   * @param              id The id of the object.
   * @param              fstr the format string.
   * @return             True if successful.
   */
  public boolean setFormat(int id, String fstr) {
    Thing t = schematic.getThing(id);
    if (t == null && (id == 0 || id == schematic.hashCode())) {
      return schematic.setFormat(fstr);
    }
    boolean result = t.setFormat(fstr);
    if (autoRefresh)
      schematic.repaint();
    return result;
  }

  /**
   * Offset the object's position on the screen from its default drawing
   * position.
   *
   * @param              id The id of the object.
   * @param              xoff The x offset.
   * @param              yoff The y offset.
   * @return             True if successful.
   */
  public boolean setDisplayOffset(int id, int xOff, int yOff) {
    Thing t = schematic.getThing(id);
    if (t == null)
      return false;
    t.setDisplayOff(xOff, yOff);
    if (autoRefresh)
      schematic.repaint();
    return true;
  }

  /**
   * Make the object with the given id dragable.
   *
   * @param              id of the object.
   * @param              canDrag Is the object dragable?
   * @return             <code>true</code> if successful <code>false</code> otherwise
   */
  public boolean setDragable(int id, boolean canDrag) {
    Thing t = schematic.getThing(id);
    if (t == null)
      return false;
    t.setDragable(canDrag);
    schematic.dragMode = canDrag;
    return true;
  }

  /**
   * Show the visibility of the object.
   *
   * @param     show    <code>true</code> will show object on screen
   *
   * @return            <code>true</code> if successful <code>false</code> otherwise
   */
  public boolean setVisibility(int id, boolean show) {
    if (id == getClockID()) {
      schematic.showTime = show;
      if (autoRefresh)
        schematic.repaint();
      return true;
    }
    Thing t = schematic.getThing(id);
    if (t == null)
      return false;
    t.setVisible(show);
    return true;
  }

  /**
   * Force an object to follow another object on the screen.
   *
   * @param              masterID The id of the master object.
   * @param              slaveID The id of the slave object.
   * @return             true if successful.
   */
  public boolean setAnimationSlave(int masterID, int slaveID) {
    Thing master = schematic.getThing(masterID);
    Thing slave = schematic.getThing(slaveID);
    if (master == null || slave == null)
      return false;
    master.addSlave(slave);
    schematic.invalidateOSI();
    if (autoRefresh)
      schematic.repaint();
    return true;
  }

  /**
   * Sets the color of an object.
   *
   * @param              id The id of the object.
   * @param              r red
   * @param              g green
   * @param              b blue
   * @return             True if successful.
   */
  public boolean setRGB(int id, int r, int g, int b) {
    if (id == this.schematic.hashCode()) {
      schematic.setBackground(new Color(r, g, b));
      return true;
    }
    Thing t = schematic.getThing(id);
    if (t == null)
      return false;
    t.setColor(new Color(r, g, b));
    if (autoRefresh)
      schematic.repaint();
    return true;
  }

  void forwardBtn_actionPerformed(ActionEvent e) {
    clock.doStep();
  }

  void posField_actionPerformed(ActionEvent e) {
    if (schematic.parsePosFunction(posField.getText()))
      posField.setBackground(Color.white);
    else
      posField.setBackground(Color.red);
  }

  void fieldField_actionPerformed(ActionEvent e) {
    if (schematic.parseFieldFunction(fieldField.getText())) {
      fieldField.setBackground(Color.white);
      schematic.repaint();
    }
    else
      fieldField.setBackground(Color.red);
  }

  void dragBox_itemStateChanged(ItemEvent e) {
    schematic.setDragMode(dragBox.getState());
    reset();
    fieldField_actionPerformed(new ActionEvent(this, 0, null));
    posField_actionPerformed(new ActionEvent(this, 0, null));
    posField.setEnabled(!dragBox.getState());
  }
}

class Faraday_runBtn_actionAdapter
    implements java.awt.event.ActionListener {
  Faraday adaptee;

  Faraday_runBtn_actionAdapter(Faraday adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.runBtn_actionPerformed(e);
  }
}

class Faraday_resetBtn_actionAdapter
    implements java.awt.event.ActionListener {
  Faraday adaptee;

  Faraday_resetBtn_actionAdapter(Faraday adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.resetBtn_actionPerformed(e);
  }
}

class Faraday_forwardBtn_actionAdapter
    implements java.awt.event.ActionListener {
  Faraday adaptee;

  Faraday_forwardBtn_actionAdapter(Faraday adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.forwardBtn_actionPerformed(e);
  }
}