package qTime;
import a2s.*;
//import java.awt.*;
import edu.davidson.numerics.Parser;
import edu.davidson.tools.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;




public final class QTimeState implements  edu.davidson.tools.SDataSource{
    String[] varStrings= new String[]{"x","v","re","im","dre","dim"};
    double[][] ds=new double[1][6];
    Parser parser;
    private double norm=1;
    private double energy=0;
    private double x[], v[],re[],im[],dre[],dim[];
    private double rei[],imi[];  // initial wave packet
    private Color[] colors=new Color[101];
    private int numPts;
    private double minV=0, maxV=0;
    double time=0;
    double x0;
    double x1;
    int startPix, endPix;
    double dx=1, dt=1, gamma=1;
    double courant=0.35;  // stability condition;
    SApplet owner;



    public QTimeState(double x0_,double x1_, int numPts_, SApplet o){
        owner=o;
        parser = new Parser(1);  // create a parser with 1 variable
	      parser.defineVariable(1,"x"); // define the variable
        initColors();
        numPts=numPts_;
        x  = new double[numPts];
        v  = new double[numPts];
        re = new double[numPts];
        im = new double[numPts];
        dre = new double[numPts];
        dim = new double[numPts];
        rei = new double[numPts];
        imi = new double[numPts];
        ds=new double[numPts][6];
        x0=x0_;
        x1=x1_;
        dx=(x1-x0)/(numPts-1);
        dt=dx*dx*courant;
        gamma=dt/2/dx/dx;
        for(int i=0; i<numPts; i++)
        {
          rei[i]=0; imi[i]=0;
          re[i]=0; im[i]=0;
          x[i]=x0+dx*i;
          v[i]=0;
        }
        minV=0; maxV=0;
        SApplet.addDataSource(this);
    }

    public final void step(double stepTime){
      if(stepTime<0)stepMinus(stepTime);
        else stepPlus(stepTime);
    }


    final void stepPlus(double stepTime){
       long maxCount=20000;
       double localTime=0;
       double localStep=dt;
       long count=0;
       gamma=localStep/2/dx/dx;
       //System.out.println("clock dt="+stepTime);
       //System.out.println("Visscher ="+dt);
       //System.out.println("steps="+(stepTime/dt));
       if(Math.abs(stepTime/dt)>maxCount){
         System.out.println("QTime time-step is too large.");
       }
       while((localTime<stepTime)&&(count<maxCount)){
         if(stepTime-localTime-localStep<0){
           localStep = stepTime - localTime;
           gamma=localStep/2/dx/dx;
           count=maxCount;
         }
         stepRe(localStep);
         stepIm(localStep);
         setBoundary();
         localTime=localTime+localStep;
         count++; // this sould never happen for reasonable dt
       }
       time=time+localTime;
    }

    final void stepMinus(double stepTime) {
      long maxCount = 20000;
      double localTime = 0;
      double localStep = -dt;
      long count = 0;
      gamma=localStep/2/dx/dx;
      //System.out.println("clock dt="+stepTime);
      //System.out.println("Visscher ="+dt);
      //System.out.println("steps="+(stepTime/dt));
      if (Math.abs(stepTime / dt) > maxCount) {
        System.out.println("QTime time-step is too large.");
      }
      while ( (localTime > stepTime) && (count < maxCount)) {
        if (stepTime - localTime - localStep > 0) {
          localStep = stepTime - localTime;
          gamma=localStep/2/dx/dx;
          count = maxCount;
        }
        stepRe(localStep);
        stepIm(localStep);
        setBoundary();
        localTime = localTime + localStep;
        count++; // this sould never happen for reasonable dt
      }
      time = time + localTime;
    }



    private final void stepRe(double dt)
    {
       for(int i=1; i<numPts-1; i++)
       {
          re[i]=re[i]-gamma*(im[i+1]+im[i-1]-2*im[i])+ dt*v[i]*im[i];
       }
    }

    private final void stepIm(double dt)
    {

       for(int i=1; i<numPts-1; i++)
       {
          im[i]=im[i]+gamma*(re[i+1]+re[i-1]-2*re[i])- dt*v[i]*re[i];
       }
    }

    private void setBoundary()
    {
        re[0]=0; im[0]=0;
        re[numPts-1]=0; im[numPts-1]=0;
    }


    public void reset(){
       time=0;
       calcNorm();
       calcEnergy();
       for(int i=0; i<numPts; i++){
          re[i]=rei[i]; im[i]=imi[i];
       }
    }
    public void setSpace(double x0_, double x1_)
    {
        x0=x0_;
        x1=x1_;
        dx=(x1-x0)/(numPts-1);
        dt=dx*dx*courant;
        gamma=dt/2/dx/dx;
        for(int i=0; i<numPts; i++)
        {
          rei[i]=0; imi[i]=0;
          re[i]=0; im[i]=0;
          x[i]=x0+dx*i;
          v[i]=0;
        }
    }

    public boolean setPotential(String potStr)
    {
        double y=0;
        boolean error = false;
        int i;
        parser.define(potStr);
        parser.parse();
        for(i=0; i<numPts; i++)
        {
             try
             {
                  y=parser.evaluate(x[i]);
                  if(y==y)  // this checks for NAN on Cafe compiler.
                    v[i] = y;
                  else
                    {System.out.println("Divide by zero!");
                     y=parser.evaluate(x[i]+1.0E-32);
                     if(y==y)v[i] = y;
                      else  v[i] = 0;
                    }
             } catch(Exception e) { error = true; }
             v[i]=v[i];
             if (i==0) {minV=v[0]; maxV=v[0];}
               else
               {
                  if(v[i]<minV) minV=v[i];
                  if(v[i]>maxV) maxV=v[i];
               }
         }
        dt=dx*dx;
        for(i=0; i<numPts; i++)
        {
            if((v[i]>0)&&(dt>1/(v[i]/2+1/dx/dx))) dt=1/(v[i]/2+1/dx/dx);
        }
        for(i=0; i<numPts; i++)
        {
            if((v[i]<0)&&(dt>-2/v[i])) dt=-2/v[i];
        }
        dt=dt*courant;// courant safety factor for stability;
        gamma=dt/2/dx/dx;
        reset();
        return error;
    }

    public final double getMinV()
      { return minV;}
    public final double getMaxV()
      {return maxV;}

    public boolean setReal(String reStr)
    {
        double y=0;
        boolean error = false;
        parser.define(reStr);
        parser.parse();
        for(int i=0; i<numPts; i++)
        {
             try
             {
                  y=parser.evaluate(x[i]);
                  if(y==y)  // this checks for NAN on Cafe compiler.
                    rei[i] = y;
                  else
                    {System.out.println("Divide by zero!");
                     y=parser.evaluate(x[i]+1.0E-32);
                     if(y==y)rei[i] = y;
                      else  rei[i] = 0;
                    }
             } catch(Exception e) { error = true; }
         }
         reset();
         return error;
    }

    public boolean setImaginary(String imStr)
    {
        double y=0;
        boolean error = false;
        parser.define(imStr);
        parser.parse();
        for(int i=0; i<numPts; i++)
        {
             try
             {
                  y=parser.evaluate(x[i]);
                  if(y==y)  // this checks for NAN on Cafe compiler.
                    imi[i] = y;
                  else
                    {System.out.println("Divide by zero!");
                     y=parser.evaluate(x[i]+1.0E-32);
                     if(y==y)imi[i] = y;
                      else  imi[i] = 0;
                    }
             } catch(Exception e) { error = true; }
         }
         reset();
         return error;
    }

    public void setXScale(int sp, int ep)
    {
        startPix=sp;
        endPix= ep;
    }

    public void setPolyReal(int iheight,int basePix, Polygon polyline)
    {
      if (numPts==0) return;
      if (polyline.npoints==0) return;
      int offset;
      for(int i=startPix; i<=endPix; i++)
      {
        offset=(int)((numPts-1)*(i-startPix)/(endPix-startPix));
        polyline.ypoints[i]=(int)(basePix+ re[offset]*iheight/2);
      }
    }

    public void setPolyImaginary(int iheight,int basePix, Polygon polyline)
    {
      if (numPts==0) return;
      int offset;
      for(int i=startPix; i<=endPix; i++)
      {
        offset=(int)((numPts-1)*(i-startPix)/(endPix-startPix));
        polyline.ypoints[i]=(int)(basePix+ im[offset]*iheight/2);
      }
    }
    public void setPolyProb(int iheight,int basePix, Polygon polyline)
    {
      if (numPts==0) return;
      if (polyline.npoints==0) return;
      int offset;
      double amp;
      for(int i=startPix; i<=endPix; i++)
      {
        offset=(int)((numPts-1)*(i-startPix)/(endPix-startPix));
        amp=-Math.sqrt(re[offset]*re[offset]+im[offset]*im[offset]);
        polyline.ypoints[i]=(int)(basePix+ amp*iheight/2);
      }
    }

    public void setPolyProbNeg(int iheight,int basePix, Polygon polyline)
    {
      if (numPts==0) return;
      if (polyline.npoints==0) return;
      int offset;
      double amp;
      for(int i=startPix; i<=endPix; i++)
      {
        offset=(int)((numPts-1)*(i-startPix)/(endPix-startPix));
        amp=Math.sqrt(re[offset]*re[offset]+im[offset]*im[offset]);
        polyline.ypoints[i]=(int)(basePix+ amp*iheight/2);
      }
    }

    public void fillPolyProb(int iheight,int basePix, Polygon polyline, Graphics g)
    {
      if (numPts==0) return;
      if (polyline.npoints==0) return;
      int[] xpts= new int[5];
      int[] ypts= new int[5];
      Polygon shape=new Polygon(xpts, ypts, 5);
      int x0,x1,y0,y1,y0Neg,y1Neg;
      int offset=0;
      double amp=Math.sqrt(re[0]*re[0]+im[0]*im[0])/2;
      if(startPix<0 || startPix>polyline.xpoints.length-1) return;
      if(endPix<0 || endPix>polyline.xpoints.length-1) return;
      x0=polyline.xpoints[startPix];
      y0=(int)(basePix+ amp*iheight/2);
      y0Neg=(int)(basePix- amp*iheight/2);
      for(int i=startPix+1; i<endPix; i++){
        offset=(int)((numPts-1)*(i-startPix)/(endPix-startPix));
        amp=Math.sqrt(re[offset]*re[offset]+im[offset]*im[offset])/2;
        x1=polyline.xpoints[i];
        y1=(int)(basePix+ amp*iheight/2);
        y1Neg=(int)(basePix- amp*iheight/2);

        shape.xpoints[0]=x0;
        shape.ypoints[0]=y0;
        shape.xpoints[1]=x1;
        shape.ypoints[1]=y1;
        shape.xpoints[2]= x1;
        shape.ypoints[2]=y1Neg;
        shape.xpoints[3]= x0;
        shape.ypoints[3]=y0Neg;
        shape.xpoints[4]= x0;
        shape.ypoints[4]=y0;
        g.setColor(colorFromPhase(Math.atan2(im[offset],re[offset])));
        g.fillPolygon(shape);
        x0=x1;
        y0=y1;
        y0Neg=y1Neg;
      }
    }

    private void calcNorm()
    {
        double sum=0;
        for (int i=0; i<numPts; i++)
        {
            sum+= re[i]*re[i]+im[i]*im[i];
        }
        if (sum>0) norm=Math.sqrt(sum*dx); else norm=1;
    }

    private void calcEnergy()
    {
        double HR=0; double HI=0;
        double sumRHR=0, sumIHI=0, sumIHR=0, sumRHI=0;
        for (int i=1; i<numPts-1; i++)
        {
            HR=-(re[i+1]+re[i-1]-2*re[i])/2/dx/dx+v[i]*re[i];
            HI=-(im[i+1]+im[i-1]-2*im[i])/2/dx/dx+v[i]*im[i];
            sumRHR+=re[i]*HR;
            sumIHI+=im[i]*HI;
            sumIHR+=im[i]*HR;
            sumRHI+=re[i]*HI;
        }
        energy=(sumRHR+sumIHI)*dx/norm/norm;
    }

    public double getEnergy()
    {
        return energy;
    }

    public double getPsiAtX(double x1)
    {
       int i=0;
       if (x1<x[0]||x1>x[numPts-1]) return 0;
       while(x1>x[i]) i++;
       return Math.sqrt(re[i]*re[i]+im[i]*im[i])/norm;
    }

    public int getAngleAtX(double x1){
       int i=0;
       if (x1<x[0]||x1>x[numPts-1]) return 0;
       while(x1>x[i]) i++;
       if(im[i]==0 && re[i]==0) return 0;
       return (int)(180*Math.atan2(im[i],re[i])/Math.PI);
    }

    private Color colorFromPhase(double phi){
        int offset=(int)(50+50*phi/Math.PI); // get offset in range 0 .. 100
        return colors[offset];
    }

    private void initColors()
    {
        int r,g,b;
        double pi=Math.PI;
        for(int i=0; i<101; i++)
        {
            b=(int)(255*Math.sin(pi*i/100)*Math.sin(pi*i/100));
            g=(int)(255*Math.sin(pi*i/100+pi/3)*Math.sin(pi*i/100+pi/3));
            r=(int)(255*Math.sin(pi*i/100+2*pi/3)*Math.sin(pi*i/100+2*pi/3));
            colors[i]=new Color(r,g,b);
        }
    }

  public String[]   getVarStrings(){return varStrings;}
  public int getID(){return hashCode();}
  public void setOwner(SApplet applet){;}
  public SApplet getOwner(){return owner;}    //usually owner is an SApplet.

  public double[][] getVariables(){
     edu.davidson.numerics.Util.numericDerivative(dx,re, dre);
     edu.davidson.numerics.Util.numericDerivative(dx,im, dim);
     for(int i=0; i<numPts; i++){
          ds[i][0]=x[i];
          ds[i][1]=v[i];
          ds[i][2]=re[i]/norm;
          ds[i][3]=im[i]/norm;
          ds[i][4]=dre[i]/norm;
          ds[i][5]=dim[i]/norm;
    }
    return ds;
  }
}