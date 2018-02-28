/*
 * BlackBody.java
 * Copyright 1998
 * Michael J. Lee and Wolfgang Christian
 * Davidson College
 *
 * This applet is free of use for eduacational purposes.
 *
 * For more information contact W. Christian
 * at wochristian@davidson.edu
 *
 */
package blackbody;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;
import edu.davidson.graphics.*;

/**
 * Class BlackBody
 *
 *
 * @author W. Christian
 */
public class BlackBody extends edu.davidson.tools.SApplet implements Runnable, edu.davidson.tools.SDataSource {
  /**
	 * 
	 */
	private static final long serialVersionUID = 7899351657346974819L;
private String label_wavelength = "Wavelength [m]";
  private String label_appearance = "Appearance";
  private String label_title = "Blackbody Radiation";
  private String label_energydensity = "Energy Density [J/m^{4}]";
  private String label_lambdamax = "lambda max =";
  private String label_r = "R";
  private String label_g = "G";
  private String label_b = "B";
  private String label_t = "T=";
  private String label_autox = "Auto X";
  private String label_autoy = "Auto Y";
  static double  h           = 6.626075E-34, c = 2.99792458E8, k = 1.380658E-23, pi = Math.PI, exp = Math.E,
                 phcc = (2 * pi * h * c * c), hc = h * c;
  static int     np          = 1000;
  static int     halfWidth   = 160;
  static int[]   saturation  = new int[halfWidth * 2];
  Object         lock        = new Object();
  private Thread paintThread = null;
  String[]       varStrings  = new String[]{"t", "lambda"};
  double[][]     ds          = new double[1][2];             // the datasource state variables r, t;
  BBGraph        graph       = new BBGraph(this);
  //BevelPanel swatch = new BevelPanel();
  PyroPanel      swatch      = new PyroPanel();
  Checkbox       autox       = new Checkbox();
  Checkbox       autoy       = new Checkbox();
  SNumber        redNumber   = new SNumber();
  SNumber        greenNumber = new SNumber();
  SNumber        blueNumber  = new SNumber();
  Label          label1      = new Label();
  Label          label2      = new Label();
  Label          label3      = new Label();
  SNumber        totalNumber = new SNumber();
  EtchedBorder   bevelPanel1 = new EtchedBorder();
  FlowLayout     flowLayout1 = new FlowLayout();
  Label          label4      = new Label();
  EtchedBorder   bevelPanel2 = new EtchedBorder();
  EtchedBorder   bevelPanel3 = new EtchedBorder();
  //STimer timer=new STimer();
  double         temperature, lambda;
  int            r, g, b, greenColor;                        //the colors in scale between 0 and 255.
  /*
   * np is the number of data points on the graph.
   * x[] and y[] are global so they can be used in the inner class.
   */
  boolean        showControls = true;                        //whether or not to show controls, default = 1, yes.
  boolean        colorSwatch  = true,                        //whether or not to show color swatch, default =1, yes.
                 enableCursor = true;                        //whether or not to allow cursor to change max lambda value
  int            i            = 0, xPos1, xPos2;
  double[]       x             = new double[np];
  double[]       y             = new double[np];
  //boolean validImage = false;
  boolean        newData       = false;
  //the array that holds the colors
  BorderLayout   borderLayout3 = new BorderLayout();
  Label          label5        = new Label();
  Label          label6        = new Label();
  BorderLayout   borderLayout4 = new BorderLayout();
  Format         mouseFormat   = new Format("%-3.3g");
  SSlider        slider        = new SSlider();
  SNumber        tempNumber    = new SNumber();
  BorderLayout   borderLayout1 = new BorderLayout();
  BorderLayout   borderLayout2 = new BorderLayout();
  //fill the saturation array

  /**
   * Calculates the saturation.  Do not script.
   *
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
  public static void calSaturation() {
    int w = halfWidth * 2;
    for(int i = 0; i < w; i++) {
      double arg = 1.4 * (i - halfWidth) / halfWidth;
      saturation[i] = (int) (255 * Math.exp(-arg * arg));
      ;
    }
  }

  /**
   * Constructor BlackBody
   * @y.exclude
   */
  public BlackBody() {}

