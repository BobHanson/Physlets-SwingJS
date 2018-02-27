package impedance;

//import java.awt.*;
import edu.davidson.tools.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;

import a2s.*;

/**
 * Class Grafiek
 */
class Grafiek extends Canvas {

  static String label_ohm       = "[ohm]";
  static String label_frequency = "Frequency [Hz]";
  static String label_impedance = "|Z|";

  /**
   * Constructor Grafiek
   *
   * @param n
   */
  Grafiek(Netwerk n) {
    netwerk = n;
    setBackground(Color.white);
  }

  /**
   * Method setNetwerk
   *
   * @param n
   */
  void setNetwerk(Netwerk n) {
    netwerk = n;
    repaint();
  }

  /**
   * Method paint
   *
   * @param g
   */
  public void paint(Graphics g) {
    //      berekenen van de size (is er geen EvSize event ???)
    w = size().width-wlm-wrm;
    w = (w<100)
        ? 100
        : w;
    h = size().height-htm-hbm;
    h = (h<100)
        ? 100
        : h;
    //      achtergrond
    g.setColor(Color.black);
    g.drawLine(0, 0, 0, size().height-1);
    g.drawLine(size().width-1, 0, size().width-1, size().height-1);
    g.setColor(Color.lightGray);
    int x0  = wlm;
    int y0  = htm;
    int wd5 = w/5;
    int hd5 = h/5;
    for(double d = 1E1; d<1E6; d *= 10, x0 += wd5, y0 += hd5) {
      for(double x = d; x<d*10; x += d) {
        Double dxp = new Double((Math.log(x)/Math.log(10.0)-Math.log(d)/Math.log(10.0))*wd5);
        Double dyp = new Double((Math.log(x)/Math.log(10.0)-Math.log(d)/Math.log(10.0))*hd5);
        int    xp  = dxp.intValue();
        int    yp  = dyp.intValue();
        g.drawLine(x0+xp, htm, x0+xp, htm+h);
        g.drawLine(wlm, h+2*htm-(y0+yp), wlm+w, h+2*htm-(y0+yp));
      }
    }
    g.setColor(Color.black);
    g.drawLine(wlm, htm, wlm, htm+h);
    g.drawLine(wlm, htm+h, wlm+w, htm+h);
    g.drawLine(wlm+w, htm+h, wlm+w, htm);
    g.drawLine(wlm+w, htm, wlm, htm);
    g.drawString("10", wlm-5, htm+h+15);
    g.drawString("100", wlm+wd5-12, htm+h+15);
    g.drawString("1K", wlm+2*wd5-7, htm+h+15);
    g.drawString("10K", wlm+3*wd5-10, htm+h+15);
    g.drawString("100K", wlm+4*wd5-15, htm+h+15);
    g.drawString("1M", wlm+5*wd5-10, htm+h+15);
    g.drawString("1M", wlm-25, htm+5);
    g.drawString("100K", wlm-40, htm+5+hd5);
    g.drawString("10K", wlm-30, htm+5+2*hd5);
    g.drawString("1K", wlm-25, htm+5+3*hd5);
    g.drawString("100", wlm-30, htm+5+4*hd5);
    g.drawString("10", wlm-25, htm+5+5*hd5);
    g.drawString(label_frequency, wlm+w-100, htm+h+35);
    g.drawString(label_impedance, 20, htm+5);
    g.drawString(label_ohm, 10, htm+25);
    //      impedantie karakteristiek
    int    xp1  = 1;
    double x    = Math.pow(10.0, (double) (xp1)/(w/5)+1);
    double y    = netwerk.Z(x).abs();
    Double dyp1 = new Double(h/5*(Math.log(y)/Math.log(10.0)-1));
    int    yp1  = dyp1.intValue();
    yp1 = (yp1>h)
          ? h
          : yp1;
    yp1 = (yp1<0)
          ? 0
          : yp1;
    g.setColor(Color.blue);
    for(int xp2 = 2; xp2<w; ++xp2) {
      x = Math.pow(10.0, (double) (xp2)/(w/5)+1);
      y = netwerk.Z(x).abs();
      Double dyp2 = new Double(h/5*(Math.log(y)/Math.log(10.0)-1));
      int    yp2  = dyp2.intValue();
      yp2 = (yp2>h)
            ? h
            : yp2;
      yp2 = (yp2<0)
            ? 0
            : yp2;
      g.drawLine(xp1+wlm, h+htm-yp1, xp2+wlm, h+htm-yp2);
      xp1 = xp2;
      yp1 = yp2;
    }
  }

