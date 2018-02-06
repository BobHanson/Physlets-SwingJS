package filters;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.display.*;

/**
 * The Moments Physlet filter calculates the mean and variance of incoming data.
 * It provides the following variables to its data listeners: ave, std, n .
 *
 * Data listeners are always updated whenever a single datum is added using the addDatum method.  However,
 * the behavior for  data sets depends on the autoReplace flag. (A data set contains more than one
 * datum.) If the autoReplace flag is set to false, all the data in an incoming data set will be
 * added to existing data.  If autoReplace is true, the applet will first clear the
 * data, thereby calculating the moments of the new data set.
 *
 * The default value for autoReplace is <code>true</code>.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 0.1, Date: 2000/09/12
 */
public class Moments extends SApplet implements SDataListener, SDataSource  {
    boolean isStandalone = false;
    boolean showControls;
    boolean autoReplace=true;
    String[] varStrings= new String[]{"ave","std","n"};
    double[][] ds=new double[1][3];  // the datasource state variables
    private double sumX=0;
    private double sumXX=0;
    private int numPts=0;
    EtchedBorder controlPanel = new EtchedBorder();
    BorderLayout borderLayout1 = new BorderLayout();
    Button resetBtn = new Button();
    FlowLayout flowLayout1 = new FlowLayout();
    Label numLabel = new Label();
    Panel panel1 = new Panel();
    SInteger numField = new SInteger();
    SNumber stdField = new SNumber();
    Panel panel4 = new Panel();
    Label minLabel3 = new Label();
    SNumber aveField = new SNumber();
    Panel panel5 = new Panel();
    Label minLabel4 = new Label();

    //Get a parameter value
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public Moments() {
    }

    //Initialize the applet
    public void init() {
        try {
            showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        controlPanel.setVisible(showControls);
        addDataListener(this);
        addDataSource(this);
    }

    //Component initialization
    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        resetBtn.setLabel("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resetBtn_actionPerformed(e);
            }
        });
        controlPanel.setLayout(flowLayout1);
        numLabel.setAlignment(2);
        numLabel.setText("#");
        stdField.setEditable(false);
        stdField.setColumns(8);
        stdField.setText("??");
        minLabel3.setText("Std");
        minLabel3.setAlignment(2);
        aveField.setEditable(false);
        aveField.setColumns(8);
        aveField.setText("??");
        minLabel4.setText("Ave");
        minLabel4.setAlignment(2);
        numField.setEditable(false);
        controlPanel.setBackground(Color.lightGray);
        controlPanel.setFillColor(Color.lightGray);
        this.setBackground(Color.lightGray);
        this.add(controlPanel, BorderLayout.CENTER);
        controlPanel.add(resetBtn, null);
        controlPanel.add(panel5, null);
        panel5.add(minLabel4, null);
        panel5.add(aveField, null);
        controlPanel.add(panel4, null);
        panel4.add(minLabel3, null);
        panel4.add(stdField, null);
        controlPanel.add(panel1, null);
        panel1.add(numLabel, null);
        panel1.add(numField, null);
    }

    //Get Applet information
    public String getAppletInfo() {
        return "Moments Physlet written by W. Christian.  Calculates the moments of a distribution.";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        String[][] pinfo =
            {
            {"ShowControls", "boolean", "Show the user interface."},
            };
        return pinfo;
    }

/**
 *  Get the average.
 *
 * @return double the average
 */
    public double getAve(){
        if(numPts<1){
            return 0;
        }
        return sumX/numPts;
    }

/**
 *  Get the number of points.
 *
 * @return double the number of points
 */
    public double getNumPts(){
        return numPts;
    }

/**
 *  Get the mean squared standard deviation.
 *
 * @return double the std
 */
    public double getStd(){
        if(numPts<2){
            return 0;
        }
        return Math.sqrt( (sumXX-sumX*sumX/numPts)/(numPts-1));
    }



/**
 *  Data source method.  DO NOT SCRIPT.
 */
    public double[][] getVariables(){
        if(numPts<1){
            ds[0][0]=0;
            ds[0][1]=0;
            ds[0][2]=0;
            return ds;
        }else if(numPts<2){
            ds[0][0]=sumX/numPts;;
            ds[0][1]=0;
            ds[0][2]=numPts;
            return ds;
        }
        double ave=sumX/numPts;
        ds[0][0]=ave;
        ds[0][1]=Math.sqrt( (sumXX-ave*ave*numPts)/(numPts-1));
        ds[0][2]=numPts;
        return ds;
    }
/**
 *  Data source method.  DO NOT SCRIPT.
 */
    public String[]   getVarStrings() {return varStrings;}
    // public int getID(){return this.hashCode();}   already in superclass
/**
 *  Data source method.  DO NOT SCRIPT.
 */
    public void setOwner(SApplet owner){; }
/**
 *  Data source method.  DO NOT SCRIPT.
 */
    public SApplet getOwner( ){return this;}

/**
 *  Data listener method.  DO NOT SCRIPT.
 */
    synchronized public void addDatum(SDataSource s, int id, double x, double y ){
        sumX +=x;
        sumXX +=x*x;
        numPts++;
        if(showControls && this.getBounds().width>50){
          numField.setValue(numPts);
          aveField.setValue(getAve() );
          stdField.setValue(getStd() );
        }
        this.updateDataConnections();
    }
/**
 *  Data listener method.  DO NOT SCRIPT.
 */
    public void addData(SDataSource s,int id, double x[], double y[] ){
        if(autoReplace){
            sumX=0;
            sumXX=0;
            numPts=0;
        }
        int n=x.length;
        for(int i=0; i<n; i++){
            sumX +=x[i];
            sumXX +=x[i]*x[i];
            numPts++;
        }
       if(showControls && this.getBounds().width>50 ){
          numField.setValue(numPts);
          aveField.setValue(getAve() );
          stdField.setValue(getStd() );
        }
        this.updateDataConnections();
    }

/**
 *    Set all moments to zero.
 *
 */
    public void deleteSeries(int id){
      reset();
    }
/**
 *    Set all  moments to zero if autoReplace flag is true.
 *
 */
    public void clearSeries(int id){
       if(autoReplace) reset();
    }

/**
 *  Reset the initial state.
 *
 *  Set all the moments to zero
 *
 */
    synchronized public void reset(){
        sumX=0;
        sumXX=0;
        numPts=0;
        if(showControls && this.getBounds().width>50 ){
          numField.setValue(0);
          aveField.setValue(0 );
          stdField.setValue(0 );
        }
        updateDataConnections();
    }

 /**
   * Controls the clear function so that data will either replace or add to existing data when the addData method is called.
   *
   * If repalce is set false, all  data in an incoming data set will be
   * added to existing data.  If autoReplace is true, the applet will first clear the
   * old data, thereby calculating the moments of just the new data set.
   *
   * @param  replace  set to <code> true </code> to replace existing data.  set to <code> false </code> to add to existing data.
   */
  public synchronized void setAutoReplaceData(int id, boolean replace){
      autoReplace=replace;
      if(autoReplace) reset();
  }

    void resetBtn_actionPerformed(ActionEvent e) {
        reset();
    }
}