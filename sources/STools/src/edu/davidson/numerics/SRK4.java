package edu.davidson.numerics;

public class SRK4 extends SODE{
   double h=0.01;
   int numEqu=0;
   double[] dydx,xTemp,k1,k2,k3,k4;
   //Parser potFunc=null;
   SDifferentiable equations;
   double tol=h*1.0e-6;

    public SRK4(){}

    public int step(double dx, double x[]) {
        if(dx==0)return 0;
        if(x.length<numEqu){ // check to make sure that the arrarys are large enough
            numEqu=x.length;
            xTemp=new double[numEqu];
            k1=new double[numEqu];
            k2=new double[numEqu];
            k3=new double[numEqu];
            k4=new double[numEqu];
            dydx= new double[numEqu];
            System.out.println("Warning:  Temporary arrays reset.");
        }
        int numSteps= (int) Math.abs(dx/h);
        for(int i=0; i<numSteps ; i++) stepRK4(h, x);
        if(dx-numSteps*h>tol){
            stepRK4(dx-numSteps*h,  x);  // step by the remaining time
            numSteps++;
        }
        return numSteps;
    }

    public double stepODE(double dx,  double x[]){
        if(x.length<numEqu){ // check to make sure that the arrarys are large enough
          System.out.println("Error:  The temporary arrays are not large enough.");
          return 0;
        }
        stepRK4( dx,x);
        return dx;
    }

    private void stepRK4(double dx,  double x[]){
        int i; // counter
        double h=dx, h2=0.5*dx, h6=dx/6.0;

        dydx=equations.rate(x);
        for(i=0;i<numEqu; i++) {k1[i]=dydx[i]; xTemp[i]=x[i]+h2*k1[i];}
        dydx=equations.rate(xTemp);
        for(i=0;i<numEqu; i++) {k2[i]=dydx[i];}
        for(i=0;i<numEqu; i++) {xTemp[i]=x[i]+h2*k2[i];}
        dydx=equations.rate(xTemp);
        for(i=0;i<numEqu; i++) {k3[i]=dydx[i];}
        for(i=0;i<numEqu; i++) {xTemp[i]=x[i]+h*k3[i];}
        dydx=equations.rate(xTemp);
        for(i=0;i<numEqu; i++) {k4[i]=dydx[i]; }
        for(i=0;i<numEqu; i++) {x[i]=x[i]+h6*(k1[i]+2*k2[i]+2*k3[i]+k4[i]);}
    }

    public final void setDifferentials(SDifferentiable d){
        equations=d;
        numEqu=equations.getNumEqu();
        xTemp=new double[numEqu];
        k1=new double[numEqu];
        k2=new double[numEqu];
        k3=new double[numEqu];
        k4=new double[numEqu];
        dydx= new double[numEqu];
    }

    public final void setNumberOfEquations(int n){
        numEqu=n;
        xTemp=new double[numEqu];
        k1=new double[numEqu];
        k2=new double[numEqu];
        k3=new double[numEqu];
        k4=new double[numEqu];
        dydx= new double[numEqu];
    }

    public final SDifferentiable getDiffernetials(){return equations;}

    public double getH(){return h;}
    public void   setH(double h){this.h=h;tol=h*1.0e-6;}
    public void setTol(double parm1) {;} // tolerance is not used in the Eurler method.
    public double getTol() {return tol;}
}

