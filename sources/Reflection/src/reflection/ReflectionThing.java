/*
**************************************************************************
**
**                      Class  Thing
**
**************************************************************************
**
** class Thing extends Object
**
** @author Jim Nolen
**
*************************************************************************/


package reflection;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import java.awt.*;
import edu.davidson.tools.*;
import edu.davidson.display.Format;


/**
*
* A Thing is a medium through which an electro-magnetic or quantum mechanical wave may
* travel.  It contains a left-travelling and a right-travelling wave and controls
* all the mathematical calculations to determine that amplitudes of those waves.
* In ReflectionApplet, an object of type Thing is owned by an instance of WavePanel.
* Thing has the ability to draw itself on a graphics context, particularly an offscreen graphics
* context of its WavePanel owner.
*
*/
public class ReflectionThing implements SDataSource{
  int vertOffset=0;
  WavePanel owner = null;
  SApplet applet = null;
  String caption;
  boolean dragPotential=false;
  boolean dragable = false;
  boolean showValue = true;     //turns display of index of refraction on and off
  boolean visibility=true;
  boolean showCaption = false;
  double indexN = 1;      //index of refraction of medium. Used in EM mode
  double energy =2;
  double potential = 1;   //potential energy of medium. Used in QM mode
  double magRight = 1;    //magnitude of right travelling wave
  double magLeft = 0;     //magnitude of left travelling wave
  double[] leftW2 = null; //left traveling wave that enters from neighboring ensembles
  double[] leftW = null;  //amplitude values for left traveling wave
  double[] rightW = null; //amplitude values for right traveling wave
  int[] xpoints = null;   //x values of (x,y) pairs for drawPolyline in painting methods
  int[] ypoints = null;    //x values of (x,y) pairs for drawPolyline in painting methods
  double pos;     //left bound of wave in world units. Equal to the pos+width of the left neighboring Thing of this
  double width;  //width of medium in world units
  int pwidth;   //pixelwidth of medium
  Color color = Color.white; //background color
  int originX = 0;  //pixel origin of medium. Horizontal center of medium
  int originY = 0;  //pixel origin of medium. Vertical center of medium
  int left;      //pixel position of left side of medium
  int right;     //pixel position of right side of medium
  double phaseLeft = 0;  //phase of right traveling wave at left boundary
  double phaseLeft2 = 0; //phase of left traveling wave at left boundary
  double wavenumber = 0;      //wavenumber of wave in this medium.  2*pi/lambda
 // double time = 0;
 // double xoff = 0;
  //for data connections:
  String[] varStrings = {"pos","width","n","p","magLeft","magRight","wavelength","transmission","reflection"};
  double[][] vars = new double[1][9];   // wavelength, intensity
  double[][] boundaryMatrix = new double [2][2];
  double[][] imMatrix = new double [2][2];
  double phaseShift=0;  // the phase shift traveling through the layer.
  double absorption=0;  // the absorption traveling through the layer.

  double[] leftE=new double[2];
  double[] rightE=new double[2];

  Format format=new Format("%6.2g"); // the format for labels.  Wavepanel format will be used if this is null.
  Font font=null;


  public ReflectionThing()  {
  }

  /**
  *
  * Constructor.
  *
  * @param w double width in units
  * @param o WavePanel owner which calls constructor
  */
  public ReflectionThing( double w, WavePanel o)  {
    this();
    owner = o;
    applet = owner.owner;
    pos=0;
    ReflectionThing t=owner.getRightMost();
    if(t!=null){
       pos = t.pos+t.width;
    }
    width = w;
    rescale(0);
  }

  /**
  *
  * Method adjust array sizes to fit size of medium
  *
  *
  *
  */
  int rescale(int leftPix){
      //leftW=null;
      //rightW=null;
      //xpoints = null;
     // ypoints = null;
      left = leftPix;
      right = (int)((pos+width)*owner.ppu);
      pwidth = right-left;
      if(leftW==null || leftW.length!= pwidth){
	      leftW = new double[pwidth];
	      rightW = new double[pwidth];
	      xpoints = new int [pwidth];
	      ypoints = new int [pwidth];
      }
      originY = (int)(0.5*owner.currenth);
      for (int i = 0; i< pwidth; i++){
          leftW[i]=0;
          rightW[i]=0;
          xpoints[i]=0;
          ypoints[i]=0;
      }
      return right;
  }

