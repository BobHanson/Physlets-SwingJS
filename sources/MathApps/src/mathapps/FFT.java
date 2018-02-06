package mathapps;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;
import edu.davidson.graphics.*;
import edu.davidson.display.*;
//import com.borland.jbcl.layout.*;
import jnt.fft.*;

/**
 * The FFT Physlet evaluates a function at a predetermined number of points and computes the Fast
 * Fourier Transfprm, FFT.
 *
 * The range of the indexpendent variable can be set using the Min and Max embedding
 * parameters.  The number of points can be set with the NumPts parameter.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 0.1, Date: 2000/07/17
 */
public class FFT extends SApplet implements SDataSource, SStepable {
    boolean isStandalone = false;
    double min;
    double max;
    int numPts;
    String functionStr;
    String variableStr;
    boolean showControls;
    boolean explicitTime=false;
    boolean validFunction=false;
    boolean showDC=false;
    double dx;
    String[] varStrings= new String[]{"n","f","fftcos","fftsin","fft"};
    double[][] ds=null;  // the datasource state variables
    double[] data=null;  // the data to be transformed

    private Parser parser = null;
    private int fftPts;
    private RealDoubleFFT_Even  fft = null;
    private double localTime=0;
    private MathFunction function=null;

    EtchedBorder etchedBorder1 = new EtchedBorder();
    Button setBtn = new Button();
    Label maxLabel = new Label();
    FlowLayout flowLayout1 = new FlowLayout();
    Panel buttonPanel = new Panel();
    Label numLabel = new Label();
    SNumber maxField = new SNumber();
    SNumber minField = new SNumber();
    Panel panel3 = new Panel();
    Panel panel2 = new Panel();
    Panel panel1 = new Panel();
    SInteger numField = new SInteger();
    Label minLabel2 = new Label();
    Panel panel4 = new Panel();
    Label label1 = new Label();
    TextField funcField = new TextField();
    BorderLayout borderLayout1 = new BorderLayout();
    VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();

    //Get a parameter value
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public FFT() {
    }

    //Initialize the applet
    public void init() {
        try {min = Double.valueOf(this.getParameter("Min", "-1")).doubleValue();
            }catch(Exception e) {e.printStackTrace();}
        try { max = Double.valueOf(this.getParameter("Max", "1")).doubleValue();
            } catch (Exception e) { e.printStackTrace(); }
        try { numPts = Integer.parseInt(this.getParameter("NumPts", "64"));
            } catch (Exception e) { e.printStackTrace(); }
        try { functionStr = this.getParameter("Function", "sin(x*pi)");
            }catch(Exception e) { e.printStackTrace(); }
        try { variableStr = this.getParameter("Variable", "x");
            } catch(Exception e) { e.printStackTrace();}
        try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
            } catch (Exception e) { e.printStackTrace(); }
        try {jbInit();
            }catch(Exception e) { e.printStackTrace(); }
        this.buttonPanel.setVisible(showControls);
        funcField.setText(functionStr);
        setFunction(functionStr,variableStr);
        setNumPts(numPts);  // will also set min and max
        SApplet.addDataSource(this);
        this.clock.addClockListener(this);
    }

    //Component initialization
    private void jbInit() throws Exception {
        setBtn.setLabel("Set");
        setBtn.setLabel("Set");
        setBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setBtn_actionPerformed(e);
            }
        });
        maxLabel.setAlignment(2);
        maxLabel.setText("Max");
        maxLabel.setAlignment(2);
        maxLabel.setText("Max");
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setLayout(flowLayout1);
        numLabel.setAlignment(2);
        numLabel.setText("#");
        numLabel.setAlignment(2);
        numLabel.setText("#");
        maxField.setValue(1.0);
        numField.setValue(64);
        minLabel2.setText("Min");
        minLabel2.setAlignment(2);
        minLabel2.setAlignment(2);
        minLabel2.setText("Min");
        etchedBorder1.setBackground(Color.lightGray);
        etchedBorder1.setLayout(verticalFlowLayout1);
        label1.setAlignment(2);
        label1.setText("f(x,t) = ");
        funcField.setText("textField1");
        panel4.setLayout(borderLayout1);
        verticalFlowLayout1.setAlignment(VerticalFlowLayout.MIDDLE);
    verticalFlowLayout1.setVgap(2);
    verticalFlowLayout1.setVerticalFill(true);
    this.setBackground(Color.lightGray);
        this.add(etchedBorder1, null);
        etchedBorder1.add(buttonPanel, null);
        buttonPanel.add(setBtn, null);
        buttonPanel.add(panel3, null);
        panel3.add(minLabel2, null);
        panel3.add(minField, null);
        buttonPanel.add(panel2, null);
        panel2.add(maxLabel, null);
        panel2.add(maxField, null);
        buttonPanel.add(panel1, null);
        panel1.add(numLabel, null);
        panel1.add(numField, null);
        etchedBorder1.add(panel4, null);
        panel4.add(label1, BorderLayout.WEST);
        panel4.add(funcField, BorderLayout.CENTER);
    }

    //Get Applet information
    public String getAppletInfo() {
        return "FFT Physlet evaluates an analytic function and calculates its fast fourier transform.";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        String[][] pinfo =
            {
            {"Min", "double", "Minimum value"},
            {"Max", "double", "Maximum value"},
            {"NumPts", "int", "Number of Points"},
            {"Function", "String", "The function string"},
            {"Variable", "String", "The independent variable."},
            {"ShowControls", "boolean", "Show the user interface"},
            };
        return pinfo;
    }

    //Main method
    public static void main(String[] args) {
        FFT applet = new FFT();
        applet.isStandalone = true;
        Frame frame;
        frame = new Frame() {

            protected void processWindowEvent(WindowEvent e) {
                super.processWindowEvent(e);
                if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                    System.exit(0);
                }
            }

            public synchronized void setTitle(String title) {
                super.setTitle(title);
                enableEvents(AWTEvent.WINDOW_EVENT_MASK);
            }
        };
        frame.setTitle("Applet Frame");
        frame.add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(400,320);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
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
 *    Set the number of grid points.
 *
 */
    synchronized public void setNumPts(int n){
        if(n<2){
          System.out.println("Number of points must be >1.");
        }
        numPts=Math.max(2,n);
        fftPts=numPts-numPts%2;  // make sure fft points is an even number.  Drop the last point if it is odd.
        dx=(max-min)/(numPts-1);
        ds=new double[fftPts-1][5];  // the datasource state variables
        data=new double[fftPts];
        if(function!=null)function.setNum(fftPts);
        fft = new RealDoubleFFT_Even(fftPts);
        this.setMinMax(min,max);   // this will update the data connections
        if(showControls && this.getBounds().width>50 ){
            numField.setValue(numPts);
            minField.setValue(min);
            maxField.setValue(max);
        }
    }


