////////////////////////////////////////////////////////////////////////////////
//	Emission.java
//	Author: Jim Nolen  07/99


package spectrum;

/**
 * Emission.class inherits from Thing.class but is specialized to EMIT at a desired
 * intensity and width.  The intensity of the emission is uniform over the selected width.
 * An instance of Emission is able to "paint" itself on an instance of spectrumPanel.class by returning an
 * emission intensity.
 *
 * Instances of Emission.class are created when addEmission(lambda,width,intensity) method is
 * called from SpectrumPanel.class.
 */

public class Emission extends SpectrumThing{

  public Emission(SpectrumPanel o, double lam, double w, double i) {
    super(o,lam,w,i);
  }

  /**
  *
  * Over-rides paintLambda of Thing.class. No actual drawing performed. The value that is
  * returned is and intensity value from -1 to +1 that is added to the total intensity
  * of a particular line in the spectrum. Negative return values absorb; positive return values emit.
  * A return value of zero makes no change to a particular spectral line.
  *
  * The intensity of Emission is uniform over the width of the line.
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
      else {lam0=lambda;w=lineWidth; }
      if ((lam < lam0-w/2) || (lam > lam0+w/2)) return 0;
      else return in;
  }





}