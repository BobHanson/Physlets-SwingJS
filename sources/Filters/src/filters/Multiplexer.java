package filters;

import java.util.Vector;
import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;

/**
 * The Multiplexer Physlet accepts data from multiple data sources and passes this data to other Physlets.
 *
 * A separate data listener is created for each index.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 0.1, Date: 2000/08/09
 */
public class Multiplexer extends SApplet implements SDataSource {
  boolean isStandalone = false;
  String[] varStrings= new String[]{"x","y"};
  double[][] ds=new double[1][2];   // the datasource state variables
  Vector listeners= new Vector();   // The list of all data sources
  String[] parserVars=null;         // the parser variables
  double[] parserValues=null;
  Parser xParser=null;
  String xFunc="0";
  Parser yParser=null;
  String yFunc="0";

  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  public Multiplexer() {
  }

  //Initialize the applet
  public void init() {
    try {
        jbInit();
     }
     catch(Exception e) {
        e.printStackTrace();
     }
     xFunc="0";
     xParser = new Parser(1);
    xParser.defineVariable(1,"x");
    xParser.define(xFunc);
    xParser.parse();
    yFunc="0";
    yParser = new Parser(1);
    yParser.define(yFunc);
    yParser.defineVariable(1,"y");
    yParser.parse();
    addDataSource(this);
  }

  //Component initialization
  private void jbInit() throws Exception {
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Multiplexer Physlet writen by Wolfgang Christian.  wochristian@davidson.edu";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    return null;
  }

  /**
 *  Data source method.  DO NOT SCRIPT.
 */
  public synchronized double[][] getVariables(){
      if(listeners.size()<=0){
         System.out.println("Warning: no data listeners in Multiplexer Physlet.");
         ds=new double[1][2];
         ds[0][0]=0;
         ds[0][1]=0;
         return ds;
      }

      int maxRows=getNumValues();
      if(maxRows!= ds.length){
          ds=new double[maxRows][2];
      }
      for(int row=0; row<maxRows; row++){
          for(int i=0; i<listeners.size();i++){
            Listener listener=(Listener)listeners.elementAt(i);
            if(row<listener.lastValues.length){
                parserValues[2*i]=listener.lastValues[row][0];
                parserValues[2*i+1]=listener.lastValues[row][1];
            }else{
                parserValues[2*i]=listener.lastValues[0][0];
                parserValues[2*i+1]=listener.lastValues[0][1];
            }
          }
          ds[row][0]=xParser.evaluate(parserValues);
          ds[row][1]=yParser.evaluate(parserValues);
      }
      return ds;
    }
/**
 *  Data source method.  DO NOT SCRIPT.
 */
    public String[]   getVarStrings() {return varStrings;}

// public int getID(){return this.hashCode();}   already in superclass

/**
 *  Data source method.  DO NOT SCRIPT.
 */
    public void setOwner(SApplet owner){; }
/**
 *  Data source method.  DO NOT SCRIPT.
 */
    public SApplet getOwner( ){return this;}

/**
 * Sets the default conditions.
 *
 *  Destroy all data listeners.
 *  Set the clock to zero.
 *  Delete data connections.
 *
 */
    synchronized public void setDefault(){
      clock.stopClock();
      deleteDataConnections(); // we are going to delete all the charges so we might as well kill the conections too.
      clock.setContinuous();
      clock.setTime(0);
      listeners.removeAllElements();
      xFunc="0";
      xParser = new Parser(1);
      xParser.defineVariable(1,"x");
      xParser.define(xFunc);
      xParser.parse();
      yFunc="0";
      yParser = new Parser(1);
      yParser.defineVariable(1,"y");
      yParser.define(yFunc);
      yParser.parse();
    }
/**
 * Set the functions of the data listener x and y values.
 *
 * @param xFunction the output function for x
 * @param yFunction the output function for y
 *
 * @return true if the functions parse correctly
 */
    synchronized public boolean setFunctions(String xFunction, String yFunction){
       if(listeners.size()<=0){
         System.out.println("Create data listeners before you define the output function.");
         xFunction="0";
         yFunction="0";
       }
       xFunc=xFunction;
       xParser.defineVariables(parserVars);
       xParser.define(xFunc);
       xParser.parse();
       if(xParser.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse function: "+xFunc);
         System.out.println("Parse error in Multiplexer x function: " + xParser.getErrorString() +
                   " at math fuinction, position " + xParser.getErrorPosition());
         System.out.println("Create data listeners before you define the output function.");
         return false;
       }
       yFunc=yFunction;
       yParser.defineVariables(parserVars);
       yParser.define(yFunc);
       yParser.parse();
       if(yParser.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse function: "+yFunc);
         System.out.println("Parse error in Multiplexer y function: " + yParser.getErrorString() +
                   " at math fuinction, position " + yParser.getErrorPosition());
         System.out.println("Create data listeners before you define the output function.");
         return false;
       }
       return true;
    }

/**
 * Set a data listener to be active.
 *
 * An active data listener will update all data connections whenever it receives
 * new data.
 *
 * @param index
 * @param active true will set the listener to be active
 *
 * @return true if the listener exists, false otherwise
 */
    synchronized public boolean setActiveListener(int index, boolean active){
       Listener listener=null;
       for(int i=0; i<listeners.size();i++){
        listener=(Listener)listeners.elementAt(i);
        if(listener.index==index){
            listener.active=active;
            return true;
        }
       }
       return false;
    }

/**
 *  Create a data listener with a given index.
 *
 *  The index is used to identify the variables from each data listener.  For example, if the index
 *  is 2 then the variables produced by this listener will be x2 and y2.
 *
 *  @param index the index
 *
 *  @return the id of the data listener
 */
    synchronized public int addMultiplexListener(int index){
       Listener listener=null;
       for(int i=0; i<listeners.size();i++){
        listener=(Listener)listeners.elementAt(i);
        if(listener.index==index) return listener.getID();
       }
       listener = new Listener(index);
       listeners.addElement(listener);
       setParserVars();
       return listener.getID();
    }

