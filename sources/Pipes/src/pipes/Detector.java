

package pipes;


import java.awt.*;
import edu.davidson.tools.*;

public class Detector extends Thing implements SDataSource{
  //int vars[] = new int[5];
  String[] varStrings = {"t","x","p"};
  double[][] vars = new double[1][3];
  boolean enabled=true;



  public Detector(int xpos, int ypos, int width, int height, Color c, Pipe o, boolean e, boolean m) {
    //Thing(int x, int y, int width, int height, Color c, Pipe o, boolean e, boolean m)
    super(xpos,ypos,width,height,c,o,e,m);
    try{SApplet.addDataSource(this);} catch(Exception ex){ex.printStackTrace();}
  }

  public double[][]getVariables(){
    vars[0][0]=owner.time;              //time                      //add to x, xorigin
    vars[0][1]=(x-owner.xorigin)*owner.ppu;            //x
    vars[0][2]=owner.pArray[x]; //pressure
    return vars;
    }

  public String[] getVarStrings(){
    return varStrings;
    }

  /**
  *
  * Thing will be painted if enabled
  *
  * @param e boolean
  *
  */

  public void setIsEnabled(boolean e){
      this.enabled = e;
  }

  public void paintOS(Graphics g){
    g.setColor(color);
    g.fillRect(x-w/2,y-h/2,w,h);

  }

}