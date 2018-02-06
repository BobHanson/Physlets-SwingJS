package engine;

import java.awt.*;

// **************************************************************
// class to hold a 3D point of the world's object
// **************************************************************
class Point3D  {
    protected
        double x, y, z;       // Coordinates of point
        double pa, pb;        // equivalent of point on  2D screen
        double depth;         // used in z-buffer algorithm
        int mode;            // 0..not connected, 1... connected
        Color col;
        int paintMode;       // 1.. paint with col, 0.. use background (do not paint)

    //***********************************************************
    public Point3D (int dx, int dy, int dz) {
        x=dx; y=dy; z=dz;
        mode=1; col = Color.black; paintMode = 1;
    }
    //***********************************************************
    public Point3D () {
        x = 0.0; y = 0.0; z = 0.0;
        mode = 1; col = Color.black; ; paintMode = 1;
    }
    //***********************************************************

    public void showPoint (Graphics g) {
    	    g.setColor(col);
            g.drawOval( (int) (pa),  (int) (pb),  (int) (1) ,  (int) (1) );
	}

}  /* class Point3D */









