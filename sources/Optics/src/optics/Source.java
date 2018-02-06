package optics;

import java.awt.*;

public class Source extends OpticElement {
    String typeOfSource;
    int numberOfRays, i, lengthOfRay = 1000;
    int interval,direction=1;
    double theta,rayIncrement = .25,raySlope = -1;  //default rayIncrement and ray slope (spread)
    int nRays=-1;  // the number of rays.  Specify either nRays or raySlope.  raySlope is the default for backward compatibility.

  public Source(Bench b) {
    super(b);
  }

  public int isInside(int mouseX,int mouseY,Rectangle r){
    if ((!noDrag || resizable) &&(mouseX>xPosition-10 && mouseX<xPosition+10 && mouseY>yPosition-10 && mouseY<yPosition+10)) return 1;
    if (resizable && mouseX>(xPosition+(direction*40)-5) && mouseX<(xPosition+(direction*40)+5) &&
        mouseY>(yPosition+raySlope*(direction*40))-5 && mouseY<(yPosition+raySlope*(direction*40))+5)
        return 2; //inside angle adjustment oval
    return 0;
  }

  public Source(Bench b, String type){
    super(b);
    typeOfSource = type;
  }

  public Source(Bench b, int xTransfer, int yTransfer,double rayIncrementT, double raySlopeT,boolean i,boolean d,int dir,boolean pd){
    super(b);
    //propertyDrag = pd;
    rayIncrement = rayIncrementT;
    raySlope = raySlopeT;
    info = i;
    noDrag = !d;
    direction = dir;
    xPosition = xTransfer;
    yPosition = yTransfer;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
    //theta = tTransfer;
    //numberOfRays = rays;
    mat[0][0] = 1;
    mat[0][1] = 0;
    mat[1][0] = 0;
    mat[1][1] = 1;
  }

  public void paint(Graphics g,Rectangle r){
    g.setColor(new Color(200,50,50));
    g.fillRect(xPosition-8,yPosition-8,16,16);
    if(color==null)g.setColor(Color.red);
        else  g.setColor(color);
    g.fillOval(xPosition-3,yPosition-3,6,6);
    g.setColor(Color.gray);
    g.drawRect(xPosition-9,yPosition-9,17,17);
    g.setColor(Color.black);
  }

  public void paintActive(Graphics g,Rectangle r){
    if(!noDrag){
      g.setColor(Color.white);
      g.fillRect(xPosition-8,yPosition-8,16,16);   // hot spot
      g.setColor(Color.gray);
      g.drawRect(xPosition-9,yPosition-9,17,17);
    }
    if(resizable){
        g.setColor(Color.white);
        g.fillOval(xPosition+(direction*40)-3,(int)(yPosition+(raySlope*(direction*40))-3),6,6);
    }
    if (info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit)+
        " , y ="+df.format(1.0*r.height/2/pixPerUnit-1.0*yPosition/pixPerUnit),xPosition,r.height-70);
      g.drawString(bench.owner.label_rayinc+" "+df.format(rayIncrement),xPosition,r.height-50);
      g.drawString(bench.owner.label_rayslope+" "+df.format(-raySlope),xPosition,r.height-30);
    }
  }

  public String getType(){
    return "source";
  }

  public double getTheta(){
    return 2/(theta-1);
  }

  public int getRays(){
    return numberOfRays;
  }

  public void setRayIncrement(double rayIncrementTransfer){
    if (rayIncrementTransfer<=0) rayIncrement = .005;
      else rayIncrement = rayIncrementTransfer;
  }

  public double getRayIncrement(){
    return rayIncrement;
  }

  public void setRaySlope(double slope){
      raySlope=slope;
      if(raySlope>5)raySlope=5;
      if(raySlope<-5)raySlope=-5;
  }
/*
  public void setRaySlope(double raySlopeTransfer){
    if (raySlopeTransfer>0) raySlope = -raySlopeTransfer;
    //  else if(raySlopeTransfer<-1) raySlope = -1;
      else raySlope = raySlopeTransfer;
    if (raySlope<-1) raySlope=-1;
  } */

  public double getRaySlope(){
    return raySlope;
  }

  public void setDirection(int dt){
    direction = dt;
  }

  public int getDirection(){
    return direction;
  }
}
