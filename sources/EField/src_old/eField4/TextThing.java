package eField4;
import java.awt.*;
import edu.davidson.numerics.Parser;
import edu.davidson.tools.SUtil;


public class TextThing extends Thing {
  private double[] tempVars= new double[10];
  private String calcStr;
  Parser calcFunc;

  private String text;

  public TextThing(OdeCanvas o, String txt, String cs, double x, double y){

    super(o,x,y,0,0);
    color=Color.black;
    font=new Font("Helvetica",Font.BOLD,14);
    text=txt;
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
    calcFunc.defineVariable(8,"m");  // define the variable
    calcFunc.defineVariable(9,"f");  // define the variable
    calcFunc.defineVariable(10,"p");  // define the variable
    calcFunc.define(calcStr);
    calcFunc.parse();
    if(calcFunc.getErrorCode() != calcFunc.NO_ERROR)
    {
      System.out.println("Failed to parse calc-text: "+calcStr);
      System.out.println("Parse error: " + calcFunc.getErrorString() +
          " at function 1, position " + calcFunc.getErrorPosition());
      return;
    }
  }

  public final String getText(){
    double val=0;
    if (calcStr==null) return text;
    tempVars[0]=vars[0];
    tempVars[1]=vars[1];
    tempVars[2]=vars[2];
    tempVars[3]=vars[3];
    tempVars[4]=vars[4];
    tempVars[5]=vars[5];
    tempVars[6]=vars[6];
    tempVars[7]=mass;
    tempVars[8]=flux;
    tempVars[9]=pe;
    try{
        val=calcFunc.evaluate(tempVars);
        val=SUtil.chop(val,1.0e-12);
    } catch(Exception e){}
    return text+" "+format.form(val);
  }

  public void paint(Graphics g){
      if(hideThing) return;
      Font f=g.getFont();
      g.setFont(font);
      g.setColor(color);
      int ptX=(int)Math.round(p.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(p.pixFromY(vars[2]) )-yDisplayOff;
      g.setColor(color);
      g.drawString(getText(),ptX,ptY);
      g.setColor(Color.black);
      g.setFont(f);
  }
}
