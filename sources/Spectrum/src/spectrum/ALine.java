////////////////////////////////////////////////////////////////////////////////
//	ALine.java
//	Author: Jim Nolen  07/99


package spectrum;

import java.awt.Color;
import java.awt.Graphics;



/**
 * Named for "Absorption Line," ALine.class inherits from Thing.class but is specialized to ABSORB at a desired
 * intensity and width.  ALine is similar to Absorption in all respects, except that the absorption intensity
 * is Gaussian over the width. The "width" is actually the full-width half-maximum of the Gaussian absorption.
 * "Intensity" for ALine is the intensity at the Gaussian peak.
 * An instance of Absorption is able to "paint" itself on an instance of spectrumPanel.class by returning an
 * absorption intensity.
 *
 * Instances of ALine.class are created when addAbsorptionLine(lambda,width,intensity) method is
 * called from SpectrumPanel.class.
 */

public class ALine extends SpectrumThing{


  public ALine(SpectrumPanel o, double lam, double w, double i){
    super(o,lam,w,i);
  }

  /**
  *
  * Over-rides paintLambda of Thing.class.  No actual drawing performed. The value that is
  * returned is and intensity value from -1 to +1 that is added to the total intensity
  * of a particular line in the spectrum. Negative return values absorb; positive return values emit.
  * A return value of zero makes no change to a particular spectral line.
  *
  * The intensity of ALine is Gaussian. Width represents the FWHM of the Gaussian peak.
  *
  * @param lam double wavelength in nm of line to "paint"
  * @returns double intensity value between -1 and +1.
  */
  public double paintLambda(double lam){
      double lam0,w;
      if (doppler) {
        lam0=dopplerShiftLine(lambda,spectrumPanel.beta);
        w=dopplerShiftLine(lineWidth,spectrumPanel.beta);
      }
      else {lam0=lambda;w=lineWidth;}
      if ((lam <lam0-4*w) || (lam > lam0+4*w)) return 0;
      else return -in*Math.exp(-(lam-lam0)*(lam-lam0)/(w*w));
  }

   void paintOS(Graphics g){
     double lam0, w;
     Color col = Color.black;
     if (doppler) {
        lam0=dopplerShiftLine(lambda,spectrumPanel.beta);
        w=dopplerShiftLine(lineWidth,spectrumPanel.beta);
     }
     else {lam0 = lambda; w = lineWidth;}
     int x1 = spectrumPanel.lambdaToPix(lam0-3*w);
     int x2 = spectrumPanel.lambdaToPix(lam0+3*w);

      for(int i=x1; i<x2; i++){
        if ((i>=0)&&(i<spectrumPanel.intensity.length)){
          spectrumPanel.intensity[i]+=paintLambda(spectrumPanel.pixToLambda(i));
          if (spectrumPanel.intensity[i]>1) spectrumPanel.intensity[i]=1;
          else if (spectrumPanel.intensity[i]<0) spectrumPanel.intensity[i]=0;
          col = new Color((int)(spectrumPanel.intensity[i]*spectrumPanel.red[i]),
                            (int)(spectrumPanel.intensity[i]*spectrumPanel.green[i]),
                            (int)(spectrumPanel.intensity[i]*spectrumPanel.blue[i]));

          g.setColor(col);
          g.drawLine(i,0,i,spectrumPanel.currentHeight);
       }
      }

  }


}