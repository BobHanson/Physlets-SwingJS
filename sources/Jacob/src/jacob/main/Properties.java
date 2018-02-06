//==============================================================================
//
//  Copyright (C) 2000  Vojko Valencic <Vojko.Valencic@fe.uni-lj.si>
//                      Savin Zlobec <savin@torina.fe.uni-lj.si>
//
//  This program is free software; you can redistribute it and/or
//  modify it under the terms of the GNU General Public License
//  as published by the Free Software Foundation; either version 2
//  of the License, or (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//
//==============================================================================

package jacob.main;

import jacob.property.*;


//REMIND: is jacob.main the rigth place for this class ?
public class Properties
{

//---SCENE---------------------------------------------------------------------

    public PropertyBoolean sceneRun = 
        new PropertyBoolean( "sceneRun", "Scene" ); 

    public PropertyBoolean snapToGrid = 
        new PropertyBoolean( "snapToGrid", "Scene" ); 

    public PropertyDouble gridSize = 
        new PropertyDouble( "gridSize", "Scene" ); 

    public PropertyInteger touchOffset = 
        new PropertyInteger( "touchOffset", "Scene" ); 

    public PropertyInteger animationDelay = 
        new PropertyInteger( "animationDelay", "Scene" ); 

    public PropertyDouble sceneScale = 
        new PropertyDouble( "sceneScale", "Scene", Property.EXPSPEC ); 

    public PropertyDouble vvectorScale = 
        new PropertyDouble( "vvectorScale", "Scene", Property.EXPSPEC ); 

    public PropertyDouble evectorScale = 
        new PropertyDouble( "evectorScale", "Scene", Property.EXPSPEC ); 

    public PropertyDouble mvectorScale = 
        new PropertyDouble( "mvectorScale", "Scene", Property.EXPSPEC ); 

    public PropertyDouble eforceScale = 
        new PropertyDouble( "eforceScale", "Scene", Property.EXPSPEC ); 

    public PropertyInteger trailLength = 
        new PropertyInteger( "trailLength", "Scene" ); 

    public PropertyInteger particleSize = 
        new PropertyInteger( "particleSize", "Scene" ); 

    public PropertyInteger arrowHeadSize = 
        new PropertyInteger( "arrowHeadSize", "Scene" ); 

    public PropertyInteger snapMarkSize = 
        new PropertyInteger( "snapMarkSize", "Scene" ); 

    public PropertyBoolean showRotatorPivot = 
        new PropertyBoolean( "showRotatorPivot", "Scene" ); 

    public PropertyBoolean showControls = 
        new PropertyBoolean( "showControls", "Scene" ); 

//---INTEGRATOR-----------------------------------------------------------------

    public PropertyString integratorType = 
        new PropertyString( "integratorType", "Integrator", Property.EXPSPEC ); 

    public PropertyDouble electricConst = 
        new PropertyDouble( "electricConst", "Integrator", Property.EXPSPEC ); 

    public PropertyDouble magneticConst = 
        new PropertyDouble( "magneticConst", "Integrator", Property.EXPSPEC ); 

    public PropertyDouble integrationStep = 
        new PropertyDouble( "integrationStep", "Integrator", Property.EXPSPEC );

    public PropertyDouble dipolCloudSize = 
        new PropertyDouble( "dipolCloudSize", "Integrator" ); 

    public PropertyDouble dipolChargeDiv =  
        new PropertyDouble( "dipolChargeDiv", "Integrator" ); 

    public PropertyDouble dipolSuscept =
        new PropertyDouble( "dipolSuscept", "Integrator" ); 

    public PropertyInteger transForcePeriod =
        new PropertyInteger( "transForcePeriod", "Integrator" ); 

    public PropertyDouble borderSpeedDiv =
        new PropertyDouble( "borderSpeedDiv", "Integrator" ); 

    public PropertyDouble translationConst =
        new PropertyDouble( "translationConst", "Integrator" ); 

    public PropertyDouble rotationConst =
        new PropertyDouble( "rotationConst", "Integrator" ); 

//---SYSTEM---------------------------------------------------------------------

    public PropertyBoolean doubleBuff = 
        new PropertyBoolean( "doubleBuff", "System" ); 

    public PropertyBoolean lockedRepaint = 
        new PropertyBoolean( "lockedRepaint", "System" ); 

    public PropertyInteger minThreadSleepTime = 
        new PropertyInteger( "minThreadSleepTime", "System" );

    public PropertyInteger minTimeBetweenRedraw = 
        new PropertyInteger( "minTimeBetweenRedraw", "System" );

//---COLORS---------------------------------------------------------------------

    public PropertyColor snapMarkFg = 
        new PropertyColor( "snapMarkFg", "Colors" ); 

    public PropertyColor sceneBg = 
        new PropertyColor( "sceneBg", "Colors" ); 

    public PropertyColor shapeFg = 
        new PropertyColor( "shapeFg", "Colors" ); 

    public PropertyColor elementOpenFg = 
        new PropertyColor( "elementOpenFg", "Colors" ); 

    public PropertyColor elementClosedFg = 
        new PropertyColor( "elementClosedFg", "Colors" ); 

    public PropertyColor elementBg = 
        new PropertyColor( "elementBg", "Colors" ); 

    public PropertyColor elementHl = 
        new PropertyColor( "elementHl", "Colors" ); 

    public PropertyColor particlePosFg = 
        new PropertyColor( "particlePosFg", "Colors" ); 

    public PropertyColor particlePosBg = 
        new PropertyColor( "particlePosBg", "Colors" ); 

    public PropertyColor particleNegFg = 
        new PropertyColor( "particleNegFg", "Colors" ); 

    public PropertyColor particleNegBg = 
        new PropertyColor( "particleNegBg", "Colors" ); 

    public PropertyColor particleHl = 
        new PropertyColor( "particleHl", "Colors" ); 

    public PropertyColor modifierHotspotHl = 
        new PropertyColor( "modifierHotspotHl", "Colors" ); 

    public PropertyColor trailFg = 
        new PropertyColor( "trailFg", "Colors" ); 

    public PropertyColor vvectorFg = 
        new PropertyColor( "vvectorFg", "Colors" ); 

    public PropertyColor evectorPosFg = 
        new PropertyColor( "evectorPosFg", "Colors" ); 

    public PropertyColor evectorNegFg = 
        new PropertyColor( "evectorNegFg", "Colors" ); 

    public PropertyColor mvectorFg = 
        new PropertyColor( "mvectorFg", "Colors" ); 

    public PropertyColor eforceFg = 
        new PropertyColor( "eforceFg", "Colors" ); 

    public PropertyColor circforceFg = 
        new PropertyColor( "circforceFg", "Colors" ); 

    public PropertyColor transforceFg = 
        new PropertyColor( "transforceFg", "Colors" ); 

    public PropertyColor pivotFg = 
        new PropertyColor( "pivotFg", "Colors" ); 

//---DEBUG----------------------------------------------------------------------

//---DEBUG START---

    public PropertyBoolean debugBounds = 
        new PropertyBoolean( "debugBounds", "Debug" ); 

    public PropertyBoolean debugParent = 
        new PropertyBoolean( "debugParent", "Debug" ); 

    public PropertyBoolean debugNgbr = 
        new PropertyBoolean( "debugNgbr", "Debug" ); 

    public PropertyBoolean debugName = 
        new PropertyBoolean( "debugName", "Debug" ); 

//---DEBUG END---

    Properties() {}
};
