package edu.davidson.surfaceplotter;
//import java.awt.*;
import edu.davidson.tools.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;

import a2s.*;

public class SurfacePanel extends Panel implements SDataListener{
  BorderLayout borderLayout1 = new BorderLayout();
  public SurfaceCanvas surfaceCanvas =  new SurfaceCanvas();
  SApplet sapplet=null;
  boolean autoRefresh=true;

  public SurfacePanel(){
     this(null);
  }

  public SurfacePanel(SApplet sa) {
    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    sapplet=sa;
    SApplet.addDataListener(this); 
    addMouseMotionListener(new PoissonPanel_mouseMotionAdapter(surfaceCanvas));
    addMouseListener(new SurfacePanel_mouseAdapter(surfaceCanvas));
  }
  
  class SurfacePanel_mouseAdapter extends java.awt.event.MouseAdapter {
	  SurfaceCanvas adaptee;

	  SurfacePanel_mouseAdapter(SurfaceCanvas adaptee) {
	    this.adaptee = adaptee;
	  }
	  
	  
      public void mousePressed(MouseEvent e) { 
          adaptee.myMouseDown( e, e.getX(), e.getY());
      } 
      public void mouseDragged(MouseEvent e) { 
          adaptee.myMouseDrag( e, e.getX(), e.getY());
      }
      public void mouseEntered(MouseEvent e) { 
          adaptee.myMouseEnter( e, e.getX(), e.getY());
      } 
      public void mouseReleased(MouseEvent e) { 
          adaptee.myMouseUp( e, e.getX(), e.getY());
          adaptee.startPlot();
      } 
	}
  
  class PoissonPanel_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
	  SurfaceCanvas adaptee;

	  PoissonPanel_mouseMotionAdapter(SurfaceCanvas adaptee) {
	    this.adaptee = adaptee;
	  }
      public void mouseDragged(MouseEvent e) { 
          adaptee.myMouseDrag( e, e.getX(), e.getY());
          // BH: why this? adaptee.startPlot();
          // let adaptee do that itself if needed
      }
	}

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    this.add(surfaceCanvas, BorderLayout.CENTER);
  }
  public boolean setFunction1(String funcStr){
      stop();
      if(funcStr==null || funcStr.equals("")){
         funcStr=null;
      }
      surfaceCanvas.dataGenerator.setDataArray(null);
      surfaceCanvas.controller.setFunction1(funcStr);
      surfaceCanvas.dataGenerator.createData();
      surfaceCanvas.startPlot();
      return true;
  }

  public boolean setFunction2(String funcStr){
      stop();
      if(funcStr==null || funcStr.equals("")){
         funcStr=null;
      }
      surfaceCanvas.controller.setFunction2(funcStr);
      surfaceCanvas.dataGenerator.createData();
      surfaceCanvas.startPlot();
      return true;
  }

  public void setScaleFactor(int sf){
      surfaceCanvas.projector.set2DScaling(sf);
  }

  public void setDistance(int dis){
     surfaceCanvas.projector.setDistance(dis);
  }

  public void setRotationAngle(double a){
     surfaceCanvas.projector.setRotationAngle((float)a);
  }

  public double getRotationAngle(){
     return surfaceCanvas.projector.getRotationAngle();
  }

  public void setElevationAngle(double a){
     surfaceCanvas.projector.setElevationAngle((float)a);
  }

  public double getElevationAngle(){
    return  surfaceCanvas.projector.getElevationAngle();
  }

  public void startRotate(){
     surfaceCanvas.startRotation();
  }
  public void stopRotate(){
     surfaceCanvas.stopRotation();
  }



  // Data listener methods
  public int getID(){return this.hashCode();}
  public void setOwner(SApplet owner){sapplet=owner;}    // cannot be set if the applet itself is the owner.
  public SApplet getOwner(){return sapplet;}

  public void addDatum(SDataSource s, int id, double x, double y ){
      System.out.println("addDatum Series not supported.");
      System.out.println("x="+x+"    y="+y);

  }

  public void addData(SDataSource s, int id, double x[], double y[] ){
       String[] varStrings;
       double[][] dataArray=null;
       varStrings=s.getVarStrings();
       if(varStrings==null || varStrings[0]==null || varStrings[0].equals("surfacedata")){
         dataArray=s.getVariables();
         if(dataArray[0].length!=dataArray.length){
            System.out.println("Surface data array is not square");
            return;
         }
       this.setGridPts(dataArray.length-1);
       surfaceCanvas.dataGenerator.setDataArray(dataArray);
       surfaceCanvas.dataGenerator.createData();
       }
       surfaceCanvas.startPlot();
       //setAutoRefresh(autoRefresh);
  }

  public void addData(int id, double dataArray[][] ){
       if(dataArray==null){
            System.out.println("null data");
            return;
       }
       if(dataArray[0].length!=dataArray.length){
            System.out.println("Surface data array is not square");
            return;
       }
       this.setGridPts(dataArray.length-1);  // this will check to see if the array size is correct and stop the threads IF it needs to resize.
       surfaceCanvas.dataGenerator.setDataArray(dataArray);
       //surfaceCanvas.dataGenerator.externalData=dataArray;
       surfaceCanvas.dataGenerator.createData();
       surfaceCanvas.startPlot();
  }

  public void deleteSeries(int id){
        stop();
        surfaceCanvas.dataGenerator.setDataArray(null);
  }
  public void clearSeries(int id){
        stop();
        surfaceCanvas.dataGenerator.setDataArray(null);
  }

  /**
   * Destroys the threads and clean up resources.
   *
   * Call when the appelt is unloaded from memeory.
   *
   */
  public void destroyThread() {
     try{
       surfaceCanvas.destroyThread();
       surfaceCanvas.dataGenerator.destroyThread();
      } catch (Exception ex){}
  }

  public double getMinX(){
      return surfaceCanvas.controller.getXMin();
  }
  public double getMaxX(){
      return surfaceCanvas.controller.getXMax();
  }
  public double getMinY(){
      return surfaceCanvas.controller.getYMin();
  }
  public double getMaxY(){
      return surfaceCanvas.controller.getYMax();
  }
  public double getMinZ(){
      return surfaceCanvas.controller.getZMin();
  }
  public double getMaxZ(){
      return surfaceCanvas.controller.getZMax();
  }

  public void setMinX(double val){
      surfaceCanvas.controller.xmin=(float) val;
  }
  public void setMaxX(double val){
      surfaceCanvas.controller.xmax=(float) val;
  }
  public void setMinY(double val){
      surfaceCanvas.controller.ymin=(float) val;
  }
  public void setMaxY(double val){
      surfaceCanvas.controller.ymax=(float) val;
  }
  public void setMinZ(double val){
      surfaceCanvas.controller.zmin=(float) val;
  }
  public void setTime(double t){
          surfaceCanvas.setTime(t);
  }
  public void resetTime(){
      surfaceCanvas.resetTime();
  }
  public void setMaxZ(double val){
      surfaceCanvas.controller.zmax=(float) val;
  }
  public int getMode(){return surfaceCanvas.controller.plotMode;}
  public void setMode(int val){surfaceCanvas.controller.plotMode=val;}


  public void setDefault(){
     stop();
     surfaceCanvas.controller.setDefault();
     setAutoRefresh(autoRefresh);
  }

  public void setContour(){
      if(surfaceCanvas.contour && !surfaceCanvas.density) return;  // contour already set
     // stop();
      //surfaceCanvas.setDensity(false);
      surfaceCanvas.setContour(true);
      surfaceCanvas.destroyImage();
      surfaceCanvas.repaint();
  }
  public boolean isContour(){return surfaceCanvas.contour;}

  public void setDensity(){
      if(!surfaceCanvas.contour && surfaceCanvas.density) return;  // density already set
     // stop();
      //surfaceCanvas.setContour(false);
      surfaceCanvas.setDensity(true);
      surfaceCanvas.destroyImage();
      surfaceCanvas.repaint();
  }
  public boolean isDensity(){return surfaceCanvas.density;}

  public void setGutter(int gutter){
      surfaceCanvas.gutter=gutter;
  }

  public void setThreeD(){
      if(!surfaceCanvas.contour && !surfaceCanvas.density && !surfaceCanvas.noDrawing) return;  // thread already set
      //stop();
      surfaceCanvas.setContour(false);
      surfaceCanvas.setDensity(false);
      surfaceCanvas.destroyImage();
      surfaceCanvas.repaint();
  }

  public void setNoDrawing(){
      surfaceCanvas.setNoDrawing(true) ;
      surfaceCanvas.destroyImage();
      surfaceCanvas.repaint();
  }
  public boolean isThreeD(){return !(surfaceCanvas.contour || surfaceCanvas.density);}

  public void setShowBox(boolean val){
      surfaceCanvas.controller.boxed=val;
  }

  public void setShowMesh(boolean val){
      surfaceCanvas.controller.showMesh=val;
  }

  public void setAutoRefresh(boolean val){
    autoRefresh=val;
    if(autoRefresh){
      surfaceCanvas.destroyImage();
      surfaceCanvas.repaint();
    }else stop();    // stop both the calculation and the painting.
  }

  // set the scale on the z axis to have the same aspect ratio as the x and y.
  public void setScaledBox(boolean val){
      surfaceCanvas.controller.autoScale=val;
  }

 // set the scale to autoscale.
 // Note-- the original autoscalez paramter set the aspect ratio to be the same for all axes.
  public void setAutoscaleZ(boolean val){
      surfaceCanvas.controller.autoScaleHeigth=val;
  }

  public void setShowFaceGrids(boolean val){
      surfaceCanvas.controller.showFaceGrids=val;
  }

  public void setShowXYticks(boolean val){
      surfaceCanvas.controller.displayXYTicks=val;
  }

  public void setShowZticks(boolean val){
      surfaceCanvas.controller.displayZTicks=val;
  }


 /**
 *  Check to see if the the vertex array is the correct size.
 *  All threads are stopped and memory is allocated if the size is not correct.
 *  The vertex array will be (pts+1)*(pts+1) in size.
 *
 *  @param pts the size
 */
  public void setGridPts(int pts){
    // set the calc divisions and hence the vertex array size.
    // Note:  the vertex array is (calc_divisions+1)^2 in size.
    if(pts==surfaceCanvas.controller.calc_divisions)return;
    synchronized(surfaceCanvas.controller){
        //stop all threads.
        surfaceCanvas.dataGenerator.interrupt(); //surfaceCanvas.setDataAvailability(false);    this is done in the abort method.
        surfaceCanvas.interrupt(); // stop calculating
        surfaceCanvas.dataGenerator.setDataArray(null);
        surfaceCanvas.controller.calc_divisions=pts;
        surfaceCanvas.controller.disp_divisions=pts;
    }
    if(autoRefresh) surfaceCanvas.repaint();   // repaint with null data.
  }

  public void setNumLevels(int num){
    if(surfaceCanvas.controller.numContourLines==num) return;
    synchronized(surfaceCanvas.controller){
        surfaceCanvas.dataGenerator.interrupt(); //surfaceCanvas.setDataAvailability(false);    this is done in the abort method.
        surfaceCanvas.interrupt(); // stop calculating
        surfaceCanvas.controller.numContourLines=num;
    }
    if(autoRefresh) surfaceCanvas.repaint();   // repaint with null data.

  }

  public void setGraphFont(Font font){
      surfaceCanvas.defaultFont=font;
  }

  void stop(){  // stop all threads
    surfaceCanvas.dataGenerator.interrupt(); //surfaceCanvas.setDataAvailability(false);    this is done in the abort method.
    surfaceCanvas.interrupt(); // stop calculating
  }

  /*
  public void addTestData( ){
       String[] varStrings;
       double[][] dataArray=new double[32][32];
       for(int i=0; i<32; i++)
       for(int j=0; j<32; j++)  dataArray[i][j]=i*j/(32.0*32.0);
       //System.out.println("Adding test Data Series");
         if(dataArray[0].length!=dataArray.length){
            System.out.println("Surface data array is not square");
            return;
         }
     addData(1, dataArray ) ;
  }    */

}
