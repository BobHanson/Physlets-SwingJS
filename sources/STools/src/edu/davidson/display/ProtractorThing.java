package edu.davidson.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.*;

import edu.davidson.tools.SApplet;

public class ProtractorThing extends Thing {
  private boolean insideTip1=false;
  private boolean insideTip2=false;
  private double angle1, angle2;
  private int spot=4;
  public boolean fixedBase=false;
  public boolean fixedlength=false;
  int len;
  Color color2=Color.green;

  public ProtractorThing(SApplet owner, SScalable sc, int s, double theta, double theta0, double x,double y){
    super(sc,x,y);
    varStrings= new String[]{"t","x","y","theta","theta0"};
    ds=new double[1][5];  // the datasource state variables t,x,y,angle1,angle2;
    this.s=s;             // the size will be the length of the protractor in pixels
    len=s;
    angle1=theta0;
    angle2=theta+theta0;
    color=Color.red;
    resizable=true;
    //noDrag=false;  // for testing
  }

  public void setXY(double x,double y){
      double xpixPerUnit=canvas.pixFromX(1)-canvas.pixFromX(0);
      double ypixPerUnit=-canvas.pixFromY(1)+canvas.pixFromY(0);
      if(insideTip1){
         double horz=x-this.x;
         double vert=y-this.y;
         if(horz==0 && vert==0) return;
         angle1=Math.atan2(vert,horz*xpixPerUnit/ypixPerUnit);
         return;
      }
      if(insideTip2){
         double horz=x-this.x;
         double vert=y-this.y;
         if(!fixedlength){
           if(horz==0 && vert==0) return;
           len= (int)(Math.sqrt(horz*horz*xpixPerUnit*xpixPerUnit+vert*vert*ypixPerUnit*ypixPerUnit));
           len=Math.max(len,spot);
         }
         if(horz==0 && vert==0) return;
         angle2=Math.atan2(vert,horz*xpixPerUnit/ypixPerUnit);
         return;
      }
      super.setXY(x,y);
  }

  public void setProperties(int sid, double alpha,double beta){
    if(sid==3){
      angle1=alpha;
      angle2=alpha+beta;
    } else  if(sid==4){
      insideTip1=true;
      insideTip2=false;
      setXY(alpha,beta);
      insideTip1=false;
    }else  if(sid==5){
      insideTip1=false;
      insideTip2=true;
      setXY(alpha,beta);
      insideTip2=false;
    }else if(sid==6){
     setX(alpha);
    }else if(sid==7){
     setY(beta);
    }
  }

  public final boolean isInsideThing(int xPix, int yPix){
      insideTip1=false;
      insideTip2=false;
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if (!noDrag && (Math.abs(xPix-ptX)<spot)&&(Math.abs(yPix-ptY)<spot))return true;
      if (resizable && !fixedBase &&
          (Math.abs(xPix-ptX- s*Math.cos(angle1))<spot) &&
          (Math.abs(yPix-ptY+ s*Math.sin(angle1))<spot)){
          insideTip1=true;
          return true;
      }
      if (resizable &&
          (Math.abs(xPix-ptX- len*Math.cos(angle2))<spot) &&
          (Math.abs(yPix-ptY+ len*Math.sin(angle2))<spot)){
          insideTip2=true;
          return true;
      }
      return false;
  }


  public void paint(Graphics osg){
      if(!visible)return;
      int ptX=(int)Math.round(canvas.pixFromX(x) )+xDisplayOff;
      int ptY=(int)Math.round(canvas.pixFromY(y) )-yDisplayOff;
      Color background=Color.white;
      if(canvas instanceof Panel) background=((Panel) canvas).getBackground();
      if(background==Color.black)osg.setColor(Color.white);
      else osg.setColor(Color.black);
      int x2= (int)(ptX+s*Math.cos(angle1));
      int y2= (int)(ptY-s*Math.sin(angle1));
      osg.drawLine(ptX,ptY,x2,y2);
      x2= (int)(ptX+len*Math.cos(angle2));
      y2= (int)(ptY-len*Math.sin(angle2));
      osg.setColor(color);
      osg.drawLine(ptX,ptY,x2,y2);
      x2= (int)(ptX+s*Math.cos(angle2));
      y2= (int)(ptY-s*Math.sin(angle2));
      int begin=(int)(180*angle1/Math.PI);
      double arc=angle2-angle1;
      if(arc>Math.PI)arc=arc-2*Math.PI;
      else if(arc<-Math.PI)arc=arc+2*Math.PI;
      int arcsize=Math.min(s,len);
      osg.drawArc(ptX-arcsize/2,ptY-arcsize/2,arcsize,arcsize,begin,(int)(180*arc/Math.PI));
      osg.setColor(Color.black);
      if(!noDrag){  // draw a hot spot on the base
        if(color!=Color.lightGray)osg.setColor(Color.lightGray);
        else setColor(Color.red);
        osg.fillOval(ptX-2,ptY-2,5,5);
        osg.setColor(color);
        osg.drawOval(ptX-2,ptY-2,5,5);
      }
      if(resizable){  // draw a hot spot on the tip
        if(color!=Color.lightGray)osg.setColor(Color.lightGray);
        else setColor(Color.red);
        x2= (int)(ptX+len*Math.cos(angle2));
        y2= (int)(ptY-len*Math.sin(angle2));
        osg.fillOval(x2-2,y2-2,5,5);
        osg.setColor(color);
        osg.drawOval(x2-2,y2-2,5,5);
      }
      if(resizable && !fixedBase){  // draw a hot spot on the tip
        if(color!=Color.lightGray)osg.setColor(Color.lightGray);
        else setColor(Color.red);
        x2= (int)(ptX+s*Math.cos(angle1));
        y2= (int)(ptY-s*Math.sin(angle1));
        osg.fillOval(x2-2,y2-2,5,5);
        osg.setColor(color);
        osg.drawOval(x2-2,y2-2,5,5);
      }
      if(label!=null){
        osg.setColor(Color.black);
        Font f=osg.getFont();
        osg.setFont(font);
        FontMetrics fm=osg.getFontMetrics(font);
        osg.drawString(label,ptX,ptY);
        osg.setFont(f);
      }
  }

  public void paintHighlight(Graphics osg){
    //paint(osg);
  }

  public double[][] getVariables(){
     ds[0][0]=time;  //t
     ds[0][1]=x;  //x
     ds[0][2]=y;  //y
     double arc=angle2-angle1;
     if(arc>Math.PI)arc=arc-2*Math.PI;
     else if(arc<-Math.PI)arc=arc+2*Math.PI;
     ds[0][3]=arc;  //protractor angle
     ds[0][4]=angle1;  //start angle
     return ds;
  }

}