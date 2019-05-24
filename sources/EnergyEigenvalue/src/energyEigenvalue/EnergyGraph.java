package energyEigenvalue;
import java.awt.*;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.*;

import edu.davidson.display.*;
import edu.davidson.tools.*;
import edu.davidson.numerics.*;


public final class EnergyGraph extends SGraph{
    Vector dataSources = new Vector();
    ActiveWavefunction activeWavefunction=null;
    ActiveState activeState=null;
    Parser potFunc;
    boolean showLevels, rclicked, lines, divergence=false, broke=false, resetE=false;// boolean that determines whether or not we show our energy level diagram
    boolean updated;
    boolean showWavefunction=true;
    private int active=0; //active is the energy level that the user is currently viewing.  This variable determines which line on the energy level will be red in color
    private double breakv; //breakvalue to stop exponential growth of Psi
   // private int max, low = 0;
    private Hashtable eValues = new Hashtable(); //our hashtable to store calculated energy values
    int boxWidth=0, ktemp=0, kcheck=0, maxiterations; //our yellow display box variable, counter to see how many times we have gone through calcOnce method
    Format    format= new Format("%-+6.3g");
    int numPts;  //number of points or values of Psi we calculate
    private double[] psi = null; //wavefunction array
    private double[] psigraphed = null;  //wave function array that is graphed
    private double[] y= null;
    private double[] x= null;
    private double eGuess, h, energy; //energy "guess" variable for search algorithm, energy variable
    private double dx, eMax, eMin, datamax, funcmin, funcmax; //our step variable, energy max and min values, and our potential function max and min values
    private double tolerance; //What should tolerance be?
    EnergyEigenvalue owner=null;
    private boolean autoscalePotential=false;
    private double sum=0;
    private boolean scalearea=false;
    private boolean energyDragMode=false;
    /**
     * @y.exclude
     * @param a
     */
    public EnergyGraph(EnergyEigenvalue a)  {
       super();
       owner=a;
       setOwner(a);
        try  {
      jbInit();
      setSeriesStyle(1,Color.red,true,0);
      setSeriesStyle(2,Color.blue,true,0);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    }

  /**
  * This method sets the Autoscale property for the energy graph.  The scale is set based on the
  * value of the potential function.
  *
  *@param boolean as    autoscale potential
  */
  public void setAutoscalePotential(boolean as){
     autoscalePotential=as;
  }

  /**
  * This method parses the potential function from the string entered into the potential
  *text box on the user interface
  *
  * @Param boolean string str     the potential function
  */
  synchronized boolean setPotential(String str){
     boolean parseError=false;
     datamax=0;
     funcmin=0;
     potFunc = new Parser(1);
     potFunc.defineVariable(1,"x"); // define the variable
     potFunc.define(str);
     potFunc.parse();
     if(potFunc.getErrorCode() != Parser.NO_ERROR){
         System.out.println("Failed to parse V(x): "+str);
         System.out.println("Parse error: " + potFunc.getErrorString() +
                   " at function 1, position " + potFunc.getErrorPosition());
         parseError=true;
         str="0";
         potFunc.define(str);
         potFunc.parse();
     }else parseError=false;
     dx= (getMaxX()-getMinX())/(numPts-1);
     x[0] = getMinX();
     y[0] = potFunc.evaluate(x[0]);
     funcmin=y[0];
     funcmax=y[0];
     for(int i=1; i<numPts; i++) {
            x[i] = x[i-1]+dx;
            y[i] = potFunc.evaluate(x[i]);
            if (y[i]>funcmax) funcmax=y[i];
            if (y[i]<funcmin) funcmin=y[i];
      }
      eMin=funcmin;
      eMax=funcmax;
      setAutoRefresh(false);
      if(autoscalePotential) setAutoscaleY( true);
      setAutoReplaceData(1,false);
      setAutoReplaceData(2,false);
      clearSeriesData(2);    // added by wc to make sure that the wavefunction has been cleared.
      clearSeriesData(1);
      setAutoReplaceData(1,true);
      setAutoReplaceData(2,true);
      if(showWavefunction)addData(1,x,y);
      if (!updated) {//added by cf to ensure that the wavefunction is put back on the graph....thank you for taking it off.
          if(showWavefunction)addData(2,x,psigraphed);
          if(activeWavefunction!=null) activeWavefunction.updateValues(this.energy);
          if(activeState!=null) activeState.updateValues(this.energy,active);
      }
      if(autoscalePotential) setAutoscaleY( false);
      if(autoscalePotential){   // make sure we aren't missing any energy levels in the hash table.  Added by W. Christian
        double lowE=funcmin,highE=funcmax;
        Enumeration levels = eValues.elements();  //like its name, this method removes all elements from hashtable
        while (levels.hasMoreElements()){
          double en= ((Double)levels.nextElement()).doubleValue();
          lowE=Math.min(lowE,en);
          highE=Math.max(highE,en);
        }
        double range=highE-lowE;
        setMinMaxY(lowE-0.12*range,highE+0.12*range);
      }
      setAutoRefresh(true);
      return !parseError;
  }
  void setIterations(int it){this.maxiterations=it;}

  void setBreakValue(double bv) {this.breakv=bv;}

  void setEnergy(double en){this.energy=en; active=0;}

  void sethBar(double h) {this.h=h;}

  void setNumpts(int num) {
       this.numPts=num;
       psi = new double[numPts]; //wavefunction array
       psigraphed = new double[numPts];  //wave function array that is graphed
       x = new double[numPts];
       y = new double[numPts];
   }

  void setTolerance(double t) {this.tolerance=t;}

  void scaleToArea(boolean sa) {this.scalearea=sa;}

  void setShowWavefunction(boolean show){
     showWavefunction=show;
     if(show){
         setShowAxis(true);
         frame=true;
         setDataBackground(Color.white);
     }else{
         setShowAxis(false);
         frame=false;
         setDataBackground(SystemColor.control);
     }
  }

  /**
  *  This method contains the difference equation algorithm for calculating Psi from
  * two previous values.  It returns a a value for the number of times the wave function
  * crosses through 0.
  *
  *@returns int count     the number of times the wave function crosses the x-axis
  */
  public int calculatePsi(double energy){  // make energy local
     if(psi==null){
       divergence=true;
       return 0;
     }
     divergence=false;
     int count=0;
     double alpha, beta;// norm=1e-4;
     double tendx=h*(10.0*dx*dx)/12.0; //constant to save calculation time
     double twlvedx=(h*dx*dx)/12.0;   //constant to save calculation time
     psi[0]=0;  //the first value of Psi
     psi[1]=1e-12;    //the second value of Psi
     sum = psi[1]*psi[1]*dx;
     //our algorithm using difference equations to calculate the next wave function value from two previous values
     for(int i=1; i<(numPts-1); i++){
         //double delta1=y[i]-energy;
         //double delta2=y[i-1]-energy;
         //double delta3=y[i+1]-energy;
         // if(delta1==0 && delta2==0 && delta3==0 ) energy=energy+1.0e-6;
         alpha=psi[i]*(2+tendx*(y[i]-energy))-psi[i-1]*(1-twlvedx*(y[i-1]-energy));
         beta=1-twlvedx*(y[i+1]-energy);
         psi[i+1]=(alpha/beta);
         if (psi[i+1]>breakv){  //if psi is larger than the breakvalue set by user we assume no convergence
          broke=true;
          System.out.println("psi>breakvalue: wavefunction not accurate");
          break;
         }
         sum = sum+(psi[i+1]*psi[i+1]*dx);//used to normalize to the area of the graph
     }
     int lastCross=0; //last cross keeps track of the last crossing of the energy (inflexion point through zero)
     for(int i=1; i<numPts; i++){
        if(psi[i]==0||(psi[i-1]/psi[i])<0){
           count++; //count sees how many times we cross the x axis...the method returns this b/c integral in finding e-levels
           lastCross=i;
        }

     }

     if(count<1 || lastCross<4 ) lastCross=numPts; //hopefully this line eliminates all anomolies with potential
     datamax=0;
     double tmp=0;
     for(int i=1; i<lastCross; i++){
         tmp=Math.abs(psi[i]);
         if (datamax<tmp) datamax=tmp;
     }
     if (Math.abs(psi[numPts-1])>=datamax) {divergence=true;}

     return count;
  }


 /**
  *This method removes all items from the hashtable that keeps track of all the energy levels
  *It is called when the user hits the update button or changes the potential function
  */
 public void purgeHashtable(){
     Enumeration keys = eValues.keys();  //like its name, this method removes all elements from hashtable
     while (keys.hasMoreElements()){
           eValues.remove(keys.nextElement());
            }
     dataSources.removeAllElements();
     owner.deleteDataConnections();
 }

 /**
 * This method finds the energy level of the quantum number handed to it.
 * It does a binary search algorithm a max of 40 times, or if before that it reaches
 * a set tolerance value it will exit the loops.
 * For maximum speed, it checks the hashtable to see if the energy level has already
 * been found; if yes it just does 1 calculation at that energy, if not it finds it
 * and stores the found value in the hashtable.
 *
 *@param int n                quantum number of energy level to be found
 *@returns double energy      energy at which chosen energy level can be found
 */
 public double findElevel(int n, boolean shouldPlot){
    active=n;
    checkPotential(n);
    String key = ""+n;
    int count=0;
    if (resetE) {  //makesure that we start the calculations with the right energies
      eMax=funcmax;
      eMin=funcmin;
      energy=(funcmax-funcmin)/2.0;
      resetE=false;
    }
    if (eValues.get(key)!=null){//check hashtable to see if we already have found this level.
         energy=((Double)eValues.get(key)).doubleValue();
         calculatePsi(energy);
         active=n;           //if in this loop, we have already calculated this level so we just pull the energy value from the hashtable
         scaleData(energy, shouldPlot);
         setTitle(null);     //don't need the title because it converged!
      }
    else{
      int ii=0;
      while (calculatePsi(eMax)<n && ii<100) {
          eMax=1.1*eMax+1.0;  // make sure the max value is large enough!
          ii++;
      }
      for(int i=1; i<maxiterations; i++){
         count=calculatePsi(energy);
          if((Math.abs(psi[numPts-1])<tolerance)&& count==n) break;
            else{
              if (count>n) eMax=energy;
              if (count<n) eMin=energy;
              if (count==n) {
              if(psi[numPts-1]<0){
              eMax=energy;
              eGuess=eMax;
              }
              if(psi[numPts-1]>0){
              eMax=energy;
              eGuess=eMax;
              }
          }

          if(count>n) {
           eGuess=(eGuess+eMin)/2.0;
           energy=eGuess;
           }
          else if(count<n) {
                eGuess=(eMax+eGuess)/2.0;
                energy=eGuess;
            }
          else { if(count==n){//we are above actual value...but close!
                  if(psi[numPts-1]<0){//we are odd n so zoom in from above
                     energy=(eMax+eMin)/2.0;
                  }
                  if(psi[numPts-1]>0){//we are even n so zoom in from above
                  energy=(eMax+eMin)/2.0;
                  }

                  }
                }
         if (i==(maxiterations-1)) {System.out.println("Hit maximum iterations. n="+n);}
      }
    }
    if (divergence || broke |(!autoscalePotential && energy>getMaxY()) ){/*we don't put energy level into hashtable if it didn't converge!*/}
    else {
      eValues.put(key, new Double(energy));
      divergence=false;
    }
 }
     if (energy>getMaxY()&& !autoscalePotential){
        owner.noscale=true;
        setTitle("Level out of Range");
     }
     else {owner.noscale=false;}
     active=n;
     return energy;
 }

   /**
   * This method scales the data to 10% of the window so it can be seen better, and then puts it on the graph
   * This method requires an the energy value at which the calculations of the wave function occurred
   *
   *@param double en      the energy at which the E-level to be scaled is found
   */
   public void scaleData(double en, boolean plotWavefunction){
      setAutoRefresh(false);   //don't repaint yet...
      clearSeriesData(2);    //clear the series on graph already
      double energyRange=getMaxY()-getMinY();    // added by W. Christian
      if(autoscalePotential && en>getMaxY()-0.12*energyRange){
          double newMax=en;
          double oldMin=getMinY();
          setMinMaxY(oldMin,newMax+0.12*(newMax-oldMin));
           //if we are greater than the current maximun value on graph....
           //if (en<=10*funcmax){setMinMaxY(oldMin,newMax+0.12*(newMax-oldMin));}
            //else {setMinMaxY(oldMin,10*funcmax);

         //}

      }
      if(autoscalePotential && en<getMinY()){
          double oldMax=getMaxY();
          double newMin=en;
          if (en<funcmax){}
          else {setMinMaxY(newMin-0.12*(newMin-oldMax),oldMax);}
      }

      double height = (getMaxY()-getMinY())*0.10/datamax;  //10% of graph height
      double area = (getMaxY()-getMinY())*(getMaxX()-getMinX())*0.02/Math.sqrt(sum);

      for(int i=0; i<numPts; i++){
          if (scalearea) {
              psigraphed[i]=(area*(psi[i]))+en;
          }
          else {
              psigraphed[i]=(height*psi[i])+en;
          } //here we scale our data to look nice at 10% of window size
      }

     if(showWavefunction)addData(2,x,psigraphed); //add the puppy to the graph
     if(activeWavefunction!=null) activeWavefunction.updateValues(this.energy);
     if(activeState!=null) activeState.updateValues(this.energy,active);

     if ((divergence || broke) && !rclicked) {
        setTitle("No Convergence");
        //System.out.println("Divergence"+divergence+"  broke"+broke+"   rclicked"+rclicked);  //debug info
     }
     else {setTitle(null);}//clear graph title
     if (rclicked) {setTitle(null); } //don't set title when dragging on right mouse

     divergence=false;
     broke=false;
     setAutoRefresh(true);  //slap the new image on the screen baby
   }

  /**
  * This method checks to see if the potential max and min values are the same (IE constant)
  * If potential is constant, it adds 10 to min value.
  * This method is automatically called everytime the calcOnce or findElevel methods are executed as a safety
  *
  *@param int n       energylevel that we are searching for
  */
  private void checkPotential(int n){
    if (eMax==eMin) {
      eMax=eMin+10.0;
      energy=(eMax+eMin)/2.0;
      int count=calculatePsi(energy);
      if (count<n){
        while (count<n){
          eMax=eMax+10.0;
          eMin=energy;
          energy=(eMax+eMin)/2.0;
          count=calculatePsi(energy);
        }
      }
    }
  }

  /**
  * This method tells the graph whether or not to show the energy level diagram
  * Whether it does or not is determined by the boolean value it requires as input
  *
  *@param boolean show      show the energy level diagram on graph
  */
  public void showSpectrum(boolean show){
   showLevels=show;
  }

  /**
  * This method calculates a band of energy levels.  It calculates energy levels
  * corresponding to the input lowest value to the input highest value.  It does
  * so by calling the findElevel() method for each level user wants to be found
  *
  *@param int highest    lowest energy level to be found
  *@param int lowest     highest energy level to be found
  *
  */
  public void calculateLevels(int lowest, int highest){
        if (lowest>highest || lowest==0) {System.out.println("Error: parameter Higher>Lower");}
        else{
          //max=highest-lowest+1;
          int level=highest;
          for (int i=(highest); i>(lowest-1); i--) { //goes through and calculates range of scripted energy levels
              energy=(funcmax-funcmin)/2.0;  //resets energy to half potential (potential max-potentialmin)/2
              eMax=funcmax;
              eMin=funcmin;
              findElevel(level,true);

              if (level==lowest){  //puts lowest level on graph as active level
                scaleData(energy, true);
                owner.evalue.setValue(energy);
                owner.elevel.setValue(lowest);
              }
              level--;
        }
    }
    double lowE=funcmin,highE=funcmax;
    if(autoscalePotential){
        Enumeration levels = eValues.elements();  //like its name, this method removes all elements from hashtable
        while (levels.hasMoreElements()){
          double en= ((Double)levels.nextElement()).doubleValue();
          lowE=Math.min(lowE,en);
          highE=Math.max(highE,en);
        }
        double range=highE-lowE;
        setMinMaxY(lowE-0.12*range,highE+0.12*range);
        findElevel(lowest,true);
    }
  }
/*
  public void paint(Graphics g) {
      if(hideWavefunction){
          this.hideData=true;
          this.setShowAxes(false);
      }else{
          this.hideData=false;
          this.setShowAxes(true);
          super.paint(g);
      }
  }  */


  /**
 *  A hook into the Graph2D.paint method. This is called after
 *  everything has been drawn.
 *  The rectangle passed is the dimension of
 *  the data window.
 *  @params g Graphics state
 *  @params r Rectangle containing the data
 */
      public void paintLast( Graphics g, Rectangle r) {
        super.paintLast(g,r);
        g.setClip(0,0,this.getBounds().width,this.getBounds().width);
        if(energyDragMode) drawMyEnergy(g,energy);
      }

  /**
  *  This method forces the graph to paint the energy level diagram in the clipped area
  * It paints the levels only if the showlevels boolean is true from the showSpectrum() method
  *
  *@param Graphics g               graphics context
  *@param Rectangle r
  */
  public void paintBeforeData(Graphics g, Rectangle r) {
      int activex=0;
      int activey=0;
      boolean paintActive=false;
      int y=0,x=0,b=0;
      x = getSize().width;
      //double point;
      if (showLevels){
          borderRight=80;
          b = borderRight;
          g.setColor(Color.black);
          FontMetrics fm = g.getFontMetrics();
          int a=fm.getAscent();
          String s1 = owner.label_levels;
          g.drawString(s1,x-((int)(borderRight/2.0)+(int)(fm.stringWidth(s1)/2.0)), a);
          g.fillRect(x-(borderRight-5),a+2,(borderRight-10),(pixFromY(getMinY())-pixFromY(getMaxY())));
          borderTop=a+2;
          Enumeration levels = eValues.elements();
          Enumeration keys = eValues.keys();
          g.setColor(Color.green);

            while (levels.hasMoreElements()){
              y = pixFromY(((Double)levels.nextElement()).doubleValue());
              if (active==(Integer.valueOf((String)keys.nextElement()).intValue())&&!lines) {
                  g.setColor(Color.red);
                  paintActive=true;
                  activex=x;
                  activey=y;
              }
              else {g.setColor(Color.green); }
              g.drawLine(x-(b-9),y,x-9,y);
            }
            if(paintActive){
              g.setColor(Color.red);
              g.drawLine(activex-(b-9),activey,activex-9,activey);
            }
       }
      else {borderRight=10; }
      lines=false;
 }

 /**
  * @y.exclude
  * @param o
  */
  public EnergyGraph(SApplet o){
    setOwner(o);
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setSampleData(false);
    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        this_mouseDragged(e);
      }
    });
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        this_mouseReleased(e);
      }
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }
      public void mouseEntered(MouseEvent e) {
        this_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        this_mouseExited(e);
      }
    });
  }

  void this_mouseReleased(MouseEvent e) {
     energyDragMode=false;
     setAutoRefresh(false);
     int yPix=e.getY();
     int x = getSize().width;
     Rectangle dr = getBounds();
     repaint(0,dr.height-20,boxWidth,20);
     boxWidth=0;  // reset the yellow message box.

     if(rclicked){  //if right mouse button was pressed, cover up red line...
      Graphics g = getGraphics();
      g.setColor(Color.black);
      g.drawLine(x-(borderRight-9),yPix,x-9,yPix);
      owner.evalue.setValue(yFromPix(yPix));
      g.dispose();
      rclicked=false;
     }
     setAutoRefresh(true);
  }



  /**
  * This method determines the closest energy level to where the user is with the
  * mouse on the energy level diagram
  * It is called by the mousedragged and mousepressed events that occur
  *
  *@param int xPix        pixel on horizontal axis where mouse event occurred
  *@param int yPix        pixel on vertical axis where mouse event occurred
  *
  */
  public void determineClosest(int xPix, int yPix){
     double pt=0, q=0;
     int n=0, k=0;
     double difference=0.0, closest=70000.0;
     int x = getSize().width;
     Enumeration levels = eValues.elements();
     Enumeration keys = eValues.keys();
     ktemp=active;
     if (xPix>(x-(borderRight-5)) && (xPix<(x-5)) && (yPix>pixFromY(getMaxY())) && (yPix<pixFromY(getMinY()))) {
             while (levels.hasMoreElements()){
                  pt = ((Double)levels.nextElement()).doubleValue();
                   k = Integer.valueOf((String)keys.nextElement()).intValue();
                   q = yFromPix(yPix);
                   difference=Math.abs(pt-q);
                    if (difference<closest){  //this loop finds closest energy level to click event
                       closest=difference;
                       energy=pt;
                       n=k;
                       kcheck=n;
                       active=n;
                    }
             }
             if (ktemp==kcheck && !rclicked){return;}  //if energy is equal to the active level we skip this routine to save time and calculations
              else{
                if (energy==funcmax) {
                    resetE=true;
                    findElevel(n,true);
                    active=n;
                    scaleData(energy, true);
                    owner.elevel.setValue(n);
                    owner.evalue.setValue(energy);
                }
                else{
                calculatePsi(energy);   //this calculates and graphs wave function at energy closest to click event
                active=n;
                scaleData(energy,true);
                owner.elevel.setValue(n);
                owner.evalue.setValue(energy);
                }
             }
     }
  }

  void this_mousePressed(MouseEvent e) {
     int x = getSize().width;
     int xPix=e.getX();
     int yPix=e.getY();

     if(((e.getModifiers() & InputEvent.BUTTON3_MASK)!=0) && (xPix>(x-(borderRight-5))) && (xPix<(x-5))){
         if ((yPix>pixFromY(getMaxY())) && (yPix<pixFromY(funcmin))){
               setAutoRefresh(false);
               rclicked=true;
               active=0;
               energy=yFromPix(yPix);
               if (energy>10*funcmax){ /*NOTHING*/}
               else{
                  calculatePsi(energy);
                  scaleData(energy,true);
                  if ((autoscalePotential && yPix<pixFromY(10*funcmax))) {drawEnergyLine(pixFromY(10*funcmax));}
                  else {drawEnergyLine(yPix);}

               }
               drawMyCoords(xPix,yPix);
         }
    }
    else if((xPix>(x-(borderRight-5))) && (xPix<(x-5)) && (yPix>pixFromY(getMaxY())) && (yPix<pixFromY(getMinY()))) {
            determineClosest(xPix,yPix);
        }
    else if (xPix<(x-(borderRight-5)) && !((e.getModifiers() & InputEvent.BUTTON3_MASK)!=0)) {
              drawMyCoords(xPix,yPix);
         }
    else if((e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){ // clone
        SGraphFrame graphFrame=new SGraphFrame((SGraph)this.clone());
        graphFrame.setSize(this.getSize().width,this.getSize().height);
        graphFrame.show();
    }
  }

  void this_mouseDragged(MouseEvent e) {
    int x = getSize().width;
    int xPix=e.getX();
    int yPix=e.getY();

    if(((e.getModifiers() & InputEvent.BUTTON3_MASK)!=0) && (xPix>(x-(borderRight-5))) && (xPix<(x-5))){
         if ( (yPix>pixFromY(getMaxY()))&& (yPix<pixFromY(funcmin)) ){  //make sure we are in the boundries of the energy level diagram vertically
               setAutoRefresh(false);
               rclicked=true;
               active=0;
               energy=yFromPix(yPix);
               if (energy>10*funcmax){ /*NOTHING*/}
               else{
                  calculatePsi(energy);
                  scaleData(energy,true);
                  if ((autoscalePotential && yPix<pixFromY(10*funcmax))) {drawEnergyLine(pixFromY(10*funcmax));}
                  else {drawEnergyLine(yPix);}

               }
               drawMyCoords(xPix,yPix);
         }
    }
    else if((xPix>(x-(borderRight-5))) && (xPix<(x-5)) && (yPix>pixFromY(getMaxY())) && (yPix<pixFromY(getMinY()))) {
            determineClosest(xPix,yPix);
        }
    else if (xPix<(x-(borderRight-5)) && !((e.getModifiers() & InputEvent.BUTTON3_MASK)!=0)) {
              drawMyCoords(xPix,yPix);
         }
  }

  void drawEnergyLine(int yPix){
       lines=true;
       int w = getSize().width;
       Graphics lg = getGraphics();
       if(lg==null)return;
       lg.setColor(Color.red);
       lg.drawLine(w-(borderRight-9),yPix,w-9,yPix);
       lg.dispose();
  }

  void this_mouseEntered(MouseEvent e) {
    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

  void this_mouseExited(MouseEvent e) {
    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

  /**
  * This method determines a location so that the amplitude of the wave function at a specific x value when
  * the right mouse is clicked can be found.
  *
  *@param int xPix             pixel on horizontal axis where mouse event occurred
  *@returns int location       location at which to find amplitude of wave function
  */
  private int amplitude(int xPix){
      double location=0;
      double f=pixFromX(getMaxX());
      double g=pixFromX(getMinX());
      location=Math.round(numPts*((xPix-g)/(f-g)));

      return (int)location;
  }

  public void drawMyCoords(int xPix,int yPix){
        int x = getSize().width;
        String msg=null;

        if((xPix>(x-(borderRight-5))) && (xPix<(x-5)) && (yPix>pixFromY(getMaxY())) && (yPix<pixFromY(getMinY()))){
            msg="E:"+format.form(yFromPix(yPix));
            energyDragMode=true;
            return;
        }
        else if (xPix<(x-(borderRight))){
         int index= amplitude(xPix);
         if(index>=0 && index <numPts)
             msg="x:"+format.form(xFromPix(xPix))+", E:"+format.form(yFromPix(yPix))+" Psi:"+format.form(psigraphed[index]);
         else
             msg="x:"+format.form(xFromPix(xPix))+", E:"+format.form(yFromPix(yPix));
        }
        Graphics dg=getGraphics();
        Rectangle dr = getBounds();
        dg.setColor(Color.yellow);
        FontMetrics fm=dg.getFontMetrics(dg.getFont());
        boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
        dg.fillRect(0,dr.height-20,boxWidth,20);
        dg.setColor(Color.black);
        dg.drawString(msg,10,dr.height-5);
        dg.dispose();
  }

  public void drawMyEnergy(Graphics g, double en){
        //int x = getSize().width;
        String msg="E:"+format.form(en);
        Rectangle dr = getBounds();
        g.setColor(Color.yellow);
        FontMetrics fm=g.getFontMetrics(g.getFont());
        boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
        g.fillRect(0,dr.height-20,boxWidth,20);
        g.setColor(Color.black);
        g.drawString(msg,10,dr.height-5);
  }

  int getActiveWavefunctionID(){
      if(activeWavefunction!=null) return  activeWavefunction.getID();
      activeWavefunction=new ActiveWavefunction();
      activeWavefunction.updateValues(this.energy);
      return activeWavefunction.getID();
  }

  int getActiveStateID(){
      if(activeState!=null) return  activeState.getID();
      activeState=new ActiveState(energy,active);
      return activeState.getID();
  }

    /**
   * Gets the quantum number of the active state.
   *
   * @return int the quantum number
   */
   public int getActiveQuantumNumber(){
      return active;
   }

     /**
   * Gets the energy of the active state.
   *
   * @return double the energy
   */
   public double getActiveEnergy(){
      return energy;
   }

  int getWavefunctionID(int n){
     Wavefunction wf=null;
     for(Enumeration e=dataSources.elements() ; e.hasMoreElements(); ){
          wf=(Wavefunction)e.nextElement();
          if(wf.qnumber==n) return wf.getID();
      }
      wf=new Wavefunction(n);
      dataSources.addElement(wf);
      return wf.getID();
  }

  // inner class used for data connection
  public class Wavefunction extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"x","p","psi","energy"};
    double[][] ds=new double[1][4];
    int qnumber=0;
    double eigenenergy=0;

    Wavefunction(int n){ // Constructor
       qnumber=n;
       eigenenergy=findElevel(qnumber,false);
       ds=new double[numPts][4];
       double dataheight=Math.abs(psi[0]);
       double norm=0;
       double dx=Math.abs(x[1]-x[0]);
       for(int i=1; i<numPts; i++){
          dataheight=Math.max(dataheight,Math.abs(psi[i]));
          norm+=psi[i]*psi[i]*dx;
       }
       norm=Math.sqrt(norm);
       if(dataheight==0)  dataheight=1;
       if(norm==0)  norm=1;

       //double height = (getMaxY()-getMinY())*0.10/datamax;  //10% of graph height
       //double area = (getMaxY()-getMinY())*(getMaxX()-getMinX())*0.02/Math.sqrt(sum);

       for(int i=0; i<numPts; i++){
          ds[i][0]=x[i];
          ds[i][1]=y[i];
          if (scalearea) {
            ds[i][2]=psi[i]/norm;
          } else {
            ds[i][2]=psi[i]/dataheight;
          }
          ds[i][3]=eigenenergy;
        }

       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
      return ds;
    }

    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return owner;}    //Owner is an SApplet. EnergyEigenvalue is an SApplet
  }

    // inner class used for data connection to histogram.
  public class ActiveWavefunction extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"x","p","psi","energy"};  // speed and number
    double[][] ds=new double[1][4];  // the datasource state variables v, n;
    double eigenenergy=0;

    ActiveWavefunction(){ // Constructor
       ds=new double[numPts][4];
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    void updateValues(double en){
       eigenenergy=en;
       if(x==null || y==null || psigraphed==null ) return;
       int numPts=x.length;
       if(ds==null || ds.length != x.length){  // the number of points has changed.
           ds= new double[numPts][4];
       }
       double dataheight=Math.abs(psigraphed[0]-en);
       double norm=0;
       double dx=Math.abs(x[1]-x[0]);
       for(int i=1; i<numPts; i++){
          double psi=psigraphed[i]-en;
          dataheight=Math.max(dataheight,Math.abs(psi));
          norm+=psi*psi*dx;
       }
       norm=Math.sqrt(norm);
       if(dataheight==0)  dataheight=1;
       if(norm==0)  norm=1;

       for(int i=0; i<numPts; i++){
          ds[i][0]=x[i];
          ds[i][1]=y[i];
          //ds[i][2]=psigraphed[i]-en;
          if (scalearea) {
            ds[i][2]=(psigraphed[i]-en)/norm;
          } else {
            ds[i][2]=(psigraphed[i]-en)/dataheight;
          }
          ds[i][3]=en;
        }
       //System.out.println("e "+en);
        owner.updateDataConnections();
    }

    public double[][] getVariables(){
      return ds;
    }

    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return owner;}    //Owner is an SApplet. EnergyEigenvalue is an SApplet
  }

  public class ActiveState extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"n","energy",};  // speed and number
    double[][] ds=new double[1][2];  // the datasource state variables v, n;
    double eigenenergy=0;
    double qn=0;

    ActiveState(double en, int n){ // Constructor
       eigenenergy=en;// the energy of the state
       qn=n; // the quantum number of the state
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    void updateValues(double en, int n){
       eigenenergy=en;// the energy of the state
       qn=n; // the quantum number of the state
       owner.updateDataConnections();
    }

    public double[][] getVariables(){
      ds[0][0]=qn;
      ds[0][1]=eigenenergy;
      return ds;
    }

    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return owner;}    //Owner is an SApplet. EnergyEigenvalue is an SApplet
  }

}