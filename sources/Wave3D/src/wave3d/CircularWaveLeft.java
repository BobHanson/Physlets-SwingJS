

package wave3d;

import java.awt.*;

public class CircularWaveLeft extends Wave{

  public CircularWaveLeft(ThreeDPanel panel, double z1,double z2,double a,double ph, Color clr) {
        super(panel);
        zPropagate=z1;
        zTerminate=z2;
        amplitude=a;
        phase=ph;
        length=zTerminate-zPropagate;
        numLines =(int)Math.round(length/panel.lineDensity);
        color=clr;
        pts=new double[2*numLines][3];

         for (int i=1,j=0;i<2*numLines;i+=2,j++) {

             pts[i][0]=Math.cos(-j*h+phase)*amplitude;
             pts[i][1]=Math.sin(-j*h+phase)*amplitude;
             pts[i][2]=j*panel.lineDensity+zPropagate;
         }

         for (int i=0,j=0;i<2*numLines; i+=2,j++) {

             pts[i][0]=0;
             pts[i][1]=0;
             pts[i][2]=j*panel.lineDensity+zPropagate;

         }
  }

    public void setFirstStick(int incrementer,int offset){
             pts[0][0]=0;
             pts[0][1]=0;
             pts[0][2]=zPropagate+offset;

             pts[1][0]=Math.cos(-incrementer*h+phase)*amplitude;
             pts[1][1]=Math.sin(-incrementer*h+phase)*amplitude;
             pts[1][2]=zPropagate+offset;
  }

}

