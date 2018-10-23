package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;


import edu.davidson.tools.SApplet;

public class RectangleThing extends Thing {

    public RectangleThing(SApplet owner, SScalable sc, double x,double y,int width,int height){
      super(sc,x,y);
      s=1;
      w=width;
      h=height;
      applet=owner;
    }

    public void paint(Graphics osg){
      if(!visible) return;
      int ptX=canvas.pixFromX(x)-w/2+xDisplayOff;
      int ptY=canvas.pixFromY(y)-h/2-yDisplayOff;
      osg.setColor(color);
      osg.fillRect(ptX,ptY,w,h);
      //System.out.println("rect flux:"+calcFlux());
    }

    public void paintHighlight(Graphics osg){
      if(!visible) return;
      int ptX=canvas.pixFromX(x)-w/2+xDisplayOff;
      int ptY=canvas.pixFromY(y)-h/2-yDisplayOff;
      if(color==Color.black) osg.setColor(Color.red);
      else osg.setColor(Color.black);
      osg.drawRect(ptX,ptY,w,h);
      osg.drawRect(ptX-1,ptY-1,w+2,h+2);
    }

    public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if (w<3 && ((Math.abs(xPix-ptX)<w/2+3)&&(Math.abs(yPix-ptY)<h/2+3)) )  return true;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
    }
}
