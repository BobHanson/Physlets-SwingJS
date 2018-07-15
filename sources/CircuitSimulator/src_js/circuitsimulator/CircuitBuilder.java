package circuitsimulator;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

import java.util.Enumeration;
import edu.davidson.tools.*;


/**
 * Applet, extension to circuit. An interactive tool to build (drag and drop) and analyse circuits.
 *
 * @author Toon Van Hoecke
 */
public class CircuitBuilder extends circuitsimulator.Circuit {

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
     */
  public void init() {
    super.init();
    /*
     * String s = getParameter("startlist");
     *  if (s == null) startlist = "/" ;
     *  else imagedir += s+"/";
     */
    //{{INIT_CONTROLS
    setLayout(null);
    if(!Circuit.isJS)setBackground(new java.awt.Color(0, 143, 213));
    setSize(514, 445);
    try {
      borderPanel.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      borderPanel.setIPadBottom(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      borderPanel.setIPadSides(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      borderPanel.setPaddingRight(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      borderPanel.setPaddingBottom(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      borderPanel.setPaddingTop(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      borderPanel.setIPadTop(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      borderPanel.setPaddingLeft(0);
    } catch(java.beans.PropertyVetoException e) {}
    borderPanel.setLayout(null);
    add(borderPanel);
    borderPanel.setBounds(0, 0, 514, 444);
    try {
      builderPanel.setPaddingRight(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      builderPanel.setBevelStyle(circuitsimulator.BuilderPanel.BEVEL_LOWERED);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      builderPanel.setPaddingLeft(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      builderPanel.setIPadBottom(1);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      builderPanel.setPaddingBottom(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      builderPanel.setPaddingTop(0);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      builderPanel.setIPadSides(1);
    } catch(java.beans.PropertyVetoException e) {}
    try {
      builderPanel.setIPadTop(1);
    } catch(java.beans.PropertyVetoException e) {}
    builderPanel.setLayout(null);
    borderPanel.add(builderPanel);
    if(!Circuit.isJS)builderPanel.setBackground(new java.awt.Color(0, 143, 213));
    builderPanel.setBounds(313, 17, 195, 401);
    //}}
    //{{REGISTER_LISTENERS
    SymMouse aSymMouse = new SymMouse();
    //}}
    circanvas.addMouseListener(aSymMouse);
    builderPanel.setcircuitBuilder(this);
    builderPanel.loadImages();
  }

  //{{DECLARE_CONTROLS
  symantec.itools.awt.BorderPanel borderPanel  = new symantec.itools.awt.BorderPanel();
  circuitsimulator.BuilderPanel   builderPanel = new circuitsimulator.BuilderPanel();
  //}}
  PopupOnElement                  popupOnElement;
  CircuitElement                  currentElement = null;
  Vector                          scopeList      = new Vector();  //vector of scopes
  Vector                          meterList      = new Vector();  //vector of meters
  Vector                          graphList      = new Vector();  //vector of graphs
  String                          componentList  = "";
  OscilloDialog                   oscdiag        = null;
  Meter                           meter          = null;
  DataGraphDialog                 graph          = null;

  /**
   * Method start
   * Exclude the javadoc because this method should not be scripted.
 * @y.exclude
   */
  public void start() {
    super.start();
    gridZone.width = builderPanel.getBounds().x;
    popupOnElement = new PopupOnElement(this);
    loadList("/lists/default.txt");
    parse();
    calculateCircuit();
  }
  
  public void runTimer() {
		timer = new Timer(sleepTime, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		      for(Enumeration enu = meterList.elements(); enu.hasMoreElements(); ) {
		          meter = (Meter) enu.nextElement();
		          meter.recalc();
		        }
				circanvas.repaint();
				if(runner!=null)runTimer();
			}
		});
		timer.setRepeats(false);
		timer.start();
  }

  /**
   * Exclude the javadoc because this method should not be scripted.
   * @y.exclude
   */
  public void run() {
	if(Circuit.isJS) {
	    	System.err.println("Error.  Thread should not start when using JavaScript.");
	    	return;
	}
    while(runner==Thread.currentThread()) {
      if(parsed /*&& imageRepainting*/) {
        circanvas.repaint();
      }
      for(Enumeration enu = meterList.elements(); enu.hasMoreElements(); ) {
        meter = (Meter) enu.nextElement();
        meter.recalc();
      }
      try {
        Thread.sleep(100);
      } catch(InterruptedException e) {}
    }
  }

  /**
   * Class SymMouse
   *
   *
   * @author
   * @version %I%, %G%
   */
  class SymMouse extends java.awt.event.MouseAdapter {

    /**
     * Method mouseReleased
     *
     * @param event
     */
    public void mouseReleased(java.awt.event.MouseEvent event) {
      Object object = event.getSource();
      if(object == circanvas) {
        circanvas_mouseReleased(event);
      }
    }

    /**
     * Method mousePressed
     *
     * @param event
     */
    public void mousePressed(java.awt.event.MouseEvent event) {
      Object object = event.getSource();
      if(object == circanvas) {
        circanvas_MousePressed(event);
      }
    }
  }

  /**
   * Method circanvas_MousePressed
   *
   * @param event
   */
  void circanvas_MousePressed(java.awt.event.MouseEvent event) {
    currentElement = getComponent(coordString(event.getPoint(), false));
    if((event.isMetaDown() == true) | (event.isControlDown() == true)) {
      popupOnElement.selectItems();
      add(popupOnElement);
      popupOnElement.show(event.getComponent(), event.getX(), event.getY());
    } else {
      circanvas.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    }
    if((debugLevel & DEBUG_IO) > 0) {
      System.out.println(currentElement.getMyName());
    }
  }

  /**
   * Method circanvas_mouseReleased
   *
   * @param event
   */
  void circanvas_mouseReleased(java.awt.event.MouseEvent event) {
    if((event.isMetaDown() == false) & (event.isControlDown() == false)) {
      moveComponent(currentElement, coordString(event.getPoint(), false));
      circanvas.setCursor(java.awt.Cursor.getDefaultCursor());
      parse();
      repaintMeters();
    }
  }

  /* ********************************** Additional routines ************************** */

  /**
   * Method step
   * Exclude the javadoc because this method should not be scripted.
 * @y.exclude
   * @param dt
   * @param time
   */
  public void step(double dt, double time) {
    super.step(dt, time);
    if((parsed) &&!graphList.isEmpty()) {
      for(Enumeration e = graphList.elements(); e.hasMoreElements(); ) {
        graph = (DataGraphDialog) e.nextElement();
        graph.addData();
      }
    }
  }

  /**
   * Method reset
   *
   */
  public void reset() {
    super.reset();
    if((parsed) &&!graphList.isEmpty()) {
      for(Enumeration e = graphList.elements(); e.hasMoreElements(); ) {
        graph = (DataGraphDialog) e.nextElement();
        graph.clearGraph();
      }
    }
  }

  /**
   * Method coordString
   * Exclude the javadoc because this method should not be scripted.
 * @y.exclude
   * @param absCoords
   * @param absolute
   *
   * @return
   */
  public String coordString(Point absCoords, boolean absolute) {
    Point circanvasP = new Point(0, 0);
    if(absolute) {
      circanvasP.setLocation(circanvas.getLocation());
    }
    String posString = "row=";
    int    diffx, diffy, x, y;
    int    row = -1, col = -1;
    x = absCoords.x - circanvasP.x;
    y = absCoords.y - circanvasP.y;
    do {
      col++;
      diffx = interGrid / 2 + interGrid * col - x;
    } while(diffx < 0);
    do {
      row++;
      diffy = interGrid / 2 + interGrid * row - y;
    } while(diffy < 0);
    if((diffx >= diffy) & (diffx + diffy >= interGrid)) {
      posString += Integer.toString(row - 1) + ",col=" + Integer.toString(col - 1) + ",to=v";
    } else if((diffx >= diffy) & (diffx + diffy < interGrid)) {
      posString += Integer.toString(row) + ",col=" + Integer.toString(col - 1) + ",to=h";
    } else if((diffx < diffy) & (diffy + diffx >= interGrid)) {
      posString += Integer.toString(row - 1) + ",col=" + Integer.toString(col - 1) + ",to=h";
    } else if((diffx < diffy) & (diffy + diffx < interGrid)) {
      posString += Integer.toString(row - 1) + ",col=" + Integer.toString(col) + ",to=v";
    }
    if((debugLevel & DEBUG_IO) > 0) {
      System.out.println(posString);
    }
    return posString;
  }

  /**
   * Method repaintMeters
   *
   */
  public void repaintMeters() {
    if(parsed) {
      calculateCircuit();
      for(Enumeration e = scopeList.elements(); e.hasMoreElements(); ) {
        oscdiag = (OscilloDialog) e.nextElement();
        oscdiag.scopeCanvas.repaint();
      }
      for(Enumeration e = meterList.elements(); e.hasMoreElements(); ) {
        meter = (Meter) e.nextElement();
        meter.recalc();
      }
    }
  }

  /**
   * Method parseCommand
   *
   * @param s
   */
  public void parseCommand(String s) {
    s = "" + SUtil.removeWhitespace(s);
    if(SUtil.parameterExist(s, "setGrid")) {
      String parameters = new String(s.substring(9, s.length() - 3));
      int    rows       = (int) SUtil.getParam(parameters, "rows=");
      int    cols       = (int) SUtil.getParam(parameters, "cols=");
      setGrid(rows, cols);
    } else if(SUtil.parameterExist(s, "setNumberOfDT")) {
      int par = Integer.parseInt(s.substring(s.indexOf("(") + 1, s.indexOf(")")));
      setNumberOfDT(par);
    } else if(SUtil.parameterExist(s, "setDT")) {
      double par = (Double.valueOf(s.substring(s.indexOf("(") + 1, s.indexOf(")")))).doubleValue();
      setDT(par);
    } else if(SUtil.parameterExist(s, "setNOC")) {
      int par = Integer.parseInt(s.substring(s.indexOf("(") + 1, s.indexOf(")")));
      setNOC(par);
    } else if(SUtil.parameterExist(s, "setFPS")) {
      double par = (Double.valueOf(s.substring(s.indexOf("(") + 1, s.indexOf(")")))).doubleValue();
      setFPS(par);
    } else if(SUtil.parameterExist(s, "addObject")) {
      String name = new String(s.substring(s.indexOf("(\"") + 2, s.indexOf("\",")));
      String list = new String(s.substring(s.indexOf(",\"") + 2, s.indexOf("\")")));
      addObject(name, list);
    }
  }

  /**
   * Method loadList
   *
   * @param inputfile
   */
  void loadList(String inputfile) {
    boolean trydoc = false;
    String  s      = "";
    try {
      if(Circuit.DEBUG) {
        System.out.println("loading from codebase:" + getCodeBase().toString() + inputfile);
      }
      java.net.URL           url = new java.net.URL(getCodeBase().toString() + inputfile);
      java.io.InputStream    in  = url.openStream();
      java.io.BufferedReader br  = new java.io.BufferedReader(new java.io.InputStreamReader(in));
      String                 line;
      while((line = br.readLine()) != null) {
        s += line + "\n";
        parseCommand(line);
      }
    } catch(Exception e) {
      trydoc = true;
      if(Circuit.DEBUG) {
        System.out.println("load failed from docbase: " + e.getMessage());
      }
    }
    if(trydoc) {
      s = "";
      try {
        String pathName = getDocumentBase().toString();
        if(pathName.endsWith(".html") || pathName.endsWith(".htm")) {
          int index = pathName.lastIndexOf("/");
          pathName = pathName.substring(0, index + 1);  // drop the html file name
        }
        java.net.URL           url = new java.net.URL(pathName + inputfile);
        java.io.InputStream    in  = url.openStream();
        java.io.BufferedReader br  = new java.io.BufferedReader(new java.io.InputStreamReader(in));
        String                 line;
        while((line = br.readLine()) != null) {
          s += line + "\n";
          parseCommand(line);
        }
      } catch(Exception e) {
        System.out.println("load failed: " + e.getMessage());
        setGrid("rows=8,cols=5");
      }
    }
  }
}
