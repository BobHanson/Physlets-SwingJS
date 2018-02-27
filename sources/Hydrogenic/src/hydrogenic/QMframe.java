
//Title:        Radial Project
//Version:      
//Copyright:    Copyright (c) 1998
//Author:       Cabell Fisher
//Company:      Fisher Radiology
//Description:  Your description

package hydrogenic;

import java.awt.Dimension;
import java.awt.FlowLayout;
//import java.awt.*;
import java.awt.event.*;
import a2s.*;

public class QMframe extends Frame {
  Label label1 = new Label();
  Button closebtn = new Button();
  FlowLayout flowLayout1 = new FlowLayout();

  public QMframe() {
    super();
    addWindowListener(new WindowAdapter() { public void
    windowClosing(WindowEvent e) { dispose();} } );
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    QMframe QMframe1 = new QMframe();
  }

  private void jbInit() throws Exception {
    this.setLayout(flowLayout1);
    /** @j2sNative */{
    	  this.setSize(new Dimension(220, 92));
    }
    this.setTitle("Error");
    label1.setAlignment(1);
    label1.setText("Warning:  Your l value is out of range!");
    closebtn.setLabel("OK");
    closebtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        closebtn_actionPerformed(e);
      }
    });
    this.add(label1, null);
    this.add(closebtn, null);
  }

  void closebtn_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }
}

                 
