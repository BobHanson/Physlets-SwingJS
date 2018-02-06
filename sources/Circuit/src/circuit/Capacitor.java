package circuit;
import java.awt.*;
import edu.davidson.tools.SApplet;

public class Capacitor extends Part {
  public Capacitor(SApplet o, Circuit c,int i1, int j1,int i2,int j2) {
     super(o, c,i1,  j1, i2, j2) ;
     label="C";
     voltRMS=0;
     showC=true;
     showV=true;
     showR=false;
     showZ=false;
     showL=false;
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
      g.drawLine((int)(base_x1-v),(int)(base_y1+u),(int)(base_x1+v),(int)(base_y1-u));
      g.drawLine((int)(base_x2-v),(int)(base_y2+u),(int)(base_x2+v),(int)(base_y2-u));
  }
}
