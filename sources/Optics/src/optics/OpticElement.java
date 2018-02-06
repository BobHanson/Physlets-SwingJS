package optics;

import java.awt.*;
import java.util.*;
import java.text.DecimalFormat;
import edu.davidson.display.Thing;

public class OpticElement extends Thing {
    double focalLength;
    boolean imageLabel=true;
    int errCode=0;
    private Color rayColor= new Color(255,255,191);
    Color color= null;
    private int nextElement=0;
    private boolean clipOn=false;
    boolean drawDashedRay;  //for use within the psource
    boolean drawSourceRay;  //for use within the psource
    boolean showRays=true; // added by WC to turn off ray drawing for some types of objects
    int pixPerUnit = 1;
    int xPosition;
    int yPosition;
    boolean info;  //whether or not to show info or allow dragging of an objects properties
    boolean showFocus=false;
    double percentSize;
    double indexOfRefraction=1;
    double[][] mat = new double[2][2];
    DecimalFormat df = new DecimalFormat("0.##");
    Bench bench=null;

  public OpticElement(Bench b){
    super(b);
    pixPerUnit=b.pixPerUnit;
    bench=b;
    mat[0][0] = 1;
    mat[0][1] = 0;
    mat[1][0] = 0;
    mat[1][1] = 1;
    varStrings= new String[]{"x","y","f","n"};
    ds=new double[1][4];  // the datasource state variables
  }

  public void setPixPerUnit(int ppu){
    pixPerUnit = ppu;
  }

  public void paintTempActive(Graphics g, Rectangle r){
  }

  public void paintTemp(Graphics g, Rectangle r){
  }

  public void setInfo(boolean i){
    info = i;
  }

  public void setDrag(boolean d){
    noDrag = !d;
  }
 /*
  public void setPropertyDrag(boolean pd){
    propertyDrag = pd;
  }   */

  public boolean getInfo(){
    return info;
  }

  public boolean getDrag(){
    return !noDrag;
  }
  /*
  public boolean getPropertyDrag(){
    return propertyDrag;
  } */

  /*
  public void setSize(double s){
    size = s;
  }  */

  /*
  public double getSize(){
    return size;
  } */

  public int isInside(int mouseX,int mouseY,Rectangle r){
    if (mouseX>xPosition-10 && mouseX<xPosition+10) return 1;
      else return 0;
  }

  public int isInside(int mouseX,int mouseY){
    Rectangle r=bench.getBounds();
    return  isInside( mouseX, mouseY, r);
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    double tempV = v[0]-r.height/2;
    v[0] = v[0]*mat[0][0] + v[1]*mat[0][1];
    v[1] = direction*(tempV*mat[1][0] + v[1]*mat[1][1]);
    return v;
  }

  public void setColor(Color c){
      color=c;
      rayColor=c;
  }

  public void setResizable(boolean rs){
      resizable=rs;
      //propertyDrag=rs;
  }

  public void paint(Graphics g,Rectangle r){
    if(color==null)g.setColor(Color.white);
      else  g.setColor(color);
    g.drawLine(xPosition,0,xPosition,r.height);
  }

  public void paintActive(Graphics g,Rectangle r){
    g.setColor(Color.green);
    g.drawLine(xPosition,0,xPosition,r.height);
  }

  public void setPixX(int setX, Rectangle r){
    if (setX<1 && r.width>0) xPosition=1;
      else if (setX>r.width-1 && r.width>0) xPosition = r.width-1;
      else xPosition = setX;
    super.setX(xPosition/(double)this.pixPerUnit);
    if(constraint!=null){
        xPosition=(int)(getX()*this.pixPerUnit);
    }
    if(bench.activeElement!=null) bench.activeElement.updateMySlaves();
  }

  public void setX(double x){
    setPixX((int)(x*bench.pixPerUnit), bench.getBounds());
    if(bench!=null && bench.owner!=null && bench.owner.isAutoRefresh())bench.repaint();
  }

