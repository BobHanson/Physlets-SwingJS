////////////////////////////////////////////////////////////////////////////////
//      Diatomic.java
//      Wolfgang Christian
package diatomic;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;

/**
 * Class Diatomic
 */
public class Diatomic extends SApplet {
  String button_start = "Run ";
  String button_stop = "Stop";
  String button_step = "Step";
  String label_time = "Time:";
  boolean      showControls;
  int          numDiatomic;
  int          numAtom;
  int          fps;
  double       dt;
  BorderLayout borderLayout1 = new BorderLayout();
  EtchedBorder etchedBorder1 = new EtchedBorder();
  EtchedBorder controlPanel  = new EtchedBorder();
  Panel        panel1        = new Panel();
  Panel        panel2        = new Panel();
  Button       runBtn        = new Button();
  PhysletPanel physletPanel  = new PhysletPanel(this);
  Button       stepBtn       = new Button();


  /**
   * Constructor Diatomic
   * @y.exclude
   */
  public Diatomic() {}

  protected void setResources() {
  button_start = localProperties.getProperty("button.start", button_start);
  button_stop = localProperties.getProperty("button.stop", button_stop);
  button_step = localProperties.getProperty("button.step", button_step);
  label_time = localProperties.getProperty("label.time", label_time);
}



  /**
   * Method init
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
      numDiatomic = Integer.parseInt(this.getParameter("NumMol", "16"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      numAtom = Integer.parseInt(this.getParameter("NumAtom", "16"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      fps = Integer.parseInt(this.getParameter("FPS", "20"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      dt = Double.valueOf(this.getParameter("Dt", "0.1")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    clock.setDt(dt);
    clock.setFPS(fps);
    clock.addClockListener(physletPanel);
    controlPanel.setVisible(showControls);
  }

  //Component initialization
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    runBtn.setLabel(button_start);
    runBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        runBtn_actionPerformed(e);
      }
    });
    stepBtn.setLabel(button_step);
    stepBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        stepBtn_actionPerformed(e);
      }
    });
    controlPanel.setBackground(Color.lightGray);
    this.add(etchedBorder1, BorderLayout.CENTER);
    etchedBorder1.add(physletPanel, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(panel2, BorderLayout.WEST);
    panel2.add(runBtn, null);
    panel2.add(stepBtn, null);
    controlPanel.add(panel1, BorderLayout.CENTER);
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
    String[][] pinfo = {
      {"ShowControls", "boolean", "Show the user interface"}, {"NumMol", "int", "Number of diatomic molecules"},
      {"NumAtom", "int", "Number of atoms"}, {"FPS", "int", "Frames per second"}, {"Dt", "double", "Time step"},
    };
    return pinfo;
  }

  /**
   * Method runBtn_actionPerformed
   *
   * @param e
   */
  void runBtn_actionPerformed(ActionEvent e) {
    if(this.clock.isRunning()) {
      runBtn.setLabel(button_start);
      clock.stopClock();
    } else {
      runBtn.setLabel(button_stop);
      clock.setTime(0);
      clock.startClock();
    }
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
  public synchronized void start() {
    if(firstTime) {
      firstTime = false;
      physletPanel.createOSI();
      physletPanel.initialize();
      physletPanel.setAtomNum(numAtom);
      physletPanel.setMolNum(numDiatomic);
      //physletPanel.setAtomNum(1);
      //physletPanel.setMolNum(0);
      //set(0,"atom","i=4,r=5, x=19,y=13,fixed=true");
      physletPanel.paintOSI();
    }
    super.start();
  }

  /**
   * Method stepBtn_actionPerformed
   *
   * @param e
   */
  void stepBtn_actionPerformed(ActionEvent e) {
    if(clock.isRunning()) {
      runBtn_actionPerformed(null);
    } else {
      clock.doStep();
    }
  }

  /**
   * Create an object and add it to the Physlet.
   * The first argument is the name of the object to be added and the second is a
   * comma-delimited list of parameters.  For example, a circle can be added a follows:
   * <p>
   * <code>addObject ("circle", "x = 0, y = -1.0, r = 10");</code>
   * </p>
   * See the supplemental documentation for a list of
   * <a href="doc-files/EField_addObject.html">object names and parameters.</a>
   *
   * @param              name the type of object to be created.
   * @param              parList a list of parameters to be set
   * @return             id the identifier of the ensemble
   */
  public synchronized int addObject(String name, String parList) {
    int n = 1;
    name = name.toLowerCase().trim();
    name = SUtil.removeWhitespace(name);
    //String parList2 = parList.trim();
    parList = SUtil.removeWhitespace(parList);
    if(name.equals("atom")) {
      if(SUtil.parameterExist(parList, "n=")) {
        n = (int) SUtil.getParam(parList, "n=");
      }
      n = Math.max(0, n);
      n = Math.min(n, EnsemblePanel.maxN);
      createAtoms(n);
      if(SUtil.parameterExist(parList, "m=")) {
        physletPanel.mM = SUtil.getParam(parList, "m=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        int r = (int) SUtil.getParam(parList, "r=");
        physletPanel.setAtomRad(r);
      }
      return physletPanel.getID();
    } else if(name.equals("diatomic")||name.equals("diatom")||name.equals("molecule")) {
      if(SUtil.parameterExist(parList, "n=")) {
        n = (int) SUtil.getParam(parList, "n=");
      }
      n = Math.max(0, n);
      n = Math.min(n, EnsemblePanel.maxN);
      createDiatomics(n);
      if(SUtil.parameterExist(parList, "m=")) {
        physletPanel.mD = SUtil.getParam(parList, "m=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        int r = (int) SUtil.getParam(parList, "r=");
        physletPanel.setMolRad(r);
      }
      if(SUtil.parameterExist(parList, "d=")) {
        double sep = SUtil.getParam(parList, "d=");
        physletPanel.setMolSeparation(sep);
      }
      return physletPanel.getID();
    }
    return 0;
  }

  /**
   *  Create atoms and add them to the ensemble.
   *
   *  @param n the number of atoms
   */
  private void createAtoms(int n) {
    boolean runAgain = false;
    if(clock.isRunning()) {
      runAgain = true;
      pause();
    }
    physletPanel.setAtomNum(n);
    if(runAgain) {
      runAgain = false;
      forward();
    } else {
      physletPanel.paintOSI();
      if(this.autoRefresh) {
        physletPanel.repaint();
      }
    }
  }

  /**
   *  Create diatomic molecules and add them to the ensemble.
   *
   *  @param n the number of molecules
   */
  private void createDiatomics(int n) {
    boolean runAgain = false;
    if(clock.isRunning()) {
      runAgain = true;
      pause();
    }
    physletPanel.setMolNum(n);
    if(runAgain) {
      runAgain = false;
      forward();
    } else {
      physletPanel.paintOSI();
      if(this.autoRefresh) {
        physletPanel.repaint();
      }
    }
  }

  /**
   *  Sets the default conditions.
   *
   */
  public void setDefault() {
    clock.stopClock();
    clock.setTime(0);
    this.deleteDataConnections();
    physletPanel.setDefault();
    physletPanel.paintOSI();
  }

  /**
   *
   * Returns the id of an atom.
   *
   * The atom id can be used to make a data connection to the atom's dynamic variables.
   *
   * @param i
   * @return int  the id.
   *
   */
  public int addAtomDataSource(int i) {
    return physletPanel.addAtomDataSource(i);
  }

  /**
   *
   * Returns the id of an atom.
   *
   * The atom id can be used to make a data connection to the atom's dynamic variables.
   *
   * @param i
   * @return int  the id.
   *
   */
  public int addDiatomicDataSource(int i) {
    return physletPanel.addDiatomicDataSource(i);
  }

  /**
   * Change the properies of an object.
   * The first argument is the object identifier.
   * The second argument is the name of the property and the third is a
   * comma-delimited list of parameters.  For example, the scale can be added a follows:
   * <p>
   * <code>set(id, "sale", "xmin=0, xmax=2, autoscalx=false");</code>
   * </p>
   *
   * @param              id the identifier of the object
   * @param              name the type of property to be created.
   * @param              parList a list of parameters
   * @return             true if successful
   */
  public synchronized boolean set(int id, String name, String parList) {
    boolean shouldRun = false;
    if(clock.isRunning()) {
      clock.stopClock();
      shouldRun = true;
    }
    name = name.toLowerCase().trim();
    name = SUtil.removeWhitespace(name);
    //String parList2 = parList.trim();
    parList = SUtil.removeWhitespace(parList);
    int    i     = 0;
   // double x     = 0, y = 0, vx = 0, vy = 0;
    double scale = physletPanel.scale;
    if(name.equals("atom")) {
      if(SUtil.parameterExist(parList, "i=")) {
        i = (int) SUtil.getParam(parList, "i=");
        if(i<1) {
          System.out.println("Atom index <1.");
          return false;
        }
        if(i>physletPanel.anMax) {
          System.out.println("Atom index out of range. Number of atoms="+physletPanel.anMax);
          return false;
        }
        if(SUtil.parameterExist(parList, "x=")) {
          physletPanel.ax[i] = SUtil.getParam(parList, "x=")*scale;
        }
        if(SUtil.parameterExist(parList, "y=")) {
          physletPanel.ay[i] = physletPanel.iheight-SUtil.getParam(parList, "y=")*scale;
        }
        if(SUtil.parameterExist(parList, "vx=")) {
          physletPanel.avx[i] = SUtil.getParam(parList, "vx=")*scale;
        }
        if(SUtil.parameterExist(parList, "vy=")) {
          physletPanel.avy[i] = -SUtil.getParam(parList, "vy=")*scale;
        }
        if(SUtil.parameterExist(parList, "fixed=")) {
          if(SUtil.getParamStr(parList, "fixed=").equalsIgnoreCase("true")) {
            physletPanel.setAtomFixed(i, true);
          } else {
            physletPanel.setAtomFixed(i, false);
          }
        }
      }
    } else if(name.equals("diatomic")||name.equals("molecule")) {
      if(SUtil.parameterExist(parList, "i=")) {
        i = (int) SUtil.getParam(parList, "i=");
        if(i<1) {
          System.out.println("Diatomic index <1.");
          return false;
        }
        if(i>physletPanel.mnMax) {
          System.out.println("Diatomic index out of range. Number of atoms="+physletPanel.mnMax);
          return false;
        }
        if(SUtil.parameterExist(parList, "x=")) {
          physletPanel.xcm[i] = SUtil.getParam(parList, "x=")*scale;
        }
        if(SUtil.parameterExist(parList, "y=")) {
          physletPanel.ycm[i] = physletPanel.iheight-SUtil.getParam(parList, "y=")*scale;
        }
        if(SUtil.parameterExist(parList, "vx=")) {
          physletPanel.vxcm[i] = SUtil.getParam(parList, "vx=")*scale;
        }
        if(SUtil.parameterExist(parList, "vy=")) {
          physletPanel.vycm[i] = -SUtil.getParam(parList, "vy=")*scale;
        }
        if(SUtil.parameterExist(parList, "theta=")) {
          physletPanel.teta[i] = SUtil.getParam(parList, "theta=");
        }
        if(SUtil.parameterExist(parList, "w=")) {
          physletPanel.w[i] = SUtil.getParam(parList, "w=");
        }
        physletPanel.BallDValues(i);
      }
    } else if(name.equals("ensemble")) {
      if(SUtil.parameterExist(parList, "steps=")) {
        physletPanel.numSteps = Math.max(1, (int) SUtil.getParam(parList, "steps="));
      }
      if(SUtil.parameterExist(parList, "mass_atom=")) {
        physletPanel.mM = SUtil.getParam(parList, "mass_atom=");
      }
      if(SUtil.parameterExist(parList, "mass_mol=")) {
        physletPanel.mD = SUtil.getParam(parList, "mass_mol=");
      } else if(SUtil.parameterExist(parList, "mass_diatomic=")) {
        physletPanel.mD = SUtil.getParam(parList, "mass_diatomic=");
      }
      if(SUtil.parameterExist(parList, "r_atom=")) {
        int r = (int) SUtil.getParam(parList, "r_atom=");
        physletPanel.setAtomRad(r);
      }
      if(SUtil.parameterExist(parList, "r_mol=")) {
        int r = (int) SUtil.getParam(parList, "r_mol=");
        physletPanel.setMolRad(r);
      } else if(SUtil.parameterExist(parList, "r_diatomic=")) {
        int r = (int) SUtil.getParam(parList, "r_diatomic=");
        physletPanel.setMolRad(r);
      }
      if(SUtil.parameterExist(parList, "separation=")) {
        double sep = SUtil.getParam(parList, "separation=");
        physletPanel.setMolSeparation(sep);
      }
      if(SUtil.parameterExist(parList, "scale=")) {
        physletPanel.scale = SUtil.getParam(parList, "scale=");
      } else if(SUtil.parameterExist(parList, "ppu=")) {
        physletPanel.scale = SUtil.getParam(parList, "ppu=");
      }
    } else {
      return false;
    }
    physletPanel.KinEnTotal();
    if(shouldRun) {
      clock.startClock();
    } else if(this.autoRefresh) {
      physletPanel.paintOSI();
      physletPanel.repaint();
    }
    return true;
  }

  /**
   * Repaint the OSI if the clock is not running.
   */
  private void repaintOSI() {
    if(destroyed) {
      return;
    }
    if(this.autoRefresh&&!clock.isRunning()) {
      physletPanel.paintOSI();
      physletPanel.repaint();
    }
  }

  /**
   * Sets the color of the object.
   *
   * @param              id The series id.
   * @param              r red.
   * @param              g green.
   * @param              b blue
   *
   * @return true if successful, false otherwise
   */
  public boolean setRGB(int id, int r, int g, int b) {
    Color c = new Color(r, g, b);
    if((id==0)||(id==physletPanel.hashCode())) {
      physletPanel.backgroundColor = c;
      repaintOSI();
      return true;
    }
    SDataSource ds = SApplet.getDataSource(id);
    if((ds!=null)&&(ds instanceof PhysletPanel.AtomSource)) {
      int i = ((PhysletPanel.AtomSource) ds).index;
      if(i<physletPanel.atomColor.length) {
        physletPanel.atomColor[i] = c;
      }
      repaintOSI();
      return true;
    }
    if((ds!=null)&&(ds instanceof PhysletPanel.DiatomicSource)) {
      int i = ((PhysletPanel.DiatomicSource) ds).index;
      if(i<physletPanel.molColor.length) {
        physletPanel.molColor[i] = c;
      }
      repaintOSI();
      return true;
    }
    return false;
  }

  /**
   *   Forces the applet to repaint when an object changes its properties.
   *   Default is true.
   *   Set this value to false at the beginning of long scripts and then reset
   *   to true to avoid flashing.
   *
   *   @param              auto The id of the object.
   */
  public void setAutoRefresh(boolean auto) {
    this.autoRefresh = auto;
    if(this.autoRefresh) {
      physletPanel.paintOSI();
      physletPanel.repaint();
    }
  }

  /**
   *
   * Returns the id of the ensemble.
   *
   * The ensemble id can be used to make a data connection to the ensemble state variables.
   *
   * @return int  the id.
   *
   */
  public int getEnsembleID() {
    return physletPanel.hashCode();
  }
}
