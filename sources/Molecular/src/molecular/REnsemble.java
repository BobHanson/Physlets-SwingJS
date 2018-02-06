

package molecular;

import java.awt.*;

public class REnsemble extends Ensemble {

  TPEnsemblePanel towner= null;

  public REnsemble(EnsemblePanel o) {
    super(o);
    towner=(TPEnsemblePanel)o;
  }

  /*public void setBounds(){
    towner.currentw = towner.getSize().width;
    towner.currenth = towner.getSize().height;
    lwidth= (int)(0.5*towner.getSize().width);
    lheight= (int)(towner.getSize().height);
    setLWPos(towner.cwpos+towner.wwidth);
    double c=towner.currentw/((double)ppu);
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
  } */

  public void setBounds(){
    towner.setBounds();
  }

   /**
  *
  * Calculates Time until ith particle collides with left wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeLW(int i){
    if (empty[i]) return 10000;
    double ct=10000;
    double vp=xVel[i];
    double vw=towner.wVel;
    if(vp>vw && vp>0) return 10000;
    if(vw<vp && vw<0) return 10000;
      else{
        if (neighbor[7]==null) ct= Math.abs((lwpos+rad[i]-xPos[i])/(vp-vw));
       else if (xPos[i]>lwpos) ct=Math.abs((lwpos-xPos[i])/(vp-vw));

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
           if (min<=0) {System.out.println("PartColTime <= 0: Right"); pause();}
         }
      }

    if (min<0) {System.out.println("negative Collide Time: Right"); pause(); min= 10000;}
    if (min==0) {System.out.println("Collide Time = 0: Right"); pause(); min= 10000;}
    mint= min;
    temptime=mint;
  }



  public void collideLW(int i){
    if (empty[i]) return;
     double a,b,c,d,v1n,v2n,v1,v2,m1,m2;
   // pl+=Math.abs(2*xVel[i]*mass[i]/(twpos-bwpos));
    if (neighbor[7]!=null) {xPos[i]=rwpos;
    }
    else {
      m1=towner.weight;
      v1=towner.wVel;
      m2=mass[i];
      v2=xVel[i];
     double pin = m1*v1+m2*v2;
     a= m2+(m2*m2)/m1;
     b=-2*pin*m2/m1;
     c=((pin*pin)/m1)-(m1*v1*v1+m2*v2*v2);
     d=b*b-4*a*c;
     if (d<0) return;
     v1n=(-b+Math.sqrt(d))/(2*a);
     v2n=(-b-Math.sqrt(d))/(2*a);
     v2n=Math.max(v1n,v2n);
     v1n=(pin-m2*v2n)/m1;
     towner.wVel=v1n;  //Math.max(v1n,v2n);
     xVel[i]=v2n;  //Math.min(v1n,v2n);
     pl+=Math.abs((v2n-v2)*mass[i]/(twpos-bwpos));
    }

    bwColTimes[i]=calcColTimeBW(i);
    rwColTimes[i]=calcColTimeRW(i);
    twColTimes[i]=calcColTimeTW(i);
    towner.recalcRWColTimes();
    for (int q=0; q<lastpartnum+1; q++) if (!empty[q]) lwColTimes[q]=calcColTimeLW(q);
    for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
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
      osg.setColor(bgColor);
      osg.fillRect(offsetw,0,w,h);
      int diameter;
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
      owner.repaint();
      if (runAgain) forward();
    }
  }

  public void recalculateColTimes(){
    towner.recalculateColTimes();
  }
}