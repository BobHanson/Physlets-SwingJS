(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.awt.Rectangle','java.awt.Point','java.lang.Error','java.awt.event.MouseWheelEvent','javax.swing.event.MenuDragMouseEvent','java.awt.event.MouseEvent','swingjs.api.Interface','java.awt.EventQueue','javax.swing.JComponent','java.awt.event.ActionEvent','swingjs.JSUtil',['javax.swing.SwingUtilities','.SharedOwnerFrame'],'sun.awt.AppContext','Thread']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SwingUtilities", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'javax.swing.SwingConstants');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'installSwingDropTargetAsNecessary$java_awt_Component$javax_swing_TransferHandler', function (c, t) {
}, 1);

Clazz.newMeth(C$, 'isRectangleContainingRectangle$java_awt_Rectangle$java_awt_Rectangle', function (a, b) {
if (b.x >= a.x && (b.x + b.width) <= (a.x + a.width)  && b.y >= a.y  && (b.y + b.height) <= (a.y + a.height) ) {
return true;
}return false;
}, 1);

Clazz.newMeth(C$, 'getLocalBounds$java_awt_Component', function (aComponent) {
var b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[aComponent.getBounds()]);
b.x = b.y = 0;
return b;
}, 1);

Clazz.newMeth(C$, 'getWindowAncestor$java_awt_Component', function (c) {
for (var p = c.getParent(); p != null ; p = p.getParent()) {
if (Clazz.instanceOf(p, "java.awt.Window")) {
return p;
}}
return null;
}, 1);

Clazz.newMeth(C$, 'convertScreenLocationToParent$java_awt_Container$I$I', function (parent, x, y) {
for (var p = parent; p != null ; p = p.getParent()) {
if (Clazz.instanceOf(p, "java.awt.Window")) {
var point = Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[x, y]);
C$.convertPointFromScreen$java_awt_Point$java_awt_Component(point, parent);
return point;
}}
throw Clazz.new_((I$[3]||$incl$(3)).c$$S,["convertScreenLocationToParent: no window ancestor"]);
}, 1);

Clazz.newMeth(C$, 'convertPoint$java_awt_Component$java_awt_Point$java_awt_Component', function (source, aPoint, destination) {
var p;
if (source == null  && destination == null  ) return aPoint;
if (source == null ) {
source = C$.getWindowAncestor$java_awt_Component(destination);
if (source == null ) throw Clazz.new_((I$[3]||$incl$(3)).c$$S,["Source component not connected to component tree hierarchy"]);
}p = Clazz.new_((I$[2]||$incl$(2)).c$$java_awt_Point,[aPoint]);
C$.convertPointToScreen$java_awt_Point$java_awt_Component(p, source);
if (destination == null ) {
destination = C$.getWindowAncestor$java_awt_Component(source);
if (destination == null ) throw Clazz.new_((I$[3]||$incl$(3)).c$$S,["Destination component not connected to component tree hierarchy"]);
}C$.convertPointFromScreen$java_awt_Point$java_awt_Component(p, destination);
return p;
}, 1);

Clazz.newMeth(C$, 'convertPoint$java_awt_Component$I$I$java_awt_Component', function (source, x, y, destination) {
var point = Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[x, y]);
return C$.convertPoint$java_awt_Component$java_awt_Point$java_awt_Component(source, point, destination);
}, 1);

Clazz.newMeth(C$, 'convertRectangle$java_awt_Component$java_awt_Rectangle$java_awt_Component', function (source, aRectangle, destination) {
var point = Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[aRectangle.x, aRectangle.y]);
point = C$.convertPoint$java_awt_Component$java_awt_Point$java_awt_Component(source, point, destination);
return Clazz.new_((I$[1]||$incl$(1)).c$$I$I$I$I,[point.x, point.y, aRectangle.width, aRectangle.height]);
}, 1);

Clazz.newMeth(C$, 'getAncestorOfClass$Class$java_awt_Component', function (c, comp) {
if (comp == null  || c == null  ) return null;
var parent = comp.getParent();
while (parent != null  && !(c.isInstance$O(parent)) )parent = parent.getParent();

return parent;
}, 1);

Clazz.newMeth(C$, 'getAncestorNamed$S$java_awt_Component', function (name, comp) {
if (comp == null  || name == null  ) return null;
var parent = comp.getParent();
while (parent != null  && !(name.equals$O(parent.getName())) )parent = parent.getParent();

return parent;
}, 1);

Clazz.newMeth(C$, 'getDeepestComponentAt$java_awt_Component$I$I', function (parent, x, y) {
if (!parent.contains$I$I(x, y)) {
return null;
}if (Clazz.instanceOf(parent, "java.awt.Container")) {
var components = (parent).getComponents();
for (var i = 0; i < components.length; i++) {
var comp = components[i];
if (comp != null  && comp.isVisible() ) {
var loc = comp.getLocation();
if (Clazz.instanceOf(comp, "java.awt.Container")) {
comp = C$.getDeepestComponentAt$java_awt_Component$I$I(comp, x - loc.x, y - loc.y);
} else {
comp = comp.getComponentAt$I$I(x - loc.x, y - loc.y);
}if (comp != null  && comp.isVisible() ) {
return comp;
}}}
}return parent;
}, 1);

Clazz.newMeth(C$, 'convertMouseEvent$java_awt_Component$java_awt_event_MouseEvent$java_awt_Component', function (source, sourceEvent, destination) {
var p = C$.convertPoint$java_awt_Component$java_awt_Point$java_awt_Component(source, Clazz.new_((I$[2]||$incl$(2)).c$$I$I,[sourceEvent.getX(), sourceEvent.getY()]), destination);
var newSource;
if (destination != null ) newSource = destination;
 else newSource = source;
var newEvent;
if (Clazz.instanceOf(sourceEvent, "java.awt.event.MouseWheelEvent")) {
var sourceWheelEvent = sourceEvent;
newEvent = Clazz.new_((I$[4]||$incl$(4)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I$I$I,[newSource, sourceWheelEvent.getID(), sourceWheelEvent.getWhen(), sourceWheelEvent.getModifiers(), p.x, p.y, sourceWheelEvent.getXOnScreen(), sourceWheelEvent.getYOnScreen(), sourceWheelEvent.getClickCount(), sourceWheelEvent.isPopupTrigger(), sourceWheelEvent.getScrollType(), sourceWheelEvent.getScrollAmount(), sourceWheelEvent.getWheelRotation()]);
} else if (Clazz.instanceOf(sourceEvent, "javax.swing.event.MenuDragMouseEvent")) {
var sourceMenuDragEvent = sourceEvent;
newEvent = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$javax_swing_MenuElementA$javax_swing_MenuSelectionManager,[newSource, sourceMenuDragEvent.getID(), sourceMenuDragEvent.getWhen(), sourceMenuDragEvent.getModifiers(), p.x, p.y, sourceMenuDragEvent.getXOnScreen(), sourceMenuDragEvent.getYOnScreen(), sourceMenuDragEvent.getClickCount(), sourceMenuDragEvent.isPopupTrigger(), sourceMenuDragEvent.getPath(), sourceMenuDragEvent.getMenuSelectionManager()]);
} else {
newEvent = Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[newSource, sourceEvent.getID(), sourceEvent.getWhen(), sourceEvent.getModifiers(), p.x, p.y, sourceEvent.getXOnScreen(), sourceEvent.getYOnScreen(), sourceEvent.getClickCount(), sourceEvent.isPopupTrigger(), 0]);
}return newEvent;
}, 1);

