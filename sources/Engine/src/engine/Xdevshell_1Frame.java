package engine;

//*************************************************

import java.awt.Event;
import a2s.*;
import edu.davidson.tools.SApplet;

//import java.applet.Applet;
//*************************************************
   public class Xdevshell_1Frame extends Frame { 
   SApplet parent;
//*************************************************
   // Constructor
   public Xdevshell_1Frame(SApplet parentApplet) { 
   parent = parentApplet; 
   }
//*************************************************
   public boolean handleEvent(Event event) { 
      parent.postEvent(event);
      return super.handleEvent(event); 
   }
} 
