package wave3d;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Figure {

       public int numLines=0;
       double[][] pts=null;
       Color color=Color.blue;
       String figType="line";
       double pixPerUnit;
       boolean visible=true;
       double wavelength=200;      //cycles/pixel for waves
       double speed=5;
       Font       font=new Font("Monospaced",Font.BOLD,16);
       double polarization=0;
       ThreeDPanel threeDPanel;
       int lineDensity;
       double xyFactor=1.0;

  public Figure(ThreeDPanel panel){
    threeDPanel=panel;
    lineDensity=panel.lineDensity;
  }

  public int getID(){return this.hashCode();}
  public void translate(double dz, double time){}
  public void setWavelength(double l){wavelength=l;}
  public void setSpeed(double s){speed=s;}
  public double getPtsValue(int i,int j) {
    if(j==0 || j==1) return xyFactor*pts[i][j];
    return pts[i][j];
  }
 // public double[][] getPts() {return pts;}
  public int getNumPts() {return 2*numLines;}
  public int getNumLines() {return numLines; }
  public void setZ(double z){}
  public void setPolarization(double angle){polarization=angle;}
  
  void recompute(ThreeDPanel panel) {
	  
  }

  public void drawFigure(Graphics g,double[][] trans) {
          g.setColor(color);
          for (int i=0; i<getNumPts();i++)
               g.drawLine((int)Math.round(trans[0][0]*getPtsValue(i,0)+trans[0][1]*getPtsValue(i,1)+trans[0][2]*getPtsValue(i,2)),
                          (int)Math.round(trans[1][0]*getPtsValue(i,0)+trans[1][1]*getPtsValue(i,1)+trans[1][2]*getPtsValue(i,2)),
                          (int)Math.round(trans[0][0]*getPtsValue(++i,0)+trans[0][1]*getPtsValue(i,1)+trans[0][2]*getPtsValue(i,2)),
                          (int)Math.round(trans[1][0]*getPtsValue(i,0)+trans[1][1]*getPtsValue(i,1)+trans[1][2]*getPtsValue(i,2)));
  }
}
