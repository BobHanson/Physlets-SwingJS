package animator4;

//import a2s.*;
import java.awt.Graphics;

import edu.davidson.numerics.Parser;


public class ExShell extends Shell {
  //private String rStr;
  Parser rFunc;

  public ExShell(AnimatorCanvas o,int tickness,String rStr, String xStr, String yStr) {
     super(o,10, xStr,yStr);   // the diameter is set to 10 but it should have no effect.
     s=tickness;

     rFunc = new Parser(1);
     rFunc.defineVariable(1,"t"); // define the variable
     rFunc.define(rStr);
     rFunc.parse();
     if(rFunc.getErrorCode() != Parser.NO_ERROR){
       System.out.println("Failed to parse horzizontal component of vector: "+rStr);
       System.out.println("Parse error: " + rFunc.getErrorString() +
          " at function 1, position " + rFunc.getErrorPosition());
        return;
     }
  }

  double getRad(double t){
    double r=0;
    try{
        r=rFunc.evaluate(t);
    }catch(Exception e){}
    return Math.abs(r);
  }

  public void paint(Graphics g){
      if(!visible)return;
      w=(int)(canvas.pixPerUnit*getRad(vars[0])*2);
      h=w;
      super.paint(g);
    }

  public void paintHighlight(Graphics g){
      if(!visible)return;
      w=(int)(canvas.pixPerUnit*getRad(vars[0])*2);
      h=w;
      super.paintHighlight(g);
    }

  public boolean isInsideThing(int xPix, int yPix){
      w=(int)(canvas.pixPerUnit*getRad(vars[0])*2);
      h=w;
      return super.isInsideThing(xPix,yPix);
  }

  public void paintGhosts(Graphics g){
      if(!visible)return;
      w=(int)(canvas.pixPerUnit*getRad(vars[0])*2);
      h=w;
      super.paintGhosts(g);
  }

} 