/**
 *  Show or hide the zero frequency component.
 *
 *  The dc compent will be included in the fft if this flag is true.
 *
 * @param set true to show the dc component
 *
 */
    synchronized public void setShowDC(boolean set){
       if(showDC==set) return;
       showDC=set;
       if(this.autoRefresh){
           evaluate();
           updateDataConnections();
       }
    }

/**
 *    Set the minimum and maximum values sorted by the histogram.
 *
 */
    synchronized public void setMinMax(double min_, double max_){
        if(parser==null || !validFunction){
            System.out.println("Invalid function.");
            return;
        }
        this.max=max_;
        this.min=min_;
        if(max<min){  // swap if the min is > max
          System.out.println("Maximum must be >  minimum.");
          double temp=max;
          max=min;
          min=temp;
        }
        if(max==min){
          System.out.println("Maximum cannot be =  minimum.");
          max=max+1.0;
        }
        double fmin=1/(max-min);
        int numHalf=fftPts/2;
        for( int i=0; i<fftPts-1; i++){
          ds[i][0]=i;// point index
          ds[i][1]=(-numHalf+i+1)*fmin;  // frequency scale
          ds[i][2]=0;  //fft sin terms
          ds[i][3]=0;  //fft cos terms
          ds[i][4]=0;  //fft
        }
        double dx=(max-min)/(numPts-1);
        double val=min;
        if(function!=null)for( int i=0; i<fftPts; i++){
          function.v[i][0]=val;// x
          function.v[i][1]=0;  // y
          val+=dx;
        }
        if(showControls && this.getBounds().width>50 ){
            minField.setValue(min);
            maxField.setValue(max);
        }
        if(this.autoRefresh){
           evaluate();
           updateDataConnections();
       }
    }

/**
 *    Evaluate the function on the grid.
 *
 */
    synchronized void evaluate(){
        double dx=(max-min)/(numPts-1);
        double val=min;
        for( int i=0; i<fftPts; i++){
          if(this.explicitTime)data[i]=parser.evaluate(val,localTime);
            else data[i]=parser.evaluate(val);
          val+=dx;
          if(function!=null)function.v[i][1]=data[i];  // save the y value
        }
        fft.transform(data);
        data=fft.toWraparoundOrder(data);
        if(!showDC)data[0]=0;  // set the dc component to zero.
        int numHalf=fftPts/2;
        for( int i=0; i<numHalf; i++){
          ds[i+numHalf-1][2]=data[2*i         ]/numHalf;  // cos terms
          ds[i        ][2]=data[fftPts-2*i-2]/numHalf;  // cos terms
          ds[i+numHalf-1][3]=data[2*i+1           ]/numHalf;  // sin terms
          ds[i        ][3]=data[fftPts-2*i-1]/numHalf;  // sin terms
          ds[i+numHalf-1][4]=Math.sqrt(ds[i+numHalf-1][2]*ds[i+numHalf-1][2]+ds[i+numHalf-1][3]*ds[i+numHalf-1][3]);  // power spectrum terms
          ds[i        ][4]=Math.sqrt(ds[i][2]*ds[i][2]+ds[i][3]*ds[i][3]);  // power spectrum terms
        }
    }



