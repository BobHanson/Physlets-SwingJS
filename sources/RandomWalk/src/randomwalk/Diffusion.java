package randomwalk;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

import edu.davidson.display.SGraph;
import edu.davidson.tools.*;



public class Diffusion extends SApplet{
  boolean isStandalone = false;
  boolean showGraph=false;
  SGraph graph = new SGraph();
  BorderLayout borderLayout1 = new BorderLayout();
  Button runBtn = new Button();
  Button stepBtn = new Button();
  Panel centerPanel = new Panel();
  BorderLayout borderLayout2 = new BorderLayout();
  Panel westPanel = new Panel();
  DiffusionPanel diffusionPanel = new DiffusionPanel(this, graph);
  FlowLayout flowLayout1 = new FlowLayout();
  Panel buttonPanel = new Panel();
  BorderLayout borderLayout3 = new BorderLayout();
  Button resetBtn = new Button();
  boolean showControls=true;
  int nrows=128;
  int ncols=128;
  int nbins=128;
  int nwalkers=128;

  //Construct the applet
  public Diffusion() {
  }

   public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
   }

   public void destroy() {
    graph.destroy();
    super.destroy();
  }

  //Initialize the applet
  public void init() {
    double fps=10.0;
    double dt=0.1;
    try { fps = Integer.parseInt(this.getParameter("FPS", "10")); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { nrows = Integer.parseInt(this.getParameter("Rows", "128")); } catch (Exception e) { e.printStackTrace(); }
    try { ncols = Integer.parseInt(this.getParameter("Cols", "128")); } catch (Exception e) { e.printStackTrace(); }
    try { nbins = Integer.parseInt(this.getParameter("Bins", "32")); } catch (Exception e) { e.printStackTrace(); }
    try { nwalkers = Integer.parseInt(this.getParameter("Particles", "128")); } catch (Exception e) { e.printStackTrace(); }
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try  {
      jbInit();
    }
    catch(Exception e)  {
      e.printStackTrace();
    }
    if(!showGraph){
        graph.setVisible(false);
    }
    if(!showControls){
        buttonPanel.setVisible(false);
    }
    diffusionPanel.setRowsColsBinsParticles(nrows, ncols, nbins, nwalkers);
    clock.setFPS(fps);
    clock.setDt(dt);
    clock.addClockListener(diffusionPanel);
  }

  //Component initialization
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    runBtn.setLabel("Run");
    runBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                runBtn_actionPerformed(e);
            }
    });
    stepBtn.setLabel("Step");
    stepBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        stepBtn_actionPerformed(e);
      }
    });
    centerPanel.setLayout(borderLayout2);
    diffusionPanel.setLayout(flowLayout1);
    westPanel.setLayout(borderLayout3);
    this.add(centerPanel, BorderLayout.CENTER);
    westPanel.add(diffusionPanel, BorderLayout.CENTER);
    westPanel.add(buttonPanel, BorderLayout.SOUTH);
    resetBtn.setLabel("Reset");
    resetBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resetBtn_actionPerformed(e);
            }
    });
    this.add(centerPanel, BorderLayout.CENTER);
    buttonPanel.add(stepBtn, null);
    buttonPanel.add(runBtn, null);
    buttonPanel.add(resetBtn, null);

    if(!showGraph){
         centerPanel.add(westPanel, BorderLayout.CENTER);
    }else {
        centerPanel.add(graph, BorderLayout.CENTER);
        centerPanel.add(westPanel, BorderLayout.WEST);
    }
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Diffusion Physlet authored by Wolfgang Christian, wochristian@davidson.edu.";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    return null;
  }

 /**
  *
  * Returns the id of the ensemble. This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getEnsembleID(){
     return diffusionPanel.getID();
  }


 /**
  *
  * Returns the id of the histogram. This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getHistogramID(){
     return diffusionPanel.getHistogramID();
  }

  public void setRowsColsBinsParticles(int r, int c, int b, int p){
    boolean shouldrun=clock.isRunning();
    clock.stopClock();
    diffusionPanel.setRowsColsBinsParticles( r,  c,  b,  p);
    if(shouldrun) clock.startClock();
  }

  public void reset(){
    diffusionPanel.reset();
    diffusionPanel.paint();
    graph.clearAllSeries();
    updateDataConnections();
  }

  public void stepForward(){
    if(clock.isRunning()){
        clock.stopClock();
        runBtn.setLabel("Run");
    }else clock.doStep();

  }

  public void forward(){
     if(!clock.isRunning())clock.startClock();
     runBtn.setLabel("Stop");
  }

  public void pause(){
     if(clock.isRunning())clock.stopClock();
     runBtn.setLabel("Run");
  }


  public void setDefault(){
      super.setDefault();
      diffusionPanel.reset();
  }

  void stepBtn_actionPerformed(ActionEvent e) {
    clock.doStep();
  }



  void runBtn_actionPerformed(ActionEvent e) {
    if(clock.isRunning()){
       clock.stopClock();
       runBtn.setLabel("Run");
    } else{
        clock.startClock();
        runBtn.setLabel("Stop");
    }
  }



  void resetBtn_actionPerformed(ActionEvent e) {
    diffusionPanel.reset();
  }
}
