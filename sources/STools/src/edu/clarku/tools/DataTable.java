////////////////////////////////////////////////////////////////////////////////
//	DataTable.java
//	Macneil Shonle

package edu.clarku.tools;

import java.util.*;
import java.io.*;

/**
 * A DataTable is a series of labeled columns with an arbitrary number
 * of entries. A DataTable can be dynamically resized, similar to a
 * Vector. Unlike a Vector, a DataTable always has doubles as its elements.
 * DataTables are two dimensional, where each column is labeled by a valid
 * Java identifier.
 *
 * The rows and columns of DataTables are zero-based. Columns can be accessed
 * both by label and by index.
 */
public class DataTable {
	/**
	 * Demonstrates the use of the DataTable class by creating a DataTable
	 * and manipulating it.
	 *
	 * @param args the command-line arguments, which are ignored
	 */
	public static void main(String[] args) {
		DataTable dt = new DataTable("x, y, z");

		// add rows
		dt.addRowEntry(new double[] {1.0, 2.0, 3.0});
		dt.addRowEntry(new double[] {4.0, 5.0, 6.0});
		dt.addRowEntry(new double[] {7.0, 8.0, 9.0});
		dt.addRowEntry(new double[] {10.0, 11.0, 12.0});

		// add a new column
		dt.addColumnVariable("q", new double[] {1.1, 2.2, 3.3, 4.4});

		// output the table
		System.out.println("" + dt);

		// demonstrate cycling
		dt.setCyclic(true);
		dt.addRowEntry(new double[] {5.0, 5.0, 5.0, 5.0});
		double[] sixes = new double[] {6.0, 6.0, 6.0, 6.0};
		dt.addRowEntry(dt.getRowEntry(dt.getInsertionPoint()));	// skip row
		dt.addRowEntries(new double[][] {sixes, sixes});

		System.out.println("" + dt);
	}

	// names -- The list of variables (column names).
	String[] names = null;
	// values -- The representation of the table itself.
	double[][] values = null;
	boolean cyclic = false;
	int numEntries = 0;
	int numVariables = 0;
	int blockSize = DEFAULT_BLOCK_SIZE;
	// insertion -- The current insertion point in cyclic mode.
	int insertion = 0;

	/**
	 * The default block size for the entries in the table. The default
	 * block size is 50.
	 *
	 * @see #setBlockSize
	 * @see #getBlockSize
	 */
	public static final int DEFAULT_BLOCK_SIZE = 50;

	/**
	 * Creates a new DataTable with the given variables as names for its
	 * columns. The DataTable will have zero entries. The variable names
	 * are specified with the string which is a list of valid Java identifiers.
	 * The identifers can be seperated by any non-letter characters, such as
	 * whitespace or punctuation. The seperators can also appear at the
	 * beginning or end of the variable string.<p>
	 *
	 * The DataTable will have initially no row entries, entries can
	 * be added using the appendRowEntry method (when the table is not in
	 * cyclic mode). The block-size used will be DEFAULT_BLOCK_SIZE. The
	 * table will not initially be in cyclic mode. There must be at least one
	 * column-variable specified.
	 *
	 * @param variables the list of column names (the variables)
	 * @exception MalformedVariableList If the <code>variables</code> String
	 *	is malformed
	 */
	public DataTable(String variables)
		throws MalformedVariableList {
		this.names = scanVariableList(variables);
		if (this.names == null)
			throw new MalformedVariableList("String contains no identifiers.");
		this.numVariables = names.length;
		this.values = null;
	}

	/**
	 * Creates a new DataTable with the given variables as names for its
	 * columns. The DataTable will have <code>initialRowEntries</code> entries,
	 * which will all be set to zero. More entries can be added using the
	 * appendRowEntry method (when the table is not in cyclic mode). The
	 * table will not initially be in cyclic mode.
	 *
	 * The variable String should be formatted as specified in the
	 * DataTable(String) constructor. There must be at least one
	 * column-variable specified.
	 *
	 * @param variables the list of column names (the variables)
	 * @param initialRowEntries the number of zero-initiallized rows the
	 *	table will contain (zero is acceptable)
	 * @exception MalformedVariableList If the <code>variables</code> String
	 *	is malformed
	 * @exception TooManyRows If the value of <code>initialRowEntries</code>
	 *	is negative
	 * @see DataTable#DataTable(String)
	 */
	public DataTable(String variables, int initialRowEntries)
		throws MalformedVariableList, IllegalArgumentException {
		this(variables);
		if (initialRowEntries < 0)
			throw new IllegalArgumentException("Atemp to reinitialize"
				+ " with negative amount of initial entries.");
		else if (initialRowEntries > 0)
			this.values = new double[initialRowEntries][numVariables];
		this.numEntries = initialRowEntries;
	}

	/*
	 * Creates a new DataTable with the given variables as names for its
	 * columns. The DataTable will have <code>initialRowEntries</code> entries,
	 * which will all be set to zero. More entries can be added using the
	 * appendRowEntry method (when the table is not in cyclic mode). The
	 * table will not initially be in cyclic mode. The variable String should
	 * be formatted as specified in the DataTable(String) constructor.<p>
	 *
	 * The table will have <code>blockSize</code> for its block size.
	 *
	 * @param variables the list of column names (the variables)
	 * @param initialRowEntries the number of zero-initiallized rows the
	 *	table will contain (zero is acceptable)
	 * @param blockSize the block-size used for reallocation
	 * @exception MalformedVariableList If the <code>variables</code> String
	 *	is malformed
	 * @exception TooManyRows If the value of <code>initialRowEntries</code>
	 *	is negative
	 * @see DataTable#DataTable(String)
	 */
	public DataTable(String variables, int initialRowEntries, int blockSize)
		throws MalformedVariableList {
		this(variables, initialRowEntries);
		this.blockSize = blockSize;
	}

	/**
	 * Clears all of the information in the DataTable and creates a new
	 * DataTable with the given variables as names for its columns. The
	 * new table will have no entries.<p>
	 *
	 * The table will not be in cyclic mode, though it will have the same
	 * block-size it had before reinitialization.
	 *
	 * @param variables the list of column names (the variables)
	 * @exception MalformedVariableList If the <code>variables</code> String
	 *	is malformed
	 */
	public void reinitialize(String variables)
		throws MalformedVariableList {
		reinitialize(variables, 0);
	}

	/**
	 * Clears all of the information in the DataTable and creates a new
	 * DataTable with the given variables as names for its columns. The
	 * new table will have <code>initialRowEntries</code> entries,
	 * which will all be set to zero. More entries can be added using the
	 * appendRowEntry method (when the table is not in cyclic mode).<p>
	 *
	 * The table will not be in cyclic mode, though it will have the same
	 * block-size it had before reinitialization. If the
	 * <code>initialRowEntries</code> value is negative, an exception will
	 * be thrown and the table will remain untouched.
	 *
	 * @param variables the list of column names (the variables)
	 * @param initialRowEntries the number of zero-initiallized rows the
	 *	table will contain (zero is acceptable)
	 * @exception MalformedVariableList If the <code>variables</code> String
	 *	is malformed
	 * @exception TooManyRows If the value of <code>initialRowEntries</code>
	 *	is negative
	 */
	public void reinitialize(String variables, int initialRowEntries)
		throws MalformedVariableList, IllegalArgumentException {
		if (initialRowEntries < 0)
			throw new IllegalArgumentException("Atemp to reinitialize"
				+ " with negative amount of initial entries.");
		this.names = scanVariableList(variables);
		if (this.names == null)
			throw new MalformedVariableList("String contains no identifiers.");
		this.numVariables = names.length;
		this.values = null;
		if (initialRowEntries > 0)
			ensureNewEntries(initialRowEntries);
		this.numEntries = initialRowEntries;
		setCyclic(false);
	}

	/**
	 * Makes the DataTable go into cyclic mode if <code>becomeCyclic</code> is
	 * true and takes the DataTable out of cyclic mode if
	 * <code>becomeCyclic</code> is false.<p> If <code>becomeCyclic</code>
	 * is true, the insertion point will be moved to the first entry in the
	 * table (if it exists). The insertion point will be movied regardless of
	 * the previous mode the DataTable was in.
	 *
	 * When the table is cyclic, the addRowEntry adds the entry at the
	 * insertion point instead of making room at the end of the table. The
	 * insertion point is then advanced to the next entry. When the insertion
	 * point reaches the end of the table it will be moved back to the first
	 * entry of the table.
	 *
	 * @param becomeCyclic true if the DataTable is to be put in cyclic mode,
	 *	false otherwise.
	 */
	public synchronized void setCyclic(boolean becomeCyclic) {
		if (becomeCyclic)
			insertion = 0;
		cyclic = becomeCyclic;
	}

	/**
	 * Returns true if the DataTable is currently in cyclic mode.
	 *
	 * @return true in cyclic mode, false in non-cyclic mode
	 */
	public synchronized boolean isCyclic() {
		return cyclic;
	}

	/**
	 * Returns the current position of the insertion point in the DataTable,
	 * as used in cyclic mode. The insertion point is the index of the next
	 * entry to be replaced by the appendRowEntry method. The insertion point
	 * only exists in cyclic mode.
	 *
	 * @exception NotInCyclicMode If the DataTable is not cyclic (that is, the
	 *	insertion point does not exist).
	 */
	public synchronized int getInsertionPoint()
		throws NotInCyclicMode {
		if (!isCyclic())
			throw new NotInCyclicMode("No insertion point in non-cyclic mode.");
		return insertion;
	}

	/**
	 * Sets the current insertion point to the value of <code>newPoint</code>.
	 *
	 * @param newPoint the new insertion point
	 * @exception IndexOutOfBoundsException If the index is negative or larger
	 *	than size of the table
	 * @exception NotInCyclicMode If the DataTable is not cyclic (that is, the
	 *	insertion point does not exist).
	 */
	public synchronized void setInsertionPoint(int newPoint)
		throws InvalidTableIndex, NotInCyclicMode {
		if (newPoint < 0)
			throw new InvalidTableIndex("Negative entry index given.");
		else if (newPoint >= numEntries)
			throw new InvalidTableIndex("Table size smaller than index.");
		if (!isCyclic())
			throw new NotInCyclicMode("No insertion point in non-cyclic mode.");
		insertion = newPoint;
	}

	/**
	 * Sets the block size for the next allocation necessary by the table.
	 * When the space allocated for the values fills up the entire table
	 * needs to be reallocated with a larger size. The new table will be
	 * as long as the previous table plus the block size set. The table
	 * will not need to reallocate space until the new block is filled as
	 * well.
	 *
	 * Reallocation is a linear time operation, so it should be avoided as
	 * much as possible.
	 *
	 * @param newSize the new block size
	 * @exception IllegalArgumentException If the new size is non-positive
	 */
	public synchronized void setBlockSize(int newSize)
		throws IllegalArgumentException {
		if (newSize < 1)
			throw new IllegalArgumentException("Non-positive block size!");
		blockSize = newSize;
	}

	/**
	 * Returns the block sized used for new table allocations. The block
	 * size is described in the setBlockSize method.
	 *
	 * @return the current block size to be used for the next allocation
	 * @see #setBlockSize(int)
	 */
	public synchronized int getBlockSize() {
		return blockSize;
	}

	/**
	 * Returns the number of column-variables in the table.
	 *
	 * @return the number of column-variables in the table
	 */
	public synchronized int getNumColumnVariables() {
		return numVariables;
	}

	/**
	 * Returns the number of row-entries in the table.
	 *
	 * @return the number of row-entries in the table
	 */
	public synchronized int getNumRowEntries() {
		return numEntries;
	}

	/**
	 * Returns true if there are no entries currently in the DataTable.
	 *
	 * @return true if there are no entries currently in the DataTable
	 */
	public synchronized boolean isEmpty() {
		return numEntries == 0;
	}

	/**
	 * Sets the <code>i</code>th entry to the given values, the length of
	 * <code>ds</code> must be the same as the number of variables present.
	 * If the DataTable is in cyclic mode the index will be interpretted
	 * modulo the number of entries in the table. The index must be
	 * non-negative.
	 *
	 * @param entry the index of the entry to set
	 * @param ds the values for the entry to be modified
	 * @exception WrongNumberOfVariables If the length of ds is not the same
	 *	as the number of column variables
	 * @exception InvalidTableIndex If the index is out of bounds
	 */
	public synchronized void setRowEntry(int entry, double[] ds)
		throws WrongNumberOfVariables, InvalidTableIndex {
		if (ds.length != numVariables)
			throw new WrongNumberOfVariables("Entry length does not match the"
				+ " number of columns.");
		entry = checkEntryIndex(entry);
		System.arraycopy(ds, 0, values[entry], 0, ds.length);
	}

	/**
	 * Returns a copy of the given row-entry. The array returned will have the
	 * same length as the number of column-variables in the DataTable. If the
	 * DataTable is in cyclic mode the index will be interpretted modulo the
	 * number of entries in the table. The index must be non-negative.
	 *
	 * @param entry the index of the entry to return
	 * @return a copy of the given row-entry
	 * @exception InvalidTableIndex If the index is out of bounds
	 */
	public synchronized double[] getRowEntry(int entry)
		throws InvalidTableIndex {
		entry = checkEntryIndex(entry);
		double[] result = new double[numVariables];
		System.arraycopy(values[entry], 0, result, 0, numVariables);
		return result;
	}

	/**
	 * Adds an empty row entry to the end of the table, regardless if the
	 * table is in cyclic mode. The "empty" row will be empty in that all
	 * the entries will be zeros. The index of the newly added row will be
	 * returned. In cyclic mode, the insertion point will not be affected.
	 *
	 * @return the index of the empty row
	 */
	public synchronized int addEmptyRowEntry() {
		ensureNewEntries(1);
		return numEntries++;
	}

	/**
	 * Adds <i>n</i> row entries to the end of the table, regardless if the
	 * table is in cyclic mode. The "empty" rows will be empty in that all
	 * the entries will be zeros. In cyclic mode, the insertion point will
	 * not be affected.
	 *
	 * @exception IllegalArgumentException If n is non-positive
	 */
	public synchronized void addEmptyRowEntries(int n)
		throws IllegalArgumentException {
		if (n < 1)
			throw new IllegalArgumentException("Cannot add " + n
				+ " empty rows.");
		ensureNewEntries(n);
		numEntries += n;
	}

	/**
	 * Adds another row entry for each variable in the table, the length of
	 * <code>ds</code> must be the same as the number of variables. Throws
	 * WrongNumberOfVariables if ds is not the right length. If the table
	 * is in cyclic mode, a new row will not be added. Instead, a previous
	 * row will get written over and the insertion point will be advanced.
	 *
	 * @param ds the single row to be added
	 * @return the index of the added row
	 * @exception WrongNumberOfVariables If the array is too large or too
	 *	small
	 */
	public synchronized int addRowEntry(double[] ds)
		throws WrongNumberOfVariables {
		int dest;
		if (isCyclic()) {
			dest = insertion++;
			if (insertion >= numEntries)
				insertion = 0;
		}
		else {
			ensureNewEntries(1);
			dest = numEntries++;
		}
		setRowEntry(dest, ds);
		return dest;
	}

	/**
	 * Adds multiple entries by the row to the DataTable. The array passed
	 * is an array of rows, where each row must have the same length as the
	 * number of column variables. Throws WrongNumberOfVariables if dds is not
	 * the right length. If the table is in cyclic mode, the new rows will not
	 * be added. Instead, the previous row will get written over and the
	 * insertion point will be advanced.
	 *
	 * @exception WrongNumberOfVariables If one of the row arrays is too
	 *	big or too small
	 */
	public synchronized void addRowEntries(double[][] dds)
		throws WrongNumberOfVariables {
		if (dds != null && dds.length > 0) {
			if (!isCyclic())
				ensureNewEntries(dds.length);
			for (int i=0; i<dds.length; i++)
				addRowEntry(dds[i]);
		}
	}

	/**
	 * Adds a column variable with the given name to the DataTable. The
	 * column for this variable will be initialized from the given array.
	 * The array cannot be larger than the total number of rows. If the
	 * array is shorter than the total number of rows, the remaining entries
	 * for the new column-variable will be zeros.
	 *
	 * @param name the label for the new column
	 * @param ds the data for the column
	 * @exception MalformedVariableList If the given string doesn't form one
	 *	valid identifier
	 * @exception TooManyRows If <code>ds</code> is too long
	 */
	public synchronized void addColumnVariable(String name, double[] ds)
		throws MalformedVariableList, TooManyRows {
		String[] strings = scanVariableList(name);
		if (strings == null)
			throw new MalformedVariableList("String contains no valid"
				+ " identifiers.");
		if (strings.length != 1)
			throw new MalformedVariableList("Too many identifiers specified.");
		if (ds.length > numEntries)
			throw new TooManyRows();
		for (int i=0; i<numEntries; i++) {
			double[] newEntry = new double[numVariables + 1];
			System.arraycopy(values[i], 0, newEntry, 0, numVariables);
			newEntry[numVariables] = (i >= ds.length) ? 0.0 : ds[i];
			values[i] = newEntry;
		}
		numVariables++;
		String[] newNames = new String[names.length + 1];
		System.arraycopy(names, 0, newNames, 0, names.length);
		newNames[newNames.length - 1] = strings[0];
		this.names = newNames;
	}

	/**
	 * Returns a copy of the <code>n</code>th column in the DataTable.
	 *
	 * @param n the index to the column desired
	 * @return a copy of the <code>n</code>th column
	 * @exception InvalidTableIndex If column <code>n</code> does not exist.
	 */
	public synchronized double[] getColumn(int n) throws InvalidTableIndex {
		if (n < 0 || n >= getNumColumnVariables())
			throw new InvalidTableIndex("Column " + n + " does not exist.");
		double[] result = new double[numEntries];
		for (int i=0; i<numEntries; i++)
			result[i] = values[i][n];
		return result;
	}

	/**
	 * May be larger than the size returned, but no more than the block size
	 *
	 * @return
	 */
	public synchronized double[][] getRowEntries() {
		return values;
	}

	/**
	 * Returns the first one found
	 *
	 * @param variable
	 * @return
	 */
	public synchronized int getColumnIndex(String variable)
		throws InvalidTableIndex {
		for (int i=0; i<names.length; i++)
			if (variable.equals(names[i]))
				return i;
		throw new InvalidTableIndex("No column is named \'" + variable + "\'.");
	}

	public synchronized boolean contains(String variable) {
		for (int i=0; i<names.length; i++)
			if (variable.equals(names[i]))
				return true;
		return false;
	}

	public synchronized String getColumnName(int i)
		throws InvalidTableIndex {
		if (i >= numVariables)
			throw new InvalidTableIndex("Column does not exist.");
		if (i < 0)
			throw new InvalidTableIndex("Negative column index given.");
		return names[i];
	}

	public synchronized String[] getColumnNames() {
		return names;
	}

	/**
	 * Returns the value of the element in row <code>i</code>, column
	 * <code>j</code>. The index <code>i</code> must be less than the
	 * number of rows in the table and the index <code>j</code> must
	 * be less than the number of variables in the table.
	 *
	 * @param i the row-entry index
	 * @param j the column-variable index
	 * @return the value of entry [i, j]
	 * @exception IndexOutOfBoundsException If either of the indicies is out
	 *	of bounds
	 */
	public synchronized double get(int i, int j)
		throws IndexOutOfBoundsException {
		checkDatum(i, j);
		return values[i][j];
	}

	/**
	 * Sets the value of the element in row <code>i</code>, column
	 * <code>j</code> to the given value. The index <code>i</code> must
	 * be less than the number of rows in the table and the index
	 * <code>j</code> must be less than the number of variables in the table.
	 *
	 * @param i the row-entry index
	 * @param j the column-variable index
	 * @param d the new value for entry [i, j]
	 * @exception IndexOutOfBoundsException If either of the indicies is out
	 *	of bounds
	 */
	public synchronized void set(int i, int j, double d)
		throws IndexOutOfBoundsException {
		checkDatum(i, j);
		values[i][j] = d;
	}

	/**
	 * Increments the value of the element in row <code>i</code>, column
	 * <code>j</code> by the given value. The index <code>i</code> must
	 * be less than the number of rows in the table and the index
	 * <code>j</code> must be less than the number of variables in the table.
	 *
	 * This has the same effect as performing:
	 * <pre>
	 *      dataTable.set(i, j, dataTable.get(i, j) + d);
	 * </pre>
	 *
	 * @param i the row-entry index
	 * @param j the column-variable index
	 * @param d the new value for entry [i, j]
	 * @exception IndexOutOfBoundsException If either of the indicies is out
	 *	of bounds
	 */
	public synchronized void increment(int i, int j, double d)
		throws IndexOutOfBoundsException {
		checkDatum(i, j);
	//	System.out.println("i: " + i);
	//	System.out.println("j: " + j);
	//	for (int n=0; n<values.length; n++) {
	//		for (int m=0; m<values[n].length; m++)
	//			System.out.print(values[n][m] + " ");
	//		System.out.println("");
	//	}
	//
		values[i][j] += d;
	}

	/**
	 * Returns a String representation of the DataTable.
	 *
	 * @return a String representation of the DataTable
	 */
	public synchronized String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<names.length; i++) {
			buff.append("\t");
			buff.append(names[i]);
		}
		buff.append("\n");
		for (int r=0; r<numEntries; r++) {
			buff.append(r + ": ");
			for (int c=0; c<numVariables; c++) {
				buff.append("\t");
				buff.append(values[r][c]);
			}
			buff.append("\n");
		}
		return buff.toString();
	}

	// checkEntryIndex -- Throws InvalidTableIndex exceptions with detail
	//	messages for a variety of row entry index problems, such as negative
	//	indecies and out of bounds error with the given index. Returns the
	//	index to use for when the DataTable is in cyclic mode (which will
	//	be i modulo the number of row entries). Returns i when not in
	//	cyclic mode.
	private int checkEntryIndex(int i) throws InvalidTableIndex {
		int result = i;
		if (i < 0)
			throw new InvalidTableIndex("Negative entry index given.");
		if (isEmpty())
			throw new InvalidTableIndex("Table is empty, cannot set entry.");
		if (i >= numEntries)
			if (isCyclic())
				result = i % numEntries;
			else
				throw new InvalidTableIndex("Entry index too large.");
		return result;
	}

	// checkDatum -- Throws an exception if there is no row-entry i or
	//	column-variable j, does nothing otherwise.
	private void checkDatum(int i, int j) throws IndexOutOfBoundsException {
		if (i > numEntries || i < 0)
			throw new IndexOutOfBoundsException("Row Entry " + i
				+ " does not exist.");
		if (j > numVariables || j < 0)
			throw new IndexOutOfBoundsException("Column Variable " + j
				+ " does not exist.");
	}

	// ensureNewEntries -- Expands the DataTable to include n new entries, if
	//	necessary.
	private void ensureNewEntries(int n) {
		if (values == null)
			values = new double[n][numVariables];
		else if (n > values.length - numEntries) {
			int newSize = values.length + Math.max(n, blockSize);
			double[][] newValues = new double[newSize][];
			System.arraycopy(values, 0, newValues, 0, numEntries);
			values = newValues;
			for (int i=numEntries; i<newSize; i++)
				values[i] = new double[numVariables];
		}
	}

	// scanVariableList -- Scans the given String for identifier names and
	//	returns an array of them seperated. May throw MalformedVariableList
	//	if there is a lexical error.
	private String[] scanVariableList(String variables)
		throws MalformedVariableList {
		StreamTokenizer st = new StreamTokenizer(new StringReader(variables));
		Vector list = new Vector();
		try {
			int token = st.nextToken();
			while (token != StreamTokenizer.TT_EOF) {
				if (token == StreamTokenizer.TT_WORD)
					list.addElement(st.sval);
				token = st.nextToken();
			}
		}
		catch (IOException ioe) {}
		if (list.isEmpty())
			return null;
		String[] result = new String[list.size()];
		for (int i=0; i<list.size(); i++)
			result[i] = (String)list.elementAt(i);
		return result;
	}
}