package mathapps;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;
import edu.davidson.graphics.*;
import edu.davidson.display.*;
//import com.borland.jbcl.layout.*;

public class Analytic2D extends SApplet implements SDataSource, SStepable {
    boolean isStandalone = false;
    double xmin,ymin;
    double xmax, ymax;
    int xPts,yPts;
    String functionStr;
    String xVariableStr;
    String yVariableStr;
    boolean showControls;
    boolean explicitTime=false;
    boolean validFunction=false;
    double dx=1, dy=1;

    String[] varStrings= new String[]{"surfacedata"};
    double[][] ds= null; //new double[xPts][yPts];  // the datasource state variable
    private Parser parser = null;
    EtchedBorder etchedBorder1 = new EtchedBorder();
    Panel panel5 = new Panel();
    Panel panel6 = new Panel();
    Button setBtn = new Button();
    TextField funcField = new TextField();
    Label label1 = new Label();
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
    Label maxLabel1 = new Label();
    Panel buttonPanel1 = new Panel();
    FlowLayout flowLayout2 = new FlowLayout();
    Label numLabel1 = new Label();
    SNumber yMaxField = new SNumber();
    SNumber yMinField = new SNumber();
    Panel panel4 = new Panel();
    Panel panel7 = new Panel();
    Panel panel8 = new Panel();
    SInteger yNumField = new SInteger();
    Label minLabel3 = new Label();
    Label maxLabel = new Label();
    Panel buttonPanel = new Panel();
    FlowLayout flowLayout1 = new FlowLayout();
    Label numLabel = new Label();
    SNumber xMaxField = new SNumber();
    SNumber xMinField = new SNumber();
    Panel panel3 = new Panel();
    Panel panel2 = new Panel();
    Panel panel1 = new Panel();
    SInteger xNumField = new SInteger();
    Label minLabel2 = new Label();

    //Get a parameter value
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    public Analytic2D() {
    }

    //Initialize the applet
    public void init() {
        try {xmin = Double.valueOf(this.getParameter("XMin", "-1")).doubleValue();
            }catch(Exception e) {e.printStackTrace();}
        try {xmax = Double.valueOf(this.getParameter("XMax", "1")).doubleValue();
            } catch (Exception e) { e.printStackTrace(); }
        try {ymin = Double.valueOf(this.getParameter("YMin", "-1")).doubleValue();
            }catch(Exception e) {e.printStackTrace();}
        try {ymax = Double.valueOf(this.getParameter("YMax", "1")).doubleValue();
            } catch (Exception e) { e.printStackTrace(); }
        try { xPts = Integer.parseInt(this.getParameter("XPts", "64"));
            } catch (Exception e) { e.printStackTrace(); }
        try { yPts = Integer.parseInt(this.getParameter("YPts", "64"));
            } catch (Exception e) { e.printStackTrace(); }
        try { functionStr = this.getParameter("Function", "sin(x*y)");
            }catch(Exception e) { e.printStackTrace(); }
        try { xVariableStr = this.getParameter("XVariable", "x");
            } catch(Exception e) { e.printStackTrace();}
        try { yVariableStr = this.getParameter("YVariable", "y");
            } catch(Exception e) { e.printStackTrace();}
        try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
            } catch (Exception e) { e.printStackTrace(); }
        try {jbInit();
            }catch(Exception e) { e.printStackTrace(); }
        this.buttonPanel.setVisible(showControls);

        funcField.setText(functionStr);
        setFuncion(functionStr,xVariableStr, yVariableStr);
        setNumPts(xPts,yPts);  // will also set min and max
        SApplet.addDataSource(this);
        this.clock.addClockListener(this);
    }

    //Component initialization
    private void jbInit() throws Exception {
        etchedBorder1.setBackground(Color.lightGray);
        etchedBorder1.setLayout(verticalFlowLayout1);
        setBtn.setLabel("Set");
        setBtn.setLabel("Set");
        setBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setBtn_actionPerformed(e);
            }
        });
        funcField.setText("textField1");
        label1.setAlignment(2);
        label1.setText("f(x,y,t) = ");
        panel6.setLayout(borderLayout1);
        panel5.setLayout(borderLayout2);
        maxLabel1.setText("Max");
        maxLabel1.setAlignment(2);
        maxLabel1.setText("Y Max");
        maxLabel1.setAlignment(2);
        buttonPanel1.setLayout(flowLayout2);
        buttonPanel1.setBackground(Color.lightGray);
        numLabel1.setAlignment(2);
        numLabel1.setText("#");
        numLabel1.setAlignment(2);
        numLabel1.setText("#");
        yMaxField.setValue(100.0);
        yNumField.setValue(64);
        minLabel3.setText("Min");
        minLabel3.setAlignment(2);
        minLabel3.setAlignment(2);
        minLabel3.setText("Y Min");
        maxLabel.setAlignment(2);
        maxLabel.setText("Max");
        maxLabel.setAlignment(2);
        maxLabel.setText("X Max");
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setLayout(flowLayout1);
        numLabel.setAlignment(2);
        numLabel.setText("#");
        numLabel.setAlignment(2);
        numLabel.setText("#");
        xMaxField.setValue(100.0);
        xNumField.setValue(64);
        minLabel2.setText("Min");
        minLabel2.setAlignment(2);
        minLabel2.setAlignment(2);
        minLabel2.setText("X Min");
        this.setBackground(Color.lightGray);
        this.add(etchedBorder1, null);
        etchedBorder1.add(buttonPanel, null);
        buttonPanel.add(panel3, null);
        panel3.add(minLabel2, null);
        panel3.add(xMinField, null);
        buttonPanel.add(panel2, null);
        panel2.add(maxLabel, null);
        panel2.add(xMaxField, null);
        buttonPanel.add(panel1, null);
        panel1.add(numLabel, null);
        panel1.add(xNumField, null);
        etchedBorder1.add(buttonPanel1, null);
        buttonPanel1.add(panel4, null);
        panel4.add(minLabel3, null);
        panel4.add(yMinField, null);
        buttonPanel1.add(panel7, null);
        panel7.add(maxLabel1, null);
        panel7.add(yMaxField, null);
        buttonPanel1.add(panel8, null);
        panel8.add(numLabel1, null);
        panel8.add(yNumField, null);
        etchedBorder1.add(panel5, null);
        panel5.add(setBtn, BorderLayout.EAST);
        panel5.add(panel6, BorderLayout.CENTER);
        panel6.add(label1, BorderLayout.WEST);
        panel6.add(funcField, BorderLayout.CENTER);
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
        Analytic applet = new Analytic();
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
 *    Set the number of bins in the histogram.
 *
 */
    synchronized public void setNumPts(int nx,int ny){
        if(nx<1){
          System.out.println("Number of x points must be >0.");
        }
        xPts=Math.max(1,nx);
        if(ny<1){
          System.out.println("Number of y points must be >0.");
        }
        yPts=Math.max(1,ny);
        dx=(xmax-xmin)/(xPts-1);
        dy=(ymax-ymin)/(yPts-1);
        ds=new double[xPts][yPts];  // the datasource state variables
        this.setMinMax(xmin,xmax,ymin,ymax);   // this will update the data connections
        if(showControls && this.getBounds().width>50 ){
            xNumField.setValue(xPts);
            yNumField.setValue(yPts);
        }
    }
/**
 *    Set the minimum and maximum values sorted by the histogram.
 *
 */
    synchronized public void setMinMax(double xmin_, double xmax_,double ymin_, double ymax_){
        if(parser==null || !validFunction){
            System.out.println("Invalid function.");
            return;
        }
        this.xmax=xmax_;
        this.xmin=xmin_;
        this.ymax=ymax_;
        this.ymin=ymin_;
        if(xmax<xmin){  // swap the bins if the min is > the the max
          System.out.println("X Maximum must be >  minimum.");
          double temp=xmax;
          xmax=xmin;
          xmin=temp;
        }
        if(xmax==xmin){
          System.out.println("X Maximum cannot be =  minimum.");
          xmax=xmax+1.0;
        }
        dx=(xmax-xmin)/(xPts-1);
        if(ymax<ymin){  // swap the bins if the min is > the the max
          System.out.println("Y Maximum must be >  minimum.");
          double temp=ymax;
          ymax=ymin;
          ymin=temp;
        }
        if(ymax==ymin){
          System.out.println("Y Maximum cannot be =  minimum.");
          ymax=ymax+1.0;
        }
        evaluate();
        if(showControls && this.getBounds().width>50 ){
            xMinField.setValue(xmin);
            xMaxField.setValue(xmax);
            yMinField.setValue(ymin);
            yMaxField.setValue(ymax);
        }
        this.updateDataConnections();
    }

/**
 *    Evaluate the function on the grid.
 *
 */
    synchronized void evaluate(){
        if(this.explicitTime){
            evaluateExplicitTime();
            return;
        }
        double xval=xmin;
        double yval=ymin;
        for( int j=0; j<yPts; j++){
            for( int i=0; i<xPts; i++){
                ds[i][j]=parser.evaluate(xval, yval);
                xval+= dx;
            }
            xval=xmin;
            yval+= dy;
        }
    }
/**
 *    Evaluate the function on the grid.
 *
 */
    synchronized void evaluateExplicitTime(){
        double time=this.clock.getTime();
        double[] state=new double[3];
        state[2]=time;
        double xval=xmin;
        double yval=ymin;
        for( int j=0; j<yPts; j++){
            for( int i=0; i<xPts; i++){
                ds[i][j]=parser.evaluate(state);
                xval+= dx;
                state[0]=xval;
            }
            xval=xmin;
            yval+= dy;
            state[1]=yval;
        }
    }

/**
 * Parse the a function of one variable.
 *
 * @return true if the string is a function of one variable.
 */
    private boolean parseOneVariable(String string){
        // return true if fuction is valid
        Parser oneFunc = new Parser(2);
        String str=new String(string);
        oneFunc.defineVariable(1,xVariableStr); // define the x variable
        oneFunc.defineVariable(2,yVariableStr); // define the x variable
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
        Parser twoFunc = new Parser(3);
        String str=new String(string);
        twoFunc.defineVariable(1,xVariableStr); // define the variable
        twoFunc.defineVariable(2,yVariableStr); // define the variable
        twoFunc.defineVariable(3,"t"); // define the variable
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
 * Set the function string and the independent variable.
 *
 * @param function the function string
 * @param variable the independent variable
 *
 * @return true if the string is a valid function.
 */
    public boolean setFuncion( String function,String xvar, String yvar){
      xVariableStr = new String (xvar.trim().toLowerCase() );
      yVariableStr = new String (yvar.trim().toLowerCase() );
      String str=function.trim();
      if(!parseOneVariable(str) )parseTwoVariables(str);
      if(ds==null) this.setNumPts(xPts,yPts);
          else this.setMinMax(xmin,xmax,ymin,ymax);
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
      setFuncion(str,xVariableStr, yVariableStr);
      return validFunction;
    }

    public String getFunctionStr(String string){
      return functionStr;
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
        xmin=xMinField.getValue();
        xmax=xMaxField.getValue();
        ymin=yMinField.getValue();
        ymax=yMaxField.getValue();
        setFunctionStr(funcField.getText());
        setNumPts(xNumField.getValue(),yNumField.getValue() );
    }
}