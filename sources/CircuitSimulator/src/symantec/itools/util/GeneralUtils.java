package symantec.itools.util;

import java.lang.IllegalArgumentException;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.text.MessageFormat;

//???RKM??? I would have called this Util.java, but for the bug in the project manager

// 	06/11/97	LAB	Added frameTarget_* varaibles for convenience in classes
//					which need reference to these strings.
// 	07/08/97	LAB	Added checkValidPercent() function
//					Changed GeneralUtils to a "final" class so JIT can inline it.
// 	10/09/97	LAB	Added Added removeCharAtIndex(String string, int index) function.

// Written by Levi Brown and Rod Magnuson 1.1, July 8, 1997.

/**
 * Useful utility functions and constants.
 * @version 1.1, July 8, 1997
 * @author  Symantec
 */
public final class GeneralUtils
{
    /**
     * Do not use, all-static class.
     */
    public GeneralUtils() {
    }

    /**
     * A constant indicating a document should be shown in the current frame.
     * It is the second parameter for the method:
     * java.applet.AppletContext.showDocument(URL, String).
     * It is provided here for general use.
     * @see java.applet.AppletContext#showDocument(java.net.URL, java.lang.String)
     */
     public static String frameTarget_self = "_self";
    /**
     * A constant indicating a document should be shown in the parent frame.
     * It is the second parameter for the method:
     * java.applet.AppletContext.showDocument(URL, String).
     * It is provided here for general use.
     * @see java.applet.AppletContext#showDocument(java.net.URL, java.lang.String)
     */
     public static String frameTarget_parent = "_parent";
    /**
     * A constant indicating a document should be shown in the topmost frame.
     * It is the second parameter for the method:
     * java.applet.AppletContext.showDocument(URL, String).
     * It is provided here for general use.
     * @see java.applet.AppletContext#showDocument(java.net.URL, java.lang.String)
     */
     public static String frameTarget_top = "_top";
    /**
     * A constant indicating a document should be shown in a new unnamed
     * top-level window.
     * It is the second parameter for the method:
     * java.applet.AppletContext.showDocument(URL, String).
     * It is provided here for general use.
     * @see java.applet.AppletContext#showDocument(java.net.URL, java.lang.String)
     */
     public static String frameTarget_blank = "_blank";

	/**
	 * Compares two objects passed in for equality.
	 * Handle null objects.
	 * @param objectA one of the objects to be compared
	 * @param objectB one of the objects to be compared
	 */
	public static boolean objectsEqual(Object objectA,Object objectB)
	{
		if (objectA == null)
			return (objectB == null);

		return objectA.equals(objectB);
	}

	/**
	 * Checks to make sure the percent parameter is in range.
     * @exception IllegalArgumentException
     * if the specified percentage value is unacceptable
	 */
	public static void checkValidPercent(double percent) throws IllegalArgumentException
	{
		if(percent > 1 || percent < 0){
		    Object[] args = { new Double(percent) };
			throw new IllegalArgumentException(MessageFormat.format(errors.getString("InvalidPercent2"), args));
		}
	}

	/**
	 * Remove a character at the given index from the given string
	 * @param String string the string to remove a character from.
	 * @param int index the index of the character to remove.
	 * @return the string without the character at the given index.
	 * If the string is null or empty, null or empty will be returned.
	 * If the index is out of bounds, the orignial string is returned.
	 */
	public static String removeCharAtIndex(String string, int index)
	{
		if(string == null || string == "")
			return string;

		int length		= string.length();

		if(index > length || index < 0)
			return string;

		String left	= index > 0 ? string.substring(0, index) : "";
		String right	= index + 1 < length ? string.substring(index + 1) : "";

		return left + right;
	}

    /**
     * Error strings.
     */
    static protected ResourceBundle errors = ResourceBundle.getBundle("symantec.itools.resources.ErrorsBundle", Control.getControl(Control.FORMAT_CLASS));

}
