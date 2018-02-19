package lorentz;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Insets;

//import java.awt.*;
import a2s.*;

public class SizedButton extends Panel
{
	Button button;
	public SizedButton(String str)
	{
		button=new Button(str);
		setLayout( new BorderLayout());
		add("Center",button);
	}
	public Insets insets()
	{
		int inH=(getSize().height-26) /2;
		if (inH<0) inH=0;
		return new Insets(inH,2,inH,2);
	}
	public boolean action(Event evt, Object arg)
	{
		if (evt.target.equals(button) )
		{
			evt.target=this;
			return false;
		}
		else return false;
	}

	public final void setLabel(String  label)
	{
		button.setLabel(label);
	}

}
