package symantec.itools.beans;


//  07/01/97    CAR Created

/**
 * A simple class that encapsulates a bean's Visual Café "connection" information as used
 * by the Interaction Wizard.  A Visual Café connection implies a relationship between
 * objects (or between an object and itself) involving either event notification or data
 * transmission.  The Interaction Wizard allows users to graphically build these relationships
 * between objects and then Visual Café is able to generate the code for the specified
 * relationship based on the underlying connection information encapsulated in the
 * ConnectionDescriptor.
 *
 * @version 1.0, July 1, 1997
 *
 * @author  Symantec
 */
public class ConnectionDescriptor extends java.beans.FeatureDescriptor {

    /**
     * A constant indicating an input connection.
     * @see #setForm
     */
    public static final String INPUT        = "input";
    /**
     * A constant indicating an output connection.
     * @see #setForm
     */
    public static final String OUTPUT       = "output";
    /**
     * A constant used by SymantecBeanDescriptor when specifying connection information.
     * @see symantec.itools.beans.SymantecBeanDescriptor
     */
    public static final String CONNECTIONS  = "CONNECTIONS";

    /**
     * Constructs a default ConnectionDescriptor. Form, type, init, and expr
     * are all empty strings.
     * @see #setForm
     * @see #setType
     * @see #setInit
     * @see #setExpr
     */
    public ConnectionDescriptor() {
        form = "";
        type = "";
        init = "";
        expr = "";
    }

    /**
     * Constructs a ConnectionDescriptor with the given form.
     * Type, init, and expr are all empty strings.
     * @param f the form string
     * @see #setType
     * @see #setInit
     * @see #setExpr
     */
    public ConnectionDescriptor(String f) {
        if (f != INPUT && f != OUTPUT)
            throw new IllegalArgumentException("acceptable values are \"input\" or \"output\"");
        form = f;
        type = "";
        init = "";
        expr = "";
    }

    /**
     * Constructs a ConnectionDescriptor with the given form, type, init, and
     * expr String values.
     * @param f the form string
     * @param t the type string
     * @param i the init string
     * @param e the expression string
     * @param d a short description
     */
    public ConnectionDescriptor(String f, String t, String i, String e, String d) {
        if (f != INPUT && f != OUTPUT)
            throw new IllegalArgumentException("acceptable values are \"input\" or \"output\"");
        form = f;
        type = t;
        init = i;
        expr = e;
        setShortDescription(d);
    }

    /**
     * Gets the current form string.
     * @return the current form
     * @see #setForm
     */
    public String getForm() { return form; }

    /**
     * Sets the form string. The form of a connection is either INPUT or OUTPUT. An output
     * connection defines an interaction that returns data; an input connection defines an
     * interaction that sets data, or initiates execution of a method that doesn't take data.
     * @return the new form
     * @see #getForm
     */
    public void setForm(String f) {
        if (f != INPUT && f != OUTPUT)
            throw new IllegalArgumentException("acceptable values are \"input\" or \"output\"");
        form = f;
    }

    /**
     * Gets the current type string.
     * @return the current type
     * @see #setType
     */
    public String getType() { return type; }

    /**
     * Specifies the Java type of the parameter or return value of this connection.  The most
     * common types are int, boolean, String, and void.
     * @return the new type
     * @see #getType
     */
    public void setType(String t) { type = t; }

    /**
     * Gets the current init string.
     * @return the current init
     * @see #setInit
     */
    public String getInit() { return init; }

    /**
     * Specifies any initialization code that needs to be present prior to the code generated from
     * the connection expression.  This string is usually blank.
     * @return the new init
     * @see #getInit
     * @see #setExpr
     */
    public void setInit(String i) { init = i; }

    /**
     * Gets the current expression string.
     * @return the current expression
     * @see #setExpr
     */
    public String getExpr() { return expr; }

    /**
     * Specifies the code fragment that is used to create this connection.  The following replacement
     * variables are allowed in the code fragment:
     * %name%   the name of the class/bean
     * %class%  full classname of the class/bean
     * %arg%    method argument used for input connection data
     * @return the new expression
     * @see #getExpr
     */
    public void setExpr(String e) { expr = e; }

    private String form;
    private String type;
    private String init;
    private String expr;
}
