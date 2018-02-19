package edu.davidson.display;

import java.awt.Color;
//import java.awt.*;
import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class BoxThing extends Thing {

    public BoxThing(SApplet owner, SScalable sc, double x,double y,int width,int height){
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
      for(int i=0;i<=s;i++){
        osg.drawRect(ptX+i,ptY+i,w-2*i,h-2*i);
      }
      //System.out.println("box flux:"+calcFlux());
    }
    public void paintHighlight(Graphics osg){
      if(!visible) return;
      int ptX=canvas.pixFromX(x)-w/2+xDisplayOff;
      int ptY=canvas.pixFromY(y)-h/2-yDisplayOff;
      if(color==Color.red) osg.setColor(Color.blue);
      else osg.setColor(Color.red);
      osg.drawRect(ptX,ptY,w,h);
      osg.drawRect(ptX-1,ptY-1,w+2,h+2);
    }

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }
}
