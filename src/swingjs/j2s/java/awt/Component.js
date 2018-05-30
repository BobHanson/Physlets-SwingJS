(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.util.HashMap','java.awt.ComponentOrientation','sun.awt.AppContext','swingjs.JSToolkit','java.awt.Toolkit','java.awt.Point','java.awt.event.ComponentEvent','java.util.Locale','java.awt.Dimension','java.awt.Rectangle',['java.awt.Component','.BaselineResizeBehavior'],'java.awt.Cursor','java.awt.event.PaintEvent','java.awt.EventQueue','java.awt.event.MouseWheelEvent','java.awt.AWTEventMulticaster','java.awt.event.ComponentListener','java.awt.event.FocusListener','java.awt.event.HierarchyListener','java.awt.event.HierarchyEvent','java.awt.event.HierarchyBoundsListener','java.awt.event.KeyListener','java.awt.event.MouseListener','java.awt.event.MouseMotionListener','java.awt.event.MouseWheelListener','java.awt.event.InputMethodListener','java.beans.PropertyChangeListener','Boolean','java.beans.PropertyChangeSupport','sun.awt.SunToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Component", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'java.awt.image.ImageObserver');
C$.isInc = false;
C$.incRate = 0;
var p$=C$.prototype;
C$.coalesceMap = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.coalesceMap = Clazz.new_((I$[1]||$incl$(1)));
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.peer = null;
this.parent = null;
this.appContext = null;
this.x = 0;
this.y = 0;
this.width = 0;
this.height = 0;
this.foreground = null;
this.background = null;
this.font = null;
this.peerFont = null;
this.cursor = null;
this.locale = null;
this.visible = false;
this.enabled = false;
this.valid = false;
this.popups = null;
this.name = null;
this.nameExplicitlySet = false;
this.focusable = false;
this.$isFocusTraversableOverridden = 0;
this.focusTraversalKeysEnabled = false;
this.minSize = null;
this.minSizeSet = false;
this.prefSize = null;
this.prefSizeSet = false;
this.maxSize = null;
this.maxSizeSet = false;
this.componentOrientation = null;
this.newEventsOnly = false;
this.componentListener = null;
this.focusListener = null;
this.hierarchyListener = null;
this.hierarchyBoundsListener = null;
this.keyListener = null;
this.mouseListener = null;
this.mouseMotionListener = null;
this.mouseWheelListener = null;
this.inputMethodListener = null;
this.windowClosingException = null;
this.eventMask = 0;
this.changeSupport = null;
this.changeSupportLock = null;
this.isPacked = false;
this.boundsOp = 0;
this.isAddNotifyComplete = false;
this.backgroundEraseDisabled = false;
this.dropTarget = null;
this.graphicsConfig = null;
this.eventCache = null;
this.coalescingEnabled = false;
this.autoFocusTransferOnDisposal = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.visible = true;
this.enabled = true;
this.valid = false;
this.nameExplicitlySet = false;
this.focusable = true;
this.$isFocusTraversableOverridden = 0;
this.focusTraversalKeysEnabled = false;
this.componentOrientation = (I$[2]||$incl$(2)).UNKNOWN;
this.newEventsOnly = false;
this.windowClosingException = null;
this.eventMask = 4096;
this.changeSupportLock =  Clazz.new_();
this.isPacked = false;
this.boundsOp = 3;
this.isAddNotifyComplete = false;
this.coalescingEnabled = p$.checkCoalescing.apply(this, []);
this.autoFocusTransferOnDisposal = true;
}, 1);

Clazz.newMeth(C$, 'getAppContext', function () {
return this.appContext;
});

Clazz.newMeth(C$, 'getChangeSupportLock', function () {
return this.changeSupportLock;
});

Clazz.newMeth(C$, 'getBoundsOp', function () {
return this.boundsOp;
});

Clazz.newMeth(C$, 'setBoundsOp$I', function (op) {
if (op == 5) {
this.boundsOp=3;
} else if (this.boundsOp == 3) {
this.boundsOp=op;
}});

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
this.setAppContext();
}, 1);

Clazz.newMeth(C$, 'setAppContext', function () {
this.appContext=(I$[3]||$incl$(3)).getAppContext();
});

Clazz.newMeth(C$, 'constructComponentName', function () {
return null;
});

Clazz.newMeth(C$, 'getName', function () {
if (this.name == null  && !this.nameExplicitlySet ) {
{
if (this.name == null  && !this.nameExplicitlySet ) this.name=this.constructComponentName();
}}return this.name;
});

Clazz.newMeth(C$, 'setName$S', function (name) {
var oldName;
{
oldName=this.name;
this.name=name;
this.nameExplicitlySet=true;
}this.firePropertyChange$S$O$O("name", oldName, name);
});

Clazz.newMeth(C$, 'getParent', function () {
return this.getParent_NoClientCode();
});

Clazz.newMeth(C$, 'getParent_NoClientCode', function () {
return this.parent;
});

Clazz.newMeth(C$, 'getContainer', function () {
return this.getParent();
});

Clazz.newMeth(C$, 'getPeer', function () {
return this.peer;
});

Clazz.newMeth(C$, 'setDropTarget$java_awt_dnd_DropTarget', function (dt) {
if (dt === this.dropTarget  || (this.dropTarget != null  && this.dropTarget.equals$O(dt) ) ) return;
var old;
if ((old=this.dropTarget) != null ) {
if (this.peer != null ) this.dropTarget.removeNotify$java_awt_peer_ComponentPeer(this.peer);
var t = this.dropTarget;
this.dropTarget=null;
try {
t.setComponent$java_awt_Component(null);
} catch (iae) {
if (Clazz.exceptionOf(iae, "java.lang.IllegalArgumentException")){
} else {
throw iae;
}
}
}if ((this.dropTarget=dt) != null ) {
try {
this.dropTarget.setComponent$java_awt_Component(this);
if (this.peer != null ) this.dropTarget.addNotify$java_awt_peer_ComponentPeer(this.peer);
} catch (iae) {
if (Clazz.exceptionOf(iae, "java.lang.IllegalArgumentException")){
if (old != null ) {
try {
old.setComponent$java_awt_Component(this);
if (this.peer != null ) this.dropTarget.addNotify$java_awt_peer_ComponentPeer(this.peer);
} catch (iae1) {
if (Clazz.exceptionOf(iae1, "java.lang.IllegalArgumentException")){
} else {
throw iae1;
}
}
}} else {
throw iae;
}
}
}});

Clazz.newMeth(C$, 'getDropTarget', function () {
return this.dropTarget;
});

Clazz.newMeth(C$, 'getGraphicsConfiguration', function () {
return (I$[4]||$incl$(4)).getGraphicsConfiguration();
});

Clazz.newMeth(C$, 'resetGC', function () {
});

Clazz.newMeth(C$, 'getToolkit', function () {
return this.getToolkitImpl();
});

Clazz.newMeth(C$, 'getToolkitImpl', function () {
var peer = this.peer;
if ((peer != null ) && !(Clazz.instanceOf(peer, "java.awt.peer.LightweightPeer")) ) {
return peer.getToolkit();
}var parent = this.parent;
if (parent != null ) {
return parent.getToolkitImpl();
}return (I$[5]||$incl$(5)).getDefaultToolkit();
});

Clazz.newMeth(C$, 'isValid', function () {
return this.valid;
});

Clazz.newMeth(C$, 'isDisplayable', function () {
return true;
});

Clazz.newMeth(C$, 'isVisible', function () {
return this.isVisible_NoClientCode();
});

Clazz.newMeth(C$, 'isVisible_NoClientCode', function () {
return this.visible;
});

Clazz.newMeth(C$, 'isRecursivelyVisible', function () {
return this.visible && (this.parent == null  || this.parent.isRecursivelyVisible() ) ;
});

Clazz.newMeth(C$, 'pointRelativeToComponent$java_awt_Point', function (absolute) {
var compCoords = this.getLocationOnScreen();
return Clazz.new_((I$[6]||$incl$(6)).c$$I$I,[absolute.x - compCoords.x, absolute.y - compCoords.y]);
});

Clazz.newMeth(C$, 'getMousePosition', function () {
return null;
});

Clazz.newMeth(C$, 'isSameOrAncestorOf$java_awt_Component$Z', function (comp, allowChildren) {
return comp === this ;
});

Clazz.newMeth(C$, 'isShowing', function () {
if (this.visible) {
var parent = this.parent;
return (parent == null ) || parent.isShowing() ;
}return false;
});

Clazz.newMeth(C$, 'isEnabled', function () {
return this.isEnabledImpl();
});

Clazz.newMeth(C$, 'isEnabledImpl', function () {
return this.enabled;
});

Clazz.newMeth(C$, 'setEnabled$Z', function (b) {
this.enable$Z(b);
});

Clazz.newMeth(C$, 'enable', function () {
if (!this.enabled) {
this.enabled=true;
var peer = this.getOrCreatePeer();
if (peer != null ) {
peer.setEnabled$Z(true);
if (this.visible) {
this.updateCursorImmediately();
}}}});

Clazz.newMeth(C$, 'enable$Z', function (b) {
if (b) {
this.enable();
} else {
this.disable();
}});

Clazz.newMeth(C$, 'disable', function () {
if (this.enabled) {
this.enabled=false;
var peer = this.getOrCreatePeer();
if (peer != null ) {
peer.setEnabled$Z(false);
if (this.visible) {
this.updateCursorImmediately();
}}}});

Clazz.newMeth(C$, 'isDoubleBuffered', function () {
return false;
});

Clazz.newMeth(C$, 'setVisible$Z', function (b) {
if (b) {
this.show();
} else {
this.hide();
}});

Clazz.newMeth(C$, 'show$Z', function (b) {
this.setVisible$Z(b);
});

Clazz.newMeth(C$, 'show', function () {
if (!this.visible) {
this.visible=true;
this.updatePeerVisibility$Z(true);
if (this.componentListener != null  || (this.eventMask & 1) != 0  || (I$[5]||$incl$(5)).enabledOnToolkit$J(1) ) {
var e = Clazz.new_((I$[7]||$incl$(7)).c$$java_awt_Component$I,[this, 102]);
(I$[5]||$incl$(5)).getEventQueue().postEvent$java_awt_AWTEvent(e);
}}var parent = this.parent;
if (parent != null ) {
parent.invalidate();
}});

Clazz.newMeth(C$, 'updatePeerVisibility$Z', function (isVisible) {
this.updatePeerVisibilityOrig$Z(isVisible);
});

Clazz.newMeth(C$, 'updatePeerVisibilityOrig$Z', function (isVisible) {
this.peer.setVisible$Z(isVisible);
this.createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(1400, this, this.parent, 4, (I$[5]||$incl$(5)).enabledOnToolkit$J(32768));
if (Clazz.instanceOf(this.peer, "java.awt.peer.LightweightPeer")) {
this.repaint();
}this.updateCursorImmediately();
});

Clazz.newMeth(C$, 'getOrCreatePeer', function () {
return this.peer;
});

Clazz.newMeth(C$, 'containsFocus', function () {
return this.isFocusOwner();
});

Clazz.newMeth(C$, 'clearCurrentFocusCycleRootOnHide', function () {
});

Clazz.newMeth(C$, 'hide', function () {
this.isPacked=false;
if (this.visible) {
this.clearCurrentFocusCycleRootOnHide();
this.visible=false;
this.mixOnHiding$Z(this.isLightweight());
this.updatePeerVisibility$Z(false);
if (this.componentListener != null  || (this.eventMask & 1) != 0  || (I$[5]||$incl$(5)).enabledOnToolkit$J(1) ) {
var e = Clazz.new_((I$[7]||$incl$(7)).c$$java_awt_Component$I,[this, 103]);
(I$[5]||$incl$(5)).getEventQueue().postEvent$java_awt_AWTEvent(e);
}}var parent = this.parent;
if (parent != null ) {
parent.invalidate();
}});

Clazz.newMeth(C$, 'getForeground', function () {
var foreground = this.foreground;
if (foreground != null ) {
return foreground;
}var parent = this.parent;
return (parent != null ) ? parent.getForeground() : null;
});

Clazz.newMeth(C$, 'setForeground$java_awt_Color', function (c) {
var oldColor = this.foreground;
var peer = this.getOrCreatePeer();
this.foreground=c;
if (peer != null ) {
c=this.getForeground();
if (c != null ) {
peer.setForeground$java_awt_Color(c);
}}this.firePropertyChange$S$O$O("foreground", oldColor, c);
});

Clazz.newMeth(C$, 'isForegroundSet', function () {
return (this.foreground != null );
});

Clazz.newMeth(C$, 'getBackground', function () {
var background = this.background;
if (background != null ) {
return background;
}var parent = this.parent;
return (parent != null ) ? parent.getBackground() : null;
});

Clazz.newMeth(C$, 'setBackground$java_awt_Color', function (c) {
var oldColor = this.background;
var peer = this.getOrCreatePeer();
this.background=c;
if (peer != null ) {
c=this.getBackground();
if (c != null ) {
peer.setBackground$java_awt_Color(c);
}}this.firePropertyChange$S$O$O("background", oldColor, c);
});

Clazz.newMeth(C$, 'isBackgroundSet', function () {
return (this.background != null );
});

Clazz.newMeth(C$, 'getFont', function () {
return this.getFont_NoClientCode();
});

Clazz.newMeth(C$, 'getFont_NoClientCode', function () {
var font = this.font;
if (font != null ) {
return font;
}var parent = this.parent;
if (parent != null ) return parent.getFont_NoClientCode();
return null;
});

Clazz.newMeth(C$, 'setFont$java_awt_Font', function (f) {
var oldFont;
var newFont;
oldFont=this.font;
newFont=this.font=f;
{
{
}var peer = this.getOrCreatePeer();
if (peer != null ) {
f=this.getFont();
if (f != null ) {
peer.setFont$java_awt_Font(f);
this.peerFont=f;
}}}this.firePropertyChange$S$O$O("font", oldFont, newFont);
if (f !== oldFont  && (oldFont == null  || !oldFont.equals$O(f) ) ) {
this.invalidateIfValid();
}});

Clazz.newMeth(C$, 'isFontSet', function () {
return (this.font != null );
});

Clazz.newMeth(C$, 'getLocale', function () {
var locale = this.locale;
if (locale != null ) {
return locale;
}return (I$[8]||$incl$(8)).ENGLISH;
});

Clazz.newMeth(C$, 'setLocale$java_util_Locale', function (l) {
var oldValue = this.locale;
this.locale=l;
this.firePropertyChange$S$O$O("locale", oldValue, l);
this.invalidateIfValid();
});

Clazz.newMeth(C$, 'getLocation', function () {
return this.location();
});

Clazz.newMeth(C$, 'getLocationOnScreen', function () {
return this.getLocationOnScreen_NoTreeLock();
});

Clazz.newMeth(C$, 'getLocationOnScreen_NoTreeLock', function () {
if (this.isShowing()) {
if (this.isLightweight()) {
var host = this.getNativeContainer();
var pt = host.peer.getLocationOnScreen();
for (var c = this; c !== host ; c=c.getParent()) {
pt.x+=c.x;
pt.y+=c.y;
}
return pt;
} else {
var pt = this.peer.getLocationOnScreen();
return pt;
}} else {
throw Clazz.new_(Clazz.load('java.awt.IllegalComponentStateException').c$$S,["component must be showing on the screen to determine its location"]);
}});

Clazz.newMeth(C$, 'location', function () {
return p$.location_NoClientCode.apply(this, []);
});

Clazz.newMeth(C$, 'location_NoClientCode', function () {
return Clazz.new_((I$[6]||$incl$(6)).c$$I$I,[this.x, this.y]);
});

Clazz.newMeth(C$, 'setLocation$I$I', function (x, y) {
this.setBoundsOp$I(1);
this.setBounds$I$I$I$I(x, y, this.width, this.height);
});

Clazz.newMeth(C$, 'move$I$I', function (x, y) {
this.setLocation$I$I(x, y);
});

Clazz.newMeth(C$, 'setLocation$java_awt_Point', function (p) {
this.setLocation$I$I(p.x, p.y);
});

Clazz.newMeth(C$, 'getSize', function () {
return this.size();
});

Clazz.newMeth(C$, 'size', function () {
return Clazz.new_((I$[9]||$incl$(9)).c$$I$I,[this.width, this.height]);
});

Clazz.newMeth(C$, 'setSize$I$I', function (width, height) {
this.resize$I$I(width, height);
});

Clazz.newMeth(C$, 'resize$I$I', function (width, height) {
this.setBoundsOp$I(2);
this.setBounds$I$I$I$I(this.x, this.y, width, height);
});

Clazz.newMeth(C$, 'setSize$java_awt_Dimension', function (d) {
this.setSize$I$I(d.width, d.height);
});

Clazz.newMeth(C$, 'setBounds$I$I$I$I', function (x, y, width, height) {
this.reshape$I$I$I$I(x, y, width, height);
});

Clazz.newMeth(C$, 'setBounds$java_awt_Rectangle', function (r) {
this.setBounds$I$I$I$I(r.x, r.y, r.width, r.height);
});

Clazz.newMeth(C$, 'reshape$I$I$I$I', function (x, y, width, height) {
try {
this.setBoundsOp$I(3);
width=Math.max(0, width);
height=Math.max(0, height);
var resized = (this.width != width) || (this.height != height) ;
var moved = (this.x != x) || (this.y != y) ;
if (!resized && !moved ) {
return;
}var oldX = this.x;
var oldY = this.y;
var oldWidth = this.width;
var oldHeight = this.height;
this.x=x;
this.y=y;
this.width=width;
this.height=height;
if (resized) {
this.isPacked=false;
}var needNotify = true;
this.mixOnReshaping();
if (this.getOrCreatePeer() != null ) {
p$.reshapeNativePeer$I$I$I$I$I.apply(this, [x, y, width, height, this.getBoundsOp()]);
resized=(oldWidth != this.width) || (oldHeight != this.height) ;
moved=(oldX != this.x) || (oldY != this.y) ;
if (Clazz.instanceOf(this, "java.awt.Window")) {
needNotify=false;
}if (resized) {
this.invalidate();
}if (this.parent != null ) {
this.parent.invalidateIfValid();
}}if (needNotify) {
p$.notifyNewBounds$Z$Z.apply(this, [resized, moved]);
}p$.repaintParentIfNeeded$I$I$I$I.apply(this, [oldX, oldY, oldWidth, oldHeight]);
} finally {
this.setBoundsOp$I(5);
}
});

Clazz.newMeth(C$, 'repaintParentIfNeeded$I$I$I$I', function (oldX, oldY, oldWidth, oldHeight) {
if (this.parent != null  && Clazz.instanceOf(this.peer, "java.awt.peer.LightweightPeer")  && this.isShowing() ) {
this.parent.repaint$I$I$I$I(oldX, oldY, oldWidth, oldHeight);
this.repaint();
}});

Clazz.newMeth(C$, 'reshapeNativePeer$I$I$I$I$I', function (x, y, width, height, op) {
var nativeX = x;
var nativeY = y;
for (var c = this.parent; (c != null ) && (Clazz.instanceOf(c.peer, "java.awt.peer.LightweightPeer")) ; c=c.parent) {
nativeX+=c.x;
nativeY+=c.y;
}
this.peer.setBounds$I$I$I$I$I(nativeX, nativeY, width, height, op);
});

Clazz.newMeth(C$, 'notifyNewBounds$Z$Z', function (resized, moved) {
if (this.componentListener != null  || (this.eventMask & 1) != 0  || (I$[5]||$incl$(5)).enabledOnToolkit$J(1) ) {
if (resized) {
var e = Clazz.new_((I$[7]||$incl$(7)).c$$java_awt_Component$I,[this, 101]);
(I$[5]||$incl$(5)).getEventQueue().postEvent$java_awt_AWTEvent(e);
}if (moved) {
var e = Clazz.new_((I$[7]||$incl$(7)).c$$java_awt_Component$I,[this, 100]);
(I$[5]||$incl$(5)).getEventQueue().postEvent$java_awt_AWTEvent(e);
}} else {
if (Clazz.instanceOf(this, "java.awt.Container") && (this).countComponents() > 0 ) {
var enabledOnToolkit = (I$[5]||$incl$(5)).enabledOnToolkit$J(65536);
if (resized) {
(this).createChildHierarchyEvents$I$J$Z(1402, 0, enabledOnToolkit);
}if (moved) {
(this).createChildHierarchyEvents$I$J$Z(1401, 0, enabledOnToolkit);
}}}});

Clazz.newMeth(C$, 'getX', function () {
return this.x;
});

Clazz.newMeth(C$, 'getY', function () {
return this.y;
});

Clazz.newMeth(C$, 'getWidth', function () {
return this.width;
});

Clazz.newMeth(C$, 'getHeight', function () {
return this.height;
});

Clazz.newMeth(C$, 'getBounds', function () {
return Clazz.new_((I$[10]||$incl$(10)).c$$I$I$I$I,[this.x, this.y, this.width, this.height]);
});

Clazz.newMeth(C$, 'getBounds$java_awt_Rectangle', function (rv) {
if (rv == null ) return Clazz.new_((I$[10]||$incl$(10)).c$$I$I$I$I,[this.getX(), this.getY(), this.getWidth(), this.getHeight()]);
rv.setBounds$I$I$I$I(this.getX(), this.getY(), this.getWidth(), this.getHeight());
return rv;
});

Clazz.newMeth(C$, 'getSize$java_awt_Dimension', function (rv) {
if (rv == null ) {
return Clazz.new_((I$[9]||$incl$(9)).c$$I$I,[this.getWidth(), this.getHeight()]);
} else {
rv.setSize$I$I(this.getWidth(), this.getHeight());
return rv;
}});

Clazz.newMeth(C$, 'getLocation$java_awt_Point', function (rv) {
if (rv == null ) {
return Clazz.new_((I$[6]||$incl$(6)).c$$I$I,[this.getX(), this.getY()]);
} else {
rv.setLocation$I$I(this.getX(), this.getY());
return rv;
}});

Clazz.newMeth(C$, 'isOpaque', function () {
return true;
});

Clazz.newMeth(C$, 'isLightweight', function () {
return false;
});

Clazz.newMeth(C$, 'setPreferredSize$java_awt_Dimension', function (preferredSize) {
this.setPrefSizeComp$java_awt_Dimension(preferredSize);
});

Clazz.newMeth(C$, 'setPrefSizeComp$java_awt_Dimension', function (preferredSize) {
var old = (this.prefSizeSet ? this.prefSize : null);
this.prefSize=preferredSize;
this.prefSizeSet=(preferredSize != null );
this.firePropertyChange$S$O$O("preferredSize", old, preferredSize);
});

Clazz.newMeth(C$, 'isPreferredSizeSet', function () {
return this.prefSizeSet;
});

Clazz.newMeth(C$, 'getPreferredSize', function () {
return this.preferredSize();
});

Clazz.newMeth(C$, 'preferredSize', function () {
return this.prefSizeComp();
});

Clazz.newMeth(C$, 'prefSizeComp', function () {
var dim = this.prefSize;
if (dim == null  || !(this.isPreferredSizeSet() || this.isValid() ) ) {
this.prefSize=this.getMinimumSize();
dim=this.prefSize;
}return Clazz.new_((I$[9]||$incl$(9)).c$$java_awt_Dimension,[dim]);
});

Clazz.newMeth(C$, 'setMinimumSize$java_awt_Dimension', function (minimumSize) {
var old;
if (this.minSizeSet) {
old=this.minSize;
} else {
old=null;
}this.minSize=minimumSize;
this.minSizeSet=(minimumSize != null );
this.firePropertyChange$S$O$O("minimumSize", old, minimumSize);
});

Clazz.newMeth(C$, 'isMinimumSizeSet', function () {
return this.minSizeSet;
});

Clazz.newMeth(C$, 'getMinimumSize', function () {
return this.minimumSize();
});

Clazz.newMeth(C$, 'minimumSize', function () {
var dim = this.minSize;
if (dim == null  || !(this.isMinimumSizeSet() || this.isValid() ) ) {
this.minSize=this.getSize();
dim=this.minSize;
}return Clazz.new_((I$[9]||$incl$(9)).c$$java_awt_Dimension,[dim]);
});

Clazz.newMeth(C$, 'setMaximumSize$java_awt_Dimension', function (maximumSize) {
var old;
if (this.maxSizeSet) {
old=this.maxSize;
} else {
old=null;
}this.maxSize=maximumSize;
this.maxSizeSet=(maximumSize != null );
this.firePropertyChange$S$O$O("maximumSize", old, maximumSize);
});

Clazz.newMeth(C$, 'isMaximumSizeSet', function () {
return this.maxSizeSet;
});

Clazz.newMeth(C$, 'getMaximumSize', function () {
return this.getMaxSizeComp();
});

Clazz.newMeth(C$, 'getMaxSizeComp', function () {
if (this.isMaximumSizeSet()) {
return Clazz.new_((I$[9]||$incl$(9)).c$$java_awt_Dimension,[this.maxSize]);
}return Clazz.new_((I$[9]||$incl$(9)).c$$I$I,[32767, 32767]);
});

Clazz.newMeth(C$, 'getAlignmentX', function () {
return this.getAlignmentXComp();
});

Clazz.newMeth(C$, 'getAlignmentXComp', function () {
return 0.5;
});

Clazz.newMeth(C$, 'getAlignmentY', function () {
return this.getAlignmentYComp();
});

Clazz.newMeth(C$, 'getAlignmentYComp', function () {
return 0.5;
});

Clazz.newMeth(C$, 'getBaseline$I$I', function (width, height) {
if (width < 0 || height < 0 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Width and height must be >= 0"]);
}return -1;
});

Clazz.newMeth(C$, 'getBaselineResizeBehavior', function () {
return (I$[11]||$incl$(11)).OTHER;
});

Clazz.newMeth(C$, 'doLayout', function () {
this.layout();
});

Clazz.newMeth(C$, 'layout', function () {
});

Clazz.newMeth(C$, 'validate', function () {
this.validateComponent();
});

Clazz.newMeth(C$, 'validateComponent', function () {
{
var peer = this.peer;
var wasValid = this.isValid();
if (!wasValid && peer != null  ) {
var newfont = this.getFont();
var oldfont = this.peerFont;
if (newfont !== oldfont  && (oldfont == null  || !oldfont.equals$O(newfont) ) ) {
peer.setFont$java_awt_Font(newfont);
this.peerFont=newfont;
}peer.layout();
}this.valid=true;
if (!wasValid) {
this.mixOnValidating();
}}});

Clazz.newMeth(C$, 'invalidate', function () {
this.invalidateComp();
});

Clazz.newMeth(C$, 'invalidateComp', function () {
this.valid=false;
if (!this.isPreferredSizeSet()) {
this.prefSize=null;
}if (!this.isMinimumSizeSet()) {
this.minSize=null;
}if (!this.isMaximumSizeSet()) {
this.maxSize=null;
}if (this.parent != null ) {
this.parent.invalidateIfValid();
}});

Clazz.newMeth(C$, 'invalidateIfValid', function () {
if (this.isValid()) {
this.invalidate();
}});

Clazz.newMeth(C$, 'getGraphics', function () {
var g;
if ((g=(this.parent == null  ? null : this.parent.getGraphics())) != null ) g.setFont$java_awt_Font(this.getFont());
return g;
});

Clazz.newMeth(C$, 'getTreeLock', function () {
return this;
});

Clazz.newMeth(C$, 'getFontMetrics$java_awt_Font', function (font) {
return font.getFontMetrics();
});

Clazz.newMeth(C$, 'setCursor$java_awt_Cursor', function (cursor) {
this.cursor=cursor;
this.updateCursorImmediately();
});

Clazz.newMeth(C$, 'updateCursorImmediately', function () {
(I$[4]||$incl$(4)).setCursor$java_awt_Cursor(this.cursor);
});

Clazz.newMeth(C$, 'getCursor', function () {
return this.getCursor_NoClientCode();
});

Clazz.newMeth(C$, 'getCursor_NoClientCode', function () {
var cursor = this.cursor;
if (cursor != null ) {
return cursor;
}var parent = this.parent;
if (parent != null ) {
return parent.getCursor_NoClientCode();
} else {
return (I$[12]||$incl$(12)).getPredefinedCursor$I(0);
}});

Clazz.newMeth(C$, 'isCursorSet', function () {
return (this.cursor != null );
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
});