  protected void setResources () {
    label_wavelength = localProperties.getProperty("label.wavelength", label_wavelength);
    label_appearance = localProperties.getProperty("label.appearance", label_appearance);
    label_title = localProperties.getProperty("label.title", label_title);
    label_energydensity = localProperties.getProperty("label.energydensity", label_energydensity);
    label_lambdamax = localProperties.getProperty("label.lambdamax", label_lambdamax);
    label_r = localProperties.getProperty("label.r", label_r);
    label_g = localProperties.getProperty("label.g", label_g);
    label_b = localProperties.getProperty("label.b", label_b);
    label_t = localProperties.getProperty("label.t", label_t);
    label_autox = localProperties.getProperty("label.autox", label_autox);
    label_autoy = localProperties.getProperty("label.autoy", label_autoy);
  }


  /**
   * Method init
   *
   * Exclude the javadoc because this method should not be scripted.
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
      colorSwatch = Boolean.valueOf(this.getParameter("ColorSwatch", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      enableCursor = Boolean.valueOf(this.getParameter("EnableCursor", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      temperature = Double.valueOf(this.getParameter("Temp", "5321")).doubleValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    graph.setSeriesStyle(1, Color.red, true, 0);
    graph.setAutoReplaceData(1, true);
    calSaturation();
    //timer.setClient(this);
    tempNumber.setFormat("%+6.2f");
    // graph.setBorders("0,10,0,0");
    paintThread = new Thread(this);
    paintThread.start();
    try {
      SApplet.addDataSource(this);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method jbInit
   *
   *
   * @throws Exception
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
  public void jbInit() throws Exception {
    this.setBackground(Color.white);
    /** @j2sNative */{
    	  this.setSize(new Dimension(445, 378));
    }
    graph.setBorders("20,20,50,20");
    graph.setLabelY(label_energydensity);
    graph.addMouseMotionListener(new BlackBody_graph_mouseMotionAdapter(this));
    graph.addMouseListener(new BlackBody_graph_mouseAdapter(this));
    autox.setState(true);
    autox.addItemListener(new BlackBody_autox_itemAdapter(this));
    autox.setVisible(true);
    autox.setLabel(label_autox);
    autoy.setState(true);
    autoy.addItemListener(new BlackBody_autoy_itemAdapter(this));
    swatch.setLayout(borderLayout4);
    label1.setText("blue");
    label2.setText("green");
    label3.setText("red");
    bevelPanel1.setVisible(false);
    label4.setText("total");
    //bevelPanel2.setMargins(new Insets(5, 5, 5, 5));
    //bevelPanel3.setMargins(new Insets(0, 5, 0, 0));
    label5.setText(label_title);
    label6.setBackground(Color.black);
    label6.setForeground(Color.white);
    label6.setText("        Colors");
    slider.addMouseListener(new BlackBody_slider_mouseAdapter(this));
    slider.addAdjustmentListener(new BlackBody_slider_adjustmentAdapter(this));
    bevelPanel3.setLayout(borderLayout3);
    bevelPanel2.setLayout(borderLayout2);
    bevelPanel1.setLayout(flowLayout1);
    redNumber.addActionListener(new BlackBody_redNumber_actionAdapter(this));
    autoy.setVisible(true);
    autoy.setLabel(label_autoy);
    this.setLayout(borderLayout1);
    this.add(graph, BorderLayout.CENTER);
    if(colorSwatch) {
      this.add(swatch, BorderLayout.WEST);
    }
    //this.add(swatch, BorderLayout.WEST);
    // swatch.add(label6, BorderLayout.SOUTH);
    swatch.setBackground(Color.black);
    graph.setBackground(new Color(230, 230, 230));
    graph.setLabelX(label_wavelength);
    if(showControls) {
      this.add(bevelPanel2, BorderLayout.SOUTH);
    }
    //this.add(bevelPanel2, BorderLayout.SOUTH);
    bevelPanel2.add(bevelPanel1, BorderLayout.NORTH);
    bevelPanel1.add(label1, null);
    bevelPanel1.add(blueNumber, null);
    bevelPanel1.add(label2, null);
    bevelPanel1.add(greenNumber, null);
    bevelPanel1.add(label3, null);
    bevelPanel1.add(redNumber, null);
    bevelPanel1.add(label4, null);
    bevelPanel1.add(totalNumber, null);
    bevelPanel2.add(bevelPanel3, BorderLayout.SOUTH);
    bevelPanel2.setBackground(Color.lightGray);
    bevelPanel3.setBackground(Color.lightGray);
    bevelPanel3.add(autox, BorderLayout.WEST);
    bevelPanel3.add(autoy, BorderLayout.CENTER);
    bevelPanel3.add(label5, BorderLayout.EAST);
    bevelPanel2.add(slider, BorderLayout.CENTER);
    bevelPanel2.add(tempNumber, BorderLayout.EAST);
    setT(temperature);
    slider.setDValue(temperature / 10000);
    tempNumber.addActionListener(new BlackBody_tempNumber_actionAdapter(this));
  }
  /**
   * Method start
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void start() {
    synchronized(lock) {
      newData       = true;
      graph.iheight = 0;
      lock.notify();
    }
    super.start();
    //this.addCaption("test");
  }

  /**
   * Sets default values and deletes all data connections.
   */
  public void setDefault() {
    pause();
    deleteDataConnections();  // we are going to delete all the things so we might as well kill the conections too.
    graph.clearAllThings();
  }

  /**
   * Destroys the applet.  Do not script.
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void destroy() {
    destroyed   = true;
    autoRefresh = false;
    graph.destroy();
    paintThread = null;
    synchronized(lock) {
      newData = true;
      lock.notify();
    }
    super.destroy();
  }


  /**
   * Gets the applet information.
   *
   * @return the information
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Name: BlackBody\r\n" + "Author: Wolfgang Christian and Mike Lee\r\n" + "email:wochristian@davidson.edu";
  }

  /**
   * Gets the embedding parameter information.
   * @return the parameter information
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String pinfo[][] = {
      {"Temp", "double", "Temperature"}, {"ShowControls", "boolean", "whether or not to show UI controls"},
      {"colorSwatch", "integer", "whether or not to show color swatch"},
      {"EnableCursor", "integer", "whether or not to enable cursor"}
    };
    return pinfo;
  }

  /**
   * Gets the datasource variable strings.
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   * @return the variables
   */
  public String[] getVarStrings() {
    return varStrings;
  }

  /**
   * Method setOwner
   * Exclude the javadoc because this method should not be scripted.
   *
   * @param applet
   * @y.exclude
   */
  public void setOwner(SApplet applet) {
    ;
  }

  /**
   * Do not script.
   *
   * @return this
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
  public SApplet getOwner() {
    return this;
  }  //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"

  /**
   * Gets the datasource values.
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   *
   * @return the values
   */
  public double[][] getVariables() {
    ds[0][0] = temperature;
    ds[0][1] = lambda * 1E9;
    return ds;
  }

  /**
   * Adds a caption.
   *
   * @param txt
   * @return  the id
   */
  public int addCaption(String txt) {
    Thing t = new CaptionThing(this, graph, txt, 0, 0);
    t.setDisplayOff(0, -40);
    int id = graph.addThing(t);
    synchronized(lock) {
      newData = true;
      lock.notify();
    }
    graph.repaint();
    return id;
  }

  /**
   *  Sets the color of the object.
   *
   *  @param              id The series id.
   *  @param              r red.
   *  @param              g green.
   *  @param              b blue.
   */
  public synchronized void setRGB(int id, int r, int g, int b) {
    Color c = new Color(r, g, b);
    graph.setObjectColor(id, c);
  }

  /**
   *  Offsets the object's position on the screen from its default drawing
   *  position.
   *
   *  @param              id The id of the object.
   * @param xOff
   * @param yOff
   *  @return             True if successful.
   */
  public boolean setDisplayOffset(int id, int xOff, int yOff) {
    Thing t = graph.getThing(id);
    if(t == null) {
      return false;
    }
    t.setDisplayOff(xOff, yOff);
    synchronized(lock) {
      newData = true;
      lock.notify();
    }
    graph.repaint();
    return true;
  }

  //my method subroutine to paint the graph data

  /**
   * Sets the temperature.
   * Same as setT.
   *
   * @param temp
   */
  public void setTemperature(double temp) {
    setT(temp);
  }

  /**
   * Sets the temperature.
   *
   * Same as setTemperature.
   *
   * @param temp
   */
  public void setT(double temp) {
    //newData=true;
    graph.clearSeries(1);
    //Multiplicative factor sets maximum temperature.
    double maximumXValue = 1000E-9,  //sets maximum scale on the x axis (in nm)
           delta = maximumXValue / np;
    temperature = temp;
    double redValue = 0, greenValue = 0, blueValue = 0, totalValue = 0, maxValue = 0, redUnrounded,
           greenUnrounded, blueUnrounded;
    //double areaUnderCurve = 0;
    double colorScale = np / 60.0;
    for(i = 1; i < np; i++) {
      //to figure formula for x[i] divide the maximum
      //value you want for your wavelength scale by
      //np (in this case 60), and multiply that number
      //by i.  Mathematica helps a lot in estimating.
      //Total x[i] range can be found by finding x[60].
      x[i] = i * delta;
      //old y[i] = 39.4761E+8/((Math.pow(exp,1/(i*T))-1)*Math.pow(i,5));
      if(x[i] * k * temperature <= 0) {
        y[i] = 0;
      } else {
        y[i] = phcc / ((Math.pow(exp, hc / (x[i] * k * temperature)) - 1) * Math.pow(x[i], 5));
      }
      //To set the integration ranges properly for the
      //three colors, divide the entire range into
      //np pieces (in this case 60), and proportion the
      //splits according to the 400,560,700 nm values.
      //Blue is 320 to 480 nm
      //Green is 480 to 630 nm
      //Red is 630 to 770 nm
      if(i >= 19 * colorScale & i <= 29 * colorScale) {
        blueValue = blueValue + y[i];
      }
      if(i >= 29 * colorScale & i <= 38 * colorScale) {
        greenValue = greenValue + y[i];
      }
      if(i >= 38 * colorScale & i <= 46 * colorScale) {
        redValue = redValue + y[i];
      }
      //if (i>=1 & i<=60) areaUnderCurve = areaUnderCurve + y[i];
      // x[i]=x[i]*1.0E+9;  // change to nm added by wc.
    }
    totalValue = blueValue + greenValue + redValue;
    // System.out.println("red= " + redValue +"   green= " + greenValue+"    blue= " + blueValue);
    maxValue   = Math.max(blueValue, redValue);
    maxValue   = Math.max(maxValue, greenValue);
    if(redValue < 6.158) {
      redUnrounded = 0;
    } else {
      redUnrounded = 255.0 * redValue / maxValue * (Math.log(totalValue) / 20);
    }
    if(greenValue < 6.158) {
      greenUnrounded = 0;
    } else {
      greenUnrounded = 255.0 * greenValue / maxValue * (Math.log(totalValue) / 20);
    }
    if(blueValue < +6.158) {
      blueUnrounded = 0;
    } else {
      blueUnrounded = 255.0 * blueValue / maxValue * (Math.log(totalValue) / 20);
    }
    //double redUnrounded = 255.0*redValue/maxValue*(Math.log(totalValue)/15),
    //       greenUnrounded = 255.0*greenValue/maxValue*(Math.log(totalValue)/15),
    //       blueUnrounded = 255.0*blueValue/maxValue*(Math.log(totalValue)/15);
    //THIS USES LOGARITHMIC SCALES FOR THE COLOR MAGNITUDES!!!
    //double redUnrounded = 255.0*Math.log(redValue)/(16.051538),
    //      greenUnrounded = 255.0*Math.log(greenValue)/(15.7000011),
    //       blueUnrounded = 255.0*Math.log(blueValue)/(15.4479329);
    r          = (int) Math.min(redUnrounded, 255);
    g          = (int) Math.min(greenUnrounded, 255);
    greenColor = g;
    b          = (int) Math.min(blueUnrounded, 255);
    //if (r>255) r = 255;
    //if (greenColor>255) greenColor = 255;
    //if (b>255) b = 255;
    //if (b<5) b=1;
    redNumber.setValue(redValue);
    greenNumber.setValue(greenValue);
    blueNumber.setValue(blueValue);
    totalNumber.setValue(totalValue);
    graph.addData(1, x, y);
    //swatch.setBackground(new Color(r,g,b));
    swatch.repaint();
    tempNumber.setValue(temperature);
    lambda = 2.9E-3 / temperature;
    synchronized(lock) {
      newData = true;
      lock.notify();
    }
    updateDataConnections();
  }

  /**
   * Runs the thread.  Do not script.
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
  public void run() {
    //System.out.println("Paintthread start");
    while(paintThread != null) {
      synchronized(lock) {
        if(!newData) {
          try {
            lock.wait();
          } catch(InterruptedException ie) {}
        }
        newData = false;
        if(paintThread != null) {
          graph.paintImage();
        }
      }
      if(paintThread != null) {
        try {
          Thread.sleep(50);
        } catch(InterruptedException ie) {}
      }
    }
  }

  /**
   * Sets autoscale for the x and y axes.
   *
   * @param x true to autoscale the x axis; false otherwise
   * @param y true to autoscale the y axis; false otherwise
   */
  public void setAutoscale(boolean x, boolean y) {
    if(x) {
      graph.setAutoscaleX(true);
    } else {
      graph.setAutoscaleX(false);
    }
    if(y) {
      graph.setAutoscaleY(true);
    } else {
      graph.setAutoscaleY(false);
    }
  }

  /**
   * Method slider_adjustmentValueChanged
   *
   * @param e
   */
  void slider_adjustmentValueChanged(AdjustmentEvent e) {
    //newData=true;
    setT(slider.getDValue() * 10000);
    graph.repaint();
  }

  /**
   * Method redNumber_actionPerformed
   *
   * @param e
   */
  void redNumber_actionPerformed(ActionEvent e) {}

  /**
   * Class BBGraph
   *
   *
   * @author
   * @version %I%, %G%
   */
  class BBGraph extends SGraph {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5479738146106405139L;
	int       iheight = 0, iwidth = 0;
    Image     osi     = null;
    BlackBody applet;

    /**
     * Constructor BBGraph
     *
     * @param app
     */
    BBGraph(BlackBody app) {
      super(app);
      applet = app;
    }

    /**
     * Method paint
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     * @param g
     */
    public void paint(Graphics g) {
      if(applet.destroyed) {
        return;
      }
      synchronized(lock) {
        if(!newData && (osi != null)) {
          g.drawImage(osi, 0, 0, this);
        } else {
          super.paint(g);
        }
      }
    }

    /**
     * Method paintImage
     * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
    public void paintImage() {
      if(applet.destroyed) {
        return;
      }
      if((osi == null) || (iwidth != this.getSize().width) || (iheight != this.getSize().height)) {
        iwidth  = this.getSize().width;
        iheight = this.getSize().height;
        if((iheight < 10) || (iwidth < 10)) {
          osi = null;
          return;
        }
        osi = createImage(iwidth, iheight);  //create an off screen image
      }
      if(osi == null) {
        return;
      }
      Graphics osg = osi.getGraphics();
      osg.setColor(getBackground());
      osg.fillRect(0, 0, iwidth, iheight);
      osg.setColor(Color.black);
      if(applet.destroyed) {
        return;
      }
      super.paint(osg);
      //paintSpectrum(osg);
      osg.dispose();
      //newData=false;
      // repaint();
      Graphics g = getGraphics();
      g.drawImage(osi, 0, 0, this);
      g.dispose();
    }

    /*
     *   public void paintFirst(Graphics g, Rectangle r){
     *     if( osi == null || iwidth != this.getSize().width || iheight != this.getSize().height  ){
     *       iwidth = this.getSize().width;
     *       iheight = this.getSize().height;
     *       osi = createImage(iwidth,iheight);       //create an off screen image
     *       newData=true;
     *     }
     *   }
     */

    /**
     * Method getIntensity
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     * @param x
     *
     * @return
     */
    public final double getIntensity(double x) {
      if(x * k * temperature <= 0) {
        return 0;
      }
      return phcc / ((Math.pow(exp, hc / (x * k * temperature)) - 1) * Math.pow(x, 5));
    }

    /**
     * Method getColor
     * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     * @param x
     *
     * @return
     */
    public final Color getColor(double x) {
      int r  = 0, g = 0, b = 0;
      int nm = (int) (x * 1.0e9);
      if((nm > 400 - halfWidth) && (nm < 400 + halfWidth)) {
        b = saturation[nm - 400 + halfWidth];
      }
      if((nm > 500 - halfWidth) && (nm < 500 + halfWidth)) {
        g = saturation[nm - 500 + halfWidth];
      }
      if((nm > 600 - halfWidth) && (nm < 600 + halfWidth)) {
        r = saturation[nm - 600 + halfWidth];
      }
      return new Color(r, g, b);
    }

    /**
     * Method paintLast
     * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     * @param g
     * @param r
     */
    public void paintLast(Graphics g, Rectangle r) {
      if(applet.destroyed) {
        return;
      }
      paintSpectrum(g);
      super.paintLast(g, r);
    }

    /**
     * Method paintSpectrum
     * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     * @param g
     */
    public void paintSpectrum(Graphics g) {
      if(applet.destroyed) {
        return;
      }
      int    iStart = pixFromX(x[1]);
      int    iEnd   = pixFromX(x[np - 1]);
      int    y0     = pixFromY(0);
      double y      = 0;
      double x      = 0;
      for(int i = iStart; i < iEnd; i++) {
        x = xFromPix(i);
        y = getIntensity(x);
        g.setColor(getColor(x));
        g.drawLine(i, y0, i, pixFromY(y));
      }
    }
  }

  /**
   * Class PyroPanel
   *
   *
   * @author
   * @version %I%, %G%
   */
  class PyroPanel extends SPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8122847632468739245L;

	/**
     * Constructor PyroPanel
     *
     */
    public PyroPanel() {
      setMinimumSize(new Dimension(80, 100));
      setPreferredSize(new Dimension(80, 100));
    }

    /**
     * Method paint
     *
     * @param g
     */
    public void paint(Graphics g) {
      //Set PyroPanel background to black--in the jbinit()
      g.setColor(new Color(r, 0, 0));
      g.fillOval(getBounds().width / 2 - 15, 15, 30, 30);
      //'R' text in white
      g.setColor(Color.white);
      g.drawString(label_r, getBounds().width / 2 - 30, 35);
      //green circle
      g.setColor(new Color(0, greenColor, 0));
      g.fillOval(getBounds().width / 2 - 15, 50, 30, 30);
      //'G' text in white
      g.setColor(Color.white);
      g.drawString(label_g, getBounds().width / 2 - 30, 70);
      //blue circle
      g.setColor(new Color(0, 0, b));
      g.fillOval(getBounds().width / 2 - 15, 85, 30, 30);
      //'B' text in white
      g.setColor(Color.white);
      g.drawString(label_r, getBounds().width / 2 - 30, 105);
      //total circle
      g.setColor(new Color(r, greenColor, b));
      g.fillOval(getBounds().width / 2 - 20, 150, 40, 40);
      //'Composite' text in white
      g.setColor(Color.white);
      g.drawString(label_appearance, getBounds().width / 2 - 35, 210);
      g.setColor(Color.white);
      g.drawString(label_t + (long) temperature + " K", getBounds().width / 2 - 30, 240);
    }
  }

  /**
   * Method graph_mousePressed
   *
   * @param e
   */
  void graph_mousePressed(MouseEvent e) {
    if(enableCursor) {
      Graphics g = graph.getGraphics();
      g.setColor(Color.yellow);
      g.fillRect(25, graph.pixFromY(0) + 28, 155, 15);
      lambda = 2.9E-3 / temperature;
      xPos1  = graph.pixFromX(lambda);
      g.setColor(Color.blue);
      g.drawLine(xPos1, 0, xPos1, 12);
      g.drawLine(xPos1, 12, xPos1 - 3, 9);
      g.drawLine(xPos1, 12, xPos1 + 3, 9);
      xPos2 = e.getX();
      if(xPos2 <= graph.pixFromX(0)) {
        xPos2 = graph.pixFromX(0) + 1;
      }
      if(xPos2 >= graph.pixFromX(1000E-9)) {
        xPos2 = graph.pixFromX(1000E-9);
      }
      g.drawLine(xPos2, 0, xPos2, 12);
      g.drawLine(xPos2, 12, xPos2 - 3, 9);
      g.drawLine(xPos2, 12, xPos2 + 3, 9);
      lambda = graph.xFromPix(xPos2);
      g.drawString(label_lambdamax+ " " + mouseFormat.form(lambda * 1E9) + " nm", 30, graph.pixFromY(0) + 40);
      slider.setDValue(((2.9E-3 / lambda)) / 10000);
      tempNumber.setValue(((2.9E-3 / lambda)));
      temperature = 2.9E-3 / lambda;
      g.dispose();  //added by wc
      updateDataConnections();
    }
  }

  /**
   * Method graph_mouseDragged
   *
   * @param e
   */
  void graph_mouseDragged(MouseEvent e) {
    if(enableCursor) {
      Graphics g = graph.getGraphics();
      g.setColor(new Color(230, 230, 230));
      g.drawLine(xPos2, 0, xPos2, 12);
      g.drawLine(xPos2, 12, xPos2 - 3, 9);
      g.drawLine(xPos2, 12, xPos2 + 3, 9);
      g.setColor(Color.yellow);
      g.fillRect(25, graph.pixFromY(0) + 28, 155, 15);
      g.setColor(Color.blue);
      xPos2 = e.getX();
      if(xPos2 <= graph.pixFromX(0)) {
        xPos2 = graph.pixFromX(0) + 1;
      }
      if(xPos2 >= graph.pixFromX(1000E-9)) {
        xPos2 = graph.pixFromX(1000E-9);
      }
      g.drawLine(xPos1, 0, xPos1, 12);
      g.drawLine(xPos1, 12, xPos1 - 3, 9);
      g.drawLine(xPos1, 12, xPos1 + 3, 9);
      g.drawLine(xPos2, 0, xPos2, 12);
      g.drawLine(xPos2, 12, xPos2 - 3, 9);
      g.drawLine(xPos2, 12, xPos2 + 3, 9);
      lambda = graph.xFromPix(xPos2);
      g.drawString(label_lambdamax+" " + mouseFormat.form(lambda * 1E9) + " nm", 30, graph.pixFromY(0) + 40);
      slider.setDValue(((2.9E-3 / lambda)) / 10000);
      tempNumber.setValue(((2.9E-3 / lambda)));
      temperature = 2.9E-3 / lambda;
      g.dispose();
      updateDataConnections();
    }
  }

  /**
   * Method graph_mouseReleased
   *
   * @param e
   */
  void graph_mouseReleased(MouseEvent e) {
    if(enableCursor) {
      // Graphics g = graph.getGraphics();
      xPos2 = e.getX();
      if(xPos2 <= graph.pixFromX(0)) {
        xPos2 = graph.pixFromX(0) + 1;
      }
      if(xPos2 >= graph.pixFromX(1000E-9)) {
        xPos2 = graph.pixFromX(1000E-9);
      }
      lambda = graph.xFromPix(xPos2);
      setT(2.9E-3 / lambda);
      // System.out.println(""+2.9E-3/lambda);
      slider.setDValue(((2.9E-3 / lambda)) / 10000);
      tempNumber.setValue(((2.9E-3 / lambda)));
    }
  }

  /**
   * Method slider_mouseReleased
   *
   * @param e
   */
  void slider_mouseReleased(MouseEvent e) {
    setAutoscale(autox.getState(), autoy.getState());
    setT(slider.getDValue() * 10000);
    graph.repaint();
  }

  /**
   * Method graph_mouseEntered
   *
   * @param e
   */
  void graph_mouseEntered(MouseEvent e) {
    if(enableCursor) {
      setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    }
  }

  /**
   * Method graph_mouseExited
   *
   * @param e
   */
  void graph_mouseExited(MouseEvent e) {
    setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
  }

  /**
   * Method autoy_itemStateChanged
   *
   * @param e
   */
  void autoy_itemStateChanged(ItemEvent e) {
    synchronized(lock) {
      newData = true;
      lock.notify();
    }
    setAutoscale(autox.getState(), autoy.getState());
  }

  /**
   * Method autox_itemStateChanged
   *
   * @param e
   */
  void autox_itemStateChanged(ItemEvent e) {
    synchronized(lock) {
      newData = true;
      lock.notify();
    }
    setAutoscale(autox.getState(), autoy.getState());
  }

  /**
   * Method tempNumber_actionPerformed
   *
   * @param e
   */
  void tempNumber_actionPerformed(ActionEvent e) {
    setT(tempNumber.getValue());
    slider.setDValue((tempNumber.getValue()) / 10000);
  }
}

