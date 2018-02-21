/*----------------------------------------------------------------------------------------*
 * LineAccumulator.java                                                                   *
 *                                                                                        *
 * Surface Plotter   version 1.30b1  17 May 1997                                          *
 *                                                                                        *
 * Copyright (c) 1996 Yanto Suryono. All Rights Reserved.                                 *
 *                                                                                        *
 * Permission to use, copy, and distribute this software for NON-COMMERCIAL purposes      *
 * and without fee is hereby granted, provided that this copyright notice appears in all  *
 * copies and this software is not modified in any way                                    *
 *                                                                                        *
 * Please send bug reports and/or corrections/suggestions to                              *
 * Yanto Suryono <d0771@cranesv.egg.kushiro-ct.ac.jp>                                     *
 *----------------------------------------------------------------------------------------*/
package edu.davidson.surfaceplotter;
import java.awt.Graphics;
//import java.awt.*;
import java.util.*;

/**
 * The class <code>LineAccumulator</code> accumulates line drawing information and
 * then draws all accumulated lines together. It is used as contour lines accumulator
 * in Surface Plotter. 
 *
 * @author  Yanto Suryono
 * @version 1.30b1, 17 May 1997
 * @since   1.30b1
 */
 
public class LineAccumulator {
  private Vector accumulator; 
  
  /**
   * The constructor of <code>LineAccumulator</code>
   */
   
  LineAccumulator() {
    accumulator = new Vector();
  } 
  
  /**
   * Adds a line to the accumulator. 
   *
   * @param x1 the first point's x coordinate
   * @param y1 the first point's y coordinate
   * @param x2 the second point's x coordinate
   * @param y2 the second point's y coordinate
   */
   
  public void addLine(int x1, int y1, int x2, int y2) {
    accumulator.addElement(new LineRecord(x1,y1,x2,y2));
  }
  
  /**
   * Clears accumulator.
   */
   
  public void clearAccumulator() {
    accumulator.removeAllElements();
  } 
  
  /**
   * Draws all accumulated lines.
   *
   * @param g the graphics context to draw
   */
   
  public void drawAll(Graphics g) {
    Enumeration enumeration = accumulator.elements();
    
    while (enumeration.hasMoreElements()) {
      LineRecord line = (LineRecord)(enumeration.nextElement());
      g.drawLine(line.x1,line.y1,line.x2,line.y2);
    }
  }
}

/**
 * Represents a stright line.
 * Used by <code>LineAccumulator</code> class.
 *
 * @see LineAccumulator
 */
 
class LineRecord extends Object {
  /**
   * @param x1 the first point's x coordinate
   */
  public int x1;

  /**
   * @param y1 the first point's y coordinate
   */
  public int y1;

  /**
   * @param x2 the second point's x coordinate
   */
  public int x2;

  /**
   * @param y2 the second point's y coordinate
   */
  public int y2;
  
  /**
   * The constructor of <code>LineRecord</code>
   *
   * @param x1 the first point's x coordinate
   * @param y1 the first point's y coordinate
   * @param x2 the second point's x coordinate
   * @param y2 the second point's y coordinate
   */
   
  LineRecord(int x1, int y1, int x2, int y2) {
    super();
    this.x1 = x1; this.y1 = y1;
    this.x2 = x2; this.y2 = y2;
  }
}

