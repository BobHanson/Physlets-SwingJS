/*
 *
 *
 *                      Class  bField.BField
 *
 *
 *
 * class BField extends Physlet
 *
 * The main entry point and BField applet.
 *
 */

/**
 * Calcualte and draw magnetic fields from wires and loops.  An external field may also be specified.
 *
 * @author   Wolfgang Christian
 * @version  $Revision: 1.0a $, $Date: 1998/07/30 08:00:00 $
 */
package bfield;


import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.SUtil;
import edu.davidson.display.*;

/**
 * Class BField
 *
 * @author W. Christian
 */
public class BField extends SApplet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String       button_reset          = "Clear";
  String       button_currentin      = "Current In";
  String       button_currentout     = "Current Out";
  String       label_newfield        = "New Field: Bx By";
  String       label_coil            = "Coil";
  String       label_vectors         = "Vectors";
  String       label_doubleclick     = "Double Click";
  String       label_force_undefined = "F=Undefined";
  String       label_field_undefined = "B=Undefined inside wire.";
  Color        wireColor             = null;
  String       wireLabel             = null;
  boolean      hideWire              = false;
  boolean      showControls          = true;
  boolean      showFieldVectors      = true;
  boolean      coilMode              = false;
  String       rangeStr              = "-1.0,1.0,-1.0,1.0";
  int          gridSize;
  EtchedBorder etchedBorder1 = new EtchedBorder();
  EtchedBorder etchedBorder2 = new EtchedBorder();
  Button       updateBtn     = new Button();
  TextField    bxField       = new TextField();
  TextField    byField       = new TextField();
  Panel        panel1        = new Panel();
  GridLayout   gridLayout1   = new GridLayout();
  EtchedBorder etchedBorder3 = new EtchedBorder();
  Checkbox     lineCkBox     = new Checkbox();
  Checkbox     fieldVecCkBox = new Checkbox();
  Panel        panel2        = new Panel();
  Button       inBtn         = new Button();
  Button       outBtn        = new Button();
  BorderLayout borderLayout1 = new BorderLayout();
  Panel        panel3        = new Panel();
  FieldPanel   fieldPanel    = new FieldPanel(this);
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  Button       clearBtn      = new Button();
  String       bxStr         = "0";
  String       byStr         = "0";
  Checkbox     coilCkBox     = new Checkbox();

  /**
   * Constructor BField
   *
   */
  public BField() {}

  protected void setResources() {
    button_reset          = localProperties.getProperty("button.reset", button_reset);
    button_currentin      = localProperties.getProperty("button.currentin", button_currentin);
    button_currentout     = localProperties.getProperty("button.currentout", button_currentout);
    label_newfield        = localProperties.getProperty("label.newfield", label_newfield);
    label_coil            = localProperties.getProperty("label.coil", label_coil);
    label_vectors         = localProperties.getProperty("label.vectors", label_vectors);
    label_doubleclick     = localProperties.getProperty("label.doubleclick", label_doubleclick);
    label_force_undefined = localProperties.getProperty("label.force_undefined", label_force_undefined);
    label_field_undefined = localProperties.getProperty("label.field_undefined", label_field_undefined);
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
  public void init() {
    initResources(null);
    try {
      bxStr = this.getParameter("BxFunction", "0");
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      byStr = this.getParameter("ByFunction", "0");
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
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
      rangeStr = this.getParameter("Range", "-1,1,-1,1");
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    bxField.setText(bxStr);
    byField.setText(byStr);
    etchedBorder1.setVisible(showControls);
    fieldPanel.setGridSize(gridSize);
    fieldPanel.setShowFieldVectors(showFieldVectors);
    fieldVecCkBox.setState(showFieldVectors);
    fieldPanel.setRange(rangeStr);
    //fieldPanel.setBFunctions(bxStr,byStr); // do this in the start method.
    if(showControls) {
      fieldPanel.setShowBOnDrag(true);
    }
  }

  private void jbInit() throws Exception {
    etchedBorder2.setLayout(borderLayout4);
    /** j2sNative */{
      this.setSize(new Dimension(505, 457));
    }
    updateBtn.setActionCommand("newB");
    updateBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        updateBtn_actionPerformed(e);
      }
    });
    updateBtn.setLabel(label_newfield);
    bxField.setText("textField1");
    byField.setText("textField2");
    gridLayout1.setColumns(2);
    lineCkBox.setLabel(label_doubleclick);
    lineCkBox.addItemListener(new java.awt.event.ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        lineCkBox_itemStateChanged(e);
      }
    });
    fieldVecCkBox.setState(true);
    fieldVecCkBox.addItemListener(new java.awt.event.ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        fieldVecCkBox_itemStateChanged(e);
      }
    });
    fieldVecCkBox.setLabel(label_vectors);
    inBtn.setLabel(button_currentin);
    inBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        inBtn_actionPerformed(e);
      }
    });
    outBtn.setLabel(button_currentout);
    outBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        outBtn_actionPerformed(e);
      }
    });
    clearBtn.setLabel(button_reset);
    coilCkBox.setLabel(label_coil);
    coilCkBox.addItemListener(new java.awt.event.ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        coilCkBox_itemStateChanged(e);
      }
    });
    clearBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        clearBtn_actionPerformed(e);
      }
    });
    etchedBorder3.setLayout(borderLayout1);
    panel1.setLayout(gridLayout1);
    etchedBorder1.setLayout(borderLayout3);
    this.setLayout(borderLayout2);
    this.add(etchedBorder1, BorderLayout.NORTH);
    etchedBorder1.add(etchedBorder2, BorderLayout.NORTH);
    etchedBorder2.add(updateBtn, BorderLayout.WEST);
    etchedBorder2.add(panel1, BorderLayout.CENTER);
    panel1.add(bxField, null);
    panel1.add(byField, null);
    etchedBorder1.add(etchedBorder3, BorderLayout.SOUTH);
    etchedBorder3.add(panel2, BorderLayout.EAST);
    panel2.add(clearBtn, null);
    panel2.add(inBtn, null);
    panel2.add(outBtn, null);
    etchedBorder3.add(panel3, BorderLayout.CENTER);
    panel3.add(coilCkBox, null);
    panel3.add(fieldVecCkBox, null);
    panel3.add(lineCkBox, null);
    this.add(fieldPanel, BorderLayout.CENTER);
  }

  /**
   * Gets information about this applet.
   * @return the information string
   */
  public String getAppletInfo() {
    return "BField by W. Christian. Email:wochristian@davidson.edu";
  }

  /**
   * Gets information about the embedding parameters.
   *
   * @return the parameter information
   */
  public String[][] getParameterInfo() {
    String pinfo[][] = {
      {"BxFunction", "String", "B field x component"}, {"ByFunction", "String", "B field y component"},
      {"ShowControls", "boolean", "Show interactive controls on screen"},
      {"GridSize", "int", "Grid size for calculations"},
    };
    return pinfo;
  }

  /**
   *    Add add  a Wire.
   *
   *    @param x        The x position.
   *    @param y        The y position.
   *    @param current  The current.
   *
   *    @return         The id for the object that was created.  Can be used later to set object properties.
   *
   *    @see #addCoil
   *    @see #setBFunctions
   */
  public synchronized int addWire(double x, double y, double current) {
    Wire w = fieldPanel.addWire(x, y, current);
    //w.hideWire=hideWire;
    w.setVisible(!hideWire);
    if(wireLabel != null) {
      w.setLabel(wireLabel);
    }
    if(wireColor != null) {
      w.setColor(wireColor);
    }
    return w.getID();
  }

  /**
   *   Add add  a single coil.
   *
   *   A cross section of the coil is drawn.
   *
   *    @param x        The x position.
   *    @param y        The y position.
   *    @param current  The current.
   *
   *    @return         The id for the object that was created.  Can be used later to set object properties.
   *
   *    @see #addWire
   *    @see #setBFunctions
   */
  public synchronized int addCoil(double x, double y, double current) {
    Wire w = fieldPanel.addCoil(x, y, current);
    //w.hideWire=hideWire;
    w.setVisible(!hideWire);
    if(wireLabel != null) {
      w.setLabel(wireLabel);
    }
    if(wireColor != null) {
      w.setColor(wireColor);
    }
    return w.getID();
  }

  /**
   * Creates an object and adds it to the Physlet.
   * The first argument is the name of the object to be added and the second is a
   * comma-delimited list of parameters.  For example, a circle can be added a follows:
   * <p>
   * <code>addObject ("circle", "x = 0, y = -1.0, r = 10");</code>
   * </p>
   * See the supplemental documentation for a list of
   * <a href="doc-files/bfield_addobject.html">object names and parameters.</a>
   *
   * @param              name the type of object to be created.
   * @param              parList a list of parameters to be set
   * @return             id that identifies the object.
   */
  public synchronized int addObject(String name, String parList) {
    if(destroyed) {
      return 0;
    }
    Thing  t      = null;
    double x      = 0;
    double y      = 0;
    int    width  = 20;
    int    height = 20;
    int    r      = 10;
    int    s      = 10;
    name = name.toLowerCase().trim();
    name = SUtil.removeWhitespace(name);
    String parList2 = parList.trim();
    ;
    parList = SUtil.removeWhitespace(parList);
    if(name.equals("field")) {
      return fieldPanel.addField().hashCode();
    } else if(name.equals("wire")) {
      double current = 1;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "i=")) {
        current = SUtil.getParam(parList, "i=");
      }
      int id = addWire(x, y, current);
      t = fieldPanel.getThing(id);
      if(SUtil.parameterExist(parList, "s=")) {
        s = (int) SUtil.getParam(parList, "s=");
        t.setSize(s);
      }
      if (SUtil.parameterExist(parList, "dx=") && SUtil.parameterExist(parList, "num=") ) {
        double dx = SUtil.getParam(parList, "dx=");
        int num = (int) SUtil.getParam(parList, "num=");
        s=t.getSize();
        for(int i=0; i<num-1; i++){
          x+=dx;
          id = addWire(x, y, current);
          t = fieldPanel.getThing(id);
          t.setSize(s);
        }
      }

      return id;
    } else if(name.equals("coil")) {
      double current = 1;
      double rad     = 0.5;
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "i=")) {
        current = SUtil.getParam(parList, "i=");
      }
      int id = addCoil(x, y, current);
      t = fieldPanel.getThing(id);
      if(SUtil.parameterExist(parList, "r=")) {
        rad = SUtil.getParam(parList, "r=");
        setRadius(id, rad);
      }
      if(SUtil.parameterExist(parList, "s=")) {
        s = (int) SUtil.getParam(parList, "s=");
        t.setSize(s);
      }
      if (SUtil.parameterExist(parList, "dx=") && SUtil.parameterExist(parList, "num=")) {
        double dx = SUtil.getParam(parList, "dx=");
        int num = (int) SUtil.getParam(parList, "num=");
        rad=((Wire) t).getRadius();
        s = t.getSize();
        for (int i = 0; i < num - 1; i++) {
          x += dx;
          id = addCoil(x, y, current);
          t = fieldPanel.getThing(id);
          t.setSize(s);
          setRadius(id, rad);
        }
      }
      return id;
    } else if(name.equals("compass")) {
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "r=")) {
        r = (int) SUtil.getParam(parList, "r=");
      }
      t = new CompassThing(this, fieldPanel, x, y, r);
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
      t = new FieldBox(this, fieldPanel, x, y, width, height);
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
      t = new FieldRectangle(this, fieldPanel, x, y, width, height);
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
      t = new FieldCircle(this, fieldPanel, x, y, r);
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
      t = new FieldCursor(this, fieldPanel, x, y, 2 * r + 1);
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
      t = new FieldShell(this, fieldPanel, x, y, r);
    } else if(name.equals("arrow")) {
      String horz = "1", vert = "1";
      s = 4;  // the size of the arrowhead
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
      t = new FieldArrow(this, fieldPanel, s, horz, vert, x, y);
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
      t = new CalcThing(this, fieldPanel, txt, calc, x, y);
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
      t = new CaptionThing(this, fieldPanel, txt, x, y);
    } else if(name.equals("image")) {
      String file = " ";
      if(SUtil.parameterExist(parList, "x=")) {
        x = SUtil.getParam(parList, "x=");
      }
      if(SUtil.parameterExist(parList, "y=")) {
        y = SUtil.getParam(parList, "y=");
      }
      if(SUtil.parameterExist(parList, "gif=")) {
        file = SUtil.getParamStr(parList, "gif=");
      }
      if(SUtil.parameterExist(parList, "file=")) {
        file = SUtil.getParamStr(parList, "file=");
      }
      if(file == null) {
        return 0;
      }
      Image im = edu.davidson.graphics.Util.getImage(file, this);
      if(im != null) {
        t = new FieldImage(this, fieldPanel, im, x, y);
      } else {
        t = null;
      }
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
      if(SUtil.parameterExist(parList, "path=")) {
        String path = SUtil.getParamStr(parList2, "path=");
        if(path.equals("ellipse")) {
          t = new ConstraintEllipse(this, fieldPanel, xmin, xmax, ymin, ymax);
        }
      } else {
        t = new Constraint(this, fieldPanel, xmin, xmax, ymin, ymax);
      }
    }
    if(t == null) {
      System.out.println("Object not created. name:" + name + "parameter list:" + parList);
      return 0;
    }
    fieldPanel.addThing(t);
    fieldPanel.invalidateOSI();
    if(autoRefresh) {
      fieldPanel.repaint();
    }
    return t.hashCode();
  }

  /**
   *       Forces an object to follow another object on the screen.
   *
   *       @param              masterID The id of the master object.
   *       @param              slaveID The id of the slave object.
   *       @return             true if successful.
   */
  public boolean setAnimationSlave(int masterID, int slaveID) {
    Thing master = fieldPanel.getThing(masterID);
    Thing slave  = fieldPanel.getThing(slaveID);
    if((master == null) || (slave == null)) {
      return false;
    }
    master.addSlave(slave);
    fieldPanel.invalidateOSI();
    if(autoRefresh) {
      fieldPanel.repaint();
    }
    return true;
  }

  /**
   * Gets the x component of the B field at a point.
   *
   * @param              x The x coordinate.
   * @param              y The y coordinate.
   *
   * @return    double the x component of B
   */
  public double getBx(double x, double y) {
    return fieldPanel.getBx(x, y, null);
  }

  /**
   * Gets the y component of the B field at a point.
   *
   * @param              x The x coordinate.
   * @param              y The y coordinate.
   *
   * @return    double the y component of B
   */
  public double getBy(double x, double y) {
    return fieldPanel.getBy(x, y, null);
  }

  /**
   * Gets the series ID for the graph object. This ID is used to make a connection to
   * a SDataSource.
   *
   * @return the id
   */
  synchronized public int getGraphID() {
    return fieldPanel.graph.hashCode();
  }

  /**
   * Set the series line and marker color.
   *
   * @param              id The series id.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   */
  synchronized public void setSeriesRGB(int id, int r, int g, int b) {
    fieldPanel.graph.setSeriesColor(id, new Color(r, g, b));
  }

  /**
   * Sets the series style for the graph object.
   *
   * @param              id The series id.
   * @param              conPts Connect the points?
   * @param              m Marker style. (m=1 is cross; m=2 is square, m=3 is
   *                     circle)
   */
  public synchronized void setSeriesStyle(int id, boolean conPts, int m) {
    fieldPanel.graph.setSeriesStyle(id, conPts, m);
  }

  /**
   *   Deletes a series from the graph
   *
   * @param s
   */
  public void deleteSeries(int s) {
    fieldPanel.graph.deleteSeries(s);
  }

  /**
   *     Clears the data from a series.  Series properties such as color and style
   *     remain unchanged.
   *
   * @param s
   */
  public void clearSeries(int s) {
    fieldPanel.graph.clearSeriesData(s);
  }

  /**
   *    Automatically repaints the Physlet every time a parameter is changed.
   *
   *    @param ar     Automatic repaint?
   */
  public synchronized void setAutoRefresh(boolean ar) {
    if(autoRefresh == ar) {
      return;
    }
    autoRefresh = ar;
    fieldPanel.setAutoRefresh(ar);
    if(!autoRefresh) {
      return;
    }
    //fieldPanel.invalidateOSI();
    //fieldPanel.setFields();
    //if(autoRefresh)fieldPanel.repaint();
    fieldPanel.invalidateOSI();
    fieldPanel.setFields();
    fieldPanel.paintOSI();
    Graphics g = fieldPanel.getGraphics();
    fieldPanel.paint(g);
    g.dispose();
  }

  /**
   *    Sets an external magnetic field. The field generated by this function is added
   *    to the field generated by wires.
   *
   *    @param bx     Bx(x,y)
   *    @param by     By(x,y)
   *    @param xmin   The x minimum value.
   *    @param xmax   The x maximum value.
   *    @param ymin   The y minimum value.
   *    @param ymax   The y maximum value.
   *
   *    @see  #addWire
   *    @see  #addCoil
   *
   */
  public void setBFunctions(String bx, String by, double xmin, double xmax, double ymin, double ymax) {
    bxField.setText(bx);
    byField.setText(by);
    fieldPanel.setBFunctions(bx, by, xmin, xmax, ymin, ymax);
  }

  /**
   *    Removes the wires and coils and resets the applet into a predfined state.
   *
   */
  public void setDefault() {
    setShowFieldVectors(false);
    wireLabel = null;
    wireColor = null;
    fieldPanel.setDefault();
  }

  /**
   *  Resets the applet and clears the field lines.
   */
  public void reset() {
    fieldPanel.reset();
  }

  /**
   *    Enables the user to click in order to draw a magnetic field line.
   *    CAUTION:  May be subject to numerical error if the field line is drawn
   *    close to a wire where the radius of curvature is large.
   *
   *    @param sfl        Boolean value.
   */
  public void setShowFieldLineOnClick(boolean sfl) {
    fieldPanel.setShowFieldLineOnClick(sfl);
    lineCkBox.setState(sfl);
  }

  /**
   *    Enables the user to double click in order to draw a magnetic field line.
   *    CAUTION:  May be subject to numerical error if the field line is drawn
   *    close to a wire where the radius of curvature is large.
   *
   *    @param sfl        Boolean value.
   */
  public void setShowFieldLineOnDoubleClick(boolean sfl) {
    fieldPanel.setShowFieldLineOnDoubleClick(sfl);
  }

  /**
   *    Shows the vector field.  Arrows are of unit length.  Color is used to represent magnitude.
   *
   *    @param sfv        Boolean value.
   */
  public void setShowFieldVectors(boolean sfv) {
    fieldVecCkBox.setState(sfv);
    fieldPanel.setShowFieldVectors(sfv);
  }

  /**
   *    Sets the red, green, and blue color values for a wire or coil that has already been created. Color values
   *    must be in the range 0..255.
   *
   *    @param id       The id for the wire or loop.
   *    @param r        red.
   *    @param g        green.
   *    @param b        blue.
   *
   * @return true if successful; false otherwise
   */
  public boolean setRGB(int id, int r, int g, int b) {
    return fieldPanel.setColor(id, new Color(r, g, b));
  }

  /**
   *    Changes the label property of a wire or loop after it has been created.
   *    Labels are usually single character identifies.
   *    Use the id returned by the add methods to identify the object you wish to change.
   *
   *    @param id        The id for the wire or loop.
   *    @param str       The new label.
   *
   * @return true if successful; false otherwise
   */
  public boolean setLabel(int id, String str) {
    return fieldPanel.setLabel(id, str);
  }

  /**
   * Offsets the object's position on the screen from its default drawing
   * position.
   *
   * @param              id The id of the object.
   * @param xOff
   * @param yOff
   * @return             True if successful.
   */
  public boolean setDisplayOffset(int id, int xOff, int yOff) {
    Thing t = fieldPanel.getThing(id);
    if(t == null) {
      return false;
    }
    t.setDisplayOff(xOff, yOff);
    fieldPanel.invalidateOSI();
    if(autoRefresh) {
      fieldPanel.setFields();
      fieldPanel.repaint();
    }
    return true;
  }

  /**
   *    Changes the drag property of an object.
   *    Use the id returned by the add methods to identify the object you wish to change.
   *
   *    @param id        the object identifier
   *    @param drag      true if dragable
   *
   *    @return true if successful
   */
  public boolean setDragable(int id, boolean drag) {
    return fieldPanel.setDragable(id, drag);
  }

  /**
   * Changes the object's format for the display of numeric data.
   *
   * Us this method to control the number of significant digits in calculations with text objects.
   * Use Unix printf conventions.  For example fstr="%6.3f"
   *
   * @param              id The id of the object.
   * @param              fstr the format string.
   * @return             True if successful.
   */
  public boolean setFormat(int id, String fstr) {
    Thing t = fieldPanel.getThing(id);
    if((t == null) && ((id == 0) || (id == fieldPanel.hashCode()))) {
      return fieldPanel.setFormat(fstr);
    }
    boolean result = t.setFormat(fstr);
    if(autoRefresh) {
      fieldPanel.repaint();
    }
    return result;
  }

  /**
   * Sets the object's font for text that is associated with the object.
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
    Thing t    = fieldPanel.getThing(id);
    if((t == null) || (font == null)) {
      return false;
    }
    t.setFont(font);
    if(autoRefresh) {
      fieldPanel.repaint();
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
   *    Changes the option drag property of a coil after it has been created.
   *    Enables a user to click-drag on a coil in order to change a loop's radius.
   *
   *    @param id        The id for the wire or loop.
   *    @param isDrag         The new current.
   *
   *    @deprecated
   *
   *    @see  #setResizable setResizable
   *
   * @return true if successful; false otherwise
   */
  public boolean setOptionDrag(int id, boolean isDrag) {
    return fieldPanel.setOptionDrag(id, isDrag);
  }

  /**
   * Makes an object resizable.  Only coils are resizbale in this applet.
   *
   * Enables a user to click-drag on a coil in order to change a loop's radius.
   *
   * @param              id the object identifier.
   * @param isResizable
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setResizable(int id, boolean isResizable) {
    return fieldPanel.setOptionDrag(id, isResizable);
  }

  /**
   *    Changes the current of a wire or loop after it has been created.
   *    Use the id returned by the add methods to identify the object you wish to change.
   *
   *    @param id        The id for the wire or loop.
   *    @param c         The new current.
   *
   * @return true if successful; false otherwise
   */
  public boolean setCurrent(int id, double c) {
    return fieldPanel.setCurrent(id, c);
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
    Thing t = fieldPanel.getThing(id);
    Thing c = fieldPanel.getThing(constraintID);
    if(t == null) {
      return false;
    }
    if(c == null) {
      return false;
    }
    if(!(c instanceof Constraint)) {
      return false;
    }
    t.setConstraint((Constraint) c);
    return true;
  }

  /**
   *    Changes the radius of a loop after it has been created.
   *    Use the id returned by the add methods to identify the object you wish to change.
   *
   *    @param id        The id for the wire or loop.
   *    @param r         The new radius.
   *
   * @return true if successful; false otherwise
   */
  public boolean setRadius(int id, double r) {
    return fieldPanel.setRadius(id, r);
  }

  /**
   *    Shows the net force vector on a wire.
   *    Use the id returned by the add methods to identify the object you wish to change.
   *
   *    @param id        The id for the wire or loop.
   *    @param showForce Boolean value.
   *
   * @return true if successful; false otherwise
   */
  public boolean setShowFVector(int id, boolean showForce) {
    return fieldPanel.setShowForce(id, showForce);
  }

  /**
   * Shows the force on a wire in the message box when the object is clicked.
   *
   * @param id
   * @param showForce
   *
   * @return true if successful; false otherwise
   */
  public boolean setShowForce(int id, boolean showForce) {
    return fieldPanel.setShowForce(id, showForce);
  }

  /**
   *    Enables a wire to show the force vector components.
   *    Use the id returned by the add methods to identify the object you wish to change.
   *
   *    @param id              The id for the wire or loop.
   *    @param showComponents  Boolean value.
   *
   * @return true if successful; false otherwise
   */
  public boolean setShowFComponents(int id, boolean showComponents) {
    return fieldPanel.setShowFComponents(id, showComponents);
  }

  /**
   *    Enables an object to show information such as the current.
   *    Use the id returned by the add methods to identify the object you wish to change.
   *
   *    @param id        The id for the wire or loop.
   *    @param showInfo  Boolean value.
   *
   * @return true if successful; false otherwise
   */
  public boolean setShowInfo(int id, boolean showInfo) {
    return fieldPanel.setShowInfo(id, showInfo);
  }

  /**
   *    Sets an objects visibility.
   *
   *    Wires will not be drawn but will still create a field.
   *
   *    @param id      the id for the wire or loop.
   *    @param v       true if visible
   *
   * @return true if successful; false otherwise
   */
  public boolean setVisibility(int id, boolean v) {
    return fieldPanel.setVisibility(id, v);
  }

  /**
   * Sets position of an object.
   *
   * @param              id The ID of the object.
   * @param              x The x coordinate.
   * @param              y The y coordinate.
   *
   * @return true if successful; false otherwise
   */
  public boolean setXY(int id, double x, double y) {
    Thing t = fieldPanel.getThing(id);
    if(t == null) {
      return false;
    }
    if(t instanceof Wire) {
      fieldPanel.setWireXY((Wire) t, x, y);
      return true;
    }
    t.setXY(x, y);
    fieldPanel.invalidateOSI();
    if(autoRefresh) {
      fieldPanel.repaint();
    }
    return true;
  }

  /**
   * Sets x position of an object.
   *
   * @param              id The ID of the object.
   * @param              x The x coordinate.
   *
   * @return true if successful; false otherwise
   */
  public boolean setXPos(int id, double x) {
    Thing t = fieldPanel.getThing(id);
    if(t == null) {
      return false;
    }
    if(t instanceof Wire) {
      double y = t.getY();
      fieldPanel.setWireXY((Wire) t, x, y);
      return true;
    }
    t.setX(x);
    fieldPanel.invalidateOSI();
    if(autoRefresh) {
      fieldPanel.repaint();
    }
    return true;
  }

  /**
   * Sets the x positon of an object.
   * Same as setXPos.
   *
   * @param id
   * @param x
   *
   * @return x
   */
  boolean setX(int id, double x) {
    return setXPos(id, x);
  }

  /**
   * Gets X position of an object.
   *
   * @param  id The ID of the object.
   *
   * @return the x coordinate
   */
  public double getXPos(int id) {
    Thing t = fieldPanel.getThing(id);
    if(t == null) {
      return 0;
    }
    return t.getX();
  }

  /**
   *  Sets Y position of an object.
   *
   *  @param              id The ID of the object.
   *  @param              y The y coordinate.
   *
   * @return true if successful; false otherwise
   */
  public boolean setYPos(int id, double y) {
    Thing t = fieldPanel.getThing(id);
    if(t == null) {
      return false;
    }
    if(t instanceof Wire) {
      double x = t.getX();
      fieldPanel.setWireXY((Wire) t, x, y);
      return true;
    }
    t.setY(y);
    fieldPanel.invalidateOSI();
    if(autoRefresh) {
      fieldPanel.repaint();
    }
    return true;
  }

  /**
   * Sets the y postion of an object.
   * Same as setYPos.
   *
   * @param id
   * @param y
   *
   * @return y
   */
  public boolean setY(int id, double y) {
    return setYPos(id, y);
  }

  /**
   * Gets X position of an object.
   *
   * @param              id The ID of the object.
   *
   * @return the x coordinate
   */
  public double getYPos(int id) {
    Thing t = fieldPanel.getThing(id);
    if(t == null) {
      return 0;
    }
    return t.getY();
  }

  /**
   *    Enables a the user to read coordinates by click-dragging the mouse.
   *
   *    @param sc         Boolean value.
   */
  public void setShowCoordOnDrag(boolean sc) {
    fieldPanel.setShowCoordOnDrag(sc);
  }

  /**
   * Enables sketching with the mouse.
   *
   * @param              sketch true will sketch
   *
   * @return  int the id of the mouse data source
   */
  public int setSketchMode(boolean sketch) {
    return fieldPanel.setSketchMode(sketch);
  }

  /**
   *    Enables a the user to read the magnetic field magnitude by click-dragging the mouse.
   *
   *    @param sb         Boolean value.
   */
  public void setShowBOnDrag(boolean sb) {
    fieldPanel.setShowBOnDrag(sb);
  }

  /**
   *    Enables interactive controls. Useful for testing but usually false for scripted problems.
   *
   *    @param sc         Boolean value.
   */
  public void setShowControls(boolean sc) {
    etchedBorder2.setVisible(sc);
    invalidate();
    validate();
  }

  /**
   *    Sets the default visibility for wires and coils.
   *
   *    Should be set before the add methods are called.
   *
   *    @param hw         Boolean value.
   */
  public void setHideWire(boolean hw) {
    hideWire = hw;
  }

  /**
   *    Sets the default red, green, and blue color values for all subsequent wires and coils. Color values
   *    must be in the range 0..255.  The default is to color code the direction of currcent flow.
   *
   *    @param r        red.
   *    @param g        green.
   *    @param b        blue.
   */
  public void setDefaultRGB(int r, int g, int b) {
    wireColor = new Color(r, g, b);
  }

  /**
   *    Sets the default label that will be drawn inside a wire.
   *    Labels should be single characters. All subsequent wires will carry this label.
   *
   *    @param str the label
   */
  public void setDefaultLabel(String str) {
    if((str == null) || str.trim().equals("")) {
      wireLabel = null;
    } else {
      wireLabel = str;
    }
  }

  /**
   * Swaps the drawing order on the screen.
   *
   * @param              id1 the first id of a screen object
   * @param              id2 the second id of a screen object
   * @return             true if successful
   */
  public synchronized boolean swapZOrder(int id1, int id2) {
    boolean err = fieldPanel.swapZOrder(id1, id2);
    fieldPanel.invalidateOSI();
    if(autoRefresh) {
      fieldPanel.repaint();
    }
    return err;
  }

  /**
   * Counts the number of applets on a page.
   *
   * @return  the number of applets
   */
  public int getAppletCount() {
    if(firstTime) {
      return 0;
    } else {
      return super.getAppletCount();
    }
  }

  /**
   *  Starts the applet.     Do not script.
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
  public synchronized void start() {
    if(firstTime) {
      firstTime      = false;
      fieldPanel.osi = null;
      fieldPanel.paintOSI();
      fieldPanel.setBFunctions(bxStr, byStr);
      //addObject("image","file=wc.gif, x=0,y=0");
      //addObject("coil","x=-2, dx=0.2,num=10");
    }
    super.start();
    // fieldPanel.setSketchMode(true);
    // debug scripts go here
    // int id=this.addObject("compass","r=25") ;
    // setDragable(id,true);
    /*
     *     int w2=addWire(2,0,-2);
     *     setDragable(w2,true);
     *     setShowForce(w2,false);
     *     setShowBOnDrag(false);
     *     int vec=addObject("arrow","h=bx,v=by");
     *     setAnimationSlave(w2,vec);
     *    // int cid=addObject("constraint","path=ellipse, xmin=-2,xmax=2,ymin=-2,ymax=2");
     *    // setConstraint(w2,cid);
     */
  }

  /**
   * Destroy all threads and cleanup the applet.
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   *
   */
  public void destroy() {
    destroyed   = true;
    autoRefresh = false;
    fieldPanel.setAutoRefresh(false);
    super.destroy();
  }

  //action methods for buttons and text fields

  /**
   * Method fieldVecCkBox_itemStateChanged
   *
   * @param e
   */
  void fieldVecCkBox_itemStateChanged(ItemEvent e) {
    if(e.getStateChange() == ItemEvent.SELECTED) {
      fieldPanel.setShowFieldVectors(true);
    } else {
      fieldPanel.setShowFieldVectors(false);
    }
  }

  /**
   * Method lineCkBox_itemStateChanged
   *
   * @param e
   */
  void lineCkBox_itemStateChanged(ItemEvent e) {
    if(e.getStateChange() == ItemEvent.SELECTED) {
      fieldPanel.setShowFieldLineOnDoubleClick(true);
    } else {
      fieldPanel.setShowFieldLineOnDoubleClick(false);
    }
  }

  /**
   * Method coilCkBox_itemStateChanged
   *
   * @param e
   */
  void coilCkBox_itemStateChanged(ItemEvent e) {
    if(e.getStateChange() == ItemEvent.SELECTED) {
      coilMode = true;
    } else {
      coilMode = false;
    }
  }

  /**
   * Method clearBtn_actionPerformed
   *
   * @param e
   */
  void clearBtn_actionPerformed(ActionEvent e) {
    fieldPanel.clearAll();
  }

  /**
   * Method inBtn_actionPerformed
   *
   * @param e
   */
  void inBtn_actionPerformed(ActionEvent e) {
    if(coilMode) {
      fieldPanel.addCoil(+1);
    } else {
      fieldPanel.addWire(-1);
    }
  }

  /**
   * Method outBtn_actionPerformed
   *
   * @param e
   */
  void outBtn_actionPerformed(ActionEvent e) {
    if(coilMode) {
      fieldPanel.addCoil(-1);
    } else {
      fieldPanel.addWire(+1);
    }
  }

  /**
   * Method updateBtn_actionPerformed
   *
   * @param e
   */
  void updateBtn_actionPerformed(ActionEvent e) {
    bxStr = bxField.getText();
    byStr = byField.getText();
    fieldPanel.setBFunctions(bxStr, byStr);
  }
}