/**
 * Parse the a function of one variable.
 *
 * @return true if the string is a function of one variable.
 */
    private boolean parseOneVariable(String string){
        // return true if fuction is valid
        Parser oneFunc = new Parser(1);
        String str=new String(string);
        oneFunc.defineVariable(1,variableStr); // define the variable
        oneFunc.define( str.toLowerCase() );
        oneFunc.parse();
        if(oneFunc.getErrorCode() != Parser.NO_ERROR){
            validFunction=false;
            return false; // function is not valid
        }
        validFunction=true;
        explicitTime=false;  // no explicit time
        parser=oneFunc;
        functionStr=string;
        return true;
    }

/**
 * Parse the a function of two variables.
 *
 * @return true if the string is a function of two variables.
 */
    private boolean parseTwoVariables(String string){
        // return true if fuction is valid
        Parser twoFunc = new Parser(2);
        String str=new String(string);
        twoFunc.defineVariable(1,variableStr); // define the variable
        twoFunc.defineVariable(2,"t"); // define the variable
        twoFunc.define( str.toLowerCase() );
        twoFunc.parse();
        if(twoFunc.getErrorCode() != Parser.NO_ERROR){
            System.out.println("Failed to parse f(x,t)): "+str);
            System.out.println("Parse error in MathFunction: " + twoFunc.getErrorString() +
                   " at function 1, position " + twoFunc.getErrorPosition());
            parseOneVariable("0"); // set a default function.
            validFunction=false;
            return false; // function is not valid
        }
        validFunction=true;
        explicitTime=true;  //  explicit time
        parser=twoFunc;
        functionStr=string;
        return true;
    }

/**
 * Set the function string and the independent variable.
 *
 * @param function the function string
 * @param variable the independent variable
 *
 * @return true if the string is a valid function.
 */
    public boolean setFunction( String function,String variable){
      variableStr = new String (variable.trim().toLowerCase() );
      String str=function.trim();
      if(!parseOneVariable(str) )parseTwoVariables(str);
      if(ds==null) this.setNumPts(numPts);
          else this.setMinMax(min,max);
      return validFunction;
    }

/**
 * Set the function string.
 *
 * @param function the function string
 *
 * @return true if the string is a valid function.
 */
    public boolean setFunctionStr(String function){
      String str=function.trim();
      setFunction(str,variableStr);
      return validFunction;
    }

    public String getFunctionStr(String string){
      return functionStr;
    }

   public synchronized int getFunctionID(){
      if(function!=null)return function.getID();
      function=new MathFunction(fftPts);
      double dx=(max-min)/(numPts-1);
      double val=min;
      for( int i=0; i<fftPts; i++){
          function.v[i][0]=val;// x
          if(this.explicitTime)data[i]=parser.evaluate(val,localTime);
            else data[i]=parser.evaluate(val);
          function.v[i][1]=data[i];  // save the y value
          val+=dx;
      }
      return function.getID();
   }

/**
 * Step the time.  DO NOT SCRIPT.
 *
 * This function is called by the animation clock.
 *
 */
    public void step(double dt,double time){
        if(parser==null || !validFunction){
            System.out.println("Invalid function.");
            return;
        }
        localTime=time+dt;
        if(explicitTime)evaluate();
        updateDataConnections();

  }

/**
 * Inhibit or enable the fft calcuation.
 *
 * Inhibit  auto refresh if a number of parameters are to be changed.
 *
 */
  public void setAutoRefresh(boolean refresh){
    if(this.autoRefresh==refresh) return;
    this.autoRefresh=refresh;
    if(refresh)evaluate();
  }

/**
 * Delete the data connections and set the time to zero.
 *
 */
  public synchronized void setDefault(){
      this.deleteDataConnections();
      clock.stopClock();
      clock.setTime(0);
      localTime=0;
      function=null;
  }

/**
 * Reset the applet to time =0.
 *
 */
  public void reset(){
      clock.stopClock();
      clock.setTime(0);
      localTime=0;
      if(this.autoRefresh){
           evaluate();
           updateDataConnections();
      }
  }

    void setBtn_actionPerformed(ActionEvent e) {
        min=minField.getValue();
        max=maxField.getValue();
        this.autoRefresh=false;
        setFunctionStr(funcField.getText());
        this.autoRefresh=true;
        setNumPts(numField.getValue() );
    }

    class MathFunction extends Object implements SDataSource {
      String[] varStrings= new String[]{"x","y"};
      double[][] v=null;


    MathFunction(int num){
      v=new double[num][2];
      try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    void setNum(int num){
      if(v!=null && v.length==num)return;
      v=new double[num][2];
    }

    public void setOwner(SApplet owner){;}
    public SApplet getOwner(){return FFT.this;}

    public String[]   getVarStrings(){ return varStrings;}

    public final int getID(){return hashCode();}

    public double[][] getVariables(){ return v;}

  }  // end of MathFunction
}