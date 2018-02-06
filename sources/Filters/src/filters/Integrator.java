package filters;


import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;

// definite integral
// see integral for indefinite integral.
public final class Integrator  extends SApplet implements SDataListener, SDataSource{
    //private Object runLock = new Object();
    private String xiStr;
    private Parser xIntegrand=null;
    private String yiStr;
    private Parser yIntegrand=null;
    String[] varStrings= new String[]{"x","y","n","integral","ds"};
    double[][] variables=new double[1][5];  // the datasource state variables t,x,y,vx,vy,ax,ay;
    int n=0;  // the number of data points.
    double sumX=0;
    double sumY=0;
    double lastX=0;
    double lastY=0;
    double lastXIntegrand;
    double lastYIntegrand;
    double dx=0;
    double dy=0;
    double ds=0;
    boolean isStandalone = false;
    int mode=0; // the type of integral.  1=dx, 2=dy, 3=ds.


    //Construct the applet

    public Integrator() {
    }
//Initialize the applet

    public void init() {
        String diffStr="dx";
        try { varStrings[0] = this.getParameter("Independent", "x"); } catch (Exception e) { e.printStackTrace(); }
        //try { varStrings[1] = this.getParameter("Dependent", "y"); } catch (Exception e) { e.printStackTrace(); }
        try { diffStr = this.getParameter("Differential", "dx"); } catch (Exception e) { e.printStackTrace(); }
        try { xiStr = this.getParameter("Integrand", varStrings[1]); } catch (Exception e) { e.printStackTrace(); }
        yiStr= new String(xiStr);
        diffStr=diffStr.toLowerCase();
        if(diffStr.equals("dx")) mode=0;
          else if (diffStr.equals("dy")) mode=1;
          else if (diffStr.equals("ds")) mode=2;
          else {mode=0; System.out.println("Differential parameter invalid. Use dx, dy or ds.");}

        addDataListener(this);
        addDataSource(this);

    }

    private boolean parseXIntegrand(SDataSource s){
        xiStr=xiStr.trim();
        xiStr=xiStr.toLowerCase();
        int num=s.getVarStrings().length;
        xIntegrand = new Parser(num);
        for(int i=0;i<num;i++){
            xIntegrand.defineVariable(1+i,s.getVarStrings()[i]); // define the variable
        }
        xIntegrand.define(xiStr);
        xIntegrand.parse();
        if(xIntegrand.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse the x integrand in filters.Integrator: "+xiStr);
         System.out.println("Parse error: " + xIntegrand.getErrorString() +
          " at function 1, position " + xIntegrand.getErrorPosition());
          xIntegrand=null;
          return false;
         }
         return true;
    }

    private boolean parseYIntegrand(SDataSource s){
        yiStr=yiStr.trim();
        yiStr=yiStr.toLowerCase();
        int num=s.getVarStrings().length;
        yIntegrand = new Parser(num);
        for(int i=0;i<num;i++){
            yIntegrand.defineVariable(1+i,s.getVarStrings()[i]); // define the variable
        }
        yIntegrand.define(yiStr);
        yIntegrand.parse();
        if(yIntegrand.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse the y integrand in filters.Integrator: "+yiStr);
         System.out.println("Parse error: " + yIntegrand.getErrorString() +
          " at function 1, position " + yIntegrand.getErrorPosition());
          yIntegrand=null;
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
        return "Integrator Physlets";
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
        variables[0][0]=lastX;
        variables[0][1]=lastY;
        variables[0][2]=n;
        variables[0][3]=sumX+sumY;
        variables[0][4]=ds;
        return variables;
    }
    public String[]   getVarStrings() {return varStrings;}
    // public int getID(){return this.hashCode();}   already in superclass
    public void setOwner(SApplet owner){; }
    public SApplet getOwner( ){return this;}

    public void reset(){
      sumX=0; sumY=0; n=0;
      updateDataConnections();
    }

    synchronized public void setLineIntegralMode(String xComponent, String yComponent){
        mode=3;
        xIntegrand=null;
        yIntegrand=null;
        xiStr=xComponent;
        yiStr=yComponent;
        sumX=0; sumY=0; n=0;
    }

// data listener methods
    synchronized public void addDatum(SDataSource s, int id, double x, double y ){
      if(xIntegrand==null && xiStr!=null )parseXIntegrand(s);
      if(yIntegrand==null && yiStr!=null )parseYIntegrand(s);
      dx=(x-lastX);
      dy=(y-lastY);
      ds=Math.sqrt(dx*dx+dy*dy);
      double tmp=0;
      if(n>0)switch(mode) {
         case 0:// dx mode
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(s.getVariables()[0]);else break;
            sumX+=(tmp+lastXIntegrand)*dx/2;
            lastXIntegrand=tmp;
            break;
         case 1: //dy
            if(yIntegrand!=null) tmp= yIntegrand.evaluate(s.getVariables()[0]); else break;
            sumY+=(tmp+lastYIntegrand)*dy/2;
            lastYIntegrand=tmp;
            break;
         case 2:  //ds
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(s.getVariables()[0]);else break;
            sumX+=(tmp+lastXIntegrand)*ds/2;
            lastXIntegrand=tmp;
            break;
         case 3:  //line integral
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(s.getVariables()[0]);else break;
            sumX+=(tmp+lastXIntegrand)*dx/2;
            lastXIntegrand=tmp;
            if(yIntegrand!=null) tmp= yIntegrand.evaluate(s.getVariables()[0]); else break;
            sumY+=(tmp+lastYIntegrand)*dy/2;
            lastYIntegrand=tmp;
            break;
         default:
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(s.getVariables()[0]);else break;
            sumX+=(tmp+lastXIntegrand)*dx/2;
            lastXIntegrand=tmp;
      }
      lastX=x;
      lastY=y;
      n++;
      updateDataConnections();
    }
    public void addData(SDataSource s,int id, double x[], double y[] ){
      if(xIntegrand==null && xiStr!=null )parseXIntegrand(s);
      if(yIntegrand==null && yiStr!=null )parseYIntegrand(s);
      sumX=0; sumY=0;
      n=x.length;
      if (n<2)return;
      double tmp=0;
      dx=(x[1]-x[0])/2;   // first point in trapazoidal rule get 1/2 the weight.
      dy=(y[1]-y[0])/2;
      for(int i=0; i<n; i++){
         ds=Math.sqrt(dx*dx+dy*dy);
         switch(mode) {
         case 0:// dx mode
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(s.getVariables()[0]);else break;
            sumX+=tmp*dx;
            break;
         case 1: //dy
            if(yIntegrand!=null) tmp= yIntegrand.evaluate(s.getVariables()[0]); else break;
            sumY+=tmp*dy;
            break;
         case 2:  //ds
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(s.getVariables()[0]);else break;
            sumX+=tmp*ds;
            break;
         case 3:  //line integral
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(s.getVariables()[0]);else break;
            sumX+=tmp*dx;
            if(yIntegrand!=null) tmp= yIntegrand.evaluate(s.getVariables()[0]); else break;
            sumY+=tmp*dy;
            break;
         default:
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(s.getVariables()[0]);else break;
            sumX+=tmp*dx;
         }
         if(i<n-3){ // get the next interval
             dx=(x[i+2]-x[i+1]);
             dy=(y[i+2]-y[i+1]);
         }else if(i<n-2){  // the last interval gets half the weight
             dx=(x[i+2]-x[i+1])/2;
             dy=(y[i+2]-y[i+1])/2;
         }else{}
      }
      updateDataConnections();
    }
    public void deleteSeries(int id){
      reset();
    }
    public void clearSeries(int id){
     reset();
    }
}

