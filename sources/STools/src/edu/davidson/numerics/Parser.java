/*----------------------------------------------------------------------------------------*
 * Parser.java version 1.0                                                    Jun 16 1996 *
 * Parser.java version 2.0                                                    Aug 25 1996 *
 * Parser.java version 2.1                                                    Oct 14 1996 *
 * Parser.java version 2.11                                                   Oct 25 1996 *
 * Parser.java version 2.2                                                    Nov  8 1996 *
 * Parser.java version 3.0                                                    May 17 1997 *
 *                                                                                        *
 * Copyright (c) 1996 Yanto Suryono. All Rights Reserved.                                 *
 *                                                                                        *
 * Permission to use, copy, and distribute this software for NON-COMMERCIAL purposes      *
 * and without fee is hereby granted provided that this copyright notice appears in all   *
 * copies and this software is not modified in any way                                    *
 *                                                                                        *
 * Please send bug reports and/or corrections/suggestions to                              *
 * Yanto Suryono <d0771@cranesv.egg.kushiro-ct.ac.jp>                                     *
 *----------------------------------------------------------------------------------------*/
package edu.davidson.numerics;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Indicates that an error occured in parser operation, and the operation
 * could not be completed. Used internally in <code>Parser</code> class.
 *
 * @see Parser
 */

class ParserException extends Exception {
  private int errorcode;

  /**
   * The constructor of <code>ParserException</code>.
   *
   * @param code the error code
   */

  ParserException(int code) {
    super();
    errorcode = code;
  }

  /**
   * Gets the error code.
   *
   * @return the error code
   */

  public int getErrorCode() {
    return errorcode;
  }
}

/**
 * The class <code>Parser</code> is a mathematical expression parser.<p>
 * Example of code that uses this class:<p>
 *
 * <pre>
 * Parser parser = new Parser(1);    // creates parser with one variable
 * parser.defineVariable(1,"x");     // lets the variable be 'x'
 * parser.define("sin(x)/x");        // defines function: sin(x)/x
 * parser.parse();                   // parses the function
 *
 * // calculates: sin(x)/x with x = -5.0 .. +5.0 in 20 steps
 * // and prints the result to standard output.
 *
 * float result;
 * for (i=-10; i <= 10; i++) {
 *   parser.setVariable(1,(float)i/2.0f);
 *   result = parser.evaluate();
 *   System.out.println(result);
 * }
 * </pre>
 *
 * @author  Yanto Suryono
 * @version 3.0, 17 May 1997
 */

public final class Parser {

  // global variables

  private int     var_count;             // number of variables
  private String  var_name[];            // variables' name
  private double  var_value[];           // value of variables
  private double  number[];              // numeric constants in defined function
  private String  function = "";         // function definition
  private String  postfix_code = "";     // the postfix code
  private boolean valid = false;         // postfix code status
  private int     error;                 // error code of last process
  private boolean ISBOOLEAN = false;     // boolean flag
  private boolean INRELATION = false;    // relation flag

  // variables used during parsing

  private int     position;              // parsing pointer
  private int     start;                 // starting position of identifier
  private int     num;                   // number of numeric constants
  private char    character;             // current character

  // variables used during evaluating

  private boolean  radian;               // radian unit flag
  private int      numberindex;          // pointer to numbers/constants bank
  private double[] refvalue = null;      // value of references

  //private  static final int MAX_NUM       = 100;  // max numeric constants  // changed by W. Christian
  private  static final int MAX_NUM       = 200;  // max numeric constants
  //private  static final int NO_FUNCS      = 24;   // no. of built-in functions
  // changed from 24 to 25 function by W. Christian to add step function
  // changed from 25 to 26 function by W. Christian to add random function
  // changed from 26 to 27 function by W. Christian to add sin function for Spanish version
  private  static final int NO_FUNCS             = 27;   // no. of built-in functions

  private  static final int NO_EXT_FUNCS  = 4;    // no. of extended functions
  private  static final int STACK_SIZE    = 50;   // evaluation stack size

  private double[] stack = new double[STACK_SIZE]; // moved by W. Christian from evaluate for speed

  // constants

  private  static final double DEGTORAD          = Math.PI / 180;
  private  static final double LOG10             = Math.log(10);


  // references - version 3.0

  private  Hashtable<String, int[]> references = null;
  private  Vector<String>    refnames = null;

  // error codes

  /**
   * No error.
   */
  public   static final int NO_ERROR             =  0;

  /**
   * Syntax error.
   */
  public   static final int SYNTAX_ERROR         =  1;

  /**
   * Parentheses expected.
   */
  public   static final int PAREN_EXPECTED       =  2;

  /**
   * Attempt to evaluate an uncompiled function.
   */
  public   static final int UNCOMPILED_FUNCTION  =  3;

  /**
   * Expression expected.
   */
  public   static final int EXPRESSION_EXPECTED  =  4;

  /**
   * Unknown identifier.
   */
  public   static final int UNKNOWN_IDENTIFIER   =  5;

  /**
   * Operator expected.
   */
  public   static final int OPERATOR_EXPECTED    =  6;

  /**
   * Parenthesis mismatch.
   */
  public   static final int PAREN_NOT_MATCH      =  7;

  /**
   * Code damaged.
   */
  public   static final int CODE_DAMAGED         =  8;

  /**
   * Stack overflow.
   */
  public   static final int STACK_OVERFLOW       =  9;

  /**
   * Too many constants.
   */
  public   static final int TOO_MANY_CONSTS      = 10;

  /**
   * Comma expected.
   */
  public   static final int COMMA_EXPECTED       = 11;

  /**
   * Invalid operand.
   */
  public   static final int INVALID_OPERAND      = 12;

  /**
   * Invalid operator.
   */
  public   static final int INVALID_OPERATOR     = 13;

  /**
   * No function definition to parse.
   */
  public   static final int NO_FUNC_DEFINITION   = 14;

  /**
   * Referenced name could not be found.
   */
  public   static final int REF_NAME_EXPECTED    = 15;

  // postfix codes

  private  static final int  FUNC_OFFSET         = 1000;
  private  static final int  EXT_FUNC_OFFSET     = FUNC_OFFSET + NO_FUNCS;
  private  static final int  VAR_OFFSET          = 2000;
  private  static final int  REF_OFFSET          = 3000;
  private  static final int  PI_CODE             = 253;
  private  static final int  E_CODE              = 254;
  private  static final int NUMERIC              = 255;

  // Jump, followed by n : Displacement
  private  static final int JUMP_CODE           = 1;

  // Relation less than (<)
  private  static final int LESS_THAN           = 2;

  // Relation greater than (>)
  private  static final int GREATER_THAN        = 3;

  // Relation less than or equal (<=)
  private  static final int LESS_EQUAL          = 4;

  // Relation greater than or equal (>=)
  private  static final int GREATER_EQUAL       = 5;
  

  // Relation not equal (<>)
  private  static final int NOT_EQUAL           = 6;

  // Relation equal (=)
  private  static final int EQUAL               = 7;

  // Conditional statement IF, followed by a conditional block :
  //   * Displacement (Used to jump to condition FALSE code)
  //   * Condition TRUE code
  //   * Jump to next code outside conditional block
  //   * Condition FALSE code
  //   * ENDIF
  private  static final int IF_CODE             = 8;
  private  static final int ENDIF               = 9;

  private  static final int AND_CODE            = 10;    // Boolean AND
  private  static final int OR_CODE             = 11;    // Boolean OR
  private  static final int NOT_CODE            = 12;    // Boolean NOT

  // built in functions

  private String funcname[] =

  { "sin",   "cos",   "tan",   "ln",    "log",   "abs",
    "int",   "frac",  "asin",  "acos",  "atan",  "sinh",
    "cosh",  "tanh",  "asinh", "acosh", "atanh", "ceil",
    "floor", "round", "exp",   "sqr",   "sqrt",  "sign", "step", "random", "sen" };    // step and random added by W. Christian

  // extended functions

  private String extfunc[]  = { "min",   "max",   "mod",   "atan2" };
private int[] postfix_code_ints;

  /**
   * The constructor of <code>Parser</code>.
   *
   * @param variablecount the number of variables
   */

  public Parser(int variablecount) {
    var_count = variablecount;
    references = new Hashtable<String, int[]>();
    refnames = new Vector<String>();
    radian = true;

    // arrays are much faster than vectors (IMHO)

    var_name = new String[variablecount];
    var_value = new double[variablecount];
    number = new double[MAX_NUM];
  }

    /**
   * The constructor of <code>Parser</code> with a variable count of 1.
   *
   */

  public Parser() {
    int variablecount=1;
    var_count = variablecount;
    references = new Hashtable();
    refnames = new Vector();
    radian = true;

    // arrays are much faster than vectors (IMHO)

    var_name = new String[variablecount];
    var_value = new double[variablecount];
    number = new double[MAX_NUM];
  }

  /**
   * Set the number of variables.
   *
   * @param variablecount the number of variables
   */
  private void adjustVarCount(int variablecount){
    var_count = variablecount;
    references = new Hashtable();
    refnames = new Vector();
    var_name = new String[variablecount];
    var_value = new double[variablecount];
    number = new double[MAX_NUM];
  }

  /**
   * Sets the angle unit to radian. Default upon construction.
   */

  public void useRadian() {
    radian = true;
  }

  /**
   * Sets the angle unit to degree.
   */

  public void useDegree() {
    radian = false;
  }

  /**
   * Sets the variable names.
   * Nothing happens if variable index > number of variables.
   *
   * @param index the variable index (one based)
   * @param name  the variable name
   */

  public void defineVariable(int index, String name) {
    if (index > var_count) return;
    var_name[index-1] = name;
  }

  /**
   * Sets the variable names using an array.
   *
   * The size of the arrays are adjust to reflect the number of variables.
   * Added by W. Chrsitian.
   *
   * @param newVars  the variable names
   */

  public void defineVariables( String[] newVars) {
      if(newVars.length!=var_count)adjustVarCount(newVars.length);
      for(int i=0; i<newVars.length; i++){
             defineVariable(i+1,newVars[i]); // define the variables
      }
  }

  /**
   * Sets the variable value.
   * The variable is accessed by index.
   * Nothing happens if variable index > number of variables.
   *
   * @param index the variable index (one based)
   * @param value the variable value
   */

  public void setVariable(int index, double value) {
    if (index > var_count) return;
    var_value[index-1] = value;
  }

  /**
   * Sets the variable value.
   * The variable is accessed by name.
   * Nothing happens if variable could not be found.
   *
   * @param name  the variable name
   * @param value the variable value
   */

  public void setVariable(String name, double value) {
    for (int i=0; i < var_count; i++) {
      if (var_name[i].equals(name)) {
        var_value[i] = value; break;
      }
    }
  }


  /**
   * Remove any escape sequences such as \, and replace with the character.
   *  by W. Christian.
   *
   * @param function the function
   *
   * @return the new function
   */
   private String removeEscapeCharacter(String str){
      if(str==null || str.length()<1) return str;
      StringBuffer sb=new StringBuffer(str.length());
      for(int i=0; i<str.length(); i++){
         if(str.charAt(i) != '\\' )sb.append(str.charAt(i));
      }
      return sb.toString();
   }

  /**
   * Fix a bug in the parser by adding a zero in front of unary minus.
   * -x^2 is converted to 0-x^2
   * (- is converted to (0-
   *  by W. Christian.
   *
   * @param function the function
   *
   * @return the new function
   */
   private String fixUnaryMinus(String str){
      if(str==null || str.length()<1) return str;
      StringBuffer sb=new StringBuffer(str.length());
      if(str.charAt(0) == '-' )sb.append('0');   // the first character is a unary minus so prepend a zero.
      for(int i=0; i<str.length(); i++){
         if(i>0 && str.charAt(i) == '-' && str.charAt(i-1) == '(')sb.append('0');
         sb.append(str.charAt(i));  // this should always happen so we do not loose any characters.
      }
      return sb.toString();
  }

  /**
   * Defines a function. Current postfix code becomes invalid.
   * Modifed by W. Christian.
   *
   * @param definition the function definition
   */

  public void define(String definition) {
    function = definition;
    function.toLowerCase();
    function=removeEscapeCharacter(function);
    function=fixUnaryMinus(function);
    valid = false;
  }

	/**
	 * Parses defined function.
	 */

	public void parse() {
		String allFunction = new String(function);
		String orgFunction = new String(function);
		int index;

		if (valid)
			return;
		num = 0;
		error = NO_ERROR;
		references.clear();
		refnames.removeAllElements();

		while ((index = allFunction.lastIndexOf(";")) != -1) {
			function = allFunction.substring(index + 1) + ')';
			allFunction = allFunction.substring(0, index++);

			// references are of form: refname1:reffunc1;refname2:reffunc2;...

			String refname = null;
			int separator = function.indexOf(":");
			if (separator == -1) {
				error = NO_FUNC_DEFINITION;
				for (position = 0; position < function.length(); position++)
					if (function.charAt(position) != ' ')
						break;
				position++;
			} else {
				refname = function.substring(0, separator);
				function = function.substring(separator + 1);
				refname = refname.trim();
				if (refname.equals("")) {
					error = REF_NAME_EXPECTED;
					position = 1;
				} else {
					index += ++separator;
					parseSubFunction();
				}
			}
			if (error != NO_ERROR) {
				position += index;
				break;
			}
			references.put(refname, getints(postfix_code));
			refnames.addElement(refname);
		}
		if (error == NO_ERROR) {
			function = allFunction + ')';
			parseSubFunction();
		    postfix_code_ints = getints(postfix_code);
		}
		function = orgFunction;
		valid = (error == NO_ERROR);
	}


  private int[] getints(String postfix_code) {
	  int[] a = new int[postfix_code.length()];
	  for (int i = a.length; --i >= 0;)
		  a[i] = postfix_code.codePointAt(i);
	return a;
}

public double evaluate(double x, double y)
  // added by Wolfgang Christian to make it easier to call parser.
  {
    if (var_count!=2) return 0;
    var_value[0] = x;
    var_value[1] = y;
    return evaluate();
  }

  public double evaluate(double x, double y, double z)
  // added by Wolfgang Christian to make it easier to call parser.
  {
    if (var_count!=3) return 0;
    var_value[0] = x;
    var_value[1] = y;
    var_value[2] = z;
    return evaluate();
  }

  public double evaluate(double x)
  // added by Wolfgang Christian to make it easier to call parser.
  {
    if (var_count!=1) return 0;
    var_value[0] = x;
    return evaluate();
  }

  public double evaluate(double[] v)
  // added by Wolfgang Christian to make it easier to call parser with an array.
  {
    if (var_value.length!=v.length) return 0;
    System.arraycopy(v,0,var_value,0,v.length);
    return evaluate();
  }


  /**
   * Evaluates compiled function.
   *
   * @return the result of the function
   */

  public double evaluate() {
    int size = refnames.size();
    double result;

    if (!valid) {
      error = UNCOMPILED_FUNCTION; return 0;
    }
    error = NO_ERROR; numberindex = 0;

    if (size != 0) {
      int[] orgPFC = postfix_code_ints;
      refvalue = new double[size];

      for (int i=0; i < size; i++) {
        String name = (String)refnames.elementAt(i);
        postfix_code_ints = references.get(name);
        result = evaluateSubFunction();
        if (error != NO_ERROR) {
          postfix_code_ints = orgPFC;
          refvalue = null;
          return result;
        }
        refvalue[i] = result;
      }
      postfix_code_ints = orgPFC;
    }
    result = evaluateSubFunction();
    refvalue = null;
    // added by W. Christian to trap for NaN
    if(Double.isNaN(result)) result=0.0;
    return result;
  }

  /**
   * Gets error code of last operation.
   *
   * @return the error code
   */

  public int getErrorCode() {
    return error;
  }

  /**
   * Gets error string/message of last operation.
   *
   * @return the error string
   */

  public String getErrorString() {
    return toErrorString(error);
  }

  /**
   * Gets error position. Valid only if error code != NO_ERROR
   *
   * @return error position (one based)
   */

  public int getErrorPosition() {
    return position;
  }

  /**
   * Converts error code to error string.
   *
   * @return the error string
   */

  public static String toErrorString(int errorcode) {
    String s = "";

    switch (errorcode) {
      case NO_ERROR             : s = "no error"; break;
      case SYNTAX_ERROR         : s = "syntax error"; break;
      case PAREN_EXPECTED       : s = "parenthesis expected"; break;
      case UNCOMPILED_FUNCTION  : s = "uncompiled function"; break;
      case EXPRESSION_EXPECTED  : s = "expression expected"; break;
      case UNKNOWN_IDENTIFIER   : s = "unknown identifier"; break;
      case OPERATOR_EXPECTED    : s = "operator expected"; break;
      case PAREN_NOT_MATCH      : s = "parentheses not match"; break;
      case CODE_DAMAGED         : s = "internal code damaged"; break;
      case STACK_OVERFLOW       : s = "execution stack overflow"; break;
      case TOO_MANY_CONSTS      : s = "too many constants"; break;
      case COMMA_EXPECTED       : s = "comma expected"; break;
      case INVALID_OPERAND      : s = "invalid operand type"; break;
      case INVALID_OPERATOR     : s = "invalid operator"; break;
      case NO_FUNC_DEFINITION   : s = "bad reference definition (: expected)"; break;
      case REF_NAME_EXPECTED    : s = "reference name expected"; break;
    }
    return s;
  }

  // added by W. Christian

  /**
   * Gets function string of last operation.
   *
   * @return the function string
   */

  public String getFunctionString() {
    return function;
  }

/*----------------------------------------------------------------------------------------*
 *                            Private methods begin here                                  *
 *----------------------------------------------------------------------------------------*/

  /**
   * Advances parsing pointer, skips pass all white spaces.
   *
   * @exception ParserException
   */

  private void skipSpaces() throws ParserException {
    try {
      while (function.charAt(position-1) == ' ') position++;
      character = function.charAt(position-1);
    }
    catch(StringIndexOutOfBoundsException e) {
      throw new ParserException(PAREN_NOT_MATCH);
    }
  }

  /**
   * Advances parsing pointer, gets next character.
   *
   * @exception ParserException
   */

  private void getNextCharacter() throws ParserException {
    position++;
    try {
      character = function.charAt(position-1);
    }
    catch(StringIndexOutOfBoundsException e) {
      throw new ParserException(PAREN_NOT_MATCH);
    }
  }

  /**
   * Appends postfix code to compiled code.
   *
   * @param code the postfix code to append
   */

  private void addCode(int code) {
    postfix_code += (char) code;
  }

  /**
   * Scans a number. Valid format: xxx[.xxx[e[+|-]xxx]]
   *
   * @exception ParserException
   */

   // changed by W. Christian to parese numbers with leading zeros.
  private void scanNumber() throws ParserException {
    String  numstr = "";
    double  value;

    if (num == MAX_NUM)
      throw new ParserException(TOO_MANY_CONSTS);
    if(character != '.')   // added by W. Christian
      do {
        numstr += character; getNextCharacter();
      } while ((character >= '0') && (character <= '9'));
      else{numstr +='0';}   // added by W. Christian
    if (character == '.') {
      do {
        numstr += character; getNextCharacter();
      } while ((character >= '0') && (character <= '9'));
    }
    if (character == 'e') {
      numstr += character; getNextCharacter();
      if ((character == '+') || (character == '-')) {
        numstr += character; getNextCharacter();
      }
      while ((character >= '0') && (character <= '9')) {
        numstr += character; getNextCharacter();
      }
    }
    try {
      value = Double.valueOf(numstr).doubleValue();
    }
    catch(NumberFormatException e) {
      position = start;
      throw new ParserException(SYNTAX_ERROR);
    }
    number[num++] = value; addCode((char)NUMERIC);
  }

  /**
   * Scans a non-numerical identifier. Can be function call,
   * variable, reference, etc.
   *
   * @exception ParserException
   */

  private void scanNonNumeric() throws ParserException {
    String stream = "";

    if ((character == '*') || (character == '/') ||
        (character == '^') || (character == ')') ||
        (character == ',') || (character == '<') ||
        (character == '>') || (character == '=') ||
        (character == '&') || (character == '|'))
      throw new ParserException(SYNTAX_ERROR);
    do {
      stream += character; getNextCharacter();
    } while (!(
              (character == ' ') || (character == '+') ||
              (character == '-') || (character == '*') ||
              (character == '/') || (character == '^') ||
              (character == '(') || (character == ')') ||
              (character == ',') || (character == '<') ||
              (character == '>') || (character == '=') ||
              (character == '&') || (character == '|')
              ));
    if (stream.equals("pi")) {
      addCode((char)PI_CODE); return;
    }
    else
    if (stream.equals("e")) {
      addCode((char)E_CODE); return;
    }

    // if

    if (stream.equals("if")) {
      skipSpaces();
      if (character != '(')
        throw new ParserException(PAREN_EXPECTED);
      scanAndParse();
      if (character != ',')
        throw new ParserException(COMMA_EXPECTED);
      addCode((char)IF_CODE);
      String savecode = new String(postfix_code);
      postfix_code = "";
      scanAndParse();
      if (character != ',')
        throw new ParserException(COMMA_EXPECTED);
      addCode((char)JUMP_CODE);
      savecode += (char)(postfix_code.length()+2);
      savecode += postfix_code;
      postfix_code = "";
      scanAndParse();
      if (character != ')')
        throw new ParserException(PAREN_EXPECTED);
      savecode += (char)(postfix_code.length()+1);
      savecode += postfix_code;
      postfix_code = new String(savecode);
      getNextCharacter();
      return;
    }

    // built-in function

    for (int i = 0; i < NO_FUNCS; i++) {
      if (stream.equals(funcname[i])) {
        skipSpaces();
        if (character != '(')
          throw new ParserException(PAREN_EXPECTED);
        scanAndParse();
        if (character != ')')
          throw new ParserException(PAREN_EXPECTED);
        getNextCharacter();
        addCode((char)(i + FUNC_OFFSET));
        return;
      }
    }

    // extended functions

    for (int i = 0; i < NO_EXT_FUNCS; i++) {
      if (stream.equals(extfunc[i])) {
        skipSpaces();
        if (character != '(')
          throw new ParserException(PAREN_EXPECTED);
        scanAndParse();
        if (character != ',')
          throw new ParserException(COMMA_EXPECTED);
        String savecode = new String(postfix_code);
        postfix_code = "";
        scanAndParse();
        if (character != ')')
          throw new ParserException(PAREN_EXPECTED);
        getNextCharacter();
        savecode += postfix_code;
        postfix_code = new String(savecode);
        addCode((char)(i + EXT_FUNC_OFFSET));
        return;
      }
    }

    // registered variables

    for (int i = 0; i < var_count; i++) {
      if (stream.equals(var_name[i])) {
        addCode((char)(i + VAR_OFFSET)); return;
      }
    }

    // references

    int index = refnames.indexOf(stream);
    if (index != -1) {
      addCode((char)(index + REF_OFFSET)); return;
    }

    position = start;
    throw new ParserException(UNKNOWN_IDENTIFIER);
  }

  /**
   * Gets an identifier starting from current parsing pointer.
   *
   * @exception ParserException
   */

  private void getIdentifier() throws ParserException {
    boolean negate = false;

    getNextCharacter(); skipSpaces();

    if (character == '!') {
      getNextCharacter(); skipSpaces();
      if (character != '(')
        throw new ParserException(PAREN_EXPECTED);
      scanAndParse();
      if (character != ')')
        throw new ParserException(PAREN_EXPECTED);
      if (!ISBOOLEAN)
        throw new ParserException(INVALID_OPERAND);
      addCode((char)NOT_CODE);
      getNextCharacter();
      return;
    }
    ISBOOLEAN = false;
    while ((character == '+') || (character == '-')) {
      if (character == '-') negate = !negate;
      getNextCharacter(); skipSpaces();
    }
    start = position;
    //if ((character >= '0') && (character <= '9'))    changed be W. Christian to handle leanding zeros.
    if (((character >= '0') && (character <= '9')) || character=='.')
      scanNumber();
    else
    if (character == '(') {
      scanAndParse();
      getNextCharacter();
    }
    else scanNonNumeric();
    skipSpaces();
    if (negate) addCode('_');
  }

  /**
   * Scans arithmetic level 3 (highest). Power arithmetics.
   *
   * @exception ParserException
   */

  private void arithmeticLevel3() throws ParserException {
    int repcount = 0;

    if (ISBOOLEAN)
      throw new ParserException(INVALID_OPERAND);
    do {
      getIdentifier();
      if (ISBOOLEAN)
        throw new ParserException(INVALID_OPERAND);
      repcount++;
    } while (character == '^');
    for (int i = 1; i <= repcount; i++) addCode('^');
  }

  /**
   * Scans arithmetic level 2. Multiplications and divisions.
   *
   * @exception ParserException
   */

  private void arithmeticLevel2() throws ParserException {
    if (ISBOOLEAN)
      throw new ParserException(INVALID_OPERAND);
    do {
      char operator = character;
      getIdentifier();
      if (ISBOOLEAN)
        throw new ParserException(INVALID_OPERAND);
      if (character == '^') arithmeticLevel3();
      addCode(operator);
    } while ((character == '*') || (character == '/'));
  }

  /**
   * Scans arithmetic level 1 (lowest).
   * Additions and substractions.
   *
   * @exception ParserException
   */

  private void arithmeticLevel1() throws ParserException {
    if (ISBOOLEAN)
      throw new ParserException(INVALID_OPERAND);
    do {
      char operator = character;
      getIdentifier();
      if (ISBOOLEAN)
        throw new ParserException(INVALID_OPERAND);
      if (character == '^') arithmeticLevel3(); else
      if ((character == '*') || (character == '/')) arithmeticLevel2();
      addCode(operator);
    } while ((character == '+') || (character == '-'));
  }

  /**
   * Scans relation level.
   *
   * @exception ParserException
   */

  private void relationLevel() throws ParserException {
    char code = (char)0;

    if (INRELATION)
      throw new ParserException(INVALID_OPERATOR);
    INRELATION = true;
    if (ISBOOLEAN)
      throw new ParserException(INVALID_OPERAND);
    switch (character) {
      case '=': code = EQUAL; break;
      case '<': code = LESS_THAN;
                getNextCharacter();
                if (character == '>') code = NOT_EQUAL; else
                if (character == '=') code = LESS_EQUAL; else position--;
                break;
      case '>': code = GREATER_THAN;
                getNextCharacter();
                if (character == '=') code = GREATER_EQUAL; else position--;
                break;
    }
    scanAndParse();
    INRELATION = false;
    if (ISBOOLEAN)
      throw new ParserException(INVALID_OPERAND);
    addCode(code);
    ISBOOLEAN = true;
  }

  /**
   * Scans boolean level.
   *
   * @exception ParserException
   */

  private void booleanLevel() throws ParserException {
    if (!ISBOOLEAN)
      throw new ParserException(INVALID_OPERAND);
    char operator = character;
    scanAndParse();
    if (!ISBOOLEAN)
      throw new ParserException(INVALID_OPERAND);
    switch (operator) {
      case '&': addCode((char)AND_CODE); break;
      case '|': addCode((char)OR_CODE); break;
    }
  }

  /**
   * Main method of scanning and parsing process.
   *
   * @exception ParserException
   */

  private void scanAndParse() throws ParserException {
    getIdentifier();
    do {
      switch (character) {
        case '+':
        case '-': arithmeticLevel1(); break;
        case '*':
        case '/': arithmeticLevel2(); break;
        case '^': arithmeticLevel3(); break;
        case ',':
        case ')': return;

        case '=':
        case '<':
        case '>': relationLevel(); break;
        case '&':
        case '|': booleanLevel(); break;
        default : throw new ParserException(OPERATOR_EXPECTED);
      }
    } while (true);
  }

  /**
   * Parses subfunction.
   */

  private void parseSubFunction() {
    position = 0;
    postfix_code = "";
    postfix_code_ints = new int[0];
    INRELATION = false; ISBOOLEAN = false;
    try {
      scanAndParse();
    }
    catch (ParserException e) {
      error = e.getErrorCode();
      if ((error == SYNTAX_ERROR) && (postfix_code == ""))
      error = EXPRESSION_EXPECTED;
    }
    if ((error == NO_ERROR) &&
        (position != function.length())) error = PAREN_NOT_MATCH;
  }

  /**
   * Built-in one parameter function call.
   *
   * @return the function result
   * @param  function  the function index
   * @param  parameter the parameter to the function
   */

  private double builtInFunction(int function, double parameter) {
    switch (function) {
      case  0: if (radian)
                 return Math.sin(parameter);
               else
                 return Math.sin(parameter * DEGTORAD);
      case  1: if (radian)
                 return Math.cos(parameter);
               else
                 return Math.cos(parameter * DEGTORAD);
      case  2: if (radian)
                 return Math.tan(parameter);
               else
                 return Math.tan(parameter * DEGTORAD);
      case  3: return Math.log(parameter);
      case  4: return Math.log(parameter) / LOG10;
      case  5: return Math.abs(parameter);
      case  6: return Math.rint(parameter);
      case  7: return parameter-Math.rint(parameter);
      case  8: if (radian)
                 return Math.asin(parameter);
               else
                 return Math.asin(parameter) / DEGTORAD;
      case  9: if (radian)
                 return Math.acos(parameter);
               else
                 return Math.acos(parameter) / DEGTORAD;
      case 10: if (radian)
                 return Math.atan(parameter);
               else
                 return Math.atan(parameter) / DEGTORAD;
      case 11: return (Math.exp(parameter)-Math.exp(-parameter))/2;
      case 12: return (Math.exp(parameter)+Math.exp(-parameter))/2;
      case 13: double a = Math.exp(parameter);
               double b = Math.exp(-parameter);
               return (a-b)/(a+b);
      case 14: return Math.log(parameter + Math.sqrt(parameter * parameter + 1));
      case 15: return Math.log(parameter + Math.sqrt(parameter * parameter - 1));
      case 16: return Math.log((1 + parameter) / (1 - parameter)) / 2;
      case 17: return Math.ceil(parameter);
      case 18: return Math.floor(parameter);
      case 19: return Math.round(parameter);
      case 20: return Math.exp(parameter);
      case 21: return parameter * parameter;
      case 22: return Math.sqrt(parameter);
      case 23: if (parameter == 0.0d)
                 return 0;
               else
               if (parameter > 0.0d)
                 return 1;
               else
                 return -1;
      case 24: if (parameter<0) return 0; else return 1;  // added by W. Christian for step function
      case 25: return parameter*Math.random();  // added by W. Christian for random function
      case 26: if (radian)  // added by W. Christian for sin function for Spanish
         return Math.sin(parameter);
       else
         return Math.sin(parameter * DEGTORAD);

      default: error = CODE_DAMAGED;
               return Double.NaN;
    }
  }

  /**
   * Built-in two parameters extended function call.
   *
   * @return the function result
   * @param  function  the function index
   * @param  param1    the first parameter to the function
   * @param  param2    the second parameter to the function
   */

  private double builtInExtFunction(int function, double param1, double param2) {
    switch (function) {
      case  0: return Math.min(param1,param2);
      case  1: return Math.max(param1,param2);
      case  2: return Math.IEEEremainder(param1,param2);
      case  3: return Math.atan2(param1,param2);
      default: error = CODE_DAMAGED;
               return Double.NaN;
    }
  }

  /**
   * Evaluates subfunction.
   *
   * @return the result of the subfunction
   */

  private double evaluateSubFunction() {
     //   double stack[];  moved by W. Christian
    int    stack_pointer = -1;
    int    code_pointer = 0;
    int    destination;
    int   code;

    //stack = new double[STACK_SIZE];  moved by W. Christian
      int codeLength=postfix_code_ints.length;  // added bt W. Christian to check the length.
      while (true) {
      try {
        if(code_pointer==codeLength) return stack[0]; //added by W. Christian.  No use doing an expection!
        code = postfix_code_ints[code_pointer++];
      }
      catch (StringIndexOutOfBoundsException e) {
        return stack[0];
      }

      try {
        switch (code) {
          case '+': stack[stack_pointer-1] += stack[stack_pointer];
                    stack_pointer--; break;
          case '-': stack[stack_pointer-1] -= stack[stack_pointer];
                    stack_pointer--; break;
          case '*': stack[stack_pointer-1] *= stack[stack_pointer];
                    stack_pointer--; break;
          case '/': if(stack[stack_pointer]!=0)stack[stack_pointer-1] /= stack[stack_pointer];
                    else stack[stack_pointer-1] /= 1.0e-128; // added by W.Christian to trap for divide by zero.
                    stack_pointer--; break;
          case '^': stack[stack_pointer-1] = Math.pow(stack[stack_pointer-1],
                                                      stack[stack_pointer]);
                    stack_pointer--; break;
          case '_': stack[stack_pointer] = -stack[stack_pointer]; break;

          case JUMP_CODE     : destination = code_pointer +
                               postfix_code_ints[code_pointer++];
                               while (code_pointer < destination) {
                                 if (postfix_code_ints[
                                     code_pointer++] == NUMERIC) numberindex++;
                               }
                               break;
          case LESS_THAN     : stack_pointer--;
                               stack[stack_pointer] =
                               (stack[stack_pointer] <
                                stack[stack_pointer+1]) ? 1.0 : 0.0;
                               break;
          case GREATER_THAN  : stack_pointer--;
                               stack[stack_pointer] =
                               (stack[stack_pointer] >
                                stack[stack_pointer+1]) ? 1.0 : 0.0;
                               break;
          case LESS_EQUAL    : stack_pointer--;
                               stack[stack_pointer] =
                               (stack[stack_pointer] <=
                                stack[stack_pointer+1]) ? 1.0 : 0.0;
                               break;
          case GREATER_EQUAL : stack_pointer--;
                               stack[stack_pointer] =
                               (stack[stack_pointer] >=
                                stack[stack_pointer+1]) ? 1.0 : 0.0;
                               break;
          case EQUAL         : stack_pointer--;
                               stack[stack_pointer] =
                               (stack[stack_pointer] ==
                                stack[stack_pointer+1]) ? 1.0 : 0.0;
                               break;
          case NOT_EQUAL     : stack_pointer--;
                               stack[stack_pointer] =
                               (stack[stack_pointer] !=
                                stack[stack_pointer+1]) ? 1.0 : 0.0;
                               break;
          case IF_CODE       : if (stack[stack_pointer--] == 0.0) {
                                 destination = code_pointer +
                                 postfix_code_ints[code_pointer++];
                                 while (code_pointer < destination) {
                                   if (postfix_code_ints[
                                       code_pointer++] == NUMERIC) numberindex++;
                                 }
                               }
                               else code_pointer++;
                               break;
          case ENDIF         : break;  // same as NOP
          case AND_CODE      : stack_pointer--;
                               if ((stack[stack_pointer] != 0.0) &&
                                   (stack[stack_pointer+1] != 0.0))
                                 stack[stack_pointer] = 1.0;
                               else
                                 stack[stack_pointer] = 0.0;
                               break;
          case OR_CODE       : stack_pointer--;
                               if ((stack[stack_pointer] != 0.0) ||
                               (stack[stack_pointer+1] != 0.0))
                                 stack[stack_pointer] = 1.0;
                               else
                                 stack[stack_pointer] = 0.0;
                               break;
          case NOT_CODE      : stack[stack_pointer] =
                               (stack[stack_pointer] == 0.0) ? 1.0 : 0.0;
                               break;

          case NUMERIC       : stack[++stack_pointer] = number[numberindex++]; break;
          case PI_CODE       : stack[++stack_pointer] = Math.PI; break;
          case E_CODE        : stack[++stack_pointer] = Math.E; break;

          default: if ((int)code >= REF_OFFSET)
                     stack[++stack_pointer] = refvalue[(int)code-REF_OFFSET];
                   else
                   if ((int)code >= VAR_OFFSET)
                     stack[++stack_pointer] = var_value[(int)code-VAR_OFFSET];
                   else
                   if ((int)code >= EXT_FUNC_OFFSET) {
                     stack[stack_pointer-1] =
                     builtInExtFunction((int)code-EXT_FUNC_OFFSET,
                                        stack[stack_pointer-1],
                                        stack[stack_pointer]);
                     stack_pointer--;
                   }
                   else
                   if ((int)code >= FUNC_OFFSET) {
                     stack[stack_pointer] =
                     builtInFunction((int)code-FUNC_OFFSET, stack[stack_pointer]);
                   }
                   else {
                     error = CODE_DAMAGED;
                     return Double.NaN;
                   }
        }
      }
      catch (ArrayIndexOutOfBoundsException oe) {
        error = STACK_OVERFLOW;
        return Double.NaN;
      }
      catch (NullPointerException ne) {
        error = CODE_DAMAGED;
        return Double.NaN;
      }
    }
  }
}

