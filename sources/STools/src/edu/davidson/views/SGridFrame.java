package edu.davidson.views;

import java.awt.BorderLayout;

/**
 * Title:        DataTable
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      Physlets
 * @author Wolfgang Christian
 * @version 1.0
 */

//import java.awt.*;
import java.awt.event.*;
import a2s.*;


public class SGridFrame extends Frame {
    SGrid sgrid;
    BorderLayout borderLayout1 = new BorderLayout();
    ScrollPane scrollPane = new ScrollPane();

    public SGridFrame() {
       this(50,3);
    }

    public SGridFrame(int nrows, int ncols) {
        super();
        sgrid = new SGrid(nrows,ncols);
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


    public SGrid getSGrid(){return sgrid;}

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
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
    this.add(scrollPane, BorderLayout.CENTER);
    scrollPane.add(sgrid, BorderLayout.CENTER);
    }

  void this_windowOpened(WindowEvent e) {
    //System.out.println("Window opening");
  }

  void this_windowClosing(WindowEvent e) {
      //System.out.println("Window closing");
      setVisible(false);
      //dispose();
  }

  void this_windowActivated(WindowEvent e) {
     //System.out.println("Window activated");
  }

  void this_windowDeiconified(WindowEvent e) {
     //System.out.println("Window Deiconified");
  }
}