package dynamics;

// 
// Decompiled by Procyon v0.5.30
// 

class SFront
{
    double x0;
    double y0;
    double x;
    double y;
    double vx;
    double vy;
    boolean on;
    
    void setup(final double n, final double n2, final double vx, final double vy) {
        this.x = n;
        this.x0 = n;
        this.y = n2;
        this.y0 = n2;
        this.vx = vx;
        this.vy = vy;
        this.on = true;
    }
    
    void moving(final double n) {
        this.x += this.vx * n;
        this.y += this.vy * n;
    }
}
