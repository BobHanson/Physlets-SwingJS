package bfield;
import java.awt.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.Timer;

import java.util.StringTokenizer;
import edu.davidson.tools.*;
import edu.davidson.display.*;
import edu.davidson.numerics.*;
import edu.davidson.graph.DataSet;
import java.util.Enumeration;

public class FieldPanel extends Panel  implements SScalable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
double tolerance=1.0e-5;
  private Vector     fieldSolvers=new Vector();
  Vector     arrowHeads=new Vector();
  Format    format= new Format("%-+8.4g");
  VectorField field=new VectorField(4,4);
  Rectangle fieldBounds=null;    // this is a hack to store the size of the field plot;
  SGraph  graph=new SGraph();
  FieldThing fieldThing=null;
  Image       osi = null;
  private String       message=null;
  private String       caption=null;
 // private boolean      showTime=true;
  private   Vector     wires=new Vector();
  private   Vector     things=new Vector();
  private   Font       f=new Font("Helvetica",Font.BOLD,14);
  private   boolean   osiInvalid=true;  // the osi is valid if the SGraph has been properly setup and drawn
  private   int       iwidth  = 0;
  private   int       iheight = 0;
 // private   int	    xOffset=0; // x offset in pixels
  // private   int	    yOffset=0; // y offset in pixels
  private   int     boxWidth=0;
  private   boolean   isDrag=false;
  private   int       hotSpot=0;
  private   Wire      dragWire=null;
  private   Thing     dragThing=null;
  private double xmin=-1;
  private double xmax=1;
  private double ymin=-1;
  private double ymax=1;
  private boolean showFieldVectors=true;
  private boolean showWires=true;
  private boolean showCoordOnDrag=true;
  private boolean showBOnDrag=false;
  private boolean showFieldLineOnClick=false;
  private boolean showFieldLineOnDoubleClick=false;
  private BField  owner;
  //private double maxB=0;  // the min-max for the B field;
  private String bxStr="0";
  private String byStr="0";
  private Parser     parserBx=null;
  private Parser     parserBy=null;
  private int        gridSize=32;
  private boolean    autoRefresh=true;
  private boolean    sketchMode=false;
  private SketchThing trailThing=null;
  //private Cursor sketchCursor=null;
  Image sketchImage=null;


  public FieldPanel(BField applet) {
      this.owner=applet;
      graph.setAutoscaleX(false);
      graph.setAutoscaleY(false);
      graph.setMinMaxX(xmin,xmax);
      graph.setMinMaxY(ymin,ymax);
      graph.setShowAxis(false);
      graph.setDataBackground( Color.white);
      addMouseMotionListener(new FieldPanel_mouseMotionAdapter(this));
      addMouseListener(new FieldPanel_mouseAdapter(this));
  }

  SApplet getOwner(){return owner;}

  public int getPixWidth(){ return iwidth;}
  public int getPixHeight(){ return iheight;}

  public void addThing(Thing t){
     things.addElement(t);
     if(autoRefresh){
         repaint();
     }
  }

  public Wire addWire(double current){
     double x=xmin+(xmax-xmin)*Math.random();
     double y=ymin+(ymax-ymin)*Math.random();
     Wire w= addWire(x,y,current);
     //w.noDrag=false;
     w.setDragable(true);
     w.noOptionDrag=false;
     return w;
  }

  public Wire addWire(double x, double y, double current){
     owner.lock.getBusyFlag();
     stopFieldThreads();
     if(hasCoils() ){
         fieldThing=null;
         wires.removeAllElements() ;
         things.removeAllElements() ;
     }
     Wire w=new Wire(this,x,y,current);
     wires.addElement(w);
     things.addElement(w);
     owner.lock.freeBusyFlag();
     if(autoRefresh){
         setFields();
         repaint();
     }
     return w;
  }

  public Wire addCoil(double current){
     double x=xmin+(xmax-xmin)*Math.random();
     double y=ymin+(ymax-ymin)*Math.random();
     Wire w= addCoil(x,y,current);
     w.setDragable(true);
     w.noOptionDrag=false;
     return w;
  }

  public Wire addCoil(double x, double y, double current){
     owner.lock.getBusyFlag();
     stopFieldThreads();
     if(hasWires() ){
         fieldThing=null;
         wires.removeAllElements();
         things.removeAllElements();
     }
     Wire w=new Coil(this,x,y,current);
     wires.addElement(w);
     things.addElement(w);
     owner.lock.freeBusyFlag();
     if(autoRefresh){
         setFields();
         repaint();
     }
     return w;
  }

  public FieldThing addField(){
     owner.lock.getBusyFlag();
     stopFieldThreads();
     FieldThing ft=new FieldThing(this);
     fieldThing=ft;
     things.addElement(ft);
     owner.lock.freeBusyFlag();
     if(autoRefresh){
         repaint();
     }
     return ft;
  }

  void clearAll(){
      owner.lock.getBusyFlag();
      stopFieldThreads();
      fieldThing=null;
      wires.removeAllElements();
      things.removeAllElements();
      arrowHeads.removeAllElements();
      owner.lock.freeBusyFlag();
     if(autoRefresh){
         setFields();
         repaint();
     }
  }
  void setWireXY(Wire w, double x, double y){
     owner.lock.getBusyFlag();
     stopFieldThreads();
     w.setXY(x,y);
     owner.lock.freeBusyFlag();
     this.osiInvalid=true;
     if(autoRefresh){
         setFields();
         repaint();
     }
  }

  void invalidateOSI(){ this.osiInvalid=true;}

/*
  boolean isInsidePix(int x, int y){
    Wire wire;
    int n=wires.size();
    for(int i=0; i<n; i++){
        wire=(Wire)wires.elementAt(i);
        if(wire.isInsidePix(x,y)){
            return true;
        }
    }
    return false;
  } */

  boolean isInsideDragableThing(int x, int y){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(!t.isNoDrag() &&
             t.isInsideThing(x,y)){
           return true;
         }
    }
    return false;
  }

  boolean hasCoils(){
    Wire wire;
    int n=wires.size();
    for(int i=0; i<n; i++){
        wire=(Wire)wires.elementAt(i);
        if(wire instanceof Coil) return true;
    }
    return false;
  }

  boolean hasWires(){
    Wire wire;
    int n=wires.size();
    for(int i=0; i<n; i++){
        wire=(Wire)wires.elementAt(i);
        if(!(wire instanceof Coil) ) return true;
    }
    return false;
  }


  boolean isInsideAnyWire(double x, double y){
    boolean tempNoOpDrag=false;
    boolean tempNoDrag=false;
    Wire wire;
    int n=wires.size();
    for(int i=0; i<n; i++){
        wire=(Wire)wires.elementAt(i);
        tempNoDrag= wire.isNoDrag();
        tempNoOpDrag= wire.noOptionDrag;
        wire.setNoDrag(false);
        wire.noOptionDrag=false;
        if(!(wire instanceof Coil) && wire.isInsideWire(x,y,0)&& wire!=dragWire){
            wire.setNoDrag(tempNoDrag);
            wire.noOptionDrag=tempNoOpDrag;
            return true;
        } else if((wire instanceof Coil) && (wire.isInsideWire(x,y,-1)|| wire.isInsideWire(x,y,1))&& wire!=dragWire){
            wire.setNoDrag(tempNoDrag);
            wire.noOptionDrag=tempNoOpDrag;
            return true;
        }
        wire.setNoDrag(tempNoDrag);
        wire.noOptionDrag=tempNoOpDrag;
    }
    return false;
  }

  public Thing getThing(int id){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t.hashCode()==id) return t;
    }
    if(trailThing!=null && trailThing.hashCode()==id) return trailThing;
    return null;
  }

  public Wire getWireFromID(int id){
      for (int i=0;i<wires.size();i++){
            Wire w = (Wire)wires.elementAt(i);
            if (w.getID()==id)return w;
      }
      return null;
  }

  final double getCurl(double x1, double y1, Wire w){
     double del=1.0e-8;
     double dbdx=(getBy( x1+del,  y1, w)-getBy( x1-del,  y1, w))/2/del;
     double dbdy=(getBx( x1,  y1+del, w)-getBx( x1,  y1-del, w))/2/del;
     return dbdx-dbdy;
  }

  final double[] getB(double x1, double y1, Wire w){
      double bx=0, by=0;
      double[] temp= new double[2];
      if(parserBx!=null)bx= parserBx.evaluate(x1,y1);
      if(parserBy!=null)by= parserBy.evaluate(x1,y1);
      if (wires==null) return new double[] {bx,by};
      Wire p;
      //double x=0,y=0,c=0;
      int n=wires.size();
      for(int i=0; i<n; i++){
          p=(Wire)wires.elementAt(i);
          if(w!=p){
              temp[0]=x1;
              temp[1]=y1;
              temp=p.getB(temp);
              bx+=temp[0];
              by+=temp[1];
          }
        }
      return new double[] {bx,by};
  }


  final double getBx(double x1, double y1, Wire w){
      double bx=0;
      if(parserBx!=null)bx= parserBx.evaluate(x1,y1);
      if (wires==null) return bx;
      Wire p;
      //double x=0,y=0,c=0;
      int n=wires.size();
      for(int i=0; i<n; i++){
          p=(Wire)wires.elementAt(i);
          if(w!=p){
              bx+=p.getWireBx(x1,y1);
          }
        }
      return bx;
  }

  final double getBy(double x1, double y1, Wire w){
      double by=0;
      if(parserBy!=null)by= parserBy.evaluate(x1,y1);
      if (wires==null) return by;
      Wire p;
      //double x=0,y=0,c=0;
      int n=wires.size();
      for(int i=0; i<n; i++){
            p=(Wire)wires.elementAt(i);
            if(w!=p){
             // x=p.x;
             //y=p.y;
             // c=p.getCurrent();
              //by+= c*(x1-x)/((x-x1)*(x-x1)+(y-y1)*(y-y1));
              by+=p.getWireBy(x1,y1);
            }
        }
      return by;
  }
/*
  public void paintBlank(){
    Graphics g=getGraphics();
    if(g==null) return;
    iwidth = this.getSize().width;
    iheight = this.getSize().height;
    osi= createImage(iwidth,iheight);	 //create an off screen image
    g.setColor(Color.lightGray);
    g.fillRect(0,0,iwidth,iheight);
    g.dispose();
  }*/

  public void paint(Graphics g){
    if (getSize().width==0||getSize().height==0)return;
    if( osi == null || osiInvalid || iwidth != this.getSize().width ||
      iheight != this.getSize().height){
        if(this.getSize().width==0) return;
          else paintOSI();
      }
    if(!autoRefresh) return;
    g.drawImage(osi,0,0,this);
    Font oldFont=g.getFont();
    g.setFont(f); // this font is used for messages and captions.
    //paintWires(g);
    paintMessage(g,message);
    FontMetrics fm=g.getFontMetrics(f);
    if(caption!=null) g.drawString(caption,(iwidth-fm.stringWidth(caption))/2, 25);
    g.setFont(oldFont);
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

  /*
  public void paintWires(Graphics g){
    Wire w;
    int n=wires.size();
    for(int i=0; i<n; i++){
        w=(Wire)wires.elementAt(i);
        if(dragWire!=w){
            w.paint(g);
            if(w.isShowF())paintForceOnWire(g,w);
        }
    }
  }*/

  /*
  public void paintThings_old(Graphics g){
    Thing t;
    int n=things.size();
    for(int i=0; i<n; i++){
        t=(Thing)things.elementAt(i);
        if(dragWire!=t){
            t.paint(g);
            if((t instanceof Wire) && ((Wire)t).isShowF())paintForceOnWire(g,(Wire)t);
        }
    }
  }  */

  public void paintThings(Graphics g){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
        //if(dragWire!=t){
            t.paint(g);
            if((t instanceof Wire) && ((Wire)t).isShowF())paintForceOnWire(g,(Wire)t);
        //}
    }
  }

  public void paintArrowHeads(Graphics g, Rectangle r){
    ArrowHead ah;
    int n=arrowHeads.size();
    for(int i=0; i<n; i++){
        ah=(ArrowHead)arrowHeads.elementAt(i);
        ah.paint(g, r);
    }
  }

  void paintForceOnWire(Graphics g,Wire w){
      double x=w.getX()-w.xo;
      double y=w.getY()-w.yo;
      double c=w.getCurrent();
      double[] bvec=getB(x,y,w);
      double bx=bvec[0];
      double by=bvec[1];

     // double bx=getBx(x,y,w);
     // double by=getBy(x,y,w);

      int x0=pixFromX(x);
      int y0=pixFromY(y);
      int x1=pixFromX(x-c*by);
      int y1=pixFromY(y+c*bx);

      Color oldColor=g.getColor();
      g.setColor(Color.black);
      SUtil.drawArrow(g,x0,y0,x1,y1);
      g.setColor(oldColor);
  }

  void paintCoordinates(Graphics g, double x,double y){
      FontMetrics fm=g.getFontMetrics(g.getFont());
      String msg="";
      if(showCoordOnDrag) msg="x=" + format.form(x)+ " y=" +format.form(y);
      boolean infinite=isInsideAnyWire(x,y);
      if(dragWire!=null  && dragWire.isShowF() ){
          double[] bvec=getB(x,y,dragWire);
          double fx=bvec[0];
          double fy=bvec[1];
          //double fx=getBx(x,y,dragWire);
          //double fy=getBy(x,y,dragWire);
          double f=Math.abs(dragWire.getCurrent())*Math.sqrt(fx*fx+fy*fy);
          if (infinite)  msg=msg+" "+owner.label_force_undefined;
          else msg=msg+" |F|="+format.form(f)+"N/m ";
      }else if(showBOnDrag){
          double[] bvec=getB(x,y,null);
          double bx=bvec[0];
          double by=bvec[1];
          //double bx=getBx(x,y,null);
          //double by=getBy(x,y,null);
          if ( (dragWire!=null && !(dragWire instanceof Coil)) || infinite)  msg=msg+" "+owner.label_field_undefined;
              else msg=msg+" |B|="+format.form(Math.sqrt(bx*bx+by*by));
      }
      g.setColor(Color.yellow);
      g.fillRect(0,getBounds().height-15,boxWidth,15);
      boxWidth=20+fm.stringWidth(msg);
      g.fillRect(0,getBounds().height-15,boxWidth,15);
      g.setColor(Color.black);
      g.drawString(msg, 8, getBounds().height-2);
  }

  public void paintOSI(){
      owner.lock.getBusyFlag();
      if( osi == null || iwidth != getSize().width ||iheight != getSize().height){
          iwidth = this.getSize().width;
          iheight = this.getSize().height;
          if(iwidth<=0 || iheight<=0){
        	  owner.lock.freeBusyFlag();
        	  return;
          }
          Image osi_temp = createImage(iwidth,iheight);	 //create an off screen image
          osi=osi_temp;  // replace the current image
          setXRange(xmin,xmax);
          setFields();
         // System.out.println("I Created 1");
      }
      if(osi==null){
        owner.lock.freeBusyFlag();
        return;
      }
      Graphics osg = osi.getGraphics();// a graphics context for the  off screen image
      if(osg==null){
        owner.lock.freeBusyFlag();
        return;
      }
      osg.setColor(Color.white);
      osg.fillRect(0,0,iwidth,iheight);
      osiInvalid=false;
      if(graph!=null){
          Rectangle r = getBounds();
        /* The r.x and r.y returned from bounds is relative to the
        ** parents space so set them equal to zero.
        */
          r.x = 0;
          r.y = 0;
          graph.paint(osg,r);

          r.width=pixFromX(xFromPix(iwidth))-pixFromX(xFromPix(0));
          r.height=pixFromY(yFromPix(iheight))-pixFromY(yFromPix(0));
          fieldBounds=r;
          paintArrowHeads(osg,r);
          if(showWires)paintThings(osg);
          if(showFieldVectors && fieldThing==null) field.paint(osg,r);
      }else{
          osg.setColor(getBackground());
          osg.fillRect(0,0,iwidth,iheight);
          osg.setColor(osg.getColor());
          //osg.clipRect(0,0,iwidth,iheight);
         // paintGrid(osg);
      }

      osg.setColor(Color.black);
      osg.drawRect(0,0,iwidth-1,iheight-1);
      osg.dispose();
      owner.lock.freeBusyFlag();
      return;
    }

  public double xFromPix(int pix){
    if(graph!=null) return graph.xFromPix(pix);
    else return pix;
  }
  public int pixFromX(double x){
    if(graph!=null) return graph.pixFromX(x);
    else return (int)Math.round(x);
  }

  public double yFromPix(int pix){
    if(graph!=null) return graph.yFromPix(pix);
    else return pix;
  }
  public int pixFromY(double y){
    if(graph!=null) return graph.pixFromY(y);
    else return (int)Math.round(y);
  }

  void wire_Dragged(MouseEvent e, boolean drawVectors) {
    int xpt=e.getX();
    if(xpt<1) xpt=1;
      else if(xpt>iwidth-2) xpt=iwidth-2;
    int ypt=e.getY();
    if(ypt<1) ypt=1;
      else if(ypt>iheight-2) ypt=iheight-2;
    double x=xFromPix(xpt);
    double y=yFromPix(ypt);
    if(isDrag){
       Graphics g=getGraphics();
       // active drawing
       if(dragWire!=null){
           owner.lock.getBusyFlag();
           if(osi==null){
                osi=createImage(iwidth,iheight);
               // System.out.println("I Created 2");
           }
           osiInvalid=false;
           Graphics osg = osi.getGraphics();
           //Rectangle r= new Rectangle(iwidth,iheight);

           Rectangle r = getBounds();
           /* The r.x and r.y returned from bounds is relative to the
            ** parents space so set them equal to zero.
           */
           r.x = 0;
           r.y = 0;

           osg.setColor(Color.white);
           osg.fillRect(0,0,iwidth,iheight);
           if(hotSpot==0){
               //dragWire.x=x;
               //dragWire.y=y;
               dragWire.setXY(x,y);
               dragWire.updateMySlaves();
           }else if(hotSpot==1){
               dragWire.radius=Math.abs(y-dragWire.getY());
               if(y<dragWire.getY()){hotSpot=-1; dragWire.current= -dragWire.current;}
           }else if(hotSpot==-1){
               dragWire.radius=Math.abs(y-dragWire.getY());
               if(y>dragWire.getY()){hotSpot=1; dragWire.current= -dragWire.current;}
           }
           if(drawVectors || (fieldThing!=null && fieldThing.isVisible()) ){
               setDirectionVectors();
               r.width=pixFromX(xFromPix(iwidth))-pixFromX(xFromPix(0));
               r.height=pixFromY(yFromPix(iheight))-pixFromY(yFromPix(0));
           }
           if(showWires)paintThings(osg);
           if(drawVectors && fieldThing==null){
               field.paint(osg,r);
           }
           //dragWire.paint(osg);
           dragWire.paintInfo(osg,hotSpot);
           if(dragWire.isShowF())paintForceOnWire(osg,dragWire);
           osg.dispose();
           g.drawImage(osi,0,0,this);
           owner.lock.freeBusyFlag();
           if(owner!=null) owner.updateDataConnections();
       }
       g.setPaintMode();
       paintCoordinates(g,x,y);
       g.setColor(Color.black);
       g.drawRect(0,0,iwidth-1,iheight-1);
       g.dispose();
    }
  }

  void wire_Moved(double x, double y) {  // a wire has moved so we need to repaint the fields.  Coordinates have already been updated.
       Graphics g=getGraphics();
       // active drawing
       owner.lock.getBusyFlag();
       if(osi==null){
                osi=createImage(iwidth,iheight);
               // System.out.println("I Created 2");
       }
       osiInvalid=false;
       Graphics osg = osi.getGraphics();
           //Rectangle r= new Rectangle(iwidth,iheight);

       Rectangle r = getBounds();
           /* The r.x and r.y returned from bounds is relative to the
            ** parents space so set them equal to zero.
           */
       r.x = 0;
       r.y = 0;

       osg.setColor(Color.white);
       osg.fillRect(0,0,iwidth,iheight);
       setDirectionVectors();
       r.width=pixFromX(xFromPix(iwidth))-pixFromX(xFromPix(0));
       r.height=pixFromY(yFromPix(iheight))-pixFromY(yFromPix(0));
       if(showWires)paintThings(osg);
       if(fieldThing==null){
               field.paint(osg,r);
       }
       osg.dispose();
       g.drawImage(osi,0,0,this);
       owner.lock.freeBusyFlag();
       g.setPaintMode();
       g.setColor(Color.black);
       g.drawRect(0,0,iwidth-1,iheight-1);
       paintCoordinates(g,x,y);
       g.dispose();
  }

  void setShowFieldVectors(boolean sfv){
      showFieldVectors=sfv;
      osiInvalid=true;
      if(autoRefresh) repaint();
  }

  void  setShowFieldLineOnClick(boolean sfl){
      showFieldLineOnClick=sfl;
      showFieldLineOnDoubleClick=false;
  }
  void  setShowFieldLineOnDoubleClick(boolean sfl){
      showFieldLineOnDoubleClick=sfl;
      showFieldLineOnClick=false;
  }

  boolean setLabel(int id, String str){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    w.setLabel(str);
    osiInvalid=true;
    if(autoRefresh) repaint();
    return true;
  }

  public void setAutoRefresh(boolean ar){
      autoRefresh=ar;
      /*
      if(autoRefresh){
          setFields();
          paintOSI();
          repaint();
      }*/
  }

  boolean setColor(int id, Color c){
    Thing t=getThing(id);
    if (t==null) return false;
    t.setColor(c);
    osiInvalid=true;
    if(autoRefresh) repaint();
    return true;
  }

  public boolean setCurrent(int id, double c){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    w.setCurrent(c);
    if(autoRefresh) setFields();
    osiInvalid=true;
    if(autoRefresh) repaint();
    return true;
  }

  public boolean setRadius(int id, double r){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    w.setRadius(r);
    if(autoRefresh) setFields();
    osiInvalid=true;
    if(autoRefresh) repaint();
    return true;
  }

  /*
  boolean setDrag(int id, boolean isDrag){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    //w.noDrag=!isDrag;
    w.setDragable(isDrag);
    return true;
  } */

  /**
   * Make the object with the given id dragable.
   *
   * @param              id The id of the object.
   * @param              drag Dragable?
   * @return             true if successful.
   */
  public boolean setDragable(int id, boolean drag){
      Thing t=getThing(id);
      if(t==null) return false;
      t.setDragable(drag);
      return true;
  }

  boolean setOptionDrag(int id, boolean isDrag){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    w.noOptionDrag=!isDrag;
    return true;
  }

  /*
  boolean setHide(int id, boolean hide){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    //w.hideWire=hide;
    w.setVisibility(!hide);
    osiInvalid=true;
    if(autoRefresh) repaint();
    return true;
  } */

  boolean setVisibility(int id, boolean v){
    //Thing t=getWireFromID(id);
    Thing t=getThing(id);
    if (t==null) return false;
    t.setVisible(v);
    osiInvalid=true;
    if(autoRefresh) repaint();
    return true;
  }

  boolean setShowForce(int id, boolean showForce){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    w.setShowF(showForce);
    return true;
  }

  boolean setShowFComponents(int id, boolean sfc){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    w.setShowFComponents(sfc);
    return true;
  }

  boolean setShowInfo(int id, boolean si){
    Wire w=getWireFromID(id);
    if (w==null) return false;
    w.showInfo=si;
    return true;
  }

  void  setShowCoordOnDrag(boolean sc){
      showCoordOnDrag=sc;
  }

  void  setShowBOnDrag(boolean sb){
      showBOnDrag=sb;
  }

  void setDefault(){
      owner.lock.getBusyFlag();
      stopFieldThreads();
      tolerance=1.0e-5;
      message=null;
      showFieldLineOnClick=false;
      showFieldLineOnDoubleClick=false;
      //showTime=true;
      showCoordOnDrag=true;
      showBOnDrag=false;
      setXRange(xmin,xmax);
      message=null;
      fieldThing=null;
      wires.removeAllElements();
      things.removeAllElements();
      arrowHeads.removeAllElements();
      owner.lock.freeBusyFlag();
      if(autoRefresh) setFields();
      osiInvalid=true;
      if(autoRefresh)repaint();
  }

  void reset(){
      owner.lock.getBusyFlag();
      stopFieldThreads();
      owner.lock.freeBusyFlag();
      if(autoRefresh) setFields();
      osiInvalid=true;
      if(autoRefresh)repaint();
  }

    /**
   * Enable sketching with the mouse.
   *
   * @param              sketch true will sketch
   *
   * @return  int the id of the mouse data source
   */

  public int setSketchMode(boolean sm){
      //Applet applet=edu.davidson.graphics.Util.getApplet(this);
      sketchImage=edu.davidson.graphics.Util.getImage("pencil.gif",owner);
      //int xoff=0;
      //int yoff=29;
      sketchMode=sm;
      if(!sm){
          trailThing=null;
          return 0;
      }
      trailThing=new SketchThing(owner, this,1);
      trailThing.setTrailSize(2000);
      /*
      // the following will not work on IE or NN 4.7  It works on Java 1.2.
      Toolkit tk=Toolkit.getDefaultToolkit();
      int maxColors=tk.getMaximumCursorColors();
      if(maxColors==0 || sketchImage ==null){
         System.out.println("Custom cursors not supported or cursor image not found.");
         sketchCursor=null;
         return trailThing.hashCode();
      }
      sketchCursor=tk.createCustomCursor(sketchImage ,new Point(xoff,yoff),"sketch cursor");
      setCursor(sketchCursor);
      */
      return trailThing.hashCode();
  }

  void setGridSize(int gs){
      gridSize=gs;
      if(autoRefresh) setFields();
      osiInvalid=true;
      if(autoRefresh) repaint();
  }

  void setDirectionVectors(){
    double x,y;
    int nx=gridSize;
    int ny=gridSize;
    double[][][] force=field.resize(ny,nx);

    // shadow the scale values to sync the images.
   double xmin=xFromPix(0);
   double xmax=xFromPix(iwidth);
   double ymin=yFromPix(iheight);
   double ymax=yFromPix(0);

    for(int j=0; j<nx; j++) {
          x = (xmax-xmin)*(double)j/(double)(nx-1) +xmin;
          for(int i=0; i<ny; i++) {
                y = (ymax-ymin)*(double)i/(double)(ny-1) +ymin;
                double[] bvec=getB(x,y,null);
                double bx=bvec[0];
                double by=bvec[1];
                //double bx=getBx(x,y,null);
               // double by=getBy(x,y,null);
                double b=Math.sqrt(bx*bx+by*by);
                if(b>0){
                    force[i][j][0]=bx/b;
                    force[i][j][1]=by/b;
                    force[i][j][2]=b;
                }else{
                    force[i][j][0]=0;
                    force[i][j][1]=0;
                    force[i][j][2]=0;
                }
          }
      }
  }

  public final boolean setFormat(String str){
     try{format= new Format(str);}
     catch (IllegalArgumentException e){return false;}
     return true;
  }

  /**
 * Swap the drawing order on the screen.
 *
 *  @param id1   The first id of a screen object.
 *  @param id2   The second id of a screen object.
 *
 *  @returns true if successful.
*/
  public synchronized boolean swapZOrder(int id1, int id2){
     Thing t1=getThing(id1);
     Thing t2=getThing(id2);
     if(t1==null || t2==null) return false;
     int index1=things.indexOf(t1);
     int index2=things.indexOf(t2);
     things.removeElementAt(index1);
     things.insertElementAt(t2,index1);
     things.removeElementAt(index2);
     things.insertElementAt(t1,index2);
     if(autoRefresh)repaint();
     return true;
  }


  void stopFieldThreads(){
   //   System.out.println("Stop FieldThreads");
      Vector v;
      synchronized(fieldSolvers){
          v=(Vector)fieldSolvers.clone();
      }
      FieldSolver fs;
      int n=v.size();
      for(int i=0; i<n; i++){
            fs=(FieldSolver)v.elementAt(i);
            //if(fs.fieldThread.isAlive()) fs.fieldThread.stop();
            //fs.keepRunning=false;
            //fieldSolvers.removeElement(fs);
            fs.interrupted=true;
        }
      arrowHeads.removeAllElements();
      v=null;
  }

  void setFields(){
     owner.lock.getBusyFlag();
     stopFieldThreads();
     int nx=gridSize;
     int ny=gridSize;
     graph.deleteAllSeries(); // remove any field line that may have been drawn.
     arrowHeads.removeAllElements();
    int i,j;
    double x,y;

    // shadow the scale values so that the image has the correct values.
    double xmin=xFromPix(0);
    double xmax=xFromPix(iwidth);
    double ymin=yFromPix(iheight);
    double ymax=yFromPix(0);

    double[][][] force=field.resize(ny,nx);
    for(j=0; j<nx; j++) {
          x = (xmax-xmin)*(double)j/(double)(nx-1) +xmin;
          for(i=0; i<ny; i++) {
                y = (ymax-ymin)*(double)i/(double)(ny-1) +ymin;
                double[] bvec=getB(x,y,null);
                double fx=bvec[0];
                double fy=bvec[1];
                //double fx=getBx(x,y,null);
                //double fy=getBy(x,y,null);
                double mag=Math.sqrt(fx*fx+fy*fy);
                if(mag>0){
                    force[i][j][0]=fx/mag;
                    force[i][j][1]=fy/mag;
                    force[i][j][2]=mag;
                }else{
                    force[i][j][0]=0;
                    force[i][j][1]=0;
                    force[i][j][2]=0;
                }
          }
      }

    osiInvalid=true;  // this will force a repaint;
    owner.lock.freeBusyFlag();
  }

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
      graph.setMinMaxX(xmin,xmax);
      graph.setMinMaxY(ymin,ymax);
     // repaint();
  }


  public void update(Graphics g){paint(g);}

  //mouse stuff

  void this_mouseMoved(MouseEvent e) {
    if(isInsideDragableThing((int)e.getX(),(int)e.getY()) ) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      //if(this.sketchMode)setCursor(this.sketchCursor);
      else if( this.sketchMode )setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }


  void this_mouseReleased( MouseEvent e){
    boxWidth=0;  // cleanup the yellow message box.
    int xpt=e.getX();
    if(xpt<1) xpt=1;
      else if(xpt>iwidth-2) xpt=iwidth-2;
    int ypt=e.getY();
    if(ypt<1) ypt=1;
      else if(ypt>iheight-2) ypt=iheight-2;
    double x=xFromPix(xpt);
    double y=yFromPix(ypt);
    if(dragWire!=null){
        if(hotSpot==0){
            //dragWire.x=x;
            //dragWire.y=y;
            dragWire.setXY(x,y);
            dragWire.updateMySlaves();
        }else if(hotSpot==1){
           dragWire.radius=Math.abs(y-dragWire.getY());
           if(y<dragWire.getY()){hotSpot=-1; dragWire.current= -dragWire.current;}
        }else if(hotSpot==-1){
           dragWire.radius=Math.abs(y-dragWire.getY());
           if(y>dragWire.getY()){hotSpot=1; dragWire.current= -dragWire.current;}
        }
        setFields();
        if(owner!=null) owner.updateDataConnections();
    }else if(dragThing!=null){
        if(this.containsWires(dragThing)){ setFields(); }
        dragThing.setXY(x,y);
        dragThing.updateMySlaves();
        osiInvalid=true;
        if(owner!=null) owner.updateDataConnections();
    }
    // clean up the variables
    isDrag=false;
    dragWire=null;
    dragThing=null;
    hotSpot=0;
    if(sketchMode && trailThing!=null){
        DataSet dataset=trailThing.getDataSet();
        graph.attachDataSet( dataset);
        graph.xaxis.attachDataSet(dataset);
        graph.yaxis.attachDataSet(dataset);
        osiInvalid=true;
    }
    this_mouseMoved( e);  // reset the cursor
    repaint();
  }
  
  /*
  void this_mouseClicked(MouseEvent e) {
	    int xpt=e.getX();
	    int ypt=e.getY();
	    double x=xFromPix(xpt);
	    double y=yFromPix(ypt);
	    if(showFieldLineOnClick ||
	       (e.getClickCount()==2  && showFieldLineOnDoubleClick ) ) {
	        new FieldSolver(x,y, true);
	        //if(parserBx!=null || parserBy!=null)new FieldSolver(x,y, false);
	        return;
	    }
  }*/

  void this_mousePressed(MouseEvent e) {
    int xpt=e.getX();
    int ypt=e.getY();
    double x=xFromPix(xpt);
    double y=yFromPix(ypt);
    
    // could also use mouseClicked event handler
    if(showFieldLineOnClick ||
       (e.getClickCount()==2  && showFieldLineOnDoubleClick ) ) {
        new FieldSolver(x,y, true);
        new FieldSolver(x,y, false);
        //if(parserBx!=null || parserBy!=null)new FieldSolver(x,y, false);
        return;
    }
    
    //Wire wire;
    isDrag=true;
    dragWire=null;
    dragThing=null;

    for( Enumeration enumThing=things.elements(); enumThing.hasMoreElements();){
         Thing t= (Thing) enumThing.nextElement();
         if(!t.isNoDrag() && t.isInsideThing(xpt,ypt)){
           dragThing=t;
         }
    }
    if(dragThing instanceof Wire) dragWire=(Wire)dragThing;
    if(dragWire!=null){
        // paint the shape for the first time.
        hotSpot=dragWire.getHotSpot(xpt,ypt);
        if(hotSpot==0){
               dragWire.setXY(x,y);
               dragWire.updateMySlaves();
        }else if(hotSpot==1){
           dragWire.radius=Math.abs(y-dragWire.getY());
           if(y<dragWire.getY()){hotSpot=-1; dragWire.current= -dragWire.current;}
        }else if(hotSpot==-1){
           dragWire.radius=Math.abs(y-dragWire.getY());
           if(y>dragWire.getY()){hotSpot=1; dragWire.current= -dragWire.current;}
        }
        if(owner!=null) owner.updateDataConnections();
    }else if(dragThing!=null){  // we are dragging a geometric shape, not a wire

         dragThing.setXY(x,y);
         dragThing.updateMySlaves();
         if(owner!=null) owner.updateDataConnections();
    }
    paintOSI();
    Graphics g=getGraphics();
    paint(g);
    paintCoordinates(g,x,y);
    g.dispose();

    if(sketchMode && trailThing!=null ){
              trailThing.clearTrail();
              owner.clearData(trailThing.hashCode());
              setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
              this_mouseDragged(e);
    }

  }

  void this_mouseDragged(MouseEvent e) {
    if(dragWire!=null){
        wire_Dragged(e,showFieldVectors);
        return;
    }
    int xpt=e.getX();
    if(xpt<1) xpt=1;
      else if(xpt>iwidth-2) xpt=iwidth-2;
    int ypt=e.getY();
    if(ypt<1) ypt=1;
      else if(ypt>iheight-2) ypt=iheight-2;
    double x=xFromPix(xpt);
    double y=yFromPix(ypt);
    Graphics g=null;
    if(isDrag && !sketchMode){
       if(dragThing!=null){
         dragThing.setXY(x,y);
         dragThing.updateMySlaves();
         owner.updateDataConnection(dragThing.hashCode());
       }
       if(dragThing!=null && containsWires( dragThing) ) {
         this.wire_Moved(x,y);
         return;
       }
       paintOSI();
       g=getGraphics();
       paint(g);
       paintCoordinates(g,x,y);
       g.dispose();
    }
    if(sketchMode && trailThing!=null){
          g=getGraphics();
        //x=Math.min(x,contour.xaxis.maximum);
        //x=Math.max(x,contour.xaxis.minimum);
        //y=Math.min(y,contour.yaxis.maximum);
        //y=Math.max(y,contour.yaxis.minimum);
          trailThing.incTrail(x,y);
          g=getGraphics();
          paint(g);
          trailThing.paint(g);
          paintCoordinates(g,x,y);
          if(sketchImage!=null)g.drawImage(sketchImage,xpt,ypt-sketchImage.getHeight(this),this);
          g.dispose();
          owner.updateDataConnection(trailThing.hashCode());
    }
  }

  private boolean containsWires( Thing thing){
     if (thing==null) return false;
     Vector slaves=thing.getSlaves();
     if (slaves==null) return false;
     Thing slave=null;
     for( Enumeration e=slaves.elements(); e.hasMoreElements();){
         slave= (Thing) e.nextElement();
         if(slave instanceof Wire) return true;
     }
     return false;
  }


  // methods for external B field

  boolean setRange(String rs){
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

  private boolean parseBx(String s){
     bxStr=s.trim();
     if (bxStr.equals("") || bxStr.equals("0") ){
         parserBx=null;
         return true;
     }
     parserBx = new Parser(2);
     parserBx.defineVariable(1,"x"); // define the variable
     parserBx.defineVariable(2,"y"); // define the variable
     parserBx.define(bxStr);
     parserBx.parse();
     if(parserBx.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse Bx(x,y): "+bxStr);
         System.out.println("Parse error: " + parserBx.getErrorString() +
                   " at function 1, position " + parserBx.getErrorPosition());
         return false;
     }else return true;
  }

  private boolean parseBy(String s){
     byStr=s.trim();
     if (byStr.equals("") || byStr.equals("0") ){
         parserBy=null;
         return true;
     }
     parserBy = new Parser(2);
     parserBy.defineVariable(1,"x"); // define the variable
     parserBy.defineVariable(2,"y"); // define the variable
     parserBy.define(byStr);
     parserBy.parse();
     if(parserBy.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse By(x,y): "+byStr);
         System.out.println("Parse error: " + parserBy.getErrorString() +
                   " at function 1, position " + parserBy.getErrorPosition());
         return false;
     }else return true;
  }

  boolean setBFunctions(String bx, String by){
     owner.lock.getBusyFlag();
     if(parseBx(bx) && parseBy(by)){
        if(autoRefresh){
             setFields();
             repaint();
        }
        owner.lock.freeBusyFlag();
        return true;
     }
     owner.lock.freeBusyFlag();
     return false;
  }

  public boolean setBFunctions(String bx, String by, double x1,double x2,double y1,double y2){
      xmin = x1;
      xmax = x2;
      ymin = y1;
      ymax = y2;
      return setBFunctions(bx,by);
  }

  // FieldSolver Inner class

    public class FieldSolver implements Runnable, SDifferentiable{   // inner class to solve and plot the Electric field.
      Color fieldColor=Color.black; //new Color(128,128,255);
      SRK45      odeSolver=new SRK45();
      //Thread     fieldThread = null;
  	  private Timer bfieldTimer;
      double[]   fieldLine = new double[2];
      boolean    plus=true;
      boolean    keepRunning=true;
      boolean    outOfBounds=false;
      boolean    interrupted=false;
      DataSet    data;
      int        np=0;
      int        maxPts=400;  // increased max from 200 to 400 and lowered step size
      double[]   points=new double[2*maxPts];  // data points
      int        scale=1;
      private double[] dydx=new double[2];
      private double    res=0;

      FieldSolver(double x, double y, boolean p ){
         this.plus=p;
         fieldLine[0]=x;
         fieldLine[1]=y;
         points[np]=x;
         points[np+1]=y;
         np=np+2;
         odeSolver.setDifferentials(this);
         odeSolver.setTol(tolerance);
         /*
         if (fieldThread == null){
             fieldThread = new Thread( this);
             fieldThread.start();
         }*/
         if (bfieldTimer == null){
				bfieldTimer = new Timer(50, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						runTimer();
					}
					
				});
				bfieldTimer.setRepeats(true);
				bfieldTimer.start();
         }
         fieldSolvers.addElement(this);
         res=graph.xFromPix(3)-graph.xFromPix(0);
      }
      public int getNumEqu(){return 2;}

      public double[] rate(double[] x){
          double[] bvec=getB(x[0],x[1],null);
          double fx=bvec[0];
          double fy=bvec[1];
          //double fx=getBx(x[0],x[1],null);
          //double fy=getBy(x[0],x[1],null);
          double f=Math.sqrt(fx*fx+fy*fy);
          if(!plus) {fy=-fy; fx=-fx;}
          if(f<=0){
              dydx[0]=0;
              dydx[1]=0;
              keepRunning=false;  // no field so we might as well stop.
          }else{
             dydx[0]=scale*fx/f;
             dydx[1]=scale*fy/f;
            //  dydx[0]=fx/f;
            //  dydx[1]=fy/f;
          }
          return dydx;
        }

      boolean stepField(){
      // return true if the field line should keep running.
      /*
          if(osi==null){
              System.out.println("OSI=null");
              keepRunning=false;
              return;
          }
      */
          //double ds=(xmax-xmin)/25.0;  //step size
          double ds=(xmax-xmin)/50.0;  // try smaller step size
      //    int x0=pixFromX(fieldLine[0]);
      //   int y0=pixFromY(fieldLine[1]);

         // odeSolver.step(ds, fieldLine);
          odeSolver.setH(ds);
          odeSolver.stepRK45(fieldLine);
          int x1=pixFromX(fieldLine[0]);
          int y1=pixFromY(fieldLine[1]);
          if(np<maxPts*2){
              points[np]=fieldLine[0];
              points[np+1]=fieldLine[1];
              np=np+2;
          }

         // speed it up if we are outside the panel.
         //scale=1+(int)Math.round(Math.abs(x1*1.0/iwidth)) + (int)Math.round(Math.abs(y1*1.0/iheight));
         // scale=1;
        // if( (Math.abs(iwidth/2-x1)>(iwidth/1.8))|| (Math.abs(iheight/2-y1)>(iheight/1.8)))scale=20; else scale=1;
          /**
          Graphics g=getGraphics();
          g.setColor(fieldColor);
          g.drawLine(x0,y0,x1,y1);
          g.setColor(Color.black);
          g.dispose();

          if(osi!=null){
              g=osi.getGraphics();
              g.setColor(fieldColor);
              g.drawLine(x0,y0,x1,y1);
              g.setColor(Color.black);
              g.dispose();
          }
          **/
          if( ( x1<-30 || y1<-30 || x1>iwidth+30 || y1>iheight+30) )  {
              outOfBounds=true;
              return false;   // stop at the edges
          }
          if(np>4 && insideBox(fieldLine[0],fieldLine[1],points[0], points[1],points[2],points[3])  ){
              //System.out.println("end="+np);
              outOfBounds=false;
              return false;
          }
          return true;
      }

      boolean insideBox(double x, double y, double x0, double y0, double x1, double y1){
          double temp;
          if(x0>x1){
              temp=x1;
              x1=x0;
              x0=temp;
          }
          if(y0>y1){
              temp=y1;
              y1=y0;
              y0=temp;
          }
          if( x>=x0-res && x<=x1+res && y>=y0-res && y<=y1+res) return true;
          else return false;
      }
      
      public void runTimer(){

          int count=0;
          keepRunning=true;
          while (keepRunning && !interrupted  ){
            try{
              //while(osi==null){ Thread.sleep(50);}
              if(!interrupted) keepRunning=stepField();
              //if(!interrupted) Thread.sleep(20);
              count++;
              if(count>=maxPts){
                  keepRunning=false;
                  outOfBounds=true;
              } //xxx
            }catch (Exception e){}
          }
         fieldSolvers.removeElement(this);
         /*
         if(interrupted){   // don't draw if this thread is interrupted.
             fieldThread.stop(); // this fixes a bug in the Java VM
             fieldThread=null;
             return;
         }*/
         if(interrupted){   // don't draw if this thread is interrupted.
        	 bfieldTimer.stop(); // this fixes a bug in the Java VM
             bfieldTimer=null;
             return;
         }
         owner.lock.getBusyFlag();
         data = graph.addDataSet(points,count);   // this constructs dataset
         data.linecolor = fieldColor;
         owner.lock.freeBusyFlag();
        int index=0;
        double x=points[index*2];
        double y=points[index*2+1];
        double[] bvec=getB(x,y,null);
        double bx=bvec[0];
        double by=bvec[1];
        //double bx=getBx(x,y,null);
        //double by=getBy(x,y,null);
        double b=Math.sqrt(bx*bx+by*by);
        arrowHeads.addElement(new ArrowHead(FieldPanel.this,x,y,bx/b,by/b,fieldColor));

        index=count/2;
        if(index>20){
            x=points[index*2];
            y=points[index*2+1];
            if(x>xmin && x<xmax && y>ymin && y<ymax){
                bvec=getB(x,y,null);
                bx=bvec[0];
                by=bvec[1];
                //bx=getBx(x,y,null);
                //by=getBy(x,y,null);
                b=Math.sqrt(bx*bx+by*by);
                arrowHeads.addElement(new ArrowHead(FieldPanel.this,x,y,bx/b,by/b,fieldColor));
            }
        }

         //System.out.println("registered:"+ count+ "size:"+fieldSolvers.size());
         osiInvalid=true;
         repaint();
         if(plus && outOfBounds && points!=null) new FieldSolver(points[0],points[1], false);
        // if(fieldSolvers.size()==0) repaint();
         points=null;  // help the system to garbage collect
         /*
         fieldThread.stop(); // this fixes a bug in the Java VM
         fieldThread=null;
         */
    	 bfieldTimer.stop(); // this fixes a bug in the Java VM
         bfieldTimer=null;
         interrupted=true;
         
      
      }

      public void run(){
          int count=0;
          keepRunning=true;
          while (keepRunning && !interrupted  ){
            try{
              while(osi==null){ Thread.sleep(50);}
              if(!interrupted) keepRunning=stepField();
              if(!interrupted) Thread.sleep(20);
              count++;
              if(count>=maxPts){
                  keepRunning=false;
                  outOfBounds=true;
              }
            }catch (InterruptedException e){}
          }
         fieldSolvers.removeElement(this);
         if(interrupted){   // don't draw if this thread is interrupted.
        	 /*
             fieldThread.stop(); // this fixes a bug in the Java VM
             fieldThread=null;
             */
        	 bfieldTimer.stop(); // this fixes a bug in the Java VM
             bfieldTimer=null;
             return;
         }
         owner.lock.getBusyFlag();
         data = graph.addDataSet(points,count);   // this constructs dataset
         data.linecolor = fieldColor;
         owner.lock.freeBusyFlag();
        int index=0;
        double x=points[index*2];
        double y=points[index*2+1];
        double[] bvec=getB(x,y,null);
        double bx=bvec[0];
        double by=bvec[1];
        //double bx=getBx(x,y,null);
        //double by=getBy(x,y,null);
        double b=Math.sqrt(bx*bx+by*by);
        arrowHeads.addElement(new ArrowHead(FieldPanel.this,x,y,bx/b,by/b,fieldColor));

        index=count/2;
        if(index>20){
            x=points[index*2];
            y=points[index*2+1];
            if(x>xmin && x<xmax && y>ymin && y<ymax){
                bvec=getB(x,y,null);
                bx=bvec[0];
                by=bvec[1];
                //bx=getBx(x,y,null);
                //by=getBy(x,y,null);
                b=Math.sqrt(bx*bx+by*by);
                arrowHeads.addElement(new ArrowHead(FieldPanel.this,x,y,bx/b,by/b,fieldColor));
            }
        }

         //System.out.println("registered:"+ count+ "size:"+fieldSolvers.size());
         osiInvalid=true;
         repaint();
         if(plus && outOfBounds && points!=null) new FieldSolver(points[0],points[1], false);
        // if(fieldSolvers.size()==0) repaint();
         points=null;  // help the system to garbage collect
         /*
         fieldThread.stop(); // this fixes a bug in the Java VM
         fieldThread=null;
         */
    	 bfieldTimer.stop(); // this fixes a bug in the Java VM
         bfieldTimer=null;
         interrupted=true;
      }

  }

}

class FieldPanel_mouseAdapter extends java.awt.event.MouseAdapter {
  FieldPanel adaptee;

  FieldPanel_mouseAdapter(FieldPanel adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.this_mouseReleased(e);
  }
  
  /*
  public void mouseClicked(MouseEvent e) {
	  //System.out.println("mopuse clicked");
	  adaptee.this_mouseClicked(e);
  }*/

  public void mousePressed(MouseEvent e) {
	  //System.out.println("mouse pressed");
    adaptee.this_mousePressed(e);
  }
}

class FieldPanel_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  FieldPanel adaptee;

  FieldPanel_mouseMotionAdapter(FieldPanel adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseDragged(MouseEvent e) {
    adaptee.this_mouseDragged(e);
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.this_mouseMoved(e);
  }

}


