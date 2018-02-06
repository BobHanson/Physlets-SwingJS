
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) wolfgang christian<p>
 * Company:      <p>
 * @author wolfgang christian
 * @version 1.0
 */
package ode;
import edu.davidson.numerics.Parser;

public class RateEquation {
  Parser parser=null;
  String funcStr=null;
  String variable=null;
  double initialVal=0;


  public RateEquation(String vrs, double val, String func) {
      funcStr=func;
      variable=vrs;
      initialVal=val;
      parser = new Parser(0);  //prevent null pointer exceptions in case the equations is accessed before the suystem is parsed.
  }

  public boolean setVariables(String[] vars){
    parser = new Parser(vars.length);
    for(int i=0; i<vars.length; i++){
             parser.defineVariable(i+1,vars[i]); // define the variables
    }
    if(funcStr==null) return false;
    parser.define(funcStr);
    parser.parse();
    if(parser.getErrorCode() != Parser.NO_ERROR){
             System.out.println("Failed to parse rate equation): "+funcStr);
             System.out.println("Parse error: " + parser.getErrorString() +
                   " at function 1, position " + parser.getErrorPosition());
             for(int i=0; i<vars.length; i++){
               System.out.println("vars "+vars[i]);
             }
             return false;
      }else{return true;}
  }

  String appendXMLEquation(String str){
      return  str+"<ode var= \""+variable+"\" init=\""+initialVal+"\" rate=\""+funcStr+ "\" >"+"\n";
  }
}