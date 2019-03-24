package animator4;

//import a2s.*;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Vector;

public class Doppler extends Circle {
  double lastCrest=0;
  double c=1.0;
  double period=1.0;
  int numCrests=10;
  int counter=0;
  Vector waves=null;

  public Doppler(AnimatorCanvas o,int diameter, String xStr, String yStr, int numCrests, double period, double c) {
      super( o, diameter, xStr, yStr);
      this.numCrests=numCrests;
      this.period=period;
      this.c=c;
      waves=new Vector(numCrests);
      lastCrest=vars[0];
      initCrests();
  }

  private void initCrests(){
    for(int i=0; i<numCrests; i++ )
      waves.addElement(new Wave(lastCrest,vars[1],vars[2]));
  }

  void addCrest(double t0){
      if(t0<lastCrest+period) return;
      while(t0>=lastCrest+period){
        lastCrest += period;
        if(waves.size()>numCrests) waves.removeElementAt(0);
        waves.addElement(new Wave(lastCrest,vars[1],vars[2]));
      }
  }

  public void clearTrail(){
      super.clearTrail();
      lastCrest=vars[0];
      if(waves==null) return;
      waves.removeAllElements();
      initCrests();
  }

  public void paint(Graphics g){
      super.paint(g);
      if(waves==null) return;
      addCrest(vars[0]);
      int len=waves.size();	//length of vector
      Wave wave;
      int red=255-color.getRed();
      int green=255-color.getGreen();
      int blue=255-color.getBlue();
      if(len>0)for(int i=0; i<len;i++){
			  //g.setColor(new Color(255*(len-i)/len,255*(len-i)/len,255*(len-i)/len));
        g.setColor(new Color(255-red*(i)/(len+1),255-green*(i)/(len+1),255-blue*(i)/(len+1)));
			  wave=(Wave)(waves.elementAt(i));
			  wave.draw(vars[0], g);
      }
  }

  // inner class for wavefront
  final class Wave {
    private double x0,y0;	// place where the crest was created.
    private double t0;		// time when the crest was created.

    Wave(double t0_, double x0_, double y0_){
	    x0=x0_; y0=y0_;	t0=t0_;
	  }


	  void draw(double t, Graphics g){
      if(!visible)return;
      double rad=(t-t0)*c;
      int pixRadius=w/2+(int)(rad*canvas.pixPerUnit)+1;
      int ptX=canvas.pixFromX(x0)-pixRadius+xDisplayOff;
      int ptY=canvas.pixFromY(y0)-pixRadius-yDisplayOff;
		  g.drawOval(ptX,ptY,pixRadius*2,pixRadius*2);
	  }
  }

}