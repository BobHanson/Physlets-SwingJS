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

//public class BlockApplet extends a2s.Applet
public class BlockApplet extends  a2s.Applet
{
    TextField mouseP;
    Animation anim;
    Button cg;
    Dimension area;
    int cnt;
    Block[] b;
    double[] bc;
    Color bgColor;
    String rts;
    String[] STR;
    double offset;
    int xMax;
    int id;
    int xs;
    boolean moving;
    boolean cgStatus;
    Image offImage;
    Graphics g;
    int yoffset;
    
    public void init() {
    	this.setSize(700, 600);
        this.setBackground(this.bgColor);
        for (int i = 0; i < this.STR.length; ++i) {
            if ((this.rts = this.getParameter(this.STR[i])) != null) {
                this.STR[i] = new String(this.rts);
            }
        }
        final Panel panel = new Panel();
        panel.add(this.cg = new Button(this.STR[0]));
        panel.add(this.mouseP = new TextField("0 , 0", 10));
        panel.add(new Button(this.STR[2]));
        this.add("North", panel);
        this.setBackground(this.bgColor);
        for (int j = 0; j < this.cnt; ++j) {
            this.b[j] = new Block();
        }
        this.reset(true);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            final String s = (String)o;
            if (s.equals(this.STR[2])) {
                this.reset(true);
            }
            else if (s.equals(this.STR[1])) {
                this.cgState(false);
                this.cg.setLabel(this.STR[0]);
            }
            else if (s.equals(this.STR[0])) {
                this.cgState(true);
                this.cg.setLabel(this.STR[1]);
            }
        }
        return true;
    }
    
    public void reset(final boolean b) {
        this.area = this.size();
        final Dimension area = this.area;
        area.height -= this.yoffset;
        Block.width = 120;
        this.xMax = 0;
        for (int i = 1; i < this.cnt; ++i) {
            this.xMax += Block.width / i;
        }
        this.xMax /= 2;
        Block.height = this.area.height / (this.cnt + 1);
        this.offset = -Block.width / 2.0;
        int n = this.area.height - Block.height;
        if (b) {
            this.b[0].init(0, n);
            for (int j = 1; j < this.cnt; ++j) {
                n -= Block.height;
                this.b[j].init(0, n);
                this.bc[j] = this.offset;
            }
        }
        else {
            for (int k = 0; k < this.cnt; ++k, n -= Block.height) {
                this.b[k].changeY(n);
            }
        }
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int xs, int n) {
        if ((n -= this.yoffset) < 0) {
            return true;
        }
        this.xs = xs;
        this.id = 1;
        while (this.id < this.cnt) {
            if (this.b[this.id].inside(xs, n)) {
                return this.moving = true;
            }
            ++this.id;
        }
        this.id = 0;
        this.repaint();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int xs, int n) {
        if ((n -= this.yoffset) < 0) {
            return true;
        }
        final int n2 = xs - this.xs;
        if (this.moving) {
            int i;
            for (i = this.id; i < this.cnt; ++i) {
                this.b[i].moveX(n2);
            }
            --i;
            this.bc[i] = this.b[i].x + this.offset;
            for (int j = this.cnt - 2; j > 0; --j) {
                this.bc[j] = (this.bc[j + 1] * (this.cnt - j - 1) + this.b[j].x + this.offset) / (this.cnt - j);
            }
            this.writeText(this.xs = xs, n);
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, int n2) {
        if ((n2 -= this.yoffset) < 0) {
            return true;
        }
        if (this.moving) {
            this.repaint();
        }
        this.moving = false;
        return true;
    }
    
    void writeText(final int n, final int n2) {
        this.mouseP.setText(String.valueOf(n) + " , " + String.valueOf(this.b[0].y - n2));
    }
    
    public boolean mouseMove(final Event event, final int n, int n2) {
        if ((n2 -= this.yoffset) < 0) {
            return true;
        }
        this.writeText(n, n2);
        return true;
    }
    
    void cgState(final boolean cgStatus) {
        this.cgStatus = cgStatus;
        this.repaint();
    }
    
    protected void paintComponent_(Graphics g) {
    	//super.paintComponent(g);
    	updateScreen(g);
    }
    
    
    public void updateScreen(final Graphics graphics) {
        final int y = this.b[0].y;
        if (this.g == null) {
            this.offImage = this.createImage(this.area.width, this.area.height);
            this.g = this.offImage.getGraphics();
            Block.g = this.g;
        }
        this.g.setColor(this.getBackground());
        this.g.fillRect(0, 0, this.area.width, this.area.height);
        this.g.setColor(Color.black);
        this.g.drawRect(0, 0, this.area.width - 1, this.area.height - 1);
        final int n = Block.height / 5;
        final int n2 = Block.width / 10;
        this.g.fillRect(0, y, Block.width + 1, n);
        this.g.fillRect(n2, y + n, n, Block.height - n);
        this.g.fillRect(Block.width - 2 * n2, y + n, n, Block.height - n);
        this.g.drawString(String.valueOf(Block.width), Block.width, this.b[0].y + Block.height / 2);
        Color color = Color.green;
        for (int i = 1; i < this.cnt; ++i) {
            if (color == Color.green) {
                final double n3 = this.b[i - 1].x - this.bc[i];
                if (n3 > 0.0) {
                    color = Color.green;
                }
                else if (n3 == 0.0) {
                    color = Color.yellow;
                }
                else {
                    color = Color.red;
                }
            }
            this.b[i].draw(color);
        }
        for (int j = this.cnt - 1; j > 0; --j) {
            if (this.cgStatus) {
                final int n4 = (int)this.bc[j] + Block.width;
                final int n5 = (this.b[this.cnt - 1].y + this.b[j].y + Block.height) / 2;
                this.g.setColor(Color.black);
                this.g.drawOval(n4 - 2, n5 - 2, 4, 4);
                final int n6 = n5 + (this.cnt - j) * Block.height / 2;
                this.g.drawLine(n4, n6, n4, n6 - (this.cnt - j) * Block.height / 2);
                this.g.drawLine(n4, n6, n4 - 3, n6 - 3);
                this.g.drawLine(n4, n6, n4 + 3, n6 - 3);
                this.g.drawString(this.d2String(this.bc[j] + Block.width), n4, n6);
            }
        }
        double n7 = this.b[this.cnt - 1].x / this.xMax;
        if (n7 == 1.0 && color != Color.red) {
            this.g.setColor(Color.red);
            final int n8 = this.b[1].x + Block.width + 30;
            final int n9 = this.b[0].y - 10;
            this.g.setColor(Color.blue);
            this.g.drawOval(n8, n9, 2, 2);
            final int n10 = n8 - 10;
            this.g.drawOval(n10, n9, 2, 2);
            this.g.drawArc(n10, n9, 12, 12, -132, 92);
            this.g.drawString(this.STR[3], n10 + 20, n9 + 10);
        }
        if (color == Color.red) {
            n7 = 0.0;
        }
        this.g.drawString(this.d2String(n7 * 100.0) + "%", this.b[this.cnt - 1].x + Block.width + 5, this.b[this.cnt - 1].y);
        graphics.drawImage(this.offImage, 0, this.yoffset, this);
    }
    
    String d2String(final double n) {
        String s = String.valueOf((float)((int)(100.0 * n) / 100.0));
        if (s.indexOf(".") == -1) {
            s += ".0";
        }
        return s;
    }
    
    public BlockApplet() {
        this.cnt = 5;
        this.b = new Block[this.cnt];
        this.bc = new double[this.cnt];
        this.bgColor = new Color(200, 223, 208);
        this.STR = new String[] { "SHOW", "HIDE", "RESET", "MSG" };
        this.id = 0;
        this.xs = 0;
        this.moving = false;
        this.cgStatus = false;
        this.g = null;
        this.yoffset = 40;
    }
}
