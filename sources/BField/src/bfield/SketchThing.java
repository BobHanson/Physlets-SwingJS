
package bfield;

import edu.davidson.display.TrailThing;
import edu.davidson.tools.*;
/**
 * The class <code>SketchThing</code> produces the data source variables for the
 * sketch cursor..
 *
 * @author  Wolfgang Chrsitian
 * @version 1.0, 1 Oct 2000
 */
public class SketchThing extends TrailThing {
  FieldPanel fieldPanel;

  public SketchThing(SApplet owner,FieldPanel fp, int s) {
      super(owner, fp,s);
      fieldPanel=fp;
      //redefine the data soruce variables
      varStrings= new String[]{"t","x","y","bx","by"};
      ds=new double[1][5];  // the datasource state variables
  }


  final public double[][] getVariables(){
     ds[0][0]=applet.clock.getTime();  //x
     ds[0][1]=x;  //x
     ds[0][2]=y;  //y
     ds[0][3]=fieldPanel.getBx(x,y,null);  //Fx
     ds[0][4]=fieldPanel.getBy(x,y,null);;   //fy
     return ds;
  }
}