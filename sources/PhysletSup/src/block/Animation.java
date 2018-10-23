package block;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.*;


// 
// Decompiled by Procyon v0.5.30
// 

class Animation extends Canvas
{
    Dimension area;
    Control p;
    int cnt;
    Block[] b;
    double[] bc;
    double offset;
    int xMax;
    int id;
    int xs;
    boolean moving;
    boolean cgStatus;
    Dimension offDimension;
    Image offImage;
    Graphics g;
    
    Animation(final MainWindow m) {
        this.cnt = 5;
        this.b = new Block[this.cnt];
        this.bc = new double[this.cnt];
        m.add("North", this.p = new Control(this));
        this.setBackground(Color.white);
        this.init();
    }
    
    public void reset(final boolean clean) {
        this.area = this.size();
        Block.width = 120;
        this.xMax = 0;
        for (int i = 1; i < this.cnt; ++i) {
            this.xMax += Block.width / i;
        }
        this.xMax /= 2;
        Block.height = this.area.height / (this.cnt + 1);
        this.offset = -Block.width / 2.0;
        int y0 = this.area.height - Block.height;
        if (clean) {
            this.b[0].init(0, y0);
            for (int i = 1; i < this.cnt; ++i) {
                y0 -= Block.height;
                this.b[i].init(0, y0);
                this.bc[i] = this.offset;
            }
        }
        else {
            for (int i = 0; i < this.cnt; ++i, y0 -= Block.height) {
                this.b[i].changeY(y0);
            }
        }
        this.repaint();
    }
    
    public void paint(final Graphics gs) {
        this.update(gs);
    }
    
    public boolean mouseUp(final Event e, final int x, final int y) {
        if (this.moving) {
            this.repaint();
        }
        this.moving = false;
        return true;
    }
    
    void writeText(final int x, final int y) {
        this.p.mouseP.setText(String.valueOf(x) + " , " + String.valueOf(this.b[0].y - y));
    }
    
    public void update(final Graphics gs) {
        final int y0 = this.b[0].y;
        if (this.g == null || this.area.width != this.offDimension.width || this.area.height != this.offDimension.height) {
            this.offDimension = this.area;
            this.offImage = this.createImage(this.area.width, this.area.height);
            this.g = this.offImage.getGraphics();
            Block.g = this.g;
        }
        this.g.setColor(this.getBackground());
        this.g.fillRect(0, 0, this.area.width, this.area.height);
        this.g.setColor(Color.black);
        final int dy = Block.height / 5;
        final int dx = Block.width / 10;
        this.g.fillRect(0, y0, Block.width + 1, dy);
        this.g.fillRect(dx, y0 + dy, dy, Block.height - dy);
        this.g.fillRect(Block.width - 2 * dx, y0 + dy, dy, Block.height - dy);
        this.g.drawString(String.valueOf(Block.width), Block.width, this.b[0].y + Block.height / 2);
        Color fillColor = Color.green;
        for (int i = 1; i < this.cnt; ++i) {
            if (fillColor == Color.green) {
                final double tmp = this.b[i - 1].x - this.bc[i];
                if (tmp > 0.0) {
                    fillColor = Color.green;
                }
                else if (tmp == 0.0) {
                    fillColor = Color.yellow;
                }
                else {
                    fillColor = Color.red;
                }
            }
            this.b[i].draw(fillColor);
        }
        for (int i = this.cnt - 1; i > 0; --i) {
            if (this.cgStatus) {
                final int xx = (int)this.bc[i] + Block.width;
                int yy = (this.b[this.cnt - 1].y + this.b[i].y + Block.height) / 2;
                this.g.setColor(Color.black);
                this.g.drawOval(xx - 2, yy - 2, 4, 4);
                yy += (this.cnt - i) * Block.height / 2;
                this.g.drawLine(xx, yy, xx, yy - (this.cnt - i) * Block.height / 2);
                this.g.drawLine(xx, yy, xx - 3, yy - 3);
                this.g.drawLine(xx, yy, xx + 3, yy - 3);
                this.g.drawString(String.valueOf(this.bc[i] + Block.width), xx, yy);
            }
        }
        double fraction = this.b[this.cnt - 1].x / this.xMax;
        if (fraction == 1.0 && fillColor != Color.red) {
            this.g.setColor(Color.red);
            int xx = this.b[1].x + Block.width + 30;
            int yy = this.b[0].y - 10;
            this.g.setColor(Color.black);
            this.g.drawOval(xx, yy, 2, 2);
            xx -= 10;
            this.g.drawOval(xx, yy, 2, 2);
            this.g.drawArc(xx, yy, 12, 12, -132, 92);
            xx += 20;
            yy += 10;
            this.g.drawString("You did it!", xx, yy);
        }
        if (fillColor == Color.red) {
            fraction = 0.0;
        }
        this.g.drawString(this.d2String(fraction * 100.0) + "%", this.b[this.cnt - 1].x + Block.width + 5, this.b[this.cnt - 1].y);
        gs.drawImage(this.offImage, 0, 0, this);
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        this.xs = x;
        this.id = 1;
        while (this.id < this.cnt) {
            if (this.b[this.id].inside(x, y)) {
                return this.moving = true;
            }
            ++this.id;
        }
        this.id = 0;
        this.repaint();
        return true;
    }
    
    void init() {
        for (int i = 0; i < this.cnt; ++i) {
            this.b[i] = new Block();
        }
    }
    
    void cgState(final boolean s) {
        this.cgStatus = s;
        this.repaint();
    }
    
    public boolean mouseDrag(final Event e, final int x, final int y) {
        final int dx = x - this.xs;
        if (this.moving) {
            int i;
            for (i = this.id; i < this.cnt; ++i) {
                this.b[i].moveX(dx);
            }
            --i;
            this.bc[i] = this.b[i].x + this.offset;
            for (i = this.cnt - 2; i > 0; --i) {
                this.bc[i] = (this.bc[i + 1] * (this.cnt - i - 1) + this.b[i].x + this.offset) / (this.cnt - i);
            }
            this.writeText(this.xs = x, y);
            this.repaint();
        }
        return true;
    }
    
    String d2String(final double d) {
        final float d2 = (float)((int)(100.0 * d) / 100.0);
        String str = String.valueOf(d2);
        if (str.indexOf(".") == -1) {
            str += ".0";
        }
        return str;
    }
    
    public boolean mouseMove(final Event e, final int x, final int y) {
        this.writeText(x, y);
        return true;
    }
}
