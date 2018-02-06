package animator4;

import edu.davidson.numerics.Parser;


public class Interaction {
    static int XMODE=0;
    static int YMODE=1;
    static int RMODE=2;
    Thing thing1,thing2;
    String fStr;
    Parser force=null;
    double[] v=new double[2];
    double[] zero=new double[2];
    int mode=RMODE;
    double[] parserVars=new double[5];
    double oldDx=0;
    double oldDy=0;

    public Interaction(Thing me, Thing t2, String str,int m) {
        mode=m;
        thing1=me;
        thing2=t2;
        this.fStr=str;
        force = new Parser(5);//t,x,y,r,v
        force.defineVariable(1,"t"); // define the variable
        force.defineVariable(2,"x"); // define the variable
        force.defineVariable(3,"y"); // define the variable
        force.defineVariable(4,"r"); // define the variable
        force.defineVariable(5,"v"); // define the variable
        force.define(this.fStr);
        force.parse();
        if(force.getErrorCode() != Parser.NO_ERROR){
          System.out.println("Failed to parse interaction force(): "+str);
          System.out.println("Parse error: " + force.getErrorString() +
                   " in Interaction at position " + force.getErrorPosition());
          force=null;
        }
    }

    public double[] getF(){
       if (force==null){
           return zero;
       }
       double dx=thing1.vars[1]-thing2.vars[1];
       double dy=thing1.vars[2]-thing2.vars[2];
       double dvx=thing1.vars[3]-thing2.vars[3];
       double dvy=thing1.vars[4]-thing2.vars[4];
       double r=Math.sqrt(dx*dx+dy*dy);
       double vel=Math.sqrt(dvx*dvx+dvy*dvy);
       double dot=(dx*dvx+dy*dvy);   // the dot product of v and r.
       if(dot!=0)vel=vel*dot/Math.abs(dot); else vel=0;

       if(r==0 || (dx==0 && mode==XMODE) || (dy==0 && mode==YMODE)  ){  // vector cannot have a direction since it is radial.
          parserVars[0]=thing1.vars[0];
          parserVars[1]=Math.abs(dx);
          parserVars[2]=Math.abs(dy);
          parserVars[3]=r;
          parserVars[4]=vel;
          oldDx=dx;
          oldDy=dy;
          return zero;
       }
       if(oldDx==dx && oldDy==dy &&
           parserVars[3]==r && parserVars[4]==vel &&
           parserVars[0]==thing1.vars[0] ) return v;  // no need to repeat the calculation since nothing changed.

       parserVars[0]=thing1.vars[0];
       oldDx=dx;
       oldDy=dy;
       parserVars[1]=Math.abs(dx);
       parserVars[2]=Math.abs(dy);
       parserVars[3]=r;
       parserVars[4]=vel;
       double f= force.evaluate(parserVars);          //Force(x,t)  since Time is the same for all objects we can use either object.
       if(mode==XMODE){
           v[0]=f*dx/Math.abs(dx);
           v[1]=0;
       }else if(mode==YMODE){
           v[0]=0;
           v[1]=f*dy/Math.abs(dy);
       }else{
           v[0]=f*dx/r;
           v[1]=f*dy/r;
       }
       return v;
    }
}