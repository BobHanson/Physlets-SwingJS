package animator4;

import a2s.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Enumeration;
import java.util.Vector;

import edu.davidson.display.Format;
import edu.davidson.numerics.SDifferentiable;
import edu.davidson.numerics.SRK45;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SDataSource;
import edu.davidson.tools.SStepable;
import edu.davidson.tools.SUtil;


public final class AnimatorCanvas extends Canvas
        implements SStepable, SDataSource {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MouseMotionAdapter mouseMotionAdapter;
    private MouseAdapter       mouseAdapter;
    private boolean            enableMouse      = false;
    private int                boxWidth         = 0;
    private Format             mouseFormat      = new Format("%-+6.3g");
    private Image              sketchImage      = null;
    private boolean            sketchMode       = false;
    private TrailThing         trailThing       = null;
    Cursor             sketchCursor     = null;
    private Thing              referenceObject  = null;

    CollisionDataSource collisionDataSource=null;
    Thing          dragThing       = null;
    Color          defaultColor    = null;
    Vector         things          = new Vector();
    Font           boldFont        = new Font("Helvetica", Font.BOLD, 14);
    Animator       owner           = null;
    int            pixPerUnit      = 10;
    Image          backgroundImage = null;
    Image          osi;
    int            iwidth             = 0, iheight = 0;
    int            xOffset            = 0, yOffset = 0;
    double         gridUnit           = 1;
    double         time               = 0;
    boolean        timeDisplay        = true;
    boolean        coordDisplay       = true;
    boolean        autoRefresh        = true;
    String         message            = null;
    boolean        mouseDrag          = false;
    int            mouseX             = 0, mouseY = 0;
    String[]       varStrings         = new String[] {
        "t", "xcm", "ycm", "px", "py", "m", "ke"
    };
    double[][]     ds                 = new double[1][7];    // the datasource state variables t,x,y,vx,vy,ax,ay;
    DynamicsSolver dynamics           = new DynamicsSolver();
    int            stickyCount        = 0;
    int            bouncyCount        = 0;
    boolean        dampOnMousePressed = true;

    public AnimatorCanvas(Animator o) {

        this();

        owner = o;
    }

    public AnimatorCanvas() {

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setEnableMouse(true);

        try {
            SApplet.addDataSource(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setBackground(Color.white);
    }

    public String[] getVarStrings() {
        return varStrings;
    }

    public int getID() {
        return hashCode();
    }

    public void setOwner(SApplet o) {
        ;
    }    // usually done in constructor.

    public SApplet getOwner() {
        return owner;
    }

    public double[][] getVariables() {

        double xcm = 0, ycm = 0, px = 0, py = 0, m = 0, ke = 0;

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            m   += t.mass;
            xcm += t.mass * t.vars[1];
            ycm += t.mass * t.vars[2];
            px  += t.mass * t.vars[3];
            py  += t.mass * t.vars[4];
            ke  += t.mass * (t.vars[3] * t.vars[3] + t.vars[4] * t.vars[4]);
        }

        ds[0][0] = time;

        if (m > 0) {
            ds[0][1] = xcm / m;
        } else {
            ds[0][1] = 0;
        }

        if (m > 0) {
            ds[0][2] = ycm / m;
        } else {
            ds[0][2] = 0;
        }

        ds[0][3] = px;
        ds[0][4] = py;
        ds[0][5] = m;
        ds[0][6] = ke / 2;

        return ds;
    }

    /**
  *
  * Get the an identifier to the mouse data source.
  *
  * @return int the object identifier
  *

  public int getMouseID(){
      if(mouseDataSource==null) mouseDataSource=new MouseDataSource(this,mouseX,mouseY);
      return mouseDataSource.getID();
  }*/
    public Thing getThing(int id) {

        Thing t = null;

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            t = (Thing) e.nextElement();

            if (t.hashCode() == id) {
                return t;
            }
        }

        return null;
    }

    void clearTrails() {

        Thing t = null;

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            t = (Thing) e.nextElement();

            t.clearTrail();
        }
    }

    /**
     * Set the reference frame.
     *
     * The new reference frame is the frame in which the object is stationary.
     *
     * @param t the object from which the motion will be observed
     */
    public void setReferenceObject(Thing t) {

        boolean keepRunning = owner.clock.isRunning();

        owner.clock.stopClock();

        referenceObject = t;

        clearTrails();
        paintOSI();

        if (keepRunning) {
            owner.clock.startClock();
        } else {
            repaint();
        }
    }

    public Thing getReferenceObject() {
        return referenceObject;
    }

    public int addParametricCurve(int n, double start, double stop,
                                  String xStr, String yStr) {

        Thing t = new FunctionThing(this, n, start, stop, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addPolyShape(int n, int h[], int v[], String xStr,
                            String yStr) {

        Thing t = new ShapeThing(this, n, h, v, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    /**
     *    Add an interaction between two particles.
     *
     *    @param id1 int             The first particle.
     *    @param id2 int             The second particle
     *    @param force String        The force between the particles, f(r).
     *    @returns true if successful.
    */
    public boolean addInteraction(int id1, int id2, String f, String mode) {

        Thing t1 = getThing(id1);
        Thing t2 = getThing(id2);

        if ((t1 == null) || (t2 == null)) {
            return false;
        }

        mode = mode.trim();
        mode = mode.toLowerCase();

        int m;

        if (mode.equals("x")) {
            m = Interaction.XMODE;
        } else if (mode.equals("y")) {
            m = Interaction.YMODE;
        } else {
            m = Interaction.RMODE;
        }

        if ((t1 != null) && t1.dynamic) {
            t1.addInteraction(t2, f, m);
        }
        ;

        if ((t2 != null) && t2.dynamic) {
            t2.addInteraction(t1, f, m);
        }
        ;

        return true;
    }

    public boolean deleteObject(int id){
     Thing t=null;
     t=getThing(id);
     if(t==null) return false;
     if(owner!=null)owner.stop();  // this will stop the thread and wait for a join.
     things.removeElement(t);
     if(owner!=null){
         owner.removeDataSource(t.hashCode());
         owner.removeDataListener(t.hashCode());
         owner.cleanupDataConnections();
     }
      if (autoRefresh) {
          repaint();
      }
     return true;
  }

    public int addArrow(int size, String hStr, String vStr, String xStr,
                        String yStr) {

        Thing t = new Arrow(this, size, hStr, vStr, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addArrowStatic(int size, double hStr, double vStr,
                              String xStr, String yStr) {

        Thing t = new ArrowStatic(this, size, hStr, vStr, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addProtractor(int size,double theta, double theta0, String xStr, String yStr) {
        Thing t = new Protractor(this,size, theta, theta0, xStr, yStr);
        if (defaultColor != null) {
            t.color = defaultColor;
        }
        things.addElement(t);
        if (autoRefresh) {
            repaint();
        }
        return t.hashCode();
    }

    public int addPiston(int s, String x, String y, String xStr,
                         String yStr) {

        Thing t = new Piston(this, s, x, y, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addSpring(int s, String x, String y, String xStr,
                         String yStr) {

        Thing t = new Spring(this, s, x, y, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addCircle(int diameter, String xStr, String yStr) {

        Thing t = new Circle(this, diameter, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addDoppler(int diameter, String xStr, String yStr,
                          int numCrests, double period, double c) {

        Thing t = new Doppler(this, diameter, xStr, yStr, numCrests, period,
                              c);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addCharge(int diameter, String xStr, String yStr, double q) {

        Thing t = new Charge(this, diameter, xStr, yStr, q);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addShell(int diameter, String xStr, String yStr) {

        Thing t = new Shell(this, diameter, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addExShell(int tickness, String rStr, String xStr,
                          String yStr) {

        Thing t = new ExShell(this, tickness, rStr, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addBox(int w, int h, String xStr, String yStr) {

        Thing t = new Box(this, w, h, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addRectangle(int w, int h, String xStr, String yStr) {

        Thing t = new DrawnRectangle(this, w, h, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addText(String text, String calcStr, String xStr,
                       String yStr) {

        Thing t = new TextThing(this, text, calcStr, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addCalcThing(String text, String xStr, String yStr) {

        Thing t = new CalcThing(this, text, xStr, yStr);

        if (defaultColor != null) {
            t.color = defaultColor;
        }

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public int addCaption(String text, String calcStr, String xStr,
                          String yStr) {

        Thing t = new CaptionThing(this, text, calcStr, xStr, yStr);

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    /**
     *    Add a dragable cursor
     *
     *    @param int diameter   The diameter.
     *    @param double x    The initial x position.
     *    @param double y    The initial y position.
     *
    */
    public int addCursor(int diameter, String xStr, String yStr) {

        Thing t = new CursorThing(this, diameter, xStr, yStr);

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public MouseDataSource addMouseThing() {

        MouseDataSource mds = new MouseDataSource(this);

        things.addElement(mds);

        return mds;
    }

    public CollisionDataSource getCollisionThing() {
        if(collisionDataSource!=null) return collisionDataSource;
        collisionDataSource = new CollisionDataSource(this);
        things.addElement(collisionDataSource);
        return collisionDataSource;
    }

    public int addImage(Image im, String xStr, String yStr) {

        if (im == null) {
            return 0;
        }
        MediaTracker tracker = new MediaTracker(this);
        try {
            tracker.addImage(im, 0);
            tracker.waitForID(0, 1000);    // wait one second
        } catch (Exception e) {
            //return 0;
        }

        /*
        if (tracker.isErrorAny()) {
            return 0;
        }

        if (im.getHeight(this) < 1) {
            return 0;
        }
        */

        Thing t = new ImageThing(this, im, xStr, yStr);

        things.addElement(t);

        if (autoRefresh) {
            repaint();
        }

        return t.hashCode();
    }

    public void updateMouseMovedListeners(int xpix, int ypix) {

        Vector v;

        synchronized (things) {
            v = (Vector) things.clone();
        }

        for (Enumeration e = v.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            if (t instanceof MouseDataSource) {
                ((MouseDataSource) t).moveXY(xpix, ypix);
            }
        }
    }

    public void updateMouseDragListeners(double x, double y) {

        Vector v;

        synchronized (things) {
            v = (Vector) things.clone();
        }

        for (Enumeration e = v.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            if (t instanceof MouseDataSource) {
                ((MouseDataSource) t).dragXY(x, y);
            }
        }
    }

    public void updateMouseClickListeners(int xpix, int ypix) {

        Vector v;

        synchronized (things) {
            v = (Vector) things.clone();
        }

        for (Enumeration e = v.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            if (t instanceof MouseDataSource) {
                ((MouseDataSource) t).clickXY(xpix, ypix);
            }
        }
    }

    public void updateMouseReleaseListeners(int xpix, int ypix) {

        Vector v;

        synchronized (things) {
            v = (Vector) things.clone();
        }

        for (Enumeration e = v.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            if (t instanceof MouseDataSource) {
                ((MouseDataSource) t).releaseXY(xpix, ypix);
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }    //update usually does a rect fill with a background color.  We don't need this.

    public void paint() {
        Graphics g = getGraphics();
        if (g == null) {
            return;
        }

        if (osi == null) {
            return;
        }

        synchronized (osi) {
            paintOSI();
            g.drawImage(osi, 0, 0, this);    // draw the image onto the visible graph

            if (mouseDrag && sketchMode && (sketchImage != null)) {
                g.drawImage(sketchImage, mouseX,
                            mouseY - sketchImage.getHeight(this), this);
            }
            g.dispose();
            Toolkit tk = Toolkit.getDefaultToolkit();
            tk.sync();
        }
    }

    public void paint(Graphics g) {
        if(!autoRefresh) return;
        if ((getSize().width == 0) || (getSize().height == 0)) {
            return;
        }

        if ((osi == null) || (iwidth != this.getSize().width)
                || (iheight != this.getSize().height)) {
            iwidth  = this.getSize().width;
            iheight = this.getSize().height;
            osi     = createImage(iwidth, iheight);    //create an off screen image
        }

        if (owner.isClockRunning()) {
            return;    // let the animation repaint the screen
        }

        if (osi == null) {
            return;
        }

        synchronized (osi) {
            paintOSI();
            g.drawImage(osi, 0, 0, this);    // draw the image onto the visible graph
        }
    }

    public void paintOSI() {

        int xo = iwidth / 2 - xOffset;
        int yo = iheight / 2 + yOffset;

        if (osi == null) {
            return;
        }

        Graphics osg = osi.getGraphics();    // a graphics context for the  off screen image

        if (osg == null) {
            return;
        }

        int xref = 0, yref = 0;

        if (referenceObject != null) {
            xref = pixFromX(referenceObject.getX()) - pixFromX(0);
            yref = pixFromY(referenceObject.getY()) - pixFromY(0);
        }

        xOffset += xref;
        yOffset -= yref;

        osg.setColor(getBackground());
        osg.fillRect(0, 0, iwidth, iheight);

        if (backgroundImage != null) {
            osg.drawImage(backgroundImage, 0, 0, this);
        }

        osg.clipRect(0, 0, iwidth, iheight);
        paintThingsBeforeGrid(osg);
        paintGrid(osg, xo, yo);
        paintThingsAfterGrid(osg);

        xOffset -= xref;
        yOffset += yref;

        paintTime(osg);
        paintMessage(osg);

        if (mouseDrag) {
            this.paintCoords(osg, mouseX, mouseY);
        }

        osg.dispose();
    }

    void setTime() {

        time = owner.clock.getTime();

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            t.setTime(time, owner.dt / 10000.0);    // will clear trails.
        }

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            t.setVarsFromMaster();
        }

        dynamics.resetDynamicsVariables();

        if (owner != null) {
            owner.updateDataConnections();
        }

        if (autoRefresh) {
            paint();
        }
    }

    public void step(double dt, double time) {
        if(dt==0) return;
        if (testForCollision()) {
            if ((owner.label_collision != null) &&!owner.label_collision.equals("")) {
                setMessage(owner.label_collision);
            }
            owner.stopClock();

            return;
        }

        bounceThings();
        dynamics.step(dt, time);    // this moves those things that obey Newton's laws.  Time will be advanced by ode.

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            if (!t.dynamic && t.noDrag && (t.myMaster == null)) {
                t.setVars(this.time + dt, dt);    // update using trajectories.
            }

            if (!t.dynamic) {
                t.vars[0] = this.time + dt;    // make sure time is ALWAYS updated.
            }

            if ((t.myMaster == null) &&!(t instanceof TrailThing)) {
                t.incTrail();    // slaves will incTrail in next loop.
            }
        }

        if (dragThing != null) {
            dragThing.vars[0] = this.time + dt;
            dragThing.vars[3] = 0;
            dragThing.vars[4] = 0;
        }

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            if (t.myMaster != null) {    // update only the slaves since everything else has been updated.
                t.setVarsFromMaster();
                t.incTrail();
            }
        }

        this.time = time + dt;

        if (owner != null) {
            owner.updateDataConnections();
        }

        paint();
    }

    public void setMessage(String msg) {

        if (((message == null) && (msg == null))
                || (message != null) && message.equals(msg)) {
            return;
        }

        message = msg;

        paintMessage();

        if (autoRefresh) {
            repaint();
        }
    }

    /**
     *    Add a caption to the applet.
     *
     *    @param s        The caption.
    */
    public int setCaption(String s) {

        int id = addCaption(s, null, "0", "0");

        if (autoRefresh) {
            repaint();
        }

        return id;
    }

    /*
        public void paintCaption(Graphics osg){
            if(caption==null) return;
            osg.setColor(Color.black);
            Font f=osg.getFont();
            osg.setFont(captionFont);
            FontMetrics fm=osg.getFontMetrics(captionFont);
            osg.drawString(caption,(iwidth-fm.stringWidth(caption))/2, 25);
            osg.setFont(f);
        }
    */
    public void paintThingsBeforeGrid(Graphics g) {

        Vector v;

        synchronized (things) {
            v = (Vector) things.clone();
        }

        for (Enumeration e = v.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            if (t.paintBeforeGrid) {
                t.paint(g);
            }
        }
    }

    public void paintThingsAfterGrid(Graphics g) {

        Vector v;

        synchronized (things) {
            v = (Vector) things.clone();
        }

        for (Enumeration e = v.elements(); e.hasMoreElements(); ) {
            Thing t = (Thing) e.nextElement();

            if (!t.paintBeforeGrid) {
                t.paint(g);
            }
        }
    }

    void paintTime(Graphics osg) {

        osg.setColor(Color.black);

        Font f = osg.getFont();

        osg.setFont(boldFont);

        String tStr = new Format("%7.4g").form(SUtil.chop(time, 1.0e-12));

        if (timeDisplay) {
            if (iwidth > 150) {
                osg.drawString(owner.label_time+": " + tStr, 10, 15);
            } else {
                osg.drawString(owner.label_time+": " + tStr, 10, iheight - 40);
            }
        }

        osg.setFont(f);
    }

    void paintGrid(Graphics osg, int xo, int yo) {

        osg.setColor(Color.lightGray);

        int gridSpace = (int) Math.round(pixPerUnit * gridUnit);

        if (gridSpace > 0) {
            for (int i = xo % gridSpace; i < iwidth; i = i + gridSpace) {
                osg.drawLine(i, 0, i, iheight);
            }
        }

        if (gridSpace > 0) {
            for (int i = yo % gridSpace; i < iheight; i = i + gridSpace) {
                osg.drawLine(0, i, iwidth, i);
            }
        }
    }

    void paintMessage() {

        Graphics g = getGraphics();
        if(g!=null){
          paintMessage(g);
          g.dispose();
        }
    }

    void paintMessage(Graphics osg) {
        String tempMsg=message; // reference for thread safety
        if (tempMsg == null || osg==null) {
            return;
        }

        FontMetrics fm = osg.getFontMetrics(osg.getFont());

        osg.setColor(Color.yellow);

        int w = 15 + fm.stringWidth(tempMsg);

        osg.fillRect(iwidth - w - 5, iheight - 15, w, 15);
        osg.setColor(Color.black);
        osg.drawString(tempMsg, iwidth - w + 2, iheight - 3);
        osg.drawRect(iwidth - w - 5, iheight - 15, w, 15);
    }

    void paintCoords(int xPix, int yPix) {

        Graphics g = getGraphics();
        if(g!=null){
          paintCoords(g, xPix, yPix);
          g.dispose();
        }
    }

    void paintCoords(Graphics g, int xPix, int yPix) {

        if (!coordDisplay) {
            return;
        }

        String             msg = "" + mouseFormat.form(xFromPix(xPix))
                                 + " , " + mouseFormat.form(yFromPix(yPix));
        java.awt.Rectangle r   = this.getBounds();

        g.setColor(Color.yellow);

        FontMetrics fm = g.getFontMetrics(g.getFont());

        boxWidth = Math.max(20 + fm.stringWidth(msg), boxWidth);

        g.fillRect(0, r.height - 20, boxWidth, 20);
        g.setColor(Color.black);
        g.drawString(msg, 10, r.height - 5);
        g.drawRect(0, r.height - 20, boxWidth - 1, 20);
    }

    double xFromPix(int x) {

        int xo = iwidth / 2 - xOffset;

        return (x - xo) / (1.0 * pixPerUnit);
    }

    double yFromPix(int y) {

        int yo = iheight / 2 + yOffset;

        return -(y - yo) / (1.0 * pixPerUnit);
    }

    int pixFromX(double x) {

        int xpix = (int) (iwidth / 2 + x * pixPerUnit - xOffset);

        return xpix;
    }

    int pixFromY(double y) {

        int ypix = (int) (iheight / 2 - y * pixPerUnit + yOffset);

        return ypix;
    }

    void setDefault() {

        defaultColor    = null;
        referenceObject = null;
        dragThing       = null;
        collisionDataSource=null;

        this.setBackground(Color.white);

        this.xOffset = 0;
        this.yOffset = 0;
        dynamics     = new DynamicsSolver();

        //things.removeAllElements();
        Vector v;
        Thing t;
        synchronized(things){v=(Vector)things.clone(); things.removeAllElements();}
        for( Enumeration e=v.elements(); e.hasMoreElements();){
            t= (Thing) e.nextElement();
            owner.removeDataListener(t.hashCode() );
            owner.removeDataSource(t.hashCode() );
        }

        if (autoRefresh) {
            repaint();
        }
    }

    public final boolean setFormat(String str) {

        try {
            mouseFormat = new Format(str);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    public int setSketchMode(boolean sm) {

        //Applet applet=edu.davidson.graphics.Util.getApplet(this);
        sketchImage = edu.davidson.graphics.Util.getImage("pencil.gif",
                owner);

        //int xoff = 0;
        //int yoff = 29;

        sketchMode = sm;

        if (!sm) {
            trailThing = null;

            return 0;
        }

        //trailThing=new TrailThing(this,1);
        //trailThing.trailSize=2000;

        /*
         // the following code only works on Java 1.2.
         Toolkit tk=Toolkit.getDefaultToolkit();
         int maxColors=tk.getMaximumCursorColors();
         if(maxColors==0 || im==null){
            System.out.println("Custom cursors not supported or cursor image not found.");
            sketchCursor=null;
            return trailThing.hashCode();
         }
         sketchCursor=tk.createCustomCursor(im,new Point(xoff,yoff),"sketch cursor");
         setCursor(sketchCursor);
         */
        sketchCursor = null;

        //return trailThing.hashCode();
        return 0;
    }

    /**
     * Swap the drawing order on the screen.
     *
     *  @param id1   The first id of a screen object.
     *  @param id2   The second id of a screen object.
     *
     *  @returns true if successful.
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

        if (autoRefresh) {
            repaint();
        }

        return true;
    }

    void highlightMySlaves(Thing master, Graphics g) {

        Thing slave = null;

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            slave = (Thing) e.nextElement();

            if (slave.myMaster == master) {
                slave.paintHighlight(g);
            }
        }
    }

    void paintMyConnectors(Thing t, Graphics g) {

        Thing connector = null;

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            connector = (Thing) e.nextElement();

            if ((connector instanceof Connector)
                    && ((t == ((Connector) connector).thing1)
                        || (t == ((Connector) connector).thing2))) {
                connector.paint(g);
            }
        }
    }

    // mouse stuff
    public boolean isEnableMouse() {
        return enableMouse;
    }

    boolean isInsideDragableThing(int x, int y) {

        Thing t = null;

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            t = (Thing) e.nextElement();

            if ((!t.noDrag || t.resizable) && t.isInsideThing(x, y)) {
                return true;
            }
        }

        return false;
    }

    boolean isInsideStickyThing(int x, int y, Thing exclude) {

        // exclude is a dynamic object with position x,y
        // it needs to be excluded since an object is always inside itself.
        Thing t = null;

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            t = (Thing) e.nextElement();

            //  !t.dynamic  we only want to check for collision with static objects.
            if ( (!t.dynamic) &&   // t must be static
                 (t != exclude) && // an object cannot be inside itself
                 t.sticky &&       // t is sticky
                 (t.isInsideThing(x, y) ||  // x and y are the exclude object
                  exclude.isInsideThing(pixFromX(t.vars[1]), pixFromY(t.vars[2]) ) ) ) {
                return true;
            }

        }

        return false;
    }

    void bounceMe(int x, int y, Thing me) {

        Thing t = null;

        for (Enumeration e = things.elements(); e.hasMoreElements(); ) {
            t = (Thing) e.nextElement();

            if ((t != me) && t.bouncy && t.isInsideThing(x, y)) {    // bounce me if I am inside a bouncy thing
                double dx = Math.abs(me.vars[1] - t.vars[1])
                            - t.w / pixPerUnit / 2.0;
                double dy = Math.abs(me.vars[2] - t.vars[2])
                            - t.h / pixPerUnit / 2.0;

                if (Math.abs(dx) < Math.abs(dy)) {    // closer to an x surface so bounce the x velocity
                    if ((me.vars[3] < t.vars[3])
                            && (me.vars[1] > t.vars[1])) {
                        me.vars[3] = -me.vars[3];

                        dynamics.resetDynamicsVariable(me);
                    } else if ((me.vars[3] > t.vars[3])
                               && (me.vars[1] < t.vars[1])) {
                        me.vars[3] = -me.vars[3];

                        dynamics.resetDynamicsVariable(me);
                    }
                } else {    // closer to an y surface so bounce the y velocity
                    if ((me.vars[4] < t.vars[4])
                            && (me.vars[2] > t.vars[2])) {
                        me.vars[4] = -me.vars[4];

                        dynamics.resetDynamicsVariable(me);
                    } else if ((me.vars[4] > t.vars[4])
                               && (me.vars[2] < t.vars[2])) {
                        me.vars[4] = -me.vars[4];

                        dynamics.resetDynamicsVariable(me);
                    }
                }
            }
        }
    }

    public boolean testForCollision() {

        if (stickyCount == 0) {
            return false;    // we do NOT have a sticky object so don't do the test.
        }

        Thing t;
        int   n = things.size();

        stickyCount = 0;

        for (int i = 0; i < n; i++) {
            t = (Thing) things.elementAt(i);

            if (t.sticky && t.dynamic) {    // both things have to be sticky to stop the clock.  One object needs to be dynamic.
                stickyCount++;
                if (isInsideStickyThing(pixFromX(t.vars[1]),pixFromY(t.vars[2]), t)) {
                    if(collisionDataSource!=null){
                      collisionDataSource.setXY(t.vars[1],t.vars[2]);
                      collisionDataSource.setBlock(false);
                      owner.updateDataConnection(collisionDataSource.hashCode());
                      collisionDataSource.setBlock(true);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void bounceThings() {

        if (bouncyCount == 0) {
            return;    // we do NOT have a bouncy object so don't do the test.
        }

        Thing t;
        int   n = things.size();

        bouncyCount = 0;

        for (int i = 0; i < n; i++) {
            t = (Thing) things.elementAt(i);

            if (t.dynamic) {    //Object needs to be dynamic to bounce
                bouncyCount++;

                bounceMe(pixFromX(t.vars[1]), pixFromY(t.vars[2]), t);
            }
        }
    }

    /**
    *    Enable the graph to respond to mouse actionions.
    *
    *    @param em   Enable the coordinate display when the mouse is pressed.
    *
   */
    public void setEnableMouse(boolean em) {

        if (enableMouse == em) {
            return;
        }

        enableMouse = em;

        if (enableMouse) {
            addMouseMotionListener(mouseMotionAdapter =
                new Animator_mouseMotionAdapter(this));
            addMouseListener(mouseAdapter = new Animator_mouseAdapter(this));
        } else {
            removeMouseMotionListener(mouseMotionAdapter);
            removeMouseListener(mouseAdapter);
        }
    }

    void this_mousePressed(MouseEvent e) {

        if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {

            // SFrame graphFrame=new SFrame((Animator)this.clone());
            // graphFrame.setSize(this.getSize().width,this.getSize().height);
            // graphFrame.show();
        } else {
            mouseDrag = true;
            mouseX    = e.getX();
            mouseY    = e.getY();

            //if(mouseDataSource!=null)mouseDataSource.clickXY(mouseX,mouseY);
            updateMouseClickListeners(mouseX, mouseY);

            Graphics g = getGraphics();
            int      n = things.size();

            for (int i = 0; i < n; i++) {
                Thing t = (Thing) things.elementAt(i);

                if ((!t.noDrag || t.resizable)
                        && t.isInsideThing(mouseX, mouseY)) {
                    dragThing = t;
                }
            }

            if (dragThing != null) {
                mouseX = pixFromX(dragThing.getX());
                mouseY = pixFromY(dragThing.getY());

                double x = xFromPix(mouseX);
                double y = yFromPix(mouseY);

                if (dragThing.dynamic) {
                    dynamics.resetDynamicsDragThingVariables(x, y);
                } else {
                    if (!(dragThing instanceof ArrowStatic)) {
                        dragThing.setXY(x, y);
                    }

                    if (dampOnMousePressed ) {
                        dragThing.setVX(0);
                        dragThing.setVY(0);
                    }
                }

                dragThing.updateMySlaves();
                owner.updateDataConnection(dragThing.hashCode());
                g.setXORMode(getBackground());
                dragThing.paintHighlight(g);

                //dragThing.paint(g);
                highlightMySlaves(dragThing, g);
                g.setPaintMode();
            }

            g.dispose();
            paintCoords(mouseX, mouseY);
        }

        if (sketchMode) {
            if (trailThing == null) {
                trailThing           = new TrailThing(this, 1);
                trailThing.trailSize = 2000;

                things.addElement(trailThing);
            }

            int maxPix = iwidth;
            int minPix = 0;

            if (mouseX < minPix) {
                mouseX = minPix;
            } else if (mouseX > maxPix - 2) {
                mouseX = maxPix - 2;
            }

            double x = xFromPix(mouseX);

            minPix = 0;
            maxPix = iheight;

            if (mouseY < minPix) {
                mouseY = minPix;
            } else if (mouseY > maxPix - 2) {
                mouseY = maxPix - 2;
            }

            double y = yFromPix(mouseY);

            trailThing.incTrail(x, y);
            owner.clearData(trailThing.hashCode());
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            this_mouseDragged(e);
        }
    }

    void this_mouseDragged(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();

        double x;
        double y;
        int    maxPix = iwidth;
        int    minPix = 0;

        if (mouseX < minPix) {
            mouseX = minPix;
        } else if (mouseX > maxPix - 2) {
            mouseX = maxPix - 2;
        }

        x      = xFromPix(mouseX);
        minPix = 0;
        maxPix = iheight;

        if (mouseY < minPix) {
            mouseY = minPix;
        } else if (mouseY > maxPix - 2) {
            mouseY = maxPix - 2;
        }

        y = yFromPix(mouseY);

        //if(mouseDataSource!=null)mouseDataSource.dragXY(x,y);
        updateMouseDragListeners(x, y);

        if (dragThing != null) {
            if (dragThing.dynamic) {
                dynamics.resetDynamicsDragThingVariables(x, y);
            } else {
                dragThing.setXY(x, y);

                if (dampOnMousePressed) {
                    dragThing.setVX(0);
                    dragThing.setVY(0);
                }
            }

            dragThing.updateMySlaves();

            if (!owner.isClockRunning()) {
                owner.updateDataConnection(dragThing.hashCode());
                paint();
            }
        } else if (sketchMode && (trailThing != null)) {
            trailThing.incTrail(x, y);

            Graphics g = getGraphics();

            paint(g);

            //g.drawImage(osi2,0,0,this);
            //paintCoords(g,x,y);
            if (sketchImage != null) {
                g.drawImage(sketchImage, mouseX,
                            mouseY - sketchImage.getHeight(this), this);
            }

            trailThing.paint(g);
            g.dispose();
            owner.updateDataConnection(trailThing.hashCode());
        }

        paintCoords(mouseX, mouseY);
    }

    void this_mouseReleased(MouseEvent e) {

        mouseDrag = false;

        java.awt.Rectangle r = getBounds();

        if (sketchMode && (trailThing != null)) {

            //contour.attachDataSet( trailThing.dataset);
            //things.addElement(trailThing);
            owner.updateDataConnection(trailThing.hashCode());

            trailThing = null;
        }

        if ((dragThing == null) &&!sketchMode) {
            repaint(0, r.height - 20, boxWidth, 20);
        } else {
            paint();
        }

        dragThing = null;

        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));    // make sure we have the correct cursor

        boxWidth = 0;    // reset the yellow message box.

        if (e == null) {
            return;
        }

        mouseX = e.getX();
        mouseY = e.getY();

        //if(mouseDataSource!=null)mouseDataSource.releaseXY(mouseX,mouseY);
        updateMouseReleaseListeners(mouseX, mouseY);
    }

    public void this_mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void this_mouseExited(MouseEvent e) {

        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        // repaint();
    }

    public void this_mouseMoved(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();

        //if(mouseDataSource!=null)mouseDataSource.moveXY(mouseX,mouseY);
        updateMouseMovedListeners(mouseX, mouseY);

        if (isInsideDragableThing(mouseX, mouseY)) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    // inner class to solve particle dynamics
    final class DynamicsSolver implements SDifferentiable {

        int              n         = 1;
        private SRK45    odeSolver = new SRK45();
       // private SRK4    odeSolver = new SRK4();
        private double[] dxdt      = new double[1];    // rate of all the dynamics objects (t,x1,y1,vx1,vy1,x2,y2,vx2,vy2....)
        double[] vars      = new double[1];    // state of all the dynamics objects (t,x1,y1,vx1,vy1,x2,y2,vx2,vy2....)
        Vector   dThings   = new Vector();

        public DynamicsSolver() {
            odeSolver.setDifferentials(this);
        }

        final public double[] rate(double[] x) {    // calculate the rate given t,x,y,vx,vy

            Thing t;

            if (4 * dThings.size() != n - 1) {
                return new double[n];
            }    // error check.

            updateThings();    // painful but necessary!  Make sure all the particle positions are up to date so forces are correct.

            dxdt[0] = 1;    //time

            for (int i = 1; i < n; i = i + 4) {
                t = (Thing) dThings.elementAt((i - 1) / 4);

                if (t.constrainX) {
                    dxdt[i] = 0;
                } else {
                    dxdt[i] = x[i + 2];                  //  dX/dt
                }

                if (t.constrainY) {
                    dxdt[i + 1] = 0;
                } else {
                    dxdt[i + 1] = x[i + 3];              // dY/dt
                }

                if (t.constrainR) {
                    t.constrainedRForce(i, dxdt);
                } else {
                    dxdt[i + 2] = t.getFx() / t.mass;    // d Vx/dt
                    dxdt[i + 3] = t.getFy() / t.mass;    // d Vy/dt
                }
            }

            return dxdt;
        }

        final public int getNumEqu() {
            return n;
        }

        synchronized void addRateEquation(Thing t) {

            boolean shouldRun = false;

            if (owner.isClockRunning()) {
                shouldRun = true;

                owner.pause();
            }

            dThings.addElement(t);

            dxdt    = new double[n + 4];    // each particle add 4 ODEs to the set.
            vars    = new double[n + 4];
            vars[0] = time;
            n       += 4;

            for (int i = 1; i < n; i = i + 4) {
                t           = (Thing) dThings.elementAt((i - 1) / 4);
                vars[i]     = t.vars[1];    //  x
                vars[i + 1] = t.vars[2];    //  y
                vars[i + 2] = t.vars[3];    // vx
                vars[i + 3] = t.vars[4];    // vy
            }

            t.vars[5] = t.getTotalFx() / t.mass;    // d Vx/dt
            t.vars[6] = t.getTotalFy() / t.mass;    // d Vy/dt

            odeSolver.setDifferentials(this);

            if (shouldRun) {
                owner.forward();
            }
        }

        //update the dynamics variables from the Things
        synchronized void resetDynamicsDragThingVariables(double x,
                double y) {

            boolean shouldRun = false;

            if (owner.isClockRunning()) {
                shouldRun = true;

                owner.pause();
            }

            vars[0] = time;

            for (int i = 1; i < n; i = i + 4) {
                Thing t = (Thing) dThings.elementAt((i - 1) / 4);

                if (t == dragThing) {
                    t.vars[1] = x;
                    t.vars[2] = y;

                    if (dampOnMousePressed) {
                        t.vars[3] = 0;
                        t.vars[4] = 0;
                    }

                    t.enforceConstraintOnXY();

                    vars[i]     = t.vars[1];    //  x
                    vars[i + 1] = t.vars[2];    //  y
                    vars[i + 2] = t.vars[3];
                    ;                           // vx
                    vars[i + 3] = t.vars[4];
                    ;                           // vy
                    t.vars[5]   = 0;            // d Vx/dt
                    t.vars[6]   = 0;            // d Vy/dt
                }
            }

            if (shouldRun) {
                owner.forward();
            }
        }

        //update the dynamics variables of a single thing
        synchronized void resetDynamicsVariable(Thing t) {

            int i = dThings.indexOf(t);

            i           = 1 + i * 4;
            vars[i]     = t.vars[1];    //  x
            vars[i + 1] = t.vars[2];    //  y
            vars[i + 2] = t.vars[3];    // vx
            vars[i + 3] = t.vars[4];    // vy
        }

        //update the dynamics variables from the Things
        synchronized void resetDynamicsVariables() {

            boolean shouldRun = false;

            if (owner.isClockRunning()) {
                shouldRun = true;

                owner.pause();
            }

            vars[0] = time;

            for (int i = 1; i < n; i = i + 4) {
                Thing t = (Thing) dThings.elementAt((i - 1) / 4);

                vars[i]     = t.vars[1];                  //  x
                vars[i + 1] = t.vars[2];                  //  y
                vars[i + 2] = t.vars[3];                  // vx
                vars[i + 3] = t.vars[4];                  // vy
                t.vars[5]   = t.getTotalFx() / t.mass;    // d Vx/dt
                t.vars[6]   = t.getTotalFy() / t.mass;    // d Vy/dt
            }

            if (shouldRun) {
                owner.forward();
            }
        }

        void updateThings() {

            Thing t;

            for (int i = 1; i < n; i = i + 4) {
                t         = (Thing) dThings.elementAt((i - 1) / 4);
                t.vars[0] = vars[0];        //  t
                t.vars[1] = vars[i];        //  x
                t.vars[2] = vars[i + 1];    //  y
                t.vars[3] = vars[i + 2];    // vx
                t.vars[4] = vars[i + 3];    // vy
            }
        }

        void setTolerance(double t) {
            odeSolver.setTol(t);
        }

        void step(double dt, double time) {

            if (dt == 0) {
                return;
            }

            Thing t;

            if (n < 2) {
                return;
            }

            vars[0] = time;    // time at the beginning of the step.

            odeSolver.step(dt, vars);

            //double err=vars[0]-dt-time;    //Time sould have increaded by dt if the solver worked.
            // System.out.println("Time error="+err);
            for (int i = 1; i < n; i = i + 4) {
                t         = (Thing) dThings.elementAt((i - 1) / 4);
                t.vars[0] = time + dt;                  //  t
                t.vars[1] = vars[i];                    //  x
                t.vars[2] = vars[i + 1];                //  y
                t.vars[3] = vars[i + 2];                // vx
                t.vars[4] = vars[i + 3];                // vy

                if (t.enforceConstraintOnXY()) {    // returns true if there is a constraint in effect

                    // vars[i]=t.vars[1];         //  x
                    // vars[i+1]=t.vars[2];       //  y
                    vars[i + 2] = t.vars[3];    // vx      // turn it around if it hits the min or max.
                    vars[i + 3] = t.vars[4];            // vy
                }

                t.vars[5] = t.getTotalFx() / t.mass;    // d Vx/dt
                t.vars[6] = t.getTotalFy() / t.mass;    // d Vy/dt

                if (t == dragThing) {
                    vars[i]     = xFromPix(mouseX);
                    vars[i + 1] = yFromPix(mouseY);
                    vars[i + 2] = 0;                    // vx
                    vars[i + 3] = 0;                    // vy
                }
            }

            if (dragThing != null && dragThing.dynamic) {    // do not change the position if we are in drag mode.
                dragThing.vars[1] = xFromPix(mouseX);
                dragThing.vars[2] = yFromPix(mouseY);

                if (dampOnMousePressed) {
                    dragThing.vars[3] = 0;
                    dragThing.vars[4] = 0;
                }

                dragThing.enforceConstraintOnXY();

                dragThing.vars[5] = dragThing.getTotalFx() / dragThing.mass;    // d Vx/dt
                dragThing.vars[6] = dragThing.getTotalFy() / dragThing.mass;    // d Vy/dt
            }
        }    // end of step()
    }    // end of DynamicsSolver
}

class Animator_mouseAdapter extends java.awt.event.MouseAdapter {

    AnimatorCanvas adaptee;

    Animator_mouseAdapter(AnimatorCanvas adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseEntered(MouseEvent e) {
        adaptee.this_mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        adaptee.this_mouseExited(e);
    }

    public void mousePressed(MouseEvent e) {
        adaptee.this_mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        adaptee.this_mouseReleased(e);
    }
}

class Animator_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {

    AnimatorCanvas adaptee;

    Animator_mouseMotionAdapter(AnimatorCanvas adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseMoved(MouseEvent e) {
        adaptee.this_mouseMoved(e);
    }

    public void mouseDragged(MouseEvent e) {
        adaptee.this_mouseDragged(e);
    }
}

