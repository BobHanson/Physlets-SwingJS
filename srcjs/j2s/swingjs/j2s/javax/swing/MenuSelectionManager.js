(function(){var P$=Clazz.newPackage("javax.swing"),I$=[['java.util.Vector','javax.swing.event.EventListenerList','sun.awt.AppContext','javax.swing.MenuElement','javax.swing.event.ChangeListener','javax.swing.event.ChangeEvent','javax.swing.SwingUtilities','java.awt.event.MouseEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "MenuSelectionManager");
C$.MENU_SELECTION_MANAGER_KEY = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.MENU_SELECTION_MANAGER_KEY =  Clazz.new_();
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.selection = null;
this.changeEvent = null;
this.listenerList = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.selection = Clazz.new_((I$[1]||$incl$(1)));
this.changeEvent = null;
this.listenerList = Clazz.new_((I$[2]||$incl$(2)));
}, 1);

Clazz.newMeth(C$, 'defaultManager', function () {
{
var context = (I$[3]||$incl$(3)).getAppContext();
var msm = context.get$O(C$.MENU_SELECTION_MANAGER_KEY);
if (msm == null ) {
msm = Clazz.new_(C$);
context.put$O$O(C$.MENU_SELECTION_MANAGER_KEY, msm);
}return msm;
}}, 1);

Clazz.newMeth(C$, 'setSelectedPath$javax_swing_MenuElementA', function (path) {
var i;
var c;
var currentSelectionCount = this.selection.size();
var firstDifference = 0;
if (path == null ) {
path = Clazz.array((I$[4]||$incl$(4)), [0]);
}for (i = 0, c = path.length; i < c; i++) {
if (i < currentSelectionCount && this.selection.elementAt$I(i) === path[i]  ) firstDifference++;
 else break;
}
for (i = currentSelectionCount - 1; i >= firstDifference; i--) {
var me = this.selection.elementAt$I(i);
this.selection.removeElementAt$I(i);
me.menuSelectionChanged$Z(false);
}
for (i = firstDifference, c = path.length; i < c; i++) {
if (path[i] != null ) {
this.selection.addElement$TE(path[i]);
path[i].menuSelectionChanged$Z(true);
}}
this.fireStateChanged();
});

Clazz.newMeth(C$, 'getSelectedPath', function () {
var res = Clazz.array((I$[4]||$incl$(4)), [this.selection.size()]);
var i;
var c;
for (i = 0, c = this.selection.size(); i < c; i++) res[i] = this.selection.elementAt$I(i);

return res;
});

Clazz.newMeth(C$, 'clearSelectedPath', function () {
if (this.selection.size() > 0) {
this.setSelectedPath$javax_swing_MenuElementA(null);
}});

Clazz.newMeth(C$, 'addChangeListener$javax_swing_event_ChangeListener', function (l) {
this.listenerList.add$Class$TT(Clazz.getClass((I$[5]||$incl$(5)),['stateChanged$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'removeChangeListener$javax_swing_event_ChangeListener', function (l) {
this.listenerList.remove$Class$TT(Clazz.getClass((I$[5]||$incl$(5)),['stateChanged$javax_swing_event_ChangeEvent']), l);
});

Clazz.newMeth(C$, 'getChangeListeners', function () {
return this.listenerList.getListeners$Class(Clazz.getClass((I$[5]||$incl$(5)),['stateChanged$javax_swing_event_ChangeEvent']));
});

Clazz.newMeth(C$, 'fireStateChanged', function () {
var listeners = this.listenerList.getListenerList();
for (var i = listeners.length - 2; i >= 0; i = i-(2)) {
if (listeners[i] === Clazz.getClass((I$[5]||$incl$(5)),['stateChanged$javax_swing_event_ChangeEvent']) ) {
if (this.changeEvent == null ) this.changeEvent = Clazz.new_((I$[6]||$incl$(6)).c$$O,[this]);
(listeners[i + 1]).stateChanged$javax_swing_event_ChangeEvent(this.changeEvent);
}}
});

Clazz.newMeth(C$, 'processMouseEvent$java_awt_event_MouseEvent', function (event) {
var screenX;
var screenY;
var p;
var i;
var j;
var d;
var mc;
var r2;
var cWidth;
var cHeight;
var menuElement;
var subElements;
var path;
var tmp;
var selectionSize;
p = event.getPoint();
var source = event.getSource();
if (!source.isShowing()) {
return;
}var type = event.getID();
var modifiers = event.getModifiers();
if ((type == 504 || type == 505 ) && ((modifiers & 28) != 0) ) {
return;
}(I$[7]||$incl$(7)).convertPointToScreen$java_awt_Point$java_awt_Component(p, source);
screenX = p.x;
screenY = p.y;
tmp = this.selection.clone();
selectionSize = tmp.size();
var success = false;
for (i = selectionSize - 1; i >= 0 && success == false  ; i--) {
menuElement = tmp.elementAt$I(i);
subElements = menuElement.getSubElements();
path = null;
for (j = 0, d = subElements.length; j < d && success == false  ; j++) {
if (subElements[j] == null ) continue;
mc = subElements[j].getComponent();
if (!mc.isShowing()) continue;
if (Clazz.instanceOf(mc, "javax.swing.JComponent")) {
cWidth = (mc).getWidth();
cHeight = (mc).getHeight();
} else {
r2 = mc.getBounds();
cWidth = r2.width;
cHeight = r2.height;
}p.x = screenX;
p.y = screenY;
(I$[7]||$incl$(7)).convertPointFromScreen$java_awt_Point$java_awt_Component(p, mc);
if ((p.x >= 0 && p.x < cWidth  && p.y >= 0  && p.y < cHeight )) {
var k;
if (path == null ) {
path = Clazz.array((I$[4]||$incl$(4)), [i + 2]);
for (k = 0; k <= i; k++) path[k] = tmp.elementAt$I(k);

}path[i + 1] = subElements[j];
var currentSelection = this.getSelectedPath();
if (currentSelection[currentSelection.length - 1] !== path[i + 1]  && (currentSelection.length < 2 || currentSelection[currentSelection.length - 2] !== path[i + 1]  ) ) {
var oldMC = currentSelection[currentSelection.length - 1].getComponent();
var exitEvent = Clazz.new_((I$[8]||$incl$(8)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[oldMC, 505, event.getWhen(), event.getModifiers(), p.x, p.y, event.getXOnScreen(), event.getYOnScreen(), event.getClickCount(), event.isPopupTrigger(), 0]);
currentSelection[currentSelection.length - 1].processMouseEvent$java_awt_event_MouseEvent$javax_swing_MenuElementA$javax_swing_MenuSelectionManager(exitEvent, path, this);
var enterEvent = Clazz.new_((I$[8]||$incl$(8)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[mc, 504, event.getWhen(), event.getModifiers(), p.x, p.y, event.getXOnScreen(), event.getYOnScreen(), event.getClickCount(), event.isPopupTrigger(), 0]);
subElements[j].processMouseEvent$java_awt_event_MouseEvent$javax_swing_MenuElementA$javax_swing_MenuSelectionManager(enterEvent, path, this);
}var mouseEvent = Clazz.new_((I$[8]||$incl$(8)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[mc, event.getID(), event.getWhen(), event.getModifiers(), p.x, p.y, event.getXOnScreen(), event.getYOnScreen(), event.getClickCount(), event.isPopupTrigger(), 0]);
subElements[j].processMouseEvent$java_awt_event_MouseEvent$javax_swing_MenuElementA$javax_swing_MenuSelectionManager(mouseEvent, path, this);
success = true;
event.consume();
}}
}
});

Clazz.newMeth(C$, 'componentForPoint$java_awt_Component$java_awt_Point', function (source, sourcePoint) {
var screenX;
var screenY;
var p = sourcePoint;
var i;
var j;
var d;
var mc;
var r2;
var cWidth;
var cHeight;
var menuElement;
var subElements;
var tmp;
var selectionSize;
(I$[7]||$incl$(7)).convertPointToScreen$java_awt_Point$java_awt_Component(p, source);
screenX = p.x;
screenY = p.y;
tmp = this.selection.clone();
selectionSize = tmp.size();
for (i = selectionSize - 1; i >= 0; i--) {
menuElement = tmp.elementAt$I(i);
subElements = menuElement.getSubElements();
for (j = 0, d = subElements.length; j < d; j++) {
if (subElements[j] == null ) continue;
mc = subElements[j].getComponent();
if (!mc.isShowing()) continue;
if (Clazz.instanceOf(mc, "javax.swing.JComponent")) {
cWidth = (mc).getWidth();
cHeight = (mc).getHeight();
} else {
r2 = mc.getBounds();
cWidth = r2.width;
cHeight = r2.height;
}p.x = screenX;
p.y = screenY;
(I$[7]||$incl$(7)).convertPointFromScreen$java_awt_Point$java_awt_Component(p, mc);
if (p.x >= 0 && p.x < cWidth  && p.y >= 0  && p.y < cHeight ) {
return mc;
}}
}
return null;
});

Clazz.newMeth(C$, 'processKeyEvent$java_awt_event_KeyEvent', function (e) {
var sel2 = Clazz.array((I$[4]||$incl$(4)), [0]);
sel2 = this.selection.toArray$TTA(sel2);
var selSize = sel2.length;
var path;
if (selSize < 1) {
return;
}for (var i = selSize - 1; i >= 0; i--) {
var elem = sel2[i];
var subs = elem.getSubElements();
path = null;
for (var j = 0; j < subs.length; j++) {
if (subs[j] == null  || !subs[j].getComponent().isShowing()  || !subs[j].getComponent().isEnabled() ) {
continue;
}if (path == null ) {
path = Clazz.array((I$[4]||$incl$(4)), [i + 2]);
System.arraycopy(sel2, 0, path, 0, i + 1);
}path[i + 1] = subs[j];
subs[j].processKeyEvent$java_awt_event_KeyEvent$javax_swing_MenuElementA$javax_swing_MenuSelectionManager(e, path, this);
if (e.isConsumed()) {
return;
}}
}
path = Clazz.array((I$[4]||$incl$(4)), [1]);
path[0] = sel2[0];
path[0].processKeyEvent$java_awt_event_KeyEvent$javax_swing_MenuElementA$javax_swing_MenuSelectionManager(e, path, this);
if (e.isConsumed()) {
return;
}});

Clazz.newMeth(C$, 'isComponentPartOfCurrentMenu$java_awt_Component', function (c) {
if (this.selection.size() > 0) {
var me = this.selection.elementAt$I(0);
return p$.isComponentPartOfCurrentMenu$javax_swing_MenuElement$java_awt_Component.apply(this, [me, c]);
} else return false;
});

Clazz.newMeth(C$, 'isComponentPartOfCurrentMenu$javax_swing_MenuElement$java_awt_Component', function (root, c) {
var children;
var i;
var d;
if (root == null ) return false;
if (root.getComponent() === c ) return true;
 else {
children = root.getSubElements();
for (i = 0, d = children.length; i < d; i++) {
if (p$.isComponentPartOfCurrentMenu$javax_swing_MenuElement$java_awt_Component.apply(this, [children[i], c])) return true;
}
}return false;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:40
