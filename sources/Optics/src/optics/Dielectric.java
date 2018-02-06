package optics;

import java.awt.*;

public class Dielectric extends OpticElement {
  //int    radius;
  //double n1=1, n2=1;    //indexes of refraction (1<index<2.4)
  double delN;
  int R;  //radius of curvature
  private Polygon outline= new Polygon();
  private int iheight=0;

  public Dielectric(Bench b, int xTransfer, int yTransfer, double dn,int r,boolean i, boolean d,double s,boolean pd){
    super(b);
    resizable = pd;
    info = i;
    noDrag = !d;
    percentSize = s;
    xPosition = xTransfer;
    yPosition = yTransfer;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);
    R = r;
    if(delN==0) focalLength=0;
    else focalLength = R/delN;
    delN=dn;
    mat[0][0] = 1;
    mat[0][1] = 0;
    if (R==0) mat[1][0] = 0;                  //flat interface (R=infinite)
      else mat[1][0] = -delN/R;        //curved interface
    mat[1][1] = 1;
  }
  private void makeOutline(int h){
    if(R>0)makeOutlineMinus(h);
    else makeOutlinePlus(h);
  }

  private void makeOutlinePlus(int h){
      int xpix,ypix;
      iheight=h;
      h=(int)(h*percentSize);
      outline= new Polygon();
      int radius=(int)Math.abs(R);
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
  }

  private void makeOutlineMinus(int h){
      int xpix,ypix;
      iheight=h;
      h=(int)(h*percentSize);
      outline= new Polygon();
      int radius=(int)Math.abs(R);
      double theta0;
      if(h/2<radius)theta0=Math.asin(h/(double)radius/2.0);
          else theta0=Math.PI/2;
      double theta=theta0;
      double delta=theta0/50;
      double x0=radius*Math.cos(theta0);
      double offset=(radius-x0)-1;
      //xpix=(int)(xPosition);
      xpix=(int)(xPosition+x0-radius*Math.cos(theta)+offset);
      ypix=(int)(radius*Math.sin(theta)+iheight/2);
      outline.addPoint(xpix,ypix);
      while(theta>-theta0){
        xpix=(int)(xPosition+x0-radius*Math.cos(theta)+offset);
        ypix=(int)(radius*Math.sin(theta)+iheight/2);
        outline.addPoint(xpix,ypix);
        theta-=delta;
      }
      //xpix=(int)(xPosition);
      xpix=(int)(xPosition+x0-radius*Math.cos(theta)+offset);
      ypix=(int)(radius*Math.sin(theta)+iheight/2+2);
      outline.addPoint(xpix,ypix);
  }


  public void paint(Graphics g,Rectangle r){
    if(r.height!=iheight) makeOutline(r.height);
    g.setColor(Color.black);
    if(R<0)g.fillRect(xPosition,0,r.width,r.height);
        else g.fillRect(outline.xpoints[0]-1,0,r.width,r.height);
    int blue=(int)Math.min(255,50*(0.2+indexOfRefraction+delN));
    if (indexOfRefraction+delN>1.0) g.setColor(new Color(0,0,blue));
      else g.setColor(Color.black);
    if (!(R<5 && R>-5)) g.fillPolygon(outline);
    int last=outline.npoints-1;
    int rheight=(int) Math.abs(outline.ypoints[last]-outline.ypoints[0])+1;
    if(R<0)g.fillRect(xPosition,outline.ypoints[last]-1,r.width,rheight);
      else if(R>0) g.fillRect(outline.xpoints[0]-1,outline.ypoints[last]-0,r.width,rheight-1);
        else g.fillRect(xPosition,0,r.width,r.height);
    if(color==null)g.setColor(Color.blue);
      else  g.setColor(color);
    g.drawLine(xPosition,0,xPosition,r.height);
    //if (!(R<5 && R>-5)) g.drawPolygon(outline);
    //g.drawLine(xPosition,(int)(r.height*(1-size)/2.0),xPosition,0);
    //g.drawLine(xPosition,(int)(r.height*(1+size)/2.0),xPosition,r.height);
    if(showFocus){
      g.setColor(Color.white);
      int hot=(int)Math.abs(R/delN);
      if(focalLength==0) hot=20;
      hot=Math.min(hot,r.width/2-5);
      g.fillOval(xPosition-hot-3,r.height/2-3,6,6);
      g.fillOval(xPosition+hot-3,r.height/2-3,6,6);
    }
  }

  public double[] transform(double[] v,Rectangle r,int direction){
    if(delN==0)return v;
    if(focalLength==0){  //  flat surface tag.  This is the code from IndexChange.
        double theta;
        double tempV = v[0]-r.height/2;
        theta=Math.atan(v[1]);
        errCode=0;
        if(direction==1){
            if( Math.abs(indexOfRefraction*Math.sin(theta)/(delN+indexOfRefraction))<=1 )
            theta=Math.asin(indexOfRefraction*Math.sin(theta)/(delN+indexOfRefraction));
            else{v[1]=-v[1]; errCode=2; return v;}
        }else{
            if((v[1]>0 && Math.sin(theta)*(delN+indexOfRefraction)<=1) || (v[1]<0 && Math.sin(theta)*(delN+indexOfRefraction)>=-1) || v[1]==0)
            theta=Math.asin(Math.sin(theta)*(delN+indexOfRefraction)/indexOfRefraction);
            else {v[1]=-v[1]; errCode=2; return v;}
        }
        v[1]=Math.tan(theta);
        return v;
    }
    double tempV = v[0]-r.height/2;
    v[0] = v[0]*mat[0][0] + v[1]*mat[0][1];
    v[1] = (direction*tempV*mat[1][0] + v[1]*mat[1][1]);
    return v;
  }

  public void setDelN(double dn){
    delN=dn;
    mat[1][1] = indexOfRefraction/(delN+indexOfRefraction);
  }

  public void paintActive(Graphics g,Rectangle r){
    if (!noDrag){
      g.setColor(Color.green);
      g.drawLine(xPosition,0,xPosition,r.height);
    }
    if(showFocus || resizable){
      g.setColor(Color.white);
      int hot=(int)Math.abs(R/delN);
      if(focalLength==0) hot=20;
      hot=Math.min(hot,r.width/2-5);
      g.fillOval(xPosition-hot-3,r.height/2-3,6,6);
      g.fillOval(xPosition+hot-3,r.height/2-3,6,6);
    }
    if (info){
      g.setColor(Color.white);
      g.drawString("x = "+df.format(1.0*xPosition/pixPerUnit),xPosition,r.height-70);
      if((delN+indexOfRefraction)>=1.0)g.drawString("n1 = "+df.format(indexOfRefraction)+"    n2="+df.format(delN+indexOfRefraction),xPosition,r.height-50);
      else g.drawString(bench.owner.label_index_error+" ",xPosition,r.height-50);
      g.drawString("R = "+df.format(1.0*R/pixPerUnit),xPosition,r.height-30);
    }
  }

  public int isInside(int mouseX,int mouseY,Rectangle r){
    if (!noDrag &&(mouseX>xPosition-10 && mouseX<xPosition+10))return 1;        //dragable
    int hot=(int)(R/delN);
    if(focalLength==0) hot=20;
    if(hot<0) hot=-Math.min(-hot,r.width/2-5);
      else hot=Math.min(hot,r.width/2-5);
    if (resizable &&
        mouseX>(xPosition+hot)-15 && mouseX<(xPosition+hot)+15 &&
        mouseY<(r.height/2+15) && mouseY>(r.height/2-15)
        ) return 2;               //positive R
    if (resizable &&
        mouseX>(xPosition-hot)-15 && mouseX<(xPosition-hot)+15 &&
        mouseY<(r.height/2+15) && mouseY>(r.height/2-15)
        ) return 3;               //negative R
    return 0;
  }

  void adjustPosition(){
     super.adjustPosition();
     makeOutline(iheight);
  }

  public void setY(double y){} // don't let the this thing move up or down.

  public void setPixX(int x,Rectangle r){
    super.setPixX(x,r);
    makeOutline(iheight);
  }


  public void setRadius(int rT,Rectangle r){
    if(rT!=R)makeOutline(iheight);
    if (rT>r.width-20) R = r.width-20;
      else if (-rT>r.width-20){
        R = -(r.width-20);
        focalLength = R/delN;
      }
      else {
        R = rT;
        focalLength = R/delN;;
      }
    if (R<10 && R>-10) {mat[1][0] = 0;  focalLength=0;} //flat interface (R=infinite)
      else mat[1][0] = -delN/R;
  }

  final public int getR(){
    return R;
  }

  final public double getFocalLength(){
    return focalLength;
  }

  final public String getType(){
    return "dielectric";
  }
}
