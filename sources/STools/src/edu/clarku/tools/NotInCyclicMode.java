////////////////////////////////////////////////////////////////////////////////
//	NotInCyclicMode.java
//	Macneil Shonle

package edu.clarku.tools;

/**
 * Thrown to indicate that the DataTable was used as if it were in cyclic
 * mode when it was not.
 */
public class NotInCyclicMode extends IllegalArgumentException {
	/** Constructs a NotInCyclicMode exception with no detail message. */
	public NotInCyclicMode() {
		// intentionally left blank
	}
	
	/**
	 * Constructs a NotInCyclicMode exception with the specified detail message.
	 *
	 * @param s the detail message
	 */
	public NotInCyclicMode(String s) {
		super(s);
	}
}