
/**
 * Title:        Mandelbrot<p>
 * Description:  Calculate the Mandelbrot set.<p>
 * Copyright:    Copyright (c) 1999<p>
 * Company:      Physlets<p>
 * @author Wolfgang Christian
 * @version
 */
package mandelbrot;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//import java.awt.*;
import a2s.Frame;


public class MandelFrame extends Frame {
    BorderLayout borderLayout1 = new BorderLayout();
    Mandelbrot mandelbrot = null;


    public MandelFrame(Mandelbrot mb) {
        super();
        mandelbrot=mb;
        setLayout(borderLayout1);
        add(mandelbrot, BorderLayout.CENTER);
        setSize(300,200);
        setLocation((int)(300*Math.random()),(int)(300*Math.random()) );
        addWindowListener(new WindowAdapter() { public void
            windowClosing(WindowEvent e) {
            mandelbrot.destroy();
            setVisible(false); dispose();
            } }
        );
        setTitle(getClass().getName());
    }
}

