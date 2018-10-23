package edu.davidson.graphics;

import java.awt.Color;

import java.awt.event.*;

import java.awt.*;

public class ThreadButton extends Button implements Runnable{
    ActionEvent evt=new ActionEvent(this, ActionEvent.ACTION_PERFORMED,"THREAD");
    Color backgroundColor=Color.lightGray;
    Color downColor=Color.green;
    int longTick=500;
    int shortTick=100;
    private int tick=longTick;
    private boolean running=false;
    private int count=0;

    public ThreadButton() {
        try  {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy(){

          running=true;
    }

    synchronized private void startThread(){
          running=true;
    }

    synchronized private void stopThread(){
        if(!running) return; // we are not running so return.
        running=false;
        tick=longTick;
        count=0;
    }


      /**
	 * The run method passed to the thread.  DO NOT access this method.
	 */
  public void run(){
        //keepRunning=true;
        count=0;
        while (count <100000){
              count++;
              //if(thread!=null )dispatchEvent(evt);
              if(count>0)tick=shortTick;
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

    synchronized void this_mousePressed(MouseEvent e) {
       if (running) return;
       running=true;
       setBackground(downColor);
       startThread();
    }

    synchronized void this_mouseReleased(MouseEvent e) {
       setBackground(backgroundColor);
       stopThread();
       running=false;
    }
}
