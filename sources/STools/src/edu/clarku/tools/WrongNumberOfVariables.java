////////////////////////////////////////////////////////////////////////////////
//	WrongNumberOfVariables.java
//	Macneil Shonle

package edu.clarku.tools;

/**
 * Thrown to indicate that the DataTable was used with the wrong number of
 * variables as required to perform the operation.
 */
public class WrongNumberOfVariables extends IllegalArgumentException {
	/** Constructs a WrongNumberOfVariables exception with no detail message. */
	public WrongNumberOfVariables() {
		// intentionally left blank
	}
	
	/**
	 * Constructs a WrongNumberOfVariables exception with the specified detail
	 * message.
	 *
	 * @param s the detail message
	 */
	public WrongNumberOfVariables(String s) {
		super(s);
	}
}