  public void setXY(double x, double y){
    setPixX((int)(x*bench.pixPerUnit), bench.getBounds());
    setPixY((int)(bench.iheight/2.0-y*bench.pixPerUnit), bench.getBounds());
    if(bench!=null && bench.owner!=null && bench.owner.isAutoRefresh())bench.repaint();
  }

  public void setPixY(int setY,Rectangle r){
    if (setY<1 && r.height>0) yPosition=1;
      else if (setY>r.height-1 && r.height>0) yPosition = r.height-1;
      else yPosition = setY;
    super.setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
    if(constraint!=null){
        yPosition=(int)(bench.iheight/2.0-getY()*this.pixPerUnit);
    }
    if(bench.activeElement!=null) bench.activeElement.updateMySlaves();
  }

  public void setY(double y){
    setPixY((int)(bench.iheight/2.0-y*bench.pixPerUnit), bench.getBounds());
    if(bench!=null && bench.owner!=null && bench.owner.isAutoRefresh())bench.repaint();
  }

  public void setProperties(int sid, double alpha,double beta){
  if(sid==0 || sid==1){
    setXY(alpha, beta);
  } else if(sid==6){
    setX(alpha);
  } else if(sid==7){
    setY(beta);
  }
  }

  void adjustPosition(){
     xPosition=(int)(getX()*this.pixPerUnit);
     xPosition+=xDisplayOff;
     x=xPosition/(double)this.pixPerUnit;
     yPosition=(int)(bench.iheight/2.0-getY()*this.pixPerUnit);
     yPosition-=yDisplayOff;
     y=(bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit;
  }

  public void updateMySlaves(){
    Thing slave=null;
    for( Enumeration e=getSlaves().elements(); e.hasMoreElements();){
         slave= (Thing) e.nextElement();
         slave.setVarsFromMaster() ;
         if(slave instanceof OpticElement){
            ((OpticElement)slave).adjustPosition();
         }
    }
  }

  public int getPixX(){
    return xPosition;
  }

  public int getPixY(){
    return yPosition;
  };

  public double getTheta(){
    return 0.000;
  }

  public int getSpread(){
    return 0;
  }

  public void setSpread(int spreadTransfer){
    {};
  }

  public double getAngle(){
    return 0;
  }

  public void setAngle(double angleTransfer){
    {};
  }

  public int getSpacing(){
    return 0;
  }

  public void setSpacing(int spreadTransfer){
    {};
  }
  final public Color getRayColor(){return rayColor;}
  final public void setRayColor(Color c){rayColor=c;}
  final public void setElementColor(Color c){color=c;}

  public void setRayIncrement(double rayIncrementTransfer){
    {};
  }

  public double getRayIncrement(){
    return 0.0000;
  }

  public void setRaySlope(double raySlopeTransfer){
    {};
  }

  public double getRaySlope(){
    return 0.0000;
  }

  public String getType(){
    return "optic element";
  }

  public void setFocalLength(double fl, Rectangle r){
    {};
  }

  public void setOpeningSize(int os){
    {};
  }

  public int getOpeningSize(){
    return 0;
  }

  public double getFocalLength(){
    return 0.00000;
  }
/*
  public int getWidth(){
    return 0;
  }
*/

  public void setRadius(int rT,Rectangle r){
    {};
  }

  public int getR(){
    return 0;
  }

  public void setDirection(int dt){
    {};
  }

  public int getDirection(){
    return 0;
  }

final public void setNextElement(int ne){nextElement=ne;}
final  public int getNextElement(){return nextElement;}
final  public void setClipOn(boolean co){clipOn=co;}
final  public boolean getClipOn(){return clipOn;}

// data source methods

  public String[] getVarStrings(){return varStrings;}

  final public double[][] getVariables(){
     ds[0][0]=x;  //x
     ds[0][1]=y;  //y
     ds[0][2]=focalLength/bench.pixPerUnit;  //Fx
     ds[0][3]=indexOfRefraction;   //fy
     return ds;
  }
}
