package eField4;
import java.awt.*;
import edu.davidson.tools.*;

public class ArrowHead {
  OdeCanvas p;
  double x,y,u,v;
  int s=4; // size of head
  Color color=Color.black;

  public ArrowHead(OdeCanvas p, double x,double y, double u, double v,Color c){
      this.x=x;
      this.y=y;
      this.u=u;
      this.v=v;
      this.p=p;
      this.color=c;
  }

  public final double getX(){return x;}
  public final void setX(double x){
      this.x=x;
      return;
  }

  public final double getY(){return y;}
  public final void setY(double y){
      this.y=y;
      return;
  }

  public final double getU(){return u;}
  public final void setU(double u){
      this.u=u;
      return;
  }

  public final double getV(){return v;}
  public final void setV(double v){
      this.v=v;
      return;
  }

  public final void paint(Graphics g, Rectangle r){
      int x0=p.pixFromX(x);
      int y0=p.pixFromY(y);
      int x1=p.pixFromX(x+u);
      int y1=p.pixFromY(y+v);
 //     if(x0<=0 || y0<=0 || x1<=0 || y1<=0) return;
 //     if(x0>=r.width || y0>=r.height || x1>=r.width || y1>=r.height) return;
      g.setColor(color);
      SUtil.drawArrowHead(g,x0,y0,x1,y1,s);
  }

  public final int getSize(){return s;}
  public final void setSize(int sz){s=sz;}

  public final Color getColor(){return color;}
  public final void setColor(Color c){color=c;}

}