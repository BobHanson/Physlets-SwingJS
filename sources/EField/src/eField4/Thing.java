package eField4;


import java.awt.*;
import java.util.Vector;
import java.util.Enumeration;
import edu.davidson.numerics.Parser;
import edu.davidson.tools.SApplet;
import edu.davidson.display.Format;

public class Thing extends Object implements  edu.davidson.tools.SDataSource  {
  Format format= new Format("%-+6.3g");
  Color      darkGreen=new Color(0,128,0);
  Color      lightGreen=new Color(128,255,128);
  OdeCanvas p;
  int s=5; // size of thing
  int w=5; // width of thing
  int h=5; // height of thing
  Color color=Color.black;  // color of the drawing
  Color highlightColor=Color.gray;  // color of the drawing
  double[] vars=new double[7];  // the state variables.    Note, ax and ay are only used for datasource.
  double[] initVars=new double[7];
  String[] varStrings= new String[]{"t","x","y","vx","vy","ax","ay"};
  double[][] ds=new double[1][7];  // the datasource state variables t,x,y,vx,vy,ax,ay;
  double mag=1;                    // the charge magnitude
  double pe=0;                     // the potential energy
  double flux=0;                   // the flux for rectangles and circles.
  boolean sticky=false;
  boolean ghost=false;
  boolean showFOnDrag=false;
  boolean showVVector=false;
  boolean showAVector=false;
  boolean showFVector=false;
  boolean showFComponents=false;
  boolean showVComponents=false;
  Polygon trail= new Polygon();
  int trailSize=0;
  double damping=0;
  String label=null;
  boolean noDrag=true;
  boolean hideThing=false;
  boolean disabled=false;  // determines if a charge behaves in the normal fashion.  For example, will a test charge move?
  int  footPrints=0;
  double mass=1.0;
  boolean dynamic=false;
  Font    font=new Font("Monospaced",Font.PLAIN,14);

//Trajectories
  String xStr, yStr;
  Parser xFunc=null;
  Parser yFunc=null;
// Constraint
  Parser     constraint=null;
  String     constraintStr=null;
  double     constraintMin=0;
  double     constraintMax=0;
  boolean    showConstraintPath=true;
  boolean    constrainX=false;
  boolean    constrainY=false;
  boolean    constrainR=false;
  boolean    hasConstraint=false;
  double     constantX=0;
  double     constantY=0;
  double     constantRx=0;
  double     constantRy=0;
  double     constantR=0;

// offset the display
  int xDisplayOff=0;
  int yDisplayOff=0;

// Slave stuff
  Thing myMaster=null;// the hashCode of another Thing that controls the position of this object.
  Vector mySlaves= new Vector();

//  public Thing(){};
  public Thing(OdeCanvas p, double x,double y,double vx,double vy){
      this(p,x,y,vx,vy,1.0);
  }
  public Thing(OdeCanvas p, double x,double y,double vx,double vy, double m) {
      this.p=p;
      vars[0]=0; //time
      vars[1]=x;
      vars[2]=y;
      vars[3]=vx;
      vars[4]=vy;
      vars[5]=0;
      vars[6]=0;
      initVars[0]=0;
      initVars[1]=x;
      initVars[2]=y;
      initVars[3]=vx;
      initVars[4]=vy;
      initVars[5]=0;
      initVars[6]=0;
      mag=m;
      try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
  }

  public int getID(){return hashCode();}
  public final boolean isNoDrag(){return noDrag;}
  public final void setNoDrag(boolean nd){noDrag=nd;}
  public void setOwner(SApplet owner){}
  public SApplet getOwner(){return p.getOwner();}
  public String[] getVarStrings(){return varStrings;}
  public boolean isInsideThing(int i, int j){return false;}

  public final boolean isShowFVector(){return showFVector;}
  public final void setShowFVector(boolean sf){showFVector=sf;}
  public final int getSize(){return s;}
  public final void setSize(int sz){s=sz;}
  public final Color getColor(){return color;}
  public final void setColor(Color c){color=c;}

  public final boolean isHideThing(){return hideThing;}
  public final void setHideThing(boolean hide){hideThing=hide;}

  public final boolean setFormat(String str){
     try{format= new Format(str);}
     catch (IllegalArgumentException e){return false;}
     return true;
  }

  public final String getLabel(){return label;}
  public final void setLabel(String l){
      if(l==null || l.trim().equals("")) label=null;
          else label=new String(new char[] {l.charAt(0)} );
  }

  public final boolean isShowV(){return showVVector;}
  public final void setShowV(boolean sv){showVVector=sv;}

  final double enclosedCharge(){
    Pole pole;
    double sum=0;
    int xpix,ypix;
    int n=p.poles.size();
    for(int i=0; i<n; i++){
        pole=(Pole)p.poles.elementAt(i);
        xpix=p.pixFromX(pole.vars[1]);
        ypix=p.pixFromY(pole.vars[2]);
        if(this.isInsideThing(xpix,ypix)){sum+=pole.mag;}
    }
    return sum;
  }

  void calculateState(){
  // override me to calculate special state variables such as flux or potential.
  }

  private final void enforceConstraintOnX(){
      double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);
      if(vars[4]==0)vars[4]=speed;
      else vars[4]=speed*vars[4]/Math.abs(vars[4]);
      vars[3]=0;  // no velocity in the x direction allowed.
      if(constraintMin<constraintMax){  // this restricts the range of the constraint.
          if(vars[2]<constraintMin){  // check y position
              vars[2]=constraintMin;
              if(vars[4]<0){  // moving off of the bottom so turn it around.
                  vars[4]=-vars[4];
              }
          }
          else if(vars[2]>constraintMax){
              vars[2]=constraintMax;
              if(vars[4]>0){  // moving off of the top so turn it around
                  vars[4]=-vars[4];
              }
          }
      }
      vars[1]=constantX; //  x=constantX  Follow the constraint.
      return;
  }

  private final void enforceConstraintOnY(){
      double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);
      if(vars[3]==0)vars[3]=speed;
      else vars[3]=speed*vars[3]/Math.abs(vars[3]);
      vars[4]=0;  // no velocity in the y direction allowed.
      if(constraintMin<constraintMax){  // this restricts the range of the constraint.
          if(vars[1]<constraintMin){  // check y position
              vars[1]=constraintMin;
              if(vars[3]<0){  // moving off of the left so turn it around.
                  vars[3]=-vars[3];
              }
          }
          else if(vars[1]>constraintMax){
              vars[1]=constraintMax;
              if(vars[3]>0){  // moving off of the right so turn it around
                  vars[3]=-vars[3];
              }
          }
      }
      vars[2]=constantY; //  x=constantX  Follow the constraint.
      return;
  }

  final void enforceConstraintOnR(){
      double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);    // current speed.  Keep this constant
      double rad=Math.sqrt((vars[1]-constantRx)*(vars[1]-constantRx)+(vars[2]-constantRy)*(vars[2]-constantRy)); // current radius.
      if(rad==0){
          vars[1]=constantRx+constantR;
          vars[2]=constantRy;
          vars[3]=0;
          vars[4]=speed;
          return;
      }
      double u=(vars[1]-constantRx)/rad;   // (u,v) is unit vector along radial direction
      double v=(vars[2]-constantRy)/rad;   // (-v,u) is unit vector perpendicular to the radial direction
      vars[1]=constantR*u;
      vars[2]=constantR*v;
      if( (-vars[3]*v+vars[4]*u)>0){ // project current speed onto (-v,u) to keep the sense of rotation constant.
        vars[3]=-speed*v;
        vars[4]=speed*u;
      }else{
        vars[3]=speed*v;
        vars[4]=-speed*u;
      }
      return;
  }

  final void enforceConstraintOnXY(){
      if(!hasConstraint || xFunc!=null || yFunc!=null) return;  // Trajectories override constraints.
      else if(constrainX){
          enforceConstraintOnX();
          return;
      }if(constrainY){
          enforceConstraintOnY();
          return;
      }else if(constrainR){
          enforceConstraintOnR();
          return;
      }
      if (constraint==null) return;
      //System.out.println("total="+p.getParticleEnergy((Charge) this) );
      double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);
      if(constraintMin<constraintMax){  // this restricts the range of the constraint.
          if(vars[1]<constraintMin){
              if(vars[3]<0){  // moving to the left so turn it around.
                  vars[3]=-vars[3];
                  vars[4]=-vars[4];
              }
              //vars[1]=constraintMin;
              adjustPosition(constraintMin, speed);
              return;
          }
          else if(vars[1]>constraintMax){
              if(vars[3]>0){  // moving to the right so turn it around.
                  vars[3]=-vars[3];
                  vars[4]=-vars[4];
              }
              //vars[1]=constraintMax;
              adjustPosition(constraintMax, speed);
              return;
          }
      }
      vars[2]=constraint.evaluate(vars[1]); //  y = f(x)  Follow the constraint.
      double m=getConstraintSlope(vars[1],Math.abs(vars[1])*1.e-5+1.0e-6);

      double cos=1/Math.sqrt(1+m*m); // the direction cosine of the slope;
      if(vars[3]>0){
          vars[3]= speed*cos;               // new vx
          vars[4]= speed*m*cos;             // new vy
      }else{
          vars[3]= -speed*cos;               // new vx
          vars[4]= -speed*m*cos;             // new vy
      }
      return;
  }

  private void adjustPosition(double x1, double v1){
      double u1=0,u2=0;
      if(this instanceof TestCharge) u1=p.getPE( (Charge) this );
      vars[1]=x1;
      vars[2]=constraint.evaluate(x1);
      if(this instanceof TestCharge) u2=p.getPE( (Charge) this  );
      if(u2-u1==0) return;
      //System.out.println("u1="+ u1 + " u2="+ u2 );
      //System.out.println("total="+p.getParticleEnergy((Charge) this) );
      //System.out.println("speed="+v1 + "  Delta PE="+ (u2-u1));
      double speed, disc=-2*(u2-u1)/mass+v1*v1;
      if(disc>0)
        speed=Math.sqrt(disc);
      else speed=0;
      double m=getConstraintSlope(vars[1],Math.abs(vars[1])*1.e-5+1.0e-6);
      double cos=1/Math.sqrt(1+m*m); // the direction cosine of the slope;
      if(vars[3]>0){
          vars[3]= speed*cos;               // new vx
          vars[4]= speed*m*cos;             // new vy
      }else{
          vars[3]= -speed*cos;               // new vx
          vars[4]= -speed*m*cos;             // new vy
      }
      //System.out.println("speed="+speed + "total 2 ="+p.getParticleEnergy((Charge) this) );
  }

  public final  void setXY(double x,double y){
      if(myMaster!=null){
        vars[1]=myMaster.vars[1];
        vars[2]=myMaster.vars[2];
        return;
      }
      if(!constrainX) vars[1]=x;
      else vars[2]=y;
      if(constraint==null && !constrainX && !constrainY && !constrainR){
          vars[2]=y; // no constrains so we can set y.
          return;
      }
      if(constrainR){
          vars[2]=y;  // set Y and then project onto the circle.
          enforceConstraintOnR();
      }else enforceConstraintOnXY();
  }

  public final double getX(){return vars[1];}
  public final void setX(double x){
      if(myMaster!=null){vars[1]=myMaster.vars[1]; return;}
      vars[1]=x;
      if(constraint==null && !constrainX && !constrainY && !constrainR) return;
      else enforceConstraintOnXY();
  }

  public final double getY(){return vars[2];}
  public final void setY(double y){
     if(myMaster!=null){ vars[2]=myMaster.vars[2]; return;}
     if(constraint!=null  || constrainY || constrainR ) return;  // we have a constraint on y so y cannot be set.
     vars[2]=y;
     return;
  }

  public void setAcceleration(){  // override for charged objects
  }

  public final double getVX(){return vars[3];}
  public final void setVX(double vx){
      if(constrainX) return;  // we have a constraint on x so vx cannot be set.
      vars[3]=vx;
      return;
  }

  public final double getVY(){return vars[4];}
  public final void setVY(double vy){
      if(constrainY) return;  // we have a constraint on y so vy cannot be set.
      vars[4]=vy;
      return;
  }


  public final double[] getVars(){
      return vars;
  }

  public final void setSpeed(double newSpeed){
     newSpeed=Math.abs(newSpeed);
     if(constraint!=null || constrainX || constrainY ){
         setConstrainedSpeed(newSpeed);
         return;
     }
     double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);
     if(speed==0){
        vars[3]=newSpeed;
        vars[4]=0;
        return;
     }
     vars[3]=newSpeed*vars[3]/speed;
     vars[4]=newSpeed*vars[4]/speed;
  }

  double getConstraintSlope(double x, double dx){
      // the slope to 4 th order of the constraint equation.
      double f1,f2,f4,f5;
      f1=constraint.evaluate(x+2*dx);
      f2=constraint.evaluate(x+dx);
      f4=constraint.evaluate(x-dx);
      f5=constraint.evaluate(x-2*dx);
      return (-f1+8*f2-8*f4+f5)/12/dx;
  }

  double getConstraintSecondDeriv(double x, double dx){
      // the second derivative to 2 nd order of the constraint equation.
      double f1,f2,f3;
      f1=constraint.evaluate(x+dx);
      f2=constraint.evaluate(x);
      f3=constraint.evaluate(x-dx);
      return (f1-2*f2+f3)/dx/dx;
  }

  private final void setConstrainedSpeed(double newSpeed){
     if(constrainX){
         vars[3]=0;
         vars[4]=newSpeed;
         return;
     }else if(constrainY){
         vars[3]=newSpeed;
         vars[4]=0;
         return;
     }

     double m=getConstraintSlope(vars[1],Math.abs(vars[1])*1.e-4+1.0e-6);
     //double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);
     double cos=1/Math.sqrt(1+m*m); // the direction cosine of the slope;
     if(vars[3]>0){
          vars[3]= newSpeed*cos;               // new vx
          vars[4]= newSpeed*m*cos;             // new vy
      }else{
          vars[3]= -newSpeed*cos;               // new vx
          vars[4]= -newSpeed*m*cos;             // new vy
      }
  }


  public double[][] getVariables(){
     ds[0][0]=vars[0];  //t
     ds[0][1]=vars[1];  //x
     ds[0][2]=vars[2];  //y
     ds[0][3]=vars[3];  //vx
     ds[0][4]=vars[4];  //vy
     return ds;
  }

  public void paint(Graphics g){ ;}  // override me!
  public void paintHighlight(Graphics g){paint(g);}

  public void paintTrail(Graphics osg){
      osg.setColor(color);
      if(trailSize>1 && trail.npoints>1){
          if(footPrints==0)
              osg.drawPolyline(trail.xpoints,trail.ypoints,trail.npoints);
          else{
              for(int i=0;i<trail.npoints;i+=footPrints){
                  osg.drawLine(trail.xpoints[i]-2,trail.ypoints[i],trail.xpoints[i]+2,trail.ypoints[i]);
                  osg.drawLine(trail.xpoints[i],trail.ypoints[i]-2,trail.xpoints[i],trail.ypoints[i]+2);
              }
          }
      }
  }

  public final double getTime(){return vars[0];}

  public final void setTrailSize(int n){trailSize=n; clearTrail();}

  public void clearTrail(){
      if(trail!=null && trail.npoints==0){;}    // already have an empgy polygon so no need to allocate.
        else trail=new Polygon();
      incTrail();
  }

  public void incTrail(){}  // only charges can have trails.

  public void setTime(double t, double dt){
      if(myMaster!=null || dynamic) return;
      vars[0]=t;vars[5]=0; vars[6]=0;
      if(xFunc==null || yFunc==null) return;
      double x0=0, x1=0,x2=0,x3=0, x4=0;
      double y0=0, y1=0,y2=0,y3=0, y4=0;
      try{x0=xFunc.evaluate(t-2*dt);}catch(Exception e){}
      try{y0=yFunc.evaluate(t-2*dt);}catch(Exception e){}
      try{x1=xFunc.evaluate(t-dt);}catch(Exception e){}
      try{y1=yFunc.evaluate(t-dt);}catch(Exception e){}
      try{x2=xFunc.evaluate(t);}catch(Exception e){}
      try{y2=yFunc.evaluate(t);}catch(Exception e){}
      try{x3=xFunc.evaluate(t+dt);}catch(Exception e){}
      try{y3=yFunc.evaluate(t+dt);}catch(Exception e){}
      try{x4=xFunc.evaluate(t+2*dt);}catch(Exception e){}
      try{y4=yFunc.evaluate(t+2*dt);}catch(Exception e){}
      vars[1]=x2;
      vars[2]=y2;
      vars[3]=(x3-x1)/dt/2;
      vars[4]=(y3-y1)/dt/2;
      vars[5]= (-x4+16*x3-30*x2+16*x1-x0)/dt/dt/12;
      vars[6]= (-y4+16*y3-30*y2+16*y1-y0)/dt/dt/12;
      return;
  }

  public boolean hasTrajectory(){
      if(xFunc == null || yFunc==null) return false;
      return true;
  }

  public boolean setTrajectory(String xStr,String yStr){
      vars[0]=p.time;
      vars[1]=0;
      vars[2]=0;
      vars[3]=0;  // set velocity and acceleration to zero.
      vars[4]=0;
      vars[5]=0;
      vars[6]=0;
      if(xStr==null || yStr==null){
          this.xStr=xStr;
          this.yStr=yStr;
          xFunc = null;
          yFunc = null;
          return true;
      }
      this.xStr=xStr;
      this.yStr=yStr;
      xFunc = new Parser(1);
      xFunc.defineVariable(1,"t"); // define the variable
      xFunc.define(xStr);
      xFunc.parse();
      if(xFunc.getErrorCode() != Parser.NO_ERROR){
          System.out.println("Failed to parse trajectory x(t): "+xStr);
          System.out.println("Parse error: " + xFunc.getErrorString() +
                   " at function 1, position " + xFunc.getErrorPosition());
          return false;
      }
      yFunc = new Parser(1);
      yFunc.defineVariable(1,"t"); // define the variable
      yFunc.define(yStr);
      yFunc.parse();
      if(yFunc.getErrorCode() != Parser.NO_ERROR){
           System.out.println("Failed to parse trajectory y(t): "+yStr);
           System.out.println("Parse error: " + yFunc.getErrorString() +
                   " at function 1, position " + yFunc.getErrorPosition());
           return false;
      }
      setTime(p.time,0.001);
      clearTrail();
      dynamic=false;
      if(p.autoRefresh)p.repaint();
      return true;
  }

  public boolean setConstraintStr(String s,double min, double max){
     constrainX = false;
     constrainY = false;
     constrainR = false;
     if( s==null || s.equals("") ){
         constraintMin=0;
         constraintMax=0;
         constraintStr=null;
         constraint=null;
         return true;
     }
     constraintMin=min;
     constraintMax=max;
     constraintStr=s.trim();
     constraint = new Parser(1);
     constraint.defineVariable(1,"x"); // define the variable
     constraint.define(constraintStr);
     constraint.parse();
     if(constraint.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse constraint: "+constraint.getFunctionString() );
         System.out.println("Parse error: " + constraint.getErrorString() +
                   " at position " + constraint.getErrorPosition());
         constraint=null;
         return false;
     }
     hasConstraint=true;
     enforceConstraintOnXY();
     if(p.autoRefresh)p.repaint();
     return true;
  }

  public boolean setConstrainR(double r,double x, double y){
     hasConstraint=true;
     constraint = null;
     constrainX = false;
     constrainY = false;
     constrainR = true;
     constantRx=x;
     constantRy=x;
     constantR=r;
     enforceConstraintOnXY();
     if(p.autoRefresh)p.repaint();
     return true;
  }

  public boolean setConstrainX(double x,double min, double max){
     hasConstraint=true;
     constraint = null;
     constrainX = true;
     constrainY = false;
     constrainR = false;
     constraintMin=min;
     constraintMax=max;
     constantX=x;
     enforceConstraintOnXY();
     if(p.autoRefresh)p.repaint();
     return true;
  }

  public boolean setConstrainY(double y,double min, double max){
     hasConstraint=true;
     constraint = null;
     constrainX = false;
     constrainY = true;
     constrainR = false;
     constraintMin=min;
     constraintMax=max;
     constantY=y;
     enforceConstraintOnXY();
     if(p.autoRefresh)p.repaint();
     return true;
  }

  void paintConstraintR(Graphics g, Rectangle r){
     int xpix=p.pixFromX(constantRx);  // the x position of the center of the circle
     int ypix=p.pixFromY(constantRy);  // the y position of the center of the circle
     int rpix=p.pixFromX(constantRx+constantR)-xpix;  // the radius of the circle in pix
     xpix=xpix-rpix+xDisplayOff;
     ypix=ypix-rpix-yDisplayOff;
     g.drawOval(xpix,ypix,2*rpix+1,2*rpix+1);
  }

  void paintConstraintX(Graphics g, Rectangle r){
     int bottom, top;
     int xpix=p.pixFromX(constantX);
     if(constraintMin<constraintMax){ // constraint has max min
        bottom=p.pixFromY(constraintMin);
        top=p.pixFromY(constraintMax);
    }else{
        top=r.y;
        bottom=r.y+r.height-1;
    }
    g.drawLine(xpix,top,xpix,bottom);
  }

  void paintConstraintY(Graphics g, Rectangle r){
     int left, right;
     int ypix=p.pixFromY(constantY);
     if(constraintMin<constraintMax){ // constraint has max min
        left=p.pixFromX(constraintMin);
        right=p.pixFromX(constraintMax);
    }else{
        left=r.x; // start on left side of graph
        right=r.x+r.width-1; // right hand side of graph
    }
    g.drawLine(left,ypix,right,ypix);
  }

  public void paintConstraint(Graphics g){
    if(!showConstraintPath) return;
    if(constraint==null && !constrainX && !constrainY && !constrainR) return;
    Rectangle r = p.getBounds();
        /* The r.x and r.y returned from bounds is relative to the
        ** parents space so set them equal to zero.
        */
    r.x = 0;
    r.y = 0;
    g.setColor(Color.black);
    if(constrainX){
        paintConstraintX( g,r);
        return;
    }
    else if(constrainY){
        paintConstraintY( g,r);
        return;
    }if(constrainR){
        paintConstraintR( g,r);
        return;
    }
    if(constraint==null) return;
    int left=0, right=0;
    int numPts=0;  // number of points to plot;
    double xmin=0,xmax=0;
    //int x1Pix=0, x2Pix=0;
    int y1Pix=0, y2Pix=0;
    double x=0, y=0;
    double dx;
    if(constraintMin<constraintMax){
        left=p.pixFromX(constraintMin);
        left=Math.max(left,r.x);
        right=p.pixFromX(constraintMax);
        right=Math.min(right,r.x+r.width-1);
        xmin=constraintMin;
        xmax=constraintMax;
    }else{
        left=r.x; // start on left side of graph
        right=r.x+r.width-1; // right hand side of graph
        xmin=p.xFromPix(left); // start on left side of graph
        xmax=p.xFromPix(right); // right hand side of graph
    }
    numPts=right-left;
    dx=(xmax-xmin)/(numPts-1);
    x=xmin; // start on left side of graph
    //x1Pix=p.pixFromX(x);
    y=constraint.evaluate(x);
    y1Pix=p.pixFromY(y);
    for(int i=0; i<numPts-1; i++){
            x=x+dx;
            //x2Pix=p.pixFromX(x);
            y=constraint.evaluate(x);
            y2Pix=p.pixFromY(y);
            g.drawLine(left+i,y1Pix,left+i+1,y2Pix);
            //x1Pix=x2Pix;
            y1Pix=y2Pix;
    }
  }

  public void setVarsFromMaster(){
    if(myMaster==null) return;
      if(this instanceof TextThing) myMaster.calculateState();
      vars[0]=myMaster.vars[0];
      vars[1]=myMaster.vars[1];
      vars[2]=myMaster.vars[2];
      vars[3]=myMaster.vars[3];
      vars[4]=myMaster.vars[4];
      vars[5]=myMaster.vars[5];
      vars[6]=myMaster.vars[6];
      mag=myMaster.mag;
      mass=myMaster.mass;
      flux=myMaster.flux;
      pe=myMaster.pe;
      Thing t=null;
      for( Enumeration e=mySlaves.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t!=this && t.myMaster==this )t.setVarsFromMaster();
      }
  }

  void updateMySlaves(){
    Thing slave=null;
    for( Enumeration e=mySlaves.elements(); e.hasMoreElements();){
         slave= (Thing) e.nextElement();
         slave.setVarsFromMaster() ;
         }
  }

  void paintMySlaves(Graphics g){
    Thing slave=null;
    for( Enumeration e=mySlaves.elements(); e.hasMoreElements();){
         slave= (Thing) e.nextElement();
         slave.paint(g) ;
    }
  }
}