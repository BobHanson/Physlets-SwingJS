package dataGraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

//import java.awt.*;
import a2s.*;
import edu.davidson.graph.*;
import edu.davidson.tools.*;

/**
 * <p>Bar is part of the Davidson College Physlets project.  It is a
 * subclass of SApplet and implements inter-applet communication using the
 * SDataListener interface.</p>
 * <p>The following embedding parameters are defined:</p>
 * <div align="center">
 * <center>
 * <table border="2" width="473">
 * <tr>
 * <th align="center" width="87">Parameter</th>
 * <th align="center" width="51">&nbsp;Value</th>
 * <th align="center" width="311">Description</th>
 * </tr>
 * <tr>
 * <td align="center" width="87">Value</td>
 * <td align="center" width="51">10</td>
 * <td width="311">The initial height of the bar.</td>
 * </tr>
 * <tr>
 * <td align="center" width="87">Min</td>
 * <td align="center" width="51">0</td>
 * <td width="311">The minimum value.</td>
 * </tr>
 * <tr>
 * <td align="center" width="87">Max</td>
 * <td align="center" width="51">100.0</td>
 * <td width="311">The full scale value.</td>
 * </tr>
 * <tr>
 * <td align="center" width="87">Vertical</td>
 * <td align="center" width="51">true</td>
 * <td width="311">Vertical or horizontal display.</td>
 * </tr>
 * <tr>
 * <td align="center" width="87">ShowControls</td>
 * <td align="center" width="51">true</td>
 * <td width="311">Show a text field below the bar.</td>
 * </tr>
 * </table>
 * </center>
 * </div>
 *
 * @author             Wolfgang Christian
 * @version            $Revision: 0.9c $, $Date: 1999/07/21 08:00:00 $
 */
public class Bar extends edu.davidson.tools.SApplet implements edu.davidson.tools.SDataListener {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
double                             min;
   double                             max;
   boolean                            vert;
   int                                numBars       = 1;
   edu.davidson.graphics.EtchedBorder etchedBorder  = new edu.davidson.graphics.EtchedBorder ();
   //Panel etchedBorder = new Panel();

   BorderLayout                       borderLayout1 = new BorderLayout ();
   double                             value         = 0;
   Color                              negColor      = Color.blue;
   Color                              posColor      = Color.red;
   BarGraph                           bar           = new BarGraph ();
   BorderLayout                       borderLayout2 = new BorderLayout ();
   SLabel                             valField      = new SLabel ();
   boolean                            showControls  = false;
   boolean                            autoScale     = false;
   Panel                              panel1        = new Panel ();
   Label                              barLabel      = new Label ();
   TextLine title= new TextLine();
   String titleStr="";
  BorderLayout borderLayout3 = new BorderLayout();


   /**
    * Constructor Bar
    * @y.exclude
    */
   public Bar () {}


   /**
    * Exclude the javadoc because this method should not be scripted.
    * @y.exclude
     */
   public void init () {
      initResources(null);
      int    barWidth = 10;
      String barText  = "";
      try {
         min = Double.valueOf (this.getParameter ("Min", "0")).doubleValue ();
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         max = Double.valueOf (this.getParameter ("Max", "100")).doubleValue ();
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         value = Double.valueOf (this.getParameter ("Value", "10")).doubleValue ();
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         vert = Boolean.valueOf (this.getParameter ("Vertical", "true")).booleanValue ();
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         autoScale = Boolean.valueOf (this.getParameter ("AutoScale", "false")).booleanValue ();
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         showControls = Boolean.valueOf (this.getParameter ("ShowControls", "true")).booleanValue ();
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         barWidth = Integer.parseInt (this.getParameter ("BarWidth", "10"));
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         numBars = Integer.parseInt (this.getParameter ("NumSeries", "1"));
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         barText = this.getParameter ("Label", "");
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         titleStr = this.getParameter ("Title", "");
      } catch (Exception e) {
         e.printStackTrace ();
      }
      try {
         jbInit ();
      } catch (Exception e) {
         e.printStackTrace ();
      }
      if (!showControls) {
         panel1.setVisible (false);
      }
      valField.setValue (value);
      bar.barWidth = barWidth;
      if (barText.equals ("")) barLabel.setVisible (false);
      else {
         barLabel.setText (barText);
         barLabel.setVisible (true);
      }
      addDataListener (this);
   }

   // Component initialization

   /**
    * Method jbInit
    *
    *
    * @throws Exception
    */
   private void jbInit () throws Exception {
      this.setLayout (borderLayout1);
      bar.setLayout (borderLayout2);
      barLabel.setAlignment (2);
      this.setBackground (Color.lightGray);
      panel1.setBackground (Color.lightGray);
      etchedBorder.setBackground (Color.lightGray);
      etchedBorder.setLayout(borderLayout3);
      this.add (etchedBorder, BorderLayout.CENTER);
      this.add (panel1, BorderLayout.SOUTH);
      panel1.add (barLabel, null);
      panel1.add (valField, null);
      etchedBorder.add (bar, BorderLayout.CENTER);
   }

   /**
    * Gets information about this applet.
    * @return the information string
    */
   public String getAppletInfo() {
     return "Bar by W. Christian. Email:wochristian@davidson.edu";
  }

   /**
    * Gets the embedding parameter information.
    *
    * @return the information
    */
   public String[][] getParameterInfo () {
      String pinfo[][] = {
         { "Min", "double", "Minimum value" }, { "Max", "double", "Maximum value" },
         { "Vertical", "boolean", "Orientation" },
      };
      return pinfo;
   }

   /**
   * Counts the number of applets on the html page.
   *
   * @param func
   * @param vars
   *
   * @return the number of applets
   * @y.exclude
   */
  public int getAppletCount() {
    if(firstTime) return 0;
    else return super.getAppletCount();
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
    public void start(){
      if(firstTime){
           firstTime=false;
           //setValue(100);
      }
      super.start();
    }

   /**
    * Method setAutoscale
    *
    * @param scale
    */
   public void setAutoscale (boolean scale) {
      autoScale = scale;
      bar.repaint ();
   }

   /**
   * Change the display format for the value field.  Use UNIX fprint syntax.
   *
   * @param    str the format string
   */
  public void setFormat(String str){valField.setFormat(str);}

   /**
    * Method setLabel
    *
    * @param newLabel
    */
   public void setLabel (String newLabel) {
      barLabel.setText (newLabel);
      if (newLabel.equals ("")) {
         barLabel.setVisible (false);
      } else barLabel.setVisible (true);
      this.invalidate ();
      this.validate ();
   }

   /**
    * Sets the label.
    *
    * @param newLabel the new label
    */
   public void setTtile (String _title) {
      titleStr=_title;
      bar.repaint ();
   }

   /**
    * Method setBarWidth
    *
    * @param newWidth
    */
   public void setBarWidth (int newWidth) {
      bar.barWidth = newWidth;
      bar.repaint ();
   }

   /**
    * Method setMax
    *
    * @param m
    */
   public void setMax (double m) {
      this.max = m;
      bar.repaint ();
   }

   /**
    * Method setMin
    *
    * @param m
    */
   public void setMin (double m) {
      this.min = m;
      bar.repaint ();
   }

   /**
    * Sets the value of the bar.
    *
    * @param v the value
    */
   public void setValue (double v) {
      if (autoScale) {
         if (v > this.max) {
            this.max = v;
         }
         if (v < this.min) {
            this.min = v;
         }
      }
      this.value = v;
      valField.setValue (v);
      bar.repaint ();
   }

   /**
    * Sets the color for the postive bar.
    *
    * Color values should be in the range [0...255]
    *
    * @param r red
    * @param g green
    * @param b blue
    */
   public void setPosRGB (int r, int g, int b) {
      this.posColor = new Color (r, g, b);
   }

   /**
    * Sets the color for the postive bar.
    *
    * Color values should be in the range [0...255]
    *
    * @param r red
    * @param g green
    * @param b blue
    */

   public void setNegRGB (int r, int g, int b) {
      this.negColor = new Color (r, g, b);
   }

   /**
    * Method setNumericFormat
    *
    * @param str
    */
   public void setNumericFormat (String str) {
      valField.setFormat (str);
   }

   // SDataListener Methods

   /**
    * Method addDatum
    *
    * @param id
    * @param x
    * @param y
    */
   public void addDatum (int id, double x, double y) {
      setValue (x);
   }

   /**
    * Method addDatum
    *
    * @param s
    * @param id
    * @param x
    * @param y
    */
   public void addDatum (SDataSource s, int id, double x, double y) {
      setValue (x);
   }

   /**
    * Method addData
    * Exclude the javadoc because this method should not be scripted.
    * @y.exclude
    * @param id
    * @param x
    * @param y
    */
   public void addData (int id, double x[], double y[]) {
      if (x == null) {
         return;
      }
      int n = x.length;
      if (n < 1) {
         return;
      }
      setValue (x[n - 1]);
   }

   /**
    * Method addData
    *
    * @param s
    * @param id
    * @param x
    * @param y
    */
   public void addData (SDataSource s, int id, double x[], double y[]) {
      addData (id, x, y);
   }

   /**
    * Method deleteSeries
    *
    * @param id
    */
   public void deleteSeries (int id) {
      setValue (0);
   }

   /**
    * Method clearSeries
    * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
    * @param id
    */
   public void clearSeries (int id) {
      setValue (0);
   }

   /**
    * Method setOwner
    * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
    * @param owner
    */
   public void setOwner (SApplet owner) {}

   /**
    * Exclude the javadoc because this method should not be scripted.
    * @y.exclude
     */
   public SApplet getOwner () {
      return this;
   }

   /**
    * Class BarGraph
    */
   public class BarGraph extends Panel {

      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int   barWidth = 10;
      Image osi      = null;

      /**
       * Method update
       *
       * @param g
       */
      public void update (Graphics g) {
         paint (g);
      }


      /**
       * Method paint
       *
       * @param g
       */
      public void paint(Graphics g) {
        int w = getBounds().width;
        int h = getBounds().height;
        if ( (w < 4) || (h < 4)) {
          return;
        }
        if ( (osi == null) || (osi.getWidth(null) != w) ||
            (osi.getHeight(null) != h)) {
          osi = createImage(w, h);
          //System.out.println("osi created");
        }
        if (osi == null) {
          return;
        }
        paintOSI();
        g.drawImage(osi, 0, 0, w, h, this);
      }

      /**
       * Method paintOSI
       *
       */
      private void paintOSI () {
         int    x, y, w, h, o;
         double origin = 0;
         if ((min <= 0) && (max > 0)) {
            origin = 0;
         }
         Graphics g = osi.getGraphics ();
         w = osi.getWidth(null);
         h = osi.getHeight(null);
         Color bgColor = Bar.this.getBackground ();
         g.setColor (bgColor);
         g.fillRect (0, 0, w, h);
         if (vert) {
            o = (int) (0.4999999 + h * max / (max - min));
            h = (int) (h * (value - origin) / (max - min));
            if (h >= 0) {
               y = o - h;
               g.setColor (posColor);
            } else {
               y = o;
               h = -h;
               g.setColor (negColor);
            }
            // g.fillRect(x,y,w,h);
            g.fillRect (w / 2 - barWidth, y, 2 * barWidth, h);
            g.setColor (Color.black);
            g.drawLine (0, o, w - 1, o);
         } else {  // horizontal
            o = (int) (0.4999999 - w * min / (max - min));
            w = (int) (w * (value - origin) / (max - min));
            if (w >= 0) {
               x = o;
               g.setColor (posColor);
            } else {
               w = -w;
               x = o - w;
               g.setColor (negColor);
            }
            // g.fillRect(x,y,w,h);
            g.fillRect (x, h / 2 - barWidth, w, 2 * barWidth);
            g.setColor (Color.black);
            g.drawLine (o, 0, o, h - 1);
         }
         if(!titleStr.equals("")){
            title.setText(titleStr);
            title.draw(g,w/2,15,TextLine.CENTER);
         }
         g.dispose ();
      }
   }
}
