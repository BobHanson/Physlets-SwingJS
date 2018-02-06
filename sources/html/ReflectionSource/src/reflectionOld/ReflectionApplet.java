/*
 *
 *
 *                      Class  Reflection
 *
 *
 *
 * class Reflection extends  SApplet
 *
 * @author Jim Nolen
 *
 */
package reflection;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;

//import borland.jbcl.layout.*;

/**
 * Class ReflectionApplet
 */
public class ReflectionApplet extends SApplet {

  String                             button_start     = "Run";
  String                             button_stop      = "Stop";
  String                             button_reset     = "Reset";
  String                             button_forward   = "Step";
  String                             button_add       = "Add/Change";
  String                             label_number     = "#:";
  String                             label_refraction = "n =";
  String                             label_left_wave  = "Left Wave";
  String                             label_right_wave = "Right Wave";
  String                             label_sum        = "Sum";
  String                             label_energy     = "Energy =";
  String                             label_time       = "Time:";
  String                             label_no_phase   = "Phase not available.";
  String                             label_phase      = "Phase =";
  boolean                            showControls;
  int                                fps;
  String                             mode, m;
  double                             ppu, magMax, lam, fq, wv;
  // int[] itemArray = new int[25];
  int                                selectedMedia = 0;
  WavePanel                          wavePanel     = new WavePanel(this);
  edu.davidson.graphics.EtchedBorder controlPanel  = new edu.davidson.graphics.EtchedBorder();
  edu.davidson.graphics.EtchedBorder etchedBorder2 = new edu.davidson.graphics.EtchedBorder();
  edu.davidson.graphics.EtchedBorder etchedBorder3 = new edu.davidson.graphics.EtchedBorder();
  Button                             initBtn       = new Button();
  Button                             changeBtn     = new Button();
  edu.davidson.display.SNumber       indexField    = new edu.davidson.display.SNumber();
  Label                              label1        = new Label();
  Label                              label2        = new Label();
  edu.davidson.display.SInteger      mediaField    = new edu.davidson.display.SInteger();
  FlowLayout                         flowLayout1   = new FlowLayout();
  BorderLayout                       borderLayout1 = new BorderLayout();
  BorderLayout                       borderLayout2 = new BorderLayout();
  Button                             runBtn        = new Button();
  SPanel                             panel1        = new SPanel();
  SPanel                             panel2        = new SPanel();
  BorderLayout                       borderLayout3 = new BorderLayout();
  BorderLayout                       borderLayout4 = new BorderLayout();
  Panel                              panel3        = new Panel();
  BorderLayout                       borderLayout5 = new BorderLayout();
  Button                             stepBtn       = new Button();
  FlowLayout                         flowLayout2   = new FlowLayout();
  //Construct the applet

  /**
   * Constructor ReflectionApplet
   * @y.exclude
   */
  public ReflectionApplet() {}

