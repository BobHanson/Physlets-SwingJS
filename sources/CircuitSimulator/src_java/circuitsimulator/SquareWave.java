package circuitsimulator;
import edu.davidson.tools.*;


/**
 * Circuit element, representing a time-dependant voltage source
 * 
 * @author Toon Van Hoecke
 */
public class SquareWave extends Source
{
    double dutyfactor=0.5;
    
    SquareWave(Circuit circ, int pol, int r, int c, String t, double amp, double ph, double freq){ 
        super(circ,pol,r,c,t);
        amplitude = amp;
        dutyfactor = ph;
        frequency = freq;
        function="(t%(1/"+frequency+")<("+dutyfactor+"/"+frequency+") ? "+amplitude+":0";
    }
    
    SquareWave() {super();}
    
    public double getV(double t) {return (t%(1/frequency)<(dutyfactor/frequency))?amplitude:0;}
    
    public void parsefunction() {} //overriding
    
    public String getStringAdditions() {
        return ",amp="+amplitude+",dutyfactor="+dutyfactor+",freq="+frequency;
    }
    
    public boolean set(String list) {
        boolean ret=super.set(list);
        if (SUtil.parameterExist(list,"amp=")) amplitude=SUtil.getParam(list,"amp=");
        if (SUtil.parameterExist(list,"dutyfactor=")) dutyfactor=SUtil.getParam(list,"dutyfactor=");
        return ret;
    }
}