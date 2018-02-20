package edu.davidson.views;

//import java.awt.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Font;
import java.util.Enumeration;

import edu.davidson.display.Format;

import a2s.*;

/**
 * Title:        STextArea
 * Description:  TextArea with the ability to parse parameters.
 * Copyright:    Copyright (c) 2000
 * @author Wolfgang Christian
 * @version 1.0
 */

public class STextArea extends TextArea {
  public static int VARIABLE_NOT_FOUND=1;
  public static int VARIABLE_NOT_A_NUMBER=2;
  public static String END_OF_MESSAGES="=========SERIES DATA=========";
  int errorCode=0;
  String errorMsg="";
  Vector dataSeries=new Vector();
  Font font= new Font("Monospaced",Font.PLAIN,12);

  public STextArea() {
    this.setFont(font);
  }

  public double getValue(String variable){
      StringTokenizer ptokens=new StringTokenizer(this.getText(),"\n");
      int n=ptokens.countTokens();
      double value=0;
      errorCode=0;
      errorMsg="";
      for(int i=0; i<n; i++){
          String aLine=ptokens.nextToken().trim();
          if (aLine!=null && !aLine.equals("") && aLine.startsWith(variable+"=")){
              aLine=aLine.substring( variable.length()+1 );
              try { value=Double.valueOf(aLine).doubleValue(); }
                 catch (Exception e) {
                 System.out.println("Variable "+variable+ " is not a number. var="+ aLine);
                 errorCode=VARIABLE_NOT_A_NUMBER;
                 errorMsg="Variable "+variable+ " is not a number. var="+ aLine+"\n";
                 return 0;
              }
              return value;
          }
      }
      System.out.println("Variable "+variable+" not found.");
      errorCode=VARIABLE_NOT_FOUND;
      errorMsg="Variable "+variable+" not found.\n";
      return 0;
  }

  public void setNumericFormat(int sid, String str){
    Series s=this.getSeries(sid);
    s.format= new Format("%-+8.2g");
  }

  public void setSeriesCharWidth(int sid, int charwidth){
      Series s=this.getSeries(sid);
      s.charwidth=charwidth;
      setValue(null, 0);  // this will force a repaint
  }

  public void setSeriesLabel(int sid,String str){
        Series s=this.getSeries(sid);
        s.label=str;
        if(str.length()+2> s.charwidth)s.charwidth=str.length()+2;
        setValue(null, 0);  // this will force a repaint
  }

  public void setValue(String variable, double val){
      String newText="";
      String text=this.getText();
      StringTokenizer ptokens=new StringTokenizer(text,"\n");
      //this.setText("");
      int n=ptokens.countTokens();
      boolean variableFound=false;
      for(int i=0; i<n; i++){
          String aLine=ptokens.nextToken().trim();
          if(aLine.equals(STextArea.END_OF_MESSAGES)) break;
          if (aLine!=null && aLine.startsWith(variable+"=")){
              aLine=variable+"="+val;
              variableFound=true;
          }
          //this.append(aLine+"\n");
          newText= newText +aLine+"\n";
      }
      if(!variableFound && variable!=null) newText= newText +variable+"="+val+"\n"; // this.append(variable+"="+val+"\n");
      if(dataSeries.size()>0) newText= newText + END_OF_MESSAGES+"\n";//this.append(this.END_OF_MESSAGES+"\n");
      newText= appendDataSeries(newText);
      this.setText(newText);
  }

  public int getErrorCode(){return errorCode;}
  public String getErrorMsg(){return errorMsg;}


  public void setDefault(){
    this.setText("");
    errorCode=0;
    errorMsg="";
    dataSeries.removeAllElements();
  }

  public void addDatum(int sid, double x){
    Series s=getSeries(sid);
    s.addDatum(x);
    setValue(null, 0);  // this will force a repaint
  }

  public void addData(int sid, double[] x){
    Series s=getSeries(sid);
    s.addData(x);
    setValue(null, 0);  // this will force a repaint
  }

  public void clearSeries(int sid){
    Series s=getSeries(sid);
    s.clearSeries();
    setValue(null, 0);  // this will force a repaint
  }

  public void deleteAllSeries(){
     dataSeries.removeAllElements();
     setValue(null, 0);  // this will force a repaint
  }

  public void clearAllSeries(){
    Series s=null;
    for( Enumeration e=dataSeries.elements(); e.hasMoreElements();){
         s= (Series) e.nextElement();
         s.clearSeries();
    }
    setValue(null, 0);  // this will force a repaint
  }


  public void deleteSeries(int sid){
    Series s=null;
    for( Enumeration e=dataSeries.elements(); e.hasMoreElements();){
         s= (Series) e.nextElement();
         if(s.seriesID==sid){
             dataSeries.remove(s);
             return;
        }
    }
    setValue(null, 0);  // this will force a repaint
  }

  Series getSeries(int sid){
    Series s=null;
    for( Enumeration e=dataSeries.elements(); e.hasMoreElements();){
         s= (Series) e.nextElement();
         if(s.seriesID==sid) return s;
    }
    s=new Series(sid);
    dataSeries.addElement(s);
    return s;
  }

  public String appendDataSeries(String newText){
     newText+="\n";
     int numseries=dataSeries.size();
     Series series;
     String str;
     //this.append("\n");
     for(int i=0; i<numseries; i++){
        series= (Series) dataSeries.elementAt(i);
        int spaces=series.charwidth-series.label.length();
        if(spaces<1){
          str=series.label.substring(0,series.charwidth-1);
          if(spaces==1)str=str+' ';  // add the space at the end
        } else if(spaces==1){
          str=series.label+' ';
        }else{  // pad the string with extra spaces
           char[] buffer = new char[spaces];
           for(int j=0; j<spaces;j++)buffer[j]=' ';
           str=series.label + new String(buffer);
        }
        ///this.append(str);  // append the string to the text.
        newText+= str;
     }
     //this.append("\n");
     newText+="\n";
     int row=0;
     boolean moreData=true;
     while(moreData){
       moreData=false;
       for(int i=0; i<numseries; i++){
          series= (Series) dataSeries.elementAt(i);
          str=series.getStringValue(row);
         // this.append(str);
         newText+= str;
          if(row<series.nextRow) moreData=true;
       }
       //this.append("\n");  // finished row
       newText+= "\n";
       row++;
    }
    return newText;
  }

  class Series extends Object{
     double[] data=new double[64];
     int nextRow=0;
     int MAX_VALS=8192;
     int seriesID=0;
     String label="Series "+seriesID;
     int charwidth=12;
     Format  format= new Format("%-+8.2g");
     boolean replaceData=true;

     Series(int id){
       seriesID=id;
       label="Series "+seriesID;
     }

     private boolean doubleSize(int minSize){
       if(data.length>=MAX_VALS){
           System.out.println("Series in STextArea has exceeded maximum size.");
           return false;
       }
       double[] newdata=new double[2*minSize];
       System.arraycopy(data,0,newdata,0,data.length);
       data=newdata;
       return true;
     }

     void addDatum(double val){
       if(nextRow>=data.length){
         if(!doubleSize(nextRow)) return;
       }
       data[nextRow]=val;
       nextRow++;
     }

     void addData(double[] vals){
       if(replaceData)nextRow=0;  // clear out old data
       if(nextRow+vals.length>data.length){
         if(!doubleSize(nextRow+vals.length)) return;
       }
       System.arraycopy(vals,0,data,nextRow,vals.length);
       nextRow+=vals.length;
     }

     void clearSeries(){
       nextRow=0;
       data=new double[64];
     }

     String getStringValue(int r){
       String str;
       char[] buffer = new char[charwidth];
       for(int j=0; j<charwidth;j++)buffer[j]=' ';
       str= new String(buffer);
       if(r<nextRow){
         str=format.form(data[r]);
         int spaces=charwidth-str.length();
         if(spaces<0){  // fill the string with * to indicate that characters are lost
           for(int j=0; j<charwidth-1;j++)buffer[j]='*';
           str= new String(buffer)+' ';
         }else if(spaces>1){  // the string has too few characters
           buffer = new char[spaces];
           for(int j=0; j<spaces;j++)buffer[j]=' ';
           str= str + new String(buffer);
         }
       }
       return str;
     }
  }
}