package edu.davidson.display;

import java.awt.Color;
//import java.awt.*;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.tools.SApplet;
import edu.davidson.numerics.Parser;


public class TextThing extends Thing {

  private String text;
  private String calcStr;
  private double[] textVars= new double[3];
  Parser calcFunc;
  private String valStr="";
  double chopValue=1.0e-12;

  public TextThing(SApplet owner,SScalable sc, String txt, double x, double y){
      this( owner, sc,  txt, null,  x,  y);
  }
  public TextThing(SApplet owner,SScalable sc, String txt, String cs, double x, double y){
    super(sc,x,y);
    font=new Font("Helvetica",Font.BOLD,14);
    text=txt;
    applet=owner;
    calcStr=cs;
    if (calcStr==null || calcStr.equals("") ) return;
    calcFunc = new Parser(3);
    calcFunc.defineVariable(1,"t"); // define the variable
    calcFunc.defineVariable(2,"x"); // define the variable
    calcFunc.defineVariable(3,"y"); // define the variable
    calcFunc.define(calcStr);
    calcFunc.parse();
    if(calcFunc.getErrorCode() != Parser.NO_ERROR)
    {
      System.out.println("Failed to parse calc-text: "+calcStr);
      System.out.println("Parse error: " + calcFunc.getErrorString() +
          " at function 1, position " + calcFunc.getErrorPosition());
      return;
    }
  }

  public void setProperties(int sid, double a,double b){
    if(sid==0 || sid==1){
      setXY(a, b);
    } else if(sid==2){
      double val=0;
      try{
        val=edu.davidson.tools.SUtil.chop(a,chopValue);
        } catch(Exception e){}
        valStr=format.form(val);
    }else if(sid==6){
     setX(a);
    }else if(sid==7){
     setY(b);
    }
  }

  public final String getText(){
    double val=0;
    if (calcStr==null || calcStr.equals("")) return text+valStr;
    if(applet!=null){
        textVars[0]=applet.clock.getTime();
    }
    textVars[1]=x;
    textVars[2]=y;
    try{
        val=calcFunc.evaluate(textVars);
        val=edu.davidson.tools.SUtil.chop(val,chopValue);
    } catch(Exception e){}
    return text+" "+format.form(val);
  }

  public void paint(Graphics g){
      if(!visible) return;
      Font f=g.getFont();
      g.setFont(font);
      int ptX=canvas.pixFromX(x)+xDisplayOff;
      int ptY=canvas.pixFromY(y)-yDisplayOff;
      g.setColor(color);
      g.drawString(getText(),ptX,ptY);
      g.setColor(Color.black);
      g.setFont(f);
  }
}
