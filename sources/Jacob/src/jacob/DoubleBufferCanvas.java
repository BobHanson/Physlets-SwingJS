package jacob;

import a2s.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class DoubleBufferCanvas
  extends Canvas
{
  Image offscreen;
  
  public void invalidate()
  {
    super.invalidate();
    this.offscreen = null;
  }
  
  public void update(Graphics paramGraphics)
  {
    buffPaint(paramGraphics);
  }
  
  public void buffPaint(Graphics paramGraphics)
  {
    if (this.offscreen == null) {
      this.offscreen = createImage(size().width, size().height);
    }
    Graphics localGraphics = this.offscreen.getGraphics();
    Rectangle localRectangle = paramGraphics.getClipRect();
    localGraphics.clipRect(localRectangle.x, localRectangle.y, localRectangle.width, localRectangle.height);
    localGraphics.setColor(getBackground());
    localGraphics.fillRect(localRectangle.x, localRectangle.y, localRectangle.width, localRectangle.height);
    paint(localGraphics);
    paramGraphics.drawImage(this.offscreen, 0, 0, null);
    localGraphics.dispose();
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/DoubleBufferCanvas.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */