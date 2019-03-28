package circuitsimulator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

/**
 * Used by circuit and circuitbuilder. Canvas representing the circuit.
 *
 * @author Toon Van Hoecke
 */
public class CircuitCanvas extends Canvas {

  int             d                = 54, b = 27;
  Color           parsedbgColor    = new Color(255, 255, 185);
  Color           notparsedbgColor = new Color(192, 192, 192);
  Color           bgColor;
  CircuitGrid     cirgrid;
  Circuit         circuit;
//  private boolean imagePainting = false;
  Image           osIm          = null;
  //Graphics        osGr          = null;

  /**
   * Constructor CircuitCanvas
   *
   * @param circ
   */
  CircuitCanvas(Circuit circ) {
    super();
    circuit = circ;
    bgColor = circuit.parsed
              ? parsedbgColor
              : notparsedbgColor;
    setBackground(bgColor);
    d       = circuit.interGrid;
    b       = d / 2;
    cirgrid = circuit.cirgrid;
    int insetx = (int) ((circuit.gridZone.width - (int) ((cirgrid.cols + 0.5) * d)) / 2);
    int insety = (int) ((circuit.gridZone.height - cirgrid.rows * d) / 2);
    setBounds(insetx, insety, (int) ((cirgrid.cols + 0.5) * d), cirgrid.rows * d);
    if((circuit.debugLevel & Circuit.DEBUG_IO) > 0) {
      System.out.println("canvas construction completed...");
    }
    //{{INIT_CONTROLS
    setSize(0, 0);
    //}}
  }

  /**
   * Method reconnect
   *
   */
  public void reconnect() {
    d       = circuit.interGrid;
    b       = d / 2;
    cirgrid = circuit.cirgrid;
    int insetx = (int) ((circuit.gridZone.width - (int) ((cirgrid.cols + 0.5) * d)) / 2);
    int insety = (int) ((circuit.gridZone.height - cirgrid.rows * d) / 2);
    setBounds(insetx, insety, (int) ((cirgrid.cols + 0.5) * d), cirgrid.rows * d);
    
    bgColor = circuit.parsed
              ? parsedbgColor
              : notparsedbgColor;
    setBackground(bgColor);
    if((getSize().width >= 1) && (getSize().height >= 1)) {
      osIm = createImage(getSize().width, getSize().height);
      redraw();
    }
    if((circuit.debugLevel & Circuit.DEBUG_IO) > 0) {
      System.out.println("canvas construction completed..." + getSize());
    }
  }

  /**
   * Method redraw
   *
   */
  public synchronized void redraw() {
//    imagePainting = false;
    if(osIm == null || getSize().width<4 || getSize().height<4 ) {
      return;
    }
    bgColor = circuit.parsed
              ? parsedbgColor
              : notparsedbgColor;
    Graphics osGr = osIm.getGraphics();
    
    /** @j2sNative xxi = this.osIm */

    osGr.setColor(bgColor);
    osGr.fillRect(1, 1, getSize().width - 2, getSize().height - 2);
    osGr.setColor(java.awt.Color.black);
    osGr.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
    for(int i = 0; i < cirgrid.rows; i++) {
      for(int j = 0; j < cirgrid.cols; j++) {
        osGr.fillOval(b + j * d - 2, b + i * d - 2, 4, 4);
      }
    }
    @SuppressWarnings("unchecked")
	Vector<CircuitElement> list=(Vector<CircuitElement>)cirgrid.cirElemList.clone();
    for(int i = 0,n=list.size(); i < n; i++) {
      list.elementAt(i).paint(osGr);
      list.elementAt(i).repaintImage(osGr);
    }
    osGr.dispose();
    repaint();
//    imagePainting = true;
  }

  /**
   * Method update
   *
   * @param g
   */
  public void update(Graphics g) {
    paint(g);
  }

  /**
   * Method paint
   *
   * @param g
   */
  public synchronized void paint(Graphics g) {
    //        if (imagePainting) {
    if( osIm!=null) {
      Graphics osGr = osIm.getGraphics();
      for(int i = 0; i < cirgrid.cirElemList.size(); i++) {
        cirgrid.cirElemList.elementAt(i).repaintImage(osGr);
      }
      osGr.dispose();
      //System.out.println("circuitCanvas painting " + getSize());
      g.drawImage(osIm, 0, 0, this);
    }
    return;
    /*
    *       } else {
    *          Object object = new MediaTracker(circuit);
    *          try {
    *              ((MediaTracker) object).addImage( osIm, 1 );
    *              ((MediaTracker) object).waitForID( 1, 5000L );
    *          }
    *          catch(Exception e) {}
    *          if (osIm != null) g.drawImage(osIm,0,0, this);
    *      }
     */
  }
  //{{DECLARE_CONTROLS
  //}}
}
