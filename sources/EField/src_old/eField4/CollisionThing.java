package eField4;

import java.awt.*;
import edu.davidson.tools.SApplet;

/**
 * Title:        CollisionThing<p>
 * Description:  A data source that is activated when a collision takes place.<p>
 * Copyright:    Copyright (c) 2000 by Wolfgang Christian<p>
 * @author W. Christian
 * @version 1.0
 */
public class CollisionThing extends Thing {
    String[] blockStrings= new String[]{"blocked","x","y"};
    boolean block=true;

  public CollisionThing(OdeCanvas panel){
    super(panel,0,0,0,0);
     varStrings= new String[]{"t","x","y"};
     ds=new double[1][3];  // the datasource state variables t,x,y
     ds[0][0]=0;
     ds[0][1]=0;
     ds[0][2]=0;
  }

  public void paint(Graphics g){;}

  public  void setXYT(double x,double y,double t){
     ds[0][0]=t;
     ds[0][1]=x;
     ds[0][2]=y;
  }

  public double[][] getVariables(){
     return ds;
  }

  final void setBlock(boolean block){
      this.block=block;
  }

  public String[] getVarStrings(){
        if(block) return blockStrings;
        else return varStrings;
  }
}