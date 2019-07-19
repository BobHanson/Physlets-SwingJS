package animator4;

//import a2s.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Enumeration;
import java.util.Vector;

import edu.davidson.display.Format;
import edu.davidson.numerics.Parser;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SDataListener;
import edu.davidson.tools.SDataSource;
import edu.davidson.tools.SUtil;

public class Thing extends Object implements SDataSource, SDataListener    {
  static Color      darkGreen=new Color(0,128,0);
  static Color      lightGreen=new Color(128,255,128);
  static Color      darkBlue=new Color(0,0,128);
  static Color      lightBlue=new Color(128,128,255);
  static Color      lightGray=new Color(160,160,160);
  Color highlightColor=lightGray;

  double     mass=1.0;
  Format     format= new Format("%-+6.3g");
  Font       font=new Font("Monospaced",Font.PLAIN,14);
  AnimatorCanvas canvas;
  int s=5; // size of thing
  int w=5; // width of thing
  int h=5; // height of thing
  Color color=Color.black;  // color of the drawing
  double[] vars=new double[8];  // the state variables t,x,y,vx,vy,ax,ay;
  double[] initVars=new double[8];
  String[] varStrings= new String[]{"t","x","y","vx","vy","ax","ay","m","fx","fy","w","h"};
  double[][] ds=new double[1][12];  // the datasource state variables t,x,y,vx,vy,ax,ay;
  Polygon trail= new Polygon();
  int trailSize=0;
  int trailOffset=0;
  int trailCounter=0;
  String label=null;
  Font f=new Font("Helvetica",Font.BOLD,14);
  boolean noDrag=true;
  boolean resizable=false;
  int  footPrints=0;
  boolean  paintBeforeGrid=false;
  boolean ghost=false;
  boolean showVVector=false;
  boolean showVComponents=false;
  boolean showAVector=false;
  boolean showAComponents=false;
  boolean showFVector=false;
  boolean showFComponents=false;
  boolean visible=true;
  Thing myMaster=null;// the hashCode of another Thing that controls the position of this object.
  Vector mySlaves= new Vector();

  // offset the display
  int xDisplayOff=0;
  int yDisplayOff=0;

// Coordinate stuff
  boolean showCoordinates=false;
  int xCoordOff=0;
  int yCoordOff=0;

//Trajectories
  String xStr, yStr;
  Parser xFunc=null;
  Parser yFunc=null;

//Netwon's Laws
  boolean dynamic=false;
  String fxStr, fyStr;
  Parser xForce=null;
  Parser yForce=null;
  Vector interactions=new Vector();

// initial values
  double x0;
  double y0;
  double vx0;
  double vy0;

// Constraint
  double     constraintMin=0;
  double     constraintMax=0;
  boolean    showConstraintPath=true;
  boolean    constrainX=false;
  boolean    constrainY=false;
  boolean    constrainR=false;
  double     constantX=0;
  double     constantY=0;
  double     constantRx=0;
  double     constantRy=0;
  double     constantR=0;

  boolean sticky=false;
  boolean bouncy=false;

  void setSticky(boolean s){
     sticky=s;
     if(s)canvas.stickyCount++;
  }

 void setBouncy(boolean b){
     bouncy=b;
     if(b)canvas.bouncyCount++;
  }

  public Thing(AnimatorCanvas c){
      this.canvas=c;
      vars[7]=mass;
      initVars[7]=mass;
  }   // needed for function thing since the parser variables are different.

  public Thing(AnimatorCanvas c, String xStr, String yStr){
     vars[7]=mass;
     initVars[7]=mass;
     this.canvas=c;
     setTrajectory(xStr,yStr);
     try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
     try{SApplet.addDataListener(this); }catch (Exception e){e.printStackTrace();}
  }


  public int getID(){return hashCode();}
  public final boolean isNoDrag(){return noDrag;}
  public final void setNoDrag(boolean nd){noDrag=nd;}
  public final boolean isResizable(){return resizable;}
  public final void setResizable(boolean rs){resizable=rs;}
  public void setOwner(SApplet owner){}
  public SApplet getOwner(){return canvas.getOwner();}
  public String[] getVarStrings(){return varStrings;}
  public boolean isInsideThing(int i, int j){return false;}
  public final int getSize(){return s;}
  public final void setSize(int sz){s=sz;}
  public final Color getColor(){return color;}
  public final void setColor(Color c){color=c;}
  public final boolean isVisible(){return visible;}
  public final void setVisible(boolean v){visible=v;}
  public final boolean setFormat(String str){
     try{format= new Format(str);}
     catch (IllegalArgumentException e){return false;}
     return true;
  }
  public final void setMass(double m){
      mass=m;
      vars[7]=m;
      initVars[7]=m;
  }

  public final void setLabel(String l){ label=l;}

  public void setProperties(int sid, double alpha,double beta){
  }

  public  void setXY(double x,double y){
      if(myMaster!=null){
        vars[1]=myMaster.vars[1];
        vars[2]=myMaster.vars[2];
        return;
      }

      if(!constrainX) vars[1]=x;
      else vars[2]=y;
      if(!constrainX && !constrainY && !constrainR){
          vars[2]=y;
          return;
      }
      if(constrainR){
          vars[2]=y;  // set Y and then project onto the circle.
          enforceConstraintOnR();
      }else enforceConstraintOnXY();
  }

  void updateDynamics(){
    if(!dynamic) return;
    int i = canvas.dynamics.dThings.indexOf(this);
    i           = 1 + i * 4;
    canvas.dynamics.vars[i]     = this.vars[1];    //  x
    canvas.dynamics.vars[i + 1] = this.vars[2];    //  y
  }

  public  double getH(){return h;}
  public  void setH(double vert){
     this.h=(int)vert;
  }

  public  double getW(){return w;}
  public  void setW(double horz){
     this.w=(int)horz;
  }


  public  double getX(){return vars[1];}
  public final void setX(double x){
     if(myMaster!=null){vars[1]=myMaster.vars[1]; return;}
     vars[1]=x;
     if( !constrainX && !constrainY && !constrainR) return;
     else enforceConstraintOnXY();
  }

  public  double getY(){return vars[2];}
  public final void setY(double y){
      if(myMaster!=null){ vars[2]=myMaster.vars[2]; return;}
      if(constrainY || constrainR) return;  // we have a constraint on y so y cannot be set.
      vars[2]=y;
  }

  public void setVarsFromMaster(){
      if(myMaster==null) return;
      vars[0]=myMaster.vars[0];
      vars[1]=myMaster.vars[1];
      vars[2]=myMaster.vars[2];
      vars[3]=myMaster.vars[3];
      vars[4]=myMaster.vars[4];
      vars[5]=myMaster.vars[5];
      vars[6]=myMaster.vars[6];
      vars[7]=myMaster.vars[7];
      mass=myMaster.mass;
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

  public  double getVX(){return vars[3];}
  public  void setVX(double vx){
      if(constrainX) return;  // we have a constraint on x so vx cannot be set.
      vars[3]=vx;
  }

  public  double getVY(){return vars[4];}
  public  void setVY(double vy){
      if(constrainY) return;  // we have a constraint on y so vy cannot be set.
      vars[4]=vy;
  }

  public final void setSpeed(double speed){
     if(myMaster!=null){return;}
     if(speed<=0){ // speed must me positive
       vars[3]=0;
       vars[4]=0;
       return;
     }
     double oldSpeed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);
     if(oldSpeed==0){  // we need to project the speed onto the constraint.
        if(constrainX){
           vars[4]=speed;
        }else if(constrainY){
           vars[3]=speed;
        }else if(constrainR){
           double u=(vars[1]-constantRx)/constantR;   // (u,v) is unit vector along radial direction
           double v=(vars[2]-constantRy)/constantR;   // (-v,u) is unit vector perpendicular to the radial direction
           vars[3]=-v*speed;
           vars[4]=u*speed;
        }else{
          vars[3]=speed;
          vars[4]=0;
        }
     }else{
         vars[3]= vars[3]*speed/oldSpeed;  // direction of motion will not change.
         vars[4]= vars[4]*speed/oldSpeed;
     }
     canvas.dynamics.resetDynamicsVariables();
  }

  public final void constrainedRForce(int i, double [] dxdt){
      //enforceConstraintOnR();
      //double rad=Math.sqrt((vars[1]-constantRx)*(vars[1]-constantRx)+(vars[2]-constantRy)*(vars[2]-constantRy)); // current radius.

      double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);    // current speed.  Keep this constant

      double rad=constantR;
      double u=(vars[1]-constantRx)/rad;   // (u,v) is unit vector along radial direction
      double v=(vars[2]-constantRy)/rad;   // (-v,u) is unit vector tangent to the radial direction

      double ax=getFx()/mass;
      double ay=getFy()/mass;
      double acc=(-ax*v+ay*u);  // the acceleration along the tangent.
      double acc2=-speed*speed/constantR;  // the acceleration along the radius.

      dxdt[i+2]=-acc*v+acc2*u;       // d Vx/dt
      dxdt[i+3]=acc*u+acc2*v;       // d Vy/dt
      return;
  }

  public final double getTotalFx(){
      if(!constrainR ) return getFx();
      if(constantR==0) return 0;
      //double rad=Math.sqrt((vars[1]-constantRx)*(vars[1]-constantRx)+(vars[2]-constantRy)*(vars[2]-constantRy)); // current radius.
      double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);    // current speed.  Keep this constant
      double u=(vars[1]-constantRx)/constantR;   // (u,v) is unit vector along radial direction
      double v=(vars[2]-constantRy)/constantR;   // (-v,u) is unit vector tangent to the radial direction

      double fx=getFx();
      double fy=getFy();
      double force=(-fx*v+fy*u);  // the force along the tangent.
      double force2=-mass*speed*speed/constantR;  // the force along the radius.

      return -force*v+force2*u;       // projection of the force in the x direction.
  }

  public final double getTotalFy(){
      if(!constrainR ) return getFy();
      if(constantR==0) return 0;
      //double rad=Math.sqrt((vars[1]-constantRx)*(vars[1]-constantRx)+(vars[2]-constantRy)*(vars[2]-constantRy)); // current radius.
      double speed=Math.sqrt(vars[3]*vars[3]+vars[4]*vars[4]);    // current speed.  Keep this constant
      double u=(vars[1]-constantRx)/constantR;   // (u,v) is unit vector along radial direction
      double v=(vars[2]-constantRy)/constantR;   // (-v,u) is unit vector tangent to the radial direction

      double fx=getFx();
      double fy=getFy();
      double force=(-fx*v+fy*u);  // the force along the tangent.
      double force2=-mass*speed*speed/constantR;  // the force along the radius.

      return force*u+force2*v;       // projection of the force in the x direction.
  }

  public final double getFx(){
     if(constrainX) return 0;  // we have a constraint on x so x is constant.
     double fx=0;
     for(int i=0; i<interactions.size();i++){
       Interaction in=(Interaction )interactions.elementAt(i);
       fx+= in.getF()[0];
     }
     if(this instanceof Charge)fx += getCoulombFx((Charge)this);
     if(xForce==null) return fx;
     return  xForce.evaluate(vars)+fx;
  }

  double getCoulombFx(Charge q1){
       double dx;
       double dy;
       double r,r2;
       Thing  t=null;
       Charge q2=null;
       double fx=0;
       for( Enumeration e=canvas.things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t instanceof Charge && t!=q1){
            q2=(Charge)t;
            dx=q1.vars[1]-q2.vars[1];
            dy=q1.vars[2]-q2.vars[2];
            r2=dx*dx+dy*dy;
            r=Math.sqrt(r2);
            if(r>q2.range+q1.range){
               fx+=q1.q*q2.q*dx/r2/r;
            }else{ fx+=q1.q*q2.q*dx;}
         }
       }
       return fx;
  }

  double getCoulombFy(Charge q1){
       double dx;
       double dy;
       double r,r2;
       Thing  t=null;
       Charge q2=null;
       double fy=0;
       for( Enumeration e=canvas.things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t instanceof Charge && t!=q1){
            q2=(Charge)t;
            dx=q1.vars[1]-q2.vars[1];
            dy=q1.vars[2]-q2.vars[2];
            r2=dx*dx+dy*dy;
            r=Math.sqrt(r2);
            if(r>q2.range+q1.range){
               fy+=q1.q*q2.q*dy/r2/r;
            }else{ fy=q1.q*q2.q*dy;}
         }
       }
       return fy;
  }


  public final double getFy(){
     if(constrainY) return 0;  // we have a constraint on y so y is constant.
     double fy=0;
     for(int i=0; i<interactions.size();i++){
       Interaction in=(Interaction )interactions.elementAt(i);
       fy+= in.getF()[1];
     }
     if(this instanceof Charge)fy += getCoulombFy((Charge)this);
     if(yForce==null) return fy;
     return  yForce.evaluate(vars)+fy;
  }

  boolean addInteraction( Thing t, String f, int mode){
      Interaction i=new Interaction(this,t,f, mode);
      if (i.force==null) return false;
      interactions.addElement(i);
      vars[5]=getFx()/mass;       // d Vx/dt
      vars[6]=getFy()/mass;       // d Vy/dt
      return true;
  }

  //public final double[] getVars(){return vars;}

  public double[][] getVariables(){
     ds[0][0]=vars[0];  //t
     ds[0][1]=vars[1];  //x
     ds[0][2]=vars[2];  //y
     ds[0][3]=vars[3];  //vx
     ds[0][4]=vars[4];  //vy
     ds[0][5]=vars[5];  //ax
     ds[0][6]=vars[6];  //ay
     ds[0][7]=mass;  //mass
     if(myMaster==null){
         ds[0][8]= getTotalFx();  //fx
         ds[0][9]= getTotalFy();  //fy
     } else{
         ds[0][8]= myMaster.getTotalFx();  //fx
         ds[0][9]= myMaster.getTotalFy();  //fy
     }
     ds[0][10]=w;  //width
     ds[0][11]=h;  //height
     return ds;
  }

  public void paint(Graphics g){  // override me but call super to show velocity and force.
      if(showConstraintPath){
        g.setColor(SUtil.paleColor(color) );
        if(constrainX) paintConstraintX(g);
        if(constrainY) paintConstraintY(g);
        if(constrainR) paintConstraintR(g);
      }
      int ptX=canvas.pixFromX(vars[1]);
      int ptY=canvas.pixFromY(vars[2]);
      if(showVComponents){
          g.setColor(lightGreen);
          int ptVX=canvas.pixFromX(vars[1]+vars[3]);
          int ptVY=canvas.pixFromY(vars[2]+vars[4]);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptY);
          SUtil.drawArrow(g, ptVX,ptY,ptVX, ptVY);
      }
      if(showVVector){
          g.setColor(darkGreen);
          int ptVX=canvas.pixFromX(vars[1]+vars[3]);
          int ptVY=canvas.pixFromY(vars[2]+vars[4]);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptVY);
      }
      if(showFComponents){
          g.setColor(lightBlue);
          int ptVX=canvas.pixFromX(vars[1]+getTotalFx());
          int ptVY=canvas.pixFromY(vars[2]+getTotalFy());
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptY);
          SUtil.drawArrow(g, ptVX,ptY,ptVX, ptVY);
      }
      if(showFVector){
          g.setColor(darkBlue);
          int ptVX=canvas.pixFromX(vars[1]+getTotalFx());
          int ptVY=canvas.pixFromY(vars[2]+getTotalFy());
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptVY);
      }
      if(showAComponents){
          g.setColor(lightBlue);
          int ptVX=canvas.pixFromX(vars[1]+vars[5]);
          int ptVY=canvas.pixFromY(vars[2]+vars[6]);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptY);
          SUtil.drawArrow(g, ptVX,ptY,ptVX, ptVY);
      }
      if(showAVector){
          g.setColor(darkBlue);
          int ptVX=canvas.pixFromX(vars[1]+vars[5]);
          int ptVY=canvas.pixFromY(vars[2]+vars[6]);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptVY);
      }
      g.setColor(Color.black);

  }

  public void paintHighlight(Graphics g){
            int ptX=canvas.pixFromX(vars[1]);
      int ptY=canvas.pixFromY(vars[2]);
      g.setColor(highlightColor);
      if(showVComponents){
          g.setColor(lightGreen);
          int ptVX=canvas.pixFromX(vars[1]+vars[3]);
          int ptVY=canvas.pixFromY(vars[2]+vars[4]);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptY);
          SUtil.drawArrow(g, ptVX,ptY,ptVX, ptVY);
      }
      if(showVVector){
          int ptVX=canvas.pixFromX(vars[1]+vars[3]);
          int ptVY=canvas.pixFromY(vars[2]+vars[4]);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptVY);
      }
      if(showFComponents){
          int ptVX=canvas.pixFromX(vars[1]+vars[5]*mass);
          int ptVY=canvas.pixFromY(vars[2]+vars[6]*mass);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptY);
          SUtil.drawArrow(g, ptVX,ptY,ptVX, ptVY);
      }
      if(showFVector){
          int ptVX=canvas.pixFromX(vars[1]+vars[5]*mass);
          int ptVY=canvas.pixFromY(vars[2]+vars[6]*mass);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptVY);
      }
      if(showAComponents){
          int ptVX=canvas.pixFromX(vars[1]+vars[5]);
          int ptVY=canvas.pixFromY(vars[2]+vars[6]);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptY);
          SUtil.drawArrow(g, ptVX,ptY,ptVX, ptVY);
      }
      if(showAVector){
          int ptVX=canvas.pixFromX(vars[1]+vars[5]);
          int ptVY=canvas.pixFromY(vars[2]+vars[6]);
          SUtil.drawArrow(g, ptX,ptY,ptVX, ptVY);
      }
      g.setColor(Color.black);
  }

  public void paintCoordinates(Graphics g, int xpix, int ypix){
     String xStr=format.form(SUtil.chop(vars[1],1.0e-12));
     String yStr=format.form(SUtil.chop(vars[2],1.0e-12));
     g.setColor(Color.black);
     Font f=g.getFont();
     g.setFont(font);
     g.drawString("x:"+xStr,xpix+xCoordOff,ypix-3-yCoordOff);
     g.drawString("y:"+yStr,xpix+xCoordOff,ypix+12-yCoordOff);
     g.setFont(f);
  }

  public final void paintTrail(Graphics g){
      g.setColor(color);
      if(trailSize>1 && trail.npoints>1){
          if(footPrints==0)
              g.drawPolyline(trail.xpoints,trail.ypoints,trail.npoints);
          else{
              for(int i=0;i<trail.npoints;i+=footPrints){
                  g.drawLine(trail.xpoints[i]-2,trail.ypoints[i],trail.xpoints[i]+2,trail.ypoints[i]);
                  g.drawLine(trail.xpoints[i],trail.ypoints[i]-2,trail.xpoints[i],trail.ypoints[i]+2);
              }
          }
      }
  }

  public void paintLabel(Graphics g, int xpix, int ypix){
    if(label==null) return;
    g.setColor(Color.black);
    Font f=g.getFont();
    g.setFont(font);
    FontMetrics fm=g.getFontMetrics(font);
    int off1=fm.stringWidth(label)/2;
    int off2=fm.getHeight()/2;
    g.drawString(label,xpix+off1,ypix+off2);
    g.setFont(f);
  }

  public void paintGhosts(Graphics g){
      // paint ghost images before the thing.
      if(ghost && footPrints>0 && trailSize>1 && trail.npoints>1){
          g.setColor(SUtil.veryPaleColor(color));
          for(int i=0;i<trail.npoints;i+=footPrints)
            g.fillOval((trail.xpoints[i]-s),(trail.ypoints[i]-s),2*s,2*s);
      }
  }

  public final double getTime(){return vars[0];}
  public final double getMass(){return mass;}

  public final void setTrailSize(int n){trailSize=n; clearTrail();}

  public final void setTrailSize(int n, int offset){trailSize=n; trailOffset=offset; clearTrail();}

  public void clearTrail(){
      trailCounter=0;
      if(trail!=null && trail.npoints==0){;}    // already have an empgy polygon so no need to allocate.
        else trail=new Polygon();
      if(canvas.osi!=null)incTrail();
  }


  public final void incTrail(){
      if (trail==null || trailSize<1) return;
      if (trailCounter<trailOffset){
         trailCounter++;
         return;
      }
      trailCounter++;
      int x=0;
      int y=0;
      if(canvas.getReferenceObject()!=null){
        x=canvas.pixFromX(vars[1]-canvas.getReferenceObject().getX() );
        y=canvas.pixFromY(vars[2]-canvas.getReferenceObject().getY() );
      }else{
        x=canvas.pixFromX(vars[1]);
        y=canvas.pixFromY(vars[2]);
      }
      if (trail.npoints<trailSize){
          trail.addPoint(x,y);
      }else{
          System.arraycopy(trail.xpoints, 1, trail.xpoints, 0, trailSize-1);
          System.arraycopy(trail.ypoints, 1, trail.ypoints, 0, trailSize-1);
          trail.xpoints[trailSize-1]=x;
          trail.ypoints[trailSize-1]=y;
      }
  }

  public void setTime(double t, double dt){
      if(dynamic){
          vars[0]=t;
          vars[1]=x0;   // position
          vars[2]=y0;
          vars[3]=vx0;  // set velocity
          vars[4]=vy0;
          if(xForce!=null) vars[5]=xForce.evaluate(new double[] {vars[0],x0,y0,vx0,vy0} );
          if(yForce!=null) vars[6]=yForce.evaluate(new double[] {vars[0],x0,y0,vx0,vy0} );
      }else setVars(t,dt);
      clearTrail();
  }

  void setVars(double t, double dt){
      double x0=0, x1=0,x2=0,x3=0, x4=0;
      double y0=0, y1=0,y2=0,y3=0, y4=0;
      vars[0]=t;
      vars[5]=0;
      vars[6]=0;
      vars[7]=mass;
      if(xFunc==null || yFunc==null) return;
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
      dynamic=false;
      xForce=null;
      yForce=null;
      if(interactions!=null)interactions.removeAllElements();
      if(canvas==null)vars[0]=0;
          else vars[0]=canvas.time;
      vars[1]=0;
      vars[2]=0;
      vars[3]=0;  // set velocity and acceleration to zero.
      vars[4]=0;
      vars[5]=0;
      vars[6]=0;
      vars[7]=mass;
      if(xStr==null || yStr==null){
          this.xStr=xStr;
          this.yStr=yStr;
          xFunc = null;
          yFunc = null;
          return true;
      }
      this.xStr=xStr;
      this.yStr=yStr;
      Parser xFunc = new Parser(1);
      xFunc.defineVariable(1,"t"); // define the variable
      xFunc.define(xStr);
      xFunc.parse();
      if(xFunc.getErrorCode() != Parser.NO_ERROR){
          System.out.println("Failed to parse trajectory x(t): "+xStr);
          System.out.println("Parse error: " + xFunc.getErrorString() +
                   " at function 1, position " + xFunc.getErrorPosition());
          return false;
      }
      this.xFunc=xFunc;
      Parser yFunc = new Parser(1);
      yFunc.defineVariable(1,"t"); // define the variable
      yFunc.define(yStr);
      yFunc.parse();
      if(yFunc.getErrorCode() != Parser.NO_ERROR){
           System.out.println("Failed to parse trajectory y(t): "+yStr);
           System.out.println("Parse error: " + yFunc.getErrorString() +
                   " at function 1, position " + yFunc.getErrorPosition());
           return false;
      }
      this.yFunc=yFunc;
      setTime(vars[0],0.001);   // setTime will also clear trail.
      //clearTrail();
      if(canvas!=null && canvas.autoRefresh)canvas.repaint();
      return true;
  }

  public boolean setForce(String fxStr,String fyStr, double x0, double y0, double vx0, double vy0){
      this.x0=x0;
      this.y0=y0;
      this.vx0=vx0;
      this.vy0=vy0;
      if(canvas==null)vars[0]=0;
          else vars[0]=canvas.time;
      vars[1]=x0;
      vars[2]=y0;
      vars[3]=vx0;  // set velocity and acceleration to zero.
      vars[4]=vy0;
      vars[5]=0;
      vars[6]=0;
      vars[7]=mass;
      if(fxStr==null || fyStr==null){
          this.fxStr=fxStr;
          this.fyStr=fyStr;
          xForce = null;
          yForce = null;
          return true;
      }
      this.fxStr=fxStr;
      this.fyStr=fyStr;
      Parser xForce = new Parser(8);
      xForce.defineVariable(1,"t"); // define the variable
      xForce.defineVariable(2,"x"); // define the variable
      xForce.defineVariable(3,"y"); // define the variable
      xForce.defineVariable(4,"vx"); // define the variable
      xForce.defineVariable(5,"vy"); // define the variable
      xForce.defineVariable(6,"ax"); // define the variable
      xForce.defineVariable(7,"ay"); // define the variable
      xForce.defineVariable(8,"m"); // define the variable
      xForce.define(fxStr);
      xForce.parse();
      if(xForce.getErrorCode() != Parser.NO_ERROR){
          System.out.println("Failed to parse force fx(t,x,y,vx,vy): "+fxStr);
          System.out.println("Parse error: " + xForce.getErrorString() +
                   " at function 1, position " + xForce.getErrorPosition());
          return false;
      }
      this.xForce=xForce;
      Parser yForce = new Parser(8);
      yForce.defineVariable(1,"t"); // define the variable
      yForce.defineVariable(2,"x"); // define the variable
      yForce.defineVariable(3,"y"); // define the variable
      yForce.defineVariable(4,"vx"); // define the variable
      yForce.defineVariable(5,"vy"); // define the variable
      yForce.defineVariable(6,"ax"); // define the variable
      yForce.defineVariable(7,"ay"); // define the variable
      yForce.defineVariable(8,"m"); // define the variable
      yForce.define(fyStr);
      yForce.parse();
      if(yForce.getErrorCode() != Parser.NO_ERROR){
           System.out.println("Failed to parse trajectory fy(t,x,y,vx,vy): "+fyStr);
           System.out.println("Parse error: " + yForce.getErrorString() +
                   " at function 1, position " + yForce.getErrorPosition());
           return false;
      }
      this.yForce=yForce;
      // vars[0] already set
      vars[1]=x0;
      vars[2]=y0;
      vars[3]=vx0;
      vars[4]=vy0;
      vars[5]=xForce.evaluate(new double[] {vars[0],x0,y0,vx0,vy0} );
      vars[6]=yForce.evaluate(new double[] {vars[0],x0,y0,vx0,vy0} );
      clearTrail();
      dynamic=true;
      // make sure slaves are notified about any change in position.
      Thing t=null;
      if(canvas!=null) for( Enumeration e=canvas.things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t!=this && t.myMaster==this )t.setVarsFromMaster();
      }
      if(canvas!=null && canvas.autoRefresh)canvas.repaint();
      return true;
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
      vars[1]=constantRx+constantR*u;
      vars[2]=constantRy+constantR*v;
      if( (-vars[3]*v+vars[4]*u)>0){ // project current speed onto (-v,u) to keep the sense of rotation constant.
        vars[3]=-speed*v;
        vars[4]=speed*u;
      }else{
        vars[3]=speed*v;
        vars[4]=-speed*u;
      }
      return;
  }

  private final void enforceConstraintOnX(){
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

    final boolean enforceConstraintOnXY(){
      //if(xFunc!=null || yFunc!=null) return;  // Trajectories override constraints.
      //else
      if(constrainX){
          enforceConstraintOnX();
          return true;
      }else if(constrainY){
          enforceConstraintOnY();
          return true;
      }else if(constrainR){
          enforceConstraintOnR();
          return true;
      }
      return false;
    }

  public boolean setConstrainR(double r,double x, double y){
     constrainX = false;
     constrainY = false;
     constrainR = true;
     constantRx=x;
     constantRy=y;
     constantR=r;
     enforceConstraintOnXY();
     if(canvas.autoRefresh)canvas.repaint();
     return true;
  }

    public boolean setConstrainX(double x,double min, double max){
     constrainX = true;
     constrainY = false;
     constrainR = false;
     constraintMin=min;
     constraintMax=max;
     constantX=x;
     enforceConstraintOnXY();
     if(canvas.autoRefresh)canvas.repaint();
     return true;
  }

  public boolean setConstrainY(double y,double min, double max){
     constrainX = false;
     constrainY = true;
     constrainR = false;
     constraintMin=min;
     constraintMax=max;
     constantY=y;
     enforceConstraintOnXY();
     if(canvas.autoRefresh)canvas.repaint();
     return true;
  }

  void paintConstraintX(Graphics g){
     java.awt.Rectangle r=canvas.getBounds();
     int bottom, top;
     int xpix=canvas.pixFromX(constantX);
     if(constraintMin<constraintMax){ // constraint has max min
        bottom=canvas.pixFromY(constraintMin);
        top=canvas.pixFromY(constraintMax);
    }else{
        top=r.y;
        bottom=r.y+r.height-1;
    }
    g.drawLine(xpix,top,xpix,bottom);
  }

  void paintConstraintY(Graphics g){
     java.awt.Rectangle r=canvas.getBounds();
     int left, right;
     int ypix=canvas.pixFromY(constantY);
     if(constraintMin<constraintMax){ // constraint has max min
        left=canvas.pixFromX(constraintMin);
        right=canvas.pixFromX(constraintMax);
    }else{
        left=r.x; // start on left side of graph
        right=r.x+r.width-1; // right hand side of graph
    }
    g.drawLine(left,ypix,right,ypix);
  }

  void paintConstraintR(Graphics g){
     int xpix=canvas.pixFromX(constantRx);  // the x position of the center of the circle
     int ypix=canvas.pixFromY(constantRy);  // the y position of the center of the circle
     int rpix=canvas.pixFromX(constantRx+constantR)-xpix;  // the radius of the circle in pix
     xpix=xpix-rpix+xDisplayOff;
     ypix=ypix-rpix-yDisplayOff;
     g.drawOval(xpix,ypix,2*rpix+1,2*rpix+1);
  }

    // data listener methods

  public void clearSeries(int sid){;}
  public void deleteSeries(int sid){;}
  public void addDatum(SDataSource ds, int sid, double x, double y ){
    if(canvas.owner.debugLevel>127){
      System.out.println("Animator.addDatum sid="+sid+"  x="+x+"  y="+y);
    }
    if(sid>2){
      setProperties(sid,x,y);
    } else if(sid==2){
      setW(x);
      setH(y);
    }else{
      setXY(x,y);
    }
    updateDynamics();
    updateMySlaves();
    if(canvas!=null && canvas.owner!=null && !this.canvas.owner.clock.isRunning()) { // clock will force repaint if running
       if(this.canvas instanceof Canvas) ((Canvas)canvas).repaint();
    }
  }
  public void addData(SDataSource ds, int sid, double x[], double y[] ){
    int last=x.length-1;
    if(canvas.owner.debugLevel>127){
      System.out.println("Animator.addDatum sid="+sid+"  x="+x[last]+"  y="+y[last]);
    }
    if(sid>2){
      setProperties(sid,x[last],y[last]);
    } else if(sid==2){
      setW(x[last]);
      setH(y[last]);
    }else{
       this.setXY(x[last],y[last]);
    }
    updateDynamics();
    updateMySlaves();
    if(canvas!=null && canvas.owner!=null && !this.canvas.owner.clock.isRunning()) { // clock will force repaint if running
        if(this.canvas instanceof Canvas) ((Canvas)canvas).repaint();
     }
  }
}
