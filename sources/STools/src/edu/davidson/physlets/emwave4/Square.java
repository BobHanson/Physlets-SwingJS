

package edu.davidson.physlets.emwave4;

import java.awt.*;

public class Square extends Figure {

  public Square(double z1, double s,Color clr) {

         numLines=4; //
         pts=new double[8][3];
         c=clr;
                //line 1
         pts[0][0]=-s/2;
         pts[0][1]=-s/2;
         pts[0][2]=z1;

         pts[1][0]=-s/2;
         pts[1][1]=s/2;
         pts[1][2]=z1;

               //line 2
         pts[2][0]=-s/2;
         pts[2][1]=s/2;
         pts[2][2]=z1;

         pts[3][0]=s/2;
         pts[3][1]=s/2;
         pts[3][2]=z1;

             //line 3
         pts[4][0]=s/2;
         pts[4][1]=s/2;
         pts[4][2]=z1;

         pts[5][0]=s/2;
         pts[5][1]=-s/2;
         pts[5][2]=z1;

              //line 4
         pts[6][0]=s/2;
         pts[6][1]=-s/2;
         pts[6][2]=z1;

         pts[7][0]=-s/2;
         pts[7][1]=-s/2;
         pts[7][2]=z1;




  }
}