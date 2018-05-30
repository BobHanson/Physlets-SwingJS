(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['javax.swing.RepaintManager','java.awt.Container','java.awt.Point','java.awt.Dimension','java.awt.Rectangle','javax.swing.JComponent','javax.swing.SwingUtilities',['javax.swing.JViewport','.ViewListener'],'javax.swing.ViewportLayout','javax.swing.event.ChangeListener','javax.swing.event.ChangeEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JViewport", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'javax.swing.JComponent');
C$.EnableWindowBlit = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.EnableWindowBlit = "EnableWindowBlit";
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.isViewSizeSet = false;
this.lastPaintPosition = null;
this.backingStore = false;
this.backingStoreImage = null;
this.scrollUnderway = false;
this.viewListener = null;
this.changeEvent = null;
this.scrollMode = 0;
this.repaintAll = false;
this.waitingForRepaint = false;
this.inBlitPaint = false;
this.hasHadValidView = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.isViewSizeSet = false;
this.lastPaintPosition = null;
this.backingStore = false;
this.backingStoreImage = null;
this.scrollUnderway = false;
this.viewListener = null;
this.changeEvent = null;
this.scrollMode = 1;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.setLayout$java_awt_LayoutManager(this.createLayoutManager());
this.setOpaque$Z(true);
this.uiClassID="ViewportUI";
this.updateUI();
this.setInheritsPopupMenu$Z(true);
}, 1);

Clazz.newMeth(C$, 'addImpl$java_awt_Component$O$I', function (child, constraints, index) {
this.setView$java_awt_Component(child);
return child;
});

Clazz.newMeth(C$, 'remove$java_awt_Component', function (child) {
child.removeComponentListener$java_awt_event_ComponentListener(this.viewListener);
C$.superclazz.prototype.remove$java_awt_Component.apply(this, [child]);
});

Clazz.newMeth(C$, 'scrollRectToVisible$java_awt_Rectangle', function (contentRect) {
var view = this.getView();
if (view == null ) {
return;
} else {
if (!view.isValid()) {
p$.validateView.apply(this, []);
}var dx = 0;
var dy = 0;
dx=p$.positionAdjustment$I$I$I.apply(this, [this.getWidth(), contentRect.width, contentRect.x]);
dy=p$.positionAdjustment$I$I$I.apply(this, [this.getHeight(), contentRect.height, contentRect.y]);
if (dx != 0 || dy != 0 ) {
var viewPosition = this.getViewPosition();
var viewSize = view.getSize();
var startX = viewPosition.x;
var startY = viewPosition.y;
var extent = this.getExtentSize();
viewPosition.x-=dx;
viewPosition.y-=dy;
if (view.isValid()) {
if (this.getParent().getComponentOrientation().isLeftToRight()) {
if (viewPosition.x + extent.width > viewSize.width) {
viewPosition.x=Math.max(0, viewSize.width - extent.width);
} else if (viewPosition.x < 0) {
viewPosition.x=0;
}} else {
if (extent.width > viewSize.width) {
viewPosition.x=viewSize.width - extent.width;
} else {
viewPosition.x=Math.max(0, Math.min(viewSize.width - extent.width, viewPosition.x));
}}if (viewPosition.y + extent.height > viewSize.height) {
viewPosition.y=Math.max(0, viewSize.height - extent.height);
} else if (viewPosition.y < 0) {
viewPosition.y=0;
}}if (viewPosition.x != startX || viewPosition.y != startY ) {
this.setViewPosition$java_awt_Point(viewPosition);
this.scrollUnderway=false;
}}}});

Clazz.newMeth(C$, 'validateView', function () {
var validateRoot = null;
for (var c = this; c != null ; c=c.getParent()) {
if ((Clazz.instanceOf(c, "javax.swing.CellRendererPane")) || !c.isLightweight() ) {
return;
}if ((Clazz.instanceOf(c, "javax.swing.JComponent")) && ((c).isValidateRoot()) ) {
validateRoot=c;
break;
}}
if (validateRoot == null ) {
return;
}var root = null;
for (var c = validateRoot; c != null ; c=c.getParent()) {
if (!c.isLightweight()) {
return;
}if ((Clazz.instanceOf(c, "java.awt.Window")) || (Clazz.instanceOf(c, "java.applet.Applet")) ) {
root=c;
break;
}}
if (root == null ) {
return;
}validateRoot.validate();
var rm = (I$[1]||$incl$(1)).currentManager$javax_swing_JComponent(this);
if (rm != null ) {
rm.removeInvalidComponent$javax_swing_JComponent(validateRoot);
}});

