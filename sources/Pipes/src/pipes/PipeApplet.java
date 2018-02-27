/*
**************************************************************************
**
**                      Class  PipeApplet
**
**************************************************************************
**
** class PipeApplet extends Applet
**
** The main entry point and PipeApplet applet.
**
*************************************************************************/
package pipes;

//import java.awt.*;
import a2s.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;

public class PipeApplet extends SApplet {

String button_step = "Step";
String button_start = "Run";
String button_stop = "Stop";
String button_reset = "Reset";

String label_time = "Time:";
String label_pressure_t = "P(t)=";
String label_pressure_xt = "P(x,t)=";
String label_analytic_mode = "Analytic Mode";
String label_source_mode = "Source Mode";
String label_drag_mode = "U-Drag Mode";
String label_amp = "Amp:";
String label_pos = "Pos:";
String label_wait = "Please wait . . .";



  //Pipe pipe = new Pipe();
  String pstring = "sin(3*t)";
//  String parsestring = "sin(3*t)";
  String pressureStr = "ahh!";
  String astring = "sin(0.5*x-3*t)";
  String mode = "s";
  boolean sc=true;
  double s; //speed
  int ppu;  //points per unit
  int fps;  //frames per second
  int wallW=10;
  double dt; //time step
  Pipe pipe = new Pipe(this);
  boolean sl=true;
  EtchedBorder controlPanel = new EtchedBorder();

  Pipescale pipescale = new Pipescale();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  CheckboxGroup checkboxGroup1 = new CheckboxGroup();
  Panel panel2 = new Panel();
  Label Plabel = new Label();
  TextField stringfield = new TextField();
  EtchedBorder etchedBorder4 = new EtchedBorder();
  Button stepbtn = new Button();
  Button startbtn = new Button();
  Panel panel3 = new Panel();
  Choice modeMenu = new Choice();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  //TransparentImage transparentImage1 = new TransparentImage();
  Button resetbtn = new Button();
  BorderLayout borderLayout5 = new BorderLayout();


  protected void setResources() {
    button_reset = localProperties.getProperty("button.reset", button_reset);
    button_start = localProperties.getProperty("button.start", button_start);
    button_stop = localProperties.getProperty("button.stop", button_stop);
    button_step = localProperties.getProperty("button.step", button_step);
    label_time = localProperties.getProperty("label.time", label_time);
    label_pressure_t = localProperties.getProperty("label.pressure.t", label_pressure_t);
    label_pressure_xt = localProperties.getProperty("label.pressure.xt", label_pressure_xt);
    label_analytic_mode = localProperties.getProperty("label.analytic.mode", label_analytic_mode);
    label_source_mode = localProperties.getProperty("label.source.mode", label_source_mode);
    label_drag_mode = localProperties.getProperty("label.drag.mode", label_drag_mode);
    label_amp = localProperties.getProperty("label.amp", label_amp);
    label_pos = localProperties.getProperty("label.pos", label_pos);
    label_wait= localProperties.getProperty("label.wait", label_wait);
  }



