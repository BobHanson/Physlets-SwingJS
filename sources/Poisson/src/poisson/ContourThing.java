package poisson;


import java.awt.Graphics;


import edu.davidson.display.Thing;

public class ContourThing extends Thing {

  PoissonPanel p;
  VectorField field;

  public ContourThing(PoissonPanel p) {
      super(p,0,0);
      this.p=p;
      this.field=p.field;
      resizable=true;
      noDrag=true;
  }

  public void paint(Graphics g){
     
  }
}
