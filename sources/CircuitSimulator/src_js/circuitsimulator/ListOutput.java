package circuitsimulator;

import a2s.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Used by circuitBuilder. Dialog window for the display of the element list.
 * 
 * @author Toon Van Hoecke
 */
public class ListOutput extends java.awt.Dialog 
{
    CircuitBuilder cb = null;
	
	public ListOutput(Frame parent)
	{
		super(parent);
		
		//{{INIT_CONTROLS
		setLayout(null);
		setSize(473,282);
		setVisible(false);
		add(addobjectList);
		addobjectList.setBounds(5,7,460,266);
		//}}
	
		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		//}}
	}

	public ListOutput()
	{
		this((Frame)null);
	}

	public ListOutput(String sTitle, String s, Frame parent, CircuitBuilder cirbuilder)
	{
		this(parent);
		cb = cirbuilder;
		setTitle(sTitle);
		addobjectList.setText(s);
		setVisible(true);
	}

	public void addNotify()
	{
		// Record the size of the window prior to calling parents addNotify.
		Dimension d = getSize();

		super.addNotify();

		if (fComponentsAdjusted)
			return;

		// Adjust size of frame according to the insets
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

	//{{DECLARE_CONTROLS
	TextArea addobjectList = new a2s.TextArea();
	//}}

	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == ListOutput.this)
				ListOutput_WindowClosing(event);
		}
	}

	void ListOutput_WindowClosing(java.awt.event.WindowEvent event)
	{
		try {
			// ListOutput Hide the ListOutput
			this.setVisible(false);
		} catch (java.lang.Exception e) {}
	}
}