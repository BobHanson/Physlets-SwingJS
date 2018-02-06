package bfield;

import edu.davidson.tools.*;
import edu.davidson.display.*;

public class FieldCircle extends CircleThing {
   FieldPanel fieldPanel;
   double integral=0;

  public FieldCircle(SApplet owner, FieldPanel fp, double x,double y,int radius){
    super( owner,  fp,  x, y, radius);
    fieldPanel=fp;
    varStrings= new String[]{"x","y","bx","by","path"};
    ds=new double[1][5];  // the datasource state variables
  }

  double doLineIntegral(){
      int numPts=100;
      double sum1=0,sum2=0;
      if(w<2 || h<2) return 0;
      int ptX=fieldPanel.pixFromX(x)+xDisplayOff; // center x
      int ptY=fieldPanel.pixFromY(y)-yDisplayOff; //center  y
      double x0, y0;
      x0=fieldPanel.xFromPix(ptX);
      double rad=(fieldPanel.xFromPix(ptX+w)-x0);
      y0=fieldPanel.yFromPix(ptY);

      double sin=0,cos=1, theta=0, dtheta=2*Math.PI/numPts;
      for(int i=0;i<numPts;i++){ // increment x   drop one corner
        sin=Math.sin(theta);
        cos=Math.cos(theta);
        sum1-=fieldPanel.getBx(x0+cos*rad,y0+sin*rad,null)*sin;   //
        sum2+=fieldPanel.getBy(x0+cos*rad,y0+sin*rad,null)*cos;
        theta+=dtheta;
      }
      integral=(sum1+sum2)*2*Math.PI*rad/numPts;
      return integral;
  }

  final public double[][] getVariables(){
     ds[0][0]=x;  //x
     ds[0][1]=y;  //y
     ds[0][2]=fieldPanel.getBx(x,y,null);  //Fx
     ds[0][3]=fieldPanel.getBy(x,y,null);;   //fy
     ds[0][4]=doLineIntegral();  //x
     return ds;
  }

}
