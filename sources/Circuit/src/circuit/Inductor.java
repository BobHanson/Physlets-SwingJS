package circuit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.tools.SApplet;

public class Inductor extends Part {

  public Inductor(SApplet o, Circuit c,int i1, int j1,int i2,int j2) {
     super(o, c, i1,  j1, i2, j2) ;
     label="L";
     voltRMS=0;
     res=0;
     ind=1.0;
     showV=true;
     showL=true;
     showZ=false;
     showR=false;
  }

  void drawLabel(Graphics g, int x , int y, int xOff, int yOff){
      if(label==null) return;
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
      if(i1==i2)  g.drawString(label, x-4, y-8);
      else if(j1==j2) g.drawString(label, x-18, y+5);
      else  g.drawString(label, x, y);
      g.setFont(oldFont);
  }

  void drawSymbol(Graphics g, int xx1,int yy1,int xx2,int yy2, int s){
      g.setColor(color);
      double x=xx2-xx1;
      double y=-(yy2-yy1);
      double h=Math.sqrt(x*x+y*y);
      if(h<2) return; // something is wrong
      double w=(h/2.0-s/4.0);
      int base_x1= (int)(xx1+(x*w)/h);
      int base_y1= (int)(yy1-(y*w)/h);
      int base_x2= (int)(xx2-(x*w)/h);
      int base_y2= (int)(yy2+(y*w)/h);
      g.drawLine(xx1,yy1,base_x1,base_y1);
      g.drawLine(xx2,yy2,base_x2,base_y2);
      // spring code from animator
      double x1,y1;
      double x2,y2;
      if(i1*i1+j1*j1<i2*i2+j2*j2){
          x1=base_x1; y1=base_y1;
          x2=base_x2; y2=base_y2;
      } else{
          x2=base_x1; y2=base_y1;
          x1=base_x2; y1=base_y2;
      }
      x=x2-x1;
      y=-(y2-y1);
      h=Math.sqrt(x*x+y*y);
      //(u,v) is in the spring direction and of length h/16
      //(-v,u) is orthogonal to the direction and of length h/16
      double u=(x/16.0);
      double v=-(y/16.0);
      double u0=8*x/h;    // unit vector components
      double v0=-8*y/h;
      x2= (int)(x1+u);
      y2= (int)(y1+v);
      double x3= (x1+2*u);
      double y3= (y1+2*v);
      int start=0, end=180;
      double xoffset=0, yoffset=0;
      if(i1==i2){start=0; yoffset=-h/4+1;}
          else if(j1==j2)start=90;
          else {
              start=90+(int)(360*Math.atan2(u0,v0)/2/Math.PI);
              xoffset=-h/16+1;
              yoffset=-h/8+1;
          }
      for(int count=0; count<4; count++){
                 x2=(int)(x2-v0);  y2=(int)(y2+u0);
                // g.drawLine((int)(x1),(int)(y1),(int)(x2),(int)(y2));
                 g.drawArc((int)((x1+x2)/2-xoffset),(int)((y1+y2)/2+yoffset),(int)(h/4),(int)(h/4),start,end);
                // g.drawLine((int)(x2),(int)(y2),(int)(x3),(int)(y3));
                 x1=x3;     y1=y3;
                 x2=x1+u;   y2=y1+v;
                 x3=x1+2*u; y3=y1+2*v;
                 x2=x2+v0;  y2=y2-u0;
               //  g.drawLine((int)(x1),(int)(y1),(int)(x2),(int)(y2));
                // g.drawLine((int)(x2),(int)(y2),(int)(x3),(int)(y3));
                 x1=x3;     y1=y3;
                 x2=x1+u;   y2=y1+v;
                 x3=x1+2*u; y3=y1+2*v;
      }
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
     // g.drawString("+", (int)(base_x1-v-8), (int)(base_y1+u));
      g.setFont(oldFont);

  }

}
