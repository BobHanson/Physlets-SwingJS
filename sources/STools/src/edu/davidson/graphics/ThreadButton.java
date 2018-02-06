package edu.davidson.graphics;

import java.awt.*;
import java.awt.event.*;

public class ThreadButton extends Button implements Runnable{
    ActionEvent evt=new ActionEvent(this, ActionEvent.ACTION_PERFORMED,"THREAD");
    Color backgroundColor=Color.lightGray;
    Color downColor=Color.green;
    Thread thread=null;
    int longTick=500;
    int shortTick=100;
    private int tick=longTick;
    private boolean running=false;
    private int count=0;
    //private boolean keepRunning=true;
    private Object runLock = new Object();

    public ThreadButton() {
        try  {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        thread = new Thread(this);
        thread.start();
    }

    public void destroy(){
        //keepRunning=false;
        synchronized(runLock){
          thread=null;
          running=true;
          runLock.notifyAll();
        }
    }

    synchronized private void startThread(){
      if(thread==null){
          //keepRunning=true;
          thread = new Thread(this);
          thread.start();
      }else synchronized(runLock){
          running=true;
          runLock.notifyAll();
      }
    }

    synchronized private void stopThread(){
        if(!running) return; // we are not running so return.
        running=false;
        synchronized(runLock){;}   // make sure we are in a wait state before we return.
        tick=longTick;
        count=0;
    }

    /*
    public void run(){
        keepRunning=true;
        count=0;
        while (keepRunning && count <100000){
        try{
            count++;
            if(keepRunning)Thread.sleep(tick);
            if(keepRunning)dispatchEvent(evt);
            if(count>0)tick=shortTick;
         }catch (InterruptedException e){}
        }
        thread.stop();
        thread=null;
    }  */

      /**
	 * The run method passed to the thread.  DO NOT access this method.
	 */
  public void run(){
        //keepRunning=true;
        count=0;
        while (thread!=null  && count <100000){
          synchronized(runLock){
              while(running==false) try{
                  runLock.wait();
              }catch(InterruptedException ie){}
              count++;
              if(thread!=null )dispatchEvent(evt);
              if(count>0)tick=shortTick;
          }
          if(thread!=null )try{Thread.sleep(tick);}catch (InterruptedException ie){}
        }
        //thread.stop();
        //thread=null;
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
