

package wave3d;

import java.awt.*;

public class Rectangle2D extends Figure {

  public Rectangle2D(ThreeDPanel panel, double z1, double w, double h,Color clr) {
         super(panel);
         numLines=4; //
         pts=new double[8][3];
         color=clr;
                //line 1
         pts[0][0]=-w/2;
         pts[0][1]=-h/2;
         pts[0][2]=z1;

         pts[1][0]=-w/2;
         pts[1][1]=h/2;
         pts[1][2]=z1;

               //line 2
         pts[2][0]=-w/2;
         pts[2][1]=h/2;
         pts[2][2]=z1;

         pts[3][0]=w/2;
         pts[3][1]=h/2;
         pts[3][2]=z1;

             //line 3
         pts[4][0]=w/2;
         pts[4][1]=h/2;
         pts[4][2]=z1;

         pts[5][0]=w/2;
         pts[5][1]=-h/2;
         pts[5][2]=z1;
              //line 4
         pts[6][0]=w/2;
         pts[6][1]=-h/2;
         pts[6][2]=z1;

         pts[7][0]=-w/2;
         pts[7][1]=-h/2;
         pts[7][2]=z1;

  }

  public void setZ(double z1){
    pts[0][2]=z1;
    pts[1][2]=z1;
    pts[2][2]=z1;
    pts[3][2]=z1;
    pts[4][2]=z1;
    pts[5][2]=z1;
    pts[6][2]=z1;
    pts[7][2]=z1;
  }

}