package reflection;

import a2s.*;
import edu.davidson.display.Format;
import edu.davidson.display.SScalable;
import edu.davidson.display.Thing;
import edu.davidson.graph.TextLine;
import edu.davidson.tools.SApplet;
//import edu.davidson.tools.SClock;
import edu.davidson.tools.SDataSource;
import edu.davidson.tools.SStepable;
import edu.davidson.tools.SUtil;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
//import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
//import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Vector;

public class WavePanel extends Panel
  implements SStepable, SScalable
{
  static int EM_MODE = 0;
  static int QM_MODE = 1;
  boolean ampDisplay = false;
  double ampScale = 1.0D;
  boolean autoRefresh = true;
  Font boldFont = new Font("Helvetica", 1, 12);
  private int boxWidth = 0;
  private Color[] colors = new Color[101];
  boolean coordDisplay = true;
  int currenth = 1;
  int currentw = 1;
  private boolean dragEnergy = false;
  ReflectionThing dragThing = null;
  Vector drawingThings = new Vector();
  double enMax = 1000000.0D;
  double enMin = -1000000.0D;
  private boolean fixedPts = false;
  Font font = this.boldFont;
  private Format format = new Format("%6.3g");
  private double highestN = 0.0D;
  Color imWaveColor = new Color(0, 64, 64);
  ReflectionThing insideThing = null;
  double lambda = 2.0D;
  int leftPixOffset = 0;
  double[] leftWave = null;
  private double lowestN = 0.0D;
  double magMax = 1.0D;
  String message = null;
  private int mode = EM_MODE;
  boolean mouseDown = false;
  private Format mouseFormat = new Format("%-+6.3g");
  int mousex = 0;
  int mousey = 0;
  Image osi = null;
  ReflectionApplet owner = null;
  boolean phaseDisplay = false;
  double ppu = 10.0D;
  double qmEnergy = 0.0D;
  Color reWaveColor = new Color(128, 0, 0);
  double refC = 0.0D;
  int rightPixOffset = 0;
  double[] rightWave = null;
  private boolean sendData = true;
  boolean showEnergy = true;
  boolean showLWave = false;
  boolean showMessage = false;
  boolean showPhaseColor = true;
  boolean showRWave = false;
  boolean showTime = false;
  boolean showWave = true;
  Vector thingVector = new Vector(4, 1);
  double time = 0.0D;
  double timePhase = 0.0D;
  TextLine title = new TextLine();
  String titleStr = "";
  double tranC = 1.0D;
  private boolean wasRunning = false;
  Color waveColor = Color.black;
  WaveDataSource waveDataSource = null;
  double waveVel = 0.0D;
  double xCoordinateOffset = 0.0D;
  int[] xpoints = null;
  int[] ypoints = null;

  public WavePanel(ReflectionApplet paramReflectionApplet)
  {
	createWavePanel();
    this.owner = paramReflectionApplet;
  }

  public void createWavePanel()
  {
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    initColors();
  }

  public ReflectionThing addMedium(double paramDouble1, double paramDouble2)
  {
	  ReflectionThing localObject;
    if (this.mode == EM_MODE)
      localObject = new EMThing(paramDouble1, paramDouble2, this);
    else
      localObject = new QMThing(paramDouble1, paramDouble2, this);
    this.thingVector.addElement(localObject);
    arrangeMedia();
    if (this.autoRefresh)
      recalc();
    try
    {
      SApplet.addDataSource(localObject);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      System.out.println("error in addMediaDataSource()");
    }
    return localObject;
  }

  void arrangeMedia()
  {
    double d = 0.0D;
    int i = 0;
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      localReflectionThing.setPos(d);
      i = localReflectionThing.rescale(i);
      d += localReflectionThing.width;
    }
  }

  public void calcAmps()
  {
    double d1 = 0.0D;
    double d2 = 1.0D;
    ReflectionThing localReflectionThing1 = getRightMost();
    if (localReflectionThing1 == null)
      return;
    ReflectionThing localReflectionThing2 = null;
    while (localReflectionThing1 != null)
    {
      localReflectionThing1.calcBoundaryMatrix(localReflectionThing2);
      localReflectionThing2 = localReflectionThing1;
      localReflectionThing1 = getLeftNeighbor(localReflectionThing1);
    }
    localReflectionThing1 = getLeftMost();
    d2 = Math.sqrt(localReflectionThing1.rightE[0] * localReflectionThing1.rightE[0] + localReflectionThing1.rightE[1] * localReflectionThing1.rightE[1]);
    d1 = Math.sqrt(localReflectionThing1.leftE[0] * localReflectionThing1.leftE[0] + localReflectionThing1.leftE[1] * localReflectionThing1.leftE[1]);
    localReflectionThing1 = getRightMost();
    if (d2 > 0.0D)
      this.ampScale = (this.magMax * 1.0D / d2);
    else
      System.out.println("Error: The output is nonzero but the input is zero!");
    this.refC = (d1 / d2);
    this.tranC = (1.0D - this.refC);
    makeWaves(this.time);
  }

  private void calcMinMaxN()
  {
    this.lowestN = 0.0D;
    this.highestN = 0.0D;
    ReflectionThing localReflectionThing = getRightMost();
    if (localReflectionThing == null)
      return;
    this.lowestN = localReflectionThing.indexN;
    this.highestN = localReflectionThing.indexN;
    for (localReflectionThing = getLeftNeighbor(localReflectionThing); localReflectionThing != null; localReflectionThing = getLeftNeighbor(localReflectionThing))
    {
      if (this.lowestN > localReflectionThing.indexN)
        this.lowestN = localReflectionThing.indexN;
      if (this.highestN < localReflectionThing.indexN)
        this.highestN = localReflectionThing.indexN;
    }
  }

  public static double calcRefCoeff(double[][] paramArrayOfDouble, double paramDouble1, double paramDouble2)
  {
    double d1 = paramArrayOfDouble[0][0];
    double d2 = paramArrayOfDouble[0][1];
    double d3 = paramArrayOfDouble[1][0];
    double d4 = paramArrayOfDouble[1][1];
    double d5 = d1 * paramDouble1 - d4 * paramDouble2;
    double d6 = d2 * paramDouble1 * paramDouble2 - d3;
    double d7 = d1 * paramDouble1 + d4 * paramDouble2;
    double d8 = d2 * paramDouble2 * paramDouble1 + d3;
    double d9 = (d5 * d5 + d6 * d6) / (d7 * d7 + d8 * d8);
    System.out.println("r =" + d9);
    return d9;
  }

  public static double calcTransCoeff(double[][] paramArrayOfDouble, double paramDouble1, double paramDouble2)
  {
    double d1 = paramArrayOfDouble[0][0];
    double d2 = paramArrayOfDouble[0][1];
    double d3 = paramArrayOfDouble[1][0];
    double d4 = paramArrayOfDouble[1][1];
    double d5 = 2.0D * paramDouble1;
    double d6 = 0.0D;
    double d7 = d1 * paramDouble1 + d4 * paramDouble2;
    double d8 = d2 * paramDouble2 * paramDouble1 + d3;
    double d9 = (d5 * d5 + d6 * d6) / (d7 * d7 + d8 * d8);
    System.out.println("t =" + d9);
    return d9;
  }

  public void clearAllCaptions()
  {
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      localReflectionThing.showCaption = false;
    }
    if (this.autoRefresh)
      paintOffScreen();
  }

  public void clearCaption(int paramInt)
  {
    ReflectionThing localReflectionThing = getReflectionThing(paramInt);
    if (localReflectionThing == null)
      return;
    localReflectionThing.showCaption = false;
    if (this.autoRefresh)
      paintOffScreen();
  }

  private Color colorFromPhase(double paramDouble)
  {
    int i = (int)(50.0D + 50.0D * paramDouble / 3.141592653589793D);
    return this.colors[i];
  }

  public void forward()
  {
    this.owner.forward();
  }

  Thing getDrawingThing(int paramInt)
  {
    Thing localThing = null;
    Enumeration localEnumeration = this.drawingThings.elements();
    while (localEnumeration.hasMoreElements())
    {
      localThing = (Thing)localEnumeration.nextElement();
      if (localThing.hashCode() == paramInt)
        return localThing;
    }
    return null;
  }

  public ReflectionThing getLeftMost()
  {
    double d = 100000.0D;
    ReflectionThing localObject = null;
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      if (localReflectionThing.pos < d)
      {
        d = localReflectionThing.pos;
        localObject = localReflectionThing;
      }
    }
    return localObject;
  }

  public ReflectionThing getLeftNeighbor(ReflectionThing paramReflectionThing)
  {
    double d1 = paramReflectionThing.pos;
    double d2 = -100000.0D;
    ReflectionThing localObject = null;
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      if ((localReflectionThing.pos < d1) && (localReflectionThing.pos > d2))
      {
        d2 = localReflectionThing.pos;
        localObject = localReflectionThing;
      }
    }
    return localObject;
  }

  public int getPixHeight()
  {
    return this.currenth;
  }

  public int getPixWidth()
  {
    return this.currentw;
  }

  public double getQMEnergy()
  {
    return this.qmEnergy;
  }

  ReflectionThing getReflectionThing(int paramInt)
  {
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      if (localReflectionThing.hashCode() == paramInt)
        return localReflectionThing;
    }
    return null;
  }

  ReflectionThing getReflectionThingFromIndex(int paramInt)
  {
    int i = this.thingVector.size();
    if ((paramInt < 0) || (paramInt >= i))
      return null;
    return (ReflectionThing)this.thingVector.elementAt(paramInt);
  }

  public ReflectionThing getRightMost()
  {
    double d = -1000.0D;
    ReflectionThing localObject = null;
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      if (localReflectionThing.pos > d)
      {
        d = localReflectionThing.pos;
        localObject = localReflectionThing;
      }
    }
    return localObject;
  }

  public ReflectionThing getRightNeighbor(ReflectionThing paramReflectionThing)
  {
    double d1 = paramReflectionThing.pos;
    double d2 = 10000000.0D;
    ReflectionThing localObject = null;
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      if ((localReflectionThing.pos > d1) && (localReflectionThing.pos < d2))
      {
        d2 = localReflectionThing.pos;
        localObject = localReflectionThing;
      }
    }
    return localObject;
  }

  public int getWaveID()
  {
    if (this.waveDataSource == null)
      this.waveDataSource = new WaveDataSource();
    return this.waveDataSource.getID();
  }

  private void initColors()
  {
    double d = 3.141592653589793D;
    for (int m = 0; m < 101; m++)
    {
      int k = (int)(255.0D * Math.sin(d * m / 100.0D) * Math.sin(d * m / 100.0D));
      int j = (int)(255.0D * Math.sin(d * m / 100.0D + d / 3.0D) * Math.sin(d * m / 100.0D + d / 3.0D));
      int i = (int)(255.0D * Math.sin(d * m / 100.0D + 2.0D * d / 3.0D) * Math.sin(d * m / 100.0D + 2.0D * d / 3.0D));
      this.colors[m] = new Color(i, j, k);
    }
  }

  private ReflectionThing isInsideThing(int paramInt1, int paramInt2)
  {
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      if (localReflectionThing.isInside(paramInt1, paramInt2))
        return localReflectionThing;
    }
    return null;
  }

  private void jbInit()
    throws Exception
  {
    addMouseMotionListener(new MouseMotionAdapter()
    {
      public void mouseDragged(MouseEvent paramAnonymousMouseEvent)
      {
        WavePanel.this.this_mouseDragged(paramAnonymousMouseEvent);
      }

      public void mouseMoved(MouseEvent paramAnonymousMouseEvent)
      {
        WavePanel.this.this_mouseMoved(paramAnonymousMouseEvent);
      }
    });
    addComponentListener(new ComponentAdapter()
    {
      public void componentResized(ComponentEvent paramAnonymousComponentEvent)
      {
        WavePanel.this.this_componentResized(paramAnonymousComponentEvent);
      }
    });
    addMouseListener(new MouseAdapter()
    {
      public void mouseEntered(MouseEvent paramAnonymousMouseEvent)
      {
        WavePanel.this.this_mouseEntered(paramAnonymousMouseEvent);
      }

      public void mouseExited(MouseEvent paramAnonymousMouseEvent)
      {
        WavePanel.this.this_mouseExited(paramAnonymousMouseEvent);
      }

      public void mousePressed(MouseEvent paramAnonymousMouseEvent)
      {
        WavePanel.this.this_mousePressed(paramAnonymousMouseEvent);
      }

      public void mouseReleased(MouseEvent paramAnonymousMouseEvent)
      {
        WavePanel.this.this_mouseReleased(paramAnonymousMouseEvent);
      }
    });
  }

  void makeWaves(double paramDouble)
  {
    int i = this.rightWave.length;
    ReflectionThing localReflectionThing1 = getRightMost();
    ReflectionThing localReflectionThing2 = null;
    if (localReflectionThing1 != null)
      i = localReflectionThing1.right;
    else
      return;
    do
    {
      if (this.mode == EM_MODE)
        this.timePhase = (paramDouble * this.waveVel);
      else
        this.timePhase = (paramDouble * this.qmEnergy / 5.0);
      i = localReflectionThing1.calcField(localReflectionThing2, i);
      localReflectionThing2 = localReflectionThing1;
      localReflectionThing1 = getLeftNeighbor(localReflectionThing1);
    }
    while (localReflectionThing1 != null);
  }

  static double[][] multTransMatrices(double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2)
  {
    double[][] arrayOfDouble = new double[2][2];
    double d1 = paramArrayOfDouble1[0][0];
    double d2 = paramArrayOfDouble1[0][1];
    double d3 = paramArrayOfDouble1[1][0];
    double d4 = paramArrayOfDouble1[1][1];
    double d5 = paramArrayOfDouble2[0][0];
    double d6 = paramArrayOfDouble2[0][1];
    double d7 = paramArrayOfDouble2[1][0];
    double d8 = paramArrayOfDouble2[1][1];
    arrayOfDouble[0][0] = (d1 * d5 - d2 * d7);
    arrayOfDouble[0][1] = (d1 * d6 + d2 * d8);
    arrayOfDouble[1][0] = (d5 * d3 + d4 * d7);
    arrayOfDouble[1][1] = (d4 * d8 - d6 * d3);
    return arrayOfDouble;
  }

  public void paint(Graphics paramGraphics)
  {
    if ((this.osi == null) || (this.currentw != getSize().width) || (this.currenth != getSize().height))
      setArrayBounds();
    if ((this.osi == null) || (this.owner.clock.isRunning()))
      return;
    paramGraphics.drawImage(this.osi, 0, 0, this.currentw, this.currenth, this);
  }

  void paintCoords(Graphics paramGraphics, int paramInt1, int paramInt2)
  {
    if ((!this.coordDisplay) && (!this.ampDisplay) && (!this.phaseDisplay))
      return;
    String str = "";
    double d1 = xFromPix(paramInt1) + this.xCoordinateOffset;
    if (this.coordDisplay)
      str = str + "x=" + this.mouseFormat.form(d1) + " y=" + this.mouseFormat.form(yFromPix(paramInt2));
    double d2;
    if (this.ampDisplay)
      if (this.mode == QM_MODE)
      {
        d2 = Math.sqrt(this.leftWave[paramInt1] * this.leftWave[paramInt1] + this.rightWave[paramInt1] * this.rightWave[paramInt1]);
        str = str + " |Psi|=" + this.mouseFormat.form(d2);
      }
      else
      {
        d2 = this.leftWave[paramInt1] + this.rightWave[paramInt1];
        str = str + " E=" + this.mouseFormat.form(d2);
      }
    if (this.phaseDisplay)
      if (this.mode == QM_MODE)
      {
        d2 = Math.atan2(this.rightWave[paramInt1], -this.leftWave[paramInt1]);
        str = str + " " + this.owner.label_phase + " " + this.mouseFormat.form(d2);
      }
      else
      {
        str = str + " " + this.owner.label_no_phase;
      }
    if (str.equals(""))
      return;
    Rectangle localRectangle = getBounds();
    paramGraphics.setColor(Color.yellow);
    FontMetrics localFontMetrics = paramGraphics.getFontMetrics(paramGraphics.getFont());
    this.boxWidth = Math.max(20 + localFontMetrics.stringWidth(str), this.boxWidth);
    paramGraphics.fillRect(0, localRectangle.height - 20, this.boxWidth, 20);
    paramGraphics.setColor(Color.black);
    paramGraphics.drawString(str, 10, localRectangle.height - 5);
    paramGraphics.drawRect(0, localRectangle.height - 20, this.boxWidth - 1, 20);
  }

  void paintCoords(int paramInt1, int paramInt2)
  {
    if ((!this.coordDisplay) && (!this.ampDisplay) && (!this.phaseDisplay))
      return;
    Graphics localGraphics = getGraphics();
    paintCoords(localGraphics, paramInt1, paramInt2);
    localGraphics.dispose();
  }

  void paintDrawingThings(Graphics paramGraphics)
  {
    Thing localThing = null;
    Enumeration localEnumeration = this.drawingThings.elements();
    while (localEnumeration.hasMoreElements())
    {
      localThing = (Thing)localEnumeration.nextElement();
      localThing.paint(paramGraphics);
    }
  }

  public void paintLast(Graphics paramGraphics, ReflectionThing paramReflectionThing)
  {
    ReflectionThing localReflectionThing = paramReflectionThing;
    if (localReflectionThing == null)
    {
      if (this.mouseDown)
        paintCoords(paramGraphics, this.mousex, this.mousey);
      return;
    }
    localReflectionThing.fillToEnd(paramGraphics);
    paintWave(paramGraphics);
    if (this.mouseDown)
      paintCoords(paramGraphics, this.mousex, this.mousey);
  }

  void paintLegend(Graphics paramGraphics)
  {
    FontMetrics localFontMetrics = paramGraphics.getFontMetrics(paramGraphics.getFont());
    String str1 = this.owner.label_left_wave;
    String str2 = this.owner.label_right_wave;
    String str3 = this.owner.label_sum;
    int i = Math.max(110, 10 + localFontMetrics.stringWidth(str2));
    int j = 45;
    paramGraphics.setColor(Color.white);
    paramGraphics.fillRect(5, 5, i - 2, j - 2);
    paramGraphics.setColor(Color.blue);
    paramGraphics.drawString(str2, 10, 20);
    paramGraphics.setColor(Color.red);
    paramGraphics.drawString(str1, 10, 32);
    paramGraphics.setColor(Color.green);
    paramGraphics.drawString(str3, 10, 44);
  }

  void paintMessage(Graphics paramGraphics)
  {
    FontMetrics localFontMetrics = paramGraphics.getFontMetrics(paramGraphics.getFont());
    int i = Math.max(110, 10 + localFontMetrics.stringWidth(this.message));
    paramGraphics.setColor(Color.yellow);
    paramGraphics.fillRect(this.currentw - i - 3, this.currenth - 18, i, 15);
    paramGraphics.setColor(Color.black);
    paramGraphics.drawString(this.message, this.currentw - i, this.currenth - 6);
  }

  public void paintOSI()
  {
    Graphics localGraphics = null;
    try
    { if( /** @j2sNative true ||*/ false){  osi=null;}
      if ((this.osi == null) || (this.currentw != getSize().width) || (this.currenth != getSize().height))
        setArrayBounds();
      if (this.osi == null)
        return;
      localGraphics = this.osi.getGraphics();
      if (localGraphics == null)
        return;
      ReflectionThing localReflectionThing = getRightMost();
      if (localReflectionThing != null)
        localGraphics.setColor(localReflectionThing.color);
      else
        localGraphics.setColor(Color.black);
      localGraphics.fillRect(0, 0, this.currentw, this.currenth);
      paintReflectionThings(localGraphics, localReflectionThing);
      paintDrawingThings(localGraphics);
      localGraphics.setColor(Color.black);
      if (this.showMessage)
        paintMessage(localGraphics);
      if ((this.mode == QM_MODE) && (this.showEnergy))
        paintQMEnergy(localGraphics);
      paintTime(localGraphics);
      if (!this.titleStr.equals(""))
      {
        this.title.setText(this.titleStr);
        Font localFont = localGraphics.getFont();
        localGraphics.setFont(this.font);
        this.title.draw(localGraphics, this.currentw / 2, 15, 0);
        localGraphics.setFont(localFont);
      }
    }
    catch (Exception localException)
    {
    }
    finally
    {
      if (localGraphics != null)
        localGraphics.dispose();
    }
  }

  public void paintOffScreen()
  {
    paintOSI();
    Graphics localGraphics = getGraphics();
    if ((localGraphics == null) || (this.osi == null))
      return;
    localGraphics.drawImage(this.osi, 0, 0, this.currentw, this.currenth, this);
    localGraphics.dispose();
  }

  void paintQMEnergy(Graphics paramGraphics)
  {
    if (!this.showEnergy)
      return;
    paramGraphics.setColor(Color.black);
    Font localFont = paramGraphics.getFont();
    paramGraphics.setFont(this.font);
    String str = this.owner.label_energy + this.format.form(SUtil.chop(this.qmEnergy, 1.0E-012D));
    if (this.showTime)
      paramGraphics.drawString(str, 10, 42);
    else
      paramGraphics.drawString(str, 10, 25);
    paramGraphics.setFont(localFont);
  }

  /**
  *
  * Method scans through vector an directs all media to paint themselves on given graphics context.
  *
  * @param g Graphics
  */
  void paintReflectionThings(Graphics g, ReflectionThing rightThing){
    ReflectionThing neighbor=null;
    for (Enumeration e = thingVector.elements(); e.hasMoreElements();){  // paint the wave
        ReflectionThing t = (ReflectionThing)e.nextElement();
        if (t!=null) t.paintOS(g);
        neighbor=t;
    }
    paintLast(g,rightThing);
    g.setColor(Color.black);
    if(mode==QM_MODE)g.drawLine(0,currenth/2,currentw,currenth/2);
    for (Enumeration e2 = thingVector.elements(); e2.hasMoreElements();){  // paint the text
        ReflectionThing t = (ReflectionThing)e2.nextElement();
        if (t!=null) t.paintValue(g);
    }

    for (Enumeration e = drawingThings.elements(); e.hasMoreElements();){  // paint the wave
      Object t = (Object)e.nextElement();
    }

  }
  
  void paintTime(Graphics paramGraphics)
  {
    if ((!this.showTime) || (this.owner == null))
      return;
    paramGraphics.setColor(Color.black);
    Font localFont = paramGraphics.getFont();
    paramGraphics.setFont(this.font);
    String str = this.format.form(SUtil.chop(this.owner.clock.getTime(), 1.0E-012D));
    paramGraphics.drawString(this.owner.label_time + " " + str, 10, 25);
    paramGraphics.setFont(localFont);
  }

  void paintWave(Graphics paramGraphics)
  {
    if ((this.showPhaseColor) && (this.mode == QM_MODE))
      paintWave_color(paramGraphics);
    else
      paintWave_function(paramGraphics);
  }

  void paintWave_color(Graphics paramGraphics)
  {
    if ((!this.showWave) || (this.leftWave.length < 2))
      return;
    for (int i = 0; i < this.leftWave.length; i++)
    {
      this.xpoints[i] = i;
      if (this.mode == QM_MODE)
        this.ypoints[i] = ((int)(Math.sqrt(this.leftWave[i] * this.leftWave[i] + this.rightWave[i] * this.rightWave[i]) * this.ppu));
      else
        this.ypoints[i] = ((int)((this.leftWave[i] + this.rightWave[i]) * this.ppu));
      paramGraphics.setColor(colorFromPhase(Math.atan2(this.leftWave[i], this.rightWave[i])));
      paramGraphics.drawLine(i, this.currenth / 2 - this.ypoints[i], i, this.currenth / 2 + this.ypoints[i]);
    }
  }

  void paintWave_function(Graphics paramGraphics){
    if ((!this.showWave) || (this.leftWave.length < 2))
      return;
    for (int i = 0; i < this.leftWave.length; i++)
    {
      this.xpoints[i] = i;
      if (this.mode == QM_MODE)
        this.ypoints[i] = (-(int)(Math.sqrt(this.leftWave[i] * this.leftWave[i] + this.rightWave[i] * this.rightWave[i]) * this.ppu) + this.currenth / 2);
      else
        this.ypoints[i] = ((int)((this.leftWave[i] + this.rightWave[i]) * this.ppu) + this.currenth / 2);
    }
    this.ypoints[(this.leftWave.length - 1)] = this.ypoints[(this.leftWave.length - 2)];
	paramGraphics.setColor(this.waveColor);
    paramGraphics.drawPolyline(this.xpoints, this.ypoints, this.leftWave.length);
  }

  public void pause()
  {
    this.owner.pause();
  }

  public int pixFromX(double paramDouble)
  {
    return (int)(this.ppu * paramDouble);
  }

  public int pixFromY(double paramDouble)
  {
    return (int)(this.currenth / 2.0D - paramDouble * this.ppu);
  }

  public synchronized void recalc()
  {
    boolean bool = this.owner.clock.isRunning();
    this.owner.clock.stopClock();
    if ((this.osi == null) || (this.currentw != getSize().width) || (this.currenth != getSize().height))
      setArrayBounds();
    if (this.osi == null)
      return;
    calcAmps();
    calcMinMaxN();
    paintOffScreen();
    if (bool)
      this.owner.clock.startClock();
    else if ((this.autoRefresh) && (this.sendData))
      this.owner.updateDataConnections();
  }

  void removeAllThings()
  {
    this.drawingThings.removeAllElements();
    Vector localVector;
    synchronized (this.thingVector)
    {
      localVector = (Vector)this.thingVector.clone();
    }
    Enumeration localEnumeration = localVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      this.owner.removeDataSource(localReflectionThing.getID());
    }
    this.thingVector.removeAllElements();
    arrangeMedia();
    if (this.autoRefresh)
      recalc();
  }

  public void removeObject(int paramInt)
  {
    ReflectionThing localReflectionThing = getReflectionThing(paramInt);
    if (localReflectionThing == null)
      return;
    this.thingVector.removeElement(localReflectionThing);
    arrangeMedia();
    if (this.autoRefresh)
      recalc();
    try
    {
      this.owner.removeDataSource(localReflectionThing.hashCode());
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      System.out.println("error in addMediaDataSource()");
    }
  }

  void scaleWaves(double paramDouble1, double paramDouble2)
  {
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      if (localReflectionThing != null)
        localReflectionThing.scaleWaves(paramDouble1, paramDouble2);
    }
  }

  public void setAmpDisplay(boolean paramBoolean)
  {
    this.ampDisplay = paramBoolean;
  }

  public void setArray(int paramInt)
  {
    this.fixedPts = true;
    this.currentw = paramInt;
    this.leftWave = new double[this.currentw];
    this.rightWave = new double[this.currentw];
    this.xpoints = new int[this.currentw];
    this.ypoints = new int[this.currentw];
    this.osi = createImage(this.currentw, 1);
    arrangeMedia();
  }

  public void setArrayBounds()
  {
    if (getBounds().height > 4)
      this.fixedPts = false;
    if (this.fixedPts)
      return;
    //this.currentw = getBounds().width;
    //this.currenth = getBounds().height;
    this.currentw = this.getWidth();
    this.currenth = this.getHeight();
    if ((this.currentw <= 4) || (this.currenth <= 4 ))
      return;
    if(this.leftWave==null || this.leftWave.length!= this.currentw){
	    this.leftWave = new double[this.currentw];
	    this.rightWave = new double[this.currentw];
	    this.xpoints = new int[this.currentw];
	    this.ypoints = new int[this.currentw];
    }
    if(this.osi==null || this.osi.getWidth(null)!=this.currentw || this.osi.getHeight(null)!=this.currenth) {
      this.osi = createImage(this.currentw, this.currenth);
      arrangeMedia();
      //System.out.println("end setArrayBounds obj="+rightWave);
    }
  }

  public void setAutoRefresh(boolean paramBoolean)
  {
    this.autoRefresh = paramBoolean;
    if (paramBoolean)
      recalc();
  }

  public void setCaption(int paramInt, String paramString)
  {
    ReflectionThing localReflectionThing = getReflectionThing(paramInt);
    if (localReflectionThing == null)
      return;
    localReflectionThing.caption = paramString;
    localReflectionThing.showCaption = true;
  }

  public void setCoordDisplay(boolean paramBoolean)
  {
    this.coordDisplay = paramBoolean;
  }

  public void setDefault()
  {
    this.showMessage = false;
    if (this.waveDataSource != null)
      this.owner.removeDataSource(this.waveDataSource.getID());
    this.waveDataSource = null;
    removeAllThings();
    this.showPhaseColor = true;
    this.dragEnergy = false;
    this.showRWave = false;
    this.showLWave = false;
    this.showWave = true;
    this.rightPixOffset = 0;
    this.leftPixOffset = 0;
    this.xCoordinateOffset = 0.0D;
    this.reWaveColor = new Color(128, 0, 0);
    this.imWaveColor = new Color(0, 64, 64);
  }

  public void setDragEnergy(int paramInt, boolean paramBoolean)
  {
    if (paramInt == 0)
    {
      this.dragEnergy = paramBoolean;
      return;
    }
    ReflectionThing localReflectionThing = getReflectionThing(paramInt);
    if (localReflectionThing != null)
      localReflectionThing.dragPotential = paramBoolean;
  }

  public void setEnergyDisplay(boolean paramBoolean)
  {
    this.showEnergy = paramBoolean;
  }

  public final boolean setFormat(String paramString)
  {
    try
    {
      this.format = new Format(paramString);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      System.out.println("Illegal numeric format:" + paramString);
      return false;
    }
    return true;
  }

  public void setImRGB(int paramInt1, int paramInt2, int paramInt3)
  {
    this.imWaveColor = new Color(paramInt1, paramInt2, paramInt3);
  }

  public void setIndexN(int paramInt, double paramDouble)
  {
    ReflectionThing localReflectionThing = getReflectionThing(paramInt);
    if (localReflectionThing != null)
      if ((localReflectionThing instanceof EMThing))
        localReflectionThing.setIndexN(paramDouble);
      else
        localReflectionThing.setPotential(paramDouble);
    if (this.autoRefresh)
      recalc();
  }

  public void setMagMax(double paramDouble)
  {
    if (paramDouble > 0.0D)
      this.magMax = paramDouble;
    if (this.autoRefresh)
      recalc();
  }

  public void setMediaWidth(int paramInt, double paramDouble)
  {
    this.sendData = false;
    boolean bool = this.owner.clock.isRunning();
    this.owner.clock.stopClock();
    if (paramDouble <= 0.0D)
      return;
    ReflectionThing localReflectionThing = getReflectionThing(paramInt);
    if (localReflectionThing == null)
      return;
    localReflectionThing.setWidth(paramDouble);
    arrangeMedia();
    if (this.autoRefresh)
      recalc();
    if (bool)
      this.owner.clock.startClock();
    this.sendData = true;
  }

  public void setMode(int paramInt)
  {
    paramInt = Math.abs(paramInt);
    paramInt = Math.min(paramInt, 1);
    this.mode = paramInt;
    removeAllThings();
  }

  public void setPhaseDisplay(boolean paramBoolean)
  {
    this.phaseDisplay = paramBoolean;
  }

  void setQMDragMinMaxE(double paramDouble1, double paramDouble2)
  {
    this.enMin = paramDouble1;
    this.enMax = paramDouble2;
  }

  public void setQMEnergy(double paramDouble)
  {
    if (this.mode != QM_MODE)
      return;
    this.sendData = false;
    boolean bool = this.owner.clock.isRunning();
    this.owner.clock.stopClock();
    this.qmEnergy = paramDouble;
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      localReflectionThing.setPotential(localReflectionThing.potential);
    }
    if (this.autoRefresh)
      recalc();
    if (bool)
      this.owner.clock.startClock();
    this.sendData = true;
  }

  public void setRGB(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ReflectionThing localReflectionThing = getReflectionThing(paramInt1);
    if (localReflectionThing != null)
    {
      localReflectionThing.setColor(paramInt2, paramInt3, paramInt4);
      return;
    }
    Thing localThing = getDrawingThing(paramInt1);
    if (localThing != null)
    {
      localThing.setColor(new Color(paramInt2, paramInt3, paramInt4));
      return;
    }
  }

  public void setReRGB(int paramInt1, int paramInt2, int paramInt3)
  {
    this.reWaveColor = new Color(paramInt1, paramInt2, paramInt3);
  }

  public void setTimeDisplay(boolean paramBoolean)
  {
    this.showTime = paramBoolean;
  }

  public void setTitle(String paramString)
  {
    this.titleStr = paramString;
  }

  void setWaveVelocity(double paramDouble)
  {
    this.sendData = false;
    boolean bool = this.owner.clock.isRunning();
    this.owner.clock.stopClock();
    this.waveVel = paramDouble;
    arrangeMedia();
    recalc();
    if (bool)
      this.owner.clock.startClock();
    this.sendData = true;
  }

  public void setWavelength(double paramDouble)
  {
    this.sendData = false;
    boolean bool = this.owner.clock.isRunning();
    this.owner.clock.stopClock();
    this.lambda = paramDouble;
    arrangeMedia();
    recalc();
    if (bool)
      this.owner.clock.startClock();
    this.sendData = true;
  }

  public void step(double paramDouble1, double paramDouble2)
  {
    if (!this.autoRefresh)
      return;
    if ((this.osi == null) || (this.currentw != getSize().width) || (this.currenth != getSize().height))
      setArrayBounds();
    if (this.osi == null)
      return;
    this.time = paramDouble2;
    makeWaves(paramDouble2);
    paintOffScreen();
    if (this.sendData)
      this.owner.updateDataConnections();
  }

  void this_componentResized(ComponentEvent paramComponentEvent)
  {
    setArrayBounds();
    paintOffScreen();
  }

  void this_mouseDragged(MouseEvent paramMouseEvent)
  {
    int i = this.mousey - paramMouseEvent.getY();
    if ((this.insideThing != null) && (this.insideThing.dragPotential))
    {
    	  System.out.println("Drag thing");
      this.insideThing.setPotential(this.insideThing.potential + i / 100.0);
      recalc();
    }
    else if ((this.dragThing == null) && (this.dragEnergy))
    {
      if (this.mode == EM_MODE)
      {
        setWavelength(Math.max(this.lambda + i / 100.0D, this.highestN * 3.0D * 3.141592653589793D / this.ppu));
      }
      else
      {
        double d = this.qmEnergy + i / 100.0D;
        d = Math.max(d, this.lowestN - 1.0D);
        d = Math.max(d, this.enMin);
        d = Math.min(d, this.enMax);
        setQMEnergy(d);
      }
    }
    this.mousex = paramMouseEvent.getX();
    this.mousey = paramMouseEvent.getY();
    if (this.dragThing != null)
    {
     System.out.println("Drag set media width");
      setMediaWidth(this.dragThing.hashCode(), (paramMouseEvent.getX() - this.dragThing.left) / this.ppu);
      this.owner.updateDataConnections();
    }
    paintCoords(this.mousex, this.mousey);
  }

  void this_mouseEntered(MouseEvent paramMouseEvent)
  {
    setCursor(Cursor.getPredefinedCursor(1));
  }

  void this_mouseExited(MouseEvent paramMouseEvent)
  {
    setCursor(Cursor.getPredefinedCursor(0));
    this.dragThing = null;
  }

  void this_mouseMoved(MouseEvent paramMouseEvent)
  {
    Enumeration localEnumeration = this.thingVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ReflectionThing localReflectionThing = (ReflectionThing)localEnumeration.nextElement();
      if (localReflectionThing.isNearBoundary(paramMouseEvent.getX(), paramMouseEvent.getY()))
      {
        setCursor(Cursor.getPredefinedCursor(12));
        return;
      }
    }
    setCursor(Cursor.getPredefinedCursor(1));
  }

  void this_mousePressed(MouseEvent paramMouseEvent)
  {
    Object localObject;
    if ((paramMouseEvent.getModifiers() & 0x4) != 0)
    {
      if (this.osi == null)
        return;
      localObject = new WaveFrame(this.osi);
      ((a2s.Frame)localObject).show();
    }
    else
    {
      this.mouseDown = true;
      this.mousex = paramMouseEvent.getX();
      this.mousey = paramMouseEvent.getY();
      localObject = this.thingVector.elements();
      while (((Enumeration)localObject).hasMoreElements())
      {
        ReflectionThing localReflectionThing = (ReflectionThing)((Enumeration)localObject).nextElement();
        if (localReflectionThing.isNearBoundary(this.mousex, this.mousey))
        {
          if (this.owner.clock.isRunning())
          {
            pause();
            this.wasRunning = true;
          }
          this.dragThing = localReflectionThing;
        }
      }
      this.insideThing = isInsideThing(this.mousex, this.mousey);
    }
  }

  void this_mouseReleased(MouseEvent paramMouseEvent)
  {
    this.mousex = paramMouseEvent.getX();
    this.mousey = paramMouseEvent.getY();
    this.dragThing = null;
    this.insideThing = null;
    this.mouseDown = false;
    this.boxWidth = 0;
    paintOSI();
    repaint();
    if (this.wasRunning)
    {
      forward();
      this.wasRunning = false;
    }
  }

  public void update(Graphics paramGraphics)
  {
    paint(paramGraphics);
  }

  public double xFromPix(int paramInt)
  {
    return paramInt / (1.0 * this.ppu);
  }

  public double yFromPix(int paramInt)
  {
    int i = this.currenth / 2;
    return (i - paramInt) / (1.0 * this.ppu);
  }

  public class WaveDataSource
    implements SDataSource
  {
    double[][] ds = new double[WavePanel.this.currentw][4];
    String[] varStrings = { "x", "re", "im", "a" };

    WaveDataSource()
    {
      try
      {
        SApplet.addDataSource(this);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }

    public int getID()
    {
      return hashCode();
    }

    public SApplet getOwner()
    {
      return WavePanel.this.owner;
    }

    public String[] getVarStrings()
    {
      return this.varStrings;
    }

    public double[][] getVariables()
    {
      if ((this.ds == null) || (WavePanel.this.leftWave == null) || (WavePanel.this.leftWave.length < 4))
        return new double[1][4];
      if (this.ds.length != WavePanel.this.leftWave.length)
        this.ds = new double[WavePanel.this.leftWave.length][4];
      for (int i = 0; i < WavePanel.this.leftWave.length; i++)
      {
        this.ds[i][0] = (i / WavePanel.this.ppu);
        this.ds[i][1] = WavePanel.this.rightWave[i];
        this.ds[i][2] = WavePanel.this.leftWave[i];
        if (WavePanel.this.mode == WavePanel.QM_MODE)
          this.ds[i][3] = Math.sqrt(WavePanel.this.leftWave[i] * WavePanel.this.leftWave[i] + WavePanel.this.rightWave[i] * WavePanel.this.rightWave[i]);
        else
          this.ds[i][3] = (WavePanel.this.leftWave[i] + WavePanel.this.rightWave[i]);
      }
      return this.ds;
    }

    public void setOwner(SApplet paramSApplet)
    {
    }
  }
}

/* Location:           C:\Users\wc\PhysletWorkspace\html\ReflectionSource\bin\
 * Qualified Name:     reflection.WavePanel
 * JD-Core Version:    0.6.2
 */