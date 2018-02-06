(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.awt.Component','java.awt.Insets','javajs.util.Lst','java.awt.Toolkit','java.awt.event.ContainerEvent','java.awt.Dimension',['java.awt.GraphicsCallback','.PaintCallback'],['java.awt.GraphicsCallback','.PaintAllCallback'],['java.awt.GraphicsCallback','.PaintHeavyweightComponentsCallback'],'java.awt.AWTEventMulticaster','java.awt.event.ContainerListener',['java.awt.Container','.MouseEventTargetFilter'],'java.awt.LightweightDispatcher','java.awt.event.MouseEvent','java.awt.event.MouseWheelEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Container", function(){
Clazz.newInstance(this, arguments,0,C$);
}, 'java.awt.JSComponent');
C$.EMPTY_ARRAY = null;
C$.NULL_INSETS = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.EMPTY_ARRAY = Clazz.array((I$[1]||$incl$(1)), [0]);
C$.NULL_INSETS = Clazz.new_((I$[2]||$incl$(2)).c$$I$I$I$I,[0, 0, 0, 0]);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.children = null;
this.layoutMgr = null;
this.dispatcher = null;
this.focusCycleRoot = false;
this.focusTraversalPolicyProvider = false;
this.containerListener = null;
this.listeningChildren = 0;
this.listeningBoundsChildren = 0;
this.descendantsCount = 0;
this.preserveBackgroundColor = null;
this.numOfHWComponents = 0;
this.numOfLWComponents = 0;
this.modalComp = null;
this.modalAppContext = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.focusCycleRoot = false;
this.preserveBackgroundColor = null;
this.numOfHWComponents = 0;
this.numOfLWComponents = 0;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
this.children = Clazz.new_((I$[3]||$incl$(3)));
}, 1);

Clazz.newMeth(C$, 'initializeFocusTraversalKeys', function () {
});

Clazz.newMeth(C$, 'getComponentCount', function () {
return this.countComponents();
});

Clazz.newMeth(C$, 'countComponents', function () {
return this.children.size();
});

Clazz.newMeth(C$, 'getComponent$I', function (n) {
if ((n < 0) || (n >= this.children.size()) ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,["No such child: " + n]);
}return this.children.get$I(n);
});

Clazz.newMeth(C$, 'getComponents', function () {
return this.getComponents_NoClientCode();
});

Clazz.newMeth(C$, 'getComponents_NoClientCode', function () {
return this.children.toArray$TTA(C$.EMPTY_ARRAY);
});

Clazz.newMeth(C$, 'getInsets', function () {
var i = (this.peer == null  ? null : (this.peer).getInsets());
return (i == null  ? C$.NULL_INSETS : i);
});

Clazz.newMeth(C$, 'insets', function () {
return this.getInsets();
});

Clazz.newMeth(C$, 'add$java_awt_Component', function (comp) {
return this.addImpl$java_awt_Component$O$I(comp, null, -1);
});

Clazz.newMeth(C$, 'add$S$java_awt_Component', function (name, comp) {
return this.addImpl$java_awt_Component$O$I(comp, name, -1);
});

Clazz.newMeth(C$, 'add$java_awt_Component$I', function (comp, index) {
return this.addImpl$java_awt_Component$O$I(comp, null, index);
});

Clazz.newMeth(C$, 'checkAddToSelf$java_awt_Component', function (comp) {
if (Clazz.instanceOf(comp, "java.awt.Container")) {
for (var cn = this; cn != null ; cn = cn.parent) {
if (cn === comp ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["adding container\'s parent to itself"]);
}}
}});

Clazz.newMeth(C$, 'checkNotAWindow$java_awt_Component', function (comp) {
if (Clazz.instanceOf(comp, "java.awt.Window")) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["adding a window to a container"]);
}});

Clazz.newMeth(C$, 'removeDelicately$java_awt_Component$java_awt_Container$I', function (comp, newParent, newIndex) {
var index = this.getComponentZOrder$java_awt_Component(comp);
var needRemoveNotify = C$.isRemoveNotifyNeeded$java_awt_Component$java_awt_Container$java_awt_Container(comp, this, newParent);
if (needRemoveNotify) {
comp.removeNotify();
}if (newParent !== this ) {
if (this.layoutMgr != null ) {
this.layoutMgr.removeLayoutComponent$java_awt_Component(comp);
}this.adjustListeningChildren$J$I(32768, -comp.numListening$J(32768));
this.adjustListeningChildren$J$I(65536, -comp.numListening$J(65536));
this.adjustDescendants$I(-(comp.countHierarchyMembers()));
comp.parent = null;
this.children.removeItemAt$I(index);
this.invalidateIfValid();
} else {
this.children.removeItemAt$I(index);
this.children.add$I$TE(newIndex, comp);
}if (comp.parent == null ) {
if (this.containerListener != null  || (this.eventMask & 2) != 0  || (I$[4]||$incl$(4)).enabledOnToolkit$J(2) ) {
var e = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Component$I$java_awt_Component,[this, 301, comp]);
this.dispatchEvent$java_awt_AWTEvent(e);
}comp.createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(1400, comp, this, 1, (I$[4]||$incl$(4)).enabledOnToolkit$J(32768));
if (this.peer != null  && this.layoutMgr == null   && this.isVisible() ) {
this.updateCursorImmediately();
}}return needRemoveNotify;
});

