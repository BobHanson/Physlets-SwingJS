package circuitsimulator;

import java.applet.Applet;
//import a2s.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import symantec.itools.awt.BorderPanel;
import symantec.itools.awt.util.spinner.NumericSpinner;

/**
 * Used by circuitbuilder. Representation of the tools panel.
 * 
 * @author Toon Van Hoecke
 */
//public class BuilderPanel extends BorderPanel
public class BuilderPanel extends BorderPanel {
	CircuitBuilder circuitBuilder = null;
	String selectedComponent = "";

	circuitsimulator.Wire wire;
	circuitsimulator.Resistor resistor;
	circuitsimulator.Capacitor capacitor;
	circuitsimulator.Inductor inductor;
	circuitsimulator.Switch switch1;
	circuitsimulator.Bulb bulb;
	circuitsimulator.TransformerCoil transformercoil;
	circuitsimulator.Diode diode;
	circuitsimulator.Source source;
	circuitsimulator.Battery battery;
	circuitsimulator.CurrentSource currentsource;
	circuitsimulator.SinWave sinwave;
	circuitsimulator.SquareWave squarewave;
	circuitsimulator.Vmeter vmeter;
	circuitsimulator.Ameter ameter;
	circuitsimulator.Scope scope;

	public BuilderPanel(Circuit circuit) {
		set(this, false, 0, 0, 0, 0);

		
		setBackground(new Color(0, 143, 213));
		setSize(195, 401);
	
		circuitBuilder = (CircuitBuilder) circuit;

		
		
		
		try {
			rowSpin.setMin(2);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			rowSpin.setCurrent(2);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			rowSpin.setMax(8);
		} catch (java.beans.PropertyVetoException e) {
		}
		add(rowSpin);
		rowSpin.setBackground(Color.yellow);
		rowSpin.setForeground(Color.black);
		rowSpin.setBounds(64, 32, 28, 24);
		
		try {
			colSpin.setMin(2);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			colSpin.setCurrent(2);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			colSpin.setMax(5);
		} catch (java.beans.PropertyVetoException e) {
		}
		add(colSpin);
		colSpin.setBackground(Color.yellow);
		colSpin.setBounds(64, 56, 28, 24);
		
		label1.setText("rows");
		add(label1);
		label1.setFont(new Font("Dialog", Font.PLAIN, 10));
		label1.setBounds(93, 32, 44, 24);
		
		label3.setText("cols");
		add(label3);
		label3.setFont(new Font("Dialog", Font.PLAIN, 10));
		label3.setBounds(93, 56, 44, 22);
		
		numberInput.setText("1e3");
		add(numberInput);
		numberInput.setBackground(Color.white);
		numberInput.setFont(new Font("Dialog", Font.PLAIN, 10));
		numberInput.setBounds(141, 346, 47, 20);
		
		dtInput.setText("1e-6");
		add(dtInput);
		dtInput.setBackground(Color.white);
		dtInput.setFont(new Font("Dialog", Font.PLAIN, 10));
		dtInput.setBounds(40, 347, 72, 19);
		
		label9.setText("#:");
		add(label9);
		label9.setBounds(126, 343, 16, 23);

		label10.setText("dt (s):");
		add(label10);
		label10.setBounds(5, 344, 34, 24);
		
		inputfile.setText("lists/default.txt");
		add(inputfile);
		inputfile.setBackground(Color.white);
		inputfile.setBounds(59, 5, 129, 24);

		addBtn(setGridButton, "Set grid", 5, 32, 59, 48);
		addBtn(loadButton, "Load",      6, 5, 52, 25);
		addBtn(arrows, "Show ->",     139, 58, 50, 23);
		addBtn(listButton, "List",    138, 32, 51, 23);
		
		addBtn(calculateButton, "Calculate", 6, 371, 67, 25);
		addBtn(forwarding, "Start",         75, 371, 56, 25);
		addBtn(resetting,"Reset",          132, 371, 58, 25);


		// column 1 //
		
		set(resistorBorder,      true, 5, 99, 56, 28);
		set(capacitorBorder,     true, 5, 127, 56, 28);		
		set(inductorBorder,      true, 5, 155, 56, 28);

		set(diodeBorder,         true, 5, 191, 56, 28);

		set(sourceBorder,        true, 5, 226, 56, 28);
		set(batteryBorder,       true, 5, 254, 56, 28);
		set(currentsourceBorder, true, 5, 282, 56, 28);

		
		// column 2 //

		set(bulbBorder,          true, 69, 99, 56, 28);
		set(wireBorder,          true, 69, 127, 56, 28);
		set(switchBorder,        true, 69, 155, 56, 28);

		//
		
		set(sinwaveBorder,       true, 69, 226, 56, 28);		 
		set(squarewaveBorder,    true, 69, 254, 56, 28);
		//
		
		
		// column 3 //
		
		set(transformerBorder,   true, 133, 99, 56, 42);
		//
		//
		
		//

		set(scopeBorder,         true, 133, 226, 56, 28);
		set(vmeterBorder,        true, 133, 254, 56, 28);
		set(ameterBorder,        true, 133, 282, 56, 28);

		addComponents(circuit);
		addListeners();
		setText(circuitBuilder);
	}

