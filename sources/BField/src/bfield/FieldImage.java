
/**
 * Title:        BField<p>
 * Copyright:    Copyright (c) 2000<p>
 * @author Wolfgang Christian
 * @version
 */
package bfield;

import java.awt.Image;
import edu.davidson.display.ImageThing;
import edu.davidson.tools.*;


public class FieldImage extends ImageThing {

  FieldPanel fieldPanel;

  public FieldImage(SApplet owner, FieldPanel fp, Image im, double x,double y){
    super( owner,  fp, im, x, y);
    fieldPanel=fp;
    varStrings= new String[]{"x","y","bx","by"};
    ds=new double[1][4];  // the datasource state variables
  }

  final public double[][] getVariables(){
     ds[0][0]=x;  //x
     ds[0][1]=y;  //y
     ds[0][2]=fieldPanel.getBx(x,y,null);
     ds[0][3]=fieldPanel.getBy(x,y,null);
     return ds;
  }
}