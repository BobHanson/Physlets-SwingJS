package jacob;
import java.net.URL;

public abstract class Common
{
  public static PPDFrame ppd = null;
  private static URL documentBase = null;
  private static String param = null;
  private static boolean changed = false;
  private static boolean running = false;
  private static boolean application = false;
  
  public static synchronized void startPPD()
  {
    ppd = new PPDFrame();
    running = true;
  }
  
  public static synchronized void stopPPD()
  {
    running = false;
    if (ppd != null)
    {
      ppd.stop();
      ppd.removeNotify();
      ppd.dispose();
      ppd = null;
    }
    if (application) {
      System.exit(0);
    }
  }
  
  public static int getPPDWidth()
  {
    if (ppd == null) {
      return 0;
    }
    return ppd.size().width;
  }
  
  public static int getPPDHeight()
  {
    if (ppd == null) {
      return 0;
    }
    return ppd.size().height;
  }
  
  public static void resizePPD(int paramInt1, int paramInt2) {}
  
  public static synchronized void setParam(String paramString)
  {
    param = paramString;
    changed = true;
  }
  
  public static synchronized String getParam()
  {
    changed = false;
    return param;
  }
  
  public static synchronized boolean isChanged()
  {
    return changed;
  }
  
  public static synchronized boolean isRunning()
  {
    return running;
  }
  
  public static synchronized void setApp()
  {
    application = true;
  }
  
  public static synchronized boolean isApp()
  {
    return application;
  }
  
  public static synchronized void setDocumentBase(URL paramURL)
  {
    documentBase = paramURL;
  }
  
  public static synchronized URL getDocumentBase()
  {
    return documentBase;
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Common.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */