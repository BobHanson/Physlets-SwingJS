package eField4;

import java.awt.*;
import edu.davidson.numerics.Parser;

public class Box extends Thing {

    Box(OdeCanvas p, double x,double y,int width,int height){
      super(p,x,y,0,0);
      s=1;
      w=width;
      h=height;
      this.p=p;
      vars[0]=0; //time
      vars[1]=x;
      vars[2]=y;
      initVars[0]=0;
      initVars[1]=x;
      initVars[2]=y;
      ds=new double[1][8];
      varStrings= new String[]{"t","x","y","vx","vy","ax","ay","f"};
    }

    public void paint(Graphics osg){
      if(hideThing) return;
      int ptX=p.pixFromX(vars[1])-w/2+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-h/2-yDisplayOff;
      osg.setColor(color);
      for(int i=0;i<=s;i++){
        osg.drawRect(ptX+i,ptY+i,w-2*i,h-2*i);
      }
      //System.out.println("box flux:"+calcFlux());
    }
    public void paintHighlight(Graphics osg){
      if(hideThing) return;
      int ptX=p.pixFromX(vars[1])-w/2+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-h/2-yDisplayOff;
      if(color==Color.red) osg.setColor(Color.blue);
      else osg.setColor(Color.red);
      osg.drawRect(ptX,ptY,w,h);
      osg.drawRect(ptX-1,ptY-1,w+2,h+2);
    }

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=p.pixFromX(vars[1])+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }

  final double calcFlux(){
      if(w<2 || h<2)return 0.0;
      double sum1=0,sum2=0;
      int ptX=p.pixFromX(vars[1])-w/2;
      int ptY=p.pixFromY(vars[2])-h/2;
      double x1=0,y1=0,x2=0,y2=0;
      double x=0,y=0;
      double prec=4;

      y1=p.yFromPix(ptY);
      y2=p.yFromPix(ptY+h);
      double dy=(y1-y2)/h/prec;
      x1=p.xFromPix(ptX);
      x2=p.xFromPix(ptX+w);
      x=x1;
      double dx=(x2-x1)/w/prec;

      for(int i=0; i<w*prec;i++){ // transverse the x direction.
          sum1=sum1-p.dudy(x+dx,y1); //+geometry*p.getPoleFy(x+dx,y1,null);   // upper sum the Electric field y component.
          sum1=sum1-(-p.dudy(x,y2)); //+geometry*p.getPoleFy(x,y2,null));   // lower sum the Electric field y component.
          x+=dx;
      }
      sum1=(x2-x1)*sum1/2/w/prec;
      y=y2;
      for(int i=0; i<h*prec;i++){ // transverse the y direction
          sum2=sum2+(-p.dudx(x1,y+dy)); //+geometry*p.getPoleFx(x1,y+dy,null));  // left sum the Electric field x component.
          sum2=sum2-(-p.dudx(x2,y)); //+geometry*p.getPoleFx(x2,y,null));  // sum the Electric field x component.
          y+=dy;
      }
      sum2=(y2-y1)*sum2/2/h/prec;
      flux= sum1+sum2+4*Math.PI*enclosedCharge();
      return flux;
  }

  void calculateState(){
  // calculate special state variables such as flux or potential.
      calcFlux();
  }

  final public double[][] getVariables(){
     ds[0][0]=vars[0];  //t
     ds[0][1]=vars[1];  //x
     ds[0][2]=vars[2];  //y
     ds[0][3]= vars[3];  //vx
     ds[0][4]=vars[4];   //vy
     ds[0][5]=vars[5];   //ax
     ds[0][6]=vars[6];   //ay
     // flux
     ds[0][7] = calcFlux();
     return ds;
  }
}