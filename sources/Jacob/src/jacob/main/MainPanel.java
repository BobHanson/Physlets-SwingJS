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

import java.awt.Frame;
import java.awt.Panel;
import java.awt.MenuBar;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import jacob.system.*;
import jacob.property.*;
import jacob.scene.editor.*;
import jacob.scene.*;
import jacob.lib.*;
import jacob.data.*;

public class MainPanel extends Panel 
{
    private Frame frame = null;

    private Panel controlPanel = null;

    private MenuBar frameMenuBar = null;

    private DoubleBufferPanel doubleBuff;
   
    private PropertyEditor propertyEditor;

    private Listener listener;

    private Scene scene;
 
    private PropertyMgr propertyMgr;

    private SystemMgr systemMgr;

//------------------------------------------------------------------------------
//    Constructor Methods
//------------------------------------------------------------------------------

    public MainPanel( SystemMgr systemMgr )
    {
        this.systemMgr = systemMgr;
        this.propertyMgr = systemMgr.getPropertyMgr();

        scene = new Scene( systemMgr );
        scene.setIntegrator( 
           propertyMgr.getProperties().integratorType.getValue() );

        listener = new Listener();

        setLayout( new BorderLayout() );
              
        doubleBuff = new DoubleBufferPanel( 
           propertyMgr.getProperties().doubleBuff.getValue() );
        doubleBuff.setLayout( new BorderLayout() );

        doubleBuff.add( "Center", scene );
        add( "Center", doubleBuff );
       
        initUI();
 
        if ( controlPanel != null && 
             propertyMgr.getProperties().showControls.getValue() ) 
        {
            add( "East", controlPanel );
        }

        systemMgr.getPropertyMgr().attachObserver( listener );
    }

    private void initUI()
    {
        DataElement data = systemMgr.getUIData();

        if ( data == null ) return;

        DataElement mData = data.getOptElement( "menubar" );

        if ( mData != null )
        {
            frameMenuBar = UIBuilder.buildMenuBar( systemMgr, mData, 
                                                   listener );

            scene.setPopupMenu( UIBuilder.buildPopupMenu( systemMgr, mData, 
                                                          listener ) );
        }

        DataElement cdata = data.getOptElement( "controls" );

        if ( cdata != null ) 
        {
            controlPanel = new ControlPanel( systemMgr, cdata );
        }
    }

//------------------------------------------------------------------------------
//    Scene methods   
//------------------------------------------------------------------------------

    public Scene getScene()
    {
        return scene;
    }

//------------------------------------------------------------------------------
//    Frame methods   
//------------------------------------------------------------------------------

    public void setFrame( Frame frame ) 
    {
        if ( frameMenuBar != null ) 
            frame.setMenuBar( frameMenuBar );

        frame.addWindowListener( listener );

        this.frame = frame;
    }

    public Frame getFrame()
    {
        return frame;
    }

//------------------------------------------------------------------------------
//    Data R/W methods   
//------------------------------------------------------------------------------

    public void readExperiment( String file )
    {
        InputStream is = systemMgr.getInputStream( file );

        if ( is == null )
            return;

        try
        {
            
            DataElement data = DataMgr.readXMLElement( is );
            if ( !data.getName().equals( "jacob" ) )
                throw new DataParseException( "experiment must begin with" +
                                              " jacob field" ); 

            DataElement pdata = data.getOptElement( "properties" );
            if ( pdata != null ) propertyMgr.readData( pdata );
            scene.readData( data.getElement( "scene" ) );
            is.close();
        }
        catch ( IOException ex )
        {
            SystemMgr.error( "can't load experiment: io error " +
                             "(" + ex.getMessage() + ")" );
        }
        catch ( DataParseException ex )
        {
            SystemMgr.error( "can't load experiment: data parse error " + 
                             "(" + ex.getMessage() + ")" );
            try {is.close();} catch (IOException ex2) {}
        }
    } 

    public void writeExperiment( String file )
    {
        OutputStream os = systemMgr.getOutputStream( file );

        if ( os == null )
            return;
 
        try
        {
            DataElement data = DataMgr.createXMLElement( "jacob" );
            propertyMgr.writeExpSpecData( data );
            scene.writeData( data );
            DataMgr.writeElement( os, data );   
            os.close();
        }
        catch ( IOException ex )
        {
            SystemMgr.error( "can't save experiment: io error " +
                             "(" + ex.getMessage() + ")" );
        }
    }

    public void writeParticles( String file )
    {
        OutputStream os = systemMgr.getOutputStream( file );

        if ( os == null )
            return;
 
        try
        {
            DataElement data = DataMgr.createPDElement();
            scene.writeData( data );
            DataMgr.writeElement( os, data );   
            os.close();
        }
        catch ( IOException ex )
        {
            SystemMgr.error( "can't save particles: io error " +
                             "(" + ex.getMessage() + ")" );
        }
    }

    public void readExperiment()
    {
        if ( frame == null ) return;
//FIXME: localize this
        FileDialog loadDialog = new FileDialog( MainPanel.this.frame, "Load",
                                                FileDialog.LOAD );
        loadDialog.show();
        String dir  = loadDialog.getDirectory();
        String file = loadDialog.getFile();
        
        if ( file == null || dir == null ) return;
        readExperiment( dir + file );
    }

    public void writeExperiment()
    {
        if ( frame == null ) return;
//FIXME: localize this
        FileDialog saveDialog = new FileDialog( MainPanel.this.frame, "Save",
                                                FileDialog.SAVE );
        saveDialog.show();
        String dir  = saveDialog.getDirectory();
        String file = saveDialog.getFile();
        
        if ( file == null || dir == null ) return;
        writeExperiment( dir + file );
    }

    public void writeParticles()
    {
        if ( frame == null ) return;
//FIXME: localize this
        FileDialog saveDialog = new FileDialog( MainPanel.this.frame, "Save",
                                                FileDialog.SAVE );
        saveDialog.show();
        String dir  = saveDialog.getDirectory();
        String file = saveDialog.getFile();
        
        if ( file == null || dir == null ) return;
        writeParticles( dir + file );
    }

//FIXME
    public void readScript()
    {
        if ( frame == null ) return;
//FIXME: localize this
        FileDialog loadDialog = new FileDialog( MainPanel.this.frame, "Load",
                                                FileDialog.LOAD );
        loadDialog.show();
        String dir  = loadDialog.getDirectory();
        String file = loadDialog.getFile();
 
        if ( file == null || dir == null ) return;
        new JSScriptRun( systemMgr, dir + file, 
                         new JInterface( systemMgr, this ) );
    }

//------------------------------------------------------------------------------
//    Event Listener 
//------------------------------------------------------------------------------

    private class Listener extends AWTEventAdapter
        implements PropertyObserver
    {
        public Listener() {} 

        public void actionPerformed( ActionEvent e )
        {
            String action = e.getActionCommand().trim();

            int i = action.indexOf( ":" );

            if ( i == -1 )
            {
                localAction( action );
            }
            else
            {
                String group = action.substring( 0, i );
                String gaction = action.substring( i+1 );

                try
                {
                    if ( group.equals( "Scene" ) )
                        scene.setAction( gaction );
                    else
                        SystemMgr.error( "unknown action: " + action );
                }
                catch ( MalformedActionException ex )
                {
                    SystemMgr.error( "malformed action: " + action +
                                     " (" + ex.getMessage() + ")" );
                }
                catch ( UnknownActionException ex )
                {
                    SystemMgr.error( "unknown action: " + ex.getMessage() );
                }
            }
        }

        private void localAction( String action )
        {
            if ( action.equals( "EditProperties" ) )
            {
                if ( propertyEditor == null )
                    propertyEditor = new PropertyEditor( systemMgr );
                propertyEditor.setVisible( true ); 
            }
            else if ( action.equals( "FileNew" ) )
            {  
                scene.newScene();
            }
            else if ( action.equals( "FileSave" ) )
            { 
                writeExperiment();
            }
            else if ( action.equals( "FileExportParticles" ) )
            { 
                writeParticles();
            }
            else if ( action.equals( "FileOpen" ) )
            {
                readExperiment();  
            }
            else if ( action.equals( "FileOpenScript" ) )
            {
                readScript();  
            }
            else if ( action.equals( "FileExit" ) )
            {
                systemMgr.exit( 0 );
            }
            else
            {
                SystemMgr.error( "unknown action: " + action );
            }
        }

        public void windowClosing( WindowEvent e ) 
        {
            systemMgr.exit( 0 );
        }

        public void updateProperty( UpdatePropertyEvent pe ) 
        {
//REMIND: move integrator change into Scene.java
            if ( pe.getProperties().integratorType.hasChanged() )
            {
                scene.setIntegrator( 
                    pe.getProperties().integratorType.getValue() );
            }

            if ( pe.getProperties().doubleBuff.hasChanged() )
                doubleBuff.setBuffer( 
                    pe.getProperties().doubleBuff.getValue() );

            if ( pe.getProperties().showControls.hasChanged() )
            {
                if ( pe.getProperties().showControls.getValue() )
                    MainPanel.this.add( "East", MainPanel.this.controlPanel );
                else
                    MainPanel.this.remove( MainPanel.this.controlPanel );

//REMIND: this was written in a 'try and see' way (on Linux JDK-1.1.8)
                MainPanel.this.doLayout();
                MainPanel.this.controlPanel.doLayout();
                MainPanel.this.doubleBuff.doLayout();
                MainPanel.this.doLayout();
            }       
        }
    }
}
