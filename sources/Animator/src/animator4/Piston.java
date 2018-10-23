package animator4;


import java.awt.*;
import java.awt.Graphics;

import edu.davidson.numerics.Parser;


public class Piston extends Thing {

  private String hStr;   // the horz and vert components of the vector
  private String vStr;
  Parser hFunc;
  Parser vFunc;

  public Piston(AnimatorCanvas o, int s, String h, String v, String xStr, String yStr){

    super(o,xStr,yStr);
    this.s=s;             // the size will be the size of the head.
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
      int ptX=(int)Math.round(canvas.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(canvas.pixFromY(vars[2]) )-yDisplayOff;
      double x=canvas.pixPerUnit*getHorz(vars[0]); // the x component;
      double y=canvas.pixPerUnit*getVert(vars[0]); // the y component;
      g.setColor(color);
      g.setColor(color);
      int x2= (int)(ptX+x);
      int y2= (int)(ptY-y);
      double h=Math.sqrt(x*x+y*y); // the length of the piston
      double w=s/2;      // the width of the piston
      double u=(w*x/h);
      double v=-(w*y/h);
      g.fillPolygon(new int[]{(int)(ptX-v),(int)(x2-v),(int)(x2+v),(int)(ptX+v),(int)(x2+v)},new int[]{(int)(ptY+u),(int)(y2+u),(int)(y2-u),(int)(ptY-u)} , 4);
      super.paint(g);

  }
}
