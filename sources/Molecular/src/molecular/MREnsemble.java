
package molecular;

import java.awt.Color;
import java.awt.Graphics;


public class MREnsemble extends Ensemble {
  //int lwidth;
  //int lheight;

  MEnsemblePanel towner= null;

  public MREnsemble(EnsemblePanel o) {
    super(o);
    towner=(MEnsemblePanel)o;
    dcolor= new Color(150,0,0);
  }

  public void setBounds(){
    towner.setBounds();
  }

  /*public void setBounds(){
    towner.currentw = towner.getSize().width;
    towner.currenth = towner.getSize().height;
    lwidth= (int)(0.5*towner.getSize().width);
    lheight= (int)(towner.getSize().height);
    setLWPos(towner.cwpos);
    setRWPos(towner.currentw/((double)ppu));
    setTWPos(towner.currenth/((double)ppu));
    setBWPos(0);
    xOrigin=(rwpos+lwpos)/2;
    yOrigin=twpos/2;
    if (periodicv){
       setNeighbor(1,this,0,twpos);
       setNeighbor(5,this,0,-twpos);
      }
    setPeriodicH(false);
    towner.osi = towner.createImage(towner.currentw,towner.currenth);
  }       */

  /**
  *
  * Calculates Time until ith particle collides with left wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeLW(int i){
    if (empty[i]) {System.out.println("calcColTimeLW empty particle:"+""+i);return 10000;}
    if (xVel[i]<0) {
      if (xPos[i]>lwpos) return Math.abs((lwpos-xPos[i])/xVel[i]);
    }
    return 10000;
  }


  public void findMinColTime(){
    double min = 10000;
    double t=0;
    int i,j;
    nCollidingW=0;                    //next colliding wall
    if (empty[0]==false){
        if (twColTimes[0]<min){
        min=twColTimes[0];
        nColliding1=0;               //nColliding1 is next colliding particle that will collide with wall or nColliding2
        nCollidingW=1;               //next colliding wall: 1=top wall
        }
        if (rwColTimes[0]<min){
        min=rwColTimes[0];
        nColliding1=0;
        nCollidingW=2;               //next colliding wall: 2=right wall
        }
        if (bwColTimes[0]<min){
        min=bwColTimes[0];
        nColliding1=0;
        nCollidingW=3;                   //next colliding wall: 3=bottom wall
        }
        if (lwColTimes[0]<min){
        min=lwColTimes[0];
        nColliding1=0;
        nCollidingW=4;                    //next colliding wall: 4=left wall
        }
    }
    for (i=1; i<lastpartnum+1; i++)
      if (empty[i]==false){
        if (twColTimes[i]<min){
        min=twColTimes[i];
        nColliding1=i;
        nCollidingW=1;
        if (min<=0) {System.out.println("tWColTime <= 0: Right"); pause();}
        }
        if (rwColTimes[i]<min){
        min=rwColTimes[i];
        nColliding1=i;
        nCollidingW=2;
        if (min<=0) {System.out.println("rWColTime <= 0: Right"); pause();}
        }
        if (bwColTimes[i]<min){
        min=bwColTimes[i];
        nColliding1=i;
        nCollidingW=3;
        if (min<=0) {System.out.println("bWColTime <= 0: Right"); pause();}
        }
        if (lwColTimes[i]<min){
        min=lwColTimes[i];
        nColliding1=i;
        nCollidingW=4;
        if (min<=0) {System.out.println("LWColTime <= 0: Right"); pause();}
        }

        for (int x=0; x<i; x++)
         if (colTimes[i][x]<min){
           min=colTimes[i][x];
           nCollidingW=0;                     //next colliding wall=0 if next collision is a particle collision
           nColliding1=i;                     //nColliding2  and  nColliding2  are next colliding particles
           nColliding2=x;
           //if (min<=0) {System.out.println("PartColTime <= 0: Right"); pause();}
           if (min<=0) {min=1.0E-8;}
         }
      }

    if (min<0) {System.out.println("negative Collide Time: Right"); pause(); min=10000;}
    if (min==0) {System.out.println("Collide Time = 0: Right"); pause(); min= 10000;}
    mint=min;
    temptime=mint;
  }


  /**
  *
  * Collides particle with left wall
  *
  * @param i int particle number
  *
  */
  public void collideLW(int i){
    if (empty[i]) return;
    double prob=0;
    pl+=Math.abs(2*xVel[i]*mass[i]/(twpos-bwpos));
    double v=Math.sqrt(xVel[i]*xVel[i]+yVel[i]*yVel[i]);
    prob=towner.pparser.evaluate(mass[i],v,applet.clock.getTime());
    if (prob>Math.random()){
      double yin=yPos[i]-towner.ensemble1.yOrigin;
      double xvel=xVel[i];
      double yvel=yVel[i];
      double r=rad[i];
      double xpos=towner.ensemble2.rwpos-0.0001-towner.ensemble2.xOrigin;
      towner.ensemble1.setDefaultColor(colors[i].getRed(),colors[i].getGreen(),colors[i].getBlue());
      towner.ensemble1.setDefaultMass(mass[i]);
      int j=towner.ensemble1.addParticle(xpos,yin,xvel,yvel,r);
      removeParticle(i);
      towner.swapDataSourcesRL(i,j);
    }
    else  {
    xVel[i]=-xVel[i];
    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    rwColTimes[i]=calcColTimeRW(i);
    twColTimes[i]=calcColTimeTW(i);
    for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
    }
  }



  /**
  *
  * Paints off-screen image on osi, global Image object.
  *
  *
  */
  public synchronized void paintOSI(){
    //System.out.println("rpainting");
    int i;
    int w=(int)((rwpos-lwpos)*ppu);
    //int offsetw=towner.currentw-w;
    int offsetw = (int)(lwpos*ppu);
    int h=(int)((twpos-bwpos)*ppu);
    double r;
    if (towner.osi==null){
        //System.out.println("OSI is null");
    }
    //else if ((currentw != owner.getSize().width)||(currenth!= owner.getSize().height)) {pause();setBounds();}
    //if (change) {pause(); setup();}
    else{
      //System.out.println("rpainting2");
      Graphics osg = towner.osi.getGraphics();
      if(osg==null) return;
      //osg.setColor(bgColor);
      //osg.fillRect(offsetw,0,w,lheight);
      int diameter=1;
      for (i=0; i<lastpartnum+1; i++){
        if (empty[i]==false){
          //osg.setColor(colors[i]);
          osg.setColor(colors[i]);
          r=rad[i];
          diameter=Math.max(2,(int)(2*r*ppu));   //added by W. Christian to make sure we have a mimimum size  of 2 pixels.
          osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          if ((xPos[i]+r)>rwpos) osg.fillOval((int)(ppu*(xPos[i]-r-rwpos)),h-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          if ((yPos[i]+r)>twpos) osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r-twpos)),diameter,diameter);
          if ((yPos[i]-r)<bwpos) osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r+twpos)),diameter,diameter);
          if ((xPos[i]-r)<lwpos) osg.fillOval((int)(ppu*(xPos[i]-r+rwpos)),h-(int)(ppu*(yPos[i]+r)),diameter,diameter);
        }
      }
      if (therms[0]){}
      if (therms[1]){}
      if (therms[2]){}
      if (therms[3]){}
      if (showMessage) paintMessage(osg);
      osg.dispose();
      }
  }

  public void setAutoRefresh(boolean ar){
    this.autoRefresh=ar;
    if (ar) {
      boolean runAgain=false;
      if (applet.clock.isRunning()) {runAgain=true; pause();}
      recalculateColTimes();
      paintOSI();
      towner.repaint();
      if (runAgain) forward();
    }
  }

  public void recalculateColTimes(){
    towner.recalculateColTimes();
  }


}
