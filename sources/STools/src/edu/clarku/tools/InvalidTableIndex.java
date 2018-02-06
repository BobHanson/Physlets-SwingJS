////////////////////////////////////////////////////////////////////////////////
//	InvalidTableIndex.java
//	Macneil Shonle

package edu.clarku.tools;

/**
 * Thrown to indicate that a row-entry or column-variable that does not
 * exist was accessed.
 */
public class InvalidTableIndex extends IndexOutOfBoundsException {
	/** Constructs a InvalidTableIndex exception with no detail message. */
	public InvalidTableIndex() {
		// intentionally left blank
	}
	
	/**
	 * Constructs a InvalidTableIndex exception with the specified detail
	 * message.
	 *
	 * @param s the detail message
	 */
	public InvalidTableIndex(String s) {
		super(s);
	}
}