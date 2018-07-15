package circuitsimulator;
import edu.davidson.tools.*;

/**
 * Circuit element, representing a resistor
 * 
 * @author Toon Van Hoecke
 */
public class Resistor extends CircuitElement
{
    Resistor(Circuit circuit, double v, int r, int c, String t) {
        super(circuit,r,c,t);
        value = v;
        unity = "Ohm";
    }
 
    Resistor(Circuit circuit, double v, int pol, int r, int c, String t) {
        super(circuit,pol,r,c,t);
        value = v;
        unity = "Ohm";
    }

    Resistor() {super();}
    
    Resistor(Circuit circuit) {super(circuit);}
    
    public double impedance() {return value;}
    
    public String getStringAdditions() {return ",r="+Double.toString(value);}
    
    public boolean set(String list) {
        boolean ret=super.set(list);
        if (SUtil.parameterExist(list,"r=")) value=SUtil.getParam(list,"r=");
        return ret;
    }
    
}
