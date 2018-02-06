

package pipes;


import java.awt.*;


public class Legend extends Thing {
  int red[] = new int[255];
  int green[] = new int[255];
  int blue[] = new int[255];

  public Legend(int xpos, int ypos , int width, int height, Color c,Pipe o, boolean e, boolean m) {
    //Thing(int x, int y, int width, int height, Color c, Pipe o, boolean e, boolean m)
    super(xpos,ypos,width,height,c,o,e,m);
    makeSpectrum();
  }

  public void makeSpectrum(){
      int i,j;
      for (i=0; i<255; i++){
        red[i]=i;
        green[i]=i;
        blue[i]=i;
      }
  }

  public synchronized void paintOS(Graphics g){
    int val = 0;
    //img = createImage(width,height);
    //Graphics osg = img.getGraphics();
    for (int i=0; i<w; i++){
      val = (int)(255.0*i/(double)w);
      Color linecolor = new Color(red[val],green[val],blue[val]);
      g.setColor(linecolor);
      g.drawLine(i,0,i,h);
      }
    //osg.dispose();
  }

  //public void paintOS(Graphics g){

  //}

  public void setIsEnabled(boolean e){
  this.enabled = e;
  }
}