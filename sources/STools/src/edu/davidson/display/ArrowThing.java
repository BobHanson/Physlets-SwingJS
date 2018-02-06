package edu.davidson.display;

import java.awt.*;
import edu.davidson.tools.SApplet;

public class ArrowThing extends Thing {

  private double horz;   // the horz and vert components of the vector
  private double vert;
  public  int thickness=1;


  public ArrowThing(SApplet owner, SScalable sc, int s, double h, double v, double x, double y){
      super(sc,x,y);
      this.s=s;             // the size will be the size of the head.
      horz=h;
      vert=v;
      applet=owner;
  }

  protected double getHorz(){
      return horz;
  }

  protected double getVert(){
      return vert;
  }

  public void setProperties(int sid, double alpha,double beta){
    if(sid==0 || sid==1){
      setXY(alpha, beta);
    } else if(sid==2){
      horz=alpha;
      vert=beta;
    }else if(sid==6){
     setX(alpha);
    }else if(sid==7){
     setY(beta);
    }
  }

  public void paint(Graphics osg){
  if(!visible) return;
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      double xpixPerUnit=canvas.pixFromX(1)-canvas.pixFromX(0);
      double ypixPerUnit=-canvas.pixFromY(1)+canvas.pixFromY(0);
      double x=xpixPerUnit*getHorz(); // the x component;
      double y=ypixPerUnit*getVert(); // the y component;
      osg.setColor(color);
      int x2= (int)(ptX+x);
      int y2= (int)(ptY-y);

      osg.drawLine((ptX),(ptY),x2,y2);
      if(Math.abs(x)<1 && Math.abs(y)<1){
        return;
      }
      double h=Math.sqrt(x*x+y*y);
      double w;
      if (h>3*s)w=s; else w=h/3;
      if(thickness>1){
         edu.davidson.tools.SUtil.drawThickArrow(osg,ptX,ptY,x2,y2,(int)w, thickness);
         return;
      }
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
      if(!visible) return;
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      double pixPerUnit=canvas.pixFromX(1)-canvas.pixFromX(0);
      double x=pixPerUnit*horz; // the x component;
      double y=pixPerUnit*vert; // the y component;
      //osg.setColor(color);
      osg.setColor(color);
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

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if ((Math.abs(xPix-ptX)<5)&&(Math.abs(yPix-ptY)<5))  return true;
      else return false;
  }

}
