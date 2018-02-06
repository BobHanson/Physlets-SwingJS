package optics;

import java.awt.*;

public final class Refraction extends Dielectric {
  public Refraction(Bench b, int xTransfer, int yTransfer, double delN,int radiusT,boolean i, boolean d,double s,boolean pd){
    super(b, xTransfer, yTransfer,delN, radiusT, i,  d, s,pd);
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    if(delN==0)return v; // no index change so return.
    if(focalLength==0){    // tag for a flat surface.
       return super.transform(v,r,direction);
    }
    double theta, phi; //theta is the incoming beam angle, phi is the angle of the face of the dielectric
    double tempV = v[0]-r.height/2.0;
    theta = Math.atan(v[1]);
    phi = -Math.atan(Math.abs(tempV/R));
    errCode=0;
    if(Math.abs(tempV)>=Math.abs(R)){   // ray is above the radius
        errCode=1;  // this will stop the ray
        v[1]=0;
        return v;
    }

    if(R>0) phi=-phi;
    if(tempV<0)phi=-phi;
    theta = theta+phi;
    // check for too large an angle
    if(Math.abs(theta)>Math.PI/2){v[1]=0; errCode=1; return v;}
    
    double ratio=indexOfRefraction/(delN+indexOfRefraction);
    if(direction==1){
        if(Math.abs(ratio*Math.sin(theta))<1 ){
            theta=Math.asin(Math.sin(theta)*ratio);
            theta = theta-phi;
        } else {v[1]=0; errCode=1; return v;}
    }
    else{
        if(Math.abs(Math.sin(theta)/ratio)<1 ){
          theta=Math.asin(Math.sin(theta)/ratio);
          theta = theta-phi;
        }
        else {v[1]=0; errCode=1; return v;}  // change errCode to 2 if you want internal reflection
    }
    if(Math.abs(theta)>Math.PI/2){v[1]=0; errCode=1; return v;}
    v[1]=Math.tan(theta);
    return v;
  }
}
