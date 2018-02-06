package circuit;

import java.awt.*;
import java.awt.event.*;
import java.net.*;


import edu.davidson.tools.SApplet;
import edu.davidson.graphics.*;
import edu.davidson.display.*;

public class LoadApplet extends SApplet {
  String currentStr;
  double rmin;
  double rmax;
  boolean showControls=true, showGraph=true;
  boolean showPower=false;
  boolean defaultCircuit=true;
  int preferredPixPerCell=60;
  EtchedBorder etchedBorder1 = new EtchedBorder();
  EtchedBorder etchedBorder2 = new EtchedBorder();
  SSlider rSlider = new SSlider();
  SNumber rNumber = new SNumber();
  private Circuit circuit=new Circuit(this);
  Box circuitBox = new Box(circuit, Common.CIRCUIT);
  LoadGraph graph = new LoadGraph();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  Label label1 = new Label();
  private int xold, yold;
 // private Format    format= new Format("%-+8.4g");
  private Part bat;
  private Part load;
  private Part meter;
  private Part unknown;
  private double loadRes;
  private double unknownRes=20;
  private double batVolt=12;


  //Construct the applet
  public LoadApplet() {
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void init() {
    initResources(null);
    double     dt=0.1;
    double     fps=10;
    String resourceFile = "";
    try { resourceFile = this.getParameter("Resources", ""); } catch (Exception e) { e.printStackTrace(); }
    if(resourceFile!=null && !resourceFile.equals(""))loadResources(resourceFile);
    try { fps = Double.valueOf(this.getParameter("FPS", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { defaultCircuit = Boolean.valueOf(this.getParameter("DefaultCircuit", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { batVolt = Double.valueOf(this.getParameter("Battery", "12")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { unknownRes = Double.valueOf(this.getParameter("Unknown", "20")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showGraph = Boolean.valueOf(this.getParameter("ShowGraph", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    if(showGraph)preferredPixPerCell=60; else preferredPixPerCell=200;
    try { rmin = Double.valueOf(this.getParameter("Rmin", "0")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { rmax = Double.valueOf(this.getParameter("Rmax", "100")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showPower = Boolean.valueOf(this.getParameter("PowerGraphType", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { preferredPixPerCell = Integer.parseInt(this.getParameter("PixPerCell", ""+preferredPixPerCell)); } catch (Exception e) { e.printStackTrace(); }
    try { jbInit(); } catch (Exception e) { e.printStackTrace(); }
    clock.setDt(dt);
    clock.setFPS((int)fps);
    setBackground(Color.lightGray);
    etchedBorder2.setVisible(showControls);
    if(showPower)
        graph.setLabelY(Common.LOAD_POWER);
    else
        graph.setLabelY(Common.VOLTAGE);
    graph.deleteAllSeries();
    graph.setMinMaxX(rmin,rmax);
    graph.setMinMaxY(-1,1);
    graph.setEnableMouse(true);
    graph.setBackground(Color.white);
    rSlider.setDMax(rmax);
    rSlider.setDMin(rmin);
    loadRes=(rmax+rmin)/2;
    rSlider.setDValue(loadRes);
    rNumber.setValue(loadRes);
    loadRes=rNumber.getValue();
    rNumber.addPropertyChangeListener(rSlider);
    rSlider.addPropertyChangeListener(rNumber);
    if(rmax<100)rNumber.setFormat("%6.1f");
      else rNumber.setFormat("%6.0f");
    if(defaultCircuit){
        createCircuit();
        setCircuitValues();
        plotFunction();
        graph.setVisible(showGraph);
    }else{
        circuit.setPreferredPixPerCell(preferredPixPerCell);
        circuit.setGridSize(4,2);
        showGraph=false;
        graph.setVisible(false);
    }
  }

  private void createCircuit(){
    circuit.setPreferredPixPerCell(preferredPixPerCell);
    circuit.setGridSize(4,2);
    bat=circuit.addBattery(0,1,0,0);
    unknown=circuit.addResistor(0,1,1,1);
    unknown.setLabel("Rx");
    unknown.showR=false;
    unknown.showV=true;
    circuit.addWire(1,1,2,1);
    circuit.addWire(0,0,2,0);
    load=circuit.addResistor(2,1,2,0);
    load.setLabel("Rl");
    load.showR=true;
    load.showV=true;
    circuit.addWire(2,0,3,0);
    circuit.addWire(2,1,3,1);
    meter=circuit.addVoltmeter(3,0,3,1);
  }

  private void setCircuitValues(){
      bat.setVoltRMS(batVolt);
      unknown.setR(unknownRes);
      load.setR(loadRes);
      double vout=outVolt(loadRes);
      double i=batVolt/(unknownRes+loadRes);
      meter.setVoltRMS( vout);

      load.setVoltRMS( vout);
      load.setVoltInstantaneous(vout);
      load.setCurrentInstantaneous(i);

      unknown.setVoltRMS(batVolt-vout);
      unknown.setVoltInstantaneous(batVolt-vout);
      unknown.setCurrentInstantaneous(i);
      this.updateDataConnections();
  }

  //Component initialization
  private void jbInit() throws Exception{
    this.setBackground(Color.lightGray);
    this.setSize(new Dimension(740, 390));
    rSlider.setDMax(200.0);
    rNumber.addActionListener(new LoadApplet_rNumber_actionAdapter(this));
    rSlider.addAdjustmentListener(new LoadApplet_rSlider_adjustmentAdapter(this));
    circuitBox.setTitle(Common.CIRCUIT);
    graph.setLabelY(Common.LOAD_POWER);
    label1.setBackground(Color.lightGray);
    label1.setAlignment(1);
    label1.setText(Common.LOAD_RESISTOR);
    graph.setSampleData(false);
    graph.setAutoscaleX(false);
    graph.setAutoscaleY(true);
    graph.setLabelX(Common.LOAD_RESISTOR);
    etchedBorder2.setLayout(borderLayout1);
    etchedBorder1.setLayout(borderLayout3);
    this.setLayout(borderLayout2);
    this.add(etchedBorder1, BorderLayout.CENTER);
    etchedBorder1.add(circuitBox, BorderLayout.WEST);
    etchedBorder1.add(graph, BorderLayout.CENTER);
   // if(showControls)
        this.add(etchedBorder2, BorderLayout.SOUTH);
    etchedBorder2.add(rSlider, BorderLayout.CENTER);
    etchedBorder2.add(rNumber, BorderLayout.EAST);
    etchedBorder2.add(label1, BorderLayout.WEST);
  }

  void loadResources(String rc) {
        if (rc == null) return;
        try {
            Common.initResources(new URL(getCodeBase(), rc).openStream());
            Common.setResources();
            return;
        } catch (Exception x) {
            //System.out.println("Can't load resources! : " + x.getMessage());
        }
        try {
            //Common.initResources(new URL(getCodeBase(), rc).openStream());
            Common.initResources(new URL(getDocumentBase(), rc).openStream());
            Common.setResources();
        } catch (Exception x) {

            System.out.println("Can't load resources! : " + x.getMessage());
            return;
        }
    }

  /**
   * Destroy all threads and cleanup the applet.
    * Exclude the javadoc because this method should not be scripted.
    * @y.exclude
    */
    public void destroy(){
       circuit.forceBubbleHelp(null);
       graph.destroy();
       super.destroy();
    }

    /**
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     */
    public void start() {
        graph.setOwner(this);
        graph.repaint();
        super.start();
    }

    /**
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     */
    public void stop() {
        oneShotMsg=null;
        circuit.forceBubbleHelp(null);
        super.stop();
    }

 /**
   * Starts the animation thread.
   */
  public void forward(){
      circuit.forceBubbleHelp(null);
      super.forward();
  }

  /**
   * Called when the clock stops in one-shot mode.    DO NOT SCRIPT.
   */
  protected void stoppingClock(){
      circuit.forceBubbleHelp(oneShotMsg);
  }

/**
    * Called when the clock cycles in cycle mode.    DO NOT SCRIPT.
    */
   protected void cyclingClock(){
       clock.setTime(0);
       load.setTime(0);
       unknown.setTime(0);
       clearAllData();
   }

     /**
 * Get the ID for the unknown resistor.
 * The ID can be used to make a connection to a SDataSource.
 *
 * @return int the indentifier
*/
  final public int getUnknownID(){
      return unknown.getID();
  }
    /**
 * Get the ID for the load resistor.
 * The ID can be used to make a connection to a SDataSource.
 *
 * @return int the indentifier
*/
  final public int getLoadID(){
      return load.getID();
  }

  //Get Applet information
  final public String getAppletInfo() {
    return "LoadApplet by Wolfgang Christian, wochristian@davidson.edu";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"Unknown", "double", "Internal resitance of battery."},
      {"Battery", "double", "Battery emf."},
      {"Vmin", "double", "Voltage min"},
      {"Vmax", "double", "Voltage max"},
      {"ShowControls", "boolean", "Show controls."},
      {"PowerGraphType", "boolean", "Plot load power as a function of resistance."},
    };
    return pinfo;
  }
  private double plotFunc(double r){
      if(showPower)
          return batVolt*batVolt*r/(r+unknownRes)/(r+unknownRes);
      else return batVolt*r/(r+unknownRes);
  }
  private double outVolt(double r){
      return batVolt*r/(r+unknownRes);
  }

  void plotFunction(){
     int np=200;
     double dr=(rmax-rmin)/(np-1);
     double[] x= new double[np];
     double[] y= new double[np];
     for(int i=0; i<np; i++) {
            x[i] = rmin+i*dr;
            y[i] = plotFunc(x[i]);
      }
      graph.setAutoReplaceData(3,true);
      graph.clearSeriesData(3);
      graph.addData(3,x,y);
      graph.setSeriesStyle(3,Color.red,true,0);
      //graph.setMarkerSize(3,2.0);
      xold=-100;
      yold=-100;
  }

  void adjustLoad(double r){
      loadRes=r;
      if(loadRes<rmin){
          loadRes=rmin;
          rSlider.setDValue(loadRes);
 //         rNumber.setValue(loadRes);
      }
      if(loadRes>rmax){
          loadRes=rmax;
          rSlider.setDValue(loadRes);
 //         rNumber.setValue(loadRes);
      }
      double v=plotFunc(r);
      int xpix=graph.pixFromX(r);
      int ypix=graph.pixFromY(v);
      Graphics g=graph.getGraphics();
      g.setColor(Color.green);
      g.setXORMode(Color.red);
      g.fillOval(xold-5,yold-5,10,10);
      g.fillOval(xpix-5,ypix-5,10,10);
      xold=xpix; yold=ypix;
      g.setPaintMode();
      g.dispose();
      setCircuitValues();
  }

  void rSlider_adjustmentValueChanged(AdjustmentEvent e) {
      adjustLoad(rSlider.getDValue());
  }

  void rNumber_actionPerformed(ActionEvent e) {
      if(rNumber.isValid()) adjustLoad(rNumber.getValue());
  }

  public void setPixPerCell(int ppc){
      preferredPixPerCell=ppc;
      circuit.setPreferredPixPerCell(preferredPixPerCell);
  }
  public void setDefault(){
      oneShotMsg=null;
    this.deleteDataConnections();
    //preferredPixPerCell=ppc;
    circuit.setDefault(preferredPixPerCell);  // this removes the circuit
    createCircuit();
    setCircuitValues();
    graph.setTitle(null);
    if(graph.isVisible())plotFunction();
  }

  /**
   * Set the graph title.
   *
   * @param              str Title string.
   */
  public void setTitle(String str){graph.setTitle(str); }

  public void setPowerGraphType(boolean sp){
      //if(showPower==sp) return;
      showPower=sp;
      if(showPower) graph.setLabelY(Common.LOAD_POWER);
        else graph.setLabelY(Common.VOLTAGE);
      plotFunction();
      graph.repaint();
  }
  public void setShowGraph(boolean sg){
      if(graph.isVisible()==sg){
          plotFunction();
          return;
      }
      showGraph=sg;
      graph.setVisible(sg);
//      if(sg) circuit.setBackground(Color.lightGray);
//        else circuit.setBackground(new Color(225,225,225));
      invalidate();
      validate();
      if(sg)plotFunction();
  }

  public void  setUnknown(double r){
     unknownRes=r;
     setCircuitValues();
     if(graph.isVisible()) plotFunction();
  }

  public void setVoltage(double v){
     batVolt=v;
     setCircuitValues();
     if(graph.isVisible()) plotFunction();
  }

  public void setLoad(double r){
      rSlider.setDValue(r);
      adjustLoad(r);
  }

  public boolean setBatteryHint(String str){
      return circuit.setHint( bat.getID(), str);
  }

  public boolean setVoltmeterHint(String str){
      return circuit.setHint( meter.getID(), str);
  }

  public boolean setLoadHint(String str){
      return circuit.setHint( load.getID(), str);
  }

  public boolean setUnknownHint(String str){
      return circuit.setHint( unknown.getID(), str);
  }


  class LoadGraph extends SGraph{// inner class to paint the dragable oval.
      public void paintLast(Graphics g, Rectangle r) {
          super.paintLast(g,r);
         // if(!showVR) return;
          g.clipRect(r.x,r.y,r.width,r.height);
          g.setColor(Color.green);
          g.setXORMode(Color.red);
          double xx=rSlider.getDValue();
          xold=graph.pixFromX(xx);
          yold=graph.pixFromY(plotFunc(xx));
          g.fillOval(xold-5,yold-5,10,10);
          g.setPaintMode();
      }
  }// end of IVGraph
}

class LoadApplet_rSlider_adjustmentAdapter implements java.awt.event.AdjustmentListener {
  LoadApplet adaptee;

  LoadApplet_rSlider_adjustmentAdapter(LoadApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.rSlider_adjustmentValueChanged(e);
  }
}

class LoadApplet_rNumber_actionAdapter implements java.awt.event.ActionListener {
  LoadApplet adaptee;

  LoadApplet_rNumber_actionAdapter(LoadApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.rNumber_actionPerformed(e);
  }
}

