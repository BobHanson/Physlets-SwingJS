package doppler;

import java.lang.Object;
import java.awt.*;


/*
 *
 * DopplerWaveCrest
 *
 */
class DopplerWaveCrest extends Object
{
	private int x0,y0;	// place where the Generation was created.
	//private double vx,vy;	// velocity when the Generation was created.
	private double t0;		// time when the Generation was created.

	public  DopplerWaveCrest(double t0_, int x0_, int y0_)
	{
	x0=x0_; y0=y0_;	t0=t0_;
	//vx=0; vx=0;
	}

	public  DopplerWaveCrest(double t0_,int x0_,int y0_,double vx_,double vy_)
	{
	x0=x0_; y0=y0_;	t0=t0_;
	//vx=vx_; vy=vy_;
	}
	
	public void translate(int x)
	{
	    x0=x0+x;
	}

	public void draw(double t, Graphics g)
	{
		int dt=(int)Math.round(t-t0);
		int rad=dt;
		int xc=x0-rad;
		int yc=y0-rad;
		g.drawOval(xc,yc,rad*2,rad*2);
	}



}

