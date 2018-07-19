package circuit;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import java.util.Vector;
import edu.davidson.graphics.*;
import edu.davidson.tools.SApplet;

final public class Circuit extends HintPanel{
  //private static int runningID=0;
  private Image     osi = null;
  private boolean   osiInvalid=true;
  private int       iwidth  = 0;
  private int       iheight = 0;
  private int       xOffset,yOffset;
  private int       pixPerCell=60;
  private int       preferredPixPerCell=60;
  private int       iGrid=3;
  private int       jGrid=3;
  private Vector    parts = new Vector();
  private int       maxNumber=50;
  //private boolean   noPreferredSize=false;

  SApplet owner;
  Boolean showV=null;
  Boolean showCurrent=null;
  Boolean showPhase=null;
  Boolean showF=null;
  Boolean showR=null;
  Boolean showC=null;
  Boolean showL=null;
  Boolean showZ=null;
  Vector dataSources = new Vector();

  public Circuit(SApplet o) {
        owner=o;
        addMouseMotionListener(new Circuit_mouseMotionAdapter(this));
        setBackground(new Color(225,225,225));
  }

  public Battery addBattery(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    Battery battery = new Battery(owner,this,i1,j1,i2,j2);
    parts.addElement(battery);
    osiInvalid=true;
    repaint();
    setShowInfo(battery);
    return battery;
  }

  public Resistor addResistor(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    Resistor resistor = new Resistor(owner, this,i1,j1,i2,j2);
    parts.addElement(resistor);
    osiInvalid=true;
    repaint();
    setShowInfo(resistor);
    return resistor;
  }

  public Capacitor addCapacitor(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    Capacitor capacitor = new Capacitor(owner, this,i1,j1,i2,j2);
    parts.addElement(capacitor);
    osiInvalid=true;
    repaint();
    setShowInfo(capacitor);
    return capacitor;
  }

  public SineWave addSineWave(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    SineWave sineWave = new SineWave(owner, this,i1,j1,i2,j2);
    parts.addElement(sineWave);
    osiInvalid=true;
    repaint();
    setShowInfo(sineWave);
    return sineWave;
  }

  public OnOffSwitch addOnOffSwitch(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    OnOffSwitch onOffSwitch = new OnOffSwitch(owner, this,i1,j1,i2,j2);
    parts.addElement(onOffSwitch);
    osiInvalid=true;
    repaint();
    setShowInfo(onOffSwitch);
    return onOffSwitch;
  }

  public Inductor addInductor(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    Inductor inductor = new Inductor(owner, this,i1,j1,i2,j2);
    parts.addElement(inductor);
    osiInvalid=true;
    repaint();
    setShowInfo(inductor);
    return inductor;
  }

  public Transformer addTransformer(int i1, int j1, int i2, int j2, boolean vert){
    if (parts.size()>=maxNumber) return null;
    Transformer transformer = new Transformer(owner, this,i1,j1,i2,j2,vert);
    parts.addElement(transformer);
    osiInvalid=true;
    repaint();
    setShowInfo(transformer);
    return transformer;
  }

 public Part addPart(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    Part part = new Part(owner, this,i1,j1,i2,j2);
    parts.addElement(part);
    osiInvalid=true;
    repaint();
    setShowInfo(part);
    return part;
  }

  public Wire addWire(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    Wire wire = new Wire(owner, this,i1,j1,i2,j2);
    parts.addElement(wire);
    osiInvalid=true;
    repaint();
    setShowInfo(wire);
    return wire;
  }

  public Voltmeter addVoltmeter(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    Voltmeter voltmeter = new Voltmeter(owner, this,i1,j1,i2,j2);
    parts.addElement(voltmeter);
    osiInvalid=true;
    repaint();
    setShowInfo(voltmeter);
    return voltmeter;
  }

  public Ammeter addAmmeter(int i1, int j1, int i2, int j2){
    if (parts.size()>=maxNumber) return null;
    Ammeter ammeter = new Ammeter(owner,this,i1,j1,i2,j2);
    parts.addElement(ammeter);
    osiInvalid=true;
    repaint();
    setShowInfo(ammeter);
    return ammeter;
  }

  public Part getPartFromID(int id){
      for (int i=0;i<parts.size();i++){
            Part p = (Part)parts.elementAt(i);
            if (p.getID()==id)return p;
      }
      return null;
  }

  public Dimension getMinimumSize() {
   return new Dimension(20*(jGrid),20*(iGrid));
	 // return new Dimension(preferredPixPerCell*(jGrid),preferredPixPerCell*(iGrid));
	}
  public Dimension getPreferredSize() {
    //return new Dimension(400,400);
	  return new Dimension(preferredPixPerCell*(jGrid),preferredPixPerCell*(iGrid));
	}

  Image  getOsi(){return osi;}

  void forceRepaint(){
      osiInvalid=true;
      repaint();
  }

  public void update(Graphics g){
		paint(g); //update usually does a rect fill with a background color.  We don't need this.
	}

  public void paint(Graphics g){
    if(owner.destroyed) return;
    if (getSize().width==0||getSize().height==0)return;   // image too small.
    if( osi == null || osiInvalid ||
        iwidth != this.getSize().width ||iheight != this.getSize().height){
            paintOSI();
    }
    g.drawImage(osi,0,0,this);
    paintHint(g);
  }

  public void paintGrid(Graphics g){
    if(owner.destroyed) return;
    g.setColor(Color.black);
    for   (int i=0;i<iGrid;i++) // the y direction
      for (int j=0;j<jGrid;j++) // the x direction
        g.drawLine(xOffset+pixPerCell*j,yOffset+pixPerCell*i,xOffset+pixPerCell*j,yOffset+ pixPerCell*i);
  }


  public void paintParts(Graphics g){
      if(owner.destroyed) return;
      for (int i=0;i<parts.size();i++){
            Part p = (Part)parts.elementAt(i);
            p.paint(g,pixPerCell,xOffset,yOffset);
      }
  }

  public void paintOSI(){
      if(owner.destroyed) return;
      if( osi == null || iwidth != getSize().width ||iheight != getSize().height){
          iwidth = this.getSize().width;
          iheight = this.getSize().height;
          osi = createImage(iwidth,iheight);	 //create an off screen image
      }
      pixPerCell=preferredPixPerCell;
      if(iwidth<(jGrid)*pixPerCell) pixPerCell=iwidth/(jGrid);
      if(iheight<(iGrid)*pixPerCell) pixPerCell=iheight/(iGrid);
      xOffset= (iwidth-(jGrid-1)*pixPerCell)/2;
      yOffset= (iheight-(iGrid-1)*pixPerCell)/2;
      Graphics osg = osi.getGraphics();// a graphics context for the  off screen image
      osg.setColor(getBackground());
      osg.fillRect(0,0,iwidth,iheight);
      //paintGrid(osg);
      paintParts(osg);
      osg.dispose();
      osiInvalid=false;
      return;
    }
  // package methods
  boolean setBatteryEMF(int id, double emf,boolean showV){
    Part bat=getPartFromID(id);
    if(bat instanceof Battery){
        bat.setVoltRMS(emf);
        bat.showV=showV;
        return true;
    }else return false;
  }

  boolean setCapacitance(int id, double c, boolean showC, boolean showV, boolean showPhase){
    Part cap=getPartFromID(id);
    if(cap instanceof Capacitor){
        cap.setC(c);
        cap.showC=showC;
        cap.showV=showV;
        cap.showPhase=showPhase;
        return true;
    }else return false;
  }

  boolean setSwitchOn(int id, boolean on){
    Part sw=getPartFromID(id);
    if(sw instanceof OnOffSwitch){
        sw.setSwitchOn(on);
        return true;
    }else return false;
  }

  boolean setCurrent(int id, double c){
    Part part=getPartFromID(id);
    part.setCurrentRMS(c);
    return true;
  }

  boolean setShowCurrent(int id, boolean sc){
    Part part=getPartFromID(id);
    part.showCurrent=sc;
    return true;
  }

  boolean setPhaseDegree(int id, double p){
    Part part=getPartFromID(id);
    part.setPhaseDegree(p);
    return true;
  }

  boolean setShowPhase(int id, boolean sp){
    Part part=getPartFromID(id);
    part.showPhase=sp;
    return true;
  }


  boolean setVolt(int id, double v){
    Part part=getPartFromID(id);
    part.setVoltRMS(v);
    return true;
  }

  boolean setShowV(int id, boolean sv){
    Part part=getPartFromID(id);
    part.showV=sv;
    return true;
  }