/**
 * Class BlackBody_redNumber_actionAdapter
 */
class BlackBody_redNumber_actionAdapter implements java.awt.event.ActionListener {

  BlackBody adaptee;

  /**
   * Constructor BlackBody_redNumber_actionAdapter
   *
   * @param adaptee
   */
  BlackBody_redNumber_actionAdapter(BlackBody adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method actionPerformed
   *
   * @param e
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.redNumber_actionPerformed(e);
  }
}

/**
 * Class BlackBody_graph_mouseAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class BlackBody_graph_mouseAdapter extends java.awt.event.MouseAdapter {

  BlackBody adaptee;

  /**
   * Constructor BlackBody_graph_mouseAdapter
   *
   * @param adaptee
   */
  BlackBody_graph_mouseAdapter(BlackBody adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method mousePressed
   *
   * @param e
   */
  public void mousePressed(MouseEvent e) {
    adaptee.graph_mousePressed(e);
  }

  /**
   * Method mouseReleased
   *
   * @param e
   */
  public void mouseReleased(MouseEvent e) {
    adaptee.graph_mouseReleased(e);
  }

  /**
   * Method mouseEntered
   *
   * @param e
   */
  public void mouseEntered(MouseEvent e) {
    adaptee.graph_mouseEntered(e);
  }

  /**
   * Method mouseExited
   *
   * @param e
   */
  public void mouseExited(MouseEvent e) {
    adaptee.graph_mouseExited(e);
  }
}

/**
 * Class BlackBody_graph_mouseMotionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class BlackBody_graph_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {

  BlackBody adaptee;

  /**
   * Constructor BlackBody_graph_mouseMotionAdapter
   *
   * @param adaptee
   */
  BlackBody_graph_mouseMotionAdapter(BlackBody adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method mouseDragged
   *
   * @param e
   */
  public void mouseDragged(MouseEvent e) {
    adaptee.graph_mouseDragged(e);
  }
}

/**
 * Class BlackBody_slider_adjustmentAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class BlackBody_slider_adjustmentAdapter implements java.awt.event.AdjustmentListener {

  BlackBody adaptee;

  /**
   * Constructor BlackBody_slider_adjustmentAdapter
   *
   * @param adaptee
   */
  BlackBody_slider_adjustmentAdapter(BlackBody adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method adjustmentValueChanged
   *
   * @param e
   */
  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.slider_adjustmentValueChanged(e);
  }
}

/**
 * Class BlackBody_slider_mouseAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class BlackBody_slider_mouseAdapter extends java.awt.event.MouseAdapter {

  BlackBody adaptee;

  /**
   * Constructor BlackBody_slider_mouseAdapter
   *
   * @param adaptee
   */
  BlackBody_slider_mouseAdapter(BlackBody adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method mouseReleased
   *
   * @param e
   */
  public void mouseReleased(MouseEvent e) {
    adaptee.slider_mouseReleased(e);
  }
}

/**
 * Class BlackBody_autoy_itemAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class BlackBody_autoy_itemAdapter implements java.awt.event.ItemListener {

  BlackBody adaptee;

  /**
   * Constructor BlackBody_autoy_itemAdapter
   *
   * @param adaptee
   */
  BlackBody_autoy_itemAdapter(BlackBody adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method itemStateChanged
   *
   * @param e
   */
  public void itemStateChanged(ItemEvent e) {
    adaptee.autoy_itemStateChanged(e);
  }
}

/**
 * Class BlackBody_autox_itemAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class BlackBody_autox_itemAdapter implements java.awt.event.ItemListener {

  BlackBody adaptee;

  /**
   * Constructor BlackBody_autox_itemAdapter
   *
   * @param adaptee
   */
  BlackBody_autox_itemAdapter(BlackBody adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Method itemStateChanged
   *
   * @param e
   */
  public void itemStateChanged(ItemEvent e) {
    adaptee.autox_itemStateChanged(e);
  }
}

/**
 * Class BlackBody_tempNumber_actionAdapter
 *
 *
 * @author
 * @version %I%, %G%
 */
class BlackBody_tempNumber_actionAdapter implements java.awt.event.ActionListener {

  BlackBody adaptee;

  /**
   * Constructor BlackBody_tempNumber_actionAdapter
   *
   * @param adaptee
   */
  BlackBody_tempNumber_actionAdapter(BlackBody adaptee) {
    this.adaptee = adaptee;
  }

  /**
   * Performs the slider action.  Do not script.
   *
   * @param e
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.tempNumber_actionPerformed(e);
  }
}
