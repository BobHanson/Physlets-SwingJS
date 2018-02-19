package eField4;

import java.awt.Graphics;

//import java.awt.*;

public class SpringAnchor extends LineAnchor {

    SpringAnchor(OdeCanvas p, double x,double y, Thing t){
      super(p,x,y,t);
      s=2; // number of coils
    }

    public void paint(Graphics osg){
          if(hideThing) return;
         double x,y; // the components of the spring
         int ptX1=p.pixFromX(vars[1]);
         int ptY1=p.pixFromY(vars[2]);
         int ptX2=p.pixFromX(thing.vars[1]);
         int ptY2=p.pixFromY(thing.vars[2]);
         x=ptX2-ptX1; // the x component;
         y=-ptY2+ptY1; // the y component;
         osg.setColor(color);
         double h=Math.sqrt(x*x+y*y);
         if(h>1){
             //(u,v) is in the spring direction and of length h/16
             //(-v,u) is orthogonal to the direction and of length h/16
             double u=(x/16.0/s);
             double v=-(y/16.0/s);
             double u0=8*x/h;
             double v0=-8*y/h;
             double x1= ptX1;
             double y1= ptY1;
             double x2= x1+u;
             double y2= y1+v;
             double x3= x1+2*u;
             double y3= y1+2*v;
             for(int count=0; count<4*s; count++){
                 x2=x2-v0;  y2=y2+u0;
                 osg.drawLine((int)(x1),(int)(y1),(int)(x2),(int)(y2));
                 osg.drawLine((int)(x2),(int)(y2),(int)(x3),(int)(y3));
                 x1=x3;     y1=y3;
                 x2=x1+u;   y2=y1+v;
                 x3=x1+2*u; y3=y1+2*v;
                 x2=x2+v0;  y2=y2-u0;
                 osg.drawLine((int)(x1),(int)(y1),(int)(x2),(int)(y2));
                 osg.drawLine((int)(x2),(int)(y2),(int)(x3),(int)(y3));
                 x1=x3;     y1=y3;
                 x2=x1+u;   y2=y1+v;
                 x3=x1+2*u; y3=y1+2*v;
             }
         }
    }
}