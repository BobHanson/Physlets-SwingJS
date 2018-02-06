package optics;

import java.awt.*;
//import borland.jbcl.layout.*;
//import borland.jbcl.control.*;
public class Mirror extends OpticElement {
  int curvatureFactor;
  int    radius;
  private Polygon outline= new Polygon();
  private int iheight=0;

  public Mirror(Bench b, int xTransfer, int yTransfer, double fl,boolean i, boolean d,double s,boolean pd){
    super(b);
    info = i;
    noDrag = !d;
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

  public void setFocalLength(double fl, Rectangle r){
    if(fl!=focalLength)makeOutline(iheight);
    if (fl>r.width-20) focalLength = r.width-20;
      else if (-fl>r.width-20) focalLength = -(r.width-20);
      else focalLength = fl;
    if (Math.abs(focalLength)<3){focalLength=0; mat[1][0]=0; }
    else mat[1][0] = -1/focalLength;
  }

  public void setY(double y){} // don't let the mirror move up or down.

  public void setPixX(int x,Rectangle r){
    super.setPixX(x,r);
    makeOutline(iheight);
  }

  void adjustPosition(){
     super.adjustPosition();
     makeOutline(iheight);
  }

  public double getFocalLength(){
    return focalLength;
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    double tempV = v[0]-r.height/2;
    v[0] = v[0]*mat[0][0] + v[1]*mat[0][1];
    v[1] = /*(-1*direction)*/(-1)*(tempV*mat[1][0] + v[1]*mat[1][1]);
    return v;
  }

    private void makeOutline(int h){
    if(focalLength<0)makeOutlineMinus(h);
    else makeOutlinePlus(h);
  }

  private void makeOutlinePlus(int h){
      int xpix,ypix;
      iheight=h;
      h=(int)(h*percentSize);
      outline= new Polygon();
      radius=(int)Math.abs(focalLength*2);
      double   theta0;
      if(h/2<radius)theta0=Math.asin(h/(double)radius/2.0);
          else theta0=Math.PI/2;
      double theta=theta0;
      double delta=theta0/50;
      double x0=radius*Math.cos(theta0);
      double offset=(radius-x0)-1;
      xpix=(int)(xPosition);
      ypix=(int)(radius*Math.sin(theta)+iheight/2);
      outline.addPoint(xpix,ypix);
      while(theta>-theta0){
        xpix=(int)(xPosition-x0+radius*Math.cos(theta)-offset);
        ypix=(int)(radius*Math.sin(theta)+iheight/2);
        outline.addPoint(xpix,ypix);
        theta-=delta;
      }
      xpix=(int)(xPosition);
      ypix=(int)(radius*Math.sin(theta)+iheight/2+2);
      outline.addPoint(xpix,ypix);
      for(int i=outline.npoints-1; i>=0; i--){
        outline.addPoint(outline.xpoints[i]+3,outline.ypoints[i]);
      }
  }

  private void makeOutlineMinus(int h){
      int xpix,ypix;
      iheight=h;
      h=(int)(h*percentSize);
      outline= new Polygon();
      radius=(int)Math.abs(focalLength*2);
      double theta0;
      if(h/2<radius)theta0=Math.asin(h/(double)radius/2.0);
          else theta0=Math.PI/2;
      double theta=theta0;
      double delta=theta0/50;
      double x0=radius*Math.cos(theta0);
      double offset=(radius-x0)-1;
      xpix=(int)(xPosition);
      ypix=(int)(radius*Math.sin(theta)+iheight/2);
      outline.addPoint(xpix,ypix);
      while(theta>-theta0){
        xpix=(int)(xPosition+x0-radius*Math.cos(theta)+offset);
        ypix=(int)(radius*Math.sin(theta)+iheight/2);
        outline.addPoint(xpix,ypix);
        theta-=delta;
      }
      xpix=(int)(xPosition);
      ypix=(int)(radius*Math.sin(theta)+iheight/2+2);
      outline.addPoint(xpix,ypix);

      for(int i=outline.npoints-1; i>=0; i--){
        outline.addPoint(outline.xpoints[i]+3,outline.ypoints[i]);
      }
  }


  public void paint(Graphics g,Rectangle r){
    if(r.height!=iheight)makeOutline(r.height);
    g.setColor(Color.lightGray);
    g.fillPolygon(outline);
    if(color==null)g.setColor(Color.blue);
      else  g.setColor(color);
    if(focalLength!=0)g.drawPolygon(outline);
    else{
          g.drawLine(xPosition+1,0,xPosition+1,r.height);
          g.drawLine(xPosition,0,xPosition,r.height);
          g.drawLine(xPosition-1,0,xPosition-1,r.height);
    }
    //this just allows the mirror to be seen at fl = infinity
    g.drawLine(xPosition,(int)(r.height*(1-percentSize)/2.0),xPosition,0);
    g.drawLine(xPosition,(int)(r.height*(1+percentSize)/2.0),xPosition,r.height);
    if(showFocus){
      int hot=(int)focalLength;
      g.setColor(Color.white);
      g.fillOval(xPosition-(int)hot-3,r.height/2-3,6,6);
      g.fillOval(xPosition+(int)hot-3,r.height/2-3,6,6);
    }
  }

  public void paintActive(Graphics g,Rectangle r){
    int hot=(int)focalLength;
    if(Math.abs(focalLength)<3) hot=20;
    if(!noDrag){
      g.setColor(Color.green);
      g.drawLine(xPosition,0,xPosition,r.height);
    }
    if(showFocus || resizable){
      g.setColor(Color.white);
      g.fillOval(xPosition-(int)hot-3,r.height/2-3,6,6);
      g.fillOval(xPosition+(int)hot-3,r.height/2-3,6,6);
    }
    if (info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit),xPosition,r.height-70);
      g.drawString(bench.owner.label_focal_length+" "+df.format(1.0*focalLength/pixPerUnit),xPosition,r.height-30);
    }
  }

  public int isInside(int mouseX,int mouseY,Rectangle r){
    int hot=(int)(focalLength);
    if(Math.abs(focalLength)<3) hot=20;
    if (resizable &&
        mouseX>(xPosition+hot)-15 && mouseX<(xPosition+hot)+15 &&
        mouseY<(r.height/2+15) && mouseY>(r.height/2-15)
        ) return 2;               //positive R
    if (resizable &&
        mouseX>(xPosition-hot)-15 && mouseX<(xPosition-hot)+15 &&
        mouseY<(r.height/2+15) && mouseY>(r.height/2-15)
        ) return 3;               //negative R
    if ((!noDrag || resizable) && (mouseX>xPosition-10 && mouseX<xPosition+10))return 1;        //dragable
    return 0;
  }

  public String getType(){
    return "mirror";
  }
}

