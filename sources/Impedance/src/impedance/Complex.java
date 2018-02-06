package impedance;

/*
(a+jb) + (c+jd) = (a+c) + j(b+d)
(a+jb) - (c+jd) = (a-c) + j(b-d)
(a+jb) * (c+jd) = (a*c-b*d) + j(b*c+a*d)
(a+jb) / (c+jd) = ((a*c+b*d)/(c*c+d*d)) + j((b*c-a*d)/(c*c+d*d))
abs(a+jb)       = sqrt(a*a+b*b)
*/

public class Complex implements Cloneable { 
	public Complex() {
	}
	public Complex(double _r) {
		r=_r;
	}
	public Complex(double _r, double _i) {
		r=_r;
		i=_i;
	}
	public Complex(Complex c) {
		r=c.r;
		i=c.i;
	}
	public boolean equals(Object obj) {
		if (obj instanceof Complex) {
			Complex c=(Complex)obj;
			return r==c.r&&i==c.i;
		}
		return false;
	}
	public int hashCode() {
		return new Double(r).hashCode() + new Double(i).hashCode();
	}
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			/* can't happen really! */
		}
		return null;
	}
/*	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Complex: "+this+" destroyed.");
	}
*/	public Complex add(Complex c) {
		r+=c.r;
		i+=c.i;
		return this;
	}
	public Complex sub(Complex c) {
		r-=c.r;
		i+=c.i;
		return this;
	}
	public Complex mul(Complex c) {
		double d=r*c.r-i*c.i;
		i=i*c.r+r*c.i;
		r=d;
		return this;
	}
	public Complex div(Complex c) {
		double n=c.r*c.r+c.i*c.i;
		double d=(r*c.r+i*c.i)/n;
		i=(i*c.r-r*c.i)/n;
		r=d;
		return this;
	}
	public double abs() {
		return Math.sqrt(r*r+i*i);
	}
	public String toString()  {
		String s=new String();
		s+=r;
		if (i<0)
			s+="-j"+-i;
		else
			if (i>0)
				s+="+j"+i;
		return s;
	}
	private	double r=0.0;
	private double i=0.0;
}
