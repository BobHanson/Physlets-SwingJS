package molecular;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import a2s.*;
import edu.davidson.tools.*;


public class EnsemblePanel extends Panel {
  Color txtcolor = Color.red;
  boolean showTitle = false;
  int txtxoff=0,txtyoff=0;
  String title = null;
  int currentw=0;
  int currenth=0;
  double cwpos=0;
  double lcwpos=0;//left-center wall position
  double rcwpos=0;//right-center wall position used in Diffusion
  int bwidth=0;
  int ppu=10;
  Image osi=null;
  SApplet owner=null;
  Ensemble ensemble1=null;

  public EnsemblePanel(SApplet o) {
    owner=o;
    ensemble1 = new Ensemble(this);
  }

  public void update(Graphics g){
    paint(g);
  }

  // make an image the size of this panel.
  void makeImage(){
    osi = createImage(currentw , currenth);
    if(!MolecularApplet.isJS) {
    	while( !prepareImage(osi,this))
    		try{ Thread.sleep(20); }catch (Exception e){;}
    }
               

  }

  /**
  * Flashes image onto screen
  */
  public void paint(Graphics g){    // changed by W. Christian
    if (osi==null || currentw!=getSize().width || currenth!=getSize().height){
        if(getSize().width>2){
            boolean shouldRun=owner.clock.isRunning();
            owner.clock.stopClock();
            ensemble1.setBounds();
            ensemble1.paintOSI();
            g.drawImage(osi,0,0,currentw,currenth,this);
            if (shouldRun) owner.clock.startClock();
        } else return;
    }else g.drawImage(osi,0,0,currentw,currenth,this);
    if(showTitle) paintCaption(g);
    if(ensemble1.mouseDown) ensemble1.paintCoords(g);  // added by W. Christian
  }

   /**
  *
  * Adds a title to ensemble in top-center
  *
  *
  * @param txt String
  */
  public void addCaption(String txt){
      this.showTitle=true;
      this.title=txt;
  }

   /**
   *
   * Method paints small text box on spectrum to display the wavelength at
   * a given coordinate.
   *
   * @param xcoord int x-coordinate in pixels of position on spectrum
   *
   */
   void paintCaption(Graphics g){
    Font fo = g.getFont();
    Font f = new Font("Arial", Font.BOLD, 14);
    g.setFont(f);
    FontMetrics fm=g.getFontMetrics(g.getFont());
    int boxW=fm.stringWidth(title);
    g.setColor(txtcolor);
    g.drawString(title,txtxoff+(int)((currentw-boxW)/2),txtyoff+(int)(0.1*currenth));
    g.setFont(fo);
  }


  public Ensemble getEnsemble(){
    return ensemble1;
  }


  public EnsemblePanel() {

  }

}