Clazz.newMeth(C$, 'positionAdjustment$I$I$I', function (parentWidth, childWidth, childAt) {
if (childAt >= 0 && childWidth + childAt <= parentWidth ) {
return 0;
}if (childAt <= 0 && childWidth + childAt >= parentWidth ) {
return 0;
}if (childAt > 0 && childWidth <= parentWidth ) {
return -childAt + parentWidth - childWidth;
}if (childAt >= 0 && childWidth >= parentWidth ) {
return -childAt;
}if (childAt <= 0 && childWidth <= parentWidth ) {
return -childAt;
}if (childAt < 0 && childWidth >= parentWidth ) {
return -childAt + parentWidth - childWidth;
}return 0;
});

Clazz.newMeth(C$, 'setBorder$javax_swing_border_Border', function (border) {
if (border != null ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["JViewport.setBorder() not supported"]);
}});

Clazz.newMeth(C$, 'getInsets', function () {
return (I$[2]||$incl$(2)).NULL_INSETS;
});

Clazz.newMeth(C$, 'getInsets$java_awt_Insets', function (insets) {
insets.left=insets.top=insets.right=insets.bottom=0;
return insets;
});

Clazz.newMeth(C$, 'getBackingStoreGraphics$java_awt_Graphics', function (g) {
var bsg = this.backingStoreImage.getGraphics();
bsg.setColor$java_awt_Color(g.getColor());
bsg.setFont$java_awt_Font(g.getFont());
bsg.setClip$java_awt_Shape(g.getClipBounds());
return bsg;
});

Clazz.newMeth(C$, 'paintViaBackingStore$java_awt_Graphics', function (g) {
var bsg = p$.getBackingStoreGraphics$java_awt_Graphics.apply(this, [g]);
try {
C$.superclazz.prototype.paint$java_awt_Graphics.apply(this, [bsg]);
(g).drawImagePriv$java_awt_Image$I$I$java_awt_image_ImageObserver(this.backingStoreImage, 0, 0, this);
} finally {
bsg.dispose();
}
});

Clazz.newMeth(C$, 'paintViaBackingStore$java_awt_Graphics$java_awt_Rectangle', function (g, oClip) {
var bsg = p$.getBackingStoreGraphics$java_awt_Graphics.apply(this, [g]);
try {
C$.superclazz.prototype.paint$java_awt_Graphics.apply(this, [bsg]);
g.setClip$java_awt_Shape(oClip);
(g).drawImagePriv$java_awt_Image$I$I$java_awt_image_ImageObserver(this.backingStoreImage, 0, 0, this);
} finally {
bsg.dispose();
}
});

Clazz.newMeth(C$, 'isOptimizedDrawingEnabled', function () {
return false;
});

Clazz.newMeth(C$, 'isPaintingOrigin', function () {
if (this.scrollMode == 2) {
return true;
}return false;
});

Clazz.newMeth(C$, 'getViewLocation', function () {
var view = this.getView();
if (view != null ) {
return view.getLocation();
} else {
return Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[0, 0]);
}});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
var width = this.getWidth();
var height = this.getHeight();
if ((width <= 0) || (height <= 0) ) {
return;
}if (this.inBlitPaint) {
C$.superclazz.prototype.paint$java_awt_Graphics.apply(this, [g]);
return;
}if (this.repaintAll) {
this.repaintAll=false;
} else if (this.waitingForRepaint) {
var clipB = g.getClipBounds();
if (clipB.width >= this.getWidth() && clipB.height >= this.getHeight() ) {
this.waitingForRepaint=false;
}}if (!this.backingStore || p$.isBlitting.apply(this, []) || this.getView() == null   ) {
C$.superclazz.prototype.paint$java_awt_Graphics.apply(this, [g]);
this.lastPaintPosition=p$.getViewLocation.apply(this, []);
return;
}var viewBounds = this.getView().getBounds();
if (!this.isOpaque()) {
g.clipRect$I$I$I$I(0, 0, viewBounds.width, viewBounds.height);
}if (this.backingStoreImage == null ) {
this.backingStoreImage=this.createImage$I$I(width, height);
var clip = g.getClipBounds();
if (clip.width != width || clip.height != height ) {
if (!this.isOpaque()) {
g.setClip$I$I$I$I(0, 0, Math.min(viewBounds.width, width), Math.min(viewBounds.height, height));
} else {
g.setClip$I$I$I$I(0, 0, width, height);
}p$.paintViaBackingStore$java_awt_Graphics$java_awt_Rectangle.apply(this, [g, clip]);
} else {
p$.paintViaBackingStore$java_awt_Graphics.apply(this, [g]);
}} else {
if (!this.scrollUnderway || this.lastPaintPosition.equals$O(p$.getViewLocation.apply(this, [])) ) {
p$.paintViaBackingStore$java_awt_Graphics.apply(this, [g]);
} else {
var blitFrom = Clazz.new_((I$[3]||$incl$(3)));
var blitTo = Clazz.new_((I$[3]||$incl$(3)));
var blitSize = Clazz.new_((I$[4]||$incl$(4)));
var blitPaint = Clazz.new_((I$[5]||$incl$(5)));
var newLocation = p$.getViewLocation.apply(this, []);
var dx = newLocation.x - this.lastPaintPosition.x;
var dy = newLocation.y - this.lastPaintPosition.y;
var canBlit = this.computeBlit$I$I$java_awt_Point$java_awt_Point$java_awt_Dimension$java_awt_Rectangle(dx, dy, blitFrom, blitTo, blitSize, blitPaint);
if (!canBlit) {
p$.paintViaBackingStore$java_awt_Graphics.apply(this, [g]);
} else {
var bdx = blitTo.x - blitFrom.x;
var bdy = blitTo.y - blitFrom.y;
var clip = g.getClipBounds();
g.setClip$I$I$I$I(0, 0, width, height);
var bsg = p$.getBackingStoreGraphics$java_awt_Graphics.apply(this, [g]);
try {
bsg.copyArea$I$I$I$I$I$I(blitFrom.x, blitFrom.y, blitSize.width, blitSize.height, bdx, bdy);
g.setClip$I$I$I$I(clip.x, clip.y, clip.width, clip.height);
var r = viewBounds.intersection$java_awt_Rectangle(blitPaint);
bsg.setClip$java_awt_Shape(r);
C$.superclazz.prototype.paint$java_awt_Graphics.apply(this, [bsg]);
(g).drawImagePriv$java_awt_Image$I$I$java_awt_image_ImageObserver(this.backingStoreImage, 0, 0, this);
} finally {
bsg.dispose();
}
}}}this.lastPaintPosition=p$.getViewLocation.apply(this, []);
this.scrollUnderway=false;
});

Clazz.newMeth(C$, 'reshape$I$I$I$I', function (x, y, w, h) {
var sizeChanged = (this.getWidth() != w) || (this.getHeight() != h) ;
if (sizeChanged) {
this.backingStoreImage=null;
}C$.superclazz.prototype.reshape$I$I$I$I.apply(this, [x, y, w, h]);
if (sizeChanged) {
this.fireStateChanged();
}});

Clazz.newMeth(C$, 'setScrollMode$I', function (mode) {
this.scrollMode=mode;
if (mode == 2) {
this.backingStore=true;
} else {
this.backingStore=false;
}});

Clazz.newMeth(C$, 'getScrollMode', function () {
return this.scrollMode;
});

Clazz.newMeth(C$, 'isBackingStoreEnabled', function () {
return this.scrollMode == 2;
});

Clazz.newMeth(C$, 'setBackingStoreEnabled$Z', function (enabled) {
if (enabled) {
this.setScrollMode$I(2);
} else {
this.setScrollMode$I(1);
}});

Clazz.newMeth(C$, 'isBlitting', function () {
var view = this.getView();
return (this.scrollMode == 1) && (Clazz.instanceOf(view, "javax.swing.JComponent")) && (view).isOpaque()  ;
});

Clazz.newMeth(C$, 'getView', function () {
return (this.getComponentCount() > 0) ? this.getComponent$I(0) : null;
});

Clazz.newMeth(C$, 'setView$java_awt_Component', function (view) {
var n = this.getComponentCount();
for (var i = n - 1; i >= 0; i--) {
this.remove$java_awt_Component(this.getComponent$I(i));
}
this.isViewSizeSet=false;
if (view != null ) {
this.addImplCont$java_awt_Component$O$I(view, null, -1);
this.viewListener=this.createViewListener();
view.addComponentListener$java_awt_event_ComponentListener(this.viewListener);
}if (this.hasHadValidView) {
this.fireStateChanged();
} else if (view != null ) {
this.hasHadValidView=true;
}this.revalidate();
this.repaint();
});

Clazz.newMeth(C$, 'getViewSize', function () {
var view = this.getView();
if (view == null ) {
return Clazz.new_((I$[4]||$incl$(4)).c$$I$I,[0, 0]);
} else if (this.isViewSizeSet) {
return view.getSize();
} else {
return view.getPreferredSize();
}});

Clazz.newMeth(C$, 'setViewSize$java_awt_Dimension', function (newSize) {
var view = this.getView();
if (view != null ) {
var oldSize = view.getSize();
if (!newSize.equals$O(oldSize)) {
this.scrollUnderway=false;
view.setSize$java_awt_Dimension(newSize);
this.isViewSizeSet=true;
this.fireStateChanged();
}}});

Clazz.newMeth(C$, 'getViewPosition', function () {
var view = this.getView();
if (view != null ) {
var p = view.getLocation();
p.x=-p.x;
p.y=-p.y;
return p;
} else {
return Clazz.new_((I$[3]||$incl$(3)).c$$I$I,[0, 0]);
}});

Clazz.newMeth(C$, 'setViewPosition$java_awt_Point', function (p) {
var view = this.getView();
if (view == null ) {
return;
}var oldX;
var oldY;
var x = p.x;
var y = p.y;
if (Clazz.instanceOf(view, "javax.swing.JComponent")) {
var c = view;
oldX=c.getX();
oldY=c.getY();
} else {
var r = view.getBounds();
oldX=r.x;
oldY=r.y;
}var newX = -x;
var newY = -y;
if ((oldX != newX) || (oldY != newY) ) {
if (!this.waitingForRepaint && p$.isBlitting.apply(this, []) && p$.canUseWindowBlitter.apply(this, [])  ) {
var rm = (I$[1]||$incl$(1)).currentManager$javax_swing_JComponent(this);
var jview = view;
var dirty = rm.getDirtyRegion$javax_swing_JComponent(jview);
if (dirty == null  || !dirty.contains$java_awt_Rectangle(jview.getVisibleRect()) ) {
rm.beginPaint();
try {
var g = (I$[6]||$incl$(6)).safelyGetGraphics$java_awt_Component$java_awt_Component(this, (I$[7]||$incl$(7)).getRoot$java_awt_Component(this));
p$.flushViewDirtyRegion$java_awt_Graphics$java_awt_Rectangle.apply(this, [g, dirty]);
view.setLocation$I$I(newX, newY);
g.setClip$I$I$I$I(0, 0, this.getWidth(), Math.min(this.getHeight(), jview.getHeight()));
this.repaintAll=(p$.windowBlitPaint$java_awt_Graphics.apply(this, [g]) && p$.needsRepaintAfterBlit.apply(this, []) );
g.dispose();
rm.markCompletelyClean$javax_swing_JComponent(this.getParent());
rm.markCompletelyClean$javax_swing_JComponent(this);
rm.markCompletelyClean$javax_swing_JComponent(jview);
} finally {
rm.endPaint();
}
} else {
view.setLocation$I$I(newX, newY);
this.repaintAll=false;
}} else {
this.scrollUnderway=true;
view.setLocation$I$I(newX, newY);
this.repaintAll=false;
}this.fireStateChanged();
}});

Clazz.newMeth(C$, 'getViewRect', function () {
return Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Point$java_awt_Dimension,[this.getViewPosition(), this.getExtentSize()]);
});

Clazz.newMeth(C$, 'computeBlit$I$I$java_awt_Point$java_awt_Point$java_awt_Dimension$java_awt_Rectangle', function (dx, dy, blitFrom, blitTo, blitSize, blitPaint) {

return  false;
});

Clazz.newMeth(C$, 'getExtentSize', function () {
return this.getSize();
});

Clazz.newMeth(C$, 'toViewCoordinates$java_awt_Dimension', function (size) {
return Clazz.new_((I$[4]||$incl$(4)).c$$java_awt_Dimension,[size]);
});

Clazz.newMeth(C$, 'toViewCoordinates$java_awt_Point', function (p) {
return Clazz.new_((I$[3]||$incl$(3)).c$$java_awt_Point,[p]);
});

Clazz.newMeth(C$, 'setExtentSize$java_awt_Dimension', function (newExtent) {
var oldExtent = this.getExtentSize();
if (!newExtent.equals$O(oldExtent)) {
this.setSize$java_awt_Dimension(newExtent);
this.fireStateChanged();
}});

Clazz.newMeth(C$, 'createViewListener', function () {
return Clazz.new_((I$[8]||$incl$(8)), [this, null]);
});

