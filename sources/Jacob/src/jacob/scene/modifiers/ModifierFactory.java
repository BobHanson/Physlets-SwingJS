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

package jacob.scene.modifiers;

public final class ModifierFactory
{
    private ModifierFactory() {}

    public static ComponentModifier createModifier( String type ) 
        throws UnknownModifierException
    {
         type = type.toLowerCase();

         if ( type.equals( "trail" ) )
             return new Trail();
         else if ( type.equals( "rotator" ) )
             return new Rotator();
         else if ( type.equals( "translator" ) )
             return new Translator();
         else if ( type.equals( "eforce" ) )
             return new EForce();
         else if ( type.equals( "circforce" ) )
             return new CircularForce();
         else if ( type.equals( "transforce" ) )
             return new TransForce();
         else     
             throw new UnknownModifierException( type ); 
    }
}
