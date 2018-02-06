package edu.davidson.graphics;

import java.awt.*;
import java.awt.event.*;

public class SFrame extends Frame
{
    BorderLayout borderLayout1 = new BorderLayout();

    public SFrame() {this("NO_TITLE"); }
    public SFrame(String title) {
        super();
        if(title.equals("NO_TITLE")) title= getClass().getName();
        setSize(400,300);
        setLocation((int)(300*Math.random()),(int)(300*Math.random()) );
        addWindowListener(new WindowAdapter() { public void
        windowClosing(WindowEvent e) { setVisible(false); dispose();} } );
        setTitle(title);
    }
}
