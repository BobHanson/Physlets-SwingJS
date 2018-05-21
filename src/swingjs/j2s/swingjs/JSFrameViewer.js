(function(){var P$=Clazz.newPackage("swingjs"),I$=[['java.awt.Insets','swingjs.JSMouse','swingjs.JSGraphics2D','swingjs.api.js.DOMNode','swingjs.plaf.Resizer']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSFrameViewer", null, 'swingjs.JSApp', 'swingjs.api.js.JSInterface');
var p$=C$.prototype;
C$.canvasCount = 0;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.jsgraphics = null;
this.top = null;
this.appletViewer = null;
this.resizer = null;
this.insets = null;
this.display = null;
this.applet = null;
this.japplet = null;
this.mouse = null;
this.canvas = null;
this.frameID = null;
this.canvasId = null;
this.topApp = null;
this.resizable = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.resizable = true;
}, 1);

Clazz.newMeth(C$, 'getInsets', function () {
return this.insets;
});

Clazz.newMeth(C$, 'c$$java_util_Hashtable', function (params) {
C$.superclazz.c$$java_util_Hashtable.apply(this, [params]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'setForWindow$java_awt_Container', function (window) {
this.isFrame = true;
this.appletViewer = window.appletViewer;
this.top = window;
this.applet = window;
this.fullName = this.appletViewer.fullName;
this.canvas = null;
this.jsgraphics = null;
this.insets = Clazz.new_((I$[1]||$incl$(1)).c$$I$I$I$I,[20, 0, 0, 0]);
this.getGraphics$I$I(0, 0);
return this;
});

Clazz.newMeth(C$, 'getTop', function () {
return this.top;
});

Clazz.newMeth(C$, 'cacheFileByName$S$Z', function (fileName, isAdd) {
return 0;
});

Clazz.newMeth(C$, 'cachePut$S$O', function (key, data) {
});

Clazz.newMeth(C$, 'destroy', function () {
});

Clazz.newMeth(C$, 'getFullName', function () {
return this.fullName;
});

Clazz.newMeth(C$, 'openFileAsyncSpecial$S$I', function (fileName, flags) {
});

Clazz.newMeth(C$, 'processMouseEvent$I$I$I$I$J$O$I', function (id, x, y, modifiers, time, jqevent, scroll) {
p$.getMouse.apply(this, []).processEvent$I$I$I$I$J$O$I(id, x, y, modifiers, time, jqevent, scroll);
return false;
});

Clazz.newMeth(C$, 'getMouse', function () {
return (this.mouse == null  ? this.mouse = Clazz.new_((I$[2]||$incl$(2)).c$$swingjs_JSFrameViewer,[this]) : this.mouse);
});

Clazz.newMeth(C$, 'processTwoPointGesture$FAAA', function (touches) {
p$.getMouse.apply(this, []).processTwoPointGesture$FAAA(touches);
});

Clazz.newMeth(C$, 'setDisplay$swingjs_api_js_HTML5Canvas', function (canvas) {
this.canvas = canvas;
this.jsgraphics = null;
});

Clazz.newMeth(C$, 'setScreenDimension$I$I', function (width, height) {
this.setGraphics$java_awt_Graphics$I$I((this.jsgraphics = null), width, height);
if (this.top != null ) this.top.resizeOriginal$I$I(width, height);
});

Clazz.newMeth(C$, 'setGraphics$java_awt_Graphics$I$I', function (g, width, height) {
return (g == null  ? this.getGraphics$I$I(width, height) : g);
});

Clazz.newMeth(C$, 'setStatusDragDropped$I$I$I$S', function (mode, x, y, fileName) {
return false;
});

Clazz.newMeth(C$, 'startHoverWatcher$Z', function (enable) {
});

Clazz.newMeth(C$, 'getGraphics$I$I', function (wNew, hNew) {
if (wNew == 0 && this.top != null  ) {
if (this.topApp == null ) this.topApp = this.top;
wNew = Math.max(0, (this.topApp).getContentPane().getWidth());
hNew = Math.max(0, (this.topApp).getContentPane().getHeight());
}var wOld = 0;
var hOld = 0;
{
wOld = (this.canvas == null ? 0 : this.canvas.width); hOld = (this.canvas == null ? 0 : this.canvas.height)
}
if (wNew >= 0 && hNew >= 0  && (wOld != wNew || hOld != hNew  || this.canvas == null   || this.jsgraphics == null  ) ) {
this.jsgraphics = Clazz.new_((I$[3]||$incl$(3)).c$$O,[this.canvas = this.newCanvas$I$I(wNew, hNew)]);
}return this.jsgraphics;
});

Clazz.newMeth(C$, 'newCanvas$I$I', function (width, height) {
if (this.isApplet) {
var c = this.html5Applet._getHtml5Canvas();
if (c != null ) {
return this.canvas = c;
}}if (this.topApp == null ) this.topApp = this.top;
var root = (this.topApp.getComponentCount() > 0 ? this.topApp.getComponent$I(0) : null);
var userFramedApplet = null;
var app = null;
if (root != null  && root.getContentPane().getComponentCount() > 0 ) {
var appletInFrame = false;
app = root.getContentPane().getComponent$I(0);

appletInFrame = (app.uiClassID == "AppletUI");
if (appletInFrame) {
userFramedApplet = app;
root = userFramedApplet.getComponent$I(0);
}}var parent = (root == null  ? null : (root.getUI()).domNode);
if (parent != null ) (I$[4]||$incl$(4)).remove(this.canvas);
this.display = this.canvasId = this.appletViewer.appletName + "_canvas" + ++C$.canvasCount ;
System.out.println$S("JSFrameViewer creating new canvas " + this.canvasId + ": " + width + "  " + height );
this.canvas = (I$[4]||$incl$(4)).createElement("canvas", this.canvasId);
if (userFramedApplet != null ) {
var appViewer = userFramedApplet.getFrameViewer();
appViewer.setDisplay$swingjs_api_js_HTML5Canvas(this.canvas);
appViewer.topApp = app;
}var iTop = (root == null  ? 0 : root.getContentPane().getY());
(I$[4]||$incl$(4)).setPositionAbsolute(this.canvas, iTop, 0);
(I$[4]||$incl$(4)).setStyles(this.canvas, ["width", width + "px", "height", height + "px"]);
if (this.resizer != null ) this.resizer.setPosition$I$I(0, 0);
if (parent != null ) {
parent.appendChild(this.canvas);
}{
this.canvas.width = width; this.canvas.height = height;
}
return this.canvas;
});

Clazz.newMeth(C$, 'setResizable$Z', function (tf) {
this.resizable = tf;
if (!this.isResizable()) this.resizable = false;
if (this.resizer != null ) this.resizer.setEnabled$Z(this.resizable);
 else if (this.resizable && p$.newResizer.apply(this, []) != null  ) this.resizer.setPosition$I$I(0, 0);
});

Clazz.newMeth(C$, 'isResizable', function () {
return this.resizable && (!this.appletViewer.haveResizable || this.appletViewer.$isResizable ) ;
});

Clazz.newMeth(C$, 'getResizer', function () {
return (this.resizer != null  || !this.isResizable()  ? this.resizer : p$.newResizer.apply(this, []));
});

Clazz.newMeth(C$, 'newResizer', function () {
this.resizer = Clazz.new_((I$[5]||$incl$(5))).set$swingjs_JSFrameViewer(this);
if (this.resizer != null ) this.resizer.show();
return this.resizer;
});

Clazz.newMeth(C$, 'getDiv$S', function (id) {
{
return J2S.$(this.html5Applet, id)[0];
}
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
this.top.paint$java_awt_Graphics(this.setGraphics$java_awt_Graphics$I$I(g, 0, 0));
});
})();
//Created 2018-05-15 01:03:15
