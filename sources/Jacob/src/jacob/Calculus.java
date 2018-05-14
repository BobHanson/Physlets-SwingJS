package jacob;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Vector;

public final class Calculus
{
  public static final double R2_CRIT = 8.0D;
  public static final double R2_MIN_FIX = 0.5D;
  public static final double DIPOL_CLOUD = 500.0D;
  public static final double CCOUNT_PERIOD = 20.0D;
  public static double time = 0.0D;
  
  public static boolean touching(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    double d = (paramInt1 - paramInt3) * (paramInt1 - paramInt3) + (paramInt2 - paramInt4) * (paramInt2 - paramInt4);
    return d <= PPD.TOUCH_OFFSET * PPD.TOUCH_OFFSET;
  }
  
  public static Element checkElementArrow(int paramInt1, int paramInt2)
  {
    for (int i = 0; i < PPD.elementBox.size(); i++)
    {
      Element localElement = (Element)PPD.elementBox.elementAt(i);
      if (localElement.arrow.d != 0.0D)
      {
        if (touching(localElement.arrow.px[0], localElement.arrow.py[0], paramInt1, paramInt2)) {
          return localElement;
        }
      }
      else if (touching(localElement.centerx, localElement.centery, paramInt1, paramInt2)) {
        return localElement;
      }
    }
    return null;
  }
  
  public static Element checkElementBorder(int paramInt1, int paramInt2)
  {
    double d1 = Double.MAX_VALUE;
    Object localObject = null;
    for (int i = 0; i < PPD.elementBox.size(); i++)
    {
      Element localElement = (Element)PPD.elementBox.elementAt(i);
      double d2 = localElement.touching(paramInt1, paramInt2);
      if (d2 < d1)
      {
        localObject = localElement;
        d1 = d2;
      }
    }
    if (d1 < PPD.TOUCH_OFFSET) {
      return (Element)localObject;
    }
    return null;
  }
  
  public static boolean intersects(ECircle paramECircle1, ECircle paramECircle2)
  {
    return (paramECircle1.x - paramECircle2.x) * (paramECircle1.x - paramECircle2.x) + (paramECircle1.y - paramECircle2.y) * (paramECircle1.y - paramECircle2.y) < (paramECircle1.r + paramECircle2.r) * (paramECircle1.r + paramECircle2.r);
  }
  
  public static boolean intersects(ERectangle paramERectangle, ECircle paramECircle)
  {
    int i = paramERectangle.x - paramECircle.r;
    int j = paramERectangle.y - paramECircle.r;
    int k = paramERectangle.x + paramERectangle.width + paramECircle.r;
    int m = paramERectangle.y + paramERectangle.height + paramECircle.r;
    return (paramECircle.x >= i) && (paramECircle.x <= k) && (paramECircle.y >= j) && (paramECircle.y <= m);
  }
  
  public static boolean intersects(ERectangle paramERectangle1, ERectangle paramERectangle2)
  {
    return (paramERectangle1.x + paramERectangle1.width > paramERectangle2.x) && (paramERectangle1.y + paramERectangle1.height > paramERectangle2.y) && (paramERectangle1.x < paramERectangle2.x + paramERectangle2.width) && (paramERectangle1.y < paramERectangle2.y + paramERectangle2.height);
  }
  
  public static boolean intersects(ERing paramERing1, ERing paramERing2)
  {
    double d = Math.sqrt((paramERing1.x - paramERing2.x) * (paramERing1.x - paramERing2.x) + (paramERing1.y - paramERing2.y) * (paramERing1.y - paramERing2.y));
    int i = Math.min(paramERing1.r1, paramERing2.r1);
    int j = Math.max(paramERing1.r2, paramERing2.r2);
    return (d < paramERing1.r1 + paramERing2.r1) && (j < d + i);
  }
  
  public static boolean intersects(ECircle paramECircle, ERing paramERing)
  {
    double d = Math.sqrt((paramECircle.x - paramERing.x) * (paramECircle.x - paramERing.x) + (paramECircle.y - paramERing.y) * (paramECircle.y - paramERing.y));
    return (d < paramECircle.r + paramERing.r1) && (paramERing.r2 < d + paramECircle.r);
  }
  
  public static boolean intersects(ERectangle paramERectangle, ERing paramERing)
  {
    int i = paramERectangle.x - paramERing.r1;
    int j = paramERectangle.y - paramERing.r1;
    int k = paramERectangle.x + paramERectangle.width + paramERing.r1;
    int m = paramERectangle.y + paramERectangle.height + paramERing.r1;
    if ((paramERing.x >= i) && (paramERing.x <= k) && (paramERing.y >= j) && (paramERing.y <= m))
    {
      if (paramERing.r2 * paramERing.r2 < (paramERectangle.x - paramERing.x) * (paramERectangle.x - paramERing.x) + (paramERectangle.y - paramERing.y) * (paramERectangle.y - paramERing.y)) {
        return true;
      }
      if (paramERing.r2 * paramERing.r2 < (paramERectangle.x - paramERing.x) * (paramERectangle.x - paramERing.x) + (paramERectangle.y + paramERectangle.height - paramERing.y) * (paramERectangle.y + paramERectangle.height - paramERing.y)) {
        return true;
      }
      if (paramERing.r2 * paramERing.r2 < (paramERectangle.x + paramERectangle.width - paramERing.x) * (paramERectangle.x + paramERectangle.width - paramERing.x) + (paramERectangle.y - paramERing.y) * (paramERectangle.y - paramERing.y)) {
        return true;
      }
      if (paramERing.r2 * paramERing.r2 < (paramERectangle.x + paramERectangle.width - paramERing.x) * (paramERectangle.x + paramERectangle.width - paramERing.x) + (paramERectangle.y + paramERectangle.height - paramERing.y) * (paramERectangle.y + paramERectangle.height - paramERing.y)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean reparentParticle(Vector paramVector, Particle paramParticle)
  {
    for (int i = 0; i < paramVector.size(); i++)
    {
      Element localElement = (Element)paramVector.elementAt(i);
      if ((localElement.particleInside(paramParticle)) && (localElement.etransient) && (paramParticle.parent.etransient))
      {
        paramParticle.parent = localElement;
        return true;
      }
    }
    return false;
  }
  
  public static boolean restrainParticle(Particle paramParticle)
  {
    for (int i = 0; i < PPD.elementBox.size(); i++)
    {
      Element localElement = (Element)PPD.elementBox.elementAt(i);
      if ((paramParticle.parent != localElement) && (!localElement.etransient) && (localElement.particleInside(paramParticle)))
      {
        localElement.particleReject(paramParticle);
        return true;
      }
    }
    return false;
  }
  
  public static void halfPath(Particle paramParticle)
  {
    double d1 = (paramParticle.x + paramParticle.oldx) / 2.0D;
    double d2 = (paramParticle.y + paramParticle.oldy) / 2.0D;
    paramParticle.oldx = paramParticle.x;
    paramParticle.oldy = paramParticle.y;
    paramParticle.x = d1;
    paramParticle.y = d2;
  }
  
  public static void registerNeightbours()
  {
	int i;
    for (i = 0; i < PPD.elementBox.size(); i++)
    {
      Element localElement3 = (Element)PPD.elementBox.elementAt(i);
      localElement3.neightbours.removeAllElements();
    }
    for (i = 0; i < PPD.elementBox.size() - 1; i++)
    {
      Element localElement1 = (Element)PPD.elementBox.elementAt(i);
      for (int j = i + 1; j < PPD.elementBox.size(); j++)
      {
        Element localElement2 = (Element)PPD.elementBox.elementAt(j);
        if (localElement1.intersects(localElement2))
        {
          localElement1.neightbours.addElement(localElement2);
          localElement2.neightbours.addElement(localElement1);
          localElement1.movable = (localElement2.movable = false);
        }
      }
    }
  }
  
  public static void moveParticles(Element paramElement, int paramInt1, int paramInt2)
  {
    for (int i = 0; i < PPD.particleBox.size(); i++)
    {
      Particle localParticle = (Particle)PPD.particleBox.elementAt(i);
      if (localParticle.parent == paramElement)
      {
        localParticle.oldx = localParticle.x;
        localParticle.oldy = localParticle.y;
        localParticle.x += paramInt1;
        localParticle.y += paramInt2;
        localParticle.vx = (localParticle.oldx - localParticle.x);
        localParticle.vy = (localParticle.oldy - localParticle.y);
      }
    }
  }
  
  public static void insertParticlesPos(int paramInt1, int paramInt2, Element paramElement, int paramInt3)
  {
    Random localRandom = new Random();
    for (int i = 0; i < paramInt3; i++)
    {
      ParticlePos localParticlePos = new ParticlePos(paramInt1 + localRandom.nextDouble() * 20.0D, paramInt2 + localRandom.nextDouble() * 20.0D, paramElement);
      paramElement.particleHold(localParticlePos);
      PPD.particleBox.addElement(localParticlePos);
    }
    paramElement.posCount += paramInt3;
  }
  
  public static void insertParticlesNeg(int paramInt1, int paramInt2, Element paramElement, int paramInt3)
  {
    Random localRandom = new Random();
    for (int i = 0; i < paramInt3; i++)
    {
      ParticleNeg localParticleNeg = new ParticleNeg(paramInt1 + localRandom.nextDouble() * 20.0D, paramInt2 + localRandom.nextDouble() * 20.0D, paramElement);
      paramElement.particleHold(localParticleNeg);
      PPD.particleBox.addElement(localParticleNeg);
    }
    paramElement.negCount += paramInt3;
  }
  
  public static void insertDipols(int paramInt1, int paramInt2, Element paramElement, int paramInt3)
  {
    Random localRandom = new Random();
    for (int i = 0; i < paramInt3; i++)
    {
      double d1 = paramInt1 + localRandom.nextDouble() * 20.0D;
      double d2 = paramInt2 + localRandom.nextDouble() * 20.0D;
      Object localObject = new ParticleNeg(d1, d2, paramElement);
      paramElement.particleHold((Particle)localObject);
      PPD.particleBox.addElement(localObject);
      localObject = new ParticlePos(d1 + 0.5D, d2 + 0.5D, paramElement);
      paramElement.particleHold((Particle)localObject);
      PPD.particleBox.addElement(localObject);
    }
  }
  
  public static void removeParticles(Element paramElement)
  {
    for (int i = 0; i < PPD.particleBox.size(); i++)
    {
      Particle localParticle = (Particle)PPD.particleBox.elementAt(i);
      if (localParticle.parent == paramElement)
      {
        PPD.particleBox.removeElementAt(i);
        i--;
      }
    }
  }
  
  public static void correctParticles()
  {
    for (int i = 0; i < PPD.particleBox.size(); i++)
    {
      Particle localParticle = (Particle)PPD.particleBox.elementAt(i);
      localParticle.oldx = localParticle.x;
      localParticle.oldy = localParticle.y;
      localParticle.parent.particleHold(localParticle);
      if ((localParticle.oldx != localParticle.x) && (localParticle.oldy != localParticle.y))
      {
        localParticle.vx = (localParticle.oldx - localParticle.x);
        localParticle.vy = (localParticle.oldy - localParticle.y);
      }
    }
  }
  
  public static double magicFormula()
  {
    Particle[] arrayOfParticle = PPD.particleArray;
    int k = 10;
    double d6 = 0.0D;
    time += 1.0D;
    if (time > Double.MAX_VALUE) {
      time = 0.0D;
    }
    int i;
    for ( i = 0; i < arrayOfParticle.length; i++)
    {
      arrayOfParticle[i].Mx = 0.0D;
      arrayOfParticle[i].My = 0.0D;
      arrayOfParticle[i].Ex = 0.0D;
      arrayOfParticle[i].Ey = 0.0D;
    }
    Element localElement1;
    for (i = 0; i < PPD.elementBox.size(); i++)
    {
      localElement1 = (Element)PPD.elementBox.elementAt(i);
      localElement1.beamParticles();
    }
    double d7;
    double d8;
    for (i = 0; i < PPD.elementBox.size(); i++)
    {
      localElement1 = (Element)PPD.elementBox.elementAt(i);
      localElement1.negCount = 0.0D;
      localElement1.posCount = 0.0D;
      localElement1.potencial = 0.0D;
      if (time / 20.0D == (int)(time / 20.0D))
      {
        localElement1.connectionI = (localElement1.connectionQ / 20.0D);
        localElement1.connectionQ = 0.0D;
        if (localElement1.currPlotter != null) {
          localElement1.currPlotter.addData(localElement1.connectionI);
        }
      }
      if (localElement1.movable)
      {
        d7 = PPD.electric_const * localElement1.EEx;
        d8 = PPD.electric_const * localElement1.EEy;
        if ((Math.abs(d7) < 1.0D) && (Math.abs(d7) > 0.1D)) {
          if (d7 > 0.0D) {
            d7 = 1.0D;
          } else {
            d7 = -1.0D;
          }
        }
        if ((Math.abs(d8) < 1.0D) && (Math.abs(d8) > 0.1D)) {
          if (d8 > 0.0D) {
            d8 = 1.0D;
          } else {
            d8 = -1.0D;
          }
        }
        localElement1.move((int)d7, (int)d8);
        moveParticles(localElement1, (int)d7, (int)d8);
        localElement1.EEx = 0.0D;
        localElement1.EEy = 0.0D;
      }
      if (localElement1.energyPlotter != null)
      {
        localElement1.energyPlotter.addData(localElement1.energyPlotterE);
        localElement1.energyPlotterE = 0.0D;
      }
    }
    registerNeightbours();
    for (i = 0; i < arrayOfParticle.length; i++)
    {
      arrayOfParticle[i].parent.energyPlotterP = 0.0D;
      double d9;
      double d10;
      for (int j = i + 1; j < arrayOfParticle.length; j++)
      {
        d7 = arrayOfParticle[i].x - arrayOfParticle[j].x;
        d8 = arrayOfParticle[i].y - arrayOfParticle[j].y;
        double d5 = (arrayOfParticle[i].x - arrayOfParticle[j].x) * (arrayOfParticle[i].x - arrayOfParticle[j].x) + (arrayOfParticle[i].y - arrayOfParticle[j].y) * (arrayOfParticle[i].y - arrayOfParticle[j].y);
        if (d5 < 500.0D)
        {
          if ((arrayOfParticle[i].Q == -arrayOfParticle[j].Q) && (arrayOfParticle[i].dipol_partner == null) && (arrayOfParticle[j].dipol_partner == null) && (arrayOfParticle[i].parent == arrayOfParticle[j].parent))
          {
            arrayOfParticle[i].Q /= 10.0D;
            arrayOfParticle[j].Q /= 10.0D;
            arrayOfParticle[i].dipol_partner = arrayOfParticle[j];
            arrayOfParticle[j].dipol_partner = arrayOfParticle[i];
          }
          else if ((arrayOfParticle[i].x == arrayOfParticle[j].x) && (arrayOfParticle[j].y == arrayOfParticle[j].y))
          {
            Random localRandom = new Random();
            arrayOfParticle[i].x += localRandom.nextDouble() / 2.0D;
            arrayOfParticle[j].y += localRandom.nextDouble() / 2.0D;
          }
          if (d5 < 0.5D) {
            d5 = 0.5D;
          }
        }
        else if (arrayOfParticle[i].dipol_partner == arrayOfParticle[j])
        {
          arrayOfParticle[i].dipol_partner = null;
          arrayOfParticle[j].dipol_partner = null;
          arrayOfParticle[i].Q *= 10.0D;
          arrayOfParticle[j].Q *= 10.0D;
        }
        double d3;
        double d4;
        if (arrayOfParticle[i].dipol_partner == arrayOfParticle[j])
        {
          d3 = arrayOfParticle[i].Q * PPD.suscept * (d5 / 50.0D - 0.16D);
          d4 = arrayOfParticle[j].Q * PPD.suscept * (d5 / 50.0D - 0.16D);
        }
        else
        {
          d3 = arrayOfParticle[i].Q;
          d4 = arrayOfParticle[j].Q;
        }
        if (PPD.magnetism)
        {
          d9 = Math.log(d5 * d5);
          arrayOfParticle[i].Mx += d4 * (-arrayOfParticle[i].vy * arrayOfParticle[j].vy * d7 + arrayOfParticle[j].vx * arrayOfParticle[i].vy * d8) / d9;
          arrayOfParticle[i].My += d4 * (arrayOfParticle[i].vx * arrayOfParticle[j].vy * d7 - arrayOfParticle[i].vx * arrayOfParticle[j].vx * d8) / d9;
          arrayOfParticle[j].Mx += d3 * (arrayOfParticle[i].vy * arrayOfParticle[j].vy * d7 - arrayOfParticle[i].vx * arrayOfParticle[j].vy * d8) / d9;
          arrayOfParticle[j].My += d3 * (-arrayOfParticle[j].vx * arrayOfParticle[i].vy * d7 + arrayOfParticle[i].vx * arrayOfParticle[j].vx * d8) / d9;
        }
        double d1 = d7 / d5;
        double d2 = d8 / d5;
        arrayOfParticle[i].Ex += d4 * d1;
        arrayOfParticle[i].Ey += d4 * d2;
        arrayOfParticle[j].Ex -= d3 * d1;
        arrayOfParticle[j].Ey -= d3 * d2;
        if ((arrayOfParticle[i].parent == arrayOfParticle[j].parent) && (arrayOfParticle[i].parent.energyPlotter != null))
        {
          d9 = Math.sqrt((arrayOfParticle[j].x - PPD.PPDZero.x) * (arrayOfParticle[j].x - PPD.PPDZero.x) + (arrayOfParticle[j].y - PPD.PPDZero.y) * (arrayOfParticle[j].y - PPD.PPDZero.y));
          d10 = Math.sqrt((arrayOfParticle[j].x - arrayOfParticle[i].x) * (arrayOfParticle[j].x - arrayOfParticle[i].x) + (arrayOfParticle[j].y - arrayOfParticle[i].y) * (arrayOfParticle[j].y - arrayOfParticle[i].y));
          arrayOfParticle[i].parent.energyPlotterP += arrayOfParticle[j].Q * Math.log(d9 / d10);
        }
      }
      if (arrayOfParticle[i].parent.energyPlotter != null) {
        arrayOfParticle[i].parent.energyPlotterE += arrayOfParticle[i].Q * arrayOfParticle[i].parent.energyPlotterP;
      }
      d7 = time / arrayOfParticle[i].parent.period;
      d8 = Math.sqrt(arrayOfParticle[i].parent.Ex * arrayOfParticle[i].parent.Ex + arrayOfParticle[i].parent.Ey * arrayOfParticle[i].parent.Ey);
      arrayOfParticle[i].oldx = arrayOfParticle[i].x;
      arrayOfParticle[i].oldy = arrayOfParticle[i].y;
      if (arrayOfParticle[i].parent.force_circ)
      {
        d9 = arrayOfParticle[i].x - arrayOfParticle[i].parent.centerx;
        d10 = arrayOfParticle[i].y - arrayOfParticle[i].parent.centery;
        double d11 = Math.sqrt(d9 * d9 + d10 * d10);
        arrayOfParticle[i].Ex = (arrayOfParticle[i].Q * (arrayOfParticle[i].Ex + d8 * (-d10 / d11)));
        arrayOfParticle[i].Ey = (arrayOfParticle[i].Q * (arrayOfParticle[i].Ey + d8 * (d9 / d11)));
      }
      else if (arrayOfParticle[i].parent.force_rotate)
      {
        d9 = d8 * Math.cos(d7);
        d10 = d8 * Math.sin(d7);
        arrayOfParticle[i].Ex = (arrayOfParticle[i].Q * (arrayOfParticle[i].Ex + d9));
        arrayOfParticle[i].Ey = (arrayOfParticle[i].Q * (arrayOfParticle[i].Ey + d10));
      }
      else if (arrayOfParticle[i].parent.force_translate)
      {
        d9 = d8 * Math.cos(d7);
        arrayOfParticle[i].Ex = (arrayOfParticle[i].Q * (arrayOfParticle[i].Ex + d9));
        arrayOfParticle[i].Ey = (arrayOfParticle[i].Q * arrayOfParticle[i].Ey);
      }
      else if (arrayOfParticle[i].parent.movable)
      {
        arrayOfParticle[i].Ex = (arrayOfParticle[i].Q * arrayOfParticle[i].Ex);
        arrayOfParticle[i].Ey = (arrayOfParticle[i].Q * arrayOfParticle[i].Ey);
      }
      else
      {
        d9 = arrayOfParticle[i].parent.Ex;
        d10 = arrayOfParticle[i].parent.Ey;
        for (int n = 0; n < arrayOfParticle[i].parent.neightbours.size(); n++)
        {
          Element localElement2 = (Element)arrayOfParticle[i].parent.neightbours.elementAt(n);
          if (localElement2.particleInside(arrayOfParticle[i]))
          {
            d9 += localElement2.Ex;
            d10 += localElement2.Ey;
          }
        }
        arrayOfParticle[i].Ex = (arrayOfParticle[i].Q * (arrayOfParticle[i].Ex + d9));
        arrayOfParticle[i].Ey = (arrayOfParticle[i].Q * (arrayOfParticle[i].Ey + d10));
      }
      arrayOfParticle[i].Mx *= arrayOfParticle[i].Q;
      arrayOfParticle[i].My *= arrayOfParticle[i].Q;
      arrayOfParticle[i].x += arrayOfParticle[i].k * (PPD.electric_const * arrayOfParticle[i].Ex + PPD.magnetic_const * arrayOfParticle[i].Mx + PPD.B_const * arrayOfParticle[i].vx);
      arrayOfParticle[i].y += arrayOfParticle[i].k * (PPD.electric_const * arrayOfParticle[i].Ey + PPD.magnetic_const * arrayOfParticle[i].My + PPD.B_const * arrayOfParticle[i].vy);
      arrayOfParticle[i].parent.EEx += arrayOfParticle[i].Ex;
      arrayOfParticle[i].parent.EEy += arrayOfParticle[i].Ey;
      if ((Math.abs(arrayOfParticle[i].oldx - arrayOfParticle[i].x) > PPD.PPD_WIDTH / 20.0D) || (Math.abs(arrayOfParticle[i].oldy - arrayOfParticle[i].y) > PPD.PPD_HEIGHT / 20.0D))
      {
        PPD.magnetic_const *= 0.8D;
        PPD.electric_const *= 0.8D;
        PPD.B_const *= 0.5D;
        if (PPD.ppdControls != null) {
          PPD.ppdControls.updateControls();
        }
      }
      restrainParticle(arrayOfParticle[i]);
      arrayOfParticle[i].parent.particleHold(arrayOfParticle[i]);
      arrayOfParticle[i].vx = (arrayOfParticle[i].oldx - arrayOfParticle[i].x);
      arrayOfParticle[i].vy = (arrayOfParticle[i].oldy - arrayOfParticle[i].y);
      arrayOfParticle[i].Mx *= PPD.magnetic_const / PPD.electric_const;
      arrayOfParticle[i].My *= PPD.magnetic_const / PPD.electric_const;
      if (arrayOfParticle[i].Q == 1.0D) {
        arrayOfParticle[i].parent.posCount += 1.0D;
      } else if (arrayOfParticle[i].Q == -1.0D) {
        arrayOfParticle[i].parent.negCount += 1.0D;
      }
      for (int m = 0; m < PPD.elementBox.size(); m++)
      {
        localElement1 = (Element)PPD.elementBox.elementAt(m);
        if (localElement1.connection != null) {
          localElement1.potencial += pot(localElement1.centerx, localElement1.centery, arrayOfParticle[i]);
        }
      }
    }
    return -1.0D;
  }
  
  public static void drawField(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Particle[] arrayOfParticle = PPD.particleArray;
    for (int j = paramInt1; j < paramInt3; j += 2) {
      for (int k = paramInt2; k < paramInt4; k += 2)
      {
        double d3 = 0.0D;
        double d2 = Math.sqrt((j - PPD.PPDZero.x) * (j - PPD.PPDZero.x) + (k - PPD.PPDZero.y) * (k - PPD.PPDZero.y));
        for (int i = 0; i < arrayOfParticle.length; i++)
        {
          double d1 = Math.sqrt((arrayOfParticle[i].x - j) * (arrayOfParticle[i].x - j) + (arrayOfParticle[i].y - k) * (arrayOfParticle[i].y - k));
          if (d2 > 0.0D) {
            d3 += arrayOfParticle[i].Q * Math.log(d1 / d2);
          }
        }
        d3 = d3 * 35.0D * (10.0D / PPD.particleBox.size());
        int m;
        if (d3 <= 0.0D)
        {
          d3 = -d3;
          if (d3 > 255.0D) {
            d3 = 255.0D;
          }
          d3 = (Math.log(1.0D + (d3 - 25.0D) / 230.0D) - Math.log(2.0D) + 1.0D) * 255.0D;
          d3 = (int)d3 | 0xA;
          m = (int)(d3 / 4.5D);
          paramGraphics.setColor(new Color((int)d3, m, m));
        }
        else
        {
          if (d3 > 255.0D) {
            d3 = 255.0D;
          }
          d3 = (Math.log(1.0D + (d3 - 25.0D) / 230.0D) - Math.log(2.0D) + 1.0D) * 255.0D;
          d3 = (int)d3 | 0xA;
          m = (int)(d3 / 3.5D);
          paramGraphics.setColor(new Color(m, m, (int)d3));
        }
        paramGraphics.fillRect(j, k, 2, 2);
      }
    }
  }
  
  public static double pot(int paramInt1, int paramInt2)
  {
    Particle[] arrayOfParticle = PPD.particleArray;
    double d1 = 0.0D;
    for (int i = 0; i < arrayOfParticle.length; i++)
    {
      double d2 = Math.sqrt((paramInt1 - PPD.PPDZero.x) * (paramInt1 - PPD.PPDZero.x) + (paramInt2 - PPD.PPDZero.y) * (paramInt2 - PPD.PPDZero.y));
      double d3 = Math.sqrt((arrayOfParticle[i].x - paramInt1) * (arrayOfParticle[i].x - paramInt1) + (arrayOfParticle[i].y - paramInt2) * (arrayOfParticle[i].y - paramInt2));
      d1 += arrayOfParticle[i].Q * Math.log(d2 / d3);
    }
    return d1;
  }
  
  public static double pot(int paramInt1, int paramInt2, Particle paramParticle)
  {
    double d1 = Math.sqrt((paramInt1 - PPD.PPDZero.x) * (paramInt1 - PPD.PPDZero.x) + (paramInt2 - PPD.PPDZero.y) * (paramInt2 - PPD.PPDZero.y));
    double d2 = Math.sqrt((paramParticle.x - paramInt1) * (paramParticle.x - paramInt1) + (paramParticle.y - paramInt2) * (paramParticle.y - paramInt2));
    return paramParticle.Q * Math.log(d1 / d2);
  }
  
  public static double energy()
  {
    Particle[] arrayOfParticle = PPD.particleArray;
    double d1 = 0.0D;
    for (int i = 0; i < arrayOfParticle.length; i++)
    {
      double d2 = 0.0D;
      for (int j = 0; j < arrayOfParticle.length; j++) {
        if (j != i)
        {
          double d3 = Math.sqrt((arrayOfParticle[j].x - PPD.PPDZero.x) * (arrayOfParticle[j].x - PPD.PPDZero.x) + (arrayOfParticle[j].y - PPD.PPDZero.y) * (arrayOfParticle[j].y - PPD.PPDZero.y));
          double d4 = Math.sqrt((arrayOfParticle[j].x - arrayOfParticle[i].x) * (arrayOfParticle[j].x - arrayOfParticle[i].x) + (arrayOfParticle[j].y - arrayOfParticle[i].y) * (arrayOfParticle[j].y - arrayOfParticle[i].y));
          d2 += arrayOfParticle[j].Q * Math.log(d3 / d4);
        }
      }
      d1 += arrayOfParticle[i].Q * d2;
    }
    return d1;
  }
  
  public static double forceSum(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Particle[] arrayOfParticle = PPD.particleArray;
    double d1 = 0.0D;
    for (int i = 0; i < arrayOfParticle.length; i++)
    {
      double d2 = paramInt1 - arrayOfParticle[i].x;
      double d3 = paramInt2 - arrayOfParticle[i].y;
      double d4 = (arrayOfParticle[i].x - paramInt1) * (arrayOfParticle[i].x - paramInt1) + (arrayOfParticle[i].y - paramInt2) * (arrayOfParticle[i].y - paramInt2);
      double d5 = d2 / d4;
      double d6 = d3 / d4;
      d1 += arrayOfParticle[i].Q * d5 * paramInt3;
      d1 += arrayOfParticle[i].Q * d6 * paramInt4;
    }
    return d1;
  }
  
  public static void plotForceArrow(Graphics paramGraphics, int paramInt1, int paramInt2)
  {
    Particle[] arrayOfParticle = PPD.particleArray;
    Arrow localArrow = new Arrow();
    double d1 = 0.0D;
    double d2 = 0.0D;
    for (int i = 0; i < arrayOfParticle.length; i++)
    {
      double d3 = paramInt1 - arrayOfParticle[i].x;
      double d4 = paramInt2 - arrayOfParticle[i].y;
      double d5 = (arrayOfParticle[i].x - paramInt1) * (arrayOfParticle[i].x - paramInt1) + (arrayOfParticle[i].y - paramInt2) * (arrayOfParticle[i].y - paramInt2);
      double d6 = d3 / d5;
      double d7 = d4 / d5;
      d1 += arrayOfParticle[i].Q * d6;
      d2 += arrayOfParticle[i].Q * d7;
    }
    paramGraphics.setColor(Color.black);
    localArrow.build(paramInt1, paramInt2, d1, d2, PPD.arrow_scale);
    localArrow.draw(paramGraphics);
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Calculus.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */