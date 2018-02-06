package molecular;

import java.awt.*;
import edu.davidson.tools.*;

public class TPEnsemblePanel extends EnsemblePanel implements SStepable {
  WallDataSource wallDataSource=null;
  int nCollidingW=0;
  int nCollidingE=1;
  double time=0;
  double mint=10000;
  double temptime=10000;
  double weight=100;
  double wVel=0;
  double ppos=0.5;
  LEnsemble ensemble1=null;
  REnsemble ensemble2=null;
  double wwidth=2;
  //double cwpos=0;
  //Image osi=null;
  //SApplet owner=null;


  public TPEnsemblePanel(SApplet o) {
    super(o);
    ensemble1 = new LEnsemble(this);
    ensemble2 = new REnsemble(this);
  }

  public void update(Graphics g){
    paint(g);
  }

  public void step(double dt, double time){

    int i;
    double tl=dt;                             //tl = time left until a paint step
    ensemble1.clearPressureCounters();
    ensemble2.clearPressureCounters();
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


    Graphics g = this.getGraphics();
    paintOSI();
    paint(g);
    g.dispose();
    this.time=time+dt;
    ensemble1.adjustPressureCounters(dt);
    ensemble2.adjustPressureCounters(dt);
    if(ensemble1.histogram!=null)owner.clearData(ensemble1.histogram.hashCode());
    if(ensemble2.histogram!=null)owner.clearData(ensemble2.histogram.hashCode());
    if(owner!=null) owner.updateDataConnections();

  }

  public void collideParticles(){
    if (nCollidingE==1) ensemble1.collideParticles(ensemble1.nColliding1,ensemble1.nColliding2);
    else if (nCollidingE==2) ensemble2.collideParticles(ensemble2.nColliding1,ensemble2.nColliding2);
  }

  public void collideTW(){
    if (nCollidingE==1) ensemble1.collideTW(ensemble1.nColliding1);
    else if (nCollidingE==2) ensemble2.collideTW(ensemble2.nColliding1);
  }
  public void collideRW(){
    if (nCollidingE==1) ensemble1.collideRW(ensemble1.nColliding1);
    else if (nCollidingE==2) ensemble2.collideRW(ensemble2.nColliding1);
  }
  public void collideBW(){
    if (nCollidingE==1) ensemble1.collideBW(ensemble1.nColliding1);
    else if (nCollidingE==2) ensemble2.collideBW(ensemble2.nColliding1);
  }
  public void collideLW(){
    if (nCollidingE==1) ensemble1.collideLW(ensemble1.nColliding1);
    else if (nCollidingE==2) ensemble2.collideLW(ensemble2.nColliding1);
  }



  public double advanceDT(double t){
      ensemble1.advanceDT(t);
      ensemble2.advanceDT(t);
      ensemble1.rwpos+=wVel*t;
      ensemble2.lwpos+=wVel*t;
      cwpos+=wVel*t;
      return t;
  }

  public void recalcLWColTimes(){
     for (int q2=0; q2<ensemble2.lastpartnum+1; q2++) if (!ensemble2.empty[q2]) ensemble2.lwColTimes[q2]=ensemble2.calcColTimeLW(q2);
  }
  public void recalcRWColTimes(){
     for (int q1=0; q1<ensemble1.lastpartnum+1; q1++) if (!ensemble1.empty[q1]) ensemble1.rwColTimes[q1]=ensemble1.calcColTimeRW(q1);
  }

  public void updateTimes(double dt){
      for (int i=0; i<ensemble1.lastpartnum+1; i++){
        ensemble1.rwColTimes[i]-=dt;
        ensemble1.lwColTimes[i]-=dt;
        ensemble1.twColTimes[i]-=dt;
        ensemble1.bwColTimes[i]-=dt;
        }
        for (int y=1; y<ensemble1.lastpartnum+1; y++)
            for (int x=0; x<y; x++){
            ensemble1.colTimes[y][x]-=dt;
            }

      for (int i=0; i<ensemble2.lastpartnum+1; i++){
        ensemble2.rwColTimes[i]-=dt;
        ensemble2.lwColTimes[i]-=dt;
        ensemble2.twColTimes[i]-=dt;
        ensemble2.bwColTimes[i]-=dt;
        }
        for (int y=1; y<ensemble2.lastpartnum+1; y++)
            for (int x=0; x<y; x++){
            ensemble2.colTimes[y][x]-=dt;
            }
  }

  public double findMinColTime(){
      ensemble1.findMinColTime();
      ensemble2.findMinColTime();
      double mint1=ensemble1.mint;
      double mint2=ensemble2.mint;
      if (mint1<mint2) {
        nCollidingE=1;
        nCollidingW=ensemble1.nCollidingW;
        mint=mint1;
        temptime=mint;
        return mint1;
      }
      else {
        nCollidingE=2;
        nCollidingW=ensemble2.nCollidingW;
        mint=mint2;
        temptime=mint;
        return mint2;
      }

  }

  public void paintOSI(){
    ensemble1.paintOSI();
    ensemble2.paintOSI();
    if(osi==null) return;
    Graphics osg=osi.getGraphics();
    if(osg==null) return;
    osg.setColor(Color.black);
    osg.fillRect((int)(ensemble1.rwpos*ppu),0,(int)(wwidth*ppu),(int)(currenth*ppu));
    if(showTitle) paintCaption(osg);
    osg.dispose();
  }


