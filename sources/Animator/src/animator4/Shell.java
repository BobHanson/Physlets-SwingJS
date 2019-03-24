

package animator4;
//import a2s.*;
import java.awt.Graphics;

import edu.davidson.tools.*;

public class Shell extends Thing {

  public Shell(AnimatorCanvas o,int diameter, String xStr, String yStr) {
      super(o,xStr,yStr);
      s=1;
      w=diameter;
      h=diameter;
  }


  public void paint(Graphics g){
      if(!visible)return;
      int ptX=canvas.pixFromX(vars[1])-w/2+xDisplayOff;
      int ptY=canvas.pixFromY(vars[2])-h/2-yDisplayOff;
      paintGhosts(g);
      g.setColor(color);
      for(int i=0;i<=s;i++){
        g.drawOval(ptX+i,ptY+i,w-2*i,h-2*i);
      }
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
      for(int i=0;i<=s;i++){
        g.drawOval(ptX+i,ptY+i,w-2*i,h-2*i);
        g.drawOval(ptX+i-1,ptY+i-1,w-2*i+2,h-2*i+2);
      }
     // paintTrail(g);
    //  if (showCoordinates) paintCoordinates(g, ptX+w/2, ptY+h/2);
      super.paintHighlight(g);
    }

  public boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(vars[1])+xDisplayOff;
      int ptY=canvas.pixFromY(vars[2])-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w/2+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }

  public void paintGhosts(Graphics g){
      if(!visible)return;
      // paint ghost images before the thing.
      if(ghost && footPrints>0 && trailSize>1 && trail.npoints>1){
          g.setColor(SUtil.veryPaleColor(color));
          for(int i=0;i<trail.npoints;i+=footPrints)
              for(int j=0;j<=s;j++)
                  g.drawOval(trail.xpoints[i]-w/2+j+xDisplayOff,trail.ypoints[i]-h/2+j-yDisplayOff,w-2*j,h-2*j);
      }
  }
}
