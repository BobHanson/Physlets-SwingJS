(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.util.LinkedList','sun.awt.AppContext','java.awt.EventQueue','Thread','java.awt.SequencedEvent$1','swingjs.JSUtil','java.awt.Toolkit','sun.awt.SunToolkit','java.awt.SentEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "SequencedEvent", null, 'java.awt.AWTEvent', 'java.awt.ActiveEvent');
C$.list = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.list = Clazz.new_((I$[1]||$incl$(1)));
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.nested = null;
this.appContext = null;
this.disposed = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_AWTEvent', function (nested) {
C$.superclazz.c$$O$I.apply(this, [nested.getSource(), 1006]);
C$.$init$.apply(this);
this.nested = nested;
{
C$.list.add$TE(this);
}}, 1);

Clazz.newMeth(C$, 'dispatch', function () {
try {
this.appContext = (I$[2]||$incl$(2)).getAppContext();
if (C$.getFirst() !== this ) {
if ((I$[3]||$incl$(3)).isDispatchThread()) {
var edt = (I$[4]||$incl$(4)).currentThread();
edt.pumpEvents$I$java_awt_Conditional(1007, ((
(function(){var C$=Clazz.newClass(P$, "SequencedEvent$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'java.awt.Conditional', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'evaluate', function () {
return !this.b$['java.awt.SequencedEvent'].isFirstOrDisposed();
});
})()
), Clazz.new_((I$[5]||$incl$(5)).$init$, [this, null])));
} else {
while (!this.isFirstOrDisposed()){
{
try {
(I$[6]||$incl$(6)).warn$S("Cannot wait in SequenceEvent");
Clazz.getClass(C$).wait$J(1000);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.InterruptedException")){
break;
} else {
throw e;
}
}
}}
}}if (!this.disposed) {
(I$[7]||$incl$(7)).getEventQueue().dispatchEvent$java_awt_AWTEvent(this.nested);
}} finally {
this.dispose();
}
});

Clazz.newMeth(C$, 'isOwnerAppContextDisposed$java_awt_SequencedEvent', function (se) {
if (se != null ) {
var target = se.nested.getSource();
if (Clazz.instanceOf(target, "java.awt.Component")) {
return (target).appContext.isDisposed();
}}return false;
}, 1);

Clazz.newMeth(C$, 'isFirstOrDisposed', function () {
if (this.disposed) {
return true;
}return this === C$.getFirstWithContext()  || this.disposed ;
});

Clazz.newMeth(C$, 'getFirst', function () {
return C$.list.getFirst();
}, 1);

Clazz.newMeth(C$, 'getFirstWithContext', function () {
var first = C$.getFirst();
while (C$.isOwnerAppContextDisposed$java_awt_SequencedEvent(first)){
first.dispose();
first = C$.getFirst();
}
return first;
}, 1);

Clazz.newMeth(C$, 'dispose', function () {
{
if (this.disposed) {
return;
}this.disposed = true;
}if (this.appContext != null ) {
(I$[8]||$incl$(8)).postEvent$sun_awt_AppContext$java_awt_AWTEvent(this.appContext, Clazz.new_((I$[9]||$incl$(9))));
}var next = null;
{
Clazz.getClass(C$).notifyAll();
if (C$.list.getFirst() === this ) {
C$.list.removeFirst();
if (!C$.list.isEmpty()) {
next = C$.list.getFirst();
}} else {
C$.list.remove$O(this);
}}if (next != null  && next.appContext != null  ) {
(I$[8]||$incl$(8)).postEvent$sun_awt_AppContext$java_awt_AWTEvent(next.appContext, Clazz.new_((I$[9]||$incl$(9))));
}});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:14
