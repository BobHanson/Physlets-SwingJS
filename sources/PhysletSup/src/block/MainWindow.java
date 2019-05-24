package block;


import java.awt.Event;
import java.awt.Graphics;

import java.awt.*;
// 
// Decompiled by Procyon v0.5.30
// 

class MainWindow extends Frame
{
    boolean is_applet;
    Animation anim;
    
    public void paint(final Graphics g) {
        this.anim.reset(false);
    }
    
    MainWindow(final String title, final boolean isapp) {
        super(title);
        this.is_applet = isapp;
        this.add("Center", this.anim = new Animation(this));
    }
    
    public void start() {
        this.anim.reset(true);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.hide();
            this.removeAll();
            this.dispose();
            if (!this.is_applet) {
                System.exit(0);
            }
        }
        return super.handleEvent(e);
    }
}
