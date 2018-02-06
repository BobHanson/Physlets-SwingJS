

package edu.davidson.physlets.emwave4;

import java.awt.*;
import java.util.*;


public class ThreeDPanel extends Panel {
     double theta, alpha, phi;
     double[][] trans=new double[2][3];
     Vector figs=new Vector();
     Image osi=null;
     int iwidth,iheight;


  public ThreeDPanel() {

  }

  public void setIsometric() {

         trans[0][0]=-1/Math.sqrt(2);   //isometiric perspective
         trans[0][1]=-trans[0][0];
         trans[0][2]=0;
         trans[1][0]=-1/Math.sqrt(6);
         trans[1][1]=trans[1][0];
         trans[1][2]=-2*trans[1][0];

         repaint();
  }

  public void transformMatrix() {
              trans[0][0]=(Math.cos(alpha)*Math.cos(theta));
              trans[0][1]=-(-Math.cos(alpha)*Math.sin(theta));
              trans[0][2]=(-Math.sin(alpha));
              trans[1][0]=(-Math.cos(theta)*Math.sin(alpha)*Math.sin(phi)+Math.cos(phi)*Math.sin(theta));
              trans[1][1]=-(Math.cos(phi)*Math.cos(theta)+Math.sin(alpha)*Math.sin(phi)*Math.sin(theta));
              trans[1][2]=(-Math.cos(alpha)*Math.sin(phi));
  }

  public void addFigure(Figure f) {
         figs.addElement(f);
  }

  public void update(Graphics g){paint(g);}

  public void paint(){
    Graphics g=getGraphics();
    if(g==null) return;
    paint(g);
  }

  public void paint(Graphics g) {
         Rectangle r=getBounds();
         if(osi==null ||r.width!=iwidth || r.height!=iheight){
             iheight=r.height;
             iwidth=r.width;
             osi=createImage(iwidth, iheight);
         }
         Graphics osg=osi.getGraphics();
         osg.setColor(Color.white);
         osg.fillRect(0,0,iwidth,iheight);
         Figure f;
         for (int i=0;i<figs.size(); i++) {
             f=(Figure)figs.elementAt(i);
             f.drawFigure(osg,trans);
         }
         osg.dispose();
         g.drawImage(osi,0,0,this);

  }

   public void translate(double dz) {
         Figure f;
         for (int i=0;i<figs.size(); i++) {
             f=(Figure)figs.elementAt(i);
            f.translate(dz);
         }
         repaint();

  }

  public void setAngles(double t,double a,double p){
         theta=t;
         alpha=a;
         phi=p;
         transformMatrix();
         repaint();
  }


/*
 private final void drawFigure(Figure f, Graphics g) {
          f.reSetColor(g);
          f.setOrigin(g);
          String figType=f.getType();
          if (figType=="line")
            for (int i=0; i<f.getNumPts();i++)
               g.drawLine((int)Math.round(trans[0][0]*f.getPtsValue(i,0)+trans[0][1]*f.getPtsValue(i,1)+trans[0][2]*f.getPtsValue(i,2)),
                          (int)Math.round(trans[1][0]*f.getPtsValue(i,0)+trans[1][1]*f.getPtsValue(i,1)+trans[1][2]*f.getPtsValue(i,2)),
                          (int)Math.round(trans[0][0]*f.getPtsValue(++i,0)+trans[0][1]*f.getPtsValue(i,1)+trans[0][2]*f.getPtsValue(i,2)),
                          (int)Math.round(trans[1][0]*f.getPtsValue(i,0)+trans[1][1]*f.getPtsValue(i,1)+trans[1][2]*f.getPtsValue(i,2)));
/*
          if (figType=="oval"){
             int xCorner,yCorner,width,height;
             xCorner=(int)Math.round(trans[0][0]*f.getPtsValue(0,0)+trans[0][1]*f.getPtsValue(0,1)+trans[0][2]*f.getPtsValue(0,2));
             yCorner=(int)Math.round(trans[1][0]*f.getPtsValue(0,0)+trans[1][1]*f.getPtsValue(0,1)+trans[1][2]*f.getPtsValue(0,2));
             width=  (int)Math.round(trans[0][0]*f.getPtsValue(1,0)+trans[0][1]*f.getPtsValue(1,1)+trans[0][2]*f.getPtsValue(1,2))-(int)Math.round(trans[0][0]*f.getPtsValue(2,0)+trans[0][1]*f.getPtsValue(2,1)+trans[0][2]*f.getPtsValue(2,2));
             height= (int)Math.round(trans[1][0]*f.getPtsValue(3,0)+trans[1][1]*f.getPtsValue(3,1)+trans[1][2]*f.getPtsValue(3,2))-(int)Math.round(trans[1][0]*f.getPtsValue(4,0)+trans[1][1]*f.getPtsValue(4,1)+trans[1][2]*f.getPtsValue(4,2));
             g.fillOval(xCorner,yCorner,width,height);
          }


        if (figType=="text"){
              g.setFont(new Font("SansSerif",Font.BOLD,14));
              g.drawString("x",
                          (int)Math.round(trans[0][0]*f.getPtsValue(1,0)+trans[0][1]*f.getPtsValue(1,1)+trans[0][2]*f.getPtsValue(1,2)),
                          (int)Math.round(trans[1][0]*f.getPtsValue(1,0)+trans[1][1]*f.getPtsValue(1,1)+trans[1][2]*f.getPtsValue(1,2)));
              g.drawString("y",
                          (int)Math.round(trans[0][0]*f.getPtsValue(3,0)+trans[0][1]*f.getPtsValue(3,1)+trans[0][2]*f.getPtsValue(3,2)),
                          (int)Math.round(trans[1][0]*f.getPtsValue(3,0)+trans[1][1]*f.getPtsValue(3,1)+trans[1][2]*f.getPtsValue(3,2)));
              g.drawString("z",
                          (int)Math.round(trans[0][0]*f.getPtsValue(5,0)+trans[0][1]*f.getPtsValue(5,1)+trans[0][2]*f.getPtsValue(5,2)),
                          (int)Math.round(trans[1][0]*f.getPtsValue(5,0)+trans[1][1]*f.getPtsValue(5,1)+trans[1][2]*f.getPtsValue(5,2)));
          }


          f.resetOrigin(g);


  }
*/

  public boolean registrationCheck(Object p){
         Object figure=p;
         boolean present;
         present=figs.contains(figure);
         return present;
  }

  public void setTheta(double t) {
         theta=t;
         transformMatrix();
         repaint();
  }


  public void setAlpha(double a) {
         alpha=a;
         transformMatrix();
         repaint();
  }


  public void setPhi(double p) {
         phi=p;
         transformMatrix();
         repaint();
  }

  public void clear(){
         figs.removeAllElements();
  }

}
