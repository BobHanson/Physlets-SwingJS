package jacob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

public abstract class Element
{
  Color myColor = null;
  boolean dragable = true;
  boolean scalable = true;
  public int type;
  public int drawMode = 0;
  public double Ex;
  public double Ey;
  public double EEx;
  public double EEy;
  public int centerx;
  public int centery;
  public Rectangle bounds = new Rectangle();
  public Vector neightbours = new Vector();
  public Arrow arrow = new Arrow();
  public boolean movable = false;
  public boolean etransient = true;
  public boolean force_circ = false;
  public boolean force_translate = false;
  public boolean force_rotate = false;
  public double ARROW_SCALE = 300.0D;
  public Element connection = null;
  public double connectionI = 0.0D;
  public double connectionQ = 0.0D;
  public Plotter currPlotter = null;
  public double period = 10.0D;
  public double posCount = 0.0D;
  public double negCount = 0.0D;
  public double potencial = 0.0D;
  public Plotter energyPlotter = null;
  public double energyPlotterP = 0.0D;
  public double energyPlotterE = 0.0D;
  
  public abstract void move(int paramInt1, int paramInt2);
  
  public abstract void scale(int paramInt1, int paramInt2);
  
  public abstract double touching(int paramInt1, int paramInt2);
  
  public abstract boolean intersects(Element paramElement);
  
  public abstract boolean particleInside(Particle paramParticle);
  
  public abstract void particleHold(Particle paramParticle);
  
  public abstract void particleReject(Particle paramParticle);
  
  public abstract void prepare();
  
  public abstract void calcBounds();
  
  public abstract void drawObjFg(Graphics paramGraphics);
  
  public abstract void drawObjBg(Graphics paramGraphics);
  
  public abstract void drawObjShadow(Graphics paramGraphics);
  
  public void setDrawMode(int paramInt)
  {
    this.drawMode = paramInt;
  }
  
  public void setForce(int paramInt1, int paramInt2)
  {
    this.Ex = ((paramInt1 - this.centerx) / this.ARROW_SCALE);
    this.Ey = ((paramInt2 - this.centery) / this.ARROW_SCALE);
    prepare();
  }
  
  public void delForce()
  {
    this.Ex = 0.0D;
    this.Ey = 0.0D;
    this.arrow.d = 0.0D;
    this.force_circ = false;
    this.force_translate = false;
    this.force_rotate = false;
    prepare();
  }
  
  public void tagTransient()
  {
    this.etransient = (!this.etransient);
  }
  
  public void tagMove()
  {
    this.movable = (!this.movable);
    this.EEx = (this.EEy = 0.0D);
  }
  
  public void tagForceCirc()
  {
    this.force_circ = ((!this.force_circ) && ((this.Ey != 0.0D) || (this.Ex != 0.0D)));
    this.force_rotate = (this.force_translate = false);
  }
  
  public void tagForceRotate()
  {
    this.force_rotate = ((!this.force_rotate) && ((this.Ey != 0.0D) || (this.Ex != 0.0D)));
    this.force_circ = (this.force_translate = false);
  }
  
  public void tagForceTranslate()
  {
    this.force_translate = ((!this.force_translate) && ((this.Ey != 0.0D) || (this.Ex != 0.0D)));
    this.force_circ = (this.force_rotate = false);
  }
  
  public void beamParticles()
  {
    if (this.connection == null) {
      return;
    }
    if (!PPD.elementBox.contains(this.connection))
    {
      this.connection = null;
      return;
    }
    for (int i = 0; i < PPD.particleBox.size(); i++)
    {
      Particle localParticle = (Particle)PPD.particleBox.elementAt(i);
      double d = this.potencial - this.connection.potencial;
      if ((d == 0.0D) || (Math.abs(d) <= 1.0D)) {
        return;
      }
      if ((localParticle.parent == this) && (localParticle.dipol_partner == null))
      {
        Random localRandom;
        if ((localParticle.Q > 0.0D) && (d > 0.0D))
        {
          localRandom = new Random();
          localParticle.parent = this.connection;
          localParticle.x = (localParticle.oldx = this.connection.centerx + localRandom.nextDouble() * 50.0D);
          localParticle.y = (localParticle.oldy = this.connection.centery + localRandom.nextDouble() * 50.0D);
          this.connection.particleHold(localParticle);
          this.connectionQ += localParticle.Q;
          this.posCount -= 1.0D;
          this.connection.posCount += 1.0D;
          return;
        }
        if ((localParticle.Q < 0.0D) && (d < 0.0D))
        {
          localRandom = new Random();
          localParticle.parent = this.connection;
          localParticle.x = (localParticle.oldx = this.connection.centerx + localRandom.nextDouble() * 50.0D);
          localParticle.y = (localParticle.oldy = this.connection.centery + localRandom.nextDouble() * 50.0D);
          this.connection.particleHold(localParticle);
          this.connectionQ += localParticle.Q;
          this.negCount -= 1.0D;
          this.connection.negCount += 1.0D;
          return;
        }
        return;
      }
    }
  }
  
  public void drawFg(Graphics paramGraphics)
  {
    if (this.drawMode == 0)
    {
      if (!this.etransient) {
        paramGraphics.setColor(PPD.ELEMENT_TRANSIENT);
      } else {
        paramGraphics.setColor(PPD.ELEMENT_FOREGROUND);
      }
    }
    else if (this.drawMode == 1) {
      paramGraphics.setColor(PPD.ELEMENT_HIGHLIGHT);
    }
    paramGraphics.drawOval(this.centerx - PPD.PARTICLE_RAD, this.centery - PPD.PARTICLE_RAD, PPD.PARTICLE_RAD * 2, PPD.PARTICLE_RAD * 2);
    drawObjFg(paramGraphics);
    if (this.connection != null)
    {
      paramGraphics.setColor(Color.black);
      paramGraphics.drawLine(this.centerx, this.centery, this.connection.centerx, this.connection.centery);
      paramGraphics.setColor(Color.red);
      paramGraphics.drawString(" I = " + this.connectionI, this.centerx - (this.centerx - this.connection.centerx) / 2, this.centery - (this.centery - this.connection.centery) / 2);
    }
    if (this.arrow.d != 0.0D)
    {
      if (this.force_circ) {
        paramGraphics.setColor(Color.blue);
      } else if (this.force_rotate) {
        paramGraphics.setColor(Color.black);
      } else if (this.force_translate) {
        paramGraphics.setColor(Color.green);
      } else {
        paramGraphics.setColor(PPD.ELEMENT_ARROW);
      }
      //this.arrow.draw(paramGraphics);
    }
    if ((this.posCount != 0.0D) || (this.negCount != 0.0D))
    {
      paramGraphics.setColor(Color.black);
      paramGraphics.drawString("(" + (this.posCount - this.negCount) + ")", this.centerx, this.centery);
    }
  }
  
  public void drawBg(Graphics paramGraphics)
  {
    if (this.movable) {
      paramGraphics.setColor(PPD.ELEMENT_MOVABLE);
    } else {
      paramGraphics.setColor(PPD.ELEMENT_BACKGROUND);
    }
    drawObjBg(paramGraphics);
  }
  
  public void drawShadow(Graphics paramGraphics)
  {
    if (this.drawMode == 0)
    {
      paramGraphics.setColor(Color.gray);
      drawObjShadow(paramGraphics);
    }
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Element.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */