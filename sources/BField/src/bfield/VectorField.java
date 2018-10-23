package bfield;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public final class VectorField {
  static int halfWidth= 175;
  static int[] saturation=new int[halfWidth*2];
  double[][][] field;
  int row=0;
  int col=0;
  double scale=1.0;

  public VectorField(int r, int c){
    field = new double[r][c][3];
    calSaturation();
    row=r;
    col=c;
  }

  private static void calSaturation(){
    int w=halfWidth*2;
    for(int i=0; i<w;i++){
       double arg=1.4*(i-halfWidth)/halfWidth;
       saturation[i]=(int)(255*Math.exp(-arg*arg));;
    }
  }

  public double[][][] resize(int r, int c){
    if ((r==row) && (c==col)) return field;
    field = new double[r][c][3];
    row=r;
    col=c;
    return field;
  }

  public void setGridValue(int r, int c, double fx, double fy, double mag){
      field[r][c][0]=fx;
      field[r][c][1]=fy;
      field[r][c][2]=mag;
  }

  private final Color getColor(double x){
       int r=0,g=0,b=0;
       int index=(int)(100*x);
     //  System.out.println("index:"+ index);
       if(index>100-halfWidth && index<100+halfWidth){
          b=saturation[index-100+halfWidth];
       }
       if(index>275-halfWidth && index<275+halfWidth){
          g=saturation[index-275+halfWidth];
       }
       if(index>450-halfWidth && index<450+halfWidth){
         r=saturation[index-450+halfWidth];
       }
       return new Color(r,g,b);
    }

    private final Color colorFromMag(double m){
        int rv,gv,bv;
        m*=scale;
        if (m>1){
            return getColor(m);
        }else{
        rv=(int)(255*(1-m));
        gv=(int)(255*(1-m));
        bv=255;
        return new Color(rv,gv,bv);
      }
    }

  public void paint(Graphics g, Rectangle r){
     int i,j;
     int x1,y1,x2,y2;
     double x,y;
     double dx,dy;
     double h;
     int temp;
     double fx,fy,mag;
     double w=2;  // the size of the arrow head
     dx= r.width/(double)(col-1);
     dy= r.height/(double)(row-1);
     h=(0.7*dx); // the length of the arrow
     for(i=0; i<row; i++) {
          y1 = r.height-(int) (i*dy);// +r.y;
          temp=y1;
          for(j=0; j<col; j++) {
                x1 =(int) (j*dx );//+r.x;
                fx=field[i][j][0];
                fy=field[i][j][1];
                mag=field[i][j][2];
                g.setColor(colorFromMag(mag));
                x= h*fx;
                y=-h*fy;
                x2=(int)(x1+x/2);
                y2=(int)(y1+y/2);
                x1=(int)(x2-x);
                y1=(int)(y2-y);
                g.drawLine(x1,y1,x2,y2);
                double u=w*fx;
                double v=-w*fy;
                double base_x= x2-3*u;
                double base_y= y2-3*v;
                g.drawLine((int)(base_x-v),(int)(base_y+u),x2,y2);
                g.drawLine((int)(base_x+v),(int)(base_y-u),x2,y2);
                y1=temp;  // restore y1 since it got trashed.
          }
      }
  }
}
