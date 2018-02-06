package edu.davidson.display;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

//import java.io.InputStream;
import edu.davidson.graph.*;

/*
**************************************************************************
**
**    Class  SContour
**
**
** This class extends the graphics class to incorporate
** contours.
**
*************************************************************************/

/**
 * This class extends the graphics class Graph2D to incorporate
 * contouring.
 *
 * @version  $Revision: 1.11 $, $Date: 1996/08/12 23:37:08 $.
 * @author   Wolfgang Christian and Leigh Brookshaw
 */


// Changed from extends Grah2Dint by wc.
public class SContour extends SGraph {

/*
**************
**
** Constants
**
*************/

/*
**   The minimum length of a curve before it gets a label
*/
         //static final int MINCELLS = 30;  //was 30 Changed by wc
         static  int MINCELLS = 25;
/*
**   Default number of contour levels
*/
         static final int NLEVELS = 12;


/**********************
**
** Protected Variables
**
***********************/

/**
 *   Dimension of the contour grid in the X direction
 */
     protected int nx;
/**
 *   Dimension of the contour grid in the Y direction
 */
     protected int ny;
/**
 *   Vector array containing the Contour curves.
 *   Each index in the array contains curves at a given
 *   contour level
 */
     protected Vector curves[];

/**
 *   If set the class calculates the contour levels based on
 *   the data minimum and maximum. Default value <i>true</i>.
 */
     protected boolean autoLevels;
/*
 *   If true the contour levels are calculated in
 *   logarithmic intervals
 */
     protected boolean logLevels;
/*
 *   If true the limits of the plot are the limits of the
 *   data grid not the limits of the contours!
 */
     protected boolean gridLimits;
/*
 *   The array of contour levels
 */
     protected double levels[];
/**
 *   The label for each contour level
 */
     protected TextLine labels[];
/**
 *   Font to use in drawing Labels
 */
     protected Font labelfont;
/**
 *   Color to use in drawing Labels
 */
     protected Color labelcolor;
/**
 *   Style to use in drawing Labels. TextLine.SCIENTIFIC or
 *   TextLine.ALGEBRAIC.
 */
     protected int labelStyle;
/**
 *   Precision to use in drawing Labels.
 */
     protected int labelPrecision;

/**
 *   Number of Significant figures to use in drawing Labels.
 */
     protected int labelSignificant;

/**
 *   Which levels will get labels. If it is equal to 1 every level
 *   gets a label, equal to 2 every second level etc. If it is equal to 0
 *   no labels are displayed.
 */
     protected int labelLevels;
/**
 *   If false labels are not drawn
 */
     protected boolean drawlabels;
/**
 *   If true the labels will be calculated for each
 *   contour level. These might not look all that hot.
 */
     protected boolean autoLabels;
 /**
  *  Color to draw non labelled contour line
  */
     protected Color contourColor;
 /**
  *  Color to draw labelled contour line
  */
     protected Color labelledColor;

/**
 *   The data grid, a 2D array stored in linear form.
 *   It is assumed that [0,0] is the bottom left corner
 *   and the data is ordered by row.
 */
     protected double grid[];

/**
 *   The X minimum limit of the data grid
 */
     protected double xmin;
/**
 *   The X maximum limit of the data grid
 */
     protected double xmax;
/**
 *   The Y minimum limit of the data grid
 */
     protected double ymin;
/**
 *   The Y maximum limit of the data grid
 */
     protected double ymax;
/**
 *   The minimum value of the grid values
 */
     protected double zmin;
/**
 *   The maximum value of the grid values
 */
     protected double zmax;

  /**
   * Boolean value if true Contours will not be calculated
   */
     public boolean noContours = false;


     // variables added by wc.
     boolean isDrag=false;
     Format format= new Format("%-+8.5g");


/*
*****************
**
** Constructors
**
****************/

  /**
   * Instantaite the class
   */
     public SContour() {
           grid = null;
           xmin = -1.0;
           xmax = 1.0;
           ymin = -1.0;
           ymax = 1.0;
           zmin = 0.0;
           zmax = 0.0;

           nx = 0;
           ny = 0;

           levels = new double[NLEVELS];
           labels = new TextLine[NLEVELS];

           autoLevels  = true;
           logLevels   = false;
           gridLimits  = true;   //changed from false by wc
           autoLabels  = true;
           labelfont   = new Font("Helvetica",Font.PLAIN,12);
           labelcolor  = Color.blue;
           labelLevels = 1;
           labelStyle       = TextLine.ALGEBRAIC;
           labelPrecision   = 2;
           labelSignificant = 3;
           drawlabels = true;

           contourColor  = null;
           labelledColor = null;

           curves = null;

          //  default parameters for the bean.
           setDataBackground(new Color(0.933f,0.914f,0.749f));
           setContourColor(new Color(0.180f,0.545f,0.341f));
           setLabelledContourColor(new Color(0.5f,.0f,0.0f));

           setLabelPrecision(2);
           setLabelSignificance(2);


           square       = false;
           setFont(new Font("TimesRoman",Font.PLAIN,15));
        deleteAllSeries();
        detachAxes();    // clean out the stuff from SGraph.
        detachDataSets();

/*
**      Instantiate the xaxis and attach the dataset.
*/

        xaxis=createAxis(Axis.BOTTOM);
        xaxis.setTitleText("X_axis");
        //xaxis.setTitleText(null);
        xaxis.setTitleColor(Color.magenta);
        xaxis.setTitleFont(new Font("TimesRoman",Font.ITALIC,15));
        xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,10));


/*
**      Instantiate the yaxis and attach the dataset.
*/

        yaxis=createAxis(Axis.LEFT);
        yaxis.setTitleText("Y_axis");
        //yaxis.setTitleText(null);
        yaxis.setTitleColor(Color.magenta);
        yaxis.setTitleFont(new Font("TimesRoman",Font.ITALIC,15));
        yaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,10));

        borderLeft = 0;
        borderTop = 0;
        borderRight = 0;
        borderBottom = 0;

        setShowAxis(true);

        createSampleData();

        xaxis.setManualRange(true);
        yaxis.setManualRange(true);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

     }

/*
************
**
** Methods
**
***********/

  /**
   *  Set the range of the grid
   * @param xmin Minimum X value
   * @param xmax Maximum X value
   * @param ymin Minimum Y value
   * @param ymax Maximum Y value
   */


     public void setRange(double xmin,double xmax,double ymin,double ymax) {

         if( xmin >= xmax || ymin >= ymax ) return;

         this.xmin = xmin;
         this.xmax = xmax;
         this.ymin = ymin;
         this.ymax = ymax;
         detachCurves();
         curves = null;
         if(gridLimits ) {
             if( xaxis != null ) { xaxis.setManualRange(true,xmin,xmax);}
             if( yaxis != null ) { yaxis.setManualRange(true,ymin,ymax);}
         }
     }

  /**
   *  Do not draw contours
   * @param no contours
  */
  public void setNoContours(boolean nc){
      if (nc==noContours) return;
      noContours=nc;
      if(nc) detachCurves(); else attachCurves();
  }

  /**
   *  Return the range of the grid
   * @return An array contining xmin,xmax,ymin,ymax.
   */
     public double[] getRange() {
            double d[] = new double[4];
            d[0] = xmin;
            d[1] = xmax;
            d[2] = ymin;
            d[3] = ymax;

            return d;
      }
  /**
   *  return the dimensions of the grid
   * @return An array containing the number of columns, number of rows.
   */
     public int[] getDim() {
            int i[] = new int[2];

            i[0] = nx;
            i[1] = ny;

            return i;
      }

  /**
   *  Return the grid
   * @return An array of size nx by ny contining the data grid.
   */
     public double[] getGrid() { return grid; }



  /**
   * Manually set the contour levels.
   * @param levels An array containing the contour levels
   * @param nl The number of contour levels in the arrray
   */
     public void setLevels(double start, double delta, int nl) {
           int i;
           nl=Math.min(nl,1000);   // don't let this get larger than 1000.
           if(nl<=0){
              autoLevels=true;
              setNLevels(NLEVELS);
              return;
           }
           if(delta<=0){
              autoLevels=true;
              setNLevels(nl);
              return;
           }

           detachCurves();
           curves = null;
           autoLevels = false;

           this.levels = new double[nl];
           for(i=0; i<nl; i++) {
             this.levels[i] = start;
             start+=delta;
           }

           labels = new TextLine[nl];
           for(i=0; i<nl; i++) {
             labels[i] = new TextLine( String.valueOf( (float)this.levels[i] ) );
           }
     }


  /**
   * Manually set the contour levels.
   * @param levels An array containing the contour levels
   * @param nl The number of contour levels in the arrray
   */
     public void setLevels(double levels[]) {
            int i;
           if(levels == null || levels.length <= 0){ //  added by W. Christian to reset the autolevels property.
               autoLevels=true;
               setNLevels(NLEVELS);
               return;
           }

           int nl=levels.length;  // added by W. Christian to make sure we have the correct length;
           detachCurves();
           curves = null;
           autoLevels = false;

           this.levels = new double[nl];

           System.arraycopy(levels,0,this.levels,0,nl);

           labels = new TextLine[nl];
           for(i=0; i<labels.length; i++) {
             labels[i] = new TextLine( String.valueOf( (float)levels[i] ) );
           }
     }
  /**
   * Manually set the Contour labels.
   * @param labels An array containing the labels.
   * @param nl Number of labels in the Array.
   */
     public void setLabels(TextLine labels[], int nl) {
           if( labels == null || nl <= 0 ) return;

           autoLabels = false;
           this.labels = new TextLine[nl];

           System.arraycopy(labels,0,this.labels,0,nl);
     }

  /**
   * Set the font to be used with All the labels
   * @param f Font
   */
     public void setLabelFont(Font f) {
           labelfont = f;
     }

  /**
   * Set the Color to be used with all the labels.
   * @param c Color
   */
     public void setLabelColor(Color c) {
           labelcolor = c;
     }

  /**
   *  Set the grid to be contoured.
   * @param grid Array of values
   * @param nx   Number of columns
   * @param ny   Number of rows
   */
     public void setGrid(double grid[],int nx,int ny) {
        this.grid = grid;
        this.nx   = nx;
        this.ny   = ny;
        MINCELLS=(nx+ny)/4;    // added by wc
        zrange();
        calcLevels();
     }

   /**
   *  Set the grid to be contoured.
   * @param grid Array of double[][]
   */

     public void setGrid(double newGrid[][]) {
         if(newGrid==null ||newGrid.length<2|| newGrid[0].length<2) return;
         setGrid(newGrid,xmin,xmax,ymin,ymax);
     }
     public void setGrid(double newGrid[][],double _xmin,double _xmax,double _ymin,double _ymax) {
        if(newGrid==null ||newGrid.length<2|| newGrid[0].length<2) return;
        int count=0;
        int ii   = newGrid.length;
        int jj   = newGrid[0].length;
        double array[] = new double[ii*jj];
        for(int j=0; j<jj;j++)
          for(int i=0; i<ii;i++){
            array[count]=newGrid[i][j];
            count++;
          }
        this.setRange(_xmin,_xmax,_ymin,_ymax);
        this.setGrid(array,ii,jj);

       // this.setLimitsToGrid(true);
      // this.setLabelLevels(3);
      // this.setNLevels(20);
        calcLabels();
        detachCurves();
        curves = null;
     }



  /**
   * Delete all the Contours
   */
     public void deleteContours() {
          if(curves == null) return;
          detachCurves();
          curves = null;
     }
  /**
   * Detach contours so that they will not be plotted.
   */
     public void detachContours() {
          if(curves == null) return;
          detachCurves();
     }
  /**
   * Attach contours so that they will be plotted.
   */
     public void attachContours() {
          if(curves == null) return;
          attachCurves();
     }

  /**
   * Set the contour's color.
   * @param c Color
   */
     public void setContourColor(Color c) { contourColor = c; }
  /**
   * Set the labelled contour's color.
   * @param c Color
   */
     public void setLabelledContourColor(Color c) { labelledColor = c; }

  /**
   * Return the contour levels.
   * @return An array containing the contour levels
   */
     public double[] getLevels() { return levels; }
  /**
   * If true the limits of the plot will be the grid limits.
   * If false the limits of the plot will be the contours.
   * @param b boolean
   */

     public void setLimitsToGrid(boolean b) { gridLimits = b; }
  /**
   *  Set the contour levels that are to have labels.
   * <pre>
   *    if 0 no labels are drawn
   *    if 1 every level gets a label
   *    If 2 every 2nd level gets a label
   *    etc.
   * </pre>
   */
     public void setLabelLevels(int i) {
          if(i<=0) labelLevels = 0;
          else     labelLevels = i;
        }

  /**
   * If true contour levels are calculated on a log scale.
   * @param b boolean
   */
     public void setLogLevels(boolean b) {
               logLevels = b;

               if( zmin <= 0.0 || zmax <= 0.0 ) logLevels = false;
             }

  /**
   * Set the number of contour levels.
   * @@param l Number of contour levels
   */
     public void setNLevels(int l) {
         if(l <= 0) return;

         levels = new double[l];

         calcLevels();

         detachCurves();
         curves = null;
     }

  /**
   * If true contour levels are calculated automatically.
   * @param b boolean
   */
     public void setAutoLevels(boolean b) {
               autoLevels = b;
     }
  /**
   * If true contour levels are not labeled.
   * @param b boolean
   */
     public void setDrawLabels(boolean b) {
         if(drawlabels == b) return;
         drawlabels = b;
         //repaint();
     }

     public boolean getDrawLabels(){return drawlabels;}

  /**
   * Set the label style, either TextLine.SCIENTIFIC or
   * TextLine.ALGEBRAIC.
   * @param s Style
   */
     public void setLabelStyle(int s) {
                   labelStyle = s;
                   calcLabels();
 }

  /**
   * Get the label style, either TextLine.SCIENTIFIC or
   * TextLine.ALGEBRAIC.
   * @return style
   */
     public int getLabelStyle() { return labelStyle; }

  /**
   * Set the label precision.
   * @param s Precision
   */
     public void setLabelPrecision(int p) {
                   labelPrecision = p;
                   calcLabels();
     }

  /**
   * Get the label precision.
   * @return precision
   */
     public int getLabelPrecision() { return labelPrecision; }

  /**
   * Set the label significant figures.
   * @param s number of significant figures
   */
     public void setLabelSignificance(int s) {
                   labelSignificant = s;
                   calcLabels();
     }

  /**
   * Get the number of significant figures for labels.
   * @return number of significant figures
   */
     public int getLabelSignificance() { return labelSignificant; }


/*
********************
**
** Private Methods
**
*******************/

/*
**  calcLevels()
**              Calculate the contour levels
*/
     private void calcLevels() {
       int i;
       int l;

       if(!autoLevels) return;

       if(levels == null) levels = new double[NLEVELS];
       labels = new TextLine[levels.length];
       // Nice label steps not implemented yet
       //levelStep();


       if( logLevels ) {
          double inc = Math.log(zmax-zmin)/
                      (double)(levels.length+1);
          try {
             for(i=0; i<levels.length; i++) levels[i] = zmin +
                          Math.pow(Math.E,(double)(i+1)*inc);
           } catch (Exception e) {
             System.out.println("Error calculateing Log levels!");
             System.out.println("... calculating linear levels instead");
             logLevels = false;
             calcLevels();
           }
        } else {
          double inc = (zmax-zmin)/(double)(levels.length+1);
          for(i=0; i<levels.length; i++) levels[i] = zmin + (double)(i+1)*inc;
        }
     }
/*
**  calcLabels()
**              Calculate the labels
*/
     private void calcLabels() {
        int i;
        if( !autoLabels ) return;

        if(levels==null || levels.length <= 0) return;

        labels = new TextLine[levels.length];


        for(i=0; i<labels.length; i++) {
             labels[i] = new TextLine();
             labels[i].parseDouble(levels[i],
                      labelSignificant,labelPrecision,labelStyle);
        }
     }

/*
**   zrange()
**           Calculate the range of the grid
*/

     private void zrange() {
        int i;

        zmin = grid[0];
        zmax = grid[1];
        for( i=0; i<grid.length; i++) {

             zmin = Math.min(zmin,grid[i]);
             zmax = Math.max(zmax,grid[i]);

        }

        //System.out.println("Data range: zmin="+zmin+", zmax="+zmax);

        if(zmin == zmax) {
           //System.out.println("Cannot produce contours of a constant surface!");
         }

        if(zmin <= 0 || zmax <= 0) logLevels = false;
      }
/*
**   paintFirst(Graphics g, Rectangle r)
**        before anything is painted calculate the contours.
*/

      public void paintFirst(Graphics g, Rectangle r) {

         //System.out.println("paintFirst called");


         synchronized(delayLock){
         if( curves == null && !noContours ) {
                                  calculateCurves();
                                  calcLabels();
                                } }

         setContourColors();

         if(gridLimits ) {
           if( xaxis != null ) {    // changed by wc
               // if(xaxis.minimum > xmin ) xaxis.minimum = xmin;
               // if(xaxis.maximum < xmax ) xaxis.maximum = xmax;
                if(xaxis.minimum != xmin ) xaxis.minimum = xmin;
                if(xaxis.maximum != xmax ) xaxis.maximum = xmax;
              }

           if( yaxis != null ) {  // changed by wc
                //if(yaxis.minimum > ymin ) yaxis.minimum = ymin;
                // if(yaxis.maximum < ymax ) yaxis.maximum = ymax;
                if(yaxis.minimum != ymin ) yaxis.minimum = ymin;
                if(yaxis.maximum != ymax ) yaxis.maximum = ymax;
              }
         } else
         if( dataset.isEmpty() ) {
           if( xaxis != null ) {
                xaxis.minimum = xmin;
                xaxis.maximum = xmax;
              }

           if( yaxis != null ) {
                yaxis.minimum = ymin;
                yaxis.maximum = ymax;
              }
         }


      }

  /**
  *  Set the colors for the contour lines
  */
      private void setContourColors() {
        int i;
        int j;
        Vector v;

        if(curves == null ||
           (contourColor==null && labelledColor==null) ) return;

        for(i=0; i<curves.length; i++) {
            setContourColors(curves[i],null);
        }

        if(contourColor != null) {
           for(i=0; i<curves.length; i++) {
               setContourColors(curves[i],contourColor);
           }
        }

        if(labelledColor != null) {
           for(i=0; i<curves.length; i++) {
               if(i%labelLevels == 0) {
                  setContourColors(curves[i],labelledColor);
               }
           }
        }



      }

  /**
  *  Set the colors for the contour lines
  */
      private void setContourColors(Vector v, Color c) {
        int i;
        DataSet d;

        if(v == null) return;

        for(i=0; i<v.size(); i++) {
            d = (DataSet)(v.elementAt(i));
            if(d != null) d.linecolor = c;
        }

      }
/*
**    attachCurves()
**        Attach all the curves to the graph and to the axes
*/
      private void attachCurves() {
         int i;
         if(curves == null) return;

         for(i=0; i<curves.length; i++) attachCurves(curves[i]);

      }
/*
**    attachCurves(Vector v)
**        Attach all the curves from a given level to the graph and to the axes
*/

      private void attachCurves(Vector v) {
         int j;
         if(v == null) return;
         for(j=0; j<v.size(); j++) {
               attachDataSet((DataSet)(v.elementAt(j)));
               if(xaxis != null)
                        xaxis.attachDataSet((DataSet)(v.elementAt(j)));
               if(yaxis != null)
                        yaxis.attachDataSet((DataSet)(v.elementAt(j)));
             }
      }
/*
**    detachCurves()
**                 Detach All the curves from the graph and the axes.
*/
      private void detachCurves() {
         int i;
         if(curves == null) return;

         for(i=0; i<curves.length; i++) detachCurves(curves[i]);
      }
/*
**    detachCurves()
**                 Detach all the curves from a given level from
**                 the graph and the axes.
*/
      private void detachCurves(Vector v) {
         int j;
         if(v == null) return;
         for(j=0; j<v.size(); j++) {
               detachDataSet((DataSet)(v.elementAt(j)));
               if(xaxis != null)
                        xaxis.detachDataSet((DataSet)(v.elementAt(j)));
               if(yaxis != null)
                        yaxis.detachDataSet((DataSet)(v.elementAt(j)));
             }
      }

/*
**    paintLast(Graphics g, Rectangle rect)
**          Last thing to be done is to draw the contour labels if required.
*/
      public void paintLast(Graphics g, Rectangle rect) {
              int i, j;
              int points;
              int index;
              Vector v;
              DataSet ds;
              double point[] = new double[2];
              int x;
              int y;
              Color current = g.getColor();
              Rectangle r = new Rectangle();


              if( xaxis == null || yaxis == null || labels == null ||
                  labelLevels == 0 || !drawlabels || curves == null ) {
                         super.paintLast(g,rect);
                         return;
              }


              for(i=0; i<levels.length; i++) {
                 if( labels[i] != null && !labels[i].isNull() &&
                     i%labelLevels == 0 ) {
                    labels[i].setFont(labelfont);
                    labels[i].setColor(labelcolor);
                    v = curves[i];
                    for(j=0; j<v.size(); j++) {
                        ds =  (DataSet)(v.elementAt(j));
                        points = ds.dataPoints();
                        index = (int)(Math.random()*(double)MINCELLS);
                        while ( points > MINCELLS ) {
                             point = ds.getPoint(index);
                             x = xaxis.getInteger(point[0]);
                             y = yaxis.getInteger(point[1]);

                             r.width  = labels[i].getWidth(g);
                             r.height = labels[i].getAscent(g);
                             r.x = x - r.width/2;
                             r.y = y - r.height/2;

                             g.setColor(DataBackground);
                             g.fillRect(r.x, r.y, r.width, r.height);

                             g.setColor(current);

                             labels[i].draw(g, r.x, r.y+r.height,
                                            TextLine.LEFT);

                             points -= MINCELLS;
                             index += MINCELLS;
                        }
                      }
                  }
              }

              super.paintLast(g,rect);


            }
/*
**   calculateCurves()
**        Calculate the contours and attach them to the graph and axes.
*/

     protected synchronized void calculateCurves() {
          int i;
          int j;
          double data[];
          double xscale = (xmax-xmin)/(double)(nx-1);
          double yscale = (ymax-ymin)/(double)(ny-1);

          IsoCurve isocurve;

         // double[] newGrid= new double[grid.length];
         // System.arraycopy(grid,0,newGrid,0,grid.length);

          isocurve = new IsoCurve(grid,nx,ny);

          synchronized(delayLock){
          if( curves != null) {
                               detachCurves();
                               curves = null;
                             }
          if( zmin == zmax ) return;

          int numCurves=levels.length;
          Vector newCurves[] = new Vector[numCurves];
         // try {     // trap for synchronization bug
          for(i=0; i<numCurves; i++) {
             // System.out.println("Calculating Contours: level="+levels[i]);
              isocurve.setValue(levels[i]);

              newCurves[i] = new Vector();
              data = isocurve.getCurve();
              while( isocurve!=null && data!= null ) {
                for(j=0; j<data.length; ) {
                   data[j] = xmin + data[j]*xscale;
                   j++;
                   data[j] = ymin + data[j]*yscale;
                   j++;
                 }

                try {
                  newCurves[i].addElement(new DataSet(data, data.length/2));
                } catch (Exception e) {
                  System.out.println("Error loading contour into DataSet!");
                  System.out.println("...Contour Level "+levels[i]);
                  return;    // return added by W. Christian.
                }
                data = isocurve.getCurve();
              }
              calcLabels(); // added by wc to get labels
              if(newCurves != null && newCurves[i]!=null) attachCurves(newCurves[i]); // if statement added by W. Christian

              //repaint();
          }
          curves=newCurves;
          } // end of synchronization
          //catch (Exception e) {;}   // end of synchronization bug trap.
     }

     public void calculateCurve(double aLevel) {
          int j;
          double data[];
          double xscale = (xmax-xmin)/(double)(nx-1);
          double yscale = (ymax-ymin)/(double)(ny-1);

          IsoCurve isocurve;


          isocurve = new IsoCurve(grid,nx,ny);

          if( zmin == zmax ) return;

             // System.out.println("Calculating Contours: level="+levels[i]);
              isocurve.setValue(aLevel);

              Vector aCurve = new Vector();

              while( isocurve!=null && (data = isocurve.getCurve()) != null ) {
                for(j=0; j<data.length; ) {
                   data[j] = xmin + data[j]*xscale;
                   j++;
                   data[j] = ymin + data[j]*yscale;
                   j++;
                 }

                try {
                  aCurve.addElement(new DataSet(data, data.length/2));
                } catch (Exception e) {
                  System.out.println("Error loading contour into DataSet!");
                  System.out.println("...Contour Level "+aLevel);
                  return;
                }
              }
              if(aCurve!=null) attachCurves(aCurve);  // if statement added by W. Christian

     }

    public void deleteAllSeries(){
      deleteContours();
      super.deleteAllSeries();
      calcLevels();
      repaint();
    }
    public void createSampleData() {
         int i,j;
         int count;

         int nx = 50;
         int ny = 50;

         double xmin = -1.0;
         double xmax =  1.0;
         double ymax =  1.0;
         double ymin = -1.0;


         double array[] = new double[nx*ny];
         double x, y, rad;

         double h1, h2, h3;

         h1 = 0.5*0.5;

         h2 = 0.75*0.75;

         h3 = 0.25*0.25;

         count = 0;  // count each element of the array.

         for(j=0; j<ny; j++) {

             y = 2.0*(double)j/(double)(ny-1) - 1.0;

             for(i=0; i<nx; i++) {
                x = 2.0*(double)i/(double)(nx-1) - 1.0;

                rad = (x-0.5)*(x-0.5) + (y+0.5)*(y+0.5);
                array[count] = Math.exp( -rad/h1 );

                rad = (x+0.3)*(x+0.3) + (y-0.75)*(y-0.75);
                array[count] += Math.exp( -rad/h2 );

                rad = (x+0.7)*(x+0.7) + (y+0.6)*(y+0.6);
               // array[count] += Math.exp( -rad/h3 );

                array[count]=x*x+x*y;

                count++;
	      }
	   }
           this.setRange(xmin,xmax,ymin,ymax);
           this.setGrid(array,nx,ny);
          // this.setLimitsToGrid(true);  this is the default.  No need to change.
           this.setLabelLevels(3);
           this.setNLevels(20);
      }

  void paintCoordinates(double x,double y){
      Graphics g=getGraphics();
      FontMetrics fm=g.getFontMetrics(g.getFont());
      String msg="x=" + format.form((double)x)+ " y=" +format.form((double)y);
      g.setColor(Color.yellow);
      int w=20+fm.stringWidth(msg);
      g.fillRect(0,getBounds().height-15,w,15);
      g.setColor(Color.black);
      g.drawString(msg, 8, getBounds().height-2);
      g.dispose();
  }

  public void jbInit() throws Exception{
    this.addMouseMotionListener(new SContour_this_mouseMotionAdapter(this));
    this.addMouseListener(new SContour_this_mouseAdapter(this));
  }

  void this_mousePressed(MouseEvent e) {
    isDrag=true;
    double x=xFromPix(e.getX());
    double y=yFromPix(e.getY());
    paintCoordinates(x,y);
  }

  void this_mouseReleased(MouseEvent e) {
    isDrag=false;
    repaint();
  }

  void this_mouseMoved(MouseEvent e) {
      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

  void this_mouseDragged(MouseEvent e) {
    double x=xFromPix(e.getX());
    double y=yFromPix(e.getY());
    if(isDrag){
      // System.out.println("Dragged"+x);
       paintCoordinates(x,y);
    }
  }



}

class SContour_this_mouseAdapter extends java.awt.event.MouseAdapter {
  SContour adaptee;

  SContour_this_mouseAdapter(SContour adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.this_mousePressed(e);
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.this_mouseReleased(e);
  }
}

class SContour_this_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  SContour adaptee;

  SContour_this_mouseMotionAdapter(SContour adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.this_mouseMoved(e);
  }

  public void mouseDragged(MouseEvent e) {
    adaptee.this_mouseDragged(e);
  }
}
