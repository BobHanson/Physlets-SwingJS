package faraday;

//import java.awt.*;
import edu.davidson.display.BoxThing;
import edu.davidson.tools.*;

import java.awt.Graphics;


public class FluxBox extends BoxThing implements Fluxable{
  Schematic schematic;
  double measurementTime=0;
  double flux=0;
  double volt=0;
  double lastTime=0;
  double lastFlux=0;
  boolean firstDatum=true;
  boolean secondDatum=false;
  boolean showCurrentArrow=true;
  int np=5;
  double[] readings= new double[np];

  public FluxBox(SApplet owner, Schematic sp, double x,double y,int width,int height){
    super( owner, sp,  x, y, width, height) ;
    schematic=sp;
    varStrings= new String[]{"t","x","y","flux","v"};
    ds=new double[1][5];  // the datasource state variables
    doFluxIntegral();

  }

  public void setShowCurrentArrow(boolean show){
     showCurrentArrow=show;
  }

  public void reset(){
      measurementTime=schematic.time;
      lastTime=schematic.time;
      lastFlux=0;
      firstDatum=true;
      volt=0;
  }

  public void paint(Graphics g) {
      super.paint(g);
      paintCurrentArrow(g);
  }
  public void paintCurrentArrow(Graphics g){
     if(!showCurrentArrow) return;
     if(volt==0)return;
     int ypix=canvas.pixFromY(y)-h/2-yDisplayOff-15;
     int xpix=canvas.pixFromX(x)+yDisplayOff;
     int xpix1=xpix-10;
     int xpix2=xpix+30;
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
     g.drawString(schematic.owner.label_current,xpix-10,ypix+15);
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

  private void calcVoltage(){
      if(measurementTime==lastTime) return ;
      double v=-(flux-lastFlux)/(double)(measurementTime-lastTime);
      lastTime=measurementTime; // save the time this reading was taken;
      lastFlux=flux;
      volt=average(v);
      return;
  }

  public double doFluxIntegral(){
      int numPts=Math.max(w,100);
      double dx=(schematic.xFromPix(w)-schematic.xFromPix(0));
      double left=x-dx/2;
      dx/=numPts;
      flux=0;
      for(int i=0;i<numPts ;i++){
         flux+= schematic.getFieldValue(left);
         left+=dx;
      }
      flux=flux*dx*h/schematic.pixPerUnit;;
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

}
