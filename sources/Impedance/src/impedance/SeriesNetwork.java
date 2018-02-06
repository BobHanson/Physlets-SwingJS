package impedance;
public class SeriesNetwork extends BasisNetwerk {
	public SeriesNetwork(Netwerk n1, Netwerk n2) {
		super(n1, n2);
	}
	public Complex Z(double f) {
		Complex c=new Complex(netw1.Z(f));
		c.add(netw2.Z(f));
		return c;
	}
	public String toString() {
		String s=new String();
		s+="("+netw1+"+"+netw2+")";
		return s;
	}
}
