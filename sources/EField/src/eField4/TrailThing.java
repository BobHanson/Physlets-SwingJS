package eField4;

import java.awt.Graphics;
import java.awt.Polygon;

//import java.awt.*;
import edu.davidson.graph.DataSet;

public class TrailThing extends Thing{
    DataSet dataset=new DataSet();
    double[] point=new double[2];

    public TrailThing(OdeCanvas p, int s){
      super(p,0,0,0,0);
      this.s=s;
      varStrings= new String[]{"t","x","y","fx","fy","p"};
      ds=new double[1][6];  // the datasource state variables
    }

    public final void incTrail(double x, double y){
      vars[1]=x;
      vars[2]=y;
      point[0]=x;
      point[1]=y;
      try{
         dataset.append(point,1);
      }catch (Exception e){
              System.out.println("Error appending Data!");
      }
      if (trail==null || trailSize<1) return;
      int xpix=p.pixFromX(x);
      int ypix=p.pixFromY(y);
      if (trail.npoints<trailSize){
          trail.addPoint(xpix,ypix);
      }else{
          System.arraycopy(trail.xpoints, 1, trail.xpoints, 0, trailSize-1);
          System.arraycopy(trail.ypoints, 1, trail.ypoints, 0, trailSize-1);
          trail.xpoints[trailSize-1]=xpix;
          trail.ypoints[trailSize-1]=ypix;
      }
  }

  public final void paint(Graphics g){
      g.setColor(color);
      if(trailSize>1 && trail.npoints>1){
              g.drawPolyline(trail.xpoints,trail.ypoints,trail.npoints);
      }
  }

 // public void setTrailSize(int n){trailSize=n; clearTrail();}

  public void clearTrail(){
      if(trail!=null && trail.npoints==0){;}    // already have an empgy polygon so no need to allocate.
        else {
            trail=new Polygon();
            dataset=new DataSet();
        }
  }

    public double[][] getVariables(){
     ds[0][0]=vars[0];  //t
     ds[0][1]=vars[1];  //x
     ds[0][2]=vars[2];  //y
     ds[0][3]=-p.dudx(vars[1],vars[2])+p.getPoleFx(vars[1],vars[2],null);   // fx
     ds[0][4]=-p.dudy(vars[1],vars[2])+p.getPoleFy(vars[1],vars[2],null);   // fy
     // potential
     if(p.parser!=null) ds[0][5] = p.parser.evaluate(vars[1],vars[2])+p.getPoleU(vars[1],vars[2]);
      else ds[0][5]= p.getPoleU(vars[1],vars[2]);
     return ds;
  }
}