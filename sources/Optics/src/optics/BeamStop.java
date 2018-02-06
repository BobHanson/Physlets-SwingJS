package optics;

import java.awt.*;

public final class BeamStop extends OpticElement {
  int topBlock;
  int bottomBlock;

  public BeamStop(Bench b, int xTransfer, int yTransfer,int top, int bottom,boolean i, boolean d,double s,boolean pd) {
    super(b);
    resizable = pd;
    info = i;
    noDrag = !d;
    xPosition = xTransfer;
    yPosition = yTransfer;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
    if(bottom>top){
      topBlock = top;
      bottomBlock = bottom;
    }else{
      topBlock = bottom;
      bottomBlock = top;
    }
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    double tempV = v[0]-r.height/2;
    v[0] = v[0]*mat[0][0] + v[1]*mat[0][1];
    v[1] = (tempV*mat[1][0] + v[1]*mat[1][1]);
    return v;
  }

  public void paint(Graphics g, Rectangle r){
    g.setColor(Color.yellow);
    //g.drawLine(xPosition,0,xPosition,r.height/2-openingSize/2);
    //g.drawLine(xPosition,r.height,xPosition,r.height/2+openingSize/2);
    g.drawLine(xPosition,bottomBlock,xPosition,topBlock);
  }

  public void paintActive(Graphics g, Rectangle r){
    //g.setColor(Color.yellow);
    //g.drawLine(xPosition,r.height/2+openingSize/5,xPosition,r.height/2-openingSize/5);
    if (!noDrag){
      g.setColor(Color.white);
      g.fillOval(xPosition-3,topBlock-3,6,6);
      g.fillOval(xPosition-3,bottomBlock-3,6,6);
    }
    if (info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit),xPosition,r.height-50);
      g.drawString(bench.owner.label_top+" "+df.format(1.0*topBlock/pixPerUnit),xPosition,r.height-30);
      g.drawString(bench.owner.label_top+" "+df.format(1.0*bottomBlock/pixPerUnit),xPosition,r.height-10);
    }
  }

  /*
  public void setOpeningSize(int openingSizeTransfer){
    if (openingSizeTransfer<=0) openingSize = 0;
    else openingSize = openingSizeTransfer;
  }*/

 public void setBottomBlock(int bottom){
    if (bottom<=0) bottomBlock = 0;
    else bottomBlock = bottom;
  }

  public void setTopBlock(int top){
    if (top<=0) topBlock = bench.iheight;
    else topBlock = top;
  }


  public int getBottomBlock(){
    return bottomBlock;
  }

  public int getTopBlock(){
    return topBlock;
  }

  public int isInside(int mouseX,int mouseY,Rectangle r){
    if (mouseX>xPosition-10 && mouseX<xPosition+10
       && (mouseY>topBlock-5 || mouseY<bottomBlock+5)) return 1;  // we are close inside the blocked region
    if (!noDrag && mouseX>xPosition-10 && mouseX<xPosition+10
       && mouseY>topBlock-5
       && mouseY<topBlock+5) {return 2;}    //top handle
    if (!noDrag && mouseX>xPosition-10 && mouseX<xPosition+10
       && mouseY>bottomBlock-5
       && mouseY<bottomBlock+5) {return 3;}    //bottom handle
    return 0;
  }

  public String getType(){
    return "aperature";
  }
}