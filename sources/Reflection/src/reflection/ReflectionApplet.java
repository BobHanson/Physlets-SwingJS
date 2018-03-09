package reflection;

import a2s.*;
import edu.davidson.display.ArrowThing;
import edu.davidson.display.BoxThing;
import edu.davidson.display.CaptionThing;
import edu.davidson.display.CircleThing;
import edu.davidson.display.RectangleThing;
import edu.davidson.display.SInteger;
import edu.davidson.display.SNumber;
import edu.davidson.display.TextThing;
import edu.davidson.display.Thing;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.graphics.SPanel;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReflectionApplet extends SApplet
{
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  String button_add = "Add/Change";
  String button_forward = "Step";
  String button_reset = "Reset";
  String button_start = "Run";
  String button_stop = "Stop";
  Button changeBtn = new Button();
  EtchedBorder controlPanel = new EtchedBorder();
  EtchedBorder etchedBorder2 = new EtchedBorder();
  EtchedBorder etchedBorder3 = new EtchedBorder();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  int fps;
  double fq;
  SNumber indexField = new SNumber();
  Button initBtn = new Button();
  Label label1 = new Label();
  Label label2 = new Label();
  String label_energy = "Energy =";
  String label_left_wave = "Left Wave";
  String label_no_phase = "Phase not available.";
  String label_number = "#:";
  String label_phase = "Phase =";
  String label_refraction = "n =";
  String label_right_wave = "Right Wave";
  String label_sum = "Sum";
  String label_time = "Time:";
  double lam;
  String m;
  double magMax;
  SInteger mediaField = new SInteger();
  String mode;
  SPanel panel1 = new SPanel();
  SPanel panel2 = new SPanel();
  Panel panel3 = new Panel();
  double ppu;
  Button runBtn = new Button();
  int selectedMedia = 0;
  boolean showControls;
  Button stepBtn = new Button();
  WavePanel wavePanel = new WavePanel(this);
  double wv;

  private int addDrawingObject(String paramString1, String paramString2)
  {
    double d1 = 0.0D;
    double d2 = 0.0D;
    int i = 20;
    int j = 20;
    int k = 10;
    int n = 10;
    paramString1 = paramString1.toLowerCase().trim();
    paramString1 = SUtil.removeWhitespace(paramString1);
    String str1 = paramString2.trim();
    paramString2 = SUtil.removeWhitespace(paramString2);
    Object localObject = null;
    if (paramString1.equals("box"))
    {
      if (SUtil.parameterExist(paramString2, "x="))
        d1 = SUtil.getParam(paramString2, "x=");
      if (SUtil.parameterExist(paramString2, "y="))
        d2 = SUtil.getParam(paramString2, "y=");
      if (SUtil.parameterExist(paramString2, "w="))
        i = (int)SUtil.getParam(paramString2, "w=");
      if (SUtil.parameterExist(paramString2, "h="))
        j = (int)SUtil.getParam(paramString2, "h=");
      localObject = new BoxThing(this, this.wavePanel, d1, d2, i, j);
    }
    else if (paramString1.equals("rectangle"))
    {
      if (SUtil.parameterExist(paramString2, "x="))
        d1 = SUtil.getParam(paramString2, "x=");
      if (SUtil.parameterExist(paramString2, "y="))
        d2 = SUtil.getParam(paramString2, "y=");
      if (SUtil.parameterExist(paramString2, "w="))
        i = (int)SUtil.getParam(paramString2, "w=");
      if (SUtil.parameterExist(paramString2, "h="))
        j = (int)SUtil.getParam(paramString2, "h=");
      localObject = new RectangleThing(this, this.wavePanel, d1, d2, i, j);
    }
    else if (paramString1.equals("circle"))
    {
      if (SUtil.parameterExist(paramString2, "x="))
        d1 = SUtil.getParam(paramString2, "x=");
      if (SUtil.parameterExist(paramString2, "y="))
        d2 = SUtil.getParam(paramString2, "y=");
      if (SUtil.parameterExist(paramString2, "r="))
        k = (int)SUtil.getParam(paramString2, "r=");
      localObject = new CircleThing(this, this.wavePanel, d1, d2, k);
    }
    else if (paramString1.equals("arrow"))
    {
      double d3 = 1.0D;
      double d4 = 1.0D;
      n = 4;
      if (SUtil.parameterExist(paramString2, "s="))
        n = (int)SUtil.getParam(paramString2, "s=");
      if (SUtil.parameterExist(paramString2, "x="))
        d1 = SUtil.getParam(paramString2, "x=");
      if (SUtil.parameterExist(paramString2, "y="))
        d2 = SUtil.getParam(paramString2, "y=");
      if (SUtil.parameterExist(paramString2, "h="))
        d3 = SUtil.getParam(paramString2, "h=");
      if (SUtil.parameterExist(paramString2, "v="))
        d4 = SUtil.getParam(paramString2, "v=");
      localObject = new ArrowThing(this, this.wavePanel, n, d3, d4, d1, d2);
      if (SUtil.parameterExist(paramString2, "thickness="))
        ((ArrowThing)localObject).thickness = Math.max((int)SUtil.getParam(paramString2, "thickness="), 1);
    }
    else
    {
      String str2;
      if ((paramString1.equals("text")) || (paramString1.equals("calculation")))
      {
        str2 = "";
        String str3 = "";
        if (SUtil.parameterExist(paramString2, "x="))
          d1 = SUtil.getParam(paramString2, "x=");
        if (SUtil.parameterExist(paramString2, "y="))
          d2 = SUtil.getParam(paramString2, "y=");
        if (SUtil.parameterExist(paramString2, "txt="))
          str2 = SUtil.getParamStr(str1, "txt=");
        if (SUtil.parameterExist(paramString2, "text="))
          str2 = SUtil.getParamStr(str1, "text=");
        if (SUtil.parameterExist(paramString2, "calc="))
          str3 = SUtil.getParamStr(paramString2, "calc=");
        localObject = new TextThing(this, this.wavePanel, str2, str3, d1, d2);
      }
      else if (paramString1.equals("caption"))
      {
        str2 = "";
        if (SUtil.parameterExist(paramString2, "x="))
          d1 = SUtil.getParam(paramString2, "x=");
        if (SUtil.parameterExist(paramString2, "y="))
          d2 = SUtil.getParam(paramString2, "y=");
        if (SUtil.parameterExist(paramString2, "txt="))
          str2 = SUtil.getParamStr(str1, "txt=");
        if (SUtil.parameterExist(paramString2, "text="))
          str2 = SUtil.getParamStr(str1, "text=");
        localObject = new CaptionThing(this, this.wavePanel, str2, d1, d2);
      }
    }
    if ((localObject != null) && (SUtil.parameterExist(paramString2, "label=")))
      ((Thing)localObject).setLabel(SUtil.getParamStr(str1, "label="));
    if (localObject != null)
    {
      this.wavePanel.drawingThings.addElement(localObject);
      return ((Thing)localObject).getID();
    }
    return 0;
  }

  /** @deprecated */
  public int addMedium(double paramDouble1, double paramDouble2)
  {
    ReflectionThing localReflectionThing = this.wavePanel.addMedium(paramDouble1, paramDouble2);
    this.indexField.setValue(paramDouble1);
    this.mediaField.setValue(this.wavePanel.thingVector.size());
    return localReflectionThing.hashCode();
  }

  public synchronized int addObject(String paramString1, String paramString2)
  {
    int i = 0;
    double d1 = 1.0D;
    double d2 = 1.0D;
    paramString1 = paramString1.toLowerCase().trim();
    paramString1 = SUtil.removeWhitespace(paramString1);
    String str = paramString2.trim();
    paramString2 = SUtil.removeWhitespace(paramString2);
    if ((paramString1.equals("film")) || (paramString1.equals("layer")) || (paramString1.equals("medium")))
    {
      if (SUtil.parameterExist(paramString2, "w="))
        d1 = SUtil.getParam(paramString2, "w=");
      if (SUtil.parameterExist(paramString2, "n="))
        d2 = SUtil.getParam(paramString2, "n=");
      if (SUtil.parameterExist(paramString2, "e="))
        d2 = SUtil.getParam(paramString2, "e=");
      i = addMedium(d2, d1);
    }
    if (i == 0)
      i = addDrawingObject(paramString1, str);
    if (i == 0)
      System.out.println("Object not created. name:" + paramString1 + "parameter list:" + paramString2);
    return i;
  }

  void changeBtn_actionPerformed(ActionEvent paramActionEvent)
  {
    double d1 = this.indexField.getValue();
    if (d1 < 0.0D)
    {
      this.indexField.setBackground(Color.red);
      return;
    }
    this.indexField.setBackground(Color.white);
    int i = this.mediaField.getValue();
    if (i < 1)
    {
      this.mediaField.setBackground(Color.red);
      return;
    }
    this.mediaField.setBackground(Color.white);
    if (i > this.wavePanel.thingVector.size())
    {
      setAutoRefresh(false);
      double d2 = 40.0D / this.ppu;
      if (i == 1)
        d2 = 60.0;
      int j = addMedium(d1, d2);
      setDragable(j, true);
      setRGB(j, (int)(255.0D * Math.random()), 255, (int)(255.0D * Math.random()));
      this.mediaField.setBackground(Color.white);
      setAutoRefresh(true);
      return;
    }
    ReflectionThing localReflectionThing = (ReflectionThing)this.wavePanel.thingVector.elementAt(i - 1);
    this.wavePanel.setIndexN(localReflectionThing.getID(), d1);
    recalculate();
  }

  public void forward()
  {
    this.clock.setDt(this.clock.getDt());
    recalculate();
    this.clock.startClock();
    this.runBtn.setLabel(this.button_stop);
  }

  public int getAppletCount()
  {
    if (this.firstTime)
      return 0;
    return super.getAppletCount();
  }

  public String getAppletInfo()
  {
    return "Applet Information";
  }

  public String[][] getParameterInfo()
  {
    String[][] arrayOfString= { { "ShowControls", "boolean", "true or false" } };
    return arrayOfString;
  }

  public double getQMEnergy()
  {
    return this.wavePanel.getQMEnergy();
  }

  public double getReflection()
  {
    return Math.max(0.0D, this.wavePanel.refC);
  }

  public double getTransmission()
  {
    return Math.max(0.0D, this.wavePanel.tranC);
  }

  public int getWaveID()
  {
    return this.wavePanel.getWaveID();
  }

  public void init()
  {
    initResources(null);
    try
    {
      this.showControls = Boolean.valueOf(getParameter("ShowControls", "true")).booleanValue();
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
    }
    try
    {
      this.ppu = Double.valueOf(getParameter("PPU", "10")).doubleValue();
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
    try
    {
      this.magMax = Double.valueOf(getParameter("MaxAmp", "3")).doubleValue();
    }
    catch (Exception localException3)
    {
      localException3.printStackTrace();
    }
    try
    {
      this.lam = Double.valueOf(getParameter("Wavelength", "6")).doubleValue();
    }
    catch (Exception localException4)
    {
      localException4.printStackTrace();
    }
    try
    {
      this.wv = Double.valueOf(getParameter("WaveVelocity", "10")).doubleValue();
    }
    catch (Exception localException5)
    {
      localException5.printStackTrace();
    }
    try
    {
      this.fps = Integer.valueOf(getParameter("FPS", "4")).intValue();
    }
    catch (Exception localException6)
    {
      localException6.printStackTrace();
    }
    try
    {
      this.m = String.valueOf(getParameter("Mode", "EM")).toString();
    }
    catch (Exception localException7)
    {
      localException7.printStackTrace();
    }
    try
    {
      jbInit();
    }
    catch (Exception localException8)
    {
      localException8.printStackTrace();
    }
    this.clock.addClockListener(this.wavePanel);
    setMode(this.m);
    setShowControls(this.showControls);
    setPPU(this.ppu);
    setMagMax(this.magMax);
    setWavelength(this.lam);
    setFPS(this.fps);
    setWaveVelocity(this.wv);
    try
    {
      int i = Integer.parseInt(getParameter("NumPts", "-1"));
      if (i > 4)
        this.wavePanel.setArray(i);
    }
    catch (Exception localException9)
    {
      localException9.printStackTrace();
    }
    
  }

  void initBtn_actionPerformed(ActionEvent paramActionEvent)
  {
    setDefault();
  }

  private void jbInit()
    throws Exception
  {
    setLayout(this.borderLayout1);
    this.controlPanel.setLayout(this.borderLayout2);
    this.etchedBorder2.setLayout(this.flowLayout2);
    this.etchedBorder3.setLayout(this.flowLayout1);
    this.initBtn.setLabel(this.button_reset);
    this.initBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ReflectionApplet.this.initBtn_actionPerformed(paramAnonymousActionEvent);
      }
    });
    this.changeBtn.setLabel(this.button_add);
    this.changeBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ReflectionApplet.this.changeBtn_actionPerformed(paramAnonymousActionEvent);
      }
    });
    this.indexField.setText("1");
    this.indexField.setValue(1.0D);
    this.label1.setAlignment(2);
    this.label1.setText(this.label_refraction);
    this.label2.setAlignment(2);
    this.label2.setText(this.label_number);
    this.mediaField.setText("1");
    this.mediaField.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ReflectionApplet.this.mediaField_actionPerformed(paramAnonymousActionEvent);
      }
    });
    this.runBtn.setLabel(this.button_start);
    this.runBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ReflectionApplet.this.runBtn_actionPerformed(paramAnonymousActionEvent);
      }
    });
    this.panel2.setLayout(this.borderLayout3);
    this.panel1.setLayout(this.borderLayout4);
    this.panel3.setLayout(this.borderLayout5);
    this.panel2.setMinimumSize(new Dimension(100, 20));
    this.panel2.setPreferredSize(new Dimension(100, 20));
    this.panel1.setMinimumSize(new Dimension(150, 20));
    this.panel1.setPreferredSize(new Dimension(150, 20));
    this.stepBtn.setLabel(this.button_forward);
    this.stepBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ReflectionApplet.this.stepBtn_actionPerformed(paramAnonymousActionEvent);
      }
    });
    this.controlPanel.setBackground(Color.lightGray);
    setBackground(Color.lightGray);
    this.flowLayout2.setAlignment(2);
    this.flowLayout2.setHgap(2);
    add(this.wavePanel, "Center");
    add(this.controlPanel, "South");
    this.controlPanel.add(this.etchedBorder2, "Center");
    this.etchedBorder2.add(this.panel3, null);
    this.panel3.add(this.changeBtn, "West");
    this.etchedBorder2.add(this.panel2, null);
    this.panel2.add(this.label2, "Center");
    this.panel2.add(this.mediaField, "East");
    this.etchedBorder2.add(this.panel1, null);
    this.panel1.add(this.label1, "West");
    this.panel1.add(this.indexField, "Center");
    this.controlPanel.add(this.etchedBorder3, "West");
    this.etchedBorder3.add(this.initBtn, null);
    this.etchedBorder3.add(this.runBtn, null);
    this.etchedBorder3.add(this.stepBtn, null);
  }

  void mediaField_actionPerformed(ActionEvent paramActionEvent)
  {
    int i = this.mediaField.getValue();
    ReflectionThing localReflectionThing = this.wavePanel.getReflectionThingFromIndex(i - 1);
    if (localReflectionThing != null)
      this.indexField.setValue(localReflectionThing.indexN);
  }

  public void pause()
  {
    this.clock.stopClock();
    this.runBtn.setLabel(this.button_start);
  }

  public void recalculate()
  {
    this.wavePanel.recalc();
  }

  public void removeAllMedia()
  {
    this.wavePanel.removeAllThings();
    this.indexField.setValue(1.0D);
    this.mediaField.setValue(1);
  }

  public void removeObject(int paramInt)
  {
    this.wavePanel.removeObject(paramInt);
  }

  public void reverse()
  {
    this.clock.setDt(-this.clock.getDt());
    recalculate();
    this.clock.startClock();
    this.runBtn.setLabel(this.button_stop);
  }

  void runBtn_actionPerformed(ActionEvent paramActionEvent)
  {
    if (this.clock.isRunning())
    {
      pause();
      this.runBtn.setLabel(this.button_start);
    }
    else
    {
      forward();
      this.runBtn.setLabel(this.button_stop);
    }
  }

  public void setAmpDisplay(boolean paramBoolean)
  {
    this.wavePanel.setAmpDisplay(paramBoolean);
  }

  public void setAutoRefresh(boolean paramBoolean)
  {
    this.autoRefresh = paramBoolean;
    this.wavePanel.setAutoRefresh(paramBoolean);
  }

  public void setCoordDisplay(boolean paramBoolean)
  {
    this.wavePanel.setCoordDisplay(paramBoolean);
  }

  public void setDefault()
  {
    this.clock.stopClock();
    this.wavePanel.setDefault();
  }

  public void setDragEnergy(int paramInt, boolean paramBoolean)
  {
    this.wavePanel.setDragEnergy(paramInt, paramBoolean);
  }

  public boolean setDragable(int paramInt, boolean paramBoolean)
  {
    ReflectionThing localReflectionThing = this.wavePanel.getReflectionThing(paramInt);
    if (localReflectionThing != null)
    {
      localReflectionThing.dragable = paramBoolean;
      return true;
    }
    Thing localThing = this.wavePanel.getDrawingThing(paramInt);
    if (localThing != null)
    {
      localThing.setDragable(paramBoolean);
      return true;
    }
    return false;
  }

  public void setEnergyDisplay(boolean paramBoolean)
  {
    this.wavePanel.setEnergyDisplay(paramBoolean);
    if (this.autoRefresh)
      this.wavePanel.repaint();
  }

  public boolean setFont(int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    Font localFont = new Font(paramString, paramInt2, paramInt3);
    if (localFont == null) return false;
    Thing localThing = this.wavePanel.getDrawingThing(paramInt1);
    if (localThing != null)
    {
      localThing.setFont(localFont);
      return true;
    }
    ReflectionThing localReflectionThing = this.wavePanel.getReflectionThing(paramInt1);
    if ((localReflectionThing == null) && ((paramInt1 == 0) || (paramInt1 == this.wavePanel.hashCode())))
    {
      this.wavePanel.font = localFont;
      return true;
    }
    if (localReflectionThing == null)
      return false;
    localReflectionThing.font = localFont;
    if (this.wavePanel.autoRefresh)
      this.wavePanel.repaint();
    return true;
  }

  public boolean setFormat(int paramInt, String paramString)
  {
    ReflectionThing localReflectionThing = this.wavePanel.getReflectionThing(paramInt);
    if ((localReflectionThing == null) && ((paramInt == 0) || (paramInt == this.wavePanel.hashCode())))
      return this.wavePanel.setFormat(paramString);
    boolean bool = localReflectionThing.setFormat(paramString);
    if (this.wavePanel.autoRefresh)
      this.wavePanel.repaint();
    return bool;
  }

  public void setImRGB(int paramInt1, int paramInt2, int paramInt3)
  {
    this.wavePanel.setImRGB(paramInt1, paramInt2, paramInt3);
  }

  public void setIndexN(int paramInt, double paramDouble)
  {
    this.wavePanel.setIndexN(paramInt, paramDouble);
  }

  public void setLabel(int paramInt, String paramString)
  {
    this.wavePanel.setCaption(paramInt, paramString);
  }

  public void setMagMax(double paramDouble)
  {
    this.wavePanel.setMagMax(paramDouble);
  }

  public void setMediaWidth(int paramInt, double paramDouble)
  {
    this.wavePanel.setMediaWidth(paramInt, paramDouble);
  }

  public void setMessage(String paramString)
  {
    this.wavePanel.message = paramString;
    this.wavePanel.showMessage = true;
  }

  public void setMode(String paramString)
  {
    if (paramString.equals("QM"))
      this.wavePanel.setMode(1);
    else
      this.wavePanel.setMode(0);
    this.mode = paramString;
  }

  public boolean setObjectFont(int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    return setFont(paramInt1, paramString, paramInt2, paramInt3);
  }

  public void setPPU(double paramDouble)
  {
    this.wavePanel.ppu = paramDouble;
  }

  public void setPhaseDisplay(boolean paramBoolean)
  {
    this.wavePanel.setPhaseDisplay(paramBoolean);
  }

  public void setQMDragMinMaxE(double paramDouble1, double paramDouble2)
  {
    this.wavePanel.setQMDragMinMaxE(paramDouble1, paramDouble2);
  }

  public void setQMEnergy(double paramDouble)
  {
    this.wavePanel.setQMEnergy(paramDouble);
  }

  public void setQMPotential(int paramInt, double paramDouble)
  {
    setIndexN(paramInt, paramDouble);
  }

  public void setRGB(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.wavePanel.setRGB(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setReRGB(int paramInt1, int paramInt2, int paramInt3)
  {
    this.wavePanel.setReRGB(paramInt1, paramInt2, paramInt3);
  }

  protected void setResources()
  {
    this.button_start = this.localProperties.getProperty("button.start", this.button_start);
    this.button_stop = this.localProperties.getProperty("button.stop", this.button_stop);
    this.button_reset = this.localProperties.getProperty("button.reset", this.button_reset);
    this.button_forward = this.localProperties.getProperty("button.forward", this.button_forward);
    this.button_add = this.localProperties.getProperty("button.add", this.button_add);
    this.label_number = this.localProperties.getProperty("label.number", this.label_number);
    this.label_refraction = this.localProperties.getProperty("label.refraction", this.label_refraction);
    this.label_left_wave = this.localProperties.getProperty("label.left_wave", this.label_left_wave);
    this.label_right_wave = this.localProperties.getProperty("label.right_wave", this.label_right_wave);
    this.label_sum = this.localProperties.getProperty("label.sum", this.label_sum);
    this.label_energy = this.localProperties.getProperty("label.energy", this.label_energy);
    this.label_time = this.localProperties.getProperty("label.time", this.label_time);
    this.label_no_phase = this.localProperties.getProperty("label.no_phase", this.label_no_phase);
    this.label_phase = this.localProperties.getProperty("label.phase", this.label_phase);
  }

  public void setShowControls(boolean paramBoolean)
  {
    this.controlPanel.setVisible(paramBoolean);
    invalidate();
    validate();
  }

  public void setShowLWave(boolean paramBoolean, int paramInt)
  {
    this.wavePanel.showLWave = paramBoolean;
    this.wavePanel.leftPixOffset = paramInt;
    if (this.autoRefresh)
      this.wavePanel.recalc();
  }

  public void setShowPhaseColor(boolean paramBoolean)
  {
    this.wavePanel.showPhaseColor = paramBoolean;
    if (paramBoolean)
      this.wavePanel.showWave = true;
    if (this.autoRefresh)
      this.wavePanel.recalc();
  }

  public void setShowRWave(boolean paramBoolean, int paramInt)
  {
    this.wavePanel.showRWave = paramBoolean;
    this.wavePanel.rightPixOffset = paramInt;
    if (this.autoRefresh)
      this.wavePanel.recalc();
  }

  public boolean setShowValue(int paramInt, boolean paramBoolean)
  {
    ReflectionThing localReflectionThing = this.wavePanel.getReflectionThing(paramInt);
    if (localReflectionThing == null)
      return false;
    localReflectionThing.setShowValue(paramBoolean);
    return true;
  }

  public void setShowWave(boolean paramBoolean)
  {
    this.wavePanel.showWave = paramBoolean;
    if (this.autoRefresh)
      this.wavePanel.recalc();
  }

  public void setTimeDisplay(boolean paramBoolean)
  {
    this.wavePanel.setTimeDisplay(paramBoolean);
    if (this.autoRefresh)
      this.wavePanel.repaint();
  }

  public void setTitle(String paramString)
  {
    this.wavePanel.setTitle(paramString);
  }

  public boolean setVisibility(int paramInt, boolean paramBoolean)
  {
    ReflectionThing localReflectionThing = this.wavePanel.getReflectionThing(paramInt);
    if (localReflectionThing != null)
    {
      localReflectionThing.setVisibility(paramBoolean);
      return true;
    }
    Thing localThing = this.wavePanel.getDrawingThing(paramInt);
    if (localThing != null)
    {
      localThing.setDragable(paramBoolean);
      return true;
    }
    return false;
  }

  public void setWaveVelocity(double paramDouble)
  {
    this.wavePanel.setWaveVelocity(paramDouble);
  }

  public void setWavelength(double paramDouble)
  {
    if (paramDouble > 0.0D)
      this.wavePanel.setWavelength(paramDouble);
  }

  public void setXOffset(double paramDouble)
  {
    this.wavePanel.xCoordinateOffset = paramDouble;
  }

  public void start()
  {
    if ((this.firstTime) && (this.showControls))
    {
      setAutoRefresh(false);
      setAutoRefresh(true);
      forward();
      this.firstTime = false;
    }
    super.start();
  }

  public void step()
  {
    if (this.clock.isRunning())
    {
      pause();
      return;
    }
    this.clock.doStep();
    this.runBtn.setLabel(this.button_start);
  }

  public void stepBack()
  {
    stepTimeBack();
  }

  void stepBtn_actionPerformed(ActionEvent paramActionEvent)
  {
    step();
  }

  public void stepForward()
  {
    stepTimeForward();
  }

  public void stepTimeBack()
  {
    this.wavePanel.showMessage = false;
    if (this.clock.isRunning())
    {
      pause();
      this.runBtn.setLabel(this.button_start);
      return;
    }
    int i = 0;
    if (this.clock.getDt() < 0.0D)
      i = 1;
    this.clock.setDt(-Math.abs(this.clock.getDt()));
    this.clock.doStep();
    if (i == 0)
      this.clock.setDt(Math.abs(this.clock.getDt()));
  }

  public void stepTimeForward()
  {
    this.wavePanel.showMessage = false;
    if (this.clock.isRunning())
    {
      pause();
      this.runBtn.setLabel(this.button_start);
      return;
    }
    int i = 0;
    if (this.clock.getDt() < 0.0D)
      i = 1;
    this.clock.setDt(Math.abs(this.clock.getDt()));
    this.clock.doStep();
    if (i != 0)
      this.clock.setDt(-Math.abs(this.clock.getDt()));
  }
}

/* Location:           C:\Users\wc\PhysletWorkspace\html\ReflectionSource\bin\
 * Qualified Name:     reflection.ReflectionApplet
 * JD-Core Version:    0.6.2
 */