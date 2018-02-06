////////////////////////////////////////////////////////////////////////////////
//	Euler.java
//	Wolfgang Christian
package edu.davidson.numerics;
/**
 * Euler solves a system of ordinary differential equations using the Euler method.
 *
 * The Euler method is unstable.  This class is designed to show how to use edu.davidson.numerics to solve ODEs.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 1.0, Date: 2000/10/28
 */
public class Euler extends SODE {
    double[]   dydx;
    SDifferentiable equations;
    double h=0.01;
    int numEqu=0;
    double tol=h*1.0e-6;

    public Euler() {
    }

    public int step(double dx, double x[]) {
        if(x.length<numEqu){
            numEqu=x.length;
            dydx=new double[numEqu];
            System.out.println("Warning:  Temporary arrays reset.");
        }
        int numSteps= (int) Math.abs(dx/h);
        for(int i=0; i<numSteps ; i++) eulerStep(h, x);
        if(dx-numSteps*h>tol){
            eulerStep(dx-numSteps*h,  x);  // step by the remaining time
            numSteps++;
        }
        return numSteps;

    }

    public double stepODE(double dx,  double x[]){
            if(x.length<numEqu){ // check to make sure that the arrarys are large enough
              System.out.println("Error:  The temporary arrays are not large enough.");
              return 0;
            }
            eulerStep( dx,x);
            return dx;
    }

    private void eulerStep(double stepSize, double x[]) {
            dydx=equations.rate(x);
            for(int i=0;i<numEqu; i++) {x[i]=x[i]+stepSize*dydx[i];}
    }

    public void   setTol(double t){this.tol=t;}
    public double getTol() {return tol;}

    public void setDifferentials(SDifferentiable diff) {
            equations=diff;
            numEqu=equations.getNumEqu();
            dydx=   new double[numEqu];
    }

    public double getH() { return h;}
    public void setH(double h) {this.h=h;tol=h*1.0e-6;}

    public final void setNumberOfEquations(int n){
        numEqu=n;
        dydx=new double[numEqu];
    }

}