  public void init() {
    initResources(null);
    String parsestring="";
    try { s = Double.valueOf(this.getParameter("Speed", "50")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { mode = String.valueOf(this.getParameter("Mode", "s")); } catch (Exception e) { e.printStackTrace(); }
    try { ppu = Integer.parseInt(this.getParameter("Pixper", "10")); } catch (Exception e) { e.printStackTrace(); }
    try { fps = Integer.parseInt(this.getParameter("FPS", "30")); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("Dt", "0.04")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { sc = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { sl = Boolean.valueOf(this.getParameter("ShowLegend", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { parsestring = String.valueOf(this.getParameter("InitialString", pstring)); } catch (Exception e) { e.printStackTrace(); }
    try { wallW = Integer.parseInt(this.getParameter("WallW", "8")); } catch (Exception e) { e.printStackTrace(); }
    try {
    jbInit();
    }
    catch (Exception e) {
    e.printStackTrace();
    }
  mode.toLowerCase();                     //script in detector
  if(!(mode.equals("a")|| mode.equals("s")||mode.equals("u"))) mode="u";        // added by W. Christian
  parsestring.toLowerCase();
  setShowControls(sc);
  setSpeed(s);
  setPoints(ppu);
  setFPS(Math.max(fps,5));
  setDelt(dt);
  setWallW(wallW);
  setMode(mode,parsestring);
  pipe.change=true;
  clock.addClockListener(pipe);
  //setRunningID();
  }

  private void jbInit() throws Exception {
    controlPanel.setLayout(borderLayout4);
    this.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
        this_componentResized(e);
      }
    });
    /** @j2sNative */{
      this.setSize(new Dimension(406, 323));
    }
    controlPanel.setBackground(Color.lightGray);
    pipescale.setThickness(10);
    panel2.setLayout(borderLayout1);
    Plabel.setAlignment(2);
    Plabel.setText(label_pressure_xt);
    etchedBorder4.setLayout(flowLayout1);
    stepbtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stepbtn_actionPerformed(e);
      }
    });
    stepbtn.setLabel(button_step);
    startbtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        startbtn_actionPerformed(e);
      }
    });
    startbtn.setLabel(button_start);
    flowLayout1.setVgap(0);
    resetbtn.setLabel(button_reset);
    resetbtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        resetbtn_actionPerformed(e);
      }
    });
    flowLayout1.setHgap(0);
    panel3.setLayout(borderLayout2);
    modeMenu.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        modeMenu_itemStateChanged(e);
      }
    });
    stringfield.addTextListener(new java.awt.event.TextListener() {
      public void textValueChanged(TextEvent e) {
        stringfield_textValueChanged(e);
      }
    });
    pipe.setLayout(borderLayout3);
    this.setLayout(borderLayout5);
    this.add(pipe, BorderLayout.CENTER);
    //pipe.add(transparentImage1, BorderLayout.NORTH);
    this.add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(panel2, BorderLayout.CENTER);
    panel2.add(stringfield, BorderLayout.CENTER);
    panel2.add(Plabel, BorderLayout.WEST);
    controlPanel.add(etchedBorder4, BorderLayout.WEST);
    etchedBorder4.add(resetbtn, null);
    etchedBorder4.add(startbtn, null);
    etchedBorder4.add(stepbtn, null);
    controlPanel.add(panel3, BorderLayout.EAST);
    panel3.add(modeMenu, BorderLayout.EAST);
    pipescale.setLayout(borderLayout6);
    if (sl) this.add(pipescale, BorderLayout.NORTH);
    modeMenu.addItem(label_analytic_mode);
    modeMenu.addItem(label_source_mode);
    modeMenu.addItem(label_drag_mode);

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
      if (firstTime) {
          return 0;
      } else {
          return super.getAppletCount();
      }
}

  public void start() { // called whenever you enter the HTML page.
    super.start();
    if (firstTime) {
      firstTime=false;
      //go();
      pipe.makeImage();
      pipe.change=true;          //forces applet to setup.
      pipe.setup();
      if( mode.equals("u") )clock.startClock();
    }
  }

  /*
  public void stop() {
    super.stop();
    pause();
  }*/

  synchronized public void destroy() {
     pause();
     clock.stopClock();
     clock.removeClockListener(pipe);
     removeAllDetectors();
     super.destroy();
     firstTime=true;
  }

  private void go(){
    //setRunningID();
    if (sl) pipescale.drawScale();
    //setParse(parsestring);
    startbtn.setLabel(button_stop);
    //pipe.running=true;
    pipe.checkSize();
    clock.startClock();
  }

  public String getAppletInfo() {
    return "Applet Information";
  }
