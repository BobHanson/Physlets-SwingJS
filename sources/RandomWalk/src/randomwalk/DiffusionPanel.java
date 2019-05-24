package randomwalk;

import java.awt.*;
import java.util.Random;
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
public class DiffusionPanel extends Panel  implements SStepable, edu.davidson.tools.SDataSource {
   String[] varStrings= new String[]{"r","t"};  // average distance, time
   double[][] ds=new double[1][2];  // the datasource state variables r, t
   double time=0;
   double rms=0;
   int nrows=128; // the number of rows down a panel
   int ncols=128; // the number of columns across the panel
   int nwalkers=128;
   int[] locationX=new int[nwalkers];
   int[] locationY=new int[nwalkers];
   int nbins=(int)Math.sqrt(nrows*nrows+ncols*ncols)/2+1;
   double[] count=new double[nbins];
   double[] num=new double[nbins];
   int currentStep=0;
   Dimension ps= new Dimension(150,150);
   SGraph graph=null;
   Image osi=null;
   int iwidth=0,iheight=0;
   double xpixPerGrid=0;
   double ypixPerGrid=0;
   Histogram histogram=null;
   SApplet owner=null;
   Random random=new Random();

/**
 * Construct the DiffusionPanel and initialize the walkers.
 *
 */
  public DiffusionPanel(SApplet owner, SGraph graph){
    this.owner=owner;
    this.graph=graph;
    graph.setSeriesStyle(1,Color.red,false,-3);
    graph.setAutoReplaceData(1,true);
    graph.setMinYRange(true,0,20);
    for(int i=0; i<nbins; i++)num[i]=i;
    for(int i=0; i<nwalkers; i++){
        locationX[i]=ncols/2;
        locationY[i]=nrows/2;
    }
    try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
  }

 /**
 * Step the time for the animation by dt.
 *
 *   @param dt the time step
 *   @param time the current time.
 */
  public void step(double dt, double time){
    this.time=time+dt;
    step();
  }

  void reset(){
   resetWalkers();
  }

  private void resetWalkers(){
    for(int i=0; i<nbins; i++)num[i]=i;
    for(int i=0; i<nbins; i++)count[i]=0;
    count[0]=nwalkers;
    for(int i=0; i<nwalkers; i++){
        locationX[i]=ncols/2;
        locationY[i]=nrows/2;
    }
    rms=0;
    time=0;
    paintWalkers();
    repaint();
  }

  void createImage(){
    if (getSize().width==0||getSize().height==0) return;// image is too small
    iwidth = this.getSize().width;
    iheight = this.getSize().height;
    osi = createImage(iwidth,iheight);   //create an off screen image
    xpixPerGrid=iwidth/(double)ncols;
    ypixPerGrid=iheight/(double)nrows;
    paintWalkers();
  }

/**
 * Paint the world.
 *
 * Call this method when you want to paint the screen yourself.
 */
  public void paint(){
      Graphics g=getGraphics();
      if(osi!=null) g.drawImage(osi,0,0,this);    // draw the image onto the visible graph
      g.dispose();
  }

  public void paint(Graphics g){
       if( osi== null || iwidth != this.getSize().width || iheight != this.getSize().height){
           createImage();
       }
       g.drawImage(osi,0,0,this);    // draw the image onto the visible graph
   }

  private void paintCell(Graphics g, int col, int row){
      int top=(int)(row*ypixPerGrid);
      int left=(int)(col*xpixPerGrid);
      //g.fillRect(left,top,(int)(xpixPerGrid+1 ),(int)(ypixPerGrid+1));
      g.fillOval(left,top,(int)(xpixPerGrid+2 ),(int)(ypixPerGrid+2));
  }

/**
 * Paint the walkers on the panel.
 *
 *
 */
   public void paintWalkers(){
     if(osi!=null){
       Graphics g=osi.getGraphics();
       g.setColor(Color.black);
       g.fillRect(0,0,iwidth,iheight);
       g.setColor(Color.red);
       for(int i=0; i<nwalkers; i++) paintCell(g,locationX[i],locationY[i]);
       g.setColor(Color.green);
       paintCell(g,ncols/2,nrows/2);
       g.dispose();
    }
   }


/**
 * Step.
 */
   private void step(){
      for(int i=0; i<nwalkers; i++){
        int rand=(int) (4*random.nextDouble()); // produces a number between 0 and 3
        switch(rand){
            case 0: locationX[i]=locationX[i]+1; break;
            case 1: locationX[i]=locationX[i]-1; break;
            case 2: locationY[i]=locationY[i]+1; break;
            case 3: locationY[i]=locationY[i]-1; break;
            //default:;
        }
        //locationX[i]=Math.max(locationX[i],0);
        //locationY[i]=Math.max(locationY[i],0);
        //locationX[i]=Math.min(locationX[i],ncols-1);
        //locationY[i]=Math.min(locationY[i],nrows-1);
      }
      paintWalkers();
      paint();
      currentStep++;
      for(int i=0; i<nbins; i++)count[i]=0;
      double sum=0;
      for(int i=0; i<nwalkers; i++){
        int x=locationX[i]-ncols/2;
        int y=locationY[i]-nrows/2;
        double r2=x*x+y*y;
        int r=(int)Math.sqrt(r2);
        if(r<nbins)count[r]=count[r]+1;
        sum += r2;
      }
      rms=Math.sqrt(sum/nwalkers);
      graph.clearSeries(1);
      graph.addData(1,num,count);
      owner.updateDataConnections();
   }

  void setRowsColsBinsParticles(int r, int c, int b, int p){
    nrows=Math.max(1,r);
    ncols=Math.max(1,c);
    nbins=Math.max(1,b);
    nwalkers=Math.max(1,p);
    locationX=new int[nwalkers];
    locationY=new int[nwalkers];
    count=new double[nbins];
    num=new double[nbins];
    for(int i=0; i<nbins; i++)num[i]=i;
    for(int i=0; i<nwalkers; i++){
        locationX[i]=ncols/2;
        locationY[i]=nrows/2;
    }
    rms=0;
    if(osi!=null){
        xpixPerGrid=iwidth/(double)ncols;
        ypixPerGrid=iheight/(double)nrows;
        resetWalkers();
    }
  }

  public Dimension getMinimumSize(){ return ps;}
  public void setMinimumSize(Dimension s){ ps=s;}

  public Dimension getPreferredSize(){ return ps;}
  public void setPreferredSize(Dimension s){ ps=s;}

  public String[]   getVarStrings(){return varStrings;}
  public int getID(){return hashCode();}
  public void setOwner(SApplet applet){;}
  public SApplet getOwner(){return owner;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"

  public double[][] getVariables(){
    ds[0][0]=rms;
    ds[0][1]=time;
    return ds;
  }



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
    public SApplet getOwner(){return owner;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"

  }  // end of Histogram class

}
