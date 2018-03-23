package fermat;

// 
// Decompiled by Procyon v0.5.30
// 

class ray
{
    static double t;
    static double n;
    static double ds;
    static double yc;
    static double v0;
    static int xl;
    static int yl;
    static int xr;
    static int yr;
    static int xs;
    static int ys;
    static boolean type;
    static int id;
    double x;
    double y;
    double vx;
    double vy;
    double x2;
    double y2;
    double vx2;
    double vy2;
    boolean mode;
    double v;
    double cta;
    
    static void setup(final boolean type, final double n, final int n2, final int xs, final int ys, final int xl, final int yl, final int xr, final int yr) {
        ray.type = type;
        ray.v0 = n / ray.n;
        ray.yc = n2;
        ray.xs = xs;
        ray.ys = ys;
        ray.xl = xl;
        ray.yl = yl;
        ray.xr = xr;
        ray.yr = yr;
        ray.id = 0;
    }
    
    static void change() {
        ray.n = 1.0 / ray.n;
    }
    
    void advanced(final double n) {
        this.x += this.vx * n;
        this.y += this.vy * n;
        if (this.mode) {
            if (this.y > ray.yc) {
                this.mode = false;
                this.y = ray.yc;
                ray.t = (this.y - ray.yc) / this.vy;
                this.x -= this.vx * ray.t;
                if (ray.type) {
                    this.cta = Math.atan((ray.xl - this.x) / (ray.yc - ray.yl));
                    this.v = Math.sqrt(this.vx * this.vx + this.vy * this.vy);
                    this.vx = this.v * Math.sin(this.cta);
                    this.vy = -this.v * Math.cos(this.cta);
                }
                else {
                    this.cta = Math.atan((this.x - ray.xs) / (ray.yc - ray.ys));
                    this.vy = -this.vy;
                }
                this.v = ray.v0;
                this.x2 = this.x;
                this.y2 = ray.yc;
                if (ray.type) {
                    this.cta = Math.atan((ray.xr - this.x) / (ray.yr - ray.yc));
                }
                else {
                    final double n2 = Math.sin(this.cta) / ray.n;
                    if (Math.abs(n2) < 1.0) {
                        this.cta = Math.asin(n2);
                    }
                    else {
                        this.v = 0.0;
                    }
                }
                this.vx2 = this.v * Math.sin(this.cta);
                this.vy2 = this.v * Math.cos(this.cta);
                this.advanced(ray.t);
            }
        }
        else {
            this.x2 += this.vx2 * n;
            this.y2 += this.vy2 * n;
            ray.ds = this.v * n;
            if (this.y < 0.0) {
                ray.id = 3;
            }
            else if (ray.id < 3) {
                if (ray.id != 1 && Math.sqrt((this.x - ray.xl) * (this.x - ray.xl) + (this.y - ray.yl) * (this.y - ray.yl)) < ray.ds) {
                    ++ray.id;
                }
                if (ray.id != 2 && Math.sqrt((this.x2 - ray.xr) * (this.x2 - ray.xr) + (this.y2 - ray.yr) * (this.y2 - ray.yr)) < ray.ds) {
                    ray.id += 2;
                }
            }
        }
    }
    
    ray(final int n, final int n2, final double vx, final double vy) {
        this.mode = true;
        this.mode = true;
        this.x = n;
        this.y = n2;
        this.vx = vx;
        this.vy = vy;
    }
    
    static {
        ray.n = 1.33;
        ray.type = true;
    }
}
