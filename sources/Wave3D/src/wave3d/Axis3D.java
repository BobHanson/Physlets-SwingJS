

package wave3d;

import java.awt.*;

public class Axis3D extends Figure {

  public Axis3D(ThreeDPanel panel, int x,int y,int z, double s, Color clr) {
        super(panel);
        numLines=3;
        color=clr;
        pts=new double[2*numLines][3];

         pts[0][0]=x;        //l -- x
         pts[0][1]=y;        //w -- y
         pts[0][2]=z;        //h -- z

         pts[1][0]=s+x;
         pts[1][1]=y;
         pts[1][2]=z;

               //line 2
         pts[2][0]=x;
         pts[2][1]=y;
         pts[2][2]=z;

         pts[3][0]=x;
         pts[3][1]=s+y;
         pts[3][2]=z;

             //line 3
         pts[4][0]=x;
         pts[4][1]=y;
         pts[4][2]=z;

         pts[5][0]=x;
         pts[5][1]=y;
         pts[5][2]=s+z;

  }




}