Clazz.newMeth(C$, 'canContainFocusOwner$java_awt_Component', function (focusOwnerCandidate) {
if (!(this.isEnabled() && this.isDisplayable() && this.isVisible() && this.isFocusable()  )) {
return false;
}{
if (this.parent != null ) {
return this.parent.canContainFocusOwner$java_awt_Component(focusOwnerCandidate);
}}return true;
});

Clazz.newMeth(C$, 'hasHeavyweightDescendants', function () {
return this.numOfHWComponents > 0;
});

Clazz.newMeth(C$, 'hasLightweightDescendants', function () {
return this.numOfLWComponents > 0;
});

Clazz.newMeth(C$, 'getHeavyweightContainer', function () {
if (this.peer != null  && !(Clazz.instanceOf(this.peer, "java.awt.peer.LightweightPeer")) ) {
return this;
} else {
return this.getNativeContainer();
}});

Clazz.newMeth(C$, 'isRemoveNotifyNeeded$java_awt_Component$java_awt_Container$java_awt_Container', function (comp, oldContainer, newContainer) {
return false;
}, 1);

Clazz.newMeth(C$, 'setComponentZOrder$java_awt_Component$I', function (comp, index) {
{
var curParent = comp.parent;
var oldZindex = this.getComponentZOrder$java_awt_Component(comp);
if (curParent === this  && index == oldZindex ) {
return;
}var peerRecreated = (curParent != null ) ? curParent.removeDelicately$java_awt_Component$java_awt_Container$I(comp, this, index) : false;
p$.addDelicately$java_awt_Component$java_awt_Container$I.apply(this, [comp, curParent, index]);
if (!peerRecreated && oldZindex != -1 ) {
comp.mixOnZOrderChanging$I$I(oldZindex, index);
}}});

Clazz.newMeth(C$, 'reparentTraverse$java_awt_peer_ContainerPeer$java_awt_Container', function (parentPeer, child) {
p$.checkTreeLock.apply(this, []);
for (var i = 0; i < child.getComponentCount(); i++) {
var comp = child.getComponent$I(i);
if (comp.isLightweight()) {
if (Clazz.instanceOf(comp, "java.awt.Container")) {
p$.reparentTraverse$java_awt_peer_ContainerPeer$java_awt_Container.apply(this, [parentPeer, comp]);
}} else {
comp.getPeer().reparent$java_awt_peer_ContainerPeer(parentPeer);
}}
});

Clazz.newMeth(C$, 'reparentChild$java_awt_Component', function (comp) {
if (comp == null ) {
return;
}if (comp.isLightweight()) {
if (Clazz.instanceOf(comp, "java.awt.Container")) {
p$.reparentTraverse$java_awt_peer_ContainerPeer$java_awt_Container.apply(this, [this.getPeer(), comp]);
}} else {
comp.getPeer().reparent$java_awt_peer_ContainerPeer(this.getPeer());
}});

Clazz.newMeth(C$, 'addDelicately$java_awt_Component$java_awt_Container$I', function (comp, curParent, index) {
p$.checkTreeLock.apply(this, []);
if (curParent !== this ) {
if (index == -1) {
this.children.add$TE(comp);
} else {
this.children.add$I$TE(index, comp);
}comp.parent = this;
this.adjustListeningChildren$J$I(32768, comp.numListening$J(32768));
this.adjustListeningChildren$J$I(65536, comp.numListening$J(65536));
this.adjustDescendants$I(comp.countHierarchyMembers());
} else {
if (index < this.children.size()) {
this.children.set$I$TE(index, comp);
}}this.invalidateIfValid();
if (this.peer != null ) {
if (comp.peer == null ) {
comp.addNotify();
} else {
var newNativeContainer = this.getHeavyweightContainer();
var oldNativeContainer = curParent.getHeavyweightContainer();
if (oldNativeContainer !== newNativeContainer ) {
newNativeContainer.reparentChild$java_awt_Component(comp);
}if (!comp.isLightweight() && this.isLightweight() ) {
comp.relocateComponent();
}}}if (curParent !== this ) {
if (this.layoutMgr != null ) {
if (Clazz.instanceOf(this.layoutMgr, "java.awt.LayoutManager2")) {
(this.layoutMgr).addLayoutComponent$java_awt_Component$O(comp, null);
} else {
this.layoutMgr.addLayoutComponent$S$java_awt_Component(null, comp);
}}if (this.containerListener != null  || (this.eventMask & 2) != 0  || (I$[4]||$incl$(4)).enabledOnToolkit$J(2) ) {
var e = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Component$I$java_awt_Component,[this, 300, comp]);
this.dispatchEvent$java_awt_AWTEvent(e);
}comp.createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(1400, comp, this, 1, (I$[4]||$incl$(4)).enabledOnToolkit$J(32768));
} else {
comp.createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(1400, comp, this, 1400, (I$[4]||$incl$(4)).enabledOnToolkit$J(32768));
}if (this.peer != null  && this.layoutMgr == null   && this.isVisible() ) {
this.updateCursorImmediately();
}});

Clazz.newMeth(C$, 'checkTreeLock', function () {
});

Clazz.newMeth(C$, 'getComponentZOrder$java_awt_Component', function (comp) {
if (comp == null ) {
return -1;
}{
if (comp.parent !== this ) {
return -1;
}return this.children.indexOf$O(comp);
}});

Clazz.newMeth(C$, 'add$java_awt_Component$O', function (comp, constraints) {
this.addImpl$java_awt_Component$O$I(comp, constraints, -1);
});

Clazz.newMeth(C$, 'add$java_awt_Component$O$I', function (comp, constraints, index) {
return this.addImpl$java_awt_Component$O$I(comp, constraints, index);
});

Clazz.newMeth(C$, 'addImpl$java_awt_Component$O$I', function (comp, constraints, index) {
return this.addImplCont$java_awt_Component$O$I(comp, constraints, index);
});

Clazz.newMeth(C$, 'addImplCont$java_awt_Component$O$I', function (comp, constraints, index) {
{
if (index > this.children.size() || (index < 0 && index != -1 ) ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["illegal component position"]);
}p$.checkAddToSelf$java_awt_Component.apply(this, [comp]);
p$.checkNotAWindow$java_awt_Component.apply(this, [comp]);
if (comp.parent != null ) {
comp.parent.remove$java_awt_Component(comp);
if (index > this.children.size()) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["illegal component position"]);
}}if (index == -1) {
this.children.add$TE(comp);
} else {
this.children.add$I$TE(index, comp);
}comp.parent = this;
this.adjustListeningChildren$J$I(32768, comp.numListening$J(32768));
this.adjustListeningChildren$J$I(65536, comp.numListening$J(65536));
this.adjustDescendants$I(comp.countHierarchyMembers());
this.invalidateIfValid();
if (this.peer != null ) {
comp.addNotify();
}if (this.layoutMgr != null ) {
if (Clazz.instanceOf(this.layoutMgr, "java.awt.LayoutManager2")) {
(this.layoutMgr).addLayoutComponent$java_awt_Component$O(comp, constraints);
} else if (Clazz.instanceOf(constraints, "java.lang.String")) {
this.layoutMgr.addLayoutComponent$S$java_awt_Component(constraints, comp);
}}if (this.containerListener != null  || (this.eventMask & 2) != 0  || (I$[4]||$incl$(4)).enabledOnToolkit$J(2) ) {
var e = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Component$I$java_awt_Component,[this, 300, comp]);
this.dispatchEvent$java_awt_AWTEvent(e);
}comp.createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(1400, comp, this, 1, (I$[4]||$incl$(4)).enabledOnToolkit$J(32768));
if (this.peer != null  && this.layoutMgr == null   && this.isVisible() ) {
this.updateCursorImmediately();
}}return comp;
});

Clazz.newMeth(C$, 'checkGD$S', function (stringID) {
});

Clazz.newMeth(C$, 'remove$I', function (index) {
{
if (index < 0 || index >= this.children.size() ) {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$I,[index]);
}var comp = this.children.get$I(index);
if (this.peer != null ) {
comp.removeNotify();
}if (this.layoutMgr != null ) {
this.layoutMgr.removeLayoutComponent$java_awt_Component(comp);
}this.adjustListeningChildren$J$I(32768, -comp.numListening$J(32768));
this.adjustListeningChildren$J$I(65536, -comp.numListening$J(65536));
this.adjustDescendants$I(-(comp.countHierarchyMembers()));
comp.parent = null;
this.children.removeItemAt$I(index);
this.invalidateIfValid();
if (this.containerListener != null  || (this.eventMask & 2) != 0  || (I$[4]||$incl$(4)).enabledOnToolkit$J(2) ) {
var e = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Component$I$java_awt_Component,[this, 301, comp]);
this.dispatchEvent$java_awt_AWTEvent(e);
}comp.createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(1400, comp, this, 1, (I$[4]||$incl$(4)).enabledOnToolkit$J(32768));
if (this.peer != null  && this.layoutMgr == null   && this.isVisible() ) {
this.updateCursorImmediately();
}}});

Clazz.newMeth(C$, 'remove$java_awt_Component', function (comp) {
{
if (comp.parent === this ) {
var index = this.children.indexOf$O(comp);
if (index >= 0) {
this.remove$I(index);
}}}});

