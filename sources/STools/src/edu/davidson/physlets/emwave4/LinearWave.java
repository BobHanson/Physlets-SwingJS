package edu.davidson.physlets.emwave4;

import java.awt.*;


public class LinearWave extends Wave {


  public LinearWave(double z1,double z2,double a,double ph,double p, Color clr) {
        //super();      //extraneous b/c Java calls super automatically
        zPropagate=z1;
        zTerminate=z2;
        amplitude=a;
        phase=ph;
        polarization=p;
        length=zTerminate-zPropagate;
        numLines =(int)Math.round(length/lineDensity);
        pts=new double[2*numLines][3];
        c=clr;
          for (int i=1,j=0;i<2*numLines;i+=2,j++) {
             pts[i][0]=Math.cos(polarization)*amplitude*Math.sin(j*h+phase);  //x component
             pts[i][1]=Math.sin(polarization)*amplitude*Math.sin(j*h+phase);  //y component
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

             pts[1][0]=Math.cos(polarization)*amplitude*Math.sin(incrementer*h+phase);
             pts[1][1]=Math.sin(polarization)*amplitude*Math.sin(incrementer*h+phase);
             pts[1][2]=zPropagate+offset;
  }


}
