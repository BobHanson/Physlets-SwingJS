package circuitsimulator;

/**
 * Circuit element, representing a Voltmeter (special case of resistor)
 * 
 * @author Toon Van Hoecke
 */
public class Vmeter extends Resistor
{
    Vmeter(Circuit circuit, double v, int d, int r, int c, String t) {
        super(circuit,v,d,r,c,t);
        if (value == 0.0) value = 1.0e6;
    }
    
    Vmeter() {super();}
    
    public String valueStr(){
        return "";
    }
}
