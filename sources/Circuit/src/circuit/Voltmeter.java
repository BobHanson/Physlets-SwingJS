package circuit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.tools.SApplet;

public class Voltmeter extends Part {
  public Voltmeter(SApplet o, Circuit c,int i1, int j1,int i2,int j2) {
     super(o, c,i1,  j1, i2, j2) ;
     label="V";
     voltRMS=10;
     res=1.0e64;
     showV=true;
     showZ=false;
  }

  void drawLabel(Graphics g, int x , int y, int xOff, int yOff){
      if(label==null) return;
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
      g.drawString(label, x-4, y+5);
      g.setFont(oldFont);
  }
  void drawSymbol(Graphics g, int x1,int y1,int x2,int y2, int s){
      g.setColor(color);
      double x=x2-x1;
      double y=-(y2-y1);
      double h=Math.sqrt(x*x+y*y);
      if(h<2) return; // something is wrong
      double w=(h/2.0-10);
      int base_x1= (int)(x1+(x*w)/h);
      int base_y1= (int)(y1-(y*w)/h);
      int base_x2= (int)(x2-(x*w)/h);
      int base_y2= (int)(y2+(y*w)/h);
      g.drawLine(x1,y1,base_x1,base_y1);
      g.drawLine(x2,y2,base_x2,base_y2);
      g.setColor(new Color(128,255,128));
      g.fillOval((x1+x2)/2-10,(y1+y2)/2-10,20,20);
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
      g.drawString("+", (int)(base_x1+15), (int)(base_y1+2));
      g.setFont(oldFont);
  }
  String getHint(){
      if(customHint!=null) return customHint;
      String msg="";
      if(showV) msg+= Common.LEGEND_VOLTMETER+format1.form(voltRMS)+" V";
      if(msg.trim().length()==0) msg=Common.LEGEND_NOVOLTAGE;//"Voltage is not available.";
      return msg;
  }
}