  protected void setResources() {
    button_start     = localProperties.getProperty("button.start", button_start);
    button_stop      = localProperties.getProperty("button.stop", button_stop);
    button_reset     = localProperties.getProperty("button.reset", button_reset);
    button_forward   = localProperties.getProperty("button.forward", button_forward);
    button_add       = localProperties.getProperty("button.add", button_add);
    label_number     = localProperties.getProperty("label.number", label_number);
    label_refraction = localProperties.getProperty("label.refraction", label_refraction);
    label_left_wave  = localProperties.getProperty("label.left_wave", label_left_wave);
    label_right_wave = localProperties.getProperty("label.right_wave", label_right_wave);
    label_sum        = localProperties.getProperty("label.sum", label_sum);
    label_energy     = localProperties.getProperty("label.energy", label_energy);
    label_time       = localProperties.getProperty("label.time", label_time);
    label_no_phase   = localProperties.getProperty("label.no_phase", label_no_phase);
    label_phase      = localProperties.getProperty("label.phase", label_phase);
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
      ppu = Double.valueOf(this.getParameter("PPU", "10")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      magMax = Double.valueOf(this.getParameter("MaxAmp", "3")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      lam = Double.valueOf(this.getParameter("Wavelength", "6")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    //try { fq = Double.valueOf(this.getParameter("Oscillations", "4")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try {
      wv = Double.valueOf(this.getParameter("WaveVelocity", "10")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      fps = Integer.valueOf(this.getParameter("FPS", "4")).intValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      m = String.valueOf(this.getParameter("Mode", "EM")).toString();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    clock.addClockListener(wavePanel);
    setMode(m);
    setShowControls(showControls);
    setPPU(ppu);
    setMagMax(magMax);
    setWavelength(lam);
    setFPS(fps);
    setWaveVelocity(wv);
    try {
      int numpts = Integer.parseInt(this.getParameter("NumPts", "-1"));
      if(numpts>4)wavePanel.setArray(numpts);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    controlPanel.setLayout(borderLayout2);
    etchedBorder2.setLayout(flowLayout2);
    etchedBorder3.setLayout(flowLayout1);
    initBtn.setLabel(button_reset);
    initBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        initBtn_actionPerformed(e);
      }
    });
    changeBtn.setLabel(button_add);
    changeBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        changeBtn_actionPerformed(e);
      }
    });
    indexField.setText("1");
    indexField.setValue(1.0);
    label1.setAlignment(2);
    label1.setText(label_refraction);
    label2.setAlignment(2);
    label2.setText(label_number);
    mediaField.setText("1");
    mediaField.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        mediaField_actionPerformed(e);
      }
    });
    runBtn.setLabel(button_start);
    runBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        runBtn_actionPerformed(e);
      }
    });
    panel2.setLayout(borderLayout3);
    panel1.setLayout(borderLayout4);
    panel3.setLayout(borderLayout5);
    panel2.setMinimumSize(new Dimension(100, 20));
    panel2.setPreferredSize(new Dimension(100, 20));
    panel1.setMinimumSize(new Dimension(150, 20));
    panel1.setPreferredSize(new Dimension(150, 20));
    stepBtn.setLabel(button_forward);
    stepBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        stepBtn_actionPerformed(e);
      }
    });
    controlPanel.setBackground(Color.lightGray);
    this.setBackground(Color.lightGray);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout2.setHgap(2);
    this.add(wavePanel, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(etchedBorder2, BorderLayout.CENTER);
    etchedBorder2.add(panel3, null);
    panel3.add(changeBtn, BorderLayout.WEST);
    etchedBorder2.add(panel2, null);
    panel2.add(label2, BorderLayout.CENTER);
    panel2.add(mediaField, BorderLayout.EAST);
    etchedBorder2.add(panel1, null);
    panel1.add(label1, BorderLayout.WEST);
    panel1.add(indexField, BorderLayout.CENTER);
    controlPanel.add(etchedBorder3, BorderLayout.WEST);
    etchedBorder3.add(initBtn, null);
    etchedBorder3.add(runBtn, null);
    etchedBorder3.add(stepBtn, null);
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
   *
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String pinfo[][] = {
      {"ShowControls", "boolean", "true or false"},
    };
    return pinfo;
  }

  /**
   * Method setShowControls
   *
   * @param s
   */
  public void setShowControls(boolean s) {
    controlPanel.setVisible(s);
    this.invalidate();
    this.validate();
  }

  /**
   *
   * Method sets default parameters for this applet. Should be called at the
   * begining of a set of script.
   *
   */
  public void setDefault() {
    clock.stopClock();
    wavePanel.setDefault();
  }

  /**
   *
   * Method adds small text box in lower right hand corner of screen
   * Turned off by setDefault();
   *
   * @param msg String to be displayed
   */
  public void setMessage(String msg) {
    wavePanel.message     = msg;
    wavePanel.showMessage = true;
  }

  /**
   *
   * Method sets index of refraction of a medium
   *
   * @param id the object identifier
   * @param n  the index of refraction
   */
  public void setIndexN(int id, double n) {
    wavePanel.setIndexN(id, n);
  }

  /**
   *
   * Allows the user to adjust the energy or index of refraction by dragging up and down inside an element.
   *
   * If id==0, the total energy can be adjusted. Otherwise, the potential energy of the element is adjusted.
   *
   * @param id the object identifier
   * @param drag boolean
   */
  public void setDragEnergy(int id, boolean drag) {
    wavePanel.setDragEnergy(id, drag);
  }

  /**
   *
   * Method sets pixels per unit
   *
   * @param ppu double
   */
  public void setPPU(double ppu) {
    wavePanel.ppu = ppu;
  }

  /**
   *
   * Sets the energy of the QM state.
   *
   * @param en the energy
   */
  public void setQMEnergy(double en) {
    wavePanel.setQMEnergy(en);
  }

  /**
   *
   * Gets the energy of the QM state.
   *
   * @return the energy
   */
  public double getQMEnergy() {
    return wavePanel.getQMEnergy();
  }

  /**
   *
   * Sets the potential energy of the layer.
   *
   * @param id  the object identifier for the layer
   *
   * @param v the potential energy
   */
  public void setQMPotential(int id, double v) {
    setIndexN(id, v);
  }

  /**
   *
   * Sets applet in EM or QM mode
   *
   * @param m String "EM" or "QM"
   */
  public void setMode(String m) {
    if(m.equals("QM")) {
      wavePanel.setMode(1);
    } else {
      wavePanel.setMode(0);
    }
    mode = m;
  }

  /**
   * Pauses the animation.
   *
   */
  public void pause() {
    clock.stopClock();
    runBtn.setLabel(button_start);
  }

  /**
   * Starts the animation.
   */
  public void forward() {
    clock.setDt(clock.getDt());
    recalculate();
    clock.startClock();
    runBtn.setLabel(button_stop);
  }

  /**
   * Reverses the animation.
   */
  public void reverse() {
    clock.setDt(-clock.getDt());
    recalculate();
    clock.startClock();
    runBtn.setLabel(button_stop);
  }

  /**
   * Steps the animation.
   */
  public void step() {
    if(clock.isRunning()) {
      pause();
      return;
    }
    clock.doStep();
    runBtn.setLabel(button_start);
  }

  /**
   *       Step the time by dt.
   *
   *       @see                #setDt
   */
  public void stepTimeForward() {
    wavePanel.showMessage = false;
    if(clock.isRunning()) {
      pause();
      runBtn.setLabel(button_start);
      return;
    }
    boolean isNegative = false;
    if(clock.getDt()<0) {
      isNegative = true;
    }
    clock.setDt(Math.abs(clock.getDt()));
    clock.doStep();
    if(isNegative) {
      clock.setDt(-Math.abs(clock.getDt()));  // make dt negative since it started out that way.
    }
  }

  /**
   * Method stepForward
   *
   */
  public void stepForward() {
    stepTimeForward();
  }

  /**
   *         Step the time backward by dt.
   *
   *         @see                #setDt
   */
  public void stepTimeBack() {
    wavePanel.showMessage = false;
    if(clock.isRunning()) {
      pause();
      runBtn.setLabel(button_start);
      return;
    }
    boolean isNegative = false;
    if(clock.getDt()<0) {
      isNegative = true;
    }
    clock.setDt(-Math.abs(clock.getDt()));
    clock.doStep();
    if(!isNegative) {
      clock.setDt(Math.abs(clock.getDt()));  // make dt positive since it started out that way.
    }
  }

  /**
   * Method stepBack
   *
   */
  public void stepBack() {
    stepTimeBack();
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
   * Method start
   * @y.exclude
   */
  public void start() {
    if(firstTime&&showControls) {
      setAutoRefresh(false);
      /*
       * setMode("QM");
       * setQMEnergy(0.2);
       * setShowPhaseColor(false);
       * setShowLWave(true,0);
       * setShowRWave(true,0);
       * setShowWave(true);
       * addObject("film","n=0.0,w=20");
       * // this.setTimeDisplay(true);
       * this.setAmpDisplay(true);
       * this.setPhaseDisplay(true);
       * this.setTimeDisplay(true);
       */
      //addObject("film","n=1.0,w=20");
      /*
       * setWavelength(6);
       * double indN=1.2247;
       * double wid=0.25*6/indN;        // a thin film with 1/4 wave anti=reflection coating.
       * addObject("film","n=1,w=10");
       * int id2=addObject("film","n="+indN+",w="+wid);
       * int id3=addObject("film","n=1.5,w=20");
       * setRGB(id2,200,255,200);
       * setRGB(id3,200,200,255);
       */
      setAutoRefresh(true);
      forward();
      firstTime = false;
    }
    super.start();
    /*
     *    int id=addObject("rectangle","x=20,y=0,w=100,h=50");
     *    setRGB(id,255,0,0);
     *    addObject("caption","text=hi");
     */
  }

  /**
   *
   * Method sets velocity of animated wave in units per time
   * Positive velocity moves wave to right.
   *
   * @param v double
   */
  public void setWaveVelocity(double v) {
    wavePanel.setWaveVelocity(v);
  }

  /**
   * Create an object and add it to the Physlet.
   * The first argument is the name of the object to be added and the second is a
   * comma-delimited list of parameters.  For example, a layer if width and index
   * of refraction 2.4 can be added a follows:
   * <p>
   * <code>addObject ("layer", "w=1.5, n=2.4");</code>
   * </p>
   * See the supplemental documentation for a list of
   * <a href="doc-files/Reflection_addObject.html">object names and parameters.</a>
   *
   * @param              name the type of object to be created.
   * @param              parList a list of parameters to be set
   * @return             id that identifies the object.
   */
  public synchronized int addObject(String name, String parList) {
    int    id = 0;
    double w  = 1;    //width of object
    double n  = 1.0;  // index of refraction
    name = name.toLowerCase().trim();
    name = SUtil.removeWhitespace(name);
    String parList2 = parList.trim();
    ;
    parList = SUtil.removeWhitespace(parList);
    if(name.equals("film")||name.equals("layer")||name.equals("medium")) {
      if(SUtil.parameterExist(parList, "w=")) {
        w = SUtil.getParam(parList, "w=");
      }
      if(SUtil.parameterExist(parList, "n=")) {
        n = SUtil.getParam(parList, "n=");
      }
      if(SUtil.parameterExist(parList, "e=")) {
        n = SUtil.getParam(parList, "e=");
      }
      id = this.addMedium(n, w);
    }
    if(id==0) {
      id = addDrawingObject(name, parList2);
    }
    if(id==0) {
      System.out.println("Object not created. name:"+name+"parameter list:"+parList);
    }
    return id;
  }

  private int addDrawingObject(String name, String parList) {
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
    Thing t = null;
    if(name.equals("box")) {
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
      t = new BoxThing(this, wavePanel, x, y, width, height);
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
      t = new RectangleThing(this, wavePanel, x, y, width, height);
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
      t = new CircleThing(this, wavePanel, x, y, r);
    } else if(name.equals("arrow")) {
      double h = 1, v = 1;
      s = 4;
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
        h = SUtil.getParam(parList, "h=");
      }
      if(SUtil.parameterExist(parList, "v=")) {
        v = SUtil.getParam(parList, "v=");
      }
      t = new ArrowThing(this, wavePanel, s, h, v, x, y);
      if(SUtil.parameterExist(parList, "thickness=")) {
        ((ArrowThing) t).thickness = Math.max((int) SUtil.getParam(parList, "thickness="), 1);
      }
    } else if(name.equals("text")||name.equals("calculation")) {
      String txt  = "";
      String calc = "";
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
      t = new TextThing(this, wavePanel, txt, calc, x, y);
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
      t = new CaptionThing(this, wavePanel, txt, x, y);
    }
    if((t!=null)&&SUtil.parameterExist(parList, "label=")) {
      t.setLabel(SUtil.getParamStr(parList2, "label="));
    }
    if(t!=null) {
      wavePanel.drawingThings.addElement(t);
      return t.getID();
    }
    return 0;
  }

  /**
   *
   * Remove an object from the applet.
   *
   * @param id
   */
  public void removeObject(int id) {
    wavePanel.removeObject(id);
  }

  /**
   *
   * Adds a medium with given index of refraction
   * When creating several media, the order in which method is called is insignificant
   *
   * @param n double index of refraction
   * @param w double width of medium in units
   *
   * @return  int  the object identifier
   *
   * @deprecated        Replaced by the addObject(String String) method.
   */
  public int addMedium(double n, double w) {
    ReflectionThing t = wavePanel.addMedium(n, w);
    indexField.setValue(n);
    mediaField.setValue(wavePanel.thingVector.size());
    return t.hashCode();
  }

  /**
   * Gets the object identifier for the wave  data source.
   *
   * @return int the identifier
   */
  public int getWaveID() {
    return wavePanel.getWaveID();
  }

  /**
   * Gets the reflection coef.
   *
   * @return double R
   */
  public double getReflection() {
    return Math.max(0, wavePanel.refC);
  }

  /**
   * Gets the tranmission coef.
   *
   * @return double T
   */
  public double getTransmission() {
    return Math.max(0, wavePanel.tranC);
  }

  /**
   *
   * Method removes all objects from animation
   *
   */
  public void removeAllMedia() {
    wavePanel.removeAllThings();
    indexField.setValue(1.0);
    mediaField.setValue(1);
  }

  /**
   * Make the object with the given id dragable.
   *
   * @param              id The id of the object.
   * @param              drag Dragable?
   * @return             True if successful.
   */
  public boolean setDragable(int id, boolean drag) {
    ReflectionThing t = wavePanel.getReflectionThing(id);
    if(t!=null) {
      t.dragable = drag;
      return true;
    }
    Thing dt = wavePanel.getDrawingThing(id);
    if(dt!=null) {
      dt.setDragable(drag);
      return true;
    }
    return false;
  }

  /**
   *
   * Method sets color of a medium
   *
   * @param id  the object identifier
   * @param r  red value 0 <= r <= 255
   * @param g  green value 0 <= g <= 255
   * @param b  blue value 0 <= b <= 255
   *
   */
  public void setRGB(int id, int r, int g, int b) {
    wavePanel.setRGB(id, r, g, b);
  }

  /**
   *
   * Method sets Max amplitude in units. All waves will be scaled down accordingly.
   *
   * @param max double
   */
  public void setMagMax(double max) {
    wavePanel.setMagMax(max);
  }

  /**
   *
   * method sets wavelength of incident wave in free space
   *
   * @param lambda wavelength in units
   */
  public void setWavelength(double lambda) {
    if(lambda>0) {
      wavePanel.setWavelength(lambda);
    }
  }

  /**
   *
   * Method sets the width of a given medium
   *
   * @param id int hashcode id of medium
   * @param w
   *
   */
  public void setMediaWidth(int id, double w) {
    wavePanel.setMediaWidth(id, w);
  }

  /**
   *
   * Method recalculates waves. Should be called each time an index of refraction (or potential) changes,
   * or a new medium is added.
   *
   */
  public void recalculate() {
    wavePanel.recalc();
  }

  /**
   *
   * Turns auto-refresh feature on and off.  Whenever some significant parameter (like width or wavelength) is updated,
   * the calculations and painting will be performed again, unless autoRefresh is turned off.
   *
   * @param autoRefresh boolean
   */
  public void setAutoRefresh(boolean autoRefresh) {
    this.autoRefresh = autoRefresh;
    wavePanel.setAutoRefresh(autoRefresh);
  }

  /**
   * Change the object's font for any text that is displayed.
   *
   * An id of zero will change the font for the time display in the wave panel.
   *
   * @param              id The id of the object.
   * @param              family The font family: Helvetica, Times.
   * @param              style The style, 0=plain, 1=bold.
   * @param              size The size of the font;
   * @return             true if successful.
   */
  public boolean setFont(int id, String family, int style, int size) {
    Font font = new Font(family, style, size);
    Thing dt = wavePanel.getDrawingThing(id);
    if(dt!=null) {
      dt.setFont(font);
      return true;
    }
    ReflectionThing t = wavePanel.getReflectionThing(id);
    if((t==null)&&((id==0)||(id==wavePanel.hashCode()))) {
      wavePanel.font = font;
      return true;
    }
    if(t==null) {
      return false;
    }
    t.font = font;
    if(wavePanel.autoRefresh) {
      wavePanel.repaint();
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
   *   Change the object's format for the display of numeric data.
   *
   *   Us this method to control the number of significant digits in calculations with text objects.
   *   Use Unix printf conventions.  For example fstr="%6.3f"
   *
   *   @param              id The id of the object.
   *   @param              fstr the format string.
   *   @return             True if successful.
   */
  public boolean setFormat(int id, String fstr) {
    ReflectionThing t = wavePanel.getReflectionThing(id);
    if((t==null)&&((id==0)||(id==wavePanel.hashCode()))) {
      return wavePanel.setFormat(fstr);
    }
    boolean result = t.setFormat(fstr);
    if(wavePanel.autoRefresh) {
      wavePanel.repaint();
    }
    return result;
  }

  /**
   *
   * Add a small label at bottom center of a medium
   *
   * @param id the object identifier
   * @param label the label
   */
  public void setLabel(int id, String label) {
    wavePanel.setCaption(id, label);
  }

  /**
   * Set the title.
   *
   * @param              str Title string.
   */
  public void setTitle(String str) {
    wavePanel.setTitle(str);
  }

  /**
   * Enable the energy display in the applet window.
   *
   * @param       show true will display energy
   */
  public void setEnergyDisplay(boolean show) {
    wavePanel.setEnergyDisplay(show);
    if(autoRefresh) {
      wavePanel.repaint();
    }
  }

  /**
   * Enable the time display in the applet window.
   *
   * @param      show true will display time
   */
  public void setTimeDisplay(boolean show) {
    wavePanel.setTimeDisplay(show);
    if(autoRefresh) {
      wavePanel.repaint();
    }
  }

  /**
   * Enable the amplitude display on with click-drag.
   *
   * @param      show true will display amplitude
   */
  public void setAmpDisplay(boolean show) {
    wavePanel.setAmpDisplay(show);
  }

  /**
   * Enable the coordinate display on with click-drag.
   *
   * @param   show true will display coordinates
   */
  public void setCoordDisplay(boolean show) {
    wavePanel.setCoordDisplay(show);
  }

  /**
   * Enable the phase display on with click-drag.
   *
   * @param           show true will display phase
   */
  public void setPhaseDisplay(boolean show) {
    wavePanel.setPhaseDisplay(show);
  }

  /**
   *
   * Enable the display of either the index of refraction or the potential
   *
   * @param id  the object identifier
   * @param tf boolean enable or disable
   *
   * @return   true if successful
   */
  public boolean setShowValue(int id, boolean tf) {
    ReflectionThing t = wavePanel.getReflectionThing(id);
    if(t==null) {
      return false;
    }
    t.setShowValue(tf);
    return true;
  }

  /**
   * Show the visibility of the object.
   *
   * @param id
   * @param     vis show object on screen?
   *
   * @return   true if successful
   */
  public boolean setVisibility(int id, boolean vis) {
    ReflectionThing t = wavePanel.getReflectionThing(id);
    if(t!=null) {
      t.setVisibility(vis);
      return true;
    }
    Thing dt = wavePanel.getDrawingThing(id);
    if(dt!=null) {
      dt.setDragable(vis);
      return true;
    }
    return false;
  }

  /**
   *
   * Enable the display of the total wave.
   *
   * @param  show the wave
   */
  public void setShowWave(boolean show) {
    wavePanel.showWave = show;
    if(autoRefresh) {
      wavePanel.recalc();
    }
  }

  /**
   *
   * Enable the display of the left traveling wave or the imaginary part of the wavefunction.
   *
   * @param  show the L wave
   * @param pixOffset
   */
  public void setShowLWave(boolean show, int pixOffset) {
    wavePanel.showLWave     = show;
    wavePanel.leftPixOffset = pixOffset;
    if(autoRefresh) {
      wavePanel.recalc();
    }
  }

  /**
   *
   * Method enables the display of color for phase in QM mode.
   *
   * @param  show the color
   */
  public void setShowPhaseColor(boolean show) {
    wavePanel.showPhaseColor = show;
    if(show) {
      wavePanel.showWave = true;
    }
    if(autoRefresh) {
      wavePanel.recalc();
    }
  }

  /**
   *
   * Enable the display of the right traveling wave or the real part of the wavefunction.
   *
   * @param  show the R wave
   * @param pixOffset
   */
  public void setShowRWave(boolean show, int pixOffset) {
    wavePanel.showRWave      = show;
    wavePanel.rightPixOffset = pixOffset;
    if(autoRefresh) {
      wavePanel.recalc();
    }
  }

  /**
   * Method initBtn_actionPerformed
   *
   * @param e
   */
  void initBtn_actionPerformed(ActionEvent e) {
    setDefault();
  }

  /**
   * Method changeBtn_actionPerformed
   *
   * @param e
   */
  void changeBtn_actionPerformed(ActionEvent e) {
    double index = indexField.getValue();
    if(index<0) {
      indexField.setBackground(Color.red);
      return;
    }
    indexField.setBackground(Color.white);
    int mf = mediaField.getValue();
    if((mf<1)) {
      mediaField.setBackground(Color.red);
      return;
    }
    mediaField.setBackground(Color.white);
    if((mf>wavePanel.thingVector.size())) {  // see if we need to add an object
      setAutoRefresh(false);
      double w = 40.0/ppu;
      if(mf==1) {
        w = 60;
      }
      int id = addMedium(index, w);
      setDragable(id, true);
      //mediaField.setValue(wavePanel.thingVector.size());
      setRGB(id, (int) (255*Math.random()), 255, (int) (255*Math.random()));
      mediaField.setBackground(Color.white);
      setAutoRefresh(true);
      return;
    }
    ReflectionThing t = (ReflectionThing) wavePanel.thingVector.elementAt(mf-1);
    wavePanel.setIndexN(t.getID(), index);
    recalculate();
  }

  /**
   * Method runBtn_actionPerformed
   *
   * @param e
   */
  void runBtn_actionPerformed(ActionEvent e) {
    if(clock.isRunning()) {
      pause();
      runBtn.setLabel(button_start);
    } else {
      forward();
      runBtn.setLabel(button_stop);
    }
  }

  /**
   * Method stepBtn_actionPerformed
   *
   * @param e
   */
  void stepBtn_actionPerformed(ActionEvent e) {
    step();
  }

  /**
   * Method mediaField_actionPerformed
   *
   * @param e
   */
  void mediaField_actionPerformed(ActionEvent e) {
    int             i = mediaField.getValue();
    ReflectionThing t = wavePanel.getReflectionThingFromIndex(i-1);
    if(t!=null) {
      indexField.setValue(t.indexN);
    }
  }
}
