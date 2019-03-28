package circuitsimulator;

/**
 * Circuit element, representing an amperemeter (special case of resistor)
 * 
 * @author Toon Van Hoecke
 */
public class Ameter extends Resistor
{
    Ameter(Circuit circuit, double v, int d, int r, int c, String t) {
        super(circuit,v,d,r,c,t);
        if (value == 0.0) value = 1.0e-1;
    }
    
    Ameter(Circuit circuit) {super(circuit);}
    
    public String valueStr(){
        return "";
    }
}
