package jnt.fft;

import java.awt.*;
import java.awt.event.*;

import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;
import edu.davidson.graphics.*;
import edu.davidson.display.*;
import mathapps.VerticalFlowLayout;

public class FFT extends SApplet implements SDataListener, SDataSource{
    static final int REALMODE=0;
    static final int SINMODE=1;
    static final int COSMODE=2;
    int transformMode=REALMODE;
    boolean isStandalone = false;
    int fftPts=128;  // the number of points for the FFT.  The function may be evaluated at different points.
    boolean showControls;
    String funcStr;
    String transformType;
    double xmin;
    double xmax;
    double[] x=new double[fftPts+1];   //add 1 so that array is filled x[1..numPts]
    double[] y=new double[fftPts+1];
    double[] fft=new double[fftPts+1];  // the fft is calcuated in place so this array holds the transformed data.

    private Parser function=null;
    String[] varStrings= new String[]{"x","y","n","fft"};
    double[][] variables=new double[fftPts][4];
    EtchedBorder etchedBorder1 = new EtchedBorder();
    Panel panel1 = new Panel();
    Button transBtn = new Button();
    Panel panel2 = new Panel();
    BorderLayout borderLayout1 = new BorderLayout();
    Label label1 = new Label();
    Panel panel4 = new Panel();
    TextField funcField = new TextField();
    BorderLayout borderLayout2 = new BorderLayout();
    VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    EtchedBorder paramPanel = new EtchedBorder();
    Label label3 = new Label();
    Panel panel6 = new Panel();
    SInteger ptsField = new SInteger();
    Label label4 = new Label();
    Panel panel7 = new Panel();
    SNumber minField = new SNumber();
    Label label5 = new Label();
    Panel panel8 = new Panel();
    SNumber maxField = new SNumber();
    FlowLayout flowLayout1 = new FlowLayout();
    FlowLayout flowLayout2 = new FlowLayout();
    Choice choice = new Choice();// the datasource state variables


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
        try { fftPts = Integer.parseInt(this.getParameter("NumPts", "256")); } catch (Exception e) { e.printStackTrace(); }
        try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
          }catch(Exception e) {e.printStackTrace();}
        try {funcStr = this.getParameter("Function", "sin(pi*x)");}
            catch(Exception e) {e.printStackTrace();}
        try {transformType = this.getParameter("Type", "Real");}
            catch(Exception e) { e.printStackTrace();}
        try { xmin = Double.valueOf(this.getParameter("XMin", "0")).doubleValue(); }
            catch (Exception e) { e.printStackTrace(); }
        try { xmax = Double.valueOf(this.getParameter("XMax", "1")).doubleValue();}
           catch(Exception e) {e.printStackTrace();}
        try {jbInit();}
            catch(Exception e) {e.printStackTrace();}
        choice.addItem("Real");
        choice.addItem("Sin");
        choice.addItem("Cos");
        transformType=transformType.toLowerCase();
        if(transformType.equals("sin")){
            choice.select("Sin");
            transformMode=SINMODE;
        }else if(transformType.equals("cos")){
            choice.select("Cos");
            transformMode=COSMODE;
        }else if(transformType.equals("real")){
            choice.select("Real");
            transformMode=REALMODE;
        }
        ptsField.setValue(fftPts);
        funcField.setText(funcStr);
        minField.setValue(xmin);
        maxField.setValue(xmax);
        variables=new double[fftPts][4];  // the datasource state variables
        x=new double[fftPts+1];   //add 1 so that array is filled x[1..numPts]
        y=new double[fftPts+1];
        fft=new double[fftPts+1];
        parseFunction();
        packRealData(ptsField.getValue());
        addDataListener(this);
        addDataSource(this);
        doFFT();
    }

    //Component initialization
    private void jbInit() throws Exception {
        transBtn.setLabel("FFT");
        transBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                transBtn_actionPerformed(e);
            }
        });
        this.setLayout(borderLayout1);
        label1.setAlignment(2);
        label1.setText("F(x)=");
        panel2.setLayout(borderLayout2);
        funcField.setText("textField1");
        panel4.setLayout(verticalFlowLayout1);
        etchedBorder1.setLayout(borderLayout3);
        label3.setText("# Pts");
        label3.setAlignment(2);
        ptsField.setColumns(5);
        label4.setText("Min");
        label4.setAlignment(2);
        minField.setColumns(5);
        minField.setValue(-1.0);
        label5.setText("Max");
        label5.setAlignment(2);
        maxField.setColumns(5);
        maxField.setValue(1.0);
        panel6.setLayout(flowLayout1);
        paramPanel.setLayout(flowLayout2);
        choice.addItemListener(new java.awt.event.ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                choice_itemStateChanged(e);
            }
        });
        this.add(etchedBorder1, BorderLayout.NORTH);
        etchedBorder1.add(panel1, BorderLayout.WEST);
        panel1.add(transBtn, null);
        etchedBorder1.add(panel2, BorderLayout.CENTER);
        panel2.add(label1, BorderLayout.WEST);
        panel2.add(panel4, BorderLayout.CENTER);
        panel4.add(funcField, null);
        this.add(paramPanel, BorderLayout.SOUTH);
        paramPanel.add(choice, null);
        paramPanel.add(panel7, null);
        panel7.add(label4, null);
        panel7.add(minField, null);
        paramPanel.add(panel8, null);
        panel8.add(label5, null);
        panel8.add(maxField, null);
        paramPanel.add(panel6, null);
        panel6.add(label3, null);
        panel6.add(ptsField, null);
    }

    //Get Applet information
    public String getAppletInfo() {
        return "Applet Information";
    }

    //Get parameter info
    public String[][] getParameterInfo() {
        String[][] pinfo =
            {
            {"NumPts", "int", "Number of points"},
            {"ShowControls", "boolean", "Show the unser interface."},
            {"Function", "String", "Function string"},
            {"Type", "String", "Type of tranform"},
            {"XMin", "double", "Minimum value for x"},
            {"XMax", "double", "Maximum value for x"},
            };
        return pinfo;
    }

    private boolean parseFunction(){
        funcStr=funcStr.trim();
        funcStr=funcStr.toLowerCase();
        function = new Parser(1);
        function.defineVariable(1,"x"); // define the variable
        function.define(funcStr);
        function.parse();
        if(function.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse the function in FFT: "+funcStr);
         System.out.println("Parse error: " + function.getErrorString() +
          " at function 1, position " + function.getErrorPosition());
          function=null;
          return false;
         }
         return true;
    }

    private void sinTransform(){
      // FFTransform.sinft(fft,fftPts,1);
       for(int n=0; n<fftPts-1; n++){
        variables[n][2]=n+1;
        variables[n][3]=2*fft[n+2]/fftPts;
       }
       variables[fftPts-1][2]=fftPts;
       variables[fftPts-1][3]=0;
       this.updateDataConnections();
    }

    private void cosTransform(){
       //FFTransform.cosft(fft,fftPts,1);
       for(int n=0; n<fftPts-1; n++){
        variables[n][2]=n+1;
        variables[n][3]=2*fft[n+2]/fftPts;
       }
       variables[fftPts-1][2]=fftPts;
       variables[fftPts-1][3]=0;
       this.updateDataConnections();
    }

    private void realTransform(){
       double cosTemp, sinTemp;
       //FFTransform.realft(fft,fftPts/2,1);
       for(int n=2; n<fftPts/2; n++){
         cosTemp=fft[2*n-1];
         sinTemp=fft[2*n];
         variables[n-2][2]=n-1;
         variables[n-2][3]=2*Math.sqrt(cosTemp*cosTemp+sinTemp*sinTemp)/fftPts;
         //phase=Math.atan2(sinTemp,cosTemp);
       }
       for(int n=fftPts/2; n<fftPts; n++){
         variables[n-2][2]=n-1;
         variables[n-2][3]=0;
       }
       this.updateDataConnections();
    }

/**
 *    Pack the data to get it ready for the transform.
 *
 *    @return returns packed array.
 */
  public void packRealData(int numPts){
      double h=(xmax-xmin)/(numPts-1);
      for(int i=1;i<=fftPts;i++){
        x[i]=(i-1)*h+xmin;
        y[i]=function.evaluate(x[i]);
        fft[i]=y[i];
        variables[i-1][0]=x[i];
        variables[i-1][1]=y[i];
      }
  }

    // data listener methods
    synchronized public void addDatum(SDataSource s, int id, double x, double y ){
      if(function==null && funcStr!=null )parseFunction();
      updateDataConnections();
    }
    public void addData(SDataSource s,int id, double x[], double y[] ){
      if(function==null && funcStr!=null )parseFunction();
      ptsField.setValue(x.length);
      xmin=x[0];
      minField.setValue(xmin);
      int newPts=calcPoints(x.length);
      xmax=x[newPts];
      maxField.setValue(xmax);
      if(newPts!=fftPts){  // check the array size
            fftPts=newPts;
            this.variables=new double[fftPts][4];  // the datasource state variables
            this.x=new double[fftPts+1];   //add 1 so that array is filled x[1..numPts]
            this.y=new double[fftPts+1];
            this.fft=new double[fftPts+1];
      }
      for(int i=1;i<=fftPts;i++){  // copy the data into the array's
        this.x[i]=x[i];
        this.y[i]=y[i];
        fft[i]=y[i];
        variables[i-1][0]=x[i];
        variables[i-1][1]=y[i];
      }
      switch (transformMode){  // do the a appropriate transform
          case REALMODE:
            realTransform();
            break;
          case SINMODE:
            sinTransform();
            break;
          case COSMODE:
            cosTransform();
            break;
      }
      updateDataConnections();
    }
    public void deleteSeries(int id){
      reset();
    }
    public void clearSeries(int id){
     reset();
    }

    // data source methods
    public double[][] getVariables(){
        return variables;
    }
    public String[]   getVarStrings() {return varStrings;}
    // public int getID(){return this.hashCode();}   already in superclass
    public void setOwner(SApplet owner){; }
    public SApplet getOwner( ){return this;}

    private int calcPoints(int n){
      n=Math.max(1,n);
      int pts=1;
      while(n>=pts) pts *= 2;  // keep multiplying until pts is too large
      return pts/2;  // adjust for too many multiplications
    }

    public boolean setFunction(String str){
        funcStr=str;
        funcField.setText(str);
        if(!parseFunction()){
           funcField.setBackground(Color.red);
           return false;
        }
        funcField.setBackground(Color.white);
        return true;
    }

    public void setTransformType(String tt){
        transformType=tt;
        transformType=transformType.toLowerCase();
        if(transformType.equals("sin")){
            choice.select("Sin");
            transformMode=SINMODE;
        }else if(transformType.equals("cos")){
            choice.select("Cos");
            transformMode=COSMODE;
        }else {  // real is the default
            transformType="real";
            choice.select("Real");
            transformMode=REALMODE;
        }
    }

    public void doFFT(){
        int newPts=ptsField.getValue();
        newPts=calcPoints(newPts);
        if(newPts!=fftPts){
            fftPts=newPts;
            variables=new double[fftPts][4];  // the datasource state variables
            x=new double[fftPts+1];   //add 1 so that array is filled x[1..numPts]
            y=new double[fftPts+1];
            fft=new double[fftPts+1];
        }
        if(!parseFunction()){
           funcField.setBackground(Color.red);
           return;
        }else funcField.setBackground(Color.white);
        switch (transformMode){
          case REALMODE:
            packRealData( ptsField.getValue());
            realTransform();
            break;
          case SINMODE:
            packRealData( ptsField.getValue());
            sinTransform();
            break;
          case COSMODE:
            packRealData( ptsField.getValue());
            cosTransform();
            break;
        }
    }

    void transBtn_actionPerformed(ActionEvent e) {
        xmax=maxField.getValue();
        xmin=minField.getValue();
        funcStr=funcField.getText();
        doFFT();
    }

    void choice_itemStateChanged(ItemEvent e) {
        if( choice.getSelectedItem().equals("Real"))
            transformMode=REALMODE;
        else if( choice.getSelectedItem().equals("Sin"))
            transformMode=SINMODE;
        else transformMode=COSMODE;
    }
}