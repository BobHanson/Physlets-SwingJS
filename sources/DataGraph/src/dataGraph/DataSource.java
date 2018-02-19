package dataGraph;

//import java.awt.*;
import a2s.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import edu.davidson.tools.*;


public class DataSource extends SApplet implements edu.davidson.tools.SDataSource{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] varStrings= new String[]{"n","x","y"};
    boolean isStandalone = false;
    double[][] variables=new double[1][3];  // the datasource state variables t,x,y,vx,vy,ax,ay;
    edu.davidson.graphics.EtchedBorder etchedBorder1 = new edu.davidson.graphics.EtchedBorder();
    Button addBtn = new Button();
    edu.davidson.display.SNumber yField = new edu.davidson.display.SNumber();
    FlowLayout flowLayout1 = new FlowLayout();
    Button clearbtn = new Button();
    BorderLayout borderLayout1 = new BorderLayout();
    edu.davidson.display.SNumber xField = new edu.davidson.display.SNumber();
    int n=0;
  Label label1 = new Label();
  Label label2 = new Label();
  edu.davidson.graphics.EtchedBorder etchedBorder2 = new edu.davidson.graphics.EtchedBorder();
  Panel panel1 = new Panel();
  Panel panel2 = new Panel();
  TextField hField = new TextField();
  TextField vField = new TextField();
  Label label3 = new Label();
  Label label4 = new Label();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
//Get a parameter value
    
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
    }

    //Construct the applet
    
    public DataSource() {
    }
//Initialize the applet
    public void setOwner(SApplet owner){;}
    public SApplet getOwner(){return this;}
    
    public void init() {
        try {
        jbInit();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        xField.setValue(0);
        yField.setValue(0);
        addDataSource(this);
    }
//Component initialization
    
    private void jbInit() throws Exception {
        etchedBorder1.setLayout(flowLayout1);
        this.setSize(new Dimension(401, 102));
    addBtn.setLabel("Add");
    yField.setText("0    ");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBtn_actionPerformed(e);
            }
        });
        clearbtn.setLabel("Clear");
    xField.setText("0    ");
    label1.setAlignment(2);
    label1.setText(" x=");
    label2.setAlignment(2);
    label2.setText(" y=");
    panel2.setLayout(borderLayout3);
    panel1.setLayout(borderLayout2);
    hField.setText("textField1");
    hField.setEditable(false);
    vField.setText("textField2");
    vField.setEditable(false);
    label3.setAlignment(1);
    label3.setText("Function 1=f(n,x,y)");
    label4.setAlignment(1);
    label4.setText("Funcion 2=g(n,x,y)");
    etchedBorder2.setLayout(borderLayout4);
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearbtn_actionPerformed(e);
            }
        });
        this.setLayout(borderLayout1);
        this.add(etchedBorder1, BorderLayout.NORTH);
    etchedBorder1.add(label1, null);
        etchedBorder1.add(xField, null);
    etchedBorder1.add(label2, null);
        etchedBorder1.add(yField, null);
    etchedBorder1.add(addBtn, null);
        etchedBorder1.add(clearbtn, null);
    this.add(etchedBorder2, BorderLayout.CENTER);
    etchedBorder2.add(panel2, BorderLayout.NORTH);
    panel2.add(label3, BorderLayout.WEST);
    panel2.add(hField, BorderLayout.CENTER);
    etchedBorder2.add(panel1, BorderLayout.SOUTH);
    panel1.add(label4, BorderLayout.WEST);
    panel1.add(vField, BorderLayout.CENTER);
    }
//Start the applet
    
    public void start() {
    }

/**
 *    Set the DataConnection for the applet.
 *
 *    @param sourceID    The id of the data source.
 *    @param listenerID  The id of the data listener.   This is usually an applet.
 *    @param seriesID    The id of the series in the data listener.
 *    @param xStr        The function of the data source variables to be plotted on the horizontal axis.
 *    @param xStr        The function of the data source variables to be plotted on the vertical axis.
*/
  public int makeDataConnection(int sourceID, int listenerID, int seriesID, String xStr, String yStr){
      vField.setText(xStr);
      hField.setText(yStr);
      return super.makeDataConnection( sourceID,  listenerID,  seriesID,  xStr,  yStr);
  }


  public double[][] getVariables(){return variables;}

  public String[] getVarStrings(){return varStrings;}


/**
 *    Delete a connection from the plot.
 *
 *    @param id   The id of the data source;
*/
  public void deleteDataConnection(SDataSource ds){  // break the connection for the data source
      super.deleteDataConnection(ds.hashCode());
  }

/**
 *    Delete a connection from the plot.
 *
 *    @param id   The id of the data source;
*/
  public void deleteDataConnections(){  // break the connection for the data source
      super.deleteDataConnections();
  }
    
//Stop the applet

    public void stop() {
    }

//Get Applet information

    public String getAppletInfo() {
        return "Applet Information";
    }
//Get parameter info
    
    public String[][] getParameterInfo() {
        return null;
    }

    void addBtn_actionPerformed(ActionEvent e) {
        variables[0][0]=n;
        n++;
        variables[0][1]=xField.getValue();
        variables[0][2]=yField.getValue();
        updateDataConnections();
    }

    void clearbtn_actionPerformed(ActionEvent e) {
        clearAllData();
        n=0;
    }
}

 