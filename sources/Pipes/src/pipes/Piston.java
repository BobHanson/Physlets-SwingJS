

package pipes;

import java.awt.*;

public class Piston extends Thing{

  public Piston(int xpos, int ypos, int width, int height, Color c, Pipe o, boolean e, boolean m) {
  // Thing(int x, int y, int width, int height, Color c, Pipe o, boolean e, boolean m)
  super(xpos,ypos,width,height,c,o,e,m);
  }

  /**
  *
  * Thing will be painted if enabled
  *
  * @param e boolean
  *
  */

  public void setIsEnabled(boolean e){
  this.enabled = e;
  }

  public void paintOS(Graphics g){
    int pwidth = Math.max(0,w-20);
    g.setColor(Color.black);
    g.fillRect(x,y,w,h);
    g.setColor(color);
    g.fillRect(pwidth,y,20,h);
    g.fillRect(0,y+(int)(h*3/8),w,(int)(h/4));
    }

  public boolean isInside(int xpos, int ypos){
    if ( (xpos<x+w) && (xpos>x) && (ypos>y) && (ypos<y+h) ) return true;
    else return false;

  }

}
