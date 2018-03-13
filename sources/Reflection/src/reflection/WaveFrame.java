/*
**************************************************************************
**
**                      Class  WaveFrame
**
**************************************************************************
**
** class WaveFrame extends Frame
**
** @author Jim Nolen
**
*************************************************************************/


package reflection;

import a2s.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;


/**
*
* An instance of WaveFrame is created upon a right mouse click on WavePanel.
* WaveFrame simply displays a copy of the image in wavePanel and is positioned randomly
* on the screen.
*
*
*/
public class WaveFrame extends Frame {
  Image img = null;
  public WaveFrame() {
    super();
  }

  public WaveFrame(Image i) {
    this();
    img = i;
    this.setSize(i.getWidth(this),i.getHeight(this));
    setLocation((int)(300*Math.random()),(int)(300*Math.random()) );
    addWindowListener(new WindowAdapter() { public void
         windowClosing(WindowEvent e) { dispose();} } );
    setTitle(getClass().getName());

  }

  public void update(Graphics g){
    paint(g);
  }

  public void paint(Graphics g){
    if (img==null){
    }
    else{
      int w = this.getSize().width;
      int h = this.getSize().height;
      g.drawImage(img,0,0,w,h,this);
    }
  }
}