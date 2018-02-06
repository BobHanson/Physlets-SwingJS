package filters;

import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;


public final class Differentiator  extends SApplet implements SDataListener, SDataSource{
    //private Object runLock = new Object();
    private String fStr;
    private Parser fun=null;
    private int save=5;   // number of points to save
    String[] varStrings= new String[]{"x","y","d","dd"};
    double[][] variables=new double[1][4];  // the datasource state variables t,x,y,vx,vy,ax,ay;
    double[] x= new double[save];
    double[] y= new double[save];
    int n=0;
    boolean isStandalone = false;


    //Construct the applet

    public Differentiator() {
    }
//Initialize the applet

    public void init() {
        try { varStrings[0] = this.getParameter("Independent", "x"); } catch (Exception e) { e.printStackTrace(); }
        try { varStrings[1] = this.getParameter("Dependent", "y"); } catch (Exception e) { e.printStackTrace(); }
        try { fStr = this.getParameter("Function", varStrings[1]); } catch (Exception e) { e.printStackTrace(); }
        addDataListener(this);
        addDataSource(this);

    }

    private boolean parseFunction(SDataSource s){
        fStr=fStr.trim();
        fStr=fStr.toLowerCase();
        int num=s.getVarStrings().length;
        fun = new Parser(num);
        for(int i=0;i<num;i++){
            fun.defineVariable(1+i,s.getVarStrings()[i]); // define the variable
        }
        fun.define(fStr);
        fun.parse();
        if(fun.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse the derivative function in filters.Differentiator: "+fStr);
         System.out.println("Parse error: " + fun.getErrorString() +
          " at function 1, position " + fun.getErrorPosition());
          fun=null;
          return false;
         }
         return true;
    }

    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
    }

//Get Applet information
    
    public String getAppletInfo() {
        return "Differentiator Physlets";
    }
//Get parameter info
    
    public String[][] getParameterInfo() {
        String pinfo[][] =
    {
      {"Integrand", "String", "Integrand"},
      {"Differential", "String", "Differential"},
    };
        return pinfo;
    }
// data source methods

    public double[][] getVariables(){

        if(n<3){
          variables[0][0]=x[0];
          variables[0][1]=y[0];
          variables[0][2]=0;
          variables[0][3]=0;
          return variables;
        }
        variables[0][0]=x[1];
        variables[0][1]=y[1];

        double v1;
        if((x[0]-x[1])!=0) v1=(y[0]-y[1])/(x[0]-x[1]); // use two points to get first derivative
          else if( (y[0]-y[1])!=0 && (x[0]-x[1])!=0 ) v1=1.0e20*(y[0]-y[1])/Math.abs(y[0]-y[1]);
          else v1=0;

        double v2;
        if((x[1]-x[2])!=0) v2=(y[1]-y[2])/(x[1]-x[2]); // use two points to get first derivative
          else if( (y[1]-y[2])!=0 && (x[1]-x[2])!=0 ) v2=1.0e20*(y[1]-y[2])/Math.abs(y[1]-y[2]);
          else v2=0;

        variables[0][2]=(v1+v2)/2; // smooth

        if((x[0]-x[2])!=0 )
          variables[0][3]=2*(v1 - v2)/((x[0]-x[2])) ;
          else variables[0][3]=0;  // use three point average to get second derivative

        return variables;
    }
    public String[]   getVarStrings() {return varStrings;}
    // public int getID(){return this.hashCode();}   already in superclass
    public void setOwner(SApplet owner){; }
    public SApplet getOwner( ){return this;}

    public void reset(){
      n=0;
      for(int i=0; i<save; i++){
          x[i]=0; y[i]=0;
      }
      updateDataConnections();
    }

// data listener methods
    synchronized public void addDatum(SDataSource s, int id, double xx, double yy ){
      if(xx==x[0]) return;
      double tmp=0;
      if(fun==null && fStr!=null )parseFunction(s);
      for(int i=save-1; i>0; i--){  // shift all the values toward end of array.
          x[i]=x[i-1]; y[i]=y[i-1];
      }
      x[0]=xx;   // store the new values
      y[0]=fun.evaluate(s.getVariables()[0]);

      n++;
      if (n<3){  // can't take a derivative with only one data point.
          return;
      }
      updateDataConnections();
    }
    public void addData(SDataSource s,int id, double x[], double y[] ){
        // add derivative for data later;
        ;
    }
    public void deleteSeries(int id){
      reset();
    }
    public void clearSeries(int id){
     reset();
    }
}

 