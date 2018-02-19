package lorentz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;

//import java.awt.*;
import a2s.*;
import edu.davidson.display.*;


public final class VarScrollBar extends Panel
{
	String caption;
	Scrollbar scrollBar;
	double value;
	double min, max;
	TextField valFld;
	boolean hasChanged=false;
	Label captionLbl;

	public VarScrollBar(String caption_, double value_,double min_, double max_)
	{
		caption=caption_;
		setLayout( new BorderLayout());
		value=value_; min=min_; max=max_;
		if(value>max) value=max;
		if(value<min) value=min;
		scrollBar=new Scrollbar(Scrollbar.HORIZONTAL,(int)(100*(value-min)/(max-min)),4,0,100);
		add("West",captionLbl=new Label(caption_,Label.LEFT));
		add("Center",scrollBar);
		add("East",valFld=new TextField(""+value_));
	}


	public Insets insets()
	{
		int inH=(getSize().height-30) /2;
		if (inH<0) inH=0;
		return new Insets(inH,2,inH,2);
	}
	public Dimension preferredSize()
	{
		//Dimension d=new Dimension(150,15);
		return new Dimension(100,20);
	}
	public Dimension minimumSize()
	{
		//Dimension d=new Dimension(150,15);
		return new Dimension(40,15);
	}

	public boolean keyUp(Event evt, int chr)
	{
		if (evt.target.equals(valFld))
		{
		valFld.setBackground(Color.yellow);
		}
		else return false;
		return true;//event was taken care of here.
	}

	public void setCaption(String caption_)
	{
	   caption=caption_;
	   captionLbl.setText(caption);
	}

	public double getValue()
	{
		hasChanged=false;
		valFld.setBackground(Color.white);
		valFld.setText(""+value);
		scrollBar.setValue((int)(100*(value-min)/(max-min)));
		return value;
	}

	public void setValue(double val)
	{
		hasChanged=false;
		valFld.setBackground(Color.white);
		value=val;
		valFld.setText(""+value);
		scrollBar.setValue((int)(100*(value-min)/(max-min)));
	}

	public void setMinMax(double newMin, double newMax)
	{
		hasChanged=false;
		valFld.setBackground(Color.white);
		min=newMin;
		max=newMax;
		valFld.setText(""+value);
		scrollBar.setValue((int)(100*(value-min)/(max-min)));
	}

	public boolean action(Event evt, Object arg)
	{
		if (evt.target.equals(valFld))
		{
		//System.out.println("Action"); //cr key
		value=(double)Format.atof((String)arg);
		scrollBar.setValue((int)(100*(value-min)/(max-min)));
		valFld.setBackground(Color.white);
		//valFld.repaint();	 CRASH!
		String str=valFld.getText();
		valFld.setText(str);
		Event newEvent=new ChangeValueEvent( this,evt,ChangeValueEvent.NEWVALUE);
		deliverEvent(newEvent);
		}
		else return false;
		return true;//event was taken care of here.
	}


	public boolean handleEvent(Event evt)
	{
		if (evt.id==Event.SCROLL_ABSOLUTE
		       ||evt.id==Event.SCROLL_LINE_DOWN
			   ||evt.id==Event.SCROLL_LINE_UP
			   ||evt.id==Event.SCROLL_PAGE_DOWN
			   ||evt.id==Event.SCROLL_PAGE_UP)
		{
			value=min+(max-min)*scrollBar.getValue()/100.0;
			valFld.setBackground(Color.white);
			valFld.setText(""+value);
			Event newEvent=new ChangeValueEvent( this,evt,ChangeValueEvent.NEWVALUE);
			deliverEvent(newEvent);
			return true;
		}
	  return super.handleEvent(evt);
	}
}

final class ChangeValueEvent extends Event
{
	public static final int NEWVALUE=1;
	//private int eventType;
	public ChangeValueEvent(Object target, Event event, int type)
	{
		super(target, event.when, event.id, event.x, event.y, event.key, event.modifiers, event.arg);
		//eventType=type;
		id=-1;
	}
	protected String paramString()
	{
		String typeString=new String();
		typeString="NEWVALUE";
		return super.paramString()+typeString;
	}
}




