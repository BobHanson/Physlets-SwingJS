package eField4;

import java.awt.*;
import edu.davidson.numerics.Parser;
import edu.davidson.tools.SUtil;

public class CursorThing extends Thing {

  public CursorThing(OdeCanvas o,int diameter, double x, double y) {
      super(o,x,y,0,0);
      s=1;
      w=diameter;
      h=diameter;
      noDrag=false;
  }

  public void paint(Graphics g){
      if(hideThing) return;
      int ptX=p.pixFromX(vars[1])+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-yDisplayOff;
      g.setColor(color);
      g.drawOval(ptX-w/2,ptY-h/2,w,h);
      g.drawLine(ptX-w/2,ptY,ptX-1,ptY);
      g.drawLine(ptX+1,ptY,ptX+w/2,ptY);
      g.drawLine(ptX,ptY-h/2,ptX,ptY-1);
      g.drawLine(ptX,ptY+1,ptX,ptY+h/2);

      super.paint(g);
    }

  public void paintHighlight(Graphics g){
      if(hideThing) return;
      int ptX=p.pixFromX(vars[1])+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-yDisplayOff;
      if(color==Color.red) g.setColor(Color.blue);
      else g.setColor(Color.red);
      g.drawOval(ptX-w/2,ptY-h/2,w,h);
      super.paintHighlight(g);
    }

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=p.pixFromX(vars[1])+xDisplayOff;
      int ptY=p.pixFromY(vars[2])-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }

  public final void paintGhosts(Graphics g){
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
