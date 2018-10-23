////////////////////////////////////////////////////////////////////////////////
//	SpectrumPanel.java
//	Authors: Jim Nolen  and Wolfgang Christian

package spectrum;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.*;

import java.awt.event.*;
import java.util.Vector;
import java.util.Enumeration;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import edu.davidson.display.Format;


/**
 * A SpectrumPanel is the drawing medium for the SpectrumApplet.  It's primary function is
 * to create a color palette corresponding to a range of wavelengths and to paint emission or absorption lines and
 * bands at their proper location.  The "lines" and "bands" are represented by separate objects (instances of ALine.class, etc.) and
 * are stored in a vector called thingVector.
 *
 * Red, blue, and green color values for each vertical pixel line on the panel are stored
 * in arrays called red[], blue[], green[], respectively.  Mouse action can interact with the panel
 * to display wavelength positions and drag spectral lines.
 *
 * ALL WAVELENGTH VALUES AND LAMBDA VARIABLES ARE IN NANOMETERS!!
 *
 */

public class SpectrumPanel extends SPanel implements SDataSource, edu.davidson.display.SScalable{
SApplet owner = null;
static double rh = 1.0973732E-2; //Rydberg constant in nm-1
static double clight = 0.2998;   //speed of light in nm/s
char lamChar = '\u0022';
//int halfWidth = 150;

//thingVector contains all emission and absorption objects of type Thing
Vector textVector = new Vector (20);
Vector thingVector = new Vector(125);
Vector  emissionDataSources=new Vector();
Format format = new Format("%7.3f");
int unit = 0;   //wavelength units  0 = nm, 1 = cm.
double red[] = null;       //color arrays will be initiallized from setBounds() method
double blue[] = null;
double green[] = null;
double intensity[] = null;
double saturation[] = null;
int currentWidth = 0;   //current pixel width of spectrum
int currentHeight = 0;   //current pixel height of spectrum
int wallW=0;
double lbound = 400;  //upper wavelength bound
double ubound = 650;  //upper wavelength bound
Image osi = null;     //off-screen image
boolean falseColor = true; //controls display of IR and UV spectrum with false color
boolean trueColor=true;   //turns black-white and color modes on and off.
boolean autoRefresh = true;
SpectrumThing dragThing = null;
double lamPress = 0;
double dopplerV=0;
double beta=0;
//data source variables
String[] varStrings = {"lambda","intensity"};
double[][] vars = new double[1][2];   // wavelength, intensity
boolean showMessage =false;
String message;
int mouseX=0;
boolean mouseDown=false;

//constructor
  public SpectrumPanel(){
  }

