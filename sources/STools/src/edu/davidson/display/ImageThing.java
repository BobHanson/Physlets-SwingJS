package edu.davidson.display;

import java.awt.Image;
import java.awt.Component;

import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class ImageThing extends Thing {
  private Image image;
  private Component drawingComponent=null;
  public ImageThing(SApplet owner, SScalable sc, Image im, double x,double y){
    super(sc,x,y);
    image=im;
    s=1;
    applet=owner;
    if(sc instanceof Component){
        drawingComponent= (Component) sc;
        w=im.getWidth(drawingComponent);
        h=im.getHeight(drawingComponent);
    }
    else{
        drawingComponent=null;
    }
  }
  public final Image getImage(){return image;}

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(x)+xDisplayOff+w/2;
      int ptY=canvas.pixFromY(y)-yDisplayOff+h/2;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }

  public void paint(Graphics g){
      if(!visible || drawingComponent==null )return;
      w=image.getWidth(drawingComponent);
      h=image.getHeight(drawingComponent);
      if(w==-1 || h==-1) return;
      int ptX=(int)Math.round(canvas.pixFromX(x) )+xDisplayOff;
      int ptY=(int)Math.round(canvas.pixFromY(y) )-yDisplayOff;
      g.drawImage(image,ptX,ptY,drawingComponent);
      super.paint(g);
  }
}
