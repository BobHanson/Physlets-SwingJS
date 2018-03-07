

package wave3d;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import a2s.*;

import java.util.*;


public class ThreeDPanel extends Panel {
     double theta, alpha, phi;
     double[][] trans=new double[2][3];
     Vector figs=new Vector();
     Image osi=null;
     int iwidth,iheight;
     int xOrigin,yOrigin;
     int lineDensity=6;          //lines/pixel --between 0-1
     String message="";


  public void setOrigin(int x,int y) {xOrigin=x;yOrigin=y;}

  public Figure getThing(int id) {

        for (Enumeration e = figs.elements(); e.hasMoreElements(); ) {
            Figure fig = (Figure) e.nextElement();
            if (fig.hashCode() == id) {
                return fig;
            }
        }

        return null;
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

  /**
   * Delete an object from the applet.
   *
   * @param id the object identifier
   *
   * @return true if successful
   */
  public boolean deleteObject(int id){
    Figure fig= getThing(id);
    if(fig==null) return false;
    figs.removeElement(fig);
    return true;
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
             if(iheight>0 && iwidth>0)osi = createImage(iwidth,iheight);  // Java 7 fails if width or height >0
             else return;
         }
         Graphics osg=osi.getGraphics();
         osg.setColor(Color.white);
         osg.fillRect(0,0,iwidth,iheight);
         for (int i=0;i<figs.size(); i++) {
             Figure f=(Figure)figs.elementAt(i);
             if(!f.visible) continue;
             if(!(f instanceof Text)) osg.translate(xOrigin,yOrigin);
             f.drawFigure(osg,trans);
             if(!(f instanceof Text)) osg.translate(-xOrigin,-yOrigin);
         }
         osg.dispose();
         g.drawImage(osi,0,0,this);
         paintMessage(g);
  }

    void paintMessage(Graphics osg) {
        if (message == null || message.equals("")) {
            return;
        }

        FontMetrics fm = osg.getFontMetrics(osg.getFont());

        osg.setColor(Color.yellow);

        int w = 15 + fm.stringWidth(message);

        osg.fillRect(iwidth - w - 5, iheight - 15, w, 15);
        osg.setColor(Color.black);
        osg.drawString(message, iwidth - w + 2, iheight - 3);
        osg.drawRect(iwidth - w - 5, iheight - 16, w, 15);
    }

   public void translate(double dz, double time) {
         Figure f;
         for (int i=0;i<figs.size(); i++) {
             f=(Figure)figs.elementAt(i);
            f.translate(dz, time);
         }
         repaint();

  }

  public void setMessage(String msg){
    if(msg==null) message="";
    message=msg;
    repaint();
  }

  public void setAngles(double t,double a,double p){
         theta=t;
         alpha=a;
         phi=p;
         transformMatrix();
         repaint();
  }

  final void drawLabel(Graphics g) {
         Figure f;
         f=(Figure)figs.elementAt(0);
         ///f.setOrigin(g);  removed by W. Christian
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
