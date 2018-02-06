
/**
 * Title:        Davidson Java Tools<p>
 * Description:  Java tools for the creation of Physlets~nwritten at Davidson College.<p>
 * Copyright:    Copyright (c) 1999<p>
 * Company:      Davidson College<p>
 * @author Wolfgang Christian
 * @version
 */
package edu.davidson.views;

import java.awt.*;
import java.awt.event.*;


public class STextDialog extends Dialog {
    BorderLayout borderLayout1 = new BorderLayout();
    STextArea textArea = new STextArea();

    public STextDialog(Frame frame, String title){
        super(frame);
        try  {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setSize(400,300);
        setLocation((int)(300*Math.random()),(int)(300*Math.random()) );
        if(title==null)setTitle(getClass().getName() );
          else setTitle(title);

        //textArea= new TextArea(100,20));
		    textArea.setEditable(false);
		    textArea.setBackground(Color.white);
    }

    public STextArea getTextArea(){ return textArea;}

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
    this.add(textArea, BorderLayout.CENTER);
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