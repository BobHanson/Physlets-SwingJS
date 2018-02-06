package optics;

import java.awt.*;
import edu.davidson.numerics.Parser;

public class Matrix extends OpticElement {
  Parser parser1=new Parser(2);
  Parser parser2=new Parser(2);

  public Matrix(Bench b, int x, int y, String hStr, String aStr ){
    super(b);

    info = false;
    noDrag = false;
    percentSize = 1.0;
    resizable = false;
    xPosition = x;
    yPosition = y;
    setX(xPosition/(double)bench.pixPerUnit);
    setY( (bench.iheight/2.0-yPosition)/(double)bench.pixPerUnit);

    parser1.defineVariable(1,"h"); // define the variable
    parser1.defineVariable(2,"a"); // define the variable
    parser1.define(hStr);
    parser1.parse();
    if(parser1.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse matrix element 11: "+hStr);
    }

    parser2.defineVariable(1,"h"); // define the variable
    parser2.defineVariable(2,"a"); // define the variable
    parser2.define(aStr);
    parser2.parse();
    if(parser2.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse angle function: "+aStr);
    }


  }


  public double[] transform(double[] v,Rectangle r,int direction){
    double h = (v[0]-r.height/2)/bench.pixPerUnit;  // height above the opic axis
    double theta = Math.atan(v[1]);
    v[0] = r.height/2+parser1.evaluate(h,theta)*bench.pixPerUnit;
    v[1] = (direction)*parser2.evaluate(h,theta);;
    return v;
  }
}
