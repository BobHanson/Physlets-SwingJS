/*
**************************************************************************
**
**                      Class  WavePanel
**
**************************************************************************
**
** class WavePanel extends  Panel
**
** @author Jim Nolen
**
*************************************************************************/


package reflection;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.Enumeration;
import edu.davidson.tools.*;
import edu.davidson.display.Format;
import edu.davidson.graph.TextLine;
import edu.davidson.display.*;


/**
*
* WavePanel is the painting canvas for the ReflectionApplet.  It contains any number of
* wave media, which are of type Thing.class, and controls their interaction. All media (Things)
* are stored in thingVector. Painting is done an offscreen image buffer "osi," and then
* projected onto the screen. Instances of Thing know how to paint themselves onto the wavePanel.
* The main calculation method is recalc().
*
* When WavePanel is in mode=0, the media contain electromagnetic waves and have width, index of refraction, and amplitude properties.
* When WavePanel is in mode=1, the media contain quantum mechanical waves and have width, potential, and probability properties.
*
*
*/
public class WavePanel extends Panel implements SStepable, SScalable{
  static int QM_MODE=1;
  static int EM_MODE=0;
  private boolean fixedPts=false;
  private Color[] colors=new Color[101];
  private boolean wasRunning=false;
  private boolean dragEnergy=false;

  private double lowestN=0;  // used for scaling to make sure wavelength or energy aren't too small or too large.
  private double highestN=0;
  private boolean sendData=true;  // keep data from being sent to data connections

  Font boldFont=new Font("Helvetica",Font.BOLD,12);
  Font font=boldFont;// default font

  boolean showPhaseColor=true;    // show the phase variation in the QM wave as color.
  boolean showTime=false;    // show the time in the upper left hand corner.
  Color   waveColor=new Color(128,0,0);  // dark red
  boolean showRWave=false; // show the right traveling wave
  boolean showLWave=false; // show the left traveling wave
  boolean showWave=true; // show the total wave
  boolean showEnergy=true;
  boolean coordDisplay=true; // draw coordinate display with click-drag.
  boolean ampDisplay=false; // draw amplitude display with click-drag.
  boolean phaseDisplay=false; // draw amplitude display with click-drag.
  boolean mouseDown=false;  // boolean to test for mouse down;

  int rightPixOffset=0;
  int leftPixOffset=0;

  int mousex=0, mousey=0;  // the last mouse position during a click or a drag;
  private Format mouseFormat= new Format("%-+6.3g");
  private Format format=new Format("%6.3g");
  double[] leftWave=null;
  double[] rightWave=null;
  int[]    xpoints=null;
  int[]    ypoints=null;
  Vector thingVector = new Vector(4,1);   //stores all media (of type Thing, EMThing, or QMThing)
  Vector drawingThings= new Vector();
  String message = null; //message to be displaye in small yellow text box at lower right corner
  private int     boxWidth=0;  // size of message box in pixels.
  private int mode = EM_MODE; //EM mode = 0,  QM mode = 1
 // boolean dragging = true; //enables dragging option
  boolean showMessage = false;  //turns message display on and off.
  ReflectionApplet owner = null;         //applet owner
  Image osi = null;             //offscreen image
  int currentw = 1;             //pixel width of this panel
  int currenth = 1;             //pixel height of this panel
  double ppu = 10;              //pixels per unit
  boolean autoRefresh = true;
  double magMax = 1;            //maximum wave magnitude. Used in calcAmps() and scaleWaves()
  double lambda = 2;            //wavelength of incident wave in a vacuum
  ReflectionThing dragThing=null;         //item which may be dragged. Enabled by mouse press.
  ReflectionThing insideThing=null;         //item which may be dragged. Enabled by mouse press.
  //double[][] mT = new double[2][2];
  //double frq = 3;
  double qmEnergy=0;
  double waveVel=0;
  double time = 0;
  double timePhase=0;
  double refC=0,tranC=1;
  double ampScale=1;

  WaveDataSource waveDataSource=null;
  TextLine title= new TextLine();
  String titleStr="";
  Color imWaveColor = new Color(0, 128, 128);
  Color reWaveColor = new Color(128, 0, 0);
  double enMax = 1000000.0D;
  double enMin = -1000000.0D;
  double xCoordinateOffset = 0.0D;

  //basic constructor
  public WavePanel() {
    super();
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    initColors();
  }

  public WavePanel(ReflectionApplet o) {
      this();
      owner=o;
  }

  /**
   * Enable the amplitude display on with click-drag.
   *
   * @param              show boolean   Show the time?
   */
  public void setAmpDisplay(boolean show){
      ampDisplay=show;
  }

  /**
   * Enable the phase display on with click-drag.
   *
   * @param      show true will display phase
   */
  public void setPhaseDisplay(boolean show){
      phaseDisplay=show;
  }


  /**
   * Enable the coordinate display on with click-drag.
   *
   * @param   show true will display coordinates
   */
  public void setCoordDisplay(boolean show){
      coordDisplay=show;
  }

  /**
   * Enable the energy display in the applet window.
   *
   * @param              show boolean   Show the time?
   */
  public void setEnergyDisplay(boolean show){
      showEnergy=show;
  }

  /**
   * Enable the time display in the applet window.
   *
   * @param          show true will display time
   */
  public void setTimeDisplay(boolean show){
      showTime=show;
  }

  /**
  *
  * Method sets mode of either EM or QM
  *
  * @param i int EM = 0, QM = 1.
  */
  public void setMode(int i){
      i=Math.abs(i);
      i=Math.min(i,1);
      this.mode = i;
      removeAllThings();
  }

  /**
  *
  * Method must be called any time size of panel changes.
  * Creates offscreen image.
  *
  */
  public void setArrayBounds(){
      if(getBounds().height>4)fixedPts=false;
      if(fixedPts) return;
      currentw =this.getBounds().width;
      currenth = this.getBounds().height;
      if(currentw<=4 || currenth<=4){
        return;
      }
      leftWave = new double[currentw];
      rightWave = new double[currentw];
      xpoints=new int[currentw];
      ypoints=new int[currentw];
      osi = createImage(currentw,currenth);
      arrangeMedia();
  }

  /**
*
* Method must be called any time size of panel changes.
* Creates offscreen image.
*
*/
public void setArray(int w){
    fixedPts=true;
    currentw =w;
    leftWave = new double[currentw];
    rightWave = new double[currentw];
    xpoints=new int[currentw];
    ypoints=new int[currentw];
    osi = createImage(currentw,1);
    arrangeMedia();
}


  public void step(double dt, double time){
     if(!autoRefresh) return;
     if (osi==null|| currentw != this.getSize().width || currenth != this.getSize().height) {
      setArrayBounds();
     }
     if (osi==null) return;
     this.time=time;
     makeWaves(time);
     paintOffScreen();
     if(sendData) owner.updateDataConnections();
  }


  private void calcMinMaxN(){
     lowestN=0;
     highestN=0;
     ReflectionThing t = getRightMost();
     if(t==null) return;
     lowestN=t.indexN;
     highestN=t.indexN;
     t = getLeftNeighbor(t);
     while(t!=null){
        if(lowestN>t.indexN) lowestN=t.indexN;
        if(highestN<t.indexN) highestN=t.indexN;
        t = getLeftNeighbor(t);
     }
  }


  /**
  *
  * Method runs all calculations.
  *
  */
  public synchronized void recalc(){
      boolean shouldRun=owner.clock.isRunning();
      owner.clock.stopClock();
      if (osi==null|| currentw != this.getSize().width || currenth != this.getSize().height) {
          setArrayBounds();
      }
      if(osi==null) return;
      calcAmps();
      calcMinMaxN();
      paintOffScreen();
      if(shouldRun)owner.clock.startClock();
      else if(autoRefresh && sendData) owner.updateDataConnections();
  }

  Thing getDrawingThing(int id){
    Thing t=null;
    for( Enumeration e=drawingThings.elements(); e.hasMoreElements();){
      t= (Thing) e.nextElement();
      if(t.hashCode()==id) return t;
    }
    return null;
  }


  /**
  *
  * Method retrieves thing given id from thingVector
  *
  * @param id int hashcode ID
  */
  ReflectionThing getReflectionThing(int id){
      Enumeration e = thingVector.elements();
      while (e.hasMoreElements()){
          ReflectionThing t = (ReflectionThing)e.nextElement();
          if (t.hashCode()==id) return t;
      }
      return null;
  }

 /**
  *
  * Method retrieves thing given id from thingVector
  *
  * @param id int hashcode ID
  */
  ReflectionThing getReflectionThingFromIndex(int i){
      int size=thingVector.size();
      if(i<0 || i>=size) return null;
      return (ReflectionThing) thingVector.elementAt(i) ;
  }

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
  * Method sets index of refraction of a medium
  *
  * @param id int
  * @param n double index of refraction
  */
  public void setDragEnergy(int id, boolean drag){
      if(id==0){
          dragEnergy=drag;
          return;
      }
      ReflectionThing t = getReflectionThing(id);
      if (t!=null) {
          t.dragPotential=drag;
      }
  }

    /**
  *
  * Method sets default parameters for this applet. Should be called at the
  * begining of a set of script.
  *
  */
  public void setDefault(){
      showMessage=false;
      if(waveDataSource!=null) owner.removeDataSource(waveDataSource.getID());
      waveDataSource=null;
      removeAllThings();
      this.showPhaseColor=true;
      this.dragEnergy=false;
      showRWave=false; // show the right traveling wave
      showLWave=false; // show the left traveling wave
      showWave=true; // show the total wave
      rightPixOffset=0;
      leftPixOffset=0;
  }


  /**
  *
  * Method sets index of refraction of a medium (ReflectionThing)
  *
  * @param id int hashcode id of medium (ReflectionThing)
  * @param n doule index of refraction
  */
  public void setIndexN(int id, double n){
      ReflectionThing t = getReflectionThing(id);
      if (t!=null) {
          if(t instanceof EMThing)
            t.setIndexN(n);
          else
            t.setPotential(n);
      }
      if (autoRefresh) recalc();
  }

 /**
  *
  * Gets the energy of the QM state.
  *
  * @return the energy
  */
  public double getQMEnergy(){
   return qmEnergy;
  }

  /**
  *
  * Method sets color of a medium
  *
  * @param r int red value 0 <= r <= 255
  * @param g int green value 0 <= g <= 255
  * @param b int blue value 0 <= b <= 255
  *
  */
  public void setRGB(int id, int r, int g, int b){
      ReflectionThing t = getReflectionThing(id);
      if(t!=null){
        t.setColor(r,g,b);
        return;
      }
      Thing dt= getDrawingThing(id);
      if(dt!=null){
        dt.setColor(new Color(r,g,b));
        return;
      }
  }
  

  public void setImRGB(int paramInt1, int paramInt2, int paramInt3)
  {
    this.imWaveColor = new Color(paramInt1, paramInt2, paramInt3);
  }
  
  public void setReRGB(int paramInt1, int paramInt2, int paramInt3)
  {
    this.reWaveColor = new Color(paramInt1, paramInt2, paramInt3);
  }

  /**
  *
  * Remove an object from the applet.
  *
  * @param object identifier
  */
  public void removeObject(int id){
      ReflectionThing t = getReflectionThing(id);
      if(t==null) return;
      thingVector.removeElement(t);
      arrangeMedia();
      if (autoRefresh) recalc();
      try{owner.removeDataSource(t.hashCode()); }
       catch (Exception e){
          e.printStackTrace();
          System.out.println("error in addMediaDataSource()");
       }
  }

  /**
  *
  * Method creates a medium with given index of refraction.  Layers are inserted into the stack at pos.
  * Layers that come after the pos parameter have their position in the stack increased.
  *
  * @param n double index of refraction
  * @param w double width of medium in units
  *
  * @return  the object.
  */
  public ReflectionThing addMedium( double n, double w){
       ReflectionThing t;
       if (mode==EM_MODE) { t = new EMThing(n,w,this);}
       else { t = new QMThing(n,w,this);}
       thingVector.addElement(t);
       arrangeMedia();
       if (autoRefresh) recalc();
       try{SApplet.addDataSource(t); }
       catch (Exception e){
          e.printStackTrace();
          System.out.println("error in addMediaDataSource()");
       }
       return t;
  }

  /**
  *
  * Method determines widths and positions of created media. When media are created,
  * they are inserted into thingVector according to an order number.  Then, this method must set all widths and
  * positions in order from left to right.
  *
  *
  */
  void arrangeMedia(){
      double leftEdge = 0;
      int leftPixEdge = 0;

      for(Enumeration e = thingVector.elements(); e.hasMoreElements();){
            ReflectionThing t = (ReflectionThing) e.nextElement();
            t.setPos(leftEdge);
            leftPixEdge=t.rescale(leftPixEdge);
            leftEdge += t.width;
      }
  }

  /*
  *
  * Method initializes dataConnections for Thing. Methods required by DataSource interface are in Thing.class
  *
  * @param id int hashcode id of thing

  public void addMediaDataSource(int id){
    Thing t = getThing(id);
    try{SApplet.addDataSource(t); }
    catch (Exception e){
      e.printStackTrace();
      System.out.println("error in addMediaDataSource()");
    }
  }
  */

  /**
  *
  * Method removes all media from thingVector
  *
  */
  void removeAllThings(){
     drawingThings.removeAllElements();
     Vector v;
     synchronized(thingVector){v=(Vector)thingVector.clone();}
     for( Enumeration e=v.elements(); e.hasMoreElements();){
         ReflectionThing t= (ReflectionThing) e.nextElement();
         owner.removeDataSource(t.getID() );
     }
    thingVector.removeAllElements();
    arrangeMedia();
    if(autoRefresh){
        recalc();
    }
  }

  /**
  *
  * Turns auto-refresh feature on and off
  *
  * @param tf boolean
  */
  public void setAutoRefresh(boolean tf){
      this.autoRefresh = tf;
      if (tf) recalc();
  }



  /**
  *
  * Calculate the wave amplitudes in each media. Calculation
  * proceeds from right to left, since there is only an outgoing wave in the far right medium.
  * Maximum amplitude is determined, and waves are scaled accordingly to fit screen.
  *
  *
  */
  public void calcAmps(){
     // calulate the reflection and transmission at the boundaries.
     double refl=0, in=1;
     //magMax=1;  // the magnitude of the output wave.  Set to one for now.  Adjsut at the end to get an input of 1.
     ReflectionThing t = getRightMost();
     if(t==null) return;
     ReflectionThing rightThing=null;
      while(t!=null){
          t.calcBoundaryMatrix(rightThing);
          rightThing=t;
          t = getLeftNeighbor(t);
     }
     t = getLeftMost();
     in=Math.sqrt(t.rightE[0]*t.rightE[0]+t.rightE[1] *t.rightE[1]); // incident wave if the output were one
     refl=Math.sqrt(t.leftE[0]*t.leftE[0]+t.leftE[1] *t.leftE[1]); // incident wave if the output were one
     t = getRightMost();
     if(in>0) ampScale=magMax*1.0/in;
        else System.out.println("Error: The output is nonzero but the input is zero!");
     // renormalize for an incident wave of 1.
     refC=refl/in;
     tranC=1-refC;
     makeWaves(time); // fills the amplitdue vectors.
  }

  void makeWaves(double time){
     // calculate the right and left moving waves and fill the arrays.
     int currentPix=this.rightWave.length;
     ReflectionThing t2 = getRightMost();
     ReflectionThing rightThing=null;
     if(t2!=null) currentPix=t2.right;  // check for rounding.
     else return;
     do{
          if(mode==EM_MODE)timePhase=time*waveVel;
              else timePhase=time*qmEnergy/5.0;
          currentPix=t2.calcField(rightThing,currentPix);
          rightThing=t2;
          t2 = getLeftNeighbor(t2);
     } while (t2!=null);
     //System.out.println("currentpix="+currentPix);
  }

  /**
  *
  * Method multiplies two transfer matrices of form [A,Bi]|[Ci,D]
  *
  * @param m1 double[][] first transfer matrix
  * @param m2 double[][] second transfer matrix
  * @return double[][] resulting matrix
  */
  static double[][] multTransMatrices(double[][] m1, double[][] m2){
      double[][] mn = new double[2][2];
      double a,b,c,d,e,f,g,h;
      a = m1[0][0];
      b = m1[0][1];
      c = m1[1][0];
      d = m1[1][1];

      e = m2[0][0];
      f = m2[0][1];
      g = m2[1][0];
      h = m2[1][1];

      mn[0][0]=(a*e)-(b*g);
      mn[0][1]=(a*f)+(b*h);
      mn[1][0]=(e*c)+(d*g);
      mn[1][1]=(d*h)-(f*c);
      return mn;
  }


  /**
  *
  * Method calculates the reflection coefficient given a transfer matrix and
  * the indices of refraction of the initial and final medium.
  *
  * @param m1 double[][] transfer matrix for any number of intermediate media
  * @param n0 double index of refraction from initial medium
  * @param nt double index of refraction from final medium
  * @return double reflection coefficient
  */
  public static double calcRefCoeff(double[][] m1, double n0, double nt){
      // r = (w+i*x)/(y+i*z), a complex number
      //we want |r|
      double a,b,c,d,w,x,y,z,alph,bet,r,sum,sum2;
      a = m1[0][0];
      b = m1[0][1];
      c = m1[1][0];
      d = m1[1][1];
      w=(a*n0)-(d*nt);
      x=(b*n0*nt)-c;
      y=(a*n0)+(d*nt);
      z=(b*nt*n0)+c;
      //alph=(w*y+x*z)/(y*y+z*z);
      //bet=(x*y-w*z)/(y*y+z*z);
      r = (w*w+x*x)/(y*y+z*z);
      //r=Math.sqrt(alph*alph+bet*bet);
      System.out.println("r ="+r);
      return r;  //coefficient of reflection
  }

   /**
  *
  * Method calculates the transmission coefficient given a transfer matrix and
  * the indices of refraction of the initial and final medium.
  *
  * @param m1 double[][] transfer matrix for any number of intermediate media
  * @param n0 double index of refraction from initial medium
  * @param nt double index of refraction from final medium
  * @return double transmission coefficient
  */
   public static double calcTransCoeff(double[][] m1, double n0, double nt){
      // t = (a2+i*b2)/(c2+i*d2), a complex number
      //we want |r|
      double a,b,c,d,a2,w,x,y,z,alph,bet,tc;
      a = m1[0][0];
      b = m1[0][1];
      c = m1[1][0];
      d = m1[1][1];
      w=2*n0;
      x=0;
      y=(a*n0)+(d*nt);
      z=(b*nt*n0)+c;
      //alph=(w*y+x*z)/(y*y+z*z);
      //bet=(x*y-w*z)/(y*y+z*z);
      //tc=Math.sqrt(alph*alph+bet*bet);
      tc = (w*w+x*x)/(y*y+z*z);
      System.out.println("t ="+tc);
      return tc;   //coefficient of transmission
  }


  /**
  *
  * Method scales waves relative to a new maximum value so that waves will fit in display area.
  *
  * @param maxmag double the OLD maximum amplitude
  * @param newmag double the NEW maximum amplitude to which all waves should be scaled relatively.
  */
   void scaleWaves(double maxmag, double newmag){
      for (Enumeration e=thingVector.elements(); e.hasMoreElements();){
          ReflectionThing t = (ReflectionThing) e.nextElement();
          if (t!=null) t.scaleWaves(maxmag,newmag);
      }
  }

  /**
  *
  * Sets maximum amplitude of wave (incoming wave always has maximum amplitude)
  *
  * @param m double magnitude in units
  */
  public void setMagMax(double m){
    if (m>0) this.magMax = m;
    if (autoRefresh) recalc();
  }
  /**
  *
  * Method sets the width of a given medium
  *
  * @param id int hashcode id of medium
  * @param width double width of medium
  *
  */
  public void setMediaWidth(int id,double w){
      sendData=false;
      boolean shouldRun=owner.clock.isRunning();
      owner.clock.stopClock();
       if (w<=0) return;
       ReflectionThing t = getReflectionThing(id);
       if (t==null) return;
       t.setWidth(w);
       arrangeMedia();
       if (autoRefresh){
          recalc();
       }
       if(shouldRun)owner.clock.startClock();
       sendData=true;
  }


  void setWaveVelocity(double v){
   sendData=false;
   boolean shouldRun=owner.clock.isRunning();
   owner.clock.stopClock();
   waveVel=v;
   arrangeMedia();
   recalc();
   if(shouldRun)owner.clock.startClock();
   sendData=true;
  }
  
  void setQMDragMinMaxE(double paramDouble1, double paramDouble2)
  {
    this.enMin = paramDouble1;
    this.enMax = paramDouble2;
  }

  public void  setQMEnergy(double e){
    if (mode!=QM_MODE) return;
    sendData=false;
    boolean shouldRun=owner.clock.isRunning();
    owner.clock.stopClock();
    qmEnergy=e;
    Enumeration en = thingVector.elements();
      while (en.hasMoreElements()){
          ReflectionThing t = (ReflectionThing)en.nextElement();
          t.setPotential(t.potential);
      }
    if (autoRefresh){
          recalc();
       }
    if(shouldRun)owner.clock.startClock();
    sendData=true;
  }


