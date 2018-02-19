package edu.davidson.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import a2s.*;
import java.awt.Window;

public class Bubble extends Window {
	private BubblePanel panel;

	public Bubble(Component comp, String text) {
		super(Util.getFrame(comp));
    panel=new BubblePanel(text);
		add(panel, "Center");
	}
	public void setVisible(boolean b) {
		pack();
		super.setVisible(b);
	}
  public void setText(String txt) {
    panel.setText(txt);
    if(txt==null) setVisible(false);
    else{
        pack();
        panel.repaint();
    }
	}
}
class BubblePanel extends Panel {
	String text;

	public BubblePanel(String text) {
		this.text = text;
		setForeground(Color.black);
    setBackground(Color.yellow);
	}
	public void paint(Graphics g) {
		Dimension   size = getSize();
		FontMetrics fm   = g.getFontMetrics();
		g.drawRect(0,0,size.width-1,size.height-1);
		g.drawString(text,2,fm.getAscent()+2);
	}
	public Dimension getPreferredSize() {
    String vendor=System.getProperty("java.vendor");
		Graphics    g  = getGraphics();
    if(g==null) return new Dimension(10,10);
		FontMetrics fm = g.getFontMetrics();
    int width=fm.stringWidth(text)+4;
    int height=fm.getHeight()+4;
    if(width<50) width=50;
    if(!vendor.startsWith("Microsoft"))height=height+15;
		return new Dimension(width,height);
	}
  void setText(String text){this.text = text;}
}
