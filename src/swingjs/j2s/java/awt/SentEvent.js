(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.awt.Toolkit','sun.awt.SunToolkit']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SentEvent", null, 'java.awt.AWTEvent', 'java.awt.ActiveEvent');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.$dispatched = false;
this.nested = null;
this.toNotify = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$java_awt_AWTEvent.apply(this, [null]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_AWTEvent', function (nested) {
C$.c$$java_awt_AWTEvent$sun_awt_AppContext.apply(this, [nested, null]);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_AWTEvent$sun_awt_AppContext', function (nested, toNotify) {
C$.superclazz.c$$O$I.apply(this, [(nested != null ) ? nested.getSource() : (I$[1]||$incl$(1)).getDefaultToolkit(), 1007]);
C$.$init$.apply(this);
this.nested = nested;
this.toNotify = toNotify;
}, 1);

Clazz.newMeth(C$, 'dispatch', function () {
try {
if (this.nested != null ) {
(I$[1]||$incl$(1)).getEventQueue().dispatchEvent$java_awt_AWTEvent(this.nested);
}} finally {
this.$dispatched = true;
if (this.toNotify != null ) {
(I$[2]||$incl$(2)).postEvent$sun_awt_AppContext$java_awt_AWTEvent(this.toNotify, Clazz.new_(C$));
}{
this.notifyAll();
}}
});

Clazz.newMeth(C$, 'dispose', function () {
this.$dispatched = true;
if (this.toNotify != null ) {
(I$[2]||$incl$(2)).postEvent$sun_awt_AppContext$java_awt_AWTEvent(this.toNotify, Clazz.new_(C$));
}{
this.notifyAll();
}});
})();
//Created 2018-05-15 01:01:53
