package poisson;
import edu.davidson.tools.SApplet;


public class TestChargeThing extends edu.davidson.display.CircleThing {
 double q=1;
 PoissonPanel poissonPanel;
 public TestChargeThing(SApplet owner, PoissonPanel sc, double x,double y,int radius){
     super( owner,  sc,  x, y, radius);
     poissonPanel=sc;
     varStrings= new String[]{"x","y","ax","ay","v"};
     ds=new double[1][5];  // the datasource state variables x,y,charge,voltage,potential energy;
 }

   public double[][] getVariables(){

     ds[0][0]=x;  //x
     ds[0][1]=y;  //y
     ds[0][2]=-poissonPanel.dudx(x,y);  //ax
     ds[0][3]=-poissonPanel.dudy(x,y);  //ay
     ds[0][4]=poissonPanel.getPotential(x,y);  //voltage
     return ds;
  }


} 