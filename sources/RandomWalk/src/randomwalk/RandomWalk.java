package randomwalk;

//import java.awt.*;
import java.awt.event.*;
import edu.davidson.display.*;
import edu.davidson.tools.*;
import a2s.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;


public class RandomWalk extends SApplet {
  boolean isStandalone = false;
  boolean showGraph=false;
  SGraph graph = new SGraph();
  BorderLayout borderLayout1 = new BorderLayout();
  Button walkBtn = new Button();
  Button stepBtn = new Button();
  Panel centerPanel = new Panel();
  BorderLayout borderLayout2 = new BorderLayout();
  Panel westPanel = new Panel();
  WalkPanel walkPanel = new WalkPanel(this, graph);
  FlowLayout flowLayout1 = new FlowLayout();
  Panel buttonPanel = new Panel();
  BorderLayout borderLayout3 = new BorderLayout();
  Button runBtn = new Button();
  Button clearBtn = new Button();
  boolean showControls=true;
  int nrows=25;
  int ncols=25;
  int nsteps=25;


  //Construct the applet
  public RandomWalk() {
  }


  public void destroy() {
    graph.destroy();
    super.destroy();
  }

  public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Initialize the applet
  public void init() {
    double fps=10.0;
    double dt=0.1;
    try { fps = Integer.parseInt(this.getParameter("FPS", "10")); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { nrows = Integer.parseInt(this.getParameter("Rows", "25")); } catch (Exception e) { e.printStackTrace(); }
    try { ncols = Integer.parseInt(this.getParameter("Cols", "25")); } catch (Exception e) { e.printStackTrace(); }
    try { nsteps = Integer.parseInt(this.getParameter("Steps", "25")); } catch (Exception e) { e.printStackTrace(); }
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
    walkPanel.setRowsColsSteps(nrows, ncols, nsteps);
    clock.setFPS(fps);
    clock.setDt(dt);
    clock.addClockListener(walkPanel);
  }

  //Component initialization
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    walkBtn.setLabel("Walk");
        walkBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                walkBtn_actionPerformed(e);
            }
        });
    walkBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                walkBtn_actionPerformed(e);
            }
    });
    stepBtn.setLabel("Step");
    stepBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        stepBtn_actionPerformed(e);
      }
    });
    centerPanel.setLayout(borderLayout2);
    walkPanel.setLayout(flowLayout1);
    westPanel.setLayout(borderLayout3);
    runBtn.setLabel("Run");
    runBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                runBtn_actionPerformed(e);
            }
    });
    clearBtn.setLabel("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                clearBtn_actionPerformed(e);
            }
        });
    this.add(centerPanel, BorderLayout.CENTER);
    westPanel.add(walkPanel, BorderLayout.CENTER);
    westPanel.add(buttonPanel, BorderLayout.SOUTH);
    buttonPanel.add(stepBtn, null);
    buttonPanel.add(walkBtn, null);
    buttonPanel.add(runBtn, null);
    buttonPanel.add(clearBtn, null);
    if(!showGraph){
         centerPanel.add(westPanel, BorderLayout.CENTER);
    }else {
        centerPanel.add(graph, BorderLayout.CENTER);
        centerPanel.add(westPanel, BorderLayout.WEST);
    }
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Random Walk in two dimensions.  Author: wochristian@davidson.edu";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    return null;
  }

  public void setDefault(){
      super.setDefault();
      walkPanel.reset();
  }

  public void setRowsColsSteps(int r, int c, int s){
    boolean shouldrun=clock.isRunning();
    clock.stopClock();
    walkPanel.setRowsColsSteps( r,  c,  s);
    if(shouldrun) clock.startClock();
  }

 /**
  *
  * Returns the id of the histogram. This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getHistogramID(){
     return walkPanel.getHistogramID();
  }

  public void reset(){
    walkPanel.reset();
    walkPanel.paint();
    graph.clearAllSeries();
    updateDataConnections();
  }

  public void stepForward(){
    if(clock.isRunning())clock.stopClock();
    else clock.doStep();
  }

  public void forward(){
     walkPanel.runMode=true;
     clock.startClock();
  }

  public void pause(){
     if(clock.isRunning())clock.stopClock();
  }

  public void walk(){
     walkPanel.runMode=false;
     clock.startClock();
  }


  void stepBtn_actionPerformed(ActionEvent e) {
    stepForward();
  }

  void walkBtn_actionPerformed(ActionEvent e) {
     walk();
  }

 void runBtn_actionPerformed(ActionEvent e) {
      forward();
  }

  void clearBtn_actionPerformed(ActionEvent e) {
       reset();
  }


}
