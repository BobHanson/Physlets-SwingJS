package filters;

import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;

// indefinite integral
// see integrator for definite integral.
// The inner class also do a definate integral using the stored data.
public final class Integral  extends SApplet implements SDataListener, SDataSource{
    //private Object runLock = new Object();
    private String xiStr;
    private Parser xIntegrand=null;
    private String yiStr;
    private Parser yIntegrand=null;
    private Definite definite=new Definite(this);
    String[] varStrings= new String[]{"x","y","n","integral","ds"};
    double[][] variables=null;
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

    public Integral() {
              try{SApplet.addDataListener(this); }catch (Exception e){e.printStackTrace();}
              try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }
//Initialize the applet

    public void init() {
        String diffStr="dx";
        int maxPoints=500;
        try { varStrings[0] = this.getParameter("Independent", "x"); } catch (Exception e) { e.printStackTrace(); }
        //try { varStrings[1] = this.getParameter("Dependent", "y"); } catch (Exception e) { e.printStackTrace(); }
        try { diffStr = this.getParameter("Differential", "dx"); } catch (Exception e) { e.printStackTrace(); }
        try { xiStr = this.getParameter("Integrand", varStrings[1]); } catch (Exception e) { e.printStackTrace(); }
        try { maxPoints = Integer.parseInt(this.getParameter("MaxPoints", "500")); } catch (Exception e) { e.printStackTrace(); }
        variables=new double[maxPoints][5];  // the datasource state variables
        yiStr= new String(xiStr);
        diffStr=diffStr.toLowerCase();
        if(diffStr.equals("dx")) mode=0;
          else if (diffStr.equals("dy")) mode=1;
          else if (diffStr.equals("ds")) mode=2;
          else {mode=0; System.out.println("Differential parameter invalid. Use dx, dy or ds.");}
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
      if(n<variables.length){
        variables[n][0]=lastX;
        variables[n][1]=lastY;
        variables[n][2]=n;
        variables[n][3]=sumX+sumY;
        if(n>0)variables[n][4]=ds;  else variables[n][4]=0;
      }
      for(int i=n+1; i<variables.length;i++){ // fill the remaining array.
        variables[i][0]=lastX;
        variables[i][1]=lastY;
        variables[i][2]=i;
        variables[i][3]=sumX+sumY;
        variables[i][4]=0;
      }
      n++;
      updateDataConnections();
    }

    public void addData(SDataSource s,int id, double x[], double y[] ){
    //System.out.println("Add data=");
      n=x.length;
      if (n<2)return;
      if(n!= variables.length) variables=new double[n][5];  // new datasource state variables
      if(xIntegrand==null && xiStr!=null )parseXIntegrand(s);
      if(yIntegrand==null && yiStr!=null )parseYIntegrand(s);
      sumX=0; sumY=0;
      double tmp=0;
      dx=(x[1]-x[0])/2;   // first point in trapazoidal rule get 1/2 the weight.
      dy=(y[1]-y[0])/2;
      double[][] sourceVars=s.getVariables();
      for(int i=0; i<n; i++){
        ds=Math.sqrt(dx*dx+dy*dy);
        switch(mode) {
         case 0:// dx mode
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(sourceVars[i]);else break;
            sumX+=tmp*dx;
            break;
         case 1: //dy
            if(yIntegrand!=null) tmp= yIntegrand.evaluate(sourceVars[i]); else break;
            sumY+=tmp*dy;
            break;
         case 2:  //ds
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(sourceVars[i]);else break;
            sumX+=tmp*ds;
            break;
         case 3:  //line integral
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(sourceVars[i]);else break;
            sumX+=tmp*dx;
            if(yIntegrand!=null) tmp= yIntegrand.evaluate(sourceVars[i]); else break;
            sumY+=tmp*dy;
            break;
         default:
            if(xIntegrand!=null) tmp= xIntegrand.evaluate(sourceVars[i]);else break;
            sumX+=tmp*dx;
         }
        if(i<n-3){ // get the next interval
             dx=(x[i+2]-x[i+1]);
             dy=(y[i+2]-y[i+1]);
        }else if(i<n-2){  // the last interval gets half the weight
             dx=(x[i+2]-x[i+1])/2;
             dy=(y[i+2]-y[i+1])/2;
        }else{}
        variables[i][0]=x[i];
        variables[i][1]=y[i];
        variables[i][2]=i;
        variables[i][3]=sumX+sumY;
        variables[i][4]=ds;
      }
      updateDataConnections();
    }

