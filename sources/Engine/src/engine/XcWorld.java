package engine;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Rectangle;

//*************************************************************************
public class XcWorld extends World {
// this class is used in xdj xdev to java converter

    public
        String msgString;  // drawn message
        int msgXpos, msgYpos;
        Color msgColor;
    
        double xmin, xmax, ymin, ymax;
        int sx =0;              // sx, sz.. offset of image on displayed frame
        int sz =0;

    //*************************************************************************
	public XcWorld(){
	    super();
	    xmin = 0.0; ymin = 0.0;
	    xmax = 1.0; ymax =  1.0;// dimensions of Xc world
        msgXpos = 10; 
        msgYpos = 10; 
        msgColor = Color.white; 
        msgString = new String("                   ");
	    
	}

	//*******************************************************************************

	public void XcWorldMovePoint(int i, double x1, double y1) {
	    points[i-1].x = x1;
        points[i-1].y = y1;
	}

    //*******************************************************************************

    public void XcWorldChangePointColor (int i, String c){
       c.toLowerCase();
       points[i-1].paintMode = 1;  // suppose normal painting
	   if (c.equals("black")) points[i-1].col = Color.black;
	     else
	     if (c.equals("blue")) points[i-1].col = Color.blue;
	     else
	     if (c.equals("cyan")) points[i-1].col = Color.cyan;
	     else
	     if (c.equals("darkgray")) points[i-1].col = Color.darkGray;
	     else
	     if (c.equals("gray")) points[i-1].col = Color.gray;
	     else
	     if (c.equals("green")) points[i-1].col = Color.green;
	     else
	     if (c.equals("lightgray")) points[i-1].col = Color.lightGray;
	     else
	     if (c.equals("magenta")) points[i-1].col = Color.magenta;
	     else
	     if (c.equals("orange")) points[i-1].col = Color.orange;
	     else
	     if (c.equals("pink")) points[i-1].col = Color.pink;
	     else
	     if (c.equals("red")) points[i-1].col = Color.red;
	     else
	     if (c.equals("white")) points[i-1].col = Color.white;
	     else
	     if (c.equals("yellow")) points[i-1].col = Color.yellow;
	     else
	     if (c.equals("background")) {
	        points[i-1].paintMode = 0; // background (no paint)
	        //points[i-1].col = Color.magenta;
	     }
	     else points[i-1].col = Color.white;  // default color
	}

	//*******************************************************************************

    public void XcWorldChangePointMode (int i, int m){
        points[i-1].mode = m;
	}

	//*******************************************************************************
    public void XcWorldMoveParticle(int i, double x1, double y1) {
        particles[i-1].x = x1;
        particles[i-1].y = y1;
	}


    //*******************************************************************************

    public void XcWorldChangeParticleColor (int i, String c){
        c.toLowerCase();
	    if (c.equals("black")) particles[i-1].col = Color.black;
	     else
	     if (c.equals("blue")) particles[i-1].col = Color.blue;
	     else
	     if (c.equals("cyan")) particles[i-1].col = Color.cyan;
	     else
	     if (c.equals("darkgray")) particles[i-1].col = Color.darkGray;
	     else
	     if (c.equals("gray")) particles[i-1].col = Color.gray;
	     else
	     if (c.equals("green")) particles[i-1].col = Color.green;
	     else
	     if (c.equals("lightgray")) particles[i-1].col = Color.lightGray;
	     else
	     if (c.equals("magenta")) particles[i-1].col = Color.magenta;
	     else
	     if (c.equals("orange")) particles[i-1].col = Color.orange;
	     else
	     if (c.equals("pink")) particles[i-1].col = Color.pink;
	     else
	     if (c.equals("red")) particles[i-1].col = Color.red;
	     else
	     if (c.equals("white")) particles[i-1].col = Color.white;
	     else
	     if (c.equals("yellow")) particles[i-1].col = Color.yellow;
	     else particles[i-1].col = Color.white;  // default color
	}

	//*******************************************************************************

    public void XcWorldChangeParticleMode (int i, int m){
        particles[i-1].mode = m;
	}

	//*******************************************************************************

    public void XcWorldChangeParticleSize (int i, int size){
        particles[i-1].rx = 0.5 *size;
        particles[i-1].ry = 0.5 *size;
	}
//*******************************************************************************

    public void XcWorldChangeParticleSizes (int i, int sizex, int sizey){
        particles[i-1].rx = 0.5 *sizex;
        particles[i-1].ry = 0.5 *sizey;
	}

	//*******************************************************************************

	public void XcWorldMoveSegment(int i,
					 double x11, double y11,
					 double x22, double y22) {
	    segments[i-1].p1.x = x11;
	    segments[i-1].p1.y = y11;
	    segments[i-1].p2.x = x22;
	    segments[i-1].p2.y = y22;
	}
   //*******************************************************************************

    public void XcWorldChangeSegmentColor (int i, String c){
         c.toLowerCase();
	     if (c.equals("black")) segments[i-1].col = Color.black;
	     else
	     if (c.equals("blue")) segments[i-1].col = Color.blue;
	     else
	     if (c.equals("cyan")) segments[i-1].col = Color.cyan;
	     else
	     if (c.equals("darkgray")) segments[i-1].col = Color.darkGray;
	     else
	     if (c.equals("gray")) segments[i-1].col = Color.gray;
	     else
	     if (c.equals("green")) segments[i-1].col = Color.green;
	     else
	     if (c.equals("lightgray")) segments[i-1].col = Color.lightGray;
	     else
	     if (c.equals("magenta")) segments[i-1].col = Color.magenta;
	     else
	     if (c.equals("orange")) segments[i-1].col = Color.orange;
	     else
	     if (c.equals("pink")) segments[i-1].col = Color.pink;
	     else
	     if (c.equals("red")) segments[i-1].col = Color.red;
	     else
	     if (c.equals("white")) segments[i-1].col = Color.white;
	     else
	     if (c.equals("yellow")) segments[i-1].col = Color.yellow;
	     else segments[i-1].col = Color.white;  // default color
	}

	//**********************************************************************


    public void XcWorldChangeSegmentMode (int i, int m){
        segments[i-1].mode = m;

	}

	//*******************************************************************************
    public void XcWorldUpdate () {
        Graphics g = this.getGraphics();
        if(g==null) return;
        if(traceOn) draw(g);
        else {
            if(!doubleBuffering){
                repaint();
            }
            else {
	            startUpdate(g);           // update using double buffering method - start
                draw(offGraphics);   // update visualisation
                finishUpdate(g);          // finish update using double buffering method
	        }
	    }
	}
    //****************************************************************
     public  void startUpdate (Graphics g) {
        // First, initial part of repainting (using double buffering method)
        d = this.getSize();
        if ( (offGraphics == null)
             || (d.width != offDimension.width)
             || (d.height != offDimension.height) ) {
           offDimension = d;
           offImage = createImage(d.width, d.height);
           offGraphics = offImage.getGraphics();
        }
          // erase the previous image
        if(offGraphics==null) return;
        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0, 0, d.width, d.height);
        offGraphics.setColor(Color.black);
        if(Engine.isJS) {
        	offGraphics.drawRect (0, 0, d.width-1, d.height-1);
        }else{ 
        	offGraphics.draw3DRect (0, 0, d.width-1, d.height-1, true);
        }    
   }
 //**************************************************************
   public void finishUpdate (Graphics g) {
        // last part of repainting (using double buffering method)
        g.drawImage (offImage, 0, 0, this);
   }
	//********************************************************************

	public void paintMe (Graphics g) {
	    draw (g);
	}
	
	public void update(Graphics g) {
		draw (g);
	}
    //********************************************************************

	public void draw (Graphics g) {

	    Point3D p;
	    Particle p1;
	    Segment s;
	    Rectangle w = this.getBounds();  // size of world rectangle
	    double x0 = 0.0;  // used in mode 1 to remember position of previous point
	    double y0 = 0.0;
	    double xRange, yRange, particleHeight;
        g.setColor(Color.black);
        g.fillRect(0, 0, w.width, w.height);
	    {
	       ///// if(xorMode) g.setXORMode(Color.black); else g.setPaintMode();
	        xRange = w.width /(xmax - xmin);
	        yRange = w.height /(ymax - ymin);
		    for (int i=0; i < nOfParticles; i++)  {
		        p1 = particles[i];
		        p1.pa = (p1.x - xmin)*xRange;
		        particleHeight = yRange ; //p1.ry * yRange;
		        p1.pb = w.height -((p1.y - ymin)* yRange);
                p1.showParticle(g, particleHeight); // paint the particle
		    }

		    for (int i=0; i < nOfPoints; i++)  {
		        p = points[i];
		        p.pa = (p.x - xmin)* xRange;
		        p.pb = w.height -((p.y - ymin)* yRange);
		        if((p.mode==1)&&(i>0)) {
		            if(p.paintMode >0) {
		                // the selcetted color is one of drawable colors
    	                g.setColor(p.col);  // draw line between previous and this point
		                g.drawLine((int) x0, (int) y0, (int) p.pa, (int) p.pb);
		            }  // else the selected color was a background and therefore skipped
		        }
                p.showPoint(g); // paint the point
		        x0 = p.pa; y0 = p.pb;  // remember this coordanates as previous ones
		    }

		    for (int i=0; i < nOfSegments; i++)  {
		        s = segments[i];
		        s.p1.pa = (s.p1.x - xmin)* xRange;
		        s.p1.pb = w.height -((s.p1.y - ymin)* yRange);
		        s.p2.pa = (s.p2.x - xmin)* xRange;
		        s.p2.pb = w.height - ((s.p2.y - ymin)* yRange);
                s.showSegment(g); // paint the segment
		    }
        }
        g.setColor(msgColor);
        g.drawString(msgString, msgXpos, msgYpos);
	}


    //********************************************************************

   public boolean mouseDown (Event event, int x1, int y1) {
   double  dx, dy;
   double xm, ym; // mouse position in XcWorld coordinate system
   double delta = 5.0;
    ///  int h = this.size().height;
      elementSelected = 0;  // suppose no element selected
      posMouse.x = x1;  posMouse.y = y1;
      xm = posMouse.x ;
      ym = posMouse.y ;

      // find which element is picked
      int i=1;
      while ((elementSelected==0)&&(i<nOfParticles)) {
        dx = Math.abs(particles[i-1].pa - xm );
        dy = Math.abs(particles[i-1].pb - ym);
        if ((dx < delta)&& (dy < delta)) {
            elementSelected = i; elementType = 1;
        }
        i++ ;
      }
      i = 1;
      while ((elementSelected==0)&&(i<nOfSegments)) {
        dx = Math.abs(segments[i-1].p1.pa - xm);
        dy = Math.abs(segments[i-1].p1.pb - ym);
        if ((dx < delta)&& (dy < delta)) {
            elementSelected = i; elementType = 2;

        }
        dx = Math.abs(segments[i-1].p2.pa - xm);
        dy = Math.abs(segments[i-1].p2.pb - ym);
        if ((dx < delta)&& (dy < delta)) {
            elementSelected = i; elementType = 2;

        }
        i++ ;
      }
      i = 1;
      while ((elementSelected==0)&&(i<nOfPoints)) {
        dx = Math.abs(points[i-1].pa - xm);
        dy = Math.abs(points[i-1].pb - ym);
        if ((dx < delta)&& (dy < delta)) {
            elementSelected = i; elementType = 3;

        }
        i++ ;
      }
      if (elementSelected >0) {
      }
      return false;

   }
}