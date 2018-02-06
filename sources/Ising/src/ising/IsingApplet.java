// IsingApplet.java -- An applet demonstrating the Ising model.
// Copyright (C) 1999  Ronfeng Sun
// Physlet adaptation by Macneil Shonle (mshonle@planck.clarku.edu)
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

package ising;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class IsingApplet extends Applet {
    private IsingModel experiment;
    private Canvas canvas = new IsingCanvas();
    private Choice linearDimension = new Choice();
    private Button start = new Button("Start");
    private Button step = new Button("Step");
    private Button cont = new Button("Continue");
    private Button stop = new Button("Stop");
    private TextField temperature = new TextField("2.269", 6);
    private TextField field = new TextField("0", 6);
    private TextField mctime = new TextField(6);
    private TextField magnetization = new TextField(8);
    private TextField energy = new TextField(8);
    private TextField meanM = new TextField(8);
    private TextField meanE = new TextField(8);
    private char status = 's';  // 'r' for running, 't' for taking a MC step, 's' for stop
    private int LChoices[] = {4, 8, 16, 32, 64, 128, 256, 512, 1024};
    private int L;
    private double T, H;
    private boolean rePaint;    // indicates the canvas needs to be repainted after system reinitialization

    public void init() {
        setBackground(Color.white);
        setLayout(new BorderLayout());
        Panel top = new Panel();
        Panel bottom = new Panel();
        top.setLayout(new FlowLayout());
        bottom.setLayout(new FlowLayout());

        for(int i=0; i<9; i++)
           linearDimension.addItem("" + LChoices[i]);

        top.add(new Label("L:"));
        top.add(linearDimension);
        top.add(new Label("  T:"));
        top.add(temperature);
        top.add(new Label("  H:"));
        top.add(field);
  	    top.add(start);
  	    top.add(step);
  	    top.add(cont);
     	  top.add(stop);
        add(top, BorderLayout.NORTH);

        bottom.add(new Label("t:"));
        bottom.add(mctime);
        bottom.add(new Label(" M:"));
        bottom.add(magnetization);
        bottom.add(new Label(" E:"));
        bottom.add(energy);
        bottom.add(new Label(" <M>"));
        bottom.add(meanM);
        bottom.add(new Label(" <E>"));
        bottom.add(meanE);
        add(bottom, BorderLayout.SOUTH);

        add(canvas, BorderLayout.CENTER);

        start.addActionListener(new StartListener());
        step.addActionListener(new StepListener());
        cont.addActionListener(new ContListener());
        stop.addActionListener(new StopListener());
        start.addMouseListener(new StartMouseAdapter());
        step.addMouseListener(new StepMouseAdapter());
        stop.addMouseListener(new StopMouseAdapter());
        cont.addMouseListener(new ContMouseAdapter());
        mctime.setEditable(false);
        magnetization.setEditable(false);
        energy.setEditable(false);
        meanM.setEditable(false);
        meanE.setEditable(false);

        L = 32;
        T = 2.269;
        H = 0;

        new MyThread().start();
    }

    // pressing "Start" causes system to reinitialize
    private class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e)  {
            L = LChoices[linearDimension.getSelectedIndex()];
            T = new Double(temperature.getText()).doubleValue();
            H = new Double(field.getText()).doubleValue();
            if(experiment == null)
               experiment = new IsingModel(L, T, H);
            else
               experiment.reinitialize(L, T, H);
            rePaint = true;
            canvas.repaint();
            status = 'r';  // running
        }
    }

    private class StartMouseAdapter extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            showStatus("Initialize/Reinitialize the system with a random configuration and runs in an infinite loop.");
        }
        public void mouseExited(MouseEvent e) {
            showStatus("");
        }
    }

    private class StepListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            status = 't';
        }
    }

    private class StepMouseAdapter extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            showStatus("Advances the simulation by one Monte Carlo step, then stops.");
        }
        public void mouseExited(MouseEvent e) {
            showStatus("");
        }
    }

    private class ContListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(experiment == null) {
               status = 's';
               return;
            }

            double tempT = new Double(temperature.getText()).doubleValue();
            double tempH = new Double(field.getText()).doubleValue();
            if(tempT != T) {
                T = tempT;
                experiment.resetT(T);
            }
            if(tempH != H) {
                H = tempH;
                experiment.resetH(H);
            }
            status = 'r';
        }
    }

    private class ContMouseAdapter extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            showStatus("Resets magnetic field H and temperature T, and continues the simulation with the current configuration.");
        }
        public void mouseExited(MouseEvent e) {
            showStatus("");
        }
    }

    private class StopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            status = 's';
        }
    }

    private class StopMouseAdapter extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            showStatus("Stops the simulation.");
        }
        public void mouseExited(MouseEvent e) {
            showStatus("");
        }
    }

    private class IsingCanvas extends Canvas implements MouseListener, MouseMotionListener, ActionListener {
        int oldWidth = -1;
   	    int oldHeight = -1;
        int oldL = 0;
        int imageOffset = 15;
        int startX, startY, endX, endY;   // mouse positions
        int boxXStartIndex, boxYStartIndex, boxXEndIndex, boxYEndIndex; // box position in spin indices
        int boxX, boxY, boxWidth, boxHeight;  // box position in pixels
        int setOption;  // -1 if change all selected spins to down, 1 to all up, 0 to invert
        int selectionStage=0;  // 0 if spins have not been selected, 1 if spins are selected but popup menu not shown yet, 2 if popup menu
                               // is displayed, but no MenuItem is selected yet. If an menuitem is selected, changes back to 0
        PopupMenu  popup;
    	  Image image = null;

        public IsingCanvas() {
            super();
            addMouseListener(this);
            addMouseMotionListener(this);
            popup = new PopupMenu("Set Spins");
            MenuItem mi;
            popup.add(mi = new MenuItem("Up"));
            mi.addActionListener(this);
            popup.addSeparator();
            popup.add(mi = new MenuItem("Down"));
            mi.addActionListener(this);
            popup.addSeparator();
            popup.add(mi = new MenuItem("Invert"));
            mi.addActionListener(this);
            add(popup);
        }

   	    public void update(Graphics g) {
	        paint(g);
	    }

        public void paint(Graphics g) {
            Dimension size = getSize();
            g.draw3DRect(imageOffset-5, imageOffset-5, size.width - 2*imageOffset+10, size.height - 2*imageOffset+10, true);
            if(experiment != null) {
                int width = size.width - 2*imageOffset;
                int height = size.height - 2*imageOffset;

                // if the window has been resized
                if(oldWidth != width || oldHeight != height || image == null) {
                    image = createImage(width, height);
                    oldWidth = width;
                    oldHeight = height;
                    Graphics buff = image.getGraphics();
                    buff.setPaintMode();
                    experiment.reDrawSpins(buff, 0, 0, width, height);
                }
                else if(oldL != L || rePaint) {
                    oldL = L;
                    rePaint = false;
                    Graphics buff = image.getGraphics();
                    buff.setPaintMode();
                    buff.clearRect(0, 0, width, height);
                    experiment.reDrawSpins(buff, 0, 0, width, height);
                }
                else {
                    Graphics buff = image.getGraphics();
                    buff.setPaintMode();
                    experiment.drawSpins(buff, 0, 0, width, height);
                }

                g.drawImage(image, imageOffset, imageOffset, this);
                mctime.setText("" + experiment.time_counter);
                magnetization.setText("" + experiment.getM());
                energy.setText("" + experiment.getE());
                meanM.setText("" + ((int)(100*experiment.ave_M/experiment.time_counter))/100.0);
                meanE.setText("" + ((int)(100*experiment.ave_E/experiment.time_counter))/100.0);
            }
       }

       public void mousePressed(MouseEvent e) {
            if(status != 's' || experiment == null || image == null) return;
            else if(selectionStage == 0) {
                startX = e.getX() - imageOffset;   // buffer coordinates and canvas coordinates differ by imageOffset
                startY = e.getY() - imageOffset;
                boxXStartIndex = boxXEndIndex = boxYStartIndex = boxYEndIndex = 0;
                boxX = boxY = boxWidth = boxHeight = 0;
            }
            else if(selectionStage > 0) {
                int xPos = e.getX() - imageOffset;
                int yPos = e.getY() - imageOffset;
                if( xPos >= boxX && xPos <= (boxX + boxWidth) && yPos >= boxY && yPos <= (boxY + boxHeight)) {
  	                popup.show(this, xPos, yPos);
  	                selectionStage = 2;
  	            }
  	            else {
                    Graphics buff = image.getGraphics();
                    Graphics g = this.getGraphics();
                    buff.setXORMode(Color.white);
                    buff.setColor(Color.gray);
                    buff.drawRect(boxX, boxY, boxWidth, boxHeight);
                    g.drawImage(image, imageOffset, imageOffset, this);
                    if(selectionStage == 2)
                        selectionStage = 1;
                }
            }
        }

        public void mouseDragged(MouseEvent e) {
            if(status != 's' || selectionStage != 0 || experiment == null || image == null) return;
            else {
                Graphics buff = image.getGraphics();
                Graphics g = this.getGraphics();
                buff.setXORMode(Color.white);
                buff.setColor(Color.gray);
                buff.drawRect(boxX, boxY, boxWidth, boxHeight);  // clear the previously drawn rectangle

                endX = e.getX() - imageOffset;
                endY = e.getY() - imageOffset;
                int minX = startX, maxX = endX;
                if(maxX < minX) {
                    maxX = minX;
                    minX = endX;
                }
                int minY = startY, maxY = endY;
                if(maxY < minY) {
                    maxY = minY;
                    minY = endY;
                }

                double dx = (double)(oldWidth) / L;
                double dy = (double)(oldHeight) / L;
                boxXStartIndex = (int)(minX/dx);
                boxYStartIndex = (int)(minY/dy);
                boxXEndIndex = (int)(maxX/dx);
                boxYEndIndex = (int)(maxY/dy);

                boxX = (int)(boxXStartIndex * dx) + 1;   // +1 so the box boundary will be clearly shown
                boxY = (int)(boxYStartIndex * dy) + 1;
                boxWidth = (int)((boxXEndIndex - boxXStartIndex + 1) * dx) - 2;
                boxHeight = (int)((boxYEndIndex - boxYStartIndex + 1) * dy) - 2;
                buff.drawRect(boxX, boxY, boxWidth, boxHeight);
                g.drawImage(image, imageOffset, imageOffset, this);
            }
        }

        public void mouseReleased(MouseEvent e) {
            if(status != 's' || experiment == null || image == null) return;
            else if(selectionStage == 0) {
                mouseDragged(e);     // performs the drawing of rectangle and spins, etc.
                selectionStage = 1;
            }
            else if(selectionStage == 1)
                selectionStage = 0;
        }

        public void mouseMoved(MouseEvent e) {
            if(status != 's' || experiment == null || image == null) return;
            if(selectionStage == 0)
                showStatus("Mouse press, drag, and release selects a bloc of spins.");
            else if(selectionStage == 1)
                showStatus("Mouse click within the selected bloc of spins opens popup menu.");
        }

        public void mouseExited(MouseEvent e) {
            showStatus("");
            if(status != 's' || experiment == null || image == null) return;
            if(selectionStage > 0) {
                Graphics buff = image.getGraphics();
                buff.setXORMode(Color.white);
                buff.setColor(Color.gray);
                buff.drawRect(boxX, boxY, boxWidth, boxHeight);
                Graphics g = this.getGraphics();
                g.drawImage(image, imageOffset, imageOffset, this);
                selectionStage = 0;
            }
        }

        public void mouseEntered(MouseEvent e) { }
        public void mouseClicked(MouseEvent e) { }

        public void actionPerformed(ActionEvent e) {
            if(status != 's' || selectionStage != 2 || experiment == null || image == null) return;

            Graphics buff = image.getGraphics();
            buff.setXORMode(Color.white);
            buff.setColor(Color.gray);
            buff.drawRect(boxX, boxY, boxWidth, boxHeight);

            if(e.getActionCommand().equals("Up"))
                setOption = 1;
            else if(e.getActionCommand().equals("Down"))
                setOption = -1;
            else if(e.getActionCommand().equals("Invert"))
                setOption = 0;
            experiment.setSpinBloc(boxXStartIndex, boxYStartIndex, boxXEndIndex, boxYEndIndex, setOption);
            repaint();
            selectionStage = 0;
        }
    }

    private class MyThread extends Thread {
        public void run() {
            for(;;) {
                if(experiment != null)
                    if(status == 'r') {
                       experiment.onestep();
                       canvas.repaint();
                    }
                    else if(status == 't') {
                       experiment.onestep();
                       canvas.repaint();
                       status = 's';
                    }
                try {
                       sleep(50);
                } catch (InterruptedException e) { }
            }
        }
    }
}
