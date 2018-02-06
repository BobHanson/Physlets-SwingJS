package edu.davidson.display;


import java.awt.*;
import edu.davidson.tools.SApplet;

public class ConnectorLine extends Thing {
    Thing thing1,thing2;

    public ConnectorLine(SApplet owner, SScalable sc, Thing t1, Thing t2){
      super(sc,0,0);
      s=1;
      thing1=t1;
      thing2=t2;
      applet=owner;
      visible=true;
    }

    public void setConnectorIDs(Thing t1, Thing t2){
      thing1=t1;
      thing2=t2;
    }

    public void paint(Graphics osg){
      if(!visible || thing1==null || thing2==null) return;
      int ptX1=canvas.pixFromX(thing1.getX());
      int ptY1=canvas.pixFromY(thing1.getY());
      int ptX2=canvas.pixFromX(thing2.getX());
      int ptY2=canvas.pixFromY(thing2.getY());
      osg.setColor(color);
      osg.drawLine(ptX1,ptY1,ptX2,ptY2);
    }
    public final boolean isInsideThing(int xPix, int yPix){
      return false;
    }
}
