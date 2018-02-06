

package edu.davidson.physlets.emwave4;

import java.awt.*;

public class Plane extends Figure {
  static int lineDensity=2;   //lines/pixel --between 0-1
  double polarization=0;
  public Plane(double z1, double length, double width,double p,Color clr) {
        numLines=(int)Math.round(width/lineDensity);
        double zPosition=z1;
        c=clr;
        pts=new double[2*numLines][3];
        polarization=p;
        for (int i=1,j=-numLines/2;i<2*numLines;i+=2,j++) {

             pts[i][0]=Math.cos(polarization)*j*lineDensity-Math.sin(polarization)*length/2;
             pts[i][1]=Math.sin(polarization)*j*lineDensity+Math.cos(polarization)*length/2;
             pts[i][2]=zPosition;

         }

         for (int i=0,j=-numLines/2;i<2*numLines; i+=2,j++) {

             pts[i][0]=Math.cos(polarization)*j*lineDensity+Math.sin(polarization)*length/2;
             pts[i][1]=Math.sin(polarization)*j*lineDensity-Math.cos(polarization)*length/2;
             pts[i][2]=zPosition;


         }

  }

  public static void setLineDensity(int d){lineDensity=d;}
}