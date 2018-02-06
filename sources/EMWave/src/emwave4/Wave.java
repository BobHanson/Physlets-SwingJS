

package emwave4;

import java.awt.*;

public class Wave extends Figure {
        double h;
        int incrementer=0;
        double zPropagate=-100, zTerminate=100;
        double length;
        double amplitude=100;
        double phase=0;
        double polarization=0;
        static int lineDensity=2;   //lines/pixel --between 0-1
        static double wavelength=200;      //cycles/pixel
        static int translation=2;              //amount wave is translated per tick
        int zOnDeck=0, zRelative=0,offset=0;

  public Wave() {
    //the constructor for the superclass is called automatically.
     super();
     length=zTerminate-zPropagate;
     numLines =(int)Math.round(length/lineDensity);
     pts=new double[2*numLines][3];
     h=length/wavelength*2*Math.PI/numLines;
     c=Color.blue;
     figType="line";

  }

  public void translate(double dz){
         for (int i=0;i<2*numLines; i++)
             pts[i][2]+=dz;

         zOnDeck+=dz; //a local z which keeps track of the impending stick
         zRelative=(int)(zOnDeck/lineDensity);//a variable to test whether it's time for a new stick to be created
  /*          System.out.println("offset="+offset);
            System.out.println("zOnDeck="+zOnDeck);
            System.out.println("zRelative="+zOnDeck);   */
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
 /*   public void translate(double dz){

         for (int i=0;i<2*numLines; i++)
             pts[i][2]+=dz;

         for (int i=2*numLines-1;i>1; i--)    {
             pts[i][0]=pts[i-2][0];
             pts[i][1]=pts[i-2][1];
             pts[i][2]=pts[i-2][2];
         }

         incrementer--;
         setFirstStick(incrementer);
  }  */
  public void setFirstStick(int a,int d){}
  public static void setLineDensity(int d){lineDensity=d;}
  public static void setWavelength(double l){wavelength=l;}
}


