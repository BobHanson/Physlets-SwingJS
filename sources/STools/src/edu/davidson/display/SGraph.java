package edu.davidson.display;

//import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;

import a2s.*;
//import java.awt.*;
import java.awt.event.*;

import edu.davidson.graph.*;
import edu.davidson.numerics.*;
import edu.davidson.tools.*;

/**
 * A plotting bean based on the graph package by Leigh Brookshaw.
 *
 * SDataListener added 1/1/99
 *
 * @version  $Revision: 0.4 $, $Date: 1999/01/01 05:23:41 $
 * @author   Wolfgang Christian
*/

public class SGraph extends edu.davidson.graph.Graph2D implements SStepable,Cloneable, SDataListener,Runnable, SScalable{ // implements BlackBox{
  public String label_time = "Time: ";
 // variables for the delayed drawing.
  private static Color[] colors=new Color[91];
  private boolean interrupted=false;
  private boolean newData=false;
  Object delayLock = new Object();
  private Thread delayThread=null;

  public Axis    xaxis;
  public Axis    yaxis;
  int[]   borders={0,10,10,0};
  Format  format= new Format("%-+6.2g");
  private boolean autoRefresh=true;
  private MouseMotionAdapter mouseMotionAdapter;
  private MouseAdapter mouseAdapter;
  private int     boxWidth=0;
  private boolean sampleData=false;
  private Vector  dataSetSeries=new Vector();
  private Vector  functions=new Vector();
  private Vector  cfunctions=new Vector();
  private double[] dPoint=new double[2];
 // private DataSet defaultData=null;
  private String  labelX="X Axis";
  private String  labelY="Y Axis";
  private Color   labelXColor = Color.black; // Francisco Esquembre (March 2000)
  private Color   labelYColor = Color.black; // Francisco Esquembre (March 2000)
  private Color   labelXTitleColor = Color.black; // Francisco Esquembre (March 2000)
  private Color   labelYTitleColor = Color.black; // Francisco Esquembre (March 2000)
  private double defaultMarkerScale=1.0;
  private boolean enableMouse=false;
  private boolean enableClone=true;
  private boolean enableCoordDisplay=true;
  private SApplet parentSApplet;
  private String  titleStr=null;
  private TextLine title=new TextLine();
  private Color titleColor = Color.black;
  private Color dataBackground = Color.white; // Francisco Esquembre (March 2000)
  private boolean mouseDrag=false;
  private int mouseX, mouseY;
  private Thing dragThing;
  private TrailThing trailThing=null;
 // private boolean trackDrag=false;
  private boolean sketchMode=false;
  private Font boldFont=new Font("Helvetica",Font.BOLD,14);
  private boolean hasGraphThing=false;
  boolean timeDisplay=false;
  Image sketchImage=null;
  //Cursor sketchCursor=null;

  Vector things= new Vector();
  Image offScreenImage=null;

  // Constructors
  public SGraph() {
      initColors();
      title.setFontStyle(Font.BOLD);
      title.setFontSize(16);
      setDataBackground(Color.white);
      setGraphBackground(Color.white);
      buildMarkers(4);
      drawzero = false;
      drawgrid = false;
      borderLeft = borders[0];
      borderTop = borders[1];
      borderRight = borders[2];
      borderBottom = borders[3];
      //Attach both data sets to the Xaxis
      xaxis = createAxis(Axis.BOTTOM);
      //xaxis.attachDataSet(data1);
      xaxis.setTitleColor(labelXTitleColor); // Francisco Esquembre (March 2000)
      xaxis.setLabelColor(labelXColor); // Francisco Esquembre (March 2000)
      xaxis.setTitleBackground(Color.white);
      xaxis.setTitleText(labelX);
      xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,12));
      xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,10));
      //Attach the first data set to the Left Axis
      yaxis = createAxis(Axis.LEFT);
      //yaxis.attachDataSet(data1);
      yaxis.setTitleColor(labelYTitleColor); // Francisco Esquembre (March 2000)
      yaxis.setLabelColor(labelYColor); // Francisco Esquembre (March 2000)
      yaxis.setTitleBackground(Color.white);
      yaxis.setTitleText(labelY);
      yaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,12));
      yaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,10));
      if(sampleData)makeSampleData(100);
      //System.out.println("SGraph Constructor: "+hashCode());
      try{SApplet.addDataListener(this);}catch (Exception e){e.printStackTrace();}
      delayThread = new Thread(this);
      delayThread.start();
      setFont (getFont()); // Francisco Esquembre (March 2000). Yes this changes the font above

  }
  public SGraph(SApplet applet){
      this();
      parentSApplet=applet;
  }

  public void step(double dt,double time){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
      t= (Thing) e.nextElement();
      if(t instanceof SStepable) ((SStepable) t).step(dt, time);
    }
    if(autoRefresh && parentSApplet!=null){
      if(parentSApplet.destroyed) return;
      parentSApplet.updateDataConnections();
      synchronized(delayLock){newData=true; delayLock.notify(); }
    }
  }

  boolean isInsideDragableThing(int x, int y){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(!t.noDrag && t.isInsideThing(x,y)){
           return true;
         }
    }
    return false;
  }

//Accessor methods


/**
 *    Set the fonts, referred to that of labelTitle
 *
 *    @param aFont    labelTitle font.
*/
  public void setFont (Font aFont) { // Francisco Esquembre (March 2000)
    if (aFont==null) return;
    super.setFont (aFont);
    Font derivedFont = new Font (aFont.getFamily(),Font.BOLD,aFont.getSize()+2);
    if (title==null) return;
    title.setFont (derivedFont);
    xaxis.setTitleFont(aFont);
    yaxis.setTitleFont(aFont);
    derivedFont = new Font (aFont.getFamily(),Font.PLAIN,aFont.getSize()-4);
    xaxis.setLabelFont(derivedFont);
    yaxis.setLabelFont(derivedFont);
  }

/**
 *    Set the color for the texts (except the title)
 *
 *    @param c    The color.
*/
  public void setForeground (Color c) { // Francisco Esquembre (March 2000)
    if (c==null) return;
    super.setForeground (c);
    if(xaxis==null) return;
    xaxis.setTitleColor(c);
    xaxis.setLabelColor(c);
    yaxis.setTitleColor(c);
    yaxis.setLabelColor(c);
    if(autoRefresh) repaint();
  }

/**
 *    Set the background of the data
 *
 *    @param c    The color.
*/
  public void setDataBackground (Color c) { // Francisco Esquembre (March 2000)
    dataBackground = c;
    super.setDataBackground(c);
    if(autoRefresh) repaint();
  }

/**
 * Set the background color for the entire canvas.
 * @params c The color to set the canvas
 */
    public void setGraphBackground(Color c) {
        if(c == null) return;
        if (xaxis!=null) xaxis.setTitleBackground(c);
        if (yaxis!=null) yaxis.setTitleBackground(c);
        setBackground(c);
    }

/**
 *    Get the background of the data
*/
  public Color getDataBackground () { // Francisco Esquembre (March 2000)
    return dataBackground;
  }



