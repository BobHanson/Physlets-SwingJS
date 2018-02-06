package reflection;

import edu.davidson.display.Format;
import edu.davidson.tools.SUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class QMThing extends ReflectionThing
{
  double indexRight = 1.0D;

  public QMThing(double paramDouble1, double paramDouble2, WavePanel paramWavePanel)
  {
    super(paramDouble2, paramWavePanel);
    this.varStrings = new String[] { "pos", "width", "n", "pot", "im", "re", "energy", "transmission", "reflection" };
    this.potential = paramDouble1;
    this.energy = this.owner.qmEnergy;
    if (this.potential == this.energy)
      this.potential = (this.energy * 0.9999D);
    this.indexN = Math.sqrt(Math.abs(this.potential - this.energy));
    this.wavenumber = this.indexN;
    this.phaseShift = (this.width * this.wavenumber);
  }

  public QMThing()
  {
  }

  void calcBoundaryMatrix(ReflectionThing paramReflectionThing)
  {
    this.wavenumber = this.indexN;
    this.phaseShift = (this.width * this.wavenumber);
    this.absorption = (this.width * this.wavenumber);
    if (paramReflectionThing != null)
    {
      this.indexRight = paramReflectionThing.wavenumber;
      if ((this.potential < this.energy) && (paramReflectionThing.potential <= this.energy))
        calcBoundaryMatrix1(paramReflectionThing);
      else if ((this.potential > this.energy) && (paramReflectionThing.potential >= this.energy))
        calcBoundaryMatrix2(paramReflectionThing);
      else if ((this.potential < this.energy) && (paramReflectionThing.potential >= this.energy))
        calcBoundaryMatrix3(paramReflectionThing);
      else if ((this.potential > this.energy) && (paramReflectionThing.potential <= this.energy))
        calcBoundaryMatrix4(paramReflectionThing);
    }
    else
    {
      this.indexRight = this.wavenumber;
      if (this.potential > this.energy)
      {
        this.phaseShift = 0.0D;
        this.absorption = (this.wavenumber * this.width);
      }
      else
      {
        this.phaseShift = (this.wavenumber * this.width);
        this.absorption = 0.0D;
      }
    }
    double d1 = Math.cos(this.phaseShift);
    double d2 = Math.sin(this.phaseShift);
    if (paramReflectionThing == null)
    {
      this.rightE[0] = (this.magRight * d1 * Math.exp(this.absorption));
      this.rightE[1] = (-this.magRight * d2 * Math.exp(this.absorption));
      this.leftE[0] = 0.0D;
      this.leftE[1] = 0.0D;
      return;
    }
    double d3 = this.boundaryMatrix[0][0] * paramReflectionThing.rightE[0] + this.boundaryMatrix[0][1] * paramReflectionThing.leftE[0] - this.imMatrix[0][0] * paramReflectionThing.rightE[1] - this.imMatrix[0][1] * paramReflectionThing.leftE[1];
    double d4 = this.boundaryMatrix[0][0] * paramReflectionThing.rightE[1] + this.boundaryMatrix[0][1] * paramReflectionThing.leftE[1] + this.imMatrix[0][0] * paramReflectionThing.rightE[0] + this.imMatrix[0][1] * paramReflectionThing.leftE[0];
    double d5 = this.boundaryMatrix[1][0] * paramReflectionThing.rightE[0] + this.boundaryMatrix[1][1] * paramReflectionThing.leftE[0] - this.imMatrix[1][0] * paramReflectionThing.rightE[1] - this.imMatrix[1][1] * paramReflectionThing.leftE[1];
    double d6 = this.boundaryMatrix[1][0] * paramReflectionThing.rightE[1] + this.boundaryMatrix[1][1] * paramReflectionThing.leftE[1] + this.imMatrix[1][0] * paramReflectionThing.rightE[0] + this.imMatrix[1][1] * paramReflectionThing.leftE[0];
    this.rightE[0] = (d3 * d1 * Math.exp(this.absorption) + d4 * d2 * Math.exp(this.absorption));
    this.rightE[1] = (d4 * d1 * Math.exp(this.absorption) - d3 * d2 * Math.exp(this.absorption));
    this.leftE[0] = (d5 * d1 * Math.exp(-this.absorption) - d6 * d2 * Math.exp(-this.absorption));
    this.leftE[1] = (d6 * d1 * Math.exp(-this.absorption) + d5 * d2 * Math.exp(-this.absorption));
  }

  private void calcBoundaryMatrix1(ReflectionThing paramReflectionThing)
  {
    this.phaseShift = (this.width * this.wavenumber);
    this.absorption = 0.0D;
    this.boundaryMatrix[0][0] = ((this.wavenumber + this.indexRight) / (2.0D * this.wavenumber));
    this.boundaryMatrix[0][1] = ((this.wavenumber - this.indexRight) / (2.0D * this.wavenumber));
    this.boundaryMatrix[1][0] = ((this.wavenumber - this.indexRight) / (2.0D * this.wavenumber));
    this.boundaryMatrix[1][1] = ((this.wavenumber + this.indexRight) / (2.0D * this.wavenumber));
    this.imMatrix[0][0] = 0.0D;
    this.imMatrix[0][1] = 0.0D;
    this.imMatrix[1][0] = 0.0D;
    this.imMatrix[1][1] = 0.0D;
  }

  private void calcBoundaryMatrix2(ReflectionThing paramReflectionThing)
  {
    this.phaseShift = 0.0D;
    this.absorption = (this.width * this.wavenumber);
    this.boundaryMatrix[0][0] = ((this.wavenumber + this.indexRight) / (2.0D * this.wavenumber));
    this.boundaryMatrix[0][1] = ((this.wavenumber - this.indexRight) / (2.0D * this.wavenumber));
    this.boundaryMatrix[1][0] = ((this.wavenumber - this.indexRight) / (2.0D * this.wavenumber));
    this.boundaryMatrix[1][1] = ((this.wavenumber + this.indexRight) / (2.0D * this.wavenumber));
    this.imMatrix[0][0] = 0.0D;
    this.imMatrix[0][1] = 0.0D;
    this.imMatrix[1][0] = 0.0D;
    this.imMatrix[1][1] = 0.0D;
  }

  private void calcBoundaryMatrix3(ReflectionThing paramReflectionThing)
  {
    this.absorption = 0.0D;
    this.phaseShift = (this.width * this.wavenumber);
    this.boundaryMatrix[0][0] = 0.5D;
    this.boundaryMatrix[0][1] = 0.5D;
    this.boundaryMatrix[1][0] = 0.5D;
    this.boundaryMatrix[1][1] = 0.5D;
    this.imMatrix[0][0] = (0.5D * this.indexRight / this.wavenumber);
    this.imMatrix[0][1] = (-0.5D * this.indexRight / this.wavenumber);
    this.imMatrix[1][0] = (-0.5D * this.indexRight / this.wavenumber);
    this.imMatrix[1][1] = (0.5D * this.indexRight / this.wavenumber);
  }

  private void calcBoundaryMatrix4(ReflectionThing paramReflectionThing)
  {
    this.phaseShift = 0.0D;
    this.absorption = (this.width * this.wavenumber);
    this.boundaryMatrix[0][0] = 0.5D;
    this.boundaryMatrix[0][1] = 0.5D;
    this.boundaryMatrix[1][0] = 0.5D;
    this.boundaryMatrix[1][1] = 0.5D;
    this.imMatrix[0][0] = (-0.5D * this.indexRight / this.wavenumber);
    this.imMatrix[0][1] = (0.5D * this.indexRight / this.wavenumber);
    this.imMatrix[1][0] = (0.5D * this.indexRight / this.wavenumber);
    this.imMatrix[1][1] = (-0.5D * this.indexRight / this.wavenumber);
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
      d6 = Math.atan2(this.rightE[1], this.rightE[0]);
      for (i = 0; i < this.pwidth; i++)
      {
        d2 = i / d1;
        if (this.energy > this.potential)
        {
          this.rightW[i] = (this.magRight * Math.cos(this.wavenumber * d2 + d6 - this.owner.timePhase));
          this.leftW[i] = (this.magRight * Math.sin(this.wavenumber * d2 + d6 - this.owner.timePhase));
        }
        else
        {
          this.rightW[i] = (this.magRight * Math.exp(-this.wavenumber * d2) * Math.cos(d6 - this.owner.timePhase));
          this.leftW[i] = (this.magRight * Math.exp(-this.wavenumber * d2) * Math.sin(d6 - this.owner.timePhase));
        }
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
    for (int i = 0; i < this.pwidth; i++)
    {
      d2 = i / d1;
      if (this.energy > this.potential)
      {
        this.rightW[i] = (this.magRight * Math.cos(this.wavenumber * d2 + d6 - this.owner.timePhase) + this.magLeft * Math.cos(-this.wavenumber * d2 + d7 - this.owner.timePhase));
        this.leftW[i] = (this.magRight * Math.sin(this.wavenumber * d2 + d6 - this.owner.timePhase) + this.magLeft * Math.sin(-this.wavenumber * d2 + d7 - this.owner.timePhase));
      }
      else
      {
        this.rightW[i] = (this.magRight * Math.exp(-this.wavenumber * d2) * Math.cos(d6 - this.owner.timePhase) + this.magLeft * Math.exp(this.wavenumber * d2) * Math.cos(d7 - this.owner.timePhase));
        this.leftW[i] = (this.magRight * Math.exp(-this.wavenumber * d2) * Math.sin(d6 - this.owner.timePhase) + this.magLeft * Math.exp(this.wavenumber * d2) * Math.sin(d7 - this.owner.timePhase));
      }
      if (paramInt + i - this.pwidth + 1 < this.owner.rightWave.length)
      {
        this.owner.rightWave[(paramInt + i - this.pwidth)] = this.rightW[i];
        this.owner.leftWave[(paramInt + i - this.pwidth)] = this.leftW[i];
      }
    }
    return paramInt - this.pwidth;
  }

  void fillToEnd(Graphics paramGraphics)
  {
    double d1 = this.owner.ampScale;
    double d2 = this.owner.ppu;
    double d3 = 0.0D;
    double d4 = Math.cos(this.phaseShift);
    double d5 = Math.sin(this.phaseShift);
    this.magRight = (d1 * Math.sqrt(this.rightE[0] * this.rightE[0] + this.rightE[1] * this.rightE[1]));
    double d6 = Math.atan2(this.rightE[1], this.rightE[0]);
    for (int i = this.right; i < this.owner.rightWave.length; i++)
    {
      d3 = (i - this.right) / d2;
      if (this.potential > this.energy)
      {
        this.owner.rightWave[i] = (this.magRight * Math.exp(-this.wavenumber * d3 - this.absorption) * Math.cos(d6 - this.owner.timePhase));
        this.owner.leftWave[i] = (this.magRight * Math.exp(-this.wavenumber * d3 - this.absorption) * Math.sin(d6 - this.owner.timePhase));
      }
      else
      {
        this.owner.rightWave[i] = (this.magRight * Math.cos(this.wavenumber * d3 + d6 + this.phaseShift - this.owner.timePhase));
        this.owner.leftWave[i] = (this.magRight * Math.sin(this.wavenumber * d3 + d6 + this.phaseShift - this.owner.timePhase));
      }
      int j;
      int k;
      if ((!this.owner.showPhaseColor) && (this.owner.showRWave))
      {
        j = (int)(this.owner.rightWave[i] * d2) + this.owner.currenth / 2 - this.vertOffset;
        k = (int)(this.owner.rightWave[(i - 1)] * d2) + this.owner.currenth / 2 - this.vertOffset;
        paramGraphics.setColor(this.owner.imWaveColor);
        paramGraphics.drawLine(i, j, i - 1, k);
      }
      if ((!this.owner.showPhaseColor) && (this.owner.showLWave))
      {
        j = (int)(this.owner.leftWave[i] * d2) + this.owner.currenth / 2;
        k = (int)(this.owner.leftWave[(i - 1)] * d2) + this.owner.currenth / 2;
        paramGraphics.setColor(this.owner.reWaveColor);
        paramGraphics.drawLine(i, j, i - 1, k);
      }
    }
  }

  public double[][] getVariables()
  {
    this.vars[0][0] = this.pos;
    this.vars[0][1] = this.width;
    this.vars[0][2] = this.indexN;
    this.vars[0][3] = this.potential;
    this.vars[0][4] = this.magLeft;
    this.vars[0][5] = this.magRight;
    this.vars[0][6] = this.owner.qmEnergy;
    this.vars[0][8] = (this.owner.refC * this.owner.refC);
    this.vars[0][7] = (1.0D - this.vars[0][8]);
    return this.vars;
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
    if ((!this.owner.showPhaseColor) && (this.owner.showRWave))
      paintRightWave(paramGraphics);
    if ((!this.owner.showPhaseColor) && (this.owner.showLWave))
      paintLeftWave(paramGraphics);
    if (this.showCaption)
      paintCaption(paramGraphics);
  }

  void paintValue(Graphics paramGraphics)
  {
    if (!this.showValue)
      return;
    String str = "V = " + this.format.form(SUtil.chop(this.potential, 1.0E-012D));
    Font localFont = paramGraphics.getFont();
    if (this.font != null)
      paramGraphics.setFont(this.font);
    FontMetrics localFontMetrics = paramGraphics.getFontMetrics(paramGraphics.getFont());
    int i = localFontMetrics.stringWidth(str);
    paramGraphics.setColor(Color.black);
    paramGraphics.drawString(str, this.left + (int)(0.5D * (this.pwidth - i)), (int)(0.9D * this.owner.currenth));
    paramGraphics.setFont(localFont);
  }
}

/* Location:           C:\Users\wc\PhysletWorkspace\html\ReflectionSource\bin\
 * Qualified Name:     reflection.QMThing
 * JD-Core Version:    0.6.2
 */