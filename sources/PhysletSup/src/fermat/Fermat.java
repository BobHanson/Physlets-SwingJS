package fermat;
import java.awt.Polygon;

import javax.swing.Timer;
import java.awt.event.*;

import java.awt.*;

import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Event;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Fermat extends Applet implements Runnable {
    double time;
    double ts;
    Dimension area;
    Image bgImage;
    Image fgImage;
    Graphics gb;
    Graphics g;
    Color bgColor;
    int N;
    int dN;
    int N1;
    ray[] r;
    String rts;
    String author;
    String[] STR;
    int xx;
    int yy;
    boolean running;
    //Thread animThread;
    long startTime;
    long lastTime;
    long delay;
    long delta;
    double xc2;
    int id;
    int count;
    boolean rightClick;
    boolean info;
    boolean type;
    int mode;
    FontMetrics fm;
    int chy;
    int yc;
    Color clr;
    Color clr2;
    int xs;
    int ys;
    int xl;
    int yl;
    int xr;
    int yr;
    int size;
    int size2;
    double ls;
    double ll;
    double lr;
    double PI2;
    double cta;
    double cta1;
    double cta2;
    double dcta;
    double xc;
    double v;
    Timer timer;
    
    public void init() {
        for (int i = 0; i < this.STR.length; ++i) {
            if ((this.rts = this.getParameter(this.STR[i])) != null) {
                this.STR[i] = new String(this.rts);
            }
        }
        if (this.STR[6].compareTo("lang") != 0) {
            this.author = "§@ª\u00cc¡G¶\u00c0º\u00d6©[";
        }
        else {
            this.author = "Author:Fu-Kwun Hwang";
        }
        this.setBackground(this.bgColor);
        this.area = this.size();
        this.reset(true);
    }
    
    public void reset(final boolean b) {
        this.count = 0;
        this.clear(this.running = true);
        this.start();
    }
    
    String d2String(final double n) {
        String s = String.valueOf((float)((int)(n * 100.0) / 100.0));
        if (s.indexOf(".") == -1) {
            s += ".0";
        }
        return s;
    }
    
    public void start() {
    	this.delta=100;
	    	if(timer==null) this.timer = new Timer((int) this.delta/2, new ActionListener() {
	    	    public void actionPerformed(ActionEvent ae) {
	    	    		run();
	    	    }
	    	}); 
		timer.setRepeats(true);
	    timer.start();
	    this.running = true;
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
        for (int i = 0; i < this.N; ++i) {
            this.r[i].advanced(n);
        }
        ++this.count;
        this.id = ray.id;
        if (this.id == 3) {
            this.gb.setColor(this.clr);
            this.gb.drawString(this.STR[2], 5, this.chy * 2);
            if (this.type) {
                this.gb.setColor(this.bgColor);
                this.gb.fillRect(1, 1, this.area.width / 2, this.chy);
                this.gb.setColor(Color.white);
                this.gb.drawString(this.STR[3], 5, this.chy);
            }
            else if (ray.n < 1.0) {
                final Polygon polygon = new Polygon();
                final int n2 = (int)(this.ls * Math.tan(Math.asin(ray.n)));
                polygon.addPoint(this.xs - n2, this.yc);
                polygon.addPoint(this.xs, this.ys);
                polygon.addPoint(this.xs + n2, this.yc);
                this.gb.setColor(Color.red);
                this.gb.drawPolygon(polygon);
            }
            this.stop();
        }
        if (this.type) {
            if (this.id % 2 == 1) {
                this.gb.setColor(Color.white);
                this.gb.drawLine(this.xs, this.ys, this.xx = (int)this.xc, this.yc);
                this.gb.drawLine(this.xx, this.yc, this.xl, this.yl);
                this.gb.setColor(this.clr);
                this.gb.drawLine(this.xx, this.yl, this.xx, this.yc);
            }
            if (this.id > 1) {
                this.gb.setColor(Color.white);
                this.gb.drawLine(this.xs, this.ys, this.xx = (int)this.xc2, this.yc);
                this.gb.drawLine(this.xx, this.yc, this.xr, this.yr);
                //this.gb.setColor(this.clr2);
                this.gb.setColor(Color.DARK_GRAY);
                //this.gb.drawLine(this.xx, this.ys, this.xx, this.yr);
                this.gb.drawLine(this.xx, this.getHeight()/2, this.xx, this.yr);
            }
        }
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mode = 0;
        if (event.modifiers == 4) {
            this.rightClick = true;
            this.running = !this.running;
        }
        else {
            this.rightClick = false;
            if (n2 < this.chy && n < 100) {
                this.type = !this.type;
                this.clear(true);
            }
            else if (n < 100 && Math.abs(n2 - this.yc) < this.chy) {
                ray.change();
                this.info = false;
                this.clear(true);
            }
            else if (Math.abs((n - this.xs) * (n - this.xs) + (n2 - this.ys) * (n2 - this.ys)) < this.size2) {
                this.mode = 1;
            }
            else if (Math.abs((n - this.xl) * (n - this.xl) + (n2 - this.yl) * (n2 - this.yl)) < this.size2) {
                this.mode = 2;
            }
            else if (Math.abs((n - this.xr) * (n - this.xr) + (n2 - this.yr) * (n2 - this.yr)) < this.size2) {
                this.mode = 3;
            }
            else {
                this.info = !this.info;
                this.repaint();
            }
            if (this.mode != 0) {
                this.running = false;
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int xr, final int yr) {
        if (this.mode == 1 && xr < this.area.width / 2 && yr < this.yc - 10) {
            this.xs = xr;
            this.ys = yr;
            this.clear(true);
        }
        else if (this.mode == 2 && xr > this.area.width / 2 && yr < this.yc - 10) {
            this.xl = xr;
            this.yl = yr;
            this.clear(true);
        }
        else if (this.mode == 3 && xr > this.area.width / 2 && yr > this.yc + 10) {
            this.xr = xr;
            this.yr = yr;
            this.clear(true);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.mode != 0 || (timer== null && this.rightClick)) {
            this.reset(true);
        }
        return true;
    }
    
    double getAngle(final double n) {
        if (n > this.xs) {
            return Math.atan(this.ls / (n - this.xs));
        }
        return this.PI2 + Math.atan((this.xs - n) / this.ls);
    }
    
    void check() {
        double n = 100.0;
        for (int i = this.xs; i < this.xr; ++i) {
            final double abs = Math.abs(Math.sin(Math.atan((i - this.xs) / this.ls)) / Math.sin(Math.atan((this.xr - i) / this.lr)) - ray.n);
            if (abs < n) {
                n = abs;
                this.xc2 = i;
            }
        }
    }
    
    void clear(final boolean b) {
        if (this.g == null) {
            this.bgImage = this.createImage(this.area.width, this.area.height);
            this.gb = this.bgImage.getGraphics();
            this.fgImage = this.createImage(this.area.width, this.area.height);
            this.g = this.fgImage.getGraphics();
            this.fm = this.gb.getFontMetrics();
            this.chy = this.fm.getHeight();
            this.yc = this.area.height / 2;
            this.xs = this.area.width / 3;
            this.ys = this.yc / 3;
            this.xl = this.area.width - this.xs;
            this.yl = this.ys;
            this.xr = this.xl;
            this.yr = this.area.height - this.yl;
        }
        if (b) {
            this.ls = this.yc - this.ys;
            this.ll = this.yc - this.yl;
            this.lr = this.yr - this.yc;
            this.cta1 = this.getAngle(this.area.width);
            this.cta2 = this.getAngle(0.0);
            this.xc = (this.ls * this.xl + this.ll * this.xs) / (this.ls + this.ll);
            this.cta = this.getAngle(this.xc);
            this.dcta = (this.cta2 - this.cta1) / this.N1;
            this.cta2 = this.cta - (int)((this.cta - this.cta1) / this.dcta) * this.dcta;
            ray.setup(this.type, this.v, this.yc, this.xs, this.ys, this.xl, this.yl, this.xr, this.yr);
            for (int i = 0; i < this.N; ++i, this.cta2 += this.dcta) {
                this.r[i] = new ray(this.xs, this.ys, this.v * Math.cos(this.cta2), this.v * Math.sin(this.cta2));
            }
            this.check();
            this.running = true;
            this.start();
        }
        this.gb.setColor(this.bgColor);
        this.gb.fillRect(0, 0, this.area.width, this.yc);
        this.gb.setColor(this.clr);
        this.gb.fillRect(0, this.yc, this.area.width, this.area.height);
        this.gb.setColor(Color.black);
        this.gb.drawRect(0, 0, this.area.width - 1, this.area.height - 1);
        if (this.type) {
            this.gb.drawString(this.STR[4], 5, this.chy);
            if(running)this.clr2 = new Color(255, 64, 64);
            else this.clr2 = new Color(170, 170, 170);
        }
        else {
            this.gb.drawString(this.STR[5], 5, this.chy);
            this.clr2 = Color.white;
        }
        if (ray.n > 1.0) {
            this.gb.drawString(this.STR[1], 5, this.yc - 5);
            this.gb.drawString(this.STR[0], 5, this.yc + this.chy + 5);
        }
        else {
            this.gb.drawString(this.STR[0], 5, this.yc - 5);
            this.gb.drawString(this.STR[1], 5, this.yc + this.chy + 5);
        }
        this.gb.drawString(this.author, 5, this.area.height - this.chy / 2);
        this.gb.setColor(Color.white);
        this.gb.fillOval(this.xs - this.size, this.ys - this.size, this.size2, this.size2);
        this.gb.setColor(Color.blue);
        this.gb.fillOval(this.xl - this.size, this.yl - this.size, this.size2, this.size2);
        this.gb.setColor(Color.green);
        this.gb.fillOval(this.xr - this.size, this.yr - this.size, this.size2, this.size2);
        this.gb.setColor(Color.white);
        this.g.setColor(Color.white);
        if (b) {
            this.repaint();
        }
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	updateScreen(g);
    }
    
    public void updateScreen(final Graphics graphics) {
        this.gb.setColor(this.clr2);
        for (int i = 0; i < this.N; i += this.dN) {
            this.gb.drawLine(this.xx = (int)this.r[i].x, this.yy = (int)this.r[i].y, this.xx, this.yy);
            if (!this.r[i].mode) {
                this.gb.drawLine(this.xx = (int)this.r[i].x2, this.yy = (int)this.r[i].y2, this.xx, this.yy);
            }
        }
        this.g.drawImage(this.bgImage, 0, 0, this);
        this.g.setColor(Color.white);
        for (int j = 0; j < this.N; ++j) {
            this.g.drawLine(this.xx = (int)this.r[j].x, this.yy = (int)this.r[j].y, this.xx, this.yy);
            if (!this.r[j].mode) {
                this.g.drawLine(this.xx = (int)this.r[j].x2, this.yy = (int)this.r[j].y2, this.xx, this.yy);
            }
        }
        if (this.info && this.type && timer == null) {
            this.xx = (int)this.xc2;
            final double sqrt = Math.sqrt((this.xc2 - this.xr) * (this.xc2 - this.xr) + (this.yr - this.yc) * (this.yr - this.yc));
            this.g.setColor(Color.pink);
            this.g.drawOval(this.xx - (int)sqrt, this.yc - (int)sqrt, (int)(2.0 * sqrt), (int)(2.0 * sqrt));
            this.g.setColor(Color.yellow);
            this.g.drawLine(this.xx, this.yr, this.xr, this.yr);
            final double atan = Math.atan((this.xc2 - this.xs) / this.ls);
            final double n = sqrt * Math.sin(atan);
            this.g.drawLine(this.xx - (int)n, this.yy = this.yc - (int)(sqrt * Math.cos(atan)), this.xx, this.yy);
            this.g.drawString(String.valueOf(this.xr - (int)this.xc2), this.xx, this.yr);
            this.g.drawString(String.valueOf((int)n), this.xx - (int)n, this.yy);
        }
        graphics.drawImage(this.fgImage, 0, 0, this);
    }
    
    public Fermat() {
        this.time = 0.0;
        this.ts = 0.0;
        this.bgColor = Color.lightGray;
        this.N = 81;
        this.dN = 5;
        this.N1 = this.N + 1;
        this.r = new ray[this.N];
        this.STR = new String[] { "water", "air", "MSG1", "MSG2", "PossiblePath", "RealPath", "lang" };
        this.running = true;
        this.startTime = 0L;
        this.delay = 50L;
        this.count = 0;
        this.rightClick = false;
        this.info = false;
        this.type = true;
        this.clr = new Color(160, 160, 160);
        this.size = 4;
        this.size2 = 2 * this.size;
        this.PI2 = 1.5707963267948966;
        this.v = 30.0;
    }
}
