

package molecular;

import java.awt.*;
import edu.davidson.tools.*;
          
public class MFEnsemblePanel extends EnsemblePanel implements SStepable {
  boolean flows = false;
  double iflCounter=0;
  double oflCounter=0;
  double ifrCounter=0;
  double ofrCounter=0;
  int midHeight=15;
  int midLength=20;
  int lwidth=70;
  boolean lwSet=false;
  double top,bottom;
  int nCollidingW=0;
  int nCollidingE=1;
  double time=0;
  double mint=10000;
  double temptime=10000;
  double weight=100;
  double wVel=0;
  MFLEnsemble ensemble1=null;
  MFREnsemble ensemble2=null;
  MFEnsemble ensemble3=null;
  double iFLeft = 100000;
  double iFRight = 100000;
  double oFLeft = 100000;
  double oFRight = 100000;
  double oRRate=0;
  double oLRate=0;
  double iLRate=0;
  double iRRate=0;
  double wwidth=1.3;
  boolean partInt=true;  //particle-to-particle interations
  //Parser pparser=null;
  //double cwpos=0;
  //Image osi=null;
  //SApplet owner=null;


  public MFEnsemblePanel(SApplet o){
    super(o);
    ensemble1 = new MFLEnsemble(this);
    ensemble2 = new MFREnsemble(this);
    ensemble3 = new MFEnsemble(this);
  }

  public void update(Graphics g){
    paint(g);
  }

  public void step(double dt, double time){

    int i;
    double tl=dt;                             //tl = time left until a paint step
    ensemble1.clearPressureCounters();
    ensemble2.clearPressureCounters();
    ensemble3.clearPressureCounters();
    do {
      if (tl<mint){
        mint-=tl;
        tl-=advanceDT(tl);
      }                                              //setTime 0 t

      else {
        tl-=advanceDT(mint);
        updateTimes(temptime);
        switch (nCollidingW){
          case 0: collideParticles(); break;
          case 1: collideTW(); break;
          case 2: collideRW(); break;
          case 3: collideBW(); break;
          case 4: collideLW(); break;
        }
        findMinColTime();
      }

    } while (tl>0);

    if (flows){
      owner.clock.stopClock();
      if(iflCounter<=0){
         ensemble1.iflowPart();
         iflCounter=iFLeft;
      }
      else iflCounter-=dt;

      if(ifrCounter<=0){
        ensemble2.iflowPart();
        ifrCounter=iFRight;
      }
      else ifrCounter-=dt;

      if(oflCounter<=0){
        ensemble1.oflowPart();
         oflCounter=oFLeft;
      }
      else oflCounter-=dt;

      if(ofrCounter<=0){
        ensemble2.oflowPart();
        ofrCounter=oFRight;
      }
      else ofrCounter-=dt;
      owner.clock.startClock();
    }

    paintOSI();
    Graphics g = this.getGraphics();
    paint(g);
    g.dispose();

    this.time=time+dt;
    ensemble1.adjustPressureCounters(dt);
    ensemble2.adjustPressureCounters(dt);
    ensemble3.adjustPressureCounters(dt);

    if(ensemble1.histogram!=null)owner.clearData(ensemble1.histogram.hashCode());
    if(ensemble2.histogram!=null)owner.clearData(ensemble2.histogram.hashCode());
    if(ensemble3.histogram!=null)owner.clearData(ensemble2.histogram.hashCode());
    if(owner!=null) owner.updateDataConnections();

  }

  public void collideParticles(){
    if (!partInt) return;
    if (nCollidingE==1) ensemble1.collideParticles(ensemble1.nColliding1,ensemble1.nColliding2);
    else if (nCollidingE==2) ensemble2.collideParticles(ensemble2.nColliding1,ensemble2.nColliding2);
    else if (nCollidingE==3) ensemble3.collideParticles(ensemble3.nColliding1,ensemble3.nColliding2);
  }

  public void collideTW(){
    if (nCollidingE==1) ensemble1.collideTW(ensemble1.nColliding1);
    else if (nCollidingE==2) ensemble2.collideTW(ensemble2.nColliding1);
    else if (nCollidingE==3) ensemble3.collideTW(ensemble3.nColliding1);
  }
  public void collideRW(){
    if (nCollidingE==1) ensemble1.collideRW(ensemble1.nColliding1);
    else if (nCollidingE==2) ensemble2.collideRW(ensemble2.nColliding1);
    else if (nCollidingE==3) ensemble3.collideRW(ensemble3.nColliding1);
  }
  public void collideBW(){
    if (nCollidingE==1) ensemble1.collideBW(ensemble1.nColliding1);
    else if (nCollidingE==2) ensemble2.collideBW(ensemble2.nColliding1);
    else if (nCollidingE==3) ensemble3.collideBW(ensemble3.nColliding1);
  }
  public void collideLW(){
    if (nCollidingE==1) ensemble1.collideLW(ensemble1.nColliding1);
    else if (nCollidingE==2) ensemble2.collideLW(ensemble2.nColliding1);
    else if (nCollidingE==3) ensemble3.collideLW(ensemble3.nColliding1);
  }

  public double advanceDT(double t){
      ensemble1.advanceDT(t);
      ensemble2.advanceDT(t);
      ensemble3.advanceDT(t);
      return t;
  }

  //public void recalcLWColTimes(){
  //   for (int q2=0; q2<ensemble2.lastpartnum+1; q2++) if (!ensemble2.empty[q2]) ensemble2.lwColTimes[q2]=ensemble2.calcColTimeLW(q2);
  //}
  //public void recalcRWColTimes(){
  //   for (int q1=0; q1<ensemble1.lastpartnum+1; q1++) if (!ensemble1.empty[q1]) ensemble1.rwColTimes[q1]=ensemble1.calcColTimeRW(q1);
  //}

  public void updateTimes(double dt){
      for (int i=0; i<ensemble1.lastpartnum+1; i++)
        if (!ensemble1.empty[i]){
          ensemble1.rwColTimes[i]-=dt;
          ensemble1.lwColTimes[i]-=dt;
          ensemble1.twColTimes[i]-=dt;
          ensemble1.bwColTimes[i]-=dt;
        }
        if (partInt)
        for (int y=1; y<ensemble1.lastpartnum+1; y++)
            for (int x=0; x<y; x++)
              if (!ensemble1.empty[y] && !ensemble1.empty[x]){
                ensemble1.colTimes[y][x]-=dt;
            }

      for (int i=0; i<ensemble2.lastpartnum+1; i++)
        if (!ensemble2.empty[i]){
          ensemble2.rwColTimes[i]-=dt;
          ensemble2.lwColTimes[i]-=dt;
          ensemble2.twColTimes[i]-=dt;
          ensemble2.bwColTimes[i]-=dt;
        }
        if (partInt)
        for (int y=1; y<ensemble2.lastpartnum+1; y++)
            for (int x=0; x<y; x++)
              if (!ensemble2.empty[y] && !ensemble2.empty[x]){
                ensemble2.colTimes[y][x]-=dt;
            }

      for (int i=0; i<ensemble3.lastpartnum+1; i++)
        if (!ensemble3.empty[i]){
          ensemble3.rwColTimes[i]-=dt;
          ensemble3.lwColTimes[i]-=dt;
          ensemble3.twColTimes[i]-=dt;
          ensemble3.bwColTimes[i]-=dt;
        }
        if (partInt)
        for (int y=1; y<ensemble3.lastpartnum+1; y++)
            for (int x=0; x<y; x++)
              if (!ensemble3.empty[y] && !ensemble3.empty[x]){
                ensemble3.colTimes[y][x]-=dt;
            }
  }

  public double findMinColTime(){
      ensemble1.findMinColTime();
      ensemble2.findMinColTime();
      ensemble3.findMinColTime();
       double mint1=ensemble1.mint;
       double mint2=ensemble2.mint;
       double mint3=ensemble3.mint;
      if ((mint1<=mint2)&&(mint1<=mint3)) {
        nCollidingE=1;
        nCollidingW=ensemble1.nCollidingW;
        mint=mint1;
        temptime=mint;
        return mint1;
      }
      else if ((mint2<=mint1)&&(mint2<=mint3)){
        nCollidingE=2;
        nCollidingW=ensemble2.nCollidingW;
        mint=mint2;
        temptime=mint;
        return mint2;
      }
      else if ((mint3<=mint1)&&(mint3<=mint2)){
        nCollidingE=3;
        nCollidingW=ensemble3.nCollidingW;
        mint=mint3;
        temptime=mint;
        return mint3;
      }
      else {System.out.println("DEnsemblePanel: findMinColTime");return 10000;}
  }

  public void repaint(){
     paintOSI();
     Graphics g= this.getGraphics();
     paint(g);
     g.dispose();
  }

  public void paintOSI(){
    if(osi==null) return;
    Graphics osg = osi.getGraphics();
    if(osg==null) return;
    osg.setColor(Color.black);
    osg.fillRect(0,0,currentw,currenth);
    ensemble3.paintOSI();
    ensemble1.paintOSI();
    ensemble2.paintOSI();
    osg.setColor(Color.black);
    if(showTitle) paintCaption(osg);
    osg.dispose();
  }


  public void setDefault(){
      wVel=0;
      ensemble1.setDefault();
      ensemble2.setDefault();
      ensemble3.setDefault();
  }

  /**
  *
  * Turns particle-to-particle interations on and off
  *
  *
  * @param tf boolean
  */
  public void setPartInteractions(boolean tf){
     if (owner.clock.isRunning()) ensemble1.pause();
     this.partInt=tf;
     osi=null;
     ensemble1.xPos=null;
     ensemble2.xPos=null;
     ensemble3.xPos=null;
     if ((tf)&&(ensemble1.maxp>250))
        System.out.println("Warning: Max Particles= "+ensemble1.maxp+" "+"and PartInteractions=true uses a lot of memory!");
  }

  /**
  *
  * Method sets width of diffusion tube in units
  *
  *
  * @param w double
  */
  public void setTubeWidth(int w){
    if (w>=5)this.midHeight=w;
    osi=null;
  }

  /**
  *
  * Method sets length of diffusion tube in units
  * Default is 8
  *
  * @param l double
  */
  public void setTubeLength(int l){
    if (l>=5) this.midLength=l;
    osi=null;
  }

   /**
  *
  * Method sets length of diffusion tube in units
  * Default is 8
  *
  * @param l double
  */
  public void setLeftWidth(int w, boolean tf){
    if ((w>9)&&(w<190))this.lwidth=w;
    lwSet = tf;
    if (tf) osi=null;
  }

   void setHeights(int mh, double tp, double bt){
        this.top=tp;
        this.bottom=bt;
        ensemble1.midHeight=mh;
        ensemble2.midHeight=mh;
        ensemble3.midHeight=mh;
        ensemble1.top=tp;
        ensemble2.top=tp;
        ensemble3.top=tp;
        ensemble1.bottom=bt;
        ensemble2.bottom=bt;
        ensemble3.bottom=bt;
        this.midHeight=mh;
  }

  public void setBounds(){
    if (lwSet) {setBounds2(); return;}
    currentw = getSize().width;
    currenth = getSize().height;
    double tw=(currentw/(double)(ppu));
    double ml=midLength/(double)ppu;
    ml=Math.min(ml,0.7*tw);
    ml=Math.max(ml,0.1*tw);
    lcwpos=((currentw/(double)(ppu))-ml)/2;
    ensemble1.setRWPos(lcwpos);
    ensemble1.setLWPos(0);
    ensemble1.setTWPos(currenth/((double)ppu));
    ensemble1.setBWPos(0);
    ensemble1.xOrigin=(lcwpos+ensemble1.lwpos)/2;
    ensemble1.yOrigin=(ensemble1.twpos+ensemble1.bwpos)/2;
    rcwpos=ml+lcwpos;
    ensemble1.setPeriodicV(false);
    if (ensemble1.periodicv){
       ensemble1.setNeighbor(1,ensemble1,0,ensemble1.twpos);
       ensemble1.setNeighbor(5,ensemble1,0,-ensemble1.twpos);
    }
    ensemble1.setPeriodicH(false);
    setHeights(midHeight,ensemble1.twpos,ensemble1.bwpos);
    double mh=midHeight/(double)ppu;
    mh=Math.min(mh,0.9*(top-bottom));
    ensemble3.setLWPos(lcwpos);
    ensemble3.setRWPos(rcwpos);
    ensemble3.setTWPos((ensemble1.twpos+ensemble1.bwpos+mh)/2);
    //ensemble3.setTWPos(currenth/((double)ppu));
    //ensemble3.setBWPos(0);
    ensemble3.setBWPos(ensemble3.twpos-mh);
    ensemble3.xOrigin=(rcwpos+lcwpos)/2;
    ensemble3.yOrigin= (ensemble3.twpos+ensemble3.bwpos)/2;
    ensemble3.setPeriodicV(false);
    ensemble3.setPeriodicH(false);
    ensemble2.setLWPos(rcwpos);
    ensemble2.setRWPos(currentw/((double)ppu));
    ensemble2.setTWPos(currenth/((double)ppu));
    ensemble2.setBWPos(0);
    ensemble2.xOrigin=(ensemble2.rwpos+rcwpos)/2;
    ensemble2.yOrigin= (ensemble2.twpos+ensemble2.bwpos)/2;
    ensemble2.setPeriodicV(false);
    if (ensemble2.periodicv){
       ensemble2.setNeighbor(1,ensemble2,0,ensemble2.twpos);
       ensemble2.setNeighbor(5,ensemble2,0,-ensemble2.twpos);
    }
    ensemble2.setPeriodicH(false);
    setHorizontalNeighbors();
    //osi = createImage(currentw,currenth);
    makeImage();
  }


  public void setBounds2(){
    currentw = getSize().width;
    currenth = getSize().height;
    double tw=(currentw/(double)(ppu));
    double lw=(lwidth/(double)ppu);
    if ((lw>0.049*tw)&&(lw<0.8*tw)) lcwpos=lw;
    else lcwpos=tw/4;
    double ml=midLength/(double)ppu;
    ml=Math.min(ml,0.8*(tw-lcwpos));
    ml=Math.max(ml,0.1*tw);
    ensemble1.setRWPos(lcwpos);
    ensemble1.setLWPos(0);
    ensemble1.setTWPos(currenth/((double)ppu));
    ensemble1.setBWPos(0);
    ensemble1.xOrigin=(lcwpos+ensemble1.lwpos)/2;
    ensemble1.yOrigin=(ensemble1.twpos+ensemble1.bwpos)/2;
    rcwpos=ml+lcwpos;
    if (ensemble1.periodicv){
       ensemble1.setNeighbor(1,ensemble1,0,ensemble1.twpos);
       ensemble1.setNeighbor(5,ensemble1,0,-ensemble1.twpos);
    }
    ensemble1.setPeriodicH(false);
    setHeights(midHeight,ensemble1.twpos,ensemble1.bwpos);
    double mh=midHeight/(double)ppu;
    mh=Math.min(mh,0.9*(top-bottom));
    ensemble3.setLWPos(lcwpos);
    ensemble3.setRWPos(rcwpos);
    ensemble3.setTWPos((ensemble1.twpos+ensemble1.bwpos+mh)/2);
    //ensemble3.setTWPos(currenth/((double)ppu));
    //ensemble3.setBWPos(0);
    ensemble3.setBWPos(ensemble3.twpos-mh);
    ensemble3.xOrigin=(rcwpos+lcwpos)/2;
    ensemble3.yOrigin= (ensemble3.twpos+ensemble3.bwpos)/2;
    ensemble3.setPeriodicV(false);
    ensemble3.setPeriodicH(false);
    ensemble2.setLWPos(rcwpos);
    ensemble2.setRWPos(currentw/((double)ppu));
    ensemble2.setTWPos(currenth/((double)ppu));
    ensemble2.setBWPos(0);
    ensemble2.xOrigin=(ensemble2.rwpos+rcwpos)/2;
    ensemble2.yOrigin= (ensemble2.twpos+ensemble2.bwpos)/2;
    if (ensemble2.periodicv){
       ensemble2.setNeighbor(1,ensemble2,0,ensemble2.twpos);
       ensemble2.setNeighbor(5,ensemble2,0,-ensemble2.twpos);
    }
    ensemble2.setPeriodicH(false);
    setHorizontalNeighbors();
    //osi = createImage(currentw,currenth);
    makeImage();
  }

  /**
  *
  * Method sets horizontal neighbors for three ensembles
  *
  * periodich should be false.
  *
  */
  void setHorizontalNeighbors(){
     ensemble1.periodich=false;
     ensemble2.periodich=false;
     ensemble3.periodich=false;
     ensemble1.neighbor[0]=null;
     ensemble1.neighbor[6]=null;
     ensemble1.neighbor[7]=null;
     ensemble1.setNeighbor(3, ensemble3,0,0);
     ensemble3.setNeighbor(7, ensemble1,0,0);
     ensemble3.setNeighbor(3, ensemble2,0,0);
     ensemble2.setNeighbor(7, ensemble3,0,0);
  }

  /**
  *
  * Method sets rate at which particles flow into left ensemble
  *
  * @param rate double
  */
  public void setInflowLeft(double rate){
    if(rate<0.005) this.iFLeft= 100000;
    else this.iFLeft=1/rate;
    iflCounter=iFLeft;
    iLRate=rate;
  }

  /**
  *
  * Method sets rate at which particles flow out of left ensemble
  *
  * @param prob double
  */
  public void setOFProbLeft(double prob){
    ensemble1.oLProb=prob;
  }



  /**
  *
  * Method sets rate at which particles flow out of left ensemble
  *
  * @param rate double
  */
  public void setOutflowLeft(double rate){
    if(rate<0.0005) this.oFLeft= 100000;
    else this.oFLeft=1/rate;
    oflCounter=oFLeft;
    oLRate=rate;
  }

  /**
  *
  * Method sets rate at which particles flow into right ensemble
  *
  * @param rate double
  */
  public void setInflowRight(double rate){
    if(rate<0.0005) this.iFRight= 100000;
    else this.iFRight=1/rate;
    ifrCounter=iFRight;
    iRRate=rate;
  }

  /**
  *
  * Method sets probability of particle flow out of right ensemble
  *
  * @param prob double
  */
  public void setOFProbRight(double prob){
    ensemble2.oRProb=prob;
  }


  /**
  *
  * Method sets rate at which particles flow out of right ensemble
  *
  * @param rate double
  */
  public void setOutflowRight(double rate){
    if(rate<0.0005) this.oFRight= 100000;
    else this.oFRight=1/rate;
    ofrCounter=oFRight;
    oRRate=rate;
  }

  public void setEnableFlow(boolean tf){
    boolean runAgain=false;
    if (owner.clock.isRunning()){
      ensemble1.pause();
      runAgain=true;
    }
    this.flows=tf;
    if (tf){
      setOutflowRight(oRRate);
      setInflowRight(iRRate);
      setOutflowLeft(oLRate);
      setInflowLeft(iLRate);
    }
    if (runAgain) ensemble1.forward();
  }


