/*
**************************************************************************
**
**                      Class  eField.EField
**
**************************************************************************
**
** class EField extends Applet
**
** The main entry point and EField applet.
**
*************************************************************************/

package eField4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.StringTokenizer;


import java.awt.Button;
import java.awt.Checkbox;
import java.awt.TextField;
import edu.davidson.display.Format;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SUtil;


/**
 * <p>EField is part of the Davidson College Physlets project.
 * EField plots electric fields given a potential function and/or point
 * charges.  Both fixed charges and moving test charges can be specified
 * using "add" methods.
 * <p>The following embedding parameters are defined:</p>
 * <div align="center">
 * <center>
 * <table border="2" width="100%">
 * <tr>
 * <th width="33%" align="center">Parameter</th>
 * <th width="33%" align="center">Value</th>
 * <th width="34%" align="center">Data Type</th>
 * <th width="34%" align="center">Description</th>
 * </tr>
 * <tr>
 * <td width="33%">FPS</td>
 * <td width="33%">10</td>
 * <td width="34%">double</td>
 * <td width="34%">Frames per second during animation.</td>
 * </tr>
 * <tr>
 * <td width="33%">dt</td>
 * <td width="33%">0.1</td>
 * <td width="34%">double</td>
 * <td width="34%">Animation time step per frame.</td>
 * </tr>
 * <tr>
 * <td width="33%">ShowControls</td>
 * <td width="33%">true</td>
 * <td width="34%">boolean</td>
 * <td width="34%">Show VCR buttons at bottom of applet.</td>
 * </tr>
 * <tr>
 * <td width="33%">ShowContours</td>
 * <td width="33%">true</td>
 * <td width="34%">boolean</td>
 * <td width="34%">Show equipotential contours.</td>
 * </tr>
 * <tr>
 * <td width="33%">ShowPoles</td>
 * <td width="33%">true</td>
 * <td width="34%">boolean</td>
 * <td width="34%">Show fixed charges.</td>
 * </tr>
 * <tr>
 * <td width="33%">ShowLabels</td>
 * <td width="33%">true</td>
 * <td width="34%">boolean</td>
 * <td width="34%">Add labels to contour lines.</td>
 * </tr>
 * <tr>
 * <td width="33%">ShowFieldLines</td>
 * <td width="33%">false</td>
 * <td width="34%">boolean</td>
 * <td width="34%">Draw field lines. Computationally EXPENSIVE</td>
 * </tr>
 * <tr>
 * <td width="33%">ShowFieldVectors</td>
 * <td width="33%">true</td>
 * <td width="34%">boolean</td>
 * <td width="34%">Draw direction arrows to represent field.</td>
 * </tr>
 * <tr>
 * <td width="33%">PointChargeMode</td>
 * <td width="33%">true</td>
 * <td width="34%">boolean</td>
 * <td width="34%">Select between point charge and line charge mode.&nbsp;
 * Use 1/(r*r) dependence for fixed charge if true.&nbsp; Use 1/r dependence
 * if false.</td>
 * </tr>
 * <tr>
 * <td width="33%">PixPerUnit</td>
 * <td width="33%">10</td>
 * <td width="34%">double</td>
 * <td width="34%">Conversion from pixel units to world units.</td>
 * </tr>
 * <tr>
 * <td width="33%">GridUnit</td>
 * <td width="33%">1.0</td>
 * <td width="34%">double</td>
 * <td width="34%">The grid spacing.&nbsp; A value of 0 will suppress the
 * grid.</td>
 * </tr>
 * <tr>
 * <td width="33%">Potential</td>
 * <td width="33%">0</td>
 * <td width="34%">string</td>
 * <td width="34%">The potential function, U(x,y).</td>
 * </tr>
 * <tr>
 * <td width="33%">Range</td>
 * <td width="33%">-1,1,-1,1</td>
 * <td width="34%">string</td>
 * <td width="34%">Approximate x and y coordinate range.&nbsp; X range takes
 * precedence to insure 1:1 aspect ratio. MUST be a string:
 * &quot;-1,1,-1,1&quot;</td>
 * </tr>
 * <tr>
 * <td width="33%">GridSize</td>
 * <td width="33%">64</td>
 * <td width="34%">&nbsp;</td>
 * <td width="34%">(Not Implemented.)</td>
 * </tr>
 * </table>
 * </center>
 * </div>
 * <p>Various objects in EField implement the data source interface.&nbsp;
 * This interface, SDataSource, enables inter-applet data passing between
 * Physlets.</p>
 * <div align="center">
 * <center>
 * <table border="2" height="115">
 * <tr>
 * <th height="19" align="center">Object</th>
 * <th height="19" align="center">Identifier</th>
 * <th height="19" align="center">Variables</th>
 * </tr>
 * <tr>
 * <td height="38">Fixed Charges.</td>
 * <td height="38">The id is returned when an object is created using an add
 * method.<br>
 * id=addCharge(double x, double y, double m)</td>
 * <td height="38">t, x, y, vx, vy, ax, ay, fx, fy, p, m, q<br>
 * Note: p is the potential and q is the magnitude of the charge.</td>
 * </tr>
 * <tr>
 * <td height="15">All shapes: circle, rectangle, box, etc.</td>
 * <td height="15">The id is returned when an object is created using an add
 * method.</td>
 * <td height="15">t, x, y, vx, vy, ax, ay</td>
 * </tr>
 * <tr>
 * <td height="15">clock</td>
 * <td height="15">id=getClockID()</td>
 * <td height="15">t</td>
 * </tr>
 * </table>
 * </center>
 * </div>
 * <p>Methods to access the animation clock can be found in the superclass
 * documentation, SApplet.</p>
 *
 * @author             Wolfgang Christian
 * @version            3.14c 1999/07/21
 */

public class EField extends SApplet{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String button_start="Play";
  private String button_stop="Pause";
  private String button_test="Test";
  private String button_clear="Clear";
  private String button_reset="Reset";
  private String button_reverse="Reverse";
  private String button_forward="Step>>";
  private String button_back="<<Step";
  private String button_potential="U ( x, y )";
  String label_collision = "collision";
  private String label_charge="Charge";
  private String label_labels="Labels";
  private String label_vectors="Field Vec.";
  private String label_contours="Contours";
  String label_time="Time: ";
  String label_volt="V=";
  String label_volt_undefined="V=Undefined";
  String label_field="|E|=";
  String label_field_undefined="E=Undefined";
  String label_force="|F|=";
  String label_force_undefined="F=Undefined";
  String label_calculating="Calculating. Please wait.";
  boolean    showControls;
  boolean    showContours, showFieldLines, showLabels, showPoles, showFieldVectors;
  boolean    showForce=false, showVelocity = false;
  double     pixPerUnit;
  double     dt;
  double     gridUnit;
  String     potStr;
  String     rangeStr;
  int        gridSize;
  boolean    noDrag=true;
  boolean    hideCharge=false;
  boolean    pointChargeMode=true;
  OdeCanvas   odeCanvas = new OdeCanvas(this);
  edu.davidson.graphics.EtchedBorder bevelPanel1 = new edu.davidson.graphics.EtchedBorder();
  Button     playBtn = new Button();
  Button     pauseBtn = new Button();
  Button     reverseBtn = new Button();
  Button     stepBackBtn = new Button();
  Button     stepForwardBtn = new Button();
  Button     resetBtn = new Button();
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  edu.davidson.graphics.EtchedBorder bevelPanel2 = new edu.davidson.graphics.EtchedBorder();
  edu.davidson.graphics.EtchedBorder bevelPanel4 = new edu.davidson.graphics.EtchedBorder();
  Button newBtn = new Button();
  Button clearBtn = new Button();
  FlowLayout flowLayout3 = new FlowLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  edu.davidson.graphics.EtchedBorder bevelPanel5 = new edu.davidson.graphics.EtchedBorder();
  TextField funcField = new TextField();
  BorderLayout borderLayout3 = new BorderLayout();
  Button potBtn = new Button();
  Button negBtn = new Button();
  Button posBtn = new Button();
  edu.davidson.graphics.EtchedBorder bevelPanel3 = new edu.davidson.graphics.EtchedBorder();
  Checkbox chargeBox = new Checkbox();
  Checkbox labelBox = new Checkbox();
  FlowLayout flowLayout2 = new FlowLayout();
  Checkbox fieldVectorsBox = new Checkbox();
  Checkbox contoursBox = new Checkbox();
  Color chargeColor=null;
  String chargeLabel=null;
  int    chargeTrail=0;

  /**
   * @y.exclude
   */
  public EField() {
  }

  protected void setResources(){
    button_start=localProperties.getProperty("button.start",button_start);
    button_stop=localProperties.getProperty("button.stop",button_stop);
    button_reverse=localProperties.getProperty("button.reverse",button_reverse);
    button_reset=localProperties.getProperty("button.reset",button_reset);
    button_forward=localProperties.getProperty("button.forward",button_forward);
    button_back=localProperties.getProperty("button.back",button_back);
    button_test=localProperties.getProperty("button.test",button_test);
    button_clear=localProperties.getProperty("button.clear",button_clear);
    button_potential=localProperties.getProperty("button.potential",button_potential);
    label_collision = localProperties.getProperty("label.collision", label_collision);
    label_charge=localProperties.getProperty("label.charge",label_charge);
    label_labels=localProperties.getProperty("label.labels",label_labels);
    label_vectors=localProperties.getProperty("label.vectors",label_vectors);
    label_contours=localProperties.getProperty("label.contours",label_contours);
    label_time=localProperties.getProperty("label.time",label_time);
    label_volt=localProperties.getProperty("label.volt",label_volt);
    label_volt_undefined=localProperties.getProperty("label.volt_undefined",label_volt_undefined);
    label_field=localProperties.getProperty("label.field",label_field);
    label_field_undefined=localProperties.getProperty("label.field_undefined",label_field_undefined);
    label_force=localProperties.getProperty("label.force",label_force);
    label_force_undefined=localProperties.getProperty("label.force_undefined",label_force_undefined);
    label_calculating=localProperties.getProperty("label.calculating",label_calculating);
  }

