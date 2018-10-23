package wave3d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.*;


import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.display.*;

/**
 * Wave3d animates a 3D wave traveling along the z direction.
 *
 * This program is based on a student project by ASB.  It was modified by W. Christian.
 *
 * @author Ansel.
 */
public class Wave3d extends SApplet implements SStepable {

  boolean            isStandalone = false;
  Color              color        = Color.black;  // the current drawing color
  boolean            showControls = true;
  int                pixPerUnit   = 10;
  double             rotateZAxis  = 0, rotateYAxis = 0, rotateXAxis = 0;
  int                xPrevious, yPrevious;
  boolean            rotate      = true;
  int                orientation = 1;
  int                fps         = 20;
  double             dz;
  ThreeDPanel        threeDView          = new ThreeDPanel();
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
  boolean            fixedAlpha          = false, fixedTheta = false, fixedPhi = false;

  /**
   * Gets the applet parameters
   *
   * @param key
   * @param def
   *
   * @return
   */
  public String getParameter(String key, String def) {
    return isStandalone
           ? System.getProperty(key, def)
           : ((getParameter(key) != null)
              ? getParameter(key)
              : def);
  }

  /**
   * Initializes the applet
   *
   */
  public void init() {
    try {
      showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      orientation = Integer.parseInt(this.getParameter("Orientation", "0"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      fps = Integer.parseInt(this.getParameter("FPS", "20"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      dz = pixPerUnit * Double.valueOf(this.getParameter("Dz", ".2")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    sliderPanel.setVisible(showControls);
    if(orientation == 0) {
      rotateZAxis = Math.PI / 4;
      rotateYAxis = 0;
      rotateXAxis = Math.PI / 4;
      threeDView.setAngles(rotateZAxis, rotateYAxis, rotateXAxis);
    }
    if(orientation == 1) {
      rotateZAxis = Math.PI / 4;
      rotateYAxis = Math.PI / 4;
      rotateXAxis = 0;
      threeDView.setAngles(rotateZAxis, rotateYAxis, rotateXAxis);
    }
  }

  /**
   * put your documentation comment here
   * @exception Exception
   */
  private void jbInit() throws Exception {
    this.setBackground(Color.white);
    /** @j2sNative */{
    	  this.setSize(new Dimension(600, 650));
    }
    this.addComponentListener(new Wave3d_this_componentAdapter(this));
    this.setLayout(borderLayout1);
    sliderPanel.setLayout(verticalFlowLayout1);
    sliderPanel.setLocale(java.util.Locale.getDefault());
    sliderPanel.setThickness(1);
    alpha.setDMax(6.28);
    alpha.addAdjustmentListener(new java.awt.event.AdjustmentListener() {

      /**
       * put your documentation comment here
       * @param e
       */
      public void adjustmentValueChanged(AdjustmentEvent e) {
        alpha_adjustmentValueChanged(e);
      }
    });
    alpha.setDValue(rotateYAxis);
    alpha.setDMin(0);
    panel1.setLayout(borderLayout2);
    theta.setDMax(6.28);
    theta.addAdjustmentListener(new java.awt.event.AdjustmentListener() {

      /**
       * put your documentation comment here
       * @param e
       */
      public void adjustmentValueChanged(AdjustmentEvent e) {
        theta_adjustmentValueChanged(e);
      }
    });
    theta.setDValue(rotateZAxis);
    theta.setDMin(0);
    phi.setDMax(6.28);
    phi.addAdjustmentListener(new java.awt.event.AdjustmentListener() {

      /**
       * put your documentation comment here
       * @param e
       */
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
    clock.setFPS(fps);
    clock.setDt(1.0/fps);
    clock.addClockListener(this);
    threeDView.setBackground(Color.white);
    threeDView.addMouseMotionListener(new Wave3d_threeDView_mouseMotionAdapter(this));
  }

  /**
   * Method getAppletInfo
   *
   *
   * @return
   */
  public String getAppletInfo() {
    return "Wave3d animates wave motion along the z axis.";
  }

  /**
   * Method getParameterInfo
   *
   *
   * @return
   */
  public String[][] getParameterInfo() {
    String pinfo[][] = {
      {"ShowControls", "boolean", "Show sliders"}, {"Orientation", "int", "orientation of figure"},
      {"FPS", "int", "frames per second"}, {"Dz", "double", "translation distance of wave"},
    };
    return pinfo;
  }

/**
* Counts the number of applets on a page.
*
* @param func
* @param vars
*
* @return
*/
public int getAppletCount() {

    if (firstTime) {
        return 0;
    } else {
        return super.getAppletCount();
    }
}

  /**
   * Starts the applet when the html page is loaded.  Do not script.
   *
   */
  public void start() {
    super.start();
    if (firstTime) {
      firstTime = false;
    }
    threeDView.setOrigin(this.getSize().width / 2, this.getSize().height / 2);
    threeDView.paint();
    //clock.startClock();
    // debug scripts

/*
     setDefault();
     setPixPerUnit(8);
     int id = addFilledSquare(-20, 20);
     setRGB(id, 22, 50, 134);
     id=addAxes(0, 0, -20, 10);
     setRGB(id, 222, 223, 27);
     setLineDensity(6);
     id = addLinear(-30, 30, 10, 0, 0);
     setWavelength(id,30);
     setWaveSpeed(id,4);
     setRGB(id, 135, 52, 55);
     setPolarization(id,1.0);
     //setLineDensity(12);
     //id = addLinear(-20, 20, 10, 0, 1.57);
     //setRGB(id, 52, 135, 55);
     setOneShot(0,40,"stopped");
     forward();
*/


/*
    setDefault();
    setPixPerUnit(8);
    int id = addFilledSquare(-20, 20);
    setRGB(id, 22, 50, 134);
    id = addAxes(0, 0, -20, 10);
    setRGB(id, 222, 223, 27);
    setLineDensity(6);
    id = addWavefunction("25*cos(z/6)*cos(t/4)", -20, 20, 0);
    setRGB(id, 135, 52, 55);
    id = addGrid(20, 20, 30, 0);
    setRGB(id, 22, 50, 134);
    id = addGrid(20, 20, 30, 1.57);
    setRGB(id, 22, 50, 134);
    forward();
 */   
  }

  /**
   * Method forward
   *
   */
  public void forward() {
	if(clock.isRunning()) return; // do not start a clock if it is already running
    threeDView.paint();
    oneShotMsg = "";
    clock.startClock();
  }

  /**
   * Stops the applet when the html page is no longer active.  Do not script.
   */
  public void stop() {
    super.stop();
    clock.stopClock();
  }

  /**
   * Method pause
   *
   */
  public void pause() {
    clock.stopClock();
  }

  /**
   * Destroys the applet when the html page is closed.  Do not script.
   */
  public void destroy() {
    super.destroy();
  }

  /**
   * Steps the animation by dt.
   *
   *   @param dt the time step
   *   @param time the current time.
   */
  public void step(double dt, double time) {
    threeDView.translate(dz, fps*time);
  }

  /**
   * Fix theta so that it cannot be changed using the mouse.
   *
   * @param fixed true will disable
   */
  public void setFixedTheta(boolean fixed) {
    fixedTheta = fixed;
  }

  /**
   * Fix alpha so that it cannot be changed using the mouse.
   *
   * @param fixed true will disable
   */
  public void setFixedAlpha(boolean fixed) {
    fixedAlpha = fixed;
  }

  /**
   * Fix Phi so that it cannot be changed using the mouse.
   *
   * @param fixed true will disable
   */
  public void setFixedPhi(boolean fixed) {
    fixedPhi = fixed;
  }

  /**
   * Sets the viewing angles.
   *
   * @param t  the z axis angle
   * @param a  the x axis angle
   * @param p  the y axis angle
   *
   * @return
   */
  public boolean setAngles(double t, double a, double p) {
    rotateZAxis = t;
    rotateYAxis = a;
    rotateXAxis = p;
    threeDView.setAngles(rotateZAxis, rotateYAxis, rotateXAxis);
    return true;
  }

  /**
   * Sets the pixels per unit.
   *
   * @param ppu
   */
  public void setPixPerUnit(int ppu) {
    pixPerUnit = ppu;
  }

  /**
   * Set the wavelength.
   *
   * @param id
   * @param wavelength
   *
   * @return true if successful; false otherwise
   */
  public boolean setWavelength(int id, double wavelength) {
    Figure fig = threeDView.getThing(id);
    if(fig == null) {
      return false;
    }
    fig.setWavelength(wavelength * pixPerUnit);
    if(!clock.isRunning()) fig.recompute(threeDView);
    if(autoRefresh) {
      threeDView.repaint();
    }
    return true;
  }

  /**
   * Sets the speed of the wave.
   *
   * @param id
   * @param speed
   *
   * @return true if successful; false otherwise
   */
  public boolean setWaveSpeed(int id, double speed) {
    Figure fig = threeDView.getThing(id);
    if(fig == null) {
      return false;
    }
    fig.setSpeed(speed);
    if(autoRefresh) {
      threeDView.repaint();
    }
    return true;
  }

  /**
   * change the object's font for any text4 that is displayed.
   *
   * @param              id The id of the object.
   * @param              family The font family: Helvetica, Times.
   * @param              style The style, 0=plain, 1=bold.
   * @param              size The size of the font;
   * @return             True if successful.
   */
  public boolean setFont(int id, String family, int style, int size) {
    Font   font = new Font(family, style, size);
    Figure fig  = threeDView.getThing(id);
    if(fig == null) {
      return false;
    }
    fig.font = font;
    if(autoRefresh) {
      threeDView.repaint();
    }
    return true;
  }

  /**
   *  Sets the line density for all subsequent drawing.
   *
   *  The density determines the number of pixels between lines in traveling
   *  waves and on grids.  The default is 10.
   *
   *  @param              density line density
   */
  public void setLineDensity(int density) {
    threeDView.lineDensity = density;
  }

  /**
   *  Sets the color of an object.
   *
   *         @param              id The id of the object.
   *         @param              r red
   *         @param              g green
   *         @param              b blue
   * @return            <code>true</code> if successful; <code>false</code> otherwise
   */
  public boolean setRGB(int id, int r, int g, int b) {
    if((id == 0) || (id == threeDView.hashCode())) {
      threeDView.setBackground(new Color(r, g, b));
      return true;
    }
    Figure t = threeDView.getThing(id);
    if(t == null) {
      return false;
    }
    t.color = new Color(r, g, b);
    if(autoRefresh) {
      threeDView.repaint();
    }
    return true;
  }

  /**
  * Set the red, green, and blue color values for all subsequent drawing.
  * Color values must be in the range 0..255.
  *
  * @param              r red.
  * @param              g green.
  * @param              b blue.
  */
    public void setShapeRGB(int r, int g, int b) {
        color = new Color(r, g, b);
    }

  /**
   * Show the visibility of the object.
   *
   * @param id
   * @param     show    <code>true</code> will show object on screen
   *
   * @return            <code>true</code> if successful; <code>false</code> otherwise
   */
  public boolean setVisibility(int id, boolean show) {
    Figure t = threeDView.getThing(id);
    if(t == null) {
      return false;
    }
    t.visible = show;
    if(autoRefresh) {
      threeDView.repaint();
    }
    return true;
  }

  /**
   * Sets the z position of a square or a grid.
   *
   * @param id  the object
   * @param  z  the new position along z
   *
   * @return            <code>true</code> if successful; <code>false</code> otherwise
   */
  public boolean setZ(int id, double z) {
    Figure fig = threeDView.getThing(id);
    if(fig == null) {
      return false;
    }
    fig.setZ(z * pixPerUnit);
    if(autoRefresh) {
      threeDView.repaint();
    }
    return true;
  }

  /**
       * Set the applet to run for a fixed interval, stop, and display a message.
       *
       * @param              min The starting time value for the loop
       * @param              max The ending time for the loop.
       * @param              msg Message to display after the animation stops.
       */
    public void setOneShot(double min, double max, String msg) {
       clock.setOneShot(min, max);
       clock.setTime(min);
       oneShotMsg = msg;
    }

    /**
     * Displays a message in the yellow message box.
     *
     * Messages will be cleared when the animation starts running.
     *
     * @param msg Message to display.
     */
    public void setMessage(String msg) {
        threeDView.setMessage(msg);
    }

  /**
   */
  public void stoppingClock() {
    threeDView.setMessage(oneShotMsg);
  }

  /**
   * Sets the polarization of waves or grid.
   *
   * @param id  the object
   * @param angle
   *
   * @return            <code>true</code> if successful; <code>false</code> otherwise
   */
  public boolean setPolarization(int id, double angle) {
    Figure fig = threeDView.getThing(id);
    if(fig == null) {
      return false;
    }
    boolean isRunning=clock.isRunning();
    if(isRunning) clock.stopClock();
    fig.setPolarization(angle);
    if(isRunning){
      clock.startClock();
    }else if(autoRefresh) {
      threeDView.repaint();
    }
    return true;
  }

  /**
   * Sets a scale factor for the x-y coordinates of an object.
   *
   * Can be used to adjust the wave amplitude.
   *
   * @param id  the object
   * @param angle
   *
   * @return            <code>true</code> if successful; <code>false</code> otherwise
   */
  public boolean setXYFactor(int id, double factor) {
    Figure fig = threeDView.getThing(id);
    if(fig == null) {
      return false;
    }
    boolean isRunning=clock.isRunning();
    if(isRunning) clock.stopClock();
    fig.xyFactor=factor;
    if(isRunning){
      clock.startClock();
    }else if(autoRefresh) {
      threeDView.repaint();
    }
    return true;
  }

   /**
   * Delete an object from the applet.
   *
   * @param id the object identifier
   *
   * @return true if successful
   */
  public boolean deleteObject(int id){
     boolean result= threeDView.deleteObject( id);
     if(autoRefresh) {
      threeDView.repaint();
    }
    return result;
  }

  /**
   * Add text to the animation at the given pixel location.
   *
   * @param              text
   * @param              xpix
   * @param              ypix
   * @return             id of the object
   *
   */
  public int addText(String text, int xpix, int ypix) {
    Text fig = new Text(threeDView, text, xpix, ypix);
    threeDView.addFigure(fig);
    return fig.getID();
  }

  /**
   * Add axes to the animation.
   *
   * @param x
   * @param y
   * @param z
   * @param s
   *
   * @return the id of the object.
   */
  public int addAxes(double x, double y, double z, double s) {
    Axis3D axes = new Axis3D(threeDView, (int) (x * pixPerUnit), (int) (y * pixPerUnit), (int) (z * pixPerUnit),
                             s * pixPerUnit, color);
    threeDView.addFigure(axes);
    AxesLabel labels = new AxesLabel(threeDView, (int) x * pixPerUnit, (int) y * pixPerUnit, (int) (z * pixPerUnit),
                                     s * pixPerUnit, color);
    threeDView.addFigure(labels);
    return axes.getID();
  }

  /**
   * Add a square centered on the z axis to the animation.
   *
   * @param z the position
   * @param s the side
   *
   * @return the id of the object.
   */
  public int addSquare(double z, double s) {
    Figure fig = new Square(threeDView, z * pixPerUnit, s * pixPerUnit, color);
    threeDView.addFigure(fig);
    return fig.getID();
  }

  /**
   * Add a rectangle centered on the z axis to the animation.
   *
   * @param z the position
   * @param w the width along x
   * @param h the height along y
   *
   * @return the id of the object.
   */
  public int addRectangle(double z, double w, double h) {
    Figure fig = new Rectangle2D(threeDView, z * pixPerUnit, w * pixPerUnit, h * pixPerUnit, color);
    threeDView.addFigure(fig);
    return fig.getID();
  }

  /**
   * Add a box centered on the origin to the animation.
   *
   * @param l the length
   * @param w the width
   * @param h the height
   *
   * @return the id of the object.
   */
  public int addBox(double l, double w, double h) {
    Figure fig = new Box3D(threeDView, l* pixPerUnit, w* pixPerUnit, h* pixPerUnit);
    threeDView.addFigure(fig);
    return fig.getID();
  }

  /**
   * Add a filled square.
   *
   * @param z the position
   * @param s the size
   *
   * @return the id of the object.
   */
  public int addFilledSquare(double z, double s) {
    FilledSquare square = new FilledSquare(threeDView, z * pixPerUnit, s * pixPerUnit, color);
    threeDView.addFigure(square);
    return square.getID();
  }

  /**
   * Adds a filled rectangle.
   *
   * @param z the position
   * @param s the size
   *
   * @return the id of the object.
   */
  public int addFilledRectangle(double z, double w, double h) {
    Figure fig = new FilledRectangle(threeDView, z * pixPerUnit, w * pixPerUnit, h * pixPerUnit, color);
    threeDView.addFigure(fig);
    return fig.getID();
  }

  /**
   * Add a sinusoidal wave between two points.
   *
   * @param z1 the starting point
   * @param z2 the ending point
   * @param a  the amplitude
   * @param ph the phase
   * @param p  the polzarization angle
   *
   * @return the id of the object.
   */
  public int addLinear(double z1, double z2, double a, double ph, double p) {
    LinearWave linear = new LinearWave(threeDView, z1 * pixPerUnit, z2 * pixPerUnit, a * pixPerUnit, ph, p, color);
    threeDView.addFigure(linear);
    return linear.getID();
  }

  /**
   * Add a right circular wave between two points.
   *
   * @param z1 the starting point
   * @param z2 the ending point
   * @param a  the amplitude
   * @param ph the phase
   *
   * @return the id of the object.
   */
  public int addCircularRight(double z1, double z2, double a, double ph) {
    CircularWaveRight circular = new CircularWaveRight(threeDView, z1 * pixPerUnit, z2 * pixPerUnit, a * pixPerUnit,
                                   ph, color);
    threeDView.addFigure(circular);
    return circular.getID();
  }

  /**
   * Add a left circular wave between two points.
   *
   * @param z1 the starting point
   * @param z2 the ending point
   * @param a  the amplitude
   * @param ph the phase
   *
   * @return
   */
  public int addCircularLeft(double z1, double z2, double a, double ph) {
    CircularWaveLeft circular = new CircularWaveLeft(threeDView, z1 * pixPerUnit, z2 * pixPerUnit, a * pixPerUnit, ph,
                                  color);
    threeDView.addFigure(circular);
    return circular.getID();
  }

  /**
   * Add a rectangular grid.
   *
   * Grids are designed to represent polarizers.
   *
   * @param z1 the position
   * @param length
   * @param width
   * @param p the orientation angle
   *
   * @return
   */
  public int addGrid(double z1, double length, double width, double p) {
    Grid grid = new Grid(threeDView, z1 * pixPerUnit, length * pixPerUnit, width * pixPerUnit, p, color);
    threeDView.addFigure(grid);
    return grid.getID();
  }

  /**
   * Add a wavefunction f(z,t).
   *
   * @param function the function
   * @param z1 the starting point
   * @param z2 the ending point
   * @param p the polarization angle
   *
   * @return
   */
  public int addWavefunction(String function, double z1, double z2, double p) {
    Wavefunction wavefunction = new Wavefunction(threeDView, function, z1 * pixPerUnit, z2 * pixPerUnit, pixPerUnit, p,
                                  color);
    threeDView.addFigure(wavefunction);
    return wavefunction.getID();
  }

  /**
   * Delete all objects from drawing.
   *
   */
  public void deleteAll() {
    threeDView.clear();
  }

  /**
   * Set default values and deletes all data connections.
   */
  public void setDefault() {
    clock.stopClock();
    super.deleteDataConnections();
    clock.setContinuous();
    oneShotMsg = "";
    threeDView.clear();
    clock.setTime(0.0);
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
   * Method threeDView_mouseDragged
   *
   * @param e
   */
  void threeDView_mouseDragged(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    if(orientation == 0) {
      threeDView.setAlpha(0);
      if(x < xPrevious) {
        if(!fixedTheta) {
          threeDView.setTheta(rotateZAxis += .08);
        }
      }                    //mouse moved left
      if(x > xPrevious) {
        if(!fixedTheta) {
          threeDView.setTheta(rotateZAxis -= .08);
        }
      }                    //mouse moved right
      if(y < yPrevious) {  //mouse moved up
        if(rotateXAxis <= 1.5) {
          if(!fixedPhi) {
            threeDView.setPhi(rotateXAxis += .08);
          }
        }
      }
      if(y > yPrevious) {  //mouse moved down
        if(rotateXAxis >= 0) {
          if(!fixedPhi) {
            threeDView.setPhi(rotateXAxis -= .08);
          }
        }
      }
    }
    if(orientation == 1) {
      threeDView.setPhi(0);
      if(y < yPrevious) {  //mouse moved up
        if(rotateYAxis > 0) {
          if(!fixedTheta) {
            threeDView.setTheta(rotateZAxis -= .08);
          }
        } else {
          if(!fixedTheta) {
            threeDView.setTheta(rotateZAxis += .08);
          }
        }
      }
      if(y > yPrevious) {  //mouse moved down
        if(rotateYAxis > 0) {
          if(!fixedTheta) {
            threeDView.setTheta(rotateZAxis += .08);
          }
        } else {
          if(!fixedTheta) {
            threeDView.setTheta(rotateZAxis -= .08);
          }
        }
      }
      if(x < xPrevious) {  //mouse moved left
        if(rotateYAxis <= Math.PI / 2) {
          if(!fixedAlpha) {
            threeDView.setAlpha(rotateYAxis += .08);
          }
        }
      }
      if(x > xPrevious) {  //mouse moved right
        if(rotateYAxis >= -Math.PI / 2) {
          if(!fixedAlpha) {
            threeDView.setAlpha(rotateYAxis -= .08);
          }
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
    threeDView.setOrigin(this.getSize().width / 2, this.getSize().height / 2);
  }
}

/**
 * Class Wave3d_theta_adjustmentAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Wave3d_theta_adjustmentAdapter implements java.awt.event.AdjustmentListener {

  Wave3d adaptee;

  /**
   * Constructor Wave3d_theta_adjustmentAdapter
   *
   * @param adaptee
   */
  Wave3d_theta_adjustmentAdapter(Wave3d adaptee) {
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
 * Class Wave3d_alpha_adjustmentAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Wave3d_alpha_adjustmentAdapter implements java.awt.event.AdjustmentListener {

  Wave3d adaptee;

  /**
   * Constructor Wave3d_alpha_adjustmentAdapter
   *
   * @param adaptee
   */
  Wave3d_alpha_adjustmentAdapter(Wave3d adaptee) {
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
 * Class Wave3d_phi_adjustmentAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Wave3d_phi_adjustmentAdapter implements java.awt.event.AdjustmentListener {

  Wave3d adaptee;

  /**
   * Constructor Wave3d_phi_adjustmentAdapter
   *
   * @param adaptee
   */
  Wave3d_phi_adjustmentAdapter(Wave3d adaptee) {
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
 * Class Wave3d_threeDView_mouseMotionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Wave3d_threeDView_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {

  Wave3d adaptee;

  /**
   * Constructor Wave3d_threeDView_mouseMotionAdapter
   *
   * @param adaptee
   */
  Wave3d_threeDView_mouseMotionAdapter(Wave3d adaptee) {
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
 * Class Wave3d_this_componentAdapter
 */
class Wave3d_this_componentAdapter extends java.awt.event.ComponentAdapter {

  Wave3d adaptee;

  /**
   * Constructor Wave3d_this_componentAdapter
   *
   * @param adaptee
   */
  Wave3d_this_componentAdapter(Wave3d adaptee) {
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
