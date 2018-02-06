package animator4;

import java.awt.*;

import edu.davidson.numerics.Parser;


public class Arrow extends Thing {

    private double[] arrowVars = new double[12];
    private double[] arrow     = new double[2];
    private String   hStr;    // the horz and vert components of the vector
    private String   vStr;
    private boolean  filled = false;
    Parser           hFunc;
    Parser           vFunc;
    int thickness=1;

    public Arrow(AnimatorCanvas o, int s, String h, String v, String xStr,
                 String yStr) {

        super(o, xStr, yStr);

        varStrings = new String[] {
            "t", "x", "y", "vx", "vy", "ax", "ay", "m", "horz", "vert","w", "h"
        };
        ds         = new double[1][12];    // the datasource state variables t,x,y,vx,vy,ax,ay;
        this.s     = s;    // the size will be the size of the head.
        hStr       = h;    // the h component of the arrow
        vStr       = v;
        hFunc      = new Parser(12);

        hFunc.defineVariable(1, "t");      // define the variable
        hFunc.defineVariable(2, "x");      // define the variable
        hFunc.defineVariable(3, "y");      // define the variable
        hFunc.defineVariable(4, "vx");     // define the variable
        hFunc.defineVariable(5, "vy");     // define the variable
        hFunc.defineVariable(6, "ax");     // define the variable
        hFunc.defineVariable(7, "ay");     // define the variable
        hFunc.defineVariable(8, "m");      // define the variable
        hFunc.defineVariable(9, "fx");     // define the variable
        hFunc.defineVariable(10, "fy");    // define the variable
        hFunc.defineVariable(11, "w");     // define the variable
        hFunc.defineVariable(12, "h");    // define the variable
        hFunc.define(hStr);
        hFunc.parse();

        if (hFunc.getErrorCode() != Parser.NO_ERROR) {
            System.out
                .println("Failed to parse horzizontal component of vector: "
                         + hStr);
            System.out.println("Parse error: " + hFunc.getErrorString()
                               + " at function 1, position "
                               + hFunc.getErrorPosition());

            return;
        }

        vFunc = new Parser(12);

        vFunc.defineVariable(1, "t");      // define the variable
        vFunc.defineVariable(2, "x");      // define the variable
        vFunc.defineVariable(3, "y");      // define the variable
        vFunc.defineVariable(4, "vx");     // define the variable
        vFunc.defineVariable(5, "vy");     // define the variable
        vFunc.defineVariable(6, "ax");     // define the variable
        vFunc.defineVariable(7, "ay");     // define the variable
        vFunc.defineVariable(8, "m");      // define the variable
        vFunc.defineVariable(9, "fx");     // define the variable
        vFunc.defineVariable(10, "fy");    // define the variable
        vFunc.defineVariable(11, "w");     // define the variable
        vFunc.defineVariable(12, "h");    // define the variable
        vFunc.define(vStr);
        vFunc.parse();

        if (vFunc.getErrorCode() != Parser.NO_ERROR) {
            System.out
                .println("Failed to parse vertical component of vector: "
                         + vStr);
            System.out.println("Parse error: " + vFunc.getErrorString()
                               + " at function 1, position "
                               + vFunc.getErrorPosition());

            return;
        }
    }

    void setArrow() {

        for (int i = 0; i < 8; i++) {
            arrowVars[i] = vars[i];
        }

        if ((myMaster != null) &&!dynamic) {
            arrowVars[8] = myMaster.getTotalFx();
            arrowVars[9] = myMaster.getTotalFy();
        } else {
            arrowVars[8] = getTotalFx();
            arrowVars[9] = getTotalFy();
        }

        try {
            arrow[0] = hFunc.evaluate(arrowVars);
            arrow[1] = vFunc.evaluate(arrowVars);
        } catch (Exception e) {
            arrow[0] = 0;
            arrow[1] = 0;
        }
    }

    double getHorz() {

        double h = 0;

        for (int i = 0; i < 8; i++) {
            arrowVars[i] = vars[i];
        }

        if ((myMaster != null) &&!dynamic) {
            arrowVars[8] = myMaster.getTotalFx();
            arrowVars[9] = myMaster.getTotalFy();
        } else {
            arrowVars[8] = getTotalFx();
            arrowVars[9] = getTotalFy();
        }

        try {
            h = hFunc.evaluate(arrowVars);
        } catch (Exception e) {}

        return h;
    }

    double getVert() {

        double v = 0;

        for (int i = 0; i < 8; i++) {
            arrowVars[i] = vars[i];
        }

        if ((myMaster != null) &&!dynamic) {
            arrowVars[8] = myMaster.getTotalFx();
            arrowVars[9] = myMaster.getTotalFy();
        } else {
            arrowVars[8] = getTotalFx();
            arrowVars[9] = getTotalFy();
        }

        try {
            v = vFunc.evaluate(arrowVars);
        } catch (Exception e) {}

        return v;
    }

    public void setFilled(boolean f) {
        filled = f;
    }

    public double getH() {
        return getVert();
    }

    public void setH(double vert) {
        System.out.println(
            "Error:  Cannot set the vertial component of a dynamic arrow.");
    }

    public double getW() {
        return getHorz();
    }

    public void setW(double horz) {
        System.out.println(
            "Error:  Cannot set the horizontal component of a dynamic arrow.");
    }

    public final boolean isInsideThing(int xPix, int yPix) {

        int ptX = canvas.pixFromX(vars[1]) + xDisplayOff;
        int ptY = canvas.pixFromY(vars[2]) - yDisplayOff;

        if ((Math.abs(xPix - ptX) < s + 1)
                && (Math.abs(yPix - ptY) < s + 1)) {
            return true;
        } else {
            return false;
        }
    }

    public void paint(Graphics osg) {

        if (!visible) {
            return;
        }

        int    ptX = (int) Math.round(canvas.pixFromX(vars[1])) + xDisplayOff;
        int    ptY = (int) Math.round(canvas.pixFromY(vars[2])) - yDisplayOff;
        double x   = 0;    // the x component;
        double y   = 0;    // the y component;

        if (showVVector) {
            x = canvas.pixPerUnit * vars[3];
            y = canvas.pixPerUnit * vars[4];
        } else if (showAVector) {
            x = canvas.pixPerUnit * vars[5];
            y = canvas.pixPerUnit * vars[6];
        } else if (dynamic && showFVector) {
            x = canvas.pixPerUnit * getTotalFx();
            y = canvas.pixPerUnit * getTotalFy();
        } else if (!dynamic && showFVector) {
            x = canvas.pixPerUnit * vars[5] * mass;
            y = canvas.pixPerUnit * vars[6] * mass;
        } else {
            setArrow();

            x = canvas.pixPerUnit * arrow[0];    // the x component;
            y = canvas.pixPerUnit * arrow[1];    // the y component;
        }

        osg.setColor(color);

        int x2 = (int) (ptX + x);
        int y2 = (int) (ptY - y);

        // a.addToTrail(x2,y2);
        double h = Math.sqrt(x * x + y * y);
        double w;
        if(h<2){
          osg.drawLine((ptX), (ptY), x2, y2);
          return;
        }

        if (h > 3 * s) {
            w = s;
        } else {
            w = h / 3;
        }

        if(thickness>1){
          edu.davidson.tools.SUtil.drawThickArrow(osg,ptX,ptY,x2,y2,(int)w, thickness);
        }else if (filled) {
            edu.davidson.tools.SUtil.drawSolidArrow(osg, ptX, ptY, x2, y2,(int) w);
        } else {
            osg.drawLine((ptX), (ptY), x2, y2);

            if (h > 1) {
                double u      = (w * x / h);
                double v      = -(w * y / h);
                double base_x = x2 - 3 * u;
                double base_y = y2 - 3 * v;

                osg.drawLine((int) (base_x - v), (int) (base_y + u), x2, y2);
                osg.drawLine((int) (base_x + v), (int) (base_y - u), x2, y2);
            }
        }
        if(!noDrag){  // draw a hot spot on the base
          if(color!=Color.lightGray)osg.setColor(Color.lightGray);
          else setColor(Color.red);
          osg.fillOval(ptX-2,ptY-2,5,5);
          osg.setColor(color);
          osg.drawOval(ptX-2,ptY-2,5,5);
        }

        if (label != null) {
            osg.setColor(Color.black);

            Font f = osg.getFont();

            osg.setFont(font);

            FontMetrics fm   = osg.getFontMetrics(font);
            int         off1 = 4 + (int) ((8 + fm.stringWidth(label))
                                          * (-1.0 + x / h) / 2.0);
            int         off2 = (int) (-4 * (y / h)
                                      + fm.getHeight() * (1.0 - y / h) / 4.0);

            osg.drawString(label, x2 + off1, y2 + off2);
            osg.setFont(f);
        }
    }

