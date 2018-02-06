package dataGraph;

import java.awt.*;
import edu.davidson.tools.*;

//import com.sun.java.swing.UIManager;
public class DataListener extends SApplet implements SDataListener{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
boolean isStandalone = false;
  int numFields;
  edu.davidson.graphics.EtchedBorder etchedBorder1 = new edu.davidson.graphics.EtchedBorder();
  Panel panel1 = new Panel();
  Panel panel2 = new Panel();
  edu.davidson.display.SNumber fField = new edu.davidson.display.SNumber();
  edu.davidson.display.SNumber gField = new edu.davidson.display.SNumber();
  Label label1 = new Label();
  Label label2 = new Label();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
//Get a parameter value
  
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  
  public DataListener() {
  }
//Initialize the applet
  
  public void init() {
    try { numFields = Integer.parseInt(this.getParameter("NumFields", "2")); } catch (Exception e) { e.printStackTrace(); }
    try {
    jbInit();
    }
    catch (Exception e) {
    e.printStackTrace();
    }
    addDataListener(this);
  }

    public void setOwner(SApplet owner){};
    public SApplet getOwner(){return this;};

//Component initialization
  
  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout2);
    panel2.setLayout(borderLayout1);
    etchedBorder1.setLayout(borderLayout3);
    fField.setText("0");
    gField.setText("0");
    label1.setAlignment(1);
    label1.setText("Func. 2 =");
    label2.setAlignment(1);
    label2.setText("Func. 1 =");
    this.setLayout(flowLayout1);
    this.add(etchedBorder1, null);
    etchedBorder1.add(panel1, BorderLayout.WEST);
    panel1.add(label2, BorderLayout.WEST);
    panel1.add(fField, BorderLayout.CENTER);
    etchedBorder1.add(panel2, BorderLayout.EAST);
    panel2.add(label1, BorderLayout.WEST);
    panel2.add(gField, BorderLayout.CENTER);
  }
//Start the applet
  
  public void start() {
  }
//Stop the applet
  
  public void stop() {
  }
//Get Applet information
  
  public String getAppletInfo() {
    return "Applet Information";
  }
//Get parameter info
  
  public String[][] getParameterInfo() {
    String pinfo[][] = 
    {
      {"NumFields", "int", "Number of output fields.  Must be 1 or 2."},
    };
    return pinfo;
  }

  // SDataListener Interface
    public void addDatum(int id, double x, double y ){
      // System.out.println("In DataListener Applet x:"+x+" y:"+y);
       fField.setValue(x);
       gField.setValue(y);
    }
    public void addDatum(SDataSource s, int id, double x, double y ){addDatum( id,  x,  y );}

    public void addData(int id, double x[], double y[] ){
       int n=x.length;
       fField.setValue(x[n-1]);
       gField.setValue(y[n-1]);
    }
    public void addData(SDataSource s, int id, double x[], double y[] ){addData( id, x,  y );}

    public void deleteSeries(int id){
       fField.setValue(0);
       gField.setValue(0);
    }
    public void clearSeries(int id){
       fField.setValue(0);
       gField.setValue(0);
    }
}

                         
