package faraday;


import edu.davidson.tools.*;
import edu.davidson.display.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;


public class UWire extends Thing implements Fluxable{
  Schematic schematic;
  Meter meter=null;
  double area=0;
  double measurementTime=0;
  double flux=0;
  double volt=0;
  double lastTime=0;
  double lastFlux=0;
  boolean firstDatum=true;
  boolean secondDatum=false;
  boolean showCurrentArrow=true;
  boolean showMeter=true;
  int np=12;
  double[] readings= new double[np];

  public UWire(SApplet owner, Schematic sp, double x){
    super(sp,  x, 0) ;
    schematic=sp;
    meter=new Meter();
    color= new Color(64,64,64);
    w=3;
    h=30;
    int x1=(int)(schematic.iwidth*0.1)+xDisplayOff;
    int x2=canvas.pixFromX(x)+xDisplayOff;
    area=Math.abs(x2-x1)*(schematic.iheight*0.8)/schematic.pixPerUnit/schematic.pixPerUnit;
    varStrings= new String[]{"t","x","y","flux","v"};
    ds=new double[1][5];  // the datasource state variables
    doFluxIntegral();
  }

  public void setShowCurrentArrow(boolean show){
     showCurrentArrow=show;
  }

  public void setShowMeter(boolean show){
     showMeter=show;
  }


   public void paintCurrentArrow(Graphics g){
     if(!showCurrentArrow) return;
     int yinset=(int)(schematic.iheight*0.02)-yDisplayOff;
     if(volt==0)return;
     int ypix=yinset+3;
     int xpix1=schematic.iwidth/2-10;
     int xpix2=schematic.iwidth/2+30;
     g.drawLine(xpix1,ypix,xpix2,ypix);
     g.drawLine(xpix1,ypix+1,xpix2,ypix+1);
     if(volt<0){
         g.drawLine(xpix2,ypix,xpix2-5,ypix-5);
         g.drawLine(xpix2,ypix,xpix2-5,ypix+5);
         g.drawLine(xpix2,ypix+1,xpix2-5,ypix-4);
         g.drawLine(xpix2,ypix+1,xpix2-5,ypix+6);
     }else{
         g.drawLine(xpix1,ypix,xpix1+5,ypix-5);
         g.drawLine(xpix1,ypix,xpix1+5,ypix+5);
         g.drawLine(xpix1,ypix+1,xpix1+5,ypix-4);
         g.drawLine(xpix1,ypix+1,xpix1+5,ypix+6);
     }
     g.setColor(Color.darkGray);
     g.drawString(schematic.owner.label_current,schematic.iwidth/2-10,ypix+12);
     g.setColor(Color.black);
  }

  public void paintWire(Graphics g){
      //if(schematic.drag){paintDrag(g); return;}
      int x1=canvas.pixFromX(x)+xDisplayOff;
      int ptY=schematic.iheight/2-yDisplayOff;
      int y1=ptY-h/2;
      int y2=ptY+h/2;
      g.setColor(color);
      for(int i=-w; i<=w; i++) g.drawLine(x1+i,y1,x1+i,y2);
      g.setColor(new Color(255,128,128));
      g.fillOval(x1-w,y1-w/2,2*w,w);
      g.fillOval(x1-w,y2-w/2,2*w,w);
      g.setColor(Color.black);
      g.drawOval(x1-w,y1-w/2,2*w,w);
      g.drawOval(x1-w,y2-w/2,2*w,w);
 //     System.out.println("paint:"+x1);
  }

  public void paintU(Graphics g){
      //if(schematic.drag){paintDrag(g); return;}
      int x1=(int)(schematic.iwidth*0.1)+xDisplayOff;
      int x2=(int)(schematic.iwidth*0.9)+xDisplayOff;
      int ptY=schematic.iheight/2-yDisplayOff;
      int y1=ptY-(int)(schematic.iheight*0.8)/2;;
      int y2=ptY+(int)(schematic.iheight*0.8)/2;;
      g.setColor(Color.black);
      for(int i=-w; i<=w; i++) g.drawLine(x1+i,y1,x1+i,y2);

      for(int i=-w; i<=w; i++){
          g.drawLine(x1-w,y1+i,x2,y1+i);
          g.drawLine(x1-w,y2+i,x2,y2+i);
      }

 //     System.out.println("paint:"+x1);
  }

  public void paint(Graphics osg){
      if(!visible) return;
      h=(int)(schematic.iheight*0.9);
      paintU(osg);
      paintWire(osg);
      if(showMeter) meter.paint(osg);
      paintCurrentArrow(osg);
  }

  public final boolean isInsideThing(int xPix, int yPix){
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      if ((Math.abs(xPix-ptX)<w+1)&&(Math.abs(yPix-ptY)<h/2+1))  return true;
      else return false;
  }

  public void setXY(double x, double y){
     //double xmin=canvas.xFromPix((int)(schematic.iwidth*0.1+2*w)+xDisplayOff);
     double xmin=canvas.xFromPix((int)(schematic.iwidth*0.1)+xDisplayOff);
     double xmax=canvas.xFromPix((int)(schematic.iwidth*0.9)+xDisplayOff);
     x=Math.max(x,xmin);
     x=Math.min(x,xmax);
     super.setXY(x,y);
  }


  public void setX(double x){
     if( schematic.iwidth != schematic.getSize().width){
            super.setX(x);
            return;
     }
     double xmin=canvas.xFromPix((int)(schematic.iwidth*0.1+2*w)+xDisplayOff);
     double xmax=canvas.xFromPix((int)(schematic.iwidth*0.9)+xDisplayOff);
     x=Math.max(x,xmin);
     x=Math.min(x,xmax);
     super.setX(x);
  }