Clazz.newMeth(C$, 'removeAll', function () {
{
this.adjustListeningChildren$J$I(32768, -this.listeningChildren);
this.adjustListeningChildren$J$I(65536, -this.listeningBoundsChildren);
this.adjustDescendants$I(-this.descendantsCount);
while (!this.children.isEmpty()){
var comp = this.children.removeItemAt$I(this.children.size() - 1);
if (this.peer != null ) {
comp.removeNotify();
}if (this.layoutMgr != null ) {
this.layoutMgr.removeLayoutComponent$java_awt_Component(comp);
}comp.parent = null;
if (this.containerListener != null  || (this.eventMask & 2) != 0  || (I$[4]||$incl$(4)).enabledOnToolkit$J(2) ) {
var e = Clazz.new_((I$[5]||$incl$(5)).c$$java_awt_Component$I$java_awt_Component,[this, 301, comp]);
this.dispatchEvent$java_awt_AWTEvent(e);
}comp.createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(1400, comp, this, 1, (I$[4]||$incl$(4)).enabledOnToolkit$J(32768));
}
if (this.peer != null  && this.layoutMgr == null   && this.isVisible() ) {
this.updateCursorImmediately();
}this.invalidateIfValid();
}});

Clazz.newMeth(C$, 'numListening$J', function (mask) {
var superListening = this.numListeningMask$J(mask);
if (mask == 32768) {
return this.listeningChildren + superListening;
} else if (mask == 65536) {
return this.listeningBoundsChildren + superListening;
} else {
return superListening;
}});

Clazz.newMeth(C$, 'adjustListeningChildren$J$I', function (mask, num) {
if (num == 0) return;
if ((mask & 32768) != 0) {
this.listeningChildren = this.listeningChildren+(num);
}if ((mask & 65536) != 0) {
this.listeningBoundsChildren = this.listeningBoundsChildren+(num);
}this.adjustListeningChildrenOnParent$J$I(mask, num);
});

Clazz.newMeth(C$, 'adjustDescendants$I', function (num) {
if (num == 0) return;
this.descendantsCount = this.descendantsCount+(num);
this.adjustDecendantsOnParent$I(num);
});

Clazz.newMeth(C$, 'adjustDecendantsOnParent$I', function (num) {
if (this.parent != null ) {
this.parent.adjustDescendants$I(num);
}});

Clazz.newMeth(C$, 'countHierarchyMembers', function () {
return this.descendantsCount + 1;
});

Clazz.newMeth(C$, 'getListenersCount$I$Z', function (id, enabledOnToolkit) {
if (enabledOnToolkit) {
return this.descendantsCount;
}switch (id) {
case 1400:
return this.listeningChildren;
case 1401:
case 1402:
return this.listeningBoundsChildren;
default:
return 0;
}
});

Clazz.newMeth(C$, 'createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z', function (id, changed, changedParent, changeFlags, enabledOnToolkit) {
var listeners = p$.getListenersCount$I$Z.apply(this, [id, enabledOnToolkit]);
for (var count = listeners, i = 0; count > 0; i++) {
count = count-(this.children.get$I(i).createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(id, changed, changedParent, changeFlags, enabledOnToolkit));
}
return listeners + this.createHierEventsComp$I$java_awt_Component$java_awt_Container$J$Z(id, changed, changedParent, changeFlags, enabledOnToolkit);
});

Clazz.newMeth(C$, 'createChildHierarchyEvents$I$J$Z', function (id, changeFlags, enabledOnToolkit) {
if (this.children.isEmpty()) {
return;
}var listeners = p$.getListenersCount$I$Z.apply(this, [id, enabledOnToolkit]);
for (var count = listeners, i = 0; count > 0; i++) {
count = count-(this.children.get$I(i).createHierarchyEvents$I$java_awt_Component$java_awt_Container$J$Z(id, this, this.parent, changeFlags, enabledOnToolkit));
}
});

Clazz.newMeth(C$, 'getLayout', function () {
return this.layoutMgr;
});

Clazz.newMeth(C$, 'setLayout$java_awt_LayoutManager', function (mgr) {
this.layoutMgr = mgr;
this.invalidateIfValid();
});

Clazz.newMeth(C$, 'doLayout', function () {
this.layout();
});

Clazz.newMeth(C$, 'layout', function () {
if (this.layoutMgr != null  && this.width > 0  && this.height > 0 ) {
this.layoutMgr.layoutContainer$java_awt_Container(this);
}});

Clazz.newMeth(C$, 'invalidate', function () {
var layoutMgr = this.layoutMgr;
if (Clazz.instanceOf(layoutMgr, "java.awt.LayoutManager2")) {
var lm = layoutMgr;
lm.invalidateLayout$java_awt_Container(this);
}this.invalidateComp();
});

Clazz.newMeth(C$, 'validate', function () {
if (!this.isValid()) {
{
if (this.peer == null ) this.peer = this.getToolkit().createComponent$java_awt_Component(this);
var n = this.children.size();
if (!this.isValid() && this.peer != null   && n > 0 ) {
var p = null;
if (Clazz.instanceOf(this.peer, "java.awt.peer.ContainerPeer")) p = this.peer;
if (p != null ) p.beginValidate();
this.validateTree();
if (p != null ) {
p.endValidate();
if (this.isVisible()) this.updateCursorImmediately();
}}}}});

Clazz.newMeth(C$, 'repackContainer', function () {
var newSize = this.getPreferredSize();
if (this.peer != null ) {
this.setClientSize$I$I(newSize.width, newSize.height);
}this.validate();
});

Clazz.newMeth(C$, 'setClientSize$I$I', function (w, h) {
{
this.setBoundsOp$I(4);
this.setBounds$I$I$I$I(this.x, this.y, w, h);
}});

Clazz.newMeth(C$, 'validateTree', function () {
if (!this.isValid()) {
if (Clazz.instanceOf(this.peer, "java.awt.peer.ContainerPeer")) {
(this.peer).beginLayout();
}this.doLayout();
for (var i = 0; i < this.children.size(); i++) {
var comp = this.children.get$I(i);
if ((Clazz.instanceOf(comp, "java.awt.Container")) && !comp.isValid() ) {
(comp).validateTree();
} else {
comp.validate();
}}
if (Clazz.instanceOf(this.peer, "java.awt.peer.ContainerPeer")) {
(this.peer).endLayout();
}}this.validateComponent();
});

Clazz.newMeth(C$, 'invalidateTree', function () {
{
for (var i = 0; i < this.children.size(); i++) {
var comp = this.children.get$I(i);
if (Clazz.instanceOf(comp, "java.awt.Container")) {
(comp).invalidateTree();
} else {
comp.invalidateIfValid();
}}
this.invalidateIfValid();
}});

Clazz.newMeth(C$, 'setFont$java_awt_Font', function (f) {
var oldfont = this.getFont();
C$.superclazz.prototype.setFont$java_awt_Font.apply(this, [f]);
var newfont = this.getFont();
if (newfont !== oldfont  && (oldfont == null  || !oldfont.equals$O(newfont) ) ) {
this.invalidateTree();
}});

Clazz.newMeth(C$, 'getPreferredSize', function () {
return this.preferredSize();
});

Clazz.newMeth(C$, 'preferredSize', function () {
var dim = this.prefSize;
if (dim == null  || !(this.isPreferredSizeSet() || this.isValid() ) ) {
{
this.prefSize = (this.layoutMgr != null ) ? this.layoutMgr.preferredLayoutSize$java_awt_Container(this) : this.prefSizeComp();
dim = this.prefSize;
}}return (dim == null  ? null : Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_Dimension,[dim]));
});

Clazz.newMeth(C$, 'getMinimumSize', function () {
var dim = this.minSize;
if (dim == null  || !(this.isMinimumSizeSet() || this.isValid() ) ) {
{
this.minSize = (this.layoutMgr != null ) ? this.layoutMgr.minimumLayoutSize$java_awt_Container(this) : this.minimumSize();
dim = this.minSize;
}}if (dim != null ) {
return Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_Dimension,[dim]);
} else {
return dim;
}});

Clazz.newMeth(C$, 'getMaximumSize', function () {
var dim = this.maxSize;
if (dim == null  || !(this.isMaximumSizeSet() || this.isValid() ) ) {
{
if (Clazz.instanceOf(this.layoutMgr, "java.awt.LayoutManager2")) {
var lm = this.layoutMgr;
this.maxSize = lm.maximumLayoutSize$java_awt_Container(this);
} else {
this.maxSize = this.getMaxSizeComp();
}dim = this.maxSize;
}}if (dim != null ) {
return Clazz.new_((I$[6]||$incl$(6)).c$$java_awt_Dimension,[dim]);
} else {
return dim;
}});

Clazz.newMeth(C$, 'getAlignmentX', function () {
var xAlign;
if (Clazz.instanceOf(this.layoutMgr, "java.awt.LayoutManager2")) {
{
var lm = this.layoutMgr;
xAlign = lm.getLayoutAlignmentX$java_awt_Container(this);
}} else {
xAlign = this.getAlignmentXComp();
}return xAlign;
});

Clazz.newMeth(C$, 'getAlignmentY', function () {
var yAlign;
if (Clazz.instanceOf(this.layoutMgr, "java.awt.LayoutManager2")) {
{
var lm = this.layoutMgr;
yAlign = lm.getLayoutAlignmentY$java_awt_Container(this);
}} else {
yAlign = this.getAlignmentYComp();
}return yAlign;
});

Clazz.newMeth(C$, 'paint$java_awt_Graphics', function (g) {
(I$[7]||$incl$(7)).getInstance().runComponents$java_awt_ComponentA$java_awt_Graphics$I(this.children.toArray$TTA(C$.EMPTY_ARRAY), g, 2);
});

Clazz.newMeth(C$, 'update$java_awt_Graphics', function (g) {
if (this.isShowing()) {
g.clearRect$I$I$I$I(0, 0, this.width, this.height);
this.paint$java_awt_Graphics(g);
}});

Clazz.newMeth(C$, 'paintComponents$java_awt_Graphics', function (g) {
if (this.isShowing()) {
(I$[8]||$incl$(8)).getInstance().runComponents$java_awt_ComponentA$java_awt_Graphics$I(this.children.toArray$TTA(C$.EMPTY_ARRAY), g, 4);
}});

