

package emwave4;

import java.awt.*;
import edu.davidson.numerics.Parser;

public class ParsedWave extends Wave {
        Parser parser;


  public ParsedWave(String str,double z1, double z2, double pix,double p,Color clr) {

        zPropagate=z1;
        zTerminate=z2;
        pixPerUnit=pix;
        polarization=p;
        length=zTerminate-zPropagate;
        numLines =(int)Math.round(length/lineDensity);
        pts=new double[2*numLines][3];
        h=length/pixPerUnit/numLines;         //h in units
        c=clr;

     parser = new Parser(1);
     parser.defineVariable(1,"z"); // define the variable
     parser.define(str);
     parser.parse();
     if(parser.getErrorCode() != Parser.NO_ERROR){
         //System.out.println("Failed to parse x(t): "+str);
         //System.out.println("Parse error: " + parser.getErrorString() +
           //        " at function 1, position " + parser.getErrorPosition());
         return;
     }

         for (int i=1,j=0;i<2*numLines;i+=2,j++) {

             pts[i][0]=pixPerUnit*Math.cos(polarization)*parser.evaluate(j*h+zPropagate/pixPerUnit);
             pts[i][1]=pixPerUnit*Math.sin(polarization)*parser.evaluate(j*h+zPropagate/pixPerUnit);
             pts[i][2]=j*lineDensity+zPropagate;

         }

         for (int i=0,j=0;i<2*numLines; i+=2,j++) {

             pts[i][0]=0;
             pts[i][1]=0;
             pts[i][2]=j*lineDensity+zPropagate;

         }

  }

  public void setFirstStick(int incrementer,int offset){
             pts[0][0]=0;
             pts[0][1]=0;
             pts[0][2]=zPropagate+offset;

             pts[1][0]=pixPerUnit*Math.cos(polarization)*parser.evaluate(incrementer*h+(int)(zPropagate/pixPerUnit));
             pts[1][1]=pixPerUnit*Math.sin(polarization)*parser.evaluate(incrementer*h+(int)(zPropagate/pixPerUnit));
             pts[1][2]=zPropagate+offset;


  }
}