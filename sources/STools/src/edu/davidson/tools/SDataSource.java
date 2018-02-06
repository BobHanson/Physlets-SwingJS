package edu.davidson.tools;

/**
 * The SDataSource interface enables inter-applet communcation between SApplets.
 * 
 * Limited inter-applet commucation is possible between objects in one applet and objects
 * in another applet if the first object implements the SDataSource interface and the second
 * object implements the SDataListener interface.
 * 
 * A data source calculates data and passes this data to a data connection as an array,
 * <code>double [numPts][numVars]</code>.
 * 
 * Inter-applet communication is established using JavaScript to invoke
 * </code>doucment.appletname.makeDataConnection( int sid, int lid, int series, Sting xFunction, String yFunction)</code>.
 * 
 * See the Physlets book by Wolfgang Christian and Mario Belloni, Prentice Hall (2000).
 * 
 * @author             Wolfgang Christian
 * @version            Revision: 1.0, Date: 2000/07/17
 */
public interface SDataSource{

/**
   * The values of the variables to be passed to a data connection.
   * 
   * Data sources pass either a single datum or an entire data set.  If a single datum is passed then
   * the first first dimension is one.  For example, if there are 4 variables, then a single datum
   * will be returned with a dimension <code>double[1][4]</code>.
   * 
   * If a data set is passed to a data connection, then the first dimension should be
   * the number of data points.  For example, if there are 100 data points each consisting of
   * 4 variables, then the method should return <code>double[100][4]</code>
   * 
   * @return the data source values
   */
    public double[][] getVariables();

/**
   * The the variable names for each column in data array.
   * 
   * The variable names used by the parser in the data connection.  Variable names should
   * use standard conventions.  For example, a data source for an object undergoing two
   * dimensional motion might define its source variable to be
   * <code>String[] varStrings= new String[]{"t","x","y","vx","vy"}</code>.
   * 
   * @return the data source variables
   */
    public String[]   getVarStrings();

/**
   * A unique identifier for the data source.
   * 
   * Since the identifier is used for interapplet communication, we strongly recommend that
   * the an object's hash code be used as the unique id.
   * <code> id=this.hashCode();</code>
   * 
   * @return the identifier
   * 
   */
    public int getID();

/**
   * Set an object so that it recognizes its parent applet.
   * 
   * A data source must know its parent applet before inter-applet communication can be established.
   * 
   * @param owner the SApplet that created the data source
   */
    public void setOwner(SApplet owner);

/**
   * The SApplet that created the data sources.
   * 
   * @return the SApplet
   */
    public SApplet getOwner();
}

