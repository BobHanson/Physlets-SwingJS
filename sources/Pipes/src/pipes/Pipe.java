/*
**************************************************************************
**
**                      Class  Pipe
**
**************************************************************************
**
** class Pipe extends EtchedBorder
**
** The drawing canvas for PipeApplet applet.
** Controls all calculations and all animation.
**
*************************************************************************/
package pipes;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.*;
import java.awt.event.*;
import java.util.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;
import edu.davidson.graphics.*;
import edu.davidson.numerics.*;

public class Pipe extends EtchedBorder implements SDataSource, SStepable{
  PipeApplet owner;
  int xorigin =0;
  Parser pparser = null;
  String xstr = "cos(t)";
  Hashtable thingTable = new Hashtable(7);
  //Image img = null;
  boolean dbox = false;
  Image osi = null;
  Color wallcolor = new Color(64,64,64);
  Color navy = new Color(0,0,170);
  Color forrest = new Color(0,110,40);
  Color cran = new Color(170,0,0);
  Thing dragthing=null;
  Format format = new Format("%-+7.2g");
  boolean change=true; //boolean tells whether or not  change has occured requiring new setup.
  int xmax=100; int xmin=4;   //xmin and xmax are the pixel boundaries for the piston in U-Drag mode.  The piston cannot
                               // go to the left of xmin nor to the right of xmax from the left of the panel.

  String mode = "s";
  double xoffset = 3.5;
  int xshift=0;
  int yshift=0;
  int wallW=8;
  int dnum=0;
  int delxprev = 0;
  int x2=0, y2 = 0;
  int x3,y3;
  //boolean running=false;
  boolean adefaultscale= true;
  boolean udefaultscale= true;
  boolean pdefaultscale= true;
  double scale = 2;
  double ascale = 1;
  double ascale2 = 1;
  double pscale = 2;
  double pscale2 = 2;
  double uscale = 4;
  double uscale2 = 4;
  double sfactor = 1;
  double pfactor = 0;
  double maxp=1;
  int currentWidth=0, currentHeight = 0;
  //int qf = 4;     changed by W. Christian
 // int qf = 1;
  //int l = 0;
  double dd=0;          //real position change in wave
  int deld = 0;         //pixel change in wave position
  int originx = 0;
  double dx=0;          //real position change in piston
  int delx = 0;         //pixel change in piston position

  int xip, xfp, xiw, xfw;
  boolean detect = false;
  double xpos1=1;        //initial xposition of front of piston
  double xpos2=0;        //final xposition of front of piston
  int cx=0;              //current x position; for mouse purposes
  int cy=0;              //current y position; for mouse purposes
  int cposx=0;
  int cposy=0;
  double pArray[] = null;
  double wArray[] = null;
  double pArray2[] = null;
  double prArray[] = null;
  double prArray2[] = null;

  boolean grayscale = true;
  int redmax=255, greenmax=255, bluemax=255;
  int red[] = new int[503];
  int green[] = new int[503];
  int blue[] = new int[503];
  int sleeptime=10;
  double sensitivity = .6;
  double vp = 0;
  double vw=20;       //wave velocity in units/ time
  int ppu=60;        //points per unit
  int fps;        //frames per second
  double d = 0.4;    //time step
  double time=0;     //absolute time
  boolean paintnow = true;
  boolean timeDisplay=true;

  //data source variables
  String[] varStrings = {"x","p"};
  double[][] vars = new double[1][2];   // position, pressure


  //constructor
  public Pipe(PipeApplet o) {
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    owner=o;
    makeSpectrum();
    try{SApplet.addDataSource(this);} catch(Exception ex){ex.printStackTrace();}
  }
  // SDataSource methods added by W. Christian
  public void setOwner(SApplet o){owner=(PipeApplet) o; }
  public SApplet getOwner(){return owner;}
  public int getID(){return this.hashCode();}
  public String[]   getVarStrings(){return varStrings; }
  public double[][] getVariables(){return vars; }


  public Thing getThing(int id){
       Thing t=null;
       for( Enumeration e=thingTable.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t.hashCode()==id){
           return t;
         }
       }
       return null;
    }
/*
  synchronized public void start(){
  }

  synchronized public void stop(){
  } */

  public void update(Graphics g){
  paint(g);
  }

  public synchronized void makeImage(){
    currentWidth = this.getSize().width;
    currentHeight = this.getSize().height;
    if(currentWidth<10 || currentHeight<10) return;
    osi = createImage(currentWidth,currentHeight);
  }

  public synchronized void setup(){
    time = owner.clock.getTime();
    if((this.getSize().width<10)||(this.getSize().height<10))return;
    if(osi==null || currentWidth != this.getSize().width || currentHeight != this.getSize().height )makeImage();
    int i;
    pArray = new double[currentWidth];
    pArray2 = new double[currentWidth];
    prArray = new double[currentWidth];
    prArray2 = new double[currentWidth];
    wArray = new double[99];
    vars = new double[currentWidth][2];   // position, pressure  Added by W. Christian;

    for (i=0; i<(currentWidth); i++) {
        pArray[i]=0.5*maxp;    //sets ambient pressure to 1/2.
        pArray2[i]=0;
        }
    if(mode.equals("a")) {
          if (adefaultscale) scale=ascale;
          else scale = ascale2;
          xoffset=0;   // Changed 6/14/99
      }
      else if (mode.equals("s")) {
          if (pdefaultscale) scale=pscale;
          else scale = pscale2;
          xoffset=1;
          }
      else if (mode.equals("u")) {
          if (udefaultscale) scale=uscale;
          else scale = uscale2;
          xoffset=3.5;
      }

    makeWave(pArray,currentWidth); // added by W. Christian to make sure inital values are correct.
    // added by W. Christian for data source.
    for (i=0; i<currentWidth; i++) {
            if (mode.equals("p")) vars[i][0]=((i-xoffset*ppu +xshift)/(double)ppu);   // added by W. Christian for data source.  position variable
            else vars[i][0]=(i/(double)ppu);
    }
   // thingTable.clear();    This is now done in MakeThings.
    makeThings(currentWidth,currentHeight);
    time = owner.clock.getTime();
    //osi = createImage(currentWidth,currentHeight);
    paintOSI(currentWidth,currentHeight);
    Graphics g = getGraphics();
    paint(g);
    g.dispose();
    change=false;
    }

  public void checkSize(){
    if ((currentWidth != this.getSize().width)||(currentHeight!= this.getSize().height)) change = true;
    if (change) setup();

  }

  /**
  *
  *Method steps calculation forward in time.
  *
  *@param width  int  width of pipe in pixels
  *@param height int  height of pipe in pixels
  *
  */
  public void step(double dt, double time){
    this.time=time+dt;
    calculate(dt);
    owner.clearData(this.hashCode());   // added by W. Christian to clear the pressure data.
    owner.updateDataConnections();
  }

  /**
  *
  *Method steps calculation backward in time.
  *
  *
  */
  public void stepBack(){

  }

  /**
  *
  *Calculates presures in pipe.
  *
  *@param width  int  width of pipe in pixels
  *@param height int  height of pipe in pixels
  *
  */
  public void calculate(double dt){
    double time = owner.clock.getTime();
    if((this.getSize().width!=currentWidth) || (this.getSize().height!=currentHeight)) setup();
    if (mode.equals("s")){
      xpos1=pparser.evaluate(time);
      makeWave(pArray,currentWidth);
      Source s = (Source)thingTable.get("source");
      s.setValue(xpos1);

      }
    else if (mode.equals("u")) {
      xip=cposx;
      delx=xfp-xip;
      xiw = xip;
      dd=vw*dt;                                 //real displacement of wave (double variable)
      deld = (int)(ppu*dd);
      xfw = xiw+deld;                           //number of array units to shift the wave
      pfactor=delx/(double)deld;
      moveWave(pArray,pArray2,currentWidth);
      }
    else {makeWave(pArray,currentWidth);}    //analytic mode;
    paintOSI(currentWidth,currentHeight);  // will also fill the var array for the data source.
    Graphics g = getGraphics();
    paint(g);
    if (dbox) dataBox(cx,cy,g);
    g.dispose();
    delxprev=delx;
    cposx=xfp;
  }

  /*
  void reset(){
    time = 0;
    //change=true;
    //setup();
    }
  /*

  /**
  *
  *Sets parser.
  *
  *@param xstr String   string to be parsed
  *
  *
  */
  public boolean setParse(String str){
    xstr=str;
    boolean noError=true;
    xstr.toLowerCase().trim();
    if (mode.equals("s")){                   //check for failure. turn box red.
      Parser pparser = new Parser(1);
      pparser.define(xstr);
      pparser.defineVariable(1,"t");
      pparser.parse();
      if(pparser.getErrorCode() != Parser.NO_ERROR){     // error checkeing added by W. Christian
         noError=false;
         System.out.println("Failed to parse f(t)): "+xstr);
         System.out.println("Parse error in MathFunction: " + pparser.getErrorString() +
                   " at function 1, position " + pparser.getErrorPosition());
      }
      this.pparser=pparser;
    }
    if (mode.equals("a")){
      Parser pparser = new Parser(2);
      pparser.define(xstr);
      pparser.defineVariable(1,"x");
      pparser.defineVariable(2,"t");
      pparser.parse();
      if(pparser.getErrorCode() != Parser.NO_ERROR){
         noError=false;
         System.out.println("Failed to parse f(x,t)): "+xstr);
         System.out.println("Parse error in MathFunction: " + pparser.getErrorString() +
                   " at function 1, position " + pparser.getErrorPosition());
      }
      this.pparser=pparser;
    }
   change=true;
   return noError;
  }




  /**
  *
  * Method adds a detector for plotting and returns hashcode of new detector
  *
  * @param d  boolean
  *
  */

  //New version-- changed 6/14/99

  public int addDetector(int xpos, int ypos){
  dnum++;
  if (xpos < xshift/ppu) xpos = xshift/ppu;       //xpos is the number of pixel units to right of xoffset, which is the right edge of the source in real units.
  int x=xpos+(int)(xoffset*ppu);   //x is number of pixels from left edge of panel.
  if (mode.equals("u")) x=Math.max(x,xmax);
  int y=Math.max(ypos,wallW); //y is the number of pixel units south of the north panel boundary. y can't be less than the wall width.
  Detector detect = new Detector(Math.min(x,currentWidth-8),Math.min(y,currentHeight-wallW-8),8,8,Color.orange,this,true,true);
  //try{SApplet.addDataSource(detect); }catch (Exception e){e.printStackTrace();}
  int dhash=detect.getID();
  thingTable.put(String.valueOf(dhash),detect);
  repaint();
  return dhash;         //thing.getID()
  }

  public int addDetector(double xpos, double ypos){
     return addDetector((int)((xpos)*ppu), (int)(ypos*ppu+currentHeight/2));
  }

  /**
  *
  * Method removes all detectors
  *
  *
  *
  */
  public void removeAllDetectors(){
    Thing t;
    for (Enumeration e=thingTable.elements(); e.hasMoreElements();)
      {
      t = (Thing)e.nextElement();
      if (t instanceof Detector) removeDetector(t.getID());
      }
  }

  /**
  *
  * Method removes all things EXCEPT detectors
  *
  *
  *
  */
  public void removeThings(){
    Thing t;
    Hashtable ht=(Hashtable)thingTable.clone();
    thingTable.clear();
    for (Enumeration e=ht.elements(); e.hasMoreElements();){
      t = (Thing)e.nextElement();
      if (t instanceof Detector ){
        thingTable.put(String.valueOf(t.hashCode()),t);
      }
    }
  }

  /**
  *
  * Method removes one detector
  *
  * @param id  int  hashcode of detector to be removed
  *
  */
  public void removeDetector(int id){
     owner.removeDataSource( id); // added by w. Christian to remove old data sources.
     thingTable.remove(String.valueOf(id));
     dnum--;
  }

  /**
  *
  * Method creates walls, detectors and piston
  *
  * @param width     width of pipe
  * @param height    height of pipe
  *
  */
  public void makeThings(int width, int height){
    removeThings();
    Thing wall1 = new Thing(0,0,width,wallW,navy,this,true,false);
    Thing wall2 = new Thing(0,height-wallW,width,wallW,navy,this,true,false);
    Piston piston = new Piston(0,wallW,(int)(xoffset*ppu),height-2*wallW,cran,this,true,true);
    //(int xpos, int ypos, int width, int height, Color c, Pipe o, boolean e)
    Source source = new Source(0,wallW,(int)(xoffset*ppu),currentHeight-2*wallW,Color.red,this,true,false);
    //if (mode.equals("u")) {piston.isMoveable = true; piston.isPiston=true; thingTable.put("piston",piston);}
    if (mode.equals("u")) {thingTable.put("piston",piston);}
    if (mode.equals("s")) {thingTable.put("source",source);}
    thingTable.put("wall2",wall2);
    thingTable.put("wall1",wall1);
  }

  /**
  *
  *
  *
  *
  */
  public int xToPix(double x){
    int q;
    q = (int)(x*ppu-originx);
    return q;
  }

  /**
  *
  *
  *
  *
  */
  public double pixToX(int px){
    double q;
    q = (px-originx)/ppu;
    return q;
  }

  public void reflect(double p[], double pr[]){
      int i;
      int d = Math.abs(deld);
      for (i=currentWidth-1; i<deld; i--){
        pr[i]=p[i-deld];   //moves reflected wave forward
      }
      for (i=0; i<deld; i++){
        pr[i]=p[currentWidth-i-deld];
      }
  }

  /**
  *
  *Moves wave forward in pipe
  *
  *@param p[]  double  array of pressures
  *@param p2[] double  auxilary array of pressures
  *@param l int   length of p[]
  */
  public void moveWave(double p[], double p2[], int l){
    int i;
    if (delx>0){
      //  reflect();
        System.arraycopy(p,xiw,p2,xfw,l-xfw);   //copies 1st array to second, shifting by deld;
        for (i=0; i<xfw; i++){
          p2[i]=maxp/2;}               //finishes copying process, moving last entries to the beginning.
        System.arraycopy(p2,0,p,0,l);
        pushPressure(p);
        return;
        }
    else if (delx==0){
        System.arraycopy(p,xiw,p2,xfw,l-xfw);   //copies 1st array to second, shifting by deld;
        for (i=0; i<xfw; i++){
          p2[i]=maxp/2;}               //finishes copying process, moving last entries to the beginning.
        System.arraycopy(p2,0,p,0,l);
        pushPressure(p);
        return;
        }
    else if (delx<0){
        System.arraycopy(p,xiw,p2,xfw,l-xfw);
        for (i=0; i<xfw; i++){
          p2[i]=maxp/2;}               //finishes copying process, moving last entries to the beginning.
        System.arraycopy(p2,0,p,0,l);
        pushPressure(p);
        }
  }
  /**
  *
  * Algorithm for U-Drag Mode that updates pressure array
  *
  *@param p[]  pressure array
  *
  */
  public void pushPressure(double p[]){
    int i;
    double ambient = maxp/2;

     if (delx==0){}
     else if (xfp<xfw){
        for (i=xfp; i<xfw; i++) p[i]+=p[i]*pfactor;
     }
     else if (xfp>=xfw){
        for (i=xfp; i<xfp+4; i++) p[i]+=p[i]*pfactor;
     }


    for (i=0; i<99; i++){wArray[i]=p[xfp+i];}
  }
  /**
  *
  *  Method moves piston when in U-Drag Mode
  *
  *@param xchange  int  change in xposition.
  *
  */
  public void movePiston(int xchange){     //for u mode, xchange is a scaled change in pixel position.
   Piston piston=(Piston)thingTable.get("piston");
   if (mode.equals("u")){
      int x = Math.max(piston.w+xchange,xmin);    //xmin and xmax are the pixel boundaries for the piston.
      x=Math.min(x,xmax);
      piston.setW(x);
      xfp=x;
      }
  }

  /**
  *
  * Method uses analytic function to create pressure array
  *
  *@param p[]  array of pressures
  *@param w   length of array
  */
  public void makeWave(double p[], int w){                 //for analytic mode
    if (pparser==null) return;                             // added by W. Christian
    int i;
    double pi = Math.PI;
    double xpos;
    double datamax=0;                                     // use f(t-x/v) for piston mode
    double time = owner.clock.getTime();
    for (i=0; i<w; i++){
      xpos =(i+xshift)/((double)(ppu));
      if (mode.equals("s")) p[i] = pparser.evaluate(time-(xpos-xoffset)/vw);
      else p[i] = pparser.evaluate(xpos,time);    // else
      //if (p[i]>datamax) datamax = p[i];
    }

  // for (i=0; i<w; i++) p[i]=p[i]/datamax);
  }

  //hwidth hheight global  if change use current width

  /**
  * Method paints off-screen image
  *
  * @param w pixel width of image
  * @param h pixel height of image
  */

  public synchronized void paintOSI(int w, int h){
    if(w<=0 || w<=0){
    	  return;
    }
    if((currentWidth!=w) || (currentHeight!=h)) setup();
    int numAvg=1;
    if(this.mode.equals("u" ))numAvg=7;  // average user actions
    int tempnum=0;
    int val = 0;
    double arg=0;
    double sum=0;
    double[] temp=new double[numAvg];
    int i, index=0;
    Graphics osg = osi.getGraphics();
    for (i=0; i<w; i++){                  //i=xfp; i<w-wfp
      if (grayscale) {
        arg = (pArray[(w-i-1)]-(maxp/2))*scale;      //arg = (pArray[qf*(w-i-1)]-(maxp/2))*scale;
        sum-=temp[index];  // subtract the old value from the sum
        temp[index]=arg;   // store the new value in the temp array
        sum+=arg;  // add the new value to the sum
        tempnum=i+1;
        index++;
        if(index>=numAvg)index=0;
        val = (int)(255*(0.5+(Math.atan(sum/Math.min(numAvg,tempnum))/Math.PI)));
        vars[i][1]=pArray[i];  // Added by w. Christian. Fill the pressure variable for the data source.
      }
      else val = (int)(499*(0.5+(Math.atan(arg)/Math.PI)));  //(int)(499*pArray[qf*i]);
      Color col = new Color(red[val],green[val],blue[val]);
      osg.setColor(col);
      osg.drawLine(w-i-1,0,w-i-1,h);
      }
    paintThings(osg);

    osg.dispose();
    }

  /**
  *
  *
  *
  *
  */
  public void paintThings(Graphics g){

    Thing t;
    for (Enumeration e=thingTable.elements(); e.hasMoreElements();){
      t = (Thing)e.nextElement();
      t.paintOS(g);
      }

  }

  public void paint(Graphics g){
    if(!owner.isAutoRefresh()) return;
    int width = getSize().width;
    int height = getSize().height;
    Image img;
    if(osi==null || osi.getWidth(null)<10 || osi.getHeight(null)<10 ||width<10 || height<10)return;
    synchronized(osi){
       img= osi;
    }
    if (img==null){
      //super.paint(g);
      g.setColor(Color.blue);
      FontMetrics fm = g.getFontMetrics();
      int x = (width - fm.stringWidth(owner.label_wait))/2;
      int y = height/2;
     }
    else{
     // g.setColor(Color.green);
     //g.fillRect(0,0,width,height);

     g.drawImage(img,0,0,width,height,this);
     if (!owner.clock.isRunning()){
          Graphics osg=img.getGraphics();
          osg.fillRect(x3,y3,8,8);
          osg.dispose();
          paintThings(g);}
          timeBox(g);
          if (dbox || dragthing!=null) dataBox(cx,cy,g);
    }
  }

  public void setSpectrumRGB(int rmax, int gmax, int bmax){
    redmax=Math.min(255,rmax);
    greenmax=Math.min(255,gmax);
    bluemax=Math.min(255,bmax);
    makeSpectrum();
  }

  private void makeSpectrum(){
    int i,j;
    if (grayscale){
      for (i=0; i<256; i++){
        red[i]=(int)(i*(redmax/255.0));
        blue[i]=(int)(i*(greenmax/255.0));
        green[i]=(int)(i*(bluemax/255.0));
        }
      }

    else{
      for (i=0; i<249; i++)
        {red[i]=249;
        blue[i]=i;
        green[i]=0;}
      for (i=249; i<500; i++)
        {red[i]=499-i;
        blue[i]=249;
        green[i]=0;}
      }
    red[500]=0;       //piston color
    green[500]=128;
    blue[500]=100;

    red[501]=64;         //color of walls
    green[501]=64;
    blue[501]=64;

    red[502]=255;     //color of outside border
    green[502]=215;
    blue[502]=160;

  }

  /**
  *
  * Sets speed of wave
  *
  * @param sp double
  */
  void setSpeed(double sp){this.vw = sp;change=true;}


  /**
  *
  * Sets pixels per unit
  *
  * @param p int
  */
  void setPoints(int p){this.ppu = p;change=true;}
  /**
  *
  * Sets Contrast
  *
  * @param c  double
  * @param m  string  mode
  */
  void setContrast(double c,String m){
    if(m.equals("a")) {ascale=c;}
    else if (m.equals("s")) {pscale=c;}
    else if (mode.equals("u")) {uscale=c;}
    change=true;
  }
  /**
  *
  *
  *
  *
  */
  void setFPS(int f){this.fps = f;change=true;}
  /**
  *
  *
  *
  *
  */
  void setDelt(double d){this.d = d;change=true;}
  /**
  *
  *
  *
  *
  */
  synchronized void setMode(String m){ this.mode=m; dnum=0; setup();}

  private void jbInit() throws Exception {
    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

      public void mouseDragged(MouseEvent e) {
        this_mouseDragged(e);
      }
      public void mouseMoved(MouseEvent e) {
        this_mouseMoved(e);
      }
    });
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        this_mouseReleased(e);
      }
      public void mouseEntered(MouseEvent e) {
        this_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        this_mouseExited(e);
      }
    });
  }


  void this_mousePressed(MouseEvent evt) {
    dragthing=null;
    cx = evt.getX();
    cy = evt.getY();
    for (Enumeration en = thingTable.elements(); en.hasMoreElements();){
      dragthing = (Thing)en.nextElement();
      if ((dragthing.isInside(cx,cy))&&(dragthing.isMoveable))
          {x3=dragthing.x;   //these keep track of the original detector position, if it is moved.
                             //when moved while the wave is frozen, the old position (x3,y3) is filled in black.
           y3=dragthing.y;
           setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); break;} //setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
      else dragthing = null;
      }                                     //now, cursor is in t. get original x and original y
    if ( owner.clock.isRunning()) dbox=true;
    Graphics g=getGraphics();
    dataBox(cx,cy,g);
    g.dispose();
  }

  /**
  *
  *
  *
  *
  */

//new method-- changed 6/14/99

  void moveThing(Thing t, int xchange, int ychange){
    boolean boundError=false;   //boundError turns true if the detector or thing moves out of the bounds
    int xnew= t.x+xchange;
    int ynew= t.y+ychange;
    if (mode.equals("u")){     //detector moves too close to moving piston
        if (xnew <= xmax) {
          boundError=true;
          xnew=xmax+1;
        }
    }
    else {
       if (xnew <=(int)(xoffset*ppu)){     //detector reaches bounds of wave source
          boundError=true;
          xnew=(int)(xoffset*ppu)+1;
       }
    }

    if (xnew >= currentWidth-8){     //detector reaches right edge of panel
          boundError=true;
          xnew=currentWidth-9;
       }
    if (ynew <= wallW){     //detector reaches top edge of panel
          boundError=true;
          ynew=wallW+1;
       }
    else if (ynew >= currentHeight-wallW-8){      //detector reaches bottom edge of panel
          boundError=true;
          ynew=currentHeight-wallW-9;
       }
    t.setPosXY(xnew,ynew);
    //when a boundError occurs, the detector is released, so that the dragging hand doesn't become misaligned with the detector
    if (boundError) {dragthing=null; setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));}
    if (!owner.clock.isRunning()){  // no need to paint if the clock is running
      paintOSI(currentWidth,currentHeight);
      Graphics g = this.getGraphics();
      paint(g);
      g.dispose();
    }
  }

/**
  *
  *
  *
  *
  */

//old method  6/14/99

/*

  void moveThing(Thing t, int xchange, int ychange){

    int xnew = Math.min(t.x+xchange,currentWidth-8);
    xnew=Math.max(xnew,xmax);
    int ynew = Math.min(t.y+ychange,currentHeight-10);
    ynew=Math.max(10,ynew);
    t.setPosXY(xnew,ynew);
    if (!running){
      Graphics g = this.getGraphics();
      paint(g);
      g.dispose();
    }
  }
*/

  void this_mouseReleased(MouseEvent e) {
    dbox=false;
    dragthing = null;
    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    if (!owner.clock.isRunning()){ // no need to paint if the clock is running
        paintOSI(currentWidth,currentHeight);
        Graphics g = getGraphics();
        paint(g);
        g.dispose();
    }
  }

  void this_mouseDragged(MouseEvent e) {
    if ((dragthing!=null)&&(dragthing.isMoveable)){
      x2=e.getX();
      y2=e.getY();
      if (dragthing instanceof Piston) movePiston((int)(sensitivity*(x2-cx)));
      else {
          moveThing(dragthing,x2-cx,y2-cy);
          if (!owner.clock.isRunning()){  // no need to update data if the clock is running
              owner.clearData(this.hashCode());   // added by W. Christian to clear the pressure data.
              owner.updateDataConnections();
          }
      }
    }
    cx=e.getX();
    cy=e.getY();
    if (!owner.clock.isRunning()) {  // no need to update if the clock is running
        Graphics g=getGraphics();
        dataBox(cx,cy, this.getGraphics());
        g.dispose();
    }
  }

  void this_mouseMoved(MouseEvent e) {
    int x=e.getX();
    int y=e.getY();
    Thing t;
    for (Enumeration en = thingTable.elements(); en.hasMoreElements();){

      t = (Thing)en.nextElement();
      if ((t.isInside(x,y))&&(t.isMoveable)) {setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); break;}

      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      t = null;}
   t=null;
  }

  void this_mouseEntered(MouseEvent e) {
    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

  void this_mouseExited(MouseEvent e) {
     setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }


  void timeBox(Graphics g){
    if(!timeDisplay) return;
    String msg= owner.label_time+format.form(time);
    FontMetrics fm=g.getFontMetrics(g.getFont());
    int boxW=Math.max(100,15+fm.stringWidth(msg));
    //int boxW=20+fm.stringWidth(msg);
    g.setColor(navy);
    g.fillRect(currentWidth-boxW-8,wallW,boxW+8,23);
    g.setColor(Color.yellow);
    g.fillRect(currentWidth-boxW-4,wallW+4,boxW,15);
    g.setColor(Color.black);
    g.drawString(msg,currentWidth-boxW-1,wallW+15);

  }


  void dataBox(int x,int y, Graphics g){
    double xp=0;
    xp=((x-xoffset*ppu+xshift)/ppu);
    //else xp=(x*qf/ppu);
    int index=x;
    index=Math.max(0,index);
    index=Math.min(pArray.length-1,index);
    String msg= owner.label_amp+format.form(pArray[index])+owner.label_pos+format.form(xp);

    FontMetrics fm=g.getFontMetrics(g.getFont());
    int boxW=Math.max(145,15+fm.stringWidth(msg));
    //int boxW=20+fm.stringWidth(msg);
    g.setColor(navy);
    g.fillRect(currentWidth-boxW-8,currentHeight-wallW-23,boxW+8,23);
    g.setColor(Color.yellow);
    g.fillRect(currentWidth-boxW-4,currentHeight-wallW-19,boxW,15);
    g.setColor(Color.black);
    g.drawString(msg,currentWidth-boxW,currentHeight-wallW-7);
  }

  public void setMouseSensitivity(double ms){
  this.sensitivity = ms;
  }
  public void setPPaintScale(boolean dfault, double psf){
  pdefaultscale=dfault;
  this.pscale2 = psf;
  }
  public void setAPaintScale(boolean dfault, double asf){
  adefaultscale=dfault;
  this.ascale2 = asf;
  }
  public void setUPaintScale(boolean dfault, double usf){
  udefaultscale=dfault;
  this.uscale2 = usf;
  }
  /**
  *
  * Mathod sets pixel width of pipe walls
  *
  * @param w  int
  *
  */
  public void setWallW(int w){
   wallW=w;
  }
}
