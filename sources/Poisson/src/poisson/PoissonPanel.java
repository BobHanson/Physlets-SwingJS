package poisson;
import a2s.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.Timer;
import edu.davidson.display.Format;
import edu.davidson.display.SContour;
import edu.davidson.display.SNumber;
import edu.davidson.display.SScalable;
import edu.davidson.display.Thing;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SDataSource;

public final class PoissonPanel extends Panel implements Runnable, SScalable, SDataSource{
  private boolean  hasFieldThing=false;   // flag to indicate that the vector field should be treated like a drawing thing.
  private boolean  hasContourThing=false;   // flag to indicate that the vector field should be treated like a drawing thing.
  private boolean keepRunning=true;
  //private boolean interrupted=false;
  private int vectorResolution=2;
  private String newObjectString=null;
  private int gutter=0;  // these will be set by the applet later.
  private int gutter2=0;
  private int xPts=8;
  private int yPts=8;
  private int xPtsg=8;
  private int yPtsg=8;
  private int xPts2g=8;
  private int yPts2g=8;
  private boolean invalidPotential=false;
  private Font f=new Font("Helvetica",Font.BOLD,12);
  private String message=null;
  private Thread     runThread = null;
  private int sleepTime=30;
  VectorField field=new VectorField(yPts,xPts);   // [r,c]
  private boolean[][] isConductor = new boolean[xPts+2*gutter][yPts+2*gutter];          //shows where voltage is known at start.
  private double[][] potential = new double[xPts+2*gutter][yPts+2*gutter];            //holds matrix to calc voltage when calc button is pressed.
  private double[][] charge = new double[xPts+2*gutter][yPts+2*gutter];  ;            //holds matrix to calc charge when calc button is pressed.
  private double[][] dielectric = new double[xPts+2*gutter][yPts+2*gutter];            //holds matrix to calc charge when calc button is pressed.
  private double[][] displaymatrix=new double[xPts][yPts];
  private double defaultVoltage=10;
  private Format    format= new Format("%-+8.4g");
  private int     boxWidth=0;
  private boolean   isDrag=false;
  private SNumber vDisplay=null;
  //private double contourInterval=1;
  private String    caption=null;
  private boolean   showCharge=false;
  private int defaultChargeType=PotentialObject.CONDUCTOR_TYPE;
  private boolean showRhoOnDrag=false;
  private int mouseX=0;
  private int mouseY=0;
  private int maxInterations=200;
    // data source variables
  protected String[] varStrings= new String[]{"p","q"};
  protected double[][] ds=new double[1][2];  // the datasource state variables x,y;

  Poisson owner=null;
  PContour  contour=new PContour();
  ChargeDataSource chargeDataSource=null;
  PotentialDataSource potentialDataSource=null;
  double tolerance=2.0e-3;
  // display options
  boolean showContours=true;
  boolean showGrid=true;
  boolean showFieldVectors=true;
  // mouse options
  boolean showCoordOnDrag=true;
  boolean showVOnDrag=true;
  boolean showEOnDrag=true;

  double xPixPerCell=0, yPixPerCell=0;
  double xUnitPerCell=0, yUnitPerCell=0;
  double xmin=-1;
  double xmax=1;
  double ymin=-1;
  double ymax=1;
  boolean autoRefresh=true;
  Color  positiveChargeColor=Color.red;
  Color  negativeChargeColor=Color.blue;
  double inducedChargeScale=10;


  int maxNumber = 1024;
 // int i,j,k;
  int iwidth,iheight,dx,dy,hotSpot;
  Vector things = new Vector();                             //PotentialObject Vector
 // boolean locked;                                         //are the isConductor values locked?
 // boolean draw = true;
  PotentialObject activeElement=null;
  Thing dragThing=null;
  Image osi;

  public PoissonPanel(Poisson o, int xp, int yp) {
      owner=o;
      resizeMatrices(xp, yp,gutter);
      addMouseMotionListener(new PoissonPanel_mouseMotionAdapter(this));
      addMouseListener(new PoissonPanel_mouseAdapter(this));
      contour.setShowAxis(false);
      contour.setDataBackground( Color.white);
      contour.setLabelColor(Color.green);
      contour.setDrawLabels(false);
      //chargeDataSource=new ChargeDataSource(owner);  create only when needed
      contour.setOwner(owner);
      try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
  }
  public PoissonPanel(Poisson o){this(o,50,50);}


  //Data source methods.
  public void setOwner(SApplet o){owner=(Poisson)o;}
  public SApplet getOwner(){return owner;}
  public String[] getVarStrings(){return varStrings;}
  public int getID(){return this.hashCode();}
  public double[][] getVariables(){
     ds[0][0]=calcPotentialEnergy();  //p
     ds[0][1]=0;  //q
     return ds;
  }
  //end of source methods.

 public CircleObject addPotCircle(int r, double x, double y, double ve){
    if (things.size()>=maxNumber) return null;
    CircleObject circle = new CircleObject(this,x,y,ve);
    r=Math.max(r,(int)(this.xPixPerCell*3));
    r=Math.max(r,(int)(this.yPixPerCell*3));
    circle.setWidth(r);
    circle.setHeight(r);
    things.addElement(circle);
    if(autoRefresh) recalculate();
    return circle;
  }

  public RingObject addPotRing(int r, int s, double x, double y, double ve){
    if (things.size()>=maxNumber) return null;
    RingObject ring = new RingObject(this,x,y,ve);
    r=Math.max(r,(int)(this.xPixPerCell*3));
    r=Math.max(r,(int)(this.yPixPerCell*3));
    s=Math.max(s,(int)(this.xPixPerCell*3));
    s=Math.max(s,(int)(this.yPixPerCell*3));
    ring.setWidth(r);
    ring.setHeight(r);
    ring.setSize(s);
    things.addElement(ring);
    if(autoRefresh) recalculate();
    return ring;
  }

 public RectObject addPotRect(int w, int h, double x, double y, double ve){
    if (things.size()>=maxNumber) return null;
    RectObject rect = new RectObject(this,x,y,ve);
    w=Math.max(w,(int)(this.xPixPerCell*3));
    h=Math.max(h,(int)(this.yPixPerCell*3));
    rect.setWidth(w);
    rect.setHeight(h);
    things.addElement(rect);
    if(autoRefresh)recalculate();
    return rect;
  }

 public int addField(){
     if( hasFieldThing) return 0;
     FieldThing ft=new FieldThing(this);
     hasFieldThing=true;
     things.addElement(ft);
     return ft.hashCode();
  }

 public int addContours(){
     if( hasContourThing) return 0;
     ContourThing ct=new ContourThing(this);
     hasContourThing=true;
     things.addElement(ct);
     return ct.hashCode();
  }

 public BoxObject addPotBox(int w, int h, int s, double x, double y, double ve){
    if (things.size()>=maxNumber) return null;
    BoxObject box = new BoxObject(this,x,y,ve);
    w=Math.max(w,(int)(this.xPixPerCell*3));
    h=Math.max(h,(int)(this.yPixPerCell*3));
    s=Math.max(s,(int)(this.xPixPerCell*3));
    s=Math.max(s,(int)(this.yPixPerCell*3));
    box.setWidth(w);
    box.setHeight(h);
    box.setSize(s);
    things.addElement(box);
    if(autoRefresh)recalculate();
    return box;
  }

   public int addThing(Thing t ){
    if (things.size()>=maxNumber) return 0;
    things.addElement(t);
    if(autoRefresh)repaint();
    return t.hashCode();
  }


  public void update(Graphics g){paint(g);}          //cuts down on flicker

  final int xPixToIndex(int xPix){
      int index= (int)Math.round(xPix/xPixPerCell)+gutter;
      if(index<0) return 0;
      else if(index>xPts-1+gutter2) return xPts-1+gutter2;
      else return index;
  }

  final int xIndexToPix(int i){
      i=Math.max(i-gutter,0);
      int xPix=(int)Math.round((i+0.5)*xPixPerCell);
      return xPix;
  }

  final void setVDisplay(SNumber sn){vDisplay=sn;};

  final int yPixToIndex(int yPix){
      int index= (int)Math.round((yPix)/yPixPerCell)+gutter;
      if(index<0) return 0;
      else if(index>yPts-1+gutter2) return yPts-1+gutter2;
      else return index;
  }

  final int yIndexToPix(int j){
      j=Math.max(j-gutter,0);
      int yPix=(int)Math.round((j+0.5)*yPixPerCell);
      return yPix;
  }

  final void copyToDisplayMatrix(){
      int ii;
      for (int i=gutter;i<xPtsg;i++){
        ii=i-gutter;
        for (int j=gutter;j<yPtsg;j++){
            displaymatrix[ii][j-gutter]=-potential[i][j];
        }
      }
  }

  public void paint(Graphics g){
    Rectangle r = getBounds();
    if (r.width<16  || r.height<16) return;   // too small
    //if osi changes size, get a newOne
    if (osi == null || r.width != iwidth || r.height != iheight){
      iheight = r.height;
      iwidth = r.width;
      setXRange(xmin,xmax);
      osi = createImage(iwidth,iheight);
      xPixPerCell=(double)iwidth/(double)xPts;
      yPixPerCell=(double)iheight/(double)yPts;
      xUnitPerCell=(double)(xmax-xmin)/(double)xPts;
      yUnitPerCell=(double)(ymax-ymin)/(double)yPts;
      // set the pixel scale
      Graphics osg = osi.getGraphics();
      contour.setNoContours(true);
      contour.paint(osg,getBounds());
      contour.setNoContours(!showContours);
      osg.dispose();
      // adjust the position to the new pixels
      Thing t;
      PotentialObject p;
      for (int i=0; i<things.size();i++){
          t = (Thing)things.elementAt(i);
          if(t instanceof PotentialObject){
            p = (PotentialObject)t;
            //p.hasChanged=true;
          }
      }
      setConductors();   // set the conductors according to the new position
      invalidPotential=true;
      startThread();  // do the poisson calculation;
    }
    if(invalidPotential){
        copyToDisplayMatrix();
        contour.setGrid(displaymatrix,xmin,xmax,ymin,ymax);
        setFieldFromGrad();
        invalidPotential=false;
    }
    //get a graphics context for the osi
    Graphics osg = osi.getGraphics();
    Rectangle cr = getBounds();
        /* The r.x and r.y returned from bounds is relative to the
        ** parents space so set them equal to zero.
        */
    cr.x = 0;
    cr.y = 0;

    contour.paint(osg,cr);  // also  paints the field and the conductors

    if (activeElement!=null) activeElement.paintActive(osg);
    if (showGrid) paintGrid(osg,r);       //drawing the grid
    // draw the caption
    if(caption!=null){
        Font oldFont=osg.getFont();
        osg.setFont(f); // this font is used for messages and captions.
        FontMetrics fm=osg.getFontMetrics(f);
        osg.drawString(caption,(iwidth-fm.stringWidth(caption))/2, 25);
        osg.setFont(oldFont);
    }
    // draw the boundary
    if(gutter==0){
      osg.setColor(Color.black);
      osg.fillRect(0,0,(int)xPixPerCell,iheight);
      osg.fillRect(0,0,iwidth,(int)yPixPerCell);
      osg.fillRect(iwidth-(int)xPixPerCell,0,(int)xPixPerCell,iheight);
      osg.fillRect(0,iheight-(int)yPixPerCell,iwidth,(int)yPixPerCell);
    }

    if (showCharge) paintCharge(osg);

      //dispose of the graphics context for tidy memory management
    osg.dispose();
      //"blast" the osi to the screen...  Very fast!  No flicker!
    g.drawImage(osi,0,0,this);
    if(runThread!=null)paintMessage(g,owner.label_calculating);
    else paintMessage(g,message);
  }

  void paintCoordinates(int x,int y){
      Graphics g=getGraphics();
      paintCoordinates(g,x,y);
      g.dispose();
  }

  void paintCoordinates(Graphics g, int xPix, int yPix){
      double x=xFromPix(xPix);
      double y=yFromPix(yPix);
      FontMetrics fm=g.getFontMetrics(g.getFont());
      String msg="";
      if(showCoordOnDrag) msg="x=" + format.form(x)+ " y=" +format.form(y);
      boolean isInsidePO=isPixInsidePotentialObject(xPix,yPix);
      if(showVOnDrag){
          double u;
          if(isInsidePO) u=defaultVoltage;
          else u =-potential[xPixToIndex(xPix)][yPts2g-yPixToIndex(yPix)-1];
          msg=msg+" "+owner.label_v+format.form(u);
      }
      if(showEOnDrag){
          double fx=-dudx(xPixToIndex(xPix),yPts2g-yPixToIndex(yPix)-1);
          double fy=-dudy(xPixToIndex(xPix),yPts2g-yPixToIndex(yPix)-1);
          if(isInsidePO)msg=msg+" "+owner.label_e+"0";
          else msg=msg+" "+owner.label_e+format.form(Math.sqrt(fx*fx+fy*fy));
      }
      if(showRhoOnDrag){
          msg=msg+" "+owner.label_rho+format.form(charge[xPixToIndex(xPix)][yPts2g-yPixToIndex(yPix)-1]);
      }
      g.setColor(Color.yellow);
      g.fillRect(0,getBounds().height-15,boxWidth,15);
      boxWidth=20+fm.stringWidth(msg);
      g.fillRect(0,getBounds().height-15,boxWidth,15);
      g.setColor(Color.black);
      g.drawString(msg, 8, getBounds().height-2);
  }

  void poissonPanel_mousePressed(MouseEvent e) {
    //if (e.isControlDown()) poissonPanel.isControlDown = true;
    //   else poissonPanel.isControlDown = false;
    mouseX=e.getX();
    mouseY=e.getY();
    if (newObjectString=="circle"){
        CircleObject c= this.addPotCircle(40,xFromPix(mouseX),yFromPix(mouseY),defaultVoltage);
        c.setChargeType(defaultChargeType);
       //addCircleFromPix(e.getX(),e.getY(),defaultVoltage);
       newObjectString = null;
    }else if (newObjectString=="rect"){
       RectObject r=this.addPotRect(40,40,xFromPix(mouseX),yFromPix(mouseY),defaultVoltage);
       r.setChargeType(defaultChargeType);
       //addRectFromPix(e.getX(),e.getY(),defaultVoltage);
       newObjectString = null;
    }
    Graphics g=getGraphics();
    paintCoordinates(g,mouseX,mouseY);
    if (newObjectString==null){
        activeElement=null;
        dragThing=null;
        Thing t;
        PotentialObject p;
        for (int i=things.size()-1;i>=0;i--){
            t = (Thing)things.elementAt(i);
            if(t instanceof PotentialObject){ // first test to see if we are in a potential thing.
              p=(PotentialObject)t;
              if (p.isInsideHotSpot( mouseX,mouseY)!=0){
                activeElement=p;
                hotSpot = activeElement.isInsideHotSpot(mouseX,mouseY);
                poissonPanel_mouseMoved(e);
                stopThread();
                isDrag=true;
                activeElement.paintActive(g);
                if( vDisplay!=null) vDisplay.setValue(activeElement.getPotential());
                break;
              }
            }else if(t.getMaster()==null && t.isInsideThing(mouseX,mouseY) && !t.isNoDrag() ){ // we are in a geometric thing
                dragThing=t;
                break;
            }
        }
    }
    if(dragThing!=null){
            mouseX=pixFromX(dragThing.getX());
            mouseY=pixFromY(dragThing.getY());
            double x=xFromPix(mouseX);
            double y=yFromPix(mouseY);
            dragThing.setXY(x,y);
            dragThing.updateMySlaves();
            owner.updateDataConnection(dragThing.hashCode());
            g.setXORMode(getBackground());
            //dragThing.paint(g);
            g.setPaintMode();
    }
    g.dispose();
  }

  void poissonPanel_mouseMoved(MouseEvent e){
    int x=e.getX();
    int y=e.getY();
    if( activeElement!=null){
      switch (activeElement.isInsideHotSpot(x,y)){
          case 1:
              setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.MOVE_CURSOR));
              break;
          case 2:
              setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.E_RESIZE_CURSOR));
              break;
          case 3:
              setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.S_RESIZE_CURSOR));
              break;
          case 4:
              setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.SE_RESIZE_CURSOR));
              break;
          default:
             setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
      }
      return;
    }
    for (int i=0;i<things.size();i++){
      Thing t = (Thing)things.elementAt(i);
      if (t.isInsideThing(x,y) && (!t.isNoDrag() || t.isResizable()) ){
        setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        return;
      }
      else
        setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
  }

  void poissonPanel_mouseReleased(MouseEvent e){
    Graphics g=getGraphics();
    if(activeElement!=null){ // && activeElement.hasChanged){
        contour.setNoContours(true);  // turn off the contours
        boolean temp=showFieldVectors;
        showFieldVectors=false;   // turn off the field vectors
        paint(g);
        contour.setNoContours(!showContours);
        showFieldVectors=temp;
    } else  g.drawImage(osi,0,0,this);
    if(runThread!=null)paintMessage(g,owner.label_calculating);
    g.dispose();
    if(isDrag){
        setConductors();
        //start();
    }
    boxWidth=0;  // reset the yellow message box.
    isDrag=false;
    dragThing=null;
    startThread();
  }

  void poissonPanel_mouseDragged(MouseEvent e){
    mouseX=e.getX();
    mouseY=e.getY();
    double x;
    double y;
    int maxPix=iwidth;
    int minPix=0;
    if(mouseX<minPix) mouseX=minPix;
      else if(mouseX>maxPix-2) mouseX=maxPix-2;
    x=xFromPix(mouseX);
    minPix=0;
    maxPix=iheight;
    if(mouseY<minPix) mouseY=minPix;
      else if(mouseY>maxPix-2) mouseY=maxPix-2;
    y=yFromPix(mouseY);

    if (activeElement!=null && hotSpot==1){
      //dx = activeElement.getWidth();
      //dy = activeElement.getHeight();
      activeElement.setPosition(mouseX,mouseY);
      //repaint();
    }else if (activeElement!=null && hotSpot==2){
      activeElement.setWidth(2*(mouseX-activeElement.getXPix()));
      //repaint();
    }else if (activeElement!=null && hotSpot==3){
      activeElement.setHeight(2*(mouseY-activeElement.getYPix()));
      //repaint();
    }else if (activeElement!=null && hotSpot==4){
      activeElement.setWidth(2*(mouseX-activeElement.getXPix()));
      activeElement.setHeight(2*(mouseY-activeElement.getYPix()));
      //repaint();
    }
    Graphics g=getGraphics();
    if (activeElement!=null  && isDrag){
        // blast the image onto the screen
        g.drawImage(osi,0,0,this);
        activeElement.paintActive(g);
    }
    if(dragThing!=null){
         g.setXORMode(getBackground());
         dragThing.paint(g);
         dragThing.paintMySlaves(g);
         //paintMyConnectors(dragThing,g);
         dragThing.setXY(x,y);
         dragThing.updateMySlaves();
         dragThing.paint(g);
         dragThing.paintMySlaves( g);
         //paintMyConnectors(dragThing,g);
         g.setPaintMode();
         owner.updateDataConnection(dragThing.hashCode());
    }
    paintCoordinates(g,mouseX,mouseY);
    g.dispose();
    /*
    if(activeElement!=null && activeElement.hasChanged){
        setConductors();
        invalidPotential=true;
        repaint();
        start();
    } */
  }

  public void setCaption(String s){
    caption=s;
    if(autoRefresh)repaint();
  }



    /**
    Fill the arrays.
  */
  public void changeVoltage(PotentialObject p, double v){
    Rectangle r = getBounds();
    double cellArea= xUnitPerCell*yUnitPerCell;
    if (cellArea==0) return;
    p.v=v;
    int istart=xPixToIndex(p.getXPix()-p.getWidth()/2);
    int jstart=yPixToIndex(p.getYPix()-p.getHeight()/2);  // vertical direction index
    int istop=istart+(int)(p.getWidth()/this.xPixPerCell+0.5);
    istop=Math.min(istop,yPts2g-1);
    int jstop=jstart+(int)(p.getHeight()/this.yPixPerCell+0.5);
    jstop=Math.min(jstop,yPts2g-1);
          for   (int i=istart;i<istop;i++)
            for (int j=jstart;j<jstop;j++){
                if( p instanceof RectObject || p.isInsideThing(xIndexToPix(i),yIndexToPix(j) ) ){ // no need to check for inside since start and stop are set.
                  if(p.getChargeType()==PotentialObject.CONDUCTOR_TYPE){
                     potential[i][yPts2g-1-j]=-p.getPotential();   // the last conductor fixes the voltage
                  }
                }
            }
  }

  /**
    Fill the arrays.
  */
  public void setConductors(){
    Rectangle r = getBounds();
    for   (int i=1;i<xPts2g-1;i++) // start with no conductors  and no charge  but leave the potential alone.
      for (int j=1;j<yPts2g-1;j++){
          isConductor[i][j]=false;
          charge[i][j]=0;
          dielectric[i][j]=1;
      }
    // set the dielectric first since the conductors may change the values close to the surface
    double cellArea= xUnitPerCell*yUnitPerCell;
    if (cellArea==0) return;
    PotentialObject p=null;
    for (int k=0;k<things.size();k++){
        Thing t = (Thing) things.elementAt(k);
        if(t instanceof PotentialObject){
          p=(PotentialObject)t;
          int istart=xPixToIndex(p.getXPix()-p.getWidth()/2)+1;
         // int istop=xPixToIndex(p.getXPix()+p.getWidth()/2);
          int jstart=yPixToIndex(p.getYPix()-p.getHeight()/2)+1;  // vertical direction index
          //int jstop=yPixToIndex(p.getYPix()+p.getHeight()/2);
          int istop=istart+(int)(p.getWidth()/this.xPixPerCell+0.5)-2;
          istop=Math.min(istop,yPts2g-1);
          int jstop=jstart+(int)(p.getHeight()/this.yPixPerCell+0.5)-2;
          jstop=Math.min(jstop,yPts2g-1);

          for   (int i=istart;i<istop;i++)
            for (int j=jstart;j<jstop;j++){
                if( p instanceof RectObject || p.isInsideThing(xIndexToPix(i),yIndexToPix(j) ) ){ // no need to check for inside since start and stop are set.
                //if( p.isInsideThing(xIndexToPix(i),yIndexToPix(j) ) ){ // no need to check for inside since start and stop are set.
                  if(p.getChargeType()==PotentialObject.DIELECTRIC_TYPE){
                      dielectric[i][yPts2g-1-j]=p.getPotential()+1;   // the last conductor fixes the voltage
                  }
                }
            }
        }

    }

    for(int i=0;i<xPts2g;i++){   // left and rigth edge are conductors
        isConductor[i][0]=true;
        potential[i][0]=0.0;
        dielectric[i][0]=1.0;
        isConductor[i][yPts2g-1]=true;
        potential[i][yPts2g-1]=0.0;
        dielectric[i][yPts2g-1]=1.0;
    }
    for (int j=0;j<yPts2g;j++){   // top and bottom are conductors
        isConductor[0][j]=true;
        potential[0][j]=0.0;
        dielectric[0][j]=1.0;
        isConductor[xPts2g-1][j]=true;
        potential[xPts2g-1][j]=0.0;
        dielectric[xPts2g-1][j]=1.0;
    }
    for (int k=0;k<things.size();k++){
        Thing t = (Thing) things.elementAt(k);
        if(t instanceof PotentialObject){
          p=(PotentialObject)t;
          int istart=xPixToIndex(p.getXPix()-p.getWidth()/2);
          //int istop=xPixToIndex(p.getXPix()+p.getWidth()/2);
          int jstart=yPixToIndex(p.getYPix()-p.getHeight()/2);  // vertical direction index
          //int jstop=yPixToIndex(p.getYPix()+p.getHeight()/2);
          int istop=istart+(int)(p.getWidth()/this.xPixPerCell+0.5);
          istop=Math.min(istop,yPts2g-1);
          int jstop=jstart+(int)(p.getHeight()/this.yPixPerCell+0.5);
          jstop=Math.min(jstop,yPts2g-1);
          for   (int i=istart;i<istop;i++)
            for (int j=jstart;j<jstop;j++){
                if( p instanceof RectObject || p.isInsideThing(xIndexToPix(i),yIndexToPix(j) ) ){ // no need to check for inside since start and stop are set.
                //if( p.isInsideThing(xIndexToPix(i),yIndexToPix(j) ) ){ // no need to check for inside since start and stop are set.
                  if(p.getChargeType()==PotentialObject.DENSITY_TYPE){
                      isConductor[i][yPts2g-1-j]=false;
                      dielectric[i][yPts2g-1-j]=1;
                      charge[i][yPts2g-1-j]+=p.getPotential()*cellArea;// charge density adds
                  }else if(p.getChargeType()==PotentialObject.CONDUCTOR_TYPE){
                      isConductor[i][yPts2g-1-j]=true;
                      dielectric[i][yPts2g-1-j]=1;
                      charge[i][yPts2g-1-j]=0;
                      potential[i][yPts2g-1-j]=-p.getPotential();   // the last conductor fixes the voltage
                  }
                }
            }
        }

    }
   // setContourLevels();
   copyToDisplayMatrix();
   contour.setGrid(displaymatrix,xmin,xmax,ymin,ymax);
  }
/*
  public void setContourLevels(){
     if(contourInterval==0 || things.size()==0){
         contour.setAutoLevels(true);
         //contour.setGrid(potential,xmin,xmax,ymin,ymax);
         return;
     }
     double vmin=0, vmax=0, vtemp=0;
     for (int k=0;k<things.size();k++){
             vtemp = ((PotentialObject)things.elementAt(k)).getPotential();
             if(vtemp<vmin) vmin=vtemp;
             if(vtemp>vmax) vmax=vtemp;
     }
     if(vmax==vmin){
         contour.setAutoLevels(true);
         //contour.setGrid(potential,xmin,xmax,ymin,ymax);
         return;
     }
     int numLevels=(int)Math.floor(1+(vmax-vmin)/contourInterval);
//    System.out.println("levels"+ numLevels);
     double[] levels= new double[numLevels];
     vtemp=vmin;
     for(int k=0; k<numLevels; k++){
         levels[k]=vtemp;
         vtemp=vtemp+contourInterval;
     }
     contour.setLevels(levels, levels.length);  // this also sets autolevels to false
  }

*/

  public void setDefault(){
      stopThread();
      tolerance=1.0e-4;
      message=null;
      showCoordOnDrag=true;
      showVOnDrag=false;
      showEOnDrag=false;
      setXRange(xmin,xmax);
      message=null;
      clearElements();
      chargeDataSource=null;  // data connections have been removed in the applet.
      potentialDataSource=null;
      if(autoRefresh) recalculate();
  }


  public void setDefaultVoltate(double v){defaultVoltage=v;}

  public void setDefaultChargeType(int t){defaultChargeType=t;}

  public void setGutter(int g){
    if(gutter==g)return;
    resizeMatrices(xPts, yPts,g);
    if(autoRefresh) recalculate();
  }

  public void setFieldResolution(int r){
    if(vectorResolution==r)return;
    vectorResolution=r;
    resizeMatrices(xPts, yPts, gutter);
    if(autoRefresh) recalculate();
  }

  public void setFieldFromGrad(){
      int skip=1;  // scale factor for grid points
      int rPts=yPts;
      int cPts=xPts;
      //while(rPts>iwidth/15) {rPts=rPts/2; skip++; }
      skip=1+(int)(16.0/xPixPerCell);
      skip=vectorResolution;
      rPts=yPts/skip;
      cPts=xPts/skip;
      double[][][] electric=field.resize(yPts/skip,xPts/skip);
      field.setLength(skip*xPixPerCell*0.75);
      double fx,fy,mag;
      int iiy,ii;
      for(  int iy=0; iy<rPts; iy++){
          iiy=gutter+iy*skip;
          ii=rPts-iy-1;
          for( int ix=0; ix<cPts; ix++){
              fx=-dudx(gutter+ix*skip,iiy);
              fy=-dudy(gutter+ix*skip,iiy);
              mag=Math.sqrt(fx*fx+fy*fy);
              electric[ii][ix][0]=fx/mag;
              electric[ii][ix][1]=fy/mag;
              electric[ii][ix][2]=mag;
          }
      }
  }

  public void setMessage(String msg){
      if(msg==null || msg.trim().equals("")) message =null;
      else message=msg;
      if(autoRefresh)repaint();
  }


  public void setObjectString(String s){newObjectString=s;}

  public boolean setRange(String rs){
      boolean error=false;
      double[] range=new double[4];
      StringTokenizer tokens=new StringTokenizer(rs.trim(),", ; / \\ ( { [ ) } ] \t \n \r");
      if(tokens.countTokens()<4) error=true;
      else for(int i=0; i<4; i++){
          try{
              range[i]=Double.valueOf(tokens.nextToken().trim()).doubleValue();
          }catch(NumberFormatException e){
              System.out.println("Error setting range:"+ rs);
              error=true;
          }
      }
      if (!error){
          xmin = range[0];
          xmax = range[1];
          ymin = range[2];
          ymax = range[3];
      }else{
          return false;
      }
      return true;
  }

  public void setShowContours(boolean sc){
      showContours=sc;
      contour.setNoContours(!showContours);
      if(autoRefresh)repaint();
  }

  public void setShowGrid(boolean sg){
     showGrid=sg;
     if(autoRefresh)repaint();
  }

  public void setShowCharge(boolean sc){
     showCharge=sc;
     if(autoRefresh)repaint();
  }

  public void  setShowVOnDrag(boolean sv){showVOnDrag=sv; }

  public void  setShowRhoOnDrag(boolean sr){showRhoOnDrag=sr; }

  public void  setShowEOnDrag(boolean se){showEOnDrag=se; }

  public void setShowFieldVectors(boolean sfv){
      showFieldVectors=sfv;
     // setFields();
      if(autoRefresh)repaint();
  }

  public void setTolerance(double t){tolerance=t;}
  public void setMaxIterations(int m){maxInterations=m;}

  public void setXRange(double min, double max){
      if(max==min){
          xmin=min-0.5;
          xmax=max+0.5;
      }else if(max>min){
          xmin=min;
          xmax=max;
      } else {  //min < max so switch
          xmin=max;
          xmax=min;
      }
      if (iwidth==0) return;
      double scale=(xmax-xmin)/iwidth;
      ymin=(ymax+ymin)/2-scale*iheight/2.0;
      ymax=ymin+scale*iheight;
      if(autoRefresh)recalculate();
  }

  public void setYRange(double min, double max){
      if(max==min){
          ymin=min-0.5;
          ymax=max+0.5;
      }else if(max>min){
          ymin=min;
          ymax=max;
      } else {  //min < max so switch
          ymin=max;
          ymax=min;
      }
      if(iheight==0) return;
      double scale=(ymax-ymin)/iheight;
      xmin=(xmax+xmin)/2-scale*iwidth/2.0;
      xmax=xmin+scale*iwidth;
      if(autoRefresh)recalculate();
  }


  /**
   * Used by the browser when the HTML page is closed.
   *
   * Do not script.
   *
   */
  public void destroy() {
   // interrupted=true;
    stopThread();  // this will wait for a join.
    contour.destroy();
  }

  
  private final static int STATE_INIT = 0;
  private final static int STATE_LOOP = 1;
  private final static int STATE_STOP = 2;
  
  
  private int state = STATE_INIT;
  private int counter = 0;
  private double err = 0;
  private Timer timer;
  
	@SuppressWarnings("unused")
	public void run() {
		while (true) {
			switch (state) {
			case STATE_INIT:
				keepRunning = true;
				err = 10 * tolerance;
				counter = 0;
				setMessage(null);
				state = STATE_LOOP;
				continue;
			case STATE_LOOP:
				if (keepRunning && 
						( /** @j2sNative true || */ runThread == Thread.currentThread()) 
						&& counter < maxInterations) {
					try {
						for (int i = 0; i < 10; i++) {
							if (keepRunning)
								err = step();
							if (err < tolerance)
								keepRunning = false;
							counter++;
						}
						if (keepRunning) {
							if (/** @j2sNative true || */ false) {
								timer = new Timer(sleepTime, new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										run();
									}									
								});
								timer.setRepeats(false);
								timer.start();
							} else {
								Thread.sleep(sleepTime);
							}
						}
					} catch (InterruptedException e) {
						keepRunning = false;
					}
				}
				state = STATE_STOP;
				continue;
			case STATE_STOP:
				keepRunning = false;
				
				if ((err > tolerance) && (counter >= maxInterations)) {
					setMessage(owner.label_not_converge);
				} else
					setMessage(null);
				invalidPotential = true;
				copyToDisplayMatrix();
				owner.updateDataConnections();
				runThread = null;
				repaint();
				return;
			}
		}

	}

  //Start the Poisson calculation
  public synchronized void startThread() {
      if (runThread == null){
          runThread = new Thread(this);
          keepRunning=true;
          state = STATE_INIT;
          runThread.start();
      }
  }


	public synchronized void stopThread() {
		Thread currentThread = runThread;
		keepRunning = false;
		/**
		 * 
		 * @j2sNative
		 * 
		 * 			this.runThread=null;
		 */
		{
			if (currentThread != null) {
				try {
					currentThread.interrupt();
					currentThread.join();
				} catch (InterruptedException e) {
					currentThread.stop();
					runThread = null;
				}
			}
		}
	}

  public double step(){
     double err=0, vNew=0, vOld=0, dV=0, w;
     //double bound;
     double d1,d2,d3,d4,da;
     w=2/(1+Math.PI/(xPts2g));
     adjustVoltage();
     for (int i=1;i<xPts2g-1;i++)
        for (int j=1;j<yPts2g-1;j++){
         if( !keepRunning ) return 10*tolerance;
         if(!isConductor[i][j]){ // not a conductor
             d1=(dielectric[i+1][j]+dielectric[i][j])/2;
             d2=(dielectric[i-1][j]+dielectric[i][j])/2;
             d3=(dielectric[i][j+1]+dielectric[i][j])/2;
             d4=(dielectric[i][j-1]+dielectric[i][j])/2;
             da=(d1+d2+d3+d4)/4.0;   // average dielectric
             vOld=potential[i][j];
             vNew=(d1*potential[i+1][j]+d2*potential[i-1][j]+d3*potential[i][j+1]+d4*potential[i][j-1])/4.0;
             dV=vNew/da- vOld;
             potential[i][j]=(potential[i][j]+w*dV) - charge[i][j]/(dielectric[i][j]);
             //if(Math.abs(dV)>err) err=Math.abs(potential[i][j]-vOld*4/(d1+d2+d3+d4));
         }
     }// of j loop
    // if(adjustVoltage()) err= tolerance ;  // look for constant Q conteuctors and adjust their values.
    for (int i=xPts2g-2;i>0;i--)
      for (int j=yPts2g-2;j>0;j--){
         if( !keepRunning ) return 10*tolerance;
         if(!isConductor[i][j]){ // not a conductor
             d1=(dielectric[i+1][j]+dielectric[i][j])/2;
             d2=(dielectric[i-1][j]+dielectric[i][j])/2;
             d3=(dielectric[i][j+1]+dielectric[i][j])/2;
             d4=(dielectric[i][j-1]+dielectric[i][j])/2;
             da=(d1+d2+d3+d4)/4.0 ; // average dielectric
             vOld=potential[i][j];
             vNew=(d1*potential[i+1][j]+d2*potential[i-1][j]+d3*potential[i][j+1]+d4*potential[i][j-1])/4.0;
             dV=vNew/da- vOld;
             potential[i][j]=potential[i][j] + w*dV - charge[i][j]/(dielectric[i][j]);
             if(Math.abs(dV)>err) err=Math.abs(potential[i][j]-vOld);
         }
     }// of j loop
     return err;
  }

    public void paintBoundCharge(PotentialObject p, Graphics g){
      double q=0;
      int istart=xPixToIndex(p.getXPix()-p.getWidth()/2)-2;
      //int istop=xPixToIndex(p.getXPix()+p.getWidth()/2);
      int jstart=yPixToIndex(p.getYPix()-p.getHeight()/2)-2;  // vertical direction index
      //int jstop=yPixToIndex(p.getYPix()+p.getHeight()/2);
      int istop=istart+(int)(p.getWidth()/this.xPixPerCell)+4;
      int jstop=jstart+(int)(p.getHeight()/this.yPixPerCell)+4;
      istart=Math.max(1,istart);  // do not include the edge;
      istop=Math.min(xPts2g-2,istop);  // do not include the edge;
      jstart=Math.max(1,jstart);  // do not include the edge;
      jstop=Math.min(yPts2g-2,jstop);  // do not include the edge;
      int jj;
      for (int j=jstart;j<jstop;j++){
          jj=yPts2g-1-j;
          for(int i=istart;i<istop;i++){
                q=potential[i][jj]-(potential[i+1][jj]+potential[i-1][jj]+potential[i][jj+1]+potential[i][jj-1])/4.0;
                paintChargeDot(-q,  i-gutter,  jj-gutter,  g);
          }
      }
  }

  public void paintCharge(PotentialObject p, Graphics g){
      if(p.getChargeType()==PotentialObject.DIELECTRIC_TYPE){
          paintBoundCharge( p,  g);
          return;
      }
      double q=0;
      int istart=xPixToIndex(p.getXPix()-p.getWidth()/2);
      //int istop=xPixToIndex(p.getXPix()+p.getWidth()/2);
      int jstart=yPixToIndex(p.getYPix()-p.getHeight()/2);  // vertical direction index
      //int jstop=yPixToIndex(p.getYPix()+p.getHeight()/2);
      int istop=istart+(int)(p.getWidth()/this.xPixPerCell+0.5);
      int jstop=jstart+(int)(p.getHeight()/this.yPixPerCell+0.5);
      istart=Math.max(1,istart);  // do not include the edge;
      istop=Math.min(xPts2g-2,istop);  // do not include the edge;
      jstart=Math.max(1,jstart);  // do not include the edge;
      jstop=Math.min(yPts2g-2,jstop);  // do not include the edge;
      int jj;
      for (int j=jstart;j<jstop;j++){
          jj=yPts2g-1-j;
          for(int i=istart;i<istop;i++){
                if( p instanceof RectObject || p.isInsideThing(xIndexToPix(i),yIndexToPix(j) ) ){ // no need to check for inside since start and stop are set.
                if( // make sure conductors do not touch!
                    (isConductor[i+1][jj] && isConductor[i-1][jj] && !isConductor[i][jj]) ||
                    (isConductor[i][jj+1] && isConductor[i][jj-1] && !isConductor[i][jj]))
                  {
                      q=0;
                  }else q=potential[i][jj]-(potential[i+1][jj]+potential[i-1][jj]+potential[i][jj+1]+potential[i][jj-1])/4.0;
                paintChargeDot(-q,  i-gutter,  jj-gutter,  g);
                }
          }
      }
  }

  void paintChargeDot(double q, int i, int j, Graphics g){
      int dia=1;
      if(q>tolerance){
                 q=Math.sqrt(q);  // area is proportional to charge.
                 dia=(int)(inducedChargeScale*q);
                 g.setColor(positiveChargeColor);
                 g.fillOval((int)(xPixPerCell*(i+0.5))-dia/2,(int)(yPixPerCell*(yPts-j-0.5))-dia/2,dia,dia);
      }else if(q<-tolerance){
                 q=Math.sqrt(-q);  // area is proportional to charge.
                 dia=(int)(inducedChargeScale*q);
                 g.setColor(negativeChargeColor);
                 g.fillOval((int)(xPixPerCell*(i+0.5))-dia/2,(int)(yPixPerCell*(yPts-j-0.5))-dia/2,dia,dia);
      }
  }

  public double calcCharge(PotentialObject p){
      double q=0;
      int istart=xPixToIndex(p.getXPix()-p.getWidth()/2);
      //int istop=xPixToIndex(p.getXPix()+p.getWidth()/2);
      int jstart=yPixToIndex(p.getYPix()-p.getHeight()/2);  // vertical direction index
      //int jstop=yPixToIndex(p.getYPix()+p.getHeight()/2);
      int istop=istart+(int)(p.getWidth()/this.xPixPerCell+0.5);
      int jstop=jstart+(int)(p.getHeight()/this.yPixPerCell+0.5);
      istart=Math.max(1,istart);  // do not include the edge;
      istop=Math.min(yPts2g-2,istop);  // do not include the edge;
      jstart=Math.max(1,jstart);  // do not include the edge;
      jstop=Math.min(yPts2g-2,jstop);  // do not include the edge;
      for   (int i=istart;i<istop;i++)
          for (int j=jstart;j<jstop;j++){
                int jj=yPts2g-1-j;
                if( p instanceof RectObject || p.isInsideThing(xIndexToPix(i),yIndexToPix(j) ) ){ // no need to check for inside since start and stop are set.
                  if( // make sure conductors do not touch!
                    (isConductor[i+1][jj] && isConductor[i-1][jj] && !isConductor[i][jj]) ||
                    (isConductor[i][jj+1] && isConductor[i][jj-1] && !isConductor[i][jj])
                  ); // no charge to add!
                  else q+=potential[i][jj]-(potential[i+1][jj]+potential[i-1][jj]+potential[i][jj+1]+potential[i][jj-1])/4.0;

                }
          }

    return -q;
  }

  double calcPotentialEnergy(){
      Thing t;
      double u=0;
      PotentialObject p;
      for (int i=0; i<things.size();i++){
          t = (Thing)things.elementAt(i);
          if(t instanceof PotentialObject){
            p = (PotentialObject)t;
            u+=calcPotentialEnergy(p);
          }
      }
      return u;
  }

  public double calcPotentialEnergy(PotentialObject p){
      double q=0;
      double pe=0;
      int istart=xPixToIndex(p.getXPix()-p.getWidth()/2);
      //int istop=xPixToIndex(p.getXPix()+p.getWidth()/2);
      int jstart=yPixToIndex(p.getYPix()-p.getHeight()/2);  // vertical direction index
      //int jstop=yPixToIndex(p.getYPix()+p.getHeight()/2);
      int istop=istart+(int)(p.getWidth()/this.xPixPerCell+0.5);
      int jstop=jstart+(int)(p.getHeight()/this.yPixPerCell+0.5);
      istart=Math.max(1,istart);  // do not include the edge;
      istop=Math.min(yPts2g-2,istop);  // do not include the edge;
      jstart=Math.max(1,jstart);  // do not include the edge;
      jstop=Math.min(yPts2g-2,jstop);  // do not include the edge;
      for   (int i=istart;i<istop;i++)
          for (int j=jstart;j<jstop;j++){
                int jj=yPts2g-1-j;
                if( p instanceof RectObject || p.isInsideThing(xIndexToPix(i),yIndexToPix(j) ) ){ // no need to check for inside since start and stop are set.
                if( // make sure conductors do not touch!
                    (isConductor[i+1][jj] && isConductor[i-1][jj] && !isConductor[i][jj]) ||
                    (isConductor[i][jj+1] && isConductor[i][jj-1] && !isConductor[i][jj]))
                {
                    q=0;
                }else q=potential[i][jj]-(potential[i+1][jj]+potential[i-1][jj]+potential[i][jj+1]+potential[i][jj-1])/4.0;
                pe+=q*potential[i][jj];
                }
          }

    return pe;
  }

  public void paintCharge(Graphics g){
    double q;
    int size=1;
    int ii,jj;
    int start=1;
    start=Math.max(gutter,start);  // start at the gutter but start 1 in if the gutter is zero.
    /*
    for (int i=start;i<xPts2g-start;i++){
        ii=i-gutter;
        for (int j=1+gutter;j<yPtsg-1;j++){
             q=potential[i][j]-(potential[i+1][j]+potential[i-1][j]+potential[i][j+1]+potential[i][j-1])/4.0;
             paintChargeDot( -q,  ii,  j-gutter,  g);
         }
    } */
    if (gutter==0) for(int i=1;i<xPts-1;i++){   // left and right edge if gutter=0
        jj=0;
        q=potential[i][jj]-(potential[i+1][jj]+potential[i-1][jj]+potential[i][jj+1]+potential[i][jj])/4.0;
        paintChargeDot( -q,  i,  0,  g);
        jj=yPts-1;
        q=potential[i][jj]-(potential[i+1][jj]+potential[i-1][jj]+potential[i][jj]+potential[i][jj-1])/4.0;
        paintChargeDot( -q,  i,  yPts-1,  g);
    }
    if (gutter==0) for (int j=1;j<yPts-1;j++){   // top and bottom
        ii=0;
        q=potential[ii][j]-(potential[ii+1][j]+potential[ii][j]+potential[ii][j+1]+potential[ii][j-1])/4.0;
        paintChargeDot( -q,  0,  j,  g);
        ii=xPts-1;
        q=potential[ii][j]-(potential[ii][j]+potential[ii-1][j]+potential[ii][j+1]+potential[ii][j-1])/4.0;
        paintChargeDot( -q,  xPts-1,  j,  g);
    }
    return;
  }

  double adjustVoltage(){ // keep q constant on contuctors.  Return true if we did adjust.
      Thing t;
      double err=0;
      double qnew=0, q=0;
      double vnew=0, v=0;
      PotentialObject p;
      for (int i=0; i<things.size();i++){
          t = (Thing)things.elementAt(i);
          if(t instanceof PotentialObject){
            p = (PotentialObject)t;
            if(p.isConstantQ && (p.getChargeType()==PotentialObject.CONDUCTOR_TYPE)) {
               v=p.getPotential();
               q=calcCharge(p);
               qnew=p.getQ();
               err+= Math.abs(qnew-q);
              // if(Math.abs(qnew-q)/(Math.abs(q)+Math.abs(qnew))>0.01){ // we are not close enough.
              //     didAdjust=true;
              // }
               if((qnew-q)>0) vnew=v+0.05*Math.abs(qnew-q)/(Math.abs(q)+Math.abs(qnew)+1.0e-6);    // we are too high so decrease the voltage
               else if((qnew-q)<0) vnew=v-0.05*Math.abs(qnew-q)/(Math.abs(q)+Math.abs(qnew)+1.0e-6);
               else vnew=v;
               this.changeVoltage(p,vnew);
               //return;//  only one constantQ conductor is allowed!
            }
            //System.out.println("Q="+q+" V="+v);
          }
      }
      return err;
  }

  void printChargeValues(){
      Thing t;
      double q=0,qtot=0;
      PotentialObject p;
      for (int i=0; i<things.size();i++){
          t = (Thing)things.elementAt(i);
          if(t instanceof PotentialObject){
            p = (PotentialObject)t;
            q=calcCharge(p);
            qtot+=q;
            System.out.println("chage="+q);
          }
      }
   double q1=0, q2=0;
   double q3=0, q4=0;
   int ii,jj;
   for(int i=1;i<xPts2g-1;i++){   // left and right edge if gutter=0
        jj=0;
        q1-=potential[i][jj]-(potential[i+1][jj]+potential[i-1][jj]+potential[i][jj+1]+potential[i][jj])/4.0;
        jj=yPts2g-1;
        q2-=potential[i][jj]-(potential[i+1][jj]+potential[i-1][jj]+potential[i][jj]+potential[i][jj-1])/4.0;
    }
   // System.out.println("charge left="+q1);
    //System.out.println("charge right="+q2);
    for (int j=1;j<yPts2g-1;j++){   // top and bottom
        ii=0;
        q3-=potential[ii][j]-(potential[ii+1][j]+potential[ii][j]+potential[ii][j+1]+potential[ii][j-1])/4.0;
        ii=xPts2g-1;
        q4-=potential[ii][j]-(potential[ii][j]+potential[ii-1][j]+potential[ii][j+1]+potential[ii][j-1])/4.0;
    }
    //System.out.println("chage top="+q3);
    //System.out.println("chage bottom="+q4);
    qtot+=q1+q2+q3+q4;
    System.out.println("chage total="+qtot);

  }

  public void paintGrid(Graphics g, Rectangle r){
    g.setColor(Color.black);
    for   (int i=0;i<=xPts;i++)
      for (int j=0;j<=yPts;j++)
        g.drawLine((int)(xPixPerCell*i),(int)(yPixPerCell*j),(int)(xPixPerCell*i),(int)(yPixPerCell*j));
  }

  public void paintMessage(Graphics osg, String msg){
      if (msg==null) return;
      FontMetrics fm=osg.getFontMetrics(f);
      osg.setColor(Color.yellow);
      int w=15+fm.stringWidth(msg);
      osg.fillRect(iwidth-w-5,iheight-15,w,15);
      osg.setColor(Color.black);
      osg.drawString(msg, iwidth-w+2, iheight-3);
  }

  public void resetMatrices(){
    for   (int i=0;i<xPts2g;i++){
      for (int j=0;j<yPts2g;j++){
        isConductor[i][j]=false;
        potential[i][j]=0.0;
        charge[i][j]=0.0;
        dielectric[i][j]=1.0;
      }
    }
  }
  public void recalculate(){
      setConductors();
      invalidPotential=true;
      repaint();
      startThread();
  }

  public synchronized void resizeMatrices(int xp,int yp, int gp){
      if(xPts==xp && yPts==yp && gutter==gp) return;
      stopThread();
      gutter=gp;
      xPts=xp;
      yPts=yp;
      xPtsg=xp+gutter;
      yPtsg=yp+gutter;
      gutter2=2*gutter;
      xPts2g=xp+gutter2;
      yPts2g=yp+gutter2;
      isConductor = new boolean[xPts2g][yPts2g];          //shows where voltage is known at start.
      potential = new double[xPts2g][yPts2g];            //holds matrix to calc voltage when calc button is pressed.
      charge = new double[xPts2g][yPts2g];
      dielectric = new double[xPts2g][yPts2g];
      displaymatrix= new double[xPts][yPts];
      if(chargeDataSource!=null) chargeDataSource=new ChargeDataSource(owner);
      if(potentialDataSource!=null) potentialDataSource=new PotentialDataSource(owner);
      int skip=1;  // scale factor for grid points
      int rPts=yPts;
      int cPts=xPts;
      //while(rPts>iwidth/15) {rPts=rPts/2; skip++; }
      skip=1+(int)(16.0/xPixPerCell);
      skip=vectorResolution;
      rPts=yPts/skip;
      cPts=xPts/skip;
      field.resize(yPts/skip,xPts/skip);
      osi=null;
  }

  public void makeActiveElementNull(){
    activeElement=null;
    repaint();
  }

  public void clearElements(){
    stopThread();
    activeElement = null;
    things.removeAllElements();
    hasFieldThing=false;
    hasContourThing=false;
    resetMatrices(); //set all arrays to zero
    if(autoRefresh) recalculate();
  }

  public int getPotentialArrayID(){
     if(potentialDataSource==null) potentialDataSource=new PotentialDataSource(owner);
     return potentialDataSource.hashCode();
  }

  public int getChargeArrayID(){
     if(chargeDataSource==null) chargeDataSource=new ChargeDataSource(owner);
     return chargeDataSource.hashCode();
  }

  public int getPixWidth(){
      return getSize().width;
  }
  public int getPixHeight(){
      return getSize().height;
  }

  final double getPotential(double x, double y){
      int xPix=pixFromX(x);
      int yPix=pixFromY(y);
      return potential[xPixToIndex(xPix)][yPts2g-yPixToIndex(yPix)-1]; //voltage
  }

  final double dudx(double x, double y){
      if(xPixPerCell==0) return 0;
      int xPix=pixFromX(x);
      int yPix=pixFromY(y);
      //if(isPixInsidePotentialObject(xPix,yPix)) return 0;
      int ix=xPixToIndex(xPix);
      int iy=yPts2g-yPixToIndex(yPix)-1;
      if(ix<1)ix=1; else if(ix>xPts2g-2) ix=xPts2g-2;
      if(iy<1)iy=1; else if(iy>yPts2g-2) iy=yPts2g-2;
      if (isConductor[ix][iy] || isConductor[ix][iy-1] || isConductor[ix][iy+1]) return 0;
      double z1=potential[ix-1][iy];
      double z2=potential[ix][iy];
      double z3=potential[ix+1][iy];
      double d1=(z3-z2);
      double d2=(z2-z1);
      if(Math.abs(d1)>Math.abs(d2)) return -d1/xUnitPerCell;
      else return -d2/xUnitPerCell;
  }

  final double dudx(int ix, int iy){
      if(xPixPerCell==0) return 0;
      if(ix<1)ix=1; else if(ix>xPts2g-2) ix=xPts2g-2;
      double z1=potential[ix-1][iy];
      double z2=potential[ix][iy];
      double z3=potential[ix+1][iy];
      double d1=(z3-z2);
      double d2=(z2-z1);
      if(Math.abs(d1)>Math.abs(d2)) return -d1/xUnitPerCell;
      else return -d2/xUnitPerCell;

    //  if(ix<2)ix=2; else if(ix>xPts2g-3) ix=xPts2g-3;
     // double z1=potential[ix-2][iy];
     // double z2=potential[ix-1][iy];
     // double z3=potential[ix+1][iy];
     // double z4=potential[ix+2][iy];
    //  return -(-z4+8*z3-8*z2+z1)/(12*xUnitPerCell);
  }

    final double dudy(double x, double y){
      if(yPixPerCell==0) return 0;
      int xPix=pixFromX(x);
      int yPix=pixFromY(y);
      //if(isPixInsidePotentialObject(xPix,yPix)) return 0;
      int ix=xPixToIndex(xPix);
      int iy=yPts2g-yPixToIndex(yPix)-1;
      if(ix<1)ix=1; else if(ix>xPts2g-2) ix=xPts2g-2;
      if(iy<1)iy=1; else if(iy>yPts2g-2) iy=yPts2g-2;
      if (isConductor[ix][iy] || isConductor[ix+1][iy] || isConductor[ix-1][iy]) return 0;
      double z1=potential[ix][iy-1];
      double z2=potential[ix][iy];
      double z3=potential[ix][iy+1];
      double d1=(z3-z2);
      double d2=(z2-z1);
      if(Math.abs(d1)>Math.abs(d2)) return -d1/yUnitPerCell;
      else return -d2/yUnitPerCell;
  }

  final double dudy(int ix, int iy){
      if(yPixPerCell==0) return 0;

      if(iy<1)iy=1; else if(iy>yPts2g-2) iy=yPts2g-2;
      double z1=potential[ix][iy-1];
      double z2=potential[ix][iy];
      double z3=potential[ix][iy+1];
      double d1=(z3-z2);
      double d2=(z2-z1);
      if(Math.abs(d1)>Math.abs(d2)) return -d1/yUnitPerCell;
      else return -d2/yUnitPerCell;

      //if(iy<2)iy=2; else if(iy>yPts2g-3) iy=yPts2g-3;
     // double z1=potential[ix][iy-2];
     // double z2=potential[ix][iy-1];
      //double z3=potential[ix][iy+1];
     // double z4=potential[ix][iy+2];
     // return -(-z4+8*z3-8*z2+z1)/(12*yUnitPerCell);
  }

  Thing getThing(int id){
     Thing t=null;
     for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t.hashCode()==id){
           return t;
         }
     }
     return null;
  }

 /*
  final boolean isPixInsideConductor(int xPix, int yPix){
    for (int i=things.size()-1; i>=0;i--){
      PotentialObject p = (PotentialObject)things.elementAt(i);
      if(p.isPixInside(xPix,yPix)){
          defaultVoltage=p.getPotential();
          return true;
      }
    }
    return false;
  } */

  final boolean isPixInsidePotentialObject(int xPix, int yPix){
    Thing t;
    PotentialObject p;
    for (int i=things.size()-1; i>=0;i--){
      t = (Thing)things.elementAt(i);
      if(t instanceof PotentialObject){
        p=(PotentialObject)t;
        if((p.getChargeType()==PotentialObject.CONDUCTOR_TYPE)  && p.isInsideThing(xPix,yPix)){
          defaultVoltage=p.getPotential();
          return true;
        }
      }
    }
    return false;
  }

  public double xFromPix(int pix){
    if(contour!=null) return contour.xFromPix(pix);
    else return pix;
  }
  public int pixFromX(double x){
    if(contour!=null) return contour.pixFromX(x);
    else return (int)Math.round(x);
  }

  public double yFromPix(int pix){
    if(contour!=null) return contour.yFromPix(pix);
    else return pix;
  }
  public int pixFromY(double y){
    if(contour!=null) return contour.pixFromY(y);
    else return (int)Math.round(y);
  }

  /**
   * Repaint whenevers the system parameters are changed.
   *
   * @param              autoRefresh Automatic repaint?
   */
  public void setAutoRefresh(boolean ar){
      autoRefresh=ar;
      if(autoRefresh){
        setConductors();
        invalidPotential=true;
        repaint();
        startThread();
      }
  }

  public void setActiveElementVoltage(double ve){
    defaultVoltage=ve;
    if (activeElement!=null){
        activeElement.setV(ve);
        setConductors();
        startThread();
        //repaint();
    }
  }
  // inner class to override paintBeforeData before and  paintLast.
  class PContour extends SContour{

      private void paintBeforeData_Special( Graphics g, Rectangle r) {
          super.paintBeforeData(g,r);
          Thing t;
          for (int i=0; i<things.size();i++){
              t = (Thing)things.elementAt(i);
              if(t instanceof FieldThing && t.isVisible() ){
                  field.paint(g,r, isConductor,gutter); // paint the field vectors
              }else t.paint(g);  // paint all things.
          if(t instanceof ContourThing) return;  // quit painting things so the contours will paint
          }
      }

      public void paintBeforeData( Graphics g, Rectangle r) {
          if(hasContourThing){
              paintBeforeData_Special(g,r);
              return;
          }
          super.paintBeforeData(g,r);
          Thing t;
          PotentialObject p;
          for (int i=0; i<things.size();i++){
              t = (Thing)things.elementAt(i);
              if(t instanceof PotentialObject){
                  p=(PotentialObject)t;
                  if(p.getChargeType()!=PotentialObject.CONDUCTOR_TYPE) p.paint(g); // paint the charge density  and dielectrics first so contours show
              }else if( t instanceof FieldThing && t.isVisible() ) field.paint(g,r, isConductor,gutter); // paint the field vectors
          }
          if(showFieldVectors && !hasFieldThing ) field.paint(g,r, isConductor,gutter); // paint the field vectors
      }

      private void paintLast_Special(Graphics g, Rectangle r) {
          super.paintLast(g,r);
          Thing t;
          PotentialObject p;
          boolean contoursFound=false;
          for (int i=0; i<things.size();i++){
              t = (Thing)things.elementAt(i);
              if(!contoursFound){     // first find the contourThing.  Paint all things after that.
                if(t instanceof ContourThing)contoursFound=true;
              } else if(t instanceof FieldThing && t.isVisible() ){
                  field.paint(g,r, isConductor,gutter); // paint the field vectors
              }else t.paint(g);  // paint all graphics things.
          }
      }

      public void paintLast(Graphics g, Rectangle r) {
          if(hasContourThing){
              paintLast_Special(g,r);
              return;
          }
          super.paintLast(g,r);
          Thing t;
          PotentialObject p;
          for (int i=0; i<things.size();i++){
              t = (Thing)things.elementAt(i);
              if(t instanceof PotentialObject){
                p=(PotentialObject)t;
                if(p.getChargeType()==PotentialObject.CONDUCTOR_TYPE)p.paint(g);  //paint only conductors
              }else t.paint(g);  // paint all graphics things.
          }
      }
  }// end of SContour

  // inner class used for data connections.
  public class ChargeDataSource extends Object   implements edu.davidson.tools.SDataSource{
    SApplet applet;
    String[] varStrings= new String[]{"surfacedata"};
    double[][] ds=new double[xPts][yPts];  // the datasource state variables t,x,y,vx,vy,m;

    ChargeDataSource(SApplet a){ // Constructor
       applet=a;
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
      for (int i=1+gutter;i<xPtsg-1;i++)
        for (int j=1+gutter;j<yPts-1+gutter;j++){
             ds[i-gutter][j-gutter]=-inducedChargeScale*(potential[i][j]-(potential[i+1][j]+potential[i-1][j]+potential[i][j+1]+potential[i][j-1])/4.0);
      }
      double q;
      if (gutter==0) for(int i=1;i<xPts-1;i++){   // left and right edge if gutter=0
            int jj=0;
            q=-(potential[i][jj]-potential[i][jj+1])/2.0; // the cell to left, above and below have the same potential.
            ds[i-gutter][jj]=q*inducedChargeScale;
            //paintChargeDot( -q,  i-gutter,  0,  g);
            jj=yPts-1;
            q=-(potential[i][jj]-potential[i][jj-1])/2.0;
            ds[i-gutter][jj-gutter]=q*inducedChargeScale;
            //paintChargeDot( -q,  i-gutter,  yPts-1,  g);
      }
      if (gutter==0) for (int j=1+gutter;j<yPts-1;j++){   // top and bottom
            int ii=0;
            q=-(potential[ii][j]-potential[ii+1][j])/2.0;
            ds[ii][j-gutter]=q*inducedChargeScale;
            //paintChargeDot( -q,  0,  j-gutter,  g);
            ii=xPts-1;
            q=-(potential[ii][j]-potential[ii-1][j])/2.0;
            ds[ii-gutter][j]=q*inducedChargeScale;
            //paintChargeDot( -q,  xPts-1,  j-gutter,  g);
      }
      return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet a){applet=a;}
    public SApplet getOwner(){return applet;}    //owner is an SApplet.
  } // end of data source

    // inner class used for data connections.
  public class PotentialDataSource extends Object   implements edu.davidson.tools.SDataSource{
    SApplet applet;
    String[] varStrings= new String[]{"surfacedata"};
    double[][] ds=new double[xPts][yPts];  // the datasource state variables t,x,y,vx,vy,m;

    PotentialDataSource(SApplet a){ // Constructor
       applet=a;
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
       return displaymatrix;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet a){applet=a;}
    public SApplet getOwner(){return applet;}    //owner is an SApplet.
  } // end of data source
}

class PoissonPanel_mouseAdapter extends java.awt.event.MouseAdapter {
  PoissonPanel adaptee;

  PoissonPanel_mouseAdapter(PoissonPanel adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.poissonPanel_mousePressed(e);
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.poissonPanel_mouseReleased(e);
  }
}
class PoissonPanel_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  PoissonPanel adaptee;

  PoissonPanel_mouseMotionAdapter(PoissonPanel adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.poissonPanel_mouseMoved(e);
  }

  public void mouseDragged(MouseEvent e) {
    adaptee.poissonPanel_mouseDragged(e);
  }
}
