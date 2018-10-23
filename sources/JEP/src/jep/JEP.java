package jep;


import java.awt.*;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.display.*;
import org.netlib.math.complex.Complex;
import edu.davidson.display.VerticalFlowLayout;

/**
 * The Analytic Physlet evaluates a function at a predetermined number of points.
 *
 * The range or the independent variable can be set using the Min and Max embedding
 * parameters.  The number of points can be set with the NumPts embedding parameter.
 *
 * @author             Wolfgang Christian
 * @version            Revision: 0.1, Date: 2000/07/17
 */
public class JEP extends SApplet implements SDataSource, SStepable {
    boolean isStandalone = false;
    double min;
    double max;
    int numPts;
    String functionStr;
    String variableStr;
    boolean showControls;
    boolean explicitTime=false;
    boolean validFunction=false;
    double dx;
    String[] varStrings= new String[]{"x","re","im","n"};
    double[][] ds=null;  // the datasource state variables
    private  JEParser parser;
   // private Parser parser = null;
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
    public JEP() {
    }

    //Initialize the applet
    public void init() {
        try {min = Double.valueOf(this.getParameter("Min", "0")).doubleValue();
            }catch(Exception e) {e.printStackTrace();}
        try { max = Double.valueOf(this.getParameter("Max", "1")).doubleValue();
            } catch (Exception e) { e.printStackTrace(); }
        try { numPts = Integer.parseInt(this.getParameter("NumPts", "100"));
            } catch (Exception e) { e.printStackTrace(); }
        try { functionStr = this.getParameter("Function", "sin(x)/x");
            }catch(Exception e) { e.printStackTrace(); }
        try { variableStr = this.getParameter("Variable", "x");
            } catch(Exception e) { e.printStackTrace();}
        try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
            } catch (Exception e) { e.printStackTrace(); }
        try {jbInit();
            }catch(Exception e) { e.printStackTrace(); }
        this.buttonPanel.setVisible(showControls);
        
        functionStr="1"; // debug with a simple function F(x) = 1 added by WC

        funcField.setText(functionStr);
        setFunction(functionStr,variableStr);
        setNumPts(numPts);  // will also set min and max
        SApplet.addDataSource(this);
        this.clock.addClockListener(this);
    }

    //Component initialization
    private void jbInit() throws Exception {
        setBtn.setLabel("Set");
        setBtn.setBackground(Color.lightGray);
    setBtn.setLabel("Set");
        setBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setBtn_actionPerformed(e);
            }
        });
        maxLabel.setAlignment(2);
        maxLabel.setText("Max");
        maxLabel.setAlignment(2);
    maxLabel.setBackground(Color.lightGray);
        maxLabel.setText("Max");
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setLayout(flowLayout1);
        numLabel.setAlignment(2);
        numLabel.setText("#");
        numLabel.setAlignment(2);
    numLabel.setBackground(Color.lightGray);
        numLabel.setText("#");
        maxField.setValue(100.0);
        numField.setValue(10);
        minLabel2.setText("Min");
        minLabel2.setAlignment(2);
        minLabel2.setAlignment(2);
    minLabel2.setBackground(Color.lightGray);
        minLabel2.setText("Min");
        etchedBorder1.setBackground(Color.lightGray);
        etchedBorder1.setLayout(verticalFlowLayout1);
        label1.setAlignment(2);
    label1.setBackground(Color.lightGray);
        label1.setText("f(x,t) = ");
        funcField.setText("textField1");
        panel4.setLayout(borderLayout1);
        this.setBackground(Color.lightGray);
    panel3.setBackground(Color.lightGray);
    panel2.setBackground(Color.lightGray);
    panel1.setBackground(Color.lightGray);
    panel4.setBackground(Color.lightGray);
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
        return "Analytic Physlet evaluates an analytic function at a specified number of points.";
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
        JEP applet = new JEP();
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
 *    Set the number of points to sample.
 *
 */
    synchronized public void setNumPts(int n){
        if(n<1){
          System.out.println("Number of points must be >0.");
        }
        numPts=Math.max(1,n);
        dx=(max-min)/(numPts-1);
        ds=new double[numPts][4];  // the data source state variables
        this.setMinMax(min,max);   // this will update the data connections
        if(showControls && this.getBounds().width>50 ){
            numField.setValue(numPts);
        }
    }
/**
 *    Set the minimum and maximum values along the x axis.
 *
 */
    synchronized public void setMinMax(double min_, double max_){
        if(parser==null || !validFunction){
            System.out.println("Invalid function.");
            return;
        }
        this.max=max_;
        this.min=min_;
        if(max<min){  // swap if the min is > the the max
          System.out.println("Maximum must be >  minimum.");
          double temp=max;
          max=min;
          min=temp;
        }
        if(max==min){
          System.out.println("Maximum cannot be =  minimum.");
          max=max+1.0;
        }
        dx=(max-min)/(numPts-1);
        double val=min;
        double time=this.clock.getTime();
        for( int i=0; i<numPts; i++){
          ds[i][0]=val;
          Complex z;
          if(this.explicitTime) z=parser.evaluateComplex(val,time);
            else z=parser.evaluateComplex(val);
          ds[i][1]= z.re();
          ds[i][2]= z.im();
          ds[i][3]=i;
          val+= dx;
        }
        if(showControls && this.getBounds().width>50 ){
            minField.setValue(min);
            maxField.setValue(max);
        }
        this.updateDataConnections();
    }


