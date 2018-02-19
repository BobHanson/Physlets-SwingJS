package edu.davidson.graphics;

//import java.awt.*;
import a2s.*;

import java.awt.Color;
import java.awt.event.*;

public class TreadButton extends Button {

    public TreadButton() {
        try  {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
            public void mouseReleased(MouseEvent e) {
                this_mouseReleased(e);
            }
        });
    }

    void this_mousePressed(MouseEvent e) {
       setBackground(Color.green);

    }

    void this_mouseReleased(MouseEvent e) {
       setBackground(Color.gray);
    }
} 
