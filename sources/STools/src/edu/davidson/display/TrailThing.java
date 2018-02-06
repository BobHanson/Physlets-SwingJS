package edu.davidson.display;

import java.awt.*;
import edu.davidson.tools.SApplet;
import edu.davidson.graph.DataSet;

public class TrailThing extends Thing{
    DataSet dataset=new DataSet();
    double[] point=new double[2];

    public TrailThing(SApplet owner, SScalable sc, int s){
      super(sc,0,0);
      this.s=s;
      applet=owner;
    }

    public DataSet getDataSet(){return dataset;}

    public final void incTrail(double x, double y){
      this.x=x;
      this.y=y;
      point[0]=x;
      point[1]=y;
      try{
         dataset.append(point,1);
      }catch (Exception e){
              System.out.println("Error appending Data!");
      }
      if (trail==null || trailSize<1) return;
      int xpix=canvas.pixFromX(x);
      int ypix=canvas.pixFromY(y);
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

  public final void setTrailSize(int n){trailSize=n; clearTrail();}

  public void clearTrail(){
      if(trail!=null && trail.npoints==0){;}    // already have an empgy polygon so no need to allocate.
        else {
            trail=new Polygon();
            dataset=new DataSet();
        }
  }
}