package mathapps;


import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;
import edu.davidson.graphics.*;
import edu.davidson.display.*;


public class FFT2D extends SApplet implements SStepable {
    private Parser parser = null;
   // private int fftRowPts;
    //private int fftColPts;
    private FFT2DTransformer  transformer = new FFT2DTransformer(this);
   // private boolean showDC=true;
    private double localTime=0;

    boolean isStandalone = false;
    double xmin,ymin;
    double xmax, ymax;
    int rowPts,colPts;
    String functionStr;
    String xVariableStr;
    String yVariableStr;
    boolean showControls;
    boolean explicitTime=false;
    boolean validFunction=false;
    double dx=1, dy=1;

    double[][] dataArray= null; //new double[xPts][colPts];  // the datasource state variable

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
    public FFT2D() {
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
        try { rowPts = Integer.parseInt(this.getParameter("XPts", "64"));
            } catch (Exception e) { e.printStackTrace(); }
        try { colPts = Integer.parseInt(this.getParameter("YPts", "64"));
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
        setFunction(functionStr,xVariableStr, yVariableStr);
        setNumPts(rowPts,colPts);  // will also set min and max
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
        FFT2D applet = new FFT2D();
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
 * Decrease the size of the ouptut array by dropping high frequencies.
 *
 * The number of gutter points will determine the number of high frequency points to drop.
 * The resulting grid will be centered about zero frequency.
 *
 * Setting the gutter should be done after the number of grid points has been set.
 * The gutter cannot be greater than the xgrid/2 or the ygrid/2.
 * Zero will disable clipping.
 *
 * @param pts the number of gutter points
 */
  public void setGutter(int pts){
       if(pts<0)pts=0;
       if(transformer.getGutter()!=pts){
          transformer.setGutter(pts);
          if(this.autoRefresh){
            //transformer.setDataArray(dataArray);
          }
       }
  }

/**
 * Set the number of points for the grid.
 *
 * @param nx the number of points on the x grid
 * @param ny the number of points on the y grid
 */
    synchronized public void setNumPts(int nx,int ny){
        if(nx<1){
          System.out.println("Number of x points must be >0.");
        }
        rowPts=Math.max(1,nx);
        if(ny<1){
          System.out.println("Number of y points must be >0.");
        }
        colPts=Math.max(1,ny);
        if(dataArray==null || dataArray.length!=rowPts ||dataArray[0].length!=colPts ) dataArray=new double[rowPts][colPts];  // the datasource state variables
        if(showControls && this.getBounds().width>50 ){
            xNumField.setValue(rowPts);
            yNumField.setValue(colPts);
        }
    }
/**
 *    Set the minimum and maximum values used to evaluate the function.
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
        if(xmax<xmin){  // swap if the min is > max
          System.out.println("X Maximum must be >  minimum.");
          double temp=xmax;
          xmax=xmin;
          xmin=temp;
        }
        if(xmax==xmin){
          System.out.println("X Maximum cannot be =  minimum.");
          xmax=xmax+1.0;
        }
        dx=(xmax-xmin)/(rowPts-1);
        if(ymax<ymin){  // swap if the min is > max
          System.out.println("Y Maximum must be >  minimum.");
          double temp=ymax;
          ymax=ymin;
          ymin=temp;
        }
        if(ymax==ymin){
          System.out.println("Y Maximum cannot be =  minimum.");
          ymax=ymax+1.0;
        }
        if(showControls && this.getBounds().width>50 ){
            xMinField.setValue(xmin);
            xMaxField.setValue(xmax);
            yMinField.setValue(ymin);
            yMaxField.setValue(ymax);
        }
    }

  /**
   * Destroys the thread and clean up resources.
   *
   * Call when the applet is unloaded from memeory and the object is no longer needed.
   *
   */
  public void destroy() {
       transformer.destroy();
       super.destroy();
  }

/**
 *    Evaluate the function on the grid.
 *
 */
    public synchronized void evaluate(){
        double dx=(xmax-xmin)/(rowPts-1);
        double dy=(ymax-ymin)/(colPts-1);
        double xval=xmin;
        double yval=ymin;
        int offset=0;
        for( int i=0; i<rowPts; i++){
            offset=i*colPts;
            for( int j=0; j<colPts; j++){
                dataArray[i][j]=0;
                if(this.explicitTime)dataArray[i][j]=parser.evaluate(xval, yval,localTime);
                  else dataArray[i][j]=parser.evaluate(xval, yval);
                yval+= dy;
            }
            yval=ymin;
            xval+= dx;
        }
        transformer.setDataArray(dataArray);
    }


/**
 * Parse the a function of one variable.
 *
 * @return true if the string is a function of one variable.
 */
    private boolean parseVariables(String string){
        // return true if fuction is valid
        Parser oneFunc = new Parser(2);
        String str=new String(string);
        oneFunc.defineVariable(1,xVariableStr); // define the x variable
        oneFunc.defineVariable(2,yVariableStr); // define the y variable
        oneFunc.define( str.toLowerCase() );
        oneFunc.parse();
        if(oneFunc.getErrorCode() != Parser.NO_ERROR){
            System.out.println("variable 1: "+xVariableStr);
            System.out.println("variable 2: "+yVariableStr);
            System.out.println("Failed to parse f(one,two)): "+str);
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
    private boolean parseTimeVariables(String string){
        // return true if fuction is valid
        Parser twoFunc = new Parser(3);
        String str=new String(string);
        twoFunc.defineVariable(1,xVariableStr); // define the variable
        twoFunc.defineVariable(2,yVariableStr); // define the variable
        twoFunc.defineVariable(3,"t"); // define the variable
        twoFunc.define( str.toLowerCase() );
        twoFunc.parse();
        if(twoFunc.getErrorCode() != Parser.NO_ERROR){
            System.out.println("variable one: "+xVariableStr);
            System.out.println("variable two: "+yVariableStr);
            System.out.println("Failed to parse f(one,two,t)): "+str);
            System.out.println("Parse error in MathFunction: " + twoFunc.getErrorString() +
                   " at function 1, position " + twoFunc.getErrorPosition());
            parseVariables("0"); // set a default function.
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
 * Scale the output array.
 *
 * The amplitude of the output array will be scaled such that the largest
 * value will equal the scale.
 *
 * Setting the scale to zero will disable the scale feature.
 *
 */
  public void setFFTScale(double scale){
    transformer.setFFTScale(scale);
    if(autoRefresh)evaluate();
  }

/**
 * Delete the data connections and set the time to zero.
 *
 */
  public void setDefault(){
      this.deleteDataConnections();
      clock.stopClock();
      clock.setTime(0);
      localTime=0;
      transformer.setDefault();
  }

/**
 * Reset the applet to time =0.
 *
 */
  public void reset(){
      clock.stopClock();
      clock.setTime(0);
      localTime=0;
  }

/**
 * Set the function string and the independent variable.
 *
 * @param function the function string
 * @param variable the independent variable
 *
 * @return true if the string is a valid function.
 */
    public synchronized boolean setFunction( String function,String xvar, String yvar){
      xVariableStr = new String (xvar.trim().toLowerCase() );
      yVariableStr = new String (yvar.trim().toLowerCase() );
      String str=function.trim();
      if(!parseVariables(function.trim()) )parseTimeVariables(function.trim());
      this.setMinMax(xmin,xmax,ymin,ymax);
      if(validFunction && this.showControls){
        this.funcField.setText(str);
        this.functionStr=str;
      }
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
      setFunction(str,xVariableStr, yVariableStr);
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
        localTime=time+dt;
        if(explicitTime) evaluate();

  }

    public synchronized int getFFTID(){
        return transformer.getID();
    }

    public synchronized int getEvenID(){
        return transformer.getEvenID();
    }

    public synchronized int getOddID(){
        return transformer.getOddID();
    }

    synchronized void setBtn_actionPerformed(ActionEvent e) {
        xmin=xMinField.getValue();
        xmax=xMaxField.getValue();
        ymin=yMinField.getValue();
        ymax=yMaxField.getValue();
        rowPts=xNumField.getValue();
        colPts=yNumField.getValue();
        setNumPts(rowPts,colPts);
        setFunctionStr(funcField.getText());  // will set min and max.
        if(!this.explicitTime || !this.clock.isRunning()) evaluate();
        if(this.explicitTime)this.clock.startClock();
    }
}