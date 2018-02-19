package edu.davidson.graphics;

import java.awt.Dimension;
import java.awt.Insets;
import a2s.*;

public class SPanel extends Panel {
  Dimension ms=new Dimension(10,10);
  Dimension ps=new Dimension(10,10);
  int       vinset=0;
  int       hinset=0;
  public SPanel() {
  }

  public Insets getInsets(){
		    return new Insets(vinset,hinset,vinset,hinset);
  }
  public void setVinsets(int v){
        vinset=v;
        invalidate();
        if(getParent()!=null) getParent().validate();
  }
  public int getVinsets(){return vinset;}

  public void setHinsets(int h){
        hinset=h;
        invalidate();
        if(getParent()!=null) getParent().validate();
  }
  public int getHinsets(){return hinset;}

  public Dimension getMinimumSize(){ return ms;}
  public void setMinimumSize(Dimension s){ ms=s;}

  public Dimension getPreferredSize(){ return ps;}
  public void setPreferredSize(Dimension s){ ps=s;}

}