Clazz.newMeth(C$, 'update$java_awt_Graphics', function (g) {
this.paint$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'paintAll$java_awt_Graphics', function (g) {
});

Clazz.newMeth(C$, 'lightweightPaint$java_awt_Graphics', function (g) {
this.lwPaintComp$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'lwPaintComp$java_awt_Graphics', function (g) {
this.paint$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'paintHeavyweightComponents$java_awt_Graphics', function (g) {
});

Clazz.newMeth(C$, 'repaint', function () {
this.repaint$J$I$I$I$I(0, 0, 0, this.width, this.height);
});

Clazz.newMeth(C$, 'repaint$J', function (tm) {
this.repaint$J$I$I$I$I(tm, 0, 0, this.width, this.height);
});

Clazz.newMeth(C$, 'repaint$I$I$I$I', function (x, y, width, height) {
this.repaint$J$I$I$I$I(0, x, y, width, height);
});

Clazz.newMeth(C$, 'repaint$J$I$I$I$I', function (tm, x, y, width, height) {
if (this.canPaint()) {
if (this.peer != null ) {
if (this.isVisible() && width > 0  && height > 0 ) {
var e = Clazz.new_((I$[13]||$incl$(13)).c$$java_awt_Component$I$java_awt_Rectangle,[this, 801, Clazz.new_((I$[10]||$incl$(10)).c$$I$I$I$I,[x, y, width, height])]);
(I$[5]||$incl$(5)).getEventQueue().postEvent$java_awt_AWTEvent(e);
} else {
this.peer.setVisible$Z(false);
}}} else if (this.parent != null ) {
var px = this.x + ((x < 0) ? 0 : x);
var py = this.y + ((y < 0) ? 0 : y);
var pwidth = (width > this.width) ? this.width : width;
var pheight = (height > this.height) ? this.height : height;
this.parent.repaint$J$I$I$I$I(tm, px, py, pwidth, pheight);
}});

Clazz.newMeth(C$, 'canPaint', function () {
return !(Clazz.instanceOf(this.peer, "java.awt.peer.LightweightPeer"));
});

Clazz.newMeth(C$, 'print$java_awt_Graphics', function (g) {
this.paint$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'printAll$java_awt_Graphics', function (g) {
});

Clazz.newMeth(C$, 'printHeavyweightComponents$java_awt_Graphics', function (g) {
});

Clazz.newMeth(C$, 'imageUpdate$java_awt_Image$I$I$I$I$I', function (img, infoflags, x, y, w, h) {
return false;
});

Clazz.newMeth(C$, 'createImage$java_awt_image_ImageProducer', function (producer) {
return this.getToolkit().createImage$java_awt_image_ImageProducer(producer);
});

Clazz.newMeth(C$, 'createImage$I$I', function (width, height) {
return (I$[5]||$incl$(5)).getDefaultToolkit().createImage$BA$I$I(null, width, height);
});

Clazz.newMeth(C$, 'createVolatileImage$I$I', function (width, height) {
return null;
});

Clazz.newMeth(C$, 'createVolatileImage$I$I$java_awt_ImageCapabilities', function (width, height, caps) {
return this.createVolatileImage$I$I(width, height);
});

Clazz.newMeth(C$, 'prepareImage$java_awt_Image$java_awt_image_ImageObserver', function (image, observer) {
return this.prepareImage$java_awt_Image$I$I$java_awt_image_ImageObserver(image, -1, -1, observer);
});

Clazz.newMeth(C$, 'prepareImage$java_awt_Image$I$I$java_awt_image_ImageObserver', function (image, width, height, observer) {
return false;
});

Clazz.newMeth(C$, 'checkImage$java_awt_Image$java_awt_image_ImageObserver', function (image, observer) {
return this.checkImage$java_awt_Image$I$I$java_awt_image_ImageObserver(image, -1, -1, observer);
});

Clazz.newMeth(C$, 'checkImage$java_awt_Image$I$I$java_awt_image_ImageObserver', function (image, width, height, observer) {
return 35;
});

Clazz.newMeth(C$, 'setIgnoreRepaint$Z', function (ignoreRepaint) {
});

Clazz.newMeth(C$, 'getIgnoreRepaint', function () {
return false;
});

Clazz.newMeth(C$, 'contains$java_awt_Point', function (p) {
return this.contains$I$I(p.x, p.y);
});

Clazz.newMeth(C$, 'contains$I$I', function (x, y) {
return this.inside$I$I(x, y);
});

Clazz.newMeth(C$, 'inside$I$I', function (x, y) {
return (x >= 0) && (x < this.width) && (y >= 0) && (y < this.height)  ;
});

Clazz.newMeth(C$, 'getComponentAt$I$I', function (x, y) {
return this.locate$I$I(x, y);
});

Clazz.newMeth(C$, 'locate$I$I', function (x, y) {
return this.contains$I$I(x, y) ? this : null;
});

Clazz.newMeth(C$, 'getComponentAt$java_awt_Point', function (p) {
return this.getComponentAt$I$I(p.x, p.y);
});

Clazz.newMeth(C$, 'deliverEvent$java_awt_Event', function (e) {
this.postEvent$java_awt_Event(e);
});

Clazz.newMeth(C$, 'dispatchEvent$java_awt_AWTEvent', function (e) {
this.dispatchEventImpl$java_awt_AWTEvent(e);
});

Clazz.newMeth(C$, 'dispatchEventImpl$java_awt_AWTEvent', function (e) {
this.dispatchEventImplComp$java_awt_AWTEvent(e);
});

Clazz.newMeth(C$, 'dispatchEventImplComp$java_awt_AWTEvent', function (e) {
var id = e.getID();
(I$[14]||$incl$(14)).setCurrentEventAndMostRecentTime$java_awt_AWTEvent(e);
if (!e.focusManagerIsDispatching) {
if (e.isPosted) {
e.isPosted=true;
}}if (!e.isConsumed()) {
if (Clazz.instanceOf(e, "java.awt.event.KeyEvent")) {
if (e.isConsumed()) {
return;
}}}if (this.areInputMethodsEnabled()) {
if ((Clazz.instanceOf(e, "java.awt.event.InputEvent")) || (Clazz.instanceOf(e, "java.awt.event.FocusEvent")) ) {
}} else {
if (id == 1004) {
}}switch (id) {
case 401:
case 402:
var p = ((Clazz.instanceOf(this, "java.awt.Container")) ? this : this.parent);
if (p != null ) {
p.preProcessKeyEvent$java_awt_event_KeyEvent(e);
}break;
case 201:
break;
default:
break;
}
if (this.newEventsOnly) {
if (this.eventEnabled$java_awt_AWTEvent(e)) {
this.processEvent$java_awt_AWTEvent(e);
}} else if (id == 507) {
this.autoProcessMouseWheel$java_awt_event_MouseWheelEvent(e);
} else if (!(Clazz.instanceOf(e, "java.awt.event.MouseEvent") && !this.postsOldMouseEvents() )) {
}if (id == 201 && !e.isConsumed() ) {
}if (!(Clazz.instanceOf(e, "java.awt.event.KeyEvent"))) {
}});

Clazz.newMeth(C$, 'autoProcessMouseWheel$java_awt_event_MouseWheelEvent', function (e) {
});

Clazz.newMeth(C$, 'dispatchMouseWheelToAncestor$java_awt_event_MouseWheelEvent', function (e) {
var newX;
var newY;
newX=e.getX() + this.getX();
newY=e.getY() + this.getY();
var newMWE;
{
var anc = this.getParent();
while (anc != null  && !anc.eventEnabled$java_awt_AWTEvent(e) ){
newX+=anc.getX();
newY+=anc.getY();
if (!(Clazz.instanceOf(anc, "java.awt.Window"))) {
anc=anc.getParent();
} else {
break;
}}
if (anc != null  && anc.eventEnabled$java_awt_AWTEvent(e) ) {
newMWE=Clazz.new_((I$[15]||$incl$(15)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I$I$I,[anc, e.getID(), e.getWhen(), e.getModifiers(), newX, newY, e.getXOnScreen(), e.getYOnScreen(), e.getClickCount(), e.isPopupTrigger(), e.getScrollType(), e.getScrollAmount(), e.getWheelRotation()]);
(e).copyPrivateDataInto$java_awt_AWTEvent(newMWE);
anc.dispatchEventToSelf$java_awt_AWTEvent(newMWE);
}}return true;
});

Clazz.newMeth(C$, 'checkWindowClosingException', function () {
if (this.windowClosingException != null ) {
if (Clazz.instanceOf(this, "java.awt.Dialog")) {
(this).interruptBlocking();
} else {
this.windowClosingException.fillInStackTrace();
this.windowClosingException.printStackTrace();
this.windowClosingException=null;
}return true;
}return false;
});

Clazz.newMeth(C$, 'areInputMethodsEnabled', function () {
return ((this.eventMask & 4096) != 0) && ((this.eventMask & 8) != 0 || this.keyListener != null  ) ;
});

Clazz.newMeth(C$, 'eventEnabled$java_awt_AWTEvent', function (e) {
return this.eventTypeEnabled$I(e.id);
});

Clazz.newMeth(C$, 'eventTypeEnabled$I', function (type) {
switch (type) {
case 100:
case 101:
case 102:
case 103:
if ((this.eventMask & 1) != 0 || this.componentListener != null  ) {
return true;
}break;
case 1004:
case 1005:
if ((this.eventMask & 4) != 0 || this.focusListener != null  ) {
return true;
}break;
case 401:
case 402:
case 400:
if ((this.eventMask & 8) != 0 || this.keyListener != null  ) {
return true;
}break;
case 501:
case 502:
case 504:
case 505:
case 500:
if ((this.eventMask & 16) != 0 || this.mouseListener != null  ) {
return true;
}break;
case 503:
case 506:
if ((this.eventMask & 32) != 0 || this.mouseMotionListener != null  ) {
return true;
}break;
case 507:
if ((this.eventMask & 131072) != 0 || this.mouseWheelListener != null  ) {
return true;
}break;
case 1100:
case 1101:
if ((this.eventMask & 2048) != 0 || this.inputMethodListener != null  ) {
return true;
}break;
case 1400:
if ((this.eventMask & 32768) != 0 || this.hierarchyListener != null  ) {
return true;
}break;
case 1401:
case 1402:
if ((this.eventMask & 65536) != 0 || this.hierarchyBoundsListener != null  ) {
return true;
}break;
case 1001:
if ((this.eventMask & 128) != 0) {
return true;
}break;
case 900:
if ((this.eventMask & 1024) != 0) {
return true;
}break;
case 701:
if ((this.eventMask & 512) != 0) {
return true;
}break;
case 601:
if ((this.eventMask & 256) != 0) {
return true;
}break;
default:
break;
}
if (type > 1999) {
return true;
}return false;
});

Clazz.newMeth(C$, 'postEvent$java_awt_Event', function (e) {
if (this.handleEvent$java_awt_Event(e)) {
e.consume();
return true;
}var parent = this.parent;
var eventx = e.x;
var eventy = e.y;
if (parent != null ) {
e.translate$I$I(this.x, this.y);
if (parent.postEvent$java_awt_Event(e)) {
e.consume();
return true;
}e.x=eventx;
e.y=eventy;
}return false;
});

Clazz.newMeth(C$, 'addComponentListener$java_awt_event_ComponentListener', function (l) {
if (l == null ) {
return;
}this.componentListener=(I$[16]||$incl$(16)).add$java_awt_event_ComponentListener$java_awt_event_ComponentListener(this.componentListener, l);
this.newEventsOnly=true;
});

Clazz.newMeth(C$, 'removeComponentListener$java_awt_event_ComponentListener', function (l) {
if (l == null ) {
return;
}this.componentListener=(I$[16]||$incl$(16)).remove$java_awt_event_ComponentListener$java_awt_event_ComponentListener(this.componentListener, l);
});

Clazz.newMeth(C$, 'getComponentListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[17]||$incl$(17)),['componentHidden$java_awt_event_ComponentEvent','componentMoved$java_awt_event_ComponentEvent','componentResized$java_awt_event_ComponentEvent','componentShown$java_awt_event_ComponentEvent'])));
});

Clazz.newMeth(C$, 'addFocusListener$java_awt_event_FocusListener', function (l) {
if (l == null ) {
return;
}this.focusListener=(I$[16]||$incl$(16)).add$java_awt_event_FocusListener$java_awt_event_FocusListener(this.focusListener, l);
this.newEventsOnly=true;
});

Clazz.newMeth(C$, 'removeFocusListener$java_awt_event_FocusListener', function (l) {
if (l == null ) {
return;
}this.focusListener=(I$[16]||$incl$(16)).remove$java_awt_event_FocusListener$java_awt_event_FocusListener(this.focusListener, l);
});

Clazz.newMeth(C$, 'getFocusListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[18]||$incl$(18)),['focusGained$java_awt_event_FocusEvent','focusLost$java_awt_event_FocusEvent'])));
});

Clazz.newMeth(C$, 'addHierarchyListener$java_awt_event_HierarchyListener', function (l) {
});

Clazz.newMeth(C$, 'removeHierarchyListener$java_awt_event_HierarchyListener', function (l) {
});

Clazz.newMeth(C$, 'getHierarchyListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[19]||$incl$(19)),['hierarchyChanged$java_awt_event_HierarchyEvent'])));
});

Clazz.newMeth(C$, 'addHierarchyBoundsListener$java_awt_event_HierarchyBoundsListener', function (l) {
if (l == null ) {
return;
}var notifyAncestors;
{
notifyAncestors=(this.hierarchyBoundsListener == null  && (this.eventMask & 65536) == 0 );
this.hierarchyBoundsListener=(I$[16]||$incl$(16)).add$java_awt_event_HierarchyBoundsListener$java_awt_event_HierarchyBoundsListener(this.hierarchyBoundsListener, l);
notifyAncestors=(notifyAncestors && this.hierarchyBoundsListener != null  );
this.newEventsOnly=true;
}if (notifyAncestors) {
{
this.adjustListeningChildrenOnParent$J$I(65536, 1);
}}});

Clazz.newMeth(C$, 'removeHierarchyBoundsListener$java_awt_event_HierarchyBoundsListener', function (l) {
if (l == null ) {
return;
}var notifyAncestors;
{
notifyAncestors=(this.hierarchyBoundsListener != null  && (this.eventMask & 65536) == 0 );
this.hierarchyBoundsListener=(I$[16]||$incl$(16)).remove$java_awt_event_HierarchyBoundsListener$java_awt_event_HierarchyBoundsListener(this.hierarchyBoundsListener, l);
notifyAncestors=(notifyAncestors && this.hierarchyBoundsListener == null  );
}if (notifyAncestors) {
{
this.adjustListeningChildrenOnParent$J$I(65536, -1);
}}});

Clazz.newMeth(C$, 'numListening$J', function (mask) {
return this.numListeningMask$J(mask);
});

Clazz.newMeth(C$, 'numListeningMask$J', function (mask) {
if ((mask == 32768 && (this.hierarchyListener != null  || (this.eventMask & 32768) != 0 ) ) || (mask == 65536 && (this.hierarchyBoundsListener != null  || (this.eventMask & 65536) != 0 ) ) ) {
return 1;
} else {
return 0;
}});

Clazz.newMeth(C$, 'countHierarchyMembers', function () {
return 1;
});

Clazz.newMeth(C$, 'createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z', function (id, changed, changedParent, changeFlags, enabledOnToolkit) {
return this.createHierEventsComp$I$java_awt_Component$java_awt_Container$J$Z(id, changed, changedParent, changeFlags, enabledOnToolkit);
});

Clazz.newMeth(C$, 'createHierEventsComp$I$java_awt_Component$java_awt_Container$J$Z', function (id, changed, changedParent, changeFlags, enabledOnToolkit) {
switch (id) {
case 1400:
if (this.hierarchyListener != null  || (this.eventMask & 32768) != 0  || enabledOnToolkit ) {
var e = Clazz.new_((I$[20]||$incl$(20)).c$$java_awt_Component$I$java_awt_Component$java_awt_Container$J,[this, id, changed, changedParent, changeFlags]);
this.dispatchEvent$java_awt_AWTEvent(e);
return 1;
}break;
case 1401:
case 1402:
if (this.hierarchyBoundsListener != null  || (this.eventMask & 65536) != 0  || enabledOnToolkit ) {
var e = Clazz.new_((I$[20]||$incl$(20)).c$$java_awt_Component$I$java_awt_Component$java_awt_Container,[this, id, changed, changedParent]);
this.dispatchEvent$java_awt_AWTEvent(e);
return 1;
}break;
default:
break;
}
return 0;
});

Clazz.newMeth(C$, 'getHierarchyBoundsListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[21]||$incl$(21)),['ancestorMoved$java_awt_event_HierarchyEvent','ancestorResized$java_awt_event_HierarchyEvent'])));
});

Clazz.newMeth(C$, 'adjustListeningChildrenOnParent$J$I', function (mask, num) {
if (this.parent != null ) {
this.parent.adjustListeningChildren$J$I(mask, num);
}});

Clazz.newMeth(C$, 'addKeyListener$java_awt_event_KeyListener', function (l) {
if (l == null ) {
return;
}this.keyListener=(I$[16]||$incl$(16)).add$java_awt_event_KeyListener$java_awt_event_KeyListener(this.keyListener, l);
this.newEventsOnly=true;
});

Clazz.newMeth(C$, 'removeKeyListener$java_awt_event_KeyListener', function (l) {
if (l == null ) {
return;
}this.keyListener=(I$[16]||$incl$(16)).remove$java_awt_event_KeyListener$java_awt_event_KeyListener(this.keyListener, l);
});

Clazz.newMeth(C$, 'getKeyListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[22]||$incl$(22)),['keyPressed$java_awt_event_KeyEvent','keyReleased$java_awt_event_KeyEvent','keyTyped$java_awt_event_KeyEvent'])));
});

Clazz.newMeth(C$, 'addMouseListener$java_awt_event_MouseListener', function (l) {
if (l == null ) {
return;
}this.mouseListener=(I$[16]||$incl$(16)).add$java_awt_event_MouseListener$java_awt_event_MouseListener(this.mouseListener, l);
this.newEventsOnly=true;
});

Clazz.newMeth(C$, 'removeMouseListener$java_awt_event_MouseListener', function (l) {
if (l == null ) {
return;
}this.mouseListener=(I$[16]||$incl$(16)).remove$java_awt_event_MouseListener$java_awt_event_MouseListener(this.mouseListener, l);
});

Clazz.newMeth(C$, 'getMouseListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[23]||$incl$(23)),['mouseClicked$java_awt_event_MouseEvent','mouseEntered$java_awt_event_MouseEvent','mouseExited$java_awt_event_MouseEvent','mousePressed$java_awt_event_MouseEvent','mouseReleased$java_awt_event_MouseEvent'])));
});

Clazz.newMeth(C$, 'addMouseMotionListener$java_awt_event_MouseMotionListener', function (l) {
if (l == null ) {
return;
}this.mouseMotionListener=(I$[16]||$incl$(16)).add$java_awt_event_MouseMotionListener$java_awt_event_MouseMotionListener(this.mouseMotionListener, l);
this.newEventsOnly=true;
});

Clazz.newMeth(C$, 'removeMouseMotionListener$java_awt_event_MouseMotionListener', function (l) {
if (l == null ) {
return;
}this.mouseMotionListener=(I$[16]||$incl$(16)).remove$java_awt_event_MouseMotionListener$java_awt_event_MouseMotionListener(this.mouseMotionListener, l);
});

Clazz.newMeth(C$, 'getMouseMotionListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[24]||$incl$(24)),['mouseDragged$java_awt_event_MouseEvent','mouseMoved$java_awt_event_MouseEvent'])));
});

Clazz.newMeth(C$, 'addMouseWheelListener$java_awt_event_MouseWheelListener', function (l) {
if (l == null ) {
return;
}this.mouseWheelListener=(I$[16]||$incl$(16)).add$java_awt_event_MouseWheelListener$java_awt_event_MouseWheelListener(this.mouseWheelListener, l);
this.newEventsOnly=true;
});

Clazz.newMeth(C$, 'removeMouseWheelListener$java_awt_event_MouseWheelListener', function (l) {
if (l == null ) {
return;
}this.mouseWheelListener=(I$[16]||$incl$(16)).remove$java_awt_event_MouseWheelListener$java_awt_event_MouseWheelListener(this.mouseWheelListener, l);
});

Clazz.newMeth(C$, 'getMouseWheelListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[25]||$incl$(25)),['mouseWheelMoved$java_awt_event_MouseWheelEvent'])));
});

Clazz.newMeth(C$, 'addInputMethodListener$java_awt_event_InputMethodListener', function (l) {
if (l == null ) {
return;
}this.inputMethodListener=(I$[16]||$incl$(16)).add$java_awt_event_InputMethodListener$java_awt_event_InputMethodListener(this.inputMethodListener, l);
this.newEventsOnly=true;
});

Clazz.newMeth(C$, 'removeInputMethodListener$java_awt_event_InputMethodListener', function (l) {
if (l == null ) {
return;
}this.inputMethodListener=(I$[16]||$incl$(16)).remove$java_awt_event_InputMethodListener$java_awt_event_InputMethodListener(this.inputMethodListener, l);
});

Clazz.newMeth(C$, 'getInputMethodListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[26]||$incl$(26)),['caretPositionChanged$java_awt_event_InputMethodEvent','inputMethodTextChanged$java_awt_event_InputMethodEvent'])));
});

Clazz.newMeth(C$, 'getListeners$Class', function (listenerType) {
return this.getListenersComp$Class(listenerType);
});

Clazz.newMeth(C$, 'getListenersComp$Class', function (listenerType) {
var l = null;
if (listenerType === Clazz.getClass((I$[17]||$incl$(17)),['componentHidden$java_awt_event_ComponentEvent','componentMoved$java_awt_event_ComponentEvent','componentResized$java_awt_event_ComponentEvent','componentShown$java_awt_event_ComponentEvent']) ) {
l=this.componentListener;
} else if (listenerType === Clazz.getClass((I$[18]||$incl$(18)),['focusGained$java_awt_event_FocusEvent','focusLost$java_awt_event_FocusEvent']) ) {
l=this.focusListener;
} else if (listenerType === Clazz.getClass((I$[19]||$incl$(19)),['hierarchyChanged$java_awt_event_HierarchyEvent']) ) {
l=this.hierarchyListener;
} else if (listenerType === Clazz.getClass((I$[21]||$incl$(21)),['ancestorMoved$java_awt_event_HierarchyEvent','ancestorResized$java_awt_event_HierarchyEvent']) ) {
l=this.hierarchyBoundsListener;
} else if (listenerType === Clazz.getClass((I$[22]||$incl$(22)),['keyPressed$java_awt_event_KeyEvent','keyReleased$java_awt_event_KeyEvent','keyTyped$java_awt_event_KeyEvent']) ) {
l=this.keyListener;
} else if (listenerType === Clazz.getClass((I$[23]||$incl$(23)),['mouseClicked$java_awt_event_MouseEvent','mouseEntered$java_awt_event_MouseEvent','mouseExited$java_awt_event_MouseEvent','mousePressed$java_awt_event_MouseEvent','mouseReleased$java_awt_event_MouseEvent']) ) {
l=this.mouseListener;
} else if (listenerType === Clazz.getClass((I$[24]||$incl$(24)),['mouseDragged$java_awt_event_MouseEvent','mouseMoved$java_awt_event_MouseEvent']) ) {
l=this.mouseMotionListener;
} else if (listenerType === Clazz.getClass((I$[25]||$incl$(25)),['mouseWheelMoved$java_awt_event_MouseWheelEvent']) ) {
l=this.mouseWheelListener;
} else if (listenerType === Clazz.getClass((I$[26]||$incl$(26)),['caretPositionChanged$java_awt_event_InputMethodEvent','inputMethodTextChanged$java_awt_event_InputMethodEvent']) ) {
l=this.inputMethodListener;
} else if (listenerType === Clazz.getClass((I$[27]||$incl$(27)),['propertyChange$java_beans_PropertyChangeEvent']) ) {
return this.getPropertyChangeListeners();
}return (I$[16]||$incl$(16)).getListeners$java_util_EventListener$Class(l, listenerType);
});

Clazz.newMeth(C$, 'enableEvents$J', function (eventsToEnable) {
var notifyAncestors = 0;
{
if ((eventsToEnable & 32768) != 0 && this.hierarchyListener == null   && (this.eventMask & 32768) == 0 ) {
notifyAncestors|=32768;
}if ((eventsToEnable & 65536) != 0 && this.hierarchyBoundsListener == null   && (this.eventMask & 65536) == 0 ) {
notifyAncestors|=65536;
}this.eventMask|=eventsToEnable;
this.newEventsOnly=true;
}if (this.parent != null  && Clazz.instanceOf(this.peer, "java.awt.peer.LightweightPeer") ) {
this.parent.proxyEnableEvents$J(this.eventMask);
}if (notifyAncestors != 0) {
{
this.adjustListeningChildrenOnParent$J$I(notifyAncestors, 1);
}}});

Clazz.newMeth(C$, 'disableEvents$J', function (eventsToDisable) {
var notifyAncestors = 0;
{
if ((eventsToDisable & 32768) != 0 && this.hierarchyListener == null   && (this.eventMask & 32768) != 0 ) {
notifyAncestors|=32768;
}if ((eventsToDisable & 65536) != 0 && this.hierarchyBoundsListener == null   && (this.eventMask & 65536) != 0 ) {
notifyAncestors|=65536;
}this.eventMask&=~eventsToDisable;
}if (notifyAncestors != 0) {
{
this.adjustListeningChildrenOnParent$J$I(notifyAncestors, -1);
}}});

Clazz.newMeth(C$, 'checkCoalescing', function () {
if (this.getClass().getClassLoader() == null ) {
return false;
}var clazz = this.getClass();
{
var value = C$.coalesceMap.get$O(clazz);
if (value != null ) {
return value.booleanValue();
}var enabled = (I$[28]||$incl$(28)).$valueOf(p$.checkCoelescence.apply(this, []));
C$.coalesceMap.put$TK$TV(clazz, enabled);
return enabled.booleanValue();
}});

Clazz.newMeth(C$, 'checkCoelescence', function () {
{
return this.coalesceEvents$java_awt_AWTEvent$java_awt_AWTEvent.exClazz != C$;
}
});

Clazz.newMeth(C$, 'isCoalescingEnabled', function () {
return this.coalescingEnabled;
});

Clazz.newMeth(C$, 'coalesceEvents$java_awt_AWTEvent$java_awt_AWTEvent', function (existingEvent, newEvent) {
return null;
});

Clazz.newMeth(C$, 'processEvent$java_awt_AWTEvent', function (e) {
this.processEventComp$java_awt_AWTEvent(e);
});

