package circuit;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;

import java.net.*;

import edu.davidson.tools.SApplet;
import edu.davidson.tools.SStepable;
import edu.davidson.graphics.*;
import edu.davidson.display.*;


final public class LRCApplet extends SApplet implements SStepable {
  static final double pi=Math.PI;
  static final double pi2=2*Math.PI;
  static final double sqrt2=Math.sqrt(2);
  private String resourceFile = "";
  double resVal;
  double capVal;
  double indVal;
  double xc=0, xl = 0;
  double reactance=0;
  double fmin;
  double fmax;
  double tmax=0.1;
  double freqVal;
  boolean showControls=true, showCheckBox = true, showGraph=true;
  boolean showImpedance=false;
  boolean defaultCircuit=true;
  boolean autoRefresh=true;
  int preferredPixPerCell=60;

  String currentStr;
  EtchedBorder etchedBorder1 = new EtchedBorder();
  EtchedBorder etchedBorder2 = new EtchedBorder();
  SSlider fSlider = new SSlider();
  SNumber fNumber = new SNumber();
  private Circuit circuit=new Circuit(this);
  Box circuitBox = new Box(circuit, Common.CIRCUIT);
  RCGraph graph = new RCGraph();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  private int xold, yold;
 // private Format    format= new Format("%-+8.4g");
  private Part bat;
  private Part cap;
  private Part meter;
  private Part res;
  private Part ind;
  private double volt=10;
  Panel panel1 = new Panel();
  Label label1 = new Label();
  Checkbox checkbox1 = new Checkbox();


  //Construct the applet
  public LRCApplet() {
  }

  private void firstTime(){
    graph.setBorders("0,10,10,5");
    graph.setMinMaxX(fmin,fmax);
    graph.setMinMaxY(-1,1);
    graph.setEnableMouse(true);
    graph.setBackground(Color.white);
    if(showImpedance){
      graph.setLabelY(Common.CURRENT_RMS);
      graph.setLabelX(Common.FREQUENCY);
      graph.setMinMaxX(fmin,fmax);
      graph.setMinMaxY(0,1.1*volt/resVal);
      graph.setFormat("%-+6.2g");
    } else{
      graph.setLabelY(Common.VOLTAGE);
      graph.setLabelX(Common.TIME);
      graph.setMinMaxX(0,tmax );
      if(tmax<0.1) graph.setFormat("%-+6.2e");  else graph.setFormat("%-+6.2g");
      graph.setMinMaxY(-1.1*getVoltMax(),1.2*getVoltMax());
    }
    if(defaultCircuit){
      graph.setVisible(showGraph);
      circuit.setPreferredPixPerCell(preferredPixPerCell);
      createCircuit();
      setCircuitValues();
      plotFunction();
    }else{
      circuit.setPreferredPixPerCell(preferredPixPerCell);
      circuit.setGridSize(3,2);
      showGraph=false;
      graph.setVisible(false);
    }
    graph.setOwner(this);
    graph.repaint();
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void init() {
    initResources(null);
    double     dt=0.1;
    double     fps=10;
    try { resourceFile = this.getParameter("Resources", ""); } catch (Exception e) { e.printStackTrace(); }
    if(resourceFile!=null && !resourceFile.equals(""))loadResources(resourceFile);

    try { fps = Double.valueOf(this.getParameter("FPS", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { defaultCircuit = Boolean.valueOf(this.getParameter("DefaultCircuit", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { resVal = Double.valueOf(this.getParameter("Resistor", "25")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { capVal = Double.valueOf(this.getParameter("Capacitor", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { indVal = Double.valueOf(this.getParameter("Inductor", "98")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { fmin = Double.valueOf(this.getParameter("FMin", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { fmax = Double.valueOf(this.getParameter("FMax", "500")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showCheckBox = Boolean.valueOf(this.getParameter("ShowCheckBox", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showImpedance = Boolean.valueOf(this.getParameter("ImpedanceGraphType", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showGraph = Boolean.valueOf(this.getParameter("ShowGraph", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    if(showGraph)preferredPixPerCell=60; else preferredPixPerCell=200;
    try { preferredPixPerCell = Integer.parseInt(this.getParameter("PixPerCell", ""+preferredPixPerCell)); } catch (Exception e) { e.printStackTrace(); }
    try { jbInit(); } catch (Exception e) { e.printStackTrace();  }
    clock.setDt(dt);
    clock.setFPS((int)fps);
    checkbox1.setState(!showImpedance);
    checkbox1.setVisible(showCheckBox);
    etchedBorder2.setVisible(showControls);
    volt*=sqrt2;    // convert to max Volts.
    capVal*=1.0e-6;  // input units in uF
    indVal*=1.0e-3;  // input units is mH
    fSlider.setDMax(fmax);
    fSlider.setDMin(fmin);
    freqVal=(fmax+fmin)/2;
    fNumber.setValue(freqVal);
    fSlider.setDValue(freqVal);
    fNumber.addPropertyChangeListener(fSlider);
    fSlider.addPropertyChangeListener(fNumber);
    if(fmax<100)fNumber.setFormat("%6.1f");
      else fNumber.setFormat("%6.0f");
    tmax=10.0/fmax;
    clock.addClockListener(this);
  }

  //Component initialization
  private void jbInit() throws Exception{
    this.setBackground(Color.lightGray);
    /** j2sNative */{
    	  this.setSize(new Dimension(740, 390));
    }
    fSlider.setDMax(200.0);
    fNumber.addActionListener(new LRCApplet_fNumber_actionAdapter(this));
    fSlider.addAdjustmentListener(new LRCApplet_fSlider_adjustmentAdapter(this));
    circuitBox.setTitle(Common.CIRCUIT);
    panel1.setBackground(Color.lightGray);
    graph.setLabelY(Common.CURRENT_RMS);
    label1.setAlignment(2);
    label1.setText(Common.FREQUENCY);
    checkbox1.setLabel(Common.VOLTAGE);
    checkbox1.addItemListener(new LRCApplet_checkbox1_itemAdapter(this));
    graph.setLabelY(Common.VOLTAGE);
    graph.setSampleData(false);
    graph.setAutoscaleX(false);
    graph.setAutoscaleY(false);
    graph.setLabelX(Common.FREQUENCY);
    etchedBorder2.setLayout(borderLayout1);
    etchedBorder1.setLayout(borderLayout3);
    this.setLayout(borderLayout2);
    this.add(etchedBorder1, BorderLayout.CENTER);
    etchedBorder1.add(circuitBox, BorderLayout.WEST);
    etchedBorder1.add(graph, BorderLayout.CENTER);
    this.add(etchedBorder2, BorderLayout.SOUTH);
    etchedBorder2.add(fSlider, BorderLayout.CENTER);
    etchedBorder2.add(fNumber, BorderLayout.EAST);
    etchedBorder2.add(panel1, BorderLayout.WEST);
    panel1.add(checkbox1, null);
    panel1.add(label1, null);
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
      if(debugLevel>127)System.out.println("begin LRCApplet start");
      if(firstTime){
        firstTime=false;
        firstTime();
      }else graph.repaint();
      super.start();
      if(debugLevel>127)System.out.println("end LRCApplet start");
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
   * Starts the animation.
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
       res.setTime(0);
       ind.setTime(0);
       cap.setTime(0);
       clearAllData();
   }

  //Get Applet information
  final public String getAppletInfo() {
    return "LRCApplet by Wolfgang Christian, wochristian@davidson.edu";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"Resistor", "double", "Resistance in Ohm."},
      {"Capacitor", "double", "Capacitor value in micro-H."},
      {"Fmin", "double", "Minimum frequency"},
      {"FMax", "double", "Maximum frequency."},
      {"ShowControls", "boolean", "Show the slider."},
      {"ShowCheckBox", "boolean", "Show the current check-box."},
      {"ImpedanceGraphType", "boolean", "Plot current, I(f), rather than time series, V(t)."},
    };
    return pinfo;
  }

  // Methods added by W. Christian

  private void createCircuit(){
    xl=Math.abs(pi2*freqVal*indVal);
    xc=Math.abs(pi2*freqVal*capVal);
    if(xc!=0)xc=1/xc;
    reactance=xl-xc;
    circuit.setPreferredPixPerCell(preferredPixPerCell);
    circuit.setGridSize(4,2);
    bat=circuit.addSineWave(0,0,0,1);
    bat.setVoltRMS(volt/sqrt2);
    bat.showPhase=true;
    res=circuit.addResistor(0,1,1,1);
    res.setLabel("R");
    res.setR(resVal);
    res.showR=true;
    res.showV=true;
    res.showPhase=true;
    circuit.addWire(1,1,2,1);
    circuit.addWire(1,0,2,0);
    cap=circuit.addCapacitor(2,1,2,0);
    cap.setLabel("C");
    cap.setC(capVal*1.0e6);  // convert to uF
    cap.showC=true;
    cap.showV=true;
    cap.showPhase=true;

    ind=circuit.addInductor(0,0,1,0);
    ind.setLabel("L");
    ind.setL(indVal*1.0e3);  // convert to mH
    ind.showL=true;
    ind.showV=true;
    ind.showPhase=true;

    circuit.addWire(2,0,3,0);
    circuit.addWire(2,1,3,1);
    meter=circuit.addVoltmeter(3,0,3,1);
  }

  private void setCircuitValues(){
      double irms=volt/zMag()/sqrt2;
      if(bat==null || cap==null || res==null || ind==null) return;
      bat.setF(freqVal);
      bat.setCurrentRMS(irms);
      res.setVoltRMS(vrMag(freqVal)/sqrt2);
      res.setCurrentRMS(irms);
      res.setPhaseRadian(vrPhase(freqVal));
      meter.setVoltRMS(vrMag(freqVal)/sqrt2);
      cap.setVoltRMS(vcMag(freqVal)/sqrt2);
      cap.setCurrentRMS(irms);
      cap.setPhaseRadian(vcPhase(freqVal));
      ind.setVoltRMS(vlMag(freqVal)/sqrt2);
      ind.setCurrentRMS(irms);
      ind.setPhaseRadian(vlPhase(freqVal));
      double t=clock.getTime();
      double vc=vcMag(freqVal), vr=vrMag(freqVal), vl=vlMag(freqVal);
      double wt=pi2*freqVal*t;  // omega*time
      double i=vr*Math.sin(wt+res.getPhaseRadian())/ resVal;
      bat.setTime(t);
      bat.setVoltInstantaneous(volt*Math.sin(wt));
      bat.setCurrentInstantaneous(i);

      res.setTime(t);
      res.setVoltInstantaneous(vr*Math.sin(wt+res.getPhaseRadian()));
      res.setCurrentInstantaneous(i);

      cap.setTime(t);
      cap.setVoltInstantaneous(vc*Math.sin(wt+cap.getPhaseRadian()) );
      cap.setCurrentInstantaneous(i);

      ind.setTime(t);
      ind.setVoltInstantaneous(vl*Math.sin(wt+ind.getPhaseRadian()));
      ind.setCurrentInstantaneous(i);
  }
  final double zMag(){
      return Math.sqrt(resVal*resVal+reactance*reactance);
  }
  final double vcMag(double f){
      return volt*xc/Math.sqrt(resVal*resVal+reactance*reactance);
  }
  final double vlMag(double f){
      return volt*xl/Math.sqrt(resVal*resVal+reactance*reactance);
  }
  final double vrMag(double f){
      return volt*resVal/Math.sqrt(resVal*resVal+reactance*reactance);
  }
  final double vrPhase(double f){
      return Math.atan2(-reactance,resVal);
  }
  final double vcPhase(double f){
      return Math.atan2(-resVal,-reactance);
  }
  final double vlPhase(double f){
      return Math.atan2(resVal,reactance);
  }

  final void plotFunction(){
    if(showImpedance){
        plotZ();
    } else{
        plotV();
    }
  }

  final private double getVoltMax(){
      if(capVal==0 || indVal==0) return volt;
      double omega0= 1/Math.sqrt(capVal*indVal);
      double x=indVal*omega0; // inductive reactance at resonance
      if((omega0>=pi2*fmin) && (omega0<=pi2*fmax)) return Math.max(volt, volt*x/resVal);
      else return volt;
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
    public void step(double dt, double t){
      double wt=pi2*freqVal*t;  // omega*time
      double vc=vcMag(freqVal), vr=vrMag(freqVal), vl=vlMag(freqVal);
      double i=vr*Math.sin(wt+res.getPhaseRadian())/ resVal;

      bat.setTime(t);
      bat.setVoltInstantaneous(volt*Math.sin(wt));
      bat.setCurrentInstantaneous(i);

      res.setTime(t);
      res.setVoltInstantaneous(vr*Math.sin(wt+res.getPhaseRadian()));
      res.setCurrentInstantaneous(i);

      cap.setTime(t);
      cap.setVoltInstantaneous(vc*Math.sin(wt+cap.getPhaseRadian()) );
      cap.setCurrentInstantaneous(i);

      ind.setTime(t);
      ind.setVoltInstantaneous(vl*Math.sin(wt+ind.getPhaseRadian()));
      ind.setCurrentInstantaneous(i);

      this.updateDataConnections();
  }

  final void plotV(){
     graph.setAutoRefresh(false);
     int np=Math.max(150,graph.getBounds().width/3);
     if(graph.getBounds().width==0)np=200;
     double dt=tmax/(np-1);
     double[] x= new double[np];
     double[] y1= new double[np];
     double[] y2= new double[np];
     double[] y3= new double[np];
     double[] y4= new double[np];
     double vc=vcMag(freqVal), vr=vrMag(freqVal), vl=vlMag(freqVal);
     double pc=vcPhase(freqVal), pr=vrPhase(freqVal), pl=vlPhase(freqVal);
     double w=pi2*freqVal;
     for(int i=0; i<np; i++) {
            x[i] = i*dt;
            y1[i] = volt*Math.sin(w*x[i]);
            y2[i] = vc*Math.sin(w*x[i]+pc);
            y3[i] = vr*Math.sin(w*x[i]+pr);
            y4[i] = vl*Math.sin(w*x[i]+pl);
      }
      graph.setAutoReplaceData(1,true);
      graph.setAutoReplaceData(2,true);
      graph.setAutoReplaceData(3,true);
      graph.setAutoReplaceData(4,true);
      graph.clearSeriesData(1);
      graph.clearSeriesData(2);
      graph.clearSeriesData(3);
      graph.clearSeriesData(4);
      graph.addData(1,x,y1);
      graph.addData(2,x,y2);
      graph.addData(3,x,y3);
      graph.addData(4,x,y4);
      graph.setSeriesStyle(1,Color.black,true,0);
      graph.setSeriesStyle(2,Color.red,true,0);
      graph.setSeriesStyle(3,Color.blue,true,0);
      graph.setSeriesStyle(4,Color.green,true,0);
      graph.setSeriesLegend(1, Color.black,75,20,Common.LEGEND_VAC);
      graph.setSeriesLegend(2, Color.red,125,20,Common.LEGEND_VC);
      graph.setSeriesLegend(3, Color.blue,175,20,Common.LEGEND_VR);
      graph.setSeriesLegend(4, Color.green,225,20,Common.LEGEND_VL);
      graph.setAutoRefresh(autoRefresh);
  }
  void plotZ(){
     int np=200;
     double df=(fmax-fmin)/(np-1);
     double[] x= new double[np];
     double[] y= new double[np];
    // double temp1,temp2;
     double temp;
     double con1=pi2*capVal;
     double con2=pi2*indVal;
     for(int i=0; i<np; i++) {
            x[i] = fmin+i*df;
            //temp1=con2;
           // temp2=-1/(con1);
            temp=con2*x[i]-1/(con1*x[i]);
            y[i] = volt/Math.sqrt(resVal*resVal+temp*temp);
      }
      graph.clearSeriesData(1);
      graph.clearSeriesData(2);
      graph.clearSeriesData(3);
      graph.clearSeriesData(4);
      graph.addData(1,x,y);
      graph.setSeriesStyle(1,Color.red,true,0);
      graph.setSeriesLegend(1, Color.red,75,20,Common.CURRENT);
      xold=-100;
      yold=-100;
      if (circuit.getOsi()==null) return;  // plot not visible on screen yet.
      Graphics g=graph.getGraphics();
      g.setColor(Color.green);
      g.setXORMode(Color.red);
      double xx=fSlider.getDValue();
      xold=graph.pixFromX(xx);
      yold=graph.pixFromY(volt/zMag());
      g.fillOval(xold-5,yold-5,10,10);
      g.setPaintMode();
      g.dispose();
  }

  void adjustFreq(double f){
      freqVal=f;
      if(freqVal<fmin){
          freqVal=fmin;
          fSlider.setDValue(freqVal);
         // fNumber.setValue(freqVal);
      }
      if(freqVal>fmax){
          freqVal=fmax;
          fSlider.setDValue(freqVal);
        //  fNumber.setValue(freqVal);
      }
      xc=Math.abs(pi2*freqVal*capVal);
      if(xc!=0)xc=1/xc;
      xl=Math.abs(pi2*freqVal*indVal);
      reactance=xl-xc;
      this.clock.setTime(0);
      this.clearAllData();
      setCircuitValues();
      if(!showImpedance){
         plotV();
         return;
      }
      double i=volt/zMag();
      int xpix=graph.pixFromX(freqVal);
      int ypix=graph.pixFromY(i);
      Graphics g=graph.getGraphics();
      g.setColor(Color.green);
      g.setXORMode(Color.red);
      g.fillOval(xold-5,yold-5,10,10);
      g.fillOval(xpix-5,ypix-5,10,10);
      xold=xpix; yold=ypix;
      g.setPaintMode();
      g.dispose();
      repaint();
  }

  void fSlider_adjustmentValueChanged(AdjustmentEvent e) {
      adjustFreq(fSlider.getDValue());
  }

  void fNumber_actionPerformed(ActionEvent e) {
      if(fNumber.isValid()) adjustFreq(fNumber.getValue());
  }

/**
 * Get the ID for the resistor.
 * The ID can be used to make a connection to a data listener.
 *
 * @return int the indentifier
*/
  final public int getResistorID(){
      return res.getID();
  }

/**
 * Get the ID for the inductor.
 * The ID can be used to make a connection to a data listener.
 *
 * @return int the indentifier
*/
  final public int getInductorID(){
      return ind.getID();
  }
/**
 * Get the ID for the capacitor.
 * The ID can be used to make a connection to a data listener.
 *
 * @return int the indentifier
*/
  final public int getCapacitorID(){
      return cap.getID();
  }

/**
 * Get the ID for the AC voltage source.
 * The ID can be used to make a connection to a data listener.
 *
 * @return int the indentifier
*/
  final public int getSourceID(){
      return bat.getID();
  }

  public void setDefault(){
    oneShotMsg=null;
    this.deleteDataConnections();
    this.clock.stopClock();
    this.clock.setTime(0);
    //preferredPixPerCell=ppc;
    if(circuit!=null)circuit.setDefault(preferredPixPerCell);  // this removes the circuit
    createCircuit();
    setCircuitValues();
    graph.setTitle(null);
  }

  public void setInductance(double l, boolean showL, boolean showV, boolean showPhase){
    indVal=l;
    if(ind==null) return;
    ind.setL(indVal*1.0e3);  // convert to mH
    ind.showL=showL;
    ind.showV=showV;
    ind.showPhase=showPhase;
    adjustFreq(freqVal);
  }


  public void setCapacitance(double c, boolean showC, boolean showV, boolean showPhase){
    capVal=c;
    if(cap==null) return;
    cap.setC(capVal*1.0e6);  // convert to uF
    cap.showC=showC;
    cap.showV=showV;
    cap.showPhase=showPhase;
    adjustFreq(freqVal);
  }
  public void setResistance(double r, boolean showR, boolean showV, boolean showPhase){
    resVal=r;
    if(res==null) return;
    res.setR(resVal);
    res.showR=showR;
    res.showV=showV;
    res.showPhase=showPhase;
    adjustFreq(freqVal);
  }

  public boolean setCapacitorHint(String str){
      return circuit.setHint( cap.getID(), str);
  }

  public boolean setResistorHint(String str){
      return circuit.setHint( res.getID(), str);
  }

  public boolean setInductorHint(String str){
      return circuit.setHint( ind.getID(), str);
  }

  public boolean setACHint(String str){
      return circuit.setHint( bat.getID(), str);
  }

  public boolean setVoltmeterHint(String str){
      return circuit.setHint( meter.getID(), str);
  }

  public void setACVoltage(double v,boolean showV, boolean showPhase){
    volt=v;
    bat.setVoltRMS(volt/sqrt2);
    bat.showV=showV;
    bat.showPhase=showPhase;
    adjustFreq(freqVal);
  }
  public void setFrequency(double f){
      if(f<0) return;
      fSlider.setDValue(f);adjustFreq(f);
  }
  public void setShowControls(boolean sc){etchedBorder2.setVisible(sc);}
  public void setShowCheckBox(boolean scb){checkbox1.setVisible(scb);}
  public void setPixPerCell(int ppc){
      preferredPixPerCell=ppc;
      circuit.setPreferredPixPerCell(preferredPixPerCell);
  }

  public void setShowGraph(boolean sg){
      if(graph.isVisible()==sg) return;
      showGraph=sg;
      graph.setVisible(sg);
      invalidate();
      validate();
  }

  /**
   * Set the graph title.
   *
   * @param              str Title string.
   */
  public void setTitle(String str){graph.setTitle(str); }

  public void setAutoRefresh(boolean ar){
    if(autoRefresh==ar) return;
    autoRefresh=ar;
    graph.setAutoRefresh(autoRefresh);
    if(autoRefresh)setShowImpedance(showImpedance);
  }

  public void setImpedanceGraphType(boolean igt){
    if(showImpedance==igt) return;
    showImpedance=igt;
    if(autoRefresh) setShowImpedance(showImpedance);
  }
  void setShowImpedance(boolean si){
      //if(showImpedance==si) return;
      graph.setAutoRefresh(false);
      showImpedance=si;
      graph.deleteSeries(1);
      graph.deleteSeries(2);
      graph.deleteSeries(3);
      graph.deleteSeries(4);
      if(showImpedance){
          xold=-100;
          yold=-100; // make sure the first dot if off the screen.
          graph.setLabelY(Common.CURRENT_RMS);
          graph.setLabelX(Common.FREQUENCY);
          graph.setMinMaxX(fmin,fmax);
          graph.setMinMaxY(0,1.1*volt/resVal);
          graph.setFormat("%-+6.2g");
      }else{
          graph.setLabelY(Common.VOLTAGE);
          graph.setLabelX(Common.TIME);
          graph.setMinMaxX(0,tmax);
          graph.setMinMaxY(-1.1*getVoltMax(),1.2*getVoltMax());
          if(tmax<0.1) graph.setFormat("%-+6.2e");  else graph.setFormat("%-+6.2g");
      }
      plotFunction();
      graph.setAutoRefresh(autoRefresh);
      graph.repaint();
  }

  void checkbox1_itemStateChanged(ItemEvent e) {
       if(e.getStateChange()==ItemEvent.SELECTED){
           setShowImpedance(false);
       }else{
           setShowImpedance(true);
       }
  }

  class RCGraph extends SGraph{// inner class to paint the dragable oval.
      public void paintLast(Graphics g, Rectangle r) {
          super.paintLast(g,r);
          g.setColor(Color.green);
          g.setXORMode(Color.red);
          double xx=fSlider.getDValue();
          xold=graph.pixFromX(xx);
          yold=graph.pixFromY(volt/zMag());
          g.fillOval(xold-5,yold-5,10,10);
          g.setPaintMode();
      }
  }// end of LRCGraph
}

class LRCApplet_fSlider_adjustmentAdapter implements java.awt.event.AdjustmentListener {
  LRCApplet adaptee;

  LRCApplet_fSlider_adjustmentAdapter(LRCApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.fSlider_adjustmentValueChanged(e);
  }
}

class LRCApplet_fNumber_actionAdapter implements java.awt.event.ActionListener {
  LRCApplet adaptee;

  LRCApplet_fNumber_actionAdapter(LRCApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.fNumber_actionPerformed(e);
  }
}

class LRCApplet_checkbox1_itemAdapter implements java.awt.event.ItemListener {
  LRCApplet adaptee;

  LRCApplet_checkbox1_itemAdapter(LRCApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.checkbox1_itemStateChanged(e);
  }
}

