(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.util.HashMap','java.awt.Rectangle','sun.awt.AppContext','javax.swing.SwingUtilities',['javax.swing.RepaintManager','.ProcessingRunnable'],'java.util.IdentityHashMap','javajs.util.Lst','java.awt.Toolkit','java.util.HashSet','sun.awt.AWTAccessor','java.lang.StringBuffer','Thread','sun.awt.SunToolkit','java.awt.event.InvocationEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "RepaintManager", function(){
Clazz.newInstance(this, arguments,0,C$);
});
C$.HANDLE_TOP_LEVEL_PAINT = false;
C$.repaintManagerKey = null;
C$.volatileImageBufferEnabled = false;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.HANDLE_TOP_LEVEL_PAINT = false;
C$.repaintManagerKey = Clazz.getClass(C$);
C$.volatileImageBufferEnabled = true;
{
C$.volatileImageBufferEnabled = false;
var headless = false;
if (C$.volatileImageBufferEnabled && headless ) {
C$.volatileImageBufferEnabled = false;
}C$.HANDLE_TOP_LEVEL_PAINT = true;
}
;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.volatileMap = null;
this.hwDirtyComponents = null;
this.dirtyComponents = null;
this.tmpDirtyComponents = null;
this.invalidComponents = null;
this.runnableList = null;
this.paintDepth = 0;
this.painting = false;
this.repaintRoot = null;
this.paintThread = null;
this.processingRunnable = null;
this.myComponent = null;
this.tmp = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.volatileMap = Clazz.new_((I$[1]||$incl$(1)).c$$I,[1]);
this.paintDepth = 0;
this.tmp = Clazz.new_((I$[2]||$incl$(2)));
}, 1);

Clazz.newMeth(C$, 'currentManager$java_awt_Component', function (c) {
return C$.currentManager$java_awt_Component$sun_awt_AppContext(c, (I$[3]||$incl$(3)).getAppContext());
}, 1);

Clazz.newMeth(C$, 'currentManager$java_awt_Component$sun_awt_AppContext', function (c, appContext) {
var rm = appContext.get$O(C$.repaintManagerKey);
if (rm == null ) {
rm = Clazz.new_(C$);
rm.set$java_awt_Component(c);
appContext.put$O$O(C$.repaintManagerKey, rm);
}return rm;
}, 1);

Clazz.newMeth(C$, 'currentManager$javax_swing_JComponent', function (c) {
return C$.currentManager$java_awt_Component(c);
}, 1);

Clazz.newMeth(C$, 'setCurrentManager$javax_swing_RepaintManager', function (aRepaintManager) {
if (aRepaintManager != null ) {
(I$[4]||$incl$(4)).appContextPut$O$O(C$.repaintManagerKey, aRepaintManager);
} else {
(I$[4]||$incl$(4)).appContextRemove$O(C$.repaintManagerKey);
}}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
p$.setMaps.apply(this, []);
this.processingRunnable = Clazz.new_((I$[5]||$incl$(5)), [this, null]);
}, 1);

Clazz.newMeth(C$, 'set$java_awt_Component', function (c) {
this.myComponent = c;
p$.setMaps.apply(this, []);
});

Clazz.newMeth(C$, 'setMaps', function () {
this.dirtyComponents = Clazz.new_((I$[6]||$incl$(6)));
this.tmpDirtyComponents = Clazz.new_((I$[6]||$incl$(6)));
this.hwDirtyComponents = Clazz.new_((I$[6]||$incl$(6)));
});

Clazz.newMeth(C$, 'addInvalidComponent$javax_swing_JComponent', function (invalidComponent) {
var validateRoot = null;
for (var c = invalidComponent; c != null ; c = c.getParent()) {
if ((Clazz.instanceOf(c, "javax.swing.CellRendererPane"))) {
return;
}if ((Clazz.instanceOf(c, "javax.swing.JComponent")) && ((c).isValidateRoot()) ) {
validateRoot = c;
break;
}}
if (validateRoot == null ) {
return;
}var root = null;
for (var c = validateRoot; c != null ; c = c.getParent()) {
if (!c.isVisible()) {
return;
}if ((Clazz.instanceOf(c, "java.awt.Window")) || (Clazz.instanceOf(c, "java.applet.Applet")) ) {
root = c;
break;
}}
if (root == null ) {
return;
}if (this.invalidComponents == null ) {
this.invalidComponents = Clazz.new_((I$[7]||$incl$(7)));
} else {
var n = this.invalidComponents.size();
for (var i = 0; i < n; i++) {
if (validateRoot === this.invalidComponents.get$I(i) ) {
return;
}}
}this.invalidComponents.add$TE(validateRoot);
p$.scheduleProcessingRunnable$java_awt_Component.apply(this, [root]);
});

Clazz.newMeth(C$, 'removeInvalidComponent$javax_swing_JComponent', function (component) {
if (this.invalidComponents != null ) {
var index = this.invalidComponents.indexOf$O(component);
if (index != -1) {
this.invalidComponents.removeItemAt$I(index);
}}});

Clazz.newMeth(C$, 'addDirtyRegion0$java_awt_Container$I$I$I$I', function (c, x, y, w, h) {
if (w <= 0 || h <= 0  || c == null   || c.getWidth() <= 0  || c.getHeight() <= 0 ) return;
if (p$.extendDirtyRegion$java_awt_Component$I$I$I$I.apply(this, [c, x, y, w, h])) {
return;
}var root = null;
for (var p = c; p != null ; p = p.getParent()) {
if (!p.isVisible() || p.getPeer() == null  ) {
return;
}if ((Clazz.instanceOf(p, "java.awt.Window")) || (Clazz.instanceOf(p, "java.applet.Applet")) ) {
if (Clazz.instanceOf(p, "java.awt.Frame") && ((p).getExtendedState() & 1) == 1 ) {
return;
}root = p;
break;
}}
if (root == null ) return;
{
if (p$.extendDirtyRegion$java_awt_Component$I$I$I$I.apply(this, [c, x, y, w, h])) {
return;
}this.dirtyComponents.put$TK$TV(c, Clazz.new_((I$[2]||$incl$(2)).c$$I$I$I$I,[x, y, w, h]));
}p$.scheduleProcessingRunnable$java_awt_Component.apply(this, [c]);
});

Clazz.newMeth(C$, 'addDirtyRegion$javax_swing_JComponent$I$I$I$I', function (c, x, y, w, h) {
p$.addDirtyRegion0$java_awt_Container$I$I$I$I.apply(this, [c, x, y, w, h]);
});

Clazz.newMeth(C$, 'addDirtyRegion$java_awt_Window$I$I$I$I', function (window, x, y, w, h) {
p$.addDirtyRegion0$java_awt_Container$I$I$I$I.apply(this, [window, x, y, w, h]);
});

Clazz.newMeth(C$, 'addDirtyRegion$java_applet_Applet$I$I$I$I', function (applet, x, y, w, h) {
p$.addDirtyRegion0$java_awt_Container$I$I$I$I.apply(this, [applet, x, y, w, h]);
});

Clazz.newMeth(C$, 'scheduleHeavyWeightPaints', function () {
var hws;
{
if (this.hwDirtyComponents.size() == 0) {
return;
}hws = this.hwDirtyComponents;
this.hwDirtyComponents = Clazz.new_((I$[6]||$incl$(6)));
}for (var hw, $hw = hws.keySet().iterator(); $hw.hasNext()&&((hw=$hw.next()),1);) {
var dirty = hws.get$O(hw);
if (Clazz.instanceOf(hw, "java.awt.Window")) {
this.addDirtyRegion$java_awt_Window$I$I$I$I(hw, dirty.x, dirty.y, dirty.width, dirty.height);
} else if (Clazz.instanceOf(hw, "java.applet.Applet")) {
this.addDirtyRegion$java_applet_Applet$I$I$I$I(hw, dirty.x, dirty.y, dirty.width, dirty.height);
} else {
p$.addDirtyRegion0$java_awt_Container$I$I$I$I.apply(this, [hw, dirty.x, dirty.y, dirty.width, dirty.height]);
}}
});

Clazz.newMeth(C$, 'nativeAddDirtyRegion$sun_awt_AppContext$java_awt_Container$I$I$I$I', function (appContext, c, x, y, w, h) {
if (w > 0 && h > 0 ) {
{
var dirty = this.hwDirtyComponents.get$O(c);
if (dirty == null ) {
this.hwDirtyComponents.put$TK$TV(c, Clazz.new_((I$[2]||$incl$(2)).c$$I$I$I$I,[x, y, w, h]));
} else {
this.hwDirtyComponents.put$TK$TV(c, (I$[4]||$incl$(4)).computeUnion$I$I$I$I$java_awt_Rectangle(x, y, w, h, dirty));
}}p$.scheduleProcessingRunnable$sun_awt_AppContext.apply(this, [appContext]);
}});

Clazz.newMeth(C$, 'extendDirtyRegion$java_awt_Component$I$I$I$I', function (c, x, y, w, h) {
var r = this.dirtyComponents.get$O(c);
if (r != null ) {
(I$[4]||$incl$(4)).computeUnion$I$I$I$I$java_awt_Rectangle(x, y, w, h, r);
return true;
}return false;
});

Clazz.newMeth(C$, 'getDirtyRegion$javax_swing_JComponent', function (aComponent) {
var r = null;
{
r = this.dirtyComponents.get$O(aComponent);
}if (r == null ) return Clazz.new_((I$[2]||$incl$(2)).c$$I$I$I$I,[0, 0, 0, 0]);
 else return Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_Rectangle,[r]);
});

Clazz.newMeth(C$, 'markCompletelyDirty$javax_swing_JComponent', function (aComponent) {
this.addDirtyRegion$javax_swing_JComponent$I$I$I$I(aComponent, 0, 0, 2147483647, 2147483647);
});

Clazz.newMeth(C$, 'markCompletelyClean$javax_swing_JComponent', function (aComponent) {
{
this.dirtyComponents.remove$O(aComponent);
}});

Clazz.newMeth(C$, 'isCompletelyDirty$javax_swing_JComponent', function (aComponent) {
var r;
r = this.getDirtyRegion$javax_swing_JComponent(aComponent);
if (r.width == 2147483647 && r.height == 2147483647 ) return true;
 else return false;
});

Clazz.newMeth(C$, 'validateInvalidComponents', function () {
var ic;
{
if (this.invalidComponents == null ) {
return;
}ic = this.invalidComponents;
this.invalidComponents = null;
}var n = ic.size();
for (var i = 0; i < n; i++) {
var c = ic.get$I(i);
c.validate();
}
});

Clazz.newMeth(C$, 'prePaintDirtyRegions', function () {
var dirtyComponents;
var runnableList;
{
dirtyComponents = this.dirtyComponents;
runnableList = this.runnableList;
this.runnableList = null;
}if (runnableList != null ) {
for (var runnable, $runnable = runnableList.iterator(); $runnable.hasNext()&&((runnable=$runnable.next()),1);) {
runnable.run();
}
}this.paintDirtyRegions();
if (dirtyComponents.size() > 0) {
p$.paintDirtyRegions1$java_util_Map.apply(this, [dirtyComponents]);
}});

Clazz.newMeth(C$, 'updateWindows$java_util_Map', function (dirtyComponents) {
var toolkit = (I$[8]||$incl$(8)).getDefaultToolkit();
if (!(Clazz.instanceOf(toolkit, "sun.awt.SunToolkit") && (toolkit).needUpdateWindow() )) {
return dirtyComponents;
}var windows = Clazz.new_((I$[9]||$incl$(9)));
var dirtyComps = dirtyComponents.keySet();
for (var it = dirtyComps.iterator(); it.hasNext(); ) {
var dirty = it.next();
var window = Clazz.instanceOf(dirty, "java.awt.Window") ? dirty : (I$[4]||$incl$(4)).getWindowAncestor$java_awt_Component(dirty);
if (window != null  && !(I$[10]||$incl$(10)).getWindowAccessor().isOpaque$java_awt_Window(window) ) {
it.remove();
windows.add$TE(window);
}}
for (var window, $window = windows.iterator(); $window.hasNext()&&((window=$window.next()),1);) {
(I$[10]||$incl$(10)).getWindowAccessor().updateWindow$java_awt_Window$java_awt_image_BufferedImage(window, null);
}
return dirtyComponents;
});

Clazz.newMeth(C$, 'paintDirtyRegions', function () {
{
var tmp = this.tmpDirtyComponents;
this.tmpDirtyComponents = this.dirtyComponents;
this.dirtyComponents = tmp;
this.dirtyComponents.clear();
}p$.paintDirtyRegions1$java_util_Map.apply(this, [this.tmpDirtyComponents]);
});

Clazz.newMeth(C$, 'paintDirtyRegions1$java_util_Map', function (tmpDirtyComponents) {
if (tmpDirtyComponents.isEmpty()) {
return;
}p$.updateWindows$java_util_Map.apply(this, [tmpDirtyComponents]);
var roots = Clazz.new_((I$[7]||$incl$(7)));
for (var dirty, $dirty = tmpDirtyComponents.keySet().iterator(); $dirty.hasNext()&&((dirty=$dirty.next()),1);) {
this.collectDirtyComponents$java_util_Map$java_awt_Component$java_util_List(tmpDirtyComponents, dirty, roots);
}
var count = roots.size();
this.painting = true;
try {
for (var j = 0; j < count; j++) {
var i = j;
var dirtyComponent = roots.get$I(j);
var rect = tmpDirtyComponents.get$O(dirtyComponent);
var localBoundsH = dirtyComponent.getHeight();
var localBoundsW = dirtyComponent.getWidth();
(I$[4]||$incl$(4)).computeIntersection$I$I$I$I$java_awt_Rectangle(0, 0, localBoundsW, localBoundsH, rect);
if (Clazz.instanceOf(dirtyComponent, "javax.swing.JComponent")) {
(dirtyComponent).paintImmediately$I$I$I$I(rect.x, rect.y, rect.width, rect.height);
} else if (dirtyComponent.isShowing()) {
var g = dirtyComponent.getGraphics();
if (g != null ) {
try {
(dirtyComponent).paint$java_awt_Graphics(g);
} finally {
g.dispose();
}
}}if (this.repaintRoot != null ) {
p$.adjustRoots$javax_swing_JComponent$javajs_util_Lst$I.apply(this, [this.repaintRoot, roots, i + 1]);
count = roots.size();
this.repaintRoot.paintImmediately$I$I$I$I(0, 0, this.repaintRoot.getWidth(), this.repaintRoot.getHeight());
this.repaintRoot = null;
}}
} finally {
this.painting = false;
}
tmpDirtyComponents.clear();
});

Clazz.newMeth(C$, 'adjustRoots$javax_swing_JComponent$javajs_util_Lst$I', function (root, roots, index) {
for (var i = roots.size() - 1; i >= index; i--) {
var c = roots.get$I(i);
for (; ; ) {
if (c === root  || c == null   || !(Clazz.instanceOf(c, "javax.swing.JComponent")) ) {
break;
}c = c.getParent();
}
if (c === root ) {
roots.removeItemAt$I(i);
}}
});

Clazz.newMeth(C$, 'collectDirtyComponents$java_util_Map$java_awt_Component$java_util_List', function (dirtyComponents, dirtyComponent, roots) {
var dx;
var dy;
var rootDx;
var rootDy;
var component;
var rootDirtyComponent;
var parent;
component = rootDirtyComponent = dirtyComponent;
var x = dirtyComponent.getX();
var y = dirtyComponent.getY();
var w = dirtyComponent.getWidth();
var h = dirtyComponent.getHeight();
dx = rootDx = 0;
dy = rootDy = 0;
this.tmp.setBounds$java_awt_Rectangle(dirtyComponents.get$O(dirtyComponent));
(I$[4]||$incl$(4)).computeIntersection$I$I$I$I$java_awt_Rectangle(0, 0, w, h, this.tmp);
if (this.tmp.isEmpty()) {
return;
}for (; ; ) {
if (!(Clazz.instanceOf(component, "javax.swing.JComponent"))) break;
parent = component.getParent();
if (parent == null ) break;
component = parent;
dx = dx+(x);
dy = dy+(y);
this.tmp.x = this.tmp.x+(x);
this.tmp.y = this.tmp.y+(y);
x = component.getX();
y = component.getY();
w = component.getWidth();
h = component.getHeight();
this.tmp = (I$[4]||$incl$(4)).computeIntersection$I$I$I$I$java_awt_Rectangle(0, 0, w, h, this.tmp);
if (this.tmp.isEmpty()) {
return;
}if (dirtyComponents.get$O(component) != null ) {
rootDirtyComponent = component;
rootDx = dx;
rootDy = dy;
}}
if (dirtyComponent !== rootDirtyComponent ) {
var r;
this.tmp.x = this.tmp.x+(rootDx - dx);
this.tmp.y = this.tmp.y+(rootDy - dy);
r = dirtyComponents.get$O(rootDirtyComponent);
(I$[4]||$incl$(4)).computeUnion$I$I$I$I$java_awt_Rectangle(this.tmp.x, this.tmp.y, this.tmp.width, this.tmp.height, r);
}if (!roots.contains$O(rootDirtyComponent)) roots.add$TE(rootDirtyComponent);
});

