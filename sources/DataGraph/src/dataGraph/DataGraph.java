
/*
**************************************************************************
**
**                      Class  dataGraph.DataGraph
**
**************************************************************************
**
** class DataGraph extends Applet
**
**
*************************************************************************/

package dataGraph;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.*;
import java.lang.Double;

import edu.davidson.tools.*;
import edu.davidson.display.*;


/**
 * This applet plots data sets and functions.  Each data set is a "series"
 * of of coordinate pairs, (x,y).  A series is identified by a series
 * number.
 * Each series a unique color and style.
 * Whenever data is added, the appropriate series number must be specified.
 * <p>The following embedding parameters are defined:</p>
 * <div align="center">
 * <center>
 * <table border="2" width="508" height="212">
 * <tr>
 * <th align="center" width="70" height="19">Parameter</th>
 * <th align="center" width="47" height="19">&nbsp;Value </th>
 * <th align="center" width="43" height="19">&nbsp;Type </th>
 * <th align="center" width="318" height="19">Description</th>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">Function</td>
 * <td align="center" width="47" height="19">null</td>
 * <td width="43" height="19">string</td>
 * <td width="318" height="19">An analytic function, f(x).</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">XMin</td>
 * <td align="center" width="47" height="19">-1.0</td>
 * <td width="43" height="19">double</td>
 * <td width="318" height="19">Minimum value along x axis.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">XMin</td>
 * <td align="center" width="47" height="19">1.0</td>
 * <td width="43" height="19">double</td>
 * <td width="318" height="19">Maximum value along x axis.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">YMin</td>
 * <td align="center" width="47" height="19">-1.0</td>
 * <td width="43" height="19">double</td>
 * <td width="318" height="19">Minimum value along y axis.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">YMax</td>
 * <td align="center" width="47" height="19">1.0</td>
 * <td width="43" height="19">double</td>
 * <td width="318" height="19">Maximum value along y axis.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">AutoScaleX</td>
 * <td align="center" width="47" height="19">true</td>
 * <td width="43" height="19">boolean</td>
 * <td width="318" height="19">Autoscale the x axis.</td>
 * </tr>
 * <tr>
 * <td align="center" width="70" height="19">AutoScaleY</td>
 * <td align="center" width="47" height="19">true</td>
 * <td width="43" height="19">boolean</td>
 * <td width="318" height="19">Autoscale the y axis.</td>
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
 * <p>A DataTable can be used as a data listener by obtaining its object
 * identifier and passing this value to a data connection.&nbsp; This value
 * is obtained using</p>
 * <pre>getGraphID().</pre>
 * <p>A data series is a data source.&nbsp; This enables other data
 * listeners to change whenever data is added to a series.&nbsp; This
 * interface, SDataSource, enables inter-applet data passing between
 * Physlets.</p>
 * <div align="center">
 * <center>
 * <table border="2" height="115">
 * <tr>
 * <th height="19" align="center">Object</th>
 * <th height="19" align="center">Identifier</th>
 * <th height="19" align="center">Variables</th>
 * </tr>
 * <tr>
 * <td height="38">series</td>
 * <td height="38">id=getSeriesID(int sid</td>
 * <td height="38">x,y,u,v<br>
 * Note: u and v are the differences between neighboring data points, <font
 * face="Symbol">D</font>x, <font face="Symbol">D</font>y.</td>
 * </tr>
 * <tr>
 * <td height="15">clock</td>
 * <td height="15">id=getClockID()</td>
 * <td height="15">t</td>
 * </tr>
 * </table>
 * </center>
 * </div>
 * <p>&nbsp;</p>
 *
 * @author             Wolfgang Christian
 * @version            $Revision: 0.9c $, $Date: 1999/07/21 08:00:00 $
 */

public class DataGraph extends SApplet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String button_clear_data="Clear";
    private String button_clear_function="Clear";
    private String button_reset="Reset";
    private String button_add="Add";
    private String button_plot="Plot";
    private String label_time="Time: ";
    boolean firstTime=true;
    boolean showControls = true;
    boolean autoX, autoY;
    String function;
    double xmin;
    double xmax;
    double ymin;
    double ymax;
    String dataFile;
    edu.davidson.graphics.EtchedBorder etchedBorder1 = new edu.davidson.graphics.EtchedBorder();
    edu.davidson.display.SGraph graph = new edu.davidson.display.SGraph(this);
    Button clearSeriesBtn = new Button();
    BorderLayout borderLayout1 = new BorderLayout();
    edu.davidson.graphics.EtchedBorder etchedBorder2 = new edu.davidson.graphics.EtchedBorder();
    edu.davidson.graphics.EtchedBorder etchedBorder3 = new edu.davidson.graphics.EtchedBorder();
    Button addDatumBtn = new Button();
    Button addFuncBtn = new Button();
    Button clearFuncBtn = new Button();   
    TextField funcField = new TextField();
    
    SNumber yField = new SNumber();
    SNumber xField = new SNumber();
    BorderLayout borderLayout2 = new BorderLayout();
    Label label1 = new Label();
    Panel border1 = new Panel();
    FlowLayout flowLayout1 = new FlowLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    Panel border2 = new Panel();
    Panel border3 = new Panel();
    Label label2 = new Label();
    Label label3 = new Label();
    BorderLayout borderLayout4 = new BorderLayout();
    FlowLayout flowLayout2 = new FlowLayout();
    Panel panel1 = new Panel();
    Panel panel2 = new Panel();
    GridLayout gridLayout1 = new GridLayout();
    BorderLayout borderLayout5 = new BorderLayout();
    BorderLayout borderLayout6 = new BorderLayout();
    Button button4 = new Button();
    //Construct the applet

    /**
     * @y.exclude
     */
    public DataGraph() {
//    	funcField.addKeyListener(new KeyListener(){
//
//			@Override
//			public void keyTyped(KeyEvent e) {
//				System.out.println(e.getKeyCode() + " " + (0 + e.getKeyChar()) + " typed");
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e) {
//				System.out.println(e.getKeyCode() + " " + (0 + e.getKeyChar()) + " pressed");
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//				System.out.println(e.getKeyCode() + " " + (0 + e.getKeyChar()) + " released");
//			}
//    		
//    	});
    }

    protected void setResources(){
      button_clear_data=localProperties.getProperty("button.clear_data",button_clear_data);
      button_clear_function=localProperties.getProperty("button.clear_function",button_clear_function);
      button_reset=localProperties.getProperty("button.reset",button_reset);
      button_add=localProperties.getProperty("button.add",button_add);
      button_plot=localProperties.getProperty("button.plot",button_plot);
      label_time=localProperties.getProperty("label.time",label_time);
      graph.label_time=label_time;
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
    public void init() {
        initResources(null);
        try { function = this.getParameter("Function", ""); } catch (Exception e) { e.printStackTrace(); }
        try { xmin = Double.valueOf(this.getParameter("XMin", "-1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
        try { xmax = Double.valueOf(this.getParameter("XMax", "1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
        try { ymin = Double.valueOf(this.getParameter("YMin", "-1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
        try { ymax = Double.valueOf(this.getParameter("YMax", "1")).doubleValue(); } catch (Exception e) { e.printStackTrace(); }
        try { dataFile = this.getParameter("DataFile", ""); } catch (Exception e) { e.printStackTrace(); }
        try { autoX = Boolean.valueOf(this.getParameter("AutoScaleX", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try { autoY = Boolean.valueOf(this.getParameter("AutoScaleY", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try { showControls = Boolean.valueOf(this.getParameter("ShowControls", "true")).booleanValue(); } catch (Exception e) { e.printStackTrace(); }
        try {jbInit();}
          catch (Exception e) {e.printStackTrace();}
        if(!showControls) etchedBorder1.setVisible(false);
        if(function!=null && !function.equals("")) funcField.setText(function);
        graph.setEnableMouse(true);
        //if(function!=null && !function.equals("")) graph.addFunction(function);
        graph.setAutoscaleX(autoX);
        graph.setAutoscaleY(autoY);
        graph.setMinMaxX(xmin,xmax);
        graph.setMinMaxY(ymin,ymax);
        if(dataFile!=null && !dataFile.equals(""))graph.loadFile(1,dataFile);
        // debug scripts go here
        //int id=this.addObject("arrow","x=0.25,y=0.25,h=0,v=0.2");
        //setDragable(id,true);
    }

    private void jbInit() throws Exception {
        etchedBorder1.setLayout(borderLayout2);
        etchedBorder1.setBackground(Color.lightGray);
        graph.setSampleData(false);
        graph.setLabelX("x");
        graph.setBorders("0,10,15,0");
        graph.setLabelY("y");
        clearSeriesBtn.setLabel(button_clear_data);
        etchedBorder3.setLayout(borderLayout3);
        etchedBorder2.setLayout(borderLayout4);
        addDatumBtn.setLabel(button_add);
        addDatumBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDatumBtn_actionPerformed(e);
            }
        });
        addFuncBtn.setLabel(button_plot);
        addFuncBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addFuncBtn_actionPerformed(e);
            }
        });
        addFuncBtn.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addFuncBtn_actionPerformed(e);
            }
        });
        addFuncBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFuncBtn_actionPerformed(e);
            }
        });
        clearFuncBtn.setLabel(button_clear_function);
        clearFuncBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFuncBtn_actionPerformed(e);
            }
        });
        funcField.setText("sin(2*pi*x)");
        label1.setAlignment(2);
        label1.setText("F(x) =");
        //border1.setThickness(1);
        flowLayout1.setHgap(0);
        flowLayout1.setVgap(0);
       // border2.setThickness(1);
      //  border3.setThickness(1);
        border2.setLayout(flowLayout2);
        border3.setLayout(gridLayout1);
        label2.setAlignment(2);
        label2.setText("x = ");
        label3.setAlignment(2);
        label3.setText("y = ");
        flowLayout2.setHgap(0);
        flowLayout2.setVgap(0);
        button4.setLabel(button_reset);
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button4_actionPerformed(e);
            }
        });
        panel1.setLayout(borderLayout6);
        panel2.setLayout(borderLayout5);
        border1.setLayout(flowLayout1);
        clearSeriesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearSeriesBtn_actionPerformed(e);
            }
        });
        this.setLayout(borderLayout1);
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
    });
    this.add(etchedBorder1, BorderLayout.SOUTH);
        etchedBorder1.add(etchedBorder3, BorderLayout.SOUTH);
        etchedBorder3.add(border1, BorderLayout.EAST);
        border1.add(addFuncBtn, null);
        border1.add(clearFuncBtn, null);
        etchedBorder3.add(label1, BorderLayout.WEST);
        etchedBorder3.add(funcField, BorderLayout.CENTER);
        etchedBorder1.add(etchedBorder2, BorderLayout.NORTH);
        etchedBorder2.add(border3, BorderLayout.CENTER);
        border3.add(panel2, null);
        panel2.add(label2, BorderLayout.WEST);
        panel2.add(xField, BorderLayout.CENTER);
        border3.add(panel1, null);
        panel1.add(label3, BorderLayout.WEST);
        panel1.add(yField, BorderLayout.CENTER);
        etchedBorder2.add(border2, BorderLayout.EAST);
        border2.add(addDatumBtn, null);
        border2.add(clearSeriesBtn, null);
        etchedBorder2.add(button4, BorderLayout.WEST);
        this.add(graph, BorderLayout.CENTER);
    }

    /**
     * Destroy all threads and cleanup the applet.
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     */
    public void destroy(){
       destroyed=true;
       setAutoRefresh(false);
       if(clock.isRunning())clock.stopClock();
       graph.destroy();
       super.destroy();
    }

  /**
   * Counts the number of applets on a page.
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
    public void start() {
       // System.out.println("start");

        if(firstTime){
          firstTime=false;
          graph.setOwner(this);
          if(function!=null && !function.equals("")) graph.addFunction(function);
        }
        super.start();

// debug scripts
/*
        setDefault();
        setMinMaxY(-20,20);
        setMinMaxX(-20,20);
        int fid=addObject("vectorfield","fx=y, fy=0, n=32");
*/
        //this.addCFunction("x","sin(x)","cos(x)");
        //this.setSketchMode(true);
        //graph.loadFile(1,"test.txt");
        //this.addObject("image","gif=wc.gif, x=1,y=6");
       //graph.setSketchMode(true);
      // this.setSeriesStyle(1,false,-3);
       //int sid=getSeriesID(1);
       //setMarkerSize(1,0.11);
       //getSourceData(sid,"x");
       //setDefault();
       //int id=this.addObject("tangent","x=0,y=0");
       //setDragable(id,true);
       //setResizable(id,true);
    }

    /**
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
   */
    public void stop() {
        super.stop();
    }

    /**
     * Gets the applet information.
     *
     * @return the information
     */
    public String getAppletInfo() {
        return  "DataGraph was written by W. Christian. Email:wochristian@davidson.edu";
    }

    /**
     * Gets the parameter inforamtion.
     * @return the information
     */
    public String[][] getParameterInfo() {
        String pinfo[][] =
    {
      {"Function", "String", "A function to plot."},
      {"XMin", "double", "Minumum X value"},
      {"XMax", "double", "Maximum X value"},
      {"YMin", "double", "Minimum Y value"},
      {"YMax", "double", "Maximum Y value"},
      {"AutoScaleX", "boolean", "Autoscale the x axis."},
      {"AutoScaleY", "boolean", "Autoscale the y axis."},
      {"ShowControls", "boolean", "Show the control buttons at the bottom of the applet."},
      {"DataFile", "String", "Data points file"},
    };
        return pinfo;
    }

    void clearSeriesBtn_actionPerformed(ActionEvent e) {
        clearSeries(1);
    }

/**
     * Get the series ID for the applet. This ID is used to make a connection to
     * a SDataSource.
     */
    public int getGraphID(){return graph.hashCode();}
    public int getSeriesID(int sid){return graph.getIDFromSID(sid);}

  /**
   * The id to the linear regression data source.
   * The source variables are m,b,dm, and db.
   *
   * @param              sid the series id.
   * @param              start the first point to use in the regression
   * @param              end the last point to use in the regression
   * @return             int the object identifier for the data source
   */
    public int getRegressionID(int sid, int start, int end){return graph.getRegressionID(sid,start,end);}


/**
     * Adds a data vector to the series.
     * Exclude the javadoc because this method should not be scripted.
     * @y.exclude
     * @param              id series ID
     * @param              x[] x vector
     * @param              y[] y vector
     *
     */
    public void addData(int sid, double x[], double y[] ){
       graph.addData( sid, x, y );
    }

/**
     * Adds a data point to the series.
     * @param              id series ID
     * @param              x value
     * @param              y value
     */
    public void addDatum(int sid, double x, double y ){
       graph.addDatum( sid, x, y );
    }

/**
     * Adds a dragable cursor to the graph.  The cursor is a data source.
     *
     * @param              x The initial x coordinate.
     * @param              y The initial y coordinate.
     * @return             int   The ID.
     */

    public int addCursor(double x, double y){
       return graph.addCursor(this, 10, x, y );
    }

    /**
 *    Adds a connector line between two objects
 *
 *    @param SApplet owner   the SApplet that created the cursor.
 *    @param int id1   the first object
 *    @param int id2   the second object
 *
 * @return the object id
*/
   public int addConnectorLine( int id1, int id2){
        return graph.addConnectorLine(this, id1,id2);
    }


  /**
   * Creates an object and adds it to this Physlet.
   * The first argument is the name of the object to be added and the second is a
   * comma-delimited list of parameters.  For example, a circle can be added a follows:
   *<p>
   * <code>addObject ("circle", "x = 0, y = -1.0, r = 10");</code>
   * </p>
   * See the supplemental documentation for a list of
   * <a href="doc-files/datagraph_addobject.html">object names and parameters.</a>
   *
   * @param              name the type of object to be created.
   * @param              parList a list of parameters to be set
   * @return             id that identifies the object.
   */
  public synchronized int addObject(String name, String parList){
     if(destroyed) return 0;
     Thing t=null;
     double x=0;
     double y=0;
     int width=20;
     int height=20;
     int r=10;
     name=name.toLowerCase().trim();
     name=SUtil.removeWhitespace(name);
     if(parList==null)parList=" ";
     String parList2=parList.trim();
     parList=SUtil.removeWhitespace(parList);
     if(name.equals("graph")){
         t=new GraphThing(this,graph);
     } else if(name.equals("box")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"w=")) width=(int)SUtil.getParam(parList,"w=");
         if( SUtil.parameterExist(parList,"h=")) height=(int)SUtil.getParam(parList,"h=");
         t=new BoxThing(this,graph,x,y,width,height);
     } else if(name.equals("rectangle")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"w=")) width=(int)SUtil.getParam(parList,"w=");
         if( SUtil.parameterExist(parList,"h=")) height=(int)SUtil.getParam(parList,"h=");
         t=new RectangleThing(this,graph,x,y,width,height);
     }else if (name.equals("protractor")) {
       int s = 40;    // the protractor length.
       double theta=0,theta0=0;
       if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
       if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
       if (SUtil.parameterExist(parList, "theta=")) {
         theta = SUtil.getParam(parList, "theta=");
       }
       if (SUtil.parameterExist(parList, "theta0=")) {
         theta0 = SUtil.getParam(parList, "theta0=");
       }

       if (SUtil.parameterExist(parList, "s=")) {
         s = (int) SUtil.getParam(parList, "s=");
       }
       ProtractorThing p = new ProtractorThing(this,graph,s,theta,theta0,x,y);
       if (SUtil.parameterExist(parList, "fixedbase")) {
         p.fixedBase=true;
       }
       if (SUtil.parameterExist(parList, "fixedlength")) {
         p.fixedlength=true;
       }
       t=p;
     }else if(name.equals("tangent")){
         double rise=20,run=20;
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"w=")) run=SUtil.getParam(parList,"w=");
         if( SUtil.parameterExist(parList,"h=")) rise=SUtil.getParam(parList,"h=");
         t=new TangentThing(this,graph,x,y,run,rise);
     }else if(name.equals("circle")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new CircleThing(this,graph,x,y,r);
     }else if(name.equals("cursor")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new MarkerThing(this,graph,2*r+1,x,y);
     }else if(name.equals("shell")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new ShellThing(this,graph,x,y,r);
     }else if(name.equals("phasewheel")){
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"r="))  r=(int)SUtil.getParam(parList,"r=");
         t=new PhaseThing(this,graph,x,y,r);
     }else if(name.equals("arrow")){
         double h=1, v=1;
         int s=4;
         if( SUtil.parameterExist(parList,"s=")) s=(int)SUtil.getParam(parList,"s=");
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"h=")) h=SUtil.getParam(parList,"h=");
         if( SUtil.parameterExist(parList,"v=")) v=SUtil.getParam(parList,"v=");
         t=new ArrowThing(this,graph,s,h,v,x,y);
         if (SUtil.parameterExist(parList, "thickness=")) {
           ((ArrowThing)t).thickness = Math.max((int)SUtil.getParam(parList, "thickness="),1);
         }
     }else if(name.equals("text") ||name.equals("calculation")){
         String txt="";
         String calc="";
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"txt=")) txt=SUtil.getParamStr(parList2,"txt=");
         if( SUtil.parameterExist(parList,"text=")) txt=SUtil.getParamStr(parList2,"text=");
         if( SUtil.parameterExist(parList,"calc=")) calc=SUtil.getParamStr(parList,"calc=");
         t=new TextThing(this,graph,txt,calc, x,y);
     }else if(name.equals("caption")){
         String txt="";
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"txt=")) txt=SUtil.getParamStr(parList2,"txt=");
         if( SUtil.parameterExist(parList,"text=")) txt=SUtil.getParamStr(parList2,"text=");
         t=new CaptionThing(this,graph,txt, x,y);
     }else if(name.equals("complexdata")){
         ComplexThing ct=new ComplexThing(this,graph);
         if( SUtil.parameterExist(parList,"centered=")){
           ct.setCentered(SUtil.getParamStr(parList,"centered=").equals("true"));
         }
         t=ct;
     }else if(name.equals("function")){
         String func="0";
         String indVar="x";
         if( SUtil.parameterExist(parList,"f=")) func=SUtil.getParamStr(parList,"f=");
         if( SUtil.parameterExist(parList,"var=")) indVar=SUtil.getParamStr(parList,"var=");
         int id=addFunction(indVar, func);
         if(( SUtil.parameterExist(parList,"xmin=")) && ( SUtil.parameterExist(parList,"xmax=")) ){
             int n=100;
             n=Math.max(graph.getSize().width/2,n);
             if( SUtil.parameterExist(parList,"n="))n=(int)SUtil.getParam(parList,"n=");
             double xmin=SUtil.getParam(parList,"xmin=");
             double xmax=SUtil.getParam(parList,"xmax=");
             graph.setFunctionRange(id,xmin,xmax,n);
         }
         if(( SUtil.parameterExist(parList,"ymin=")) && ( SUtil.parameterExist(parList,"ymax=")) ){
             double min=SUtil.getParam(parList,"ymin=");
             double max=SUtil.getParam(parList,"ymax=");
             graph.setFunctionClip(id,min,max);
         }
         return id;
     }else if(name.equals("vectorfield")){
         String fx="0";
         String fy="0";
         int n=32;
         if( SUtil.parameterExist(parList,"fx=")) fx=SUtil.getParamStr(parList,"fx=");
         if( SUtil.parameterExist(parList,"fy=")) fy=SUtil.getParamStr(parList,"fy=");
         if( SUtil.parameterExist(parList,"n="))n=(int)SUtil.getParam(parList,"n=");
         int id=graph.addVectorField(fx,fy,n);
         return id;
     }else if(name.equals("cfunction")){
         String funcRe="0";
         String funcIm="0";
         String indVar="x";
         if( SUtil.parameterExist(parList,"re=")) funcRe=SUtil.getParamStr(parList,"re=");
         if( SUtil.parameterExist(parList,"im=")) funcIm=SUtil.getParamStr(parList,"im=");
         if( SUtil.parameterExist(parList,"var=")) indVar=SUtil.getParamStr(parList,"var=");
         boolean isCentered=true;
         if( SUtil.parameterExist(parList,"centered=")){
           isCentered=SUtil.getParamStr(parList,"centered=").equals("true");
         }
         int id=graph.addCFunction(indVar, funcRe, funcIm,isCentered);
         if(( SUtil.parameterExist(parList,"xmin=")) && ( SUtil.parameterExist(parList,"xmax=")) ){
             int n=100;
             n=Math.max(graph.getSize().width/2,n);
             if( SUtil.parameterExist(parList,"n="))n=(int)SUtil.getParam(parList,"n=");
             double xmin=SUtil.getParam(parList,"xmin=");
             double xmax=SUtil.getParam(parList,"xmax=");
             graph.setFunctionRange(id,xmin,xmax,400);
         }
         return id;
     }else if(name.equals("image")){
         String file=" ";
         if( SUtil.parameterExist(parList,"x=")) x=SUtil.getParam(parList,"x=");
         if( SUtil.parameterExist(parList,"y=")) y=SUtil.getParam(parList,"y=");
         if( SUtil.parameterExist(parList,"gif=")) file=SUtil.getParamStr(parList,"gif=");
         if( SUtil.parameterExist(parList,"file=")) file=SUtil.getParamStr(parList,"file=");
         if(file==null) return 0;
         Image im=getImage(file);
         if(im!=null) t=new ImageThing(this,graph,im, x, y);
         else t=null;
     }
     if( t!=null && SUtil.parameterExist(parList,"label=")) t.setLabel(SUtil.getParamStr(parList2,"label="));
     if(t==null){
         System.out.println("Object not created. name:" + name + "parameter list:" + parList);
         return 0;
     }
     return graph.addThing(t);
  }

  /**
   *  Get an image from the code base, the document base, or the absolute URL.
   *
   * @param              file Location of image relative to the document
   *                     containing the HTML page.
   * @return             the image
   *
   */
  private Image getImage(String file){
		  Image im=null;
		  // java.io.File f=new java.io.File(file);
		try{
		      im=getImage(getDocumentBase(),file);
		} catch(Exception e){
		    im=null;
		    //System.out.println("Failed to load image file from document base.");
		}      
		if(im==null) try{
		          im=getImage(getCodeBase(),file);
		    } catch(Exception e){
		        im=null;
		        //System.out.println("Failed to load image file from code base.");
		  }
		if(im==null)try{
		      java.net.URL url= new java.net.URL(file);
		      im =getImage(url);
		    } catch(Exception e){
		        im=null;
		        //System.out.println("Failed to load image file from absolute URL.");
		    }
		if(im==null){
		      System.out.println("Failed to load image file.");
		      return im;
		  }
		  MediaTracker tracker=new MediaTracker(this);
		  try{
		        tracker.addImage(im,0);
		        tracker.waitForID(0,1000);  // wait one second
		  }catch(Exception e){
		   //return null;
		  }
		  //if(tracker.isErrorAny()) return null;
		  //if(im.getHeight(this)<1) return null;
		  return im;
   }

  /**
   * Changes the properties of an object.
   * The first argument is the object identifier.
   * The second argument is the name of the property and the third is a
   * comma-delimited list of parameters.  For example, the scale can be added a follows:
   *<p>
   * <code>set(id, "sale", "xmin=0, xmax=2, autoscalx=false");</code>
   * </p>
   *
   * @param              id the identifier of the object
   * @param              name the type of property to be created.
   * @param              parList a list of parameters
   * @return             true if successful
   */
  public synchronized boolean set(int id, String name, String parList){
     name=name.toLowerCase().trim();
     name=SUtil.removeWhitespace(name);
     //String parList2=parList.trim();;
     parList=SUtil.removeWhitespace(parList);
     String str="true";
     if(name.equals("scale")){
         if( SUtil.parameterExist(parList,"autoscalex")){
             if( SUtil.parameterExist(parList,"autoscalex=")) str=SUtil.getParamStr(parList,"autoscalex=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) graph.setAutoscaleX(false);
             else graph.setAutoscaleX(true);
         }
         if( SUtil.parameterExist(parList,"noautoscalex")){graph.setAutoscaleX(false);}
         if( SUtil.parameterExist(parList,"autoscaley")){
             if(SUtil.parameterExist(parList,"autoscaley=")) str=SUtil.getParamStr(parList,"autoscaley=");
             str=SUtil.removeWhitespace(str.toLowerCase());
             if(str.equals("false")) graph.setAutoscaleY(false);
             else graph.setAutoscaleY(true);
         }
         if( SUtil.parameterExist(parList,"noautoscaley")){graph.setAutoscaleY(false);}
         double xmin=graph.getMinX();
         double xmax=graph.getMaxX();
         if( SUtil.parameterExist(parList,"xmin="))  xmin=SUtil.getParam(parList,"xmin=");
         if( SUtil.parameterExist(parList,"xmax="))  xmax=SUtil.getParam(parList,"xmax=");
         if(( SUtil.parameterExist(parList,"xmin=")) || ( SUtil.parameterExist(parList,"xmax=")) ){
             graph.setMinMaxX(xmin,xmax);
         }
         double ymin=graph.getMinY();
         double ymax=graph.getMaxY();
         if( SUtil.parameterExist(parList,"ymin="))  ymin=SUtil.getParam(parList,"ymin=");
         if( SUtil.parameterExist(parList,"ymax="))  ymax=SUtil.getParam(parList,"ymax=");
         if(( SUtil.parameterExist(parList,"ymin=")) || ( SUtil.parameterExist(parList,"ymax=")) ){
             graph.setMinMaxY(ymin,ymax);
         }
         return true;
     }
     Thing t=graph.getThing(id);
     if(t==null){
         System.out.println("Object property not set.  Property name=" + name + "Property value=" + parList);
         return false;
     }
     return false;
  }

    /**
	 * Forces an object to follow another object on the screen.
	 *
	 * @param              masterID The id of the master object.
	 * @param              slaveID The id of the slave object.
	 * @return             true if successful.
	 */
	public boolean setAnimationSlave(int masterID, int slaveID){
          Thing master=graph.getThing(masterID);
          Thing slave=graph.getThing(slaveID);
          if(master==null || slave==null) return false;
          master.addSlave(slave);
          if(autoRefresh)graph.repaint();
        return true;
  }

/**
     * Adds an analytic function to the plot.
     *
     * @param              indVar the independent variable;
     * @param              str the function string, f(v);
     * @return             int the object ID
     *
     * @deprecated        Replaced by the addObject(String String) method.
     */
    public int addFunction(String indVar, String str){
       if(str!=null) funcField.setText(str);
       return graph.addFunction(indVar, str );
    }

    /**
     * Adds a complex function to the plot.
     *
     * @param              indVar the independent variable;
     * @param              strRe the real function string, f(v);
     * @param              strIm the imaginary function string, f(v);
     * @return             int the object ID
     *
     * @deprecated        Replaced by the addObject(String String) method.
     */
    public int addCFunction(String indVar, String strRe, String strIm){
       return graph.addCFunction(indVar, strRe, strIm, true);
    }

/**
     * Deletes a function from the plot.
     *
     * @param              id The id of the function;
     */
    public void deleteFunction(int id){
       graph.deleteFunction( id );
    }

  /**
   * Deletes an object from this Physlet.
   *
   * @param id the object identifier
   *
   * @return true if successful
   */
  public void deleteObject(int id){graph.deleteObject( id);}

/**
     * Deletes all functions from the plot.
     */
    public void deleteAllFunctions(){
       graph.deleteAllFunctions();
    }

/**
     * Creates a series and return the series ID for use by a data connection.
     * Return an existing ID if the series already exists.
     *
     * @param              series Series ID
     * @return             series    Object ID
     */
    public int createSeries(int sid){
       graph.createSeries(sid);
       return graph.getIDFromSID(sid);
    }

/**
     * Deletes a series from the graph.
     *
     * @param              Series ID
     */
    public void deleteSeries(int s){ graph.deleteSeries(s);}

/**
     * Deletes every series from the graph.
     *
     */
    public void deleteAllSeries(){ graph.deleteAllSeries();}

/**
     * Clears the data from a series.  Series properties such as color and style
     * remain unchanged.
     *
     * @param              Series ID
     */
    public void clearSeries(int s){ graph.clearSeriesData(s);}

/**
     * Clears all data from the graph.  Series properties such as color and style
     * remain unchanged.
     *
     */
    public void clearAllSeries(){ graph.clearAllSeries();}

  /*
   * Forces current data connections to send new values.
   * Removed so that the superclass is invoked

  public void updateDataConnections(){
      for(Enumeration e=dataConnections.elements() ; e.hasMoreElements(); ){
          SDataConnection dc=(SDataConnection)e.nextElement();
          dc.clearData();
          dc.registerDatum();
      }
  }
  */

  /**
   *    Makes a new data connection.
   *
   *    @param sourceID    The id of the data source.
   *    @param listenerID  The id of the data listener.   This is usually an applet.
   *    @param seriesID    The id of the series in the data listener.
   *    @param xStr        The function of the data source variables to be plotted on the horizontal axis.
   *    @param xStr        The function of the data source variables to be plotted on the vertical axis.
   * @param yStr
   *
   *    @return SDataConnection The hasCode id of the newly created data connection.
   */
  synchronized public int makeDataConnection(int sourceID, int listenerID, int seriesID, String xStr, String yStr) {
    Thing source = this.graph.getThing(sourceID);
    if(source instanceof SStepable){
      clock.addClockListener( (SStepable) source);
      source.setTime(clock.getTime());
    }
    return super.makeDataConnection(sourceID, listenerID,seriesID, xStr, yStr);
  }

/**
   * Reads a data file.
   * Data is stored as comma delimited x,y coordinate values.
   *
   * @param              sid The series id.
   * @param              fileName The file name
   */
  public void loadDataFile(int sid, String fileName){
     graph.loadFile(sid,fileName);
  }

/**
   * Plots a linear regression for the data in a series.  The regression is
   * stored as an analytic function.
   *
   * @param              sid the series id.
   * @param              start the first point to use in the regression
   * @param              end the last point to use in the regression
   * @return             int the object identifier.  Use the object id to reference
   *                     the regression as a function.
   */
  public int plotRegression(int sid, int start, int end){ return graph.plotRegression(sid, start, end);}


/**
     * Sets the autoRefresh property. AutoRefresh will repaint the graph whenever
     * data changes. This slows down the computer and may cause flicker.
     *
     * @param              auto AutoRefresh the graph?
     */
    public void setAutoRefresh(boolean auto){
        autoRefresh=auto;
        graph.setAutoRefresh(auto);
    }

/**
     * Sets the addRepeatedDataum property.  A new datum will not be added to a
     * series if this is false.
     * Default is true.
     *
     * @param              add Added repeated data values to the graph?
     */
    public void setAddRepeatedDatum(int sid, boolean add){
        graph.setAddRepeatedDatum(sid,add);
    }

/**
     * Sets the autoscaleX property for the graph.
     *
     * @param              autoOn Autoscale the x axis?
     */
    public void setAutoscaleX(boolean autoOn){graph.setAutoscaleX(autoOn);}

/**
     * Sets the autoscaleY property for the graph.
     *
     * @param              autoOn Autoscale the y axis?
     */
    public void setAutoscaleY(boolean autoOn){graph.setAutoscaleY(autoOn);}

/**
     * Sets the draw grid property for the graph.
     *
     * @param              drawOn Draw the grid?
     */
    public void setDrawGrid(boolean drawOn){graph.setDrawGrid(drawOn);}

/**
   * Sets the graph to draw lines at x=0 and y=0.
   *
   * @param              drawOn Draw the zero lines?
   */
  public void setDrawZero(boolean drawOn){graph.setDrawZero(drawOn);}

/**
   * Sets the default values.
   * Deletes functions.
   * Deletes series.
   * Deletes objects such as circles and rectangles.
   */
  public void setDefault(){
      clock.stopClock();
      clock.setTime(0);
      graph.setTimeDisplay(false);
      clock.removeClockListener(graph);
      graph.deleteAllSeries();
      graph.deleteAllFunctions();
      graph.clearAllThings();
      this.deleteDataConnections();
  }

      /**
      * Gets the height of an object.
      *
      * @param              id the object identifier
      *
      * @return the height
      */
    public double getH(int id) {
      Thing t=graph.getThing(id);
      if(t==null){
         System.out.println("Object not found in getH method.");
         return 0;
      }
      return t.getHeight();
    }

    /**
     * Changes the height of an object.
     *
     * @param              id the object identifier
     * @param              h new height in pixels
     * @return             true if successful
     */
    public boolean setH(int id, double h) {
      Thing t=graph.getThing(id);
      if(t==null){
         System.out.println("Object not found in setH method.");
         return false;
      }
      t.setHeight((int)h);
      return true;
    }

    /**
     * Gets the width of an object.
     *
     * @param              id The id of the object.
     *
     * @return the width
     */
    public double getW(int id) {

      Thing t=graph.getThing(id);
      if(t==null){
         System.out.println("Object not found in getW method.");
         return 0;
      }
      return t.getWidth();
    }

    /**
     * Changes the width of an object..
     *
     * @param              id the object identifier
     * @param              w new width in pixels
     * @return             true if successful
     */
    public boolean setW(int id, double w) {

      Thing t=graph.getThing(id);
      if(t==null){
         System.out.println("Object not found in setW method.");
         return false;
      }
      t.setWidth((int) w);
      return true;
    }

   /**
   * Gets the x world coordinate of the object.
   *
   * @return   the x value
   */
  public double  getX(int id){
      Thing t=graph.getThing(id);
      if (t==null) {
         System.out.println("Object not found in getX method.");
         return 0;
      }
      return t.getX();
  }

  /**
   * Gets the y world coordinate of the object.
   *
   * @return   the y value
   */
  public double  getY(int id){
      Thing t=graph.getThing(id);
      if(t==null){
         System.out.println("Object not found in getY method.");
         return 0;
      }
      return t.getY();
  }

    /**
     * Bug-fix to get the x of an object on Netscape and Sun.
     *
     * @param              id The id of the object.
     * @param              x  new x value
     * @return             true if successful
     */
    public double getXPos(int id) {
       return getX(id);
    }

    /**
     * Bug-fix to get the y of an object on Netscape and Sun.
     *
     * @param              id The id of the object.
     * @param              x  new x value
     * @return             true if successful
     */
    public double getYPos(int id) {
       return getY(id);
    }


  /**
   * Sets the world coordinates of the object.
   *
   * @return   the x value
   */
  public boolean  setXY(int id, double x, double y){
      Thing t=graph.getThing(id);
      if(t==null){
         System.out.println("Object not found in setX method.");
         return false;
      }
      t.setXY(x,y);
      t.updateMySlaves();
      if(!clock.isRunning())updateDataConnections();
      return true;
  }

  /**
   * Sets the x world coordinate of the object.
   *
   * @return   the x value
   */
  public boolean  setX(int id, double x){
      Thing t=graph.getThing(id);
      if(t==null){
         System.out.println("Object not found in setX method.");
         return false;
      }
      t.setX(x);
      t.updateMySlaves();
      if(!clock.isRunning())updateDataConnections();
      return true;
  }
  public boolean  setXPos(int id, double x){
    return setX(id,x);
  }

  /**
   * Sets the y world coordinate of the object.
   *
   * @return   the y value
   */
  public boolean  setY(int id, double y){
      Thing t=graph.getThing(id);
      if(t==null){
         System.out.println("Object not found in setY method.");
         return false;
      }
      t.setY(y);
      t.updateMySlaves();
      if(!clock.isRunning())updateDataConnections();
      return true;
  }
  public boolean  setYPos(int id, double y){
    return setY(id,y);
  }

 /**
   *
   * Sets the visibility of the object.
   * @param     the object id
   * @param     show object on screen?
   *
   * @return   true if successful
   */
  public boolean  setVisibility(int id, boolean show){
      Thing t=graph.getThing(id);
      if(t!=null){
        t.setVisible(show);
        if(autoRefresh)graph.repaint();
        return true;
      }
      SGraph.MathFunction func = graph.getFunction(id);
      if(func!=null){
        func.visible=show;
        if(autoRefresh)graph.repaint();
        return true;
      }
      return false;
  }

  /**
   * Offsets the object's position on the screen from its default drawing
   * position.
   *
   * @param              id The id of the object.
   * @param              xoff The x offset.
   * @param              yoff The y offset.
   * @return             True if successful.
   */
  public boolean setDisplayOffset(int id, int xOff, int yOff){
      Thing t=graph.getThing(id);
      if(t==null) return false;
      t.setDisplayOff(xOff,yOff);
      if(autoRefresh)graph.repaint();
      return true;
  }

  /**
   * Makes the object with the given id dragable.
   *
   * @param              id The id of the object.
   * @param              drag Dragable?
   * @return             true if successful.
   */
  public boolean setDragable(int id, boolean drag){
      Thing t=graph.getThing(id);
      if(t==null) return false;
      t.setDragable(drag);
      return true;
  }

  /**
   * Makes the object with the given id resizable.
   *
   * @param              id the id of the object
   * @param              resizable  frue if object can be resized
   * @return             true if successful
   */
  public boolean setResizable(int id, boolean resize){
      Thing t=graph.getThing(id);
      if(t==null) return false;
      t.setResizable(resize);
      return true;
  }

  /**
   * Enables sketching with the mouse.
   *
   * @param              sketch true will sketch
   *
   * @return  int the id of the mouse data source
   */

  public int setSketchMode(boolean sketch){
     return graph.setSketchMode(sketch);
  }

/**
   * Enables to mouse to show coordinates.
   *
   * @param              mouseOn Enable the mouse?
   */
  public void setEnableMouse(boolean mouseOn){graph.setEnableMouse(mouseOn);}


/**
   * Sets the range of an analytic function.
   *
   * @param              id The id of the function.
   * @param              xmin Minimum value of the range.
   * @param              xmax Maximum value of the range.
   * @param              n The number of data points.
   */
  public void setFunctionRange(int id, double xmin, double xmax, int n  ){
      graph.setFunctionRange(id,xmin,xmax,n);
  }

/**
   * Clips the v values on a function.
   *
   * @param              id The id of the function.
   * @param              ymin Minimum value of the range.
   * @param              ymax Maximum value of the range.
   */
  public void setFunctionClip(int id, double min, double max){
      graph.setFunctionClip(id,min,max);
  }

/**
   * Sets the y scale of the graph to the analytic function max-min.
   *
   * The Y scale of a graph only depends on the the data in the data series.
   * This method can be used to set the scale to the max-min of an analytic function.
   *
   * @param              id The id of the function.
   */

  public void setYScaleFromFunction(int id){
    graph.setYScaleFromFunction(id);
  }

/**
     * Changes the function that is being ploted.
     *
     * @param              str The function string, f(x);
     */
    public boolean setFunctionString(int id, String str){
       if(str!=null) funcField.setText(str);
       return graph.setFunctionString(id, str );
    }

    /**
     * Swaps the drawing order on the screen.
     *
     * @param              id1 The first id of a screen object.
     * @param              id2 The second id of a screen object.
     * @return             True if successful.
     */
    public synchronized boolean swapZOrder(int id1, int id2) {
        return graph.swapZOrder(id1, id2);
    }

/**
     * Get the function that is being ploted.
     *
     * @param              id The id of the function.
     * @return             str   The function string, f(x);
     */
    public String getFunctionString(int id){
       return graph.getFunctionString(id);
    }

/**
     * Changes the function's independent variable.
     *
     * @param              id The id of the function.
     * @param              str The new independent variable.
     */
    public boolean setFunctionVariable(int id, String str){
       return graph.setFunctionVariable(id, str );
    }

/**
   * Sets the gutters, i.e., margins, around the graph.  Should only be used if
   * the axes are set to be visible.
   *
   * @param              g1 The gutter on the left of the graph.
   * @param              g1 The gutter on the top of the graph.
   * @param              g1 The gutter on the right of the graph.
   * @param              g1 The gutter on the bottom of the graph.
   */
  public synchronized void setGutters(int g1, int g2, int g3, int g4){
          graph.setGutters(g1,g2,g3,g4);
  }

/**
   * Changes the display format for mouse actions.  Use UNIX fprint syntax.
   * Changed to setMouseFormat because of name conflict within swingjs.
   *
   * @param              str The format for coordinate display.
   */
  public void setMouseFormat(String str){
    graph.setFormat(str);
  }

  /**
   * Makes a copy of the graph in a separate window.
   */
  public void cloneGraph(){
    SGraphFrame graphFrame=new SGraphFrame((SGraph)graph.clone());
    graphFrame.setSize(this.getSize().width,this.getSize().height);
    graphFrame.show();
  }

  /**
   * Changes the object's format for the display of numeric data.
   *
   * An id of zero will change the display format for mouse actions.
   * Use this method to control the number of significant digits in calculations with text objects.
   * Use Unix printf conventions.  For example fstr="%6.3f"
   *
   * @param              id The id of the object.
   * @param              fstr the format string.
   * @return             True if successful.
   */
  public boolean setFormat(int id, String fstr){
      Thing t=graph.getThing(id);
      if(t==null && (id==0 || id==graph.hashCode())){
        graph.setFormat(fstr);
        return true;
      }
      boolean result=t.setFormat(fstr);
      if(autoRefresh)graph.repaint();
      return result;
  }

/**
   * Sets the x axis label.
   *
   * @param              The label value.
   */
  public void setLabelX(String s){graph.setLabelX(s); }


/**
   * Sets the y axis label.
   *
   * @param              The label value.
   */
  public void setLabelY(String s){graph.setLabelY(s); }


/**
   * Sets the marker size.
   *
   * @param              id Series id.
   * @param              size Marker size.
   */
  public void setMarkerSize(int id, double size){graph.setMarkerSize(id,size);}

/**
   * Sets the x axis minimum and maximum values.  No effect if autoscale is
   * true.
   *
   * @param              min Minumum value.
   * @param              max Maximum value.
   */
  public void setMinMaxX(double min,double max){graph.setMinMaxX(min,max);}

/**
   * Sets the y axis minimum and maximum values.  No effect if autoscale is
   * true.
   *
   * @param              min Minumum value.
   * @param              max Maximum value.
   */
  public void setMinMaxY(double min,double max){graph.setMinMaxY(min,max);}

/**
 *    Restricts the range on the x axis so that it can be no smaller than the given values.
 *
 *    @param enable true to restrict the range, false otherwise
 *    @param min            minimum value.
 *    @param max            maximum value.
*/
  public void setMinXRange(boolean enable, double min,double max){
     graph.setMinXRange(enable,min,max);
  }

/**
 *    Restricts the range on the y axis so that it can be no smaller than the given values.
 *
 *    @param enable true to restrict the range, false otherwise
 *    @param min            minimum value.
 *    @param max            maximum value.
*/
  public void setMinYRange(boolean enable, double min,double max){
     graph.setMinYRange(enable,min,max);
  }

/**
   * Sets the series legend.
   *
   * @param              id The series id.
   * @param              xpix The x postion in pixels.
   * @param              ypix The y postion in pixels.
   * @param              legend The legend.
   */
  public synchronized void setSeriesLegend(int id, int xpix, int ypix, String legend ){
      graph.setSeriesLegend(id,xpix,ypix,legend);}

/**
   * Sets the series legend color.
   *
   * @param              id The series id.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   */
  public synchronized void setSeriesLegendRGB(int id, int r, int g, int b){
      graph.setSeriesLegendColor(id, new Color(r,g,b));}

/**
   * Sets the series style.
   *
   * @param              id The series id.
   * @param              conPts Connect the points?
   * @param              m Marker style. (m=1 is cross; m=2 is square, m=3 is
   *                     circle)
   */
  public synchronized void setSeriesStyle(int id, boolean conPts, int m ){
      graph.setSeriesStyle( id,  conPts,  m ) ;
  }

/**
   * Set the series line and marker color.
   *
   * @param              id The series id.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   */
  public synchronized void setSeriesRGB(int id, int r, int g, int b){
      graph.setSeriesColor(id, new Color(r,g,b));
  }

/**
   * Sets the color of the object.
   *
   * @param              id The series id.
   * @param              r red.
   * @param              g green.
   * @param              b blue.
   */
  public synchronized void setRGB(int id, int r, int g, int b){
      Color c=new Color(r,g,b);
      graph.setObjectColor(id,c);
  }


/**
   * Disables the clearSeries function so that new data will replace a dataset when the addData method is called.
   * Eliminates screen flash since clearSeries(id) does not have to be called.
   *
   * @param              id The series id.
   * @param              auto AutoReplace
   */
  public synchronized void setAutoReplaceData(int id, boolean auto){
      graph.setAutoReplaceData(id, auto);
  }


/**
   * Sets the series so that the last point in the dataset has a distinctive
   * marker.
   *
   * @param              id The series id.
   * @param              lpm Enable the marker.
   */
  public synchronized void setLastPointMarker(int id, boolean lpm){
      graph.setLastPointMarker(id, lpm);
  }

/**
   * Sets the graph title.
   *
   * @param              str Title string.
   */
  public void setTitle(String str){graph.setTitle(str); }

  /**
   * Enables the time display in the applet window.
   *
   * @param              show boolean   Show the time?
   */
  public void setTimeDisplay(boolean show){
      graph.setTimeDisplay(show);
      if(autoRefresh)graph.repaint();
  }

/**
   * Shows or hides the the X and Y axes on the graph.
   * A program may need to set the gutters to zero eliminate extra white space.
   *
   * @param              show Show the axis?
   */
  public synchronized void setShowAxes(boolean show){
    if(graph.isShowAxis()==show) return;
    if(show){
        graph.setGutters(20,20,20,20);
        graph.drawgrid=true;
    }
    else{
        graph.setGutters(0,0,0,0);
        graph.drawgrid=false;
    }
    graph.setShowAxes(show);
  }

/**
   * Sets the aspect ratio=1 so that both axes have the same pixels per unit.
   *
   * @param              isSquare?
   */
  public synchronized void setSquare(boolean isSquare){
      graph.setSquare(isSquare);
  }

  /**
   * Sorts the series data according to the x value.
   *
   * @param   sid the series number
   * @param   sorted true if data should be sorted
   */
  public synchronized void setSorted(int sid, boolean sorted){
      graph.setSeriesSorted(sid,sorted);
  }

  /**
   * Sets strip chart mode.
   *
   * A strip chart will drop data points to keep the total number of displayed points constant.
   *
   * @param   sid the series number
   * @param   numPts the number of points in the strip chart
   * @param   stripChart true for stip chart mode
   */
  public synchronized void setStripChart(int sid,int numPts, boolean stripChart){
      graph.setSeriesStripChart(sid,numPts,stripChart);
  }

/**
 *    Gets the value on the x axis from the pixel value.
 *
 *    @param x    The pixel inside the canvas.
 *    @return     The corresponding value along the x axis.
 *
*/
  public double xFromPix(int x){
      return graph.xFromPix(x);
  }

/**
 *    Gets the value on the y axis from the pixel value.
 *
 *    @param y    The pixel inside the canvas.
 *    @return     The corresponding value along the y axis.
 *
*/
  public double yFromPix(int y){
      return graph.yFromPix(y);
  }

  /**
 *    Gets the pixel value corresponding to an x value.
 *
 *    @param x    The value along the x axis.
 *    @return     The pixel value inside the canvas.
 *
*/
  public int pixFromX(double x){
      return graph.pixFromX(x);
  }

  /**
 *    Gets the pixel value corresponding to an y value.
 *
 *    @param x    The value along the y axis.
 *    @return     The pixel value inside the canvas.
 *
*/
  public int pixFromY(double y){
      return graph.pixFromY(y);
  }

// add action methods for buttons
    void addFuncBtn_actionPerformed(ActionEvent e) {
      graph.deleteAllFunctions();
      addFunction("x",funcField.getText() );
      //clock.startClock();
    }

    void addDatumBtn_actionPerformed(ActionEvent e) {
        addDatum(1,xField.getValue(),yField.getValue());
    }

    void button4_actionPerformed(ActionEvent e) {
        graph.clearAllData();
    }

    void clearFuncBtn_actionPerformed(ActionEvent e) {
        graph.deleteAllFunctions();
    }

}



