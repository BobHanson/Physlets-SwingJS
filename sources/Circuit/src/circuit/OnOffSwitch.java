package circuit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.tools.SApplet;

public class OnOffSwitch extends Part {
  public OnOffSwitch(SApplet o, Circuit c,int i1, int j1,int i2,int j2) {
     super(o, c,i1,  j1, i2, j2) ;
     label="";
     voltRMS=0;
     switchOn=false;
     showC=false;
     showV=false;
     showR=false;
     showZ=false;
     showL=false;
  }

  void drawLabel(Graphics g, int x , int y, int xOff, int yOff){
      if(label==null) return;
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
      if(i1==i2)  // horz--   move it down
          g.drawString(label, x-4, y+15);
      else  // vert-- move to left
          g.drawString(label, x-10, y);
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
      w=s/8.0;
      double u=(w*x/h);
      double v=-(w*y/h);
      g.setColor(Color.red);
      if(switchOn){
          g.drawLine((int)(base_x1),(int)(base_y1),(int)(base_x2),(int)(base_y2));
          if(j1==j2){ // point down
              g.drawLine((int)(base_x1-1),(int)(base_y1),(int)(base_x2-1),(int)(base_y2));
              g.drawLine((int)(base_x1+1),(int)(base_y1),(int)(base_x2+1),(int)(base_y2));
          }else{
              g.drawLine((int)(base_x1),(int)(base_y1-1),(int)(base_x2),(int)(base_y2-1));
              g.drawLine((int)(base_x1),(int)(base_y1+1),(int)(base_x2),(int)(base_y2+1));
          }
          g.drawLine((int)(base_x1),(int)(base_y1),(int)(base_x2+v),(int)(base_y2-u));
      }else{ // switch is open
          g.drawLine((int)(base_x1),(int)(base_y1),(int)(base_x2+v),(int)(base_y2-u));
          if(j1==j2){ // point down
              g.drawLine((int)(base_x1-1),(int)(base_y1),(int)(base_x2+v-1),(int)(base_y2-u));
              g.drawLine((int)(base_x1+1),(int)(base_y1),(int)(base_x2+v+1),(int)(base_y2-u));
          }else{
              g.drawLine((int)(base_x1),(int)(base_y1-1),(int)(base_x2+v),(int)(base_y2-u-1));
              g.drawLine((int)(base_x1),(int)(base_y1+1),(int)(base_x2+v),(int)(base_y2-u+1));
          }
      }
  }

  String getHint(){
      if(customHint!=null) return customHint;
      if(switchOn) return Common.SWITCH_ON;
      else return Common.SWITCH_OFF ;
  }
}
