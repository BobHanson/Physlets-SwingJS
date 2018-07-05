package symantec.itools.awt;

import a2s.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.MediaTracker;

import java.beans.PropertyVetoException;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;

// 	03/02/97	RKM	Changed call to invalidate to repaint
//	05/31/97	RKM	Updated to support Java 1.1
//					Made properties bound & constrained
//  07/29/97    CAR marked fields transient as needed
//                  implemented readObject
//	05/31/97	RKM	Fixed bug in setImageURL, was not properly comparing objects
//  08/19/97    CAR constructor now calls super.setLayout(null)
//  08/25/97    LAB Changed all property name strings in bound and constrained messages to
//					conform to the BeanSpec naming guidelines.  Added IMAGE_NORMAL image style
//					(Addresses Mac Bug #7255).
//  08/27/97    CAR updated setImageURL to "erase" image if URL is null
//                  updated paint to draw images in itself before calling super.paint
//  09/11/97    LAB Made imageStyle protected (Addresses Mac Bug #7692).

/**
 * The ImagePanel component is similar to a regular panel except that it
 * displays an image within the panel.
 * The image to use is specified with a URL.
 */
public class ImagePanel extends Panel
{
    /**
     * A constant indicating the image is to be tiled in this panel.
     */
    public static final int IMAGE_TILED = 0;
    /**
     * A constant indicating the image is to be centered in this panel.
     */
    public static final int IMAGE_CENTERED = 1;
    /**
     * A constant indicating the image is to be scaled to fit this panel.
     */
    public static final int IMAGE_SCALED_TO_FIT = 2;
    /**
     * A constant indicating the image is to be drawn from the upper left corner of the panel.
     */
    public static final int IMAGE_NORMAL = 3;

    /**
     * Constructs a default ImagePanel. By default the image will be tiled.
     */
	public ImagePanel()
	{
	    super.setLayout(null);
		imageURL = null;
		image = null;
		imageStyle = IMAGE_TILED;

		vetos = new symantec.itools.beans.VetoableChangeSupport(this);
		changes = new symantec.itools.beans.PropertyChangeSupport(this);
	}

	// Properties

	/**
	 * Paints this component using the given graphics context.
     * This is a standard Java AWT method which typically gets called
     * by the AWT to handle painting this component. It paints this component
     * using the given graphics context. The graphics context clipping region
     * is set to the bounding rectangle of this component and its [0,0]
     * coordinate is this component's top-left corner.
     *
     * @param g the graphics context used for painting
     * @see java.awt.Component#repaint
     * @see java.awt.Component#update
	 */
	public void paint(Graphics g)
	{
		Dimension dim = size();
		if (image != null)
		{

			int imageWidth = image.getWidth(this);
			int imageHeight = image.getHeight(this);

			switch(imageStyle)
			{
				default:
				case IMAGE_TILED:
				{
					//Calculate number of images that should be drawn horizontally
					int numHImages = dim.width / imageWidth;

					//Don't forget remainders
					if (dim.width % imageWidth != 0)
						numHImages++;

					//Calculate number of images that should be drawn vertically
					int numVImages = dim.height / imageHeight;

					//Don't forget remainders
					if (dim.height % imageHeight != 0)
						numVImages++;

					int h;
					int v = 0;
					for (int vCount = 0;vCount < numVImages;vCount++)
					{
						h = 0;
						for (int hCount = 0;hCount < numHImages;hCount++)
						{
							g.drawImage(image, h, v, imageWidth, imageHeight, this);

							//Increment to next column
							h += imageWidth;
						}

						//Increment to next row
						v += imageHeight;
					}

					break;
				}

				case IMAGE_CENTERED:
				{
					g.drawImage
						(image,
						 (dim.width - imageWidth) / 2,
						 (dim.height - imageHeight) / 2,
						 imageWidth,
						 imageHeight,
						 this);

					break;
				}

				case IMAGE_SCALED_TO_FIT:
				{
					g.drawImage(image, 0, 0, dim.width, dim.height, this);

					break;
				}

				case IMAGE_NORMAL:
				{
					g.drawImage(image, 0, 0, this);

					break;
				}
			}//switch
		}
		else
		{
		    g.clearRect(0, 0, dim.width, dim.height);
		}
		super.paint(g);
	}

    /**
     * Sets the URL of the image to display in this panel.
     * @param url the URL of the image to display
     * @see #getImageURL
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     */
	public void setImageURL(URL url)
		throws PropertyVetoException
	{
		if (!symantec.itools.util.GeneralUtils.objectsEqual(imageURL,url))
		{
			vetos.fireVetoableChange("imageURL", imageURL, url);

			imageURL = url;
			if (imageURL != null)
			{
			    if(image != null)
			    {
			        image.flush();
			        image = null;
			    }
				image = getToolkit().getImage(imageURL);
				if (image != null)
				{
					MediaTracker mt = new MediaTracker(this);
					try
					{
						mt.addImage(image, 0);
						mt.waitForAll();
					}
					catch (InterruptedException ie)
					{
					}
				}
			}
			else
			{
			    if(image != null)
			    {
			        image.flush();
			        image = null;
			    }

			}

			changes.firePropertyChange("imageURL", imageURL, url);

	        repaint();
		}
	}

    /**
     * Returns the URL of the image being displayed in this panel.
     * @see #setImageURL
     */
	public URL getImageURL()
	{
		return imageURL;
	}

    /**
     * Sets the new panel image style.
     * @param newStyle the new panel image style, one of
     * IMAGE_TILED, IMAGE_CENTERED, or IMAGE_SCALED_TO_FIT
     * @exception PropertyVetoException
     * if the specified property value is unacceptable
     * @see #getStyle
     * @see #IMAGE_TILED
     * @see #IMAGE_CENTERED
     * @see #IMAGE_SCALED_TO_FIT
     * @see #IMAGE_NORMAL
     */
	public void setStyle(int newStyle) throws PropertyVetoException
	{
		if (newStyle != imageStyle)
		{
			Integer oldStyleInt = new Integer(imageStyle);
			Integer newStyleInt = new Integer(newStyle);

			vetos.fireVetoableChange("style", oldStyleInt, newStyleInt);

			imageStyle = newStyle;

	        changes.firePropertyChange("style", oldStyleInt, newStyleInt);

	        repaint();
		}
	}

    /**
     * Gets the current panel image style.
     * @return the current panel image style, one of
     * IMAGE_TILED, IMAGE_CENTERED, or IMAGE_SCALED_TO_FIT
     * @see #setStyle
     * @see #IMAGE_TILED
     * @see #IMAGE_CENTERED
     * @see #IMAGE_SCALED_TO_FIT
     * @see #IMAGE_NORMAL
     */
	public int getStyle()
	{
		return imageStyle;
	}

	// Methods

	//???RKM??? When beans come around we need to make certain that this is not returned as a property
    /**
     * Returns the image being displayed in this panel.
     */
	public Image getImage()
	{
		return image;
	}

    /**
     * Adds a listener for all event changes.
     * @param listener the listener to add.
     * @see #removePropertyChangeListener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener)
    {
    	//super.addPropertyChangeListener(listener);
    	changes.addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener for all event changes.
     * @param listener the listener to remove.
     * @see #addPropertyChangeListener
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
    {
    	//super.removePropertyChangeListener(listener);
    	changes.removePropertyChangeListener(listener);
    }

    /**
     * Adds a vetoable listener for all event changes.
     * @param listener the listener to add.
     * @see #removeVetoableChangeListener
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener listener)
    {
     	//super.addVetoableChangeListener(listener);
		vetos.addVetoableChangeListener(listener);
    }

    /**
     * Removes a vetoable listener for all event changes.
     * @param listener the listener to remove.
     * @see #addVetoableChangeListener
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener)
    {
    	//super.removeVetoableChangeListener(listener);
    	vetos.removeVetoableChangeListener(listener);
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
		in.defaultReadObject();

		if (imageURL != null) {
			image = getToolkit().getImage(imageURL);
			if (image != null) {
				MediaTracker mt = new MediaTracker(this);
				try {
					mt.addImage(image, 0);
					mt.waitForAll();
				}
				catch (InterruptedException ie) { }
			}
		}
    }

	/**
	 * The style that the image will be displayed in.
	 */
	protected int imageStyle;
	
	// Private members

	transient private Image image;
	private URL imageURL;
    private symantec.itools.beans.VetoableChangeSupport vetos;
    private symantec.itools.beans.PropertyChangeSupport changes;
}

