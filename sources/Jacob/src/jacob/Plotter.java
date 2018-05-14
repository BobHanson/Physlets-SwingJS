package jacob;

import a2s.*;
import java.awt.BorderLayout;
import java.awt.Event;

public class Plotter
  extends Frame
{
  PlotterCanvas plotterCanvas;
  
  public Plotter(String paramString, int paramInt1, int paramInt2)
  {
    super(paramString);
    addNotify();
    setLayout(new BorderLayout());
    add("Center", this.plotterCanvas = new PlotterCanvas(paramInt1));
    resize(paramInt1, paramInt2);
    show();
  }
  
  public void addData(double paramDouble)
  {
    this.plotterCanvas.addData(paramDouble);
  }
  
  public boolean handleEvent(Event paramEvent)
  {
    Object localObject = paramEvent.arg;
    if (paramEvent.id == 201)
    {
      hide();
      return true;
    }
    if (paramEvent.id == 401) {}
    return super.handleEvent(paramEvent);
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Plotter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */