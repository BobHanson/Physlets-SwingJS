package edu.davidson.display;

import java.awt.Color;
//import java.awt.*;
import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class MarkerThing extends Thing {

  boolean longCross=false;

  public MarkerThing(SApplet owner, SScalable sc,int diameter, double x, double y) {
      super(sc,x,y);
      s=1;
      w=diameter;
      h=diameter;
      noDrag=false;
      applet=owner;
  }

  public void paint(Graphics g){
      if(!visible) return;
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      g.setColor(color);
      g.drawOval(ptX-w/2,ptY-h/2,w,h);
      if(longCross){
          g.drawLine(0,ptY,ptX-1,ptY);
          g.drawLine(ptX+1,ptY,canvas.getPixWidth(),ptY);
          g.drawLine(ptX,0,ptX,ptY-1);
          g.drawLine(ptX,ptY+1,ptX,canvas.getPixHeight());
      }else{
          g.drawLine(ptX-w/2,ptY,ptX-1,ptY);
          g.drawLine(ptX+1,ptY,ptX+w/2,ptY);
          g.drawLine(ptX,ptY-h/2,ptX,ptY-1);
          g.drawLine(ptX,ptY+1,ptX,ptY+h/2);
      }
    }

  public void paintHighlight(Graphics g){
      if(!visible) return;
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if(color==Color.red) g.setColor(Color.blue);
      else g.setColor(Color.red);
      g.drawOval(ptX-w/2,ptY-h/2,w,h);
    }

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }
}
