package bfield;
import java.awt.*;

public final class Coil extends Wire {
    static int n=6;    // number of segments on 1/2 circle
    static double theta=Math.PI/n;
    int ptY1=0;
    int ptY2=0;
    private double[] lastBReading=new double[2];
    private double[] lastPosition=new double[2];

    public Coil(FieldPanel p, double x,double y, double c) {
      super( p,x,y,c);
      s=10;
      radius=0.5;
      color=null;
      showF=false;
      showFComponents=false;
    }

    double[] getB(double[] h){
      // calculate the B field at the point z1,y1 for a coil.
      // Biot-Savart law
      double x1=h[0];
      double y1=h[1];
      double phi=theta/2;
      double dl=radius*Math.sqrt(2*(1-Math.cos(theta)));
      double bx=0, by=0;
      double dy,dz;
      double rx,ry,rz,r2,r3;

      for(int i=0; i<n; i++){
          dy=-Math.sin(phi);   // components perpendicular to the radius of the coil in y-z plane
          dz= Math.cos(phi);
          rx=x1-x+xo;            // distance component from dl to the field point
          ry=y1-y-radius*Math.cos(phi)+yo;
          rz=-radius*Math.sin(phi);
          r2=rx*rx+ry*ry+rz*rz;  // distance squared from dl to the field point
          r3=Math.sqrt(r2)*r2;
          bx+=rz*dy/r3-ry*dz/r3;
          by+=rx*dz/r3;
          phi+=theta;
      }
      h[0]=2*current*dl*bx;  // factor of 2 because we used half circle
      h[1]=2*current*dl*by;
      return h;
    }

    double getWireBx(double x1, double y1){
      lastPosition[0]=x1;
      lastPosition[1]=y1;
      lastBReading= getB(lastPosition);
      return  lastBReading[0];
    }

    double getWireBy(double x1, double y1){
      lastPosition[0]=x1;
      lastPosition[1]=y1;
      lastBReading= getB(lastPosition);
      return  lastBReading[1];
    }

    public final void paint(Graphics osg){
      if ( !isVisible() ) return;
      xPix=p.pixFromX(x)+xDisplayOff;
      ptY1=p.pixFromY(y-radius)-yDisplayOff;
      ptY2=p.pixFromY(y+radius)-yDisplayOff;
      yPix=(ptY1+ptY2)/2-yDisplayOff;
      osg.setColor(new Color(192,128,192));
      //osg.fillRect((xPix-s+1),(ptY2),2*s-2,ptY1-ptY2);
      osg.fillRect((xPix-s/2+1),(ptY2),s-2,ptY1-ptY2);
      if(color!=null) osg.setColor(color);
          else if(current>0)osg.setColor(Color.blue);
              else osg.setColor(Color.red);
      //osg.fillOval((xPix-s),(ptY1-s),2*s,2*s);
      osg.fillOval((xPix-s/2),(ptY1-s/2),s,s);
      if(color!=null) osg.setColor(color);
          else if(current>0)osg.setColor(Color.red);
              else osg.setColor(Color.blue);
      //osg.fillOval((xPix-s),(ptY2-s),2*s,2*s);
      osg.fillOval((xPix-s/2),(ptY2-s/2),s,s);
      if(label!=null){
          Font oldFont=osg.getFont();
          osg.setFont(font); // this font is used for messages and captions.
          osg.setColor(Color.white);
          osg.drawString(label, xPix-4, yPix+5);
          osg.setColor(Color.black);
          osg.setFont(oldFont);
      }
    }

    void setShowF(boolean sf){;} // forces are not calculated on coils
    void setShowFComponents(boolean sfc){;}

    boolean isInsidePix(int x, int y){
      if (!noDrag &&(Math.abs(xPix-x)<s+1)&&(Math.abs(yPix-y)<s+1) )  return true;
      if (!noOptionDrag && (Math.abs(xPix-x)<s+1)&&((Math.abs(ptY2-y)<s+1)|| (Math.abs(ptY1-y)<s+1)))  return true;
      else return false;
    }

    boolean isInsideWire(double x, double y, int hs){
      int xpix=p.pixFromX(x);
      int ypix=p.pixFromY(y);
      if(getHotSpot(xpix, ypix)!=hs) return false;
      return isInsidePix(xpix,ypix);
    }

    public boolean isInsideThing(int i, int j){return isInsidePix(i,j);}

    final int getHotSpot(int x, int y){
      if(!noOptionDrag && Math.abs(ptY2-y)<s+1)  return 1; //top
      else if(!noOptionDrag && Math.abs(ptY1-y)<s+1)  return -1; // bottom
      else return 0;  //center
    }

}