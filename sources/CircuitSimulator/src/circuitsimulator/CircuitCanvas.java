package circuitsimulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.net.*;

/**
 * Used by circuit and circuitbuilder. Canvas representing the circuit.
 *
 * @author Toon Van Hoecke
 */
public class CircuitCanvas extends a2s.Canvas {

  int             d                = 54, b = 27;
  Color           parsedbgColor    = new Color(255, 255, 185);
  Color           notparsedbgColor = new Color(192, 192, 192);
  Color           bgColor;
  URL             imagebase;
  CircuitGrid     cirgrid;
  Circuit         circuit;
  private boolean imagePainting = false;
  Image           osIm          = null;
  Graphics        osGr          = null;

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
    if((circuit.debugLevel & circuit.DEBUG_IO) > 0) {
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
      osGr = osIm.getGraphics();
      redraw();
    }
    if((circuit.debugLevel & circuit.DEBUG_IO) > 0) {
      System.out.println("canvas construction completed...");
    }
  }

  /**
   * Method redraw
   *
   */
  public synchronized void redraw() {
    imagePainting = false;
    if(osGr == null || getSize().width<4 || getSize().height<4 ) {
      return;
    }
    bgColor = circuit.parsed
              ? parsedbgColor
              : notparsedbgColor;
    osGr.setColor(bgColor);
    osGr.fillRect(1, 1, getSize().width - 2, getSize().height - 2);
    osGr.setColor(java.awt.Color.black);
    osGr.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
    for(int i = 0; i < cirgrid.rows; i++) {
      for(int j = 0; j < cirgrid.cols; j++) {
        osGr.fillOval(b + j * d - 2, b + i * d - 2, 4, 4);
      }
    }
    java.util.Vector list=(java.util.Vector)cirgrid.cirElemList.clone();
    for(int i = 0,n=list.size(); i < n; i++) {
      ((CircuitElement) list.elementAt(i)).paint(osGr);
      ((CircuitElement) list.elementAt(i)).repaintImage(osGr);
    }
    repaint();
    imagePainting = true;
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
    Image temp=osIm;
    if(temp != null && g!=null) {
      g.drawImage(temp, 0, 0, this);
    }
    if( osGr!=null)
      for(int i = 0; i < cirgrid.cirElemList.size(); i++) {
        ((CircuitElement) cirgrid.cirElemList.elementAt(i)).repaintImage(osGr);
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
