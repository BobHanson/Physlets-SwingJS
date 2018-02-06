package edu.davidson.tools;

import edu.davidson.numerics.Parser;

/**
 * The SDataConnection is responsible for SApplet inter-applet communication protocol.
 *
 * Data connections are created and controlled by the SApplet class.  It is very unlikely that
 * programmers will access data connections directly.
 *
 * Limited inter-applet communication is possible between objects in one applet and objects
 * in another applet if the first object implements the SDataSource interface and the second
 * object implements the SDataListener interface.
 *
 * Inter-applet communication is established using JavaScript to invoke
 * </code>doucment.appletname.makeDataConnection( int sid, int lid, int series, Sting xFunction, String yFunction)</code>.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 1.0, Date: 2000/07/17
 */
public class SDataConnection{ // class to let a source communicate with a listener.
      int series;
      SDataSource ds=null;
      SDataListener dl=null;
      String xStr;
      String yStr;
      double[] x=null;
      double[] y=null;
      Parser     xparser=null;
      Parser     yparser=null;
      String[]   vars=null;
      int        smooth=1;
      int        stride=1;
      int        strideCounter=1;
      double[]   tempX=new double[smooth];
      double[]   tempY=new double[smooth];
      double     lastX=0, lastY=0;
      boolean    block=false;
      double    xmin=0;
      double    xmax=0;
      boolean   windowX=false;
      double    ymin=0;
      double    ymax=0;
      boolean   windowY=false;

      public SDataConnection(SDataSource ds, SDataListener dl, int series, String xStr, String yStr){
          this.series=series;
          this.ds=ds;
          this.dl=dl;
          this.xStr=xStr;
          this.yStr=yStr;
          vars= ds.getVarStrings();
          if(vars==null){
             System.out.println("Error:Data source variables have not been initialized.");
          }else{
            setXStr(xStr);
            setYStr(yStr);
          }
          dl.clearSeries(series);
         // System.out.println("DataConnection made.");

      }

    /**
      *  Have the data connection reject data unless xmin <= x <= xmax.
      *  @param xmin  the minimum value to pass
      *  @param xmax  the maximum value to pass
    */
    public final void setWindowX(double xmin, double xmax) {
      //System.out.println("Window set xmin="+xmin +"  xmax"+xmax);
      if(xmax<xmin){
        double temp=xmax;
        xmax=xmin;
        xmin=temp;
      }
      this.xmin=xmin;
      this.xmax=xmax;
      windowX=true;
    }


    /**
      *  Have the data connection reject data unless ymin <= y <= ymax.
      *  @param ymin  the minimum value to pass
      *  @param ymax  the maximum value to pass
    */
    public final void setWindowY(double ymin, double ymax) {
      if(ymax<ymin){
        double temp=ymax;
        ymax=ymin;
        ymin=temp;
      }
      this.ymin=ymin;
      this.ymax=ymax;
      windowY=true;
    }

  /**
  *
  * Smooth the data being sent to the data connection.
  *
  */
      public void setSmooth(int s){
          if(s<2){smooth=1; tempX=null; tempY=null; return;}
          smooth=s;
          tempX=new double[smooth];
          tempY=new double[smooth];
          for(int i=0; i<smooth; i++){
              tempX[i]=lastX;
              tempY[i]=lastY;
          }
      }
  /**
  *
  * Set the stride of being sent to the data connection.
  *     If stride =1 -> all data is sent.
  *     If stride =2 -> every other data is sent.
  *
  *     @param s int The stride.
  */
      public void setStride(int s){
         stride=Math.max(1,s);
         strideCounter=1;
      }

      public boolean setXStr(String s){
         xStr=s.trim();
         if (xStr.equals("") || xStr.equals("0") ){
             xparser=null;
             return true;
         }
         xparser = new Parser(vars.length);
         for(int i=0; i<vars.length; i++){
             xparser.defineVariable(i+1,vars[i]); // define the variables
         }
         xparser.define(xStr);
         xparser.parse();
         if(xparser.getErrorCode() != Parser.NO_ERROR){
             System.out.println("Failed to parse horizontal datasource): "+xStr);
             System.out.println("Parse error: " + xparser.getErrorString() +
                   " at function 1, position " + xparser.getErrorPosition());
             for(int i=0; i<vars.length; i++){
               System.out.println("vars "+vars[i]);
             }
             return false;
         }else{
             return true;
         }
      }
      public boolean setYStr(String s){
         yStr=s.trim();
         if (yStr.equals("") || yStr.equals("0") ){
             yparser=null;
             return true;
         }
         yparser = new Parser(vars.length);
         for(int i=0; i<vars.length; i++){
             yparser.defineVariable(i+1,vars[i]); // define the variables
         }
         yparser.define(yStr);
         yparser.parse();
         if(yparser.getErrorCode() != Parser.NO_ERROR){
             System.out.println("Failed to parse vertical datasource: "+yStr);
             System.out.println("Parse error: " + yparser.getErrorString() +
                   " at function 1, position " + yparser.getErrorPosition());
             for(int i=0; i<vars.length; i++){
               System.out.println("vars "+vars[i]);
             }
             return false;
         }else{
             return true;
         }
      }

      public void registerDatum(){
          if(block) return;
          strideCounter--;
          if(strideCounter>0) return;
          strideCounter=stride;
          double[][] v=ds.getVariables();
          if(v==null || ds.getVarStrings()==null )return;
          if(ds.getVarStrings()[0].equals("blocked")) return;  // special case to block data.
          if(ds.getVarStrings()[0].equals("surfacedata") && v.length>1){ // special case for contour plots.
            dl.addData(ds,series,null,null);
          }
         // System.out.println("connection ds="+ds.getID()+" dl="+dl.getID());
          int numPts= v.length;
          if(numPts==1){ // we only have one datum
              if (xparser!=null) lastX=xparser.evaluate(v[0]);
              else lastX=0;
              if (yparser!=null) lastY=yparser.evaluate(v[0]);
              else lastY=0;
              if(windowX && (lastX<xmin || lastX>xmax))return;
              if(windowY && (lastY<ymin || lastY>ymax))return;
              if(smooth>1){  // check for data smoothing.
                 double sumx=0, sumy=0;
                 for(int i=1; i<smooth; i++){
                     tempX[i-1]=tempX[i];
                     tempY[i-1]=tempY[i];
                     sumx+= tempX[i];
                     sumy+= tempY[i];
                 }
                 tempX[smooth-1]=lastX;
                 tempY[smooth-1]=lastY;
                 sumx+= lastX;
                 sumy+= lastY;
                 //System.out.println("In smooth Datum.  last x=" + lastX + " y="+lastY);
                // System.out.println("In smooth Datum.  sum x=" + sumx/smooth + " y="+sumy/smooth);
                 dl.addDatum(ds,series,sumx/smooth,sumy/smooth);
              } else dl.addDatum(ds, series,lastX,lastY);
              //System.out.println("In Register Datum.  Data x=" + xx + " y="+yy);
              return;
          }
          if(x==null || x.length!= numPts) x= new double[numPts];
          if(y==null || y.length!= numPts)y= new double[numPts];
          for(int i=0; i<numPts;i++){
              if (xparser!=null) x[i]=xparser.evaluate(v[i]);
              else x[i]=0;
              if (yparser!=null) y[i]=yparser.evaluate(v[i]);
              else y[i]=0;
          }
          if(windowX ){
            double[][] newData=windowx(x,y);
            x=newData[0];
            y=newData[1];
          }
          if(windowY ){
            double[][] newData=windowy(x,y);
            x=newData[0];
            y=newData[1];
          }
          if(smooth>2){
              x=smoothData(x);
              y=smoothData(y);
          }
          dl.addData(ds,series,x,y);
      }

      private double[][] windowx( double[] x, double[] y){
          int numPts=x.length;
          for(int i=0; i<numPts; i++){
            if( (x[i]<xmin) || (x[i] >xmax) ){
              System.arraycopy(x,i+1,x,i,numPts-i-1);
              System.arraycopy(y,i+1,y,i,numPts-i-1);
              numPts--;
              i--;
            }
          }
          double[][] newData=new double[2][numPts];
          System.arraycopy(x,0,newData[0],0,numPts);
          System.arraycopy(y,0,newData[1],0,numPts);
          return newData;
      }

      private double[][] windowy( double[] x, double[] y){
          int numPts=x.length;
          for(int i=0; i<numPts; i++){
            if( (y[i]<ymin) || (y[i] >ymax) ){
              System.arraycopy(x,i+1,x,i,numPts-i-1);
              System.arraycopy(y,i+1,y,i,numPts-i-1);
              numPts--;
              i--;
            }
          }
          double[][] newData=new double[2][numPts];
          System.arraycopy(x,0,newData[0],0,numPts);
          System.arraycopy(y,0,newData[1],0,numPts);
          return newData;
      }


      private double[] smoothData( double[] x){
          int numPts= x.length;
          int delta=smooth/2;
          double[] newX= new double[numPts];
          for(int i=delta; i<numPts-delta; i++){
             double sum=0;
             for(int j=-delta; j<=delta; j++){
                sum+=x[i+j];
             }
             newX[i]=sum/(2*delta+1);
          }
          return newX;
      }

      public void clearData(){
          dl.clearSeries(series);
      }

      public void deleteData(){

          dl.deleteSeries(series);
      }

      final public SDataSource getDataSource(){return ds;}
      final public SDataListener getDataListener(){return dl;}


}
