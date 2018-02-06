

package wave3d;

import java.awt.*;

public class Text extends Figure {
  String text;
  int xpix, ypix;

  public Text(ThreeDPanel panel, String text, int xpix, int ypix) {
   super(panel);
   this.text=text;
   this.xpix=xpix;
   this.ypix=ypix;
   color=Color.black;
  }

  public void drawFigure(Graphics g,double[][] trans) {
     g.setColor(color);
     Font f=g.getFont();
     g.setFont(font);
     g.drawString(text,xpix,ypix);
     g.setFont(f);
  }
}