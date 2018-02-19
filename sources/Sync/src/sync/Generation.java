package sync;
import java.awt.Graphics;
import java.lang.Object;
//import java.awt.*;


/*
*
* Generation
*
 */
class Generation extends Object
{
  static final int numPts=20;
  private double theta=0;	 //first field line in rest frame;
  private double dTheta=2*Math.PI/numPts;	// increment in rest frame

  private double x0,y0;	// place where the Generation was created.
  private double vx,vy;	// velocity when the Generation was created.
  private double beta, gamma; //standard relativity constants
  private int t0;		// time when the Generation was created.
  public double[] xPts=new double[numPts];
  public double[] yPts=new double[numPts];

  public  Generation(int t0_, int x0_, int y0_)
  {
    x0=x0_; y0=y0_;	t0=t0_;
    vx=0; vx=0;	 beta=0; gamma=1;
  }

  public  Generation(int t0_,double x0_,double y0_,double vx_,double vy_)
  {
    x0=x0_; y0=y0_;	t0=t0_;
    vx=vx_; vy=vy_;
    beta=Math.sqrt(vx*vx+vy*vy);  // beta is the speed since c=1
    if (beta>=1)beta=0.9999;
    gamma=1/Math.sqrt(1-beta*beta);
  }


  public  void shift( int x_, int y_)
  {
    x0=x0+x_;  y0=y0+y_;
  }


  public void draw(int t, Generation nextGen, Graphics g)
      //return the size of the event sphere.
  {
    if(nextGen==null)System.out.println("Error:Null nextgen");
    //Assert.notNull(nextGen,"NextGen is null");
    if (g==null) return;
    int dt=t-t0;
    double rad=dt;	// radius of light cone.  Assume a speed of 1
    double xc=vx*dt; //charge position if it had moved with constant velocity
    double yc=vy*dt;

    int i;  // loop counter
    double m,c,dis;
    double denom; // the cos of theta
    double thetaF; // the angle of the field line from the direction of motion.
    double thetaV; // angle of the velocity vector;
    double xR,yR; //intersction of field line with light cone
    theta=0.0001;  // works better if we don't get infinity!
    for(i=0;i<numPts;i++)
    {
      /**denom=Math.cos(theta);
   if(denom!=0) m=gamma*Math.sin(theta)/denom	 **/
  //atan2(y,x) gives -Pi to Pi with positive clockwise rotation from the x axis.
      thetaV=Math.atan2(vy,vx);
   thetaF=Math.atan2(gamma*Math.sin(theta-thetaV),Math.cos(theta-thetaV));
   denom=Math.cos(thetaF+thetaV);
   if (denom!=0)m=Math.tan(thetaF+thetaV);	 //check for infinite TAN
   else
     if (thetaF+thetaV>0)m=+1000.0;
   else m=-1000.0;
   c=yc-xc*m;
   dis=4*m*m*c*c-4*(1+m*m)*(c*c-rad*rad);
   if (denom>0) xR=(-2*m*c+Math.sqrt(dis))/(2*(1+m*m));
   else xR=(-2*m*c-Math.sqrt(dis))/(2*(1+m*m));
   yR=m*xR+c;
   xPts[i]=x0+xR;
   yPts[i]=y0+yR;
   theta=theta+dTheta;
   //g.drawOval((int)xPts[i],(int)yPts[i],2,2);
   g.drawLine((int)xPts[i],(int)yPts[i],(int)nextGen.xPts[i],(int)nextGen.yPts[i]);
    }
  }

  public void draw(int t, int xx, int yy, Graphics g)
      //return the size of the event sphere.
  {
    if (g==null) return;
    int dt=t-t0;
    double rad=dt;	// radius of light cone.  Assume a speed of 1
    double xc=vx*dt; //charge position if it had moved with constant velocity
    double yc=vy*dt;

    int i;  // loop counter
    double m,c,dis;
    double denom; // the cos of theta
    double thetaF; // the angle of the field line from the direction of motion.
    double thetaV; // angle of the velocity vector;
    double xR,yR; //intersction of field line with light cone
    theta=0.0001;  // works better if we don't get infinity!
    for(i=0;i<numPts;i++)
    {
      //atan2(y,x) gives -Pi to Pi with positive clockwise rotation from the x axis.
      thetaV=Math.atan2(vy,vx);
      thetaF=Math.atan2(gamma*Math.sin(theta-thetaV),Math.cos(theta-thetaV));
      denom=Math.cos(thetaF+thetaV);
      if (denom!=0)m=Math.tan(thetaF+thetaV);	 //check for infinite TAN
      else
        if (thetaF+thetaV>0)m=+1000.0;
      else m=-1000.0;
      c=yc-xc*m;
      dis=4*m*m*c*c-4*(1+m*m)*(c*c-rad*rad);
      if (denom>0) xR=(-2*m*c+Math.sqrt(dis))/(2*(1+m*m));
      else xR=(-2*m*c-Math.sqrt(dis))/(2*(1+m*m));
      yR=m*xR+c;
      xPts[i]=x0+xR;
      yPts[i]=y0+yR;
      theta=theta+dTheta;
      g.drawLine((int)xPts[i],(int)yPts[i],xx,yy);
    }
  }

}

abstract class State extends Object
{
  protected double x=0,y=0,u=0,v=0,t=0;
  protected double xo=0,yo=0;	 //origin
  double vMax=0.5;
  double newMax=0.5;
  double theta=0;

  public abstract double resetTime();
  public final double getX() {return x;}
  public final double getV() {return v;}
  public final double getY() {return y;}
  public final double getU() {return u;}
  public final void	setOrigin(double xo_, double yo_){xo=xo_; yo=yo_;}
  public final void	shiftOrigin(double x_, double y_){xo=xo+x_; yo=yo+y_;x=x+x_;y=y+y_;}
  public final void	setVMax(double vMax_){
    newMax=vMax_;
    if(t==0){
      vMax=vMax_;
    }
  }
  public abstract double incTime(double dt);
}

class SHOState extends State
{
  double A=10;  // amplitude

  public final double incTime(double dt_){
    if(newMax>vMax) vMax=vMax+Math.min(0.1,newMax-vMax);
    if(newMax<vMax) vMax=vMax+Math.max(-0.1,newMax-vMax);
    double omega=vMax/A;
    theta=theta+omega*dt_;
    t=t+dt_;
    x=xo;
    v=0; //motion along y direction only
    y=yo+A*Math.sin(theta);
    u=vMax*Math.cos(theta);
    return t;
  }

  public final double resetTime()
  {
    x=xo; y=yo; t=0; theta=0;v=0;u=vMax; return 0;
  }
}

class SyncState extends State
{
  double A=10;  // amplitude

  public double incTime(double dt_){
    if(newMax>vMax) vMax=vMax+Math.min(0.1,newMax-vMax);
    if(newMax<vMax) vMax=vMax+Math.max(-0.1,newMax-vMax);
    double omega=vMax/A;
    theta=theta+omega*dt_;
    t=t+dt_;
    x=xo+A*Math.cos(theta);
    v=-vMax*Math.sin(theta);
    y=yo+A*Math.sin(theta);
    u=vMax*Math.cos(theta);
    return t;
  }
  public final double resetTime()
  {
    x=xo+A; y=yo; t=0; theta=0;v=0;u=vMax; return 0;
  }

}

class InertialState extends State
{
  public double incTime(double dt_){
    if(newMax>vMax) vMax=vMax+Math.min(0.05,newMax-vMax);
    if(newMax<vMax) vMax=vMax+Math.max(-0.05,newMax-vMax);
    t=t+dt_;
    x=x+vMax*dt_;
    v=vMax;
    y=yo;
    u=0;
    return t;
  }
  public final double resetTime()
  {
    x=xo; y=yo; t=0; theta=0;v=vMax;u=0; return t;
  }

}

class WigglerState extends State
{
  double A=5;  // amplitude
  double L=40;  // wavelength
  double m; // slope of dy/dx
  double k=2*Math.PI/L;

  public final double incTime(double dt_){
    if(newMax>vMax) vMax=vMax+Math.min(0.1,newMax-vMax);
    if(newMax<vMax) vMax=vMax+Math.max(-0.1,newMax-vMax);
    t=t+dt_;
    x=xo+theta/k;
    m=A*k*Math.cos(k*x);
    y=yo+A*Math.sin(theta);
    v=vMax/Math.sqrt(1+m*m); // velocity along x direction
    if (m>0)u=Math.sqrt(vMax*vMax-v*v);
    else u=-Math.sqrt(vMax*vMax-v*v);
    theta=theta+k*v*dt_; // update the theta using the x velocity
    return t;
  }

  public final double resetTime()
  {
    x=xo; y=yo; t=0; theta=0;v=0;u=vMax; return 0;
  }
}





