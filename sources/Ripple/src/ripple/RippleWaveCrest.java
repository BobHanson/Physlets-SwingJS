package ripple;

import java.awt.Graphics;
import java.lang.Object;



/*
 *
 * Generation
 *
 */
class RippleWaveCrest extends Object
{
	private int x0,y0;	// place where the Generation was created.
	private int t0;		// time when the Generation was created.

	public  RippleWaveCrest(int t0_, int x0_, int y0_)
	{
	x0=x0_; y0=y0_;	t0=t0_;
	}


	public void draw(int t, Graphics g,int xoff, int yoff)
	{
		int rad=t-t0;    //wave moves on pix per time step
		int xc=x0-rad;
		int yc=y0-rad;
		g.drawOval(xoff+xc,yoff+yc,rad*2,rad*2);
	}



}

