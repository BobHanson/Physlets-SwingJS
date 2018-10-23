package dynamics;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.Applet;
import javax.swing.Timer;

import dynamics.SFront;

import java.awt.*;
// 
// Decompiled by Procyon v0.5.30
// 

public class Airplane extends Applet implements Runnable
{
    int yOffset;
    double time;
    double ts;
    Dimension area;
    Image bgImage;
    Image fgImage;
    Graphics gb;
    Graphics g;
    Color bgColor;
    double va;
    double vs;
    double cta;
    int count;
    int n;
    int d;
    int id;
    SFront[] S;
    Image ear;
    Image plane;
    Choice cb;
    String[] label;
    String Start;
    String Reset;
    String rts;
    String[] STR;
    int earw;
    int earh;
    int planew;
    int planeh;
    int xx;
    int yy;
    int idx;
    boolean running;
    //Thread animThread;
    long startTime;
    long lastTime;
    long delay;
    long delta;
    double cst;
    boolean rightClick;
    boolean dragging;
    FontMetrics fm;
    int chy;
    double xa;
    double ya;
    int xm;
    int ym;
    int y1;
    int size;
    int size2;
    Timer timer;
    public String     imagedir    = "./";
    
    public void init() {
    	this.setSize(700, 600);
        this.setBackground(this.bgColor);
        for (int i = 0; i < this.STR.length; ++i) {
            if ((this.rts = this.getParameter(this.STR[i])) != null) {
                this.STR[i] = new String(this.rts);
            }
        }
        Panel panel = new Panel();
        panel.add(new Label(this.STR[0]));
        panel.add(this.cb = new Choice());
        for (int j = 0; j < this.label.length; ++j) {
            this.cb.addItem(this.label[j]);
        }
        panel.add(new Button(this.Reset = this.STR[1]));
        panel.add(new Button(this.Start = this.STR[2]));
        this.cb.select(4);
        this.add("North", panel);
        this.area = this.size();
        final Dimension area = this.area;
        area.height -= this.yOffset;
        this.count = this.area.width / 5;
        this.S = new SFront[this.count];
        for (int k = 0; k < this.count; ++k) {
            this.S[k] = new SFront();
        }
        String s = getParameter("imagedir");
        if(s == null) {
          imagedir = "./";
        } else {
          imagedir += s + "/";
        }
        this.loadImage();
        this.reset(true);
    }
  /* 
    void loadImage() {
        final URL codeBase = this.getCodeBase();
        this.ear = this.getImage(codeBase, "ear.jpg");
        this.plane = this.getImage(codeBase, "airplane.jpg");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.ear, 0);
        mediaTracker.addImage(this.plane, 1);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.earw = this.ear.getWidth(this) / 2;
        this.earh = this.ear.getHeight(this) / 2;
        this.planew = this.plane.getWidth(this);
        this.planeh = this.plane.getHeight(this) / 2 + 5;
    }
    */
    
    void loadImage() {
    	this.plane=edu.davidson.graphics.Util.getImage(imagedir+"airplane.jpg",this);
        this.ear=edu.davidson.graphics.Util.getImage(imagedir+"ear.jpg",this);   
        if(plane!=null) {
          this.planew = this.plane.getWidth(this);
          this.planeh = this.plane.getHeight(this) / 2 + 5;
        }
        if(ear!=null) {
          this.earw = this.ear.getWidth(this) / 2;
          this.earh = this.ear.getHeight(this) / 2;
        }
       
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            final String s = (String)o;
            if (s.equals(this.Reset)) {
                this.reset(true);
            }
            else if (s.equals(this.Start)) {
            	this.reset(true);
                this.running = true;
                this.start();
        	    timer.start();
        	    this.running = true;
            }
        }
        return true;
    }
    
    public void reset(final boolean b) {
        this.xa = 0.0;
        this.ya = this.y1;
        this.id = 0;
        this.n = 0;
        this.idx = 0;
        this.va = this.vs * (this.cb.getSelectedIndex() + 1) / 2.0;
        this.cta = Math.asin(this.vs / this.va);
        this.clear();
    }
    
    String d2String(final double n) {
        String s = String.valueOf((float)((int)(n * 100.0) / 100.0));
        if (s.indexOf(".") == -1) {
            s += ".0";
        }
        return s;
    }
    
    
    public void start() {
    	this.delta=200;
	    	if(timer==null) this.timer = new Timer((int) this.delta/2, new ActionListener() {
	    	    public void actionPerformed(ActionEvent ae) {
	    	    		run();
	    	    }
	    	}); 
		timer.setRepeats(true);
	    //timer.start();
	    //this.running = true;
    }
    
    public void stop() {
        timer.stop();
        this.running = false;
    }
    
    
    public void run() {
	    if (this.running) {
	        //this.advanced(this.delta / 1000.0);
	        this.advanced(this.delta / 1000.0);
	    }
    }
    
    void advanced(final double n) {
        this.xa += this.va * n;
        if (this.xa < this.area.width && this.xa / this.d > this.id) {
            this.id = (int)(this.xa / this.d) + 1;
            this.cst = this.vs / Math.sqrt((this.xm - this.xa) * (this.xm - this.xa) + (this.ym - this.ya) * (this.ym - this.ya));
            this.S[this.n].setup(this.xa, this.ya, this.cst * (this.xm - this.xa), this.cst * (this.ym - this.ya));
            this.gb.fillOval((int)this.xa - this.size, (int)this.ya - this.size, this.size2, this.size2);
            ++this.n;
            if (this.n == this.count) {
                this.stop();
            }
        }
        for (int i = 0; i < this.n; ++i) {
            this.S[i].moving(n);
        }
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, int n2) {
        if ((n2 -= this.yOffset) < 0) {
            return false;
        }
        if (event.modifiers == 4) {
            this.rightClick = true;
            this.running = !this.running;
        }
        else if (n > this.xm - this.earw && n2 > this.ym - this.earh && n < this.xm + this.earw && n2 < this.ym + this.earh) {
            this.dragging = true;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int xm, int ym) {
        if ((ym -= this.yOffset) < 0) {
            return false;
        }
        if (this.dragging) {
            this.xm = xm;
            this.ym = ym;
            this.reset(true);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, int n2) {
        if ((n2 -= this.yOffset) < 0) {
            return false;
        }
        if (this.rightClick) {
            this.rightClick = false;
        }
        this.dragging = false;
        return true;
    }
    
    void clear() {
        if (this.g == null) {
            this.bgImage = this.createImage(this.area.width, this.area.height);
            this.gb = this.bgImage.getGraphics();
            this.fgImage = this.createImage(this.area.width, this.area.height);
            this.g = this.fgImage.getGraphics();
            this.fm = this.gb.getFontMetrics();
            this.chy = this.fm.getHeight();
            this.xm = this.area.width / 2;
            this.ym = this.area.height - this.y1;
        }
        this.gb.setColor(this.bgColor);
        this.gb.fillRect(0, 0, this.area.width, this.area.height);
        this.gb.setColor(Color.black);
        this.gb.drawRect(0, 0, this.area.width - 1, this.area.height - 1);
        if(this.ear!=null)this.gb.drawImage(this.ear, this.xm - this.earw, this.ym - this.earh, Color.white, this);
        this.gb.setColor(Color.white);
        this.repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	updateScreen(g);
    }
    
    public void updateScreen(final Graphics graphics) {
        this.g.drawImage(this.bgImage, 0, 0, this);
        for (int i = 0; i < this.n; ++i) {
            if (i % 2 == 0) {
                this.g.setColor(Color.green);
            }
            else {
                this.g.setColor(Color.yellow);
            }
            this.g.fillOval(this.xx = (int)this.S[i].x - this.size, this.yy = (int)this.S[i].y - this.size, this.size2, this.size2);
            if ((int)this.S[i].y >= this.ym) {
                this.g.setColor(Color.blue);
                if (this.S[i].on) {
                    this.gb.setColor(Color.black);
                    this.gb.drawString(String.valueOf(++this.idx), (int)this.S[i].x0, (int)this.S[i].y0 + this.chy * (this.idx % 2));
                    this.S[i].on = false;
                    if (this.idx == this.n) {
                        this.stop();
                    }
                    else if (this.idx == 1 && this.va > this.vs) {
                        this.running = !this.running;
                    }
                }
            }
            this.g.drawLine((int)this.S[i].x, (int)this.S[i].y, (int)this.S[i].x0, (int)this.S[i].y0);
        }
       if(this.plane!=null)this.g.drawImage(this.plane, this.xx = (int)this.xa - this.planew, this.yy = (int)this.ya - this.planeh, Color.lightGray, this);
        this.g.setColor(Color.black);
        if (this.va > this.vs) {
            this.g.drawLine(this.xx = (int)this.xa, this.yy = (int)this.ya, (int)(this.xa - (this.area.height - this.ya) / Math.tan(this.cta)), this.area.height);
            this.g.drawLine(this.xx, this.yy, (int)(this.xa - this.ya / Math.tan(this.cta)), 0);
        }
        graphics.drawImage(this.fgImage, 0, this.yOffset, this);
    }
    
    public Airplane() {
        this.yOffset = 40;
        this.time = 0.0;
        this.ts = 0.0;
        this.bgColor = Color.lightGray;
        this.va = 250.0;
        this.vs = 100.0;
        this.d = 15;
        this.label = new String[] { "0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0" };
        this.STR = new String[] { "speedRatioLabel", "Reset", "Start" };
        this.running = false;
        this.startTime = 0L;
        this.delay = 50L;
        this.rightClick = false;
        this.dragging = false;
        this.y1 = 30;
        this.size = 2;
        this.size2 = 2 * this.size;
    }
}
