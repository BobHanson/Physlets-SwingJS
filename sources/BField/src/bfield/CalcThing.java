package bfield;


import java.awt.*;
import edu.davidson.numerics.Parser;
//import edu.davidson.display.Format;
import edu.davidson.tools.SUtil;
import edu.davidson.tools.SApplet;


public class CalcThing extends edu.davidson.display.Thing {
  private double[] tempVars= new double[7];
  private String calcStr;
  Parser calcFunc;
  FieldPanel fieldPanel=null;

  private String text;

  public CalcThing(SApplet owner, FieldPanel panel, String txt, String cs, double x, double y){
    super(panel,x,y);
    fieldPanel=panel;
    applet=owner;
    font=new Font("Helvetica",Font.BOLD,14);
    text=txt;
    calcStr=cs;
    if (calcStr==null) return;
    calcFunc = new Parser(7);
    calcFunc.defineVariable(1,"x"); // define the variable
    calcFunc.defineVariable(2,"y"); // define the variable
    calcFunc.defineVariable(3,"bx"); // define the variable
    calcFunc.defineVariable(4,"by"); // define the variable
    calcFunc.defineVariable(5,"i"); // define the variable
    calcFunc.defineVariable(6,"curl"); // define the variable
    calcFunc.defineVariable(7,"path"); // define the variable
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
    double current=0;
    double[] bvec = new double[2];
    double curl=0;
    double path=0;
    Wire wire;
    if (calcStr==null || calcFunc==null) return text;
    if(getMaster()!=null && getMaster() instanceof Wire  ){
       wire=(Wire) getMaster();
       current=wire.getCurrent();
       bvec=fieldPanel.getB(x, y, wire);
       curl=fieldPanel.getCurl(x, y, wire);
    }else if(getMaster()!=null && getMaster() instanceof FieldRectangle){
       FieldRectangle frect=(FieldRectangle) getMaster();
       path=frect.doLineIntegral();
       bvec=fieldPanel.getB(x, y, null);
    }else if(getMaster()!=null && getMaster() instanceof FieldCircle){
       FieldCircle fcircle=(FieldCircle) getMaster();
       path=fcircle.doLineIntegral();
       bvec=fieldPanel.getB(x, y, null);
    }else if(getMaster()!=null && getMaster() instanceof FieldBox){
       FieldBox fbox=(FieldBox) getMaster();
       path=fbox.doLineIntegral();
       bvec=fieldPanel.getB(x, y, null);
    }else if(getMaster()!=null &&  getMaster() instanceof FieldShell){
       FieldShell fshell=(FieldShell) getMaster();
       path=fshell.doLineIntegral();
       bvec=fieldPanel.getB(x, y, null);
    }else{
       bvec=fieldPanel.getB(x, y, null);
       curl=fieldPanel.getCurl(x, y, null);;
    }

    tempVars[0]=x;
    tempVars[1]=y;
    tempVars[2]=bvec[0];
    tempVars[3]=bvec[1];
    tempVars[4]=current;
    tempVars[5]=curl;
    tempVars[6]=path;
    try{
        val=calcFunc.evaluate(tempVars);
        val=SUtil.chop(val,1.0e-8);
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
