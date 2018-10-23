package eField4;

import java.awt.Graphics;
import java.awt.Image;


public class ImageThing extends Thing {
  private Image image;
  public ImageThing(OdeCanvas p, Image im, double x,double y){
    super(p,x,y,0,0);
    image=im;
    w=im.getWidth(p);
    h=im.getHeight(p);
    noDrag=true;
  }
  public final Image getImage(){return image;}

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=p.pixFromX(vars[1])+xDisplayOff+w/2;
      int ptY=p.pixFromY(vars[2])-yDisplayOff+h/2;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }

  public void paint(Graphics g){
     if(hideThing) return;
      w=image.getWidth(p);
      h=image.getHeight(p);
      if(w==-1 || h==-1) return;
      int ptX=(int)Math.round(p.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(p.pixFromY(vars[2]) )-yDisplayOff;
      g.drawImage(image,ptX,ptY,p);
      paintTrail(g);
      //if (showCoordinates) paintCoordinates(g, ptX+w/2, ptY+h/2);
      super.paint(g);
  }
}