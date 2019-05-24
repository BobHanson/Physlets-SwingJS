/*
**************************************************************************
**
**                      Class  Ensemble
**
**************************************************************************
**
** class Ensemble extends  Panel
**
** @author Jim Nolen and Wolfgang Christian
**
*************************************************************************/

package molecular;

import java.awt.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Vector;
import java.util.Enumeration;
import edu.davidson.tools.*;


public class Ensemble extends Object implements SStepable, edu.davidson.tools.SDataSource {
// mouse stuff added by W. Christian
    private MouseMotionAdapter mouseMotionAdapter;
    private MouseAdapter mouseAdapter;
    private boolean enableMouse=false;
    private int     boxWidth=0;
    private edu.davidson.display.Format mouseFormat= new edu.davidson.display.Format("%-+6.3g");
    private int mouseX=0, mouseY=0;
    boolean mouseDown=false;

// data source
    Vector  ensembleDataSources=new Vector();
String message = null;
boolean showMessage = false;

int bwidth=5;
double xOrigin=0, yOrigin=0;
int skipCounter=0, skip=0;
String[] varStrings= new String[]{"time","p","v","t","n","qt","qr","qb","ql","pt","pr","pb","pl","dv","dt"};
double[][] ds=new double[1][15];  // the datasource state variables;
Color bgColor = Color.green;
boolean autoRefresh=true;
boolean setup;
boolean running;
double qt=0,qb=0,qr=0,ql=0;
double pt=0,pb=0,pr=0,pl=0;
double dv=0;  // the change in volume in one time step;
double mint;
double kb=1;
double dmass=1;
double dtemp=0;
double dsize=1;
Color dcolor= new Color(0,0,150);
int maxp = 80;
int mode = 0;
int ppu = 10;                 //pixels per unit
double time = 0;

double xOff[]= new double [8];
double yOff[]= new double [8];
double rwpos = 10;
double lwpos = 0;
double twpos = 10;
double bwpos = 0;
int nump = 20;
double temptime=-1;             //number of particles
double colTimes[][]=null;
//double imageColTimes[][]=null;
double rwColTimes[]=null;
double lwColTimes[]=null;
double twColTimes[]=null;
double bwColTimes[]=null;
double xVel[]=null;
double yVel[]=null;
double xPos[]=null;
double yPos[]=null;
double mass[]=null;
double rad[]=null;
boolean fixed[]=null;
double k=0;
Color forrest  = new Color(0,120,0);
Color brown  = new Color(190,110,0);
Color navy  = new Color(0,0,150);
Color purple  = new Color(100,0,135);
Color colors[]=null;
Color colors2[] =  {Color.white,Color.red,Color.pink,navy,Color.orange,Color.lightGray,Color.yellow,Color.magenta,
                forrest,Color.darkGray,brown,purple,Color.blue,Color.cyan,Color.black};
int lastpartnum=-1;
int sleeptime = 50;
int nColliding1=0;
int nColliding2=0;   //next colliding particles
int nCollidingW=1;
int nCollidingN=0;
int nCollidingI=0;
boolean periodicv=true;
boolean periodich=true;
boolean periodic=true;
boolean colPart;            //true if next collision will be a particle collision false if a wall collision
boolean change= true;
Ensemble neighbor[] = new Ensemble[8];
double wallTemps[] = new double[4];
boolean therms[] = {false,false,false,false};
double pvolume=0;
boolean keepRunning;
boolean empty[]=null;
boolean image[]=null;
double imageTimes[]=new double [8];
double imageTimes2[]=new double [8];
//BitSet empty=null;

EnsemblePanel owner = null;
SApplet applet = null;
Histogram histogram=null;

  //constructor methods.  Sets Applet owner.

  public Ensemble() {   // the basic constructor
  }

  public Ensemble(EnsemblePanel o){
    this(); // added by W. Christian to call basic constructor.
    owner = o;
    applet=owner.owner;
    //try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
  }

  public synchronized void start(){
  }

  public void stop(){
  }

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

  void initializeArrays(){
    if (maxp<=0) maxp=50;
    colTimes=new double[maxp][];
    for (int i=0; i<maxp; i++){
      colTimes[i]=new double[i+1];
    }
    fixed =new boolean[maxp];
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
    for (int j=0; j<maxp; j++){
        empty[j]=true;
        fixed[j]=false;
    }
    lastpartnum=-1;
  }

  /**
  *
  *
  * Clear variables that are used to keep track of collisions.
  *
  */
  void clearPressureCounters(){
    qt=0;qr=0;qb=0;ql=0;
    pr=0;pl=0;pt=0;pb=0;
  }

  /**
  *
  *
  * Divide the variables that are used to keep track of collisions by dt.
  *
  */
  void adjustPressureCounters(double dt){
   pr/=dt;
   pl/=dt;
   pt/=dt;
   pb/=dt;
  }

