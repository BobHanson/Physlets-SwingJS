package randomwalk;

import java.awt.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Drawing shows how to draw a grid on the screen for the "Game of Life."
 *
 */
public class WalkPanel extends Panel implements SStepable{
   boolean runMode=false;
   int nrows=25; // the number of rows down a panel
   int ncols=25; // the number of columns across the panel
   int nsteps=25;
   int locationX=ncols/2;
   int locationY=nrows/2;
   double[] count=new double[nsteps];
   double[] num=new double[nsteps];
   int currentStep=0;
   Dimension ps= new Dimension(150,150);
   SGraph graph=null;
   Image osi=null;
   int iwidth=0,iheight=0;
   double xpixPerGrid=0;
   double ypixPerGrid=0;
   SApplet owner=null;
   Histogram histogram=null;

/**
 * Contruct the Drawing.
 *
 */
  public WalkPanel(SApplet owner, SGraph graph){
    this.owner=owner;
    this.graph=graph;
    graph.setSeriesStyle(1,Color.red,false,-3);
    for(int i=0; i<nsteps; i++)num[i]=i;
  }

  void setRowsColsSteps(int r, int c, int s){
    nrows=Math.max(1,r);
    ncols=Math.max(1,c);
    nsteps=Math.max(1,s);
    count=new double[nsteps];
    num=new double[nsteps];
    for(int i=0; i<nsteps; i++)num[i]=i;
    if(osi!=null){
        xpixPerGrid=iwidth/(double)ncols;
        ypixPerGrid=iheight/(double)nrows;
        resetWalker();
    }
  }

 void reset(){
   resetWalker();
   for(int i=0; i<nsteps; i++)count[i]=0;
 }

  private void paintCell(Graphics g, int col, int row){
      int top=(int)(row*ypixPerGrid);
      int left=(int)(col*xpixPerGrid);
      //g.fillRect(left,top,(int)(xpixPerGrid+1 ),(int)(ypixPerGrid+1));
      g.fillOval(left,top,(int)(xpixPerGrid+2 ),(int)(ypixPerGrid+2));
  }

  private void resetWalker(){
       currentStep=0;
       locationX=ncols/2;
       locationY=nrows/2;
       if(osi==null) return;
       Graphics g=osi.getGraphics();
       g.setColor(Color.black);
       g.fillRect(0,0,getBounds().width,getBounds().height);
       g.setColor(Color.green);
       paintCell(g, ncols/2,nrows/2);
       g.setColor(Color.pink);
       paintCell(g, locationX,locationY);
       g.dispose();
  }

  void createImage(){
    if (getSize().width==0||getSize().height==0) return;// image is too small
    iwidth = this.getSize().width;
    iheight = this.getSize().height;
    osi = createImage(iwidth,iheight);   //create an off screen image
    xpixPerGrid=iwidth/(double)ncols;
    ypixPerGrid=iheight/(double)nrows;
    resetWalker();
  }

/**
 * Paint the world.
 *
 * Call this method when you want to paint the screen yourself.
 */
  public void paint(){
      Graphics g=getGraphics();
      if(osi!=null) g.drawImage(osi,0,0,this);    // draw the image onto the visible graph
      g.setColor(Color.green);
      paintCell(g, ncols/2,nrows/2);
      g.dispose();
  }

  public void paint(Graphics g){
       if( osi== null || iwidth != this.getSize().width || iheight != this.getSize().height){
           createImage();
       }
       g.drawImage(osi,0,0,this);    // draw the image onto the visible graph
       g.setColor(Color.green);
       paintCell(g, ncols/2,nrows/2);
   }


/**
 * Step.
 */
   void step(){
      if(osi==null) return;
      Graphics g=osi.getGraphics();
      g.setColor(Color.red);
      paintCell(g, locationX,  locationY);
      int rand=(int) (4*Math.random()); // produces a number between 0 and 3
      switch(rand){
        case 0: locationX=locationX+1; break;
        case 1: locationX=locationX-1; break;
        case 2: locationY=locationY+1; break;
        case 3: locationY=locationY-1; break;
        //default: ;
      }
      g.setColor(Color.pink);
      paintCell(g, locationX,  locationY);
      g.dispose();
      paint();
      currentStep++;
      if(currentStep>=nsteps){
        g=osi.getGraphics();
        g.setColor(Color.white);
        paintCell(g, locationX,  locationY);
        g.dispose();
        paint();
        locationX=locationX-ncols/2;
        locationY=locationY-nrows/2;
        int r=(int)Math.sqrt(locationX*locationX+locationY*locationY);
        if(r<nsteps){
            count[r]=count[r]+1;
            graph.clearSeries(1);
            graph.addData(1,num,count);
        }
        resetWalker();
        owner.updateDataConnections();
      }
   }

   /**
 * Step the time for the animation by dt.
 *
 *   @param dt the time step
 *   @param time the current time.
 */
  public void step(double dt, double time){
    if(runMode) run();
    else step();
  }

/**
 * Run.
 */
    void run(){
      if(osi==null) return;
      Graphics g=osi.getGraphics();
      while(currentStep<nsteps){
          int rand=(int) (4*Math.random()); // produces a number between 0 and 3
          switch(rand){
            case 0: locationX=locationX+1; break;
            case 1: locationX=locationX-1; break;
            case 2: locationY=locationY+1; break;
            case 3: locationY=locationY-1; break;
            //default: ;
          }
          g.setColor(Color.red);
          paintCell(g, locationX,  locationY);
          currentStep++;
      }
      g.setColor(Color.white);
      paintCell(g, locationX,  locationY);
      g.dispose();
      paint();// blast osi onto schreen
      locationX=locationX-ncols/2;
      locationY=locationY-nrows/2;
      int r=(int)Math.sqrt(locationX*locationX+locationY*locationY);
      if(r<nsteps){
            count[r]=count[r]+1;
            graph.clearSeries(1);
            graph.addData(1,num,count);
      }
      resetWalker();
      owner.updateDataConnections();
   }


  public Dimension getMinimumSize(){ return ps;}
  public void setMinimumSize(Dimension s){ ps=s;}

  public Dimension getPreferredSize(){ return ps;}
  public void setPreferredSize(Dimension s){ ps=s;}

/**
  *
  * Returns the id of the histogram. This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getHistogramID(){
     if(histogram==null) histogram= new Histogram();
     return histogram.getID();
  }

  // inner class used for data connection to histogram.
  public class Histogram extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"r","n"};  // speed and number
    double[][] ds=new double[1][2];  // the datasource state variables r, n;

    Histogram(){ // Constructor
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
      if(ds.length!=count.length){
           ds=new double[count.length][2];
           //for(int i=0; i<count.length; i++) ds[i][0]=i;
      }
      for(int i=0; i<count.length; i++){
        ds[i][0]=i;
        ds[i][1]=count[i];
      }
      return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return owner;}    //usually owner is an SApplet.
}// end of Histogram class


}
