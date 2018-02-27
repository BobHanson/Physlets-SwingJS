package splotter;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
//import java.awt.*;
import java.awt.event.*;
//import com.borland.jbcl.layout.*;
//import com.borland.jbcl.control.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import edu.davidson.surfaceplotter.SurfacePanel;

import a2s.*;

public class SPlotter extends SApplet implements SStepable  {
  boolean isStandalone = false;
  boolean firstTime=true;
  String funcStr;
  String func2Str;
  String scaleStr;
  String typeStr;   // type fo plot, such as threed or contour.
  int gridPts;
  int numLevels;
  int scaleFactor=10;  // scale for box on screen.
  boolean showControls;
  EtchedBorder etchedBorder1 = new EtchedBorder();
  Button plotBtn = new Button();
  SurfacePanel surfacePanel = new SurfacePanel(this);
  BorderLayout borderLayout1 = new BorderLayout();
  TextField funcField = new TextField();
  Panel panel1 = new Panel();
  Button rotateBtn = new Button();
  Button runBtn = new Button();

  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  public SPlotter() {

  }

  //Initialize the applet
  public void init() {
    double     dt=0.1;
    double     fps=10;
    try { fps = Double.valueOf(this.getParameter("FPS", "10")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
    try { funcStr = this.getParameter("Function", ""); } catch (Exception e) { e.printStackTrace(); }
    try { func2Str = this.getParameter("Function2", ""); } catch (Exception e) { e.printStackTrace(); }
    try { scaleStr = this.getParameter("Scale", "xmin=-1,xmax=1,ymin=-1,ymax=1,zmin=-1,zmax=1"); } catch (Exception e) { e.printStackTrace(); }
    try { gridPts = Integer.parseInt(this.getParameter("GridPts", "32")); } catch (Exception e) { e.printStackTrace(); }
    try { numLevels = Integer.parseInt(this.getParameter("Levels", "10")); } catch (Exception e) { e.printStackTrace(); }
    try { scaleFactor = Integer.parseInt(this.getParameter("ScaleFactor", "10")); } catch (Exception e) { e.printStackTrace(); }
    try { typeStr = this.getParameter("Type", "threed"); } catch (Exception e) { e.printStackTrace(); }
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
    try  {
      jbInit();
    }
    catch(Exception e)  {
      e.printStackTrace();
    }
    clock.setDt(dt);
    clock.setFPS((int)fps);
    etchedBorder1.setVisible(showControls);
    this.setGridPoints(gridPts);
    setNumLevels(numLevels);
    this.set(0,"style","scalefactor="+scaleFactor);
    surfacePanel.surfaceCanvas.setNoDrawing(true);
    //this.set(0,"type",typeStr);
    //this.set(0,"scale",scaleStr);
    clock.addClockListener(this);
    //setFunction1(funcStr);
    //setFunction2(func2Str);
    // debug
    //clock.startClock();
  }

  //Component initialization
  private void jbInit() throws Exception {
	  /** @j2sNative */{
		  resize(350,400);
	  }
    this.setLayout(borderLayout1);
    plotBtn.setLabel("Plot");
    plotBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        plotBtn_actionPerformed(e);
      }
    });
    funcField.setText("x*y");
    rotateBtn.setName("rotateBtn");
    rotateBtn.setLabel("Rotate");
    rotateBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        rotateBtn_actionPerformed(e);
      }
    });
    runBtn.setName("runBtn");
    runBtn.setLabel("Run");
    runBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        runBtn_actionPerformed(e);
      }
    });
    etchedBorder1.setBackground(SystemColor.inactiveCaptionText);
    this.add(etchedBorder1, BorderLayout.SOUTH);
    etchedBorder1.add(plotBtn, BorderLayout.WEST);
    etchedBorder1.add(funcField, BorderLayout.CENTER);
    etchedBorder1.add(panel1, BorderLayout.EAST);
    panel1.add(runBtn, null);
    panel1.add(rotateBtn, null);
    panel1.setBackground(SystemColor.inactiveCaptionBorder);
    this.add(surfacePanel, BorderLayout.CENTER);
  }

  /**
   * Used by the browser when the HTML page is active.
   *
   * Do not script.
   *
   */
  public void start() {
    super.start();
    if(firstTime){
      firstTime=false;
      set(0,"type",typeStr);
      set(0,"scale",scaleStr);
      setFunction1(funcStr);
      setFunction2(func2Str);
    }
  }

  /**
   * Used by the browser when the HTML page is hidden.
   *
   * Do not script.
   *
   */
  public void stop(){ super.stop(); }

  /**
   * Used by the browser when the HTML page is closed.
   *
   * Do not script.
   *
   */
  public void destroy() {
     surfacePanel.destroyThread();
     super.destroy();
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Applet Information";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    String[][] pinfo =
    {
      {"Function", "String", "Analytic Function, f(x,y)"},
      {"GridPts", "int", "Grid points along axis"},
      {"Levels", "int", "Number of contour levels."},
      {"Mode", "String", "Contour or 3D"},
      {"ShowControls", "boolean", "Show user interface"},
    };
    return pinfo;
  }
  /**
   * Sets the first function.
   *
   * @param              funcStr the function string
   */
  public void setFunction(String funcStr){
     setFunction1(funcStr);
  }

    /**
   * Sets the first function.
   *
   * Time animation is stopped and time is set to zero.
   * Rotation will also stop.
   *
   * @param              funcStr the function string
   */

  public void setFunction1(String funcStr){
      funcField.setText(funcStr);
      clock.stopClock();
      surfacePanel.setFunction1(funcStr);  // this will stop the paint threads.
  }

  /**
   * Sets the second function.
   *
   * The second function only appears on ThreeD plots.  It has no effect on the density plot.
   *
   * @param              funcStr the function string
   */
  public void setFunction2(String funcStr){
      clock.stopClock();
      surfacePanel.setFunction2(funcStr);
  }

  /**
   * Repaint whenevers the system parameters are changed.
   *
   * @param              autoRefresh Automatic repaint?
   */
  public void setAutoRefresh(boolean ar){
      autoRefresh=ar;
      surfacePanel.setAutoRefresh(ar);
  }

    /**
   * Change the properies of an object.
   * The first argument is the object identifier.
   * The second argument is the name of the property and the third is a
   * comma-delimited list of parameters.  For example, the scale can be added a follows:
   *<p>
   * <code>set(id, "scale", "xmin=0, xmax=2, autoscalx=false");</code>
   * </p>
   *
   * @param              id the identifier of the object
   * @param              name the type of property to be created.
   * @param              parList a list of parameters
   * @return             true if successful
   */
  public synchronized boolean set(int id, String name, String parList){
     name=name.toLowerCase().trim();
     name=SUtil.removeWhitespace(name);
     String parList2=parList.trim();;
     parList=SUtil.removeWhitespace(parList);
     String str="false";
     double xmin=surfacePanel.getMinX();
     double xmax=surfacePanel.getMaxX();
     double ymin=surfacePanel.getMinY();
     double ymax=surfacePanel.getMaxY();
     double zmin=surfacePanel.getMinZ();
     double zmax=surfacePanel.getMaxZ();
     if(name.equals("scale")){
        if( SUtil.parameterExist(parList,"xmin="))  xmin=SUtil.getParam(parList,"xmin=");
        if( SUtil.parameterExist(parList,"xmax="))  xmax=SUtil.getParam(parList,"xmax=");
        if( SUtil.parameterExist(parList,"ymin="))  ymin=SUtil.getParam(parList,"ymin=");
        if( SUtil.parameterExist(parList,"ymax="))  ymax=SUtil.getParam(parList,"ymax=");
        if( SUtil.parameterExist(parList,"zmin="))  zmin=SUtil.getParam(parList,"zmin=");
        if( SUtil.parameterExist(parList,"zmax="))  zmax=SUtil.getParam(parList,"zmax=");
        if(SUtil.parameterExist(parList,"autoscalez=")){
             str=SUtil.getParamStr(parList,"autoscalez=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) surfacePanel.setAutoscaleZ(false);
             else surfacePanel.setAutoscaleZ(true);
        }
        surfacePanel.setMinX(xmin);
        surfacePanel.setMaxX(xmax);
        surfacePanel.setMinY(ymin);
        surfacePanel.setMaxY(ymax);
        surfacePanel.setMinZ(zmin);
        surfacePanel.setMaxZ(zmax);
     }else if(name.equals("font")){
         String family="Helvetica";
         int style=Font.PLAIN;
         int size=10;
         if( SUtil.parameterExist(parList,"family="))  family=SUtil.getParamStr(parList,"family=");
         if( SUtil.parameterExist(parList,"style="))  style=(int)SUtil.getParam(parList,"style=");
         if( SUtil.parameterExist(parList,"size="))  size=(int)SUtil.getParam(parList,"size=");
         Font font=new Font(family,style,size);
         surfacePanel.setGraphFont(font);
     }else if(name.equals("mode")){
         int mode=surfacePanel.getMode();
         if( SUtil.parameterExist(parList,"mesh")|| SUtil.parameterExist(parList,"wireframe"))  mode=edu.davidson.surfaceplotter.Controller.OPT_WIREFRAME;
         if( SUtil.parameterExist(parList,"hidden"))  mode=edu.davidson.surfaceplotter.Controller.OPT_HIDDEN;
         if( SUtil.parameterExist(parList,"spectrum"))  mode=edu.davidson.surfaceplotter.Controller.OPT_SPECTRUM;
         if( SUtil.parameterExist(parList,"grayscale"))  mode=edu.davidson.surfaceplotter.Controller.OPT_GRAYSCALE;
         if( SUtil.parameterExist(parList,"dualshade"))  mode=edu.davidson.surfaceplotter.Controller.OPT_DUALSHADES;
         surfacePanel.setMode(mode);
     }else if(name.equals("type")){
         if( SUtil.parameterExist(parList,"contour")) surfacePanel.setContour();
         else if( SUtil.parameterExist(parList,"density")) surfacePanel.setDensity();
         else if( SUtil.parameterExist(parList,"threed"))  surfacePanel.setThreeD();
         else if( SUtil.parameterExist(parList,"none"))  surfacePanel.setNoDrawing();
     }else if(name.equals("view")){
         if( SUtil.parameterExist(parList,"scalefactor=")){
             int scalefactor=10;
             scalefactor=(int)SUtil.getParam(parList,"scalefactor=");
             scalefactor=Math.max(scalefactor,1);   // set a minimum
             surfacePanel.setScaleFactor(scalefactor);
         }
         if( SUtil.parameterExist(parList,"distance=")){
             int distance=200;
             distance=(int)SUtil.getParam(parList,"distance=");
             distance=Math.max(distance,50); // set a minimum
             surfacePanel.setDistance(distance);
         }
         if( SUtil.parameterExist(parList,"rotationangle=")){
             double a=45;
             a=(int)SUtil.getParam(parList,"rotationangle=");
             surfacePanel.setRotationAngle(a);
         }
         if( SUtil.parameterExist(parList,"elevationangle=")){
             double a=10;
             a=(int)SUtil.getParam(parList,"elevationangle=");
             surfacePanel.setElevationAngle(a);
         }

     }else if(name.equals("style")){
         if( SUtil.parameterExist(parList,"box")) surfacePanel.setShowBox(true);
         if( SUtil.parameterExist(parList,"mesh")) surfacePanel.setShowMesh(true);
         if( SUtil.parameterExist(parList,"facegrid"))  surfacePanel.setShowFaceGrids(true);
         if( SUtil.parameterExist(parList,"scaledbox"))  surfacePanel.setScaledBox(true);
         if( SUtil.parameterExist(parList,"xyticks"))  surfacePanel.setShowXYticks(true);
         if( SUtil.parameterExist(parList,"zticks"))  surfacePanel.setShowZticks(true);

         if( SUtil.parameterExist(parList,"gutter=")){
             int gutter=0;
             gutter=(int)SUtil.getParam(parList,"gutter=");
             gutter=Math.max(gutter,0);   // set a minimum
             gutter=Math.min(gutter,200);
             surfacePanel.setGutter(gutter);
         }
         if( SUtil.parameterExist(parList,"numlevels=")){
             int numlevels=10;
             numlevels=(int)SUtil.getParam(parList,"numlevels=");
             numlevels=Math.max(numlevels,1);   // set a minimum
             numlevels=Math.min(numlevels,100);
             surfacePanel.setNumLevels( numlevels);
         }
         if(SUtil.parameterExist(parList,"scaledbox=")){
             str=SUtil.getParamStr(parList,"scaledbox=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) surfacePanel.setScaledBox(false);
             else surfacePanel.setScaledBox(true);
         }
         if(SUtil.parameterExist(parList,"box=")){
             str=SUtil.getParamStr(parList,"box=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) surfacePanel.setShowBox(false);
             else surfacePanel.setShowBox(true);
         }
         if(SUtil.parameterExist(parList,"mesh=")){
             str=SUtil.getParamStr(parList,"mesh=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) surfacePanel.setShowMesh(false);
             else surfacePanel.setShowMesh(true);
         }
         if(SUtil.parameterExist(parList,"facegrid=")){
             str=SUtil.getParamStr(parList,"facegrid=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) surfacePanel.setShowFaceGrids(false);
             else surfacePanel.setShowFaceGrids(true);
         }
         if(SUtil.parameterExist(parList,"xyticks=")){
             str=SUtil.getParamStr(parList,"xyticks=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) surfacePanel.setShowXYticks(false);
             else surfacePanel.setShowXYticks(true);
         }
         if(SUtil.parameterExist(parList,"zticks=")){
             str=SUtil.getParamStr(parList,"zticks=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) surfacePanel.setShowZticks(false);
             else surfacePanel.setShowZticks(true);
         }
     }else{
         System.out.println("Set property not found: " + name + "parameter list: " + parList);
         return false;
     }
     this.setAutoRefresh(autoRefresh);
     return true;
  }

  /**
   * Set default values and deletes all data connections.
   */
  public void setDefault(){
      deleteDataConnections(); // we are going to delete all the things so we might as well kill the conections too.
      surfacePanel.setDefault();   // This will repaint if necessary.
	}

  public void setGridPoints(int pts){
      surfacePanel.setGridPts(pts);
  }

  /**
   * Sets the number of countour levels.
   *
   * @param num the number of levels
  */
  public void setNumLevels(int num){
      surfacePanel.setNumLevels(num);
  }

  /**
   * Get the rotation angle.
   *
   */
  public double getRotationAngle() {
      return surfacePanel.getRotationAngle() ;
  }


  /**
   * Sets the rotation angle.
   *
   * @param angle the rotation angle in degrees
   */
  public void setRotationAngle(double angle) {
      surfacePanel.setRotationAngle( angle) ;
  }

  /**
   * Get the elevation angle.
   *
   */
  public double getElevationAngle() {
      return surfacePanel.getElevationAngle() ;
  }


  /**
   * Sets the elevation angle.
   *
   * @param angle the elevation angle in degrees
   */
  public void setElevationAngle(double angle) {
      surfacePanel.setElevationAngle( angle) ;
  }

  public int getPlotID(){
      return surfacePanel.hashCode();
  }

  void rotateBtn_actionPerformed(ActionEvent e) {
       if(rotateBtn.getLabel().equals("Rotate")){
           rotateBtn.setLabel("Static");
           surfacePanel.startRotate();
       }else{
           rotateBtn.setLabel("Rotate");
           surfacePanel.stopRotate();
       }

  }

  public void step(double dt, double t){
     surfacePanel.setTime(t);
  }

  void runBtn_actionPerformed(ActionEvent e) {
      if(runBtn.getLabel().equals("Run")){
           runBtn.setLabel("Stop");
           clock.startClock();
       }else{
           runBtn.setLabel("Run");
           clock.stopClock();
       }
  }

  void plotBtn_actionPerformed(ActionEvent e) {
    clock.stopClock();
    clock.setTime(0);
    surfacePanel.setFunction1(funcField.getText());  // this will stop the paint threads.
    surfacePanel.setTime(0);
    if(rotateBtn.getLabel().equals("Static")){
           surfacePanel.startRotate();
    }
    if(runBtn.getLabel().equals("Stop")){
           clock.startClock();
    }
  }

}
