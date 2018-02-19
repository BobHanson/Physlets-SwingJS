package poisson;

//import java.awt.*;
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
  double length=15;

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

  public void setLength(double l){length=l;}  // length of vectors in pix

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

  public void paint(Graphics g, Rectangle r, boolean[][] isConductor, int gutter){
     if(row==0 || col==0) return;
     int i,j;
     int x1,y1,x2,y2,x,y;
     double fx,fy,mag;
     double w=2;  // the size of the arrow head
     int skip = (isConductor[0].length-2*gutter)/row;
     double xPixPerCell=(double)r.width/(double)col;
     double yPixPerCell=(double)r.height/(double)row;
     double vecLength=Math.sqrt(xPixPerCell*xPixPerCell+yPixPerCell*yPixPerCell);
     double xo=xPixPerCell/4.0;
     double yo=+yPixPerCell*3.0/4.0;

     for(i=0; i<row; i++) {
          y1 =(int)yo;
          xo=xPixPerCell/4.0;
          for(j=0; j<col; j++) {
                if (!isConductor[gutter+(j)*skip][gutter+(row-i-1)*skip]){
                  x1 =(int)xo;
                  fx=field[i][j][0];
                  fy=field[i][j][1];
                  mag=field[i][j][2];
                  g.setColor(colorFromMag(mag));
                  //x=(int)(10*fx);
                  //y=-(int)(10*fy);
                  x=(int)(vecLength*fx*skip/4.0);
                  y=-(int)(vecLength*fy*skip/4.0);
                  x2=x+x1;
                  y2=y+y1;
                  g.drawLine(x1,y1,x2,y2);
                  double u=(w*x/length);
                  double v=(w*y/length);
                  double base_x= x2-3*u;
                  double base_y= y2-3*v;
                  g.drawLine((int)(base_x-v),(int)(base_y+u),x2,y2);
                  g.drawLine((int)(base_x+v),(int)(base_y-u),x2,y2);
                }
                xo+=xPixPerCell;
          }
          yo+=yPixPerCell;
      }
  }
}
