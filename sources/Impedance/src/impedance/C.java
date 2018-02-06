package impedance;
public class C extends Component { // C=Condensator
	public C(double c) {
		super(c);
	}
	public Complex Z(double f) {
		return new Complex(0, -1/(2*Math.PI*f*value));
	}
	public String toString() {
		String s=new String();
		s+="C("+value+")";
		return s;
	}
}
