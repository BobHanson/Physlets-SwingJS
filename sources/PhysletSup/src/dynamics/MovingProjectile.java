package dynamics;

class MovingProjectile extends rk4
{
    double r3;
    double GM;
    double c0;
    private int count;
    
    void setup(final double n, final double n2, final double n3, final double n4) {
        super.y[0] = n;
        super.y[1] = n2;
        super.y[2] = n3;
        super.y[3] = n4;
        this.c0 = 10.0 * Math.abs(n3);
    }
    
    void setupDrag(final double n, final double n2) {
        super.y[1] = n;
        super.y[3] = n2;
    }
    
    void setGM(final double n) {
        this.GM = -n;
    }
    
    double getX() {
        return super.y[0];
    }
    
    double getY() {
        return super.y[2];
    }
    
    double vx() {
        return super.y[1];
    }
    
    double vy() {
        return super.y[3];
    }
    
    double V() {
        return Math.sqrt(super.y[1] * super.y[1] + super.y[3] * super.y[3]);
    }
    
    double R() {
        return Math.sqrt(super.y[0] * super.y[0] + super.y[2] * super.y[2]);
    }
    
    public void derivs(final double n, final double[] array, final double[] array2) {
        this.r3 = array[0] * array[0] + array[2] * array[2];
        this.r3 *= Math.sqrt(this.r3);
        array2[0] = array[1];
        array2[1] = this.GM * array[0] / this.r3;
        array2[2] = array[3];
        array2[3] = this.GM * array[2] / this.r3;
    }
    
    void advanced(double h) {
        this.count = (int)(this.c0 / this.R());
        h /= this.count;
        for (int i = 0; i < this.count; ++i) {
            this.nextmove(h);
        }
    }
    
    MovingProjectile() {
        this.GM = -980000.0;
        this.count = 20;
        this.init(4, true);
    }
}
