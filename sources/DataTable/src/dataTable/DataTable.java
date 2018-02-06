package dataTable;

import java.awt.*;
import edu.davidson.tools.*;
import edu.davidson.views.SGridPanel;

/**
 * This applet shows a row-column table of double values.  Each data column
 * is referred to as a series. A series is identified by a series number and
 * has a unique color and style.
 * Whenever data is added using a data connection, the appropriate series
 * number must be specified.
 * <p>The following embedding parameters are defined:</p>
 * <div align="center">
 * <center>
 * <table border="2" width="508" height="204">
 * <tr>
 * <th align="center" width="70" height="19">Parameter</th>
 * <th align="center" width="47" height="19">&nbsp;Value </th>
 * <th align="center" width="43" height="19">&nbsp;Type </th>
 * <th align="center" width="318" height="19">Description</th>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="11">NumRows</td>
 * <td align="center" width="47" height="11">10</td>
 * <td width="43" height="11">int</td>
 * <td width="318" height="11">The number of rows.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">NumCols</td>
 * <td align="center" width="47" height="19">2</td>
 * <td width="43" height="19">int</td>
 * <td width="318" height="19">The number of columns.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">CellWidth</td>
 * <td align="center" width="47" height="19">40</td>
 * <td width="43" height="19">int</td>
 * <td width="318" height="19">The size of a cell in pixels.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">ShowRowHeader</td>
 * <td align="center" width="47" height="19">true</td>
 * <td width="43" height="19">booelan</td>
 * <td width="318" height="19">Show the top header row.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">ShowRowHeader</td>
 * <td align="center" width="47" height="19">true</td>
 * <td width="43" height="19">boolean</td>
 * <td width="318" height="19">Show the left most header column.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">LastOnTop</td>
 * <td align="center" width="47" height="19">false</td>
 * <td width="43" height="19">boolean</td>
 * <td width="318" height="19">Add data to the top of the table rather than
 * the bottom.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">ShowScrollBars</td>
 * <td align="center" width="47" height="19">true</td>
 * <td width="43" height="19">boolean</td>
 * <td width="318" height="19">Create a scrollable table.&nbsp; Useful for
 * large data set.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="6">SizeToFit</td>
 * <td align="center" width="47" height="6">false</td>
 * <td width="43" height="6">boolean</td>
 * <td width="318" height="6">Make the data table fit the embedding
 * size.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="6">ShowControls</td>
 * <td align="center" width="47" height="6">true</td>
 * <td width="43" height="6">boolean</td>
 * <td width="318" height="6">Show the user interface.</td>
 * </tr>
 * </table>
 * </center>
 * </div>
 * <p>DataTable is designed to be used as a data listener with other
 * Physlets.&nbsp; The object identifier for the data listener is passed to
 * a data connection.&nbsp; This value is is obtained using the following
 * method.</p>
 * <pre>getTableID().</pre>
 *
 * @author             Wolfgang Christian
 * @version            $Revision: 0.9b $, $Date: 1999/07/15 08:00:00 $
 */
public class DataTable extends SApplet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String dataFile="";
    int numRows;
    int numCols;
    int cellWidth;
    boolean showControls;
    boolean showRowHeader;
    boolean showColHeader;
    boolean showScrollBars=true;
    boolean lastOnTop;
    boolean sizeToFit;
    SGridPanel grid;
    BorderLayout borderLayout1 = new BorderLayout();

    /**
     * @y.exclude
     */
    public DataTable() {
    }

    /**
     * @y.exclude
     */
    public void init() {
       initResources(null);
        try { numRows = Integer.parseInt(this.getParameter("NumRows", "10")); } catch (Exception e) { e.printStackTrace(); }
        try { cellWidth = Integer.parseInt(this.getParameter("CellWidth", "40")); } catch (Exception e) { e.printStackTrace(); }
        try { numCols = Integer.parseInt(this.getParameter("NumCols", "2")); } catch (Exception e) { e.printStackTrace(); }
        try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try { showRowHeader = Boolean.valueOf(this.getParameter("ShowRowHeader", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try { showColHeader = Boolean.valueOf(this.getParameter("ShowColHeader", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try { lastOnTop = Boolean.valueOf(this.getParameter("LastOnTop", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try { showScrollBars = Boolean.valueOf(this.getParameter("ShowScrollBars", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try { sizeToFit = Boolean.valueOf(this.getParameter("SizeToFit", "false")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try { dataFile = this.getParameter("DataFile", ""); } catch (Exception e) { e.printStackTrace(); }
        grid = new SGridPanel(this, showScrollBars);
        try {
          jbInit();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        grid.setDefaultCellWidth(cellWidth);
        grid.setLastOnTop(lastOnTop);
        grid.setShowRowHeader(showRowHeader);
        grid.setShowColHeader(showColHeader);
        grid.initCells(numRows,numCols);
        grid.setShowControls(showControls);
    }
//Component initialization

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        this.add(grid, BorderLayout.CENTER);
    }

  /**
   * Counts the number of applets on a page.
   *
   * @param func
   * @param vars
   *
   * @return the applet count
   * @y.exclude
   */
  public int getAppletCount() {
    if(firstTime) return 0;
    else return super.getAppletCount();
  }



  /**
   * @y.exclude
   */
  public void start(){
        //super.start();
        validate();
        if(sizeToFit)sizeToFit(true);
        if(firstTime){
            firstTime=false;
            if(dataFile!=null && !dataFile.equals(""))grid.loadFile(1,dataFile);
        }
        super.start();

        //addDatum(1,2);
       // int sid=getSeriesID(1);
        //String vars=getSourceData(sid,"x");
       // System.out.println(vars);

    }

    /**
     * @y.exclude
     * @return the info
     */
    public final String getAppletInfo() {
        return "Data Table Applet by W. Christian";
    }

    /**
     * @y.exclude
     * @return the info
     */
    public String[][] getParameterInfo() {
        String pinfo[][] =
    {
      {"NumRows", "int", "Number of rows"},
      {"NumCols", "int", "Number of cols."},
      {"ShowControls", "boolean", "Show controls at start up."},
      {"ShowRowHeader", "boolean", "Show the fixed row header"},
      {"ShowColHeader", "boolean", "Show the fixed col header"},
      {"SizeToFit", "boolean", "Fit the table to size of the panel."},
    };
        return pinfo;
    }

  /**
   * Read a data file of data values into a series.
   *
   * @param              sid The series id.
   * @param              fileName The file name
   */
  public void loadDataFile(int sid, String fileName){
     grid.loadFile(sid,fileName);
  }

/**
     * Clear the data from a series.  Series properties such as color and style
     * remain unchanged.
     *
     * @param              Series ID
     */
    public void clearSeries(int s){ grid.clearSeries(s);}

/**
     * Clear the data from all series.  Series properties such as color and style
     * remain unchanged.
     *
     * @param              Series ID
     */
    public void clearAllSeries(){ grid.clearAllSeries();}

/**
     * Add a data point to the series.
     *
     * @param              id series ID
     * @param              x x value
     */
    public void addDatum(int sid, double x){
       grid.addDatum( sid, x, 0 );
    }

/**
     * Get the object identifier for the table. This identifier is used to make a data connection.
     *
     * @return          The id.
     */
    public int getTableID(){return grid.hashCode();}


/**
   * Repaint every time data is added.
   *
   * @param              autoRefresh Automatic repaint?
   */
  public void setAutoRefresh(boolean autoRefresh){
      grid.setAutoRefresh( autoRefresh);
  }

/**
     * Attempts to fit the size of the grid to the window.  Works best if the
     * ShowScrollBara parameter is set to false.
     */
    public void sizeToFit(boolean stf){ grid.sizeToFit(stf);}


/**
     * Clears the data from the grid and sets the stride to 1 for all series.
     */
    public void setDefault(){ grid.setDefault();}

/**
     * Do not register all data as entries in the table.  This is useful when
     * the data rate is too high.
     * The first dat point is always registered.
     *
     * @param              sid the series number
     * @param              stride The number of data points to skip before
     *                     registering.
     */
    public void setDataStride(int sid, int stride){grid.setDataStride(sid, stride);}

/**
     * Get the object identifier for the series from the series number.  First column is series number 1.
     *
     * @param              sid the series number
     * @return             the objevct identifier
     */
    public int getSeriesID(int sid){return grid.getSeriesID(sid);}

/**
     * Get the width of the cells in a column.
     *
     * @param              sid the series number
     * @return             The width of the cells.
     */
    public int getSeriesWidth(int sid){return grid.getSeriesWidth(sid);}

/**
     * Set the width of the cells in a column.
     *
     * @param              id the series number
     * @param              width The width of the cells.
     */
    public void setSeriesWidth(int id, int width){grid.setSeriesWidth(id, width);}

/**
     * Set the column header for the series.  Series 0 is the A column.  Series
     * 1 is the B column. Etc.
     *
     * @param              sid the series number
     * @param              str the header.
     */
    public boolean setSeriesLabel(int sid, String str){ // add 1 to sereis to find correc col.
       return grid.setSeriesLabel(sid,str);
    }

   /**
     * Set the numeric format for the display of numbers using the series identifier.
     *
     * @param              sid the series number
     * @param              str the format string  For example: %-+6.2f
     */
    public void setNumericFormat(int sid, String str){
       grid.setNumericFormat(sid,str);
    }

    /**
     * Set the numeric format for the display of numbers using the object identifier.
     *
     * @param   id the object identifier
     * @param   str the format string  For example: %-+6.2f
     *
     * @return  true if successful
     */
    public boolean setFormat(int id, String str){
       SDataSource ds=SApplet.getDataSource(id);
       if(ds == null  && id==0){
           return grid.setFormat(str);
       }
       return grid.setFormat(ds, str);
    }

    /**
     * Highlight a cell when it is clicked to show that it is active.
     * The value of the active cell can be read  by getActiveCellValue
     *
     * @param    show true if cell should highlight on click
     */
    public void setShowActiveCell(boolean show){
       grid.setShowActiveCell(show);
    }

    /**
     * Read the value of the active cell.
     * The active cell is selected with a mouse click.  It is then highlighed on the screen.
     *
     */
    public double getActiveCellValue(){
       return grid.getActiveCellValue();
    }

    /**
     * Read the row of the active cell.
     * The active cell is selected with a mouse click.  It is then highlighed on the screen.
     *
     */
    public int getActiveCellRow(){
       return grid.getActiveCellRow();
    }

    /**
     * Read the row of the active cell.
     * The active cell is selected with a mouse click.  It is then highlighed on the screen.
     *
     */
    public int getActiveCellCol(){
       return grid.getActiveCellCol();
    }

    /**
     * Set the value of the active cell.
     * The active cell is selected with a mouse click.  It is then highlighed on the screen.
     *
     * @return true if the cell exists false otherwise
     */
    public boolean setActiveCellValue(double val){
       return grid.setActiveCellValue(val);
    }

    /**
     * Set the active cell.
     * The active cell is then highlighed on the screen.
     *
     * @param row the row
     * @param col the column
     *
     * @return true if the cell exists false otherwise
     */
    public boolean setActiveCell(int row, int col){
       return grid.setActiveCell(row, col);
    }
}

