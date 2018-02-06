package impedance;
public class P extends BasisNetwerk { // P=Parallel schakeling van twee netwerken
	public P(Netwerk n1, Netwerk n2) {
		super(n1, n2);
	}
	public Complex Z(double f) {
		Complex c1=netw1.Z(f);
		Complex c2=netw2.Z(f);
		Complex teller=new Complex(c1);
		teller.mul(c2);
		Complex noemer=new Complex(c1);
		noemer.add(c2);
		return teller.div(noemer);
	}
	public String toString() {
		String s=new String();
		s+="("+netw1+"//"+netw2+")";
		return s;
	}
}
