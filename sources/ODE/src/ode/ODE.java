package ode;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.*;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.*;
import java.awt.*;

/**
 * The class <code>ODE</code> solves systems of first order ordinary differential equation, ODEs,
 * and passes the solution to data listeners.
 *
 * @author  Wolfgang Christian
 * @version 1.0, 1 Oct 2000
 */
public class ODE extends SApplet {
  boolean isStandalone = false;
  Equations equations=null;
  boolean firstTime=true;

  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

/**
 * ODE constructor.
 */
  public ODE() {
  }

/**
 * Initialize the applet.
 */
  public void init() {
    double dt=0.1, fps=10;
    String method="SRK45";
    try { fps = Double.valueOf(this.getParameter("FPS", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { method = this.getParameter("MathMethod", "SRK45"); } catch (Exception e) { e.printStackTrace(); }
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    equations=new Equations(this, method);
    clock.setDt(dt);
    clock.setFPS(fps);
    clock.addClockListener(equations);     // have the clock call the step function in this applet at every tick.
  }

  //Component initialization
  private void jbInit() throws Exception {
  }

  //Get Applet information
  public final String getAppletInfo() {
    return "ODE Physlet by Wolfgang Christian.  wochristian@davidson.edu";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    return null;
  }

  /**
   * Entry point for standalone application.
   */
  public static void main(String[] args) {
    ODE applet = new ODE();
    applet.isStandalone = true;
    Frame frame;
    frame = new Frame() {

      protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
          System.exit(0);
        }
      }

      public synchronized void setTitle(String title) {
        super.setTitle(title);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      }
    };
    frame.setTitle("Applet Frame");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }

/**
 * Show the differential equations in a text window.
*/
  public void showEquations(){
          equations.showEquations();
  }

  /**
   * Remove the current system of ODEs and delete the data connections.
   *
   * The ode step will be set equal to the clock time step.
   */
  public void setDefault(){
      clock.stopClock();
      deleteDataConnections(); // we are going to delete all the charges so we might as well kill the conections too.
      clock.setContinuous();
      clock.setTime(0);
      equations.setDefault();
      equations.setODEStep(clock.getDt());
  }

  /**
   * Set the tolerance. Default is 1 part in 1.0e-8.
   */
  public void setTolerance(double t){
      equations.setTolerance(t);
  }

  /**
   * Set the ODE step.
   *
   */
  public void setODEStep(double h){
      equations.setODEStep(h);
  }

/**
* Reset the initial conditions at time t=0.
*/
  public void reset(){
     pause();
     clock.setTime(0);
     equations.reset();
     this.clearAllData();
     this.updateDataConnections();
  }

  /**
   * Create an object and add it to the Physlet.
   *
   * Many Physlets use the addObject method.  The first argument is the name of
   * the object to be added and the second is a comma-delimited list of parameters.
   * In this applet a system of ODEs can be created a follows:
   * <p>
   * <code>addObject ("ode","var=t,value=0,eqn=1");</code>
   * <code>addObject ("ode","var=n,value=100,eqn=-0.1*t");</code>
   * </p>
   * The above example produces exponential decay of the variable n.
   *
   * @param              name the type of object to be created
   * @param              parList a list of parameters to be set
   */
  public void addObject(String name, String list){
    name=name.toLowerCase().trim();
    name=SUtil.removeWhitespace(name);
    list=SUtil.removeWhitespace(list);
    if (name.equals("ode")) {
      String variable=null;
      String equation=null;
      double value=0;
      if( SUtil.parameterExist(list,"var=")) variable=SUtil.getParamStr(list,"var=");
      if( SUtil.parameterExist(list,"rate=")) equation=SUtil.getParamStr(list,"rate=");
      if( SUtil.parameterExist(list,"value="))value=SUtil.getParam(list,"value=");
      if(variable != null && equation !=null){
        equations.addRateEquation(variable,value,equation);
      } else System.out.println("Error:ODE not created.");
    }
  }


    /**
   * Change the properies of an object.
   * The first argument is the object identifier.
   * The second argument is the name of the property and the third is a
   * comma-delimited list of parameters.  For example, the current value of a variable
   * associated with a particular ode can be changed using the following code.
   *<p>
   * <code>set(id, "ode", "value=2");</code>
   * </p>
   * The initial value can be changed as follows.
   *<p>
   * <code>set(id, "ode", "initial=-1");</code>
   * </p>
   * @param              id the identifier of the object
   * @param              name the type of property to be created.
   * @param              parList a list of parameters
   * @return             true if successful
   */
  public synchronized boolean set(int id, String name, String parList){
     name=name.toLowerCase().trim();
     name=SUtil.removeWhitespace(name);
     String parList2=parList.trim();
     parList=SUtil.removeWhitespace(parList);
     if(name.equals("ode")){
        double val=0;
        if( SUtil.parameterExist(parList,"value=")){
          val=SUtil.getParam(parList,"value=");
          this.equations.setValue(id,val);  // set the value of the variable
        }
        if( SUtil.parameterExist(parList,"initial=")){
          val=SUtil.getParam(parList,"initial=");
          this.equations.setInitialValue(id,val);  // set the initial value of the variable
        }
     }else if(name.equals("system")){
        String method="";
        double h=0.1;
        if( SUtil.parameterExist(parList,"method=")){
          method=SUtil.getParamStr(parList2,"method=");
          method=SUtil.removeWhitespace(method);
          this.equations.setMethod(method);  // set the numerical method
        }
        if( SUtil.parameterExist(parList,"h=")){
          h=SUtil.getParam(parList,"h=");
          if(h==0){
             h=clock.getDt();
             System.out.println("Error; ODE step size cannot be zero.");
          }
          equations.setODEStep(h);
        }
     }else{
         System.out.println("Set property not found: " + name + "parameter list: " + parList);
         return false;
     }
     this.setAutoRefresh(autoRefresh);
     return true;
  }

/**
 * Parser the ODE objects to produce the system of rate equations.
 *
 * Call this method after all ODEs have been created using the addObject method.
 *
 * @return true if equations are valid
 */
  public boolean parse(){
    return equations.parse();
  }

/**
 * Called by browser to start the applet.  DO NOT SCRIPT.
 */
  public void start(){
    super.start();
    if(firstTime){
      firstTime=false;

      //addObject("ode","var=t,value=0,rate=1");
      //addObject("ode","var=n,value=100,rate=-0.1*n");
      //parse();
      //clock.startClock();
      //getDataID(100);
      //this.stepClock();
    }
    //this.showEquations();
  }

  /**
   * Reverse the direction of the time step in the animation.
   */
  public void reverse(){clock.setDt(-clock.getDt());}

/**
   * Start the animation.
   */
  public void forward(){
      if(equations.isValidSystem() )clock.startClock();
      else System.out.println("Error: ODE system is not valid.  Check syntax and parse.");
  }

/**
   * Pause the animation.
   */
  public void pause(){
      if(clock.isRunning())clock.stopClock();
  }

/**
	 * Step back by one ODE step.
	 */
	public void stepBack(){
        if(clock.isRunning()){
            pause();
            return;
        }
        boolean isNegative=false;
        if(clock.getDt()<0) isNegative=true;
        clock.setDt(-Math.abs(clock.getDt()));
        clock.doStep();
        if(!isNegative) clock.setDt(Math.abs(clock.getDt() )); // make dt positive since it started out that way.
	}

 /**
	 * Step forward by one ODE step.
	 */
	public void stepForward(){
        if(clock.isRunning()){
            pause();
            return;
        }
        boolean isNegative=false;
        if(clock.getDt()<0) isNegative=true;
        clock.setDt(Math.abs(clock.getDt() ));
        clock.doStep();
        if(isNegative) clock.setDt(-Math.abs(clock.getDt() )); // make dt negative since it started out that way.
	}


/**
 * Get the data source identifier for the ODE solver.
 *
 * The data source will contain the current values of the state variables.
 *
 * @return int the data source identifier
 */
  public int getSourceID(){return equations.getID();}

/**
 * Create a multi-point data set and return the id for use in a data connection.
 *
 * The data source will contain the multiple values of the state variables.
 *
 * @param numpts the number of points in the data set
 *
 * @return int the data source identifier
 */
  public int getDataID(int numpts){return equations.getDataID(numpts);}
}