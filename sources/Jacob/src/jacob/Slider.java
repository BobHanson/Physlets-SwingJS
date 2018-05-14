package jacob;

import a2s.*;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;

class Slider
  extends Canvas
{
  public final int VALUE_CHANGED = 5000;
  public final int X_OFFSET = 2;
  public final int Y_OFFSET = 2;
  public final int BSIZE = 1;
  double value = 0.0D;
  
  public Slider() {}
  
  public Slider(double paramDouble)
  {
    this.value = paramDouble;
  }
  
  public boolean handleEvent(Event paramEvent)
  {
    int i = 3;
    int j = 3;
    int k = getSize().width - 6;
    int m = getSize().height - 6;
    switch (paramEvent.id)
    {
    case 501: 
      setValue((paramEvent.x - i) / k);
      postEvent(new Event(this, 5000, null));
      return true;
    case 506: 
      setValue((paramEvent.x - i) / k);
      postEvent(new Event(this, 5000, null));
      return true;
    }
    return super.handleEvent(paramEvent);
  }
  
  public void paint(Graphics paramGraphics)
  {
    int i = 2;
    int j = 2;
    int k = getSize().width - 2;
    int m = getSize().height - 2;
    int n = 3;
    int i1 = 3;
    int i2 = (int)((k - 2 - 2 + 1) * this.value);
    int i3 = m - 2 - 2 + 1;
    paramGraphics.setColor(getBackground().darker());
    for (int i4 = 0; i4 < 1; i4++)
    {
      paramGraphics.drawLine(i + i4, j + i4, k - i4, j + i4);
      paramGraphics.drawLine(i + i4, j + i4, i + i4, m - i4);
    }
    paramGraphics.setColor(getBackground().brighter());
    for (int i5 = 0; i5 < 1; i5++)
    {
      paramGraphics.drawLine(i + i5, m - i5, k, m - i5);
      paramGraphics.drawLine(k - i5, j + i5, k - i5, m - i5);
    }
    paramGraphics.setColor(PPD.SLIDER_COLOR);
    paramGraphics.fillRect(n, i1, i2, i3);
  }
  
  public double getValue()
  {
    return this.value;
  }
  
  public synchronized void setValue(double paramDouble)
  {
    if (paramDouble < 0.0D) {
      paramDouble = 0.0D;
    }
    if (paramDouble > 1.0D) {
      paramDouble = 1.0D;
    }
    this.value = paramDouble;
    repaint();
  }
  
  public Dimension preferredSize()
  {
    return new Dimension(80, 25);
  }
  
  public Dimension minimumSize()
  {
    return new Dimension(60, 20);
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Slider.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */