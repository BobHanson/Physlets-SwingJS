package optics;
import java.awt.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Vector;

import edu.davidson.display.SScalable;
import edu.davidson.display.Thing;
import soundOut.SoundOut;


public final class Bench extends Panel implements SScalable {
  //int i = 0, //i
 //     si = 0,  //source i
  int   inc,
      pixPerUnit = 100,   //pixels per unit (as in 100 pixels per meter)
      maxNumber = 100,   //maximum number of optic elements (excluding sources)
      maxSNumber = 100,  //maximum number of sources
      widthOfLens, //width of lens, used in figuring focal length from screen shape
      spacingInt, // spacing = 5; //spacing used in isource
      temp1, temp2;  //temporary numbers for use throughout program
  PSource tempPSource = new PSource(this, 0,0,0,0,true,false,1,false);
  Vector v = new Vector();   //optic element Vector
  Vector sv = new Vector();  //source Vector
  Vector things = new Vector();
  //boolean hasThisBeenDoneOnce=false;      //referring to the drawing of the PSource Rays
  Color rayColor=null; //color of rays drawn.
  Color elementColor=null; //default color of objects drawn.
  DecimalFormat df = new DecimalFormat("0.##");
  int[] x = new int[maxNumber];
  int[] y = new int[maxNumber];
  int[] sx = new int[maxNumber];
  int[] sy = new int[maxNumber];
  int xOld,xNew,yOld,yNew,indexOfPSources;
  double mOld,slope,
    yNewDouble;
  double[] f = new double[maxNumber];
  double[] ray = new double[2];
  OpticElement activeElement=null;
  Thing  dragThing=null;
  int isInsideOpticElement;   //0 for not at all, 1 for on body, 2 for on focal point
  boolean isControlDown,
          blocked = false;
  boolean hasBench=false;   // set to true when bench has been added as a drawing object.
  Image osi=null;           //off screen image, used to produce flicker free images (through the off screen graphics, osg)
  int iheight,iwidth;       //size of osi
  int step;  //direction of ray drawings, 1 for to the right, -1 to the left
  private int xDown=0, yDown=0;
  private int xLast=0, yLast=0;
  OpticsApplet owner=null;

  public Bench(OpticsApplet o) {
    owner=o;
    setBackground(Color.black);
    int id=addScreen(2000,0,false,false,1,false);
    OpticElement el=getElement(id);
    el.setPixX(2000, new Rectangle(0,0) );
    id=addScreen(-100,0,false,false,1,false);
    el=getElement(id);
    el.setPixX(-100, new Rectangle(0,0) );
  }

  void createOSI(){
      Rectangle r = getBounds();
      iheight = r.height;
      iwidth = r.width;
      if(iheight>0 && iwidth>0)osi = createImage(iwidth,iheight);  // Java 7 fails if width or height >0
  }

  public int getPixWidth(){ return iwidth;}
  public int getPixHeight(){ return iheight;}

  public double xFromPix(int pix){return pix/(double)pixPerUnit;}
  public int pixFromX(double x){ return (int)Math.round(x*pixPerUnit);}

  public double yFromPix(int pix){ return -(pix-iheight/2.0)/(double)pixPerUnit;}
  public int pixFromY(double y){ return (int)Math.round(iheight/2.0-y*pixPerUnit);}

  public void update(Graphics g){paint(g);}    //to get rid of default clearing of screen in update

  public void setPixPerUnit(int ppu){
    pixPerUnit = ppu;
    for (int i=0;i<v.size();i++){
      OpticElement e = (OpticElement)v.elementAt(i);
      e.setPixPerUnit(pixPerUnit);
    }
    for (int i=0;i<sv.size();i++){
      OpticElement e = (OpticElement)sv.elementAt(i);
      e.setPixPerUnit(pixPerUnit);
    }
  }

  void setRayColor(Color c){rayColor=c;}
  void setElementColor(Color c){elementColor=c;}

  private void adjustHeight(){
    int adjustment=(getBounds().height-iheight)/2;
    for (int i=0;i<v.size();i++){
      OpticElement e = (OpticElement)v.elementAt(i);
      e.yPosition= e.yPosition +adjustment;
    }
    for (int i=0;i<sv.size();i++){
      OpticElement e = (OpticElement)sv.elementAt(i);
      e.yPosition=e.yPosition+adjustment;
    }
  }