Clazz.newMeth(C$, 'lightweightPaint$java_awt_Graphics', function (g) {
this.lwPaintComp$java_awt_Graphics(g);
this.paintHeavyweightComponents$java_awt_Graphics(g);
});

Clazz.newMeth(C$, 'paintHeavyweightComponents$java_awt_Graphics', function (g) {
if (this.isShowing()) {
(I$[9]||$incl$(9)).getInstance().runComponents$java_awt_ComponentA$java_awt_Graphics$I(this.children.toArray$TTA(C$.EMPTY_ARRAY), g, 3);
}});

Clazz.newMeth(C$, 'addContainerListener$java_awt_event_ContainerListener', function (l) {
if (l == null ) {
return;
}this.containerListener = (I$[10]||$incl$(10)).add$java_awt_event_ContainerListener$java_awt_event_ContainerListener(this.containerListener, l);
this.newEventsOnly = true;
});

Clazz.newMeth(C$, 'removeContainerListener$java_awt_event_ContainerListener', function (l) {
if (l == null ) {
return;
}this.containerListener = (I$[10]||$incl$(10)).remove$java_awt_event_ContainerListener$java_awt_event_ContainerListener(this.containerListener, l);
});

Clazz.newMeth(C$, 'getContainerListeners', function () {
return (this.getListeners$Class(Clazz.getClass((I$[11]||$incl$(11)),['componentAdded$java_awt_event_ContainerEvent','componentRemoved$java_awt_event_ContainerEvent'])));
});

Clazz.newMeth(C$, 'getListeners$Class', function (listenerType) {
var l = null;
if (listenerType === Clazz.getClass((I$[11]||$incl$(11)),['componentAdded$java_awt_event_ContainerEvent','componentRemoved$java_awt_event_ContainerEvent']) ) {
l = this.containerListener;
} else {
return this.getListenersComp$Class(listenerType);
}return (I$[10]||$incl$(10)).getListeners$java_util_EventListener$Class(l, listenerType);
});

Clazz.newMeth(C$, 'eventEnabled$java_awt_AWTEvent', function (e) {
var id = e.getID();
if (id == 300 || id == 301 ) {
if ((this.eventMask & 2) != 0 || this.containerListener != null  ) {
return true;
}return false;
}return this.eventTypeEnabled$I(e.id);
});

Clazz.newMeth(C$, 'processEvent$java_awt_AWTEvent', function (e) {
this.processEventCont$java_awt_AWTEvent(e);
});

Clazz.newMeth(C$, 'processEventCont$java_awt_AWTEvent', function (e) {
if (Clazz.instanceOf(e, "java.awt.event.ContainerEvent")) {
this.processContainerEvent$java_awt_event_ContainerEvent(e);
return;
}this.processEventComp$java_awt_AWTEvent(e);
});

Clazz.newMeth(C$, 'processContainerEvent$java_awt_event_ContainerEvent', function (e) {
var listener = this.containerListener;
if (listener != null ) {
switch (e.getID()) {
case 300:
listener.componentAdded$java_awt_event_ContainerEvent(e);
break;
case 301:
listener.componentRemoved$java_awt_event_ContainerEvent(e);
break;
}
}});

Clazz.newMeth(C$, 'dispatchEventImpl$java_awt_AWTEvent', function (e) {
if ((this.dispatcher != null ) && this.dispatcher.dispatchEvent$java_awt_AWTEvent(e) ) {
e.consume();
if (this.peer != null ) {
this.peer.handleEvent$java_awt_AWTEvent(e);
}return;
}this.dispatchEventImplComp$java_awt_AWTEvent(e);
{
switch (e.getID()) {
case 101:
break;
case 100:
break;
default:
break;
}
}});

Clazz.newMeth(C$, 'dispatchEventToSelf$java_awt_AWTEvent', function (e) {
this.dispatchEventImplComp$java_awt_AWTEvent(e);
});

Clazz.newMeth(C$, 'getMouseEventTarget$I$I$Z', function (x, y, includeSelf) {
return p$.getMouseEventTarget$I$I$Z$java_awt_Container_EventTargetFilter$Z.apply(this, [x, y, includeSelf, (I$[12]||$incl$(12)).FILTER, false]);
});

Clazz.newMeth(C$, 'getMouseEventTarget$I$I$Z$java_awt_Container_EventTargetFilter$Z', function (x, y, includeSelf, filter, searchHeavyweights) {
var comp = null;
if (comp == null  || comp === this  ) {
comp = p$.getMouseEventTargetImpl$I$I$Z$java_awt_Container_EventTargetFilter$Z$Z.apply(this, [x, y, includeSelf, filter, false, searchHeavyweights]);
}return comp;
});

