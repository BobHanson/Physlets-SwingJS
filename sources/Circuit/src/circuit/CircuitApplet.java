package circuit;

import java.awt.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;

final public class CircuitApplet extends SApplet {
  boolean showControls;
  int gridSize;
  int preferredPixPerCell=80;
  private Circuit circuit=new Circuit(this);
  Box circuitBox = new Box( circuit, Common.CIRCUIT);
  BorderLayout borderLayout1 = new BorderLayout();

  //Construct the applet
  public CircuitApplet() {
  }

  /**
   * Destroys all threads and cleanup the applet.
    * Exclude the javadoc because this method should not be scripted.
    * @y.exclude
     */
    public void destroy(){
       circuit.forceBubbleHelp(null);
       super.destroy();
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
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     */
  public void init() {
    initResources(null);
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { gridSize = Integer.parseInt(this.getParameter("GridSize", "3")); } catch (Exception e) { e.printStackTrace(); }
    try { preferredPixPerCell = Integer.parseInt(this.getParameter("PixPerCell", "30")); } catch (Exception e) { e.printStackTrace(); }
    try { jbInit(); } catch (Exception e) { e.printStackTrace(); }
    circuitBox.setHint(Common.LEGEND_SELECT);
    circuit.setPreferredPixPerCell(preferredPixPerCell);
    circuit.setGridSize(gridSize,gridSize);
    //circuit.addTransformer(1,1,2,2,true);
   // circuit.addOnOffSwitch(1,1,0,0);
   // circuit.addOnOffSwitch(0,0,1,0);
   // circuit.addResistor(0,0,1,1);
    //circuit.addAmmeter(0,0,1,1);
   // circuit.addResistor(1,0,0,0);
  }

  //Component initialization
  private void jbInit() throws Exception{
	  /** @j2sNative */{
		  this.setSize(new Dimension(293, 262));
	  }
    this.setLayout(borderLayout1);
    circuitBox.setTitle(Common.CIRCUIT);
    this.add(circuitBox, BorderLayout.CENTER);
  }

  //Get Applet information
  final public String getAppletInfo() {
    return "CircuitApplet by Wolfgang Christian, wochristian@davidson.edu";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"showControls", "boolean", "Show onscreen controls"},
      {"gridSize", "int", "Number of X-Y Nodes"},
      {"elementSize", "int", "Size in pix of one circuit element"},
    };
    return pinfo;
  }

  // methods added by WC

  public void setDefault(){
    oneShotMsg=null;
    circuit.setDefault();
    circuit.setPreferredPixPerCell(preferredPixPerCell);
    circuit.setGridSize(gridSize,gridSize);
  }

  public void setGridSize(int i, int j){
    circuit.setGridSize(i,j);
  }

  public void setTitle(String t){
      circuitBox.setTitle(t);
      repaint();
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

  public boolean setInductance(int id, double l, boolean showL, boolean showV, boolean showPhase){
      return circuit.setInductance( id, l,  showL,  showV,  showPhase);
  }


  public int addResistor(int i1, int j1, int i2, int j2){
    Resistor r=circuit.addResistor(i1,j1,i2,j2);
    return r.getID();
  }

  public int addPart(int i1, int j1, int i2, int j2){
    Part p=circuit.addPart(i1,j1,i2,j2);
    return p.getID();
  }

  public boolean setResistance(int id, double r, boolean showR, boolean showV, boolean showPhase){
      return circuit.setResistance( id, r,  showR,  showV,  showPhase);
  }

  public int addBattery(int i1, int j1, int i2, int j2){
    Battery b=circuit.addBattery(i1,j1,i2,j2);
    return b.getID();
  }
  public boolean setBatteryEMF(int id, double emf, boolean showV){
      return circuit.setBatteryEMF( id, emf,  showV);
  }

  public int addAmmeter(int i1, int j1, int i2, int j2){
    Ammeter am=circuit.addAmmeter(i1,j1,i2,j2);
    return am.getID();
  }
  public boolean setAmmeter(int id, double current, boolean showCurrent){
      return circuit.setAmmeter( id, current,  showCurrent);
  }

  public int addVoltmeter(int i1, int j1, int i2, int j2){
    Voltmeter vm=circuit.addVoltmeter(i1,j1,i2,j2);
    return vm.getID();
  }
  public boolean setVoltmeter(int id, double voltage, boolean showV){
      return circuit.setVoltmeter( id, voltage,  showV);
  }
  public boolean setVolt(int id, double v){
      circuit.setShowV(id, true);
      return circuit.setVolt( id, v);
  }

  public boolean setCurrent(int id, double c){
      circuit.setShowCurrent(id,true);
      return circuit.setCurrent( id, c);
  }

  public boolean setPhaseDegree(int id, double p){
      circuit.setShowPhase(id, true);
      return circuit.setPhaseDegree( id, p);
  }

  public boolean setLabel(int id, String str){
      return circuit.setLabel( id, str);
  }

  public boolean setHint(int id, String str){
      return circuit.setHint( id, str);
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

  public int addTransformer(int i1, int j1, int i2, int j2, boolean horz){
    Transformer t=circuit.addTransformer(i1,j1,i2,j2, horz);
    return t.getID();
  }

  public int addWire(int i1, int j1, int i2, int j2){
    Wire w=circuit.addWire(i1,j1,i2,j2);
    return w.getID();
  }

  public int addACSource(int i1, int j1, int i2, int j2){
    SineWave sw=circuit.addSineWave(i1,j1,i2,j2);
    return sw.getID();
  }

  public void setPixPerCell(int ppc){
      preferredPixPerCell=ppc;
      circuit.setPreferredPixPerCell(preferredPixPerCell);
  }

}

