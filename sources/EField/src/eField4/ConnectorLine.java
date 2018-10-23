package eField4;

import java.awt.Graphics;



public class ConnectorLine extends Connector {

    public ConnectorLine(OdeCanvas o,Thing t1, Thing t2) {
        super(o,t1,t2);
    }
    public void paint(Graphics g){
      if(hideThing) return;
      if(thing1==null || thing2 ==null) return;
      int ptX1=p.pixFromX(thing1.vars[1]);
      int ptY1=p.pixFromY(thing1.vars[2]);
      int ptX2=p.pixFromX(thing2.vars[1]);
      int ptY2=p.pixFromY(thing2.vars[2]);
      g.setColor(color);
      g.drawLine(ptX1,ptY1,ptX2,ptY2);
    }
}
