package qTime;

import java.awt.*;
import edu.davidson.numerics.Parser;
import edu.davidson.graph.*;
import edu.davidson.display.Format;

/**
 * Class QTimeGraph
 *
 *
 * @author Wolfgang Christian
 */
public final class QTimeGraph extends Graph2D {

  private boolean   probability    = true;
  boolean           offsetFunction = true;
  boolean           showPotential  = true;
  Font              f              = new Font("Helvetica", Font.BOLD, 14);  // for caption
  Graphics          g_drag         = null;
  String            caption        = "QTime";
  int               numPts, count = 0;
  Image             osi2 = null;
  Image             osi  = null;
  QTimeState        state;
  QTime             owner;
  boolean           tempStopped = false;
  Parser            parser      = null;
  int               iwidth      = 0;
  int               iheight     = 0;
  int[]             xpts;
  int[]             ypts;
  double            maxX        = 10;
  double            minX        = -10;
  double            maxY        = 10;
  double            minY        = -10;
  Format            format      = new Format("%7.3g");
  Format            formatAngle = new Format("%3i");
  Polygon           polyline    = new Polygon();
  DataSet           data        = new DataSet();
  Axis              yaxis, xaxis;
  Color             currentColor = Color.black;
  protected boolean drag         = false;
  String            potentialStr, realStr, imaginaryStr;

  /**
   * Constructor QTimeGraph
   *
   * @param applet
   * @param numPts_
   */
  public QTimeGraph(QTime applet, int numPts_)                              // constructor for TimeGraph
  {
    owner  = applet;
    caption = owner.label_qtime;
    parser = new Parser(1);
    parser.defineVariable(1, "x");  // define the variable
    setBackground(Color.white);
    numPts       = numPts_;
    state        = new QTimeState(minX, maxX, numPts, applet);
    borderTop    = 18;
    borderBottom = 15;
    xaxis        = createAxis(Axis.BOTTOM);
    xaxis.setTitleText("X");
    xaxis.setLabelFont(new Font("Helvetica", Font.PLAIN, 10));
    xaxis.setTitleFont(new Font("TimesRoman", Font.PLAIN, 12));
    xaxis.setManualRange(true, minX, maxX);
    xaxis.maximum = maxX;
    xaxis.minimum = minX;
    yaxis         = createAxis(Axis.LEFT);
    yaxis.setTitleText(owner.label_energy);
    yaxis.setLabelFont(new Font("Helvetica", Font.PLAIN, 10));
    yaxis.setTitleFont(new Font("TimesRoman", Font.PLAIN, 12));
    //        yaxis.setManualRange(true);
    //      yaxis.maximum =  10;
    //      yaxis.minimum = -10;
    data           = new DataSet();
    data.linecolor = currentColor;
    xaxis.attachDataSet(data);
    yaxis.attachDataSet(data);
    attachDataSet(data);
  }

  /**
   * Method paint
   *
   * @param g
   */
  public void paint(Graphics g) {
    if((getSize().width == 0) || (getSize().height == 0)) {
      return;
    }
    if((osi == null) || (iwidth != this.getSize().width) || (iheight != this.getSize().height)) {
      //size has changed so we need a new off screen image, osi.
      iwidth  = this.getSize().width;
      iheight = this.getSize().height;
      osi     = createImage(iwidth, iheight);  //create an off screen image
      osi2    = createImage(iwidth, iheight);  //create an off screen image
    }
    Graphics osg = osi.getGraphics();  // a graphics context for the  off screen image
    osg.setColor(getBackground());
    osg.fillRect(0, 0, iwidth, iheight);
    osg.setColor(g.getColor());
    osg.clipRect(0, 0, iwidth, iheight);
    //update(osg);                           // draw the graph onto the off screen image context
    super.paint(osg);
    osg.setColor(Color.black);
    osg.setFont(f);
    FontMetrics fm = osg.getFontMetrics(f);
    if(this.isShowAxis()) {
      osg.drawString(caption, (iwidth - fm.stringWidth(caption)) / 2, 10);
    } else {
      osg.drawString(caption, (iwidth - fm.stringWidth(caption)) / 2, 30);
    }
    osg.dispose();
    g.drawImage(osi, 0, 0, this);  // draw the off screen image onto the visible graph from osg
    g.setColor(Color.black);
    String tStr = format.form(owner.clock.getTime());
    //String tStr = format.form(state.time);
    g.drawString(owner.label_time+ " " + tStr, 10, iheight - 15);
    if(polyline.npoints != iwidth + 1) {  // make sure the Polygon has enough points
      xpts = new int[iwidth + 1];
      ypts = new int[iwidth + 1];
      for(int i = 0; i <= iwidth; i++) {
        xpts[i] = i;
        ypts[i] = iheight / 2;
      }
      polyline = new Polygon(xpts, ypts, iwidth + 1);
    }
    paintPsi(g);
    g.setColor(Color.black);
  }

  /**
   * Method stepState
   *
   * @param dt
   */
  public void stepState(double dt) {
    state.step(dt);
    if(osi == null) {
      return;
    }
    if(polyline.npoints == 0) {
      return;
    }
    Graphics g = osi2.getGraphics();
    g.drawImage(osi, 0, 0, this);  // draw the off screen image onto the visible graph from osg
    g.setColor(Color.black);
    //String tStr = format.form(state.time);
    String tStr = format.form(owner.clock.getTime());
    g.drawString(owner.label_time+" " + tStr, 10, iheight - 15);
    paintPsi(g);
    g.dispose();
    g = getGraphics();
    g.drawImage(osi2, 0, 0, this);  // draw the off screen image onto the visible graph from osg
    g.setColor(Color.black);
    g.dispose();
    //repaint();
  }

  protected void paintPsi(Graphics g) {
    if(polyline.npoints == 0) {
      return;
    }
    // System.out.println("paintPsi1 "+polyline.npoints);
    int aminx  = xaxis.getInteger(minX);
    int awidth = xaxis.getInteger(maxX) - aminx;
    g.clipRect(aminx, 0, awidth, iheight);
    state.setXScale(aminx, aminx + awidth);
    if(probability) {
      int basePix = iheight / 2;                        //use this to put the wavepacket in the center
      if(offsetFunction) {
        basePix = yaxis.getInteger(state.getEnergy());  // offset the
      }
      state.fillPolyProb(iheight, basePix, polyline, g);
    } else {
      state.setPolyProb(iheight, iheight / 2, polyline);
      g.setColor(Color.black);
      g.drawPolygon(polyline);
      state.setPolyProbNeg(iheight, iheight / 2, polyline);
      g.drawPolygon(polyline);
      state.setPolyReal(iheight, iheight / 2, polyline);
      g.setColor(Color.red);
      g.drawPolygon(polyline);
      state.setPolyImaginary(iheight, iheight / 2, polyline);
      g.setColor(Color.green);
      g.drawPolygon(polyline);
    }
  }

  /**
   * Method update
   *
   * @param g
   */
  public void update(Graphics g) {
    paint(g);  //update usually does a rect fill with a background color.  We don't need this.
  }

  /**
   *    Set the gutters.
   *
   *    @param g1    The gutter sizes: left, top, right, bottom.
   * @param g2
   * @param g3
   * @param g4
   */
  public void setGutters(int g1, int g2, int g3, int g4) {
    borderLeft   = g1;
    borderTop    = g2;
    borderRight  = g3;
    borderBottom = g4;
  }

  /**
   * Method setCaption
   *
   * @param s
   */
  public void setCaption(String s) {
    caption = s;
    repaint();
  }

  /**
   * Method setXTitle
   *
   * @param s
   */
  public void setXTitle(String s) {
    xaxis.setTitleText(s);
  }

  /**
   * Method setYTitle
   *
   * @param s
   */
  public void setYTitle(String s) {
    yaxis.setTitleText(s);
  }

  /**
   * Method setPotential
   *
   * @param potStr
   *
   * @return
   */
  public boolean setPotential(String potStr) {
    parser.define(potStr);
    parser.parse();
    if(parser.getErrorCode() != Parser.NO_ERROR) {
      System.out.println("Parse error: " + parser.getErrorString() + " at function potential, position "
                         + parser.getErrorPosition());
      return false;
    }
    potentialStr = potStr;
    parsePotential();
    return true;
  }

  /**
   * Method setReal
   *
   * @param reStr
   *
   * @return
   */
  public boolean setReal(String reStr) {
    parser.define(reStr);
    parser.parse();
    if(parser.getErrorCode() != Parser.NO_ERROR) {
      System.out.println("Parse error: " + parser.getErrorString() + " at function real, position "
                         + parser.getErrorPosition());
      return false;
    }
    realStr = reStr;
    state.setReal(realStr);
    return true;
  }

  /**
   * Method setImaginary
   *
   * @param imStr
   *
   * @return
   */
  public boolean setImaginary(String imStr) {
    parser.define(imStr);
    parser.parse();
    if(parser.getErrorCode() != Parser.NO_ERROR) {
      System.out.println("Parse error: " + parser.getErrorString() + " at function imaginary, position "
                         + parser.getErrorPosition());
      return false;
    }
    imaginaryStr = imStr;
    state.setImaginary(imaginaryStr);
    return true;
  }

  /**
   * Method parsePotential
   *
   */
  public void parsePotential() {
    double  maximum = maxX;
    double  minimum = minX;
    double  x, y;
    int     count = 0;
    boolean error = false;
    parser.define(potentialStr);
    parser.parse();
    if(parser.getErrorCode() != Parser.NO_ERROR) {
      System.out.println("Parse error: " + parser.getErrorString() + " at function potential, position "
                         + parser.getErrorPosition());
      return;
    }
    double d[] = new double[2 * numPts];
    for(int i = 0; i < numPts; i++) {
      x        = minimum + i * (maximum - minimum) / (numPts - 1);
      d[count] = x;
      try {
        y = parser.evaluate(x);
        if(y == y) {  // this checks for NAN on Cafe compiler.
          d[count + 1] = y;
        } else {
          System.out.println("Divide by zero!");
          y = parser.evaluate(x + 1.0E-32);
          if(y == y) {
            d[count + 1] = y;
          } else {
            d[count + 1] = 0;
          }
        }
        count += 2;
      } catch(Exception e) {
        error = true;
      }
    }
    if(count <= 2) {
      System.out.println("Error NO POINTS to PLOT!");
      return;
    } else if(error) {
      System.out.println("Error while calculating points!");
    }
    data.deleteData();
    if(showPotential) {
      try {
        data.append(d, count / 2);
      } catch(Exception e) {
        System.out.println("Error while appending data!");
        return;
      }
    }
    //double delta=yaxis.maximum-yaxis.minimum;
    //setYMinMax(yaxis.minimum-0.25*delta,yaxis.maximum+0.25*delta);
    state.setPotential(potentialStr);
    double delta = state.getMaxV() - state.getMinV();
    if(delta < 10E-10) {
      delta = 1;  // constant potential
    }
    setYMinMax(state.getMinV() - 0.25 * delta, state.getMaxV() + 0.25 * delta);
    repaint();
  }

  /**
   * Method setRGBColor
   *
   * @param r
   * @param g
   * @param b
   */
  public void setRGBColor(int r, int g, int b) {
    currentColor = new Color(r, g, b);
    repaint();
  }

  /**
   * Method setYMinMax
   *
   * @param min
   * @param max
   */
  public void setYMinMax(double min, double max) {
    minY          = min;
    maxY          = max;
    yaxis.minimum = min;
    yaxis.maximum = max;
    if(yaxis.isManualRange()) {  // force a recalculation of the labels if the range is manual;
      yaxis.setManualRange(true, min, max);
    }
    repaint();
  }

  /**
   * Method setXMinMax
   *
   * @param min
   * @param max
   */
  public void setXMinMax(double min, double max) {
    if((minX != min) || (maxX != max)) {
      if(min < max) {
        minX = min;
        maxX = max;
      } else {
        minX = max;
        maxX = min;
      }
      xaxis.setManualRange(true, minX, maxX);
      state.setSpace(min, max);
      parsePotential();
      state.setReal(realStr);
      state.setImaginary(imaginaryStr);
    }
  }

  /**
   * Method reset
   *
   */
  public void reset() {
    state.reset();
    if(yaxis.isManualRange()) {
      return;
    }
    double min = state.getMinV();
    double max = state.getMaxV();
    double en  = state.getEnergy();
    if(en > max) {
      max = en;
    }
    if(en < min) {
      min = en;
    }
    double delta = max - min;
    if(delta < 10E-10) {
      delta = 1;  // just in case everything is zero.
    }
    if(en + delta / 2 > max) {
      max = en + delta / 2;
    }
    if(en - delta / 2 < min) {
      min = en - delta / 2;
    }
    setYMinMax(min, max);
  }

