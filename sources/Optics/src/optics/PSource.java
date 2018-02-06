package optics;

import java.awt.*;

public final class PSource extends Source {     //Principal-Ray Source
  public PSource(Bench b, int xTransfer, int yTransfer,double tTransfer, int rays,boolean i, boolean d,double s,boolean pd){
    super(b);
    //propertyDrag = pd;
    info = i;
    noDrag = !d;
    drawDashedRay=true;      //to draw dashed principal rays
    drawSourceRay=true;            //to draw regular rays

    xPosition = xTransfer;
    yPosition = yTransfer;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
    theta = tTransfer;
    numberOfRays = rays;
    mat[0][0] = 1;
    mat[0][1] = 0;
    mat[1][0] = 0;
    mat[1][1] = 1;
    direction=1;
  }
  public void init(int xTransfer, int yTransfer,double tTransfer, int rays,boolean i, boolean d,double s,boolean pd){
    resizable = pd;
    info = i;
    noDrag = !d;
    drawDashedRay=true;      //to draw dashed principal rays
    drawSourceRay=true;            //to draw regular rays
    xPosition = xTransfer;
    yPosition = yTransfer;
    theta = tTransfer;
    numberOfRays = rays;
    mat[0][0] = 1;
    mat[0][1] = 0;
    mat[1][0] = 0;
    mat[1][1] = 1;
    direction=1;
  }

  public void paint(Graphics g,Rectangle r){
    g.setColor(Color.lightGray);
    g.drawLine(xPosition,yPosition,xPosition,r.height/2);
    g.setColor(Color.white);
    g.drawLine(xPosition+1,yPosition,xPosition+1,r.height/2);
    g.setColor(Color.gray);
    g.drawLine(xPosition-1,yPosition,xPosition-1,r.height/2);
    g.setColor(Color.lightGray);
    if (yPosition<r.height/2){
      g.drawLine(xPosition,yPosition,xPosition+5,yPosition+10);
      g.drawLine(xPosition,yPosition,xPosition-5,yPosition+10);
      g.setColor(Color.white);
      g.drawLine(xPosition+1,yPosition,xPosition+6,yPosition+10);
      g.drawLine(xPosition+1,yPosition,xPosition-4,yPosition+10);
      g.setColor(Color.gray);
      g.drawLine(xPosition-1,yPosition,xPosition+4,yPosition+10);
      g.drawLine(xPosition-1,yPosition,xPosition-6,yPosition+10);
    }
    else{
      g.drawLine(xPosition,yPosition,xPosition+5,yPosition-10);
      g.drawLine(xPosition,yPosition,xPosition-5,yPosition-10);
      g.setColor(Color.white);
      g.drawLine(xPosition+1,yPosition,xPosition+6,yPosition-10);
      g.drawLine(xPosition+1,yPosition,xPosition-4,yPosition-10);
      g.setColor(Color.gray);
      g.drawLine(xPosition-1,yPosition,xPosition+4,yPosition-10);
      g.drawLine(xPosition-1,yPosition,xPosition-6,yPosition-10);
    }
  }

  public void paintTemp(Graphics g,Rectangle r){
    g.setColor(Color.lightGray);
    g.drawLine(xPosition,yPosition,xPosition,r.height/2);
    if (yPosition<r.height/2){
    g.drawLine(xPosition,yPosition,xPosition+5,yPosition+10);
    g.drawLine(xPosition,yPosition,xPosition-5,yPosition+10);
    }
    else{
    g.drawLine(xPosition,yPosition,xPosition+5,yPosition-10);
    g.drawLine(xPosition,yPosition,xPosition-5,yPosition-10);
    }
  }

  public void paintActive(Graphics g,Rectangle r){
    //paint(g,r);
    if (!noDrag){
      g.setColor(Color.white);
      g.fillOval(xPosition-3,yPosition-3,6,6);
    }
    if (info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit)+
        " , h ="+df.format(1.0*r.height/2/pixPerUnit-1.0*yPosition/pixPerUnit),xPosition,r.height-50);
    }
  }

  public void paintTempActive(Graphics g,Rectangle r){
    if (info){
      g.setColor(Color.yellow);
      //g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit)+
      //" ,h ="+df.format(1.0*yPosition/pixPerUnit),xPosition,r.height-30);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit)+
        " , h ="+df.format(1.0*r.height/2/pixPerUnit-1.0*yPosition/pixPerUnit),xPosition,r.height-50);
    }
  }

  public String getType(){
    return "psource";
  }
}