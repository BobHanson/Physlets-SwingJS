package ising;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;


public class Ising extends SApplet implements PropertyChangeListener{
    boolean isStandalone = false;
    boolean showControls;
    int arraySize=32;
    double temperature=2.629;
    double bfield=0;
    BorderLayout borderLayout1 = new BorderLayout();
    Panel controlPanel = new Panel();
    BorderLayout borderLayout2 = new BorderLayout();
    Panel panel2 = new Panel();
    Button startBtn = new Button();
    Panel panel3 = new Panel();
    Panel panel4 = new Panel();
    SSlider tempSlider = new SSlider();
    BorderLayout borderLayout4 = new BorderLayout();
    VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
    Panel panel5 = new Panel();
    SNumber tempField = new SNumber();
    IsingPanel isingPanel = new IsingPanel(this);
    Label label1 = new Label();
    Button resetBtn = new Button();


    //Get a parameter value
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public Ising() {
    }

    /**
*
* Called whenever the tempSlider or tempNumber is changed by the user.  It then
* updates the temperature value in the model.
*
*/

  public void propertyChange(PropertyChangeEvent evt)  {
    if(evt.getSource()==tempSlider)temperature=tempSlider.getDValue();
    else if(evt.getSource()==tempField)temperature=tempField.getValue();
    isingPanel.model.resetT(temperature);
    //System.out.println("changed="+temperature);
  }

    //Initialize the applet
    public void init() {
        double fps=10.0;
        double dt=0.1;
        int flips=32*32;
        try { fps = Integer.parseInt(this.getParameter("FPS", "10")); } catch (Exception e) { e.printStackTrace(); }
        try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
        try {arraySize = Integer.parseInt(this.getParameter("ArraySize", "32"));}catch(Exception e) {e.printStackTrace();}
        try {showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();} catch(Exception e) {e.printStackTrace();}
        try {temperature = Double.valueOf(this.getParameter("Temperature", "2.629")).doubleValue();}catch(Exception e) {e.printStackTrace();}
        try {bfield = Double.valueOf(this.getParameter("BField", "0")).doubleValue();}catch(Exception e) {e.printStackTrace();}
        try { flips = Integer.parseInt(this.getParameter("NumFlips", ""+arraySize*arraySize)); } catch (Exception e) { e.printStackTrace(); }
        try {jbInit();}catch(Exception e) { e.printStackTrace();}
        if(!showControls){
          controlPanel.setVisible(false);
        }
        IsingModel model=isingPanel.model;
        tempSlider.setDValue(temperature);
        tempField.setValue(temperature);
        tempField.setNoColor(true);
        model.reinitialize(arraySize, temperature, bfield);
        model.setFlipsPerStep(flips);
        tempSlider.addPropertyChangeListener(tempField); //let them communicate
        tempField.addPropertyChangeListener(tempSlider);
        tempField.addPropertyChangeListener(this);  //make sure the temp is updated in model
        tempSlider.addPropertyChangeListener(this);
        clock.setFPS(fps);
        clock.setDt(dt);
        clock.addClockListener(isingPanel);
    }

    //Component initialization
    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        controlPanel.setLayout(borderLayout2);
        startBtn.setLabel("Start");
        startBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                startBtn_actionPerformed(e);
            }
        });
        panel3.setLayout(borderLayout4);
        panel4.setLayout(verticalFlowLayout1);
        verticalFlowLayout1.setAlignment(VerticalFlowLayout.MIDDLE);
        tempSlider.setDValue(2.6);
        tempSlider.setDMax(5.0);
        //mField.setEditable(false);
        //eField.setEditable(false);
        //timeField.setEditable(false);
        controlPanel.setBackground(Color.lightGray);
        label1.setAlignment(2);
        label1.setText("T = ");
        resetBtn.setLabel("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resetBtn_actionPerformed(e);
            }
        });
        this.add(isingPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);
        controlPanel.add(panel2, BorderLayout.WEST);
        panel2.add(startBtn, null);
        panel2.add(resetBtn, null);
        controlPanel.add(panel3, BorderLayout.CENTER);
        panel4.add(tempSlider, null);
        panel3.add(panel5, BorderLayout.EAST);
        panel5.add(label1, null);
        panel5.add(tempField, null);
        panel3.add(panel4, BorderLayout.CENTER);
    }

    //Start the applet
    public void start() {
     super.start();
    }

    //Stop the applet
    public void stop() { super.stop();}

    //Destroy the applet
    public void destroy() {
      super.destroy();
    }

    //Get Applet information
    public String getAppletInfo() {
        return "Ising Physlet by Wolfgang Christian.  email: wochristian@davidson.edu";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        String[][] pinfo =
            {
            {"ArraySize", "int", "Size of the Ising array."},
            {"ShowControls", "boolean", "Show user interface"},
            {"Temperature", "double", "Temperature"},
            {"BField", "double", "External field."},
            };
        return pinfo;
    }

  public void reset(){
    super.reset();
    isingPanel.reset();
    this.clearAllData();
    updateDataConnections();
  }

  public void stepForward(){
    if(clock.isRunning()){
        clock.stopClock();
        startBtn.setLabel("Run");
    }else clock.doStep();

  }

  public synchronized void forward(){
    if(clock.isRunning()){
       clock.stopClock();
       startBtn.setLabel("Start");
    } else{
        isingPanel.model.getME();  // just to make sure that everything is up to date.
        clock.startClock();
        startBtn.setLabel("Stop");
    }
  }

  public void pause(){
     if(clock.isRunning())clock.stopClock();
     startBtn.setLabel("Run");
  }


  public void setDefault(){
      super.setDefault();
      isingPanel.setDefault();
  }

  public void setFlipsPerStep(int flips){
      isingPanel.model.setFlipsPerStep(flips);
  }

  public void setTemperature(double t){
      tempSlider.setDValue(t);
      tempField.setValue(t);
      isingPanel.model.resetT(t);
  }

  public void setField(double h){
      isingPanel.model.resetH(h);
      if(!clock.isRunning()){
          isingPanel.model.adjustE();
      }
  }

 /**
  *
  * Returns the id of the Ising model. This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getIsingID(){
     return isingPanel.getID();
  }

  void startBtn_actionPerformed(ActionEvent e) {
    forward();
  }

  void resetBtn_actionPerformed(ActionEvent e) {
        reset();
  }


}