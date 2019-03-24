package animator4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import edu.davidson.tools.SUtil;


public class CalcThing extends Thing {

  double chopValue=1.0e-12;
  double calcW=0.0;
  double calcH=0.0;


  public CalcThing(AnimatorCanvas o, String txt, String xStr, String yStr){
    super(o,xStr,yStr);
    label=txt;
  }

  void setChop(double value){
     chopValue=value;
  }

  public  double getH(){return calcH;}
  public  void setH(double h){
     this.calcH=h;
  }

  public  double getW(){return calcW;}
  public  void setW(double w){
     this.calcW=w;
  }

  public final String getText(){
    double val=0;
    try{
        val=SUtil.chop(calcW,chopValue);
    } catch(Exception e){}
    return label+" "+format.form(val);
  }

  public void paint(Graphics g){
      if(!visible)return;
      Font f=g.getFont();
      g.setFont(font);
      int ptX=(int)Math.round(canvas.pixFromX(vars[1]) )+xDisplayOff;
      int ptY=(int)Math.round(canvas.pixFromY(vars[2]) )-yDisplayOff;
      g.setColor(color);
      g.drawString(getText(),ptX,ptY);
      g.setColor(Color.black);
      g.setFont(f);
  }
}