  /**
  *
  * Constructor method.
  *
  * @param o SApplet instance of spectrumApplet that owns this instance of SpectrumPanel
  */
  public SpectrumPanel(SApplet o) {
    this();
    owner = o;
    try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
  *
  * Method controls main action of spectrumPanel.
  * Can be called and should be called from script after adding a
  * set of absorption or emission lines.
  *
  */
  public void runSpectrum(){
    setBounds();
    fillMatrices();

    repaint();
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
    autoRefresh=ar;
    if (ar){
      runSpectrum();
    }
  }

  /**
  *
  * Method auto-scales spectrum to fit all lines in view
  * Scales doppler shifted lines automatically, as well.
  *
  *
  */
  public void autoScale(){
       if (thingVector.isEmpty()) return;
       double lam;
       double w;
       double maxLam=0;
       double maxwidth=0,minwidth=100;
       double minLam=100000000;
       SpectrumThing t=null;
       for( Enumeration e=thingVector.elements(); e.hasMoreElements();){
         t= (SpectrumThing) e.nextElement();
         if (t.doppler) {
            lam=t.dopplerShiftLine(t.lambda,beta);
            w=t.dopplerShiftLine(t.lineWidth,beta);
         }
         else {lam=t.lambda; w=t.lineWidth;}
         if ((lam+w/2)>maxLam) {maxLam=lam+w/2;maxwidth=w;}
         if ((lam-w/2)<minLam) {minLam=lam-w/2;minwidth=w;}
       }
       if (unit==1){
          minLam=nmToCm(minLam);
          maxLam=nmToCm(maxLam);
          minwidth=nmToCm(minwidth);
          maxwidth=nmToCm(maxwidth);
       }
       setScale(minLam-4*minwidth,maxLam+4*maxwidth);
  }

  /**
  *
  * Method repaints spectrum with any lines or bands to be found in thingVector.
  *
  */
  public void repaint(){
    paintOSI();
    Graphics g = this.getGraphics();
    if(g==null) return;
    paint(g);
    g.dispose();
    if (owner!=null) {
        owner.clearData(this.hashCode());   // added by W. Christian to clear the pressure data.
        owner.updateDataConnections();
    }
  }

  /**
  *
  * Method updates the width of the panel to the actual pixel width on
  * the screen. Method also establishes the color arrays according to the  width of the
  * panel. The off-screen image (osi) is created here.
  *
  *
  *
  */
  public void setBounds(){
     currentWidth = this.getSize().width;
     currentHeight = this.getSize().height;
     initializeMatrices();
     if(currentWidth<2 || currentHeight<2) {
         osi=null;
         return;
      }
     osi = createImage(currentWidth,currentHeight);
  }

  /**
  * Method returns lower wavelength bound of spectrum
  *
  *
  * @return double wavelength
  */
  public double getLBound(){
      return lbound;
  }

  /**
  * Method returns upper wavelength bound of spectrum
  *
  *
  * @return double wavelength
  */
  public double getUBound(){
      return ubound;
  }



  /**
  *
  * Color matrices and intensity matrices initialized here.
  *
  *
  */
  public void initializeMatrices(){
     red= new double[currentWidth];
     green = new double[currentWidth];
     blue = new double[currentWidth];
     intensity = new double[currentWidth];
     vars = new double[currentWidth][2];
     //saturation = new double[halfWidth*2];
  }

  /**
  *
  * Color and Intensity Matrices are filled.
  *
  *
  */
  public void fillMatrices(){
    if (falseColor) makePalette();
    else makePalette2();
    for(int i=0; i<currentWidth; i++){
       intensity[i]=0;    //intensity =0 corresponds to black
    }
  }



  /**
  *
  * Method uses the intensity at each pixel position to determine the intensity of
  * the emission or absorption at that point. If an emission line is present at
  * a particular position, the red[], green[], and blue[] arrays are not changed--
  * only the intensity. Intensity of +1 is full emission; intensity of -1 will cancel a full emission.
  *
  * Actual Painting is performed on off-screen image OSI.
  *
  */
  void paintOSI(){
    //if (osi==null) {setBounds(); return;}
    if (osi==null || currentWidth != this.getBounds().width || currentHeight != this.getBounds().height) {setBounds();}
    if(osi==null) return;
    Graphics osg = osi.getGraphics();
    osg.setColor(Color.black);
    osg.fillRect(0,0,currentWidth,currentHeight);
    paintThings(osg);
    paintText(osg);
    if(dragThing!=null && mouseDown) paintLambda( osg, mouseX);
    osg.dispose();
  }

  /**
  *
  * Off screen image OSI is drawn on screen.
  *
  */
  public void paint(Graphics g){
     if (osi==null) return;
     else  g.drawImage(osi,0,0,currentWidth,currentHeight,this);
  }


  /**
  *
  * Method acquires the total intensity for a particular pixel position.
  * The intensity of all emission or absorption at a particualr position
  * is added together to form a total intensity for that position.
  *
  * Emission or absorption lines are stored in thingVector.
  */
  /*void paintThings(){
    Thing t;
    double lam,num;
    for (int j=0; j<currentWidth; j++) intensity[j]=0;
    for (Enumeration e=thingVector.elements(); e.hasMoreElements();){
      t = (Thing)e.nextElement();
      for(int i=0; i<currentWidth; i++){
        lam = lbound + i*(ubound-lbound)/currentWidth;
        num=t.paintLambda(lam);
        intensity[i]+=num;
      }
    }
  }*/

  void paintThings(Graphics g){
    SpectrumThing t;
    double lam,num;
    for (int j=0; j<currentWidth; j++) intensity[j]=0;
    for (Enumeration e=thingVector.elements(); e.hasMoreElements();){
      t = (SpectrumThing)e.nextElement();
      t.paintOS(g);
    }
  }


  void paintText(Graphics g){
     if (showMessage) paintMessage(g);
     TextThing t = null;
     for (Enumeration e=textVector.elements(); e.hasMoreElements();){
       t = (TextThing)e.nextElement();
       t.paintOS(g);
     }

  }


  /*
  //This version of makePalette() adapted from BlackBody Applet by Mike Lee and Wolfgang Christian
  public void makePalette(){
    int w=halfWidth*2;
    for(int i=0; i<w; i++){
       double arg=1.4*(i-halfWidth)/halfWidth;
       saturation[i]=(255*Math.exp(-arg*arg));
    }
    for(int i=0; i<currentWidth; i++){
       //lam is wavelength (nm) corresponding to pixel position i
       double lam = lbound+(i*(ubound-lbound)/(double)currentWidth);
       if(lam>400-halfWidth && lam<400+halfWidth){
          blue[i]=saturation[(int)(lam-400+halfWidth)];
       }
       if(lam>500-halfWidth && lam<500+halfWidth){
         green[i]=saturation[(int)(lam-500+halfWidth)];
       }
      if(lam>600-halfWidth && lam<600+halfWidth){
          red[i]=saturation[(int)(lam-600+halfWidth)];
       }
    }
  }
  */

  /**
  *
  * Method assigns color values to each wavelength position. Color arrays containing
  * a value for each pixel across the width of the screen are filled.
  *
  * If trueColor is set to false, gray lines will be displayed.
  * If falseColor is set to true, emission or absorption in the Infrared and UV regions
  * will be displayed in pink and lavender, respectively.
  *
  * makePalette() is called when falseColor=true;
  */
  void makePalette(){
    for(int i=0; i<currentWidth; i++){

       //lam is wavelength (nm) corresponding to pixel position i

       double lam = lbound+((double)i*(ubound-lbound)/(double)currentWidth);
       if (!trueColor) {
          red[i]=200;
          green[i]=200;
          blue[i]=200;
       }

       else if (lam>880) {
       green[i]=Math.min(200,100+100*(lam-880)/100);
       red[i]=Math.min(255,155+100*(lam-880)/100);
       blue[i]=Math.min(200,100+100*(lam-880)/100);
       }

       else if ((lam>730) && (lam <=880)){
       green[i]=100*(lam-730)/150;
       blue[i]=100*(lam-730)/150;
       red[i]=155+100*(880-lam)/150;
       }
       else if ((lam>=560) && (lam<=750)){
         if (lam<660) green[i]=255*(660-lam)/100;
         else green[i]=0;
         red[i]=255;
         blue[i]=0;
       }
       else if ((lam<560)&& (lam>=480)){
         green[i]=255;
         red[i]=255*(lam-480)/80;
         blue[i]=0;
       }
       else if((lam<480)&&(lam>=440)){
         red[i]=0;
         if (lam>460) {
            //blue[i]=255*(480-lam)/30;
            green[i]=200+55*(lam-460)/20;
            blue[i]=150*(480-lam)/20;
         }
         else {
            //blue[i]=255;
            green[i]=200*(lam-440)/20;
            blue[i]=150+105*(460-lam)/20;
         }

       }
       else if((lam<440)&&(lam>=400)){
         red[i]=200*(440-lam)/40;
         blue[i]=255-55*(440-lam)/40;
         green[i]=0;
       }

       else if ((lam<400)&& (lam>330)){
       red[i]=200*(lam-250)/150;
       blue[i]=200*(lam-250)/150;
       green[i]=60*(lam-330)/70;
       }

       else if  (lam<330){
          red[i]=Math.min(200,105+95*(330-lam)/150);
          blue[i]=Math.min(255,105+150*(330-lam)/150);
          green[i]=Math.min(200,60+140*(330-lam)/150);
       }

       else {red[i]=0; blue[i]=0; green[i]=0;};
    }
  }

    /**
  *
  * Method assigns color values to each wavelength position. Color arrays containing
  * a value for each pixel across the width of the screen are filled.
  *
  * If trueColor is set to false, gray lines will be displayed.
  * If falseColor is set to false, emission or absorption in the Infrared and UV regions
  * will be displayed in colorless gray.
  *
  * makePalette2 is called when falseColor=false;
  */
  void makePalette2(){
    for(int i=0; i<currentWidth; i++){

       //lam is wavelength (nm) corresponding to pixel position i

       double lam = lbound+((double)i*(ubound-lbound)/(double)currentWidth);
       if (!trueColor) {
          red[i]=200;
          green[i]=200;
          blue[i]=200;
       }

       else if (lam>880) {
       green[i]=Math.min(155,100+155*(lam-880)/100);
       red[i]=155;
       blue[i]=Math.min(155,100+155*(lam-880)/100);
       }

       else if ((lam>730) && (lam <=880)){
       green[i]=100*(lam-730)/150;
       blue[i]=100*(lam-730)/150;
       red[i]=155+100*(880-lam)/150;
       }
       else if ((lam>=560) && (lam<=750)){
         if (lam<660) green[i]=255*(660-lam)/100;
         else green[i]=0;
         red[i]=255;
         blue[i]=0;
       }
       else if ((lam<560)&& (lam>=480)){
         green[i]=255;
         red[i]=255*(lam-480)/80;
         blue[i]=0;
       }
       else if((lam<480)&&(lam>=440)){
         red[i]=0;
         if (lam>460) {
            //blue[i]=255*(480-lam)/30;
            green[i]=200+55*(lam-460)/20;
            blue[i]=150*(480-lam)/20;
         }
         else {
            //blue[i]=255;
            green[i]=200*(lam-440)/20;
            blue[i]=150+105*(460-lam)/20;
         }

       }
       else if((lam<440)&&(lam>=400)){
         red[i]=200*(440-lam)/40;
         blue[i]=255-55*(440-lam)/40;
         green[i]=0;
       }

       else if ((lam<400)&& (lam>330)){
       red[i]=200-45*(400-lam)/70;
       blue[i]=200-45*(400-lam)/70;
       green[i]=155*(400-lam)/200;
       }

       else if (lam<330){
          red[i]=155;
          blue[i]=155;
          green[i]=Math.min(155,155*(400-lam)/200);
       }

       else {red[i]=0; blue[i]=0; green[i]=0;};
    }
  }

 /*
  *
  * Method sets upper and lower bounds for displayed spectrum.
  *
  * @param lambda1 double first wavelength bound in nm
  * @param lambda2 double second wavelength bound in nm
  *
  */
  void setScale(double lambda1, double lambda2){
    lbound = Math.min(lambda1,lambda2);
    ubound = Math.max(lambda1,lambda2);
    if (unit==1) {
       lbound = cmToNm(lbound);
       ubound = cmToNm(ubound);
    }
    if (autoRefresh) runSpectrum();
  }

  /**
  *
  * method sets unit of wavelength. default is nm.
  *
  * @param u int 1=cm, 0=nm.
  *
  */
  public void setWavelengthUnit(int u){
    this.unit = u;
  }

    /*
  *
  * Method turns Color in visible spectrum on and off
  *
  * @param tc boolean
  *
  */
  void setTrueColor(boolean tc){
    trueColor=tc;
  }

  /*
  *
  * Method turns false Color in invisible spectrum (IR and UV) on and off
  *
  * @param fc boolean
  *
  */
  void setFalseColor(boolean fc){
    falseColor=fc;
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
      SpectrumThing t=getThing(id);
      if (unit==1){en = en*10E-7;}
      t.gs = en;
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
     SpectrumThing t = getThing(id);
     double en = t.calcEn();
     if (unit==1){en = en*10E7;}
     return  en;
  }



  /*
  *
  * Method DopplerShifts displayed spectrum.
  *
  * @param vt double velocity as fraction of c, speed of light -1 <= vt <= +1
  *
  */
  void setDopplerShift(double v,boolean ds){
      if ((v<1) && (v>-1)) beta=v;
      for (Enumeration e=thingVector.elements(); e.hasMoreElements();){
            SpectrumThing t = (SpectrumThing)e.nextElement();
            t.doppler=ds;
      }
  }

  /**
  *
  * Method clears all absorption and emission lines.
  *
  *
  */
  void clearAllLines(){
      thingVector.removeAllElements();
      if (autoRefresh) repaint();
  }

  /**
  *
  * Method clears one line corresponding to hashcode id
  *
  * @param id int hashcode id of emission or absorption line
  *
  */
  public void clearLine(int id){
       SpectrumThing t = getThing(id);
       thingVector.removeElement(t);
       if (autoRefresh) repaint();
  }

  /**
  *
  * Method clears caption or title corresponding to hashcode id
  *
  * @param id int hashcode id of caption or title
  *
  */
  public void clearCaption(int id){
     TextThing t = getTextThing(id);
     textVector.removeElement(t);
     if (autoRefresh) repaint();
  }

  /**
  *
  * Adds Emission slit to SpectrumPanel
  *
  * @param lambda double wavelength of slit in nm or cm
  * @param width double width of slit in nm or cm
  * @param intensity double relative intensity of emission (0 <= i <= 1)
  *
  */
  public int addEmission(double lambda, double width, double in){
      if (unit==1) {
        lambda=cmToNm(lambda);
        width = cmToNm(width);
      }
      Emission e = new Emission(this,lambda,width,in);
      thingVector.addElement(e);

      if (autoRefresh) runSpectrum();
      return e.hashCode();
      //repaint();
  }
  /**
  *
  * Adds Gaussian Emission line to SpectrumPanel
  *
  * @param lambda double wavelength of line in nm or cm
  * @param width double width of line in nm or cm
  * @param intensity double relative intensity of emission peak(0 <= i <= 1)
  *
  */
  public int addEmissionLine(double lambda, double width, double in){
      if (unit==1) {
        lambda=cmToNm(lambda);
        width = cmToNm(width);
      }
      ELine e = new ELine(this,lambda,width,in);
      thingVector.addElement(e);
      if (autoRefresh) runSpectrum();
      return e.hashCode();
  }
  /**
  *
  * Adds Emission band to SpectrumPanel
  *
  * @param lambda1 double wavelength left bound in nm  or cm
  * @param lambda2 double wavelength right bound in nm  or cm
  * @param func String intensity function for band  f(lambda)
  */
  public int addEmissionBand(double lambda1, double lambda2, String func){
      if (unit==1) {
        lambda1=cmToNm(lambda1);
        lambda2=cmToNm(lambda2);
      }
      EBand e = new EBand(this,lambda1,lambda2,func);
      thingVector.addElement(e);
      if (autoRefresh) runSpectrum();
      return e.hashCode();
  }
  /**
  *
  * Adds Absorption slit to SpectrumPanel
  *
  * @param lambda double wavelength of slit in nm or cm
  * @param width double width of slit in nm or cm
  * @param intensity double relative intensity of absorption (0 <= i <= 1)
  *
  */
  public int addAbsorption(double lambda, double width, double in){
      if (unit==1) {
        lambda=cmToNm(lambda);
        width = cmToNm(width);
      }
      Absorption a = new Absorption(this,lambda,width,in);
      thingVector.addElement(a);
      if (autoRefresh) runSpectrum();
      return a.hashCode();
  }
  /**
  *
  * Adds Gaussian Absorption line to SpectrumPanel
  *
  * @param lambda double wavelength of line in nm or cm
  * @param width double width of line in nm  or cm
  * @param intensity double relative intensity of absorption peak(0 <= i <= 1)
  *
  */
  public int addAbsorptionLine(double lambda, double width, double in){
      if (unit==1) {
        lambda=cmToNm(lambda);
        width = cmToNm(width);
      }
      ALine a = new ALine(this,lambda,width,in);
      thingVector.addElement(a);
      if (autoRefresh) runSpectrum();
      return a.hashCode();
  }
  /**
  *
  * Adds Absorption band to SpectrumPanel
  *
  * @param lambda1 double wavelength left bound in nm or cm
  * @param lambda2 double wavelength right bound in nm  or cm
  * @param func String intensity function for band  f(lambda)
  */
  public int addAbsorptionBand(double lambda1, double lambda2, String func){
      if (unit==1) {
        lambda1=cmToNm(lambda1);
        lambda2=cmToNm(lambda2);
      }
      ABand a = new ABand(this,lambda1,lambda2,func);
      thingVector.addElement(a);
      if (autoRefresh) runSpectrum();
      return a.hashCode();
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
     double lam=0;
     int a=sn;
     if (sn>bn) {sn=bn; bn=a;}
     if (sn<3) {sn=3; bn=6;}
     for(int i=sn; i<bn+1; i++){
        lam = 1/(rh*(0.25-1/(double)(i*i)));
        addEmissionLine(lam,4,1);
     }
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
     double lam=0;
     int a=sn;
     if (sn>bn) {sn=bn; bn=a;}
     if (sn<4) {sn=4; bn=7;}
     for(int i=sn; i<bn+1; i++){
        double par=(1/(double)9)-(1/((double)(i*i)));
        lam = 1/(rh*par);
        addEmissionLine(lam,10,1);
     }
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
     double lam=0;
     int a=sn;
     if (sn>bn) {sn=bn; bn=a;}
     if (sn<2) {sn=2; bn=5;}
     for(int i=sn; i<bn+1; i++){
        lam = 1/(rh*(1-1/(double)(i*i)));
        addEmissionLine(lam,0.5,1);
     }
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
     double lam=0;
     int a=sn;
     if (sn>bn) {sn=bn; bn=a;}
     if (sn<5) {sn=5; bn=8;}
     for(int i=sn; i<bn+1; i++){
        double par=(1/(double)16)-(1/((double)(i*i)));
        lam = 1/(rh*par);
        addEmissionLine(lam,20,1);
     }
  }


  public SpectrumThing getThing(int id){
       SpectrumThing t=null;
       for( Enumeration e=thingVector.elements(); e.hasMoreElements();){
         t= (SpectrumThing) e.nextElement();
         if(t.hashCode()==id){
           return t;
         }
       }
       return null;
  }

  public TextThing getTextThing(int id){
       TextThing t=null;
       for( Enumeration e=textVector.elements(); e.hasMoreElements();){
         t= (TextThing) e.nextElement();
         if(t.hashCode()==id){
           return t;
         }
       }
       return null;
  }

  /**
  *
  * Method retrieves the wavelength of a line with given hashcode id.
  *
  * @param id int hashcode id# of line
  * @return double wavelength in nm
  */
  public double getWavelength(int id){
    SpectrumThing t = getThing(id);
    if (unit==1) return t.lambda*10E-7;
    else return t.lambda;
  }

   /**
  *
  * Method retrieves the width of a line with given hashcode id.
  *
  * @param id int hashcode id# of line
  * @return double width in nm or cm
  */
  public double getWidth(int id){
    SpectrumThing t = getThing(id);
    if (unit==1) return t.lineWidth*10E-7;
    else return t.lineWidth;
  }




  private void jbInit() throws Exception {
    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        this_mouseDragged(e);
      }
      public void mouseMoved(MouseEvent e) {
        this_mouseMoved(e);
      }
    });
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        this_mouseReleased(e);
      }
      public void mouseEntered(MouseEvent e) {
        this_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        this_mouseExited(e);
      }
    });
  }

   /**
   *
   * Method paints small text box on spectrum to display the wavelength at
   * a given coordinate.
   *
   * @param xcoord int x-coordinate in pixels of position on spectrum
   *
   */
   private void paintMessage(Graphics g){
    FontMetrics fm=g.getFontMetrics(g.getFont());
    int boxW=Math.max(110,10+fm.stringWidth(message));
    g.setColor(Color.yellow);
    g.fillRect(currentWidth-boxW-3,currentHeight-wallW-18,boxW,15);
    //g.fillRect(4,currentHeight-wallW-19,boxW,15);
    g.setColor(Color.black);
    g.drawString(message,currentWidth-boxW,currentHeight-wallW-6);
  }

   /**
   *
   * Method paints small text box on spectrum to display the wavelength at
   * a given coordinate.
   *
   * @param xcoord int x-coordinate in pixels of position on spectrum
   *
   */
   private void paintLambda(Graphics g, int xcoord){
    String msg= null;
    double lampos = lbound+(ubound-lbound)*xcoord/currentWidth;
    if (unit==1){
      lampos = nmToCm(lampos);
      msg= "Wavelength:"+" "+format.form(lampos)+" "+"cm";
      }
    else  msg= "Wavelength:"+" "+format.form(lampos)+" "+"nm";
    //String msg= lamChar+""+"="+""+format.form(lampos)+""+"nm";
    FontMetrics fm=g.getFontMetrics(g.getFont());
    int boxW=Math.max(110,10+fm.stringWidth(msg));
    //int boxW=20+fm.stringWidth(msg);
    //g.setColor(Color.yellow);
    //g.fillRect(currentWidth-boxW-8,currentHeight-wallW-23,boxW+8,23);
    g.setColor(Color.yellow);
    //g.fillRect(currentWidth-boxW-4,currentHeight-wallW-19,boxW,15);
    g.fillRect(3,currentHeight-wallW-18,boxW,15);
    g.setColor(Color.black);
    g.drawString(msg,7,currentHeight-wallW-6);
  }
  private void paintLambda( int xcoord){
    Graphics g = this.getGraphics();
    paintLambda( g, xcoord);
    g.dispose();
  }

  void clearLambdaBox(){
     if (autoRefresh) repaint();
  }

  void this_mousePressed(MouseEvent e) {
          //lamPress = lbound+((double)e.getX()*(ubound-lbound)/(double)currentWidth);
       // for (Enumeration en=thingVector.elements(); en.hasMoreElements();){
        //    Thing t = (Thing)en.nextElement();
        //    if ((t.isInside(lamPress))&& (t.dragable)) {dragThing=t; break;}
        //}

        if(( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){
           if(osi==null) return;
           SpectrumFrame specFrame=new SpectrumFrame(osi);
           specFrame.setTitle(format.form(lbound)+" "+"nm"+" "+"to"+" "+format.form(ubound)+" "+"nm");
           specFrame.show();
        } else{
            mouseDown=true;
            mouseX=e.getX();
            paintLambda(mouseX);
        }
    }




  void this_mouseReleased(MouseEvent e) {
       mouseX=e.getX();
       mouseDown=false;
       repaint();
  }


  void this_mouseDragged(MouseEvent e) {
    if (dragThing!=null) {
        mouseX=Math.min(e.getX(),currentWidth);
        mouseX=Math.max(0,mouseX);
        double lam = lbound+((double)mouseX*(ubound-lbound)/(double)currentWidth);
        moveLine(dragThing,lam);
        repaint();
    }else paintLambda(e.getX());

  }



  /**
  *
  * Moves a particular line to a new wavelength
  *
  * @param id int hashcode id of line to be moved
  * @param lamNew double new wavelength of line
  *
  */
  public void moveLine(SpectrumThing t, double lamNew){
      t.setNewLambda(lamNew);
      if (autoRefresh) repaint();
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
      SpectrumThing t = getThing(id);
      if (unit==1) lamNew = cmToNm(lamNew);
      moveLine(t,lamNew);
  }

  public double nmToCm(double nm){
    return nm*10E-8;
  }

  public double cmToNm(double cm){
      return cm*10E6;
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
      SpectrumThing t = getThing(id);
      t.in=inNew;
      if (autoRefresh) repaint();
  }

  void this_mouseEntered(MouseEvent e) {
    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

  void this_mouseMoved(MouseEvent e) {
      boolean inside=false;
      double lam = lbound+((double)e.getX()*(ubound-lbound)/(double)currentWidth);
      for (Enumeration en=thingVector.elements(); en.hasMoreElements();){
            SpectrumThing t = (SpectrumThing)en.nextElement();
            if ((t.isInside(lam))&&(t.dragable)){
                  dragThing=t;
                  setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                  inside=true;
                  break;
            }
      }

      if (!inside){
         dragThing=null;
         setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      }
  }

  void this_mouseExited(MouseEvent e) {
       dragThing=null;
       setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       repaint();
  }

  /**
  *
  * Adds a message to small yellow box opposite coordinate display.
  *
  *
  * @param msg String
  */
  public void setMessage(String msg){
      this.showMessage=true;
      this.message=msg;
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
     TextThing t = getTextThing(id);
     t.xoff=xoff;
     t.yoff=yoff;
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
     SpectrumThing line = getThing(id);
     TextThing text = (TextThing)getTextThing(id);
     if (line!=null) {
         line.colr = r;
         line.colg = g;
         line.colb = b;
     }
     else if (text!=null){
        text.txtColor = new Color(r,g,b);
     }
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
      Caption cap = new Caption(this,id,str);
      textVector.addElement(cap);
      return cap.hashCode();
  }

  /**
  *
  * Method adds a caption or title to top center of spectrum panel
  *
  *
  * @param text String
  */
  public int addCaption(String text){
      Title title = new Title(this,text);
      textVector.addElement(title);
      return title.hashCode();
  }


  public synchronized int addEmissionDataSource(int i){
    DataSource ds=new DataSource(i);
    emissionDataSources.addElement(ds);
    return ds.hashCode();
  }

  public int getID(){return this.hashCode();}
  public void setOwner(SApplet app){this.owner=app;}
  public SApplet getOwner(){return owner;}  //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  public String[]   getVarStrings(){return varStrings; }

  public double[][] getVariables(){
      for (int i=0; i<currentWidth; i++){
          vars[i][0]=pixToLambda(i);
          vars[i][1]=intensity[i];
      }
      /*
      for( Enumeration e=thingVector.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         vars[index][0]=t.lambda;
         vars[index][1]=t.width;
         vars[index][2]=t.in;
         index++;
         if (index>vars.length) break;
      */

      return vars;
  }

  double pixToLambda(int pix){
       double lam = lbound+((double)pix*(ubound-lbound)/(double)currentWidth);
       return lam;
  }

  int lambdaToPix(double lam){
       int pix = (int)Math.round(((lam-lbound)/(ubound-lbound))*currentWidth);
       return pix;
  }

  public double pixToIntensity(int xpix){
        if(xpix<0 || xpix>=intensity.length) return 0;
        return intensity[xpix];
  }
  // SScalable methods
    public double xFromPix(int xpix){ return pixToLambda(xpix);}
    public double yFromPix(int ypix){return 0;}
    public int pixFromX(double x){ return lambdaToPix(x);}
    public int pixFromY(double y){return 0;}
    public int getPixWidth(){return currentWidth;}
    public int getPixHeight(){return currentHeight;}



    // inner class used for data connection to particles.
  public class DataSource extends Object   implements SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"lam","int","w"};
    double[][] ds=new double[1][3];  // the datasource state variables t,x,y,vx,vy,ax,ay,p;
    int index=0;

    DataSource(int i){ // Constructor
       index=i;
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
       SpectrumThing t = getThing(index);
       if (t==null) return ds;
       ds[0][0]=t.lambda;
       ds[0][1]=t.in;
       ds[0][2]=t.lineWidth;
       return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return owner;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  }


}