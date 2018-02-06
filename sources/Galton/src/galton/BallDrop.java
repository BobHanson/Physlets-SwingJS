package galton;
/*
 * Copyright (c) 1996 David Krider. All Rights Reserved.
 *
 * DAVID KRIDER MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. DAVID KRIDER SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES. IN FACT, DAVID
 * KRIDER NEVER TAKES RESPONSIBILITY FOR ANYTHING, ANYTIME, ANYWHERE.
 * LET'S KEEP IT THAT WAY.
 */


import java.util.*;
import java.awt.*;
import java.awt.image.*;

import edu.davidson.graphics.Util;
import edu.davidson.tools.*;

/**
 * Ball implements a ball object which keeps track of the position,
 * velocity and radius of the balls which fall through the pins.
 * @version  1.0
 */

class Ball implements ImageObserver {
  /**
   * The degree to which the ball will slow down after hitting the bottom
   * off the screen or a pin
   */
  static final double slowdown = .75;

  double x,y,vx,vy,vlen,r;
  int w,h;

  /**
   * The image that will represent the ball on the screen
   */
  Image itsImage = null;

  /*
   * Construct the ball with an initial position and zero velocity
   * @param tx The initial x position
   * @param ty The initial y position
   */

  Ball(double tx,double ty) {
    x=tx;
    y=ty;
    vx=0;
    vy=0;
    vlen=0;
  }

  /**
   * Move the ball by adding the current velocity to the current
   * position
   */

  void Move() {
    x += vx;
    y += vy;
  }

  /**
   * Accelerate the ball by modifying the velocity
   * @param ax The change in the x velocity
   * @param ay The change in the y velocity
   */

  void Accelerate(double ax,double ay) {
    vx += ax;
    vy += ay;
    vlen=Math.sqrt(vx*vx+vy*vy);
  }

  /**
   * Check to see if the ball hit a pin.  If so, bounce the ball off the
   * pin.
   * @param pinx The x position of the pin
   * @param piny The y position of the pin
   * @param pinr The radius of the pin
   * @return true if it hit the pin
   */

  boolean Boink(int pinx,int piny,double pinr) {
    double dx,dy,dist;

    dx=x-(double)pinx;
    dy=y-(double)piny;

    dist=Math.sqrt(dx*dx+dy*dy);

    if(dist<=pinr+r) {
      double tvx,tvy,proj;

      /*
       * A bunch of math to handle the change in velocity that accompanies
       * striking the pin.  This is all done in floating point arithmetic.
       * Things would probably go faster if this were done in scaled
       * integers, so go to work and send me the modifications when you're
       * done.
       */

      tvx = vx/vlen;
      tvy = vy/vlen;

      dx /= dist;
      dy /= dist;

      proj=tvx*dx+tvy*dy;

      tvx -= 2.0*proj*dx;
      tvy -= 2.0*proj*dy;

      vx = tvx*vlen;
      vy = tvy*vlen;

      /*
       * Sometimes the ball ends up inside the pin and keeps bouncing
       * around inside of it.  This isn't good.  As a fix, keep moving
       * the ball until it is outside of the pin
       */

      do {
        x += vx;
        y += vy;

        dx=x-(double)pinx;
        dy=y-(double)piny;

        dist=Math.sqrt(dx*dx+dy*dy);
      } while(dist<=pinr+r);

      /*
       * Slow the ball down a little to keep things mellow
       */

      vx *= slowdown;
      vy *= slowdown;

      vlen=Math.sqrt(vx*vx+vy*vy);

      return true;
    }
    else return false;
  }

  /**
   * Set the image that this ball uses to draw itself
   * @param aImage The image to use
   */

  void SetImage(Image aImage) {
    itsImage=aImage;
    w=itsImage.getWidth(this);
    h=itsImage.getHeight(this);
    r=w/2.0;
  }

  /**
   * Draw the ball at the correct position
   * @param g The current graphics context
   */

  void Draw(Graphics g) {
    if(itsImage != null)
      g.drawImage(itsImage,(int)x-w/2,(int)y-h/2, this);
  }

  /**
   * Get the correct width and height of the ball.  This probably wont
   * be used since the image is already loaded and ready due to the use
   * of the MediaTracker but the AWT has me nervous so I'll be extra
   * careful.
   */

  public boolean imageUpdate(Image img,
                             int infoflags,
                             int x,
                             int y,
                             int width,
                             int height)
  {
    if((infoflags & (WIDTH | HEIGHT)) == (WIDTH | HEIGHT)) {
      if(img == itsImage) {
        w=width;
        h=height;
        r=w/2;
      }
      return false;
    }

    return true;
  }

}

/**
 * BallDrop is an 'applet' that manages a set of Ball objects that are
 * falling through a set of pins.  This serves as an example of double
 * buffered animation and the use of Java as an educational tool.  What can
 * you learn from this Applet?  Probably that there are several correct ways
 * to do the same thing, and that this isn't one of them.
 */

public class BallDrop extends SApplet implements Runnable, SDataSource {
  String[] varStrings= new String[]{"t","mean","std","n"};
  double[][] ds=new double[1][4];
  double time=0;
  int totalBalls=0;

  Thread itsThread = null;
  //MediaTracker itsTracker = null;
  Vector itsBalls = null;
  double mean=0,stdev=0;
  boolean showFunc=true; // added by wc
  int countBalls;   // added by wc
  int maxBalls=400; // added by wc
  boolean preLab=false;  // added by wc
  double  trueMean=0.0;
  boolean firstTime=true;

  /*
   * Images for the double buffered animation
   */

  Dimension offDimension,backDimension;
  Image offImage,backImage;

  /*
   * Default settings for applet parameters
   */

  int numrows=8,numcolumns=20,numballs=10,delay=10,
    topspace=30,sidespace=20;

  /*
   * The images for the balls and pins
   */

  Image pin,ball;
  double pinr;
  int ballw,ballh,pinw,pinh;

  /*
   * Data on the current accumulation of balls in the rack at the
   * bottom of the screen
   */
  int numracks,rackheight[],rackdel[];

  /**
   * The code that initializes the applet.  Loads in the images for the
   * balls and pins using the MediaTracker.  Also start out the balls with
   * a little nudge to get them going.
   */
  public void init() {
    String param = null;

    //itsTracker=new MediaTracker(this);

    param=getParameter("BallImage");
    if(param==null) param="galtonimages/smallball.gif";
    //ball = getImage(getDocumentBase(),param);
    ball=Util.getImage(param,this);

    param=getParameter("PinImage");
    if(param==null) param="galtonimages/smallpin.gif";
    //pin = getImage(getDocumentBase(),param);
    pin=Util.getImage(param,this);

    param=getParameter("NumRows");
    if(param != null) numrows=Integer.valueOf(param).intValue();
    param=getParameter("NumColumns");
    if(param != null) numcolumns=Integer.valueOf(param).intValue();
    param=getParameter("NumBalls");
    if(param != null) numballs=Integer.valueOf(param).intValue();
    param=getParameter("Delay");
    if(param != null) delay=Integer.valueOf(param).intValue();
    param=getParameter("TopSpace");
    if(param != null) topspace=Integer.valueOf(param).intValue();
    param=getParameter("SideSpace");
    if(param != null) sidespace=Integer.valueOf(param).intValue();
    // the following parameteres were added by Wolfgang Christian
    param=getParameter("MaxBalls");
    if(param != null) maxBalls=Integer.valueOf(param).intValue();
    param=getParameter("PreLab");
    if(param != null) preLab=Boolean.valueOf(param).booleanValue();
    param=getParameter("ShowFunc");
    if(param != null) showFunc=Boolean.valueOf(param).booleanValue();

/*
    itsTracker.addImage(ball,0);
    itsTracker.addImage(pin,0);

    try {
      itsTracker.waitForAll();
    }
    catch (InterruptedException e) {
      System.out.println("Ball or pin images not found.");
      return;
    }
*/
    if(pin==null) System.out.println("Pin image not found.");
    if(ball==null) System.out.println("Ball image not found.");

    pinw = pin.getWidth(this);
    pinh = pin.getHeight(this);
    pinr = pinw/2.0;
    ballw = ball.getWidth(this);
    ballh = ball.getHeight(this);
    addDataSource(this);
  }

  public void reset(){resetBoard();}
  public void setDefault(){super.setDefault();resetBoard();}

  private synchronized void resetBoard(){
    stop();
    trueMean=0.0;
    int i;
    Ball aBall;
    Dimension d = getSize();
    for(i=0;i<numracks;++i){
      rackheight[i]=0;
      rackdel[i]=0;
    }
    itsBalls.removeAllElements();
    countBalls=maxBalls;
    for(i=0;i<numballs;++i){
      aBall = new Ball(d.width/2,0);
      countBalls--;
      aBall.Accelerate((Math.random()-.5)*.5,0);
      aBall.SetImage(ball);
      itsBalls.addElement(aBall);
    }
    mean=0; stdev=0;
    time=0;
    totalBalls=0;
    this.clearAllData();
    repaint();
    start();
  }

  public void forward(){
    start();
  }

  public void pause(){
    stop();
  }

  protected void pausingClock(){ pause(); }


