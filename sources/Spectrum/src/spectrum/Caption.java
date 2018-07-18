

package spectrum;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import a2s.*;


public class Caption extends TextThing{
  int lineID = 0;

  public Caption() {
  }

  public Caption(SpectrumPanel o, int id,String cap){
     super(o, cap);
     lineID=id;
  }

   public void paintOS(Graphics g){
    Font f = new Font("Arial", Font.BOLD, 11);
    g.setFont(f);
    SpectrumThing t = owner.getThing(lineID);
    double lam=t.lambda;
    FontMetrics fm=g.getFontMetrics(g.getFont());
    int boxW=fm.stringWidth(txt);
    int boxH=20;
    int x0 = xoff+owner.lambdaToPix(lam)-(int)(boxW/2);
    int y0 = yoff+(int)(0.8*owner.currentHeight);
    g.setColor(txtColor);
    g.drawString(txt,x0,y0);
  }
}