package bfield;
import java.awt.*;
import edu.davidson.display.Thing;

public class FieldThing extends Thing {

  FieldPanel p;
  VectorField field;

  public FieldThing(FieldPanel p) {
      super(p,0,0);
      this.p=p;
      this.field=p.field;
  }

  public void paint(Graphics osg){
      if(visible) field.paint(osg,p.fieldBounds);
  }
} 