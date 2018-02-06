package animator4;

public class Charge extends Circle {
  double q=1;
  double range=1.0e-4;

  public Charge(AnimatorCanvas o,int diameter, String xStr, String yStr,double q) {
      super(o,diameter,xStr,yStr);
      s=1;
      w=diameter;
      h=diameter;
      this.q=q;
      this.range=diameter/2.0/canvas.pixPerUnit;
      setForce("0", "0", getX(), getY(), 0, 0 );
      canvas.dynamics.addRateEquation(this);
  }

} 