Bob Hanson, St. Olaf College, hansonr@stolaf.edu

2018.02.06

Just a very preliminary assessment of the issues that might arise 
if we wanted to convert Physlets to JavaScript.

* indicates "Will probably require some minor additions to SwingJS.

EASY:

Create a single Eclipse project for all applets.
 - dump PhysletsWorkspace into a folder sources/
 - add all src directories to the main project properties
  
Preferably, move classes to be under edu.davidson.physlets.
This would allow compatibility with other applets and removes the
issue of name-space collision. But it is not absolutely necessary.


EASY:

Add the SwingJS a2s package, which converts non-Swing components
to Swing compoenents.


EASY:

Make explicit all imports. That is, no wildcards such as "include java.awt.*" 
We need to know explicitly what java.awt classes in particular are being accessed,
since SwingJS requires javax.swing components exclusively, not java.awt components. 
 

We need to discover all use of java.awt controls such as Button, Checkbox, etc. 

From what I can see, this includes:

Button, Checkbox, CheckboxGroup, Choice, Frame, Label, 
Menu, MenuBar, MenuItem, Panel, Scrollbar, TextArea, TextField 

This is easy but time-consuming in Eclipse using ALT-S G. 


EASY:

(*) netscape.javascript.JSObject is not implemented in SwingJS. 
But I don't see why it couln't be. It only involves a few methods.   


MODERATELY EASY:

(*)Replace references to java.awt controls with references to a2s controls.

SwingJS support for non-Swing components is on a need-to-have basis. So
there could be some issues here, and I would need to work with you to fix those.


MORE DIFFICULT: 

Rewrite threads to use javax.swing.Timer. 

HTML5 runs on a single thread, so any java.lang.Thread extension
must be rewritten in a state-driven (INIT,LOOP,DONE) mode.
I have never had any problem with this, but it does take some careful
work. I have lots of examples of how to do this, though. 

There are a few places where this could be an issue:

 MediaTracker

      MediaTracker tracker=new MediaTracker(this);
      try{
            tracker.addImage(im,0);
            tracker.waitForID(0,1000);  // wait one second

As I recall, this is not an issue; we can certainly load images. 
In PhET I see the code was changed as follows:

            public Image fetchImage( URL imageLocation ) throws IOException {
                Image image = null;
//                try {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                image = toolkit.createImage( imageLocation );
//                        MediaTracker tracker = new MediaTracker( this );
//                        tracker.addImage( image, 0 );
//                        tracker.waitForAll();
//                }
//                catch ( InterruptedException e ) {
//                }
                return image;
            }

But I should probably implement MediaTracker to do exactly that anyway.

This motif is not possible in HTML5, because there is only one thread, and it cannot wait:

    public void run() {
      while (appletRunning  && shouldRun) {
        synchronized (lock) {
          if (!newdata)
            try {
              lock.wait();
            }catch (InterruptedException ie) {}
            newdata = false;
        }
        if(debugLevel >0)System.out.println("evaluating");
        if(appletRunning && shouldRun) jso.eval(jsFunction);
        try{Thread.sleep(20);} catch(InterruptedException ex){}
      }
    }






MORE DIFFICULT:

CircuitSimulator needs to be rewritten using javax.swing classes.
Right now it uses symantec.itools, which are not open source.

UNKNOWN:

Jacob/src contains references to org.mozilla. Is it possible that
the Jacob project is unnecessary?



DONE

Classes I needed to add to SwingJS:

  - sun.audio.ContinuousAudioDataStream
  - java.awt.SystemColor
  

FIXES

Had to bypass new SClock() as it fired thread.wait()

java.awt.Panel-->a2s.Panel  required renaming Border.border() Border.myBorder()
  




