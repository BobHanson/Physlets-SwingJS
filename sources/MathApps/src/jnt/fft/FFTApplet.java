package jnt.fft;

import java.applet.*;
import edu.davidson.graphics.*;
//import com.borland.jbcl.layout.*;
import mathapps.VerticalFlowLayout;

public class FFTApplet extends Applet {
    boolean isStandalone = false;
    EtchedBorder etchedBorder1 = new EtchedBorder();
    VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();

    //Get a parameter value
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public FFTApplet() {
    }

    //Initialize the applet
    public void init() {
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Component initialization
    private void jbInit() throws Exception {
        this.setLayout(verticalFlowLayout1);
        this.add(etchedBorder1, null);
    }

    //Get Applet information
    public String getAppletInfo() {
        return "Applet Information";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        return null;
    }
}