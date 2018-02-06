/*****************************************************************************

JEP - Java Expression Parser
    JEP is a Java package for parsing and evaluating mathematical
	expressions. It currently supports user defined variables,
	constant, and functions. A number of common mathematical
	functions and constants are included.

Author: Nathan Funk
Copyright (C) 2001 Nathan Funk

    JEP is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    JEP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with JEP; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*****************************************************************************/

/**
  * The JEP class is the main interface with which the user should
  * interact. It contains all neccessary methods to parse and evaluate
  * expressions.
  * @author Nathan Funk
  */

package org.nfunk.jep;

import java.io.*;
import org.nfunk.jep.function.*;
import org.netlib.math.complex.*;


public class JEP {

	/** symTab: Symbol Table */
	private SymbolTable symTab;

	/** funTab: Function Table*/
	private FunctionTable funTab;

	/** topNode: Node at the top of the parse Tree*/
	private Node topNode;

	/** traverse: Traverse Option*/
	private boolean traverse;

	/** parser: The parser object*/
	private Parser parser;

	/** hasError: Boolean value; Is set to false
	when expression has been parsed succesfully*/
	private boolean hasError;

	/** lastError: Contains information about the error that occured in
	parsing the last expression*/
	private ParseException lastError;

	/** lastValue: stores the last value calculated */
	private Object lastValue;

	/** Constructor; initializes all internal variables*/
	public JEP()
	{
		topNode = null;
		traverse = false;
		hasError = true;
		lastError = null;
		initSymTab();
		initFunTab();
		parser = new Parser(new StringReader(""));

		//Ensure errors are reported for the initial expression
		//e.g. No expression entered
		parseExpression("");
	}

	/**
	  * Initializes the symbol table
	  */
	public void initSymTab() {
		//Init SymbolTable
		symTab = new SymbolTable();
	}

	/**
	  * Initializes the function table
	  */
	public void initFunTab() {
		//Init FunctionTable
		funTab = new FunctionTable();
	}

	/**
	  * Adds the standard functions to the parser. If this function is not called
		* before parsing an expression, functions such as sin() or cos() would
		* produce an "Unrecognized function..." error.
		* In most cases, this method should be called immediately after the JEP
		* object is created.
	  */
	public void addStandardFunctions()
	{
		//add functions to Function Table
		funTab.put("sin", new Sine());
		funTab.put("cos", new Cosine());
		funTab.put("tan", new Tangent());
		funTab.put("asin", new ArcSine());
		funTab.put("acos", new ArcCosine());
		funTab.put("atan", new ArcTangent());

		funTab.put("sinh", new SineH());
		funTab.put("cosh", new CosineH());
		funTab.put("tanh", new TanH());
		funTab.put("asinh", new ArcSineH());
		funTab.put("acosh", new ArcCosineH());
		funTab.put("atanh", new ArcTanH());

		funTab.put("log", new Logarithm());
		funTab.put("ln", new NaturalLogarithm());

		funTab.put("angle", new Angle());
		funTab.put("abs", new Abs());
		funTab.put("mod", new Modulus());

		funTab.put("rand", new Random());
	}

	/**
	  * Adds the constants pi and e to the parser. As addStandardFunctions(), this
	  * method should be called immediatly after the JEP object is created.
	  */
	public void addStandardConstants()
	{
		//add constants to Symbol Table
		symTab.put("pi", new Double(Math.PI));
		symTab.put("e", new Double(Math.E));
	}

	/**
	  * Call this function if you want to parse expressions which involve
	  * complex numbers. Specifies "i" as the imaginary unit (0,1). Two
	  * functions re() and im() are also added to extract the real or
	  * imaginary components of a complex number respectively.
	  */
	public void addComplex()
	{
		//add constants to Symbol Table
		symTab.put("i", new Complex(0,1));
		funTab.put("re", new Real());
		funTab.put("im", new Imaginary());
	}

	/**
	  * Parses the expression
	  * @param expression_in The input expression string
	  */
	public void parseExpression(String expression_in)
	{
		Reader reader = new StringReader(expression_in);

		try
		{
			topNode = parser.parseStream(reader, symTab, funTab, traverse);
			hasError = false;
		} catch (Throwable e)
		{
			topNode = null;
			hasError = true;
			if (e instanceof ParseException)
				lastError = (ParseException)e;
			else
			    lastError = null;
		}
	}

	/**
	  * Reports whether there is an error in the expression
	  * @return Returns true if the expression has an error
	  */
	public boolean hasError()
	{
		return hasError;
	}

	/**
	  * Reports information on the error in the expression
	  * @return Returns a string containing information on the error
	  */
	public String getErrorInfo()
	{
		if (hasError)
		{
			if (lastError != null)
				return lastError.getErrorInfo();
			else
				return "Illegal character in expression";
		}
		else
			return "";
	}

	/**
	  * Returns the position (vertical) at which the last error occured.
	  */
	public int getErrorColumn()
	{
		if (hasError && lastError != null)
			return lastError.getColumn();
		else
			return 0;
	}

	/**
	  * Returns the line in which the last error occured.
		*/
	public int getErrorLine()
	{
		if (hasError && lastError != null)
			return lastError.getLine();
		else
			return 0;
	}

	/**
	  * Evaluates and returns the value of the expression.
	  * @return The calculated value of the expression
	  */
	public double getValue()
	{
		if (!hasError && topNode != null)
		{
			lastValue = topNode.getValue();

			if (lastValue instanceof Double)
				return ((Double)lastValue).doubleValue();
			else if (lastValue instanceof Complex)
				return ((Complex)lastValue).re();
			else
				return 0;
		}
		else
			return 0;
	}


	/**
	  * Evaluates and returns the value of the expression as a complex number.
	  * @return The calculated value of the expression as a complex number.
	  */
	public Complex getComplexValue()
	{
		if (!hasError && topNode != null)
		{
			lastValue = topNode.getValue();

			if (lastValue instanceof Double)
				return new Complex(((Double)lastValue).doubleValue(), 0);
			else if (lastValue instanceof Complex)
				return (Complex)lastValue;
			else
				return new Complex(0,0);
		}
		else
			return new Complex(0,0);
	}


	/**
	  * Adds a new function to the parser. This must be done before parsing
	  * an expression so the parser is aware that the new function may be
	  * contained in the expression.
	  */
	public void addFunction(String functionName, Object function)
	{
		funTab.put(functionName, function);
	}

	/**
	  * Adds a new variable to the parser, or updates the value of an
	  * existing variable. This must be done before parsing
	  * an expression so the parser is aware that the new variable may be
	  * contained in the expression.
	  * @param name Name of the variable to be added
	  * @param value Initial value or new value for the variable
	  * @return Double object of the variable
	  */
	public Double addVariable(String name, double value)
	{
		Double object = new Double(value);
		symTab.put(name, object);
		return object;
	}


	/**
	  * Adds a new complex variable to the parser, or updates the value of an
	  * existing variable. This must be done before parsing
	  * an expression so the parser is aware that the new variable may be
	  * contained in the expression.
	  * @param name Name of the variable to be added
	  * @param re Initial real value or new real value for the variable
	  * @param re Initial imaginary value or new imaginary value for the variable
	  * @return Complex object of the variable
	  */
	public Complex addComplexVariable(String name, double re, double im){
		Complex object = new Complex(re,im);
		symTab.put(name, object);
		return object;
	}

	/**
	  * Adds a new variable to the parser as an object, or updates the value of an
	  * existing variable. This must be done before parsing
	  * an expression so the parser is aware that the new variable may be
	  * contained in the expression.
	  * @param name Name of the variable to be added
	  * @param object Initial value or new value for the variable
	  */
	public void addVariableAsObject(String name, Object object)
	{
		symTab.put(name, object);
	}



	/**
	  * setTraverse is useful for debugging purposes. When setTraverse(true)
	  * is called, the parse-tree will be traversed with command-line output.
	  * The default setting is traverse = false.
	  */
	public void setTraverse(boolean traverse_in)
	{
		traverse = traverse_in;
	}

/*	public void setVarValue()
	{

	}*/
}

