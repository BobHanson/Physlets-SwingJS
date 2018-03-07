

package wave3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public final class FilledRectangle extends Figure {

  public FilledRectangle(ThreeDPanel panel, double z1, double w, double h ,Color clr) {
         super(panel);
         numLines=4;
         pts=new double[4][3];
         color=clr;
                //point 1
         pts[0][0]=-w/2;
         pts[0][1]=-h/2;
         pts[0][2]=z1;
              //point 2
         pts[1][0]=-w/2;
         pts[1][1]=h/2;
         pts[1][2]=z1;
               //point 3
         pts[2][0]=w/2;
         pts[2][1]=h/2;
         pts[2][2]=z1;
              //point 4
         pts[3][0]=w/2;
         pts[3][1]=-h/2;
         pts[3][2]=z1;

  }

  public void setZ(double z1){
    pts[0][2]=z1;
    pts[1][2]=z1;
    pts[2][2]=z1;
    pts[3][2]=z1;
  }

  public void drawFigure(Graphics g,double[][] trans) {
          g.setColor(color);
          //setOrigin(g);

          Polygon p=new Polygon();
          p.addPoint((int)Math.round(trans[0][0]*getPtsValue(0,0)+trans[0][1]*getPtsValue(0,1)+trans[0][2]*getPtsValue(0,2)),
                                (int)Math.round(trans[1][0]*getPtsValue(0,0)+trans[1][1]*getPtsValue(0,1)+trans[1][2]*getPtsValue(0,2)));
          p.addPoint((int)Math.round(trans[0][0]*getPtsValue(1,0)+trans[0][1]*getPtsValue(1,1)+trans[0][2]*getPtsValue(1,2)),
                                (int)Math.round(trans[1][0]*getPtsValue(1,0)+trans[1][1]*getPtsValue(1,1)+trans[1][2]*getPtsValue(1,2)));
          p.addPoint((int)Math.round(trans[0][0]*getPtsValue(2,0)+trans[0][1]*getPtsValue(2,1)+trans[0][2]*getPtsValue(2,2)),
                                (int)Math.round(trans[1][0]*getPtsValue(2,0)+trans[1][1]*getPtsValue(2,1)+trans[1][2]*getPtsValue(2,2)));
          p.addPoint((int)Math.round(trans[0][0]*getPtsValue(3,0)+trans[0][1]*getPtsValue(3,1)+trans[0][2]*getPtsValue(3,2)),
                                (int)Math.round(trans[1][0]*getPtsValue(3,0)+trans[1][1]*getPtsValue(3,1)+trans[1][2]*getPtsValue(3,2)));
          g.fillPolygon(p);

          //resetOrigin(g);
   }
}


