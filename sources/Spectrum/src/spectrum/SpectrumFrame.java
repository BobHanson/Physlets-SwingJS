/**
 *  SpectrumFrame.class extends Frame and is essentially a duplicate
 *  of the image on SpectrumPanel.
 *
 *
 *  @authors Jim Nolen and Wolfgang Christian
*/
package spectrum;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.*;
import java.awt.event.*;

public class SpectrumFrame extends Frame {
    Image img=null;

    public SpectrumFrame(Image i) {
          super();
          img=i;
          setSize(i.getWidth(this),2*i.getHeight(this));
          setLocation((int)(300*Math.random()),(int)(300*Math.random()) );
          addWindowListener(new WindowAdapter() { public void
              windowClosing(WindowEvent e) { dispose();} } );
          setTitle(getClass().getName());
    }

    public void update(Graphics g){  paint(g);}

    public void paint(Graphics g){
      String calcString="Error: No Image.";
      int w = getSize().width;                      //if square mode recalculate freq
      int h = getSize().height;
      if (img == null) {
          super.paint(g);
          g.setColor(Color.black);
          FontMetrics fm = g.getFontMetrics();
          int x = (w - fm.stringWidth(calcString))/2;
          int y = h/2;
          g.drawString(calcString, x, y);
      } else {
          g.setColor(Color.black);
          g.fillRect(0,0,w,h);
          g.drawImage(img, 0, 0, w, h, this);
      }
    }
}


