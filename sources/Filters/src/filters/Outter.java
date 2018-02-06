package filters;

import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;

/**
 * The Outter Physlet accepts data from two data sources and creates an array using the outter product.
 *
 * The data sources must have the same number of datum.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 0.1, Date: 2000/08/09
 */
public class Outter extends SApplet implements SDataSource {
  boolean isStandalone = false;
  String[] varStrings= new String[]{"surfacedata"};
  double[][] ds=null;   // the datasource state variables

  String[] parserVars=null;         // the parser variables
  double[] parserValues=null;

  Parser parser=null;
  String func="0";


  Listener listenerOne=null;
  Listener listenerTwo=null;

  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  public Outter() {
  }

  //Initialize the applet
  public void init() {
    try {
        jbInit();
     }
     catch(Exception e) {
        e.printStackTrace();
     }
    parser = new Parser(6);
    parserVars=new String[6];
    parserValues=new double[6];
    parserVars[0]="x1";
    parserVars[1]="y1";
    parserVars[2]="y1minus";
    parserVars[3]="x2";
    parserVars[4]="y2";
    parserVars[5]="y2minus";

    func="0";
    parser.defineVariables(parserVars);
    parser.parse();

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
      if(listenerOne==null || listenerTwo==null){
         System.out.println("Warning: no data listeners in Multiplexer Physlet.");
         ds=new double[1][1];
         return ds;
      }
      int len1=listenerOne.lastValues.length;
      int len2=listenerTwo.lastValues.length;
      if(ds==null || ds.length!=len1 || ds[0].length!=len2 ) ds=new double[len1][len2];
      for(int row=0; row<len1; row++){
        parserValues[0]=listenerOne.lastValues[row][0];
        parserValues[1]=listenerOne.lastValues[row][1];
        parserValues[2]=listenerOne.lastValues[len1-row-1][1];
        for(int col=0; col<len2; col++){
            parserValues[3]=listenerTwo.lastValues[col][0];
            parserValues[4]=listenerTwo.lastValues[col][1];
            parserValues[5]=listenerTwo.lastValues[len2-col-1][1];
            ds[row][col]=parser.evaluate(parserValues);
        }
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

      func="0";
      parser.define(func);
      parser.parse();

    }
/**
 * Set the display function.
 *
 * @param funcStr the function
 *
 * @return true if the functions parse correctly
 */
    synchronized public boolean setFunction(String funcStr){
       if(listenerOne==null || listenerTwo==null){
         System.out.println("Create data listeners before you define the output function.");
         return false;
       }
       func=funcStr;
       parser.defineVariables(parserVars);
       parser.define(func);
       parser.parse();
       if(parser.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse function): "+func);
         System.out.println("Parse error in Outter: " + parser.getErrorString() +
                   " at math fuinction, position " + parser.getErrorPosition());
         return false;
       }
       return true;
    }

/**
 *  Get the first data listener
 *
 *  @return the id of the data listener
 */
    synchronized public int getFirstDataListener(){
       if(listenerOne==null )listenerOne = new Listener(1);
       return listenerOne.getID();
    }

/**
 *  Get the first data listener
 *
 *  @return the id of the data listener
 */
    synchronized public int getSecondDataListener(){
       if(listenerTwo==null )listenerTwo = new Listener(2);
       return listenerTwo.getID();
    }

// inner class that listens for data and stores the data in a temporary array.
  class Listener extends Object implements SDataListener{
      int index=0;
      String xStr="x0";
      String yStr="y0";
      boolean active=true;
      double[][] lastValues=new double[1][2];  // the datasource state variables


      public Listener(int i) {
         index=i;
         xStr="x"+i;
         yStr="y"+i;
         try{SApplet.addDataListener(this);}catch (Exception e){e.printStackTrace();}
      }

      public void setOwner(SApplet owner){;}
      public SApplet getOwner(){return Outter.this;}

      public String[]   getVarStrings(){ return varStrings;}
      public final int getID(){return this.hashCode();}

      public void deleteSeries(int id){;}
      public void clearSeries(int id){;}
      synchronized public void addDatum(SDataSource s, int id, double x, double y ){
        lastValues[0][0]=x;
        lastValues[0][1]=y;
        if(active)Outter.this.updateDataConnections();
      }
      synchronized public void addData(SDataSource s,int id, double x[], double y[] ){
        if(x.length!= lastValues.length){
          lastValues=new double[x.length][2];
        }
        for(int i=0; i<x.length; i++){
            lastValues[i][0]=x[i];
            lastValues[i][1]=y[i];
        }
        if(active)Outter.this.updateDataConnections();
      }
  }
}