package  animator4;

import java.awt.BorderLayout;
import a2s.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;

import edu.davidson.display.Format;
import edu.davidson.graphics.SFrame;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SUtil;


/**
 * <p>Animator is designed to animate geometric shapes or images on the screen.  Objects can move
 * along a predefined trajectory or in response to a force. </p>
 * <p>The following embedding parameters are defined:</p>
 * <div align="center">
 * <center>
 * <table border="2">
 * <tr>
 * <th align="center">Parameter</th>
 * <th align="center">&nbsp;Value </th>
 * <th align="center">Description</th>
 * </tr>
 * <tr>
 * <td align="center">FPS</td>
 * <td align="center">10</td>
 * <td>Frames per second.</td>
 * </tr>
 * <tr>
 * <td align="center">dt</td>
 * <td align="center">0.1</td>
 * <td>Animation time step per frame.</td>
 * </tr>
 * <tr>
 * <td align="center">ShowControls</td>
 * <td align="center">true</td>
 * <td>Show VCR buttons at bottom of applet.</td>
 * </tr>
 * <tr>
 * <td align="center">GridUnit</td>
 * <td align="center">1.0</td>
 * <td>The grid spacing.&nbsp; A value of 0 will suppress the grid.</td>
 * </tr>
 * <tr>
 * <td align="center">PixPerUnit</td>
 * <td align="center">10</td>
 * <td>Conversion factor from pixel units to world units.</td>
 * </tr>
 * </table>
 * </center>
 * </div>
 * <p>Various objects in Animator implement the data source interface.&nbsp;
 * This interface, SDataSource, enables inter-applet data passing between
 * Physlets.</p>
 * <div align="center">
 * <center>
 * <table border="2" height="115">
 * <tr>
 * <th height="19" align="center">Object</th>
 * <th height="19" align="center">Identifier</th>
 * <th height="19" align="center">Variables</th>
 * </tr>
 * <tr>
 * <td height="38">images</td>
 * <td height="38">id=addImage(String file,String xStr,String yStr)</td>
 * <td height="38">t, x, y, vx, vy, ax, ay, m</td>
 * </tr>
 * <tr>
 * <td height="38">All shapes: circle, rectangle, box, arrow, etc.</td>
 * <td height="38">The id is returned when an object is created using an add
 * method.</td>
 * <td height="38">t, x, y, vx, vy, ax, ay, m</td>
 * </tr>
 * <tr>
 * <td height="15">clock</td>
 * <td height="15">id=getClockID()</td>
 * <td height="15">t</td>
 * </tr>
 * <tr>
 * <td height="19">ensemble</td>
 * <td height="19">id=getEnsembleID()</td>
 * <td height="19">t, xcm, ycm, px, py , m , ke</td>
 * </tr>
 * </table>
 * </center>
 * </div>
 *
 * @author             Wolfgang Christian
 * @version            1.5c 1999/07/21
 */
