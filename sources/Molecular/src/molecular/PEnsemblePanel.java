

package molecular;

import java.awt.Graphics;

import edu.davidson.tools.*;

public class PEnsemblePanel extends EnsemblePanel {
  PistonEnsemble ensemble=null;

  public PEnsemblePanel(SApplet o) {
    super(o);
    ensemble=null;
    ensemble = new PistonEnsemble(this);
  }

  public void update(Graphics g){
    paint(g);
  }


  /**
  *
  * Flashes offscreen image onto screen
  *
  *
  */
  /*public void paint(Graphics g){
    if (osi==null){
    }
    else{
    g.drawImage(osi,0,0,currentw,currenth,this);
    }
  } */

      /**
  *
  * Flashes image onto screen
  *
  *
  */
  public void paint(Graphics g){    // changed by wc
    if (osi==null || currentw!=getSize().width || currenth!=getSize().height){
        if(getSize().width>1){
            boolean shouldRun=owner.clock.isRunning();
            owner.clock.stopClock();
            ensemble1.setDefault();
            ensemble1.setBounds();
            ensemble1.paintOSI();
            g.drawImage(osi,0,0,currentw,currenth,this);
            if (shouldRun) owner.clock.startClock();
        } else return;
    }else g.drawImage(osi,0,0,currentw,currenth,this);
    if(showTitle) paintCaption(g);
    if(ensemble1.mouseDown) ensemble1.paintCoords(g);  // added by W. Christian
  }

  public PistonEnsemble getPEnsemble(){
    return ensemble;
  }

  
  public PEnsemblePanel() {
  }


}
