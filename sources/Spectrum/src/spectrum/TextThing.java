

package spectrum;


import java.awt.Color;
import java.awt.Graphics;

import java.awt.*;


public class TextThing extends Object{
  String txt=null;
  Color txtColor = Color.white;
  SpectrumPanel owner = null;
  int xoff=0, yoff=0;

  public TextThing() {
  }

  public TextThing(SpectrumPanel o, String cap){
     this();
     owner = o;
     txt=cap;
  }

  public void paintOS(Graphics g){
  }


}