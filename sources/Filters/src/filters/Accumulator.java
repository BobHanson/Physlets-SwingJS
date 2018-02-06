package filters;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.display.*;
import java.awt.Toolkit;

/**
 * The Accumulator Physlet stores datum consisting of (x,y) pairs to produce a data set.
 *
 * The number of datum that will be saved can be set using the NumPts embedding parameters.
 *
 * When NumPts have been received from a data connection using the addDatum method.
 * The accumulated data is passed to all data listeners as a single data set using the listener's addData method.
 * If the autoReplaceData flag is set to true, no furhter action is taken untill another data set of
 * NumPts is available.
 *
 * If autoReplace false is set to false, data will be sent whenever a new datum is received.
 * The oldest data will be dropped and new data will be added to the end of the data set after
 * NumPts have been received.  This feature can be used to produce a strip chart if the
 * a data connection send the resulting data to a DataGraph.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 0.1, Date: 2000/07/09
 */
public class Accumulator extends SApplet implements SDataListener, SDataSource  {
    boolean isStandalone = false;
    int numPts=100;
    boolean showControls=true;
    boolean autoReplace=true;

    String[] varStrings= new String[]{"x","y"};
    double[][] ds=null;  // the datasource variables.  Initialize in init() method.

    private int nextIndex=0;
    EtchedBorder controlPanel = new EtchedBorder();
    BorderLayout borderLayout1 = new BorderLayout();
    SNumber yField = new SNumber();
    Panel panel3 = new Panel();
    Label minLabel2 = new Label();
    SNumber xField = new SNumber();
    Panel panel4 = new Panel();
    Label minLabel3 = new Label();
    FlowLayout flowLayout1 = new FlowLayout();
    Button resetBtn = new Button();
    Label numLabel = new Label();
    Panel panel1 = new Panel();
    SInteger numField = new SInteger();
    Toolkit tk=Toolkit.getDefaultToolkit();

    //Get a parameter value
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public Accumulator() {
    }

    //Initialize the applet
    public void init() {
        try {numPts = Integer.parseInt(this.getParameter("NumPts", "100"));
        }catch(Exception e) {e.printStackTrace();}
        try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
        }catch (Exception e) { e.printStackTrace(); }
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        controlPanel.setVisible(showControls);
        setNumPts(numPts);
        addDataListener(this);
        addDataSource(this);
    }

    //Component initialization
    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        minLabel2.setText("Last Y");
        minLabel2.setAlignment(2);
        minLabel3.setText("Last X");
        minLabel3.setAlignment(2);
        controlPanel.setLayout(flowLayout1);
        resetBtn.setLabel("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resetBtn_actionPerformed(e);
            }
        });
        numLabel.setAlignment(2);
        numLabel.setText("Max #");
        xField.setEditable(false);
        xField.setText("??");
        yField.setEditable(false);
        yField.setText("??");
        controlPanel.setBackground(Color.lightGray);
        controlPanel.setFillColor(Color.lightGray);
        this.add(controlPanel, BorderLayout.CENTER);
        controlPanel.add(resetBtn, null);
        controlPanel.add(panel1, null);
        panel1.add(numLabel, null);
        panel1.add(numField, null);
        controlPanel.add(panel4, null);
        panel4.add(minLabel3, null);
        panel4.add(xField, null);
        controlPanel.add(panel3, null);
        panel3.add(minLabel2, null);
        panel3.add(yField, null);
    }

    //Get Applet information
    public String getAppletInfo() {
        return "Accumulator Physlet written by W. Christian.  Accumulate datum to produce a data set.";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        String[][] pinfo =
            {
            {"NumPts", "int", "The number of points to put into data set."},
            {"ShowControls", "boolean", "Show the user interface."},
            };
        return pinfo;
    }

/**
 *    Set the number of points in the data set.
 *
 */
    synchronized public void setNumPts(int n){
        if(n<1){
          System.out.println("Number of points must be >0.");
        }
        numPts=Math.max(1,n);
        ds=new double[numPts][2];  // the datasource state variables
        for( int i=0; i<numPts; i++){
          ds[i][0]=0;
          ds[i][1]=0;
        }
        nextIndex=0;
        if(showControls && this.getBounds().width>50 ){
           xField.setText("??");
           xField.setText("??");
           numField.setValue(0);
        }
        this.updateDataConnections();
    }

/**
 *  Data source method.  DO NOT SCRIPT.
 */
    public double[][] getVariables(){
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
        if(autoReplace){
            ds[nextIndex][0]=x;
            ds[nextIndex][1]=y;
            nextIndex++;
            if(showControls && this.getBounds().width>50 ){
                   xField.setValue(x);
                   yField.setValue(y);
                   numField.setValue(nextIndex);
            }
            if(nextIndex==numPts){
                nextIndex=0;
                this.updateDataConnections();
                return;
            }
       }else{  // autoReplace is false so add data to the end of the data set and always update the connection
            if(nextIndex==0){
                nextIndex=numPts;
                for( int i=0; i<numPts; i++){  // fill the entire array with the first data point
                    ds[i][0]=x;
                    ds[i][1]=y;
                }
            }else for( int i=1; i<numPts; i++){  // shift the existing data
                ds[i-1][0]=ds[i][0];
                ds[i-1][1]=ds[i][1];
            }
            ds[numPts-1][0]=x; // add new data
            ds[numPts-1][1]=y;
            if(showControls && this.getBounds().width>50 ){
                   xField.setValue(x);
                   yField.setValue(y);
                   numField.setValue(nextIndex);
                   tk.sync();
            }
            this.updateDataConnections();
       }
    }
/**
 *  Data listener method.  DO NOT SCRIPT.
 */
    public void addData(SDataSource s,int id, double x[], double y[] ){
       if(showControls && this.getBounds().width>50 ){
            xField.setValue(x[0]);
            yField.setValue(y[0]);
            numField.setValue(nextIndex);
        }
        int n=x.length;
        for(int i=0; i<n; i++){
            if(autoReplace){
                ds[nextIndex][0]=x[i];
                ds[nextIndex][1]=y[i];
                nextIndex++;
                if(nextIndex==numPts){
                    nextIndex=0;
                    this.updateDataConnections();
                    return;
                }
            }
        }
    }

/**
 *  Reset the initial state.
 *
 *  Set all bins to zero.
 *
 */
    synchronized public void reset(){
        for( int i=0; i<numPts; i++){
          ds[i][0]=0;
          ds[i][1]=0;
        }
        nextIndex=0;
        if(showControls && this.getBounds().width>50 ){
           xField.setText("??");
           xField.setText("??");
           numField.setValue(nextIndex);
        }
        updateDataConnections();
    }
/**
 *    Set all bins to zero.
 *
 */
    public void deleteSeries(int id){
      reset();
    }
/**
 *    Set all  bins to zero if autoReplace flag is true.
 *
 */
    public void clearSeries(int id){
       if(autoReplace) reset();
    }

/**
   * Controls the clear function so that data will either replace or add to the histogram when the addData method is called.
   *
   * Whenver a data source sends a complete data set, the histogram can either clear the bins or add the new data to the
   * exisiting data.  The default if for all the data in the bins to be replaced.  Set this flag to <code> true </code> in
   * order to keep the old data and add the new data.
   *
   * Note:  Data sources that supply only a single datum add this datum to existing data.  Use the deleteSeries method to
   * clear exisitng data if the autoReplaceData flag has ben set to false.
   *
   * @param  replace  set to <code> true </code> to replace existing data.  set to <code> false </code> to add to existing data.
   */
  public synchronized void setAutoReplaceData(int id, boolean replace){
      autoReplace=replace;
      if(autoReplace && nextIndex>=numPts) reset();
  }

    void resetBtn_actionPerformed(ActionEvent e) {
        reset();
        tk.beep();
    }
}