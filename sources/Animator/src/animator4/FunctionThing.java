package animator4;

//import a2s.*;
import java.awt.Graphics;

import edu.davidson.numerics.Parser;


public class FunctionThing extends Thing {

  private String	xStr;
  private String	yStr;
  private Parser xFunc;
  private Parser yFunc;
  private int num=10;
  private double start;
  private double stop;

  public FunctionThing(AnimatorCanvas o, int n, double start_, double stop_, String xStr_,String yStr_){
      super(o);
      start=start_;
      stop=stop_;
      num=n;

      xStr=xStr_;
      yStr=yStr_;
      xFunc = new Parser(2);
      xFunc.defineVariable(1,"s"); // define the variable
      xFunc.defineVariable(2,"t"); // define the variable
      xFunc.define(xStr);
      xFunc.parse();
      if(xFunc.getErrorCode() != Parser.NO_ERROR)
      {
          System.out.println("Failed to parse x(s,t): "+xStr);
          System.out.println("Parse error: " + xFunc.getErrorString() +
                   " at function x, position " + xFunc.getErrorPosition());
      }
      yFunc = new Parser(2);
      yFunc.defineVariable(1,"s"); // define the variable
      yFunc.defineVariable(2,"t"); // define the variable
      yFunc.define(yStr);
      yFunc.parse();
      if(yFunc.getErrorCode() != Parser.NO_ERROR)
      {
        System.out.println("Failed to parse y(s,t): "+yStr);
        System.out.println("Parse error: " + yFunc.getErrorString() +
                   " at function y, position " + yFunc.getErrorPosition());

      }
  }

  final double getX(double s, double t){
      double x=0;
      try{
          x=xFunc.evaluate(s,t);
      }catch(Exception e){}
      return x;
  }

  final double getY(double s, double t){
      double y=0;
      try{
	        y=yFunc.evaluate(s,t);
      }catch(Exception e){}
      return y;
  }

  public void paint(Graphics g){
      if(!visible)return;
      g.setColor(color);
      double s=start;
      int ptX0=(int)Math.round(canvas.pixFromX(getX(start,canvas.time)))+xDisplayOff;
      int ptY0=(int)Math.round(canvas.pixFromY(getY(start,canvas.time)))-yDisplayOff;
      double ds=(stop-start)/(num-1);
      for(int j=0;j<num;j++){
           int ptX1=(int)Math.round(canvas.pixFromX(getX(s,canvas.time)))+xDisplayOff;
           int ptY1=(int)Math.round(canvas.pixFromY(getY(s,canvas.time)))-yDisplayOff;
           g.drawLine(ptX0,ptY0,ptX1,ptY1);
           ptX0=ptX1;
           ptY0=ptY1;
           s=s+ds;
      }
  }
} 