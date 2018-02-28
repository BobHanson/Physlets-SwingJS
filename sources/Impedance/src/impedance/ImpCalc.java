package impedance;
//import java.awt.*;
import java.awt.Color;
import java.awt.Event;

import a2s.*;


/*	Nog doen ...
	Package van maken ...
	Hoe moet je een icon zetten ...
*/

class ImpCalcFrame extends Frame {
	public ImpCalcFrame() {
		super("Impedantie Calculator");
		parser=new Parser();
		String s=new String("(R(1E2)+C(1E-6))//(R(1E2)+L(3E-2))");
		parser.parseExpr(s);
		netwerk=parser.getResult();
		tf=new TextField(s);
		add("North", tf);
		graf=new Grafiek(netwerk);
		add("Center", graf);
		status=new Status(netwerk.toString());
		add("South", status);
		/** j2sNative */{
		  resize(600, 600+tf.size().height+status.size().height);
		}
		setBackground(Color.white);
	}
	public boolean handleEvent(Event e) {
		if (e.id==Event.WINDOW_DESTROY) {
			System.exit(0);
		}
		return super.handleEvent(e);
	}
	public boolean action(Event e, Object o) {
		if (e.target instanceof TextField) {
			String s=(String)o;
			if (parser.parseExpr(s)) {
				netwerk=parser.getResult();
				status.setText(netwerk.toString());
				graf.setNetwerk(netwerk);
			}
			else {
				tf.select(parser.getErrorPos()-1, parser.getErrorPos());
				status.errorText("Fout: "+parser.getErrorText()+" op positie "+parser.getErrorPos());
			}
			return true;
		}
		return false;
	}
	private Parser parser;
	private Netwerk netwerk;
	private TextField tf;
	private Status status;
	private Grafiek graf;
}

public class ImpCalc {
	public static void main(String[] args) {
		Frame frame=new ImpCalcFrame();
	//	frame-&gt;SetIcon(this, IDI_IMPCALC); // Hoe moet dit?
		frame.show();
	}
}
