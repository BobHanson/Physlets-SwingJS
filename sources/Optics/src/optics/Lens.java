package optics;

import java.awt.*;

public final class Lens extends OpticElement {
    double indexOfGlass=1.8;
    int    curvatureFactor;
    int    radius;
    private Polygon outline= new Polygon();
    private int iheight=0;

  public Lens(Bench b) {
        super(b);
  }

  public Lens(Bench b, int xTransfer, int yTransfer, double fl,boolean i, boolean d,double s,boolean pd){
    super(b);
    info = i;
    noDrag = !d;
    resizable=pd;
    percentSize = s;
    resizable = pd;
    curvatureFactor = 3;
    xPosition = xTransfer;
    yPosition = yTransfer;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
    focalLength = fl;
    mat[0][0] = 1;
    mat[0][1] = 0;
    if(focalLength==0 ) mat[1][0] =0;
      else mat[1][0] = -1/focalLength;
    mat[1][1] = 1;
  }

  private void makeOutline(int h){
    if( focalLength==0)makeOutlineZero(h);
    else if(focalLength<0)makeOutlineMinus(h);
    else makeOutlinePlus(h);
  }

  private void makeOutlineZero(int h){
      int xpix,ypix;
      iheight=h;
      h=(int)(h*percentSize);
      outline= new Polygon();
      xpix=(int)(xPosition);
      ypix=(int)(0);
      outline.addPoint(xpix,ypix);
      xpix=(int)(xPosition);
      ypix=(int)(iheight);
      outline.addPoint(xpix,ypix);
  }

  private void makeOutlinePlus(int h){
      int xpix,ypix;
      iheight=h;
      h=(int)(h*percentSize);
      outline= new Polygon();
      //double sfac=100/pixPerUnit;
     // if(sfac<=0.01)sfac=0.01;
      radius=(int)(1.5*h+Math.abs(2*focalLength));
      double theta0=Math.asin(h/(double)radius/2.0);
      double theta=theta0;
      double delta=theta0/50;
      double x0=radius*Math.cos(theta0);
      while(theta>-theta0){
        xpix=(int)(xPosition-x0+radius*Math.cos(theta)+3);
        ypix=(int)(radius*Math.sin(theta)+iheight/2);
        outline.addPoint(xpix,ypix);
        theta-=delta;
      }
      for(int i=outline.npoints-1; i>=0; i--){
        outline.addPoint(2*xPosition-outline.xpoints[i],outline.ypoints[i]);
      }
  }

  private void makeOutlineMinus(int h){
      int xpix,ypix;
      //double sfac=100/pixPerUnit;
      //if(sfac<=0.01)sfac=0.01;
      iheight=h;
      h=(int)(h*percentSize);
      outline= new Polygon();
      radius=(int)(1.5*h+Math.abs(2*focalLength));
      double theta0=Math.asin(h/(double)radius/2.0);
      double theta=theta0;
      double delta=theta0/50;
      double x0=radius*Math.cos(theta0);
      double thickness=radius-x0;
      xpix=(int)(xPosition);
      ypix=(int)(radius*Math.sin(theta)+iheight/2);
      outline.addPoint(xpix,ypix);
      while(theta>-theta0){
        xpix=(int)(xPosition-x0+radius*Math.cos(theta)-thickness-4);
        ypix=(int)(radius*Math.sin(theta)+iheight/2);
        outline.addPoint(xpix,ypix);
        theta-=delta;
      }
      xpix=(int)(xPosition);
      ypix=(int)(radius*Math.sin(theta)+iheight/2+2);
      outline.addPoint(xpix,ypix);

      for(int i=outline.npoints-1; i>=0; i--){
        outline.addPoint(2*xPosition-outline.xpoints[i],outline.ypoints[i]);
      }
  }

