package bfield;

import edu.davidson.numerics.Parser;
import edu.davidson.tools.SApplet;

public class FieldArrow extends edu.davidson.display.ArrowThing {

  private String hStr;   // the horz and vert components of the vector
  private String vStr;
  private double[] tempVars= new double[4];
  Parser hFunc;
  Parser vFunc;
  FieldPanel fieldPanel;

  public FieldArrow(SApplet owner, FieldPanel fp, int s, String h, String v, double x, double y){

    super( owner, fp,s, 0, 0,  x,  y);
    fieldPanel=fp;
    applet=owner;
    this.s=s;             // the size will be the size of the head.
    hStr=h;               // the h component of the arrow
    vStr=v;

    hFunc = new Parser(4);
    hFunc.defineVariable(1,"x"); // define the variable
    hFunc.defineVariable(2,"y"); // define the variable
    hFunc.defineVariable(3,"bx"); // define the variable
    hFunc.defineVariable(4,"by"); // define the variable
    hFunc.define(hStr);
    hFunc.parse();
    if(hFunc.getErrorCode() != Parser.NO_ERROR){
      System.out.println("Failed to parse horzizontal component of vector: "+hStr);
      System.out.println("Parse error: " + hFunc.getErrorString() +
          " at position " + hFunc.getErrorPosition());
      return;
    }

    vFunc = new Parser(4);
    vFunc.defineVariable(1,"x"); // define the variable
    vFunc.defineVariable(2,"y"); // define the variable
    vFunc.defineVariable(3,"bx"); // define the variable
    vFunc.defineVariable(4,"by"); // define the variable

    vFunc.define(vStr);
    vFunc.parse();
    if(vFunc.getErrorCode() != Parser.NO_ERROR){
      System.out.println("Failed to parse vertical component of vector: "+vStr);
      System.out.println("Parse error: " + vFunc.getErrorString() +
          " at position " + vFunc.getErrorPosition());
      return;
    }
  }
  protected double getHorz(){
    double h=0;
    tempVars[0]=x;
    tempVars[1]=y;
    if(getMaster() instanceof Wire){
      tempVars[2]=fieldPanel.getBx(x,y,(Wire) getMaster());
      tempVars[3]=fieldPanel.getBy(x,y, (Wire) getMaster());
    }else{
      tempVars[2]=fieldPanel.getBx(x,y,null);
      tempVars[3]=fieldPanel.getBy(x,y,null);
    }
    try{
        h=hFunc.evaluate(tempVars);
    }catch(Exception e){}
    return h;
  }

  protected double getVert(){
    double val=0;
    tempVars[0]=x;
    tempVars[1]=y;
    if(getMaster() instanceof Wire){
      tempVars[2]=fieldPanel.getBx(x,y,(Wire) getMaster());
      tempVars[3]=fieldPanel.getBy(x,y, (Wire) getMaster());
    }else{
      tempVars[2]=fieldPanel.getBx(x,y,null);
      tempVars[3]=fieldPanel.getBy(x,y,null);
    }
    try{
        val=vFunc.evaluate(tempVars);
    }catch(Exception e){}
    return val;
  }
}
