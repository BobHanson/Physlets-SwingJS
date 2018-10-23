package edu.davidson.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


//import java.lang.*;


/*
**************************************************************************
**
**    Class  DataSet
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
**    the Graph2D class and Axis class for plotting 2D graphs.
**
*************************************************************************/


/**
 *  This class is designed to hold the data to be plotted.
 *  It is to be used in conjunction with the Graph2D class and Axis
 *  class for plotting 2D graphs.
 *
 * @version $Revision: 1.15 $, $Date: 1996/09/24 05:23:41 $
 * @author Leigh Brookshaw
 */
public class DataSet extends Object {



/*
***************************
** Public Static Values
**************************/
/**
 *    A constant value flag used to specify no straight line segment
 *    is to join the data points
 */
      public final static int NOLINE    =  0;
/**
 *    A constant value flag used to specify that a straight line segment
 *    is to join the data points.
 */
      public final static int LINE      =  1;

/**
 *    The maximum number of points*stride that a user can have in a dataset.
 */
      public final static int MAX_LENGTH      =  20000;

/*
***********************
** Public Variables
**********************/

  /**
   *    The Graphics canvas that is driving the whole show.
   * @see graph.Graph2D
   */
      public Graph2D g2d;

  /**
   *    The linestyle to employ when joining the data points with
   *    straight line segments. Currently only solid and no line
   *    are supported.
   */
      public int   linestyle     = LINE;
  /**
   *    The color of the straight line segments
   */
      public Color linecolor     = null;
  /**
   *    The index of the marker to use at the data points.
   * @see graph.Markers
   */
      public int    marker       = 0;
  /**
   *    The marker color
   */
      public Color  markercolor  = null;
  /**
   *    The scaling factor for the marker. Default value is 1.
   */
      public double markerscale  = 1.0;
  /**
   *    The Axis object the X data is attached to. From the Axis object
   *    the scaling for the data can be derived.
   * @see graph.Axis
   */
      public Axis xaxis;
  /**
   *    The Axis object the Y data is attached to.
   * @see graph.Axis
   */
      public Axis yaxis;
  /**
   * The current plottable X maximum of the data.
   * This can be very different from
   * true data X maximum. The data is clipped when plotted.
   */
      public double xmax;
  /**
   * The current plottable X minimum of the data.
   * This can be very different from
   * true data X minimum. The data is clipped when plotted.
   */
      public double xmin;
  /**
   * The current plottable Y maximum of the data.
   * This can be very different from
   * true data Y maximum. The data is clipped when plotted.
   */
      public double ymax;
  /**
   * The current plottable Y minimum of the data.
   * This can be very different from
   * true data Y minimum. The data is clipped when plotted.
   */
      public double ymin;
  /**
   * Boolean to control clipping of the data window.
   * Default value is <em>true</em>, clip the data window.
   */
      public boolean clipping = true;


/*
*********************
** Protected Variables
**********************/

  /**
   * Sort the data in ascending order.
   *
   * added by W. Christian
   */
      protected boolean sorted=false;
      protected boolean stripChart=false;
      protected int chartPts=0;
      public void setSorted(boolean s){sorted=s; if(sorted) insertionSort();}
      public boolean isSorted(){return sorted;}
      public void setStripChart(int pts, boolean chart){
        stripChart=chart;
        chartPts=Math.max(pts,1);
        if(stripChart) chartPoints();
      }
      public boolean isStripChart(){return stripChart;}
      private double[] tempDatum = new double[2];

  /**
   * KeepTrack of the last datum.
   *
   * added by W. Christian
   */
      public double[]  getLastPoint(){
          return lastPoint;
      }
      private double[] lastPoint = new double[2];


  /**
   * The data X maximum.
   * Once the data is loaded this will never change.
   */
      protected double dxmax;
  /**
   * The data X minimum.
   * Once the data is loaded this will never change.
   */
      protected double dxmin;
  /**
   * The data Y maximum.
   * Once the data is loaded this will never change.
   */
      protected double dymax;
  /**
   * The data Y minimum.
   * Once the data is loaded this will never change.
   */
      protected double dymin;

  /**
   * The array containing the actual data
   */
      protected double data[];
  /**
   * The number of data points stored in the data array
   */
      protected int length;
  /**
   *    The X range of the clipped data
   */
      protected double xrange;
  /**
   *    The Y range of the clipped data
   */
      protected double yrange;

  /**
   *    The length of the example line in the data legend.
   */
      protected int legend_length = 20;

  /**
   *    The legend text
   */
      protected TextLine legend_text = null;
  /**
   * The X pixel position of the data legend
   */
      protected int legend_ix;
  /**
   * The Y pixel position of the data legend
   */
      protected int legend_iy;
  /**
   * The X data position of the data legend
   */
      protected double legend_dx;
  /**
   * The Y data position of the data legend
   */
      protected double legend_dy;
  /**
   *    The amount to increment the data array when the append method is being
   *    used.
   */
      protected int increment = 100;


  /**
   * The stride of the data. For data pairs (x,y) the stride is 2
   */
    protected int stride = 2;

/*
*********************
** Constructors
********************/

  /**
   *  Instantiate an empty data set.
   */
      public DataSet ( ) {
               length = 0;
               data = null;
               lastPoint = new double[stride];
               range(stride);
      }
  /**
   *  Instantiate an empty data set.
   *  @param stride the stride of the data set. The default stride is 2.
   */
      public DataSet (int stride ) throws Exception {
               if( stride < 2 ) throw
                          new Exception("Invalid stride parameter!");
               this.stride = stride;
               lastPoint = new double[stride];
               length = 0;
               lastPoint = new double[stride];
               range(stride);
      }

  /**
   * Instantiate a DataSet with the parsed data. Default stride is 2.
   * The double array contains the data. The X data is expected in
   * the even indices, the y data in the odd. The integer n is the
   * number of data Points. This means that the length of the data
   * array is 2*n.
   * @param d Array containing the (x,y) data pairs.
   * @param n Number of (x,y) data pairs in the array.
   * @exception  Exception
   *            A Generic exception if it fails to load the
   *            parsed array into the class.
   */
      public DataSet ( double d[], int n ) throws Exception {
           int i;
           int k = 0;

           length = 0;

           if ( d  == null || d.length == 0 || n <= 0 ) {
              throw new Exception("DataSet: Error in parsed data!");
           }

           if(d.length>MAX_LENGTH || n>MAX_LENGTH ){
               System.out.println("Error: DataSet passed to constructor has too many points.  Max Pointst="+length/stride);
               return;
           }

//     Copy the data locally.
           lastPoint = new double[stride];
           data = new double[n*stride];
           length = n*stride;

           System.arraycopy(d, 0, data, 0, length);

           System.arraycopy(d,length-stride , lastPoint, 0, stride);  // keep track of the last point
           // sort the data.  Added by W. Christian
           if(sorted) insertionSort();
           if(stripChart) chartPoints();


//     Calculate the data range.

           range(stride);


      }

  /**
   * Instantiate a DataSet with the parsed data.
   * The double array contains the data. The X data is expected to be in
   * indices i*stride where i=0,1,... The Y data is expected to be found
   * in indices i*stride+1 where i=0,1,2...
   * The integer n is the
   * number of data Points. This means that the length of the data
   * array is 2*stride.
   * @param d Array containing the (x,y) data pairs.
   * @param n Number of (x,y) data pairs in the array.
   * @param s The stride of the data.
   * @exception  Exception
   *            A Generic exception if it fails to load the
   *            parsed array into the class.
   */
      public DataSet ( double d[], int n, int s ) throws Exception {
           if( s < 2 ) throw
                          new Exception("Invalid stride parameter!");
           int i;
           int k = 0;

           length = 0;

           if ( d  == null || d.length == 0 || n <= 0 ) {
              throw new Exception("DataSet: Error in parsed data!");
           }

           if(d.length>MAX_LENGTH || n>MAX_LENGTH ){
               System.out.println("Error: DataSet passed to constructor has too many points.  Max Pointst="+length/stride);
               return;
           }

           this.stride = s;
           lastPoint = new double[stride];

//     Copy the data locally.

           data = new double[n*stride];
           length = n*stride;

           System.arraycopy(d, 0, data, 0, length);

           System.arraycopy(d,length-stride , lastPoint, 0, stride);  // keep track of the last point
           // sort the data.  Added by W. Christian
           if(sorted) insertionSort();
           if(stripChart) chartPoints();


//     Calculate the data range.

           range(stride);
      }
/*
*******************
** Public Methods
******************/

   /**
   * Replace data in a data set.
   * @param d Array containing (x,y) pairs to replace existing data.
   * @param n Number of (x,y) data pairs in the array.
   * @exception Exception
   *          A generic exception if it fails to load the
   *            parsed array into the class.
   */
      public synchronized void replace( double d[], int n ) throws Exception {
           if(n*stride>MAX_LENGTH){
               System.out.println("Error: DataSet has too many points.  Max Pointst="+length/stride);
               return;
           }
           if ( d  == null || d.length == 0 || n <= 0 ) {
              throw new Exception("DataSet: Error in replace data!");
           }
           if(data==null || data.length!=d.length){  // if we have no exisiting data or the lengths do not match
               length = 0;
               data = null;
               append(  d,  n );
               return;
           }
           System.arraycopy(d, 0, data, 0, length);
           System.arraycopy(d,length-stride , lastPoint, 0, stride);  // keep track of the last point
           // sort the data.  Added by W. Christian
           if(sorted) insertionSort();
           if(stripChart) chartPoints();
           range(stride);
           if(xaxis != null) xaxis.resetRange();
           if(yaxis != null) yaxis.resetRange();
           return;
      }


  /**
   * Append data to the data set.
   * @param d Array containing (x,y) pairs to append
   * @param n Number of (x,y) data pairs in the array.
   * @exception Exception
   *          A generic exception if it fails to load the
   *            parsed array into the class.
   */
      public synchronized void append( double d[], int n ) throws Exception {
           if(n*stride>MAX_LENGTH){
               System.out.println("Error: DataSet has too many points.  Max Pointst="+length/stride);
               return;
           }
           int i;
           int k = 0;
           double tmp[];
           int ln = n*stride;

           if ( d  == null || d.length == 0 || n <= 0 ) {
              throw new Exception("DataSet: Error in append data!");
           }

          // if(data == null) data = new double[increment];   changed by W. Chrstian to mirror constructor
         if(data == null){ // wc version
             data = new double[n*stride];
             length = n*stride;
             System.arraycopy(d, 0, data, 0, length);
             // sort the data.  Added by W. Christian
             if(sorted) insertionSort();
             if(stripChart) chartPoints();
             range(stride);
             if(xaxis != null) xaxis.resetRange();
             if(yaxis != null) yaxis.resetRange();
             System.arraycopy(d,ln-stride , lastPoint, 0, stride);  // keep track of the last point
             return;
         }

//     Copy the data locally.


           if( ln+length < data.length ) {
               System.arraycopy(d, 0, data, length, ln);
               length += ln;
           } else {
               tmp = new double[ln+length+increment];

               if( length != 0 ) {
                 System.arraycopy(data, 0, tmp, 0, length);
               }
               System.arraycopy(d, 0, tmp, length, ln);
               length += ln;
               data = tmp;
             }
// sort the data.  Added by W. Christian
           if(sorted && ln==stride) insertDatum(length-ln); // only one datum was added.
              else if(sorted)insertionSort();// do a complete sort
           if(stripChart) chartPoints();
           System.arraycopy(d,ln-stride , lastPoint, 0, stride);  // keep track of the last point

//     Calculate the data range.

           if( stripChart || n>1 || stride!=2 || length<6 ) range(stride);   // the old calculations
           else{  // appending a single point.  no need to check the range for all points. added for speed. Wolfgnag Christian
             if( dxmax < d[0] )   { dxmax = d[0]; }
             else if( dxmin > d[0] )   { dxmin = d[0]; }

             if( dymax < d[1] ) { dymax = d[1]; }
             else if( dymin > d[1] ) { dymin = d[1]; }

             if( xaxis == null) {
              xmin = dxmin;
              xmax = dxmax;
             }
             if( yaxis == null) {
              ymin = dymin;
              ymax = dymax;
             }
           }
//     Update the range on Axis that this data is attached to
           if(xaxis != null) xaxis.resetRange();
           if(yaxis != null) yaxis.resetRange();
      }
  /**
   * Delete data from the data set (start and end are inclusive).
   * The first (x,y) pair in the data set start at index 0.
   * @param start The start (x,y) pair index.
   * @param end   The end (x,y) pair index.
   */
      public synchronized void  delete( int start, int end ) {
           int End   = stride*end;
           int Start = stride*start;

           if(length <= 0) return;

           if( End   < Start )         return;
           if( Start < 0 )             Start = 0;
           if( End > length-stride )        End = length-stride;

           if( End < length-stride) {
               System.arraycopy(data, End+stride,
                                data, Start, length - End - stride);
             }

           length -= End+stride-Start;


//     Calculate the data range.

           range(stride);
           if(xaxis != null) xaxis.resetRange();
           if(yaxis != null) yaxis.resetRange();


      }
  /**
   * Delete all the data from the data set.
   */
      public synchronized void  deleteData( ) {
           length = 0;
           data = null;
           range(stride);
           if(xaxis != null) xaxis.resetRange();
           if(yaxis != null) yaxis.resetRange();
      }

  /**
   * Draw the straight line segments and/or the markers at the
   * data points.
   * If this data has been attached to an Axis then scale the data
   * based on the axis maximum/minimum otherwise scale using
   * the data's maximum/minimum
   * @param g Graphics state
   * @param bounds The data window to draw into
   */
      public synchronized void draw_data(Graphics g, Rectangle bounds) {
           Color c;

           if ( xaxis != null ) {
                xmax = xaxis.maximum;
                xmin = xaxis.minimum;
           }

           if ( yaxis != null ) {
                ymax = yaxis.maximum;
                ymin = yaxis.minimum;
           }


           xrange = xmax - xmin;
           yrange = ymax - ymin;

           /*
           ** draw the legend before we clip the data window
           */
           draw_legend(g,bounds);
           /*
           ** Clip the data window
           */
           if(clipping) g.clipRect(bounds.x, bounds.y,
                                   bounds.width, bounds.height);

           c = g.getColor();

           if( linestyle != DataSet.NOLINE) {
               if ( linecolor != null) g.setColor(linecolor);
               else                    g.setColor(c);
               draw_lines(g,bounds);
           }

           if( linestyle == DataSet.NOLINE  && marker==0){
               if(markercolor != null) g.setColor(markercolor);
               else                    g.setColor(c);
               draw_dots(g,bounds);
           }

           if( marker > 0 ) {
               if(markercolor != null) g.setColor(markercolor);
               else                    g.setColor(c);
               draw_markers(g,bounds);
           }
           // special marker types added by W. Christian
           if(marker==-1){
               if ( linecolor != null) g.setColor(linecolor);
               else                    g.setColor(c);
               draw_polygon(g,bounds);
           }
           if(marker==-2){
               if ( linecolor != null) g.setColor(linecolor);
               else                    g.setColor(c);
               draw_polygon2(g,bounds);
           }

           if(marker==-3){
               if ( linecolor != null) g.setColor(linecolor);
               else                    g.setColor(c);
               draw_histogram(g,bounds);
           }


           g.setColor(c);
      }
   /**
   * return the data X maximum.
   */
      public double[] getData() {  return data; }

  /**
   * return the data X maximum.
   */
      public double getXmax() {  return dxmax; }
  /**
   * return the data X minimum.
   */
      public double getXmin() {  return dxmin; }
  /**
   * return the data Y maximum.
   */
      public double getYmax() {  return dymax; }
  /**
   * return the data Y minimum.
   */
      public double getYmin() {  return dymin; }


  /**
   * Define a data legend in the graph window
   * @param x    pixel position of the legend.
   * @param y    pixel position of the legend.
   * @param text text to display in the legend
   */
      public void legend(int x, int y, String text) {
           if(text == null) { legend_text = null;  return; }
           if(legend_text == null) legend_text = new TextLine(text);
           else                    legend_text.setText(text);
           legend_text.setJustification(TextLine.LEFT);
           legend_ix    = x;
           legend_iy    = y;
           legend_dx    = 0.0;
           legend_dy    = 0.0;

      }
  /**
   * Get the legend attached to a DataSet
   */
   public String getLegend(){return legend_text.getText();}

  /**
   * Get the legend x position
   */
   public int getLegend_ix(){return legend_ix;}

  /**
   * Get the legend y position
   */
   public int getLegend_iy(){return legend_iy;}


  /**
   * Define a data legend in the graph window
   * @param x    data position of the legend.
   * @param y    data position of the legend.
   * @param text text to display in the legend
   */
      public void legend(double x, double y, String text) {
           if(text == null) { legend_text = null;  return; }
           if(legend_text == null) legend_text = new TextLine(text);
           else                    legend_text.setText(text);
           legend_text.setJustification(TextLine.LEFT);
           legend_dx    = x;
           legend_dy    = y;
           legend_ix    = 0;
           legend_iy    = 0;
      }
  /**
   * Set the font to be used in the legend
   * @param f font
   */
      public void legendFont(Font f) {
           if(f == null) return;
           if(legend_text == null) legend_text = new TextLine();

           legend_text.setFont(f);
      }
  /**
   * Set the color for the legend text
   * @param c color
   */
      public void legendColor(Color c) {
           if(c == null) return;
           if(legend_text == null) legend_text = new TextLine();

           legend_text.setColor(c);
      }
  /**
   * Return the number of data points in the DataSet
   * @return number of (x,y) points.
   */
      public int dataPoints() {  return length/stride; }

  /**
   * get the data point at the parsed index. The first (x,y) pair
   * is at index 0.
   * @param index Data point index
   * @return array containing the (x,y) pair.
   */
      public double[] getPoint(int index) {
            double point[] = new double[stride];
            int i = index*stride;
            if( index < 0 || i > length-stride ) return null;

            for(int j=0; j<stride; j++) point[j] = data[i+j];

            return point;
          }

  /**
   * Return the data point that is closest to the parsed (x,y) position
   * @param x
   * @param y (x,y) position in data space.
   * @return array containing the closest data point.
   */
      public double[] getClosestPoint(double x, double y) {
            double point[] = {0.0, 0.0, 0.0};
            int i;
            double xdiff, ydiff, dist2;

            xdiff = data[0] - x;
            ydiff = data[1] - y;

            point[0] = data[0];
            point[1] = data[1];
            point[2] = xdiff*xdiff + ydiff*ydiff;



            for(i=stride; i<length-1; i+=stride) {

                xdiff = data[i  ] - x;
                ydiff = data[i+1] - y;


                dist2 = xdiff*xdiff + ydiff*ydiff;

                if(dist2 < point[2]) {
                    point[0] = data[i  ];
                    point[1] = data[i+1];
                    point[2] = dist2;
                  }

           }

           //System.out.println("DataSet: closestpoint "+point[0]+", "+point[1]+", "+point[2]);

           return point;

          }


/*
*********************
** Protected Methods
*********************/

  /**
   * Draw into the data window the straight line segments joining the
   * data points as a filled polygon.
   * @param g Graphics context
   * @param w Data window
   */

      private void draw_polygon2(Graphics g, Rectangle w) {  // added by W. Christian.  Draws even if outside the clip region.
          if( data == null || data.length < stride || data.length<4) return;
          int i;
          int x0 = 0 , y0 = 0;
          int x1 = 0 , y1 = 0;
          int count=0;
          int[] xpoints= new int[data.length/stride+4];
          int[] ypoints= new int[data.length/stride+4];
          int yOrigin = (int)(w.y + (1.0 - (0-ymin)/yrange)*w.height);
          int ySave=0;
          Color oldColor=g.getColor();
          g.setColor(new Color(0,255,255));
          if(xmin-data[0]>10*xrange) x0=-100000;
            else if(data[0]-xmax > 10*xrange) x0=100000;
            else x0 = (int)(w.x + ((data[0]-xmin)/xrange)*w.width);
          if(ymin-data[1]>10*yrange) y0=100000;
            else if(data[1]-ymax>10*yrange) y0=-100000;
            else y0 = (int)(w.y + (1.0 - (data[1]-ymin)/yrange)*w.height);
          ySave=y0;
          if(y0>yOrigin)y0=yOrigin;
          for(i=stride; i<length; i+=stride) {
              if(xmin-data[i]>10*xrange) x1=-100000;
                else if(data[i]-xmax>10*xrange) x1=100000;
                else x1 = (int)(w.x + ((data[i]-xmin)/xrange)*w.width);
              if(ymin-data[i+1]>10*yrange) y1=100000;
                else if(data[i+1]-ymax>10*yrange) y1=-100000;
                else y1 = (int)(w.y + (1.0 - (data[i+1]-ymin)/yrange)*w.height);
              if(y1!=yOrigin && ((double)(y0-yOrigin)/(y1-yOrigin)<0) && y1!=y0){  // change in sign so interpolate x
                 double m=(double)(x1-x0)/(y1-y0);   // 1/slope
                 if(y1>yOrigin){
                     x1=(int)(x0+(yOrigin-y0)*m);
                     y1=yOrigin;
                 }
              }else if(y0==yOrigin && y1<yOrigin){  // interpolate x
                  y0=ySave;
                  double m=(double)(x1-x0)/(y1-y0);   // 1/slope
                  x0=(int)(x1+(yOrigin-y1)*m);
                  y0=yOrigin;
              }else if(y1>yOrigin){
                  ySave=y1;  // save the value in case y1 gets changed later.
                  y1=yOrigin;
              }
              xpoints[count]=x0;
              ypoints[count]=y0;
              count++;
              x0 = x1;
              y0 = y1;
          }
          xpoints[count]=x0;
          ypoints[count]=y0;
          count++;
          xpoints[count]=x0;
          ypoints[count]=yOrigin;
          count++;
          xpoints[count]=xpoints[0];
          ypoints[count]=yOrigin;
          count++;
          g.fillPolygon(xpoints,ypoints,count);
          g.setColor(oldColor);
          g.drawPolygon(xpoints,ypoints,count);
          count=0;
          g.setColor(new Color(255,255,0));
          if(xmin-data[0]>10*xrange) x0=-10000;
            else if(data[0]-xmax > 10*xrange) x0=10000;
            else x0 = (int)(w.x + ((data[0]-xmin)/xrange)*w.width);
          if(ymin-data[1]>10*yrange) y0=10000;
            else if(data[1]-ymax>10*yrange) y0=-10000;
            else y0 = (int)(w.y + (1.0 - (data[1]-ymin)/yrange)*w.height);
          ySave=y0;
          if(y0<yOrigin)y0=yOrigin;
          for(i=stride; i<length; i+=stride) {
              if(xmin-data[i]>10*xrange) x1=-10000;
                else if(data[i]-xmax>10*xrange) x1=10000;
                else x1 = (int)(w.x + ((data[i]-xmin)/xrange)*w.width);
              if(ymin-data[i+1]>10*yrange) y1=10000;
                else if(data[i+1]-ymax>10*yrange) y1=-10000;
                else y1 = (int)(w.y + (1.0 - (data[i+1]-ymin)/yrange)*w.height);
              if(y1!=yOrigin && ((double)(y0-yOrigin)/(y1-yOrigin)<0) && y1!=y0){  // change in sign so interpolate x
                 double m=(double)(x1-x0)/(y1-y0);   // 1/slope
                 if(y1<yOrigin){     // going into positive region
                     x1=(int)(x0+(yOrigin-y0)*m);
                     y1=yOrigin;
                 }
              }else if(y0==yOrigin && y1>yOrigin){  // interpolate x
                  y0=ySave;
                  double m=(double)(x1-x0)/(y1-y0);   // 1/slope
                  x0=(int)(x1+(yOrigin-y1)*m);
                  y0=yOrigin;
              }else if(y1<yOrigin){
                  ySave=y1;
                  y1=yOrigin;
              }
              xpoints[count]=x0;
              ypoints[count]=y0;
              count++;
              x0 = x1;
              y0 = y1;
          }
          xpoints[count]=x0;
          ypoints[count]=y0;
          count++;
          xpoints[count]=x0;
          ypoints[count]=yOrigin;
          count++;
          xpoints[count]=xpoints[0];
          ypoints[count]=yOrigin;
          count++;
          g.fillPolygon(xpoints,ypoints,count);
          g.setColor(oldColor);
          g.drawPolygon(xpoints,ypoints,count);
      }

      private void draw_polygon(Graphics g, Rectangle w) {  // added by W. Christian.  Draws even if outside the clip region.
          if( data == null || data.length < stride || data.length<4) return;
          int i;
          int x0 = 0 , y0 = 0;
          int x1 = 0 , y1 = 0;
          int count=0;
          int[] xpoints= new int[data.length/stride+4];
          int[] ypoints= new int[data.length/stride+4];
          int yOrigin = (int)(w.y + (1.0 - (0-ymin)/yrange)*w.height);
          if(xmin-data[0]>10*xrange) x0=-10000;
            else if(data[0]-xmax > 10*xrange) x0=10000;
            else x0 = (int)(w.x + ((data[0]-xmin)/xrange)*w.width);
          if(ymin-data[1]>10*yrange) y0=10000;
            else if(data[1]-ymax>10*yrange) y0=-10000;
            else y0 = (int)(w.y + (1.0 - (data[1]-ymin)/yrange)*w.height);
          for(i=stride; i<length; i+=stride) {
              if(xmin-data[i]>10*xrange) x1=-10000;
                else if(data[i]-xmax>10*xrange) x1=10000;
                else x1 = (int)(w.x + ((data[i]-xmin)/xrange)*w.width);
              if(ymin-data[i+1]>10*yrange) y1=10000;
                else if(data[i+1]-ymax>10*yrange) y1=-10000;
                else y1 = (int)(w.y + (1.0 - (data[i+1]-ymin)/yrange)*w.height);
              xpoints[count]=x0;
              ypoints[count]=y0;
              count++;
              x0 = x1;
              y0 = y1;
          }
          xpoints[count]=x0;
          ypoints[count]=y0;
          count++;
          xpoints[count]=x0;
          ypoints[count]=yOrigin;
          count++;
          xpoints[count]=xpoints[0];
          ypoints[count]=yOrigin;
          count++;
          g.fillPolygon(xpoints,ypoints,count);
      }

      protected synchronized void draw_histogram(Graphics g, Rectangle w) {  // added by W. Christian.  Draws even if outside the clip region.
          if( data == null || data.length < stride || data.length<2) return;
          Markers m = g2d.getMarkers();
          if( m == null) return;
          int binwidth=(int)(markerscale*2);
          int x1 = 0 , y1 = 0;
          int yOrigin = (int)(w.y + (1.0 - (0-ymin)/yrange)*w.height);
          for(int i=0; i<length; i+=stride) {
              if(xmin-data[i]>10*xrange) x1=-10000;
                else if(data[i]-xmax>10*xrange) x1=10000;
                else x1 = (int)(w.x + ((data[i]-xmin)/xrange)*w.width);
              if(ymin-data[i+1]>10*yrange) y1=10000;
                else if(data[i+1]-ymax>10*yrange) y1=-10000;
                else y1 = (int)(w.y + (1.0 - (data[i+1]-ymin)/yrange)*w.height);
              if(yOrigin-y1<0)g.fillRect(x1-binwidth, yOrigin+1, 2*binwidth+1,-yOrigin+y1);
                else g.fillRect(x1-binwidth, y1+1, 2*binwidth+1,yOrigin-y1);
          }
      }

/*
  /**
   * Draw into the data window the straight line segments joining the
   * data points.
   * @param g Graphics context
   * @param w Data window

*/
      protected synchronized void draw_lines(Graphics g, Rectangle w) {  // new version by W. Christian.  Draws even if outside the clip region.
          int i;
          int x0 = 0 , y0 = 0;
          int x1 = 0 , y1 = 0;
//    Is there any data to draw? Sometimes the draw command will
//    will be called before any data has been placed in the class.
          if( data == null || data.length < stride ) return;
          if(xmin-data[0]>10*xrange) x0=-10000;
            else if(data[0]-xmax > 10*xrange) x0=10000;
            else x0 = (int)(w.x + ((data[0]-xmin)/xrange)*w.width);
          if(ymin-data[1]>10*yrange) y0=10000;
            else if(data[1]-ymax>10*yrange) y0=-10000;
            else y0 = (int)(w.y + (1.0 - (data[1]-ymin)/yrange)*w.height);
          for(i=stride; i<length; i+=stride) {
              if(xmin-data[i]>10*xrange) x1=-10000;
                else if(data[i]-xmax>10*xrange) x1=10000;
                else x1 = (int)(w.x + ((data[i]-xmin)/xrange)*w.width);
              if(ymin-data[i+1]>10*yrange) y1=10000;
                else if(data[i+1]-ymax>10*yrange) y1=-10000;
                else y1 = (int)(w.y + (1.0 - (data[i+1]-ymin)/yrange)*w.height);
              //x0 = (int)(w.x + ((data[i-stride]-xmin)/xrange)*w.width);
              //y0 = (int)(w.y + (1.0 - (data[i-stride+1]-ymin)/yrange)*w.height);
              g.drawLine(x0,y0,x1,y1);
              x0 = x1;
              y0 = y1;

          }
      }


  /**
   *  Return true if the point (x,y) is inside the allowed data range.
   */

      protected boolean inside(double x, double y) {
          if( x >= xmin && x <= xmax &&
              y >= ymin && y <= ymax )  return true;
          return false;
      }

  /**
   *  Draw the markers.
   *  Only markers inside the specified range will be drawn. Also markers
   *  close the edge of the clipping region will be clipped.
   * @param g Graphics context
   * @param w data window
   * @see graph.Markers
   */
      protected void draw_markers(Graphics g, Rectangle w) {
          int x1,y1;
          int i;
//     Calculate the clipping rectangle
          Rectangle clip = g.getClipBounds();
          int xcmin = clip.x;
          int xcmax = clip.x + clip.width;
          int ycmin = clip.y;
          int ycmax = clip.y + clip.height;
/*
**        Load the marker specified for this data
*/
          Markers m = g2d.getMarkers();


          if( m == null) return;

//          System.out.println("Drawing Data Markers!");

          for(i=0; i<length; i+=stride) {
              if( inside( data[i], data[i+1]) ) {

                x1 = (int)(w.x + ((data[i]-xmin)/xrange)*w.width);
                y1 = (int)(w.y + (1.0 - (data[i+1]-ymin)/yrange)*w.height);

                if( x1 >= xcmin && x1 <= xcmax &&
                   y1 >= ycmin && y1 <= ycmax )
                        m.draw(g, marker, markerscale, x1, y1);

                }
          }


      }
  /**
   *  Draw the dots at the data points.
   * @param g Graphics context
   * @param w data window
   * @see graph.Markers
   */
      protected synchronized void draw_dots(Graphics g, Rectangle w) {
          int x1,y1;
          int i;
//     Calculate the clipping rectangle
          Rectangle clip = g.getClipBounds();
          int xcmin = clip.x;
          int xcmax = clip.x + clip.width;
          int ycmin = clip.y;
          int ycmax = clip.y + clip.height;
/*
**        Load the marker specified for this data
*/

          for(i=0; i<length; i+=stride) {
              if( inside( data[i], data[i+1]) ) {

                x1 = (int)(w.x + ((data[i]-xmin)/xrange)*w.width);
                y1 = (int)(w.y + (1.0 - (data[i+1]-ymin)/yrange)*w.height);

                if( x1 >= xcmin && x1 <= xcmax &&
                   y1 >= ycmin && y1 <= ycmax )
                        g.drawLine(x1,y1,x1,y1);

                }
          }


      }

  /**
   * Draw a legend for this data set
   * @param g Graphics context
   * @param w Data Window
   */

      protected void draw_legend(Graphics g, Rectangle w) {
          Color c = g.getColor();
          Markers m = null;


          if( legend_text == null) return;
          if( legend_text.isNull() ) return;

          if( legend_ix == 0 && legend_iy == 0 ) {
                legend_ix = (int)(w.x + ((legend_dx-xmin)/xrange)*w.width);
                legend_iy = (int)(w.y + (1.0 - (legend_dy-ymin)/yrange)*w.height);
          }




          if( linestyle != DataSet.NOLINE ) {
              if ( linecolor != null) g.setColor(linecolor);
              g.drawLine(legend_ix,legend_iy,legend_ix+legend_length,legend_iy);
          }

          if( marker > 0 ) {
               m = g2d.getMarkers();
               if( m != null) {
                  if(markercolor != null) g.setColor(markercolor);
                  else                    g.setColor(c);

                  m.draw(g,marker,1.0, legend_ix+legend_length/2, legend_iy);
               }
          }


          legend_text.draw( g,
                        legend_ix+legend_length+legend_text.charWidth(g,' '),
                        legend_iy+legend_text.getAscent(g)/3);

          g.setColor(c);

      }

  /**
   * Calculate the range of the data. This modifies dxmin,dxmax,dymin,dymax
   * and xmin,xmax,ymin,ymax
   */

      protected void range(int stride) {
           int i;


           if( length >= stride ) {
              dxmax = data[0];
              dymax = data[1];
              dxmin = dxmax;
              dymin = dymax;
           } else {
               dxmin = 0.0;
               dxmax = 0.0;
               dymin = 0.0;
               dymax = 0.0;
           }

           for(i=stride; i<length; i+=stride ) {

             if( dxmax < data[i] )   { dxmax = data[i]; }
             else
             if( dxmin > data[i] )   { dxmin = data[i]; }

             if( dymax < data[i+1] ) { dymax = data[i+1]; }
             else
             if( dymin > data[i+1] ) { dymin = data[i+1]; }
           }

           if( xaxis == null) {
              xmin = dxmin;
              xmax = dxmax;
           }
           if( yaxis == null) {
              ymin = dymin;
              ymax = dymax;
           }
     }

  /**
   * Insert one datum into the data array.
   * Added by W. Christian.
   */
   private synchronized void insertDatum(int index){
       if(length<2*stride)return; // need at least two points to insert.
       if(tempDatum.length!=stride) tempDatum = new double[stride];
       System.arraycopy(data, index, tempDatum, 0, stride);
       for(int i=0; i<length; i+=stride ){
           if( data[i] > data[index] ){
               System.arraycopy(data, i, data, i+stride, length-i-stride);
               System.arraycopy(tempDatum, 0, data, i, stride);
               return;
           }
       }
   }

  /**
   * Perform an insertion sort of the data set.
   *
   * Since data will be partially sorted this should be the fastest.
   * Added by W. Christian.
   */
   protected void insertionSort(){
       if(length<2*stride)return; // need at least two points to sort.
       for(int i=stride; i<length; i+=stride ) {
           if( data[i] < data[i-stride] ) {
             insertDatum(i);
           }
       }
   }

   protected void chartPoints(){
       if(length<=chartPts*stride)return; // no need to drop points since we have not reached the required number of points
       System.arraycopy(data, length-chartPts*stride, data, 0, chartPts*stride);
       length=chartPts*stride;
   }

}


