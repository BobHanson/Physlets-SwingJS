package optics;


import java.awt.*;
import edu.davidson.numerics.Parser;
//import edu.davidson.display.Format;
import edu.davidson.tools.SUtil;
import edu.davidson.display.Thing;
import edu.davidson.tools.SApplet;


public class CalcThing extends edu.davidson.display.Thing {
  private String calcStr;
  private String text;
  private double[] tempVars= new double[4];
  Parser calcFunc=null;
  Bench bench=null;
  private String valStr="";
  double chopValue=1.0e-8;

  public CalcThing(SApplet owner, Bench panel, String txt, String cs, double x, double y){
    super(panel,x,y);
    color=Color.white;
    bench=panel;
    applet=owner;
    font=new Font("Helvetica",Font.BOLD,14);
    text=txt;
    calcStr=cs;
    if (calcStr==null ||calcStr.equals("") ){
        calcStr=null;
        calcFunc=null;
        return;
    }
    calcFunc = new Parser(4);
    calcFunc.defineVariable(1,"x"); // define the variable
    calcFunc.defineVariable(2,"y"); // define the variable
    calcFunc.defineVariable(3,"f"); // define the variable
    calcFunc.defineVariable(4,"n"); // define the variable
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
    if (calcStr==null || calcStr.equals("")) return text+valStr;
    double val=0;
    Thing master=this.getMaster();
    tempVars[0]=x;
    tempVars[1]=y;
    tempVars[2]=0;
    tempVars[3]=0;
    if(master!=null && master instanceof OpticElement) {
        tempVars[2]=((OpticElement)master).focalLength/bench.pixPerUnit;;
        tempVars[3]=((OpticElement)master).indexOfRefraction;
    }
    try{
        val=calcFunc.evaluate(tempVars);
        val=SUtil.chop(val,chopValue);
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