  /**
   * Begin the animation thread
   */
  public void start() {
    if(firstTime){
        firstTime=false;
        Dimension d = getSize();
        Ball aBall;
        numracks=getSize().width/ballw;
        System.out.println("ball=" + ballw);
        System.out.println("numracks=" + numracks);
        rackheight=new int[numracks];
        rackdel=new int[numracks];
        for(int i=0;i<numracks;++i) {
          rackheight[i]=0;
          rackdel[i]=0;
        }

        /*
         * Allocate and start the balls out with a small initial velocity
         */

        itsBalls=new Vector();
        countBalls=maxBalls;
        for(int i=0;i<numballs;++i) {
          aBall = new Ball(d.width/2,0);
          countBalls--;
          aBall.Accelerate((Math.random()-.5)*.5,0);
          aBall.SetImage(ball);
          itsBalls.addElement(aBall);
        }
    }
    if (itsThread == null) {
      this.setRunningID(this);
      itsThread = new Thread(this);
      itsThread.start();
    }

  }

  /**
   * Stop the tread
   */

  public void stop() {
    Thread myThread=itsThread;
    itsThread = null;
    if(myThread==null) return;
    try{myThread.join();}catch (InterruptedException e){}  // wait for the thread to die
    offImage = null;
    backImage = null;
  }

  /**
   * Stop the animation when the user clicks on the applet.
   */

  public boolean mouseDown(Event e, int x, int y) {
    if (itsThread == null) {
      start();
    }else {
      Thread myThread=itsThread;
      itsThread = null;
      if(myThread!=null)
        try{myThread.join();}catch (InterruptedException ex){}  // wait for the thread to die
    }
    return false;
  }

  public void destroy() {
      Thread myThread=itsThread;
      itsThread = null;
      if(myThread!=null)
        try{myThread.join();}catch (InterruptedException e){}  // wait for the thread to die
      super.destroy();
  }

  /**
   * The thread has begun to run
   */
  public void run() {
    // Remember the starting time
    long startTime = System.currentTimeMillis();

    while (Thread.currentThread() == itsThread && this.getRunningID()==this ) {
      UpdateBalls();

      repaint();

      // Delay depending on how far we are behind.
      time+= delay/1000.0;
      try {
        startTime += delay;
        Thread.sleep(Math.max(10,
                              startTime-System.currentTimeMillis()));
      }
      catch (InterruptedException e) {
        break;
      }
      startTime=System.currentTimeMillis();
    }
    itsThread=null;
  }

  /**
   * Paint the next frame in the animation
   */

  public void paint(Graphics g) {
    Graphics offGraphics;
    Ball aBall;
    Dimension d = getSize();

    /*
     * Create the background image if necessary
     */

    if ( (backImage == null)
         || (d.width != backDimension.width)
         || (d.height != backDimension.height) ) {
      Graphics backGraphics;

      backDimension = d;
      backImage = createImage(d.width, d.height);
      backGraphics = backImage.getGraphics();

      /*
       * Erase the previous image.
       */

      backGraphics.setColor(getBackground());
      backGraphics.fillRect(0, 0, d.width, d.height);
      backGraphics.setColor(Color.black);

      /*
       * Paint the background goodies into the background image
       */

      paintBackground(backGraphics);
    }
    else {
      /*
       * The applet wasn't resized so just make sure that the image of
       * the rack is up to date
       */

      updateRack(backImage.getGraphics());
    }

    if ( (offImage == null)
         || (d.width != offDimension.width)
         || (d.height != offDimension.height) ) {
      offDimension = d;
      offImage = createImage(d.width, d.height);
    }

    offGraphics = offImage.getGraphics();

    offGraphics.drawImage(backImage,0,0, this);

    /*
     * Draw the balls onto the image
     */

    for(Enumeration e = itsBalls.elements();e.hasMoreElements();) {
      aBall=(Ball)e.nextElement();
      aBall.Draw(offGraphics);
    }

    g.drawImage(offImage, 0, 0, this);
  }

  /**
   * Just call paint() so that the background color isn't painted also
   */

  public void update(Graphics g) {
    paint(g);
  }

  /**
   * Paint the image that will serve as the background.  This includes the
   * pins, "bell curve" and rack of previous balls
   * @param g The graphics context
   */

  void paintBackground(Graphics g) {
    Dimension d = getSize();
    int i,j,pinx,piny;
    double scale,x,y,oy;

    // Draw the pins

    for(i=0;i<numrows;++i) {  // Rows
      piny=i*(d.height-2*topspace)/(numrows*2)+topspace;
      scale=(double)(d.width-2*sidespace)/(double)numcolumns;

      for(j=0;j<numcolumns;++j) { // Columns
        pinx=(int)((j+(i%2)/2.0)*scale)+sidespace;
        g.drawImage(pin,pinx-pinw/2,piny-pinh/2, this);
      }
    }

    //  Draw density curve

    scale=(double)d.width/numracks;
    g.setColor(Color.black);
    oy=0;
    if (showFunc) for(i=0;i<=numracks;++i) {
      x=numracks/2;
      y=d.height*(1.0-.5*Math.exp(-(double)(i-x)*(i-x)/(2*81)));
      if(i>0) g.drawLine((int)((i-1)*scale),(int)oy,(int)(i*scale),(int)y);
      oy=y;
    }

    // Draw 'racks'

    for(i=0;i<numracks;++i) {
      for(j=0;j<rackheight[i];++j) {
        pinx=i*ballw;
        piny=d.height-(j+1)*ballh;
        g.drawImage(ball,pinx,piny, this);
      }
    }
  }

