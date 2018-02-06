package org.nfunk.jep.function;

import java.util.*;
import org.nfunk.jep.*;

public class Modulus extends PostfixMathCommand implements PostfixMathCommandI
{
	public Modulus()
	{
		numberOfParameters = 2;
	}
	
	public void run(Stack inStack)
		throws ParseException 
	{
		checkStack(inStack);// check the stack
		Object param2 = inStack.pop();
		Object param1 = inStack.pop();
		
		if ((param1 instanceof Double) && (param2 instanceof Double))
		{
			double divisor = ((Double)inStack.pop()).doubleValue();
			double dividend = ((Double)inStack.pop()).doubleValue();
		
			double result = dividend % divisor;
	
			inStack.push(new Double(result));
		}
		else
		{
			throw new ParseException("Invalid parameter type");
		}
		return;
	}
}