package edu.davidson.tools;

import netscape.javascript.JSObject;

public class OnloadThread extends Thread{
  static final int delay=100;  // delay time in ms
  JSObject win;
  String jsFunction;
  String appletNames;
  SApplet applet;
  boolean halt=false;

  public OnloadThread(SApplet app, String onload, String names) {
    jsFunction=onload;
    appletNames=names;
    applet=app;
    try {win=JSObject.getWindow(applet);}
    catch (Exception e){win=null;
       if(app.debugLevel>127)System.out.println("JSObject getWindow failed.");
    }
    //setDaemon(true);
    start();
  }

  public void run(){
    int count=100; // wait up to 100 delay times for all appelts to load
    while(count>0 && !halt && !applet.isActive()){
      if(applet.debugLevel>127)System.out.println("Running OnloadThread.");
      count--;
      try {
        Thread.sleep(delay);
        if(halt) return;
        if(applet.checkAppletNames(appletNames))break;  // break out of loop if applet names match
        if(halt) return;
      } catch(Exception ex) {
        return;
      }
    }
    try{
      if(halt  || !applet.isActive() ) return;
      win.eval(jsFunction);
      applet.scriptHasRun=true;
      }catch (Exception ex){}
  }
}