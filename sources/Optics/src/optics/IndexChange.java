package optics;

import java.awt.*;

public final class IndexChange extends Dielectric {

  public IndexChange(Bench b, int xTransfer, int yTransfer, double delN, boolean i, boolean d,double s,boolean pd){
      super(b, xTransfer, yTransfer,delN, 0, i,  d, s,pd);
  }

  public void setRadius(int rT,Rectangle r){
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    if(delN==0)return v;  // no change so return.
    double theta;
    double tempV = v[0]-r.height/2;
    theta=Math.atan(v[1]);
    errCode=0;
    if(direction==1){
        if( Math.abs(indexOfRefraction*Math.sin(theta)/(delN+indexOfRefraction))<=1 )
            theta=Math.asin(indexOfRefraction*Math.sin(theta)/(delN+indexOfRefraction));
        else{v[1]=-v[1]; errCode=2; return v;}
    }
    else{
        //if((v[1]>0 && Math.sin(theta)*(delN+indexOfRefraction)<=1) ||
           //(v[1]<0 && Math.sin(theta)*(delN+indexOfRefraction)>=-1) || v[1]==0)
          if((Math.abs(Math.sin(theta)*(delN+indexOfRefraction)/indexOfRefraction)<=1))
             theta=Math.asin(Math.sin(theta)*(delN+indexOfRefraction)/indexOfRefraction);
        else {v[1]=-v[1]; errCode=2; return v;}
    }
    v[1]=Math.tan(theta);
    return v;
  }

}
