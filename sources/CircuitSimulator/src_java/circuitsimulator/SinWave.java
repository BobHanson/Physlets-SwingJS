package circuitsimulator;
import edu.davidson.tools.*;


/**
 * Circuit element, representing a time-dependant voltage source
 * 
 * @author Toon Van Hoecke
 */
public class SinWave extends Source
{
    SinWave(Circuit circ, int pol, int r, int c, String t, double amp, double ph, double freq){ 
        super(circ,pol,r,c,t);
        amplitude = amp;
        phase = ph;
        frequency = freq;
        function = Double.toString(amplitude)+"*sin(2*pi*"+Double.toString(frequency)
                        +"*(t+"+Double.toString(phase)+"))";
    }
    
    SinWave() {super();}
    
    public String getStringAdditions() {
        return ",amp="+amplitude+",phase="+phase+",freq="+frequency;
    }
    
    public boolean set(String list) {
        double t=gett();
        double Vnow=getV(t), Vprev=getV(t-circuit.dt);
        boolean ret=super.set(list);
        double newFreq=0; 
        if (SUtil.parameterExist(list,"amp=")) amplitude=SUtil.getParam(list,"amp=");
        if (SUtil.parameterExist(list,"phase=")) 
            if (SUtil.getParamStr(list,"phase=").equals("continuous")) 
                phase=getPhase(t,Vnow,Vprev);
            else phase=SUtil.getParam(list,"phase=");
        function = Double.toString(amplitude)+"*sin(2*pi*"+Double.toString(frequency)
                        +"*(t+"+Double.toString(phase)+"))";
        return ret;
    }

    public double getV(double t) {return amplitude*Math.sin(2*Math.PI*frequency*(t+phase));}

    public void parsefunction() {} //overriding
    
    public double getPhase(double t, double Vnow, double Vprev) {
        if (Vnow>Vprev) return (Math.asin(Vnow/amplitude)/(2*Math.PI)-(frequency*t)%1.0)/frequency;
        else return (0.5-(Math.asin(Vnow/amplitude))/(2*Math.PI)-(frequency*t)%1.0)/frequency;
    }
}