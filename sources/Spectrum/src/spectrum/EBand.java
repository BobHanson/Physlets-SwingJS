 ////////////////////////////////////////////////////////////////////////////////
//	EBand.java
//	Author: Jim Nolen  07/99


package spectrum;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.*;
import edu.davidson.numerics.Parser;

/**
 * Named for "Emission Band," EBand.class inherits from Thing.class but is specialized to EMIT at a desired
 * intensity and width.  The intensity of the emission is determined by a parser evaluated as a
 * function of wavelength. It is able to "paint" itself on an instance of spectrumPanel.class by returning an
 * emission intensity.
 *
 * Instances of EBand.class are created when addEmissionBand(lambda1,lambda2,func) method is
 * called from SpectrumPanel.class.
 */

public class EBand extends SpectrumThing{
 double lambda1 = 0;
 double lambda2 = 0;
 String inString= null;
 Parser iparser = null;

   public EBand(SpectrumPanel o, double lam1, double lam2, String func){
    super(o,Math.abs(0.5*(lam1-lam2)),Math.abs(lam1-lam2),0);
    lambda1=Math.min(lam1,lam2);
    lambda2=Math.max(lam1,lam2);
    if (setParse(func)) inString=func;
  }

  /**
  *
  * Over-rides paintLambda of Thing.class.  No actual drawing performed. The value that is
  * returned is and intensity value from -1 to +1 that is added to the total intensity
  * of a particular line in the spectrum. Negative return values absorb; positive return values emit.
  * A return value of zero makes no change to a particular spectral line.
  *
  * The intensity of EBand is determined by a function of wavelength.
  *
  * @param lam double wavelength in nm of line to "paint"
  * @returns double intensity value between -1 and +1.
  */
  public double paintLambda(double lam){
      double lam1,lam2,w;
      if (doppler) {
        lam1=dopplerShiftLine(lambda1,spectrumPanel.beta);
        lam2=dopplerShiftLine(lambda2,spectrumPanel.beta);
        w=dopplerShiftLine(lineWidth,spectrumPanel.beta);
      }
      else {lam1=lambda1;lam2=lambda2;w=lineWidth;}
      if ((lam<Math.min(lam1,lam2))||(lam>Math.max(lam1,lam2)))return 0;
      return iparser.evaluate(lam);
  }


  /**
  *
  * Method initializes parser which gives intensity as
  * a function of wavelength.
  *
  * @params str String function
  * @return true if successful
  */
  public boolean setParse(String str){
    String xstr=str;
    boolean noError=true;
    xstr.toLowerCase().trim();
    iparser = new Parser(1);
    iparser.define(xstr);
    iparser.defineVariable(1,"h");
    iparser.parse();
    if(iparser.getErrorCode() != Parser.NO_ERROR){     // error checkeing added by W. Christian
         noError=false;
         System.out.println("Failed to parse f(h)): "+xstr);
         System.out.println("Parse error in MathFunction: " + iparser.getErrorString() +
                   " at function 1, position " + iparser.getErrorPosition());
    }
    return noError;
  }

  public void setNewLambda(double lam){
    if (lam<=0) return;
    lambda=lam;
    double dl= (lambda1-lambda2)/2;
    lambda1=lam-dl;
    lambda2=lam+dl;
  }

   void paintOS(Graphics g){
     Color col = Color.black;
     double lam2,lam1;
     if (doppler) {
        lam1=dopplerShiftLine(lambda1,spectrumPanel.beta);
        lam2=dopplerShiftLine(lambda2,spectrumPanel.beta);
     }
     else {lam1=lambda1; lam2=lambda2;}
     int x1 = spectrumPanel.lambdaToPix(lam1);
     int x2 = spectrumPanel.lambdaToPix(lam2);

      for(int i=x1; i<x2; i++){
        if ((i>=0)&&(i<spectrumPanel.intensity.length)){
        if (colr>=0)
          col = new Color((int)(colr*paintLambda(spectrumPanel.pixToLambda(i))),
                              (int)(colg*paintLambda(spectrumPanel.pixToLambda(i))),
                              (int)(colb*paintLambda(spectrumPanel.pixToLambda(i))));
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