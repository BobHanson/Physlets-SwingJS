package soundOut;

import java.awt.*;
//import borland.jbcl.control.*;
//import borland.jbcl.view.BeanPanel;
import java.awt.event.*;
import edu.davidson.display.Format;

public class SoundCanvas extends Panel{
  private Format mouseFormat= new Format("%-+8.5g");
  private int numPts=0;
  private int iwidth=0;
  private int iheight=0;
  private int[] xPix;
  private int[] yPix;
  private int startDraw,drawPts;  //index for drawing
  private double[] yVec=null;
  private int c1=0,c2=0,c3;   // cursor positions
  private boolean noCursors=false;
  private boolean dragC1=true;   // flag to let us know which cursor to drag.
  private ActionListener actionListener=null;
  edu.davidson.graphics.EtchedBorder bevelPanel1 = new edu.davidson.graphics.EtchedBorder();

  public void setYVec(double[] v){
      yVec=v;
      if(yVec!=null) numPts=yVec.length; else numPts=0;
      this.setYVec(v,0,numPts-1);
  }

  public void setYVec(double[] v, int startCursor, int endCursor){
      yVec=v;
      if(yVec==null){
          numPts=0;
          c1=0;
          c2=0;
          yPix=null;
          xPix=null;
          return;
      }
      numPts=yVec.length;
      if(xPix==null || numPts!=xPix.length){
          xPix=new int[numPts];
          yPix=new int[numPts];
          c1=getSize().width/2-20;
          c2=getSize().width/2+20;
          if(c1<0) c1=0;
          if(c2>numPts-1)c2=numPts-1;
          notifyActionListeners();
      }
      startDraw=startCursor;
      drawPts=endCursor-startCursor+1;
      if(drawPts>numPts)drawPts=numPts;
      recalc();
  }

  public void setNoCursors(boolean c){
      if (noCursors==c)  return;
      noCursors=c;
      repaint();
  }

  public boolean isNoCursors(){
      return noCursors;
  }

  public double getTimeFromPix(int pix){
      if (pix<0 || pix > iwidth-1) return 0;
      else{
          double ratio=pix/(iwidth-1.0);
          return (startDraw+ratio*(drawPts-1.0))/8000.0;
      }
  }

  public void setC1(int c){
      c1=c;
      repaint();
  }
  public int getC1(){
      if (noCursors || yVec==null || iwidth<2) return 0;
      else{
          double ratio=c1/(iwidth-1.0);
          return (int)(ratio*(numPts-1));
      }
  }

  public void setC2(int c){
      c2=c;
      repaint();
  }
  public int getC2(){
      if(noCursors || yVec==null || iwidth<2) return numPts;
      else{
          double ratio=c2/(iwidth-1.0);
          return (int)(ratio*(numPts-1));
      }
  }

  public double getMin(double[] vec){
      if (vec==null || vec.length==0) return 0;
      double min=vec[1];
      for(int i=0; i<vec.length; i++){
        if (vec[i]<min) min=vec[i];
      }
      return min;
  }

  public double getMax(double[] vec){
      if (vec==null || vec.length==0) return 0;
      double max=vec[1];
      for(int i=0; i<vec.length; i++){
        if (vec[i]>max) max=vec[i];
      }
      return max;
  }

  private void recalc(){
      iwidth=getSize().width;
      iheight=getSize().height;
      double min=getMin(yVec);
      double max=getMax(yVec);
      int gut=5;
      int h=iheight-2*gut;
      if (max==min){max=max+0.5; min=min-0.5;}
      double scale=1.0*numPts/drawPts;
      for(int i=0; i<drawPts; i++){
          xPix[i]=(int)(scale*i*(iwidth-1.0)/(numPts-1.0));
          yPix[i]=gut+(int)(h*(max-yVec[startDraw+i])/(max-min));
      }
      repaint();
  }

  public void paint(Graphics g)
   {
       int i;  // loop counter
       if (getSize().width==0||getSize().height==0)return;
       if(iwidth!=getSize().width||iheight!=getSize().height){
            c1=getSize().width/2-20;
            c2=getSize().width/2+20;
            if(c1<0) c1=0;
            if(c2>numPts-1)c2=numPts-1;
            recalc();
            notifyActionListeners();
       }
       if(noCursors){
           g.setColor(new Color(255,255,200));
           g.fillRect(0,0,iwidth,iheight);
           if(g instanceof PrintGraphics) g.setColor(Color.black);
               else g.setColor(Color.red);
           g.drawPolyline(xPix,yPix,drawPts);
       }else{
           g.setColor(Color.white);
           g.fillRect(0,0,iwidth,iheight);
           g.setColor(new Color(255,255,200));
           g.fillRect(c1,0,c2-c1,iheight-1);
           if(g instanceof PrintGraphics) g.setColor(Color.black);
               else g.setColor(Color.blue);
           g.drawPolyline(xPix,yPix,drawPts);
           g.setColor(Color.red);
           g.drawLine(c1,0,c1,iheight-1);
           g.drawLine(c2,0,c2,iheight-1);
       }
       g.setColor(Color.black);
   }

  public SoundCanvas() {
    try {
      numPts=0;   // size will be set when we set data vector
      yVec=null;
      xPix=null;
      yPix=null;
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void jbInit() throws Exception{
    this.addMouseListener(new SoundCanvas_this_mouseAdapter(this));
    this.addMouseMotionListener(new SoundCanvas_this_mouseMotionAdapter(this));
  }

  void this_mouseMoved(MouseEvent e) {
    if(!noCursors) setCursor(Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
  }

  void this_mouseDragged(MouseEvent e) {
  int x=e.getX();   //   int y=e.getY();
  double t=getTimeFromPix(x);
  Graphics g=getGraphics();
  if(g!=null){
       // g.setFont(f);
        FontMetrics fm=g.getFontMetrics(g.getFont());
        String message="t= " + mouseFormat.form(t)+"s";
        g.setColor(Color.yellow);
        int w=15+fm.stringWidth("t= +000.00000 s");  // this is approx 8.5
        g.fillRect(0,iheight-15,w,15);
        g.setColor(Color.black);
        g.drawString(message, 10, iheight-2);
        if(!noCursors){
            g.setColor(Color.red);
            // g.translate(getInsets().left, getInsets().top);
            g.setXORMode(getBackground());
            g.drawLine(c3,0,c3,iheight-1);
            c3=x;
            g.drawLine(c3,0,c3,iheight-1);
        }
        g.dispose();
    }
  }

  void this_mousePressed(MouseEvent e){
     if (noCursors){
         return;
     }
     int x=e.getX();
     if(Math.abs(x-c1)<=Math.abs(x-c2) ){
         c3=c1;
         dragC1=true;
     }else{
         c3=c2;
         dragC1=false;
     }
  }

  void this_mouseReleased(MouseEvent e) {
      if (noCursors){
          repaint();
          return;
      }
      int x=e.getX();
      if(dragC1)c1=x; else c2=x;
      if(c1>c2){
        int temp=c2;
        c2=c1;
        c1=temp;
      }
      if(c1==c2)c2=c1+1;
      if (c1<0) c1=0;
      if (c1>=iwidth) c1=iwidth-1;
      if (c2<0) c2=0;
      if (c2>=iwidth) c2=iwidth-1;
      repaint();
      notifyActionListeners();
   }
  public synchronized void addActionListener(ActionListener l){
      actionListener=AWTEventMulticaster.add(actionListener,l);
  }

  public synchronized void removeActionListener(ActionListener l){
      actionListener=AWTEventMulticaster.remove(actionListener,l);
  }
  public void notifyActionListeners(){
      if(actionListener != null){
          ActionEvent e=new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"CMove");
          actionListener.actionPerformed(e);
      }
  }
}

class SoundCanvas_this_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  SoundCanvas adaptee;

  SoundCanvas_this_mouseMotionAdapter(SoundCanvas adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.this_mouseMoved(e);
  }

  public void mouseDragged(MouseEvent e) {
    adaptee.this_mouseDragged(e);
  }
}

class SoundCanvas_this_mouseAdapter extends java.awt.event.MouseAdapter {
  SoundCanvas adaptee;

  SoundCanvas_this_mouseAdapter(SoundCanvas adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.this_mousePressed(e);
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.this_mouseReleased(e);
  }
}