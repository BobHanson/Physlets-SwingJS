package circuitsimulator;

/**
 * Test Circuit element, representing a resistor with resistance = R*current
 * 
 * @author Toon Van Hoecke
 */
public class ResistorI extends Resistor
{
    ResistorI(Circuit circuit, double v, int r, int c, String t) {
        super(circuit,v,r,c,t);
        leftlinear=false;
    }
    
    public double impedance(){return Math.abs(value*getI());}
    
    public String valueStr(){return format.form(value)+"*I";}
    
    public String getStringAdditions() {return ",r="+Double.toString(value);}
}
