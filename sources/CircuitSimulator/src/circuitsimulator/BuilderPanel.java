package circuitsimulator;

import a2s.*;

import java.awt.Component;
import java.awt.Font;
import java.awt.Point;

import symantec.itools.awt.BorderPanel;

/**
 * Used by circuitbuilder. Representation of the tools panel.
 * 
 * @author Toon Van Hoecke
 */
public class BuilderPanel extends symantec.itools.awt.BorderPanel
{   
    CircuitBuilder circuitBuilder=null;
    String selectedComponent ="";
    
	public BuilderPanel()
	{
		//{{INIT_CONTROLS
		try {
			setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			setBevelStyle(circuitsimulator.BuilderPanel.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		setLayout(null);
		setBackground(new java.awt.Color(0,143,213));
		setSize(195,401);
		setGridButton.setLabel("Set grid");
		add(setGridButton);
		setGridButton.setBackground(new java.awt.Color(0,169,251));
		setGridButton.setBounds(5,32,59,48);
		try {
			rowSpin.setMin(2);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			rowSpin.setCurrent(2);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			rowSpin.setMax(8);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(rowSpin);
		rowSpin.setBackground(java.awt.Color.yellow);
		rowSpin.setForeground(java.awt.Color.black);
		rowSpin.setBounds(64,32,28,24);
		try {
			colSpin.setMin(2);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			colSpin.setCurrent(2);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			colSpin.setMax(5);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(colSpin);
		colSpin.setBackground(java.awt.Color.yellow);
		colSpin.setBounds(64,56,28,24);
		label1.setText("rows");
		add(label1);
		label1.setFont(new Font("Dialog", Font.PLAIN, 10));
		label1.setBounds(93,32,44,24);
		label3.setText("cols");
		add(label3);
		label3.setFont(new Font("Dialog", Font.PLAIN, 10));
		label3.setBounds(93,56,44,22);
		try {
			resistorBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			resistorBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			resistorBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			resistorBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			resistorBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			resistorBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			resistorBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			resistorBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		resistorBorder.setLayout(null);
		add(resistorBorder);
		resistorBorder.setBackground(new java.awt.Color(0,169,251));
		resistorBorder.setBounds(5,99,56,28);
		try {
			capacitorBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			capacitorBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			capacitorBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			capacitorBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			capacitorBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			capacitorBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			capacitorBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			capacitorBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		capacitorBorder.setLayout(null);
		add(capacitorBorder);
		capacitorBorder.setBackground(new java.awt.Color(0,169,251));
		capacitorBorder.setBounds(5,127,56,28);
		try {
			inductorBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			inductorBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			inductorBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			inductorBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			inductorBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			inductorBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			inductorBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			inductorBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		inductorBorder.setLayout(null);
		add(inductorBorder);
		inductorBorder.setBackground(new java.awt.Color(0,169,251));
		inductorBorder.setBounds(5,155,56,28);
		try {
			wireBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			wireBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			wireBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			wireBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			wireBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			wireBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			wireBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			wireBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		wireBorder.setLayout(null);
		add(wireBorder);
		wireBorder.setBackground(new java.awt.Color(0,169,251));
		wireBorder.setBounds(69,127,56,28);
		try {
			sourceBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sourceBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sourceBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sourceBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sourceBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sourceBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sourceBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sourceBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		sourceBorder.setLayout(null);
		add(sourceBorder);
		sourceBorder.setBackground(new java.awt.Color(0,169,251));
		sourceBorder.setBounds(5,226,56,28);
		listButton.setLabel("List");
		add(listButton);
		listButton.setBackground(new java.awt.Color(0,169,251));
		listButton.setBounds(138,32,51,23);
		calculateButton.setLabel("Calculate");
		add(calculateButton);
		calculateButton.setBackground(new java.awt.Color(0,169,251));
		calculateButton.setBounds(6,372,67,24);
		try {
			scopeBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			scopeBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			scopeBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			scopeBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			scopeBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			scopeBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			scopeBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			scopeBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		scopeBorder.setLayout(null);
		add(scopeBorder);
		scopeBorder.setBackground(new java.awt.Color(0,169,251));
		scopeBorder.setBounds(133,226,56,28);
		try {
			vmeterBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vmeterBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vmeterBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vmeterBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vmeterBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vmeterBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vmeterBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vmeterBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		vmeterBorder.setLayout(null);
		add(vmeterBorder);
		vmeterBorder.setBackground(new java.awt.Color(0,169,251));
		vmeterBorder.setBounds(133,254,56,28);
		try {
			ameterBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ameterBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ameterBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ameterBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ameterBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ameterBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ameterBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ameterBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		ameterBorder.setLayout(null);
		add(ameterBorder);
		ameterBorder.setBackground(new java.awt.Color(0,169,251));
		ameterBorder.setBounds(133,282,56,28);
		try {
			switchBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			switchBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			switchBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			switchBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			switchBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			switchBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			switchBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			switchBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		switchBorder.setLayout(null);
		add(switchBorder);
		switchBorder.setBackground(new java.awt.Color(0,169,251));
		switchBorder.setBounds(69,155,56,28);
		try {
			batteryBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			batteryBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			batteryBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			batteryBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			batteryBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			batteryBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			batteryBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			batteryBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		batteryBorder.setLayout(null);
		add(batteryBorder);
		batteryBorder.setBackground(new java.awt.Color(0,169,251));
		batteryBorder.setBounds(5,254,56,28);
		numberInput.setText("1e3");
		add(numberInput);
		numberInput.setBackground(java.awt.Color.white);
		numberInput.setFont(new Font("Dialog", Font.PLAIN, 10));
		numberInput.setBounds(141,346,47,20);
		dtInput.setText("1e-6");
		add(dtInput);
		dtInput.setBackground(java.awt.Color.white);
		dtInput.setFont(new Font("Dialog", Font.PLAIN, 10));
		dtInput.setBounds(40,347,72,19);
		label9.setText("#:");
		add(label9);
		label9.setBounds(126,343,16,23);
		label10.setText("dt (s):");
		add(label10);
		label10.setBounds(5,344,34,24);
		arrows.setLabel("Show ->");
		add(arrows);
		arrows.setBackground(new java.awt.Color(0,169,251));
		arrows.setBounds(139,58,50,23);
		loadButton.setLabel("Load");
		add(loadButton);
		loadButton.setBackground(new java.awt.Color(0,169,251));
		loadButton.setBounds(6,5,52,25);
		inputfile.setText("lists/default.txt");
		add(inputfile);
		inputfile.setBackground(java.awt.Color.white);
		inputfile.setBounds(59,5,129,24);
		try {
			bulbBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			bulbBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			bulbBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			bulbBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			bulbBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			bulbBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			bulbBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			bulbBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		bulbBorder.setLayout(null);
		add(bulbBorder);
		bulbBorder.setBackground(new java.awt.Color(0,169,251));
		bulbBorder.setBounds(69,99,56,28);
		forwarding.setLabel("Start");
		add(forwarding);
		forwarding.setBackground(new java.awt.Color(0,169,251));
		forwarding.setBounds(75,371,56,25);
		resetting.setLabel("Reset");
		add(resetting);
		resetting.setBackground(new java.awt.Color(0,169,251));
		resetting.setBounds(132,371,58,25);
		try {
			currentsourceBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentsourceBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentsourceBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentsourceBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentsourceBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentsourceBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentsourceBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentsourceBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		currentsourceBorder.setLayout(null);
		add(currentsourceBorder);
		currentsourceBorder.setBackground(new java.awt.Color(0,169,251));
		currentsourceBorder.setBounds(5,282,56,28);
		try {
			transformerBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			transformerBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			transformerBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			transformerBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			transformerBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			transformerBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			transformerBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			transformerBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		transformerBorder.setLayout(null);
		add(transformerBorder);
		transformerBorder.setBackground(new java.awt.Color(0,169,251));
		transformerBorder.setBounds(134,99,56,42);
		try {
			sinwaveBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sinwaveBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sinwaveBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sinwaveBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sinwaveBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sinwaveBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sinwaveBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			sinwaveBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		sinwaveBorder.setLayout(null);
		add(sinwaveBorder);
		sinwaveBorder.setBackground(new java.awt.Color(0,169,251));
		sinwaveBorder.setBounds(69,226,56,28);
		try {
			squarewaveBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			squarewaveBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			squarewaveBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			squarewaveBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			squarewaveBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			squarewaveBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			squarewaveBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			squarewaveBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		squarewaveBorder.setLayout(null);
		add(squarewaveBorder);
		squarewaveBorder.setBackground(new java.awt.Color(0,169,251));
		squarewaveBorder.setBounds(69,254,56,28);
		try {
			diodeBorder.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			diodeBorder.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			diodeBorder.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			diodeBorder.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			diodeBorder.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			diodeBorder.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			diodeBorder.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			diodeBorder.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		diodeBorder.setLayout(null);
		add(diodeBorder);
		diodeBorder.setBackground(new java.awt.Color(0,169,251));
		diodeBorder.setBounds(5,191,56,28);
		//}}
		wireBorder.add(wire);
		resistorBorder.add(resistor);
		inductorBorder.add(inductor);
		capacitorBorder.add(capacitor);
		switchBorder.add(switch1);
		bulbBorder.add(bulb);
		transformerBorder.add(transformercoil);
		diodeBorder.add(diode);
		sourceBorder.add(source);
		batteryBorder.add(battery);
		currentsourceBorder.add(currentsource);
		sinwaveBorder.add(sinwave);
		squarewaveBorder.add(squarewave);
		scopeBorder.add(scope);
		vmeterBorder.add(vmeter);
		ameterBorder.add(ameter);
		
		//{{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		setGridButton.addActionListener(lSymAction);
		SymMouse aSymMouse = new SymMouse();
		listButton.addActionListener(lSymAction);
		calculateButton.addActionListener(lSymAction);
		SymText lSymText = new SymText();
		numberInput.addTextListener(lSymText);
		dtInput.addTextListener(lSymText);
		arrows.addActionListener(lSymAction);
		loadButton.addActionListener(lSymAction);
		inputfile.addActionListener(lSymAction);
		forwarding.addActionListener(lSymAction);
		resetting.addActionListener(lSymAction);
		//}}
		resistor.addMouseListener(aSymMouse);
		wire.addMouseListener(aSymMouse);
		capacitor.addMouseListener(aSymMouse);
		inductor.addMouseListener(aSymMouse);
		switch1.addMouseListener(aSymMouse);
		bulb.addMouseListener(aSymMouse);
		transformercoil.addMouseListener(aSymMouse);
		diode.addMouseListener(aSymMouse);
		source.addMouseListener(aSymMouse);
		battery.addMouseListener(aSymMouse);
		currentsource.addMouseListener(aSymMouse);
		sinwave.addMouseListener(aSymMouse);
		squarewave.addMouseListener(aSymMouse);
		scope.addMouseListener(aSymMouse);
		vmeter.addMouseListener(aSymMouse);
		ameter.addMouseListener(aSymMouse);
	}

	//{{DECLARE_CONTROLS
	Button setGridButton = new a2s.Button();
	symantec.itools.awt.util.spinner.NumericSpinner rowSpin = new symantec.itools.awt.util.spinner.NumericSpinner();
	symantec.itools.awt.util.spinner.NumericSpinner colSpin = new symantec.itools.awt.util.spinner.NumericSpinner();
	Label label1 = new a2s.Label();
	Label label3 = new a2s.Label();
	Button listButton = new a2s.Button();
	Button calculateButton = new a2s.Button();
	TextField numberInput = new a2s.TextField();
	TextField dtInput = new a2s.TextField();
	Label label9 = new a2s.Label();
	Label label10 = new a2s.Label();
	Button arrows = new a2s.Button();
	Button loadButton = new a2s.Button();
	TextField inputfile = new a2s.TextField();
	Button forwarding = new a2s.Button();
	Button resetting = new a2s.Button();
	symantec.itools.awt.BorderPanel resistorBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel capacitorBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel inductorBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel wireBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel sourceBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel scopeBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel vmeterBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel ameterBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel switchBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel batteryBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel bulbBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel currentsourceBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel transformerBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel sinwaveBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel squarewaveBorder = new symantec.itools.awt.BorderPanel();
	symantec.itools.awt.BorderPanel diodeBorder = new symantec.itools.awt.BorderPanel();
	//}}
	String coordStr;
	circuitsimulator.Wire wire = new circuitsimulator.Wire();
	circuitsimulator.Resistor resistor = new circuitsimulator.Resistor();
	circuitsimulator.Capacitor capacitor = new circuitsimulator.Capacitor();
	circuitsimulator.Inductor inductor = new circuitsimulator.Inductor();
	circuitsimulator.Switch switch1 = new circuitsimulator.Switch();
	circuitsimulator.Bulb bulb = new circuitsimulator.Bulb();
	circuitsimulator.TransformerCoil transformercoil = new circuitsimulator.TransformerCoil();
	circuitsimulator.Diode diode = new circuitsimulator.Diode();
	circuitsimulator.Source source = new circuitsimulator.Source();
	circuitsimulator.Battery battery = new circuitsimulator.Battery();
	circuitsimulator.CurrentSource currentsource = new circuitsimulator.CurrentSource();
	circuitsimulator.SinWave sinwave = new circuitsimulator.SinWave();
	circuitsimulator.SquareWave squarewave = new circuitsimulator.SquareWave();
	circuitsimulator.Vmeter vmeter = new circuitsimulator.Vmeter();
	circuitsimulator.Ameter ameter = new circuitsimulator.Ameter();
	circuitsimulator.Scope scope = new circuitsimulator.Scope();
	
	public void setcircuitBuilder (CircuitBuilder cb) {
	    circuitBuilder=cb;
        label1.setText(cb.cirProp.getProperty("rows"));
        label3.setText(cb.cirProp.getProperty("cols"));
	    loadButton.setLabel(cb.cirProp.getProperty("Load"));
        setGridButton.setLabel(cb.cirProp.getProperty("setgrid"));
        listButton.setLabel(cb.cirProp.getProperty("List"));
        arrows.setLabel(cb.cirProp.getProperty("arrows"));
        calculateButton.setLabel(cb.cirProp.getProperty("Calculate"));
        forwarding.setLabel(cb.cirProp.getProperty("Start"));
        resetting.setLabel(cb.cirProp.getProperty("Reset"));
        dtInput.setText(Double.toString(circuitBuilder.dt));
	    numberInput.setText(Integer.toString(circuitBuilder.numberofdt));
	}
	
	public void loadImages() {
		resistor.setCircuit(circuitBuilder);
		wire.setCircuit(circuitBuilder);
		capacitor.setCircuit(circuitBuilder);
		inductor.setCircuit(circuitBuilder);
		switch1.setCircuit(circuitBuilder);
		bulb.setCircuit(circuitBuilder);
		transformercoil.setCircuit(circuitBuilder);
		diode.setCircuit(circuitBuilder);
		source.setCircuit(circuitBuilder);
		battery.setCircuit(circuitBuilder);
		currentsource.setCircuit(circuitBuilder);
		sinwave.setCircuit(circuitBuilder);
		squarewave.setCircuit(circuitBuilder);
		scope.setCircuit(circuitBuilder);
		vmeter.setCircuit(circuitBuilder);
		ameter.setCircuit(circuitBuilder);
	}

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == setGridButton) setGridButton_ActionPerformed(event);
			else if (object == listButton) listButton_ActionPerformed(event);
			else if (object == calculateButton)	calculateButton_ActionPerformed(event);
			else if (object == arrows) arrows_ActionPerformed(event);
			else if (object == loadButton) loadButton_ActionPerformed(event);
			else if (object == forwarding) forwarding_ActionPerformed(event);
			else if (object == resetting) resetting_ActionPerformed(event);
		}
	}

 	class SymMouse extends java.awt.event.MouseAdapter 
 	{
		public void mousePressed(java.awt.event.MouseEvent event) {
			Object object = event.getSource();
			try {
            if (object == resistor) resistorBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
            else if (object == capacitor) capacitorBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == inductor) inductorBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == wire) wireBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == switch1) switchBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == bulb) bulbBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == transformercoil) transformerBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == diode) diodeBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == source) sourceBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == battery) batteryBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == currentsource) currentsourceBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == sinwave) sinwaveBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == squarewave) squarewaveBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == scope) scopeBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == vmeter) vmeterBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			else if (object == ameter) ameterBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
    		} catch(java.beans.PropertyVetoException e) { }
		}
		
		public void mouseReleased(java.awt.event.MouseEvent event) {
			Object object = event.getSource();
			int stat=-1;
		    coordStr = circuitBuilder.coordString(absoluteP(event.getPoint(),(Component)object),true);
		    String name = ""+((CircuitElement)object).getMyName();
            if (name.equals("transformercoil"))
                stat=circuitBuilder.addObject("transformer",coordStr); 
    	    else stat=circuitBuilder.addObject(name,coordStr);
    	    if (stat != -1) {
    	        circuitBuilder.currentElement=circuitBuilder.getComponent(coordStr);
	            if (!name.equals("wire") && !name.equals("scope")
	                && !name.equals("ameter") && !name.equals("vmeter")) 
	                changeProperties();
    	        circuitBuilder.reset();
	            forwarding.setLabel(circuitBuilder.cirProp.getProperty("Start"));
                circuitBuilder.parse();
                circuitBuilder.repaintMeters();
	        }
			resetButtons();
		}
	}

	void setGridButton_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    int r = rowSpin.getCurrent();
	    int c = colSpin.getCurrent();
		circuitBuilder.setGrid(r,c);
	}

	void resetButtons()
	{
		try {
		    resistorBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    capacitorBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    inductorBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    wireBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    switchBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    bulbBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    transformerBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    diodeBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    sourceBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    batteryBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    currentsourceBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    sinwaveBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    squarewaveBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    scopeBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    vmeterBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    ameterBorder.setBevelStyle(BorderPanel.BEVEL_RAISED);
		    dtInput.setText(Double.toString(circuitBuilder.dt));
		} catch(java.beans.PropertyVetoException e) { }
	}

	void changeProperties()
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    String title = ""+circuitBuilder.cirProp.getProperty("changevalue_title");
	    ValueInput valueInput = new ValueInput(title,circuitBuilder,(Frame)anchorpoint);
	}
	
	void listButton_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    String s = circuitBuilder.cirgrid.getcomponentList();
	    String title = circuitBuilder.cirProp.getProperty("circuitlist");
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    ListOutput listOutput = new ListOutput(title,s,(Frame)anchorpoint,circuitBuilder);
    }

	void parseButton_ActionPerformed(java.awt.event.ActionEvent event)
	{
        circuitBuilder.parse();
        circuitBuilder.repaintMeters();
    }

	void calculateButton_ActionPerformed(java.awt.event.ActionEvent event)
	{
        circuitBuilder.repaintMeters();
        if ((circuitBuilder.debugLevel&circuitBuilder.DEBUG_IO)>0) 
            System.out.println("Next period calculated");
    }
	
	void arrows_ActionPerformed(java.awt.event.ActionEvent event)
	{
		if (circuitBuilder.showCurrent) circuitBuilder.setShowCurrent(0);
		else circuitBuilder.setShowCurrent(1);
		circuitBuilder.circanvas.redraw();
	}
    
    public Point absoluteP(Point relativeP, Component anchor)    
    {
        Point returnP = new Point(relativeP);
	    Point p1;
	    Object anchorpoint = anchor;
	    while (anchorpoint != this) {
            p1 = ((Component)anchorpoint).getLocation();
	        returnP.setLocation(returnP.x+p1.x, returnP.y+p1.y);
	        anchorpoint = ((Component)anchorpoint).getParent();
	    } 
	    returnP.setLocation(returnP.x+this.getLocation().x, returnP.y+this.getLocation().y);
        if ((circuitBuilder.debugLevel&circuitBuilder.DEBUG_IO)>0) 
            System.out.println("coords : "+returnP.toString());
	    return returnP;
    }

    public void updateTextFields() 
    {
	    dtInput.setText(Double.toString(circuitBuilder.dt));
	    numberInput.setText(Integer.toString(circuitBuilder.numberofdt));
    }

	class SymText implements java.awt.event.TextListener
	{
		public void textValueChanged(java.awt.event.TextEvent event)
		{
			Object object = event.getSource();
			if (object == numberInput)
				numberInput_TextValueChanged(event);
			else if (object == dtInput)
				dtInput_TextValueChanged(event);
		}
	}

	void numberInput_TextValueChanged(java.awt.event.TextEvent event)
	{
	    circuitBuilder.setNumberOfDT(Integer.parseInt(numberInput.getText()));
	    circuitBuilder.repaintMeters();
	}

	void dtInput_TextValueChanged(java.awt.event.TextEvent event)
	{
	    circuitBuilder.setDT((Double.valueOf(dtInput.getText())).doubleValue());
	    circuitBuilder.setFPS(1.0/(circuitBuilder.noc*circuitBuilder.dt));
	    circuitBuilder.repaintMeters();
	}

	void loadButton_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    circuitBuilder.reset();
	    forwarding.setLabel(circuitBuilder.cirProp.getProperty("Start"));
        circuitBuilder.loadList(inputfile.getText());
	    updateTextFields();
	    circuitBuilder.parse();
        circuitBuilder.calculateCircuit();
    }

	void forwarding_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    if (forwarding.getLabel().equals(circuitBuilder.cirProp.getProperty("Start"))) {
	        circuitBuilder.forward();
	        forwarding.setLabel(circuitBuilder.cirProp.getProperty("Pause"));
	    } else {
	        circuitBuilder.pause();
	        forwarding.setLabel(circuitBuilder.cirProp.getProperty("Start"));
	    }
	}

	void resetting_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    circuitBuilder.pause();
	    circuitBuilder.reset();
	    forwarding.setLabel(circuitBuilder.cirProp.getProperty("Start"));
	}
}