package animator4;

import java.awt.Graphics;

import edu.davidson.tools.SApplet;
/**
 * Title:        CollisionDataSource<p>
 * Description:  A data source that is activated when a collision takes place.<p>
 * Copyright:    Copyright (c) 2000 by Wolfgang Christian<p>
 * @author W. Christian
 * @version 1.0
 */
public class CollisionDataSource extends Thing {
    SApplet owner;
    String[] blockStrings= new String[]{"blocked","x","y"};
    boolean block=true;

    public CollisionDataSource(AnimatorCanvas c){
     super(c,"0","0");
     canvas=c;
     owner=c.owner;
     varStrings= new String[]{"t","x","y"};
     ds=new double[1][3];  // the datasource state variables t,x,y
     ds[0][0]=0;
     ds[0][1]=0;
     ds[0][2]=0;
    }

    public final void paint(Graphics g){;}

    final void setBlock(boolean block){
      this.block=block;
    }

    public  void setXY(double x,double y){
     ds[0][0]=owner.clock.getTime();
     ds[0][1]=x;
     ds[0][2]=y;
    }

    public double[][] getVariables(){return ds;}
    public String[] getVarStrings(){
        if(block) return blockStrings;
        else return varStrings;
    }

}