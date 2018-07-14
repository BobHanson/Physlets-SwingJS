package optics;

import java.awt.BorderLayout;
import a2s.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.davidson.display.BoxThing;
import edu.davidson.display.CircleThing;
import edu.davidson.display.Constraint;
import edu.davidson.display.ImageThing;
import edu.davidson.display.ProtractorThing;
import edu.davidson.display.RectangleThing;
import edu.davidson.display.ShellThing;
import edu.davidson.display.Thing;
//import java.applet.*;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SStepable;
import edu.davidson.tools.SUtil;

/**
 * This applet simulates an optics bench containing lenses, mirrors, and sources.
 *
 * @version   4.0 $, Date: February 20, 2000
 * @author   Wolfgang Christian and Mike Lee
*/
public class OpticsApplet extends SApplet {
  static boolean isJS = /** @j2sNative true || */ false;
  private String button_lens="  Lens  ";
  private String button_source=" Source ";
  private String button_clear="Clear All";
  private String button_object=" Object ";
  private String button_active="Clear Active";
  private String button_mirror=" Mirror ";
  private String button_aperture="Aperture";
  private String button_beam="  Beam  ";
  String  label_angle="Angle:";
  String  label_spacing="spacing=";
  String  label_spread="spread =";
  String  label_anglevalue="angle =";
  String  label_rayinc="rayIncrement =";
  String  label_rayslope="raySlope =";
  String  label_opening="opening =";
  String  label_top="top =";
  String  label_bottom="bottom =";
  String  label_index_error="ERROR: n2<1.0 ";
  String  label_focal_length="fl =";


    private boolean showControls=true;
   // private boolean clipOn=true;
    boolean info=false, propertyDrag = false;
    boolean drag=true;
    double size=.5;   //1.0 is the full height.  .5 is half centered about the middle
    int direction=1;  //1 to right, -1 to left
    int defaultPixPerUnit;  //number of pixels on screen per unit (default 1:1)
    int pixPerUnit;  //number of pixels on screen per unit (default 1:1)
  String example, opticString = null;
  EtchedBorder bevelPanel1 = new EtchedBorder();
  Button lensBtn = new Button();
  Button sourceBtn = new Button();
  //BevelPanel bench = new BevelPanel();
  Bench bench = new Bench(this);
  EtchedBorder bevelPanel2 = new EtchedBorder();
  BorderLayout borderLayout2 = new BorderLayout();
  Button clearBtn = new Button();
  BorderLayout borderLayout1 = new BorderLayout();
  Button psourceBtn = new Button();
    int defaultFocalLength = 50, defaultOpeningSize = 100;
    double defaultDelN = 1.4;
    int defaultR = -50;          //positive R is divergent, negative R is convergent
  Button isourceBtn = new Button();
  EtchedBorder bevelPanel3 = new EtchedBorder();
  Button aperatureBtn = new Button();
  Button clearActiveElementBtn = new Button();
  Button mirrorBtn = new Button();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();


  /**
   * @y.exclude
   */
  public OpticsApplet() {
  }

  protected void setResources(){
    button_lens=localProperties.getProperty("button.lens",button_lens);
    button_source=localProperties.getProperty("button.source",button_source);
    button_clear=localProperties.getProperty("button.clear",button_clear);
    button_object=localProperties.getProperty("button.object",button_object);
    button_active=localProperties.getProperty("button.active",button_active);
    button_mirror=localProperties.getProperty("button.mirror",button_mirror);
    button_aperture=localProperties.getProperty("button.aperture",button_aperture);
    button_beam=localProperties.getProperty("button.beam",button_beam);
    label_angle=localProperties.getProperty("label.angle",label_angle);
    label_spacing=localProperties.getProperty("label.spacing",label_spacing);
    label_spread=localProperties.getProperty("label.spread",label_spread);
    label_anglevalue=localProperties.getProperty("label.anglevalue",label_anglevalue);
    label_rayinc=localProperties.getProperty("label.rayinc",label_rayinc);
    label_rayslope=localProperties.getProperty("label.rayslope",label_rayslope);
    label_opening=localProperties.getProperty("label.opening",label_opening);
    label_top=localProperties.getProperty("label.top",label_top);
    label_bottom=localProperties.getProperty("label.bottom",label_bottom);
    label_index_error=localProperties.getProperty("label.index_error",label_index_error);
    label_focal_length=localProperties.getProperty("label.focal_length",label_focal_length);
  }

  /**
   * @y.exclude
   */
  public void init() {
    initResources(null);
    try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); }
    catch (Exception e) { e.printStackTrace(); }
    try { defaultPixPerUnit = Integer.parseInt(this.getParameter("PixPerUnit", "100"));
    } catch (Exception e) { e.printStackTrace(); }
    try { jbInit(); } catch (Exception e) { e.printStackTrace(); }
    if(showControls)setUnDefault(); else setDefault();
    pixPerUnit=defaultPixPerUnit;
 //   int id=addLens(4,1.75);
  //  System.out.println("position"+ getX(id) );
  //  setDirection(-1);
  //  id=addSource(7.5,0.0,0.05,-0.40);
    //System.out.println("position"+ getX(id) );
    //this.setBackground(Color.lightGray);
    //bevelPanel1.setBackground(Color.lightGray);
    //bevelPanel2.setBackground(Color.lightGray);
    //bevelPanel3.setBackground(Color.lightGray);
    bench.setPixPerUnit(pixPerUnit);
    if(autoRefresh) bench.repaint();
  }
  /**
   * @y.exclude
   */
  public void jbInit() throws Exception{
    bevelPanel1.setLayout(flowLayout2);
	/** @j2sNative */{
	    this.setSize(new Dimension(437, 426));
	}
    bevelPanel1.addMouseListener(new OpticsApplet_bevelPanel1_mouseAdapter(this));
    lensBtn.setLabel(button_lens);
    lensBtn.addActionListener(new OpticsApplet_lensBtn_actionAdapter(this));
    sourceBtn.setLabel(button_source);
    sourceBtn.addActionListener(new OpticsApplet_sourceBtn_actionAdapter(this));
    bench.addKeyListener(new OpticsApplet_bench_keyAdapter(this));
    bench.addMouseMotionListener(new OpticsApplet_bench_mouseMotionAdapter(this));
    clearBtn.setLabel(button_clear);
    psourceBtn.setLabel(button_object);
    isourceBtn.setActionCommand("Add ISource");
    aperatureBtn.setActionCommand("aperatureBtn");
    clearActiveElementBtn.setLabel(button_active);
    clearActiveElementBtn.setActionCommand("clearActiveElementBtn");
    mirrorBtn.setActionCommand("mirrorBtn");
    mirrorBtn.addActionListener(new OpticsApplet_mirrorBtn_actionAdapter(this));
    mirrorBtn.setLabel(button_mirror);
    clearActiveElementBtn.addActionListener(new OpticsApplet_clearActiveElementBtn_actionAdapter(this));
    aperatureBtn.addActionListener(new OpticsApplet_aperatureBtn_actionAdapter(this));
    aperatureBtn.setLabel(button_aperture);
    bevelPanel3.setLayout(flowLayout1);
    isourceBtn.addActionListener(new OpticsApplet_isourceBtn_actionAdapter(this));
    isourceBtn.setLabel(button_beam);
    psourceBtn.addActionListener(new OpticsApplet_psourceBtn_actionAdapter(this));
    clearBtn.addActionListener(new OpticsApplet_clearBtn_actionAdapter(this));
    bevelPanel2.setLayout(borderLayout2);
    bench.addMouseListener(new OpticsApplet_bench_mouseAdapter(this));
    this.setLayout(borderLayout1);
    bench.repaint();
    if(showControls)
        this.add(bevelPanel1, BorderLayout.SOUTH);
    bevelPanel1.add(lensBtn, null);
    bevelPanel1.add(mirrorBtn, null);
    bevelPanel1.add(aperatureBtn, null);
    bevelPanel1.add(clearActiveElementBtn, null);
    this.add(bevelPanel2, BorderLayout.CENTER);
    bevelPanel2.add(bench, BorderLayout.CENTER);
    if(showControls)
        this.add(bevelPanel3, BorderLayout.NORTH);
    bevelPanel3.add(isourceBtn, null);
    bevelPanel3.add(psourceBtn, null);
    bevelPanel3.add(sourceBtn, null);
    bevelPanel3.add(clearBtn, null);
  }

   /**
   * Counts the number of applets on a page.
   *
   * @param func
   * @param vars
   *
   * @return the applet count
   * @y.exclude
   */
  public int getAppletCount() {
    if(firstTime) return 0;
    else return super.getAppletCount();
  }

  /**
   * @y.exclude
   */
  public void start() {
    if(firstTime){
       firstTime=false;
       bench.createOSI();
    }
    super.start();
    //int id1=addObject("source","infinite, x=5, y=0.0, s=100, nrays=50");


/*
    int id1=addObject("protractor","x=.4,y=.8,s=50,theta=0.785,theta0=-1.57");
    this.setResizable(id1,true);
    this.setDragable(id1,true);
    int cid=addObject("text","text=theta=");
    makeDataConnection(id1,cid,1,"x","y");
    makeDataConnection(id1,cid,2,"theta*180/pi","0");
    this.updateDataConnections();
*/

    //addObject("source","x=5,y=5.3");
    //addObject("lens","x=20,f=5");

    //int id1=addObject("dielectric","x=2.2, dn=1.0");
    //set(id1,"dielectric","dn=2");


    //int id1=addObject("source","point, x=0.25, y=0.0, inc=0.1, slope=0.2");
    //setDragable(id1,false);
   // setResizable(id1,true);
    //id1=addObject("mirror","x=3, f=1.0");
    //this.setDisplayOffset(id1,0,50);
    //int id3=addObject("box","x=0.25, y=0, w=4,h=200");
    //setAnimationSlave(id3,id1);
    //setDragable(id3,true);

    //int id1 =addObject("image","file=ball.gif,x=2,y=0.5");
    //setDragable(id1,true);
    //setRGB(id1,255,0,0);

    //int id1=addObject("beamstop","x=2, top=0.25, bottom=0.75");

    //int id1=addObject("matrix","x=2, height=h,angle=a-h*0.5");
    //int id2=addObject("source","x=1,y=0,infinite");

    //int id=addObject("source","point,x=0.5,y=0.3,slope=-0.5, inc=0.1");
   // setDragable(id,true);
/*
    int id=addObject("source","x=0.25, y=1.0, rays=true");
    setRGB(id,0,255,0);
    setResizable(id,true);
    setDragable(id,false);
*/
    /*
    setDefault();
    int id1=addObject("lens","x=2, f=1.0");
    int id2=addObject("text","x=3, text=test, calc=f");
    setAnimationSlave(id1,id2);
    int id3=addObject("constraint","xmin=1,xmax=3");
    this.setConstraint(id1,id3);
    setDragable(id1,false);
    setResizable(id1,true);    */

  }

  /**
   * @y.exclude
   * @return the info
   */
  public String getAppletInfo() {
    return "Applet Information";
  }

  /**
   * @y.exclude
   * @return the info
   */
  public String[][] getParameterInfo() {
    String pinfo[][] =
    {
      {"example", "String", "an example parameter"},
    };
    return pinfo;
  }

  /**
   * Offset the object's position on the screen from its default drawing
   * position.
   *
   * @param              id The id of the object.
   * @param              xoff The x offset.
   * @param              yoff The y offset.
   * @return             True if successful.
   */
  public boolean setDisplayOffset(int id, int xOff, int yOff){
      Thing t=bench.getThing(id);
      if(t==null) return false;
      t.setDisplayOff(xOff,yOff);
      t.setVarsFromMaster() ;
      if(t instanceof OpticElement){
            ((OpticElement)t).adjustPosition();
      }
      if(autoRefresh) bench.repaint();
      return true;
  }

/**
 *    Clears all elements from bench and repaints
 */
   public void clearAll(){
    bench.clearThings();
    if(autoRefresh)repaint();
  }

  /**
 *    Changes all the colors of the buttons to black.
 *  @y.exclude
 */
  public void setBtnOff(){
    lensBtn.setForeground(Color.black);
    sourceBtn.setForeground(Color.black);
    psourceBtn.setForeground(Color.black);
    isourceBtn.setForeground(Color.black);
    aperatureBtn.setForeground(Color.black);
    mirrorBtn.setForeground(Color.black);
    /*
    lensBtn.repaint();
    sourceBtn.repaint();
    psourceBtn.repaint();
    isourceBtn.repaint();
    aperatureBtn.repaint();
    mirrorBtn.repaint();
    */
    bench.repaint();
  }


/**
 *    Change the drag property of a wire or loop after it has been created.
 *    Use the id returned by the add methods to identify the object you wish to change.
 *
 *    @param id        the object identifier
 *    @param drag      true if dragable
 *
 *    @return true if successful
*/
  public boolean setDragable(int id, boolean drag){
      Thing thing=bench.getThing(id);
      if(thing==null) return false;
      thing.setDragable( drag );
      return true;
  }

