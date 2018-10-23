package eField4;
import java.awt.Polygon;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.tools.*;

public class Charge extends Thing implements Cloneable {

  public Charge(OdeCanvas p, double x,double y,double vx,double vy){
      this(p,x,y,vx,vy,1.0);
  }
  public Charge(OdeCanvas p, double x,double y,double vx,double vy, double m) {
      super(p,x,y,vx,vy,m);
      sticky=true;
      noDrag=false;
      this.p=p;
      vars[0]=0;
      vars[1]=x;
      vars[2]=y;
      vars[3]=vx;
      vars[4]=vy;
      initVars[0]=0;
      initVars[1]=x;
      initVars[2]=y;
      initVars[3]=vx;
      initVars[4]=vy;
      mag=m;
      showFVector=true;
      ds=new double[1][12];
      varStrings= new String[]{"t","x","y","vx","vy","ax","ay","fx","fy","p","m","q"};
  }

  public void setAcceleration(){  // override for charge
     vars[5]=mag*(-p.dudx(vars[1],vars[2])+p.getPoleFx(vars[1],vars[2],this)+p.bz*vars[4]);   // fx
     vars[6]=mag*(-p.dudy(vars[1],vars[2])+p.getPoleFy(vars[1],vars[2],this)-p.bz*vars[3]);   // fy
  }

  final public double[][] getVariables(){
     ds[0][0]=vars[0];  //t
     ds[0][1]=vars[1];  //x
     ds[0][2]=vars[2];  //y
     ds[0][3]= vars[3];  //vx
     ds[0][4]=vars[4];   //vy
     ds[0][7]=mag*(-p.dudx(vars[1],vars[2])+p.getPoleFx(vars[1],vars[2],this)+p.bz*vars[4]);   // fx
     ds[0][8]=mag*(-p.dudy(vars[1],vars[2])+p.getPoleFy(vars[1],vars[2],this)-p.bz*vars[3]);   // fy
     if(hasTrajectory()){
       ds[0][5]= vars[5];  //ax is given by the trajectory
       ds[0][6]= vars[6];   //ay
     }else{
       ds[0][5]= ds[0][7]/mass;  //ax is given by the gradient of the potential
       ds[0][6]= ds[0][8]/mass; //ay
     }
     // potential
     if(p.parser!=null) ds[0][9] = p.parser.evaluate(vars[1],vars[2])+p.getPoleU(vars[1],vars[2]);
      else ds[0][9]= p.getPoleU(vars[1],vars[2]);
     ds[0][10]= mass;
     ds[0][11]= mag;
     return ds;
  }

  double calcPE(){
      pe=p.getPE(this);
      return pe;
  }

  void calculateState(){
  // calculate special state variables such as flux or potential.
      calcPE();
  }

  public final Object clone(){
      Charge c=null;
      try{
          c=(Charge)super.clone();
          c.trail= new Polygon();
          c.font= new Font("Monospaced",Font.PLAIN,14);
          c.vars=(double [])vars.clone();
          c.initVars=(double [])initVars.clone();
          c.color=Color.black;
      }catch(CloneNotSupportedException e){return null;}
      return c;
  }

  public final void incTrail(){
      if (trail==null || trailSize<1) return;
      int x=p.pixFromX(vars[1]);
      int y=p.pixFromY(vars[2]);
      if (trail.npoints<trailSize){
          trail.addPoint(x,y);
      }else{
          /*
          for (int i=0; i<trailSize-1; i++){
              trail.xpoints[i]=trail.xpoints[i+1];
              trail.ypoints[i]=trail.ypoints[i+1];
          }  */
          System.arraycopy(trail.xpoints, 1, trail.xpoints, 0, trailSize-1);
          System.arraycopy(trail.ypoints, 1, trail.ypoints, 0, trailSize-1);
          trail.xpoints[trailSize-1]=x;
          trail.ypoints[trailSize-1]=y;
      }
	}

  public final Polygon getTrail(){return trail;}

  public final void resetTime(){
      vars[0]=initVars[0];
      vars[1]=initVars[1];
      vars[2]=initVars[2];
      vars[3]=initVars[3];
      vars[4]=initVars[4];
      vars[5]=initVars[5];
      vars[6]=initVars[6];
      clearTrail();
  }

  public final double getMaxU(){
      double x0=p.xFromPix(0);
      double x1=p.xFromPix(s);
      double r2=(x0-x1)*(x0-x1);
      if(r2!=0)return mag/Math.sqrt(r2);
      else return 0;
  }

