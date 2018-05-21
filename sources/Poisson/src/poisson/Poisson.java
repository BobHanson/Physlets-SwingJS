package poisson;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.display.*;
import a2s.*;

/**
 * This applet solves Poisson's equation.  Both conductors and charge distributions
 * can be specified using "add" methods.
 *
 * @author   Wolfgang Christian
 */
public class Poisson extends SApplet {
  String                             button_oval        = "Add Oval";
  String                             button_rect        = "Add Rect";
  String                             button_reset       = "Clear";
  String                             label_rhov         = "rho|V";
  String                             label_contours     = "Contours";
  String                             label_vectors      = "Field Vectors";
  String                             label_grid         = "Grid";
  String                             label_rho          = "rho=";
  String                             label_v            = "V=";
  String                             label_e            = "|E|=";
  String                             label_calculating  = "Calculating.";
  String                             label_not_converge = "Did not converge.";
  private boolean                    showContours       = true;
  private boolean                    showFieldVectors   = true;
  private boolean                    showGrid           = false;
  //private boolean                    drawChargeDensity  = false;
  private boolean                    showControls       = true;
  private int                        gridSize           = 50;
  private String                     rangeStr;
  private boolean                    noDrag       = true;
  private Color                      defaultColor = null;
  String                             testParam, objectString;
  double                             defaultVoltage    = 10;
  edu.davidson.graphics.EtchedBorder bevelPanel1       = new EtchedBorder();
  FlowLayout                         flowLayout1       = new FlowLayout();
  Button                             addCircleBtn      = new Button();
  Button                             addRectBtn        = new Button();
  Button                             clearBtn          = new Button();
  BorderLayout                       borderLayout1     = new BorderLayout();
  PoissonPanel                       poissonPanel      = new PoissonPanel(this,50,50);
  //Panel poissonPanel = new Panel();
  SNumber                            voltageNumber     = new SNumber();
  edu.davidson.graphics.EtchedBorder bevelPanel2       = new EtchedBorder();
  Checkbox                           gridCkBox         = new Checkbox();
  Checkbox                           contoursCkBox     = new Checkbox();
  Checkbox                           fieldVectorsCkBox = new Checkbox();
  FlowLayout                         flowLayout2       = new FlowLayout();
  Checkbox                           rhoCkBox          = new Checkbox();
  //Construct the applet

  /**
   * Constructor Poisson
   * @y.exclude
   */
  public Poisson() {}

  protected void setResources() {
    button_oval        = localProperties.getProperty("button.oval", button_oval);
    button_rect        = localProperties.getProperty("button.rect", button_rect);
    button_reset       = localProperties.getProperty("button.reset", button_reset);
    label_rhov         = localProperties.getProperty("label.rhov", label_rhov);
    label_contours     = localProperties.getProperty("label.contours", label_contours);
    label_vectors      = localProperties.getProperty("label.vectors", label_vectors);
    label_grid         = localProperties.getProperty("label.grid", label_grid);
    label_calculating  = localProperties.getProperty("label.calculating", label_calculating);
    label_e            = localProperties.getProperty("label.e", label_e);
    label_v            = localProperties.getProperty("label.v", label_v);
    label_rho          = localProperties.getProperty("label.rho", label_rho);
    label_not_converge = localProperties.getProperty("label.not_converge", label_not_converge);
  }

  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    int gutterPts = 0;
    try {
      rangeStr = this.getParameter("Range", "-1,1,-1,1");
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      showContours = Boolean.valueOf(this.getParameter("ShowContours", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      showGrid = Boolean.valueOf(this.getParameter("ShowGrid", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      showFieldVectors = Boolean.valueOf(this.getParameter("ShowFieldVectors", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      gridSize = Integer.parseInt(this.getParameter("GridSize", "32"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      gutterPts = Integer.parseInt(this.getParameter("Gutter", "0"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    poissonPanel.resizeMatrices(gridSize, gridSize, gutterPts);
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    if(showControls) {
      poissonPanel.setVDisplay(voltageNumber);
      poissonPanel.setShowEOnDrag(true);
      poissonPanel.setShowVOnDrag(true);
      poissonPanel.setShowRhoOnDrag(true);
    }
    poissonPanel.setRange(rangeStr);
    poissonPanel.setShowContours(showContours);
    contoursCkBox.setState(showContours);
    poissonPanel.setShowGrid(showGrid);
    gridCkBox.setState(showGrid);
    poissonPanel.setShowFieldVectors(showFieldVectors);
    fieldVectorsCkBox.setState(showFieldVectors);
    poissonPanel.clearElements();
  }

  //Component initialization
  private void jbInit() throws Exception {
	/** j2sNative */{
      this.setSize(new Dimension(400, 500));
	}
    //this.addMouseListener(new Poisson_this_mouseAdapter(this));
    addCircleBtn.setActionCommand("addCircleBtn");
    addCircleBtn.setLabel(button_oval);
    addCircleBtn.addActionListener(new Poisson_addCircleBtn_actionAdapter(this));
    addRectBtn.setActionCommand("addRectBtn");
    addRectBtn.addActionListener(new Poisson_addRectBtn_actionAdapter(this));
    addRectBtn.setLabel(button_rect);
    clearBtn.setActionCommand("clearBtn");
    poissonPanel.addMouseListener(new Poisson_poissonPanel_mouseAdapter(this));
    voltageNumber.setValue(10.0);
    voltageNumber.addActionListener(new Poisson_voltageNumber_actionAdapter(this));
    bevelPanel2.setLayout(flowLayout2);
    gridCkBox.setLabel(label_grid);
    gridCkBox.addItemListener(new Poisson_gridCkBox_itemAdapter(this));
    contoursCkBox.setLabel(label_contours);
    contoursCkBox.addItemListener(new Poisson_contoursCkBox_itemAdapter(this));
    fieldVectorsCkBox.setLabel(label_vectors);
    rhoCkBox.setFont(new Font("Dialog", 1, 12));
    rhoCkBox.setLabel(label_rhov);
    rhoCkBox.addItemListener(new Poisson_rhoCkBox_itemAdapter(this));
    fieldVectorsCkBox.addItemListener(new Poisson_fieldVectorsCkBox_itemAdapter(this));
    //    voltageNumber.addTextListener(new Poisson_voltageNumber_textAdapter(this));
    clearBtn.addActionListener(new Poisson_clearBtn_actionAdapter(this));
    clearBtn.setLabel(button_reset);
    //    poissonPanel.addMouseMotionListener(new Poisson_poissonPanel_mouseMotionAdapter(this));
    //    poissonPanel.addMouseListener(new Poisson_poissonPanel_mouseAdapter(this));
    bevelPanel1.setLayout(flowLayout1);
    this.setLayout(borderLayout1);
    if(showControls) {
      this.add(bevelPanel1, BorderLayout.SOUTH);
    }
    bevelPanel1.add(rhoCkBox, null);
    bevelPanel1.add(voltageNumber, null);
    bevelPanel1.add(addCircleBtn, null);
    bevelPanel1.add(addRectBtn, null);
    bevelPanel1.add(clearBtn, null);
    this.add(poissonPanel, BorderLayout.CENTER);
    if(showControls) {
      this.add(bevelPanel2, BorderLayout.NORTH);
    }
    bevelPanel2.add(contoursCkBox, null);
    bevelPanel2.add(fieldVectorsCkBox, null);
    bevelPanel2.add(gridCkBox, null);
  }

  /**
   * Used by the browser when the HTML page is closed.
   *
   * Do not script.
   * @y.exclude
   */
  public void destroy() {
    poissonPanel.destroy();
    super.destroy();
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
   * Used by the browser when the HTML page is active.
   *
   * Do not script.
   * @y.exclude
   *
   */
  public void start() {
    if(firstTime) {
      firstTime = false;
      set(0, "charge", "plus,r=255,g=162,b=162");
      set(0, "charge", "minus,r=162,g=162,b=255");
    }
    super.start();
    // debugging scripts
    //int rid=addObject("charge","rectangle, h=100,w=100,x=0, y=0, v=10");
    /*
     * int rid=addObject("dielectric","circle,r=90,y=0,chi=2");
     * setRGB(rid,255,0,0);
     * setShowCharge(rid,true);
     * setDragable(rid,true);
     * setResizable(rid,false);
     */
    //int id=addObject("charge","circle, q=-5,r=30,y=0");
    //setRGB(id,64,64,255);
    //setResizable(id,false);
    //setShowCharge(id,false);
    /*
     * setTolerance(.001);
     * int rid=addObject("rectangle","h=800,w=15,x=0, y=4.95");
     * setRGB(rid,64,64,64);
     * setDragable(rid,false);
     * setResizable(rid,false);
     * //setShowCharge(rid,false);
     * setShowCharge(rid,true);
     * addObject("charge","circle,q=30,r=10,x=-3,y=0");
     * //addObject("conductor","circle,v=30,r=6,x=-3,y=0");
     */
  }

  /**
   * Add a connecting line between two objects
   *
   * @param              id1 The first id of a screen object.
   * @param              id2 The second id of a screen object.
   * @return             id that identifies the connector.
   *
   */
  public synchronized int addConnectorLine(int id1, int id2) {
    Thing t1 = poissonPanel.getThing(id1);
    Thing t2 = poissonPanel.getThing(id2);
    t1 = poissonPanel.getThing(id1);
    t2 = poissonPanel.getThing(id2);
    if((t1==null)||(t2==null)) {
      System.out.println("Invalid id. Connector line not created in Poisson.");
      return 0;
    }
    Thing t = new ConnectorLine(this, poissonPanel, t1, t2);
    if(autoRefresh) {
      poissonPanel.repaint();
    }
    return t.hashCode();
  }

  /**
   *    Add a circular conductor with a fixed voltage.  Alternative to addObject method.
   *
   *    @param x        x postion of center
   *    @param y        y postion of center
   *    @param r        radius in pixels
   *    @param v        voltage of conductor
   *
   * @return the integer indentifier for the object
   */
  public int addCircle(double x, double y, int r, double v) {
    CircleObject c = poissonPanel.addPotCircle(r, x, y, v);
    //c.setPosition(c.getXPix()+(c.getw()-r)/2,c.getYPix()+(c.geth()-r)/2);
    //c.setw(r);
    //c.seth(r);
    c.setNoDrag(noDrag);
    if(defaultColor!=null) {
      c.setColor(defaultColor);
    }
    poissonPanel.recalculate();
    return c.hashCode();
  }

  /**
   *    Add a circular charge distribution. Alternative to addObject method.
   *
   *    @param x        x postion of center
   *    @param y        y postion of center
   *    @param r        radius in pixels
   *    @param q        charge
   *
   * @return the integer indentifier for the object
   */
  public int addCircleCharge(double x, double y, int r, double q) {
    CircleObject c = poissonPanel.addPotCircle(r, x, y, q);
    //c.setPosition(c.getXPix()+(c.getw()-r)/2,c.getYPix()+(c.geth()-r)/2);
    //c.setw(r);
    //c.seth(r);
    c.setNoDrag(noDrag);
    c.setChargeType(PotentialObject.DENSITY_TYPE);
    if(defaultColor!=null) {
      c.setColor(defaultColor);
    }
    if(autoRefresh) {
      poissonPanel.recalculate();
    }
    return c.hashCode();
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
   * @return             id that identifies the object.
   */
  public synchronized int addObject(String name, String parList) {
    Thing  t      = null;
    double x      = 0;
    double y      = 0;
    int    width  = 20;
    int    height = 20;
    int    r      = 10;
    name = name.toLowerCase().trim();
    name = SUtil.removeWhitespace(name);
    String parList2 = parList.trim();
    parList = SUtil.removeWhitespace(parList);
    if(name.equals("contour")||name.equals("contours")) {
      return poissonPanel.addContours();
    } else if(name.equals("field")) {
      return poissonPanel.addField();
    } else if(name.equals("box")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "w=")) {
        width = (int) SUtil.getParam(parList, "w=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        height = (int) SUtil.getParam(parList, "h=");
      }
      t = new BoxThing(this, poissonPanel, x, y, width, height);
    } else if(name.equals("constraint")) {
      double xmin = 0, xmax = 0, ymin = 0, ymax = 0;
      if(SUtil.parameterExist(parList, "xmin=")) {
        xmin = SUtil.getParam(parList, "xmin=");
      }
      if(SUtil.parameterExist(parList, "ymin=")) {
        ymin = SUtil.getParam(parList, "ymin=");
      }
      if(SUtil.parameterExist(parList, "xmax=")) {
        xmax = SUtil.getParam(parList, "xmax=");
      }
      if(SUtil.parameterExist(parList, "ymax=")) {
        ymax = SUtil.getParam(parList, "ymax=");
      }
      t = new Constraint(this, poissonPanel, xmin, xmax, ymin, ymax);
    } else if(name.equals("chargedensity")||name.equals("charge")) {
      double q = 10;
      int    s = 20;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "w=")) {
        width = (int) SUtil.getParam(parList, "w=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        height = (int) SUtil.getParam(parList, "h=");
      }
      if(SUtil.parameterExist(parList, "q=")) {
        q = SUtil.getParam(parList, "q=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = 2*(int) SUtil.getParam(parList, "r=");  // diameter
      }
      if(SUtil.parameterExist(parList, "s=")) {
        s = (int) SUtil.getParam(parList, "s=");
      }
      if(SUtil.parameterExist(parList, "circle")) {
        CircleObject c = poissonPanel.addPotCircle(r, x, y, q);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.DENSITY_TYPE);
        t = c;
      } else if(SUtil.parameterExist(parList, "ring")||SUtil.parameterExist(parList, "shell")) {
        RingObject c = poissonPanel.addPotRing(r, s, x, y, q);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.DENSITY_TYPE);
        t = c;
      } else if(SUtil.parameterExist(parList, "box")) {
        BoxObject c = poissonPanel.addPotBox(width, height, s, x, y, q);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.DENSITY_TYPE);
        t = c;
      } else {
        RectObject c = poissonPanel.addPotRect(width, height, x, y, q);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.DENSITY_TYPE);
        t = c;
      }
      if(autoRefresh) {
        poissonPanel.recalculate();
      }
      if(defaultColor!=null) {
        t.setColor(defaultColor);
      }
      if(autoRefresh) {
        poissonPanel.repaint();
      }
      return t.hashCode();
    } else if(name.equals("conductor")) {
      double v = 10;
      int    s = 20;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "w=")) {
        width = (int) SUtil.getParam(parList, "w=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        height = (int) SUtil.getParam(parList, "h=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = 2*(int) SUtil.getParam(parList, "r=");
      }
      if(SUtil.parameterExist(parList, "s=")) {
        s = (int) SUtil.getParam(parList, "s=");
      }
      if(SUtil.parameterExist(parList, "v=")) {
        v = SUtil.getParam(parList, "v=");
      }
      if(SUtil.parameterExist(parList, "circle")) {
        CircleObject c = poissonPanel.addPotCircle(r, x, y, v);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.CONDUCTOR_TYPE);
        t = c;
      } else if(SUtil.parameterExist(parList, "ring")||SUtil.parameterExist(parList, "shell")) {
        RingObject c = poissonPanel.addPotRing(r, s, x, y, v);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.CONDUCTOR_TYPE);
        t = c;
      } else if(SUtil.parameterExist(parList, "box")) {
        BoxObject c = poissonPanel.addPotBox(width, height, s, x, y, v);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.CONDUCTOR_TYPE);
        t = c;
      } else {
        RectObject c = poissonPanel.addPotRect(width, height, x, y, v);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.CONDUCTOR_TYPE);
        t = c;
      }
      if(autoRefresh) {
        poissonPanel.recalculate();
      }
      if(defaultColor!=null) {
        t.setColor(defaultColor);
      }
      if(autoRefresh) {
        poissonPanel.repaint();
      }
      return t.hashCode();
    } else if(name.equals("dielectric")) {
      double v = 1;
      int    s = 20;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "w=")) {
        width = (int) SUtil.getParam(parList, "w=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        height = (int) SUtil.getParam(parList, "h=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = 2*(int) SUtil.getParam(parList, "r=");
      }
      if(SUtil.parameterExist(parList, "s=")) {
        s = (int) SUtil.getParam(parList, "s=");
      }
      if(SUtil.parameterExist(parList, "chi=")) {
        v = SUtil.getParam(parList, "chi=");
      }
      v = Math.abs(v);
      if(SUtil.parameterExist(parList, "circle")) {
        CircleObject c = poissonPanel.addPotCircle(r, x, y, v);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.DIELECTRIC_TYPE);
        t = c;
      } else if(SUtil.parameterExist(parList, "ring")||SUtil.parameterExist(parList, "shell")) {
        RingObject c = poissonPanel.addPotRing(r, s, x, y, v);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.DIELECTRIC_TYPE);
        t = c;
      } else if(SUtil.parameterExist(parList, "box")) {
        BoxObject c = poissonPanel.addPotBox(width, height, s, x, y, v);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.DIELECTRIC_TYPE);
        t = c;
      } else {
        RectObject c = poissonPanel.addPotRect(width, height, x, y, v);
        c.setNoDrag(noDrag);
        c.setChargeType(PotentialObject.DIELECTRIC_TYPE);
        t = c;
      }
      if(autoRefresh) {
        poissonPanel.recalculate();
      }
      if(defaultColor!=null) {
        t.setColor(defaultColor);
      }
      if(autoRefresh) {
        poissonPanel.repaint();
      }
      return t.hashCode();
    } else if(name.equals("connectorline")) {
      int   id1 = 0, id2 = 0;
      Thing t1, t2;
      if(SUtil.parameterExist(parList, "id1=")) {
        id1 = (int) SUtil.getParam(parList, "id1=");
      }
      if(SUtil.parameterExist(parList, "id2=")) {
        id2 = (int) SUtil.getParam(parList, "id2=");
      }
      t1 = poissonPanel.getThing(id1);
      t2 = poissonPanel.getThing(id2);
      t  = new ConnectorLine(this, poissonPanel, t1, t2);
    } else if(name.equals("rectangle")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "w=")) {
        width = (int) SUtil.getParam(parList, "w=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        height = (int) SUtil.getParam(parList, "h=");
      }
      t = new RectangleThing(this, poissonPanel, x, y, width, height);
    } else if(name.equals("circle")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      t = new CircleThing(this, poissonPanel, x, y, r);
    } else if(name.equals("testcharge")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      t = new TestChargeThing(this, poissonPanel, x, y, r);
    } else if(name.equals("cursor")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      t = new MarkerThing(this, poissonPanel, 2*r+1, x, y);
    } else if(name.equals("shell")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      t = new ShellThing(this, poissonPanel, x, y, r);
    } else if(name.equals("arrow")) {
      String horz = "1", vert = "1";
      int    s    = 4;                              // the size of the arrowhead.
      if(SUtil.parameterExist(parList, "s=")) {
        s = (int) SUtil.getParam(parList, "s=");
      }
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "h=")) {
        horz = SUtil.getParamStr(parList, "h=");
      }
      if(SUtil.parameterExist(parList, "v=")) {
        vert = SUtil.getParamStr(parList, "v=");
      }
      t = new ArrowThing2(this, poissonPanel, s, horz, vert, x, y);
    } else if(name.equals("text")) {
      String txt  = "";
      String calc = null;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "txt=")) {
        txt = SUtil.getParamStr(parList2, "txt=");
      }
      if(SUtil.parameterExist(parList, "text=")) {
        txt = SUtil.getParamStr(parList2, "text=");
      }
      if(SUtil.parameterExist(parList, "calc=")) {
        calc = SUtil.getParamStr(parList, "calc=");
      }
      t = new CalcThing(this, poissonPanel, txt, calc, x, y);
    } else if(name.equals("caption")) {
      String txt = "";
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "txt=")) {
        txt = SUtil.getParamStr(parList2, "txt=");
      }
      if(SUtil.parameterExist(parList, "text=")) {
        txt = SUtil.getParamStr(parList2, "text=");
      }
      t = new CaptionThing(this, poissonPanel, txt, x, y);
    } else if(name.equals("function")) {
      String func   = "0";
      String indVar = "x";
      if(SUtil.parameterExist(parList, "f=")) {
        func = SUtil.getParamStr(parList, "f=");
      }
      if(SUtil.parameterExist(parList, "var=")) {
        indVar = SUtil.getParamStr(parList, "var=");
      }
      int id = poissonPanel.contour.addFunction(indVar, func);
      if((SUtil.parameterExist(parList, "xmin="))&&(SUtil.parameterExist(parList, "xmax="))) {
        int n = 100;
        n = Math.max(poissonPanel.getSize().width/2, n);
        if(SUtil.parameterExist(parList, "n=")) {
          n = (int) SUtil.getParam(parList, "n=");
        }
        double xmin = SUtil.getParam(parList, "xmin=");
        double xmax = SUtil.getParam(parList, "xmax=");
        poissonPanel.contour.setFunctionRange(id, xmin, xmax, n);
      }
      return id;
    }
    if(t==null) {
      System.out.println("Object not created. name:"+name+"parameter list:"+parList);
      return 0;
    }
    if(defaultColor!=null) {
      t.setColor(defaultColor);
    }
    if(autoRefresh) {
      poissonPanel.repaint();
    }
    poissonPanel.addThing(t);
    return t.hashCode();
  }

  /**
   *    Add a rectangular conductor with a fixed voltage. Alternative to addObject method.
   *
   *    @param x        x postion of center
   *    @param y        y postion of center
   *    @param w        width in pixels
   *    @param h        height in pixels
   * @param q
   *
   * @return the integer indentifier for the object
   */
  public int addRectangle(double x, double y, int w, int h, double q) {
    RectObject r = poissonPanel.addPotRect(w, h, x, y, q);
    //r.setPosition(r.getXPix()+(r.getw()-w)/2,r.getYPix()+(r.geth()-h)/2);
    //r.setw(w);
    //r.seth(h);
    r.setNoDrag(noDrag);
    if(defaultColor!=null) {
      r.setColor(defaultColor);
    }
    if(autoRefresh) {
      poissonPanel.recalculate();
    }
    return r.hashCode();
  }

  /**
   *    Add a rectangular charge distribution. Alternative to addObject method.
   *
   *    @param x        x postion of center
   *    @param y        y postion of center
   *    @param w        width in pixels
   *    @param h        height in pixels
   * @param v
   *
   * @return the integer indentifier for the object
   */
  public int addRectangleCharge(double x, double y, int w, int h, double v) {
    RectObject r = poissonPanel.addPotRect(w, h, x, y, v);
    //r.setPosition(r.getXPix()+(r.getw()-w)/2,r.getYPix()+(r.geth()-h)/2);
    //r.setw(w);
    // r.seth(h);
    r.setNoDrag(noDrag);
    r.setChargeType(PotentialObject.DENSITY_TYPE);
    if(defaultColor!=null) {
      r.setColor(defaultColor);
    }
    if(autoRefresh) {
      poissonPanel.recalculate();
    }
    return r.hashCode();
  }

  //Gets Applet information

  /**
   * Method getAppletInfo
   *
   *
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Solve Poisson's equation for conductors and charge distributions. wochristian@davidson.edu";
  }

  /**
   * Changes the properies of an object.
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
    name = name.toLowerCase().trim();
    name = SUtil.removeWhitespace(name);
    String parList2 = parList.trim();
    ;
    parList = SUtil.removeWhitespace(parList);
    String str  = "false";
    double xmin = poissonPanel.contour.getMinX();
    double xmax = poissonPanel.contour.getMaxX();
    double ymin = poissonPanel.contour.getMinY();
    double ymax = poissonPanel.contour.getMaxY();
    if(name.equals("scale")) {
      if(SUtil.parameterExist(parList, "xmin=")) {
        xmin = SUtil.getParam(parList, "xmin=");
      }
      if(SUtil.parameterExist(parList, "xmax=")) {
        xmax = SUtil.getParam(parList, "xmax=");
      }
      if((SUtil.parameterExist(parList, "xmin="))||(SUtil.parameterExist(parList, "xmax="))) {
        poissonPanel.setXRange(xmin, xmax);
      }
      if(SUtil.parameterExist(parList, "ymin=")) {
        ymin = SUtil.getParam(parList, "ymin=");
      }
      if(SUtil.parameterExist(parList, "ymax=")) {
        ymax = SUtil.getParam(parList, "ymax=");
      }
      if((SUtil.parameterExist(parList, "ymin="))||(SUtil.parameterExist(parList, "ymax="))) {
        poissonPanel.setYRange(ymin, ymax);
      }
      return true;
    } else if(name.equals("charge")) {
      int r = 0, g = 0, b = 0;
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      if(SUtil.parameterExist(parList, "g=")) {
        g = (int) SUtil.getParam(parList, "g=");
      }
      if(SUtil.parameterExist(parList, "b=")) {
        b = (int) SUtil.getParam(parList, "b=");
      }
      Color newColor = new Color(r, g, b);
      if(SUtil.parameterExist(parList, "positive")||SUtil.parameterExist(parList, "plus")) {
        poissonPanel.positiveChargeColor = newColor;
      } else {
        poissonPanel.negativeChargeColor = newColor;
      }
      return true;
    } else if(name.equals("chargescale")) {
      double scale = 10;
      if(SUtil.parameterExist(parList, "scale=")) {
        scale = SUtil.getParam(parList, "scale=");
      }
      poissonPanel.inducedChargeScale = scale;
      return true;
    }
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      System.out.println("Object not created. name:"+name+"parameter list:"+parList);
      return false;
    }
    return true;
  }

  /**
   *     Forces an object to follow another object on the screen.
   *
   *     @param              masterID The id of the master object.
   *     @param              slaveID The id of the slave object.
   *     @return             true if successful.
   */
  public boolean setAnimationSlave(int masterID, int slaveID) {
    Thing master = poissonPanel.getThing(masterID);
    Thing slave  = poissonPanel.getThing(slaveID);
    if((master==null)||(slave==null)) {
      return false;
    }
    master.addSlave(slave);
    if(autoRefresh) {
      poissonPanel.repaint();
    }
    return true;
  }

  /**
   * Repaints when the system parameters are changed.
   *
   * @param ar
   */
  public void setAutoRefresh(boolean ar) {
    autoRefresh = ar;
    poissonPanel.setAutoRefresh(ar);
  }

  /**
   * Method setBtnOff
   *
   */
  void setBtnOff() {
    addCircleBtn.setForeground(Color.black);
    addRectBtn.setForeground(Color.black);
  }

  /**
   *    Set the caption.
   *
   *    @param caption        The applet caption.
   */
  public void setCaption(String caption) {
    poissonPanel.setCaption(caption);
  }

  /**
   * Sets the connector line  between two objects. Connectors are visual objects that are drawn
   * on the screen. For example, a line connecting the centers of two rectangles.
   *
   * @param              id the id of the connector object.
   * @param              id1 the id of the first object.
   * @param              id2 the id of the second object.
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setConnector(int id, int id1, int id2) {
    Thing t  = poissonPanel.getThing(id);
    Thing t1 = poissonPanel.getThing(id1);
    Thing t2 = poissonPanel.getThing(id2);
    if((t==null)||(t1==null)||(t2==null)) {
      return false;
    }
    if(!(t instanceof ConnectorLine)) {
      return false;
    }
    ((ConnectorLine) t).setConnectorIDs(t1, t2);
    return true;
  }

  /**
   * Sets a motion constraint on an object.
   *
   * @param              id the ID of the object.
   * @param              constraintID the ID of the constraint.
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setConstraint(int id, int constraintID) {
    Thing t = poissonPanel.getThing(id);
    Thing c = poissonPanel.getThing(constraintID);
    if(t==null) {
      return false;
    }
    if(c==null) {
      return false;
    }
    if(!(c instanceof Constraint)) {
      return false;
    }
    t.setConstraint((Constraint) c);
    return true;
  }

  /**
   *    Sets the color for all subsequent drawing.
   *
   *    @param r        0<=red<=255
   *    @param g        0<=green<=255
   *    @param b        0<=blue<=255
   */
  public void setDefaultRGB(int r, int g, int b) {
    defaultColor = new Color(r, g, b);
  }

  /**
   *    Sets the default values: grid=false, field vectors=false,contours=true,
   *    drag=false, no E and no V on mouse drag.
   */
  public void setDefault() {
    this.deleteDataConnections();
    setShowFieldVectors(false);
    setShowContours(true);
    setShowGrid(false);
    noDrag       = true;
    defaultColor = null;
    poissonPanel.setDefault();
  }

  /**
   * Offset the object's position on the screen from its default drawing
   * position.
   *
   * @param              id The id of the object.
   * @param xOff
   * @param yOff
   * @return             True if successful.
   */
  public boolean setDisplayOffset(int id, int xOff, int yOff) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    t.setDisplayOff(xOff, yOff);
    if(autoRefresh) {
      poissonPanel.repaint();
    }
    return true;
  }

  /**
   *    Sets the dragable property for all subsequent objects.
   *
   * @param drag
   */
  public void setDefaultDragable(boolean drag) {
    noDrag = !drag;
  }

  /**
   *    Turn field vectors off and on.
   *
   * @param isShowField
   */
  public void setShowFieldVectors(boolean isShowField) {
    fieldVectorsCkBox.setState(isShowField);
    showFieldVectors = isShowField;
    poissonPanel.setShowFieldVectors(isShowField);
  }

  /**
   *    Shows the induced charge.
   *
   * @param              id the id of the object.
   * @param              isShowCharge property
   *
   * @return true if successful
   */
  public boolean setShowCharge(int id, boolean isShowCharge) {
    Thing t = poissonPanel.getThing(id);
    if(id==0) {
      poissonPanel.setShowCharge(isShowCharge);
      return true;
    }
    if(t==null) {
      return false;
    }
    if(!(t instanceof PotentialObject)) {
      return false;
    }
    PotentialObject p = (PotentialObject) t;
    p.setShowCharge(isShowCharge);
    if(autoRefresh) {
      poissonPanel.repaint();
    }
    return true;
  }

  /**
   *    Shows the contour lines?
   *
   * @param isShowContours
   */
  public void setShowContours(boolean isShowContours) {
    contoursCkBox.setState(isShowContours);
    poissonPanel.setShowContours(isShowContours);
    showContours = isShowContours;
  }

  /**
   * Turns grid off and on.
   *
   * @param isShowGrid
   */
  public void setShowGrid(boolean isShowGrid) {
    gridCkBox.setState(isShowGrid);
    poissonPanel.setShowGrid(isShowGrid);
    showGrid = isShowGrid;
  }

  /**
   * Makes an object dragable.
   *
   * @param              id The ID of the object.
   * @param isDragable
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setDragable(int id, boolean isDragable) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    t.setDragable(isDragable);
    return true;
  }

  /**
   *   Makes an object resizable.
   *
   *   @param              id the ID of the object.
   * @param isResizable
   *
   *   @return             <code>true</code> if successful.
   */
  public boolean setResizable(int id, boolean isResizable) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    t.setResizable(isResizable);
    return true;
  }

  /**
   * Makes the charge rather than the voltage on this conductor constant.
   * At present, this property can only be set for a SINGLE conductor.
   *
   * @param              id the object identifier.
   * @param              q the charge value
   * @param              isConstant <code>true</code> if charge should be held constant.
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setConstantQ(int id, double q, boolean isConstant) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    if(!(t instanceof PotentialObject)) {
      return false;
    }
    PotentialObject p = (PotentialObject) t;
    p.setConstantQ(q, isConstant);
    if(autoRefresh) {
      poissonPanel.recalculate();
    }
    return true;
  }

  /**
   * Sets the voltage on the conductor.
   *
   * @param              id the object identifier.
   * @param v
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setVoltage(int id, double v) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    if(!(t instanceof PotentialObject)) {
      return false;
    }
    PotentialObject p = (PotentialObject) t;
    p.setVoltage(v);
    if(autoRefresh) {
      poissonPanel.recalculate();
    }
    return true;
  }

  /**
   * Gets the x position.
   *
   * @param              id the object identifier.
   *
   * @return             <code>true</code> if successful.
   */
  public double getXPos(int id) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return 0;
    }
    return t.getX();
  }

  /**
   * Gets the y position.
   *
   * @param              id the object identifier.
   *
   * @return             <code>true</code> if successful.
   */
  public double getYPos(int id) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return 0;
    }
    return t.getY();
  }

  /**
   * Sets the x position.
   *
   * @param              id the object identifier.
   * @param              x the x position
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setXPos(int id, double x) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    t.setX(x);
    t.updateMySlaves();
    if(autoRefresh&&(t instanceof PotentialObject)) {
      poissonPanel.recalculate();
    }
    return true;
  }

  /**
   * Sets the y position.
   *
   * @param              id the object identifier.
   * @param y
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setYPos(int id, double y) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    t.setY(y);
    t.updateMySlaves();
    if(autoRefresh&&(t instanceof PotentialObject)) {
      poissonPanel.recalculate();
    }
    return true;
  }

  /**
   * Sets the position.
   *
   * @param              id the object identifier.
   * @param              x the x position
   * @param y
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setXYPos(int id, double x, double y) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    t.setX(x);
    t.setY(y);
    t.updateMySlaves();
    if(autoRefresh&&(t instanceof PotentialObject)) {
      poissonPanel.recalculate();
    }
    return true;
  }

  /**
   * Sets the dielectric permittivity.
   *
   * @param              id the object identifier.
   * @param              chi the permittivity
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setChi(int id, double chi) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    if(!(t instanceof PotentialObject)) {
      return false;
    }
    PotentialObject p = (PotentialObject) t;
    p.setChi(chi);
    if(autoRefresh) {
      poissonPanel.recalculate();
    }
    return true;
  }

  /**
   * Sets the charge density.
   *
   * @param              id the object identifier.
   * @param              q the charge value
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setChargeDensity(int id, double q) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    if(!(t instanceof PotentialObject)) {
      return false;
    }
    PotentialObject p = (PotentialObject) t;
    p.setChargeDensity(q);
    if(autoRefresh) {
      poissonPanel.recalculate();
    }
    return true;
  }

  /**
   * Makes an object visible.
   *
   * @param              id The ID of the object.
   * @param isVisible
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setVisibility(int id, boolean isVisible) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    t.setVisible(isVisible);
    if(t instanceof FieldThing) {
      this.setShowFieldVectors(isVisible);
    }
    if(t instanceof ContourThing) {
      this.setShowContours(isVisible);
    }
    if(autoRefresh) {
      poissonPanel.repaint();
    }
    return true;
  }

  /**
   *    Sets the resoltion for the field vectors.
   *
   *    Default is 2.
   *
   *    @param r        the resolution
   *
   */
  public void setFieldResolution(int r) {
    poissonPanel.setFieldResolution(r);
  }

  /**
   *    Sets a gutter of grid points outside the viewing region.  Can be used to minimize the conducting boundary.
   *
   *    @param g        the gutter
   *
   */
  public void setGutter(int g) {
    poissonPanel.setGutter(g);
  }

  /**
   * change the object's font for any text that is displayed.
   *
   * @param              id The id of the object.
   * @param              family The font family: Helvetica, Times.
   * @param              style The style, 0=plain, 1=bold.
   * @param              size The size of the font;
   * @return             True if successful.
   */
  public boolean setFont(int id, String family, int style, int size) {
    Font  font = new Font(family, style, size);
    //if(id==this.animatorCanvas.hashCode()){animatorCanvas.font=font; return true;}
    Thing t    = poissonPanel.getThing(id);
    if((t==null)||(font==null)) {
      return false;
    }
    t.setFont(font);
    if(poissonPanel.autoRefresh) {
      poissonPanel.repaint();
    }
    return true;
  }

  /**
   * Sets the object's font if the object has text that can be displayed.
   *
   * @param              id The id of the object.
   * @param              family The font family: Helvetica, Times.
   * @param              style The style, 0=plain, 1=bold.
   * @param              size The size of the font;
   * @return             true if successful.
   */
  public boolean setObjectFont(int id, String family, int style, int size) {
    return setFont(id, family, style, size);
  }

  /**
   * change the object's numerical format for any numbers that is displayed.
   *
   * @param              id The id of the object.
   * @param              format a string using unix printf conventions.
   * @return             true if successful.
   */
  public boolean setFormat(int id, String format) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      return false;
    }
    t.setFormat(format);
    if(poissonPanel.autoRefresh) {
      poissonPanel.repaint();
    }
    return true;
  }

  /**
   *         Display a message in the yellow message box.
   *
   *         @param              msg Message to display after the animation stops.
   */
  public void setMessage(String msg) {
    poissonPanel.setMessage(msg);
  }

  /**
   *       Sets the maximum number of inerations for the Poisson solver.  Default is on part in 50.
   *
   * @param m
   */
  public void setMaxIterations(int m) {
    poissonPanel.setMaxIterations(m);
  }

  /**
   *       Sets the tolerance of the Poisson solver.  Default is on part in 1.0e-3.
   *
   *       @param              tol
   */
  public void setTolerance(double tol) {
    poissonPanel.setTolerance(tol);
  }

  /**
   *    Sets the drawing to chargedensity mode or conductor mode in the UI.
   *
   *    @param isChargeMode        <code>true</code> if chargedensity mode and <code>false</code>  otherwise
   *
   */
  public void setDrawChargeDensity(boolean isChargeMode) {
    rhoCkBox.setState(isChargeMode);
    if(isChargeMode) {
      poissonPanel.setDefaultChargeType(PotentialObject.DENSITY_TYPE);
    } else {
      poissonPanel.setDefaultChargeType(PotentialObject.CONDUCTOR_TYPE);
    }
    //drawChargeDensity = isChargeMode;
  }

  /**
   *    Sets the scale using the horizontal axis. The leftmost pixel will be xmin.
   *    The rightmost pixel will be xmax.  The vertical axis will be rescaled to maintian
   *    an aspect ratio of one.
   *
   *    @param xmin        Minimuum value on x axis.
   *    @param xmax        Maximum value on x axis.
   */
  public void setXRange(double xmin, double xmax) {
    poissonPanel.setXRange(xmin, xmax);
  }

  /**
   * Method setYRange
   *
   * @param ymin
   * @param ymax
   */
  public void setYRange(double ymin, double ymax) {
    poissonPanel.setYRange(ymin, ymax);
  }

  /**
   *    Show the magnitude of the electric field when the mouse is dragged.
   *
   * @param isShowEOnDrag
   */
  public void setShowEOnDrag(boolean isShowEOnDrag) {
    poissonPanel.setShowEOnDrag(isShowEOnDrag);
  }

  /**
   *    Show the charge density when the mouse is dragged.
   *
   *    @param isShowRhoOnDrag        Show charge density on drag?
   */
  public void setShowRhoOnDrag(boolean isShowRhoOnDrag) {
    poissonPanel.setShowRhoOnDrag(isShowRhoOnDrag);
  }

  /**
   *    Show the charge voltage when the mouse is dragged.
   *
   * @param isShowVOnDrag
   */
  public void setShowVOnDrag(boolean isShowVOnDrag) {
    poissonPanel.setShowVOnDrag(isShowVOnDrag);
  }

  /**
   * Sets the color for an object.  Currently works for test charges and poles.
   *
   * @param              id The ID of the object.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   *
   * @return             True if successful.
   */
  public boolean setRGB(int id, int r, int g, int b) {
    Thing t = poissonPanel.getThing(id);
    if(t==null) {
      poissonPanel.contour.setObjectColor(id, new Color(r, g, b));
      return false;
    }
    t.setColor(new Color(r, g, b));
    if(autoRefresh) {
      poissonPanel.repaint();
    }
    return true;
  }

  /**
   * Gets the object identifier for the potential array.
   *
   *
   * @return   the integer identifier.
   */
  public int getPotentialArrayID() {
    return poissonPanel.getPotentialArrayID();
  }

  /**
   * Gets the object identifier for the charge density.
   *
   *
   * @return   the integer identifier.
   */
  public int getChargeArrayID() {
    return poissonPanel.getChargeArrayID();
  }

  /**
   * Gets the object identifier for ensemble of objects in the poisson applet.
   * The ensemble is made up of conductors, dielectrics, and charge densities.
   *
   *
   * @return   the integer identifier.
   */
  public int getEnsembleID() {
    return poissonPanel.getID();
  }

  /**
   * Method getParameterInfo
   *
   *
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String pinfo[][] = {
      {"ShowControls", "boolean", "Show controls at bottom of applet."}, {"Range", "String", "xmin, xmax, ymin, ymax"},
      {"GridSize", "int", "Number of grid points along x."},
    };
    return pinfo;
  }

  /**
   * Method addCircleBtn_actionPerformed
   *
   * @param e
   */
  void addCircleBtn_actionPerformed(ActionEvent e) {
    if(addCircleBtn.getForeground()==Color.blue) {
      setNull();
      setBtnOff();
    } else {
      objectString = "circle";
      poissonPanel.setObjectString(objectString);
      poissonPanel.makeActiveElementNull();
      poissonPanel.setDefaultVoltate(voltageNumber.getValue());
      setBtnOff();
      addCircleBtn.setForeground(Color.blue);
    }
  }

  /**
   * Method addRectBtn_actionPerformed
   *
   * @param e
   */
  void addRectBtn_actionPerformed(ActionEvent e) {
    if(addRectBtn.getForeground()==Color.blue) {
      setNull();
      setBtnOff();
    } else {
      objectString = "rect";
      poissonPanel.setObjectString(objectString);
      poissonPanel.makeActiveElementNull();
      poissonPanel.setDefaultVoltate(voltageNumber.getValue());
      setBtnOff();
      addRectBtn.setForeground(Color.blue);
    }
  }

  //void this_mousePressed(MouseEvent e){}

  /**
   * Method clearBtn_actionPerformed
   *
   * @param e
   */
  void clearBtn_actionPerformed(ActionEvent e) {
    poissonPanel.clearElements();
  }

  /**
   * Method voltageNumber_actionPerformed
   *
   * @param e
   */
  void voltageNumber_actionPerformed(ActionEvent e) {
    poissonPanel.setActiveElementVoltage(voltageNumber.getValue());
  }

  /**
   * Method setNull
   *
   */
  void setNull() {
    objectString = null;
    poissonPanel.setObjectString(objectString);
    setBtnOff();
  }

  /**
   * Method fieldVectorsCkBox_itemStateChanged
   *
   * @param e
   */
  void fieldVectorsCkBox_itemStateChanged(ItemEvent e) {
    if(e.getStateChange()==ItemEvent.SELECTED) {
      poissonPanel.setShowFieldVectors(true);
      showFieldVectors = true;
    } else {
      poissonPanel.setShowFieldVectors(false);
      showFieldVectors = false;
    }
  }

  /**
   * Method contoursCkBox_itemStateChanged
   *
   * @param e
   */
  void contoursCkBox_itemStateChanged(ItemEvent e) {
    if(e.getStateChange()==ItemEvent.SELECTED) {
      poissonPanel.setShowContours(true);
      showContours = true;
    } else {
      poissonPanel.setShowContours(false);
      showContours = false;
    }
  }

  /**
   * Method gridCkBox_itemStateChanged
   *
   * @param e
   */
  void gridCkBox_itemStateChanged(ItemEvent e) {
    if(e.getStateChange()==ItemEvent.SELECTED) {
      poissonPanel.setShowGrid(true);
      showGrid = true;
    } else {
      poissonPanel.setShowGrid(false);
      showGrid = false;
    }
  }

  /**
   * Method poissonPanel_mouseReleased
   *
   * @param e
   */
  void poissonPanel_mouseReleased(MouseEvent e) {
    setNull();
  }

  /**
   * Method rhoCkBox_itemStateChanged
   *
   * @param e
   */
  void rhoCkBox_itemStateChanged(ItemEvent e) {
    if(e.getStateChange()==ItemEvent.SELECTED) {
      poissonPanel.setDefaultChargeType(PotentialObject.DENSITY_TYPE);
      //drawChargeDensity = true;
    } else {
      poissonPanel.setDefaultChargeType(PotentialObject.CONDUCTOR_TYPE);
      //drawChargeDensity = false;
    }
  }
}

/**
 * Class Poisson_addCircleBtn_actionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_addCircleBtn_actionAdapter implements java.awt.event.ActionListener {

  Poisson adaptee;

  /**
   * Constructor Poisson_addCircleBtn_actionAdapter
   *
   * @param adaptee
   */
  Poisson_addCircleBtn_actionAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method actionPerformed
   *
   * @param e
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.addCircleBtn_actionPerformed(e);
  }
}

/**
 * Class Poisson_addRectBtn_actionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_addRectBtn_actionAdapter implements java.awt.event.ActionListener {

  Poisson adaptee;

  /**
   * Constructor Poisson_addRectBtn_actionAdapter
   *
   * @param adaptee
   */
  Poisson_addRectBtn_actionAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method actionPerformed
   *
   * @param e
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.addRectBtn_actionPerformed(e);
  }
}

/**
 * Class Poisson_clearBtn_actionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_clearBtn_actionAdapter implements java.awt.event.ActionListener {

  Poisson adaptee;

  /**
   * Constructor Poisson_clearBtn_actionAdapter
   *
   * @param adaptee
   */
  Poisson_clearBtn_actionAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method actionPerformed
   *
   * @param e
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.clearBtn_actionPerformed(e);
  }
}

/*
 * class Poisson_voltageNumber_textAdapter implements java.awt.event.TextListener{
 * Poisson adaptee;
 *
 * Poisson_voltageNumber_textAdapter(Poisson adaptee) {
 *   this.adaptee = adaptee;
 * }
 *
 * public void textValueChanged(TextEvent e) {
 *   adaptee.voltageNumber_textValueChanged(e);
 * }
 * }
 */

