package reflection;

import edu.davidson.display.Format;
import edu.davidson.tools.SUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.PrintStream;

public class EMThing extends ReflectionThing
{
  public EMThing(double paramDouble1, double paramDouble2, WavePanel paramWavePanel)
  {
    super(paramDouble2, paramWavePanel);
    this.indexN = paramDouble1;
    this.wavenumber = (this.indexN * 2.0D * 3.141592653589793D / this.owner.lambda);
    this.phaseShift = (this.width * this.wavenumber);
  }

  public EMThing()
  {
  }

  void calcBoundaryMatrix(ReflectionThing paramReflectionThing)
  {
    double d1 = 1.0D;
    if (paramReflectionThing != null)
      d1 = paramReflectionThing.indexN;
    this.boundaryMatrix[0][0] = ((this.indexN + d1) / (2.0D * this.indexN));
    this.boundaryMatrix[0][1] = ((this.indexN - d1) / (2.0D * this.indexN));
    this.boundaryMatrix[1][0] = ((this.indexN - d1) / (2.0D * this.indexN));
    this.boundaryMatrix[1][1] = ((this.indexN + d1) / (2.0D * this.indexN));
    this.wavenumber = (this.indexN * 2.0D * 3.141592653589793D / this.owner.lambda);
    this.phaseShift = (this.width * this.wavenumber);
    double d2 = Math.cos(this.phaseShift);
    double d3 = Math.sin(this.phaseShift);
    if (paramReflectionThing == null)
    {
      this.rightE[0] = (this.magRight * d2);
      this.rightE[1] = (-this.magRight * d3);
      this.leftE[0] = 0.0D;
      this.leftE[1] = 0.0D;
      return;
    }
    double d4 = this.boundaryMatrix[0][0] * paramReflectionThing.rightE[0] + this.boundaryMatrix[0][1] * paramReflectionThing.leftE[0];
    double d5 = this.boundaryMatrix[0][0] * paramReflectionThing.rightE[1] + this.boundaryMatrix[0][1] * paramReflectionThing.leftE[1];
    double d6 = this.boundaryMatrix[1][0] * paramReflectionThing.rightE[0] + this.boundaryMatrix[1][1] * paramReflectionThing.leftE[0];
    double d7 = this.boundaryMatrix[1][0] * paramReflectionThing.rightE[1] + this.boundaryMatrix[1][1] * paramReflectionThing.leftE[1];
    this.rightE[0] = (d4 * d2 + d5 * d3);
    this.rightE[1] = (d5 * d2 - d4 * d3);
    this.leftE[0] = (d6 * d2 - d7 * d3);
    this.leftE[1] = (d7 * d2 + d6 * d3);
  }

  int calcField(ReflectionThing paramReflectionThing, int paramInt)
  {
    double d1 = this.owner.ppu;
    double d2 = 0.0D;
    double d3 = Math.cos(this.phaseShift);
    double d4 = Math.sin(this.phaseShift);
    double d5 = this.owner.ampScale;
    this.magRight = d5;
    this.magLeft = 0.0D;
    if (paramReflectionThing == null)
    {
      this.magRight = (d5 * Math.sqrt(this.rightE[0] * this.rightE[0] + this.rightE[1] * this.rightE[1]));
      for (int i = 0; i < this.pwidth; i++)
      {
        d2 = i / d1;
        this.rightW[i] = (this.magRight * Math.cos(this.wavenumber * d2 - this.owner.timePhase - this.phaseShift));
        this.leftW[i] = 0.0D;
        if (paramInt + i - this.pwidth + 1 < this.owner.rightWave.length)
        {
          this.owner.rightWave[(paramInt + i - this.pwidth)] = this.rightW[i];
          this.owner.leftWave[(paramInt + i - this.pwidth)] = this.leftW[i];
        }
      }
      return paramInt - this.pwidth;
    }
    this.magRight = (d5 * Math.sqrt(this.rightE[0] * this.rightE[0] + this.rightE[1] * this.rightE[1]));
    double d6 = Math.atan2(this.rightE[1], this.rightE[0]);
    this.magLeft = (d5 * Math.sqrt(this.leftE[0] * this.leftE[0] + this.leftE[1] * this.leftE[1]));
    double d7 = Math.atan2(this.leftE[1], this.leftE[0]);
    for (int j = 0; j < this.pwidth; j++)
    {
      d2 = j / d1;
      this.rightW[j] = (this.magRight * Math.cos(this.wavenumber * d2 - this.owner.timePhase + d6));
      this.leftW[j] = (this.magLeft * Math.cos(-this.wavenumber * d2 - this.owner.timePhase + d7));
      if (paramInt + j - this.pwidth + 1 < this.owner.rightWave.length)
      {
        this.owner.rightWave[(paramInt + j - this.pwidth)] = this.rightW[j];
        this.owner.leftWave[(paramInt + j - this.pwidth)] = this.leftW[j];
      }
    }
    return paramInt - this.pwidth;
  }

  double calcRefCoeff(EMThing paramEMThing1, EMThing paramEMThing2)
  {
    double d1 = paramEMThing2.indexN / paramEMThing1.indexN;
    double d2 = (1.0D - d1) / (1.0D + d1);
    return d2 * d2;
  }

  double calcTransCoeff(EMThing paramEMThing1, EMThing paramEMThing2)
  {
    double d1 = calcRefCoeff(paramEMThing1, paramEMThing2);
    double d2 = Math.sqrt(1.0D - d1 * d1);
    return d2 * d2;
  }

  void fillToEnd(Graphics paramGraphics)
  {
    double d1 = this.owner.ampScale;
    double d2 = this.owner.ppu;
    double d3 = 0.0D;
    double d4 = Math.cos(this.phaseShift);
    double d5 = Math.sin(this.phaseShift);
    this.magRight = (d1 * Math.sqrt(this.rightE[0] * this.rightE[0] + this.rightE[1] * this.rightE[1]));
    for (int i = this.right; i < this.owner.rightWave.length; i++)
    {
      d3 = (i - this.right) / d2;
      this.owner.rightWave[i] = (this.magRight * Math.cos(this.wavenumber * d3 - this.owner.timePhase));
      this.owner.leftWave[i] = 0.0D;
      if ((this.owner.showRWave) && (i > 0))
      {
        int j = this.originY + (int)(this.owner.rightWave[i] * this.owner.ppu) - this.owner.rightPixOffset;
        int k = this.originY + (int)(this.owner.rightWave[(i - 1)] * this.owner.ppu) - this.owner.rightPixOffset;
        paramGraphics.setColor(Color.blue);
        paramGraphics.drawLine(i, j, i - 1, k);
      }
    }
  }

  void paintLeftWave(Graphics paramGraphics)
  {
    if (!this.owner.showLWave)
      return;
    if (this.owner.getRightNeighbor(this) == null)
      return;
    for (int i = 0; i < this.pwidth; i++)
    {
      this.xpoints[i] = (i + this.left);
      this.ypoints[i] = (this.originY + (int)(this.leftW[i] * this.owner.ppu) - this.owner.leftPixOffset);
    }
    paramGraphics.setColor(this.owner.reWaveColor);
    paramGraphics.drawPolyline(this.xpoints, this.ypoints, this.pwidth);
  }

  void paintOS(Graphics paramGraphics)
  {
    ReflectionThing localReflectionThing = this.owner.getLeftNeighbor(this);
    paramGraphics.setColor(this.color);
    if (this.visibility)
      if (localReflectionThing == null)
        paramGraphics.fillRect(this.left, 0, this.pwidth, this.owner.currenth);
      else if (this.pwidth < 1)
      {
        if (localReflectionThing.pwidth < 1)
          paramGraphics.fillRect(this.left + 1, 0, 1, this.owner.currenth);
        else
          paramGraphics.fillRect(this.left, 0, 1, this.owner.currenth);
      }
      else if (localReflectionThing.pwidth < 1)
        paramGraphics.fillRect(this.left + 1, 0, this.pwidth - 1, this.owner.currenth);
      else
        paramGraphics.fillRect(this.left, 0, this.pwidth, this.owner.currenth);
    paintRightWave(paramGraphics);
    paintLeftWave(paramGraphics);
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
      this.ypoints[i] = (this.originY + (int)(this.rightW[i] * this.owner.ppu) - this.owner.rightPixOffset);
    }
    paramGraphics.setColor(this.owner.imWaveColor);
    paramGraphics.drawPolyline(this.xpoints, this.ypoints, this.pwidth);
  }

  void paintSumWave(Graphics paramGraphics)
  {
    for (int i = 0; i < this.pwidth; i++)
    {
      this.xpoints[i] = (i + this.left);
      this.ypoints[i] = (this.originY + this.vertOffset + (int)((this.leftW[i] + this.rightW[i]) * this.owner.ppu));
    }
    paramGraphics.setColor(Color.green);
    paramGraphics.drawPolyline(this.xpoints, this.ypoints, this.pwidth);
  }

  void paintValue(Graphics paramGraphics)
  {
    if (!this.showValue)
      return;
    String str = "n = " + this.format.form(SUtil.chop(this.indexN, 1.0E-012D));
    Font localFont = paramGraphics.getFont();
    if (this.font != null)
      paramGraphics.setFont(this.font);
    FontMetrics localFontMetrics = paramGraphics.getFontMetrics(paramGraphics.getFont());
    int i = localFontMetrics.stringWidth(str);
    paramGraphics.setColor(Color.black);
    paramGraphics.drawString(str, this.left + (int)(0.5D * (this.pwidth - i)), (int)(0.9D * this.owner.currenth));
    paramGraphics.setFont(localFont);
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

  void setIndexN(double paramDouble)
  {
    if (paramDouble < 1.0D)
      System.out.println("Cannot have index n<1 ");
    else
      this.indexN = paramDouble;
  }
}

/* Location:           C:\Users\wc\PhysletWorkspace\html\ReflectionSource\bin\
 * Qualified Name:     reflection.EMThing
 * JD-Core Version:    0.6.2
 */