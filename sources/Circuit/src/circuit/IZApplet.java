
/*
**************************************************************************
**
**                      Class  circuit.IZApplet
**
**************************************************************************
**
** class IZApplet extends SApplet
**
** The main entry point for the EField applet.
**
*************************************************************************/
package circuit;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SStepable;
import edu.davidson.graphics.*;
import edu.davidson.numerics.Parser;
import edu.davidson.display.*;

/**
 * IZApplet is part of the Davidson College Physlets project.
 * IZApplet displays a current as a function of a sinusoidal voltage, sin(2*PI*t).
 * The current response is spedified as a function of voltage and frequency, I(V,f).
 * The phase shift between I and V is specifiec by a function of frequency, phase(f).
 */
final public class IZApplet extends SApplet implements SStepable {
  static final double pi=Math.PI;
  static final double pi2=2*Math.PI;
  static final double sqrt2=Math.sqrt(2);
  private Parser ivFunc = new Parser(2);
  private Parser phaseFunc = new Parser(1);
  private String labelY=null;
  double fmin, fmax;
  double imax=10;
  double freqVal=60.0; // 60 Hz
  double tmax=0.1;
  boolean showControls=true, showCheckBox = true, showGraph=true;
  boolean showVoltage=true, showCurrent=true, customCircuit=false;
  boolean autoRefresh=true;  // refresh the graph whenever a variable changes.
  boolean defaultCircuit=true;
  boolean showIF=false;
  double volt=10;
  int preferredPixPerCell=60;
  boolean showAmmeterPhase=false;

  String currentStr="V";
  String phaseStr="0";
  EtchedBorder etchedBorder1 = new EtchedBorder();
  EtchedBorder etchedBorder2 = new EtchedBorder();
  SSlider fSlider = new SSlider();
  SNumber fNumber = new SNumber();
  private Circuit circuit=new Circuit(this);
  IZGraph graph = new IZGraph();
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
  public IZApplet() {
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
     setCircuitValues();
     calcCurrentIMax();
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
     setCircuitValues();
     calcCurrentIMax();
     return true;
  }