  public final void paint(Graphics osg){
      if(hideThing) return;
      if(!p.showPoles && this instanceof Pole) return;
      int ptX=p.pixFromX(vars[1]);
      int ptY=p.pixFromY(vars[2]);
      // paint ghost images first
      if(ghost && footPrints>0 && trailSize>1 && trail.npoints>1){
          osg.setColor(SUtil.veryPaleColor(color));
          for(int i=0;i<trail.npoints;i+=footPrints)
            osg.fillOval((trail.xpoints[i]-s),(trail.ypoints[i]-s),2*s,2*s);
      }

      osg.setColor(color);
      if(p.pointChargeMode) osg.fillOval((ptX-s),(ptY-s),2*s,2*s);
      else { // we are in cylindrical coordinates!
         osg.setColor(SUtil.paleColor(color));
         osg.fillOval((ptX-s),(ptY-s),2*s,2*s);
         osg.setColor(color);
         if(label==null){
           osg.drawLine(ptX, ptY - s, ptX, ptY + s);
           osg.drawLine(ptX - s, ptY, ptX + s, ptY);
         }
         osg.drawOval((ptX-s),(ptY-s),2*s,2*s);
      }
      osg.setColor(lightGreen);
      if(showVComponents){
          int ptVX=p.pixFromX(vars[1]+vars[3]);
          int ptVY=p.pixFromY(vars[2]+vars[4]);
          SUtil.drawArrow(osg, ptX,ptY,ptVX, ptY);
          SUtil.drawArrow(osg, ptVX,ptY,ptVX, ptVY);
      }
      osg.setColor(darkGreen);
      if(showVVector){
          int ptVX=p.pixFromX(vars[1]+vars[3]);
          int ptVY=p.pixFromY(vars[2]+vars[4]);
          SUtil.drawArrow(osg, ptX,ptY,ptVX, ptVY);
      }
      osg.setColor(color);

      paintTrail(osg);
      paintConstraint( osg);
      if(label!=null){
          Font oldFont=osg.getFont();
          osg.setFont(font); // this font is used for messages and captions.
          osg.setColor(Color.white);
          osg.drawString(label, ptX-4, ptY+5);
          osg.setFont(oldFont);
          osg.setColor(color);
      }
  }

  public final double getMag(){return mag;}
  public final void setMag(double m){mag=m;}

  private final boolean isInsidePole(int xPix, int yPix, OdeCanvas p){
      int ptX=p.pixFromX(vars[1]);
      int ptY=p.pixFromY(vars[2]);
      if ((Math.abs(xPix-ptX)<s+1)&&(Math.abs(yPix-ptY)<s+1))  return true;
      else return false;
  }
  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=p.pixFromX(vars[1]);
      int ptY=p.pixFromY(vars[2]);
      if ((Math.abs(xPix-ptX)<s+1)&&(Math.abs(yPix-ptY)<s+1))  return true;
      else return false;
  }
/*
  public final boolean isInside(int xPix, int yPix, OdePanel p){
      if (noDrag) return false;
      int ptX=p.pixFromX(vars[1]);
      int ptY=p.pixFromY(vars[2]);
      if ((Math.abs(xPix-ptX)<s+1)&&(Math.abs(yPix-ptY)<s+1))  return true;
      else return false;
  }
 */
  public final boolean isInsidePole(double x, double y, OdeCanvas p){
      return isInsidePole(p.pixFromX(x),p.pixFromY(y),p);
  }

  public final boolean doesOverlap(Charge c){
      double r2;
      if((c.getMag()<0 && mag<0) || (c.getMag()>0 && mag>0)) // overlap large areas if the signs are like
          r2=(p.xFromPix(0)-p.xFromPix(s*2+3))*(p.xFromPix(0)-p.xFromPix(s*2+3));
      else
          r2=(p.xFromPix(0)-p.xFromPix(s+2))*(p.xFromPix(0)-p.xFromPix(s+2));
      double d2=( getX()-c.getX())*( getX()-c.getX())+ ( getY()-c.getY())*( getY()-c.getY());
      if(d2>r2)return false;else return true;
  }

  public final double getRadius(){
      double r= (p.xFromPix(0)-p.xFromPix(s+1))*(p.xFromPix(0)-p.xFromPix(s+1));
      r=Math.sqrt(r);
      return r;
  }

}