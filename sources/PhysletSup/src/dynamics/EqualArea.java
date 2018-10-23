package dynamics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import java.awt.*;
import java.applet.Applet;
import java.applet.Applet;

public class EqualArea extends Applet
{
    Dimension area;
    Button bs;
    String rts;
    String[] STR;
    Color bgColor;
    Image bgImage;
    Graphics gb;
    int size;
    int size2;
    double x;
    double deltaX;
    double deltaT;
    int xmax;
    int id;
    int imax;
    int[] X;
    int[] Y;
    int ys;
    int[] X2;
    Color[] c;
    boolean fill;
    int height;
    int width;
    int ydrop;
    int yOffset;
    //Thread animThread;
    long startTime;
    long lastTime;
    long delay;
    long delta;
    boolean running;
    double vi;
    int dropId;
    int yfill;
    int xfill;
   
    Timer timer;
    
    public void init() {
        for (int i = 0; i < this.STR.length; ++i) {
            if ((this.rts = this.getParameter(this.STR[i])) != null) {
                this.STR[i] = new String(this.rts);
            }
        }
        this.setBackground(this.bgColor);
        final Panel panel = new Panel();
        panel.add(this.bs = new Button(this.STR[1]));
        this.add("North", panel);
        this.reset();
    }
    
    public void reset() {
        this.area = this.size();
        final Dimension area = this.area;
        area.height -= this.yOffset;
        this.X[0] = (this.X[1] = 50);
        this.X2[0] = this.X[0];
        this.width = (int)(this.vi * this.deltaT);
        for (int i = 1; i < this.imax; ++i) {
            this.X2[i] = this.X2[i - 1] + this.width;
        }
        final double n = this.X[0];
        this.deltaX = n;
        this.x = n;
        final int[] y = this.Y;
        final int n2 = 1;
        final int[] y2 = this.Y;
        final int n3 = 2;
        final int ys = this.area.height / 6;
        y[n2] = (y2[n3] = ys);
        this.ys = ys;
        this.height = this.Y[0] - this.Y[1];
        this.Y[0] = this.Y[1] + this.area.height / 4;
        this.id = 0;
        this.clear();
        this.drawBall((int)this.x, this.ys, false, false);
        this.drawBox();
        this.fill = true;
        this.ydrop = -1;
        this.repaint();
    }
    
    public boolean action(final Event event, final Object o) {
        final String s = (String)o;
        if (event.target instanceof Button) {
            if (s.equals(this.STR[1])) {
                this.bs.setLabel(this.STR[0]);
            }
            this.reset();
    	    timer.start();
    	    this.running = true;
        }
        return true;
    }
    
    public void start() {
    	this.delta=100;
	    	if(timer==null) this.timer = new Timer((int) this.delta/2, new ActionListener() {
	    	    public void actionPerformed(ActionEvent ae) {
	    	    		run();
	    	    }
	    	}); 
		timer.setRepeats(true);
    }
    
    public void stop() {
        timer.stop();
        this.running = false;
    }
    
    
    public void run() {
	    if (this.running) {
	    	this.advanced(this.delta / 2000.0);
	    }
    }
    
    void advanced(final double n) {
        this.x += this.vi * n;
        if (this.fill) {
            if (this.x > this.X2[this.imax - 1]) {
                this.drawBall((int)this.x, this.ys, true, false);
                if (this.x > this.area.width) {
                    this.fill = false;
                    this.running = false;
                }
            }
            else if (this.x > this.deltaX) {
                this.x = this.deltaX;
                ++this.id;
                this.deltaX = this.X2[this.id];
            }
            this.drawBall((int)this.x, this.ys, true, true);
            this.repaint();
        }
        else if (this.ydrop > -1) {
            this.dropit(this.dropId, this.ydrop++);
            if (this.ydrop > this.height) {
                this.ydrop = -1;
                this.gb.setColor(Color.white);
                this.gb.drawLine(this.X2[0], this.Y[0] + 1, this.X2[this.dropId], this.Y[0] + 1);
                this.gb.drawLine(this.X2[this.dropId], this.Y[0] + 1, this.X2[this.dropId], this.Y[0] + this.height / 2 + 1);
            }
            this.repaint();
        }
    }
    
    void drawBall(final int n, final int n2, final boolean b, final boolean b2) {
        this.X[2] = n;
        if (b) {
            this.gb.setColor(this.bgColor);
            this.gb.fillOval(this.X[1] - this.size, this.Y[1] - this.size2, this.size2, this.size2);
        }
        this.gb.setColor(Color.black);
        this.gb.fillOval(this.X[2] - this.size, this.Y[2] - this.size2, this.size2, this.size2);
        if (b2) {
            this.gb.setColor(this.c[this.id]);
            this.gb.fillPolygon(this.X, this.Y, 3);
        }
        this.X[1] = n;
    }
    
    int getID(final int n, final int n2) {
        final double n3 = (this.Y[0] - n2) / (n - this.X2[0]);
        for (int i = 1; i < this.imax; ++i) {
            if (n3 > this.height / (this.X2[i] - this.X2[0])) {
                return i - 1;
            }
        }
        return -1;
    }
    
    public boolean mouseDown(final Event event, final int n, int n2) {
        n2 -= this.yOffset;
        if (!this.fill && this.ydrop == -1) {
            final int id = this.getID(n, n2);
            if (id == -1) {
                return true;
            }
            this.dropId = id;
            this.gb.setColor(this.c[this.dropId + 1]);
            this.gb.drawLine(this.X2[0], this.Y[0] + 1, this.X2[this.dropId], this.Y[0] + 1);
            this.gb.drawLine(this.X2[this.dropId], this.Y[0] + 1, this.X2[this.dropId], this.Y[0] + this.height);
            this.yfill = this.Y[0] + this.height + 1;
            this.xfill = -1;
            this.ydrop = 0;
            this.running = true;
        }
        return true;
    }
    
    void clear() {
        if (this.gb == null) {
            this.bgImage = this.createImage(this.area.width, this.area.height);
            this.gb = this.bgImage.getGraphics();
        }
        this.gb.setColor(this.bgColor);
        this.gb.fillRect(0, 0, this.area.width, this.area.height);
    }
    
    void drawBox() {
        final int n = this.Y[0] - this.Y[1];
        final int n2 = this.Y[0] + 1;
        int n3 = this.X2[0] - 1;
        this.gb.setColor(Color.black);
        for (int i = 0; i < this.imax - 1; ++i) {
            this.gb.drawLine(n3, n2, n3, n2 + n);
            this.gb.drawLine(n3, n2 + n, n3 + this.width, n2 + n);
            n3 += this.width;
        }
        this.gb.drawLine(n3, n2, n3, n2 + n);
    }
    
    void dropit(final int n, final int n2) {
        int xfill = (int)((this.X2[n + 1] - this.X2[n]) * (1.0 - n2 / this.height));
        this.gb.setColor(this.bgColor);
        this.gb.drawLine(this.X2[n] - (this.X2[n] - this.X2[0]) * n2 / this.height, this.Y[1] + n2, this.X2[n + 1] - (this.X2[n + 1] - this.X2[0]) * n2 / this.height, this.Y[1] + n2);
        this.gb.setColor(this.c[n + 1]);
        this.xfill += xfill;
        if (this.xfill > this.width - 2) {
            --this.yfill;
            this.xfill -= this.width;
            xfill = this.xfill;
            this.gb.drawLine(this.X2[n], this.yfill, this.X2[n] + this.width - 2, this.yfill);
        }
        this.gb.drawLine(this.X2[n] + this.xfill, this.yfill, this.X2[n] + this.xfill - xfill, this.yfill);
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	 g.drawImage(this.bgImage, 0, this.yOffset, this);
    }
    

    public EqualArea() {
        this.STR = new String[] { "Reset", "Start" };
        this.bgColor = new Color(200, 223, 208);
        this.size = 2;
        this.size2 = 2 * this.size;
        this.deltaT = 1.0;
        this.xmax = 600;
        this.imax = 6;
        this.X = new int[3];
        this.Y = new int[3];
        this.X2 = new int[this.imax];
        this.c = new Color[] { Color.black, Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink };
        this.yOffset = 40;
        this.startTime = 0L;
        this.delay = 100L;
        this.running = false;
        this.vi = 100.0;
    }
}
