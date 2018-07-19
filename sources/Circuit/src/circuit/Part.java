package circuit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.display.*;
import edu.davidson.tools.*;

public class Part implements SDataSource{
  static char    delta='\u0394';
  static char    omega='\u03a9';
  static char    micro='\u03bc';
  static char    phi='\u03c6';
  static char    degree='\u00b0';

  SApplet owner=null;

  Format    format= new Format("%-+6.2f");   // the default
  Format    format0= new Format("%-+6.0f");
  Format    format1= new Format("%-+6.1f");
  Format    format2= new Format("%-+6.2f");
  Format    format3= new Format("%-+6.3f");
  Font f=new Font("Helvetica",Font.BOLD,12);
  Color color=new Color(0,127,0);   // dark green
  int i1,j1,i2,j2;
  int id=0;

  String label="Z";
  String customHint=null;

  boolean showV=false;
  boolean showCurrent=false;
  boolean showPhase=false;
  boolean showF=false;
  boolean showR=false;
  boolean showC=false;
  boolean showL=false;
  boolean showZ=true;
  boolean milliAmp=false;
  boolean switchOn=false;

  String[] varStrings= new String[]{"t","r","l","c","z","v","i","re","im","f","vrms"};
  double[][] ds=new double[1][11];  // the datasource state variables;

  double voltRMS=0;       // not all these variables are used for every part.
  double currentRMS=0;
  double voltInstantaneous=0;       // not all these variables are used for every part.
  double currentInstantaneous=0;
  double freq=1;
  double phase=0;  // in degrees.
  double radian=0;  // in phase in radians.
  double res=0;
  double cap=0;
  double ind=0;
  double time=0;
  Circuit circuit;

  public Part(SApplet o, Circuit c, int i1, int j1,int i2,int j2) {
     owner=o;
     circuit=c;
     this.i1=i1;
     this.j1=j1;
     this.i2=i2;
     this.j2=j2;
     try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
  }
  public int getID(){return hashCode();}
  public void setOwner(SApplet owner){}
  public SApplet getOwner(){return owner;}
  public String[] getVarStrings(){return varStrings;}

  public double[][] getVariables(){
    //"t","r","l","c","z","v","i","re","im","f"
     ds[0][0]=time;  //t
     ds[0][1]=res;
     ds[0][2]=ind;
     ds[0][3]=cap;
     if(currentRMS==0) ds[0][4]=0;
         else ds[0][4]=Math.abs(voltRMS/currentRMS);
     ds[0][5]=voltInstantaneous;
     ds[0][6]=currentInstantaneous;
     ds[0][7]=ds[0][4]*Math.cos(phase);
     ds[0][8]=ds[0][4]*Math.sin(phase);
     ds[0][9]=freq;
     ds[0][10]=voltRMS;
     return ds;
  }

  String getMSHint(){
      double xl=2*Math.PI*freq*ind;
      double xc=-2*Math.PI*cap;
      if(xc!=0)xc=0;
      double z=xl+xc;
      String msg="";
      if(showZ) msg+= "Z="+format1.form(res)+" + i"+format1.form(z)+" "+" "+Common.OHM+" ";
      if(showR) msg+= "R="+format1.form(res)+" "+" "+Common.OHM+"   ";
      if(showC) msg+= "C="+format3.form(cap)+" "+" uF    ";
      if(showL) msg+= "L="+format1.form(ind)+" mH    ";
      if(showV) msg+= Common.LEGEND_DELTAV+format1.form(voltRMS)+" "+Common.LEGEND_V+"    ";
      if(showPhase) msg+= Common.PHASE+"="+(int)(phase)+" "+Common.DEG+"  ";
      if(showCurrent) msg+= "I="+format3.form(currentRMS)+" "+Common.LEGEND_I+"    ";
      if(msg.trim().length()==0) msg=Common.LEGEND_NOVALUE; //"Values are not available for this part.";
      if(this instanceof Wire) msg=null;
      return msg;
  }


  String getHint(){
      if(customHint!=null) return customHint;
      if(isMicrosoft()){return getMSHint(); }
      double xl=2*Math.PI*freq*ind;
      double xc=-2*Math.PI*cap;
      if(xc!=0)xc=0;
      double z=xl+xc;
      String msg="";
      if(showZ) msg+= "Z="+format1.form(res)+" + i"+format1.form(z)+" "+omega+"  ";
      if(showR) msg+= "R="+format1.form(res)+" "+omega+"  ";
      if(showC) msg+= "C="+format3.form(cap)+" "+micro+"F  ";
      if(showL) msg+= "L="+format1.form(ind)+" mH  ";
      if(showV) msg+= delta+Common.LEGEND_VEQU+format1.form(voltRMS)+" "+Common.LEGEND_V+"  ";
      if(showPhase) msg+= phi+ "="+ (int)(phase)+ degree+"  ";
      if(showCurrent && !milliAmp) msg+= "I="+format3.form(currentRMS)+" "+Common.LEGEND_A+"  ";
      if(showCurrent && milliAmp) msg+= "I="+format3.form(currentRMS)+" m"+Common.LEGEND_A+"  ";
      if(msg.trim().length()==0) msg=Common.LEGEND_NOVALUE; //"Values are not available for this part.";
      if(this instanceof Wire) msg=null;
      return msg;
  }

