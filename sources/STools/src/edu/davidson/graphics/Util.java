package edu.davidson.graphics;

import edu.davidson.tools.SClock;
import javajs.async.Assets;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
//import java.applet.Applet;
//import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;

/**
 * A handy collection of methods for getting a component's
 * frame, getting a component's applet, waiting for a
 * component's image, and wallpapering a components background.
 * <p>
 *
 * @version 1.0, Apr 1 1996
 * @author  David Geary & Wolfgang Christian
 */
public class Util {
	
	public static boolean isJS = /** @j2sNative true || */ false;

	static {

		Assets.add(new Assets.Asset("physlets", "physlet-assets.zip",
				new String[] { "opticsimages", "images", "circuitimages", "galtonimages" }));
		Assets.add(isJS ? Assets.jsutil.getAppletInfo("assets") : null);

	}
	public static Dialog getDialog(Component c) {
        if(c instanceof Dialog)
            return (Dialog)c;

        while((c = c.getParent()) != null) {
            if(c instanceof Dialog)
                return (Dialog)c;
		}
		return null;
	}
    public static Frame getFrame(Component c) {
        if(c instanceof Frame)
            return (Frame)c;

        while((c = c.getParent()) != null) {
            if(c instanceof Frame)
                return (Frame)c;
        }
        return null;
    }
    public static Applet getApplet(Component c) {
        if(c instanceof Applet)
            return (Applet)c;

        while((c = c.getParent()) != null) {
            if(c instanceof Applet)
                return (Applet)c;
        }
        return null;
    }
    public static void waitForImage(Component component, Image image) {
        MediaTracker tracker = new MediaTracker(component);
        try {
            tracker.addImage(image, 0);
            tracker.waitForID(0,10000);  // max 10 second wait
        }
        catch(InterruptedException e) { e.printStackTrace(); }
    }
    public static void wallPaper(Component component,
                                 Graphics  g,
                                 Image     image) {
        Dimension compsize = component.getSize();
        Util.waitForImage(component, image);

        int patchW = image.getWidth(component);
        int patchH = image.getHeight(component);

        Assert.notFalse(patchW != -1 && patchH != -1);

        for(int r=0; r < compsize.width; r += patchW) {
            for(int c=0; c < compsize.height; c += patchH)
            g.drawImage(image, r, c, component);
        }
    }
	public static void stretchImage(Component component,
	                                Graphics  g,
									Image     image) {
		Dimension sz = component.getSize();
		waitForImage(component, image);
		g.drawImage(image, 0, 0, sz.width, sz.height, component);
	}
  public static void setCursor(int cursor,
                                 Component component) {
		component.setCursor(Cursor.getPredefinedCursor(cursor));
    }

  // Methods added by W. Christian

  static public boolean isMicrosoft() {
	if(isJS) {
	        return false;
	}
    String vendor=System.getProperty("java.vendor").toLowerCase();
    return(vendor.startsWith("microsoft"));
  }

	/**
	 * Get an image from (a) the document base, (b) a jar file, the code base, the
	 * document base, or the absolute URL.
	 *
	 * @param file Location of image relative to the document containing the HTML
	 *             page.
	 * @return the image
	 *
	 */
	static public Image getImage(String file, Applet applet) {
		Image im = null;
		URL url = null;
		while (true) {
			if (applet == null)
				System.out.println("Applet not found in getImage method.");
			try {
				url = Assets.getURLFromPath(file);
				if (url != null)
					im = applet.getImage(url);
			} catch (Exception e) {
			}
			if (im != null)
				break;
			try {
				// first look for image relative to html document; works best for JavaScript
				// Physlets
				url = new URL(applet.getDocumentBase(), file);
				im = applet.getImage(url);
				if (im != null)
					break;
			} catch (Exception e) {
			}
			try { // look for images in jar files first!
				String resourcePath = file;
				if (!file.startsWith("/"))
					resourcePath = "/" + file;
				url = Util.class.getResource(resourcePath);
				im = applet.getImage(url);
				if (im != null)
					break;
			} catch (Exception e) {
			}
			try { // look for the image in the codebase directory
				url = new URL(applet.getCodeBase(), file);
				im = applet.getImage(url);
				if (im != null)
					break;
			} catch (Exception e) {
			}
			try {
				url = new java.net.URL(file);
				im = applet.getImage(url);
				if (im != null)
					break;
			} catch (Exception e) {
			}
			break;
		}
		if (im != null) {
			try {
				MediaTracker tracker = new MediaTracker(applet);
				tracker.addImage(im, 0);
				tracker.waitForID(0, 1000); // wait one second
				if (tracker.isErrorAny()) {
					System.out.println("Util tracker error " + url);
					im = null;
				}
			} catch (Exception e) {
				im = null;
			}
		}
		if (im == null) {
			System.out.println("Failed to load image file. file=");
		} else {
			System.out.println("Util getImage OK " + url);
		}
		return im;
	}
}