package animator4;

import a2s.*;

import java.awt.Color;
import java.awt.Graphics;

import edu.davidson.tools.SUtil;

public class CursorThing extends Thing {

  public CursorThing(AnimatorCanvas o,int diameter, String xStr, String yStr) {
      super(o,xStr,yStr);
      s=1;
      w=diameter;
      h=diameter;
      noDrag=false;
  }

  public void paint(Graphics g){
      if(!visible)return;
      int ptX=canvas.pixFromX(vars[1])+xDisplayOff;
      int ptY=canvas.pixFromY(vars[2])-yDisplayOff;
      g.setColor(color);
      g.drawOval(ptX-w/2,ptY-h/2,w,h);
      g.drawLine(ptX-w/2,ptY,ptX-1,ptY);
      g.drawLine(ptX+1,ptY,ptX+w/2,ptY);
      g.drawLine(ptX,ptY-h/2,ptX,ptY-1);
      g.drawLine(ptX,ptY+1,ptX,ptY+h/2);
      if (showCoordinates) paintCoordinates(g, ptX, ptY);
      super.paint(g);
    }

  public void paintHighlight(Graphics g){
      if(!visible)return;
      int ptX=canvas.pixFromX(vars[1])+xDisplayOff;
      int ptY=canvas.pixFromY(vars[2])-yDisplayOff;
      if(color==Color.red) g.setColor(Color.blue);
      else g.setColor(Color.red);
      g.drawOval(ptX-w/2,ptY-h/2,w,h);
      if (showCoordinates) paintCoordinates(g, ptX, ptY);
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
          for(int i=0;i<trail.npoints;i+=footPrints){
            //g.fillOval((trail.xpoints[i]-w/2),(trail.ypoints[i]-h/2),w,h);
            int ptX=trail.xpoints[i]+xDisplayOff;
            int ptY=trail.ypoints[i]-yDisplayOff;
            g.drawOval(ptX-w/2,ptY-h/2,w,h);
            g.drawLine(ptX-w/2,ptY,ptX-1,ptY);
            g.drawLine(ptX+1,ptY,ptX+w/2,ptY);
            g.drawLine(ptX,ptY-h/2,ptX,ptY-1);
            g.drawLine(ptX,ptY+1,ptX,ptY+h/2);
          }
      }
  }
}