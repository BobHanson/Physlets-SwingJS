package superposition;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.*;
import edu.davidson.display.Format;
import edu.davidson.numerics.Parser;
import edu.davidson.tools.SApplet;


public final class SuperpositionCanvas extends Canvas
{
  private SApplet   applet;
  private Image     osiGrid = null;
  private Image     osi = null;
  private int       iwidth  = 0;
  private int       iheight = 0;
  private boolean   showCoord=false;    //display mouse coordinates
  private int       xCoord, yCoord;     // coordinates for mouse down
  private boolean   running=false;
  String    label_time="Time:";
  boolean   invalidGrid=true;
  String    caption="";
  double    time=0;
  double    pixPerX=10;
  double    pixPerY=10;
  double    gridX=1;
  double    gridY=1;
  boolean   isRunning=false;

  int[] xpix=null;
  int[] ypix=null;
  Color lineColor= new Color(0,0,0);

  Parser parser;
  String funcStr;
  boolean showTime=false;


  //Font f=new Font("Helvetica",Font.BOLD,14);
  Font f=new Font("Helvetica",Font.PLAIN,14);


  public void setRGB(int r, int g, int b)
  {
    lineColor= new Color(r,g,b);
  }

  public SuperpositionCanvas(SApplet app){
        applet=app;
          funcStr="1+4*sin(pi*x/4-pi*t)";
          funcStr="5*exp(-(x-2*t-5)*(x-2*t-5))";
          parser= new Parser(2);
          parser.defineVariable(1,"x"); // define the variable
          parser.defineVariable(2,"t"); // define the variable
          parser.define(funcStr);
          parser.parse();
      if(parser.getErrorCode() != Parser.NO_ERROR){
           System.out.println("Parse error: " + parser.getErrorString() +
                   " at function 1, position " + parser.getErrorPosition());
           System.out.println("Function: "+funcStr);
       }
       setBackground(Color.white);
       
       this.addMouseListener(new java.awt.event.MouseAdapter() {
    	      public void mousePressed(MouseEvent e) {
                startDrawCoord(e.getX(),e.getY());
                isRunning=app.clock.isRunning();
                app.clock.stopClock();
    	      }
    	      public void mouseReleased(MouseEvent e) {
    	         endDrawCoord(e.getX(),e.getY());
    	         if(isRunning) app.clock.startClock();
    	      }
    	    });
    	    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
    	      public void mouseMoved(MouseEvent e) {
    	    	//drawCoord(e.getX(),e.getY());
    	      }
    	      public void mouseDragged(MouseEvent e) {
    	    	drawCoord(e.getX(),e.getY());
    	      }
    	    });
  }

  public void update(Graphics g){
    paint(g); //update usually does a rect fill with a background color.  We don't need this.
  }

  public void setTime(double t)
  {
    time=t;
    repaint();
  }

  public void setFuncStr(String s){
    Parser parser= new Parser(2);
    parser.defineVariable(1,"x"); // define the variable
    parser.defineVariable(2,"t"); // define the variable
    parser.define(s);
    parser.parse();
    if (parser.getErrorCode() != Parser.NO_ERROR){
            System.out.println("Set function parse error: " + parser.getErrorString() +
                   " at function 1, position " + parser.getErrorPosition());
            System.out.println("function: "+s);
            return;
         }
    funcStr=s;
    this.parser=parser;
    //osi=null;
    repaint();
  }

  public void setRunning(boolean r)
  {
    running=r;
    if(!r) repaint();
  }

  public void setShowTime(boolean st)
  {
    showTime=st;
    repaint();
  }

  public void setPixPerX(double ppu)
  {
    if(pixPerX!=ppu)
    {
      pixPerX=ppu;
      invalidGrid=true;
      repaint();
    }
  }
  public void setPixPerY(double ppu)
  {
    if (pixPerY!=ppu)
    {
      pixPerY=ppu;
      invalidGrid=true;
      repaint();
    }
  }

  public void setCaption(String s)
  {
    if (!caption.equals(s))
    {
      caption=s;
      invalidGrid=true;
      repaint();
    }
  }

  public void setGridX(double gu)
  {
      if(gridX!=gu)
      {
        gridX=gu;
        invalidGrid=true;
        repaint();
      }
  }

  public void setGridY(double gu)
  {
      if(gridY!=gu)
      {
        gridY=gu;
        invalidGrid=true;
        repaint();
      }
  }

  void drawGrid(Graphics g){
        //System.out.println("Draw OSI");
        invalidGrid=false;
        if( osiGrid == null || iwidth != this.getSize().width || iheight != this.getSize().height  ){
                   iwidth = this.getSize().width;
                        iheight = this.getSize().height;
                  if(iwidth<=0 || iheight<=0) return;
            osiGrid = createImage(iwidth,iheight);   //create an off screen image
            osi = createImage(iwidth,iheight);   //create an off screen image
        }
        int xo=iwidth/2;
        int yo=iheight/2;
        if(osiGrid==null) return;
        Graphics osg = osiGrid.getGraphics();                        // a graphics context for the  off screen image
        if(osg==null) return;
        osg.setColor(getBackground());
        osg.fillRect(0,0,iwidth,iheight);
        osg.setColor(g.getColor());
        osg.clipRect(0,0,iwidth,iheight);
        int ptX,ptY,i,n;
        osg.setColor(Color.lightGray);
        int gridSpace=(int)(pixPerX*gridX);
        if (gridSpace>0.5)for(i=xo % gridSpace; i<iwidth; i=i+gridSpace)
        {
           osg.drawLine(i,0,i,iheight);
        }
        gridSpace=(int)(pixPerY*gridY);
        if (gridSpace>0.5)for(i=yo % gridSpace; i<iheight; i=i+gridSpace)
        {
           osg.drawLine(0,i,iwidth,i);
        }
        osg.setFont(f);
        osg.setColor(Color.black);
        FontMetrics fm=osg.getFontMetrics(f);
        osg.drawString(caption,(iwidth-fm.stringWidth(caption))/2, 15);
        osg.dispose();
  }

  void drawOSI(){
       double x;
       if (xpix==null || ypix==null || this.getBounds().width!=iwidth){
          iwidth=this.getBounds().width;
          xpix=new int[iwidth];
          ypix=new int[iwidth];
       }
       int xo=iwidth/2; int yo=iheight/2;
       int i=0;   // counter for loop.
       try
       {
           for(i=0; i<iwidth; i++)
           {
                   x=(i-xo)/pixPerX;
                   xpix[i]=i;
                   ypix[i]=(int)Math.round(yo-parser.evaluate(x,time)*pixPerY);
           }
       }catch(Exception e){System.out.println("Failed to draw function.");}
       Graphics g=osi.getGraphics();
       if(g==null) return;
       g.drawImage(osiGrid,0,0,this);    // draw the grid onto the visible graph
       g.setColor(lineColor);
       g.drawPolyline(xpix,ypix,iwidth);
       g.setColor(Color.black);
       if (showTime){
                   String tStr= new Format("%7.4g").form(time);
                   g.setFont(f);
                   g.drawString(label_time+" " + tStr,15, 15);
       }
       //not needed for JS implementation
       if(showCoord && running) {
                    Format format= new Format("%-+6.3g");
                    String xStr=format.form(pixToX(xCoord));
                    String yStr=format.form(pixToY(yCoord));
                    g.setFont(f);
                    g.setColor(Color.black);
                    g.drawString("X: " + xStr+"  F: " + yStr, 10, iheight-15);
                    g.drawLine(xCoord-10,yCoord,xCoord+10,yCoord);
                    g.drawLine(xCoord,yCoord-10,xCoord,yCoord+10);
        }
        g.dispose();
  }

  public void paint(Graphics g){
       if(applet.destroyed==true) return;
       osi=null;
       osiGrid=null;
       try{
       if (getSize().width==0||getSize().height==0)return;
       if( invalidGrid || osiGrid == null || osi== null || iwidth != this.getSize().width || iheight != this.getSize().height){
           drawGrid(g);
       }
       drawOSI();
       g.drawImage(osi,0,0,this);    // draw the image onto the visible graph
       } catch(Exception ex){
         osiGrid = null;
         osi= null;
       }
   }

   private double pixToX(int x)
   {
     int xo=iwidth/2;
     return (x-xo)/(1.0*pixPerX);
   }
   private double pixToY(int y)
   {
     int yo=iheight/2;
     return -(y-yo)/(1.0*pixPerY);
   }
   public void startDrawCoord(int x,int y)
    {
        xCoord=x; yCoord=y;
        if(!running){
            Graphics g=getGraphics();
            if(g==null) return;
            //if (osi!=null) g.drawImage(osi,0,0,this);
            paint(g);// draw the image onto the visible graph
            Format format= new Format("%-+6.3g");
            String xStr=format.form(pixToX(xCoord));
            String yStr=format.form(pixToY(yCoord));
            g.setFont(f);
            g.drawString("X: " + xStr+"  F: " + yStr, 10, iheight-15);
            g.drawLine(xCoord-10,yCoord,xCoord+10,yCoord);
            g.drawLine(xCoord,yCoord-10,xCoord,yCoord+10);
            g.dispose();
        }
        showCoord=true;
    }
    public void endDrawCoord(int x,int y)
    {
        showCoord=false;
        if(!running){
            Graphics g=getGraphics();
            if(g==null) return;
            paint(g);
            g.dispose();
        }
    }
    
    public void drawCoord(int x,int y)
    {
        xCoord=x; yCoord=y;
        if(!running){
            Graphics g=getGraphics();
            if(g==null) return;
            //if (osi!=null) g.drawImage(osi,0,0,this);
            paint(g);// draw the image onto the visible graph
            Format format= new Format("%-+6.3g");
            String xStr=format.form(pixToX(xCoord));
            String yStr=format.form(pixToY(yCoord));
            g.setFont(f);
            g.drawString("X: " + xStr+"  F: " + yStr, 10, iheight-15);
            g.drawLine(xCoord-10,yCoord,xCoord+10,yCoord);
            g.drawLine(xCoord,yCoord-10,xCoord,yCoord+10);
            g.dispose();
        }
    }
   /**  use MouseAdapter and MouseMotionAdapter rather than Applet events
    public boolean mouseDown(Event evt, int x, int y)
        {
            if(((evt.modifiers & Event.META_MASK)!=0)||
                 ((evt.modifiers & Event.ALT_MASK)!=0)) return false;
                startDrawCoord(x,y);
                return true;
        }

        public boolean mouseUp(Event evt, int x, int y)
        {
            if(((evt.modifiers & Event.META_MASK)!=0)||
                 ((evt.modifiers & Event.ALT_MASK)!=0)) return false;
                endDrawCoord(x,y);
                return true;
        }

        public boolean mouseDrag(Event evt, int x, int y)
        {
            if(((evt.modifiers & Event.META_MASK)!=0)||
                 ((evt.modifiers & Event.ALT_MASK)!=0)) return false;
                drawCoord(x,y);
                return true;
        }
     **/
}
