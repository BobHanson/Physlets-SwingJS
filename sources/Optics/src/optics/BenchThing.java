package optics;

import edu.davidson.display.Thing;
import java.awt.*;

public class BenchThing  extends Thing {
  Bench bench;
  public BenchThing(Bench b) {
      super(b);
      bench=b;
  }

  public void paint(Graphics g){ ;
    bench.paintBench(g);
  }
}