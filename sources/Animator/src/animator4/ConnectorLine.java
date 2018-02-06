package animator4;

import java.awt.*;

public class ConnectorLine extends Connector {

    public ConnectorLine(AnimatorCanvas o,Thing t1, Thing t2) {
        super(o,t1,t2);
    }

    public void paint(Graphics g){
      if(!visible)return;
      if(thing1==null || thing2 ==null) return;
      int ptX1=canvas.pixFromX(thing1.vars[1]);
      int ptY1=canvas.pixFromY(thing1.vars[2]);
      int ptX2=canvas.pixFromX(thing2.vars[1]);
      int ptY2=canvas.pixFromY(thing2.vars[2]);
      g.setColor(color);
      g.drawLine(ptX1,ptY1,ptX2,ptY2);
    }
} 