//Get parameter info

  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"speed", "double", "units/time"},
      {"pixper", "int", "pix per unit"},
      {"framesper", "int", "frames per sec."},
      {"timestep", "double", "delta t"},
    };
    return pinfo;
  }

  /**
  *
  *
  * Get the id of the pipe for use as a data source.
  *
  * @returns int The id of the pipe.
  */
  public int getPipeID(){   // added by W. Christian
     return pipe.hashCode();
  }

  void stepbtn_actionPerformed(ActionEvent e) {
     stepForward();
  }

  void resetbtn_actionPerformed(ActionEvent e) {
    reset();
    stepForward();
  }
  /**
  *
  * Advances wave by one step
  *
  */
  public void stepForward(){
      if(clock.isRunning()){
            pause();
            return;
      }
      clock.doStep();
  }

  /**
  *
  * Step back by dt.
  *
  */
  public void stepBack(){
     pipe.stepBack();
  }

  /**
  *
  * Start the animation
  *
  *
  */
  public void forward(){
      go();
  }

  /**
  *
  * Reset to time t=0;
  *
  *
  */
  public void reset(){
    pause();
    clock.setTime(0);
    //pipe.running=false;
    pipe.time=0;
    this.clearAllData();
    pipe.change=true;          //forces applet to setup.
    pipe.setup();
  }

  /**
  *
  * Stop the animation
  *
  *
  */
  public void pause(){
      //pipe.running=false;
      clock.stopClock();
      stoppingClock();
      startbtn.setLabel("Start");
  }

  void startbtn_actionPerformed(ActionEvent e) {
    //parsestring =  stringfield.getText();
    if (!clock.isRunning() )go();
      else pause();
  }

  /**
  *
  * Show/hide control panel
  *
  * @param b boolean
  *
  */
  public void setShowControls(boolean b) {
    controlPanel.setVisible(b);
    controlPanel.invalidate();
    controlPanel.validate();
  }

  void modeMenu_itemStateChanged(ItemEvent e) {
    if (mode.equals("s")) pstring=stringfield.getText();
    if (mode.equals("a")) astring=stringfield.getText();
    pause();
    startbtn.setLabel(button_start);
    String mstring = modeMenu.getSelectedItem().toString();  
    if (mstring.equals(label_analytic_mode)){
      setMode("a",astring);

      }
    else if (mstring.equals(label_drag_mode)){
      setMode("u",astring);

      }
    else {
      setMode("s",pstring);
     }
    reset();
    stepForward();

  }

  void stringfield_textValueChanged(TextEvent e) {
    pause();
    pipe.setParse(stringfield.getText());

  }

  void this_componentResized(ComponentEvent e) {
    pipe.change=true;
  }
  /**
  *
  * Sets parser string
  *
  *@param s string
  *
  */
  public void setParse(String s){
    pause();
    if(pipe.setParse(s)) stringfield.setBackground(Color.white);
        else  stringfield.setBackground(Color.red);
    stringfield.setText(s);
  }
  /**
  *
  * Sets wave speed
  *
  *@param s double
  *
  */
  public void setSpeed(double s){
    pipe.setSpeed(s);
  }

    /**
   * Show the simulation time.
   *
   * @param  show true will show
   */
  public void  setShowTime(boolean show){
      pipe.timeDisplay=show;
      pipe.repaint();
  }

/**
 *    Enable the time display in the applet window.
 *
 *    @param show true    will show the time
 *    @deprecated         replaced by setShowTime
*/
  public void setTimeDisplay(boolean show){
      pipe.timeDisplay=show;
      pipe.repaint();
  }

  /**
  *
  * Set pixels per unit
  *
  * @param pperunit  int
  * @deprecated         replaced by setPixPerUnit
  */
  public void setPoints(int pperunit){
  pipe.setPoints(pperunit);
  }

  /**
   * Set the pixels per unit.  This sets the scale for the pipe.
   *
   * @param              ppu pixels per unit.
   */
    public void setPixPerUnit(int ppu) {
      pipe.setPoints(ppu);
    }

    /**
     * Shift the origin for the entire drawing.  Shift is specified in pixels.
     *
     * @param              xo x pixel shift.
     * @param              yo y pixel shift.
     */
    public void shiftPixOrigin(int xo, int yo) {
        pipe.xshift = xo;
        pipe.yshift = yo;
    }

  /**
  *
  * Set frames per second
  *
  * @param fps int
  *
  */
  public void setFPS(int fps){
     clock.setFPS(fps);
  }


  /**
   * Set the color for an object.  Currently works for test charges and poles.
   *
   * @param              id The ID of the object.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   * @return             Tue if successful.
   */
  public void setRGB(int id, int r, int g, int b){
     if(id==0 || id==pipe.hashCode() ){
        pipe.setSpectrumRGB(r,g,b);
     }
  }

  /**
  *
  * Set the default conditions.
  *
  *
  */
  public void setDefault(){
    pause();
    deleteDataConnections();
    clock.setTime(0);
    this.removeAllDetectors();
    //pipe.running=false;
    pipe.time=0;
    pipe.setUPaintScale(true,4);
    pipe.setAPaintScale(true,1);
    pipe.setPPaintScale(true,2);
    pipe.setSpectrumRGB(255,255,255);
    pipe.xshift = 0;
    pipe.yshift = 0;
    pipe.timeDisplay=true;
    pipe.setup();
  }

  /**
  *
  * Set time step dt
  *
  * @param d  double
  *
  */
  public void setDelt(double dt){
    clock.setDt(dt);
  }

/**
 *    Make the object with the given id dragable.
 *
 *    @param id      The id of the object.
 *    @param drag       Dragable?
 *
 *    @returns boolean  True if successful.
*/
  public boolean setDragable(int id, boolean drag){
      Thing t=pipe.getThing(id);
      if(t==null) return false;
      t.isMoveable=drag;
      return true;
  }



  /**
  *
  * Set mode
  * @param m  String  mode "a" "s" or "u"
  * @param s  String  evaluated by parser
  *
  */
  public void setMode(String m, String s){
    boolean isRunning=clock.isRunning();
    clock.stopClock();
    mode=m;
    if(!(mode.equals("a")|| mode.equals("s")||mode.equals("u"))) mode="u";        // added by W. Christian
    //reset();
    pipe.setMode(mode);
    setParse(s);
    if(mode.equals("a")){
        Plabel.setText(label_pressure_xt);
        stringfield.setEnabled(true);
        modeMenu.select(label_analytic_mode);
        astring=s;
    }
    else if (mode.equals("s")){
        Plabel.setText(label_pressure_t);
        stringfield.setEnabled(true);
        modeMenu.select(label_source_mode);
        pstring=s;
    }
    else if (mode.equals("u")) {
        stringfield.setEnabled(false);
        modeMenu.select(label_drag_mode);
    }

    pipe.change=true;
    pipe.setup();
    if(isRunning)clock.startClock();
  }
  /**
  *
  * Removes all detectors from plot
  *
  *
  *
  */
  public void removeAllDetectors(){
    pipe.removeAllDetectors();
  }

  /**
  *
  * Set pixels per unit
  *
  * @param x  double The x position of the detector.
  * @param y  double The y position of the detector.
  *
  * @return int the id of new detector
  */
  public int addDetector(double x,double y){

    return pipe.addDetector(  x,  y);
  }

  /**
  *
  * removes one detector
  *
  * @param id  hashcode of detector to remove
  *
  */
  public void removeDetector(int id){
  pipe.removeDetector(id);
  }
  /**
  *
  * Mathod sets pixel width of pipe walls
  *
  * @param w  int
  *
  */
  public void setWallW(int w){
   pipe.setWallW(w);
  }

  /**
  *
  * MouseSensitivity relates to U-Drag-It mode
  * High sensitivity means piston moves farther relative to mouse movements
  *
  * @param ms  double
  *
  */
  public void setMouseSensitivity(double ms){
    pipe.setMouseSensitivity(ms);
  }
  /**
  *
  * Sets contrast factor for painting in U-Drag Mode.
  * High factor yeilds high contrast in wave.
  *
  * @param dfault boolean  use default
  * @param usf double Manually-set scale factor for U-Drag Mode
  *
  */
  public void setUPaintScale(boolean dfault, double usf){
    pipe.setUPaintScale(dfault,usf);
  }

  /**
  *
  * Sets contrast factor for painting in Anayltic Mode.
  * High factor yeilds high contrast in wave.
  *
  * @param dfault boolean  use default
  * @param usf double Manually-set scale factor for Analytic Mode
  *
  */
  public void setAPaintScale(boolean dfault, double asf){
  pipe.setAPaintScale(dfault,asf);
  }
  /**
  *
  * Sets contrast factor for painting in Piston Mode.
  * High factor yeilds high contrast in wave.
  *
  * @param dfault boolean  use default
  * @param usf double Manually-set scale factor for Piston Mode
  *
  */
  public void setPPaintScale(boolean dfault, double psf){
  pipe.setPPaintScale(dfault,psf);
  }

  public void setContrast(double contrast){
    if(contrast<=0){
      pipe.setUPaintScale(true,4);
      pipe.setAPaintScale(true,1);
      pipe.setPPaintScale(true,2);
      return;
    }
     pipe.setUPaintScale(false,contrast);
     pipe.setAPaintScale(false,contrast);
     pipe.setPPaintScale(false,contrast);
  }








}

