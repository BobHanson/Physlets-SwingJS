package pipes;

import java.awt.Color;
import java.awt.Graphics;

//import java.awt.*;
import edu.davidson.tools.*;

public class Thing implements SDataSource{
  //int vars[] = new int[5];
  Pipe owner=null;
  SApplet appletOwner=null;
  String[] varStrings = {"t","x","p"};
  double[][] vars = new double[1][3];
  boolean isMoveable;
  //boolean isPiston;
  //boolean isDetector=false;
  int x;
  int y;
  int w;
  int h;
  boolean enabled=true;
  Color color = Color.blue;

  public  Thing(int x, int y, int width, int height, Color c, Pipe o, boolean e,boolean im) {
  this.x = x;
  this.y = y;
  this.w = width;
  this.h = height;
  color = c;
  owner=o;
  enabled = e;
  appletOwner = owner.getOwner();
  isMoveable=im;
  }
  /*
  public Thing(int x, int y, int width, int height, Color c, boolean m,boolean ip, Pipe o) {
  this.isMoveable = m;
  this.isPiston=ip;
  this.x = x;
  this.y = y;
  this.w = width;
  this.h = height;
  color = c;
  if ((m)&&(!ip)) {try{SApplet.addDataSource(this);} catch(Exception e){e.printStackTrace();}}
  owner=o;
  appletOwner = owner.getOwner();
  }
  */


  //public void update(Graphics g){
  //  paint(g);
  //}

  public double[][]getVariables(){
    vars[0][0]=owner.time;              //time                      //add to x, xorigin
    vars[0][1]=(x-owner.xorigin)*owner.ppu;            //x
    vars[0][2]=owner.pArray[x]; //pressure
    return vars;
    }

  public String[] getVarStrings(){
    return varStrings;
    }
  /**
  *
  * Returns hashcode of thing
  *
  *
  */
  public int getID(){return hashCode();}
  /**
  *
  * Sets Owner of thing
  * @param SApplet  extend Applet
  *
  */
  public void setOwner(SApplet owner){}
  /**
  *
  * Returns Owning applet
  *
  *
  */
  public SApplet getOwner(){return appletOwner;}

  /**
  *
  * Method tells whether or not coordinate pair is inside Thing
  *
  * @param xpos  int x-position of cursor
  * @param ypos   int y-position of cursor
  */
  public boolean isInside(int xpos, int ypos){
    if ( (xpos<x+w/2) && (xpos>x-w/2) && (ypos>y-h/2) && (ypos<y+h/2) ) return true;
    else return false;

  }

  /**
  *
  * Sets position of upper right corner of thing
  *
  * @param xpos
  * @param ypos
  */
  public void setPosXY(int xpos, int ypos){
  this.x=xpos;
  this.y=ypos;
  }
  /**
  *
  * Sets position of  left edge thing
  *
  * @param xpos
  *
  */
  public void setPosX(int xpos){
  this.x=xpos;

  }
  /**
  *
  * Sets position of  upper edge thing
  *
  * @param xpos
  *
  */
  public void setPosY(int ypos){
  this.y=ypos;
  }

  public void setH(int height){
  this.h=height;
  }

  public void setW(int width){
  this.w=width;
  }

  public void setColor(Color c){this.color=c;}

  /**
  *
  * Function allows thing to be moveable
  *
  * @param m boolean
  *
  */
  public void setIsMoveable(boolean m){
  this.isMoveable=m;
  }

  public Color getColor(){return color;}
  public int getPosX() {return x;}
  public int getPosY() {return y;}
  public int getWidth() {return w;}
  public int getHeight() {return h;}

  public void paintOS(Graphics g){
    g.setColor(color);
    g.fillRect(x,y,w,h);

  }

  public void step(){
  }

}