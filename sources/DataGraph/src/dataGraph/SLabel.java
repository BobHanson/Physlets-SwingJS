package dataGraph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;


import java.awt.*;
import edu.davidson.display.Format;


public class SLabel extends Panel{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
double val=0;
  int    preferredW=20;
  double chopVal=1.0e-12;
  Format  format= new Format("%-+6.2g");
  Format  formatI= new Format("%-+6d");
  Dimension ms=new Dimension(20,17);
  Dimension ps=new Dimension(preferredW,17);

  public SLabel() {
        this.setBackground(Color.white);
  }

  /**
   * Set the value to be displayed.
   *
   * @param the new value.
   */
  public void setValue(double _val){
    val=_val;
    repaint();
  }

  /**
   * Change the numeric format.  Use UNIX fprint syntax.
   *
   * @param              str The format for cooridinate display.
   */
  public void setFormat(String str){
    format= new Format(str);
  }

  public void update(Graphics g){ paint(g);}

  public void paint(Graphics g) {
      int h=getBounds().height;
      int w=getBounds().width;
      g.setColor(getBackground());
      g.fillRect(0,0,w,h);
      g.setColor(Color.black);
      String str;
      if(Math.abs(val-(int)val)<= chopVal) str=formatI.form((int)val);
      else str=format.form(edu.davidson.tools.SUtil.chop(val,chopVal));
      FontMetrics fm=g.getFontMetrics(g.getFont());
      int sw=fm.stringWidth(str);
      if(sw>preferredW){
        preferredW=sw+4;
        ps=new Dimension(preferredW,17);
        getParent().invalidate();
        getParent().validate();
      }
      g.drawString(str, (w-sw)/2, h-fm.getDescent()-2);
  }

  public Dimension getMinimumSize(){ return ms;}
  public void setMinimumSize(Dimension s){ ms=s;}

  public Dimension getPreferredSize(){ return ps;}
  public void setPreferredSize(Dimension s){ ps=s;}
}