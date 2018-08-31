package circuit;

import a2s.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;

import java.net.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;
import edu.davidson.display.*;


final public class IVApplet extends SApplet implements SStepable {
  private Parser ivFunc = new Parser(2);
  private Parser phaseFunc = new Parser(1);
  static final double pi=Math.PI;
  static final double pi2=2*Math.PI;
  static final double sqrt2=Math.sqrt(2);
  double vmin;
  double vmax;
  double freqVal=60.0; // 60 Hz
  double tmax=3.0/freqVal;
  boolean showControls=true, showCheckBox = true, showGraph=true;
  boolean defaultCircuit=true;
  boolean showIV=false;
  boolean autoRefresh=true;
  double volt=1;
  int preferredPixPerCell=60;

  String currentStr="V";
  String phaseStr="0";
  EtchedBorder etchedBorder1 = new EtchedBorder();
  EtchedBorder etchedBorder2 = new EtchedBorder();
  SSlider vSlider = new SSlider();
  SNumber vNumber = new SNumber();
  private Circuit circuit=new Circuit(this);
  IVGraph graph = new IVGraph();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  private int xold, yold;
 // private Format    format= new Format("%-+8.4g");
  private Part bat;
  private Part voltmeter;
  private Part ammeter;
  private Part part;
  Panel panel1 = new Panel();
  Label label1 = new Label();
  Checkbox checkbox1 = new Checkbox();

  /**
   * @y.exclude
   */
  public IVApplet() {
  }

  boolean parseIVFunction(){
     ivFunc.defineVariable(1,"v"); // define the variable
     ivFunc.defineVariable(2,"f"); // define the variable
     ivFunc.define( currentStr.toLowerCase() );
     ivFunc.parse();
     if(ivFunc.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse I(V,f): "+currentStr);
         System.out.println("Parse error: " + ivFunc.getErrorString() +
                   " at IV function, position " + ivFunc.getErrorPosition());
         return false;
     }
     return true;
  }

  boolean parsePhaseFunction(){
     phaseFunc.defineVariable(1,"f"); // define the variable
     phaseFunc.define(phaseStr.toLowerCase());
     phaseFunc.parse();
     if(phaseFunc.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse phase(f): "+phaseStr);
         System.out.println("Parse error: " + phaseFunc.getErrorString() +
                   " at phase function, position " + phaseFunc.getErrorPosition());
         return false;
     }
     return true;
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
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     */
  public void init() {
    initResources(null);
    String resourceFile = "";
    double     dt=0.1;
    double     fps=10;
    try { resourceFile = this.getParameter("Resources", ""); } catch (Exception e) { e.printStackTrace(); }
    if(resourceFile!=null && !resourceFile.equals(""))loadResources(resourceFile);
    try { fps = Double.valueOf(this.getParameter("FPS", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { defaultCircuit = Boolean.valueOf(this.getParameter("DefaultCircuit", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { currentStr = this.getParameter("Current", "2*V"); } catch (Exception e) { e.printStackTrace(); }
    try { phaseStr = this.getParameter("Phase", "0"); } catch (Exception e) { e.printStackTrace(); }
    try { vmin = Double.valueOf(this.getParameter("Vmin", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { vmax = Double.valueOf(this.getParameter("Vmax", "1000")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showGraph = Boolean.valueOf(this.getParameter("ShowGraph", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    if(showGraph)preferredPixPerCell=60; else preferredPixPerCell=200;
    try { showCheckBox = Boolean.valueOf(this.getParameter("ShowCheckBox", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showIV = Boolean.valueOf(this.getParameter("IVGraphType", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { preferredPixPerCell = Integer.parseInt(this.getParameter("PixPerCell", ""+preferredPixPerCell)); } catch (Exception e) { e.printStackTrace(); }
    try { jbInit(); } catch (Exception e) { e.printStackTrace();  }
    clock.setDt(dt);
    clock.setFPS((int)fps);
    parseIVFunction();
    parsePhaseFunction();
    checkbox1.setState(!showIV);
    checkbox1.setVisible(showCheckBox);
    etchedBorder2.setVisible(showControls);
    //graph.deleteAllSeries();
    graph.setBorders("0,10,10,5");
    graph.setMinMaxX(vmin,vmax);
    graph.setMinMaxY(-1,1);
    graph.setEnableMouse(true);
    graph.setBackground(Color.white);

    vSlider.setDMax(Math.max(Math.abs(vmin),Math.abs(vmax)) );
    vSlider.setDMin(0);
    volt=Math.min(volt,vmax);
    volt=Math.max(volt,vmin);
    vNumber.setValue(volt);
    vSlider.setDValue(volt);
    vNumber.addPropertyChangeListener(vSlider);
    vSlider.addPropertyChangeListener(vNumber);
    if(vmax<10)vNumber.setFormat("%6.1f");
      else vNumber.setFormat("%6.0f");
    if(freqVal!=0)tmax=3.0/freqVal;
        else  tmax=1.0;
    clock.addClockListener(this);
 //   if(showGraph) circuit.setBackground(Color.lightGray);
 //         else circuit.setBackground(new Color(225,225,225));
  }

  private void firstTime(){
    if(showIV){
      vSlider.setDMax(vmax);
      vSlider.setDMin(vmin);
      vSlider.setDValue(volt);
      graph.setAutoscaleY(true);
      graph.setLabelY(Common.CURRENT);
      graph.setLabelX(Common.VOLTAGE);
      graph.setMinMaxX(vmin,vmax);
    } else{  //I(t)
      volt=Math.abs(volt);
      vSlider.setDMax(Math.max(Math.abs(vmin),Math.abs(vmax)) );
      vSlider.setDMin(0);
      vSlider.setDValue(volt);
      graph.setAutoscaleY(false);
      graph.setLabelY(Common.VOLTAGE_CURRENT);
      graph.setLabelX(Common.TIME);
      graph.setMinMaxX(0,tmax );
      graph.setMinMaxY(Math.min(1.1*findYMin(),0.9*findYMin()),Math.max(1.2*findYMax(),0.8*findYMax()) );
    }
    if(defaultCircuit){
      createCircuit();
      setCircuitValues();
      plotFunction();
      graph.setVisible(showGraph);
    }else{
      circuit.setPreferredPixPerCell(preferredPixPerCell);
      circuit.setGridSize(3,2);
      showGraph=false;
      graph.setVisible(false);
    }
    graph.setOwner(this);
    graph.repaint();
  }

  //Component initialization
  private void jbInit() throws Exception{
    this.setBackground(Color.lightGray);
    /** j2sNative */{
    	  this.setSize(new Dimension(740, 390));
    }
    vSlider.setDMax(10.0);
    vNumber.addActionListener(new IVApplet_vNumber_actionAdapter(this));
    vSlider.addAdjustmentListener(new IVApplet_vSlider_adjustmentAdapter(this));
    panel1.setBackground(Color.lightGray);
    graph.setLabelY(Common.CURRENT);
    label1.setAlignment(2);
    label1.setText(Common.VOLTAGE);
    checkbox1.setLabel(Common.LEGEND_VTIME);
    checkbox1.addItemListener(new IVApplet_checkbox1_itemAdapter(this));
    graph.setSampleData(false);
    graph.setAutoscaleX(false);
    graph.setAutoscaleY(false);
    graph.setLabelX(Common.VOLTAGE);
    etchedBorder2.setLayout(borderLayout1);
    etchedBorder1.setLayout(borderLayout3);
    this.setLayout(borderLayout2);
    this.add(etchedBorder1, BorderLayout.CENTER);
    etchedBorder1.add(circuit, BorderLayout.WEST);
 //   etchedBorder1.add(circuitBox, BorderLayout.WEST);
    etchedBorder1.add(graph, BorderLayout.CENTER);
    this.add(etchedBorder2, BorderLayout.SOUTH);
    etchedBorder2.add(vSlider, BorderLayout.CENTER);
    etchedBorder2.add(vNumber, BorderLayout.EAST);
    etchedBorder2.add(panel1, BorderLayout.WEST);
    panel1.add(checkbox1, null);
    panel1.add(label1, null);
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
      if(firstTime){
        firstTime=false;
        firstTime();
      }else graph.repaint();
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
   * Called when the clock stops in one-shot mode.    DO NOT SCRIPT.
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
       part.setTime(0);
       clearAllData();
   }

   /**
    * @y.exclude
    */
  final public String getAppletInfo() {
    return "IVApplet by Wolfgang Christian, wochristian@davidson.edu";
  }

/**
 * Gets the ID for the unknown part.
 * The ID can be used to make a connection to a SDataSource.
 *
 * @return int the identifier
*/
  final public int getUnknownID(){
      return part.getID();
  }

  /**
 * Gets the ID for the ammeter.
 * The ID can be used to make a connection to a SDataSource.
 *
 * @return int the identifier
*/
  final public int getAmmeterID(){
      return this.ammeter.getID();
  }

  /**
   * Gets the ID for the voltmeter.
   * The ID can be used to make a connection to a SDataSource.
   *
   * @return int the identifier
  */
    final public int getVoltmeterID(){
        return voltmeter.getID();
    }


  /**
   * @y.exclude
   */

  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"Current", "String", "Current: I(V,f)"},
      {"Phase", "String", "Phase: P(f)"},
      {"Vmin", "double", "Minimum voltage"},
      {"VMax", "double", "Maximum voltage."},
      {"ShowControls", "boolean", "Show the slider."},
      {"ShowCheckBox", "boolean", "Show the check box."},
      {"IVGraphType", "boolean", "Plot I(V) graph instead of I(t) and V(t)."},
    };
    return pinfo;
  }

  // Methods added by W. Christian

  private void createCircuit(){
    circuit.setPreferredPixPerCell(preferredPixPerCell);
    circuit.setGridSize(3,2);
    bat=circuit.addSineWave(0,0,0,1);
    bat.setVoltRMS(volt);
    bat.showPhase=false;
    bat.showF=false;
    bat.showV=false;
    part=circuit.addPart(1,0,1,1);
    part.setLabel("  ?");
    part.showR=false;
    part.showV=false;
    part.showZ=false;
    part.showPhase=false;
    circuit.addWire(0,0,1,0);
    ammeter=circuit.addAmmeter(0,1,1,1);
    circuit.addWire(1,0,2,0);
    circuit.addWire(1,1,2,1);
    voltmeter=circuit.addVoltmeter(2,0,2,1);
    if(freqVal==0){
        bat.setLabel(" "+Common.LEGEND_V);
        ammeter.setLabel(Common.LEGEND_A);
        voltmeter.setLabel(Common.LEGEND_V);
        bat.showF=false;
    }else{
        bat.setLabel(Common.LEGEND_VAC);
        ammeter.setLabel(Common.LEGEND_A);
        voltmeter.setLabel(Common.LEGEND_V);
        bat.showF=true;
    }
  }

  private void setCircuitValues(){
      if(bat==null) return;
      double phase=phaseFunc.evaluate(freqVal);
      bat.setVoltRMS(volt);
      bat.setF(freqVal);
      voltmeter.setVoltRMS(volt);
      part.setVoltRMS(volt);
      voltmeter.setVoltRMS(volt);
      ammeter.setCurrentRMS(calcCurrentRMS());
      double t=clock.getTime();
      double w=pi2*freqVal;
      //double rad=part.getPhaseRadian();
      double v = volt*sqrt2*Math.sin(w*t-phase);
      double i = ivFunc.evaluate( sqrt2*volt*Math.sin(w*t),freqVal );  // iv function is amplitude
      part.setTime(t);
      part.setVoltInstantaneous(v);
      part.setCurrentInstantaneous(i);
      part.setPhaseRadian(phase);
  }

  final double calcCurrentRMS(){
   // Find the RMS curent by integrating over one cycle.
     if(freqVal==0) return ivFunc.evaluate(volt,freqVal);
     double volt2=sqrt2*volt;
     int np=32;
     double dt=1.0/(np-1.0);
     double t= 0;
     double v= 0; // volt*Math.sin(pi2*t) is zero for first term.
     double c=ivFunc.evaluate(v,freqVal); // temporary current
     double sum=c;                        // since the first and the last points are 0 and 2*pi we can combine terms in Simpson's rule.
     for(int i=0; i<np-2; i++) {
            t +=dt; // increment t
            v = volt2*Math.sin(pi2*t);
            c=ivFunc.evaluate(v,freqVal);
            sum += c*c;
      }
      sum =Math.sqrt(sum/(np-1));
      //return ivFunc.evaluate(volt,freqVal);
      return sum;
  }

  final void plotFunction(){
    if(showIV){
        plotIV();
    } else{
        plotV();
    }
  }
  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void step(double dt, double t){
      double wt=pi2*freqVal*t;
      if(part==null) return;
      //double rad=part.getPhaseRadian();
      double v = volt*sqrt2*Math.sin(wt-part.getPhaseRadian());
      double i = ivFunc.evaluate( sqrt2*volt*Math.sin(wt),freqVal );  // iv function is amplitude
      part.setTime(t);
      part.setVoltInstantaneous(v);
      part.setCurrentInstantaneous(i);
      this.updateDataConnections();
  }

  final void plotV(){
     int np=200;
     double dt=tmax/(np-1);
     double[] x= new double[np];
     double[] y1= new double[np];
     double[] y2= new double[np];
     double w=pi2*freqVal;
     double phase=phaseFunc.evaluate(freqVal);
     for(int i=0; i<np; i++) {
            x[i] = i*dt;
            y1[i] = volt*sqrt2*Math.sin(w*x[i]-phase);
            y2[i] = ivFunc.evaluate( sqrt2*volt*Math.sin(w*x[i]),freqVal );  // iv function is amplitude
      }
      graph.setAutoReplaceData(1,true);
      graph.setAutoReplaceData(2,true);
      graph.clearSeriesData(1);
      graph.clearSeriesData(2);
      graph.addData(1,x,y1);
      graph.addData(2,x,y2);
      graph.setSeriesStyle(1,Color.black,true,0);
      graph.setSeriesStyle(2,Color.red,true,0);
      graph.setSeriesLegend(1, Color.black,75,20,Common.LEGEND_V);
      graph.setSeriesLegend(2, Color.red,125,20,Common.LEGEND_I);
  }
  double findYMax(){
     double ymax=ivFunc.evaluate(0,freqVal);
     ymax=Math.max(ymax,ivFunc.evaluate(vmin,freqVal));
     ymax=Math.max(ymax,ivFunc.evaluate(vmax,freqVal));
     ymax=Math.max(ymax,ivFunc.evaluate(-vmin,freqVal));
     ymax=Math.max(ymax,ivFunc.evaluate(-vmax,freqVal));
     ymax=Math.max(ymax,-vmax);
     return Math.max(ymax*sqrt2,vmax*sqrt2);  // convert from RMS
  }

  double findYMin(){
     double ymin=ivFunc.evaluate(0,freqVal);
     ymin=Math.min(ymin,ivFunc.evaluate(vmin,freqVal));
     ymin=Math.min(ymin,ivFunc.evaluate(vmax,freqVal));
     ymin=Math.min(ymin,ivFunc.evaluate(-vmin,freqVal));
     ymin=Math.min(ymin,ivFunc.evaluate(-vmax,freqVal));
     ymin=Math.min(ymin,-vmax);
     return Math.min(ymin*sqrt2,vmax*sqrt2);  // convert from RMS
  }

  void plotIV(){
     int np=200;
     double dv=(vmax-vmin)/(np-1);
     double[] x= new double[np];
     double[] y= new double[np];
     for(int i=0; i<np; i++) {
            x[i] = vmin+i*dv;
            y[i] = ivFunc.evaluate(x[i],freqVal);
      }
      graph.clearSeriesData(1);
      graph.clearSeriesData(2);
      graph.addData(1,x,y);
      graph.setSeriesStyle(1,Color.red,true,0);
     // graph.setSeriesLegend(1, Color.red,75,20," I(V) = "+currentStr);
      xold=-100;
      yold=-100;
  }

  void adjustVoltage(double v){
      volt=v;
      if(volt<vmin){
          volt=vmin;
          vSlider.setDValue(volt);
 //         vNumber.setValue(volt);
      }
      if(volt>vmax){
          volt=vmax;
          vSlider.setDValue(volt);
//          vNumber.setValue(volt);
      }
      setCircuitValues();
      this.updateDataConnections();
      if(!showIV){
         plotV();
         return;
      }
      int xpix=graph.pixFromX(volt);
      int ypix=graph.pixFromY(ivFunc.evaluate(volt,freqVal));;     // get value from IV function
      Graphics g=graph.getGraphics();
      g.setColor(Color.green);
      g.setXORMode(Color.red);
      g.fillOval(xold-5,yold-5,10,10);
      g.fillOval(xpix-5,ypix-5,10,10);
      xold=xpix; yold=ypix;
      g.setPaintMode();
      g.dispose();
  }

  void vSlider_adjustmentValueChanged(AdjustmentEvent e) {
      adjustVoltage(vSlider.getDValue());
  }

  void vNumber_actionPerformed(ActionEvent e) {
      if(vNumber.isValid()) adjustVoltage(vNumber.getValue());
  }
  public void setSourceVoltage(double v,boolean showV){
    volt=v;
    bat.showV=showV;
    adjustVoltage(volt);
  }
  public void setDefault(){
    oneShotMsg=null;
    this.deleteDataConnections();
    this.clock.stopClock();
    this.clock.setTime(0);
    freqVal=60.0; // 60 Hz
    tmax=3.0/freqVal;
    //preferredPixPerCell=ppc;
    phaseStr="0";
    parsePhaseFunction();
    currentStr="0";
    parseIVFunction();
    circuit.setDefault(preferredPixPerCell);  // this removes the cuircuit
    createCircuit();
    setCircuitValues();
    graph.setTitle(null);
    graph.setVisible(showGraph);
    if(autoRefresh) setShowIV(showIV);  // this also plots the functions.
  }

  public void setAutoRefresh(boolean ar){
    if(autoRefresh==ar) return;
    autoRefresh=ar;
    graph.setAutoRefresh(ar);
    if(autoRefresh)setShowIV(showIV);
  }

  /**
   * Set the graph title.
   *
   * @param              str Title string.
   */
  public void setTitle(String str){graph.setTitle(str); }

  public void setCurrentFunction(String str){
     currentStr=str;
     parseIVFunction();
     setCircuitValues();
     if(autoRefresh) setShowIV(showIV);
  }

  public void setPhaseFunction(String str){
     phaseStr=str;
     parsePhaseFunction();
     setCircuitValues();
     if(autoRefresh) setShowIV(showIV);
  }

  public void setVoltage(double v){ volt=v; vSlider.setDValue(volt); adjustVoltage(volt);}

  /**
   * Sets the frequency for the voltage source.
   * @param f the frequency
   */
  public void setFrequency(double f){
      freqVal=f;
      if(freqVal==0){
          bat.setLabel(" "+Common.LEGEND_V);
          ammeter.setLabel(Common.LEGEND_A);
          voltmeter.setLabel(Common.LEGEND_V);
          bat.showF=false;
      }else{
          bat.setLabel(Common.LEGEND_VAC);
          ammeter.setLabel(Common.LEGEND_A);
          voltmeter.setLabel(Common.LEGEND_V);
          bat.showF=true;
      }
      if(autoRefresh) setShowIV(showIV);
  }
  public void setTMax(double tm){tmax=tm; if(autoRefresh) setShowIV(showIV);}
  public void setShowControls(boolean sc){etchedBorder2.setVisible(sc);}
  public void setShowCheckBox(boolean scb){checkbox1.setVisible(scb);}
  public void setShowGraph(boolean sg){
      if(graph.isVisible()==sg) return;
      showGraph=sg;
      graph.setVisible(sg);
      invalidate();
      validate();
  }

  public void setPartLabel(String str){part.setLabel(str);}

  public boolean setPartHint(String str){
      return circuit.setHint( part.getID(), str);
  }

  public boolean setVoltmeterHint(String str){
      return circuit.setHint( voltmeter.getID(), str);
  }
  /**
   * Sets the hint for the ammeter.
   * @param str the hint
   * @return true if successful; false otherwise
   */

  public boolean setAmmeterHint(String str){
      return circuit.setHint( ammeter.getID(), str);
  }

  public boolean setMilliAmp(boolean showMilliAmp){
      return circuit.setMilliAmp( ammeter.getID(), showMilliAmp);
  }

  /**
   * Sets the hint for the voltage source.
   * @param str the hint
   * @return true if successful; false otherwise
   */
  public boolean setACHint(String str){
      return circuit.setHint( bat.getID(), str);
  }

  public void setPixPerCell(int ppc){
      if(preferredPixPerCell==ppc) return;
      preferredPixPerCell=ppc;
      circuit.setPreferredPixPerCell(preferredPixPerCell);
  }
  public void setIVGraphType(boolean iv){
      showIV=iv;
      if(autoRefresh) setShowIV(showIV);
  }
  public void setShowIV(boolean siv){
      showIV=siv;
      graph.setAutoRefresh(false);
      graph.deleteSeries(1);
      graph.deleteSeries(2);
      if(showIV){
          vSlider.setDMinMaxAndValue(vmin,vmax,volt);
          graph.setAutoscaleY(true);
          xold=-100;
          yold=-100; // make sure the first dot if off the screen.
          graph.setLabelY(Common.CURRENT);
          graph.setLabelX(Common.VOLTAGE);
          graph.setMinMaxX(vmin,vmax);
          graph.setMinMaxY(0,volt); // autoscale is true so this is just a place holder.
      }else{
          volt=Math.abs(volt);
          vSlider.setDMinMaxAndValue(0,Math.max(Math.abs(vmin),Math.abs(vmax)), volt );
  //        vNumber.setValue(volt);
          graph.setAutoscaleY(false);
          graph.setLabelY(Common.VOLTAGE_CURRENT);
          graph.setLabelX(Common.TIME);
          graph.setMinMaxX(0,tmax);
          graph.setMinMaxY(Math.min(1.1*findYMin(),0.9*findYMin()),Math.max(1.2*findYMax(),0.8*findYMax()) );
          //graph.setMinMaxY(-1.1*volt,1.2*volt);
      }
      setCircuitValues();
      plotFunction();
      graph.setAutoRefresh(autoRefresh);
      graph.repaint();
  }

  void checkbox1_itemStateChanged(ItemEvent e) {
       if(e.getStateChange()==ItemEvent.SELECTED){
           setShowIV(false);
       }else{
           setShowIV(true);
       }
  }

  //  Inner Class
  class IVGraph extends SGraph{// inner class to paint the dragable oval.
      public void paintLast(Graphics g, Rectangle r) {
          super.paintLast(g,r);
          if(!showIV) return;
          g.clipRect(r.x,r.y,r.width,r.height);
          g.setColor(Color.green);
          g.setXORMode(Color.red);
          double xx=vSlider.getDValue();
          xold=graph.pixFromX(xx);
          yold=graph.pixFromY(ivFunc.evaluate(xx,freqVal));
          g.fillOval(xold-5,yold-5,10,10);
          g.setPaintMode();
      }
  }// end of IVGraph
}

class IVApplet_vSlider_adjustmentAdapter implements java.awt.event.AdjustmentListener {
  IVApplet adaptee;

  IVApplet_vSlider_adjustmentAdapter(IVApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.vSlider_adjustmentValueChanged(e);
  }
}

class IVApplet_vNumber_actionAdapter implements java.awt.event.ActionListener {
  IVApplet adaptee;

  IVApplet_vNumber_actionAdapter(IVApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.vNumber_actionPerformed(e);
  }
}

class IVApplet_checkbox1_itemAdapter implements java.awt.event.ItemListener {
  IVApplet adaptee;

  IVApplet_checkbox1_itemAdapter(IVApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.checkbox1_itemStateChanged(e);
  }
}

