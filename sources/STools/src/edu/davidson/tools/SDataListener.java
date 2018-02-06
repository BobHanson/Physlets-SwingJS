package edu.davidson.tools;

/**
 * The SDataListener interface enables inter-applet communcation between SApplets.
 *
 * Limited inter-applet commucation is possible between objects in one applet and objects
 * in another applet if the first object implements the SDataSource interface and the second
 * object implements the SDataListener interface.
 *
 * A data listener receives data from a data connection using using either the addData or the addDatum methods.
 *
 * Inter-applet communication is established using JavaScript to invoke
 * </code>doucment.appletname.makeDataConnection( int sid, int lid, int series, Sting xFunction, String yFunction)</code>.
 *
 * See the Physlets book by Wolfgang Christian and Mario Belloni, Prentice Hall (2000).
 *
 * @author             Wolfgang Christian
 * @version            Revision: 1.0, Date: 2000/07/17
 */
public interface SDataListener{
/**
 * A unique identifier for the data source.
 *
 * Since the identifier is used for interapplet communication, we strongly recommend that
 * the an object's hash code be used as the unique id.
 * <code> id=this.hashCode();</code>
 *
 * @ return the identifier
 */
    public int  getID();

/**
 * Add a single datum.
 *
 * A datum consists of two values, x and y. Note, even though a data source may have many
 * variables, a data connection transforms these values into two values using its parser functions.
 *
 * The series identifier, sid, is an optional integer that can be used to enable a data listener
 * to track multiple data sets.  Some data listners ignore this parameter since they only track a single data set.
 *
 * @param ds the source of the original data
 * @param sid the series identifier
 * @param x the ordinate of the datum
 * @param y the abscissa of the datum
 */
    public void addDatum(SDataSource ds, int sid, double x, double y );

/**
 * Add a data set.
 *
 * A data set consists of arrays of values, x[] and y[]. Note, even though a data source may have many
 * variables, a data connection transforms these values using its parser functions.
 *
 * The series identifier, sid, is an optional integer that can be used to enable a data listener
 * to track multiple data sets.  Some data listners ignore this parameter since they only track a single data set.
 *
 * @param ds the source of the original data
 * @param sid the series identifier
 * @param x the ordinate values of the data set
 * @param y the abscissa values of the data set
 */
    public void addData(SDataSource ds, int sid, double x[], double y[] );

/**
 * Delete a data set.
 *
 * Recover all resources used by a data set.  Call this methid if the dagta set is not
 * likely to be used again.
 *
 * The series identifier, sid, is an optional integer that can be used to enable a data listener
 * to track multiple data sets.  Some data listners ignore this parameter since they only track a single data set.
 *
 * @param sid the series identifier
 */
    public void deleteSeries(int sid);

/**
 * Clear the data so that that data set can be reused.
 *
 * Even though the data is cleared, the data set retains all of its other properties.  For example,
 * the DataGraph Physlets sets the data to zero but retains the data set's drawing color
 * and drawing style.
 *
 * The series identifier, sid, is an optional integer that can be used to enable a data listener
 * to track multiple data sets.  Some data listners ignore this parameter since they only track a single data set.
 *
 * @param sid the series identifier
 */
    public void clearSeries(int sid);

/**
 * Set an object so that it recognizes its parent applet.
 *
 * A data listener must know its owner before inter-applet communication can be established.
 *
 * @param
 */
    public void setOwner(SApplet owner);

/**
 * The SApplet that created the data listener.
 *
 *
 * @return the SApplet
 */
    public SApplet getOwner();   // the parent applet that contains the data listener.
}

