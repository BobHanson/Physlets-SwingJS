/*
**************************************************************************
**
**                      Class  QMThing
**
**************************************************************************
**
** class QMThing extends Thing
**
** @author Jim Nolen
**
*************************************************************************/


package reflection;

import java.awt.*;
import edu.davidson.tools.*;

/**
*
* QMThing extends Thing and contains methods specialized for the production of
* quantum mechanical waves.
*
*
*
*/
public class QMThing extends ReflectionThing{
   double indexRight=1;
   //double energy=0;

  public QMThing()  {
  }

  /**
  *
  * Constructor.
  *
  * @param v double potential
  * @param w double width in units
  * @param o WavePanel owner which calls constructor
  */
  public QMThing(double v, double w, WavePanel o)  {
    super(w,o);
    //varStrings = {"pos","width","n","pot","magLeft","magRight","wavelength"};
    varStrings = new String[] {"pos","width","n","pot","im","re","energy","transmission","reflection"};
    potential = v;
    energy=owner.qmEnergy;
    if (potential==energy)
          potential=energy*0.9999; //use asymptotic value in the case that E=V
                                  //this avoids a division by zero later on.
    indexN=Math.sqrt(Math.abs(potential-energy));  //this is equivalent to the wavenumber
    wavenumber=indexN;        //wavenumber "k" is used in place of indexN in order to be consistent with literature
    phaseShift=width*wavenumber;    //phase change of wave accross medium
  }

  private void calcBoundaryMatrix1(ReflectionThing rightThing){
      //E>V1, E>V2...oscilatory solution in V2
      phaseShift=width*wavenumber;
      absorption=0;
      boundaryMatrix[0][0]=(wavenumber+indexRight)/(2*wavenumber);
      boundaryMatrix[0][1]=(wavenumber-indexRight)/(2*wavenumber);
      boundaryMatrix[1][0]=(wavenumber-indexRight)/(2*wavenumber);
      boundaryMatrix[1][1]=(wavenumber+indexRight)/(2*wavenumber);
      imMatrix[0][0]=0;
      imMatrix[0][1]=0;
      imMatrix[1][0]=0;
      imMatrix[1][1]=0;
  }
  private void calcBoundaryMatrix2(ReflectionThing rightThing){
       //E<V1, E<V2...exponential solution in V1
       phaseShift=0;
       absorption=width*wavenumber;
       boundaryMatrix[0][0]=(wavenumber+indexRight)/(2*wavenumber);
       boundaryMatrix[0][1]=(wavenumber-indexRight)/(2*wavenumber);
       boundaryMatrix[1][0]=(wavenumber-indexRight)/(2*wavenumber);
       boundaryMatrix[1][1]=(wavenumber+indexRight)/(2*wavenumber);
       imMatrix[0][0]=0;
       imMatrix[0][1]=0;
       imMatrix[1][0]=0;
       imMatrix[1][1]=0;
  }
  private void calcBoundaryMatrix3(ReflectionThing rightThing){
       //E>V1, E<V2...oscilatory solution in V1
       absorption=0;
       phaseShift=width*wavenumber;
       boundaryMatrix[0][0]=0.5;
       boundaryMatrix[0][1]=0.5;
       boundaryMatrix[1][0]=0.5;
       boundaryMatrix[1][1]=0.5;
       imMatrix[0][0]=0.5*indexRight/wavenumber;
       imMatrix[0][1]=-0.5*indexRight/wavenumber;
       imMatrix[1][0]=-0.5*indexRight/wavenumber;
       imMatrix[1][1]=0.5*indexRight/wavenumber;
  }

  private void calcBoundaryMatrix4(ReflectionThing rightThing){
       //E<V1, E>V2...exponential solution in V1
       phaseShift=0;
       absorption=width*wavenumber;
       boundaryMatrix[0][0]=0.5;
       boundaryMatrix[0][1]=0.5;
       boundaryMatrix[1][0]=0.5;
       boundaryMatrix[1][1]=0.5;
       imMatrix[0][0]=-0.5*indexRight/wavenumber;
       imMatrix[0][1]=+0.5*indexRight/wavenumber;
       imMatrix[1][0]=+0.5*indexRight/wavenumber;
       imMatrix[1][1]=-0.5*indexRight/wavenumber;
  }

  /**
  *
  * Method calculates transfer Matrix (real and imaginary) for transfering wave
  * across boundary between two media.
  *
  * @param rightThing Thing right neighbor of this
  */
  void calcBoundaryMatrix(ReflectionThing rightThing){
      wavenumber = indexN;
      phaseShift=width*wavenumber;
      absorption=width*wavenumber;
       // indexN*2*Math.PI/owner.lambda;
       if(rightThing!=null) {
        indexRight=rightThing.wavenumber;
        if(potential<energy && rightThing.potential<=energy){
         calcBoundaryMatrix1(rightThing);
        }else if(potential>energy &&  rightThing.potential>=energy){
          calcBoundaryMatrix2(rightThing);
        }else if(potential<energy &&  rightThing.potential>=energy){
          calcBoundaryMatrix3(rightThing);
        }else if(potential>energy &&  rightThing.potential<=energy){
          calcBoundaryMatrix4(rightThing);
        }
        //else if (potential==energy)
        //      calcBoundaryMatrix1(rightThing);
      }
      else {
        indexRight=wavenumber;
        if(potential>energy) {
          phaseShift=0;
          absorption=wavenumber*width;
        }
        else{
          phaseShift=wavenumber*width;
          absorption=0;
        }
      }
      double cos=Math.cos(phaseShift);
      double sin=Math.sin(phaseShift);
      if(rightThing==null){
          rightE[0]=magRight*cos*Math.exp(absorption);  //real part of right traveling wave at left boundary
          rightE[1]=-magRight*sin*Math.exp(absorption); //imaginary part of right traveling wave at left boundary
          leftE[0]=0;   //real part of left traveling wave  at left boundary
          leftE[1]=0;   //imaginary part of left traveling wave at left boundary
          return;
      }
      //transfer wave accross boundary
      double reRight=boundaryMatrix[0][0]*rightThing.rightE[0]+boundaryMatrix[0][1]*rightThing.leftE[0]
                     -imMatrix[0][0]*rightThing.rightE[1]-imMatrix[0][1]*rightThing.leftE[1];
      double imRight=boundaryMatrix[0][0]*rightThing.rightE[1]+boundaryMatrix[0][1]*rightThing.leftE[1]
                     +imMatrix[0][0]*rightThing.rightE[0]+imMatrix[0][1]*rightThing.leftE[0];
      double reLeft=boundaryMatrix[1][0]*rightThing.rightE[0]+boundaryMatrix[1][1]*rightThing.leftE[0]
                    -imMatrix[1][0]*rightThing.rightE[1]-imMatrix[1][1]*rightThing.leftE[1];
      double imLeft=boundaryMatrix[1][0]*rightThing.rightE[1]+boundaryMatrix[1][1]*rightThing.leftE[1]
                    +imMatrix[1][0]*rightThing.rightE[0]+imMatrix[1][1]*rightThing.leftE[0];

      //propagate the phase to the LH boundary

      //real part of right traveling wave at left boundary
      rightE[0]=reRight*cos*Math.exp(absorption)+imRight*sin*Math.exp(absorption);
      //imaginary part of right traveling wave at left boundary
      rightE[1]=imRight*cos*Math.exp(absorption)-reRight*sin*Math.exp(absorption);
      //real part of left traveling wave  at left boundary
      leftE[0]=reLeft*cos*Math.exp(-absorption)-imLeft*sin*Math.exp(-absorption);
       //imaginary part of left traveling wave at left boundary
      leftE[1]=imLeft*cos*Math.exp(-absorption)+reLeft*sin*Math.exp(-absorption);

  }

  int calcField(ReflectionThing rightThing, int currentPix){
     // calculate the fields starting at currentPix.  Return the new currentPix.
     // fill the E field vectors at the current time.
     double ppu = owner.ppu;
     double x=0; // the position relative to the left hand side of the film.
     double cos=Math.cos(phaseShift);
     double sin=Math.sin(phaseShift);
     double amp=owner.ampScale;
     double rightPhase,leftPhase;
     double test;
     magRight=amp;
     magLeft=0;
     if(rightThing==null){
         magRight=amp*Math.sqrt(rightE[0]*rightE[0]+rightE[1]*rightE[1]);   // |Psi|
         rightPhase=Math.atan2(rightE[1],rightE[0]);
         for (int i = 0; i<pwidth; i++){
            x = i/(double)(ppu);

            //rightW[] is the REAL part of Psi.
            //leftW[] is the IMAGINARY part of Psi.

            if (energy>potential){
               rightW[i]=magRight*Math.cos(wavenumber*x+rightPhase-owner.timePhase);
               leftW[i]=magRight*Math.sin(wavenumber*x+rightPhase-owner.timePhase);
            }
            else{
               rightW[i]=magRight*Math.exp(-wavenumber*x)*Math.cos(rightPhase-owner.timePhase);
               leftW[i]=magRight*Math.exp(-wavenumber*x)*Math.sin(rightPhase-owner.timePhase);
            }
            if(currentPix+i-pwidth+1<owner.rightWave.length){
                owner.rightWave[currentPix+i-pwidth]=rightW[i];
                owner.leftWave[currentPix+i-pwidth]=leftW[i];
            }
         }
         return currentPix-pwidth;
     }
     magRight=amp*Math.sqrt(rightE[0]*rightE[0]+rightE[1]*rightE[1]);
     rightPhase=Math.atan2(rightE[1],rightE[0]);   //phase of right-travelling wave at left boundary
     magLeft=amp*Math.sqrt(leftE[0]*leftE[0]+leftE[1]*leftE[1]);
     leftPhase=Math.atan2(leftE[1],leftE[0]);    //phase of left-travelling wave at left boundary

     for (int i = 0; i<pwidth; i++){
            x = (i)/(double)(ppu);

            //rightW[] is the REAL part of Psi.
            //leftW[] is the IMAGINARY part of Psi.

            if (energy>potential){
            rightW[i]= magRight*Math.cos(wavenumber*x+rightPhase-owner.timePhase)+
                magLeft*Math.cos(-wavenumber*x + leftPhase-owner.timePhase);
            leftW[i] = magRight*Math.sin(wavenumber*x+rightPhase-owner.timePhase)+
                magLeft*Math.sin(-wavenumber*x+leftPhase-owner.timePhase);
            }
            else {
             rightW[i]= magRight*Math.exp(-wavenumber*x)*Math.cos(rightPhase-owner.timePhase)+
                  magLeft*Math.exp(wavenumber*x)*Math.cos(leftPhase-owner.timePhase);
             leftW[i] = magRight*Math.exp(-wavenumber*x)*Math.sin(rightPhase-owner.timePhase)+
                 magLeft*Math.exp(wavenumber*x)*Math.sin(leftPhase-owner.timePhase);
            }
            if(currentPix+i-pwidth+1<owner.rightWave.length){
                owner.rightWave[currentPix+i-pwidth]=rightW[i];
                owner.leftWave[currentPix+i-pwidth]=leftW[i];
            }
     }
     return currentPix-pwidth;
  }

  /**
  *
  * Method to paint from the last thing to the right of the canvas.
  *
  * @param g Graphics
  *
  */
  void fillToEnd(Graphics g){
      double amp=owner.ampScale;
      double ppu = owner.ppu;
      double x=0; // the position relative to the left hand side of the medium.
      double cos=Math.cos(phaseShift);
      double sin=Math.sin(phaseShift);
      magRight=amp*Math.sqrt(rightE[0]*rightE[0]+rightE[1]*rightE[1]);   // |Psi|
      double rightPhase=Math.atan2(rightE[1],rightE[0]);
      for (int i = right; i<owner.rightWave.length; i++){
            x = (i-right)/(double)(ppu);
            if (potential>energy){
              owner.rightWave[i]=magRight*Math.exp(-wavenumber*x-absorption)*Math.cos(rightPhase-owner.timePhase);
              owner.leftWave[i]=magRight*Math.exp(-wavenumber*x-absorption)*Math.sin(rightPhase-owner.timePhase);
               //rightW[i]=magRight*Math.exp(-wavenumber*x)*Math.cos(rightPhase);
               //leftW[i]=magRight*Math.exp(-wavenumber*x)*Math.sin(rightPhase);

            }else{
              owner.rightWave[i]=magRight*Math.cos(wavenumber*x+rightPhase+phaseShift-owner.timePhase);
              owner.leftWave[i]=magRight*Math.sin(wavenumber*x+rightPhase+phaseShift-owner.timePhase);
            }
            if(!owner.showPhaseColor && owner.showRWave){
                int ynew= (int)( owner.rightWave[i]*ppu)+ owner.currenth/2-vertOffset;
                int yold= (int)( owner.rightWave[i-1]*ppu)+ owner.currenth/2-vertOffset;
                g.setColor(owner.imWaveColor);
                g.drawLine(i,ynew, i-1,yold);
            }
            if(!owner.showPhaseColor && owner.showLWave){
                int ynew= (int)( owner.leftWave[i]*ppu)+ owner.currenth/2;
                int yold= (int)( owner.leftWave[i-1]*ppu)+ owner.currenth/2;
                g.setColor(owner.reWaveColor);
                g.drawLine(i,ynew, i-1,yold);
            }

      }

  }

  /**
  *
  * Method directs thing to paint itself on the owner's canvas
  * Over-rides painOS() in Thing.class
  *
  * @param g Graphics
  *
  */
  void paintOS(Graphics g){
      ReflectionThing t = owner.getLeftNeighbor(this);
      g.setColor(color);
      if(this.visibility){
        if (t==null)g.fillRect(left,0,pwidth,owner.currenth);
        else if (pwidth<1){
          if (t.pwidth<1)
            g.fillRect(left+1,0,1,owner.currenth);
          else
            g.fillRect(left,0,1,owner.currenth);
        }
        else {
            if (t.pwidth<1)
              g.fillRect(left+1,0,pwidth-1,owner.currenth);
            else
              g.fillRect(left,0,pwidth,owner.currenth);
        }
    }
     if(!owner.showPhaseColor && owner.showRWave) paintRightWave(g);
     if(!owner.showPhaseColor && owner.showLWave) paintLeftWave(g);
      //paintLeftWave2(g);
      //paintSumWave(g);
      //if (showValue) paintValue(g);
      if (showCaption) paintCaption(g);
  }

   /**
   *
   * Method paints value of indexN or potential in bottem center of screen
   *
   * @param g Graphics
   */
   void paintValue(Graphics g){
      if (!showValue) return;
      String val="V = " + format.form(SUtil.chop(potential,1.0e-12));
      Font f=g.getFont();
      if(font!=null){
        g.setFont(font);
      }
      FontMetrics fm=g.getFontMetrics(g.getFont());
      int boxW=fm.stringWidth(val);
      g.setColor(Color.black);
      g.drawString(val,left+(int)(0.5*(pwidth-boxW)),(int)(0.9*owner.currenth));
      g.setFont(f);
   }

   /**
  *
  * Used in data connections
  *
  * @return double[][] array of variables for data connections.
  */
  public double[][] getVariables(){
          vars[0][0]=pos;
          vars[0][1]=width;
          vars[0][2]=indexN;
          vars[0][3]=potential;
          vars[0][4]=magLeft;
          vars[0][5]=magRight;
          vars[0][6]=owner.qmEnergy;
          vars[0][8]=owner.refC*owner.refC;
          vars[0][7]=1-vars[0][8];
      return vars;
 }



}
