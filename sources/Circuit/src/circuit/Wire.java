package circuit;
import java.awt.*;
import edu.davidson.tools.SApplet;

public class Wire extends Part {
  public Wire(SApplet o, Circuit c, int i1, int j1,int i2,int j2) {
     super(o, c,i1,  j1, i2, j2) ;
     label=null;
     showZ=false;
  }

  void drawSymbol(Graphics g, int x1,int y1,int x2,int y2, int s){
      g.setColor(color);
      g.drawLine(x1,y1,x2,y2);
  }
}