  /**
   * @y.exclude
   */
  public void init() {
    double fps=10;
    initResources(null);
    try { fps = Double.valueOf(this.getParameter("FPS", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }

    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showContours = Boolean.valueOf(this.getParameter("ShowContours", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showPoles = Boolean.valueOf(this.getParameter("ShowCharge", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showLabels = Boolean.valueOf(this.getParameter("ShowLabels", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showFieldLines = Boolean.valueOf(this.getParameter("ShowFieldLines", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showFieldVectors = Boolean.valueOf(this.getParameter("ShowFieldVectors", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { pointChargeMode = Boolean.valueOf(this.getParameter("PointChargeMode", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }

    try { pixPerUnit = Double.valueOf(this.getParameter("PixPerUnit", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { gridUnit = Double.valueOf(this.getParameter("GridUnit", "1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { potStr = this.getParameter("Potential", "0"); } catch (Exception e) { e.printStackTrace(); }
    try { rangeStr = this.getParameter("Range", "-1,1,-1,1"); } catch (Exception e) { e.printStackTrace(); }
    try { gridSize = Integer.parseInt(this.getParameter("GridSize", "64")); } catch (Exception e) { e.printStackTrace(); }
    try { jbInit(); } catch (Exception e) { e.printStackTrace(); }
    odeCanvas.setGridSize(gridSize);
    odeCanvas.setShowContours(showContours);
    contoursBox.setState(showContours);

    odeCanvas.pointChargeMode=pointChargeMode;
    odeCanvas.setShowFieldLines(showFieldLines);

    odeCanvas.setShowFieldVectors(showFieldVectors);
    fieldVectorsBox.setState(showFieldVectors);

    odeCanvas.setShowPoles(showPoles);
    chargeBox.setState(showPoles);

    odeCanvas.setShowLabels(showLabels);
    labelBox.setState(showLabels);

    odeCanvas.setRange(rangeStr);
    odeCanvas.setPotStr(potStr);
    funcField.setText(potStr);
    setRunningID(this);

    clock.addClockListener(odeCanvas);
    clock.setDt(dt);
    clock.setFPS((int)fps);
    if(showControls){
        odeCanvas.setShowVOnDrag(true);
        odeCanvas.setShowEOnDrag(true);
        noDrag=false;
    }
  }

  /**
   * @y.exclude
   * @throws java.lang.Exception
   */
  public void jbInit() throws Exception{
    bevelPanel1.setLayout(flowLayout1);
    /** j2sNative */{
    	  this.setSize(new Dimension(488, 491));
    }
    playBtn.setLabel(button_start);
    pauseBtn.setLabel(button_stop);
    pauseBtn.addActionListener(new EField_pauseBtn_actionAdapter(this));
    reverseBtn.setLabel(button_reverse);
    reverseBtn.addActionListener(new EField_reverseBtn_actionAdapter(this));
    stepBackBtn.setLabel(button_back);
    stepBackBtn.addActionListener(new EField_stepBackBtn_actionAdapter(this));
    stepForwardBtn.setLabel(button_forward);
    stepForwardBtn.addActionListener(new EField_stepForwardBtn_actionAdapter(this));
    resetBtn.setLabel(button_reset);
   // bevelPanel4.setBevelOuter(edu.davidson.graphics.EtchedBorder.RAISED);
    bevelPanel2.setLayout(borderLayout2);
    //bevelPanel2.setBackground(Color.lightGray);
    bevelPanel4.setLayout(flowLayout3);
    //bevelPanel4.setBackground(Color.lightGray);
    newBtn.setLabel(button_test);
    newBtn.addActionListener(new EField_newBtn_actionAdapter(this));
    clearBtn.setLabel(button_clear);
    clearBtn.addActionListener(new EField_clearBtn_actionAdapter(this));
   // bevelPanel5.setBevelOuter(edu.davidson.graphics.EtchedBorder.RAISED);
   // bevelPanel5.setMargins(new Insets(5, 2, 5, 2));
    bevelPanel5.setBackground(Color.lightGray);
    borderLayout3.setHgap(5);
    potBtn.setFont(new Font("Dialog", 1, 12));
    potBtn.setLabel(button_potential);
    negBtn.setLabel("-");
    negBtn.addActionListener(new EField_negBtn_actionAdapter(this));
    posBtn.setLabel("+");
    bevelPanel3.setLayout(flowLayout2);
    chargeBox.setLabel(label_charge);
    chargeBox.addItemListener(new EField_chargeBox_itemAdapter(this));
    labelBox.setLabel(label_labels);
    labelBox.addItemListener(new EField_labelBox_itemAdapter(this));
    fieldVectorsBox.setLabel(label_vectors);
    fieldVectorsBox.addItemListener(new EField_fieldVectorsBox_itemAdapter(this));
    contoursBox.setLabel(label_contours);
    contoursBox.addItemListener(new EField_contoursBox_itemAdapter(this));
    posBtn.addActionListener(new EField_posBtn_actionAdapter(this));
    potBtn.addActionListener(new EField_potBtn_actionAdapter(this));
    bevelPanel5.setLayout(borderLayout3);
    resetBtn.addActionListener(new EField_resetBtn_actionAdapter(this));
    playBtn.addActionListener(new EField_playBtn_actionAdapter(this));
    this.setLayout(borderLayout1);
    this.add(odeCanvas, BorderLayout.CENTER);
    if(showControls)
        this.add(bevelPanel1, BorderLayout.SOUTH);
    bevelPanel1.add(playBtn, null);
    bevelPanel1.add(pauseBtn, null);
    bevelPanel1.add(reverseBtn, null);
    bevelPanel1.add(stepBackBtn, null);
    bevelPanel1.add(stepForwardBtn, null);
    bevelPanel1.add(resetBtn, null);
    bevelPanel1.setBackground(playBtn.getBackground());
    if(showControls)
        this.add(bevelPanel2, BorderLayout.NORTH);
    bevelPanel2.add(bevelPanel4, BorderLayout.EAST);
    bevelPanel4.add(posBtn, null);
    bevelPanel4.add(negBtn, null);
    bevelPanel4.add(newBtn, null);
    bevelPanel4.add(clearBtn, null);
    bevelPanel4.setBackground(newBtn.getBackground());

    bevelPanel2.add(bevelPanel5, BorderLayout.CENTER);
    bevelPanel5.add(funcField, BorderLayout.CENTER);
    bevelPanel5.add(potBtn, BorderLayout.WEST);
    bevelPanel5.setBackground(potBtn.getBackground());

    bevelPanel2.add(bevelPanel3, BorderLayout.SOUTH);
    bevelPanel3.add(contoursBox, null);
    bevelPanel3.add(fieldVectorsBox, null);
    bevelPanel3.add(chargeBox, null);
    bevelPanel3.add(labelBox, null);
    //bevelPanel3.setBackground(Color.lightGray);
    bevelPanel3.setBackground(chargeBox.getBackground());
  }
/**
   * Called when the clock stops in one-shot mode.    DO NOT SCRIPT.
   */
  protected void stoppingClock(){odeCanvas.setMessage(oneShotMsg);}

/**
    * Called when the clock cycles in cycle mode.    DO NOT SCRIPT.
    */
   protected void cyclingClock(){
       clock.setTime(0);
       odeCanvas.resetTime();
       clearAllData();
   }

  /**
   * Counts the number of applets on a page.
   *
   * @param func
   * @param vars
   *
   * @return the applet count
   * @y.exclude
   */
  public int getAppletCount() {
    if(firstTime) return 0;
    else return super.getAppletCount();
  }

  /**
   * @y.exclude
   */
  public void start(){
      if(firstTime){
           firstTime=false;
      }
      super.start();

      // debug scripts from here

      //setAutoRefresh(true);
      //odeCanvas.osiInvalid=true;
      //repaint();

      //int id=addObject("box","x=0");
      //setDragable(id,true);

      /*
     int id=addObject("testcharge","m=1");
     setDragable(id,true);
     this.setPotential("x*0.2",-10,10,-10,10);
     this.setShowVOnDrag(true);
     */

     // int id=addObject("image","file=jet.gif");
     //setDragable(id,true);
  }

  /**
   * @y.exclude
   */
  public void stop(){
      //clock.stopClock();
      //odeCanvas.setMessage(null);
      super.stop();
  }

  /**
   * @y.exclude
   */
  public void destroy() {
     destroyed=true;
     setAutoRefresh(false);
     clock.stopClock();
     odeCanvas.destroy();
     super.destroy();
  }

  /**
   * @y.exclude
   * @return the info
   */
  final public String getAppletInfo() {
    return "EField written by W. Christian.  Email:wochristian@davidson.edu";
  }



  /**
   * Gets the Canvas so that EField can be used in EJS for drawing.
   *
   * @return   AnimatorCanvas
   * @y.exclude
   */
    public OdeCanvas getPhysletCanvas(){ return this.odeCanvas;}

/**
 * Gets the series ID for the graph object. This ID is used to make a connection to
 * a SDataSource.
*/
    public int getGraphID(){return odeCanvas.contour.hashCode();}

    /**
     * Gets the id for collisions.  This id can be
     * used to access the collision as a data source.
     *
     * @return             int The id of the ensemble containing all
     *                     objects on the screen.  Used as a data source.
     */
    public int getCollisionID() {
      return odeCanvas.getCollisionThing().hashCode();
    }

    /**
     * Gets the series ID, that is the hashcode, from a series number.  Hash codes are unique
     * object identifiers that are defind by the Java VM.  Series numbers are 0,1,2,.......
     */
    public int getSeriesID(int sid) {
      return odeCanvas.contour.getIDFromSID(sid);
    }

/**
   * Sets the series style for the graoh object.
   *
   * @param              id The series id.
   * @param              conPts Connect the points?
   * @param              m Marker style. (m=1 is cross; m=2 is square, m=3 is
   *                     circle)
   */
  public synchronized void setSeriesStyle(int id, boolean conPts, int m ){
      odeCanvas.contour.setSeriesStyle( id,  conPts,  m ) ;
  }

  /**
     * Delete a series from the graph
     *
     * @param              Series ID
     */
    public void deleteSeries(int s){  odeCanvas.contour.deleteSeries(s);}

/**
     * Clear the data from a graph series.  Series properties such as color and style
     * remain unchanged.
     *
     * @param              Series ID
     */
    public void clearSeries(int s){  odeCanvas.contour.clearSeriesData(s);}

/**
   * Gets the x position of an object.
   *
   * @param              id The id of the object.
   */
  public double getX(int id){
      Thing t=odeCanvas.getThing(id);
      if(t==null) return 0;
      return t.getX();
  }

/**
   * Gets the y position of an object.
   *
   * @param              id The id of the object.
   */
  public double getY(int id){
      Thing t=odeCanvas.getThing(id);
      if(t==null) return 0;
      return t.getY();
  }

/**
   * Gets the x velocity of an object.
   *
   * @param              id The id of the object.
   */
  public double getVX(int id){
      Thing t=odeCanvas.getThing(id);
      if(t==null) return 0;
      return t.getVX();
  }

/**
   * Gets the y velocity of an object.
   *
   * @param              id The id of the object.
   */
  public double getVY(int id){
      Thing t=odeCanvas.getThing(id);
      if(t==null) return 0;
      return t.getVY();
  }

/**
   * Gets the y velocity of an object.
   *
   * @param              id The id of the object.
   */
  public double getTime(){return odeCanvas.time;}

  /**
   * @y.exclude
   * @return the info
   */
  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"FPS", "double", "Frames per Second"},
      {"ShowControls", "boolean", "Show controls at bottom of applet."},
      {"PixPerUnit", "double", "Pixels per unit scale factor."},
      {"dt", "double", "TimeStep"},
      {"GridUnit", "double", "Grid Unit"},
      {"Potential", "String", "Potential U(x,y)"},
      {"Range", "String", "xmin, xmax, ymin, ymax"},
      {"GridSize", "int", "Grid Size"},
    };
    return pinfo;
  }

  void reverseBtn_actionPerformed(ActionEvent e){reverse();}

  void forwardBtn_actionPerformed(ActionEvent e) {forward();}

  void stepBackBtn_actionPerformed(ActionEvent e) {stepBack();}

  void stepForwardBtn_actionPerformed(ActionEvent e) {stepForward();}


  /**
   * Create an object and add it to the Physlet.
   * The first argument is the name of the object to be added and the second is a
   * comma-delimited list of parameters.  For example, a circle can be added a follows:
   *<p>
   * <code>addObject ("circle", "x = 0, y = -1.0, r = 10");</code>
   * </p>
   * See the supplemental documentation for a list of
   * <a href="doc-files/efield_addobject.html">object names and parameters.</a>
   *
   * @param              name the type of object to be created.
   * @param              parList a list of parameters to be set
   * @return             id that identifies the object.
   */
  public synchronized int addObject(String name, String parList){
    if(destroyed) return 0;
     Thing t=null;
     double x=0;
     double y=0;
     int width=20;
     int height=20;
     int r=20;
     double mass=1; // mass
     if(parList==null) parList="";
     name=name.toLowerCase().trim();
     name=SUtil.removeWhitespace(name);
     String parList2=parList.trim();;
     parList=SUtil.removeWhitespace(parList);
     if(name.equals("box")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"w=")) width=(int)SUtil.getParam(parList,"w=");
         if( SUtil.parameterExist(parList,"h=")) height=(int)SUtil.getParam(parList,"h=");
         t=new Box(odeCanvas,x,y,width,height);
         if( SUtil.parameterExist(parList,"m=")) mass=SUtil.getParam(parList,"m=");
         if(mass!=1) t.mass=mass;
     } else if(name.equals("rectangle")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"w=")) width=(int)SUtil.getParam(parList,"w=");
         if( SUtil.parameterExist(parList,"h=")) height=(int)SUtil.getParam(parList,"h=");
         t=new DrawRectangle(odeCanvas,x,y,width,height);
         if( SUtil.parameterExist(parList,"m=")) mass=SUtil.getParam(parList,"m=");
         if(mass!=1) t.mass=mass;
     }else if(name.equals("circle")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new Circle(odeCanvas,x,y,r);
         if( SUtil.parameterExist(parList,"m=")) mass=SUtil.getParam(parList,"m=");
         if(mass!=1) t.mass=mass;
     }else if(name.equals("cursor")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new CursorThing(odeCanvas,2*r+1,x,y);
     }else if(name.equals("shell")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new Shell(odeCanvas,x,y,r);
         if( SUtil.parameterExist(parList,"m=")) mass=SUtil.getParam(parList,"m=");
         if(mass!=1) t.mass=mass;
     }else if(name.equals("arrow")){
         String horz="1", vert="1";
         int s=4;  // the size of the arrowhead.
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"h=")) horz=SUtil.getParamStr(parList,"h=");
         if( SUtil.parameterExist(parList,"v=")) vert=SUtil.getParamStr(parList,"v=");
         if( SUtil.parameterExist(parList,"s=")) s=(int)SUtil.getParam(parList,"s=");
         t=new Arrow(odeCanvas,s,horz,vert, x,y);
     }else if(name.equals("line")){
         String horz="1", vert="1";
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"h=")) horz=SUtil.getParamStr(parList,"h=");
         if( SUtil.parameterExist(parList,"v=")) vert=SUtil.getParamStr(parList,"v=");
         t=new Arrow(odeCanvas,0,horz,vert, x,y);    // special arrow without a head since s=0;
     }else if(name.equals("text")){
         String txt="";
         String calc=null;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"txt=")) txt=SUtil.getParamStr(parList2,"txt=");
         if( SUtil.parameterExist(parList,"text=")) txt=SUtil.getParamStr(parList2,"text=");
         if( SUtil.parameterExist(parList,"calc=")) calc=SUtil.getParamStr(parList,"calc=");
         t=new TextThing(odeCanvas,txt, calc, x,y);
     }else if(name.equals("caption")){
         String txt="";
         String calc=null;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"txt=")) txt=SUtil.getParamStr(parList2,"txt=");
         if( SUtil.parameterExist(parList,"text=")) txt=SUtil.getParamStr(parList2,"text=");
         if( SUtil.parameterExist(parList,"calc=")) calc=SUtil.getParamStr(parList,"calc=");
         t=new CaptionThing(odeCanvas,txt, calc, x,y);
          //CaptionThing(OdePanel o, String txt, String cs, double x, double y)
     }else if(name.equals("connectorline")){
         int id1=0,id2=0;
         if( SUtil.parameterExist(parList,"id1=")) id1=(int)SUtil.getParam(parList,"id1=");
         if( SUtil.parameterExist(parList,"id2=")) id2=(int)SUtil.getParam(parList,"id2=");
         Thing t1=odeCanvas.getThing(id1);
         Thing t2=odeCanvas.getThing(id2);
         t=new ConnectorLine(odeCanvas,t1,t2);
     }else if(name.equals("connectorspring")){
         int id1=0,id2=0;
         if( SUtil.parameterExist(parList,"id1=")) id1=(int)SUtil.getParam(parList,"id1=");
         if( SUtil.parameterExist(parList,"id2=")) id2=(int)SUtil.getParam(parList,"id2=");
         Thing t1=odeCanvas.getThing(id1);
         Thing t2=odeCanvas.getThing(id2);
         t=new ConnectorSpring(odeCanvas,t1,t2);
     }else if(name.equals("charge")){
         double q=1;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"q=")) q=SUtil.getParam(parList,"q=");
         r=9;
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=odeCanvas.addPole(x,y,q);
         t.setNoDrag(noDrag);
         t.setHideThing(hideCharge);
         t.setShowFVector(showForce);
         t.showFOnDrag=showForce;
         t.showFOnDrag=showForce;
         t.setSize(r);
         if( SUtil.parameterExist(parList,"m=")) mass=SUtil.getParam(parList,"m=");
         if(mass!=1) t.mass=mass;
         if(chargeLabel!=null)t.setLabel(chargeLabel);
         if(chargeColor!=null)t.setColor(chargeColor);
         return t.hashCode();
     }else if(name.equals("testcharge")){
         double q=1, vx=0, vy=0;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"vx=")) vx=SUtil.getParam(parList,"vx=");
         if( SUtil.parameterExist(parList,"vy=")) vy=SUtil.getParam(parList,"vy=");
         if( SUtil.parameterExist(parList,"q=")) q=SUtil.getParam(parList,"q=");
         r=5;
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=odeCanvas.addTestCharge(x,y,vx,vy);
         t.setNoDrag(noDrag);
         t.setShowV(showVelocity);
         t.setShowFVector(showForce);
         t.showFOnDrag=showForce;
         t.setLabel(chargeLabel);
         t.setTrailSize(chargeTrail);
         t.mag=q;
         t.setSize(r);
         if( SUtil.parameterExist(parList,"m=")) mass=SUtil.getParam(parList,"m=");
         if(mass!=1) t.mass=mass;
         if(chargeColor!=null)t.setColor(chargeColor);
         return t.hashCode();
     }else if(name.equals("image")){
         String file=" ";
         if( SUtil.parameterExist(parList,"gif=")) file=SUtil.getParamStr(parList,"gif=");
         if( SUtil.parameterExist(parList,"file=")) file=SUtil.getParamStr(parList,"file=");
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if(file==null) return 0;
         int id=addImage(file, x,y);
         if( SUtil.parameterExist(parList,"m=")) mass=SUtil.getParam(parList,"m=");
         if(mass!=1) setMass(id,mass);
         return id;
     }
     if(t==null){
         System.out.println("Object not created. name:" + name + "parameter list:" + parList);
         return 0;
     }
     odeCanvas.drawThings.addElement(t);
     if(odeCanvas.autoRefresh)odeCanvas.repaint();
     return t.hashCode();
  }

/**
   * Adds a test charge.  A test charge moves with F=E but does not effect the
   * potentials or fields.
   *
   * This method is an alternative to the addObject method.
   *
   * @param              x The x position.
   * @param              y The xy position.
   * @param              vx The x velocity.
   * @param              vy The y velocity.
   * @return             An ID that identifies the charge.
   */
  public synchronized int addTestCharge(double x, double y, double vx, double vy){
     Charge c=odeCanvas.addTestCharge(x,y,vx,vy);
     c.setNoDrag(noDrag);
     c.setShowV(showVelocity);
     c.setShowFVector(showForce);
     c.showFOnDrag=showForce;
     c.setLabel(chargeLabel);
     c.setTrailSize(chargeTrail);
     if(chargeColor!=null)c.setColor(chargeColor);
     return c.hashCode();
  }

 /**
   * Add a fixed charge.  A fixed charge does not move but does effect the
   * field and the potential at the test charges.
   *
   * This method is an alternative to the addObject method.
   *
   * @param              x The x position.
   * @param              y The y position.
   * @param              q The magnitude of the charge.
   * @return             An ID that identifies the charge.
   */
  public synchronized int addCharge(double x, double y, double q){
     Charge c=odeCanvas.addPole(x,y,q);
     c.setNoDrag(noDrag);
     c.setHideThing(hideCharge);
     c.setShowFVector(showForce);
     c.showFOnDrag=showForce;
     c.showFOnDrag=showForce;
     if(chargeLabel!=null)c.setLabel(chargeLabel);
     if(chargeColor!=null) c.setColor(chargeColor);
     return c.hashCode();
  }

/**
   * Add a filled rectangle. This method is an alternative to the addObject method.
   *
   * @param              x The x position.
   * @param              y The y position.
   * @param              width The width of the rectangle in pixels.
   * @param              height The height of the rectangle in pixels.
   * @return             An ID that identifies the rectangle.
   */
  public synchronized int addRectangle(double x, double y, int width, int height ){
     DrawRectangle r=new DrawRectangle(odeCanvas,x,y,width,height);
     r.setNoDrag(noDrag);
     odeCanvas.drawThings.addElement(r);
     return r.hashCode();
  }


  /**
   * Adds a box.  This method is an alternative to the addObject method.
   *
   * @param              x The x position.
   * @param              y The y position.
   * @param              width The width of the box in pixels.
   * @param              height The height of the box in pixels.
   * @return             An ID that identifies the box.
   */
  public synchronized int addBox(double x, double y, int width, int height ){
     Box b=new Box(odeCanvas,x,y,width,height);
     b.setNoDrag(noDrag);
     odeCanvas.drawThings.addElement(b);
     return b.hashCode();
  }

/**
   * Adds a line from a point(x,y) to an object on the screen.
   * This method is an alternative to the addObject method.
   *
   * @param              ID The ID of the object on the screen.
   * @param              x The x position.
   * @param              y The y position.
   * @return             An ID that identifies the anchor.
   */
  public synchronized int addLineAnchor(int id, double x, double y){
     Thing t=odeCanvas.getThing(id);
     LineAnchor a=new LineAnchor(odeCanvas,x,y,t);
     odeCanvas.drawThings.addElement(a);
     return a.hashCode();
  }

  /**
   * Adds an image to the animation.  Looks for image in the code base, the
   * document base, and absolute URL.
   *
   * This method is an alternative to the addObject method.
   *
   * @param              file Location of image relative to the document
   *                     containing the HTML page.
   * @param              xStr The x position of the image.
   * @param              yStr The y position of the image.
   */
  public int addImage(String file,double x,double y){
      int id=0;
      Image im=null;

	  try{
	          im=getImage(getDocumentBase(),file);
	          id=odeCanvas.addImage(im,x,y);
	    } catch(Exception e){
	        id=0;
	        //System.out.println("Failed to load image file from document base.");
	  }
      if(id==0) try{
              im=getImage(getCodeBase(),file);
              id=odeCanvas.addImage(im,x,y);
        } catch(Exception e){
            id=0;
            //System.out.println("Failed to load image file from code base.");
      }
      if(id==0)try{
          java.net.URL url= new java.net.URL(file);
          im =getImage(url);
          id=odeCanvas.addImage(im,x,y);
        } catch(Exception e){
            id=0;
            //System.out.println("Failed to load image file from absolute URL.");
        }
      //System.out.println("id="+id);
      if(id==0){
          System.out.println("Failed to load image file.");
      }
      return id;
	}

/**
   * Adds a spring from a point(x,y) to an object on the screen.
   *
   * This method is an alternative to the addObject method.
   *
   * @param              ID The ID of the object on the screen.
   * @param              x The x position.
   * @param              y The y position.
   * @return             An ID that identifies the anchor.
   */
  public synchronized int addSpringAnchor(int id, double x, double y){
     Thing t=odeCanvas.getThing(id);
     LineAnchor a=new SpringAnchor(odeCanvas,x,y,t);
     odeCanvas.drawThings.addElement(a);
     return a.hashCode();
  }

/**
   * Adds a filled circle.  This method is an alternative to the addObject method.
   *
   * @param              x The x position.
   * @param              y The y position.
   * @param              width The radius of the shell in pixels.
   * @return             An ID that identifies the circle.
   */
  public synchronized int addCircle(double x, double y, int radius ){
     Circle c=new Circle(odeCanvas,x,y,radius);
     c.setNoDrag(noDrag);
     odeCanvas.drawThings.addElement(c);
     return c.hashCode();
  }

/**
   * Adds a spherical shell.  This method is an alternative to the addObject method.
   *
   * @param              x The x position.
   * @param              y The y position.
   * @param              width The radius of the shell in pixels.
   * @return             An ID that identifies the box.
   */
  public synchronized int addShell(double x, double y, int radius ){
     Shell s=new Shell(odeCanvas,x,y,radius);
     s.setNoDrag(noDrag);
     odeCanvas.drawThings.addElement(s);
     return s.hashCode();
  }

/**
   * Adds a polygon to the animation. The polygon can have an arbitrary number
   * of points but the shape is fixed.  The position can be a function of time.
   *
   * This method is an alternative to the addObject method.
   *
   * @param              n The number of vertices in the polygon.
   * @param              hStr A comma separated list of the x postions of the
   *                     vertices in pixel units.  MUST BE A STRING.
   * @param              vStr A comma separated list of the y postions of the
   *                     vertices in pixel units.  MUST BE A STRING.
   * @param              x The x position of the base.
   * @param              y The y position of the base.
   * @return             The id of the shape.
   *
   * @see                #addRectangle
   */
  public int addPolyShape(int n, String hStr, String vStr, double x, double y){
          StringTokenizer htokens=new StringTokenizer(hStr,", ; / \\ ( { [ ) } ] \t \n \r");
          StringTokenizer vtokens=new StringTokenizer(vStr,", ; / \\ ( { [ ) } ] \t \n \r");
          if((htokens.countTokens()!=n)||(htokens.countTokens()!=n)) return 0;
          int h[]=new int[n];
          int v[]=new int[n];
          for(int i=0; i<n; i++)
          {
            //h[i]=Integer.decode(htokens.nextToken()).intValue();
            //v[i]=Integer.decode(vtokens.nextToken()).intValue();
            h[i]=Format.atoi(htokens.nextToken());
            v[i]=Format.atoi(vtokens.nextToken());
          }
          return odeCanvas.addPolyShape(n,h,v,x,y);
  }

  /**
   * Add a polygon to the animation. The polygon can have an arbitrary number
   * of points but the shape is fixed.  Use relative postions for the vertices.
   *
   * This method is an alternative to the addObject method.
   *
   * @param              n The number of vertices in the polygon.
   * @param              hStr A comma separated list of the x postions of the
   *                     relative vertices in pixel units.  MUST BE A STRING.
   * @param              vStr A comma separated list of the y postions of the
   *                     relative vertices in pixel units.  MUST BE A STRING.
   * @param              x The x position of the base.
   * @param              y The y position of the base.
   * @return             The id of the shape.
   *
   * @see                #addRectangle
   */
  public int addRelPolyShape(int n, String hStr, String vStr, double x, double y){
          StringTokenizer htokens=new StringTokenizer(hStr,", ; / \\ ( { [ ) } ] \t \n \r");
          StringTokenizer vtokens=new StringTokenizer(vStr,", ; / \\ ( { [ ) } ] \t \n \r");
          if((htokens.countTokens()!=n)||(htokens.countTokens()!=n)) return 0;
          int h[]=new int[n];
          int v[]=new int[n];
          for(int i=0; i<n; i++){
            h[i]=Format.atoi(htokens.nextToken());
            v[i]=Format.atoi(vtokens.nextToken());
          }
          for(int i=1; i<n; i++){
            h[i]=h[i-1]+h[i];
            v[i]=v[i-1]+v[i];
          }
          return odeCanvas.addPolyShape(n,h,v,x,y);
  }

   /**
   * Delete an object from the applet.
   *
   * @param id the object identifier
   *
   * @return true if successful
   */
  public synchronized void deleteObject(int id){odeCanvas.deleteObject( id);}


 /**
   * Deletes the test charges.
   */
  public synchronized void deleteTestCharges(){odeCanvas.clearTestCharges();}

/**
   * Deletes the fixed charges.
   */
  public synchronized void deleteCharges(){odeCanvas.clearPoles();}

 /**
   * Sets the color for an object.  Currently works for test charges and poles.
   *
   * @param              id The ID of the object.
   * @param              d The damping coeficient in the rate equation.
   *                     F_damp=-damping*velocity.
   * @return             True if successful.
   */
  public boolean setDamping(int id, double d){
        Thing t=odeCanvas.getThing(id);
	    if(t==null) return false;
        t.damping=d;
        return true;
  }

/**
   * Sets the default conditions.
   * Hide field lines.
   * Hide vector field.
   * Disable charge drag.
   * Do not show test charge velocity.
   * Do not show force on charge.
   */
  public void setDefault(){
      pause();
      deleteDataConnections(); // we are going to delete all the charges so we might as well kill the connections too.
      clock.setContinuous();
      clock.setTime(0);
      setShowFieldLines(false);
      setShowFieldVectors(false);
      setShowContours(false);
      setShowCharge(true);
      noDrag=false;
      showVelocity=false;
      showForce=false;
      chargeLabel=null;
      chargeColor=null;
      odeCanvas.setDefault();
  }

/**
   * Sets the scale using the horizontal axis. The leftmost pixel will be xmin.
   * The rightmost pixel will be xmax.  The vertical axis will be rescaled to
   * maintian an aspect ratio of one.
   *
   * @param              xmin Minimuum value on x axis.
   * @param              xmax Maximum value on x axis.
   */
  public void setXRange(double xmin, double xmax){
      odeCanvas.setXRange(xmin,xmax);
      boolean ar=odeCanvas.autoRefresh;
      odeCanvas.autoRefresh=true;
      odeCanvas.setFields();
      odeCanvas.autoRefresh=ar;
      odeCanvas.clearTrails();
  }

  public void setYRange(double ymin, double ymax){
      odeCanvas.setYRange(ymin,ymax);
      boolean ar=odeCanvas.autoRefresh;
      odeCanvas.autoRefresh=true;
      odeCanvas.setFields();
      odeCanvas.autoRefresh=ar;
      odeCanvas.clearTrails();
  }

  /**
 * Sets the color scale on the vector field.
 *
 * @param   colorScale
 */
  public void setZColorScale(double colorScale){
    odeCanvas.field.scale=colorScale;
  }
    /**
     * Bug-fix to get the x of an object on Netscape and Sun.
     *
     * @param              id The id of the object.
     * @param              x  new x value
     * @return             true if successful
     */
    public double getXPos(int id) {
       return getX(id);
    }

    /**
     * Bug-fix to get the y of an object on Netscape and Sun.
     *
     * @param              id The id of the object.
     * @param              x  new x value
     * @return             true if successful
     */
    public double getYPos(int id) {
       return getY(id);
    }

      /**
   * Sets the x position of an object.
   *
   * @param              id The ID of the object.
   * @param              x The x coordinate.
   * @param              y The y coordinate.
   */
  public boolean setX(int id, double x){
        Thing t=odeCanvas.getThing(id);
	    if(t==null) return false;
        t.setX(x);
        if(t instanceof Charge)((Charge)t).setAcceleration();
        t.updateMySlaves();
        if(!clock.isRunning())updateDataConnections();
        if(odeCanvas.autoRefresh)odeCanvas.repaint();
        return true;
  }
  public boolean setXPos(int id, double x){
    return setX( id,  x);
  }

  /**
   * Sets the y position of an object.
   *
   * @param              id The ID of the object.
   * @param              x The x coordinate.
   * @param              y The y coordinate.
   */
  public boolean setY(int id, double y){
        Thing t=odeCanvas.getThing(id);
	    if(t==null) return false;
        t.setY(y);
        if(t instanceof Charge)((Charge)t).setAcceleration();
        t.updateMySlaves();
        if(!clock.isRunning())updateDataConnections();
        if(odeCanvas.autoRefresh)odeCanvas.repaint();
        return true;
  }

  public boolean setYPos(int id, double y){
     return setY( id,  y);
  }

  /**
   * Sets the position of an object.
   *
   * @param              id The ID of the object.
   * @param              x The x coordinate.
   * @param              y The y coordinate.
   */
  public boolean setXY(int id, double x, double y){
        Thing t=odeCanvas.getThing(id);
	    if(t==null) return false;
        t.setXY(x,y);
        if(t instanceof Charge)((Charge)t).setAcceleration();
        t.updateMySlaves();
        if(!clock.isRunning())updateDataConnections();
        if(odeCanvas.autoRefresh)odeCanvas.repaint();
        return true;
  }

/**
   * Sets drag for all subsequently created charges.
   *
   * @param              drag Drag?
   */
  public void setDrag(boolean drag){noDrag=!drag;}

/**
   * Sets the velocity of an object to zero if it is being dragged.
   *
   * @param              damp the velocity?
   */
  public void setDampOnMousePressed(boolean damp){odeCanvas.dampOnMousePressed=damp;}

/**
   * Make the object with the given id dragable.
   *
   * @param              id The id of the object.
   * @param              drag Dragable?
   * @return             True if successful.
   */
  public boolean setDragable(int id, boolean drag){
      Thing t=odeCanvas.getThing(id);
      if(t==null) return false;
      t.noDrag=!drag;
      return true;
  }

  /**
   * Show the visibility of the object.
   *
   * @param     st show object on screen?
   *
   * @return   true if successful
   */
  public boolean  setVisibility(int id, boolean show){
      if(id==getClockID()){
          odeCanvas.setShowTime(show);
          if(odeCanvas.autoRefresh)odeCanvas.repaint();
          return true;
      }
      Thing t=odeCanvas.getThing(id);
      if(t==null) return false;
      t.hideThing=!show;
      return true;
  }

/**
   * change the object's font for any text that is displayed.
   *
   * @param              id The id of the object.
   * @param              family The font family: Helvetica, Times.
   * @param              style The style, 0=plain, 1=bold.
   * @param              size The size of the font;
   * @return             True if successful.
   */
  public boolean setFont(int id, String family, int style, int size){
      Font font=new Font(family,style,size);
      //if(id==this.animatorCanvas.hashCode()){animatorCanvas.font=font; return true;}
      Thing t=odeCanvas.getThing(id);
      if(t==null || font==null) return false;
      t.font=font;
      if(odeCanvas.autoRefresh)odeCanvas.repaint();
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
    public boolean setObjectFont(int id, String family, int style, int size){
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
  public boolean setFormat(int id, String fstr){
      Thing t=odeCanvas.getThing(id);
      //if(t==null) return false;
      if(t==null && (id==0 || id==odeCanvas.hashCode())){
        return odeCanvas.setFormat(fstr);
      }
      boolean result=t.setFormat(fstr);
      if(autoRefresh)odeCanvas.repaint();
      return result;
  }


/**
   * Sets the trail to leave footprints as the charge moves.
   *
   * @param              id The id of the object.
   * @param              n The number of points to skip between trail
   *                     footprints or ghost images.
   * @return             True if successful.
   */
  public boolean setFootPrints(int id, int n){
      Thing t=odeCanvas.getThing(id);
      if(t==null) return false;
      t.footPrints=n;
      return true;
  }


/**
   * Hide all charges.
   *
   * @param              hc HidCharge parameter.
   */
  public void setHideCharge(boolean hc){if(hc) hideCharge=false; else hideCharge=true;}


/**
   * Make the object label.
   *
   * @param              id The id of the object.
   * @param              label The label string.
   * @return             True if successful.
   */
  public boolean setLabel(int id, String label){
      Thing t=odeCanvas.getThing(id);
      if(t==null)return false;
      t.label=label;
      if(odeCanvas.autoRefresh)odeCanvas.repaint();
      return true;
  }

  /**
 *       Display a message in the yellow message box.
 *
 *       @param              msg Message to display after the animation stops.
 */
public void setCollisionMessage (String msg) {
    label_collision = msg;
}



/**
   * Show the field lines.
   *
   * @param              sfl Show field line.
   */
  public void setShowFieldLines(boolean sfl){
          showFieldLines=sfl;
          odeCanvas.setShowFieldLines(sfl);
  }

/**
   * Show the direction field vectors.
   *
   * @param              sfv Show field line vectors.
   */
  public void setShowFieldVectors(boolean sfv){
          fieldVectorsBox.setState(sfv);
          showFieldVectors=sfv;
          odeCanvas.setShowFieldVectors(sfv);
  }

/**
   * Show the contour lines.
   *
   * @param              sc Show contours.
   */
  public void setShowContours(boolean sc){
          contoursBox.setState(sc);
          odeCanvas.setShowContours(sc);
          showContours=sc;
  }

/**
   * Sets point charge to produce 1/(r*r) field.  Setting this parameter false
   * will produce 1/r field.
   *
   * @param              pcm Sets point charge mode
   */
  public void setPointChargeMode(boolean pcm){
      odeCanvas.pointChargeMode=pcm;
      if(odeCanvas.autoRefresh)odeCanvas.repaint();
  }

/**
   * Show the contour labels.
   *
   * @param              sl Show contour labels?
   */
  public void setShowLabels(boolean sl){
          labelBox.setState(sl);
          odeCanvas.setShowLabels(sl);
          showLabels=sl;
  }

/**
   * Repaint whenevers the system parameters are changed.
   *
   * @param              autoRefresh Automatic repaint?
   */
  public void setAutoRefresh(boolean ar){
      autoRefresh=ar;
      odeCanvas.setAutoRefresh(ar);
  }

  /**
	 * Force an object to follow another object on the screen.
	 *
	 * @param              masterID The id of the master object.
	 * @param              slaveID The id of the slave object.
	 * @return             true if successful.
	 */
	public boolean setAnimationSlave(int masterID, int slaveID){
        Thing master=odeCanvas.getThing(masterID);
        Thing slave=odeCanvas.getThing(slaveID);
        if(master==null || slave==null) return false;
        if(master.myMaster==slave) master.myMaster=null;  // prevent slave=master and master=slave.
        master.mySlaves.addElement(slave);
        slave.myMaster=master;
        slave.setVarsFromMaster();
        if(odeCanvas.autoRefresh)odeCanvas.repaint();
        return true;
    }

/**
   * Change the properies of an object.
   * The first argument is the object identifier.
   * The second argument is the name of the property and the third is a
   * comma-delimited list of parameters.  For example, the scale can be added a follows:
   *<p>
   * <code>set(id, "sale", "xmin=0, xmax=2, autoscalx=false");</code>
   * </p>
   *
   * @param              id the identifier of the object
   * @param              name the type of property to be created.
   * @param              parList a list of parameters
   * @return             true if successful
   */
  public synchronized boolean set(int id, String name, String parList){
     if(parList==null) parList="";
     name=name.toLowerCase().trim();
     name=SUtil.removeWhitespace(name);
     //String parList2=parList.trim();;
     parList=SUtil.removeWhitespace(parList);
     //String str="false";
     double xmin=odeCanvas.contour.getMinX();
     double xmax=odeCanvas.contour.getMaxX();
     double ymin=odeCanvas.contour.getMinY();
     double ymax=odeCanvas.contour.getMaxY();
     if(name.equals("potential")){
         String ps="0";
         if(SUtil.parameterExist(parList,"u="))ps=SUtil.getParamStr(parList,"u=");
         if(SUtil.parameterExist(parList,"v="))ps=SUtil.getParamStr(parList,"v=");
         if(SUtil.parameterExist(parList,"potential="))ps=SUtil.getParamStr(parList,"potential=");
         setPotential( ps,  xmin,  xmax,  ymin,  ymax);
         return true;
     }else if(name.equals("scale")){
         if( SUtil.parameterExist(parList,"xmin="))  xmin=SUtil.getParam(parList,"xmin=");
         if( SUtil.parameterExist(parList,"xmax="))  xmax=SUtil.getParam(parList,"xmax=");
         if(( SUtil.parameterExist(parList,"xmin=")) || ( SUtil.parameterExist(parList,"xmax=")) ){
             odeCanvas.setXRange(xmin,xmax);
         }
         if( SUtil.parameterExist(parList,"ymin="))  ymin=SUtil.getParam(parList,"ymin=");
         if( SUtil.parameterExist(parList,"ymax="))  ymax=SUtil.getParam(parList,"ymax=");
         if(( SUtil.parameterExist(parList,"ymin=")) || ( SUtil.parameterExist(parList,"ymax=")) ){
             odeCanvas.setYRange(ymin,ymax);
         }
         int zlevels=11;
         double zmin=-1,zmax=1;;
         double delta=0.2;
         if( SUtil.parameterExist(parList,"zmin="))  zmin=SUtil.getParam(parList,"zmin=");
         if( SUtil.parameterExist(parList,"zmax="))  zmax=SUtil.getParam(parList,"zmax=");
         if( SUtil.parameterExist(parList,"zlevels="))zlevels=(int)SUtil.getParam(parList,"zlevels=");
         if( SUtil.parameterExist(parList,"delta=")) delta=SUtil.getParam(parList,"delta=");
         if(( SUtil.parameterExist(parList,"zmin=")) || ( SUtil.parameterExist(parList,"zmax=")) ){
             if(zlevels>2)delta=(zmax-zmin)/(zlevels-1);
         }
         if( SUtil.parameterExist(parList,"zmin=")){
             odeCanvas.contour.setLevels(zmin,delta,zlevels);
         }
         return true;
     }
     Thing t=odeCanvas.getThing(id);
     if(t==null){
         System.out.println("Object not created. name:" + name + "parameter list:" + parList);
         return false;
     }
     return true;
  }


/**
   * Sets the z component of the magnetic field.
   *
   * @param              bz The magnetic field in the z directon.
   */
  public void setBz(double bz){odeCanvas.setBz(bz);}


/**
   * Sets the caption.
   *
   * @param              c The caption string.
   */
  public void setCaption(String c){odeCanvas.setCaption(c);}

/**
   * Sets the charge label. See also setLabel to change the label of any object.
   *
   * @param              l All subsequent charges will show this label in the
   *                     middle of the charge.
   */
  public void setChargeLabel(String l){
      if(l==null || l.trim().equals("") ) chargeLabel= null ;
      else chargeLabel=l;
  }

/**
   * Sets the magnitude for a charge.
   *
   * @param              id The ID of the object.
   * @param              mag The magnitude.
   */
  public boolean setChargeMagnitude(int id, double mag){
        Thing t=odeCanvas.getThing(id);
	      if(t==null) return false;
        t.mag=mag;
        odeCanvas.setFields();
        if(odeCanvas.autoRefresh)odeCanvas.repaint();
        return true;
  }

/**
   * Sets the color for new charges.
   *
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   */
  public void setChargeRGB(int r, int g, int b){
	    chargeColor= new Color(r,g,b);
	}

  /**
	 * Sets the mass of an object.
	 *
	 * @param      id The id of the object.
	 * @param      m  The new mass.
	 * @return     True if successful.
	 */
	public boolean setMass(int id, double m){
        Thing t=odeCanvas.getThing(id);
	      if(t==null) return false;
        t.mass=m;
        return true;
    }

/**
   * Constrain the motion of the test charges to a path given my F(x).
   *
   * @param              str The function string F(x).
   * @param              xmin The minimum value of the range.
   * @param              xmax The maximum value of the range.
   * @return             True if successful.
   */
  public boolean setConstraintStr(int id, String str, double xmin, double xmax){
      Thing t=odeCanvas.getThing(id);
      return t.setConstraintStr(str,xmin,xmax);
  }

/**
   * Constrain the motion of the test charges to a path of constant x.
   *
   * @param              x The x value.
   * @param              xmin The minimum value of the range.
   * @param              xmax The maximum value of the range.
   * @return             True if successful.
   */
  public boolean setConstrainX(int id, double x, double xmin, double xmax){
      Thing t=odeCanvas.getThing(id);
      return t.setConstrainX(x,xmin,xmax);
  }

  /**
   * Constrain the motion of the test charges to a circular path.
   *
   * @param              r The r value.
   * @param              x The x coordinate of the center
   * @param              y The y coordinate of the center
   * @return             True if successful.
   */
  public boolean setConstrainR(int id, double r, double x, double y){
      Thing t=odeCanvas.getThing(id);
      return t.setConstrainR(r,x,y);
  }

/**
   * Constrain the motion of the test charges to a path of constant y.
   *
   * @param              y The y value.
   * @param              ymin The minimum value of the range.
   * @param              ymax The maximum value of the range.
   * @return             True if successful.
   */
  public boolean setConstrainY(int id, double y, double ymin, double ymax){
      Thing t=odeCanvas.getThing(id);
      return t.setConstrainY(y,ymin,ymax);
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
  public boolean setDisplayOffset(int id, int xOff, int yOff){
      Thing t=odeCanvas.getThing(id);
      if(t==null) return false;
      t.xDisplayOff=xOff;
      t.yDisplayOff=yOff;
      if(odeCanvas.autoRefresh)odeCanvas.repaint();
      return true;
  }


/**
   * Sets the animation the default trail length.
   *
   * @param              t The default trail points to draw in the animation
   *                     behind a moving charge.
   */
  public void setChargeTrail(int t){
	    chargeTrail= t;
  }
/**
   * Sets the color for an object.  Currently works for test charges and poles.
   *
   * @param              id The ID of the object.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   * @return             Tue if successful.
   */
  public boolean setRGB(int id, int r, int g, int b){
        Thing t=odeCanvas.getThing(id);
	    if(t==null){
           odeCanvas.contour.setObjectColor(id,new Color(r,g,b));
           return false;
      }
      t.setColor(new Color(r,g,b));
        if(odeCanvas.autoRefresh)odeCanvas.repaint();
        return true;
  }

  /**
   * Sets the series line and marker color.
   *
   * @param              id The series id.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   */
  public synchronized void setSeriesRGB(int id, int r, int g, int b){
      odeCanvas.contour.setSeriesColor(id, new Color(r,g,b));
  }

/**
   * Sets the size for an object.
   *
   * @param              id The ID of the object.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   * @return             True if successful.
   */
  public boolean setOnScreenSize(int id, int size){
        Thing t=odeCanvas.getThing(id);
	      if(t==null) return false;
        t.setSize(size);
        if(odeCanvas.autoRefresh)odeCanvas.repaint();
        return true;
  }

/**
   * Make a data connection between a data source, i.e. a charge, and a data
   * listener.
   * Typical data listeners are graphs and spread sheets.
   *
   * @param              sourceID The source ID.
   * @param              listenerID The listener ID.
   * @param              seriesID The listener ID series.
   * @param              xStr The ordinate to be passed to the data listener.
   * @param              yStr The abscissa to be passed to the data listener.
   * @return             An ID that identifies the data
   *                     connection.
   */
  public int makeDataConnection(int sourceID, int listenerID, int seriesID, String xStr, String yStr){
      return super.makeDataConnection(sourceID,listenerID,seriesID,xStr,yStr);
  }

/**
   * Break the data connection identified by the id.
   *
   * @param              id The data connection id.
   */
  public void deleteDataConnection(int id){ super.deleteDataConnection(id);}

/**
   * Break all data connections.
   */
  public void deleteDataConnections(){ super.deleteDataConnections();}

/**
   * Show the simulation time.
   *
   * @param              st Show the time?
   */
  public void  setShowTime(boolean st){
      odeCanvas.setShowTime(st);
      if(odeCanvas.autoRefresh)odeCanvas.repaint();
  }


/**
   * Show the coordinates on a mouse drag.
   *
   * @param              sc Show the coordinates.
   */
  public void  setShowCoordOnDrag(boolean sc){
      odeCanvas.setShowCoordOnDrag(sc);
  }

/**
   * Show the potential at the mouse postion on a mouse drag.
   *
   * @param              sv Show the voltage?
   */
  public void  setShowVOnDrag(boolean sv){
      odeCanvas.setShowVOnDrag(sv);
  }

/**
   * Show the field at the mouse postion on a mouse drag.
   *
   * @param              se Show the field.
   */
  public void  setShowEOnDrag(boolean se){
      odeCanvas.setShowEOnDrag(se);
  }

/**
   * Draw equipotential starting at the mouse click.
   *
   * @param              sfl Enable field line on click.
   */
  public void  setShowEquipotentialOnClick(boolean sp){
      odeCanvas.setShowEquipotentialOnClick(sp);
  }
/**
   * Draw equipotential starting at the mouse double click.
   *
   * @param              sfl Enable field line on double click.
   */
  public void  setShowEquipotentialOnDoubleClick(boolean sp){
      odeCanvas.setShowEquipotentialOnDoubleClick(sp);
  }


/**
   * Draw a field line starting at the mouse click.
   *
   * @param              sfl Enable field line on click.
   */
  public void  setShowFieldLineOnClick(boolean sfl){
      odeCanvas.setShowFieldLineOnClick(sfl);
  }
/**
   * Draw a field line starting at the mouse double click.
   *
   * @param              sfl Enable field line on double click.
   */
  public void  setShowFieldLineOnDoubleClick(boolean sfl){
      odeCanvas.setShowFieldLineOnDoubleClick(sfl);
  }

/**
   * Show a the constraint function that test particles must follow.
   *
   * @param              sc Show the path?
   */
  public void setShowConstraintPath(int id, boolean sc){
        Thing t=odeCanvas.getThing(id);
        if(t==null)return;
        //if(t instanceof TestCharge) ((TestCharge)t).showConstraintPath=sc;
       t.showConstraintPath=sc;
  }

/**
   * Enable the charge force vector to show components when it is drawn.
   * Works for test charges and poles.
   *
   * @param              id The ID of the charge.
   * @param              sc Show the vector?
   * @return             True if successful.
   */
  public boolean setShowFComponents(int id, boolean sc){
	    return odeCanvas.setShowFComponents(id, sc);
  }

/**
   * Enable the charge to show the force magnitude in the message box.
   *
   * @param              id The ID of the charge.
   * @param              sfm Show the force magnitude?
   */
  public boolean setShowFOnDrag(int id, boolean sfm){
	    return odeCanvas.setShowFOnDrag(id, sfm);
  }


/**
   * Enable the charge to show the net force vector when it is drawn.  Works
   * for test charges and poles.
   *
   * @param              id The ID of the charge.
   * @param              sf Show the vector?
   * @return             True if successful.
   */
  public boolean setShowFVector(int id, boolean sf){
	    return odeCanvas.setShowFVector(id, sf);
  }

  /**
   * Sets the field resolution determines how the spacing of the field vectors on the grid.
   * A resoluton of 2 would skip every other grid point when calculating the field vectors.
   *
   * @param              r the resolution
   */
  public void setFieldResolution(int r){
      odeCanvas.setFieldResolution(r);
  }

  /**
   * Sets the grid size for the evaluation of the potential energy.
   *
   * @param              n the size of the grid
   */
  public void setGridSize(int n){
      odeCanvas.setGridSize(n);
  }

/**
   * Have the charge draw ghost images as it moves.  Use footprints to set the
   * spacing.
   *
   * @param              id The id of the object.
   * @param              ghost Draw ghost?
   * @return             True if successful.
   */
  public boolean setGhost(int id, boolean ghost){
      Thing t=odeCanvas.getThing(id);
      if(t==null)return false;
      t.ghost=ghost;
      return true;
  }


/**
   * Show the default to show the force vector on all subsequently created
   * test charges.
   *
   * @param              sf Show the force?
   */
  public void setShowForce(boolean sf){
      showForce=sf;
  }
/**
   * Show the default to show the velocity vector on newly created test
   * charges.
   *
   * @param              sv Show the velocity?
   */
  public void setShowVelocity(boolean sv){
      showVelocity=sv;
  }

/**
   * Enable the charge velocity vector to show components when it is drawn.
   * Works for test charges and poles.
   *
   * @param              id The ID of the charge.
   * @param              svc Show the vector components?
   * @return             True if successful.
   */
  public boolean setShowVComponents(int id, boolean svc){
	    return odeCanvas.setShowVComponents(id, svc);
  }

/**
   * Enable the charge to show the net velocity vector when it is drawn.
   * Works for test charges and poles.
   *
   * @param              id The ID of the charge.
   * @param              sv Show the velocity vector?
   */
  public boolean setShowVVector(int id, boolean sv){
	    return odeCanvas.setShowVVector(id, sv);
  }

  /**
   * Enable sketching with the mouse.
   *
   * @param              sketch true will sketch
   *
   * @return  int the id of the mouse data source
   */

  public int setSketchMode(boolean sketch){
     return odeCanvas.setSketchMode(sketch);
  }

/**
   * Make the object stickey so that collisions stop the animation.
   *
   * @param              id The id of the object.
   * @param              stick Sticky?
   * @return             True if successful.
   */
  public boolean setSticky(int id, boolean isSticky){
      Thing t=odeCanvas.getThing(id);
      if(t==null)return false;
      t.sticky=isSticky;
      return true;
  }


/**
   * Change the speed of a test charge.  Direction of motion remains unchanged.
   *
   * @param              id The ID of the charge.
   * @param              speed Show new speed.
   * @return             True if successful.
   */
  public boolean setSpeed(int id, double speed){
	    return odeCanvas.setSpeed(id, speed);
  }


/**
   * Display all fixed charges on the screen.
   *
   * @param              sc Show the charges on the screen.
   */
  public void setShowCharge(boolean sc){
      showPoles=sc;
      odeCanvas.setShowPoles(showPoles);
      chargeBox.setState(showPoles);
  }

/**
   * Sets the trail behind a charge.  Zero will remove the trail.
   *
   * @param              id The ID of the object.
   * @param              pts Trail length.
   * @return             True if successful.
   */
  public boolean setTrail(int id, int pts){
        Thing t=odeCanvas.getThing(id);
        if(t==null) return false;
        t.setTrailSize(pts);
        if(odeCanvas.autoRefresh)odeCanvas.repaint();
        return true;
  }

  /**
   * Enable the time display in the applet window.
   *
   * @param     boolean   Show the time?
   */
  public void setTimeVisibility(boolean visible){
      odeCanvas.setShowTime(visible);
      if(odeCanvas.autoRefresh)odeCanvas.repaint();
  }


/**
   * Let the simulation continue forever.
   */
  public void setTimeContinuous(){
      clock.setContinuous();
      odeCanvas.setMessage(null);
  }

 /**
   * Cycle the simulation
   *
   * @param              max Reset the simulation to t=0 when t>=max and
   *                     repeat the simulation.
   */
  public void setTimeCycle(double max){
      if(clock.getDt()<0)clock.setDt(-clock.getDt());  // make sure dt is positive
      clock.setCycle(0,max);
      reset();
	}

  /**
   * Run the simulaiton one time.
   *
   * @param     max Reset the simulation to t=0 when t>=max and stop the simulation.
   * @param     string the message to be displayed on the screen when the max time is reached.
   */
  public void setTimeOneShot(double max, String msg){
     this.setMaxTime(max,msg);
	}

/**
   * Sets the tolerance for the animation calculations. Default is 1 part in
   * 1.0e-5.
   */
  public void setTolerance(double t){
      odeCanvas.setTolerance(t);
  }

/**
   * Sets the trajectory of a pole.
   *
   * @param              id The ID of the pole.
   * @param              xStr The function x(t).
   * @param              yStr The function y(t).
   * @return             True if the functions are valid and the
   *                     trajectory has been set.
   */
  public boolean setTrajectory(int id, String xStr, String yStr){
      return odeCanvas.setTrajectory(id, xStr, yStr);
  }

/**
   * Sets the potential function.   Y values are set so as to preserve an
   * aspect ration of 1.
   *
   * @param              ps The potential function, f(x,y).
   * @param              xmin x minimum
   * @param              xmax x maximum
   * @param              ymin approximate y minimum
   * @param              ymax approximate y maximum
   */
  public void setPotential(String ps, double xmin, double xmax, double ymin, double ymax){
      odeCanvas.setPotStr(ps,xmin,xmax,ymin,ymax);
      boolean ar=odeCanvas.autoRefresh;
      odeCanvas.autoRefresh=true;
      odeCanvas.setFields();
      odeCanvas.autoRefresh=ar;
      odeCanvas.clearTrails();
  }


/**
   * Stop the simulation at a predetermined time and display a message for the
   * user.
   *
   * @param              max Stop the simulation when t=max and continue
   *                     simulation.
   * @param              msg Display a user message.
   */
  public void setMaxTime(double max, String msg){
      if(clock.getDt()<0)clock.setDt(-clock.getDt());  // make sure dt is positive
      clock.setOneShot(0,max);
      oneShotMsg=msg;
      odeCanvas.setMessage(null);
  }

/**
 *    DO NOT SCRIPT!  Used by the ODE thread.
 *

  public void run(){
      keepRunning=!odeCanvas.testForCollision();
      odeCanvas.collision=!keepRunning;
      odeCanvas.setMessage(null);
      while (keepRunning && (dt!=0) && runOnStart && (getRunningID()==this) ){
          try{
              odeCanvas.stepTime(dt);
              runThread.sleep(sleepTime);
              if (cycle && (odeCanvas.getTime()>endTime)&& (dt>0) ){odeCanvas.resetTime();}
              if(oneShot && (odeCanvas.getTime()+dt/2.0>=endTime) ){
                  keepRunning=false;
                  odeCanvas.setMessage(oneShotMsg);
              }
              if(odeCanvas.collision) keepRunning=false;
          }catch (InterruptedException e){ stop();}
      }
      if(odeCanvas.collision) odeCanvas.setMessage("Collision");
      runThread=null;
  }
*/
/**
   * Pause the simulation
   */
  public void pause(){
      clock.stopClock();
  }

 /**
   * Resume the simulation with a positive time step.
   */
  public void forward(){
      if(odeCanvas.calculatingFieldLines) return;
      if(clock.getDt()<0)clock.setDt(-clock.getDt());  // make sure dt is positive
      boolean keepRunning=!odeCanvas.testForCollision();
      odeCanvas.collision=!keepRunning;
      odeCanvas.setMessage(null);
      if(keepRunning)clock.startClock();
  }
/**
   * Resume the simulation with a negative time step.
   */
  public void reverse(){
  /*
      if(clock.cycle ){
          odeCanvas.setMessage("Cannot cycle in reverse.");
          return;
      }
  */
      if(odeCanvas.calculatingFieldLines) return;
      if(clock.getDt()>0)clock.setDt(-clock.getDt());  // make sure dt is negative
      boolean keepRunning=!odeCanvas.testForCollision();
      odeCanvas.collision=!keepRunning;
      odeCanvas.setMessage(null);
      if(keepRunning)clock.startClock();
  }
/**
   * Step the simulation one positive time step.
   */
  public void stepTimeForward(){
    if(odeCanvas.testForCollision()) return;
    double dt=clock.getDt();
    if(dt<0)clock.setDt(-dt);  // make sure dt is positive
    odeCanvas.setMessage(null);
    clock.doStep();
	}
  public void stepForward(){stepTimeForward();}

  /**
	 * Step the simulation one negative time step.
  */
	public void stepTimeBack(){
    if(odeCanvas.testForCollision()) return;
    double dt=clock.getDt();
    if(dt>0)clock.setDt(-dt);  // make sure dt is negative
    odeCanvas.setMessage(null);
    if((clock.getTime()-Math.abs(dt))<=0) reset();
    else clock.doStep();
	}
  public void stepBack(){stepTimeBack();}

  void pauseBtn_actionPerformed(ActionEvent e){
      pause();
  }

 /**
 * Reset the initial conditions at time t=0.
  */
  public void reset(){
     pause();
     clock.setTime(0);
     odeCanvas.resetTime();
  }

  void resetBtn_actionPerformed(ActionEvent e) {
    reset();
  }

  void potBtn_actionPerformed(ActionEvent e) {
     String fStr=funcField.getText();
     if(odeCanvas.setPotStr(fStr)) funcField.setBackground(Color.white);
     else funcField.setBackground(Color.red);
  }

  void newBtn_actionPerformed(ActionEvent e) {
     odeCanvas.addTestCharge();
  }

  void clearBtn_actionPerformed(ActionEvent e) {
     odeCanvas.clearTestCharges();
     odeCanvas.clearPoles();
     odeCanvas.drawThings.removeAllElements();
     odeCanvas.contour.detachDataSets();
  }

  void posBtn_actionPerformed(ActionEvent e) {
        odeCanvas.addPole(+1);
  }

  void negBtn_actionPerformed(ActionEvent e) {
       odeCanvas.addPole(-1);
  }

  void fieldVectorsBox_itemStateChanged(ItemEvent e) {
      if(e.getStateChange()==ItemEvent.SELECTED){
          odeCanvas.setShowFieldVectors(true);
          showFieldVectors=true;
      }else{
          odeCanvas.setShowFieldVectors(false);
          showFieldVectors=false;
      }
  }

  void contoursBox_itemStateChanged(ItemEvent e) {
     if(e.getStateChange()==ItemEvent.SELECTED) odeCanvas.setShowContours(true);
        else odeCanvas.setShowContours(false);
  }

  void chargeBox_itemStateChanged(ItemEvent e) {
     if(e.getStateChange()==ItemEvent.SELECTED) odeCanvas.setShowPoles(true);
        else odeCanvas.setShowPoles(false);
  }

  void labelBox_itemStateChanged(ItemEvent e) {
     if(e.getStateChange()==ItemEvent.SELECTED) odeCanvas.setShowLabels(true);
        else odeCanvas.setShowLabels(false);
  }
}

class EField_playBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_playBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.forwardBtn_actionPerformed(e);
  }
}

class EField_stepBackBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_stepBackBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.stepBackBtn_actionPerformed(e);
  }
}

class EField_stepForwardBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_stepForwardBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.stepForwardBtn_actionPerformed(e);
  }
}

class EField_reverseBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_reverseBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.reverseBtn_actionPerformed(e);
  }
}

class EField_pauseBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_pauseBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.pauseBtn_actionPerformed(e);
  }
}

class EField_resetBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_resetBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.resetBtn_actionPerformed(e);
  }
}

class EField_potBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_potBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.potBtn_actionPerformed(e);
  }
}

class EField_newBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_newBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.newBtn_actionPerformed(e);
  }
}

class EField_clearBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_clearBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.clearBtn_actionPerformed(e);
  }
}

class EField_posBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_posBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.posBtn_actionPerformed(e);
  }
}

class EField_negBtn_actionAdapter implements java.awt.event.ActionListener{
  EField adaptee;

  EField_negBtn_actionAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.negBtn_actionPerformed(e);
  }
}

class EField_fieldVectorsBox_itemAdapter implements java.awt.event.ItemListener {
  EField adaptee;

  EField_fieldVectorsBox_itemAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.fieldVectorsBox_itemStateChanged(e);
  }
}


class EField_contoursBox_itemAdapter implements java.awt.event.ItemListener {
  EField adaptee;

  EField_contoursBox_itemAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.contoursBox_itemStateChanged(e);
  }
}

class EField_chargeBox_itemAdapter implements java.awt.event.ItemListener {
  EField adaptee;

  EField_chargeBox_itemAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.chargeBox_itemStateChanged(e);
  }
}

class EField_labelBox_itemAdapter implements java.awt.event.ItemListener {
  EField adaptee;

  EField_labelBox_itemAdapter(EField adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.labelBox_itemStateChanged(e);
  }
}



