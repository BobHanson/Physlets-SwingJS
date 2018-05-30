(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.awt.Component','java.awt.Insets','javajs.util.Lst','java.awt.Toolkit','java.awt.event.ContainerEvent','java.awt.Dimension',['java.awt.GraphicsCallback','.PaintCallback'],['java.awt.GraphicsCallback','.PaintAllCallback'],['java.awt.GraphicsCallback','.PaintHeavyweightComponentsCallback'],'java.awt.AWTEventMulticaster','java.awt.event.ContainerListener',['java.awt.Container','.MouseEventTargetFilter'],'java.awt.LightweightDispatcher','java.awt.event.MouseEvent','java.awt.event.MouseWheelEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "LightweightDispatcher", null, null, 'java.awt.event.AWTEventListener');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.nativeContainer = null;
this.mouseEventTarget = null;
this.targetLastEntered = null;
this.isMouseInNativeContainer = false;
this.eventMask = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.isMouseInNativeContainer = false;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Container', function (nativeContainer) {
C$.$init$.apply(this);
this.nativeContainer=nativeContainer;
this.mouseEventTarget=null;
this.eventMask=0;
}, 1);

Clazz.newMeth(C$, 'dispose', function () {
p$.stopListeningForOtherDrags.apply(this, []);
this.mouseEventTarget=null;
});

Clazz.newMeth(C$, 'enableEvents$J', function (events) {
this.eventMask|=events;
});

Clazz.newMeth(C$, 'dispatchEvent$java_awt_AWTEvent', function (e) {
var ret = false;
if (Clazz.instanceOf(e, "java.awt.event.MouseEvent") && (this.eventMask & 131120) != 0 ) {
var me = e;
ret=p$.processMouseEvent$java_awt_event_MouseEvent.apply(this, [me]);
}return ret;
});

Clazz.newMeth(C$, 'isMouseGrab$java_awt_event_MouseEvent', function (e) {
var modifiers = e.getModifiersEx();
if (e.getID() == 501 || e.getID() == 502 ) {
switch (e.getButton()) {
case 1:
modifiers^=1024;
break;
case 2:
modifiers^=2048;
break;
case 3:
modifiers^=4096;
break;
}
}return ((modifiers & 7168) != 0);
});

Clazz.newMeth(C$, 'processMouseEvent$java_awt_event_MouseEvent', function (e) {
var id = e.getID();
var mouseOver = null;
{
mouseOver = e.bdata.jqevent && e.bdata.jqevent.target["data-component"];
}
if (mouseOver == null ) mouseOver=this.nativeContainer.getMouseEventTarget$I$I$Z(e.getX(), e.getY(), true);
p$.trackMouseEnterExit$java_awt_Component$java_awt_event_MouseEvent.apply(this, [mouseOver, e]);
if (!p$.isMouseGrab$java_awt_event_MouseEvent.apply(this, [e]) && id != 500 ) {
this.mouseEventTarget=(mouseOver !== this.nativeContainer ) ? mouseOver : null;
}if (this.mouseEventTarget != null ) {
switch (id) {
case 504:
case 505:
break;
case 501:
this.retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent(this.mouseEventTarget, id, e);
break;
case 502:
this.retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent(this.mouseEventTarget, id, e);
break;
case 500:
if (mouseOver === this.mouseEventTarget ) {
this.retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent(mouseOver, id, e);
}break;
case 503:
this.retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent(this.mouseEventTarget, id, e);
break;
case 506:
if (p$.isMouseGrab$java_awt_event_MouseEvent.apply(this, [e])) {
this.retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent(this.mouseEventTarget, id, e);
}break;
case 507:
this.retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent(mouseOver, id, e);
break;
}
e.consume();
}return e.isConsumed();
});

Clazz.newMeth(C$, 'trackMouseEnterExit$java_awt_Component$java_awt_event_MouseEvent', function (targetOver, e) {
var targetEnter = null;
var id = e.getID();
if (id != 505 && id != 506  && id != 1500  && this.isMouseInNativeContainer == false  ) {
this.isMouseInNativeContainer=true;
p$.startListeningForOtherDrags.apply(this, []);
} else if (id == 505) {
this.isMouseInNativeContainer=false;
p$.stopListeningForOtherDrags.apply(this, []);
}if (this.isMouseInNativeContainer) {
targetEnter=targetOver;
}if (this.targetLastEntered === targetEnter ) {
return;
}if (this.targetLastEntered != null ) {
this.retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent(this.targetLastEntered, 505, e);
}if (id == 505) {
e.consume();
}if (targetEnter != null ) {
this.retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent(targetEnter, 504, e);
}if (id == 504) {
e.consume();
}this.targetLastEntered=targetEnter;
});

Clazz.newMeth(C$, 'startListeningForOtherDrags', function () {
});

Clazz.newMeth(C$, 'stopListeningForOtherDrags', function () {
});

Clazz.newMeth(C$, 'eventDispatched$java_awt_AWTEvent', function (e) {
var isForeignDrag = (Clazz.instanceOf(e, "java.awt.event.MouseEvent")) && (e.id == 506) && (e.getSource() !== this.nativeContainer )  ;
if (!isForeignDrag) {
return;
}var srcEvent = e;
var me;
{
var srcComponent = srcEvent.getComponent();
if (!srcComponent.isShowing()) {
return;
}var c = this.nativeContainer;
while ((c != null ) && !(Clazz.instanceOf(c, "java.awt.Window")) ){
c=c.getParent_NoClientCode();
}
if ((c == null ) || (c).isModalBlocked() ) {
return;
}me=Clazz.new_((I$[14]||$incl$(14)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[this.nativeContainer, 1500, srcEvent.getWhen(), srcEvent.getModifiersEx() | srcEvent.getModifiers(), srcEvent.getX(), srcEvent.getY(), srcEvent.getXOnScreen(), srcEvent.getYOnScreen(), srcEvent.getClickCount(), srcEvent.isPopupTrigger(), srcEvent.getButton()]);
(srcEvent).copyPrivateDataInto$java_awt_AWTEvent(me);
}var targetOver = this.nativeContainer.getMouseEventTarget$I$I$Z(me.getX(), me.getY(), true);
p$.trackMouseEnterExit$java_awt_Component$java_awt_event_MouseEvent.apply(this, [targetOver, me]);
});

Clazz.newMeth(C$, 'retargetMouseEvent$java_awt_Component$I$java_awt_event_MouseEvent', function (target, id, e) {
if (target == null ) {
return;
}var x = e.getX();
var y = e.getY();
var component;
for (component=target; component != null  && component !== this.nativeContainer  ; component=component.getParent()) {
x-=component.x;
y-=component.y;
if ((component).uiClassID == "PopupMenuUI") break;
}
var retargeted;
if (component != null ) {
if (id == 507) {
retargeted=Clazz.new_((I$[15]||$incl$(15)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I$I$I,[target, id, e.getWhen(), e.getModifiersEx() | e.getModifiers(), x, y, e.getXOnScreen(), e.getYOnScreen(), e.getClickCount(), e.isPopupTrigger(), (e).getScrollType(), (e).getScrollAmount(), (e).getWheelRotation()]);
} else {
retargeted=Clazz.new_((I$[14]||$incl$(14)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[target, id, e.getWhen(), e.getModifiersEx() | e.getModifiers(), x, y, e.getXOnScreen(), e.getYOnScreen(), e.getClickCount(), e.isPopupTrigger(), e.getButton()]);
}(e).copyPrivateDataInto$java_awt_AWTEvent(retargeted);
if (target === this.nativeContainer ) {
(target).dispatchEventToSelf$java_awt_AWTEvent(retargeted);
} else {
if (this.nativeContainer.modalComp != null ) {
if ((this.nativeContainer.modalComp).isAncestorOf$java_awt_Component(target)) {
target.dispatchEvent$java_awt_AWTEvent(retargeted);
} else {
e.consume();
}} else {
target.dispatchEvent$java_awt_AWTEvent(retargeted);
}}}});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:07
