package engine;

import java.awt.Color;
import java.awt.Graphics;


//******************************************************************
// class particle
//******************************************************************

class Particle extends Point3D {
	public
	    double rx;      // size of particle (can be elypse)
	    double ry;
	    //int mode;       // 0..circle, 1..circle, 2..full circle, 4.. triangle

	//****************************************************************
  	// constructors...
	//****************************************************************
	public  Particle ()  {
        //  initialize instance variables to default values
        x = 0.0; 	y = 0.0; 	z = 0.0;
		mode = 0;
     	col = Color.black;
	    rx = 5.0;
		ry = 5.0;
		paintMode = 0; // default invisible
    	} // end particle
	//****************************************************************
	 public void showParticle (Graphics g, double height) {
	    int sizex, sizey;
	    sizex = (int) (rx * height);
	    sizey = (int) (ry * height);
    	g.setColor(col);
    	switch (mode) {
    	    case 0:  //  centered filled circle
                g.fillOval( (int) (pa-sizex),  (int) (pb-sizey),  (2*sizex) ,  (int) (2*sizey) );
                break;
    	    case 1:  // centered hollow circle
                g.drawOval( (int) (pa-sizex),  (int) (pb-sizey),  (int) (2*sizex) ,  (int) (2*sizey) );
                break;
            case 2:  // centered filled rectangle
                g.fillRect( (int) (pa-sizex),  (int) (pb-sizey),  (int) (2*sizex) ,  (int) (2*sizey) );
                break;
            case 3:  // centered hollow rectangle
                g.drawRect( (int) (pa-sizex),  (int) (pb- sizey),  (int) (2*sizex) ,  (int) (2*sizey) );
                break;
           	case 10:  //  filled circle
                g.fillOval( (int) (pa),  (int) (pb - 2*height),  (int) (2*sizex) ,  (int) (2*sizey) );
                break;
    	    case 11:  // hollow circle
                g.drawOval( (int) (pa),  (int) (pb - 2*sizey),  (int) (2*sizex) ,  (int) (2*sizey) );
                break;
            case 12:  // filled rectangle
                g.fillRect( (int) (pa),  (int) (pb - 2* sizey),  (int) (2*sizex) ,  (int) (2*sizey) );
                break;
            case 13:  // hollow rectangle
                g.drawRect( (int) (pa),  (int) (pb - 2*sizey),  (int) (2*sizex) ,  (int) (2*sizey) );
                break;
        }
	}

}

