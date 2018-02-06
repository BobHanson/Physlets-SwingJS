package ripple;

import java.awt.Color;
import  edu.davidson.display.CircleThing;
import  edu.davidson.tools.SApplet;
import  edu.davidson.display.SScalable;

public final class Source extends CircleThing{
  double amp,phase;
  double displayX, displayY;

  public Source(SApplet owner, SScalable sc, double x, double y, double amp, double phase, int radius){
          super(owner,sc,x,y,radius);
          this.amp=amp;
          this.phase=phase;
          this.noDrag=false;
          this.color=Color.red;
  }


  public final double getAmp()
  {
      return amp;
  }
  public final double getPhase()
  {
      return phase;
  }
  void setDisplayCoordinates( double scale){
     displayX=x+xDisplayOff/scale;
     displayY=y+yDisplayOff/scale;
  }
}