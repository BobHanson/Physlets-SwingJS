/*
 * Class  doppler.Doppler
 * class Dopplet extends Physlet
 *
 * The main entry point and Doppler applet.
 */
package doppler;

import java.awt.BorderLayout;
import a2s.Canvas;
import a2s.Checkbox;
import a2s.CheckboxGroup;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a2s.Panel;
import java.net.URL;
import java.util.Vector;

import javax.swing.Timer;

import edu.davidson.display.Format;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.tools.SApplet;

/**
 * Doppler demonstrates the classical and relativistic Doppler effect.
 *
 * @version  Revision: 4.0, Date: 2000/06/20
 * @author   Wolfgang Christian
 */
public class Doppler extends SApplet implements Runnable, ActionListener {
  private String label_speed="speed = ";
  private String label_classical="classical";
  private String label_relativistic="relativistic";
  String label_time="time: ";
  String label_position="source x: ";
  String label_caption="Doppler";
  private String button_start="Run ";
  private String button_stop="Stop";


  private Timer       m_Doppler = null; // BH
  private VarScrollBar  speedBar;
  private DopplerCanvas dc;
  private int           sleepTime = 50;
  private Checkbox      cCheck;
  private Checkbox      rCheck;
  private SizedButton   runBtn;
  //       boolean running=false;
  private boolean       runOnStart         = false;
  // STANDALONE APPLICATION SUPPORT:
  //              m_fStandAlone will be set to true if applet is run standalone
  //--------------------------------------------------------------------------
  private boolean       m_fStandAlone      = false;
  private double        m_speed            = 0.5;
  private int           m_fps              = 20;
  private boolean       m_relativistic     = false;
  private String        m_helpFile         = null;
  private boolean       m_showControls     = true;
  private boolean       m_showButtons      = true;
  // Parameter names.  To change a name of a parameter, you need only make
  // a single change.  Simply modify the value of the parameter string below.
  //--------------------------------------------------------------------------
  private final String  PARAM_speed        = "Speed";
  private final String  PARAM_fps          = "FPS";
  private final String  PARAM_relativistic = "Relativistic";
  private final String  PARAM_helpFile     = "helpFile";
  private final String  PARAM_showControls = "ShowControls";
  private final String  PARAM_showButtons  = "ShowButtons";

  /**
   * Method GetParameter
   *
   * @param strName
   * @param args
   *
   * @return the paramter string
   * @y.exclude
   */
  String GetParameter(String strName, String args[]) {
    if(args == null) {
      return getParameter(strName);
    }
    int    i;
    String strArg   = strName + "=";
    String strValue = null;
    int    nLength  = strArg.length();
    try {
      for(i = 0; i < args.length; i++) {
        String strParam = args[i].substring(0, nLength);
        if(strArg.equalsIgnoreCase(strParam)) {
          // Found matching parameter on command line, so extract its value.
          // If in double quotes, remove the quotes.
          //---------------------------------------------------------------
          strValue = args[i].substring(nLength);
          if(strValue.startsWith("\"")) {
            strValue = strValue.substring(1);
            if(strValue.endsWith("\"")) {
              strValue = strValue.substring(0, strValue.length() - 1);
            }
          }
          break;
        }
      }
    } catch(Exception e) {}
    return strValue;
  }

  /**
   * Method GetParameters
   *
   * @param args
   */
  void GetParameters(String args[]) {
    String param;
    param = GetParameter(PARAM_speed, args);
    if(param != null) {
      m_speed = Double.valueOf(param).doubleValue();
    }
    param = GetParameter(PARAM_fps, args);
    if(param != null) {
      m_fps = Integer.parseInt(param);
    }
    param = GetParameter(PARAM_relativistic, args);
    if(param != null) {
      m_relativistic = Boolean.valueOf(param).booleanValue();
    }
    param = GetParameter(PARAM_showControls, args);
    if(param != null) {
      m_showControls = Boolean.valueOf(param).booleanValue();
    }
    param = GetParameter(PARAM_showButtons, args);
    if(param != null) {
      m_showButtons = Boolean.valueOf(param).booleanValue();
    }
    param = GetParameter(PARAM_helpFile, args);
    if(param != null) {
      m_helpFile = param;
    }
  }

  /**
   * Method main
   *
   * @param args
   * @y.exclude
   */
  public static void main(String args[]) {
    DopplerFrame frame = new DopplerFrame("Doppler");
    // Must show Frame before we size it so insets() will return valid values
    //----------------------------------------------------------------------
    frame.show();
    frame.hide();
    frame.resize(frame.insets().left + frame.insets().right + 320, frame.insets().top + frame.insets().bottom + 340);
    Doppler applet_Doppler = new Doppler();
    frame.add("Center", applet_Doppler);
    applet_Doppler.m_fStandAlone = true;
    applet_Doppler.GetParameters(args);
    applet_Doppler.init();
    applet_Doppler.start();
    frame.show();
  }

  /**
   * Constructor Doppler
   * @y.exclude
   */
  public Doppler() {}

  /**
   * Method getAppletInfo
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Name: Doppler ver 1.2\r\n" + "Author: Wolfgang Christian\r\n" + "email: wochristian@davidson.edu\r\n" + "";
  }

  /**
   * Method getParameterInfo
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    String[][] info = {
      {PARAM_speed, "double", "Source speed"}, {PARAM_fps, "int", "Frames per second"},
      {PARAM_relativistic, "boolean", "Relativistic effects."},
      {PARAM_showControls, "boolean", "Show controls and buttons."},
      {PARAM_showButtons, "boolean", "Show classical/relativistic options."},
      {PARAM_helpFile, "String", "Applet help file URL."},
    };
    return info;
  }

  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    runOnStart = false;
    if(!m_fStandAlone) {
      GetParameters(null);
    }
    resize(320, 340);
    int f = 1;
    /**
     * @j2sNative f = 5;
     * 
     */
    sleepTime = 1000 / f / m_fps;  // BH
    setLayout(new BorderLayout());
    Panel p1 = new Panel();  //panel for the scroll and checkboxes
    p1.setBackground(Color.lightGray);
    p1.setLayout(new BorderLayout());
    CheckboxGroup g  = new CheckboxGroup();
    Panel         p3 = new Panel();
    p3.setBackground(Color.lightGray);
    p3.setLayout(new GridLayout(1, 2, 4, 4));
    p3.add(cCheck = new Checkbox(label_classical, g, true));
    p3.add(rCheck = new Checkbox(label_relativistic, g, false));
    if(m_showButtons) {
      p1.add("North", p3);
    }
    if(m_relativistic) {
      if(m_speed > 0.99) {
        m_speed = 0.99;
      }
      speedBar = new VarScrollBar(label_speed, m_speed, 0, 0.99);
    } else {
      if(m_speed > 2.0) {
        m_speed = 2.0;
      }
      speedBar = new VarScrollBar(label_speed, m_speed, 0, 2.0);
    }
    p1.add("Center", new EtchedBorder(speedBar));
    //p1.add("South", new Label("by W. Christian Ver 1.2", Label.RIGHT));
    p1.add("West", runBtn = new SizedButton(button_start));
    if(m_showControls) {
      add("South", p1);
    }
    // end of control panel p1 init
    dc = new DopplerCanvas(this);
    dc.setBackground(Color.white);
    add("Center", dc);
    dc.setSpeed(m_speed, 0);
    dc.setRelativistic(m_relativistic);
    invalidate();
 }

  protected void setResources(){
     label_caption=localProperties.getProperty("label.caption",label_caption);
     label_speed=localProperties.getProperty("label.speed",label_speed);
     label_classical=localProperties.getProperty("label.classical",label_classical);
     label_relativistic=localProperties.getProperty("label.relativistic",label_relativistic);
     label_time=localProperties.getProperty("label.time",label_time);
     label_position=localProperties.getProperty("label.position",label_position);
     button_start=localProperties.getProperty("button.start",button_start);
     button_stop=localProperties.getProperty("button.stop",button_stop);
  }

  /**
   * Method destroy
   * @y.exclude
   */
  public void destroy() {
    if(m_Doppler != null) {
      m_Doppler.stop();
      m_Doppler = null;
      //running=false;
    }
    super.destroy();
  }

  /**
   *    Resets the applet to time=0.
   *
   */
  public void reset() {
    dc.reset();
    dc.repaint();
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
   *    Start the applet thread.  Should not be called from JavaScript.
   *    @see #forward
   *    @y.exclude
   */
  public void start() {
    super.start();
    if(firstTime) {
      firstTime = false;
    }
    this.setRunningID(this);
  	keepRunning = true;
    if(m_Doppler == null) {
      m_Doppler = new Timer(sleepTime, this); // BH
      m_Doppler.setRepeats(true); // BH
      m_Doppler.start();
      //       running=true;
    }
  }

	boolean keepRunning = true;
	@Override
	public void actionPerformed(ActionEvent e) {
	    if(runOnStart && keepRunning && (this.getRunningID() == this)) {
	    	m_Doppler.setDelay(sleepTime);
	        dc.incTime(m_speed);
	        return;
	    }
        stop();
	    m_Doppler = null;

		// TODO Auto-generated method stub

	}

  /**
   *    Stop the applet thread. Should not be called from JavaScript.
   *
   *    @see #pause
   *    @y.exclude
   */
  public void stop() {
    if(m_Doppler != null) {
      m_Doppler.stop();
      m_Doppler = null;
      //running=false;
    }
    super.stop();
  }

  /**
   *    Starts the animation.
   *
   */
  public void forward() {
    runOnStart = true;
    runBtn.setLabel(button_stop);
    start();
  }

  protected void pausingClock() {
    pause();
  }

  /**
   *    Pauses the animation.
   *
   */
  public void pause() {
    runOnStart = false;
    runBtn.setLabel(button_start);
    stop();
  }

  /**
   *    Steps the time.
   *
   */
  public void step() {
    if(m_Doppler == null) {
      dc.incTime(m_speed);
    }
  }

  /**
   *    Starts the applet thread.  Should not be called from JavaScript.
   *
   *    @see #forward
   *    @see #step
   *    @y.exclude
   *    @deprecated // BH
   */
  public void run() {
    boolean keepRunning = true;
    while(runOnStart && keepRunning && (this.getRunningID() == this)) {
      try {
        dc.incTime(m_speed);
        //dc.repaint();
        Thread.sleep(sleepTime);
      } catch(InterruptedException e) {
        stop();
      }
    }
    m_Doppler = null;
  }

  /**
   * Method handleEvent
   *
   * @param evt
   *
   * @return true if successful; false otherwise
   * @y.exclude
   */
  public boolean handleEvent(Event evt) {
    if(evt.target.equals(speedBar)) {
      //System.out.println("Bar Moved!");
      m_speed = speedBar.getValue();
      dc.setSpeed(m_speed, 0);
      return true;
    } else {
      return super.handleEvent(evt);
    }
  }

  /**
   * Method mouseDown
   *
   * @param evt
   * @param x
   * @param y
   *
   * @return true if successful; false otherwise
   * @y.exclude
   */
  public boolean mouseDown(Event evt, int x, int y) {
    if(((evt.modifiers & Event.META_MASK) != 0) || ((evt.modifiers & Event.ALT_MASK) != 0)) {
      String mfile = m_helpFile;
      if(mfile != null) {
        try {
          URL textURL = new URL(getDocumentBase(), mfile);
          getAppletContext().showDocument(textURL, "_blank");
        } catch(Exception e) {
          System.out.println("Failed to load help file!");
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Method action
   *
   * @param ev
   * @param arg
   *
   * @return true if successful; false otherwise
   * @y.exclude
   */
  public boolean action(Event ev, Object arg) {
    if(ev.target.equals(runBtn)) {
      if(runOnStart) {
        runOnStart = false;
        runBtn.setLabel(button_stop);
        pause();
      } else {
        runOnStart = false;
        runBtn.setLabel(button_start);
        forward();
      }
      return true;
    } else if(ev.target.equals(rCheck)) {
      m_relativistic = true;
      if(m_speed > 1.00) {
        m_speed = 0.99;
      }
      speedBar.setMinMax(0, 0.99);
      speedBar.setValue(m_speed);
      dc.setSpeed(m_speed, 0);
      dc.setRelativistic(m_relativistic);
      return true;
    } else if(ev.target.equals(cCheck)) {
      m_relativistic = false;
      speedBar.setMinMax(0, 2.0);
      speedBar.setValue(m_speed);
      //dc.setSpeed(m_speed,0);
      dc.setRelativistic(m_relativistic);
      return true;
    }
    return false;
  }

  /**
   *    Sets the speed of the source.
   *
   *    @param v        The speed of the source.
   */
  public void setSpeed(double v) {
    if(v < 0) {
      v = 0;
    }
    if(m_relativistic) {
      if(v > 0.99) {
        m_speed = 0.99;
      } else {
        m_speed = v;
      }
    } else {
      if(v > 2.00) {
        m_speed = 2.00;
      } else {
        m_speed = v;
      }
    }
    speedBar.setValue(m_speed);
    dc.setSpeed(m_speed, 0);
  }

  /**
   *    Sets the position of the source.
   *
   *    The source position is given in pixels.
   *
   *    @param x       The position of the source in pixels.
   */
  public void setSourceX(double x) {
    dc.setX((int) x);
  }

  /**
   *    Displays the clasical Doppler effect.
   *
   */
  public void setClassical() {
    if(m_relativistic) {
      m_relativistic = false;
      speedBar.setMinMax(0, 2.0);
      speedBar.setValue(m_speed);
      dc.setRelativistic(m_relativistic);
      cCheck.setState(true);
    } else {
      return;
    }
  }

  /**
   *    Displays the relativisitc Doppler effect.
   *
   */
  public void setRelativistic() {
    if(m_relativistic) {
      return;
    } else {
      m_relativistic = true;
      if(m_speed > 1.00) {
        m_speed = 0.99;
      }
      speedBar.setMinMax(0, 0.99);
      speedBar.setValue(m_speed);
      dc.setSpeed(m_speed, 0);
      dc.setRelativistic(m_relativistic);
      rCheck.setState(true);
    }
  }

  /**
   *    Sets the applet caption.
   *
   *    @param s        The caption string.
   */
  public void setCaption(String s) {
    label_caption=s;
    dc.repaint();
  }

}

//Drawing methods

/**
 * Class DopplerCanvas
 *
 *
 * @author
 * @version %I%, %G%
 */
final class DopplerCanvas extends Canvas {

  Doppler applet = null;
private long lastTime;

  /**
   * Constructor DopplerCanvas
   *
   * @param a
   */
  public DopplerCanvas(Doppler a) {
    applet = a;
  }  //constructor

  /**
   * Method reset
   *
   */
  public void reset() {
    xs         = 0;
    ys         = size().height / 2;
    labTime    = 0;
    sourceTime = 10;
    genVec.removeAllElements();
  }

  /**
   * Method resetSource
   *
   */
  public synchronized void resetSource() {
    xs         = 0;
    ys         = size().height / 2;
    labTime    = 0;
    sourceTime = 0;
    genVec.removeAllElements();
  }

  /**
   * Method setX
   *
   * @param newx
   */
  public synchronized void setX(int newx) {
    xs         = newx;
    ys         = size().height / 2;
    labTime    = 0;
    sourceTime = 0;
    genVec.removeAllElements();
  }

  /**
   * Method translateSource
   *
   * @param x
   */
  public synchronized void translateSource(int x) {
    int              len = genVec.size();  //length of vector
    int              i;
    DopplerWaveCrest gen;
    xs = xs + x;
    ys = size().height / 2;
    if(len > 0) {
      for(i = 0; i < len; i++) {
        gen = (DopplerWaveCrest) (genVec.elementAt(i));
        gen.translate(x);
      }
    }
  }

  /**
   * Method setSpeed
   *
   * @param vx_
   * @param vy_
   */
  public void setSpeed(double vx_, double vy_) {
    vxs = vx_;
   // vys = vy_;
  }

  /**
   * Method setRelativistic
   *
   * @param r
   */
  public void setRelativistic(boolean r) {
    if(relativistic != r) {
      reset();
    }
    relativistic = r;
  }

  /**
   * Method incTime
   *
   * @param speed
   */
  public synchronized void incTime(double speed) {
    labTime = labTime + 1.0;
    if(relativistic) {
      sourceTime = sourceTime + 1.0 * Math.sqrt(1 - speed * speed);
    } else {
      sourceTime = sourceTime + 1.0;
    }
    xs = (xs + vxs);
    if(xs > size().width) {
      translateSource(-size().width);
    }
    if(sourceTime > pixPerUnit) {
      sourceTime = sourceTime - pixPerUnit;
      if(genVec.size() > 10) {
        genVec.removeElementAt(0);
      }
      genVec.addElement(new DopplerWaveCrest(labTime, (int) xs, (int) ys, vxs, vxs));
    }
    long thisTime = System.currentTimeMillis();
    //System.out.println("Doppler time = " + (thisTime - lastTime));
    lastTime = thisTime;
    repaint(); 
    
// BH paint(g) should not be called directly
//    Graphics g = getGraphics();
//    paint(g);
//    g.dispose();
  }

  private void calcBuffImage() {
    int              i;
    int              len = genVec.size();  //length of vector
    Graphics         g   = buff_image.getGraphics();
    DopplerWaveCrest gen;
    //g.clearRect(0,0,buff_width,buff_height);
    g.setColor(Color.white);
    g.fillRect(0, 0, buff_width, buff_height);
    //----------------------------------------------------------------------
    if(len > 0) {
      for(i = 0; i < len; i++) {
        g.setColor(new Color(255 * (len - i) / len, 255 * (len - i) / len, 255 * (len - i) / len));
        gen = (DopplerWaveCrest) (genVec.elementAt(i));
        gen.draw(labTime, g);
      }
    }
    g.setColor(Color.red);
    g.fillOval((int) (xs - 2), (int) (ys - 2), 4, 4);
    g.setColor(Color.black);
    g.setFont(f);
    FontMetrics fm = g.getFontMetrics(f);
    g.drawString(applet.label_caption, (buff_width - fm.stringWidth(applet.label_caption)) / 2, 20);
    g.dispose();
  }

  /**
   * Method update
   *
   * @param g
   */
  public void update(Graphics g) {
    paint(g);  //update usually does a rect fill with a background color.  We don't need this.
  }

  // Doppler Paint Handler
  //--------------------------------------------------------------------------

  /**
   * Method paint
   *
   * @param g
   */
  public void paint(Graphics g) {
    try {
      if((buff_image == null) || (size().width != buff_width) || (size().height != buff_height)) {
        ys          = size().height / 2;
        buff_width  = size().width;
        buff_height = size().height;
        buff_image  = createImage(buff_width, buff_height);
        reset();
        //resetSource();
      }
      calcBuffImage();
      g.drawImage(buff_image, 0, 0, this);
      String tStr = new Format("%7.4g").form(labTime / pixPerUnit);
      String pStr = new Format("%7.4g").form(pixToX((int) (xs)));
      if(timeDisplay) {
        g.drawString(applet.label_time + tStr, 10, 15);
        if(!showCoord) {
          g.drawString(applet.label_position + pStr, 10, buff_height - 15);
        }
      }
      if (showCoord)
    	  paintCoords(g);
    } catch(Exception ex) {
      buff_image = null;
    }
  }

  private double pixToX(int x) {
    int xo = buff_width / 2;
    return (x - xo) / (1.0 * pixPerUnit);
  }

  private double pixToY(int y) {
    int yo = buff_height / 2;
    return -(y - yo) / (1.0 * pixPerUnit);
  }

  /**
   * Method startDrawCoord
   *
   * @param x
   * @param y
   */
  public void startDrawCoord(int x, int y) {
    applet.stop();
    xCoord = x;
    yCoord = y;
    showCoord = true;
    repaint();
  }

  private void paintCoords(Graphics g) {
  g.clearRect(8, buff_height - 30, 150, 20);
  g.drawString("X: " + pixToX(xCoord) + "  Y: " + pixToY(yCoord), 10, buff_height - 15);
  g.setColor(Color.red);
  g.drawLine(xCoord - 10, yCoord, xCoord + 10, yCoord);
  g.drawLine(xCoord, yCoord - 10, xCoord, yCoord + 10);
  }
  /**
   * Method endDrawCoord
   *
   * @param x
   * @param y
   */
  public void endDrawCoord(int x, int y) {
    applet.start();
    showCoord = false;
    repaint();
    //Graphics g=getGraphics();
    //paint(g);
    //g.dispose();
  }

  /**
   * Method drawCoord
   *
   * @param x
   * @param y
   */
  public void drawCoord(int x, int y) {
    xCoord = x;
    yCoord = y;
    if(showCoord) {
      Graphics g = getGraphics();
      paint(g);  // draw the image onto the visible graph
      g.clearRect(8, buff_height - 30, 150, 20);
      g.drawString("X: " + pixToX(xCoord) + "  Y: " + pixToY(yCoord), 10, buff_height - 15);
      g.setColor(Color.red);
      g.drawLine(xCoord - 10, yCoord, xCoord + 10, yCoord);
      g.drawLine(xCoord, yCoord - 10, xCoord, yCoord + 10);
      g.dispose();
    }
  }

  /**
   * Method mouseDown
   *
   * @param evt
   * @param x
   * @param y
   *
   * @return
   */
  public boolean mouseDown(Event evt, int x, int y) {
    if(((evt.modifiers & Event.META_MASK) != 0) || ((evt.modifiers & Event.ALT_MASK) != 0)) {
      return false;
    }
    startDrawCoord(x, y);
    return true;
  }

  /**
   * Method mouseUp
   *
   * @param evt
   * @param x
   * @param y
   *
   * @return
   */
  public boolean mouseUp(Event evt, int x, int y) {
    if(((evt.modifiers & Event.META_MASK) != 0) || ((evt.modifiers & Event.ALT_MASK) != 0)) {
      return false;
    }
    endDrawCoord(x, y);
    return true;
  }

  /**
   * Method mouseDrag
   *
   * @param evt
   * @param x
   * @param y
   *
   * @return
   */
  public boolean mouseDrag(Event evt, int x, int y) {
    if(((evt.modifiers & Event.META_MASK) != 0) || ((evt.modifiers & Event.ALT_MASK) != 0)) {
      return false;
    }
    drawCoord(x, y);
    return true;
  }

  private double  xs = 0, ys;            // source position;
  private double  vxs; //vys;              // source velocity;
  private Vector  genVec       = new Vector();
  private double  sourceTime   = 0;
  private double  labTime      = 0;      // running time
  private Image   buff_image   = null;
  private int     buff_width   = 0;
  private int     buff_height  = 0;
  private boolean relativistic = false;
  private boolean showCoord    = false;  //display mouse coordinates
  private int     xCoord, yCoord;        // coordinates for mouse down
  int             pixPerUnit  = 10;
  Font            f           = new Font("Helvetica", Font.BOLD, 14);
  boolean         timeDisplay = true;
}
