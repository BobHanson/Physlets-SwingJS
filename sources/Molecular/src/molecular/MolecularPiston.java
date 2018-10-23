/*
 *
 *
 *                      Class  molecular.MolecularApplet
 *
 * class MolecularApplet extends Applet
 *
 * The main entry point and MolecularApplet applet.
 */
package molecular;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import edu.davidson.tools.*;

/**
 * Class MolecularPiston
 */
public class MolecularPiston extends SApplet {

  String                             button_reset       = "Reset";
  String                             button_start       = "Start";
  String                             button_stop        = "Pause";
  String                             button_forward     = "Step";
  String                             label_newparticles = "New Particles:";
  String                             orientation        = "vertical";
  boolean                            sc, perh, perv, dragable;
  double                             pm, h, g;
  int                                inp, maxp, fps, ppu, del, bwidth, pwidth;
  String                             parseString = null;
  boolean                            parsemode   = true;
  double                             dt          = 0.1;
  int                                mode;
  boolean                            bill          = false;
  PistonEnsemble                     ensemble1     = null;
  PEnsemblePanel                     ensemblePanel = new PEnsemblePanel(this);
  edu.davidson.graphics.EtchedBorder controlPanel  = new edu.davidson.graphics.EtchedBorder();
  edu.davidson.graphics.EtchedBorder etchedBorder3 = new edu.davidson.graphics.EtchedBorder();
  Button                             startbtn      = new Button();
  Button                             resetbtn      = new Button();
  Button                             stepbtn       = new Button();
  Panel                              panel1        = new Panel();
  Label                              label1        = new Label();
  edu.davidson.display.SInteger      partfield     = new edu.davidson.display.SInteger();
  BorderLayout                       borderLayout1 = new BorderLayout();
  BorderLayout                       borderLayout4 = new BorderLayout();
  edu.davidson.display.SNumber       parsefield    = new edu.davidson.display.SNumber();
  BorderLayout                       borderLayout2 = new BorderLayout();
  BorderLayout                       borderLayout3 = new BorderLayout();
  GridLayout                         gridLayout1   = new GridLayout();
  //Construct the applet

  /**
   * Constructor MolecularPiston
   * @y.exclude
   */
  public MolecularPiston() {}

