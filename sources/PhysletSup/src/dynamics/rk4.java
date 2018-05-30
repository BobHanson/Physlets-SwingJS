package dynamics;

/*
 * Decompiled with CFR 0_123.
 */
abstract class rk4 {
    int n;
    public double[] dydx;
    public double[] y;
    public double[] yout;

    public void init(int n, boolean bl) {
        this.n = n;
        this.dydx = new double[this.n];
        this.yout = new double[this.n];
        if (bl) {
            this.y = this.yout;
            return;
        }
        this.y = new double[this.n];
    }

    public abstract void derivs(double var1, double[] var3, double[] var4);

    public void nextmove(double d, double[] arrd) {
        this.y = arrd;
        this.core(0.0, d);
    }

    public void nextmove(double d) {
        this.core(0.0, d);
    }

    private void core(double d, double d2) {
        double[] arrd = new double[this.n];
        double[] arrd2 = new double[this.n];
        double[] arrd3 = new double[this.n];
        double d3 = d2 * 0.5;
        double d4 = d2 / 6.0;
        double d5 = d + d3;
        this.derivs(d5, this.y, this.dydx);
        int n = 0;
        while (n < this.n) {
            arrd3[n] = this.y[n] + d3 * this.dydx[n];
            ++n;
        }
        this.derivs(d5, arrd3, arrd2);
        n = 0;
        while (n < this.n) {
            arrd3[n] = this.y[n] + d3 * arrd2[n];
            ++n;
        }
        this.derivs(d5, arrd3, arrd);
        n = 0;
        while (n < this.n) {
            arrd3[n] = this.y[n] + d2 * arrd[n];
            double[] arrd4 = arrd;
            int n2 = n;
            arrd4[n2] = arrd4[n2] + arrd2[n];
            ++n;
        }
        this.derivs(d + d2, arrd3, arrd2);
        n = 0;
        while (n < this.n) {
            this.yout[n] = this.y[n] + d4 * (this.dydx[n] + arrd2[n] + 2.0 * arrd[n]);
            ++n;
        }
    }

    rk4() {
    }
}

