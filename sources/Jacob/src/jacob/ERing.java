package jacob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class ERing
  extends Element
{
  public int x;
  public int y;
  public int r1;
  public int r2;
  int GRAB_NULL = 0;
  int GRAB_OUT = 1;
  int GRAB_IN = 2;
  int grabPoint = this.GRAB_OUT;
  Polygon p = new Polygon();
  int circ_sep = 0;
  
  public ERing()
  {
    this(0, 0, 0);
  }
  
  public ERing(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, PPD.RING_MIN_SIZE + PPD.RING_MIN_WIDTH, PPD.RING_MIN_SIZE);
  }
  
  public ERing(int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramInt1, paramInt2, paramInt3, PPD.RING_MIN_SIZE);
  }
  
  public ERing(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramInt1, paramInt2, paramInt3, paramInt4, 0.0D, 0.0D);
  }
  
  public ERing(int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble1, double paramDouble2)
  {
    this.type = 2;
    this.x = paramInt1;
    this.y = paramInt2;
    this.r1 = paramInt3;
    this.r2 = paramInt4;
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
    int i = (int)Math.sqrt((this.x - paramInt1) * (this.x - paramInt1) + (this.y - paramInt2) * (this.y - paramInt2));
    if (this.grabPoint == this.GRAB_OUT)
    {
      int j = this.r1 - this.r2;
      this.r1 = i;
      if (this.r1 < PPD.RING_MIN_WIDTH + PPD.RING_MIN_SIZE) {
        this.r1 = (PPD.RING_MIN_WIDTH + PPD.RING_MIN_SIZE);
      }
      this.r2 = (this.r1 - j);
    }
    else if (this.grabPoint == this.GRAB_IN)
    {
      this.r2 = i;
    }
    if (this.r2 < PPD.RING_MIN_SIZE) {
      this.r2 = PPD.RING_MIN_SIZE;
    }
    if (this.r1 - this.r2 < PPD.RING_MIN_WIDTH) {
      this.r2 = (this.r1 - PPD.RING_MIN_WIDTH);
    }
    prepare();
  }
  
  public void prepare()
  {
    calcBounds();
    this.p.npoints = 0;
    for (double d1 = 0.0D; d1 <= 6.283185307179586D; d1 += PPD.ARC_STEP) {
      this.p.addPoint((int)(this.x + this.r1 * Math.cos(d1)), (int)(this.y + this.r1 * Math.sin(d1)));
    }
    this.circ_sep = this.p.npoints;
    this.p.addPoint(this.x + this.r2, this.y);
    for (double d2 = 0.0D; d2 <= 6.283185307179586D; d2 += PPD.ARC_STEP) {
      this.p.addPoint((int)(this.x + this.r2 * Math.cos(d2)), (int)(this.y + this.r2 * Math.sin(d2)));
    }
    Draw.trimPolyPoints(this.p);
    if ((this.Ex != 0.0D) || (this.Ey != 0.0D))
    {
      this.arrow.build(this.centerx, this.centery, this.Ex, this.Ey, this.ARROW_SCALE);
      this.bounds.add(this.arrow.px[0], this.arrow.py[0]);
    }
  }
  
  public void calcBounds()
  {
    this.bounds.x = (this.x - this.r1 - PPD.TOUCH_OFFSET);
    this.bounds.y = (this.y - this.r1 - PPD.TOUCH_OFFSET);
    this.bounds.width = (this.r1 * 2 + PPD.TOUCH_OFFSET * 2);
    this.bounds.height = (this.r1 * 2 + PPD.TOUCH_OFFSET * 2);
  }
  
  public double touching(int paramInt1, int paramInt2)
  {
    double d1 = Math.sqrt((this.x - paramInt1) * (this.x - paramInt1) + (this.y - paramInt2) * (this.y - paramInt2));
    double d2 = Math.abs(d1 - this.r1);
    double d3 = Math.abs(d1 - this.r2);
    double d4 = Math.min(d2, d3);
    if (d2 < d3) {
      this.grabPoint = this.GRAB_OUT;
    } else {
      this.grabPoint = this.GRAB_IN;
    }
    return d4;
  }
  
  public boolean intersects(Element paramElement)
  {
    switch (paramElement.type)
    {
    case 2: 
      return Calculus.intersects((ERing)paramElement, this);
    case 0: 
      return Calculus.intersects((ECircle)paramElement, this);
    case 1: 
      return Calculus.intersects((ERectangle)paramElement, this);
    }
    return false;
  }
  
  public boolean particleInside(Particle paramParticle)
  {
    double d = (paramParticle.x - this.x) * (paramParticle.x - this.x) + (paramParticle.y - this.y) * (paramParticle.y - this.y);
    return (d <= this.r1 * this.r1) && (d >= this.r2 * this.r2);
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
      if (d1 < this.r2)
      {
        paramParticle.x = (this.x + this.r2 * ((paramParticle.x - this.x) / d1));
        paramParticle.y = (this.y + this.r2 * ((paramParticle.y - this.y) / d1));
      }
      else
      {
        paramParticle.x = (this.x + this.r1 * ((paramParticle.x - this.x) / d1));
        paramParticle.y = (this.y + this.r1 * ((paramParticle.y - this.y) / d1));
      }
    }
  }
  
  public void particleReject(Particle paramParticle)
  {
    double d = Math.sqrt((paramParticle.x - this.x) * (paramParticle.x - this.x) + (paramParticle.y - this.y) * (paramParticle.y - this.y));
    paramParticle.x = (this.x + this.r1 * ((paramParticle.x - this.x) / d));
    paramParticle.y = (this.y + this.r1 * ((paramParticle.y - this.y) / d));
  }
  
  public void drawObjFg(Graphics paramGraphics)
  {
    Color localColor1 = PPD.ELEMENT_FOREGROUND;
    Color localColor2 = PPD.ELEMENT_FOREGROUND;
    if ((this.drawMode == 1) && (this.grabPoint == this.GRAB_IN))
    {
      localColor2 = PPD.ELEMENT_HIGHLIGHT;
      if (!this.etransient) {
        localColor1 = PPD.ELEMENT_TRANSIENT;
      }
    }
    else if ((this.drawMode == 1) && (this.grabPoint == this.GRAB_OUT))
    {
      localColor1 = PPD.ELEMENT_HIGHLIGHT;
      if (!this.etransient) {
        localColor2 = PPD.ELEMENT_TRANSIENT;
      }
    }
    paramGraphics.setColor(localColor1);
    for (int i = 1; i < this.circ_sep; i++) {
      paramGraphics.drawLine(this.p.xpoints[(i - 1)], this.p.ypoints[(i - 1)], this.p.xpoints[i], this.p.ypoints[i]);
    }
    paramGraphics.setColor(localColor2);
    for (int j = this.circ_sep + 2; j < this.p.npoints; j++) {
      paramGraphics.drawLine(this.p.xpoints[(j - 1)], this.p.ypoints[(j - 1)], this.p.xpoints[j], this.p.ypoints[j]);
    }
  }
  
  public void drawObjBg(Graphics paramGraphics)
  {
    if (this.myColor != null) {
      paramGraphics.setColor(this.myColor);
    }
    this.p.translate(PPD.SHADOW_OFFSET, PPD.SHADOW_OFFSET);
    paramGraphics.fillPolygon(this.p);
    this.p.translate(-PPD.SHADOW_OFFSET, -PPD.SHADOW_OFFSET);
  }
  
  public void drawObjShadow(Graphics paramGraphics) {}
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/ERing.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */