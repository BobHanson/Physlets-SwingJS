package circuit;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

import edu.davidson.graphics.*;
import edu.davidson.display.*;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SStepable;


final public class RCApplet extends SApplet implements SStepable {
  static final double pi=Math.PI;
  static final double pi2=2*Math.PI;
  static final double sqrt2=Math.sqrt(2);
  double resVal;
  double capVal;
  double xc=0;
  double fmin;
  double fmax;
  double tmax=0.1;
  double freqVal;
  boolean showControls=true, showCheckBox = true, showGraph=true;
  boolean defaultCircuit=true;
  boolean showImpedance=false;
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
  private double volt=10;
  Panel panel1 = new Panel();
  Label label1 = new Label();
  Checkbox checkbox1 = new Checkbox();


  //Construct the applet
  public RCApplet() {
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
    try { resVal = Double.valueOf(this.getParameter("Resistor", "50")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { capVal = Double.valueOf(this.getParameter("Capacitor", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { fmin = Double.valueOf(this.getParameter("Fmin", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { fmax = Double.valueOf(this.getParameter("FMax", "1000")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
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
    volt*=sqrt2;
    capVal*=1.0e-6;
    graph.deleteAllSeries();
    graph.setBorders("0,10,10,5");
    graph.setMinMaxX(fmin,fmax);
    graph.setMinMaxY(-1,1);
    graph.setEnableMouse(true);
    graph.setBackground(Color.white);
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
    if(showImpedance){
        graph.setLabelY(Common.CURRENT_RMS_A);
        graph.setLabelX(Common.FREQUENCY_HZ);
        graph.setMinMaxX(fmin,fmax);
        graph.setMinMaxY(0,volt/resVal);
    } else{
        graph.setLabelY(Common.VOLTAGE_V);
        graph.setLabelX(Common.TIME_SEC);
        graph.setMinMaxX(0,tmax );
        graph.setMinMaxY(-1.1*volt,1.2*volt);
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
    clock.addClockListener(this);
  }

  //Component initialization
  private void jbInit() throws Exception{
    this.setBackground(Color.lightGray);
    this.setSize(new Dimension(740, 390));
    fSlider.setDMax(200.0);
    fNumber.addActionListener(new RCApplet_fNumber_actionAdapter(this));
    fSlider.addAdjustmentListener(new RCApplet_fSlider_adjustmentAdapter(this));
    circuitBox.setTitle(Common.CIRCUIT);
    panel1.setBackground(Color.lightGray);
    graph.setLabelY(Common.CURRENT_RMS_A);
    label1.setAlignment(2);
    label1.setText(Common.FREQUENCY);
    checkbox1.setLabel(Common.VOLTAGE);
    checkbox1.addItemListener(new RCApplet_checkbox1_itemAdapter(this));
    graph.setLabelX(Common.FREQUENCY_HZ);
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
       cap.setTime(0);
       clearAllData();
   }

   /**
    * Gets the applet information.
    * @return the information
    */
  final public String getAppletInfo() {
    return "RCApplet by Wolfgang Christian, wochristian@davidson.edu";
  }

  /**
   * Gets the parameter information.
   * @return the information
   */
  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"Resistor", "double", "Resistance in Ohm."},
      {"Capacitor", "double", "Capacitor value in micro-H."},
      {"Fmin", "double", "Minimum frequency"},
      {"FMax", "double", "Maximum frequency."},
      {"ShowControls", "boolean", "Show the slider."},
      {"ShowCheckBox", "boolean", "Show the check box."},
      {"ImpedanceGraphType", "boolean", "Plot current, I(f), rather than time series, V(t)."},
    };
    return pinfo;
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

  private void createCircuit(){
    xc=Math.abs(pi2*freqVal*capVal);
    if(xc!=0)xc=1/xc;
    circuit.setPreferredPixPerCell(preferredPixPerCell);
    circuit.setGridSize(4,2);
    bat=circuit.addSineWave(0,0,0,1);
    bat.setVoltRMS(volt/sqrt2);
    bat.showPhase=false;
    res=circuit.addResistor(0,1,1,1);
    res.setLabel(Common.LEGEND_R);
    res.setR(resVal);
    res.showR=true;
    res.showV=true;
    res.showPhase=true;
    circuit.addWire(1,1,2,1);
    circuit.addWire(0,0,2,0);
    cap=circuit.addCapacitor(2,1,2,0);
    cap.setLabel(Common.LEGEND_C);
    cap.setC(capVal*1.0e6);  // convert to uF
    cap.showC=true;
    cap.showV=true;
    cap.showPhase=true;
    circuit.addWire(2,0,3,0);
    circuit.addWire(2,1,3,1);
    meter=circuit.addVoltmeter(3,0,3,1);
  }

  private void setCircuitValues(){
      double irms=volt/zMag(freqVal)/sqrt2;
      bat.setF(freqVal);
      bat.setCurrentRMS(irms);
      res.setVoltRMS(vrMag(freqVal)/sqrt2);
      res.setCurrentRMS(irms);
      res.setPhaseRadian(vrPhase(freqVal));
      meter.setVoltRMS(vrMag(freqVal)/sqrt2);
      cap.setVoltRMS(vcMag(freqVal)/sqrt2);
      cap.setCurrentRMS(irms);
      cap.setPhaseRadian(vcPhase(freqVal));
      double t=clock.getTime();
      double vc=vcMag(freqVal), vr=vrMag(freqVal);
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
  }
  final double zMag(double f){
      return Math.sqrt(resVal*resVal+xc*xc);
  }
  final double vcMag(double f){
      return volt*xc/Math.sqrt(resVal*resVal+xc*xc);
  }
  final double vrMag(double f){
      return volt*resVal/Math.sqrt(resVal*resVal+xc*xc);
  }
  final double vrPhase(double f){
      return Math.atan2(xc,resVal);
  }
  final double vcPhase(double f){
      return Math.atan2(-resVal,xc);
  }
  final void plotFunction(){
    if(showImpedance){
        plotZ();
    } else{
        plotV();
    }
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
 public void step(double dt, double t){
      double wt=pi2*freqVal*t;  // omega*time
      double vc=vcMag(freqVal), vr=vrMag(freqVal);
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

      this.updateDataConnections();
  }

  final void plotV(){
     int np=Math.max(150,graph.getBounds().width/3);
     if(graph.getBounds().width==0)np=200;
     graph.setAutoRefresh(false);
     double dt=tmax/(np-1);
     double[] x= new double[np];
     double[] y1= new double[np];
     double[] y2= new double[np];
     double[] y3= new double[np];
     double vc=vcMag(freqVal), vr=vrMag(freqVal);
     double pc=vcPhase(freqVal), pr=vrPhase(freqVal);
     double w=pi2*freqVal;
     for(int i=0; i<np; i++) {
            x[i] = i*dt;
            y1[i] = volt*Math.sin(w*x[i]);
            y2[i] = vc*Math.sin(w*x[i]+pc);
            y3[i] = vr*Math.sin(w*x[i]+pr);
      }
      graph.setAutoReplaceData(1,true);
      graph.setAutoReplaceData(2,true);
      graph.setAutoReplaceData(3,true);
      graph.clearSeriesData(1);
      graph.clearSeriesData(2);
      graph.clearSeriesData(3);
      graph.addData(1,x,y1);
      graph.addData(2,x,y2);
      graph.addData(3,x,y3);
      graph.setSeriesStyle(1,Color.black,true,0);
      graph.setSeriesStyle(2,Color.red,true,0);
      graph.setSeriesStyle(3,Color.blue,true,0);
      graph.setSeriesLegend(1, Color.black,75,20,"Vac");
      graph.setSeriesLegend(2, Color.red,125,20,"Vc");
      graph.setSeriesLegend(3, Color.blue,175,20,"Vr");
      graph.setAutoRefresh(autoRefresh);
  }
  void plotZ(){
     int np=200;
     double df=(fmax-fmin)/(np-1);
     double[] x= new double[np];
     double[] y= new double[np];
     double temp;
     double con=pi2*capVal;
     for(int i=0; i<np; i++) {
            x[i] = fmin+i*df;
            temp=1/(con*x[i]);
            y[i] = volt/Math.sqrt(resVal*resVal+temp*temp);
      }
      graph.clearSeriesData(1);
      graph.clearSeriesData(2);
      graph.clearSeriesData(3);
      graph.addData(1,x,y);
      graph.setSeriesStyle(1,Color.red,true,0);
      graph.setSeriesLegend(1, Color.red,75,20,"Current");
      xold=-100;
      yold=-100;
      if (circuit.getOsi()==null) return;  // plot not visible on screen yet.
      Graphics g=graph.getGraphics();
      g.setColor(Color.green);
      g.setXORMode(Color.red);
      double xx=fSlider.getDValue();
      xold=graph.pixFromX(xx);
      yold=graph.pixFromY(volt/zMag(xx));
      g.fillOval(xold-5,yold-5,10,10);
      g.setPaintMode();
      g.dispose();
  }

  void adjustFreq(double f){
      freqVal=f;
      if(freqVal<fmin){
          freqVal=fmin;
          fSlider.setDValue(freqVal);
 //         fNumber.setValue(freqVal);
      }
      if(freqVal>fmax){
          freqVal=fmax;
          fSlider.setDValue(freqVal);
 //         fNumber.setValue(freqVal);
      }
      xc=Math.abs(pi2*freqVal*capVal);
      if(xc!=0)xc=1/xc;
      this.clock.setTime(0);
      this.clearAllData();
      setCircuitValues();
      if(!showImpedance){
         plotV();
         return;
      }
      double i=volt/zMag(freqVal);
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
  }

  void fSlider_adjustmentValueChanged(AdjustmentEvent e) {
      adjustFreq(fSlider.getDValue());
  }

  void fNumber_actionPerformed(ActionEvent e) {
      if(fNumber.isValid()) adjustFreq(fNumber.getValue());
  }


  public void setPixPerCell(int ppc){
      if(preferredPixPerCell==ppc) return;
      preferredPixPerCell=ppc;
      circuit.setPreferredPixPerCell(preferredPixPerCell);
  }

  /**
   * Set the graph title.
   *
   * @param              str Title string.
   */
  public void setTitle(String str){graph.setTitle(str); }


  public void setDefault(){
	//System.out.println("setting default");
    oneShotMsg=null;
    this.deleteDataConnections();
    this.clock.stopClock();
    this.clock.setTime(0);
   // preferredPixPerCell=ppc;
    //System.out.println("clock set");
    circuit.setDefault(preferredPixPerCell);  // this removes the circuit
    //System.out.println("circuit removed");
    createCircuit();
    //System.out.println("circuit created");
    setCircuitValues();
    //System.out.println("values set");
    graph.setTitle(null);
    //System.out.println("done");
  }

  public void setCapacitance(double c, boolean showC, boolean showV, boolean showPhase){
    capVal=c;
    cap.setC(capVal*1.0e6);  // convert to uF
    cap.showC=showC;
    cap.showV=showV;
    cap.showPhase=showPhase;
    adjustFreq(freqVal);
  }
  public void setResistance(double r, boolean showR, boolean showV, boolean showPhase){
    resVal=r;
    res.setR(resVal);
    res.showR=showR;
    res.showV=showV;
    res.showPhase=showPhase;
    adjustFreq(freqVal);
  }
  public void setSourceVoltage(double v,boolean showV, boolean showPhase){
    volt=v;
    bat.setVoltRMS(volt/sqrt2);
    bat.showV=showV;
    bat.showPhase=showPhase;
    adjustFreq(freqVal);
  }

    public boolean setCapacitorHint(String str){
      return circuit.setHint( cap.getID(), str);
  }

  public boolean setResistorHint(String str){
      return circuit.setHint( res.getID(), str);
  }

  public boolean setACHint(String str){
      return circuit.setHint( bat.getID(), str);
  }

  public boolean setVoltmeterHint(String str){
      return circuit.setHint( meter.getID(), str);
  }

  public void setFrequency(double f){fSlider.setDValue(f); adjustFreq(f);}
  public void setShowControls(boolean sc){etchedBorder2.setVisible(sc);}
  public void setShowCheckBox(boolean scb){checkbox1.setVisible(scb);}

  public void setShowGraph(boolean sg){
      if(graph.isVisible()==sg) return;
      showGraph=sg;
      graph.setVisible(sg);
      invalidate();
      validate();
  }


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
      if(showImpedance){
          xold=-100;
          yold=-100; // make sure the first dot if off the screen.
          graph.setLabelY(Common.CURRENT_A);
          graph.setLabelX(Common.FREQUENCY_HZ);
          graph.setMinMaxX(fmin,fmax);
          graph.setMinMaxY(0,volt/resVal);
      }else{
          graph.setLabelY(Common.VOLTAGE_V);
          graph.setLabelX(Common.TIME_SEC);
          graph.setMinMaxX(0,tmax);
          graph.setMinMaxY(-1.1*volt,1.2*volt);
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
          yold=graph.pixFromY(volt/zMag(xx));
          g.fillOval(xold-5,yold-5,10,10);
          g.setPaintMode();
      }
  }// end of RCGraph
}

class RCApplet_fSlider_adjustmentAdapter implements java.awt.event.AdjustmentListener {
  RCApplet adaptee;

  RCApplet_fSlider_adjustmentAdapter(RCApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.fSlider_adjustmentValueChanged(e);
  }
}

class RCApplet_fNumber_actionAdapter implements java.awt.event.ActionListener {
  RCApplet adaptee;

  RCApplet_fNumber_actionAdapter(RCApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.fNumber_actionPerformed(e);
  }
}

class RCApplet_checkbox1_itemAdapter implements java.awt.event.ItemListener {
  RCApplet adaptee;

  RCApplet_checkbox1_itemAdapter(RCApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.checkbox1_itemStateChanged(e);
  }
}