  public void setDefault(){
      wVel=0;
      ensemble1.setDefault();
      ensemble2.setDefault();
      wallDataSource=null;
  }
  /*
  public void setBounds(){
    this.currentw = this.getSize().width;
    this.currenth = this.getSize().height;
    ensemble1.setRWPos(0.5*(currentw-2*bwidth)/((double)ppu));
    ensemble1.setLWPos(0);
    if (!ensemble1.periodicv) ensemble1.setTWPos((currenth-2*bwidth)/((double)ppu));
    else ensemble1.setTWPos((currenth)/((double)ppu));
    ensemble1.setBWPos(0);
    ensemble1.xOrigin=(ensemble1.rwpos+ensemble1.lwpos)/2;
    ensemble1.yOrigin=ensemble1.twpos/2;
    cwpos=ensemble1.rwpos;
    if (ensemble1.periodicv){
       ensemble1.setNeighbor(1,ensemble1,0,ensemble1.twpos);
       ensemble1.setNeighbor(5,ensemble1,0,-ensemble1.twpos);
      }
   ensemble1.setPeriodicH(false);
     ensemble2.setLWPos(cwpos+wwidth);
     ensemble2.setRWPos((currentw-bwidth)/((double)ppu));
     if (!ensemble2.periodicv) ensemble2.setTWPos((currenth-2*bwidth)/((double)ppu));
     else ensemble2.setTWPos((currenth)/((double)ppu));
     ensemble2.setBWPos(0);
     ensemble2.xOrigin=( ensemble2.rwpos+ ensemble2.lwpos)/2;
     ensemble2.yOrigin=ensemble2.twpos/2;
    if (ensemble2.periodicv){
       ensemble2.setNeighbor(1,ensemble2,0,ensemble2.twpos);
       ensemble2.setNeighbor(5,ensemble2,0,-ensemble2.twpos);
    }
    ensemble2.setPeriodicH(false);
    osi = createImage(currentw,currenth);
  }

  */
  public void setBounds(){
    this.currentw = this.getSize().width;
    this.currenth = this.getSize().height;
    ensemble1.setRWPos((ppos*currentw-wwidth*ppu/2.0)/((double)ppu));
    ensemble1.setLWPos(0);
    ensemble1.setTWPos(currenth/((double)ppu));
    ensemble1.setBWPos(0);
    ensemble1.xOrigin=(ensemble1.rwpos+ensemble1.lwpos)/2;
    ensemble1.yOrigin=ensemble1.twpos/2;
    cwpos=ensemble1.rwpos+wwidth/2;
    //cwpos=ensemble1.rwpos;
    ensemble1.setPeriodicV(false);
    if (ensemble1.periodicv){
       ensemble1.setNeighbor(1,ensemble1,0,ensemble1.twpos);
       ensemble1.setNeighbor(5,ensemble1,0,-ensemble1.twpos);
    }
   ensemble1.setPeriodicH(false);
    ensemble2.setLWPos(cwpos+wwidth/2);
    //ensemble2.setLWPos(cwpos+wwidth);
     ensemble2.setRWPos(currentw/((double)ppu));
     ensemble2.setTWPos(currenth/((double)ppu));
     ensemble2.setBWPos(0);
     ensemble2.xOrigin=( ensemble2.rwpos+ ensemble2.lwpos)/2;
     ensemble2.yOrigin=ensemble2.twpos/2;
     ensemble2.setPeriodicV(false);
    if (ensemble2.periodicv){
       ensemble2.setNeighbor(1,ensemble2,0,ensemble2.twpos);
       ensemble2.setNeighbor(5,ensemble2,0,-ensemble2.twpos);
    }
    ensemble2.setPeriodicH(false);

    //osi = createImage(currentw,currenth);
    makeImage();
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
    }else{g.drawImage(osi,0,0,currentw,currenth,this);}
    if(ensemble1.mouseDown) ensemble1.paintCoords(g);  // added by W. Christian
    if(ensemble2.mouseDown) ensemble2.paintCoords(g);  // added by W. Christian
  }

  public int getWallDataSource(){
    if(wallDataSource==null) wallDataSource= new WallDataSource();
    return wallDataSource.getID();
  }



  public LEnsemble getEnsemble1(){
    return ensemble1;
  }

  public REnsemble getEnsemble2(){
    return ensemble2;
  }

  public void setPistonWidth(double w){
    if (w>0) this.wwidth=w;
  }

  public void setPistonPosition(double pos){
     if ((pos>0)&& (pos<1)) this.ppos=pos;
     osi=null;
     if(ensemble1.autoRefresh){
        recalculateColTimes();
        owner.repaint();
    }
  }

  public void setPistonMass(double m){
    if (m>0) this.weight=m;
  }


  public TPEnsemblePanel() {
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
    for (int y=1; y<ensemble2.lastpartnum+1; y++)
        for (int x=0; x<y; x++){
              if (ensemble2.empty[x]==false && ensemble2.empty[y]==false)
                  ensemble2.colTimes[y][x]=ensemble2.calcColTime(y,x);
            }

    findMinColTime();
  }

    // inner class used for data connection to particles.
  public class WallDataSource extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"t","x","vx","m"};
    double[][] ds=new double[1][4];  // the datasource state variables t,x,y,vx,vy,ax,ay,p;

    WallDataSource(){ // Constructor
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
      ds[0][0]=owner.getClockTime();  //t
      //ds[0][1]=ensemble1.rwpos+wwidth/2;  //x
      ds[0][1]=cwpos-(currentw/2)/((double)ppu);
      ds[0][2]=wVel;  //vx
      ds[0][3]=weight;  //m
      return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet owner){;}
    public SApplet getOwner(){return owner;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  }


}
