package slider;
import a2s.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import edu.davidson.display.SNumber;
import edu.davidson.display.SSlider;
import edu.davidson.tools.SApplet;
import edu.davidson.tools.SDataSource;
import netscape.javascript.JSObject;

/**
 * Class SliderApplet
 */
public class SliderApplet extends SApplet implements SDataSource, PropertyChangeListener {

	double min;
	double max;
	double value;
	String text;
	SSlider slider = new SSlider();
	SNumber number = new SNumber();
	Label label = new Label();
	BorderLayout borderLayout1 = new BorderLayout();
	Object lock = new Object();
	Dispatcher dispatcherThread;
	Create createThread;
	boolean newdata = false;
	boolean appletRunning = true;
	// data source variables
	protected String[] varStrings = new String[] { "t", "value", };
	protected double[][] ds = new double[1][2]; // the datasource state
												// variables x,y;

	/**
	 * Constructor SliderApplet
	 * 
	 * @y.exclude
	 */
	public SliderApplet() {
	}

	/**
	 * Method init
	 * 
	 * @y.exclude
	 */
	public void init() {
		boolean sc = true;
		try {
			text = this.getParameter("label", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			min = Double.valueOf(this.getParameter("min", "0")).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			max = Double.valueOf(this.getParameter("max", "1.0")).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			value = Double.valueOf(this.getParameter("value", "0.5")).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			sc = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		number.addPropertyChangeListener(slider);
		slider.addPropertyChangeListener(number);
		slider.addPropertyChangeListener(this);
		number.addPropertyChangeListener(this);
		slider.setDMax(max);
		slider.setDMin(min);
		slider.setDValue(value);
		number.setNoColor(true);
		number.setVisible(sc);
		if (text.equals("")) {
			label.setVisible(false);
		} else {
			label.setVisible(true);
		}
		try {
			SApplet.addDataSource(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Component initialization
	private void jbInit() throws Exception {
		this.setBackground(Color.lightGray);
		 /** @j2sNative */{	 
			 this.setSize(new Dimension(399, 41));
		 }
		label.setText(text);
		this.setLayout(borderLayout1);
		this.add(slider, BorderLayout.CENTER);
		this.add(number, BorderLayout.EAST);
		this.add(label, BorderLayout.WEST);
	}

	/**
	 * Method getAppletInfo
	 *
	 *
	 * @return the info
	 * @y.exclude
	 */
	public String getAppletInfo() {
		return "Applet Information";
	}

	/**
	 * Method getParameterInfo
	 *
	 *
	 * @return the info
	 * @y.exclude
	 */
	public String[][] getParameterInfo() {
		String pinfo[][] = { { "min", "double", "minimum" }, { "max", "double", "maximum" },
				{ "value", "double", "current value" }, };
		return pinfo;
	}

	/**
	 * Method start
	 * 
	 * @y.exclude
	 */
	public void start() {
		super.start();
		if (firstTime) {
			firstTime = false;
			// createThread = new Create();
		}
	}

	/**
	 * Gets the slider value.
	 *
	 *
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the slider value
	 *
	 * @param v
	 */
	public void setValue(double v) {
		slider.setDValue(v);
		number.setValue(v);
		value = slider.getDValue();
		return;
	}

	/**
	 * Gets the slider's maxumum value.
	 *
	 *
	 * @return the max value
	 */
	public double getMax() {
		return slider.getDMax();
	}

	/**
	 * Sets the sliders maximum value.
	 *
	 * @param v
	 */
	public void setMax(double v) {
		slider.setDMax(v);
		value = slider.getDValue();
		return;
	}

	/**
	 * Gets the slider's minimim value.
	 *
	 *
	 * @return the min value
	 */
	public double getMin() {
		return slider.getDMin();
	}

	/**
	 * Sets the slider's minimum value.
	 *
	 * @param v
	 */
	public void setMin(double v) {
		slider.setDMin(v);
		value = slider.getDValue();
		return;
	}

	/**
	 * Gets the label.
	 *
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label.getText();
	}

	/**
	 * Method setLabel
	 *
	 * @param s
	 */
	public void setLabel(String s) {
		label.setText(s);
		if (s.equals("")) {
			label.setVisible(false);
		} else {
			label.setVisible(true);
		}
		this.invalidate();
		this.validate();
		return;
	}

	/**
	 * Change the object's format for the display of numeric data.
	 *
	 * Us this method to control the number of significant digits in
	 * calculations with text objects. Use Unix printf conventions. For example
	 * fstr="%6.3f"
	 *
	 * @param id
	 *            The id of the object.
	 * @param fstr
	 *            the format string.
	 */
	public void setFormat(int id, String fstr) {
		this.number.setFormat(fstr);
	}

	/**
	 * Sets default values and deletes all data connections.
	 */
	public void setDefault() {
		Create temp = createThread;
		Dispatcher temp2 = dispatcherThread;
		if (temp != null) { // stop the old threads if they exisit
			temp.shouldRun = false;
		}
		if (temp2 != null) { // stop the old threads if they exisit
			temp2.shouldRun = false;
		}
		deleteDataConnections(); // we are going to delete all the things so we
									// might as well kill the conections too.
	}

	/**
	 * Destroy all threads and cleanup the applet.
	 * 
	 * @y.exclude
	 */
	public void destroy() {
		appletRunning = false;
		Create temp = createThread;
		Dispatcher temp2 = dispatcherThread;
		if (temp != null) {
			temp.shouldRun = false;
		}
		if (temp2 != null) {
			temp2.shouldRun = false;
		}
		super.destroy();
	}

	/**
	 * Method propertyChange
	 *
	 * @param evt
	 * @y.exclude
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof SSlider) {
			value = slider.getDValue();
		} else {
			value = number.getValue();
		}
		this.updateDataConnections();
		synchronized (lock) {
			newdata = true;
			lock.notify();  
		}
	}

	/**
	 * Change the JavaScript function that should be called whenever data
	 * changes in a data source.
	 *
	 * @param str
	 *            The javascript function
	 */
	public void setJSFunction(String str) {
		str=null;
		Create temp = createThread;
		Dispatcher temp2 = dispatcherThread;
		if (temp != null) { // stop the old threads if they exist
			temp.shouldRun = false;
		}
		if (temp2 != null) { // stop the old threads if they exist
			temp2.shouldRun = false;
		}
		if (appletRunning)
			createThread = new Create(str);
	}

	/**
	 * Method setOwner
	 *
	 * @param o
	 * @y.exclude
	 */
	public void setOwner(SApplet o) {
		;
	}

	/**
	 * Method getOwner
	 *
	 *
	 * @return the owner
	 * @y.exclude
	 */
	public SApplet getOwner() {
		return this;
	}

	/**
	 * Method getVarStrings
	 * 
	 * @return the strings
	 * @y.exclude
	 */
	public String[] getVarStrings() {
		return varStrings;
	}

	/**
	 * Method getVariables
	 * 
	 * @return the variables
	 * @y.exclude
	 */
	public double[][] getVariables() {
		ds[0][0] = clock.getTime(); // x
		ds[0][1] = value; // x
		return ds;
	}

	class Dispatcher extends Thread {
		boolean shouldRun = true;
		String jsFunction;
		JSObject jso;

		Dispatcher(String str, JSObject obj) {
			jsFunction = str;
			jso = obj;
			if (jso == null || jsFunction == null || jsFunction.trim().equals(""))
				return;
			setDaemon(true);
			start();
		}

		public void run() {
			while (appletRunning && shouldRun) {
				synchronized (lock) {
					if (!newdata)
						try {
							lock.wait();
						} catch (InterruptedException ie) {
						}
					newdata = false;
				} 
				if (debugLevel > 0)
					System.out.println("evaluating");
				if (appletRunning && shouldRun)
					jso.eval(jsFunction);
				try {
					Thread.sleep(20);
				} catch (InterruptedException ex) {
				}
			}
		}
	}

	class Create extends Thread {
		boolean shouldRun = true;
		String jsFunction;

		Create(String str) {
			jsFunction = str;
			if (jsFunction == null || jsFunction.trim().equals(""))
				return;
			setDaemon(true);
			start();
		}

		public void run() {
			JSObject jso = null;
			while ((jso == null) && appletRunning && shouldRun) {
				try {
					Thread.sleep(10);
					jso = JSObject.getWindow(SliderApplet.this);
				} catch (InterruptedException ex) {
				} catch (Exception e) {
					System.out.println("JSObject not created.");
					return;
				}
			}
			// we have a js window object so start thread
			if (debugLevel > 0)
				System.out.println("creating dispatcher");
			if (appletRunning && shouldRun)
				dispatcherThread = new Dispatcher(jsFunction, jso);
		}
	}
}
