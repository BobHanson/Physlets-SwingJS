/*
 * Copyright (c) 1997-1999 Inprise Corporation. All Rights Reserved.
 *
 * This SOURCE CODE FILE, which has been provided by Inprise as part
 * of an Inprise product for use ONLY by licensed users of the product,
 * includes CONFIDENTIAL and PROPRIETARY information of Inprise.
 *
 * USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS
 * OF THE LICENSE STATEMENT AND LIMITED WARRANTY FURNISHED WITH
 * THE PRODUCT.
 *
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD INPRISE, ITS RELATED
 * COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY CLAIMS
 * OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR DISTRIBUTION
 * OF YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES ARISING OUT OF
 * OR RESULTING FROM THE USE, MODIFICATION, OR DISTRIBUTION OF PROGRAMS
 * OR FILES CREATED FROM, BASED ON, AND/OR DERIVED FROM THIS SOURCE
 * CODE FILE.
 */
//--------------------------------------------------------------------------------------------------
// Copyright (c) 1996-1999 Inprise Corporation. All Rights Reserved.
//--------------------------------------------------------------------------------------------------

//package com.borland.jbcl.layout;
package mathapps;

import java.awt.*;

/**
 * VFlowLayout is similar to FlowLayout except it lays out components
 * vertically. Extends FlowLayout because it mimics much of the
 * behavior of the FlowLayout class, except vertically. An additional
 * feature is that you can specify a fill to edge flag, which causes
 * the VerticalFlowLayout manager to resize all components to expand to the
 * column width Warning: This causes problems when the main panel
 * has less space that it needs and it seems to prohibit multi-column
 * output. Additionally there is a vertical fill flag, which fills the last
 * component to the remaining height of the container.
 */
public class VerticalFlowLayout extends FlowLayout implements java.io.Serializable {

  public static final int TOP   = 0;
  public static final int MIDDLE        = 1;
  public static final int BOTTOM        = 2;

  //int align;
  int hgap;
  int vgap;
  boolean hfill;
  boolean vfill;

  /**
   * Construct a new VerticalFlowLayout with a middle alignemnt, and
   * the fill to edge flag set.
   */
  public VerticalFlowLayout() {
    this(TOP, 5, 5, true, false);
  }

  /**
   * Construct a new VerticalFlowLayout with a middle alignemnt.
   * @param fill the fill to edge flag
   */
  public VerticalFlowLayout(boolean hfill, boolean vfill){
    this(TOP, 5, 5, hfill, vfill);
  }

  /**
   * Construct a new VerticalFlowLayout with a middle alignemnt.
   * @param align the alignment value
   */
  public VerticalFlowLayout(int align) {
    this(align, 5, 5, true, false);
  }

  /**
   * Construct a new VerticalFlowLayout.
   * @param align the alignment value
   * @param fill the fill to edge flag
   */
  public VerticalFlowLayout(int align, boolean hfill, boolean vfill) {
    this(align, 5, 5, hfill, vfill);
  }

  /**
   * Construct a new VerticalFlowLayout.
   * @param align the alignment value
   * @param hgap the horizontal gap variable
   * @param vgap the vertical gap variable
   * @param fill the fill to edge flag
   */
  public VerticalFlowLayout(int align, int hgap, int vgap, boolean hfill, boolean vfill) {
    setAlignment(align);
    this.hgap = hgap;
    this.vgap = vgap;
    this.hfill = hfill;
    this.vfill = vfill;
  }

  /**
     * Gets the horizontal gap between components.
     * @return     the horizontal gap between components.
     * @see        java.awt.FlowLayout#setHgap
     * @since      JDK1.1
     */
  public int getHgap() {
    return hgap;
  }

  /**
   * Sets the horizontal gap between components.
   */
  public void setHgap(int hgap) {
    super.setHgap(hgap);
    this.hgap = hgap;
  }

  /**
   * Gets the vertical gap between components.
   */
  public int getVgap() {
    return vgap;
  }

  /**
   * Sets the vertical gap between components.
   */
  public void setVgap(int vgap) {
    super.setVgap(vgap);
    this.vgap = vgap;
  }

  /**
   * Returns the preferred dimensions given the components
   * in the target container.
   * @param target the component to lay out
   */
  public Dimension preferredLayoutSize(Container target) {
    Dimension tarsiz = new Dimension(0, 0);

    for (int i = 0 ; i < target.getComponentCount(); i++) {
      Component m = target.getComponent(i);
      if (m.isVisible()) {
        Dimension d = m.getPreferredSize();
        tarsiz.width = Math.max(tarsiz.width, d.width);
        if (i > 0) {
          tarsiz.height += vgap;
        }
        tarsiz.height += d.height;
      }
    }
    Insets insets = target.getInsets();
    tarsiz.width += insets.left + insets.right + hgap*2;
    tarsiz.height += insets.top + insets.bottom + vgap*2;
    return tarsiz;
  }

  /**
   * Returns the minimum size needed to layout the target container
   * @param target the component to lay out
   */
  public Dimension minimumLayoutSize(Container target) {
    Dimension tarsiz = new Dimension(0, 0);

    for (int i = 0 ; i < target.getComponentCount(); i++) {
      Component m = target.getComponent(i);
      if (m.isVisible()) {
          Dimension d = m.getMinimumSize();
          tarsiz.width = Math.max(tarsiz.width, d.width);
          if (i > 0) {
            tarsiz.height += vgap;
          }
          tarsiz.height += d.height;
      }
    }
    Insets insets = target.getInsets();
    tarsiz.width += insets.left + insets.right + hgap*2;
    tarsiz.height += insets.top + insets.bottom + vgap*2;
    return tarsiz;
  }

  public void setVerticalFill(boolean vfill) {
    this.vfill = vfill;
  }

  public boolean getVerticalFill() {
    return vfill;
  }

  public void setHorizontalFill(boolean hfill) {
    this.hfill = hfill;
  }

  public boolean getHorizontalFill() {
    return hfill;
  }

  /**
         * places the components defined by first to last within the target
         * container using the bounds box defined
   * @param target the container
   * @param x the x coordinate of the area
   * @param y the y coordinate of the area
   * @param width the width of the area
   * @param height the height of the area
   * @param first the first component of the container to place
   * @param last the last component of the container to place
   */
  private void placethem(Container target, int x, int y, int width, int height,
                                       int first, int last) {
    int align = getAlignment();
    //if ( align == this.TOP )
    //  y = 0;
    Insets insets = target.getInsets();
    if ( align == VerticalFlowLayout.MIDDLE )
      y += height  / 2;
    if ( align == VerticalFlowLayout.BOTTOM )
      y += height;

    for (int i = first ; i < last ; i++) {
      Component m = target.getComponent(i);
        Dimension md = m.getSize();
      if (m.isVisible()) {
        int px = x + (width-md.width)/2;
        m.setLocation(px, y);
        y += vgap + md.height;
      }
    }
  }

  /**
   * Lays out the container.
   * @param target the container to lay out.
   */
  public void layoutContainer(Container target) {
    Insets insets = target.getInsets();
    int maxheight = target.getSize().height - (insets.top + insets.bottom + vgap*2);
    int maxwidth = target.getSize().width - (insets.left + insets.right + hgap*2);
    int numcomp = target.getComponentCount();
    int x = insets.left + hgap;
    int y = 0  ;
    int colw = 0, start = 0;

    for (int i = 0 ; i < numcomp ; i++) {
      Component m = target.getComponent(i);
      if (m.isVisible()) {
        Dimension d = m.getPreferredSize();
        // fit last component to remaining height
        if ((this.vfill) && (i == (numcomp-1))) {
          d.height = Math.max((maxheight - y), m.getPreferredSize().height);
        }

        // fit componenent size to container width
        if ( this.hfill ) {
          m.setSize(maxwidth, d.height);
          d.width = maxwidth;
        }
        else {
          m.setSize(d.width, d.height);
        }

        if ( y  + d.height > maxheight ) {
          placethem(target, x, insets.top + vgap, colw, maxheight-y, start, i);
          y = d.height;
          x += hgap + colw;
          colw = d.width;
          start = i;
        }
        else {
          if ( y > 0 )
            y += vgap;
           y += d.height;
           colw = Math.max(colw, d.width);
        }
      }
    }
    placethem(target, x, insets.top + vgap, colw, maxheight - y, start, numcomp);
  }
}