Clazz.newMeth(C$, 'processEventComp$java_awt_AWTEvent', function (e) {
if (Clazz.instanceOf(e, "java.awt.event.FocusEvent")) {
this.processFocusEvent$java_awt_event_FocusEvent(e);
} else if (Clazz.instanceOf(e, "java.awt.event.MouseEvent")) {
switch (e.getID()) {
case 501:
case 502:
case 500:
case 504:
case 505:
this.processMouseEvent$java_awt_event_MouseEvent(e);
break;
case 503:
case 506:
this.processMouseMotionEvent$java_awt_event_MouseEvent(e);
break;
case 507:
this.processMouseWheelEvent$java_awt_event_MouseWheelEvent(e);
break;
}
} else if (Clazz.instanceOf(e, "java.awt.event.KeyEvent")) {
this.processKeyEvent$java_awt_event_KeyEvent(e);
} else if (Clazz.instanceOf(e, "java.awt.event.ComponentEvent")) {
this.processComponentEvent$java_awt_event_ComponentEvent(e);
} else if (Clazz.instanceOf(e, "java.awt.event.InputMethodEvent")) {
this.processInputMethodEvent$java_awt_event_InputMethodEvent(e);
} else if (Clazz.instanceOf(e, "java.awt.event.HierarchyEvent")) {
switch (e.getID()) {
case 1400:
this.processHierarchyEvent$java_awt_event_HierarchyEvent(e);
break;
case 1401:
case 1402:
this.processHierarchyBoundsEvent$java_awt_event_HierarchyEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'processComponentEvent$java_awt_event_ComponentEvent', function (e) {
var listener = this.componentListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 101:
listener.componentResized$java_awt_event_ComponentEvent(e);
break;
case 100:
listener.componentMoved$java_awt_event_ComponentEvent(e);
break;
case 102:
listener.componentShown$java_awt_event_ComponentEvent(e);
break;
case 103:
listener.componentHidden$java_awt_event_ComponentEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'processFocusEvent$java_awt_event_FocusEvent', function (e) {
var listener = this.focusListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 1004:
listener.focusGained$java_awt_event_FocusEvent(e);
break;
case 1005:
listener.focusLost$java_awt_event_FocusEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'processKeyEvent$java_awt_event_KeyEvent', function (e) {
var listener = this.keyListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 400:
listener.keyTyped$java_awt_event_KeyEvent(e);
break;
case 401:
listener.keyPressed$java_awt_event_KeyEvent(e);
break;
case 402:
listener.keyReleased$java_awt_event_KeyEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'processMouseEvent$java_awt_event_MouseEvent', function (e) {
var listener = this.mouseListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 501:
listener.mousePressed$java_awt_event_MouseEvent(e);
break;
case 502:
listener.mouseReleased$java_awt_event_MouseEvent(e);
break;
case 500:
listener.mouseClicked$java_awt_event_MouseEvent(e);
break;
case 505:
listener.mouseExited$java_awt_event_MouseEvent(e);
break;
case 504:
listener.mouseEntered$java_awt_event_MouseEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'processMouseMotionEvent$java_awt_event_MouseEvent', function (e) {
var listener = this.mouseMotionListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 503:
listener.mouseMoved$java_awt_event_MouseEvent(e);
break;
case 506:
listener.mouseDragged$java_awt_event_MouseEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'processMouseWheelEvent$java_awt_event_MouseWheelEvent', function (e) {
var listener = this.mouseWheelListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 507:
listener.mouseWheelMoved$java_awt_event_MouseWheelEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'postsOldMouseEvents', function () {
return false;
});

Clazz.newMeth(C$, 'processInputMethodEvent$java_awt_event_InputMethodEvent', function (e) {
var listener = this.inputMethodListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 1100:
listener.inputMethodTextChanged$java_awt_event_InputMethodEvent(e);
break;
case 1101:
listener.caretPositionChanged$java_awt_event_InputMethodEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'processHierarchyEvent$java_awt_event_HierarchyEvent', function (e) {
var listener = this.hierarchyListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 1400:
listener.hierarchyChanged$java_awt_event_HierarchyEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'processHierarchyBoundsEvent$java_awt_event_HierarchyEvent', function (e) {
var listener = this.hierarchyBoundsListener;
if (listener != null ) {
var id = e.getID();
switch (id) {
case 1401:
listener.ancestorMoved$java_awt_event_HierarchyEvent(e);
break;
case 1402:
listener.ancestorResized$java_awt_event_HierarchyEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'handleEvent$java_awt_Event', function (evt) {
switch (evt.id) {
case 504:
return this.mouseEnter$java_awt_Event$I$I(evt, evt.x, evt.y);
case 505:
return this.mouseExit$java_awt_Event$I$I(evt, evt.x, evt.y);
case 503:
return this.mouseMove$java_awt_Event$I$I(evt, evt.x, evt.y);
case 501:
return this.mouseDown$java_awt_Event$I$I(evt, evt.x, evt.y);
case 506:
return this.mouseDrag$java_awt_Event$I$I(evt, evt.x, evt.y);
case 502:
return this.mouseUp$java_awt_Event$I$I(evt, evt.x, evt.y);
case 401:
case 403:
return this.keyDown$java_awt_Event$I(evt, evt.key);
case 402:
case 404:
return this.keyUp$java_awt_Event$I(evt, evt.key);
case 1001:
return this.action$java_awt_Event$O(evt, evt.arg);
case 1004:
return this.gotFocus$java_awt_Event$O(evt, evt.arg);
case 1005:
return this.lostFocus$java_awt_Event$O(evt, evt.arg);
}
return false;
});

Clazz.newMeth(C$, 'mouseDown$java_awt_Event$I$I', function (evt, x, y) {
return false;
});

Clazz.newMeth(C$, 'mouseDrag$java_awt_Event$I$I', function (evt, x, y) {
return false;
});

Clazz.newMeth(C$, 'mouseUp$java_awt_Event$I$I', function (evt, x, y) {
return false;
});

Clazz.newMeth(C$, 'mouseMove$java_awt_Event$I$I', function (evt, x, y) {
return false;
});

Clazz.newMeth(C$, 'mouseEnter$java_awt_Event$I$I', function (evt, x, y) {
return false;
});

Clazz.newMeth(C$, 'mouseExit$java_awt_Event$I$I', function (evt, x, y) {
return false;
});

Clazz.newMeth(C$, 'keyDown$java_awt_Event$I', function (evt, key) {
return false;
});

Clazz.newMeth(C$, 'keyUp$java_awt_Event$I', function (evt, key) {
return false;
});

Clazz.newMeth(C$, 'action$java_awt_Event$O', function (evt, what) {
return false;
});

Clazz.newMeth(C$, 'addNotify', function () {
this.addNotifyComp();
});

Clazz.newMeth(C$, 'addNotifyComp', function () {
{
var peer = this.getOrCreatePeer();
if (this.parent != null ) {
var mask = 0;
if ((this.mouseListener != null ) || ((this.eventMask & 16) != 0) ) {
mask|=16;
}if ((this.mouseMotionListener != null ) || ((this.eventMask & 32) != 0) ) {
mask|=32;
}if ((this.mouseWheelListener != null ) || ((this.eventMask & 131072) != 0) ) {
mask|=131072;
}if (this.focusListener != null  || (this.eventMask & 4) != 0 ) {
mask|=4;
}if (this.keyListener != null  || (this.eventMask & 8) != 0 ) {
mask|=8;
}if (mask != 0) {
this.parent.proxyEnableEvents$J(mask);
}}this.invalidate();
this.peerFont=this.getFont();
if (this.getContainer() != null  && !this.isAddNotifyComplete ) {
this.getContainer().increaseComponentCount$java_awt_Component(this);
}if (this.parent != null  && this.parent.peer != null  ) {
var parentContPeer = this.parent.peer;
}this.isAddNotifyComplete=true;
if (this.visible && peer != null  ) {
var isDisposed = false;

isDisposed = peer.isDisposed;
if (isDisposed) peer.setVisible$Z(true);
}if (this.hierarchyListener != null  || (this.eventMask & 32768) != 0  || (I$[5]||$incl$(5)).enabledOnToolkit$J(32768) ) {
var e = Clazz.new_((I$[20]||$incl$(20)).c$$java_awt_Component$I$java_awt_Component$java_awt_Container$J,[this, 1400, this, this.parent, 2 | ((this.isRecursivelyVisible()) ? 4 : 0)]);
this.dispatchEvent$java_awt_AWTEvent(e);
}}});

Clazz.newMeth(C$, 'getNativeContainer', function () {
var p = this.parent;
while (p != null  && Clazz.instanceOf(p.peer, "java.awt.peer.LightweightPeer") ){
p=p.getParent();
}
return p;
});

Clazz.newMeth(C$, 'removeNotify', function () {
this.removeNotifyComp();
});

Clazz.newMeth(C$, 'removeNotifyComp', function () {
{
if (this.getContainer() != null  && this.isAddNotifyComplete ) {
this.getContainer().decreaseComponentCount$java_awt_Component(this);
}var p = this.getOrCreatePeer();
if (p != null ) {
if (this.visible) {
p.setVisible$Z(false);
}(I$[5]||$incl$(5)).getEventQueue().removeSourceEvents$O$Z(this, false);
p.dispose();
this.isAddNotifyComplete=false;
}if (this.hierarchyListener != null  || (this.eventMask & 32768) != 0  || (I$[5]||$incl$(5)).enabledOnToolkit$J(32768) ) {
var e = Clazz.new_((I$[20]||$incl$(20)).c$$java_awt_Component$I$java_awt_Component$java_awt_Container$J,[this, 1400, this, this.parent, 2 | ((this.isRecursivelyVisible()) ? 4 : 0)]);
this.dispatchEvent$java_awt_AWTEvent(e);
}}});

Clazz.newMeth(C$, 'gotFocus$java_awt_Event$O', function (evt, what) {
return false;
});

Clazz.newMeth(C$, 'lostFocus$java_awt_Event$O', function (evt, what) {
return false;
});

Clazz.newMeth(C$, 'isFocusTraversable', function () {
if (this.$isFocusTraversableOverridden == 0) {
this.$isFocusTraversableOverridden=1;
}return this.focusable;
});

Clazz.newMeth(C$, 'isFocusable', function () {
return this.isFocusTraversable();
});

Clazz.newMeth(C$, 'setFocusable$Z', function (focusable) {
var oldFocusable;
{
oldFocusable=this.focusable;
this.focusable=focusable;
}this.$isFocusTraversableOverridden=2;
this.firePropertyChange$S$O$O("focusable", (I$[28]||$incl$(28)).$valueOf(oldFocusable), (I$[28]||$incl$(28)).$valueOf(focusable));
});

Clazz.newMeth(C$, 'isFocusTraversableOverridden', function () {
return (this.$isFocusTraversableOverridden != 1);
});

Clazz.newMeth(C$, 'getFocusTraversalKeysEnabled', function () {
return this.focusTraversalKeysEnabled;
});

Clazz.newMeth(C$, 'requestFocus', function () {
(I$[4]||$incl$(4)).requestFocus$java_awt_Component(this);
});

Clazz.newMeth(C$, 'requestFocus$Z', function (temporary) {
return (I$[4]||$incl$(4)).requestFocus$java_awt_Component(this);
});

Clazz.newMeth(C$, 'requestFocusInWindow', function () {
return (I$[4]||$incl$(4)).requestFocus$java_awt_Component(this);
});

Clazz.newMeth(C$, 'requestFocusInWindow$Z', function (temporary) {
return (I$[4]||$incl$(4)).requestFocus$java_awt_Component(this);
});

Clazz.newMeth(C$, 'getFocusCycleRootAncestor', function () {
var rootAncestor = this.parent;
while (rootAncestor != null  && !rootAncestor.isFocusCycleRoot() ){
rootAncestor=rootAncestor.parent;
}
return rootAncestor;
});

Clazz.newMeth(C$, 'isFocusCycleRoot$java_awt_Container', function (container) {
return this.isFocusCycleRootComp$java_awt_Container(container);
});

Clazz.newMeth(C$, 'isFocusCycleRootComp$java_awt_Container', function (container) {
var rootAncestor = this.getFocusCycleRootAncestor();
return (rootAncestor === container );
});

Clazz.newMeth(C$, 'hasFocus', function () {
return (I$[4]||$incl$(4)).hasFocus$java_awt_Component(this);
});

Clazz.newMeth(C$, 'isFocusOwner', function () {
return this.hasFocus();
});

Clazz.newMeth(C$, 'setAutoFocusTransferOnDisposal$Z', function (value) {
this.autoFocusTransferOnDisposal=value;
});

Clazz.newMeth(C$, 'isAutoFocusTransferOnDisposal', function () {
return this.autoFocusTransferOnDisposal;
});

Clazz.newMeth(C$, 'paramString', function () {
return this.paramStringComp();
});

Clazz.newMeth(C$, 'paramStringComp', function () {
var thisName = this.getName();
var str = (thisName != null  ? thisName : "");
if (!this.isValid()) {
str += ",invalid";
}if (!this.visible) {
str += ",hidden";
}if (!this.enabled) {
str += ",disabled";
}str += ",parent:" + (this.parent == null  ? null : this.parent.getName()) + "," + this.x + "," + this.y + "," + this.width + "x" + this.height ;
return str;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + "[" + this.paramString() + "]" ;
});

Clazz.newMeth(C$, 'addPropertyChangeListener$java_beans_PropertyChangeListener', function (listener) {
this.addPropChangeListenerComp$java_beans_PropertyChangeListener(listener);
});

Clazz.newMeth(C$, 'addPropChangeListenerComp$java_beans_PropertyChangeListener', function (listener) {
{
if (listener == null ) {
return;
}if (this.changeSupport == null ) {
this.changeSupport=Clazz.new_((I$[29]||$incl$(29)).c$$O,[this]);
}this.changeSupport.addPropertyChangeListener$java_beans_PropertyChangeListener(listener);
}});

Clazz.newMeth(C$, 'removePropertyChangeListener$java_beans_PropertyChangeListener', function (listener) {
{
if (listener == null  || this.changeSupport == null  ) {
return;
}this.changeSupport.removePropertyChangeListener$java_beans_PropertyChangeListener(listener);
}});

Clazz.newMeth(C$, 'getPropertyChangeListeners', function () {
{
if (this.changeSupport == null ) {
return Clazz.array((I$[27]||$incl$(27)), [0]);
}return this.changeSupport.getPropertyChangeListeners();
}});

Clazz.newMeth(C$, 'addPropertyChangeListener$S$java_beans_PropertyChangeListener', function (propertyName, listener) {
this.addPropChangeListComp$S$java_beans_PropertyChangeListener(propertyName, listener);
});

Clazz.newMeth(C$, 'addPropChangeListComp$S$java_beans_PropertyChangeListener', function (propertyName, listener) {
{
if (listener == null ) {
return;
}if (this.changeSupport == null ) {
this.changeSupport=Clazz.new_((I$[29]||$incl$(29)).c$$O,[this]);
}this.changeSupport.addPropertyChangeListener$S$java_beans_PropertyChangeListener(propertyName, listener);
}});

Clazz.newMeth(C$, 'removePropertyChangeListener$S$java_beans_PropertyChangeListener', function (propertyName, listener) {
{
if (listener == null  || this.changeSupport == null  ) {
return;
}this.changeSupport.removePropertyChangeListener$S$java_beans_PropertyChangeListener(propertyName, listener);
}});

Clazz.newMeth(C$, 'getPropertyChangeListeners$S', function (propertyName) {
{
if (this.changeSupport == null ) {
return Clazz.array((I$[27]||$incl$(27)), [0]);
}return this.changeSupport.getPropertyChangeListeners$S(propertyName);
}});

Clazz.newMeth(C$, 'firePropertyChange$S$O$O', function (propertyName, oldValue, newValue) {
var changeSupport;
{
changeSupport=this.changeSupport;
}if (changeSupport == null  || (oldValue != null  && newValue != null   && oldValue.equals$O(newValue) ) ) {
return;
}changeSupport.firePropertyChange$S$O$O(propertyName, oldValue, newValue);
});

Clazz.newMeth(C$, 'firePropertyChange$S$Z$Z', function (propertyName, oldValue, newValue) {
var changeSupport = this.changeSupport;
if (changeSupport == null  || oldValue == newValue  ) {
return;
}changeSupport.firePropertyChange$S$O$O(propertyName, (I$[28]||$incl$(28)).$valueOf(oldValue), (I$[28]||$incl$(28)).$valueOf(newValue));
});

Clazz.newMeth(C$, 'firePropertyChange$S$I$I', function (propertyName, oldValue, newValue) {
var changeSupport = this.changeSupport;
if (changeSupport == null  || oldValue == newValue ) {
return;
}changeSupport.firePropertyChange$S$O$O(propertyName, Integer.$valueOf(oldValue), Integer.$valueOf(newValue));
});

Clazz.newMeth(C$, 'firePropertyChange$S$B$B', function (propertyName, oldValue, newValue) {
if (this.changeSupport == null  || oldValue == newValue ) {
return;
}this.firePropertyChange$S$O$O(propertyName, Byte.$valueOf(($b$[0] = oldValue, $b$[0])), Byte.$valueOf(($b$[0] = newValue, $b$[0])));
});

Clazz.newMeth(C$, 'firePropertyChange$S$C$C', function (propertyName, oldValue, newValue) {
if (this.changeSupport == null  || oldValue == newValue ) {
return;
}this.firePropertyChange$S$O$O(propertyName, Clazz.new_(java.lang.Character,[oldValue]), Clazz.new_(java.lang.Character,[newValue]));
});

Clazz.newMeth(C$, 'firePropertyChange$S$H$H', function (propertyName, oldValue, newValue) {
if (this.changeSupport == null  || oldValue == newValue ) {
return;
}this.firePropertyChange$S$O$O(propertyName, Short.$valueOf(oldValue), Short.$valueOf(newValue));
});

Clazz.newMeth(C$, 'firePropertyChange$S$J$J', function (propertyName, oldValue, newValue) {
if (this.changeSupport == null  || oldValue == newValue ) {
return;
}this.firePropertyChange$S$O$O(propertyName, Long.$valueOf(oldValue), Long.$valueOf(newValue));
});

Clazz.newMeth(C$, 'firePropertyChange$S$F$F', function (propertyName, oldValue, newValue) {
if (this.changeSupport == null  || oldValue == newValue  ) {
return;
}this.firePropertyChange$S$O$O(propertyName, Float.$valueOf(oldValue), Float.$valueOf(newValue));
});

Clazz.newMeth(C$, 'firePropertyChange$S$D$D', function (propertyName, oldValue, newValue) {
if (this.changeSupport == null  || oldValue == newValue  ) {
return;
}this.firePropertyChange$S$O$O(propertyName, Double.$valueOf(oldValue), Double.$valueOf(newValue));
});

Clazz.newMeth(C$, 'setComponentOrientation$java_awt_ComponentOrientation', function (o) {
var oldValue = this.componentOrientation;
this.componentOrientation=o;
this.firePropertyChange$S$O$O("componentOrientation", oldValue, o);
this.invalidateIfValid();
});

Clazz.newMeth(C$, 'getComponentOrientation', function () {
return this.componentOrientation;
});

Clazz.newMeth(C$, 'applyComponentOrientation$java_awt_ComponentOrientation', function (orientation) {
this.applyCompOrientComp$java_awt_ComponentOrientation(orientation);
});

Clazz.newMeth(C$, 'applyCompOrientComp$java_awt_ComponentOrientation', function (orientation) {
if (orientation == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.setComponentOrientation$java_awt_ComponentOrientation(orientation);
});

Clazz.newMeth(C$, 'canBeFocusOwner', function () {
if (this.isEnabled() && this.isDisplayable() && this.isVisible() && this.isFocusable()  ) {
return true;
}return false;
});

Clazz.newMeth(C$, 'canBeFocusOwnerRecursively', function () {
if (!this.canBeFocusOwner()) {
return false;
}if (this.parent != null ) {
return this.parent.canContainFocusOwner$java_awt_Component(this);
}return true;
});

Clazz.newMeth(C$, 'relocateComponent', function () {
});

Clazz.newMeth(C$, 'getContainingWindow', function () {
return (I$[30]||$incl$(30)).getContainingWindow$java_awt_Component(this);
});

Clazz.newMeth(C$, 'isInstanceOf$O$S', function (obj, className) {
if (obj == null ) return false;
if (className == null ) return false;
var cls = obj.getClass();
while (cls != null ){
if (cls.getName().equals$O(className)) {
return true;
}cls=cls.getSuperclass();
}
return false;
}, 1);

Clazz.newMeth(C$, 'areBoundsValid', function () {
var cont = this.getContainer();
return cont == null  || cont.isValid()  || cont.getLayout() == null  ;
});

Clazz.newMeth(C$, 'getLocationOnWindow', function () {
var curLocation = this.getLocation();
for (var parent = this.getContainer(); parent != null  && !(Clazz.instanceOf(parent, "java.awt.Window")) ; parent=parent.getContainer()) {
curLocation.x+=parent.getX();
curLocation.y+=parent.getY();
}
return curLocation;
});

Clazz.newMeth(C$, 'getSiblingIndexAbove', function () {
var parent = this.getContainer();
if (parent == null ) {
return -1;
}var nextAbove = parent.getComponentZOrder$java_awt_Component(this) - 1;
return nextAbove < 0 ? -1 : nextAbove;
});

Clazz.newMeth(C$, 'getSiblingIndexBelow', function () {
var parent = this.getContainer();
if (parent == null ) {
return -1;
}var nextBelow = parent.getComponentZOrder$java_awt_Component(this) + 1;
return nextBelow >= parent.getComponentCount() ? -1 : nextBelow;
});

Clazz.newMeth(C$, 'mixOnShowing', function () {
});

Clazz.newMeth(C$, 'mixOnHiding$Z', function (isLightweight) {
});

Clazz.newMeth(C$, 'mixOnReshaping', function () {
(I$[4]||$incl$(4)).taintUI$java_awt_Component(this);
});

Clazz.newMeth(C$, 'mixOnZOrderChanging$I$I', function (oldZorder, newZorder) {
});

Clazz.newMeth(C$, 'mixOnValidating', function () {
});

Clazz.newMeth(C$, 'doesClassImplement$Class$S', function (cls, interfaceName) {
if (cls == null ) return false;
for (var c, $c = 0, $$c = cls.getInterfaces(); $c<$$c.length&&((c=$$c[$c]),1);$c++) {
if (c.getName().equals$O(interfaceName)) {
return true;
}}
return C$.doesClassImplement$Class$S(cls.getSuperclass(), interfaceName);
}, 1);

Clazz.newMeth(C$, 'doesImplement$O$S', function (obj, interfaceName) {
if (obj == null ) return false;
if (interfaceName == null ) return false;
return C$.doesClassImplement$Class$S(obj.getClass(), interfaceName);
}, 1);
var $b$ = new Int8Array(1);
;
(function(){var C$=Clazz.newClass(P$.Component, "BaselineResizeBehavior", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "CONSTANT_ASCENT", 0, []);
Clazz.newEnumConst($vals, C$.c$, "CONSTANT_DESCENT", 1, []);
Clazz.newEnumConst($vals, C$.c$, "CENTER_OFFSET", 2, []);
Clazz.newEnumConst($vals, C$.c$, "OTHER", 3, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})()
;
(function(){var C$=Clazz.newClass(P$.Component, "AWTTreeLock", function(){
Clazz.newInstance(this, arguments[0],false,C$);
});

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:45:06
