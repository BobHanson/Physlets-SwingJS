package edu.davidson.physlets.emwave4;
import java.awt.*;
public class Figure {

       public int numLines=0;
       double[][] pts=null;
       Color c=Color.blue;
       static int xOrigin,yOrigin;
       String figType="line";
       double pixPerUnit;


  public Figure() {

  }
  public void translate(double z){}
  public double getPtsValue(int i,int j) {return pts[i][j];}
  public double[][] getPts() {return pts;}
  public int getNumPts() {return 2*numLines;}
  public int getNumLines() {return numLines; }
  public void setColor(Color clr){}
  public void reSetColor(Graphics g) {g.setColor(c);}
  public void setOrigin(Graphics g) {g.translate(xOrigin,yOrigin);}
  public static void setOrigin(int x,int y) {xOrigin=x;yOrigin=y;}
  public void setOrigin(Graphics g, int x, int y) {g.translate(xOrigin=x,yOrigin=y);}
  public void resetOrigin(Graphics g) {g.translate(-xOrigin,-yOrigin);}
  public void draw(Graphics g,double[][] trans){}
  public void drawFigure(Graphics g,double[][] trans) {
          reSetColor(g);
          setOrigin(g);
          for (int i=0; i<getNumPts();i++)
               g.drawLine((int)Math.round(trans[0][0]*getPtsValue(i,0)+trans[0][1]*getPtsValue(i,1)+trans[0][2]*getPtsValue(i,2)),
                          (int)Math.round(trans[1][0]*getPtsValue(i,0)+trans[1][1]*getPtsValue(i,1)+trans[1][2]*getPtsValue(i,2)),
                          (int)Math.round(trans[0][0]*getPtsValue(++i,0)+trans[0][1]*getPtsValue(i,1)+trans[0][2]*getPtsValue(i,2)),
                          (int)Math.round(trans[1][0]*getPtsValue(i,0)+trans[1][1]*getPtsValue(i,1)+trans[1][2]*getPtsValue(i,2)));
            resetOrigin(g);
  }
}
