/*
 * put your module comment here
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */


package  ripple;

import  java.awt.*;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import  java.util.Vector;

import javax.swing.Timer;

import  java.util.Enumeration;
import  java.awt.image.MemoryImageSource;
import  edu.davidson.display.SScalable;
import  edu.davidson.display.Thing;
import edu.davidson.display.Format;


/**
 * put your documentation comment here
 */
class RippleCanvas extends Canvas implements Runnable, SScalable {
    Image img;
    Image osi; // off screen image for buffering
    Format    format= new Format("%-+8.4g");
    int pixPerUnit = 10;
    double gridUnit = 1;
    int sleepTime = 50;
    Vector sources = new Vector();
    Vector things = new Vector();
    public String msgStr = "";
    private int iwidth = 0;
    private int iheight = 0;
    private boolean showCoord = true;          //display mouse coordinates
    private double mouseX, mouseY;                // coordinates for mouse down
    private boolean mousePressed=false;
    private   int     boxWidth=0;
    private Thread movieThread;
    Vector frames = new Vector();
    int current = 0;
    // boolean running=false;
    boolean imageLoaded = true;
    Source dragSource = null;
    Thing     dragThing=null;
    double wavelength = 0;
    boolean showInfo = true;
    Ripple owner = null;
    boolean shouldRun=false;


    /**
     * Stops the movie thread.
     */
    public synchronized void stop () {
        Thread myThread = movieThread;
        if (movieThread == null || Ripple.isJS) {
        	movieThread = null;
            return;
        }
        if (myThread != null) {
            //myThread.stop();
            movieThread = null;                 // no need to call stop.  This should kill the thread.
            try {
            	/** @j2sNative */ {myThread.join();}
            } catch (InterruptedException e) {}
        }
        movieThread = null;
    }


    /*
     public void stopThread() {
     Thread myThread=movieThread;
     if( myThread!=null){
     myThread.stop();
     try{ myThread.join();} catch (InterruptedException e){}
     }
     running=false;
     movieThread=null;
     }*/
    public synchronized void start () {
        //System.out.println("RippleCanvas.start");
        if (movieThread == null) {
            movieThread = new Thread(this);
            //running=true;
            movieThread.start();
        }
        imageLoaded = true;
    }
    
    private Timer timer;  
    
