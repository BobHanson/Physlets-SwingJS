package eField4;


import java.awt.*;


public class ShapeThing extends Thing {

  private int horz[];
  private int vert[];

  private int horz2[];
  private int vert2[];

  private int num;

  public ShapeThing(OdeCanvas o,int n, int h[], int v[] ,double x, double y){
      super(o,x,y,0.0,0.0);
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
      if(hideThing) return;
      int ptX=(int)Math.round(p.pixFromX(vars[1]) )+xDisplayOff;  // the base of the spring
      int ptY=(int)Math.round(p.pixFromY(vars[2]) )-yDisplayOff;
      g.setColor(color);
      g.fillPolygon(getHorz(ptX),getVert(ptY),num);
  }
}
