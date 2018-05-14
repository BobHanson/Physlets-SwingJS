package jacob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Vector;

public final class Draw
{
  public static void drawElements(Graphics paramGraphics, Vector paramVector, Rectangle paramRectangle)
  {
    Element localElement;
    int i;
    for (i = 0; i < paramVector.size(); i++)
    {
      localElement = (Element)paramVector.elementAt(i);
      if ((paramRectangle == null) || (localElement.bounds.intersects(paramRectangle))) {
        localElement.drawShadow(paramGraphics);
      }
    }
    for (i = 0; i < paramVector.size(); i++)
    {
      localElement = (Element)paramVector.elementAt(i);
      if ((paramRectangle == null) || (localElement.bounds.intersects(paramRectangle))) {
        localElement.drawBg(paramGraphics);
      }
    }
    for (i = 0; i < PPD.particleBox.size(); i++)
    {
      Particle localParticle = (Particle)PPD.particleBox.elementAt(i);
      localParticle.drawShadow(paramGraphics);
    }
    for (i = 0; i < paramVector.size(); i++)
    {
      localElement = (Element)paramVector.elementAt(i);
      if ((paramRectangle == null) || (localElement.bounds.intersects(paramRectangle))) {
        localElement.drawFg(paramGraphics);
      }
    }
  }
  
  public static void drawParticles(Graphics paramGraphics, Vector paramVector, boolean paramBoolean)
  {
    paramBoolean = true;
    for (int i = 0; i < paramVector.size(); i++) {
      ((Particle)paramVector.elementAt(i)).draw(paramGraphics, paramBoolean);
    }
  }
  
  public static Color brighter(Color paramColor, double paramDouble)
  {
    return new Color(Math.min((int)(paramColor.getRed() * (1.0D / paramDouble)), 255), Math.min((int)(paramColor.getGreen() * (1.0D / paramDouble)), 255), Math.min((int)(paramColor.getBlue() * (1.0D / paramDouble)), 255));
  }
  
  public static Color darker(Color paramColor, double paramDouble)
  {
    return new Color(Math.max((int)(paramColor.getRed() * paramDouble), 0), Math.max((int)(paramColor.getGreen() * paramDouble), 0), Math.max((int)(paramColor.getBlue() * paramDouble), 0));
  }
  
  public static void storeBounds(Rectangle paramRectangle, Element paramElement)
  {
    if (paramElement != null)
    {
      paramRectangle.x = paramElement.bounds.x;
      paramRectangle.y = paramElement.bounds.y;
      paramRectangle.width = paramElement.bounds.width;
      paramRectangle.height = paramElement.bounds.height;
    }
    else
    {
      paramRectangle.x = 0;
      paramRectangle.y = 0;
      paramRectangle.width = 0;
      paramRectangle.height = 0;
    }
  }
  
  public static void trimPolyPoints(Polygon paramPolygon)
  {
    if (paramPolygon.xpoints.length >= 2 * paramPolygon.npoints)
    {
      int i = paramPolygon.xpoints.length;
      while (i >> 1 > paramPolygon.npoints) {
        i >>= 1;
      }
      int[] arrayOfInt = new int[i];
      System.arraycopy(paramPolygon.xpoints, 0, arrayOfInt, 0, paramPolygon.npoints);
      paramPolygon.xpoints = arrayOfInt;
      arrayOfInt = new int[i];
      System.arraycopy(paramPolygon.ypoints, 0, arrayOfInt, 0, paramPolygon.npoints);
      paramPolygon.ypoints = arrayOfInt;
    }
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Draw.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */