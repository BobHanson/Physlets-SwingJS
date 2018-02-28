////////////////////////////////////////////////////////////////////////////////
//	SpectrumApplet.java
//	Author: Jim Nolen  07/99


package spectrum;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.tools.*;
import edu.davidson.graphics.*;

/**
 * SpectrumApplet.class inherits from SApplet which inherits from Applet.
 * This is the main entry point for the applet SpectrumApplet.  The two main components of the applet are the
 * controlPanel and one instance of SpectrumPanel.class.
 */

//import com.sun.java.swing.UIManager;
public class Spectrum extends SApplet {
  double minLambda=350;
  double maxLambda=700;

  String oneShotMessage = null;
  //XYLayout xYLayout1 = new XYLayout();
  boolean trueColor=true, falseColor = false, firstTime = true;
  boolean isStandalone = false;
  boolean showControls;
  SpectrumPanel specPanel1 = new SpectrumPanel(this);
  EtchedBorder controlPanel = new EtchedBorder();
  Button goBtn = new Button();
  Button button2 = new Button();
  FlowLayout flowLayout2 = new FlowLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
//Get a parameter value

  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet

  public Spectrum() {
  }
//Initialize the applet

  public void init() {
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { minLambda = Double.valueOf(this.getParameter("Min", "400")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { maxLambda = Double.valueOf(this.getParameter("Max", "650")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { trueColor = Boolean.valueOf(this.getParameter("TrueColor", "true")).booleanValue();  } catch (Exception e) { e.printStackTrace(); }
    try { falseColor = Boolean.valueOf(this.getParameter("FalseColor", "false")).booleanValue();  } catch (Exception e) { e.printStackTrace(); }
    try {
    jbInit();
    }
    catch (Exception e) {
    e.printStackTrace();
    }

    setShowControls(showControls);
    setTrueColor(trueColor);
    setFalseColor(falseColor);
  }

  private void jbInit() throws Exception {
    this.setEnabled(true);
    /** j2sNative */{
    	  this.setSize(new Dimension(310, 158));
    }
    controlPanel.setLayout(flowLayout1);
    goBtn.setLabel("Run");
    goBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        goBtn_actionPerformed(e);
      }
    });
    button2.setLabel("Balmer");
    button2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button2_actionPerformed(e);
      }
    });
    specPanel1.setLayout(flowLayout2);
    //xYLayout1.setWidth(400);
    //xYLayout1.setHeight(300);
    this.setLayout(borderLayout2);
    this.add(specPanel1, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(button2, null);
    controlPanel.add(goBtn, null);
  }
//Start the applet

  public void start() {
    super.start();
    specPanel1.setScale(minLambda,maxLambda);

    // test code
    //addPaschenSeries(8,4);
    //specPanel1.autoScale();
    //int id=addEmission(500.0,10.0,1.0);
    //this.setDragable(id,true);
  }

//Destroy the applet

  /**
  *
  * Method controls main action of spectrumPanel.
  * Can be called and should be called from script after adding a
  * set of absorption or emission lines.
  *
  */
  public void runSpectrum(){
    specPanel1.runSpectrum();
    //specPanel1.start();
  }

//Get Applet information

  public String getAppletInfo() {
    return "Applet Information";
  }
