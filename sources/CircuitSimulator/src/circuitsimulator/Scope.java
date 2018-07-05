package circuitsimulator;

/**
 * Circuit element, representing a Oscilloscope (special case of resistor)
 * 
 * @author Toon Van Hoecke
 */
public class Scope extends Resistor
{
    Scope(Circuit circuit, double v, int d, int r, int c, String t) {
        super(circuit,v,d,r,c,t);
        if (value == 0.0) value = 1.0e6;
        setValueVisible(false);
    }

    Scope() {super();}
}