Clazz.newMeth(C$, 'convertPointToScreen$java_awt_Point$java_awt_Component', function (p, c) {
var x;
var y;
do {
if (Clazz.instanceOf(c, "javax.swing.JComponent")) {
x = (c).getX();
y = (c).getY();
} else if (Clazz.instanceOf(c, "java.applet.Applet") || Clazz.instanceOf(c, "java.awt.Window") ) {
try {
var pp = c.getLocationOnScreen();
x = pp.x;
y = pp.y;
} catch (icse) {
if (Clazz.exceptionOf(icse, "java.awt.IllegalComponentStateException")){
x = c.getX();
y = c.getY();
} else {
throw icse;
}
}
} else {
x = c.getX();
y = c.getY();
}p.x = p.x+(x);
p.y = p.y+(y);
if (Clazz.instanceOf(c, "java.awt.Window") || Clazz.instanceOf(c, "java.applet.Applet") ) break;
c = c.getParent();
} while (c != null );
}, 1);

Clazz.newMeth(C$, 'convertPointFromScreen$java_awt_Point$java_awt_Component', function (p, c) {
var x;
var y;
do {
if (Clazz.instanceOf(c, "javax.swing.JComponent")) {
x = (c).getX();
y = (c).getY();
} else if (Clazz.instanceOf(c, "java.applet.Applet") || Clazz.instanceOf(c, "java.awt.Window") ) {
try {
var pp = c.getLocationOnScreen();
x = pp.x;
y = pp.y;
} catch (icse) {
if (Clazz.exceptionOf(icse, "java.awt.IllegalComponentStateException")){
x = c.getX();
y = c.getY();
} else {
throw icse;
}
}
} else {
x = c.getX();
y = c.getY();
}p.x = p.x-(x);
p.y = p.y-(y);
if (Clazz.instanceOf(c, "java.awt.Window") || Clazz.instanceOf(c, "java.applet.Applet") ) break;
c = c.getParent();
} while (c != null );
}, 1);

Clazz.newMeth(C$, 'windowForComponent$java_awt_Component', function (c) {
return C$.getWindowAncestor$java_awt_Component(c);
}, 1);

Clazz.newMeth(C$, 'isDescendingFrom$java_awt_Component$java_awt_Component', function (a, b) {
if (a === b ) return true;
for (var p = a.getParent(); p != null ; p = p.getParent()) if (p === b ) return true;

return false;
}, 1);

Clazz.newMeth(C$, 'computeIntersection$I$I$I$I$java_awt_Rectangle', function (x, y, width, height, dest) {
var x1 = (x > dest.x) ? x : dest.x;
var x2 = ((x + width) < (dest.x + dest.width)) ? (x + width) : (dest.x + dest.width);
var y1 = (y > dest.y) ? y : dest.y;
var y2 = ((y + height) < (dest.y + dest.height) ? (y + height) : (dest.y + dest.height));
dest.x = x1;
dest.y = y1;
dest.width = x2 - x1;
dest.height = y2 - y1;
if (dest.width < 0 || dest.height < 0 ) {
dest.x = dest.y = dest.width = dest.height = 0;
}return dest;
}, 1);

Clazz.newMeth(C$, 'computeUnion$I$I$I$I$java_awt_Rectangle', function (x, y, width, height, dest) {
var x1 = (x < dest.x) ? x : dest.x;
var x2 = ((x + width) > (dest.x + dest.width)) ? (x + width) : (dest.x + dest.width);
var y1 = (y < dest.y) ? y : dest.y;
var y2 = ((y + height) > (dest.y + dest.height)) ? (y + height) : (dest.y + dest.height);
dest.x = x1;
dest.y = y1;
dest.width = (x2 - x1);
dest.height = (y2 - y1);
return dest;
}, 1);

Clazz.newMeth(C$, 'computeDifference$java_awt_Rectangle$java_awt_Rectangle', function (rectA, rectB) {
if (rectB == null  || !rectA.intersects$java_awt_Rectangle(rectB)  || C$.isRectangleContainingRectangle$java_awt_Rectangle$java_awt_Rectangle(rectB, rectA) ) {
return Clazz.array((I$[1]||$incl$(1)), [0]);
}var t = Clazz.new_((I$[1]||$incl$(1)));
var a = null;
var b = null;
var c = null;
var d = null;
var result;
var rectCount = 0;
if (C$.isRectangleContainingRectangle$java_awt_Rectangle$java_awt_Rectangle(rectA, rectB)) {
t.x = rectA.x;
t.y = rectA.y;
t.width = rectB.x - rectA.x;
t.height = rectA.height;
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.x = rectB.x;
t.y = rectA.y;
t.width = rectB.width;
t.height = rectB.y - rectA.y;
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.x = rectB.x;
t.y = rectB.y + rectB.height;
t.width = rectB.width;
t.height = rectA.y + rectA.height - (rectB.y + rectB.height);
if (t.width > 0 && t.height > 0 ) {
c = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.x = rectB.x + rectB.width;
t.y = rectA.y;
t.width = rectA.x + rectA.width - (rectB.x + rectB.width);
t.height = rectA.height;
if (t.width > 0 && t.height > 0 ) {
d = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}} else {
if (rectB.x <= rectA.x && rectB.y <= rectA.y ) {
if ((rectB.x + rectB.width) > (rectA.x + rectA.width)) {
t.x = rectA.x;
t.y = rectB.y + rectB.height;
t.width = rectA.width;
t.height = rectA.y + rectA.height - (rectB.y + rectB.height);
if (t.width > 0 && t.height > 0 ) {
a = t;
rectCount++;
}} else if ((rectB.y + rectB.height) > (rectA.y + rectA.height)) {
t.setBounds$I$I$I$I((rectB.x + rectB.width), rectA.y, (rectA.x + rectA.width) - (rectB.x + rectB.width), rectA.height);
if (t.width > 0 && t.height > 0 ) {
a = t;
rectCount++;
}} else {
t.setBounds$I$I$I$I((rectB.x + rectB.width), rectA.y, (rectA.x + rectA.width) - (rectB.x + rectB.width), (rectB.y + rectB.height) - rectA.y);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.setBounds$I$I$I$I(rectA.x, (rectB.y + rectB.height), rectA.width, (rectA.y + rectA.height) - (rectB.y + rectB.height));
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}}} else if (rectB.x <= rectA.x && (rectB.y + rectB.height) >= (rectA.y + rectA.height) ) {
if ((rectB.x + rectB.width) > (rectA.x + rectA.width)) {
t.setBounds$I$I$I$I(rectA.x, rectA.y, rectA.width, rectB.y - rectA.y);
if (t.width > 0 && t.height > 0 ) {
a = t;
rectCount++;
}} else {
t.setBounds$I$I$I$I(rectA.x, rectA.y, rectA.width, rectB.y - rectA.y);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I((rectB.x + rectB.width), rectB.y, (rectA.x + rectA.width) - (rectB.x + rectB.width), (rectA.y + rectA.height) - rectB.y);
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}}} else if (rectB.x <= rectA.x) {
if ((rectB.x + rectB.width) >= (rectA.x + rectA.width)) {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectA.width, rectB.y - rectA.y);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I(rectA.x, (rectB.y + rectB.height), rectA.width, (rectA.y + rectA.height) - (rectB.y + rectB.height));
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}} else {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectA.width, rectB.y - rectA.y);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I((rectB.x + rectB.width), rectB.y, (rectA.x + rectA.width) - (rectB.x + rectB.width), rectB.height);
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I(rectA.x, (rectB.y + rectB.height), rectA.width, (rectA.y + rectA.height) - (rectB.y + rectB.height));
if (t.width > 0 && t.height > 0 ) {
c = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}}} else if (rectB.x <= (rectA.x + rectA.width) && (rectB.x + rectB.width) > (rectA.x + rectA.width) ) {
if (rectB.y <= rectA.y && (rectB.y + rectB.height) > (rectA.y + rectA.height) ) {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectB.x - rectA.x, rectA.height);
if (t.width > 0 && t.height > 0 ) {
a = t;
rectCount++;
}} else if (rectB.y <= rectA.y) {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectB.x - rectA.x, (rectB.y + rectB.height) - rectA.y);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I(rectA.x, (rectB.y + rectB.height), rectA.width, (rectA.y + rectA.height) - (rectB.y + rectB.height));
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}} else if ((rectB.y + rectB.height) > (rectA.y + rectA.height)) {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectA.width, rectB.y - rectA.y);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I(rectA.x, rectB.y, rectB.x - rectA.x, (rectA.y + rectA.height) - rectB.y);
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}} else {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectA.width, rectB.y - rectA.y);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I(rectA.x, rectB.y, rectB.x - rectA.x, rectB.height);
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I(rectA.x, (rectB.y + rectB.height), rectA.width, (rectA.y + rectA.height) - (rectB.y + rectB.height));
if (t.width > 0 && t.height > 0 ) {
c = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}}} else if (rectB.x >= rectA.x && (rectB.x + rectB.width) <= (rectA.x + rectA.width) ) {
if (rectB.y <= rectA.y && (rectB.y + rectB.height) > (rectA.y + rectA.height) ) {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectB.x - rectA.x, rectA.height);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I((rectB.x + rectB.width), rectA.y, (rectA.x + rectA.width) - (rectB.x + rectB.width), rectA.height);
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}} else if (rectB.y <= rectA.y) {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectB.x - rectA.x, rectA.height);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I(rectB.x, (rectB.y + rectB.height), rectB.width, (rectA.y + rectA.height) - (rectB.y + rectB.height));
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I((rectB.x + rectB.width), rectA.y, (rectA.x + rectA.width) - (rectB.x + rectB.width), rectA.height);
if (t.width > 0 && t.height > 0 ) {
c = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}} else {
t.reshape$I$I$I$I(rectA.x, rectA.y, rectB.x - rectA.x, rectA.height);
if (t.width > 0 && t.height > 0 ) {
a = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I(rectB.x, rectA.y, rectB.width, rectB.y - rectA.y);
if (t.width > 0 && t.height > 0 ) {
b = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}t.reshape$I$I$I$I((rectB.x + rectB.width), rectA.y, (rectA.x + rectA.width) - (rectB.x + rectB.width), rectA.height);
if (t.width > 0 && t.height > 0 ) {
c = Clazz.new_((I$[1]||$incl$(1)).c$$java_awt_Rectangle,[t]);
rectCount++;
}}}}result = Clazz.array((I$[1]||$incl$(1)), [rectCount]);
rectCount = 0;
if (a != null ) result[rectCount++] = a;
if (b != null ) result[rectCount++] = b;
if (c != null ) result[rectCount++] = c;
if (d != null ) result[rectCount++] = d;
return result;
}, 1);

Clazz.newMeth(C$, 'isLeftMouseButton$java_awt_event_MouseEvent', function (anEvent) {
return ((anEvent.getModifiers() & 16) != 0);
}, 1);

Clazz.newMeth(C$, 'isMiddleMouseButton$java_awt_event_MouseEvent', function (anEvent) {
return ((anEvent.getModifiers() & 8) == 8);
}, 1);

Clazz.newMeth(C$, 'isRightMouseButton$java_awt_event_MouseEvent', function (anEvent) {
return ((anEvent.getModifiers() & 4) == 4);
}, 1);

Clazz.newMeth(C$, 'paintComponent$java_awt_Graphics$java_awt_Component$java_awt_Container$I$I$I$I', function (g, c, p, x, y, w, h) {
C$.getCellRendererPane$java_awt_Component$java_awt_Container(c, p).paintComponent$java_awt_Graphics$java_awt_Component$java_awt_Container$I$I$I$I$Z(g, c, p, x, y, w, h, false);
}, 1);

Clazz.newMeth(C$, 'paintComponent$java_awt_Graphics$java_awt_Component$java_awt_Container$java_awt_Rectangle', function (g, c, p, r) {
C$.paintComponent$java_awt_Graphics$java_awt_Component$java_awt_Container$I$I$I$I(g, c, p, r.x, r.y, r.width, r.height);
}, 1);

Clazz.newMeth(C$, 'getCellRendererPane$java_awt_Component$java_awt_Container', function (c, p) {
var shell = c.getParent();
if (Clazz.instanceOf(shell, "javax.swing.CellRendererPane")) {
if (shell.getParent() !== p ) {
p.add$java_awt_Component(shell);
}} else {
shell = (I$[7]||$incl$(7)).getInstance$S$Z("javax.swing.CellRendererPane", false);
shell.add$java_awt_Component(c);
p.add$java_awt_Component(shell);
}return shell;
}, 1);

Clazz.newMeth(C$, 'updateComponentTreeUI$java_awt_Component', function (c) {
C$.updateComponentTreeUI0$java_awt_Component(c);
c.invalidate();
c.validate();
c.repaint();
}, 1);

Clazz.newMeth(C$, 'updateComponentTreeUI0$java_awt_Component', function (c) {
if (Clazz.instanceOf(c, "javax.swing.JComponent")) {
var jc = c;
jc.updateUI();
var jpm = jc.getComponentPopupMenu();
if (jpm != null ) {
C$.updateComponentTreeUI$java_awt_Component(jpm);
}}var children = null;
if (Clazz.instanceOf(c, "javax.swing.JMenu")) {
children = (c).getMenuComponents();
} else if (Clazz.instanceOf(c, "java.awt.Container")) {
children = (c).getComponents();
}if (children != null ) {
for (var i = 0; i < children.length; i++) {
C$.updateComponentTreeUI0$java_awt_Component(children[i]);
}
}}, 1);

Clazz.newMeth(C$, 'invokeLater$Runnable', function (doRun) {
(I$[8]||$incl$(8)).invokeLater$Runnable(doRun);
}, 1);

Clazz.newMeth(C$, 'invokeAndWait$Runnable', function (doRun) {
(I$[8]||$incl$(8)).invokeAndWait$Runnable(doRun);
}, 1);

Clazz.newMeth(C$, 'isEventDispatchThread', function () {
return (I$[8]||$incl$(8)).isDispatchThread();
}, 1);

Clazz.newMeth(C$, 'getRootPane$java_awt_Component', function (c) {
if (Clazz.instanceOf(c, "javax.swing.RootPaneContainer")) {
return (c).getRootPane();
}for (; c != null ; c = c.getParent()) {
if (Clazz.instanceOf(c, "javax.swing.JRootPane")) {
return c;
}}
return null;
}, 1);

Clazz.newMeth(C$, 'getRoot$java_awt_Component', function (c) {
var applet = null;
for (var p = c; p != null ; p = p.getParent()) {
if (Clazz.instanceOf(p, "java.awt.Window")) {
return p;
}if (Clazz.instanceOf(p, "java.applet.Applet")) {
applet = p;
}}
return applet;
}, 1);

Clazz.newMeth(C$, 'processKeyBindings$java_awt_event_KeyEvent', function (event) {
if (event != null ) {
if (event.isConsumed()) {
return false;
}var component = event.getComponent();
var pressed = (event.getID() == 401);
if (!C$.isValidKeyEventForKeyBindings$java_awt_event_KeyEvent(event)) {
return false;
}while (component != null ){
if (Clazz.instanceOf(component, "javax.swing.JComponent")) {
return (component).processKeyBindings$java_awt_event_KeyEvent$Z(event, pressed);
}if ((Clazz.instanceOf(component, "java.applet.Applet")) || (Clazz.instanceOf(component, "java.awt.Window")) ) {
return (I$[9]||$incl$(9)).processKeyBindingsForAllComponents$java_awt_event_KeyEvent$java_awt_Container$Z(event, component, pressed);
}component = component.getParent();
}
}return false;
}, 1);

Clazz.newMeth(C$, 'isValidKeyEventForKeyBindings$java_awt_event_KeyEvent', function (e) {
if (e.getID() == 400) {
var mod = e.getModifiers();
if (((mod & 8) != 0) && ((mod & 2) == 0) ) {
return false;
}}return true;
}, 1);

Clazz.newMeth(C$, 'notifyAction$javax_swing_Action$javax_swing_KeyStroke$java_awt_event_KeyEvent$O$I', function (action, ks, event, sender, modifiers) {
if (action == null ) {
return false;
}if (Clazz.instanceOf(action, "sun.swing.UIAction")) {
if (!(action).isEnabled$O(sender)) {
return false;
}} else if (!action.isEnabled()) {
return false;
}var commandO;
var stayNull;
commandO = action.getValue$S("ActionCommandKey");
if (commandO == null  && (I$[9]||$incl$(9)).isActionStandin$javax_swing_Action(action) ) {
stayNull = true;
} else {
stayNull = false;
}var command;
if (commandO != null ) {
command = commandO.toString();
} else if (!stayNull && event.getKeyChar() != "\uffff" ) {
command = String.valueOf(event.getKeyChar());
} else {
command = null;
}action.actionPerformed$java_awt_event_ActionEvent(Clazz.new_((I$[10]||$incl$(10)).c$$O$I$S$J$I,[sender, 1001, command, event.getWhen(), modifiers]));
return true;
}, 1);

Clazz.newMeth(C$, 'replaceUIInputMap$javax_swing_JComponent$I$javax_swing_InputMap', function (component, type, uiInputMap) {
var map = component.getInputMap$I$Z(type, (uiInputMap != null ));
while (map != null ){
var parent = map.getParent();
if (parent == null  || (Clazz.instanceOf(parent, "javax.swing.plaf.UIResource")) ) {
map.setParent$javax_swing_InputMap(uiInputMap);
return;
}map = parent;
}
}, 1);

Clazz.newMeth(C$, 'replaceUIActionMap$javax_swing_JComponent$javax_swing_ActionMap', function (component, uiActionMap) {
var map = component.getActionMap$Z((uiActionMap != null ));
while (map != null ){
var parent = map.getParent();
if (parent == null  || (Clazz.instanceOf(parent, "javax.swing.plaf.UIResource")) ) {
map.setParent$javax_swing_ActionMap(uiActionMap);
return;
}map = parent;
}
}, 1);

Clazz.newMeth(C$, 'getUIInputMap$javax_swing_JComponent$I', function (component, condition) {
var map = component.getInputMap$I$Z(condition, false);
while (map != null ){
var parent = map.getParent();
if (Clazz.instanceOf(parent, "javax.swing.plaf.UIResource")) {
return parent;
}map = parent;
}
return null;
}, 1);

Clazz.newMeth(C$, 'getUIActionMap$javax_swing_JComponent', function (component) {
var map = component.getActionMap$Z(false);
while (map != null ){
var parent = map.getParent();
if (Clazz.instanceOf(parent, "javax.swing.plaf.UIResource")) {
return parent;
}map = parent;
}
return null;
}, 1);

Clazz.newMeth(C$, 'getSharedOwnerFrame', function () {
var p = (I$[11]||$incl$(11)).getAppletViewer();
var f = p.sharedOwnerFrame;
return (f == null  ? (p.sharedOwnerFrame = Clazz.new_((I$[12]||$incl$(12)))) : f);
}, 1);

Clazz.newMeth(C$, 'getSharedOwnerFrameShutdownListener', function () {
var sharedOwnerFrame = C$.getSharedOwnerFrame();
return sharedOwnerFrame;
}, 1);

Clazz.newMeth(C$, 'appContextGet$O', function (key) {
return (I$[13]||$incl$(13)).getAppContext().get$O(key);
}, 1);

Clazz.newMeth(C$, 'appContextPut$O$O', function (key, value) {
(I$[13]||$incl$(13)).getAppContext().put$O$O(key, value);
}, 1);

Clazz.newMeth(C$, 'appContextRemove$O', function (key) {
(I$[13]||$incl$(13)).getAppContext().remove$O(key);
}, 1);

Clazz.newMeth(C$, 'loadSystemClass$S', function (className) {
return Clazz.forName(className, true, (I$[14]||$incl$(14)).currentThread().getContextClassLoader());
}, 1);

Clazz.newMeth(C$, 'isLeftToRight$java_awt_Component', function (c) {
return c.getComponentOrientation().isLeftToRight();
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
throw Clazz.new_((I$[3]||$incl$(3)).c$$S,["SwingUtilities is just a container for static methods"]);
}, 1);

Clazz.newMeth(C$, 'doesIconReferenceImage$javax_swing_Icon$java_awt_Image', function (icon, image) {
var iconImage = (icon != null  && (Clazz.instanceOf(icon, "javax.swing.ImageIcon")) ) ? (icon).getImage() : null;
return (iconImage === image );
}, 1);

Clazz.newMeth(C$, 'findDisplayedMnemonicIndex$S$I', function (text, mnemonic) {
if (text == null  || mnemonic == 0  ) {
return -1;
}var uc = Character.toUpperCase(String.fromCharCode(mnemonic));
var lc = Character.toLowerCase(String.fromCharCode(mnemonic));
var uci = text.indexOf(uc);
var lci = text.indexOf(lc);
if (uci == -1) {
return lci;
} else if (lci == -1) {
return uci;
} else {
return (lci < uci) ? lci : uci;
}}, 1);

Clazz.newMeth(C$, 'calculateInnerArea$javax_swing_JComponent$java_awt_Rectangle', function (c, r) {
if (c == null ) {
return null;
}var rect = r;
var insets = c.getInsets();
if (rect == null ) {
rect = Clazz.new_((I$[1]||$incl$(1)));
}rect.x = insets.left;
rect.y = insets.top;
rect.width = c.getWidth() - insets.left - insets.right ;
rect.height = c.getHeight() - insets.top - insets.bottom ;
return rect;
}, 1);

Clazz.newMeth(C$, 'updateRendererOrEditorUI$O', function (rendererOrEditor) {
if (rendererOrEditor == null ) {
return;
}var component = null;
if (Clazz.instanceOf(rendererOrEditor, "java.awt.Component")) {
component = rendererOrEditor;
}if (Clazz.instanceOf(rendererOrEditor, "javax.swing.DefaultCellEditor")) {
component = (rendererOrEditor).getComponent();
}if (component != null ) {
C$.updateComponentTreeUI$java_awt_Component(component);
}}, 1);
;
(function(){var C$=Clazz.newClass(P$.SwingUtilities, "SharedOwnerFrame", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, 'javax.swing.JFrame', 'java.awt.event.WindowListener');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'addNotify', function () {
this.updateUI();
this.getOrCreatePeer();
C$.superclazz.prototype.addNotify.apply(this, []);
this.installListeners();
});

Clazz.newMeth(C$, 'installListeners', function () {
var windows = this.getOwnedWindows();
for (var ind = 0; ind < windows.length; ind++) {
var window = windows[ind];
if (window != null ) {
window.removeWindowListener$java_awt_event_WindowListener(this);
window.addWindowListener$java_awt_event_WindowListener(this);
}}
});

Clazz.newMeth(C$, 'windowClosed$java_awt_event_WindowEvent', function (e) {
var windows = this.getOwnedWindows();
for (var ind = 0; ind < windows.length; ind++) {
var window = windows[ind];
if (window != null ) {
if (window.isDisplayable()) {
return;
}window.removeWindowListener$java_awt_event_WindowListener(this);
}this.dispose();
}
});

Clazz.newMeth(C$, 'windowOpened$java_awt_event_WindowEvent', function (e) {
});

Clazz.newMeth(C$, 'windowClosing$java_awt_event_WindowEvent', function (e) {
});

Clazz.newMeth(C$, 'windowIconified$java_awt_event_WindowEvent', function (e) {
});

Clazz.newMeth(C$, 'windowDeiconified$java_awt_event_WindowEvent', function (e) {
});

Clazz.newMeth(C$, 'windowActivated$java_awt_event_WindowEvent', function (e) {
});

Clazz.newMeth(C$, 'windowDeactivated$java_awt_event_WindowEvent', function (e) {
});

Clazz.newMeth(C$, 'show', function () {
});

Clazz.newMeth(C$, 'dispose', function () {
});
})()
})();
//Created 2018-02-06 08:59:43
