package animator4;

//import a2s.*;
import java.awt.Graphics;

import java.awt.Image;


public class ImageThing extends Thing {
  private Image image;
  public ImageThing(AnimatorCanvas o, Image im, String xStr,String yStr){
    super(o,xStr,yStr);
    image=im;
    w=im.getWidth(canvas);
    h=im.getHeight(canvas);
  }
  public final Image getImage(){return image;}

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(vars[1])+xDisplayOff+w/2;
      int ptY=canvas.pixFromY(vars[2])-yDisplayOff+h/2;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }

  public void paint(Graphics g){
      try{
        if(!visible)return;
        w=image.getWidth(canvas);
        h=image.getHeight(canvas);
        if(w==-1 || h==-1) return;
        int ptX=(int)Math.round(canvas.pixFromX(vars[1]) )+xDisplayOff;
        int ptY=(int)Math.round(canvas.pixFromY(vars[2]) )-yDisplayOff;
        g.drawImage(image,ptX,ptY,canvas);
        paintTrail(g);
        if (showCoordinates) paintCoordinates(g, ptX+w/2, ptY+h/2);
        super.paint(g);
      }catch(Exception ex){}
  }
}