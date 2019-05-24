package energyEigenvalue;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.*;
import edu.davidson.display.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;


/**
 * Class EnergyEigenvalue
 */
public class EnergyEigenvalue extends SApplet {
  String button_update = "Update";
  String button_find = "Find";
  String label_potential = "Potential";
  String label_energy = "Energy";
  String label_levels = "Energy Levels";

  boolean      autoscalePotential = false, sa;
  boolean      sf;
  boolean      noscale = false;
  double       energy, hBar;
  double       xmin, xmax, breakvalue;
  double       ymin, ymax, tolerance;
  int          iterations;
  String       potentialStr;
  int          nump, lower, higher;
  boolean      showControls, ss, sc, sp;
  EtchedBorder controlpanel   = new EtchedBorder();
  EtchedBorder etchedBorder2  = new EtchedBorder();
  EnergyGraph  energyGraph    = new EnergyGraph(this);
  Panel        panel1         = new Panel();
  Panel        potentialpanel = new Panel();
  Label        label1         = new Label();
  TextField    potentialFcn   = new TextField();
  BorderLayout borderLayout1  = new BorderLayout();
  BorderLayout borderLayout4  = new BorderLayout();
  Button       update         = new Button();
  Panel        panel3         = new Panel();
  Panel        panel4         = new Panel();
  SNumber      evalue         = new SNumber();
  Label        label2         = new Label();
  ThreadButton forward        = new ThreadButton();
  ThreadButton rewind         = new ThreadButton();
  ThreadButton frewind        = new ThreadButton();
  ThreadButton fforward       = new ThreadButton();
  SInteger     elevel         = new SInteger();
  Button       find           = new Button();
  Label        label3         = new Label();
  BorderLayout borderLayout2  = new BorderLayout();
  FlowLayout   flowLayout1    = new FlowLayout();
  FlowLayout   flowLayout2    = new FlowLayout();
  BorderLayout borderLayout5  = new BorderLayout();
  BorderLayout borderLayout3  = new BorderLayout();

  /**
   * @y.exclude
   */
  public EnergyEigenvalue() {}

  protected void setResources() {
  button_update = localProperties.getProperty("button.update", button_update);
  button_find = localProperties.getProperty("button.find", button_find);
  label_potential = localProperties.getProperty("label.potential", label_potential);
  label_energy = localProperties.getProperty("label.energy", label_energy);
  label_levels = localProperties.getProperty("label.levels", label_levels);
}


  /**
   *  @y.exclude
   */
  public void init() {
    initResources(null);
    try {
      xmin = Double.valueOf(this.getParameter("XMin", "-5")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      xmax = Double.valueOf(this.getParameter("XMax", "5")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      ymin = Double.valueOf(this.getParameter("YMin", "0")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      ymax = Double.valueOf(this.getParameter("YMax", "25")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      hBar = Double.valueOf(this.getParameter("HBarTwoM", "1")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      energy = Double.valueOf(this.getParameter("Energy", "1.0")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      potentialStr = this.getParameter("Potential", "x*x");
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      autoscalePotential = Boolean.valueOf(this.getParameter("AutoScaleY", "false")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      sc = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      ss = Boolean.valueOf(this.getParameter("ShowSpectrum", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      sp = Boolean.valueOf(this.getParameter("ShowPotential", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      sf = Boolean.valueOf(this.getParameter("ShowFunctions", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      nump = Integer.valueOf(this.getParameter("NumPts", "200")).intValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      lower = Integer.valueOf(this.getParameter("Lowest", "1")).intValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      higher = Integer.valueOf(this.getParameter("Highest", "1")).intValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      tolerance = Double.valueOf(this.getParameter("Tolerance", "1e-8")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      iterations = Integer.valueOf(this.getParameter("MaxIterations", "40")).intValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      breakvalue = Double.valueOf(this.getParameter("BreakValue", "1e12")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      sa = Boolean.valueOf(this.getParameter("ScaleToArea", "false")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    energyGraph.setShowWavefunction(sf);
    energyGraph.setAutoscalePotential(autoscalePotential);
    energyGraph.setNumpts(nump);
    energyGraph.setIterations(iterations);
    energyGraph.setTolerance(tolerance);
    energyGraph.setBreakValue(breakvalue);
    energyGraph.showSpectrum(ss);
    setShowControls(sc);
    setShowPotential(sp);
    energyGraph.scaleToArea(sa);
    energyGraph.sethBar(hBar);
    energyGraph.setEnergy(energy);
    energyGraph.setMinMaxX(xmin, xmax);
    potentialFcn.setText(potentialStr);
    energyGraph.setPotential(potentialStr);
    energyGraph.setMinMaxY(ymin, ymax);
    energyGraph.setGraphBackground(SystemColor.control);
  }

  //Component initialization
  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout2);
    potentialpanel.setLayout(borderLayout1);
    energyGraph.setLabelY(label_energy);
    energyGraph.setEnableMouse(false);
    /** j2sNative */{
    	  this.setSize(new Dimension(387, 342));
    }
    etchedBorder2.setLayout(borderLayout5);
    energyGraph.setBackground(SystemColor.control);
    energyGraph.setSampleData(false);
    energyGraph.setBorders("0,10,100,0");
    energyGraph.setAutoscaleX(false);
    energyGraph.setAutoscaleY(false);
    energyGraph.setLabelX("X");
    label1.setAlignment(1);
    label1.setText(label_potential);
    potentialFcn.setText("x*x");
    update.setLabel(button_update);
    panel3.setLayout(flowLayout2);
    panel4.setLayout(flowLayout1);
    evalue.setColumns(8);
    evalue.setValue(1.0);
    evalue.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        evalue_actionPerformed(e);
      }
    });
    label2.setAlignment(2);
    label2.setText(label_energy);
    forward.setLabel(">");
    forward.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseReleased(MouseEvent e) {
        forward_mouseReleased(e);
      }
    });
    forward.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        forward_actionPerformed(e);
      }
    });
    rewind.setLabel("<");
    rewind.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseReleased(MouseEvent e) {
        rewind_mouseReleased(e);
      }
    });
    rewind.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        rewind_actionPerformed(e);
      }
    });
    frewind.setLabel("<<");
    frewind.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseReleased(MouseEvent e) {
        frewind_mouseReleased(e);
      }
    });
    frewind.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        frewind_actionPerformed(e);
      }
    });
    fforward.setLabel(">>");
    fforward.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseReleased(MouseEvent e) {
        fforward_mouseReleased(e);
      }
    });
    fforward.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        fforward_actionPerformed(e);
      }
    });
    elevel.setValue(1);
    find.setLabel(button_find);
    label3.setAlignment(2);
    label3.setText("n =");
    borderLayout2.setHgap(20);
    flowLayout1.setHgap(1);
    flowLayout1.setAlignment(0);
    flowLayout2.setHgap(0);
    flowLayout2.setAlignment(2);
    find.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        find_actionPerformed(e);
      }
    });
    update.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        update_actionPerformed(e);
      }
    });
    controlpanel.setLayout(borderLayout4);
    this.setLayout(borderLayout3);
    controlpanel.setBackground(Color.lightGray);
    this.add(etchedBorder2, BorderLayout.CENTER);
    etchedBorder2.add(energyGraph, BorderLayout.CENTER);
    this.add(controlpanel, BorderLayout.SOUTH);
    controlpanel.add(potentialpanel, BorderLayout.NORTH);
    potentialpanel.add(potentialFcn, BorderLayout.CENTER);
    potentialpanel.add(label1, BorderLayout.WEST);
    potentialpanel.add(update, BorderLayout.EAST);
    controlpanel.add(panel1, BorderLayout.CENTER);
    panel1.add(panel4, BorderLayout.CENTER);
    panel4.add(label2, null);
    panel4.add(evalue, null);
    panel4.add(frewind, null);
    panel4.add(rewind, null);
    panel4.add(forward, null);
    panel4.add(fforward, null);
    panel1.add(panel3, BorderLayout.EAST);
    panel3.add(find, null);
    panel3.add(label3, null);
    panel3.add(elevel, null);
  }

  /**
   * Counts the number of applets on a page.
   *
   *
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
   *  Start the applet.  Do not script.
   *  @y.exclude
   */
  public void start() {
    super.start();
    if(firstTime) {
      firstTime = false;
      super.start();
      energyGraph.setOwner(this);
      energyGraph.updated = true;
      energyGraph.setTitle(null);
      energyGraph.purgeHashtable();
      energyGraph.setPotential(potentialStr);
      energyGraph.updated = false;
      findEnergyLevel(lower);
      findEnergyLevels(lower, higher);
    }
    //setPotential("step ( abs(x)-1 )",-3,3);
    //this.findEnergyLevel(3);
  }

  /**
   *  Destroy all threads and cleanup the applet.     Do not script.
   *    @y.exclude
   */
  public void destroy() {
    energyGraph.destroy();
    forward.destroy();
    rewind.destroy();
    frewind.destroy();
    fforward.destroy();
    super.destroy();
  }

  /**
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
      {"Energy", "double", "Initial energy"}, {"Potential", "String", "Potential Energy"},
      {"ShowControls", "boolean", "Show user controls"}, {"HBar", "double", "hBar value"},
      {"AutoscalePotential", "boolean", "autoscales the ys axis based on potential."},
      {"ShowPotential", "boolean", "shows or hides potential panel"}, {"Lower", "int", "lowest e level to find"},
      {"Higher", "int", "highest e level to find"}, {"NumPts", "int", "number of points to graph"},
      {"MaxIterations", "int", "number of iterations for binary search to perform"},
      {"BreakValue", "double", "max divergent value allowed value before Psi algorithm quits"},
    };
    return pinfo;
  }

  /**
   *  Gets the object identifier for a Wavefunction data source.
   *
   *  @param n the quantum number
   *
   *  @return int the identifier
   */
  public int getWavefunctionID(int n) {
    return energyGraph.getWavefunctionID(n);
  }

  /**
   * Gets the object identifier for the active wavefunction  data source.
   *
   * @return int the identifier
   */
  public int getActiveWavefunctionID() {
    return energyGraph.getActiveWavefunctionID();
  }

  /**
   * Gets the object identifier for the active state.
   *
   * The active state variables are energy (en) and quantum number (n) data source.
   *
   * @return int the identifier
   */
  public int getActiveStateID() {
    return energyGraph.getActiveStateID();
  }

  /**
   * Gets the energy of the active state.
   *
   * @return double the energy
   */
  public double getActiveEnergy() {
    return energyGraph.getActiveEnergy();
  }

  /**
   * Gets the quantum number of the active state.
   *
   * @return int the quantum number
   */
  public int getActiveQuantumNumber() {
    return energyGraph.getActiveQuantumNumber();
  }

  /**
   * Method update_actionPerformed
   *
   * @param e
   */
  void update_actionPerformed(ActionEvent e) {
    energyGraph.updated = true;
    energyGraph.setTitle(null);
    energyGraph.purgeHashtable();
    energyGraph.setPotential(potentialFcn.getText());
    energyGraph.updated = false;
  }

  /**
   * Method find_actionPerformed
   *
   * @param e
   */
  void find_actionPerformed(ActionEvent e) {
    int n = elevel.getValue();
    if(n<1) {
      elevel.setBackground(Color.red);
    } else {
      energyGraph.updated = true;
      energyGraph.setPotential(potentialFcn.getText());
      energyGraph.updated = false;
      energyGraph.resetE  = true;
      double en = energyGraph.findElevel(n, true);
      if(noscale) {}
      else {
        energyGraph.scaleData(en, true);
      }
      evalue.setValue(en);
    }
  }

  /**
   * Method fforward_actionPerformed
   *
   * @param e
   */
  void fforward_actionPerformed(ActionEvent e) {
    double ene = evalue.getValue();
    setPsi(ene);
    ene = ene+0.1;
    evalue.setValue(ene);
  }

  /**
   * Method frewind_actionPerformed
   *
   * @param e
   */
  void frewind_actionPerformed(ActionEvent e) {
    double ene = evalue.getValue();
    setPsi(ene);
    ene = ene-0.1;
    evalue.setValue(ene);
  }

  /**
   * Method forward_actionPerformed
   *
   * @param e
   */
  void forward_actionPerformed(ActionEvent e) {
    double ene = evalue.getValue();
    setPsi(ene);
    ene = ene+0.005;
    evalue.setValue(ene);
  }

  /**
   * Method rewind_actionPerformed
   *
   * @param e
   */
  void rewind_actionPerformed(ActionEvent e) {
    double ene = evalue.getValue();
    setPsi(ene);
    ene = ene-0.005;
    evalue.setValue(ene);
  }

  /**
   * Sets the Autoscale property for the energy graph.  The scale is set based on the
   * value of the potential function.
   *
   * @param as
   */
  public void setAutoscaleY(boolean as) {
    autoscalePotential = as;
    energyGraph.setAutoscalePotential(as);
  }

  /**
   * Sets the Autoscale property for the energy graph.  The scale is set based on the
   * value of the potential function.
   *
   * @param scale
   */
  public void setScaleToArea(boolean scale) {
    energyGraph.scaleToArea(scale);
  }

  /**
   * This method allows the user to hide the entire control panel at bottom of applet
   *
   * @param sc
   */
  public void setShowControls(boolean sc) {
    controlpanel.setVisible(sc);
    this.invalidate();
    this.validate();
  }

  /**
   *    Set the wave function that corresponds to a given energy.
   *
   *    @param energy       The energy.
   */
  public void setPsi(double energy) {
    energyGraph.setEnergy(energy);
    energyGraph.calculatePsi(energy);
    energyGraph.scaleData(energy, true);
    energyGraph.drawEnergyLine(energyGraph.pixFromY(energy));
  }

  /**
   *    Set the potential function and the range
   *
   *    @param str       The potential function, f(x).
   *    @param xmin     x minimum
   *    @param xmax     x maximum
   */
  public void setPotential(String str, double xmin, double xmax) {
    potentialStr = str;
    potentialFcn.setText(str);
    energyGraph.setMinMaxX(xmin, xmax);
    energyGraph.updated = true;
    energyGraph.setTitle(null);
    energyGraph.purgeHashtable();
    energyGraph.setPotential(str);
    energyGraph.updated = false;
  }

  /**
   * This method allows the user to hide just the potential setting controls of the applet
   * Useful to script in specific potentials for specific problems.
   *
   * @param sp
   */
  public void setShowPotential(boolean sp) {
    potentialpanel.setVisible(sp);
    this.invalidate();
    this.validate();
  }

  /**
   * Set the min and max values for the x axis.
   *
   * @param xmin
   * @param xmax
   */
  public void setMinMaxX(double xmin, double xmax) {
    String pot = energyGraph.potFunc.getFunctionString();
    setPotential(pot, xmin, xmax);
  }

  /**
   * Set the min and max values for the y axis.
   *
   * @param ymin
   * @param ymax
   */
  public void setMinMaxY(double ymin, double ymax) {
    energyGraph.setMinMaxY(ymin, ymax);
  }

  /**
   * Finds a sequence of energy levels and eigenfunctions.
   *
   * @param lowest
   * @param highest
   */
  public void findEnergyLevels(int lowest, int highest) {
    energyGraph.calculateLevels(lowest, highest);
  }

  /**
   * Finds a single energy level input by the user.
   *
   * @param n
   */
  public void findEnergyLevel(int n) {
    elevel.setValue(n);
    energyGraph.updated = false;
    energyGraph.resetE  = true;
    double en = energyGraph.findElevel(n, true);
    if(noscale) {}
    else {
      energyGraph.scaleData(en, true);
    }
    evalue.setValue(en);
  }

  /**
   * Method frewind_mouseReleased
   *
   * @param e
   */
  void frewind_mouseReleased(MouseEvent e) {}

  /**
   * Method rewind_mouseReleased
   *
   * @param e
   */
  void rewind_mouseReleased(MouseEvent e) {}

  /**
   * Method forward_mouseReleased
   *
   * @param e
   */
  void forward_mouseReleased(MouseEvent e) {}

  /**
   * Method fforward_mouseReleased
   *
   * @param e
   */
  void fforward_mouseReleased(MouseEvent e) {}

  /**
   * Method evalue_actionPerformed
   *
   * @param e
   */
  void evalue_actionPerformed(ActionEvent e) {
    setPsi(evalue.getValue());
  }
}
