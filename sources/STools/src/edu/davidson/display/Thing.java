package edu.davidson.display;


import java.awt.*;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Enumeration;
import edu.davidson.tools.*;


public class Thing extends Object implements SStepable, edu.davidson.tools.SDataSource, edu.davidson.tools.SDataListener  {
  static Color      darkGreen=new Color(0,128,0);
  static Color      lightGreen=new Color(128,255,128);

  protected boolean noDrag=true;
  protected boolean visible=true;
  protected boolean resizable=true;
  protected boolean highlight=false;
  protected int hotSpot=0;
  protected SApplet applet=null;
  protected int w=5,h=5;  // the width and height
  protected  int s=5; // size of thing
  protected Color color=Color.black;  // color of the drawing
  protected double x,y,time;               // position of the object in world coordiantes.
  protected SScalable canvas;
  protected Constraint constraint=null;
  protected Font       font=new Font("Helvetica",Font.BOLD,14);
  protected Format     format= new Format("%-+6.3g");
  // offset the display
  protected int xDisplayOff=0;
  protected int yDisplayOff=0;
  // data source variables
  protected String[] varStrings= new String[]{"x","y","w","h","t"};
  protected double[][] ds=new double[1][5];  // the datasource state variables x,y;
  protected String label=null;

  // Slave stuff
  Thing myMaster=null;// the hashCode of another Thing that controls the position of this object.
  Vector mySlaves= new Vector();

  Color highlightColor=Color.red;  // color of the drawing
  Polygon trail= new Polygon();
  int trailSize=0;




  public Thing(SScalable sc){
      this.canvas=sc;
      try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
      try{SApplet.addDataListener(this); }catch (Exception e){e.printStackTrace();}
  }

  public Thing(SScalable sc, double x, double y){
     this(sc);
     this.x=x;
     this.y=y;
  }


  public int getID(){return hashCode();}
  public final boolean isNoDrag(){return noDrag;}
  public final void setNoDrag(boolean nd){noDrag=nd;}
  public void setOwner(SApplet o){
      applet=o;
  }
  public SApplet getOwner(){return applet;}
  public String[] getVarStrings(){return varStrings;}
  public boolean isInsideThing(int i, int j){return false;}
  public final int getSize(){return s;}
  public void setSize(int sz){s=sz;}
  public final Color getColor(){return color;}
  public void setColor(Color c){color=c;}
  public final void setDragable(boolean d){noDrag= !d;}
  public void setVisible(boolean v){visible=v;}
  public void setHighlight(boolean hl){highlight=hl;}
  public final boolean isHighlight(){return highlight;}

  public final boolean isVisible(){return visible;}
  public void setResizable(boolean rs){resizable=rs;}
  public final boolean isResizable(){return resizable;}
  public final Thing getMaster(){return myMaster;}
  public final Vector getSlaves(){return mySlaves;}
  public void setLabel(String l){label=l;}
  public String getLabel(){return label;}

  public final boolean setFormat(String str){
     try{format= new Format(str);}
     catch (IllegalArgumentException e){return false;}
     return true;
  }

  public final boolean setFont(Font f){
     font=f;
     if(font==null) font=new Font("Helvetica",Font.BOLD,14);
     return true;
  }

  public void setDisplayOff(int xOff,int yOff){
    xDisplayOff=xOff;
    yDisplayOff=yOff;
  }

  public double getX(){return x;}
  public void setX(double x){
     if(myMaster!=null){this.x=myMaster.x; return;}
     this.x=x;
     if(constraint!=null)constraint.enforceConstraint(this);
  }
  public void setXY(double x,double y){
      if(myMaster!=null){
        this.x=myMaster.x;
        this.y=myMaster.y;
        return;
      }
      this.x=x;this.y=y;
      if(constraint!=null)constraint.enforceConstraint(this);
      updateMySlaves();
  }

  public void setProperties(int sid, double alpha,double beta){
    if(sid==0 || sid==1){
      setXY(alpha, beta);
    } else if(sid==2){
      w=(int)alpha;
      h=(int)beta;
    }else if(sid==6){
     setX(alpha);
    }else if(sid==7){
     setY(beta);
    }
  }

  public void dragMe(double x,double y){
      setXY(x,y);
  }

  public final int getXPix(){return canvas.pixFromX(x);}


  public final double getY(){return y;}
  public void setY(double y){
      if(myMaster!=null){this.y=myMaster.y; return;}
      this.y=y;
      if(constraint!=null)constraint.enforceConstraint(this);
  }

  public void setTime(double t){
    time=t;
  }

  public final int getYPix(){return canvas.pixFromY(y);}

  public final int getHeight(){return h;}
  public void setHeight(int h){this.h=h;}

  public final int getWidth(){return w;}
  public void setWidth(int w){this.w=w;}

  public void setConstraint(Constraint c){
      if(c==null) return;
      this.constraint=c;
      constraint.enforceConstraint(this);
  }

  public void addSlave(Thing slave){
        if(myMaster==slave) myMaster=null;  // prevent slave=master and master=slave.
        mySlaves.addElement(slave);
        slave.myMaster=this;
        slave.setVarsFromMaster();
  }

  public void setVarsFromMaster(){
    if(myMaster==null) return;
      //if(this instanceof TextThing) myMaster.calculateState();
      x=myMaster.x;
      y=myMaster.y;
      Thing t=null;
      for( Enumeration e=mySlaves.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t!=this && t.myMaster==this )t.setVarsFromMaster();
      }
  }

  public void updateMySlaves(){
    Thing slave=null;
    for( Enumeration e=mySlaves.elements(); e.hasMoreElements();){
         slave= (Thing) e.nextElement();
         slave.setVarsFromMaster() ;
         }
  }

  public void paintMySlaves(Graphics g){
    Thing slave=null;
    for( Enumeration e=mySlaves.elements(); e.hasMoreElements();){
         slave= (Thing) e.nextElement();
         slave.paint(g) ;
    }
  }

  public void step(double dt,double time){
    this.time=time+dt;
  }

  public double[][] getVariables(){
     ds[0][0]=x;  //x
     ds[0][1]=y;  //y
     ds[0][2]=w;  //x
     ds[0][3]=h;  //y
     ds[0][4]=time;  //t
     return ds;
  }

  public void paint(Graphics g){ ;}  // override me!
  public void paintHighlight(Graphics g){paint(g);}

  // data listener methods

  public void clearSeries(int sid){;}
  public void deleteSeries(int sid){;}
  public void addDatum(SDataSource ds, int sid, double x, double y ){
    this.setProperties(sid,x,y);
    if(this.canvas instanceof Canvas) ((Canvas)canvas).repaint();
  }
  public void addData(SDataSource ds, int sid, double x[], double y[] ){
     int last=x.length-1;
     this.setProperties(sid,x[last],y[last]);
  }


}
