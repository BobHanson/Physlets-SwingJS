package poisson;


import java.awt.Color;
import java.awt.Graphics;

public final class RingObject extends PotentialObject {
 // double size=1;
  public RingObject(PoissonPanel p,double x, double y, double volt) {
      super(p,x,y,volt);
      s=30;
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
    int i;
    for( i=0;i<=s;i++){
       g.drawOval(xpix-w/2+i,ypix-h/2+i,w-2*i+1,h-2*i+1);

    }
    //i=0;
   //g.drawOval(xpix-w/2+i,ypix-h/2+i,w-2*i+1,h-2*i+1);
    //i=s;
    //g.drawOval(xpix-w/2+i,ypix-h/2+i,w-2*i+1,h-2*i+1);
    if(!noDrag){ // don't draw the X
      g.setColor(Color.gray);
      g.drawLine(xpix-5,ypix-5,xpix+5,ypix+5);
      g.drawLine(xpix-5,ypix+5,xpix+5,ypix-5);
    }
  //  g.drawString(""+v+" V",x+w/2-15,y+h/2-15);
    if(showCharge)p.paintCharge(this,g);

  }

  public boolean isInsideThing(int xPix,int yPix){
      boolean insideOuter=false;
      boolean outsideInner=false;
      double w2=w*w/4;
      double h2=h*h/4;
      int xlocation=canvas.pixFromX(x);
      int ylocation=canvas.pixFromY(y);
      double e=w2/h2; // excentricity
      double x2=(xPix-xlocation)*(xPix-xlocation);
      double y2=(yPix-ylocation)*(yPix-ylocation);
      if(x2+e*y2 <= w2)insideOuter=true;
          else return false; // no need to continue since we are outside the outer radius.
      if( (w-2*s)<=0 ||(h-2*s)<=0) return true;
      w2=(w-2*s)*(w-2*s)/4;
      h2=(h-2*s)*(h-2*s)/4;
      e=w2/h2; // excentricity
      if(x2+e*y2 >= w2)return true; else return false;
  }
}
