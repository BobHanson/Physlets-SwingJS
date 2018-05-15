(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.JSUtil','swingjs.api.js.DOMNode','java.awt.Toolkit','swingjs.plaf.JSComponentUI','java.awt.Insets']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSWindowUI", null, 'swingjs.plaf.JSComponentUI', 'java.awt.peer.WindowPeer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.frameNode = null;
this.titleBarNode = null;
this.titleNode = null;
this.closerNode = null;
this.layerNode = null;
this.w = null;
this.z = 0;
this.defaultWidth = 0;
this.defaultHeight = 0;
this.isFrame = false;
this.isDialog = false;
this.window = null;
this.font = null;
this.modalNode = null;
this.graphics = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.defaultWidth = 400;
this.defaultHeight = 400;
}, 1);

Clazz.newMeth(C$, 'setFrame$java_awt_Window$Z', function (target, isFrame) {
this.window = target;
this.w = this.window;
this.isFrame = isFrame;
this.isContainer = this.isWindow = true;
var jc = this;
var viewer = (I$[1]||$incl$(1)).getAppletViewer();
this.applet = viewer.html5Applet;
this.graphics = jc.getGraphics();
return this;
});

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.containerNode = this.domNode = this.newDOMObject$S$S$SA("div", this.id, []);
this.setWindowClass$swingjs_api_js_DOMNode(this.domNode);
}return this.domNode;
});

Clazz.newMeth(C$, 'setWindowClass$swingjs_api_js_DOMNode', function (windowNode) {
(I$[2]||$incl$(2)).setZ(windowNode, this.z);
this.$$O(windowNode).addClass("swingjs-window");
});

Clazz.newMeth(C$, 'getToolkit', function () {
return (I$[3]||$incl$(3)).getDefaultToolkit();
});

Clazz.newMeth(C$, 'getFontMetrics$java_awt_Font', function (font) {
if (!font.equals$O(this.font)) this.window.setFont$java_awt_Font(this.font = font);
return this.graphics.getFontMetrics$java_awt_Font(font);
});

Clazz.newMeth(C$, 'toFront', function () {
if ((I$[4]||$incl$(4)).debugging) System.out.println$S("window to front for " + this.id);
this.z = (I$[1]||$incl$(1)).J2S._setWindowZIndex(this.domNode, 2147483647);
});

Clazz.newMeth(C$, 'toBack', function () {
System.out.println$S("window to back for " + this.id);
this.z = (I$[1]||$incl$(1)).J2S._setWindowZIndex(this.domNode, -2147483648);
});

Clazz.newMeth(C$, 'updateAlwaysOnTopState', function () {
});

Clazz.newMeth(C$, 'updateFocusableWindowState', function () {
});

Clazz.newMeth(C$, 'requestWindowFocus', function () {
return false;
});

Clazz.newMeth(C$, 'setModalBlocked$java_awt_Dialog$Z', function (blocker, blocked) {
});

Clazz.newMeth(C$, 'updateMinimumSize', function () {
});

Clazz.newMeth(C$, 'updateIconImages', function () {
});

Clazz.newMeth(C$, 'setOpacity$F', function (opacity) {
});

Clazz.newMeth(C$, 'setOpaque$Z', function (isOpaque) {
});

Clazz.newMeth(C$, 'updateWindow$java_awt_image_BufferedImage', function (backBuffer) {
});

Clazz.newMeth(C$, 'repositionSecurityWarning', function () {
});

Clazz.newMeth(C$, 'dispose', function () {
(I$[1]||$incl$(1)).J2S._jsUnsetMouse(this.domNode);
(I$[2]||$incl$(2)).remove(this.outerNode);
if (this.modalNode != null ) (I$[2]||$incl$(2)).remove(this.modalNode);
});

Clazz.newMeth(C$, 'getInsets', function () {
return Clazz.new_((I$[5]||$incl$(5)).c$$I$I$I$I,[0, 0, 0, 0]);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:28
