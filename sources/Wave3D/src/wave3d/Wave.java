
package wave3d;

import java.awt.Color;



public class Wave extends Figure {
        double h;
        int incrementer=0;
        double zPropagate=-100, zTerminate=100;
        double length;
        double amplitude=100;
        double phase=0;
        int translation=2;              //amount wave is translated per tick
        int zOnDeck=0, zRelative=0,offset=0;

  public Wave(ThreeDPanel panel) {
    //the constructor for the superclass is called automatically.
     super(panel);
     lineDensity=panel.lineDensity;
     length=zTerminate-zPropagate;
     numLines =(int)Math.round(length/panel.lineDensity);
     pts=new double[2*numLines][3];
     h=length/wavelength*2*Math.PI/numLines;
     color=Color.blue;
     figType="line";

  }

  public void setWavelength(double len){
    wavelength=len;
    h=length*2*Math.PI/wavelength/(numLines-1);
  }

public void setSpeed(double s){
  speed=s;
}

  public void translate(double dz, double time){
         dz=dz*speed/5;
         for (int i=0;i<2*numLines; i++)
             pts[i][2]+=dz;

         zOnDeck+=dz; //a local z which keeps track of the impending stick
         zRelative=(int)(zOnDeck/lineDensity);//a variable to test whether it's time for a new stick to be created
         if(zRelative>=1){
            offset=zOnDeck%(int)(lineDensity);
            for(int j=0;j<zRelative;j++){  //cycles through loop for every new stick exposed by the translation
                for (int i=2*numLines-1;i>1; i--) {  //moves all the sticks down one position in the array to make room for new stick
                    pts[i][0]=pts[i-2][0];
                    pts[i][1]=pts[i-2][1];
                    pts[i][2]=pts[i-2][2];
                }
                incrementer--;
                setFirstStick(incrementer,(int)(lineDensity*(zRelative-j-1)+offset));
            }
         zOnDeck=offset;

         }
  }
  public void setFirstStick(int a,int d){}

  public void setPolarization(double angle){
    polarization=angle;
    for (int i=1,j=0;i<2*numLines;i+=2,j++) {
     pts[i][0]=Math.cos(polarization)*amplitude*Math.sin(incrementer*h+j*h+phase);  //x component
     pts[i][1]=Math.sin(polarization)*amplitude*Math.sin(incrementer*h+j*h+phase);  //y component
     pts[i][2]=j*threeDPanel.lineDensity+zPropagate;
    }

    for (int i=0,j=0;i<2*numLines; i+=2,j++) {

     pts[i][0]=0;
     pts[i][1]=0;
     pts[i][2]=j*threeDPanel.lineDensity+zPropagate;

    }
  }
}


