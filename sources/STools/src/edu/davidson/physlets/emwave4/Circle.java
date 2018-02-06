

package edu.davidson.physlets.emwave4;

import java.awt.*;

public class Circle extends Figure {

  public Circle(double z1, double r, Color clr) {
        pts=new double[5][3];
        //corner
        pts[0][0]=-r;
        pts[0][1]=r;
        pts[0][2]=z1;

        //major axis
        pts[1][0]=r/2;
        pts[1][1]=0;
        pts[1][2]=z1;

        pts[2][0]=-r/2;
        pts[2][1]=0;
        pts[2][2]=z1;

        //minor axis
        pts[3][0]=0;
        pts[3][1]=r/2;
        pts[3][2]=z1;

        pts[4][0]=0;
        pts[4][1]=-r/2;
        pts[4][2]=z1;

        numLines=2;
        xOrigin=350;
        yOrigin=175;
        figType="oval";
        c=clr;

  }
}