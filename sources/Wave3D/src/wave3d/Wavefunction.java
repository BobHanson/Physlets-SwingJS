
package wave3d;

import java.awt.*;
import edu.davidson.numerics.Parser;

public class Wavefunction extends Wave {

  Parser parser;

  public Wavefunction(ThreeDPanel panel, String str,double z1, double z2, double pix,double p,Color clr) {
    super(panel);
    zPropagate=z1;
    zTerminate=z2;
    pixPerUnit=pix;
    polarization=p;
    length=zTerminate-zPropagate;
    numLines =(int)Math.round(length/panel.lineDensity);
    pts=new double[2*numLines][3];
    h=length/pixPerUnit/numLines;         //h in units
    color=clr;

     parser = new Parser(2);
     parser.defineVariable(1,"z"); // define the variable
     parser.defineVariable(2,"t"); // define the variable
     parser.define(str);
     parser.parse();
     if(parser.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse f(z,t): "+str);
         System.out.println("Parse error: " + parser.getErrorString() + " at function 1, position " + parser.getErrorPosition());
         str="0";
         parser.define(str);
         parser.parse();
     }

     for (int i=1,j=0;i<2*numLines;i+=2,j++) {

         pts[i][0]=pixPerUnit*Math.cos(polarization)*parser.evaluate(j*h+zPropagate/pixPerUnit,0);
         pts[i][1]=pixPerUnit*Math.sin(polarization)*parser.evaluate(j*h+zPropagate/pixPerUnit,0);
         pts[i][2]=j*panel.lineDensity+zPropagate;

     }

     for (int i=0,j=0;i<2*numLines; i+=2,j++) {
         // points along the z axis
         pts[i][0]=0;
         pts[i][1]=0;
         pts[i][2]=j*panel.lineDensity+zPropagate;

     }
  }

  public void translate(double dz, double time){
     for (int i=1,j=0;i<2*numLines;i+=2,j++) {
         pts[i][0]=pixPerUnit*Math.cos(polarization)*parser.evaluate(j*h+zPropagate/pixPerUnit,time);
         pts[i][1]=pixPerUnit*Math.sin(polarization)*parser.evaluate(j*h+zPropagate/pixPerUnit,time);
     }
  }

}