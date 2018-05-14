package jacob;

public class PlotterTest
  implements Runnable
{
  Thread thread = null;
  Plotter plotter = new Plotter("Sin", 100, 200);
  double a = 0.0D;
  
  public void start()
  {
    if (this.thread == null)
    {
      this.thread = new Thread(this);
      this.thread.start();
    }
  }
  
  public void stop()
  {
    if ((this.thread != null) && (this.thread.isAlive())) {
      this.thread.stop();
    }
  }
  
  public void run()
  {
    this.thread.setPriority(1);
    while (this.thread != null)
    {
      try
      {
        Thread.sleep(10L);
      }
      catch (InterruptedException localInterruptedException) {}
      this.plotter.addData((Math.sin(this.a) + 1.0D) / 2.0D);
      this.a += 0.05D;
    }
  }
  
  public static void main(String[] paramArrayOfString)
  {
    PlotterTest localPlotterTest = new PlotterTest();
    localPlotterTest.start();
  }
}


/* Location:              /Users/wochristian/Desktop/DecompilerTest/PhysletsSup.jar!/jar/Jacob.jar!/jacob/PlotterTest.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */