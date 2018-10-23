package jacob;

import java.awt.*;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.Timer;

class PPDCanvas extends Canvas implements Runnable {
	boolean isJS = /** @j2sNative true || */ false;
	//boolean isJS = true; // for debugging
	private Timer timer; // Swing timer when in JavaScript
	private boolean shouldRun = false; // keeps the timer running.
	Thread anim = null;
	int currentAction = 4609;
	Element currentElement = null;
	Element selectedElement = null;
	Particle currentParticle = null;
	Rectangle updateRegion = new Rectangle();
	boolean painted = true;
	boolean update_hack = true;
	boolean interactive = true;
	Image backBuffer;
	Graphics backGC;
	int buffWidth;
	int buffHeight;
	int oldx;
	int oldy;
	Plotter lintPlotter = null;
	double lint;
	int lintx = 0;
	int linty = 0;
	int oldlintx = 0;
	int oldlinty = 0;

	PPDCanvas() {
		PPD.nullElement = new ERectangle(0, 0, size().width, size().height);
		setBackground(PPD.CANVAS_BACKGROUND);
	}

	public void start() {
		shouldRun = true;
		if (this.anim == null) {
			this.anim = new Thread(this);
			if (!isJS)
				this.anim.start();
		}
		if (isJS) {
			runTimer();
		}
	}

	public void stop() {
		shouldRun = false;
		if ((this.anim != null) && (this.anim.isAlive())) {
			this.anim.stop();
		}
		this.anim = null;
		if (this.backBuffer != null) {
			this.backBuffer.flush();
		}
	}

	public boolean mouseDown(Event paramEvent, int paramInt1, int paramInt2) {
		if (this.interactive) {
			int i;
			Object localObject;
			switch (this.currentAction) {
			case 4096:
				this.currentElement = new ECircle(paramInt1, paramInt2, 0);
				PPD.elementBox.addElement(this.currentElement);
				break;
			case 4097:
				this.currentElement = new ERectangle(paramInt1, paramInt2, 0, 0);
				PPD.elementBox.addElement(this.currentElement);
				break;
			case 4098:
				this.currentElement = new ERing(paramInt1, paramInt2);
				PPD.elementBox.addElement(this.currentElement);
				break;
			case 4352:
				if (this.currentElement != null) {
					Calculus.insertParticlesPos(paramInt1, paramInt2, this.currentElement, PPD.num_particles);
				}
				break;
			case 4353:
				if (this.currentElement != null) {
					Calculus.insertParticlesNeg(paramInt1, paramInt2, this.currentElement, PPD.num_particles);
				}
				break;
			case 4354:
				if (this.currentElement != null) {
					Calculus.insertDipols(paramInt1, paramInt2, this.currentElement, PPD.num_particles);
				}
				break;
			case 4608:
				if (this.currentElement != null) {
					Calculus.removeParticles(this.currentElement);
					PPD.elementBox.removeElement(this.currentElement);
					for (i = 0; i < PPD.elementBox.size(); i++) {
						localObject = (Element) PPD.elementBox.elementAt(i);
						if (((Element) localObject).connection == this.currentElement) {
							((Element) localObject).connection = null;
						}
					}
					this.currentElement = null;
				}
				break;
			case 4865:
				if (this.currentElement != null) {
					Draw.storeBounds(this.updateRegion, this.currentElement);
					this.currentElement.delForce();
					redraw(this.updateRegion);
				}
				break;
			case 4866:
				if (this.currentElement != null) {
					this.currentElement.tagForceCirc();
					redraw(this.currentElement.bounds);
				}
				break;
			case 4867:
				if (this.currentElement != null) {
					this.currentElement.tagForceRotate();
					redraw(this.currentElement.bounds);
				}
				break;
			case 4868:
				if (this.currentElement != null) {
					this.currentElement.tagForceTranslate();
					redraw(this.currentElement.bounds);
				}
				break;
			case 5376:
				if (this.currentElement != null) {
					this.currentElement.tagMove();
					this.currentElement.setDrawMode(0);
					redraw(this.currentElement.bounds);
				}
				break;
			case 5632:
				if (this.currentElement != null) {
					this.currentElement.tagTransient();
					this.currentElement.setDrawMode(0);
					redraw(this.currentElement.bounds);
				}
				break;
			case 6912:
				if (this.currentElement != null) {
					if (paramEvent.metaDown()) {
						if (this.currentElement.connection != null) {
							this.currentElement.connection.connection = null;
							this.currentElement.connection = null;
						}
						this.selectedElement = null;
						redraw();
					} else if (this.selectedElement == null) {
						this.selectedElement = this.currentElement;
					} else if (this.selectedElement != this.currentElement) {
						if (this.selectedElement.connection != null) {
							this.selectedElement.connection.connection = null;
						}
						this.selectedElement.connection = this.currentElement;
						this.currentElement.connection = this.selectedElement;
						this.selectedElement = null;
						redraw();
					}
				}
				break;
			case 7168:
				if (this.currentElement != null) {
					if (this.currentElement.energyPlotter == null) {
						this.currentElement.energyPlotter = new Plotter(PPD.ACTIONMENU_PLOT_ENERGY, 200, 100);
					}
					this.currentElement.energyPlotter.show();
				}
				break;
			case 7424:
				if (this.currentElement != null) {
					if (this.currentElement.currPlotter == null) {
						this.currentElement.currPlotter = new Plotter(PPD.ACTIONMENU_PLOT_CURR, 200, 100);
					}
					this.currentElement.currPlotter.show();
				}
				break;
			case 6656:
				if (this.currentElement != null) {
					for (i = 0; i < PPD.particleArray.length; i++) {
						localObject = PPD.particleArray[i];
						if (((Particle) localObject).parent == this.currentElement) {
							((Particle) localObject).k = 0.0D;
						}
					}
				}
				break;
			case 5888:
				PPD.PPDZero.x = paramInt1;
				PPD.PPDZero.y = paramInt2;
				redraw();
				break;
			case 6144:
				this.oldlintx = (this.lintx = paramInt1);
				this.oldlinty = (this.linty = paramInt2);
				this.lint = 0.0D;
				this.lintPlotter = new Plotter(PPD.ACTIONMENU_PLOT_LINT, 200, 100);
				break;
			case 6400:
				this.oldlintx = (this.lintx = paramInt1);
				this.oldlinty = (this.linty = paramInt2);
				this.lint = 0.0D;
				this.lintPlotter = new Plotter(PPD.ACTIONMENU_PLOT_POTENTIAL, 200, 150);
				break;
			}
			this.oldx = paramInt1;
			this.oldy = paramInt2;
		}
		return true;
	}

	public boolean mouseDrag(Event paramEvent, int paramInt1, int paramInt2) {
		if ((this.interactive) && (this.currentElement != null)) {
			Draw.storeBounds(this.updateRegion, this.currentElement);
			switch (this.currentAction) {
			case 4096:
			case 4097:
			case 4098:
			case 4610:
				if (this.currentElement.scalable) {
					this.currentElement.scale(paramInt1, paramInt2);
					Calculus.registerNeightbours();
					Calculus.correctParticles();
				}
				break;
			case 4609:
				if (this.currentElement.dragable) {
					this.currentElement.move(paramInt1 - this.oldx, paramInt2 - this.oldy);
					Calculus.moveParticles(this.currentElement, paramInt1 - this.oldx, paramInt2 - this.oldy);
					Calculus.registerNeightbours();
				}
				break;
			case 4864:
				this.currentElement.setForce(paramInt1, paramInt2);
				break;
			}
			redraw(this.updateRegion);
			redraw(this.currentElement.bounds);
		}
		if (this.currentAction == 6144) {
			if (PPD.particleArray != null) {
				if (!PPD.animate) {
					Calculus.magicFormula();
				}
				double d = (this.oldlintx - paramInt1) * (this.oldlintx - paramInt1)
						+ (this.oldlinty - paramInt2) * (this.oldlinty - paramInt2);
				if (d > 100.0D) {
					this.lint += Calculus.forceSum(this.oldlintx, this.oldlinty, paramInt1 - this.oldlintx,
							paramInt2 - this.oldlinty);
					this.lintPlotter.addData(this.lint);
					this.oldlintx = paramInt1;
					this.oldlinty = paramInt2;
				}
			}
			this.lintx = paramInt1;
			this.linty = paramInt2;
			redraw();
		}
		if (this.currentAction == 6400) {
			if (PPD.particleArray != null) {
				if (!PPD.animate) {
					Calculus.magicFormula();
				}
				this.lint = Calculus.pot(this.oldlintx, this.oldlinty);
				this.lintPlotter.addData(this.lint);
				this.oldlintx = paramInt1;
				this.oldlinty = paramInt2;
			}
			this.lintx = paramInt1;
			this.linty = paramInt2;
			redraw();
		}
		this.oldx = paramInt1;
		this.oldy = paramInt2;
		return true;
	}

	public boolean mouseUp(Event paramEvent, int paramInt1, int paramInt2) {
		if (this.interactive) {
			if (this.currentAction == 4608) {
				PPD.particleArray = new Particle[PPD.particleBox.size()];
				PPD.particleBox.copyInto(PPD.particleArray);
				Calculus.registerNeightbours();
				redraw();
			} else if (PPD.getGroup(this.currentAction) == 4352) {
				PPD.particleArray = new Particle[PPD.particleBox.size()];
				PPD.particleBox.copyInto(PPD.particleArray);
				redraw();
			} else if (PPD.getGroup(this.currentAction) == 4096) {
				redraw();
			}
		}
		if (this.currentAction == 6144) {
			this.lintPlotter.dispose();
			this.lintPlotter = null;
		}
		if (this.currentAction == 6400) {
			this.lintPlotter.dispose();
			this.lintPlotter = null;
		}
		return true;
	}

	public boolean mouseMove(Event paramEvent, int paramInt1, int paramInt2) {
		if ((this.interactive) && (PPD.getGroup(this.currentAction) != 4096)) {
			Element localElement = this.currentElement;
			if (PPD.getGroup(this.currentAction) == 4864) {
				this.currentElement = Calculus.checkElementArrow(paramInt1, paramInt2);
			} else {
				this.currentElement = Calculus.checkElementBorder(paramInt1, paramInt2);
			}
			if (localElement != this.currentElement) {
				if (localElement != null) {
					localElement.setDrawMode(0);
					redraw(localElement.bounds);
				}
				if (this.currentElement != null) {
					this.currentElement.setDrawMode(1);
					redraw(this.currentElement.bounds);
				}
			} else if ((this.currentElement instanceof ERing)) {
				redraw(this.currentElement.bounds);
			}
		}
		return true;
	}

	public void redraw() {
		if (!PPD.animate) {
			repaint();
		}
	}

	public void redraw(Rectangle paramRectangle) {
		if ((!PPD.animate) && (paramRectangle != null)) {
			repaint();
		}
	}

	public void update(Graphics paramGraphics) {
		Rectangle localRectangle = paramGraphics.getClipBounds();
		this.update_hack = false;
		if ((PPD.CANVAS_DOUBLEBUFFER) && (!PPD.draw_field)) {
			if ((this.backBuffer == null) || (this.buffWidth != size().width) || (this.buffHeight != size().height)) {
				this.buffWidth = size().width;
				this.buffHeight = size().height;
				if (this.backBuffer != null) {
					this.backBuffer.flush();
				}
				this.backBuffer = createImage(this.buffWidth, this.buffHeight);
				this.backGC = this.backBuffer.getGraphics();
				((ERectangle) PPD.nullElement).width = this.buffWidth;
				((ERectangle) PPD.nullElement).height = this.buffHeight;
			}
			this.backGC.setColor(getBackground());
			this.backGC.fillRect(localRectangle.x, localRectangle.y, localRectangle.width, localRectangle.height);
			paintMe(this.backGC);
			paramGraphics.drawImage(this.backBuffer, 0, 0, this);
		} else if (PPD.draw_field) {
			paramGraphics.drawImage(this.backBuffer, 0, 0, this);
		} else {
			paramGraphics.clearRect(localRectangle.x, localRectangle.y, localRectangle.width, localRectangle.height);
			paintMe(paramGraphics);
		}
		setPainted();
	}

	public void paintMe(Graphics paramGraphics) {
		if (this.update_hack) {
			repaint();
			return;
		}
		Draw.drawElements(paramGraphics, PPD.elementBox, paramGraphics.getClipBounds());
		Draw.drawParticles(paramGraphics, PPD.particleBox, PPD.animate);
		if ((this.lintPlotter != null) && (PPD.particleArray != null)) {
			Calculus.plotForceArrow(paramGraphics, this.lintx, this.linty);
		}
		paramGraphics.setColor(Color.black);
		paramGraphics.drawLine(PPD.PPDZero.x - 3, PPD.PPDZero.y - 3, PPD.PPDZero.x + 3, PPD.PPDZero.y + 3);
		paramGraphics.drawLine(PPD.PPDZero.x - 3, PPD.PPDZero.y + 3, PPD.PPDZero.x + 3, PPD.PPDZero.y - 3);
		this.update_hack = true;
	}

	public void loadFile(String paramString) {
		PPD.particleBox.removeAllElements();
		PPD.elementBox.removeAllElements();
		try {
			InputStream localInputStream;
			if (isJS) { // convert string to stream.
				String jsConfig = JacobConfigurations.getConiguration(paramString);
				localInputStream = new ByteArrayInputStream(jsConfig.getBytes());
			} else {
				localInputStream = new URL(Common.getDocumentBase(), paramString).openStream();
			}
			Data.loadFile(PPD.particleBox, PPD.elementBox, localInputStream);
		} catch (Exception localException) {
			System.out.println("load failed: " + localException.getMessage());
		}
		Calculus.registerNeightbours();
		Calculus.correctParticles();
		PPD.particleArray = new Particle[PPD.particleBox.size()];
		PPD.particleBox.copyInto(PPD.particleArray);
		redraw();
	}

	public void loadFile(InputStream paramInputStream) {
		PPD.particleBox.removeAllElements();
		PPD.elementBox.removeAllElements();
		try {
			Data.loadFile(PPD.particleBox, PPD.elementBox, paramInputStream);
		} catch (Exception localException) {
			System.out.println("load failed: " + localException.getMessage());
		}
		Calculus.registerNeightbours();
		Calculus.correctParticles();
		PPD.particleArray = new Particle[PPD.particleBox.size()];
		PPD.particleBox.copyInto(PPD.particleArray);
		redraw();
	}

	public void evolveState() {
		if ((PPD.draw_field) && (PPD.particleArray != null)) {
			int i = 0;
			int j = 0;
			int k = 25;
			int m = 25;
			int n = size().width;
			int i1 = size().height;
			this.interactive = false;
			while (PPD.draw_field) {
				if (j < n) {
					Calculus.drawField(this.backGC, i, j, k, m);
					i += 25;
					if (i > n) {
						i = 0;
						j += 25;
					}
					k = i + 25;
					if (m > n) {
						k = n;
					}
					m = j + 25;
					if (m > i1) {
						m = i1;
					}
				}
				repaint();
				// waitPainted();
			}
			this.interactive = true;
		} else if ((PPD.animate) && (PPD.particleArray != null)) {
			Calculus.magicFormula();
			if (PPD.energyPlotter != null) {
				PPD.energyPlotter.addData(Calculus.energy());
			}
			repaint();
			// waitPainted();
		}
	}

	public void runTimer() {
		timer = new Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				evolveState();
				if (shouldRun) {
					// System.out.println("Running");
					runTimer(); // continue rotation
				}
			}

		});
		timer.setRepeats(false);
		timer.start();
	}

	public void run() {
		this.anim.setPriority(1);
		while (this.anim != null) {
			try {
				Thread.sleep(10L);
			} catch (InterruptedException localInterruptedException1) {
			}
			if (Common.isChanged()) {
				loadFile(Common.getParam());
			}
			if ((PPD.draw_field) && (PPD.particleArray != null)) {
				int i = 0;
				int j = 0;
				int k = 25;
				int m = 25;
				int n = size().width;
				int i1 = size().height;
				this.interactive = false;
				while (PPD.draw_field) {
					if (j < n) {
						Calculus.drawField(this.backGC, i, j, k, m);
						i += 25;
						if (i > n) {
							i = 0;
							j += 25;
						}
						k = i + 25;
						if (m > n) {
							k = n;
						}
						m = j + 25;
						if (m > i1) {
							m = i1;
						}
					}
					repaint();
					try {
						Thread.sleep(10L);
					} catch (InterruptedException localInterruptedException2) {
					}
					waitPainted();
				}
				this.interactive = true;
			} else if ((PPD.animate) && (PPD.particleArray != null)) {
				Calculus.magicFormula();
				if (PPD.energyPlotter != null) {
					PPD.energyPlotter.addData(Calculus.energy());
				}
				repaint();
				waitPainted();
			}
		}
	}

	private synchronized void setPainted() {
		this.painted = true;
		notifyAll();
	}

	private synchronized void waitPainted() {
		while (!this.painted) {
			try {
				wait();
			} catch (InterruptedException localInterruptedException) {
			}
		}
		this.painted = false;
	}
}

/*
 * Location:
 * /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/
 * jacob/PPDCanvas.class Java compiler version: 1 (45.3) JD-Core Version: 0.7.1
 */