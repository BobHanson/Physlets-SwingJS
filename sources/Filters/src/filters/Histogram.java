package filters;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.display.*;

/**
 * The Histogram Physlet sorts data into bins to form a histogram.
 *
 * The range and number of bins can be set using the Min, Max, and NumBins embedding
 * parameters.
 *
 * Single datum are added to existing bins whereas data sets will either replace
 * the data that has already been sorted or add to existing data depending on the
 * value of the autoReplace flag.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 0.1, Date: 2000/07/09
 */
public class  Histogram extends SApplet implements SDataListener, SDataSource {
    boolean isStandalone = false;
    int numberOfBins;
    double minBin;
    double maxBin;
    double binSize;
    boolean showControls;
    boolean autoReplace=true;

    String[] varStrings= new String[]{"x","y"};
    double[][] ds=null;
    EtchedBorder controlPanel = new EtchedBorder();
    BorderLayout borderLayout1 = new BorderLayout();
    Button setBtn = new Button();
    FlowLayout flowLayout1 = new FlowLayout();
    Panel panel1 = new Panel();
    Label numLabel = new Label();
    SInteger numField = new SInteger();
    Label minLabel1 = new Label();
    SNumber maxField = new SNumber();
    Panel panel2 = new Panel();
    Label minLabel2 = new Label();
    SNumber minField = new SNumber();
    Panel panel3 = new Panel();  // the datasource variables.  Initialize in init() method.

    //Get a parameter value
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public Histogram() {
    }

    //Initialize the applet
    public void init() {
        try {numberOfBins = Integer.parseInt(this.getParameter("NumBins", "10"));
        }catch(Exception e) {e.printStackTrace();}
        try { minBin = Double.valueOf(this.getParameter("Min", "0")).doubleValue();
        }catch (Exception e) { e.printStackTrace(); }
        try {maxBin = Double.valueOf(this.getParameter("Max", "100")).doubleValue();
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
        setNumBins(numberOfBins);
        minField.setValue(minBin);
        maxField.setValue(maxBin);
        numField.setValue(numberOfBins);
        addDataListener(this);
        addDataSource(this);

    }

    //Component initialization
    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        setBtn.setLabel("Set");
        setBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setBtn_actionPerformed(e);
            }
        });
        controlPanel.setLayout(flowLayout1);
        numLabel.setAlignment(2);
        numLabel.setText("#");
        minLabel1.setText("Max");
        minLabel1.setAlignment(2);
        minLabel2.setText("Min");
        minLabel2.setAlignment(2);
        maxField.setValue(100.0);
        numField.setValue(10);
        controlPanel.setBackground(Color.lightGray);
        controlPanel.setFillColor(Color.lightGray);
        this.setBackground(Color.lightGray);
        this.add(controlPanel, BorderLayout.CENTER);
        controlPanel.add(setBtn, null);
        controlPanel.add(panel3, null);
        panel3.add(minLabel2, null);
        panel3.add(minField, null);
        controlPanel.add(panel2, null);
        panel2.add(minLabel1, null);
        panel2.add(maxField, null);
        controlPanel.add(panel1, null);
        panel1.add(numLabel, null);
        panel1.add(numField, null);
    }

    //Get Applet information
    public String getAppletInfo() {
        return "Histogram Physlet written by W. Christian.  Sort data from a data sourct into bins.";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        String[][] pinfo =
            {
            {"NumBins", "int", "Number of bins."},
            {"Min", "double", "Minimum value for data sorting."},
            {"Max", "double", "Maximum value for data sortting."},
            {"ShowControls", "boolean", "Show user inteface."},
            };
        return pinfo;
    }


/**
 *    Set the number of bins in the histogram.
 *
 */
    synchronized public void setNumBins(int n){
        if(n<1){
          System.out.println("Number of bins must be >0.");
        }
        numberOfBins=Math.max(1,n);
        if(maxBin<minBin){
          System.out.println("Bin maximum must be > bin minimum.");
          double temp=maxBin;
          maxBin=minBin;
          minBin=temp;
        }
        if(maxBin==minBin){
          System.out.println("Bin maximum cannot be = bin minimum.");
          maxBin=maxBin+1.0;
        }
        binSize=(maxBin-minBin)/numberOfBins;
        ds=new double[numberOfBins][2];  // the datasource state variables
        double binVal=minBin+binSize/2.0;
        for( int i=0; i<numberOfBins; i++){
          ds[i][0]=binVal;
          binVal+= binSize;
        }
        if(showControls && this.getBounds().width>50 ){
            minField.setValue(minBin);
            maxField.setValue(maxBin);
            numField.setValue(numberOfBins);
        }
        this.updateDataConnections();
    }
/**
 *    Set the minimum and maximum values sorted by the histogram.
 *
 */
    synchronized public void setMinMaxBins(double min, double max){
        maxBin=max;
        minBin=min;
        if(maxBin<minBin){  // swap the bins if the min is > the the max
          System.out.println("Bin maximum must be > bin minimum.");
          double temp=maxBin;
          maxBin=minBin;
          minBin=temp;
        }
        if(maxBin==minBin){
          System.out.println("Bin maximum cannot be = bin minimum.");
          maxBin=maxBin+1.0;
        }
        binSize=(maxBin-minBin)/numberOfBins;
        double binVal=minBin+binSize/2.0;
        for( int i=0; i<numberOfBins; i++){
          ds[i][0]=binVal;
          ds[i][1]=0;
          binVal+= binSize;
        }
        if(showControls && this.getBounds().width>50 ){
            minField.setValue(minBin);
            maxField.setValue(maxBin);
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
       int bin= (int)((x-minBin) / binSize);
       if(bin<0) return;
       if(bin>numberOfBins-1) return;
       ds[bin][1] += y;
       this.updateDataConnections();
    }
/**
 *  Data listener method.  DO NOT SCRIPT.
 */
    public void addData(SDataSource s,int id, double x[], double y[] ){
        if(autoReplace)for( int i=0; i<numberOfBins; i++){
          ds[i][1]=0;
        }
        int n=x.length;
        for(int i=0; i<n; i++){
           int bin= (int)((x[i]-minBin) / binSize);
           if((bin>=0) && (bin<numberOfBins)) ds[bin][1] += y[i];
        }
        this.updateDataConnections();
    }

/**
 *  Reset the initial state.
 *
 *  Set all bins to zero.
 *
 */
    synchronized public void reset(){
        for( int i=0; i<numberOfBins; i++){
          ds[i][1]=0;
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
  }

  void setBtn_actionPerformed(ActionEvent e) {
    minBin=minField.getValue();
    maxBin=maxField.getValue();
    if(numberOfBins!=numField.getValue() )this.setNumBins(numField.getValue());
    else this.setMinMaxBins(minBin,maxBin);
  }
}