package engine;

//*************************************************
import java.awt.*;
import java.applet.Applet;
//*************************************************
   public class Xdevshell_1Frame extends Frame { 
   Applet parent;
//*************************************************
   // Constructor
   public Xdevshell_1Frame(Applet parentApplet) { 
   parent = parentApplet; 
   }
//*************************************************
   public boolean handleEvent(Event event) { 
      parent.postEvent(event);
      return super.handleEvent(event); 
   }
} 
