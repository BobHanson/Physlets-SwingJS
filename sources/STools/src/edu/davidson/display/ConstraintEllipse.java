package edu.davidson.display;
import java.awt.Graphics;

//import java.awt.*;
import edu.davidson.tools.SApplet;

public class ConstraintEllipse extends Constraint {
    public ConstraintEllipse(SApplet owner, SScalable sc,double l,double r,double b, double t){
      super( owner,  sc, l, r, b,t);
    }

    public void paint(Graphics osg){
      if(!visible) return;
      int ptX=canvas.pixFromX(left);
      int ptY=canvas.pixFromY(top);
      w=canvas.pixFromX(right)-ptX;
      h=canvas.pixFromY(bottom)-ptY;
      osg.setColor(color);
      if(w>0 && h>0)
        osg.drawOval(ptX,ptY,w,h);
      else if(w>0 && h==0) osg.drawLine(ptX,ptY,ptX+w,ptY);
      else if(w==0 && h>0) osg.drawLine(ptX,ptY,ptX,ptY+h);
    }

    public void enforceConstraint(Thing t){
      double x=t.x;
      double y=t.y;
      double a=Math.abs(right-left)/2.0;  // semimajor axis
      double b=Math.abs(top-bottom)/2.0;  // semiminor axis
      if(a>0 && b>0){  // project onto the ellipse
        double theta=Math.atan2(x,y);
        double cos=Math.cos(theta);
        double sin=Math.sin(theta);
        double d=1.0/Math.sqrt(cos*cos/a/a+sin*sin/b/b);
        t.x=d*Math.sin(theta);
        t.y=d*Math.cos(theta);
      }else if(a==0 && b>0){
        t.x=right;
      }else if(b==0 && a>0){
        t.y=top;
      }else{
        t.x=right;
        t.y=top;
      }
    }
}