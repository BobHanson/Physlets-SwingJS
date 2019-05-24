package edu.davidson.display;

import java.awt.Color;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Polygon;

import edu.davidson.tools.*;

public class ComplexThing extends Thing {
    double[] xvec, revec, imvec;
    boolean centered=true;
    public ComplexThing(SApplet owner, SScalable sc){
      super(sc,0,0);
      s=1;
      w=0;
      h=0;
      applet=owner;
    }

    public void setCentered(boolean c){ centered=c;}

    public void addDatum(SDataSource ds, int sid, double x, double y ){
     System.out.println("Single data points not supported.  The data connection must provide a compelete data set.");
    }

    public void addData(SDataSource ds, int sid, double x[], double y[] ){
      if(x==null || y==null)return;
      int length=x.length;
      if(xvec==null || xvec.length != length){
          xvec=new double[length];
          revec=new double[length];
          imvec=new double[length];
          System.arraycopy(x,0,xvec,0,length);
      }
      if(sid==1){  // x values
         System.arraycopy(x,0,xvec,0,length);
      }else{
        System.arraycopy(y,0,imvec,0,length);
        System.arraycopy(x,0,revec,0,length);
        if(this.canvas instanceof Canvas) ((Canvas)canvas).repaint();
      }
    }

    public void paint(Graphics osg){
      if(xvec==null) return;
      int left=canvas.pixFromX(xvec[0]);
      int right=canvas.pixFromX(xvec[xvec.length-1]);
      if(canvas.getPixWidth() <= Math.abs(right-left))
        paint1(osg);
      else paint2(osg);
    }
    private void paint1(Graphics osg){
      double[] x,re,im;
      x=xvec; re=revec; im=imvec;
      if(!visible || x==null || re==null || im==null) return;
      int length=x.length;
      if(length!=re.length || length!=im.length)return;
      int y1Bottom=0, y1Top=0;
      int origin=canvas.pixFromY(0);
      for(int i=0; i<length; i++){
          if(canvas instanceof SGraph)
            osg.setColor( SGraph.colorFromRadians(Math.atan2(im[i],re[i])));
          else
              osg.setColor(Color.getHSBColor( (float)(1+Math.atan2(im[i],re[i])/Math.PI)/2, 1.0f, 1.0f ));
          double amp=Math.sqrt(re[i]*re[i]+im[i]*im[i]);
          if(centered){
            y1Bottom=canvas.pixFromY(-amp/2);
            y1Top=canvas.pixFromY(amp/2);
          }else{
            y1Bottom=origin;
            y1Top=canvas.pixFromY(amp);
          }
          int xpix=canvas.pixFromX(x[i]);
          osg.drawLine(xpix,y1Bottom,xpix,y1Top);
      }
    }

    private void paint2(Graphics osg){
      double[] x,re,im;
      x=xvec; re=revec; im=imvec;
      if(!visible || x==null || re==null || im==null) return;
      int length=x.length;
      if(length!=re.length || length!=im.length)return;

      int[] xpts= new int[5];
      int[] ypts= new int[5];
      Polygon shape=new Polygon(xpts, ypts, 5);

      int x0,x1,y0Top,y1Top,y0Bottom,y1Bottom;
      int offset=0;

      double amp=Math.sqrt(re[0]*re[0]+im[0]*im[0]);
      int origin=canvas.pixFromY(0);
      x0=canvas.pixFromX(x[0]);
      if(centered){
        y0Bottom=canvas.pixFromY(-amp/2);
        y0Top=canvas.pixFromY(amp/2);
      }else{
        y0Bottom=origin;
        y0Top=canvas.pixFromY(amp);
      }
      for(int i=1; i<length; i++){
          x1=canvas.pixFromX(x[i]);
          amp=Math.sqrt(re[i]*re[i]+im[i]*im[i]);
          if(canvas instanceof SGraph)
              osg.setColor( SGraph.colorFromRadians(Math.atan2(im[i],re[i])));
          else
              osg.setColor(Color.getHSBColor( (float)(1+Math.atan2(im[i],re[i])/Math.PI)/2, 1.0f, 1.0f ));
          if(centered){
            y1Bottom=canvas.pixFromY(-amp/2);
            y1Top=canvas.pixFromY(amp/2);
          }else{
            y1Bottom=origin;
            y1Top=canvas.pixFromY(amp);
          }
          shape.xpoints[0]=x0;
          shape.ypoints[0]=y0Top;
          shape.xpoints[1]=x1;
          shape.ypoints[1]=y1Top;
          shape.xpoints[2]= x1;
          shape.ypoints[2]=y1Bottom;
          shape.xpoints[3]= x0;
          shape.ypoints[3]=y0Bottom;
          shape.xpoints[4]= x0;
          shape.ypoints[4]=y0Top;

          osg.fillPolygon(shape);
          x0=x1;
          y0Top=y1Top;
          y0Bottom=y1Bottom;
      }
    }

    public void paintHighlight(Graphics osg){
      if(!visible) return;
      paint(osg);
    }

  public final boolean isInsideThing(int xPix, int yPix){
      return false;
  }

}