/**
 *    Create sample data when it is initialized.
 *
 *    @param sd    Create the sample data?.
*/
  public void setSampleData(boolean sd){
      if(sd==sampleData) return;
      sampleData=sd;
      if(sd)makeSampleData(100);
        else deleteAllSeries();
      //if(autoRefresh) repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }
/**
 *    Will the graph show sample data when it is initialized?
 *
 *    @return    True if graph contains sample data?.
*/
  public boolean isSampleData(){return sampleData;}

/**
 *    Set the draw grid property.
 *
 *    @param dg    Draw the grid?.
*/
  public void setDrawGrid(boolean dg){
      if(dg==drawgrid) return;
      drawgrid=dg;
      //if(autoRefresh)repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }
/**
 *    Will the graph have a grid?
 *
 *    @return    True if grid is shown.
*/
  public boolean isDrawGrid(){return drawgrid;}

/**
 *    Set the autoRefresh property. Repaint the graph whenever data changes.  Slows down the system.
 *
 *    @param auto    AutoRefresh the graph?.
*/
  public void setAutoRefresh(boolean auto){
      if(auto==autoRefresh) return;
      autoRefresh=auto;
      if(autoRefresh){
         //repaint();
         //Graphics g=getGraphics();
        // paintOffScreen(g);
        //g.dispose();
        synchronized(delayLock){newData=true; delayLock.notify(); }
      }
  }
  public boolean isAutoRefresh(){return autoRefresh;}


/**
 *    Set the autoscaleX property.
 *
 *    @param as    Autoscale the x axis?.
*/
  public void setAutoscaleX(boolean as){
      if(!as==xaxis.isManualRange() ) return;
      xaxis.setManualRange(!as);
      //if(autoRefresh)repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }

  public boolean isAutoscaleX(){return !xaxis.isManualRange() ;}

 /**
 *    Set the autoscaleY property.
 *
 *    @param as    Autoscale the y axis?.
*/
  public void setAutoscaleY(boolean as){
      if(!as==yaxis.isManualRange() ) return;
      yaxis.setManualRange(!as);
      //if(autoRefresh)repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }
  public boolean isAutoscaleY(){return !yaxis.isManualRange();}

/**
 *    Have the graph draw x=0 and y=0 lines.
 *
 *    @param dz    Draw the lines?
*/
  public void setDrawZero(boolean dz){
      if(dz==drawzero) return;
      drawzero=dz;
      //if(autoRefresh)repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }
  public boolean isDrawZero(){return drawzero;}

/**
 *    Set the series legend.
 *
 *    @param sid        The series id.
 *    @param c         The legend color.
 *    @param xpix      The x postion in pixels.
 *    @param ypix      The y postion in pixels.
 *    @param legend    The legend.
*/
  public synchronized void setSeriesLegend(int sid, Color c,int xpix, int ypix, String legend ){
      DataSet data;
      Series series=getSeriesFromSID(sid);
      if(series==null){
          dPoint[0]=0; dPoint[1]=0;
          data=attachArray(sid,dPoint).getDataSet();
          data.deleteData();  // data object still exists
      }else{data=series.data;}
      data.legend(xpix,ypix,legend);
      if(c!=null)data.legendColor(c);
      //if(autoRefresh)repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }

  public synchronized void setSeriesLegend(int sid,int xpix, int ypix, String legend ){
      Color c=Color.black;
      Series series=createSeries(sid);
      c=series.data.markercolor;
      setSeriesLegend( sid,  c, xpix,  ypix, legend ) ;
  }

  public synchronized void setSeriesLegendColor(int sid, Color c ){
      Series series=createSeries(sid);
      series.data.legendColor(c);
      //if(autoRefresh) repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }

   static private Color colorOf (String c) { // Francisco Esquembre (March 2000)
     if (c.equals("black"))           return Color.black;
     else if (c.equals("blue"))       return Color.blue;
     else if (c.equals("cyan"))       return Color.cyan;
     else if (c.equals("darkGray"))   return Color.darkGray;
     else if (c.equals("gray"))       return Color.gray;
     else if (c.equals("green"))      return Color.green;
     else if (c.equals("lightGray"))  return Color.lightGray;
     else if (c.equals("magenta"))    return Color.magenta;
     else if (c.equals("orange"))     return Color.orange;
     else if (c.equals("pink"))       return Color.pink;
     else if (c.equals("red"))        return Color.red;
     else if (c.equals("white"))      return Color.white;
     else if (c.equals("yellow"))     return Color.yellow;
     else return Color.white;  // default color
   }

/**
 *    Set the series style.
 *
 *    @param sid        The series id.
 *    @param c         The series color (as a string).
 *    @param conPts    Connect the points?
 *    @param m         Marker style. (m=1 is cross; m=2 is square, m=3 is circle)
*/ // Francisco Esquembre (March 2000)

  public synchronized void setSeriesStyle(int sid, String colorName,boolean conPts, int m ){
    setSeriesStyle (sid,SGraph.colorOf (colorName), conPts, m);
  }

/**
 *    Set the series style.
 *
 *    @param sid        The series id.
 *    @param c         The series color (as a string).
 *    @param conPts    Connect the points?
 *    @param m         Marker style. (m=1 is cross; m=2 is square, m=3 is circle)
 *    @param size      Marker size.
*/ // Francisco Esquembre (March 2000)

  public synchronized void setSeriesStyle(int sid, String colorName,boolean conPts, int m , double size){
    setSeriesStyle (sid,SGraph.colorOf (colorName), conPts, m, size);
  }


/**
 *    Set the series style.
 *
 *    @param sid        The series id.
 *    @param c         The series color.
 *    @param conPts    Connect the points?
 *    @param m         Marker style. (m=1 is cross; m=2 is square, m=3 is circle)
 *    @param size      Marker size.
*/ // Francisco Esquembre (March 2000)

  public synchronized void setSeriesStyle(int sid, Color c,boolean conPts, int m , double size){
      DataSet data;
      Series series=getSeriesFromSID(sid);
      if(series==null){
          dPoint[0]=0; dPoint[1]=0;
          data=attachArray(sid,dPoint).getDataSet();
          data.deleteData();  // data object still exists
      }else{data=series.data;}
      if(conPts) data.linestyle = 1;
          else data.linestyle = 0;
      data.marker    = m;
      data.markerscale = size;
      data.markercolor = c;
      data.linecolor = c;
      //if(autoRefresh) repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }

/**
 *    Set the series style.
 *
 *    @param sid        The series id.
 *    @param c         The series color.
 *    @param conPts    Connect the points?
 *    @param m         Marker style. (m=1 is cross; m=2 is square, m=3 is circle)
*/
  public synchronized void setSeriesStyle(int sid, Color c,boolean conPts, int m ){
      DataSet data;
      Series series=getSeriesFromSID(sid);
      if(series==null){
          dPoint[0]=0; dPoint[1]=0;
          data=attachArray(sid,dPoint).getDataSet();
          data.deleteData();  // data object still exists
      }else{data=series.data;}
      if(conPts) data.linestyle = 1;
          else data.linestyle = 0;
      data.marker    = m;
      //data.markerscale = markerScale;
      data.markercolor = c;
      data.linecolor = c;
      //if(autoRefresh) repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }

  public synchronized void setSeriesStyle(int sid, boolean conPts, int m ){
      Color c=Color.black;
      Series series=createSeries(sid);
      c=series.data.markercolor;
      setSeriesStyle( sid, c, conPts,  m ) ;
  }

  public synchronized void setSeriesColor(int sid, Color c ){
      Series series=createSeries(sid);
      series.data.markercolor = c;
      series.data.linecolor = c;
      //if(autoRefresh) repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }

  public synchronized void setSeriesSorted(int sid, boolean sorted){
      Series series=createSeries(sid);
      series.data.setSorted(sorted);
  }

  public synchronized void setSeriesStripChart(int sid,int chartpoints, boolean stripchart){
      Series series=createSeries(sid);
      series.data.setStripChart(chartpoints,stripchart);
  }

/**
 *    Set sketch mode.  A click drag will create a data set.
 *
 *    @param sm       set sketch mode if true
 *    @param gifFile  the cursor gif

  public int  setSketchMode(boolean sm, String gifFile){
      sketchMode=sm;
      if(!sm){
          trailThing=null;
          return 0;
      }
      trailThing=new TrailThing(parentSApplet, this,1);
      trailThing.trailSize=2;
      Toolkit tk=Toolkit.getDefaultToolkit();
      Image cursorImage;
      cursorImage=tk.getImage(gifFile);
      int maxColors=tk.getMaximumCursorColors();
      if(maxColors==0 || cursorImage==null ){
         System.out.println("Custom cursors not supported or cursor image not found.");
         sketchCursor=null;
         return trailThing.hashCode();
      }
      if(cursorImage==null) return trailThing.hashCode();
      sketchCursor=tk.createCustomCursor(cursorImage,new Point(1,1),"sketch cursor");
      setCursor(sketchCursor);
      return trailThing.hashCode();
  }*/

/**
 *    Set sketch mode.  A click drag will create a data set.
 *
 *    @param sm       set sketch mode if true
*/
  public int setSketchMode(boolean sm){
      Applet applet=edu.davidson.graphics.Util.getApplet(this);
      sketchImage=edu.davidson.graphics.Util.getImage("pencil.gif",applet);
      sketchMode=sm;
      if(!sm){
          trailThing=null;
          return 0;
      }
      trailThing=new TrailThing(parentSApplet, this,1);
      trailThing.trailSize=2000;

      // custom cursors don't work yet so set this to zero.
      /*
      if(edu.davidson.graphics.Util.isMicrosoft()){
        return trailThing.hashCode();
      }
      try{
        Toolkit tk=Toolkit.getDefaultToolkit();
        int maxColors=tk.getMaximumCursorColors();
        if(maxColors==0 || im==null){
          System.out.println("Custom cursors not supported or cursor image not found.");
          sketchCursor=null;
          return trailThing.hashCode();
        }
        sketchCursor=tk.createCustomCursor(im,new Point(xoff,yoff),"sketch cursor");
        if(sketchCursor==null) System.out.println("Custom Cursor=null");
        //setCursor(sketchCursor);
      }catch (Exception e){
        sketchCursor=null;
        System.out.println("Custom Cursor=null");
      }*/
      return trailThing.hashCode();
  }

/**
 *    Show or hide the the X and Y axes on the graph. May need to set the gutters to zero seliminate white space.
 *
 *    @param sa       Show the axis?
*/
  public synchronized void setShowAxes(boolean sa){
      setShowAxis(sa);
      //if(autoRefresh) repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }

  }

/**
 *    Show the X and Y axes
*/
  public boolean isShowAxes(){
      return isShowAxis();
  }

/**
 *    Set the aspect ratio=1 so that both axes have the same pixels per unit.
 *
 *    @param isSquare?
*/
  public synchronized void setSquare(boolean isSquare){
      square=isSquare;
      if(autoRefresh) repaint();
  }

/**
 *    Get the aspect ratio=1 so that both axes have the same pixels per unit.
*/
  public boolean isSquare(){ // Francisco Esquembre (March 2000)
      return square;
  }

/**
 *    Get the margins.  Use strings for the beans inspector.
 *
 *    @return   A string containg the gutter sizes: left, top, right, bottom.
*/

  public String getBorders(){
      String  b=""+borders[0]+','+borders[1]+','+borders[2]+','+ borders[3];
      return b;
  }

/**
 *  Get the ID for this object.
*/
  public int getID(){return hashCode();}


/**
 *  Get the ID for a Thing object from the hashcode.
*/
  public Thing getThing(int id){
       Thing t=null;
       for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t.hashCode()==id){
           return t;
         }
       }
       return null;
    }

/**
 *    Set the margins.  Need to use strings for the beans inspector.
 *
 *    @param s    The gutter sizes: left, top, right, bottom.
*/
  public void setBorders(String s){
      boolean error=false;
      StringTokenizer tokens=new StringTokenizer(s.trim(),", ; / \\ ( { [ ) } ] \t \n \r");
      if(tokens.countTokens()<4) error=true;
      else for(int i=0; i<4; i++){
          try{
              borders[i]=Integer.parseInt(tokens.nextToken().trim());
             // System.out.println("boarders:"+ borders[i]);
          }catch(NumberFormatException e){
              error=true;
          }
      }
      if (!error){
          borderLeft = borders[0];
          borderTop = borders[1];
          borderRight = borders[2];
          borderBottom = borders[3];
          if(autoRefresh) repaint();
      }else{
          borders[0]=borderLeft;
          borders[1]=borderTop;
          borders[2]=borderRight;
          borders[3]=borderBottom;
      }
  }

  /**
 *    Set the gutters.
 *
 *    @param g1    The gutter sizes: left, top, right, bottom.
*/
  public void setGutters(int g1, int g2, int g3, int g4){
          borderLeft =  g1;
          borderTop =   g2;
          borderRight = g3;
          borderBottom = g4;
          if(autoRefresh) repaint();
  }

/**
 *    Set the x axis label.
 *
 *    @param The label value.
*/
  public void setLabelX(String s){
      xaxis.setTitleColor(labelXTitleColor); // Francisco Esquembre (March 2000)
      xaxis.setTitleText(s);
      labelX=s;
      if(autoRefresh) repaint();
  }
  public void setLabelX(String s, Color c){
      labelXTitleColor = c;  // Francisco Esquembre (March 2000)
      xaxis.setTitleColor(c);
      xaxis.setTitleText(s);
      labelX=s;
      if(autoRefresh) repaint();
  }
  public String getLabelX(){
      return labelX;
  }

  /**
 *    Get the x axis label title color.
*/
  public Color getLabelXTitleColor(){ // Francisco Esquembre (March 2000)
      return labelXTitleColor;
  }

  /**
 *    Set the x axis label title color.
 *
 *    @param The label color.
*/
  public void setLabelXTitleColor(Color c){ // Francisco Esquembre (March 2000)
      labelXTitleColor = c;
      xaxis.setTitleColor(c);
      xaxis.setTitleText(labelX);
      if(autoRefresh) repaint();
  }

  /**
 *    Get the x axis label color.
*/
  public Color getLabelXColor(){ // Francisco Esquembre (March 2000)
      return labelXColor;
  }

  /**
 *    Set the x axis label color.
 *
 *    @param The label color.
*/
  public void setLabelXColor(Color c){ // Francisco Esquembre (March 2000)
      labelXColor = c;
      xaxis.setLabelColor(c);
      if(autoRefresh) repaint();
  }

  /**
 *    Set the y axis label.
 *
 *    @param The label value.
*/
  public void setLabelY(String s){
      yaxis.setTitleColor(labelYTitleColor); // Francisco Esquembre (March 2000)
      yaxis.setTitleText(s);
      labelY=s;
      if(autoRefresh) repaint();
  }

  public void setLabelY(String s, Color c){
      labelYTitleColor = c;  // Francisco Esquembre (March 2000)
      yaxis.setTitleColor(c);
      yaxis.setTitleText(s);
      labelY=s;
      // yaxis.setTitleBackground( DataBackground  );
      if(autoRefresh) repaint();
  }

  public String getLabelY(){
      return labelY;
  }

  /**
 *    Get the y axis label title color.
*/
  public Color getLabelYTitleColor(){ // Francisco Esquembre (March 2000)
      return labelYTitleColor;
  }

  /**
 *    Set the y axis label title color.
 *
 *    @param The label color.
*/
  public void setLabelYTitleColor(Color c){ // Francisco Esquembre (March 2000)
      labelYTitleColor = c;
      yaxis.setTitleColor(c);
      yaxis.setTitleText(labelY);
      if(autoRefresh) repaint();
  }

  /**
 *    Get the y axis label color.
*/
  public Color getLabelYColor(){ // Francisco Esquembre (March 2000)
      return labelYColor;
  }

  /**
 *    Set the y axis label color.
 *
 *    @param The label color.
*/
  public void setLabelYColor(Color c){ // Francisco Esquembre (March 2000)
      labelYColor = c;
      yaxis.setLabelColor(c);
      if(autoRefresh) repaint();
  }



  public synchronized void setLastPointMarker(int sid, boolean lpm){
      Series series=createSeries(sid);
      series.enableLPCursor=lpm;
      if(autoRefresh) repaint();
  }

  public synchronized void setAutoReplaceData(int sid, boolean auto){
      Series series=createSeries(sid);
      series.autoReplace=auto;
  }

/**
 *    Set the addRepeatedDataum property.  A new datum will not be added to a series if this is false.
 *    Default is true.
 *
 *    @param add    Added repeated data values to the graph?
*/
    public void setAddRepeatedDatum(int sid, boolean add){
        Series series=createSeries(sid);
        series.addRepeatedDatum=add;
    }


/**
 *    Set the default marker size.
 *
 *    @param size    Marker size.
*/
  public void setMarkerSize(double scale){ // Should be deprecated
      defaultMarkerScale=scale;
  }

/**
 *    Get the default marker size.
*/
  // Should be deprecated
  public double getMarkerSize(){ // Francisco Esquembre (March 2000)
      return defaultMarkerScale;
  }


/**
 *    Set the default marker size.
 *
 *    @param size    Marker size.
*/
  public void setDefaultMarkerSize(double size){
      defaultMarkerScale=size;
  }

/**
 *    Get the default marker size.
*/
  public double getDefaultMarkerSize(){
      return defaultMarkerScale;
  }

/**
 *    Set all the markers to a new size and set the default maker to the new
size.
 *
 *    @param size    Marker size.
*/
  public void setAllMarkerSizes(double scale){
      defaultMarkerScale=scale;
      for(int i=0; i<dataSetSeries.size(); i++){ // Francisco Esquembre (March 2000)
         Series s=(Series)dataSetSeries.elementAt(i);
         s.data.markerscale = scale;
       }
       if(autoRefresh) repaint(); // Francisco Esquembre (March 2000)
  }

  /**
 *    Set the marker size.
 *
 *    @param id      Series id.
 *    @param size    Marker size.
*/
  public void setMarkerSize(int sid, double scale){
      Series series=createSeries(sid);
      series.data.markerscale = scale;
      if(autoRefresh) repaint();
  }


/**
 *    Set the x axis minimum and maximum values.
 *
 *    @param min    Minumum value.
 *    @param max    Maximum value.
*/
  public void setMinMaxX(double min,double max){
          if (xaxis.isManualRange() ){
             xaxis.setManualRange(true,  min, max);
          }else{
              xaxis.resetRange();  // just to make sure that the range is correct
          }
          if(autoRefresh) repaint();
  }

/**
 *    Restrict the range on the x axis so that it can be no smaller than the given values.
 *
 *    @param enable true to restrict the range, false otherwise
 *    @param min            minimum value.
 *    @param max            maximum value.
*/
  public void setMinXRange(boolean enable, double min,double max){
      xaxis.setMinRange(enable,min,max);
      if(autoRefresh) repaint();
  }

/**
 *    Restrict the range on they axis so that it can be no smaller than the given values.
 *
 *    @param enable true to restrict the range, false otherwise
 *    @param min            minimum value.
 *    @param max            maximum value.
*/
  public void setMinYRange(boolean enable, double min,double max){
      yaxis.setMinRange(enable,min,max);
      if(autoRefresh) repaint();
  }

/**
 *    Set the x axis minimum.
 *
 *    @param min    Minumum value.
*/
  public void setMinX(double min){ // Francisco Esquembre (March 2000)
          if (xaxis.isManualRange() ){
             if(min> xaxis.maximum)xaxis.setManualRange(true,  min, min+1);
                else xaxis.setManualRange(true,  min, xaxis.maximum);
          }else{
              xaxis.resetRange();  // just to make sure that the range is correct
          }
          if(autoRefresh) repaint();
  }


/**
 *    Get the x axis minimum .
 *
*/
  public double getMinX(){return  xaxis.minimum; }

/**
 *    Set the x axis maximum.
 *
 *    @param max    Maximum value.
*/
  public void setMaxX(double max){ // Francisco Esquembre (March 2000)
          if (xaxis.isManualRange() ){
             if(max< xaxis.minimum)xaxis.setManualRange(true,  max-1, max);
                 else xaxis.setManualRange(true,  xaxis.minimum, max);
          }else{
              xaxis.resetRange();  // just to make sure that the range is correct
          }
          if(autoRefresh) repaint();
  }

/**
 *    Get the x axis maximum .
 *
*/
  public double getMaxX(){return  xaxis.maximum;}

/**
 *    Set the y axis minimum and maximum values.
 *
 *    @param min    Minumum value.
 *    @param max    Maximum value.
*/
  public void setMinMaxY(double min,double max){
      if (yaxis.isManualRange() ){
          yaxis.setManualRange(true,  min, max);
      }else{
          yaxis.resetRange();  // just to make sure that the range is correct
      }
      if(autoRefresh)repaint();
  }

/**
 *    Set the y axis minimum.
 *
 *    @param min    Minumum value.
*/
  public void setMinY(double min){ // Francisco Esquembre (March 2000)
          if (yaxis.isManualRange() ){
            if(min> yaxis.maximum)yaxis.setManualRange(true,  min, min+1);
              else yaxis.setManualRange(true,  min, yaxis.maximum);
          }else{
              yaxis.resetRange();  // just to make sure that the range is correct
          }
          if(autoRefresh) repaint();
  }


/**
 *    Get the y axis minimum .
 *
*/
  public double getMinY(){return  yaxis.minimum;}

/**
 *    Set the y axis maximum.
 *
 *    @param max    Maximum value.
*/
  public void setMaxY(double max){ // Francisco Esquembre (March 2000)
          if (yaxis.isManualRange() ){
            if(max< yaxis.minimum)yaxis.setManualRange(true,  max-1, max);
              else yaxis.setManualRange(true,  yaxis.minimum, max);
          }else{
              yaxis.resetRange();  // just to make sure that the range is correct
          }
          if(autoRefresh) repaint();
  }

/**
 *    Get the y axis maximum .
 *
*/
  public double getMaxY(){return  yaxis.maximum;}

  private DataSet getDataSetFromSID(int sid){
      Series series;
      int n=dataSetSeries.size();
      for(int i=0; i<n; i++){
          series=(Series)dataSetSeries.elementAt(i);
          if(sid==series.sid ){
              return series.data;
          }
      }
      return null;
  }

/**   Get the function object from the id.
  *
  *   @param id  The id of the function.
  *
*/
  public MathFunction getFunction(int id){
     MathFunction f=null;
     Vector v;
     synchronized(functions){v=(Vector)functions.clone();}
     for( Enumeration e=v.elements(); e.hasMoreElements();){
         f= (MathFunction) e.nextElement();
         if(f.hashCode()==id){
           return f;
         }
     }
     return null;
  }

/**   Get the Complex function object from the id.
  *
  *   @param id  The id of the function.
  *
*/
  public ComplexFunction getCFunction(int id){
     ComplexFunction f=null;
     Vector v;
     synchronized(cfunctions){v=(Vector)cfunctions.clone();}
     for( Enumeration e=v.elements(); e.hasMoreElements();){
         f= (ComplexFunction) e.nextElement();
         if(f.hashCode()==id){
           return f;
         }
     }
     return null;
  }

/**   Get the function id from its creation order.
  *
  *   @param pos  The order of creation of the function.
  *
*/
  public int getFunctionId(int pos){ // Francisco Esquembre (March 2000)
     Vector v;
     synchronized(functions){v=(Vector)functions.clone();}
     if ((pos<1) || (pos>v.size())) return 0;
     MathFunction f= (MathFunction) v.elementAt(pos-1);
     return f.hashCode();
  }


/**   Get the complex function id from its creation order.
  *
  *   @param pos  The order of creation of the function.
  *
*/
  public int getCFunctionId(int pos){ // Francisco Esquembre (March 2000)
     Vector v;
     synchronized(cfunctions){v=(Vector)cfunctions.clone();}
     if ((pos<1) || (pos>v.size())) return 0;
     ComplexFunction f= (ComplexFunction) v.elementAt(pos-1);
     return f.hashCode();
  }


  private Series getSeriesFromSID(int sid){
      Series series;
      for( Enumeration e=dataSetSeries.elements(); e.hasMoreElements();){
         series=(Series) e.nextElement();
         if(sid==series.sid ){
              return series;
         }
      }
      return null;
  }

  public String getTitle(){return titleStr;}

  public void setTitle(String str){
      titleStr=str;
      title.setText(str);
      if(autoRefresh) repaint();
  }

   public void setTimeDisplay(boolean show){
       timeDisplay=show;
       if(timeDisplay)parentSApplet.clock.addClockListener(this);
       else parentSApplet.clock.removeClockListener(this);
   }

  /*
  public int setTrackDrag(boolean track, int numpts){
      if(track){
          trailThing=new TrailThing(parentSApplet, this,1);
          trailThing.trailSize=numpts;
          trackDrag=true;
          return trailThing.hashCode();
      }else{
          trailThing=null;
          trackDrag=false;
          return 0;
      }
  }
  */

  public Color getTitleColor(){return titleColor;} // Francisco Esquembre (March 2000)
  public void setTitleColor(Color c){ // Francisco Esquembre (March 2000)
      titleColor = c;
      // text.setColor (c); This wouldbe the nicest way, but we'll do it directly
      if(autoRefresh) repaint();
  }

  int getIDFromDataSet(DataSet dset){
      Series series;
      int n=dataSetSeries.size();
      for(int i=0; i<n; i++){
          series=(Series)dataSetSeries.elementAt(i);
          if(dset==series.data ){
              return series.hashCode();
          }
      }
      return 0;
  }

  public int getIDFromSID(int val){
      Series series;
      int n=dataSetSeries.size();
      for(int i=0; i<n; i++){
          series=(Series)dataSetSeries.elementAt(i);
          if(val==series.sid ){
              return series.hashCode();
          }
      }
      return 0;
  }

  public int getRegressionID(int sid, int start, int end){
      Series series=createSeries( sid );  // this will create an empty series if it does not exist.
      return series.getRegressionID(start, end);
  }

/**   set the color of the object from the id.
  *
  *   @param id  The id of the function.
  *   @param c  The color
  *
*/
  public void setObjectColor(int id, Color c){
     MathFunction f=null;
     Vector v;
     if(id==0 || id==this.hashCode()){
          setDataBackground(c);
          setGraphBackground( c);
         return;
     }
     synchronized(functions){v=(Vector)functions.clone();}
     for( Enumeration e=v.elements(); e.hasMoreElements();){
         f= (MathFunction) e.nextElement();
         if(f.hashCode()==id){
           f.color=c;
           if(autoRefresh) repaint();
            ;
         }
     }
     Series s=null;
     for( Enumeration e=dataSetSeries.elements(); e.hasMoreElements();){
         s= (Series) e.nextElement();
         if(s.hashCode()==id){
             s.data.markercolor = c;
             s.data.linecolor = c;
             if(autoRefresh) repaint();
             return ;
         }
     }
     synchronized(things){v=(Vector)things.clone();}
        for( Enumeration e=v.elements(); e.hasMoreElements();){
            Thing t= (Thing) e.nextElement();
             if(t.hashCode()==id){
               t.setColor(c);
               if(autoRefresh) repaint();
              return ;
            }
    }


  }

  public void setOwner(SApplet owner){parentSApplet=owner;};
  public SApplet getOwner(){return parentSApplet;}
  public int getPixWidth(){
      return getSize().width;
  }
  public int getPixHeight(){
      return getSize().height;
  }

  /*
  public int[] getBorders(){
      int[] b=new int[4];
      System.arraycopy(borders, 0, b, 0, 4);
      return b;
  }
  public void setBorders(int[] b){
    borders=b;
    if(autoRefresh) repaint();
  }
  public int getBorders(int i){return borders[i];}
  public void setBorders(int i, int val){
    borders[i]=val;
    if(autoRefresh) repaint();
  }
  */

// General Purpose Methods

/**
 *    Delete all functions and all complex functions.
 *
*/
  synchronized public void deleteAllFunctions(){
      Vector v;
      synchronized(functions){v=(Vector)functions.clone(); functions.removeAllElements();}
      for(Enumeration e=v.elements() ; e.hasMoreElements(); ){
          MathFunction f=(MathFunction)e.nextElement();
          parentSApplet.removeDataSource(f.hashCode());
          if(f.explicitTime)parentSApplet.clock.removeClockListener(f);
      }
      synchronized(cfunctions){v=(Vector)cfunctions.clone(); cfunctions.removeAllElements();}
      for(Enumeration e=v.elements() ; e.hasMoreElements(); ){
          ComplexFunction f=(ComplexFunction)e.nextElement();
          parentSApplet.removeDataSource(f.hashCode());
          if(f.explicitTime)parentSApplet.clock.removeClockListener(f);
      }
      //if(parentSApplet!=null) parentSApplet.cleanupDataConnections();
      //if(autoRefresh) repaint();
      synchronized(delayLock){newData=true; delayLock.notify(); }
  }

/**
 *    Delete a function or a complex function from the plot.
 *
 *    @param id   The id of the function;
*/
  synchronized public void deleteObject(int id){
    if(deleteThing(id)) return;  // object was in the things array
    if(deleteFunction(id)) return ;
    deleteSeries(id);
  }

  private boolean deleteThing(int id){
     Thing t=null;
     t=getThing(id);
     if(t==null) return false;
     if(parentSApplet!=null)parentSApplet.stopClock();
     things.removeElement(t);
     if(parentSApplet!=null){
         parentSApplet.removeDataSource(t.hashCode());
         parentSApplet.removeDataListener(t.hashCode());
         if(t instanceof SStepable)parentSApplet.clock.removeClockListener((SStepable)t);
         parentSApplet.cleanupDataConnections();
     }
     if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
     return true;
  }

/**
 *    Delete a function or a complex function from the plot.
 *
 *    @param id   The id of the function;
*/
  synchronized public boolean deleteFunction(int id){
      boolean functionFound=false;
      synchronized(functions){
      for(Enumeration e=functions.elements() ; e.hasMoreElements(); ){
          MathFunction f=(MathFunction)e.nextElement();
          if (f.hashCode()==id){
             functionFound=true;
             // SApplet.dataSources.remove(Integer.toString(f.hashCode()));
              parentSApplet.removeDataSource(f.hashCode());
              functions.removeElement(f);
              if(f.explicitTime)parentSApplet.clock.removeClockListener(f);
              break;
          }
      }}

      synchronized(cfunctions){
      for(Enumeration e=cfunctions.elements() ; e.hasMoreElements(); ){
          ComplexFunction f=(ComplexFunction)e.nextElement();
          if (f.hashCode()==id){
              functionFound=true;
             // SApplet.dataSources.remove(Integer.toString(f.hashCode()));
              parentSApplet.removeDataSource(f.hashCode());
              cfunctions.removeElement(f);
              if(f.explicitTime)parentSApplet.clock.removeClockListener(f);
              break;
          }
      }}

      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
      return functionFound;
  }

/**
 *    Delete all series from the graph.
*/
 synchronized public void deleteAllSeries(){
      clearAllThings();
      for(Enumeration e=dataSetSeries.elements() ; e.hasMoreElements(); ){
          Series s=(Series)e.nextElement();
          if(s.regression!=null)SApplet.dataSources.remove(Integer.toString(s.regression.hashCode()));
          SApplet.dataSources.remove(Integer.toString(s.hashCode()));
       }
      dataSetSeries.removeAllElements();
      detachDataSets();
      if(parentSApplet!=null) parentSApplet.cleanupDataConnections();
     // defaultData=null;
     // makeDefaultData();
      if(!xaxis.isManualRange()) setMinMaxX(0,1);
      if(!yaxis.isManualRange()) setMinMaxY(0,1);
      //if(autoRefresh) repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }

/**   Remove any Data that is not part of a series. Used to get grid of field lines, contour lines, etc
  *
  *
*/
  public void deleteAllNonSeriesData(){
      for(Enumeration e=dataset.elements() ; e.hasMoreElements(); ){  // check all the data sets in the graph.
          DataSet dset=(DataSet)e.nextElement();
          int id=this.getIDFromDataSet(dset);  // return zero if the id does not dataset does not exist in any series.
          if(id==0) detachDataSet(dset);
      }
      if (autoRefresh) repaint();
  }

/**
 *    Delete one series from the graph.
 *
 *    @param id    The id of the series to remove.
 *
*/
  public void deleteSeries(int id){
      deleteSeries(id, true);
  }
  public void deleteSeries(int id, boolean willRepaint ){
      Series series;
      int n=dataSetSeries.size();
      for(int i=0; i<n; i++){
          series=(Series)dataSetSeries.elementAt(i);
          if(id==series.sid){
              if(series.regression!=null)SApplet.dataSources.remove(Integer.toString(series.regression.hashCode()));
              SApplet.dataSources.remove(Integer.toString(series.hashCode()));
              dataSetSeries.removeElement(series);
              detachDataSet(series.data);
              break;
          }
      }
      if(dataSetSeries.size()==0){
          detachDataSets();
       //   defaultData=null;
       //   makeDefaultData();
      }
      if(parentSApplet!=null) parentSApplet.cleanupDataConnections();
      if (willRepaint && autoRefresh) repaint();
  }

  public void setFunctionRange(int id, double xmin, double xmax, int n  ){
      MathFunction f=null;
      for(Enumeration e=functions.elements() ; e.hasMoreElements(); ){
          f=(MathFunction)e.nextElement();
          if (f.hashCode()==id){
              f.setFunctionRange(xmin,xmax,n);
          }
      }
      ComplexFunction cf=null;
      for(Enumeration e=cfunctions.elements() ; e.hasMoreElements(); ){
          cf=(ComplexFunction)e.nextElement();
          if (cf.hashCode()==id){
              cf.setFunctionRange(xmin,xmax);
          }
      }
      if(parentSApplet!=null){
        if(parentSApplet.destroyed) return;
        parentSApplet.updateDataConnection(f.hashCode() );
      }
      //if(parentSApplet!=null)parentSApplet.updateDataConnections();
      synchronized(delayLock){newData=true; delayLock.notify(); }
  }

  public void setFunctionClip(int id, double min, double max){
      MathFunction f=null;
      for(Enumeration e=functions.elements() ; e.hasMoreElements(); ){
          f=(MathFunction)e.nextElement();
          if (f.hashCode()==id){
              f.setFunctionClip(min,max);
          }
      }
      if(parentSApplet!=null){
        if(parentSApplet.destroyed) return;
        parentSApplet.updateDataConnection(f.hashCode() );
      }
      //if(parentSApplet!=null)parentSApplet.updateDataConnections();
      synchronized(delayLock){newData=true; delayLock.notify(); }
  }

  public void setYScaleFromFunction(int id){
      for(Enumeration e=functions.elements() ; e.hasMoreElements(); ){
          MathFunction f=(MathFunction)e.nextElement();
          if (f.hashCode()==id){
              this.setMinMaxY(f.ymin,f.ymax);
          }
      }
      for(Enumeration e=cfunctions.elements() ; e.hasMoreElements(); ){
          ComplexFunction f=(ComplexFunction)e.nextElement();
          if (f.hashCode()==id){
              this.setMinMaxY(f.ymin,f.ymax);
          }
      }

      synchronized(delayLock){newData=true; delayLock.notify(); }
  }


/**   Change the function that is being graphed without adding a new function.
  *
  *   @param str  The function f(x).
  *
*/
  public synchronized boolean setFunctionString(int id, String str ){
     MathFunction fun=getFunction(id);
     if(fun==null) return false;
     if(!fun.setString(str)) return false;
     fun.setScale();
     if(parentSApplet!=null){
       if(parentSApplet.destroyed) return false;
       parentSApplet.updateDataConnection(fun.hashCode());
     }
     //if(parentSApplet!=null)parentSApplet.updateDataConnections();
     synchronized(delayLock){newData=true; delayLock.notify(); }
     return true;
  }

/**   Change the complex function that is being graphed without adding a new function.
  *
  *   @param str  The function f(x).
  *
*/
  public synchronized boolean setCFunctionStrings(int id, String strRe, String strIm ){
     ComplexFunction fun=getCFunction(id);
     if(fun==null) return false;
     if(!fun.setStringRe(strRe)) return false;
     if(!fun.setStringIm(strIm)) return false;
     fun.setScale();
     if(parentSApplet!=null){
       if(parentSApplet.destroyed) return false;
       parentSApplet.updateDataConnection(fun.hashCode());
     }
     synchronized(delayLock){newData=true; delayLock.notify(); }
     return true;
  }

/**   Get the function that is being graphed.
  *
  *   @param id      The id of the function.
  *   @return String The function string.
  *
*/
  public  String getFunctionString(int id){
     MathFunction fun=getFunction(id);
     if(fun==null) return "";
     return fun.getFunctionStr();
  }

/**   Get the re part of the complex function that is being graphed.
  *
  *   @param id      The id of the function.
  *   @return String The function string.
  *
*/
  public  String getReFunctionString(int id){
     ComplexFunction fun=getCFunction(id);
     if(fun==null) return "";
     return fun.getFunctionStrRe();
  }

  public  String getImFunctionString(int id){
     ComplexFunction fun=getCFunction(id);
     if(fun==null) return "";
     return fun.getFunctionStrIm();
  }

/**   Change the function's independent variable.
  *
  *   @param str  The new variable
  *
*/
  public synchronized boolean setFunctionVariable(int id, String str ){
     MathFunction fun=getFunction(id);
     if(fun==null) return false;
     fun.setVariable(str);
     return true;
  }

/**   Change a function and it's independent variable
  *
  *   @param var str  The function f(var).
  *
*/
  public synchronized boolean setFunction(int id, String var, String str ){ // Francisco Esquembre (March 2000)
     MathFunction fun=getFunction(id);
     if(fun==null) return false;
     if(!fun.setString(str)) return false;
     fun.setVariable(var);
     fun.setScale();
     if(parentSApplet!=null){
       if(parentSApplet.destroyed) return false;
       parentSApplet.updateDataConnection(fun.hashCode());
     }
     //if(parentSApplet!=null)parentSApplet.updateDataConnections();
     synchronized(delayLock){newData=true; delayLock.notify(); }
     return true;
  }

public Object clone(){
       SGraph g= new SGraph(parentSApplet);
       g.autoRefresh=autoRefresh;
       g.setSampleData(false);
       g.setTitle(titleStr);
       g.setTitleColor(titleColor); // Francisco Esquembre (March 2000)
       g.setAutoscaleX(isAutoscaleX());
       g.setAutoscaleY(isAutoscaleY());
       g.setLabelX(labelX,labelXTitleColor); // Francisco Esquembre (March 2000)
       g.setLabelY(labelY,labelYTitleColor); // Francisco Esquembre (March 2000)
       g.setLabelXColor(labelXColor); // Francisco Esquembre (March 2000)
       g.setLabelYColor(labelYColor); // Francisco Esquembre (March 2000)
       g.setMinMaxX(getMinX(),getMaxX());
       g.setMinMaxY(getMinY(),getMaxY());
       g.defaultMarkerScale=defaultMarkerScale;
       g.drawgrid=isDrawGrid();
       g.drawzero=isDrawZero();
       g.setEnableMouse(enableMouse);
       g.setEnableClone(false);
       g.setEnableCoordDisplay(enableCoordDisplay);
       g.deleteAllSeries();
       for(int i=0; i<dataSetSeries.size(); i++){
         Series s=(Series)dataSetSeries.elementAt(i);
         g.addDataSeries(s);
         g.setLastPointMarker(s.sid, s.enableLPCursor);
       }
       g.deleteAllFunctions();

       MathFunction f;
       for( Enumeration e=functions.elements(); e.hasMoreElements();){
            f= (MathFunction) e.nextElement();
            int id=g.addFunction(f.variable, f.getFunctionStr() );
            g.setFunctionRange(id,f.xmin,f.xmax,f.numPts);
            g.setFunctionClip(id,f.minClip,f.maxClip);
            g.setObjectColor(id,f.color);
        }

       ComplexFunction cf;
       for( Enumeration e=cfunctions.elements(); e.hasMoreElements();){
            cf= (ComplexFunction) e.nextElement();
            int id=g.addCFunction(cf.variable, cf.getFunctionStrRe(),cf.getFunctionStrRe(),cf.centered);
            g.setFunctionRange(id,cf.xmin,cf.xmax,400);
            g.setObjectColor(id,cf.color);
        }
       // make sure the clone has EXACTLY the same scale.
       boolean tempx=g.xaxis.manualRange;
       g.xaxis.manualRange=true;
       boolean tempy=g.yaxis.manualRange;
       g.yaxis.manualRange=true;
       g.setMinMaxX(xaxis.minimum,xaxis.maximum);
       g.setMinMaxY(yaxis.minimum,yaxis.maximum);
       g.xaxis.manualRange=tempx;
       g.yaxis.manualRange=tempy;
       return g;
}
/**
 *    Create an empty series so that styles, color, and other properites can be set.
 *    Return the current series if it exists
 *
*/
public synchronized Series createSeries(int sid ){
      Series series=getSeriesFromSID(sid);
      if(series==null) series=makeEmptySeries(sid);    // series does not exist so make it.
      return series;
}

/**
 *    Clear the data from all series. Series continues to exist so
 *    that data can be added without resetting the style
 *
*/
  public void clearAllData(){
      Series series;
      int n=dataSetSeries.size();
      for(int i=0; i<n; i++){
          series=(Series)dataSetSeries.elementAt(i);
          series.data.deleteData();  // data object still exists
      }
      //makeDefaultData();
      //if(autoRefresh) repaint();
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }

/**
 *    Clear the data from a series.  Series properties such as color and style remain unchanged.
 *
 *    @param s    Series ID
*/
    public void clearSeries(int s){ clearSeriesData(s);}

/**
 *    Clear the all data from a graph.  Series properties such as color and style remain unchanged.
 *
*/
    public void clearAllSeries(){
      Series series;
      int n=dataSetSeries.size();
      for(int i=0; i<n; i++){
          series=(Series)dataSetSeries.elementAt(i);
          series.data.deleteData();  // data object still exists
          series.setOwner(parentSApplet);
      }
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
    }

/**
 *    Delete the data from a series. Series continues to exist so
 *    that data can be added without resetting the style
 *
 *    @param id    The id of the series.
 *
*/
  public void clearSeriesData(int sid){
      Series series;
      int n=dataSetSeries.size();
      for(int i=0; i<n; i++){
          series=(Series)dataSetSeries.elementAt(i);
          if(sid==series.sid && !series.autoReplace){
              series.data.deleteData();  // data object still exists
              series.setOwner(parentSApplet);
              //if(autoRefresh) repaint();
              if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
              break;
          }
      }
  }

/**
 *  This method makes some last minute scale adjustments.  For example,
 *  it is used to rescale the graph if analytic functions are present in SGraph.
*/
    public void adjustScale(){
        if(yaxis.isManualRange()) return;
        double ymin=yaxis.minimum, ymax=yaxis.maximum;
        MathFunction f;
        Vector v;
        synchronized(functions){v=(Vector)functions.clone();}
        for( Enumeration e=v.elements(); e.hasMoreElements();){
            f= (MathFunction) e.nextElement();
            ymin=Math.min(f.ymin,ymin);
            ymax=Math.max(f.ymax,ymax);
        }
        ComplexFunction cf;
        synchronized(cfunctions){v=(Vector)cfunctions.clone();}
        for( Enumeration e=v.elements(); e.hasMoreElements();){
            cf= (ComplexFunction) e.nextElement();
            ymin=Math.min(cf.ymin,ymin);
            ymax=Math.max(cf.ymax,ymax);
        }
        if(ymin==ymax){ymin-=0.5; ymax+=0.5;}
        yaxis.minimum=ymin;
        yaxis.maximum=ymax;
        yaxis.calculateGridLabels();
    }

/**
 *  A hook into the Graph2D.paint method. This is called before
 *  the data is drawn but after the axis in Graph2D.  It is used
 *  to paint analytic functions in SGraph.
 *  The rectangle passed is the dimension of
 *  the data window.
 *  @params g Graphics state
 *  @params r Rectangle containing the data
 */
    public void paintFunctions( Graphics g, Rectangle r) {
        MathFunction f;
        //Vector v;
        synchronized(functions){
        for( Enumeration e=functions.elements(); e.hasMoreElements();){
            f= (MathFunction) e.nextElement();
            f.paint(g,r);
        } }
        ComplexFunction cf;
        synchronized(cfunctions){
        for( Enumeration e=cfunctions.elements(); e.hasMoreElements();){
            cf= (ComplexFunction) e.nextElement();
            cf.paint(g,r);
        } }
    }

    public void repaint(){
    	if(delayLock==null) {newData=true; return;}
    synchronized(delayLock){newData=true; delayLock.notify(); }
    }

/**
 *  Start the paint thread.
 */
    public void startPaintThread(){
      if(delayThread!=null)return;
      interrupted=false;
      delayThread = new Thread(this);
      newData=true;
      delayThread.start();
      //System.out.println("new paint thread");
    }

 /**
 *  Destroy the paint thread.
 */
    public void destroy(){
      interrupted=true;
      if(delayThread==null)return;
      synchronized(delayLock){newData=true; delayLock.notify(); }
      Thread temp=delayThread;
      if(temp!=null)try{
        temp.interrupt();
        temp.join(100);
      }catch (Exception e){}
      // delayThread.stop();
      delayThread=null;
    }

    // this method will paint the screen but not too fast.
    public void run(){
        //System.out.println("Paintthread start");
        while (!interrupted){
          synchronized(delayLock){
              if(!newData)try{
                  delayLock.wait();
              }catch(InterruptedException ie){return;}
              newData=false;
              if(!interrupted)paintOffScreen();
          }
          if(!interrupted)try{Thread.sleep(50);}catch (InterruptedException ie){return;}
        }
        //System.out.println("Paintthread stop");
    }

 /**
 *  Paint the graph offscreen into an image and then blast the image onto the screen
 *  @params g Graphics state
 */
    public void paintOffScreen( Graphics g) {
        if(parentSApplet!=null && parentSApplet.destroyed) return;
        int w = getSize().width;
        int h = getSize().height;
        if(w<10 || h<10){
             g.fillRect(0,0,w,h);
             return;
        }
        if( offScreenImage==null
          || offScreenImage.getWidth(this)!=w
          || offScreenImage.getHeight(this)!=h )offScreenImage = createImage(w,h);
        if( offScreenImage==null) return;
        synchronized(offScreenImage){
          Graphics osg = offScreenImage.getGraphics();// a graphics context for the  off screen image
          if(parentSApplet!=null && parentSApplet.destroyed) return;
          update(osg);
          g.drawImage(offScreenImage, 0, 0, w, h, this);
          if(mouseDrag)this.drawCoords(mouseX,mouseY);
          osg.dispose();
          Toolkit tk=Toolkit.getDefaultToolkit();
          tk.sync();
        }
    }
    public void paintOffScreen(){
        Graphics g=getGraphics();
        if(g==null) {repaint(); return;}
        paintOffScreen(g);
        g.dispose();
    }

/**
 *  A hook into the Graph2D.paint method. This is called before
 *  the data is drawn but after the axis.
 *  The rectangle passed is the dimension of
 *  the data window.
 *  @params g Graphics state
 *  @params r Rectangle containing the data
 */
    public void paintBeforeData( Graphics g, Rectangle r) {
       if(!hasGraphThing) return;
        Series s;
        Vector v;
        synchronized(things){v=(Vector)things.clone();}
        for( Enumeration e=v.elements(); e.hasMoreElements();){
            Thing t= (Thing) e.nextElement();
            if(t instanceof GraphThing) return;
            t.paint(g);
        }
    }

 /**
 *  A hook into the Graph2D.paint method. This is called before
 *  the data is drawn but after the axis in Graph2D.  It is used
 *  to paint analytic functions and geometric objects in SGraph.
 *  The rectangle passed is the dimension of
 *  the data window.
 *  @params g Graphics state
 *  @params r Rectangle containing the data
 */
    public void paintLast( Graphics g, Rectangle r) {
        Series s;
        Vector v;
        boolean paintOn=false;
        synchronized(things){v=(Vector)things.clone();}
        for( Enumeration e=v.elements(); e.hasMoreElements();){
            Thing t= (Thing) e.nextElement();
            if(t instanceof GraphThing)paintOn=true;
            if(! hasGraphThing || paintOn) t.paint(g);
        }
        for( Enumeration e=dataSetSeries.elements(); e.hasMoreElements();){
            s= (Series) e.nextElement();
            s.paintLastPoint(g,r);
        }
        if(titleStr!=null){
            g.setColor(titleColor); // Francisco Esquembre (March 2000) See remark above about the 'nicest' way
            title.draw(g,r.x+r.width/2,r.y+Math.max(10+r.height/20,16),TextLine.CENTER);

        }
        if (timeDisplay)paintTime( g, r);
    }


    void paintTime(Graphics g, Rectangle r ){
        if (!timeDisplay || parentSApplet==null )return;
        Rectangle rc= new Rectangle(0,0,getBounds().width,getBounds().height);
        g.setClip(rc);
        g.setColor(Color.black);
        Font f=g.getFont();
        g.setFont(boldFont);
        String tStr= new Format("%7.4g").form(SUtil.chop(parentSApplet.clock.getTime(),1.0e-12));
        g.drawString(label_time + tStr, 10, getBounds().height-5);
        g.setFont(f);
    }

/**
 *    Get the pixel value corresponding to an x value.
 *
 *    @param x    The value along the x axis.
 *    @return     The pixel value inside the canvas.
 *
*/
  public int pixFromX(double x){
      int pix;
      try{pix=xaxis.getInteger(x);}catch (Exception e){ return 0; }
      return pix;
  }
  public int pixFromY(double y){
      int pix;
      try{pix=yaxis.getInteger(y);}catch (Exception e){ return 0; }
      return pix;
  }
/**
 *    Get the value on the x axis from the pixel value.
 *
 *    @param x    The pixel inside the canvas.
 *    @return     The corresponding value along the x axis.
 *
*/
  public double xFromPix(int x){
      double val;
      try{val=xaxis.getDouble(x);}catch (Exception e){ return 0; }
      return val;
  }
  public double yFromPix(int y){
      double val;
      try{val=yaxis.getDouble(y);}catch (Exception e){ return 0; }
      return val;
  }

/**
 *    Add a data set to the graph.  The data does not have an ID and cannot be accessed as a series.
 *
 *    @param points   The data.
 *    @param np       The number of points.
 *
*/
  public  synchronized DataSet addDataSet(double[] points, int np){
       DataSet data=loadDataSet(points,np);
       xaxis.attachDataSet(data);
       yaxis.attachDataSet(data);
       return data;
    }

  private  synchronized DataSet addDataSeries(Series s){
       DataSet data=null;
       addData( s.sid, s.getX(), s.getY() , s.getNumpts() );
       data=getDataSetFromSID(s.sid);
       if(data==null){
           System.out.println("Error: DataSet not created in SGraph.addDataSetSeries.");
           return null;
       }
       data.markercolor=s.getDataSet().markercolor;
       data.linestyle=s.getDataSet().linestyle;
       data.linecolor=s.getDataSet().linecolor;
       data.markercolor=s.getDataSet().markercolor;
       data.markerscale=s.getDataSet().markerscale;
       data.marker=s.getDataSet().marker;
       data.legend(s.getDataSet().getLegend_ix(),s.getDataSet().getLegend_iy(),s.getDataSet().getLegend());
       data.legendColor(s.getDataSet().linecolor);
       return data;
    }

/**
 *    Add a datum to the graph.  The data is attached to series.
 *
 *    @param id   The series id.
 *    @param x    The x value of the datum.
 *    @param y    The y value of the datum.
 *
*/
  public synchronized void addDatum(int sid, double x, double y ){
      Series series=getSeriesFromSID(sid);
      dPoint[0]=x; dPoint[1]=y;
      if(series==null){
          series=attachArray(sid,dPoint);
      }else{  // series exists
          try{
              /*if(series.data.dataPoints()==0){
                removeDefaultData();
              }  */
              if(series.addRepeatedDatum) series.data.append(dPoint,1);
              else{ // check to see if the datum is different from the last point.
                 double[] datum=series.getLastPoint();
                 if(datum==null || datum[0]!=x || datum[1]!=y){
                     series.data.append(dPoint,1);
                 }
              }
          }catch (Exception e){
              System.out.println("Error appending Data!");
          }
      }
      if(!autoRefresh) return;
      synchronized(delayLock){newData=true; delayLock.notify(); }
  }



  public synchronized void addDatum(SDataSource ds, int id, double x, double y ){
      addDatum(id,x, y);
  }

/**
 *    Remove all dragable objects for the graph.
 *
 *
*/
public void clearAllThings(){
    Vector v;
    synchronized(things){v=(Vector)things.clone(); things.removeAllElements();}
    hasGraphThing=false;
    Thing t;
    for( Enumeration e=v.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         parentSApplet.removeDataListener(t.hashCode() );
         parentSApplet.removeDataSource(t.hashCode() );
         if(t instanceof SStepable)parentSApplet.clock.removeClockListener((SStepable)t);
    }
}

/**
 *    Add a dragable cursor
 *
 *    @param SApplet owner   the SApplet that created the cursor.
 *    @param int diameter   the diameter
 *    @param double x    the initial x position
 *    @param double y    the initial y position
 *
 * @return the object id
 *
*/
  public int addCursor(SApplet owner, int diameter, double x, double y){
       Thing t=new MarkerThing(owner, this, diameter, x,y);
       things.addElement(t);
       if(autoRefresh)repaint();
       return t.hashCode();
  }

/**
 *    Add a connector line between two objects
 *
 *    @param SApplet owner   the SApplet that created the cursor.
 *    @param int id1   the first object
 *    @param int id2   the second object
 *
 * @return the object id
*/
   public int addConnectorLine(SApplet owner, int id1, int id2){
         Thing t1=getThing(id1);
         Thing t2=getThing(id2);
         Thing t=new ConnectorLine(owner,this,t1,t2);
         things.addElement(t);
         if(autoRefresh)repaint();
         return t.hashCode();
    }


    /**
     * Swap the drawing order on the screen.
     *
     * @param              id1 The first id of a screen object.
     * @param              id2 The second id of a screen object.
     * @return             True if successful.
     */
    public synchronized boolean swapZOrder(int id1, int id2) {
        Thing t1 = getThing(id1);
        Thing t2 = getThing(id2);

        if ((t1 == null) || (t2 == null)) {
            return false;
        }

        int index1 = things.indexOf(t1);
        int index2 = things.indexOf(t2);

        things.removeElementAt(index1);
        things.insertElementAt(t2, index1);
        things.removeElementAt(index2);
        things.insertElementAt(t1, index2);

        return true;
    }

/**
 *    Add a drawable thing.
 *
 *    @param  t   the ting to be added.
 *
 *    @ return  int id of the thing
*/
  public int addThing(Thing t){
       if(t instanceof GraphThing) hasGraphThing=true;
       things.addElement(t);
       if(autoRefresh)repaint();
       return t.hashCode();
  }

/**
 *    Add data to the graph using arrays.  The data is attached to series.
 *
 *    @param id   The series id.
 *    @param x    The x array of the data.
 *    @param y    The y array of the data.
 *
*/
  public synchronized void addData(int sid, double[] x, double[] y ){
      addData(sid, x, y, x.length);
      return ;
  }

  public synchronized void addData(SDataSource ds, int sid, double[] x, double[] y ){
      // replace existing data if it exists.
      int i,j,np;
      if(x==null || y==null||(x.length!=y.length)||x.length==0){
          if(getSeriesFromSID(sid)==null) makeEmptySeries(sid);    // series does not exist so make it.
          return;
      }
      np=x.length;
      Series series=getSeriesFromSID(sid);
      double[] points= new double[2*np];
      for(i=j=0; i<np; i++,j+=2) {
            points[j] = x[i];
            points[j+1] =y[i];
      }
      if(series==null){
          series=attachArray(sid, points);
      }else{
          try{
              series.data.replace(points,np);
          }catch (Exception e){
              System.out.println("Error appending Data!");
          }
      }
    if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
    return;
  }
  public synchronized void addData(int sid, double[] x, double[] y, int num ){
      int i,j,np;
      if(x==null || y==null||(x.length!=y.length)||num==0){
          if(getSeriesFromSID(sid)==null) makeEmptySeries(sid);    // series does not exist so make it.
              return;
      }
      np=x.length;
      if(np>num)np=num;
      Series series=getSeriesFromSID(sid);
      double[] points= new double[2*np];
      for(i=j=0; i<np; i++,j+=2) {
            points[j] = x[i];
            points[j+1] =y[i];
      }
      if(series==null){
          series=attachArray(sid, points);
      }else{
          try{
              if(series.autoReplace){  // delete the old data and paint right away.
                  //series.data.deleteData();
                  //series.data.append(points,np);
                  series.data.replace(points,np);
                  synchronized(delayLock){newData=true; delayLock.notify(); }
                  return;
              }else series.data.append(points,np);
          }catch (Exception e){
              System.out.println("Error appending Data!");
          }
      }
    //if(autoRefresh) repaint();
    if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
    return;
  }

 /**   Add an analytic function to the graph.
  *
  *   @param str  The function f(x).
  *
*/
  public synchronized int addFunction(String indVar, String str ){
     MathFunction func=new MathFunction(indVar, str);
     synchronized(functions){
       functions.addElement(func);
       if(datarect!=null && datarect.width>20){
         Rectangle r=datarect;
         func.xmin=xFromPix(r.x); // start on left side of graph
         func.xmax=xFromPix(r.x+r.width-1); // right hand side of graph
         func.numPts=r.width; // right hand side of graph
         func.setScale();
       }else{
          func.xmin=-1;
          func.xmax=1;
          func.numPts=200;
          func.setScale();
       }
     }
     //if(autoRefresh) repaint();
     if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
     return func.hashCode();
  }

/**   Add an analytic function to the graph.
  *
  *   @param str  The function f(x).
  *
*/
  public synchronized int addVectorField(String strFx, String strFy, int gridSize ){
     VectorFieldThing field=new VectorFieldThing(strFx, strFy, gridSize);
     things.addElement(field);
     //if(autoRefresh)repaint();
     synchronized(delayLock){newData=true; delayLock.notify(); }
     return field.hashCode();
  }

/**   Add an analytic function to the graph.
  *
  *   @param str  The function f(x).
  *
*/
  public synchronized int addCFunction(String indVar, String strRe, String strIm, boolean centered ){
     ComplexFunction func=new ComplexFunction(indVar, strRe, strIm);
     func.centered=centered;
     synchronized(cfunctions){
       cfunctions.addElement(func);
       if(datarect!=null && datarect.width>20){
         Rectangle r=datarect;
         func.xmin=xFromPix(r.x); // start on left side of graph
         func.xmax=xFromPix(r.x+r.width-1); // right hand side of graph
         func.setScale();
       }else{
          func.xmin=-1;
          func.xmax=1;
          func.setScale();
       }
     }
     //if(autoRefresh) repaint();
     if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
     return func.hashCode();
  }

  public synchronized int addFunction(String str ){ return addFunction("x", str ); }

  public void loadFile(int series, String file) {
        boolean error=false;
        Applet applet=parentSApplet;
        if(applet==null) applet=edu.davidson.graphics.Util.getApplet(this);
        if(applet==null){
            System.out.println("File load failed. Applet not found.");
            return;
        }
        boolean temp=autoRefresh;
        autoRefresh=false;
        this.clearSeries(series);
        try {
            InputStream is = new URL(applet.getCodeBase(), file).openStream();
            readFile(series, is);
            is.close();
        } catch (Exception ex) {
            error=true;
        }
        if(error)try {
            InputStream is = new URL(applet.getDocumentBase(), file).openStream();
            readFile(series, is);
            is.close();
        } catch (Exception ex) {
            System.out.println("file load failed: " + ex.getMessage());
            return;
        }
        autoRefresh=temp;
        if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
  }
  void readFile(int series, InputStream is) throws IOException {
        double x;
        double y;
        StreamTokenizer st = new StreamTokenizer(is);
        st.eolIsSignificant(true);
        st.commentChar('#');
        scan:
        while (st.ttype != StreamTokenizer.TT_EOF) {
            switch (st.nextToken()) {
                default:
                    break scan;

                case StreamTokenizer.TT_EOL:
                    break;
                case StreamTokenizer.TT_WORD:
                    if ("series".equals(st.sval)) {
                    st.nextToken();
                    series =(int)st.nval;
                    this.clearSeries(series);
                    break;
                    }
                case StreamTokenizer.TT_NUMBER:
                    x =st.nval;
                    st.nextToken();
                    y =st.nval;
                    addDatum(series,x,y);
                    break;
            }
            while (st.ttype != StreamTokenizer.TT_EOL && st.ttype != StreamTokenizer.TT_EOF) st.nextToken();
        }
  }

  public int plotRegression(int sid, int start, int end){
      String str;
      double m=2,b=0.5;
      Series s=getSeriesFromSID(sid);
      double[] regVars=s.getSlopeIntercept(start, end);
      m=regVars[0];
      b=regVars[1];
      if(b>=0)str=""+m+"*x+"+b;
      else str=""+m+"*x-"+Math.abs(b);
      return addFunction("x", str );
  }

  public void drawCoords(Graphics g, int xPix,int yPix){
    if(!enableCoordDisplay)return;
    String msg=""+format.form(xFromPix(xPix))+ " , "+format.form(yFromPix(yPix));
    Rectangle r = getBounds();
    g.setColor(Color.yellow);
    FontMetrics fm=g.getFontMetrics(g.getFont());
    boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
    g.fillRect(0,r.height-20,boxWidth,20);
    g.setColor(Color.black);
    g.drawString(msg,10,r.height-5);
  }
  public void drawCoords(int xPix,int yPix){
    Graphics g=getGraphics();
    drawCoords(g,xPix,yPix);
    g.dispose();
  }

/**
 *    True if graph will show coordinates on drag.
 *
 *    @param em   Enable the coordinate display when the mouse is pressed.
 *
*/
   public boolean isEnableCoordDisplay(){return enableCoordDisplay;}

 /**
 *    Enable the graph to show coordinates on drag.
 *
 *    @param em   Enable the coordinate display when the mouse is pressed.
 *
*/
   public void setEnableCoordDisplay(boolean ecd){
      enableCoordDisplay=ecd;
   }


 /**
 *    True if graph will clone on a right mouse click.
 *
 *    @param em   Enable the coordinate display when the mouse is pressed.
 *
*/
   public boolean isEnableClone(){return enableClone;}

 /**
 *    Enable the clone feature on right mouse click.
 *
 *    @param em   Enable the coordinate display when the mouse is pressed.
 *
*/
   public void setEnableClone(boolean ec){
      enableClone=ec;
   }

 /**
 *    True if coordinates will display when the mouse is pressed.
 *
 *    @param em   Enable the coordinate display when the mouse is pressed.
 *
*/
   public boolean isEnableMouse(){return enableMouse;}

 /**
 *    Enable the graph to respond to mouse actions.
 *
 *    @param em   Enable the coordinates to display when the mouse is pressed.
 *
*/
   public void setEnableMouse(boolean em){
       if(enableMouse==em) return;
       enableMouse=em;
       if(enableMouse){
          addMouseMotionListener(mouseMotionAdapter=new SGraph_mouseMotionAdapter(this));
          addMouseListener(mouseAdapter=new SGraph_mouseAdapter(this));
      } else{
          removeMouseMotionListener(mouseMotionAdapter);
          removeMouseListener(mouseAdapter);
      }
   }

  public void setParentSApplet(SApplet p){parentSApplet=p;}

/**
 *    Change the display format for mouse actions.
 *
 *    @param str   The format for cooridinate display.
 *
*/
  public void setFormat(String str){format= new Format(str);}

  public String getFormat(){ return format.toString();}

  static  Color colorFromRadians(double phi){
        int offset=(int)(45+45*phi/Math.PI); // get offset in range 0 .. 90
        return colors[offset];
  }

  static  Color colorFromDegrees(int phi){
        if(phi>=0) return colors[(45+phi/4)%90];
        else return colors[89-(45-phi/4)%90];
  }

  private static void initColors()
    {
        int r,g,b;
        double pi=Math.PI;
        for(int i=0; i<91; i++)
        {
            b=(int)(255*Math.sin(pi*i/90)*Math.sin(pi*i/90));
            g=(int)(255*Math.sin(pi*i/90+pi/3)*Math.sin(pi*i/90+pi/3));
            r=(int)(255*Math.sin(pi*i/90+2*pi/3)*Math.sin(pi*i/90+2*pi/3));
            colors[i]=new Color(r,g,b);
        }
    }

  void sGraph_mousePressed(MouseEvent e) {
      if(enableClone && ( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){
           SGraphFrame graphFrame=new SGraphFrame((SGraph)this.clone());
           graphFrame.setSize(this.getSize().width,this.getSize().height);
           graphFrame.show();
      } else {
          mouseX=e.getX();
          mouseY=e.getY();
          mouseDrag=true;
          Graphics g=getGraphics();
          int n=things.size();
          for(int i=0; i<n; i++){
            Thing t=(Thing)things.elementAt(i);
            if(!t.noDrag && t.isInsideThing(mouseX,mouseY)){
                dragThing=t;
            }
          }
          if(dragThing!=null){
            sGraph_mouseDragged( e);
          }else if(sketchMode && trailThing!=null ){
              trailThing.clearTrail();
              parentSApplet.clearData(trailThing.hashCode());
              setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
              sGraph_mouseDragged( e);
          }
          g.dispose();
          drawCoords(mouseX,mouseY);
      }
   }
  void sGraph_mouseDragged(MouseEvent e){
      mouseX=e.getX();
      mouseY=e.getY();
      double x;
      double y;
      int maxPix=pixFromX(xaxis.maximum);
      int minPix=pixFromX(xaxis.minimum);
      if(mouseX<minPix) mouseX=minPix;
      else if(mouseX>maxPix-2) mouseX=maxPix-2;
      x=xFromPix(mouseX);
      minPix=pixFromY(yaxis.maximum);
      maxPix=pixFromY(yaxis.minimum);
      if(mouseY<minPix) mouseY=minPix;
      else if(mouseY>maxPix-2) mouseY=maxPix-2;
      y=yFromPix(mouseY);
      if(dragThing!=null){
      /*
         Graphics g=getGraphics();
         g.setXORMode(getBackground());
         dragThing.paint(g);
         dragThing.dragMe(x,y);
         dragThing.paint(g);
         parentSApplet.updateDataConnection(dragThing.hashCode());
         g.setPaintMode();
         g.dispose();*/
         dragThing.dragMe(x,y);
         if(parentSApplet!=null){
           if(parentSApplet.destroyed) return;
           parentSApplet.updateDataConnection(dragThing.hashCode());
         }
         if(parentSApplet!=null || !parentSApplet.clock.isRunning() )this.paintOffScreen();
      }else if(sketchMode && trailThing!=null){
          x=Math.min(x,xaxis.maximum);
          x=Math.max(x,xaxis.minimum);
          y=Math.min(y,yaxis.maximum);
          y=Math.max(y,yaxis.minimum);
          trailThing.incTrail(x,y);
          Graphics g=getGraphics();
          paintOffScreen(g);
          trailThing.paint(g);
          if(sketchImage!=null)g.drawImage(sketchImage,mouseX,mouseY-sketchImage.getHeight(this),this);
          g.dispose();
          if(parentSApplet.destroyed) return;
          parentSApplet.updateDataConnection(trailThing.hashCode());
      }

      drawCoords(mouseX,mouseY);
  }
  void sGraph_mouseReleased(MouseEvent e){
      mouseDrag=false;
      Rectangle r = getBounds();
      if(dragThing==null)repaint(0,r.height-20,boxWidth,20);
      else{
          //paintOffScreen();
          synchronized(delayLock){newData=true; delayLock.notify(); }
      }
      dragThing=null;
      boxWidth=0;  // reset the yellow message box.
      if(sketchMode && trailThing!=null){
        attachDataSet( trailThing.dataset);
        xaxis.attachDataSet(trailThing.dataset);
        yaxis.attachDataSet(trailThing.dataset);
        synchronized(delayLock){newData=true; delayLock.notify(); }
      }
      sGraph_mouseMoved( e);
  }

  public void sGraph_mouseEntered(MouseEvent e) {
    // if(this.sketchMode && this.sketchCursor!=null )setCursor(this.sketchCursor);
     if(this.sketchMode )setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
       else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    //int xPix=e.getX();
    //int yPix=e.getY();
    //boxWidth=0;  // reset the yellow message box.
    //drawCoords(xPix,yPix);
  }

  public void sGraph_mouseExited(MouseEvent e) {
     setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    // repaint();
  }

  public void sGraph_mouseMoved(MouseEvent e) {
    int xpt=e.getX();
    int ypt=e.getY();
    if(isInsideDragableThing(xpt,ypt)) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      else if(this.sketchMode )setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));   //  if(this.sketchMode && this.sketchCursor!=null)setCursor(this.sketchCursor);
       else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

// Private Methods

  private Series attachArray(int id, double[] points){
      DataSet data;
      if(points.length<2) return null;
      int np=(int)points.length/2;
      data = loadDataSet(points,np);   // this constructs dataset
      data.linestyle = 1;
      data.marker    = 3;
      data.markerscale = defaultMarkerScale;
      data.markercolor = new Color(0,0,255);
      data.linecolor = new Color(0,0,255);
      data.legendColor(Color.black);
      xaxis.attachDataSet(data);
      yaxis.attachDataSet(data);
      Series newSeries;
      dataSetSeries.addElement(newSeries=new Series(id,data) );
      //removeDefaultData();
      // repaint();
      // return data;
      return newSeries;
  }
 /*
  private void makeDefaultData(){
  // this dataset is when all actual data is cleared.
      if(defaultData!=null) return;
      double points[]={0.0, 0.0};
      defaultData = loadDataSet(points,1);   // this constructs dataset
      xaxis.attachDataSet(defaultData);
      yaxis.attachDataSet(defaultData);
  }

  private void removeDefaultData(){
      if(defaultData==null) return;
      detachDataSet(defaultData);
      defaultData=null;
  }
  */



  private Series makeEmptySeries(int sid){
      DataSet data;
      dPoint[0]=0; dPoint[1]=0;
      Series series=attachArray(sid,dPoint);
      data=series.getDataSet();
      data.deleteData();  // data object still exists
      return series;
  }
  private void makeSampleData(int np){
      DataSet data;
      double points[] = new double[2*np];
      int i=0, j=0;

      for(i=j=0; i<np; i++,j+=2) {
            points[j] = j-np;
            points[j+1] = 60000 * Math.pow( ((double)points[j]/(np-2) ), 2);
      }
      Series s=attachArray(1,points);
      data=s.getDataSet();
      data.legend(200,100,"sample data");
      if(autoRefresh)repaint();
  }
  private void buildMarkers(int w){
      // build makers of width w.  w=4 is reasonable  can be rescaled later.
      Markers markers;
      setMarkers(markers=new Markers());
      boolean[] draw= new boolean[5];
      int[] xMark= new int[5];
      int[] yMark= new int[5];
      // cross
      draw[0]=false; draw[1]=true; draw[2]=false; draw[3]=true;
      xMark[0]=w;    xMark[1]=-w;  xMark[2]=-w;   xMark[3]=w;
      yMark[0]=w;    yMark[1]=-w;  yMark[2]=w;    yMark[3]=-w;
      markers.AddMarker(1,4,draw,xMark,yMark);
      // square
      draw[0]=false; draw[1]=true; draw[2]=true; draw[3]=true; draw[4]=true;
      xMark[0]=w;    xMark[1]=-w;  xMark[2]=-w;  xMark[3]=w;   xMark[4]=w;
      yMark[0]=w;    yMark[1]=w;   yMark[2]=-w;  yMark[3]=-w;  yMark[4]=w;
      markers.AddMarker(2,5,draw,xMark,yMark);

      markers.AddMarker(3,w, Markers.TYPE_CIRCLE);
      markers.AddMarker(4,w, Markers.TYPE_SQUARE);

  }

    // inner class used for data connection to table.
  public class RegressionDataSource extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] regStrings= new String[]{"m","b","dm","db"};
    double[][] ds=new double[1][4];  // the datasource state variables x, dx;
    Series series=null;
    SApplet owner=null;
    int start=0, end=0;

    RegressionDataSource(Series s, int start, int end){ // Constructor
       this.owner=parentSApplet;
       this.series=s;
       this.start=start;
       this.end=end;
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
        ds[0][0]= 0;   // slope
        ds[0][1]= 0;   // intercept
        ds[0][2]= 0; // dslope
        ds[0][3]= 0; //dintercept
        int first=0, last=0;
        double sumx=0,sumy=0,sumxx=0,sumxy=0;
        double xval,yval;
        double[] vals=series.data.getData();
        int n=series.data.dataPoints();
        if(end<=start)last=n;  // do the whole series if start=end
          else last=Math.min(n,end);
        first=Math.max(1,start)-1;
        if((last-first)<2){  return ds;} // not enough data points to do a fit.
        for(int i=first;i<last; i++){
          xval=vals[2*i];
          yval=vals[2*i+1];
          sumx+=xval;
          sumy+=yval;
          sumxx+=xval*xval;
          sumxy+=xval*yval;
        }
        double delta=n*sumxx-sumx*sumx;
        if(delta==0){
            ds[0][0]=1.0e64;    // slope is infinite.
            ds[0][1]=1.0e64;    // intercept is infinite.
        }
        double m= (n*sumxy-sumx*sumy)/delta;   // slope
        double b= (sumxx*sumy-sumx*sumxy)/delta;     // intercept
        ds[0][0]= m;   // slope
        ds[0][1]= b;     // intercept
        if((last-first)<3){  return ds;} // not enough data to find uncertainty.
        double chisq=0,dy;
        for(int i=first;i<last; i++){
            dy=vals[2*i+1]-b-m*vals[2*i];
            chisq+=dy*dy;
        }
        ds[0][2]= Math.sqrt(chisq*n/delta/(n-2)); // dslope
        ds[0][3]= Math.sqrt(chisq*sumxx/delta/(n-2)); //dintercept
        return ds;
    }
    public String[] getVarStrings(){ return regStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return parentSApplet;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  }

  class Series extends Object implements SDataSource{
   //private SApplet parrentApplet=null;
   private DataSet data;
   private int sid;
   boolean enableLPCursor=false;
   boolean addRepeatedDatum=true;     // discard new datum if it is the same as the last data point.
   String[] varStrings= new String[]{"x","y","u","v"};
   SApplet owner=null;
   boolean autoReplace=false;
   RegressionDataSource regression=null;

   public Series(int i, DataSet ds) {
       sid=i;
       data=ds;
       owner=parentSApplet;
       //System.out.println("Series Created. SID: "+sid+"  Hash Code ID: "+hashCode() + " owner:"+owner.hahsCode() );
       try{SApplet.addDataSource(this);}catch (Exception e){e.printStackTrace();}
   }

   public void setOwner(SApplet owner){this.owner=owner;}
   public SApplet getOwner(){return owner;}

   public String[]   getVarStrings(){ return varStrings;}

   DataSet getDataSet(){ return data;}

   public final int getID(){return hashCode();}

   int getRegressionID(int start, int end){
       if(regression==null) regression=new  RegressionDataSource(this, start, end);
       return  regression.getID();
   }

   void paintLastPoint( Graphics g, Rectangle r){
       int lpRadius=2;
       int n=data.dataPoints();
       if (n<1 ||  !enableLPCursor) return;
       double[] datum=data.getLastPoint();
       if(datum==null)return;
       int xpix=pixFromX(datum[0]);
       int ypix=pixFromY(datum[1]);
       if(data.marker>0){
         lpRadius=Math.max(lpRadius,(int)Math.round(4*data.markerscale)-1);
       }
       int lpSize=lpRadius*2+1;
       g.setColor(Color.red);
       g.fillOval(xpix-lpRadius-1,ypix-lpRadius-1,lpSize,lpSize);
       g.setColor(Color.black);
       g.drawOval(xpix-lpRadius-1,ypix-lpRadius-1,lpSize,lpSize);
   }

   double[] getLastPoint(){
       int n=data.dataPoints();
       if (n<1) return null;
       double[] datum=data.getPoint(n-1);
       return datum;
   }

   double[] getX(){
       double[] vals=data.getData();
       int n=data.dataPoints();
       double[] x=new double[n];
       for(int i=0; i<n; i++)
          x[i]=vals[2*i];
       return x;
   }
   double[]getY(){
       double[] vals=data.getData();
       int n=data.dataPoints();
       double[] y=new double[n];
       for(int i=0; i<n; i++)
          y[i]=vals[2*i+1];
       return y;
   }
   int getNumpts(){return data.dataPoints();}

   public double[] getSlopeIntercept(int start, int end){
      int first,last;
      double[] datum;
      double[] regVars=new double[2];
      double x=0,y=0,xx=0,xy=0;
      int n=data.dataPoints();

      if(end<=start)last=n;  // do the whole series if start=end
        else last=Math.min(n,end);
      first=Math.max(1,start)-1;
      if(last-first<2) return regVars;
      for(int i=first;i<last; i++){
          datum=data.getPoint(i);
          x+=datum[0];
          y+=datum[1];
          xx+=datum[0]*datum[0];
          xy+=datum[0]*datum[1];
      }
      regVars[0]= (n*xy-x*y)/(n*xx-x*x);   // slope
      regVars[1]=  y/n-regVars[0]*x/n;     // intercept
      return regVars;
   }

   public double[][] getVariables(){
        int n=data.dataPoints();
        double[] datum;
        double[][] v=new double[n][4];
        if (n<1) return v;
        datum=data.getPoint(0);
        v[0][0]=datum[0];
        v[0][1]=datum[1];
        for(int i=1;i<n; i++){  // we already have the first point
            datum=data.getPoint(i);
            v[i][0]=datum[0];
            v[i][1]=datum[1];
            v[i][2]=v[i][0]-v[i-1][0];
            v[i][3]=v[i][1]-v[i-1][1];
        }
        return v;
   }

  }

  public class MathFunction extends Object implements Cloneable, SDataSource, SStepable {
    Parser parser=null;
    private Parser checkFunc = new Parser(1);
    boolean explicitTime=false;
    Color  color=Color.black;
    int numPts=100;
    double xmin=0;
    double xmax=0;
    double ymin=0;
    double ymax=0;
    double minClip,maxClip=0;
    double time=0;
    boolean fixedRange=false;
    boolean functionClip=false;
    String[] varStrings= new String[]{"x","y","v","a"};
    double[][] v=new double[numPts][4];
    SApplet owner=null;
    String string=null;
    String variable=null;
    public boolean visible=true;


    MathFunction(String indVar, String str){
      xmin=xaxis.minimum;
      xmax=xaxis.maximum;
      if(xmin>=xmax){
        xmax+=1;
        xmin=xmax-1;
      }
      str=str.trim();
      variable = new String (indVar.trim()); // Francisco Esquembre (March 2000)
      string = new String (str); // Francisco Esquembre (March 2000)
      if(! indVar.equals("t")) checkFunctionForTime(string);
      parser = new Parser(2);
      parser.defineVariable(1,indVar); // define the variable
      if(! indVar.equals("t")) parser.defineVariable(2,"t"); // define the variable
        else parser.defineVariable(2,"time"); // define the variable
      parser.define(str);
      parser.parse();
      if(parser.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse f(x,t)): "+str);
         System.out.println("Parse error in MathFunction: " + parser.getErrorString() +
                   " at math function, position " + parser.getErrorPosition());
      } else setScale();
      owner=parentSApplet;
      try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
      if(owner!=null){
        if(explicitTime) owner.clock.addClockListener(this);
        time=owner.clock.getTime();
      }
    }

    public void step(double dt,double time){
      this.time=time+dt;
      owner.clearData(this.hashCode());
      if(parentSApplet!=null && parentSApplet.destroyed) return;
      owner.updateDataConnection(this.hashCode());
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
    }

    public boolean setString(String str){
      str=str.trim();
      string = new String (str); // Francisco Esquembre (March 2000)
      parser.define(str);
      parser.parse();
      if(parser.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse f(x,t)): "+str);
         System.out.println("Parse error in MathFunction: " + parser.getErrorString() +
                   " at function 1, position " + parser.getErrorPosition());
         return false;
      } else setScale();
      if (owner!=null) { // Francisco Esquembre (March 2000)
        if(parentSApplet!=null && parentSApplet.destroyed) return false;
        owner.clearData(this.hashCode());
        owner.updateDataConnection(this.hashCode());
      }
      return true;
    }

    String getString(){ return variable; } // Francisco Esquembre (March 2000)

    void setVariable(String str){
        variable = new String (str.trim()); // Francisco Esquembre (March 2000)
        //varStrings[0]=str;
        parser.defineVariable(1,str); // define the variable
    }

    String getVariable(){ return variable; } // Francisco Esquembre (March 2000)

    void checkFunctionForTime(String fieldStr){
     String str=new String(fieldStr);
     checkFunc.defineVariable(1,"x"); // define the variable
     checkFunc.define( str.toLowerCase() );
     checkFunc.parse();
     if(checkFunc.getErrorCode() != Parser.NO_ERROR){
         explicitTime=true;
     }else explicitTime=false;  // no explicit time
    }

    public void setOwner(SApplet owner){this.owner=owner;}
    public SApplet getOwner(){return owner;}

    public String[]   getVarStrings(){ return varStrings;}

    public final int getID(){return hashCode();}

    Parser getParser(){return parser;}

    String getFunctionStr(){return parser.getFunctionString();}

    void setFunctionRange(double xmin, double xmax, int n){
      if(n<1){
          fixedRange=false;
          return;
      }
      this.xmin=xmin;
      this.xmax=xmax;
      this.numPts=n;
      if(this.numPts!=v.length) v=new double[numPts][4];
      fixedRange=true;
    }

    void setFunctionClip(double min, double max){
      if(min>=max){
          functionClip=false;
          return;
      }
      this.minClip=min;
      this.maxClip=max;
      functionClip=true;
    }

    void paint(Graphics g, Rectangle r){
        //System.out.println("paint"+this.hashCode() +" fun="+parser.getFunctionString());
        if(!visible) return;
        if(fixedRange)paint2(g,r);
        else paint1(g,r);
    }

    void paint1(Graphics g, Rectangle r){
        numPts=r.width;
        if(numPts!=v.length) v=new double[numPts][4];
        if(numPts<1)return;
        Shape rc=g.getClip();
        g.setClip(r);
        int y1Pix=0, y2Pix=0;
        double x1=0, x2=0, y1=0, y2=0;
        xmin=xFromPix(r.x); // start on left side of graph
        xmax=xFromPix(r.x+r.width-1); // right hand side of graph
        x1=xmin;
        ymin=parser.evaluate(x1,time);
        y1=ymin;
        ymax=ymin;
        y1Pix=pixFromY(ymin);
        g.setColor(color);
        for(int i=1; i<r.width; i++){
            x2=xFromPix(r.x+i);
            y2=parser.evaluate(x2,time);
            if(!functionClip || ( y2>minClip && y2<maxClip)){
              if(y2<ymin)ymin=y2;
              if(y2>ymax)ymax=y2;
            }
            y2Pix=pixFromY(y2);
            if(!functionClip || (y1>minClip && y1<maxClip && y2>minClip && y2<maxClip)){
              g.drawLine(r.x+i-1,y1Pix,r.x+i,y2Pix);
            }
            y1Pix=y2Pix;
            y1=y2;
        }
        g.setClip(rc);
    }

    void paint2(Graphics g, Rectangle r){ // fixed range paint.
        if(numPts<1) return;
        Shape rc=g.getClip();
        g.setClip(r);
        int x1Pix=0, x2Pix=0;
        int y1Pix=0, y2Pix=0;
        double x=0, y=0;
        double dx=(xmax-xmin)/(numPts-1);
        x=xmin; // start on left side of graph
        x1Pix=pixFromX(x);
        ymin=parser.evaluate(x,time);
        ymax=ymin;
        y1Pix=pixFromY(ymin);
        g.setColor(color);
        for(int i=1; i<numPts; i++){
            x=x+dx;
            x2Pix=pixFromX(x);
            y=parser.evaluate(x,time);
            if(y<ymin)ymin=y;
            if(y>ymax)ymax=y;
            y2Pix=pixFromY(y);
            g.drawLine(x1Pix,y1Pix,x2Pix,y2Pix);
            x1Pix=x2Pix;
            y1Pix=y2Pix;
        }
        g.setClip(rc);
    }

    public double[][] getVariables(){
      double[][] v=this.v;  // make local copies in case a new array is allocated.
      int numPts=v.length;
      synchronized(functions){
        double x=xaxis.minimum;
        double xMax=xaxis.maximum;
        if(fixedRange){
          x=xmin;
          xMax=xmax;
        }
        double dx=(xMax-x)/(numPts-1);
        for(int i=0;i<numPts; i++){
            v[i][0]=x;
            v[i][1]=parser.evaluate(x,time);
            x += dx;
        }
        if (numPts<4) return v;
        // calculate the derivatives for everything except the first two and the last two.
        for(int i=2;i<numPts-2; i++){
            v[i][2]=(-v[i+2][1]+8*v[i+1][1]-8*v[i-1][1]+v[i-2][1])/dx/12;  //  first derivative to 4th order
            v[i][3]=(-v[i+2][1]+16*v[i+1][1]-30*v[i][1]+16*v[i-1][1]-v[i-2][1])/dx/dx/12;  //  second derivative
        }
        // first derivate end points;
        x=xaxis.minimum;
        v[1][2]= (-v[3][1]+8*v[2][1]-8*v[0][1]+parser.evaluate(x-dx,time))/dx/12;  //  first derivative to 4th order
        v[0][2]= (-v[2][1]+8*v[1][1]-8*parser.evaluate(x-dx,time)+parser.evaluate(x-2*dx,time))/dx/12;
        v[numPts-1][2]=(-parser.evaluate(xMax+2*dx,time)+8*parser.evaluate(xMax+dx,time)-8*v[numPts-2][1]+v[numPts-3][1])/dx/12;
        v[numPts-2][2]=(-parser.evaluate(xMax+dx,time)+8*v[numPts-1][1]-8*v[numPts-3][1]+v[numPts-4][1])/dx/12;
        // second derivate at end points;
        v[1][3]= (-v[3][1]+16*v[2][1]-30*v[1][1]+16*v[0][1]-parser.evaluate(x-dx,time))/dx/dx/12;
        v[0][3]= (-v[2][1]+16*v[1][1]-30*v[0][1]+16*parser.evaluate(x-dx,time)-parser.evaluate(x-2*dx,time))/dx/dx/12;
        v[numPts-1][3]= (-parser.evaluate(xMax+2*dx,time)+16*parser.evaluate(xMax+dx,time)-30*v[numPts-1][1]+16*v[numPts-2][1]-v[numPts-3][1])/dx/dx/12;
        v[numPts-2][3]= (-parser.evaluate(xMax+dx,time)+16*v[numPts-1][1]-30*v[numPts-2][1]+16*v[numPts-3][1]-v[numPts-4][1])/dx/dx/12;
        return v;
      }
    }

    void setScale(){
    // set the ymin and ymax values in case we need to rescale.
        if(numPts<2) return;
        double x=0, y=0;
        double dx=(xmax-xmin)/(numPts-1);
        x=xmin; // start on left side of graph
        y=parser.evaluate(x,time);
        if(!functionClip || ( y>minClip && y<maxClip)){
          ymin=ymax=y;
        } else{
          ymin=ymax=minClip;
        }
        for(int i=1; i<numPts; i++){
            x=x+dx;
            y=parser.evaluate(x,time);
            if(!functionClip || ( y>minClip && y<maxClip)){
              ymin=Math.min(ymin,y);
              ymax=Math.max(ymax,y);
            }
        }
    }
  }  // end of MathFunction

   class ComplexFunction extends Object implements Cloneable, SDataSource, SStepable {
    Parser parserIm=null, parserRe=null;;
    private Parser checkFunc = new Parser(1);
    boolean explicitTime=false;
    Color  color=Color.black;
    double xmin=0;
    double xmax=0;
    double ymin=0;
    double ymax=0;
    double time=0;
    boolean fixedRange=false;
    String[] varStrings= new String[]{"x","y","v","a"};
    double[][] v=new double[100][4];
    SApplet owner=null;
    String stringRe=null;
    String stringIm=null;
    String variable=null;
    boolean centered=true;

    ComplexFunction(String indVar, String strRe, String strIm){
      xmin=xaxis.minimum;
      xmax=xaxis.maximum;
      if(xmin>=xmax){
        xmax+=1;
        xmin=xmax-1;
      }
      strRe=strRe.trim();
      stringRe = new String (strRe);
      strIm=strIm.trim();
      stringIm = new String (strIm);
      variable = new String (indVar.trim()); // Francisco Esquembre (March 2000)
      if(! indVar.equals("t")){
        explicitTime=checkFunctionForTime(stringRe) || checkFunctionForTime(stringRe);
      }

      parserRe = new Parser(2);
      parserRe.defineVariable(1,indVar); // define the variable
      if(! indVar.equals("t")) parserRe.defineVariable(2,"t"); // define the variable
        else parserRe.defineVariable(2,"time"); // define the variable
      parserRe.define(strRe);
      parserRe.parse();
      if(parserRe.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse f(x,t)): "+strRe);
         System.out.println("Parse error in ComplexFunction: " + parserRe.getErrorString() +
                   " at position " + parserRe.getErrorPosition());
      }
      parserIm = new Parser(2);
      parserIm.defineVariable(1,indVar); // define the variable
      if(! indVar.equals("t")) parserIm.defineVariable(2,"t"); // define the variable
        else parserIm.defineVariable(2,"time"); // define the variable
      parserIm.define(strIm);
      parserIm.parse();
      if(parserIm.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse f(x,t)): "+strIm);
         System.out.println("Parse error in ComplexFunction: " + parserIm.getErrorString() +
                   " at math position " + parserIm.getErrorPosition());
      }
      setScale();
      owner=parentSApplet;
      try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
      if(owner!=null){
        if(explicitTime) owner.clock.addClockListener(this);
        time=owner.clock.getTime();
      }
    }

    public void step(double dt,double time){
      this.time=time+dt;
      if(parentSApplet!=null && parentSApplet.destroyed) return;
      owner.clearData(this.hashCode());
      owner.updateDataConnection(this.hashCode());
      if(autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
    }

    public boolean setStringIm(String strIm){
      strIm=strIm.trim();
      stringIm = new String (strIm); // Francisco Esquembre (March 2000)
      parserIm.define(strIm);
      parserIm.parse();
      if(parserIm.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse f(x,t)): "+strIm);
         System.out.println("Parse error in ComplexFunction: " + parserIm.getErrorString() +
                   " at position " + parserIm.getErrorPosition());
         return false;
      } else setScale();
      if (owner!=null) {
        if(parentSApplet!=null && parentSApplet.destroyed) return false;
        owner.clearData(this.hashCode());
        owner.updateDataConnection(this.hashCode());
      }
      return true;
    }


    public boolean setStringRe(String strRe){
      strRe=strRe.trim();
      stringRe = new String (strRe); // Francisco Esquembre (March 2000)
      parserRe.define(strRe);
      parserRe.parse();
      if(parserRe.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse Re(x,t)): "+strRe);
         System.out.println("Parse error in ComplexFunction: " + parserRe.getErrorString() +
                   " at position " + parserRe.getErrorPosition());
         return false;
      } else setScale();
      if (owner!=null) {
        if(parentSApplet!=null && parentSApplet.destroyed) return false;
        owner.clearData(this.hashCode());
        owner.updateDataConnection(this.hashCode());
      }
      return true;
    }

    String getString(){ return variable; } // Francisco Esquembre (March 2000)

    void setVariable(String str){
        variable = new String (str.trim()); // Francisco Esquembre (March 2000)
        //varStrings[0]=str;
        parserRe.defineVariable(1,str); // define the variable
        parserIm.defineVariable(1,str); // define the variable
    }

    String getVariable(){ return variable; } // Francisco Esquembre (March 2000)

    boolean checkFunctionForTime(String fieldStr){
     boolean explicitTime=false;
     String str=new String(fieldStr);
     checkFunc.defineVariable(1,"x"); // define the variable
     checkFunc.define( str.toLowerCase() );
     checkFunc.parse();
     if(checkFunc.getErrorCode() != Parser.NO_ERROR){
         explicitTime=true;
     }else explicitTime=false;  // no explicit time
     return explicitTime;
    }

    public void setOwner(SApplet owner){this.owner=owner;}
    public SApplet getOwner(){return owner;}

    public String[]   getVarStrings(){ return varStrings;}

    public final int getID(){return hashCode();}

    Parser getParserRe(){return parserRe;}
    Parser getParserIm(){return parserIm;}

    String getFunctionStrRe(){return parserRe.getFunctionString();}
    String getFunctionStrIm(){return parserIm.getFunctionString();}

    void setFunctionRange(double xmin, double xmax){
      if(xmin>=xmax){
          fixedRange=false;
          return;
      }
      this.xmin=xmin;
      this.xmax=xmax;
      fixedRange=true;
    }
    void paint(Graphics g, Rectangle r){
        //System.out.println("paint"+this.hashCode() +" fun="+parser.getFunctionString());
        paint1(g,r);
    }

    void paint1(Graphics g, Rectangle r){
        if(r.width<3)return;
        Shape rc=g.getClip();
        g.setClip(r);
        int y1Pix=0, y2Pix=0;
        double x=0, y=0;
        double xstart, xstop;
        int origin=pixFromY(0);
        xstart=xFromPix(r.x); // start on left side of graph
        xstop=xFromPix(r.x+r.width-1); // right hand side of graph
        double yRe, yIm;
        yRe=parserRe.evaluate(xmin,time);
        yIm=parserIm.evaluate(xmin,time);
        ymin=Math.sqrt(yRe*yRe+yIm*yIm);
        ymax=ymin;
        for(int i=1; i<r.width-1; i++){
            x=xFromPix(r.x+i);
            if(fixedRange && (x<xmin || x>xmax) ) continue;
            yRe=parserRe.evaluate(x,time);
            yIm=parserIm.evaluate(x,time);
            y=Math.sqrt(yRe*yRe+yIm*yIm);
           //g.setColor(Color.getHSBColor( (float)(1+Math.atan2(yIm,yRe)/Math.PI)/2, 1.0f, 1.0f ));
           g.setColor(colorFromRadians(Math.atan2(yIm,yRe)));
            if(y<ymin)ymin=y;
            if(y>ymax)ymax=y;
            if(centered){
              y1Pix=pixFromY(-y/2);
              y2Pix=pixFromY(y/2);
            }else{
              y1Pix=origin;
              y2Pix=pixFromY(y);
            }
            //g.drawLine(r.x+i-1,y1Pix,r.x+i,y2Pix);
            g.drawLine(r.x+i,y1Pix,r.x+i,y2Pix);
        }
        g.setClip(rc);
    }

    public double[][] getVariables(){
        return v;
    }

    void setScale(){
    // set the ymin and ymax values in case we need to rescale.
        int numPts=500;
        double x=0, y=0;
        double dx=(xmax-xmin)/(numPts-1);
        x=xmin; // start on left side of graph
        double yRe=parserRe.evaluate(x,time);
        double yIm=parserIm.evaluate(x,time);
        ymin=Math.sqrt(yRe*yRe+yIm*yIm);
        ymax=ymin;
        for(int i=1; i<numPts; i++){
            x=x+dx;
            yRe=parserRe.evaluate(x,time);
            yIm=parserIm.evaluate(x,time);
            y=Math.sqrt(yRe*yRe+yIm*yIm);
            ymin=Math.min(ymin,y);
            ymax=Math.max(ymax,y);
        }
    }
  }  // end of ComplexFunction

  class VectorFieldThing extends Thing implements Cloneable, SStepable {
    Parser parserFx=null, parserFy=null;
    private Parser checkFunc = new Parser(2);
    boolean explicitTime=false;
    double xmin=0;
    double xmax=0;
    double ymin=0;
    double ymax=0;
    double time=0;
    String stringFx=null;
    String stringFy=null;
    int gridSize=0;
    VectorField field;
    double[][][] fieldData;

    VectorFieldThing(String strFx, String strFy, int gs){
      super(SGraph.this);
      field= new VectorField(gs,gs );
      fieldData = field.resize(gs,gs);
      gridSize=gs;
      xmin=xaxis.minimum;
      xmax=xaxis.maximum;
      ymin=yaxis.minimum;
      ymax=yaxis.maximum;

      strFx=strFx.trim();
      stringFx = new String (strFx);
      strFy=strFy.trim();
      stringFy = new String (strFy);
      explicitTime=checkFunctionForTime(stringFx) || checkFunctionForTime(stringFy);
      parserFx = new Parser(3);
      parserFx.defineVariable(1,"x");
      parserFx.defineVariable(2,"y");
      parserFx.defineVariable(3,"t");
      parserFx.define(strFx);
      parserFx.parse();
      if(parserFx.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse fx(x,y,t)): "+strFx);
         System.out.println("Parse error in VectorField: " + parserFx.getErrorString() +
                   " at position " + parserFx.getErrorPosition());
      }
      parserFy = new Parser(3);
      parserFy.defineVariable(1,"x");
      parserFy.defineVariable(2,"y");
      parserFy.defineVariable(3,"t");
      parserFy.define(strFy);
      parserFy.parse();
      if(parserFy.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse fy(x,y,t)): "+strFy);
         System.out.println("Parse error in VectorField: " + parserFy.getErrorString() +
                   " at position " + parserFy.getErrorPosition());
      }
      if(parentSApplet!=null){
        time=parentSApplet.clock.getTime();
        if(explicitTime) parentSApplet.clock.addClockListener(this);
      }
    }

    public void step(double dt,double time){
      this.time=time+dt;
      if(explicitTime && autoRefresh) synchronized(delayLock){newData=true; delayLock.notify(); }
    }

    boolean checkFunctionForTime(String fieldStr){
     boolean explicitTime=false;
     String str=new String(fieldStr);
     checkFunc.defineVariable(1,"x"); // define the variable
     checkFunc.defineVariable(2,"y"); // define the variable
     checkFunc.define( str.toLowerCase() );
     checkFunc.parse();
     if(checkFunc.getErrorCode() != Parser.NO_ERROR){
         explicitTime=true;
     }else explicitTime=false;  // no explicit time
     return explicitTime;
    }

    public void paint(Graphics g){
        for(int j=0; j<gridSize; j++) {
              x = (xmax-xmin)*(double)j/(double)(gridSize-1) +xmin;
              for(int i=0; i<gridSize; i++) {
                    y = (ymax-ymin)*(double)i/(double)(gridSize-1) +ymin;
                    double fx=parserFx.evaluate(x,y,this.time);
                    double fy=parserFy.evaluate(x,y,this.time);
                    double mag=Math.sqrt(fx*fx+fy*fy);
                    if(mag>0){
                        fieldData[i][j][0]=fx/mag;
                        fieldData[i][j][1]=fy/mag;
                        fieldData[i][j][2]=mag;
                    }else{
                        fieldData[i][j][0]=0;
                        fieldData[i][j][1]=0;
                        fieldData[i][j][2]=0;
                    }
              }
          }


        field.paint(g,datarect);
    }
  }  // end of VectorField


}

class SGraph_mouseAdapter extends java.awt.event.MouseAdapter {
  SGraph adaptee;

  SGraph_mouseAdapter(SGraph adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseEntered(MouseEvent e) {
    adaptee.sGraph_mouseEntered(e);
  }

  public void mouseExited(MouseEvent e) {
    adaptee.sGraph_mouseExited(e);
  }

  public void mousePressed(MouseEvent e) {
    adaptee.sGraph_mousePressed(e);
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.sGraph_mouseReleased(e);
  }
}
class SGraph_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  SGraph adaptee;

  SGraph_mouseMotionAdapter(SGraph adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.sGraph_mouseMoved(e);
  }
  public void mouseDragged(MouseEvent e) {
    adaptee.sGraph_mouseDragged(e);
  }
}