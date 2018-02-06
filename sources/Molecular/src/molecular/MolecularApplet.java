/*
 *
 *
 *                      Class  molecular.MolecularApplet
 *
 *
 *
 * class MolecularApplet extends Applet
 *
 * The main entry point and MolecularApplet applet.
 *
 * @author Jim Nolen and Wolfgang Christian
 *
 */
package molecular;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;

/**
 * Class MolecularApplet
 *
 */
public class MolecularApplet extends SApplet {

  String                             button_reset       = "Reset";
  String                             button_start       = "Start";
  String                             button_stop        = "Pause";
  String                             button_forward     = "Step";
  String                             label_newparticles = "New Particles:";
  boolean                            sc, perh, per, perv;
  int                                inp, maxp, fps, ppu, mode, bwidth;
  boolean                            bill          = false;
  double                             dt            = 0.1;
  Ensemble                           ensemble1     = null;
  EnsemblePanel                      ensemblePanel = new EnsemblePanel(this);
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
  BorderLayout                       borderLayout2 = new BorderLayout();
  BorderLayout                       borderLayout3 = new BorderLayout();
  GridLayout                         gridLayout1   = new GridLayout();
  //Construct the applet

  /**
   * Constructor MolecularApplet
   * @y.exclude
   */
  public MolecularApplet() {}

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
    ensemble1 = ensemblePanel.getEnsemble();
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
      perh = Boolean.valueOf(this.getParameter("PeriodicH", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      perv = Boolean.valueOf(this.getParameter("PeriodicV", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      maxp = Integer.parseInt(this.getParameter("MaxParticles", "80"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      bwidth = Integer.parseInt(this.getParameter("Border", "5"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    clock.addClockListener(ensemble1);
    setBorderWidth(bwidth);
    setMaxParticles(maxp);
    setFPS(fps);
    setDt(dt);
    setPpu(ppu);
    setShowControls(sc);
    setPeriodicV(perv);
    setPeriodicH(perh);
    setDefaultSize(1);
    setRunningID();
    setBackgroundRGB(150, 150, 150);
    setBackground(Color.gray);
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
    partfield.addTextListener(new java.awt.event.TextListener() {

      public void textValueChanged(TextEvent e) {
        partfield_textValueChanged(e);
      }
    });
    this.setSize(new Dimension(392, 256));
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
    controlPanel.add(panel1, BorderLayout.WEST);
    panel1.add(label1, BorderLayout.CENTER);
    panel1.add(partfield, BorderLayout.EAST);
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
      //{"Periodic","boolean","periodic boundaries"}
    };
    return pinfo;
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
    // changed by wc
    super.start();
    ensemblePanel.osi = null;
    if(firstTime) {
      pause();
      firstTime = false;
      ensemble1.setDefault();
      //createParticles(20);
      //this.setParticleFixed(1,true);
      // this.setParticleVel(1,0,0);
    }
    repaint();
    //resetClock();
    //if (firstTime) {
    // createParticles(inp);
    // forward();
    //firstTime=false;
    //}
  }

  /**
   *
   * Shows or hides control panel
   *
   * @param s boolean
   *
   */
  public void setShowControls(boolean s) {
    controlPanel.setVisible(s);
    this.invalidate();
    this.validate();
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
   * Adds a title to ensemble in top-center
   *
   * @param cap String
   */
  public void addCaption(String cap) {
    ensemblePanel.addCaption(cap);
  }

  /**
   * Method this_componentResized
   *
   * @param e
   */
  void this_componentResized(ComponentEvent e) {
    pause();
    ensemble1.setDefault();
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

  /**
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
  public void setDefault() {
    clock.stopClock();
    clock.setTime(0);
    ensemble1.setDefault();
    setDefaultMass(1);
    setDefaultSize(1);
    setDefaultColor(50, 0, 170);
    ensemble1.showMessage   = false;
    ensemblePanel.showTitle = false;
  }

  /**
   *
   * Stops the animation
   */
  public void pause() {
    clock.stopClock();
    startbtn.setLabel(button_start);
  }

  /**
   * Starts the animation
   */
  public void forward() {
    setRunningID();
    //ensemble1.preRun();
    startbtn.setLabel(button_stop);
    //if (clock.isRunning()){pause();}
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
    pause();
    ensemble1.setDefault();
  }

  /**
   * Method stepbtn_actionPerformed
   *
   * @param e
   */
  void stepbtn_actionPerformed(ActionEvent e) {
    stepForward();
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
   *
   *
   * @return
   */
  /*
   * public void setParticle(int i, double xin, double yin, double xvel, double yvel, double r){
   * ensemble1.setParticle(i,xin, yin,xvel,yvel,r);
   * }
   */

  /**
   *
   * Adds one particle to the ensemble
   *
   * @param xin double initial xposition
   * @param yin double initial yposition
   * @param xvel double initial x-velocity
   * @param yvel double initial y-velocity
   * @param r double  particle radius
   * @return int new particle number
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
    boolean runAgain = false;
    if(clock.isRunning()) {
      runAgain = true;
      pause();
    }
    //partfield.setValue(n);
    ensemble1.createParticles(n);
    if(runAgain) {
      runAgain = false;
      forward();
    }
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
   * Set the temperature of the ensemble in one step.
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
   * Removes ith particle from ensemble
   *
   * @param i int particle number
   *
   */
  public void removeParticle(int i) {
    ensemble1.removeParticle(i);
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
   *
   * Sets Background color inside ensemble
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
   * Method setBorderWidth
   *
   * @param w
   */
  public void setBorderWidth(int w) {
    ensemble1.setBorderWidth(w);
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
   *
   * Sets size of particle
   * default value is 1 in world units.
   *
   * @param i int particle number
   * @param s double size.
   *
   */
  public void setParticleSize(int i, double s) {
    ensemble1.setParticleSize(i, s);
  }

  /**
   *
   * Sets a particle so that it cannot move.
   *
   * @param i int particle number
   * @param fixed true if fixed
   *
   */
  public void setParticleFixed(int i, boolean fixed) {
    ensemble1.setParticleFixed(i, fixed);
  }

  /**
   * Adds a data source to a particle so that it can be used in a data connection.
   *
   * @param i
   *
   * @return the id
   */
  public int addParticleDataSource(int i) {
    return ensemble1.addParticleDataSource(i);
  }

  /**
   * Method setAutoRefresh
   *
   * @param ar
   */
  public void setAutoRefresh(boolean ar) {
    autoRefresh = ar;
    ensemble1.setAutoRefresh(ar);
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
   * Method setWallTemp
   *
   * @param wall
   * @param t
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
   * Sets Periodic periodic boundary conditions  in horizontal direction
   *
   * @param p boolean
   *
   */
  public void setPeriodicH(boolean p) {
    pause();
    ensemble1.setPeriodicH(p);
  }

  /**
   *
   * Sets Periodic periodic boundary conditions ion vertical direction
   *
   * @param p boolean
   *
   */
  public void setPeriodicV(boolean p) {
    pause();
    ensemble1.setPeriodicV(p);
  }

  /**
   *
   * Sets initial number of particles
   *
   *
   *
   * @return
   */
  /*
   * public void setInp(int i){
   * partfield.setValue(i);
   * ensemble1.setInp(i);
   * pause();
   * }
   */

  /**
   *
   * Initializes and fills all arrays
   * must be done when mode is set or something else changes
   *
   *
   *
   */
  /*
   * public void setUp(){
   *  pause();
   *  ensemble1.setup();
   * }
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
   * @return int  the id
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
}
