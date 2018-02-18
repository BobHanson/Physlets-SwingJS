package minkowski;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

//import java.awt.*;
import a2s.Canvas;
import edu.davidson.display.Format;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SDataSource;



/*
 *
 * MinkowskiCanvas
 *
 */

public final class MinkowskiCanvas extends Canvas{
  Image     osi = null;
  int iwidth=0, iheight=0;
	double v;
	Vector labVec=new Vector();
  Vector movVec=new Vector();
	private Transformation tr= new Transformation('L');
  private Color primeColor=new Color(0,128,0);
  private int     boxWidth=0;
  Format    format= new Format("%-+6.2f");
  boolean coordDisplay=true;
  boolean mouseDown=false;
  SApplet owner=null;
  int pixPerUnit=20;
  int radius=4;

  public MinkowskiCanvas() {
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

	public MinkowskiCanvas(SApplet o) // constructor for Canvas
 {
    this();
    owner=o;
		setBackground(Color.white);
		v=0.5;
	}
	public final void setType(char t_){
	  tr.setType(t_);
	  clearLabCoords();
	  clearMovCoords();
    }


	public void setSpeed(double v_){
		v=v_;  // the speed is negative
		if (v<-0.99)	v=-0.99;
		if (v>0.99)	v=0.99;
		repaint();
	}

  public double getSpeed(){return v;}

	public Dimension preferredSize(){
		return new Dimension(200,200);
	}

  public void update(Graphics g){
    paint(g);
	}

	public void paint(Graphics g){
    if( osi == null || iwidth != getSize().width ||iheight != getSize().height){
          iwidth = this.getSize().width;
          iheight = this.getSize().height;
          osi = createImage(iwidth,iheight);	 //create an off screen image
    }
    if(osi==null) return;
    Graphics osg=osi.getGraphics();
    paintOSI(osg);
    osg.dispose();
    g.drawImage(osi,0,0,this);
  }
	public void paintOSI(Graphics g){
    g.setColor(Color.white);
    g.fillRect(0,0,iwidth,iheight);
    DPoint p;
		double x,t;
		paintXGrid(g);
		paintXPGrid(g);
		paintX(g);
    paintT(g);
		g.setColor(Color.red);
		for (int i=0;i<labVec.size();i++)
		{
			p=(DPoint)labVec.elementAt(i);
			paintPoint(g,p.x,p.y);
		}
		g.setColor(primeColor);
		for (int i=0;i<movVec.size();i++)
		{
			p=(DPoint)movVec.elementAt(i);
			x=tr.transformH(p.x,p.y,-v);  // convert from the moving frame to the lab frame.
			t=tr.transformV(p.x,p.y,-v);
      //p.xprime=x;
      //p.yprime=t;
			paintPoint(g,x,t);
		}
		g.setColor(Color.black);
    g.drawRect(0,0,iwidth-1,iheight-1);
	}

	public final void paintXPGrid(Graphics g)
	{
		int w=getSize().width; int h=getSize().height;
		int xo=w/2;
		int to=h/2;
		int x1=(w/2) % pixPerUnit;
		int t1=(h/2) % pixPerUnit;
		int x2,t2;
		int xx1,tt1,xx2,tt2,xx3,tt3;
//		double gamma=1.0/Math.sqrt(1-v*v);
		Color color1=new Color(128,128,255);
		Color color2=new Color(0,0,128);
		g.setColor(color1);
		x2=x1+pixPerUnit; t2=t1+pixPerUnit;
		while (x1<w)
		{
			t1=(h/2) % pixPerUnit;
			t2=t1+pixPerUnit;
			while(t1<h)
			{	 //map (x1,t1)
				xx1=xo+(int)tr.transformH(x1-xo,t1-to,v);// (((x1-xo)+v*(t1-to))*gamma);
				tt1=to+(int)tr.transformV(x1-xo,t1-to,v);//(((t1-to)+(x1-xo)*v)*gamma);
				//map (x1,t2)
				xx2=xo+(int)tr.transformH(x1-xo,t2-to,v);//(((x1-xo)+v*(t2-to))*gamma);
				tt2=to+(int)tr.transformV(x1-xo,t2-to,v);//(((t2-to)+(x1-xo)*v)*gamma);
				 //map (x2,t1)
				xx3=xo+(int)tr.transformH(x2-xo,t1-to,v);//(((x2-xo)+v*(t1-to))*gamma);
				tt3=to+(int)tr.transformV(x2-xo,t1-to,v);//(((t1-to)+(x2-xo)*v)*gamma);

				if(x1==xo)g.setColor(color2); else g.setColor(color1);
				g.drawLine(xx1,tt1,xx2,tt2);
				if(t1==to)g.setColor(color2); else g.setColor(color1);
				g.drawLine(xx1,tt1,xx3,tt3);

				t2=t1;
				t1=t1+pixPerUnit;
			}
			x2=x1;
			x1=x1+pixPerUnit;
		}

		g.setColor(Color.black);


	}

	public final void paintXGrid(Graphics g)
	{
		int w=getSize().width; int h=getSize().height;
		int x=(w/2) % pixPerUnit;
		int t=(h/2) % pixPerUnit;
		Color color=Color.white.darker();
		g.setColor(color);
		while (x<w)
		{
			g.drawLine(x,0,x,h);
			x=x+pixPerUnit;
		}
		while(t<h)
		{
			g.drawLine(0,t,w,t);
			t=t+pixPerUnit;
		}
		g.setColor(Color.black);


	}

	public final void paintX(Graphics g){
    // paint the x axis
		int w=getSize().width; int h=getSize().height;
		int x=(w/2) % pixPerUnit;
		g.drawLine(0,h/2,w,h/2);
		while (x<w)
		{
			g.drawLine(x,h/2-5,x,h/2+5);
			x=x+pixPerUnit;
		}

	}
	public final void paintT(Graphics g){
   // paint the time axis
		int w=getSize().width; int h=getSize().height;
		int t=(h/2) % pixPerUnit;
		g.drawLine(w/2,0,w/2,h);
		while (t<h)
		{
			g.drawLine(w/2-5,t,w/2+5,t);
			t=t+pixPerUnit;
		}
	}
	public void paintPoint(Graphics g,double x_, double t_)
	{
    int xo=getSize().width /2;
		int to=getSize().height/2;
		g.fillOval((int)(xo+x_*pixPerUnit-radius),(int)(to-t_*pixPerUnit-radius),1+2*radius,1+2*radius);
	}

	synchronized public final int addLabPoint(double x_, double t_){
		Graphics g=this.getGraphics();
    DPoint pt= new DPoint(x_,t_, owner);
		labVec.addElement(pt);
    //double x=tr.transformH(x_,t_,-v);
		//double t=tr.transformV(x_,t_,-v);
    //pt.xprime=x;
    //pt.yprime=t;
		g.setColor(Color.red);
		paintPoint(g,x_,t_);
		g.setColor(Color.black);
		g.dispose();
    return pt.getID();
	}
	synchronized public final int addMovPoint(double x_, double t_){
		Graphics g=this.getGraphics();
    DPoint pt= new DPoint(x_,t_, owner);
		movVec.addElement(pt);
		g.setColor(primeColor);
  	double x=tr.transformH(x_,t_,-v);  // convert to the lab frame in order to plot
		double t=tr.transformV(x_,t_,-v);
    pt.xprime=x;
    pt.yprime=t;
		paintPoint(g,x,t);
		g.setColor(Color.black);
		g.dispose();
    return pt.getID();
	}

	synchronized public final void clearLabCoords(){
    for( Enumeration e=labVec.elements(); e.hasMoreElements();){
        DPoint pt= (DPoint) e.nextElement();
        owner.removeDataSource(pt.getID());
    }
    labVec.removeAllElements();
		repaint();
	}

 	synchronized public final void clearMovCoords(){
    for( Enumeration e=movVec.elements(); e.hasMoreElements();){
        DPoint pt= (DPoint) e.nextElement();
        owner.removeDataSource(pt.getID());
    }
    movVec.removeAllElements();
		repaint();
	}

  void paintCoords(int xPix,int yPix){
      if(!coordDisplay) return;
      Graphics g=getGraphics();
      paintCoords( g,xPix,yPix);
      g.dispose();
  }

  void paintCoords(Graphics g, int xPix,int yPix){
    if(!coordDisplay) return;
    double x=xFromPix(xPix);
    double y=yFromPix(yPix);
    String msg;

    if(tr.getType()=='R'){
        msg="x="+format.form(x)+ "  y="+format.form(y);
        msg=msg+"  x'="+format.form(tr.transformH(x,y,v))+ "  y'="+format.form(tr.transformV(x,y,v));
    }else{
        msg="x="+format.form(x)+ "  t="+format.form(y);
        msg=msg+"  x'="+format.form(tr.transformH(x,y,v))+ "  t'="+format.form(tr.transformV(x,y,v));
    }
    Rectangle r = getBounds();
    g.setColor(Color.yellow);
    FontMetrics fm=g.getFontMetrics(g.getFont());
    boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
    g.fillRect(1,r.height-20,boxWidth,20);
    g.setColor(Color.black);
    g.drawString(msg,10,r.height-5);
    g.drawRect(1,r.height-20,boxWidth-1,20-1);
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

    void this_mousePressed(MouseEvent e) {
      int mouseX=e.getX();
      int mouseY=e.getY();
      mouseDown=true;
      paintCoords(mouseX,mouseY);
    }

    void this_mouseReleased(MouseEvent e) {
      mouseDown=false;
      Rectangle r = getBounds();
      repaint(1,r.height-20,boxWidth,20);
      boxWidth=0;
    }

    void this_mouseDragged(MouseEvent e) {
      int mouseX=e.getX();
      int mouseY=e.getY();
      double x;
      double y;
      int maxPix=iwidth;
      int minPix=0;
      if(mouseX<minPix) mouseX=minPix;
      else if(mouseX>maxPix-2) mouseX=maxPix-2;
      x=xFromPix(mouseX);
      minPix=0;
      maxPix=iheight;
      if(mouseY<minPix) mouseY=minPix;
      else if(mouseY>maxPix-2) mouseY=maxPix-2;
      y=yFromPix(mouseY);
      paintCoords(mouseX,mouseY);
    }

    public double xFromPix(int xpix){
        return (xpix-iwidth/2)/(double)pixPerUnit;
    }

    public double yFromPix(int ypix){
        return -(ypix-iheight/2)/(double)pixPerUnit;
    }

    // inner class to store coordinates
 class DPoint extends Object implements SDataSource {
	double x,y;
 	double xprime,yprime;
  String[] varStrings= new String[]{"x","t","xprime","tprime","v"};
  double[][] ds=new double[1][5];  // the datasource state variables t,x,y,vx,vy,ax,ay;

	DPoint(double x_, double y_, SApplet o){
		x=x_;
		y=y_;
    owner=o;
    // try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
	}
  public void setOwner(SApplet owner){;}
  public SApplet getOwner(){return owner;}
  public String[] getVarStrings(){return varStrings;}
  public int getID(){return this.hashCode();}

  public double[][] getVariables(){
     ds[0][0]=x;  //t
     ds[0][1]=y;  //x
     ds[0][2]=xprime;  //y
     ds[0][3]=yprime;  //vx
     ds[0][4]=v;  //vy
     return ds;
  }
}

}



