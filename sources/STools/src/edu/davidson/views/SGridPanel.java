package edu.davidson.views;

import java.awt.BorderLayout;
import java.awt.Container;
//import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

//ReadFile
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.IOException;
import java.net.*;

import edu.davidson.graphics.*;
import edu.davidson.tools.*;

import a2s.*;

public class SGridPanel extends edu.davidson.graphics.EtchedBorder implements SDataListener{
    boolean showScrollBars=true;
    SApplet owner=null;
    Container scrollPane;
    //Panel scrollPane1 = new Panel();
    //ScrollPane scrollPane1 = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
    //ScrollPane scrollPane1 = new ScrollPane(ScrollPane.SCROLLBARS_NEVER);
    //ScrollPane scrollPane1 = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
    edu.davidson.graphics.EtchedBorder controlPanel = new edu.davidson.graphics.EtchedBorder();
    Panel panel2 = new Panel();
    Label label1 = new Label();
    Panel panel3 = new Panel();
    TextField colField = new TextField();
    Label label2 = new Label();
    BorderLayout borderLayout2 = new BorderLayout();
    Panel panel4 = new Panel();
    Button enterBtn = new Button();
    TextField rowField = new TextField();
    Label label3 = new Label();
    BorderLayout borderLayout1 = new BorderLayout();
    SPanel panel1 = new SPanel();
    TextField cellField = new TextField();
    BorderLayout borderLayout3 = new BorderLayout();
    boolean autosizeGrid=false;
    SGrid sgrid = new SGrid(this);
    BorderLayout borderLayout4 = new BorderLayout();


    public SGridPanel(SApplet o, boolean sb){
        showScrollBars=sb;
        if(showScrollBars) scrollPane = new ScrollPane();
            else scrollPane = new Panel();
        try {
            setLayout(new BorderLayout());  // added by W. Christian
            jbInit();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        setBackground(Color.lightGray);
        owner=o;
        try{SApplet.addDataListener(this);}catch (Exception e){e.printStackTrace();}
    }

    private void jbInit() throws Exception {
        if(!showScrollBars) scrollPane.setLayout(borderLayout4);
        panel2.setLayout(borderLayout1);
        controlPanel.setLayout(borderLayout2);
        label1.setAlignment(2);
        label1.setText("Cell:");
        colField.setText("A");
        label2.setAlignment(2);
        label2.setText("C:");
        enterBtn.setLabel("Enter");
        enterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterBtn_actionPerformed(e);
            }
        });
        rowField.setText("1");
        label3.setAlignment(2);
        label3.setText("R:");
        panel1.setVinsets(5);
        panel1.setLayout(borderLayout3);
        this.add(scrollPane, BorderLayout.CENTER);
        if(showScrollBars)scrollPane.add(sgrid, null);
            else scrollPane.add(sgrid, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.NORTH);
        controlPanel.add(panel3, BorderLayout.WEST);
        panel3.add(label3, null);
        panel3.add(rowField, null);
        panel3.add(label2, null);
        panel3.add(colField, null);
        controlPanel.add(panel2, BorderLayout.CENTER);
        panel2.add(label1, BorderLayout.WEST);
        panel2.add(panel1, BorderLayout.CENTER);
        panel1.add(cellField, BorderLayout.CENTER);
        controlPanel.add(panel4, BorderLayout.EAST);
        panel4.add(enterBtn, null);
    }

    /**
     * Set the numeric format for the display of numbers using the series identifier.
     *
     * @param              sid the series number
     * @param              str the format string  For example: %-+6.2f
     */
    public void setNumericFormat(int sid, String str){
       sgrid.setNumericFormat(sid,str);
    }

        /**
     * Highlight a cell when it is clicked to show that it is active.
     * The value of the active cell can be read  by getActiveCellValue
     *
     * @param    show true if cell should highlight on click
     */
    public void setShowActiveCell(boolean show){
       sgrid.setShowActiveCell(show);
    }

    /**
     * Read the value of the active cell.
     * The active cell is selected with a mouse click.  It is then highlighed on the screen.
     *
     */
    public double getActiveCellValue(){
       return sgrid.getActiveCellValue();
    }


    public void initCells(int r, int c){
       // add one for the header
       sgrid.initCells(r+1,c+1);
    }

    public void setAutoRefresh(boolean auto){
       sgrid.setAutoRefresh(auto);
    }

    public void setShowRowHeader(boolean showRowHeader){
       sgrid.setShowRowHeader(showRowHeader);
    }
    public void setShowColHeader(boolean showColHeader){
       sgrid.setShowColHeader(showColHeader);
    }

    public void setLastOnTop(boolean lot){
       sgrid.setLastOnTop(lot);
    }

    public void setShowControls(boolean sc){
       controlPanel.setVisible(sc);
       if(owner!=null) owner.invalidate();
    }

        /**
     * Read the row of the active cell.
     * The active cell is selected with a mouse click.  It is then highlighed on the screen.
     *
     */
    public int getActiveCellRow(){
       return sgrid.getActiveCellRow();
    }

    /**
     * Read the row of the active cell.
     * The active cell is selected with a mouse click.  It is then highlighed on the screen.
     *
     */
    public int getActiveCellCol(){
       return sgrid.getActiveCellCol();
    }

    /**
     * Set the value of the active cell.
     * The active cell is selected with a mouse click.  It is then highlighed on the screen.
     *
     * @return true if the cell exists false otherwise
     */
    public boolean setActiveCellValue(double val){
       return sgrid.setActiveCellValue(val);
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
       return sgrid.setActiveCell(row, col);
    }


    public void loadFile(int series, String file) {
        Applet applet=owner;
        if(applet==null) applet=edu.davidson.graphics.Util.getApplet(this);
        if(applet==null){
            System.out.println("File load failed. Aplet not found.");
            return;
        }
        boolean temp=sgrid.autoRefresh;
        sgrid.autoRefresh=false;
        this.clearSeries(series);
        try {
            InputStream is = new URL(applet.getDocumentBase(), file).openStream();
            readFile(series, is);
            is.close();
        } catch (Exception ex) {

            System.out.println("file load failed: " + ex.getMessage());
        }
        sgrid.autoRefresh=temp;
        if(sgrid.autoRefresh){
            sgrid.paintOsi();
        }
  }

  void readFile(int series, InputStream is) throws IOException {
        double x;
        double y;
        StreamTokenizer st = new StreamTokenizer(is);
        st.eolIsSignificant(true);
        st.commentChar('#');
        scan:
        while (st.ttype != StreamTokenizer.TT_EOF) {
            switch (st.nextToken()) {
                default:
                    break scan;

                case StreamTokenizer.TT_EOL:
                    break;
                case StreamTokenizer.TT_WORD:
                    if ("series".equals(st.sval)) {
                    st.nextToken();
                    series =(int)st.nval;
                    this.clearSeries(series);
                    break;
                    }
                case StreamTokenizer.TT_NUMBER:
                    x =st.nval;
                    addDatum(series,x,0);
                    break;
            }
            while (st.ttype != StreamTokenizer.TT_EOL && st.ttype != StreamTokenizer.TT_EOF) st.nextToken();
        }
  }

    public void setDataStride(int id, int stride){sgrid.setDataStride(id, stride);}

    public boolean setSeriesLabel(int series, String str){ // add 1 to sereis to find correc col.
       return sgrid.setColLabel(series,str);
    }

    public int  getID(){ return hashCode();}

/**
 *    Add data to the table or change data that is already displayed.
 *
*/
    public void addDatum(int id, double x, double y ){
        sgrid.addDatum(id,x);
        owner.updateDataConnections();
    }
    public void addDatum(SDataSource s, int id, double x, double y ){
        sgrid.addDatum(id,x);
        if(s.getOwner()!=owner) owner.updateDataConnections();
    }

/**
 *    Add data to the table or change data that is already displayed.
 *
*/
    public void addData(int id, double x[], double y[] ){
       sgrid.addData(id, x);
       owner.updateDataConnections();
    }
    public void addData(SDataSource s, int id, double x[], double y[] ){
       sgrid.addData(id, x);
       if(s.getOwner()!=owner) owner.updateDataConnections();
    }

/**
 *    Clears the data from the grid and sets the stride to 1 for all series.
 *
*/
    public void setDefault(){
        owner.deleteDataConnections();
        sgrid.setDefault();
        if(autosizeGrid) sgrid.sizeToFit();
        else  sgrid.setGridSize();
    }
    public void setDefaultCellWidth(int dcw){sgrid.setDefaultCellWidth(dcw);}

    public void deleteSeries(int id){sgrid.clearSeries(id); }
    public void clearSeries(int id){sgrid.clearSeries(id); }
    public void clearAllSeries(){sgrid.clearAllSeries(); }
    public void setOwner(SApplet o){owner=o;}
    public SApplet getOwner(){return owner;}


    /**
     * Set the numeric format for the display of numbers using the object identifier of the data source.
     *
     * @param   ds the data source
     * @param   fstr the format string  For example: %-+6.2f
     *
     * @return  true if successful
     */
    public boolean setFormat(SDataSource ds, String fstr){
       if(ds instanceof SeriesDataSource){
         int sid=((SeriesDataSource) ds).sid;
         return sgrid.setNumericFormat(sid, fstr);
       }
       else return false;
    }

    /**
     * Set the default format for the display of numbers.
     *
     * @param   fstr the format string  For example: %-+6.2f
     *
     * @return  true if successful
     */
    public boolean setFormat(String fstr){
         return sgrid.setFormat(fstr);
    }


/**
 *    Get the width of the cells in a column.
 *
 *    @param id        The series id.
 *    @returns int     The width of the cells.
*/
    public int getSeriesWidth(int id){return sgrid.getColWidth(id);}

/**
 *    Set the width of the cells in a column.
 *
 *    @param id        The series id.
 *    @param width     The width of the cells.
*/
    public void setSeriesWidth(int id, int width){sgrid.setColWidth(id, width);}


/**
 *    Attempts to fit the size of the grid to the window.  Works best if the ShowScrollBara parameter is set to false.
 *
*/
    public void sizeToFit(boolean stf){
        autosizeGrid=stf;
        if(stf) sgrid.sizeToFit();
        else  sgrid.setGridSize();
    }

    void enterBtn_actionPerformed(ActionEvent e) {
        int row=0;
        try{
            row= (new Integer(rowField.getText())).intValue();
            rowField.setBackground(Color.white);
        }catch(NumberFormatException ex){
           rowField.setBackground(Color.red);
           return;
        }
        int col=sgrid.getCol(colField.getText());
        if(col<0){
            colField.setBackground(Color.red);
            return;
        }else colField.setBackground(Color.white);
        sgrid.setHighlight(row,col);
        sgrid.setCell(row,col,this.cellField.getText());
    }

    /**
     * Get the object identifier for the series from the series number.  First column is series number 1.
     *
     * @param              sid The series number.
     * @return             the objevct identifier
     */
    public int getSeriesID(int sid){
        SeriesDataSource source= new SeriesDataSource(sid);
        return source.hashCode();
    }

  // inner class used for data connection to table.
  public class SeriesDataSource extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"x","dx"};
    double[][] ds=new double[1][2];  // the datasource state variables x, dx;
    int sid=0;

    SeriesDataSource(int _sid){ // Constructor
       sid=_sid;
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
       //int len=sgrid.nRows-1; // row zero is the header
       int len=sgrid.getNextRow(sid)-1; // row zero is the header
       if(len>0) ds=new double[len][2];  // the datasource state variables x,dx;
    }

    public double[][] getVariables(){
       int len=sgrid.getNextRow(sid)-1; // row zero is the header
       if(ds==null || ds.length!=len)ds=new double[len][2];  // the datasource state variables x,u;
       //if(ds.length!=sgrid.nRows-1)ds=new double[sgrid.nRows-1][2];  // the datasource state variables x,u;
       for(int i=0; i<ds.length; i++){
           ds[i][0]=sgrid.getCellValue(i+1,sid);
        }
        for(int i=0; i<ds.length-1; i++){
           ds[i][1]=ds[i+1][0]-ds[i][0];
        }
        return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return owner;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  }


}