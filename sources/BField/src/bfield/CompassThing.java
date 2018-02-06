package bfield;
import java.awt.*;
import edu.davidson.tools.SApplet;

public class CompassThing extends FieldShell {
     int x1, y1, x2, y2;
     int ARROW_SIZE=3;
    // int PARTICLE_RAD=0;

     int px[] = new int[3];
     int py[] = new int[3];

    public double d = 0;

  public CompassThing(SApplet owner, FieldPanel fp, double x,double y, int radius) {
     super(owner,  fp,  x, y, radius);
     d=radius-4;
  }

  public void paint(Graphics osg){
      if(!visible) return;
      super.paint(osg);
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      double bx=fieldPanel.getBx( x,  y, null);
      double by=fieldPanel.getBy( x,  y, null);
      double b=Math.sqrt(bx * bx + by * by);
      osg.setColor(Color.red);
      osg.setFont(font);
      if(b==0){
          build(ptX,ptY,0,1);
          osg.fillPolygon(px, py, 3);
          osg.drawLine(x1, y1, x2, y2);
          build(ptX,ptY,1,0);
          osg.fillPolygon(px, py, 3);
          osg.drawLine(x1, y1, x2, y2);
          build(ptX,ptY,0,-1);
          osg.fillPolygon(px, py, 3);
          osg.drawLine(x1, y1, x2, y2);
          build(ptX,ptY,-1,0);
          osg.fillPolygon(px, py, 3);
          osg.drawLine(x1, y1, x2, y2);
          osg.setColor(Color.black);
          osg.drawString("?",ptX-4,ptY);
          return;
      }
      double sin=by/b;
      double cos=bx/b;
      if(d<=0) return;
      build(ptX,ptY,cos,sin);
      osg.fillPolygon(px, py, 3);
      osg.drawLine(x1, y1, x2, y2);
  }

  public void build(int x, int y, double cos, double sin) {
        double dx, dy;
        x1 =(int)(2*d*cos);
        y1 = (int)(-2*d*sin);
        x = x-x1/2; // offset so that we rotate about the center.
        y = y-y1/2;


        dx = x1/d;
        dy = y1/d;
        x1 += x;
        y1 += y;
        x2 = x;
        y2 = y;

       //int basex=(int)(2*d*cos-ARROW_SIZE * 2*cos);
        //int basey=(int)(-2*d*sin+ARROW_SIZE * 2*sin);

        int basex=x1-(int)(ARROW_SIZE * 2*cos);;
        int basey=y1+(int)(ARROW_SIZE * 2*sin);;

        px[0] = (int)(basex + ARROW_SIZE * 2 * dx);
        py[0] = (int)(basey + ARROW_SIZE * 2 * dy);

        px[1] = (int)(basex + ARROW_SIZE * dy);
        py[1] = (int)(basey - ARROW_SIZE * dx);

        px[2] = (int)(basex - ARROW_SIZE * dy);
        py[2] = (int)(basey + ARROW_SIZE * dx);
    }


}