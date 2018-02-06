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
package org.nfunk.jep.function;

import java.util.*;
import org.nfunk.jep.*;
import org.netlib.math.complex.*;

public class Power extends PostfixMathCommand implements PostfixMathCommandI
{
	public Power()
	{
		numberOfParameters = 2;
	}
	
	public void run(Stack inStack)
		throws ParseException 
	{
		checkStack(inStack); // check the stack
		
		Object param2 = inStack.pop();
		Object param1 = inStack.pop();
		
		inStack.push(pow(param1, param2));

		return;
	}
	
	public Object pow(Object param1, Object param2)
		throws ParseException
	{
		if (param1 instanceof Double)
		{
			if (param2 instanceof Double)
			{
				return pow((Double)param1, (Double)param2);
			}
			else if (param2 instanceof Complex)
			{
				return pow((Double)param1, (Complex)param2);
			}
		} else if (param1 instanceof Complex)
		{
			if (param2 instanceof Double)
			{
				return pow((Complex)param1, (Double)param2);
			}
			else if (param2 instanceof Complex)
			{
				return pow((Complex)param1, (Complex)param2);
			}
		}

		throw new ParseException("Invalid parameter type");
	}
	

	public Complex pow(Double d1, Double d2)
	{
		Complex c = new Complex(d1.doubleValue(), 0.0);
		
		return Complex.pow(c, d2.doubleValue());
	}
	
	public Complex pow(Complex c1, Complex c2)
	{
		return Complex.pow(c1, c2);
	}
	
	public Complex pow(Complex c, Double d)
	{
		return Complex.pow(c, d.doubleValue());
	}

	public Complex pow(Double d, Complex c)
	{
		return Complex.pow(d.doubleValue(), c);
	}
	
}