  void calcBoundaryMatrix(ReflectionThing t){
      boundaryMatrix[0][0]=0;
      boundaryMatrix[0][1]=0;
      boundaryMatrix[1][0]=0;
      boundaryMatrix[1][1]=0;

  }

  int calcField(ReflectionThing rightThing, int currentPix){
    // calculate the fields starting at currentPix.  Return the new currentPix.
    return currentPix;
  }

  /**
  *
  * Method sets color of background in Medium
  *
  *
  * @param r red
  * @param b blue
  * @param g green
  *
  */
  public void setColor(int r, int g, int b){
      if ((r>=0)&&(g>=0)&&(b>=0)&&(r<=255)&&(g<=255)&&(b<=255))
        this.color = new Color(r,g,b);
      else this.color = Color.blue;
  }


  /**
  *
  * Method directs thing to paint itself on the owner's canvas
  *
  * @param g Graphics
  *
  */
  void paintOS(Graphics g){
      g.setColor(color);
      g.fillRect(left,0,pwidth,owner.currenth);
      if (this instanceof EMThing){
      paintRightWave(g);
      paintLeftWave(g);
      paintSumWave(g);
      }
      else{
      paintRightWave(g);
      paintLeftWave(g);
      }
        //Sum of two waves
      if (showValue) paintValue(g);   //paints index of refraction on medium
      if (showCaption) paintCaption(g);
  }

  /**
  *
  * Method to paint from the last thing to the right of the canvas.
  *
  * @param g Graphics
  *
  */
  void fillToEnd(Graphics g){}

  /**
  *
  * Method paints left going wave on a graphics context
  *
  * @param g Graphics
  *
  */
  void paintLeftWave(Graphics g){
    if(!owner.showLWave)return;
    for (int i=0; i<pwidth; i++){
        xpoints[i] = i+left;
        ypoints[i] = originY+(int)(leftW[i]*owner.ppu) -owner.leftPixOffset;
    }
    g.setColor(owner.imWaveColor);
    g.drawPolyline(xpoints,ypoints,xpoints.length);
  }

  /**
  *
  * Method paints right going wave on a graphics context
  *
  * @param g Graphics
  *
  */
  void paintRightWave(Graphics g){
    if(!owner.showRWave)return;
    for (int i=0; i<pwidth; i++){
        xpoints[i] = i+left;
        ypoints[i] = originY-vertOffset+(int)(rightW[i]*owner.ppu)-owner.rightPixOffset;
    }
    g.setColor(owner.reWaveColor);
    g.drawPolyline(xpoints,ypoints,xpoints.length);
  }

  /**
  *
  * Method paints sum of right and left going waves on a graphics context
  *
  * @param g Graphics
  *
  */
  void paintSumWave(Graphics g){
    for (int i=0; i<pwidth; i++){
        xpoints[i] = i+left;
         if (this instanceof EMThing){
        ypoints[i] = originY+vertOffset+(int)((leftW[i]+rightW[i])*owner.ppu);
        }
        else
        ypoints[i] = originY+vertOffset+(int)((leftW[i]*leftW[i]+rightW[i]*rightW[i])*owner.ppu);
    }
    g.setColor(Color.green);
    g.drawPolyline(xpoints,ypoints,xpoints.length);
  }

  /**
   *
   * Method paints small caption in bottom center of medium
   *
   * @param g Graphics
   */
   void paintCaption(Graphics g){
      Font f=g.getFont();
      if(font!=null){
        g.setFont(font);
      }
      FontMetrics fm=g.getFontMetrics(g.getFont());
      int boxW=fm.stringWidth(caption);
      g.setColor(Color.black);
      g.drawString(caption,left+(int)(0.5*(pwidth-boxW)),(int)(0.9*owner.currenth));
      g.setFont(f);
      //g.drawString(caption,left,(int)(0.8*originY));

  }

   /**
   *
   * Method paints value of indexN or potential in bottem center of screen
   *
   * @param g Graphics
   */
   void paintValue(Graphics g){
  }



  /**
  *
  * Method works out the math for all waves in this medium.
  * Overridden in EMTHing.class and QMThing.class
  *
  */
 // void makeWave(){
  //}

  /**
  *
  * Method sets position of medium
  *
  * @param xpos double Position of the left hand side of the layer.
  *
  */
  void setPos(double xpos){
      this.pos = xpos;
  }

  /**
  *
  * Method sets width of medium in pixels
  *
  * @param w double
  *
  */
  void setWidth(double w){
      this.width = w;
      if (this instanceof QMThing)
          setPotential(potential);
  }

  void setIndexN(double n){
      indexN=n;
  }

  void setPotential(double v){
        if (this instanceof EMThing) return;
        potential = v;
        energy=owner.qmEnergy;
        if (potential==energy)
               potential=energy*0.9999; //use asymptotic value in the case that E=V
                                  //this avoids a division by zero later on.
        indexN=Math.sqrt(Math.abs(potential-energy));
        wavenumber=indexN;
        phaseShift=width*indexN;

  }

  /**
  *
  * Method scales waves to fit display screen. Called from wavePanel
  *
  * @param maxmag double current maximum magnitude from all waves in wavePanel.
  * @param newmag double NEW maximum magnitude to which old maximum will be scaled.
  */
  void scaleWaves(double maxmag, double newmag){
    for(int i = 0; i<pwidth; i++){
        leftW[i]*=newmag/maxmag;
        rightW[i]*=newmag/maxmag;
        //leftW2[i]*=newmag/maxmag;
    }
  }

  /**
  *
  * Method turns index of refraction display (or potential display) on and off
  *
  * @param tf boolean
  */
  public void setShowValue(boolean tf){
      showValue = tf;
  }

  public void  setVisibility(boolean vis){
    visibility=vis;
  }

  /**
  *
  * Method determines whether a given x,y coordinate is close to right boundary of a medium
  * Used in mouse dragging.
  *
  * @param x int horizontal pixel coordinate
  * @param y int vertical pixel coordinate
  */
  boolean isNearBoundary(int x, int y){
      if(!dragable)return false;
      //double p = owner.ppu;
      if (Math.abs(right-x)<4) return true;
      else return false;
  }

  /**
  *
  * Method determines whether a given x,y coordinate is close to right boundary of a medium
  * Used in mouse dragging.
  *
  * @param x int horizontal pixel coordinate
  * @param y int vertical pixel coordinate
  */
  boolean isInside(int x, int y){
      if(!dragable){
         if (x>left && x<right) return true;
      }else if (x>left+3 && x<right-3) return true;
      return false;
  }

  /**
  *
  * Used in data connections
  *
  */
  public int getID(){return this.hashCode();}

  /**
  *
  * Used in data connections
  *
  */
  public void setOwner(SApplet app){this.applet=app;}

  public final boolean setFormat(String str){
     try{format= new Format(str);}
     catch (IllegalArgumentException e){
         System.out.println("Illegal numeric format:"+str);
         return false;
     }
     return true;
  }

  /**
  *
  * Used in data connections
  *
  */
  public SApplet getOwner(){return applet;}  //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"


  /**
  *
  * Used in data connections
  *
  * @return String[] array of variable titles.
  */
  public String[]   getVarStrings(){return varStrings; }


  /**
  *
  * Used in data connections
  *
  * @return double[][] array of variables for data connections.
  */
  public double[][] getVariables(){
          vars[0][0]=pos;
          vars[0][1]=width;
          vars[0][2]=indexN;
          vars[0][3]=potential;
          vars[0][4]=magLeft;
          vars[0][5]=magRight;
          vars[0][6]=owner.lambda;
          vars[0][7]=owner.tranC;
          vars[0][8]=owner.refC;

      return vars;
 }

}