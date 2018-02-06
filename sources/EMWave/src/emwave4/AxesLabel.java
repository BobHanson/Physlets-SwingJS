

package emwave4;

import java.awt.*;

public class AxesLabel extends Axis3D {

  public AxesLabel(int x,int y, int z, double s, Color clr) {
         super(x,y,z,s,clr);
         figType="text";

         }
  public void drawFigure(Graphics g,double[][] trans) {
              reSetColor(g);
              setOrigin(g);

              g.setFont(new Font("SansSerif",Font.BOLD,14));
              g.drawString("x",
                          (int)Math.round(trans[0][0]*getPtsValue(1,0)+trans[0][1]*getPtsValue(1,1)+trans[0][2]*getPtsValue(1,2)),
                          (int)Math.round(trans[1][0]*getPtsValue(1,0)+trans[1][1]*getPtsValue(1,1)+trans[1][2]*getPtsValue(1,2)));
              g.drawString("y",
                          (int)Math.round(trans[0][0]*getPtsValue(3,0)+trans[0][1]*getPtsValue(3,1)+trans[0][2]*getPtsValue(3,2)),
                          (int)Math.round(trans[1][0]*getPtsValue(3,0)+trans[1][1]*getPtsValue(3,1)+trans[1][2]*getPtsValue(3,2)));
              g.drawString("z",
                          (int)Math.round(trans[0][0]*getPtsValue(5,0)+trans[0][1]*getPtsValue(5,1)+trans[0][2]*getPtsValue(5,2)),
                          (int)Math.round(trans[1][0]*getPtsValue(5,0)+trans[1][1]*getPtsValue(5,1)+trans[1][2]*getPtsValue(5,2)));

              resetOrigin(g);
  }


}
