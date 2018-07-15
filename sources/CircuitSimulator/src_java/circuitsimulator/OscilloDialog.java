package circuitsimulator;

import java.awt.*;

/**
 * Used by circuitbuilder. Dialog window simulating an oscilloscope for the pointed element.
 * 
 * @author Toon Van Hoecke
 */
public class OscilloDialog extends Dialog
{
    public double voltageScaling, timeScaling;
    public double verOffset, horOffset;
    public String mode;
    CircuitBuilder cb;
    CircuitElement ce;
    
	public OscilloDialog(Frame parent, CircuitBuilder cirbuilder)
	{
		super(parent);
		cb = cirbuilder;
		ce = cb.currentElement;
		scopeCanvas.setCircuit(cb,this);
		setTitle(cb.cirProp.getProperty("scope_title")+" "
		         +cb.cirProp.getProperty(ce.name())+" "+ce.getlabel());

		//{{INIT_CONTROLS
		setLayout(null);
		setBackground(new java.awt.Color(220,200,160));
		setSize(549,312);
		setVisible(false);
		try {
			timeScale.setStyle(symantec.itools.awt.ImagePanel.IMAGE_CENTERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		timeScale.setLayout(null);
		add(timeScale);
		timeScale.setBounds(432,180,108,116);
		try {
			timeIndicator.setBevelStyle(symantec.itools.awt.shape.Circle.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			timeIndicator.setFillColor(java.awt.Color.red);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			timeIndicator.setFillMode(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		timeScale.add(timeIndicator);
		timeIndicator.setForeground(java.awt.Color.blue);
		timeIndicator.setBounds(39,33,12,12);
		invisible10us.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible10us);
		invisible10us.setBounds(69,84,20,15);
		invisible20us.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible20us);
		invisible20us.setBounds(81,71,21,14);
		invisible50us.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible50us);
		invisible50us.setBounds(85,54,20,15);
		invisible01ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible01ms);
		invisible01ms.setBounds(83,34,20,14);
		invisible02ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible02ms);
		invisible02ms.setBounds(69,15,21,15);
		invisible05ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible05ms);
		invisible05ms.setBounds(45,9,19,19);
		invisible1ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible1ms);
		invisible1ms.setBounds(23,16,18,17);
		invisible2ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible2ms);
		invisible2ms.setBounds(10,32,15,17);
		invisible5ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible5ms);
		invisible5ms.setBounds(5,50,17,15);
		invisible10ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible10ms);
		invisible10ms.setBounds(9,69,18,17);
		invisible20ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible20ms);
		invisible20ms.setBounds(24,84,17,17);
		invisible50ms.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		timeScale.add(invisible50ms);
		invisible50ms.setBounds(45,91,17,15);
		try {
			voltageScale.setStyle(symantec.itools.awt.ImagePanel.IMAGE_CENTERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		voltageScale.setLayout(null);
		add(voltageScale);
		voltageScale.setBounds(432,36,109,116);
		try {
			voltageIndicator.setBevelStyle(symantec.itools.awt.shape.Circle.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			voltageIndicator.setFillColor(java.awt.Color.red);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			voltageIndicator.setFillMode(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		voltageScale.add(voltageIndicator);
		voltageIndicator.setForeground(java.awt.Color.blue);
		voltageIndicator.setBounds(30,41,12,12);
		invisible01V.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible01V);
		invisible01V.setBounds(65,14,19,14);
		invisible02V.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible02V);
		invisible02V.setBounds(42,7,21,16);
		invisible05V.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible05V);
		invisible05V.setBounds(21,14,20,17);
		invisible1V.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible1V);
		invisible1V.setBounds(10,30,16,14);
		invisible2V.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible2V);
		invisible2V.setBounds(5,51,17,16);
		invisible5V.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible5V);
		invisible5V.setBounds(10,71,16,13);
		invisible10V.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible10V);
		invisible10V.setBounds(26,85,16,16);
		invisible5mV.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible5mV);
		invisible5mV.setBounds(68,85,18,17);
		invisible10mV.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible10mV);
		invisible10mV.setBounds(84,68,17,15);
		invisible20mV.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible20mV);
		invisible20mV.setBounds(86,49,18,17);
		invisible20V.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible20V);
		invisible20V.setBounds(46,89,16,19);
		invisible50mV.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageScale.add(invisible50mV);
		invisible50mV.setBounds(82,29,17,17);
		try {
			horScrol.setStyle(symantec.itools.awt.ImagePanel.IMAGE_CENTERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		horScrol.setLayout(null);
		add(horScrol);
		horScrol.setBounds(396,240,26,48);
		invisibleLeft.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		horScrol.add(invisibleLeft);
		invisibleLeft.setBounds(-3,3,16,17);
		invisibleRight.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		horScrol.add(invisibleRight);
		invisibleRight.setBounds(14,4,14,14);
		try {
			horIndicator.setFillColor(java.awt.Color.red);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			horIndicator.setFillMode(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		horScrol.add(horIndicator);
		horIndicator.setBackground(java.awt.Color.red);
		horIndicator.setForeground(java.awt.Color.lightGray);
		horIndicator.setBounds(10,16,5,5);
		try {
			vertScrol.setStyle(symantec.itools.awt.ImagePanel.IMAGE_CENTERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		vertScrol.setLayout(null);
		add(vertScrol);
		vertScrol.setBounds(384,108,36,36);
		invisibleUp.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		vertScrol.add(invisibleUp);
		invisibleUp.setBounds(-3,0,15,17);
		invisibleDown.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		vertScrol.add(invisibleDown);
		invisibleDown.setBounds(-3,18,18,13);
		try {
			verIndicator.setFillColor(java.awt.Color.red);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			verIndicator.setFillMode(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		vertScrol.add(verIndicator);
		verIndicator.setBackground(java.awt.Color.red);
		verIndicator.setForeground(java.awt.Color.lightGray);
		verIndicator.setBounds(10,15,5,5);
		try {
			voltageMode.setStyle(symantec.itools.awt.ImagePanel.IMAGE_CENTERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		voltageMode.setLayout(null);
		add(voltageMode);
		voltageMode.setBounds(384,24,24,36);
		try {
			modeIndicator.setBevelStyle(symantec.itools.awt.shape.Rect.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			modeIndicator.setFillColor(java.awt.Color.red);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			modeIndicator.setFillMode(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		voltageMode.add(modeIndicator);
		modeIndicator.setBounds(12,14,9,7);
		invisibleAC.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageMode.add(invisibleAC);
		invisibleAC.setBounds(1,2,21,12);
		invisibleDC.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageMode.add(invisibleDC);
		invisibleDC.setBounds(0,12,22,12);
		invisibleGround.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		voltageMode.add(invisibleGround);
		invisibleGround.setBounds(-1,21,24,12);
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
		borderCanvas.setBounds(20,9,355,285);
		scopeCanvas.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.CROSSHAIR_CURSOR));
		borderCanvas.add(scopeCanvas);
		scopeCanvas.setBackground(new java.awt.Color(77,57,7));
		scopeCanvas.setBounds(1,1,351,281);
		try {
			borderVoltageScale.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderVoltageScale.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderVoltageScale.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderVoltageScale.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderVoltageScale.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderVoltageScale.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderVoltageScale.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderVoltageScale.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		borderVoltageScale.setLayout(null);
		add(borderVoltageScale);
		borderVoltageScale.setBounds(430,12,113,144);
		labelVoltageScale.setText("Voltage Scale");
		borderVoltageScale.add(labelVoltageScale);
		labelVoltageScale.setBounds(3,2,105,25);
		try {
			borderTimeBase.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderTimeBase.setIPadBottom(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderTimeBase.setIPadSides(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderTimeBase.setPaddingRight(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderTimeBase.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderTimeBase.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderTimeBase.setIPadTop(1);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderTimeBase.setPaddingLeft(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		borderTimeBase.setLayout(null);
		add(borderTimeBase);
		borderTimeBase.setBounds(430,156,113,144);
		labelTimeBase.setText("Time Base");
		borderTimeBase.add(labelTimeBase);
		labelTimeBase.setBounds(5,7,101,21);
		add(coords);
		coords.setFont(new Font("Dialog", Font.PLAIN, 10));
		coords.setBounds(21,294,217,16);
		//}}
		labelVoltageScale.setText(cb.cirProp.getProperty("scope_vscale"));
		labelTimeBase.setText(cb.cirProp.getProperty("scope_tbase"));
		String cbString = cb.getCodeBase().toString()+cb.imagedir+"/";
		try {
		try { timeScale.setImageURL(new java.net.URL(cbString+"btbt.gif"));
		} catch (java.net.MalformedURLException error) { }
		try { voltageScale.setImageURL(new java.net.URL(cbString+"btsv.gif"));
		} catch (java.net.MalformedURLException error) { }
		try { horScrol.setImageURL(new java.net.URL(cbString+"poth.gif"));
		} catch (java.net.MalformedURLException error) { }
		try { vertScrol.setImageURL(new java.net.URL(cbString+"potv.gif"));
		} catch (java.net.MalformedURLException error) { }
		try { voltageMode.setImageURL(new java.net.URL(cbString+"btmode.gif"));
		} catch (java.net.MalformedURLException error) { }
		} catch(java.beans.PropertyVetoException e) { }
		
		
		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymMouse aSymMouse = new SymMouse();
		invisible5mV.addMouseListener(aSymMouse);
		invisible10mV.addMouseListener(aSymMouse);
		invisible20mV.addMouseListener(aSymMouse);
		invisible50mV.addMouseListener(aSymMouse);
		invisible01V.addMouseListener(aSymMouse);
		invisible02V.addMouseListener(aSymMouse);
		invisible05V.addMouseListener(aSymMouse);
		invisible1V.addMouseListener(aSymMouse);
		invisible2V.addMouseListener(aSymMouse);
		invisible5V.addMouseListener(aSymMouse);
		invisible10V.addMouseListener(aSymMouse);
		invisible20V.addMouseListener(aSymMouse);
		invisible10us.addMouseListener(aSymMouse);
		invisible20us.addMouseListener(aSymMouse);
		invisible50us.addMouseListener(aSymMouse);
		invisible01ms.addMouseListener(aSymMouse);
		invisible02ms.addMouseListener(aSymMouse);
		invisible05ms.addMouseListener(aSymMouse);
		invisible1ms.addMouseListener(aSymMouse);
		invisible2ms.addMouseListener(aSymMouse);
		invisible5ms.addMouseListener(aSymMouse);
		invisible10ms.addMouseListener(aSymMouse);
		invisible20ms.addMouseListener(aSymMouse);
		invisible50ms.addMouseListener(aSymMouse);
		invisibleLeft.addMouseListener(aSymMouse);
		invisibleRight.addMouseListener(aSymMouse);
		invisibleUp.addMouseListener(aSymMouse);
		invisibleDown.addMouseListener(aSymMouse);
		invisibleAC.addMouseListener(aSymMouse);
		invisibleDC.addMouseListener(aSymMouse);
		invisibleGround.addMouseListener(aSymMouse);
		scopeCanvas.addMouseListener(aSymMouse);
		SymMouseMotion aSymMouseMotion = new SymMouseMotion();
		scopeCanvas.addMouseMotionListener(aSymMouseMotion);
		//}}
		
		voltageScaling = 1.0;
		timeScaling = 1e-3;
		verOffset = horOffset = 0;
		scopeCanvas.repaint();
		mode = "DC";
	}
	
	public OscilloDialog(CircuitBuilder cirbuilder)
	{
		this((Frame)null, cirbuilder);
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
	symantec.itools.awt.ImagePanel timeScale = new symantec.itools.awt.ImagePanel();
	symantec.itools.awt.shape.Circle timeIndicator = new symantec.itools.awt.shape.Circle();
	symantec.itools.awt.InvisibleButton invisible10us = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible20us = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible50us = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible01ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible02ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible05ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible1ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible2ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible5ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible10ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible20ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible50ms = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.ImagePanel voltageScale = new symantec.itools.awt.ImagePanel();
	symantec.itools.awt.shape.Circle voltageIndicator = new symantec.itools.awt.shape.Circle();
	symantec.itools.awt.InvisibleButton invisible01V = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible02V = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible05V = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible1V = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible2V = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible5V = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible10V = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible5mV = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible10mV = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible20mV = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible20V = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisible50mV = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.ImagePanel horScrol = new symantec.itools.awt.ImagePanel();
	symantec.itools.awt.InvisibleButton invisibleLeft = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisibleRight = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.shape.Square horIndicator = new symantec.itools.awt.shape.Square();
	symantec.itools.awt.ImagePanel vertScrol = new symantec.itools.awt.ImagePanel();
	symantec.itools.awt.InvisibleButton invisibleUp = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisibleDown = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.shape.Square verIndicator = new symantec.itools.awt.shape.Square();
	symantec.itools.awt.ImagePanel voltageMode = new symantec.itools.awt.ImagePanel();
	symantec.itools.awt.shape.Rect modeIndicator = new symantec.itools.awt.shape.Rect();
	symantec.itools.awt.InvisibleButton invisibleAC = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisibleDC = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.InvisibleButton invisibleGround = new symantec.itools.awt.InvisibleButton();
	symantec.itools.awt.BorderPanel borderCanvas = new symantec.itools.awt.BorderPanel();
	circuitsimulator.ScopeCanvas scopeCanvas = new circuitsimulator.ScopeCanvas();
	symantec.itools.awt.BorderPanel borderVoltageScale = new symantec.itools.awt.BorderPanel();
	java.awt.Label labelVoltageScale = new java.awt.Label();
	symantec.itools.awt.BorderPanel borderTimeBase = new symantec.itools.awt.BorderPanel();
	java.awt.Label labelTimeBase = new java.awt.Label();
	java.awt.Label coords = new java.awt.Label();
	//}}

	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == OscilloDialog.this)
				OscilloDialog_WindowClosing(event);
		}
	}
	
	void OscilloDialog_WindowClosing(java.awt.event.WindowEvent event)
	{
		setVisible(false);
		cb.scopeList.removeElement(this);
	}

	class SymMouse extends java.awt.event.MouseAdapter
	{
		public void mouseClicked(java.awt.event.MouseEvent event)
		{
			Object object = event.getSource();
			if (object == invisible5mV)
				invisible5mV_mouseClicked(event);
			if (object == invisible10mV)
				invisible10mV_mouseClicked(event);
			if (object == invisible20mV)
				invisible20mV_mouseClicked(event);
			if (object == invisible50mV)
				invisible50mV_mouseClicked(event);
			if (object == invisible01V)
				invisible01V_mouseClicked(event);
			if (object == invisible02V)
				invisible02V_mouseClicked(event);
			if (object == invisible05V)
				invisible05V_mouseClicked(event);
			if (object == invisible1V)
				invisible1V_mouseClicked(event);
			if (object == invisible2V)
				invisible2V_mouseClicked(event);
			if (object == invisible5V)
				invisible5V_mouseClicked(event);
			if (object == invisible10V)
				invisible10V_mouseClicked(event);
			if (object == invisible20V)
				invisible20V_mouseClicked(event);
			if (object == invisible10us)
				invisible10us_mouseClicked(event);
			if (object == invisible20us)
				invisible20us_mouseClicked(event);
			if (object == invisible50us)
				invisible50us_mouseClicked(event);
			if (object == invisible01ms)
				invisible01ms_mouseClicked(event);
			if (object == invisible02ms)
				invisible02ms_mouseClicked(event);
			if (object == invisible05ms)
				invisible05ms_mouseClicked(event);
			if (object == invisible1ms)
				invisible1ms_mouseClicked(event);
			if (object == invisible2ms)
				invisible2ms_mouseClicked(event);
			if (object == invisible5ms)
				invisible5ms_mouseClicked(event);
			if (object == invisible10ms)
				invisible10ms_mouseClicked(event);
			if (object == invisible20ms)
				invisible20ms_mouseClicked(event);
			if (object == invisible50ms)
				invisible50ms_mouseClicked(event);
			if (object == invisibleLeft)
				invisibleLeft_mouseClicked(event);
			if (object == invisibleRight)
				invisibleRight_mouseClicked(event);
			if (object == invisibleUp)
				invisibleUp_mouseClicked(event);
			if (object == invisibleDown)
				invisibleDown_mouseClicked(event);
			if (object == invisibleAC)
				invisibleAC_mouseClicked(event);
			if (object == invisibleDC)
				invisibleDC_mouseClicked(event);
			if (object == invisibleGround)
				invisibleGround_mouseClicked(event);
			if (object == scopeCanvas)
				scopeCanvas_MouseClicked(event);
			scopeCanvas.repaint();
            timeScale.repaint();
            voltageScale.repaint();
            horScrol.repaint();
            vertScrol.repaint();
            voltageMode.repaint();
		}
	}

	void invisible5mV_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(59, 70, 12, 12);
		    voltageScaling = 0.005;
		} catch (java.lang.Exception e) {}
	}

	void invisible10mV_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(66, 61, 12, 12);
		    voltageScaling = 0.01;
		} catch (java.lang.Exception e) {}
	}

	void invisible20mV_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(68, 51, 12, 12);
		    voltageScaling = 0.02;
		} catch (java.lang.Exception e) {}
	}

	void invisible50mV_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(66, 41, 12, 12);
		    voltageScaling = 0.05;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible01V_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(59, 33, 12, 12);
		    voltageScaling = 0.1;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible02V_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(48, 32, 12, 12);
		    voltageScaling = 0.2;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible05V_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(37, 33, 12, 12);
		    voltageScaling = 0.5;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible1V_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(30, 41, 12, 12);
		    voltageScaling = 1.0;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible2V_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(27, 51, 12, 12);
		    voltageScaling = 2.0;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible5V_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(30, 61, 12, 12);
		    voltageScaling = 5.0;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible10V_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(37, 70, 12, 12);
		    voltageScaling = 10.0;
		} catch (java.lang.Exception e) {}
	}

	void invisible20V_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    voltageIndicator.setBounds(48, 73, 12, 12);
		    voltageScaling = 20.0;
		} catch (java.lang.Exception e) {}
	}


	void invisible10us_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(59, 70, 12, 12);
		    timeScaling = 10e-6;
		} catch (java.lang.Exception e) {}
	}

	void invisible20us_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(66, 61, 12, 12);
		    timeScaling = 20e-6;
		} catch (java.lang.Exception e) {}
	}

	void invisible50us_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(68, 51, 12, 12);
		    timeScaling = 50e-6;
		} catch (java.lang.Exception e) {}
	}

	void invisible01ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(66, 41, 12, 12);
		    timeScaling = 0.1e-3;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible02ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(59, 33, 12, 12);
		    timeScaling = 0.2e-3;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible05ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(48, 32, 12, 12);
		    timeScaling = 0.5e-3;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible1ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(37, 33, 12, 12);
		    timeScaling = 1e-3;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible2ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(30, 41, 12, 12);
		    timeScaling = 2e-3;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible5ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(27, 51, 12, 12);
		    timeScaling = 5e-3;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible10ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(30, 61, 12, 12);
		    timeScaling = 10e-3;
		} catch (java.lang.Exception e) {}
	}
	
	void invisible20ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(37, 70, 12, 12);
		    timeScaling = 20e-3;
		} catch (java.lang.Exception e) {}
	}

	void invisible50ms_mouseClicked(java.awt.event.MouseEvent event) {
		try {
		    timeIndicator.setBounds(48, 73, 12, 12);
		    timeScaling = 50e-3;
		} catch (java.lang.Exception e) {}
	}


	void invisibleLeft_mouseClicked(java.awt.event.MouseEvent event)
	{
		horOffset-=7;
        horIndicator.setBounds(10+(int)Math.round(10*Math.sin(horOffset*Math.PI/180)), 
                               26-(int)Math.round(10*Math.cos(horOffset*Math.PI/180)), 5, 5);
	}

	
	void invisibleRight_mouseClicked(java.awt.event.MouseEvent event)
	{
		horOffset+=7;
        horIndicator.setBounds(10+(int)Math.round(10*Math.sin(horOffset*Math.PI/180)), 
                               26-(int)Math.round(10*Math.cos(horOffset*Math.PI/180)), 5, 5);
	}
	
	void invisibleUp_mouseClicked(java.awt.event.MouseEvent event)
	{
		verOffset+=7;
        verIndicator.setBounds(20-(int)Math.round(10*Math.cos(verOffset*Math.PI/180)), 
                               15-(int)Math.round(10*Math.sin(verOffset*Math.PI/180)), 5, 5);
	}

	void invisibleDown_mouseClicked(java.awt.event.MouseEvent event)
	{
		verOffset-=7;
        verIndicator.setBounds(20-(int)Math.round(10*Math.cos(verOffset*Math.PI/180)), 
                               15-(int)Math.round(10*Math.sin(verOffset*Math.PI/180)), 5, 5);
	}

	void invisibleAC_mouseClicked(java.awt.event.MouseEvent event)
	{
		modeIndicator.setBounds(12, 4, 9, 7);
		mode = "AC";
	}
	
	void invisibleDC_mouseClicked(java.awt.event.MouseEvent event)
	{
		modeIndicator.setBounds(12, 14, 9, 7);
		mode = "DC";
	}
	
	void invisibleGround_mouseClicked(java.awt.event.MouseEvent event)
	{
		modeIndicator.setBounds(12, 24, 9, 7);
		mode = "Ground";
	}

	void scopeCanvas_MouseDragged(java.awt.event.MouseEvent event)
	{
		coords.setText(scopeCanvas.getCoords(event.getPoint()));
	}

	class SymMouseMotion extends java.awt.event.MouseMotionAdapter
	{
		public void mouseDragged(java.awt.event.MouseEvent event)
		{
			Object object = event.getSource();
			if (object == scopeCanvas)
				scopeCanvas_MouseDragged(event);
		}
	}

	void scopeCanvas_MouseClicked(java.awt.event.MouseEvent event)
	{
		coords.setText(scopeCanvas.getCoords(event.getPoint()));
	}
}