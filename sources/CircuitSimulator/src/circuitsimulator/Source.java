package circuitsimulator;
import edu.davidson.numerics.Parser;
import edu.davidson.tools.*;

/**
 * Circuit element, representing a time-dependant voltage source
 * 
 * @author Toon Van Hoecke
 */
public class Source extends CircuitElement
{
    double amplitude=0.0, phase=0.0;
    int sourcetype=-1;
    Parser parser = new Parser(3);
    double[] pvars = new double[3]; 
    double internalResistance = 1e-9;
    
    Source(Circuit circ, int pol, int r, int c, String to){
        super(circ,pol,r,c,to);
        frequency = 1.0;
        inputIndex=circuit.cirgrid.sourceContainer.size()+1;
        circuit.cirgrid.sourceContainer.addElement(this);
        setValueVisible(false);
        unity = "V";
    }
    
    Source() {super();}
        
    Source(Circuit circ, int pol, int r, int c, String to, String func){
        this(circ,pol,r,c,to);
        function = ""+func;
        parsefunction();
    }
    
    Source(Circuit circ, int pol, int r, int c, String to, String func, double freq){
        this(circ,pol,r,c,to,func);
        frequency = freq;
    }
    
    
    Source (Circuit circ, int pol, int r, int c, String to, int st, double amp, double ph, double freq) {
        this(circ,pol,r,c,to);
        frequency = freq;
        amplitude = amp;
        phase = ph;
        sourcetype = st;
        setfunction();
        parsefunction();
    }
    
    public void parsefunction() {
        parser.defineVariable(1,"t");
        parser.defineVariable(2,"f");//frequency
        parser.defineVariable(3,"p");//period
        parser.define(function.toLowerCase());
        parser.parse();
        if (parser.getErrorCode() != parser.NO_ERROR){
            System.out.println("Failed to parse function: "+function);
            System.out.println("Parse error: "+parser.getErrorString()+
                " function position "+parser.getErrorPosition());
        }
    }
    
    public String valueStr(){return function;}
    
    public void setfunction() {
        switch (sourcetype) {
        case 0: 
            function = Double.toString(amplitude);
		    break;
        case 1: 
            function = Double.toString(amplitude)+"*round(1-t*"+Double.toString(frequency)
                        +")+floor(t*"+Double.toString(frequency)+"))";
		    break;
		case 2:
            function = Double.toString(amplitude)+"*sin(2*pi*"+Double.toString(frequency)
                        +"*(t+"+Double.toString(phase)+"))";
	        break;
	    case 3:
            function = Double.toString(amplitude)+"*cos(2*pi*"+Double.toString(frequency)
                        +"*(t+"+Double.toString(phase)+"))";
	    }
    }
    
    public double getV(double t) {
        pvars[0] = t;
        pvars[1]=frequency;
        pvars[2]=1/frequency;
        return parser.evaluate(pvars);
    }
    
    public boolean set(String s) {
        boolean ret=super.set(s);
        if (SUtil.parameterExist(s,"r=")) internalResistance=SUtil.getParam(s,"r=");
        parsefunction();
        return ret;
    }
        
    public void setvalue(String s) {
        function = ""+s;
        parsefunction();
    }
    
    public String getvalue(){return function;}

    public double input(double sign){return sign;}
    
    public double impedance(){return internalResistance;}
    
    public String getStringAdditions() {
        return ",func="+function+",freq="+frequency+",r="+internalResistance;
    }
}
