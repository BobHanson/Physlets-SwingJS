package poisson;

//import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;


public class PotentialObject extends edu.davidson.display.Thing{
  static final int CONDUCTOR_TYPE=0;
  static final int DENSITY_TYPE=1;
  static final int DIELECTRIC_TYPE=2;
  private int type=CONDUCTOR_TYPE;

  PoissonPanel p=null;
  double v;      // the voltage or charge density of the object
  boolean showCharge=false;
  double constantQ=1;
  boolean isConstantQ=false;

  public PotentialObject( PoissonPanel p, double x, double y, double v) {
      super(p);
      varStrings= new String[]{"x","y","q","v","p"};
      ds=new double[1][5];  // the datasource state variables x,y,charge,voltage,potential energy;
      noDrag=false;
      color=Color.darkGray;
      w=50;
      h=50;
      this.p=p;
      this.x = x;
      this.y = y;
      setV(v);
  }

  public void paint(Graphics g){
  }

  public void paintActive(Graphics g){
    if(noDrag && ! resizable)return;
    int xlocation=canvas.pixFromX(x);
    int ylocation=canvas.pixFromY(y);
  //  g.setColor(new Color(255,128,128) );
    if(resizable){
      g.setColor(Color.black);
      g.drawRect(xlocation-w/2,ylocation-h/2,w,h);
      g.drawRect(xlocation+1-w/2,ylocation+1-h/2,w-2,h-2);
    }else{
      g.setColor(Color.darkGray);
      g.drawRect(xlocation-w/2,ylocation-h/2,w,h);
    }

    if(!noDrag){
      g.drawRect(xlocation-10,ylocation-10,20,20);
      g.drawRect(xlocation-10+1,ylocation-10+1,20-2,20-2);
    }
    if(resizable){
      g.fillRect(xlocation+w/2-1,ylocation-5,6,10);
      g.fillRect(xlocation-5,ylocation+h/2-1,10,6);
      g.fillRect(xlocation+w/2,ylocation+h/2,5,5);
    }
   // g.drawString(""+v+" V",x+w/2-15,y+h/2-15);
   // g.drawString(""+v+" V",x+w/2-15,y-15);
  }

  public final void setPosition(int xe,int ye){
    // set the position using the pixel coorinates.
    int xlocation=canvas.pixFromX(x);  // the current coordinates.
    int ylocation=canvas.pixFromY(y);
    if( xlocation==xe && ylocation==ye) return;
    x=canvas.xFromPix(xe);
    y=canvas.yFromPix(ye);
    if(constraint!=null)constraint.enforceConstraint(this);
    updateMySlaves();
    //hasChanged=true;
  }

  final private void calcColor(){
      if(type==PotentialObject.DENSITY_TYPE){
          if(v<0) color=new Color(191,191,255);
          else if(v>0)color= new Color(255,191,191);
          else color=Color.darkGray;
      }else if(type==PotentialObject.DIELECTRIC_TYPE){
          color=new Color(191,191,191);
      }else{ // conductor
          if(v<0) color=Color.blue;
          else if(v>0) color=Color.red;
          else color=Color.black;
      }
  }

  public double getCharge(){
     return p.calcCharge(this);
  }

  public double getPotentialEnergy(){
     return p.calcPotentialEnergy(this);
  }

  final public double getPotential(){return v;}


  final public double getQ(){
      return constantQ;
  }

  final public void setConstantQ(double q,boolean set){
    if(getChargeType()!=CONDUCTOR_TYPE){
        isConstantQ=false;
        return;
    }
    constantQ=q;
    isConstantQ=set;
  }

  public double[][] getVariables(){
     ds[0][0]=x;  //x
     ds[0][1]=y;  //y
     ds[0][2]=p.calcCharge(this);  //charge
     ds[0][3]=v;  //volatge
     ds[0][4]=p.calcPotentialEnergy(this);  //potential energy
     return ds;
  }

  final public void setChargeType(int t){
      this.type=t;
      calcColor();
  }

  final public int getChargeType(){
     return type;
  }

  final void setShowCharge(boolean sc){
     showCharge=sc;
  }

  public void setHeight(int h){
      //hasChanged=true;
      h=Math.abs(h);
      this.h=Math.max(h,(int)p.yPixPerCell*2);
  }
  public void setWidth(int w){
     w=Math.abs(w);
     //hasChanged=true;
     this.w=Math.max(w,(int)p.xPixPerCell*2);
  }


  public void setV(double ve){
    if(ve==v) return;
    v = ve;
    calcColor();
    //hasChanged=true;
  }

  public void setVoltage(double ve){
    if(type!=PotentialObject.CONDUCTOR_TYPE){
       System.out.println("Error: trying to set the voltage of a non-conductor." );
       return;
    }
    if(ve==v) return;
    v = ve;
  }

  public void setChi(double chi){
    if(type!=PotentialObject.DIELECTRIC_TYPE){
       System.out.println("Error: trying to set chi for a non-dielectric." );
       return;
    }
    if(chi<0){
       System.out.println("Error: must be >0." );
       return;
    }
    v = chi;
  }

  public void setChargeDensity(double q){
    if(type!=PotentialObject.DENSITY_TYPE){
       System.out.println("Error: trying to set the chage density." );
       return;
    }
    v = q;
  }

  void setConductors(PoissonPanel p){
  }

  public boolean isInsideThing(int xpix,int ypix){
    int xlocation=canvas.pixFromX(x);
    int ylocation=canvas.pixFromY(y);
    if (xpix>=xlocation-w/2 && ypix>=ylocation-h/2 && xpix<=xlocation+w/2 && ypix<=ylocation+h/2) return true;
    else return false;
   // if(isInside(xpix,ypix)==0) return false;
   // else return true;
  }

  public int isInsideHotSpot(int xpix,int ypix){
    if (noDrag && !resizable ) return 0;
    int xlocation=canvas.pixFromX(x);
    int ylocation=canvas.pixFromY(y);
    //if (xpix>=x && ypix>=y && xpix<=x+w && ypix<=y+h) return 1;   //drag object, center
    if (resizable && xpix>=xlocation+w/2 && ypix>=ylocation+h/2 && xpix<=xlocation+w/2+5 && ypix<=ylocation+h/2+5) return 4;  //change w and h, lower right corner
    else if (!noDrag && xpix>=xlocation-10 && ypix>=ylocation-10 && xpix<=xlocation+10 && ypix<=ylocation+10) return 1;   //drag object, center
    else if (resizable && xpix>=xlocation+w/2-1 && ypix>=ylocation-5 && xpix<=xlocation+w/2-1+6 && ypix<=ylocation+5) return 2; //change w, right side
    else if (resizable && ypix>=ylocation+h/2-1 && xpix>=xlocation-5 && ypix<=ylocation+h/2-1+6 && xpix<=xlocation+5) return 3;  //change h, bottom side
    else if (xpix>=xlocation-w/2 && ypix>=ylocation-h/2 && xpix<=xlocation+w/2 && ypix<=ylocation+h/2) return 5;   //for matrix underneath objects
    else return 0;           //not inside anything
  }
/*
  public boolean isPixInside(int xPix,int yPix){
      int xlocation=canvas.pixFromX(x);
      int ylocation=canvas.pixFromY(y);
     if( (xPix>xlocation-w/2) && (xPix <xlocation+w/2) && (yPix>ylocation-h/2) && (yPix<ylocation+h/2)) return true;
     else return false;
  }
*/
}