  public void paintThings(Graphics g){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         t.paint(g);
    }
  }
  public void paint(Graphics g){
    Rectangle r = getBounds();
      //if osi is null, or if the size changed, create a new osi
    if (osi == null || r.width != iwidth || r.height != iheight){
      adjustHeight();
      iheight = r.height;
      iwidth = r.width;
      osi = createImage(iwidth,iheight);
    }
      //get a graphics context for the osi
    if (osi==null) return;
    Graphics osg = osi.getGraphics();
    if (osg==null) return;
    osg.setColor(Color.black);
    osg.fillRect(0,0,iwidth,iheight);
    if(!hasBench)paintBench(osg);  // need to paint the bench since it has not been added explicitly
    paintThings(osg);
    //draw the coordinates in the bottom left corner.
    if (activeElement!=null){
      drawCoords(osg,activeElement.getPixX(),activeElement.getPixY());
    }
    if (dragThing!=null){
      drawCoords(osg,pixFromX(dragThing.getX()) ,pixFromY(dragThing.getY()));
    }
      //dispose of the graphics context for tidy memory management
    osg.dispose();
      //"blast" the osi to the screen...  Very fast!  No flicker!
    g.drawImage(osi,0,0,this);
  }

  void paintBench(Graphics osg){
    sort(v);
    Rectangle r = getBounds();
    //paint the elements, excluding the source to the osg
    for (int i=0;i<v.size();i++){
      OpticElement e = (OpticElement)v.elementAt(i);
      x[i] =  e.getPixX();
      y[i] =  e.getPixY();
      if (e.getType()=="lens") f[i] = e.getFocalLength();
      if(e.isVisible())e.paint(osg,r);
    }
    //paint the sources to the osg
    for (int i=0;i<sv.size();i++){
      OpticElement e = (OpticElement)sv.elementAt(i);
      sx[i] =  e.getPixX();
      sy[i] =  e.getPixY();
      if(e.isVisible())e.paint(osg,r);
    }
      //if there is an active element, paint it active to the osg
    if (activeElement != null && activeElement.isVisible() ) activeElement.paintActive(osg,r);
      //draw center line in yellow to the osg
    osg.setColor(Color.yellow);
    osg.drawLine(0,r.height/2,r.width,r.height/2);
        //go through the source vector to see what kind of rays need to be drawn to the osg
      for (int si=0;si<sv.size();si++){  // loop throught sources
        OpticElement se = (OpticElement)sv.elementAt(si);
        // new looping mechanism added by W. Christian
        int numIncrements=1;
        if(se.getRayIncrement()!=0)
          numIncrements = (int) Math.abs(2*se.getRaySlope()/se.getRayIncrement());
        numIncrements=Math.max(1,numIncrements);    // make sure we have one ray
        numIncrements=Math.min(100,numIncrements);  // limit the number of rays
        double rayInc=se.getRayIncrement();
        if(se.getRaySlope()>0)rayInc *=-1;
        if (se.getRayIncrement() != 3 && se.getType() == "source"){
          slope = se.getRaySlope(); // get slope of first ray
          for (int n=0 ; n<numIncrements; n++){
            xOld = se.getPixX(); // get x start of ray
            yOld = se.getPixY(); // get y start of ray
            ray[0] = yOld;
            ray[1] = slope;
            osg.setColor(se.getRayColor() );
            drawRay(osg,r,se.getDirection()); // draw and transform the ray to the end of the bench
            slope+=rayInc;
          }
        }

        /*
        if (se.getRayIncrement() != 3 && se.getType() == "source"){
          double raySlope = se.getRaySlope(); // get slope of first ray
          for (slope=raySlope;slope<=-raySlope;slope+=se.getRayIncrement()){
            xOld = se.getPixX(); // get x start of ray
            yOld = se.getPixY(); // get y start of ray
            ray[0] = yOld;
            ray[1] = slope;
            osg.setColor(se.getRayColor() );
            drawRay(osg,r,se.getDirection()); // draw and transform the ray to the end of the bench
          }
        }
        */
        if (se.getRayIncrement() == 3 && se.getType() == "source"){ // special case for parallel ray
          xOld = se.getPixX();
          yOld = se.getPixY();
          ray[0] = yOld;
          ray[1] = 0;
          osg.setColor(se.getRayColor() );
          drawRay(osg,r,se.getDirection());
        }
        if (se.getType() == "isource"){     //infinite-ray source
          int spread = se.getSpread(),
              spacing = se.getSpacing();
          double iAngle = se.getAngle();
          for (spacingInt=0;spacingInt<=spread/2;spacingInt=spacingInt+spacing){
            xOld = se.getPixX();
            yOld = se.getPixY()-spacingInt;
            ray[0] = yOld;
            ray[1] = iAngle;
            osg.setColor(se.getRayColor() );
            drawRay(osg,r,se.getDirection());
            xOld = se.getPixX();
            yOld = se.getPixY()+spacingInt;
            ray[0] = yOld;
            ray[1] = iAngle;

            drawRay(osg,r,se.getDirection());
          }
        }
        if (se.getType() == "psource"){    //principle-ray source
           //se is current source
           //osg is off screen graphics context
           //r is rectangle for current osg
         se.setNextElement(findStart(se.getPixX(),1));  // make sure PRays go to right
         int count=0;
         while(se!=null && count<10) {se=drawPSourceRay((Source)se,osg,r);count++;}

        // while(se!=null) {se=drawPSourceRay(se,osg,r);}
         //hasThisBeenDoneOnce = false;
         indexOfPSources = 0;
         // break;
        }
      }
  }

  public OpticElement drawPSourceRay(OpticElement tpsource, Graphics g,Rectangle r){
    //OpticElement tempPSource=null;
    g.setColor(Color.red);
    int objectX = tpsource.getPixX(),
        objectY = tpsource.getPixY();
    OpticElement e=null,ne=null;    //element, nextElement
    boolean found=false;
    int nextIndex=tpsource.getNextElement();
    int currentDirection=tpsource.getDirection();
    int newDirection=currentDirection;
    while(nextIndex<v.size()-1 && nextIndex>=0 && !found){
      e = (OpticElement)v.elementAt(nextIndex);
      if(e.getType()=="lens" || e.getType()=="mirror" || e.getType()=="dielectric"){
        ne = e;
        found = true;
        if(ne instanceof Mirror)newDirection=-currentDirection;
      }
      nextIndex+=newDirection;
    }

    if(!found)return null;
     else{
      if (objectX != ne.getPixX()-ne.getFocalLength()){
        int elementX = ne.getPixX(),
            elementY = ne.getPixY();
        double fl = ne.getFocalLength();
        int imageX=0, imageY=0;
        if (ne instanceof Mirror){
            if (fl==0){   // flat mirror
                imageY=objectY;
                imageX=2*elementX-objectX;
            }else{
                int tempImageX;
                if((fl-(elementX-objectX))==0)tempImageX=10000;
                else tempImageX =(int)( elementX - fl*(elementX-objectX)/(double)(fl-(elementX-objectX)));
                imageX = 2*elementX - tempImageX;
                imageY = (int)(r.height/2.0 + (tempImageX-elementX)*(r.height/2.0-objectY)/(double)(elementX-objectX));
            }
        }else{  // must be a lens or dielectric
            if((fl-(elementX-objectX))==0)imageX=10000;
            //else imageX = elementX - (int)(currentDirection*(fl*(elementX-objectX))/(double)(fl-(elementX-objectX)));
            //imageY = (int)(r.height/2.0 + currentDirection*((imageX-elementX)*(r.height/2.0-objectY))/(double)(elementX-objectX));
            else imageX = elementX - (int)((fl*(elementX-objectX))/(double)(fl-currentDirection*(elementX-objectX)));
            imageY = (int)(r.height/2.0 + ((imageX-elementX)*(r.height/2.0-objectY))/(double)(elementX-objectX));
        }
        if (tpsource.drawDashedRay && tpsource.showRays){ // from the element to the image.  Shows virtural images
          drawDashedLine(objectX,objectY,elementX,objectY,g);
          drawDashedLine(objectX,objectY,elementX,r.height/2,g);
          drawDashedLine(objectX,objectY,elementX,imageY,g);
          drawDashedLine(elementX,objectY,imageX,imageY,g);
          drawDashedLine(elementX,r.height/2,imageX,imageY,g);
          drawDashedLine(elementX,imageY,imageX,imageY,g);
        }
      // if (!hasThisBeenDoneOnce){// the actual rays that go through the entire optic system.
        if (tpsource.drawSourceRay && tpsource.showRays){
          g.setColor(tpsource.getRayColor() );
          xOld = objectX; // get x start of ray
          yOld = objectY; // get y start of ray
          ray[0] = objectY;
          if((elementX-objectX-fl)==0)ray[1] =0;   // set the slope = 0
            else ray[1] = -(objectY-r.height/2)/(elementX-objectX-fl);
          drawRay(g,r,1);
          xOld = objectX; // get x start of ray
          yOld = objectY;
          ray[0] = objectY;
          ray[1] = 0;
          drawRay(g,r,1);
          xOld = objectX; // get x start of ray
          yOld = objectY;
          ray[0] = objectY;
          if( ((objectX-elementX)!=0)||(!(ne instanceof Mirror)&& (imageX-elementX)!=0) ){
              if (ne instanceof Mirror) ray[1] =(objectY-r.height/2.0)/(objectX-elementX);
              else ray[1] = (imageY-r.height/2.0)/(imageX-elementX);
              drawRay(g,r,1);
          }
       }
       indexOfPSources++;
       //tempPSource = new PSource(imageX,imageY,0,0,true,false,1,false);
       tempPSource.init(imageX,imageY,0.0,0,true,false,1.0,false);
       g.setColor(Color.white);
       if(tpsource.imageLabel){
         if (imageY-r.height/2 < 0) g.drawString(""+indexOfPSources,imageX,imageY-20);
         else g.drawString(""+indexOfPSources,imageX,imageY+20);
       }
       tempPSource.setNextElement(nextIndex);
       tempPSource.paintTemp(g,r);
       tempPSource.setPixPerUnit(pixPerUnit);
       tempPSource.info=tpsource.info;
       tempPSource.drawDashedRay=false;
       tempPSource.drawSourceRay=false;
       tempPSource.setDirection(newDirection);
       if (activeElement instanceof PSource) tempPSource.paintTempActive(g,r);
      }
     }
    found = false;
    return tempPSource;
  }

  public void drawDashedLine(int x1, int y1, int x2, int y2, Graphics g){
    g.setColor(new Color(191,191,255));
    if(x1-x2==0) return;   // check for divide by zero.  W. Christian
    double slope = (y1-y2)*1.0/(x1-x2);
    //double slope = .5;
    double xd = (double)x1, yd = (double)y1;
    int tempIndex=0;
    while (tempIndex<Math.abs((x2-x1))){
      g.fillOval((int)xd,(int)yd,1,1);
        if (xd<x2){          //real image
          xd+=3.0;
          yd+=3.0*slope;
        }
        else {               //virtual image
          xd-=3.0;
          yd-=3.0*slope;
        }
      tempIndex+=3;
    }
  }

  private int findStart(int x, int dir){
      OpticElement e;
      for (int i=0;i<v.size();i++){
           e=(OpticElement)v.elementAt(i);
           if(dir==1 && e.getPixX()>x) return i;
           if(dir==-1 && e.getPixX()>x) return i-1;
      }
      if(dir==1) return v.size()-1;
        else return v.size()-1;
  }

  public void drawRay(Graphics g,Rectangle r,int dt){    //dt = direction
    int rayCount=0;
   // g.setColor(Color.red);
    OpticElement e;    //element, newElement
    step = dt; // direction of ray on bench.  1 is to right, -1 is to left
    int i=findStart(xOld, step);
    while(i>=0 && i<v.size() ){
       if (rayCount>30) {return;}     //30 is the number of rays that should be drawn
       rayCount++;
       e=(OpticElement)v.elementAt(i);
       xNew = e.getPixX();
         //use yNewDouble to keep double precision to use below
       yNewDouble = (ray[0] + ray[1]*(xNew-xOld));
       yNew = (int)yNewDouble;
       if (e instanceof Aperature){
         if  (e.getOpeningSize()==0 || yNew<r.height/2 - e.getOpeningSize()/2 ||
              yNew>r.height/2 + e.getOpeningSize()/2) blocked = true;
       }
       if (e instanceof BeamStop){
         BeamStop bs= (BeamStop)e;
         if  (yNew< bs.getBottomBlock() &&
              yNew> bs.getTopBlock()) blocked = true;
       }
       if (e instanceof Lens || e instanceof Mirror || e instanceof Matrix){
         if  (yNew<r.height/2 - r.height*e.percentSize/2 ||
              yNew>r.height/2 + r.height*e.percentSize/2) blocked = true;
         }
       if (yNew<0 || yNew>r.height || xNew<0 || xNew>r.width) blocked = true;
       if (!blocked) g.drawLine(xOld,yOld,xNew,yNew);
       if (blocked) {g.drawLine(xOld,yOld,xNew,yNew); break;}
       yOld = yNew;
       ray[0] = yNewDouble;
       xOld=xNew;
       Rectangle rec = getBounds();
         //transform ray acording to the next element
       ray = e.transform(ray,rec,step);
       if (e.errCode==2) {step=-step;}
       if (e.errCode==1) {blocked=true; break;}
       if (e instanceof Screen) g.fillOval(xNew-2,yNew-2,4,4);
       if (e instanceof Mirror) step=-step;
       i+=step;
    }
    blocked = false;
  }

  public synchronized void sort(Vector v){
    //Vector tempVec;
    //synchronized(v){tempVec=(Vector)v.clone();}
    OpticElement e, ne;
    boolean orderChanged=false;
    for (inc=0;inc<=maxNumber;inc++){
      for (int i=0;i<v.size()-1;i++){
        e = (OpticElement)v.elementAt(i);
        ne = (OpticElement)v.elementAt(i+1);
        if (e.getPixX() > ne.getPixX()){
          orderChanged=true;
          v.removeElementAt(i);
            //System.out.println("removing "+e.getType()+" element at "+i);
          v.insertElementAt(e,i+1);
          //  System.out.println("inserting "+e.getType()+" element at "+(i+1));
        }
      }
    }
    //synchronized(v){if( v.size()==tempVec.size() ) v=tempVec;}
    if(orderChanged) recalculateIndexOfRefraction();

  }

  synchronized void recalculateIndexOfRefraction(){
    double n=1.0;
    for (int i=0;i<v.size()-1;i++){
      OpticElement e = (OpticElement)v.elementAt(i);
      e.indexOfRefraction=n;
      if(e instanceof Dielectric){
          n+= ((Dielectric)e).delN;
          if(n<1)n=1;
          e.focalLength=((Dielectric)e).R/((Dielectric)e).delN;;
          //System.out.println("n " +n);
      }
    }
  }

  public void propagate(double[] v,int d){
    v[0] = v[0]*1 + v[1]*d;  //v[0] = v[0] + slope*d
    v[1] = v[0]*0 + v[1]*1;  //v[1] = v[1]
  }

  public OpticElement getElement(int id){
       OpticElement t=null;
       for( Enumeration e=v.elements(); e.hasMoreElements();){
         t= (OpticElement) e.nextElement();
         if(t.hashCode()==id){
           return t;
         }
       }
       for( Enumeration e=sv.elements(); e.hasMoreElements();){
         t= (OpticElement) e.nextElement();
         if(t.hashCode()==id){
           return t;
         }
       }
       return null;
    }

  public void addThing(Thing t){
     things.addElement(t);
  }

  /**
   * Delete an object.
   *
   * @param id the object identifier
   *
   * @return true if successful
   */

  public boolean deleteObject(int id){
    Vector temp_v;
    Thing t=getThing(id);
    if(t==null) return false;
    if(t==activeElement){
      clearActiveElement();
      return true;
    }

    owner.removeDataListener(t.hashCode() );
    owner.removeDataSource(t.hashCode() );
    owner.cleanupDataConnections();
    v.removeElement(t);
    sv.removeElement(t);
    things.removeElement(t);
    if(t instanceof BenchThing)hasBench=false;
    sort(v);
    recalculateIndexOfRefraction();
    if(owner.isAutoRefresh())repaint();
    return true;
  }

  public Thing getThing(int id){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t.hashCode()==id) return t;
    }
    for( Enumeration e=v.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t.hashCode()==id) return t;
    }
    for( Enumeration e=sv.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t.hashCode()==id) return t;
    }
    return null;
  }

  public int addLens(int xTransfer, int yTransfer, double fl,boolean info, boolean drag,double size,boolean pd){
    if (v.size()>=maxNumber) return 0;
    Lens l = new Lens(this, xTransfer,yTransfer,fl,info,drag,size,pd);
    l.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)l.setElementColor(elementColor);
    v.addElement(l);
    return l.hashCode();
  }

  public int addMatrix(int x, int y, String hStr, String aStr){
    if (v.size()>=maxNumber) return 0;
    Matrix mat = new Matrix(this, x,y,hStr,aStr);
    mat.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)mat.setElementColor(elementColor);
    v.addElement(mat);
    return mat.hashCode();
  }

  public int addIndexChange(int xTransfer, int yTransfer, double delN,boolean info, boolean drag,double size,boolean pd){
    if (v.size()>=maxNumber) return 0;
    IndexChange ic = new IndexChange(this, xTransfer,yTransfer,delN,info,drag,size,pd);
    ic.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)ic.setElementColor(elementColor);
    v.addElement(ic);
    sort(v);
    recalculateIndexOfRefraction();
    return ic.hashCode();
  }

  public int addMirror(int xTransfer, int yTransfer, double fl,boolean info, boolean drag,double size,boolean pd){
    if (v.size()>=maxNumber) return 0;
    Mirror m = new Mirror(this, xTransfer,yTransfer,fl,info,drag,size,pd);
    m.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)m.setElementColor(elementColor);
    v.addElement(m);
    return m.hashCode();
  }

  public int addSphericalMirror(int xTransfer, int yTransfer, double fl,boolean info, boolean drag,double size,boolean pd){
    if (v.size()>=maxNumber) return 0;
    Mirror2 m = new Mirror2(this, xTransfer,yTransfer,fl,info,drag,size,pd);
    m.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)m.setElementColor(elementColor);
    v.addElement(m);
    return m.hashCode();
  }

  public int addDielectric(int xTransfer, int yTransfer, double dn, int rT,boolean info, boolean drag,double size,boolean pd){
    if (v.size()>=maxNumber) return 0;
    Dielectric d = new Dielectric(this, xTransfer,yTransfer,dn,rT,info,drag,size,pd);
    d.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)d.setElementColor(elementColor);
    v.addElement(d);
    sort(v);
    recalculateIndexOfRefraction();
    return d.hashCode();
  }

  public int addSource(int xTransfer, int yTransfer,double rayInc, double raySlope,boolean info, boolean drag,int direction,boolean pd){
    if (sv.size()>=maxSNumber) return 0;
    Source so = new Source(this, xTransfer,yTransfer,rayInc,raySlope,info,drag,direction,pd);
    so.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)so.setElementColor(elementColor);
    if(rayColor!=null)so.setRayColor(rayColor);
    sv.addElement(so);
    return so.hashCode();
  }

  public int addAperature(int xTransfer, int yTransfer, int openingSizeTransfer,boolean info, boolean drag,double size,boolean pd){
    if (sv.size()>=maxSNumber) return 0;
    Rectangle r = getBounds();
    Aperature a = new Aperature(this, xTransfer,yTransfer,openingSizeTransfer,info,drag,size,pd);
    a.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)a.setElementColor(elementColor);
    v.addElement(a);
    return a.hashCode();
  }

  public int addBeamStop(int xTransfer, int yTransfer, int top, int bottom ,boolean info, boolean drag,double size,boolean pd){
    if (sv.size()>=maxSNumber) return 0;
    Rectangle r = getBounds();
    BeamStop a = new BeamStop(this, xTransfer,yTransfer,top,bottom,info,drag,size,pd);
    a.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)a.setElementColor(elementColor);
    v.addElement(a);
    return a.hashCode();
  }

  public int addPSource(int xTransfer, int yTransfer,boolean info, boolean drag,double size,boolean pd){
    if (sv.size()>=maxSNumber) return 0;
    PSource pso = new PSource(this, xTransfer,yTransfer,0,0,info,drag,size,pd);
    pso.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)pso.setElementColor(elementColor);
    if(rayColor!=null)pso.setRayColor(rayColor);
    sv.addElement(pso);
    return pso.hashCode();
  }

  public int addRefraction(int xTransfer, int yTransfer, double delN,int rT,boolean info, boolean drag,double size,boolean pd){
    Refraction r = new Refraction(this, xTransfer,yTransfer,delN,rT,info,drag,size,pd);
    r.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)r.setElementColor(elementColor);
    v.addElement(r);
    sort(v);
    recalculateIndexOfRefraction();
    return r.hashCode();
  }

  public int addISource(int xTransfer, int yTransfer,int size, double angle, boolean info, boolean drag,int direction,boolean pd){
    if (sv.size()>=maxSNumber) return 0;
    ISource iso = new ISource(this, xTransfer,yTransfer,size,angle,info,drag,direction,pd);
    iso.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)iso.setElementColor(elementColor);
    if(rayColor!=null)iso.setRayColor(rayColor);
    sv.addElement(iso);
    return iso.hashCode();
  }

  public int addScreen(int xTransfer, int yTransfer,boolean info, boolean drag,double size,boolean pd){
    if (v.size()>=maxNumber) return 0;
    Screen sc = new Screen(this, xTransfer, yTransfer,info,drag,size,pd);
    sc.setPixPerUnit(pixPerUnit);
    if(elementColor!=null)sc.setElementColor(elementColor);
    v.addElement(sc);
    return sc.hashCode();
  }

  public void clearThings(){
    activeElement = null;
    Vector temp_v;
    Thing t;

   // v.removeAllElements();

    synchronized(v){temp_v=(Vector)v.clone(); v.removeAllElements();}
    for( Enumeration e=temp_v.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         owner.removeDataListener(t.hashCode() );
         owner.removeDataSource(t.hashCode() );
    }

    //sv.removeAllElements();

    synchronized(sv){temp_v=(Vector)sv.clone(); sv.removeAllElements();}
    for( Enumeration e=temp_v.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         owner.removeDataListener(t.hashCode() );
         owner.removeDataSource(t.hashCode() );
    }

   // things.removeAllElements();

    synchronized(things){temp_v=(Vector)things.clone(); things.removeAllElements();}
    for( Enumeration e=temp_v.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         owner.removeDataListener(t.hashCode() );
         owner.removeDataSource(t.hashCode() );
    }
    hasBench=false;
    int id=addScreen(2000,0,false,false,1,false);
    OpticElement el=getElement(id);
    el.setPixX(2000, new Rectangle(0,0) );
    id=addScreen(-100,0,false,false,1,false);
    el=getElement(id);
    el.setPixX(-100, new Rectangle(0,0) );

    sort(v);
    recalculateIndexOfRefraction();
    if(owner.isAutoRefresh())repaint();
  }

  public void clearActiveElement(){
    if (activeElement!=null){
      if (v.indexOf(activeElement) != -1) v.removeElementAt(v.indexOf(activeElement));
      if (sv.indexOf(activeElement) != -1) sv.removeElementAt(sv.indexOf(activeElement));
    }
    activeElement = null;
    sort(v);
    recalculateIndexOfRefraction();
    if(owner.isAutoRefresh())repaint();
  }

  public void mousePressed(int mouseX, int mouseY){
    xDown=mouseX; yDown=mouseY;  // save the old values
    xLast=mouseX; yLast=mouseY;  // save the old values
    Rectangle r = getBounds();
    dragThing=null;
    activeElement=null;
    for (int i=0;i<v.size();i++){
      OpticElement e = (OpticElement)v.elementAt(i);
      if ((!e.isNoDrag() ||e.isResizable())&&(e.isInside(mouseX,mouseY,r)==1 || e.isInside(mouseX,mouseY,r)==2 || e.isInside(mouseX,mouseY,r)==3)){
        activeElement = e;
        //System.out.println("hi I, i'm element number "+i+", a "+e.getType());
        //System.out.println("Active Element ="+e);
        break;
      }
      else activeElement = null;
    }
    if (activeElement == null){
      for (int i=0;i<sv.size();i++){
        OpticElement e = (OpticElement)sv.elementAt(i);
        if ((!e.isNoDrag() ||e.isResizable() )&&(e.isInside(mouseX,mouseY,r)==1 || e.isInside(mouseX,mouseY,r)==2)){
          activeElement = e;
        // System.out.println("hi II, i'm element number "+i+", a "+e.getType());
          break;
        }
        else activeElement = null;
      }
    }
    if (activeElement == null){
      for (int i=0;i<things.size();i++){
        Thing e = (Thing)things.elementAt(i);
        if ((!e.isNoDrag() ||e.isResizable() )&& e.isInsideThing(mouseX,mouseY)){
          dragThing = e;
         // System.out.println("hi III, i'm element number "+i);
          break;
        }
        else dragThing = null;
      }
    }
    isMouseInside( mouseX,  mouseY);
  }

  public void mouseReleased(int mouseX, int mouseY){
   isControlDown = false;
   dragThing=null;
   repaint();
  }

 public void mouseDragged(int mouseX, int mouseY){
  Rectangle r = getBounds();
  //System.out.println("Moving Active Element ="+activeElement);
  if (activeElement!=null){
   if (activeElement.getDrag() || activeElement.isResizable()){
     if (isInsideOpticElement == 1 && !isControlDown &&
       activeElement.getType()!="source" || activeElement.getType()=="psource"){
       if(activeElement.getDrag())activeElement.setPixX(mouseX,r);
       if(activeElement.isResizable())activeElement.setPixY(mouseY,r);
       repaint();
     }
     if (isInsideOpticElement == 1 && !isControlDown &&
          (activeElement.getType()=="source" || activeElement.getType()=="isource")){
        if(activeElement.getDrag()){
          activeElement.setPixX(mouseX,r);
          activeElement.setPixY(mouseY,r);
          repaint();
        }
     }
     if (activeElement.getType()=="aperature" &&
          isInsideOpticElement == 2){
       if(activeElement.isResizable())activeElement.setOpeningSize(2*(r.height/2 - mouseY));
       if(activeElement.getDrag())activeElement.setPixX(mouseX,r);        //maybe or maybe not move xPosition
       if(activeElement.isResizable())activeElement.setPixY(mouseY,r);
       repaint();
     }
     if (activeElement.getType()=="aperature" &&
          isInsideOpticElement == 3){
       if(activeElement.isResizable())activeElement.setOpeningSize(2*(mouseY - r.height/2));
       if(activeElement.getDrag())activeElement.setPixX(mouseX,r);        //maybe or maybe not move xPosition
       if(activeElement.isResizable())activeElement.setPixY(mouseY,r);
       repaint();
     }
   }
   if (activeElement.isResizable()){
     if ((isInsideOpticElement == 2 &&
          (activeElement.getType()=="lens" || activeElement.getType()=="mirror"))){      //right side of lens
       activeElement.setFocalLength(mouseX-activeElement.getPixX(),r);
       //System.out.println("FL set:"+activeElement.getID() );
       repaint();
     }
     if ((isInsideOpticElement == 3 &&
          (activeElement.getType()=="lens" || activeElement.getType()=="mirror"))){      //left side of lens
       activeElement.setFocalLength(-(mouseX-activeElement.getPixX()),r);
       repaint();
     }
     if ((isInsideOpticElement == 2 &&
           activeElement.getType()=="dielectric")){      //left side of dielectric
       activeElement.setRadius(mouseX-activeElement.getPixX(),r);
       //System.out.println("left side of dielectric, 2 , "+activeElement.getR());
       ((Dielectric)activeElement).setDelN(-(mouseY-r.height/2.0)/50);
       //if (mouseY-r.height/2<0) ((Dielectric)activeElement).setDelN(-(mouseY-r.height/2.0)/50);
       //if (mouseY-r.height/2>0) ((Dielectric)activeElement).setDelN((mouseY-r.height/2.0)/50);
       recalculateIndexOfRefraction();
       repaint();
     }
     if ((isInsideOpticElement == 3 && activeElement.getType()=="dielectric")){      //right side of dielectric
       activeElement.setRadius(-(mouseX-activeElement.getPixX()),r);
      // System.out.println("right side of dielectric, 3 , "+activeElement.getR());
       ((Dielectric)activeElement).setDelN(-(mouseY-r.height/2.0)/50);
       //if (mouseY-r.height/2<0) ((Dielectric)activeElement).setDelN(-(mouseY-r.height/2.0)/50);
       //if (mouseY-r.height/2>0) ((Dielectric)activeElement).setDelN((mouseY-r.height/2.0)/50);
       recalculateIndexOfRefraction();
       repaint();
     }
     if (isInsideOpticElement == 1 && isControlDown &&
           activeElement.getType()=="source"){
       activeElement.setRayIncrement(1-(mouseX-activeElement.getPixX())*.005);
       if (mouseX<=activeElement.getPixX()+15) activeElement.setRayIncrement(1);
       if (mouseX-activeElement.getPixX()<0) activeElement.setRayIncrement(3);
       //activeElement.setRaySlope((activeElement.getPixX()-mouseX)/(activeElement.getPixY()-mouseY));
       repaint();
     }
     if (isInsideOpticElement == 2 &&
           activeElement.getType()=="source"){
       activeElement.setRaySlope(1.0*(mouseY-activeElement.getPixY())/(mouseX-activeElement.getPixX()));
       if (activeElement.getPixX()>mouseX) activeElement.setDirection(-1);
         else activeElement.setDirection(1);
       repaint();
     }
     if (isInsideOpticElement == 1 && isControlDown &&
           activeElement.getType()=="isource"){
       activeElement.setSpread(100+(activeElement.getPixY()-mouseY));
       activeElement.setSpacing(10+(activeElement.getPixX()-mouseX));
       repaint();
     }
     if (isInsideOpticElement == 2 &&
           activeElement.getType()=="isource"){
       activeElement.setAngle((1.0*activeElement.getPixY()-mouseY)/(1*(activeElement.getPixX()-mouseX)));
       if (activeElement.getPixX()>mouseX) activeElement.setDirection(-1);
         else activeElement.setDirection(1);
       repaint();
     }
   }
  }else if(dragThing!=null){
      activeElement=null;
      dragThing.dragMe(xFromPix(mouseX),yFromPix(mouseY));
      //dragThing.updateMySlaves();
      Thing slave=null;
      for( Enumeration e=dragThing.getSlaves().elements(); e.hasMoreElements();){
           slave= (Thing) e.nextElement();
           slave.setVarsFromMaster() ;
           if(slave instanceof OpticElement){
              ((OpticElement)slave).adjustPosition();
           }
      }
      repaint();
  }else{
    drawAngle(mouseX,mouseY);
  }
  owner.updateDataConnections();
  //if(owner!=null &&activeElement!=null )owner.updateDataConnection(activeElement.getID() );
 }
 
 void isMouseInside(int mouseX, int mouseY) {
	    Rectangle r = getBounds();
	    setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.CROSSHAIR_CURSOR));
	    isInsideOpticElement = 0;
	    for (int i=0;i<v.size();i++){  // check optic elements
	      OpticElement e = (OpticElement)v.elementAt(i);
	      if (e.isInside(mouseX,mouseY,r)==1 || e.isInside(mouseX,mouseY,r)==2 || e.isInside(mouseX,mouseY,r)==3){
	        setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
	        isInsideOpticElement = e.isInside(mouseX,mouseY,r);
	        break;
	      }
	    }
	    for (int i=0;i<sv.size();i++){  // check sources elements
	        OpticElement e = (OpticElement)sv.elementAt(i);
	        if (e.isInside(mouseX,mouseY,r)==1 || e.isInside(mouseX,mouseY,r)==2){
	          setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
	          isInsideOpticElement = e.isInside(mouseX,mouseY,r);
	          break;
	        }
	    }
	    for (int i=0;i<things.size();i++){ // check things
	          Thing t = (Thing)things.elementAt(i);
	          if ((!t.isNoDrag() || t.isResizable() )&& t.isInsideThing(mouseX,mouseY)){
	            setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
	            break;
	          }
	    } 
 }

  public void mouseMoved(int mouseX, int mouseY){
	  isMouseInside( mouseX, mouseY);
      //******draw the coordinates in the bottom left corner.
    Graphics g = getGraphics();
    drawCoords(g,mouseX,mouseY);
    g.dispose();
  }

  public void drawCoords(Graphics g,int mouseX,int mouseY){
    Rectangle r = getBounds();
    g.setColor(Color.yellow);
    g.fillRect(0,r.height-20,100,20);
    g.setColor(Color.black);
    g.drawString(""+df.format(1.0*mouseX/pixPerUnit)+
      " , "+df.format(1.0*r.height/2/pixPerUnit-1.0*mouseY/pixPerUnit),10,r.height-5);
   // g.dispose();
  }
  
  public void drawAngle(int mouseX,int mouseY){
    Graphics g = getGraphics();
    if(SoundOut.isJS){
    	osi=null;
        paint(g);
    }
    double dx=mouseX-xDown;
    double dy=-mouseY+yDown;
    Rectangle r = getBounds();
    g.setColor(Color.yellow);
    g.drawLine(xDown,yDown,xDown+30,yDown);
    g.fillRect(0,r.height-20,100,20);
    g.setColor(Color.black);
    g.drawString(owner.label_angle + (int)(180*Math.atan2(dy,dx)/Math.PI), 10,r.height-5);

    // the angle
    g.setColor(Color.white);
    g.setXORMode(Color.red);
    g.drawLine(xDown,yDown,xLast,yLast);
    g.drawLine(xDown,yDown,mouseX,mouseY);
    xLast=mouseX;
    yLast=mouseY;
    g.setPaintMode();
    g.dispose();
     
  }

}
