package reflection;

import edu.davidson.display.Format;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SDataSource;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.PrintStream;

public class ReflectionThing
  implements SDataSource
{
  double absorption = 0.0D;
  SApplet applet = null;
  double[][] boundaryMatrix = new double[2][2];
  String caption;
  Color color = Color.white;
  boolean dragPotential = false;
  boolean dragable = false;
  double energy = 2.0D;
  Font font = null;
  Format format = new Format("%6.2g");
  double[][] imMatrix = new double[2][2];
  double indexN = 1.0D;
  int left;
  double[] leftE = new double[2];
  double[] leftW = null;
  double[] leftW2 = null;
  double magLeft = 0.0D;
  double magRight = 1.0D;
  int originX = 0;
  int originY = 0;
  WavePanel owner = null;
  double phaseLeft = 0.0D;
  double phaseLeft2 = 0.0D;
  double phaseShift = 0.0D;
  double pos;
  double potential = 1.0D;
  int pwidth;
  int right;
  double[] rightE = new double[2];
  double[] rightW = null;
  boolean showCaption = false;
  boolean showValue = true;
  String[] varStrings = { "pos", "width", "n", "p", "magLeft", "magRight", "wavelength", "transmission", "reflection" };
  double[][] vars = new double[1][9];
  int vertOffset = 0;
  boolean visibility = true;
  double wavenumber = 0.0D;
  double width;
  int[] xpoints = null;
  int[] ypoints = null;

  public ReflectionThing(double paramDouble, WavePanel paramWavePanel)
  {
    this();
    this.owner = paramWavePanel;
    this.applet = this.owner.owner;
    this.pos = 0.0D;
    ReflectionThing localReflectionThing = this.owner.getRightMost();
    if (localReflectionThing != null)
      localReflectionThing.pos += localReflectionThing.width;
    this.width = paramDouble;
    rescale(0);
  }

  public ReflectionThing()
  {
  }

  void calcBoundaryMatrix(ReflectionThing paramReflectionThing)
  {
    this.boundaryMatrix[0][0] = 0.0D;
    this.boundaryMatrix[0][1] = 0.0D;
    this.boundaryMatrix[1][0] = 0.0D;
    this.boundaryMatrix[1][1] = 0.0D;
  }

  int calcField(ReflectionThing paramReflectionThing, int paramInt)
  {
    return paramInt;
  }

  void fillToEnd(Graphics paramGraphics)
  {
  }

  public int getID()
  {
    return hashCode();
  }

  public SApplet getOwner()
  {
    return this.applet;
  }

  public String[] getVarStrings()
  {
    return this.varStrings;
  }

  public double[][] getVariables()
  {
    this.vars[0][0] = this.pos;
    this.vars[0][1] = this.width;
    this.vars[0][2] = this.indexN;
    this.vars[0][3] = this.potential;
    this.vars[0][4] = this.magLeft;
    this.vars[0][5] = this.magRight;
    this.vars[0][6] = this.owner.lambda;
    this.vars[0][7] = this.owner.tranC;
    this.vars[0][8] = this.owner.refC;
    return this.vars;
  }

  boolean isInside(int paramInt1, int paramInt2)
  {
    if (!this.dragable)
    {
      if ((paramInt1 > this.left) && (paramInt1 < this.right))
        return true;
    }
    else if ((paramInt1 > this.left + 3) && (paramInt1 < this.right - 3))
      return true;
    return false;
  }

  boolean isNearBoundary(int paramInt1, int paramInt2)
  {
    if (!this.dragable)
      return false;
    return Math.abs(this.right - paramInt1) < 4;
  }

  void paintCaption(Graphics paramGraphics)
  {
    Font localFont = paramGraphics.getFont();
    if (this.font != null)
      paramGraphics.setFont(this.font);
    FontMetrics localFontMetrics = paramGraphics.getFontMetrics(paramGraphics.getFont());
    int i = localFontMetrics.stringWidth(this.caption);
    paramGraphics.setColor(Color.black);
    paramGraphics.drawString(this.caption, this.left + (int)(0.5D * (this.pwidth - i)), (int)(0.9D * this.owner.currenth));
    paramGraphics.setFont(localFont);
  }

  void paintLeftWave(Graphics paramGraphics)
  {
    if (!this.owner.showLWave)
      return;
    for (int i = 0; i < this.pwidth; i++)
    {
      this.xpoints[i] = (i + this.left);
      this.ypoints[i] = (this.originY + (int)(this.leftW[i] * this.owner.ppu) - this.owner.leftPixOffset);
    }
    paramGraphics.setColor(this.owner.reWaveColor);
    paramGraphics.drawPolyline(this.xpoints, this.ypoints, this.xpoints.length);
  }

  void paintOS(Graphics paramGraphics)
  {
    paramGraphics.setColor(this.color);
    paramGraphics.fillRect(this.left, 0, this.pwidth, this.owner.currenth);
    if ((this instanceof EMThing))
    {
      paintRightWave(paramGraphics);
      paintLeftWave(paramGraphics);
      paintSumWave(paramGraphics);
    }
    else
    {
      paintRightWave(paramGraphics);
      paintLeftWave(paramGraphics);
    }
    if (this.showValue)
      paintValue(paramGraphics);
    if (this.showCaption)
      paintCaption(paramGraphics);
  }

  void paintRightWave(Graphics paramGraphics)
  {
    if (!this.owner.showRWave)
      return;
    for (int i = 0; i < this.pwidth; i++)
    {
      this.xpoints[i] = (i + this.left);
      this.ypoints[i] = (this.originY - this.vertOffset + (int)(this.rightW[i] * this.owner.ppu) - this.owner.rightPixOffset);
    }
    paramGraphics.setColor(this.owner.imWaveColor);
    paramGraphics.drawPolyline(this.xpoints, this.ypoints, this.xpoints.length);
  }

  void paintSumWave(Graphics paramGraphics)
  {
    for (int i = 0; i < this.pwidth; i++)
    {
      this.xpoints[i] = (i + this.left);
      if ((this instanceof EMThing))
        this.ypoints[i] = (this.originY + this.vertOffset + (int)((this.leftW[i] + this.rightW[i]) * this.owner.ppu));
      else
        this.ypoints[i] = (this.originY + this.vertOffset + (int)((this.leftW[i] * this.leftW[i] + this.rightW[i] * this.rightW[i]) * this.owner.ppu));
    }
    paramGraphics.setColor(Color.green);
    paramGraphics.drawPolyline(this.xpoints, this.ypoints, this.xpoints.length);
  }

  void paintValue(Graphics paramGraphics)
  {
  }

  int rescale(int paramInt)
  {
    this.left = paramInt;
    this.right = ((int)((this.pos + this.width) * this.owner.ppu));
    this.pwidth = (this.right - this.left);
    this.leftW = new double[this.pwidth];
    this.rightW = new double[this.pwidth];
    this.xpoints = new int[this.pwidth];
    this.ypoints = new int[this.pwidth];
    this.originY = ((int)(0.5D * this.owner.currenth));
    for (int i = 0; i < this.pwidth; i++)
    {
      this.leftW[i] = 0.0D;
      this.rightW[i] = 0.0D;
      this.xpoints[i] = 0;
      this.ypoints[i] = 0;
    }
    return this.right;
  }

  void scaleWaves(double paramDouble1, double paramDouble2)
  {
    for (int i = 0; i < this.pwidth; i++)
    {
      this.leftW[i] *= paramDouble2 / paramDouble1;
      this.rightW[i] *= paramDouble2 / paramDouble1;
    }
  }

  public void setColor(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 >= 0) && (paramInt2 >= 0) && (paramInt3 >= 0) && (paramInt1 <= 255) && (paramInt2 <= 255) && (paramInt3 <= 255))
      this.color = new Color(paramInt1, paramInt2, paramInt3);
    else
      this.color = Color.blue;
  }

  public final boolean setFormat(String paramString)
  {
    try
    {
      this.format = new Format(paramString);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      System.out.println("Illegal numeric format:" + paramString);
      return false;
    }
    return true;
  }

  void setIndexN(double paramDouble)
  {
    this.indexN = paramDouble;
  }

  public void setOwner(SApplet paramSApplet)
  {
    this.applet = paramSApplet;
  }

  void setPos(double paramDouble)
  {
    this.pos = paramDouble;
  }

  void setPotential(double paramDouble)
  {
    if ((this instanceof EMThing))
      return;
    this.potential = paramDouble;
    this.energy = this.owner.qmEnergy;
    if (this.potential == this.energy)
      this.potential = (this.energy * 0.9999D);
    this.indexN = Math.sqrt(Math.abs(this.potential - this.energy));
    this.wavenumber = this.indexN;
    this.phaseShift = (this.width * this.indexN);
  }

  public void setShowValue(boolean paramBoolean)
  {
    this.showValue = paramBoolean;
  }

  public void setVisibility(boolean paramBoolean)
  {
    this.visibility = paramBoolean;
  }

  void setWidth(double paramDouble)
  {
    this.width = paramDouble;
    if ((this instanceof QMThing))
      setPotential(this.potential);
  }
}

/* Location:           C:\Users\wc\PhysletWorkspace\html\ReflectionSource\bin\
 * Qualified Name:     reflection.ReflectionThing
 * JD-Core Version:    0.6.2
 */