package ripple;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.*;



class RippleSource extends Vector
{
    int  xs,ys,time;		// source position;
    RippleSource(int x, int y)
    {
        xs=x;
        ys=y;
    }

   public void  addCrest(int t)
    {
      addElement(new RippleWaveCrest(t, xs, ys) );
    }
   public void drawCrests(int t, Graphics g, Canvas p)
   {
      RippleWaveCrest crest;
      int xo=p.getSize().width/2;
	  int yo=p.getSize().height/2;
      int len=size();	//length of vector
      g.fillOval((int)(xo+xs-2),(int)(yo-ys-2),4,4);
	  if(len>0)for(int i=0; i<len;i++)
	  {
		g.setColor(new Color(255*(len-i)/len,255*(len-i)/len,255*(len-i)/len));
		crest=(RippleWaveCrest)(elementAt(i));
		crest.draw(t, g,xo,yo);
	  }
   }

}