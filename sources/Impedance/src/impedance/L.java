package impedance;
public class L extends Component { // L=Spoel
	public L(double l) {
		super(l);
	}
	public Complex Z(double f) {
		return new Complex(0, 2*Math.PI*f*value);
	}
	public String toString() {
		String s=new String();
		s+="L("+value+")";
		return s;
	}
}
