package animator4;

import java.awt.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class CaptionThing extends TextThing {

  public CaptionThing(AnimatorCanvas o, String txt, String cs, String xStr, String yStr){
      super( o,  txt,  cs,  xStr,  yStr);
      color=Color.black;
  }

  public void paint(Graphics g){
      if(!visible)return;
      String caption=getText();
      if(caption==null) return;
      g.setColor(color);
      Font f=g.getFont();
      g.setFont(font);
      FontMetrics fm=g.getFontMetrics(font);
      g.drawString(caption,(canvas.iwidth-fm.stringWidth(caption))/2+xDisplayOff, 25-yDisplayOff);
      g.setFont(f);
  }
} 