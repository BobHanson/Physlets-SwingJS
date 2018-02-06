package optics;

import java.awt.*;

public final class ISource extends Source {
  int spread,spacing;  //spread is width of beam, spacing is in between rays
  double angle;  //the slope of the infinite rays

  public ISource(Bench b, int xTransfer, int yTransfer,int size, double angleT,boolean i, boolean d,int dir,boolean pd){
    super(b);
    resizable = pd;
    info = i;
    noDrag = !d;
    direction=dir;
    xPosition = xTransfer;
    yPosition = yTransfer;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
    //theta = tTransfer;
    //numberOfRays = rays;
    spread = size;      //spread of rays (pixel height from top to bottom)    100 default
    spacing = 10;      //spacing between rays
    angle = angleT;         //default is parallel to horizon   0 default
    mat[0][0] = 1;
    mat[0][1] = 0;
    mat[1][0] = 0;
    mat[1][1] = 1;
  }

  public int isInside(int mouseX,int mouseY,Rectangle r){
    if (mouseX>xPosition-10 && mouseX<xPosition+10 &&
        mouseY>yPosition-50 && mouseY<yPosition+50)
        return 1;           //drag whole thing
    if (resizable && mouseX>(xPosition+(direction*40)-10) && mouseX<(xPosition+(direction*40)+10) &&
        mouseY>(yPosition+angle*(direction*40))-10 && mouseY<(yPosition+angle*(direction*40))+10)
        return 2;           //change angle of rays
    return 0;
  }

  public int getSpread(){
    return spread;
  }

  public void setSpread(int spreadTransfer){
    if (spreadTransfer<1) spread = 1;
    else spread = spreadTransfer;
  }

  public int getSpacing(){
    return spacing;
  }

  public void setSpacing(int spacingTransfer){
    if (spacingTransfer<1) spacing = 1;
    else spacing = spacingTransfer;
  }

  public double getAngle(){
    return angle;
  }

  public void setAngle(double angleTransfer){
    if (angleTransfer<-1) angle = -1.0;
      else if (angleTransfer>1) angle = 1.0;
      else angle = angleTransfer;
  }

  public void paint(Graphics g,Rectangle r){
    if(color==null)g.setColor(Color.green);
        else  g.setColor(color);
    /*g.drawLine(xPosition,yPosition,xPosition,r.height/2);
    if (yPosition<r.height/2){
    g.drawLine(xPosition,yPosition,xPosition+5,yPosition+10);
    g.drawLine(xPosition,yPosition,xPosition-5,yPosition+10);
    }
    else{
    g.drawLine(xPosition,yPosition,xPosition+5,yPosition-10);
    g.drawLine(xPosition,yPosition,xPosition-5,yPosition-10);
    }*/
    g.drawOval(xPosition-10,yPosition-spread/2,20,spread);
  }

  public void paintActive(Graphics g,Rectangle r){
    //paint(g,r);
    if (!noDrag){
      g.setColor(Color.white);
      g.fillOval(xPosition-3,yPosition-3,6,6);
    }
    if(resizable)g.fillOval(xPosition+(direction*40)-3,(int)yPosition+(int)(angle*(direction*40))-3,6,6);
    if (info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit)+
        " , y ="+df.format(1.0*r.height/2/pixPerUnit-1.0*yPosition/pixPerUnit),xPosition,r.height-90);
      g.drawString(bench.owner.label_spacing+" "+df.format(1.0*spacing/pixPerUnit),xPosition,r.height-70);
      g.drawString(bench.owner.label_spread+" "+df.format(1.0*spread/pixPerUnit),xPosition,r.height-50);
      g.drawString(bench.owner.label_anglevalue+" "+df.format(angle),xPosition,r.height-30);
    }
  }

  public String getType(){
    return "isource";
  }
}