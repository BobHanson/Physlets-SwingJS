package dla;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

//import java.awt.*;
import edu.davidson.display.*;
import edu.davidson.tools.*;
import edu.davidson.graph.SpecialFunction;
import a2s.*;

public class Dlamodel extends Panel implements Runnable {

  MassHistogram histogram= null;
  Image osi = null; //off-screen image
  DLA control= null;
  public boolean startPressed=false; //if the start button is ever pressed, it is turned to true
  SGraph graphPanel = null; //save memory for the Sgraph that will be passed from the constructor
  double[] bin   = null; //save the mass of particles attached in each bin
  double[] logDistance = null; //log of the distance from the seed
  double[] logMass = null;//log of the mass of the particles from the center
  Thread calcThread = null; //thread to be started when the start button is pressed
  double[] r = new double[10]; //radii of the different bin distances
  boolean[][] grid = null; //grid the hold information of where particles have attached
  int height = 0; //height of panel
  int width = 0; //width of panel
  int x0 = 0;      //starting x-position of random particle
  int y0 = 0;      //starting y-position of random particle
  public boolean attached = false; //tells if the random walker attaches to the growing mass
  double radius = 0; //radius from seed particle;
  int seedX = 0; //x-position of seed particle
  int seedY = 0; //y-position of seed particle
  int counter = 0; //counter to see if the start button has ever been pressed
  int tempWidth =0; //holds the old width and compares it to the new width (if the width of the panel has changed)
  int tempHeight=0; //holds the old height and compares it to the new height (if the height of the panel has changed)
  long particlesAttached = 1; //number of particles attached to our snowflake.  We start at one because
                              //we count our seed particle as the first to be attached to our snowflake;

  public Dlamodel(DLA control, SGraph graph) { //pass the control class
    graphPanel=graph;
    this.control = control;
    graphPanel.setEnableMouse(true);
    graphPanel.setSeriesStyle(1,true,3);
    graphPanel.setAutoReplaceData(1,true); //keeps the graph smooth (doesn't replace with zeros every time)
    graph.setMinYRange(true,2,4);
    graph.setMinXRange(true,1,2);
    graphPanel.setMarkerSize(1,.5); //reduce size of markers
    graphPanel.setLabelX("Log_{10} of Distance");
    graphPanel.setLabelY("Log_{10} of Mass");
  }
/**
 * Moves the random walker in one of four directions.
 */
  public void step()  {
    switch((int)(Math.random()*4) ) {//produces a random number between zero and three
      case 0:
        x0=x0+1;  //move right
        break;
      case 1:
        y0=y0+1;//move down on the panel
        break;
      case 2:
        x0=x0-1; //move left
        break;
      case 3:
        y0=y0-1;//move up on the panel
        break;
      default:
        System.out.println("Switch Error");
        break;
    }
   }
/**
 *  Takes the off-screen-image and paints it on the panel
 * @param g is the panel graphics object
 */
  public void paint(Graphics g){
    if(osi!=null){ //paints only if there is an image
      g.drawImage(osi,0,0,this);
    }else{
        g.setColor(Color.black);    //clear the off-screen-image
        g.fillRect(0,0,this.getBounds().width,this.getBounds().height);
    }
    if(calcThread!=null){
        g.setColor(Color.yellow);
        g.drawString("running",12,15);
    }
  }
/**
 * Gets the graphics objects and sends it to the paint method
 */
  public void paint() {
    Graphics g = getGraphics();
    paint(g);
    g.dispose(); //gives the grahpic resources back to the operating system
  }
/**
 * Finds the start potion of the random walker.  It take a random angle and takes a distance
 * from the seed (which is in the center of the panel)
 */
  public void startPosition() {
    int tempX = 0;
    int tempY = 0;
    double angle = 0;
    angle = (2*Math.PI)*(Math.random()); //produces a random number between 0 and just under 2Pi.
    tempX = (int)(Math.cos(angle)*radius);
    tempY = (int)(Math.sin(angle)*radius);
    if(tempX<=0)  { //if cos(theta) is neg, then it subtracts two
      x0 = seedX+(tempX-2);
    }
    else  { //if positive, then it adds two
      x0 = seedX+(tempX+2);
    }
    if(tempY<=0)  { //if sin(theta) is neg, then it subtracts two
      y0 = seedY+(tempY-2);
    }
    else  {//otherwise it adds two
      y0 = seedY+(tempY+2);
    }
  }
/**
 * Checks to seed if the random walkder should attach to the growing snowflake.  It checks it's four sides
 * to see if the snowflake is there
 */
  public void checkNeighbors(){
    if(grid[x0][y0+1]==true || grid[x0-1][y0]==true || grid[x0][y0-1]==true || grid[x0+1][y0]==true)  {
      int num=0;
      Graphics g = osi.getGraphics();
      grid[x0][y0] = true;
      attached = true;
      particlesAttached++;
      num=(int)((particlesAttached%3000)/1000); //picks a color (changes color every 1000 particles attached)
      switch(num) {
        case 0: g.setColor(Color.red); break;
        case 1: g.setColor(Color.blue);break;
        case 2: g.setColor(Color.green); break;
      }
      g.fillRect(x0,y0,1,1); //color in the pixel
      g.setColor(Color.white);//change back to the default color
      g.dispose(); //give back the graphics object to the operating system
      paint(); //draw the new particle that attached itself
      checkRadius(); // see if the radius should change
      control.updateDataConnections();
    }
  }
/**
 * Starts the entire algorithm, when the start button is pressed by the user.
 */
  public void start(){
    if(calcThread==null) { //if the thread isn't running, then the program is started up
      startPressed = true; //used for the graphPanel (so that it's doesn't graph data that isn't there
      create(); //set all the variables to start position
      calcThread= new Thread(this);  //create a new thread for this class
      calcThread.start(); //start the thread.  This calls the run method
    }
  }
/**
 *Finds the distance of the random walker from the seed particle.
 * @return distance is a double indicating the distance of the seed particle from the walker
 */

