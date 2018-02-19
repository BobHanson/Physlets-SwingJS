package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;

//import java.awt.*;
import edu.davidson.tools.SApplet;


public class PhaseThing extends Thing {

    public PhaseThing(SApplet owner, SScalable sc, double x,double y,int radius){
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
      for(int theta=0; theta<357; theta=theta+4){
        osg.setColor(SGraph.colorFromDegrees(theta));
        osg.fillArc(ptX,ptY,2*w+1,2*w+1,theta,4);
      }
      osg.setColor(Color.black);
      osg.fillOval(ptX+w-2,ptY+w-2,5,5);
    }

    public void paintHighlight(Graphics osg){
      if(!visible) return;
      paint(osg);
    }

    public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w+1)&&(Math.abs(yPix-ptY)<w+1))  return true;
      else return false;
  }
}