/**
 *  Evaluate the parser at x and return the real part.
 *
 * @param x the independent variable
 */
    public double getReValue(double x) {
      if (parser==null) return 0;
      Complex z;
      if(this.explicitTime) z=parser.evaluateComplex(x,this.clock.getTime());
        else z=parser.evaluateComplex(x);
      return z.re();
    }

/**
 *  Evaluate the parser at x and return the imaginary part.
 *
 * @param x the indpendent variable
 */
    public double getImValue(double x) {
      if (parser==null) return 0;
      Complex z;
      if(this.explicitTime) z=parser.evaluateComplex(x,this.clock.getTime());
        else z=parser.evaluateComplex(x);
      return z.im();
    }

/**
 *    Evaluate the function on the grid.
 *
 */
    synchronized void evaluate(){
        double time=this.clock.getTime();
        for( int i=0; i<numPts; i++){
          Complex z;
          if(this.explicitTime) z=parser.evaluateComplex(ds[i][0],time);
              else z=parser.evaluateComplex(ds[i][0]);
          ds[i][1]= z.re();
          ds[i][2]= z.im();
        }
    }

/**
 * Parse the a function of one variable.
 *
 * @return true if the string is a function of one variable.
 */
    private boolean parseOneVariable(String string){
        // return true if fuction is valid
        JEParser oneFunc = new JEParser (string, variableStr, JEParser.MAKE_COMPLEX);

        //Parser oneFunc = new Parser(1);
        //String str=new String(string);
        //oneFunc.defineVariable(1,variableStr); // define the variable
        //oneFunc.define( str.toLowerCase() );
        //oneFunc.parse();
        if(oneFunc.hasError ()){
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
        // return true if function is valid
        JEParser twoFunc = new JEParser (string, variableStr,"t", JEParser.MAKE_COMPLEX);

        //Parser twoFunc = new Parser(2);
        //String str=new String(string);
        //twoFunc.defineVariable(1,variableStr); // define the variable
        //twoFunc.defineVariable(2,"t"); // define the variable
        //twoFunc.define( str.toLowerCase() );
        //twoFunc.parse();

        if(twoFunc.hasError ()){
            System.out.println("Failed to parse f(x,t)): "+string);
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
 * Reset to time=0.
 *
 */
  public void reset(){
      clock.stopClock();
      clock.setTime(0);
      evaluate();
      updateDataConnections();
  }

  /**
   * Checks to see if a function is syntactically correct.
   * @param func
   * @param vars
   *
   * @return true if function is valid; false otherwise
   */
  public boolean isValidCFunction(String string, String variableStr) {
        // return true if function is valid
        JEParser func = new JEParser (string, variableStr,"t", JEParser.MAKE_COMPLEX);
        if(func.hasError ()){
            return false; // function is not valid
        } else return true;
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

    public void stepForward() {
      stepTimeForward();
    }

    /**
     * Step the time by dt.
     *
     * @see                #setDt
     */
    public void stepTimeForward() {
      if (clock.isRunning()) {
        pause();
        return;
      }
      boolean isNegative = (clock.getDt() < 0);
      clock.setDt(Math.abs(clock.getDt()));
      clock.doStep();

      if (isNegative) {
        clock.setDt(-Math.abs(clock.getDt()));    // make dt negative since it started out that way.
      }
    }

    /**
     * Step the time backward by dt.
     *
     * @see                #setDt
     */
    public void stepTimeBack() {

      if (clock.isRunning()) {
        pause();
        return;
      }
      boolean isNegative = (clock.getDt() < 0);

      clock.setDt(-Math.abs(clock.getDt()));
      clock.doStep();
      if (!isNegative) {
        clock.setDt(Math.abs(clock.getDt()));    // make dt positive since it started out that way.
      }
    }


    public void stepBack() {
        stepTimeBack();
    }


    public void step(double dt,double time){
        if(parser==null || !validFunction){
            System.out.println("Invalid function.");
            return;
        }
        if(explicitTime) evaluate();
        updateDataConnections();

  }

    void setBtn_actionPerformed(ActionEvent e) {
        min=minField.getValue();
        max=maxField.getValue();
        setFunctionStr(funcField.getText());
        setNumPts(numField.getValue() );  // this will set min, max, and update data connections
        System.out.println("re="+getReValue(0));
        System.out.println("im="+getImValue(0));
    }
}