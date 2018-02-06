package engine;

import java.awt.*;

// *********************************************************************
// ************************ class World  *******************************
// *********************************************************************

public class World extends Canvas{
    Dimension offDimension;  // this is used in double-buffered repainting
    Dimension d;             // that is needed in animation
    Image offImage;          // to avoid flickering
     Graphics offGraphics; // of the image
    public int nOfPoints = 0;
    int numLinks = 0;
	public int nOfParticles = 0;
	public int nOfSegments = 0;
    public Point posMouse = new Point(0,0);
	Point3D [] points = new Point3D[1000];          // array of all points defining bodies
    Segment[] segments = new Segment[1000];           // array of all links defining bodies
    Particle[] particles = new Particle[1000];      // array of all particles

    public boolean traceOn = false;
    public int elementSelected =0;
    public int elementType =0;
    int mouseMode = 2;     // 0..pan, 1..zoom, 2..rotate, 3..select, 4..connect
    int selectedParticle = -1 ;     // index of selected particle otherwise
    public boolean updateImmediate = true;
    public boolean xorMode = false;
    public boolean doubleBuffering = true;
  // Constructors *****************************************************
  public World()  {
    super();
    for (int j=0;j<points.length;j++) points[j] = new Point3D();
    for (int j=0;j<particles.length;j++) particles[j] = new Particle();
    for (int j=0;j<segments.length;j++) segments[j] = new Segment();

   }   /* world */


    //******************************************************************
   /// public void setup(Dimension d) {
   ///         sx = d.width /2 ;
   ///         sz = d.height /2 -30;  // shift a little bit up because of netscape frame
    ///        defineCube();
   /// }

  // *****************************************************************
  // add a 3D point to array of points
  public void addPoint(int x1, int y1,int z1) {
      nOfPoints++;
      points[nOfPoints -1].x = x1;
      points[nOfPoints -1].y = y1;
      points[nOfPoints -1].z = z1;
  }  /* addPoint */

  // *****************************************************************
  // set coordinates of the 3D point (in the array)  to defined value
  public void setPoint(int i, int x1, int y1, int z1) {
        points[i].x = x1;
        points[i].y = y1;
        points[i].y = z1;
  }
  // ****************************************************************
   public boolean mouseMove (Event event, int x, int y) {
      posMouse.x = x;  posMouse.y = y;
      return false;

   }

// ****************************************************************
   public boolean mouseDrag (Event event, int x, int y) {
      posMouse.x = x;  posMouse.y = y;
      return false;

   }

}  // class World


