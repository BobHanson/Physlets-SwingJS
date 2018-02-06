
/**
 * Title:        null<p>
 * Description:  null<p>
 * Copyright:    null<p>
 * Company:      Physlets<p>
 * @author W. Christian
 * @version null
 */
package ising;

import java.awt.*;
import edu.davidson.tools.*;
import edu.davidson.display.Format;

public class IsingPanel extends Panel implements SStepable, edu.davidson.tools.SDataSource{
  String[] varStrings= new String[]{"u","m","t","teperature","b"};  // speed and number
  double[][] ds=new double[1][5];  // the datasource state variables r, t;
  Ising owner=null;
  IsingModel model=null;
  int iwidth=0, iheight=0;
  Image osi=null;
  Format format= new Format("%-+6.3g");

  public IsingPanel(Ising owner) {
       this.owner=owner;
       model= new IsingModel();
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
  }

  void reset(){
    owner.clock.stopClock();
    model.reinitialize();
    repaint();
  }

  synchronized void setDefault(){
    owner.clock.stopClock();
    model.setArraySize(model.getArraySize() );
    model.reinitialize();
    repaint();
  }

  synchronized void paint(){
    if(osi==null){
      repaint();
      return;
    }
    Graphics osg = osi.getGraphics();
    model.drawSpins(osg, 0, 0, iwidth, iheight);  // draw only the spins that have changed.
    osg.dispose();
    Graphics g = getGraphics();
    if(osi!=null) g.drawImage(osi,0,0,this);    // draw the image onto the visible graph
    g.dispose();
  }

  synchronized public void paint(Graphics g){
    if(model==null) return;
    // if the window has been resized
    if(iwidth != this.getSize().width || iheight != this.getSize().height || osi == null) {
      iwidth = this.getSize().width;
      iheight = this.getSize().height;
      osi = createImage(iwidth, iheight);
    }
    if(osi==null)return;
    Graphics osg = osi.getGraphics();
    model.reDrawSpins(osg, 0, 0, iwidth, iheight);
    osg.dispose();
    if(osi!=null) g.drawImage(osi,0,0,this);    // draw the image onto the visible graph
  }

/**
 * Step the time for the animation by dt.
 *
 *   @param dt the time step
 *   @param time the current time.
 */
  public void step(double dt, double time){
    model.onestep();
    owner.updateDataConnections();
    paint();
   // System.out.println("T="+model.getT() );
  }

  public String[]   getVarStrings(){return varStrings;}
  public int getID(){return hashCode();}
  public void setOwner(SApplet applet){;}
  public SApplet getOwner(){return owner;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"

  public double[][] getVariables(){
    ds[0][0]=model.getE();
    ds[0][1]=model.getM();
    ds[0][2]=model.time_counter;
    ds[0][3]=model.getT();
    ds[0][4]=model.getB();
    return ds;
  }



}