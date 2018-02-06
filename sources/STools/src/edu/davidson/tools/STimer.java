package edu.davidson.tools;

/**
 * The STimer interface is deprecated and has been replaced by SClock.
 *
 * Use SStepable and the SClock for animation.
 *
 *
 * @author             Wolfgang Christian
 * @deprecated         Replaced by the SClock object.
 */
public final class STimer extends Object implements Runnable{
    Thread thread=null;
    private boolean running=false;
    private boolean oneShot=false;
    private TimerClient client=null;
    private int interval;
    boolean keepRunning=false;

  public STimer() {
    interval=100;
  }

  public void start(int i){
    interval=i;
    start();
  }

  public void start(){
    if (thread == null){
      thread = new Thread(this);
      thread.start();
      running=true;
    }
  }

  public void stop(){keepRunning=false;}

  public void stopThread(){
      Thread mythread=thread;
      if(mythread!=null){
         keepRunning=false;
         try{mythread.join();}catch(InterruptedException e){}
         thread=null;
      }
      keepRunning=false;
  }

  public void resume(){
    if(thread != null && thread.isAlive()){
        thread.resume();
        running=true;
    } else start();
  }
  public void resume(int i){
      interval=i;
      resume();
  }

  public void pause(){
    if(thread != null ){
        thread.suspend();
        running=false;
    }

  }


  public void destroy(){
    stopThread();
  }

  public void run(){
    keepRunning=true;
    while (keepRunning && client!=null){
      try{
        Thread.sleep(interval);
        client.tick();
        if(oneShot){keepRunning=false;}
      }catch (InterruptedException e){}
    }
    thread=null;
    running=false;
  }

  public final void doOneShot(){doOneShot(interval);}
  public final void doOneShot(int i){
      setOneShot(true);
      start();
  }

  public final void setOneShot(boolean o){oneShot=o;}
  public final boolean isOneShot(){return oneShot;}

  public final void setRunning(boolean r){running=r;}
  public final boolean isRunning(){return running;}

  public final void setInterval(int i){interval=i;}
  public final int getInterval(){return interval;}

  public final void setClient(TimerClient c){client=c;}
  public final TimerClient getClient(){return client;}

}