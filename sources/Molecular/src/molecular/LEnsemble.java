

package molecular;

import java.awt.Graphics;

public class LEnsemble extends Ensemble {

  TPEnsemblePanel towner= null;

  public LEnsemble(EnsemblePanel o) {
    super(o);
    towner=(TPEnsemblePanel)o;
  }

  /**
  *
  * Calculates Time until ith particle collides with right wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeRW(int i){
    if (empty[i]) return 10000;
    double ct=10000;
    double vp=xVel[i];
    double vw=towner.wVel;
    if(vp<vw && vp<0) return 10000;
    if(vw>vp && vw>0) return 10000;
      else{
       if (neighbor[3]==null) ct= Math.abs((rwpos-rad[i]-xPos[i])/(vp-vw));
       else if (xPos[i]<rwpos) ct=Math.abs((rwpos-xPos[i])/(vp-vw));

      }
    if (ct<=0)   System.out.println("Error in calcColTimeLW");
    return ct;
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
        if (min<=0) {System.out.println("twColTime <= 0: Left"); pause();}
        }
        if (rwColTimes[i]<min){
        min=rwColTimes[i];
        nColliding1=i;
        nCollidingW=2;
        if (min<=0) {System.out.println("rwColTime <= 0: Left"); pause();}
        }
        if (bwColTimes[i]<min){
        min=bwColTimes[i];
        nColliding1=i;
        nCollidingW=3;
        if (min<=0) {System.out.println("bwColTime <= 0: Left"); pause();}
        }
        if (lwColTimes[i]<min){
        min=lwColTimes[i];
        nColliding1=i;
        nCollidingW=4;
        if (min<=0) {System.out.println("lwColTime <= 0: Left"); pause();}
        }

        for (int x=0; x<i; x++)
         if (colTimes[i][x]<min){
           min=colTimes[i][x];
           nCollidingW=0;                     //next colliding wall=0 if next collision is a particle collision
           nColliding1=i;                     //nColliding2  and  nColliding2  are next colliding particles
           nColliding2=x;
         if (min<=0) {System.out.println("PartColTime <= 0: Left"); pause();}
         }
      }
    double dist=rwpos-xPos[nColliding1];
    if (min<0) {System.out.println("negative Collide Time: Left"+dist); pause(); min= 10000;}
    if (min==0) {System.out.println("Collide Time = 0: Left"+dist); pause(); min= 10000;}
    mint=min;
    temptime=mint;
  }


  /**
  *
  * Collides particle with right wall
  *
  * @param i int particle number
  *
  */
  public void collideRW(int i){
     double a,b,c,d,v1n,v2n,v1,v2,m1,m2;
    //pr+=Math.abs(2*xVel[i]*mass[i]/(twpos-bwpos));
    if (neighbor[3]!=null) {xPos[i]=lwpos;
    }
    else {
     m1=towner.weight;
     v1=towner.wVel;
     m2=mass[i];
     v2=xVel[i];
     double pin = m1*v1+m2*v2;
     double kin=m1*v1*v1+m2*v2*v2;
     a= m2+(m2*m2)/m1;
     b=-2*pin*m2/m1;
     c=((pin*pin)/m1)-(m1*v1*v1+m2*v2*v2);
     d=b*b-4*a*c;
     if (d<0) return;
     v1n=(-b+Math.sqrt(d))/(2*a);
     v2n=(-b-Math.sqrt(d))/(2*a);
     v2n=Math.min(v1n,v2n);
     v1n=(pin-m2*v2n)/m1;
     towner.wVel=v1n;
     xVel[i]=v2n;
     pr+=Math.abs((v2n-v2)*mass[i]/(twpos-bwpos));
    }

    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    twColTimes[i]=calcColTimeTW(i);
    towner.recalcLWColTimes();
    for (int q=0; q<lastpartnum+1; q++) if (!empty[q]) rwColTimes[q]=calcColTimeRW(q);
    for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
  }

   public void setBounds(){
    towner.setBounds();
  }

  /*public void setBounds(){
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
    int i;
    int w=(int)((rwpos-lwpos)*ppu);
    int h=(int)((twpos-bwpos)*ppu);
    double r;
    if (towner.osi==null){
        //System.out.println("OSI is null");
    }
    //else if ((currentw != owner.getSize().width)||(currenth!= owner.getSize().height)) {pause();setBounds();}
    //if (change) {pause(); setup();}
    else{
      Graphics osg = towner.osi.getGraphics();
      if(osg==null) return;
      osg.setColor(bgColor);
      osg.fillRect(0,0,w,h);
      int diameter;
      for (i=0; i<lastpartnum+1; i++){
        if (empty[i]==false){
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
      /*
      if (therms[0]){
          osg.setColor(Color.red);
          for ( i=bwidth+1; i<w-bwidth-1; i+=6) osg.drawLine(i,bwidth,i,bwidth+5);
          osg.setColor(Color.blue);
          for ( i=bwidth+3; i<w-bwidth-1; i+=6) osg.drawLine(i,bwidth,i,bwidth+5);
          }
      if (therms[2]){
          osg.setColor(Color.red);
          for ( i=bwidth+1; i<w-bwidth-1; i+=6) osg.drawLine(i,h-bwidth-5,i,h-bwidth);
          osg.setColor(Color.blue);
          for ( i=bwidth+3; i<w-bwidth-1; i+=6) osg.drawLine(i,h-bwidth-5,i,h-bwidth);
      }
      if (therms[3]){
          osg.setColor(Color.red);
          for ( i=bwidth+1; i<h-bwidth-1; i+=6) osg.drawLine(bwidth,i,bwidth+5,i);
          osg.setColor(Color.blue);
          for ( i=bwidth+3; i<h-bwidth-1; i+=6) osg.drawLine(bwidth,i,bwidth+5,i);
      }
      */
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
    towner.recalculateColTimes();
  }


}