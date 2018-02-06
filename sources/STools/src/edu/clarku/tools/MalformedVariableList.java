////////////////////////////////////////////////////////////////////////////////
//	MalformedVariableList.java
//	Macneil Shonle

package edu.clarku.tools;

/**
 * Thrown to indicate that the variable list passed to the DataTable
 * constructor is invalid.
 */
public class MalformedVariableList extends IllegalArgumentException {
	/** Constructs a MalformedVariableList exception with no detail message. */
	public MalformedVariableList() {
		// intentionally left blank
	}
	
	/**
	 * Constructs a MalformedVariableList exception with the specified detail
	 * message.
	 *
	 * @param s the detail message
	 */
	public MalformedVariableList(String s) {
		super(s);
	}
}