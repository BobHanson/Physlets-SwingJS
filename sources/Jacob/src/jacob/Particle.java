package jacob;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Particle
{
  public int type;
  public double x;
  public double y;
  public double oldx;
  public double oldy;
  public double vx;
  public double vy;
  public double Mx;
  public double My;
  public double Ex;
  public double Ey;
  public double Q;
  public double k;
  public Element parent;
  public Particle dipol_partner = null;
  public Arrow arrow = new Arrow();
  
  public Particle()
  {
    this(0.0D, 0.0D, PPD.nullElement, 1.0D);
  }
  
  public Particle(double paramDouble1, double paramDouble2, Element paramElement, double paramDouble3)
  {
    this.x = (this.oldx = paramDouble1);
    this.y = (this.oldy = paramDouble2);
    this.parent = paramElement;
    this.k = paramDouble3;
    this.vx = 0.0D;
    this.vy = 0.0D;
  }
  
  public abstract void draw(Graphics paramGraphics, boolean paramBoolean);
  
  public void drawShadow(Graphics paramGraphics)
  {
    paramGraphics.setColor(Color.gray);
    int i = 1;
    if (PPD.SHADOW_OFFSET < 0) {
      i = -1;
    }
    for (int j = 1; j < Math.abs(PPD.SHADOW_OFFSET); j++)
    {
      int m = j * i;
      paramGraphics.fillOval((int)this.x - PPD.PARTICLE_RAD + m, (int)this.y - PPD.PARTICLE_RAD + m, PPD.PARTICLE_RAD * 2, PPD.PARTICLE_RAD * 2);
    }
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Particle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */