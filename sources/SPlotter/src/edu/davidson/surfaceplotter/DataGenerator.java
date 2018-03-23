package edu.davidson.surfaceplotter;
import edu.davidson.numerics.Parser;

public class DataGenerator implements Runnable {

  private Thread calcThread;                // calcThread thread
  private Parser parser1,parser2;           // the function parsers
  private Object runLock=new Object();             // the dummy object to lock.
  private boolean shouldRun=true;           // keeps the thread running.
  private boolean abort=false;              // cut the calculation short.
  private boolean running=false;            // thread will enter a wait state after it starts.
  SurfaceCanvas surfaceCanvas;
  private double[][] externalData=null;
  private boolean newData=false;
  private double[][] newDataArray=null;
  private double time=0;
  private int totalMemory=0;
  private SurfaceVertex[][] vertex=null;
  private SurfaceVertex[][] vertexCalc=null;
  private SurfaceVertex[][] vertexDraw=null;

  public DataGenerator(SurfaceCanvas pp) {
    surfaceCanvas=pp;
    parser1 = new Parser(3);
    parser1.defineVariable(1,"x");
    parser1.defineVariable(2,"y");
    parser1.defineVariable(3,"t");
    parser2 = new Parser(3);
    parser2.defineVariable(1,"x");
    parser2.defineVariable(2,"y");
    parser2.defineVariable(3,"t");
    calcThread = new Thread(this);
    //calcThread.setPriority(Thread.currentThread().getPriority()-1);
    //calcThread.setDaemon(true);
    if(!surfaceCanvas.isJS) calcThread.start();  // thread will enter a wait state after it starts.
  }

  boolean isExternalData(){
      if(externalData==null) return false;
      else return true;
  }

  void setDataArray(double[][] dataArray){
      if(dataArray==null &&  externalData==null) return;
      newData=true;
      newDataArray=dataArray;
      if(dataArray!=null) createData();
  }

  /**
	 * The run method passed to the thread.  DO NOT access this method.
  */
  public void run(){
        while (shouldRun){
          synchronized(runLock){
              while(running==false) try{
                  runLock.wait();    // let others get the lock.
              }catch(Exception ie){}
              running=false; // force a wait state  at the end of the calculation.
              if(shouldRun)doCalc();
              if(newData){
                  newData=false;
                  running=true;
                  externalData=newDataArray;
                      ////  wait for the drawing
              }
          }
        	  try{Thread.sleep(20);}catch (Exception e){}
        }
        //calcThread.stop();
        calcThread=null;
    }
  /**
   * Parses defined functions and calculates surface vertices
   */
   void doCalc() {
    float   stepx, stepy, x, y, v;
    float   xi,xx,yi,yx;
    float   min, max;
    boolean f1, f2;
    int     i,j,k;

    // image conversion

    int[]   pixels = null;
    int     imgwidth = 0;
    int     imgheight = 0;

    try {
      xi =surfaceCanvas.controller.getXMin(); // Float.valueOf(setting_panel.getXMin()).floatValue();
      yi =surfaceCanvas.controller.getYMin(); //Float.valueOf(setting_panel.getYMin()).floatValue();
      xx =surfaceCanvas.controller.getXMax(); // Float.valueOf(setting_panel.getXMax()).floatValue();
      yx =surfaceCanvas.controller.getYMax(); // Float.valueOf(setting_panel.getYMax()).floatValue();
      if ((xi >= xx) || (yi >= yx)) throw new NumberFormatException();
    }
    catch(NumberFormatException e) {
      surfaceCanvas.setMessage("Error in ranges");
      return;
    }

    surfaceCanvas.setRanges(xi,xx,yi,yx);

    surfaceCanvas.setMessage("parsing ...");

    f1 = surfaceCanvas.controller.isPlotFunction1();
    if (f1 && externalData==null) {
        parser1.define(surfaceCanvas.controller.getFunction1Definition());
        parser1.parse();

        if (parser1.getErrorCode() != Parser.NO_ERROR) {
          surfaceCanvas.setMessage("Parse error: " + parser1.getErrorString() +
                     " at function 1, position " + parser1.getErrorPosition());
          //setErrorPosition(1,parser1.getErrorPosition());
          return;
        }
     }

     f2 = surfaceCanvas.controller.isPlotFunction2();
     if (f2) {
        parser2.define(surfaceCanvas.controller.getFunction2Definition());
        parser2.parse();

        if (parser2.getErrorCode() != Parser.NO_ERROR) {
          surfaceCanvas.setMessage("Parse error: " + parser2.getErrorString() +
                     " at function 2, position " + parser2.getErrorPosition());
          //setErrorPosition(2,parser2.getErrorPosition());
          return;
        }
     }

    if (!f1 && !f2 && externalData==null) {
        surfaceCanvas.setMessage("No function selected");
        return;
    }

    Thread.yield();
    if(abort) return;
    int calc_divisions = surfaceCanvas.controller.getCalcDivisions();
    if( externalData!=null && ((calc_divisions+1!=externalData.length) || (calc_divisions+1!=externalData[0].length)) ){
        // System.out.println("Wrong array length: exiting doCalc in DataGenerator.");
        return;
    }

    // the following is not needed since we have to vertex matrices and one of them should always be valid.
    //surfaceCanvas.setDataAvailability(false);

    //func1calc = f1; func2calc = f2;
    //if (func1calc)func1name = new String(function_panel.getFunction1Definition());
    //if (func2calc) func2name = new String(function_panel.getFunction2Definition());

    stepx = (xx - xi) / calc_divisions;
    stepy = (yx - yi) / calc_divisions;

    int newMemory = (calc_divisions+1)*(calc_divisions+1);
    boolean showf1=f1 || (externalData!=null); // added to plot non-parser data.
    //if(true || newMemory!=totalMemory || vertex==null){  // Changed by W. Christian 
    if(true){
        totalMemory=newMemory;
        vertexCalc= allocateMemory(showf1,f2,totalMemory);
        vertexDraw= allocateMemory(showf1,f2,totalMemory);
        vertex=vertexCalc;
    }
    if (vertex == null) return;

    max = Float.NaN;
    min = Float.NaN;
    surfaceCanvas.controller.setMinimumResult("");
    surfaceCanvas.controller.setMaximumResult("");

   // surfaceCanvas.destroyImage(); // move to end of method.

    i = 0; j = 0; k = 0; x = xi; y = yi;

    float xfactor = 20/(xx-xi);
    float yfactor = 20/(yx-yi);
      while (i <= calc_divisions) {
        if (f1 && externalData==null) {
          parser1.setVariable(1,x);
          parser1.setVariable(2,y);
          parser1.setVariable(3,time);
        }
        if (f2) {
          parser2.setVariable(1,x);
          parser2.setVariable(2,y);
          parser2.setVariable(3,time);
        }
        while (j <= calc_divisions) {
          Thread.yield();
          if(abort) return;
          if (showf1) {
            if(externalData!=null) v=(float)externalData[i][j];  // are things flipped?
              else if(f1) v = (float)parser1.evaluate();
              else v=0;
            if (Float.isInfinite(v)) v = Float.NaN;
            if (!Float.isNaN(v)) {
              if (Float.isNaN(max) || (v > max)) max = v;
                else if (Float.isNaN(min) || (v < min)) min = v;
            }
            vertex[0][k] = new SurfaceVertex((x-xi)*xfactor-10,
                                             (y-yi)*yfactor-10,v,surfaceCanvas);
          }
          if (f2) {
            v = (float)parser2.evaluate();
            if (Float.isInfinite(v)) v = Float.NaN;
            if (!Float.isNaN(v)) {
              if (Float.isNaN(max) || (v > max)) max = v; else
              if (Float.isNaN(min) || (v < min)) min = v;
            }
            vertex[1][k] = new SurfaceVertex((x-xi)*xfactor-10,
                                             (y-yi)*yfactor-10,v,surfaceCanvas);
          }
          j++; y += stepy;
          if (f1) parser1.setVariable(2,y);
          if (f2) parser2.setVariable(2,y);
          k++;
          //surfaceCanvas.setMessage("Calculating : " + k*100/total + "% completed");
        }
        if(abort) return;
        j = 0; y = yi; i++; x += stepx;
      }

    if(abort) return;
    //surfaceCanvas.controller.setMinimumResult(Float.toString(min));
    //surfaceCanvas.controller.setMaximumResult(Float.toString(max));
    surfaceCanvas.controller.setMinimumResult(min);
    surfaceCanvas.controller.setMaximumResult(max);

    SurfaceVertex[][] canvasVertex=surfaceCanvas.setNewData( vertex);  // new method using two arrays.
    if(canvasVertex==vertexCalc){ // swap since the canvas is now using the calc vertex for the drawing.
        vertexCalc=vertexDraw;
        vertexDraw=canvasVertex;
        vertex=vertexCalc;
    }
  }
    /**
   * Allocates Memory
   */

  private SurfaceVertex[][] allocateMemory(boolean f1, boolean f2, int total) {
       SurfaceVertex[][] vertex = null;
    // Releases memory being used

   // surfaceCanvas.setValuesArray(null);

    /* The following program:

       SurfaceVertex[][] vertex = new SurfaceVertex[2][];

       if (f1) vertex[0] = new SurfaceVertex[total];
       if (f2) vertex[1] = new SurfaceVertex[total];


       Didn't work with my Microsoft Internet Explorer v3.0b2.
       It resulted in a "java.lang.ArrayStoreException"  :(

     */

    try {
      vertex = new SurfaceVertex[2][total];
      if (!f1) vertex[0] = null;
      if (!f2) vertex[1] = null;
    }
    catch(OutOfMemoryError e) {
      surfaceCanvas.setMessage("Not enough memory");
    }
    catch(Exception e) {
      surfaceCanvas.setMessage("Error: " + e.toString());
    }
    return vertex;
  }

  public void createData(){
     synchronized (runLock) {   // look for a wait state.
          running=true;
          runLock.notify();
     }
  }

  /**
   * Destroys the thread and clean up resources.
   *
   * Call when the appelt is unloaded from memeory and the object is no longer needed.
   *
   */
  public void destroyThread() {
       calcThread.stop();  // this is the only thing that seems to kill the thread
       shouldRun=false;
       abort=true;
       calcThread.interrupt();
       //synchronized (runLock) {   // look for a wait state.
          running=true;
          runLock.notify();
       //}
  }

  void setTime(double t){
     if(time==t) return;
     synchronized (runLock) {   // look for a wait state.
          time=t;
          running=true;
          runLock.notify();
     }
  }
  void interrupt(){
      abort=true;   // abort the calculation and get into a wait state as fast as we can.
      synchronized (runLock) {   // wait for the calculation to finish and the thread to go into a wait state.
         surfaceCanvas.setDataAvailability(false);
         abort=false;
      }
  }


}