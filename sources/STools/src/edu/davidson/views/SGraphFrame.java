package edu.davidson.views;

import java.awt.BorderLayout;
//import java.awt.*;
import java.awt.event.*;
import edu.davidson.display.SGraph;
import a2s.*;


public class SGraphFrame extends Frame {
    BorderLayout borderLayout1 = new BorderLayout();
    edu.davidson.display.SGraph graph = null;

    public SGraphFrame() {
        super();
        graph = new edu.davidson.display.SGraph();
        try  {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setSize(400,300);
        setLocation((int)(300*Math.random()),(int)(300*Math.random()) );
        setTitle(getClass().getName());
    }

    public SGraphFrame(SGraph g) {
        super();
        graph=g;
        try  {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(borderLayout1);
        add(graph, BorderLayout.CENTER);
        setSize(300,200);
        setLocation((int)(300*Math.random()),(int)(300*Math.random()) );
        setTitle(getClass().getName());
    }

    public SGraph getGraph(){ return graph;}

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        graph.setSampleData(false);
        this.addWindowListener(new java.awt.event.WindowAdapter() {

      public void windowOpened(WindowEvent e) {
        this_windowOpened(e);
      }

      public void windowClosing(WindowEvent e) {
        this_windowClosing(e);
      }

      public void windowActivated(WindowEvent e) {
        this_windowActivated(e);
      }

      public void windowDeiconified(WindowEvent e) {
        this_windowDeiconified(e);
      }
    });
    this.add(graph, BorderLayout.CENTER);
    }

  void this_windowOpened(WindowEvent e) {
    //System.out.println("Window opening");
    if(graph!=null)graph.startPaintThread();
  }

  void this_windowClosing(WindowEvent e) {
      //System.out.println("Window closing");
      if(graph!=null)graph.destroy();
      setVisible(false);
      //dispose();
  }

  void this_windowActivated(WindowEvent e) {
     //System.out.println("Window activated");
     if(graph!=null)graph.startPaintThread();
  }

  void this_windowDeiconified(WindowEvent e) {
     //System.out.println("Window Deiconified");
     if(graph!=null)graph.startPaintThread();
  }
}

