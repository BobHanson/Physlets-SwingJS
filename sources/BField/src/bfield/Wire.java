package bfield;
import java.awt.*;
import edu.davidson.tools.*;
import edu.davidson.display.Thing;

public class Wire extends Thing{
  //String label=null;
  //Font f=new Font("Helvetica",Font.BOLD,14);
  //boolean noDrag=true;
  //boolean hideWire=false;
  //Color color=Color.black;
  // double x=0;
  // double y=0;
  // Format    format= new Format("%-+6.2g");
  //String[] varStrings= new String[]{"x","y","bx","by","i"};
  //double[][] ds=new double[1][5];  // the datasource state variables
  boolean noOptionDrag=true;
  boolean showInfo=false;
  protected  boolean showF=true;
  protected  boolean showFComponents=false;
  double xo=0;   // the offset in real world units.
  double yo=0;
  FieldPanel p;
  double current=1.0;
  double radius=0;
  int    xPix=0;  // pixel values used during the last paint.
  int    yPix=0;

  public Wire(FieldPanel p, double x,double y, double c) {
      super(p,x,y);
      this.s=10;
      this.p=p;
      this.current=c;
      if(c<0) color=Color.blue;
      else if(c>0) color=Color.red;
      varStrings= new String[]{"x","y","bx","by","i"};
      ds=new double[1][5];  // the datasource state variables
      //try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}    this is now done in Thing.
  }

  public void setDisplayOff(int xOff,int yOff){
    xDisplayOff=xOff;
    yDisplayOff=yOff;
    xo=p.xFromPix(0)-p.xFromPix(xOff);
    yo=p.yFromPix(0)-p.yFromPix(-yOff);
  }

  double[] getB(double[] r){
      double xx=x-xo;
      double yy=y-yo;
      double r2=((xx-r[0])*(xx-r[0])+(yy-r[1])*(yy-r[1]));
      double bx=-current*(r[1]-yy)/r2;
      double by= current*(r[0]-xx)/r2;
      r[0]=bx;
      r[1]=by;
      return r;
  }

  double getWireBx(double x1, double y1){
      double xx=x-xo;
      double yy=y-yo;
      return -current*(y1-yy)/((xx-x1)*(xx-x1)+(yy-y1)*(yy-y1));
  }

  double getWireBy(double x1, double y1){
      double xx=x-xo;
      double yy=y-yo;
      return current*(x1-xx)/((xx-x1)*(xx-x1)+(yy-y1)*(yy-y1));
  }

  final double getCurrent(){return current;}
  final void setCurrent(double c){current=c;}

  public final int getID(){return hashCode();}

  public SApplet getOwner(){return p.getOwner();}
  public void setOwner(SApplet owner){;}

  final double getRadius(){return radius;}
  final void setRadius(double r){radius=r;}

  void setShowF(boolean sf){showF=sf;}
  boolean isShowF(){return showF;}

  void setShowFComponents(boolean sfc){showFComponents=sfc;}
  boolean isShowFComponents(){return showFComponents;}

  public String[] getVarStrings(){return varStrings;}

  final public double[][] getVariables(){
     ds[0][0]=x;  //x
     ds[0][1]=y;  //y
     ds[0][2]=p.getBx(x,y,this);  //Fx
     ds[0][3]=p.getBy(x,y,this);;   //fy
     ds[0][4]=current;  //x
     return ds;
  }

  final double getMaxB(){
      double x0=p.xFromPix(0);
      double x1=p.xFromPix(s);
      double dx=x0-x1;
      if(dx!=0)return Math.abs(current/dx);
      else return 0;
  }

  //final String getLabel(){return label;}
  /*
  final void setLabel(String l){
      if(l==null || l.trim().equals("")) label=null;
          else label=new String(new char[] {l.charAt(0)} );
  } */

 boolean isInsidePix(int x, int y){
      //System.out.println("is inside x:"+x);
      //System.out.println("is inside xpix:"+xPix);
      if (noDrag) return false;
      if ((Math.abs(xPix-x)<s+1)&&(Math.abs(yPix-y)<s+1))  return true;
      else return false;
  }

  public boolean isInsideThing(int i, int j){return isInsidePix(i,j);}

  int getHotSpot(int x, int y){ return 0;}

  boolean isInsideWire(double x, double y, int hs){
      if(hs!=0) return false;
      return isInsidePix(p.pixFromX(x),p.pixFromY(y));
  }

  final boolean isInsideWire(double x, double y){
      return isInsidePix(p.pixFromX(x),p.pixFromY(y));
  }

  public void paint(Graphics osg){
      if ( !isVisible() ) return;
      xPix=p.pixFromX(x)+xDisplayOff;
      yPix=p.pixFromY(y)-yDisplayOff;
      osg.setColor(color);
      osg.fillOval((xPix-s/2),(yPix-s/2),s,s);
      if(label!=null){
          Font oldFont=osg.getFont();
          osg.setFont(font); // this font is used for messages and captions.
          osg.setColor(Color.white);
          osg.drawString(label, xPix-4, yPix+5);
          osg.setColor(Color.black);
          osg.setFont(oldFont);
      }
  }

  public void paintInfo(Graphics osg, int hotSpot){
      if(!showInfo && hotSpot==0) return;
          osg.setColor(Color.yellow);
          osg.fillRect(xPix+15, yPix-8,60,15);
          osg.setColor(Color.black);
          if(!noOptionDrag && Math.abs(hotSpot)==1) osg.drawString("R ="+format.form(radius), xPix+20, yPix+5);
          if(Math.abs(hotSpot)==0) osg.drawString("I ="+format.form(current), xPix+20, yPix+5);
  }

}