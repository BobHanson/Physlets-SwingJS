

package edu.davidson.physlets.emwave4;

import java.awt.*;

public final class FilledSquare extends Figure {

  public FilledSquare(double z1, double s,Color clr) {

         numLines=4;
         pts=new double[4][3];
         c=clr;
                //point 1
         pts[0][0]=-s/2;
         pts[0][1]=-s/2;
         pts[0][2]=z1;
              //point 2
         pts[1][0]=-s/2;
         pts[1][1]=s/2;
         pts[1][2]=z1;
               //point 3
         pts[2][0]=s/2;
         pts[2][1]=s/2;
         pts[2][2]=z1;
              //point 4
         pts[3][0]=s/2;
         pts[3][1]=-s/2;
         pts[3][2]=z1;

  }
  public void drawFigure(Graphics g,double[][] trans) {
          reSetColor(g);
          setOrigin(g);

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

          resetOrigin(g);
   }
}


