package impedance;
public class R extends Component { // R=Weerstand
	public R(double r) {
		super(r);
	}
	public Complex Z(double f) {
		return new Complex(value);
	}
	public String toString() {
		String s=new String();
		s+="R("+value+")";
		return s;
	}
}
