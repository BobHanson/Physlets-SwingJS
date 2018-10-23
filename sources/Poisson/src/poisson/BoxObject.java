package poisson;

import java.awt.Color;
import java.awt.Graphics;



public final  class BoxObject extends PotentialObject {
  public BoxObject(PoissonPanel p,double x, double y, double volt) {
      super(p,x,y,volt);
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
    //g.fillRect(xpix-w/2,ypix-h/2,w,h);
    int i;
    for( i=0;i<=s;i++){
       g.drawRect(xpix-w/2+i,ypix-h/2+i,w-2*i+1,h-2*i+1);

    }
    if(!noDrag){ // don't draw the X
      g.setColor(Color.gray);
      g.drawLine(xpix+w/2-5,ypix+h/2-5,xpix+w/2+5,ypix+h/2+5);
      g.drawLine(xpix+w/2-5,ypix+h/2+5,xpix+w/2+5,ypix+h/2-5);
    }
   // g.drawString(""+v+" V",x+w/2-15,y+h/2-15);
    if(showCharge)p.paintCharge(this,g);
    //System.out.println("paintcharge rect "+this.getChargeType());

  }

  public boolean isInsideThing(int xpix,int ypix){
      boolean insideOuter=false;
      boolean outsideInner=false;
      int xlocation=canvas.pixFromX(x);
      int ylocation=canvas.pixFromY(y);
      if (xpix>=xlocation-w/2 && ypix>=ylocation-h/2 && xpix<=xlocation+w/2 && ypix<=ylocation+h/2) insideOuter=true;
        else return false;  // no need to continue since we are outside the box.
      if( ((w-2*s)<=0) ||((h-2*s)<=0)) return true;
      if(xpix<=xlocation-w/2+s || ypix<=ylocation-h/2+s || xpix>=xlocation+w/2-s || ypix>=ylocation+h/2-s)
          return true;
          else return false;
  }
}
