package hydrogenic;
//import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import edu.davidson.display.Format;

import a2s.*;

public final class DensityCanvas extends Canvas implements Runnable{
  Image img=null;
  static String calcString = "Please Wait...";
  private Thread calcThread;
  int red[]=new int[256];
  int green[]=new int[256];
  int blue[]=new int[256];
  int m=0;
  int n=0;
  int l=0;
  int ao;
  double [] magx= null;
  double [] modRnl= null;
  double [] modYlm= null;
  double pi=Math.PI;
  double ylm;
  double rnl;
  boolean showPhaseColor;
  double [] amp= new double[2];
  double leg, datamaxR, datamaxY, brightness;
  int originx,originy, theta;
  int radius;
  double rx,ry, val;
  int npr=0;
  int npa=181;
  Format  format=new Format("%-+6.3g");
  int boxWidth=0;

  public DensityCanvas() {makePallet();
        try{
            jbInit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        }

  public void update(Graphics g){
    paint(g);
  }

  /**
  * This method overrides the AWT paint method and paints an image
  * This method is called by the run() method
  */
  public void paint(Graphics g){
      int w = getSize().width;
      int h = getSize().height;
      int max=Math.max(w,h);
      int min=Math.min(w,h);
      int position=(max-min)/2;

      if (img == null) {
          super.paint(g);
          g.setColor(Color.black);
          FontMetrics fm = g.getFontMetrics();
          int x = (w - fm.stringWidth(calcString))/2;
          int y = h/2;
          g.drawString(calcString, x, y);
      }
      else {
          g.setColor(Color.black);
          g.fillRect(0,0,w,h);
          w=Math.min(w,h);
          h=Math.min(w,h); //the next line of code centers the image on the canvas
          if (getSize().width<getSize().height) g.drawImage(img, 0, position, w, h, this);
          else g.drawImage(img, position, 0, w, h, this);
      }
    }

  /**
  * This method ensures that a thread is completely terminated
  */
  synchronized public void stop() {
        if(calcThread==null) return;
        calcThread.stop();
        try{ calcThread.join();} catch (InterruptedException e){}
        calcThread=null;
    }

  /**
  * This method starts a new thread making sure the old one is dead first
  */
  public void start(){
  if (calcThread==null){calcThread=new Thread(this);}
  calcThread.start();
  }

  /**
  * This method carries out the calculations of the wave functions using a thread
  * This method calls the calcRnl,calcYlm, colorfunction, repaint, and stop methods
  * The angular and radial wave function arrays are calculated first and then used
  * as a look up table for the colorfunction method that determines how the image looks
  */
  public void run() {
        int width = getSize().width;
        int height = getSize().height;
        int pixels[] = new int[width * height];
        int c[] = new int[4];
        int index = 0;
        originx=width/2;
        originy=height/2;
        npr=(int)(Math.sqrt((originx+1)*(originx+1)+(originy+1)*(originy+1)))+1;
        modRnl = new double[npr];
        modYlm = new double[npa];
        magx = new double[npr];

        //does the physics
        calcRnl(modRnl);   //calculates radial probability
        calcYlm(modYlm);  //calculates angular probability

        //this for loop paints the canvas by calling the ColorFunction method
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                c[0] = c[1] = c[2] = 0;
                c[3] = 255;
                colorFunction (c, i, j, width, height, modRnl, modYlm);
                pixels[index++] = ((c[3] << 24) |
                                   (c[0] << 16) |
                                   (c[1] << 8) |
                                   (c[2] << 0));

            }
        }

        newImage(width, height, pixels);
        repaint();
        stop();  // make sure the thread is dead and set to null before we go on.
    }

    synchronized void newImage(int width, int height,int pixels[]) {
        img = createImage(new MemoryImageSource(width, height,
                                                ColorModel.getRGBdefault(),
                                                pixels, 0, width));
    }

   /**
  * This method calculates the angular probabilities and returns them in array form
  * It calculates angular wave function from 0 to 180 degrees.  It takes an array for input.
  */
   public double[] calcYlm(double modYlm[]){
      int i;
      double [] temp= new double[npa];
      double normY=Math.sqrt((((2*l)+1)*SpecialFunctions.factorial(l-m))/(SpecialFunctions.factorial(l+m)*4.0*pi));
      datamaxY=0;

      if(l==0&&m==0){ //Pre-programmed S-States for efficiency
        for(i=0; i<npa; i++){
          modYlm[i]=Math.sqrt(1/(4.0*pi));
        }
          datamaxY=Math.sqrt(1/(4.0*pi));
      }
      else for(i=0; i<npa; i++){
            leg = SpecialFunctions.legendre(l,m,Math.cos((pi*i)/(180.0)));
            temp[i]=leg;
            modYlm[i]=normY*leg; //Our Angular Probablilty array
            if(datamaxY<Math.abs(modYlm[i])) datamaxY=Math.abs(modYlm[i]);
           }

      return modYlm;
   }

   /**
   * This method calculates the radial wave function as well as a brightness factor
   * to be used later in the colorfunction method to ensure picture is clear
   * The method inputs and returns an array of the radial wave function amplitude
   */
   public double[] calcRnl(double modRnl[]){
      int i;
      double x=0;
      double ao=4*n*n+2; //The distance in Bohr radii from nucleus over which we calculate probability
      double dx=ao/npr;  //The steps over which we calculate the probability
      double f1=SpecialFunctions.factorial(n-l-1);
      double f2=SpecialFunctions.factorial(n+l);
      double normR=(2.0/(n*n))*Math.sqrt(f1/f2);//Normilazation constant for radial wave equation
      datamaxR=0;
      brightness=0;

      //calculates radial probability
      if(n==1&&l==0){
        for(i=0; i<npr; i++){
          rnl=2.0*Math.exp(-x);
          modRnl[i]=rnl;
          brightness=(brightness+Math.abs(rnl*i));
          magx[i]=x;
          x=x+dx;
          if(datamaxR<Math.abs(modRnl[i])) datamaxR=Math.abs(modRnl[i]);
        }
      }
     else{ for(i=0; i<npr; i++){
            rnl =normR*Math.pow((2.0*x)/n,l)*Math.exp(-x/n)*SpecialFunctions.laguerre(2*l+1,n-l-1,(2.0*x)/n);
            modRnl[i]=rnl;
            brightness=(brightness+Math.abs(rnl*i));
            magx[i]=x;
            x=x+dx;
            if(datamaxR<Math.abs(modRnl[i])) datamaxR=Math.abs(modRnl[i]);
            }

          }
    brightness=brightness/7000.0/datamaxR;
    return modRnl;
}


    /**
    * This method calculates and returns the value of the wave function and how far out from the
    * nucleus the user is when they press and hold the left mouse button.
    * Amplitude(i,j,magx[]modRnl[],modYlm[]) is called by the drawCoords() method
    */
    public double[] amplitude(int i, int j,double magx[], double modRnl[], double modYlm[]){
      //when the user right clicks on the canvas this method is called..it returns distance from origin in bohr radii
       double radii=0;
       double amplitude;
       getWaveFunction(i,j);
       radii=magx[radius];
       amplitude=(modRnl[radius]*modYlm[theta]);
       if((m&1)==1)
            amplitude=-amplitude;
       if (m<0) amplitude=-amplitude;
       if((i-originx)<0&&(m&1)==1)
           amplitude=-amplitude;
       amp[0]=radii;
       amp[1]=amplitude;

       return  amp;
    }
    /*
     * This method returns the amplitude and phase of the wave function for the i,j pixel location
    */
    double[] getWaveFunction(int i, int j){
        rx=(i-originx);
        ry=(j-originy);
        radius=(int)Math.round(Math.sqrt(rx*rx+ry*ry));
        theta=(int)((pi/2-Math.atan2(originy-j,Math.abs(rx)))*(180.0/pi));
        val=255.0*((modRnl[radius]/datamaxR)*(modYlm[theta]/datamaxY));
        if((m&1)==1)
            val=-val;
        if (m<0) val=-val;

        amp[0]=val;
        amp[1]=0;
        return amp;
    }

    /**
    * This method uses the precalculated wave function arrays as a look-up table and
    * scales them to an integer value between 0-255 for the color scale.
    * The method is called by the run() method and is part of the process of painting
    * the canvas. 
    */
    void colorFunction(int c[], int i, int j, int width, int height, double modRnl[], double modYlm[]){
       double val;
       boolean sign;
       amp=getWaveFunction(i,j);
       val=amp[0];
       if((i-originx)<0&&(m&1)==1)
           val=-val;
       if(val<0) sign=true; else sign=false;
       val=(255*(1-Math.exp(-Math.abs(val)/(brightness*255.0))));
       int index=(int)Math.round(val);
       index=Math.min(index,255);
    if (!showPhaseColor) {
        c[0]=index;
        c[1]=index;
        c[2]=index;
    }else{
      if (sign){
        c[0]=0;
        c[1]=green[index];
        c[2]=blue[index];
      }else {
        c[0]=red[index];
        c[1]=green[index];
        c[2]=0;
        }
    }
  }

   void makePallet(){
      for(int i=0; i<256; i++){
      red[i]=i;
      blue[i]=i;
      green[i]=i;
      }
   }

   void setM(int m){ this.m=m;}
   void setN(int n){ this.n=n;} //gets the n,l, and m values from density class
   void setL(int l){ this.l=l;}
   void setPhase(boolean phase) {this.showPhaseColor=phase;}

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

    double xFromPix(int xpix){return xpix;}
    double yFromPix(int ypix){return ypix;}

    /**
    * This method is called by the mousePressed and mouseDragged events/methods.
    * It calls the amplitude method which returns values for amplitude and bohr radii
    * at the point where the user clicked and paints those values in a yellow box
    * on the graph
    */
    public void drawCoords(int xPix,int yPix){
        amplitude(xPix, yPix, magx, modRnl, modYlm);
        String msg="Bohr radii:" +format.form(amp[0])+" Amplitude:"+format.form(amp[1]);
        Graphics g=getGraphics();
        Rectangle r = getBounds();
        g.setColor(Color.yellow);
        FontMetrics fm=g.getFontMetrics(g.getFont());
        boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
        g.fillRect(0,r.height-20,boxWidth,20);
        g.setColor(Color.black);
        g.drawString(msg,10,r.height-5);
        g.dispose();
    }

    /**
    * This method calls the drawCoords(xPix,Ypix) method on action event (user left clicks)
    * Method also clones/creates new frame/window/container with current image on it
    */
    void this_mousePressed(MouseEvent e) {
        if(( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){
           if(img==null) return;
           DensityFrame modeFrame=new DensityFrame(img);
           modeFrame.setTitle("Psi (n, l, m) = Psi ("+n+", "+l+", "+m+")");
           modeFrame.show();
        } else{
            int xPix=e.getX();
            int yPix=e.getY();
            drawCoords(xPix,yPix);
        }
    }

    /**
    * This method calls the drawCoords(xPix,Ypix) method on action event (user left clicks)
    */
    void this_mouseDragged(MouseEvent e) {
        if(( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){ return;}
        int xPix=e.getX();
        int yPix=e.getY();
        drawCoords(xPix,yPix);
    }

    void this_mouseReleased(MouseEvent e) {
        if(( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){ return;}
        Rectangle r = getBounds();
        repaint(0,r.height-20,boxWidth,20);
        boxWidth=0;  // reset the yellow message box.
    }

    void this_mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    void this_mouseExited(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

  public static void main(String[] args) {
    new DensityCanvas();
  }
}