/**
 *    Change the showFocus property.
 *
 *    The focus of lenses and mirrors will be drawn as a white dot on the axis when the
 *    object is selected with a mouse click.
 *
 *    @param id        the object identifier
 *    @param show      true to show the focus
 *
 *    @return true if successful
*/
  public boolean setShowFocus(int id, boolean show){
      Thing thing=bench.getThing(id);
      if(thing==null) return false;
      if(thing instanceof OpticElement)
          //((OpticElement)thing).setDragable( show );
          ((OpticElement)thing).setResizable(show);
      return true;
  }

    /**
   * Set a motion constraint on an object.
   *
   * @param              id the ID of the object.
   * @param              constraintID the ID of the constraint.
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setConstraint(int id, int constraintID){
      Thing t=bench.getThing(id);
      Thing c=bench.getThing(constraintID);
	    if(t==null)return false;
      if(c==null)return false;
      if ( !(c instanceof Constraint)) return false;
      t.setConstraint( (Constraint)c);
      return true;
  }

/**
 *    Determines if elements added after value is set will be allowed to be dragged
 *
 *    @param d    True or False
 */
  public void setDrag(boolean d){drag = d;}

  /**
	 * Force an object to follow another object on the screen.
	 *
	 * @param              masterID The id of the master object.
	 * @param              slaveID The id of the slave object.
	 * @return             true if successful.
	 */
  public boolean setAnimationSlave(int masterID, int slaveID){
        Thing master=bench.getThing(masterID);
        Thing slave=bench.getThing(slaveID);
        if(master==null || slave==null) return false;
        master.addSlave(slave);
        master.updateMySlaves();
        if(slave instanceof OpticElement){
               slave.setVarsFromMaster() ;
              ((OpticElement)slave).adjustPosition();
        }
        if(autoRefresh)bench.repaint();
        return true;
  }

  /**
   * Make an object resizable.
   *
   * A resizable object will usually change its physical properites.  For example, a
   * resizable lens will change its focal length.
   *
   *
   * @param              id the object identifier.
   * @param              resizable property
   *
   * @return             <code>true</code> if successful.
   */
  public boolean setResizable(int id, boolean isResizable){
      Thing thing=bench.getThing(id);
      if(thing==null) return false;
      thing.setResizable( isResizable );
      return true;
  }


/**
 *    Determines if elements added after value is set will be allowed to have their properties changed by dragging
 *
 *    @param pd    True or False
 */
  public void setPropertyDrag(boolean pd){propertyDrag = pd;}
/**
 *    Determines if elements added after value will show information on screen including focal lengths and positions
 *
 *    @param i    True or False
 */
  public void setInfo(boolean i){info = i;}

  /**
 *    Set the color of lenses and mirrors.   0,0,0 will reset default.
 *
 *    @param r    0<r<255
 *    @param g    0<g<255
 *    @param b    0<b<255
 */
  public void setElementRGB(int r, int g, int b){
      if(r==0 && g==0 && b==0) bench.setElementColor(null);
      else bench.setElementColor(new Color(r,g,b));
  }

  /**
   * Sets the object's font if the object has text that can be displayed.
   *
   * This method fails due to operator overloading on some browers.  Use setObjectFont instead.
   *
   * @param              id The id of the object.
   * @param              family The font family: Helvetica, Times.
   * @param              style The style, 0=plain, 1=bold.
   * @param              size The size of the font;
   * @return             true if successful.
   */
  public boolean setObjectFont(int id, String family, int style, int size) {
    Font font = new Font(family, style, size);
    Thing t = bench.getThing(id);

    if ((t == null) || (font == null)) {
      return false;
    }

    t.setFont(font);
    if(autoRefresh) bench.repaint();
    return true;
  }


/**
 *    Set the color of the source light rays.
 *
 *    @param r    0<r<255
 *    @param g    0<g<255
 *    @param b    0<b<255
 */
  public void setRayRGB(int r, int g, int b){bench.setRayColor(new Color(r,g,b));}

 /**
 *    Set the red, green, and blue color values for a wire or coil that has already been created. Color values
 *    must be in the range 0..255.
 *
 *    @param id       The id for the wire or loop.
 *    @param r        red.
 *    @param g        green.
 *    @param b        blue.
*/
  public boolean setRGB(int id, int r, int g, int b ){
      Thing thing=bench.getThing(id);
      if(thing==null) return false;
      thing.setColor( new Color(r,g,b) );
      return true;
  }




 /**
 *    Set the size of the optic elements.  Size=1 will make the height of the elements
 *    equal to the height of the applet.
 *
 *    @param s    Size  in the range 0 to 1.0
 */

  public void setSize(double s){size = s;}
/**
 *    Set the direction that sources added after will start in
 *
 *    @param d    d=1 or d=-1.  d=1 is to the right.  d=-1 is to the left
 */
  public void setDirection(int d){direction = d;}
/**
 *    Set the Pixels per Unit.
 *
 *    @param ppu    100 pixels per unit is as if on the screen 100 pixels represents 1 meter
 */
  public void setPixPerUnit(int ppu){
    pixPerUnit = ppu;
    bench.setPixPerUnit(ppu);
    if(autoRefresh) bench.repaint();
    }

 /**
 *    Repaint every time the system parameters are changed.
 *
 *    @param ar     Automatic repaint?
*/
  public void setAutoRefresh(boolean ar){
      autoRefresh=ar;
      if(ar)bench.repaint();
  }

/**
 *    Calls the following methods:  setPixPerUnit(100), setInfo(false), setSize(.8), setDrag(false), setPropertyDrag(false), & setDirection(1)
 */
  public void setDefault(){
    deleteDataConnections();
    info=false;
    drag = false;
    propertyDrag = false;
    bench.setElementColor(null);
    bench.setRayColor(null);
    clearAll();
    setPixPerUnit(defaultPixPerUnit);
    setInfo(false);
    setSize(0.8);
    setDrag(false);
    setPropertyDrag(false);
    setDirection(1);
  }
/**
 *    Calls the following methods:  setPixPerUnit(100), setInfo(true), setSize(.8),
 *   setDrag(true), setPropertyDrag(true), & setDirection(1)
 *   @y.exclude
 */
  public void setUnDefault(){
    clearAll();
    setPixPerUnit(defaultPixPerUnit);
    setInfo(true);
    setSize(0.8);
    setDrag(true);
    setPropertyDrag(true);
    setDirection(1);
  }

  /**
   * Show the visibility of the object.
   *
   * @param     show    <code>true</code> will show object on screen
   *
   * @return            <code>true</code> if successful <code>false</code> otherwise
   */
  public boolean  setVisibility(int id, boolean show){
      Thing t=bench.getThing(id);
      if(t==null) return false;
      t.setVisible(show);
      return true;
  }

  void lensBtn_actionPerformed(ActionEvent e) {
    //if (lensBtn.getForeground()==Color.blue) setNull();
    if ( opticString!=null && opticString.equals( "lens") ) setNull();
    else {
      opticString = "lens";
      setBtnOff();
      lensBtn.setForeground(Color.blue);
      lensBtn.repaint();
    }
  }

  void sourceBtn_actionPerformed(ActionEvent e) {
    if (sourceBtn.getForeground()==Color.red) setNull();
    else {
      opticString = "source";
      setBtnOff();
      sourceBtn.setForeground(Color.red);
      sourceBtn.repaint();
    }
  }

 //************************* get element parameters *********************/

 /**
 *    Get the element's focal length
 *
 *    @param id            The id of the element.
 *
 *    @return double      the focal length
*/
  public double getFocalLength(int id){
      OpticElement t=bench.getElement(id);
      if(t==null) return 0;
      return t.focalLength/(double)pixPerUnit;
  }

 /**
 *    Get the element's x position.
 *
 *    @param id            The id of the element.
 *
 *    @return double      the x position.
*/
  public double getX(int id){
      OpticElement t=bench.getElement(id);
      if(t==null) return 0;
      return t.getPixX()/(double)pixPerUnit;
  }

