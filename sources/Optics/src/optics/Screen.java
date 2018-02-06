package optics;

import java.awt.*;

public final class Screen extends OpticElement {

  public Screen(Bench b, int xTransfer, int yTransfer,boolean i, boolean d,double s,boolean pd) {
    super(b);
    resizable = pd;
    info = i;
    noDrag = !d;
    xPosition = xTransfer;
    yPosition = yTransfer;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    double tempV = v[0]-r.height/2;
    v[0] = v[0]*mat[0][0] + v[1]*mat[0][1];
    v[1] = (tempV*mat[1][0] + v[1]*mat[1][1]);
    return v;
  }

  public void paint(Graphics g,Rectangle r){
    if(xPosition<0) return;
    if(color==null)g.setColor(Color.yellow);
        else  g.setColor(color);
    g.drawLine(xPosition,0,xPosition,r.height);
    g.setColor(Color.orange);
    g.drawLine(xPosition+1,0,xPosition+1,r.height);
  }

  public void paintActive(Graphics g,Rectangle r){
    if (!noDrag){
      g.setColor(Color.white);
      g.drawLine(xPosition,0,xPosition,r.height);
    }
    if(info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit),xPosition,r.height-30);
    }
  }

  public String getType(){
    return "screen";
  }
}
