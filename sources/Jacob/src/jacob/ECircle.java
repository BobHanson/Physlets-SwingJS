package jacob;

import java.awt.Graphics;

public class ECircle
  extends Element
{
  public int x;
  public int y;
  public int r;
  
  public ECircle()
  {
    this(0, 0, 0);
  }
  
  public ECircle(int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramInt1, paramInt2, paramInt3, 0.0D, 0.0D);
  }
  
  public ECircle(int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2)
  {
    this.type = 0;
    this.x = paramInt1;
    this.y = paramInt2;
    this.r = paramInt3;
    this.Ex = paramDouble1;
    this.Ey = paramDouble2;
    this.centerx = paramInt1;
    this.centery = paramInt2;
    prepare();
  }
  
  public void move(int paramInt1, int paramInt2)
  {
    this.x += paramInt1;
    this.y += paramInt2;
    this.centerx = this.x;
    this.centery = this.y;
    prepare();
  }
  
  public void scale(int paramInt1, int paramInt2)
  {
    this.r = ((int)Math.sqrt((this.x - paramInt1) * (this.x - paramInt1) + (this.y - paramInt2) * (this.y - paramInt2)));
    if (this.r < 2) {
      this.r = 2;
    }
    prepare();
  }
  
  public void prepare()
  {
    calcBounds();
    if ((this.Ex != 0.0D) || (this.Ey != 0.0D))
    {
      this.arrow.build(this.centerx, this.centery, this.Ex, this.Ey, this.ARROW_SCALE);
      this.bounds.add(this.arrow.px[0], this.arrow.py[0]);
    }
  }
  
  public void calcBounds()
  {
    this.bounds.x = (this.x - this.r - PPD.TOUCH_OFFSET);
    this.bounds.y = (this.y - this.r - PPD.TOUCH_OFFSET);
    this.bounds.width = (this.r * 2 + PPD.TOUCH_OFFSET * 2);
    this.bounds.height = (this.r * 2 + PPD.TOUCH_OFFSET * 2);
  }
  
  public double touching(int paramInt1, int paramInt2)
  {
    double d = Math.abs(Math.sqrt((this.x - paramInt1) * (this.x - paramInt1) + (this.y - paramInt2) * (this.y - paramInt2)) - this.r);
    return d;
  }
  
  public boolean intersects(Element paramElement)
  {
    switch (paramElement.type)
    {
    case 0: 
      return Calculus.intersects((ECircle)paramElement, this);
    case 1: 
      return Calculus.intersects((ERectangle)paramElement, this);
    case 2: 
      return Calculus.intersects(this, (ERing)paramElement);
    }
    return false;
  }
  
  public boolean particleInside(Particle paramParticle)
  {
    return (paramParticle.x - this.x) * (paramParticle.x - this.x) + (paramParticle.y - this.y) * (paramParticle.y - this.y) < this.r * this.r;
  }
  
  public void particleHold(Particle paramParticle)
  {
    if (!particleInside(paramParticle))
    {
      if (Calculus.reparentParticle(this.neightbours, paramParticle)) {
        return;
      }
      double d2 = paramParticle.x;
      paramParticle.x = paramParticle.oldx;
      if (Calculus.reparentParticle(this.neightbours, paramParticle)) {
        return;
      }
      paramParticle.x = d2;
      d2 = paramParticle.y;
      paramParticle.y = paramParticle.oldy;
      if (Calculus.reparentParticle(this.neightbours, paramParticle)) {
        return;
      }
      paramParticle.y = d2;
      double d1 = Math.sqrt((paramParticle.x - this.x) * (paramParticle.x - this.x) + (paramParticle.y - this.y) * (paramParticle.y - this.y));
      paramParticle.x = (this.x + this.r * ((paramParticle.x - this.x) / d1));
      paramParticle.y = (this.y + this.r * ((paramParticle.y - this.y) / d1));
    }
  }
  
  public void particleReject(Particle paramParticle) {}
  
  public void drawObjFg(Graphics paramGraphics)
  {
    paramGraphics.drawOval(this.x - this.r, this.y - this.r, this.r * 2, this.r * 2);
  }
  
  public void drawObjBg(Graphics paramGraphics)
  {
    if (this.myColor != null) {
      paramGraphics.setColor(this.myColor);
    }
    paramGraphics.fillOval(this.x - this.r + PPD.SHADOW_OFFSET, this.y - this.r + PPD.SHADOW_OFFSET, this.r * 2, this.r * 2);
  }
  
  public void drawObjShadow(Graphics paramGraphics) {}
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/ECircle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */