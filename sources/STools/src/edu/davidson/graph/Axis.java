package edu.davidson.graph;


import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Enumeration;
import edu.davidson.display.Format;



/*
**************************************************************************
**
**    Class  Axis
**
**************************************************************************
**    Copyright (C) 1995, 1996 Leigh Brookshaw
**
**    This program is free software; you can redistribute it and/or modify
**    it under the terms of the GNU General Public License as published by
**    the Free Software Foundation; either version 2 of the License, or
**    (at your option) any later version.
**
**    This program is distributed in the hope that it will be useful,
**    but WITHOUT ANY WARRANTY; without even the implied warranty of
**    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**    GNU General Public License for more details.
**
**    You should have received a copy of the GNU General Public License
**    along with this program; if not, write to the Free Software
**    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
**************************************************************************
**
**    This class is designed to be used in conjunction with
**    the Graph2D class and DataSet class for plotting 2D graphs.
**
*************************************************************************/



/**
 * This class controls the look and feel of axes.
 * It is designed to be used in conjunction with
 * the Graph2D class and DataSet class for plotting 2D graphs.
 *
 * To work with the other classes a system of registration is used.
 * The axes have to be attached to the controlling Graph2D class
 * and the DataSet's have to be attached to both the Graph2D class
 * and the Axis class.
 *
 * This way the 3 main classes Graph2D, Axis and DataSet know of each
 * others existence.
 *
 * This does not mean the classes cannot be used independently, they can
 * but in this mode nothing is automated, the user must code everything
 * manually
 *
 * @version  $Revision: 1.12 $, $Date: 1996/09/12 03:27:07 $.
 * @author   Leigh Brookshaw
 */

public class Axis extends Object {
// Added by W. Christian to produce better labels
private Format labelFormat= new Format("%5.2f");
// Added by W. Christian to better control range
double rangeMinimum=0;
double rangeMaximum=0;
boolean rangeBounded=false;


/**
 *    Restrict the range on the axis so that it can be no smaller than the given values.
 *
 *    @param restrictXRange true to restrict the range, false otherwise
 *    @param min            minimum value.
 *    @param max            maximum value.
*/
  public void setMinRange(boolean rangeBounded, double min,double max){
      this.rangeBounded=rangeBounded;
      rangeMinimum=min;
      rangeMaximum=max;
      if (rangeBounded ){
              this.resetRange();
      }
  }

// orginal methdos and data start here.

/*
***************************
** Public Static Values
**************************/

/**
 *    Constant flagging Horizontal Axis
 */
      static final int  HORIZONTAL = 0;
/**
 *    Constant flagging Vertical Axis
 */
      static final int  VERTICAL   = 1;
/**
 *    Constant flagging Axis position on the graph.
 *    Left side => Vertical
 */
      public static final int  LEFT       = 2;
/**
 *    Constant flagging Axis position on the graph.
 *    Right side => Vertical
 */
      public static final int  RIGHT      = 3;
/**
 *    Constant flagging Axis position on the graph.
 *    Top side => Horizontal
 */
      public static final int  TOP        = 4;
/**
 *    Constant flagging Axis position on the graph.
 *    Bottom side => Horizontal
 */
      public static final int  BOTTOM     = 5;
/**
 *    The first guess on the number of Labeled Major tick marks.
 */
      static final int  NUMBER_OF_TICS = 4;

/*
***********************
** Public Variables
**********************/

  /**
   *    If <i>true</i> draw a grid positioned on major ticks over the graph
   */
      public boolean  drawgrid        = false;
  /**
   *    If <i>true</i> draw a line positioned on the Zero label tick mark.
   */
      public boolean  drawzero        = false;
  /**
   * Color of the grid
   */
      public Color   gridcolor        = null;
  /**
   * Color of the line at the Zero label
   */
      public Color   zerocolor        = null;
  /**
   * Default value <i>true</i>. Normally never changed. If set <i>false</I>
   * the Axis draw method exits without drawing the axis.
   * @see Axis#drawAxis()
   */
      public boolean redraw           = true;
  /**
   * Rescale the axis so that labels fall at the end of the Axis. Default
   * value <i>false</i>.
   */
      public boolean force_end_labels = false;
  /**
   * Size in pixels of the major tick marks
   */
      public int     major_tic_size = 10;
  /**
   * Size in pixels of the minor tick marks
   */
      public int     minor_tic_size  = 5;
  /**
   * Number of minor tick marks between major tick marks
   */
      public int     minor_tic_count = 1;
  /**
   * Color of the Axis.
   */
      public Color   axiscolor;
  /**
   * Minimum data value of the axis. This is the value used to scale
   * data into the data window. This is the value to alter to force
   * a rescaling of the data window.
   */
      public double minimum;
  /**
   * Maximum data value of the axis. This is the value used to scale
   * data into the data window. This is the value to alter to force
   * a rescaling of the data window.
   */
      public double maximum;
  /**
   * Before the Axis can be positioned correctly and drawn the data window
   * needs to be calculated and passed to the Axis.
   */
      public Dimension data_window = new Dimension(0,0);

  /**
   * The graph canvas this axis is attached to (if it is attached to any)
   * @see graph.Graph2D
   */
      public Graph2D g2d = null;



/*
***********************
** Protected Variables
**********************/
  /**
   * The position in pixels of the minimum point of the axis line
   */
      protected Point amin;

  /**
   * The position in pixels of the maximum point of the axis line
   */
      protected Point amax;

  /**
   * The orientation of the axis. Either Axis.HORIZONTAL or
   * Axis.VERTICAL
   */
      protected int orientation;

  /**
   * The position of the axis. Either Axis.LEFT, Axis.RIGHT, Axis.TOP, or
   * Axis.BOTTOM
   */
      protected int position;

  /**
   * The width of the Axis. Where width for a horizontal axis is really
   * the height
   */
      protected int width = 0;

  /**
   * Textline class to contain the title of the axis.
   */
      protected RTextLine title    = new RTextLine();

  /**
   * Textline class to hold the labels before printing.
   */
      protected RTextLine label    = new RTextLine("0");
  /**
   * Textline class to hold the label's exponent (if it has one).
   */
      protected RTextLine exponent = new RTextLine();
  /**
   * The width of the maximum label. Used to position a Vertical Axis.
   */
      protected int max_label_width     = 0;
  /**
   * Vector containing a list of attached DataSets
   */
      protected Vector dataset = new Vector();
  /**
   * String to contain the labels.
   */
      protected String label_string[]     = null;
  /**
   * The actual values of the axis labels
   */
      protected float  label_value[]      = null;
  /**
   * The starting value of the labels
   */
      protected double label_start        = 0.0;
  /**
   * The increment between labels
   */
      protected double label_step         = 0.0;
  /**
   * The label exponent
   */
      protected int    label_exponent     = 0;
  /**
   * The number of labels required
   */
      protected int    label_count        = 0;
  /**
   * Initial guess for the number of labels required
   */
      protected int    guess_label_number = 4;

  /**
   * If true the axis range must be manually set by setting the
   * Axis.minimum and Axis.maximum variables. The default is false.
   * The default action is for the axis range to be calculated everytime
   * a dataset is attached.
   */
     public boolean manualRange = false;   // changed to public scope by W. Christian

/*
*********************
** Constructors
********************/

  /**
   *  Instantiate the class. The defalt type is a Horizontal axis
   *  positioned at the bottom of the graph.
   */
      public Axis() {
          orientation = HORIZONTAL;
          position    = BOTTOM;
      }

  /**
   * Instantiate the class. Setting the position.
   * @param p Set the axis position. Must be one of Axis.BOTTOM,
   * Axis.TOP, Axis.LEFT, Axis.RIGHT, Axis.HORIZONTAL or Axis.VERTICAL.
   * If one of the latter two are used then Axis.BOTTOM or
   * Axis.LEFT is assumed.
   */
      public Axis(int p) {
           setPosition(p);

           switch (position) {
              case LEFT: case VERTICAL:
                          title.setRotation(90);
                          break;
              case RIGHT:
                          title.setRotation(-90);
                          break;
              default:
                          title.setRotation(0);
                          break;
           }


      }

/*
*******************
** Public Methods
******************/

  /**
   * Set the axis position.
   * @param p Must be one of Axis.BOTTOM,
   * Axis.TOP, Axis.LEFT, Axis.RIGHT, Axis.HORIZONTAL or Axis.VERTICAL.
   * If one of the latter two are used then Axis.BOTTOM or
   * Axis.LEFT is assumed.
   */
      public void setPosition(int p) {
           position = p;

           switch (position) {
              case LEFT:
                          orientation = VERTICAL;
                          break;
              case RIGHT:
                          orientation = VERTICAL;
                          break;

              case TOP:
                          orientation = HORIZONTAL;
                          break;
              case BOTTOM:
                          orientation = HORIZONTAL;
                          break;
              case HORIZONTAL:
                          orientation = HORIZONTAL;
                          position    = BOTTOM;
                          break;
              case VERTICAL:
                          orientation = VERTICAL;
                          position    = LEFT;
                          break;
              default:
                          orientation = HORIZONTAL;
                          position    = BOTTOM;
                          break;
           }
      }

  /**
   * Attach a DataSet for the Axis to manage.
   * @param d dataSet to attach
   * @see graph.DataSet
   */
      public void attachDataSet( DataSet d ) {
            if( orientation == HORIZONTAL )   attachXdata( d );
            else                              attachYdata( d );
      }
  /**
   * Detach an attached DataSet
   * @param d dataSet to detach
   * @see graph.DataSet
   */
      public void detachDataSet( DataSet d ) {
           int i = 0;

           if( d == null ) return;

           if( orientation == HORIZONTAL ) {
               d.xaxis = null;
           } else {
               d.yaxis = null;
           }
           dataset.removeElement(d);

           if(!manualRange && !dataset.isEmpty()) resetRange();
      }
  /**
   * Detach All attached dataSets.
   */
      public void detachAll() {
            int i;
            DataSet d;

            if( dataset.isEmpty() ){
              if(!manualRange){  // if added by W. Christian
                 minimum = 0.0;
                 maximum = 1.0;
              }
              return;
            }


            if( orientation == HORIZONTAL ) {
                 for (i=0; i<dataset.size(); i++) {
                     d = (DataSet)(dataset.elementAt(i));
                     d.xaxis = null;
                 }
            } else {
                 for (i=0; i<dataset.size(); i++) {
                     d = (DataSet)(dataset.elementAt(i));
                     d.yaxis = null;
                 }
            }

            dataset.removeAllElements();
            if(!manualRange)  // if added by W. Christian
            {
              minimum = 0.0;
              maximum = 1.0;
            }
      }
  /**
   * Return the minimum value of All datasets attached to the axis.
   * @return Data minimum
   */
      public double getDataMin() {
            double m;
            Enumeration e;
            DataSet d;

            if( dataset.isEmpty() ) return 0.0;

            //d = (DataSet)(dataset.firstElement());
           // if(d == null) return 0.0;
            m=Double.MAX_VALUE; // aded by W. Christian
            if( orientation == HORIZONTAL ) {
                 //m = d.getXmin();
                 for (e = dataset.elements() ; e.hasMoreElements() ;) {
                     d = (DataSet)e.nextElement();
                     if(d.length>0) m =  Math.min( d.getXmin(),m );
                 }
            } else {
                 //m = d.getYmin();
                 for (e = dataset.elements() ; e.hasMoreElements() ;) {

                     d = (DataSet)e.nextElement();
                     if(d.length>0) m = Math.min(d.getYmin(),m);

                 }
            }

            return m;
      }
  /**
   * Return the maximum value of All datasets attached to the axis.
   * @return Data maximum
   */
      public double getDataMax() {
            double m;
            Enumeration e;
            DataSet d;

            if( dataset.isEmpty() ) return 1.0;

            //d = (DataSet)(dataset.firstElement());

            //if(d == null) return 0.0;
            m=-Double.MAX_VALUE; // aded by W. Christian

            if( orientation == HORIZONTAL ) {
                // m = d.getXmax();
                 for (e = dataset.elements() ; e.hasMoreElements() ;) {

                     d = (DataSet)e.nextElement();
                     if(d.length>0) m = Math.max(d.getXmax(),m);
                 }
            } else {
                 //m = d.getYmax();
                 for (e = dataset.elements() ; e.hasMoreElements() ;) {

                     d = (DataSet)e.nextElement();

                     if(d.length>0) m = Math.max(d.getYmax(),m);
                 }
            }

            return m;
      }
  /**
   * Return the pixel equivalent of the passed data value. Using the
   * position of the axis and the maximum and minimum values convert
   * the data value into a pixel value
   * @param v data value to convert
   * @return equivalent pixel value
   * @see graph.Axis#getDouble( )
   */
      public int getInteger(double v) {
          double scale;
          int pix=0;  //added by W. Christian to remove overflow.
          if(amax==null || amin==null ) return 0; // added by wc.
          if(maximum==minimum) return (amax.x - amin.x)/2; // added by wc.
          if( orientation == HORIZONTAL ) {
               scale  = (double)(amax.x - amin.x)/(maximum - minimum);
               pix=(int)(( v-minimum ) * scale);       // W. Christian added 1/2 pixel offset to round properly.
               pix=Math.max(pix,-100000);                  // make sure pix is not too small
               pix=Math.min(pix, 100000);                  // make sure pix is not too large
               return amin.x + (int)pix;
          } else {
               scale  = (double)(amax.y - amin.y)/(maximum - minimum);
               pix=(int)((v-minimum)*scale );    // W. Christian added 1/2 pixel offset to round properly.
               pix=Math.max(pix,-100000);
               pix=Math.min(pix, 100000);
               return amax.y - (int)pix;
          }

      }

  /**
   * Return the data value equivalent of the passed pixel position.
   * Using the
   * position of the axis and the maximum and minimum values convert
   * the pixel position into a data value
   * @param i pixel value
   * @return equivalent data value
   * @see graph.Axis#getInteger( )
   */
      public double getDouble(int i) {
            double scale;
          if(amax==null || amin==null ) return (maximum+minimum)/2.0; // added by wc.
          if( orientation == HORIZONTAL ) {
               scale  = (maximum - minimum)/(double)(amax.x - amin.x);
               return minimum + (i - amin.x)*scale;
          } else {
               scale  = (maximum - minimum)/(double)(amax.y - amin.y);
               return maximum - (i - amin.y)*scale;
          }
      }

  /**
   * Reset the range of the axis (the minimum and maximum values) to the
   * default data values.
   */
     public void resetRange() {
             if(manualRange || dataset.isEmpty()){
                if(rangeBounded){ // added by W. Christian to restrict range range to some minimum
                  if(minimum>rangeMinimum) minimum=rangeMinimum;
                  if(maximum<rangeMaximum) maximum=rangeMaximum;
                }
                if(minimum>=maximum)maximum=minimum+1.0;
                return;
             }
             minimum = getDataMin();
             maximum = getDataMax();
             // following code added by W. Christian to handle constant values.
             if(minimum==Double.MAX_VALUE || maximum==Double.MIN_VALUE){
               minimum=0;
               maximum=1;
             }
             if (minimum==maximum)
             {
                if( dataset.isEmpty() )
                {
                    minimum=0.0;
                    maximum=1.0;
                }
                else if (maximum <20)
                {
                    minimum-=1.0;
                    maximum+=1.0;
                }
                else
                {
                    minimum-=0.1*maximum;
                    maximum+=0.1*maximum;
                }
             }
             // make a scale a bit bigger than the actual data.
             double range=maximum-minimum;
             double absMax=Math.max(Math.abs(maximum),Math.abs(minimum));
             if((absMax>0.1) && (range/absMax<1.0e-4))range=1.0e-4; // added by W. Christian to give a force a range if change in variable is small..
             if(rangeBounded){ // added by W. Christian to restrict range range to some minimum
                  if(minimum>rangeMinimum) minimum=rangeMinimum;
                  if(maximum<rangeMaximum) maximum=rangeMaximum;
                  minimum=minimum-0.05*(rangeMaximum-rangeMinimum);
                  maximum=maximum+0.05*(rangeMaximum-rangeMinimum);
             }else{
                minimum=minimum-0.05*range;
                maximum=maximum+0.05*range;
             }
     }

  /**
   * Return the position of the Axis.
   * @return One of Axis.LEFT, Axis.RIGHT, Axis.TOP, or Axis.BOTTOM.
   */
     public int getAxisPos() { return position; }
  /**
   * If the Axis is Vertical return <i>true</i>.
   */
     public boolean isVertical() {
          if( orientation == HORIZONTAL ) return false;
          else                            return true;
     }
  /**
   * Return the width of the axis.
   * @param g graphics context.
   */
     public int getAxisWidth(Graphics g) {
          int i;
          width = 0;
          if( minimum == maximum )  resetRange();   // added by WC
          if( minimum == maximum )    return 0;     // something is wrong.  Maybe manual range is set?
          //if( dataset.size() == 0 )   return 0;   //  line removed by wc. We should have a default range.


          calculateGridLabels();


          exponent.setText(null);

          if(label_exponent != 0) {
              exponent.copyState(label);
              exponent.setText("x10^"+String.valueOf(label_exponent));
          }

           if( orientation == HORIZONTAL ) {

               width = label.getRHeight(g) + label.getLeading(g);
               width += Math.max(title.getRHeight(g),exponent.getRHeight(g));

           } else {
               for(i=0; i<label_string.length; i++) {
                  label.setText(" "+label_string[i]);
                  width = Math.max(label.getRWidth(g),width);
               }
               max_label_width = width;
               width = 0;

               if(!title.isNull() ) {
                  width = Math.max(width,title.getRWidth(g)+
                                         title.charWidth(g,' '));
               }

               if ( !exponent.isNull() ) {
                  width = Math.max(width,exponent.getRWidth(g)+
                                         exponent.charWidth(g,' '));
               }
               width += max_label_width;
           }


           return width;
      }
  /**
   * Position the axis at the passed coordinates. The coordinates should match
   * the type of axis.
   * @param xmin The minimum X pixel
   * @param xmax The maximum X pixel. These should be equal if the axis
   *         is vertical
   * @param ymin The minimum Y pixel
   * @param ymax The maximum Y pixel. These should be equal if the axis
   *         is horizontal
   * @return <i>true</i> if there are no inconsistencies.
   */
      public boolean positionAxis(int xmin, int xmax, int ymin, int ymax ){
           amin = null;
           amax = null;

           if( orientation == HORIZONTAL && ymin != ymax ) return false;
           if( orientation == VERTICAL   && xmin != xmax ) return false;

           amin = new Point(xmin,ymin);
           amax = new Point(xmax,ymax);


           return true;
      }

  /**
   * Draw the axis using the passed Graphics context.
   * @param g Graphics context for drawing
   */
      public void drawAxis(Graphics g) {
         try{  // try added by W. Christian to catch null pointers if the labels change.
          Graphics lg;

          if( !redraw            ) return;
          if( minimum == maximum ) {
                 //System.out.println("Axis: data minimum==maximum Trying to reset range! maximum= "+ maximum);
                 resetRange();
                 if( minimum == maximum ) {
                    //System.out.println("Axis: Reseting Range failed!  Axis not drawn!");
                    return;
                  }
          }
          if( amin.equals(amax) ) return;
          if( width == 0 ) width = getAxisWidth(g);

          lg = g.create();

          if(( force_end_labels )&&(!manualRange)) {
              minimum = label_start;
              maximum = minimum + (label_count-1)*label_step;
          }

          /*
          ** For rotated text set the Component that is being drawn into
          */
          if(title!=null) title.setDrawingComponent(g2d);      // check for null added by W. Christian
          if(label!=null) label.setDrawingComponent(g2d);
          if(exponent!=null)exponent.setDrawingComponent(g2d);


          if( orientation == HORIZONTAL) {
               drawHAxis(lg);
          } else {
               drawVAxis(lg);
          }
          lg.dispose();    // added by W. Christian to release system resources.
      } catch (Exception e) {;}

     }
  /**
   * Set the title of the axis
   * @param s string containing text.
   */
     public void setTitleText(String s) {   title.setText(s); }

  /**
   * Set the color of the title
   * @param c Color of the title.
   */
     public void setTitleBackground(Color c) {   title.setBackground(c); }

  /**
   * Set the color of the title
   * @param c Color of the title.
   */
     public void setTitleColor(Color c) {   title.setColor(c); }

  /**
   * Set the font of the title
   * @param c Title font.
   */
     public void setTitleFont(Font f)   {   title.setFont(f); }

  /**
   * Set the title rotation angle. Only multiples of 90 degrees allowed.
   * @param a Title rotation angle in degrees.
   */
     public void setTitleRotation(int a)   {   title.setRotation(a); }

  /**
   * Set the color of the labels
   * @param c Color of the labels.
   */
     public void setLabelColor(Color c) {   label.setColor(c); }

  /**
   * Set the font of the labels.
   * @param f font.
   */
     public void setLabelFont(Font f)   {   label.setFont(f); }

  /**
   * Set the color of the exponent
   * @param c Color.
   */
     public void setExponentColor(Color c) {   exponent.setColor(c); }

  /**
   * Set the font of the exponent
   * @param f font.
   */
     public void setExponentFont(Font f)   {   exponent.setFont(f); }

  /**
   * Is the range of the axis to be set automatically (based on the data)
   * or manually by setting the values Axis.minimum and Axis.maximum?
   * @param b boolean value.
   */
     public boolean isManualRange(){return manualRange;}

     public void setManualRange(boolean b)
       {
        manualRange = b;
        resetRange();
        if(manualRange && maximum==minimum){
            maximum=minimum+1.0e-6;
        }
        calculateGridLabels();
       }
     public void setManualRange(boolean b, double min, double max)
       {
        manualRange = b;
        minimum=min;
        maximum=max;
        resetRange();            // added by W. Christian
        calculateGridLabels();  //added by W. Christian
       }



/*
*********************
** Protected Methods
********************/

  /**
   * Draw a Horizontal Axis.
   * @param g Graphics context.
   */

     protected void drawHAxis(Graphics g) {
          Graphics lg;
          int i;
          int j;
          int x0,y0,x1,y1;
          int direction;
          int offset;
          double minor_step;

          Color c;

          //double vmin = minimum*1.001;
          //double vmax = maximum*1.001;
          // changed by W. Christian
          double range=maximum-minimum;
          double vmin = minimum-range*0.0001;
          double vmax = maximum+range*0.0001;

          double scale  = (amax.x - amin.x)/(maximum - minimum);
          double val;
          double minor;

//          System.out.println("Drawing Horizontal Axis!");


          if( axiscolor != null) g.setColor(axiscolor);

          if(g2d.showAxis) g.drawLine(amin.x,amin.y,amax.x,amax.y);

          if(position == TOP )     direction =  1;
          else                     direction = -1;

          minor_step = label_step/(minor_tic_count+1);
          val = label_start;
          for(i=0; i<label_count; i++) {
              if( val >= vmin && val <= vmax ) {
                 y0 = amin.y;
                 x0 = amin.x + (int)( ( val - minimum ) * scale);
                 if( Math.abs(label_value[i]) <= 0.0001 && drawzero ) {
                      c = g.getColor();
                      if(zerocolor != null) g.setColor(zerocolor);
                      if(g2d.showAxis) g.drawLine(x0,y0,x0,y0+data_window.height*direction);
                      g.setColor(c);

                 } else if( drawgrid ) {
                      c = g.getColor();
                      if(gridcolor != null) g.setColor(gridcolor);
                      g.drawLine(x0,y0,x0,y0+data_window.height*direction);
                      g.setColor(c);
                 }
                 x1 = x0;
                 y1 = y0 + major_tic_size*direction;
                 if(g2d.showAxis) g.drawLine(x0,y0,x1,y1);
              }

              minor = val + minor_step;
              for(j=0; j<minor_tic_count; j++) {
                 if( minor >= vmin && minor <= vmax ) {
                    y0 = amin.y;
                    x0 = amin.x + (int)( ( minor - minimum ) * scale);
                    if( drawgrid ) {
                      c = g.getColor();
                      if(gridcolor != null) g.setColor(gridcolor);
                      if(g2d.showAxis) g.drawLine(x0,y0,x0,y0+data_window.height*direction);
                      g.setColor(c);
                    }
                    x1 = x0;
                    y1 = y0 + minor_tic_size*direction;
                    if(g2d.showAxis) g.drawLine(x0,y0,x1,y1);
                 }
                minor += minor_step;
              }

              val += label_step;
          }


          if(position == TOP ) {
             offset = - label.getLeading(g) - label.getDescent(g);
          } else {
             offset = + label.getLeading(g) + label.getAscent(g);
          }


          val = label_start;
          for(i=0; i<label_count; i++) {
              if( val >= vmin && val <= vmax ) {
                 y0 = amin.y + offset;
                 x0 = amin.x + (int)(( val - minimum ) * scale);
                 label.setText(label_string[i]);
                 label.draw(g,x0,y0,TextLine.CENTER);
              }
              val += label_step;
          }


          if( !exponent.isNull() ) {
             x0 = amax.x;
             if(position == TOP ) {
                y0 = amin.y - label.getLeading(g)
                            - label.getDescent(g)
                            - exponent.getLeading(g)
                            - exponent.getDescent(g);
                //y0 = y0;  //added by W. Christian
                //x0 = x0;  // added by W. Christian
             } else {   // Bottom postion for Horz axis
                y0 = amax.y + label.getLeading(g)
                            + label.getAscent(g)
                            + exponent.getLeading(g)
                            + exponent.getAscent(g);
                //y0 = amax.y-10;  //added by W. Christian
                x0 = x0-25;  // added by W. Christian to shift the exponent to the left
             }
             exponent.draw(g,x0,y0,TextLine.LEFT);

          }

          if( !title.isNull() ) {
             if(position == TOP ) {
                y0 = amin.y - label.getLeading(g)
                            - label.getDescent(g)
                            - title.getLeading(g)
                            - title.getDescent(g);
             } else {
                y0 = amax.y + label.getLeading(g)
                            + label.getAscent(g)
                            + title.getLeading(g)
                            + title.getAscent(g);
             }

              x0 = amin.x + ( amax.x - amin.x)/2;

             // g.setColor(Color.black);  // added by W. Christian
              title.draw(g,x0,y0,TextLine.CENTER);

          }

     }

  /**
   * Draw a Vertical Axis.
   * @param g Graphics context.
   */

     protected void drawVAxis(Graphics g) {
          Graphics lg;
          int i;
          int j;
          int x0,y0,x1,y1;
          int direction;
          int offset = 0;
          double minor_step;
          double minor;
          Color c;

          FontMetrics fm;
          Color gc = g.getColor();
          Font  gf = g.getFont();
          double range=maximum-minimum;
          // range added by W. Christian
          //double vmin = minimum*1.001;
          //double vmax = maximum*1.001;
          double vmin = minimum-range*0.0001;
          double vmax = maximum+range*0.0001;

          double scale  = (amax.y - amin.y)/(maximum - minimum);
          double val;

//          System.out.println("Drawing Vertical Axis!");


          if( axiscolor != null) g.setColor(axiscolor);

          if(g2d.showAxis) g.drawLine(amin.x,amin.y,amax.x,amax.y);

          if(position == RIGHT )     direction = -1;
          else                       direction =  1;

          minor_step = label_step/(minor_tic_count+1);
          val = label_start;
          for(i=0; i<label_count; i++) {
              if( val >= vmin && val <= vmax ) {
                 x0 = amin.x;
                 y0 = amax.y - (int)( ( val - minimum ) * scale);
                 if( Math.abs(label_value[i]) <= 0.0001 && drawzero ) {
                      c = g.getColor();
                      if(zerocolor != null) g.setColor(zerocolor);
                      if(g2d.showAxis) g.drawLine(x0,y0,x0+data_window.width*direction,y0);
                      g.setColor(c);
                 } else
                 if( drawgrid ) {
                      c = g.getColor();
                      if(gridcolor != null) g.setColor(gridcolor);
                      g.drawLine(x0,y0,x0+data_window.width*direction,y0);
                      g.setColor(c);
                 }
                 x1 = x0 + major_tic_size*direction;
                 y1 = y0;
                 if(g2d.showAxis) g.drawLine(x0,y0,x1,y1);
              }

              minor = val + minor_step;
              for(j=0; j<minor_tic_count; j++) {
                 if( minor >= vmin && minor <= vmax ) {
                    x0 = amin.x;
                    y0 = amax.y - (int)( ( minor - minimum ) * scale);
                    if( drawgrid ) {
                      c = g.getColor();
                      if(gridcolor != null) g.setColor(gridcolor);
                      if(g2d.showAxis) g.drawLine(x0,y0,x0+ data_window.width*direction,y0);
                      g.setColor(c);
                    }
                    x1 = x0 + minor_tic_size*direction;
                    y1 = y0;
                    if(g2d.showAxis) g.drawLine(x0,y0,x1,y1);
                 }
                minor += minor_step;
              }
              val += label_step;
          }


          val = label_start;
          for(i=0; i<label_count; i++) {
              if( val >= vmin && val <= vmax ) {
                 x0 = amin.x + offset;
                 y0 = amax.y - (int)(( val - minimum ) * scale) +
                             label.getAscent(g)/2;

                 if(position == RIGHT ) {
                    label.setText(" "+label_string[i]);
                    label.draw(g,x0,y0,TextLine.LEFT);
                 } else {
                    label.setText(label_string[i]+" ");
                    label.draw(g,x0,y0,TextLine.RIGHT);
                  }
              }
              val += label_step;
          }



          if( !exponent.isNull() ) {

             y0 = amin.y;

             if(position == RIGHT ) {
                 x0  =  amin.x + max_label_width + exponent.charWidth(g,' ');
                 exponent.draw(g,x0,y0,TextLine.LEFT);
             } else {  // Left for Vertical axis
                 x0 =  amin.x - max_label_width - exponent.charWidth(g,' ');
                 y0 = y0+5; // added by W. Christian  to move V axis down
                 x0 = x0+3; //added by W.Christian to shift the exponent right
                 exponent.draw(g,x0,y0,TextLine.RIGHT);
             }

          }

          if( !title.isNull() ) {

             y0 = amin.y + (amax.y-amin.y)/2;

             if( title.getRotation() == 0 || title.getRotation() == 180 ) {
               if(position == RIGHT ) {
                    x0 =  amin.x + max_label_width + title.charWidth(g,' ');
                    title.draw(g,x0,y0,TextLine.LEFT);
               } else {
                    x0 =  amin.x - max_label_width - title.charWidth(g,' ');
                    title.draw(g,x0,y0,TextLine.RIGHT);
               }
             } else {
               title.setJustification(TextLine.CENTER);
               if(position == RIGHT ) {
                    x0 =  amin.x + max_label_width - title.getLeftEdge(g)+
                          + title.charWidth(g,' ');
               } else {
                    x0 =  amin.x - max_label_width - title.getRightEdge(g)
                              - title.charWidth(g,' ');
               }
              // g.setColor(Color.black);  // added by W. Christian
               title.draw(g,x0,y0);
             }

          }


 }






  /**
   * Attach a DataSet to a Horizontal Axis
   * @param d dataset to attach.
   */
      protected void attachXdata( DataSet d ) {

            dataset.addElement(d);
            d.xaxis = this;
            if (manualRange) return;  // added by W. Christian
            if( dataset.size() == 1 ) {
                  minimum = d.dxmin;
                  maximum = d.dxmax;
            } else {
               if(minimum > d.dxmin) minimum = d.dxmin;
               if(maximum < d.dxmax) maximum = d.dxmax;
            }

      }

  /**
   * Attach a DataSet to a Vertical Axis
   * @param d dataset to attach.
   */
      protected void attachYdata( DataSet d ) {

            dataset.addElement(d);
            d.yaxis = this;
            if (manualRange) return;  // added by W. Christian
            if( dataset.size() == 1 ) {
                  minimum = d.dymin;
                  maximum = d.dymax;
            } else {
               if(minimum > d.dymin) minimum = d.dymin;
               if(maximum < d.dymax) maximum = d.dymax;
            }

      }


  /**
   * calculate the labels
   */
   // access changed from protected to public by Wolfgang Christian
       public void calculateGridLabels() {
        double val;
        int i;
        int j;

        if(Math.abs(minimum)==0 && Math.abs(maximum)==0){
            maximum=minimum+1.0e-6;
        }
        if (Math.abs(minimum) > Math.abs(maximum) )
         label_exponent = ((int)Math.floor(
                       SpecialFunction.log10(Math.abs(minimum))/3.0) )*3;
        else
         label_exponent = ((int)Math.floor(
                       SpecialFunction.log10(Math.abs(maximum))/3.0) )*3;

        label_step = RoundUp( (maximum-minimum)/guess_label_number );
        label_start = Math.floor( minimum/label_step )*label_step;

        val = label_start;
        label_count = 1;
        while(val < maximum) { val += label_step; label_count++; }

        label_string = new String[label_count];
        label_value  = new float[label_count];


      //System.out.println("label_step="+label_step);
     //System.out.println("label_start="+label_start);
     //System.out.println("label_count="+label_count);
      //System.out.println("label_exponent"+label_exponent);


        for(i=0; i<label_count; i++) {
            val = label_start + i*label_step;

            if( label_exponent< 0 ) {
                  for(j=label_exponent; j<0;j++) { val *= 10; }
            } else {
                  for(j=0; j<label_exponent;j++) { val /= 10; }
            }

            //label_string[i] = String.valueOf(val);    //old code
            // format added by W. Christian
            label_string[i] = labelFormat.form(val);
            label_value[i] = (float)val;
        }

      }

/*
*******************
** Private Methods
******************/

  /**
   * Round up the passed value to a NICE value.
   */

      private double RoundUp( double val ) {
          int exponent;
          int i;

          exponent = (int)(Math.floor( SpecialFunction.log10(val) ) );

          if( exponent < 0 ) {
             for(i=exponent; i<0; i++) { val *= 10.0; }
          } else {
             for(i=0; i<exponent; i++) { val /= 10.0; }
          }

          if( val > 5.0 )     val = 10.0;
          else
          if( val > 2.0 )     val = 5.0;
          else
          if( val > 1.0 )     val = 2.0;
          else
                              val = 1.0;

          if( exponent < 0 ) {
             for(i=exponent; i<0; i++) { val /= 10.0; }
          } else {
             for(i=0; i<exponent; i++) { val *= 10.0; }
          }

          return val;

      }

}


