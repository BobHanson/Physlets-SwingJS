package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;

//import java.awt.*;
import edu.davidson.tools.SApplet;

public class CircleThing extends Thing {

    public CircleThing(SApplet owner, SScalable sc, double x,double y,int radius){
      super(sc,x,y);
      s=1;
      w=radius;
      h=radius;
      applet=owner;
    }

    public void paint(Graphics osg){
      if(!visible) return;
      int ptX=canvas.pixFromX(x)-w+xDisplayOff;
      int ptY=canvas.pixFromY(y)-w-yDisplayOff;
      osg.setColor(color);
      osg.fillOval(ptX,ptY,2*w+1,2*h+1);
    }

    public void paintHighlight(Graphics osg){
      if(!visible) return;
      int ptX=canvas.pixFromX(x)-w+xDisplayOff;
      int ptY=canvas.pixFromY(y)-w-yDisplayOff;
      if(color==Color.red) osg.setColor(Color.blue);
      else osg.setColor(Color.red);
      osg.fillOval(ptX,ptY,2*w+1,2*h+1);
      osg.fillOval(ptX-1,ptY-1,2*w+3,2*h+3);
    }

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w+1)&&(Math.abs(yPix-ptY)<w+1))  return true;
      else return false;
  }

}
