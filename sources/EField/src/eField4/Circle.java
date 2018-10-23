package eField4;

import java.awt.Color;
import java.awt.Graphics;


public class Circle extends Thing {

    Circle(OdeCanvas p, double x,double y,int radius){
      super(p,x,y,0,0);
      s=1;
      w=radius;
      h=radius;
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
      int ptX=p.pixFromX(vars[1])-w+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-w-yDisplayOff;
      osg.setColor(color);
      osg.fillOval(ptX,ptY,2*w+1,2*h+1);
      //System.out.println("circle flux:"+calcFlux());
    }

    public void paintHighlight(Graphics osg){
      if(hideThing) return;
      int ptX=p.pixFromX(vars[1])-w+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-w-yDisplayOff;
      if(color==Color.red) osg.setColor(Color.blue);
      else osg.setColor(Color.red);
      osg.fillOval(ptX,ptY,2*w+1,2*h+1);
      osg.fillOval(ptX-1,ptY-1,2*w+3,2*h+3);
    }

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=p.pixFromX(vars[1])+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w+1)&&(Math.abs(yPix-ptY)<w+1))  return true;
      else return false;
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

  final double calcFlux(){
      // bug!  calcFlux does not work if there is a displayOffset.
      int numPts=Math.max(50,(int)(4*Math.PI*w));
      double sum=0;
      int ptX=p.pixFromX(vars[1]);  // the center of the circle
      double r=p.xFromPix(ptX+w)-p.xFromPix(ptX); // the radius of the circle in world units.
      double x,y;
      double theta=0;
      double dTheta=2*Math.PI/(numPts);
      for(int i=0; i<numPts;i++){
          x=vars[1]+r*Math.cos(theta);
          y=vars[2]+r*Math.sin(theta);
          sum=sum+Math.cos(theta)*(-p.dudx(x,y)); //+geometry*p.getPoleFx(x,y,null));  // sum the Electric field x component.
          sum=sum-Math.sin(theta)*(p.dudy(x,y)); //+geometry*p.getPoleFy(x,y,null));   // sum the Electric field y component.
          theta+=dTheta;
      }
      flux=Math.PI*r*sum/numPts+Math.PI*enclosedCharge()*4;
      return flux;
  }

}
