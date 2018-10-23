package faraday;



import edu.davidson.numerics.Parser;
//import edu.davidson.display.Format;
import edu.davidson.tools.SUtil;
import edu.davidson.display.Thing;
import edu.davidson.tools.SApplet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class CalcThing extends edu.davidson.display.Thing {
  private double[] tempVars= new double[5];
  private String calcStr;
  Parser calcFunc;
  Schematic schematic;

  private String text;

  public CalcThing(SApplet owner, Schematic panel, String txt, String cs, double x, double y){
    super(panel,x,y);
    schematic=panel;
    applet=owner;
    font=new Font("Helvetica",Font.BOLD,14);
    text=txt;
    calcStr=cs;
    if (calcStr==null) return;
    calcFunc = new Parser(5);
    calcFunc.defineVariable(1,"t"); // define the variable
    calcFunc.defineVariable(2,"x"); // define the variable
    calcFunc.defineVariable(3,"y"); // define the variable
    calcFunc.defineVariable(4,"flux"); // define the variable
    calcFunc.defineVariable(5,"v"); // define the variable
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
    Thing myMaster =getMaster();
    if (calcStr==null || calcFunc==null) return text;
    if(myMaster!=null){
      tempVars[0]=schematic.time;
      tempVars[1]=myMaster.getX();
      tempVars[2]=myMaster.getY();
      if(myMaster instanceof Fluxable){
        tempVars[3]=((Fluxable)myMaster).getFlux();
        tempVars[4]=((Fluxable)myMaster).getVolt();
      }
    }else{
      tempVars[0]=schematic.time;
      tempVars[1]=x;
      tempVars[2]=y;
      tempVars[3]=0;
      tempVars[4]=0;
    }

    try{
        val=calcFunc.evaluate(tempVars);
        val=SUtil.chop(val,1.0e-8);
    } catch(Exception e){}
    return text+" "+format.form(val);
  }

  public void paint(Graphics g){
      if(!visible) return;
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
