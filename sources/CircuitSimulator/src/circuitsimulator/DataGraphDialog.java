package circuitsimulator;

import a2s.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;

import edu.davidson.display.*;


/**
 * Used by circuitbuilder. Dialog window simulating an oscilloscope for the pointed element.
 * 
 * @author Toon Van Hoecke
 */
public class DataGraphDialog extends a2s.Dialog
{
    public String type;
    CircuitBuilder cb;
    CircuitElement ce;
    
	public DataGraphDialog(Frame parent, CircuitBuilder cirbuilder, String s)
	{
		super(parent, "",false);
		cb = cirbuilder;
		ce = cb.currentElement;
		type = ""+s;
		String title;
		if (type.equals("v")) title = cb.cirProp.getProperty("vgraph_title"); 
		else title = cb.cirProp.getProperty("igraph_title");
		setTitle(title+" "+cb.cirProp.getProperty(ce.getMyName())+" "+ce.getlabel());
		periodInput.setText(Double.toString(cb.numberofdt*cb.dt));

		//{{INIT_CONTROLS
		setLayout(null);
		setBackground(new java.awt.Color(220,200,160));
		setSize(550,393);
		setVisible(false);
		try {
			borderCanvas.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderCanvas.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderCanvas.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderCanvas.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderCanvas.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderCanvas.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderCanvas.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderCanvas.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		borderCanvas.setLayout(null);
		add(borderCanvas);
		borderCanvas.setBackground(java.awt.Color.white);
		borderCanvas.setBounds(10,8,525,344);
		add(stripChart);
		stripChart.setBackground(java.awt.Color.lightGray);
		stripChart.setBounds(11,359,107,24);
		add(periodInput);
		periodInput.setBounds(248,362,119,21);
		label1.setAlignment(java.awt.Label.RIGHT);
		add(label1);
		label1.setBounds(132,361,110,23);
		//}}
		borderCanvas.add(dataGraph);
		dataGraph.setBounds(15,18,500,320);
		
		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		stripChart.addActionListener(lSymAction);
		//}}
		
		stripChart.setLabel(cb.cirProp.getProperty("gmode_strip"));
		label1.setText(cb.cirProp.getProperty("twindow"));
        dataGraph.clearSeries(1);
        dataGraph.setSeriesStyle(1,true,0);
        dataGraph.setLabelX(cb.cirProp.getProperty("taxis"));  
		if (type.equals("v")) dataGraph.setLabelY(cb.cirProp.getProperty("vaxis")); 
		else dataGraph.setLabelY(cb.cirProp.getProperty("iaxis"));
	}
	
	public DataGraphDialog(CircuitBuilder cirbuilder)
	{
		this((Frame)null, cirbuilder, "v");
	}
	
	public void addNotify()
	{
  	    // Record the size of the window prior to calling parents addNotify.
	    Dimension d = getSize();

		super.addNotify();

		if (fComponentsAdjusted)
			return;

		// Adjust components according to the insets
		Insets insets = getInsets();
		setSize(insets.left + insets.right + d.width, insets.top + insets.bottom + d.height);
		Component components[] = getComponents();
		for (int i = 0; i < components.length; i++)
		{
			Point p = components[i].getLocation();
			p.translate(insets.left, insets.top);
			components[i].setLocation(p);
		}
		fComponentsAdjusted = true;
	}

    // Used for addNotify check.
	boolean fComponentsAdjusted = false;

    /**
     * Shows or hides the component depending on the boolean flag b.
     * @param b  if true, show the component; otherwise, hide the component.
     * @see java.awt.Component#isVisible
     */
    public void setVisible(boolean b)
	{
		if(b) setLocation(100, 100);
		super.setVisible(b);
	}

	//{{DECLARE_CONTROLS
	symantec.itools.awt.BorderPanel borderCanvas = new symantec.itools.awt.BorderPanel();
	Button stripChart = new a2s.Button();
	TextField periodInput = new a2s.TextField();
	Label label1 = new a2s.Label();
	//}}
	SGraph dataGraph = new SGraph();

	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == DataGraphDialog.this) {
		        setVisible(false);
		    }
		}
	}
	
    public void addData(){
        dataGraph.addDatum(1,cb.realt,type.equals("v")?ce.getV():ce.getI());
    }
    
    public void clearGraph(){
        dataGraph.setAutoReplaceData(1,false);
        dataGraph.clearSeriesData(1);
        dataGraph.setAutoRefresh(true);
        dataGraph.setAutoReplaceData(1,true);
    }

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == stripChart)
				stripChart_ActionPerformed(event);
		}
	}

	void stripChart_ActionPerformed(java.awt.event.ActionEvent event)
	{
		if (stripChart.getLabel().equals(cb.cirProp.getProperty("gmode_strip"))) {
		    int nop = (int) (Double.valueOf(periodInput.getText()).doubleValue()/(cb.dt*cb.noc));
		    dataGraph.setSeriesStripChart(1, nop, true);
		    stripChart.setLabel(cb.cirProp.getProperty("gmode_full"));
		} else {
		    dataGraph.setSeriesStripChart(1, 100, false);
		    stripChart.setLabel(cb.cirProp.getProperty("gmode_strip"));
        }			 
	}
}