//Get parameter info

  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"ShowControls", "boolean", ""},
    };
    return pinfo;
  }

  public void setShowControls(boolean sc){
    this.showControls = sc;
    controlPanel.setVisible(showControls);
    controlPanel.invalidate();
    controlPanel.validate();

  }

 /**
 *    Set the applet to run for a fixed inteval, stop, and display a message.
 *
 *    @param min        The starting time value for the loop
 *    @param max        The ending time for the loop.
 *    @param msg        Message to display after the animation stops.
 *    @see #setTimeContinuous
 *    @see #setTimeInterval
 */
 public void setOneShot(double min,double max, String msg){
      clock.setOneShot(min,max);
      setMessage(null);
      //setAnimationTime(min);
      oneShotMessage=msg;
 }


 /**
 *   Called when the clock stops in one-shot mode.    DO NOT SCRIPT.
 *
 *
 */
 protected void stoppingClock(){setMessage(oneShotMessage);}


  /**
  *
  * Adds a message to small yellow box opposite coordinate display.
  *
  *
  * @param msg String
  */
  public void setMessage(String msg){
      specPanel1.setMessage(msg);
  }

  /**
  *
  * Method adds a caption or title to top center of spectrum panel
  *
  *
  * @param text String
  */
  public int addCaption(String text){
      return specPanel1.addCaption(text);
  }

  /*
  *
  * Method setsRGB of emission line or caption
  *
  * @param id int hashcode id of line or caption
  * @param r int red
  * @param g int green
  * @param b int blue
  */
  public void setRGB(int id, int r, int g, int b){
     specPanel1.setRGB(id,r,g,b);
  }


  /**
  *
  * Method changes offset of title text from default top-center position
  *
  * @param id int id of title
  * @param xoff int pixel offset in horizontal direction
  * @param yoff int pixel offset in vertical direction
  */
  public void setDisplayOffset(int id, int xoff, int yoff){
      specPanel1.setDisplayOffset(id,xoff,yoff);
  }

  /**
  *
  * Method add caption to a plotted spectral line
  *
  * @param id int hashcode id of line
  * @param str String caption text
  *
  */
  public int setCaption( int id, String str){
     return specPanel1.setCaption(id,str);
  }

  /**
  *
  * Method sets a particular line to the ground state level
  *
  * @param id int hashcode id of line
  * @param en double Energy value in 1/(wavelength units)
  *
  */
  public void setGroundState(int id, double en){
        specPanel1.setGroundState(id,en);
  }

  /**
  *
  * Adds Emission slit to SpectrumPanel
  *
  * @param lambda double wavelength of slit in nm
  * @param width double width of slit in nm
  * @param intensity double relative intensity of emission (0 <= i <= 1)
  *
  * @return int   Returns the hashcode id of the emission line.
  */
  public int addEmission(double lambda, double width, double intensity){
    return specPanel1.addEmission(lambda,  width,  intensity);
  }
  /**
  *
  * Adds Gaussian Emission line to SpectrumPanel
  *
  * @param lambda double wavelength of line in nm
  * @param width double width of line in nm
  * @param intensity double relative intensity of emission peak(0 <= i <= 1)
  *
  * @return int   Returns the hashcode id of the emission line.
  */
  public int addEmissionLine(double lambda, double width, double intensity){
    return specPanel1.addEmissionLine(lambda,width,intensity);
  }
  /**
  *
  * Adds Emission band to SpectrumPanel
  *
  * @param lambda1 double wavelength of left bound in nm
  * @param lambda1 double wavelength of right bound in nm
  * @param func String intensity function for band  f(lambda)
  *
  * @return int   Returns the hashcode id of the emission line.
  */
  public int addEmissionBand(double lambda1, double lambda2, String func){
    return specPanel1.addEmissionBand(lambda1,lambda2,func);
  }
  /**
  *
  * Adds Absorption slit to SpectrumPanel
  *
  * @param lambda double wavelength of slit in nm
  * @param width double width of slit in nm
  * @param intensity double relative intensity of absorption (0 <= i <= 1)
  *
  * @return int   Returns the hashcode id of the absorption line.
  */
  public int addAbsorption(double lambda, double width, double intensity){
    return specPanel1.addAbsorption(lambda,width,intensity);
  }
  /**
  *
  * Adds Gaussian Absorption line to SpectrumPanel
  *
  * @param lambda double wavelength in nm of line
  * @param width double width of line in nm
  * @param intensity double relative intensity of absorption peak(0 <= i <= 1)
  *
  * @return int   Returns the hashcode id of the absorption line.
  */
  public int addAbsorptionLine(double lambda, double width, double intensity){
      return specPanel1.addAbsorptionLine(lambda,width,intensity);
  }
  /**
  *
  * Adds Absorption band to SpectrumPanel
  *
  * @param lambda1 double wavelength in nm of left bound
  * @param lambda1 double wavelength in nm of right bound
  * @param func String intensity function for band  f(lambda)
  * @return int   Returns the hashcode id of the absorption line.
  */
  public int addAbsorptionBand(double lambda1, double lambda2, String func){
    return specPanel1.addAbsorptionBand(lambda1,lambda2,func);
  }

  public void addBlackBody(int temp, double saturation){
  }

  /**
  *
  * Method clears all absorption and emission lines.
  *
  *
  */
  public void clearAllLines(){
    specPanel1.clearAllLines();
  }

  /**
  *
  * Method clears line corresponding to given hashcode id
  *
  * @param id int hashcode id
  */
  public void clearLine(int id){
    specPanel1.clearLine(id);
  }

   /**
  *
  * Method clears caption or line corresponding to given hashcode id
  *
  * @param id int hashcode id
  */
  public void clearCaption(int id){
    specPanel1.clearCaption(id);
  }


  /*
  *
  * Method sets upper and lower bounds for displayed spectrum.
  *
  * @param lambda1 double first wavelength bound in nm
  * @param lambda2 double second wavelength bound in nm
  *
  */
  public void setScale(double lambda1, double lambda2){
      specPanel1.setScale(lambda1,lambda2);
  }

  /*
  *
  * Method sets default settings for spectrum
  *
  *
  */
  public void setDefault(){
      clearAllLines();
      setWavelengthUnit("nm");
      specPanel1.setScale(minLambda,maxLambda);
      setDopplerShift(0,false);
      specPanel1.showMessage=false;

      //setTrueColor(true);
      //setFalseColor(false);
  }


  /*
  *
  * Method DopplerShifts displayed spectrum.
  *
  * @param vt double velocity as fraction of c, speed of light -1 <= vt <= +1
  *
  */
  public void setDopplerShift( double v, boolean ds){
      specPanel1.setDopplerShift(v,ds);
  }

  /*
  *
  * Method turns Color in visible spectrum on and off
  *
  * @param tc boolean
  *
  */
  public void setTrueColor(boolean tc){
    specPanel1.setTrueColor(tc);
  }

  /*
  *
  * Method turns false Color in invisible spectrum on and off
  *
  * @param fc boolean
  *
  */
  public void setFalseColor(boolean fc){
    specPanel1.setFalseColor(fc);
  }

  void goBtn_actionPerformed(ActionEvent e) {
     clearAllLines();
     setScale(1,1200);
     //setFalseColor(true);
     //addBalmerSeries(3,6);
     setDragable(addAbsorptionLine(500,20,1),true);
     addEmission(600,1190,1);
     runSpectrum();
  }

  void button2_actionPerformed(ActionEvent e) {
    setDefault();
    setAutoRefresh(false);
    addBalmerSeries(3,8);
    autoScale();
    runSpectrum();
    setAutoRefresh(true);
  }

  /**
  *
  * Method adds line from Balmer series to displayed spectrum.
  *
  * @param bn int upper index of series
  * @param sn int lower indes of series. sn >= 3.
  *
  */
  public void addBalmerSeries(int bn, int sn){
    setWavelengthUnit("nm");
    specPanel1.addBalmerSeries(bn,sn);
  }

  /**
  *
  * Method adds line from Lyman series to displayed spectrum.
  *
  * @param bn int upper index of series
  * @param sn int lower indes of series. sn >= 2.
  *
  */
  public void addLymanSeries(int bn, int sn){
    specPanel1.addLymanSeries(bn,sn);
  }

  /**
  *
  * Method adds line from Paschen series to displayed spectrum.
  *
  * @param bn int upper index of series
  * @param sn int lower indes of series. sn >= 4.
  *
  */
  public void addPaschenSeries(int bn, int sn){
    specPanel1.addPaschenSeries(bn,sn);
  }

  /**
  *
  * Method adds line from Brackett series to displayed spectrum.
  *
  * @param bn int upper index of series
  * @param sn int lower indes of series. sn >= 5.
  *
  */
  public void addBrackettSeries(int bn, int sn){
    specPanel1.addBrackettSeries(bn,sn);
  }

  /**
  *
  * Method should be called from script only. When set "true," the spectrumPanel
  * will refresh itself every time a line or band is added. When set "false," the repainting will
  * be suppressed. If "false," then scripter must call runSpectrum() after series of
  * scripted lines in order to repaint the spectrum. Setting autoRefresh back to "true" will not
  * cause a repaint.
  *
  * @param ar boolean
  *
  */
  public void setAutoRefresh(boolean ar){
    specPanel1.setAutoRefresh(ar);
  }

  /**
  *
  * Method auto-scales the spectrum.
  * Must be called each time spectrum should be auto scaled.
  *
  *
  */
  public void autoScale(){
     specPanel1.autoScale();
  }

  /**
  *    Make the object with the given id dragable.
  *
  *    @param id      The id of the object.
  *    @param drag boolean  Dragable?
  *
  *    @returns boolean  True if successful.
  */
  public boolean setDragable(int id, boolean drag){
      SpectrumThing t=specPanel1.getThing(id);
      if(t==null) return false;
      t.dragable=drag;
      return true;
  }

  /**
  *
  * Changes intensity of a particular line
  *
  * @param id int hashcode id of line to be cahnged
  * @param inNew double new intensity of line  -1<=in<=+1
  *
  */
  public void setIntensity(int id, double inNew){
      specPanel1.setIntensity(id,inNew);
  }

  /**
  *
  * Changes wavelength of a particular line
  *
  * @param id int hashcode id of line to be cahnged
  * @param lamNew double new wavelength of line in nm or cm
  *
  */
  public void setWavelength(int id, double lamNew){
      specPanel1.setWavelength(id,lamNew);
  }

  /**
  *
  * method sets unit of wavelength. default is nm.
  *
  * @param u String  nm or cm
  *
  */
  public void setWavelengthUnit(String u){
    if (u.equals("cm")) specPanel1.setWavelengthUnit(1);
    else specPanel1.setWavelengthUnit(0);
  }


  /**
  *
  *
  *
  */
  public int getSpectrumID(){
    return specPanel1.hashCode();
  }


  /**
  *
  * Method retrieves the wavelength of a line with given hashcode id.
  *
  * @param id int hashcode id# of line
  * @return double wavelength in nm or cm
  */
  public double getWavelength(int id){
    return specPanel1.getWavelength(id);
  }

  /**
  *
  * Method retrieves the energy of a line relative to its ground state
  *
  * @param id int hashcode id# of line
  * @return double energy in 1/nm or 1/cm
  *
  */
  public double getEnergy(int id){
      return specPanel1.getEnergy(id);
  }

  /**
  *
  * Method retrieves the width of a line with given hashcode id.
  *
  * @param id int hashcode id# of line
  * @return double width in nm or cm
  */
  public double getWidth(int id){
    return specPanel1.getWidth(id);
  }

  /**
  *
  * Method retrieves the intensity of a line with given hashcode id.
  *
  * @param id int hashcode id# of line
  * @return double intensity
  */
  public double getIntensity(int id){
    SpectrumThing t = specPanel1.getThing(id);
    return t.in;
  }

  /**
  *
  * Method retrieves the intensity function of a band with given hashcode id.
  *
  * @param id int hashcode id# of band
  * @return String intensity function
  */
  /*public String getIntensityFunction(int id){
     Thing t = specPanel1.getThing(id);

     if ((t instanceof ABand)||(t instanceof EBand))
        return t.inString;

     else return "No Function";
  }*/

  void specPanel1_mousePressed(MouseEvent e) {
    clearAllLines();
    runSpectrum();
  }

    /**
   * Entry point for standalone application.
   */
  public static void main(String[] args) {
    Spectrum applet = new Spectrum();
    applet.isStandalone = true;
    Frame frame;
    frame = new Frame() {

      protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
          System.exit(0);
        }
      }

      public synchronized void setTitle(String title) {
        super.setTitle(title);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      }
    };
    frame.setTitle("Spectrum Frame");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }


}

