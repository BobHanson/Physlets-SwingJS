
/**
 * Title:        MouseDataSource<p>
 * Description:  A data source attached to the mouse.<p>
 * Copyright:    Copyright (c) 2000 by Wolfgang Christian<p>
 * @author W. Christian
 * @version 1.0
 */
package animator4;

import a2s.*;
import java.awt.Graphics;

import edu.davidson.tools.SApplet;

public class MouseDataSource extends Thing {
    SApplet owner;
    boolean trackMove=false;
    boolean trackDrag=false;
    boolean trackClick=false;
    boolean trackRelease=false;

    public MouseDataSource(AnimatorCanvas c){
     super(c,"0","0");
     canvas=c;
     owner=c.owner;
     ds[0][0]=0;
     ds[0][1]=0;
     varStrings= new String[]{"x","y"};
     ds=new double[1][2];
    }

    public final void paint(Graphics g){;}

    public double getX(){ return ds[0][0];}
    public double getY(){ return ds[0][1];}

    public  void moveXY(int xpix,int ypix){
      if(!trackMove) return;
      ds[0][0]=canvas.xFromPix(xpix);
      ds[0][1]=canvas.yFromPix(ypix);
      vars[1]=ds[0][0];
      vars[2]=ds[0][1];
      owner.updateDataConnection(this.hashCode());
    }

    public  void dragXY(double x,double y){
      if(!trackDrag) return;
      ds[0][0]=x;
      ds[0][1]=y;
      vars[1]=x;
      vars[2]=y;
      owner.updateDataConnection(this.hashCode());
    }


    public  void clickXY(int xpix,int ypix){
      if(!trackClick)return;
      ds[0][0]=canvas.xFromPix(xpix);
      ds[0][1]=canvas.yFromPix(ypix);
      vars[1]=ds[0][0];
      vars[2]=ds[0][1];
      owner.updateDataConnection(this.hashCode());
    }

    public  void releaseXY(int xpix,int ypix){
      if(!trackRelease)return;
      ds[0][0]=canvas.xFromPix(xpix);
      ds[0][1]=canvas.yFromPix(ypix);
      vars[1]=ds[0][0];
      vars[2]=ds[0][1];
      owner.updateDataConnection(this.hashCode());
    }

    void setTrackMove(boolean track){ trackMove=track;}
    void setTrackDrag(boolean track){ trackDrag=track;}
    void setTrackClick(boolean track){ trackClick=track;}
    void setTrackRelease(boolean track){ trackRelease=track;}

        // data source methods
    public double[][] getVariables(){
        return ds;
    }
    public String[]   getVarStrings() {return varStrings;}
    public int getID(){return this.hashCode();}
    public void setOwner(SApplet owner){; }
    public SApplet getOwner( ){return owner;}

}