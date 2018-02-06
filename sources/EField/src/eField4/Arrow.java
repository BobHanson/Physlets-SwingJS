package eField4;

import java.awt.*;
import edu.davidson.numerics.Parser;

public class Arrow extends Thing {

  private String hStr;   // the horz and vert components of the vector
  private String vStr;
  private double[] tempVars= new double[8];
  Parser hFunc;
  Parser vFunc;

  public Arrow(OdeCanvas canvas, int s, String h, String v, double x, double y){

    super(canvas,x,y,0,0);
    varStrings= new String[]{"t","x","y","vx","vy","ax","ay","m", "horz", "vert"};
    ds=new double[1][10];  // the datasource state variables t,x,y,vx,vy,ax,ay;
    this.s=s;             // the size will be the size of the head.
    hStr=h;               // the h component of the arrow
    vStr=v;

    hFunc = new Parser(8);
    hFunc.defineVariable(1,"t"); // define the variable
    hFunc.defineVariable(2,"x"); // define the variable
    hFunc.defineVariable(3,"y"); // define the variable
    hFunc.defineVariable(4,"vx"); // define the variable
    hFunc.defineVariable(5,"vy"); // define the variable
    hFunc.defineVariable(6,"ax"); // define the variable
    hFunc.defineVariable(7,"ay"); // define the variable
    hFunc.defineVariable(8,"m"); // define the variable
    hFunc.define(hStr);
    hFunc.parse();
    if(hFunc.getErrorCode() != Parser.NO_ERROR){
      System.out.println("Failed to parse horzizontal component of vector: "+hStr);
      System.out.println("Parse error: " + hFunc.getErrorString() +
          " at position " + hFunc.getErrorPosition());
      return;
    }

    vFunc = new Parser(8);
    vFunc.defineVariable(1,"t"); // define the variable
    vFunc.defineVariable(2,"x"); // define the variable
    vFunc.defineVariable(3,"y"); // define the variable
    vFunc.defineVariable(4,"vx"); // define the variable
    vFunc.defineVariable(5,"vy"); // define the variable
    vFunc.defineVariable(6,"ax"); // define the variable
    vFunc.defineVariable(7,"ay"); // define the variable
    vFunc.defineVariable(8,"m"); // define the variable

    vFunc.define(vStr);
    vFunc.parse();
    if(vFunc.getErrorCode() != Parser.NO_ERROR){
      System.out.println("Failed to parse vertical component of vector: "+vStr);
      System.out.println("Parse error: " + vFunc.getErrorString() +
          " at position " + vFunc.getErrorPosition());
      return;
    }
  }

  double getHorz(double v[]){
    double h=0;
    tempVars[0]=v[0];
    tempVars[1]=v[1];
    tempVars[2]=v[2];
    tempVars[3]=v[3];
    tempVars[4]=v[4];
    tempVars[5]=v[5];
    tempVars[6]=v[6];
    tempVars[7]=mass;
    try{
        h=hFunc.evaluate(tempVars);
    }catch(Exception e){}
    return h;
  }

  double getVert(double v[] ){
    double val=0;
    tempVars[0]=v[0];
    tempVars[1]=v[1];
    tempVars[2]=v[2];
    tempVars[3]=v[3];
    tempVars[4]=v[4];
    tempVars[5]=v[5];
    tempVars[6]=v[6];
    tempVars[7]=mass;
    try{
        val=vFunc.evaluate(tempVars);
    }catch(Exception e){}
    return val;
  }

  public void paint(Graphics osg){
      if(hideThing) return;
      int ptX=(int)Math.round(p.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(p.pixFromY(vars[2]) )-yDisplayOff;
      double pixPerUnit=p.pixFromX(1)-p.pixFromX(0);
      double x=pixPerUnit*getHorz(vars); // the x component;
      double y=pixPerUnit*getVert(vars); // the y component;

      if(showVVector){
          x=pixPerUnit*vars[3];
          y=pixPerUnit*vars[4];
      }
      if(showAVector){
          x=pixPerUnit*vars[5];
          y=pixPerUnit*vars[6];
      }
      if(dynamic && showFVector){
          //x=pixPerUnit*getFx();
          //y=pixPerUnit*getFy();
          x=pixPerUnit*vars[5]*mass;
          y=pixPerUnit*vars[6]*mass;
      }
      if(!dynamic && showFVector){
          x=pixPerUnit*vars[5]*mass;
          y=pixPerUnit*vars[6]*mass;
      }
      osg.setColor(color);
      int x2= (int)(ptX+x);
      int y2= (int)(ptY-y);

      osg.drawLine((ptX),(ptY),x2,y2);
      double h=Math.sqrt(x*x+y*y);
      double w;
      if (h>3*s)w=s; else w=h/3;
      if(h>1){
             double u=(w*x/h);
             double v=-(w*y/h);
             double base_x= x2-3*u;
             double base_y= y2-3*v;
             osg.drawLine((int)(base_x-v),(int)(base_y+u),x2,y2);
             osg.drawLine((int)(base_x+v),(int)(base_y-u),x2,y2);
      }
  }

  public void paintHighlight(Graphics osg){
     if(hideThing) return;
      int ptX=(int)Math.round(p.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(p.pixFromY(vars[2]) )-yDisplayOff;
      double pixPerUnit=p.pixFromX(1)-p.pixFromX(0);
      double x=pixPerUnit*getHorz(vars); // the x component;
      double y=pixPerUnit*getVert(vars); // the y component;

      if(showVVector){
          x=pixPerUnit*vars[3];
          y=pixPerUnit*vars[4];
      }
      if(dynamic && showFVector){
          //x=pixPerUnit*getFx();
          //y=pixPerUnit*getFy();
          x=pixPerUnit*vars[5];
          y=pixPerUnit*vars[6];
      }
      if(!dynamic && showFVector){
          x=pixPerUnit*vars[5];
          y=pixPerUnit*vars[6];
      }
      //osg.setColor(color);
      osg.setColor(highlightColor);
      int x2= (int)(ptX+x);
      int y2= (int)(ptY-y);
     // a.addToTrail(x2,y2);
      osg.drawLine((ptX),(ptY),x2,y2);
      double h=Math.sqrt(x*x+y*y);
      double w;
      if (h>3*s)w=s; else w=h/3;
      if(h>1){
             double u=(w*x/h);
             double v=-(w*y/h);
             double base_x= x2-3*u;
             double base_y= y2-3*v;
             osg.drawLine((int)(base_x-v),(int)(base_y+u),x2,y2);
             osg.drawLine((int)(base_x+v),(int)(base_y-u),x2,y2);
      }
  }

  public double[][] getVariables(){
     double horz=0;
     double vert=0;

     if(showVVector){
          horz=vars[3];
          vert=vars[4];
      }
      else if(dynamic && showFVector){
          //horz=getFx();
          //vert=getFy();
          horz=vars[5];
          vert=vars[6];
      }
      else if(!dynamic && showFVector){
          horz=vars[5];
          vert=vars[6];
      }else{
          horz=getHorz(vars);
          vert=getVert(vars);
      }

     ds[0][0]=vars[0];  //t
     ds[0][1]=vars[1];  //x
     ds[0][2]=vars[2];  //y
     ds[0][3]=vars[3];  //vx
     ds[0][4]=vars[4];  //vy
     ds[0][5]=vars[5];  //ax
     ds[0][6]=vars[6];  //ay
     ds[0][7]=mass;  //mass
     ds[0][8]=horz;  //horz
     ds[0][9]=vert;  //vert

     return ds;
  }

}
