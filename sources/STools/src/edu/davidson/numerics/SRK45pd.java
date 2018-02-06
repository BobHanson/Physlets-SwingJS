package edu.davidson.numerics;

public final class SRK45pd extends SODE{
       //static double[]   a={1,2.0/9.0, 1.0/3.0, 3.0/4.0, 1.0 , 5.0/6.0};
       static double[][] b={{1.0/5.0},
                            {3.0/40.0, 9.0/40.0},
                            {3.0/10.0, -9.0/10.0, 6.0/5.0},
                            {226.0/729.0,-25.0/27.0, 880.0/729.0, 55.0/729.0},
                            {-181.0/270.0,5.0/2.0,-266.0/297.0,-91.0/27.0, 189.0/55.0}};
       static double[]   ch={19.0/216.0, 0.0, 1000.0/2079.0, -125.0/216.0, 81.0/88.0, 5.0/56.0};
       static double[]   ct={31.0/540.0-19.0/216.0,
                             0.0,
                             190.0/297.0-1000.0/2079.0,
                             -145.0/108.0+125.0/216.0,
                             351.0/220.0-81.0/88.0,
                             1.0/20.0-5.0/56.0};
       double h=0.01;
       int numEqu=0;
       double[]   dydx,xTemp;
       double[][] f;
       double[]   truncErr;
       SDifferentiable equations;
       double err;
       double tol=1.0e-7;
       double hmin=1.0e-6;

       public SRK45pd(){
        }

       public double getTol(){return tol;}
       public void   setTol(double t){this.tol=t; hmin=tol*10;}

       public double getH(){return h;}
       public void   setH(double h){this.h=h;}

       private int stepBack(double dx, double x[]){
           int count=0;
           double oldH;
           h=-Math.abs(h);
           while(dx<0){
             if (h<dx){  // this should be the last step;
               oldH=h;
               h=dx;
               dx=dx-stepRK45pd(x);
               h=oldH;
               count++;
             }
             else{
                 dx=dx-stepRK45pd(x);
                 count++;
             }
           }
           System.out.println("count:"+ count+"  h:"+h);
           h=+Math.abs(h);
           return count;
       }

       public int step(double dx, double x[]){
           if(dx==0)return 0;
           if(x.length<numEqu){ // check to make sure that the arrarys are large enough
                numEqu= x.length;
                xTemp=    new double[numEqu];
                dydx=     new double[numEqu];
                truncErr= new double[numEqu];
                f=        new double[6][numEqu];
                System.out.println("Warning:  Temporary arrays reset.");
           }
           if(dx<0){ return stepBack(dx, x);}
           int count=0;
           double oldH;
           while(dx>0){
             if (h>dx){
               oldH=h;
               h=dx;  // this should be last step;
               dx=dx-stepRK45pd(x);
               h=oldH;
               count++;
             }
             else{
                 dx=dx-stepRK45pd(x);
                 count++;
             }
           }
           System.out.println("count:"+ count+"  h:"+h);
           return count;
       }

       public double stepODE(double dx,  double x[]){
            if(x.length<numEqu){ // check to make sure that the arrarys are large enough
              System.out.println("Error:  The temporary arrays are not large enough.");
              return 0;
            }
            h=dx;
            return stepRK45pd(x);
        }

       private double stepRK45pd(double x[]){
            int i,j,k; // counters
            double h_did=h;
            dydx=equations.rate(x);
            for(i=0;i<numEqu; i++){
                f[0][i]=dydx[i];
                xTemp[i]=x[i];
            }
            err=2*tol; // make sure this executes once.
            if (h>=0 && h<=hmin) h=1.1*hmin;
            else if (h<0 && -h<=hmin) h=-1.1*hmin;
            while(err>tol && Math.abs(h)>hmin){
                for(k=1;k<6; k++){
                    for(i=0;i<numEqu; i++){
                        x[i]=xTemp[i];
                        for(j=0;j<k; j++)
                            x[i]=x[i]+h*b[k-1][j]*f[j][i];    // k is offset by -1 from Danby
                    }
                    dydx=equations.rate(x);
                    for(i=0;i<numEqu; i++)f[k][i]=dydx[i];
                }
                for(i=0;i<numEqu; i++){
                    x[i]=xTemp[i];
                    truncErr[i]=0;
                    for(k=0;k<6; k++){
                        x[i]=x[i]+h*ch[k]*f[k][i];
                        truncErr[i]=truncErr[i]+h*ct[k]*f[k][i];
                    }
                    truncErr[i]=Math.abs(truncErr[i]);
                }
                err=0;
                for(i=0;i<numEqu; i++){
                   if(err<truncErr[i]) err=truncErr[i];
                }
                h_did=h;
                if(err==0.0)err=tol/1.0e5;
                // find h step for the next try.
                if(err>tol)  // shrink
                   h=0.9*h*Math.pow( tol/err, 0.25);
                else  //grow
                   h=0.9*h*Math.pow( tol/err, 0.2);
                if (h>=0 && h<hmin)h=hmin;  // set a minimum size for h
                else if (h<0 && -h<hmin)h=-hmin;
            }
            return h_did;
        }

        public final void setDifferentials(SDifferentiable d){
            equations=d;
            numEqu=equations.getNumEqu();
            xTemp=    new double[numEqu];
            dydx=     new double[numEqu];
            truncErr= new double[numEqu];
            f=        new double[6][numEqu];
        }
        public final SDifferentiable getDiffernetials(){return equations;}

        public final void setNumberOfEquations(int n){
            numEqu=n;
            xTemp=    new double[numEqu];
            dydx=     new double[numEqu];
            truncErr= new double[numEqu];
            f=        new double[6][numEqu];
        }
}

