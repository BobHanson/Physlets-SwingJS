package edu.davidson.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

//import java.awt.*;
import edu.davidson.tools.SApplet;

public class TangentThing extends Thing {
     double rise=1, run=1;

    public TangentThing(SApplet owner, SScalable sc, double x,double y,double run, double rise){
      super(sc,x,y);
      s=1;
      this.rise=rise;
      this.run=run;
      applet=owner;
      this.resizable=true;
    }

    public void paint(Graphics g){
      if(!visible) return;
      int ptX1=canvas.pixFromX(x)+xDisplayOff+(int)run;
      int ptY1=canvas.pixFromY(y)-yDisplayOff-(int)rise;
      int ptX2=canvas.pixFromX(x)+xDisplayOff-(int)run;
      int ptY2=canvas.pixFromY(y)-yDisplayOff+(int)rise;
      g.setColor(color);
      g.drawLine(ptX1,ptY1,ptX2,ptY2);
      if(!noDrag){
        g.drawOval((ptX1+ptX2)/2-1,(ptY1+ptY2)/2-1,3,3);
      }
      if(resizable){
        g.drawOval(ptX1-1,ptY1-1,3,3);
        g.drawOval(ptX2-1,ptY2-1,3,3);
      }

      Font f=g.getFont();
      g.setFont(font);
      if(run==0) g.drawString("m=infinite",(ptX1+ptX2)/2,(ptY1+ptY2)/2);
      else g.drawString("m="+format.form(rise/run),(ptX1+ptX2)/2,(ptY1+ptY2)/2);
      g.setColor(Color.black);
    }

    public void paintHighlight(Graphics g){
      if(!visible) return;
      int ptX1=canvas.pixFromX(x)+xDisplayOff+(int)run;
      int ptY1=canvas.pixFromY(y)-yDisplayOff-(int)rise;
      int ptX2=canvas.pixFromX(x)+xDisplayOff-(int)run;
      int ptY2=canvas.pixFromY(y)-yDisplayOff+(int)rise;
      g.setColor(color);
      g.drawLine(ptX1,ptY1,ptX2,ptY2);
      if(!noDrag){
        g.drawOval((ptX1+ptX2)/2-1,(ptY1+ptY2)/2-1,3,3);
      }
      if(resizable){
        g.drawOval(ptX1-1,ptY1-1,3,3);
        g.drawOval(ptX2-1,ptY2-1,3,3);
      }

      Font f=g.getFont();
      g.setFont(font);
      if(run==0) g.drawString("m=infinite",(ptX1+ptX2)/2,(ptY1+ptY2)/2);
      else g.drawString("m="+format.form(rise/run),(ptX1+ptX2)/2,(ptY1+ptY2)/2);
      g.setColor(Color.black);
    }

    public final boolean isInsideThing(int xPix, int yPix){
      int ptX1=canvas.pixFromX(x)+xDisplayOff+(int)run;
      int ptY1=canvas.pixFromY(y)-yDisplayOff-(int)rise;
      int ptX2=canvas.pixFromX(x)+xDisplayOff-(int)run;
      int ptY2=canvas.pixFromY(y)-yDisplayOff+(int)rise;
      int ptX=(ptX1+ptX2)/2;
      int ptY=(ptY1+ptY2)/2;
      if (!noDrag && (Math.abs(xPix-ptX)<3)&&(Math.abs(yPix-ptY)<3)){
          hotSpot=0;
          return true;
      }
      if (resizable && (Math.abs(xPix-ptX1)<3)&&(Math.abs(yPix-ptY1)<3)){
          hotSpot=1;
          return true;
      }
      if (resizable && (Math.abs(xPix-ptX2)<3)&&(Math.abs(yPix-ptY2)<3)){
          hotSpot=2;
          return true;
      }
      return false;
    }

    public void dragMe(double xnew,double ynew){
      //System.out.println("hotSpot="+hotSpot);
      if(hotSpot==0)setXY(xnew,ynew);
      else if(hotSpot==1){
         run=canvas.pixFromX(xnew)-canvas.pixFromX(x);
         rise=-canvas.pixFromY(ynew)+canvas.pixFromY(y);
      }else if(hotSpot==2){
         run=-canvas.pixFromX(xnew)+canvas.pixFromX(x);
         rise=+canvas.pixFromY(ynew)-canvas.pixFromY(y);
      }
    }

}