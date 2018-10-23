package edu.davidson.display;


import java.awt.Graphics;


import edu.davidson.tools.SApplet;

public class GraphThing extends Thing {

    public GraphThing(SApplet owner, SScalable sc){
      super(sc);
      applet=owner;
    }

    public void paint(Graphics osg){
      return;
    }

    public void paintHighlight(Graphics osg){
      return;
    }
}