Clazz.newMeth(C$, 'toString', function () {
var sb = Clazz.new_((I$[11]||$incl$(11)));
if (this.dirtyComponents != null ) sb.append$S("" + this.dirtyComponents);
return sb.toString();
});

Clazz.newMeth(C$, 'getOffscreenBuffer$java_awt_Component$I$I', function (c, proposedWidth, proposedHeight) {
return null;
});

Clazz.newMeth(C$, 'getVolatileOffscreenBuffer$java_awt_Component$I$I', function (c, proposedWidth, proposedHeight) {
var config = c.getGraphicsConfiguration();
var maxSize = this.getDoubleBufferMaximumSize();
var width = proposedWidth < 1 ? 1 : (proposedWidth > maxSize.width ? maxSize.width : proposedWidth);
var height = proposedHeight < 1 ? 1 : (proposedHeight > maxSize.height ? maxSize.height : proposedHeight);
var image = this.volatileMap.get$O(config);
if (image == null  || image.getWidth() < width  || image.getHeight() < height ) {
if (image != null ) {
image.flush();
}image = config.createCompatibleVolatileImage$I$I(width, height);
this.volatileMap.put$TK$TV(config, image);
}return image;
});

Clazz.newMeth(C$, 'setDoubleBufferMaximumSize$java_awt_Dimension', function (d) {
});

Clazz.newMeth(C$, 'getDoubleBufferMaximumSize', function () {
return null;
});

Clazz.newMeth(C$, 'setDoubleBufferingEnabled$Z', function (aFlag) {
});

Clazz.newMeth(C$, 'resetDoubleBuffer', function () {
});

Clazz.newMeth(C$, 'resetVolatileDoubleBuffer$java_awt_GraphicsConfiguration', function (gc) {
var image = this.volatileMap.remove$O(gc);
if (image != null ) {
image.flush();
}});

Clazz.newMeth(C$, 'useVolatileDoubleBuffer', function () {
return C$.volatileImageBufferEnabled;
});

Clazz.newMeth(C$, 'isPaintingThread', function () {
return ((I$[12]||$incl$(12)).currentThread() === this.paintThread );
});

Clazz.newMeth(C$, 'paint$javax_swing_JComponent$javax_swing_JComponent$java_awt_Graphics$I$I$I$I', function (paintingComponent, bufferComponent, g, x, y, w, h) {
paintingComponent.paintToOffscreen$java_awt_Graphics$I$I$I$I$I$I(g, x, y, w, h, x + w, y + h);
});

Clazz.newMeth(C$, 'beginPaint', function () {
var multiThreadedPaint = false;
var paintDepth = 0;
var currentThread = (I$[12]||$incl$(12)).currentThread();
{
paintDepth = this.paintDepth;
if (this.paintThread == null  || currentThread === this.paintThread  ) {
this.paintThread = currentThread;
this.paintDepth++;
} else {
multiThreadedPaint = true;
}}});

Clazz.newMeth(C$, 'endPaint', function () {
if (p$.isPaintingThread.apply(this, [])) {
}});

Clazz.newMeth(C$, 'doubleBufferingChanged$javax_swing_JRootPane', function (rootPane) {
});

Clazz.newMeth(C$, 'scheduleProcessingRunnable$java_awt_Component', function (c) {
p$.scheduleProcessingRunnable$sun_awt_AppContext.apply(this, [c.getAppContext()]);
});

Clazz.newMeth(C$, 'scheduleProcessingRunnable$sun_awt_AppContext', function (context) {
if (this.processingRunnable.markPending()) {
(I$[13]||$incl$(13)).getSystemEventQueueImplPP$sun_awt_AppContext(context).postEvent$java_awt_AWTEvent(Clazz.new_((I$[14]||$incl$(14)).c$$O$I$Runnable$O$Z,[(I$[8]||$incl$(8)).getDefaultToolkit(), 1200, this.processingRunnable, null, false]));
}});
;
(function(){var C$=Clazz.newClass(P$.RepaintManager, "ProcessingRunnable", function(){
Clazz.newInstance(this, arguments[0],true,C$);
}, null, 'Runnable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pending = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'markPending', function () {
if (!this.pending) {
this.pending = true;
return true;
}return false;
});

Clazz.newMeth(C$, 'run', function () {
{
this.pending = false;
}this.this$0.scheduleHeavyWeightPaints();
this.this$0.validateInvalidComponents();
this.this$0.prePaintDirtyRegions();
});

Clazz.newMeth(C$);
})()
})();
//Created 2018-02-08 10:02:41
