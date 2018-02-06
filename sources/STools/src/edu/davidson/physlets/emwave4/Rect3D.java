

package edu.davidson.physlets.emwave4;

public class Rect3D extends Figure {

  public Rect3D(double l,double w, double h) {
         numLines=12;

         pts=new double[2*numLines][3];

                //line 1
         pts[0][0]=l/2;        //l -- x
         pts[0][1]=w/2;        //w -- y
         pts[0][2]=h/2;        //h -- z

         pts[1][0]=-l/2;
         pts[1][1]=w/2;
         pts[1][2]=h/2;

               //line 2
         pts[2][0]=-l/2;
         pts[2][1]=w/2;
         pts[2][2]=h/2;

         pts[3][0]=-l/2;
         pts[3][1]=-w/2;
         pts[3][2]=h/2;

             //line 3
         pts[4][0]=-l/2;
         pts[4][1]=-w/2;
         pts[4][2]=h/2;

         pts[5][0]=l/2;
         pts[5][1]=-w/2;
         pts[5][2]=h/2;

              //line 4
         pts[6][0]=l/2;
         pts[6][1]=-w/2;
         pts[6][2]=h/2;

         pts[7][0]=l/2;
         pts[7][1]=w/2;
         pts[7][2]=h/2;

               //line 5
         pts[8][0]=l/2;
         pts[8][1]=w/2;
         pts[8][2]=-h/2;

         pts[9][0]=-l/2;
         pts[9][1]=w/2;
         pts[9][2]=-h/2;

              //line 6
         pts[10][0]=-l/2;
         pts[10][1]=w/2;
         pts[10][2]=-h/2;

         pts[11][0]=-l/2;
         pts[11][1]=-w/2;
         pts[11][2]=-h/2;

              //line 7
         pts[12][0]=-l/2;
         pts[12][1]=-w/2;
         pts[12][2]=-h/2;

         pts[13][0]=l/2;
         pts[13][1]=-w/2;
         pts[13][2]=-h/2;

              //line 8
         pts[14][0]=l/2;
         pts[14][1]=-w/2;
         pts[14][2]=-h/2;

         pts[15][0]=l/2;
         pts[15][1]=w/2;
         pts[15][2]=-h/2;


              //lin 9
         pts[16][0]=l/2;
         pts[16][1]=w/2;
         pts[16][2]=h/2;

         pts[17][0]=l/2;
         pts[17][1]=w/2;
         pts[17][2]=-h/2;

               //line 10
         pts[18][0]=-l/2;
         pts[18][1]=w/2;
         pts[18][2]=h/2;

         pts[19][0]=-l/2;
         pts[19][1]=w/2;
         pts[19][2]=-h/2;

               //line 11
         pts[20][0]=-l/2;
         pts[20][1]=-w/2;
         pts[20][2]=h/2;

         pts[21][0]=-l/2;
         pts[21][1]=-w/2;
         pts[21][2]=-h/2;

               //line 12
         pts[22][0]=l/2;
         pts[22][1]=-w/2;
         pts[22][2]=h/2;

         pts[23][0]=l/2;
         pts[23][1]=-w/2;
         pts[23][2]=-h/2;




  }
}