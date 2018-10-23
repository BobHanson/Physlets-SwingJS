package jacob;

import java.awt.*;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Graphics;

class PPDControls
  extends Panel
{
  Button start_stop;
  Button field;
  
  public PPDControls()
  {
    setForeground(PPD.CONTROLS_FOREGROUND);
    setLayout(new FlowLayout(0));
    add(this.start_stop = new Button(PPD.CONTROLS_START));
    this.field = new Button(PPD.CONTROLS_FIELD);
    setBackground(PPD.CONTROLS_BACKGROUND);
  }
  
  public boolean handleEvent(Event paramEvent)
  {
    Object localObject = paramEvent.arg;
    if ((paramEvent.target instanceof Button))
    {
      String str = (String)localObject;
      if (str != null)
      {
        if (str.equals(PPD.CONTROLS_START))
        {
          this.start_stop.setLabel(PPD.CONTROLS_STOP);
          PPD.animate = true;
          return true;
        }
        if (str.equals(PPD.CONTROLS_STOP))
        {
          this.start_stop.setLabel(PPD.CONTROLS_START);
          PPD.animate = false;
          return true;
        }
        if (str.equals(PPD.CONTROLS_FIELD))
        {
          this.field.setLabel(PPD.CONTROLS_FIELD_CLEAR);
          this.start_stop.disable();
          PPD.draw_field = true;
          return true;
        }
        if (str.equals(PPD.CONTROLS_FIELD_CLEAR))
        {
          this.field.setLabel(PPD.CONTROLS_FIELD);
          this.start_stop.enable();
          PPD.draw_field = false;
          return true;
        }
      }
    }
    return false;
  }
  
  public void paint(Graphics paramGraphics)
  {
    Color localColor1 = getBackground();
    Color localColor2 = Draw.brighter(localColor1, 0.8D);
    Color localColor3 = Draw.darker(localColor1, 0.65D);
    int i = size().width - 1;
    int j = size().height - 1;
    paramGraphics.setColor(localColor2);
    paramGraphics.drawLine(0, 0, i, 0);
    paramGraphics.drawLine(0, 0, 0, j);
    paramGraphics.setColor(localColor3);
    paramGraphics.drawLine(i, 0, i, j);
    paramGraphics.drawLine(0, j, i, j);
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/PPDControls.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */