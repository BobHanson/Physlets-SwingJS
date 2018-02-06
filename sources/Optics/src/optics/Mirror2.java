package optics;

import java.awt.*;

public class Mirror2 extends Mirror {
  public Mirror2(Bench b, int xTransfer, int yTransfer, double fl,boolean i, boolean d,double s,boolean pd){
    super(b, xTransfer,  yTransfer,  fl, i,  d, s, pd);
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    if (focalLength==0  || mat[1][0]==0){
        errCode=0;
        return super.transform(v,r,direction);
    }
    // v[0] is height and v[1] is slope of ray.
    double theta, phi; //theta is the incoming beam angle, phi is the angle of the mirror
    double tempV = v[0]-r.height/2;
    radius=(int)Math.abs(focalLength*2);
    theta = Math.atan(v[1]);
    phi = Math.atan(Math.abs(tempV/radius));
    errCode=0;

    if(Math.abs(tempV)>=Math.abs(radius)){   // ray is above the radius
        errCode=1;  // this will stop the ray
        v[1]=0;
        return v;
    }
    if(tempV<0)phi=-phi;
    theta = -theta+2*phi;
    // check for too large an angle
    if(Math.abs(theta)>Math.PI/2){v[1]=0; errCode=1; return v;}
    v[1]=Math.tan(theta);
    return v;
  }

}

