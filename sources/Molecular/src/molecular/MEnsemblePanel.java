

package molecular;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Enumeration;
import java.util.Vector;

import edu.davidson.tools.*;
import edu.davidson.numerics.Parser;

public class MEnsemblePanel extends EnsemblePanel implements SStepable {
  Vector  panelDataSources=new Vector();
  int nCollidingW=0;
  int nCollidingE=1;
  double time=0;
  double mint=10000;
  double temptime=10000;
  double weight=100;
  double wVel=0;
  MLEnsemble ensemble1=null;
  MREnsemble ensemble2=null;
  double wwidth=1.3;
  Parser pparser=null;
  //double cwpos=0;
  //Image osi=null;
  //SApplet owner=null;


  public MEnsemblePanel(SApplet o) {
    super(o);
    ensemble1 = new MLEnsemble(this);
    ensemble2 = new MREnsemble(this);
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



    paintOSI();
    Graphics g = this.getGraphics();
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
        for (int y=1; y<ensemble2.lastpartnum+1; y++)
            for (int x=0; x<y; x++)
              if (!ensemble2.empty[y] && !ensemble2.empty[x]){
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
    if(osi==null) return;
    Graphics osg = osi.getGraphics();
    if(osg==null) return;
    osg.setColor(ensemble1.bgColor);
    osg.fillRect(0,0,currentw,currenth);
    ensemble1.paintOSI();
    ensemble2.paintOSI();
    osg.setColor(Color.black);
    osg.drawLine((int)(ppu*cwpos),currenth,(int)(ppu*cwpos),0);
    int cwleft =(int)((cwpos-wwidth)*ppu);
    int cwright=(int)((cwpos+wwidth)*ppu);
    osg.setColor(Color.white);
    for (int i=bwidth+1; i<currenth-bwidth-1; i+=6) osg.drawLine(cwleft,i,cwright,i);
    osg.setColor(Color.black);
    for (int i=bwidth+3; i<currenth-bwidth-1; i+=6) osg.drawLine(cwleft,i,cwright,i);
    osg.dispose();
  }


  public void setDefault(){
      wVel=0;
      ensemble1.setDefault();
      ensemble2.setDefault();
  }

  public void setBounds(){
    currentw = getSize().width;
    currenth = getSize().height;
    ensemble1.setRWPos(0.5*currentw/((double)ppu));
    ensemble1.setLWPos(0);
    ensemble1.setTWPos(currenth/((double)ppu));
    ensemble1.setBWPos(0);
    ensemble1.xOrigin=(ensemble1.rwpos+ensemble1.lwpos)/2;
    ensemble1.yOrigin=ensemble1.twpos/2;
    cwpos=ensemble1.rwpos;
    ensemble1.setPeriodicV(false);
    if (ensemble1.periodicv){
       ensemble1.setNeighbor(1,ensemble1,0,ensemble1.twpos);
       ensemble1.setNeighbor(5,ensemble1,0,-ensemble1.twpos);
      }
    ensemble1.setPeriodicH(false);
    ensemble2.setLWPos(cwpos);
    ensemble2.setRWPos(currentw/((double)ppu));
    ensemble2.setTWPos(currenth/((double)ppu));
    ensemble2.setBWPos(0);
    ensemble2.xOrigin=(ensemble2.rwpos+ ensemble2.lwpos)/2;
    ensemble2.yOrigin= ensemble2.twpos/2;
    ensemble2.setPeriodicV(false);
    if ( ensemble2.periodicv){
        ensemble2.setNeighbor(1, ensemble2,0, ensemble2.twpos);
        ensemble2.setNeighbor(5, ensemble2,0,- ensemble2.twpos);
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
    }else{
    g.drawImage(osi,0,0,currentw,currenth,this);
    }
    if(showTitle) paintCaption(g);
    if(ensemble1.mouseDown) ensemble1.paintCoords(g);  // added by W. Christian
    if(ensemble2.mouseDown) ensemble2.paintCoords(g);  // added by W. Christian
  }

  public boolean setProbabilityFunction(String str){
    boolean noError=true;
    String xstr=str;
    xstr.toLowerCase().trim();
    pparser = new Parser(3);
    pparser.define(xstr);
    pparser.defineVariable(1,"m");
    pparser.defineVariable(2,"v");
    pparser.defineVariable(3,"t");
    pparser.parse();
    if(pparser.getErrorCode() != Parser.NO_ERROR){     // error checkeing added by W. Christian
         noError=false;
         System.out.println("Failed to parse P(m)): "+xstr);
         System.out.println("Parse error in MathFunction: " + pparser.getErrorString() +
                   " at function 1, position " + pparser.getErrorPosition());
      }
    return noError;}


  public MLEnsemble getEnsemble1(){
    return ensemble1;
  }

  public MREnsemble getEnsemble2(){
    return ensemble2;
  }

  public void setPistonWidth(double w){
    if (w>0) this.wwidth=w;
  }

  public void setPistonMass(double m){
    if (m>0) this.weight=m;
  }


  public MEnsemblePanel() {
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

  public synchronized int addParticleDataSourceL(int i){
    DataSourceME ds=new DataSourceME(i);
    ds.myEnsemble=ensemble1;
    panelDataSources.addElement(ds);
    return ds.hashCode();
  }

  public synchronized int addParticleDataSourceR(int i){
    DataSourceME ds=new DataSourceME(i);
    ds.myEnsemble=ensemble2;
    panelDataSources.addElement(ds);
    return ds.hashCode();
  }

  void removeDataSources(){
      for(Enumeration e=panelDataSources.elements() ; e.hasMoreElements(); ){
          SDataSource ds=(SDataSource)e.nextElement();
          owner.removeDataSource(ds.hashCode());
      }
      panelDataSources.removeAllElements();

  }

  void swapDataSourcesLR(int i, int j){  // from left to right
     for(Enumeration e=panelDataSources.elements() ; e.hasMoreElements(); ){
          DataSourceME ds=(DataSourceME)e.nextElement();
          if(ds.index==i){
              ds.index=j;
              ds.myEnsemble=ensemble2;
          }
      }
  }

  void swapDataSourcesRL(int i, int j){  // from left to right
     for(Enumeration e=panelDataSources.elements() ; e.hasMoreElements(); ){
          DataSourceME ds=(DataSourceME)e.nextElement();
          if(ds.index==i){
              ds.index=j;
              ds.myEnsemble=ensemble1;
          }
      }
  }

    // inner class used for data connection to particles.
  public class DataSourceME extends Object   implements edu.davidson.tools.SDataSource{  // inner class to access the particles as SDataSources.
    String[] varStrings= new String[]{"t","x","y","vx","vy","m"};
    double[][] ds=new double[1][6];  // the datasource state variables t,x,y,vx,vy,ax,ay,p;
    int index=0;
    Ensemble myEnsemble=null;

    DataSourceME(int i){ // Constructor
       index=i;
       try{SApplet.addDataSource(this); }catch (Exception e){e.printStackTrace();}
    }

    public double[][] getVariables(){
       ds[0][0]=time;  //t
       if(index>myEnsemble.xPos.length-1){
           System.out.println("ERROR: DataSource index is large than than number of particles.");
           ds[0][1]=0;  //x
           ds[0][2]=0;  //y
           ds[0][3]=0;  //vx
           ds[0][4]=0;  //vy
           ds[0][5]=0;  //m
           return ds;
       }
       if (myEnsemble.empty[index]){
          ds[0][1]=0;  //x
          ds[0][2]=0;  //y
          ds[0][3]=0;  //vx
          ds[0][4]=0;  //vy
          ds[0][5]=0;  //m
       }
       else {
            ds[0][1]=myEnsemble.xPos[index]-myEnsemble.xOrigin;  //x
            ds[0][2]=myEnsemble.yPos[index]-myEnsemble.yOrigin;  //y
            ds[0][3]=myEnsemble.xVel[index];  //vx
            ds[0][4]=myEnsemble.yVel[index];  //vy
            ds[0][5]=myEnsemble.mass[index];  //m
       }
       return ds;
    }
    public String[]   getVarStrings(){return varStrings;}
    public int getID(){return hashCode();}
    public void setOwner(SApplet applet){;}
    public SApplet getOwner(){return owner;}    //usually owner is an SApplet. Here, this ensemble is owned by EnsemblePanel called "owner"
  }

}
