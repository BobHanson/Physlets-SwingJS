

package pipes;

import java.awt.Color;
import java.awt.Graphics;

//import java.awt.*;

public class Medium extends Thing{

  public Medium(int xpos, int ypos, int width, int height, Color c, Pipe o, boolean e, boolean m) {
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

    }

}
