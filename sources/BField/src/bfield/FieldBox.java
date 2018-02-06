package bfield;

import edu.davidson.display.BoxThing;
import edu.davidson.tools.*;

public class FieldBox extends BoxThing {
   FieldPanel fieldPanel;
   double integral=0;

  public FieldBox(SApplet owner, FieldPanel fp, double x,double y,int width,int height){
    super( owner,  fp,  x, y, width, height);
    fieldPanel=fp;
    varStrings= new String[]{"x","y","bx","by","path"};
    ds=new double[1][5];  // the datasource state variables
  }

  double doLineIntegral(){
      double sum1=0,sum2=0,sum3=0,sum4=0;
      if(w<2 || h<2) return 0;
      int ptX=fieldPanel.pixFromX(x)-w/2+xDisplayOff;
      int ptY=fieldPanel.pixFromY(y)-h/2-yDisplayOff;
      double xlocal1, ylocal1, xlocal2, ylocal2;
      xlocal1=fieldPanel.xFromPix(ptX);
      xlocal2=fieldPanel.xFromPix(ptX+w);
      double dx=(xlocal2-xlocal1)/w;
      ylocal1=fieldPanel.yFromPix(ptY);
      ylocal2=fieldPanel.yFromPix(ptY+h);
      double dy=(ylocal2-ylocal1)/h;    // this will be negative

      double x0=xlocal1+dx/2;
      for(int i=0;i<w;i++){ // increment x 
        sum1-=fieldPanel.getBx(x0,ylocal1,null);
        sum2+=fieldPanel.getBx(x0,ylocal2,null);
        x0+=dx;
      }
      double y0=ylocal1+dy/2;
      for(int j=0;j<h;j++){
        sum3-=fieldPanel.getBy(xlocal1,y0,null);
        sum4+=fieldPanel.getBy(xlocal2,y0,null);
        y0+=dy;
      }
      integral=(sum1*dx+sum2*dx-sum3*dy-sum4*dy);
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