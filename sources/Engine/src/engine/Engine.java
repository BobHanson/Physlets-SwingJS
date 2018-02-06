//	Created with xdj
//*************************************************
package engine;

import java.awt.*;
import java.lang.Math;
import edu.davidson.tools.*;
//*************************************************
public class Engine extends SApplet implements SStepable {
//variables added by W. Christian
    static  final int BURNMODE=0;
    static  final int EXHAUSTMODE=1;
    static  final int INTAKEMODE=2;
    static  final int COMPRESSIONMODE=3;
    int mode=0;
    double gamma=1.4;
    DataSource gas=new DataSource(this);
    double pressure=1;
    double temperature=1;
    double volume=1;
    String label_burning="Burning Gas Mixture";
    String label_expelling="Expelling smoke";
    String label_sucking="Sucking in Gas Mixture";
    String label_compressing="Compressing Gas";
// end of variables added by W. Christian

//world variables

      double radius;

      //cylinder position
      double CylPtBottom, CylPtTop;

      //the X value for the Cylinder
      double Xcyl;

      //circle point position
      double Ycircle, Xcircle;

      //positon of Piston taken at center pt
      double Ypiston, MiddlePositonPiston, Xpiston=0.0;

      //the height of the piston
      int PistonHeight;

      //Value for the AirValves
      double ValveHeight, Xvalve;
      int ValveLength;

      //name of the AirValve segments
      int  ValveJoint1=11, ValveJoint2=12;

      //nameofparticles
      int CirPart=4, Circle=5,
            PistonJoint=3, Piston=2,
            ValveBottom1=6,
            ValveBottom2=7, AirColor=1;
      //pole between box and circle segment number
      int pole=4;

      //degree of point on circle
      int StartingAngle=89;
      int currentangle;

      //changing angles into radians
      double pi=Math.PI;
      double conversionRadians;

      //the angle in radian mode
      double RadianAngle;

      //Delay Variable
     // int DelayVar=10;
      //int LoopOn=0;
// end of world variables


//   boolean running = false;
   Point posMouse = new Point(0,0);
   int btnMouse = 0;
   int currElement = -1;
   int currElementType = 0;
   XcWorld aWorld_2XcWorld;
//*************************************************
   public void init(){
	  initResources(null);
      addNotify();
      openFrames();
      clock.addClockListener(this);
      clock.setDt(0.05);
      clock.setFPS(20);
      //LoopOn=1;
   }
   
   /**
    * Sets the resource strings.
    */
   protected void setResources () {
       label_burning = localProperties.getProperty("label.burning", label_burning);
       label_expelling = localProperties.getProperty("label.expelling", label_expelling);
       label_sucking = localProperties.getProperty("label.sucking", label_sucking);
       label_compressing = localProperties.getProperty("label.compressing", label_compressing);
   }
   
//*************************************************
   public void openFrames() {
      aWorld_2XcWorld = new XcWorld();
      add(aWorld_2XcWorld);
      aWorld_2XcWorld.reshape(0,0,400,450);
      aWorld_2XcWorld.setForeground(Color.black);
      aWorld_2XcWorld.setBackground(Color.black);
      aWorld_2XcWorld.setFont(new Font("Helvetica" , Font.PLAIN , 12));
      aWorld_2XcWorld.nOfPoints = 0;
      aWorld_2XcWorld.nOfParticles = 0;
      aWorld_2XcWorld.nOfSegments = 0;
      aWorld_2XcWorld.doubleBuffering = false;
      XdevProgram();
   }
//*************************************************
    void XdevProgram() {
      // Author : Sadie Lowry
      //Received : July 1994

      // A FOURSTROKE ENGINE
      // Author : Sadie Lowry
      //          University of Michigan, Ann Arbor
      // E-mail : lowry@engin.umich.edu

      int i;
      int w, h;                                 //width and height variables of XcWorld
      int nOfPar=16, nOfSeg=14;

      //Space between Circle and the Cylinder
      double Space=40.0;

      //width of the Cylinder *must be even number
      int CylWidth=100;
      double CylHeight;
      double CompressionSpace=20.0;

      //value for the hose connected to the AirValves
      double HoseThickness, HoseLength, HoseStart;

      //Width and Height of Piston box
      int PistonWidth;

      //diameter of circle
      int Diameter = 120;
      //finds radius of circle
      radius = Diameter / 2.0;
      conversionRadians=pi/180;

      //finding height and width for aWorld_2
     //### XGetVal aWorld_2: height h;

      h = aWorld_2XcWorld.getSize().height;
     //### XGetVal aWorld_2: width w;

      w = aWorld_2XcWorld.getSize().width;

      //sets size of piston to fit in cylinder
      PistonWidth = CylWidth - 5;
      PistonHeight = (int) (CylWidth/2.0 - 20);

      //Height of Cylinder takes into account compression space
      CylHeight = Diameter + PistonHeight + CompressionSpace;

      //finds position of cylinder coordinate
      CylPtBottom = radius + Space;
      CylPtTop = CylPtBottom + CylHeight;

      //the x position for the Cylinder
      Xcyl=CylWidth/2.0;

      //the length and width of the AirValves hose
      HoseThickness=CylWidth/4.0;
      HoseLength=Xcyl;

      //the length and height of AirValve
      ValveLength=(int) HoseThickness;  //does not have to be the same size
      ValveHeight=HoseThickness*2.0;
      Xvalve=Xcyl-ValveLength/2-1;
      //The point where the hose Starts(x position for slanted part of hose)
      HoseStart=Xcyl-ValveLength*0.75;

      //sets aWorld : setting (0,0) to be located in the middle of
      //the board, radius + constant(10) from bottom of board

     //### XSetVal aWorld_2 : nOfParticles nOfPar, nOfSegments nOfSeg,      background black,      updateImmediate true,      doubleBuffering true,      xorMode true,      xmin  -w/2.0,          xmax w/2.0,       ymin  -radius-50.0 ,   ymax h-radius-50.0 ;
      aWorld_2XcWorld.nOfParticles = nOfPar;
      aWorld_2XcWorld.nOfSegments = nOfSeg;
      aWorld_2XcWorld.setBackground(Color.black);
      aWorld_2XcWorld.updateImmediate = true;
      aWorld_2XcWorld.doubleBuffering = true;
      aWorld_2XcWorld.xorMode = true;
      aWorld_2XcWorld.xmin = -w/2.0;
      aWorld_2XcWorld.xmax = w/2.0;
      aWorld_2XcWorld.ymin = -radius-50.0;
      aWorld_2XcWorld.ymax = h-radius-50.0;

      //makes cylinder
        aWorld_2XcWorld.XcWorldMoveSegment(1, -Xcyl, CylPtBottom, -Xcyl, CylPtTop);
        aWorld_2XcWorld.XcWorldMoveSegment(2, -Xcyl+ValveLength, CylPtTop, Xcyl-ValveLength, CylPtTop);
        aWorld_2XcWorld.XcWorldMoveSegment(3, Xcyl, CylPtBottom, Xcyl, CylPtTop);

      //makes AirValveHoses

      //Left Side
        aWorld_2XcWorld.XcWorldMoveSegment(5, -Xcyl+ValveLength, CylPtTop, -HoseStart, CylPtTop+HoseThickness);
        aWorld_2XcWorld.XcWorldMoveSegment(6, -HoseStart, CylPtTop+HoseThickness, -Xcyl-HoseLength, CylPtTop+HoseThickness);
        aWorld_2XcWorld.XcWorldMoveSegment(7, -Xcyl, CylPtTop, -Xcyl-HoseLength, CylPtTop);

      //Right Side
        aWorld_2XcWorld.XcWorldMoveSegment(8, Xcyl-ValveLength, CylPtTop, HoseStart, CylPtTop+HoseThickness);
        aWorld_2XcWorld.XcWorldMoveSegment(9, HoseStart, CylPtTop+HoseThickness, Xcyl+HoseLength, CylPtTop+HoseThickness);
        aWorld_2XcWorld.XcWorldMoveSegment(10, Xcyl, CylPtTop, Xcyl+HoseLength, CylPtTop);

      //sets currentangle to the angle started with
      currentangle=StartingAngle;
      //makes connection between piston and circle (rod)
      //converts to radian mode
      conversionRadians=pi/180.0;
      RadianAngle = currentangle * conversionRadians;
      Ycircle = radius * Math.sin(RadianAngle);
      Xcircle = radius * Math.cos(RadianAngle);
      //printf ("xcir=%.3f, ycir=%.3f\n", Ycircle, Xcircle);
      MiddlePositonPiston = CylPtBottom + radius + PistonHeight/2.0;
      Ypiston = MiddlePositonPiston + Ycircle;
        aWorld_2XcWorld.XcWorldMoveSegment(pole, Xpiston, Ypiston, Xcircle, Ycircle);

      i=1;
      while (i<=10) {
        aWorld_2XcWorld.XcWorldChangeSegmentColor(i, "cyan");
        i = i +1;
      }

      //Air Valves

      //Left Side
        aWorld_2XcWorld.XcWorldChangeParticleMode(ValveBottom1, 2);
        aWorld_2XcWorld.XcWorldMoveParticle(ValveBottom1, -Xvalve, CylPtTop);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(ValveBottom1, ValveLength, 2);
        aWorld_2XcWorld.XcWorldChangeParticleColor(ValveBottom1, "red");
        aWorld_2XcWorld.XcWorldMoveSegment(ValveJoint1, -Xvalve, CylPtTop, -Xvalve, CylPtTop+ValveHeight);
        aWorld_2XcWorld.XcWorldChangeSegmentColor(ValveJoint1, "red");

      //Right Side
        aWorld_2XcWorld.XcWorldChangeParticleMode(ValveBottom2, 2);
        aWorld_2XcWorld.XcWorldMoveParticle(ValveBottom2, Xvalve, CylPtTop);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(ValveBottom2, ValveLength, 2);
        aWorld_2XcWorld.XcWorldChangeParticleColor(ValveBottom2, "red");
        aWorld_2XcWorld.XcWorldMoveSegment(ValveJoint2, Xvalve, CylPtTop, Xvalve, CylPtTop+ValveHeight);
        aWorld_2XcWorld.XcWorldChangeSegmentColor(ValveJoint2, "red");

      //Piston
        aWorld_2XcWorld.XcWorldChangeParticleMode(Piston, 2);
        aWorld_2XcWorld.XcWorldMoveParticle(Piston, Xpiston, Ypiston);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(Piston, PistonWidth, PistonHeight);
        aWorld_2XcWorld.XcWorldChangeParticleColor(Piston, "red");

      //Joint on Piston
        aWorld_2XcWorld.XcWorldMoveParticle(PistonJoint, Xpiston, Ypiston);
        aWorld_2XcWorld.XcWorldChangeParticleColor(PistonJoint, "black");

      //Circle
        aWorld_2XcWorld.XcWorldChangeParticleMode(Circle, 1);
        aWorld_2XcWorld.XcWorldMoveParticle(Circle, 0.0, 0.0);
        aWorld_2XcWorld.XcWorldChangeParticleSize(Circle, Diameter);
        aWorld_2XcWorld.XcWorldChangeParticleColor(Circle, "red");

      //Joint on Circle
        aWorld_2XcWorld.XcWorldMoveParticle(CirPart, Xcircle, Ycircle);
        aWorld_2XcWorld.XcWorldChangeParticleColor(CirPart, "red");

      //middlepoint of big circle
        aWorld_2XcWorld.XcWorldMoveParticle(16, 0.0, 0.0);
        aWorld_2XcWorld.XcWorldChangeParticleColor(16, "red");

      //Air Color
        aWorld_2XcWorld.XcWorldChangeParticleMode(AirColor, 12);


      //Makes Spark Plug
        aWorld_2XcWorld.XcWorldChangeParticleMode(8, 14);
        aWorld_2XcWorld.XcWorldMoveParticle(8, -5.0, CylPtTop-6);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(8, 10, 2);
      //XcWorldChangeParticleColor(aWorld_2, 8, "Gray");

        aWorld_2XcWorld.XcWorldChangeParticleMode(9, 14);
        aWorld_2XcWorld.XcWorldMoveParticle(9, -5.0, CylPtTop-3);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(9, 10, 2);
      //XcWorldChangeParticleColor(aWorld_2, 9, "Gray");

        aWorld_2XcWorld.XcWorldChangeParticleMode(10, 11);
        aWorld_2XcWorld.XcWorldMoveParticle(10, -10.0, CylPtTop);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(10, 20, 2);
      //XcWorldChangeParticleColor(aWorld_2, 10, "Gray");

        aWorld_2XcWorld.XcWorldChangeParticleMode(11, 11);
        aWorld_2XcWorld.XcWorldMoveParticle(11, -10.0, CylPtTop+3);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(11, 20, 2);
      //XcWorldChangeParticleColor(aWorld_2, 11, "Gray");

        aWorld_2XcWorld.XcWorldChangeParticleMode(12, 11);
        aWorld_2XcWorld.XcWorldMoveParticle(12, -10.0, CylPtTop+6);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(12, 20, 2);
      //XcWorldChangeParticleColor(aWorld_2, 12, "Gray");

        aWorld_2XcWorld.XcWorldChangeParticleMode(13, 12);
        aWorld_2XcWorld.XcWorldMoveParticle(13, -5.0, CylPtTop+6);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(13, 10, 8);
      //XcWorldChangeParticleColor(aWorld_2, 13, "Gray");

        aWorld_2XcWorld.XcWorldChangeParticleMode(14, 12);
        aWorld_2XcWorld.XcWorldMoveParticle(14, -8.0, CylPtTop+14);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(14, 16, 10);
      //XcWorldChangeParticleColor(aWorld_2, 14, "Gray");

        aWorld_2XcWorld.XcWorldChangeParticleMode(15, 12);
        aWorld_2XcWorld.XcWorldMoveParticle(15, -8.0, CylPtTop+24);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(15, 20, 5);
      //XcWorldChangeParticleColor(aWorld_2, 15, "Gray");

      i=8;
      while (i<=15) {
        aWorld_2XcWorld.XcWorldChangeParticleColor(i, "white");
        i=i+1;
      }

        aWorld_2XcWorld.XcWorldMoveSegment(13, 12.0, CylPtTop+26.5, 20.0, CylPtTop+26.5);
        aWorld_2XcWorld.XcWorldChangeSegmentColor(13, "gray");
        aWorld_2XcWorld.XcWorldMoveSegment(14, 20.0, CylPtTop+26.5, 20.0, CylPtTop+100);
        aWorld_2XcWorld.XcWorldChangeSegmentColor(14, "gray");
      // visualise the engin on screen
        aWorld_2XcWorld.XcWorldUpdate( );
   } //end program
//*************************************************
   public void step(double dt,double time){

            int i=8;  //changes the color of the spark plug
            int sparkangle=90;
            int sparkduration=10;
            //the angle inwhich the valve for the dirty air opens DA=Dirty Air
            int OutLetOpen=270;
            //the duration of the OutLet Valve
            int OutLetOpen_Duration=180;
            //the angle inwhich the valve for the clean air opens CA=Clean Air and DA closes
            int InLetOpen=450;
            //the duration of the InLet Valve in degrees
            int InLetOpen_Duration=180;
            //a four spark engine starts over again after it reaches the angle cycle2
            int cycle2=720;
            //number of degrees the angle changes to
            int stepAngle=1;
            //the valve new postion when it open and closes
            double YBottomValve=CylPtTop;
            double  YTopValve;
            //the size inwhich the vavles open
            int ValveOpenSize=10;
            YTopValve=CylPtTop+ValveHeight;

            //starts cycle over again spark->dirtyair out->freshair in
            currentangle = (currentangle%cycle2);

            //increments angle of rotation
            currentangle = currentangle + stepAngle;
            //converts to radian mode
            RadianAngle = currentangle * conversionRadians;

            //finds position of point on circle and point in piston
            Ycircle = radius * Math.sin(RadianAngle);
            Xcircle = radius * Math.cos(RadianAngle);
            Ypiston = MiddlePositonPiston + Ycircle;
            //printf("angle %d, ycircle %.3f, xcircle %.3f\n", currentangle, Ycircle, Xcircle);
            //moves and redraws circle point and piston to new coordinates
            button_5_helpCallback( );

            //redraws the size of the Air Color
            button_3_helpCallback( );
            //the angles where the spark happens

            //changes the color of the spark plug and air color
            if (currentangle==sparkangle) {
                mode=BURNMODE;
                play(getCodeBase(), "beep.au"); //###     XBell 100;
                while (i<=15) {
                    aWorld_2XcWorld.XcWorldChangeParticleColor(i, "yellow");
                    i=i+1;
                }
                i=8;

                aWorld_2XcWorld.XcWorldChangeParticleColor(AirColor, "pink'");
                //###   XSetVal label_8 : foreground white, labelString "Burning Gas Mixture";
                aWorld_2XcWorld.msgXpos = 40;
                aWorld_2XcWorld.msgYpos = 300;           
                aWorld_2XcWorld.msgColor = Color.pink;
                aWorld_2XcWorld.msgString = label_burning;
            }

            //sets the air color  and changes color of Sparkplug
            if (currentangle == (sparkangle+sparkduration)) {
                mode=BURNMODE;
                while (i<=15) {
                    aWorld_2XcWorld.XcWorldChangeParticleColor(i, "white");
                    i=i+1;
                }
                i=7;
                aWorld_2XcWorld.XcWorldChangeParticleColor(AirColor, "pink");
                aWorld_2XcWorld.msgXpos = 40;
                aWorld_2XcWorld.msgYpos = 300;
                aWorld_2XcWorld.msgColor = Color.orange;
                aWorld_2XcWorld.msgString = label_burning;
            }

            //Opening and closing of valves
            if (currentangle==OutLetOpen) {
                mode=EXHAUSTMODE;
                aWorld_2XcWorld.XcWorldChangeParticleColor(AirColor, "gray");
                //###    XSetVal label_8 : labelString "Expelling Smoke";
                aWorld_2XcWorld.msgXpos = 240;
                aWorld_2XcWorld.msgYpos = 60;
                aWorld_2XcWorld.msgColor = Color.lightGray;
                aWorld_2XcWorld.msgString = label_expelling;
            }

            //shows OutLet Valve opening and air leaving
            if ((currentangle>= OutLetOpen) && (currentangle < (OutLetOpen+OutLetOpen_Duration))){
                mode=EXHAUSTMODE;
                YTopValve=YTopValve-ValveOpenSize* Math.cos(RadianAngle);
                YBottomValve=YBottomValve-ValveOpenSize* Math.cos(RadianAngle);
                aWorld_2XcWorld.XcWorldMoveParticle(ValveBottom2, Xvalve, YBottomValve);
                aWorld_2XcWorld.XcWorldMoveSegment(ValveJoint2, Xvalve, YBottomValve, Xvalve, YTopValve);
            }

            //shows OutLet Valve closing
            if (currentangle == (OutLetOpen+OutLetOpen_Duration)) {
                mode=EXHAUSTMODE;
                YTopValve=CylPtTop+ValveHeight;
                YBottomValve=CylPtTop;
                aWorld_2XcWorld.XcWorldMoveParticle(ValveBottom2, Xvalve, YBottomValve);
                aWorld_2XcWorld.XcWorldMoveSegment(ValveJoint2, Xvalve, YBottomValve, Xvalve, YTopValve);

            }

            if (currentangle == InLetOpen) {
                mode=INTAKEMODE;
                aWorld_2XcWorld.XcWorldChangeParticleColor(AirColor, "yellow");
                //###    XSetVal label_8 :  labelString "Sucking in Gas Mixture";
                aWorld_2XcWorld.msgXpos = 50;
                aWorld_2XcWorld.msgYpos = 60;
                aWorld_2XcWorld.msgColor = Color.yellow;
                aWorld_2XcWorld.msgString = label_sucking;
                pressure=1.0; // pressure is atmospheric
            }

            //show InLet valve opening and air coming in
            if ((currentangle >= InLetOpen) && (currentangle < InLetOpen+InLetOpen_Duration)) {
                mode=INTAKEMODE;
                YTopValve=YTopValve+ValveOpenSize* Math.cos(RadianAngle);
                YBottomValve=YBottomValve+ValveOpenSize* Math.cos(RadianAngle);
                aWorld_2XcWorld.XcWorldMoveParticle(ValveBottom1, -Xvalve, YBottomValve);
                aWorld_2XcWorld.XcWorldMoveSegment(ValveJoint1, -Xvalve, YBottomValve, -Xvalve, YTopValve);
            }

            //shows InLet Valve closing
            if (currentangle == (InLetOpen+InLetOpen_Duration)) {
                mode=COMPRESSIONMODE;
                YTopValve=CylPtTop+ValveHeight;
                YBottomValve=CylPtTop;
                aWorld_2XcWorld.XcWorldMoveParticle(ValveBottom1, -Xvalve, YBottomValve);
                aWorld_2XcWorld.XcWorldMoveSegment(ValveJoint1, -Xvalve, YBottomValve, -Xvalve, YTopValve);

                aWorld_2XcWorld.XcWorldChangeParticleColor(AirColor, "orange");
                //###   XSetVal label_8 :  labelString "Compressing  Gas";
                aWorld_2XcWorld.msgXpos = 40;
                aWorld_2XcWorld.msgYpos = 300;        
                aWorld_2XcWorld.msgColor = Color.orange;
                aWorld_2XcWorld.msgString = label_compressing;
            }
            // visualise the engin on screen
            aWorld_2XcWorld.XcWorldUpdate( );

            volume=(CylPtTop-Ypiston)/(double)(CylPtTop - MiddlePositonPiston + radius);   // Volume is 1 at the bottom of the stroke.
            switch(mode){
              case BURNMODE:{
                  pressure=3.0/Math.pow(volume,gamma); // pressure increases due to burn
                  temperature=pressure*volume;
                  break;
              }
              case EXHAUSTMODE:{
                  pressure=1.1;
                  // temperature stays fixed at last burn temperature
                  break;
              }
              case INTAKEMODE:{
                  pressure=1;
                  temperature=1;  // temperature of incoming air.
                  break;
              }
              case COMPRESSIONMODE:{
                 // pressure=1.0/volume; // pressure is atmospheric
                  pressure=1.0/Math.pow(volume,gamma);
                  temperature=pressure*volume;
                  break;
              }
            }
            this.updateDataConnections();
      }
//*************************************************
   public void button_3_helpCallback() {
      //This makes the air color in the cylinder, the air increases and decreased (compression
      //and decompression)

      //width and height of the color
      int ColorHeight, ColorWidth;

      //the lower left corner of the Air color
      double YairColor, XairColor=-Xcyl+1.5;

      YairColor=Ypiston+PistonHeight/2;

      ColorWidth=(int) (2*Xcyl-2);
      ColorHeight=(int) (CylPtTop-Ypiston - PistonHeight/2 -2);

      //AirColor
        aWorld_2XcWorld.XcWorldMoveParticle(AirColor, XairColor, YairColor);
        aWorld_2XcWorld.XcWorldChangeParticleSizes(AirColor, ColorWidth, ColorHeight);

   } //end callback or function

//*************************************************
   public void button_5_activateCallback() {
      int i=8;
      currentangle=StartingAngle;
      RadianAngle=currentangle*conversionRadians;

      //resets angle to begining point
      Ycircle = radius * Math.sin(RadianAngle);
      Xcircle = radius * Math.cos(RadianAngle);

      //resets starting point of box(piston)
      Ypiston = MiddlePositonPiston + Ycircle;

      //moves circle point and box
        button_5_helpCallback( );

      //resets spark and valves
        aWorld_2XcWorld.XcWorldMoveParticle(ValveBottom1, -Xvalve, CylPtTop);
        aWorld_2XcWorld.XcWorldMoveSegment(ValveJoint1, -Xvalve, CylPtTop, -Xvalve, CylPtTop+ValveHeight);
        aWorld_2XcWorld.XcWorldMoveParticle(ValveBottom2, Xvalve, CylPtTop);
        aWorld_2XcWorld.XcWorldMoveSegment(ValveJoint2, Xvalve, CylPtTop, Xvalve, CylPtTop+ValveHeight);

      //resets the air color
        aWorld_2XcWorld.XcWorldChangeParticleColor(AirColor, "black");

      //changes the words
     //### XSetVal label_6 : background black, foreground black;

     //$$$ aWorld_2XcWorld.msgColor = Color.white;
     //$$$ aWorld_2XcWorld.msgString = "";
      


      //sets spark plug color back to grey if it is yellow
      if (currentangle <= 90) {
         while (i<=15) {
        aWorld_2XcWorld.XcWorldChangeParticleColor(i, "white");
           i=i+1;
         }
      }
   } //end callback or function
//*************************************************
   public void button_5_helpCallback() {

      //moves circle point and box
        aWorld_2XcWorld.XcWorldMoveParticle(Piston, Xpiston, Ypiston);
        aWorld_2XcWorld.XcWorldMoveParticle(PistonJoint, Xpiston, Ypiston);
        aWorld_2XcWorld.XcWorldMoveParticle(CirPart, Xcircle, Ycircle);

      //connects point on circle to point in box
        aWorld_2XcWorld.XcWorldMoveSegment(pole, Xpiston, Ypiston, Xcircle, Ycircle);



   } //end callback


