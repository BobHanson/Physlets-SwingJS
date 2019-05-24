package jacob;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;

class PlotterCanvas extends DoubleBufferCanvas
{
  double[] plotdata = new double[1];
  int plotindex = 0;
  double iscale;
  double scale = this.iscale = 0.25D;
  
  public PlotterCanvas(int paramInt) {}
  
  public void paint(Graphics paramGraphics)
  {
    int i = size().height / 2;
    if (size().width != this.plotdata.length)
    {
      this.plotdata = new double[size().width];
      this.plotindex = 0;
    }
    paramGraphics.setColor(Color.blue);
    int k;
    for (int j = 0; j < size().width; j++)
    {
      k = i + (int)(this.plotdata[((this.plotindex + j) % this.plotdata.length)] * this.scale);
      k = size().height - k;
      paramGraphics.drawLine(j, i, j, k);
    }
    if (this.iscale / this.scale < i / 2)
    {
      paramGraphics.setColor(Color.yellow);
      for (k = 0; k < i; k = (int)(k + i * this.scale / this.iscale))
      {
        paramGraphics.drawLine(0, i + k, size().width, i + k);
        paramGraphics.drawLine(0, i - k, size().width, i - k);
      }
    }
    paramGraphics.setColor(Color.white);
    paramGraphics.drawLine(0, i, size().width, i);
  }
  
  public void addData(double paramDouble)
  {
    this.plotdata[this.plotindex] = paramDouble;
    this.plotindex = ((this.plotindex + 1) % this.plotdata.length);
    calcScale();
    repaint();
  }
  
  private void calcScale()
  {
    double d = 0.0D;
    for (int i = 0; i < this.plotdata.length; i++) {
      if ((this.plotdata[i] != Double.NaN) && (Math.abs(this.plotdata[i]) > d)) {
        d = Math.abs(this.plotdata[i]);
      }
    }
    if (d == 0.0D) {
      return;
    }
    if (size().height / 2 < d * this.scale) {
      this.scale /= 2.0D;
    } else if (size().height / 2 > d * this.scale * 2.0D) {
      this.scale *= 2.0D;
    }
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/PlotterCanvas.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */