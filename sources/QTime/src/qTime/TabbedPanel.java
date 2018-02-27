// TabbedPanel
// A panel capable of displaying one of many components at a time. The
// component to display is chosen by a row of tab buttons.
package qTime;

//import java.awt.*;
import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import a2s.*;


public class TabbedPanel extends Panel
{
	TabSelector tab;		// component for choosing panel
	TabbedDisplayPanel disp;	// where other panels are displayed
	CardLayout card;

	public TabbedPanel()
	{
	Color hi = new Color(230,230,230), lo = new Color(50,50,50);
	setLayout(new BorderLayout());
	add("North",tab = new TabSelector(hi, lo));
	add("Center",disp = new TabbedDisplayPanel(hi, lo));
	disp.setLayout(card = new CardLayout());
	}

	// addItem
	// Add a component to be chosen by a tab with the given name
	public void addItem(String n, Component c)
	{
	tab.addItem(n);
	disp.addItem(n, c);
	}

	// select
	// Display a component in the panel
	public void select(String n)
	{
	tab.choose(n);
	disp.choose(n);
	}

	// chose
	// Called back by a TabSelector object when the user clicks on a tab
	void chose(String n)
	{
	disp.choose(n);
	}
}

class TabSelector extends Canvas
{
	Color hi, lo;
	Vector name = new Vector();
	int chosen = 0;
	Font font = new Font("timesRoman", Font.PLAIN, 12),
	     chfont = new Font(font.getName(), Font.BOLD, 13);

	TabSelector(Color h, Color l)
	{
	hi = h; lo = l;
	}

	void addItem(String n)
	{
	name.addElement(n);
	paint(getGraphics());
	}

	void choose(String n)
	{
	for(int i=0; i<name.size(); i++)
		if (((String)name.elementAt(i)).equals(n)) {
			chosen = i;
			paint(getGraphics());
			}
	}

	public void paint(Graphics g)
	{
	if (g == null || name.size() == 0)
		return;
	g.setColor(Color.lightGray);
	g.fillRect(0, 0, getSize().width, getSize().height);
	int tw = getSize().width / name.size(),
	    th = getSize().height;
	for(int i=0; i<name.size(); i++) {
		int x = tw*i;
		if (i == chosen) {
			g.setColor(lo);
			g.drawLine(x+tw-3, 1, x+tw-3, th-1);
			g.drawLine(x+tw-4, 2, x+tw-4, th-1);
			g.setColor(hi);
			g.drawLine(x, 0, x, th-1);
			g.drawLine(x+1, 0, x+1, th-1);
			g.drawLine(x, 0, x+tw-4, 0);
			g.drawLine(x, 1, x+tw-5, 1);
			g.drawLine(x+tw-3, th-1, x+tw-1, th-1);
			g.drawLine(x+tw-3, th-2, x+tw-1, th-2);
			}
		else {
			g.setColor(lo);
			g.drawLine(x+tw-3, 6, x+tw-3, th-1);
			g.drawLine(x+tw-4, 7, x+tw-4, th-1);
			g.setColor(hi);
			g.drawLine(x, 5, x, th-1);
			g.drawLine(x+1, 5, x+1, th-1);
			g.drawLine(x, 5, x+tw-4, 5);
			g.drawLine(x, 6, x+tw-5, 6);
			g.drawLine(x, th-1, x+tw-1, th-1);
			g.drawLine(x, th-2, x+tw-1, th-2);
			}
		g.setColor(lo);
		if (i == chosen) g.setFont(chfont);
		else g.setFont(font);
		String str = (String)name.elementAt(i);
		int textw = g.getFontMetrics().stringWidth(str);
		int texth = g.getFontMetrics().getHeight();
		if (textw < tw-5)
			g.drawString(str, x+(tw-textw)/2, (th-texth)/2+texth);
		}
	}

	public boolean mouseDown(Event evt, int x, int y)
	{
	if (name.size() == 0) return false;
	chosen = x / (getSize().width / name.size());
	paint(getGraphics());
	((TabbedPanel)getParent()).chose((String)name.elementAt(chosen));
	return true;
	}

	public Dimension minimumSize()
	{
	return new Dimension(50,25);
	}

	public Dimension preferredSize()
	{
	return minimumSize();
	}
}

class TabbedDisplayPanel extends Panel
{
	Color hi, lo;
	CardLayout card;

	TabbedDisplayPanel(Color h, Color l)
	{
	hi = h; lo = l;
	setLayout(card = new CardLayout());
	}

	// addItem
	// Add one component to the set of possibles to be shown
	void addItem(String n, Component c)
	{
	add(n, c);
	}

	// choose
	// Display the named panel
	void choose(String n)
	{
	((CardLayout)getLayout()).show(this, n);
	}

	public Insets insets()
	{
	return new Insets(5,5,5,5);
	}

	public void paint(Graphics g)
	{
	g.setColor(hi);
	g.drawLine(0, 0, 0, getSize().height-1);
	g.drawLine(1, 0, 1, getSize().height-1);
	g.setColor(lo);
	g.drawLine(0, getSize().height-1, getSize().width-1, getSize().height-1);
	g.drawLine(0, getSize().height-2, getSize().width-1, getSize().height-2);
	g.drawLine(getSize().width-1, getSize().height-1, getSize().width-1, 0);
	g.drawLine(getSize().width-2, getSize().height-1, getSize().width-2, 0);
	}
}

