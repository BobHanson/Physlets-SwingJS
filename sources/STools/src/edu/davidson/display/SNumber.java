//Title: SNumber
//Version: 0.2
//Copyright: April 20, 1998
//Author:Wolfgang Christian
//Company:Davidson College
//Description:Number field for floating point numbers

package edu.davidson.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import a2s.*;
import java.awt.event.TextEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SNumber extends TextField implements PropertyChangeListener {
	protected PropertyChangeSupport boundSupport;
	private double value = 0.0; // the value of the double
	private String formStr = "%-+6.3g";
	private Format valFormat = new Format("%-+6.3g");
	private boolean validData = true;
	private boolean noColor = false;

	public SNumber(double d) {
		this(d, null);
	}
	
	public SNumber(double d, String strFormat) {
		if (strFormat != null)
			setFormat(strFormat);
		boundSupport = new PropertyChangeSupport(this);
		value = 0;
		setText(valFormat.form(value));
		setPreferredSize(new Dimension(50, 25));
		this.addTextListener(new SNumber_this_textAdapter(this));
	}
	/*
  protected void processKeyEvent(KeyEvent evt){
        double v=0;
        if(evt.getID()==KeyEvent.KEY_TYPED){
            //System.out.println("processKeyEvent this.getText:"+this.getText()+ " evt.getKeyChar:" + evt.getKeyChar() );
            if(!isEditable()) setBackground(SystemColor.control);
            if(isEditable()) this.setBackground(Color.yellow);
            if(this.getText().equals("")&& !java.lang.Character.isDigit(evt.getKeyChar()) ) this.setBackground(Color.red);
            try{  // TextValueChanged my get called before the process key event in MS IE.
               v=Double.valueOf(this.getText()).doubleValue();
            }catch(NumberFormatException e){this.setBackground(Color.red);}
            //System.out.println("v:"+v);
        }
        super.processKeyEvent(evt);
    }

	 */

	public SNumber() {
		this(0.0, null);
	}

	public void setFormat(String f) {
      if(formStr.equals(f))return;
		formStr = f;
		valFormat = new Format(f);
		setText(valFormat.form(value));
	}
  public String getFormat(){return formStr;}

	public void setValue(double d) {
		validData = true;
      if (d==value){return;}
		double oldVal = value;
		value = d;
		this.setText(valFormat.form(value));
		boundSupport.firePropertyChange("DValue", new Double(oldVal), new Double(value));
      if(isEditable())setBackground(Color.white);
          else setBackground(SystemColor.control);
	}

	public double getValue() {
      if(noColor) return value;
      if(!isEditable())setBackground(SystemColor.control);
          else if(!validData) setBackground(Color.red);
              else setBackground(Color.white);
		return value;
	}

  public boolean isValid(){return validData;}
  public boolean isNoColor(){return noColor;}
  public void setNoColor(boolean nc){noColor=nc;}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		// use the support class
		if (boundSupport == null)
			super.addPropertyChangeListener(l);
		else
			boundSupport.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		boundSupport.removePropertyChangeListener(l);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// make changes to this object if it is bound to another object
		// System.out.println("PropertyValueChanged DValue:"+evt.getNewValue());
      if(!isEditable())setBackground(SystemColor.control);
        else setBackground(Color.white);
		if (evt.getPropertyName().equals("DValue")) {
			Double val = (Double) evt.getNewValue();
			try {
				if (val.doubleValue() == value) {
					return;
				} else {
					String str = valFormat.form(val.doubleValue());
					value = Double.valueOf(str).doubleValue();
					this.setText(str); // will also call thie_texValueChanged
				}
			}
          catch(Exception e){}
		}
	}

	void this_textValueChanged(TextEvent evt) {
		updateValueFromText();
	}

	/**
	 * called from Minkowski text input action event
	 */
	public void updateValueFromText() {
		double oldValue = value;
		String str = this.getText().trim();
     // System.out.println("TextValueChanged getText:"+this.getText()+ " oldValue:"+value );
		try {
			validData = true; // assume the string will give us valid data.
			if (str != null && str != "") {
				value = Double.valueOf(str).doubleValue();
				// System.out.println("new value:"+value);
              if(!noColor) this.setBackground(Color.yellow);// number seems to be OK
              if (value==oldValue) return;
				boundSupport.firePropertyChange("DValue", new Double(oldValue), new Double(value));
			} else {
              if(!noColor) this.setBackground(Color.red);
				validData = false;
				value = oldValue;
			}
      }catch(NumberFormatException e){    // internet explorer does not throw a number format exception!
          if(!noColor) this.setBackground(Color.red);
			validData = false;
			value = oldValue;
		}
	}
	
}


class SNumber_this_textAdapter implements java.awt.event.TextListener {
	SNumber adaptee;

	SNumber_this_textAdapter(SNumber adaptee) {
		this.adaptee = adaptee;
	}

	public void textValueChanged(TextEvent e) {
		adaptee.this_textValueChanged(e);
	}
}
