package poisson;

import java.awt.*;

public final  class RectObject extends PotentialObject {
  public RectObject(PoissonPanel p,double xe, double ye, double ve) {
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
    g.fillRect(xpix-w/2,ypix-h/2,w,h);
    //g.fillRect(xlocation,ylocation,w,h);
    if(!noDrag){ // don't draw the X
      g.setColor(Color.white);
      g.drawLine(xpix+w/2-5,ypix+h/2-5,xpix+w/2+5,ypix+h/2+5);
      g.drawLine(xpix+w/2-5,ypix+h/2+5,xpix+w/2+5,ypix+h/2-5);
    }
   // g.drawString(""+v+" V",x+w/2-15,y+h/2-15);
    if(showCharge)p.paintCharge(this,g);
    //System.out.println("paintcharge rect "+this.getChargeType());

  }
}