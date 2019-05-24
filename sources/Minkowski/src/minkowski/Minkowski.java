package minkowski;

import java.awt.*;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import edu.davidson.graphics.*;
import edu.davidson.display.*;
import edu.davidson.tools.*;

/**
 *
 *
 *                      Class  minkowski.Minkowski
 *
 *
 *
 * class Minkowski extends SApplet
 *
 * Minkowski shows the geometric representation of Rotation, Galilean, and Lorentz coordinate transformations.
 *
 *
 */
public class Minkowski extends SApplet {

  private String     button_clear = "Clear";
  private String     button_add   = "Add";
  private String     label_speed  = "speed =";
  double             speed;
  String             mode;
  boolean            showControls;
  EtchedBorder       etchedBorder1       = new EtchedBorder();
  BorderLayout       borderLayout1       = new BorderLayout();
  SPanel             coordPanel          = new SPanel();
  BorderLayout       borderLayout2       = new BorderLayout();
  MinkowskiCanvas    minkowskiCanvas     = new MinkowskiCanvas(this);
  Panel              panel2              = new Panel();
  EtchedBorder       box2                = new EtchedBorder();
  Panel              panel4              = new Panel();
  BorderLayout       borderLayout4       = new BorderLayout();
  BorderLayout       borderLayout7       = new BorderLayout();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  Button             addMovingBtn        = new Button();
  Button             clearMovingBtn      = new Button();
  Panel              panel8              = new Panel();
  Label              yPrimeLabel         = new Label();
  SNumber            yPrimeField         = new SNumber(0,"%-+0.3g");
  EtchedBorder       etchedBorder2       = new EtchedBorder();
  Label              label1              = new Label();
  SNumber            xPrimeField         = new SNumber(0,"%-+0.3g");
  EtchedBorder       etchedBorder3       = new EtchedBorder();
  Label              label5              = new Label();
  Label              yLabel              = new Label();
  Button             addLabBtn           = new Button();
  Button             clearLabBtn         = new Button();
  SNumber            yField              = new SNumber(0,"%-+0.3g");
  SNumber            xField              = new SNumber(0,"%-+0.3g");
  VerticalFlowLayout verticalFlowLayout4 = new VerticalFlowLayout();
  EtchedBorder       box4                = new EtchedBorder();
  Panel              panel10             = new Panel();
  Panel              panel6              = new Panel();
  BorderLayout       borderLayout6       = new BorderLayout();
  EtchedBorder       etchedBorder6       = new EtchedBorder();
  EtchedBorder       etchedBorder7       = new EtchedBorder();
  EtchedBorder       sliderPanel         = new EtchedBorder();
  EtchedBorder       box1                = new EtchedBorder();
  Panel              panel3              = new Panel();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  SSlider            speedSlider         = new SSlider();
  BorderLayout       borderLayout3       = new BorderLayout();
  Label              speedLabel          = new Label();
  SNumber            speedField          = new SNumber(0,"%-+0.3g");
  GridLayout         gridLayout1         = new GridLayout();
  //Construct the applet

  /**
   * Constructor Minkowski
   * @y.exclude
   */
  public Minkowski() {}

  protected void setResources() {
    button_add   = localProperties.getProperty("button.add", button_add);
    button_clear = localProperties.getProperty("button.clear", button_clear);
    label_speed  = localProperties.getProperty("label.speed", label_speed);
  }

  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    try {
      speed = Double.valueOf(this.getParameter("Speed", "0.0")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      mode = this.getParameter("Mode", "Lorentz");
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    mode = mode.toLowerCase();
    if(mode.equals("galilean")) {
      minkowskiCanvas.setType('G');
    } else if(mode.equals("rotation")) {
      minkowskiCanvas.setType('R');
    } else {
      minkowskiCanvas.setType('L');
    }
// BH problem here is that Swing scroll bars fire their model's document change, 
// so every press of a key in the speedField runs a new graph
//    speedField.addPropertyChangeListener(speedSlider);
    speedSlider.addPropertyChangeListener(speedField);
    this.coordPanel.setVisible(showControls);
    sliderPanel.setVisible(showControls);
    speedField.setNoColor(true);
    speedField.setValue(speed);
    speedSlider.setDValue(speed);
    this.minkowskiCanvas.setSpeed(speed);
    setLabels();
  }

  //Component initialization
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    etchedBorder1.setLayout(borderLayout2);
    coordPanel.setBackground(SystemColor.control);
    coordPanel.setMinimumSize(new Dimension(150, 200));
    coordPanel.setPreferredSize(new Dimension(150, 200));
    coordPanel.setLayout(borderLayout7);
    panel2.setLayout(gridLayout1);
    panel4.setLayout(verticalFlowLayout1);
    box2.setLayout(borderLayout4);
    panel4.setBackground(SystemColor.controlHighlight);
    addMovingBtn.setLabel(button_add);
    addMovingBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addMovingBtn_actionPerformed(e);
      }
    });
    clearMovingBtn.setLabel(button_clear);
    clearMovingBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        clearMovingBtn_actionPerformed(e);
      }
    });
    yPrimeLabel.setText("y\' = ");
    yPrimeLabel.setAlignment(2);
    label1.setAlignment(2);
    label1.setText("x\' = ");
    label5.setAlignment(2);
    label5.setText("x =");
    yLabel.setText("y =");
    yLabel.setAlignment(2);
    addLabBtn.setLabel(button_add);
    addLabBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        addLabBtn_actionPerformed(e);
      }
    });
    clearLabBtn.setLabel(button_clear);
    clearLabBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        clearLabBtn_actionPerformed(e);
      }
    });
    box4.setLayout(borderLayout6);
    panel6.setLayout(verticalFlowLayout4);
    panel6.setBackground(SystemColor.controlHighlight);
    box1.setLayout(verticalFlowLayout2);
    panel3.setLayout(borderLayout3);
    speedLabel.setAlignment(2);
    speedLabel.setText(label_speed+" ");
    verticalFlowLayout2.setVgap(0);
    panel2.setBackground(SystemColor.control);
    speedSlider.setDValue(0.5);
    speedSlider.setDMin(-0.99);
    speedSlider.setDMax(0.99);
    speedSlider.addAdjustmentListener(new java.awt.event.AdjustmentListener() {

      public void adjustmentValueChanged(AdjustmentEvent e) {
        speedSlider_adjustmentValueChanged(e);
      }
    });
    gridLayout1.setColumns(1);
    gridLayout1.setRows(2);
    speedField.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        speedField_actionPerformed(e);
      }
    });
    this.add(etchedBorder1, BorderLayout.CENTER);
    etchedBorder1.add(coordPanel, BorderLayout.WEST);
    coordPanel.add(panel2, BorderLayout.CENTER);
    panel2.add(box4, null);
    box4.add(panel6, BorderLayout.CENTER);
    panel6.add(etchedBorder7, null);
    etchedBorder7.add(xField, BorderLayout.CENTER);
    etchedBorder7.add(label5, BorderLayout.WEST);
    panel6.add(etchedBorder6, null);
    etchedBorder6.add(yField, BorderLayout.CENTER);
    etchedBorder6.add(yLabel, BorderLayout.WEST);
    panel6.add(panel10, null);
    panel10.add(addLabBtn, null);
    panel10.add(clearLabBtn, null);
    panel2.add(box2, null);
    box2.add(panel4, BorderLayout.CENTER);
    panel4.add(etchedBorder3, null);
    etchedBorder3.add(xPrimeField, BorderLayout.CENTER);
    etchedBorder3.add(label1, BorderLayout.WEST);
    panel4.add(etchedBorder2, null);
    etchedBorder2.add(yPrimeField, BorderLayout.CENTER);
    etchedBorder2.add(yPrimeLabel, BorderLayout.WEST);
    panel4.add(panel8, null);
    panel8.add(addMovingBtn, null);
    panel8.add(clearMovingBtn, null);
    etchedBorder1.add(minkowskiCanvas, BorderLayout.CENTER);
    this.add(sliderPanel, BorderLayout.SOUTH);
    sliderPanel.add(box1, BorderLayout.NORTH);
    box1.add(panel3, null);
    sliderPanel.setBackground(SystemColor.control);
    box1.setBackground(SystemColor.control);
    panel3.setBackground(SystemColor.control);
    panel3.add(speedSlider, BorderLayout.CENTER);
    panel3.add(speedLabel, BorderLayout.WEST);
    panel3.add(speedField, BorderLayout.EAST);
  }

  /**
   * Method getAppletInfo
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
      {"Speed", "double", "Speed"}, {"Mode", "String", "Transformation type"},
      {"ShowControls", "boolean", "Show tihe user interface."},
    };
    return pinfo;
  }

  /**
   * Method speedSlider_adjustmentValueChanged
   *
   * @param e
   */
  void speedSlider_adjustmentValueChanged(AdjustmentEvent e) {
    this.minkowskiCanvas.setSpeed(speedSlider.getDValue());
  }

  /**
   * Method addLabBtn_actionPerformed
   *
   * @param e
   */
  void addLabBtn_actionPerformed(ActionEvent e) {
    this.minkowskiCanvas.addLabPoint(xField.getValue(), yField.getValue());
  }

  /**
   * Method clearLabBtn_actionPerformed
   *
   * @param e
   */
  void clearLabBtn_actionPerformed(ActionEvent e) {
    minkowskiCanvas.clearLabCoords();
  }

  /**
   * Method addMovingBtn_actionPerformed
   *
   * @param e
   */
  void addMovingBtn_actionPerformed(ActionEvent e) {
    minkowskiCanvas.addMovPoint(xPrimeField.getValue(), yPrimeField.getValue());
  }

  /**
   * Method clearMovingBtn_actionPerformed
   *
   * @param e
   */
  void clearMovingBtn_actionPerformed(ActionEvent e) {
    minkowskiCanvas.clearMovCoords();
  }

  /**
   * Method speedField_actionPerformed
   *
   * @param e
   */
  void speedField_actionPerformed(ActionEvent e) {
	speedField.updateValueFromText();
	double speed = speedField.getValue();
    minkowskiCanvas.setSpeed(speed);
    speed = minkowskiCanvas.getSpeed();
    // BH  we need to drive the slider only upon pressing return, or the graph will change as we type
//    speedField.setValue(speed);
    speedSlider.setDValue(speed);
//    speedSlider.propertyChange(new PropertyChangeEvent(speedField, "DValue" , null, Double.valueOf(speed)));
    speedField.setValue(speed);
  }

  private void setLabels() {
    if(mode.equals("galilean")) {
      yPrimeLabel.setText("t\' = ");
      yLabel.setText("t = ");
      speedLabel.setText(label_speed+" ");
    } else if(mode.equals("rotation")) {
      yPrimeLabel.setText("y\' = ");
      yLabel.setText("y = ");
      speedLabel.setText(" ");
    } else {
      yPrimeLabel.setText("t\' = ");
      yLabel.setText("t = ");
      speedLabel.setText(label_speed+" ");
    }
  }

  /**
   *  Sets the pixels per unit.  This sets the scale for the animation.
   *
   *  @param pu pixels per unit.
   */
  public void setPixPerUnit(int pu) {
    minkowskiCanvas.pixPerUnit = pu;
    if(autoRefresh) {
      repaint();
    }
  }

  /**
   * Shows the coordinate values on the screen when the mouse is dragged.
   *
   * @param              id  the object identifier
   * @param              show true to show
   * @return             true if successful
   */
  public boolean setShowCoordinates(int id, boolean show) {
    if((id==0)||(id==this.minkowskiCanvas.hashCode())) {
      minkowskiCanvas.coordDisplay = show;
      return true;
    }
    return false;
  }

  /**
   *   Changes the type of transformation
   *
   *   The mode should be Galilean, Rotation, or Lorentz.
   *
   *   @param              mode the transformation type
   */
  public void setMode(String mode) {
    mode      = mode.toLowerCase();
    this.mode = mode;
    if(mode.equals("galilean")) {
      minkowskiCanvas.setType('G');
    } else if(mode.equals("rotation")) {
      minkowskiCanvas.setType('R');
    } else {
      minkowskiCanvas.setType('L');
    }
    setLabels();
  }

  /**
   *    Clears the lab and moving coordinates.   Reset the clock.
   */
  public void reset() {
    super.reset();
  }

  /**
   *    Sets the default state.
   */
  synchronized public void setDefault() {
    deleteDataConnections();  // we are going to delete all the things so we might as well kill the conections too.
    reset();
    this.minkowskiCanvas.clearLabCoords();
    this.minkowskiCanvas.clearMovCoords();
  }

  /**
   *    Sets the velocity of the moving frame.
   *
   * @param v
   */
  public void setVelocity(double v) {
    speed = v;
    speedField.setValue(speed);
    speedSlider.setDValue(speed);
    this.minkowskiCanvas.setSpeed(speed);
  }

  /**
   * Adds a point to the laboratory coordiante frame.
   *
   * @param x the x value
   * @param y the y value
   *
   * @return the id
   */
  public int addLabPoint(double x, double y) {
    if(showControls) {
      xField.setValue(x);
      yField.setValue(x);
    }
    return this.minkowskiCanvas.addLabPoint(x, y);
  }

  /**
   * Add a point to the moving coordiante frame.
   *
   * @param x the x value
   * @param y the y value
   *
   * @return the id
   */
  public int addMovingPoint(double x, double y) {
    if(showControls) {
      xPrimeField.setValue(x);
      yPrimeField.setValue(x);
    }
    return minkowskiCanvas.addMovPoint(x, y);
  }
}