  boolean isInside(int x, int y, int pixPerCell,int xOffset,int yOffset){
      int x1=xOffset+pixPerCell*j1;
      int y1=yOffset+pixPerCell*i1;
      int x2=xOffset+pixPerCell*j2;
      int y2=yOffset+pixPerCell*i2;
      if( Math.abs(x-(x1+x2)/2)<pixPerCell/4 && Math.abs(y-(y1+y2)/2)<pixPerCell/4 ) return true;
        else return false;
  }

  void paint(Graphics g,int pixPerCell,int xOffset,int yOffset){
      int x1=xOffset+pixPerCell*j1;
      int y1=yOffset+pixPerCell*i1;
      int x2=xOffset+pixPerCell*j2;
      int y2=yOffset+ pixPerCell*i2;
      drawSymbol(g,x1,y1,x2,y2,pixPerCell);
      if(i1==i2) drawLabel(g, (x1+x2)/2,(y1+y2)/2,-pixPerCell/5, -5);
      else if(j1==j2) drawLabel(g, (x1+x2)/2,(y1+y2)/2, -8, -5);
      else drawLabel(g, (x1+x2)/2,(y1+y2)/2, -8, -5);
  }
  void drawLabel(Graphics g, int x , int y){drawLabel(g,x,y,0,0);}
  void drawLabel(Graphics g, int x , int y, int xOff, int yOff){
      if(label==null) return;
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
      g.drawString(label, x+xOff, y-yOff);
      g.setFont(oldFont);
  }

  void drawSymbol(Graphics g, int x1,int y1,int x2,int y2, int s){
      g.setColor(color);
      double x=x2-x1;
      double y=-(y2-y1);
      double h=Math.sqrt(x*x+y*y);
      if(h<2) return; // something is wrong
      double w=(h/2.0-s/4.0);
      int base_x1= (int)(x1+(x*w)/h);
      int base_y1= (int)(y1-(y*w)/h);
      int base_x2= (int)(x2-(x*w)/h);
      int base_y2= (int)(y2+(y*w)/h);
      g.drawLine(x1,y1,base_x1,base_y1);
      g.drawLine(x2,y2,base_x2,base_y2);
      w=s/8.0;
      double u=(w*x/h);
      double v=-(w*y/h);
      g.drawLine((int)(base_x1-v),(int)(base_y1+u),(int)(base_x1+v),(int)(base_y1-u));
      g.drawLine((int)(base_x2-v),(int)(base_y2+u),(int)(base_x2+v),(int)(base_y2-u));
      g.drawLine((int)(base_x2-v),(int)(base_y2+u),(int)(base_x1-v),(int)(base_y1+u));
      g.drawLine((int)(base_x2+v),(int)(base_y2-u),(int)(base_x1+v),(int)(base_y1-u));
  }

  void setCurrentRMS(double c){currentRMS=c;}
  double getCurrentRMS(){return currentRMS;}

  void setCurrentInstantaneous(double c){currentInstantaneous=c;}
  double getCurrentInstantaneous(){return currentInstantaneous;}

  void setVoltRMS(double v){voltRMS=v;}
  double getVoltRMS(){return voltRMS;}

  void setVoltInstantaneous(double v){voltInstantaneous=v;}
  double getVoltInstantaneous(){return voltInstantaneous;}

  void setLabel(String l){this.label=l; circuit.forceRepaint();}
  String getLabel(){return label;}

  void setCustomHint(String h){
      if(h.trim().equals("")) customHint=null;
      else customHint=h;}
  String getCustomHint(){return customHint;}

  void setC(double c){cap=c;}
  double getC(){return cap;}

  void setF(double f){freq=f;}
  double getF(){return freq;}

  void setL(double l){ind=l;}
  double getL(){return ind;}

  void setPhaseDegree(double d){phase=d; radian=d*Math.PI/180.0;}
  double getPhaseDegree(){return phase;}

  void setPhaseRadian(double r){radian=r; phase=r*180.0/Math.PI;}
  double getPhaseRadian(){return radian;}

  void setR(double r){res=r;}
  double getR(){return res;}

  void setTime(double t){time=t;}
  double getTime(){return time;}

  final void setMilliAmp(boolean sma){milliAmp=sma;}
  final void setSwitchOn(boolean on){switchOn=on;}

  static public boolean isMicrosoft() {
	if(SClock.isJS) return false;
    String vendor=System.getProperty("java.vendor").toLowerCase();
    return(vendor.startsWith("microsoft"));
	}

}