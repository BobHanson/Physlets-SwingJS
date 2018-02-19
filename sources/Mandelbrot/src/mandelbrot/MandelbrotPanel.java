package mandelbrot;

import a2s.*;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.*;
import java.awt.event.*;
import edu.davidson.display.Format;

public class MandelbrotPanel extends Panel implements Runnable {
  Image osi=null;
  int iwidth=0;
  int iheight=0;
  int row=0;
  int maxCount=255;

  public double reMin=-2;
  public double reMax=1;
  public double imMin=-1.5;
  public double imMax=1.5;

  int mousex=0;
  int mousey=0;
  int firstx=0;
  int firsty=0;
  boolean mousedown=false;
  int boxWidth=0;
  boolean coordDisplay=true;
  private Format mouseFormat= new Format("%-+6.3g");

  Color[] color=new Color[maxCount];
  Thread thread=null;
  mandelbrot.Mandelbrot owner=null;

  public MandelbrotPanel(){
    for(int i=0; i<maxCount; i++){
       int r= 255*((i%6)/3);
       int g= 255*(( (i+2)%6)/3);
       int b= 255*(( (i+4)%6)/3);
       color[i]=new Color(r,g,b);
    }
    color[maxCount-1]=Color.black;
    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void setOwner(Mandelbrot mb){
    owner=mb;
  }

  void start(){
     iwidth = this.getSize().width;
     iheight = this.getSize().height;
     row=0;
     osi = createImage(iwidth,iheight);	 //create an off screen image
     Graphics g=osi.getGraphics();
     g.setColor(Color.black);    //clear the off-screen-image
     g.fillRect(0,0,this.getBounds().width,this.getBounds().height);
     g.dispose();
     thread=new Thread(this);
     thread.start();
  }

  void stop(){
     thread=null;
  }

  public void run(){
    while(thread!=null){
      try{
          if(row<iheight)nextRow(); else thread=null;
          paint();
          Thread.sleep(20);
      } catch (InterruptedException e){}
    }
  }

    int interation(double re,double im){
      // algorithm  Take z, square it, add the number passes as a parameter.
      int count=-1;
      double a=0;
      double b=0;
      double temp=0;
      do{
        temp=a;
        a=a*a-b*b+re;
        b=2*temp*b+im;
        count++;
      } while( a*a+b*b<4.0 && count<maxCount-1);
      return count;
  }

    void nextRow(){
     double re,im;
     int count;
     Graphics g=osi.getGraphics();
     for(int i=0; i<iwidth; i++){
       re=reFromPix(i);
       im=imFromPix(row);
       count=interation(re,im);
       g.setColor(color[count]);
       g.drawLine(i,row,i,row);
     }
     g.dispose();
     row++;
  }

  public void paint(){
     Graphics g=getGraphics();
     paint(g);
     g.dispose();
  }

  public void paint(Graphics g){
     if(osi!=null) g.drawImage(osi,0,0,this);
     if(mousedown && owner.zoomOn){
        g.setXORMode(Color.white);
        g.drawRect(firstx,firsty,mousex-firstx, mousey-firsty); //erase
        g.setPaintMode();
    }
    if(mousedown){
        paintCoords( g,  mousex, mousey);
    }
  }

  double imFromPix(int pix){
     return (imMax-pix*(imMax-imMin)/iheight);
  }

  double reFromPix(int pix){
     return reMin+pix*(reMax-reMin)/iwidth;
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
    });
  }

  double xFromPix(int x){
       return reMin+(reMax-reMin)*x/(double)iwidth;
  }

  double yFromPix(int y){
    return imMax-(imMax-imMin)*y/(double)iheight;
  }

  void paintCoords(Graphics g, int xPix,int yPix){
        if(!coordDisplay && !mousedown ) return;
        String msg="re="+mouseFormat.form(xFromPix(xPix))+ "  im="+mouseFormat.form(yFromPix(yPix));
        java.awt.Rectangle r = this.getBounds();
        g.setColor(Color.yellow);
        FontMetrics fm=g.getFontMetrics(g.getFont());
        boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
        g.fillRect(0,r.height-20,boxWidth,20);
        g.setColor(Color.black);
        g.drawString(msg,10,r.height-5);
        g.drawRect(0,r.height-20,boxWidth-1,20);
}

  void this_mousePressed(MouseEvent e) {
     mousex=e.getX();
     mousey=e.getY();
     firstx=mousex;
     firsty=mousey;
     mousedown=true;
     if(!owner.zoomOn){
        Graphics g=getGraphics();
        paintCoords(g,mousex,mousey);
        g.dispose();
        return;
     }
     if(thread==null){  // no need to draw if the thread is running
         Graphics g=getGraphics();
         g.setXORMode(Color.white);
         //g.drawRect(firstx,firsty,mousex-firstx, mousey-firsty); //erase
         g.drawRect(Math.min(firstx,mousex),Math.min(firsty,mousey),Math.abs(mousex-firstx), Math.abs(mousey-firsty) );
         g.setPaintMode();
         paintCoords(g,mousex,mousey);
         g.dispose();
     }else{
        Graphics g=getGraphics();
        paintCoords(g,mousex,mousey);
        g.dispose();
     }
  }

  void this_mouseReleased(MouseEvent e) {
     if(mousedown && owner.zoomOn){
         double remin=-2.0,remax=0.5,immin=-1,immax=1;
         remin=reFromPix(firstx);
         remax=reFromPix(mousex);
         immax=imFromPix(firsty);
         immin=imFromPix(mousey);
         owner.clone(remin,remax,immin,immax);
         owner.zoomOn=false;
     }
     mousedown=false;
     boxWidth=0;
     if(thread==null )repaint();
  }

  void this_mouseDragged(MouseEvent e) {
    if(mousedown && thread==null && owner.zoomOn ){
       Graphics g=getGraphics();
       g.setXORMode(Color.white);
       //g.drawRect(firstx,firsty,mousex-firstx, mousey-firsty); //erase
       g.drawRect(Math.min(firstx,mousex),Math.min(firsty,mousey),Math.abs(mousex-firstx), Math.abs(mousey-firsty) );
       mousex=e.getX();
       mousey=e.getY();
       g.drawRect(Math.min(firstx,mousex),Math.min(firsty,mousey),Math.abs(mousex-firstx), Math.abs(mousey-firsty) );
       g.setPaintMode();
       paintCoords(g,mousex,mousey);
       g.dispose();
    }else{
        mousex=e.getX();
        mousey=e.getY();
        Graphics g=getGraphics();
        paintCoords(g,mousex,mousey);
        g.dispose();
     }

  }


}