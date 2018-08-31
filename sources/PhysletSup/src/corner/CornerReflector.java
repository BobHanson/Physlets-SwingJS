package corner;

import java.awt.Event;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import a2s.*;
import edu.davidson.tools.SApplet;

//
// Decompiled by Procyon v0.5.30
//

public class CornerReflector extends Applet
{
    Dimension area;
    Image bgImage;
    Image fgImage;
    Graphics gb;
    Graphics g;
    Color bgColor;
    String rts;
    String[] STR;
    int xx;
    int yy;
    int xs2;
    int ys2;
    long startTime;
    long lastTime;
    long delay;
    long delta;
    double c;
    int cnt;
    int ns;
    double xt;
    double yt;
    double bt;
    boolean rightClick;
    boolean dragm;
    boolean dragd;
    boolean dragd2;
    boolean down;
    int xs;
    int ys;
    double cta1;
    double cta2;
    double ctaa;
    double dd;
    double PI2;
    double m;
    int ni;
    int nf;
    int n;
    double V;
    double dc2;
    FontMetrics fm;
    int chy;
    int xc;
    int yc;
    int size;
    int size2;
    int xm;
    int ym;
    int ym2;
    int Nmax;
    int NR;
    int Nmax1;
    double cta;
    double rm;
    double dc;
    double[] CR;
    double[] X;
    double[] Y;
    double[] VX;
    double[] VY;
    double cst;
    int NI;
    double[] PXA;
    double[] PYA;
    double[] PXB;
    double[] PYB;
    Point na;
    Point nb;

    public void init() {
        for (int i = 0; i < this.STR.length; ++i) {
            if ((this.rts = this.getParameter(this.STR[i])) != null) {
                this.STR[i] = new String(this.rts);
            }
        }
        this.setBackground(this.bgColor);
        this.area = this.size();
        this.reset();
    }

    public void reset() {
        this.xs = this.xc / 2;
        this.ys = this.yc;
        this.xs2 = this.xs - 15;
        this.ys2 = this.ys;
        this.clear();
        this.setupRay();
    }
    
    public void start() {
    	clear();
    	repaint();
    	//System.out.println("start");
    }


