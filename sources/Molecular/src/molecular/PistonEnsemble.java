/*
**************************************************************************
**
**                      Class  PistonEnsemble
**
**************************************************************************
**
** class PistonEnsemble extends Ensemble
**
** @author Jim Nolen and Wolfgang Christian
**
*************************************************************************/


package molecular;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import java.awt.event.*;
import edu.davidson.numerics.Parser;


public class PistonEnsemble extends Ensemble {
  
  Parser pparser=null;
  Color pColor = new Color(120,120,0);   //piston color
  Color bg2Color = Color.white;          //background color
  double dt=0.05;
  boolean dragable=false;
  boolean insidePiston=false;  //for mouse action
  boolean parsemode=true;
  int orientation=1;          //horizontal -> orientation=0, vertical-> orientation=1
  double pa=0;
  double pv=0;
  double ppos=50;
  double dppos=50;
  double twVel=0;
  double rwVel=0;
  double g=-0.8;    //gravity
  double weight=23; //piston weight
  double top;
  double y1,y2;
  double mph=5;  //minimum piston height for drag mode.
  int pwidth=20;                      //piston width
  int xold,yold;                     //initial drag coordinates
  double isoTemp = 5;                //thermal constraint temp
  boolean thermConstrain =false;     //thermal constraint on/off

  boolean willUpdate=true;  // this is a hack added by W. Christian to turn off data updates on isothermal moves unitil after the temperature is set.


  public PistonEnsemble(EnsemblePanel o){
      super(o);
      periodicv=false;
  }



  /**
  *
  *
  *
  *
  */
  public double calcColTimeTW(int i){
    if (orientation==0) return super.calcColTimeTW(i);
    else {
    //top=currenth/((double)ppu);
    double t1,t2;
    double t=10000;
    double c=twpos-yPos[i]-rad[i];
    double b=twVel-yVel[i];
    if (c<0 && b<0) return 0;  // past the wall and moving
    if(twVel>0 && yVel[i]<0) return 10000;
    double a=g/2;
    double d=b*b-4*a*c;
    if (a==0) {
        if(b>0) return 10000;  // particle is moving away from wall or slower than wall
        if(c==0)return 0;
        if(b==0) return 1000;
        t1=-c/b;
        if (t1>0) t=Math.min(t,t1);
    }
    else{
      if (d>=0) {
       t1= (-b+Math.sqrt(d))/(2*a);
       t2= (-b-Math.sqrt(d))/(2*a);
       if (t1>0) t=Math.min(t,t1);
       if (t2>0) t=Math.min(t,t2);
       }
    }
    return Math.max(0,t);
   }
  }

  public double calcColTimeRW(int i){
    if (orientation!=0) return super.calcColTimeRW(i);
    else {
    top=owner.currentw/((double)ppu);
    double t1,t2;
    double t=10000;
    double c=rwpos-xPos[i]-rad[i];
    double b=rwVel-xVel[i];
    if (c<0 && b<0) return 0;  // past the wall and moving
    if(rwVel>0 && xVel[i]<0) return 10000;
    double a=g/2;
    double d=b*b-4*a*c;
    if (a==0) {
        if(b>0) return 10000;  // particle is moving away from wall or slower than wall
        if(c==0)return 0;
        if(b==0) return 1000;
        t1=-c/b;
        if (t1>0) t=Math.min(t,t1);
    }
    else{
      if (d>=0) {
       t1= (-b+Math.sqrt(d))/(2*a);
       t2= (-b-Math.sqrt(d))/(2*a);
       if (t1>0) t=Math.min(t,t1);
       if (t2>0) t=Math.min(t,t2);
       }
    }
    return Math.max(0,t);
   }
  }



  public double advanceDT(double t){
    for(int i=0; i<lastpartnum+1; i++){
        if (empty[i]==false){
          xPos[i]+=t*xVel[i];
          yPos[i]+=t*yVel[i];
        }
    }
    if (orientation==0){
      rwpos+=rwVel*t+0.5*g*t*t;
       rwVel+=g*t;
       time+=t;
    }

    else{
       twpos+=twVel*t+0.5*g*t*t;
       twVel+=g*t;
       time+=t;
    }
    //System.out.println("g: "+g);
    //System.out.println("rwVel: "+rwVel);
    //System.out.println("rwpos: "+rwpos);
    return t;
  }

   /* public double calcKE(){
    double ke=0;
    double vel2;
    for (int i=0; i<lastpartnum+1; i++)
      if (empty[i]==false) {
        vel2=xVel[i]*xVel[i]+yVel[i]*yVel[i];
        ke+=0.5*mass[i]*vel2;
      }
    //ke+=weight+twVel*twVel*0.5;

    return ke;
  }  */

  public void collideTW(int i){
    if (orientation==0) super.collideTW(i);
    else{

    if (!parsemode){
     double a,b,c,d,v1n,v2n;
     double m1=weight;
     double v1=twVel;
     double m2=mass[i];
     double v2=yVel[i];
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


     twVel=v1n;  //Math.max(v1n,v2n);
     yVel[i]=v2n;  //Math.min(v1n,v2n);
     if (rwpos>0) pt+=Math.abs((v2-yVel[i])*mass[i]/rwpos);
      double pf=m1*v1n+m2*v2n;
      double kf=m1*v1n*v1n+m2*v2n*v2n;
    }
    if (parsemode){
     y1=yVel[i];
     yVel[i]=-y1+2*twVel;
     if (rwpos>0) pt+=Math.abs(2*y1*mass[i]/rwpos);
    }

    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    rwColTimes[i]=calcColTimeRW(i);
    for (int q=0; q<lastpartnum+1; q++) if (!empty[q]) twColTimes[q]=calcColTimeTW(q);
    for (int q=0; q<i; q++) if (!empty[q]) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) if (!empty[p]) colTimes[p][i]=calcColTime(p,i);

    }
  }

  public void collideRW(int i){
    if (orientation!=0) super.collideRW(i);
    else{

    if (!parsemode){
     double a,b,c,d,v1n,v2n;
     double m1=weight;
     double v1=rwVel;
     double m2=mass[i];
     double v2=xVel[i];
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


     rwVel=v1n;  //Math.max(v1n,v2n);
     xVel[i]=v2n;  //Math.min(v1n,v2n);
     if (twpos>0) pr+=Math.abs((v2-xVel[i])*mass[i]/twpos);
      double pf=m1*v1n+m2*v2n;
      double kf=m1*v1n*v1n+m2*v2n*v2n;
    }
    if (parsemode){
     y1=xVel[i];
     xVel[i]=-y1+2*rwVel;
     if (twpos>0) pr+=Math.abs(2*y1*mass[i]/rwpos);
    }

    bwColTimes[i]=calcColTimeBW(i);
    lwColTimes[i]=calcColTimeLW(i);
    twColTimes[i]=calcColTimeTW(i);
    for (int q=0; q<lastpartnum+1; q++) if (!empty[q]) rwColTimes[q]=calcColTimeRW(q);
    for (int q=0; q<i; q++) if (!empty[q]) colTimes[i][q]=calcColTime(i,q);
    for (int p=i+1; p<lastpartnum+1; p++) if (!empty[p]) colTimes[p][i]=calcColTime(p,i);

    }
  }

 /**
 *
 * Method controls the dragging of Piston width mouse.
 *
 * @param xnew int new xcoordinate of mouse position in pixels
 * @param ynew int new ycoordinate of mouse position in pixels
 */
  void dragPiston(int xnew,int ynew){
    double newpos;
    if (orientation==0){
      if (xnew>owner.currentw-2*bwidth) return;
      //xnew=Math.min(xnew,owner.currentw-4*bwidth);
      double xchange=(xnew-xold)/(double)ppu;
      newpos=Math.max(mph,xchange+rwpos);
      setParse(Double.toString(newpos),true);
      movePiston(newpos,1,thermConstrain);
    }
    else{
      if (ynew<2*bwidth) return;
      //ynew=Math.max(ynew,4*bwidth);
      double ychange=(yold-ynew)/(double)ppu;
      newpos=Math.max(mph,ychange+twpos);
      setParse(Double.toString(newpos),true);
      movePiston(newpos,1,thermConstrain);
    }

  }

  void movePiston(double newpos, int numsteps, boolean isothermal){
    boolean runAgain=false;
    if (applet.clock.isRunning()) {runAgain=true; pause();}
    double temp=getTemp();
    double oldtime=this.time;
    String oldstring=pparser.getFunctionString();
    double cpos=0;
    if (orientation==0) cpos=rwpos;
    else if (orientation==1) cpos=twpos;
    //double dx=(newpos-cpos)/numsteps;
    double tstep= numsteps*applet.clock.getDt();
    double v=(newpos-cpos)/tstep;
    setParse(""+cpos+"+"+"("+v+"*t"+")",parsemode);
    //System.out.println(""+cpos+"+"+"("+v+"*t"+")");
    willUpdate=false;
    for(int i=0; i<numsteps; i++){
        step(tstep/numsteps,time-tstep/numsteps);
        if(isothermal){
            //changeToTemp(temp);   //added by Jim Nolen 7/13/99
            setTemp(temp);
            thermalize(0.5);
        }
        // step did not update so we need to do this now.
        if(histogram!=null)applet.clearData(histogram.hashCode());
        if(applet!=null) applet.updateDataConnections();
        try{Thread.sleep(100);}
        catch(Exception e){;}
    }
    willUpdate=true;
    setParse(oldstring,parsemode);
    if (runAgain) forward();
  }

  public void setOrientation(String or){
     if (or.equals("horizontal")) this.orientation=0;
     else this.orientation=1;
  }

  public boolean setParse(String str, boolean pm){
    boolean runAgain=false;
    if (applet.clock.isRunning()) {runAgain=true;pause();}
    parsemode=pm;
    boolean noError=true;
    String xstr=str;
    xstr.toLowerCase().trim();
    pparser = new Parser(1);
    pparser.define(xstr);
    pparser.defineVariable(1,"t");
    pparser.parse();
    if(pparser.getErrorCode() != Parser.NO_ERROR){     // error checkeing added by W. Christian
         noError=false;
         System.out.println("Failed to parse f(t)): "+xstr);
         System.out.println("Parse error in MathFunction: " + pparser.getErrorString() +
                   " at function 1, position " + pparser.getErrorPosition());
      }
    if (runAgain) forward();
    return noError;
  }

  void fitCurveToParse(double dt){
    if (pparser==null) return;
    double x1,x2,d1,d2,b,c,d;
    dt/=2;
    if (orientation==0){
      x1=xOrigin+pparser.evaluate(time+dt);
      x2=xOrigin+pparser.evaluate(time+2*dt);
      d1=x1-(xOrigin+pparser.evaluate(time));
      d2=x2-(xOrigin+pparser.evaluate(time));
      b=dt*dt/2;
      c=dt;
      rwVel=(4*b*d1-b*d2)/(2*c*b);         //solution to two linear equations using Cramer's Rule.
      g =(d1*2*c-d2*c)/(2*c*b);
    }
    else{
      x1=yOrigin+pparser.evaluate(time+dt);
      x2=yOrigin+pparser.evaluate(time+2*dt);
      d1=x1-(yOrigin+pparser.evaluate(time));
      d2=x2-(yOrigin+pparser.evaluate(time));
      b=dt*dt/2;
      c=dt;
      twVel=(4*b*d1-b*d2)/(2*c*b);         //solution to two linear equations using Cramer's Rule.
      g =(d1*2*c-d2*c)/(2*c*b);
    }
  }


  public synchronized void paintOSI(){
    int hbwidth=10;
    int vbwidth=10;
    int w=owner.currentw;
    int h = owner.currenth;
    int i;
    double r;
    if (owner.osi==null){}
    else{
      if (periodich){
        if (periodicv) {vbwidth=0; hbwidth=0;}
        else vbwidth=bwidth; hbwidth=0;
      }
      else if (periodicv) {hbwidth=bwidth; vbwidth=0;}
      else {hbwidth=bwidth; vbwidth=bwidth;}
      Graphics osg = owner.osi.getGraphics();
      if(osg==null) return;
      osg.setColor(Color.black);
      osg.fillRect(0,0,w,h);
      osg.setColor(bgColor);
      osg.fillRect(hbwidth,vbwidth,w-2*hbwidth,h-2*vbwidth);
      osg.setColor(bg2Color);
      if (orientation==0){
        osg.fillRect(hbwidth+(int)(rwpos*ppu),vbwidth,1+(int)(w-2*hbwidth-(rwpos*ppu)),h-2*vbwidth);
        osg.setColor(pColor);
        osg.fillRect(hbwidth+(int)(rwpos*ppu),vbwidth,pwidth,h-2*vbwidth);
      }

      else{
        osg.fillRect(hbwidth,vbwidth,w-2*hbwidth,(int)(h-vbwidth-(twpos*ppu)));
        osg.setColor(pColor);
        osg.fillRect(hbwidth,(int)(h-(twpos*ppu))-pwidth,w-2*hbwidth,pwidth);
      }
      int diameter;
      for (i=0; i<lastpartnum+1; i++){
        if (empty[i]==false){
          osg.setColor(colors[i]);
          r=rad[i];
          diameter=Math.max(2,(int)(2*r*ppu));   //added by W. Christian to make sure we have a mimimum size  of 2 pixels.
          osg.fillOval(hbwidth+(int)(ppu*(xPos[i]-r)),h-vbwidth-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          if ((xPos[i]+r)>rwpos) osg.fillOval(hbwidth+(int)(ppu*(xPos[i]-r-rwpos)),h-vbwidth-(int)(ppu*(yPos[i]+r)),diameter,diameter);
          //if ((yPos[i]+r)>twpos) osg.fillOval((int)(ppu*(xPos[i]-r)),currenth-(int)(ppu*(yPos[i]+r-twpos)),(int)(2*r*ppu),(int)(2*r*ppu));
          //if ((yPos[i]-r)<bwpos) osg.fillOval((int)(ppu*(xPos[i]-r)),currenth-(int)(ppu*(yPos[i]+r+twpos)),(int)(2*r*ppu),(int)(2*r*ppu));
          if ((xPos[i]-r)<lwpos) osg.fillOval(hbwidth+(int)(ppu*(xPos[i]-r+rwpos)),h-vbwidth-(int)(ppu*(yPos[i]+r)),diameter,diameter);
        }
      }

      if (orientation==0){
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
      }
      else{
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
      }
      osg.dispose();
      }
  }

  public void setPistonMass(double m){
    this.weight=m;
    change=true;
  }

  public void setPistonWidth(int pw){
    if (pw>=1) this.pwidth=Math.min(pw,80);
    change=true;
  }

  /**
  * Method allows the piston to be dragable
  *
  * @param d boolean
  */
  public void setDragable(boolean d){
    this.dragable=d;
    if (dragable) this.setEnableMouse(true);
  }

  /**
  * Method sets minimum piston height for piston when dragging.
  * Does not apply to normal piston opperation.
  * Method protects against crushing particles and crashing program.
  *
  * @param h double height in units.
  */
  public void setMinPistonHeight(double h){
      if (h>0) mph=h;
  }

  /**
  * Method constrains the temperature of the ensemble to a fixed value.
  * Used for isothermal expansion and compression when piston is moving.
  *
  * @param temp double temperature value
  * @param tf boolean turns constraint on if true, off if false.
  */
  public void setConstrainIsothermal(boolean tf){
       //if (temp>0) isoTemp=temp;
       thermConstrain=tf;
  }

  public void setGravity(double gr){
    this.g=gr;
    change=true;
    //autoRefresh;
  }

  boolean isInsidePiston(int x,int y){
       double xp,yp;
       if (orientation==0){
          xp = lwpos+(x-bwidth)/(double)ppu;
          if ((xp>=rwpos)&&(xp<=rwpos+pwidth/(double)ppu)) insidePiston =true;
          else insidePiston=false;
       }
       else {
          yp = bwpos+(owner.currenth-y)/(double)ppu;
          if ((yp>=twpos)&&(yp<=twpos+pwidth/(double)ppu)) insidePiston =true;
          else insidePiston=false;
       }
       return insidePiston;


  }

  public void this_mouseMoved(MouseEvent e) {
     if ((dragable) && (isInsidePiston(e.getX(),e.getY())))
          owner.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     
     else  owner.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      
  }

   public void this_mouseDragged(MouseEvent e){
      //mouseX=e.getX();
      //mouseY=e.getY();
      //paintCoords(mouseX,mouseY);
      if ((dragable)&(insidePiston)){
        dragPiston(e.getX(),e.getY());
        xold=e.getX();
        yold=e.getY();
      }
  }

  public void this_mousePressed(MouseEvent e) {
      if ((dragable)&&(insidePiston)){
        if (orientation==0) setParse(Double.toString(rwpos), true);
        else setParse(Double.toString(twpos), true);
        xold=e.getX();
        yold=e.getY();
      }
   }

  public void setBounds(){
    for (int i=0; i<8; i++) neighbor[i]=null;
    owner.currentw = owner.getSize().width;
    owner.currenth = owner.getSize().height;
    if (orientation==0){
      if (!periodicv) yOrigin=((owner.currenth-2*bwidth)/((double)ppu))/2;
      else yOrigin=((owner.currenth)/((double)ppu))/2;
      xOrigin=((owner.currentw-2*bwidth)/((double)ppu))/2;
      if (parsemode) ppos=xOrigin+pparser.evaluate(time);
      else ppos=dppos+xOrigin;
      setRWPos(ppos);
      setLWPos(0);
      if (!periodicv) setTWPos((owner.currenth-2*bwidth)/((double)ppu));
      else setTWPos((owner.currenth)/((double)ppu));
      setBWPos(0);
      setPeriodicH(false);
      if (periodicv){
        setNeighbor(1,this,rwpos,0);
        setNeighbor(5,this,-rwpos,0);
      }

    }
    else{
      if (!periodich) xOrigin=((owner.currentw-2*bwidth)/((double)ppu))/2;
      else xOrigin=((owner.currentw)/((double)ppu))/2;
      yOrigin=((owner.currenth-2*bwidth)/((double)ppu))/2;
      if (parsemode) ppos=yOrigin+pparser.evaluate(time);
      else ppos=dppos+yOrigin;
      if (!periodich) setRWPos((owner.currentw-2*bwidth)/((double)ppu));
      else setRWPos((owner.currentw)/((double)ppu));
      setLWPos(0);
      setTWPos(ppos);
      setBWPos(0);
      setPeriodicV(false);
      if (periodich){
         setNeighbor(3,this,rwpos,0);
         setNeighbor(7,this,-rwpos,0);
      }
    }

    //owner.osi = owner.createImage(owner.currentw,owner.currenth);
    owner.makeImage();
  }

  /**
  *
  * Advances system by dt to remove transients.
  * System is NOT painted.
  *
  * @param dt double time step
  *
  */
  public void thermalize(double dt){
    // save the old values
    double tempTime=this.time;
    double tempTWV=twVel;
    double tempG =g;
    double tempPr=pr;
    double tempPl=pl;
    double tempPt=pt;
    double tempPb=pb;
    twVel=0;
    g=0;
    recalculateColTimes();
    int i;
    int counter=200000;
    double tl=dt;                             //tl = time left until a paint step
    do {
      if (tl<mint){
        mint-=tl;
        tl-=advanceDT(tl);
      }                                              //setTime 0 t

      else {
        counter--;
        tl-=advanceDT(mint);
        for (i=0; i<lastpartnum+1; i++){
        rwColTimes[i]-=temptime;
        lwColTimes[i]-=temptime;
        twColTimes[i]-=temptime;
        bwColTimes[i]-=temptime;
        }
        for (int y=1; y<lastpartnum+1; y++)
            for (int x=0; x<y; x++){
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

      }

    } while (tl>0 && counter>0);
    // restore the old values.
    pr=tempPr;pl=tempPl;
    pt=tempPt;pb=tempPb;
    this.time=tempTime;
    tempTWV=twVel=tempTWV;
    g= tempG;
    recalculateColTimes();
    if(counter<=0){
       System.out.println("Maximum number of time steps exceeded in thermalize.  Ensemble volume may be too small. NStep=200000");
       diagnostics();
       pause();
    }
  }

  void diagnostics(){
   System.out.println("p right="+pr);
   System.out.println("p left="+pl);
   System.out.println("p top="+pt);
   System.out.println("p bottom="+pb);
   for(int i=0; i<lastpartnum+1; i++){
       if (empty[i]==false){
           double c=rwpos-xPos[i]-rad[i];
           if(c<=0)System.out.println("right wall c<=0: particle: "+i);
           c=twpos-yPos[i]-rad[i];
           if(c==0)System.out.println("top wall c=0: particle: "+i);

           double v1=twVel;
           double v2=yVel[i];
           if((v2-v1)==0)System.out.println("top wall v= yvel: "+i);

           v1=rwVel;
           v2=xVel[i];
           if((v2-v1)==0)System.out.println("right wall v= xvel: "+i);
       }
   }
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
    double vol=getVol();
    this.time=time;
    int i;
    qt=0;qr=0;qb=0;ql=0;//divide by dt
    pr=0;pl=0;pt=0;pb=0;
    //two types of pressure. ~dp/dt and ~pv=nrt.
    //pt,pr,pb,pl    //divide at end by dt
    //subtract vol of spheres.
     if (parsemode){
        fitCurveToParse(dt);
        recalculateColTimes();
     }
    int counter=200000;
    double tl=dt;                             //tl = time left until a paint step
    do {
      if (tl<mint){
        mint-=tl;
        tl-=advanceDT(tl);
      }                                              //setTime 0 t

      else {
        counter--;
        tl-=advanceDT(mint);
        for (i=0; i<lastpartnum+1; i++){
        rwColTimes[i]-=temptime;
        lwColTimes[i]-=temptime;
        twColTimes[i]-=temptime;
        bwColTimes[i]-=temptime;
        }
        for (int y=1; y<lastpartnum+1; y++)
            for (int x=0; x<y; x++){
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

      }

    } while (tl>0 && counter>0);
    if(counter<=0){
       System.out.println("Maximum number of time steps exceeded.  Ensemble volume may be too small. NStep=100000");
       diagnostics();
       pause();
    }
    paintOSI();
    Graphics g = owner.getGraphics();
    owner.paint(g);
    g.dispose();
    pr/=dt;
    pl/=dt;
    pt/=dt;
    pb/=dt;
    dv=getVol()-vol;
    if(willUpdate){
       if(histogram!=null)applet.clearData(histogram.hashCode());
       if(applet!=null) applet.updateDataConnections();
    }
  }

  public void recalculateTWColTimes(){
     for (int i=0; i<lastpartnum+1; i++)
       if (empty[i]==false){
          twColTimes[i]=calcColTimeTW(i);
        }
  }

 public void recalculateRWColTimes(){
     for (int i=0; i<lastpartnum+1; i++)
       if (empty[i]==false){
          rwColTimes[i]=calcColTimeRW(i);
        }
  }

  public void setVolume(double vol, int numsteps, boolean isothermal){

    boolean runAgain=false;
    if (applet.clock.isRunning()) {
        runAgain=true;
        pause();
    }
    double newheight,newpos;
    double cv=(rwpos-lwpos)*(twpos-bwpos);
    if (orientation==0){
      newheight=vol/(twpos-bwpos);
      if (newheight<3){System.out.println("Volume too small"); return;}
      newpos=newheight-xOrigin;
      //applet.setPiston(""+newheight+"",true);
      setParse(""+newpos+"",true);
      movePiston(newheight, numsteps, isothermal);

    }
    else {
      newheight=vol/(rwpos-lwpos);
      if (newheight<3){System.out.println("Volume too small"); return;}
      newpos=newheight-yOrigin;
      //applet.setPiston(""+newheight+"",true);
      setParse(""+newpos+"",true);
      movePiston(newheight,numsteps, isothermal);
      //setParse(""+newheight+"",true);
    }
    if (runAgain) forward();
  }

  public void reset(){
   if (applet.clock.isRunning()) pause();
   if (orientation==0) rwVel=0;
   else twVel=0;
   time=0;
   setBounds();
   initializeArrays();
   recalculateColTimes();
   //setAutoRefresh(true);
   paintOSI();
   owner.repaint();
  }


  public void setBG2Color(int r, int g, int b){
    bg2Color= new Color(r,g,b);
  }

  public synchronized void setInitialPistonPos(double h){
    dppos=h;
    owner.osi=null;
    if(autoRefresh){
        recalculateColTimes();
        owner.repaint();
    }
  }

  public synchronized void setDefaultPiston(){
    if (orientation==0){
       setParse("5*sin(2*t)+5", parsemode);
       dppos=owner.getSize().width/(4*(double)ppu);
    }
    else  {
      setParse("5*sin(2*t)+5", parsemode);
      dppos=owner.getSize().height/(4*(double)ppu);
    }
  }



}