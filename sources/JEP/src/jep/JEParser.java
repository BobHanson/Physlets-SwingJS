package jep;

import  org.nfunk.jep.function.*;
import  org.nfunk.jep.ParseException;
import  org.netlib.math.complex.Complex;
import  java.lang.Math;
import  java.util.*;


/**
 * put your documentation comment here
 */
public class JEParser {
  public static final boolean MAKE_COMPLEX=true;
  public static final double SQRT2=Math.sqrt(2.0);
  public static final double COS45=1.0/SQRT2;
  org.nfunk.jep.JEP jep = new org.nfunk.jep.JEP();
  String[] variables;
 // boolean hasError=true;

  /**
   * put your documentation comment here
   */
  public JEParser (String f, String v) {
    this(f,v,false);
  }

  public JEParser (String f, String v1, String v2) {
    this(f,v1,v2,false);
  }

  public JEParser (String f, String v, boolean makeComplex) {
    variables = new String[1];
    variables[0]=v;
    jep.addStandardFunctions();
    jep.addStandardConstants();
    jep.addFunction("sqrt", new SquareRoot());
    jep.addFunction("exp", new Exp());
    jep.addFunction("step", new Step());
    if(makeComplex)jep.addComplex();
    jep.addVariable(v, 0);
    jep.parseExpression(f);
  }

  public JEParser (String f, String v1, String v2, boolean makeComplex) {
    variables = new String[2];
    variables[0]=v1;
    variables[1]=v2;
    jep.addStandardFunctions();
    jep.addStandardConstants();
    jep.addFunction("sqrt", new SquareRoot());
    jep.addFunction("exp", new Exp());
    jep.addFunction("step", new Step());
    if(makeComplex)jep.addComplex();
    jep.addVariable(v1, 0);
    jep.addVariable(v2, 0);
    jep.parseExpression(f);
  }

  public void setFunction(String f){
    jep.parseExpression(f);
  }

  public boolean hasError(){
      return jep.hasError();
  }

  /**
   * put your documentation comment here
   * @param x
   * @return double f(x)
   */
  public double evaluate (double x) {
    jep.addVariable(variables[0], x);
    return  jep.getValue();
  }

  public double evaluate (double x, double y) {
    if(variables.length<1){
      System.out.println("JEParser Error: Only one variable has been defined.");
      return 0;
    }
    jep.addVariable(variables[0], x);
    jep.addVariable(variables[1], y);
    return  jep.getValue();
  }

  /**
   * put your documentation comment here
   * @param x
   * @return double f(x)
   */
  public Complex evaluateComplex (double _x) {
     jep.addVariable(variables[0], _x);
     return  jep.getComplexValue();
  }

  /**
   * put your documentation comment here
   * @param x
   * @return double f(x)
   */
  public Complex evaluateComplex (double _x, double _y) {
     jep.addVariable(variables[0], _x);
     jep.addVariable(variables[1], _y);
     return  jep.getComplexValue();
  }

  public class SquareRoot extends PostfixMathCommand
      implements PostfixMathCommandI {

    /**
     * put your documentation comment here
     */
    public SquareRoot () {
      numberOfParameters = 1;
    }

    /**
     * put your documentation comment here
     * @param inStack
     * @exception ParseException
     */
    public void run (Stack inStack) throws ParseException {
      checkStack(inStack);
      Object param=inStack.pop();
      if (param instanceof Double){
        double val=((Double)param).doubleValue();
        if(val>=0)inStack.push( new Double(Math.sqrt(val)));
        else inStack.push( new Complex(0, Math.sqrt(-val) ));
      }else if (param instanceof Complex){
        inStack.push(((Complex)param).sqrt());
      }else{
        throw new ParseException("Invalid parameter type");
      }
      return;
    }
  }

  public class Exp extends PostfixMathCommand
      implements PostfixMathCommandI {

    /**
     * put your documentation comment here
     */
    public Exp () {
      numberOfParameters = 1;
    }

    /**
     * put your documentation comment here
     * @param inStack
     * @exception ParseException
     */
    public void run (Stack inStack) throws ParseException {
      checkStack(inStack);
      Object param=inStack.pop();
      if (param instanceof Double){
        inStack.push( new Double(Math.exp(((Double)param).doubleValue())));
      }else if (param instanceof Complex){
        inStack.push(((Complex)param).exp());
      }else{
        throw new ParseException("Invalid parameter type");
      }
      return;
    }
  }

  public class Step extends PostfixMathCommand
      implements PostfixMathCommandI {

    /**
     * put your documentation comment here
     */
    public Step () {
      numberOfParameters = 1;
    }

    /**
     * put your documentation comment here
     * @param inStack
     * @exception ParseException
     */
    public void run (Stack inStack) throws ParseException {
      checkStack(inStack);
      Object param=inStack.pop();
      if (param instanceof Double){
        inStack.push( new Double( ((Double)param).doubleValue()<0?0:1));
      }else{
        throw new ParseException("Invalid parameter type");
      }
      return;
    }
  }


}