  /**
  *
  * Advances system by one time step dt, global variable.
  * Performs one paint operation at end of step.
  *
  * @param dt double time step
  *
  */
  public void step(double dt, double time){
    int i;
    qt=0;qr=0;qb=0;ql=0;//divide by dt
    pr=0;pl=0;pt=0;pb=0;
    //two types of pressure. ~dp/dt and ~pv=nrt.
    //pt,pr,pb,pl    //divide at end by dt
    //subtract vol of spheres.
    double tl=dt;                             //tl = time left until a paint step
    int counter=0;
    do {
      if (tl<mint){
        mint-=tl;
        tl-=advanceDT(tl);
      }                                              //setTime 0 t
      else {
        tl-=advanceDT(mint);
        for (i=0; i<lastpartnum+1; i++)
          if (!empty[i]){
            rwColTimes[i]-=temptime;
            lwColTimes[i]-=temptime;
            twColTimes[i]-=temptime;
            bwColTimes[i]-=temptime;
          }
        for (int y=1; y<lastpartnum+1; y++)
            for (int x=0; x<y; x++)
            if (!empty[y] && !empty[x]){
            colTimes[y][x]-=temptime;
            }
        switch (nCollidingW){
          case 0: collideParticles(nColliding1,nColliding2); break;
          case 1: collideTW(nColliding1); break;
          case 2: collideRW(nColliding1); break;
          case 3: collideBW(nColliding1); break;
          case 4: collideLW(nColliding1); break;
        }
        findMinColTime();
        //temptime=mint;

      }
      counter++;
    } while (tl>0 && counter<1000);
    if(counter>=1000) 
    	System.out.println(" counter ="+counter + "  tl= "+tl + " mint ="+mint + "  dt="+dt + "temptime="+temptime);
    tl=0;
    paintOSI();
    Graphics g = owner.getGraphics();
    owner.paint(g);
    g.dispose();
    this.time=time+dt;
   // if(skipCounter<=0){
       // calc pressure
       // whatever
   //    skipCounter=skip;
   // } else skipCounter--;
   pr/=dt;
   pl/=dt;
   pt/=dt;
   pb/=dt;
    if(applet!=null){
        if(histogram!=null)applet.clearData(histogram.hashCode());
        applet.updateDataConnections();
    }
  }

  /**
  *
  * Moves all particles forward by a given time
  *
  * @param t double time step
  * @returns double time step-- same as input
  *
  */
  public double advanceDT(double t){
    for(int i=0; i<lastpartnum+1; i++){
        if (empty[i]==false){
          xPos[i]+=t*xVel[i];
          yPos[i]+=t*yVel[i];
        }
    }
    time+=t;
    return t;
  }




  /**
  *
  * Calculates Time until ith particle collides with top wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeTW(int i){
    if (empty[i]) return 10000;
    if (yVel[i]>0){
      if (neighbor[1]==null) return Math.max(0,(twpos-rad[i]-yPos[i])/yVel[i]);
      else {if (yPos[i]<twpos) return Math.max(0,(twpos-yPos[i])/yVel[i]);}       //could be zero?!?!
    }
     return 10000;
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
    if (xVel[i]<0) {
      if (neighbor[7]==null) return Math.max(0,(lwpos+rad[i]-xPos[i])/xVel[i]);
      else {
      if (xPos[i]>lwpos)return Math.max(0,(lwpos-xPos[i])/xVel[i]);
      }

    }
    return 10000;
  }

  /**
  *
  * Calculates Time until ith particle collides with bottom wall
  *
  * @param i int particle number.
  * @returns double collision time
  */
  public double calcColTimeBW(int i){
    if (empty[i]) return 10000;
    if (yVel[i]<0){
        if (neighbor[5]==null)  return Math.max(0,(bwpos+rad[i]-yPos[i])/yVel[i]);
        else {if (yPos[i]>bwpos)return Math.max(0,(bwpos-yPos[i])/yVel[i]);}

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
     if (empty[i]) return 10000;
    if (xVel[i]>0){
      if (neighbor[3]==null) return Math.max(0,(rwpos-rad[i]-xPos[i])/xVel[i]);
      else {if (xPos[i]<rwpos) return Math.max(0,(rwpos-xPos[i])/xVel[i]);}

    }
   return 10000;
  }

  /**
  *
  * Retrieves a collision time from colTimes array.
  *
  * @param i int 1st particle number.
  * @param j int 2nd particle number.
  * @returns double collision time
  */
  public double getColTime(int i, int j){
    int p1=Math.max(i,j);    //particle 1.
    int p2=Math.min(i,j);    //particle 2.
    return colTimes[p1][p2];
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
      if ( empty[i] || empty[j] ) return 10000;
      double t1,t2;
      double ct=10000;
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
          //t1=(-vrdot+Math.sqrt(d))/(v12mags);   //now they will collide
          t2=(-vrdot-Math.sqrt(d))/(v12mags);
          //ct= Math.min(t1,t2);
          ct=Math.max(0,t2);
          return ct;
        }
      }

      ct=Math.min(ct,calcImageCollisionTime(i,j));

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
        Ensemble en=null;
          for (int q=0; q<8; q++){
             en=neighbor[q];
            if(en!=null && en==this) ct=Math.min(ct,calcColTimeIm(p1,p2,q, xOff[q],yOff[q]));
          }
      return Math.max(0,ct);
  }

  /*public double calcColTimeIm(int p1, int nnum, double xo,double yo){
      Ensemble nb=neighbor[nnum];
      return 10000;}*/

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

      Ensemble nb=neighbor[nnum];
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
    Ensemble en= neighbor[nnum];
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
    int p1=Math.max(i,j);    //particle 1.
    int p2=Math.min(i,j);    //particle 2.
    //check for overlapping particles
    if (empty[p1] || empty[p2]) return;
    if(mass[p1]!=mass[p2]) collideDiffMassParticles(p1,p2);
    else if(fixed[p1] || fixed[p2]) collideFixedParticles(p1, p2);
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
  * Collides a particle with a fixed particle.
  *
  * @param i int 1st particle number
  * @param j int 2nd particle number.  Assume this partilce is fixed.
  *
  */
  public void collideFixedParticles(int i, int j){
    if(fixed[i] && fixed[j]) return;  // both particles are fixed and cannot collide.
    else if(fixed[i] && !fixed[j]){ // check to see if the second particle is fixed.
        int tempi=i;  // swap i and j particles.
        i=j;
        j=tempi;
    }
    double x1=xPos[i];
    double x2=xPos[j];
    double y1=yPos[i];
    double y2=yPos[j];
    double rx=x2-x1;
    double ry=y2-y1;
    double r=Math.sqrt(rx*rx+ry*ry);
    rx /=r;  // unit vector components from particle 1 to particle 2.  This is the collision normal.
    ry /=r;
    double vx=xVel[i];
    double vy=yVel[i];
    double vn=rx*vx+ry*vy;  // projection of velocity into the normal
    if(vn <= 0) return;  // the centers of the particles are already moving away.
    xVel[i]= vx- 2*vn*rx;
    yVel[i]= vy- 2*vn*ry;
  }


  /**
  *
  * Collides particle with top wall
  *
  * @param i int particle number
  *
  */
  public void collideTW(int i){
    if (empty[i]) return;
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

    for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);


  }

  /**
  *
  * Collides particle with bottom wall
  *
  * @param i int particle number
  *
  */
  public void collideBW(int i){
    if (empty[i]) return;
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

    for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
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
    for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);

  }

  /**
  *
  * Collides particle with right wall
  *
  * @param i int particle number
  *
  */
  public void collideRW(int i){
    if (empty[i]) return;
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
    for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
  }

  /**
  *  Switch the velocity of the i-th particle with a random particle in the ensemble.
  */
  private void switchVelocity(int i){
      int index;
      int count=0;
      do{
          index=(int)Math.floor((lastpartnum)*Math.random() );
          //changed from:
          //index=(int)Math.floor((lastpartnum+1)*Math.random() );
          //changed 07/19/99, by Jim Nolen
          count++;
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
      for (int q=0; q<index; q++) colTimes[index][q]=calcColTime(index,q);
      for (int p=index+1; p<lastpartnum+1; p++) colTimes[p][index]=calcColTime(p,index);
      return;
  }

  public void thermalize(int i, int wallnum){
    if (i<0 || i>=maxp || mass[i]<=0) {System.out.println("Error in thermalize: mass=0?");return;}
    //double vf;
    double temp= wallTemps[wallnum];
    switchVelocity(i);  // added by W. Christian to make sure our selction of particles is random and not weighted toward the fast particles.
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
  *
  * Method finds time until the next collision.
  * Searches arrays for smallest particle or wall collision time.
  *
  * @returns double collision time
  *
  */
  /*
  public double findMinColTime(){
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
        }
        if (rwColTimes[i]<min){
        min=rwColTimes[i];
        nColliding1=i;
        nCollidingW=2;
        }
        if (bwColTimes[i]<min){
        min=bwColTimes[i];
        nColliding1=i;
        nCollidingW=3;
        }
        if (lwColTimes[i]<min){
        min=lwColTimes[i];
        nColliding1=i;
        nCollidingW=4;
        }

        for (int x=0; x<i; x++)
         if (colTimes[i][x]<min){
           min=colTimes[i][x];
           nCollidingW=0;                     //next colliding wall=0 if next collision is a particle collision
           nColliding1=i;                     //nColliding2  and  nColliding2  are next colliding particles
           nColliding2=x;
         }
      }
    if (min<0) {System.out.println("negative Collide Time"); pause(); return 10000;}
    if (min==0) {System.out.println("Collide Time = 0"); pause(); return 10000;}
    else return min;
  }
  */

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
        //if (min<=0) {System.out.println("tWColTime <= 0"); pause();}               //next colliding wall: 1=top wall
        }
        if (rwColTimes[0]<min){
        min=rwColTimes[0];
        nColliding1=0;
        nCollidingW=2;
        //if (min<=0) {System.out.println("rWColTime <= 0"); pause();}               //next colliding wall: 2=right wall
        }
        if (bwColTimes[0]<min){
        min=bwColTimes[0];
        nColliding1=0;
        nCollidingW=3;
        //if (min<=0) {System.out.println("bWColTime <= 0"); pause();}                   //next colliding wall: 3=bottom wall
        }
        if (lwColTimes[0]<min){
        min=lwColTimes[0];
        nColliding1=0;
        nCollidingW=4;
        //if (min<=0) {System.out.println("LWColTime <= 0"); pause();}                    //next colliding wall: 4=left wall
        }
    }

    for (i=1; i<lastpartnum+1; i++)
      if (empty[i]==false){
        if (twColTimes[i]<min){
        min=twColTimes[i];
        nColliding1=i;
        nCollidingW=1;
        //if (min<=0) {System.out.println("tWColTime <= 0"); pause();}
        }
        if (rwColTimes[i]<min){
        min=rwColTimes[i];
        nColliding1=i;
        nCollidingW=2;
        //if (min<=0) {System.out.println("rWColTime <= 0"); pause();}
        }
        if (bwColTimes[i]<min){
        min=bwColTimes[i];
        nColliding1=i;
        nCollidingW=3;
        //if (min<=0) {System.out.println("bWColTime <= 0"); pause();}
        }
        if (lwColTimes[i]<min){
        min=lwColTimes[i];
        nColliding1=i;
        nCollidingW=4;
        //if (min<=0) {System.out.println("LWColTime <= 0"); pause();}
        }

        for (int x=0; x<i; x++){
         if (colTimes[i][x]<min){
           min=colTimes[i][x];
           nCollidingW=0;                     //next colliding wall=0 if next collision is a particle collision
           nColliding1=i;                     //nColliding2  and  nColliding2  are next colliding particles
           nColliding2=x;
           //if (min<=0) {System.out.println("PartColTime <= 0  min="+min); min=1.0E-6;}// pause();}
           if (min<=0) {min=1.0E-8;}
         }
        }
      }


    //if (min<0) {System.out.println("negative Collide Time"); pause(); min=10000;}
    //if (min==0) {System.out.println("Collide Time = 0"); pause(); min= 10000;}
    mint=min;
    temptime=mint;
  }


  /**
  *
  * Sets the Neighboring ensemble. For periodic boundaries,
  * neighbor must be set to "this"
  *
  * @param i int neighbor number-- begins with upper left. Proceeds clockwise.
  * @param e Ensemble  neighboring ensemble
  * @param xoffset double x-displacement between neighbor and "this"
  * @param yoffset double y-displacement between neighbor and "this"
  *
  */
  void setNeighbor(int i, Ensemble e,double xoffset, double yoffset){
      this.neighbor[i]=e;
      xOff[i]=xoffset;
      yOff[i]=yoffset;
  }


  /**
  *
  * Sets size of particle. Default size is .
  *
  * @param i int particle number
  * @param s double size
  * @ returns true if successful, false otherwise.
  *
  */
  public boolean setParticleSize(int i,double s){
    boolean runAgain=false;
    if (applet.clock.isRunning()) {runAgain=true; pause();}

    if(i<0 || rad==null || i>rad.length-1){
        System.out.println("Error in setParticleSize. Particle index out or range:"+i);
        return false;
    }
    s=Math.abs(s);
    if (s+0.001>(rwpos-lwpos)/2) s=2;
    if (xPos[i]+s>rwpos) {System.out.println("Error in setParticleSize:"+""+s);s=rwpos-0.01-xPos[i];}
    if (xPos[i]-s<lwpos) {System.out.println("Error in setParticleSize:"+""+s);s=xPos[i]-lwpos-0.01;}
    if (yPos[i]+s>twpos) {System.out.println("Error in setParticleSize:"+""+s);s=twpos-0.01-yPos[i];}
    if (yPos[i]-s<bwpos) {System.out.println("Error in setParticleSize:"+""+s);s=yPos[i]-bwpos-0.01;}
    rad[i]=s;

    if (autoRefresh){
      bwColTimes[i]=calcColTimeBW(i);
      lwColTimes[i]=calcColTimeLW(i);
      rwColTimes[i]=calcColTimeRW(i);
      twColTimes[i]=calcColTimeTW(i);
      for (int q=0; q<i; q++) colTimes[i][q]=calcColTime(i,q);
      for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=calcColTime(p,i);
      findMinColTime();      //initial min colision time must be determined
    }
    if (runAgain)forward();
    return true;
  }

    /**
  *
  * Set a particle so that it cannot move
  *
  * @param i int particle number
  * @param fixed true will fix the particle
  *
  */
  public void setParticleFixed(int i,boolean f){
    if (i<fixed.length && i>=0)fixed[i]=f;
    else System.out.println("Error in setParticleFixed. Particle index out or range:"+i);
  }


  /**
  *
  * Sets Mass of particle. Default mass is 1 unit.
  *
  * @param i int particle number
  * @param m double mass
  *
  */
  public void setParticleMass(int i,double m){
    if (i<mass.length && m>0)mass[i]=m;
    else System.out.println("Error in setParticleMass. Particle index out or range:"+i+"or mass<=0");
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
     for (int x=0; x<i; x++) colTimes[i][x]=calcColTime(i,x);
     for (int y=i+1; y<lastpartnum+1; y++) colTimes[y][i]=calcColTime(y,i);
     //for (int x=0; x<i; x++) colTimes[i][x]=calcColTime(i,x);
     //for (int y=i+1; y<lastpartnum+1; y++) colTimes[y][i]=calcColTime(y,i);

     mass[i]=dmass;
     colors[i]=dcolor;
    }
    else System.out.println("Error in setParticle. Particle index out or range:"+i);
  }

  /**
  *
  * Adds particle to this ensemble. Does not require a specific particle number.
  * Added particle is next number after "lastpartnum".
  * Uses default mass and color values for new particle.
  *
  *
  * @param xin double initial x position in units
  * @param yin double initial y position in units
  * @param xvel double initial x velocity in units
  * @param xvel double initial x volocity in units
  * @param r double particle radius in units
  * @returns int new particle number
  */
  public synchronized int addParticle(double xin, double yin, double xvel, double yvel, double r){
    boolean runAgain=false;
    if (applet.clock.isRunning()) {runAgain=true; pause();}
    if (xVel==null || owner.osi==null) setBounds();
    if (xPos==null) initializeArrays();
    //int i=lastpartnum+1;
    int i=findEmptyPart();
    xin+=(rwpos+lwpos)/2;
    yin+=(twpos+bwpos)/2;
    if ((i<maxp)&&(i>=0)) setParticle(i,xin,yin,xvel,yvel,r);
    else System.out.println("Error in addParticle. Particle index out or range:"+i);
    if (autoRefresh){
       recalculateColTimes();
      // findMinColTime();      //initial min colision time must be determined
       paintOSI();
       owner.repaint();
    }
    pvolume+=Math.PI*r*r;
    if (runAgain) {forward();}
    return i;
  }

  int findEmptyPart(){
    int i;
    for (i=0; i<lastpartnum+1; i++){
      if (empty[i]) break;
    }
    if (i>lastpartnum){
        if (i<maxp)return i;  //lastpartnum+1
        else return 0;
    }
    else if (empty[i]) return i;
    else return 0;
      //changed 07/19/99  by JHN
  }



  /**
  *
  * Sets Position of particle
  *
  * @param i int particle number
  * @param xin double initial x-position
  * @param yin double initial y-position
  *
  */
  public void setParticlePos(int i, double xin, double yin){
    if ((i<xPos.length)&&(i>=0)){
       xin+=xOrigin;
       yin+=yOrigin;
       if (xin+rad[i]>rwpos || xin-rad[i]<lwpos) {xin-=xOrigin; System.out.println("Error in setParticlePos:"+"X"+xin);xin=xOrigin;}
       if (yin+rad[i]>twpos || yin-rad[i]<bwpos) {yin-=yOrigin; System.out.println("Error in setParticlePos:"+"Y"+yin);yin=yOrigin;}
          xPos[i]=xin;
          yPos[i]=yin;
       if (autoRefresh){
       rwColTimes[i]=calcColTimeRW(i);
       lwColTimes[i]=calcColTimeLW(i);
       twColTimes[i]=calcColTimeTW(i);
       bwColTimes[i]=calcColTimeBW(i);
        for (int x=0; x<i; x++) colTimes[i][x]=calcColTime(i,x);
        for (int y=i+1; y<lastpartnum+1; y++) colTimes[y][i]=calcColTime(y,i);
        findMinColTime();      //initial min colision time must be determined

       }
    }
    else System.out.println("Error in setParticlePos. Particle index out or range:"+i);

  }

  /**
  *
  * Sets Velocity of particle
  *
  * @param i int particle number
  * @param xvel double initial x-velocity
  * @param yvel double initial y-velocity
  *
  */
  public void setParticleVel(int i, double xvel, double yvel){
    if ((i<xVel.length)&&(i>=0)){
       xVel[i]=xvel;
       yVel[i]=yvel;
    if (autoRefresh){
     recalculateColTimes(i);
     //findMinColTime();      //initial min colision time must be determined
    }

    }
    else System.out.println("Error in setParticleVel(). Particle index out or range:"+i);
  }

  /**
  *
  * Sets color of particle
  *
  * @param i int particle number
  * @param rd int red value   (<256)
  * @param gr int green value    (<256)
  * @param bl int blue value    (<256)
  *
  */
  public void setParticleRGB(int i, int rd, int gr, int bl){
     if ((i<colors.length)&&(i>=0)) {colors[i]= new Color(rd,gr,bl);}
     else System.out.println("Error in setParticleRGB. Particle index out or range:"+i);
  }

  public void setTemperature(double temp, int numsteps){
    if(numsteps<1){setTemp(temp);return;}
    boolean runAgain=false;
    if (applet.clock.isRunning()) {
        runAgain=true;
        pause();
    }
    double currentTemp=getTemp();
    double dT=(temp-currentTemp)/(numsteps);
    for(int i=0; i<numsteps; i++){
        currentTemp+=dT;
        setTemp(currentTemp);
        // setTemp did not update so we need to do this now.
        if(histogram!=null)applet.clearData(histogram.hashCode());
        if(!MolecularApplet.isJS) {
        	if(applet!=null) applet.updateDataConnections();
        	try{Thread.sleep(100);}
        	catch(Exception e){;}
        	} else {
        		if(applet!=null) applet.updateDataConnections();
        	}
    }
    if (runAgain) forward();
  }

  public void setTemp(double temp){
    if (temp<=0) return;
    boolean runAgain=false;
    if (applet.clock.isRunning()) {
        runAgain=true;
        pause();
    }
    int n=getNumParticles();
    double tmass=0;
    for(int i=0; i<lastpartnum+1; i++){
      if (empty[i]==false) tmass+=mass[i];
    }
    double v=Math.sqrt((2*n*temp)/(tmass));

    for (int i=0; i<lastpartnum+1; i++){
        if (Math.random()>0.5){
            yVel[i]=-v*Math.random();
            if (Math.random()<0.5) xVel[i]=Math.sqrt(v*v-yVel[i]*yVel[i]);
            else xVel[i]=-Math.sqrt(v*v-yVel[i]*yVel[i]);
        }
        else {
            yVel[i]=v*Math.random();
            if (Math.random()<0.5) xVel[i]=Math.sqrt(v*v-yVel[i]*yVel[i]);
            else xVel[i]=-Math.sqrt(v*v-yVel[i]*yVel[i]);
        }
    }

    recalculateColTimes();
    //findMinColTime();      //initial min colision time must be determined
    if (runAgain) forward();
  }

  /**
  *
  * Method slow down or speed up particles so that they have a particular temperature
  *
  * @param temp double new temperature
  */
  public void changeToTemp(double temp){
      double nt,ot;
      boolean runAgain=false;
      if (applet.clock.isRunning()) pause();
      ot = getTemp();
      double scale= ot/temp;
      for (int i=0; i<lastpartnum+1; i++){
        if (empty[i]==false) {
            xVel[i]=xVel[i]/Math.sqrt(scale);
            yVel[i]=yVel[i]/Math.sqrt(scale);
        }
      }
      nt=getTemp();
      recalculateColTimes();
      if (runAgain) forward();
  }

  /**
  *
  * Sets color of background
  *
  * @param rd int red value   (<256)
  * @param gr int green value    (<256)
  * @param bl int blue value    (<256)
  *
  */
  public synchronized void setBackgroundRGB(int rd, int gr, int bl){
     this.bgColor= new Color(rd,gr,bl);
     if (autoRefresh){paintOSI();}
  }

  public void recalculateColTimes(int i){
      bwColTimes[i]=calcColTimeBW(i);
      lwColTimes[i]=calcColTimeLW(i);
      rwColTimes[i]=calcColTimeRW(i);
      twColTimes[i]=calcColTimeTW(i);
      for (int q=0; q<i; q++) if (!empty[q]) colTimes[i][q]=calcColTime(i,q);
      for (int p=i+1; p<lastpartnum+1; p++)  if (!empty[p]) colTimes[p][i]=calcColTime(p,i);
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
    for (int y=1; y<lastpartnum+1; y++)
        for (int x=0; x<y; x++){
              if (empty[x]==false && empty[y]==false) colTimes[y][x]=calcColTime(y,x);
            }
    findMinColTime();      //initial min colision time must be determined
  }

  public synchronized void setAutoRefresh(boolean ar){
   this.autoRefresh=ar;
   if (ar) {
      boolean runAgain=false;
      if (applet.clock.isRunning()) {runAgain=true; pause();}
      setBounds();
      //initializeArrays();
      recalculateColTimes();
     // findMinColTime();      //initial min colision time must be determined
      paintOSI();
      owner.repaint();
      if (runAgain) forward();
    }
  }

  /**
  *
  * Removes particle from algorithm
  * Sets empty[i] to true, so that all algorithms will skip over this slot
  *
  *
  */
  public void removeParticle(int i){
    if (i<lastpartnum+1 && i>=0){
      clearColTimes(i);  //added 7/16/99
      nump--;
      pvolume-=rad[i]*rad[i]*Math.PI;
    }
    else System.out.println("Error in removeParticle. Particle index out or range:"+i);
  }

   //changed 07/16/99
  void removeParticles(int n){
    if (n<lastpartnum+1 && n>=0){
      int i=0;
      while ((i<n+1)&&(i<lastpartnum+1)){
        if (empty[i]==false){
          clearColTimes(i);  //added 7/16/99
          nump--;
          pvolume-=rad[i]*rad[i]*Math.PI;
        }
        i++;
      }
      lastpartnum-=n;
    }
    //else System.out.println("Error in removeParticles");
  }

  //changed 07/16/99
  /*void removeParticles(int n){
    if (n<lastpartnum && n>=0){
      for (int i=lastpartnum; i>lastpartnum-n; i--){
        empty[i]=true;
        nump--;
        pvolume-=rad[i]*rad[i]*Math.PI;

      }
      lastpartnum-=n;
    }
    else System.out.println("Error in removeParticles");
  }
  */

  /**
  * If a particle that is the next Colliding particle is
  * removed from the ensemble, the collide times must be cleared.
  *
  *
  * @param i particle number to be removed
  */
  void clearColTimes(int i){
    if ((i>=0)&&(i<empty.length)){
      empty[i]=true;
      twColTimes[i]=10000;
      rwColTimes[i]=10000;
      bwColTimes[i]=10000;
      lwColTimes[i]=10000;
      for (int q=0; q<i; q++) colTimes[i][q]=10000;
      for (int p=i+1; p<lastpartnum+1; p++) colTimes[p][i]=10000;
    }
  }

  public void setParticles(int n){
     int cn=getNumParticles();
     boolean runAgain=false;
     if (applet.clock.isRunning()) {
        runAgain=true;
        pause();
     }
     if (n<=0){}
     else if (n>cn)createParticles(n-cn);
     else if (n<cn)removeParticles(cn-n);
     if (runAgain) forward();

  }

  /**
  *
  * Calculates the total kinetic energy of the system.
  *
  * @returns double kinetic energy in units
  *
  */
  public double calcKE(){
    int i;
    double ke=0;
    double vel2;
    for (i=0; i<lastpartnum+1; i++){
      if (empty[i]==false) {
        vel2=xVel[i]*xVel[i]+yVel[i]*yVel[i];
        ke+=0.5*mass[i]*vel2;
      }
    }
    return ke;
  }
/*
  public double calcPressure(){
   return 1;
  }
*/
  public double calcTemp(){
    int n=getNumParticles();
    if (n>0) return calcKE()/(n*kb);
    else return 1;
  }



  /**
  *
  * Paints off-screen image on osi, global Image object.
  *
  *
  */
  public synchronized void paintOSI(){
    int i;
    int vbwidth=10;
    int hbwidth=10;
    int w=owner.currentw;
    int h=owner.currenth;
    double r;
    if (owner.osi==null){
        return;
        //System.out.println("OSI is null");
    }
    else{
      if (periodich){
        if (periodicv) {vbwidth=0; hbwidth=0;}
        else vbwidth=bwidth; hbwidth=0;
      }
      else if (periodicv) {hbwidth=bwidth; vbwidth=0;}
      else {hbwidth=bwidth; vbwidth=bwidth;}

      Graphics osg = owner.osi.getGraphics();
      osg.setColor(Color.black);
      osg.fillRect(0,0,w,h);
      osg.setColor(bgColor);
      osg.fillRect(hbwidth,vbwidth,w-2*hbwidth,h-2*vbwidth);
      int diameter;
      if(empty!=null)  // changed by W. Christian
      for (i=0; i<lastpartnum+1; i++){
        if (empty[i]==false){
          osg.setColor(colors[i]);
          r=rad[i];
          diameter=Math.max(2,(int)(2*r*ppu));   //added by W. Christian to make sure we have a mimimum size  of 2 pixels.
          osg.fillOval(hbwidth+(int)(ppu*(xPos[i]-r)),h-vbwidth-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          if ((xPos[i]+r)>rwpos) osg.fillOval(hbwidth+(int)(ppu*(xPos[i]-r-rwpos)),h-vbwidth-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          if ((yPos[i]+r)>twpos) osg.fillOval(hbwidth+(int)(ppu*(xPos[i]-r)),h-vbwidth-(int)(ppu*(yPos[i]+r-twpos)),diameter,diameter);
          if ((yPos[i]-r)<bwpos) osg.fillOval(hbwidth+(int)(ppu*(xPos[i]-r)),h-vbwidth-(int)(ppu*(yPos[i]+r+twpos)),diameter,diameter);
          if ((xPos[i]-r)<lwpos) osg.fillOval(hbwidth+(int)(ppu*(xPos[i]-r+rwpos)),h-vbwidth-(int)(ppu*(yPos[i]+r)),diameter,diameter);
        }
      }

      if (therms[0]){
          osg.setColor(Color.red);
          for ( i=bwidth+1; i<w-bwidth-1; i+=6) osg.drawLine(i,bwidth,i,bwidth+5);
          osg.setColor(Color.blue);
          for ( i=bwidth+3; i<w-bwidth-1; i+=6) osg.drawLine(i,bwidth,i,bwidth+5);
          }
      if (therms[1]){
          osg.setColor(Color.red);
          for ( i=bwidth+1; i<h-bwidth-1; i+=6) osg.drawLine(w-bwidth-5,i,w-bwidth,i);
          osg.setColor(Color.blue);
          for ( i=bwidth+3; i<h-bwidth-1; i+=6) osg.drawLine(w-bwidth-5,i,w-bwidth,i);
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
      if (showMessage) paintMessage(osg);
      osg.dispose();

      }
  }

  public void setPpu(int p){
    if (ppu>0){this.ppu=p; pause();}
    else System.out.println("Error in setPpu:"+""+p);
  }

  /**
  *
  * Default method to add particles in random distribution.
  * Clears existing particles.
  *
  */
  public synchronized void createParticles(int n){
    if (xVel==null || owner.osi==null) setBounds();
    if (xPos==null) initializeArrays();
    boolean runAgain=false;
    if (applet.clock.isRunning()) {runAgain=true; pause();}
    if (n<0) {System.out.println("Error: createParticles("+n+")"); return; }
    if ((n+lastpartnum)>=maxp) n=maxp-lastpartnum-1;
    for (int i=0; i<n; i++){
        //addParticle(rwpos/2,twpos/2,(1-2*Math.random())*10,(1-2*Math.random())*10,dsize);
        addParticle(0,0,(1-2*Math.random())*10,(1-2*Math.random())*10,dsize);
      }
    if (autoRefresh){
       recalculateColTimes();
       //findMinColTime();      //initial min colision time must be determined
       paintOSI();
       owner.repaint();
    }

    if (runAgain) {forward();}
     //setAutoRefresh(true);
    //setAutoRefresh(false);
  }

  public void setBoltzmann(double kb){
  this.kb=Math.abs(kb);
  }

   public synchronized void setPeriodicH(boolean ph){
      this.periodich=ph;
      if (ph==false)
       {neighbor[7]=null;
        neighbor[3]=null;
        if (!periodicv){
          neighbor[0]=null;
          neighbor[2]=null;
          neighbor[4]=null;
          neighbor[6]=null;
        }
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

  public void setBorderWidth(int w){
    if (w<0) return;
    else this.bwidth=w;
  }

  public void setMaxParticles(int p){pause(); if (p>0) maxp=p; else maxp=50; initializeArrays();}
  public void setDefaultMass(double m){if (m>0) dmass=m;}
  public void setDefaultSize(double s){dsize=s;}
  public void setDefaultColor(int r,int g, int b){dcolor=new Color(r,g,b);}
  public void setDefaultTemp(double t){dtemp=t;}
  void setRWPos(double p){ rwpos=p;}
  void setLWPos(double p){ lwpos=p;}
  void setTWPos(double p){ twpos=p;}
  void setBWPos(double p){ bwpos=p;}

  public void setWallTemp(int wallnum, double t){
    if (wallnum>=0 && wallnum<4 && t>0) {
      wallTemps[wallnum]=Math.abs(t);
      therms[wallnum]=true;
    }
    else System.out.println("Error in setWallTemp");
  }

  public void removeWallTemp(int wallnum){
    if (wallnum>=0 && wallnum<4) therms[wallnum]=false;
  }

  public double getRWPos(){
    return rwpos;
  }
  public double getLWPos(){
    return lwpos;
  }
  public double getTWPos(){
    return twpos;
  }
  public double getBWPos(){
    return bwpos;
  }

  public int setHistogram(int nbins, double vmin, double vmax){
      if(histogram!=null){
          applet.removeDataSource(histogram.hashCode());
          ensembleDataSources.removeElement(histogram);
          histogram=null;
      }
      if(nbins<=0) {histogram=null; return 0;}
      histogram=new Histogram(nbins, vmin, vmax);
      ensembleDataSources.addElement(histogram);
      return   histogram.hashCode();
  }

  void removeDataSources(){
      for(Enumeration e=ensembleDataSources.elements() ; e.hasMoreElements(); ){
          SDataSource ds=(SDataSource)e.nextElement();
          applet.removeDataSource(ds.hashCode());
      }
      ensembleDataSources.removeAllElements();
      histogram=null;

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
   try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
   paintOSI();
   if (autoRefresh)owner.repaint();
  }

  public void pause(){
	  //applet.clock.stopClock();
  }

  public void forward(){
     // preRun();
      if (!applet.clock.isRunning()) applet.clock.startClock();
  }

  public synchronized int addParticleDataSource(int i){
    ParticleDataSource ds=new ParticleDataSource(i);
    ensembleDataSources.addElement(ds);
    return ds.hashCode();
  }

  public double[][] getVariables(){
       ds[0][0]=time;  //time
       ds[0][2]=(rwpos-lwpos)*(twpos-bwpos); //-pvolume;  //v
       ds[0][3]=calcTemp();  //t
       ds[0][4]=getNumParticles();  //n
       ds[0][1]=ds[0][4]*ds[0][3]/ds[0][2];  //p by ideal gas law.
       ds[0][5]=qt;
       ds[0][6]=qr;
       ds[0][7]=qb;
       ds[0][8]=ql;
       ds[0][9]=pt;
       ds[0][10]=pr;
       ds[0][11]=pb;
       ds[0][12]=pl;
       ds[0][13]=dv;
       ds[0][14]=applet.clock.getDt();
       return ds;
  }


  public String[]   getVarStrings(){return varStrings;}
  public int getID(){return hashCode();}
  public void setOwner(SApplet app){;}
  public SApplet getOwner(){return applet;}  //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"

  public double getVol(){return (rwpos-lwpos)*(twpos-bwpos);}
  public double getTemp(){return calcTemp();}
  public int getNumParticles(){
    int n=0;
    for(int i=0; i<lastpartnum+1; i++){
       if (empty[i]==false) n++;
    }
    return n;
  }

  /**
  *
  * Adds a message to small yellow box opposite coordinate display.
  *
  *
  * @param msg String
  */
  public void setMessage(String msg){
      this.showMessage=true;
      this.message=msg;
  }


  /**
   *
   * Method paints small text box on spectrum to display the wavelength at
   * a given coordinate.
   *
   * @param xcoord int x-coordinate in pixels of position on spectrum
   *
   */
   void paintMessage(Graphics g){
    int wallW=0;
    FontMetrics fm=g.getFontMetrics(g.getFont());
    int boxW=Math.max(110,10+fm.stringWidth(message));
    g.setColor(Color.yellow);
    g.fillRect(owner.currentw-boxW-3,owner.currenth-wallW-18,boxW,15);
    g.setColor(Color.black);
    g.drawString(message,owner.currentw-boxW,owner.currenth-wallW-6);
  }


  // coordinate drawing methods added by W. Christian

  void paintCoords(Graphics g){
      paintCoords(g,mouseX,mouseY);
  }

  void paintCoords( int xPix,int yPix){
        Graphics g=owner.getGraphics();
        paintCoords( g,  xPix, yPix);
        g.dispose();
  }

  void paintCoords(Graphics g, int xPix,int yPix){
        String msg=""+mouseFormat.form(xFromPix(xPix))+ " , "+mouseFormat.form(yFromPix(yPix));
        java.awt.Rectangle r = owner.getBounds();
        g.setColor(Color.yellow);
        FontMetrics fm=g.getFontMetrics(g.getFont());
        boxWidth=Math.max(20+fm.stringWidth(msg),boxWidth);
        g.fillRect(0,r.height-20,boxWidth,20);
        g.setColor(Color.black);
        g.drawString(msg,10,r.height-5);
        g.drawRect(0,r.height-20,boxWidth-1,20);
  }

  double xFromPix(int xpix){ return (xpix-owner.currentw/2)/(double)ppu;}

  double yFromPix(int ypix){ return (ypix-owner.currenth/2)/(double)ppu; }

     public boolean isEnableMouse(){return enableMouse;}

 /**
 *    Enable the graph to respond to mouse actionions.
 *
 *    @param em   Enable the coordinate display when the mouse is pressed.
 *
*/
   public void setEnableMouse(boolean em){
       if(enableMouse==em) return;
       enableMouse=em;
       if(enableMouse){
          owner.addMouseMotionListener(mouseMotionAdapter=new Ensemble_mouseMotionAdapter(this));
          owner.addMouseListener(mouseAdapter=new Ensemble_mouseAdapter(this));
      } else{
          owner.removeMouseMotionListener(mouseMotionAdapter);
          owner.removeMouseListener(mouseAdapter);
      }

   }

     void this_mousePressed(MouseEvent e) {
      if(( e.getModifiers() & InputEvent.BUTTON3_MASK)!=0){
          //  right mouse click;
      } else{   // left mouse click so paint coordinates.
          mouseX=e.getX();
          mouseY=e.getY();
          mouseDown=true;
          paintCoords(mouseX,mouseY);
      }
   }
  void this_mouseDragged(MouseEvent e){
      mouseX=e.getX();
      mouseY=e.getY();
      paintCoords(mouseX,mouseY);
  }
  void this_mouseReleased(MouseEvent e){
      mouseDown=false;
      mouseX=e.getX();
      mouseY=e.getY();
      java.awt.Rectangle r = owner.getBounds();
      owner.repaint(0,r.height-20,boxWidth,20);
      boxWidth=0;  // reset the yellow message box.
  }

  public void this_mouseEntered(MouseEvent e) {
     owner.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
  }

  public void this_mouseExited(MouseEvent e) {
     owner.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }

  public void this_mouseMoved(MouseEvent e) {
  }

  // inner class used for data connection to histogram.
  public class Histogram extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"v","n"};  // speed and number
    double[][] ds=new double[1][2];  // the datasource state variables v, n;
    int nbins=20;
    double vmin=0;
    double vmax=10;

    Histogram(int n, double min, double max){ // Constructor
       nbins=n;
       vmin=min;
       vmax=max;
       ds=new double[nbins][2];
       double dv=(vmax-vmin)/(nbins);
       double v=vmin+dv/2;
       for(int i=0; i<nbins;i++){ds[i][0]=v; v+=dv;} // set the bins
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
      double vel=0;
      double dv=(vmax-vmin)/(nbins);
      int index=0;
      for(int i=0; i<nbins;i++){ ds[i][1]=0; } // set the number of particles to zero in each bin

      for(int i=0; i<lastpartnum+1; i++){
        if (empty[i]==false) {
          vel=Math.sqrt(xVel[i]*xVel[i]+yVel[i]*yVel[i]);
          index=(int)Math.floor((vel-vmin)/dv);
          if(index>=0 && index <nbins){
              ds[index][1]++;
              //System.out.println("v="+ds[index][0]+"  N="+ds[index][1]);
          }
        }
      }
      return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return applet;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  }
  // inner class used for data connection to particles.
  public class ParticleDataSource extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"t","x","y","vx","vy","m"};
    double[][] ds=new double[1][6];  // the datasource state variables t,x,y,vx,vy,ax,ay,p;
    int index=0;

    ParticleDataSource(int i){ // Constructor
       index=i;
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
       ds[0][0]=time;  //t
       if(index>xPos.length-1){
           System.out.println("ERROR: DataSource index is large than than number of particles.");
           ds[0][1]=0;  //x
           ds[0][2]=0;  //y
           ds[0][3]=0;  //vx
           ds[0][4]=0;  //vy
           ds[0][5]=0;  //m
           return ds;
       }
       if (empty[index]){
          ds[0][1]=0;  //x
          ds[0][2]=0;  //y
          ds[0][3]=0;  //vx
          ds[0][4]=0;  //vy
          ds[0][5]=0;  //m
       }
       else {
          ds[0][1]=xPos[index]-xOrigin;  //x
          ds[0][2]=yPos[index]-yOrigin;  //y
          ds[0][3]=xVel[index];  //vx
          ds[0][4]=yVel[index];  //vy
          ds[0][5]=mass[index];  //m
       }
       return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return applet;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  }
}


class Ensemble_mouseAdapter extends java.awt.event.MouseAdapter {
  Ensemble adaptee;

  Ensemble_mouseAdapter(Ensemble adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseEntered(MouseEvent e) {
    adaptee.this_mouseEntered(e);
  }

  public void mouseExited(MouseEvent e) {
    adaptee.this_mouseExited(e);
  }

  public void mousePressed(MouseEvent e) {
    adaptee.this_mousePressed(e);
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.this_mouseReleased(e);
  }
}
class Ensemble_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  Ensemble adaptee;

  Ensemble_mouseMotionAdapter(Ensemble adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.this_mouseMoved(e);
  }
  public void mouseDragged(MouseEvent e) {
    adaptee.this_mouseDragged(e);
  }
}