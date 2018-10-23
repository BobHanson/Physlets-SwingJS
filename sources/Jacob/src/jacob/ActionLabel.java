package jacob;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;

public class ActionLabel extends Label
{
  boolean out;
  
  public ActionLabel(boolean paramBoolean)
  {
    this("", paramBoolean);
  }
  
  public ActionLabel(String paramString, boolean paramBoolean)
  {
    super(paramString);
    this.out = paramBoolean;
    setForeground(PPD.ACTIONLABEL_FOREGROUND);
    setBackground(PPD.ACTIONLABEL_BACKGROUND);
  }
  
  public void paint(Graphics paramGraphics)
  {
    Color localColor1 = getBackground();
    Color localColor2 = Draw.brighter(localColor1, 0.8D);
    Color localColor3 = Draw.darker(localColor1, 0.65D);
    int i = size().width - 1;
    int j = size().height - 1;
    if (this.out)
    {
      paramGraphics.setColor(localColor2);
      paramGraphics.drawLine(0, 0, i, 0);
      paramGraphics.drawLine(0, 0, 0, j);
      paramGraphics.setColor(localColor3);
      paramGraphics.drawLine(i, 0, i, j);
      paramGraphics.drawLine(0, j, i, j);
    }
    else
    {
      paramGraphics.setColor(localColor2);
      paramGraphics.drawLine(i, 0, i, j);
      paramGraphics.drawLine(0, j, i, j);
      paramGraphics.setColor(localColor3);
      paramGraphics.drawLine(0, 0, i, 0);
      paramGraphics.drawLine(0, 0, 0, j);
    }
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/ActionLabel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */