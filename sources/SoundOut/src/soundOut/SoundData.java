// class to keep track of sound amplitude vector
package soundOut;
import edu.davidson.numerics.Parser;

public class SoundData {
  private int numPts;
  private Parser ampFunc;
  private String ampStr;
  private double[] ampVec;

  public SoundData(int n) {
      numPts=n;
      ampVec=new double[n];
      ampStr="sin(400*pi*t)";
      ampFunc = new Parser(1);
      ampFunc.defineVariable(1,"t"); // define the variable
      addAmpFunc(ampStr);
  }
  public double[] getAmpVec(){
      return ampVec;
  }

  public void addAmpFunc(String f){
    double TIM_MULT =1.0/8000.0;
    double t=0, y=0;
    ampStr=f;
    ampFunc.define(ampStr);
    ampFunc.parse();
    if(ampFunc.getErrorCode() != Parser.NO_ERROR)
    {
        System.out.println("Failed to parse amp(t): "+ampStr);
        System.out.println("Parse error: " + ampFunc.getErrorString() +
                   " at function 1, position " + ampFunc.getErrorPosition());
        return;
    }
    for (int i = 0; i < numPts; i++)
    {
        t=i*TIM_MULT;
        try{
           y=ampFunc.evaluate(t);
        }catch(Exception e){y=0;}
        ampVec[i]+=y;
    }
  }

  public void newAmpFunc(String f){
    for (int i = 0; i < numPts; i++) ampVec[i]=0;
    addAmpFunc(f);
  }

}