  protected void setResources() {
    button_start       = localProperties.getProperty("button.start", button_start);
    button_stop        = localProperties.getProperty("button.stop", button_stop);
    button_reset       = localProperties.getProperty("button.reset", button_reset);
    button_forward     = localProperties.getProperty("button.forward", button_forward);
    label_newparticles = localProperties.getProperty("label.newparticles", label_newparticles);
  }

  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    ensemble1 = ensemblePanel.getPEnsemble();
    try {
      sc = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      inp = Integer.parseInt(this.getParameter("InitialP", "10"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      fps = Integer.parseInt(this.getParameter("FPS", "10"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      ppu = Integer.parseInt(this.getParameter("PixPerUnit", "10"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      pm = Double.valueOf(this.getParameter("PistonMass", "20")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      g = Double.valueOf(this.getParameter("Gravity", "-1")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      h = Double.valueOf(this.getParameter("InitialPistonPos", "30")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    // try { perh = Boolean.valueOf(this.getParameter("PeriodicH", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    // try { perv = Boolean.valueOf(this.getParameter("PeriodicV", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try {
      maxp = Integer.parseInt(this.getParameter("MaxParticles", "100"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      parseString = String.valueOf(this.getParameter("ParseString", "10+5*sin(t)"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      orientation = String.valueOf(this.getParameter("Orientation", "vertical"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      parsemode = Boolean.valueOf(this.getParameter("ParseMode", "False")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      dragable = Boolean.valueOf(this.getParameter("Dragable", "False")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    // try { bwidth = Integer.parseInt(this.getParameter("Border", "0")); } catch (Exception e) { e.printStackTrace(); }
    try {
      pwidth = Integer.parseInt(this.getParameter("PistonWidth", "5"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    setOrientation(orientation);
    clock.addClockListener(ensemble1);
    setInitialPistonPos(10);
    setPistonWidth(pwidth);
    setMaxParticles(maxp);
    setDragable(dragable);
    setFPS(fps);
    setDt(dt);
    setPpu(ppu);
    setShowControls(sc);
    setPeriodicH(perh);
    setPeriodicV(perv);
    setGravity(g);
    setPiston(parseString, parsemode);
    setDefaultSize(1);
    setRunningID();
    setBackgroundRGB(150, 150, 150);
    setBackground(Color.gray);
    setPistonMass(pm);
    partfield.setValue(inp);
    this.ensemblePanel.ensemble1.setEnableMouse(true);  // added by W. Christian to enable mouse.
  }

  private void jbInit() throws Exception {
    startbtn.setLabel(button_start);
    startbtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        startbtn_actionPerformed(e);
      }
    });
    resetbtn.setLabel(button_reset);
    stepbtn.setLabel(button_forward);
    resetbtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        resetbtn_actionPerformed(e);
      }
    });
    panel1.setLayout(borderLayout1);
    label1.setAlignment(1);
    label1.setText(label_newparticles);
    parsefield.setText("");
    parsefield.addTextListener(new java.awt.event.TextListener() {

      public void textValueChanged(TextEvent e) {
        parsefield_textValueChanged(e);
      }
    });
    partfield.addTextListener(new java.awt.event.TextListener() {

      public void textValueChanged(TextEvent e) {
        partfield_textValueChanged(e);
      }
    });
    this.setSize(new Dimension(300, 300));
    ensemblePanel.setLayout(borderLayout4);
    stepbtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        stepbtn_actionPerformed(e);
      }
    });
    etchedBorder3.setLayout(gridLayout1);
    controlPanel.setLayout(borderLayout2);
    this.setLayout(borderLayout3);
    this.add(ensemblePanel, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(etchedBorder3, BorderLayout.CENTER);
    etchedBorder3.add(startbtn, null);
    etchedBorder3.add(resetbtn, null);
    etchedBorder3.add(stepbtn, null);
    etchedBorder3.add(parsefield, null);
    controlPanel.add(panel1, BorderLayout.WEST);
    panel1.add(label1, BorderLayout.CENTER);
    panel1.add(partfield, BorderLayout.EAST);
  }

  /**
   * Counts the number of applets on a page.
   * @return the count
   * @y.exclude
   */
  public int getAppletCount() {
    if(firstTime) {
      return 0;
    } else {
      return super.getAppletCount();
    }
  }

  /**
   * Method start
   * @y.exclude
   */
  public void start() {
    ensemblePanel.osi = null;
    if(firstTime) {
      firstTime = false;
      ensemble1.reset();
      if(sc) {
        createParticles(inp);
        forward();
      }
    }
    repaint();
    super.start();
  }

  /**
   * Method getAppletInfo
   *
   *
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Applet Information";
  }

  /**
   * Method getParameterInfo
   *
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String pinfo[][] = {
      {"ShowControls", "boolean", ""}, {"InitialP", "int", "number of particles"}, {"Mode", "String", ""},
      {"Frames", "double", "frames per second"}, {"PixPer", "int", "pixels per unit"}, {"Delta", "double", "time step"},
      {"Periodic", "boolean", "periodic boundaries"}, {"PistonMass", "double", "mass of piston"},
      {"Gravity", "double", "gravitational constant"},
    };
    return pinfo;
  }

  /**
   *
   * Shows or hides control panel
   *
   * @param s boolean
   *
   */
  public void setShowControls(boolean s) {
    pause();
    controlPanel.setVisible(s);
    this.invalidate();
    this.validate();
  }

  /**
   * Method startbtn_actionPerformed
   *
   * @param e
   */
  void startbtn_actionPerformed(ActionEvent e) {
    if(!clock.isRunning()) {
      createParticles(inp);
      forward();
    } else {
      pause();
    }
  }

  /*
   *
   * Reset to time t=0;
   *
   *
   *
   * public void resetClock(){
   *   pause();
   *   ensemble1.reset();
   *
   * }
   */

  /**
   *
   * Stop the animation
   *
   *
   */
  public void pause() {
    clock.stopClock();
    startbtn.setLabel(button_start);
  }

  /**
   *
   * Start the animation
   *
   *
   */
  public void forward() {
    setRunningID();
    startbtn.setLabel(button_stop);
    clock.startClock();
  }

  /**
   * Method partfield_textValueChanged
   *
   * @param e
   */
  void partfield_textValueChanged(TextEvent e) {
    pause();
    inp = partfield.getValue();
  }

  /**
   * Method resetbtn_actionPerformed
   *
   * @param e
   */
  void resetbtn_actionPerformed(ActionEvent e) {
    ensemble1.reset();
  }

  /**
   * Method stepbtn_actionPerformed
   *
   * @param e
   */
  void stepbtn_actionPerformed(ActionEvent e) {
    setConstrainIsothermal(true);
    //stepForward();
  }

  /**
   *
   * Steps simulation forward one time step
   *
   *
   *
   */
  public void stepForward() {
    clock.doStep();
  }

  /**
   *
   * Adds one particle to the ensemble
   *
   * @param xin double initial xposition
   * @param yin double initial yposition
   * @param xvel double initial x-velocity
   * @param yvel double initial y-velocity
   * @param r double  particle radius
   * @return int particle number
   *
   * @return the id
   */
  public int addParticle(double xin, double yin, double xvel, double yvel, double r) {
    return ensemble1.addParticle(xin, yin, xvel, yvel, r);
  }

  /**
   * Method createParticles
   *
   * @param n
   */
  public void createParticles(int n) {
    pause();
    //partfield.setValue(n);
    ensemble1.createParticles(n);
  }

  /**
   * Method addParticleDataSource
   *
   * @param i
   *
   * @return the data source id
   */
  public int addParticleDataSource(int i) {
    return ensemble1.addParticleDataSource(i);
  }

  /**
   *
   * Sets color of particle
   *
   * @param i int particle number
   * @param rd int red value   (<256)
   * @param gr int green value    (<256)
   * @param bl int blue value    (<256)
   *
   */
  public void setParticleRGB(int i, int rd, int gr, int bl) {
    ensemble1.setParticleRGB(i, rd, gr, bl);
  }

  /**
   *
   * Sets mass of particle
   * default value is 1 unit.
   *
   * @param i int particle number
   * @param m double mass.
   *
   */
  public void setParticleMass(int i, double m) {
    ensemble1.setParticleMass(i, m);
  }

  /**
   * Method setDefault
   *
   */
  public void setDefault() {
    clock.stopClock();
    clock.setTime(0);
    //setDefaultPiston();
    ensemble1.reset();
    setDefaultMass(1);
    setDefaultSize(1);
    setDefaultColor(50, 0, 170);
    setMinPistonHeight(5);
    ensemble1.showMessage   = false;
    ensemblePanel.showTitle = false;
  }

  /**
   *
   * Sets maximum number of particles that can be stored in arrays
   *
   * @param p max particles
   *
   */
  public void setMaxParticles(int p) {
    ensemble1.setMaxParticles(p);
  }

  /**
   * Method setAutoRefresh
   *
   * @param ar
   */
  public void setAutoRefresh(boolean ar) {
    ensemble1.setAutoRefresh(ar);
  }

  /**
   * Method setBorderWidth
   *
   * @param w
   */
  public void setBorderWidth(int w) {
    ensemble1.setBorderWidth(w);
  }

  /**
   *
   * Sets Background color inside piston
   *
   * @param r int red
   * @param g int green
   * @param b int blue
   *
   */
  public void setBackgroundRGB(int r, int g, int b) {
    ensemble1.setBackgroundRGB(r, g, b);
  }

  /**
   *
   * Sets Background color above piston head
   *
   * @param r int red
   * @param g int green
   * @param b int blue
   *
   */
  public void setBG2Color(int r, int g, int b) {
    setBG2Color(r, g, b);
  }

  /**
   *
   * Sets default mass when adding new particles.  All particles created after
   * calling this method will have this mass
   *
   * @param m double mass
   *
   */
  public void setDefaultMass(double m) {
    ensemble1.setDefaultMass(m);
  }

  /**
   *
   * Sets default size when adding new particles.  All particles created after
   * calling this method will have this radius
   *
   * @param r double radius
   *
   */
  public void setDefaultSize(double r) {
    ensemble1.setDefaultSize(r);
  }

  /**
   *
   * Sets default color when adding new particles.  All particles created after
   * calling this method will have this color
   *
   * @param r int red
   * @param g int green
   * @param b int blue
   *
   */
  public void setDefaultColor(int r, int g, int b) {
    ensemble1.setDefaultColor(r, g, b);
  }

  //sets initial piston position to top of piston.

  /**
   * Method setDefaultPiston
   *
   */
  public void setDefaultPiston() {
    ensemble1.setDefaultPiston();
  }

  /**
   * Method setDefaultTemp
   *
   * @param t
   */
  public void setDefaultTemp(double t) {
    ensemble1.setDefaultTemp(t);
  }

  /**
   *
   * Sets Position of particle
   *
   * @param i int particle number
   * @param xin double initial x-position
   * @param yin double initial y-position
   *
   */
  public void setParticlePos(int i, double xin, double yin) {
    ensemble1.setParticlePos(i, xin, yin);
  }

  /**
   *
   * Sets Velocity of particle
   *
   * @param i int particle number
   * @param xvel double initial x-velocity
   * @param yvel double initial y-velocity
   *
   */
  public void setParticleVel(int i, double xvel, double yvel) {
    ensemble1.setParticleVel(i, xvel, yvel);
  }

  /**
   * Method setInitialPistonPos
   *
   * @param h
   */
  public void setInitialPistonPos(double h) {
    ensemble1.setInitialPistonPos(h);
  }

  /**
   * Method setBoltzmann
   *
   * @param kb
   */
  public void setBoltzmann(double kb) {
    ensemble1.setBoltzmann(kb);
  }

  /**
   *
   * Sets Velocity of particle
   *
   * @param wall string  Which wall?  [top, bottom, right, left]
   * @param t double     The wall temperature
   *
   */
  public void setWallTemp(String wall, double t) {
    wall.toLowerCase();
    wall.trim();
    if(wall.equals("top")||wall.equals("north")) {
      ensemble1.setWallTemp(0, t);
    } else if(wall.equals("right")||wall.equals("east")) {
      ensemble1.setWallTemp(1, t);
    } else if(wall.equals("bottom")||wall.equals("south")) {
      ensemble1.setWallTemp(2, t);
    } else if(wall.equals("left")||wall.equals("west")) {
      ensemble1.setWallTemp(3, t);
    }
  }

  /**
   * Method removeWallTemp
   *
   * @param wall
   */
  public void removeWallTemp(String wall) {
    wall.toLowerCase();
    wall.trim();
    if(wall.equals("top")||wall.equals("north")) {
      ensemble1.removeWallTemp(0);
    } else if(wall.equals("right")||wall.equals("east")) {
      ensemble1.removeWallTemp(1);
    } else if(wall.equals("bottom")||wall.equals("south")) {
      ensemble1.removeWallTemp(2);
    } else if(wall.equals("left")||wall.equals("west")) {
      ensemble1.removeWallTemp(3);
    }
  }

  /**
   *
   * Sets frames per second of animation
   *
   * @param fps int
   *
   */
  public void setFPS(int fps) {
    clock.setFPS(fps);
  }

  /**
   *
   * Sets calculation time step
   *
   * @param dt double  timestep
   *
   */
  public void setDt(double dt) {
    clock.setDt(dt);
    //ensemble1.setDt(d);
  }

  /**
   *
   * Sets calculation time delay
   *
   * @param delay int  delay milliseconds
   *
   */
  public void setDelay(int delay) {
    delay = Math.max(delay, 10);
    clock.setFPS(1000.0/delay);
  }

  /**
   *
   * Removes ith particle from ensemble
   *
   * @param i int particle number
   *
   */
  public void removeParticle(int i) {
    ensemble1.removeParticle(i);
  }

  /**
   * Method setParticles
   *
   * @param n
   */
  public void setParticles(int n) {
    ensemble1.setParticles(n);
  }

  /**
   *
   * Set the temperature of the ensemble in 1 step.
   *
   * @param temp double  The new temperature.
   *
   */
  public void setTemp(double temp) {
    ensemble1.setTemp(temp);
  }

  /**
   *
   * Set the temperature of the ensemble in n steps. Data will be sent to data listeners after each step.
   *
   * @param temp double  The new temperature.
   * @param steps int    The number of steps.
   *
   */
  public void setTemperature(double temp, int steps) {
    ensemble1.setTemperature(temp, steps);
  }

  /**
   *
   * Sets pixels per unit of ensemble
   *
   * @param p int
   *
   */
  public void setPpu(int p) {
    ensemble1.setPpu(ppu);
  }

  /**
   *
   * Sets Periodic periodic boundary conditions
   *
   * @param p boolean
   *
   */
  public void setPeriodicH(boolean p) {
    ensemble1.setPeriodicH(p);
  }

  /**
   *
   * Sets Periodic periodic boundary conditions
   *
   * @param p boolean
   *
   */
  public void setPeriodicV(boolean p) {
    ensemble1.setPeriodicV(p);
  }

  /**
   *
   * Initializes and fills all arrays
   * must be done when mode is set or something else changes
   *
   *
   *
   * @param k
   */
  /*
   * public void setUp(){
   * ensemble1.setup();
   * }
   */

  /**
   *
   * Puts value in kinetic energy field
   *
   * @param k double value to be inserted in field
   *
   */
  public void setKE(double k) {
    parsefield.setValue(k);
  }

  /**
   * Method setOrientation
   *
   * @param or
   */
  public void setOrientation(String or) {
    pause();
    or.toLowerCase().trim();
    this.orientation = or;
    ensemble1.setOrientation(or);
  }

  /**
   *
   *  Move the piston to set the volume for the ensemble.
   *
   *  @param vol         The new volume.
   * @param steps
   *
   */
  public void setVolume(double vol, int steps) {
    ensemble1.setVolume(vol, steps, false);
  }

  /**
   *
   *  Move the piston to set the volume for the ensemble.
   *
   *  @param vol         The new volume.
   * @param steps
   *
   */
  public void setVolumeIsothermal(double vol, int steps) {
    double t = ensemble1.getTemp();
    ensemble1.setVolume(vol, steps, true);
    ensemble1.setTemp(t);
  }

  /**
   * Method constrains the temperature of the ensemble to a fixed value.
   * Used for isothermal expansion and compression when piston is moving.
   *
   * @param tf boolean
   */
  public void setConstrainIsothermal(boolean tf) {
    ensemble1.setConstrainIsothermal(tf);
  }

  /**
   *
   * Adds a message to small yellow box opposite coordinate display.
   *
   *
   * @param msg String
   */
  public void setMessage(String msg) {
    ensemble1.setMessage(msg);
  }

  /**
   *
   * Sets offsets for title
   *
   * @param xoff
   * @param yoff
   */
  public void setDisplayOffset(int xoff, int yoff) {
    if(xoff>=0) {
      ensemblePanel.txtxoff = xoff;
    }
    if(yoff>=0) {
      ensemblePanel.txtyoff = yoff;
    }
  }

  /**
   *
   * Sets color of title
   *
   * @param r int red
   * @param g int green
   * @param b int blue
   */
  public void setRGB(int r, int g, int b) {
    ensemblePanel.txtcolor = new Color(r, g, b);
  }

  /**
   *
   * Adds a title to ensemble in top-center
   *
   * @param cap String
   */
  public void addCaption(String cap) {
    ensemblePanel.addCaption(cap);
  }

  /**
   * Method allows the piston to be dragable
   *
   * @param d boolean
   */
  public void setDragable(boolean d) {
    ensemble1.setDragable(d);
  }

  /**
   * Method sets minimum piston height for piston when dragging.
   * Does not apply to normal piston opperation.
   * Method protects against crushing particles and crashing program.
   *
   * @param h double height in units. (usually around 3 to 7)
   */
  public void setMinPistonHeight(double h) {
    ensemble1.setMinPistonHeight(h);
  }

  /**
   * Method setPiston
   *
   * @param str
   * @param parsemode
   */
  public void setPiston(String str, boolean parsemode) {
    boolean runAgain = false;
    double  newpos   = 0;
    if(clock.isRunning()) {
      runAgain = true;
      pause();
    }
    this.parsemode = parsemode;
    parsefield.setText(str);
    if(!ensemble1.setParse(str, parsemode)) {
      parsefield.setBackground(Color.red);
      return;
    } else {
      parsefield.setBackground(Color.white);
    }
    if(ensemble1.orientation==0) {
      newpos = ensemble1.xOrigin+ensemble1.pparser.evaluate(clock.getTime());
    } else {
      newpos = ensemble1.yOrigin+ensemble1.pparser.evaluate(clock.getTime());
    }
    ensemble1.movePiston(newpos, 4, true);  // move the piston to the new position but keep Temp constant.
    if(runAgain) {
      forward();
    }
    //ensemble1.reset();
  }

  /**
   * Method getPistonPos
   *
   *
   * @return the position
   */
  public double getPistonPos() {
    if(ensemble1.orientation==0) {
      return ensemble1.rwpos;
    } else {
      return ensemble1.twpos;
    }
  }

  /*
   * public void setRWPos(double p){ensemble1.setRWPos(p);}
   * public void setLWPos(double p){ensemble1.setLWPos(p);}
   * public void setTWPos(double p){ensemble1.setTWPos(p);}
   * public void setBWPos(double p){ensemble1.setBWPos(p);}
   */

  /**
   *
   * Returns the right wall position
   *
   * @return double
   *
   */
  public double getRWPos() {
    return ensemble1.getRWPos();
  }

  /**
   *
   * Returns the left wall position
   *
   * @return double
   *
   */
  public double getLWPos() {
    return ensemble1.getLWPos();
  }

  /**
   *
   * Returns the top wall position
   *
   * @return double
   *
   */
  public double getTWPos() {
    return ensemble1.getTWPos();
  }

  /**
   *
   * Returns the bottom wall position
   *
   * @return double
   *
   */
  public double getBWPos() {
    return ensemble1.getBWPos();
  }

  /**
   *
   * Returns the id of the ensemble.   This id can be used to make data connections.
   *
   * @return int  The id.
   *
   */
  public int getEnsembleID() {
    return ensemble1.hashCode();
  }

  /**
   *
   * Returns the id of the velocity histogram. This id can be used to make data connections.
   *
   * @param nbins int  The number of bins in the histogram.
   * @param vmin
   * @param vmax
   *
   * @return int  The id.
   *
   */
  public int getHistogramID(int nbins, double vmin, double vmax) {
    return ensemble1.setHistogram(nbins, vmin, vmax);
  }

  /**
   *
   * Returns the object identifier of the particle. This id can be used to make data connections.
   *
   * Each particle has an index ranging from 0 to N-1 that specifies its postion in an array where N is the total number
   * of particles. This index must be converted to an object identifier in order to make a data connection.
   *
   * @param i int  the particle index.
   *
   * @return int  the object identifier.
   *
   */
  public int getParticleID(int i) {
    return ensemble1.addParticleDataSource(i);
  }

  /**
   *
   * Sets Mass of piston
   *
   * @param m double new mass of piston
   *
   */
  public void setPistonMass(double m) {
    ensemble1.setPistonMass(m);
  }

  /**
   *
   * Sets width of piston
   *
   * @param pw double new mass of piston
   *
   */
  public void setPistonWidth(int pw) {
    ensemble1.setPistonWidth(pw);
  }

  /**
   *
   * Sets Gravitational constant that acts on piston
   *
   * @param gr double
   *
   */
  public void setGravity(double gr) {
    ensemble1.setGravity(gr);
  }

  /**
   * Method parsefield_textValueChanged
   *
   * @param e
   */
  void parsefield_textValueChanged(TextEvent e) {
    if(!ensemble1.setParse(parsefield.getText(), parsemode)) {
      parsefield.setBackground(Color.red);
    } else {
      parsefield.setBackground(Color.white);
    }
  }
}
