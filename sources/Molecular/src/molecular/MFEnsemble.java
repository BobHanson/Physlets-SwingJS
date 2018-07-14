/*
**************************************************************************
**
**                      Class  MFEnsemble
**
**************************************************************************
**
** class MFEnsemble extends  Ensemble
**
** @author Jim Nolen and Wolfgang Christian
**
*************************************************************************/

package molecular;

import java.awt.Color;
import java.awt.Graphics;

import edu.davidson.tools.*;


public class MFEnsemble extends Ensemble {
  MFEnsemble neighbor[] = new MFEnsemble[8];
  MFEnsemblePanel owner = null;
  int midHeight=10;
  double top,bottom;  //top and bottom of two outer ensembles in units

  //constructor methods.  Sets Applet owner.

  public MFEnsemble() {   // the basic constructor
  }

  public MFEnsemble(MFEnsemblePanel o){
    super(o); // added by W. Christian to call basic constructor.
    owner = o;
    applet=owner.owner;
    try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
  }



  public void setBounds(){
    owner.setBounds();
  }

  /*
  public void setBounds(){
    owner.currentw = owner.getSize().width;
    owner.currenth = owner.getSize().height;
    if (!periodich) setRWPos((owner.currentw-2*bwidth)/((double)ppu));
    else setRWPos((owner.currentw)/((double)ppu));
    //setRWPos((owner.currentw-2*bwidth)/((double)ppu));
    setLWPos(0);
    if (!periodicv) setTWPos((owner.currenth-2*bwidth)/((double)ppu));
    else setTWPos((owner.currenth)/((double)ppu));
    //setTWPos((owner.currenth-2*bwidth)/((double)ppu));
    setBWPos(0);
    xOrigin=(rwpos+lwpos)/2;
    yOrigin=(twpos+bwpos)/2;
    if (periodicv){
       setNeighbor(1,this,0,twpos);
       setNeighbor(5,this,0,-twpos);
      }
    if (periodich){
       setNeighbor(3,this,rwpos,0);
       setNeighbor(7,this,-rwpos,0);
      }
    if (periodich || periodicv){
          setNeighbor(0,this,-rwpos,twpos);
          setNeighbor(2,this,rwpos,twpos);
          setNeighbor(4,this,rwpos,-twpos);
          setNeighbor(6,this,-rwpos,-twpos);
      }

    owner.makeImage();
  }
  */


   /**
  *
  * Calculates Time until ith particle collides with left wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeLW(int i){
    if (empty[i]) {System.out.println("Middle: calcColTimeLW empty particle:"+""+i);return 10000;}
    if ((xVel[i]<0)&&(xPos[i]>lwpos)) {
         return Math.max(0,(lwpos-xPos[i])/xVel[i]);
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
    if (empty[i]) {System.out.println("Middle: calcColTimeRW empty particle:"+""+i);return 10000;}
    if ((xVel[i]>0)&&(xPos[i]<rwpos)){
       return Math.max(0,(rwpos-xPos[i])/xVel[i]);
    }
    return 10000;
  }




   /**
  *
  * Calculates collision time between any two particles
  *
  * @param i int 1st particle number.
  * @param j int 2nd particle number.
  * @returns double collision time
  */
  public double calcColTime(int i, int j){
      if (!owner.partInt) return 10000;
      double ct=10000;
      //if ( empty[i] || empty[j] ) return 10000;
    if((!empty[i])&&(!empty[j])){
      double t1,t2;
      
      int p1=Math.max(i,j);    //particle 1.
      int p2=Math.min(i,j);    //particle 2.
      double v12x,v12y,r12x,r12y,vrdot,sigs,v12mags,r12mags;
      double d;
      v12x=xVel[p1]-xVel[p2];
      v12y=yVel[p1]-yVel[p2];
      r12x=xPos[p1]-xPos[p2];
      r12y=yPos[p1]-yPos[p2];
      if (Math.sqrt(r12y*r12y+r12x*r12x)<rad[p1]+rad[p2]-0.001) return 10000;
      vrdot=(v12x*r12x)+(v12y*r12y);
      if (vrdot>=0) ct=10000;        //no collision
      else {
        sigs=(rad[p1]+rad[p2])*(rad[p1]+rad[p2]);
        r12mags=r12x*r12x+r12y*r12y;
        v12mags=v12x*v12x+v12y*v12y;
        d=(vrdot*vrdot)-(v12mags)*(r12mags-sigs);
        if (d<0) ct=10000;          //no collision
        else{
          if (r12mags<(rad[p1]+rad[p2])*(rad[p1]+rad[p2])) return 10000;
          //t1=(-vrdot+Math.sqrt(d))/(v12mags);   //now they will collide
          t2=(-vrdot-Math.sqrt(d))/(v12mags);
          //ct= Math.min(t1,t2);
          ct=Math.max(0,t2);
          return ct;
        }
      }

    }

    //ct=Math.min(ct,calcImageCollisionTime(i,j));
    return ct;
    //return calcColTimeIm(i,j,neighbor[0],0,0);
   }

  /**
  *
  * Retrieves a collision time from a particle with an image particle
  *
  * @param p1 int 1st particle number.
  * @param p2 int 2nd particle number.
  * @returns double collision time
  */
  public double calcImageCollisionTime(int p1,int p2){
        double ct=10000;
        if (empty[p1] || empty[p2]) return 10000;
        MFEnsemble en=null;
          for (int q=0; q<8; q++){
             en=neighbor[q];
            if(en!=null && en==this) ct=Math.min(ct,calcColTimeIm(p1,p2,q, xOff[q],yOff[q]));
          }
      return Math.max(0,ct);
  }



  public double calcImageColTimes(int rpnum, int q){
      if (empty[rpnum]) return 10000;
      MFEnsemble nb = neighbor[q];
      double ct=10000;
      if (nb!=null)
      for (int i=0; i<nb.lastpartnum+1; i++)
         if (!nb.empty[i]){
             ct=Math.min(ct,calcColTimeIm(rpnum,i,q,0,0));
         }
      return ct;

  }


  /**
  *
  * Calculates collision time between a particle and a particle on a neighboring ensemble (ie image particle).
  *
  * @param p1 int 1st particle number-- real particle.
  * @param im int 2nd particle number-- image particle.
  * @param nnum int neighbor number
  * @param xo double xoffset of this neighboring ensemble
  * @param yo double yoffset of this neighboring ensemble
  * @returns double collision time
  */
  public double calcColTimeIm(int p1, int im, int nnum, double xo,double yo){

      MFEnsemble nb=neighbor[nnum];
      double ct,t1,t2,v12x,v12y,r12x,r12y,vrdot,sigs,v12mags,r12mags;
      double xp=nb.xPos[im]+xo;
      double yp=nb.yPos[im]+yo;
      double xv=nb.xVel[im];
      double yv=nb.yVel[im];
      double r=nb.rad[im];
      double d;
      v12x=xVel[p1]-xv;
      v12y=yVel[p1]-yv;
      r12x=xPos[p1]-xp;
      r12y=yPos[p1]-yp;
      vrdot=(v12x*r12x)+(v12y*r12y);
      if (vrdot>=0) ct=10000;        //no collision
      else {
        sigs=(rad[p1]+r)*(rad[p1]+r);
        r12mags=r12x*r12x+r12y*r12y;
        v12mags=v12x*v12x+v12y*v12y;
        d=(vrdot*vrdot)-(v12mags)*(r12mags-sigs);
        if (d<0) ct=10000;
        else{
          //t1=(-vrdot+Math.sqrt(d))/(v12mags);   //now they will collide
          t2=(-vrdot-Math.sqrt(d))/(v12mags);
          //ct= Math.min(t1,t2);
          ct=t2;
        }
      }
   return  Math.max(0,ct);
  }


  /*
  public void collideImages(int j, int nnum, int i){
    int p1=j;    //particle 1.
    int p2=i;    //particle 2.
    DEnsemble en= neighbor[nnum];
    double xpos2=en.xPos[p2]+xOff[nnum];
    double ypos2=en.yPos[p2]+yOff[nnum];
    double xvel2=en.xVel[p2];
    double yvel2=en.yVel[p2];
    //if (image>j){}
    double prod1,r12x,r12y,v12x,v12y,norm2;
    v12x=xVel[p1]-xvel2;
    v12y=yVel[p1]-yvel2;

    r12x=xpos2-xPos[p1];
    r12y=ypos2-yPos[p1];
    norm2=r12x*r12x+r12y*r12y;
    prod1=v12x*r12x+v12y*r12y;

    xVel[p1]=xVel[p1]-prod1*r12x/norm2;
    yVel[p1]=yVel[p1]-prod1*r12y/norm2;
    en.xVel[p2]=en.xVel[p2]+prod1*r12x/norm2;
    en.yVel[p2]=en.yVel[p2]+prod1*r12y/norm2;

    for (int q=0; q<p1; q++) colTimes[p1][q]=calcColTime(p1,q);
    for (int q=0; q<p2; q++) colTimes[p2][q]=calcColTime(p2,q);
    for (int p=p2+1; p<lastpartnum+1; p++) colTimes[p][p2]=calcColTime(p,p2);
    for (int p=p1+1; p<lastpartnum+1; p++) colTimes[p][p1]=calcColTime(p,p1);

    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    rwColTimes[i]=calcColTimeRW(i);
    twColTimes[i]=calcColTimeTW(i);
    bwColTimes[j]=calcColTimeBW(j);
    lwColTimes[j]=calcColTimeLW(j);
    rwColTimes[j]=calcColTimeRW(j);
    twColTimes[j]=calcColTimeTW(j);
  }
  */

  /**
  *
  * Collides two particles
  *
  * @param i int 1st particle
  * @param j int 2nd particle
  *
  */
  public void collideParticles(int i, int j){
    if (!owner.partInt) return;
    int p1=Math.max(i,j);    //particle 1.
    int p2=Math.min(i,j);    //particle 2.
    //check for overlapping particles
    if (empty[p1] || empty[p2]) return;
    if(mass[p1]!=mass[p2]) collideDiffMassParticles(p1,p2);
    else {                                 //rest of algorithm assumes equal masses
      double prod1,r12x,r12y,v12x,v12y,norm2;
      double sig=rad[i]+rad[j];
      v12x=xVel[p1]-xVel[p2];
      v12y=yVel[p1]-yVel[p2];
                                       //r12=r2-r1    v12= v1-v2
      r12x=xPos[p2]-xPos[p1];
      r12y=yPos[p2]-yPos[p1];
      if (r12x>sig) r12x-=(rwpos-lwpos);
      if (r12y>sig) r12y-=twpos;
      if (r12x<-sig) r12x+=(rwpos-lwpos);
      if (r12y<-sig) r12y+=twpos;
      norm2=r12x*r12x+r12y*r12y;
      prod1=v12x*r12x+v12y*r12y;
      xVel[p1]=xVel[p1]-prod1*r12x/norm2;
      yVel[p1]=yVel[p1]-prod1*r12y/norm2;
      xVel[p2]=xVel[p2]+prod1*r12x/norm2;
      yVel[p2]=yVel[p2]+prod1*r12y/norm2;
    }

    for (int q=0; q<p1; q++) colTimes[p1][q]=calcColTime(p1,q);
    for (int q=0; q<p2; q++) colTimes[p2][q]=calcColTime(p2,q);
    for (int p=p2+1; p<lastpartnum+1; p++) colTimes[p][p2]=calcColTime(p,p2);
    for (int p=p1+1; p<lastpartnum+1; p++) colTimes[p][p1]=calcColTime(p,p1);

    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    rwColTimes[i]=calcColTimeRW(i);
    twColTimes[i]=calcColTimeTW(i);
    bwColTimes[j]=calcColTimeBW(j);
    lwColTimes[j]=calcColTimeLW(j);
    rwColTimes[j]=calcColTimeRW(j);
    twColTimes[j]=calcColTimeTW(j);

  }
  
  /**
  *
  * Collides two particles of different mass. Algorithm translates into
  * center of mass reference frame.
  *
  * @param i int 1st particle number
  * @param j int 2nd particle number
  *
  */
  public void collideDiffMassParticles(int i, int j){
    if (!owner.partInt) return;
    double sig,x1,x2,m1,m2,r12x,r12y,v1x,v2x,v1y,v2y,vcx,vcy,n1x,n1y,n2x,n2y,d1,d2;
    sig=rad[i]+rad[j];
    m1=mass[i];               //m1=2   <-  -2
    m2=mass[j];               //m2=1   ->  2
    x1=xPos[i];
    x2=xPos[j];
    r12x=xPos[i]-xPos[j];
    r12y=yPos[i]-yPos[j];
    v1x=xVel[i];
    v2x=xVel[j];
    v1y=yVel[i];
    v2y=yVel[j];
    vcx=(m1*v1x+m2*v2x)/(m1+m2);
    vcy=(m1*v1y+m2*v2y)/(m1+m2);
    v1x-=vcx;
    v1y-=vcy;
    v2x-=vcx;
    v2y-=vcy;
    if (r12x>sig+0.1) r12x-=(rwpos-lwpos);
    if (r12y>sig+0.1) r12y-=twpos;
    if (r12x<-sig-0.1) r12x+=(rwpos-lwpos);
    if (r12y<-sig-0.1) r12y+=twpos;
    n1x=(r12x)/(sig);
    n1y=(r12y)/(sig);
    n2x=-n1x;
    n2y=-n1y;
    d1=Math.abs(n1x*v1x+n1y*v1y);
    d2=Math.abs(n2x*v2x+n2y*v2y);
    xVel[i]+=2*d1*n1x;
    yVel[i]+=2*d1*n1y;
    xVel[j]+=2*d2*n2x;
    yVel[j]+=2*d2*n2y;
  }


  /**
  *
  * Collides particle with top wall
  *
  * @param i int particle number
  *
  */
  public void collideTW(int i){
    if (empty[i]) {System.out.println("Middle: colliding empty particle:"+""+i);return;}
    //pt+=Math.abs(2*yVel[i]*mass[i]/(rwpos-lwpos)); // changed by W. Christian
    pt+=Math.abs(yVel[i]*mass[i]/(rwpos-lwpos));
    if (neighbor[1]!=null){yPos[i]=bwpos;
    }
    else{
      if (therms[0]) thermalize(i,0);
      else yVel[i]=-yVel[i];

    }
    pt+=Math.abs(-yVel[i]*mass[i]/(rwpos-lwpos));
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
  * Calculates Time until ith particle collides with bottom wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeBW(int i){
    if (empty[i]) {System.out.println("Middle: calcColTimeBW empty particle:"+""+i);return 10000;}
    if (yVel[i]<0){
        if (neighbor[5]==null) if (yPos[i]-rad[i]>bwpos)  return Math.max(0,(bwpos+rad[i]-yPos[i])/yVel[i]);
        else if (yPos[i]>bwpos) return Math.max(0,(bwpos-yPos[i])/yVel[i]);

    }
    return 10000;
  }


  /**
  *
  * Calculates Time until ith particle collides with top wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeTW(int i){
    if (empty[i]) {System.out.println("Middle: calcColTimeTW empty particle:"+""+i);return 10000;}
    if (yVel[i]>0){
      if (neighbor[1]==null) if(yPos[i]+rad[i]<twpos) return Math.max(0,(twpos-rad[i]-yPos[i])/yVel[i]);
      else if (yPos[i]<twpos) return Math.max(0,(twpos-yPos[i])/yVel[i]);       //could be zero?!?!
    }
    return 10000;
  }

  /**
  *
  * Collides particle with bottom wall
  *
  * @param i int particle number
  *
  */
  public void collideBW(int i){
    if (empty[i]) {System.out.println("Middle: colliding empty particle:"+""+i);return;}
    //pb+=Math.abs(2*yVel[i]*mass[i]/(rwpos-lwpos));
    pb+=Math.abs(yVel[i]*mass[i]/(rwpos-lwpos));
    if (neighbor[5]!=null){yPos[i]=twpos;
    }
    else{
      if (therms[2])
          thermalize(i,2);
      else yVel[i]=-yVel[i];

    }
    pb+=Math.abs(-yVel[i]*mass[i]/(rwpos-lwpos));
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
  public void collideLW(int i){
    if (empty[i]) {System.out.println("Middle: colliding empty particle:"+""+i);return;}
    double yin=yPos[i]-owner.ensemble1.yOrigin;
    double xvel=xVel[i];
    double yvel=yVel[i];
    double r=rad[i];
    //double xo=(owner.ensemble1.rwpos+owner.ensemble1.lwpos)/2;
    double xo=owner.ensemble1.xOrigin;
    //double xpos=lwpos-xo+0.0001;
    double xpos=owner.ensemble1.rwpos-xo+0.0001;
    owner.ensemble1.setDefaultColor(colors[i].getRed(),colors[i].getGreen(),colors[i].getBlue());
    owner.ensemble1.setDefaultMass(mass[i]);
    owner.ensemble1.addParticle(xpos,yin,xvel,yvel,r);
    removeParticle(i);
    /*
    //pl+=Math.abs(2*xVel[i]*mass[i]/(twpos-bwpos));      //changed by W. Christian
    pl+=Math.abs(xVel[i]*mass[i]/(twpos-bwpos));
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
    */
  }

  /**
  *
  * Collides particle with right wall
  *
  * @param i int particle number
  *
  */
  public void collideRW(int i){
     if (empty[i]) {System.out.println("Middle: colliding empty particle:"+""+i);return;}
    double yin=yPos[i]-owner.ensemble2.yOrigin;
    double xvel=xVel[i];
    double yvel=yVel[i];
    double r=rad[i];
    double xpos=owner.ensemble2.lwpos+0.0001-owner.ensemble2.xOrigin;
    owner.ensemble2.setDefaultColor(colors[i].getRed(),colors[i].getGreen(),colors[i].getBlue());
    owner.ensemble2.setDefaultMass(mass[i]);
    owner.ensemble2.addParticle(xpos,yin,xvel,yvel,r);
    removeParticle(i);
    /*
    // pr+=Math.abs(2*xVel[i]*mass[i]/(twpos-bwpos));
    pr+=Math.abs(xVel[i]*mass[i]/(twpos-bwpos));
    if (neighbor[3]!=null) {xPos[i]=lwpos;
    }else {
       if (therms[1]) thermalize(i,1);
       else xVel[i]=-xVel[i];

    }
    pr+=Math.abs(-xVel[i]*mass[i]/(twpos-bwpos));
    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    rwColTimes[i]=calcColTimeRW(i);
    twColTimes[i]=calcColTimeTW(i);
    if (owner.partInt){
    for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
    }
    */
  }

  void iflowPart(){
      boolean runAgain=false;
      if (applet.clock.isRunning()) {pause();runAgain=true;}
      //Color oc = dcolor;
      //dcolor=Color.green;
      double r = dsize;
      int i = findEmptyPart();
      setParticle(i,lwpos+dsize+0.001,0.001+r+Math.random()*(twpos-r-r-0.002),Math.random()*10,(1-2*Math.random())*10,r);
      recalculateColTimes();
      //dcolor=oc;
      if (runAgain) forward();
  }

  void oflowPart(){
      boolean runAgain=false;
      if (applet.clock.isRunning()) {pause();runAgain=true;}
      removeParticles(1);
      recalculateColTimes();
      if (runAgain) forward();
  }

  void oflowPart(int i){
      boolean runAgain=false;
      if (applet.clock.isRunning()) {pause();runAgain=true;}
      removeParticle(i);
      recalculateColTimes();
      if (runAgain) forward();
  }

  /**
  *
  * Adds ith particle to ensemble.  Require particle number.
  * Gives user more control over the indexing.  If adding a particle with an
  * index that already exists, the previous particle will be replaced.
  *
  *
  * @param i int particle number
  * @param xin double initial x position in units
  * @param yin double initial y position in units
  * @param xvel double initial x velocity in units
  * @param xvel double initial x volocity in units
  * @param r double particle radius in units
  *
  */
  void setParticle(int i, double xin, double yin, double xvel, double yvel, double r){
    if (xVel==null || owner.osi==null) setBounds();
    if (xPos==null) initializeArrays();
    if ((i<xVel.length) && (xVel!=null))
    {
     lastpartnum=Math.max(i,lastpartnum);
     if (r+0.001>(rwpos+lwpos)/2) r=2;
     rad[i]=r;
     if (periodich)
         if (xin+rad[i]>rwpos || xin-rad[i]<lwpos) {xin=(rwpos+lwpos)/2;rad[i]=Math.min(rad[i],rwpos-xin-0.01);}
     if (periodicv)
         if (yin+rad[i]>twpos || yin-rad[i]<bwpos) {yin=(twpos+bwpos)/2;rad[i]=Math.min(rad[i],twpos-yin-0.01);}
     xPos[i]=xin;
     yPos[i]=yin;
     xVel[i]=xvel;
     yVel[i]=yvel;
     empty[i]=false;
     rwColTimes[i]=calcColTimeRW(i);
     lwColTimes[i]=calcColTimeLW(i);
     twColTimes[i]=calcColTimeTW(i);
     bwColTimes[i]=calcColTimeBW(i);
     //recalculateColTimes();
     if (owner.partInt){
     for (int x=0; x<i; x++) colTimes[i][x]=calcColTime(i,x);
     for (int y=i+1; y<lastpartnum+1; y++) colTimes[y][i]=calcColTime(y,i);
     //for (int x=0; x<i; x++) colTimes[i][x]=calcColTime(i,x);
     //for (int y=i+1; y<lastpartnum+1; y++) colTimes[y][i]=calcColTime(y,i);
     }
     mass[i]=dmass;
     colors[i]=dcolor;
    }
    else System.out.println("Error in setParticle. Particle index out or range:"+i);
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
        if (min<=0) {System.out.println("Middle: tWColTime <= 0"); pause();}               //next colliding wall: 1=top wall
        }
        if (rwColTimes[0]<min){
        min=rwColTimes[0];
        nColliding1=0;
        nCollidingW=2;
        if (min<=0) {System.out.println("Middle: rWColTime <= 0"); pause();}               //next colliding wall: 2=right wall
        }
        if (bwColTimes[0]<min){
        min=bwColTimes[0];
        nColliding1=0;
        nCollidingW=3;
        if (min<=0) {System.out.println("Middle: bWColTime <= 0"); pause();}                   //next colliding wall: 3=bottom wall
        }
        if (lwColTimes[0]<min){
        min=lwColTimes[0];
        nColliding1=0;
        nCollidingW=4;
        if (min<=0) {System.out.println("Middle: LWColTime <= 0"); pause();}                    //next colliding wall: 4=left wall
        }
    }

    for (i=1; i<lastpartnum+1; i++)
      if (empty[i]==false){
        if (twColTimes[i]<min){
        min=twColTimes[i];
        nColliding1=i;
        nCollidingW=1;
        if (min<=0) {System.out.println("Middle: tWColTime <= 0"); pause();}
        }
        if (rwColTimes[i]<min){
        min=rwColTimes[i];
        nColliding1=i;
        nCollidingW=2;
        if (min<=0) {System.out.println("Middle: rWColTime <= 0"); pause();}
        }
        if (bwColTimes[i]<min){
        min=bwColTimes[i];
        nColliding1=i;
        nCollidingW=3;
        if (min<=0) {System.out.println("Middle: bWColTime <= 0"); pause();}
        }
        if (lwColTimes[i]<min){
        min=lwColTimes[i];
        nColliding1=i;
        nCollidingW=4;
        if (min<=0) {System.out.println("Middle: LWColTime <= 0"); pause();}
        }

        if (owner.partInt)
        for (int x=0; x<i; x++){

         if (colTimes[i][x]<min){
           min=colTimes[i][x];
           nCollidingW=0;                     //next colliding wall=0 if next collision is a particle collision
           nColliding1=i;                     //nColliding2  and  nColliding2  are next colliding particles
           nColliding2=x;
           if (min<=0) {System.out.println("Middle: PartColTime <= 0"); pause();}
         }
        }
      }

   
    if (min<0) {System.out.println("Middle: negative Collide Time"); pause(); min=10000;}
    if (min==0) {System.out.println("Middle: Collide Time = 0"); pause(); min= 10000;}
    mint=min;
    temptime=mint;
  }

  /**
  * If a particle that is the next Colliding particle is
  * removed from the ensemble, the collide times must be cleared.
  *
  *
  * @param i particle number to be removed
  */
  void clearColTimes(int i){
    //if ((i>=0)&&(i<empty.length+1)){
      empty[i]=true;
      twColTimes[i]=10000;
      rwColTimes[i]=10000;
      bwColTimes[i]=10000;
      lwColTimes[i]=10000;
      if (owner.partInt){
          for (int q=0; q<i; q++) colTimes[i][q]=10000;
          for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=10000;
      }
    //}
  }



  /**
  *
  * Sets the Neighboring ensemble. For periodic boundaries,
  * neighbor must be set to "this"
  *
  * @param i int neighbor number-- begins with upper left. Proceeds clockwise.
  * @param e DEnsemble  neighboring ensemble
  * @param xoffset double x-displacement between neighbor and "this"
  * @param yoffset double y-displacement between neighbor and "this"
  *
  */
  void setNeighbor(int i, MFEnsemble e,double xoffset, double yoffset){
      this.neighbor[i]=e;
      xOff[i]=xoffset;
      yOff[i]=yoffset;
  }

  
  void initializeArrays(){
    if (maxp<=0) maxp=50;
    if (owner.partInt){
      colTimes=new double[maxp][];
      for (int i=0; i<maxp; i++){
        colTimes[i]=new double[i+1];
      }
    }
    rwColTimes=new double[maxp];
    lwColTimes=new double[maxp];
    twColTimes=new double[maxp];
    bwColTimes=new double[maxp];
    xVel=new double[maxp];
    yVel=new double[maxp];
    xPos=new double[maxp];
    yPos=new double[maxp];
    mass=new double[maxp];
    rad=new double[maxp];
    colors=new Color[maxp];
    empty = new boolean[maxp];
    for (int j=0; j<maxp; j++) empty[j]=true;
    lastpartnum=-1;
  }


  public void recalculateColTimes(int i){
      bwColTimes[i]=calcColTimeBW(i);
      lwColTimes[i]=calcColTimeLW(i);
      rwColTimes[i]=calcColTimeRW(i);
      twColTimes[i]=calcColTimeTW(i);
       if (owner.partInt){
      for (int q=0; q<i; q++) if (!empty[q]) colTimes[i][q]=calcColTime(i,q);
      for (int p=i+1; p<lastpartnum+1; p++)  if (!empty[p]) colTimes[p][i]=calcColTime(p,i);
      }
      findMinColTime();      //initial min colision time must be determined

  }

  public void recalculateColTimes(){
    for (int i=0; i<lastpartnum+1; i++)
        if (empty[i]==false){
        int p=i;
        rwColTimes[i]=calcColTimeRW(i);
        lwColTimes[i]=calcColTimeLW(i);
        twColTimes[i]=calcColTimeTW(i);
        bwColTimes[i]=calcColTimeBW(i);
        }
    if (owner.partInt)
    for (int y=1; y<lastpartnum+1; y++)
        for (int x=0; x<y; x++){
              if (empty[x]==false && empty[y]==false) colTimes[y][x]=calcColTime(y,x);
            }
    findMinColTime();      //initial min colision time must be determined
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
    int ht=(int)((top-bottom)*ppu);
    int offseth = (int)(ppu*(top-twpos));

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
      osg.fillRect(offsetw-1,offseth,w+2,h);
      int diameter=1;
      for (i=0; i<lastpartnum+1; i++){
        if (!empty[i]){
          //osg.setColor(colors[i]);
          osg.setColor(colors[i]);
          r=rad[i];
          diameter=Math.max(2,(int)(2*r*ppu));   //added by W. Christian to make sure we have a mimimum size  of 2 pixels.
          osg.fillOval((int)(ppu*(xPos[i]-r)),ht-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          //osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          //if ((yPos[i]+r)>twpos) osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r-twpos)),diameter,diameter);
          //if ((yPos[i]-r)<bwpos) osg.fillOval((int)(ppu*(xPos[i]-r)),h-(int)(ppu*(yPos[i]+r+twpos)),diameter,diameter);
         }
      }
      if (therms[0]){}
      if (therms[1]){}
      if (therms[2]){}
      if (therms[3]){}
      osg.dispose();
      }
  }



  public synchronized void setPeriodicV(boolean pv){
      this.periodicv=pv;
      if (pv==false)  {
        neighbor[1]=null;
        neighbor[5]=null;
        if (!periodich){
          neighbor[0]=null;
          neighbor[2]=null;
          neighbor[4]=null;
          neighbor[6]=null;
        }
      }
  }

  void setRWPos(double p){ rwpos=p;}
  void setLWPos(double p){ lwpos=p;}
  void setTWPos(double p){ twpos=p;}
  void setBWPos(double p){ bwpos=p;}

  
  public void thermalize(int i, int wallnum){
    if (i<0 || i>=maxp || mass[i]<=0) {System.out.println("Error in thermalize: mass=0?");return;}
    //double vf;
    double temp= wallTemps[wallnum];
    //switchVelocity(i);  // added by W. Christian to make sure our selction of particles is random and not weighted toward the fast particles.
    double vin = Math.sqrt(xVel[i]*xVel[i]+yVel[i]*yVel[i]);
    double v = Math.sqrt(2*kb*temp/mass[i]);
    if (wallnum==0) {
        yVel[i]=-v*Math.random();
        if (Math.random()<0.5) xVel[i]=Math.sqrt(v*v-yVel[i]*yVel[i]);
        xVel[i]=-Math.sqrt(v*v-yVel[i]*yVel[i]);

        qt+=0.5*mass[i]*(v*v-vin*vin);
    }
    else if (wallnum==1){
        xVel[i]=-v*Math.random();
        if (Math.random()<0.5) yVel[i]=Math.sqrt(v*v-xVel[i]*xVel[i]);
        else yVel[i]=-Math.sqrt(v*v-xVel[i]*xVel[i]);

        qr+=0.5*mass[i]*(v*v-vin*vin);
    }else if (wallnum==2){
        yVel[i]=v*Math.random();
        if (Math.random()<0.5) xVel[i]=Math.sqrt(v*v-yVel[i]*yVel[i]);
        else xVel[i]=-Math.sqrt(v*v-yVel[i]*yVel[i]);
        //vf=Math.sqrt(xVel[i]*xVel[i]+yVel[i]*yVel[i]);
        qb+=0.5*mass[i]*(v*v-vin*vin);
    }else if (wallnum==3){
        xVel[i]=v*Math.random();
        if (Math.random()<0.5)   yVel[i]=Math.sqrt(v*v-xVel[i]*xVel[i]);
        else yVel[i]=-Math.sqrt(v*v-xVel[i]*xVel[i]);
        ql+=0.5*mass[i]*(v*v-vin*vin);
    }
  }



  /**
  *  Switch the velocity of the i-th particle with a random particle in the ensemble.
  */
  void switchVelocity(int i){
      int index=0;
      int count=0;
       if (i>=empty.length){ System.out.println("switchVelocity:"+" "+i);return;}
      do{
          index=(int)Math.floor((lastpartnum)*Math.random() );
          //changed from:
          //index=(int)Math.floor((lastpartnum+1)*Math.random() );
          //changed 07/19/99, by Jim Nolen
          count++;
          index=Math.min(index,lastpartnum);
      }while(empty[index]==true && count<2*lastpartnum);

      if(empty[index]==true){  //we have an error
         System.out.println("Error: Cannot find non-empty particle. No particle switch.");
          return;
      }

      // do the switch
      double xtemp=xVel[i];
      double ytemp=yVel[i];
      xVel[i]=xVel[index];
      yVel[i]=yVel[index];
      xVel[index]=xtemp;
      xVel[index]=ytemp;
      //recalc collision times for the switched particle.  The calling routine should recalc for the i-th particle.
      bwColTimes[index]=calcColTimeBW(index);
      lwColTimes[index]=calcColTimeLW(index);
      rwColTimes[index]=calcColTimeRW(index);
      twColTimes[index]=calcColTimeTW(index);
      // collisions with other particles
      if (owner.partInt){
        for (int q=0; q<index; q++) colTimes[index][q]=calcColTime(index,q);
        for (int p=index+1; p<lastpartnum+1; p++) colTimes[p][index]=calcColTime(p,index);
      }
      return;
  }



  public void setDefault(){
   pause();
   owner.osi=null;
   time=0;
   pvolume=0;
   setBounds();
   initializeArrays();
   //setAutoRefresh(true);   removed by W. Christian
   removeDataSources();  // added by W. Christian
   paintOSI();
   if (autoRefresh)owner.repaint();
  }

}