public class Animator extends SApplet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String label_time = "Time";
    String label_collision = "collision";
    private String button_start = "Play";
    private String button_stop = "Pause";
    private String button_reset = "Reset";
    private String button_forward = ">>";
    private String button_back = "<<";
    private String message = null;
    int fps = 10;
    int ppu = 10;
    double gridUnit = 1.0;
    boolean showControls;
    double dt;
    edu.davidson.graphics.EtchedBorder controlPanel = new edu.davidson.graphics.EtchedBorder();
    edu.davidson.graphics.EtchedBorder etchedBorder2 = new edu.davidson.graphics.EtchedBorder();
    Button playBtn = new Button();
    Button pauseBtn = new Button();
    FlowLayout flowLayout2 = new FlowLayout();
    Button stepBackBtn = new Button();
    Button stepForwardBtn = new Button();
    Button resetBtn = new Button();
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    AnimatorCanvas animatorCanvas = new AnimatorCanvas(this);

    /**
     * @y.exclude
     */
    public Animator () {
    }

    /**
     * Sets the resource strings.
     */
    protected void setResources () {
        label_time = localProperties.getProperty("label.time", label_time);
        label_collision = localProperties.getProperty("label.collision", label_collision);
        button_start = localProperties.getProperty("button.start", button_start);
        button_stop = localProperties.getProperty("button.stop", button_stop);
        button_reset = localProperties.getProperty("button.reset", button_reset);
        button_forward = localProperties.getProperty("button.forward", button_forward);
        button_back = localProperties.getProperty("button.back", button_back);
    }

    /**
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     */
    public void init () {
        initResources(null);
        try {
        	String defFPS = "10";
            fps = Integer.parseInt(this.getParameter("FPS", defFPS));
        	/**
        	 * @j2sNative
        	 * 
        	 * fps *= 2;
        	 */

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ppu = Integer.parseInt(this.getParameter("PixPerUnit", "10"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            showControls = Boolean.valueOf(this.getParameter("ShowControls",
                    "true")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dt = Double.valueOf(this.getParameter("dt", "0.1")).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            gridUnit = Double.valueOf(this.getParameter("GridUnit", "1.0")).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controlPanel.setVisible(showControls);
        animatorCanvas.pixPerUnit = ppu;
        animatorCanvas.gridUnit = gridUnit;
        clock.addClockListener(animatorCanvas);
        clock.setDt(dt);
        clock.setFPS(fps);
        // testing scripts.  \
        /*
         *  int id1=addObject("rectangle","x=0,y=1,h=20,w=45,m=40");
         *  setForce(id1,"0","-100*m",0,1,0,0);
         *   setConstrainX(id1,0,1,100);
         *  setShowConstraintPath(id1, true);
         *
         *  int id2=addObject("circle","x=0, y=13.4,m=50,r=30");
         *  setConstrainX(id2,0,6,100);
         *  setShowConstraintPath(id2, true);
         *  setForce(id2,"0","-9.8*m",0,14.6,0,0);
         *  setDragable(id2,true);
         *
         *  addInteraction(id2,id1,"40000*step(-r+4.0)*(-r+4.0)-550*(r-15)", "r");
         *
         */
        //Arrow("5", "0", "0", "0");
        /*
         * //this.setTolerance(1.0e-3);
         * int id=addObject("charge","x=2, q=-10,r=30,m=100");
         * setShowFVector(id,true);
         * setDragable(id,true);
         *
         * id=addObject("charge","x=-2,q=10");
         * setShowFVector(id,true);
         * setDragable(id,true);
         */
        //addObject("calculation","text=this is a test");
        //int id=addCircle(15,"0.8","17.2");
        //this.setForce(id,"5","0",0,5,0,0);
        //this.setConstrainR(id,5.0,0,0);
        //this.setDragable(id,true);
    }

    //Component initialization
    private void jbInit () throws Exception {
        controlPanel.setLayout(flowLayout2);
        etchedBorder2.setLayout(borderLayout1);
        this.setBackground(Color.lightGray);
        playBtn.setLabel(button_start);
        playBtn.addActionListener(new java.awt.event.ActionListener() {

            /**
             * put your documentation comment here
             * @param e
             */
            public void actionPerformed (ActionEvent e) {
                playBtn_actionPerformed(e);
            }
        });
        pauseBtn.setLabel(button_stop);
        pauseBtn.addActionListener(new java.awt.event.ActionListener() {

            /**
             * put your documentation comment here
             * @param e
             */
            public void actionPerformed (ActionEvent e) {
                pauseBtn_actionPerformed(e);
            }
        });
        stepBackBtn.setLabel(button_back);
        stepBackBtn.addActionListener(new java.awt.event.ActionListener() {

            /**
             * put your documentation comment here
             * @param e
             */
            public void actionPerformed (ActionEvent e) {
                stepBackBtn_actionPerformed(e);
            }
        });
        stepForwardBtn.setLabel(button_forward);
        stepForwardBtn.addActionListener(new java.awt.event.ActionListener() {

            /**
             * put your documentation comment here
             * @param e
             */
            public void actionPerformed (ActionEvent e) {
                stepForwardBtn_actionPerformed(e);
            }
        });
        resetBtn.setLabel(button_reset);
        resetBtn.addActionListener(new java.awt.event.ActionListener() {

            /**
             * put your documentation comment here
             * @param e
             */
            public void actionPerformed (ActionEvent e) {
                resetBtn_actionPerformed(e);
            }
        });
        this.setLayout(borderLayout2);
        this.add(controlPanel, BorderLayout.SOUTH);
        controlPanel.add(stepBackBtn, null);
        controlPanel.add(playBtn, null);
        controlPanel.add(stepForwardBtn, null);
        controlPanel.add(pauseBtn, null);
        controlPanel.add(resetBtn, null);
        this.add(etchedBorder2, BorderLayout.CENTER);
        etchedBorder2.add(animatorCanvas, BorderLayout.CENTER);
    }

    /**
     * Counts the number of applets on the html page.
     *
     * @return the number of applets
     */
    public int getAppletCount () {
        if (firstTime) {
            return  0;
        }
        else {
            return  super.getAppletCount();
        }
    }

    /**
     * Exclude the javadoc because this method should not be scripted.
     *   @y.exclude
     *
     */
    public void start () {
    	addObject("image","file=assets/red_truck.gif,x=5,y=5");
        if (firstTime) {
            firstTime = false;
            animatorCanvas.clearTrails();
        }
        super.start();
        // debug
        //int id1=addObject("protractor","x=0,y=0,theta=0.785,theta0=0,s=50,fixedbase");
        //setDragable(id1,true);
        //setResizable(id1,true);
        /*
         *             int id1=addObject("rectangle","x=0,y=-0.6,w=1000,h=100");
         *             setForce(id1, "0","0",0,0,0,0);
         *             int id2=addObject("circle","r=5, x=-1.6,y=(6-9.8/2*t*t)");
         *             setRGB(id2,255,0,0);
         *             //setForce(id2,"0","-9.8",-2,2,2,0);
         *             setSticky(id1,true);
         *             setSticky(id2,true);
         *             setCollisionMessage("End of Animation");
         */
        /*
         *
         *             int id1=addObject("circle","r=10,x=-5,y=0");
         *             setForce(id1,"0","0",-2,5,0,0);
         *
         *             int id2=addObject("circle","r=10,x=-5,y=5");
         *             setForce(id2,"0","0",2,6,0,0);
         *             addInteraction(id2,id1,"-y","y");
         *
         *             int id3=addObject("circle","r=10,x=-5,y=5");
         *             setForce(id3,"0","0",2,-5,0,0);
         *             addInteraction(id3,id2,"-y","y");
         */
        //this.setReferenceFrame(id2);
        // int id=addObject("arrow2","h=0.4,v=0.6,s=0, filled,thickness=5");
        //int id=addObject("arrow2","h=0.4,v=0.6,s=0");
        //setResizable(id,true);
        //setLabel(id,"Force");
        //setDragable(id,true);
        //this.setSketchMode(true);
        //int id=addObject("doppler","x=0,r=20,n=20,c=2,period=2");
        //setRGB(id,255,0,0);
        //setTrajectory(id,"5*sin(t/6)","5*cos(t/6)");
        /*
         *  debugging
         * int id=addObject("circle","x=-1");
         * setDragable(id,true);
         * id=addObject("image","file=jet.gif");
         * setDragable(id,true);
         * id=addObject("image","file=jet.gif,x=1");
         * this.setForce(id,"0","0",1,0,0,0);
         * setDragable(id,true);
         */
        //int id=addObject("relshape","h=50/0/-50, v= 0/50/0, n=3");
    }

    /**
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     *
     */
    public void stop () {
        super.stop();
    }

    /**
     *    Called when the clock stops in one-shot mode.    DO NOT SCRIPT.
     */
    protected void stoppingClock () {
        animatorCanvas.setMessage(message);
    }

    /**
     *    Called when the clock cycles in cycle mode.    DO NOT SCRIPT.
     */
    protected void cyclingClock () {
        setAnimationTime(clock.getMinTime());
        clearAllData();
    }

    /**
     *   Force the applet to repaint whenever any object changes its properties.
     *   Default is true.
     *   Set this value to false at the beginning of long scripts and then reset
     *   to true to avoid flashing.
     *
     *   @param              auto The id of the object.
     */
    public void setAutoRefresh (boolean auto) {
        if (destroyed) {
            animatorCanvas.autoRefresh = false;
            autoRefresh = false;
            return;
        }
        autoRefresh = auto;
        animatorCanvas.autoRefresh = auto;
        animatorCanvas.dynamics.resetDynamicsVariables();       // make sure that everything is current.
        if (auto) {
            animatorCanvas.repaint();
        }
    }

    /**
     *         Add a caption to the applet.
     *
     * @param s
     *         @return             The id of the caption.
     */
    public int setCaption (String s) {
        return  animatorCanvas.setCaption(s);
    }

    /**
     *   Make the object with the given id dragable.
     *
     *   @param              id of the object.
     *   @param              canDrag Is the object dragable?
     *   @return             <code>true</code> if successful <code>false</code> otherwise
     */
    public boolean setDragable (int id, boolean canDrag) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.noDrag = !canDrag;
        return  true;
    }

    /**
     * Make an object resizable.
     *
     * @param              id the object identifier
     * @param isResizable  true if the object is resizable
     *
     * @return             <code>true</code> if successful.
     */
    public boolean setResizable (int id, boolean isResizable) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setResizable(isResizable);
        return  true;
    }

    /**
     *   Show the visibility of the object.
     *
     * @param id    the object identifier
     *   @param     show    <code>true</code> will show object on screen
     *
     *   @return            <code>true</code> if successful <code>false</code> otherwise
     */
    public boolean setVisibility (int id, boolean show) {
        if (id == getClockID()) {
            animatorCanvas.timeDisplay = show;
            if (animatorCanvas.autoRefresh) {
                animatorCanvas.repaint();
            }
            return  true;
        }
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setVisible(show);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Set default values and deletes all data connections.
     *   pixPerUnit= PARAM value.
     *   gridUnit= PARAM value.
     *   time=0.
     *   shapeTrail=0.
     *   pixelOrigin=(0,0).
     *   caption=null.
     *   timeDisplay=tue;
     */
    public void setDefault () {
        pause();
        deleteDataConnections();                // we are going to delete all the things so we might as well kill the conections too.
        animatorCanvas.setMessage(null);
        message = null;
        animatorCanvas.timeDisplay = true;
        clock.setTime(0);       // will sop clock and then set time.
        animatorCanvas.setTime();
        animatorCanvas.pixPerUnit = ppu;
        animatorCanvas.gridUnit = gridUnit;
        clock.setDt(dt);
        clock.setFPS(fps);
        clock.setContinuous();
        animatorCanvas.setDefault();            // This will repaint if necessary.
    }

    /**
     *         Set the mass of an object.
     *
     *         @param      id The id of the object.
     *         @param      m  The new mass.
     *         @return     True if successful.
     */
    public boolean setMass (int id, double m) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setMass(m);
        return  true;
    }

    /**
     *       Display a message in the yellow message box.
     *
     *       @param              msg Message to display after the animation stops.
     */
    public void setCollisionMessage (String msg) {
        label_collision = msg;
    }

    /**
     *         Display a message in the yellow message box.
     *
     *         @param              msg Message to display after the animation stops.
     */
    public void setMessage (String msg) {
        animatorCanvas.setMessage(msg);
        message = msg;
    }

    /**
     *         Force an object to follow another object on the screen.
     *
     *         @param              masterID The id of the master object.
     *         @param              slaveID The id of the slave object.
     *         @return             true if successful.
     */
    public boolean setAnimationSlave (int masterID, int slaveID) {
        Thing master = animatorCanvas.getThing(masterID);
        Thing slave = animatorCanvas.getThing(slaveID);
        if ((master == null) || (slave == null)) {
            return  false;
        }
        if (master.myMaster == slave) {
            master.myMaster = null;             // prevent slave=master and master=slave.
        }
        master.mySlaves.addElement(slave);
        slave.myMaster = master;
        slave.setVarsFromMaster();
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *         Set the red, green, and blue color values for all subsequent drawing.
     *         Color values must be in the range 0..255.
     *
     *         @param              r red.
     *         @param              g green.
     *         @param              b blue.
     */
    public void setShapeRGB (int r, int g, int b) {
        animatorCanvas.defaultColor = new Color(r, g, b);
    }

    /**
     *         Set the color of an object.
     *
     *         @param              id The id of the object.
     *         @param              r red
     *         @param              g green
     *         @param              b blue
     *         @return             True if successful.
     */
    public boolean setRGB (int id, int r, int g, int b) {
        if (id == this.animatorCanvas.hashCode()) {
            animatorCanvas.setBackground(new Color(r, g, b));
            return  true;
        }
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.color = new Color(r, g, b);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *       Set the applet to run for a fixed interval, stop, and display a message.
     *
     *       @param              min The starting time value for the loop
     *       @param              max The ending time for the loop.
     *       @param              msg Message to display after the animation stops.
     *       @see                #setTimeContinuous
     *       @see                #setTimeInterval
     */
    public void setOneShot (double min, double max, String msg) {
        clock.setOneShot(min, max);
        animatorCanvas.setMessage(null);
        setAnimationTime(min);
        message = msg;
    }

    /**
     *   Set a size parameter for an object.  The effect of this parameter varies,
     *   or has no effect, depending on the object.  For example, the size of a
     *   spring determines the number of coild and the size of a box determines
     *   the width of the walls.
     *
     *   @param              id The ID of the object.
     *   @param              size The size of the object.
     *   @return             boolean       Returns true if successful.
     */
    public boolean setOnScreenSize (int id, int size) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setSize(size);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *       Set a time loop for the animation from 0 to max. Animation will run continuously.
     *
     *       @param              max The ending time for the loop.
     *       @see                #setTimeContinuous
     *       @see                #setOneShot
     * @see                #setTimeInterval
     */
    public void setTimeCycle (double max) {
        setTimeInterval(0, max);
    }

    /**
     *         Set a time loop for the animation. Animation will run continuously.
     *
     *         @param              min The starting time value for the loop
     *         @param              max The ending time for the loop.
     *         @see                #setTimeContinuous
     *         @see                #setOneShot
     */
    public void setTimeInterval (double min, double max) {
        clock.setCycle(min, max);
        setAnimationTime(min);
        message = null;
        animatorCanvas.setMessage(null);
    }

    /**
     *   Let the animation time increase indefinitely.  May overflow for very long
     *   times.
     *
     *   @see                #setTimeOneShot
     *   @see                #setTimeInterval
     *   @see                #setOneShot
     */
    public void setTimeContinuous () {
        clock.setContinuous();
        message = null;
        animatorCanvas.setMessage(null);
    }

    /**
     *   Runs the simulaiton one time and displays a message.
     *
     *   @param     max Reset the simulation to t=0 when t>=max and stop the simulation.
     * @param msg the message
     */
    public void setTimeOneShot (double max, String msg) {
        this.setOneShot(0, max, msg);
    }

    /**
     *   Set the trail to leave footprints as the object moves.
     *
     *   @param              id The id of the object.
     *   @param              n The number of points to skip between trail
     *                       footprints or ghost images.
     *   @return             boolean       True if successful.
     */
    public boolean setFootPrints (int id, int n) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.footPrints = n;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Have the object draw ghost images as it moves.  Use footprints to set the
     *   spacing.
     *
     *   @param              id The id of the object.
     *   @param              ghost Draw ghost?
     *   @return             True if successful.
     */
    public boolean setGhost (int id, boolean ghost) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.ghost = ghost;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     * Make the object label.
     *
     * @param              id the id of the object
     * @param              label the label string
     * @return             true if successful
     */
    public boolean setLabel (int id, String label) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setLabel(label);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *       Set the tolerance of the ODE solver.  Default is on part in 1.0e-8.
     *
     *       @param              tol the tolerance
     */
    public void setTolerance (double tol) {
        animatorCanvas.dynamics.setTolerance(tol);
    }

    /**
     *         Enable an object to display its path as it moves.  Not all objects can
     *         show their path.
     *
     *         @param              id The id of the object.
     *         @param              n Number of points in trail.  n=0 disables the trail.
     *         @return             boolean    True if successful.
     */
    public boolean setTrail (int id, int n) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setTrailSize(n);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     * Enable an object to display its path as it moves.  Not all objects can
     * show their path.
     *
     * @param    id the id
     * @param    n number of points in trail.  n=0 disables the trail.
     * @param              offset  the number of points to skip before the trail starts
     * @return             boolean    true if successful.
     */
    public boolean setTrail (int id, int n, int offset) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setTrailSize(n, offset);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Set the trajectory of an object on the screen.
     *
     *   @param              id The ID of the thing.
     *   @param              xStr The function x(t).
     *   @param              yStr The function y(t).
     *   @return             boolean  True if the functions are valid and the
     *                       trajectory has been set.
     */
    public boolean setTrajectory (int id, String xStr, String yStr) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        return  t.setTrajectory(xStr, yStr);
    }

    /**
     *   Set the Force on a particle.
     *
     *   @param              id     The ID of the pole.
     *   @param              fxStr   A function of t, x, y, vx, vy, ax, ay,  m.
     *   @param              fyStr   A function of t, x, y, vx, vy, ax, ay,  m.
     *   @param              x0      The initial value for x.
     *   @param              y0      The initial value for y.
     *   @param              vx0     The initial value for vx.
     *   @param              vy0     The initial value for vy.
     *   @return             True if the functions are valid and the trajectory has been set.
     *
     */
    public boolean setForce (int id, String fxStr, String fyStr, double x0,
            double y0, double vx0, double vy0) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        if (t.setForce(fxStr, fyStr, x0, y0, vx0, vy0)) {
            animatorCanvas.dynamics.addRateEquation(t);
        }
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Have the object draw itself before the grid is drawn.
     *
     *   @param              id The id of the object.
     *   @param              before Paint before grid?
     *   @return             True if successful.
     */
    public boolean setPaintBeforeGrid (int id, boolean before) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.paintBeforeGrid = before;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Enable the time display in the applet window.
     *
     *   @param              show boolean   Show the time?
     *
     *   @deprecated         replaced by setShowTime
     */
    public void setTimeDisplay (boolean show) {
        animatorCanvas.timeDisplay = show;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
    }

    /**
     * Show the simulation time.
     *
     * @param  show true will show
     */
    public void setShowTime (boolean show) {
        setTimeDisplay(show);
    }

    /**
     *   Enable the time display in the applet window.
     *
     * @param visible
     */
    public void setTimeVisibility (boolean visible) {
        setTimeDisplay(visible);
    }

    /**
     *   Show the objects trajectory constraint if it exists.
     *
     * @param id the object identifier
     *   @param              sc Show the path?
     *
     * @return true if successful
     */
    public boolean setShowConstraintPath (int id, boolean sc) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.showConstraintPath = sc;
        return  true;
    }

    /**
     *   Have the object show its coordinates on screen.
     *
     *   @param              id The id of the object.
     *   @param              show Show the coordinates?
     *   @return             True if successful.
     */
    public boolean setShowCoordinates (int id, boolean show) {
        if (id == this.animatorCanvas.hashCode()) {
            animatorCanvas.coordDisplay = show;
            return  true;
        }
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.showCoordinates = show;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *  Have the object show its velocity components.
     *
     *  @param              id The id of the object.
     *  @param              show Show the velocity?
     *  @return             True if successful.
     */
    public boolean setShowVComponents (int id, boolean show) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.showVComponents = show;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Have the object show its force components.
     *
     *   @param              id The id of the object.
     *   @param              show Show the force?
     *   @return             True if successful.
     */
    public boolean setShowFComponents (int id, boolean show) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.showFComponents = show;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Have the object show its acceleration components.
     *
     *   @param              id The id of the object.
     *   @param              show Show the acceleration?
     *   @return             True if successful.
     */
    public boolean setShowAComponents (int id, boolean show) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.showAComponents = show;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Have the object show its velocity vector.
     *
     *   @param              id The id of the object.
     *   @param              show Show the velocity?
     *   @return             True if successful.
     */
    public boolean setShowVVector (int id, boolean show) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.showVVector = show;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Have the object show its acceleration vector.
     *
     *   @param              id The id of the object.
     *   @param              show Show the acceleration?
     *   @return             True if successful.
     */
    public boolean setShowAVector (int id, boolean show) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.showAVector = show;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Have the object show its force vector.
     *
     *   @param              id The id of the object.
     *   @param              show Show the force?
     *   @return             True if successful.
     */
    public boolean setShowFVector (int id, boolean show) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.showFVector = show;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Make the object sticky.  A sticky object will stop the clock upon a
     *   collision with another sticky object.
     *   The sticky object must be moving under the action of forces.  Objects
     *   that have trajectories will not stick.
     *
     *   @param              id of the object.
     *   @param              sticky Sticky?
     *   @return             true if successful.
     */
    public boolean setSticky (int id, boolean sticky) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setSticky(sticky);
        return  true;
    }

    /**
     * Make the object bouncy.  A dynamic object will bounce off of bouncy objects.
     *
     * @param              id of the object.
     * @param bounce
     *
     * @return             true if successful.
     */
    public boolean setBouncy (int id, boolean bounce) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setBouncy(bounce);
        return  true;
    }

    /**
     * Constrain the motion of the object to a circular path.
     *
     * @param id  the object identifier
     * @param              r The r value.
     * @param              x The x coordinate of the center
     * @param              y The y coordinate of the center
     * @return             True if successful.
     */
    public boolean setConstrainR (int id, double r, double x, double y) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        return  t.setConstrainR(r, x, y);
    }

    /**
     *   Constrains the motion of the object to a path of constant x.
     *
     * @param id  the object identifier
     *   @param              x The x value.
     * @param min  the min value
     * @param max  the max value
     *   @return             True if successful.
     */
    public boolean setConstrainX (int id, double x, double min, double max) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        return  t.setConstrainX(x, min, max);
    }

    /**
     *   Constrains the motion of the object to a path of constant y.
     *
     * @param id   the object identifier
     *   @param    y The y value.
     * @param min  the min value
     * @param max  the max value
     *   @return   true if successful.
     */
    public boolean setConstrainY (int id, double y, double min, double max) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        return  t.setConstrainY(y, min, max);
    }

    /**
     *   Offset the object's coordinates on the screen.
     *
     *   @param              id The id of the object.
     * @param xOff  the x offset
     * @param yOff  the yoffset
     *   @return             True if successful.
     */
    public boolean setCoordinateOffset (int id, int xOff, int yOff) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.xCoordOff = xOff;
        t.yCoordOff = yOff;
        if (t instanceof CaptionThing) {        // this is a hack to keep from breaking old scripts.
            t.xDisplayOff = xOff;
            t.yDisplayOff = yOff;
        }
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     * Set the velocity of an object to zero if it is being dragged.
     *
     * @param              damp the velocity?
     */
    public void setDampOnMousePressed (boolean damp) {
        animatorCanvas.dampOnMousePressed = damp;
    }

    /**
     *   Sets the object's font if the object has text that can be displayed.
     *
     *   This method fails due to operator overloading on some browers.  Use setObjectFont instead.
     *
     *   @param              id The id of the object.
     *   @param              family The font family: Helvetica, Times.
     *   @param              style The style, 0=plain, 1=bold.
     *   @param              size The size of the font;
     *   @return             true if successful.
     */
    public boolean setFont (int id, String family, int style, int size) {
        Font font = new Font(family, style, size);
        //if(id==this.animatorCanvas.hashCode()){animatorCanvas.font=font; return true;}
        Thing t = animatorCanvas.getThing(id);
        if ((t == null) || (font == null)) {
            return  false;
        }
        t.font = font;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     * Sets the object's font if the object has text that can be displayed.
     *
     * @param              id The id of the object.
     * @param              family The font family: Helvetica, Times.
     * @param              style The style, 0=plain, 1=bold.
     * @param              size The size of the font;
     * @return             true if successful.
     */
    public boolean setObjectFont (int id, String family, int style, int size) {
        return  setFont(id, family, style, size);
    }

    /**
     *   Change the object's format for the display of numeric data.
     *
     *   Us this method to control the number of significant digits in calculations with text objects.
     *   Use Unix printf conventions.  For example fstr="%6.3f"
     *
     *   @param              id The id of the object.
     *   @param              fstr the format string.
     *   @return             True if successful.
     */
    public boolean setFormat (int id, String fstr) {
        Thing t = animatorCanvas.getThing(id);
        if ((t == null) && ((id == 0) || (id == animatorCanvas.hashCode()))) {
            return  animatorCanvas.setFormat(fstr);
        }
        boolean result = t.setFormat(fstr);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  result;
    }

    /**
     *   Offset the object's position on the screen from its default drawing
     *   position.
     *
     *   @param              id The id of the object.
     * @param xOff           the x offset
     * @param yOff           the y offset
     *   @return             true if successful.
     */
    public boolean setDisplayOffset (int id, int xOff, int yOff) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.xDisplayOff = xOff;
        t.yDisplayOff = yOff;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Set the grid spacing in world, i.e., not pixel, units.  Zero will
     *   suppress the drawing of the grid.
     *
     *   @param              gu grid unit.
     */
    public void setGridUnit (double gu) {
        animatorCanvas.gridUnit = gu;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
    }

    /**
     * Enable sketching with the mouse.
     *
     * @param              sketch true will sketch
     *
     * @return  int the id of the mouse data source
     */
    public int setSketchMode (boolean sketch) {
        return  animatorCanvas.setSketchMode(sketch);
    }

    /**
     *   Set the pixels per unit.  This sets the scale for the animation.
     *
     *   @param              pu pixels per unit.
     */
    public void setPixPerUnit (int pu) {
        animatorCanvas.pixPerUnit = pu;
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
    }

    /**
     *         Shift the origin for the entire drawing.  Shift is specified in pixels.
     *
     *         @param              xo x pixel shift.
     *         @param              yo y pixel shift.
     */
    public void shiftPixOrigin (int xo, int yo) {
        animatorCanvas.xOffset = -xo;
        animatorCanvas.yOffset = -yo;
    }

    /**
     *         Clear data from all dataConnections and reset the animation time to 0.
     *
     *         @see                #setAnimationTime
     */
    public void reset () {
        this.clearAllData();
        this.setAnimationTime(0.0);
    }

    /**
     *   Set the animation time.
     *
     *   @param              time The new time displayed inside the applet.
     */
    public void setAnimationTime (double time) {
        boolean shouldRun = false;
        if (clock.isRunning()) {
            shouldRun = true;
            stopClock();
        }
        clock.setTime(time);                    // will sop clock and then set time.
        animatorCanvas.setTime();
        if (shouldRun) {
            startClock();
        }
        else if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
    }

    /**
     *         Step the time by dt.
     *
     *         @see                #setDt
     */
    public void stepTimeForward () {
        animatorCanvas.setMessage(null);
        if (clock.isRunning()) {
            pause();
            return;
        }
        boolean isNegative = (clock.getDt() < 0);
        clock.setDt(Math.abs(clock.getDt()));
        clock.doStep();
        if (clock.isCycle() && (clock.getTime() <= clock.getMinTime())) {
            animatorCanvas.clearTrails();
        }
        if (isNegative) {
            clock.setDt(-Math.abs(clock.getDt()));              // make dt negative since it started out that way.
        }
    }

    /**
     * Method stepForward
     *
     */
    public void stepForward () {
        stepTimeForward();
    }

    /**
     *         Step the time backward by dt.
     *
     *         @see                #setDt
     */
    public void stepTimeBack () {
        animatorCanvas.setMessage(null);
        if (clock.isRunning()) {
            pause();
            return;
        }
        boolean isNegative = (clock.getDt() < 0);
        clock.setDt(-Math.abs(clock.getDt()));
        clock.doStep();
        if (clock.isCycle() && (clock.getTime() <= clock.getMinTime())) {
            animatorCanvas.clearTrails();
        }
        if (!isNegative) {
            clock.setDt(Math.abs(clock.getDt()));               // make dt positive since it started out that way.
        }
    }

    /**
     * Method stepBack
     *
     */
    public void stepBack () {
        stepTimeBack();
    }

    ///// The Add methods.
    /**
     * Creates an object and adds it to the Physlet.
     * The first argument is the name of the object to be added and the second is a
     * comma-delimited list of parameters.  For example, a circle can be added a follows:
     * <p>
     * <code>addObject ("circle", "x = 0, y = -1.0, r = 10");</code>
     * </p>
     * See the supplemental documentation for a list of
     * <a href="doc-files/animator_addobject.html">object names and parameters.</a>
     *
     * @param              name the type of object to be created.
     * @param              parList a list of parameters to be set
     *
     * @return             <code>double</code> id of the object
     */
    public synchronized int addObject (String name, String parList) {
        if (destroyed) {
            return  0;
        }
        Thing t = null;
        //double x = 0;
        //double y = 0;
        String xStr = "0";
        String yStr = "0";
        int width = 20;
        int height = 20;
        int s = 1;              // size or thickness
        int r = 5;
        double m = 1;
        int id = 0;
        if (parList == null) {
            parList = " ";
        }
        name = name.toLowerCase().trim();
        name = SUtil.removeWhitespace(name);
        String parList2 = parList.trim();
        parList = SUtil.removeWhitespace(parList);
        if (name.equals("mouse")) {
            MouseDataSource mouseDataSource = animatorCanvas.addMouseThing();
            if (SUtil.parameterExist(parList, "drag")) {
                mouseDataSource.setTrackDrag(true);
            }
            if (SUtil.parameterExist(parList, "move")) {
                mouseDataSource.setTrackMove(true);
            }
            if (SUtil.parameterExist(parList, "click")) {
                mouseDataSource.setTrackClick(true);
            }
            if (SUtil.parameterExist(parList, "release")) {
                mouseDataSource.setTrackRelease(true);
            }
            return  mouseDataSource.getID();
        }
        else if (name.equals("box")) {
            s = 1;
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "w=")) {
                width = (int)SUtil.getParam(parList, "w=");
            }
            if (SUtil.parameterExist(parList, "h=")) {
                height = (int)SUtil.getParam(parList, "h=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            if (SUtil.parameterExist(parList, "s=")) {
                s = (int)SUtil.getParam(parList, "s=");
            }
            id = animatorCanvas.addBox(width, height, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            if (s != 1) {
                animatorCanvas.getThing(id).setSize(s);
            }
            return  id;
        }
        else if (name.equals("rectangle")) {
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "w=")) {
                width = (int)SUtil.getParam(parList, "w=");
            }
            if (SUtil.parameterExist(parList, "h=")) {
                height = (int)SUtil.getParam(parList, "h=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = animatorCanvas.addRectangle(width, height, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        else if (name.equals("circle")) {
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "r=")) {
                r = (int)SUtil.getParam(parList, "r=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = animatorCanvas.addCircle(2*r + 1, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        else if (name.equals("doppler")) {
            int numCrests = 10;
            double period = 1.0;
            double c = 1.0;
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "r=")) {
                r = (int)SUtil.getParam(parList, "r=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            if (SUtil.parameterExist(parList, "n=")) {
                numCrests = (int)SUtil.getParam(parList, "n=");
            }
            if (SUtil.parameterExist(parList, "period=")) {
                period = SUtil.getParam(parList, "period=");
            }
            if (SUtil.parameterExist(parList, "c=")) {
                c = SUtil.getParam(parList, "c=");
            }
            numCrests = Math.max(1, numCrests);
            period = Math.max(dt, period);
            if (c <= 0) {
                c = 1;
            }
            id = animatorCanvas.addDoppler(2*r + 1, xStr, yStr, numCrests,
                    period, c);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        else if (name.equals("charge")) {
            double q = 1;
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "r=")) {
                r = (int)SUtil.getParam(parList, "r=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            if (SUtil.parameterExist(parList, "q=")) {
                q = SUtil.getParam(parList, "q=");
            }
            id = animatorCanvas.addCharge(2*r + 1, xStr, yStr, q);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        else if (name.equals("shell")) {
            s = 1;
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "r=")) {
                r = (int)SUtil.getParam(parList, "r=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            if (SUtil.parameterExist(parList, "s=")) {
                s = (int)SUtil.getParam(parList, "s=");
            }
            id = animatorCanvas.addShell(2*r + 1, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            if (s != 1) {
                animatorCanvas.getThing(id).setSize(s);
            }
            return  id;
        }
        else if (name.equals("exshell")) {
            String rStr = "10";
            s = 2;
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "r=")) {
                rStr = SUtil.getParamStr(parList, "r=");
            }
            if (SUtil.parameterExist(parList, "s=")) {
                s = (int)SUtil.getParam(parList, "s=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = animatorCanvas.addExShell(s, rStr, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        else if (name.equals("image")) {
            String file = " ";
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "gif=")) {
                file = SUtil.getParamStr(parList, "gif=");
            }
            if (SUtil.parameterExist(parList, "file=")) {
                file = SUtil.getParamStr(parList, "file=");
            }
            if (file == null) {
                return  0;
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = addImage(file, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        else if (name.equals("protractor")) {
            s = 40;             // the protractor length.
            double theta = 0, theta0 = 0;
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "theta=")) {
                theta = SUtil.getParam(parList, "theta=");
            }
            if (SUtil.parameterExist(parList, "theta0=")) {
                theta0 = SUtil.getParam(parList, "theta0=");
            }
            if (SUtil.parameterExist(parList, "s=")) {
                s = (int)SUtil.getParam(parList, "s=");
            }
            id = animatorCanvas.addProtractor(s, theta, theta0, xStr, yStr);
            if (SUtil.parameterExist(parList, "fixedbase")) {
                Protractor p = (Protractor)animatorCanvas.getThing(id);
                p.fixedBase = true;
            }
            if (SUtil.parameterExist(parList, "fixedlength")) {
                Protractor p = (Protractor)animatorCanvas.getThing(id);
                p.fixedlength = true;
            }
            return  id;
        }
        else if (name.equals("arrow")) {
            String horz = "1", vert = "1";
            s = 4;              // the size of the arrowhead.
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "h=")) {
                horz = SUtil.getParamStr(parList, "h=");
            }
            if (SUtil.parameterExist(parList, "v=")) {
                vert = SUtil.getParamStr(parList, "v=");
            }
            if (SUtil.parameterExist(parList, "s=")) {
                s = (int)SUtil.getParam(parList, "s=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = animatorCanvas.addArrow(s, horz, vert, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            if (SUtil.parameterExist(parList, "thickness=")) {
                Arrow a = (Arrow)animatorCanvas.getThing(id);
                a.thickness = Math.max((int)SUtil.getParam(parList, "thickness="),
                        1);
            }
            if (SUtil.parameterExist(parList, "filled")) {
                Arrow a = (Arrow)animatorCanvas.getThing(id);
                a.setFilled(true);
            }
            return  id;
        }
        else if (name.equals("arrow2")) {
            double horz = 1, vert = 1;
            s = 5;              // the size of the arrowhead.
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "h=")) {
                horz = SUtil.getParam(parList, "h=");
            }
            if (SUtil.parameterExist(parList, "v=")) {
                vert = SUtil.getParam(parList, "v=");
            }
            if (SUtil.parameterExist(parList, "s=")) {
                s = (int)SUtil.getParam(parList, "s=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = animatorCanvas.addArrowStatic(s, horz, vert, xStr, yStr);
            if (SUtil.parameterExist(parList, "thickness=")) {
                ArrowStatic a = (ArrowStatic)animatorCanvas.getThing(id);
                a.thickness = Math.max((int)SUtil.getParam(parList, "thickness="),
                        1);
            }
            if (m != 1) {
                setMass(id, m);
            }
            if (SUtil.parameterExist(parList, "filled")) {
                ArrowStatic a = (ArrowStatic)animatorCanvas.getThing(id);
                a.setFilled(true);
            }
            if (SUtil.parameterExist(parList, "fixedlength")) {
                ArrowStatic a = (ArrowStatic)animatorCanvas.getThing(id);
                a.setFixedLength(true);
            }
            return  id;
        }
        else if (name.equals("piston")) {
            String horz = "1", vert = "1";
            s = 4;              // the size of the arrowhead.
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "h=")) {
                horz = SUtil.getParamStr(parList, "h=");
            }
            if (SUtil.parameterExist(parList, "v=")) {
                vert = SUtil.getParamStr(parList, "v=");
            }
            if (SUtil.parameterExist(parList, "s=")) {
                s = (int)SUtil.getParam(parList, "s=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = animatorCanvas.addPiston(s, horz, vert, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        else if (name.equals("line")) {
            String horz = "1", vert = "1";
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "h=")) {
                horz = SUtil.getParamStr(parList, "h=");
            }
            if (SUtil.parameterExist(parList, "v=")) {
                vert = SUtil.getParamStr(parList, "v=");
            }
            id = animatorCanvas.addArrow(0, horz, vert, xStr, yStr);
            if (SUtil.parameterExist(parList, "thickness=")) {
                Arrow a = (Arrow)animatorCanvas.getThing(id);
                a.thickness = Math.max((int)SUtil.getParam(parList, "thickness="),
                        1);
            }
            return  id;
        }
        else if (name.equals("calculation")) {
            String txt = "";
            //String calc = null;
            double chop = 1.0e-12;
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "txt=")) {
                txt = SUtil.getParamStr(parList2, "txt=");
            }
            if (SUtil.parameterExist(parList, "text=")) {
                txt = SUtil.getParamStr(parList2, "text=");
            }
            id = animatorCanvas.addCalcThing(txt, xStr, yStr);
            if (SUtil.parameterExist(parList, "chop=")) {
                chop = SUtil.getParam(parList, "chop=");
                CalcThing ct = (CalcThing)animatorCanvas.getThing(id);
                ct.setChop(chop);
            }
            return  id;
        }
        else if (name.equals("text")) {
            String txt = "";
            String calc = null;
            double chop = 1.0e-12;
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "txt=")) {
                txt = SUtil.getParamStr(parList2, "txt=");
            }
            if (SUtil.parameterExist(parList, "text=")) {
                txt = SUtil.getParamStr(parList2, "text=");
            }
            if (SUtil.parameterExist(parList, "calc=")) {
                calc = SUtil.getParamStr(parList, "calc=");
            }
            id = animatorCanvas.addText(txt, calc, xStr, yStr);
            if (SUtil.parameterExist(parList, "chop=")) {
                chop = SUtil.getParam(parList, "chop=");
                TextThing tt = (TextThing)animatorCanvas.getThing(id);
                tt.setChop(chop);
            }
            return  id;
        }
        else if (name.equals("caption")) {
            String txt = "";
            String calc = null;
            double chop = 1.0e-12;
            if (SUtil.parameterExist(parList, "txt=")) {
                txt = SUtil.getParamStr(parList2, "txt=");
            }
            if (SUtil.parameterExist(parList, "text=")) {
                txt = SUtil.getParamStr(parList2, "text=");
            }
            if (SUtil.parameterExist(parList, "calc=")) {
                calc = SUtil.getParamStr(parList, "calc=");
            }
            id = animatorCanvas.addCaption(txt, calc, "0", "0");
            if (SUtil.parameterExist(parList, "chop=")) {
                chop = SUtil.getParam(parList, "chop=");
                CaptionThing cap = (CaptionThing)animatorCanvas.getThing(id);
                cap.setChop(chop);
            }
            return  id;
        }
        else if (name.equals("connectorline")) {
            int id1 = 0, id2 = 0;
            if (SUtil.parameterExist(parList, "id1=")) {
                id1 = (int)SUtil.getParam(parList, "id1=");
            }
            if (SUtil.parameterExist(parList, "id2=")) {
                id2 = (int)SUtil.getParam(parList, "id2=");
            }
            return  addConnectorLine(id1, id2);
        }
        else if (name.equals("connectorspring")) {
            int id1 = 0, id2 = 0;
            if (SUtil.parameterExist(parList, "id1=")) {
                id1 = (int)SUtil.getParam(parList, "id1=");
            }
            if (SUtil.parameterExist(parList, "id2=")) {
                id2 = (int)SUtil.getParam(parList, "id2=");
            }
            return  addConnectorSpring(id1, id2);
        }
        else if (name.equals("cursor")) {
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "r=")) {
                r = (int)SUtil.getParam(parList, "r=");
            }
            return  animatorCanvas.addCursor(2*r + 1, xStr, yStr);
        }
        else if (name.equals("interaction")) {
            int id1 = 0, id2 = 0;
            String fStr = "0", mode = "r";
            if (SUtil.parameterExist(parList, "id1=")) {
                id1 = (int)SUtil.getParam(parList, "id1=");
            }
            if (SUtil.parameterExist(parList, "id2=")) {
                id2 = (int)SUtil.getParam(parList, "id2=");
            }
            if (SUtil.parameterExist(parList, "force=")) {
                fStr = SUtil.getParamStr(parList, "force=");
            }
            if (SUtil.parameterExist(parList, "mode=")) {
                mode = SUtil.getParamStr(parList, "mode=");
            }
            if (!animatorCanvas.addInteraction(id1, id2, fStr, mode)) {
                System.out.println("Error in add interaction. force=" + fStr);
            }
            return  0;
        }
        else if (name.equals("curve")) {
            int n = 100;
            double start = -1, stop = 1;
            if (SUtil.parameterExist(parList, "n=")) {
                n = (int)SUtil.getParam(parList, "n=");
            }
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "start=")) {
                start = SUtil.getParam(parList, "start=");
            }
            if (SUtil.parameterExist(parList, "stop=")) {
                stop = SUtil.getParam(parList, "stop=");
            }
            return  animatorCanvas.addParametricCurve(n, start, stop, xStr,
                    yStr);
        }
        else if (name.equals("polyshape")) {
            int n = 0;
            String hStr = " ", vStr = " ";
            if (SUtil.parameterExist(parList, "n=")) {
                n = (int)SUtil.getParam(parList, "n=");
            }
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "h=")) {
                hStr = SUtil.getParamStr(parList, "h=");
            }
            if (SUtil.parameterExist(parList, "v=")) {
                vStr = SUtil.getParamStr(parList, "v=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = addPolyShape(n, hStr, vStr, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        else if (name.equals("relshape")) {
            int n = 0;
            String hStr = " ", vStr = " ";
            if (SUtil.parameterExist(parList, "n=")) {
                n = (int)SUtil.getParam(parList, "n=");
            }
            if (SUtil.parameterExist(parList, "x=")) {
                xStr = SUtil.getParamStr(parList, "x=");
            }
            if (SUtil.parameterExist(parList, "y=")) {
                yStr = SUtil.getParamStr(parList, "y=");
            }
            if (SUtil.parameterExist(parList, "h=")) {
                hStr = SUtil.getParamStr(parList, "h=");
            }
            if (SUtil.parameterExist(parList, "v=")) {
                vStr = SUtil.getParamStr(parList, "v=");
            }
            if (SUtil.parameterExist(parList, "m=")) {
                m = SUtil.getParam(parList, "m=");
            }
            id = addRelPolyShape(n, hStr, vStr, xStr, yStr);
            if (m != 1) {
                setMass(id, m);
            }
            return  id;
        }
        if (t == null) {
            System.out.println("Object not created. name:" + name + "parameter list:"
                    + parList);
            //return  0;
        }
        return  0;
    }

    /**
     *         Adds a filled circle to the animation.
     *         Duplicates functionality of addObject(String String) method.
     *
     *         @param              diameter The diameter of the circle in pixels.
     *         @param              xStr The x position of the center.
     *         @param              yStr The y position of the center.
     *
     *         @return             id of filled circle.
     */
    public int addCircle (int diameter, String xStr, String yStr) {
        return  animatorCanvas.addCircle(diameter, xStr, yStr);
    }

    /**
     *         Adds a circular shell to the animation.
     *         Duplicates functionality of addObject(String String) method.
     *
     *         @param              diameter The diameter of the shell in pixels.
     *         @param              xStr The x position of the center.
     *         @param              yStr The y position of the center.
     *         @return             id of shell.
     *
     */
    public int addShell (int diameter, String xStr, String yStr) {
        return  animatorCanvas.addShell(diameter, xStr, yStr);
    }

    /**
     *         Adds an expanding shell to the animation.
     *         Duplicates functionality of addObject(String String) method.
     *
     *         @param              tickness The thickness of the shell in pixels.
     *         @param              rStr The radius of the shell.
     *         @param              xStr The x position of the center.
     *         @param              yStr The y position of the center.
     *   @return             id of the object
     */
    public int addExShell (int tickness, String rStr, String xStr, String yStr) {
        return  animatorCanvas.addExShell(tickness, rStr, xStr, yStr);
    }

    /**
     *   Add a piston, i.e. a rectangle that changes size, to the animation.  Both
     *   the position, length and width can be functions of time.
     *         Duplicates functionality of addObject(String String) method.
     *
     * @param s
     *   @param              hStr The horizontal size.
     *   @param              vStr The vertical size.
     *   @param              xStr The x position of the base.
     *   @param              yStr The y position of the base.
     *   @return             id of the object
     *
     *   @see                #addRectangle
     */
    public int addPiston (int s, String hStr, String vStr, String xStr, String yStr) {
        return  animatorCanvas.addPiston(s, hStr, vStr, xStr, yStr);
    }

    /**
     *   Add a spring to the animation.  Both the position and the direction
     *   components can be functions of time.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              hStr The horizontal size.
     *   @param              vStr The vertical size.
     *   @param              xStr The x position of the base.
     *   @param              yStr The y position of the base.
     *   @return             id of the object
     *
     */
    public int addSpring (String hStr, String vStr, String xStr, String yStr) {
        return  animatorCanvas.addSpring(4, hStr, vStr, xStr, yStr);
    }

    /**
     *   Adds an arrow, i.e. vector, to the animation.  Both the position and the
     *   direction components can be functions of time.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              hStr The horizontal component.  Can be a function of x,y,vx,vy,ax,ay, and t.
     *   @param              vStr The vertical component.    Can be a function of x,y,vx,vy,ax,ay, and t.
     *   @param              xStr The x position of the base. Can be a function of t.
     *   @param              yStr The y position of the base. Can be a function of t.
     *   @return             true if successful
     *   @see                #addLine
     */
    public int addArrow (String hStr, String vStr, String xStr, String yStr) {
        return  animatorCanvas.addArrow(4, hStr, vStr, xStr, yStr);
    }

    /**
     *   Adds an interaction between two particles, Force(x,y,r,v,t).  All values except time are
     *   relative values.  That is, v is the relative velocity, x is the relative x separation, etc.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              id1 The first particle.
     *   @param              id2 The second particle
     *   @param              force The force between the particles as a function of x, y, r, v, t
     *   @param              mode "r", "x", or "y"
     *   @return             <code>double</code> id of the object
     */
    public boolean addInteraction (int id1, int id2, String force, String mode) {
        return  animatorCanvas.addInteraction(id1, id2, force, mode);
    }

    /**
     *   Adds a calculated value, i.e., a function of time that is calculated as a
     *   number, to the animation.
     *   The position on the screen can change so that this value can move with
     *   other objects.
     *   Duplicates functionality of addObject(String String) method.
     *
     * @param text
     *   @param              calc A function of position, velocity, acceleration,
     *                       and time that will be evaluated at every time step.
     *   @param              xStr The x position of the base.
     *   @param              yStr The y position of the base.
     *
     *   @return             <code>double</code> id of the object
     */
    public int addCalculation (String text, String calc, String xStr, String yStr) {
        return  animatorCanvas.addText(text, calc, xStr, yStr);
    }

    /**
     *   Add a caption to the animation. A caption is centered on the screen and
     *   uses a bold font.
     *   The position on the screen can change so that this value can move with
     *   other objects.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              text A fixed caption to the left of the number.
     *   @param              calc A function of time that will be evaluated.
     *   @return             The id of the object.
     *   @see                #addCalculation
     */
    public int addCaption (String text, String calc) {
        return  animatorCanvas.addCaption(text, calc, "0", "0");
    }

    /**
     *   Add a cursor to the animation.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              diameter The diameter.
     * @param xStr
     * @param yStr
     *   @return             id of the object
     */
    public int addCursor (int diameter, String xStr, String yStr) {
        return  animatorCanvas.addCursor(diameter, xStr, yStr);
    }

    /**
     *   Adds text to the animation.
     *   Duplicates functionality of addObject(String String) method.
     *   @param              text The text.
     *   @param              xStr The x  position of the text.
     *   @param              yStr The y  position of the text.
     *   @return             id of the object
     */
    public int addText (String text, String xStr, String yStr) {
        return  animatorCanvas.addText(text, null, xStr, yStr);
    }

    /**
     *   Adds a line to the animation.  Both the position and the direction
     *   components can be functions of time.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              hStr The horizontal size.
     *   @param              vStr The vertical size.
     *   @param              xStr The x position of the base.
     *   @param              yStr The y position of the base.
     *   @return             id of the object
     *
     *   @see                #addArrow
     */
    public int addLine (String hStr, String vStr, String xStr, String yStr) {
        return  animatorCanvas.addArrow(0, hStr, vStr, xStr, yStr);
    }

    /**
     *   Adds a connecting line between two objects.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              id1 The first id of a screen object.
     *   @param              id2 The second id of a screen object.
     *   @return             id that identifies the connector.
     */
    public synchronized int addConnectorLine (int id1, int id2) {
        Thing t1 = animatorCanvas.getThing(id1);
        Thing t2 = animatorCanvas.getThing(id2);
        ConnectorLine c = new ConnectorLine(animatorCanvas, t1, t2);
        animatorCanvas.things.addElement(c);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  c.hashCode();
    }

    /**
     *   Adds a connecting spring between two objects.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              id1 The first id of a screen object.
     *   @param              id2 The second id of a screen object.
     *   @return             id that identifies the connector.
     */
    public synchronized int addConnectorSpring (int id1, int id2) {
        Thing t1 = animatorCanvas.getThing(id1);
        Thing t2 = animatorCanvas.getThing(id2);
        ConnectorSpring c = new ConnectorSpring(animatorCanvas, t1, t2);
        animatorCanvas.things.addElement(c);
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  c.hashCode();
    }

    /**
     * Swap the drawing order on the screen.
     *
     * @param              id1 The first id of a screen object.
     * @param              id2 The second id of a screen object.
     * @return             True if successful.
     */
    public synchronized boolean swapZOrder (int id1, int id2) {
        return  animatorCanvas.swapZOrder(id1, id2);
    }

    /**
     * Delete an object from the applet.
     *
     * @param id the object identifier
     *
     */
    public synchronized void deleteObject (int id) {
        animatorCanvas.deleteObject(id);
    }

    /**
     *   Adds a box to the animation.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              w The width of the circle in pixels.
     *   @param              h The height of the circle in pixels.
     *   @param              xStr The x position of the center.
     *   @param              yStr The y position of the center.
     *
     *   @return             True if successful.
     *   @see                #addRectangle
     */
    public int addBox (int w, int h, String xStr, String yStr) {
        return  animatorCanvas.addBox(w, h, xStr, yStr);
    }

    /**
     *   Adds a solid rectangle to the animation.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              w The width of the circle in pixels.
     *   @param              h The height of the circle in pixels.
     *   @param              xStr The x position of the center.
     *   @param              yStr The y position of the center.
     *   @return             id of the object
     *
     *   @see                #addBox
     */
    public int addRectangle (int w, int h, String xStr, String yStr) {
        return  animatorCanvas.addRectangle(w, h, xStr, yStr);
    }

    /**
     *   Plot a function.  The x and y coodinates are specified as a parametric
     *   curve.
     *
     *   @param              n The number of points to plot.
     *   @param              start The starting value of the parameter, s.
     *   @param              stop The ending value of the paramter, s.
     *   @param              xStr The x  function, x(s,t).
     *   @param              yStr The y  function, y(s,t).
     *   @return             id of the object
     *
     */
    public int addParametricCurve (int n, double start, double stop, String xStr,
            String yStr) {
        return  animatorCanvas.addParametricCurve(n, start, stop, xStr, yStr);
    }

    /**
     *   Add a polygon to the animation. The polygon can have an arbitrary number
     *   of points but the shape is fixed.  The position can be a function of time.
     *
     *   @param              n The number of vertices in the polygon.
     *   @param              hStr A slash separated list of the x postions of the
     *                       vertices in pixel units.  MUST BE A STRING.
     *   @param              vStr A slash separated list of the y postions of the
     *                       vertices in pixel units.  MUST BE A STRING.
     *   @param              xStr The x position of the base.
     *   @param              yStr The y position of the base.
     *   @return             id of the object
     *
     *   @see                #addRectangle
     */
    public int addPolyShape (int n, String hStr, String vStr, String xStr,
            String yStr) {
        StringTokenizer htokens = new StringTokenizer(hStr, ", ; / \\ ( { [ ) } ] \t \n \r");
        StringTokenizer vtokens = new StringTokenizer(vStr, ", ; / \\ ( { [ ) } ] \t \n \r");
        if ((htokens.countTokens() != n) || (htokens.countTokens() != n)) {
            return  0;
        }
        int h[] = new int[n];
        int v[] = new int[n];
        for (int i = 0; i < n; i++) {
            //h[i]=Integer.decode(htokens.nextToken()).intValue();
            //v[i]=Integer.decode(vtokens.nextToken()).intValue();
            h[i] = Format.atoi(htokens.nextToken());
            v[i] = Format.atoi(vtokens.nextToken());
        }
        return  animatorCanvas.addPolyShape(n, h, v, xStr, yStr);
    }

    /**
     *   Add a polygon to the animation. The polygon can have an arbitrary number
     *   of points but the shape is fixed.  Use relative postions for the vertices.
     *
     *   @param              n The number of vertices in the polygon.
     *   @param              hStr A slash separated list of the x postions of the
     *                       relative vertices in pixel units.  MUST BE A STRING.
     *   @param              vStr A slash separated list of the y postions of the
     *                       relative vertices in pixel units.  MUST BE A STRING.
     *   @param              xStr The x position of the base.
     *   @param              yStr The y position of the base.
     *   @return             id of the shape.
     *
     *   @see                #addRectangle
     */
    public int addRelPolyShape (int n, String hStr, String vStr, String xStr,
            String yStr) {
        StringTokenizer htokens = new StringTokenizer(hStr, ", ; / \\ ( { [ ) } ] \t \n \r");
        StringTokenizer vtokens = new StringTokenizer(vStr, ", ; / \\ ( { [ ) } ] \t \n \r");
        if ((htokens.countTokens() != n) || (htokens.countTokens() != n)) {
            return  0;
        }
        int h[] = new int[n];
        int v[] = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Format.atoi(htokens.nextToken());
            v[i] = Format.atoi(vtokens.nextToken());
        }
        for (int i = 1; i < n; i++) {
            h[i] = h[i - 1] + h[i];
            v[i] = v[i - 1] + v[i];
        }
        return  animatorCanvas.addPolyShape(n, h, v, xStr, yStr);
    }

    /**
     *   Adds an image to the animation.  Looks for image in the code base, the
     *   document base, and absolute URL.
     *
     *   @param              file Location of image relative to the document
     *                       containing the HTML page.
     *   @param              xStr The x position of the image.
     *   @param              yStr The y position of the image.
     *   @return             id of the object
     */
    public int addImage (String file, String xStr, String yStr) {
        int id = 0;
        Image im;
        // java.io.File f=new java.io.File(file);
        try {
            //if(f.canRead())
            im = getImage(getCodeBase(), file);
            id = animatorCanvas.addImage(im, xStr, yStr);
            // else id=0;
        } catch (Exception e) {
            id = 0;
            //System.out.println("Failed to load image file from code base.");
        }
        if (id == 0) {
            try {
                //if(f.canRead())
                im = getImage(getDocumentBase(), file);
                id = animatorCanvas.addImage(im, xStr, yStr);
                //else id=0;
            } catch (Exception e) {
                id = 0;
                //System.out.println("Failed to load image file from document base.");
            }
        }
        if (id == 0) {
            try {
                java.net.URL url = new java.net.URL(file);
                im = getImage(url);
                id = animatorCanvas.addImage(im, xStr, yStr);
            } catch (Exception e) {
                id = 0;
                //System.out.println("Failed to load image file from absolute URL.");
            }
        }
        if (id == 0) {
            System.out.println("Failed to load image file.");
        }
        return  id;
    }

    /**
     *   Add an image to the animation.  The position of the image can change
     *   during the animation.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              file Location of image relative to the document
     *                       containing the HTML page.
     *   @param              xStr The x position of the image.
     *   @param              yStr The y position of the image.
     *   @return             id of the object
     */
    public int addImageFromDocumentBase (String file, String xStr, String yStr) {
        int id = 0;
        try {
            id = animatorCanvas.addImage(getImage(getDocumentBase(), file),
                    xStr, yStr);
        } catch (Exception e) {
            id = 0;
            System.out.println("Failed to load image file from Document Base");
        }
        return  id;
    }

    /**
     *   Adds an image to the animation.  The position of the image can change
     *   during the animation.
     *   Duplicates functionality of addObject(String String) method.
     *
     *   @param              file Location of image relative to the code
     *                       containing the jar files.
     *   @param              xStr The x position of the image.
     *   @param              yStr The y position of the image.
     *   @return             id of the object
     */
    public int addImageFromCodeBase (String file, String xStr, String yStr) {
        int id = 0;
        try {
            id = animatorCanvas.addImage(getImage(getCodeBase(), file), xStr,
                    yStr);
        } catch (Exception e) {
            id = 0;
            System.out.println("Failed to load image file from Code base!");
        }
        return  id;
    }

    /**
     *   Get the Canvas so that Animator can be used in EJS for drawing.
     *
     *   @return   AnimatorCanvas
     */
    public AnimatorCanvas getPhysletCanvas () {
        return  this.animatorCanvas;
    }

    /**
     *   Get the id for the ensemble of objects.  This id can be
     *   used to access the ensemble as a data source.
     *
     *   @return             int The id of the ensemble containing all
     *                       objects on the screen.  Used as a data source.
     */
    public int getEnsembleID () {
        return  animatorCanvas.hashCode();
    }

    /**
     * Get the id for collisions.  This id can be
     * used to access the collision as a data source.
     *
     * @return             int The id of the ensemble containing all
     *                     objects on the screen.  Used as a data source.
     */
    public int getCollisionID () {
        return  animatorCanvas.getCollisionThing().hashCode();
    }

    /**
     *   Reverse the direction of the time step in the animation.
     */
    public void reverse () {
        clock.setDt(-clock.getDt());
    }

    /**
     *   Start the animation.
     */
    public void forward () {
        animatorCanvas.setMessage(null);
        clock.startClock();
    }

    /**
     *   Pause the animation.
     */
    public void pause () {
        animatorCanvas.setMessage(null);
        if (clock.isRunning()) {
            clock.stopClock();
        }
    }

    /**
     *   Get the animation time.
     *
     *   @return             The time displayed inside the applet.
     */
    public double getAnimationTime () {
        return  animatorCanvas.time;
    }

    /**
     * Gets the x position of an object.
     *
     * @param   id the object identifier
     *
     * @return the x position
     */
    public double getX (int id) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  0;
        }
        return  t.getX();
    }

    /**
     * Changes the x of an object.
     *
     * @param              id The id of the object.
     * @param              x  new x value
     * @return             true if successful
     */
    public boolean setX (int id, double x) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setX(x);
        animatorCanvas.dynamics.resetDynamicsVariables();
        if (t == animatorCanvas.dragThing) {
            animatorCanvas.this_mouseReleased(null);
        }
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     * Sets the x position of an object.
     *
     * @param id the object identifier
     * @param x
     *
     * @return true of successful
     */
    public boolean setXPos (int id, double x) {
        return  setX(id, x);
    }

    /**
     * Bug-fix to get the x of an object on Netscape and Sun.
     *
     * @param              id The id of the object.
     * @return             true if successful
     */
    public double getXPos (int id) {
        return  getX(id);
    }

    /**
     * Bug-fix to get the y of an object on Netscape and Sun.
     *
     * @param              id The id of the object.
     * @return             true if successful
     */
    public double getYPos (int id) {
        return  getY(id);
    }

    /**
     * Change the x and y of an object.
     *
     * @param              id The id of the object.
     * @param              x  new x value
     * @param              y  new y value
     * @return             true if successful
     */
    public boolean setXY (int id, double x, double y) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setXY(x, y);
        animatorCanvas.dynamics.resetDynamicsVariables();
        if (t == animatorCanvas.dragThing) {
            animatorCanvas.this_mouseReleased(null);
        }
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *  Get the height of an object.
     *
     *  @param              id the object identifier
     *
     *  @return the height
     */
    public double getH (int id) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  0;
        }
        return  t.getH();
    }

    /**
     * Change the height of an object.
     *
     * @param              id the object identifier
     * @param              h new height
     * @return             true if successful
     */
    public boolean setH (int id, double h) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setH(h);
        animatorCanvas.dynamics.resetDynamicsVariables();
        if (t == animatorCanvas.dragThing) {
            animatorCanvas.this_mouseReleased(null);
        }
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     * Get the width of an object.
     *
     * @param              id The id of the object.
     *
     * @return the width
     */
    public double getW (int id) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  0;
        }
        return  t.getW();
    }

    /**
     * Change the width of an object.
     *
     * @param              id the object identifier
     * @param              w new width
     * @return             true if successful
     */
    public boolean setW (int id, double w) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setW(w);
        animatorCanvas.dynamics.resetDynamicsVariables();
        if (t == animatorCanvas.dragThing) {
            animatorCanvas.this_mouseReleased(null);
        }
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     *   Gets the y position of an object.
     *
     *   @param              id the object identifier
     *
     *   @return the y position
     */
    public double getY (int id) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  0;
        }
        return  t.getY();
    }

    /**
     * Changes the y position of an object.
     *
     * @param              id The id of the object.
     * @param              y  new postion
     * @return             true if successful
     */
    public boolean setY (int id, double y) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setY(y);
        animatorCanvas.dynamics.resetDynamicsVariables();
        if (t == animatorCanvas.dragThing) {
            animatorCanvas.this_mouseReleased(null);
        }
        if (animatorCanvas.autoRefresh) {
            animatorCanvas.repaint();
        }
        return  true;
    }

    /**
     * Method setYPos
     *
     * @param id the object identifier
     * @param y
     *
     * @return true if successful
     */
    public boolean setYPos (int id, double y) {
        return  setY(id, y);
    }

    /**
     *   Get the x component of an object's velocity.
     *
     *   @param              id The id of the object.
     *
     *   @return  the x component of an object's velocity.
     */
    public double getVX (int id) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  0;
        }
        return  t.getVX();
    }

    /**
     * Change the x component of the speed of an object.
     *
     * @param              id The id of the object.
     * @param              vx  new vx
     * @return             true if successful
     */
    public boolean setVX (int id, double vx) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setVX(vx);
        animatorCanvas.dynamics.resetDynamicsVariables();
        return  true;
    }

    /**
     *   Get the y component of an object's velocity.
     *
     *   @param              id The id of the object.
     *
     *   @return the velocity component
     */
    public double getVY (int id) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  0;
        }
        return  t.getVY();
    }

    /**
     * Change the y component of an object's velocity.
     *
     * @param              id The id of the object.
     * @param vy
     * @return             true if successful
     */
    public boolean setVY (int id, double vy) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setVY(vy);
        animatorCanvas.dynamics.resetDynamicsVariables();
        return  true;
    }

    /**
     * Change the speed of an object.  Direction of motion remains unchanged.
     *
     * @param              id The id of the object.
     * @param              speed  new speed
     * @return             true if successful
     */
    public boolean setSpeed (int id, double speed) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        t.setSpeed(speed);
        animatorCanvas.dynamics.resetDynamicsVariables();
        return  true;
    }

    /**
     * Sets the charge on a Charge object.
     *
     * @param              id The id of the charge.
     * @param              q  new charge value
     * @return             true if successful
     */
    public boolean setCharge (int id, double q) {
        Thing t = animatorCanvas.getThing(id);
        if (t instanceof Charge) {
            ((Charge)t).q = q;
            animatorCanvas.dynamics.resetDynamicsVariables();
            return  true;
        }
        return  false;
    }

    /**
     *   Gets the x component of the force acting on an object.
     *
     *   @param              id The id of the object.
     *
     * @return the force
     */
    public double getFx (int id) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  0;
        }
        return  t.getFx();
    }

    /**
     * Sets the reference frame.
     * The new reference frame is the frame in which the object with id is stationary.
     *
     * @param id the object from which the motion will be observed
     *
     * @return true if successful
     */
    public boolean setReferenceFrame (int id) {
        animatorCanvas.clearTrails();
        if ((id == 0) || (id == animatorCanvas.hashCode())) {
            animatorCanvas.setReferenceObject(null);
            return  true;
        }
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  false;
        }
        animatorCanvas.setReferenceObject(t);
        return  true;
    }

    /**
     *   Get the y component of the force acting an object.
     *
     *   @param              id The id of the object.
     *
     * @return the force
     */
    public double getFy (int id) {
        Thing t = animatorCanvas.getThing(id);
        if (t == null) {
            return  0;
        }
        return  t.getFy();
    }

    //Get Applet information
    /**
     * Method getAppletInfo
     *
     *
     * @return the info
     */
    public String getAppletInfo () {
        return  "Animator4 Physlet by W. Christian. Email:wochristian@davidson.edu";
    }

    /**
     * Method getParameterInfo
     *
     *
     * @return the info
     */
    public String[][] getParameterInfo () {
        String pinfo[][] =  {
            {
                "FPS", "int", "Frames per sconds"
            },  {
                "PixPerUnit", "int", "Pixels for screen unit"
            },  {
                "ShowControls", "boolean", "Show the control buttons"
            },  {
                "dt", "double", "Animation time step per frame."
            },
        };
        return  pinfo;
    }

     /**
     * Method playBtn_actionPerformed
     *
     * @param e
     */
    void playBtn_actionPerformed (ActionEvent e) {
        forward();
    }

    /**
     * Method pauseBtn_actionPerformed
     *
     * @param e
     */
    void pauseBtn_actionPerformed (ActionEvent e) {
        pause();
    }

    /**
     * Method stepForwardBtn_actionPerformed
     *
     * @param e
     */
    void stepForwardBtn_actionPerformed (ActionEvent e) {
        stepForward();
    }

    /**
     * Method stepBackBtn_actionPerformed
     *
     * @param e
     */
    void stepBackBtn_actionPerformed (ActionEvent e) {
        stepBack();
    }

    /**
     * Method resetBtn_actionPerformed
     *
     * @param e
     */
    void resetBtn_actionPerformed (ActionEvent e) {
        setAnimationTime(0);
    }
    
    /**
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     *
     * @param args
     */
    public static void main (String[] args) {
        Animator applet = new Animator();
        applet.isStandalone = true;
        SFrame frame = new SFrame("Animator Physlet");
        frame.setTitle("Applet Frame");
        frame.add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        applet.setSize(400, 320);
        frame.setSize(400, 320);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width)/2, (d.height -
                frame.getSize().height)/2);
        frame.setVisible(true);
        
        applet.test();
    }

	public void test() {
        
       // from https://www.compadre.org/Physlets/mechanics/ex3_1.cfm 
		setAutoRefresh(false);
		setDefault();
		shiftPixOrigin(20,0);
		setPixPerUnit(10);
		setGridUnit(0);
		int id=addObject("circle","r=12");
		setTrajectory(id,"step(8-t)*(-22+5*t)+step(t-8)*(-22+5*8-2*(t-8))","step(8-t)*(-12+2*t)+step(t-8)*(-12+2*8+1*(t-8))");
		setRGB(id,0,0,0);
		id=addObject("circle","r=11");
		setTrajectory(id,"step(8-t)*(-22+5*t)+step(t-8)*(-22+5*8-2*(t-8))","step(8-t)*(-12+2*t)+step(t-8)*(-12+2*8+1*(t-8))");
		setRGB(id,255,0,0);
		setAutoRefresh(true);
		setOneShot(0,16,"End of Animation");
		updateDataConnections();
		setAnimationTime(0);
	}


}