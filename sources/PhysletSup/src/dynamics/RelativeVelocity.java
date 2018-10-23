package dynamics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import java.awt.*;
import java.applet.Applet;

public class RelativeVelocity extends Applet
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
    String[] names;
    Label frameLabel;
    Checkbox cbShow;
    Checkbox cb2D;
    boolean show2D;
    String rts;
    String[] STR;
    boolean show;
    int xx;
    int yy;
    Color[] c;
    double[] X;
    double[] V;
    double V4;
    double X4;
    double V5;
    double X5;
    int ID;
    int[] dx;
    int[] Y;
    int[] dv;
    double VYman;
    boolean running;
    Thread animThread;
    long startTime;
    long lastTime;
    long delay;
    long delta;
    double xmax;
    double yman;
    boolean rightClick;
    int dragging;
    boolean manMoving;
    FontMetrics fm;
    int chy;
    int ya;
    int yb;
    int h1;
    int w1;
    int w2;
    int y02;
    int y22;
    int yball;
    int size;
    int size2;
    int dy;
    int dx0;
    int size3;
    int Y4;
    int w4;
    int x5max;
    int x4max;
    int manH;
    int dx1;
    double tu;
    double tu2;
    double tb;
    double tb0;
    double gravity;
    double gravity2;
    double X0;
    double V0;
    int x3;
    int dx4;
    int dv4;
    int dv5;
    int y4;
    int y5;
    int tmp;
    boolean dirty;
    int arrow;
    double Vscale;
    
    Timer timer;
    
    public void init() {
    	
        for (int i = 0; i < this.STR.length; ++i) {
            if ((this.rts = this.getParameter(this.STR[i])) != null) {
                this.STR[i] = new String(this.rts);
            }
        }
        for (int j = 0; j < this.names.length; ++j) {
            if ((this.rts = this.getParameter(this.names[j])) != null) {
                this.names[j] = new String(this.rts);
            }
        }
        this.setBackground(this.bgColor);
        final Panel panel = new Panel();
        panel.add(new Label(this.STR[6]));
        panel.add(this.frameLabel = new Label(this.names[0]));
        this.frameLabel.setForeground(Color.blue);
        panel.add(new Button(this.STR[0]));
        panel.add(new Button(this.STR[1]));
        panel.add(new Button(this.STR[3]));
        panel.add(this.cbShow = new Checkbox(this.STR[4]));
        panel.add(this.cb2D = new Checkbox(this.STR[5]));
        this.add("North", panel);
        this.area = this.size();
        final Dimension area = this.area;
        area.height -= this.yOffset;
        this.reset(true);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            final String s = (String)o;
            if (s.equals(this.STR[0])) {
                this.stop();
                this.reset(true);
            }
            else if (s.equals(this.STR[1])) {
                this.start();
        	    timer.start();
        	    this.running = true;
            }
            else if (s.equals(this.STR[3])) {
                this.clear();
            }
        }
        else if (event.target == this.cbShow) {
            this.show = this.cbShow.getState();
        }
        else if (event.target == this.cb2D) {
            if (!(this.show2D = this.cb2D.getState())) {
                this.clear();
            }
            this.tb0 = this.time - this.tu;
        }
        return true;
    }
    
    public void reset(final boolean b) {
        this.V[0] = 0.0;
        this.V[3] = 5.0;
        this.V[2] = 10.0;
        this.V[1] = 30.0;
        this.V4 = 10.0;
        this.X4 = 0.0;
        this.V5 = 5.0;
        this.X5 = this.w4 / 2.0;
        final double[] x = this.X;
        final int n = 1;
        final double[] x2 = this.X;
        final int n2 = 2;
        final double[] x3 = this.X;
        final int n3 = 0;
        final double n4 = 0.0;
        x3[n3] = n4;
        x[n] = (x2[n2] = n4);
        this.X[3] = this.area.width / 2;
        this.ID = 0;
        this.VYman = 0.0;
        this.show = this.cbShow.getState();
        this.manMoving = false;
        this.frameLabel.setText(this.names[this.ID]);
        this.clear();
        if (b) {
            final double n5 = 0.0;
            this.time = n5;
            this.ts = n5;
        }
        this.tb0 = this.time - this.tu;
        this.show2D = this.cb2D.getState();
        this.g.drawImage(this.bgImage, 0, 0, this);
        this.repaint();
    }
    
    public void start() {
    	this.delta=100;
	    	if(timer==null) this.timer = new Timer((int) this.delta, new ActionListener() {
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
        this.yman += this.VYman * n;
        this.Y[3] = this.mode(this.yman, this.area.height);
        if (this.Y[3] > this.ya && this.Y[3] < this.yb) {
            if (this.ID == 3) {
                this.ID = 2;
            }
            final double[] x = this.X;
            final int n2 = 3;
            x[n2] += this.V[2] * n;
        }
        this.V0 = this.V[this.ID];
        for (int i = 0; i < this.X.length; ++i) {
            final double[] x2 = this.X;
            final int n3 = i;
            x2[n3] += (this.V[i] - this.V0) * n;
        }
        this.X4 += (this.V4 - this.V0) * n;
        this.X5 += this.V5 * n;
        if (this.X5 > this.x5max) {
            this.X5 = 2 * this.x5max - this.X5;
            this.V5 *= -1.0;
        }
        else if (this.X5 < 0.0) {
            this.X5 = -this.X5;
            this.V5 *= -1.0;
        }
        this.time += n;
        this.ts += n;
        if (this.ts > 0.25) {
            this.ts -= 0.25;
            if (this.time > 100.0) {
                this.time -= 100.0;
                for (int j = 0; j < this.X.length; ++j) {
                    this.X[j] %= this.area.width;
                    this.yman %= this.area.height;
                }
            }
        }
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, int n2) {
        if ((n2 -= this.yOffset) < 0) {
            return false;
        }
        if (event.modifiers == 4) {
            this.rightClick = true;
        }
        this.dragging = -1;
        for (int i = 0; i < this.X.length - 1; ++i) {
            if (i != this.ID && Math.abs(n2 - this.Y[i]) < this.arrow && Math.abs(n - this.w2 - this.dv[i]) < this.arrow) {
                this.dragging = i;
                break;
            }
        }
        if (this.dragging == -1) {
            if (Math.abs(n2 - this.Y[3]) < this.arrow && Math.abs(n - this.dx[3] - this.dv[3]) < this.arrow) {
                this.dragging = 3;
            }
            else if (Math.abs(n2 - this.Y[3] - this.VYman * this.Vscale) < this.arrow && Math.abs(n - this.dx[3]) < this.arrow) {
                this.dragging = 4;
            }
            else if (Math.sqrt((n - this.dv4) * (n - this.dv4) + (n2 - this.y4) * (n2 - this.y4)) < this.size) {
                this.dragging = 5;
            }
            else if (Math.sqrt((n - this.dv5) * (n - this.dv5) + (n2 - this.y5) * (n2 - this.y5)) < this.size) {
                this.dragging = 6;
            }
            else {
                this.running = !this.running;
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, int n, int n2) {
        if (!this.running) {
            return true;
        }
        n2 -= this.yOffset;
        if (n2 > this.ya && n2 < this.yb) {
            if (n2 > this.Y[1] && n2 < this.Y[1] + this.h1 && (((n -= this.dx[1]) > 0 && n < this.w1) || ((n -= this.w2) > 0 && n < this.w1))) {
                this.ID = 1;
            }
            else {
                this.ID = 2;
            }
        }
        else if (n2 < this.Y[3] && n2 > this.Y[3] - this.manH && (n -= this.dx[3] + this.size) > 0 && n < 2 * this.size2) {
            this.ID = 3;
        }
        else {
            this.ID = 0;
        }
        this.frameLabel.setForeground(this.c[this.ID]);
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, int n2) {
        if ((n2 -= this.yOffset) < 0) {
            return false;
        }
        if (!this.running && this.dragging != -1) {
            if (this.dragging == 5) {
                this.V5 = (n - this.dx4) / this.Vscale;
            }
            else if (this.dragging == 6) {
                this.V4 = (n - this.dx4) / this.Vscale + this.V0 - this.V5;
            }
            else if (this.dragging == 4) {
                this.VYman = (n2 - this.Y[3]) / this.Vscale;
            }
            else if (this.dragging == 3) {
                this.V[3] = (n - this.dx[3]) / this.Vscale;
                if (n2 < this.ya || n2 > this.yb) {
                    final double[] v = this.V;
                    final int n3 = 3;
                    v[n3] += this.V0;
                }
            }
            else {
                this.V[this.dragging] = (n - this.w2) / this.Vscale + this.V0;
            }
            this.repaint();
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
        else if (this.dragging == -1) {
            this.running = !this.running;
        }
        this.repaint();
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
            this.Y[1] = (this.area.height - this.h1) / 3;
            this.Y4 = this.Y[1] * 2 + 30;
            this.x4max = this.area.width - this.w4;
            this.w2 = this.area.width / 2;
            this.x3 = this.w2 - this.w1;
            this.xmax = this.area.width;
            this.ya = 50;
            this.Y[0] = this.dy - this.size;
            this.Y[2] = this.Y[0] + this.ya;
            this.yb = this.area.height - this.ya;
            this.y02 = this.area.height - this.dy - this.size;
            this.y22 = this.y02 - this.ya;
            this.manH = 7 * this.size;
            this.yball = this.Y4 - 100;
            this.tu = Math.sqrt(2.0 * (this.Y4 - this.yball) / this.gravity);
            this.tu2 = 2.0 * this.tu;
        }
        this.Y[3] = (this.ya + this.manH) / 2;
        this.yman = this.Y[3];
        this.gb.setColor(this.bgColor);
        this.gb.fillRect(0, 0, this.area.width, this.area.height);
        this.gb.setColor(new Color(150, 220, 180));
        this.gb.fillRect(0, this.ya, this.area.width, this.yb - this.ya);
        this.gb.setColor(Color.black);
        this.gb.drawRect(0, 0, this.area.width - 1, this.area.height - 1);
        this.gb.drawLine(0, this.ya, this.area.width, this.ya);
        this.gb.drawLine(0, this.yb, this.area.width, this.yb);
        this.gb.setColor(Color.white);
    }
    
    String d2String(final double n) {
        String s = String.valueOf((float)((int)(n * 100.0) / 100.0));
        if (s.indexOf(".") == -1) {
            s += ".0";
        }
        return s;
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	updateScreen(g);
    }
    
    public void updateScreen(final Graphics graphics) {
        this.frameLabel.setText(this.names[this.ID]);
        this.g.drawImage(this.bgImage, 0, 0, this);
        this.g.setColor(Color.black);
        this.g.drawString(this.STR[2] + this.d2String(this.time), 5, this.area.height - 15);
        this.X0 = this.X[this.ID];
        this.V0 = this.V[this.ID];
        this.dx[1] = this.mode(this.X[1], this.w2);
        this.g.setColor(this.c[1]);
        this.g.fillOval(this.xx = this.dx[1], this.Y[1], this.w1, this.h1);
        this.g.fillOval(this.xx += this.w2, this.Y[1], this.w1, this.h1);
        this.xx = this.mode(this.X4, this.area.width);
        if (this.show2D) {
            if (this.dirty && this.xx < 2) {
                this.clear();
                this.dirty = false;
            }
            this.tb = (this.time - this.tb0) % this.tu2;
            if (this.tb > this.tu) {
                this.tb = this.tu2 - this.tb;
            }
            else {
                this.dirty = true;
            }
            this.yy = this.yball + (int)(this.gravity2 * this.tb * this.tb);
            this.g.setColor(Color.blue);
            this.g.drawOval(this.xx, this.yy, this.size2, this.size2);
            this.gb.drawLine(this.xx, this.yy += this.size, this.xx, this.yy);
            if (this.show) {
                this.g.drawString(String.valueOf(this.Y4 - this.yy), this.xx, this.yy);
            }
        }
        this.g.setColor(this.c[this.ID]);
        this.g.fillRect(this.xx, this.Y4, this.w4, this.size2);
        this.dx4 = this.xx + this.w4 / 2;
        if (this.show) {
            final Graphics g = this.g;
            final String string = "X=" + String.valueOf(this.xx);
            g.drawString(string, this.xx - this.fm.stringWidth(string), this.Y4);
            final Graphics g2 = this.g;
            final String string2 = "V=" + String.valueOf(this.V4 - this.V0);
            g2.drawString(string2, this.xx - this.fm.stringWidth(string2), this.yy = this.Y4 + this.size2 + this.chy);
        }
        if (this.xx > this.x4max) {
            this.g.fillRect(0, this.Y4, this.xx - this.x4max, this.size2);
        }
        this.g.setColor(Color.cyan);
        if ((this.xx += (int)this.X5) > this.area.width) {
            this.g.fillOval(this.xx -= this.area.width, this.Y4, this.size2, this.size2);
        }
        else {
            this.g.fillOval(this.xx, this.Y4, this.size2, this.size2);
        }
        this.g.setColor(Color.black);
        this.g.drawOval(this.xx, this.Y4, this.size2, this.size2);
        this.g.setColor(Color.cyan);
        if (this.show) {
            this.g.drawString("dx=" + String.valueOf((int)this.X5), this.xx, this.Y4);
            this.g.drawString("dV=" + String.valueOf(this.V5), this.xx, this.yy);
            this.g.setColor(this.c[this.ID]);
            this.g.drawString(" X=" + String.valueOf(this.xx), this.xx, this.Y4 - this.chy);
            final double n;
            this.g.drawString(" V=" + String.valueOf(n = this.V4 + this.V5 - this.V0), this.xx, this.yy += this.chy);
            if (!this.running) {
                this.g.drawLine(this.dx4, this.yy, this.dv5 = this.dx4 + (int)(this.Vscale * n), this.y5 = this.yy);
                if (n > 0.0) {
                    this.tmp = -this.arrow;
                }
                else {
                    this.tmp = this.arrow;
                }
                this.g.drawLine(this.dv5, this.yy, this.xx = this.dv5 + this.tmp, this.yy - this.size);
                this.g.drawLine(this.dv5, this.yy, this.xx, this.yy + this.size);
                this.g.setColor(Color.green);
                this.g.drawLine(this.dx4, this.yy -= this.chy, this.dv4 = this.dx4 + (int)(this.Vscale * this.V5), this.y4 = this.yy);
                if (this.V5 > 0.0) {
                    this.tmp = -this.arrow;
                }
                else {
                    this.tmp = this.arrow;
                }
                this.g.drawLine(this.dv4, this.yy, this.xx = this.dv4 + this.tmp, this.yy - this.size);
                this.g.drawLine(this.dv4, this.yy, this.xx, this.yy + this.size);
            }
        }
        this.dx[3] = this.mode(this.X[3], this.area.width);
        this.g.setColor(this.c[3]);
        this.g.drawOval(this.xx = this.dx[3], this.yy = this.Y[3] - this.manH, this.size2, this.size2);
        this.g.drawLine(this.xx += this.size, this.yy += this.size2, this.xx - this.size2, this.yy + this.size2);
        this.g.drawLine(this.xx, this.yy, this.xx + this.size2, this.yy + this.size2);
        this.g.drawLine(this.xx -= this.size, this.yy += this.size, this.xx, this.yy + this.size3);
        this.g.drawLine(this.xx += this.size2, this.yy, this.xx, this.yy + this.size3);
        this.dx[0] = this.mode(this.X[0], this.dx0);
        this.dx[2] = this.mode(this.X[2], this.dx0);
        final int n2 = this.mode(this.X[0], this.dx1) / this.dx0;
        final int n3 = this.mode(this.X[2], this.dx1) / this.dx0;
        int n4 = 0;
        int n5 = 0;
        while (this.xx < this.area.width) {
            this.g.setColor(this.c[0]);
            if (n5 % 5 == n2) {
                this.g.fillOval(this.xx = n4 + this.dx[0], this.Y[0], this.size2, this.size2);
                this.g.fillOval(this.xx, this.y02, this.size2, this.size2);
            }
            else {
                this.g.drawOval(this.xx = n4 + this.dx[0], this.Y[0], this.size2, this.size2);
                this.g.drawOval(this.xx, this.y02, this.size2, this.size2);
            }
            this.g.setColor(this.c[2]);
            if (n5 % 5 == n3) {
                this.g.fillOval(this.xx = n4 + this.dx[2], this.Y[2], this.size2, this.size2);
                this.g.fillOval(this.xx, this.y22, this.size2, this.size2);
            }
            else {
                this.g.drawOval(this.xx = n4 + this.dx[2], this.Y[2], this.size2, this.size2);
                this.g.drawOval(this.xx, this.y22, this.size2, this.size2);
            }
            n4 += this.dx0;
            ++n5;
        }
        if (this.show) {
            for (int i = 0; i < this.X.length; ++i) {
                this.g.setColor(this.c[this.ID]);
                if (i == 3) {
                    final Graphics g3 = this.g;
                    final int xx = this.dx[3];
                    this.xx = xx;
                    final int n6 = this.Y[3];
                    final int n7;
                    g3.drawLine(xx, n6, this.xx, n7 = n6 + (int)(this.VYman * this.Vscale));
                    if (this.VYman != 0.0) {
                        if (this.VYman > 0.0) {
                            this.tmp = -this.arrow;
                        }
                        else {
                            this.tmp = this.arrow;
                        }
                        this.g.drawLine(this.xx, n7, this.xx + this.arrow, n7 + this.tmp);
                        this.g.drawLine(this.xx, n7, this.xx - this.arrow, n7 + this.tmp);
                    }
                    this.g.drawString(String.valueOf(this.VYman), this.xx, n7 + this.chy);
                    this.g.drawLine(this.xx, this.yy = this.Y[3], this.xx + this.dv[3], n7);
                    if (this.Y[3] > this.ya && this.Y[3] < this.yb) {
                        this.dv[3] = (int)(this.Vscale * (this.V[3] - this.V0 + this.V[2]));
                    }
                    else {
                        this.dv[3] = (int)(this.Vscale * (this.V[3] - this.V0));
                    }
                    this.g.drawLine(this.xx, this.yy, this.xx += this.dv[3], this.yy);
                }
                if (i != this.ID) {
                    if (i != 3) {
                        this.dv[i] = (int)(this.Vscale * (this.V[i] - this.V0));
                        this.g.drawLine(this.xx = this.w2, this.yy = this.Y[i], this.xx += this.dv[i], this.yy);
                    }
                    if (this.dv[i] > 0) {
                        this.tmp = -this.arrow;
                    }
                    else {
                        this.tmp = this.arrow;
                    }
                    this.g.drawLine(this.xx, this.yy, this.xx + this.tmp, this.yy - this.arrow);
                    this.g.drawLine(this.xx, this.yy, this.xx + this.tmp, this.yy + this.arrow);
                    this.g.drawString(String.valueOf(this.dv[i] / this.Vscale), this.xx, this.Y[i] + this.chy);
                }
                this.g.setColor(this.c[i]);
                this.g.drawString(String.valueOf((int)this.X[i]), 0, this.Y[i] + this.chy);
            }
        }
        graphics.drawImage(this.fgImage, 0, this.yOffset, this);
    }
    
    int mode(final double n, final int n2) {
        double n3 = n % n2;
        if (n3 < 0.0) {
            n3 += n2;
        }
        return (int)n3;
    }
    
    public RelativeVelocity() {
        this.yOffset = 40;
        this.time = 0.0;
        this.ts = 0.0;
        this.bgColor = Color.lightGray;
        this.names = new String[] { "Ground", "Boat", "River", "Man" };
        this.show2D = false;
        this.STR = new String[] { "Reset", "Start", "Time", "Clear", "Info", "Ball", "Reference" };
        this.show = false;
        this.c = new Color[] { Color.black, Color.red, Color.yellow, Color.blue };
        this.X = new double[4];
        this.V = new double[this.X.length];
        this.ID = 0;
        this.dx = new int[this.X.length];
        this.Y = new int[this.X.length];
        this.dv = new int[this.X.length];
        this.running = true;
        this.startTime = 0L;
        this.delay = 50L;
        this.rightClick = false;
        this.dragging = -1;
        this.manMoving = false;
        this.h1 = 10;
        this.w1 = 50;
        this.size = 2;
        this.size2 = 2 * this.size;
        this.dy = 5;
        this.dx0 = 50;
        this.size3 = 2 * this.size2;
        this.w4 = 150;
        this.x5max = this.w4 - this.size2;
        this.dx1 = 5 * this.dx0;
        this.gravity = 9.8;
        this.gravity2 = this.gravity / 2.0;
        this.dirty = false;
        this.arrow = 5;
        this.Vscale = 4.0;
    }
}