  /**
   * Update the rack by adding any additional balls to it and then
   * draw the changes to the background image
   * @param g The graphics context for the background
   */

  void updateRack(Graphics g) {
    int i,pinx,piny;  double offset;

    Dimension d = getSize();

    double sumr,sumrr;
    int num;
      sumr=0;
      sumrr=0;
      num=0;
    offset=(numracks-1.0)/2.0;
    for(i=0;i<numracks;++i) {
      while(rackdel[i]>0) {
                ++rackheight[i];
                pinx=i*ballw;
                piny=d.height-rackheight[i]*ballh;
                g.drawImage(ball,pinx,piny, this);
                --rackdel[i];
      }
      num += rackheight[i];
      sumr += rackheight[i]*(i-offset);
      sumrr += rackheight[i]*(i-offset)*(i-offset);
    }
    if(num>0){
      totalBalls=num;
      mean=sumr/num;
      if(preLab)trueMean=6.5;
      stdev=Math.sqrt(sumrr/num - mean*mean);
     // System.out.println(mean + " " + stdev);
      this.updateDataConnections();
    }

  }

  public synchronized void setShowFunc(boolean value)
  {
    if (showFunc!=value)
        {
                backImage = null;
                showFunc=value;
                repaint();
        }
  }

  public double getMean()
  {
      if(preLab) return mean+trueMean;
      else return mean;
  }

  public double getStDev()
  {
          return stdev;
  }


  /**
   * Update the positions and velocities of the balls.  Check to see if there
   * has been a collision with a pin and act accordingly
   */

  synchronized void UpdateBalls() {
    int i,j,k,pinx,piny,rack,bottomy,fr,lr,fc,lc;
    double scale;
    Ball aBall;

    Dimension d = getSize();

    bottomy=d.height;

    for(Enumeration e = itsBalls.elements();e.hasMoreElements();) {
      aBall=(Ball)e.nextElement();

      aBall.Move();

      /*
       * Check to see if the bottom has been encountered.  If so, make the
       * ball bounce a few times before coming to rest in a rack
       */

      if(aBall.y>bottomy-aBall.r) {
        if(aBall.vlen>.25) {
          aBall.vx = 0;
          aBall.vy = -aBall.vy*.25;
          aBall.y = bottomy-aBall.r;
        }
        else {
          rack=(int)(aBall.x/ballw);

          if(rack>=0 && rack<numracks) {
            ++rackdel[rack];
          }

          aBall.x=d.width/2;
          aBall.y=aBall.r;
          aBall.vx=(Math.random()-.5)/3;
          aBall.vy=0;
          // added by WC
          countBalls--;  // a new ball has been added to the board
          if(countBalls<0) itsBalls.removeElement(aBall);
        }
      }
      else {
        /*
         * Check for collisions with any of the pins
         */

        k=(int)((aBall.y-topspace)*2*numrows/(d.height-2*topspace));

        fr= k<=0 ? 0 : k-1;
        lr= k>=numrows-1 ? numrows-1 : k+1;

        for(i=fr;i<=lr;++i) {  // Rows
          piny=i*(d.height-2*topspace)/(2*numrows)+topspace;

          scale=(double)(d.width-2*sidespace)/(double)numcolumns;

          k=(int)((aBall.x-sidespace)/scale-(i%2)/2.0);

          fc= k<=0 ? 0 : k-1;
          lc= k>=numcolumns-1 ? numcolumns-1 : k+1;

          for(j=fc;j<=lc;++j) { // Columns
            pinx=(int)((j+(i%2)/2.0)*scale+sidespace);

            aBall.Boink(pinx,piny,pinr);
          }
        }
      }

      /*
       * Gravity. . .
       */

      aBall.Accelerate(0,.075);
    }
  }
    // data source methods
    public double[][] getVariables(){
        ds[0][0]=time;
        if(preLab) ds[0][1]= mean+trueMean;
          else ds[0][1]=mean;
        ds[0][2]=stdev;
        ds[0][3]=totalBalls;
        return ds;
    }
    public String[]   getVarStrings() {return varStrings;}
    // public int getID(){return this.hashCode();}   already in superclass
    public void setOwner(SApplet owner){; }
    public SApplet getOwner( ){return this;}
}





