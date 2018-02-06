
/**
 * Title:        Your Product Name<p>
 * Description:  Your description<p>
 * Copyright:    Copyright (c) 1998<p>
 * Company:      Your Company<p>
 * @author W. Christian
 * @version
 */
package filters;



public class FFTransform {

  public FFTransform() {}


  public static double[] four1(double[] data, int nn, int isign){
         int n, mmax, m,i,j,istep;
         double wtemp, wr, wpr, wpi, wi, theta,
                tempr, tempi, wrs,wis;

                n=2*nn;
                j=1;
                for(int ii=1;ii<=nn;ii++){
                    i=2*ii-1;
                    if(j>i){
                       tempr=data[j];
                       tempi=data[j+1];
                       data[j]=data[i];
                       data[j+1]=data[i+1];
                       data[i]=tempr;
                       data[i+1]=tempi;
                    }
                    m=n/2;
                    while(m>=2 && j>m){
                         j=j-m;
                         m=m/2;
                    }
                    j=j+m;
                }
                mmax=2;
                while(n>mmax){
                     istep=2*mmax;
                     theta=6.28318530717959/(isign*mmax);
                     wtemp=Math.sin(0.5*theta);
                     wpr=-2.0*wtemp*wtemp;
                     wpi=Math.sin(theta);
                     wr=1.0;
                     wi=0.0;
                     for (int ii=1;ii<=mmax/2;ii++){
                         m=2*ii-1;
                         wrs=wr;
                         wis=wi;
                         for(int jj=0;jj<=(n-m)/istep;jj++){
                            i=m+jj*istep;
                            j=i+mmax;
                            tempr=wrs*data[j]-wis*data[j+1];
                            tempi=wrs*data[j+1]+wis*data[j];
                            data[j]=data[i]-tempr;
                            data[j+1]=data[i+1]-tempi;
                            data[i]=data[i]+tempr;
                            data[i+1]=data[i+1]+tempi;
                         }
                         wtemp=wr;
                         wr=wr*wpr-wi*wpi+wr;
                         wi=wi*wpr+wtemp*wpi+wi;
                     }
                     mmax=istep;
                }

                return data;
  }

  public static double[] realft(double[] data, int n, int isign){
         int i1,i2,i3,i4,np3;
         double c1,c2,h1r,h1i,h2r,h2i,wrs,wis;
         double wr,wi,wpr,wpi,wtemp,theta;

         theta=6.28318530717959/(2.0*n);
         c1=0.5;
         if(isign==1){
           c2=-0.5;
           data=four1(data,n,1);
         }
         else {
         c2=0.5;
         theta=-theta;
         }
         wtemp=Math.sin(0.5*theta);
         wpr=-2.0*wtemp*wtemp;
         wpi=Math.sin(theta);
         wr=1.0+wpr;
         wi=wpi;
         for(int i=2;i<=n/2;i++){
             i1=i+i-1;
             i2=i1+1;
             i3=n+n+3-i2;
             i4=i3+1;
             wrs=wr;
             wis=wi;
             h1r=c1*(data[i1]+data[i3]);
             h1i=c1*(data[i2]-data[i4]);
             h2r=-c2*(data[i2]+data[i4]);
             h2i=c2*(data[i1]-data[i3]);
             data[i1]=h1r+wrs*h2r-wis*h2i;
             data[i2]=h1i+wrs*h2i+wis*h2r;
             data[i3]=h1r-wrs*h2r+wis*h2i;
             data[i4]=-h1i+wrs*h2i+wis*h2r;
             wtemp=wr;
             wr=wr*wpr-wi*wpi+wr;
             wi=wi*wpr+wtemp*wpi+wi;
         }
         if(isign==1){
            h1r=data[1];
            data[1]=h1r+data[2];
            data[2]=h1r-data[2];
         }
         else {
            h1r=data[1];
            data[1]=c1*(h1r+data[2]);
            data[2]=c1*(h1r-data[2]);
            data=four1(data,n,-1);
         }

         return data;
  }

  public static double[] sinft(double[] y, int n,int isign){
         int m,n2;
         double sum,y1,y2,wis;
         double theta,wi,wr,wpi,wpr,wtemp;

         theta=3.14159265358979/n;
         wr=1.0;
         wi=0.0;
         wtemp=Math.sin(0.5*theta);
         wpr=-2.0*wtemp*wtemp;
         wpi=Math.sin(theta);
         y[1]=0.0;
         m=n/2;
         n2=n+2;
         for(int j=2;j<=m+1;j++){
             wtemp=wr;
             wr=wr*wpr-wi*wpi+wr;
             wi=wi*wpr+wtemp*wpi+wi;
             wis=wi;
             y1=wis*(y[j]+y[n2-j]);
             y2=0.5*(y[j]-y[n2-j]);
             y[j]=y1+y2;
             y[n2-j]=y1-y2;
         }
         y=realft(y,m,+1);
         sum=0.0;
         y[1]=0.5*y[1];
         y[2]=0.0;
         for(int jj=0,j;jj<=m-1;jj++){
             j=2*jj+1;
             sum=sum+y[j];
             y[j]=y[j+1];
             y[j+1]=sum;
         }

         return y;
  }

  public static double[] cosft(double[] y, int n, int isign){
         double enf0,even,odd,sum,sume,sumo,y1,y2,wrs,wis;
         double theta,wi,wr,wpi,wpr,wtemp;
         int m,n2;

         theta=3.14159265358979/n;
         wr=1.0;
         wi=0.0;
         wtemp=Math.sin(0.5*theta);
         wpr=-2.0*wtemp*wtemp;
         wpi=Math.sin(theta);
         sum=y[1];
         m=n/2;
         n2=n+2;
         //for(int j=2;j<=m;j++){
         for(int j=2;j<=m+1;j++){
            wtemp=wr;
            wr=wr*wpr-wi*wpi+wr;
            wi=wi*wpr+wtemp*wpi+wi;
            wrs=wr;
            wis=wi;
            y1=0.5*(y[j]+y[n2-j]);
            y2=(y[j]-y[n2-j]);
            y[j]=y1-wis*y2;
            y[n2-j]=y1+wis*y2;
            sum=sum+wrs*y2;
         }
         y=realft(y,m,+1);
         y[2]=sum;
         int j;
         for(int jj=2;jj<=m;jj++){
            j=2*jj;
            sum=sum+y[j];
            y[j]=sum;
         }
         if(isign==-1){
            even=y[1];
            odd=y[2];
            for(int jj=1;jj<=m-1;jj++){
                j=2*jj+1;
                even=even+y[j];
                odd=odd+y[j+1];
            }
            enf0=2.0*(even-odd);
            sumo=y[1]-enf0;
            sume=(2.0*odd/n)-sumo;
            y[1]=0.5*enf0;
            y[2]=y[2]-sume;
            for(int jj=1;jj<=m-1;jj++){
               j=2*jj+1;
               y[j]=y[j]-sumo;
               y[j+1]=y[j+1]-sume;
            }
         }
         return y;
  }

}
