/*
 *
 *
 *                      Class  hydrogenic.Density
 *
 *
 *
 * class Density extends Applet
 *
 * The main entry point and Density applet.
 *
 */
package hydrogenic;

//import java.awt.*;
import java.awt.event.*;

import javax.swing.SwingUtilities;

import edu.davidson.graphics.*;
import edu.davidson.tools.*;
import edu.davidson.display.*;

import a2s.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
 *  Density Physlet plots hydrogenic wavefunctions.
 *  @version 1.0
 *  @author Cabell Fischer
 *  @author Wolfgang Christian
 */
public class Density extends SApplet {
  String button_plot = "Plot";
  String label_phase = "Phase";
  String label_yes = "Y";
  String label_no = "N";


  int           m;
  int           n;
  int           l;
  boolean       sc, ph;
  EtchedBorder  southetchdbrdr = new EtchedBorder();
  DensityCanvas densityCanvas  = new DensityCanvas();
  BorderLayout  borderLayout1  = new BorderLayout();
  CheckboxGroup phasegroup     = new CheckboxGroup();
  Panel         panel1         = new Panel();
  FlowLayout    flowLayout2    = new FlowLayout();
  Button        plotBtn        = new Button();
  Checkbox      yphase         = new Checkbox();
  Checkbox      nphase         = new Checkbox();
  Label         label4         = new Label();
  Panel         panel2         = new Panel();
  FlowLayout    flowLayout3    = new FlowLayout();
  SInteger      nValue         = new SInteger() {
	  public void checkValue() {
		  super.checkValue();
		  if (checkNLM())
			  setBackground(Color.yellow);
	  }
  }; 
  SInteger      mValue         = new SInteger() {
	  public void checkValue() {
		  super.checkValue();
		  if (checkNLM())
			  setBackground(Color.yellow);
	  }
  };
  SInteger      lValue         = new SInteger() {
	  public void checkValue() {
		  super.checkValue();
		  if (checkNLM())
			  setBackground(Color.yellow);
	  }
  };
  Label         label1         = new Label();
  Label         label2         = new Label();
  Label         label3         = new Label();
  BorderLayout  borderLayout2  = new BorderLayout();

  protected void setResources() {
  button_plot = localProperties.getProperty("button.plot", button_plot);
  label_phase = localProperties.getProperty("label.phase", label_phase);
  label_yes = localProperties.getProperty("label.yes", label_yes);
  label_no = localProperties.getProperty("label.no", label_no);
}


/**
 * @y.exclude
 */
public Density(){

}

