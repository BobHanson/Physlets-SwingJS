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
public class Meter extends java.awt.Dialog
{
    CircuitBuilder cb = null;
    int elementID;
    CircuitElement ce;
    Format format;
    String type="";
        
	public Meter(Frame parent)
	{
		super(parent);
		//{{INIT_CONTROLS
		setLayout(null);
		setSize(192,77);
		setVisible(false);
		numeric.setText("0.0");
		numeric.setAlignment(a2s.Label.RIGHT);
		add(numeric);
		numeric.setBackground(java.awt.Color.black);
		numeric.setForeground(java.awt.Color.yellow);
		numeric.setFont(new Font("MonoSpaced", Font.BOLD, 12));
		numeric.setBounds(12,12,144,24);
		scale.setText("V");
		scale.setAlignment(a2s.Label.RIGHT);
		add(scale);
		scale.setBackground(java.awt.Color.black);
		scale.setForeground(java.awt.Color.yellow);
		scale.setFont(new Font("MonoSpaced", Font.BOLD, 12));
		scale.setBounds(156,12,24,24);
		try {
			{
				String[] tempString = new String[2];
				tempString[0] = "DC";
				tempString[1] = "AC";
				mode.setListItems(tempString);
			}
		}
		catch(java.beans.PropertyVetoException e) { }
		add(mode);
		mode.setBounds(68,46,61,22);
		setTitle("Meter");
		//}}
        format = new Format("%6.4f");
		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		mode.addActionListener(lSymAction);
		//}}
	}

	public Meter(Frame parent, boolean modal)
	{
		this(parent);
		setModal(modal);
	}

	public Meter(Frame parent, String title, boolean modal)
	{
		this(parent, modal);
		setTitle(title);
	}

	public Meter(String t, CircuitBuilder cirbuilder, Frame parent)
	{
		this(parent);
		cb = cirbuilder;
		elementID = cb.currentElement.hashCode();
        ce = cb.currentElement;
		type += t;
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
			if (object == Meter.this)
				Meter_WindowClosing(event);
		}
	}

	void Meter_WindowClosing(java.awt.event.WindowEvent event)
	{
		cb.meterList.removeElement(this);
		dispose();
		
	}
	//{{DECLARE_CONTROLS
	Label numeric = new a2s.Label();
	Label scale = new a2s.Label();
	symantec.itools.awt.util.spinner.ListSpinner mode = new symantec.itools.awt.util.spinner.ListSpinner();
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
		double v=0.0;
		if (mode.getCurrentText().equals("AC")) {
		    if (type.equals("V")) v = cb.getVrms(elementID);
		    else if (type.equals("A")) v = cb.getIrms(elementID);
		} else {
		    if (type.equals("V")) v = ce.getV();
		    else if (type.equals("A")) v = ce.getI();
            v = v*((ce.direction == ce.vequation.direction)? 1 : -1);
		}
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
}