package edu.davidson.graphics;

import edu.davidson.tools.SClock;

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
	if(SClock.isJS) {
	        return false;
	}
    String vendor=System.getProperty("java.vendor").toLowerCase();
    return(vendor.startsWith("microsoft"));
  }

	/**
	 * Get an image from the code base, the document base, or the absolute URL.
	 *
	 * @param file Location of image relative to the document containing the HTML
	 *             page.
	 * @return the image
	 *
	 */
	static public Image getImage(String file, Applet applet) {
		Image im = null;
		URL url = null;
		if (applet == null)
			System.out.println("Applet not found in getImage method.");
		try { // first look for image relative to html document; works best for JavaScript
				// Physlets
			url = new URL(applet.getDocumentBase(), file);
			im = applet.getImage(url);
			System.out.println("Util getImage OK " + url);
		} catch (Exception e) {
			im = null;
			// System.out.println("Failed to load image file from document base.");
		}
		if (im == null)
			try { // look for images in jar files first!
				String resourcePath = file;
				if (!file.startsWith("/"))
					resourcePath = "/" + file;
				// System.out.println("file="+resourcePath);
				url = Util.class.getResource(resourcePath);
				// System.out.println("url="+url);
				im = applet.getImage(url);
				System.out.println("Util getImage OK " + url);
				// System.out.println("image loaded file="+file);
			} catch (Exception e) {
				im = null;
			}
		if (im == null)
			try { // look for the image in the codebase directory
				url = new URL(applet.getCodeBase(), file);
				im = applet.getImage(url);
				System.out.println("Util getImage OK " + url);
			} catch (Exception e) {
				im = null;
				// System.out.println("Failed to load image file from code base.");
			}
		if (im == null)
			try {
				url = new java.net.URL(file);
				im = applet.getImage(url);
				System.out.println("Util getImage OK " + url);
			} catch (Exception e) {
				im = null;
				// System.out.println("Failed to load image file from absolute URL.");
			}
		if (im == null) {
			System.out.println("Failed to load image file. file=");
			return null;
		}
		MediaTracker tracker = new MediaTracker(applet);
		try {
			tracker.addImage(im, 0);
			tracker.waitForID(0, 1000); // wait one second
			if (tracker.isErrorAny()) {
				System.out.println("Util tracker error " + url);
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		// if(tracker.isErrorAny()) return null;
		// if(im.getHeight(applet)<1) return null;
		return im;
	}
}