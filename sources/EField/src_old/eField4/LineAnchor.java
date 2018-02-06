package eField4;

import java.awt.*;
import edu.davidson.numerics.Parser;

public class LineAnchor extends Thing {
    Thing thing;

    LineAnchor(OdeCanvas p, double x,double y, Thing t){
      super(p,x,y,0,0);
      this.p=p;
      thing=t;
      vars[0]=0; //time
      vars[1]=x;
      vars[2]=y;
      initVars[0]=0;
      initVars[1]=x;
      initVars[2]=y;
    }

    public void paint(Graphics osg){
      if(hideThing) return;
      int ptX1=p.pixFromX(vars[1]);
      int ptY1=p.pixFromY(vars[2]);
      int ptX2=p.pixFromX(thing.vars[1]);
      int ptY2=p.pixFromY(thing.vars[2]);
      osg.setColor(color);
      osg.drawLine(ptX1,ptY1,ptX2,ptY2);
    }
}
