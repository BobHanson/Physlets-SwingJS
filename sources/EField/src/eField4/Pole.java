package eField4;
import java.awt.*;


public class Pole extends Charge{  // fixed charge that is used to calculate the potential
//  String xStr, yStr;
//	Parser xFunc=null;
//	Parser yFunc=null;
  public Pole(OdeCanvas p, double x,double y,double m) {
      super(p,x,y,0,0,m);     // initialize and add the charge to the DataSource list in SApplet
      this.p=p;
      s=9;
      vars[0]=0;
      vars[1]=x;
      vars[2]=y;
      vars[3]=0;
      vars[4]=0;
      initVars[0]=0;
      initVars[1]=x;
      initVars[2]=y;
      initVars[3]=0;
      initVars[4]=0;
      showVVector=false;
      showFVector=false;
      trailSize=0;
      if(mag<0) color=Color.blue; else color=Color.red;
      sticky=true;
  }

}