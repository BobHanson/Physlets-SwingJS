(function(){var P$=Clazz.newPackage("java.awt.event"),I$=[['sun.awt.SunToolkit','sun.awt.AppContext']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "WindowEvent", null, 'java.awt.event.ComponentEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.opposite = null;
this.oldState = 0;
this.newState = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Window$I$java_awt_Window$I$I', function (source, id, opposite, oldState, newState) {
C$.superclazz.c$$java_awt_Component$I.apply(this, [source, id]);
C$.$init$.apply(this);
this.opposite = opposite;
this.oldState = oldState;
this.newState = newState;
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Window$I$java_awt_Window', function (source, id, opposite) {
C$.c$$java_awt_Window$I$java_awt_Window$I$I.apply(this, [source, id, opposite, 0, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Window$I$I$I', function (source, id, oldState, newState) {
C$.c$$java_awt_Window$I$java_awt_Window$I$I.apply(this, [source, id, null, oldState, newState]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Window$I', function (source, id) {
C$.c$$java_awt_Window$I$java_awt_Window$I$I.apply(this, [source, id, null, 0, 0]);
}, 1);

Clazz.newMeth(C$, 'getWindow', function () {
return (Clazz.instanceOf(this.source, "java.awt.Window")) ? this.source : null;
});

Clazz.newMeth(C$, 'getOppositeWindow', function () {
if (this.opposite == null ) {
return null;
}return ((I$[1]||$incl$(1)).targetToAppContext$O(this.opposite) === (I$[2]||$incl$(2)).getAppContext() ) ? this.opposite : null;
});

Clazz.newMeth(C$, 'getOldState', function () {
return this.oldState;
});

Clazz.newMeth(C$, 'getNewState', function () {
return this.newState;
});

Clazz.newMeth(C$, 'paramString', function () {
var typeStr;
switch (this.id) {
case 200:
typeStr = "WINDOW_OPENED";
break;
case 201:
typeStr = "WINDOW_CLOSING";
break;
case 202:
typeStr = "WINDOW_CLOSED";
break;
case 203:
typeStr = "WINDOW_ICONIFIED";
break;
case 204:
typeStr = "WINDOW_DEICONIFIED";
break;
case 205:
typeStr = "WINDOW_ACTIVATED";
break;
case 206:
typeStr = "WINDOW_DEACTIVATED";
break;
case 207:
typeStr = "WINDOW_GAINED_FOCUS";
break;
case 208:
typeStr = "WINDOW_LOST_FOCUS";
break;
case 209:
typeStr = "WINDOW_STATE_CHANGED";
break;
default:
typeStr = "unknown type";
}
typeStr += ",opposite=" + this.getOppositeWindow() + ",oldState=" + this.oldState + ",newState=" + this.newState ;
return typeStr;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:56
