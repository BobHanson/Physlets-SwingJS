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

package jacob.property;

import java.util.Vector;

import jacob.main.Properties;

public class UpdatePropertyEvent
{
    private PropertyMgr propertyMgr;
 
    private Object source;
 
    private Vector changedProperties;


    public UpdatePropertyEvent( PropertyMgr propertyMgr,
                                Object source, 
                                Vector changedProperties )
    {
        this.propertyMgr = propertyMgr;
        this.source = source;
        this.changedProperties = changedProperties;
    }

    public Object getSource()
    {
        return source;
    }

    public PropertyMgr getPropertyMgr()
    {
        return propertyMgr;
    }

    public boolean hasChanged( String name )
    {
        return changedProperties.contains( name );
    }

    public Vector getChangedProperties()
    {
        return changedProperties;
    }

    public Properties getProperties()
    {
        return propertyMgr.getProperties();
    }
}
