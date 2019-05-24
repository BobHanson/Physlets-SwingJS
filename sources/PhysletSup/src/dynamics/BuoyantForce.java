package dynamics;
import java.applet.Applet;
// Adapted for Physlet Physics 
import java.awt.*;
//import edu.davidson.tools.SApplet;

import javax.swing.Timer;

import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class BuoyantForce extends Applet
{
    int yOffset=0;
    boolean running;
    Dimension area;
    Dimension offDimension;
    Image offImage;
    Image blockImage;
    Graphics g;
    Graphics gb;
    int yc;
    int x0;
    int y0;
    int w;
    int h;
    double rho;
    Color bgColor;
    Color fgColor;
    BuoyantBlock block;
    String rts;
    int yb;
    //Thread animThread;
    long startTime;
    long lastTime;
    long delay;
    long delta;
    int size;
    int size2;
    int count;
    int prevyb;
    int sign;
    boolean dragging;
    boolean oscillating;
    private int d;
    private int x1;
    private int y1;
    private int xa;
    private int xx;
    private int yy;
    private int xb;
    private int arw;
    Timer timer;
    int sleepTime=10;
    
    public BuoyantForce() {
        this.yOffset = 0;
        this.running = false;
        this.x0 = 25;
        this.y0 = 10;
        this.w = 100;
        this.h = 100;
        this.rho = 0.4;
        this.bgColor = Color.white;
        this.fgColor = Color.lightGray;
        this.block = new BuoyantBlock();
        this.startTime = 0L;
        this.delay = 50L;
        this.size = 2;
        this.size2 = 2 * this.size;
        this.count = 100;
        this.sign = 1;
        this.dragging = false;
        this.oscillating = false;
    }
    
    public void init() {
    	this.resize(400, 400);
        //this.setBackground(this.bgColor);
        //this.area = this.size();
        this.area = new Dimension(400,400);  // for debugging
        final Dimension area = this.area;
        area.height -= this.yOffset;
        this.offDimension = this.area;
        this.offImage = this.createImage(this.area.width, this.area.height);
        this.g = this.offImage.getGraphics();
        this.blockImage = this.createImage(this.w, this.h);
        this.gb = this.blockImage.getGraphics();
        this.yc = (int)(this.area.height * 0.6);
        this.reset(true);
    }
    
    public void setDensity(double density) {
  	  stop();
    	if(this.rho!=density) {
    	  this.rho=density;
    	}
    }
    
    public void reset(final boolean b) {
        this.yb = this.y0;
        this.clear();
        if (b) {
            this.running = false;
        }
    }
    
    String d2String(final double n) {
        String s = String.valueOf((float)((int)(n * 100.0) / 100.0));
        if (s.indexOf(".") == -1) {
            s += ".0";
        }
        return s;
    }
    
    private final static int LOOP0 = 0;
    private final static int LOOP1 = 1;
    private final static int LOOP2 = 2;
    private final static int STATE_STOP = 3;
    private int counter0=0;
    private int counter2=0;
    		
    
    
    private int state = LOOP0;
    
    public void start() {
    	state = LOOP0;
    	runTimer();
    }
    
    public void stop() {
        timer.stop();
        this.running = false;
    }
    
     void runTimer() {
        this.gb.setColor(Color.white);
        this.gb.drawRect(0, 0, this.w, this.h-1);
		switch (state) {
		case LOOP0:
			advance0(counter0);
			counter0++;
			if(counter0>=this.d)state=LOOP1;
			break;
		case LOOP1:
			advance1();
            ++this.yb;
			if(this.yb >= this.yc + this.d - this.w) {
				counter2=this.yc + this.w - this.d;
				state=LOOP2;
			}
			break;
		case LOOP2:
			advance2(counter2);
            counter2--;
			if(counter2 <= this.yc)state=STATE_STOP;
			break;
		case STATE_STOP:
			this.running = false;
			return;
		}
		timer = new Timer(sleepTime, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runTimer();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	public void advance0(int i) {
        final double n = 1.0 / this.rho;
        final int n2 = this.x1 + this.w - 1;
            this.gb.setColor(this.bgColor);
            final Graphics gb = this.gb;
            final int n3 = 1;
            final int n4 = this.h - this.d + i;
            gb.drawLine(n3, n4, n2, n4);
            this.gb.setColor(this.fgColor);
            final Graphics gb2 = this.gb;
            final int n5 = 1;
            final int n6 = (int)(i * n);
            gb2.drawLine(n5, n6, n2, n6);
            this.gb.setColor(Color.black);
            this.gb.drawRect(1, 0, this.w - 1, this.h - 1);
            this.gb.setColor(Color.red);
            this.gb.drawLine(this.xx = this.w / 2, this.yy = this.h - this.d, this.xx, this.yy += this.d);
            this.gb.drawLine(this.xx, this.yy, this.xx - this.arw, this.yy - this.arw);
            this.gb.drawLine(this.xx, this.yy, this.xx + this.arw, this.yy - this.arw);
            this.gb.setColor(Color.black);
            this.gb.drawRect(1, 0, this.w - 1, this.h - 1);
            this.repaint();	
	}
	
	public void advance1() {
            final int n7 = this.yb + this.h - this.yc;
            if (n7 > 0) {
                this.g.setColor(this.fgColor);
                this.g.drawLine(this.x0 + 1, this.yy = this.yc + this.h - n7, this.xa - 1, this.yy);
                this.g.setColor(Color.green);
                this.xx = this.x1 + this.w / 2;
                this.yy = this.yc + n7;
                if (n7 > 1) {
                    this.g.drawLine(this.xb + 1, this.yy, this.xb + n7 - 1, this.yy);
                }
            }
            this.gb.setColor(Color.black);
            this.gb.drawRect(1, 0, this.w - 1, this.h - 1);
            this.repaint();
	}
	
	public void advance2(int j) {
            this.g.setColor(this.bgColor);
            this.g.drawLine(this.x0, this.yy = j + this.d, this.x0 + this.w - 1, this.yy--);
            this.g.setColor(this.fgColor);
            this.g.drawLine(this.x0 + 1, j, this.x0 + this.w - 1, j);
            this.g.setColor(Color.black);
            this.g.drawLine(this.x0, this.yy, this.x0 + this.w, this.yy);
            this.g.drawLine(this.x0, this.yy = j + this.d - this.w, this.x0, this.yy);
            this.g.drawLine(this.xx = this.x0 + this.w, this.yy, this.xx, this.yy);
            this.gb.setColor(Color.black);
            this.gb.drawRect(1, 0, this.w - 1, this.h - 1);
            this.repaint();
        	
	}
    
    
    void clear() {
        if (this.g != null) {
            this.g.clearRect(0, 0, this.area.width, this.area.height);
            this.g.setColor(this.fgColor);
            this.d = (int)(this.rho * this.h);
            this.g.fillRect(this.xa = this.x0 + this.w, this.yc, this.w + 2 * this.x0, this.yc);
            this.g.fillRect(this.x0, this.y1 = this.y0 + this.h - this.d, this.w, this.d);
            this.g.setColor(Color.red);
            this.g.drawLine(this.xx = this.x0 + this.w / 2, this.yy = this.y1, this.xx, this.yy += this.d);
            this.arw = (int)(this.rho * 10.0);
            this.g.drawLine(this.xx, this.yy, this.xx - this.arw, this.yy - this.arw);
            this.g.drawLine(this.xx, this.yy, this.xx + this.arw, this.yy - this.arw);
            this.x1 = 2 * this.x0 + this.w;
            this.gb.clearRect(0, 0, this.w, this.h);
            this.gb.setColor(this.fgColor);
            this.gb.fillRect(0, this.h - this.d, this.w, this.d);
            this.g.setColor(Color.black);
            this.g.drawString("density", 5, this.yy = this.y0 + this.w + 15);
            this.g.drawString("0.0", 5, this.yy - 15);
            this.g.drawString("1.0", 5, 15);
            this.g.drawRect(0, 0, this.area.width - 1, this.area.height - 1);
            this.g.drawLine(this.xa, this.yc, this.xa, this.area.height);
            this.g.drawLine(this.xx = this.xa + this.w + 2 * this.x0, this.yc - this.h, this.xx, this.area.height);
            this.g.drawRect(this.x0, this.y0, this.w, this.h);
            this.g.drawLine(this.xa - this.w, this.yc, this.xa - this.w, this.yc + this.h);
            this.g.drawLine(this.xa - this.w, this.yc + this.h, this.xa, this.yc + this.h);
            this.g.setColor(Color.blue);
            this.g.drawLine(this.xb = this.xx + 5, this.yc, this.area.width - 1, this.yc);
            this.g.drawLine(this.xb, this.yc, this.xb, this.area.height - 1);
            this.g.drawString("P", this.area.width - 15, this.yc - 5);
            this.g.drawString("Y", this.xb + 5, this.area.height - 5);
            this.g.drawLine(this.xb, this.yc, this.area.width - 1, this.yc + this.area.width - 1 - this.xb);
            this.gb.setColor(Color.black);
            this.gb.drawRect(0, 0, this.w - 1, this.h - 1);
        }
    }
    
    void redraw() {
        this.clear();
        this.repaint();
    }
    
    protected void paintComponent_(Graphics g) {
    	//super.paintComponent_(g);
    	updateScreen(g);
    }
    
    public void updateScreen(Graphics graphics) {
        this.g.drawImage(this.blockImage, this.x1, this.yb, this);
        this.g.setColor(this.bgColor);
        this.g.drawLine(this.x1, this.yb - 1, this.x1 + this.w, this.yb - 1);
        graphics.drawImage(this.offImage, 0, this.yOffset, this);
    }
    

}
