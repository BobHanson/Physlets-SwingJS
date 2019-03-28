package circuitsimulator;
import edu.davidson.tools.*;

/**
 * Circuit element, representing a capacitor
 * 
 * @author Toon Van Hoecke
 */
public class Capacitor extends CircuitElement
{
    Capacitor(Circuit circuit, double v, int r, int c, String t) {
        super(circuit,r,c,t);
        value = v;
        unity = "F";
    }
    
    Capacitor(Circuit c) {super(c);}
    
    public double impedance(){return circuit.dt/value;}
    
    public double integralVHere(){return -1.0;}
    
    public double integralVNext(){return 1.0;}

    public String getStringAdditions() {return ",c="+Double.toString(value);}
    
    public boolean set(String list) {
        boolean ret=super.set(list);
        if (SUtil.parameterExist(list,"c=")) value=SUtil.getParam(list,"c=");
        return ret;
    }
}