   // the following methods were added by W. Christian
   // data source ID
   public int getGasID(){
       return gas.getID();
   }

   public void reset(){
       pause();          // stop the clock.
       super.reset();   // set the clock to zero.
       button_5_activateCallback();
       step(clock.getDt(),0);  // force a repaint and set the variables.
   }
   /**
    * Called when the clock stops in one-shot mode.    DO NOT SCRIPT.
    */
   protected void stoppingClock(){
       aWorld_2XcWorld.msgXpos = 40;
       aWorld_2XcWorld.msgYpos = 300;
       aWorld_2XcWorld.msgColor = Color.orange;
       aWorld_2XcWorld.msgString = oneShotMsg;
       aWorld_2XcWorld.XcWorldUpdate( );
   }

   // inner class used for data connections.
  public class DataSource extends Object   implements edu.davidson.tools.SDataSource{
    SApplet applet;
    String[] varStrings= new String[]{"time","p","v","t","theta"};
    double[][] ds=new double[1][5];  // the datasource state variables t,x,y,vx,vy,m;

    DataSource(SApplet a){ // Constructor
       applet=a;
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
       ds[0][0]=clock.getTime();  // these variables are defined in the outer class.
       ds[0][1]=pressure; // pressure
       ds[0][2]=volume; // volume
       ds[0][3]=temperature; // temperature
       ds[0][4]=currentangle;
       return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet a){applet=a;}
    public SApplet getOwner(){return applet;}    //owner is an SApplet.
  }

}
