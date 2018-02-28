package lorentz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

//import java.awt.*;
import a2s.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;

/**
 * Lorentz models electric and magnetic fields from a moving charge.
 */
public class Lorentz extends SApplet implements SStepable {

  private String       button_start  = "Start";
  private String       button_stop   = "Stop";
  private String       label_efield  = "E Field";
  private String       label_bfield  = "B Field";
  private String       label_both    = "Both";
  String               label_xyfield = "XY Field";
  String               label_yzfield = "YZ Field";
  private String       label_speed   = "v =";
  private VarScrollBar speedBar;
  private XYCanvas     cXY;
  private YZCanvas     cYZ;
  private Checkbox     electric;
  private Checkbox     magnetic;
  private Checkbox     both;
  private SizedButton  runBtn;
  private double       m_speed      = 0.5;
  private boolean      showControls = true;

  /**
   * @y.exclude
   */
  public Lorentz(){

  }

  /**
   * User interface actions.  Do not script.
   *
   * @param evt
   * @param arg
   *
   * @return true if successful
   * @y.exclude
   */
  public boolean action(Event evt, Object arg) {
    if(evt.target.equals(electric)) {
      cXY.setShowTypes(true, false);
      cYZ.setShowTypes(true, false);
    } else if(evt.target.equals(magnetic)) {
      cXY.setShowTypes(false, true);
      cYZ.setShowTypes(false, true);
    } else if(evt.target.equals(both)) {
      cXY.setShowTypes(true, true);
      cYZ.setShowTypes(true, true);
    } else if(evt.target.equals(runBtn)) {
      if(!clock.isRunning()) {
        runBtn.setLabel(button_stop);
        m_speed          = speedBar.getValue();
        cXY.newReference = true;
        cXY.setBeta(m_speed);
        cYZ.newReference = true;
        cYZ.setBeta(m_speed);
        clock.startClock();
      } else {
        clock.stopClock();
        runBtn.setLabel(button_start);
        cXY.setPosition(0);
        cYZ.setPosition(0);
        cXY.repaint();
        cYZ.repaint();
      }
    } else {
      return false;
    }
    return true;
  }

  /**
   * Starts the animation.
   *
   */
  public void foward() {
    startAnimate();
  }

  /**
   * Starts the animation.
   *
   * Same as forward();
   */
  public void startAnimate() {
    clock.startClock();
    runBtn.setLabel(button_stop);
    m_speed          = speedBar.getValue();
    cXY.newReference = true;
    cXY.setBeta(m_speed);
    cYZ.newReference = true;
    cYZ.setBeta(m_speed);
  }

  /**
   * Stops the animation.
   */
  public void pause() {
    stopAnimate();
  }

  /**
   * Stops the animation.
   *
   * Same as pause.
   */
  public void stopAnimate() {
    clock.stopClock();
    runBtn.setLabel(button_start);
    cXY.setPosition(0);
    cYZ.setPosition(0);
    cXY.repaint();
    cYZ.repaint();
  }

  /**
   * Sets the speed.
   *
   * @param v the speed
   */
  public void setSpeed(double v) {
    boolean shouldRun = clock.isRunning();
    clock.stopClock();
    if(v!=m_speed) {
      m_speed = v;
      if((cXY==null)||(cYZ==null)) {
        return;
      }
      speedBar.setValue(m_speed);
      cXY.newReference = true;
      cXY.setBeta(m_speed);
      cYZ.newReference = true;
      cYZ.setBeta(m_speed);
    }
    if(shouldRun) {
      clock.startClock();
    }
  }

  /**
   * Handles slider events.  Do not script.
   *
   * @param evt
   *
   * @return true if successful
   * @y.exclude
   */
  public boolean handleEvent(Event evt) {
    if(evt.target.equals(speedBar)) {
      if((cXY==null)||(cYZ==null)) {
        return true;
      }
      if(m_speed==speedBar.getValue()) {
        return true;
      }
      m_speed          = speedBar.getValue();
      cXY.newReference = true;
      cXY.setBeta(m_speed);
      cYZ.newReference = true;
      cYZ.setBeta(m_speed);
      return true;
    }
    return super.handleEvent(evt);
  }

