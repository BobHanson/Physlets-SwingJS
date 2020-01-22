package eField4;

import java.applet.Applet;
import java.awt.*;
//import a2s.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.Timer;

import java.util.StringTokenizer;
import java.util.Enumeration;
import edu.davidson.display.*;
import edu.davidson.tools.*;
import edu.davidson.numerics.*;
import edu.davidson.graph.*;

/**
 * Class OdeCanvas
 *
 *
 * @author Wolfgang Christian
 */
public final class OdeCanvas extends Canvas implements SStepable, Runnable {

  boolean isJS = /** @j2sNative true || */ false;
  private static final long serialVersionUID = 1L;
  // variables for the delayed drawing.
  private CollisionThing collisionDataSource;
  private boolean        newData                        = false;
  Object                 delayLock                      = new Object();
  private Thread         delayThread                    = null;
  //private boolean keepDrawing=true;
  private Image          sketchImage                    = null;
  Color                  darkBlue                       = new Color(0, 0, 128);
  Color                  lightBlue                      = new Color(128, 128, 255);
  String                 message                        = null;
  double                 time                           = 0;
  Vector                 arrowHeads                     = new Vector();
  Vector                 testCharges                    = new Vector();
  Vector                 poles                          = new Vector();
  Vector                 fieldSolvers                   = new Vector();
  Vector                 fieldPoles                     = new Vector(10);
  Vector                 drawThings                     = new Vector(20);
  Vector                 fieldLines                     = new Vector(20);
  Parser                 parser                         = null;
  String                 potStr                         = null;
  Font                   f                              = new Font("Helvetica", Font.BOLD, 14);
  String                 caption                        = null;
  double                 bz                             = 0;               // magnetic field in the z direction
  double                 xmin                           = -1;
  double                 xmax                           = 1;
  double                 ymin                           = -1;
  double                 ymax                           = 1;
  double                 tolerance                      = 1.0e-5;
  double                 polemin                        = 0, polemax = 0;  // the min-max for potential;
  // display options
  boolean                showContours                   = true;
  boolean                showFieldLines                 = false;
  boolean                showPoles                      = true;
  boolean                showFieldVectors               = true;
  // mouse options
  boolean                showCoordOnDrag                = true;
  boolean                showVOnDrag                    = false;
  boolean                showEOnDrag                    = false;
  boolean                showFieldLineOnClick           = false;
  boolean                showFieldLineOnDoubleClick     = false;
  boolean                showEquipotentialOnClick       = false;
  boolean                showEquipotentialOnDoubleClick = false;
  boolean                autoRefresh                    = true;
  boolean                showTime                       = true;
  boolean                pointChargeMode                = true;            // false to use cylinder, 1/r, force on charge.
  boolean                collision                      = false;
  boolean                dampOnMousePressed             = false;
  boolean                calculatingFieldLines          = false;
  Thing                  dragShape                      = null;
  SContour               contour                        = new SContour();
  VectorField            field                          = new VectorField(4, 4);
  protected Image        osi                            = null;
  private Image          osi2                           = null;            // use this image while dragging.
  private boolean        osiInvalid                     = true;
  private int            iwidth                         = 0;
  private int            iheight                        = 0;
  //private int            xOffset                        = 0;               // x offset in pixels
  //private int            yOffset                        = 0;               // y offset in pixels
  private int            boxWidth                       = 0;
  private Format         format                         = new Format("%-+8.4g");
  private boolean        isDrag                         = false;
  private boolean        dragV                          = false;
  //private int            groupIndex                     = 0;
  protected EField       parentSApplet                  = null;
  private double         mouseX, mouseY;
  private int            gridSize          = 64;
  private int            skip              = 2;
  private boolean        fieldLinesInvalid = false;
  private boolean        sketchMode        = false;
  private TrailThing     trailThing        = null;
  //private Cursor         sketchCursor      = null;
  private boolean        allPositive       = true;
  private boolean        allNegative       = true;

  /**
   * Constructor OdeCanvas
   *
   * @param applet
   */
  public OdeCanvas(EField applet) {
    // this.applet=applet;
    parentSApplet = applet;
    contour.setShowAxis(false);
    contour.setDataBackground(Color.white);
    contour.setLabelColor(Color.black);//BH -- could not read Color.green);
    addMouseMotionListener(new OdeCanvas_mouseMotionAdapter(this));
    addMouseListener(new OdeCanvas_mouseAdapter(this));
    contour.setOwner(applet);
    delayThread = new Thread(this);
    state = STATE_INIT;
    delayThread.start();
    //double[] levels={-1.0,-0.8,-0.6,-0.4,-0.2,0,0.2,0.4,0.6,0.8,1.0};
    //contour.setLevels(levels);
    // contour.setLevels(-2.0, 0.2,21);
  }

  /**
   * Method setOwner
   *
   * @param owner
   */
  public void setOwner(SApplet owner) {}  // no need to do anything since the parentSApplet is already set

  /**
   * Method getOwner
   *
   *
   * @return
   */
  public SApplet getOwner() {
    return parentSApplet;
  }

  /**
   * Method setDefault
   *
   */
  public void setDefault() {
    collisionDataSource = null;
    dragShape           = null;
    sketchMode          = false;
    stopFieldThreads();
    parentSApplet.lock.getBusyFlag();
    time                           = 0;
    tolerance                      = 1.0e-5;
    message                        = null;
    showFieldLineOnClick           = false;
    showFieldLineOnDoubleClick     = false;
    showEquipotentialOnClick       = false;
    showEquipotentialOnDoubleClick = false;
    showTime                       = true;
    showCoordOnDrag                = true;
    showVOnDrag                    = false;
    showEOnDrag                    = false;
    setXRange(xmin, xmax);
    message     = null;
    allPositive = true;
    allNegative = true;
    clearPoles();
    clearTestCharges();  //clearTestCharge will also repaint()
    clearDrawThings();
    //drawThings.removeAllElements();
    contour.deleteAllSeries();
    //OdeCanvas.contour.detachDataSets();  // this is now done in delete all series.
    parentSApplet.lock.freeBusyFlag();
  }

  /**
   * Method setMessage
   *
   * @param msg
   */
  public void setMessage(String msg) {
    if((msg == null) || msg.trim().equals("")) {
      message = null;
    } else {
      message = msg;
    }
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setAutoRefresh
   *
   * @param ar
   */
  public void setAutoRefresh(boolean ar) {
    autoRefresh = ar;
    if(autoRefresh) {
      setFields();
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setBz
   *
   * @param bz
   */
  public void setBz(double bz) {
    this.bz = bz;
  }

  //public void setParentSApplet(EField p){parentSApplet=p;}

  /**
   * Method setCaption
   *
   * @param s
   */
  public void setCaption(String s) {
    caption = s;
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setXRange
   *
   * @param min
   * @param max
   */
  public void setXRange(double min, double max) {
    if(max == min) {
      xmin = min - 0.5;
      xmax = max + 0.5;
    } else if(max > min) {
      xmin = min;
      xmax = max;
    } else {  //min < max so switch
      xmin = max;
      xmax = min;
    }
    if(iwidth == 0) {
      return;
    }
    double scale = (xmax - xmin) / iwidth;
    ymin = (ymax + ymin) / 2 - scale * iheight / 2.0;
    ymax = ymin + scale * iheight;
    setFields();
    //refreshContour();
    //setFields();
    //clearTrails();
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setYRange
   *
   * @param min
   * @param max
   */
  public void setYRange(double min, double max) {
    if(max == min) {
      ymin = min - 0.5;
      ymax = max + 0.5;
    } else if(max > min) {
      ymin = min;
      ymax = max;
    } else {  //min < max so switch
      ymin = max;
      ymax = min;
    }
    if(iheight == 0) {
      return;
    }
    double scale = (ymax - ymin) / iheight;
    xmin = (xmax + xmin) / 2 - scale * iwidth / 2.0;
    xmax = xmin + scale * iwidth;
    setFields();
    //refreshContour();
    //setFields();
    //clearTrails();
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setRange
   *
   * @param rs
   *
   * @return
   */
  public boolean setRange(String rs) {
    boolean         error  = false;
    double[]        range  = new double[4];
    StringTokenizer tokens = new StringTokenizer(rs.trim(), ", ; / \\ ( { [ ) } ] \t \n \r");
    if(tokens.countTokens() < 4) {
      error = true;
    } else {
      for(int i = 0; i < 4; i++) {
        try {
          range[i] = Double.valueOf(tokens.nextToken().trim()).doubleValue();
        } catch(NumberFormatException e) {
          System.out.println("Error setting range:" + rs);
          error = true;
        }
      }
    }
    if(!error) {
      xmin = range[0];
      xmax = range[1];
      ymin = range[2];
      ymax = range[3];
      setXRange(xmin, xmax);
    } else {
      return false;
    }
    return true;
  }

  /**
   * Method setPotStr
   *
   * @param ps
   * @param x1
   * @param x2
   * @param y1
   * @param y2
   *
   * @return
   */
  public boolean setPotStr(String ps, double x1, double x2, double y1, double y2) {
    xmin = x1;
    xmax = x2;
    ymin = y1;
    ymax = y2;
    setXRange(xmin, xmax);
    return setPotStr(ps);
  }

  /**
   * Method setPotStr
   *
   * @param ps
   * @param rs
   *
   * @return
   */
  public boolean setPotStr(String ps, String rs) {
    setRange(rs);
    return setPotStr(ps);
  }

  /**
   * Method setShowContours
   *
   * @param sc
   */
  public void setShowContours(boolean sc) {
    showContours = sc;
    contour.setNoContours(!sc);
    setFields();  // could set osi invalid instead
    //osiInvalid=true;  // this will force a repaint;
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setShowFieldLines
   *
   * @param sf
   */
  public void setShowFieldLines(boolean sf) {
    showFieldLines = sf;
    if(!showFieldLines)message =null;
    setFields();  // this will stop the field threads.
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setShowFieldVectors
   *
   * @param sfv
   */
  public void setShowFieldVectors(boolean sfv) {
    showFieldVectors = sfv;
    setFields();  // could set osi invalid instead
    //osiInvalid=true;  // this will force a repaint;
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setShowPoles
   *
   * @param sp
   */
  public void setShowPoles(boolean sp) {
    showPoles = sp;
    setFields();  // could set osi invalid instead
    //osiInvalid=true;  // this will force a repaint;
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Enable sketching with the mouse.
   *
   * @param sm
   *
   * @return  int the id of the mouse data source
   */
  public int setSketchMode(boolean sm) {
    Applet applet = edu.davidson.graphics.Util.getApplet(this);
    sketchImage = edu.davidson.graphics.Util.getImage("pencil.gif", applet);
   // int xoff = 0;
    // int yoff = 29;
    sketchMode = sm;
    if(!sm) {
      trailThing = null;
      return 0;
    }
    trailThing           = new TrailThing(this, 1);
    trailThing.trailSize = 2000;
    /*
     * // the following code only works on Java 1.2.
     * Toolkit tk=Toolkit.getDefaultToolkit();
     * int maxColors=tk.getMaximumCursorColors();
     * if(maxColors==0 || im==null){
     *   System.out.println("Custom cursors not supported or cursor image not found.");
     *   sketchCursor=null;
     *   return trailThing.hashCode();
     * }
     * sketchCursor=tk.createCustomCursor(im,new Point(xoff,yoff),"sketch cursor");
     * setCursor(sketchCursor);
     */
    //sketchCursor         = null;
    return trailThing.hashCode();
  }

  /**
   * Method setTolerance
   *
   * @param t
   */
  public void setTolerance(double t) {
    tolerance = t;
  }

  /**
   * Method setTrajectory
   *
   * @param id
   * @param xStr
   * @param yStr
   *
   * @return
   */
  public boolean setTrajectory(int id, String xStr, String yStr) {
    Thing t = null;
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      t = (Thing) e.nextElement();
      if(t.hashCode() == id) {
        boolean result = t.setTrajectory(xStr, yStr);
        if(t instanceof Charge) {
          setFields();
        }
        return result;
      }
    }
    return false;  // did not find a pole with the correct ID so return false.
  }

  /*
   * public boolean setColor(int id, Color color){
   *    Thing t=null;
   *    for( Enumeration e=drawThings.elements(); e.hasMoreElements();){
   *        t= (Thing) e.nextElement();
   *        if(t.hashCode()==id){
   *          t.setColor(color);
   *          return true;
   *        }
   *    }
   *    return false;
   * }
   */

  /**
   * Method setShowFComponents
   *
   * @param cid
   * @param sc
   *
   * @return
   */
  public boolean setShowFComponents(int cid, boolean sc) {
    Charge c = null;
    for(Enumeration e = poles.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.showFComponents = sc;
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    for(Enumeration e = testCharges.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.showFComponents = sc;
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    return false;  // did not find a charge with the correct ID so return false.
  }

  /**
   * Method setShowFOnDrag
   *
   * @param cid
   * @param sf
   *
   * @return
   */
  public boolean setShowFOnDrag(int cid, boolean sf) {
    Thing t = null;
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      t = (Thing) e.nextElement();
      if(t.hashCode() == cid) {
        t.showFOnDrag = sf;
        return true;
      }
    }
    return false;  // did not find a charge with the correct ID so return false.
  }

  /**
   * Method setShowFVector
   *
   * @param cid
   * @param sf
   *
   * @return
   */
  public boolean setShowFVector(int cid, boolean sf) {
    Charge c = null;
    for(Enumeration e = poles.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.setShowFVector(sf);
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    for(Enumeration e = testCharges.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.setShowFVector(sf);
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    return false;  // did not find a charge with the correct ID so return false.
  }

  /**
   * Method setShowVVector
   *
   * @param cid
   * @param sv
   *
   * @return
   */
  public boolean setShowVVector(int cid, boolean sv) {
    Charge c = null;
    for(Enumeration e = poles.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.setShowV(sv);
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    for(Enumeration e = testCharges.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.setShowV(sv);
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    return false;                                   // did not find a charge with the correct ID so return false.
  }

  /**
   * Method setSpeed
   *
   * @param cid
   * @param speed
   *
   * @return
   */
  public boolean setSpeed(int cid, double speed) {  //only works for test chages.
    Charge c = null;
    for(Enumeration e = testCharges.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.setSpeed(speed);
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    return false;  // did not find a charge with the correct ID so return false.
  }

  /**
   * Method setShowVComponents
   *
   * @param cid
   * @param svc
   *
   * @return
   */
  public boolean setShowVComponents(int cid, boolean svc) {
    Charge c = null;
    for(Enumeration e = poles.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.showVComponents = svc;
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    for(Enumeration e = testCharges.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hashCode() == cid) {
        c.showVComponents = svc;
        //if(autoRefresh)repaint();
        if(autoRefresh) {
          synchronized(delayLock) {
            newData = true;
            delayLockNotify();
          }
        }
        return true;
      }
    }
    return false;  // did not find a charge with the correct ID so return false.
  }

  /**
   * Method setShowTime
   *
   * @param st
   */
  public void setShowTime(boolean st) {
    showTime = st;
  }

  /**
   * Method setShowCoordOnDrag
   *
   * @param sc
   */
  public void setShowCoordOnDrag(boolean sc) {
    showCoordOnDrag = sc;
  }

  /**
   * Method setShowVOnDrag
   *
   * @param sv
   */
  public void setShowVOnDrag(boolean sv) {
    showVOnDrag = sv;
  }

  /**
   * Method setShowEOnDrag
   *
   * @param se
   */
  public void setShowEOnDrag(boolean se) {
    showEOnDrag = se;
  }

  /**
   * Method setShowFieldLineOnClick
   *
   * @param sfl
   */
  public void setShowFieldLineOnClick(boolean sfl) {
    if(showFieldLineOnClick == sfl) {
      return;
    }
    showFieldLineOnClick           = sfl;
    showFieldLineOnDoubleClick     = false;
    showEquipotentialOnClick       = false;
    showEquipotentialOnDoubleClick = false;
  }

  /**
   * Method setShowFieldLineOnDoubleClick
   *
   * @param sfl
   */
  public void setShowFieldLineOnDoubleClick(boolean sfl) {
    if(showFieldLineOnDoubleClick == sfl) {
      return;
    }
    showFieldLineOnDoubleClick     = sfl;
    showFieldLineOnClick           = false;
    showEquipotentialOnClick       = false;
    showEquipotentialOnDoubleClick = false;
  }

  /**
   * Method setShowEquipotentialOnClick
   *
   * @param sp
   */
  public void setShowEquipotentialOnClick(boolean sp) {
    if(showEquipotentialOnClick == sp) {
      return;
    }
    showEquipotentialOnClick       = sp;
    showEquipotentialOnDoubleClick = false;
    showFieldLineOnClick           = false;
    showFieldLineOnDoubleClick     = false;
  }

  /**
   * Method setShowEquipotentialOnDoubleClick
   *
   * @param sp
   */
  public void setShowEquipotentialOnDoubleClick(boolean sp) {
    if(showEquipotentialOnDoubleClick == sp) {
      return;
    }
    showEquipotentialOnDoubleClick = sp;
    showEquipotentialOnClick       = false;
    showFieldLineOnClick           = false;
    showFieldLineOnDoubleClick     = false;
  }

  /**
   * Method setShowLabels
   *
   * @param sl
   */
  public void setShowLabels(boolean sl) {
    contour.setDrawLabels(sl);
    setFields();
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setPotStr
   *
   * @param s
   *
   * @return
   */
  public boolean setPotStr(String s) {
    potStr = s.trim();
    if(potStr.equals("") || potStr.equals("0")) {
      parser = null;
      setFields();
      //if(autoRefresh)repaint();
      if(autoRefresh) {
        synchronized(delayLock) {
          newData = true;
          delayLockNotify();
        }
      }
      return true;
    }
    parser = new Parser(2);
    parser.defineVariable(1, "x");  // define the variable
    parser.defineVariable(2, "y");  // define the variable
    parser.define(potStr);
    parser.parse();
    if(parser.getErrorCode() != Parser.NO_ERROR) {
      System.out.println("Failed to parse U(x,y): " + parser.getFunctionString());
      System.out.println("Parse error: " + parser.getErrorString() + " at function 1, position "
                         + parser.getErrorPosition());
      return false;
    } else {
      setFields();
      //if(autoRefresh)repaint();
      if(autoRefresh) {
        synchronized(delayLock) {
          newData = true;
          delayLockNotify();
        }
      }
      return true;
    }
  }

  /**
   * Method resetTime
   *
   */
  public void resetTime() {
    time      = 0.0;
    dragShape = null;
    Charge c;
    int    n = testCharges.size();
    for(int i = 0; i < n; i++) {
      c = (Charge) testCharges.elementAt(i);
      if(c.hasTrajectory()) {
        c.setTime(time, 0.001);
      } else {
        c.resetTime();
      }
      c.clearTrail();
      c.updateMySlaves();
    }
    time = 0;
    for(Enumeration e = poles.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hasTrajectory()) {
        c.setTime(time, 0.001);
        c.clearTrail();
        c.updateMySlaves();
      }
    }
    setFields();
    message = null;
    //Graphics g=getGraphics();
    //paint(g);
    //g.dispose();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
    if(parentSApplet != null) {
      parentSApplet.clearAllData();
    }
    parentSApplet.updateDataConnections();
  }

  /**
   * Method deleteObject
   *
   * @param id
   *
   * @return
   */
  public boolean deleteObject(int id) {
    Thing t = null;
    t = getThing(id);
    if(t == null) {
      return false;
    }
    if(parentSApplet != null) {
      parentSApplet.stop();  // this will stop the thread and wait for a join.
    }
    stopFieldThreads();
    drawThings.removeElement(t);
    testCharges.removeElement(t);
    poles.removeElement(t);
    if(parentSApplet != null) {
      parentSApplet.removeDataSource(t.hashCode());
      parentSApplet.cleanupDataConnections();
    }
    polemin = 0;
    polemax = 0;
    setFields();
    clearAnchors();
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
    return true;
  }

  /**
   * Method clearAnchors
   *
   */
  void clearAnchors() {
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      Thing t = (Thing) e.nextElement();
      if((t instanceof LineAnchor) && drawThings.contains(((LineAnchor) t).thing)) {
        drawThings.removeElement(t);
      }
    }
  }

  /**
   * Method clearDrawThings
   *
   */
  public void clearDrawThings() {
    Vector v;
    Thing  t;
    synchronized(drawThings) {
      v = (Vector) drawThings.clone();
      drawThings.removeAllElements();
    }
    for(Enumeration e = v.elements(); e.hasMoreElements(); ) {
      t = (Thing) e.nextElement();
      parentSApplet.removeDataSource(t.hashCode());
    }
    if(parentSApplet != null) {
      parentSApplet.cleanupDataConnections();
    }
  }

  /**
   * Method clearTestCharges
   *
   */
  public void clearTestCharges() {
    Vector v;
    synchronized(testCharges) {
      v = (Vector) testCharges.clone();
      testCharges.removeAllElements();
    }
    for(Enumeration e = v.elements(); e.hasMoreElements(); ) {
      Charge c = (Charge) e.nextElement();
      if(c==null) continue;
      if(drawThings.contains(c)) {
        drawThings.removeElement(c);
      }
      //SApplet.dataSources.remove(Integer.toString(c.hashCode()));
      parentSApplet.removeDataSource(c.hashCode());
    }
    if(parentSApplet != null) {
      parentSApplet.cleanupDataConnections();
    }
    clearAnchors();
  }

  /**
   * Method clearPoles
   *
   */
  public void clearPoles() {
    stopFieldThreads();
    Vector v;
    synchronized(poles) {
      v = (Vector) poles.clone();
      poles.removeAllElements();
    }
    for(Enumeration e = v.elements(); e.hasMoreElements(); ) {
      Charge c = (Charge) e.nextElement();
      if(drawThings.contains(c)) {
        drawThings.removeElement(c);
      }
      //parentSApplet.dataSources.remove(Integer.toString(c.hashCode()));
      parentSApplet.removeDataSource(c.hashCode());
    }
    if(parentSApplet != null) {
      parentSApplet.cleanupDataConnections();
    }
    polemin = 0;
    polemax = 0;
    setFields();
    clearAnchors();
    // synchronized(delayLock) can cause a race condition here.  Let the OS do the painting.
    newData = true;
    if(autoRefresh) {
      repaint();
    }
    // bug??? if(autoRefresh) synchronized(delayLock){newData=true; delayLockNotify(); }
  }

  /**
   * Method clearTrails
   *
   */
  public void clearTrails() {
    Charge c;
    int    n = testCharges.size();
    for(int i = 0; i < n; i++) {
      c = (Charge) testCharges.elementAt(i);
      c.clearTrail();
    }
  }

  /**
   * Method addImage
   *
   * @param im
   * @param x
   * @param y
   *
   * @return
   */
  public int addImage(Image im, double x, double y) {
    if(im == null) {
      return 0;
    }
    MediaTracker tracker = new MediaTracker(this);
    try {
      tracker.addImage(im, 0);
      tracker.waitForID(0, 1000);  // wait one second
    } catch(Exception e) {
      //return 0;
    }
    //if(tracker.isErrorAny())  return 0;}
    //if(im.getHeight(this) < 1) { return 0;}
    Thing t = new ImageThing(this, im, x, y);
    drawThings.addElement(t);
    // if(autoRefresh)repaint();
    if(autoRefresh) {
		synchronized (delayLock) {
			newData = true;
			delayLockNotify();
		}
    }
    return t.hashCode();
  }

	@SuppressWarnings("unused")
	private void delayLockNotify() {
		if (/** @j2sNative true || */ false) {
		    state = STATE_PAINTING;			
		} else {
			delayLock.notify();
		}
	}

/**
   * Method addPolyShape
   *
   * @param n
   * @param h
   * @param v
   * @param x
   * @param y
   *
   * @return
   */
  public int addPolyShape(int n, int h[], int v[], double x, double y) {
    Thing t = new ShapeThing(this, n, h, v, x, y);
    drawThings.addElement(t);
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
    return t.hashCode();
  }

  /**
   * Method addTestCharge
   *
   *
   * @return
   */
  public Charge addTestCharge() {
    double x = xmin + (xmax - xmin) * Math.random();
    double y = ymin + (ymax - ymin) * Math.random();
    Charge c = addTestCharge(x, y, 0, 0);
    c.setShowFVector(true);
    c.setShowV(true);
    return c;
  }

  /**
   * Method addTestCharge
   *
   * @param x
   * @param y
   * @param vx
   * @param vy
   *
   * @return
   */
  public Charge addTestCharge(double x, double y, double vx, double vy) {
    Charge c = new TestCharge(this, x, y, vx, vy);
    c.setAcceleration();
    drawThings.addElement(c);
    testCharges.addElement(c);
    //if(autoRefresh)repaint();
    if(autoRefresh) {
		synchronized (delayLock) {
			newData = true;
			delayLockNotify();
		}
    }
    return c;
  }

  /**
   * Method addPole
   *
   * @param m
   *
   * @return
   */
  public Charge addPole(double m) {
    double x = xmin + (xmax - xmin) * Math.random();
    double y = ymin + (ymax - ymin) * Math.random();
    Charge c = addPole(x, y, m);
    return c;
  }

  /**
   * Method addPole
   *
   * @param x
   * @param y
   * @param m
   *
   * @return
   */
  public Charge addPole(double x, double y, double m) {
    Pole p = new Pole(this, x, y, m);
    drawThings.addElement(p);
    stopFieldThreads();
    poles.addElement(p);
    setFields();
    //if(autoRefresh)repaint();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
    return p;
  }

  /**
   * Method update
   *
   * @param g
   */
  public void update(Graphics g) {
    //paint(g); //update usually does a rect fill with a background color.  We don't need this.
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method getBackground
   *
   *
   * @return
   */
  public Color getBackground() {
    if(contour != null) {
      return contour.getDataBackground();
    } else {
      return Color.DARK_GRAY;
    }
  }

  /**
   * Method paintOSI
   *
   */
  public void paintOSI() {
    if(parentSApplet.destroyed) return;
    boolean newImage = false;
    if((osi == null) || (iwidth != getSize().width) || (iheight != getSize().height)) {
      iwidth  = this.getSize().width;
      iheight = this.getSize().height;
      osi     = null;
      setXRange(xmin, xmax);
      // setFields();   this statement moved to setXRange
      osi      = createImage(iwidth, iheight);  //create an off screen image
      osi2     = createImage(iwidth, iheight);  //create an off screen image
      // if(showFieldLines) paintFieldLines();  // ready to paint fields
      newImage = true;
      // System.out.println("I Created 1");
    }
    if(osi == null  || parentSApplet.destroyed) {
      return;
    }
    Graphics osg = osi.getGraphics();  // a graphics context for the  off screen image
    if(osg == null) {
      return;
    }
    osg.setColor(Color.white);
    osg.fillRect(0, 0, iwidth, iheight);
    osiInvalid = false;
    if(contour != null) {
      Rectangle r = getBounds();
      /*
       * The r.x and r.y returned from bounds is relative to the
       * parents space so set them equal to zero.
       */
      r.x = 0;
      r.y = 0;
      if(showContours) {
        contour.noContours = false;
      } else {
        contour.noContours = true;
      }
      //parentSApplet.lock.getBusyFlag();
      contour.paint(osg, r);
      paintArrowHeads(osg, r);
      //parentSApplet.lock.freeBusyFlag();
      if(showFieldVectors) {
        field.paint(osg, r, skip);
      }
      //if(showPoles)paintPoles(osg);    do this with every paint
    } else {
      osg.setColor(getBackground());
      osg.fillRect(0, 0, iwidth, iheight);
      osg.setColor(osg.getColor());
      osg.clipRect(0, 0, iwidth, iheight);
      // paintGrid(osg);
    }
    osg.setColor(Color.black);
    osg.drawRect(0, 0, iwidth - 1, iheight - 1);
    osg.dispose();
    if(newImage) {
      clearTrails();
    }
    return;
  }

  /*
   * public void paintOffScreen() {
   *     // copy an image from osi to osi2 for use while dragging or stepping.
   *     // osi has the contour but not the charges or shapes.
   *     Graphics g=getGraphics();
   *     int w = getSize().width;
   *     int h = getSize().height;
   *     if(osi2==null) return;
   *     Graphics osg = osi2.getGraphics();// a graphics context for the  off screen image
   *     paint(osg);
   *     g.drawImage(osi2, 0, 0, w, h, this);
   *     if(isDrag)paintCoordinates(g,mouseX,mouseY);
   *     osg.dispose();
   *     g.dispose();
   * }
   */

  /**
   * Method paint
   *
   * @param g
   */
  public void paint(Graphics g) {
    if(parentSApplet.destroyed) return;
    synchronized(delayLock) {
      newData = true;
      delayLockNotify();
    }
  }

  /**
   * Method paint
   *
   */
  public void paint() {
    if(parentSApplet.destroyed) return;
    try{
    if((getSize().width == 0) || (getSize().height == 0)) {
      return;
    }
    if((osi == null) || osiInvalid || (iwidth != this.getSize().width) || (iheight != this.getSize().height)) {
      if(this.getSize().width == 0) {
        return;
      } else {
        paintOSI();
      }
    }
    if((osi == null) || (osi2 == null)) {
      return;
    }
    Graphics g = osi2.getGraphics();
    g.drawImage(osi, 0, 0, this);  // osi has the contours and the field lines
    Font oldFont = g.getFont();
    g.setFont(f);  // this font is used for messages and captions.
    //paintTestCharges(g);
    //paintPoles(g);
    paintThings(g);
    g.setColor(Color.black);
    paintMessage(g, message);
    g.setColor(Color.black);
    FontMetrics fm = g.getFontMetrics(f);
    if(caption != null) {
      g.setColor(Color.black);
      g.drawString(caption, (iwidth - fm.stringWidth(caption)) / 2, 25);
    }
    if(showTime) {
      String tStr = new Format("%7.4g").form(time);
      g.setColor(Color.black);
      if(iwidth > 150) {
        g.drawString(parentSApplet.label_time + tStr, 10, 15);
      } else {
        g.drawString(parentSApplet.label_time + tStr, 10, iheight - 40);
      }
    }
    g.setFont(oldFont);
    g.dispose();  // dispose g from osi1
    g = getGraphics();  // get graphics context for this panel
    if((g == null) || (osi2 == null)) {
      return;
    }
    g.drawImage(osi2, 0, 0, this);
    g.dispose();
    if(fieldLinesInvalid && (dragShape == null)) {
      paintFieldLines();  // start the treads to paint the field lines
      fieldLinesInvalid = false;
    }
    }catch(Exception ex){repaint();}
  }

  /**
   * Method paintMessage
   *
   * @param osg
   * @param msg
   */
  public void paintMessage(Graphics osg, String msg) {
    if(msg == null) {
      return;
    }
    FontMetrics fm = osg.getFontMetrics(f);
    osg.setColor(Color.yellow);
    int w = 15 + fm.stringWidth(msg);
    osg.fillRect(iwidth - w - 5, iheight - 15, w, 15);
    osg.setColor(Color.black);
    osg.drawString(msg, iwidth - w + 2, iheight - 3);
    osg.drawRect(iwidth - w - 5, iheight - 15, w, 15);
  }

  /**
   * Method paintForceOnCharge
   *
   * @param g
   * @param c
   */
  public void paintForceOnCharge(Graphics g, Charge c) {
    double x        = c.getX();
    double y        = c.getY();
    double m        = c.getMag();
    //if((parser==null) || (potStr.trim()=="")) return;
    double fx       = m * (-dudx(x, y) + getPoleFx(x, y, c) + bz * c.getVY());
    double fy       = m * (-dudy(x, y) + getPoleFy(x, y, c) - bz * c.getVX());
    int    x0       = pixFromX(x);
    int    y0       = pixFromY(y);
    int    x1       = pixFromX(x + fx);
    int    y1       = pixFromY(y + fy);
    Color  oldColor = g.getColor();
    if(c.showFComponents) {
      g.setColor(lightBlue);
      SUtil.drawArrow(g, x0, y0, x1, y0);
      SUtil.drawArrow(g, x1, y0, x1, y1);
    }
    g.setColor(darkBlue);
    SUtil.drawArrow(g, x0, y0, x1, y1);
    g.setColor(oldColor);
  }

  /**
   * Method paintTestCharges
   *
   * @param g
   */
  public void paintTestCharges(Graphics g) {
    Charge c;
    int    n = testCharges.size();
    for(int i = 0; i < n; i++) {
      c = (Charge) testCharges.elementAt(i);
      if(dragShape != c) {
        c.paint(g);
        if(c.isShowFVector()) {
          paintForceOnCharge(g, c);
        }
      }
    }
  }

  /**
   * Method paintPoles
   *
   * @param g
   */
  public void paintPoles(Graphics g) {
    Pole p;
    int  n = poles.size();
    for(int i = 0; i < n; i++) {
      p = (Pole) poles.elementAt(i);
      if(dragShape != p) {
        p.paint(g);
        if(p.isShowFVector()) {
          paintForceOnCharge(g, p);
        }
      }
    }
  }

  /**
   * Method paintThings
   *
   * @param g
   */
  public void paintThings(Graphics g) {
    Vector v;
    synchronized(drawThings) {
      v = (Vector) drawThings.clone();
    }
    for(Enumeration e = v.elements(); e.hasMoreElements(); ) {
      Thing t = (Thing) e.nextElement();
      //if(dragShape!=t){
      t.paint(g);
      if((t instanceof Charge) && ((Charge) t).isShowFVector()) {
        paintForceOnCharge(g, (Charge) t);
      }
      //}
      // if(showPoles)paintPoles(osg);   trap for this!
    }
  }

  /**
   * Method paintArrowHeads
   *
   * @param g
   * @param r
   */
  public void paintArrowHeads(Graphics g, Rectangle r) {
    ArrowHead ah;
    int       n = arrowHeads.size();
    for(int i = 0; i < n; i++) {
      ah = (ArrowHead) arrowHeads.elementAt(i);
      ah.paint(g, r);
    }
  }

  /**
   * Method getPotential
   *
   * @param x
   * @param y
   *
   * @return
   */
  final double getPotential(double x, double y) {
    if(parser != null) {
      return parser.evaluate(x, y) + getPoleU(x, y);
    }
    return getPoleU(x, y);
  }

  /**
   * Method getPE
   *
   * @param c
   *
   * @return
   */
  final double getPE(Charge c) {
    // find the potential energy for a charge
    if(c == null) {
      return 0;
    }
    double x1 = c.getX();
    double y1 = c.getY();
    double m1 = c.getMag();
    if(poles == null) {
      return 0;
    }
    Pole   p;
    double x = 0, y = 0, m = 0, u = 0, r2 = 0;
    int    n = poles.size();
    for(int i = 0; i < n; i++) {
      p = (Pole) poles.elementAt(i);
      if(p != c) {
        x  = p.getX();
        y  = p.getY();
        m  = p.getMag();
        r2 = (x - x1) * (x - x1) + (y - y1) * (y - y1);
        if(r2 != 0) {  // make sure two charges are not on top of each other.
          if(pointChargeMode) {
            u += m * m1 / Math.sqrt(r2);
          } else {
            u += 2 * m * m1 * Math.log(r2);
          }
        } else {
          u = Double.NaN;
        }
      }
    }
    if(parser != null) {
      u += m1 * parser.evaluate(x1, y1);
    }
    return u;
  }

  /**
   * Method getPoleU
   *
   * @param x1
   * @param y1
   *
   * @return
   */
  final double getPoleU(double x1, double y1) {
    // find the potential energy due to the fixed charges
    if(poles == null) {
      return 0;
    }
    Pole   p;
    double x = 0, y = 0, m = 0, u = 0, r2 = 0;
    int    n = poles.size();
    for(int i = 0; i < n; i++) {
      p  = (Pole) poles.elementAt(i);
      x  = p.getX();
      y  = p.getY();
      m  = p.getMag();
      r2 = (x - x1) * (x - x1) + (y - y1) * (y - y1);
      if(r2 != 0) {
        if(pointChargeMode) {
          u += m / Math.sqrt(r2);
        } else {
          u += 2 * m * Math.log(r2);
        }
      }
    }
    return u;
  }

  /**
   * Method getFx
   *
   * @param c
   *
   * @return
   */
  final double getFx(Charge c) {
    double x = c.getX();
    double y = c.getY();
    double m = c.getMag();
    //if((parser==null) || (potStr.trim()=="")) return;
    return m * (-dudx(x, y) + getPoleFx(x, y, c) + bz * c.getVY());
  }

  /**
   * Method getFy
   *
   * @param c
   *
   * @return
   */
  final double getFy(Charge c) {
    double x = c.getX();
    double y = c.getY();
    double m = c.getMag();
    return m * (-dudy(x, y) + getPoleFy(x, y, c) - bz * c.getVX());
  }

  /**
   * Method getPoleFx
   *
   * @param x1
   * @param y1
   * @param c
   *
   * @return
   */
  final double getPoleFx(double x1, double y1, Charge c) {
    // get the electric field at (x1, y1) due to the poles
    if(poles == null) {
      return 0;
    }
    Pole   p;
    double x = 0, y = 0, m = 0, fx = 0;
    int    n = poles.size();
    for(int i = 0; i < n; i++) {
      p = (Pole) poles.elementAt(i);
      if(c != p) {
        x = p.getX();
        y = p.getY();
        m = p.getMag();
        if(pointChargeMode) {
          fx += m * (x1 - x) / Math.pow((x - x1) * (x - x1) + (y - y1) * (y - y1), 1.5);
        } else {
          fx += 2 * m * (x1 - x) / ((x - x1) * (x - x1) + (y - y1) * (y - y1));
        }
      }
    }
    return fx;
  }

  /**
   * Method getPoleFx_Cyl
   *
   * @param x1
   * @param y1
   * @param c
   *
   * @return
   */
  final double getPoleFx_Cyl(double x1, double y1, Charge c) {
    if(poles == null) {
      return 0;
    }
    Pole   p;
    double x = 0, y = 0, m = 0, fx = 0;
    int    n = poles.size();
    for(int i = 0; i < n; i++) {
      p = (Pole) poles.elementAt(i);
      if(c != p) {
        x  = p.getX();
        y  = p.getY();
        m  = p.getMag();
        fx += 2 * m * (x1 - x) / ((x - x1) * (x - x1) + (y - y1) * (y - y1));  // factor of 2 due to E=2*Q/r for line charge
      }
    }
    return fx;
  }

  /**
   * Method getPoleFy
   *
   * @param x1
   * @param y1
   * @param c
   *
   * @return
   */
  final double getPoleFy(double x1, double y1, Charge c) {
    if(poles == null) {
      return 0;
    }
    Pole   p;
    double x = 0, y = 0, m = 0, fy = 0;
    int    n = poles.size();
    for(int i = 0; i < n; i++) {
      p = (Pole) poles.elementAt(i);
      if(c != p) {
        x = p.getX();
        y = p.getY();
        m = p.getMag();
        if(pointChargeMode) {
          fy += m * (y1 - y) / Math.pow((x - x1) * (x - x1) + (y - y1) * (y - y1), 1.5);
        } else {
          fy += 2 * m * (y1 - y) / ((x - x1) * (x - x1) + (y - y1) * (y - y1));
        }
      }
    }
    return fy;
  }

  /**
   * Method getPoleFy_Cyl
   *
   * @param x1
   * @param y1
   * @param c
   *
   * @return
   */
  final double getPoleFy_Cyl(double x1, double y1, Charge c) {
    if(poles == null) {
      return 0;
    }
    Pole   p;
    double x = 0, y = 0, m = 0, fy = 0;
    int    n = poles.size();
    for(int i = 0; i < n; i++) {
      p = (Pole) poles.elementAt(i);
      if(c != p) {
        x  = p.getX();
        y  = p.getY();
        m  = p.getMag();
        fy += 2 * m * (y1 - y) / ((x - x1) * (x - x1) + (y - y1) * (y - y1));  // factor of 2 due to E=2*Q/r for line charge
      }
    }
    return fy;
  }

  /**
   * Method dudx
   *
   * @param x
   * @param y
   *
   * @return
   */
  final double dudx(double x, double y) {
    double h = 1.0e-7;
    if(parser == null) {
      return 0;
    }
    double y1 = parser.evaluate(x - 2 * h, y);
    double y2 = parser.evaluate(x - h, y);
    double y3 = parser.evaluate(x + h, y);
    double y4 = parser.evaluate(x + 2 * h, y);
    //if(Math.abs(y2)>1.0e6 ||  Math.abs(y3)>1.0e6) return 0;
    return (-y4 + 8 * y3 - 8 * y2 + y1) / (12 * h);
  }

  /**
   * Method dudy
   *
   * @param x
   * @param y
   *
   * @return
   */
  final double dudy(double x, double y) {
    double h = 1.0e-7;
    if(parser == null) {
      return 0;
    }
    double y1 = parser.evaluate(x, y - 2 * h);
    double y2 = parser.evaluate(x, y - h);
    double y3 = parser.evaluate(x, y + h);
    double y4 = parser.evaluate(x, y + 2 * h);
    // if(Math.abs(y2)>1.0e6 ||  Math.abs(y3)>1.0e6) return 0;
    return (-y4 + 8 * y3 - 8 * y2 + y1) / (12 * h);
  }

  /**
   * Method getTime
   *
   *
   * @return
   */
  public double getTime() {
    return time;
  }

  /**
   * Method getThing
   *
   * @param id
   *
   * @return
   */
  public Thing getThing(int id) {
    Thing t = null;
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      t = (Thing) e.nextElement();
      if(t.hashCode() == id) {
        return t;
      }
    }
    return null;
  }

  /**
   * Method getCollisionThing
   *
   *
   * @return
   */
  public CollisionThing getCollisionThing() {
    if(collisionDataSource != null) {
      return collisionDataSource;
    }
    collisionDataSource = new CollisionThing(this);
    drawThings.addElement(collisionDataSource);
    return collisionDataSource;
  }

  /**
   * Method getParticleEnergy
   *
   * @param s
   *
   * @return
   */
  public double getParticleEnergy(Charge s) {
    double x  = s.getX();
    double y  = s.getY();
    double vx = s.getVX();
    double vy = s.getVY();
    double u;
    if(parser != null) {
      u = parser.evaluate(x, y) + getPoleU(x, y);
    } else {
      u = getPoleU(x, y);
    }
    return vx * vx / 2 + vy * vy / 2 + u;
  }

  /**
   * Make the OdeCanvas a dataSource
   * public double[][] getVariables(){
   *     double[][] v=new double[1][1];
   *     return v;
   * }
   *
   * @return
   */
  public boolean testForCollision() {
    Charge c;
    int    n = testCharges.size();
    for(int i = 0; i < n; i++) {  //update test charges
      c = (Charge) testCharges.elementAt(i);
      if(!c.disabled) {
        if((c instanceof TestCharge) && isInsideStickyThing(pixFromX(c.vars[1]), pixFromY(c.vars[2]), c)) {
          return true;
        }
      }
    }
    return false;
  }

  // this method will paint the screen but not too fast.

  
  
  private final static int STATE_INIT = 0;
  private final static int STATE_PAINTING = 1;
  private final static int STATE_SLEEPING = 2;
  
  protected int state = STATE_INIT;
  
  private Timer timer;
  
	/**
	 * Method run
	 *
	 */
	public void run() {
		while (delayThread != null) {
			synchronized (delayLock) {
				switch (state) {
				case STATE_INIT:
					if (!newData && (delayThread != null)) {
						try {
							if (isJS) {
								state = STATE_SLEEPING;
							    continue;	
							} else {
								delayLock.wait();
							}
						} catch (InterruptedException ie) {
							return;
						}
					}
					state = STATE_PAINTING;
					continue;
				case STATE_PAINTING:
					newData = false;
					if (delayThread != null) {
						// parentSApplet.lock.getBusyFlag();
						paint();
						if (isDrag) {
							paintCoordinates(mouseX, mouseY);
						}
						// parentSApplet.lock.freeBusyFlag();
					}
					state = STATE_SLEEPING;
					continue;
				case STATE_SLEEPING:
					if (delayThread != null) {
						try {
							if (isJS) {
								timer = new Timer(50, new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										state = STATE_INIT;
										run();
									}

								});
								timer.setRepeats(false);
								timer.start();
								return;
							} else {
								Thread.sleep(50);
								state = STATE_INIT;
								continue;
							}
						} catch (InterruptedException ie) {
							return;
						}
					}
					break;
				}
			}
		}
		delayThread = null;
	}

  /**
   * Method step
   *
   * @param dt
   * @param t_
   */
  public void step(double dt, double t_) {
    time = t_ + dt;  // increase the time so trajectories will evaluate correctly.
    //double[] x = new double[5];
    Charge   c;
    int      n = testCharges.size();
    for(int i = 0; i < n; i++) {                 //update test charges
      c = (Charge) testCharges.elementAt(i);
      //   oldE+= getParticleEnergy(c);
      if(!c.disabled && (c.myMaster == null)) {  // do not update slaves in this loop
        if(c.hasTrajectory()) {
          c.setTime(time, dt);
        } else {
          ((TestCharge) c).enforceConstraintOnXY();
          ((TestCharge) c).odeSolver.step(dt, c.getVars());
        }
        c.incTrail();
        if((c instanceof TestCharge) && isInsideStickyThing(pixFromX(c.vars[1]), pixFromY(c.vars[2]), c)) {
          collision = true;
          c.vars[3] = 0;                         // set the velocity to zero.
          c.vars[4] = 0;
          if(this.collisionDataSource != null) {
            collisionDataSource.setXYT(c.vars[1], c.vars[1], time);
            collisionDataSource.setBlock(false);
            parentSApplet.updateDataConnection(collisionDataSource.hashCode());
            collisionDataSource.setBlock(true);
          }
        }
      }
      //   newE+= getParticleEnergy(c);
    }
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      Thing t = (Thing) e.nextElement();
      if(t.myMaster != null) {  // update only the slaves since everything else has been updated.
        t.setVarsFromMaster();
        t.incTrail();
      }
    }
    boolean hasMoved = false;  // check to see if we need to redraw fields and contours.
    for(Enumeration e = poles.elements(); e.hasMoreElements(); ) {
      c = (Charge) e.nextElement();
      if(c.hasTrajectory()) {
        hasMoved = true;
        c.setTime(time, dt);
        c.incTrail();
      }
    }
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      Thing t = (Thing) e.nextElement();
      if(!(t instanceof Charge)) {  // update everything EXCEPT charges
        t.setTime(time, dt);
      }
    }
    if(hasMoved && showContours) {
      setFields();
    } else if(hasMoved && showFieldVectors) {
      setDirectionVectors();
      osiInvalid = true;
    }
    if(parentSApplet != null) {
      parentSApplet.updateDataConnections();
    }
    //if(autoRefresh)paintOffScreen();
    if(autoRefresh) {
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
    if(collision) {
      setMessage(parentSApplet.label_collision);
      parentSApplet.stopClock();
    }
  }

  private void stopDrawingThread() {
    //keepDrawing=false;
    synchronized(delayLock) {
      delayLockNotify();
    }  // make sure the thread is running so that it can stop.
    try {
      delayThread.interrupt();
      delayThread.join(1000);
    } catch(Exception e) {
      ;
    }  // wait up to 1 seconds.
    //delayThread.stop();  // needed to fix a browser bug.
    //delayThread=null;
  }

  //Destroy all the threads

  /**
   * Method destroy
   *
   */
  public void destroy() {
    synchronized(delayLock) {
      stopFieldThreads();
    }
    stopDrawingThread();
    contour.destroy();
  }

  /**
   * Method stopFieldThreads
   *
   */
  void stopFieldThreads() {
    //   System.out.println("Stop FieldThreads");
    Vector v;
    synchronized(fieldSolvers) {
      v = (Vector) fieldSolvers.clone();
    }
    if(v.size() == 0) {
      return;
    }
    FieldSolver fs;
    for(Enumeration e = v.elements(); e.hasMoreElements(); ) {
      fs = (FieldSolver) e.nextElement();
      if(fs==null) continue;
      fs.interrupt();
      fieldSolvers.removeElement(fs);
    }
    parentSApplet.lock.getBusyFlag();
    arrowHeads.removeAllElements();
    contour.deleteAllNonSeriesData();
    v = null;
    parentSApplet.lock.freeBusyFlag();
    calculatingFieldLines = false;
  }

  /**
   * Method setZRange
   *
   */
  void setZRange() {
    Pole p;
    polemin = 0;
    polemax = 0;
    int n = fieldPoles.size();
    for(int i = 0; i < n; i++) {
      p = (Pole) fieldPoles.elementAt(i);
      if(polemin > p.getMaxU()) {
        polemin = p.getMaxU();
      }
      if(polemax < p.getMaxU()) {
        polemax = p.getMaxU();
      }
    }
  }

  /*
   * void setZRange(){
   *    Pole p=null;
   *    polemin=0;
   *    polemax=0;
   *    double plusCharge=0;
   *    double minusCharge=0;
   *    int n=poles.size();
   *    if(n==0) return;
   *    for(int i=0; i<n; i++){
   *       p=(Pole)poles.elementAt(i);
   *       if(p.getMag()<0)minusCharge+=p.getMag();
   *       if(p.getMag()>0)plusCharge+=p.getMag();
   *    }
   *    double x0=xFromPix(0);
   *    double x1=xFromPix(p.s+2);
   *    double r2=(x0-x1)*(x0-x1);
   *
   *    polemin=minusCharge/Math.sqrt(r2);
   *    polemax=plusCharge/Math.sqrt(r2);
   * }
   */

  /**
   * Set the field resolution determines how the spacing of the field vectors on the grid.
   * A resoluton of 2 would skip every other grid point when calculating the field vectors.
   *
   * @param              r the resolution
   */
  void setFieldResolution(int r) {
    if(skip == r) {
      return;
    }
    skip = r;
    if(autoRefresh) {
      setFields();
      //repaint();
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setGridSize
   *
   * @param n
   */
  void setGridSize(int n) {
    if(gridSize == n) {
      return;
    }
    gridSize = n;
    if(autoRefresh) {
      setFields();
      //repaint();
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

  /**
   * Method setFormat
   *
   * @param str
   *
   * @return
   */
  public final boolean setFormat(String str) {
    try {
      format = new Format(str);
    } catch(IllegalArgumentException e) {
      return false;
    }
    return true;
  }

  private void deleteFieldLines() {
    DataSet dataSet;
    for(Enumeration e = fieldLines.elements(); e.hasMoreElements(); ) {
      dataSet = (DataSet) e.nextElement();
      contour.detachDataSet(dataSet);
    }
    arrowHeads.removeAllElements();
    fieldLines.removeAllElements();
  }

  /**
   * Method setFields
   *
   */
  synchronized void setFields() {
    // reclalculate all the fields based on the current charge positions.
    if(!autoRefresh) {
      return;  // minimize calculations if we aren't painting.
    }
    stopFieldThreads();
    clearTrails();
    parentSApplet.lock.getBusyFlag();
    int nx = gridSize;
    int ny = gridSize;
    //contour.deleteAllSeries();
    deleteFieldLines();
    contour.deleteContours();
    //Rectangle r=getBounds();
    double[][] z = new double[nx][ny];
    int        i, j;
    double     x, y;
    calcFieldPoles();
    setZRange();
    for(j = 0; j < ny; j++) {
      y = (ymax - ymin) * (double) j / (double) (ny - 1) + ymin;
      for(i = 0; i < nx; i++) {
        x = (xmax - xmin) * (double) i / (double) (nx - 1) + xmin;
        double u = getPoleU(x, y);
        if(u > polemax) {
          u = polemax;
        }
        if(u < polemin) {
          u = polemin;
        }
        if(parser != null) {
          z[i][j] = parser.evaluate(x, y) + u;
        } else {
          z[i][j] = u;
        }
      }
    }
    contour.setGrid(z, xmin, xmax, ymin, ymax);
    nx = gridSize / skip;
    ny = gridSize / skip;
    double[][][] force = field.resize(ny, nx);
    for(j = 0; j < nx; j++) {
      x = (xmax - xmin) * (double) j / (double) (nx - 1) + xmin;
      for(i = 0; i < ny; i++) {
        y = (ymax - ymin) * (double) i / (double) (ny - 1) + ymin;
        double fx  = -dudx(x, y) + getPoleFx(x, y, null);
        double fy  = -dudy(x, y) + getPoleFy(x, y, null);
        double mag = Math.sqrt(fx * fx + fy * fy);
        if(mag > 0) {
          force[i][j][0] = fx / mag;
          force[i][j][1] = fy / mag;
          force[i][j][2] = mag;
        } else {
          force[i][j][0] = 0;
          force[i][j][1] = 0;
          force[i][j][2] = 0;
        }
      }
    }
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      Thing t = (Thing) e.nextElement();
      t.setAcceleration();
    }
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      Thing t = (Thing) e.nextElement();
      t.setVarsFromMaster();
    }
    osiInvalid = true;  // this will force a repaint;
    parentSApplet.lock.freeBusyFlag();
    if(showFieldLines) {
      fieldLinesInvalid = true;
    }
    //if( showFieldLines && osi!=null) paintFieldLines(); // start the treads to paint the field lines
  }

  /**
   * Method setDirectionVectors
   *
   */
  void setDirectionVectors() {
    double       x, y;
    int          nx    = gridSize / skip;
    int          ny    = gridSize / skip;
    double[][][] force = field.resize(ny, nx);
    for(int j = 0; j < nx; j++) {
      x = (xmax - xmin) * (double) j / (double) (nx - 1) + xmin;
      for(int i = 0; i < ny; i++) {
        y = (ymax - ymin) * (double) i / (double) (ny - 1) + ymin;
        double fx  = -dudx(x, y) + getPoleFx(x, y, null);
        double fy  = -dudy(x, y) + getPoleFy(x, y, null);
        double mag = Math.sqrt(fx * fx + fy * fy);
        if(mag > 0) {
          force[i][j][0] = fx / mag;
          force[i][j][1] = fy / mag;
          force[i][j][2] = mag;
        } else {
          force[i][j][0] = 0;
          force[i][j][1] = 0;
          force[i][j][2] = 0;
        }
      }
    }
  }

  /**
   * Method xFromPix
   *
   * @param pix
   *
   * @return
   */
  public double xFromPix(int pix) {
    if(contour != null) {
      return contour.xFromPix(pix);
    } else {
      return pix;
    }
  }

  /**
   * Method pixFromX
   *
   * @param x
   *
   * @return
   */
  public int pixFromX(double x) {
    if(contour != null) {
      return contour.pixFromX(x);
    } else {
      return (int) Math.round(x);
    }
  }

  /**
   * Method yFromPix
   *
   * @param pix
   *
   * @return
   */
  public double yFromPix(int pix) {
    if(contour != null) {
      return contour.yFromPix(pix);
    } else {
      return pix;
    }
  }

  /**
   * Method pixFromY
   *
   * @param y
   *
   * @return
   */
  public int pixFromY(double y) {
    if(contour != null) {
      return contour.pixFromY(y);
    } else {
      return (int) Math.round(y);
    }
  }

  /**
   * Method calcFieldPoles
   *
   */
  void calcFieldPoles() {
    // combine any overlapping poles into a single pole
    Pole p;
    synchronized(poles) {
      fieldPoles.removeAllElements();
      int n = poles.size();
      allPositive = true;
      allNegative = true;
      if(n > 0) {
        p = (Pole) ((Pole) poles.elementAt(0)).clone();
      } else {
        return;
      }
      fieldPoles.addElement(p);
      if(p.mag < 0) {
        allPositive = false;
      }
      if(p.mag > 0) {
        allNegative = false;
      }
      for(int i = 1; i < n; i++) {
        p = (Pole) ((Pole) poles.elementAt(i)).clone();
        if(p.mag < 0) {
          allPositive = false;
        }
        if(p.mag > 0) {
          allNegative = false;
        }
        boolean noOverlap = true;
        for(int j = 0; j < fieldPoles.size(); j++) {
          Pole fp = (Pole) fieldPoles.elementAt(j);
          if(fp.doesOverlap(p)) {
            noOverlap = false;
            fp.mag    = fp.mag + p.mag;
            //  System.out.println("Overlap Found");
            break;  // make sure we only add once;
          }
        }
        if(noOverlap) {
          fieldPoles.addElement(p);
        }
      }
    }
  }

  /**
   * Method paintFieldLines
   *
   */
  void paintFieldLines() {
    if(parentSApplet.destroyed) return;
    if((parser == null) && (fieldPoles.size() == 0)) {
      return;  // nothing to paint.
    }
    //stopFieldThreads();
    Pole   pole;
    double x0, y0, x1, y1, a = 0;
    double totalCharge = 0;
    int    numLines    = 12;
    // calcFieldPoles();  // this is now done in set fields;
    int    n           = fieldPoles.size();
    for(int i = 0; i < n; i++) {
      pole        = (Pole) fieldPoles.elementAt(i);
      totalCharge += pole.mag;
    }
    // paint field lines that start at the dominant charge type
    // System.out.println("Paint FieldThreads q="+totalCharge);
    for(int i = 0; i < n; i++) {
      pole = (Pole) fieldPoles.elementAt(i);
      if(Math.abs(pole.mag) < 100 * Double.MIN_VALUE) {
        continue;
      }
      numLines = (int) Math.round(Math.abs(12.0 * pole.mag));
      if(numLines > 48) {
        numLines = 32;  // limit the number
      }
      x0 = pole.getX();
      y0 = pole.getY();
      double r = pole.getRadius();
      for(int j = 0; j < numLines; j++) {
        a  = 2 * Math.PI * j / numLines;
        x1 = r * Math.cos(a);
        y1 = r * Math.sin(a);
        if((pole.mag > 0) && (totalCharge >= 0)) {
          new FieldSolver(x0 + x1, y0 + y1, true, pole);
        }
        if((pole.mag < 0) && (totalCharge < 0)) {
          new FieldSolver(x0 + x1, y0 + y1, false, pole);
        }
      }
    }
    double dx       = (xmax - xmin) / 100.0;
    double integral = 0;
    if(dx <= 0) {
      dx = 0.01;
    }
    if(parser != null) {
      boolean plus = (totalCharge >= 0);
      integral = paintLeftFieldLines(integral, dx, plus);    //left
      integral = paintTopFieldLines(integral, dx, plus);     //top
      integral = paintRightFieldLines(integral, dx, plus);   //right
      integral = paintBottomFieldLines(integral, dx, plus);  //bottom
    }
    return;
  }

  /**
   * Method paintLeftFieldLines
   *
   * @param integral
   * @param ds
   * @param plus
   *
   * @return
   */
  double paintLeftFieldLines(double integral, double ds, boolean plus) {
    double x = xmin;
    double y = ymin;
    while(y < ymax) {
      y += ds;
      double fx = -(-dudx(x, y) + getPoleFx_Cyl(x, y, null));
      if(plus && (fx < 0)) {
        integral += Math.abs(fx * ds);
      }                               // entering
      if(!plus && (fx > 0)) {
        integral += fx * ds;
      }                               // leaving
      if(integral > 0.52) {
        integral -= 0.52;
        new FieldSolver(x, y, plus);  // boolean gives direction of solution
      }
    }
    return integral;
  }

  /**
   * Method paintTopFieldLines
   *
   * @param integral
   * @param ds
   * @param plus
   *
   * @return
   */
  double paintTopFieldLines(double integral, double ds, boolean plus) {
    double x = xmin;
    double y = ymax;
    while(x < xmax) {
      x += ds;
      double fy = -dudy(x, y) + getPoleFy_Cyl(x, y, null);
      if(plus && (fy < 0)) {
        integral += Math.abs(fy * ds);
      }                               // entering
      if(!plus && (fy > 0)) {
        integral += fy * ds;
      }                               // leaving
      if(integral > 0.52) {
        integral -= 0.52;
        new FieldSolver(x, y, plus);  // boolean gives direction of solution
      }
    }
    return integral;
  }

  /**
   * Method paintRightFieldLines
   *
   * @param integral
   * @param ds
   * @param plus
   *
   * @return
   */
  double paintRightFieldLines(double integral, double ds, boolean plus) {
    double x = xmax;
    double y = ymax;
    while(y > ymin) {
      y -= ds;
      double fx = -dudx(x, y) + getPoleFx_Cyl(x, y, null);  // reverse the sign
      if(plus && (fx < 0)) {
        integral += Math.abs(fx * ds);
      }                                                     // entering
      if(!plus && (fx > 0)) {
        integral += fx * ds;
      }                                                     // leaving
      if(integral > 0.52) {
        integral -= 0.52;
        new FieldSolver(x, y, plus);                        // boolean gives direction of solution
      }
    }
    return integral;
  }

  /**
   * Method paintBottomFieldLines
   *
   * @param integral
   * @param ds
   * @param plus
   *
   * @return
   */
  double paintBottomFieldLines(double integral, double ds, boolean plus) {
    double x = xmax;
    double y = ymin;
    while(x > xmin) {
      x -= ds;
      double fy = -(-dudy(x, y) + getPoleFy_Cyl(x, y, null));
      if(plus && (fy < 0)) {
        integral += Math.abs(fy * ds);
      }                               // entering
      if(!plus && (fy > 0)) {
        integral += fy * ds;
      }                               // leaving
      if(integral > 0.52) {
        integral -= 0.52;
        new FieldSolver(x, y, plus);  // boolean gives direction of solution
      }
    }
    return integral;
  }

  /**
   * Method isInsideStickyThing
   *
   * @param x
   * @param y
   * @param exclude
   *
   * @return
   */
  boolean isInsideStickyThing(int x, int y, Thing exclude) {
    Thing t = null;
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      t = (Thing) e.nextElement();
      if((t != exclude) && t.sticky && t.isInsideThing(x, y)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method isInsideDragableThing
   *
   * @param x
   * @param y
   *
   * @return
   */
  boolean isInsideDragableThing(int x, int y) {
    Thing t = null;
    for(Enumeration e = drawThings.elements(); e.hasMoreElements(); ) {
      t = (Thing) e.nextElement();
      if(!t.noDrag && t.isInsideThing(x, y)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method isInsidePole
   *
   * @param x
   * @param y
   *
   * @return
   */
  boolean isInsidePole(double x, double y) {
    //if (dragShape instanceof Pole) return true;
    Pole pole;
    int  n = poles.size();
    for(int i = 0; i < n; i++) {
      pole = (Pole) poles.elementAt(i);
      if(pole.isInsidePole(x, y, this) && (pole != dragShape)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method paintCoordinates
   *
   * @param x
   * @param y
   */
  void paintCoordinates(double x, double y) {
    Graphics g = getGraphics();
    paintCoordinates(g, x, y);
    g.dispose();
  }

  /**
   * Method paintCoordinates
   *
   * @param g
   * @param x
   * @param y
   */
  void paintCoordinates(Graphics g, double x, double y) {
    FontMetrics fm  = g.getFontMetrics(g.getFont());
    String      msg = "";
    if(showCoordOnDrag) {
      msg = "x=" + format.form(x) + " y=" + format.form(y);
    }
    boolean infinite = isInsidePole(x, y);
    if(showVOnDrag) {
      if(infinite || ((dragShape != null) && (dragShape instanceof Pole))) {
        msg = msg + " "+parentSApplet.label_volt_undefined;
      } else {
        double u;
        if(parser != null) {
          u = parser.evaluate(x, y) + getPoleU(x, y);
        } else {
          u = getPoleU(x, y);
        }
        msg = msg + " "+parentSApplet.label_volt + format.form(u);
      }
    }

    if((dragShape != null) && dragShape.showFOnDrag && (dragShape instanceof Charge)) {
      double fx = dragShape.mag * (-dudx(x, y) + getPoleFx(x, y, (Charge) dragShape) + bz * dragShape.getVY());
      double fy = dragShape.mag * (-dudy(x, y) + getPoleFy(x, y, (Charge) dragShape) - bz * dragShape.getVX());
      if(infinite) {
        msg = msg + " "+parentSApplet.label_force_undefined;
      } else {
        msg = msg + " "+parentSApplet.label_force + format.form(Math.sqrt(fx * fx + fy * fy));
      }
    } else if(showEOnDrag) {
      if(infinite || (dragShape instanceof Pole)) {
        infinite = true;
        msg      = msg + " "+parentSApplet.label_field_undefined;
      } else {
        double fx = -dudx(x, y) + getPoleFx(x, y, null);
        double fy = -dudy(x, y) + getPoleFy(x, y, null);
        msg = msg + " "+parentSApplet.label_field + format.form(Math.sqrt(fx * fx + fy * fy));
      }
    }
    g.setColor(Color.yellow);
    //g.fillRect(0,getBounds().height-15,boxWidth,15);
    if(!msg.equals("")) {
      boxWidth = Math.max(boxWidth, 20 + fm.stringWidth(msg));
    }
    if(boxWidth == 0) {
      return;
    }
    g.fillRect(0, getBounds().height - 15, boxWidth, 15);
    g.setColor(Color.black);
    g.drawString(msg, 8, getBounds().height - 2);
    g.drawRect(0, getBounds().height - 15, boxWidth, 15);
  }

  /**
   * Method this_mouseReleased
   *
   * @param e
   */
  void this_mouseReleased(MouseEvent e) {
    boxWidth = 0;  // cleanup the yellow message box.
    int xpt = e.getX();
    if(xpt < 1) {
      xpt = 1;
    } else if(xpt > iwidth - 2) {
      xpt = iwidth - 2;
    }
    int ypt = e.getY();
    if(ypt < 1) {
      ypt = 1;
    } else if(ypt > iheight - 2) {
      ypt = iheight - 2;
    }
    double x = xFromPix(xpt);
    double y = yFromPix(ypt);
    if(dragShape != null) {
      if(dragV) {
        dragShape.setVX(x - dragShape.getX());
        dragShape.setVY(y - dragShape.getY());
      } else {
        //dragShape.setX(x);
        //dragShape.setY(y);
        if(dampOnMousePressed) {
          dragShape.setVX(0);
          dragShape.setVY(0);
        }
      }
      dragShape.updateMySlaves();
      if(dragShape instanceof Pole) {
        setFields();
      } else {  // must be a test charge
        dragShape.clearTrail();
        osiInvalid = true;
      }
      if(!testForCollision()) {
        collision = false;
        message   = null;
      }
      repaint();
    }
    if(isDrag) {  // we had a mouse down so clean up
      isDrag = false;
      if(fieldSolvers.size() == 0) {
        repaint();
      }
    }
    dragShape = null;
    this_mouseMoved(e);
    if(sketchMode && (trailThing != null)) {
      contour.attachDataSet(trailThing.dataset);
      contour.xaxis.attachDataSet(trailThing.dataset);
      contour.yaxis.attachDataSet(trailThing.dataset);
      synchronized(delayLock) {
        newData = true;
        delayLockNotify();
      }
    }
  }

	void this_mouseClicked(MouseEvent e) {
		double x = xFromPix(e.getX());
		double y = yFromPix(e.getY());
		if (showFieldLineOnClick || (e.getClickCount() == 2 && showFieldLineOnDoubleClick)) {
			new FieldSolver(x, y, true);
			new FieldSolver(x, y, false);
			return;
		}

		if (showEquipotentialOnClick || ((e.getClickCount() == 2) && showEquipotentialOnDoubleClick)) {
			contour.calculateCurve(getPotential(x, y));
			osiInvalid = true;
			repaint();
			return;
		}
	}

  /**
   * Method this_mousePressed
   *
   * @param e
   */
  void this_mousePressed(MouseEvent e) {
    double x = xFromPix(e.getX());
    double y = yFromPix(e.getY());
    /*  replace with mouseClicked event handler
    if((e.isShiftDown() && showFieldLines) || showFieldLineOnClick
        || ((e.getClickCount() == 2) && showFieldLineOnDoubleClick)) {
      //calcFieldPoles();
      new FieldSolver(x, y, true);
      new FieldSolver(x, y, false);
      return;
    }
    if(showEquipotentialOnClick || ((e.getClickCount() == 2) && showEquipotentialOnDoubleClick)) {
      contour.calculateCurve(getPotential(x, y));
      osiInvalid = true;
      repaint();
      return;
    }*/
    Thing shape;
    isDrag    = true;
    dragShape = null;
    if(e.isControlDown()) {
      dragV = true;
    } else {
      dragV = false;
    }
    Graphics g = getGraphics();
    int      n = drawThings.size();
    for(int i = 0; i < n; i++) {
      shape = (Thing) drawThings.elementAt(i);
      if(!shape.noDrag && shape.isInsideThing(e.getX(), e.getY())) {
        dragShape = shape;
        //System.out.println("is inside");
      }
    }
    osiInvalid = true;
    if(dragShape != null) {
      if(dragShape instanceof Pole) {
        stopFieldThreads();
      }
      // paint the shape for the first time.
      g.setXORMode(getBackground());
      dragShape.paintHighlight(g);
      if(dragV) {
        dragShape.setVX(x - dragShape.getX());
        dragShape.setVY(y - dragShape.getY());
      } else {
        //dragShape.setX(x);
        //dragShape.setY(y);
        if(dampOnMousePressed) {
          dragShape.setVX(0);
          dragShape.setVY(0);
        }
      }
      dragShape.paint(g);
      //if(!(dragShape instanceof Charge)) dragShape.paintMySlaves( g);
      if((dragShape instanceof Charge) && dragShape.isShowFVector()) {
        paintForceOnCharge(g, (Charge) dragShape);
      }
      g.setPaintMode();
    }
    mouseX = x;
    mouseY = y;
    g.dispose();
    paintCoordinates(x, y);
    if(sketchMode && (trailThing != null)) {
      trailThing.clearTrail();
      parentSApplet.clearData(trailThing.hashCode());
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      this_mouseDragged(e);
    }
  }

  /**
   * Method this_mouseMoved
   *
   * @param e
   */
  void this_mouseMoved(MouseEvent e) {
    int xpt = e.getX();
    int ypt = e.getY();
    if(isInsideDragableThing(xpt, ypt)) {
      setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    } else if(this.sketchMode) {
      setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
      // else if(this.sketchMode)setCursor(this.sketchCursor);
    } else {
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }
  }

  /**
   * Method charge_Dragged
   *
   * @param e
   * @param calcVectors
   */
  void charge_Dragged(MouseEvent e, boolean calcVectors) {
    int xpt = e.getX();
    if(xpt < 1) {
      xpt = 1;
    } else if(xpt > iwidth - 2) {
      xpt = iwidth - 2;
    }
    double x = xFromPix(xpt);
    double y;
    int    ypt = e.getY();
    if(ypt < 1) {
      ypt = 1;
    } else if(ypt > iheight - 2) {
      ypt = iheight - 2;
    }
    y = yFromPix(ypt);
    mouseX = x;
    mouseY = y;
    if(isDrag) {
      Graphics g = getGraphics();
      // active drawing
      if(dragShape != null) {
        if(dragV) {
          dragShape.setVX(x - dragShape.getX());
          dragShape.setVY(y - dragShape.getY());
        } else {
          dragShape.setXY(x, y);
          if(dampOnMousePressed) {
            dragShape.setVX(0);
            dragShape.setVY(0);
          }
          dragShape.setAcceleration();
        }
        if(parentSApplet.isClockRunning()) {
          paintCoordinates(x, y);
          return;        // no need to repaint if the applet is running.
        }
        //parentSApplet.updateDataConnection(dragShape.hashCode());  // must update all connections since potential can be passed.
        parentSApplet.updateDataConnections();
        if(osi == null) {
          osi  = createImage(iwidth, iheight);
          osi2 = createImage(iwidth, iheight);
          // System.out.println("I Created 2");
        }
        osiInvalid = false;
        Graphics  osg = osi.getGraphics();
        Rectangle r   = new Rectangle(iwidth, iheight);
        osg.setColor(Color.white);
        osg.fillRect(0, 0, iwidth, iheight);
        if(calcVectors) {
          setDirectionVectors();
          field.paint(osg, r, skip);
        } else {
          //if(showContours)contour.paint(osg,r); //always paint in orde to get the series and othe graphs.
          boolean tmpLabels = contour.getDrawLabels();
          contour.setDrawLabels(false);
          contour.paint(osg, r);
          contour.setDrawLabels(tmpLabels);
          if(showFieldVectors) {
            field.paint(osg, r, skip);
          }
        }
        for(Enumeration en = drawThings.elements(); en.hasMoreElements(); ) {
          Thing t = (Thing) en.nextElement();
          if(t instanceof TextThing) {
            t.setVarsFromMaster();
          }
        }
        dragShape.updateMySlaves();
        paintThings(osg);
        osg.dispose();
        g.drawImage(osi, 0, 0, this);
        Font oldFont = g.getFont();
        g.setFont(f);  // this font is used for messages and captions.
        if(showTime) {
          String tStr = new Format("%7.4g").form(time);
          g.setColor(Color.black);
          if(iwidth > 150) {
            g.drawString(parentSApplet.label_time + tStr, 10, 15);
          } else {
            g.drawString(parentSApplet.label_time + tStr, 10, iheight - 40);
          }
        }
        if (caption != null) {
          g.setColor(Color.black);
          FontMetrics fm = g.getFontMetrics(f);
          g.drawString(caption, (iwidth - fm.stringWidth(caption)) / 2, 25);
        }
        g.setFont(oldFont);

      }
      //g.setPaintMode();
      mouseX = x;
      mouseY = y;
      paintCoordinates(g, x, y);
      g.setColor(Color.black);
      g.drawRect(0, 0, iwidth - 1, iheight - 1);
      g.dispose();
    }
  }

  /**
   * Method this_mouseDragged
   *
   * @param e
   */
  void this_mouseDragged(MouseEvent e) {
    if(dragShape instanceof Pole) {
      stopFieldThreads();
    }
    if((dragShape instanceof Pole) && (showFieldVectors)) {
      charge_Dragged(e, true);
      return;
    }
    // if(dragShape instanceof Pole && !showContours && !showFieldLines){
    if(dragShape instanceof Charge) {  // dragging a charge but not a pole so we do not need to recalculate fields.
      charge_Dragged(e, false);
      return;
    }
    // we are not dragging a charge.
    int xpt = e.getX();
    if(xpt < 1) {
      xpt = 1;
    } else if(xpt > iwidth - 2) {
      xpt = iwidth - 2;
    }
    double x = xFromPix(xpt);
    double y;
    int    ypt = e.getY();
    if(ypt < 1) {
      ypt = 1;
    } else if(ypt > iheight - 2) {
      ypt = iheight - 2;
    }
    y      = yFromPix(ypt);
    mouseX = x;
    mouseY = y;
    if(isDrag && (dragShape != null)) {
      if(dragV) {
        dragShape.setVX(x - dragShape.getX());
        dragShape.setVY(y - dragShape.getY());
      } else {
        dragShape.setXY(x, y);
        if(dampOnMousePressed) {
          dragShape.setVX(0);
          dragShape.setVY(0);
        }
        dragShape.setAcceleration();
      }
      //if(parentSApplet!=null) parentSApplet.updateDataConnections();   // register after we check to see if thread is running.
      if(parentSApplet.isClockRunning()) {
        paintCoordinates(x, y);
        return;                                             // no need to repaint if the applet is running.
      }
      //parentSApplet.updateDataConnection(dragShape.hashCode());
      if(parentSApplet != null) {
        parentSApplet.updateDataConnections();
      }
      for(Enumeration en = drawThings.elements(); en.hasMoreElements(); ) {
        Thing t = (Thing) en.nextElement();
        if(t instanceof TextThing) {
          t.setVarsFromMaster();
        }
      }
      dragShape.updateMySlaves();
      if(autoRefresh) {
        paint();
      }
      //if(autoRefresh) synchronized(delayLock){newData=true; delayLockNotify(); }
    } else {
      paintCoordinates(x, y);
      if(sketchMode && (trailThing != null)) {
        x = Math.min(x, contour.xaxis.maximum);
        x = Math.max(x, contour.xaxis.minimum);
        y = Math.min(y, contour.yaxis.maximum);
        y = Math.max(y, contour.yaxis.minimum);
        trailThing.incTrail(x, y);
        Graphics g = getGraphics();
        g.drawImage(osi2, 0, 0, this);
        paintCoordinates(g, x, y);
        if(sketchImage != null) {
          g.drawImage(sketchImage, xpt, ypt - sketchImage.getHeight(this), this);
        }
        trailThing.paint(g);
        g.dispose();
        parentSApplet.updateDataConnection(trailThing.hashCode());
      }
    }
  }

  /*
   * void this_mouseDragged(MouseEvent e) {
   *   if(dragShape instanceof Pole) stopFieldThreads();
   *   if((dragShape instanceof Pole)&&(showFieldVectors)){
   *       charge_Dragged(e,true);
   *       return;
   *   }
   *
   *  // if(dragShape instanceof Pole && !showContours && !showFieldLines){
   *   if(dragShape instanceof Charge ){  // dragging a charge but not a pole so we do not need to recalculate fields.
   *       charge_Dragged(e,false);
   *       return;
   *   }
   *   // we are not dragging a charge.
   *   int xpt=e.getX();
   *   if(xpt<1) xpt=1;
   *     else if(xpt>iwidth-2) xpt=iwidth-2;
   *   double x=xFromPix(xpt);
   *   double y;
   *   int ypt=e.getY();
   *   if(ypt<1) ypt=1;
   *     else if(ypt>iheight-2) ypt=iheight-2;
   *   y=yFromPix(ypt);
   *   if(isDrag){
   *      Graphics g=getGraphics();
   *      // active drawing
   *      synchronized(osi){
   *        if(dragShape!=null){
   *          if(dragV){
   *             dragShape.setVX(x-dragShape.getX());
   *             dragShape.setVY(y-dragShape.getY());
   *          }else{
   *             dragShape.setXY(x,y);
   *             if(dampOnMousePressed){
   *               dragShape.setVX(0);
   *               dragShape.setVY(0);
   *             }
   *          }
   *          //if(parentSApplet!=null) parentSApplet.updateDataConnections();   // register after we check to see if thread is running.
   *          if(parentSApplet.isClockRunning()){
   *               paintCoordinates(x,y);
   *               return;  // no need to repaint if the applet is running.
   *          }
   *          //parentSApplet.updateDataConnection(dragShape.hashCode());
   *          parentSApplet.updateDataConnections();
   *          if(osi==null){
   *               osi=createImage(iwidth,iheight);
   *               osi2=createImage(iwidth,iheight);
   *              // System.out.println("I Created 2");
   *          }
   *          osiInvalid=false;
   *          Graphics osg = osi.getGraphics();
   *          Rectangle r= new Rectangle(iwidth,iheight);
   *          osg.setColor(Color.white);
   *          osg.fillRect(0,0,iwidth,iheight);
   *          if(showContours)contour.paint(osg,r);
   *          if(showFieldVectors) field.paint(osg,r);
   *          for( Enumeration en=drawThings.elements(); en.hasMoreElements();){
   *            Thing t= (Thing) en.nextElement();
   *            if(t instanceof TextThing){
   *                t.setVarsFromMaster();
   *            }
   *          }
   *          dragShape.updateMySlaves();
   *          paintThings(osg);
   *          osg.dispose();
   *          g.drawImage(osi,0,0,this);
   *        }
   *      }
   *      //g.setPaintMode();
   *      mouseX=x;
   *      mouseY=y;
   *      paintCoordinates(g,x,y);
   *      g.setColor(Color.black);
   *      g.drawRect(0,0,iwidth-1,iheight-1);
   *      g.dispose();
   *   }
   * }
   */
  /*
   * void old_this_mouseDragged(MouseEvent e) {
   *   if(dragShape instanceof Pole) stopFieldThreads();
   *   if((dragShape instanceof Pole)&&(showFieldVectors)){
   *       charge_Dragged(e,true);
   *       return;
   *   }
   *
   *  // if(dragShape instanceof Pole && !showContours && !showFieldLines){
   *   if(dragShape instanceof Charge ){  // dragging a charge but not a pole so we do not need to recalculate fields.
   *       charge_Dragged(e,false);
   *       return;
   *   }
   *   // we are not dragging a charge.
   *   int xpt=e.getX();
   *   if(xpt<1) xpt=1;
   *     else if(xpt>iwidth-2) xpt=iwidth-2;
   *   double x=xFromPix(xpt);
   *   double y;
   *   int ypt=e.getY();
   *   if(ypt<1) ypt=1;
   *     else if(ypt>iheight-2) ypt=iheight-2;
   *   y=yFromPix(ypt);
   *   if(isDrag){
   *      Graphics g=getGraphics();
   *      // active drawing
   *      if(dragShape!=null){
   *          //System.out.println("Dragged shape");
   *          // erase the old drawing if we are not showing an active vector field
   *          g.setXORMode(getBackground());
   *          dragShape.paint(g);
   *          //dragShape.paintMySlaves( g);
   *          if(dragV){
   *             dragShape.setVX(x-dragShape.getX());
   *             dragShape.setVY(y-dragShape.getY());
   *          }else{
   *             dragShape.setXY(x,y);
   *             if(dampOnMousePressed){
   *               dragShape.setVX(0);
   *               dragShape.setVY(0);
   *             }
   *          }
   *          dragShape.updateMySlaves();
   *          dragShape.paint(g);
   *          //dragShape.paintMySlaves( g);
   *          g.setPaintMode();
   *      }
   *      mouseX=x;
   *      mouseY=y;
   *      paintCoordinates(g,x,y);
   *      g.dispose();
   *      if (dragShape==null) return;
   *      if(parentSApplet!=null) parentSApplet.updateDataConnection(dragShape.hashCode());
   *   }
   * }
   */

  /**
   * Class FieldSolver
   *
   *
   * @author
   * @version %I%, %G%
   */
  class FieldSolver implements Runnable, SDifferentiable {  // inner class to solve and plot the Electric field.

    Color            fieldColor  = new Color(128, 128, 255);
    SRK45            odeSolver   = new SRK45();
    Thread           fieldThread = null;
    double[]         fieldLine   = new double[2];
    boolean          plus        = true;
    boolean          keepRunning = true;
    boolean          interrupted = false;
    DataSet          data;
    int              np     = 0;
    int              maxPts = 300;
    double[]         points = new double[2 * maxPts];  // data points
    int              scale  = 1;
    Charge           origin = null;
    private double[] dydx   = new double[2];

    /**
     * Constructor FieldSolver
     *
     * @param x
     * @param y
     * @param p
     * @param or
     */
    FieldSolver(double x, double y, boolean p, Charge or) {
      this(x, y, p);
      origin = or;
    }

    /**
     * Constructor FieldSolver
     *
     * @param x
     * @param y
     * @param p
     */
    FieldSolver(double x, double y, boolean p) {
      calculatingFieldLines = true;
      if(parentSApplet.clock.isRunning()) {
        parentSApplet.clock.stopClock();
      }
      this.plus      = p;
      fieldLine[0]   = x;
      fieldLine[1]   = y;
      points[np]     = x;
      points[np + 1] = y;
      np             = np + 2;
      odeSolver.setDifferentials(this);
      odeSolver.setTol(tolerance);
      if(fieldThread == null) {
        fieldThread = new Thread(this);
        fieldState = FIELD_STATE_INIT;
        fieldThread.start();
      }
      message = parentSApplet.label_calculating;
      Graphics g = getGraphics();
      OdeCanvas.this.paintMessage(g, message);
      g.dispose();
      fieldSolvers.addElement(this);
    }

    /**
     * Method getNumEqu
     *
     *
     * @return
     */
    public int getNumEqu() {
      return 2;
    }

    /**
     * Method rate
     *
     * @param x
     *
     * @return
     */
    public double[] rate(double[] x) {
      double fx = -dudx(x[0], x[1]) + getPoleFx(x[0], x[1], null);
      double fy = -dudy(x[0], x[1]) + getPoleFy(x[0], x[1], null);
      double f  = Math.sqrt(fx * fx + fy * fy);
      if(!plus) {
        fy = -fy;
        fx = -fx;
      }
      if(f <= 0) {
        dydx[0]     = 0;
        dydx[1]     = 0;
        keepRunning = false;  // no field so we might as well stop.
      } else {
        dydx[0] = scale * fx / f;
        dydx[1] = scale * fy / f;
        //  dydx[0]=fx/f;
        //  dydx[1]=fy/f;
      }
      return dydx;
    }

    /**
     * Method stepField
     *
     */
    void stepField() {
      /*
       *   if(osi==null){
       *       System.out.println("OSI=null");
       *       keepRunning=false;
       *       return;
       *   }
       */
      double ds = (xmax - xmin) / 20.0;  //step size
      parentSApplet.lock.getBusyFlag();
      int x0 = pixFromX(fieldLine[0]);
      int y0 = pixFromY(fieldLine[1]);
      odeSolver.setH(ds);
      odeSolver.stepRK45(fieldLine);
      int x1 = pixFromX(fieldLine[0]);
      int y1 = pixFromY(fieldLine[1]);
      parentSApplet.lock.freeBusyFlag();
      if(np < maxPts * 2) {
        points[np]     = fieldLine[0];
        points[np + 1] = fieldLine[1];
        np             = np + 2;
      }
      // speed it up if we are outside the panel.
      //scale=1+(int)Math.round(Math.abs(x1*1.0/iwidth)) + (int)Math.round(Math.abs(y1*1.0/iheight));
      // scale=1;
      if((Math.abs(iwidth / 2 - x1) > (iwidth / 1.8)) || (Math.abs(iheight / 2 - y1) > (iheight / 1.8))) {
        scale = 20;
      } else {
        scale = 1;
      }
      parentSApplet.lock.getBusyFlag();
      Graphics g = getGraphics();
      //g.setColor(fieldColor);
      g.setColor(Color.red);
      g.drawLine(x0, y0, x1, y1);
      //g.setColor(Color.black);
      g.dispose();
      if(osi != null) {
        g = osi.getGraphics();
        //g.setColor(fieldColor);
        g.setColor(Color.red);
        g.drawLine(x0, y0, x1, y1);
        g.dispose();
      }
      if(osi2 != null) {
        g = osi2.getGraphics();
        //g.setColor(fieldColor);
        g.setColor(Color.red);
        g.drawLine(x0, y0, x1, y1);
        g.dispose();
      }
      parentSApplet.lock.freeBusyFlag();
      if((allPositive || allNegative) && ((x0 < -1) || (y0 < -1) || (x0 > iwidth) || (y0 > iheight))) {
        keepRunning = false;
      }
      //if(showFieldLines && parser!=null &&( x1<0 || y1<0 || x1>iwidth || y1>iheight) )   keepRunning=false;
    }

    /**
     * Method endOfFieldLine
     *
     *
     * @return
     */
    boolean endOfFieldLine() {
      int n = fieldPoles.size();
      for(int i = 0; i < n; i++) {
        Charge c = (Charge) fieldPoles.elementAt(i);
        if(c != origin) {
          int s  = c.getSize();
          int x0 = pixFromX(c.getX());
          int y0 = pixFromY(c.getY());
          int x1 = pixFromX(fieldLine[0]);
          int y1 = pixFromY(fieldLine[1]);
          int r  = (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1);
          if(r < s * s) {
            return true;
          }
        }
      }
      return false;
    }

    /**
     * Method interrupt
     *
     */
    void interrupt() {
      parentSApplet.lock.getBusyFlag();
      interrupted = true;
      parentSApplet.lock.freeBusyFlag();
      Thread temp=fieldThread;
      if(temp!=null){
        temp.interrupt();
      }
    }

    final private static int FIELD_STATE_INIT = 0;
    final private static int FIELD_STATE_SLEEP1 = 1;
    final private static int FIELD_STATE_STEPFIELD = 2;
    final private static int FIELD_STATE_SLEEP2 = 3;
    final private static int FIELD_STATE_COUNTING = 4;
    final private static int FIELD_STATE_DONE = 5;

    protected int fieldState = FIELD_STATE_INIT;
    private int count = 0;
	private Timer fieldTimer;

		/**
		 * Method run
		 *
		 */
		public void run() {
			while (keepRunning || fieldState == FIELD_STATE_INIT) {
				try {
					switch (fieldState) {
					case FIELD_STATE_INIT:
						count = 0;
						keepRunning = true;
						fieldState = FIELD_STATE_SLEEP1;
						continue;
					case FIELD_STATE_SLEEP1:
						if (osi == null) {
							if (isJS) {
								fieldTimer = new Timer(50, new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										run();
									}
									
								});
								fieldTimer.setRepeats(false);
								fieldTimer.start();
								return;
							} else {
								Thread.sleep(50);
							}
							continue;
						}
						fieldState = FIELD_STATE_STEPFIELD;
						continue;
					case FIELD_STATE_STEPFIELD:
						stepField();
						fieldState = FIELD_STATE_SLEEP2;
						continue;
					case FIELD_STATE_SLEEP2:
							if (isJS) {
								fieldTimer = new Timer(20, new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										fieldState = FIELD_STATE_COUNTING;
										run();
									}
									
								});
								fieldTimer.setRepeats(false);
								fieldTimer.start();
								return;
							} else{
								Thread.sleep(20);
							}
							fieldState = FIELD_STATE_COUNTING;
							continue;
					case FIELD_STATE_COUNTING:
						count++;
						if ((count >= maxPts) || endOfFieldLine() || interrupted) {
							keepRunning = false;
							fieldState = FIELD_STATE_DONE;
							continue;
						}
						fieldState = FIELD_STATE_SLEEP1;
						continue;
					}
				} catch (InterruptedException e) {
					return;
				}
			}
			// FIELD_STATE_DONE:
			fieldThread = null;
			fieldSolvers.removeElement(this);
			if (interrupted) { // all field solvever have been told to stop.
				points = null; // help the system to garbage collect
				return;
			}
			parentSApplet.lock.getBusyFlag();
			data = contour.addDataSet(points, count); // this constructs
														// dataset
			data.linecolor = fieldColor;
			fieldLines.addElement(data);
			parentSApplet.lock.freeBusyFlag();
			int index = 0;
			if (count > 150) {
				index = 10;
			} else if (count > 8) {
				index = count / 2;
			}
			if (index > 0) {
				double x = points[index * 2];
				double y = points[index * 2 + 1];
				double fx = -dudx(x, y) + getPoleFx(x, y, null);
				double fy = -dudy(x, y) + getPoleFy(x, y, null);
				double f = Math.sqrt(fx * fx + fy * fy);
				if ((x > xmin) && (x < xmax) && (y > ymin) && (y < ymax)) {
					arrowHeads.addElement(new ArrowHead(OdeCanvas.this, x, y, fx / f, fy / f, fieldColor));
				}
			}
			// System.out.println("registered:"+ count+
			// "size:"+fieldSolvers.size());
			// points=null; // help the system to garbage collect
			// osiInvalid=true;
			// repaint();
			if (fieldSolvers.size() == 0) {
				calculatingFieldLines = false;
				message = null;
				osiInvalid = true; // repaint everything if this is the last
									// field solver
				repaint();
			}
		}
		}
}

/**
 * Class OdeCanvas_mouseAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class OdeCanvas_mouseAdapter extends java.awt.event.MouseAdapter {

  OdeCanvas adaptee;

  /**
   * Constructor OdeCanvas_mouseAdapter
   *
   * @param adaptee
   */
  OdeCanvas_mouseAdapter(OdeCanvas adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseEntered(MouseEvent e) {
    adaptee.repaint();
  }

  /**
   * Method mouseReleased
   *
   * @param e
   */
  public void mouseReleased(MouseEvent e) {
    adaptee.this_mouseReleased(e);
  }

  /**
   * Method mousePressed
   *
   * @param e
   */
  public void mousePressed(MouseEvent e) {
    adaptee.this_mousePressed(e);
  }
}

/**
 * Class OdeCanvas_mouseMotionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class OdeCanvas_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {

  OdeCanvas adaptee;

  /**
   * Constructor OdeCanvas_mouseMotionAdapter
   *
   * @param adaptee
   */
  OdeCanvas_mouseMotionAdapter(OdeCanvas adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method mouseDragged
   *
   * @param e
   */
  public void mouseDragged(MouseEvent e) {
    adaptee.this_mouseDragged(e);
  }

  /**
   * Method mouseMoved
   *
   * @param e
   */
  public void mouseMoved(MouseEvent e) {
    adaptee.this_mouseMoved(e);
  }
}
