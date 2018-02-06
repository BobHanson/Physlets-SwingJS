package eField4;

import java.awt.*;
import edu.davidson.numerics.*;

public class TestCharge extends Charge implements SDifferentiable{
  SRK45      odeSolver=new SRK45();
  private double[] dydx=new double[5];

  public TestCharge(OdeCanvas p, double x,double y,double vx,double vy){
      super(p,x,y,vx,vy,1.0);
      odeSolver.setDifferentials(this);
      sticky=false;
      dynamic=true;
      noDrag=false;
  }
  public TestCharge(OdeCanvas p, double x,double y,double vx,double vy, double m) {
      super(p,x,y,vx,vy,m);
      odeSolver.setDifferentials(this);
      sticky=false;
      dynamic=true;
      noDrag=false;
  }

  public int getNumEqu(){return 5;}

  public final double[] constrainedRate(double[] x){
      // the rate equations for a test charge with a constraint, f(x).
      dydx[0]=1;
      double speed=Math.sqrt(x[3]*x[3]+x[4]*x[4]);
      double m=getConstraintSlope(x[1],Math.abs(x[1])*1.0e-2+1.0e-6);  // first derivative
      double v=-1/Math.sqrt(1+m*m); // the direction cosine of the slope;
      double u=-m*v; // the direction sine of the slope;
      double m2=getConstraintSecondDeriv(x[1],Math.abs(x[1])*1.0e-2+1.0e-6);
      double kappa=Math.abs(m2)/Math.pow(1+m*m,1.5);
      //(u,v) is in the radial direction
      //(-v,u) is tangent

      dydx[1]=x[3];
      dydx[2]=x[4];

      double ax=mag*(-p.dudx(x[1],x[2])+p.getPoleFx(x[1],x[2],null)+p.bz*x[4]);
      double ay=mag*(-p.dudy(x[1],x[2])+p.getPoleFy(x[1],x[2],null)-p.bz*x[3]);
      double acc=(-ax*v+ay*u);  // the acceleration along the tangent.
      double acc2=-speed*speed*kappa;  // the acceleration along the radius.

      dydx[3]=(acc2*u-acc*v-damping*x[3])/mass;  // project the force perpendicular onto the tangent
      dydx[4]=(acc2*v+acc*u-damping*x[4])/mass;
      vars[5]=dydx[3];
      vars[6]=dydx[4];
      return dydx;
  }

  public final double[] constrainedRRate(double[] x){
      dydx[0]=1;   // time rate
      enforceConstraintOnR();  // this will also project the speed onto the tangent.
      //double rad=Math.sqrt((vars[1]-constantRx)*(vars[1]-constantRx)+(vars[2]-constantRy)*(vars[2]-constantRy)); // current radius.
      double speed=Math.sqrt(x[3]*x[3]+x[4]*x[4]);

      double u=(vars[1]-constantRx)/constantR;   // (u,v) is unit vector along radial direction
      double v=(vars[2]-constantRy)/constantR;   // (-v,u) is unit vector tangent to the radial direction

      dydx[1]=x[3]; // x position rate
      dydx[2]=x[4];

      double ax=mag*(-p.dudx(x[1],x[2])+p.getPoleFx(x[1],x[2],null)+p.bz*x[4]);
      double ay=mag*(-p.dudy(x[1],x[2])+p.getPoleFy(x[1],x[2],null)-p.bz*x[3]);
      double acc=(-ax*v+ay*u);  // the acceleration along the tangent.
      double acc2=-speed*speed/constantR;  // the acceleration along the radius.

      dydx[3]=(acc2*u-acc*v-damping*x[3])/mass;  // project the force perpendicular onto the tangent
      dydx[4]=(acc2*v+acc*u-damping*x[4])/mass;
      vars[5]=dydx[3];
      vars[6]=dydx[4];
      if(p.dragShape==this){
        dydx[3]=0;
        dydx[4]=0;
      }
      return dydx;
  }

  public final double[] constrainedXRate(double[] x){
      x[1]=constantX;
      dydx[0]=1;
      dydx[1]=0; // x is fixed.
      dydx[2]=x[4];
      dydx[3]=0;
      dydx[4]=(mag*(-p.dudy(x[1],x[2])+p.getPoleFy(x[1],x[2],null)-p.bz*x[3])-damping*x[4])/mass;
      vars[5]=0;
      vars[6]=dydx[4];
      return dydx;
  }

  public final double[] constrainedYRate(double[] x){
      x[2]=constantY;
      dydx[0]=1;
      dydx[1]=x[3];
      dydx[2]=0;
      dydx[3]=(mag*(-p.dudx(x[1],x[2])+p.getPoleFx(x[1],x[2],null)+p.bz*x[4])-damping*x[3])/mass;
      dydx[4]=0;
      vars[5]=dydx[3];
      vars[6]=0;
      return dydx;
  }

  public final double[] rate(double[] x){
      // the rate equations for a test charge.
      if(p.dragShape==this){
        x[3]=0;
        x[4]=0;
      }
      if (constraint!=null) return constrainedRate(x);
      else if (constrainX) return constrainedXRate(x);
      else if (constrainY) return constrainedYRate(x);
      else if (constrainR) return constrainedRRate(x);
      dydx[0]=1;   // time rate
      dydx[1]=x[3]; //position rate is velocity
      dydx[2]=x[4];
      dydx[3]=(mag*(-p.dudx(x[1],x[2])+p.getPoleFx(x[1],x[2],null)+p.bz*x[4])-damping*x[3])/mass;  //ax
      dydx[4]=(mag*(-p.dudy(x[1],x[2])+p.getPoleFy(x[1],x[2],null)-p.bz*x[3])-damping*x[4])/mass;  //ay
      vars[5]=dydx[3]; // ax
      vars[6]=dydx[4]; // ay
      if(p.dragShape==this){
        dydx[3]=0;
        dydx[4]=0;
      }
      return dydx;
  }

}