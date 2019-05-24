package dla;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import edu.davidson.display.*;
import edu.davidson.tools.*;

/**
*
* Diffusion Limited Aggregation
*
* A program that models the construction of a snowflake.  It uses a random walker to
* attaches particles to the snowflake. Data connections added by W. Chrsitian.
*
* @Author: Andrew R. Schoewe
* @Date:  4/18/00
*
*/
public class DLA extends SApplet {
  boolean isStandalone = false;
  SGraph graph=new SGraph() ;
  public Dlamodel drawingPanel = new Dlamodel(this, graph);
  BorderLayout borderLayout1 = new BorderLayout();
  Panel controlPanel = new Panel();
  Button startBtn = new Button();
  Button stopBtn = new Button();
  Button resetBtn = new Button();
  Checkbox graphCheck = new Checkbox();
  SGraphFrame graphFrame = new SGraphFrame(drawingPanel.graphPanel); //pass the SGraph from my model
  boolean showControls=true;




  //Construct the applet
  public DLA() {
  }

  public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Initialize the applet
  public void init() {
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try  {
      jbInit();
    }
    catch(Exception e)  {
      e.printStackTrace();
    }
    controlPanel.setVisible(showControls);
  }

  //Component initialization
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    startBtn.setLabel("Start");
    startBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        startBtn_actionPerformed(e);
      }
    });
    stopBtn.setLabel("Stop");
    stopBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        stopBtn_actionPerformed(e);
      }
    });

    resetBtn.setLabel("Reset");
    resetBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        resetBtn_actionPerformed(e);
      }
    });
    graphCheck.setLabel("Graph");
    graphCheck.addItemListener(new java.awt.event.ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        graphCheck_itemStateChanged(e);
      }
    });
    graphFrame.addWindowListener(new java.awt.event.WindowAdapter() {

      public void windowClosing(WindowEvent e) {
        graphFrame_windowClosing(e);
      }
    });
    graphFrame.setTitle("Ln of Mass vs. Ln of Distance");
    this.add(drawingPanel, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(startBtn, null);
    controlPanel.add(stopBtn, null);
    controlPanel.add(resetBtn, null);
    controlPanel.add(graphCheck, null);
  }

  //Get Applet information
  public final String getAppletInfo() {
    return "Diffusion Limited Aggregation by A. Schoewe and W. Christian.  email:wochristian@davidson.edu";
  }

  public String[][] getParameterInfo() {
    String pinfo[][] = {
      {"ShowControls", "boolean", "Show the control buttons at the bottom of the applet."},
    };
    return pinfo;
  }

/**
  *
  * Returns the id of the mass histogram. This id can be used to make data connections.
  *
  * @param show  Show the graph if true; otherwise, hide the graph.
  *
  */
  public void setShowGraph(boolean show){
      if(show){
        graphCheck.setState(show);
        initFrame();
        graphFrame.setVisible(true);//show the graph
        drawingPanel.plotGraph();
      }else{
        graphCheck.setState(show);
        graphFrame.setVisible(false);//hide the graph
      }
  }

/**
  *
  * Returns the id of the mass histogram. This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getHistogramID(){
     return drawingPanel.getHistogramID();
  }

/**
*
* When the start button is pressed, the start method in my control is called.
* @param e is an actionEvent variable called when the start button is pressed
*/

  void startBtn_actionPerformed(ActionEvent e) {
    forward();
  }

/**
* When the stop button is pressed, the stop method in my control is called.
* @param is an an actionEvent variable called when the stop button is pressed
*/

  void stopBtn_actionPerformed(ActionEvent e) {
    pause();
  }

/**
* When the reset button is pressed, the reset method in my control is called.
* @param e is an actionEvent variable called when the reset button is pressed
*/

  void resetBtn_actionPerformed(ActionEvent e) {
    reset();
  }

/**
* When the graph check box is either check or unchecked, this method is called.
* @param e is an ItemEvent called when the GraphCheck is checked or unchecked.
*/

  void graphCheck_itemStateChanged(ItemEvent e) {
    if(graphCheck.getState())  {
       initFrame();
       graphFrame.setVisible(true);//show the graph
       drawingPanel.plotGraph();
      }
    if(!graphCheck.getState())  {
      graphFrame.setVisible(false);//hide the graph
    }
  }

/**
* This method tells notifies the program that the SGraphFrame is being closed.
* @param e is a WindowEvent variable called when the window is closed by the user.
*/

  void graphFrame_windowClosing(WindowEvent e) {
    //createFrame(); //create a new SGraph and SGraphFrame
    graphCheck.setState(false); //uncheck the check box
  }

/**
*
* This method creates a new SGraph and SGraphFrame.  This is needed because when the user closes the
* SGraphFrame with the exit button (upper right hand corner of window), the drawing thread for the SGraph is stopped.
* So, to keep the SGraphFrame updated with new SGraph data, we need to make a new SGraph (and of course, a new SGraphFrame).
*


  public void createFrame_old() {
   graph=new SGraph() ;
   graph.setAutoReplaceData(1,true);
   drawingPanel.setGraph(graph);  //set the SGraph in our model, to the new SGraph (with a new drawing thread)
   graphFrame = new SGraphFrame(graph);
   graphFrame.addWindowListener(new java.awt.event.WindowAdapter() { //make it listen to events with the new SGraphFrame

      public void windowClosed(WindowEvent e) {
        graphFrame_windowClosing(e);
      }
    });
   graphFrame.setSize(250,300);
   graph.setSeriesStyle(1,true, 3);
   graph.setMarkerSize(1,.5);
   graph.setLabelX("Ln of Distance");
   graph.setLabelY("Ln of Mass");
   graphCheck.setState(false); //uncheck the check box
  }
*/


public void initFrame() {
   graph.setAutoReplaceData(1,true);
   drawingPanel.setGraph(graph);  //set the SGraph in our model, to the new SGraph (with a new drawing thread)
   /** j2sNative */{
     graphFrame.setSize(250,300);
   }
   //graph.setLabelX("Log_{10} of Distance");
   //graph.setLabelY("Ln of Mass");
  // graphCheck.setState(false); //uncheck the check box
  }


  synchronized public void reset(){
    drawingPanel.reset();
    drawingPanel.paint();
  }

  public void stepForward(){

  }

  synchronized public void forward(){
     drawingPanel.start();
  }

  synchronized public void pause(){
     drawingPanel.stop();
  }


  synchronized public void setDefault(){
      super.setDefault();
      drawingPanel.reset();
  }

  /**
 *  Destroy the paint thread.
 *
 *  Added by W. Christian
 */
    public void destroy(){
     drawingPanel.destroy();
     if(graph!=null) graph.destroy();
     super.destroy();
   }

}
