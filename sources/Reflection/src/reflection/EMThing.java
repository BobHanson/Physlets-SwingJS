/*
**************************************************************************
**
**                      Class  EMThing
**
**************************************************************************
**
** class EMThing extends Thing
**
** @author Jim Nolen
**
*************************************************************************/



package reflection;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import edu.davidson.tools.SUtil;



/**
*
* EMThing extends Thing and contains methods specialized for the production of
* electro-magnetic waves.
*
*
*
*/
public class EMThing extends ReflectionThing{
  //double[] leftW2 = null;

  public EMThing()  {
  }

  /**
  *
  * Constructor.
  *
  * @param n double index of refraction
  * @param w double width in units
  * @param o WavePanel owner which calls constructor
  */
  public EMThing(double n, double w, WavePanel o)  {
    super(w,o);
    indexN = n;
    wavenumber = indexN*2*Math.PI/owner.lambda;
    phaseShift=width*wavenumber;
  }

  int rescale(int leftPix){
      //leftW=null;
      //rightW=null;
      //xpoints = null;
      //ypoints = null;
      left = leftPix;
      right = (int)((pos+width)*owner.ppu);

      pwidth = right-left;
      //pwidth=owner.getWidth();
      leftW = new double[pwidth];
      //leftW2 = new double[pwidth];
      rightW = new double[pwidth];
      xpoints = new int [pwidth];
      ypoints = new int [pwidth];
      originY = (int)(0.5*owner.currenth);
      for (int i = 0; i< pwidth; i++){
          leftW[i]=0;
          //leftW2[i]=0;
          rightW[i]=0;
          xpoints[i]=0;
          ypoints[i]=0;
      }
      return right;
  }

  void calcBoundaryMatrix(ReflectionThing rightThing){
      double indexRight=1;
      if(rightThing!=null) indexRight=rightThing.indexN;
      boundaryMatrix[0][0]=(indexN+indexRight)/(2*indexN);
      boundaryMatrix[0][1]=(indexN-indexRight)/(2*indexN);
      boundaryMatrix[1][0]=(indexN-indexRight)/(2*indexN);
      boundaryMatrix[1][1]=(indexN+indexRight)/(2*indexN);
      wavenumber = indexN*2*Math.PI/owner.lambda;
      phaseShift=width*wavenumber;
      double cos=Math.cos(phaseShift);
      double sin=Math.sin(phaseShift);
      if(rightThing==null){
          rightE[0]=magRight*cos;
          rightE[1]=-magRight*sin;
          leftE[0]=0;
          leftE[1]=0;
          return;
      }
      double reRight=boundaryMatrix[0][0]*rightThing.rightE[0]+boundaryMatrix[0][1]*rightThing.leftE[0];
      double imRight=boundaryMatrix[0][0]*rightThing.rightE[1]+boundaryMatrix[0][1]*rightThing.leftE[1];
      double reLeft=boundaryMatrix[1][0]*rightThing.rightE[0]+boundaryMatrix[1][1]*rightThing.leftE[0];
      double imLeft=boundaryMatrix[1][0]*rightThing.rightE[1]+boundaryMatrix[1][1]*rightThing.leftE[1];
      // propagate the phase to the LH boundary
      rightE[0]=reRight*cos+imRight*sin;
      rightE[1]=imRight*cos-reRight*sin;
      leftE[0]=reLeft*cos-imLeft*sin;
      leftE[1]=imLeft*cos+reLeft*sin;
  }

