package circuitsimulator;
import edu.davidson.tools.*;

/**
 * Circuit element, representing an inductor.
 * 
 * @author Toon Van Hoecke
 */
public class Inductor extends CircuitElement
{
    Inductor(Circuit circuit, double v, int r, int c, String t) {
        super(circuit,r,c,t);
        value = v;
        unity = "H";
	}
    
    Inductor(Circuit circuit) {super(circuit);}
    
    public double impedance(){return value/circuit.dt;}
    
    public double differential(){return value/circuit.dt;}
    
    public String getStringAdditions() {return ",l="+Double.toString(value);}
    
    public boolean set(String list) {
        boolean ret=super.set(list);
        if (SUtil.parameterExist(list,"l=")) value=SUtil.getParam(list,"l=");
        return ret;
    }
}
