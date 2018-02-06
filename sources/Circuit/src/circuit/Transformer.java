package circuit;
import java.awt.*;
import edu.davidson.tools.SApplet;

public class Transformer extends Part {
  boolean vertical=true;
  public Transformer(SApplet o, Circuit c,int i1, int j1,int i2,int j2, boolean v) {
     super(o,c, i1,  j1, i2, j2) ;
     vertical=v;
     voltRMS=0;
     res=0;
     ind=1.0;
     showV=false;
     showL=false;
     showZ=false;
  }

  void drawLabel(Graphics g, int x , int y, int xOff, int yOff){
      if(label==null) return;
      Font oldFont=g.getFont();
      g.setFont(f); // this font is used for messages and captions.
      g.setColor(Color.black);
      if (vertical) g.drawString(label, x-3, y-25);
          else g.drawString(label, x-30, y+3);
      g.setFont(oldFont);
  }

  void drawSymbol(Graphics g, int x1,int y1,int x2,int y2, int s){
      if(!vertical){
          drawHorz(g,x1,y1,x2,y2,s);
          return;
      }
      g.setColor(color);
      double x=x2-x1;
      double y=-(y2-y1);
      double h=Math.sqrt(x*x+y*y);
      if(h<2) return; // something is wrong
      double w=(h/2.0-30);
      int base_x1= (int)(x1+(x*w)/h);
      int base_y1= (int)(y1-(y*w)/h);
      int base_x2= (int)(x2-(x*w)/h);
      int base_y2= (int)(y2+(y*w)/h);
      g.drawLine(x1,y1,x1,base_y1);
      g.drawLine(x2,y1,x2,base_y1);
      g.drawLine(x1,base_y1,base_x1,base_y1);
      g.drawLine(x2,base_y1,base_x2,base_y1);

      int r=6;
      int n=Math.abs(base_y2-base_y1)/(2*r);
      for(int i=0; i<=n; i++){
          g.drawArc(base_x1-r,base_y1+i*2*r,2*r,2*r,90,-180);
          g.drawArc(base_x2-r,base_y1+i*2*r,2*r,2*r,90,180);
      }
      base_y2=base_y1+(n+1)*2*r;
      g.drawLine(x1,y2,x1,base_y2);
      g.drawLine(x2,y2,x2,base_y2);
      g.drawLine(x1,base_y2,base_x1,base_y2);
      g.drawLine(x2,base_y2,base_x2,base_y2);
       // the iron core
      g.drawLine((x1+x2)/2,base_y1,(x1+x2)/2,base_y2);
      g.drawLine((x1+x2)/2-3,base_y1,(x1+x2)/2-3,base_y2);
      g.drawLine((x1+x2)/2+3,base_y1,(x1+x2)/2+3,base_y2);
  }

  private void drawHorz(Graphics g, int x1,int y1,int x2,int y2, int s){
      g.setColor(color);
      double x=x2-x1;
      double y=-(y2-y1);
      double h=Math.sqrt(x*x+y*y);
      if(h<2) return; // something is wrong
      double w=(h/2.0-30);
      int base_x1= (int)(x1+(x*w)/h);
      int base_y1= (int)(y1-(y*w)/h);
      int base_x2= (int)(x2-(x*w)/h);
      int base_y2= (int)(y2+(y*w)/h);
      g.drawLine(x1,y1,base_x1, y1);
      g.drawLine(base_x1,y1,base_x1, base_y1);
      g.drawLine(x1,y2,base_x1, y2);
      g.drawLine(base_x1,y2,base_x1, base_y2);

      int r=6;
      int n=Math.abs(base_y2-base_y1)/(2*r);
      for(int i=0; i<=n; i++){
          g.drawArc(base_x1+i*2*r,base_y1-r,2*r,2*r,0,-180);
          g.drawArc(base_x1+i*2*r,base_y2-r,2*r,2*r,0,180);
      }
      base_x2=base_x1+(n+1)*2*r;
      g.drawLine(x2,y1,base_x2, y1);
      g.drawLine(base_x2,y1,base_x2, base_y1);
      g.drawLine(x2,y2,base_x2, y2);
      g.drawLine(base_x2,y2,base_x2, base_y2);
       // the iron core
      g.drawLine(base_x1,(y1+y2)/2-3,base_x2,(y1+y2)/2-3);
      g.drawLine(base_x1,(y1+y2)/2,base_x2,(y1+y2)/2);
      g.drawLine(base_x1,(y1+y2)/2+3,base_x2,(y1+y2)/2+3);

  }

}
