package dynamics;

// 
// Decompiled by Procyon v0.5.30
// 

class buoyantBlock extends rk4
{
    double aCst;
    double y0;
    
    void init(final int n, final int n2) {
        this.y0 = n2;
        super.y[0] = n;
        super.y[1] = 0.0;
        this.aCst = 1.0;
    }
    
    public void derivs(final double n, final double[] array, final double[] array2) {
        array2[0] = super.y[1];
        array2[1] = this.aCst * (this.y0 - super.y[0]);
    }
    
    buoyantBlock() {
        this.aCst = 1.0;
        this.init(2, true);
    }
}