    public void paintHighlight(Graphics osg) {

        if (!visible) {
            return;
        }

        int    ptX = (int) Math.round(canvas.pixFromX(vars[1])) + xDisplayOff;
        int    ptY = (int) Math.round(canvas.pixFromY(vars[2])) - yDisplayOff;
        double x   = 0;    // the x component;
        double y   = 0;    // the y component;

        if (showVVector) {
            x = canvas.pixPerUnit * vars[3];
            y = canvas.pixPerUnit * vars[4];
        } else if (showAVector) {
            x = canvas.pixPerUnit * vars[5];
            y = canvas.pixPerUnit * vars[6];
        } else if (dynamic && showFVector) {
            x = canvas.pixPerUnit * getTotalFx();
            y = canvas.pixPerUnit * getTotalFy();
        } else if (!dynamic && showFVector) {
            x = canvas.pixPerUnit * vars[5];
            y = canvas.pixPerUnit * vars[6];
        } else {
            setArrow();

            x = canvas.pixPerUnit * arrow[0];    // the x component;
            y = canvas.pixPerUnit * arrow[1];    // the y component;
        }

        //osg.setColor(color);
        osg.setColor(highlightColor);

        int x2 = (int) (ptX + x);
        int y2 = (int) (ptY - y);

        // a.addToTrail(x2,y2);
        double h = Math.sqrt(x * x + y * y);
        double w;

        if(h<2){
          osg.drawLine((ptX), (ptY), x2, y2);
          return;
        }

        if (h > 3 * s) {
            w = s;
        } else {
            w = h / 3;
        }

        if (filled) {
            edu.davidson.tools.SUtil.drawSolidArrow(osg, ptX, ptY, x2, y2,
                                                    (int) w);

            return;
        }

        osg.drawLine((ptX), (ptY), x2, y2);

        if (h > 1) {
            double u      = (w * x / h);
            double v      = -(w * y / h);
            double base_x = x2 - 3 * u;
            double base_y = y2 - 3 * v;

            osg.drawLine((int) (base_x - v), (int) (base_y + u), x2, y2);
            osg.drawLine((int) (base_x + v), (int) (base_y - u), x2, y2);
        }

        if (label != null) {
            osg.setColor(Color.black);

            Font f = osg.getFont();

            osg.setFont(font);

            FontMetrics fm   = osg.getFontMetrics(font);
            int         off1 = 4 + (int) ((8 + fm.stringWidth(label))
                                          * (-1.0 + x / h) / 2.0);
            int         off2 = (int) (-4 * (y / h)
                                      + fm.getHeight() * (1.0 - y / h) / 4.0);

            osg.drawString(label, x2 + off1, y2 + off2);
            osg.setFont(f);
        }
    }

    public double[][] getVariables() {

        double horz = 0;
        double vert = 0;

        if (showVVector) {
            horz = vars[3];
            vert = vars[4];
        } else if (showAVector) {
            horz = canvas.pixPerUnit * vars[5];
            vert = canvas.pixPerUnit * vars[6];
        } else if (dynamic && showFVector) {
            horz = getTotalFx();
            vert = getTotalFy();
        } else if (!dynamic && showFVector) {
            horz = vars[5];
            vert = vars[6];
        } else {
            setArrow();

            horz = arrow[0];
            vert = arrow[1];
        }

        ds[0][0] = vars[0];    //t
        ds[0][1] = vars[1];    //x
        ds[0][2] = vars[2];    //y
        ds[0][3] = vars[3];    //vx
        ds[0][4] = vars[4];    //vy
        ds[0][5] = vars[5];    //ax
        ds[0][6] = vars[6];    //ay
        ds[0][7] = mass;       //mass
        ds[0][8] = horz;       //horz
        ds[0][9] = vert;       //vert
        ds[0][10] = horz;       //horz
        ds[0][11] = vert;       //vert

        return ds;
    }
}