Clazz.newMeth(C$, 'getMouseEventTargetImpl$I$I$Z$java_awt_Container_EventTargetFilter$Z$Z', function (x, y, includeSelf, filter, searchHeavyweightChildren, searchHeavyweightDescendants) {
{
for (var i = 0; i < this.children.size(); i++) {
var comp = this.children.get$I(i);
if (comp != null  && comp.visible  && ((!searchHeavyweightChildren && Clazz.instanceOf(comp.peer, "java.awt.peer.LightweightPeer") ) || (searchHeavyweightChildren && !(Clazz.instanceOf(comp.peer, "java.awt.peer.LightweightPeer")) ) )  && comp.contains$I$I(x - comp.x, y - comp.y) ) {
if (Clazz.instanceOf(comp, "java.awt.Container")) {
var child = comp;
var deeper = child.getMouseEventTarget$I$I$Z$java_awt_Container_EventTargetFilter$Z(x - child.x, y - child.y, includeSelf, filter, searchHeavyweightDescendants);
if (deeper != null ) {
return deeper;
}} else {
if (filter.accept$java_awt_Component(comp)) {
return comp;
}}}}
var isPeerOK;
var isMouseOverMe;
isPeerOK = includeSelf;
isMouseOverMe = this.contains$I$I(x, y);
if (isMouseOverMe && isPeerOK && filter.accept$java_awt_Component(this)  ) {
return this;
}return null;
}});

Clazz.newMeth(C$, 'proxyEnableEvents$J', function (events) {
if (this.parent != null ) {
this.parent.proxyEnableEvents$J(events);
}if (this.dispatcher != null ) {
this.dispatcher.enableEvents$J(events);
}});

Clazz.newMeth(C$, 'deliverEvent$java_awt_Event', function (e) {
var comp = this.getComponentAt$I$I(e.x, e.y);
if ((comp != null ) && (comp !== this ) ) {
e.translate$I$I(-comp.x, -comp.y);
comp.deliverEvent$java_awt_Event(e);
} else {
this.postEvent$java_awt_Event(e);
}});

Clazz.newMeth(C$, 'getComponentAt$I$I', function (x, y) {
return this.locate$I$I(x, y);
});

Clazz.newMeth(C$, 'locate$I$I', function (x, y) {
return this;
});

Clazz.newMeth(C$, 'getComponentAt$java_awt_Point', function (p) {
return this.getComponentAt$I$I(p.x, p.y);
});

Clazz.newMeth(C$, 'getMousePosition$Z', function (allowChildren) {
return null;
});

Clazz.newMeth(C$, 'isSameOrAncestorOf$java_awt_Component$Z', function (comp, allowChildren) {
return this === comp  || (allowChildren && p$.isParentOf$java_awt_Component.apply(this, [comp]) ) ;
});

Clazz.newMeth(C$, 'findComponentAt$I$I', function (x, y) {
{
return this.findComponentAt$I$I$Z(x, y, true);
}});

Clazz.newMeth(C$, 'findComponentAt$I$I$Z', function (x, y, ignoreEnabled) {
return null;
});

Clazz.newMeth(C$, 'findComponentAt$java_awt_Point', function (p) {
return this.findComponentAt$I$I(p.x, p.y);
});

Clazz.newMeth(C$, 'addNotify', function () {
{
this.addNotifyComp();
if (!(Clazz.instanceOf(this.peer, "java.awt.peer.LightweightPeer"))) {
this.setDispatcher();
}for (var i = 0; i < this.children.size(); i++) {
this.children.get$I(i).addNotify();
}
}});

Clazz.newMeth(C$, 'setDispatcher', function () {
this.dispatcher = Clazz.new_((I$[13]||$incl$(13)).c$$java_awt_Container,[this]);
});

Clazz.newMeth(C$, 'removeNotify', function () {
for (var i = this.children.size(); --i >= 0; ) {
var comp = this.children.get$I(i);
if (comp != null ) {
comp.setAutoFocusTransferOnDisposal$Z(false);
comp.removeNotify();
comp.setAutoFocusTransferOnDisposal$Z(true);
}}
if (this.dispatcher != null ) {
this.dispatcher.dispose();
this.dispatcher = null;
}this.removeNotifyComp();
});

Clazz.newMeth(C$, 'isAncestorOf$java_awt_Component', function (c) {
var p;
if (c == null  || ((p = c.getParent()) == null ) ) {
return false;
}while (p != null ){
if (p === this ) {
return true;
}p = p.getParent();
}
return false;
});

Clazz.newMeth(C$, 'paramString', function () {
var str = this.paramStringComp();
var layoutMgr = this.layoutMgr;
if (layoutMgr != null ) {
str += ",layout=" + layoutMgr.getClass().getName();
}return str;
});

Clazz.newMeth(C$, 'setFocusTraversalKeys$I$java_util_Set', function (id, keystrokes) {
});

Clazz.newMeth(C$, 'getFocusTraversalKeys$I', function (id) {
return null;
});

Clazz.newMeth(C$, 'areFocusTraversalKeysSet$I', function (id) {
return false;
});

Clazz.newMeth(C$, 'isFocusCycleRoot$java_awt_Container', function (container) {
if (this.isFocusCycleRoot() && container === this  ) {
return true;
} else {
return this.isFocusCycleRootComp$java_awt_Container(container);
}});

