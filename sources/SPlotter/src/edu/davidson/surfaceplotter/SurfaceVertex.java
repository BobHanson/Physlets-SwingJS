/*----------------------------------------------------------------------------------------*
 * SurfaceVertex.java                                                                     *
 *                                                                                        *
 * Surface Plotter   version 1.10    14 Oct 1996                                          *
 *                   version 1.20     8 Nov 1996                                          *
 *                   version 1.30b1  17 May 1997                                          *
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

import java.awt.Point;



/**
 * The class <code>SurfaceVertex</code> represents a vertex in 3D space.
 *
 * @author  Yanto Suryono
 * @version 1.30b1, 17 May 1997
 * @since   1.10  
 */

public final class SurfaceVertex {
  SurfaceCanvas surfaceCanvas;
  private Point  projection;
  private int    project_index;
  //private static Projector projector;

  //private static float zmin, zmax;
  //private  float zfactor;
  //private static int   master_project_index = 0;     // over 4 billion times to reset

  /**
   * The x coordinate
   */
  public float x;

  /**
   * The y coordinate
   */
  public float y;

  /**
   * The z coordinate
   */
  public float z;
  
  /**
   * The constructor of <code>SurfaceVertex</code>.
   * The x and y coordinated must be in normalized form, i.e: in the range -10 .. +10.
   *
   * @param ix the x coordinate
   * @param iy the y coordinate
   * @param iz the z coordinate
   */
   
  SurfaceVertex(float ix, float iy, float iz, SurfaceCanvas sc) {
    surfaceCanvas=sc;
    x = ix; y = iy; z = iz;
    project_index = sc.master_project_indexV-1;
  }
   
  /**
   * Determines whether this vertex is invalid, i.e has invalid coordinates value.
   *
   * @return <code>true</code> if this vertex is invalid
   */
   
  public final boolean isInvalid() {
    return Float.isNaN(z); 
  }

  /**
   * Gets the 2D projection of the vertex.
   *
   * @return the 2D projection
   */
   
  public final Point projection() {
    if (project_index != surfaceCanvas.master_project_indexV) {
      projection = surfaceCanvas.projector.project(x,y,(z-surfaceCanvas.zminV)*surfaceCanvas.zfactorV-10);
      project_index = surfaceCanvas.master_project_indexV;
    }
    return projection;
  }
  
  /**
   * Transforms coordinate values to fit the scaling factor of the
   * projector. This routine is only used for transforming center of projection
   * in Surface Plotter.
   */
     
  public final void transform() {
    x = x / surfaceCanvas.projector.getXScaling();
    y = y / surfaceCanvas.projector.getYScaling();
    z = (surfaceCanvas.zmaxV-surfaceCanvas.zminV)*(z/surfaceCanvas.projector.getZScaling()+10)/20 + surfaceCanvas.zminV;
  }
  
  /**
   * Invalidates all vertices. This will force the projector
   * to recalculate vertex projection.
   */
   
 // public static void invalidate() {
  //  master_project_index++;
  //}

  /**
   * Sets the projector to project this vertex.
   *
   * @param projector the projector
   */
     
 // public static void setProjector(Projector projector) {
    //SurfaceVertex.projector = projector;
 // }
  
  /**
   * Sets the minimum and maximum value of z range.
   * This values is used to compute a factor to normalized
   * z values into the range -10 .. +10.
   *
   * @param zmin the minimum z
   * @param zmax the maximum z 
   */
   
 // public static  void setZRange(float zmin, float zmax) {
    //SurfaceVertex.zmin = zmin;
    //SurfaceVertex.zmax = zmax;
    //zfactor = 20/(surfaceCanvas.zmaxV-surfaceCanvas.zminV);
  //}
}

