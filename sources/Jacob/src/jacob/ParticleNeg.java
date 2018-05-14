package jacob;

import java.awt.Graphics;

public class ParticleNeg
  extends Particle
{
  public ParticleNeg()
  {
    this(0.0D, 0.0D);
  }
  
  public ParticleNeg(double paramDouble1, double paramDouble2)
  {
    this(paramDouble1, paramDouble2, PPD.nullElement);
  }
  
  public ParticleNeg(double paramDouble1, double paramDouble2, Element paramElement)
  {
    this(paramDouble1, paramDouble2, paramElement, 1.0D);
  }
  
  public ParticleNeg(double paramDouble1, double paramDouble2, Element paramElement, double paramDouble3)
  {
    this(paramDouble1, paramDouble2, paramElement, 1.0D, PPD.PARTICLE_NEG_Q);
  }
  
  public ParticleNeg(double paramDouble1, double paramDouble2, Element paramElement, double paramDouble3, double paramDouble4)
  {
    super(paramDouble1, paramDouble2, paramElement, paramDouble3);
    this.type = 4;
    this.Q = paramDouble4;
  }
  
  public void draw(Graphics paramGraphics, boolean paramBoolean)
  {
    int i = (int)this.x;
    int j = (int)this.y;
    int k = PPD.PARTICLE_RAD / 2;
    if (this.k == 1.0D) {
      paramGraphics.setColor(PPD.PARTICLE_NEG_FOREGROUND);
    } else {
      paramGraphics.setColor(Draw.darker(PPD.PARTICLE_NEG_FOREGROUND, 1.0D - this.k / 6.0D));
    }
    paramGraphics.fillOval(i - PPD.PARTICLE_RAD, j - PPD.PARTICLE_RAD, PPD.PARTICLE_RAD * 2, PPD.PARTICLE_RAD * 2);
    paramGraphics.setColor(PPD.PARTICLE_NEG_BACKGROUND);
    paramGraphics.drawLine(i - k, j, i + k, j);
    if (paramBoolean)
    {
      if (PPD.arrows)
      {
        paramGraphics.setColor(PPD.PARTICLE_NEG_EARROW);
        this.arrow.build(i, j, this.Ex, this.Ey, PPD.arrow_scale);
        this.arrow.draw(paramGraphics);
      }
      if (PPD.arrows_mag)
      {
        paramGraphics.setColor(PPD.PARTICLE_NEG_MARROW);
        this.arrow.build(i, j, this.Mx, this.My, PPD.arrow_mag_scale);
        this.arrow.draw(paramGraphics);
      }
    }
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/ParticleNeg.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */