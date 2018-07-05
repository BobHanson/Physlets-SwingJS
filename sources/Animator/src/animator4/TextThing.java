package animator4;

import a2s.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.numerics.Parser;
import edu.davidson.tools.SUtil;


public class TextThing extends Thing {

  private String calcStr;
  private double[] textVars= new double[10];
  Parser calcFunc;
  double chopValue=1.0e-12;
  //Format format= new Format("%-+6.3g");


  public TextThing(AnimatorCanvas o, String txt, String cs, String xStr, String yStr){

    super(o,xStr,yStr);
    font=new Font("Helvetica",Font.BOLD,14);
    label=txt;
    calcStr=cs;
    if (calcStr==null) return;
    calcFunc = new Parser(10);
    calcFunc.defineVariable(1,"t"); // define the variable
    calcFunc.defineVariable(2,"x"); // define the variable
    calcFunc.defineVariable(3,"y"); // define the variable
    calcFunc.defineVariable(4,"vx"); // define the variable
    calcFunc.defineVariable(5,"vy"); // define the variable
    calcFunc.defineVariable(6,"ax"); // define the variable
    calcFunc.defineVariable(7,"ay"); // define the variable
    calcFunc.defineVariable(8,"m"); // define the variable
    calcFunc.defineVariable(9,"fx"); // define the variable
    calcFunc.defineVariable(10,"fy"); // define the variable
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
    if (calcStr==null) return label;
    for(int i=0; i<8;i++){
      textVars[i]=vars[i];
    }
    if(myMaster!=null && !dynamic){
        textVars[8]=myMaster.getTotalFx();
        textVars[9]=myMaster.getTotalFy();
    }else{
        textVars[8]=getTotalFx();
        textVars[9]=getTotalFy();
    }
    try{
        val=calcFunc.evaluate(textVars);
        val=SUtil.chop(val,chopValue);
    } catch(Exception e){}
    return label+" "+format.form(val);
  }

  void setChop(double value){
     chopValue=value;
  }

  public void paint(Graphics g){
      if(!visible)return;
      Font f=g.getFont();
      g.setFont(font);
      int ptX=(int)Math.round(canvas.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(canvas.pixFromY(vars[2]) )-yDisplayOff;
      g.setColor(color);
      g.drawString(getText(),ptX,ptY);
      g.setColor(Color.black);
      g.setFont(f);
  }
}
