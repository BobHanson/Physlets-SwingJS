////////////////////////////////////////////////////////////////////////////////
//	TooManyRows.java
//	Macneil Shonle

package edu.clarku.tools;

/**
 * Thrown to indicate that the data inserted into the DataTable was too long.
 */
public class TooManyRows extends IllegalArgumentException {
	/** Constructs a TooManyRows exception with no detail message. */
	public TooManyRows() {
		// intentionally left blank
	}
	
	/**
	 * Constructs a TooManyRows exception with the specified detail
	 * message.
	 *
	 * @param s the detail message
	 */
	public TooManyRows(String s) {
		super(s);
	}
}