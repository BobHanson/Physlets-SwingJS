package jacob;

import a2s.*;
//import exp.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Starter
  extends Applet
{
  public void init()
  {
    String str1 = getParameter("resources");
    if (str1 == null) {
      str1 = "default.rc";
    }
    String str2 = "Resources not loaded: file=" + str1;
    InputStream localInputStream = null;
    try
    {
      URL localURL = Data.class.getResource(str1);
      localInputStream = localURL.openStream();
    }
    catch (Exception localException)
    {
      str2 = localException.getMessage();
    }
    if (localInputStream == null) {
      try
      {
        localInputStream = new URL(getCodeBase(), str1).openStream();
      }
      catch (IOException localIOException1)
      {
        str2 = localIOException1.getMessage();
      }
    }
    if (localInputStream == null) {
      try
      {
        localInputStream = new URL(getDocumentBase(), str1).openStream();
      }
      catch (IOException localIOException2)
      {
        str2 = localIOException2.getMessage();
      }
    }
    if (localInputStream == null)
    {
      System.out.println("Can't load resources! : " + str2);
      return;
    }
    PPD.initResources(localInputStream);
    PPD.setResources();
    Common.setDocumentBase(getDocumentBase());
  }
  
  public void loadFile(String paramString)
  {
    Common.setParam(paramString);
  }
  
  public void startPPD() {}
  
  public void stopPPD() {}
  
  public static void main(String[] paramArrayOfString)
  {
    String str;
    if (paramArrayOfString.length < 1) {
      str = "default.rc";
    } else {
      str = paramArrayOfString[0];
    }
    Common.setApp();
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(str);
      PPD.initResources(localFileInputStream);
    }
    catch (Exception localException)
    {
      System.out.println("Can't load resources! : " + localException.getMessage());
    }
    PPD.setResources();
    Common.startPPD();
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/Starter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */