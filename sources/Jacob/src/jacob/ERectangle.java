package jacob;

import java.awt.Graphics;

public class ERectangle
  extends Element
{
  public int x;
  public int y;
  public int width;
  public int height;
  
  public ERectangle()
  {
    this(0, 0, 0, 0);
  }
  
  public ERectangle(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramInt1, paramInt2, paramInt3, paramInt4, 0.0D, 0.0D);
  }
  
  public ERectangle(int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble1, double paramDouble2)
  {
    this.type = 1;
    this.x = paramInt1;
    this.y = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.Ex = paramDouble1;
    this.Ey = paramDouble2;
    this.centerx = (paramInt1 + paramInt3 / 2);
    this.centery = (paramInt2 + paramInt4 / 2);
    prepare();
  }
  
  public void move(int paramInt1, int paramInt2)
  {
    this.x += paramInt1;
    this.y += paramInt2;
    this.centerx = (this.x + this.width / 2);
    this.centery = (this.y + this.height / 2);
    prepare();
  }
  
  public void scale(int paramInt1, int paramInt2)
  {
    this.width = Math.abs(this.x - paramInt1);
    this.height = Math.abs(this.y - paramInt2);
    if (this.width < 2) {
      this.width = 2;
    }
    if (this.height < 2) {
      this.height = 2;
    }
    this.centerx = (this.x + this.width / 2);
    this.centery = (this.y + this.height / 2);
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
    this.bounds.x = (this.x - PPD.TOUCH_OFFSET);
    this.bounds.y = (this.y - PPD.TOUCH_OFFSET);
    this.bounds.width = (this.width + PPD.TOUCH_OFFSET * 2);
    this.bounds.height = (this.height + PPD.TOUCH_OFFSET * 2);
  }
  
  public double touching(int paramInt1, int paramInt2)
  {
    double[] arrayOfDouble = new double[6];
    double d = Double.MAX_VALUE;
    arrayOfDouble[0] = Double.MAX_VALUE;
    arrayOfDouble[1] = Double.MAX_VALUE;
    if ((paramInt1 >= this.x) && (paramInt1 <= this.x + this.width)) {
      arrayOfDouble[0] = Math.min(Math.abs(paramInt2 - this.y), Math.abs(paramInt2 - (this.y + this.height)));
    }
    if ((paramInt2 >= this.y) && (paramInt2 <= this.y + this.height)) {
      arrayOfDouble[1] = Math.min(Math.abs(paramInt1 - this.x), Math.abs(paramInt1 - (this.x + this.width)));
    }
    arrayOfDouble[2] = Math.sqrt((paramInt1 - this.x) * (paramInt1 - this.x) + (paramInt2 - this.y) * (paramInt2 - this.y));
    arrayOfDouble[3] = Math.sqrt((paramInt1 - (this.x + this.width)) * (paramInt1 - (this.x + this.width)) + (paramInt2 - (this.y + this.height)) * (paramInt2 - (this.y + this.height)));
    arrayOfDouble[4] = Math.sqrt((paramInt1 - this.x) * (paramInt1 - this.x) + (paramInt2 - (this.y + this.height)) * (paramInt2 - (this.y + this.height)));
    arrayOfDouble[5] = Math.sqrt((paramInt1 - (this.x + this.width)) * (paramInt1 - (this.x + this.width)) + (paramInt2 - this.y) * (paramInt2 - this.y));
    for (int i = 0; i < 6; i++) {
      d = Math.min(d, arrayOfDouble[i]);
    }
    return d;
  }
  
  public boolean intersects(Element paramElement)
  {
    switch (paramElement.type)
    {
    case 1: 
      return Calculus.intersects((ERectangle)paramElement, this);
    case 0: 
      return Calculus.intersects(this, (ECircle)paramElement);
    case 2: 
      return Calculus.intersects(this, (ERing)paramElement);
    }
    return false;
  }
  
  public boolean particleInside(Particle paramParticle)
  {
    int i = this.x;
    int j = this.y;
    int k = this.x + this.width;
    int m = this.y + this.height;
    return ((int)paramParticle.x >= i) && ((int)paramParticle.x <= k) && ((int)paramParticle.y >= j) && ((int)paramParticle.y <= m);
  }
  
  public void particleHold(Particle paramParticle)
  {
    if (!particleInside(paramParticle))
    {
      if (Calculus.reparentParticle(this.neightbours, paramParticle)) {
        return;
      }
      double d = paramParticle.x;
      paramParticle.x = paramParticle.oldx;
      if (Calculus.reparentParticle(this.neightbours, paramParticle)) {
        return;
      }
      paramParticle.x = d;
      d = paramParticle.y;
      paramParticle.y = paramParticle.oldy;
      if (Calculus.reparentParticle(this.neightbours, paramParticle)) {
        return;
      }
      paramParticle.y = d;
      if (paramParticle.x < this.x) {
        paramParticle.x = this.x;
      } else if (paramParticle.x > this.x + this.width) {
        paramParticle.x = (this.x + this.width);
      }
      if (paramParticle.y < this.y) {
        paramParticle.y = this.y;
      } else if (paramParticle.y > this.y + this.height) {
        paramParticle.y = (this.y + this.height);
      }
    }
  }
  
  public void particleReject(Particle paramParticle) {}
  
  public void drawObjFg(Graphics paramGraphics)
  {
    paramGraphics.drawRect(this.x, this.y, this.width, this.height);
  }
  
  public void drawObjBg(Graphics paramGraphics)
  {
    if (this.myColor != null) {
      paramGraphics.setColor(this.myColor);
    }
    paramGraphics.fillRect(this.x + PPD.SHADOW_OFFSET, this.y + PPD.SHADOW_OFFSET, this.width, this.height);
  }
  
  public void drawObjShadow(Graphics paramGraphics) {}
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/ERectangle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */