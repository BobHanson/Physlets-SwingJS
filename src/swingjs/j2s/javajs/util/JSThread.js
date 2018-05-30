(function(){var P$=Clazz.newPackage("javajs.util"),I$=[['javajs.util.JSThread$1']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "JSThread", null, 'Thread', 'javajs.api.JSFunction');
C$.threadCount = 0;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.threadCount = 0;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.isJS = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$ThreadGroup$S.apply(this, [null, "JSThread-" + (++C$.threadCount)]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.c$$ThreadGroup$S.apply(this, [null, name]);
}, 1);

Clazz.newMeth(C$, 'c$$ThreadGroup$S', function (group, name) {
C$.superclazz.c$$ThreadGroup$S.apply(this, [group, name]);
C$.$init$.apply(this);
{
this.isJS = true;
}
}, 1);

Clazz.newMeth(C$, 'run', function () {
this.run1$I(0);
});

Clazz.newMeth(C$, 'start', function () {
{
Clazz.load("swingjs.JSToolkit").dispatch$O$I$I(this, 1, 0);
}
});

Clazz.newMeth(C$, 'run1$I', function (state) {
var executeFinally = true;
try {
while (!Thread.interrupted()){
switch (state) {
case 0:
if (!this.myInit()) return;
state=1;
continue;
case 1:
if (!this.isLooping()) {
state=2;
continue;
}if (this.myLoop() && this.sleepAndReturn$I$I(this.getDelayMillis(), state) ) {
executeFinally=false;
return;
}continue;
case 2:
this.whenDone();
return;
}
}
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.Exception")){
this.onException$Exception(e);
state=2;
} else {
throw e;
}
} finally {
if (executeFinally) this.doFinally();
}
});

Clazz.newMeth(C$, 'sleepAndReturn$I$I', function (delay, state) {
if (!this.isJS) {
Thread.sleep$J(delay);
return false;
}var me = this;
var r = ((
(function(){var C$=Clazz.newClass(P$, "JSThread$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, null, 'Runnable', 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'run', function () {
this.$finals.me.run1$I(this.$finals.state);
});
})()
), Clazz.new_((I$[1]||$incl$(1)).$init$, [this, {me: me, state: state}]));
{
setTimeout( function() { java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent$java_awt_AWTEvent( Clazz.new_(java.awt.event.InvocationEvent.c$$O$Runnable,[me, r]))}, delay);
}
return true;
});
})();
//Created 2018-05-24 08:45:56
