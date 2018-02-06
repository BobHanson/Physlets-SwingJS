(function(){var P$=Clazz.newPackage("swingjs"),I$=[['sun.awt.AppContext','swingjs.JSUtil','java.awt.image.ColorModel','javax.swing.UIManager','swingjs.api.Interface','Thread','javajs.util.PT','swingjs.JSNullComponentPeer','java.io.BufferedInputStream','java.io.ByteArrayInputStream','swingjs.JSToolkit$1','swingjs.api.js.DOMNode']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSToolkit", null, 'sun.awt.SunToolkit');
C$.isMac = false;
C$.defaultContext = null;
var p$=C$.prototype;
C$.uid = null;
C$.dispatchID = 0;
C$.compositor = null;
C$.audioPlayer = null;
C$.systemClipboard = null;
C$.hardwiredFontList = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
{
{
swingjs.JSToolkit.isMac = (J2S.featureDetection.os == "mac");
}
}
;
C$.dispatchID = 0;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.imageKit = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
System.out.println$S("JSToolkit initialized");
}, 1);

Clazz.newMeth(C$, 'getPostEventQueue$Z', function (isPost) {
return (isPost ? (I$[1]||$incl$(1)).getAppContext().get$O("PostEventQueue") : (I$[1]||$incl$(1)).getAppContext().get$O((I$[1]||$incl$(1)).EVENT_QUEUE_KEY));
}, 1);

Clazz.newMeth(C$, 'exit', function () {
(I$[2]||$incl$(2)).getAppletViewer().exit();
}, 1);

Clazz.newMeth(C$, 'getScreenWidth', function () {
var jq = (I$[2]||$incl$(2)).getJQuery();
var w = 0;
{
w = jq.$(window).width();
}
return w;
});

Clazz.newMeth(C$, 'getScreenHeight', function () {
var jq = (I$[2]||$incl$(2)).getJQuery();
var h = 0;
{
h = jq.$(window).height();
}
return h;
});

Clazz.newMeth(C$, 'getScreenResolution', function () {
return 0;
});

Clazz.newMeth(C$, 'getColorModel', function () {
return (I$[3]||$incl$(3)).getRGBdefault();
});

Clazz.newMeth(C$, 'sync', function () {
});

Clazz.newMeth(C$, 'isModalExclusionTypeSupported$java_awt_Dialog_ModalExclusionType', function (modalExclusionType) {
return true;
});

Clazz.newMeth(C$, 'isModalityTypeSupported$java_awt_Dialog_ModalityType', function (modalityType) {
return true;
});

Clazz.newMeth(C$, 'isTraySupported', function () {
return false;
});

Clazz.newMeth(C$, 'grab$java_awt_Window', function (w) {
});

Clazz.newMeth(C$, 'ungrab$java_awt_Window', function (w) {
});

Clazz.newMeth(C$, 'getPropertyObject$O$S$O', function (t, key, def) {
return def;
}, 1);

Clazz.newMeth(C$, 'getGraphicsConfiguration', function () {
var ap = (I$[2]||$incl$(2)).getAppletViewer();
var gc = ap.graphicsConfig;
return (gc == null  ? (gc = ap.graphicsConfig = (I$[2]||$incl$(2)).getInstance$S("swingjs.JSGraphicsConfiguration")) : gc);
}, 1);

Clazz.newMeth(C$, 'isFocused$java_awt_Window', function (window) {
return false;
}, 1);

Clazz.newMeth(C$, 'getCSSFont$java_awt_Font', function (font) {
var css = "";
if (font.isItalic()) css += "font-style:italic;";
if (font.isBold()) css += "font-weight:bold;";
css += "font-size:" + font.getSize() + "px;" ;
css += "font-family:" + font.getFamily() + ";" ;
return css;
}, 1);

Clazz.newMeth(C$, 'getStringWidth$swingjs_api_js_HTML5CanvasContext2D$java_awt_Font$S', function (context, font, text) {
if (text == null  || text.length$() == 0 ) return 0;
var fontInfo = C$.getCanvasFont$java_awt_Font(font);
if (context == null ) context = C$.getDefaultCanvasContext2d();
var w = 0;
{
context.font = fontInfo;
w = Math.ceil(context.measureText(text).width);
}
return w;
}, 1);

Clazz.newMeth(C$, 'getDefaultCanvasContext2d', function () {
{
if (this.defaultContext == null) this.defaultContext = document.createElement( 'canvas' ).getContext('2d');
}
return C$.defaultContext;
}, 1);

Clazz.newMeth(C$, 'getCSSColor$java_awt_Color', function (c) {
var s = "000000" + Integer.toHexString(c.getRGB() & 16777215);
return "#" + s.substring(s.length$() - 6);
}, 1);

Clazz.newMeth(C$, 'getLookAndFeelDefaults', function () {
if (C$.uid == null ) C$.uid = (I$[4]||$incl$(4)).getLookAndFeel().getDefaults();
return C$.uid;
}, 1);

Clazz.newMeth(C$, 'getComponentUI$javax_swing_JComponent', function (target) {
var ui = (I$[5]||$incl$(5)).getInstance$S$Z("swingjs.plaf.JS" + (target).getUIClassID(), true);
if (ui != null ) ui.set$javax_swing_JComponent(target);
return ui;
}, 1);

Clazz.newMeth(C$, 'getSwingDivId', function () {
return (I$[6]||$incl$(6)).currentThread().getName() + "_swingdiv";
}, 1);

Clazz.newMeth(C$, 'dispatchSystemEvent$Runnable', function (runnable) {
var f = null;
{
System.out.println("JST dispatchSystemEvent " + runnable.run.toString()); f = function(_JSToolkit_dispatchSystemEvent) { System.out.println("JST running " + runnable.run.toString());runnable.run()};
}
C$.dispatch$O$I$I(f, 0, 0);
}, 1);

Clazz.newMeth(C$, 'dispatchEvent$java_awt_AWTEvent$O$Z', function (event, src, andWait) {
var f = null;
var id = ++C$.dispatchID;
{
f = function() { 
if (src == null) 
event.dispatch(); 
else 
src.dispatchEvent$java_awt_AWTEvent(event);
};
}
if (andWait) C$.invokeAndWait$javajs_api_JSFunction$I(f, id);
 else C$.dispatch$O$I$I(f, 0, id);
}, 1);

Clazz.newMeth(C$, 'dispatch$O$I$I', function (f, msDelay, id) {
{
var thread = java.lang.Thread.thisThread;
var thread0 = thread;
var id0 = SwingJS.eventID || 0;
var ff = function(_JSToolkit_setTimeout) { SwingJS.eventID = id;
java.lang.Thread.thisThread = thread;
try { if (f.run) f.run();
else f();
} catch (e) { var s = "JSToolkit.dispatch(" + id +"): " + e + "\n" + (e.getStackTrace ? e.getStackTrace() + "\n" : "") + (!!e.stack ? e.stack : "");
System.out.println(s);
alert(s)} SwingJS.eventID = id0;
java.lang.Thread.thisThread = thread0;
};
return (msDelay == -1 ? ff() : setTimeout(ff, msDelay));
}
}, 1);

Clazz.newMeth(C$, 'invokeAndWait$javajs_api_JSFunction$I', function (f, id) {
{
var thread = java.lang.Thread.thisThread;
var thread0 = thread;
(function(_JSToolkit_setTimeout) { var id0 = SwingJS.eventID || 0;
SwingJS.eventID = id;
java.lang.Thread.thisThread = thread;
if (f.run) f.run();
else f();
SwingJS.eventID = id0;
java.lang.Thread.thisThread = thread0;
})();
}
}, 1);

Clazz.newMeth(C$, 'isDispatchThread', function () {
{
return (!!SwingJS.eventID);
}
}, 1);

Clazz.newMeth(C$, 'getHTML5Applet$java_awt_Component', function (c) {
return (c.getAppContext().getThreadGroup()).getHtmlApplet();
}, 1);

Clazz.newMeth(C$, 'taintUI$java_awt_Component', function (c) {
{
c.getUI && c.getUI() && c.getUI().setTainted();
}
}, 1);

Clazz.newMeth(C$, 'createComponent$java_awt_Component', function (target) {
var peer = C$.getUI$java_awt_Component$Z(target, true);
if ((I$[2]||$incl$(2)).debugging) System.out.println$S("JSToolkit creating UI-Peer for " + target.getClass().getName() + ": " + peer.getClass().getName() );
return peer;
});

Clazz.newMeth(C$, 'createDialog$java_awt_Dialog', function (target) {
var ui = target.getUI();
if (ui == null ) return null;
if ((I$[2]||$incl$(2)).debugging) System.out.println$S("JSToolkit creating Dialog Peer for " + target.getClass().getName() + ": " + target.getClass().getName() );
return (ui).setFrame$java_awt_Window$Z(target, true);
});

Clazz.newMeth(C$, 'createFrame$java_awt_Frame', function (target) {
var ui = target.getUI();
if (ui == null ) return null;
if ((I$[2]||$incl$(2)).debugging) System.out.println$S("JSToolkit creating Frame Peer for " + target.getClass().getName() + ": " + target.getClass().getName() );
return (ui).setFrame$java_awt_Window$Z(target, true);
});

Clazz.newMeth(C$, 'createWindow$java_awt_Window', function (target) {
var ui = target.getUI();
if (ui == null ) return null;
if ((I$[2]||$incl$(2)).debugging) System.out.println$S("JSToolkit creating Window Peer for " + target.getClass().getName() + ": " + target.getClass().getName() );
return (ui).setFrame$java_awt_Window$Z(target, false);
});

Clazz.newMeth(C$, 'getUI$java_awt_Component$Z', function (c, isQuiet) {
var ui = null;
{
ui = c.getUI && c.getUI();
}
if (ui == null ) {
var s = c.getClass().getName();
if (!(I$[7]||$incl$(7)).isOneOf$S$S(s, ";javax.swing.Box.Filler;swingjs.JSApplet;")) System.out.println$S("[JSToolkit] Component " + s + " has no corresponding JSComponentUI." );
ui = Clazz.new_((I$[8]||$incl$(8)).c$$java_awt_Component,[c]);
}return ui;
}, 1);

Clazz.newMeth(C$, 'getPlainDocument$javax_swing_JComponent', function (c) {
return (I$[2]||$incl$(2)).getInstance$S("swingjs.JSPlainDocument");
}, 1);

Clazz.newMeth(C$, 'getClassNameForObject$O', function (c) {
{
return c.__CLASS_NAME__;
}
}, 1);

Clazz.newMeth(C$, 'getImagekit', function () {
return (this.imageKit == null  ? this.imageKit = (I$[5]||$incl$(5)).getInstance$S$Z("swingjs.JSImagekit", false) : this.imageKit);
});

Clazz.newMeth(C$, 'getImage$S', function (filename) {
return this.createImage$S(filename);
});

Clazz.newMeth(C$, 'getImage$java_net_URL', function (url) {
return this.createImage$java_net_URL(url);
});

Clazz.newMeth(C$, 'createImage$java_awt_image_ImageProducer', function (producer) {
var kit = (I$[5]||$incl$(5)).getInstance$S$Z("swingjs.JSImagekit", true);
producer.startProduction$java_awt_image_ImageConsumer(kit);
return kit.getCreatedImage();
});

Clazz.newMeth(C$, 'createImage$S', function (filename) {
return p$.getImagekit.apply(this, []).createImageFromBytes$BA$I$I$S((I$[2]||$incl$(2)).getSignedStreamBytes$java_io_BufferedInputStream(Clazz.new_((I$[9]||$incl$(9)).c$$java_io_InputStream,[Clazz.new_((I$[10]||$incl$(10)).c$$BA,[(I$[2]||$incl$(2)).getFileAsBytes$O(filename)])])), 0, -1, filename);
});

Clazz.newMeth(C$, 'createImage$java_net_URL', function (url) {
var b = (I$[2]||$incl$(2)).getURLInputStream$java_net_URL$Z(url, true);
return (b == null  ? null : p$.getImagekit.apply(this, []).createImageFromBytes$BA$I$I$S((I$[2]||$incl$(2)).getSignedStreamBytes$java_io_BufferedInputStream(b), 0, -1, url.toString()));
});

Clazz.newMeth(C$, 'createImage$BA$I$I', function (data, imageoffset, imagelength) {
return p$.getImagekit.apply(this, []).createImageFromBytes$BA$I$I$S(data, imageoffset, imagelength, null);
});

Clazz.newMeth(C$, 'checkImage$java_awt_Image$I$I$java_awt_image_ImageObserver', function (image, width, height, observer) {
return 63;
});

Clazz.newMeth(C$, 'prepareImage$java_awt_Image$I$I$java_awt_image_ImageObserver', function (image, width, height, observer) {
return true;
});

Clazz.newMeth(C$, 'hasFocus$java_awt_Component', function (c) {
var ui = C$.getUI$java_awt_Component$Z(c, false);
return (ui != null  && !ui.isNull  && ui.hasFocus() );
}, 1);

Clazz.newMeth(C$, 'requestFocus$java_awt_Component', function (c) {
var ui = C$.getUI$java_awt_Component$Z(c, false);
if (ui == null  || ui.isNull  || !ui.isFocusable() ) return false;
var r = ((
(function(){var C$=Clazz.newClass(P$, "JSToolkit$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'Runnable', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {
this.$finals.ui.requestFocus$java_awt_Component$Z$Z$J$sun_awt_CausedFocusEvent_Cause(null, false, false, 0, null);
});
})()
), Clazz.new_((I$[11]||$incl$(11)).$init$, [this, {ui: ui}]));
C$.dispatch$O$I$I(r, 50, 0);
return true;
}, 1);

Clazz.newMeth(C$, 'getCompositor', function () {
return (C$.compositor == null  ? C$.compositor = (I$[5]||$incl$(5)).getInstance$S$Z("swingjs.JSGraphicsCompositor", false) : C$.compositor);
}, 1);

Clazz.newMeth(C$, 'setGraphicsCompositeAlpha$swingjs_JSGraphics2D$I', function (g, rule) {
return C$.getCompositor().setGraphicsCompositeAlpha$swingjs_JSGraphics2D$I(g, rule);
}, 1);

Clazz.newMeth(C$, 'drawImageOp$swingjs_JSGraphics2D$java_awt_image_BufferedImage$java_awt_image_BufferedImageOp$I$I', function (g, img, op, x, y) {
return C$.getCompositor().drawImageOp$swingjs_JSGraphics2D$java_awt_image_BufferedImage$java_awt_image_BufferedImageOp$I$I(g, img, op, x, y);
}, 1);

Clazz.newMeth(C$, 'filterRaster$java_awt_image_Raster$java_awt_image_WritableRaster$java_awt_image_RasterOp', function (src, dst, op) {
return C$.getCompositor().filterRaster$java_awt_image_Raster$java_awt_image_WritableRaster$java_awt_image_RasterOp(src, dst, op);
}, 1);