  /**
  *
  * Flashes offscreen image onto screen
  *
  *
  */
  public void paint(Graphics g){
    if (osi==null || currentw!=getSize().width || currenth!=getSize().height){
        if(getSize().width>2){
            boolean shouldRun=owner.clock.isRunning();
            owner.clock.stopClock();
            setBounds();
            g.drawImage(osi,0,0,currentw,currenth,this);
            if (shouldRun) owner.clock.startClock();
        } else return;
    }else{
    g.drawImage(osi,0,0,currentw,currenth,this);
    }
    if(ensemble1.mouseDown) ensemble1.paintCoords(g);  // added by W. Christian
    if(ensemble2.mouseDown) ensemble2.paintCoords(g);  // added by W. Christian
    if(ensemble3.mouseDown) ensemble3.paintCoords(g);
  }

  /*
  public boolean setProbabilityFunction(String str){
    boolean noError=true;
    String xstr=str;
    xstr.toLowerCase().trim();
    pparser = new Parser(1);
    pparser.define(xstr);
    pparser.defineVariable(1,"m");
    pparser.parse();
    if(pparser.getErrorCode() != pparser.NO_ERROR){     // error checkeing added by W. Christian
         noError=false;
         System.out.println("Failed to parse P(m)): "+xstr);
         System.out.println("Parse error in MathFunction: " + pparser.getErrorString() +
                   " at function 1, position " + pparser.getErrorPosition());
      }
    return noError;}
    */


  public MFLEnsemble getEnsemble1(){
    return ensemble1;
  }

  public MFREnsemble getEnsemble2(){
    return ensemble2;
  }

  public MFEnsemble getEnsemble3(){
    return ensemble3;
  }

  public MFEnsemblePanel() {
  }



  public void recalculateColTimes(){
    for (int i=0; i<ensemble1.lastpartnum+1; i++)
        if (ensemble1.empty[i]==false){
        int p=i;
        ensemble1.rwColTimes[i]=ensemble1.calcColTimeRW(i);
        ensemble1.lwColTimes[i]=ensemble1.calcColTimeLW(i);
        ensemble1.twColTimes[i]=ensemble1.calcColTimeTW(i);
        ensemble1.bwColTimes[i]=ensemble1.calcColTimeBW(i);
        }
    if (partInt)
    for (int y=1; y<ensemble1.lastpartnum+1; y++)
        for (int x=0; x<y; x++){
              if (ensemble1.empty[x]==false && ensemble1.empty[y]==false)
                  ensemble1.colTimes[y][x]=ensemble1.calcColTime(y,x);
            }

    for (int i=0; i<ensemble2.lastpartnum+1; i++)
        if (ensemble2.empty[i]==false){
        int p=i;
        ensemble2.rwColTimes[i]=ensemble2.calcColTimeRW(i);
        ensemble2.lwColTimes[i]=ensemble2.calcColTimeLW(i);
        ensemble2.twColTimes[i]=ensemble2.calcColTimeTW(i);
        ensemble2.bwColTimes[i]=ensemble2.calcColTimeBW(i);
        }
    if (partInt)
    for (int y=1; y<ensemble2.lastpartnum+1; y++)
        for (int x=0; x<y; x++){
              if (ensemble2.empty[x]==false && ensemble2.empty[y]==false)
                  ensemble2.colTimes[y][x]=ensemble2.calcColTime(y,x);
            }

    for (int i=0; i<ensemble3.lastpartnum+1; i++)
        if (ensemble3.empty[i]==false){
        int p=i;
        ensemble3.rwColTimes[i]=ensemble3.calcColTimeRW(i);
        ensemble3.lwColTimes[i]=ensemble3.calcColTimeLW(i);
        ensemble3.twColTimes[i]=ensemble3.calcColTimeTW(i);
        ensemble3.bwColTimes[i]=ensemble3.calcColTimeBW(i);
        }
    if (partInt)
    for (int y=1; y<ensemble3.lastpartnum+1; y++)
        for (int x=0; x<y; x++){
              if (ensemble3.empty[x]==false && ensemble3.empty[y]==false)
                  ensemble3.colTimes[y][x]=ensemble3.calcColTime(y,x);
            }
    findMinColTime();
  }

}
