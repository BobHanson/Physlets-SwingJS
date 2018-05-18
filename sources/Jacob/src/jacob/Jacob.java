package jacob;

import a2s.*;
import java.awt.Container;
import edu.davidson.graphics.EtchedBorder;
import edu.davidson.tools.SUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;
//import netscape.security.PrivilegeManager;

public class Jacob extends Applet {
	boolean isStandalone = false;
	boolean firstTime = true;
	int fps;
	double dt;
	boolean showControls;
	String configFile;
	String rc;
	PPDCanvas ppdCanvas = new PPDCanvas();
	EtchedBorder controlPanel = new EtchedBorder();
	Button runBtn = new Button();
	BorderLayout borderLayout1 = new BorderLayout();
	Button loadBtn = new Button();

	public String getParameter(String paramString1, String paramString2) {
		return getParameter(paramString1) != null ? getParameter(paramString1)
				: this.isStandalone ? System.getProperty(paramString1, paramString2) : paramString2;
	}

	public void start() {
		super.start();
		if (this.firstTime) {
			PPD.nullElement = new ERectangle(0, 0, this.ppdCanvas.size().width, this.ppdCanvas.size().height);
			setBackground(PPD.CANVAS_BACKGROUND);
			if ((this.configFile != null) && (!this.configFile.equals(""))) {
				loadFile(this.configFile);
			}
			this.firstTime = false;
			this.ppdCanvas.currentAction = 0;
			//this.loadFile("efelt03.ppd");  //load example for debugging
		}
	}

	public void init() {
		try {
			this.fps = Integer.parseInt(getParameter("FPS", "10"));
		} catch (Exception localException1) {
			localException1.printStackTrace();
		}
		try {
			this.dt = Double.valueOf(getParameter("dt", "0.1")).doubleValue();
		} catch (Exception localException2) {
			localException2.printStackTrace();
		}
		try {
			this.showControls = Boolean.valueOf(getParameter("ShowControls", "true")).booleanValue();
		} catch (Exception localException3) {
			localException3.printStackTrace();
		}
		try {
			this.configFile = getParameter("ConfigFile", "");
		} catch (Exception localException4) {
			localException4.printStackTrace();
		}
		try {
			this.rc = getParameter("resources", "default.rc");
		} catch (Exception localException5) {
			localException5.printStackTrace();
		}
		try {
			jbInit();
		} catch (Exception localException6) {
			localException6.printStackTrace();
		}
		if (this.rc == null) {
			this.rc = "default.rc";
		}
		String str = "Resources not loaded: file=" + this.rc;
		InputStream localInputStream = null;
		if (this.ppdCanvas.isJS) { // convert string to stream.
			String defaultConfig = DefaultRC.getDefault();
			localInputStream = new ByteArrayInputStream(defaultConfig.getBytes());
		}
		if (localInputStream == null) {
			try {
			URL localURL = exp.Data.class.getResource(this.rc);
			localInputStream = localURL.openStream();
			} catch (Exception localException7) {
				str = localException7.getMessage();
			}
		}
		if (localInputStream == null) {
			try {
				localInputStream = new URL(getCodeBase(), this.rc).openStream();
			} catch (IOException localIOException1) {
				str = localIOException1.getMessage();
			}
		}
		if (localInputStream == null) {
			try {
				localInputStream = new URL(getDocumentBase(), this.rc).openStream();
			} catch (IOException localIOException2) {
				str = localIOException2.getMessage();
			}
		}
		if (localInputStream == null) {
			System.out.println("Can't load resources! : " + str);
			return;
		}
		PPD.initResources(localInputStream);
		this.controlPanel.setVisible(this.showControls);
		PPD.setResources();
		Common.setDocumentBase(getDocumentBase());
		PPD.arrow_scale = 300.0D;
		PPD.arrows = true;
	}

	private void jbInit() throws Exception {
		setLayout(this.borderLayout1);
		this.runBtn.setLabel("Run");
		this.runBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
				Jacob.this.runBtn_actionPerformed(paramAnonymousActionEvent);
			}
		});
		this.loadBtn.setLabel("Load");
		this.loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
				Jacob.this.loadBtn_actionPerformed(paramAnonymousActionEvent);
			}
		});
		add(this.ppdCanvas, "Center");
		add(this.controlPanel, "South");
		this.controlPanel.add(this.runBtn, "East");
		if (!this.ppdCanvas.isJS) this.controlPanel.add(this.loadBtn, "West");
	}

	public String getAppletInfo() {
		return "Applet Information";
	}

	public String[][] getParameterInfo() {
		String[][] arrayOfString = { { "FPS", "int", "frames per second" }, { "dt", "double", "time step" },
				{ "ShowControls", "boolean", "show controls" }, { "ConfigFile", "String", "configuration" } };
		return arrayOfString;
	}

	void runBtn_actionPerformed(ActionEvent paramActionEvent) {
		if (this.runBtn.getLabel().equals("Run")) {
			this.runBtn.setLabel("Stop");
			PPD.animate = true;
			this.ppdCanvas.start();
		} else {
			this.ppdCanvas.stop();
			this.runBtn.setLabel("Run");
			PPD.animate = false;
		}
	}

	void loadBtn_actionPerformed(ActionEvent paramActionEvent) {
		Container localContainer;
		for (localContainer = getParent(); (localContainer != null)
				&& (!(localContainer instanceof Frame)); localContainer = localContainer.getParent()) {
		}
		if (!(localContainer instanceof Frame)) {
			return;
		}
		FileDialog localFileDialog = new FileDialog((Frame) localContainer, "Input File", 0);
		localFileDialog.show();
		try {
			// PrivilegeManager.enablePrivilege("UniversalFileRead");
		} catch (Throwable localThrowable) {
		}
		try {
			File localFile = new File(localFileDialog.getDirectory(), localFileDialog.getFile());
			FileInputStream localFileInputStream = new FileInputStream(localFile);
			PPD.particleBox.clear();
			PPD.elementBox.clear();
			Data.loadFile(PPD.particleBox, PPD.elementBox, localFileInputStream);
		} catch (Exception localException) {
			System.out.println("load failed: " + localException.getMessage());
		}
		Calculus.registerNeightbours();
		Calculus.correctParticles();
		PPD.particleArray = new Particle[PPD.particleBox.size()];
		PPD.particleBox.copyInto(PPD.particleArray);
		this.ppdCanvas.redraw();
	}

	public boolean addCharges(int paramInt1, int paramInt2) {
		Element localElement = getElement(paramInt1);
		if (localElement == null) {
			return false;
		}
		if (paramInt2 > 0) {
			Calculus.insertParticlesNeg(0, 0, localElement, paramInt2);
			PPD.particleArray = new Particle[PPD.particleBox.size()];
			PPD.particleBox.copyInto(PPD.particleArray);
		} else if (paramInt2 < 0) {
			Calculus.insertParticlesNeg(0, 0, localElement, -paramInt2);
			PPD.particleArray = new Particle[PPD.particleBox.size()];
			PPD.particleBox.copyInto(PPD.particleArray);
		}
		this.ppdCanvas.repaint();
		return true;
	}

	public boolean addDipoles(int paramInt1, int paramInt2) {
		Element localElement = getElement(paramInt1);
		if (localElement == null) {
			return false;
		}
		paramInt2 = Math.abs(paramInt2);
		if (paramInt2 > 0) {
			Calculus.insertDipols(0, 0, localElement, paramInt2);
			PPD.particleArray = new Particle[PPD.particleBox.size()];
			PPD.particleBox.copyInto(PPD.particleArray);
		}
		this.ppdCanvas.repaint();
		return true;
	}

	public synchronized int addObject(String paramString1, String paramString2) {
		Object localObject = null;
		int i = 0;
		int j = 0;
		int k = 20;
		int m = 20;
		int n = 20;
		int i1 = 0;
		int i2 = 0;
		paramString1 = paramString1.toLowerCase().trim();
		paramString1 = SUtil.removeWhitespace(paramString1);
		String str = paramString2.trim();
		paramString2 = SUtil.removeWhitespace(paramString2);
		if (paramString1.equals("rectangle")) {
			if (SUtil.parameterExist(paramString2, "x=")) {
				i = (int) SUtil.getParam(paramString2, "x=");
			}
			if (SUtil.parameterExist(paramString2, "y=")) {
				j = (int) SUtil.getParam(paramString2, "y=");
			}
			if (SUtil.parameterExist(paramString2, "w=")) {
				k = (int) SUtil.getParam(paramString2, "w=");
			}
			if (SUtil.parameterExist(paramString2, "h=")) {
				m = (int) SUtil.getParam(paramString2, "h=");
			}
			if (SUtil.parameterExist(paramString2, "q=")) {
				i2 = (int) SUtil.getParam(paramString2, "q=");
			}
			i -= k / 2;
			j -= m / 2;
			localObject = new ERectangle(i, j, k, m);
			PPD.elementBox.addElement(localObject);
			if (i2 > 0) {
				Calculus.insertParticlesPos(i + k / 2, j + m / 2, (Element) localObject, i2);
				PPD.particleArray = new Particle[PPD.particleBox.size()];
				PPD.particleBox.copyInto(PPD.particleArray);
			} else if (i2 < 0) {
				Calculus.insertParticlesNeg(i + k / 2, j + m / 2, (Element) localObject, -i2);
				PPD.particleArray = new Particle[PPD.particleBox.size()];
				PPD.particleBox.copyInto(PPD.particleArray);
			}
			i1 = localObject.hashCode();
			return i1;
		}
		if (paramString1.equals("circle")) {
			if (SUtil.parameterExist(paramString2, "x=")) {
				i = (int) SUtil.getParam(paramString2, "x=");
			}
			if (SUtil.parameterExist(paramString2, "y=")) {
				j = (int) SUtil.getParam(paramString2, "y=");
			}
			if (SUtil.parameterExist(paramString2, "r=")) {
				n = (int) SUtil.getParam(paramString2, "r=");
			}
			if (SUtil.parameterExist(paramString2, "q=")) {
				i2 = (int) SUtil.getParam(paramString2, "q=");
			}
			localObject = new ECircle(i, j, n);
			PPD.elementBox.addElement(localObject);
			if (i2 > 0) {
				Calculus.insertParticlesPos(i, j, (Element) localObject, i2);
				PPD.particleArray = new Particle[PPD.particleBox.size()];
				PPD.particleBox.copyInto(PPD.particleArray);
			} else if (i2 < 0) {
				Calculus.insertParticlesNeg(i, j, (Element) localObject, -i2);
				PPD.particleArray = new Particle[PPD.particleBox.size()];
				PPD.particleBox.copyInto(PPD.particleArray);
			}
			i1 = localObject.hashCode();
			return i1;
		}
		if (paramString1.equals("ring")) {
			int i3 = 0;
			if (SUtil.parameterExist(paramString2, "x=")) {
				i = (int) SUtil.getParam(paramString2, "x=");
			}
			if (SUtil.parameterExist(paramString2, "y=")) {
				j = (int) SUtil.getParam(paramString2, "y=");
			}
			if (SUtil.parameterExist(paramString2, "r1=")) {
				n = (int) SUtil.getParam(paramString2, "r1=");
			}
			if (SUtil.parameterExist(paramString2, "r2=")) {
				i3 = (int) SUtil.getParam(paramString2, "r2=");
			}
			if (SUtil.parameterExist(paramString2, "q=")) {
				i2 = (int) SUtil.getParam(paramString2, "q=");
			}
			localObject = new ERing(i, j, n, i3);
			PPD.elementBox.addElement(localObject);
			if (i2 > 0) {
				Calculus.insertParticlesPos(i, j, (Element) localObject, i2);
				PPD.particleArray = new Particle[PPD.particleBox.size()];
				PPD.particleBox.copyInto(PPD.particleArray);
			} else if (i2 < 0) {
				Calculus.insertParticlesNeg(i, j, (Element) localObject, -i2);
				PPD.particleArray = new Particle[PPD.particleBox.size()];
				PPD.particleBox.copyInto(PPD.particleArray);
			}
			i1 = localObject.hashCode();
			return i1;
		}
		if (localObject == null) {
			System.out.println("Object not created. name:" + paramString1 + "parameter list:" + paramString2);
			return 0;
		}
		return 0;
	}

	public boolean deleteObject(int paramInt) {
		Element localElement1 = getElement(paramInt);
		if (localElement1 == null) {
			return false;
		}
		Calculus.removeParticles(localElement1);
		PPD.elementBox.removeElement(localElement1);
		for (int i = 0; i < PPD.elementBox.size(); i++) {
			Element localElement2 = (Element) PPD.elementBox.elementAt(i);
			if (localElement2.connection == localElement1) {
				localElement2.connection = null;
			}
		}
		PPD.particleArray = new Particle[PPD.particleBox.size()];
		PPD.particleBox.copyInto(PPD.particleArray);
		Calculus.registerNeightbours();
		this.ppdCanvas.redraw();
		return true;
	}

	public void pause() {
		this.ppdCanvas.stop();
		this.runBtn.setLabel("Run");
		PPD.animate = false;
	}

	public void forward() {
		this.runBtn.setLabel("Stop");
		PPD.animate = true;
		this.ppdCanvas.start();
	}

	public void stepForward() {
		if (PPD.animate == true) {
			pause();
			return;
		}
		if (PPD.particleArray == null) {
			return;
		}
		Calculus.magicFormula();
		if (PPD.energyPlotter != null) {
			PPD.energyPlotter.addData(Calculus.energy());
		}
		this.ppdCanvas.repaint();
	}

	public void loadFile(String paramString) {
		pause();
		if (this.ppdCanvas.isJS) {
			ppdCanvas.loadFile(paramString);
			return;
		}
		String str = "File not loaded: file=" + paramString;
		InputStream localInputStream = null;
		if (localInputStream == null) {
			try {
				URL localURL1 = exp.Data.class.getResource(paramString);
				localInputStream = localURL1.openStream();
			} catch (Exception localException1) {
				str = localException1.getMessage();
			}
		}
		if (localInputStream == null) {
			try {
				URL localURL2 = exp.Data.class.getResource("/" + paramString);
				localInputStream = localURL2.openStream();
			} catch (Exception localException2) {
				str = localException2.getMessage();
			}
		}
		if (localInputStream == null) {
			try {
				localInputStream = new URL(getCodeBase(), paramString).openStream();
			} catch (IOException localIOException1) {
				str = localIOException1.getMessage();
			}
		}
		if (localInputStream == null) {
			try {
				localInputStream = new URL(getDocumentBase(), paramString).openStream();
			} catch (IOException localIOException2) {
				str = localIOException2.getMessage();
			}
		}
		if (localInputStream == null) {
			try {
				URL localURL3 = exp.Data.class.getResource(paramString);
				localInputStream = localURL3.openStream();
			} catch (Exception localException3) {
				str = localException3.getMessage();
			}
		}
		if (localInputStream == null) {
			System.out.println("Can't load file! : " + str);
			return;
		}
		this.ppdCanvas.loadFile(localInputStream);
	}

	public void setDefault() {
		this.ppdCanvas.stop();
		this.runBtn.setLabel("Run");
		PPD.animate = false;
		PPD.particleBox.removeAllElements();
		PPD.elementBox.removeAllElements();
		Calculus.registerNeightbours();
		Calculus.correctParticles();
		PPD.particleArray = new Particle[PPD.particleBox.size()];
		PPD.particleBox.copyInto(PPD.particleArray);
		this.ppdCanvas.redraw();
	}

	public boolean setRGB(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		Element localElement = getElement(paramInt1);
		if (localElement == null) {
			return false;
		}
		localElement.myColor = new Color(paramInt2, paramInt3, paramInt4);
		this.ppdCanvas.redraw();
		return true;
	}

	public boolean setResizbale(int paramInt, boolean paramBoolean) {
		Element localElement = getElement(paramInt);
		if (localElement == null) {
			return false;
		}
		localElement.scalable = paramBoolean;
		return true;
	}

	public boolean setDragable(int paramInt, boolean paramBoolean) {
		Element localElement = getElement(paramInt);
		if (localElement == null) {
			return false;
		}
		localElement.dragable = paramBoolean;
		return true;
	}

	public boolean setPPDAction(String paramString) {
		paramString = paramString.toLowerCase();
		if (paramString.equals("move")) {
			this.ppdCanvas.currentAction = 4609;
			return true;
		}
		if (paramString.equals("scale")) {
			this.ppdCanvas.currentAction = 4610;
			return true;
		}
		if (paramString.equals("none")) {
			this.ppdCanvas.currentAction = 0;
			return true;
		}
		return false;
	}

	private Element getElement(int paramInt) {
		Vector localVector = PPD.elementBox;
		Element localElement = null;
		for (int i = 0; i < localVector.size(); i++) {
			localElement = (Element) localVector.elementAt(i);
			if (localElement.hashCode() == paramInt) {
				return localElement;
			}
		}
		return null;
	}
}

/*
 * Location:
 * /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/
 * jacob/Jacob.class Java compiler version: 1 (45.3) JD-Core Version: 0.7.1
 */