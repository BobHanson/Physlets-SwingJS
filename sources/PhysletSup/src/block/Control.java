package block;

import java.awt.Event;

import java.awt.*;

class Control extends Panel
{
    TextField mouseP;
    Animation anim;
    Button cg;
    
    Control(final Animation an) {
        this.anim = an;
        this.add(this.cg = new Button("Show c.g."));
        this.add(this.mouseP = new TextField("0 , 0", 10));
        this.add(new Button("Reset"));
    }
    
    public boolean action(final Event ev, final Object arg) {
        final String label = (String)arg;
        if (ev.target instanceof Button) {
            if (label.equals("Reset")) {
                this.anim.reset(true);
            }
            else if (label.equals("Hide c.g.")) {
                this.anim.cgState(false);
                this.cg.setLabel("Show c.g.");
            }
            else if (label.equals("Show c.g.")) {
                this.anim.cgState(true);
                this.cg.setLabel("Hide c.g.");
            }
            return true;
        }
        return false;
    }
}
