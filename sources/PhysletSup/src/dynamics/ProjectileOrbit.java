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

import dynamics.SFront;

import java.applet.Applet;
import java.awt.*;

public class ProjectileOrbit extends Applet
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
    MovingProjectile mb;
    Button bs;
    Checkbox cb;
    boolean state;
    String rts;
    String[] STR;
    int id;
    int xx;
    int yy;
    boolean running;
    long startTime;
    long lastTime;
    long delay;
    long delta;
    boolean rightClick;
    boolean dragging;
    FontMetrics fm;
    int chy;
    int xc;
    int yc;
    int Re;
    int De;
    int w1;
    int w0;
    int w2;
    double v0;
    double dv;
    double v;
    double r0;
    double rcst;
    double tcst;
    double cst;
    double rcst2;
    Image earth;
    int height;
    int width;
    int x1;
    int y1;
    int stry;
    int xs;
    int ys;
    double vcst;
    double dc;
    Timer timer;
    public String     imagedir    = "./";
    
    public void init() {
    	this.setSize(700, 600);
        for (int i = 0; i < this.STR.length; ++i) {
            if ((this.rts = this.getParameter(this.STR[i])) != null) {
                this.STR[i] = new String(this.rts);
            }
        }
        this.setBackground(this.bgColor);
        final Panel panel = new Panel();
        panel.add(new Button(this.STR[0]));
        panel.add(this.bs = new Button(this.STR[1]));
        panel.add(new Button(this.STR[3]));
        panel.add(new Button(this.STR[4]));
        panel.add(this.cb = new Checkbox(this.STR[5]));
        this.add("North", panel);
        this.area = this.size();
        final Dimension area = this.area;
        area.height -= this.yOffset;
        
        String s = getParameter("imagedir");
        if(s == null) {
          imagedir = "./";
        } else {
          imagedir += s + "/";
        }
        this.reset(true);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            final String s = (String)o;
            if (s.equals(this.STR[0])) {
                this.bs.setLabel(this.STR[1]);
                this.reset(true);
            }
            else if (s.equals(this.STR[1])) {
                this.running = true;
                this.bs.setLabel(this.STR[6]);
                this.start();
        	    timer.start();
        	    this.running = true;
            }
            else if (s.equals(this.STR[6])) {
                this.running = false;
                this.mb.setup(0.0, this.v, this.r0, 0.0);
                this.repaint();
                this.bs.setLabel(this.STR[1]);
            }
            else {
                if (s.equals(this.STR[4])) {
                    ++this.id;
                }
                else if (s.equals(this.STR[3])) {
                    --this.id;
                    if (this.id < 2) {
                        this.id = 2;
                    }
                }
                this.running = false;
                this.v = this.id * this.v0;
                this.mb.setup(0.0, this.v, this.r0, 0.0);
                this.repaint();
                this.bs.setLabel(this.STR[1]);
            }
        }
        if (event.target instanceof Checkbox) {
            this.state = !this.cb.getState();
        }
        return true;
    }
    
    public void reset(final boolean b) {
        this.clear();
        this.running = false;
        if (b) {
            final double n = 0.0;
            this.time = n;
            this.ts = n;
        }
        this.repaint();
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
	    	this.advanced(this.delta / 8000.0);
	    }
    }
    
    void advanced(double n) {
        this.time += n;
        this.ts += n;
        n /= 5.0;
        for (int i = 0; i < 5; ++i) {
            this.mb.advanced(n);
            this.gb.drawLine(this.xx = this.xc + (int)this.mb.getX(), this.yy = this.yc + (int)this.mb.getY(), this.xx, this.yy);
            if (this.state && this.mb.R() < this.Re) {
                this.running = false;
                this.bs.setLabel("Back");
                this.repaint();
                return;
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
            this.running = !this.running;
        }
        else if (Math.sqrt((n - this.xs) * (n - this.xs) + (n2 - this.ys) * (n2 - this.ys)) < 5.0) {
            this.xx = this.xc + (int)this.mb.getX();
            this.yy = this.yc + (int)this.mb.getY();
            this.dragging = true;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, int n2) {
        if ((n2 -= this.yOffset) < 0) {
            return false;
        }
        if (this.dragging) {
            this.mb.setupDrag((n - this.xx) * this.vcst, (n2 - this.yy) * this.vcst);
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
            this.xc = this.area.width / 2;
            this.yc = this.area.height / 2;
            this.Re = -8+((this.xc > this.yc) ? (this.yc / 2) : (this.xc / 2));
            this.De = 2 * this.Re;
            this.yc -= this.Re / 2 - 15;
            /*
            this.base = this.getCodeBase();
            this.earth = this.getImage(this.base, "earth.jpg");
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.earth, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
            */
            this.earth=edu.davidson.graphics.Util.getImage(imagedir+"earth.jpg",this);
            if(this.earth!=null) {
              this.height = this.earth.getHeight(this);
              this.width = this.earth.getWidth(this);
            }
        }
        this.gb.setColor(this.bgColor);
        this.gb.fillRect(0, 0, this.area.width, this.area.height);
        this.gb.setColor(Color.black);
        this.gb.drawRect(0, 0, this.area.width - 1, this.area.height - 1);
        this.gb.setColor(Color.black);
        this.gb.fillOval(this.xx = this.xc - this.w1, this.yy = this.yc - 3 * this.Re / 2, this.w2, this.Re);
        if(this.earth!=null)this.gb.drawImage(this.earth, this.xc - this.width / 2, this.yc - this.height / 2, this);
        this.gb.setColor(Color.white);
        this.gb.fillOval(this.xc - this.w0, this.yc - this.w0, this.w1, this.w1);
        final double n = 6.67E-11;
        final double n2 = 6374000.0;
        final double n3 = 5.976E24;
        this.rcst = n2 / (this.yc - this.yy);
        this.rcst2 = this.rcst / 1000.0;
        this.cst = this.rcst / this.tcst;
        this.mb.setGM(n * n3 * this.tcst * this.tcst / (this.rcst * this.rcst * this.rcst));
        this.v0 = Math.sqrt(n * n3 / n2) / this.cst / 10.0;
        this.id = 2;
        this.v = this.id * this.v0;
        this.r0 = this.yy - this.yc - this.w0;
        this.mb.setup(0.0, this.v, this.r0, 0.0);
        this.stry = 0;
        this.g.drawImage(this.bgImage, 0, 0, this);
        this.x1 = this.xc - this.w0;
        this.y1 = this.yc - this.w0;
    }
    
    protected void paintComponent_(Graphics g) {
    	//super.paintComponent_(g);
    	updateScreen(g);
    }
    
    public void updateScreen(final Graphics graphics) {
        this.g.drawImage(this.bgImage, 0, 0, this);
        this.g.setColor(Color.black);
        this.g.drawString(this.STR[2] + this.d2String(this.time * 3600.0), 100, this.chy);
        this.g.setColor(Color.green);
        this.g.fillOval(this.xx = this.x1 + (int)this.mb.getX(), this.yy = this.y1 + (int)this.mb.getY(), this.w1, this.w1);
        this.g.setColor(Color.red);
        this.g.drawString("V =" + this.d2String(this.mb.V()) + "m/s", 5, this.stry + this.chy);
        this.drawArrow2(this.xx += this.w0, this.yy += this.w0, this.mb.vx() / this.vcst, this.mb.vy() / this.vcst);
        this.xs = this.xx + (int)(this.mb.vx() / this.vcst);
        this.ys = this.yy + (int)(this.mb.vy() / this.vcst);
        this.g.setColor(Color.white);
        this.g.drawLine(this.xx, this.yy, this.xc, this.yc);
        this.g.drawString("x=" + this.d2String(this.mb.getX() * this.rcst2) + " km, y=" + this.d2String(-this.mb.getY() * this.rcst2) + " km", this.xc, this.chy);
        if (this.dragging) {
            this.g.setColor(Color.black);
            this.g.drawString("(Vx,Vy)=(" + this.d2String(this.mb.vx()) + " , " + this.d2String(-this.mb.vy()) + " )", 100, 2 * this.chy);
        }
        graphics.drawImage(this.fgImage, 0, this.yOffset, this);
    }
    
    String d2String(final double n) {
        return String.valueOf((int)(n * this.cst));
    }
    
    void drawArrow(int n, int n2, double n3, final double n4) {
        this.g.drawLine(n, n2, n += (int)(n3 * Math.cos(n4)), n2 += (int)(n3 * Math.sin(n4)));
        n3 *= 0.2;
        this.g.drawLine(n, n2, n - (int)(n3 * Math.cos(n4 + this.dc)), n2 - (int)(n3 * Math.sin(n4 + this.dc)));
        this.g.drawLine(n, n2, n - (int)(n3 * Math.cos(n4 - this.dc)), n2 - (int)(n3 * Math.sin(n4 - this.dc)));
    }
    
    void drawArrow2(final int n, final int n2, final double n3, final double n4) {
        double atan = 1.5707963267948966;
        if (n3 == 0.0) {
            if (n4 < 0.0) {
                atan = -atan;
            }
        }
        else {
            atan = Math.atan(n4 / n3);
            if (n3 < 0.0) {
                atan += 3.141592653589793;
            }
        }
        this.drawArrow(n, n2, Math.sqrt(n3 * n3 + n4 * n4), atan);
    }
    
    public ProjectileOrbit() {
        this.yOffset = 40;
        this.time = 0.0;
        this.ts = 0.0;
        this.bgColor = Color.lightGray;
        this.mb = new MovingProjectile();
        this.state = true;
        this.STR = new String[] { "Reset", "Start", "Time", "-", "+", "full", "Back" };
        this.id = 2;
        this.running = false;
        this.startTime = 0L;
        this.delay = 50L;
        this.rightClick = false;
        this.dragging = false;
        this.w1 = 6;
        this.w0 = this.w1 / 2;
        this.w2 = 2 * this.w1;
        this.v0 = 300.0;
        this.tcst = 3600.0;
        this.vcst = 10.0;
        this.dc = 0.5235987755982988;
    }
}
