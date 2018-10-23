

package spectrum;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import java.awt.*;

public class Title extends TextThing{



  public Title() {
  }

  public Title(SpectrumPanel o, String cap){
     super(o, cap);
  }

  public void paintOS(Graphics g){
    Font f = new Font("Arial", Font.BOLD, 14);
    g.setFont(f);
    FontMetrics fm=g.getFontMetrics(g.getFont());
    int boxW=fm.stringWidth(txt);
    int boxH=20;
    int x0 = xoff+(int)((owner.currentWidth-boxW)/2);
    int y0 = yoff+(int)(0.1*owner.currentHeight);
    g.setColor(txtColor);
    g.drawString(txt,x0,y0);
  }




}