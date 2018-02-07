Bob Hanson, St. Olaf College, hansonr@stolaf.edu

2018.02.06

only Doppler and Optics have been converted (required 5 hr total)

See "BH" for notes in code

 - temporarily removed new SClock in SApplet, as it is unnecessary and blocks with while(true) { ... wait() ... }
 - temporarily removed HintThread for HintPanel

Doppler

 - converted m_doppler thread to javax.swing.Timer
 - fixed problem in a2s.Scrollbar not setting adjustment callback
 - decreased sleepTime from 50 to 5 ms
 
Optics

 - changed Border.border() to Border.myBorder(), as the former conflicts with javax.swing.JPanel
 

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

But I should probably implement MediaTracker to do exactly that anyway. [I have done that now; untested.]


MORE DIFFICULT:

CircuitSimulator needs to be rewritten using javax.swing classes.
Right now it uses symantec.itools, which are not open source.

IGNORED:

Jacob/src contains references to org.mozilla and does not directly 
relate to Physlets. I suggest ignoring it.



DONE

Classes I needed to add to SwingJS:

  - sun.audio.ContinuousAudioDataStream
  - java.awt.SystemColor

In addition, the transpiler now allows for unqualified methods such as "setValue" instead of "setvalue$I"
specifically for extensions of JApplet and Applet. This should make interfacing with JavaScript
easier using a "LiveConnect"-like interface.

Note that any method that is declared in an interface that is in a package such as "api.js"
that is declared in .j2s as a nonqualified class will simply not be qualified. For example:

j2s.compiler.nonqualified.classes=org.jmol.api.js;jspecview.api.js

The intent is to allow for certain Applet methods that are ONLY called by JavaScript, never Java.   


FIXES

- Had to bypass new SClock() as it fired thread.wait()

- java.awt.Panel-->a2s.Panel  required renaming Border.border() Border.myBorder()


THREAD ISSUES:

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

The following 11 uses of wait/notify are in the code:

blackbody.BlackBody: paintThread waiting for new data to graph.paintImage()
   (private use only)
   
efield4.OdeCanvas: delayThread waiting for new data to paint()
   (private use only)

mathapps.FFT2DTransformer: waiting to doTransform()
   (private use only)

script.ScriptLister: waiting to JavaScript window.eval()
   (private use only)

slider.SliderApplet: waiting to run window.eval() whenever data changes in the data source
   (private use only)

edu.davidson.surfaceplotter.DataGenerator: waiting for new data to doCalc()  
   (private use only)

edu.davidson.surfaceplotter.SurfaceCanvas: waiting to doCalc()
   (private use only)

edu.davidson.display.SGraph: waiting for new data to paintOffScreen();
   (private use only)

edu.davidson.graphics.ThreadButton: waiting for dispatchEvent(evt)
  (notify was not implemented; evt is never defined)

edu.davidson.tools.BusyFlag: sets/clears a busy flag indicating that something is happening somewhere
  (Used in bfield, efield4, and faraday; most likely can be ignored in JavaScript)

edu.davidson.tools.SClock: waiting until set to run, then delivers an event to listeners based on a preset delay
  (Used only by page JavaScript?)
  
  
Ideas:

The wait/notify mechanism allows a simple Object to be passed as a token that can be used
to start up waiting processes. 

An alternative might be to implement object.wait() as the addition of a class to a 
"wait list". Then object.notify() would simply run object.waitList.get(i).runNotify(), 
where we define runNotify to be the general action of the loop.

This would then work in both JavaScript and Java. But we sould still have to make 
sure the while loop is not run in JavaScript, or it will lock the browser.  




  






