package edu.davidson.surfaceplotter;

public class Controller {
  //make the static variables public.
  public  static final int INIT_CALC_DIV   = 20;
  public  static final int INIT_DISP_DIV   = 20;

  public static final int OPT_WIREFRAME   = 0;
  public static final int OPT_HIDDEN      = 1;
  public static final int OPT_SPECTRUM    = 2;
  public static final int OPT_GRAYSCALE   = 3;
  public static final int OPT_DUALSHADES  = 4;

  //public static final int OPT_SURFACE     = 315;
  //public static final int OPT_CONTOUR     = 316;
 // public static final int OPT_DENSITY     = 317;

  protected int calc_divisions=INIT_CALC_DIV;                    // number of divisions to calculate
  protected int disp_divisions=INIT_DISP_DIV;                    // number of divisions to calculate


  boolean delay_regen=false;
  boolean boxed=true;
  boolean showMesh=true;
  boolean autoScale=false;
  boolean displayXYTicks=true;
  boolean displayZTicks=true;
  boolean showFaceGrids=false;

  String minStr="";
  String maxStr="";

  String function1Str="x*y";
  String function2Str=null;
  boolean showFunction1=true;
  boolean showFunction2=false;

  float xmin=-1,xmax=1,ymin=-1,ymax=1,zmin=-1,zmax=1;
  int numContourLines=10;
  int plotMode=OPT_DUALSHADES;

  // added by W. Christian to autoscale the height.
  boolean autoScaleHeigth=false;
  float hmin=0; float hmax=0;



  Controller() {
  }

  public void setDefault(){
      delay_regen=false;
      boxed=true;
      showMesh=true;
      autoScale=false;
      displayXYTicks=true;
      displayZTicks=true;
      showFaceGrids=false;
      numContourLines=10;
      plotMode=OPT_DUALSHADES;
      function1Str=null;
      function2Str=null;
      showFunction1=false;
      showFunction2=false;
  }

    /**
   * Determines whether the delay regeneration in enabled.
   *
   * @return <code>true</code> if the checkbox is checked,
   *         <code>false</code> otherwise
   */

  public boolean isExpectDelay() {
    return delay_regen;
  }

  /**
   * Called when automatic rotation starts.
  */
  public void rotationStarts() {
    //calc.disable();   // Button
    //stop.disable();   // Button
   // regen.disable();   // Button
   //zmin.disable();     // InputField
   // zmax.disable();    // InputField
    //rotate.setLabel("Freeze"); // Button
  }

  /**
   * Called when automatic rotation stops
   */
  public void rotationStops() {
    //calc.enable();      // Button
    //stop.enable();      // Button
    //regen.enable();     // Button
   // zmin.enable();      // InputField
    //zmax.enable();      // InputField
   // rotate.setLabel("Rotate"); // Button
  }

  /**
   * Gets the selected shading/plot mode
   *
   * @return the selected shading/plot mode
   */
  public int getPlotMode() {
    //for (int i=0; i < MODES_COUNT; i++) {
    //  if (((CheckboxMenuItem)getMenuItem(OPT_SHADES+i)).getState())
    //  return i;
   // }
   // return 0;
   return plotMode;
   //return 0;
  }

  public boolean isPlotFunction1(){
    if(function1Str==null) return false;
    return showFunction1;
  }

  public boolean isPlotFunction2(){
    if(function2Str==null) return false;
    return showFunction2;
  }

    /**
   * Gets the first function defintion.
   *
   * @return the function definition
   */

  public String getFunction1Definition() {
    return function1Str;
  }

  /**
   * Gets the second function defintion.
   *
   * @return the function definition
   */

  public String getFunction2Definition() {
    return function2Str;
  }

    /**
   * Gets the number of contour lines to be created
   *
   * @return the number of contour lines
   */

  public int getContourLines() {
    return numContourLines;
  }

  /**
   * Gets the calculated number of divisions.
   *
   * @return calculated number of divisions
   */
  public int getCalcDivisions() {
    return calc_divisions;
  }

    /**
   * Gets the number of divisions to be displayed.
   * Automatically fixes invalid values.
   *
   * @return valid number of divisions to be displayed
   */

  public int getDispDivisions() {
    int plot_density;
    plot_density = disp_divisions;
    if (plot_density > calc_divisions) plot_density = calc_divisions;
    while ((calc_divisions % plot_density) != 0) plot_density++;
    return plot_density;
  }

    /**
   * Sets the number of divisions to be displayed.
   *
   * @param divisions number of divisions to be displayed
   */
  public void setDispDivisions(int divisions) {
    disp_divisions=divisions;
  }

  /**
   * Sets function1.  If str is null the function will not be shown.
   *
   * @param str the function string
   */
  public void setFunction1(String str){
      function1Str=str;
      if(function1Str==null){
        showFunction1=false;
      }else{
        showFunction1=true;
      }
   }

  /**
   * Sets function2.  If str is null the function will not be shown.
   *
   * @param str the function string
   */
  public void setFunction2(String str){
      function2Str=str;
      if(function2Str==null){
        showFunction2=false;
      }else{
        showFunction2=true;
      }
   }

  // the minimum z value from the data generator.
  public void setMinimumResult(float val){
     minStr=Float.toString(val);
     hmin=val;
  }
  public void setMinimumResult(String val){
     minStr=val;
     hmin=0;
  }

  // the minimum z value from the data generator.
  public void setMaximumResult(float val){
     maxStr=Float.toString(val);
     hmax=val;
  }
  public void setMaximumResult(String val){
     maxStr=val;
     hmax=0;
  }

    /**
   * Gets the current minimum x value.
   *
   * @return float representation of the minimum x
   */

  public float getXMin() {
    return xmin;
  }

  /**
   * Gets the current minimum y value.
   *
   * @return float representation of the minimum y
   */

  public float getYMin() {
    return ymin;
  }
  /**
   * Gets the current minimum z value.
   *
   * @return float representation of the minimum z
   */

  public float getZMin() {
    if(autoScaleHeigth) return hmin;
    return zmin;
  }

  /**
   * Gets the current maximum x value.
   *
   * @return float representation of the maximum x
   */

  public float getXMax() {
    return xmax;
  }

  /**
   * Gets the current maximum y value.
   *
   * @return float representation of the maximum y
   */

  public float getYMax() {
    return ymax;
  }

  /**
   * Gets the current maximum z value.
   *
   * @return float representation of the maximum z
   */

  public float getZMax() {
    if(autoScaleHeigth) return hmax;
    return zmax;
  }



  /**
   * Determines whether to show bounding box.
   *
   * @return <code>true</code> if to show bounding box
   */
  public boolean isBoxed() {
    return boxed;
  }

    /**
   * Determines whether to show x-y mesh.
   *
   * @return <code>true</code> if to show x-y mesh
   */

  public boolean isMesh() {
    return showMesh;
  }

  /**
   * Determines whether to scale axes and bounding box.
   *
   * @return <code>true</code> if to scale bounding box
   */

  public boolean isScaleBox() {
    return autoScale;
  }

  /**
   * Determines whether to show x-y ticks.
   *
   * @return <code>true</code> if to show x-y ticks
   */

  public boolean isDisplayXY() {
    return displayXYTicks;
  }

  /**
   * Determines whether to show z ticks.
   *
   * @return <code>true</code> if to show z ticks
   */

  public boolean isDisplayZ() {
    return  displayZTicks;
  }

  /**
   * Determines whether to show face grids.
   *
   * @return <code>true</code> if to show face grids
   */

  public boolean isDisplayGrids() {
    return showFaceGrids;
  }


}