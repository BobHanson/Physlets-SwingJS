package eField4;

import java.awt.*;

public class CaptionThing extends TextThing {

  public CaptionThing(OdeCanvas o, String txt, String cs, double x, double y){
      super( o,  txt,  cs,  x,  y);
      color=Color.black;
  }

  public void paint(Graphics g){
      if(hideThing) return;
      String caption=getText();
      if(caption==null) return;
      g.setColor(color);
      Font f=g.getFont();
      g.setFont(font);
      FontMetrics fm=g.getFontMetrics(font);
      g.drawString(caption,(p.getSize().width-fm.stringWidth(caption))/2+xDisplayOff, 25-yDisplayOff);
      g.setFont(f);
  }
}
