

package pipes;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

//import java.awt.*;
import edu.davidson.graphics.*;

public class Pipescale extends EtchedBorder{
  Image img = null;
  Color navy = new Color(0,0,170);
  Color cran = new Color(170,0,0);
  boolean grayscale = true;
  int ht = 10;
  int white[] = new int[255];
  int black[] = new int[255];
  int red[] = new int[503];
  int green[] = new int[503];
  int blue[] = new int[503];


  public Pipescale() {
  }

  public void update(Graphics g){
  paint(g);
  }

  public void paint(Graphics g){
    //Graphics g = this.getGraphics();
    int width = this.getSize().width;
    int height = this.getSize().height;
    if (img==null){}
    else{
      g.setColor(navy);
      g.fillRect(0,0,width,height);
      g.drawImage(img,0,0,width,height,this);
      g.setColor(navy);
      g.fillRect(0,height-3,width,height);
      g.fillRect(0,0,width,3);
    }
    //g.dispose();
  }

 public void drawScale(){
    makeSpectrum();
    int i,j;
    int colors[] = new int[4];    //stores 4 bytes: amt of red, blue, green and transparency in each pix.
    int w = this.getSize().width;
    int h = this.getSize().height;

    paintOSI(w,h);
    repaint();
 }


 public void makeSpectrum(){
    int i,j;
    if (grayscale){
      for (i=0; i<255; i++){
      red[i]=i;
      green[i]=i;
      blue[i]=i;
      }
    return;
    }
    else{
    for (i=0; i<249; i++)
      {red[i]=249;
       blue[i]=i;
       green[i]=0;}
    for (i=249; i<500; i++)
      {red[i]=499-i;
       blue[i]=249;
       green[i]=0;}
    red[500]=0;       //piston color
    green[500]=128;
    blue[500]=100;

    red[501]=64;         //color of walls
    green[501]=64;
    blue[501]=64;

    red[502]=255;     //color of outside border
    green[502]=215;
    blue[502]=160;




    }
  }
  //public double calcPress(int i, int j, int width, int height){}
  //public double[] stepTime(double vals[], double dt){}

  public synchronized void paintOSI(int width, int height){
    int i;
    int val = 0;
    img = createImage(width,height);
    Graphics osg = img.getGraphics();
    for (i=0; i<width; i++){
      val = (int)(255.0*i/(double)width);
      Color linecolor = new Color(red[val],green[val],blue[val]);
      osg.setColor(linecolor);
      osg.drawLine(i,0,i,height);
      }
    osg.dispose();
  }

}