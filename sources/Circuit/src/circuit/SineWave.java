package circuit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.tools.SApplet;

public class SineWave extends Part {
  public SineWave(SApplet o, Circuit c,int i1, int j1,int i2,int j2) {
     super(o, c,i1,  j1, i2, j2) ;
     label=Common.LEGEND_VAC;
     voltRMS=10;
     phase=0;
     showV=true;
     showZ=false;
     showF=true;
     showPhase=true;
  }

  void drawLabel(Graphics g, int x , int y, int xOff, int yOff){
      if(label==null) return;
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
      g.drawString(label, x-10, y+5);
      g.setFont(oldFont);
  }
  void drawSymbol(Graphics g, int x1,int y1,int x2,int y2, int s){
      g.setColor(color);
      double x=x2-x1;
      double y=-(y2-y1);
      double h=Math.sqrt(x*x+y*y);
      if(h<2) return; // something is wrong
      double w=(h/2.0-6);
      int base_x1= (int)(x1+(x*w)/h);
      int base_y1= (int)(y1-(y*w)/h);
      int base_x2= (int)(x2-(x*w)/h);
      int base_y2= (int)(y2+(y*w)/h);
      g.drawLine(x1,y1,base_x1,base_y1);
      g.drawLine(x2,y2,base_x2,base_y2);
      g.fillOval((x1+x2)/2-15,(y1+y2)/2-15,30,30);
      g.setColor(Color.white);
      g.fillOval((x1+x2)/2-13,(y1+y2)/2-13,26,26);
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
      g.drawString("+", (int)(base_x1+20), (int)(base_y1));
      g.setFont(oldFont);

  }

  String getMSHint(){
      String msg="";
      if(showV) msg+= Common.LEGEND_VEQU+format.form(voltRMS);
      if(showF) msg+= " "+Common.LEGEND_FREQ+format.form(freq)+"Hz  ";
      if(showPhase) msg+="phi="+format.form(phase)+" ";
      if(msg.trim().length()==0) msg=Common.LEGEND_NOVALUE; //"Values are not available for this part.";
      return msg;
  }

  String getHint(){
      if(customHint!=null) return customHint;
      if(isMicrosoft()){return getMSHint(); }
      String msg="";
      if(showV) msg+= Common.LEGEND_VEQU+format.form(voltRMS);
      if(showF) msg+= " "+Common.LEGEND_FREQ+format.form(freq)+"Hz  ";
      if(showPhase) msg+= phi+ "="+format.form(phase)+ degree+"  ";
      if(msg.trim().length()==0) msg=Common.LEGEND_NOVALUE;//"Values not available for this part.";
      return msg;
  }
}