  public void reset(){
      measurementTime=schematic.time;
      lastTime=schematic.time;
      lastFlux=0;
      firstDatum=true;
      volt=0;
  }

  public double getFlux(){return flux;}
  public double getVolt(){return volt;}

  private double average(double v){
      if(firstDatum){
          lastTime=measurementTime; // save the time this reading was taken;
          lastFlux=flux;
          firstDatum=false;
          secondDatum=true;
          return 0;
      }else if(secondDatum){
          for(int i=0; i<np; i++)readings[i]=v;
          secondDatum=false;
          return v;
      }else{
          //for(int i=np-1; i>0; i--)readings[i]=readings[i-1]*(1.0-1.5/np);
          for(int i=np-1; i>0; i--)readings[i]=readings[i-1];
          readings[0]=v;
      }
      double avg=0;
      for(int i=0; i<np; i++){
          avg+=readings[i];
      }
      return avg/np;
  }
  /*
  private double average_old(double v){
      double tau=Math.exp(-0.5);  // relaxation
      if(firstDatum){
          lastTime=measurementTime; // save the time this reading was taken;
          lastFlux=flux;
          firstDatum=false;
          secondDatum=true;
          for(int i=0; i<np; i++)readings[i]=0;
          return 0;
      }else if(secondDatum){
          readings[0]=v;
          for(int i=1; i<np; i++){
              readings[i]=readings[i-1]*tau;
          }
          secondDatum=false;
      }else{
          for(int i=np-1; i>0; i--)readings[i]=readings[i-1]*tau;
          //for(int i=np-1; i>0; i--)readings[i]=readings[i-1];
          readings[0]=v;
      }
      double avg=0;
      for(int i=0; i<np; i++){
          avg+=readings[i];
      }
      return avg/np;
  }*/

  private void calcVoltage(){
      if(measurementTime==lastTime){
          lastFlux=flux;
          return ;
      }
      double v=-(flux-lastFlux)/(double)(measurementTime-lastTime);
      lastTime=measurementTime; // save the time this reading was taken;
      lastFlux=flux;
      volt=average(v);
      return;
  }

  public double doFluxIntegral(){
      int numPts=Math.max(w,200);
      int x1=(int)(schematic.iwidth*0.1); // position of the left wire.
      double left=schematic.xFromPix(x1);
      double dx=(x-left)/numPts;

      //int x1=(int)(schematic.iwidth*0.1)+xDisplayOff;
     // int x2=canvas.pixFromX(x)+xDisplayOff;
      double height=schematic.iheight*0.8/schematic.pixPerUnit;
      flux=0;
      for(int i=0;i<numPts ;i++){
         flux+= schematic.getFieldValue(left);
         left+=dx;
      }
      if(dx>0)flux=flux*dx*height; else flux=0;
      measurementTime=schematic.time;
      calcVoltage();
      return flux;
  }

  final public double[][] getVariables(){
     ds[0][0]=measurementTime;
     ds[0][1]=x;  //x
     ds[0][2]=y;  //y
     ds[0][3]=flux;  //flux
     ds[0][4]=volt;   //voltage
     return ds;
  }

  Format getFormat(){return format;};
  // inner class to take care of meter
 public class Meter {
  int r=20;
  double min=-200, max=200;
  int textOffset=0;
  double sqrt2=Math.sqrt(2);
  Color color=Color.black;
  Font bigFont=new Font("Dialog",Font.BOLD,18);


  public Meter() {
     min=schematic.metermin;
     max=schematic.metermax;
  }

  public void paint(Graphics g){
      int x1=(int)(schematic.iwidth*0.1);//+xDisplayOff;    // x1 and y1 posiiton  the center of the meter.
      int y1=schematic.iheight/2;//-yDisplayOff;
      int yo=y1+(int)(0.8*r);                            // pivot of the meter needle.
      int i=0;
      g.setColor(new Color(192,255,255));
      g.fillOval(x1-r,y1-r,2*r,2*r);
      g.setColor(Color.black);
      g.drawOval(x1-r,y1-r,2*r,2*r);
      int len=r-1;
      g.drawLine(x1,yo,x1-len,yo-len);
      g.drawLine(x1,yo,x1+len,yo-len);
      len=(int)(len*sqrt2-4);
      g.setColor(Color.red);
      double theta;
      if(volt<min) theta=-Math.PI/4;
      else if(volt>max) theta=Math.PI/4;
      else theta=-Math.PI/4+(Math.PI/2.0)*(volt-min)/(max-min);
      //theta=-theta;  // sign mistake!
      g.drawLine(x1,yo,x1+(int)(len*Math.sin(theta)),yo-(int)(len*Math.cos(theta)) );
      g.drawLine(x1-1,yo,x1+(int)(len*Math.sin(theta))-1,yo-(int)(len*Math.cos(theta)) );
      Format format=getFormat();
      String msg=""+format.form(volt);
      FontMetrics fm=g.getFontMetrics(g.getFont());
      int w=fm.stringWidth(msg)/2+5;
      g.setColor(Color.yellow);
      g.fillRect(x1-w+textOffset,yo+12,2*w,20);
      g.setColor(Color.black);
      w-=5;
      g.drawString(msg,x1-w+textOffset,yo+25);
      Font font=g.getFont();
      g.setFont(bigFont);
      g.drawString("+",x1-15,y1-r);
      g.setFont(font);
  }
 }// end of inner class

}
