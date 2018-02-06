////////////////////////////////////////////////////////////////////////////////
//	STableListener.java
//	Wolfgang Christian
package edu.davidson.tools;
import edu.clarku.tools.DataTable;
/**
 * The STableListener interface specifies that an SDataListener can process Table data.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 1.0, Date: 2000/09/07
 */
public interface STableListener extends SDataListener {

/**
 * Add data from a data table.
 *
 * A data table consists of a two dimensional array of values, double[][], together with parameters that
 * describe the data.
 *
 * The table identifier, tid, is an optional integer parameter that allows a listener to track multiple
 * tables.  This integer is usually assigned starting at 1 for the first table.
 * Some table listeners ignore this parameter since they only track a single table.
 *
 * SDataSource, ds, is an optional parameter that can be used in inter-applet communication to specify
 * the data source that created the table.
 *
 * @param ds the source of the original data
 * @param sid the table identifier
 * @param table the data table
 */
    public void addTable(SDataSource ds,int tid,  DataTable table);

}