package edu.davidson.graphics;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import a2s.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HintPanel extends Panel{

  String text="";
  boolean hintVisible=false;

  public HintPanel() {
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent event) {
        hintVisible=true;
        repaint();
      }

      public void mouseExited(MouseEvent event) {
        hintVisible=false;
        repaint();
      }
    }); // end of addMouseLinstener
  }
  public void setBubbleHelp(String txt){
    if(txt==null)text="";
      else text=txt;
    if(text.equals(""))hintVisible=false;
  }

  public void updateBubbleHelp(String txt){
	  if (true)
		  return; // BH SwingJS for now no HintThreads.
    if(txt==null)text="";
      else text=txt;
    if(text.equals("")){
      hintVisible=false;
      repaint();
    } else{
      if(!hintVisible)new HintThread(this);
    }
  }

  public void forceBubbleHelp(String txt){
   if(txt==null)text="";
     else text=txt;
   if(text.equals(""))hintVisible=false;
   repaint();
  }

  public void destroyHint(){
    hintVisible=false;
  }

  public void paintHint(Graphics g){
    if(!hintVisible) return;
    g.setColor(Color.yellow);
    g.fillRect(0,getBounds().height-20,getBounds().width-1,19);
    g.setColor(Color.black);
    g.drawRect(0,getBounds().height-20,getBounds().width-1,19);
    FontMetrics fm   = g.getFontMetrics();
    g.drawString(text,2,-fm.getDescent()+getBounds().height-2);
  }


}

class HintThread extends Thread {
  HintPanel hp;
  int bubbleInterval = 200;


  public HintThread(HintPanel hp) {
    this.hp = hp;
    hp.hintVisible=true;
    this.start();
  }
  public void run() {
    long    start = System.currentTimeMillis();
    boolean done = false;
    while(!done) {
      long delta = System.currentTimeMillis() - start;
      try{
        Thread.sleep(50);
      }catch (InterruptedException e){}
      if(delta > bubbleInterval) {
        hp.repaint();
        done = true;
      }
    }
  }
}