    public void deleteSeries(int id){
      reset();
    }
    public void clearSeries(int id){
     reset();
    }

// The id of the data source for the data connection.  Used by JavaScript to make a data connection.
  public int getDefiniteIntegralID(double start, double stop){
      definite.setLimits(start, stop);
      return definite.getID();
  }

  public double getDefiniteIntegral(double start, double stop){
      return definite.getDefinate(start, stop);
  }


// inner class that does definite integrals as data source.
  public class Definite extends Object implements SDataSource{
     String[] varStrings= new String[]{"start","stop","integral"};
     double[][] vars= new double[1][3];
     double start=0;
     double stop=0;
     SApplet owner;

    Definite(SApplet o){ // Constructor
        owner=o;
        try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    void setLimits(double start,double stop){
       this.start=start;
       this.stop=stop;
    }

    int getStartIndex(double start){
       if(variables==null){  return -1;}
       for(int i=0; i<variables.length; i++)
          if(variables[i][0]>=start) return i;
       return variables.length-1;
    }

    int getEndIndex(double stop){
       if(variables==null){  return -1;}
       for(int i=0; i<variables.length; i++)
          if(variables[i][0]>=stop) return i;
       return variables.length-1;
    }

    public double getDefinate(double start, double stop){
        double sum=0;
        int istart=getStartIndex(start);
        int iend=getEndIndex(stop);
        if(istart<0 || iend<=0){
            return 0 ;
        }
        sum=variables[iend][3]-variables[istart][3];  // integral
        if(istart>1 && variables.length>1 ){ // interpolation
            double frac=0;
            frac=(variables[istart][0]-start)/(variables[istart][0]-variables[istart-1][0]);
            sum=sum+frac*(variables[istart][3]-variables[istart-1][3]);
        }
        if(iend<variables.length-1 && variables.length>1 && stop <variables[variables.length-1][0] ){ // interpolation
            double frac=0;
            frac=(variables[iend][0]-stop)/(variables[iend][0]-variables[iend-1][0]);
            sum=sum-frac*(variables[iend][3]-variables[iend-1][3]);
        }
        return sum;
    }

// data soruce methods
    public double[][] getVariables(){
        //System.out.println("start="+start);
        int istart=getStartIndex(start);
        int iend=getEndIndex(stop);
        if(istart<0 || iend<=0){
            vars[0][0]=0;  // start
            vars[0][1]=0;  // end
            vars[0][2]=0;  // integral
            return vars ;
        }
        //vars[0][0]=variables[istart][0];  // start
        //vars[0][1]=variables[iend][0];  // end
        vars[0][0]=Math.max(start,variables[0][0]);  // start
        vars[0][1]=Math.min(stop,variables[variables.length-1][0]);  // end
        vars[0][2]=variables[iend][3]-variables[istart][3];  // integral

        if(istart>1 && variables.length>1 ){ // interpolation
            double frac=0;
            frac=(variables[istart][0]-start)/(variables[istart][0]-variables[istart-1][0]);
            //System.out.println("istart="+istart+ " frac="+frac + " vars[0][2]="+vars[0][2]);
            vars[0][2]=vars[0][2]+frac*(variables[istart][3]-variables[istart-1][3]);
            //System.out.println(" new vars[0][2]="+vars[0][2]);
        }
        if(iend<variables.length-1 && variables.length>1 && stop <variables[variables.length-1][0] ){ // interpolation
            double frac=0;
            frac=(variables[iend][0]-stop)/(variables[iend][0]-variables[iend-1][0]);
            //System.out.println("istart="+istart+ " frac="+frac + " vars[0][2]="+vars[0][2]);
            vars[0][2]=vars[0][2]-frac*(variables[iend][3]-variables[iend-1][3]);
            //System.out.println(" new vars[0][2]="+vars[0][2]);
        }
        return vars;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return this.hashCode();}
    public void setOwner(SApplet o){owner=o;}
    public SApplet getOwner(){return owner;}
  }

}