  public double findDistance()  {
    double distance = Math.sqrt((x0-seedX)*(x0-seedX)+(y0-seedY)*(y0-seedY));
    return distance;
  }
/**
 * Called by the thread (CalcThread), this method keeps the program running)
 */
  public void run() {
    boolean outOfBounds=false;
    double currentDistance=0;
    while(calcThread!=null){ //while the thread is running, all of these methods are repeadetly called
      if(attached=true) {
        fillBins(currentDistance); //keeps track of where the particles attach
        if( particlesAttached % 10 ==1 ) plotGraph(); //lpot the SGraph every 10th particle.
        attached=false;
      }
      startPosition(); //finds a new start position
      currentDistance=findDistance();
      outOfBounds=false; //tells if the random walker is outside of the grid range
      while(attached==false && currentDistance<2*radius+1 && outOfBounds==false)  { //repeats these methods until
        if(currentDistance>radius+2)                      //a particle attaches
          bigStep(currentDistance);//make the stepping more efficient
        else
          step();
        if(x0<width-1 && y0<height-1 && x0>0 && y0>0)    //stay within range of array
          checkNeighbors();
        else
          outOfBounds = true;
        currentDistance=findDistance();
      }
      try{
          Thread.sleep(10); //let the thread rest for 10 milliseconds
      }catch(InterruptedException e){}
    }
    repaint();  // get rid of the running label.
  }
/**
 * When the stop button is pressed, the thread is turned to null.  This stops the thread.
 */
  public void stop(){
    calcThread=null;
  }

/**
 *  Destroy the paint thread.
 *
 *  Added by W. Christian
 */
    public void destroy(){
      Thread temp=calcThread;
      calcThread=null;
      if(temp!=null)try{temp.join(5000);}catch (InterruptedException ie){}
    }
/**
 * Sets all the variables to their default values
 */
  public void create()  {
    //if(counter<1) {     //does all of this the first time the start button is pressed
      width = this.getBounds().width;  //get width of panel
      height = this.getBounds().height; //get height of panel
      seedX = (width/2); //x position of seed
      seedY = (height/2); //y position of seed
      logDistance = new double[10];
      logMass = new double[10];
      tempWidth = width;
      tempHeight = height;
      radius = 10; //arbitrary number for intial radius
      grid = new boolean[width][height];
      osi = createImage(width,height);
      bin = new double[10];
      x0=seedX; //initial x-position of walker
      y0=seedY; //initial y-position of walker
      grid[seedX][seedY] = true; //set seed;
      Graphics g = osi.getGraphics(); //draw to off-screen image
      g.setColor(Color.black);
      g.fillRect(0,0,width,height);
      g.setColor(Color.red);
      g.fillRect(seedX,seedY,1,1); //draw seed
      g.setColor(Color.white);
      g.dispose(); //give back to operation system
      paint(); //show seed on panel
       for(int i =0; i<10; i++) {//find radii for our different bins
         r[i] = Math.sqrt((height/2)*(height/2)+(width/2)*(width/2))*((double)(i+0.5)/10.0);
         logMass[i] =0;
         logDistance[i] = SpecialFunction.log10(r[i]);;
         bin[i] = 0;
       }
       bin[0]=1;  // these is always a seed.
       counter++;
    //}
    if(tempWidth!=width && tempHeight!=height){ //creates new osi only if the dimensions of panel change
      tempWidth = width;
      tempHeight = height;
      grid = new boolean[width][height];
      for(int i =0; i<10; i++)  { //changes the radii if panel dimensions change
        r[i] = Math.sqrt((height/2)*(height/2)+(width/2)*(width/2))*((double)(i+1)/10);
        logMass[i] =0;
        logDistance[i] = 0;
        bin[i] = 0;
      }
      osi = createImage(width,height);
    }
  }
/**
 * Checks the distance of the farthest particle that is attached.  Only update if a new particle
 * is farther away then the radius.
 */
  public void checkRadius() {
    double tempDistance =  Math.sqrt((x0-seedX)*(x0-seedX)+(y0-seedY)*(y0-seedY));
    if(tempDistance>radius) radius=tempDistance;
  }
/**
 * Takes bigger steps with the walker when the walker is farther than the radius from the seed
 * particle.  It makes the program slightly more efficient.
 * @param r is the distance of the random walker from the seed
 */
    public void bigStep(double r)  {
    int stepSize = Math.max(1,(int)(r-radius)-1);
    switch((int)(Math.random()*4)) {
      case 0:
        x0=x0+stepSize;
        break;
      case 1:
        y0=y0+stepSize;
        break;
      case 2:
        x0=x0-stepSize;
        break;
      case 3:
        y0=y0-stepSize;
        break;
      default:
        System.out.println("Switch Error");
        break;
    }
  }
/**
*
* Sets the Sgraph equal to our new Sgraph, so that it refreshes the information on the SgraphFrame.
* @param gr is out new SGraph
*/

  void setGraph(SGraph gr){
    graphPanel=gr;
    if(calcThread==null)plotGraph();
  }

/**
* When the graph button is pressed, this method is called to display the SGraph information.
*/

  public void plotGraph() {
    if(!control.graphFrame.isVisible()) return;
    if(logDistance==null || logMass==null || r==null || bin==null) return;
    double insideMass= bin[0];
    if(insideMass<=0) return;
    logMass[0] = SpecialFunction.log10(insideMass);
    //logDistance[0] = Math.log(r[0]);
    for(int i=1; i<10; i++) {
      //logDistance[i] = Math.log(r[i]);
      insideMass=insideMass + bin[i];//add up the mass of the outter ring and all the interior rings
      logMass[i] = SpecialFunction.log10(insideMass);
    }
    graphPanel.addData(1,logDistance,logMass);
  }

/**
* Keeps track of the accumulated particles (mass) in each bin) as the particles attach.
* @param particleDist is a double that indicates the distance that the particle is attached from the seed
*   particle.
*/

  public void fillBins(double particleDist) {

    if((int)particleDist<r[0])
      bin[0]++; //is equivalent to the mass in the bin
    if((int)particleDist>=r[0] && (int)particleDist<r[1])
      bin[1]++;
     if((int)particleDist>=r[1] && (int)particleDist<r[2])
      bin[2]++;
     if((int)particleDist>=r[2] && (int)particleDist<r[3])
      bin[3]++;
     if((int)particleDist>=r[3] && (int)particleDist<r[4])
      bin[4]++;
     if((int)particleDist>=r[4] && (int)particleDist<r[5])
      bin[5]++;
     if((int)particleDist>=r[5] && (int)particleDist<r[6])
      bin[6]++;
     if((int)particleDist>=r[6] && (int)particleDist<r[7])
      bin[7]++;
     if((int)particleDist>=r[7] && (int)particleDist<r[8])
      bin[8]++;
     if((int)particleDist>=r[8] && (int)particleDist<r[9])
      bin[9]++;
  }

/**
* Called when the reset button is pressed.  It resets all the values to default state.
*/

  public void reset() {
    if(calcThread!=null)  //stops thread if it hasn't already been stopped
      calcThread=null;
    create();  //reset all the variables
    Graphics g = osi.getGraphics();
    g.setColor(Color.black);    //clear the off-screen-image
    if(width>0 && height>0) g.fillRect(0,0,width,height);
    g.dispose();
    paint();//show the cleared screen
    particlesAttached = 1;
    graphPanel.setAutoReplaceData(1,false);   //clear our graph
    graphPanel.clearSeriesData(1);
    graphPanel.setAutoRefresh(true);
    graphPanel.setAutoReplaceData(1,true);
    counter = 0;
    control.updateDataConnections();
  }

  /**
  *
  * Returns the id of the mass histogram. This id can be used to make data connections.
  *
  * @return int  The id.
  *
  */
  public int getHistogramID(){
     if(histogram==null) histogram= new MassHistogram();
     return histogram.getID();
  }

    // inner class used for data connection to histogram.
  public class MassHistogram extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"r","m"};  // speed and number
    double[][] ds=new double[1][2];  // the datasource state variables r, n;

  MassHistogram(){ // Constructor
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
      if(ds.length!=bin.length){
           ds=new double[bin.length][2];
           for(int i=0; i<bin.length; i++) ds[i][0]=r[i];
      }
      double mass=0;
      for(int i=0; i<bin.length; i++){
        mass+=bin[i];
        ds[i][1]=mass;
      }
      return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return control;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"

  }  // end of Histogram class
}