/**
 * Class Poisson_voltageNumber_actionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_voltageNumber_actionAdapter implements java.awt.event.ActionListener {

  Poisson adaptee;

  /**
   * Constructor Poisson_voltageNumber_actionAdapter
   *
   * @param adaptee
   */
  Poisson_voltageNumber_actionAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method actionPerformed
   *
   * @param e
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.voltageNumber_actionPerformed(e);
  }
}

/**
 * Class Poisson_fieldVectorsCkBox_itemAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_fieldVectorsCkBox_itemAdapter implements java.awt.event.ItemListener {

  Poisson adaptee;

  /**
   * Constructor Poisson_fieldVectorsCkBox_itemAdapter
   *
   * @param adaptee
   */
  Poisson_fieldVectorsCkBox_itemAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method itemStateChanged
   *
   * @param e
   */
  public void itemStateChanged(ItemEvent e) {
    adaptee.fieldVectorsCkBox_itemStateChanged(e);
  }
}

/**
 * Class Poisson_contoursCkBox_itemAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_contoursCkBox_itemAdapter implements java.awt.event.ItemListener {

  Poisson adaptee;

  /**
   * Constructor Poisson_contoursCkBox_itemAdapter
   *
   * @param adaptee
   */
  Poisson_contoursCkBox_itemAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method itemStateChanged
   *
   * @param e
   */
  public void itemStateChanged(ItemEvent e) {
    adaptee.contoursCkBox_itemStateChanged(e);
  }
}

