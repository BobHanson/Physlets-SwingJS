package jacob;

import java.awt.Graphics;

public class Arrow
{
  public int x1;
  public int y1;
  public int x2;
  public int y2;
  public int[] px = new int[3];
  public int[] py = new int[3];
  public double d = 0.0D;
  
  public void build(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2)
  {
    build(paramInt1, paramInt2, paramDouble1, paramDouble2, PPD.arrow_scale);
  }
  
  public void build(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    this.x1 = ((int)(paramDouble3 * paramDouble1));
    this.y1 = ((int)(paramDouble3 * paramDouble2));
    this.d = Math.sqrt(this.x1 * this.x1 + this.y1 * this.y1);
    double d1;
    double d2;
    if (this.d > 0.1D)
    {
      d1 = this.x1 / this.d;
      d2 = this.y1 / this.d;
    }
    else
    {
      d1 = 0.0D;
      d2 = 0.0D;
    }
    this.x1 += paramInt1;
    this.y1 += paramInt2;
    this.x2 = ((int)(paramInt1 + PPD.PARTICLE_RAD * d1));
    this.y2 = ((int)(paramInt2 + PPD.PARTICLE_RAD * d2));
    this.px[0] = ((int)(this.x1 + PPD.ARROW_SIZE * 2 * d1));
    this.py[0] = ((int)(this.y1 + PPD.ARROW_SIZE * 2 * d2));
    this.px[1] = ((int)(this.x1 + PPD.ARROW_SIZE * d2));
    this.py[1] = ((int)(this.y1 - PPD.ARROW_SIZE * d1));
    this.px[2] = ((int)(this.x1 - PPD.ARROW_SIZE * d2));
    this.py[2] = ((int)(this.y1 + PPD.ARROW_SIZE * d1));
  }
  
  public void draw(Graphics paramGraphics)
  {
    if (this.d > 0.1D)
    {
      paramGraphics.fillPolygon(this.px, this.py, 3);
      paramGraphics.drawLine(this.x1, this.y1, this.x2, this.y2);
    }
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Arrow.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */