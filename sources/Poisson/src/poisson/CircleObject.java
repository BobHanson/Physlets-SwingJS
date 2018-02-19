package poisson;

import java.awt.Color;
import java.awt.Graphics;

//import java.awt.*;


public final class CircleObject extends PotentialObject {
/*
  public CircleObject(PoissonPanel p,int xe, int ye, double ve) {
      super(p,xe,ye,ve);
  }  */
  public CircleObject(PoissonPanel p,double xe, double ye, double ve) {
      super(p,xe,ye,ve);
  }

  public void paint(Graphics g){
    //hasChanged=false;
    if(!visible){
      if(showCharge)p.paintCharge(this,g);
      return;
    }
    g.setColor(color);
    int xpix=canvas.pixFromX(x);
    int ypix=canvas.pixFromY(y);
    g.fillOval(xpix-w/2,ypix-h/2,w,h);
    //g.fillOval(xlocation,ylocation,w,h);
    if(!noDrag){ // don't draw the X
      g.setColor(Color.white);
      g.drawLine(xpix-5,ypix-5,xpix+5,ypix+5);
      g.drawLine(xpix-5,ypix+5,xpix+5,ypix-5);
    }
  //  g.drawString(""+v+" V",x+w/2-15,y+h/2-15);
    if(showCharge)p.paintCharge(this,g);

  }

  public boolean isInsideThing(int xPix,int yPix){
      double w2=w*w/4;
      double h2=h*h/4;
      int xlocation=canvas.pixFromX(x);
      int ylocation=canvas.pixFromY(y);
      double e=w2/h2; // excentricity
      double x2=(xPix-xlocation)*(xPix-xlocation);
      double y2=(yPix-ylocation)*(yPix-ylocation);
      if(x2+e*y2 <= w2)return true;
      else return false;
  }
}