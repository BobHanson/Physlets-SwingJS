package circuitsimulator;
import edu.davidson.tools.*;

/**
 * Circuit element, representing a diode
 *
 * @author Toon Van Hoecke
 */
public class Diode extends CircuitElement
{
    double nv,ov=0;

    Diode(Circuit circuit, double isat, int d, int r, int c, String t) {
        super(circuit,d,r,c,t);
        value = isat;
        unity = "A";
        //reverseEquation = true;
        //rightlinear=false;
        leftlinear=false;
    }

    Diode() {super();}

    public double impedance() {
        nv = ov;
        ov = getV();
        nv = nv==0? 1.0/(value*38.9) : nv/(value*(Math.exp(38.9*nv)-1.0));
        //return nv<0.001? nv : 0.001;
        return Math.abs(nv);
    }

    public double rightFunction(double sign){
        //double nv;
        /*if (reverseEquation == true) {
            nv=38.9*getV()*sign;
            //if (nv > 1) reverseEquation = false;
            return value*(Math.exp(nv)-1);
        }*/
        //nv = (getI()/value)+1.0;
        //return nv>0?25.707e-3*Math.log(nv):0.0;
        return 0;
    }

    public String valueStr(){return function;}

    public String getStringAdditions() {return ",isat="+Double.toString(value);}

    public boolean set(String list) {
        boolean ret=super.set(list);
        if (SUtil.parameterExist(list,"isat=")) value=SUtil.getParam(list,"isat=");
        return ret;
    }
}