    public int addDataListener(int index){
      return addMultiplexListener(index);
    }

    synchronized private void setParserVars(){
       int numVars=2*listeners.size();
       xParser = new Parser(numVars);
       yParser = new Parser(numVars);
       parserVars=new String[numVars];
       parserValues=new double[numVars];
       for(int i=0; i<listeners.size();i++){
        Listener listener=(Listener)listeners.elementAt(i);
        parserVars[2*i]=listener.xStr;
        parserVars[2*i+1]=listener.yStr;
       }
    }

    synchronized private int getNumValues(){
       int num=0;
       for(int i=0; i<listeners.size();i++){
        Listener listener=(Listener)listeners.elementAt(i);
        num=Math.max(num,listener.lastValues.length);
       }
       return num;
    }
// inner class that listens for data and stores the data in a temporary array.
  class Listener extends Object implements SDataListener{
      int index=0;
      String xStr="x0";
      String yStr="y0";
      boolean active=false;
      double[][] lastValues=new double[1][2];  // the datasource state variables


      public Listener(int i) {
         index=i;
         xStr="x"+i;
         yStr="y"+i;
         try{SApplet.addDataListener(this);}catch (Exception e){e.printStackTrace();}
      }

      public void setOwner(SApplet owner){;}
      public SApplet getOwner(){return Multiplexer.this;}

      public String[]   getVarStrings(){ return varStrings;}
      public final int getID(){return this.hashCode();}

      public void deleteSeries(int id){;}
      public void clearSeries(int id){;}
      synchronized public void addDatum(SDataSource s, int id, double x, double y ){
        lastValues[0][0]=x;
        lastValues[0][1]=y;
        if(active)Multiplexer.this.updateDataConnections();
      }
      synchronized public void addData(SDataSource s,int id, double x[], double y[] ){
        if(x.length!= lastValues.length){
          lastValues=new double[x.length][2];
        }
        for(int i=0; i<x.length; i++){
            lastValues[i][0]=x[i];
            lastValues[i][1]=y[i];
        }
        if(active)Multiplexer.this.updateDataConnections();
      }
  }
}