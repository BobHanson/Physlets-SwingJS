package impedance;
//import java.awt.*;

import java.awt.Color;
import java.awt.Graphics;

import a2s.*;


public class Status extends Canvas {
	Status(String s) {
		/** j2sNative */{
			resize(100,20);
		}
		setBackground(Color.white);
		str=s;
	}
	void setText(String s) {
		str=s;
		color=Color.blue;
		repaint();
	}
	void errorText(String s) {
		str=s;
		color=Color.red;
		repaint();
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, size().width-1, size().height-1);
		g.setColor(color);
		g.drawString(str, 3, 13);
	}
	private Color color=Color.blue;
	private String str;
}