////////////////////////////////////////////////////////////////////////////////
//      EnsemblePanel.java
//      Wolfgang Christianpackage diatomic;

/**
 * The class <code>PhysletPanel</code> adds interapplet communication to the EnsemblePanel
 * class.
 *
 * @author  Wolfgang Chrsitian
 * @version 1.0, 1 Oct 2000
 */
package diatomic;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import a2s.*;

import java.awt.event.*;

import edu.davidson.tools.*;

public class PhysletPanel extends EnsemblePanel implements SStepable, edu.davidson.tools.SDataSource {
    private int     boxWidth=0;
    private edu.davidson.display.Format mouseFormat= new edu.davidson.display.Format("%-+6.3g");
    private int mouseX=0, mouseY=0;

    double     scale=10.0;
    int         numSteps=1;
    Color backgroundColor=Color.lightGray;
    boolean mouseDown=false;
    Image      osi;    // the offscreen image for buffering
    Diatomic    owner       = null;
    Font       boldFont    = new Font("Helvetica", Font.BOLD, 14);
    boolean    timeDisplay = true;
    String[]   varStrings  = new String[]{ "t", "ke", "ke_atom", "ke_mol",
                                           "ke_mol_rot", "ke_mol_trans","n_atom","n_mol" };
    double[][] ds          = new double[1][8];    // the datasource state variables;
    public PhysletPanel(Diatomic o) {

        this();
        owner = o;
        try {
            SApplet.addDataSource(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  public PhysletPanel() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        this_mouseDragged(e);
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

    public void step(double dt, double time) {
        int mySteps=numSteps;
        this.dt=dt/mySteps;  // make sure the the time step is correct;
        for(int ii=0; ii<mySteps; ii++)monodiLoop();
        theTime=time+dt;
        paintOSI();
        Graphics g = getGraphics();
        g.drawImage(osi, 0, 0, this);    // draw the image onto the visible graph
        g.dispose();
        KinEnTotal();
        owner.updateDataConnections();
        //System.out.println("time="+theTime+" E_tot=" + KinEnTotalMD);
    }

    /**
 * put your documentation comment here
 */
    void createOSI() {
        if(iwidth<=0 || iheight<=0){
      	  return;
        }
        iwidth  = this.getSize().width;
        iheight = this.getSize().height;
        osi     = createImage(iwidth, iheight);    //create an off screen image
    }

    /**
     * put your documentation comment here
     */
    void paintOSI() {
        if(owner!=null && (owner.destroyed || !owner.isAutoRefresh())) return;
        if (osi == null || iwidth<=0 || iheight<=0) {
            return;
        }
        synchronized (osi) {
            Graphics g = osi.getGraphics();
            //g.setColor(getBackground());
            g.setColor(backgroundColor);
            g.fillRect(0, 0, iwidth, iheight);
            paintMonoat(g);
            paintDiat(g);
            paintTime(g);
            if(mouseDown)paintCoords( g, mouseX,mouseY);
            g.dispose();
        }
    }

    /**
     * put your documentation comment here
     * @param g
     */
    public void update(Graphics g) {

        paint(g);
    }    //update usually does a rect fill with a background color.  We don't need this.

    /**
     * put your documentation comment here
     * @param g
     */
    public void paint(Graphics g) {
        if(owner!=null && (owner.destroyed || !owner.isAutoRefresh())) return;
        if ((getSize().width == 0) || (getSize().height == 0)) {
            return;
        }
        if ((osi == null) || (iwidth != this.getSize().width) || (iheight != this.getSize().height)) {
            iwidth  = this.getSize().width;
            iheight = this.getSize().height;
            //worldXYmax=Math.min(iwidth,iheight);
            osi = createImage(iwidth, iheight);    //create an off screen image
            paintOSI();
        }
        if (owner.isClockRunning()) {
            return;    // let the animation repaint the screen
        }
        if (osi == null) {
            return;
        }
        synchronized (osi) {
            g.drawImage(osi, 0, 0, this);    // draw the image onto the visible graph
        }
    }

    /**
 * Paint the diatomic moleculules
 */
    void paintDiat(Graphics g) {
      if(owner!=null && (owner.destroyed || !owner.isAutoRefresh())) return;

        int dia = 2 * radiusD;
        for (int i = 1; i <= mnMax; i++) {
            //if(i==1)g.setColor(new Color(0,0,64)); else
            g.setColor(Color.black);
            g.drawLine((int) (x[i][1]), (int) (y[i][1]), (int) (x[i][2]), (int) (y[i][2]));
            if(molColor[i]==Color.green){
              g.setColor(Color.green);
              g.fillOval((int) (x[i][1] - radiusD), (int) (y[i][1] - radiusD), dia, dia);
              g.setColor(Color.yellow);
              g.fillOval((int) (x[i][2] - radiusD), (int) (y[i][2] - radiusD), dia, dia);
            }else{
              g.setColor(molColor[i]);
              g.fillOval((int) (x[i][1] - radiusD), (int) (y[i][1] - radiusD), dia, dia);
              g.fillOval((int) (x[i][2] - radiusD), (int) (y[i][2] - radiusD), dia, dia);
            }
        }
    }

    /**
     * Paint the atoms
     */
    void paintMonoat(Graphics g) {
        if(owner!=null && (owner.destroyed || !owner.isAutoRefresh())) return;
        int dia = 2 * radiusM;
        for (int i = 1; i <= anMax; i++) {
            //if(i==1)g.setColor(new Color(64,0,0));else
            if(atomFixed[i]) g.setColor(Color.black);
            else g.setColor(atomColor[i]);
            g.fillOval((int) (ax[i] - radiusM), (int) (ay[i] - radiusM), dia, dia);
        }
    }

    /**
     * put your documentation comment here
     * @param g
     */
    void paintTime(Graphics g) {

        g.setColor(Color.black);
        Font f = g.getFont();
        g.setFont(boldFont);
        String tStr = new edu.davidson.display.Format("%7.4g").form(edu.davidson.tools.SUtil.chop(theTime, 1.0e-12));
        if (timeDisplay) {
            if (iwidth > 150) {
                g.drawString(owner.label_time+ " " + tStr, 10, 15);
            } else {
                g.drawString(owner.label_time+ " " + tStr, 10, iheight - 40);
            }
        }
        g.setFont(f);
    }

    public double[][] getVariables() {
        double scale2=scale*scale;
        ds[0][0] = theTime;    //time
        ds[0][1] = KinEnTotalMD/scale2;
        ds[0][2] = KinEnMonoat/scale2;
        ds[0][3] = KinEnDiat/scale2;
        ds[0][4] = KinEnDiatRot/scale2;
        ds[0][5] = KinEnDiatTrans/scale2;
        ds[0][6] = anMax;  // number of atoms
        ds[0][7] = mnMax ; // number of diatomics
        return ds;
    }

    public String[] getVarStrings() {

        return varStrings;
    }

    public int getID() {

        return hashCode();
    }

    public void setOwner(SApplet app) {

        ;
    }

    public SApplet getOwner() {

        return owner;
    }    //owner is the SApplet


    /**
    *
    * Returns the id of an atom.
    *
    * The atom id can be used to make a data connection to the atom's dynamic variables.
    *
    * @return int  the id.
    *
    */
    public int addAtomDataSource(int i) {

        AtomSource ds = new AtomSource(i);
        return ds.hashCode();
    }

    /**
  *
  * Returns the id of an diatomic molecule.
  *
  * The diatomic id can be used to make a data connection to the molecule's dynamic variables.
  *
  * @return int  the id.
  *
  */
    public int addDiatomicDataSource(int i) {

        DiatomicSource ds = new DiatomicSource(i);
        return ds.hashCode();
    }

    // inner class used for data connection to particles.
    public class AtomSource extends Object implements edu.davidson.tools.SDataSource {    // inner class to access the particles as SDataSources.

        String[]   varStrings = new String[]{ "t", "x", "y", "vx", "vy", "m" };
        double[][] ds         = new double[1][6];    // the datasource state variables t,x,y,vx,vy,ax,ay,p;
        int        index      = 0;
        AtomSource(int i) {    // Constructor

            index = i;
            try {
                SApplet.addDataSource(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public double[][] getVariables() {

            ds[0][0] = theTime;    //t
            if ((index > anMax) || (index < 1)) {
                System.out.println("ERROR: Atom index is out of range .");
                ds[0][1] = 0;             //x
                ds[0][2] = 0;             //y
                ds[0][3] = 0;             //vx
                ds[0][4] = 0;             //vy
                ds[0][5] = 0;             //m
                return ds;
            } else {
                ds[0][1] = ax[index]/scale;     //x
                ds[0][2] = (iheight-ay[index])/scale;     //y
                ds[0][3] = avx[index]/scale;    //vx
                ds[0][4] = -avy[index]/scale;    //vy
                ds[0][5] = mM;             //m
            }
            return ds;
        }

        public String[] getVarStrings() {

            return varStrings;
        }

        public int getID() {

            return hashCode();
        }

        public void setOwner(SApplet applet) {

            ;
        }

        public SApplet getOwner() {

            return owner;
        }    //owner is the SApplet
    }
    public class DiatomicSource extends Object implements edu.davidson.tools.SDataSource {    // inner class to access the particles as SDataSources.

        String[]   varStrings = new String[]{ "t", "x", "y", "vx", "vy", "m",
                                              "theta", "w","inertia" };
        double[][] ds         = new double[1][9];    // the datasource state variables t,x,y,vx,vy,ax,ay,p;
        int        index      = 0;
        DiatomicSource(int i) {    // Constructor

            index = i;
            try {
                SApplet.addDataSource(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public double[][] getVariables() {

            ds[0][0] = theTime;    //t
            if ((index > mnMax) || (index < 1)) {
                System.out.println("ERROR: Atom index is out of range .");
                ds[0][1] = 0;              //x
                ds[0][2] = 0;              //y
                ds[0][3] = 0;              //vx
                ds[0][4] = 0;              //vy
                ds[0][5] = 0;              //m
                ds[0][6] = 0;              //theta
                ds[0][7] = 0;              //omega
                ds[0][8] = 0;              //moment of inertia
                return ds;
            } else {
                ds[0][1] = xcm[index]/scale;     //x
                ds[0][2] = (iheight -ycm[index])/scale;     //y
                ds[0][3] = vxcm[index]/scale;    //vx
                ds[0][4] = -vycm[index]/scale;    //vy
                ds[0][5] = mD;              //m
                ds[0][6] = teta[index];    //omega
                ds[0][7] = w[index];       //omega
                ds[0][8] =IM/scale/scale;              //moment of inertia
            }
            return ds;
        }

        public String[] getVarStrings() {

            return varStrings;
        }

        public int getID() {

            return hashCode();
        }

        public void setOwner(SApplet applet) {

            ;
        }

        public SApplet getOwner() {

            return owner;
        }    //owner is the SApplet
    }


    void paintCoords(Graphics g){
      paintCoords(g,mouseX,mouseY);
  }

  void paintCoords( int xPix,int yPix){
        Graphics g=getGraphics();
        paintCoords( g,  xPix, yPix);
        g.dispose();
  }

  void paintCoords(Graphics g, int xPix,int yPix){
        String msg=""+mouseFormat.form(xPix/scale)+ " , "+mouseFormat.form((iheight-yPix)/scale);
        java.awt.Rectangle r = getBounds();
        g.setColor(Color.yellow);
        FontMetrics fm=g.getFontMetrics(g.getFont());
        boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
        g.fillRect(0,r.height-20,boxWidth,20);
        g.setColor(Color.black);
        g.drawString(msg,10,r.height-5);
        g.drawRect(0,r.height-20,boxWidth-1,20);
  }

  void this_mousePressed(MouseEvent e) {
      if(( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){
          //  right mouse click;
      } else{   // left mouse click so paint coordinates.
          mouseX=e.getX();
          mouseY=e.getY();
          mouseDown=true;
          if(!owner.clock.isRunning() )paintCoords(mouseX,mouseY);
      }
  }

  void this_mouseDragged(MouseEvent e) {
      mouseX=e.getX();
      mouseY=e.getY();
      if(!owner.clock.isRunning() )paintCoords(mouseX,mouseY);
  }

  void this_mouseReleased(MouseEvent e) {
      mouseDown=false;
      mouseX=e.getX();
      mouseY=e.getY();
      java.awt.Rectangle r = getBounds();
      if(!owner.clock.isRunning() )repaint(0,r.height-20,boxWidth,20);
      boxWidth=0;  // reset the yellow message box.
  }

  void this_mouseEntered(MouseEvent e) {
     owner.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

  void this_mouseExited(MouseEvent e) {
     owner.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
}


/*--- Formatted in Physlet Style Style on Tue, Nov 14, '00 ---*/