Clazz.newMeth(C$, 'filterImage$java_awt_image_BufferedImage$java_awt_image_BufferedImage$java_awt_image_BufferedImageOp', function (src, dst, op) {
return C$.getCompositor().filterImage$java_awt_image_BufferedImage$java_awt_image_BufferedImage$java_awt_image_BufferedImageOp(src, dst, op);
}, 1);

Clazz.newMeth(C$, 'getAudioPlayer', function () {
return (C$.audioPlayer == null  ? C$.audioPlayer = (I$[2]||$incl$(2)).getInstance$S("swingjs.JSAudio") : C$.audioPlayer);
}, 1);

Clazz.newMeth(C$, 'playAudio$BA$javax_sound_sampled_AudioFormat', function (data, audioFormat) {
C$.getAudioPlayer().getAudio$BA$javax_sound_sampled_AudioFormat(data, audioFormat).play();
}, 1);

Clazz.newMeth(C$, 'getAudioClip$java_net_URL', function (url) {
return C$.getAudioPlayer().getAudioClip$java_net_URL(url);
}, 1);

Clazz.newMeth(C$, 'playAudioFile$java_net_URL', function (url) {
C$.getAudioPlayer().getAudioFileFromURL$java_net_URL(url).play();
}, 1);

Clazz.newMeth(C$, 'getAudioLine$javax_sound_sampled_Line_Info', function (info) {
return C$.getAudioPlayer().getAudioLine$javax_sound_sampled_Line_Info(info);
}, 1);

Clazz.newMeth(C$, 'getTimerQueue', function () {
return (I$[2]||$incl$(2)).getAppletViewer().getTimerQueue();
}, 1);

Clazz.newMeth(C$, 'getFileFromDialog$swingjs_api_JSFileHandler$S', function (jsFileHandler, type) {
var f = null;
{
f = function(data, fileName) { jsFileHandler.handleFileLoaded$BA$S(data, fileName) };
}
(I$[2]||$incl$(2)).J2S._getFileFromDialog(f, type);
}, 1);

Clazz.newMeth(C$, 'killDispatched$I', function (html5Id) {
{
clearTimeout(html5Id);
}
}, 1);

Clazz.newMeth(C$, 'getSystemClipboard', function () {
if (C$.systemClipboard == null ) C$.systemClipboard = (I$[2]||$incl$(2)).getInstance$S("java.awt.datatransfer.Clipboard");
return C$.systemClipboard;
});

Clazz.newMeth(C$, 'createDragSourceContextPeer$java_awt_dnd_DragGestureEvent', function (dge) {
return null;
});

Clazz.newMeth(C$, 'setCursor$java_awt_Cursor', function (c) {
var curs = null;
switch (c == null  ? 0 : c.getType()) {
case 1:
curs = "crosshair";
break;
case 3:
curs = "wait";
break;
case 2:
curs = "text";
break;
case 8:
case 9:
curs = "ns-resize";
break;
case 12:
curs = "grab";
break;
case 13:
curs = "move";
break;
case 7:
case 4:
curs = "nesw-resize";
break;
case 5:
case 6:
curs = "nwse-resize";
break;
case 11:
case 10:
curs = "ew-resize";
break;
case 0:
default:
curs = "default";
break;
}
(I$[12]||$incl$(12)).setCursor(curs);
}, 1);

Clazz.newMeth(C$, 'getFontList', function () {
if (C$.hardwiredFontList == null ) C$.hardwiredFontList = Clazz.array(java.lang.String, -1, ["Dialog", "SansSerif", "Serif", "Monospaced", "DialogInput"]);
return C$.hardwiredFontList;
});

Clazz.newMeth(C$, 'getFontFamily$java_awt_Font', function (font) {
return font.getName();
}, 1);

Clazz.newMeth(C$, 'getFontMetrics$java_awt_Font', function (font) {
return font.getFontMetrics();
});

Clazz.newMeth(C$, 'getCanvasFont$java_awt_Font', function (font) {
var strStyle = "";
if (font.isItalic()) strStyle += "italic ";
if (font.isBold()) strStyle += "bold ";
var family = font.getFamily();
if (family.equals$O("SansSerif") || family.equals$O("Dialog") || family.equals$O("DialogInput")  ) family = "Arial";
return strStyle + font.getSize() + "px " + family ;
}, 1);

Clazz.newMeth(C$, 'getDateFormat$S', function (isoType) {
var prefix = "";
var suffix = "";
{
if (isoType == null) { return ("" + (new Date())).split(" (")[0];
} if (isoType.indexOf("8824") >= 0) { var d = new Date();
var x = d.toString().split(" ");
var MM = "0" + (1 + d.getMonth()); MM = MM.substring(MM.length - 2);
var dd = "0" + d.getDate(); dd = dd.substring(dd.length - 2);
return x[3] + MM + dd + x[4].replace(/\:/g,"") + x[5].substring(3,6) + "'" + x[5].substring(6,8) + "'" } if (isoType.indexOf("8601") >= 0){ var d = new Date();
var x = d.toString().split(" ");
// Firefox now doing this?
if (x.length == 1) return x;
var MM = "0" + (1 + d.getMonth()); MM = MM.substring(MM.length - 2);
var dd = "0" + d.getDate(); dd = dd.substring(dd.length - 2);
return x[3] + '-' + MM + '-' + dd + 'T' + x[4] }
}
}, 1);

Clazz.newMeth(C$, 'beep', function () {
System.out.println$S("JSToolkit.beep");
});

Clazz.newMeth(C$, 'getPrintJob$java_awt_Frame$S$java_util_Properties', function (frame, jobtitle, props) {
var job = (I$[2]||$incl$(2)).getInstance$S("swingjs.JSPrintJob");
job.setProperties$S$java_util_Properties(jobtitle, props);
return job;
});

Clazz.newMeth(C$, 'getPrintJob$java_awt_Frame$S$java_awt_JobAttributes$java_awt_PageAttributes', function (frame, jobtitle, jobAttributes, pageAttributes) {
var job = (I$[2]||$incl$(2)).getInstance$S("swingjs.JSPrintJob");
job.setAttributes$S$java_awt_JobAttributes$java_awt_PageAttributes(jobtitle, jobAttributes, pageAttributes);
return job;
});
})();
//Created 2018-02-06 09:00:32