Clazz.newMeth(C$, 'createLayoutManager', function () {
return (I$[9]||$incl$(9)).SHARED_INSTANCE;
});

Clazz.newMeth(C$, 'addChangeListener$javax_swing_event_ChangeListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[10]||$incl$(10)),['stateChanged$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'removeChangeListener$javax_swing_event_ChangeListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[10]||$incl$(10)),['stateChanged$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'getChangeListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[10]||$incl$(10)),['stateChanged$javax_swing_event_ChangeEvent']));
});

Clazz.newMeth(C$, 'fireStateChanged', function () {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i-=2) {
if (listeners[i] === Clazz.getClass((I$[10]||$incl$(10)),['stateChanged$javax_swing_event_ChangeEvent']) ) {
if (this.changeEvent == null ) {
this.changeEvent=Clazz.new_((I$[11]||$incl$(11)).c$$O,[this]);
}(listeners[i + 1]).stateChanged$javax_swing_event_ChangeEvent(this.changeEvent);
}}
});

Clazz.newMeth(C$, 'repaint$J$I$I$I$I', function (tm, x, y, w, h) {
var parent = this.getParent();
if (parent != null ) parent.repaint$J$I$I$I$I(tm, x + this.getX(), y + this.getY(), w, h);
 else C$.superclazz.prototype.repaint$J$I$I$I$I.apply(this, [tm, x, y, w, h]);
});

Clazz.newMeth(C$, 'paramString', function () {
var isViewSizeSetString = (this.isViewSizeSet ? "true" : "false");
var lastPaintPositionString = (this.lastPaintPosition != null  ? this.lastPaintPosition.toString() : "");
var scrollUnderwayString = (this.scrollUnderway ? "true" : "false");
return C$.superclazz.prototype.paramString.apply(this, []) + ",isViewSizeSet=" + isViewSizeSetString + ",lastPaintPosition=" + lastPaintPositionString + ",scrollUnderway=" + scrollUnderwayString ;
});

Clazz.newMeth(C$, 'firePropertyChange$S$O$O', function (propertyName, oldValue, newValue) {
C$.superclazz.prototype.firePropertyChange$S$O$O.apply(this, [propertyName, oldValue, newValue]);
if (propertyName.equals$O(C$.EnableWindowBlit)) {
if (newValue != null ) {
this.setScrollMode$I(1);
} else {
this.setScrollMode$I(0);
}}});

Clazz.newMeth(C$, 'needsRepaintAfterBlit', function () {
var heavyParent = this.getParent();
while (heavyParent != null  && heavyParent.isLightweight() ){
heavyParent=heavyParent.getParent();
}
if (heavyParent != null ) {
}return true;
});

Clazz.newMeth(C$, 'flushViewDirtyRegion$java_awt_Graphics$java_awt_Rectangle', function (g, dirty) {
var view = this.getView();
if (dirty != null  && dirty.width > 0  && dirty.height > 0 ) {
dirty.x+=view.getX();
dirty.y+=view.getY();
var clip = g.getClipBounds();
if (clip == null ) {
g.setClip$I$I$I$I(0, 0, this.getWidth(), this.getHeight());
}g.clipRect$I$I$I$I(dirty.x, dirty.y, dirty.width, dirty.height);
clip=g.getClipBounds();
if (clip.width > 0 && clip.height > 0 ) {
p$.paintView$java_awt_Graphics.apply(this, [g]);
}}});

Clazz.newMeth(C$, 'windowBlitPaint$java_awt_Graphics', function (g) {
var width = this.getWidth();
var height = this.getHeight();
if ((width == 0) || (height == 0) ) {
return false;
}var retValue;
var view = this.getView();
if (this.lastPaintPosition == null  || this.lastPaintPosition.equals$O(p$.getViewLocation.apply(this, [])) ) {
p$.paintView$java_awt_Graphics.apply(this, [g]);
retValue=false;
} else {
var blitFrom = Clazz.new_((I$[3]||$incl$(3)));
var blitTo = Clazz.new_((I$[3]||$incl$(3)));
var blitSize = Clazz.new_((I$[4]||$incl$(4)));
var blitPaint = Clazz.new_((I$[5]||$incl$(5)));
var newLocation = p$.getViewLocation.apply(this, []);
var dx = newLocation.x - this.lastPaintPosition.x;
var dy = newLocation.y - this.lastPaintPosition.y;
var canBlit = this.computeBlit$I$I$java_awt_Point$java_awt_Point$java_awt_Dimension$java_awt_Rectangle(dx, dy, blitFrom, blitTo, blitSize, blitPaint);
if (!canBlit) {
p$.paintView$java_awt_Graphics.apply(this, [g]);
retValue=false;
} else {
var r = view.getBounds().intersection$java_awt_Rectangle(blitPaint);
r.x-=view.getX();
r.y-=view.getY();
p$.blitDoubleBuffered$javax_swing_JComponent$java_awt_Graphics$I$I$I$I$I$I$I$I$I$I.apply(this, [view, g, r.x, r.y, r.width, r.height, blitFrom.x, blitFrom.y, blitTo.x, blitTo.y, blitSize.width, blitSize.height]);
retValue=true;
}}this.lastPaintPosition=p$.getViewLocation.apply(this, []);
return retValue;
});

Clazz.newMeth(C$, 'blitDoubleBuffered$javax_swing_JComponent$java_awt_Graphics$I$I$I$I$I$I$I$I$I$I', function (view, g, clipX, clipY, clipW, clipH, blitFromX, blitFromY, blitToX, blitToY, blitW, blitH) {
var x = view.getX();
var y = view.getY();
g.translate$I$I(x, y);
g.setClip$I$I$I$I(clipX, clipY, clipW, clipH);
view.paintForceDoubleBuffered$java_awt_Graphics(g);
g.translate$I$I(-x, -y);
});

Clazz.newMeth(C$, 'paintView$java_awt_Graphics', function (g) {
var clip = g.getClipBounds();
var view = this.getView();
if (view.getWidth() >= this.getWidth()) {
var x = view.getX();
var y = view.getY();
g.translate$I$I(x, y);
g.setClip$I$I$I$I(clip.x - x, clip.y - y, clip.width, clip.height);
view.paintForceDoubleBuffered$java_awt_Graphics(g);
g.translate$I$I(-x, -y);
g.setClip$I$I$I$I(clip.x, clip.y, clip.width, clip.height);
} else {
try {
this.inBlitPaint=true;
this.paintForceDoubleBuffered$java_awt_Graphics(g);
} finally {
this.inBlitPaint=false;
}
}});

Clazz.newMeth(C$, 'canUseWindowBlitter', function () {
if (!this.isShowing() || (!(Clazz.instanceOf(this.getParent(), "javax.swing.JComponent")) && !(Clazz.instanceOf(this.getView(), "javax.swing.JComponent")) ) ) {
return false;
}if (this.isPainting()) {
return false;
}var dirtyRegion = (I$[1]||$incl$(1)).currentManager$javax_swing_JComponent(this).getDirtyRegion$javax_swing_JComponent(this.getParent());
if (dirtyRegion != null  && dirtyRegion.width > 0  && dirtyRegion.height > 0 ) {
return false;
}var clip = Clazz.new_((I$[5]||$incl$(5)).c$$I$I$I$I,[0, 0, this.getWidth(), this.getHeight()]);
var oldClip = Clazz.new_((I$[5]||$incl$(5)));
var tmp2 = null;
var parent;
var lastParent = null;
var x;
var y;
var w;
var h;
for (parent=this; parent != null  && P$.JComponent.isLightweightComponent$java_awt_Component(parent) ; parent=parent.getParent()) {
x=parent.getX();
y=parent.getY();
w=parent.getWidth();
h=parent.getHeight();
oldClip.setBounds$java_awt_Rectangle(clip);
(I$[7]||$incl$(7)).computeIntersection$I$I$I$I$java_awt_Rectangle(0, 0, w, h, clip);
if (!clip.equals$O(oldClip)) return false;
if (lastParent != null  && Clazz.instanceOf(parent, "javax.swing.JComponent")  && !(parent).isOptimizedDrawingEnabled() ) {
var comps = parent.getComponents();
var index = 0;
for (var i = comps.length - 1; i >= 0; i--) {
if (comps[i] === lastParent ) {
index=i - 1;
break;
}}
while (index >= 0){
tmp2=comps[index].getBounds$java_awt_Rectangle(tmp2);
if (tmp2.intersects$java_awt_Rectangle(clip)) return false;
index--;
}
}clip.x+=x;
clip.y+=y;
lastParent=parent;
}
if (parent == null ) {
return false;
}return true;
});
;
(function(){var C$=Clazz.newClass(P$.JViewport, "ViewListener", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, 'java.awt.event.ComponentAdapter');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'componentResized$java_awt_event_ComponentEvent', function (e) {
this.this$0.fireStateChanged();
this.this$0.revalidate();
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-05-24 08:46:31
