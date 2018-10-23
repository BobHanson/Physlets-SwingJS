package edu.davidson.display;
import java.awt.Color;
import java.awt.Graphics;


import edu.davidson.tools.SApplet;

public class Constraint extends Thing {
    double left,right,top,bottom;
    public Constraint(SApplet owner, SScalable sc,double l,double r,double b, double t){
      super(sc,0,0);
      applet=owner;
      left=l;
      right=r;
      top=t;
      bottom=b;
      visible=true;
      color=Color.black;
    }

    public void setProperties(int sid, double a, double b){
      double w2=(right-left)/2.0;
      double h2=(top-bottom)/2.0;
      if(sid==0 || sid==1){
        left=a-w2;
        right=a+w2;
        top= b+h2;
        bottom=b-h2;
      } else if(sid==6){
        left=a-w2;
        right=a+w2;
      }else if(sid==7){
        top= b+h2;
        bottom=b-h2;
      }
    }

    public void paint(Graphics osg){
      if(!visible) return;
      int ptX=canvas.pixFromX(left);
      int ptY=canvas.pixFromY(top);
      w=canvas.pixFromX(right)-ptX;
      h=canvas.pixFromY(bottom)-ptY;
      osg.setColor(color);
      if(w>0 && h>0)
        osg.drawRect(ptX,ptY,w,h);
      else if(w>0 && h==0) osg.drawLine(ptX,ptY,ptX+w,ptY);
      else if(w==0 && h>0) osg.drawLine(ptX,ptY,ptX,ptY+h);
    }

    public void paintHighlight(Graphics osg){
      paint(osg);
    }
    public void enforceConstraint(Thing t){
      double x=t.x;
      double y=t.y;
      if(right>left){
        x=Math.max(left,x);
        x=Math.min(right,x);
        if(top==bottom)y=top;
      }
      if(top>bottom){
        y=Math.max(bottom,y);
        y=Math.min(top,y);
        if(left==right)x=left;
      }
      t.x=x;
      t.y=y;
    }

  public final boolean isInsideThing(int xPix, int yPix){return false;}
}