

package pipes;

import java.awt.Color;
import java.awt.Graphics;

//import java.awt.*;

public class Source extends Thing{
  double value;
  double xoffset;
  int ppu;

  public Source(int xpos, int ypos, int width, int height, Color c, Pipe o, boolean e, boolean m) {
    // Thing(int x, int y, int width, int height, Color c, Pipe o, boolean e, boolean m)
    super(xpos,ypos,width,height,c,o,e,m);
    xoffset = o.xoffset;
    ppu=o.ppu;
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
    Color col = new Color((int)(100+155*(0.5+(Math.atan(value)/Math.PI))),128,128);
    g.setColor(col);
    g.fillRect(x,y,w,h);
    //g.fillRect(x,y,(int)(xoffset*ppu),currentHeight-20);
    g.setColor(Color.red);
    g.drawLine(w,y,w,y+h);
  }

  /**
  *
  * Thing will be painted if enabled
  *
  * @param e boolean
  *
  */
  public void setValue(double v){
  value = v;
  }

}
