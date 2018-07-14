package engine;

import java.awt.Color;
import java.awt.Graphics;

//import Point3D;
// **************************************************************
// class to hold a segment (link) from one point to another
// **************************************************************
class Segment {
   protected
    Point3D p1, p2;            // endpoints of the segment
    //double x1, y1, z1, x2, y2, z2;
   // double px1, px2, py1, py2; // projected values (perspective)
   // double depth1, depth2;     // used in z-buffer algorithms
    Color col;
    int mode;      // 0..line, 1.. vector (arrow), 2.. box-ending

    //***********************************************************
    public Segment () {
      col = Color.black;
      mode = 0;  // =1 ????
      p1 = new Point3D();
      p2 = new Point3D();
      p1.x = 0.0; p1.y = 0.0; p1.z = 0.0;
      p2.x = 0.0; p2.y = 0.0; p2.z = 0.0;
    }
    //***********************************************************

     public void showSegment (Graphics g) {
        int r = 5;
    	g.setColor(col);
        switch(mode) {
            case 0:
                // draw simple line
                g.drawLine((int) (p1.pa),(int) (p1.pb), (int)(p2.pa) , (int) (p2.pb));
                break;
            case 1:
                // line ends with an arrow
                drawArrow(g, p1.pa, p1.pb, p2.pa, p2.pb);
                break;
            case 2:
                // draw line  that ends with a box
                g.drawLine((int) (p1.pa),(int) (p1.pb), (int)(p2.pa) , (int) (p2.pb));
                g.fillRect( (int) (p2.pa-r),  (int) (p2.pb-r), (2*r) , (2*r) );
                break;
        }
     }

    //******************************************************************

 	void drawArrow(Graphics g, double px1, double pz1, double px2, double pz2) {
		double dxa, dxA, dza, dzA, S;
		double AS, aS;  // introduced to speed-up the computation
		int x1, z1, x2, z2, xa, za, xb, zb, dx, dz;
		int A = 10;
		int a = 5;

		x1 = (int) (px1); //// + sx);
		z1 = (int) (pz1 ) ; ////+ sz);
		x2 = (int) (px2 );////+ sx);
		z2 = (int) (pz2);//// + sz);
        dx = x2 - x1;  dz = z2 - z1;
        if ((Math.abs(dx)>1)||(Math.abs(dz) >1)) {
		    xa = xb = x2;  za = zb = z2;
      		if (dx == 0) {
         		xa = x2 + a; xb = x2 - a;
			if( dz < 0) {
				za = zb = z2 + A;
			}
			if( dz > 0) {
				za = zb = z2 - A;
			}
      		}
      		else if (dz == 0) {
			za = z2 + a; zb = z2 - a;
			if (dx>0) {
				xa = xb = x2 - A;
            		}
			if (dx<0) {
				xa =  xb = x2 + A;
            		}
         	}
         	else {
            	S = Math.sqrt(dx*dx + dz * dz);
			    AS = A/S; aS = a/S;
			    dxA = dx * AS;  dzA = dz * AS;
			    dxa = dz * aS;  dza = dx * aS;
			    xa = x2 - (int) (dxA - dxa);
			    za = z2 - (int) (dzA + dza); //
			    xb = x2 - (int) (dxA + dxa);
			    zb = z2 - (int) (dzA - dza); //
         	}

      		g.drawLine(x1, z1, x2, z2);
      		g.drawLine(x2, z2, xa , za);
      		g.drawLine(x2, z2, xb , zb);
      	}
   	}

}  /* class Segment */

