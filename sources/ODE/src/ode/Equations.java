
package ode;

import java.awt.TextArea;
import java.awt.*;

import edu.davidson.tools.*;
import edu.davidson.numerics.*;
import edu.davidson.views.STextDialog;
/**
 * The class <code>Equations</code> object keeps track of the differential
 * equations and the state variables.
 *
 * @author  Wolfgang Chrsitian
 * @version 1.0, 1 Oct 2000
 */
public class Equations implements SDataSource, SDifferentiable,SStepable{

  String[] variables=null;
  String[] sourceVariables=new String[]{""};
  RateEquation[] rateEquations=null;
  double[] state=null;
  double[] initialState=null;
  double[] rates=null;
  int numEqn=0;
  double[][] ds= null;
  SApplet owner=null;
  SODE     odeSolver=null;
  DataSet   dataSet=null;
  ///STextFrame textFrame=null;
  STextDialog textFrame=null;
  private boolean validSystem=false;

/**
 * Equations constructor.
 *
 * The owner is the SApplet that created this object.
 *
 * @param o the owner of the object
 */
  public Equations(SApplet o, String method) {
    try {
        Class c = Class.forName("edu.davidson.numerics."+ method);
        Object object=c.newInstance();
        if(object instanceof SODE) odeSolver=(SODE)object;
        else{
            odeSolver=new SRK45();
        }
    } catch (Exception e) {
        System.out.println("Math method not found: " + method);
        System.out.println("caught: " + e.toString());
        odeSolver=new SRK45();
    }
    owner=o;
    odeSolver.setDifferentials(this);
    try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
  }

  /**
   * Set the tolerance. Default is 1 part in 1.0e-8.
   */
  public void setTolerance(double t){
      if(odeSolver!=null) odeSolver.setTol(t);
  }

  public void showEquations(){
      //System.out.println("Show Equations");
      String indent="  ";
      if(textFrame==null){
          Object anchorpoint = owner.getParent();
          while (!(anchorpoint instanceof Frame))
	            anchorpoint = ((Component)anchorpoint).getParent();
          if(anchorpoint instanceof Frame) textFrame=new STextDialog((Frame)anchorpoint, "ODE");
          else return;
      }
      //if(textFrame==null)textFrame=new STextDialog("ODE");
      TextArea textArea=textFrame.getTextArea();
      textArea.setText("");
      textArea.append("<odephyslet>"+"\n");
      textArea.append(indent+"<mathmethod name=\""+odeSolver.getClass().getName()+"\">"+"\n");
      textArea.append(indent+"<odesystem>"+"\n");
      for(int i=0; i<numEqn; i++){
        textArea.append(rateEquations[i].appendXMLEquation(indent+indent));
      }
      textArea.append(indent+"<\\odesystem>"+"\n");
      textArea.append("<\\odephyslet>"+"\n");
      textFrame.show();
  }

/**
* Set the ODE step.
*/
  public void setODEStep(double h){
      if(odeSolver!=null) odeSolver.setH(h);
  }

/**
 * Add a rate quation to this system of ODEs.
 *
 * @param vrs the variable
 * @param val the value of the variable
 * @param eqn the rate equation for the variable
 */
  void addRateEquation(String vrs, double val,String eqn){

     String[] newVariables= new String[numEqn+1];
     String[] newSourceVariables= new String[2*(numEqn+1)];
     RateEquation[] newEquations= new RateEquation[numEqn+1];
     double[] newState= new double[numEqn+1];
     double[] newInitial= new double[numEqn+1];
     rates= new double[numEqn+1];
     for(int i=0; i<numEqn; i++){
         newVariables[i]=variables[i];
         newSourceVariables[i]=sourceVariables[i];
         newSourceVariables[i+numEqn+1]=sourceVariables[i+numEqn]; //the rates
         newEquations[i]=rateEquations[i];
         newState[i]=state[i];
         newInitial[i]=initialState[i];
     }
     newState[numEqn]=val;
     newInitial[numEqn]=val;
     newVariables[numEqn]=vrs;
     newSourceVariables[numEqn]=vrs;
     newSourceVariables[2*numEqn+1]=vrs+"_rate";
     newEquations[numEqn]=new RateEquation(vrs, val, eqn);

     variables=newVariables;
     sourceVariables=newSourceVariables;
     rateEquations=newEquations;
     state=newState;
     initialState=newInitial;
     numEqn++;
     if(textFrame!=null && textFrame.isVisible() ) showEquations();

  }
/**
 * Parser the equatons to produce a consistent system of rate requations.
 *
 * @return true if equations are valid
 */
  boolean parse(){
     validSystem=false;
     for(int i=0; i<numEqn; i++){
        validSystem=rateEquations[i].setVariables(variables);  // parser all the equations
        if(!validSystem) return validSystem;  // return if the system is not valid
     }
     odeSolver.setDifferentials(this);  //this sets the number of equations in the ODE solver.
     return validSystem;
  }

/**
* Reset the initial conditions at time t=0.
*/
  public void reset(){
     for(int i=0; i<numEqn; i++){
         state[i]=initialState[i];
     }
  }

