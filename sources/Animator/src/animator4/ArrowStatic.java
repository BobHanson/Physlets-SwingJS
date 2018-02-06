package animator4;

import java.awt.*;

public class ArrowStatic extends Thing {
  //private double[] arrowVars= new double[12];
  private boolean insideTip=false;
  private boolean fixedlength=false;
  private double length=0;
  private boolean filled=false;
  //private double[] arrow= new double[2];
  double horz=0;
  double vert=0;
  int thickness=1;

  public ArrowStatic(AnimatorCanvas o, int s, double horz, double vert, String xStr, String yStr){

    super(o,xStr,yStr);
    varStrings= new String[]{"t","x","y","vx","vy","ax","ay","m", "horz", "vert","w","h"};
    ds=new double[1][12];  // the datasource state variables t,x,y,vx,vy,ax,ay;
    this.s=s;             // the size will be the size of the head.
    this.horz=horz;               // the h component of the arrow
    this.vert=vert;
    length=Math.sqrt(horz*horz+vert*vert);
    //resizable=false;  // for testing
  }


  double getHorz(){return horz;}

  double getVert(){return vert;}

  public final boolean isInsideThing(int xPix, int yPix){
      insideTip=false;
      int ptX=canvas.pixFromX(vars[1])+xDisplayOff;
      int ptY=canvas.pixFromY(vars[2])-yDisplayOff;
      if (!noDrag && (Math.abs(xPix-ptX)<s+1)&&(Math.abs(yPix-ptY)<s+1))return true;
      ptX= (int)(ptX+ canvas.pixPerUnit*horz);
      ptY=(int)(ptY-canvas.pixPerUnit*vert);
      if (this.resizable && (Math.abs(xPix-ptX)<s+5)&&(Math.abs(yPix-ptY)<s+5)){
          insideTip=true;
          return true;
      }else return false;
  }


  public void paint(Graphics osg){
      if(!visible)return;
      int ptX=(int)Math.round(canvas.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(canvas.pixFromY(vars[2]) )-yDisplayOff;
      double x=canvas.pixPerUnit*horz; // the x component;
      double y=canvas.pixPerUnit*vert; // the y component;
      osg.setColor(color);
      int x2= (int)(ptX+x);
      int y2= (int)(ptY-y);
     // a.addToTrail(x2,y2);
      double h=Math.sqrt(x*x+y*y);
      if(h<2){
        osg.drawLine((ptX),(ptY),x2,y2);
        return;
      }
      double w;
      if (h>3*s)w=s;  else w=h/3;
      w=Math.max(w,5);
      if(s==0){
        osg.drawLine((ptX),(ptY),x2,y2);
      }else if(thickness>1){
          edu.davidson.tools.SUtil.drawThickArrow(osg,ptX,ptY,x2,y2,(int)w, thickness);
      }else if(filled){
          edu.davidson.tools.SUtil.drawSolidArrow(osg,ptX,ptY,x2,y2,(int)w);
      }else{
          osg.drawLine((ptX),(ptY),x2,y2);
          if(h>1){
                 double u=(w*x/h);
                 double v=-(w*y/h);
                 double base_x= x2-3*u;
                 double base_y= y2-3*v;
                 osg.drawLine((int)(base_x-v),(int)(base_y+u),x2,y2);
                 osg.drawLine((int)(base_x+v),(int)(base_y-u),x2,y2);
          }
      }
      if(!noDrag){  // draw a hot spot on the base
        if(color!=Color.lightGray)osg.setColor(Color.lightGray);
        else setColor(Color.red);
        osg.fillOval(ptX-2,ptY-2,5,5);
        osg.setColor(color);
        osg.drawOval(ptX-2,ptY-2,5,5);
      }
      if(resizable){  // draw a hot spot on the tip
        if(color!=Color.lightGray)osg.setColor(Color.lightGray);
        else setColor(Color.red);
        osg.fillOval(x2-2,y2-2,5,5);
        osg.setColor(color);
        osg.drawOval(x2-2,y2-2,5,5);
      }
      if(label!=null){
        osg.setColor(Color.black);
        Font f=osg.getFont();
        osg.setFont(font);
        FontMetrics fm=osg.getFontMetrics(font);
        int off1=4+(int)((8+fm.stringWidth(label))*(-1.0+x/h)/2.0);
        int off2=(int)(-4*(y/h)+fm.getHeight()*(1.0-y/h)/4.0);
        osg.drawString(label,x2+off1,y2+off2);
        osg.setFont(f);
      }
  }

  public void paintHighlight(Graphics osg){
      if(!visible)return;
      int ptX=(int)Math.round(canvas.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(canvas.pixFromY(vars[2]) )-yDisplayOff;
      double x=canvas.pixPerUnit*horz; // the x component;
      double y=canvas.pixPerUnit*vert; // the y component;
      //osg.setColor(color);
      osg.setColor(highlightColor);
      int x2= (int)(ptX+x);
      int y2= (int)(ptY-y);
      osg.drawLine((ptX),(ptY),x2,y2);
      double h=Math.sqrt(x*x+y*y);
      if(h<2){
        return;
      }
      double w;
      if (h>3*s)w=s; else w=h/3;
      w=Math.max(w,5);
      if(h>1&& s>0){
             double u=(w*x/h);
             double v=-(w*y/h);
             double base_x= x2-3*u;
             double base_y= y2-3*v;
             osg.drawLine((int)(base_x-v),(int)(base_y+u),x2,y2);
             osg.drawLine((int)(base_x+v),(int)(base_y-u),x2,y2);
      }
      if(label!=null){
        osg.setColor(Color.black);
        Font f=osg.getFont();
        osg.setFont(font);
        FontMetrics fm=osg.getFontMetrics(font);
        int off1=4+(int)((8+fm.stringWidth(label))*(-1.0+x/h)/2.0);
        int off2=(int)(-4*(y/h)+fm.getHeight()*(1.0-y/h)/4.0);
        osg.drawString(label,x2+off1,y2+off2);
        osg.setFont(f);
      }
  }

  public double[][] getVariables(){
     ds[0][0]=vars[0];  //t
     ds[0][1]=vars[1];  //x
     ds[0][2]=vars[2];  //y
     ds[0][3]=vars[3];  //vx
     ds[0][4]=vars[4];  //vy
     ds[0][5]=vars[5];  //ax
     ds[0][6]=vars[6];  //ay
     ds[0][7]=mass;  //mass
     ds[0][8]=horz;  //horz
     ds[0][9]=vert;  //vert
     ds[0][10]=horz;  //horz
     ds[0][11]=vert;  //vert
     return ds;
  }

  public void setFilled(boolean f){filled=f;}
  public void setFixedLength(boolean f){fixedlength=f;}

  public  double getH(){return vert;}
  public  void setH(double vert){
     this.vert=vert;
  }

  public  double getW(){return horz;}
  public  void setW(double horz){
     this.horz=horz;
  }

  public void setXY(double x,double y){
      if(insideTip){
         horz=x-vars[1];
         vert=y-vars[2];
         if(fixedlength){
           double len=Math.sqrt(horz*horz+vert*vert);
           if (len==0) return;
           horz=length*horz/len;
           vert=length*vert/len;
         }
         return;
      }
      super.setXY(x,y);
  }

}