    /**
     * Runs the movie thread.
     */
	public void run() {
		boolean isJS = /** @j2sNative true || */ false;
		Graphics g = getGraphics();
		if (g == null) {
			movieThread = null;
			return;
		}
		while ((movieThread != null) && (sources.size() > 0) && (frames.size() > 1) && owner.getRunningID() == owner) {
				try {
					if (current >= frames.size())
						current = 0;
					if (frames.size() > 0)
						img = (Image) frames.elementAt(current);
					if (movieThread != null) {
						paint(g);
						if (mousePressed)
							paintCoordinates(g, mouseX, mouseY);
					}
					current++;
					if(isJS) {
						timer = new Timer(sleepTime, new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								run();
							}

						});
						timer.setRepeats(false);
						timer.start();
						return;
					} else if (movieThread != null) {
						Thread.currentThread();
							Thread.sleep(sleepTime);
					}
				} catch (InterruptedException e) { }
		}
		g.dispose();
		imageLoaded = true;
		repaint();
	}

    /**
     * put your documentation comment here
     * @param t
     */
    void setSleepTime (int t) {
        sleepTime = t;
    }

    /**
     * put your documentation comment here
     * @param w
     */
    void setWavelength (double w) {
        wavelength = w;
    }

    /**
     * put your documentation comment here
     * @param si
     */
    void setShowInfo (boolean si) {
        showInfo = si;
    }

    /**
     * Override to remove flicker.
     * @param g
     */
    public void updateOLD (Graphics g) {
        paint(g);               //update usually does a rect fill with a background color.  We don't need this.
    }

    /**
     * put your documentation comment here
     * @param pu
     */
    public void setPixPerUnit (int pu) {
        pixPerUnit = pu;
    }

    public void paint () {
       Graphics g=getGraphics();
       if(g==null) return;
       paint(g);
       g.dispose();
    }

    /**
     * put your documentation comment here
     * @param g
     */
    public void paint (Graphics g) {
        if (g == null)return;
        Source source;
        iwidth = getSize().width;
        iheight = getSize().height;
        int xo = iwidth/2;
        int yo = iheight/2;
        if (sources.size() == 0)
            msgStr = owner.label_nosource;
        int k;                  // loop counter
        if(osi==null || osi.getHeight(this)!=iheight || osi.getWidth(this)!=iwidth){
           if(iwidth<=0 || iheight<=0) return;
           osi= createImage(iwidth,iheight);
        }
        if (img == null) {
            // System.out.println("RippleCanvas.Paint null");
            super.paint(g);
            g.setColor(Color.black);
            FontMetrics fm = g.getFontMetrics();
            int x = (iwidth - fm.stringWidth(msgStr))/2;
            g.drawString(msgStr, x, 30);
            x = (iwidth - fm.stringWidth(owner.label_wavelength+" " + wavelength))/2;
            if (showInfo)
                g.drawString(owner.label_wavelength+" " + wavelength, x, 60);
            paintThings(g);
        }
        else if (sources.size() > 0){         // draw the current image if we have at least one source
            if(things.size()>0){
              Graphics osg=osi.getGraphics();
              osg.drawImage(img, 0, 0, iwidth, iheight, this);
              this.paintThings(osg);
              osg.dispose();
              g.drawImage(osi, 0, 0, iwidth, iheight, this);
            }else g.drawImage(img, 0, 0, iwidth, iheight, this);
            paintThings(g);
            g.setColor(Color.red);
            if (movieThread != null)
                g.drawString(owner.label_running, 10, 15);
            else
                g.drawString(owner.label_frame+" " + current, 10, 15);
        }
    }

    /**
     * put your documentation comment here
     * @return
     */
    public Dimension minimumSize () {
        return  new Dimension(20, 20);
    }

    /**
     * put your documentation comment here
     * @return
     */
    public Dimension preferredSize () {
        return  new Dimension(200, 200);
    }

    /**
     * put your documentation comment here
     * @return
     */
    public Image getImage () {
        return  img;
    }

    /**
     * put your documentation comment here
     * @return
     */
    public int getImageNumber () {
        return  frames.size();
    }

    /**
     * put your documentation comment here
     * @return
     */
    public boolean isRunning () {
        if (movieThread == null)
            return  false;
        else
            return  true;
    }

    /**
     * put your documentation comment here
     * @return
     */
    public boolean isImageLoaded () {
        return  imageLoaded;
    }

    /**
     * put your documentation comment here
     * @param img_
     */
    synchronized public void setImage (Image img_) {
        img = img_;
        repaint();
        imageLoaded = true;
    }

    /**
     * put your documentation comment here
     */
    void showImageSize () {
        System.out.print("Image w: " + img.getWidth(this));
        System.out.println("Image h: " + img.getHeight(this));
    }

    /**
     * put your documentation comment here
     * @param t
     */
    public void addThing (Thing t) {
        things.addElement(t);
    }

    public int addSource(double x, double y, double amp, double phase, int radius){
       Source s=new Source(owner,this,x,y,amp,phase,radius);
       //s.setSize((4 + 1.0)/(double)pixPerUnit);
       sources.addElement(s);
       things.addElement(s);
       setImage(null);
       frames.removeAllElements();
       msgStr = owner.label_clickdrag;
       repaint();
       return s.hashCode();
    }

    /**
     * put your documentation comment here
     * @param width
     * @param height
     * @param pixels
     * @param offset
     * @param scan
     * @param count
     * @return
     */
    synchronized boolean addImage (int width, int height, int[] pixels, int offset,
            int scan, int count) {
        imageLoaded = false;
        MemoryImageSource imagesource = new MemoryImageSource(width, height, pixels, 0, scan);
        Image newImage = createImage(imagesource);
        img = newImage;
        //showImageSize();
        if (count < owner.nimages) {
            frames.addElement(newImage);
            current = count;
        }
        paintImage();
        return  true;
    }

    /**
     * put your documentation comment here
     * @param image
     * @param flags
     * @param x
     * @param y
     * @param w
     * @param h
     * @return
     */
    synchronized public boolean imageUpdate (Image image, int flags, int x,
            int y, int w, int h) {
        //System.out.println("Image update width: "+w+"  height: "+h);
        if ((flags & ALLBITS) != 0) {
            // System.out.println("ALLBITS width: "+w+"  height: "+h);
            imageLoaded = true;
        }
        return  true;
    }

    /**
     * put your documentation comment here
     */
    public void paintImage () {
        Graphics g = getGraphics();
        if (g == null)
            return;
        paint(g);
        g.dispose();
    }

    /**
     * put your documentation comment here
     * @param im
     */
    private void paintThings (Graphics g) {
        Thing t = null;
        for (Enumeration e = things.elements(); e.hasMoreElements();) {
            t = (Thing)e.nextElement();
            t.paint(g);
        }
    }

    /**
     * put your documentation comment here
     * @param i
     */
    public void step (int i) {
        int n = frames.size() - 1;
        if (n < 1)
            return;
        current = current + i;
        while (current > n)
            current = current - n - 1;
        while (current < 0)
            current = current + n + 1;
        img = (Image)frames.elementAt(current);
        imageLoaded = true;
        paintImage();
    }

    /**
     * put your documentation comment here
     * @param xpix
     * @return
     */
    public double xFromPix (int xpix) {
        int xo = iwidth/2;
        return  (xpix - xo)/(1.0*pixPerUnit);
    }

    /**
     * put your documentation comment here
     * @param ypix
     * @return
     */
    public double yFromPix (int ypix) {
        int yo = iheight/2;
        return  -(ypix - yo)/(1.0*pixPerUnit);
    }

    /**
     * put your documentation comment here
     * @param x
     * @return
     */
    public int pixFromX (double x) {
        return  (int)(iwidth/2.0 + x*pixPerUnit);
    }

    /**
     * put your documentation comment here
     * @param y
     * @return
     */
    public int pixFromY (double y) {
        return  (int)(iheight/2.0 - y*pixPerUnit);
    }

    /**
     * put your documentation comment here
     * @return
     */
    public int getPixWidth () {
        return  iwidth;
    }

    /**
     * put your documentation comment here
     * @return
     */
    public int getPixHeight () {
        return  iheight;
    }

    /**
     * put your documentation comment here
     * @param x
     * @param y
     */
    public void setDragSource (int x, int y) {
        Source source;
        int n = sources.size();
        dragSource = null;
        if (img != null)
            return;             // only drag if we are in edit mode;
        if (n < 1)
            return;
        for (int i = 0; i < n; i++) {
            source = (Source)sources.elementAt(i);
            if (source.isInsideThing(x, y))
                dragSource = source;
        }
    }

    public Thing getThing(int id){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(t.hashCode()==id) return t;
    }
    return null;
  }

  boolean setColor(int id, Color c){
    Thing t=getThing(id);
    if (t==null) return false;
    t.setColor(c);
    return true;
  }

    /**
   * Make the object with the given id dragable.
   *
   * @param              id The id of the object.
   * @param              drag Dragable?
   * @return             true if successful.
   */
  public boolean setDragable(int id, boolean drag){
      Thing t=getThing(id);
      if(t==null) return false;
      t.setDragable(drag);
      return true;
  }

 boolean isInsideDragableThing(int x, int y){
    Thing t=null;
    for( Enumeration e=things.elements(); e.hasMoreElements();){
         t= (Thing) e.nextElement();
         if(!t.isNoDrag() &&
             t.isInsideThing(x,y)){
           return true;
         }
    }
    return false;
  }

  public void paintCoordinates (double x, double y) {
       Graphics g=getGraphics();
       if(g==null) return;
       paintCoordinates(g,x,y);
       g.dispose();
    }

    void paintCoordinates(Graphics g, double x,double y){
      if(!showCoord) return;
      FontMetrics fm=g.getFontMetrics(g.getFont());
      String msg="";
      msg="x=" + format.form(x)+ " y=" +format.form(y);
      g.setColor(Color.yellow);
      g.fillRect(0,getBounds().height-15,boxWidth,15);
      boxWidth=20+fm.stringWidth(msg);
      g.fillRect(0,getBounds().height-15,boxWidth,15);
      g.setColor(Color.black);
      g.drawString(msg, 8, getBounds().height-2);
  }





/**
 * Constructor
 * @param     SApplet the applet that created this canvas
 */
    public RippleCanvas (Ripple o) {
        this();
        owner = o;
        msgStr = owner.label_wait;
    }

  public RippleCanvas() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        this_mouseReleased(e);
      }
    });
    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        this_mouseMoved(e);
      }
      public void mouseDragged(MouseEvent e) {
        this_mouseDragged(e);
      }
    });
  }

  void this_mouseMoved(MouseEvent e) {
      if(isInsideDragableThing((int)e.getX(),(int)e.getY()) ) setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

  void this_mousePressed(MouseEvent e) {
      mousePressed=true;
      int xpt=e.getX();
      int ypt=e.getY();
      mouseX=xFromPix(xpt);
      mouseY=yFromPix(ypt);
      for( Enumeration enumEl=things.elements(); enumEl.hasMoreElements();){
       Thing t= (Thing) enumEl.nextElement();
       if(!t.isNoDrag() && t.isInsideThing(xpt,ypt)){
         dragThing=t;
         if(dragThing instanceof Source){
             shouldRun=owner.m_animate;;
         }
       }
      paintCoordinates ( mouseX,  mouseY);
    }
  }

  void this_mouseDragged(MouseEvent e) {
    int xpt=e.getX();
    if(xpt<1) xpt=1;
      else if(xpt>iwidth-2) xpt=iwidth-2;
    int ypt=e.getY();
    if(ypt<1) ypt=1;
      else if(ypt>iheight-2) ypt=iheight-2;
    mouseX=xFromPix(xpt);
    mouseY=yFromPix(ypt);
    if(dragThing!=null){
         if(dragThing instanceof Source){
             owner.stopAnimate();
         }
         dragThing.setXY(mouseX,mouseY);
         dragThing.updateMySlaves();
         if(owner!=null) owner.updateDataConnection(dragThing.hashCode());
    }
    paint ();
    paintCoordinates ( mouseX,  mouseY);
  }

  void this_mouseReleased(MouseEvent e) {
    mousePressed=false;
    int xpt=e.getX();
    if(xpt<1) xpt=1;
      else if(xpt>iwidth-2) xpt=iwidth-2;
    int ypt=e.getY();
    if(ypt<1) ypt=1;
      else if(ypt>iheight-2) ypt=iheight-2;
    double x=xFromPix(xpt);
    double y=yFromPix(ypt);
    boolean needsRecalc=false;
    if(dragThing!=null){
            dragThing.setXY(x,y);
            dragThing.updateMySlaves();
            if(owner!=null) owner.updateDataConnections();
            needsRecalc=dragThing instanceof Source;
            Vector slaves=dragThing.getSlaves();
            Thing t = null;
            if(!needsRecalc)
              for (Enumeration enumEl = slaves.elements(); enumEl.hasMoreElements();) {
                    t = (Thing)enumEl.nextElement();
                    if(t instanceof Source){
                      needsRecalc=true;
                      break;
                    }
              }
    }
    boxWidth=0;
    if(needsRecalc){
        if(shouldRun){
            owner.recalculate();
            owner.startAnimate();
        }else owner.recalculate();
    }
    else paint ();
    dragThing=null;
  }
}



