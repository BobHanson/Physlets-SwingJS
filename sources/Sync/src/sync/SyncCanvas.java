package sync;
import java.awt.*;
import java.util.*;
import edu.davidson.tools.SApplet;

//Drawing methods
class SyncCanvas extends Canvas {
  SApplet applet;

  public SyncCanvas(SApplet app) // constructor for Canvas
  {
    applet=app;
    resetSource();
  }

  public  void resetSource(){
    time=(int)aState.resetTime();
    aState.setOrigin(getSize().width / 2, getSize().height /2);
    genVec.removeAllElements();
    //while(genVec.size()>0) genVec.removeElementAt(0);
    res_counter=0; //reset the counter
  }

  public  void setSpeed(double vMax_){
    String str=aState.getClass().getName();
    if(!str.equals("InertialState")) res_expand=1;
    else if (vMax_<0.8) res_expand=2;
    else if (vMax_<0.9) res_expand=4;
    else res_expand=8;
    res_counter=0; //reset the counter
    aState.setVMax(vMax_);
    if(aState.t==0)genVec.removeAllElements();
  }

  void setResolution(int res_){
    if (res_>0) resolution=res_;
  }

  void setNumSteps(int ns){
    if (ns>0) numWaves=ns;
  }

  public  void setState(State state){
    aState=state;
    aState.setOrigin(getSize().width/2, getSize().height /2);
    time=(int)aState.resetTime();
    res_counter=0; //reset the counter
    while(genVec.size()>0)
    {
      genVec.removeElementAt(0);
    }
  }


  void incTime(){
    time=(int)aState.incTime(resolution);
    double xs=aState.getX();
    double ys=aState.getY();
    double vxs=aState.getV();
    double vys=aState.getU();

    if (res_counter<=0){	//add a new generation
      //System.out.println("time="+time+" x="+xs+" y="+ys);
      genVec.addElement(new Generation(time,xs,ys,vxs,vys));  //check y motion
      if(genVec.size()>numWaves) genVec.removeElementAt(0);
      res_counter=res_expand-1; //reset the counter
    } else 	res_counter--;
    //paint();
    if(xs>=getSize().width) shiftSource(-getSize().width,0);
    if(ys>=getSize().height) shiftSource(0,-getSize().height);

  }

  void paint(){
    if(applet.destroyed) return;
    Graphics g=this.getGraphics();
    if(g==null) return;
    paint(g);
    g.dispose();
  }

  public void shiftSource(int x, int y)
  {
    int len=genVec.size();	//length of vector
    if (len<=0) return;
    for(int i=0; i<len;i++)
    {
      ((Generation)(genVec.elementAt(i))).shift(x,y);
    }
    aState.shiftOrigin(x,y);

  }

  public void calcBuffImage(){
    double max;
    if (getSize().width>getSize().height)max=getSize().width; else max=getSize().height;
    int i;
    Vector tempVec=(Vector) genVec.clone();
    int len=tempVec.size()-1;	//counter maximum
    Graphics g=buff_image.getGraphics();
    Generation gen1,gen2;
    g.clearRect(0,0,buff_width,buff_height);
    g.setColor(Color.black);
    if (len<=1){
      g.setColor(Color.red);
      g.fillOval((int)(aState.getX()-2),(int)(aState.getY()-2),4,4);
      g.setColor(Color.black);
      return;
    }
    gen1=(Generation)(tempVec.elementAt(0));	//generation FURTHEST out
    draw_gen:
    for(i=0; i<len;i++)
    {
      //g.setColor(new Color(255*(len-i)/len,255*(len-i)/len,255*(len-i)/len));
      gen2=(Generation)(tempVec.elementAt(i));
      gen2.draw(time,gen1,g);
      gen1=gen2;
    }
    gen1.draw(time,(int)aState.getX(),(int)aState.getY(),g);	//The last field lines get drawn right to the charage.
    g.setColor(Color.red);
    // Draw source in center of applet
    //----------------------------------------------------------------------
    g.fillOval((int)(aState.getX()-2),(int)(aState.getY()-2),4,4);
    g.setColor(Color.black);
    if(!caption.equals(""))paintCaption(g);
    g.dispose();
  }

  public void update(Graphics g)
  {
    paint(g); //update usually does a rect fill with a background color.  We don't need this.
  }

  void paintMessage(Graphics osg) {
    int iwidth=getSize().width;
    int iheight=getSize().height;
    String tempMsg=message; // reference for thread safety
    if (tempMsg == null || osg==null) {
      return;
    }

    FontMetrics fm = osg.getFontMetrics(osg.getFont());

    osg.setColor(Color.yellow);

    int w = 15 + fm.stringWidth(tempMsg);

    osg.fillRect(iwidth - w - 5, iheight - 15, w, 15);
    osg.setColor(Color.black);
    osg.drawString(tempMsg, iwidth - w + 2, iheight - 3);
    osg.drawRect(iwidth - w - 5, iheight - 15, w, 15);
  }

  public void paintCaption(Graphics g){
    int iwidth=getSize().width;
    g.setColor(Color.black);
    Font f=g.getFont();
    g.setFont(font);
    FontMetrics fm=g.getFontMetrics(font);
    g.drawString(caption,(iwidth-fm.stringWidth(caption))/2, 25);
    g.setFont(f);
  }


  // Sync Paint Handler
  //--------------------------------------------------------------------------
  public void paint(Graphics g){
    if(applet.destroyed) return;
    if (getSize().width!=buff_width||getSize().height!=buff_height){
      resetSource();
      buff_width=getSize().width;
      buff_height=getSize().height;
      buff_image=createImage(buff_width,buff_height);
    }
    if (buff_image==null) return;
    calcBuffImage();
    g.drawImage(buff_image,0,0,this);
    if(!message.equals(""))paintMessage(g);
    // g.drawString("Position= "+xs/size().width, 10, 20);
  }


  int numWaves=100;
  private Vector genVec= new Vector();
  private int time=0;		// running time
  private Image buff_image=createImage(1,1);
  private int buff_width=0;
  private int buff_height=0;
  private State aState=new WigglerState();
  private int resolution;
  private int res_expand=1;
  private int res_counter=0;
  String message="";
  String caption="";
  Font       font=new Font("Monospaced",Font.PLAIN,14);
  //AboutFrame about=new AboutFrame("Sync Fields");
}