/**
*    Set the element's x position.
*
*    @param id     the id of the element.
*    @param x      the x position.
*/
 public void setX(int id, double x){
     Thing t=bench.getThing(id);
     if(t==null) return;
     t.setX(x);
}

/**
*    Set the element's x position.
*
*    @param id     the id of the element.
*    @param x      the x position.
*/
 public void setXY(int id, double x, double y){
     Thing t=bench.getThing(id);
     if(t==null) return;
     t.setXY(x,y);
}

/**
*    Set the element's x position.
*
*    @param id     the id of the element.
*    @param x      the x position.
*/
 public void setXPos(int id, double x){
     setX( id,  x);
}

/**
*    Set the element's y position.
*
*    @param id     the id of the element.
*    @param x      the y position.
*/
 public void setY(int id, double y){
     Thing t=bench.getThing(id);
     if(t==null) return;
     t.setY(y);
}

/**
*    Set the element's y position.
*
*    @param id     the id of the element.
*    @param y      the x position.
*/
 public void setYPos(int id, double y){
     setY( id,  y);
}

 /**
 *    Get the element's y position.
 *
 *    @param id            The id of the element.
 *
 *    @return double      the y position.
*/
  public double getY(int id){
      OpticElement t=bench.getElement(id);
      if(t==null) return 0;
      Rectangle r = bench.getBounds();
      return -(t.getPixY() -r.height/2  )/(double)pixPerUnit;
  }

      /**
     * Bug-fix to get the x of an object on Netscape and Sun.
     *
     * @param              id The id of the object.
     * @param              x  new x value
     * @return             true if successful
     */
    public double getXPos(int id) {
       return getX(id);
    }

    /**
     * Bug-fix to get the y of an object on Netscape and Sun.
     *
     * @param              id The id of the object.
     * @param              x  new x value
     * @return             true if successful
     */
    public double getYPos(int id) {
       return getY(id);
    }

/**
 *    Bug-fix to set the size of the optic elements.  Size=1 will make the height of the elements
 *    equal to the height of the applet.
 *
 *    @param s    Size  in the range 0 to 1.0
 */

  public void setElementSize(double s){setSize(s);}

  public void setSize(Dimension d){
    // bug fix for Mozilla and Java 1.4 plug-in
    if(started && !Dimension.class.isInstance(d)){
      d=super.getSize();
      System.out.println("SCRIPT ERROR: Java 1.4 plug-in does not support method overloading.");
      System.out.println("Method: setSize");
      System.out.println("Alternate method: setElementSize.");
      System.out.println("");
    }
    /** @j2sNative */{
      super.setSize(d);
    }
  }

  public void setSize(int w, int h){
    // bug fix for Mozilla and Java 1.4 plug-in
    if(started){
      w=super.getWidth();
      h=super.getHeight();
      System.out.println("SCRIPT ERROR: Java 1.4 plug-in does not support method overloading.");
      System.out.println("Method: setSize");
      System.out.println("Alternate method: setElementSize.");
      System.out.println("");
    }
    /** @j2sNative */{
        super.setSize(w,h);
      }

  }


/**
 *    Get the element's  index of refraction.
 *
 *    @param id            The id of the element.
 *
 *    @return double      the index
*/
  public double getIndex(int id){
      OpticElement t=bench.getElement(id);
      if(t==null) return 0;
      return t.indexOfRefraction;
  }


  /**
   * Change the properies of an object.
   * The first argument is the object identifier.
   * The second argument is the name of the property and the third is a
   * comma-delimited list of parameters.  For example, the scale can be added a follows:
   *<p>
   * <code>set(id, "lens", "x=3, f=-1");</code>
   * </p>
   *
   * @param              id the identifier of the object
   * @param              name the type of object to be set.
   * @param              parList a list of parameters
   * @return             true if successful
   */
  public synchronized boolean set(int id, String name, String parList){
     name=name.toLowerCase().trim();
     name=SUtil.removeWhitespace(name);
     String parList2=parList.trim();;
     parList=SUtil.removeWhitespace(parList);
     Thing t=bench.getThing(id);
     if(t==null){
        System.out.println("Object not found in set method.");
        return false;
     }
     if(t instanceof OpticElement){
        if( SUtil.parameterExist(parList,"x=")){
          OpticElement oe=(OpticElement) t;
          double x=SUtil.getParam(parList,"x=");
          oe.setX(x);
          oe.adjustPosition();
        }
        if( SUtil.parameterExist(parList,"y=")){
          OpticElement oe=(OpticElement) t;
          double y=SUtil.getParam(parList,"y=");
          oe.setY(y);
          oe.adjustPosition();
        }
        if( SUtil.parameterExist(parList,"f=")){
          OpticElement oe=(OpticElement) t;
          double f=SUtil.getParam(parList,"f=");
          Rectangle r = bench.getBounds();
          oe.setFocalLength(f*pixPerUnit,r);
        }
        if( SUtil.parameterExist(parList,"dn=") && t instanceof Dielectric){
          Dielectric dielectric=(Dielectric) t;
          double dn=SUtil.getParam(parList,"dn=");
          dielectric.setDelN(dn);
          bench.recalculateIndexOfRefraction();
        }
        if(autoRefresh) bench.repaint();
        return true;
     }
     else{
          if( SUtil.parameterExist(parList,"x=")){
          double x=SUtil.getParam(parList,"x=");
          t.setX(x);
        }
        if( SUtil.parameterExist(parList,"y=")){
          double y=SUtil.getParam(parList,"y=");
          t.setY(y);
        }
        if(autoRefresh) bench.repaint();
     }
     return true;
  }

     /**
   * Create an object and add it to the Physlet.
   * The first argument is the name of the object to be added and the second is a
   * comma-delimited list of parameters.  For example, a circle can be added a follows:
   *<p>
   * <code>addObject ("circle", "x = 0, y = -1.0, r = 10");</code>
   * </p>
   * See the supplemental documentation for a list of
   * <a href="doc-files/optics_addobject.html">object names and parameters.</a>
   *
   * @param              name the type of object to be created.
   * @param              parList a list of parameters to be set
   * @return             id that identifies the object.
   */
  public synchronized int addObject(String name, String parList){
     Thing t=null;
     double x=0;
     double y=0;
     double f=1.0;
     name=name.toLowerCase().trim();
     name=SUtil.removeWhitespace(name);
     String parList2=parList.trim();;
     parList=SUtil.removeWhitespace(parList);
     if(name.equals("matrix")){
         String hStr="h",aStr="a";
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"height=")) hStr=SUtil.getParamStr(parList,"height=");
         if( SUtil.parameterExist(parList,"angle=")) aStr=SUtil.getParamStr(parList,"angle=");
         int id=bench.addMatrix((int)(x*pixPerUnit),0,hStr,aStr);
         if(autoRefresh) bench.repaint();
         return id;
     }if(name.equals("lens")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"f=")) f=SUtil.getParam(parList,"f=");
         return   addLens(x,f);
     }else if(name.equals("screen")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         return   addScreen(x);
     }else if(name.equals("source")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"infinite")){ // beam at infinity
           int size=20;
           double slope=0;
           if( SUtil.parameterExist(parList,"s=")) size=(int)SUtil.getParam(parList,"s=");
           if( SUtil.parameterExist(parList,"slope=")) slope=SUtil.getParam(parList,"slope=");
           int sid = addISource(x,y,size,slope);
           ISource source= (ISource) bench.getThing(sid);
           if (SUtil.parameterExist(parList, "nrays=")) {
             int nrays = (int) SUtil.getParam(parList, "nrays=");
             source.numberOfRays = nrays;
             source.spacing=Math.max(1,size/nrays);
           }
           return sid;
         }
         if( SUtil.parameterExist(parList,"point")){
           double rayIncrement=0.1 ;
           double slope=0.5 ;
           if( SUtil.parameterExist(parList,"slope=")) slope=SUtil.getParam(parList,"slope=");
           if( SUtil.parameterExist(parList,"inc=")) rayIncrement=SUtil.getParam(parList,"inc=");
           int sid=addSource( x,  y, rayIncrement, slope);  // point source
           Source source= (Source) bench.getThing(sid);
           if( SUtil.parameterExist(parList,"nrays=")){
             int nrays =(int) SUtil.getParam(parList,"nrays=");
             source.numberOfRays=nrays;
             source.rayIncrement=Math.max(0.001,2*slope/nrays);
          }
           return  sid ;
         }
         // principal-ray source
         int id=addPSource(x,y); // principal rays
         if( SUtil.parameterExist(parList,"rays=")){
             String val=SUtil.getParamStr(parList,"rays=");
             OpticElement el=bench.getElement( id);
             if(val.toLowerCase().equals("false") ) el.showRays=false;
         }
         if( SUtil.parameterExist(parList,"imagelabel=")){
             String val=SUtil.getParamStr(parList,"imagelabel=");
             OpticElement el=bench.getElement( id);
             if(val.toLowerCase().equals("false") ) el.imageLabel=false;
         }
         return id;
     } else if(name.equals("dielectric")){
        double dn=1;
        double r=0;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"dn=")) dn=SUtil.getParam(parList,"dn=");
         if( SUtil.parameterExist(parList,"r=")) r=SUtil.getParam(parList,"r=");
         if(r==0) return addIndexChange(x,dn);
         else return   addDielectric(x,dn,r);
     }else if(name.equals("refraction")){
        double dn=1;
        double r=0;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"dn=")) dn=SUtil.getParam(parList,"dn=");
         if( SUtil.parameterExist(parList,"r=")) r=SUtil.getParam(parList,"r=");
         return addRefraction(x,dn,r);
     }else if(name.equals("mirror")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"f=")) f=SUtil.getParam(parList,"f=");
         if( SUtil.parameterExist(parList,"spherical")){
           return addSphericalMirror(x,f);
         }
         return   addMirror(x,f);
     }else if(name.equals("aperture")){
         double o=1.0;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"opening=")) o=SUtil.getParam(parList,"opening=");
         o=Math.min(1.0,o);
         o=Math.max(0,o);
         return   addAperture(x,o);
     }else if(name.equals("beamstop")){
         double top=0;
         double bottom=1.0;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"top=")) top=SUtil.getParam(parList,"top=");
         if( SUtil.parameterExist(parList,"bottom=")) bottom=SUtil.getParam(parList,"bottom=");
         top=Math.min(1.0,top);
         top=Math.max(0,top);
         bottom=Math.min(1.0,bottom);
         bottom=Math.max(0,bottom);
         return   addBeamStop(x,top,bottom);
     }else if(name.equals("constraint")){
         double xmin=0,xmax=0,ymin=0,ymax=0;
         if( SUtil.parameterExist(parList,"xmin=")) xmin=SUtil.getParam(parList,"xmin=");
         if( SUtil.parameterExist(parList,"ymin=")) ymin=SUtil.getParam(parList,"ymin=");
         if( SUtil.parameterExist(parList,"xmax=")) xmax=SUtil.getParam(parList,"xmax=");
         if( SUtil.parameterExist(parList,"ymax=")) ymax=SUtil.getParam(parList,"ymax=");
         t=new Constraint(this,bench,xmin,xmax,ymin,ymax);
         t.setColor(Color.pink);
     }else if (name.equals("protractor")) {
       int s = 40;    // the protractor length.
       double theta=0,theta0=0;
       if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
       if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
       if (SUtil.parameterExist(parList, "theta=")) {
         theta = SUtil.getParam(parList, "theta=");
       }
       if (SUtil.parameterExist(parList, "theta0=")) {
         theta0 = SUtil.getParam(parList, "theta0=");
       }

       if (SUtil.parameterExist(parList, "s=")) {
         s = (int) SUtil.getParam(parList, "s=");
       }
       ProtractorThing p = new ProtractorThing(this,bench,s,theta,theta0,x,y);
       if (SUtil.parameterExist(parList, "fixedbase")) {
         p.fixedBase=true;
       }
       if (SUtil.parameterExist(parList, "fixedlength")) {
         p.fixedlength=true;
       }
       t=p;
     }else if(name.equals("rectangle")){
         int width=20;
         int height=20;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"w=")) width=(int)SUtil.getParam(parList,"w=");
         if( SUtil.parameterExist(parList,"h=")) height=(int)SUtil.getParam(parList,"h=");
         t=new RectangleThing(this,bench,x,y,width,height);
     }else if(name.equals("circle")){
         int r=10;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new CircleThing(this,bench,x,y,r);
     }else if(name.equals("shell")){
         int r=10;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new ShellThing(this,bench,x,y,r);
     }else if(name.equals("box")){
         int width=20;
         int height=20;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"w=")) width=(int)SUtil.getParam(parList,"w=");
         if( SUtil.parameterExist(parList,"h=")) height=(int)SUtil.getParam(parList,"h=");
         t=new BoxThing(this,bench,x,y,width,height);
     }else if(name.equals("text")|| name.equals("calculation")){
         String txt="";
         String calc=null;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"txt=")) txt=SUtil.getParamStr(parList2,"txt=");
         if( SUtil.parameterExist(parList,"text=")) txt=SUtil.getParamStr(parList2,"text=");
         if( SUtil.parameterExist(parList,"calc=")) calc=SUtil.getParamStr(parList,"calc=");
         t=new CalcThing(this,bench,txt,calc, x,y);
     }else if(name.equals("image")){
         String file=" ";
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"gif=")) file=SUtil.getParamStr(parList,"gif=");
         if( SUtil.parameterExist(parList,"file=")) file=SUtil.getParamStr(parList,"file=");
         if(file==null) return 0;
         Image im=getImage(file);
         if(im!=null) t=new ImageThing(this,bench,im, x, y);
         else t=null;
     }else if(name.equals("bench")){
         t=new BenchThing(bench);
         bench.hasBench=true;
     }
     if(t==null){
         System.out.println("Object not created. name:" + name + "parameter list:" + parList);
         return 0;
     }
     t.setResizable(false);
     if( SUtil.parameterExist(parList,"label=")) t.setLabel(SUtil.getParamStr(parList2,"label="));
     bench.addThing(t);
     if(autoRefresh)bench.repaint();
     return t.hashCode();
  }

  /**
   * Delete an object from the applet.
   *
   * @param id the object identifier
   *
   * @return true if successful
   */
  public synchronized boolean deleteObject(int id){
    return bench.deleteObject( id);
  }

  /**
   * Delete the active element from the applet.
   */
  public synchronized void deleteActiveElement(){
     bench.clearActiveElement();
  }

  /**
   *    Have the applet make a new data connection.
   *
   *    @param sourceID    The id of the data source.
   *    @param listenerID  The id of the data listener.   This is usually an applet.
   *    @param seriesID    The id of the series in the data listener.
   *    @param xStr        The function of the data source variables to be plotted on the horizontal axis.
   *    @param xStr        The function of the data source variables to be plotted on the vertical axis.
   * @param yStr
   *
   *    @return SDataConnection The hasCode id of the newly created data connection.
   */
  synchronized public int makeDataConnection(int sourceID, int listenerID, int seriesID, String xStr, String yStr) {
    Thing source = this.bench.getThing(sourceID);
    if(source instanceof SStepable){
      clock.addClockListener( (SStepable) source);
      source.setTime(clock.getTime());
    }
    return super.makeDataConnection(sourceID, listenerID,seriesID, xStr, yStr);
  }

  /**
   * updateDataConnections();
   */
  public void updateDataConnections(){
    super.updateDataConnections();
    if(bench.dragThing!=null) bench.repaint();
  }

  /**
   * updateDataConnections();
   */
  public void updateDataConnection(int id) {
    super.updateDataConnection(id);
    if(bench.dragThing!=null) bench.repaint();
  }


    /**
   *  Get an image from the code base, the document base, or the absolute URL.
   *
   * @param              file Location of image relative to the document
   *                     containing the HTML page.
   * @return             the image
   *
   */
  private Image getImage(String file){
      Image im;
      // java.io.File f=new java.io.File(file);
      try{
          //if(f.canRead())
              im=getImage(getCodeBase(),file);

         // else id=0;
        } catch(Exception e){
            im=null;
            //System.out.println("Failed to load image file from code base.");
      }
      if(im==null)try{
          //if(f.canRead())
              im=getImage(getDocumentBase(),file);
          //else id=0;
        } catch(Exception e){
            im=null;
            //System.out.println("Failed to load image file from document base.");
        }
      if(im==null)try{
          java.net.URL url= new java.net.URL(file);
          im =getImage(url);
        } catch(Exception e){
            im=null;
            //System.out.println("Failed to load image file from absolute URL.");
        }
      if(im==null){
          System.out.println("Failed to load image file.");
          return im;
      }
      MediaTracker tracker=new MediaTracker(this);
      try{
            tracker.addImage(im,0);
            tracker.waitForID(0,1000);  // wait one second
      }catch(Exception e){
        //return null;
      }
      //if(tracker.isErrorAny()) return null;
      //if(im.getHeight(this)<1) return null;
      return im;
   }


  //*************************add elements by units*********************/

/**
 *    Add a blocking aperture at x.  Alternative to addObject method.
 *
 *    @param x             The position of the aperture, in relative units specified by setPixPerUnit()
 *    @param openingSize   0<=openingSize<=1  \
*/
  public int  addAperture(double x,double openingSize){
      return addAperature(x,openingSize);
  }
  private int addAperature(double x,double openingSize){
    int id=bench.addAperature((int)(x*pixPerUnit),0,(int)(openingSize*pixPerUnit),info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }

  private int addBeamStop(double x,double top, double bottom){
    // switch top and bottom for pixel drawing
    int id=bench.addBeamStop((int)(x*pixPerUnit),0,(int)(bottom*bench.getBounds().height),(int)(top*bench.getBounds().height),info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }

/**
 *    Add a dielectric interface that can have a radius of curvature or none at all.
 *    Uses small angle approximation that doesn't show aberration.
 *
 *    This method is an alternative to the addObject method.
 *
 *    @param x        The position of the aperture, in relative units specified by setPixPerUnit
 *    @param dn       Change in index of refraction from left to right.
 *    @param r        The radius of curvature of the interface, in relative units.
 *
*/
  public int  addDielectric(double x, double dn, double r){
    int id=bench.addDielectric((int)(x*pixPerUnit),0,dn,(int)(r*pixPerUnit),info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Add an index change that has no curvature.  Uses Snell's law and can show total internal reflection.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x        The position of the aperture, in relative units specified by setPixPerUnit
 *    @param delN     The change (+/-) of index going from left to right across interface
 *
*/
  public int  addIndexChange(double x, double delN){
    int id=bench.addIndexChange((int)(x*pixPerUnit),0,delN,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a refraction interface that can represent a thick lens with spherical
 *   aberration and internal reflection.  Uses Snell's law, with angular displacement.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x        The x position of the refraction, in relative units specified by setPixPerUnit()
 *    @param delN     The change in index of refraction when going from left to right across inferface
 *    @param r        The radius of curvature of the interface, specified in relative units.
*/
  public int addRefraction(double x, double delN,double r){
    int id=bench.addRefraction((int)(x*pixPerUnit),0,delN,(int)(r*pixPerUnit),info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds an infinite source with parallel rays.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x        The x position of the ISource, in relative units specified by setPixPerUnit
 *    @param y        The x position of the ISource, in relative units specified by setPixPerUnit
 *    @param size     The height from the top to bottom of the source, in pixels
 *    @param angle    The slope of the rays, -1<angle<1.  -1 slope is 45 degrees above horizontal
 *
*/
    public int addISource(double x, double y, int size, double angle){        //make sure to set direction first!!!
    Rectangle r = bench.getBounds();
    int id=bench.addISource((int)(x*pixPerUnit),(int)(r.height/2.0-y*pixPerUnit),size,angle,info,drag,direction,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a thin lens, no spherical aberration, based on matrix transformations.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the lens, in relative units specified by setPixPerUnit()
 *    @param fl    The focal length of the lens, in relative units specified by setPixPerUnit()
 *
*/
  public int addLens(double x, double fl){
    int id=bench.addLens((int)(x*pixPerUnit),0,fl*pixPerUnit,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds an ideal mirror, based on matrix transformations.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the mirror, in relative units specified by setPixPerUnit()
 *    @param fl    The focal length of the mirror, in relative units specified by setPixPerUnit()
*/
  public int addMirror(double x, double fl){
    int id=bench.addMirror((int)(x*pixPerUnit),0,fl*pixPerUnit,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a spherical mirror.  Some aberations are calculated.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the mirror, in relative units specified by setPixPerUnit()
 *    @param fl    The focal length of the mirror, in relative units specified by setPixPerUnit()
 *
*/
  public int  addSphericalMirror(double x, double fl){
    int id=bench.addSphericalMirror((int)(x*pixPerUnit),0,fl*pixPerUnit,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }

/**
 *    Adds an principal-ray source which draws the three principal rays.
 *
 *    This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the PSource, in relative units specified by setPixPerUnit()
 *    @param y     The y position of the PSource, in relative units specified by setPixPerUnit()
*/
  public int addPSource(double x, double y){
    Rectangle r = bench.getBounds();
    int id=bench.addPSource((int)(x*pixPerUnit),(int)((r.height/2.0)-(y*pixPerUnit)),info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a screen.  This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the Screen, in relative units specified by setPixPerUnit()
*/
  public int addScreen(double x){
    int id=bench.addScreen((int)(x*pixPerUnit),0,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a regular point source, with spreading rays from a single point.
 *
 *    This method is an alternative to the addObject method.
 *
 *    @param x             The x position of the Source, in relative units specified by setPixPerUnit()
 *    @param y             The y position of the source, in relative units specified by setPixPerUnit()
 *    @param rayIncrement  The delta slope between intervals of rays coming from the Source
 *    @param raySlope      The starting slope of the outermost rays (top and bottom)
 */
  public int addSource(double x, double y,double rayIncrement,double raySlope){          //make sure to set direction first!!!
    Rectangle r = bench.getBounds();
    int id=bench.addSource((int)(x*pixPerUnit),(int)((r.height/2.0)-(y*pixPerUnit)),rayIncrement,-Math.abs(raySlope),info,drag,direction,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }

  //**************add elements by pixel*******************//

/**
 *    Add a blocking aperture at x.  Alternative to addObject method.
 *
 *    @param x             The position of the aperture, in direct pixel units
 *    @param openingSize   0<=openingSize<=1
*/
  public int addApertureP(int x,int openingSize){
      return addAperatureP(x,openingSize);
  }
  private int addAperatureP(int x,int openingSize){
    int id=bench.addAperature(x,0,openingSize,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Add a dielectric interface that can have a radius of curvature or none at all.
 *    Uses the small angle approximation and therefore doesn't show aberration.
 *
 *    This method is an alternative to the addObject method.
 *
 *    @param x        The position of the aperture, in pixel units
 *    @param dn       Change in index of refraction from left to right.
 *    @param r        The radius of curvature of the interface, in pixel units.
*/
    public int addDielectricP(int x, double dn, int r){
    int id=bench.addDielectric(x,0,dn,r,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Add an index change that has no curvature.  Uses Snell's law and can show total internal reflection.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x        The position of the aperture, in pixel units
 *    @param dn     The change (+/-) of index going from left to right across interface
 *
*/
    public int addIndexChangeP(int x, double dn){
    int id=bench.addIndexChange(x,0,dn,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a refraction interface that can represent a thick lens with spherical
 *   aberration and internal reflection.  Uses Snell's law, with angular displacement.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x        The x position of the refraction, in pixel units
 *    @param delN     The change in index of refraction when going from left to right across inferface
 *    @param r        The radius of curvature of the interface, specified in pixel units.
*/
public int addRefractionP(int x, double delN,int rT){
    int id=bench.addRefraction(x,0,delN,rT,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds an infinite source with parallel rays.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x        The x position of the ISource, in pixel units
 *    @param y        The x position of the ISource, in pixel units
 *    @param size     The height from the top to bottom of the source, in pixels
 *    @param angle    The slope of the rays, -1<angle<1.  -1 slope is 45 degrees above horizontal
*/
  public int addISourceP(int x, int y, int size, double angle){        //make sure to set direction first!!!
    Rectangle r = bench.getBounds();
    int id=bench.addISource(x,r.height/2-y,size,angle,info,drag,direction,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a thin lens, no spherical aberration, based on matrix transformations.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the lens, in pixel units
 *    @param fl    The focal length of the lens, in pixel units
 */
  public int addLensP(int x, double fl){
    int id=bench.addLens(x,0,fl,info,drag,size,propertyDrag);
    //l.setClipOn(clipOn);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds an ideal mirror, based on matrix transformations.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the mirror, in pixel units
 *    @param fl    The focal length of the mirror, in pixel units
*/
  public int addMirrorP(int x, double fl){
    int id=bench.addMirror(x,0,fl,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }

/**
 *    Adds an spherical mirror.  Not all aberations are calculated.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the mirror, in pixel units
 *    @param fl    The focal length of the mirror, in pixel units
*/
  public int addSphericalMirrorP(int x, double fl){
    int id=bench.addSphericalMirror(x,0,fl,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }

/**
 *    Adds an principal-ray source which draws the three principal rays.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the PSource, in pixel units
 *    @param y     The y position of the PSource, in pixel units
 *
*/
  public int addPSourceP(int x, int y){
    Rectangle r = bench.getBounds();
    int id=bench.addPSource(x,r.height/2-y,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a screen using pixel units.  This method is an alternative to the addObject method.
 *
 *    @param x     The x position of the Screen, in pixel units
*/
  public int addScreenP(int x){
    int id=bench.addScreen(x,0,info,drag,size,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }
/**
 *    Adds a regular point source, with spreading rays from a single point.
 *
 * This method is an alternative to the addObject method.
 *
 *    @param x             The x position of the Source, in pixel units
 *    @param y             The y position of the source, in pixel units
 *    @param rayIncrement  The delta slope between intervals of rays coming from the source, typical value is .25
 *    @param raySlope      The starting slope of the outermost rays (top and bottom), typical value is between 0 and 1
 */
   public int addSourceP(int x, int y,double rayIncrement,double raySlope){          //make sure to set direction first!!!
    Rectangle r = bench.getBounds();
    int id=bench.addSource(x,r.height/2-y,rayIncrement,-Math.abs(raySlope),info,drag,direction,propertyDrag);
    if(autoRefresh) bench.repaint();
    return id;
  }

  //****************//

  void bench_mousePressed(MouseEvent e) {
    if (e.isControlDown()) bench.isControlDown = true;
       else bench.isControlDown = false;
    if (opticString=="lens"){
       bench.addLens(e.getX(),e.getY(),defaultFocalLength,info,drag,size,propertyDrag);
       setNull();}
    if (opticString=="mirror"){
       bench.addMirror(e.getX(),e.getY(),defaultFocalLength,info,drag,size,propertyDrag);
       setNull();}
    if (opticString=="source"){
       bench.addSource(e.getX(),e.getY(),.25,-1,info,drag,direction,propertyDrag);
       setNull();}
    if (opticString=="psource") {
       bench.addPSource(e.getX(),e.getY(),info,drag,size,propertyDrag);
       setNull();}
    if (opticString=="screen"){
       bench.addScreen(e.getX(),e.getY(),info,drag,size,propertyDrag);
       setNull();}
    if (opticString=="isource"){
       bench.addISource(e.getX(),e.getY(),100,0,info,drag,direction,propertyDrag);
       setNull();}
    if (opticString=="aperature"){
       bench.addAperature(e.getX(),e.getY(),defaultOpeningSize,info,drag,size,propertyDrag);
       setNull();}
    if (opticString=="dielectric"){
       bench.addDielectric(e.getX(),e.getY(),defaultDelN,defaultR,info,drag,size,propertyDrag);
       setNull();}
    if (opticString==null) bench.mousePressed(e.getX(),e.getY());
    repaint();
  }

/**
 *    Calls setBtnOff() and makes it so nothing is added when the mouse clicks the bench
 * @y.exclude
 */
  public void setNull(){
    opticString = null;
    setBtnOff();
  }

  void nullBtn_actionPerformed(ActionEvent e) {
    setNull();
  }

  void clearBtn_actionPerformed(ActionEvent e) {
    bench.clearThings();
    opticString = null;
    setBtnOff();
    repaint();
  }

  void bench_mouseReleased(MouseEvent e) {
    if (opticString==null)
        bench.mouseReleased(e.getX(),e.getY());
  }

  void bench_mouseDragged(MouseEvent e) {
    if (opticString==null) bench.mouseDragged(e.getX(),e.getY());
  }

  void bench_mouseMoved(MouseEvent e) {
    if (opticString==null) bench.mouseMoved(e.getX(),e.getY());
  }

  void psourceBtn_actionPerformed(ActionEvent e) {
    if (psourceBtn.getForeground()==Color.red) setNull();
    else {
      opticString = "psource";
      setBtnOff();
      psourceBtn.setForeground(Color.red);
      psourceBtn.repaint();
    }
  }

  void isourceBtn_actionPerformed(ActionEvent e) {
    if (isourceBtn.getForeground()==Color.green) setNull();
    else {
      opticString = "isource";
      setBtnOff();
      isourceBtn.setForeground(Color.green);
      isourceBtn.repaint();
    }
  }

  void aperatureBtn_actionPerformed(ActionEvent e) {
    if (aperatureBtn.getForeground()==Color.yellow) setNull();
    else {
      opticString = "aperature";
      setBtnOff();
      aperatureBtn.setForeground(Color.yellow);
      aperatureBtn.repaint();
    }
  }

  void clearActiveElementBtn_actionPerformed(ActionEvent e) {
    bench.clearActiveElement();
  }

  void mirrorBtn_actionPerformed(ActionEvent e) {
    if (mirrorBtn.getForeground()==Color.blue) setNull();
    else {
      opticString = "mirror";
      setBtnOff();
      mirrorBtn.setForeground(Color.blue);
      mirrorBtn.repaint();
    }
  }

  void buttonControl1_actionPerformed(ActionEvent e) {
    addSource(50,50,.25,-1);
  }

  void bench_keyPressed(KeyEvent e) {
    if (( e.getKeyCode() == 127) && showControls) bench.clearActiveElement();
  }
}

class OpticsApplet_lensBtn_actionAdapter implements java.awt.event.ActionListener{
  OpticsApplet adaptee;

  OpticsApplet_lensBtn_actionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.lensBtn_actionPerformed(e);
  }
}

class OpticsApplet_sourceBtn_actionAdapter implements java.awt.event.ActionListener{
  OpticsApplet adaptee;

  OpticsApplet_sourceBtn_actionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.sourceBtn_actionPerformed(e);
  }
}


class OpticsApplet_bench_mouseAdapter extends java.awt.event.MouseAdapter {
  OpticsApplet adaptee;

  OpticsApplet_bench_mouseAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.bench_mousePressed(e);
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.bench_mouseReleased(e);
  }
}

class OpticsApplet_bevelPanel1_mouseAdapter extends java.awt.event.MouseAdapter {
  OpticsApplet adaptee;

  OpticsApplet_bevelPanel1_mouseAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }
}


class OpticsApplet_clearBtn_actionAdapter implements java.awt.event.ActionListener{
  OpticsApplet adaptee;

  OpticsApplet_clearBtn_actionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.clearBtn_actionPerformed(e);
  }
}

class OpticsApplet_bench_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  OpticsApplet adaptee;

  OpticsApplet_bench_mouseMotionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseDragged(MouseEvent e) {
    adaptee.bench_mouseDragged(e);
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.bench_mouseMoved(e);
  }
}

class OpticsApplet_psourceBtn_actionAdapter implements java.awt.event.ActionListener{
  OpticsApplet adaptee;

  OpticsApplet_psourceBtn_actionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.psourceBtn_actionPerformed(e);
  }
}

class OpticsApplet_isourceBtn_actionAdapter implements java.awt.event.ActionListener{
  OpticsApplet adaptee;

  OpticsApplet_isourceBtn_actionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.isourceBtn_actionPerformed(e);
  }
}

class OpticsApplet_aperatureBtn_actionAdapter implements java.awt.event.ActionListener{
  OpticsApplet adaptee;

  OpticsApplet_aperatureBtn_actionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.aperatureBtn_actionPerformed(e);
  }
}

class OpticsApplet_clearActiveElementBtn_actionAdapter implements java.awt.event.ActionListener{
  OpticsApplet adaptee;

  OpticsApplet_clearActiveElementBtn_actionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.clearActiveElementBtn_actionPerformed(e);
  }
}

class OpticsApplet_mirrorBtn_actionAdapter implements java.awt.event.ActionListener{
  OpticsApplet adaptee;

  OpticsApplet_mirrorBtn_actionAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.mirrorBtn_actionPerformed(e);
  }
}


class OpticsApplet_bench_keyAdapter extends java.awt.event.KeyAdapter {
  OpticsApplet adaptee;

  OpticsApplet_bench_keyAdapter(OpticsApplet adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.bench_keyPressed(e);
  }
}



