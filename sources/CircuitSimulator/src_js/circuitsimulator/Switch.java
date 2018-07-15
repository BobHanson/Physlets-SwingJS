package circuitsimulator;

import java.awt.Graphics;

/**
 * Circuit element, representing a switch (open/close connection)
 *
 * @author Toon Van Hoecke
 */
public class Switch extends CircuitElement
{
    boolean open = true;

    Switch(Circuit circuit, boolean o, int r, int c, String t) {
        super(circuit,r,c,t);
        setValueVisible(false);
        open = o;
        value = open ? 1e10 : 0.0;
    }

    Switch() {super();}

    public void loadImage(Graphics g) {}//overriding

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(java.awt.Color.red);
        int cig = circuit.interGrid/2;
        int p = open?5:0;
        if (to.equals("h")){
            g.drawLine(x+3, y, x+cig-5, y);
            g.drawLine(x+cig+6, y, x+circuit.interGrid-4, y);
            g.fillOval(x+cig-7,y-2,4,4);
            g.fillOval(x+cig+6,y-2,4,4);
            g.drawLine(x+cig-5, y, x+cig+5, y-p);
        } else {
            g.drawLine(x, y+3, x, y+cig-5);
            g.drawLine(x, y+cig+6, x, y+circuit.interGrid-4);
            g.fillOval(x-2,y+cig-7,4,4);
            g.fillOval(x-2,y+cig+6,4,4);
            g.drawLine(x, y+cig-5, x+p, y+cig+5);
        }
    }

    public double impedance(){return value;}

    public void change() {
        open = open ? false : true;
        value = open ? 1e10 : 0.0;
    }

    public void setvalue(String s){
      if(s.toLowerCase().equals("open")){
          open=true;
          value=1e10;
      }else if(s.toLowerCase().equals("close")){
        open=false;
        value=0.0;
      } else change();
    }

    public String getStringAdditions() {return ",open="+(open?"1":"0");}
}