  /**
   * Method init
   * @y.exclude
   */
  public void init() {
    initResources(null);
    try {
      m = Integer.parseInt(this.getParameter("m", "0"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      n = Integer.parseInt(this.getParameter("n", "1"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      sc = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      l = Integer.parseInt(this.getParameter("l", "0"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    try {
      try {
        ph = Boolean.valueOf(this.getParameter("ShowPhase", "false")).booleanValue();
      } catch(Exception e) {
        e.printStackTrace();
      }
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    mValue.setValue(m);
    nValue.setValue(n);
    lValue.setValue(l);
    checkNLM();
    yphase.setState(ph);
    nphase.setState(!ph);
    densityCanvas.setM(mValue.getValue());
    densityCanvas.setN(nValue.getValue());
    densityCanvas.setL(lValue.getValue());
    densityCanvas.setPhase(ph);
    southetchdbrdr.setVisible(sc);
  }

  /**
   * Method setShowControls
   *
   * @param sc
   */
  public void setShowControls(boolean sc) {
    southetchdbrdr.setVisible(sc);
    this.invalidate();
    this.validate();
  }

  private void jbInit() throws Exception {
	  /** j2sNative */{
		  this.setSize(new Dimension(359, 343));
	  }
    flowLayout2.setVgap(2);
    flowLayout2.setHgap(2);
    plotBtn.setLabel("Plot");
    yphase.setCheckboxGroup(phasegroup);
    nphase.setCheckboxGroup(phasegroup);
    label4.setText("Phase:");
    flowLayout3.setVgap(2);
    flowLayout3.setHgap(2);
    label1.setAlignment(2);
    label1.setText("m=");
    label2.setText("n=");
    label3.setText("l=");
    label3.setAlignment(2);
    label2.setAlignment(2);
    panel2.setLayout(flowLayout3);
    label4.setAlignment(2);
    nphase.setLabel("N");
    yphase.setLabel("Y");
    plotBtn.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        plotBtn_actionPerformed(e);
      }
    });
    panel1.setLayout(flowLayout2);
    southetchdbrdr.setLayout(borderLayout2);
    this.setLayout(borderLayout1);
    this.add(southetchdbrdr, BorderLayout.SOUTH);
    southetchdbrdr.add(panel2, BorderLayout.CENTER);
    panel2.add(label2, null);
    panel2.add(nValue, null);
    panel2.add(label3, null);
    panel2.add(lValue, null);
    panel2.add(label1, null);
    panel2.add(mValue, null);
    southetchdbrdr.add(panel1, BorderLayout.SOUTH);
    panel1.add(label4, null);
    panel1.add(yphase, null);
    panel1.add(nphase, null);
    panel1.add(plotBtn, null);
    this.add(densityCanvas, BorderLayout.CENTER);
  }

  /**
   * Method start
   * @y.exclude
   */
  public void start() {
    if(firstTime) {
      setNLM(n, l, m);
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
    String pinfo[][] = {
      {"m", "int", "magnetic quantum number"}, {"n", "int", "principle quantum number"},
      {"l", "int", "angular quantum number"},
    };
    return pinfo;
  }

  /**
   * Starts a new calculation.
   *
   * This method is called when the plot button is pressed.
   * It kills the current thread and starts a new one
   */
  public void recalculate() {
	densityCanvas.run();
//    densityCanvas.stop();  //this will stop calculation in progress if button is pressed while calculations are still going
//    densityCanvas.start();
  }

  /**
   * This method gathers all of the users chosen values and starts a thread that
   * calculates the wave functions according to their parameters
   * This method calls recalculate() method
   * @param n
   * @param l
   * @param m
   */
  public void setNLM(int n, int l, int m) {
    nValue.setValue(n);
    lValue.setValue(l);
    mValue.setValue(m);
    densityCanvas.setM(mValue.getValue());
    densityCanvas.setN(nValue.getValue());
    densityCanvas.setL(lValue.getValue());
	if (!checkNLM())
		return;
    recalculate();
  }

	/**
	 * Method plotBtn_actionPerformed
	 *
	 * @param e
	 */
	void plotBtn_actionPerformed(ActionEvent e) {
		n = nValue.getValue();
		l = lValue.getValue();
		m = mValue.getValue();
		if (!checkNLM())
			return;
		densityCanvas.setM(mValue.getValue());
		densityCanvas.setN(nValue.getValue());
		densityCanvas.setL(lValue.getValue());
		densityCanvas.setPhase(yphase.getState());
		recalculate();
	}


	/**
	 * Added by Bob Hanson -- allows setting color red immediately
	 * 
	 * @return true if n, l, m are OK
	 */
	protected boolean checkNLM() {
		int n = nValue.getVal();
		int l = lValue.getVal();
		int m = mValue.getVal();
		if (nValue.getBackground().equals(Color.red))
			nValue.setBackground(Color.white);
		if (lValue.getBackground().equals(Color.red))
			lValue.setBackground(Color.white);
		if (mValue.getBackground().equals(Color.red))
			mValue.setBackground(Color.white);
		boolean isOK = true;
		if (n < 1 || n > 50) { // BH fix
			nValue.setBackground(Color.red);
			isOK = false;
		}
		if (l > (n - 1) | l < 0) {
			lValue.setBackground(Color.red);
			isOK = false;
		}
		if (Math.abs(m) > l) {
			mValue.setBackground(Color.red);
			isOK = false;
		}
		return isOK;
	}
}
