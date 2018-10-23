package edu.davidson.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;

import java.awt.*;

import edu.davidson.display.Format;

public class SGrid extends Panel {
	public static boolean isJS = /** @j2sNative true || */ false;
    SGridPanel gridpanel=null;
    int nRows=50, nCols = 3;
    int preferredWidth=20;
    int preferredHeight=20;
    int currentWidth=0, currentHeight = 0;
    int defaultCellWidth=40;
    int defaultCellHeight=20;
    private Cell[][] cells =  null;
    Image osi=null;
    Font font=new Font("TimesRoman",Font.PLAIN,12);
    Font hlFont=new Font("TimesRoman",Font.BOLD,12);
    Color silver=new Color(224,224,224);
    Cell hlCell=null;
    boolean showColHeader=true;
    boolean showRowHeader=true;
    boolean autoscaleRows=false;
    boolean autoscaleCols=false;
    boolean autoRefresh=true;
    boolean lastOnTop=false;
    boolean showHLCell=false;
    int maxRow=1;
    Format  defaultFormat= new Format("%-+6.2f");
    String[] colLabels = null;
    int[] stride=null;
    int[] skipCounter=null;
    int[] nextRow=null;

    public SGrid(SGridPanel gp){
        this();
        gridpanel=gp;
    }

    public SGrid(int nr, int nc) {
        try{jbInit();
        }catch (Exception e) {e.printStackTrace();}
        setBackground(silver);
        nRows=nr+1;
        nCols=nc+1;
        initCells(nRows,nCols);
    }

    public SGrid() {
        try{jbInit();
        }catch (Exception e) {e.printStackTrace();}
        setBackground(silver);
        initCells(nRows,nCols);
    }

    public void addDatum(int id, double x ){
        if(id>=nCols){
            System.out.println("Error: Data table initialized with insufficient number of rows.");
            return;
        }
        if(lastOnTop){addOnTop(id,x); return;}
        skipCounter[id]--;  // decrement the counter
        if(skipCounter[id]>0) return;
        skipCounter[id]=stride[id];
        //if(autoscaleRows && nextRow[id]==nRows) nRows=Math.min(maxRow,nRows+1);
        if(nextRow[id]==nRows) return;  // add autoscale feature to increase the size.
        cells[ nextRow[id] ][id].setValue(x);
        if(nextRow[id]<nRows)nextRow[id]++;
        if(autoRefresh)paintOsi();
        //osi=null;
        //invalidate();
        //if(autoRefresh)repaint();
    }

    public void addData(int id, double x[]){
        clearSeries(id);
        for( int i=1; i<Math.min(x.length+1,maxRow); i+=stride[id]){
            cells[i][id].setValue(x[i-1]);
        }
        // added 05/10/2013
       if(autoRefresh) paintOsi();
       // osi=null;
      //  invalidate();
      // if(autoRefresh)repaint();

    }

    public void addOnTop(int id, double x ){
        skipCounter[id]--;  // decrement the counter
        if(skipCounter[id]>0) return;
        skipCounter[id]=stride[id];
        if(nextRow[id]<maxRow-1) nextRow[id]++;
        for(int i=nextRow[id]; i>1;i--) cells[i][id].setValue(cells[i-1][id].str);
        cells[1][id].setValue(x);

    }

    void setDefaultCellWidth(int dcw){
        defaultCellWidth=dcw;
    }
    public void setDefault(){
        for(int i=1; i<nRows; i++)
            for(int j=1;j<nCols; j++){
                cells[i][j].str="";
                cells[i][j].value=0;
                //cells[i][j].paintOSI();           // SetGridSize will clear osi.
                //if(autoRefresh)cells[i][j].paint();
            }
        for(int j=1;j<nCols; j++){
           nextRow[j]=1;
           skipCounter[j]=1;
           stride[j]=1;
        }
        setGridSize();
    }

    synchronized void sizeToFit(){
        if(nCols<2 || nRows < 2) return;   // need gutter plus one row of data.
        int gridWidth=this.getSize().width;
        int gridHeight=this.getSize().height;
        int xGutter=cells[0][0].width;
        int yGutter=cells[0][0].height;
        if(!showRowHeader)xGutter=0;
        if(!showColHeader)yGutter=0;
        int newWidth=(gridWidth-xGutter)/(nCols-1);
        int newHeight=(gridHeight-yGutter)/(nRows-1);
        currentWidth=0; currentHeight=0;
        for(int i=0; i<maxRow; i++){
            currentWidth=0;
            for(int j=0; j<nCols; j++){
               if(j==0)cells[i][j].width=xGutter;
                  else cells[i][j].width=newWidth;
               if(i==0)cells[i][j].height=yGutter;
                  else cells[i][j].height=newHeight;
               currentWidth+=cells[0][j].width;
            }
            currentHeight+=cells[i][0].height;
        }
        osi=null;
        invalidate();
       if(autoRefresh)repaint();
    }


    synchronized void setGridSize(){
        currentWidth=0; currentHeight=0;
        for(int i=0; i<maxRow; i++){
            currentWidth=0;
            for(int j=0; j<nCols; j++){
               if(i==0)cells[i][j].width=40;
                   else cells[i][j].width=defaultCellWidth;
               if(j==0)cells[i][j].height=20;
                   else cells[i][j].height=defaultCellHeight;
               currentWidth+=cells[0][j].width;
            }
            currentHeight+=cells[i][0].height;
        }
        osi=null;
        invalidate();
        if(autoRefresh)repaint();
    }

    public boolean clearSeries(int id){
        if(id<1 || id>=nCols) return false;
        for(int i=1; i<nRows; i++){
                cells[i][id].str="";
                cells[i][id].paintOSI();
                if(autoRefresh)cells[i][id].paint();
        }
        nextRow[id]=1;
        skipCounter[id]=1;
        return true;
    }

    public boolean clearAllSeries(){
      for(int j=1; j<nCols; j++){
        for(int i=1; i<nRows; i++){
                cells[i][j].str="";
                cells[i][j].paintOSI();
                if(autoRefresh)cells[i][j].paint();
        }
        nextRow[j]=1;
        skipCounter[j]=1;
      }
        return true;
    }

    public int getColWidth(int id){
        if(id<0 || id>=nCols || nRows<1) return 0;
        return cells[0][id].width;
    }

    public boolean setColWidth(int id, int newWidth){
        if(id<0 || id>=nCols) return false;
        currentWidth=currentWidth-cells[0][id].width+newWidth;
        for(int i=0; i<nRows; i++){
                cells[i][id].width=newWidth;
        }
        osi=null;
        invalidate();
        if(autoRefresh)repaint();
        return true;
    }

    public final boolean setFormat(String str){
     try{defaultFormat= new Format(str);}
     catch (IllegalArgumentException e){return false;}
     return true;
    }

    public boolean setNumericFormat(int id, String str){
        if(id<1 || id>=nCols|| nRows<2) return false;
        try{  cells[1][id].format= new Format(str); }
            catch (IllegalArgumentException e){return false;}
        cells[1][id].paintOSI();
        if(autoRefresh)cells[1][id].paint();
        for(int i=2; i<nRows; i++){
                cells[i][id].format= cells[1][id].format;
                cells[i][id].paintOSI();
                if(autoRefresh) cells[i][id].paint();
        }
        return true;
    }

    void initCells(int r, int c){
        char clabel='A';
        clabel--;

        nRows=r; nCols=c;
        if(autoscaleRows)maxRow=nRows+10;
            else maxRow=nRows;
        skipCounter= new int[nCols];
        stride= new int[nCols];
        colLabels= new String[nCols];
        nextRow = new int[nCols];
        cells =  new Cell[maxRow][nCols];
        currentWidth=0; currentHeight=0;
        for(int i=0; i<maxRow; i++){
            currentWidth=0;
            for(int j=0; j<nCols; j++){
               cells[i][j]=new Cell(i,j);
               currentWidth+=cells[0][j].width;
               if(i==0){
                   cells[0][j].str=(new Character(clabel)).toString();
                   cells[0][j].bgColor=Color.lightGray;
                   cells[0][j].centered=true;
                   colLabels[j]=cells[0][j].str;
                   skipCounter[j]=1;
                   stride[j]=1;
                   nextRow[j]=1;
                   clabel++;
               }
            }
            cells[i][0].str=""+i;
            cells[i][0].bgColor=Color.lightGray;
            currentHeight+=cells[i][0].height;
        }
        cells[0][0].str="";
        if(gridpanel!=null) gridpanel.validate();
    }

    Cell getCellFromPix(int xpix,int ypix){
        int x=0,y=0;
        int startRow=0, startCol=0;
        if(!showRowHeader)startCol=1;
        if(!showColHeader)startRow=1;
        if(xpix>=currentWidth || ypix>=currentHeight) return null;
        int row=-1, col=-1;
        for(int i=startRow; i<nRows; i++){
            y+=cells[i][0].height;
            if(y>=ypix){row=i;  break;}
        }
        for(int j=startCol; j<nCols; j++){
            x+=cells[0][j].width;
            if(x>=xpix){col=j;  break;}
        }
        if(row>=0 && col >=0) return cells[row][col];
            else return null;
    }

    public int getCol(String str){
       for(int j=0; j<nCols; j++){
          if(colLabels[j].equals(str)) return j;
       }
       return -1;
    }

    //public void update(Graphics g){ paint(g);}

    public void highlightRow(int i, boolean hl){
       if(i>=nRows) return;
       for(int j=0; j<nCols; j++){
          cells[i][j].highlight=hl;
          cells[i][j].paintOSI();
          if(autoRefresh)cells[i][j].paint();
       }
    }

    public void setDataStride(int id, int s){
        this.stride[id]=Math.max(1,s);
        skipCounter[id]=1;  // force the next data point to register.
    }

    public boolean setColLabel(int j, String str){
        if(j<0 || j>=nCols) return false;
        colLabels[j]=str;
        cells[0][j].str=str;
        cells[0][j].paintOSI();
        if(autoRefresh)cells[0][j].paint();
        return true;
    }

    public void setAutoRefresh(boolean auto){
       autoRefresh=auto;
       if(autoRefresh){
            paintOsi();
           //repaint();
       }
    }

    public void setBounds(int x, int y, int width, int height){
      super.setBounds(x, y, width, height);
      if(width>2 && height>2){
        synchronized(this){osi=null;}
      }
    }

    public void setBounds(Rectangle r){
      super.setBounds(r);
      if(r.width>2 && r.height>2){
        synchronized(this){osi=null;}
      }
    }

    public boolean setCell(int i, int j, String s){
       if(i<0 || j<0 || i>nRows || j>nCols) return false;
       if(i==0 && j==0)  return false;
       cells[i][j].setValue(s);
       return true;
    }

    public double getCellValue(int i, int j){
       if(i<0 || j<0 || i>nRows || j>nCols) return  Double.NaN;
       if(i==0 && j==0) return  Double.NaN;
       return cells[i][j].getValue();
    }

    public double getActiveCellValue(){
      if(hlCell==null) return Double.NaN;
      return hlCell.getValue();
    }

    public boolean setActiveCellValue(double val){
      if(hlCell==null) return false;
      hlCell.setValue(val);
      return true;
    }

    public int getActiveCellRow(){
      if(hlCell==null) return -1;
      return hlCell.row;
    }

    public int getActiveCellCol(){
      if(hlCell==null) return -1;
      return hlCell.col;
    }

    public int getNextRow(int sid){
        return nextRow[sid];
    }

    public void setHighlight(int i, int j){
       if(hlCell!=null){
           if(hlCell.row==0)highlightCol(hlCell.col,false);
           else if (hlCell.col==0)highlightRow(hlCell.row,false);
           else{
               hlCell.highlight=false;
               hlCell.paint();
               hlCell.paintOSI();
           }
       }
       hlCell=null;
       if(i<0 || j<0 || i>nRows || j>nCols) return;
       if(i==0 && j==0)  return;
       hlCell=cells[i][j];
       if(hlCell.row==0)highlightCol(hlCell.col,true);
         else if (hlCell.col==0)highlightRow(hlCell.row,true);
         else{
               hlCell.highlight=true;
               hlCell.paint();
               hlCell.paintOSI();
         }
    }

    public void highlightCol(int j, boolean hl){
       if(j>=nCols) return;
       for(int i=0; i<nRows; i++){
           cells[i][j].highlight=hl;
           cells[i][j].paintOSI();
           if(autoRefresh) cells[i][j].paint();
       }
    }
    
    // paint without double buffering
    public void paintNEW(Graphics g){
    	super.paint(g);
        Rectangle r=getBounds();
        if( r.width==0 || r.height==0)return;
        if(nCols==0 || nRows==0){
            g.fillRect(r.x,r.y,r.width,r.height);
            return;
        }
        int w=currentWidth;
        int h=currentHeight;
        if(w<=1 || h<=1) return;
        if(!showRowHeader)w-= cells[0][0].width;
        if(!showColHeader)h-= cells[0][0].height;
        int count=0;
        synchronized(this){
          //if (osi==null){
             osi = createImage(w,h);
             if (osi==null) return;
             Graphics osg = osi.getGraphics();// a graphics context for the  off screen image
             if (osg==null) return;
             paintOsi(osg);
             osg.dispose();
             osg=null;
          //}
          g.drawImage(osi, 0, 0, w, h, this);
        }
     }


    public void paint(Graphics g){
       Rectangle r=getBounds();
       if( r.width==0 || r.height==0)return;
       if(nCols==0 || nRows==0){
           g.fillRect(r.x,r.y,r.width,r.height);
           return;
       }
       int w=currentWidth;
       int h=currentHeight;
       if(w<=1 || h<=1) return;
       if(!showRowHeader)w-= cells[0][0].width;
       if(!showColHeader)h-= cells[0][0].height;
       int count=0;
       synchronized(this){
         if (osi==null){
            osi = createImage(w,h);
            if (osi==null) return;
            if(!isJS) {
            while( !prepareImage(osi,this) && count<10)
               try{
                  count++;
                  Thread.sleep(20);
               }catch (Exception e){;}
            }
            Graphics osg = osi.getGraphics();// a graphics context for the  off screen image
            if (osg==null) return;
            paintOsi(osg);
            osg.dispose();
            osg=null;

         }
         g.drawImage(osi, 0, 0, w, h, this);
       }
    }

    synchronized public void paintOsi(){
       Graphics g=getGraphics();
       if(g==null) return;
       paintOsi(g);
       g.dispose();
    }

    synchronized public void paintOsi(Graphics g){
        int x=0,y=0;
        int startRow=0, startCol=0;
        if(!showRowHeader)startCol=1;
        if(!showColHeader)startRow=1;
        for(int i=startRow; i<nRows; i++){
            x=0;
            for(int j=startCol; j<nCols; j++){
                cells[i][j].paint(g,x,y);
                x+=cells[0][j].width;
            }
            y+=cells[i][0].height;
        }
        //System.out.println("x:"+x);
    }

    public Dimension getPreferredSize(){
        int w=currentWidth;
        int h=currentHeight;
        if(!showRowHeader)w-=cells[0][0].width;
        if(!showColHeader)h-=cells[0][0].height;
        return new Dimension(w,h);
    }


    private void jbInit() throws Exception {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                this_mouseClicked(e);
            }
        });
    }

    void this_mouseClicked(MouseEvent e) {
      if(!showHLCell) return;
       if(hlCell!=null){
           if(hlCell.row==0)highlightCol(hlCell.col,false);
           else if (hlCell.col==0)highlightRow(hlCell.row,false);
           else{
               hlCell.highlight=false;
               hlCell.paint();
               hlCell.paintOSI();
           }
       }
       int xpix=e.getX();
       int ypix=e.getY();
       hlCell=getCellFromPix(xpix,ypix);
       if (hlCell==null) return;
       if(hlCell.col==0 && hlCell.row==0)  hlCell=null;
       if(hlCell!=null){
           if(hlCell.row==0)highlightCol(hlCell.col,true);
           else if (hlCell.col==0)highlightRow(hlCell.row,true);
           else{
               hlCell.highlight=true;
               hlCell.paint();
               hlCell.paintOSI();
           }
           if(gridpanel!=null){
             gridpanel.rowField.setText(cells[hlCell.row][0].str);
             gridpanel.colField.setText(cells[0][hlCell.col].str);
             gridpanel.cellField.setText( cells[hlCell.row][hlCell.col].str);
             gridpanel.rowField.setBackground(Color.white);
             gridpanel.colField.setBackground(Color.white);
             gridpanel.cellField.setBackground(Color.white);
           }

       }
    }

    boolean setActiveCell(int i, int j) {
       if(hlCell!=null){
           if(hlCell.row==0)highlightCol(hlCell.col,false);
           else if (hlCell.col==0)highlightRow(hlCell.row,false);
           else{
               hlCell.highlight=false;
               hlCell.paint();
               hlCell.paintOSI();
           }
       }
       if(i<0 || j<0 || i>nRows || j>nCols) return false;
       if(i==0 && j==0)  return false;
       hlCell=cells[i][j];
       if(hlCell.col==0 && hlCell.row==0)  hlCell=null;
       if(hlCell!=null){
           if(hlCell.row==0)highlightCol(hlCell.col,true);
           else if (hlCell.col==0)highlightRow(hlCell.row,true);
           else{
               hlCell.highlight=true;
               hlCell.paint();
               hlCell.paintOSI();
           }
           if(gridpanel!=null){
             gridpanel.rowField.setText(cells[hlCell.row][0].str);
             gridpanel.colField.setText(cells[0][hlCell.col].str);
             gridpanel.cellField.setText( cells[hlCell.row][hlCell.col].str);
             gridpanel.rowField.setBackground(Color.white);
             gridpanel.colField.setBackground(Color.white);
             gridpanel.cellField.setBackground(Color.white);
           }

       }
       return true;
    }

    void setShowActiveCell(boolean show){
        showHLCell=show;
        if(show || hlCell==null ) return;
        // highlight has been turned off so we need to clear the old cell.
        if(hlCell.row==0)highlightCol(hlCell.col,false);
        else if (hlCell.col==0)highlightRow(hlCell.row,false);
        else{
               hlCell.highlight=false;
               hlCell.paint();
               hlCell.paintOSI();
        }
        hlCell=null;
    }
    void setShowColHeader(boolean show){
        if(showColHeader==show)return;
        showColHeader=show;
        osi=null;
        invalidate();
        if(autoRefresh)repaint();
    }
    void setShowRowHeader(boolean show){
        if(showRowHeader==show) return;
        showRowHeader=show;
        osi=null;
        invalidate();
        if(autoRefresh)repaint();
    }

    void setLastOnTop(boolean lot){
       lastOnTop=lot;
    }

    // inner class cell
    class Cell extends Object{
      boolean centered=false;
      int row=0;
      int col=0;
      int x=0;   // position in pix units
      int y=0;
      int width=defaultCellWidth;  // size in pix units
      int height=defaultCellHeight;
      String str="";
      boolean highlight=false;
      Color hlFontColor=Color.red;
      Color hlBGColor=silver;
      Color bgColor=Color.white;
      Format format=defaultFormat;
      double value=0;

      Cell(int r, int c){
         row=r;
         col=c;
      }

      void paint(Graphics g, int x, int y){
         this.x=x;
         this.y=y;
         paint(g);
      }
      void paint(){
         Graphics g=getGraphics();
         if (g==null) return;
         paint(g);
         g.dispose();
      }

      void paintOSI(){
         Graphics g=null;
         try{
           if(osi==null) return;
           g=osi.getGraphics();
           if (g==null) return;
           paint(g);
         } catch(Exception e){
         } finally{
           if(g!=null) g.dispose();
         }
      }

      void paint(Graphics g){
         if(highlight){ // set the background and then fill the rectangle.
             g.setColor(hlBGColor);
         }else{
             g.setColor(bgColor);
         }
         g.fillRect(x,y,width-1,height-1);
         if(highlight){
             g.setColor(hlFontColor);
             g.setFont(hlFont);
         }else{
             g.setColor(Color.black);
             if(row==0 || col==0) g.setFont(hlFont);
               else g.setFont(font);
         }
         g.drawRect(x,y,width-1,height-1);
         int xoff=4;
         if(centered){
             FontMetrics fm=g.getFontMetrics(g.getFont());
             xoff=(width-fm.stringWidth(str))/2;
         }
         g.drawString(str,x+xoff,y+height-5);
         g.setColor(Color.black);
      }
      synchronized void setValue(double v){
         value=v;
         if(value>1.0e127)str="NaN";
             else str=format.form(v);
         paintOSI();
         if(autoRefresh)paint();
      }
      synchronized void setValue(String s){
         try{
             value= Double.valueOf(str).doubleValue();
         }catch(NumberFormatException e){    // internet explorer does not throw a number format exception!
             value=0;
         }
         if(value>1.0e127)str="NaN";
             else str=s;
         paintOSI();
         if(autoRefresh)paint();
      }
      double getValue(){ return value;}
    }// end of cell inner class
}