  boolean setInductance(int id, double l, boolean showL, boolean showV, boolean showPhase){
    Part ind=getPartFromID(id);
    if(ind instanceof Inductor){
        ind.setL(l);
        ind.showL=showL;
        ind.showV=showV;
        ind.showPhase=showPhase;
        return true;
    }else return false;
  }

  boolean setResistance(int id, double r, boolean showR, boolean showV, boolean showPhase){
    Part res=getPartFromID(id);
    if(res instanceof Resistor){
        res.setR(r);
        res.showR=showR;
        res.showV=showV;
        res.showPhase=showPhase;
        return true;
    }else return false;
  }

  boolean setAmmeter(int id, double a, boolean sc){
    Part meter=getPartFromID(id);
    if(meter instanceof Ammeter){
        meter.setCurrentRMS(a);
        meter.showCurrent=sc;
        return true;
    }else return false;
  }

  boolean setLabel(int id, String str){
    Part part=getPartFromID(id);
    part.setLabel(str);
    return true;
  }

  boolean setHint(int id, String str){
    Part part=getPartFromID(id);
    part.setCustomHint(str);
    return true;
  }

  boolean setMilliAmp(int id, boolean sma){
    Part part=getPartFromID(id);
    part.milliAmp=sma;
    return true;
  }

  boolean setVoltage(int id, double v){
    Part part=getPartFromID(id);
    part.setVoltRMS(v);
    return true;
  }

  boolean setVoltmeter(int id, double v, boolean sv){
    Part meter=getPartFromID(id);
    if(meter instanceof Voltmeter){
        meter.setVoltRMS(v);
        meter.showV=sv;
        return true;
    }else return false;
  }

  Part isInside(int xpix, int ypix){
      for (int i=0;i<parts.size();i++){
        Part p = (Part)parts.elementAt(i);
        if(p.isInside(xpix, ypix, pixPerCell, xOffset, yOffset)) return p;
      }
      return null;
  }

  void setShowInfo(Part p){
      if(showV!=null)p.showV=showV.booleanValue();
      if(showCurrent!=null)p.showCurrent=showCurrent.booleanValue();
      if(showPhase!=null)p.showPhase=showPhase.booleanValue();
      if(showF!=null)p.showF=showF.booleanValue();
      if(showR!=null)p.showR=showR.booleanValue();
      if(showC!=null)p.showC=showC.booleanValue();
      if(showL!=null)p.showL=showL.booleanValue();
      if(showZ!=null)p.showZ=showZ.booleanValue();
  }
  void setDefault(int ppc){
      setDefault();
      preferredPixPerCell=ppc;
  }
  void setDefault(){
    updateBubbleHelp(null);
    this.destroyHint();
    showV=null;
    showCurrent=null;
    showPhase=null;
    showF=null;
    showR=null;
    showC=null;
    showL=null;
    showZ=null;
    parts.removeAllElements();
  }

  void setShowV(boolean val){showV=new Boolean(val); }
  void setShowCurrent(boolean val){showCurrent=new Boolean(val); }
  void setShowPhase(boolean val){showPhase=new Boolean(val); }
  void setShowF(boolean val){showPhase=new Boolean(val); }
  void setShowR(boolean val){showR=new Boolean(val); }
  void setShowC(boolean val){showC=new Boolean(val); }
  void setShowL(boolean val){showL=new Boolean(val); }
  void setShowZ(boolean val){showZ=new Boolean(val); }


  void setPreferredPixPerCell(int ppc){
      pixPerCell=ppc;
      preferredPixPerCell=ppc;
      invalidate();
      if(getParent()!=null)getParent().validate();
          else validate();
  }
  void setGridSize(int gsi, int gsj){
       iGrid=gsi;
       jGrid=gsj;
       invalidate();
       if(getParent()!=null)getParent().validate();
           else validate();
  }

  void this_mouseMoved(MouseEvent e) {
    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    int xPix=e.getX();
    int yPix=e.getY();
    Part p=isInside(xPix, yPix);
    if(p!=null){
      //System.out.println("inside " +p.getHint());
      updateBubbleHelp(p.getHint());
    }else{
      //System.out.println("not inside");
      updateBubbleHelp(null);
    }
  }

  void this_mouseDragged(MouseEvent e) {
  }
}

class Circuit_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  Circuit adaptee;

  Circuit_mouseMotionAdapter(Circuit adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseDragged(MouseEvent e) {
    adaptee.this_mouseDragged(e);
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.this_mouseMoved(e);
  }
}