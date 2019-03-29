package circuitsimulator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Timer;

import edu.davidson.tools.SUtil;
import edu.davidson.tools.SwingJSUtils;
import symantec.itools.awt.BorderPanel;


/**
 * Applet, extension to circuit. An interactive tool to build (drag and drop) and analyse circuits.
 *
 * @author Toon Van Hoecke
 */
public class CircuitBuilder extends circuitsimulator.Circuit {

	// BH note: This utility allows us to set the 
	// dimensions either using the width/height applet parameters
	// or a predefined size. It's important for testing in JavaScript, especially.
  static Dimension dim  = SwingJSUtils.setDim(514, 445);

	/**
	 * Exclude the javadoc because this method should not be scripted.
	 * 
	 * @y.exclude
	 */
	public void init() {
		super.init();
		if (dim != null)
			setSize(dim);
		borderPanel = new BorderPanel();
		builderPanel = new BuilderPanel(this);
		/*
		 * String s = getParameter("startlist"); if (s == null) startlist = "/" ; else
		 * imagedir += s+"/";
		 */
		// {{INIT_CONTROLS

		setLayout(null);
		setBackground(new Color(0, 143, 213));
		try {
			borderPanel.setBevelStyle(symantec.itools.awt.BorderPanel.BEVEL_RAISED);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			borderPanel.setIPadBottom(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			borderPanel.setIPadSides(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			borderPanel.setPaddingRight(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			borderPanel.setPaddingBottom(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			borderPanel.setPaddingTop(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			borderPanel.setIPadTop(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			borderPanel.setPaddingLeft(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		borderPanel.setLayout(null);
		add(borderPanel);
		borderPanel.setBounds(0, 0, 514, 444);

		try {
			builderPanel.setPaddingRight(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			builderPanel.setBevelStyle(circuitsimulator.BuilderPanel.BEVEL_LOWERED);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			builderPanel.setPaddingLeft(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			builderPanel.setIPadBottom(1);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			builderPanel.setPaddingBottom(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			builderPanel.setPaddingTop(0);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			builderPanel.setIPadSides(1);
		} catch (java.beans.PropertyVetoException e) {
		}
		try {
			builderPanel.setIPadTop(1);
		} catch (java.beans.PropertyVetoException e) {
		}
		builderPanel.setLayout(null);
		builderPanel.setBackground(new Color(0, 143, 213));
		builderPanel.setBounds(313, 17, 195, 401);

		borderPanel.add(builderPanel);
		// }}
		
		// {{REGISTER_LISTENERS
		SymMouse aSymMouse = new SymMouse();
		// }}
		circanvas.addMouseListener(aSymMouse);
	}

  //{{DECLARE_CONTROLS
  symantec.itools.awt.BorderPanel borderPanel;
  circuitsimulator.BuilderPanel   builderPanel;
  //}}
  PopupOnElement                  popupOnElement;
  CircuitElement                  currentElement = null;
  Vector<OscilloDialog>           scopeList      = new Vector<>();
  Vector<Meter>                   meterList      = new Vector<>();
  Vector<DataGraphDialog>         graphList      = new Vector<>();
  String                          componentList  = "";

  /**
   * Method start
   * Exclude the javadoc because this method should not be scripted.
 * @y.exclude
   */
  public void start() {
    super.start();
    gridZone = getSize();
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
		      for(Enumeration<Meter> enu = meterList.elements(); enu.hasMoreElements(); ) {
		          Meter meter = enu.nextElement();
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
      for(Enumeration<Meter> enu = meterList.elements(); enu.hasMoreElements(); ) {
        Meter meter = enu.nextElement();
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
  class SymMouse extends MouseAdapter {

    /**
     * Method mouseReleased
     *
     * @param event
     */
    public void mouseReleased(MouseEvent event) {
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
    public void mousePressed(MouseEvent event) {
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
  void circanvas_MousePressed(MouseEvent event) {
    currentElement = getComponent(coordString(event.getPoint(), false));
    if((event.isMetaDown() == true) | (event.isControlDown() == true)) {
      popupOnElement.selectItems();
      add(popupOnElement);
      popupOnElement.show(event.getComponent(), event.getX(), event.getY());
    } else {
      circanvas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
  void circanvas_mouseReleased(MouseEvent event) {
    if((event.isMetaDown() == false) & (event.isControlDown() == false)) {
      moveComponent(currentElement, coordString(event.getPoint(), false));
      circanvas.setCursor(Cursor.getDefaultCursor());
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
      for(Enumeration<DataGraphDialog> e = graphList.elements(); e.hasMoreElements(); ) {
        DataGraphDialog graph = e.nextElement();
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
      for(Enumeration<DataGraphDialog> e = graphList.elements(); e.hasMoreElements(); ) {
        DataGraphDialog graph = e.nextElement();
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
      for(Enumeration<OscilloDialog> e = scopeList.elements(); e.hasMoreElements(); ) {
        OscilloDialog oscdiag = e.nextElement();
        oscdiag.scopeCanvas.repaint();
      }
      for(Enumeration<Meter> e = meterList.elements(); e.hasMoreElements(); ) {
        Meter meter = e.nextElement();
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
	  //BH note: 
	  // We need to use getDocumentBase().getPath(), not toString()
	  // because we are looing for the end the path, not the end of 
	  // the url, to be ".html". Some URLs for whatever reason might include ? or #. 
    boolean trydoc = false;
//    String  s      = "";
    try {
      if(Circuit.DEBUG) {
        System.out.println("loading from codebase:" + getCodeBase().getPath() + inputfile);
      }
      URL url = new java.net.URL(getCodeBase() + inputfile);
//      url = new URL("https://stolaf.edu/adjfaljfafd");
//      HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
//      c.connect();
//      System.out.println(c.getContentLength() + " " + c.getResponseCode());
//
      java.io.InputStream    in  = url.openStream();
      java.io.BufferedReader br  = new java.io.BufferedReader(new java.io.InputStreamReader(in));
      String                 line;
      while((line = br.readLine()) != null) {
//        s += line + "\n";
        parseCommand(line);
      }
    } catch(Exception e) {
      trydoc = true;
      if(Circuit.DEBUG) {
        System.out.println("load failed from docbase: " + e.getMessage());
      }
    }
    if(trydoc) {
//      s = "";
      try {
        java.net.URL           url = new java.net.URL(getDocumentPath() + inputfile);
        java.io.InputStream    in  = url.openStream();
        java.io.BufferedReader br  = new java.io.BufferedReader(new java.io.InputStreamReader(in));
        String                 line;
        while((line = br.readLine()) != null) {
//          s += line + "\n";
          parseCommand(line);
        }
      } catch(Exception e) {
        System.out.println("load failed: " + e.getMessage());
        setGrid("rows=8,cols=5");
      }
    }
  }

}
