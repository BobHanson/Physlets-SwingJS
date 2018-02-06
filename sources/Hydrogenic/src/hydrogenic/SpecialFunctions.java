
//Title:        Hydrogen Atom Probabilty Density
//Version:      
//Copyright:    Copyright (c) 1998
//Author:       Cabell Fisher, Jim Nolen, and Dr. Wolfgang Christian
//Description:


package hydrogenic;

public class SpecialFunctions {

  public SpecialFunctions() {
  }

  static double laguerre(int a, int b, double x)
      {
        double LiM2, LiM1, floati, Li=0;
        int i;
        if (b<1) return 1.0;
        if (b<2) return 1.0+a-x;
        if (b<3) return 1.0+0.5*a*(3.0+a)-x*(2.0+a-0.5*x);

        //our Laguerre algorithm that solves the Laguerre polynomial L[^2l+1,n-l-1](x)] 
        LiM2= 1.0+a-x;
        LiM1= 1.0+0.5*a*(3.0+a)-x*(2.0+a-0.5*x);

        for(i=3; i<=b; i++)
          {
            floati=i;
            Li= ((2.0*floati+a-1.00-x)*LiM1-(floati+a-1.0)*LiM2)/floati;
            LiM2=LiM1;
            LiM1=Li;
          }
          return Li;
      }

   static double factorial(int c)
        {
          double a=1.0;
          int p=1;
          int c2 =c;
                         //Calculates [n-l-1]! and returns it
          if(c<=20)
          {
          while(p<=c2)
                {
                a=a*c;
                c--;
                p++;
                }
          }
          else
              {        //Stirling approximation in case the [n-l-1]! gets to large

              a=Math.pow(c,c)*Math.exp(-c)*Math.sqrt(2*Math.PI*c);
              }
        return a;
        }

     //constants defined
    public static final double ln2 = 0.693147180560;
    public static final double EulerG = 0.577215664902;
    public static final double hlfLn2Pi = 0.918938533205;
    public static final double[] b = {
        0.0,0.0833333333333,-0.00277777777778,0.000793650793651,
        -0.000595238095238,0.000841750841751};


     /**
     * Funtion returns value of legendre polynomial (n,l) at x.
     *
     *@param l  int
     *@param m   int
     *@param x  double
     *@returns double
     */
     static double legendre(int l, int m, double x)
     {

     double xsq,oneMxsq,xsqrt,fm,SnM2m,SnM1m,tiM1,M1Root,Root;
     double Snm = 0.0;
     int nMm,m2,i;
     if (m<0) m=-m;
     if (m > l) return 0.0;
     if (l == 0) return 1.0;
     nMm = l-m;
     

     if (x<0)
        {
        x = -x;
        if (nMm > 2*(nMm/2)) return -legendre(l,m,x);
        return legendre(l,m,x);
        }

     //Now l >= m >0 and x >= 0;
     xsq = x*x;
     oneMxsq = 1.0 - xsq;
     xsqrt = Math.sqrt(oneMxsq);

     if (l == 1)
         {
         if (m == 0) return x;
         if (m == 1) return xsqrt;
         }

     if (l == 2)
         {
         if (m == 0) return 1.5*xsq-0.5;
         if (m == 1) return 3.0*x*xsqrt;
         if (m == 2) return 3.0*oneMxsq;
         }
     //Now l>2, so use iteration.
     m2 = m*m;
     fm = m;
     if (l == m)
       return Math.exp(logGamma(2.0*fm+1)-fm*ln2-logGamma(fm+1))*Math.pow(xsqrt,fm);
     SnM2m = 0.0;
     SnM1m = Math.exp(0.5*logGamma(2.0*fm+1)-fm*ln2-logGamma(fm+1))*Math.pow(xsqrt,fm);
     tiM1 = 2*m+1;
     M1Root = 0.0;
     Root = Math.sqrt(tiM1);

     for (i = m+1; i <= l; i++)
       {
       Snm = (tiM1*x*SnM1m - M1Root*SnM2m)/Root;
       tiM1 += 2.0;
       M1Root = Root;
       Root = Math.sqrt((double)((i+1)*(i+1)-m2));
       SnM2m = SnM1m;
       SnM1m = Snm;
       }
     return (Math.exp(0.5*(logGamma((double)(l+m+1))-logGamma((double)(l-m+1))))*Snm);
     }


  static double logGamma(double x)
     {
     //log of gamma function by asymptotic series.
     double Recxs,sum,term,x10;
     int xInt,k,x11;

     if (x > 10.0)
       {
       //use asymptotic series.
       Recxs = 1/(x*x);
       term = x;
       sum = (x-0.5)*Math.log(x)-x+hlfLn2Pi;
       for (k = 1; k <= 5; k++)
         {
         term *= Recxs;
         sum += b[k]*term;
         }
       return sum;
       }

     if (x > 0)
       {
       //recurrence to x>10.
       x -= 1;
       x11 = (int)(11-x);
       x10 = x+(double)x11;
       xInt = (int)x;
       sum = 0;
       for (k = 1; k <= x11-1; k++)
         {
         sum -= Math.log(x10-(double)k);
         }
       return sum+logGamma(x10);
       }

     if (x == 0) return 0;
     return 0;
     }

  public static void main(String[] args) {
    new SpecialFunctions();
  }
} 