package block;

import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Block {
    static int width;
    static int height;
    static Graphics g;
    int x;
    int y;
    
    void init(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    void changeY(final int y) {
        this.y = y;
    }
    
    boolean inside(final int n, final int n2) {
        return n > this.x && n < this.x + Block.width && n2 > this.y && n2 < this.y + Block.height;
    }
    
    boolean moveX(final int n) {
        if (this.x + n < 0) {
            return false;
        }
        this.x += n;
        return true;
    }
    
    public void draw(final Color color) {
        final int n = -2;
        Block.g.setColor(Color.black);
        Block.g.drawRect(this.x, this.y, Block.width, Block.height);
        Block.g.setColor(Color.red);
        Block.g.drawString(String.valueOf(this.x), this.x, this.y + n);
        Block.g.setColor(color);
        Block.g.fillRect(this.x + 1, this.y + 1, Block.width - 1, Block.height - 1);
        Block.g.setColor(Color.blue);
        Block.g.fillOval(this.x + Block.width / 2 - 2, this.y + Block.height / 2 - 2, 4, 4);
        Block.g.drawString(String.valueOf(this.x + Block.width / 2), this.x + Block.width / 2, this.y + Block.height / 2 + n);
    }
}
