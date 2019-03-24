package circuitsimulator;

//import a2s.*;
import java.awt.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Used by circuitbuilder. Dialog for changing the value of the pointed element
 * 
 * @author Toon Van Hoecke
 */
public class ValueInput extends Dialog 
{
    boolean changeOK = false;
    CircuitBuilder cb = null;
    CircuitElement ce = null;
    String mode = "Properties";

	public ValueInput(Frame parent)
	{
		super(parent, "", false);
		//{{INIT_CONTROLS
		setLayout(null);
		setSize(306,120);
		setVisible(false);
		add(buttonOK);
		buttonOK.setBackground(java.awt.Color.lightGray);
		buttonOK.setBounds(68,85,68,24);
		add(buttonCancel);
		buttonCancel.setBackground(java.awt.Color.lightGray);
		buttonCancel.setBounds(158,84,71,24);
		add(textInput);
		textInput.setBounds(7,7,287,71);
		//}}
	
		//{{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		buttonOK.addActionListener(lSymAction);
		buttonCancel.addActionListener(lSymAction);
		//}}
	}

	public ValueInput()
	{
		this((Frame)null);
	}

	public ValueInput(String sTitle, CircuitBuilder cirbuilder, Frame parent)
	{
		this(parent);
		cb = cirbuilder;
        ce = cb.currentElement;
		setTitle(sTitle);
        if (getTitle().equals(cb.cirProp.getProperty("changevalue_title"))) {
            textInput.setText(ce.get());
            mode = "Properties";
        } else if (getTitle().equals(cb.cirProp.getProperty("elemlabel_title"))) {
            textInput.setText(ce.getlabel());
            mode = "Label";
        }
		buttonOK.setLabel(cb.cirProp.getProperty("OK"));
		buttonCancel.setLabel(cb.cirProp.getProperty("Cancel"));
        setTitle(cb.cirProp.getProperty(ce.getMyName())+": "+sTitle);
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

	//{{DECLARE_CONTROLS
	Button buttonOK = new Button();
	Button buttonCancel = new Button();
	//TextArea textInput = new TextArea("",0,0, java.awt.TextArea.SCROLLBARS_VERTICAL_ONLY);
	TextArea textInput = new TextArea("",0,0);
	//}}

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == buttonOK)
				buttonOK_ActionPerformed(event);
			else if (object == buttonCancel)
				buttonCancel_ActionPerformed(event);
		}
	}

	void buttonOK_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    changeOK = true;
        if (mode.equals("Properties")) {
            cb.set(cb.currentElement.hashCode(),textInput.getText());
            cb.calculateCircuit();
            cb.repaintMeters();
        } else if (mode.equals("Label")) {
            cb.setLabel(cb.currentElement.hashCode(),textInput.getText());
        }
	    this.setVisible(false);
	}


	void buttonCancel_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    changeOK = false;
	    this.setVisible(false);
	}

	public boolean getchangeOK() {
	    return changeOK;
	}
}