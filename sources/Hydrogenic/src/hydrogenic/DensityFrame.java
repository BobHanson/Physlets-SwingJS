
//Title:        Hydrogen Atom Probabilty Density
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Cabell Fisher and Dr. Wolfgang Christian

package hydrogenic;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.*;

import javax.swing.JFrame;

import java.awt.*;


public class DensityFrame extends JFrame {
    Image img=null;
  
  public DensityFrame(Image i) {
  super();
  img=i;
  setSize(i.getWidth(this),i.getHeight(this));
  setLocation((int)(300*Math.random()),(int)(300*Math.random()) );

  addWindowListener(new WindowAdapter() { 
	  public void windowClosing(WindowEvent e) { dispose();} } );
  setTitle(getClass().getName());
  }

  /**
  * This method overrides the AWT paint method.  It paints the image on the cloned
  * modeFrame created by the mousePressed() method in DensityCanvas class.
  * The method also paints a box around the new image designating the boundaries
  * of the picture so the physics and extra space don't get confused.
  */
  public void paint(Graphics g){
      String calcString="Error: No Image.";
      int w = getSize().width;
      int h = getSize().height;
      int max=Math.max(w,h);
      int min=Math.min(w,h);
      int position=(max-min)/2;

      if (img == null) {
          super.paint(g);
          g.setColor(Color.black);
          FontMetrics fm = g.getFontMetrics();
          int x = (w - fm.stringWidth(calcString))/2;
          int y = h/2;
          g.drawString(calcString, x, y);
      }
      else {
          g.setColor(Color.black);
          g.fillRect(0,0,w,h);
          w=Math.min(w,h);
          h=Math.min(w,h);
          if (getSize().width<getSize().height){
            g.setColor(Color.red);
            g.drawImage(img, 0, position, w, h, this);
            g.drawLine(0,getSize().height-position,getSize().width,getSize().height-position);
            g.drawLine(getSize().width-5,position,getSize().width-5,getSize().height-position);
            g.drawLine(0,position,getSize().width,position);
            g.drawLine(5,position,5,getSize().height-position);
          }
          else {
            g.drawImage(img, position, 0, w, h, this);
            g.setColor(Color.red);
            g.drawLine(position,0,position,getSize().height);
            g.drawLine(getSize().width-position,0,getSize().width-position,getSize().height);
            g.drawLine(position,23,getSize().width-position,23);
            g.drawLine(position,getSize().height-5,getSize().width-position,getSize().height-5);
          }
      }

    }

    public void update(Graphics g){paint(g);}

}

 