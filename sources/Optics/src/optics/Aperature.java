package optics;

import java.awt.*;

public final class Aperature extends OpticElement {
  int openingSize;

  public Aperature(Bench b, int xTransfer, int yTransfer, int openingSizeTransfer,boolean i, boolean d,double s,boolean pd) {
    super(b);
    resizable = pd;
    info = i;
    noDrag = !d;
    xPosition = xTransfer;
    yPosition = yTransfer;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
    openingSize = openingSizeTransfer;
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    double tempV = v[0]-r.height/2;
    v[0] = v[0]*mat[0][0] + v[1]*mat[0][1];
    v[1] = (tempV*mat[1][0] + v[1]*mat[1][1]);
    return v;
  }

  public void paint(Graphics g, Rectangle r){
    g.setColor(Color.yellow);
    g.drawLine(xPosition,0,xPosition,r.height/2-openingSize/2);
    g.drawLine(xPosition,r.height,xPosition,r.height/2+openingSize/2);
  }

  public void paintActive(Graphics g, Rectangle r){
    //g.setColor(Color.yellow);
    //g.drawLine(xPosition,r.height/2+openingSize/5,xPosition,r.height/2-openingSize/5);
    if (!noDrag){
      g.setColor(Color.white);
      g.fillOval(xPosition-3,r.height/2-openingSize/2-3,6,6);
      g.fillOval(xPosition-3,r.height/2+openingSize/2-3,6,6);
    }
    if (info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit),xPosition,r.height-50);
      g.drawString(bench.owner.label_opening+" "+df.format(1.0*openingSize/pixPerUnit),xPosition,r.height-30);
    }
  }

  public void setOpeningSize(int openingSizeTransfer){
    if (openingSizeTransfer<=0) openingSize = 0;
    else openingSize = openingSizeTransfer;
  }

  public int getOpeningSize(){
    return openingSize;
  }

  public int isInside(int mouseX,int mouseY,Rectangle r){
    if (mouseX>xPosition-10 && mouseX<xPosition+10
       && (mouseY<r.height/2-openingSize/2-5 || mouseY>r.height/2+openingSize/2+5)) return 1;
    if (!noDrag && mouseX>xPosition-10 && mouseX<xPosition+10
       && mouseY>r.height/2-openingSize/2-5
       && mouseY<r.height/2-openingSize/2+5) {return 2;}    //top handle
    if (!noDrag && mouseX>xPosition-10 && mouseX<xPosition+10
       && mouseY>r.height/2+openingSize/2-5
       && mouseY<r.height/2+openingSize/2+5) {return 3;}    //bottom handle
    return 0;
  }

  public String getType(){
    return "aperature";
  }
}