  public void paint(Graphics g,Rectangle r){
    if(r.height!=iheight)makeOutline(r.height);
    g.setColor(Color.lightGray);
    g.fillPolygon(outline);
    if(color==null)g.setColor(Color.blue);
      else g.setColor(color);
    g.drawPolygon(outline);
    g.drawLine(xPosition,(int)(r.height*(1-percentSize)/2.0),xPosition,0);
    g.drawLine(xPosition,(int)(r.height*(1+percentSize)/2.0),xPosition,r.height);
    if(showFocus){
          int hot=(int)focalLength;
          g.setColor(Color.white);
          g.drawLine(xPosition+(int)hot,r.height/2-15,
          xPosition+(int)hot,r.height/2+15);
          g.drawLine(xPosition-(int)hot,r.height/2-15,
          xPosition-(int)hot,r.height/2+15);
          g.fillOval(xPosition-(int)hot-3,r.height/2-3,6,6);
          g.fillOval(xPosition+(int)hot-3,r.height/2-3,6,6);
    }
  }

  public void paintActive(Graphics g,Rectangle r){
    int hot=(int)focalLength;
    if(Math.abs(focalLength)<2) hot=20;
    if (!noDrag){
      g.setColor(Color.blue);
      g.drawLine(xPosition,0,xPosition,r.height);
      /*drawing lines at two focal lengths beyond and behind lens
      g.drawLine(xPosition+(int)(2*hot),r.height/2-10,
      xPosition+(int)(2*hot),r.height/2+10);
      g.drawLine(xPosition-(int)(2*hot),r.height/2-10,
      xPosition-(int)(2*hot),r.height/2+10);
      */
    }
    //drawing lines at one focal length beyond and behind lens
    if(showFocus || resizable){
          g.setColor(Color.white);
          g.drawLine(xPosition+(int)hot,r.height/2-15,
          xPosition+(int)hot,r.height/2+15);
          g.drawLine(xPosition-(int)hot,r.height/2-15,
          xPosition-(int)hot,r.height/2+15);
          g.fillOval(xPosition-(int)hot-3,r.height/2-3,6,6);
          g.fillOval(xPosition+(int)hot-3,r.height/2-3,6,6);
    }
    if (info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit),xPosition,r.height-50);
      g.drawString(bench.owner.label_focal_length+" "+df.format(1.0*focalLength/pixPerUnit),xPosition,r.height-30);
    }
  }

  public int isInside(int mouseX,int mouseY,Rectangle r){
    int hot=(int)focalLength;
    if(Math.abs(focalLength)<2) hot=20;
    if (resizable &&
        mouseX>(xPosition+hot)-10 && mouseX<(xPosition+hot)+10 &&
        mouseY<(r.height/2+15) && mouseY>(r.height/2-15)
        ) return 2;               //positive fl
    if (resizable &&
        mouseX>(xPosition-hot)-10 && mouseX<(xPosition-hot)+10 &&
        mouseY<(r.height/2+15) && mouseY>(r.height/2-15)
        ) return 3;               //negative fl
    if ((!noDrag || resizable) &&(mouseX>xPosition-10 && mouseX<xPosition+10))return 1;        //dragable
    return 0;
  }

  public String getType(){
    return "lens";
  }

  public void setFocalLength(double fl, Rectangle r){
    if(fl!=focalLength){
      focalLength = fl;
      if(Math.abs(fl)<2){ focalLength=0; mat[1][0] = 0;  }
          else mat[1][0] = -1/focalLength;
      makeOutline(iheight);
    }
  }

  void adjustPosition(){
     super.adjustPosition();
     makeOutline(iheight);
  }

  public void setPixX(int x,Rectangle r){
    super.setPixX(x,r);
    makeOutline(iheight);
  }

  public void setY(double y){} // don't let the lens move up or down.

  public double getFocalLength(){
    return focalLength;
  }
 /*
  public int getWidth(){
    return 0;
  } */

  public double[] transform(double[] v,Rectangle r,int direction){
    double df=(indexOfGlass-indexOfRefraction)/(indexOfGlass-1);
    double tempV = v[0]-r.height/2;
  //  v[0] = v[0]*mat[0][0] + v[1]*mat[0][1];   // doesn't change
  //  v[1] = direction*(df*tempV*mat[1][0] + v[1]);    // Mike's version
     v[1] = direction*(df*tempV*mat[1][0]) + v[1];
    return v;
  }

}
