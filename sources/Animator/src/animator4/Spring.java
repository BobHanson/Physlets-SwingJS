package animator4;

import a2s.*;
import java.awt.Graphics;


import edu.davidson.numerics.Parser;

public class Spring extends Thing {

  private String hStr;   // the horz and vert components of the vector
  private String vStr;
  Parser hFunc;
  Parser vFunc;

  public Spring(AnimatorCanvas o, int s, String h, String v, String xStr, String yStr){

    super(o,xStr,yStr);
    this.s=s;             // the size will be the number of coils
    hStr=h;               // the h component of the arrow
    vStr=v;

    hFunc = new Parser(1);
    hFunc.defineVariable(1,"t"); // define the variable
    hFunc.define(hStr);
    hFunc.parse();
    if(hFunc.getErrorCode() != Parser.NO_ERROR){
      System.out.println("Failed to parse horzizontal component of vector: "+hStr);
      System.out.println("Parse error: " + hFunc.getErrorString() +
          " at function 1, position " + hFunc.getErrorPosition());
      return;
    }

    vFunc = new Parser(1);
    vFunc.defineVariable(1,"t"); // define the variable
    vFunc.define(vStr);
    vFunc.parse();
    if(vFunc.getErrorCode() != Parser.NO_ERROR){
      System.out.println("Failed to parse vertical component of vector: "+vStr);
      System.out.println("Parse error: " + vFunc.getErrorString() +
          " at function 1, position " + vFunc.getErrorPosition());
      return;
    }
  }

  double getHorz(double t){
    double h=0;
    try{
        h=hFunc.evaluate(t);
    }catch(Exception e){}
    return h;
  }

  double getVert(double t){
    double v=0;
    try{
        v=vFunc.evaluate(t);
    }catch(Exception e){}
    return v;
  }
  public void paint(Graphics g){
      if(!visible)return;
      int ptX=(int)Math.round(canvas.pixFromX(vars[1]) )+xDisplayOff;  // the base of the spring
      int ptY=(int)Math.round(canvas.pixFromY(vars[2]) )-yDisplayOff;
      double x=canvas.pixPerUnit*getHorz(vars[0]); // the x component;
      double y=canvas.pixPerUnit*getVert(vars[0]); // the y component;
      g.setColor(color);
      double h=Math.sqrt(x*x+y*y);
      if(h>1){
             //(u,v) is in the spring direction and of length h/16
             //(-v,u) is orthogonal to the direction and of length h/16
             double u=(x/16.0/s);
             double v=-(y/16.0/s);
             double u0=8*x/h;
             double v0=-8*y/h;
             double x1= ptX;
             double y1= ptY;
             double x2= x1+u;
             double y2= y1+v;
             double x3= x1+2*u;
             double y3= y1+2*v;
             for(int count=0; count<4*s; count++){
                 x2=x2-v0;  y2=y2+u0;
                 g.drawLine((int)(x1),(int)(y1),(int)(x2),(int)(y2));
                 g.drawLine((int)(x2),(int)(y2),(int)(x3),(int)(y3));
                 x1=x3;     y1=y3;
                 x2=x1+u;   y2=y1+v;
                 x3=x1+2*u; y3=y1+2*v;
                 x2=x2+v0;  y2=y2-u0;
                 g.drawLine((int)(x1),(int)(y1),(int)(x2),(int)(y2));
                 g.drawLine((int)(x2),(int)(y2),(int)(x3),(int)(y3));
                 x1=x3;     y1=y3;
                 x2=x1+u;   y2=y1+v;
                 x3=x1+2*u; y3=y1+2*v;
             }
      }
  }
}
