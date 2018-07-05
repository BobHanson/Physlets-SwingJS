package symantec.itools.beans;

import java.beans.*;
import java.util.Vector;


//  06/06/97    CAR Created
//  06/10/97    CAR Added final static fields to be used by BeanDescriptor.setValue
//  06/11/97    LAB Changed name of innerclass from "ConnectionDescriptor" to "Descriptor"
//					because of the 31 character limit on the Mac.
//  07/01/97    CAR No longer implements the Connections interface
//                  getConnectionDescriptors now returns an array of ConnectionDescriptor
//                  changed add to addConnectionDescriptor
//  08/15/97    CAR added call to setValue in constructor
//  08/27/97    CAR added method addAdditionalConnections to get BeanDescriptor connections
//                  from super classes

/**
 * Extension to java.beans.BeanDescriptor that implements methods for specifying
 * Visual Café integration information for a bean.  Such as the Visual Café toolbar
 * tab name for this bean, Visual Café Interaction Wizard information, etc.
 *
 * @version 1.0, June 6, 1997
 *
 * @author  Symantec
 *
 */
public class SymantecBeanDescriptor extends BeanDescriptor {

    /**
     * A constant that specifies the "can add a child" key.
     */
    public static final String CAN_ADD_CHILD    = "CAN_ADD_CHILD";
    /**
     * A constant that specifies the "flags" key.
     */
    public static final String FLAGS            = "FLAGS";
    /**
     * A constant that specifies the "folder" key.
     */
    public static final String FOLDER           = "FOLDER";
    /**
     * A constant that specifies the "toolbar" key.
     */
    public static final String TOOLBAR          = "TOOLBAR";
    /**
     * A constant that specifies the "winhelp" key.
     */
    public static final String WINHELP          = "WINHELP";
    /**
     * A constant that specifies root template 
     */
    public static final String ROOTTEMPLATE     = "ROOTTEMPLATE";

    /**
     * Constructs a SymantecBeanDescriptor for a bean class.
     * @param beanClass the class of the bean
     */
    public SymantecBeanDescriptor(Class beanClass) {

        super(beanClass);
        connections = new Vector();
        setValue(ConnectionDescriptor.CONNECTIONS, connections);
        setCanAddChild(true);
    }

    /**
     * Create a SymantecBeanDescriptor for a bean that has a customizer.
     * @param beanClass  the class of the bean
     * @param customizerClass  The class of the customizer 
     */
    public SymantecBeanDescriptor(Class beanClass, Class customizerClass) {
        super(beanClass, customizerClass);
        connections = new Vector();
        setValue(ConnectionDescriptor.CONNECTIONS, connections);
        setCanAddChild(true);
    }
        
    /**
     * Gets the ConnectionDescriptors for this bean class.
     * @return an array of ConnectionDescriptors
     */
    public ConnectionDescriptor[] getConnectionDescriptors() {

        ConnectionDescriptor[] rv = new ConnectionDescriptor[connections.size()];
        connections.copyInto(rv);

        return rv;
    }

    /**
     * Adds a ConnectionDescriptor to this bean class.  A ConnectionDescriptor defines
     * an "Interaction Wizard" interaction for this bean.
     * @param c the new ConnectionDescriptor
     * @see symantec.itools.beans.ConnectionDescriptor
     */
    public void addConnectionDescriptor(ConnectionDescriptor c) {

        connections.addElement(c);
    }

    /**
     * Adds any ConnectionDescriptors to this BeanDescriptor which are
     * associated with the BeanDescriptors of the BeanInfos in the array argument.
     * @param bi a BeanInfo array typically returned from a call to
     * BeanInfo.getAdditionalBeanInfo
     */
    public void addAdditionalConnections(BeanInfo[] bi) {

        SymantecBeanDescriptor sbd = null;
        Vector v = null;
        int i = 0;
        while (i < bi.length && bi[i].getBeanDescriptor() instanceof SymantecBeanDescriptor) {
            sbd = (SymantecBeanDescriptor) bi[i].getBeanDescriptor();
            v = (Vector) sbd.getValue(ConnectionDescriptor.CONNECTIONS);
            for (int j = 0; j < v.size(); ++j) {
                if(!connections.contains(v.elementAt(j)))
                    connections.addElement(v.elementAt(j));
            }
            ++i;
        }
    }

    /**
     * Sets the "can add a child" attribute.  Set to false if you have a
     * bean that derives from java.awt.Panel, but you don't want to
     * allow users to drop other components into this bean.
     * @param b the new value of this attribute
     */
    public void setCanAddChild(boolean b) {
        Boolean bObj = new Boolean(b);
        setValue(CAN_ADD_CHILD, bObj);
    }

    /**
     * Sets the "flags" attribute.  Set to "INVISIBLE" for beans that
     * do not have a visual representation at run time.
     * @param f the new value of this attribute
     */
    public void setFlags(String f) {
        setValue(FLAGS, f);
    }

    /**
     * Sets the "folder" attribute.  This is the name of the component library
     * that will contain this bean.
     * @param f the new value of this attribute
     */
    public void setFolder(String f) {
        setValue(FOLDER, f);
    }

    /**
     * Sets the "toolbar" attribute.  This is the name of the toolbar tab that
     * will contain this bean.
     * @param t the new value of this attribute
     */
    public void setToolbar(String t) {
        setValue(TOOLBAR, t);
    }

    /**
     * Sets the "winhelp" attribute.  A Windows help file may be specified for a
     * bean.  However, the help file may not be jarred up with the bean.  The string for
     * this attribute should specify a Windows help system ID number along with the name of the file
     * where the help resides.  The ID and file name are seperated by a comma.
     * @param h the new value of this attribute
     */
    public void setWinHelp(String h) {
        setValue(WINHELP, h);
    }

    /**
     * Sets the "ROOTTEMPLATE" attribute.  
     * @param h the new value of this attribute
     */
    public void setRootTemplate(String h) {
        setValue(ROOTTEMPLATE, h);
    }

    private Vector connections;
}
 