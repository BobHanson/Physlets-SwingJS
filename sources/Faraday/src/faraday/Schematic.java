package faraday;

//import java.awt.*;
//import java.applet.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.Enumeration;
import edu.davidson.numerics.*;
import edu.davidson.display.*;

import a2s.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


public class Schematic extends Panel implements SScalable{
  private Font f=new Font("Helvetica",Font.BOLD,12);
  private String message=null;
  private String[] varStrings= new String[]{"t","x","v","f"};
  //private double[][] variables=new double[1][4];  // the datasource state variables t,x,voltage,flux;
  private Parser posFunc = new Parser(1);
  private Parser checkFunc = new Parser(1);
  private Parser fieldFunc = new Parser(2);
  private boolean explicitTime=true;
  private double zmin=-2, zmax=2;
  private boolean autoscalez=false;
  private boolean   backgroundInvalid=true;
  private boolean    osiInvalid=true;
  private double[]  fieldValues=null;
  private boolean   showCurrentArrow=true;
  private boolean   showMeter=true;
  boolean showTime=true;
  boolean coordDisplay=true;
  Thing dragThing=null;
  int iwidth=0, iheight=0;
  Format    format= new Format("%-+6.2f");
  Image     backgroundOSI = null;
  Image     osi = null;
  boolean   fillApplet=false;
  int       pixPerUnit=10;
  boolean   showSchematic=true;
  boolean   showGrid=true;
  boolean   showColor=true;
  boolean   showBOnDrag=false;
  private int xOffset=0, yOffset=0;
  private Vector    things = new Vector();
  //private MouseMotionAdapter mouseMotionAdapter;
  //private MouseAdapter mouseAdapter;
  private int     boxWidth=0;
  double  metermin=-200;
  double metermax=200;
  double time=0;

  //private double sliderX=0;
  boolean dragMode=true;
  private int preferredWidth=200;
  private int mouseX=0, mouseY=0;
  private boolean mouseDown=false;
  Faraday owner=null;
  private Thing defaultCircuit=null;

  public Schematic(Faraday app) {
      owner=app;
      addMouseMotionListener(new Schematic_mouseMotionAdapter(this));
      addMouseListener(new Schematic_mouseAdapter(this));
      setBackground(new Color(224,224,224));
      //try{app.addDataSource(this);}catch (Exception e){e.printStackTrace();}
  }


  public void setShowCurrentArrow(boolean show){
     showCurrentArrow=show;
     if( (defaultCircuit!=null) && (defaultCircuit instanceof Fluxable) )
         ((Fluxable)defaultCircuit).setShowCurrentArrow(show);
  }

  public void setShowCurrentArrow(int id, boolean show){
     Thing t=this.getThing(id);
     if(t!=null && t instanceof Fluxable)
         ((Fluxable)t).setShowCurrentArrow(show);
  }

  public void setShowMeter(boolean show){
     showMeter=show;
     if(defaultCircuit!=null && defaultCircuit instanceof UWire)
         ((UWire)defaultCircuit).setShowMeter(show);
  }

  public void setShowMeter(int id, boolean show){
     Thing t=this.getThing(id);
     if(t!=null && t instanceof UWire)
         ((UWire)t).setShowMeter(show);
  }


  int addThing(Thing t){
     things.addElement(t);
     if(this.defaultCircuit==null ){
         this.defaultCircuit=t;
         if(posFunc!=null && posFunc.getFunctionString()!=null && !posFunc.getFunctionString().equals("") ){
            t.setX(posFunc.evaluate(time));
            t.updateMySlaves();
            if(t instanceof Fluxable)((Fluxable)t).doFluxIntegral();
         }
     }
     if(t instanceof Fluxable)((Fluxable)t).setShowCurrentArrow(showCurrentArrow);
     if(t instanceof Fluxable)((Fluxable)t).doFluxIntegral();
     if(t instanceof UWire)((UWire)t).setShowMeter(showMeter);
     osiInvalid=true;
     return t.hashCode();
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

  public void update(Graphics g){
		if( (owner==null) || ( (owner!=null) && !owner.isClockRunning()) ) paint(g); //no need to paint if the clock is running.
	}
  public String[]   getVarStrings(){return varStrings;}

 // public void setOwner(SApplet o){owner=o;};
 // public SApplet getOwner(){ return owner;};

  public int getPixWidth(){ return iwidth;}
  public int getPixHeight(){ return iheight;}

  double getFieldValue(int i){
    if(fieldValues==null)return 0;
    if((i<0) || (i>=iwidth)) {
        double x=xFromPix(i);
        return fieldFunc.evaluate(x,time);
    }
    return fieldValues[i];
  }

  double getFieldValue(double x){
      return fieldFunc.evaluate(x,time);
  }

  public double xFromPix(int xpix){
      return (xpix-xOffset-iwidth/2)/(double)pixPerUnit;
  }

  public double yFromPix(int ypix){
    return -(ypix-yOffset-iheight/2)/(double)pixPerUnit;
  }

  public int pixFromX(double x){
   return (int)(x*pixPerUnit +xOffset+iwidth/2);
  }

  public int pixFromY(double y){
    return (int)(-y*pixPerUnit+yOffset+iheight/2);
  }

  public final boolean setFormat(String str){
     try{format= new Format(str);}
     catch (IllegalArgumentException e){return false;}
     return true;
  }

  public Dimension getMinimumSize() {
     return new Dimension(200,200);	}

  public Dimension getPreferredSize() {
    Applet a=edu.davidson.graphics.Util.getApplet(this);
    if(fillApplet && a!=null) {
        return new Dimension(a.getBounds().width-6,a.getBounds().height);
    } else return new Dimension(preferredWidth,preferredWidth);
	}

  void paint(){
      try{
        Graphics g= getGraphics();
        backgroundInvalid=true;
        paint(g);
        g.dispose();
      }catch (Exception ex){
      osiInvalid=true;
      backgroundInvalid=true;
    }
  }

  public synchronized void paint(Graphics g){
    try{
      if(!showSchematic) {
          super.paint(g);
          return;
      }
      //backgroundInvalid=true;  this will force a paint every time
      if (getSize().width<=2||getSize().height<=2)return;   // image too small.
      if( backgroundOSI == null || backgroundInvalid ||
          iwidth != this.getSize().width ||iheight != this.getSize().height){
          paintBackgroundOSI();
          osiInvalid=true;
      }
      if( osiInvalid){
          paintOSI();
      }
      g.drawImage(osi,0,0,this);
      paintMessage(g, message);
    }catch (Exception ex){
      osiInvalid=true;
      backgroundInvalid=true;
    }
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

  public void paintTime(){
    Graphics g=getGraphics();
    paintTime(g);
    g.dispose();
  }

  public void paintTime(Graphics g){
    if(!showTime) return;
    String msg=owner.label_time+format.form(time);
   //FontMetrics fm=g.getFontMetrics(g.getFont());
   // g.drawImage(backgroundOSI,10,5,75,25,10,5,75,25,this);
    g.setColor(Color.black);
    g.drawString(msg,10,15);
  }

   public void paintGrid(Graphics g){
    if(!showGrid)return;
    double x,b;
    g.setColor(Color.gray);
    for   (int i=0;i<1+iheight/pixPerUnit;i++) // the y direction
      for (int j=0;j<1+iwidth/pixPerUnit;j++){ // the x direction
        x=xFromPix(pixPerUnit*j);
        b=fieldFunc.evaluate(x,time);
        if(b>=0)
            g.drawLine(pixPerUnit*j,pixPerUnit*i,pixPerUnit*j,pixPerUnit*i);
        else{
            g.drawLine(pixPerUnit*j-2,pixPerUnit*i,pixPerUnit*j+2,pixPerUnit*i);
            g.drawLine(pixPerUnit*j,pixPerUnit*i-2,pixPerUnit*j,pixPerUnit*i+2);
        }
      }
    g.setColor(Color.black);
  }

  public void paintBackground(Graphics g){
    if( fieldValues==null) return;
    double x,b;
    x=xFromPix(0);
    if( fieldFunc==null) return;
    b=fieldFunc.evaluate(x,time);
    fieldValues[0]=b;
    double max=b, min=b;
    for (int j=1;j<iwidth;j++){ // the x direction
        x=xFromPix(j);
        b=fieldFunc.evaluate(x,time);
        fieldValues[j]=b;
        if(b>max) max=b;
        if(b<min) min=b;
    }
    if(!showColor){
        g.setColor(new Color(223,223,223) );
        g.fillRect(0,0,iwidth,iheight);
        return;
    }
    if(autoscalez){
        max=Math.max(0,max);    // make sure max is greater than zero.
        min=Math.min(0,min);    // make sure min is lower than zero.
        if(max==min){ // since field is constant we will use an absolute scale of 2.
           max=Math.max(2,max);
           min=Math.min(-2,min);
        }
        double scale=max-min;
        if (scale==0){
          g.setColor(getBackground());
          g.fillRect(0,0,iwidth,iheight);
          return;
        }
    } else {
       min=zmin;
       max=zmax;
    }
    for (int j=0;j<iwidth;j++){ // the x direction
        b=fieldValues[j];
        b=Math.min(b,max);
        b=Math.max(b,min);
        if(b>0){
            if(max>-min) b=255*b/max;
            else b=-255*b/min;
            g.setColor(new Color( 255,255-(int) b,255-(int) b));
            g.drawLine(j,0,j,iheight);
        }else if(b<0){
            if(max>-min)b=-255*b/max;
            else b=255*b/min;
            g.setColor(new Color(255-(int) b,255-(int) b,255));
            g.drawLine(j,0,j,iheight);
        } else{ //b=0
            g.setColor(Color.white);
            g.drawLine(j,0,j,iheight);
        }
    }
    g.setColor(Color.black);
  }


  public void paintThings(Graphics g){
     owner.lock.getBusyFlag();
     for (int i=0;i<things.size();i++){
            Thing t = (Thing)things.elementAt(i);
            t.paint(g);
      }
      owner.lock.freeBusyFlag();
  }

  void invalidateOSI(){
      this.osiInvalid=true;
      this.backgroundInvalid=true;
  }

  public void paintOSI(){
      if( osi == null || iwidth != getSize().width ||iheight != getSize().height){
          backgroundInvalid=true;
          return;
      }
      Graphics osg = osi.getGraphics();// a graphics context for the  off screen image
      osg.drawImage(osi,0,0,this);
      osg.drawImage(backgroundOSI,0,0,this);
      paintThings(osg);
      paintTime(osg) ;
      if(mouseDown)paintCoords(osg,mouseX,mouseY);
      osg.dispose();
      osiInvalid=false;
      return;
  }

  public void paintBackgroundOSI(){
      if( backgroundOSI == null || iwidth != getSize().width ||iheight != getSize().height){
          iwidth = this.getSize().width;
          iheight = this.getSize().height;
          backgroundOSI = createImage(iwidth,iheight);	 //create an off screen image
          osi = createImage(iwidth,iheight);	 //create an off screen image
          fieldValues= new double[iwidth];
      }
      Graphics osg = backgroundOSI.getGraphics();// a graphics context for the  off screen image
      paintBackground(osg);
      paintGrid(osg);
      osg.dispose();
      updateFlux();
      backgroundInvalid=false;
      return;
    }

  void setDragMode(boolean dm){
      dragMode=dm;
      if(this.defaultCircuit!=null) defaultCircuit.setDragable(dragMode);
  }

  void setPreferredWidth(int pw){preferredWidth=pw;}

  void setMeterMinMax(double min, double max){
      metermin=min;
      metermax=max;
      if(defaultCircuit!=null && defaultCircuit instanceof UWire){
         ((UWire)defaultCircuit).meter.min=min;
         ((UWire)defaultCircuit).meter.max=max;
      }
  }


  /**
	 * Set the range corresponding to blue and red for the B field color.
	 *
	 * @param              min the bfield minimum.
	 * @param              max the bfield maximum.
	 */
	public void setBScale(double min,double max){
     zmin=min;
     zmax=max;
     backgroundInvalid=true;
	}

  // return the voltage if there is a default object.
  public double step(double dt,double t){
      this.time=t+dt;
      if(explicitTime) backgroundInvalid=true;   // the BField may be time dependendent so recalculate the field and update the flux through the objects.
      if(!dragMode && defaultCircuit!=null && posFunc!=null ){   // not in drag mode so check to see if a position function has been defined.
          defaultCircuit.setX(posFunc.evaluate(time));
          defaultCircuit.updateMySlaves();
      }
      paint();
      double volt=0;
      if(defaultCircuit!=null && defaultCircuit instanceof Fluxable)
        volt= ((Fluxable)defaultCircuit).getVolt();  // send the voltage to the internal graph
      owner.updateDataConnections();
      return volt;
  }
  /*
  void setDefaultCircuit(int id){
     Thing t=this.getThing(id);
     if( t!=null && t instanceof Fluxable)  defaultCircuit=t;
       else defaultCircuit=null;
  } */

  void setDefaultCircuit(Thing t){
    this.defaultCircuit=t;
    if( t!=null && t instanceof Fluxable)  defaultCircuit=t;
       else{ defaultCircuit=null;  return;}
    if(posFunc!=null && posFunc.getFunctionString()!=null && !posFunc.getFunctionString().equals("") ){
        t.setX(posFunc.evaluate(time));
        t.updateMySlaves();
        ((Fluxable)t).doFluxIntegral();
    }
  }

  void setDefault(){
      time=0;
      message=null;
      this.defaultCircuit=null;
      things.removeAllElements();
      osiInvalid=true;
      backgroundInvalid=true;
  }

  void resetTime(){
      time=0;
      Thing t=null;
      for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t instanceof Fluxable){
             ((Fluxable)t).reset();
             ((Fluxable)t).doFluxIntegral();
         }
      }
      backgroundInvalid=true;   // the BField may be time dependendent so recalculate the field and update the flux through the objects.

  }