/**
 * Class Poisson_gridCkBox_itemAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_gridCkBox_itemAdapter implements java.awt.event.ItemListener {

  Poisson adaptee;

  /**
   * Constructor Poisson_gridCkBox_itemAdapter
   *
   * @param adaptee
   */
  Poisson_gridCkBox_itemAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method itemStateChanged
   *
   * @param e
   */
  public void itemStateChanged(ItemEvent e) {
    adaptee.gridCkBox_itemStateChanged(e);
  }
}

/**
 * Class Poisson_poissonPanel_mouseAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_poissonPanel_mouseAdapter extends java.awt.event.MouseAdapter {

  Poisson adaptee;

  /**
   * Constructor Poisson_poissonPanel_mouseAdapter
   *
   * @param adaptee
   */
  Poisson_poissonPanel_mouseAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method mouseReleased
   *
   * @param e
   */
  public void mouseReleased(MouseEvent e) {
    adaptee.poissonPanel_mouseReleased(e);
  }
}

/**
 * Class Poisson_rhoCkBox_itemAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class Poisson_rhoCkBox_itemAdapter implements java.awt.event.ItemListener {

  Poisson adaptee;

  /**
   * Constructor Poisson_rhoCkBox_itemAdapter
   *
   * @param adaptee
   */
  Poisson_rhoCkBox_itemAdapter(Poisson adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method itemStateChanged
   *
   * @param e
   */
  public void itemStateChanged(ItemEvent e) {
    adaptee.rhoCkBox_itemStateChanged(e);
  }
}
