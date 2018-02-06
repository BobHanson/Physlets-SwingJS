(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.api.js.DOMNode','swingjs.JSUtil','javajs.api.JSFunction','java.awt.Rectangle','java.awt.Color','java.awt.Dimension']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Resizer");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.rootPane = null;
this.resizer = null;
this.rootNode = null;
this.rubberBand = null;
this.jframe = null;
this.offsetx = 0;
this.offsety = 0;
this.minSize = 0;
this.rpc = null;
this.titleHeight = 0;
this.enabled = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.offsetx = -4;
this.offsety = -4;
this.minSize = 10;
this.enabled = true;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'set$swingjs_JSFrameViewer', function (viewer) {
this.rpc = viewer.top;
this.rootPane = this.rpc.getRootPane();
this.titleHeight = viewer.getInsets().top;
if (viewer.isApplet) {
this.rootNode = viewer.getDiv$S("appletdiv");
} else {
this.jframe = this.rpc;
this.rootNode = (this.jframe.getUI()).domNode;
}return (this.rootNode == null  ? null : this);
});

Clazz.newMeth(C$, 'show', function () {
if (this.resizer == null ) p$.createAndShowResizer.apply(this, []);
 else p$.$$swingjs_api_js_DOMNode.apply(this, [this.resizer]).show();
this.setPosition$I$I(0, 0);
});

Clazz.newMeth(C$, 'hide', function () {
p$.$$swingjs_api_js_DOMNode.apply(this, [this.resizer]).hide();
});

Clazz.newMeth(C$, 'setMin$I', function (min) {
this.minSize = min;
});

Clazz.newMeth(C$, 'createAndShowResizer', function () {
var id = this.rootPane.htmlName + "_resizer";
this.resizer = (I$[1]||$incl$(1)).createElement("div", id);
(I$[1]||$incl$(1)).setSize(this.resizer, 10, 10);
(I$[1]||$incl$(1)).setStyles(this.resizer, ["background-color", "red", "opacity", "0", "cursor", "nwse-resize"]);
p$.$$swingjs_api_js_DOMNode.apply(this, [this.resizer]).addClass("swingjs-resizer");
this.rubberBand = (I$[1]||$incl$(1)).createElement("div", id + "_rb");
(I$[1]||$incl$(1)).setStyles(this.rubberBand, ["border", "1px dashed #FF00FF", "z-index", "100000", "position", "absolute", "left", "0px", "top", "0px", "display", "none"]);
this.rootNode.appendChild(this.resizer);
this.rootNode.appendChild(this.rubberBand);
var fHandleResizer = null;
var fHandleDOMResize = null;
var me = this;
{
fHandleResizer = function(xyev,type){me.fHandleResizer$I$I$I( xyev.dx, xyev.dy,type)};
}
(I$[2]||$incl$(2)).J2S._setDraggable(this.resizer, Clazz.array((I$[3]||$incl$(3)), -1, [fHandleResizer]));
p$.$$swingjs_api_js_DOMNode.apply(this, [this.rootNode]).resize(fHandleDOMResize);
});

Clazz.newMeth(C$, 'setPosition$I$I', function (dw, dh) {
var r = p$.getFrameOffset$I$I.apply(this, [dw, dh]);
(I$[1]||$incl$(1)).setPositionAbsolute(this.resizer, r.height + this.offsety, r.width + this.offsetx);
(I$[1]||$incl$(1)).setSize(this.rubberBand, r.width, r.height);
});

Clazz.newMeth(C$, 'getDOMNode', function () {
return this.resizer;
});

Clazz.newMeth(C$, 'fHandleResizer$I$I$I', function (dx, dy, type) {
if (!this.enabled) return;
switch (type) {
case 501:
(I$[1]||$incl$(1)).setStyles(this.resizer, ["background-color", "green"]);
(I$[1]||$incl$(1)).setVisible(this.rubberBand, true);
(I$[1]||$incl$(1)).setCursor("nwse-resize");
break;
case 506:
this.setPosition$I$I(dx, dy);
break;
case 502:
(I$[1]||$incl$(1)).setStyles(this.resizer, ["background-color", "red"]);
(I$[1]||$incl$(1)).setVisible(this.rubberBand, false);
(I$[1]||$incl$(1)).setCursor("auto");
this.fHandleDOMResize$O$I$I(null, dx, dy);
}
});

Clazz.newMeth(C$, 'fHandleDOMResize$O$I$I', function (event, dw, dh) {
var r;
if (!this.enabled) return;
if (event == null ) {
r = p$.getFrameOffset$I$I.apply(this, [dw, dh]);
} else {
(I$[1]||$incl$(1)).getRectangle(this.rootNode, r = Clazz.new_((I$[4]||$incl$(4))));
}if (this.jframe == null ) {
this.rootPane.getGraphics().setColor$java_awt_Color((I$[5]||$incl$(5)).WHITE);
this.rootPane.getGraphics().fillRect$I$I$I$I(0, 0, r.width, r.height);
this.rootPane.appletViewer.html5Applet._resizeApplet(Clazz.array(Integer.TYPE, -1, [r.width, r.height]));
} else {
this.jframe.setPreferredSize$java_awt_Dimension(Clazz.new_((I$[6]||$incl$(6)).c$$I$I,[r.width, r.height]));
this.jframe.invalidate();
this.jframe.repackContainer();
this.jframe.toFront();
}this.setPosition$I$I(0, 0);
});

Clazz.newMeth(C$, '$$swingjs_api_js_DOMNode', function (node) {
return (I$[2]||$incl$(2)).getJQuery().$(node);
});

Clazz.newMeth(C$, 'getFrameOffset$I$I', function (dw, dh) {
var r = (this.rpc).getBounds();
if (r.width + dw > this.minSize) r.width = r.width+(dw);
if (r.height + dh > this.minSize) r.height = r.height+(dh);
return r;
});

Clazz.newMeth(C$, 'setEnabled$Z', function (b) {
this.enabled = b;
});
})();
//Created 2018-02-06 09:00:59
