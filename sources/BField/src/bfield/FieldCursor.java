package bfield;

import edu.davidson.display.MarkerThing;
import edu.davidson.tools.*;

public class FieldCursor extends MarkerThing {
   FieldPanel fieldPanel;

  public FieldCursor(SApplet owner, FieldPanel fp, double x,double y,int diameter){
    super( owner,  fp, diameter, x, y);
    fieldPanel=fp;
    varStrings= new String[]{"x","y","bx","by","curl"};
    ds=new double[1][5];  // the datasource state variables
  }

  final public double[][] getVariables(){
     ds[0][0]=x;  //x
     ds[0][1]=y;  //y
     ds[0][2]=fieldPanel.getBx(x,y,null);
     ds[0][3]=fieldPanel.getBy(x,y,null);
     ds[0][4]=fieldPanel.getCurl(x, y, null);
     return ds;
  }

}
