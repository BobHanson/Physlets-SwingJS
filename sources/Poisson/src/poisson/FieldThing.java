package poisson;

import java.awt.*;
import edu.davidson.display.Thing;

public class FieldThing extends Thing {

  PoissonPanel p;
  VectorField field;

  public FieldThing(PoissonPanel p) {
      super(p,0,0);
      this.p=p;
      this.field=p.field;
      resizable=true;
      noDrag=true;
  }

  public void paint(Graphics g){
     
  }
}
