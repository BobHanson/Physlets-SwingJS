package animator4;

import java.awt.*;
import edu.davidson.tools.SUtil;

public class DrawnRectangle extends Thing {

  public DrawnRectangle(AnimatorCanvas o,int width, int height, String xStr, String yStr) {
      super(o,xStr,yStr);
      s=1;
      w=width;
      h=height;
  }

  public void paint(Graphics g){
      if(!visible)return;
      int ptX=canvas.pixFromX(vars[1])-w/2+xDisplayOff;
      int ptY=canvas.pixFromY(vars[2])-h/2-yDisplayOff;
      paintGhosts(g);
      g.setColor(color);
      g.fillRect(ptX,ptY,w,h);
      paintTrail(g);
      paintLabel( g, ptX+w/2, ptY+h/2);
      if (showCoordinates) paintCoordinates(g, ptX+w/2, ptY+h/2);
      super.paint(g);
  }

  public void paintHighlight(Graphics g){
      if(!visible)return;
      int ptX=canvas.pixFromX(vars[1])-w/2+xDisplayOff;
      int ptY=canvas.pixFromY(vars[2])-h/2-yDisplayOff;
      paintGhosts(g);
      if(color==highlightColor) g.setColor(lightBlue);
      else g.setColor(highlightColor);
      g.drawRect(ptX,ptY,w,h);
      g.drawRect(ptX-1,ptY-1,w+2,h+2);
   //   paintTrail(g);
    //  if (showCoordinates) paintCoordinates(g, ptX+w/2, ptY+h/2);
      super.paintHighlight(g);
  }

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(vars[1])+xDisplayOff;
      int ptY=canvas.pixFromY(vars[2])-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }

  public final void paintGhosts(Graphics g){
      if(!visible)return;
      // paint ghost images before the thing.
      if(ghost && footPrints>0 && trailSize>1 && trail.npoints>1){
          g.setColor(SUtil.veryPaleColor(color));
          for(int i=0;i<trail.npoints;i+=footPrints)
            g.fillRect((trail.xpoints[i]-w/2)+xDisplayOff,(trail.ypoints[i]-h/2)-yDisplayOff,w,h);
      }
  }
}