Clazz.newMeth(C$, 'containsFocus', function () {
return false;
});

Clazz.newMeth(C$, 'isParentOf$java_awt_Component', function (comp) {
{
while (comp != null  && comp !== this   && !(Clazz.instanceOf(comp, "java.awt.Window")) ){
comp = comp.getParent();
}
return (comp === this );
}});

Clazz.newMeth(C$, 'clearMostRecentFocusOwnerOnHide', function () {
});

Clazz.newMeth(C$, 'clearCurrentFocusCycleRootOnHide', function () {
});

Clazz.newMeth(C$, 'getTraversalRoot', function () {
return null;
});

Clazz.newMeth(C$, 'isFocusCycleRoot', function () {
return this.focusCycleRoot;
});

Clazz.newMeth(C$, 'setFocusTraversalPolicyProvider$Z', function (provider) {
var oldProvider;
{
oldProvider = this.focusTraversalPolicyProvider;
this.focusTraversalPolicyProvider = provider;
}this.firePropertyChange$S$Z$Z("focusTraversalPolicyProvider", oldProvider, provider);
});

Clazz.newMeth(C$, 'isFocusTraversalPolicyProvider', function () {
return this.focusTraversalPolicyProvider;
});

Clazz.newMeth(C$, 'transferFocusDownCycle', function () {
});

Clazz.newMeth(C$, 'preProcessKeyEvent$java_awt_event_KeyEvent', function (e) {
var parent = this.parent;
if (parent != null ) {
parent.preProcessKeyEvent$java_awt_event_KeyEvent(e);
}});

Clazz.newMeth(C$, 'postProcessKeyEvent$java_awt_event_KeyEvent', function (e) {
var parent = this.parent;
if (parent != null ) {
parent.postProcessKeyEvent$java_awt_event_KeyEvent(e);
}});

Clazz.newMeth(C$, 'postsOldMouseEvents', function () {
return true;
});

Clazz.newMeth(C$, 'applyComponentOrientation$java_awt_ComponentOrientation', function (o) {
this.applyCompOrientComp$java_awt_ComponentOrientation(o);
{
for (var i = 0; i < this.children.size(); i++) {
var comp = this.children.get$I(i);
comp.applyComponentOrientation$java_awt_ComponentOrientation(o);
}
}});

Clazz.newMeth(C$, 'addPropertyChangeListener$java_beans_PropertyChangeListener', function (listener) {
this.addPropChangeListenerComp$java_beans_PropertyChangeListener(listener);
});

Clazz.newMeth(C$, 'addPropertyChangeListener$S$java_beans_PropertyChangeListener', function (propertyName, listener) {
this.addPropChangeListComp$S$java_beans_PropertyChangeListener(propertyName, listener);
});

Clazz.newMeth(C$, 'increaseComponentCount$java_awt_Component', function (c) {
if (!c.isDisplayable()) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Peer does not exist while invoking the increaseComponentCount() method"]);
}var addHW = 0;
var addLW = 0;
if (Clazz.instanceOf(c, "java.awt.Container")) {
addLW = (c).numOfLWComponents;
addHW = (c).numOfHWComponents;
}if (c.isLightweight()) {
addLW++;
} else {
addHW++;
}for (var cont = this; cont != null ; cont = cont.getContainer()) {
cont.numOfLWComponents = cont.numOfLWComponents+(addLW);
cont.numOfHWComponents = cont.numOfHWComponents+(addHW);
}
});

Clazz.newMeth(C$, 'decreaseComponentCount$java_awt_Component', function (c) {
if (!c.isDisplayable()) {
throw Clazz.new_(Clazz.load('java.lang.IllegalStateException').c$$S,["Peer does not exist while invoking the decreaseComponentCount() method"]);
}var subHW = 0;
var subLW = 0;
if (Clazz.instanceOf(c, "java.awt.Container")) {
subLW = (c).numOfLWComponents;
subHW = (c).numOfHWComponents;
}if (c.isLightweight()) {
subLW++;
} else {
subHW++;
}for (var cont = this; cont != null ; cont = cont.getContainer()) {
cont.numOfLWComponents = cont.numOfLWComponents-(subLW);
cont.numOfHWComponents = cont.numOfHWComponents-(subHW);
}
});
;
(function(){var C$=Clazz.newInterface(P$.Container, "EventTargetFilter", function(){
});
})()
;
(function(){var C$=Clazz.newClass(P$.Container, "MouseEventTargetFilter", function(){
Clazz.newInstance(this, arguments[0],false,C$);
}, null, [['java.awt.Container','java.awt.Container.EventTargetFilter']]);
C$.FILTER = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.FILTER = Clazz.new_(C$);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'accept$java_awt_Component', function (comp) {
return (comp.eventMask & 32) != 0 || (comp.eventMask & 16) != 0  || (comp.eventMask & 131072) != 0  || comp.mouseListener != null   || comp.mouseMotionListener != null   || comp.mouseWheelListener != null  ;
});
})()
})();
//Created 2018-02-06 08:58:08