  int calcField(ReflectionThing rightThing, int currentPix){
     // calculate the fields starting at currentPix.  Return the new currentPix.
     // fill the E field vectors at the current time.
     double ppu = owner.ppu;
     double x=0; // the position relative to the left hand side of the film.
     double cos=Math.cos(phaseShift);
     double sin=Math.sin(phaseShift);
     double amp=owner.ampScale;
     magRight=amp;
     magLeft=0;
     
     if(rightThing==null){
         magRight=amp*Math.sqrt(rightE[0]*rightE[0]+rightE[1]*rightE[1]);
         for (int i = 0; i<pwidth; i++){
            x = i/(double)(ppu);
            rightW[i]= magRight*Math.cos(wavenumber*x-owner.timePhase-phaseShift);
            leftW[i]=0;
            if(currentPix+i-pwidth+1<owner.rightWave.length){
                owner.rightWave[currentPix+i-pwidth]=rightW[i];
                owner.leftWave[currentPix+i-pwidth]=leftW[i];
            }
         }
         return currentPix-pwidth;
     }
     magRight=amp*Math.sqrt(rightE[0]*rightE[0]+rightE[1]*rightE[1]);
     double rightPhase=Math.atan2(rightE[1],rightE[0]);
     magLeft=amp*Math.sqrt(leftE[0]*leftE[0]+leftE[1]*leftE[1]);
     double leftPhase=Math.atan2(leftE[1],leftE[0]);
     for (int i = 0; i<pwidth; i++){
            x = (i)/(double)(ppu);
            rightW[i]= magRight*Math.cos(wavenumber*x-owner.timePhase + rightPhase );
            leftW[i] = magLeft*Math.cos(-wavenumber*x-owner.timePhase + leftPhase );
            if(currentPix+i-pwidth+1<owner.rightWave.length){
                owner.rightWave[currentPix+i-pwidth]=rightW[i];
                owner.leftWave[currentPix+i-pwidth]=leftW[i];
            }
     }
     return currentPix-pwidth;
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
      ReflectionThing t = owner.getLeftNeighbor((ReflectionThing)this);
      g.setColor(color);
      if(this.visibility){
        if (t==null){
            g.fillRect(left,0,pwidth,owner.currenth);
        }else if (pwidth<1){
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
     paintRightWave(g);
     paintLeftWave(g);
      //paintLeftWave2(g);
     // paintSumWave(g);
      //if (showValue) paintValue(g);
      if (showCaption) paintCaption(g);
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
      double x=0; // the position relative to the left hand side of the film.
      double cos=Math.cos(phaseShift);
      double sin=Math.sin(phaseShift);
         magRight=amp*Math.sqrt(rightE[0]*rightE[0]+rightE[1]*rightE[1]);
         for (int i = right; i<owner.rightWave.length; i++){
            x = (i-right)/(double)(ppu);
            owner.rightWave[i]=magRight*Math.cos(wavenumber*x-owner.timePhase);
            owner.leftWave[i]=0;
            if(owner.showRWave && i>0){
                int ynew= originY+(int)(owner.rightWave[i]*owner.ppu)-owner.rightPixOffset;
                int yold= originY+(int)(owner.rightWave[i-1]*owner.ppu)-owner.rightPixOffset;
                g.setColor(owner.imWaveColor);
                g.drawLine(i,ynew, i-1,yold);
            }
         }
  }



  /**
  *
  * Over-rides paintLeftWave() in Thing.class
  *
  */
  void paintLeftWave(Graphics g){
    if(!owner.showLWave)return;
    if (owner.getRightNeighbor((ReflectionThing)this)==null) return;
    for (int i=0; i<pwidth; i++){
          xpoints[i] = i+left;
          ypoints[i] = originY+(int)(leftW[i]*owner.ppu)-owner.leftPixOffset;
    }
    g.setColor(owner.reWaveColor);
    g.drawPolyline(xpoints,ypoints,pwidth);
  }

  /* void paintLeftWave2(Graphics g){
    for (int i=0; i<pwidth; i++){
          xpoints[i] = i+left;
          ypoints[i] = originY+(int)(leftW2[i]*owner.ppu);
    }
    g.setColor(Color.magenta);
    g.drawPolyline(xpoints,ypoints,pwidth);
  } */


  /**
  *
  * Over-rides paintRightWave() in Thing.class
  *
  */
  void paintRightWave(Graphics g){
    if(!owner.showRWave)return;
    for (int i=0; i<pwidth; i++){
        xpoints[i] = i+left;
        ypoints[i] = originY+(int)(rightW[i]*owner.ppu)-owner.rightPixOffset;
    }
    g.setColor(owner.imWaveColor);
    g.drawPolyline(xpoints,ypoints,pwidth);
  }

  /**
  *
  * Over-rides paintSumWave() in Thing.class
  *
  */
  void paintSumWave(Graphics g){
    for (int i=0; i<pwidth; i++){
            xpoints[i] = i+left;
            ypoints[i] = originY+vertOffset+(int)((leftW[i]+rightW[i])*owner.ppu);
    }
    g.setColor(Color.green);
    g.drawPolyline(xpoints,ypoints,pwidth);

  }

  /**
  *
  * Method calculates reflection coefficient between two media
  *
  *
  * @param t1 Thing initial medium
  * @param t2 Thing second medium
  * @return double
  */
  double calcRefCoeff(EMThing t1, EMThing t2){
      double n = t2.indexN/t1.indexN; // n = n2/n1
      double r = (1-n)/(1+n);
      return r*r;
  }

  /**
  *
  * Method calculates transmission coefficient between two media
  *
  *
  * @param t1 Thing initial medium
  * @param t2 Thing second medium
  * @return double
  */
  double calcTransCoeff(EMThing t1, EMThing t2){
      double rc = calcRefCoeff(t1,t2);
      //if (rc>1) return 0;
      double tc = Math.sqrt(1-rc*rc);
      return tc*tc;
  }



  /**
  *
  * Over-rides setIndexN() in Thing.class
  *
  */
  void setIndexN(double n){
      if (n<1) System.out.println("Cannot have index n<1 ");
      else this.indexN = n;
  }

   /**
   *
   * Method paints value of indexN or potential in bottom center of screen
   *
   * @param g Graphics
   */
   void paintValue(Graphics g){
      if (!showValue) return;
      String val="n = "+ format.form(SUtil.chop(indexN,1.0e-12));
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

}