  public void setMessage(String msg){
      if(msg==null || msg.trim().equals("")) message =null;
      else message=msg;
      if((owner==null) || ( (owner!=null) && !owner.isClockRunning()) ){
          osiInvalid=true;
          repaint(); // repaint(0,r.height-20,boxWidth,20);
      }
  }

  // set the time to zero.
  void setTime(double t){
      time=t;
      backgroundInvalid=true;   // the BField may be time dependendent so recalculate the field and update the flux through the objects.
  }

  void checkFieldFunctionForTime(String fieldStr){
     String str=new String(fieldStr);
     checkFunc.defineVariable(1,"x"); // define the variable
     checkFunc.define( str.toLowerCase() );
     checkFunc.parse();
     if(checkFunc.getErrorCode() != Parser.NO_ERROR){
         explicitTime=true;
     }
     explicitTime=false;  // parse worked with only the x variable.
  }

  boolean parseFieldFunction(String fieldStr){
     checkFieldFunctionForTime( fieldStr);
     fieldFunc.defineVariable(1,"x"); // define the variable
     fieldFunc.defineVariable(2,"t"); // define the variable
     fieldFunc.define( fieldStr.toLowerCase() );
     fieldFunc.parse();
     if(fieldFunc.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse field function, B(x,t): "+fieldStr);
         System.out.println("Parse error: " + fieldFunc.getErrorString() +
                   " at B(x,t) function, position " + fieldFunc.getErrorPosition());
         return false;
     }
     if(defaultCircuit!=null && defaultCircuit instanceof Fluxable){
         ((Fluxable)defaultCircuit).reset();
         ((Fluxable)defaultCircuit).doFluxIntegral();
     }
     osiInvalid=true;
     backgroundInvalid=true;
     return true;
  }

  boolean parsePosFunction(String posStr){
     if(posStr==null || posStr.equals("")){posFunc=null; return true;}
     if(posFunc==null) posFunc = new Parser(1);
     posFunc.defineVariable(1,"t"); // define the variable
     posFunc.define(posStr.toLowerCase());
     posFunc.parse();
     if(posFunc.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse x(t): "+posStr );
         System.out.println("Parse error: " + posFunc.getErrorString() +
                   " at x(t) function, charcter " + posFunc.getErrorPosition());
         return false;
     }
     if(defaultCircuit!=null){ // && defaultCircuit.isNoDrag()){
         if(defaultCircuit instanceof Fluxable)((Fluxable)defaultCircuit).reset();
         defaultCircuit.setX(posFunc.evaluate(time));
         defaultCircuit.updateMySlaves();
         if(defaultCircuit instanceof Fluxable)((Fluxable)defaultCircuit).doFluxIntegral();
     }
     osiInvalid=true;
     return true;
  }

  // mouse stuff

  void paintCoords(int xPix,int yPix){
      if(!coordDisplay) return;
      Graphics g=getGraphics();
      paintCoords( g,xPix,yPix);
      g.dispose();
  }

  void paintCoords(Graphics g, int xPix,int yPix){
    if(!coordDisplay) return;
    double x=(xPix-xOffset-iwidth/2)/(double)pixPerUnit;
    double y=-(yPix-yOffset-iheight/2)/(double)pixPerUnit;
    String msg;
    msg="x="+format.form(x)+ "  y="+format.form(y);
    if(showBOnDrag) msg+= "  B="+format.form(fieldFunc.evaluate(x,time));
    Rectangle r = getBounds();
    g.setColor(Color.yellow);
    FontMetrics fm=g.getFontMetrics(g.getFont());
    boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
    g.fillRect(0,r.height-20,boxWidth,20);
    g.setColor(Color.black);
    g.drawString(msg,10,r.height-5);
  }

  void updateFlux(){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t instanceof Fluxable) ((Fluxable)t).doFluxIntegral();
    }

  }

  Thing isInsideDragableThing(int x, int y){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(!t.isNoDrag() && t.isInsideThing(x,y)){
           return t;
         }
    }
    return null;
  }

  void schematic_mousePressed(MouseEvent e) {
      mouseDown=true;
      mouseX=e.getX();
      mouseY=e.getY();
      if(isInsideDragableThing(mouseX,mouseY)!=null ){
         dragThing=isInsideDragableThing(mouseX,mouseY);
      }
      paintCoords(mouseX,mouseY);
  }
  void schematic_mouseDragged(MouseEvent e){
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
      if(dragThing!=null){
         owner.lock.getBusyFlag();
         dragThing.setXY(x,y);
         dragThing.updateMySlaves();
         owner.lock.freeBusyFlag();
         if((owner==null) || ( (owner!=null) && !owner.isClockRunning()) ){// paint and update the screen only if mouse is running.
             if(dragThing instanceof Fluxable)((Fluxable)dragThing).doFluxIntegral();
             //owner.updateDataConnection(dragThing.hashCode());
             owner.updateDataConnections();
             osiInvalid=true;
             paint();
         }
      }
      if(owner!=null && !owner.isClockRunning() )paintCoords(mouseX,mouseY);
  }
  void schematic_mouseReleased(MouseEvent e){
      mouseDown=false;
      dragThing=null;
      boxWidth=0;
      Rectangle r = getBounds();
      if((owner==null) || ( (owner!=null) && !owner.isClockRunning()) ){
          osiInvalid=true;
          repaint(); // repaint(0,r.height-20,boxWidth,20);
      }
      // reset the yellow message box.
  }

  public void schematic_mouseEntered(MouseEvent e) {
     setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    //int xPix=e.getX();
    //int yPix=e.getY();
    //boxWidth=0;  // reset the yellow message box.
    //drawCoords(xPix,yPix);
  }

  public void schematic_mouseExited(MouseEvent e) {
     setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    // repaint();
  }

  public void schematic_mouseMoved(MouseEvent e) {
   int xPix=e.getX();
   int yPix=e.getY();
   if(isInsideDragableThing(xPix,yPix)!=null )
       setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
   else  setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

}


class Schematic_mouseAdapter extends java.awt.event.MouseAdapter {
  Schematic adaptee;

  Schematic_mouseAdapter(Schematic adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseEntered(MouseEvent e) {
    adaptee.schematic_mouseEntered(e);
  }

  public void mouseExited(MouseEvent e) {
    adaptee.schematic_mouseExited(e);
  }

  public void mousePressed(MouseEvent e) {
    adaptee.schematic_mousePressed(e);
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.schematic_mouseReleased(e);
  }
}
class Schematic_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  Schematic adaptee;

  Schematic_mouseMotionAdapter(Schematic adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.schematic_mouseMoved(e);
  }
  public void mouseDragged(MouseEvent e) {
    adaptee.schematic_mouseDragged(e);
  }
}