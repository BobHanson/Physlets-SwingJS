package poisson;

//import java.awt.*;
import edu.davidson.numerics.Parser;
//import edu.davidson.display.Format;
import edu.davidson.tools.SUtil;
import edu.davidson.tools.SApplet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.display.SScalable;


public class CalcThing extends edu.davidson.display.Thing {
  private double[] tempVars= new double[5];
  private String calcStr;
  Parser calcFunc;

  private String text;

  public CalcThing(SApplet owner, SScalable sc, String txt, String cs, double x, double y){
    super(sc,x,y);
    applet=owner;
    font=new Font("Helvetica",Font.BOLD,14);
    text=txt;
    calcStr=cs;
    if (calcStr==null) return;
    calcFunc = new Parser(5);
    calcFunc.defineVariable(1,"x"); // define the variable
    calcFunc.defineVariable(2,"y"); // define the variable
    calcFunc.defineVariable(3,"q"); // define the variable
    calcFunc.defineVariable(4,"v"); // define the variable
    calcFunc.defineVariable(5,"p"); // define the variable
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

  public final String getText(){
    double val=0;
    double q=0,v=0,pe=0;
    PotentialObject po;
    if (calcStr==null || calcFunc==null) return text;
    if(getMaster()!=null && getMaster() instanceof PotentialObject){
       po=(PotentialObject) getMaster();
       q=po.getCharge();
       v=po.getPotential();
       pe=po.getPotentialEnergy();
    }else{
       PoissonPanel panel = ( (Poisson) applet).poissonPanel;
       v=panel.getPotential(x,y);
    }
    tempVars[0]=x;
    tempVars[1]=y;
    tempVars[2]=q;
    tempVars[3]=v;
    tempVars[4]=pe;
    try{
        val=calcFunc.evaluate(tempVars);
        val=SUtil.chop(val,1.0e-12);
    } catch(Exception e){}
    return text+" "+format.form(val);
  }

  public void paint(Graphics g){
      if(!visible) return; if(!visible) return;
      Font f=g.getFont();
      g.setFont(font);
      int ptX=(int)Math.round(canvas.pixFromX(x) )+xDisplayOff;
      int ptY=(int)Math.round(canvas.pixFromY(y) )-yDisplayOff;
      g.setColor(color);
      g.drawString(getText(),ptX,ptY);
      g.setColor(Color.black);
      g.setFont(f);
  }
}
