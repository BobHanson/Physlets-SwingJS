package wave3d;

import java.awt.*;

public class Grid extends Figure {
  double length;

  /**
   * A grid of lines used to represent a polarizer.
   *
   * @param z1
   * @param length
   * @param width
   * @param p
   * @param clr
   */
  public Grid(ThreeDPanel panel, double z1, double length, double width, double p, Color clr) {
    super(panel);
    this.length=length;
    numLines = (int) Math.round(width / panel.lineDensity);
    double zPosition = z1;
    color        = clr;
    pts          = new double[2 * numLines][3];
    polarization = p;
    for(int i = 1, j = -numLines / 2; i < 2 * numLines; i += 2, j++) {
      pts[i][0] = Math.cos(polarization) * j *  panel.lineDensity - Math.sin(polarization) * length / 2;
      pts[i][1] = Math.sin(polarization) * j *  panel.lineDensity + Math.cos(polarization) * length / 2;
      pts[i][2] = zPosition;
    }
    for(int i = 0, j = -numLines / 2; i < 2 * numLines; i += 2, j++) {
      pts[i][0] = Math.cos(polarization) * j *  panel.lineDensity + Math.sin(polarization) * length / 2;
      pts[i][1] = Math.sin(polarization) * j *  panel.lineDensity - Math.cos(polarization) * length / 2;
      pts[i][2] = zPosition;
    }
  }

  public void setZ(double z1) {
    for(int i = 0, n=pts.length; i<n; i++) {
      pts[i][2] = z1;
    }
  }

  public void setPolarization(double angle){
    polarization = angle;
    for(int i = 1, j = -numLines / 2; i < 2 * numLines; i += 2, j++) {
      pts[i][0] = Math.cos(polarization) * j * lineDensity - Math.sin(polarization) * length / 2;
      pts[i][1] = Math.sin(polarization) * j * lineDensity + Math.cos(polarization) * length / 2;
    }
    for(int i = 0, j = -numLines / 2; i < 2 * numLines; i += 2, j++) {
      pts[i][0] = Math.cos(polarization) * j * lineDensity + Math.sin(polarization) * length / 2;
      pts[i][1] = Math.sin(polarization) * j * lineDensity - Math.cos(polarization) * length / 2;
    }
  }

}
