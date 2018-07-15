package circuitsimulator;

import java.awt.*;

/**
 * Used by circuitbuilder. Popupmenu with the options for the pointed element.
 *
 * @author Toon Van Hoecke
 */
public class PopupOnElement extends java.awt.PopupMenu
{
	CircuitBuilder cb;

	PopupOnElement(CircuitBuilder circuitBuilder){
	    cb = circuitBuilder;
	    vcInit();
        delete.setLabel(cb.cirProp.getProperty("menu_delete"));
        changevalue.setLabel(cb.cirProp.getProperty("menu_changevalue"));
        knobvalue.setLabel(cb.cirProp.getProperty("menu_knobvalue"));
        knobfrequency.setLabel(cb.cirProp.getProperty("menu_knobfrequency"));
        showvalue.setLabel(cb.cirProp.getProperty("menu_showvalue"));
        changeswitch.setLabel(cb.cirProp.getProperty("menu_changeswitch"));
        changepolarity.setLabel(cb.cirProp.getProperty("menu_changepolarity"));
        elemlabel.setLabel(cb.cirProp.getProperty("menu_elemlabel"));
        getvoltage.setLabel(cb.cirProp.getProperty("menu_getvoltage"));
        voltmeter.setLabel(cb.cirProp.getProperty("menu_voltmeter"));
        ammeter.setLabel(cb.cirProp.getProperty("menu_ammeter"));
        vgraph.setLabel(cb.cirProp.getProperty("menu_vgraph"));
        igraph.setLabel(cb.cirProp.getProperty("menu_igraph"));
	}

	// The following function is a placeholder for control initialization.
	// You should call this function from a constructor or initialization function.
	public void vcInit() {
		//{{INIT_CONTROLS
		setLabel("Select Action");
	    add(delete);
	    add(changevalue);
	    add(knobvalue);
	    add(knobfrequency);
	    add(showvalue);
	    add(elemlabel);
	    add(msep);
	    add(getvoltage);
	    add(voltmeter);
	    add(ammeter);
	    add(vgraph);
	    add(igraph);
	    add(changeswitch);
	    add(changepolarity);
		//}}

		//{{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		delete.addActionListener(lSymAction);
		changevalue.addActionListener(lSymAction);
		knobvalue.addActionListener(lSymAction);
		knobfrequency.addActionListener(lSymAction);
		showvalue.addActionListener(lSymAction);
		elemlabel.addActionListener(lSymAction);
		getvoltage.addActionListener(lSymAction);
		voltmeter.addActionListener(lSymAction);
		ammeter.addActionListener(lSymAction);
		vgraph.addActionListener(lSymAction);
		igraph.addActionListener(lSymAction);
		changeswitch.addActionListener(lSymAction);
		changepolarity.addActionListener(lSymAction);
		//}}

		enableItems();
	}

	public void enableItems() {
		changevalue.setEnabled(true);
		knobvalue.setEnabled(false);
		showvalue.setEnabled(true);
		knobfrequency.setEnabled(false);
		getvoltage.setEnabled(false);
		voltmeter.setEnabled(false);
		ammeter.setEnabled(false);
		changeswitch.setEnabled(false);
		changepolarity.setEnabled(false);
	}

	public void selectItems() {
            if(cb==null) return; // added by W. Christian
	    String cename = ""+cb.currentElement.name();
            enableItems();
   	    if (cename.equals("switch")) {
   	        changeswitch.setEnabled(true);
	        changevalue.setEnabled(false);
   	    } else if (cename.equals("scope")) {
	        if (cb.parsed) getvoltage.setEnabled(true);
   	    } else if (cename.equals("vmeter")) {
	        if (cb.parsed) voltmeter.setEnabled(true);
   	    } else if (cename.equals("ameter")) {
	        if (cb.parsed) ammeter.setEnabled(true);
   	    } else if (cename.equals("resistor") || cename.equals("capacitor") || cename.equals("inductor")
   	               || cename.equals("currentsource") || cename.equals("battery")) {
	        knobvalue.setEnabled(true);
   	    } else if (cename.equals("source")|| cename.equals("sinwave") || cename.equals("squarewave")) {
	        if (cb.parsed) knobfrequency.setEnabled(true);
   	    }
   	    if (cb.currentElement.polarized) changepolarity.setEnabled(true);
	}

	//{{DECLARE_CONTROLS
    MenuItem delete = new MenuItem();
    MenuItem changevalue = new MenuItem();
    MenuItem knobvalue = new MenuItem();
    MenuItem knobfrequency = new MenuItem();
    MenuItem showvalue = new MenuItem();
    MenuItem changeswitch = new MenuItem();
    MenuItem changepolarity = new MenuItem();
    MenuItem elemlabel = new MenuItem();
    MenuItem msep = new MenuItem("-");
    MenuItem getvoltage = new MenuItem();
    MenuItem voltmeter = new MenuItem();
    MenuItem ammeter = new MenuItem();
    MenuItem vgraph = new MenuItem();
    MenuItem igraph = new MenuItem();
	//}}


	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == delete)
				delete_ActionPerformed(event);
			else if (object == changevalue)
				changevalue_ActionPerformed(event);
			else if (object == knobvalue)
				knobvalue_ActionPerformed(event);
			else if (object == knobfrequency)
				knobfrequency_ActionPerformed(event);
			else if (object == showvalue)
				showvalue_ActionPerformed(event);
			else if (object == elemlabel)
				elemlabel_ActionPerformed(event);
			else if (object == getvoltage)
				getvoltage_ActionPerformed(event);
			else if (object == voltmeter)
				voltmeter_ActionPerformed(event);
			else if (object == ammeter)
				ammeter_ActionPerformed(event);
			else if (object == vgraph)
				vgraph_ActionPerformed(event);
			else if (object == igraph)
				igraph_ActionPerformed(event);
			else if (object == changeswitch)
				changeswitch_ActionPerformed(event);
			else if (object == changepolarity)
				changepolarity_ActionPerformed(event);
		}
	}

	void delete_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    cb.removeObject(cb.currentElement.hashCode());
	    cb.parse();
	    cb.repaintMeters();
	}

	void changevalue_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    ValueInput valueInput = new ValueInput(cb.cirProp.getProperty("changevalue_title"),cb,(Frame)anchorpoint);
	}

	void knobvalue_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    String s = ""+cb.currentElement.getvalue();
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    ValueKnob valueKnob = new ValueKnob("",cb,(Frame)anchorpoint);
	}

	void knobfrequency_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    String s = ""+cb.currentElement.getvalue();
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    ValueKnob valueKnob = new ValueKnob("Hz",cb,(Frame)anchorpoint);
	}

	void showvalue_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    cb.currentElement.setValueVisible(!cb.currentElement.valueVisible);
	    cb.circanvas.redraw();
	}

	void elemlabel_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    ValueInput valueInput = new ValueInput(cb.cirProp.getProperty("elemlabel_title"),cb,(Frame)anchorpoint);
	}

	void getvoltage_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    OscilloDialog oscdiag = new OscilloDialog((Frame)anchorpoint,cb);
        cb.scopeList.addElement(oscdiag);
	    oscdiag.setVisible(true);
	}

	void voltmeter_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    Meter voltMeter = new Meter("V",cb,(Frame)anchorpoint);
        cb.meterList.addElement(voltMeter);
	    voltMeter.setVisible(true);
	}

	void ammeter_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    Meter ampMeter = new Meter("A",cb,(Frame)anchorpoint);
        cb.meterList.addElement(ampMeter);
	    ampMeter.setVisible(true);
	}

	void vgraph_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    DataGraphDialog graphdialog = new DataGraphDialog((Frame)anchorpoint,cb,"v");
        cb.graphList.addElement(graphdialog);
	    graphdialog.setVisible(true);
	}

	void igraph_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
	    DataGraphDialog graphdialog = new DataGraphDialog((Frame)anchorpoint,cb,"i");
        cb.graphList.addElement(graphdialog);
	    graphdialog.setVisible(true);
	}

	void changeswitch_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
        ((Switch)cb.currentElement).change();
        cb.cirgrid.buildEquations();
        cb.calculateCircuit();
	    cb.circanvas.redraw();
	    cb.repaintMeters();
    }

	void changepolarity_ActionPerformed(java.awt.event.ActionEvent event)
	{
	    Object anchorpoint = getParent();
	    while (!(anchorpoint instanceof Frame))
	        anchorpoint = ((Component)anchorpoint).getParent();
        ((CircuitElement)cb.currentElement).changePolarity();
	    cb.parse();
	    cb.circanvas.redraw();
	    cb.repaintMeters();
    }
}