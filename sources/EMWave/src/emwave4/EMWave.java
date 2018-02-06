package emwave4;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.display.*;

//import com.borland.jbcl.layout.*;

/**
 * Class EMWave
 */
public class EMWave extends SApplet implements SStepable {

  Color              color          = Color.black;  // the current drawing color
  float              colorDispenser = 0;
  boolean            showControls   = true;
  //  Graphics f;
  int                wLineDensity   = 2;            // lines/pixel; between 0-1
  int                pixPerUnit     = 10;
  double             rotateZAxis    = 0, rotateYAxis = 0, rotateXAxis = 0;
  int                xPrevious, yPrevious;
  boolean            rotate      = true;
  int                orientation = 1;
  //boolean animate=true;
  int                fps         = 20;
  double             dz;
  //SSlider2 theta = new SSlider2();
  //SSlider2 alpha = new SSlider2();
  //SSlider2 phi = new SSlider2();
  // STimer timer = new STimer();
  ThreeDPanel        threeDView          = new ThreeDPanel();
  //Panel threeDView = new Panel();
  //XYLayout xYLayout1 = new XYLayout();
  BorderLayout       borderLayout1       = new BorderLayout();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  EtchedBorder       sliderPanel         = new EtchedBorder();
  Panel              panel1              = new Panel();
  SSlider            alpha               = new SSlider();
  BorderLayout       borderLayout2       = new BorderLayout();
  Panel              panel2              = new Panel();
  Panel              panel3              = new Panel();
  SSlider            theta               = new SSlider();
  SSlider            phi                 = new SSlider();
  BorderLayout       borderLayout3       = new BorderLayout();
  BorderLayout       borderLayout4       = new BorderLayout();
  SPanel             panel4              = new SPanel();
  Label              label2              = new Label();
  SPanel             panel5              = new SPanel();
  SPanel             panel6              = new SPanel();
  Label              label3              = new Label();
  Label              label1              = new Label();
  BorderLayout       borderLayout5       = new BorderLayout();
  BorderLayout       borderLayout6       = new BorderLayout();
  BorderLayout       borderLayout7       = new BorderLayout();

  /**
   * @y.exclude
   */
  public EMWave() {}

  /**
   * @y.exclude
   */
  public void init() {
    try {
      showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    //try { animate = Boolean.valueOf(this.getParameter("animate", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try {
      orientation = Integer.parseInt(this.getParameter("Orientation", "0"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      fps = Integer.parseInt(this.getParameter("framesPerSec", "20"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      fps = Integer.parseInt(this.getParameter("FPS", "20"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      dz = pixPerUnit*Double.valueOf(this.getParameter("Translation", ".2")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    sliderPanel.setVisible(showControls);
    if(orientation==0) {
      rotateZAxis = Math.PI/4;
      rotateYAxis = 0;
      rotateXAxis = Math.PI/4;
      threeDView.setAngles(rotateZAxis, rotateYAxis, rotateXAxis);
    }
    if(orientation==1) {
      rotateZAxis = Math.PI/4;
      rotateYAxis = Math.PI/4;
      rotateXAxis = 0;
      threeDView.setAngles(rotateZAxis, rotateYAxis, rotateXAxis);
    }
    Figure.setOrigin(this.getSize().width/2, this.getSize().height/2);
    setRGB(0, 0, 0);
    //setRGB(80,70,200);
    //createFilledSquare(-30,20);
    //setRGB(200,1,0);;
    //createAxes(0,0,-30,10);
    //setRGB(149,43,122);
    //createLinear(-30,20,10,0,0);
    //setRGB(80,70,200);
    //createLinear(-20,20,10,0,2*Math.PI/8*1);
    //setRGB(255,255,100);
    //createLinear(-30,20,10,0,2*Math.PI/8*2);
    //setRGB(0,60,0);
    //createLinear(-20,20,10,0,2*Math.PI/8*3);
    //setRGB(156,100,200);
    //createLinear(-20,20,10,0,2*Math.PI/8*4);
    //setRGB(222,223,27);
    //createLinear(-20,20,10,0,2*Math.PI/8*5);
    //setRGB(88,75,144);
    //createLinear(-20,20,10,0,2*Math.PI/8*6);
    //setRGB(149,243,22);
    //createLinear(-20,20,10,0,2*Math.PI/8*7);
    //setLineDensity(.125);
    //setRGB(149,43,122);
    //setRGB(80,70,200);
    //setRGB(255,255,100);
    //createAxes(0,0,-30,10);
    //setRGB(0,60,0);
    //createParsed("z",-5,5,Math.PI/4);
    //createCircularRight(-30,-29.5,10,-.1);
    //setRGB(156,100,200);
    //createCircularRight(-29.8,-10,10,0);
    //setRGB(222,223,27);
    //setPlaneLineDensity(5);
    //createPlane(-10,20,20,0);
    //setRGB(88,75,144);
    //createLinear(-10,30,10,0,Math.PI/2);
    //createParsed("cos(z)",0,5,0);
    //setRGB(149,243,22);
    //createCircularRight(0,20,10,0);
  }

  /**
   *
   * @y.exclude
   *
   * @throws Exception
   */
  public void jbInit() throws Exception {
    this.setBackground(Color.white);
    this.setSize(new Dimension(600, 650));
    this.addComponentListener(new EMWave_this_componentAdapter(this));
    this.setLayout(borderLayout1);
    sliderPanel.setLayout(verticalFlowLayout1);
    sliderPanel.setLocale(java.util.Locale.getDefault());
    sliderPanel.setThickness(1);
    alpha.setDMax(6.28);
    alpha.addAdjustmentListener(new java.awt.event.AdjustmentListener() {

      public void adjustmentValueChanged(AdjustmentEvent e) {
        alpha_adjustmentValueChanged(e);
      }
    });
    alpha.setDValue(rotateYAxis);
    alpha.setDMin(0);
    panel1.setLayout(borderLayout2);
    theta.setDMax(6.28);
    theta.addAdjustmentListener(new java.awt.event.AdjustmentListener() {

      public void adjustmentValueChanged(AdjustmentEvent e) {
        theta_adjustmentValueChanged(e);
      }
    });
    theta.setDValue(rotateZAxis);
    theta.setDMin(0);
    phi.setDMax(6.28);
    phi.addAdjustmentListener(new java.awt.event.AdjustmentListener() {

      public void adjustmentValueChanged(AdjustmentEvent e) {
        phi_adjustmentValueChanged(e);
      }
    });
    phi.setDValue(rotateXAxis);
    phi.setDMin(0);
    panel3.setLayout(borderLayout3);
    panel2.setLayout(borderLayout4);
    label2.setText("theta = ");
    label2.setAlignment(2);
    label3.setText("  phi = ");
    label3.setAlignment(2);
    label1.setAlignment(2);
    label1.setText("alpha = ");
    panel4.setMinimumSize(new Dimension(50, 20));
    panel4.setPreferredSize(new Dimension(50, 20));
    panel4.setLayout(borderLayout5);
    panel5.setMinimumSize(new Dimension(50, 20));
    panel5.setPreferredSize(new Dimension(50, 20));
    panel5.setLayout(borderLayout6);
    panel6.setMinimumSize(new Dimension(50, 20));
    panel6.setPreferredSize(new Dimension(50, 20));
    panel6.setLayout(borderLayout7);
    this.add(threeDView, BorderLayout.CENTER);
    //if (showControls) {this.add(sliderPanel, BorderLayout.SOUTH);}
    this.add(sliderPanel, BorderLayout.SOUTH);
    sliderPanel.add(panel3, null);
    panel3.add(theta, BorderLayout.CENTER);
    panel3.add(panel4, BorderLayout.WEST);
    panel4.add(label2, BorderLayout.CENTER);
    sliderPanel.add(panel2, null);
    panel2.add(phi, BorderLayout.CENTER);
    panel2.add(panel5, BorderLayout.WEST);
    panel5.add(label3, BorderLayout.WEST);
    sliderPanel.add(panel1, null);
    panel1.add(alpha, BorderLayout.CENTER);
    panel1.add(panel6, BorderLayout.WEST);
    panel6.add(label1, BorderLayout.WEST);
    //theta.setCaption("Theta");
    //alpha.setCaption("Alpha");
    //phi.setCaption("Phi    ");
    //timer.setClient(this);
    clock.setFPS(fps);
    clock.setDt(1);
    clock.addClockListener(this);
    threeDView.setBackground(Color.white);
    threeDView.addMouseMotionListener(new EMWave_threeDView_mouseMotionAdapter(this));
    //threeDView.setLayout(xYLayout1);
    //threeDView.setLayout(xYLayout1);
  }

  /**
   * Gets the applet inforamtion.
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Applet Information";
  }

  /**
   * Gets the parameter info.
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String pinfo[][] = {
      {"ShowControls", "boolean", "Show sliders"}, {"Animate", "boolean", "animate figures"},
      {"Orientation", "int", "orientation of figure"}, {"fps", "int", "frames per second"},
      {"Translation", "double", "translation distance of wave"},
    };
    return pinfo;
  }

  /**
   * @y.exclude
   */
  public void start() {
    //setRunningID(this);
    //if (animate)
    threeDView.paint();
    clock.startClock();
  }

  /**
   * Starts the animation.
   *
   */
  public void forward() {
    //if (animate)
    threeDView.paint();
    clock.startClock();
  }

  /**
   * @y.exclude
   */
  public void stop() {
    clock.stopClock();
  }

  /**
   * Pauses the animation.
   *
   */
  public void pause() {
    clock.stopClock();
  }

  /**
   * @y.exclude
   */
  public void destroy() {
    // timer.destroy();
    super.destroy();
  }

  /**
   *  Step to the next animation frame.
   *
   *   @param dt the time step
   *   @param time the current time.
   */
  public void step(double dt, double time) {
    threeDView.translate(dz);
  }

  /**
   * Rotates the figures an amount theta in the x-y plane (z-axis rotation), alpha in the x-z plane (y-axis rotation),
   * and theta in the y-z plane (x-axis rotation).
   *
   * Angles are in radians.
   * When all three angles are zero the positive x-axis points horizontally to the right,
   * the positive y-axis points vertically upwards, and the positive z-axis points out of the screen..
   *
   * @param t
   * @param a
   * @param p
   */
  public void setOrientation(double t, double a, double p) {
    rotateZAxis = t;
    rotateYAxis = a;
    rotateXAxis = p;
    threeDView.setAngles(rotateZAxis, rotateYAxis, rotateXAxis);
  }

  /**
   * Translates the origin from the current origin to {x,y}.
   *
   * The default origin is the center of the window.
   * The origin, x and y, is in pixels.
   *
   * @param x
   * @param y
   */
  public void setOrigin(int x, int y) {
    Figure.setOrigin(x, y);
  }

  /**
   * Sets the line density of all waves to d.
   *
   * The line density is how many pixels there are per line--that is,
   * how many pixels the lines are separated by. The default is 2.
   *
   * @param d
   */
  public void setWaveLineDensity(int d) {
    wLineDensity = d;
    Wave.setLineDensity(d);
  }

  /**
   * Sets the line density of the all Plane figures to d.
   *
   * The line density is how many pixels there are per line--that is,
   * how many pixels the lines are separated by. The default is 2.
   *
   * @param d
   */
  public void setPlaneLineDensity(int d) {
    Plane.setLineDensity(d);
  }

  /**
   * Sets the scale.
   *
   * @param ppu
   */
  public void setPixPerUnit(int ppu) {
    pixPerUnit = ppu;
  }

  /**
   *  Sets the wavelength of all waves to w.
   *  The wavelength is in units.  The default is 20.
   *
   * @param l
   */
  public void setWavelength(double l) {
    Wave.setWavelength(l*pixPerUnit);
  }

  /**
   * Sets the color to {r,g,b} in which all subsequent figures are drawn in.
   * The color is specified by red (r), green (g), and blue (b) components in the range of 0-255.
   *
   * @param r
   * @param g
   * @param b
   */
  public void setRGB(int r, int g, int b) {
    color = new Color(Math.min(255, Math.abs(r)), Math.min(255, Math.abs(g)), Math.min(255, Math.abs(b)));
    Graphics f = threeDView.getGraphics();
    if(f==null) {
      return;
    }
    f.setColor(color);
    f.dispose();
  }

  /**
   * Creates axes at (x,y,z) with length s.
   *
   * @param x
   * @param y
   * @param z
   * @param s
   */
  public void createAxes(double x, double y, double z, double s) {
    //Color c=f.getColor();
    //Axis3D axes=new Axis3D((int)(x*pixPerUnit),(int)(y*pixPerUnit),(int)(z*pixPerUnit),s*pixPerUnit,c);
    Axis3D axes = new Axis3D((int) (x*pixPerUnit), (int) (y*pixPerUnit), (int) (z*pixPerUnit), s*pixPerUnit, color);
    threeDView.addFigure(axes);
    //AxesLabel labels=new AxesLabel((int)x*pixPerUnit,(int)y*pixPerUnit,(int)(z*pixPerUnit),s*pixPerUnit,c);
    AxesLabel labels = new AxesLabel((int) x*pixPerUnit, (int) y*pixPerUnit, (int) (z*pixPerUnit), s*pixPerUnit, color);
    threeDView.addFigure(labels);
    //f.dispose();
  }

  /**
   * Creates an outlined square at {0,0,z} with a side length s.
   * Position and length are in  world units which have a default value of 10 pixels per 1 unit.
   *
   * @param z
   * @param s
   */
  public void createSquare(double z, double s) {
    //Color c=f.getColor();
    //Square base=new Square(z*pixPerUnit,s*pixPerUnit,c);
    Square base = new Square(z*pixPerUnit, s*pixPerUnit, color);
    threeDView.addFigure(base);
    //f.dispose();
  }

  /**
   * Creates a filled square at {0,0,z}with a side length s.
   * Position and length are in units which has a default value of 10 pixels per 1 unit.
   *
   * @param z
   * @param s
   */
  public void createFilledSquare(double z, double s) {
    //Color c=f.getColor();
    // FilledSquare base=new FilledSquare(z*pixPerUnit,s*pixPerUnit,c);
    FilledSquare base = new FilledSquare(z*pixPerUnit, s*pixPerUnit, color);
    threeDView.addFigure(base);
    //f.dispose();
  }

  /**
   * Creates a linearly polarized wave extending from z1 to z2 with amplitude a, phase ph, and polarization pol.
   * Position and amplitude are in units which has a default value of 10 pixels per 1 unit.
   * The wave propagates in the positive z direction, so z1 must be less than z2. That is, z1<z2.
   * The phase, ph, and polarization, pol, are in radians.
   *
   * @param z1
   * @param z2
   * @param a
   * @param ph the phase angle
   * @param p  the polarizaiton
   */
  public void createLinear(double z1, double z2, double a, double ph, double p) {
    //Color c=f.getColor();
    //LinearWave linear=new LinearWave(z1*pixPerUnit,z2*pixPerUnit,a*pixPerUnit,ph,p,c);
    LinearWave linear = new LinearWave(z1*pixPerUnit, z2*pixPerUnit, a*pixPerUnit, ph, p, color);
    threeDView.addFigure(linear);
    //f.dispose();
  }

  /**
   * Creates a counterclockwise rotating circular wave extending from z1 to z2, with amplitude a and phase ph.
   *Position and amplitude are in units which has a default value of 10 pixels per 1 unit.
   * The wave propagates in the positive z direction, so z1 must be less than z2. That is, z1<z2.
   * The phase, ph, is in radians.
   *
   * @param z1
   * @param z2
   * @param a
   * @param ph
   */
  public void createCircularRight(double z1, double z2, double a, double ph) {
    //Color c=f.getColor();
    //CircularWaveRight circular=new CircularWaveRight(z1*pixPerUnit,z2*pixPerUnit,a*pixPerUnit,ph,c);
    CircularWaveRight circular = new CircularWaveRight(z1*pixPerUnit, z2*pixPerUnit, a*pixPerUnit, ph, color);
    threeDView.addFigure(circular);
    //f.dispose();
  }

  /**
   * Creates a clockwise rotating circular wave extending from z1 to z2, with amplitude a, and phase ph.
   * Position and amplitude are in units which has a default value of 10 pixels per 1 unit.
   * The wave propagates in the positive z direction, so z1 must be less than z2. That is, z1<z2.
   * The phase, ph, is in radians.
   *
   * @param z1
   * @param z2
   * @param a
   * @param ph
   */
  public void createCircularLeft(double z1, double z2, double a, double ph) {
    //Color c=f.getColor();
    //CircularWaveLeft circular=new CircularWaveLeft(z1*pixPerUnit,z2*pixPerUnit,a*pixPerUnit,ph,c);
    CircularWaveLeft circular = new CircularWaveLeft(z1*pixPerUnit, z2*pixPerUnit, a*pixPerUnit, ph, color);
    threeDView.addFigure(circular);
    //f.dispose();
  }

  /**
   *  Creates a plane composed of lines at {0,0,z}with length l, width w, and polarization pol.
   *  Position and length are in world units which has a default value of 10 pixels per 1 unit.
   *
   * @param z1
   * @param length
   * @param width
   * @param p
   */
  public void createPlane(double z1, double length, double width, double p) {
    //Color c=f.getColor();
    //Plane plate=new Plane(z1*pixPerUnit,length*pixPerUnit,width*pixPerUnit,p,c);
    Plane plate = new Plane(z1*pixPerUnit, length*pixPerUnit, width*pixPerUnit, p, color);
    threeDView.addFigure(plate);
    //f.dispose();
  }

  /**
   * Creates a wave function with an amplitdue envelope defined by a function extending from z1 to z2 with polarization pol.
   * The function must be a function of z.
   * The figure propagates in the positive z direction, so z1 must be less than z2. That is, z1<z2.
   * The polarization, pol, is in radians.
   *
   * @param s the amplitude function
   * @param z1
   * @param z2
   * @param p
   */
  public void createParsed(String s, double z1, double z2, double p) {
    //Color c=f.getColor();
    //ParsedWave parsed=new ParsedWave(s,z1*pixPerUnit,z2*pixPerUnit,pixPerUnit,p,c);
    ParsedWave parsed = new ParsedWave(s, z1*pixPerUnit, z2*pixPerUnit, pixPerUnit, p, color);
    threeDView.addFigure(parsed);
    //f.dispose();
  }

  /**
   * Deletes all objects from the screen.
   *
   */
  public void deleteAll() {
    threeDView.clear();
  }

  /**
   * Method theta_adjustmentValueChanged
   *
   * @param e
   */
  void theta_adjustmentValueChanged(AdjustmentEvent e) {
    threeDView.setTheta(theta.getDValue());
  }

  /**
   * Method alpha_adjustmentValueChanged
   *
   * @param e
   */
  void alpha_adjustmentValueChanged(AdjustmentEvent e) {
    threeDView.setAlpha(alpha.getDValue());
  }

  /**
   * Method phi_adjustmentValueChanged
   *
   * @param e
   */
  void phi_adjustmentValueChanged(AdjustmentEvent e) {
    threeDView.setPhi(phi.getDValue());
  }

  /**
   * Method graph_mouseClicked
   *
   * @param e
   */
  void graph_mouseClicked(MouseEvent e) {
    //colorDispenser+=.1F;
    //ParsedWave p= new ParsedWave(function.getText(),wPolarization.getValue(),colorDispenser);
    //threeDView.addFigure(p);
  }

  /**
   * Method clear_mouseClicked
   *
   * @param e
   */
  void clear_mouseClicked(MouseEvent e) {
    deleteAll();
  }

  /**
   * Method linearWave_mouseClicked
   *
   * @param e
   */
  void linearWave_mouseClicked(MouseEvent e) {
    //createLinear(-100,100,150,wPolarization.getValue());
  }

  /**
   * Method linearPol_mouseClicked
   *
   * @param e
   */
  void linearPol_mouseClicked(MouseEvent e) {
    //createPolLinear(150,wPolarization.getValue(),pPolarization.getValue());
  }

  /**
   * Method circular_mouseClicked
   *
   * @param e
   */
  void circular_mouseClicked(MouseEvent e) {
    // createCircular(150,wPolarization.getValue());
  }

  /**
   * Method circularPol_mouseClicked
   *
   * @param e
   */
  void circularPol_mouseClicked(MouseEvent e) {
    //createPolCircular(150,wPolarization.getValue(),pPolarization.getValue());
  }

  /**
   * Method plane_mouseClicked
   *
   * @param e
   */
  void plane_mouseClicked(MouseEvent e) {
    // createPlate(pPolarization.getValue());
  }

  /**
   * Method threeDView_mouseDragged
   *
   * @param e
   */
  void threeDView_mouseDragged(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    if(orientation==0) {
      threeDView.setAlpha(0);
      if(x<xPrevious) {
        threeDView.setTheta(rotateZAxis += .08);
      }                  //mouse moved left
      if(x>xPrevious) {
        threeDView.setTheta(rotateZAxis -= .08);
      }                  //mouse moved right
      if(y<yPrevious) {  //mouse moved up
        if(rotateXAxis<=1.5) {
          threeDView.setPhi(rotateXAxis += .08);
        }
      }
      if(y>yPrevious) {  //mouse moved down
        if(rotateXAxis>=0) {
          threeDView.setPhi(rotateXAxis -= .08);
        }
      }
    }
    if(orientation==1) {
      threeDView.setPhi(0);
      if(y<yPrevious) {  //mouse moved up
        if(rotateYAxis>0) {
          threeDView.setTheta(rotateZAxis -= .08);
        } else {
          threeDView.setTheta(rotateZAxis += .08);
        }
      }
      if(y>yPrevious) {  //mouse moved down
        if(rotateYAxis>0) {
          threeDView.setTheta(rotateZAxis += .08);
        } else {
          threeDView.setTheta(rotateZAxis -= .08);
        }
      }
      if(x<xPrevious) {  //mouse moved left
        if(rotateYAxis<=Math.PI/2) {
          threeDView.setAlpha(rotateYAxis += .08);
        }
      }
      if(x>xPrevious) {  //mouse moved right
        if(rotateYAxis>=-Math.PI/2) {
          threeDView.setAlpha(rotateYAxis -= .08);
        }
      }
    }
    xPrevious = x;
    yPrevious = y;
  }

  /**
   * Method this_componentResized
   *
   * @param e
   */
  void this_componentResized(ComponentEvent e) {
    Figure.setOrigin(this.getSize().width/2, this.getSize().height/2);
  }
}

/**
 * Class EMWave_theta_adjustmentAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class EMWave_theta_adjustmentAdapter implements java.awt.event.AdjustmentListener {

  EMWave adaptee;

  /**
   * Constructor EMWave_theta_adjustmentAdapter
   *
   * @param adaptee
   */
  EMWave_theta_adjustmentAdapter(EMWave adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method adjustmentValueChanged
   *
   * @param e
   */
  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.theta_adjustmentValueChanged(e);
  }
}

/**
 * Class EMWave_alpha_adjustmentAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class EMWave_alpha_adjustmentAdapter implements java.awt.event.AdjustmentListener {

  EMWave adaptee;

  /**
   * Constructor EMWave_alpha_adjustmentAdapter
   *
   * @param adaptee
   */
  EMWave_alpha_adjustmentAdapter(EMWave adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method adjustmentValueChanged
   *
   * @param e
   */
  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.alpha_adjustmentValueChanged(e);
  }
}

/**
 * Class EMWave_phi_adjustmentAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class EMWave_phi_adjustmentAdapter implements java.awt.event.AdjustmentListener {

  EMWave adaptee;

  /**
   * Constructor EMWave_phi_adjustmentAdapter
   *
   * @param adaptee
   */
  EMWave_phi_adjustmentAdapter(EMWave adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method adjustmentValueChanged
   *
   * @param e
   */
  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.phi_adjustmentValueChanged(e);
  }
}

/**
 * Class EMWave_threeDView_mouseMotionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class EMWave_threeDView_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {

  EMWave adaptee;

  /**
   * Constructor EMWave_threeDView_mouseMotionAdapter
   *
   * @param adaptee
   */
  EMWave_threeDView_mouseMotionAdapter(EMWave adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method mouseDragged
   *
   * @param e
   */
  public void mouseDragged(MouseEvent e) {
    adaptee.threeDView_mouseDragged(e);
  }
}

/**
 * Class EMWave_this_componentAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class EMWave_this_componentAdapter extends java.awt.event.ComponentAdapter {

  EMWave adaptee;

  /**
   * Constructor EMWave_this_componentAdapter
   *
   * @param adaptee
   */
  EMWave_this_componentAdapter(EMWave adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method componentResized
   *
   * @param e
   */
  public void componentResized(ComponentEvent e) {
    adaptee.this_componentResized(e);
  }
}