  /**
   * Method getAppletInfo
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Name: Lorentz Ver 1.2\r\n"+"Author: Wolfgang Christian\r\n"+"Email: wochristian@davidson.edu\r\n";
  }

  /**
   * Method getParameterInfo
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String[][] info = {
      {"Speed", "double", "Reference frame"},
    };
    return info;
  }

  protected void setResources() {
    button_start  = localProperties.getProperty("button.start", button_start);
    button_stop   = localProperties.getProperty("button.stop", button_stop);
    label_efield  = localProperties.getProperty("label.efield", label_efield);
    label_bfield  = localProperties.getProperty("label.bfield", label_bfield);
    label_both    = localProperties.getProperty("label.both", label_both);
    label_xyfield = localProperties.getProperty("label.xyfield", label_xyfield);
    label_yzfield = localProperties.getProperty("label.yzfield", label_yzfield);
    label_speed   = localProperties.getProperty("label.speed", label_speed);
  }

  /**
   * Initializes the applet when it is first created.  Do not script.
   * @y.exclude
   */
  public void init() {
    initResources(null);
    try {
      showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      m_speed = Double.valueOf(this.getParameter("Speed", "0.5")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    int m_FPS = 10;
    try {
      m_FPS = Integer.parseInt(this.getParameter("FPS", "10"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    /** @j2sNative */{
    	  resize(400, 300);
    }
    setLayout(new BorderLayout());  //layout for the applet
    setBackground(Color.lightGray);
    Panel p1 = new Panel();  //panel for the scroll and checkboxes
    p1.setBackground(Color.lightGray);
    p1.setLayout(new BorderLayout());
    CheckboxGroup g  = new CheckboxGroup();
    Panel         p3 = new Panel();
    p3.setBackground(Color.lightGray);
    p3.setLayout(new FlowLayout(FlowLayout.CENTER));
    p3.add(electric = new Checkbox(label_efield, g, false));
    p3.add(magnetic = new Checkbox(label_bfield, g, false));
    p3.add(both = new Checkbox(label_both, g, true));
    p1.add("North", p3);
    speedBar = new VarScrollBar(label_speed+"  ", m_speed, 0, +0.99);
    p1.add("Center", new EtchedBorder(speedBar));
    p1.add("West", runBtn = new SizedButton(button_start));
    //p1.add("South", new Label("by W. Christian      Ver 1.1 ", Label.RIGHT));
    if(showControls) {
      add("South", p1);
    }
    Panel p4 = new Panel();
    p4.setBackground(Color.lightGray);
    p4.setLayout(new GridLayout(1, 2));
    cXY = new XYCanvas(this);
    cXY.setBackground(Color.white);
    //cXY.resize(200,200);
    p4.add(cXY);
    cYZ = new YZCanvas(this);
    cYZ.setBackground(Color.white);
    //cYZ.resize(200,200);
    p4.add(cYZ);
    add("Center", new Border(p4));
    cXY.setBeta(m_speed);
    cYZ.setBeta(m_speed);
    m_speed = 0.1;
    clock.setFPS(m_FPS);
    clock.addClockListener(this);  // have the clock call the step function in this applet at every tick.
  }

  /**
   * Destroys the applet when the html page is destroyed.  Do not script.
   * @y.exclude
   */
  public void destroy() {
    destroyed = true;
    clock.stopClock();
    super.destroy();
  }

  /**
   * Advances the time by dt. Do not script.
   *
   * @param dt the time increment.
   * @param t
   */
  public void step(double dt, double t) {
    cXY.incPosition();
  }
}

/**
 * Class XYCanvas
 *
 *
 * @author
 * @version %I%, %G%
 */
class XYCanvas extends Canvas {

  Lorentz applet;

  /**
   * Constructor XYCanvas
   *
   * @param app
   */
  XYCanvas(Lorentz app) {
    applet = app;
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
   * Method setPosition
   *
   * @param pos_
   */
  public final void setPosition(double pos_) {
    position = pos_;
  }

  /**
   * Method incPosition
   *
   */
  public synchronized void incPosition() {
    if(beta==0) {
      return;
    }
    position = position+beta;
    if(position>10) {
      position = -10;
    }
    Graphics g = this.getGraphics();
    paint(g);
    g.dispose();
  }

  /**
   * Method setShowTypes
   *
   * @param e
   * @param b
   */
  public void setShowTypes(boolean e, boolean b) {
    showElectric = e;
    showMagnetic = b;
    newReference = true;
    repaint();
  }

  /**
   * Method setBeta
   *
   * @param b
   */
  public synchronized void setBeta(double b) {
    beta = b;
    if(b<0) {
      b = 0;
    }
    if(b>0.99) {
      b = 0.99;
    }
    repaint();
  }

  /**
   * Method paint
   *
   * @param g
   */
  public synchronized void paint(Graphics g) {
    if(applet.destroyed) {
      return;
    }
    Dimension d             = getSize();
    //  Insets in= insets();
    int       client_width  = d.width;   //-in.right-in.left;
    int       client_height = d.height;  //-in.bottom-in.top;
    int       xo, yo;
    int       i, j;                      //counters
    double    Ex, Ey, Bz;
    double    Et;                        //field magnitude
    int       dx, dy;
    double    dr;
    int       rv, gv, bv;
    if(beta<0) {
      beta = 0;
    }
    if(beta>0.99) {
      beta = 0.99;
    }
    double gamma = 1/Math.sqrt(1-beta*beta);
    xo = client_width/2;
    yo = client_height/2;
    if((client_width!=buff_width)||(client_height!=buff_height)) {
      newReference = true;
      buff_width   = client_width;
      buff_height  = client_height;
      buff_image   = createImage(buff_width, buff_height);
    }
    if(newReference) {
      newReference = false;
      Graphics bg = buff_image.getGraphics();
      bg.clearRect(0, 0, buff_width, buff_height);
      // draw B field
      if(showMagnetic) {
        for(i = (xo%10)-10; i<=buff_width+10; i = i+10) {
          for(j = (yo%10)-10; j<=buff_height+10; j = j+10) {
            dx = i-xo;
            dy = j-yo;
            dr = (gamma*gamma*dx*dx+dy*dy);
            if(dr>=1) {
              Bz = (30000*dy*gamma*beta/dr/Math.sqrt(dr));
              boolean negative;
              if(Bz>0) {
                negative = false;
              } else {
                negative = true;
              }
              Bz = Math.sqrt(Math.abs(Bz));
              if(Bz>7) {
                if(negative) {
                  rv = 255;
                } else {
                  rv = 0;
                }
                bv = 0;
                gv = 255;
                bg.setColor(new Color(rv, gv, bv));
                Bz = 7;
              } else {
                if(negative) {
                  rv = 255;
                } else {
                  rv = (int) (255*(1-Bz/7));
                }
                gv = 255;
                bv = (int) (255*(1-Bz/7));
                bg.setColor(new Color(rv, gv, bv));
              }
              bg.fillRect(i-5, j-5, i+5, j+5);
            }
          }
        }
      }
      // draw E field
      bg.setColor(Color.blue);
      if(showElectric) {
        for(i = xo%10; i<buff_width; i = i+10) {
          for(j = yo%10; j<buff_height; j = j+10) {
            dx = i-xo;
            dy = j-yo;
            dr = (gamma*gamma*dx*dx+dy*dy);
            if(dr>=1) {
              Ex = (30000*dx*gamma/dr/Math.sqrt(dr));
              Ey = (30000*dy*gamma/dr/Math.sqrt(dr));
              Et = Math.sqrt(Ex*Ex+Ey*Ey);
              if(Et>7) {
                rv = (int) (255*(1-Math.sqrt(7/Et)));
                gv = 0;
                bv = (int) (255*Math.sqrt(7/Et));
                bg.setColor(new Color(rv, gv, bv));
                Ex = (7*Ex/Et);
                Ey = (7*Ey/Et);
              } else {
                rv = (int) (255*(1-Et/7));
                gv = (int) (255*(1-Et/7));
                bv = 255;
                bg.setColor(new Color(rv, gv, bv));
              }
              bg.drawLine(i, j, (int) (i+Ex), (int) (j+Ey));
            }
          }
        }
      }
      bg.setColor(Color.black);
      //        bg.drawString("XY Field",25,25);
      bg.setColor(Color.red);
      bg.fillOval(xo-5, yo-5, 10, 10);
      bg.dispose();
    }
    if(position>=0) {
      pixPos = (int) ((position-20.0)*client_width/20.0);
      g.drawImage(buff_image, pixPos, 0, this);
      pixPos = (int) (position*client_width/20.0);
      g.drawImage(buff_image, pixPos, 0, this);
    } else {
      pixPos = (int) (position*client_width/20.0);
      g.drawImage(buff_image, pixPos, 0, this);
      pixPos = (int) ((position+20.0)*client_width/20.0);
      g.drawImage(buff_image, pixPos, 0, this);
    }
    paintXYTitle(g);
    //g.drawString(applet.label_xyfield, 25, 25);
    g.drawRect(0, 0, client_width, client_height);
  }

  /**
   * Method paintXYTitle
   *
   * @param g
   */
  void paintXYTitle(Graphics g) {
    int         iwidth  = getSize().width;
    int         iheight = getSize().height;
    FontMetrics fm      = g.getFontMetrics(g.getFont());
    g.setColor(Color.yellow);
    int w = 15+fm.stringWidth(applet.label_xyfield);
    g.fillRect(0, 0, w, 15);
    g.setColor(Color.black);
    g.drawString(applet.label_xyfield, 2, 12);
    g.drawRect(0, 0, w, 15);
  }

  Image           buff_image   = null;
  int             buff_width   = 0;
  int             buff_height  = 0;
  private double  beta         = 0.0;
  boolean         newReference = true;
  double          position     = 0;
  int             pixPos       = 0;
  private boolean showMagnetic = true;
  private boolean showElectric = true;
}

//Drawing methods for YZ

/**
 * Class YZCanvas
 *
 *
 * @author
 * @version %I%, %G%
 */
class YZCanvas extends Canvas {

  Lorentz applet;

  /**
   * Constructor YZCanvas
   *
   * @param app
   */
  YZCanvas(Lorentz app) {
    applet = app;
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
   * Method setBeta
   *
   * @param b
   */
  public synchronized void setBeta(double b) {
    beta = b;
    if(b<0) {
      b = 0;
    }
    if(b>0.99) {
      b = 0.99;
    }
    repaint();
  }

  /**
   * Method setPosition
   *
   * @param pos_
   */
  public final void setPosition(double pos_) {
    //position = pos_;
  }

  /**
   * Method setShowTypes
   *
   * @param e
   * @param b
   */
  public void setShowTypes(boolean e, boolean b) {
    showElectric = e;
    showMagnetic = b;
    newReference = true;
    repaint();
  }

  /**
   * Method paint
   *
   * @param g
   */
  public synchronized void paint(Graphics g) {
    if(applet.destroyed) {
      return;
    }
    Dimension d             = getSize();
    //  Insets in= insets();
    int       client_width  = d.width;   //-in.right-in.left;
    int       client_height = d.height;  //-in.bottom-in.top;
    int       zo, yo;
    int       i, j;                      //counters
    double    Ez, Ey, Bz, By;
    double    Et, Bt;                    //field magnitude
    int       dz, dy;
    double    dr;
    int       rv, gv, bv;
    if(beta<0) {
      beta = 0;
    }
    if(beta>0.99) {
      beta = 0.99;
    }
    double gamma = 1/Math.sqrt(1-beta*beta);
    zo = client_width/2;
    yo = client_height/2;
    if((client_width!=buff_width)||(client_height!=buff_height)) {
      newReference = true;
      buff_width   = client_width;
      buff_height  = client_height;
      buff_image   = createImage(buff_width, buff_height);
    }
    if(newReference||true) {
      newReference = false;
      Graphics bg = buff_image.getGraphics();
      bg.clearRect(0, 0, buff_width, buff_height);
      // draw B field
      bg.setColor(Color.green);
      if(showMagnetic) {
        for(i = zo%10; i<buff_width; i = i+10) {
          for(j = yo%10; j<buff_height; j = j+10) {
            dz = i-zo;
            dy = j-yo;
            dr = (dz*dz+dy*dy);
            if(dr>=1) {
              Bz = (30000*dy*gamma*beta/dr/Math.sqrt(dr));
              By = -(30000*dz*gamma*beta/dr/Math.sqrt(dr));
              Bt = Math.sqrt(Bz*Bz+By*By);
              if(Bt>7) {
                rv = 0;
                gv = (int) (255*(Math.sqrt(7/Bt)));
                bv = 0;
                bg.setColor(new Color(rv, gv, bv));
                Bz = (7*Bz/Bt);
                By = (7*By/Bt);
              } else {
                rv = (int) (255*(1-Bt/7));
                bv = (int) (255*(1-Bt/7));
                gv = 255;
                bg.setColor(new Color(rv, gv, bv));
              }
              bg.drawLine((int) (i-Bz/2), (int) (j-By/2), (int) (i+Bz/2), (int) (j+By/2));
            }
          }
        }
      }
      // draw E field
      bg.setColor(Color.blue);
      if(showElectric) {
        for(i = zo%10; i<buff_width; i = i+10) {
          for(j = yo%10; j<buff_height; j = j+10) {
            dz = i-zo;
            dy = j-yo;
            dr = (dz*dz+dy*dy);
            if(dr>=1) {
              Ez = (30000*dz*gamma/dr/Math.sqrt(dr));
              Ey = (30000*dy*gamma/dr/Math.sqrt(dr));
              Et = Math.sqrt(Ez*Ez+Ey*Ey);
              if(Et>7) {
                rv = (int) (255*(1-Math.sqrt(7/Et)));
                gv = 0;
                bv = (int) (255*Math.sqrt(7/Et));
                bg.setColor(new Color(rv, gv, bv));
                Ez = (7*Ez/Et);
                Ey = (7*Ey/Et);
              } else {
                rv = (int) (255*(1-Et/7));
                gv = (int) (255*(1-Et/7));
                bv = 255;
                bg.setColor(new Color(rv, gv, bv));
              }
              bg.drawLine(i, j, (int) (i+Ez), (int) (j+Ey));
            }
          }
        }
      }
      bg.setColor(Color.black);
      //bg.drawString(applet.label_yzfield, 25, 25);
      paintYZTitle(bg);
      bg.setColor(Color.red);
      bg.fillOval(zo-5, yo-5, 10, 10);
      bg.dispose();
    }
    g.drawImage(buff_image, 0, 0, this);
    g.drawRect(0, 0, client_width, client_height);
  }

  /**
   * Method paintYZTitle
   *
   * @param g
   */
  void paintYZTitle(Graphics g) {
    int         iwidth  = getSize().width;
    int         iheight = getSize().height;
    FontMetrics fm      = g.getFontMetrics(g.getFont());
    g.setColor(Color.yellow);
    int w = 15+fm.stringWidth(applet.label_yzfield);
    g.fillRect(0, 0, w, 15);
    g.setColor(Color.black);
    g.drawString(applet.label_yzfield, 2, 12);
    g.drawRect(0, 0, w, 15);
  }

  Image           buff_image   = null;
  int             buff_width   = 0;
  int             buff_height  = 0;
  private double  beta         = 0.0;
  //private double  position     = 0.0;
  boolean         newReference = true;
  private boolean showMagnetic = true;
  private boolean showElectric = true;
}
