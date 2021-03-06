

package molecular;

import java.awt.Graphics;

public class MFLEnsemble extends MFEnsemble {
  //int lwidth;
  double oLProb = 0;
  MFEnsemblePanel owner= null;

  public MFLEnsemble(MFEnsemblePanel o) {
    super(o);
    owner=(MFEnsemblePanel)o;
  }

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
      if (neighbor[7]==null) if (xPos[i]-rad[i]>lwpos) return Math.max(0,(lwpos+rad[i]-xPos[i])/xVel[i]);
      else if (xPos[i]>lwpos) return Math.max(0,(lwpos-xPos[i])/xVel[i]);
    }
     return 10000;
  }


   /**
  *
  * Calculates Time until ith particle collides with right wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeRW(int i){
    if (empty[i]) {System.out.println("Left: calcColTimeRW empty particle:"+""+i);return 10000;}
    if ((xVel[i]>0) && (xPos[i]<rwpos)) return Math.abs((rwpos-xPos[i])/xVel[i]);
    return 10000;
  }



  public void findMinColTime(){
    if(empty==null) return;
    double dt=applet.clock.getDt();
    double min = 10000;
    double t=0;
    int i,j;
    nCollidingW=0;                    //next colliding wall
    if (empty[0]==false){
        if (twColTimes[0]<min){
        min=twColTimes[0];
        nColliding1=0;               //nColliding1 is next colliding particle that will collide with wall or nColliding2
        nCollidingW=1;
        if (min<=0) {System.out.println("Left: tWColTime <= 0"); pause();}               //next colliding wall: 1=top wall
        }
        if (rwColTimes[0]<min){
        min=rwColTimes[0];
        nColliding1=0;
        nCollidingW=2;
        if (min<=0) {System.out.println("Left: rWColTime <= 0"); pause();}               //next colliding wall: 2=right wall
        }
        if (bwColTimes[0]<min){
        min=bwColTimes[0];
        nColliding1=0;
        nCollidingW=3;
        if (min<=0) {System.out.println("Left: bWColTime <= 0"); pause();}                   //next colliding wall: 3=bottom wall
        }
        if (lwColTimes[0]<min){
        min=lwColTimes[0];
        nColliding1=0;
        nCollidingW=4;
        if (min<=0) {System.out.println("Left: LWColTime <= 0"); pause();}                    //next colliding wall: 4=left wall
        }
    }

    for (i=1; i<lastpartnum+1; i++)
      if (empty[i]==false){
        if (twColTimes[i]<min){
        min=twColTimes[i];
        nColliding1=i;
        nCollidingW=1;
        if (min<=0) {System.out.println("Left: tWColTime <= 0"); pause();}
        }
        if (rwColTimes[i]<min){
        min=rwColTimes[i];
        nColliding1=i;
        nCollidingW=2;
        if (min<=0) {System.out.println("Left: rWColTime <= 0"); pause();}
        }
        if (bwColTimes[i]<min){
        min=bwColTimes[i];
        nColliding1=i;
        nCollidingW=3;
        if (min<=0) {System.out.println("Left: bWColTime <= 0"); pause();}
        }
        if (lwColTimes[i]<min){
        min=lwColTimes[i];
        nColliding1=i;
        nCollidingW=4;
        if (min<=0) {System.out.println("Left: LWColTime <= 0"); pause();}
        }

        if (owner.partInt)
        for (int x=0; x<i; x++){

         if (colTimes[i][x]<min){
           min=colTimes[i][x];
           nCollidingW=0;                     //next colliding wall=0 if next collision is a particle collision
           nColliding1=i;                     //nColliding2  and  nColliding2  are next colliding particles
           nColliding2=x;
           //if (min<=0) {System.out.println("Left: PartColTime <= 0"); pause();}
           if (min<=0) {min=1.0E-8;}
         }
        }
      }


    if (min<0) {System.out.println("Left: negative Collide Time"); pause(); min=10000;}
    if (min==0) {System.out.println("Left: Collide Time = 0"); pause(); min= 10000;}
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
    if (empty[i]) {System.out.println("Left: empty particle colliding");return;}
    //pl+=Math.abs(2*xVel[i]*mass[i]/(twpos-bwpos));      //changed by W. Christian
    pl+=Math.abs(xVel[i]*mass[i]/(twpos-bwpos));
    if (owner.flows){
       if (oLProb>Math.random()) {this.oflowPart(i);return;}
    }
    if (neighbor[7]!=null) {xPos[i]=rwpos;
    }else {
       if(therms[3]) thermalize(i,3);
       else xVel[i]=-xVel[i];

    }
    pl+=Math.abs(-xVel[i]*mass[i]/(twpos-bwpos));
    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    rwColTimes[i]=calcColTimeRW(i);
    twColTimes[i]=calcColTimeTW(i);
    if (owner.partInt){
      for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
      for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
    }
  }



  /**
  *
  * Collides particle with left wall
  *
  * @param i int particle number
  *
  */

  public void collideRW(int i){
    if (empty[i]) {System.out.println("Left: empty particle colliding");return;}
    if ((yPos[i]>owner.ensemble3.bwpos)&&(yPos[i]<owner.ensemble3.twpos)&&
        (2*rad[i]<owner.ensemble3.twpos-owner.ensemble3.bwpos-0.002)){
      double xvel=xVel[i];
      double yvel=yVel[i];
      double r=rad[i];
      yPos[i]=-r+Math.min(yPos[i]+r,owner.ensemble3.twpos-0.001);
      yPos[i]=r+Math.max(yPos[i]-r,owner.ensemble3.bwpos+0.001);
      double yin=yPos[i]-owner.ensemble3.yOrigin;
      double xpos=owner.ensemble3.lwpos-owner.ensemble3.xOrigin+0.0001;
      owner.ensemble3.setDefaultColor(colors[i].getRed(),colors[i].getGreen(),colors[i].getBlue());
      owner.ensemble3.setDefaultMass(mass[i]);
      owner.ensemble3.addParticle(xpos,yin,xvel,yvel,r);
      removeParticle(i);
    }
    else  {
    xVel[i]=-xVel[i];
    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    rwColTimes[i]=calcColTimeRW(i);
    twColTimes[i]=calcColTimeTW(i);
    if (owner.partInt){
      for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
      for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
    }
    }
  }

   public void setBounds(){
    owner.setBounds();
  }
  /*
  public void setBounds(){
    towner.currentw = towner.getSize().width;
    towner.currenth = towner.getSize().height;
    lwidth= (int)(0.5*towner.getSize().width);
    lheight= (int)(towner.getSize().height);
    setRWPos(0.5*towner.currentw/((double)ppu));
    setLWPos(0);
    setTWPos(towner.currenth/((double)ppu));
    setBWPos(0);
    xOrigin=(rwpos+lwpos)/2;
    yOrigin=twpos/2;
    towner.cwpos=rwpos;
    if (periodicv){
       setNeighbor(1,this,0,twpos);
       setNeighbor(5,this,0,-twpos);
      }
    setPeriodicH(false);
    towner.osi = towner.createImage(towner.currentw,owner.currenth);
  }*/



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
    //int offsetw=owner.currentw-w;
    int offsetw = (int)(lwpos*ppu);
    int h=(int)((twpos-bwpos)*ppu);

    double r;
    if (owner.osi==null){
        //System.out.println("OSI is null");
    }
    //else if ((currentw != owner.getSize().width)||(currenth!= owner.getSize().height)) {pause();setBounds();}
    //if (change) {pause(); setup();}
    else{
      //System.out.println("rpainting2");
      Graphics osg = owner.osi.getGraphics();
      if(osg==null) return;
      osg.setColor(bgColor);
      osg.fillRect(0,0,w,h);
      int diameter=1;
      for (i=0; i<lastpartnum+1; i++){
        if (!empty[i]){
          osg.setColor(colors[i]);
          r=rad[i];
          diameter=Math.max(2,(int)(2*r*ppu));   //added by W. Christian to make sure we have a mimimum size  of 2 pixels.
          osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          if (periodicv){
            if ((yPos[i]+r)>twpos) osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r-twpos)),diameter,diameter);
            if ((yPos[i]-r)<bwpos) osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r+twpos)),diameter,diameter);
          }
        }
      }
      if (therms[0]){}
      if (therms[1]){}
      if (therms[2]){}
      if (therms[3]){}

      //checks for overlapping particles
      for (int j=0; j<owner.ensemble3.lastpartnum+1; j++){
        if (!owner.ensemble3.empty[j]){
          r=owner.ensemble3.rad[j];
          if (owner.ensemble3.xPos[j]-r<rwpos){
              diameter=Math.max(2,(int)(2*r*ppu));
              osg.setColor(owner.ensemble3.colors[j]);
              osg.fillOval((int)(ppu*(owner.ensemble3.xPos[j]-r)),h-(int)(ppu*(owner.ensemble3.yPos[j]+r)),diameter,diameter);
          }
        }

      }

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
      owner.repaint();
      if (runAgain) forward();
    }
  }
  public void recalculateColTimes(){
  owner.recalculateColTimes();
  }


}
