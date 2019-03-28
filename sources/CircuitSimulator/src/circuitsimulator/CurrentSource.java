package circuitsimulator;
import edu.davidson.tools.*;

/**
 * Circuit element, representing a Current source
 * 
 * @author Toon Van Hoecke
 */
public class CurrentSource extends CircuitElement
{
    public CurrentSource (Circuit circ, double a, int pol, int r, int c, String t){
        super(circ,pol,r,c,t);
        value = a;
        reverseEquation = true;
        unity = "A";
    }

    CurrentSource(Circuit circuit) {super(circuit);}
    
    public double rightFunction(double sign){return value*sign;}
    
    public String getStringAdditions() {return ",a="+Double.toString(value);}
    
    public boolean set(String s) {
        boolean ret=super.set(s);
        if (SUtil.parameterExist(s,"a=")) value=SUtil.getParam(s,"a=");
        return ret;
    }
    
}