 /**
  *
  * Method sets wavelength of incident wave
  *
  * @param lambda wavelength in units
  */
  public void setWavelength(double lam){
   sendData=false;
   boolean shouldRun=owner.clock.isRunning();
   owner.clock.stopClock();
   lambda = lam;
   arrangeMedia();
   recalc();
   if(shouldRun)owner.clock.startClock();
   sendData=true;
  }

  /**
  *
  * Method retrieves right-most medium
  *
  * @return Thing righ-most medium
  */
  public ReflectionThing getRightMost(){
     double xpos = -1000;
     ReflectionThing rightMost = null;
     for(Enumeration e = thingVector.elements(); e.hasMoreElements();){
          ReflectionThing t = (ReflectionThing) e.nextElement();
          if (t.pos>xpos) {
              xpos = t.pos;
              rightMost=t;
          }
      }
      return rightMost;
  }

  /**
  *
  * Method retrieves left-most medium
  *
  * @return Thing left-most medium
  */
  public ReflectionThing getLeftMost(){
     double xpos = 100000;
     ReflectionThing leftMost = null;
     for(Enumeration e = thingVector.elements(); e.hasMoreElements();){
          ReflectionThing t = (ReflectionThing) e.nextElement();
          if (t.pos<xpos) {
              xpos = t.pos;
              leftMost=t;
          }
      }
      return leftMost;
  }

  /**
  *
  * Method retrieves left neighboring medium of the given medium  in any mode
  *
  * @param t Thing
  * @return Thing neighboring medium
  */
  public ReflectionThing getLeftNeighbor(ReflectionThing t){
      double tpos = t.pos;
      double npos = -100000;
      ReflectionThing neighbor = null;
      for(Enumeration e = thingVector.elements(); e.hasMoreElements();){
          ReflectionThing m = (ReflectionThing) e.nextElement();
          if ((m.pos<tpos)&&(m.pos>npos)) {
              npos = m.pos;
              neighbor=m;
          }
      }
      return neighbor;
  }


  /**
  *
  * Method retrieves right neighboring medium of the given medium in EM Mode
  *
  * @param t EMThing
  * @return EMThing neighboring medium
  */
 /*
  public EMThing getRightEMNeighbor(EMThing t){
      if (mode==QM_MODE) return null;
      double tpos = t.pos;
      double npos = 10000000;
      EMThing neighbor = null;
      for(Enumeration e = thingVector.elements(); e.hasMoreElements();){
          EMThing m = (EMThing) e.nextElement();
          if ((m.pos>tpos)&&(m.pos<npos)) {
              npos = m.pos;
              neighbor=m;
          }
      }
      return neighbor;
  } */

    /**
  *
  * Method retrieves right neighboring medium of the given medium
  *
  * @param t Thing
  * @return Thing neighboring medium
  */
  public ReflectionThing getRightNeighbor(ReflectionThing t){
      double tpos = t.pos;
      double npos = 10000000;
      ReflectionThing neighbor = null;
      for(Enumeration e = thingVector.elements(); e.hasMoreElements();){
          ReflectionThing m = (ReflectionThing) e.nextElement();
          if ((m.pos>tpos)&&(m.pos<npos)) {
              npos = m.pos;
              neighbor=m;
          }
      }
      return neighbor;
  }


 /**
  *
  * Method retrieves right neighboring medium of the given medium in QM Mode
  *
  * @param t QMThing
  * @return QMThing neighboring medium
  */
  /*
  public QMThing getRightQMNeighbor(QMThing t){
      if (mode!=1) return null;
      double tpos = t.pos;
      double npos = 10000000;
      QMThing neighbor = null;
      for(Enumeration e = thingVector.elements(); e.hasMoreElements();){
          QMThing m = (QMThing) e.nextElement();
          if ((m.pos>tpos)&&(m.pos<npos)) {
              npos = m.pos;
              neighbor=m;
          }
      }
      return neighbor;
   }   */


   void paintDrawingThings(Graphics g){
    Thing t=null;
    for( Enumeration e=drawingThings.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
            t.paint(g);
    }
  }


  /**
  *
  * Method paints offscreen image. All things (media) paint themselves onto the
  * offscreen image osi.
  *
  *
  */
  public void paintOSI(){
     Graphics osg=null;
     try{
       if (osi==null|| currentw != this.getSize().width || currenth != this.getSize().height) {
        setArrayBounds();
       }
       if (osi==null) return;
       osg = osi.getGraphics();
       if(osg==null)return;
       ReflectionThing t=getRightMost();
       if(t!=null) osg.setColor(t.color);
       else osg.setColor(Color.black);
       osg.fillRect(0,0,currentw,currenth);
       paintReflectionThings(osg, t);
       paintDrawingThings(osg);
       osg.setColor(Color.black);
       if (showMessage) paintMessage(osg);
       if ((mode==QM_MODE)&&(showEnergy)) paintQMEnergy(osg);
       //paintLegend(osg);
       paintTime(osg);
       if(!titleStr.equals("")){
              title.setText(titleStr);
              Font f=osg.getFont();
              osg.setFont(font);
              title.draw(osg,currentw/2,15,TextLine.CENTER);
              setFont(f);
        }
       //osg.dispose();  moved to finally block
     }catch(Exception ex){
     }finally{
       if(osg!=null)osg.dispose();
     }
  }

/**
  * Paints value of QM Energy in upper left corner
  */
   void paintQMEnergy(Graphics g){
      if(!showEnergy) return;
      g.setColor(Color.black);
      Font f=g.getFont();
      g.setFont(font);
      String eStr=owner.label_energy+ format.form(SUtil.chop(qmEnergy,1.0e-12));
      if(showTime){
          //FontMetrics fm=g.getFontMetrics(font);
          //int boxW=fm.stringWidth(eStr);
          //g.drawString( eStr,getBounds().width-5-boxW,25);
          g.drawString(eStr,10,42);
      }else g.drawString(eStr,10,25);
      g.setFont(f);
   }
/**
  * Paints time in upper left corner
  */
  void paintTime(Graphics g ){
        if (!showTime || owner==null )return;
        g.setColor(Color.black);
        Font f=g.getFont();
        g.setFont(font);
        String tStr= format.form(SUtil.chop(owner.clock.getTime(),1.0e-12));
        g.drawString(owner.label_time+" " + tStr, 10, 25);
        g.setFont(f);
    }

  /**
  *
  * Method scans through vector an directs all media to paint themselves on given graphics context.
  *
  * @param g Graphics
  */
  void paintReflectionThings(Graphics g, ReflectionThing rightThing){
    ReflectionThing neighbor=null;
    for (Enumeration e = thingVector.elements(); e.hasMoreElements();){  // paint the wave
        ReflectionThing t = (ReflectionThing)e.nextElement();
        if (t!=null) t.paintOS(g);
        neighbor=t;
    }
    paintLast(g,rightThing);
    g.setColor(Color.black);
    if(mode==QM_MODE)g.drawLine(0,currenth/2,currentw,currenth/2);
    for (Enumeration e2 = thingVector.elements(); e2.hasMoreElements();){  // paint the text
        ReflectionThing t = (ReflectionThing)e2.nextElement();
        if (t!=null) t.paintValue(g);
    }

    for (Enumeration e = drawingThings.elements(); e.hasMoreElements();){  // paint the wave
      Object t = (Object)e.nextElement();
    }

  }
  void paintWave(Graphics g){
      if(showPhaseColor &&  mode == QM_MODE ) paintWave_color( g);
      else paintWave_function( g);
  }


  void paintWave_color(Graphics g){
    if(!showWave || this.leftWave.length<2)return;
    for (int i=0; i<this.leftWave.length; i++){
          xpoints[i] = i;
          if(mode==QM_MODE) ypoints[i] = (int)(Math.sqrt(leftWave[i]*leftWave[i]+rightWave[i]*rightWave[i])*ppu);
          else ypoints[i] = (int)((leftWave[i]+rightWave[i])*ppu);
          g.setColor(colorFromPhase(Math.atan2(leftWave[i],rightWave[i])));
          g.drawLine(i,+this.currenth/2-ypoints[i],i,+this.currenth/2+ypoints[i]);

    }
    //g.setColor(waveColor);
    //g.drawPolyline(xpoints,ypoints,this.leftWave.length);
  }

  void paintWave_function(Graphics g){
    if(!showWave || this.leftWave.length<2)return;
    for (int i=0; i<this.leftWave.length; i++){
          xpoints[i] = i;
          if(mode==QM_MODE){
              ypoints[i] = -(int)(Math.sqrt(leftWave[i]*leftWave[i]+rightWave[i]*rightWave[i])*ppu)+this.currenth/2;
          } else ypoints[i] = (int)((leftWave[i]+rightWave[i])*ppu)+this.currenth/2;
    }
    ypoints[this.leftWave.length-1] = ypoints[this.leftWave.length-2];  // sometimes the last pixel does not have the correct value due to roundoff.
    g.setColor(waveColor);
    g.drawPolyline(xpoints,ypoints,this.leftWave.length);
  }
/*
  void paintLeftWave(Graphics g){
    if(!showLWave)return;
    for (int i=0; i<this.leftWave.length; i++){
          xpoints[i] = i;
          ypoints[i] = (int)((leftWave[i])*ppu)+this.currenth/2;
    }
    g.setColor(Color.red);
    g.drawPolyline(xpoints,ypoints,this.leftWave.length);
  }

  void paintRightWave(Graphics g){
    if(!showRWave)return;
    for (int i=0; i<this.leftWave.length; i++){
          xpoints[i] = i;
          ypoints[i] = (int)((rightWave[i])*ppu)+this.currenth/2;
    }
    g.setColor(Color.blue);
    g.drawPolyline(xpoints,ypoints,this.leftWave.length);
  }
*/

  public void paintLast(Graphics g, ReflectionThing rightThing){
    ReflectionThing t=rightThing;
    if(t==null){ //we have a vacuum
        return;
    }
    t.fillToEnd(g); // fills the right and left wave arrays.
    //paintLeftWave( g );
    //paintRightWave( g );
    paintWave( g );
    if(mouseDown)paintCoords(g, mousex, mousey);
  }

  void paintCoords( int xPix,int yPix){
        if(!coordDisplay && !ampDisplay && !phaseDisplay ) return;
        Graphics g=getGraphics();
        paintCoords( g,  xPix, yPix);
        g.dispose();
  }

  void paintCoords(Graphics g, int xPix,int yPix){
        if(!coordDisplay && !ampDisplay && !phaseDisplay ) return;
        String msg="";
        if(coordDisplay) msg +="x="+mouseFormat.form(xFromPix(xPix))+ " y="+mouseFormat.form(yFromPix(yPix));
        if(ampDisplay){
          double y;
          if(mode==QM_MODE){
              y = Math.sqrt(leftWave[xPix]*leftWave[xPix]+rightWave[xPix]*rightWave[xPix]);
              msg +=" |Psi|="+mouseFormat.form(y);
          } else{
              y =leftWave[xPix]+rightWave[xPix];
              msg +=" E="+mouseFormat.form(y);
          }
        }
        if(phaseDisplay){
          double y;
          if(mode==QM_MODE){
              //y = Math.atan2(leftWave[xPix],rightWave[xPix]);
              y = Math.atan2(rightWave[xPix],-leftWave[xPix]);
              msg =msg + " " + owner.label_phase+" "+mouseFormat.form(y);
          } else{
              msg = msg+ " " + owner.label_no_phase;
          }
        }
        if(msg.equals("")) return;
        java.awt.Rectangle r = this.getBounds();
        g.setColor(Color.yellow);
        FontMetrics fm=g.getFontMetrics(g.getFont());
        boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
        g.fillRect(0,r.height-20,boxWidth,20);
        g.setColor(Color.black);
        g.drawString(msg,10,r.height-5);
        g.drawRect(0,r.height-20,boxWidth-1,20);
  }

  public int getPixWidth(){ return currentw;}
  public int getPixHeight(){ return currenth;}

  public double xFromPix(int xpix){
       return (xpix)/(1.0*ppu);
  }

  public double yFromPix(int ypix){
       int yo=currenth/2;
       return (yo-ypix)/(1.0*ppu);
  }


  public int pixFromX(double x){
    return (int)(ppu*x);
  }

  public int pixFromY(double y){
    return (int)(currenth/2.0-y*ppu);
  }

  public void update(Graphics g){
    paint(g);
  }

  public void paintOffScreen(){
      paintOSI();
      Graphics g = this.getGraphics();
      if(g==null || osi==null) return;
      g.drawImage(osi,0,0,currentw,currenth,this);
      g.dispose();
  }

  public void paint(Graphics g){
     if (osi==null|| currentw != this.getSize().width || currenth != this.getSize().height) {
      setArrayBounds();
     }
     if (osi==null  || this.owner.clock.isRunning()) return;
     g.drawImage(osi,0,0,currentw,currenth,this);
  }

  /**
   *
   * Method paints small text box on spectrum to display the wavelength at
   * a given coordinate.
   *
   * @param xcoord int x-coordinate in pixels of position on spectrum
   *
   */
   void paintMessage(Graphics g){
      FontMetrics fm=g.getFontMetrics(g.getFont());
      int boxW=Math.max(110,10+fm.stringWidth(message));
      g.setColor(Color.yellow);
      g.fillRect(currentw-boxW-3,currenth-18,boxW,15);
      g.setColor(Color.black);
      g.drawString(message,currentw-boxW,currenth-6);
  }

  /**
  *
  * Method paints simple color legend on given graphics context
  *
  *
  * @param g Graphics
  */
  void paintLegend(Graphics g){
     FontMetrics fm=g.getFontMetrics(g.getFont());
     String leftStr = owner.label_left_wave;
     String rightStr = owner.label_right_wave;
     String sumStr = owner.label_sum;
     int boxW=Math.max(110,10+fm.stringWidth(rightStr));
     int boxH=45;//fm.getHeight();
     //g.setColor(Color.black);
     //g.drawRect(4,4,boxW,boxH);
     g.setColor(Color.white);
     g.fillRect(5,5,boxW-2,boxH-2);
     g.setColor(Color.blue);
     g.drawString(rightStr,10,20);
     g.setColor(Color.red);
     g.drawString(leftStr,10,32);
     g.setColor(Color.green);
     g.drawString(sumStr,10,44);
  }

  /**
  *
  * Method adds small caption at bottom center of a medium
  *
  * @param id hashcode id of medium
  * @param cap String
  */
  public void setCaption(int id, String cap){
        ReflectionThing t = getReflectionThing(id);
        if(t==null) return;
        t.caption = cap;
        t.showCaption = true;
  }

  /**
   * Set the title.
   *
   * @param              str Title string.
   */
  public void setTitle(String str){
     titleStr=str;
  }

  /**
  *
  * Method removes caption from particular medium
  *
  * @param id int hashcode id of medium
  */
  public void clearCaption(int id){
       ReflectionThing t = getReflectionThing(id);
       if(t==null) return;
       t.showCaption = false;
       if (autoRefresh) paintOffScreen();
  }
  /**
  *
  * Method removes captions from all media
  *
  */
  public void clearAllCaptions(){
      for (Enumeration e = thingVector.elements(); e.hasMoreElements();){
        ReflectionThing t = (ReflectionThing) e.nextElement();
        t.showCaption = false;
      }
      if (autoRefresh) paintOffScreen();
  }

  public void pause(){
      owner.pause();
      //if (owner.clock.isRunning())
      //  owner.clock.stopClock();
  }

  public void forward(){
      owner.forward();
   // if (!owner.clock.isRunning())
     //  owner.clock.startClock();
  }


  private void jbInit() throws Exception {
    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        this_mouseMoved(e);
      }
      public void mouseDragged(MouseEvent e) {
        this_mouseDragged(e);
      }
    });
    this.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
        this_componentResized(e);
      }
    });
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }
      public void mouseEntered(MouseEvent e) {
        this_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        this_mouseExited(e);
      }
      public void mouseReleased(MouseEvent e) {
        this_mouseReleased(e);
      }
    });
  }

  private ReflectionThing isInsideThing(int x, int y){
      for (Enumeration en = thingVector.elements(); en.hasMoreElements();){
          ReflectionThing t = (ReflectionThing) en.nextElement();
          if (t.isInside(x,y)){ return t;}
      }
      return null;
  }

  void this_mousePressed(MouseEvent e) {
      if(( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){
           if(osi==null) return;
           WaveFrame waveFrame=new WaveFrame(osi);
           waveFrame.show();
        }
      else{
          mouseDown=true;
          mousex = e.getX();
          mousey = e.getY();
          for (Enumeration en = thingVector.elements(); en.hasMoreElements();){
              ReflectionThing t = (ReflectionThing) en.nextElement();
              if (t.isNearBoundary(mousex,mousey)){
                  if(owner.clock.isRunning()){
                      pause();
                      wasRunning=true;
                  }
                  dragThing=t;
              }
          }
          insideThing=this.isInsideThing(mousex,mousey);
      }
  }

  void this_componentResized(ComponentEvent e) {
    setArrayBounds();
    paintOffScreen();
  }

  void this_mouseMoved(MouseEvent e) {
      for (Enumeration en = thingVector.elements(); en.hasMoreElements();){
        ReflectionThing t = (ReflectionThing) en.nextElement();
        if (t.isNearBoundary(e.getX(),e.getY())){
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            return;
        }
      }
     setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

  }

  void this_mouseDragged(MouseEvent paramMouseEvent)
  {
    int i = i = this.mousey - paramMouseEvent.getY();
    if ((this.insideThing != null) && (this.insideThing.dragPotential))
    {
      this.insideThing.setPotential(this.insideThing.potential + i / 100.0D);
      recalc();
    }
    else if ((this.dragThing == null) && (this.dragEnergy))
    {
      if (this.mode == EM_MODE)
      {
        setWavelength(Math.max(this.lambda + i / 100.0D, this.highestN * 3.0D * 3.141592653589793D / this.ppu));
      }
      else
      {
        double d = this.qmEnergy + i / 100.0D;
        d = Math.max(d, this.lowestN - 1.0D);
        d = Math.max(d, this.enMin);
        d = Math.min(d, this.enMax);
        setQMEnergy(d);
      }
    }
    this.mousex = paramMouseEvent.getX();
    this.mousey = paramMouseEvent.getY();
    if (this.dragThing != null)
    {
      setMediaWidth(this.dragThing.hashCode(), (paramMouseEvent.getX() - this.dragThing.left) / this.ppu);
      this.owner.updateDataConnections();
    }
    paintCoords(this.mousex, this.mousey);
  }

  void this_mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

  }

  void this_mouseExited(MouseEvent e) {
       setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       dragThing=null;
      // forward();
  }

  void this_mouseReleased(MouseEvent e) {
       mousex = e.getX();
       mousey = e.getY();
       dragThing=null;
       insideThing=null;
       mouseDown=false;
       boxWidth=0;
       paintOSI();
       repaint();
       if(wasRunning){
           forward();
           wasRunning=false;
       }
  }

  private Color colorFromPhase(double phi){
        int offset=(int)(50+50*phi/Math.PI); // get offset in range 0 .. 100
        return colors[offset];
  }

  private void initColors(){
        int r,g,b;
        double pi=Math.PI;
        for(int i=0; i<101; i++)
        {
            b=(int)(255*Math.sin(pi*i/100)*Math.sin(pi*i/100));
            g=(int)(255*Math.sin(pi*i/100+pi/3)*Math.sin(pi*i/100+pi/3));
            r=(int)(255*Math.sin(pi*i/100+2*pi/3)*Math.sin(pi*i/100+2*pi/3));
            colors[i]=new Color(r,g,b);
        }
  }

  /**
   * Gets the object identifier for the wave  data source.
   *
   * @return int the identifier
   */
   public int getWaveID(){
      if(waveDataSource==null){
        waveDataSource= new WaveDataSource();
      }
      return waveDataSource.getID();
   }

// inner class used for wave data connection
  public class WaveDataSource extends Object   implements edu.davidson.tools.SDataSource{
    String[] varStrings= new String[]{"x","re","im","a"};    // position and amplitude
    double[][] ds=new double[currentw][4];  // the datasource state variables;

    WaveDataSource(){ // Constructor
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
      if(ds==null || leftWave==null || leftWave.length<4) return new double[1][4];
      if(ds.length!=leftWave.length)ds=new double[leftWave.length][4];
      for(int i=0;i<leftWave.length;i++){
          ds[i][0]=i/(double)ppu;  //x
          ds[i][1]=rightWave[i];   //re or right wave
          ds[i][2]=leftWave[i];    //im or left wave
          if(mode==QM_MODE)  ds[i][3] = Math.sqrt(leftWave[i]*leftWave[i]+rightWave[i]*rightWave[i]);
          else ds[i][3]  = leftWave[i]+rightWave[i];
      }
      return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet o){;} // the owner is the outer class and cannot be set.
    public SApplet getOwner(){return owner;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  }
}