    void advanced(final double n) {
        this.gb.setColor(Color.yellow);
        if (this.cnt < this.n) {
            for (int i = 0; i < this.ns; ++i) {
                this.xx = (int)this.X[i];
                this.yy = (int)this.Y[i];
                final double[] x = this.X;
                final int n2 = i;
                x[n2] += this.VX[i] * n;
                final double[] y = this.Y;
                final int n3 = i;
                y[n3] += this.VY[i] * n;
                if (this.X[i] > this.xm) {
                    if (this.Y[i] < this.yc) {
                        if (this.Y[i] > this.ym && (this.Y[i] - this.ym) / (this.X[i] - this.xm) < this.m) {
                            final double[] x2 = this.X;
                            final int n4 = i;
                            x2[n4] -= this.VX[i] * n;
                            final double[] y2 = this.Y;
                            final int n5 = i;
                            y2[n5] -= this.VY[i] * n;
                            this.c = 2.0 * this.cta - this.CR[i];
                            this.VX[i] = this.V * Math.cos(this.c);
                            this.VY[i] = this.V * Math.sin(this.c);
                            if (this.n < this.Nmax1) {
                                this.X[this.n] = this.X[i];
                                this.Y[this.n] = this.Y[i];
                                this.VX[this.n] = -this.VX[i];
                                this.VY[this.n] = -this.VY[i];
                                ++this.n;
                            }
                            this.CR[i] = this.c;
                        }
                    }
                    else if (this.Y[i] > this.yc) {
                        if (this.ym2 > this.Y[i] && (this.ym2 - this.Y[i]) / (this.X[i] - this.xm) < this.m) {
                            final double[] x3 = this.X;
                            final int n6 = i;
                            x3[n6] -= this.VX[i] * n;
                            final double[] y3 = this.Y;
                            final int n7 = i;
                            y3[n7] -= this.VY[i] * n;
                            this.c = -2.0 * this.cta - this.CR[i];
                            this.VX[i] = this.V * Math.cos(this.c);
                            this.VY[i] = this.V * Math.sin(this.c);
                            if (this.n < this.Nmax1) {
                                this.X[this.n] = this.X[i];
                                this.Y[this.n] = this.Y[i];
                                this.VX[this.n] = -this.VX[i];
                                this.VY[this.n] = -this.VY[i];
                                ++this.n;
                            }
                            this.CR[i] = this.c;
                        }
                    }
                    else if (this.X[i] > this.xc) {
                        this.X[i] = 2 * this.xc - this.X[i];
                        this.VX[i] = -this.VX[i];
                    }
                }
                this.gb.drawLine(this.xx, this.yy, this.xx, this.yy);
                if (this.Y[i] < 0.0 || this.Y[i] > this.area.height || this.X[i] < 0.0) {
                    this.Y[i] = 0.0;
                    this.X[i] = 0.0;
                    this.VX[i] = 0.0;
                    this.VY[i] = 0.0;
                    ++this.cnt;
                }
            }
            this.gb.setColor(Color.gray);
            for (int j = this.ns; j < this.n; ++j) {
                final double[] x4 = this.X;
                final int n8 = j;
                x4[n8] += this.VX[j] * n;
                final double[] y4 = this.Y;
                final int n9 = j;
                y4[n9] += this.VY[j] * n;
                if (this.X[j] > this.area.width || this.Y[j] < 0.0 || this.Y[j] > this.area.height) {
                    ++this.cnt;
                    this.X[j] = 0.0;
                    this.Y[j] = 0.0;
                    this.VX[j] = 0.0;
                    this.VY[j] = 0.0;
                }
                this.gb.drawLine(this.xx = (int)this.X[j], this.yy = (int)this.Y[j], this.xx, this.yy);
            }
        }
        this.repaint();
    }

    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.down = true;
        if (event.modifiers == 4) {
            this.rightClick = true;
        }
        else {
            this.rightClick = false;
        }
        if (Math.sqrt((n - this.xm) * (n - this.xm) + (n2 - this.ym) * (n2 - this.ym)) < this.size2) {
            this.dragm = true;
        }
        else if (Math.sqrt((n - this.xs) * (n - this.xs) + (n2 - this.ys) * (n2 - this.ys)) < this.size2) {
            this.dragd = true;
            this.clear();
        }
        else if (Math.sqrt((n - this.xs2) * (n - this.xs2) + (n2 - this.ys2) * (n2 - this.ys2)) < this.size2) {
            this.dragd2 = true;
            this.clear();
        }
        this.repaint();
        return true;
    }

    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.dragm && n2 < this.yc - 10 && n <= this.xc) {
            final double m = (this.yc - n2) / (this.xc - n + this.dd);
            this.m = m;
            this.ctaa = Math.atan(m);
            if (this.m < Math.abs((this.yc - this.ys) / (this.xc - this.xs + this.dd)) || this.m < Math.abs((this.yc - this.ys2) / (this.xc - this.xs2 + this.dd))) {
                return true;
            }
            this.cta = this.ctaa;
            this.clear();
        }
        else if (this.dragd && n2 > this.ym && n2 < this.ym2 && n < this.xc - 10) {
            this.cta1 = Math.atan((n2 - this.ym) / (this.xm - n + this.dd));
            if (n > this.xm && this.cta1 < 0.0) {
                if (this.cta + this.cta1 > 0.0) {
                    return true;
                }
                this.cta1 += 3.141592653589793;
            }
            this.cta2 = Math.atan((n2 - this.ym2) / (this.xm - n + this.dd));
            if (n > this.xm && this.cta2 > 0.0) {
                if (this.cta2 - this.cta < 0.0) {
                    return true;
                }
                this.cta2 -= 3.141592653589793;
            }
            this.xs = n;
            this.ys = n2;
        }
        else if (this.dragd2 && n2 > this.ym && n2 < this.ym2 && n < this.xc - 10) {
            this.cta1 = Math.atan((n2 - this.ym) / (this.xm - n + this.dd));
            if (n > this.xm && this.cta1 < 0.0) {
                if (this.cta + this.cta1 > 0.0) {
                    return true;
                }
                this.cta1 += 3.141592653589793;
            }
            this.cta2 = Math.atan((n2 - this.ym2) / (this.xm - n + this.dd));
            if (n > this.xm && this.cta2 > 0.0) {
                if (this.cta2 - this.cta < 0.0) {
                    return true;
                }
                this.cta2 -= 3.141592653589793;
            }
            this.xs2 = n;
            this.ys2 = n2;
        }
        this.repaint();
        return true;
    }

    void setupRay() {
        this.m = (this.yc - this.ym) / (this.xc - this.xm + this.dd);
        this.cta1 = Math.atan((this.ys - this.ym) / (this.xm - this.xs + this.dd));
        if (this.xs > this.xm && this.cta1 < 0.0) {
            this.cta1 += 3.141592653589793;
        }
        this.cta2 = Math.atan((this.ys - this.ym2) / (this.xm - this.xs + this.dd));
        if (this.xs > this.xm && this.cta2 > 0.0) {
            this.cta2 -= 3.141592653589793;
        }
        this.ni = this.NR / 2 + (int)(this.cta2 / this.dc);
        this.nf = this.NR / 2 + (int)(this.cta1 / this.dc);
        final int n = this.nf - this.ni + 1;
        this.n = n;
        this.ns = n;
        for (int i = 0; i < this.n; ++i) {
            this.CR[i] = (this.ni + i) * this.dc - 3.141592653589793;
            this.X[i] = this.xs;
            this.Y[i] = this.ys;
            this.VX[i] = this.V * Math.cos(this.CR[i]);
            this.VY[i] = this.V * Math.sin(this.CR[i]);
        }
        this.cnt = 0;
        this.repaint();
    }

    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.dragd || this.dragd2) {
            this.clear();
            this.setupRay();
        }
        this.dragm = false;
        this.dragd = false;
        this.dragd2 = false;
        this.down = false;
        if (!this.rightClick) {
            this.repaint();
        }
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
            this.rm = this.xc / 1.5;
            this.xs = this.xc / 2;
            this.ys = this.yc;
            this.xs2 = this.xs - 15;
            this.ys2 = this.ys;
        }
        this.gb.setColor(this.bgColor);
        this.gb.fillRect(0, 0, this.area.width, this.area.height);
        this.gb.setColor(Color.black);
        this.gb.drawLine(this.xc, this.yc, this.xm = this.xc - (int)(this.rm * Math.cos(this.cta)), this.ym = this.yc - (int)(this.rm * Math.sin(this.cta)));
        this.gb.drawLine(this.xc, this.yc, this.xm, this.ym2 = this.area.height - this.ym);
        this.gb.setColor(Color.gray);
        this.gb.fillOval(this.xc - this.size, this.yc - this.size, this.size2, this.size2);
        this.gb.setColor(Color.green);
        this.gb.drawOval(this.xm - this.size, this.ym - this.size, this.size2, this.size2);
        this.setupRay();
        this.gb.setColor(Color.black);
        this.gb.drawString(this.STR[0] + " =" + (int)(this.cta * 2.0 * this.cst + 0.5), this.xc + 5, this.yc + this.chy / 3);
        this.repaint();
    }
    
    protected void paintComponent_(Graphics g) {
    	super.paintComponent_(g);
    	updateScreen(g);
    }

    void imgs(final double n, final double n2, final double[] array, final double[] array2, final Point point) {
        final double n3 = 3.141592653589793 - this.cta - this.dd;
        final double n4 = -n3;
        double n5 = n;
        double n6 = n2;
        final double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
        point.x = 0;
        double n7;
        do {
            double atan = Math.atan(n6 / n5);
            if (n5 < 0.0) {
                if (n6 > 0.0) {
                    atan += 3.141592653589793;
                }
                else {
                    atan -= 3.141592653589793;
                }
            }
            if (atan < this.cta) {
                n7 = 2.0 * this.cta - atan;
            }
            else {
                n7 = -2.0 * this.cta - atan;
            }
            n5 = sqrt * Math.cos(n7);
            n6 = sqrt * Math.sin(n7);
            array[point.x] = this.xc - (int)n5;
            array2[point.x] = this.yc - (int)n6;
            if(point.x<this.NI-1)++point.x;
        } while (n7 < n3 && n7 > n4);
        double n8 = n;
        double n9 = n2;
        point.y = Math.abs(point.x);
        double n10;
        do {
            double atan2 = Math.atan(n9 / n8);
            if (n8 < 0.0) {
                if (n9 > 0.0) {
                    atan2 += 3.141592653589793;
                }
                else {
                    atan2 -= 3.141592653589793;
                }
            }
            if (atan2 > -this.cta) {
                n10 = -2.0 * this.cta - atan2;
            }
            else {
                n10 = 2.0 * this.cta - atan2;
            }
            n8 = sqrt * Math.cos(n10);
            n9 = sqrt * Math.sin(n10);
            array[point.y] = this.xc - (int)n8;
            array2[point.y] = this.yc - (int)n9;
            if(point.y<this.NI-1)++point.y;
        } while (n10 < n3 && n10 > n4);
    }

    public void updateScreen(final Graphics graphics) {
        this.g.drawImage(this.bgImage, 0, 0, this);
        this.g.setColor(Color.yellow);
        this.g.fillOval(this.xs - this.size, this.ys - this.size, this.size2, this.size2);
        this.imgs(this.xc - this.xs, this.yc - this.ys, this.PXA, this.PYA, this.na);
        this.imgs(this.xc - this.xs2, this.yc - this.ys2, this.PXB, this.PYB, this.nb);
        for (int i = 0; i < this.na.y; ++i) {
            this.g.drawOval((int)this.PXA[i] - this.size, (int)this.PYA[i] - this.size, this.size2, this.size2);
        }
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        if (this.na.x > this.nb.x) {
            n = this.nb.x;
            final int y = this.na.y;
            n2 = 1;
            n4 = 1;
        }
        else if (this.na.x != this.nb.x) {
            n = this.na.x;
            final int y2 = this.nb.y;
            n3 = 1;
            n4 = 1;
        }
        this.g.setColor(Color.blue);
        this.g.drawLine(this.xs, this.ys, this.xs2, this.ys2);
        for (int j = 0; j < n; ++j) {
            this.g.drawLine((int)this.PXA[j], (int)this.PYA[j], (int)this.PXB[j], (int)this.PYB[j]);
        }
        for (int k = n; k < this.na.y - n4; ++k) {
            if (this.PXB[k + n3] > 0.0) {
                this.g.drawLine((int)this.PXA[k + n2], (int)this.PYA[k + n2], (int)this.PXB[k + n3], (int)this.PYB[k + n3]);
            }
        }
        this.g.setColor(Color.white);
        if (this.cnt < this.n) {
            for (int l = 0; l < this.n; ++l) {
                this.g.drawOval(this.xx = (int)this.X[l] - 1, this.yy = (int)this.Y[l] - 1, 2, 2);
            }
        }
        this.g.setColor(Color.red);
        this.g.drawOval(this.xs - this.size, this.ys - this.size, this.size2, this.size2);
        if (this.down) {
            this.g.setColor(Color.darkGray);
            this.g.drawLine(this.xc, this.yc, 2 * this.xc - this.xm, 2 * this.yc - this.ym);
            this.g.drawLine(this.xc, this.yc, 2 * this.xc - this.xm, this.ym);
            this.g.setColor(Color.orange);
            this.g.drawLine(this.xs, this.ys, (int)this.PXA[0], (int)this.PYA[0]);
            for (int n5 = 1; n5 < this.na.x; ++n5) {
                this.g.drawLine((int)this.PXA[n5 - 1], (int)this.PYA[n5 - 1], (int)this.PXA[n5], (int)this.PYA[n5]);
            }
            this.g.setColor(Color.pink);
            this.g.drawLine(this.xs, this.ys, (int)this.PXA[this.na.x], (int)this.PYA[this.na.x]);
            for (int n6 = this.na.x + 1; n6 < this.na.y; ++n6) {
                this.g.drawLine((int)this.PXA[n6 - 1], (int)this.PYA[n6 - 1], (int)this.PXA[n6], (int)this.PYA[n6]);
            }
        }
        graphics.drawImage(this.fgImage, 0, 0, this);
    }

    public CornerReflector() {
        this.bgColor = Color.lightGray;
        this.STR = new String[] { "angle", "Start", "Time" };
        this.startTime = 0L;
        this.delay = 50L;
        this.rightClick = false;
        this.dragm = false;
        this.dragd = false;
        this.dragd2 = false;
        this.down = false;
        this.dd = 0.001;
        this.PI2 = 1.5707963267948966;
        this.V = 20.0;
        this.size = 4;
        this.size2 = 2 * this.size;
        this.Nmax = 200;
        this.NR = 44;
        this.Nmax1 = this.Nmax - 1;
        this.cta = this.PI2 * 2.0 / 3.0;
        this.dc = 6.283185307179586 / this.NR;
        this.CR = new double[this.Nmax];
        this.X = new double[this.Nmax];
        this.Y = new double[this.Nmax];
        this.VX = new double[this.Nmax];
        this.VY = new double[this.Nmax];
        this.cst = 57.29577951308232;
        this.NI = 60;
        this.PXA = new double[this.NI];
        this.PYA = new double[this.NI];
        this.PXB = new double[this.NI];
        this.PYB = new double[this.NI];
        this.na = new Point(0, 0);
        this.nb = new Point(0, 0);
    }
}
