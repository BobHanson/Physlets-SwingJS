(function(){var P$=Clazz.newPackage("swingjs.plaf"),I$=[['swingjs.plaf.JSComponentUI','swingjs.api.js.DOMNode','java.awt.Toolkit','java.awt.Color','swingjs.JSUtil','java.awt.event.ComponentEvent','java.awt.event.WindowEvent','javax.swing.LookAndFeel','java.awt.Rectangle']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSFrameUI", null, 'swingjs.plaf.JSWindowUI', 'java.awt.peer.FramePeer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.frame = null;
this.title = null;
this.state = 0;
this.resizeable = false;
this.closerWrap = null;
this.isModal = false;
this.zModal = 0;
this.bounds = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
(I$[1]||$incl$(1)).frameZ = (I$[1]||$incl$(1)).frameZ+(1000);
this.z = (I$[1]||$incl$(1)).frameZ;
this.isContainer = true;
this.defaultHeight = 500;
this.defaultWidth = 500;
this.setDoc();
}, 1);

Clazz.newMeth(C$, 'updateDOMNode', function () {
if (this.domNode == null ) {
this.domNode = this.frameNode = this.newDOMObject$S$S$SA("div", this.id + "_frame", []);
(I$[2]||$incl$(2)).setStyles(this.frameNode, ["box-shadow", "0px 0px 10px gray", "box-sizing", "content-box"]);
this.setWindowClass$swingjs_api_js_DOMNode(this.frameNode);
var w = this.c.getWidth();
var h = this.c.getHeight();
if (w == 0) w = this.defaultWidth;
if (h == 0) h = this.defaultHeight;
(I$[2]||$incl$(2)).setSize(this.frameNode, w, h);
(I$[2]||$incl$(2)).setAttr(this.frameNode, "ui", this);
(I$[2]||$incl$(2)).setPositionAbsolute(this.frameNode, 0, 0);
this.setJ2sMouseHandler$swingjs_api_js_DOMNode(this.frameNode);
this.titleBarNode = this.newDOMObject$S$S$SA("div", this.id + "_titlebar", []);
(I$[2]||$incl$(2)).setPositionAbsolute(this.titleBarNode, 0, 0);
(I$[2]||$incl$(2)).setStyles(this.titleBarNode, ["background-color", "#E0E0E0", "height", "20px", "font-size", "14px", "font-family", "sans-serif", "font-weight", "bold"]);
this.titleNode = this.newDOMObject$S$S$SA("label", this.id + "_title", []);
(I$[2]||$incl$(2)).setPositionAbsolute(this.titleNode, 2, 4);
(I$[2]||$incl$(2)).setStyles(this.titleNode, ["height", "20px"]);
this.closerWrap = this.newDOMObject$S$S$SA("div", this.id + "_closerwrap", []);
(I$[2]||$incl$(2)).setPositionAbsolute(this.closerWrap, 0, 0);
(I$[2]||$incl$(2)).setStyles(this.closerWrap, ["text-align", "right"]);
this.closerNode = this.newDOMObject$S$S$SA("label", this.id + "_closer", ["innerHTML", "X"]);
(I$[2]||$incl$(2)).setStyles(this.closerNode, ["width", "20px", "height", "20px", "position", "absolute", "text-align", "center", "right", "0px"]);
(I$[2]||$incl$(2)).addJqueryHandledEvent(this, this.closerNode, "click mouseenter mouseout");
this.frameNode.appendChild(this.titleBarNode);
if (this.isModal) {
this.modalNode = (I$[2]||$incl$(2)).createElement("div", this.id + "_modaldiv");
var screen = (I$[3]||$incl$(3)).getDefaultToolkit().getScreenSize();
(I$[2]||$incl$(2)).setStyles(this.modalNode, ["background", P$.JSComponentUI.toCSSString$java_awt_Color(Clazz.new_((I$[4]||$incl$(4)).c$$I$I$I$I,[100, 100, 100, 100]))]);
(I$[2]||$incl$(2)).setPositionAbsolute(this.modalNode, 0, 0);
(I$[2]||$incl$(2)).setSize(this.modalNode, screen.width, screen.height);
}var fnode = this.frameNode;
var fGetFrameParent = null;
{
var me = this; fGetFrameParent = function(){me.notifyFrameMoved();return $(fnode).parent()}
}
(I$[5]||$incl$(5)).J2S._setDraggable(this.titleBarNode, fGetFrameParent);
this.titleBarNode.appendChild(this.titleNode);
this.titleBarNode.appendChild(this.closerWrap);
this.closerWrap.appendChild(this.closerNode);
var s = this.getInsets();
(I$[2]||$incl$(2)).setPositionAbsolute(this.frameNode, 0, 0);
(I$[2]||$incl$(2)).setAttrs(this.frameNode, ["width", "" + this.frame.getWidth() + s.left + s.right , "height", "" + this.frame.getHeight() + s.top + s.bottom ]);
this.containerNode = this.frameNode;
}var strColor = P$.JSComponentUI.toCSSString$java_awt_Color(this.c.getBackground());
(I$[2]||$incl$(2)).setStyles(this.domNode, ["background-color", strColor]);
(I$[2]||$incl$(2)).setStyles(this.frameNode, ["background", strColor]);
(I$[2]||$incl$(2)).setStyles(this.frameNode, ["color", P$.JSComponentUI.toCSSString$java_awt_Color(this.c.getForeground())]);
(I$[2]||$incl$(2)).setStyles(this.closerNode, ["background-color", strColor]);
this.setInnerComponentBounds$I$I(this.width, this.height);
this.setTitle$S(this.frame.getTitle());
return this.domNode;
});

Clazz.newMeth(C$, 'notifyFrameMoved', function () {
this.toFront();
(I$[3]||$incl$(3)).getEventQueue().postEvent$java_awt_AWTEvent(Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_Component$I,[this.frame, 100]));
});

Clazz.newMeth(C$, 'handleJSEvent$O$I$O', function (target, eventType, jQueryEvent) {
var type = "";
if (target === this.closerNode ) {
{
type = jQueryEvent.type;
}
if (eventType == -1) {
if (type == "click") {
var tbar = this.titleBarNode;
{
J2S._setDraggable(tbar, false);
}
this.frame.dispatchEvent$java_awt_AWTEvent(Clazz.new_((I$[7]||$incl$(7)).c$$java_awt_Window$I,[this.frame, 201]));
return true;
} else if (type.equals$O("mouseout")) {
(I$[2]||$incl$(2)).setStyles(this.closerNode, ["background-color", P$.JSComponentUI.toCSSString$java_awt_Color(this.c.getBackground())]);
return true;
} else if (type.equals$O("mouseenter")) {
(I$[2]||$incl$(2)).setStyles(this.closerNode, ["background-color", "red"]);
return true;
}}}return false;
});

Clazz.newMeth(C$, 'closeFrame', function () {
(I$[5]||$incl$(5)).J2S._jsUnsetMouse(this.frameNode);
this.$$O(this.frameNode).remove();
this.$$O(this.outerNode).remove();
});

Clazz.newMeth(C$, 'setInnerComponentBounds$I$I', function (width, height) {
(I$[2]||$incl$(2)).setStyles(this.closerWrap, ["text-align", "right", "width", width + "px"]);
(I$[2]||$incl$(2)).setStyles(this.titleNode, ["width", width + "px", "height", "20px"]);
});

Clazz.newMeth(C$, 'installUI$javax_swing_JComponent', function (jc) {
this.frame = this.c;
C$.superclazz.prototype.installUI$javax_swing_JComponent.apply(this, [jc]);
(I$[8]||$incl$(8)).installColors$javax_swing_JComponent$S$S(jc, "Frame.background", "Frame.foreground");
});

Clazz.newMeth(C$, 'uninstallUI$javax_swing_JComponent', function (jc) {
this.closeFrame();
});

Clazz.newMeth(C$, 'setTitle$S', function (title) {
this.title = title;
if (this.titleNode != null ) (I$[2]||$incl$(2)).setAttr(this.titleNode, "innerHTML", title);
});

Clazz.newMeth(C$, 'setMenuBar$O', function (mb) {
});

Clazz.newMeth(C$, 'setResizable$Z', function (resizeable) {
this.resizeable = resizeable;
});

Clazz.newMeth(C$, 'setState$I', function (state) {
this.state = state;
});

Clazz.newMeth(C$, 'getState', function () {
return this.state;
});

Clazz.newMeth(C$, 'setMaximizedBounds$java_awt_Rectangle', function (bounds) {
});

Clazz.newMeth(C$, 'setBoundsPrivate$I$I$I$I', function (x, y, width, height) {
this.bounds = Clazz.new_((I$[9]||$incl$(9)).c$$I$I$I$I,[x, y, width, height]);
});

Clazz.newMeth(C$, 'getBoundsPrivate', function () {
return this.bounds;
});

Clazz.newMeth(C$, 'getInsets', function () {
return this.jc.getFrameViewer().getInsets();
});

Clazz.newMeth(C$, 'propertyChange$java_beans_PropertyChangeEvent', function (e) {
if (e.getPropertyName().equals$O("resizable")) {
var resizable = (e.getNewValue()).booleanValue();
if (this.jc.getFrameViewer().isResizable() == resizable ) return;
this.jc.getFrameViewer().setResizable$Z(resizable);
}C$.superclazz.prototype.propertyChange$java_beans_PropertyChangeEvent.apply(this, [e]);
});

Clazz.newMeth(C$, 'setVisible$Z', function (b) {
C$.superclazz.prototype.setVisible$Z.apply(this, [b]);
if (this.isModal) {
if (b) {
this.$$O(this.body).after(this.modalNode);
var z = this.getZIndex$S(null) - 1;
(I$[2]||$incl$(2)).setStyles(this.modalNode, ["z-index", "" + z]);
}(I$[2]||$incl$(2)).setVisible(this.modalNode, b);
}});
})();
//Created 2018-02-08 11:13:20
