////////////////////////////////////////////////////////////////////////////////
//	Thing.java
//	Author: Jim Nolen  07/99

package spectrum;

import java.awt.Color;
import java.awt.Graphics;


import edu.davidson.tools.*;

/**
 *
 * Thing.class inherits from object and is the parent class for all
 * emitting and absorbing objects (i.e. ALine, EBand, etc.).  Thing itself
 * has very little functionality: only a constructor and a paintLambda method, the latter of which is
 * over-ridden in each child class.
 *
 *
 *
 */

public class SpectrumThing extends edu.davidson.display.Thing {
SpectrumPanel spectrumPanel = null;    //instance of SpectrumPanel.class that created the instance of this class.
SApplet sapplet = null;   //instance of SpectrumApplet.class that created owner.
double lambda=500;     //center wavelength of line
double lineWidth=5;        //width of line (FWHM)
double in =0;           // the intensity
boolean dragable = false;
boolean doppler = false;  //if doppler shift is enabled
int colr = -1;        //for setRGB methods
int colb = -1;
int colg = -1;
double gs = 0;      //ground state energy

  public SpectrumThing(SpectrumPanel sp, double lam, double w, double i) {
    super(sp,lam,0);
    spectrumPanel=sp;
    sapplet=spectrumPanel.getOwner();
    if (lam>0)lambda=lam;
    if (w>0) lineWidth=w;
    in=i;
    varStrings= new String[]{"lambda","width","intensity","lower"};
    ds=new double[1][4];  // the datasource state variables
  }

    final public double[][] getVariables(){
     ds[0][0]=lambda;
     ds[0][1]=lineWidth;
     ds[0][2]=in;
     ds[0][3]=gs;
     return ds;
  }


  /**
  *
  * Main "painting" method. No actual drawing performed. The value that is
  * returned is and intensity value from -1 to +1 that is added to the total intensity
  * of a particular line in the spectrum. Negative return values absorb; positive return values emit.
  * A return value of zero makes no change to a particular spectral line.
  *
  * @param lam double wavelength in nm of line to "paint"
  * @returns double intensity value between -1 and +1.
  */
  double paintLambda(double lam){
      return 1;
  }

  /**
  *
  * Method determines whether a given wavelength is within line width
  *
  * @param lam double
  * @return boolean
  */
  public boolean isInside(double lam){
    if ((lam>=lambda-lineWidth/2)&&(lam<=lambda+lineWidth/2))
    return true;
    else return false;

  }

  /**
  *
  * Method for moving line to a new wavelength
  *
  * @param lam double new wavelength of line
  *
  */
  public void setNewLambda(double lam){
     if (lam>0) lambda=lam;
     sapplet.updateDataConnection(this.hashCode() );
  }

  /**
  *
  * Method doppler shifts a given wavelength
  *
  * @param lam double initial wavelength
  * @param beta double f/c of shift equation
  * @return double shifted frequency
  */
  double dopplerShiftLine(double lam, double beta){
    double v0,vnew;
    v0=SpectrumPanel.clight/lam;   //clight is speed of light.
    vnew=v0*Math.sqrt(1+beta)/Math.sqrt(1-beta);
    return SpectrumPanel.clight/vnew;

  }

  public double getX(){return lambda;}
  public void setX(double x){
     super.setX(x);  // will enforce constraints.
     lambda=super.getX();
  }


  double calcEn(){
     if (lambda==0) return 0;
     else return -gs+1/lambda;

  }

  void paintOS(Graphics g){
     Color col = Color.black;
     double lam0,w;
     if (doppler) {
        lam0=dopplerShiftLine(lambda,spectrumPanel.beta);
        w=dopplerShiftLine(lineWidth,spectrumPanel.beta);
     }
     else {lam0=lambda; w=lineWidth;}
     int x1 = spectrumPanel.lambdaToPix(lam0-w/(double)(2));
     int x2 = spectrumPanel.lambdaToPix(lam0+w/(double)(2));

      for(int i=x1; i<x2; i++){
        if ((i>=0)&&(i<spectrumPanel.intensity.length)){
        if (colr>=0) {
          col = new Color((int)(colr*paintLambda(spectrumPanel.pixToLambda(i))),
                              (int)(colg*paintLambda(spectrumPanel.pixToLambda(i))),
                              (int)(colb*paintLambda(spectrumPanel.pixToLambda(i))));
        }
        else{
          spectrumPanel.intensity[i]+=paintLambda(spectrumPanel.pixToLambda(i));
          if (spectrumPanel.intensity[i]>1) spectrumPanel.intensity[i]=1;
          else if (spectrumPanel.intensity[i]<0) spectrumPanel.intensity[i]=0;
          col = new Color((int)(spectrumPanel.intensity[i]*spectrumPanel.red[i]),
                            (int)(spectrumPanel.intensity[i]*spectrumPanel.green[i]),
                            (int)(spectrumPanel.intensity[i]*spectrumPanel.blue[i]));
        }

        g.setColor(col);
        g.drawLine(i,0,i,spectrumPanel.currentHeight);

       }
      }

  }
}