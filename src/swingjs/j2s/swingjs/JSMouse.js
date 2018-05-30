(function(){var P$=Clazz.newPackage("swingjs"),I$=[['swingjs.JSUtil','swingjs.JSToolkit','java.awt.event.MouseWheelEvent','java.awt.event.MouseEvent','java.awt.Toolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSMouse");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.viewer = null;
this.jqevent = null;
this.mouse2 = null;
this.isCtrlShiftMouseDown = false;
this.wheeling = false;
this.xWhenPressed = 0;
this.yWhenPressed = 0;
this.modifiersWhenPressed10 = 0;
this.lasttime = 0;
this.lastx = 0;
this.lasty = 0;
this.clickCount = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$swingjs_JSFrameViewer', function (v) {
C$.$init$.apply(this);
this.viewer=v;
}, 1);

Clazz.newMeth(C$, 'processEvent$I$I$I$I$J$O$I', function (id, x, y, modifiers, time, jqevent, scroll) {
this.jqevent=jqevent;
if (id != 507 && id != 503 ) modifiers=C$.applyLeftMouse$I(modifiers);
switch (id) {
case 507:
this.wheeled$J$I$I$I$I(time, x, y, scroll, modifiers);
break;
case 501:
this.xWhenPressed=x;
this.yWhenPressed=y;
this.modifiersWhenPressed10=modifiers;
p$.pressed$J$I$I$I$Z.apply(this, [time, x, y, modifiers, false]);
break;
case 506:
p$.dragged$J$I$I$I.apply(this, [time, x, y, modifiers]);
break;
case 504:
p$.entry$J$I$I$Z.apply(this, [time, x, y, false]);
break;
case 505:
p$.entry$J$I$I$Z.apply(this, [time, x, y, true]);
break;
case 503:
p$.moved$J$I$I$I.apply(this, [time, x, y, modifiers]);
break;
case 502:
p$.released$J$I$I$I.apply(this, [time, x, y, modifiers]);
if (x == this.xWhenPressed && y == this.yWhenPressed  && modifiers == this.modifiersWhenPressed10 ) {
p$.clicked$J$I$I$I$I.apply(this, [time, x, y, modifiers, 1]);
}break;
default:
return false;
}
return true;
});

Clazz.newMeth(C$, 'processTwoPointGesture$FAAA', function (touches) {
p$.getMouse2.apply(this, []).processTwoPointGesture$FAAA(touches);
});

Clazz.newMeth(C$, 'getMouse2', function () {
return (this.mouse2 == null  ? (this.mouse2=(I$[1]||$incl$(1)).getInstance$S("swingjs.JSMouse2")).set$swingjs_JSMouse(this) : this.mouse2);
});

Clazz.newMeth(C$, 'translateXYBy$I$I', function (deltaX, deltaY) {
});

Clazz.newMeth(C$, 'mouseClicked$java_awt_event_MouseEvent', function (e) {
p$.clicked$J$I$I$I$I.apply(this, [e.getWhen(), e.getX(), e.getY(), e.getModifiers(), e.getClickCount()]);
});

Clazz.newMeth(C$, 'mouseEntered$java_awt_event_MouseEvent', function (e) {
p$.entry$J$I$I$Z.apply(this, [e.getWhen(), e.getX(), e.getY(), false]);
});

Clazz.newMeth(C$, 'mouseExited$java_awt_event_MouseEvent', function (e) {
p$.entry$J$I$I$Z.apply(this, [e.getWhen(), e.getX(), e.getY(), true]);
});

Clazz.newMeth(C$, 'mousePressed$java_awt_event_MouseEvent', function (e) {
p$.pressed$J$I$I$I$Z.apply(this, [e.getWhen(), e.getX(), e.getY(), e.getModifiers(), e.isPopupTrigger()]);
});

Clazz.newMeth(C$, 'mouseReleased$java_awt_event_MouseEvent', function (e) {
p$.released$J$I$I$I.apply(this, [e.getWhen(), e.getX(), e.getY(), e.getModifiers()]);
});

Clazz.newMeth(C$, 'mouseDragged$java_awt_event_MouseEvent', function (e) {
var modifiers = e.getModifiers();
if ((modifiers & 28) == 0) modifiers|=16;
p$.dragged$J$I$I$I.apply(this, [e.getWhen(), e.getX(), e.getY(), modifiers]);
});

Clazz.newMeth(C$, 'mouseMoved$java_awt_event_MouseEvent', function (e) {
p$.moved$J$I$I$I.apply(this, [e.getWhen(), e.getX(), e.getY(), e.getModifiers()]);
});

Clazz.newMeth(C$, 'mouseWheelMoved$java_awt_event_MouseWheelEvent', function (e) {
e.consume();
this.wheeled$J$I$I$I$I(e.getWhen(), 0, 0, e.getWheelRotation(), e.getModifiers());
});

Clazz.newMeth(C$, 'entry$J$I$I$Z', function (time, x, y, isExit) {
this.wheeling=false;
p$.mouseEnterExit$J$I$I$Z.apply(this, [time, x, y, isExit]);
});

Clazz.newMeth(C$, 'clicked$J$I$I$I$I', function (time, x, y, modifiers, clickCount) {
p$.mouseAction$I$J$I$I$I$I$I.apply(this, [500, time, x, y, 1, modifiers, 0]);
});

Clazz.newMeth(C$, 'moved$J$I$I$I', function (time, x, y, modifiers) {
if (this.isCtrlShiftMouseDown) p$.mouseAction$I$J$I$I$I$I$I.apply(this, [506, time, x, y, 0, C$.applyLeftMouse$I(modifiers), 0]);
 else p$.mouseAction$I$J$I$I$I$I$I.apply(this, [503, time, x, y, 0, modifiers, 0]);
});

Clazz.newMeth(C$, 'wheeled$J$I$I$I$I', function (time, x, y, rotation, modifiers) {
this.wheeling=true;
p$.mouseAction$I$J$I$I$I$I$I.apply(this, [507, time, x, y, 0, modifiers & -29 | 32, rotation]);
});

Clazz.newMeth(C$, 'pressed$J$I$I$I$Z', function (time, x, y, modifiers, isPopupTrigger) {
this.isCtrlShiftMouseDown=((modifiers & 3) == 3);
this.wheeling=false;
p$.mouseAction$I$J$I$I$I$I$I.apply(this, [501, time, x, y, 0, modifiers, 0]);
});

Clazz.newMeth(C$, 'released$J$I$I$I', function (time, x, y, modifiers) {
this.isCtrlShiftMouseDown=false;
this.wheeling=false;
p$.mouseAction$I$J$I$I$I$I$I.apply(this, [502, time, x, y, 0, modifiers, 0]);
});

Clazz.newMeth(C$, 'dragged$J$I$I$I', function (time, x, y, modifiers) {
if (this.wheeling) return;
if ((modifiers & 20) == 20) modifiers=modifiers & -5 | 2;
p$.mouseAction$I$J$I$I$I$I$I.apply(this, [506, time, x, y, 0, modifiers, 0]);
});

Clazz.newMeth(C$, 'applyLeftMouse$I', function (modifiers) {
return ((modifiers & 28) == 0) ? (modifiers | 16) : modifiers;
}, 1);

Clazz.newMeth(C$, 'getButton$I', function (modifiers) {
switch (modifiers & 28) {
case 16:
return 1;
case 8:
return 2;
case 4:
return 3;
default:
return 0;
}
});

Clazz.newMeth(C$, 'mouseEnterExit$J$I$I$Z', function (time, x, y, isExit) {
});

Clazz.newMeth(C$, 'mouseAction$I$J$I$I$I$I$I', function (id, time, x, y, xcount, modifiers, dy) {
var extended = modifiers & 16320;
var popupTrigger = (extended == 4096 || extended == 256  || (I$[2]||$incl$(2)).isMac && extended == 1152  );
var button = p$.getButton$I.apply(this, [modifiers]);
var count = p$.updateClickCount$I$J$I$I.apply(this, [id, time, x, y]);
var source = this.viewer.top;
var e;
if (id == 507) {
e=Clazz.new_((I$[3]||$incl$(3)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I$I$I,[source, id, time, modifiers, x, y, x, y, count, popupTrigger, 0, 1, dy]);
} else {
e=Clazz.new_((I$[4]||$incl$(4)).c$$java_awt_Component$I$J$I$I$I$I$I$I$Z$I,[source, id, time, modifiers, x, y, x, y, count, popupTrigger, button]);
}var bdata = Clazz.array(Byte.TYPE, [0]);
e.setBData$BA(bdata);
var jqevent = this.jqevent;
var c = null;

bdata.jqevent = jqevent;
c = jqevent.target["data-component"];
if (c == null ) {
(I$[5]||$incl$(5)).getDefaultToolkit().getSystemEventQueue().postEvent$java_awt_AWTEvent(e);
} else {
(e.getSource()).dispatchEvent$java_awt_AWTEvent(e);
}});

Clazz.newMeth(C$, 'updateClickCount$I$J$I$I', function (id, time, x, y) {
var reset = (time - this.lasttime > 500 || Math.abs(x - this.lastx) > 3  || Math.abs(y - this.lasty) > 3 );
this.lasttime=time;
this.lastx=x;
this.lasty=y;
var ret = this.clickCount;
switch (id) {
case 501:
ret=this.clickCount=(reset ? 1 : this.clickCount + 1);
break;
case 504:
case 505:
this.clickCount=0;
break;
case 503:
if (reset) this.clickCount=0;
break;
case 502:
case 506:
case -1:
break;
}
return ret;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:47:45
