//Title: SSlider
//Version: 0.1
//Copyright: Dec 14, 1997
//Author:Wolfgang Christian
//Company:Davidson College
//Description:Slider for floating point numbers

package edu.davidson.display;
import java.awt.AWTEvent;

import a2s.*;
import java.awt.event.AdjustmentEvent;
//import borland.jbcl.util.BlackBox;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class SSlider extends Scrollbar implements PropertyChangeListener {
// basic slider using Float for value and Max/Min.  No captions or display.
        protected PropertyChangeSupport boundSupport;
        private double    dValue;
        private double    dMin;
        private double    dMax;

    public SSlider() {this(0.0d,0.0d,1.0);}

    public SSlider(double v, double min, double max) {
        super(Scrollbar.HORIZONTAL,0,3,0,100);
        boundSupport=new PropertyChangeSupport(this);
        dValue=v;
        dMin=min;
        dMax=max;
        this.enableEvents(AWTEvent.ADJUSTMENT_EVENT_MASK);

    }

    protected void processAdjustmentEvent(AdjustmentEvent evt){
        double oldVal=dValue;
        int val=evt.getValue();
        dValue=dMin+(dMax-dMin)*(val-this.getMinimum())/(this.getMaximum()-this.getMinimum()-this.getVisibleAmount());
        if(dValue>dMax) dValue=dMax;
        if(dValue<dMin) dValue=dMin;
        boundSupport.firePropertyChange("DValue",new Double(oldVal),new Double(dValue));
        super.processAdjustmentEvent(evt);
    }
    
    public void setDValue(double d) {
        if(dValue==d) return;
        double oldVal=dValue;
        dValue=d;
        double val=this.getMinimum()+(this.getMaximum()-this.getMinimum()-this.getVisibleAmount())*(d-dMin)/(dMax-dMin);
        this.setValue((int)val);
        boundSupport.firePropertyChange("DValue",new Double(oldVal),new Double(dValue));
    }

    public void setDMinMaxAndValue(double dmin, double dmax, double dval){
          double oldVal=dValue;
          dMax=dmax;
          dMin=dmin;
          dValue=dval;
          if(dValue<dmin)dValue=dMin;
          if(dValue>dmax)dValue=dMax;
          double ival=this.getMinimum()+(this.getMaximum()-this.getMinimum()-this.getVisibleAmount())*(dValue-dMin)/(dMax-dMin);
          this.setValue((int)ival); // set the slider position
          if(oldVal!=dValue) boundSupport.firePropertyChange("DValue",new Double(oldVal),new Double(dValue));
	}

	public double getDValue(){return dValue;}

    public void setDMinMax(double dmin, double dmax){
          dMax=dmax;
          dMin=dmin;
          if(dValue<dmin)dValue=dMin;
          if(dValue>dmax)dValue=dMax;
          double ival=this.getMinimum()+(this.getMaximum()-this.getMinimum()-this.getVisibleAmount())*(dValue-dMin)/(dMax-dMin);
          this.setValue((int)ival); // set the slider position
    }

    public void setDMin(double dmin) {
          dMin=dmin;
          if(dValue<dmin)dValue=dMin;
          double ival=this.getMinimum()+(this.getMaximum()-this.getMinimum()-this.getVisibleAmount())*(dValue-dMin)/(dMax-dMin);
          this.setValue((int)ival);
        }
    public double getDMin(){return dMin;}

    public void setDMax(double dmax) {
          dMax=dmax;
          if(dValue>dmax)dValue=dMax;
          double ival=this.getMinimum()+(this.getMaximum()-this.getMinimum()-this.getVisibleAmount())*(dValue-dMin)/(dMax-dMin);
          this.setValue((int)ival);
    }
    public double getDMax(){return dMax;}

// BH not used
//    void this_adjustmentValueChanged(AdjustmentEvent e) {
//      double oldVal=dValue;
//      int val=this.getValue();
//      dValue=dMin+(dMax-dMin)*(val-this.getMinimum())/(this.getMaximum()-this.getMinimum()-this.getVisibleAmount());
//      boundSupport.firePropertyChange("DValue",new Double(oldVal),new Double(dValue));
//    }

    public void addPropertyChangeListener(PropertyChangeListener l){
      //use the support class
    	// BH this is fired in Swing from TextFieldUI prior to completion of the super() call.
    	if (boundSupport == null)
    		super.addPropertyChangeListener(l);
    	else
    		boundSupport.addPropertyChangeListener(l);
    }
    public void removePropertyChangeListener(PropertyChangeListener l){
        boundSupport.removePropertyChangeListener(l);
    }

    public void propertyChange(PropertyChangeEvent evt){
      //make changes to this object if it is bound to another object
      if(evt.getPropertyName().equals("DValue")){
    	  // BH must set dValue here, before trigging events with setValue()
          dValue = (Double)evt.getNewValue();
          try{
                  double val=(this.getMaximum()-this.getMinimum()-this.getVisibleAmount())*(dValue-dMin)/(dMax-dMin);
                  this.setValue((int)val);
          }
          catch(Exception e){}
      }
    }
}
