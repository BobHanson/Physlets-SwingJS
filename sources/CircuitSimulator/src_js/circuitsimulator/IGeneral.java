package circuitsimulator;

import java.awt.Font;
import edu.davidson.numerics.Parser;

/**
 * Circuit element, representing an element with a non-linear representation
 * 
 * @author Toon Van Hoecke
 */
public class IGeneral extends CircuitElement
{
    Parser parser = new Parser(2);
    double[] var=new double[2];
    
    IGeneral(Circuit circuit, String f, int r, int c, String t) {
        super(circuit,r,c,t);
        reverseEquation = true;
        font = new Font("TimesRoman", Font.PLAIN,8);
        rightlinear=false;
        function = ""+f;
        parsefunction();
    }
    
    IGeneral() {super();}
    
    public void parsefunction() {
        parser.defineVariable(1,"v");
        parser.defineVariable(2,"i");
        parser.define(function.toLowerCase());
        parser.parse();
        if (parser.getErrorCode() != parser.NO_ERROR){
            System.out.println("Failed to parse function: "+function);
            System.out.println("Parse error: "+parser.getErrorString()+
                " function position "+parser.getErrorPosition());
        }
    }
    
    public void setvalue(String s) {
        function = ""+s;
        parsefunction();
    }
    
    public double rightFunction(double sign){
        var[0]=getV();
        var[1]=getI();
        return parser.evaluate(var);
    }
    
    public String valueStr(){return function;}

    public String getStringAdditions() {return ",func="+function;}
}