  private int     wlm = 80;  // width left margin
  private int     w;         // width of grafic
  private int     wrm = 20;  // width right margin
  private int     htm = 20;  // heigth top margin
  private int     h;         // heigth of grafic
  private int     hbm = 45;  // heigth bottom margin
  private Netwerk netwerk;
}

/**
 * Class ImpAppl
 *
 */
public class ImpAppl extends SApplet {

  /**
   * @y.exclude
   */
  public ImpAppl(){

  }

  protected void setResources() {
    Grafiek.label_ohm       = localProperties.getProperty("label.ohm", Grafiek.label_ohm);
    Grafiek.label_frequency = localProperties.getProperty("label.frequency", Grafiek.label_frequency);
    Grafiek.label_impedance = localProperties.getProperty("label.impedance", Grafiek.label_impedance);
  }

  /**
   * @y.exclude
   */
  public void init() {
    initResources(null);
    String ntStr = getParameter("noText");  //Parameter added by Wolfgang Christian
    if(ntStr==null) {
      noText = false;
    } else {
      noText = Boolean.valueOf(ntStr).booleanValue();
    }
    String  showStr     = getParameter("ShowControls");  //Parameter added by Wolfgang Christian
    boolean showControl = true;
    if(showStr==null) {
      showControl = true;
    } else {
      showControl = Boolean.valueOf(showStr).booleanValue();
    }
    setLayout(new BorderLayout());
    parser = new Parser();
    String s = getParameter("network");  //Parameter added by Wolfgang Christian
    if(s==null) {
      s = new String("(R(1E2)+C(1E-6))//(R(1E2)+L(3E-2))");
    }
    parser.parseExpr(s);
    netwerk = parser.getResult();
    tf      = new TextField(s);
    if(!noText) {  // added by Wolfgang Christian
      add("North", tf);
    }
    graf = new Grafiek(netwerk);
    add("Center", graf);
    status = new Status(netwerk.toString());
    if(showControl) {
      add("South", status);
    }
  }

  /**
   * @y.exclude
   * @param e
   * @param o
   *
   * @return true if successful
   */
  public boolean action(Event e, Object o) {
    if(e.target instanceof TextField) {
      String s = (String) o;
      setNetwork(s);
      return true;
    }
    return false;
  }

  /**
   * Sets the network.
   *
   * @param s
   */
  public void setNetwork(String s) {  // added by Wolfgang Christian
    if(parser.parseExpr(s)) {
      netwerk = parser.getResult();
      status.setText(netwerk.toString());
      graf.setNetwerk(netwerk);
    } else {
      tf.select(parser.getErrorPos()-1, parser.getErrorPos());
      status.errorText("Error: "+parser.getErrorText()+" on position "+parser.getErrorPos());
    }
    tf.setText(s);
  }

  /**
   * Method getAppletInfo
   *
   *
   * @return the applet info
   * @y.exclude
   */
  public String getAppletInfo()  // added by Wolfgang Christian
  {
    return "Name: Impedance ver 0.9\r\n"+"Author: Harry Broeders\r\n"+"info: http://tlm.thrijswijk.nl/~bd\r\n"
           +"Modified by: Wolfgang Christian\r\n"+"";
  }

  /**
   * Method getParameterInfo
   *
   *
   * @return the parameter info
   * @y.exclude
   */
  public String[][] getParameterInfo()  // added by Wolfgang Christian
  {
    String[][] info = {
      {"noText", "boolean", "Show text strings."}, {"network", "String", "Initialize the network."},
    };
    return info;
  }

  private Parser    parser;
  private Netwerk   netwerk;
  private TextField tf;
  private Status    status;
  private Grafiek   graf;
  private boolean   noText = false;  // added by Wolfgang Christian
}
