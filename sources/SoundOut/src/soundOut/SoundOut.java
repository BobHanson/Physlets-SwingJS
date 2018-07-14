// SoundOut Ver 0.6

package soundOut;

import a2s.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
//import java.applet.*;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.display.SSlider;
import edu.davidson.tools.*;


public class SoundOut extends SApplet implements ActionListener{
  public static boolean isJS = /** @j2sNative true || */ false;
  Button clearBtn = new Button();
  Button addBtn = new Button();
  Checkbox sndCkBox = new Checkbox();
  EtchedBorder bevelPanel3 = new EtchedBorder();
  Label label1 = new Label();
  TextField funcField = new TextField();
  SoundCanvas sonogram;
  SoundCanvas sonogramDetail;
  SoundPlayer soundPlayer;
  SoundData soundData;
  private int numPts=8000;    // 8000 pts is one sec.
  private String ampFunc="sin(400*pi*t)+sin(404*pi*t)";
  private boolean showControls=true;
  private double volume=1.0;
  private boolean mute=true;
  EtchedBorder bevelPanel2 = new EtchedBorder();
  //SplitPanel splitPanel1 = new SplitPanel();
  Panel splitPanel1 = new Panel();

  EtchedBorder controlPanel = new EtchedBorder();
  EtchedBorder bevelPanel5 = new EtchedBorder();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  EtchedBorder bevelPanel6 = new EtchedBorder();
  //GroupBox groupBox1 = new GroupBox();
  Panel groupBox1 = new Panel();
  SSlider volSlider = new SSlider();
  GridLayout gridLayout1 = new GridLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  //GroupBox groupBox2 = new GroupBox();
  Panel groupBox2 = new Panel();
  BorderLayout borderLayout6 = new BorderLayout();
  private boolean oneShot=false;
  GridLayout gridLayout2 = new GridLayout();


  //Construct the applet
  public SoundOut() {
  }

  //Initialize the applet
  public void init() {
    initResources(null);
    try { numPts = Integer.parseInt(this.getParameter("numPts", "8000")); }
        catch (Exception e) { e.printStackTrace(); }
    try { showControls = Boolean.valueOf(this.getParameter("showControls", "true")).booleanValue(); }
        catch (Exception e) { e.printStackTrace(); }
    try { ampFunc = this.getParameter("ampFunc", "sin(2*pi*400*t)"); }
        catch (Exception e) { e.printStackTrace(); }
    try { volume = Double.valueOf(this.getParameter("volume", "1.0")).doubleValue(); }
        catch (Exception e) { e.printStackTrace(); }
    try { mute = Boolean.valueOf(this.getParameter("mute", "false")).booleanValue(); }
        catch (Exception e) { e.printStackTrace(); }
    try { jbInit(); } catch (Exception e) { e.printStackTrace(); }

    soundData = new SoundData(numPts);
    funcField.setText(ampFunc);
    soundData.newAmpFunc(ampFunc);
    soundPlayer = new SoundPlayer(numPts,oneShot);
    calc();
    soundPlayer.setMute(sndCkBox.getState());
  }

  //Component initialization
  public void jbInit() throws Exception{
    bevelPanel2.setLayout(borderLayout1);
    clearBtn.setActionCommand("ClearSnd");
    clearBtn.addActionListener(new SoundOut_clearBtn_actionAdapter(this));
    this.setBackground(Color.lightGray);
    this.setSize(new Dimension(500, 500));
    clearBtn.setLabel("New");
    addBtn.setActionCommand("AddSnd");
    addBtn.addActionListener(new SoundOut_addBtn_actionAdapter(this));
    addBtn.setLabel("Add");
    sndCkBox.setState(mute);
    sndCkBox.setLabel("Mute");
    sndCkBox.addItemListener(new SoundOut_sndCKBox_itemAdapter(this));
    bevelPanel3.setLayout(borderLayout4);
    label1.setAlignment(1);
    label1.setText("f(t)=");
    splitPanel1.setBackground(Color.black);
        splitPanel1.setLayout(gridLayout2);
    //controlPanel.setMargins(new Insets(2, 2, 2, 2));
    //bevelPanel5.setBevelInner(EtchedBorder.FLAT);
    controlPanel.setLayout(borderLayout3);
    borderLayout1.setHgap(5);
    borderLayout1.setVgap(5);
    //bevelPanel6.setBevelInner(EtchedBorder.FLAT);
    //bevelPanel6.setMargins(new Insets(5, 5, 5, 5));
    groupBox1.setLayout(gridLayout1);
    //groupBox1.setLabel("Volume");
    //volSlider.setVinset(5);
    volSlider.setDValue(volume);
    volSlider.addAdjustmentListener(new SoundOut_volSlider_adjustmentAdapter(this));
    borderLayout5.setHgap(2);
    //groupBox2.setLabel("Function");
    bevelPanel6.setLayout(borderLayout5);
    bevelPanel5.setLayout(borderLayout6);
    //splitPanel1.addActionListener(new SoundOut_splitPanel1_actionAdapter(this));
    this.setLayout(borderLayout2);

    sonogram = new SoundCanvas();
    sonogram.addActionListener(this);
    sonogramDetail = new SoundCanvas();
    sonogramDetail.setNoCursors(true);

    gridLayout2.setColumns(1);
        gridLayout2.setRows(2);
        this.add(splitPanel1, BorderLayout.CENTER);
    if(showControls)this.add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(bevelPanel2, BorderLayout.CENTER);
    bevelPanel2.add(bevelPanel5, BorderLayout.WEST);
    bevelPanel5.add(groupBox2, BorderLayout.CENTER);
    groupBox2.add(addBtn, null);
    groupBox2.add(clearBtn, null);
    bevelPanel2.add(bevelPanel6, BorderLayout.EAST);
    bevelPanel6.add(sndCkBox, BorderLayout.CENTER);
    bevelPanel2.add(groupBox1, BorderLayout.CENTER);
    groupBox1.add(volSlider, null);
    controlPanel.add(bevelPanel3, BorderLayout.NORTH);
    bevelPanel3.add(label1, BorderLayout.WEST);
    bevelPanel3.add(funcField, BorderLayout.CENTER);
    //splitPanel1.add(sonogram, new PaneConstraints("sonogram", "sonogram", PaneConstraints.ROOT, 0.5f));
    //splitPanel1.add(sonogramDetail, new PaneConstraints("sonogramDetail", "sonogram", PaneConstraints.BOTTOM, 0.48394495f));
    splitPanel1.add(sonogram, null);
    splitPanel1.add(sonogramDetail, null);
        this.add(controlPanel, BorderLayout.SOUTH);
    //this.add(sonogram, new XYConstraints(183, 3, 340, 94));

  }

  //Get Applet information
  public String getAppletInfo() {
    return "SoundOut by Wolfgang Christian. email:wochristian@davidson.edu";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"numPts", "int", "Number of sample points"},
      {"showControls", "boolean", "Show controls."},
      {"ampFunc", "String", "Amplitude function"},
      {"volume", "double", "Volume"},
      {"mute", "boolean", "Mute Sound"},
    };
    return pinfo;
  }

  public void destroy(){
     setMute(true);
     soundPlayer.closeStream();
     super.destroy();
  }

  public void stop(){
     setMute(true);
     super.stop();
  }

  /**
 *    Start the applet thread.  Should not be called from JavaScript.
 *
 *    @see #forward
*/
public void start() {
    if (firstTime) {
        firstTime = false;
    }
    this.setRunningID(this);
    super.start();
}


  void calc() {
    double yVec[] = soundData.getAmpVec();
    soundPlayer.setYVec(yVec);
    sonogram.setYVec(yVec);
    sonogramDetail.setYVec(yVec,sonogram.getC1(),sonogram.getC2());
  }

  void addBtn_actionPerformed(ActionEvent e) {
       boolean isMute=mute;
       ampFunc=funcField.getText();
       soundData.addAmpFunc(ampFunc);
       if(oneShot){
          setMute(true);
          //soundPlayer.mute=true;
          soundPlayer.closeStream();
          soundPlayer = new SoundPlayer(numPts,oneShot);
          //soundPlayer.setMute(isMute);
       }
       calc();
  }


  public void setNumPts(int n){
    boolean isMute=mute;
    //soundPlayer.mute=true;
    soundPlayer.closeStream();
    soundData = new SoundData(numPts);
    funcField.setText(ampFunc);
    soundData.newAmpFunc(ampFunc);
    soundPlayer = new SoundPlayer(numPts,oneShot);
    calc();
    //try{Thread.sleep(50);}catch(InterruptedException e){;}
    //soundPlayer.setMute(isMute);
  }

  public synchronized void setSound(String str){
      soundData.newAmpFunc(str);
      calc();
  }
  public synchronized void  addSound(String str){
      boolean isMute=mute;
      soundData.addAmpFunc(str);
      if(oneShot){
          //soundPlayer.mute=true;
          soundPlayer.closeStream();
          soundPlayer = new SoundPlayer(numPts,oneShot);
          //try{Thread.sleep(50);}catch(InterruptedException e){;}
          //soundPlayer.setMute(isMute);
      }
      calc();
  }

  public synchronized void  setMute(boolean m){
      mute=m;
      sndCkBox.setState(mute);
      soundPlayer.setMute(sndCkBox.getState());
  }


  void splitPanel1_actionPerformed(ActionEvent e) {
       sonogram.repaint();
       sonogramDetail.repaint();
  }

  void sndCKBox_itemStateChanged(ItemEvent e) {
    soundPlayer.setMute(sndCkBox.getState());
  }

  void volSlider_adjustmentValueChanged(AdjustmentEvent e) {
      soundPlayer.setVolume(volSlider.getDValue());
  }

  void clearBtn_actionPerformed(ActionEvent e) {
      boolean isMute=mute;
      ampFunc=funcField.getText();
      if(oneShot){
          setMute(true);
          playOnce(ampFunc);
          return;
      }
      //soundPlayer.setMute(true);
      soundData.newAmpFunc(ampFunc);
      calc();
     //try{Thread.sleep(50);}catch(InterruptedException ex){;}
      //soundPlayer.setMute(isMute);
  }

  public void playOnce(String str) {
    oneShot=true;
    ampFunc=str;
    boolean isMute=mute;
    //soundPlayer.mute=true;
    soundPlayer.closeStream();
    soundData = new SoundData(numPts);
    funcField.setText(str);
    soundData.newAmpFunc(ampFunc);
    soundPlayer = new SoundPlayer(numPts,oneShot);
    calc();
    //try{Thread.sleep(50);}catch(InterruptedException e){;}
    //soundPlayer.setMute(isMute);
  }

  public void playLoop(String str) {
    oneShot=false;
    ampFunc=str;
    boolean isMute=mute;
    //soundPlayer.mute=true;
    soundPlayer.closeStream();
    //soundData = new SoundData(numPts);
    funcField.setText(str);
    soundData.newAmpFunc(ampFunc);
    soundPlayer = new SoundPlayer(numPts,oneShot);
    calc();
    //try{Thread.sleep(50);}catch(InterruptedException e){;}
    //soundPlayer.setMute(isMute);
  }

  public void actionPerformed(ActionEvent evt) {
    double yVec[] = soundData.getAmpVec();
    sonogramDetail.setYVec(yVec,sonogram.getC1(),sonogram.getC2());
  }
}

class SoundOut_addBtn_actionAdapter implements java.awt.event.ActionListener{
  SoundOut adaptee;

  SoundOut_addBtn_actionAdapter(SoundOut adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.addBtn_actionPerformed(e);
  }
}

class SoundOut_splitPanel1_actionAdapter implements java.awt.event.ActionListener {
  SoundOut adaptee;

  SoundOut_splitPanel1_actionAdapter(SoundOut adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.splitPanel1_actionPerformed(e);
  }
}

class SoundOut_sndCKBox_itemAdapter implements java.awt.event.ItemListener{
  SoundOut adaptee;

  SoundOut_sndCKBox_itemAdapter(SoundOut adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.sndCKBox_itemStateChanged(e);
  }
}

class SoundOut_volSlider_adjustmentAdapter implements java.awt.event.AdjustmentListener{
  SoundOut adaptee;

  SoundOut_volSlider_adjustmentAdapter(SoundOut adaptee) {
    this.adaptee = adaptee;
  }

  public void adjustmentValueChanged(AdjustmentEvent e) {
    adaptee.volSlider_adjustmentValueChanged(e);
  }
}

class SoundOut_clearBtn_actionAdapter implements java.awt.event.ActionListener {
  SoundOut adaptee;

  SoundOut_clearBtn_actionAdapter(SoundOut adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.clearBtn_actionPerformed(e);
  }
}

