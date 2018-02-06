package circuit;

final public class CNumber {
  static double pi=Math.PI;
  double re=0;
  double im=0;

  public CNumber() {
  }

  CNumber(double re, double im) {
      this.re=re;
      this.im=im;
  }
  double getMag(){ return Math.sqrt(re*re+im*im);    }
  double getPhase(){ return Math.atan2(im,re); }
  double getDegree(){ return 180*Math.atan2(im,re)/pi;}
}