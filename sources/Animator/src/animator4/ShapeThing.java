package animator4;

import a2s.*;
import java.awt.Graphics;

import edu.davidson.tools.SUtil;

public class ShapeThing extends Thing {

  private int horz[];
  private int vert[];

  private int horz2[];
  private int vert2[];

  private int num;

  public ShapeThing(AnimatorCanvas o,int n, int h[], int v[] ,String xStr, String yStr){
      super(o,xStr,yStr);
      s=1;
      horz= new int[n];
      vert= new int[n];
      horz2= new int[n];
      vert2= new int[n];
      num=n;
      for(int i=0; i<n; i++)
      {
        horz[i]=h[i];
        vert[i]=-v[i];
      }
  }

  int[] getHorz(int off){
    for(int i=0;i<num;i++){horz2[i]=horz[i]+off;}
    return horz2;
  }

  int[] getVert(int off){
    for(int i=0;i<num;i++){vert2[i]=vert[i]+off;}
    return vert2;
  }
  
  public void paint(Graphics g){
      if(!visible)return;
      paintGhosts(g);
      int ptX=(int)Math.round(canvas.pixFromX(vars[1]) )+xDisplayOff;  // the base of the spring
      int ptY=(int)Math.round(canvas.pixFromY(vars[2]) )-yDisplayOff;
      g.setColor(color);
      g.fillPolygon(getHorz(ptX),getVert(ptY),num);
      paintTrail(g);
      if (showCoordinates) paintCoordinates(g, ptX, ptY);
      super.paint(g);
  }

  public final void paintGhosts(Graphics g){
      if(!visible)return;
      // paint ghost images before the thing.
      if(ghost && footPrints>0 && trailSize>1 && trail.npoints>1){
          g.setColor(SUtil.veryPaleColor(color));
          for(int i=0;i<trail.npoints;i+=footPrints){
              g.fillPolygon(getHorz(trail.xpoints[i]+xDisplayOff),getVert(trail.ypoints[i]-yDisplayOff),num);
          }
      }
  }
} 