	private void addBtn(Button b, String label, int x, int y, int w, int h) {
		b.setLabel(label);
		b.setBackground(new Color(0, 169, 251));
		b.setBounds(x, y, w, h);
		add(b);
	}

	private void set(BorderPanel bp, boolean andAdd, int x, int y, int w, int h) {
		bp.setBackground(new Color(0, 169, 251));
		try {
			bp.setBevelStyle(BorderPanel.BEVEL_RAISED);
			bp.setPadding(0, 0, 0, 0);
			bp.setIPadBottom(1);
			bp.setIPadSides(1);
			bp.setIPadTop(1);
		} catch (java.beans.PropertyVetoException e) {
		}
		bp.setLayout(null);
		if (bp != this) {
			add(bp);
			bp.setBounds(x,y, w, h);
		}
	}

	private void addListeners() {

		// {{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		SymMouse aSymMouse = new SymMouse();
		setGridButton.addActionListener(lSymAction);
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
		// }}
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

	private void addComponents(Circuit circuit) {
		wire = new circuitsimulator.Wire(circuit);
		resistor = new circuitsimulator.Resistor(circuit);
		capacitor = new circuitsimulator.Capacitor(circuit);
		inductor = new circuitsimulator.Inductor(circuit);
		switch1 = new circuitsimulator.Switch(circuit);
		bulb = new circuitsimulator.Bulb(circuit);
		transformercoil = new circuitsimulator.TransformerCoil(circuit);
		diode = new circuitsimulator.Diode(circuit);
		source = new circuitsimulator.Source(circuit);
		battery = new circuitsimulator.Battery(circuit);
		currentsource = new circuitsimulator.CurrentSource(circuit);
		sinwave = new circuitsimulator.SinWave(circuit);
		squarewave = new circuitsimulator.SquareWave(circuit);
		vmeter = new circuitsimulator.Vmeter(circuit);
		ameter = new circuitsimulator.Ameter(circuit);
		scope = new circuitsimulator.Scope(circuit);

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
	}

	// {{DECLARE_CONTROLS
	// Button setGridButton = new a2s.Button();
	Button setGridButton = new Button();
	NumericSpinner rowSpin = new NumericSpinner();
	NumericSpinner colSpin = new NumericSpinner();
	Label label1 = new Label();
	Label label3 = new Label();
	Button listButton = new Button();
	Button calculateButton = new Button();
	TextField numberInput = new TextField();
	TextField dtInput = new TextField();
	Label label9 = new Label();
	Label label10 = new Label();
	Button arrows = new Button();
	Button loadButton = new Button();
	TextField inputfile = new TextField();
	Button forwarding = new Button();
	Button resetting = new Button();
	
	BorderPanel resistorBorder = new BorderPanel();
	BorderPanel capacitorBorder = new BorderPanel();
	BorderPanel inductorBorder = new BorderPanel();
	BorderPanel wireBorder = new BorderPanel();
	BorderPanel sourceBorder = new BorderPanel();
	BorderPanel scopeBorder = new BorderPanel();
	BorderPanel vmeterBorder = new BorderPanel();
	BorderPanel ameterBorder = new BorderPanel();
	BorderPanel switchBorder = new BorderPanel();
	BorderPanel batteryBorder = new BorderPanel();
	BorderPanel bulbBorder = new BorderPanel();
	BorderPanel currentsourceBorder = new BorderPanel();
	BorderPanel transformerBorder = new BorderPanel();
	BorderPanel sinwaveBorder = new BorderPanel();
	BorderPanel squarewaveBorder = new BorderPanel();
	BorderPanel diodeBorder = new BorderPanel();
	// }}
	String coordStr;

	private void setText(CircuitBuilder cb) {
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

	class SymAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object object = event.getSource();
			if (object == setGridButton)
				setGridButton_ActionPerformed(event);
			else if (object == listButton)
				listButton_ActionPerformed(event);
			else if (object == calculateButton)
				calculateButton_ActionPerformed(event);
			else if (object == arrows)
				arrows_ActionPerformed(event);
			else if (object == loadButton)
				loadButton_ActionPerformed(event);
			else if (object == forwarding)
				forwarding_ActionPerformed(event);
			else if (object == resetting)
				resetting_ActionPerformed(event);
		}
	}

	class SymMouse extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			Object object = event.getSource();
			try {
				if (object == resistor)
					resistorBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == capacitor)
					capacitorBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == inductor)
					inductorBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == wire)
					wireBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == switch1)
					switchBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == bulb)
					bulbBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == transformercoil)
					transformerBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == diode)
					diodeBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == source)
					sourceBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == battery)
					batteryBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == currentsource)
					currentsourceBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == sinwave)
					sinwaveBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == squarewave)
					squarewaveBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == scope)
					scopeBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == vmeter)
					vmeterBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
				else if (object == ameter)
					ameterBorder.setBevelStyle(BorderPanel.BEVEL_LOWERED);
			} catch (java.beans.PropertyVetoException e) {
			}
		}

		public void mouseReleased(MouseEvent event) {
			Object object = event.getSource();
			int stat = -1;
			coordStr = circuitBuilder.coordString(absoluteP(event.getPoint(), (Component) object), true);
			String name = "" + ((CircuitElement) object).getMyName();
			if (name.equals("transformercoil"))
				stat = circuitBuilder.addObject("transformer", coordStr);
			else
				stat = circuitBuilder.addObject(name, coordStr);
			if (stat != -1) {
				circuitBuilder.currentElement = circuitBuilder.getComponent(coordStr);
				if (!name.equals("wire") && !name.equals("scope") && !name.equals("ameter") && !name.equals("vmeter"))
					changeProperties();
				circuitBuilder.reset();
				forwarding.setLabel(circuitBuilder.cirProp.getProperty("Start"));
				circuitBuilder.parse();
				circuitBuilder.repaintMeters();
			}
			resetButtons();
		}
	}

	void setGridButton_ActionPerformed(ActionEvent event) {
		int r = rowSpin.getCurrent();
		int c = colSpin.getCurrent();
		circuitBuilder.setGrid(r, c);
	}

	void resetButtons() {
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
		} catch (java.beans.PropertyVetoException e) {
		}
	}

	void changeProperties() {
		String title = "" + circuitBuilder.cirProp.getProperty("changevalue_title");
		new ValueInput(title, circuitBuilder, getAnchorPoint());
	}

	private Component getAnchorPoint() {
		Component anchorpoint = getParent();
		while (anchorpoint != null && !(anchorpoint instanceof Frame) && !(anchorpoint instanceof Applet))
			anchorpoint = ((Component) anchorpoint).getParent();
		return anchorpoint;
	}

	void listButton_ActionPerformed(ActionEvent event) {
		String s = circuitBuilder.cirgrid.getcomponentList();
		String title = circuitBuilder.cirProp.getProperty("circuitlist");
		Object anchorpoint = getParent();
		while (!(anchorpoint instanceof Frame))
			anchorpoint = ((Component) anchorpoint).getParent();
		ListOutput listOutput = new ListOutput(title, s, (Frame) anchorpoint, circuitBuilder);
	}

	void parseButton_ActionPerformed(ActionEvent event) {
		circuitBuilder.parse();
		circuitBuilder.repaintMeters();
	}

	void calculateButton_ActionPerformed(ActionEvent event) {
		circuitBuilder.repaintMeters();
		if ((circuitBuilder.debugLevel & CircuitBuilder.DEBUG_IO) > 0)
			System.out.println("Next period calculated");
	}

	void arrows_ActionPerformed(ActionEvent event) {
		if (circuitBuilder.showCurrent)
			circuitBuilder.setShowCurrent(0);
		else
			circuitBuilder.setShowCurrent(1);
		circuitBuilder.circanvas.redraw();
	}

	public Point absoluteP(Point relativeP, Component anchor) {
		Point returnP = new Point(relativeP);
		Point p1;
		Object anchorpoint = anchor;
		while (anchorpoint != this) {
			p1 = ((Component) anchorpoint).getLocation();
			returnP.setLocation(returnP.x + p1.x, returnP.y + p1.y);
			anchorpoint = ((Component) anchorpoint).getParent();
		}
		returnP.setLocation(returnP.x + this.getLocation().x, returnP.y + this.getLocation().y);
		if ((circuitBuilder.debugLevel & CircuitBuilder.DEBUG_IO) > 0)
			System.out.println("coords : " + returnP.toString());
		return returnP;
	}

	public void updateTextFields() {
		dtInput.setText(Double.toString(circuitBuilder.dt));
		numberInput.setText(Integer.toString(circuitBuilder.numberofdt));
	}

	class SymText implements TextListener {
		public void textValueChanged(TextEvent event) {
			Object object = event.getSource();
			if (object == numberInput)
				numberInput_TextValueChanged(event);
			else if (object == dtInput)
				dtInput_TextValueChanged(event);
		}
	}

	void numberInput_TextValueChanged(TextEvent event) {
		circuitBuilder.setNumberOfDT(Integer.parseInt(numberInput.getText()));
		circuitBuilder.repaintMeters();
	}

	void dtInput_TextValueChanged(TextEvent event) {
		circuitBuilder.setDT((Double.valueOf(dtInput.getText())).doubleValue());
		circuitBuilder.setFPS(1.0 / (circuitBuilder.noc * circuitBuilder.dt));
		circuitBuilder.repaintMeters();
	}

	void loadButton_ActionPerformed(ActionEvent event) {
		circuitBuilder.reset();
		forwarding.setLabel(circuitBuilder.cirProp.getProperty("Start"));
		circuitBuilder.loadList(inputfile.getText());
		updateTextFields();
		circuitBuilder.parse();
		circuitBuilder.calculateCircuit();
	}

	void forwarding_ActionPerformed(ActionEvent event) {
		if (forwarding.getLabel().equals(circuitBuilder.cirProp.getProperty("Start"))) {
			circuitBuilder.forward();
			forwarding.setLabel(circuitBuilder.cirProp.getProperty("Pause"));
		} else {
			circuitBuilder.pause();
			forwarding.setLabel(circuitBuilder.cirProp.getProperty("Start"));
		}
	}

	void resetting_ActionPerformed(ActionEvent event) {
		circuitBuilder.pause();
		circuitBuilder.reset();
		forwarding.setLabel(circuitBuilder.cirProp.getProperty("Start"));
	}
}