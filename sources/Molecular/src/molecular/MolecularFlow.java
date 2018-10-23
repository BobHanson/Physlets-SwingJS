/*
**************************************************************************
**
**                      Class  MolecularFlow
**
**************************************************************************
**
** class MolecularFlow extends SApplet
**
** The main entry point and MolecularFlow applet.
**
** @author Jim Nolen and Wolfgang Christian
**
*************************************************************************/

package molecular;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import edu.davidson.tools.*;



public class MolecularFlow extends SApplet {
  String button_reset="Reset";
  String button_start="Start";
  String button_stop="Pause";
  String button_forward="Step";
  String label_newparticles="New Particles:";

  boolean sc, perh,partInt, per, perv;
  int inp, maxp, fps, ppu, mode;
  String parseString=null;
  boolean bill=false;
  double dt=0.1;
  double pm=100;
  MFLEnsemble ensemble1=null;
  MFREnsemble ensemble2=null;
  MFEnsemble ensemble3=null;
  MFEnsemblePanel ensemblePanel= new MFEnsemblePanel(this);
  edu.davidson.graphics.EtchedBorder controlPanel = new edu.davidson.graphics.EtchedBorder();
  edu.davidson.graphics.EtchedBorder etchedBorder3 = new edu.davidson.graphics.EtchedBorder();
  Button startbtn = new Button();
  Button resetbtn = new Button();
  Button stepbtn = new Button();
  Panel panel1 = new Panel();
  Label label1 = new Label();
  edu.davidson.display.SInteger partfield = new edu.davidson.display.SInteger();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();


  //Construct the applet

  public MolecularFlow() {
  }

  protected void setResources(){
    button_start=localProperties.getProperty("button.start",button_start);
    button_stop=localProperties.getProperty("button.stop",button_stop);
    button_reset=localProperties.getProperty("button.reset",button_reset);
    button_forward=localProperties.getProperty("button.forward",button_forward);
    label_newparticles=localProperties.getProperty("label.newparticles",label_newparticles);
  }

  public void init() {
    initResources(null);
    ensemble1=ensemblePanel.getEnsemble1();
    ensemble2=ensemblePanel.getEnsemble2();
    ensemble3=ensemblePanel.getEnsemble3();
    try { sc = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { inp = Integer.parseInt(this.getParameter("InitialP", "10")); } catch (Exception e) { e.printStackTrace(); }
    try { fps = Integer.parseInt(this.getParameter("FPS", "50")); } catch (Exception e) { e.printStackTrace(); }
    try { ppu = Integer.parseInt(this.getParameter("PixPer", "10")); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { perv = Boolean.valueOf(this.getParameter("PeriodicV", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { partInt = Boolean.valueOf(this.getParameter("PartInt", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try { maxp = Integer.parseInt(this.getParameter("MaxParticles", "100")); } catch (Exception e) { e.printStackTrace(); }
    try { parseString = String.valueOf(this.getParameter("Probability", "0.05")); } catch (Exception e) { e.printStackTrace(); }
    try {
    jbInit();
    }
    catch (Exception e) {
    e.printStackTrace();
    }
  //clock.addClockListener(ensemble2);
  //clock.addClockListener(ensemble2);
  clock.addClockListener(ensemblePanel);
  setMaxParticles(maxp);
  setPartInteractions(partInt);
  //setProbabilityFunction(parseString);
  setFPS(fps);
  setDt(dt);
  setPpu(ppu);
  setShowControls(sc);
  setPeriodicV(false);
  setDefaultSize(1);
  setRunningID();
  setBackgroundRGBR(190,190,190);
  setBackgroundRGBL(190,190,190);
  setBackgroundRGBM(190,190,190);
  setBackground(Color.gray);
  partfield.setValue(inp);
  //setWallTemp("bottom",100);
  this.ensemblePanel.ensemble1.setEnableMouse(true);   // added by W. Christian to enable mouse.
  this.ensemblePanel.ensemble2.setEnableMouse(true);   // added by W. Christian to enable mouse.
  this.ensemblePanel.ensemble3.setEnableMouse(true);
  }


  private void jbInit() throws Exception {
    startbtn.setLabel(button_start);
    startbtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        startbtn_actionPerformed(e);
      }
    });
    resetbtn.setLabel(button_reset);
    stepbtn.setLabel(button_forward);
    resetbtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        resetbtn_actionPerformed(e);
      }
    });
    panel1.setLayout(borderLayout1);
    label1.setAlignment(1);
    label1.setText(label_newparticles);
    partfield.addTextListener(new java.awt.event.TextListener() {
      public void textValueChanged(TextEvent e) {
        partfield_textValueChanged(e);
      }
    });
    this.setSize(new Dimension(392, 256));
    ensemblePanel.setLayout(borderLayout4);
    stepbtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stepbtn_actionPerformed(e);
      }
    });
    etchedBorder3.setLayout(gridLayout1);
    controlPanel.setLayout(borderLayout2);
    this.setLayout(borderLayout3);
    this.add(ensemblePanel, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(etchedBorder3, BorderLayout.CENTER);
    etchedBorder3.add(startbtn, null);
    etchedBorder3.add(resetbtn, null);
    etchedBorder3.add(stepbtn, null);
    controlPanel.add(panel1, BorderLayout.WEST);
    panel1.add(label1, BorderLayout.CENTER);
    panel1.add(partfield, BorderLayout.EAST);
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
      {"InitialP", "int", "number of particles"},
      {"Mode", "String", ""},
      {"Frames","double", "frames per second"},
      {"PixPer","int", "pixels per unit"},
      {"Delta","double","time step"},
      //{"Periodic","boolean","periodic boundaries"}
    };
    return pinfo;
  }

  /**
   * Counts the number of applets on a page.
   *
   * @param func
   * @param vars
   *
   * @return
   */
  public int getAppletCount() {
    if(firstTime) return 0;
    else return super.getAppletCount();
  }

  public void start(){
     ensemblePanel.osi=null;
     if (firstTime) {
         firstTime=false;
    }
    repaint();
    super.start();
  }

    /**
  *
  * Set the temperature of the ensemble in one step.
  *
  * @param temp double  The new temperature.
  *
  */
  public void setTempL(double temp){
    ensemble1.setTemp(temp);
  }

  /**
  *
  * Set the temperature of the ensemble in n steps. Data will be sent to data listeners after each step.
  *
  * @param temp double  The new temperature.
  * @param steps int    The number of steps.
  *
  */
  public void setTemperatureL(double temp, int steps){
    ensemble1.setTemperature(temp, steps);
  }
    /**
  *
  * Set the temperature of the ensemble in one step.
  *
  * @param temp double  The new temperature.
  *
  */
  public void setTempR(double temp){
    ensemble2.setTemp(temp);
  }

  /**
  *
  * Set the temperature of the right ensemble in n steps. Data will be sent to data listeners after each step.
  *
  * @param temp double  The new temperature.
  * @param steps int    The number of steps.
  *
  */
  public void setTemperatureR(double temp, int steps){
    ensemble2.setTemperature(temp, steps);
  }

  /**
  *
  * Shows or hides control panel
  *
  * @param s boolean
  *
  */
  public void setShowControls(boolean s){
    controlPanel.setVisible(s);
    this.invalidate();
    this.validate();
  }

  /**
  *
  * Adds a message to small yellow box opposite coordinate display.
  *
  *
  * @param msg String
  */
  public void setMessage(String msg){
      ensemble2.setMessage(msg);
  }

  /**
  *
  * Sets offsets for title
  *
  * @param int xoff
  * @param int yoff
  */
  public void setDisplayOffset(int xoff,int yoff){
    if(xoff>=0)
      ensemblePanel.txtxoff=xoff;
    if(yoff>=0)
      ensemblePanel.txtyoff=yoff;
  }

   /**
  *
  * Sets color of title
  *
  * @param r int red
  * @param g int green
  * @param b int blue
  */
  public void setRGB(int r, int g, int b){
      ensemblePanel.txtcolor=new Color(r,g,b);
  }

  /**
  *
  * Adds a title to ensemble in top-center
  *
  * @param cap String
  */
  public void addCaption(String cap){
      ensemblePanel.addCaption(cap);
  }




  void startbtn_actionPerformed(ActionEvent e) {
      if (!clock.isRunning())  {
          setDefault();
          setPartInteractions(true);
          setAutoRefresh(false);
          setTubeWidth(15);
          setTubeLength(150);
          setLeftWidth(60,false);
          setInflowLeft(2);
          setOFProbRight(1);
          setEnableFlow(true);
			    setDefaultMass(1);
          setDefaultSize(0.2);
          setDefaultColorL(150,0,0);
          createParticlesL(5);
			    setDefaultColorR(0,150,0);
          createParticlesR(5);
          setAutoRefresh(true);
          forward();
}
      else pause();
  }

  /*
  *
  * Reset to time t=0;
  *
  *

  public void resetClock(){
      pause();
      ensemblePanel.reset();

  }
  */

  public void setDefault(){
    clock.stopClock();
    clock.setTime(0);
    ensemblePanel.setDefault();
    setPartInteractions(partInt);
    setDefaultMass(1);
    setDefaultSize(0.5);
    setDefaultColorL(50,0,170);
     setDefaultColorR(50,0,170);
    setTubeWidth(20);
    setTubeLength(70);
    setLeftWidth(100,false);
    ensemble2.showMessage=false;
    ensemblePanel.showTitle=false;
  }

  /**
  *
  * Turns particle-to-particle interations on and off
  *
  *
  * @param tf boolean
  */
  public void setPartInteractions(boolean tf){
    ensemblePanel.setPartInteractions(tf);
  }

  /**
  *
  * Method sets width of diffusion tube in units
  * Default is 20
  *
  * @param w double
  */
  public void setTubeWidth(int w){
    ensemblePanel.setTubeWidth(w);
  }

  /**
  *
  * Method sets length of diffusion tube in pixles
  * Default is 70
  *
  * @param l double
  */
  public void setTubeLength(int l){
    ensemblePanel.setTubeLength(l);
  }

   /**
  *
  * Method sets length of diffusion tube in pixels
  * Default is 100
  *
  * @param l double
  * @param tf boolean     if true, then feature is activated
  * if false, then ensembles will be symmetric
  */
  public void setLeftWidth(int w, boolean tf){
    ensemblePanel.setLeftWidth(w,tf);
  }

  public void setEnableFlow(boolean tf){
      ensemblePanel.setEnableFlow(tf);
  }

  /**
  *
  * Method sets rate at which particles flow into left ensemble
  *
  * @param rate double
  */
  public void setInflowLeft(double rate){
      ensemblePanel.setInflowLeft(rate);
  }
  /**
  *
  * Method sets rate at which particles flow into right ensemble
  *
  * @param rate double
  */
  public void setInflowRight(double rate){
      ensemblePanel.setInflowRight(rate);
  }
  /**
  *
  * Method sets rate at which particles flow out of right ensemble
  *
  * @param rate double
  */
  public void setOutflowRight(double rate){
      ensemblePanel.setOutflowRight(rate);
  }
  /**
  *
  * Method sets rate at which particles flow out of left ensemble
  *
  * @param rate double
  */
  public void setOutflowLeft(double rate){
      ensemblePanel.setOutflowLeft(rate);
  }
  /**
  *
  * Method sets probability of particle flow out of left ensemble
  *
  * @param prob double
  */
  public void setOFProbLeft(double prob){
      ensemblePanel.setOFProbLeft(prob);
  }
  /**
  *
  * Method sets probability of particle flow out of right ensemble
  *
  * @param prob double
  */
  public void setOFProbRight(double prob){
      ensemblePanel.setOFProbRight(prob);
  }

  /**
  *
  * Stop the animation
  *
  *
  */
  public void pause(){
      clock.stopClock();
      startbtn.setLabel(button_start);
  }

  /**
  *
  * Start the animation
  *
  *
  */
  public void forward(){
     setRunningID();
     //ensemble1.preRun();
     startbtn.setLabel(button_stop);
     if (clock.isRunning()){pause();}
     clock.startClock();
  }

  void partfield_textValueChanged(TextEvent e) {
    pause();
    inp=partfield.getValue();
  }


  void resetbtn_actionPerformed(ActionEvent e) {
    ensemblePanel.setDefault();
  }


  void stepbtn_actionPerformed(ActionEvent e) {
    stepForward();
  }
  /**
  *
  * Steps simulation forward one time step
  *
  *
  *
  */
  public void stepForward(){
    clock.doStep();
  }

  /**
  *
  * Adds one particle to the ensemble
  *
  * @param i int particle number
  * @param xin double initial xposition
  * @param yin double initial yposition
  * @param xvel double initial x-velocity
  * @param yvel double initial y-velocity
  * @param r double  particle radius
  *
  */
  /*public void setParticle(int i, double xin, double yin, double xvel, double yvel, double r){
    ensemble1.setParticle(i,xin, yin,xvel,yvel,r);
  }
  */

  /**
  *
  * Adds one particle to the left ensemble
  *
  * @param xin double initial xposition
  * @param yin double initial yposition
  * @param xvel double initial x-velocity
  * @param yvel double initial y-velocity
  * @param r double  particle radius
  * @returns int new particle number
  */
  public int addParticleL(double xin, double yin, double xvel, double yvel, double r){
      return ensemble1.addParticle(xin, yin, xvel, yvel, r);
  }

  /**
  *
  * Adds one particle to the right ensemble
  *
  * @param xin double initial xposition
  * @param yin double initial yposition
  * @param xvel double initial x-velocity
  * @param yvel double initial y-velocity
  * @param r double  particle radius
  * @returns int new particle number
  */
  public int addParticleR(double xin, double yin, double xvel, double yvel, double r){
      return ensemble2.addParticle(xin, yin, xvel, yvel, r);
  }

  /**
  *
  * Adds one particle to the middle ensemble
  *
  * @param xin double initial xposition
  * @param yin double initial yposition
  * @param xvel double initial x-velocity
  * @param yvel double initial y-velocity
  * @param r double  particle radius
  * @returns int new particle number
  */
  public int addParticleM(double xin, double yin, double xvel, double yvel, double r){
      return ensemble3.addParticle(xin, yin, xvel, yvel, r);
  }


  public void createParticlesL(int n){
    boolean runAgain=false;
    if (clock.isRunning()){runAgain=true; pause();}
    ensemble1.createParticles(n);
    if (runAgain)  {runAgain=false; forward(); }
  }

  public void createParticlesR(int n){
    boolean runAgain=false;
    if (clock.isRunning()){runAgain=true; pause();}
    ensemble2.createParticles(n);
    if (runAgain)  {runAgain=false; forward(); }
  }

  public void createParticlesM(int n){
    boolean runAgain=false;
    if (clock.isRunning()){runAgain=true; pause();}
    ensemble3.createParticles(n);
    if (runAgain)  {runAgain=false; forward(); }
  }

  /**
  *
  * Removes ith particle from ensemble
  *
  * @param i int particle number
  *
  */

  public void removeParticle(int i){
    ensemble1.removeParticle(i);
  }

  public void setParticlesL(int n){
    ensemble1.setParticles(n);
  }

  public void setParticlesR(int n){
    ensemble2.setParticles(n);
  }

  public void setParticlesM(int n){
    ensemble3.setParticles(n);
  }
    /**
  *
  * Sets maximum number of particles that can be stored in arrays of each ensemble
  *
  * @param p max particles
  *
  */
  public void setMaxParticles(int p){ensemble3.setMaxParticles(p);ensemble1.setMaxParticles(p);ensemble2.setMaxParticles(p);}


  /*
  *
  * Sets mass of piston between two ensembles
  *
  * @param m double default is 100.
  *

  public void setPistonMass(double m){
    ensemblePanel.setPistonMass(m);
  }

  */

  /**
  *
  * Sets Background color inside left ensemble
  *
  * @param r int red
  * @param g int green
  * @param b int blue
  *
  */
  public void setBackgroundRGBL(int r,int g, int b){ensemble1.setBackgroundRGB(r,g,b);}

  /**
  *
  * Sets Background color inside middle ensemble
  *
  * @param r int red
  * @param g int green
  * @param b int blue
  *
  */
  public void setBackgroundRGBM(int r,int g, int b){ensemble3.setBackgroundRGB(r,g,b);}


  /**
  *
  * Sets Background color inside right ensemble
  *
  * @param r int red
  * @param g int green
  * @param b int blue
  *
  */
  public void setBackgroundRGBR(int r,int g, int b){ensemble2.setBackgroundRGB(r,g,b);}
  /**
  *
  * Sets default mass when adding new particles.  All particles created after
  * calling this method will have this mass
  *
  * @param m double mass
  *
  */
  public void setDefaultMass(double m){ensemble3.setDefaultMass(m);ensemble1.setDefaultMass(m);ensemble2.setDefaultMass(m);}
  /**
  *
  * Sets default size when adding new particles.  All particles created after
  * calling this method will have this radius
  *
  * @param r double radius
  *
  */
  public void setDefaultSize(double r){ensemble3.setDefaultSize(r);ensemble1.setDefaultSize(r);ensemble2.setDefaultSize(r);}

   /**
  *
  * Sets default color when adding new particles to LEFT Ensemble.  All particles created after
  * calling this method will have this color
  *
  * @param r int red
  * @param g int green
  * @param b int blue
  *
  */
  public void setDefaultColorL(int r,int g, int b){ensemble3.setDefaultColor(r,g,b);ensemble1.setDefaultColor(r,g,b);}

   /**
  *
  * Sets default color when adding new particles to RIGHT Ensemble.  All particles created after
  * calling this method will have this color
  *
  * @param r int red
  * @param g int green
  * @param b int blue
  *
  */
  public void setDefaultColorR(int r,int g, int b){ensemble2.setDefaultColor(r,g,b);}





  public void setDefaultTemp(double t){ensemble3.setDefaultTemp(t);ensemble1.setDefaultTemp(t);ensemble2.setDefaultTemp(t);}
  /**
  *
  * Sets color of particles in left ensemble
  *
  * @param i int particle number
  * @param rd int red value   (<256)
  * @param gr int green value    (<256)
  * @param bl int blue value    (<256)
  *
  */
  public void setParticleRGBR(int i, int rd, int gr, int bl){
    ensemble2.setParticleRGB(i,rd,gr,bl);
  }
  /**
  *
  * Sets color of particles in Right ensemble
  *
  * @param i int particle number
  * @param rd int red value   (<256)
  * @param gr int green value    (<256)
  * @param bl int blue value    (<256)
  *
  */
  public void setParticleRGBL(int i, int rd, int gr, int bl){
    ensemble1.setParticleRGB(i,rd,gr,bl);
  }

  /**
  *
  * Sets mass of a particle in the left ensemble
  * Default mass is 1.
  *
  * @param i int particle number
  * @param m double mass.
  *
  */
  public void setParticleMassL(int i, double m){
    ensemble1.setParticleMass(i,m);
  }

  /**
  *
  * Sets mass of a particle in the right ensemble
  * Default mass is 1.
  *
  * @param i int particle number
  * @param m double mass.
  *
  */
  public void setParticleMassR(int i, double m){
    ensemble2.setParticleMass(i,m);
  }
   /**
  *
  * Sets mass of a particle in the middle ensemble
  * Default mass is 1.
  *
  * @param i int particle number
  * @param m double mass.
  *
  */
  public void setParticleMassM(int i, double m){
    ensemble3.setParticleMass(i,m);
  }



  /**
  *
  * Sets Position of a particle in the left ensemble
  *
  * @param i int particle number
  * @param xin double initial x-position
  * @param yin double initial y-position
  *
  */
  public void setParticlePosL(int i, double xin, double yin){
    ensemble1.setParticlePos(i,xin,yin);
  }

  /**
  *
  * Sets Position of a particle in the right ensemble
  *
  * @param i int particle number
  * @param xin double initial x-position
  * @param yin double initial y-position
  *
  */
  public void setParticlePosR(int i, double xin, double yin){
    ensemble2.setParticlePos(i,xin,yin);
  }

  /**
  *
  * Sets Velocity of a particle in the left ensemble.
  *
  * @param i int particle number
  * @param xvel double initial x-velocity
  * @param yvel double initial y-velocity
  *
  */
  public void setParticleVelL(int i, double xvel, double yvel){
    ensemble1.setParticleVel(i,xvel,yvel);
  }

  /**
  *
  * Sets Velocity of a particle in the right ensemble.
  *
  * @param i int particle number
  * @param xvel double initial x-velocity
  * @param yvel double initial y-velocity
  *
  */
  public void setParticleVelR(int i, double xvel, double yvel){
    ensemble2.setParticleVel(i,xvel,yvel);
  }

  /**
  *
  * Sets size of a particle in the left ensemble.
  * Default size is 1 in world units.
  *
  * @param i int particle number
  * @param s double size.
  *
  */
  public void setParticleSizeL(int i, double s){
    ensemble1.setParticleSize(i,s);
  }

  /**
  *
  * Sets size of a particle in the left ensemble.
  * Default size is 1 in world units.
  *
  * @param i int particle number
  * @param s double size.
  *
  */
  public void setParticleSizeR(int i, double s){
    ensemble2.setParticleSize(i,s);
  }

  /**
  *
  * Make a particle in the left ensemble into a data source.
  *
  * @param i int particle number
  * @param s double size.
  *
  */
  public int addParticleDataSourceL(int i){
    return ensemble1.addParticleDataSource(i);
  }

  /**
  *
  * Make a particle in the right ensemble into a data source.
  *
  * @param i int particle number
  * @param s double size.
  *
  */
  public int addParticleDataSourceR(int i){
    return ensemble2.addParticleDataSource(i);
  }

  /**
  *
  * Make a particle in the right ensemble into a data source.
  *
  * @param i int particle number
  * @param s double size.
  *
  */
  public int addParticleDataSourceM(int i){
    return ensemble3.addParticleDataSource(i);
  }


  /**
  *
  * Sets frames per second of animation
  *
  * @param f int
  *
  */
  public void setFPS(int f){
    clock.setFPS(f);

    // set clock delay time.
  }
   public void setAutoRefresh(boolean ar){ensemble1.setAutoRefresh(ar);ensemble2.setAutoRefresh(ar);}

  /**
  *
  * Sets calculation time step
  *
  * @param dt double  timestep
  *
  */
  public void setDt(double dt){
     clock.setTimeStep(dt);
  }

  public void setBoltzmann(double kb){
    ensemble1.setBoltzmann(kb);
    ensemble2.setBoltzmann(kb);
  }

  /**
  *
  * Sets wall temperature for walls in right ensemble
  *
  *
  *
  */
  public void setWallTempR(String wall, double t ){
    wall.toLowerCase();
    wall.trim();
    if (wall.equals("top")||wall.equals("north")) ensemble2.setWallTemp(0,t);
    else if (wall.equals("right")||wall.equals("east")) ensemble2.setWallTemp(1,t);
    else if (wall.equals("bottom")||wall.equals("south")) ensemble2.setWallTemp(2,t);
  }
  /**
  *
  * Sets wall temperature for walls in left ensemble
  *
  *
  *
  */

  public void setWallTempL(String wall, double t ){
    wall.toLowerCase();
    wall.trim();
    if (wall.equals("top")||wall.equals("north")) ensemble1.setWallTemp(0,t);
    else if (wall.equals("bottom")||wall.equals("south")) ensemble1.setWallTemp(2,t);
    else if (wall.equals("left")||wall.equals("west")) ensemble1.setWallTemp(3,t);
  }

  public void removeWallTempL(String wall){
    if (wall.equals("top")||wall.equals("north")) ensemble1.removeWallTemp(0);
    else if (wall.equals("right")||wall.equals("east")) ensemble1.removeWallTemp(1);
    else if (wall.equals("bottom")||wall.equals("south")) ensemble1.removeWallTemp(2);
    else if (wall.equals("left")||wall.equals("west")) ensemble1.removeWallTemp(3);
  }

  public void removeWallTempR(String wall){
    if (wall.equals("top")||wall.equals("north")) ensemble2.removeWallTemp(0);
    else if (wall.equals("right")||wall.equals("east")) ensemble2.removeWallTemp(1);
    else if (wall.equals("bottom")||wall.equals("south")) ensemble2.removeWallTemp(2);
    else if (wall.equals("left")||wall.equals("west")) ensemble2.removeWallTemp(3);
  }

  /**
  *
  * Sets pixels per unit of ensemble
  *
  * @param p int
  *
  */
  public void setPpu(int p){
    ensemble1.setPpu(ppu);
    ensemble2.setPpu(ppu);
    ensemblePanel.ppu=p;
  }


    /**
  *
  * Sets Periodic periodic boundary conditions ion vertical direction
  *
  * @param p boolean
  *
  */
  public void setPeriodicV(boolean p){
     pause();
     ensemble1.setPeriodicV(p);
     ensemble2.setPeriodicV(p);
  }

  /**
  *
  * Sets initial number of particles
  *
  * @param i int
  *
  */
  /*public void setInp(int i){
    partfield.setValue(i);
    ensemble1.setInp(i);
    pause();
  }
  */
  /**
  *
  * Initializes and fills all arrays
  * must be done when mode is set or something else changes
  *
  *
  *
  */
 /* public void setUp(){
    pause();
    ensemble1.setup();
  }*/


  /**
  *
  * Returns the right wall position
  *
  * @return double
  *
  */
  public double getRWPos(){
    return ensemble2.getRWPos();
  }
  /**
  *
  * Returns the left wall position
  *
  * @return double
  *
  */
  public double getLWPos(){
    return ensemble1.getLWPos();
  }
  /**
  *
  * Returns the top wall position
  *
  * @return double
  *
  */
  public double getTWPos(){
    return ensemble1.getTWPos();
  }
  /**
  *
  * Returns the bottom wall position
  *
  * @return double
  *
  */
  public double getBWPos(){
    return ensemble1.getBWPos();
  }

  /**
  *
  * Returns the id of the left ensemble.   This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getEnsembleLeftID(){return ensemble1.hashCode();}

  /**
  *
  * Returns the id of theright ensemble.   This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getEnsembleRightID(){return ensemble2.hashCode();}

  /**
  *
  * Returns the id of the middle ensemble.   This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getEnsembleMiddleID(){return ensemble3.hashCode();}


    /**
  *
  * Returns the id of the velocity histogram of the left ensemble. This id can be used to make data connections.
  *
  * @param nbins int  The number of bins in the histogram.
  * @param min   double   The minimum velocity
  * @param max   double   The maximum velocity
  *
  * @return int  The id.
  *
  */
  public int getHistogramLeftID(int nbins, double vmin, double vmax){
     return ensemble1.setHistogram(  nbins,  vmin,  vmax);
  }

  /**
  *
  * Returns the id of the velocity histogram of the right ensemble. This id can be used to make data connections.
  *
  * @param nbins int  The number of bins in the histogram.
  * @param min   double   The minimum velocity
  * @param max   double   The maximum velocity
  *
  * @return int  The id.
  *
  */
  public int getHistogramRightID(int nbins, double vmin, double vmax){
     return ensemble2.setHistogram(  nbins,  vmin,  vmax);
  }

  /**
  *
  * Returns the id of the velocity histogram of the middle ensemble. This id can be used to make data connections.
  *
  * @param nbins int  The number of bins in the histogram.
  * @param min   double   The minimum velocity
  * @param max   double   The maximum velocity
  *
  * @return int  The id.
  *
  */
  public int getHistogramMiddleID(int nbins, double vmin, double vmax){
     return ensemble3.setHistogram(  nbins,  vmin,  vmax);
  }



}

