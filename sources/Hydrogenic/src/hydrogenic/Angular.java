/*
 *
 *
 *                      Class  hydrogenic.Angular
 *
 *
 *
 * class Angular extends Applet
 *
 * The main entry point and Angular applet.
 *
 */
package hydrogenic;

import java.awt.*;
import java.awt.event.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;

/**
 *  Angular Class plots the angular probability
 *  distribution for the hydrogen atom.  User may vary values of
 *  l and m, and may plot the distributions normalized or scaled to one for
 *  comparison.
 *  @version 1.1
 *  @author Jim Nolen
 *  @author Wolfgang Christian
 */
public class Angular extends SApplet {
  String button_clear = "Clear";
  String button_go = "Go";
  String label_multiple = "Multiple";


  boolean          sa        = false;  //show amplitude
  boolean          openned   = true;
  SGraph           graph     = new SGraph();
  private double[] datax     = new double[210];
  private double[] datay     = new double[210];
  private double[] ndatax    = new double[210];
  private double[] ndatay    = new double[210];
  private boolean  compare   = false;
  private int      numpoints = 210;
  private int      l;
  private int      seriesnum = 0;
  //private boolean  normal;
  private int      m;

  /** Field sc           */
  boolean   sc;

  /** Field multi           */
  boolean   multi;
  //private boolean  graphon = false;
  private Color    forrest = new Color(0, 128, 0);
  //private boolean  limit   = false;

  protected void setResources() {
  button_clear = localProperties.getProperty("button.clear", button_clear);
  button_go = localProperties.getProperty("button.go", button_go);
  label_multiple = localProperties.getProperty("label.multiple", label_multiple);
}

/**
 * @y.exclude
 */
public Angular(){

}



  /**
   *  Initialization takes paramater values from webpage
   *  angular = 1.  showControls to show/hide panel.
   *  mult to show multiple plots.
   *
   * @y.exclude
   */
  public void init() {
    initResources(null);
    try {
      l = Integer.parseInt(this.getParameter("l", "2"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      m = Integer.parseInt(this.getParameter("m", "1"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      sc = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      Boolean.valueOf(this.getParameter("Normalize", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      multi = Boolean.valueOf(this.getParameter("MultPlot", "false")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    showPanel(sc);
    comparebox.setState(multi);
    setLM(l, m);
    graph.setEnableMouse(true);
    graph.setGraphBackground(SystemColor.control);
    graph.setAutoscaleX(false);
    graph.setAutoscaleY(false);
    graph.setMinMaxX(-1, 1);
    graph.setMinMaxY(-1, 1);
  }

  private void jbInit() throws Exception {
    this.setSize(new Dimension(257, 264));
    this.setLayout(borderLayout5);
    graph.setBackground(SystemColor.control);
    graph.setMarkers(null);
    graph.setDataBackground(Color.white);
    graph.setSampleData(false);
    graph.setBorders("0,7,10,0");
    graph.setDrawZero(true);
    graph.setLabelY("Z");
    controlPan.setLayout(borderLayout4);
    graph.setLabelX("X");
    etchedBorder2.setLayout(borderLayout3);
    etchedBorder3.setLayout(borderLayout2);
    clearbtn.setLabel(button_clear);
    gobtn.setLabel(button_go);
    etchedBorder4.setLayout(borderLayout1);
    comparebox.setBackground(SystemColor.control);
    comparebox.setLabel(label_multiple);
    etchedBorder5.setLayout(flowLayout2);
    lfield.setText("0");
    label1.setBackground(SystemColor.control);
    label1.setAlignment(2);
    label1.setText("m =");
    mfield.setText("0");
    llabel.setText("l =");
    llabel.setBackground(SystemColor.control);
    llabel.setAlignment(2);
    //comparebox.addItemListener(new java.awt.event.ItemListener() {
    // public void itemStateChanged(ItemEvent e) {
    //   comparebox_itemStateChanged(e);
    // }
    //});
    gobtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        gobtn_actionPerformed(e);
      }
    });
    clearbtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        clearbtn_actionPerformed(e);
      }
    });
    this.add(graph, BorderLayout.CENTER);
    this.add(controlPan, BorderLayout.SOUTH);
    controlPan.add(etchedBorder3, BorderLayout.WEST);
    etchedBorder3.add(gobtn, BorderLayout.NORTH);
    etchedBorder3.add(clearbtn, BorderLayout.CENTER);
    controlPan.add(etchedBorder2, BorderLayout.CENTER);
    etchedBorder2.add(etchedBorder4, BorderLayout.CENTER);
    etchedBorder4.add(comparebox, BorderLayout.CENTER);
    etchedBorder2.add(etchedBorder5, BorderLayout.NORTH);
    etchedBorder5.add(llabel, null);
    etchedBorder5.add(lfield, null);
    etchedBorder5.add(label1, null);
    etchedBorder5.add(mfield, null);
  }


  /**
   * Method start
   * @y.exclude
   */
  public void start() {
    if(firstTime) {
      setLM(l, m);
    }
    firstTime = false;
    super.start();
  }

  /**
   * Method getAppletInfo
   * @return the info
   * @y.exclude
   */
  public String getAppletInfo() {
    return "Applet Information";
  }


  /**
   * Method getParameterInfo
   * @return the info
   * @y.exclude
   */
  public String[][] getParameterInfo() {
    return null;
  }

  /** Field p           */
  static final double pi             = Math.PI;
  EtchedBorder               controlPan    = new EtchedBorder();
  EtchedBorder               etchedBorder2 = new EtchedBorder();
  EtchedBorder               etchedBorder3 = new EtchedBorder();
  Button                     clearbtn      = new Button();
  Button                     gobtn         = new Button();
  BorderLayout               borderLayout2 = new BorderLayout();
  EtchedBorder               etchedBorder4 = new EtchedBorder();
  Checkbox                   comparebox    = new Checkbox();
  BorderLayout               borderLayout1 = new BorderLayout();
  EtchedBorder               etchedBorder5 = new EtchedBorder();
  SNumber                    lfield        = new SNumber();
  Label                      label1        = new Label();
  SNumber                    mfield        = new SNumber();
  Label                      llabel        = new Label();
  FlowLayout                 flowLayout2   = new FlowLayout();
  BorderLayout               borderLayout3 = new BorderLayout();
  BorderLayout               borderLayout4 = new BorderLayout();
  BorderLayout               borderLayout5 = new BorderLayout();

  /**
   *  Calculates the probabilities from 0 < theta < pi/2.
   */
  public void calcdata() {  //calculates data and stores in arrays
    int    i;
    double g;
    double norm = 2;
    if(m<0) {
      m = -m;
    }
    norm = Math.sqrt((((2*l)+1)*SpecialFunctions.factorial(l-m))/(SpecialFunctions.factorial(l+m)*4*pi));
    for(i = 0; i<numpoints; i++)                                                                   //REMEMBER: theta is measured extending downward form z-axis.
    {
      g = SpecialFunctions.legendre(l, m, Math.cos(pi*i/(2.0*numpoints)));
      if(sa) {
        datax[i] = (norm*g)*Math.sin(pi*i/(2.0*numpoints))*(norm*g)*Math.sin(pi*i/(2.0*numpoints));  //data only calculated from 0 <theta< 90 degrees
        datay[i] = (norm*g)*Math.cos(pi*i/(2.0*numpoints))*(norm*g)*Math.cos(pi*i/(2.0*numpoints));
      } else {
        datax[i] = (norm*g)*Math.sin(pi*i/(2.0*numpoints));                                         //data only calculated from 0 <theta< 90 degrees
        datay[i] = (norm*g)*Math.cos(pi*i/(2.0*numpoints));
      }
    }
    //if (!normal)
    unitScale();
    for(i = 0; i<numpoints; i++) {
      ndatax[i] = -datax[i];
      ndatay[i] = -datay[i];
    }
  }

  /**
   *  Function scales values to compare shapes of multiple plots.
   */
  private void unitScale() {  //method makes all graphs of same general size, so the shapes can be compared.
    double length  = 0;
    double datamax = 0;
    int    i;
    for(i = 0; i<numpoints; i++)  //finds the max distance from the origin.
    {
      length = Math.sqrt((datax[i]*datax[i])+(datay[i]*datay[i]));
      if(length>datamax) {
        datamax = length;
      }
    }
    for(i = 0; i<numpoints; i++) {
      datax[i] /= datamax;
      datay[i] /= datamax;
    }
  }

  /**
   *  Sets principle and angular quantum numbers to be plotted.
   *
   *  @param m  the principle quantum number
   *  @param l  the angular quantum number
   */
  public void setLM(int l, int m) {  //method to set quantum numbers and graph, without calling
    //the button method.
    if(openned) {
      seriesnum = 0;
      graph.deleteAllSeries();
      openned = false;
    }
    //else seriesnum++;
    seriesnum++;
    this.l = l;
    this.m = m;
    mfield.setValue(m);
    lfield.setValue(l);
    compare = comparebox.getState();
    //normal = normalbox.getState();
    calcdata();
    graphdata();
    graph.square = true;
  }

  /**
   *  Shows the control panel.
   *
   *  @param q  boolean
   */
  public void showPanel(boolean q) {
    controlPan.setVisible(q);
    controlPan.setBackground(SystemColor.control);
  }

  /**
   *  Graphs data, up to five plots at a time
   */
  public void graphdata() {
    Color graphcolor = Color.red;
    if(compare==false) {
      graph.deleteAllSeries();
      seriesnum = 0;
    }
    //graph.setAutoscaleX(false);
    //graph.setAutoscaleY(false);
    switch(seriesnum) {
      case 0 :
        graphcolor = Color.red;
        break;
      case 1 :
        graphcolor = Color.black;
        break;
      case 2 :
        graphcolor = Color.blue;
        break;
      case 3 :
        graphcolor = forrest;
        break;
      case 4 :
        graphcolor = Color.gray;
        break;
      case 5 : {
        graphcolor = Color.red;
        seriesnum  = 0;
      }
    }
    graph.deleteSeries(4*seriesnum+1);
    graph.deleteSeries(4*seriesnum+2);
    graph.deleteSeries(4*seriesnum+3);
    graph.deleteSeries(4*seriesnum+4);
    graph.setSeriesStyle(4*seriesnum+1, graphcolor, true, 0);
    graph.setSeriesStyle(4*seriesnum+2, graphcolor, true, 0);
    graph.setSeriesStyle(4*seriesnum+3, graphcolor, true, 0);
    graph.setSeriesStyle(4*seriesnum+4, graphcolor, true, 0);
    graph.addData(4*seriesnum+1, datax, datay);
    graph.addData(4*seriesnum+2, ndatax, datay);
    graph.addData(4*seriesnum+3, ndatax, ndatay);
    graph.addData(4*seriesnum+4, datax, ndatay);
    //graph.setSeriesLegend(seriesnum+1,graphcolor,(int)graph.getSize().width-(int)(graph.getSize().width/4),30+seriesnum*11,"l = " + l + " m = " + m);
    if(graph.getSize().width>150) {
      graph.setSeriesLegend(seriesnum+1, graphcolor, 25+(int) (graph.getSize().width/2), 30+seriesnum*11,
                            "l = "+l+" m = "+m);
    } else {
      graph.setSeriesLegend(seriesnum+1, graphcolor, (int) (graph.getSize().width/4), 30+seriesnum*11,
                            "l = "+l+" m = "+m);
    }
  }

  /**
   * Method gobtn_actionPerformed
   *
   * @param e
   */
  void gobtn_actionPerformed(ActionEvent e) {
    int ll, mm;
    ll = (int) lfield.getValue();
    if(ll<0) {
      ll = -ll;
    }
    mm = (int) mfield.getValue();
    //if (Math.abs(y)<=x)
    if(ll<Math.abs(mm)) {
      mfield.setBackground(Color.red);
    } else {
      setLM(ll, mm);
    }
  }

  /**
   * Method clearbtn_actionPerformed
   *
   * @param e
   */
  void clearbtn_actionPerformed(ActionEvent e) {
    mfield.setValue(m);
    lfield.setValue(l);
    graph.deleteAllSeries();
    seriesnum = 0;
  }
}