  private void firstTime(){
    graph.setBorders("0,10,10,5");
    graph.setMinMaxX(fmin,fmax);
    graph.setMinMaxY(-1,1);
    graph.setEnableMouse(true);
    graph.setBackground(Color.white);
    if(showIF){
      fSlider.setDMinMaxAndValue(fmin,fmax,freqVal);
      graph.setAutoscaleY(true);
      if(labelY==null)graph.setLabelY(Common.CURRENT);
      else graph.setLabelY(labelY);
      graph.setLabelX(Common.FREQUENCY);
      graph.setMinMaxX(fmin,fmax);
      //graph.setMinMaxY(0,volt);
    } else{
      volt=Math.abs(volt);
      fSlider.setDMinMaxAndValue(fmin,fmax,freqVal );
//     fNumber.setValue(freqVal);
      graph.setAutoscaleY(false);
      if(labelY==null)graph.setLabelY(Common.VOLTAGE_CURRENT);
      else graph.setLabelY(labelY);
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
    try { currentStr = this.getParameter("Current", "2*V"); } catch (Exception e) { e.printStackTrace(); }
    try { phaseStr = this.getParameter("Phase", "0"); } catch (Exception e) { e.printStackTrace(); }
    try { fmin = Double.valueOf(this.getParameter("Fmin", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { fmax = Double.valueOf(this.getParameter("Fmax", "500")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showGraph = Boolean.valueOf(this.getParameter("ShowGraph", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    if(showGraph)preferredPixPerCell=60; else preferredPixPerCell=200;
    try { showCheckBox = Boolean.valueOf(this.getParameter("ShowCheckBox", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { showIF = Boolean.valueOf(this.getParameter("ImpedanceGraphType", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { preferredPixPerCell = Integer.parseInt(this.getParameter("PixPerCell", ""+preferredPixPerCell)); } catch (Exception e) { e.printStackTrace(); }
    try { jbInit(); } catch (Exception e) { e.printStackTrace();  }
    clock.setDt(dt);
    clock.setFPS((int)fps);
    fSlider.setDMax(fmax);
    fSlider.setDMin(fmin);
    freqVal=(fmax+fmin)/2;
    fNumber.setValue(freqVal);
    fSlider.setDValue(freqVal);
    tmax=10.0/fmax;
    parseIVFunction();
    parsePhaseFunction();
    checkbox1.setState(!showIF);
    checkbox1.setVisible(showCheckBox);
    etchedBorder2.setVisible(showControls);
    fNumber.addPropertyChangeListener(fSlider);
    fSlider.addPropertyChangeListener(fNumber);
    if(fmax<100)fNumber.setFormat("%6.1f");
      else fNumber.setFormat("%6.0f");
    if(freqVal!=0)tmax=3.0/freqVal;
        else  tmax=1.0;
    clock.addClockListener(this);
  }

  //Component initialization
  private void jbInit() throws Exception{
    this.setBackground(Color.lightGray);
    /** @j2sNative */{
    	  this.setSize(new Dimension(740, 390));
    }
    fSlider.setDMax(10.0);
    fNumber.addActionListener(new IZApplet_fNumber_actionAdapter(this));
    fSlider.addAdjustmentListener(new IZApplet_fSlider_adjustmentAdapter(this));
    panel1.setBackground(Color.lightGray);
    graph.setLabelY(Common.CURRENT);
    label1.setAlignment(2);
    label1.setText(Common.FREQUENCY);
    checkbox1.setLabel(Common.LEGEND_VTIME);
    checkbox1.addItemListener(new IZApplet_checkbox1_itemAdapter(this));
    graph.setSampleData(false);
    graph.setAutoscaleX(false);
    graph.setAutoscaleY(false);
    graph.setLabelX(Common.FREQUENCY);
    etchedBorder2.setLayout(borderLayout1);
    etchedBorder1.setLayout(borderLayout3);
    this.setLayout(borderLayout2);
    this.add(etchedBorder1, BorderLayout.CENTER);
    etchedBorder1.add(circuit, BorderLayout.WEST);
 //   etchedBorder1.add(circuitBox, BorderLayout.WEST);
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
      if(debugLevel>127) System.out.println("begin IZApplet destroy");
       circuit.forceBubbleHelp(null);
       circuit.destroyHint();
       graph.destroy();
       super.destroy();
       if(debugLevel>127) System.out.println("end IZApplet destroy");
    }

    /**
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     */
    public void start() {
      if(debugLevel>127) System.out.println("begin IZApplet start");
      if(firstTime){
        firstTime=false;
        firstTime();
      }else graph.repaint();
      super.start();
      if(debugLevel>127) System.out.println("end IZApplet start");
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
       part.setTime(0);
       clearAllData();
   }

  //Get Applet information
  final public String getAppletInfo() {
    return "IZApplet by Wolfgang Christian, wochristian@davidson.edu";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"Current", "String", "Current: I(V,f)"},
      {"Phase", "String", "Phase: P(f)"},
      {"Vmin", "double", "Minimum frequency"},
      {"VMax", "double", "Maximum frequency."},
      {"ShowControls", "boolean", "Show the slider."},
      {"ShowCheckBox", "boolean", "Show the check box."},
      {"ImpedanceGraphType", "boolean", "Plot I(f) graph instead of I(t) and V(t)."},
    };
    return pinfo;
  }

  /**
 * Get the ID for the unknown part.
 * The ID can be used to make a connection to a SDataSource.
 *
 * @return int the indentifier
*/
  final public int getUnknownID(){
      return part.getID();
  }

  // private methods
  private void createCustomCircuit(){
      circuit.setPreferredPixPerCell(preferredPixPerCell);
  }
  private void createCircuit(){
    if(customCircuit){
        createCustomCircuit();
        return;
    }
    circuit.setPreferredPixPerCell(preferredPixPerCell);
    circuit.setGridSize(3,2);
    bat=circuit.addSineWave(0,0,0,1);
    bat.setVoltRMS(volt);
    bat.showPhase=false;
    bat.showF=true;
    bat.showV=false;
    part=circuit.addPart(1,0,1,1);
    part.setLabel("  ?");
    part.showR=false;
    part.showV=false;
    part.showZ=false;
    part.showPhase=false;
    circuit.addWire(0,0,1,0);
    ammeter=circuit.addAmmeter(0,1,1,1);
    if(showAmmeterPhase)ammeter.showPhase=true;
    circuit.addWire(1,0,2,0);
    circuit.addWire(1,1,2,1);
    voltmeter=circuit.addVoltmeter(2,0,2,1);
    if(freqVal==0){
        bat.setLabel(" V");
        ammeter.setLabel("A");
        voltmeter.setLabel("V");
        bat.showF=false;
    }else{
        bat.setLabel("Vac");
        ammeter.setLabel("A");
        voltmeter.setLabel("V");
        bat.showF=true;
    }
  }

  private void setCircuitValues(){
      if(customCircuit){return;}
      if(bat==null)return;
      bat.setVoltRMS(volt);
      bat.setF(freqVal);
      voltmeter.setVoltRMS(volt);
      ammeter.setCurrentRMS(calcCurrentRMS());
      part.setVoltRMS(volt);
      // part is a data source so we need to set all the values
      double w=pi2*freqVal;
      double t=this.clock.getTime();
      double rad=phaseFunc.evaluate(freqVal);
      double v = volt*sqrt2*Math.sin(w*t-rad);
      double i = ivFunc.evaluate(volt*Math.sin(w*t),freqVal );  // iv function is amplitude
      part.setVoltInstantaneous(v);
      part.setCurrentInstantaneous(i);
      part.setPhaseRadian(rad);
      part.setF(freqVal);
      if(showAmmeterPhase)ammeter.setPhaseRadian(phaseFunc.evaluate(freqVal));
  }

  final void calcCurrentIMax(){
     int np=200;
     double df=(fmax-fmin)/(np-1);
     double f=fmin;
     imax=ivFunc.evaluate(volt,fmin);
     for(int i=0; i<np; i++) {
            f += df;
            imax=Math.max(imax,ivFunc.evaluate(volt,f));
      }
  }

  final double calcCurrentRMS(){
   // Find the RMS curent by integrating over one cycle.
     if(freqVal==0) return ivFunc.evaluate(volt,freqVal);
     int np=32;
     double dt=1.0/(np-1.0);
     double t= 0;
     double v= 0; // volt*Math.sin(pi2*t) is zero for first term.
     double c=ivFunc.evaluate(v,freqVal); // temporary current
     double sum=c;                        // since the first and the last points are 0 and 2*pi we can combine terms in Simpson's rule.
     for(int i=0; i<np-2; i++) {
            t +=dt; // increment t
            v = volt*Math.sin(pi2*t);
            c=ivFunc.evaluate(v,freqVal);
            sum += c*c;
      }
      sum =Math.sqrt(sum/(np-1));
      //return ivFunc.evaluate(volt,freqVal);
      return sum;
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void step(double dt, double t){
      if(part==null) return;
      double w=pi2*freqVal;
      double rad=part.getPhaseRadian();
      double v = sqrt2*volt*Math.sin(w*t-rad);
      double i = ivFunc.evaluate( sqrt2*volt*Math.sin(w*t),freqVal );  // iv function is amplitude
      part.setTime(t);
      part.setVoltInstantaneous(v);
      part.setCurrentInstantaneous(i);
      this.updateDataConnections();
  }

  final void plotFunction(){
    if(showIF){
        plotIF();
    } else{
        plotIT();
    }
  }
  final void plotIT(){
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
      if(showVoltage){
          graph.addData(1,x,y1);
          graph.setSeriesStyle(1,Color.black,true,0);
          if(labelY==null)graph.setSeriesLegend(1, Color.black,75,20,Common.LEGEND_V);
      }
      if(showCurrent){
          graph.addData(2,x,y2);
          graph.setSeriesStyle(2,Color.red,true,0);
          if(labelY==null)graph.setSeriesLegend(2, Color.red,125,20,Common.LEGEND_I);
      }
  }
  double findYMax(){
     double ymax=Math.abs(imax);
     return Math.max(ymax*sqrt2,volt*sqrt2);  // convert from RMS
  }
  double findYMin(){
     double ymin=-Math.abs(imax);
     return Math.min(ymin*sqrt2, -volt*sqrt2);  //convert from rms
  }

  void plotIF(){
     int np=200;
     double df=(fmax-fmin)/(np-1);
     double[] x= new double[np];
     double[] y= new double[np];
     for(int i=0; i<np; i++) {
            x[i] = fmin+i*df;
            y[i] = ivFunc.evaluate(volt,x[i]);
      }
      graph.clearSeriesData(1);
      graph.clearSeriesData(2);
      graph.addData(1,x,y);
      graph.setSeriesStyle(1,Color.red,true,0);
     // graph.setSeriesLegend(1, Color.red,75,20," I(V) = "+currentStr);
      xold=-100;
      yold=-100;
  }

  void adjustFreq(double f){
      freqVal=f;
      if(freqVal<fmin){
          freqVal=fmin;
          fSlider.setDValue(freqVal);
  //        fNumber.setValue(freqVal);
      }
      if(freqVal>fmax){
          freqVal=fmax;
          fSlider.setDValue(freqVal);
 //         fNumber.setValue(freqVal);
      }
      this.clock.setTime(0);
      this.clearAllData();
      setCircuitValues();
      if(!showIF){
         plotIT();
         return;
      }
      int xpix=graph.pixFromX(freqVal);
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

  void fSlider_adjustmentValueChanged(AdjustmentEvent e) {
      adjustFreq(fSlider.getDValue());
  }

  void fNumber_actionPerformed(ActionEvent e) {
      if(fNumber.isValid()) adjustFreq(fNumber.getValue());
  }
  public void setSourceVoltage(double v,boolean showV){
    volt=v;
    bat.showV=showV;
    adjustFreq(freqVal);
  }

  public void setCustomCircuit(int ppc){
      autoRefresh=false;
      customCircuit=true;
      this.setPixPerCell(ppc);
      setDefault();
      return;
  }

  public void setDefault(){
      oneShotMsg=null;
    this.deleteDataConnections();
    this.clock.stopClock();
    this.clock.setTime(0);
    labelY=null;
    showCurrent=true;
    showVoltage=true;
   // freqVal=60.0; // 60 Hz
   // tmax=3.0/freqVal;
   // preferredPixPerCell=ppc;
    phaseStr="0";
    parsePhaseFunction();
    currentStr="0";
    parseIVFunction();
    circuit.setDefault(preferredPixPerCell);  // this removes the circuit
    createCircuit();
    setCircuitValues();
    graph.setTitle(null);
    if(graph.isVisible()!=showGraph)graph.setVisible(showGraph);
    if(autoRefresh) setShowIF(showIF);  // this also plots the functions.
  }
  public void setCurrentFunction(String str){
     currentStr=str;
     parseIVFunction();
     if(autoRefresh) setShowIF(showIF);
  }

  public void setPhaseFunction(String str){
     phaseStr=str;
     parsePhaseFunction();
     if(autoRefresh) setShowIF(showIF);
  }

  public void setVoltage(double v){ volt=v;calcCurrentIMax(); adjustFreq(freqVal);}

  public void setFrequency(double f){
      freqVal=f;
      fSlider.setDValue(f);
      if(customCircuit || ammeter==null){
          adjustFreq(f);
          if(autoRefresh) setShowIF(showIF);
          return;
      }
      if(freqVal==0){
          bat.setLabel(" V");
          ammeter.setLabel("A");
          voltmeter.setLabel("V");
          bat.showF=false;
      }else{
          bat.setLabel("Vac");
          ammeter.setLabel("A");
          voltmeter.setLabel("V");
          bat.showF=true;
      }
      adjustFreq(f);
      if(autoRefresh) setShowIF(showIF);
  }

  /**
   * Set the graph title.
   *
   * @param              str Title string.
   */
  public void setTitle(String str){graph.setTitle(str); }

  public void setTMax(double tm){tmax=tm; if(autoRefresh) setShowIF(showIF);}
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
  public void setPartHint(String str){part.setCustomHint(str);}
  public void setShowAmmeterPhase(boolean sp){
      showAmmeterPhase=sp;
      if(ammeter!=null)ammeter.showPhase=sp;
  }

  public void setPixPerCell(int ppc){
      if(preferredPixPerCell==ppc) return;
      preferredPixPerCell=ppc;
      circuit.setPreferredPixPerCell(preferredPixPerCell);
  }

  public void setShowCurrent(boolean sc){showCurrent=sc; plotFunction();}
  public void setShowVoltage(boolean sv){showVoltage=sv; plotFunction();}

  public void setImpedanceGraphType(boolean igt){
     showIF=igt;
     if(autoRefresh) setShowIF(showIF);
  }

  void setShowIF(boolean si){
      // this does a complete refresh of the graph;
      showIF=si;
      graph.setAutoRefresh(false);
      graph.deleteSeries(1);
      graph.deleteSeries(2);
      if(showIF){
          fSlider.setDMinMaxAndValue(fmin,fmax,freqVal);
          graph.setAutoscaleY(true);
          xold=-100;
          yold=-100; // make sure the first dot if off the screen.
          if(labelY==null)graph.setLabelY(Common.CURRENT);
              else graph.setLabelY(labelY);
          graph.setLabelX(Common.FREQUENCY);
          graph.setMinMaxX(fmin,fmax);
          graph.setMinMaxY(0,volt);    // autoscale is true so this is a place holder
      }else{
          volt=Math.abs(volt);
          fSlider.setDMinMaxAndValue(fmin,fmax,freqVal);
  //        fNumber.setValue(freqVal);
          graph.setAutoscaleY(false);
          if(labelY==null)graph.setLabelY(Common.VOLTAGE_CURRENT);
              else graph.setLabelY(labelY);
          graph.setLabelX(Common.TIME);
          graph.setMinMaxX(0,tmax);
          graph.setMinMaxY(Math.min(1.1*findYMin(),0.9*findYMin()),Math.max(1.2*findYMax(),0.8*findYMax()) );
      }
      setCircuitValues();
      plotFunction();
      graph.setAutoRefresh(autoRefresh);
      graph.repaint();
  }

  void checkbox1_itemStateChanged(ItemEvent e) {
       if(e.getStateChange()==ItemEvent.SELECTED){
           setShowIF(false);
       }else{
           setShowIF(true);
       }
  }
  // Add methods for custom circuit


  public void setGridSize(int i, int j){
    circuit.setGridSize(i,j);
  }
  public int addCapacitor(int i1, int j1, int i2, int j2){
    Capacitor c=circuit.addCapacitor(i1,j1,i2,j2);
    return c.getID();
  }
  public boolean setCapacitance(int id, double c, boolean showC, boolean showV, boolean showPhase){
      return circuit.setCapacitance( id, c,  showC,  showV,  showPhase);
  }

  public int addInductor(int i1, int j1, int i2, int j2){
    Inductor l=circuit.addInductor(i1,j1,i2,j2);
    return l.getID();
  }

  public boolean setInductance(int id, double l, boolean showC, boolean showV, boolean showPhase){
      return circuit.setInductance( id, l,  showC,  showV,  showPhase);
  }
  public int addResistor(int i1, int j1, int i2, int j2){
    Resistor r=circuit.addResistor(i1,j1,i2,j2);
    return r.getID();
  }
  public boolean setResistance(int id, double r, boolean showR, boolean showV, boolean showPhase){
      return circuit.setResistance( id, r,  showR,  showV,  showPhase);
  }

  public int addPart(int i1, int j1, int i2, int j2){
    Part p=circuit.addPart(i1,j1,i2,j2);
    return p.getID();
  }

  public int addBattery(int i1, int j1, int i2, int j2){
    Battery b=circuit.addBattery(i1,j1,i2,j2);
    return b.getID();
  }
  public boolean setBatteryEMF(int id, double emf, boolean showV){
      return circuit.setBatteryEMF( id, emf,  showV);
  }

  public int addTransformer(int i1, int j1, int i2, int j2, boolean vert){
    Transformer t=circuit.addTransformer(i1,j1,i2,j2, vert);
    return t.getID();
  }

  public int addAmmeter(int i1, int j1, int i2, int j2){
    Ammeter am=circuit.addAmmeter(i1,j1,i2,j2);
    return am.getID();
  }

  public void setAutoRefresh(boolean ar){
    if(autoRefresh==ar) return;
    autoRefresh=ar;
    graph.setAutoRefresh(ar);
    if(autoRefresh)setShowIF(showIF);
  }

  public boolean setAmmeter(int id, double current, boolean showCurrent){
      return circuit.setAmmeter( id, current,  showCurrent);
  }

  public int addVoltmeter(int i1, int j1, int i2, int j2){
    Voltmeter vm=circuit.addVoltmeter(i1,j1,i2,j2);
    return vm.getID();
  }

  public int addACSource(int i1, int j1, int i2, int j2){
    SineWave sw=circuit.addSineWave(i1,j1,i2,j2);
    return sw.getID();
  }

  public boolean setVoltmeter(int id, double voltage, boolean showV){
      circuit.setShowV(id, true);
      return circuit.setVoltmeter( id, voltage,  showV);
  }
  public boolean setVolt(int id, double v){
      circuit.setShowV(id, true);
      return circuit.setVolt( id, v);
  }

  public boolean setPhaseDegree(int id, double p){
      circuit.setShowPhase(id, true);
      return circuit.setPhaseDegree( id, p);
  }

  public boolean setCurrent(int id, double c){
      circuit.setShowCurrent(id, true);
      return circuit.setCurrent( id, c);
  }

  public boolean setLabel(int id, String str){
      return circuit.setLabel( id, str);
  }

  public boolean setHint(int id, String str){
      return circuit.setHint( id, str);
  }

  public void setGraphLabelY(String str){
      if(str.equals(labelY)) return;
      if( str.trim().equals("") && (labelY==null)) return;
      if(str.trim().equals(""))labelY=null;
         else labelY=str;
      if(autoRefresh) setShowIF(showIF);
  }

  public boolean setMilliAmp(int id, boolean showMilliAmp){
      return circuit.setMilliAmp( id, showMilliAmp);
  }

  public int addOnOffSwitch(int i1, int j1, int i2, int j2){
    OnOffSwitch s=circuit.addOnOffSwitch(i1,j1,i2,j2);
    return s.getID();
  }

  public boolean setSwitchOn(int id, boolean on){
      return circuit.setSwitchOn( id, on);
  }

  public int addWire(int i1, int j1, int i2, int j2){
    Wire w=circuit.addWire(i1,j1,i2,j2);
    return w.getID();
  }


  // inner class
  class IZGraph extends SGraph{// inner class to paint the dragable oval.
      public void paintLast(Graphics g, Rectangle r) {
          super.paintLast(g,r);
          if(!showIF) return;
          g.clipRect(r.x,r.y,r.width,r.height);
          g.setColor(Color.green);
          g.setXORMode(Color.red);
          double xx=fSlider.getDValue();
          xold=graph.pixFromX(xx);
          yold=graph.pixFromY(ivFunc.evaluate(volt,xx));
          g.fillOval(xold-5,yold-5,10,10);
          g.setPaintMode();
      }
  }// end of inner class
}

class IZApplet_fSlider_adjustmentAdapter implements java.awt.event.AdjustmentListener {
  IZApplet adaptee;

  IZApplet_fSlider_adjustmentAdapter(IZApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.fSlider_adjustmentValueChanged(e);
  }
}

class IZApplet_fNumber_actionAdapter implements java.awt.event.ActionListener {
  IZApplet adaptee;

  IZApplet_fNumber_actionAdapter(IZApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.fNumber_actionPerformed(e);
  }
}

class IZApplet_checkbox1_itemAdapter implements java.awt.event.ItemListener {
  IZApplet adaptee;

  IZApplet_checkbox1_itemAdapter(IZApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.checkbox1_itemStateChanged(e);
  }
}

