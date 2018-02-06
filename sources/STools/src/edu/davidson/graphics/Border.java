package edu.davidson.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import a2s.Panel;
import java.awt.Point;
import java.awt.Rectangle;


/**
 * A panel containing a single component, around which a border
 * is drawn.  Of course, the single component may be a
 * container which may contain other components, so a Border
 * can surround multiple components.<p>
 *
 * Thickness of the border, and the gap between the Component
 * and the border are specified at time of construction.
 * Default border thickness is 2 - default gap is 0.<p>
 *
 * Border color may be set via setLineColor(Color).<p>
 *
 * Border employs a DrawnRectangle to paint the border.  Derived
 * classes are free to override DrawnRectangle border() if they
 * wish to use an extension of DrawnRectangle for drawing their
 * border.<p>
 *
 * The following code snippet, from gjt.test.BorderTest creates
 * an AWT Button, and embeds the button in a border.  That
 * border is then embedded in another border.  The AWT Button
 * winds up inside of a cyan border with a pixel width of 7,
 * inside of a black border (pixel width 2):<p>
 *
 * <pre>
 *      private Border makeBorderedAWTButton() {
 *          Button button;
 *          Border cyanBorder, blackBorder;
 *
 *          button = new Button("Button Inside Two Borders");
 *          cyanBorder = new Border(button, 7);
 *          cyanBorder.setLineColor(Color.cyan);
 *
 *          blackBorder = new Border(cyanBorder);
 *
 *          return blackBorder;
 *      }
 *</pre>
 *
 * @version 1.0, Apr 1 1996
 * @version 1.1, Nov 8 1996
 *
 *    Added getComponent() for accessing component bordered.
 *
 * @author  David Geary
 * @see     DrawnRectangle
 * @see     ThreeDBorder
 * @see     EtchedBorder
 * @see     gjt.test.BorderTest
 */
public class Border extends Panel {
    protected int            thickness;
    protected int            gap;
    protected DrawnRectangle border;
	  protected Component      borderMe;

    protected static int _defaultThickness = 2;
    protected static int _defaultGap       = 0;

    public Border() { //added by WC to make this work as a bean.
         this(new HintPanel(), _defaultThickness, _defaultGap);
    }

    public Border(Component borderMe) {
        this(borderMe, _defaultThickness, _defaultGap);
    }
    public Border(Component borderMe, int thickness) {
        this(borderMe, thickness, _defaultGap);
    }
    public Border(Component borderMe, int thickness, int gap) {
		    this.borderMe  = borderMe;
        this.thickness = thickness;
        this.gap       = gap;
        setLayout(new BorderLayout());
        add(borderMe, "Center");

    }
	public Component getComponent() {return borderMe;}
    public Rectangle getInnerBounds() {
        return myBorder().getInnerBounds();
    }
  public void setLineColor(Color c) {
        myBorder().setLineColor(c);
        repaint();  // added by wc
    }
  public Color getLineColor()        {
        return myBorder().getLineColor();
    }
  public void paint(Graphics g) {
        myBorder().paint();
		    super.paint(g);  // ensures lightweight comps get drawn
    }
	public Insets getInsets() {
        return new Insets(thickness+gap, thickness+gap,
                          thickness+gap, thickness+gap);
	}
  public void setSize(int w, int h) {
      Point location = getLocation();
        setBounds(location.x, location.y, w, h);
  }
	public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, y, w, h);
        myBorder().setSize(w, h);
	}

  protected String paramString() {
        return super.paramString() + ",border=" +
               myBorder().toString() + ",thickness=" + thickness
               + ",gap=" + gap;
    }
  protected DrawnRectangle myBorder() { // BH needed because border() can not be inherited this way from JPanel
        if(border == null)
            border = new DrawnRectangle(this, thickness);
        return border;
    }
  // Bubble Hint Methods
  public void setHint(String s){
      if(borderMe instanceof HintPanel) ((HintPanel)borderMe).setBubbleHelp(s);
  }
  // beans accessor methods added by W. Christian
  public void setThickness(int t){
      thickness=t;
      myBorder().setThickness(t);
      repaint();
  }
  public int getThickness(){return thickness;}
  public void setGap(int g){gap=g; repaint();}
  public int getGap(){return gap;}
  public void setFillColor(Color c) {
        myBorder().setFillColor(c);
        repaint();  // added by wc
    }
  public Color getFillColor(){
        return myBorder().getFillColor();
  }
}