  /**
   * Remove the current system of ODEs.
   */
  public void setDefault(){
    variables=null;
    rateEquations=null;
    state=null;
    initialState=null;
    rates=null;
    dataSet=null;
    numEqn=0;
    if(textFrame!=null && textFrame.isVisible() ) showEquations();
  }

/**
 * Check if the ode system has been parsed correctly.
 *
 * @return true if the system is valid
 */
  boolean isValidSystem(){return validSystem;}

/**
 * Advance the time by dt and then update data connections.
 *
 * @param dt the time increment.
 * @param time the time before the system changes
 */
  public void step(double dt, double t){
      if(!validSystem || numEqn==0 ) return;
      if(dataSet==null)odeSolver.step(dt, state);             // send the variables to the ode solver.
        else dataSet.solve();
      owner.updateDataConnections();
  }
/**
 * Get the rate using the x as the state variables.
 *
 * @param x the current state
 * @return the current rate
 */
  public double[] rate(double[] x){
    for(int i=0; i<numEqn; i++){
        rates[i]=rateEquations[i].parser.evaluate(x);
    }
    return rates;
  }

/**
 * Set the numerical method that should be used to solve the ODE.
 *
 * @param method the name of the method
 */
  public void setMethod(String method){
    boolean shouldRun=owner.clock.isRunning();
    owner.clock.stopClock();

    if(shouldRun) owner.clock.startClock();
  }


/**
 * Set the current value of a variable.
 *
 * @param id the rate equation id
 * @return the new value
 */
  public void setValue(int id, double val){
    boolean shouldRun=owner.clock.isRunning();
    owner.clock.stopClock();
    for(int i=0; i<numEqn; i++){
        if(rateEquations[i].hashCode()==id )state[i]=val;
    }
    if(shouldRun) owner.clock.startClock();
  }

  /**
 * Set the initial value of a variable.
 *
 * @param id the rate equation id
 * @return the new value
 */
  public void setInitialValue(int id, double val){
    for(int i=0; i<numEqn; i++){
        if(rateEquations[i].hashCode()==id )initialState[i]=val;
    }
    if(textFrame!=null && textFrame.isVisible() ) showEquations();
  }

/**
 * Get the number of equations.
 *
 * @return the number of equations
 */
  public int getNumEqu(){
       return numEqn;
  }

/**
 * Get the data source values.
 *
 * @return the data soure values
 */
  public double[][] getVariables(){
    if(ds==null || ds[0].length!=2*numEqn){
      ds= new double[1][2*numEqn];
    }
    for(int i=0; i<numEqn;i++){
      ds[0][i]=state[i];
    }
    for(int i=numEqn; i<2*numEqn;i++){
      ds[0][i]=rateEquations[i-numEqn].parser.evaluate(state);
    }
    return ds;
  }

  public String[]   getVarStrings(){return sourceVariables;}
  public int getID(){return this.hashCode();}
  public void setOwner(SApplet o){owner=o;}
  public SApplet getOwner(){return owner;}

 /**
 * Get the data source identifier for a multi-point data set.
 *
 * The data source will contain the multiple values of the state variables.
 *
 * @param n the number of points in the data set
 *
 * @return int the data source identifier
 */
  int getDataID(int n){
    n=Math.max(1,n);
    if(dataSet==null){ dataSet=new DataSet(n);}
    if(dataSet.numpts!=n){  //see if the length changed
      dataSet.numpts=n;
    }
    return dataSet.hashCode();
  }
/*
  private synchronized void solve(){
    if( owner.clock.isRunning() )owner.clock.stopClock();
    if(dataSet==null){
      System.out.println("Error in solve method. You must first create the data set using the getDataID method.");
      return;
    }
    dataSet.solve();
  }*/

  class DataSet extends Object implements SDataSource {
      double[][] data=null;
      int numpts=0;
      DataSet(int n){
        numpts=n;
        try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
      }

      void solve(){
         if(numEqn==0) return;
         if(data==null || data.length==numpts || data[0].length!=2*numEqn){
            data=new double[numpts][2*numEqn];
         }
         for(int n=0; n<numpts; n++){
            for(int i=0; i<numEqn;i++){
              data[n][i]=state[i];
            }
            for(int i=numEqn; i<2*numEqn;i++){
              data[n][i]=rateEquations[i-numEqn].parser.evaluate(state);
            }
            odeSolver.step(owner.clock.getDt(), state);// send the variables to the ode solver.
         }
      }

      public double[][] getVariables(){
        return data;
      }

      public void setOwner(SApplet o){;}
      public SApplet getOwner(){return Equations.this.owner;}

      public String[]   getVarStrings(){ return Equations.this.sourceVariables;}

      public final int getID(){return hashCode();}

  }  // end of data object
}