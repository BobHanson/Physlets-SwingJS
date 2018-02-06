package edu.davidson.display;
import java.awt.*;
import edu.davidson.tools.SApplet;

public class CaptionThing extends Thing {

  private String text;

  public CaptionThing(SApplet owner, SScalable sc, String txt, double x, double y){
    super(sc,x,y);
    yDisplayOff=-25;
    font=new Font("Helvetica",Font.BOLD,14);
    text=txt;
    applet=owner;
  }


  public void paint(Graphics g){
      if(!visible) return;
      Font f=g.getFont();
      g.setFont(font);
      //int ptX=canvas.pixFromX(x)+xDisplayOff;
      //int ptY=canvas.pixFromY(y)-yDisplayOff;
      g.setColor(color);
      FontMetrics fm=g.getFontMetrics(font);
      g.drawString(text,(canvas.getPixWidth()-fm.stringWidth(text))/2+xDisplayOff, -yDisplayOff);
      g.setColor(Color.black);
      g.setFont(f);
  }
}
