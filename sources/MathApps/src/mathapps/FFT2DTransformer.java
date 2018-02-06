
/**
 * Title:        Math Physlets<p>
 * Description:  <p>
 * Copyright:    Copyright (c) W. Christian<p>
 * Company:      Physlets<p>
 * @author W. Christian
 * @version 1.0
 */
package mathapps;

import edu.davidson.tools.*;
import jnt.fft.*;

public class FFT2DTransformer implements Runnable, SDataSource,SDataListener {
    private  Thread transformThread;         // Controls wait state. Thread will enter a wait state after it starts since this is false.
    //private boolean abort=false;            // Controls wait state. Thread will enter a wait state after it starts since this is false.
    private Object runLock=new Object();      // the dummy object to lock for the running thread.
    private double[][] tempData=null;
    private double[][] dataArray=null;
    private boolean running=false;
    private float[] data=null;
    private float[] newdata=null;
    private int fftRowPts,fftColPts;        // the number of points to be transformed.  Must be even.
    private int outRowPts,outColPts;        // the number of points in the output array.
    private boolean showDC=true;
    private RealFloat2DFFT_Even  fft = null;
    private  ArrayFunction evenArray=null;
    private  ArrayFunction oddArray=null;
    private double minVal, maxVal;
    private double outScale=0;
    private int gutterPts=0,newGutterPts=0;

    String[] varStrings= new String[]{"surfacedata"};
    double[][] ds= null; //new double[xPts][colPts];  // the datasource state variable
    double[][] ds2= null; //new double[xPts][colPts];  // temp variables for unpacking
    SApplet owner=null;


    public FFT2DTransformer(SApplet o) {
        owner=o;
        transformThread = new Thread(this);
        transformThread.start();     // thread will start but enter a wait state.
        SApplet.addDataSource(this);
        SApplet.addDataListener(this);
    }

  /**
   * Destroys the thread and clean up resources.
   *
   * Call when the applet is unloaded from memeory and the object is no longer needed.
   *
   */
  public void destroy() {
       synchronized (runLock) { // resume the paused thread.
          transformThread=null;
          running=true;  // force the thread to run.
          runLock.notify();
      }
  }

  /**
 * Delete the data connections and set the time to zero.
 *
 */
  public void setDefault(){
      evenArray=null;
      oddArray=null;
      gutterPts=0;
      outScale=0;
  }

/**
 * Scale the output array.
 *
 * The amplitude of the output array will be scaled such that the largest
 * value will equal the scale.
 *
 * Setting the scale to zero will disable the scale feature.
 *
 */
  public void setFFTScale(double scale){
    scale=Math.max(0,scale);
    outScale=scale;
  }

/**
 * Decrease the size of the ouptut array by dropping high frequencies.
 *
 * The number of gutter points will determine the number of high frequency points to drop.
 * The resulting grid will be centered about zero frequency.
 *
 * Setting the gutter should be done after the number of grid points has been set.
 * The gutter cannot be greater than the xgrid/2 or the ygrid/2.
 * Zero will disable clipping.
 *
 * @param pts the number of gutter points
 */
  public void setGutter(int pts){
          newGutterPts=pts;
  }

  public int getGutter(){
          return gutterPts;
  }

  public  int getEvenID(){
      if(evenArray!=null)return evenArray.getID();
      evenArray=new ArrayFunction(fftRowPts+1,fftColPts+1);
      return evenArray.getID();
   }

   public int getOddID(){
      if(oddArray!=null)return oddArray.getID();
      oddArray=new ArrayFunction(fftRowPts+1,fftColPts+1);
      return oddArray.getID();
   }


  /**
   * The implementation of <code>Runnable</code> interface.
   * Performs surface plotting as a background process at a separate thread.
   */
  public void run(){
        while (transformThread!=null){
          synchronized(runLock){
              while(!running) try{ // wait for new data
                  runLock.wait();    // let others get the lock.
              }catch(InterruptedException ie){}
          }
          running=false;
          doTransform();
          try{Thread.sleep(20);}catch (InterruptedException e){}
        }
  }

  public void setDataArray(double[][] d){
    synchronized (runLock) {
      dataArray=d;
      running=true;
      runLock.notify();
    }
  }

  private void doTransform(){
    if(checkArrays()){  // see if there is valid data
          packData();
          transform();
          unpackData();
          owner.updateDataConnections();
          //System.out.println("Transform Done:"+this.fftColPts);
    }
  }

/**
 *    check for valid data an allocate array memory
 *
 * @return true if valid data exists
 */
  private boolean checkArrays(){
    tempData=dataArray;
    if(tempData==null) return false;
    int rowPts=tempData.length;
    int colPts=tempData[0].length;
    gutterPts=newGutterPts;

    fftRowPts=rowPts-rowPts%2;  // make sure fft points is even.  Drop the last point if it is odd.
    fftColPts=colPts-colPts%2;  // make sure fft points is even.  Drop the last point if it is odd.

    if(fft==null || fft.getNRows() != fftRowPts || fft.getNCols() != fftColPts)
        fft = new RealFloat2DFFT_Even(fftRowPts,fftColPts);
    if(data==null || fftRowPts*(fftColPts+2) != data.length) data=new float[fftRowPts*(fftColPts+2)];

    outRowPts=fftRowPts-2*gutterPts+1;
    outColPts=fftColPts-2*gutterPts+1;

    if(outRowPts<=1 || outColPts<=1){
        System.out.println("Gutter is too large.");
        return false;
    }

    if(ds==null || ds.length!=outRowPts || ds[0].length!=outColPts ){
            ds=new double[outRowPts][outColPts];  // the datasource state variables
            if(evenArray!=null)evenArray.setNum(outRowPts,outColPts);
            if(oddArray!=null)oddArray.setNum(outRowPts,outColPts);
    }
    if(ds2==null || ds2.length!=fftRowPts || ds2[0].length!=2*fftColPts ){
            ds2=new double[fftRowPts][2*fftColPts];  // the datasource state variables
    }
    if(newdata==null || newdata.length!= 2*fftRowPts*fftColPts) newdata= new float[2*fftRowPts*fftColPts];
    return true;
  }

  /**
 *    Evaluate the function on the grid.
 *
 */
    synchronized void transform(){
        int rowSpan=fftColPts+2;
        fft.transform(data);
        data=fft.toWraparoundOrder(data, rowSpan,newdata);
        double norm=1;
        int nrows=fftRowPts;
        int ncols=fftColPts;
        int im = nrows/2;
        int jm = ncols;
        //if(!showDC)data[0]=0;  // set the dc component to zero.
        if(outScale>0){
            minVal=0;
            maxVal=0;
            for(int i=1; i<data.length; i++){
                minVal=Math.min(minVal,data[i]);
                maxVal=Math.max(maxVal,data[i]);
            }
            norm=outScale*Math.max(Math.abs(minVal),Math.abs(maxVal) );
            if(norm==0) norm=1;
        }else norm=fftRowPts*fftColPts/2;
        minVal=0;
        maxVal=0;
        for(int i=0; i<nrows/2; i++){
          for(int j=0; j<ncols; j++){
            ds2[i   ][j   ] = data[2*ncols*(im + i) + (jm + j)]/norm;
            ds2[i+im][j+jm] = data[2*ncols*(     i) +       j ]/norm;
            ds2[i+im][j   ] = data[2*ncols*(     i) + (jm + j)]/norm;
            ds2[i   ][j+jm] = data[2*ncols*(im + i) +       j ]/norm;
        }}
    }

  private void packData(){
    int offset;
    int rowSpan=fftColPts+2;
    for( int i=0; i<fftRowPts; i++){
        offset=i*rowSpan;
        for( int j=0; j<fftColPts; j++){
            data[offset+j]=(float)tempData[i][j];
        }
        data[offset+fftColPts]=0;  // the two extra points
        data[offset+fftColPts+1]=0;
    }
  }

  private void unpackData(){
        int nrows=fftRowPts+1;
        int ncols=fftColPts+1;
        for(int i=1; i<fftRowPts-2*gutterPts; i++){
          for(int j=1; j<fftColPts-2*gutterPts; j++){
            ds[i][j] = Math.sqrt(ds2[i+gutterPts][2*(j+gutterPts)]*ds2[i+gutterPts][2*(j+gutterPts)]+ds2[i+gutterPts][2*(j+gutterPts)+1]*ds2[i+gutterPts][2*(j+gutterPts)+1]);
            if(evenArray!=null)evenArray.v[i][j]=ds2[i+gutterPts][2*(j+gutterPts)];
            if(oddArray!=null)oddArray.v[i][j]=ds2[i+gutterPts][2*(j+gutterPts)+1];
        }}
        if(!showDC)
          ds[nrows/2][ncols/2]=(ds[nrows/2-1][ncols/2]+ds[nrows/2+1][ncols/2]+ds[nrows/2][ncols/2-1]+ds[nrows/2-1][ncols/2+1])/4;  // set the dc component to zero.
    }


  /**
 *  Data source method.
 */
    public double[][] getVariables(){
        return ds;
    }
/**
 *  Data source method.
 */
    public String[]   getVarStrings() {return varStrings;}
    // public int getID(){return this.hashCode();}   already in superclass
/**
 *  Data source method.
 */
    public void setOwner(SApplet o){owner=o; }

/**
 *  Data source method.
 */
    public SApplet getOwner( ){return owner;}
/**
 *  Data source method.
 */
    public int getID( ){return this.hashCode();}

 /**
 *  Data listener method.
 */
   public void addDatum(SDataSource s, int id, double x, double y ){
      System.out.println("addDatum Series not supported.");
      System.out.println("x="+x+"    y="+y);

  }
 /**
 *  Data listener method.
 */
  public void addData(SDataSource s, int id, double x[], double y[] ){
       String[] varStrings;
       double[][] dataArray=null;
       //System.out.println("Adding Data Series="+id);
       varStrings=s.getVarStrings();
       if(varStrings==null || varStrings[0]==null || varStrings[0].equals("surfacedata")){
         dataArray=s.getVariables();
         if(dataArray==null ){
            System.out.println("Data array not found in FFT2D");
            return;
         }
         setDataArray(dataArray);
       }

  }
 /**
 *  Data listener method.
 */
  public void deleteSeries(int id){
        //stop();
       //System.out.println("Delete Series="+id);

  }
/**
 *  Data listener method.
 */
  public void clearSeries(int id){
       // stop();
        //System.out.println("Clear Series="+id);
  }



// inner class for sin and cos terms.
    class ArrayFunction extends Object implements SDataSource {
      String[] varStrings= new String[]{"surfacedata"};
      double[][] v=null;
      ArrayFunction(int row, int col){
        v=new double[row][col];
        try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
      }

      void setNum(int row, int col){
        if(v!=null && v.length==row && v[0].length==col) return;
        v=new double[row][col];
      }

      public void setOwner(SApplet owner){;}
      public SApplet getOwner(){return FFT2DTransformer.this.owner;}

      public String[]   getVarStrings(){ return varStrings;}

      public final int getID(){return hashCode();}

      public double[][] getVariables(){ return v;}

  }  // end of ArrayFunction

}