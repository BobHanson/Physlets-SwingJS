package jacob;

import java.awt.Color;
import java.util.Properties;
import java.util.StringTokenizer;

public class Resources
  extends Properties
{
  public boolean getBoolean(String paramString, boolean paramBoolean)
  {
    String str = getProperty(paramString);
    if (str != null)
    {
      if ("true".equalsIgnoreCase(str.trim())) {
        return true;
      }
      if ("false".equalsIgnoreCase(str.trim())) {
        return false;
      }
    }
    return paramBoolean;
  }
  
  public Color getColor(String paramString, Color paramColor)
  {
    String str = getProperty(paramString);
    if (str != null)
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(str);
      try
      {
        int i = Integer.parseInt(localStringTokenizer.nextToken());
        int j = Integer.parseInt(localStringTokenizer.nextToken());
        int k = Integer.parseInt(localStringTokenizer.nextToken());
        return new Color(i, j, k);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        return paramColor;
      }
    }
    return paramColor;
  }
  
  public double getDouble(String paramString, double paramDouble)
  {
    String str = getProperty(paramString);
    double d = paramDouble;
    if (str != null) {
      try
      {
        d = Double.valueOf(str.trim()).doubleValue();
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    return d;
  }
  
  public int getInt(String paramString, int paramInt)
  {
    String str = getProperty(paramString);
    int i = paramInt;
    if (str != null) {
      try
      {
        i = Integer.parseInt(str.trim());
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    return i;
  }
  
  public String getString(String paramString1, String paramString2)
  {
    String str = getProperty(paramString1);
    if (str != null)
    {
      int j;
      int i = str.length();
      for (j = 0; (j < i) && (str.charAt(j) != '"'); j++) {}
      j++;
      while ((j < i) && (str.charAt(i - 1) != '"')) {
        i--;
      }
      i--;
      if (j < i) {
        return str.substring(j, i);
      }
    }
    return paramString2;
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Resources.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */