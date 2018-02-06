package edu.davidson.display;

public interface SScalable{
    public double xFromPix(int xpix);
    public double yFromPix(int ypix);
    public int pixFromX(double x);
    public int pixFromY(double yx);
    public int getPixWidth();
    public int getPixHeight();
}