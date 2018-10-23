/*
 *
 *
 *                      Class  hydrogenic.Radial
 *
 *
 *
 * class Radial extends Applet
 *
 * The main entry point and Radial applet.
 *
 */
package hydrogenic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import java.awt.event.*;
import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;
import java.awt.*;

/**
 *  Radial Class plots the radial wavefunction
 *  for the hydrogen atom.
 *  @version 1.0
 *  @author Cabell Fisher
 *  @author Wolfgang Christian
 */
public class Radial extends SApplet {
  String button_reset = "Reset";
  String button_plot = "Plot";
  String label_multiple = "Multiple";

  Panel           optionpnl     = new Panel();
  SGraph          radialgr      = new SGraph();
  EtchedBorder    etchedbrdr    = new EtchedBorder();
  BorderLayout    borderLayout1 = new BorderLayout();
  private int     sc            = 1;  //Our series counter to count graph series
  private int     px;                 //pixel x position for graph legend
  private int     py;                 //pixel y position for graph legend
  private int     py2   = 20;         //will allow us to "stack" our series legends above each other
  private boolean multi = false;
  int             l;
  int             n;
  boolean         s, sp, phase = true;
  FlowLayout      flowLayout2   = new FlowLayout();
  SInteger        angqnnb       = new SInteger();
  Panel           panel2        = new Panel();
  FlowLayout      flowLayout4   = new FlowLayout();
  Label           label1        = new Label();
  Label           label2        = new Label();
  SInteger        prinqnnb      = new SInteger();
  Panel           panel1        = new Panel();
  Checkbox        multiplecb    = new Checkbox();
  Button          resetbtn      = new Button();
  FlowLayout      flowLayout3   = new FlowLayout();
  Button          plotbtn       = new Button();
  BorderLayout    borderLayout2 = new BorderLayout();

  protected void setResources() {
    button_reset = localProperties.getProperty("button.reset", button_reset);
    button_plot = localProperties.getProperty("button.plot", button_plot);
    label_multiple = localProperties.getProperty("label.multiple", label_multiple);
  }

  /**
   * @y.exclude
   */
  public Radial(){

  }


  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    try {
      l = Integer.parseInt(this.getParameter("l", "0"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      n = Integer.parseInt(this.getParameter("n", "1"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      s = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      multi = Boolean.valueOf(this.getParameter("MultPlot", "false")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      sp = Boolean.valueOf(this.getParameter("ShowAmplitude", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    setShowControls(s);
    angqnnb.setValue(l);   //angular quantum number the user inputs when scripting
    prinqnnb.setValue(n);  //principle quantum number the user inputs when scripting
    multiplecb.setState(multi);
    //radialgr.setGraphBackground(SystemColor.control);
    radialgr.setGraphBackground(resetbtn.getBackground());
  }

  private void jbInit() throws Exception {
    optionpnl.setLayout(flowLayout2);
    this.setBackground(Color.white);
    /** j2sNative */{
      this.setSize(new Dimension(371, 333));
    }
    optionpnl.setBackground(SystemColor.control);
    radialgr.setLabelY("[Rnl(r)*r]^2");
    radialgr.setEnableMouse(true);
    radialgr.setBorders("0,12,10,0");
    etchedbrdr.setBackground(SystemColor.control);
    etchedbrdr.setThickness(3);
    flowLayout2.setVgap(0);
    angqnnb.setBackground(SystemColor.control);
    panel2.setLayout(flowLayout4);
    flowLayout4.setHgap(3);
    flowLayout4.setVgap(2);
    label1.setText("n=");
    label2.setText("l=");
    prinqnnb.setValue(1);
    panel1.setLayout(flowLayout3);
    multiplecb.addItemListener(new java.awt.event.ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        multiplecb_itemStateChanged(e);
      }
    });
    multiplecb.setLabel(label_multiple);
    resetbtn.setLabel(button_reset);
    flowLayout3.setHgap(2);
    flowLayout3.setVgap(1);
    plotbtn.setLabel(button_plot);
    plotbtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        plotbtn_actionPerformed(e);
      }
    });
    resetbtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        resetbtn_actionPerformed(e);
      }
    });
    label2.setAlignment(2);
    label1.setAlignment(2);
    etchedbrdr.setLayout(borderLayout2);
    radialgr.setBackground(SystemColor.control);
    radialgr.setDataBackground(Color.white);
    radialgr.setSampleData(false);
    radialgr.setLabelX(" r/ao ");
    this.setLayout(borderLayout1);
    this.add(radialgr, BorderLayout.CENTER);
    this.add(optionpnl, BorderLayout.NORTH);
    this.add(etchedbrdr, BorderLayout.SOUTH);
    etchedbrdr.add(panel1, BorderLayout.SOUTH);
    panel1.add(multiplecb, null);
    panel1.add(resetbtn, null);
    panel1.add(plotbtn, null);
    etchedbrdr.add(panel2, BorderLayout.CENTER);
    panel2.add(label1, null);
    panel2.add(prinqnnb, null);
    panel2.add(label2, null);
    panel2.add(angqnnb, null);
  }

  /**
   * Method start
   * @y.exclude
   */
  public void start() {
    if(firstTime) {
      setNL(n, l);
    }
    firstTime = false;
    super.start();
  }

  /**
   * Method getAppletInfo
   *
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

  /**
   * Allows the user to hide the control panel at the the bottom of the graph
   *
   * @param s
   */
  public void setShowControls(boolean s) {
    etchedbrdr.setVisible(s);
    this.invalidate();
    this.validate();  //tells graph to redraw in order to show new control box
  }

  /**
   * Allows the user to switch between graphing porbability amplitude and
   * amplitude of the wave function.
   * @param sp
   */
  public void showAmplitude(boolean sp) {
    phase = sp;
  }

  /**
   * Clears all data series contained in the graph register
   * This method also resets the series counter to 1
   */
  public void clearGraph() {
    radialgr.deleteAllSeries();
    multiplecb.setState(false);
    sc  = 1;
    py2 = 20;
  }

  /**
   * This method calculates the radial wave function probablility
   * @param n
   * @param l
   */
  public void setNL(int n, int l) {
    angqnnb.setValue(l);   //angular quantum number the user inputs when scripting
    prinqnnb.setValue(n);  //principle quantum number the user inputs when scripting
    double r;
    if(n<3) {
      r = 2*((n+1)*(n+1));
    } else {
      r = 2*((n+2)*(n+2));  //width of my x-axis (r/ao) or the radius over which we are finding probablility
    }
    double   rnl  = 0;                         //our radial function Rnl(r)
    int      np   = radialgr.getSize().width;  //number of points to be graphed which we will set to the # of pixels comprising the width of the graph
    double[] rx   = new double[np];            //our arrays for x and y values to be put on the graph
    double[] prob = new double[np];
    double   dx   = (r/np);                    //our step variable that determines the step size of x through the calculations
    int      i    = 0;                         //my for loop counter
    double   x    = 0;                         //our position value on the x-axis or radius from nucleus of hydrogen atom
    px = radialgr.getSize().width-100;
    py = radialgr.getSize().height-(radialgr.getSize().height-py2);  //puts the legend on the top of the graph
    //the following sets up how our graph looks and acts
    radialgr.setSeriesStyle(1, Color.black, true, 0);  //this series will be radial probablility and is black
    radialgr.setSeriesStyle(2, Color.red, true, 0);    //this series is out wave function Psi and is red
    radialgr.setSeriesStyle(3, Color.green, true, 0);
    radialgr.setSeriesStyle(4, Color.blue, true, 0);
    radialgr.setAutoscaleX(false);
    radialgr.setAutoscaleY(false);                     //turns auto scale functions off on the graph
    radialgr.setMinMaxX(0, r);                         //sets x scale
    // Here begin the calculations of the Normalized Radial probablility solutions
    if(sc>=5) {  //resets our series counter automatically when user has five series on graph
      sc  = 1;
      py2 = 20;
    }
    {
      double f1    = SpecialFunctions.factorial(n-l-1);                                                          //factorial function of normalization constant
      double f2    = SpecialFunctions.factorial(n+l);                                                            //factorial functions pulled out to simplify code and save time and efficiency
      double normc = (2.0/(n*n))*Math.sqrt(f1/f2);                                                               // our prized normilization constant
      if((n==1)&&(l==0)) {
        for(i = 1; i<np; i++) {
          rnl = 2*Math.exp(-x);
          if(!phase) {
            prob[i] = rnl*rnl*x*x;
          } else {
            prob[i] = rnl;
          }
          rx[i] = x;                                                                                             //n=1 l=0 state automatically entered in for efficiency
          x     = x+dx;
        }
      } else if((n==2)&&(l==0)) {
        for(i = 1; i<np; i++) {
          rnl = 0.7071067812*(1-(x/2))*Math.exp(-x/2);
          if(!phase) {
            prob[i] = rnl*rnl*x*x;
          } else {
            prob[i] = rnl;
          }
          rx[i] = x;                                                                                             //n=2 l=0 state automatically entered in for efficiency
          x     = x+dx;
        }
      } else if((n==2)&&(l==1)) {
        for(i = 1; i<np; i++) {
          rnl = 0.2041241452*x*Math.exp(-x/2);
          if(!phase) {
            prob[i] = rnl*rnl*x*x;
          } else {
            prob[i] = rnl;
          }
          rx[i] = x;                                                                                             //n=1 l=1 state automatically entered in for efficiency
          x     = x+dx;
        }
      } else {
        for(i = 1; i<np; i++) {
          rnl = normc*Math.pow((2.0*x)/n, l)*Math.exp(-x/n)*SpecialFunctions.laguerre(2*l+1, n-l-1, (2.0*x)/n);  //SpecialFunctions.laguerre(2*l+1,n-l-1,2*x/k); //our normalized radial function Rnl(r)
          if(!phase) {
            prob[i] = rnl*rnl*x*x;
          } else {
            prob[i] = rnl;                                                                                       //our radial probability [Rnl(r)]^2*(r)^2
          }
          rx[i] = x;                                                                                             //x values corresponding to the calculated probabilities
          x     = x+dx;
        }
      }
      if(multiplecb.getState()==true)                                                                            //if our graph wave function check box is checked
      {
        radialgr.clearSeriesData(sc);
        if(phase) {
          radialgr.setAutoscaleY(true);
        } else {
          radialgr.setAutoscaleY(false);
        }
        radialgr.addData(sc, rx, prob);                                                                          //puts data on graph
        radialgr.setMinMaxY(0, radialgr.getYmax()+.1*radialgr.getYmax());                                        //sets our graph scale
        if(!phase) {
          radialgr.setLabelY("[Rnl(r)*r]^2", Color.black);
        } else {
          radialgr.setLabelY("Rnl(r)", Color.black);                                                             //changes our graph y-axis label
        }
        radialgr.setSeriesLegend(sc, Color.black, px, py, "n="+n+"  l="+l);
        sc++;
        py2 = py2+15;                                                                                            //moves legend down to next location to avoid overlapping
      } else {
        radialgr.clearSeriesData(1);
        if(phase) {
          radialgr.setAutoscaleY(true);
        } else {
          radialgr.setAutoscaleY(false);
        }
        radialgr.addData(1, rx, prob);                                                                           //graph radial probablity prob[]
        radialgr.setMinMaxY(0, radialgr.getYmax()+.05*radialgr.getYmax());                                       //sets y axis scale
        if(!phase) {
          radialgr.setLabelY("[Rnl(r)*r]^2", Color.black);
        } else {
          radialgr.setLabelY("Rnl(r)", Color.black);
        }
        radialgr.setSeriesLegend(1, Color.black, px, py, "n="+n+"  l="+l);
      }
    }
  }

  /**
   * This method tells the graph to obtain the user's entered values for n&l
   * It lso calls the SetNL(n,l) method as well that calculates and plots the data
   * @param e
   */
  void plotbtn_actionPerformed(ActionEvent e) {
    int l = angqnnb.getValue();   //angular quantum number the user inputs
    int n = prinqnnb.getValue();  //principle quantum number the user inputs
    if(l>(n-1)|(l<0)) {
      angqnnb.setBackground(Color.red);           //error checking l for l values
    } else if(n>50) {
      prinqnnb.setBackground(Color.red);
    } else {
      setNL(n, l);
    }
  }

  /**
   * This method calls the clearGraph() method and deletes all data on graph
   * @param e
   */
  void resetbtn_actionPerformed(ActionEvent e) {  //this button just clears all of our series from the graph and sets values to defaults
    clearGraph();
  }

  /**
   * When the show multiple box is checked, this method engages the series counter so
   * more than one data series can be seen at once.  If the box becomes unchecked the
   * method clears all the data except for the last graph calculated.
   * @param e
   */
  void multiplecb_itemStateChanged(ItemEvent e) {
    if(multiplecb.getState()) {
      sc = 2;
    } else {
      sc = 2;
      radialgr.deleteAllSeries();
      py2 = 20;
      setNL(prinqnnb.getValue(), angqnnb.getValue());
    }
  }
}
