package circuitsimulator;

import a2s.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import edu.davidson.display.Format;

/**
 * Used by circuitbuilder. Dialog window simulating the display of a voltmeter or 
 * an amperemeter for the pointed element.
 * 
 * @author Toon Van Hoecke
 */
public class ValueKnob extends java.awt.Dialog
{
    CircuitBuilder cb = null;
    int elementID;
    CircuitElement ce;
    Format format;
    String type="";
    double initialv = 1.0;
        
	public ValueKnob(Frame parent)
	{
		super(parent);
		//{{INIT_CONTROLS
		setLayout(null);
		setSize(180,57);
		setVisible(false);
		numeric.setText("0.0");
		numeric.setAlignment(a2s.Label.RIGHT);
		add(numeric);
		numeric.setBackground(java.awt.Color.black);
		numeric.setForeground(java.awt.Color.yellow);
		numeric.setFont(new Font("MonoSpaced", Font.BOLD, 12));
		numeric.setBounds(6,27,133,24);
		add(scale);
		scale.setBackground(java.awt.Color.black);
		scale.setForeground(java.awt.Color.yellow);
		scale.setFont(new Font("MonoSpaced", Font.BOLD, 12));
		scale.setBounds(139,27,35,24);
		try {
			{
				String[] tempString = new String[2];
				tempString[0] = "lin";
				tempString[1] = "log";
				mode.setListItems(tempString);
			}
		}
		catch(java.beans.PropertyVetoException e) { }
		add(mode);
		mode.setFont(new Font("Dialog", Font.PLAIN, 10));
		mode.setBounds(4,1,54,25);
		add(scrollbar);
		scrollbar.setBounds(60,3,117,21);
		setTitle("Meter");
		//}}
        format = new Format("%6.4f");
		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		mode.addActionListener(lSymAction);
		SymAdjustment lSymAdjustment = new SymAdjustment();
		scrollbar.addAdjustmentListener(lSymAdjustment);
		//}}
	}

	public ValueKnob(Frame parent, boolean modal)
	{
		this(parent);
		setModal(modal);
	}

	public ValueKnob(Frame parent, String title, boolean modal)
	{
		this(parent, modal);
		setTitle(title);
	}

	public ValueKnob(String t, CircuitBuilder cirbuilder, Frame parent)
	{
		this(parent);
		cb = cirbuilder;
		elementID = cb.currentElement.hashCode();
        ce = cb.currentElement;
		if (t.equals("Hz")) type += t;
		else type += ce.getunity();
        if (type.equals("Hz")) initialv = ce.frequency;
        else initialv = Double.valueOf(ce.getvalue()).doubleValue();
		recalc();
		setTitle(cb.cirProp.getProperty(ce.getMyName())+" "+ce.getlabel());
		setVisible(true);
	}

	public void addNotify()
	{
		// Record the size of the window prior to calling parents addNotify.
		Dimension d = getSize();

		super.addNotify();

		if (fComponentsAdjusted)
			return;

		// Adjust components according to the insets
		Insets ins = getInsets();
		setSize(ins.left + ins.right + d.width, ins.top + ins.bottom + d.height);
		Component components[] = getComponents();
		for (int i = 0; i < components.length; i++)
		{
			Point p = components[i].getLocation();
			p.translate(ins.left, ins.top);
			components[i].setLocation(p);
		}
		fComponentsAdjusted = true;
	}

	// Used for addNotify check.
	boolean fComponentsAdjusted = false;

	public void setVisible(boolean b)
	{
		if (b)
		{
			Rectangle bounds = getParent().getBounds();
			Rectangle abounds = getBounds();

			setLocation(bounds.x + (bounds.width - abounds.width)/ 2,
				bounds.y + (bounds.height - abounds.height)/2);
		}
		super.setVisible(b);
	}

	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == ValueKnob.this)
				Meter_WindowClosing(event);
		}
	}

	void Meter_WindowClosing(java.awt.event.WindowEvent event)
	{
		dispose();
		
	}
	//{{DECLARE_CONTROLS
	Label numeric = new a2s.Label();
	Label scale = new a2s.Label();
	symantec.itools.awt.util.spinner.ListSpinner mode = new symantec.itools.awt.util.spinner.ListSpinner();
	Scrollbar scrollbar = new a2s.Scrollbar(Scrollbar.HORIZONTAL,50,1,1,101);
	//}}

    public void setDisplay(double v) {
        if (Math.abs(v) < 1E-8) {
            scale.setText("n"+type);
            numeric.setText(format.form(v*1e9));
        } else if (Math.abs(v) < 1E-4) {
            scale.setText("�"+type);
            numeric.setText(format.form(v*1e6));
        } else if (Math.abs(v) < 1E-1) {
            scale.setText("m"+type);
            numeric.setText(format.form(v*1e3));
        } else if (Math.abs(v) < 1E3) {
            scale.setText(type);
            numeric.setText(format.form(v));
        } else if (Math.abs(v) < 1E6) {
            scale.setText("k"+type);
            numeric.setText(format.form(v*1e-3));
        } else {
            scale.setText("M"+type);
            numeric.setText(format.form(v*1e-6));
        }
    }
    
    public void recalc() {
		double v=0.0, scrollValue;
		scrollValue = (double)scrollbar.getValue()*2.0/(scrollbar.getMaximum()-1);
		if (mode.getCurrentText().equals("lin")) {
		    v = initialv*scrollValue;
		} else {
		    double initiallog = Math.rint(Math.log(initialv)/Math.log(10.0));
		    double basev = initialv/Math.exp(initiallog*Math.log(10.0));
		    double log10 = initiallog+5.0*(scrollValue-1.0);
		    v = basev*Math.exp(log10*Math.log(10.0));
		}
        if (type.equals("Hz")) {
            cb.setDT(0.001/v);
            cb.set(ce.hashCode(),"freq="+Double.toString(v));
            cb.builderPanel.updateTextFields();
        } else cb.setValue(ce.hashCode(),Double.toString(v));
        cb.calculateCircuit();
        cb.repaintMeters();
        setDisplay(v);		
    }
        

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == mode)
				mode_actionPerformed(event);
		}
	}

	void mode_actionPerformed(java.awt.event.ActionEvent event)
	{
		this.recalc();
	}

	class SymAdjustment implements java.awt.event.AdjustmentListener
	{
		public void adjustmentValueChanged(java.awt.event.AdjustmentEvent event)
		{
			Object object = event.getSource();
			if (object == scrollbar)
				scrollbar_AdjustmentValueChanged(event);
		}
	}

	void scrollbar_AdjustmentValueChanged(java.awt.event.AdjustmentEvent event)
	{
		recalc();
	}
}