  /**
   * Method mouseDown
   *
   * @param e
   * @param x
   * @param y
   *
   * @return
   */
  public boolean mouseDown(Event e, int x, int y) {
    if(osi == null) {
      return false;
    }
    if(((e.modifiers & Event.META_MASK) != 0) || ((e.modifiers & Event.ALT_MASK) != 0)) {
      return false;
    }
    if((xaxis == null) || (yaxis == null)) {
      return false;
    }
    /*
     * Soon as the mouse button is pressed request the Focus
     * otherwise we will miss key events
     */
    requestFocus();
    if(g_drag != null) {
      g_drag.dispose();
    }
    //tempStopped=owner.stopped;
    tempStopped = owner.clock.isRunning();
    owner.clock.stopClock();
    // owner.stopped=true;  //stop the time development for now
    g_drag = getGraphics();
    // draw the wavepacket onto the osi so we don't have to redraw it.
    Graphics g_temp = osi.getGraphics();
    paintPsi(g_temp);
    g_temp.dispose();
    drawCoord(x, y);
    return true;
  }

  /**
   * Method mouseUp
   *
   * @param e
   * @param x
   * @param y
   *
   * @return
   */
  public boolean mouseUp(Event e, int x, int y) {
    if(osi == null) {
      return false;
    }
    if(((e.modifiers & Event.META_MASK) != 0) || ((e.modifiers & Event.ALT_MASK) != 0)) {
      return false;
    }
    // owner.stopped=tempStopped;
    if((xaxis == null) || (yaxis == null)) {
      return false;
    }
    g_drag.dispose();
    g_drag   = null;
    polyline = new Polygon(xpts, ypts, iwidth + 1);
    repaint();
    if(tempStopped) {
      owner.clock.startClock();
    }
    return true;
  }

  /**
   * Handle the Mouse Drag events
   * @param e
   * @param x
   * @param y
   *
   * @return
   */
  public boolean mouseDrag(Event e, int x, int y) {
    if(((e.modifiers & Event.META_MASK) != 0) || ((e.modifiers & Event.ALT_MASK) != 0)) {
      return false;
    }
    if(osi == null) {
      return false;
    }
    if((xaxis == null) || (yaxis == null)) {
      return false;
    }
    drawCoord(x, y);  // this will also draw a new image without the polyline
    return true;
  }

  /**
   * Method setYManualRange
   *
   * @param mr
   */
  public void setYManualRange(boolean mr) {
    if(mr == yaxis.isManualRange()) {
      return;  // no need to do anything;
    }
    if(!yaxis.isManualRange())  // mr about to change to true.  Save current range as new default.
    {
      minY = yaxis.minimum;
      maxY = yaxis.maximum;
    }
    yaxis.setManualRange(mr, minY, maxY);
    repaint();
  }

  /**
   * Method drawCoord
   *
   * @param x
   * @param y
   */
  public void drawCoord(int x, int y) {
    int xCoord = x;
    int yCoord = y;
    if(osi != null) {
      g_drag.drawImage(osi, 0, 0, this);  // draw the image onto the visible graph
    }
    g_drag.setColor(Color.black);
    g_drag.setFont(f);  // bold
    if(x < 3 * iwidth / 4) {
      g_drag.drawString("  X  : " + format.form(xaxis.getDouble(xCoord)), xCoord + 20, yCoord - 10);
      g_drag.drawString(owner.label_magnitude+" " + format.form(getPsiFromPix(xCoord)), xCoord + 20, yCoord + 10);
      g_drag.drawString(owner.label_phase+" " + formatAngle.form(getAngleFromPix(xCoord)), xCoord + 20, yCoord + 30);
    } else {
      g_drag.drawString("  X  : " + format.form(xaxis.getDouble(xCoord)), xCoord - 80, yCoord - 10);
      g_drag.drawString(owner.label_magnitude+" " + format.form(getPsiFromPix(xCoord)), xCoord - 80, yCoord + 10);
      g_drag.drawString(owner.label_phase+" " + formatAngle.form(getAngleFromPix(xCoord)), xCoord - 80, yCoord + 30);
    }
    g_drag.drawLine(xCoord - 10, yCoord, xCoord + 10, yCoord);
    g_drag.drawLine(xCoord, yCoord - 10, xCoord, yCoord + 10);
  }

  /**
   * Method getPsiFromPix
   *
   * @param pix
   *
   * @return
   */
  public double getPsiFromPix(int pix) {  // convert the x pix to a double using the x scale. Then get Psi.
    return state.getPsiAtX(xaxis.getDouble(pix));
  }

  /**
   * Method getAngleFromPix
   *
   * @param pix
   *
   * @return
   */
  public int getAngleFromPix(int pix) {  // convert the x pix to a double using the x scale. Then get Psi.
    return state.getAngleAtX(xaxis.getDouble(pix));
  }

  /**
   * Method getState
   *
   *
   * @return
   */
  public QTimeState getState() {
    return state;
  }
}
