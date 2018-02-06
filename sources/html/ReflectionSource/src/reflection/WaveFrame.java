package reflection;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WaveFrame extends Frame
{
  Image img = null;

  public WaveFrame(Image paramImage)
  {
    this();
    this.img = paramImage;
    setSize(paramImage.getWidth(this), paramImage.getHeight(this));
    setLocation((int)(300.0D * Math.random()), (int)(300.0D * Math.random()));
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent paramAnonymousWindowEvent)
      {
        WaveFrame.this.dispose();
      }
    });
    setTitle(getClass().getName());
  }

  public WaveFrame()
  {
  }

  public void paint(Graphics paramGraphics)
  {
    if (this.img != null)
    {
      int i = getSize().width;
      int j = getSize().height;
      paramGraphics.drawImage(this.img, 0, 0, i, j, this);
    }
  }

  public void update(Graphics paramGraphics)
  {
    paint(paramGraphics);
  }
}

/* Location:           C:\Users\wc\PhysletWorkspace\html\ReflectionSource\bin\
 * Qualified Name:     reflection.WaveFrame
 * JD-Core Version:    0.6.2
 */