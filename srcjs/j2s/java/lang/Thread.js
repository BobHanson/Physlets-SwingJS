(function(){var P$=java.lang,I$=[['java.lang.ThreadGroup','swingjs.JSToolkit','java.lang.ThreadDeath','java.lang.NoSuchMethodError','swingjs.JSUtil','java.util.HashMap',['java.lang.Thread','.State']]],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(java.lang, "Thread", function(){
Clazz.newInstance(this, arguments,0,C$);
}, null, 'Runnable');
C$.threadInitNumber = 0;
var p$=C$.prototype;
C$.threadSeqNumber = 0;
C$.thisThread = null;
C$.defaultUncaughtExceptionHandler = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.name = null;
this.priority = 0;
this.daemon = false;
this.target = null;
this.group = null;
this.tid = 0;
this.threadStatus = 0;
this.parkBlocker = null;
this.stopBeforeStart = false;
this.throwableFromStop = null;
this.me = null;
this.started = false;
this.uncaughtExceptionHandler = null;
this.$interrupted = false;
this.stopped = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.priority = 5;
this.daemon = false;
this.threadStatus = 0;
}, 1);

Clazz.newMeth(C$, 'nextThreadNum', function () {
return Thread.threadInitNumber++;
}, 1);

Clazz.newMeth(C$, 'nextThreadID', function () {
return ++Thread.threadSeqNumber;
}, 1);

Clazz.newMeth(C$, 'currentThread', function () {
{
if (java.lang.Thread.thisThread == "working") return null;
}
if (Thread.thisThread == null ) {
{
java.lang.Thread.thisThread = "working";
java.lang.Thread.thisThread = Clazz.new_(java.lang.Thread.c$$S, ["master"]);
var name = J2S._applets["master"]._id; var g = Clazz.new_(Clazz.load('swingjs.JSThreadGroup').c$$ThreadGroup$S, [null, name]); java.lang.Thread.thisThread = Clazz.new_(Clazz.load("javajs.util.JSThread").c$$ThreadGroup$S, [g, name]);
}
Thread.thisThread.setPriority$I(5);
}return Thread.thisThread;
}, 1);

Clazz.newMeth(C$, 'yield', function () {
}, 1);

Clazz.newMeth(C$, 'sleep$J', function (millis) {
debugger
}, 1);

Clazz.newMeth(C$, 'sleep$J$I', function (millis, nanos) {
if (millis < 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["timeout value is negative"]);
}if (nanos < 0 || nanos > 999999 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["nanosecond timeout value out of range"]);
}if (nanos >= 500000 || (nanos != 0 && millis == 0 ) ) {
millis++;
}Thread.sleep$J(millis);
}, 1);

Clazz.newMeth(C$, 'init$ThreadGroup$Runnable$S$J', function (g, target, name, stackSize) {
p$.init$ThreadGroup$Runnable$S$J$O.apply(this, [g, target, name, stackSize, null]);
});

Clazz.newMeth(C$, 'init$ThreadGroup$Runnable$S$J$O', function (g, target, name, stackSize, acc) {
var parent = (Thread.thisThread == null  ? null : Thread.thisThread);
if (g == null ) {
if (g == null  && parent != null   && !parent.equals$O("working") ) {
g = parent.getThreadGroup();
}}if (g == null ) {
g = this.newThreadGroup$ThreadGroup$S(null, name);
parent = this;
}g.checkAccess();
g.addUnstarted();
this.group = g;
this.priority = parent.getPriority();
this.name = name;
this.target = target;
this.setPriority$I(this.priority);
this.tid = Thread.nextThreadID();
this.me = this;
});

Clazz.newMeth(C$, 'newThreadGroup$ThreadGroup$S', function (group, name) {
return Clazz.new_((I$[1]||$incl$(1)).c$$ThreadGroup$S,[group, name]);
});

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
p$.init$ThreadGroup$Runnable$S$J.apply(this, [null, null, "Thread-" + Thread.nextThreadNum(), 0]);
}, 1);

Clazz.newMeth(C$, 'c$$Runnable', function (target) {
C$.$init$.apply(this);
p$.init$ThreadGroup$Runnable$S$J.apply(this, [null, target, "Thread-" + Thread.nextThreadNum(), 0]);
}, 1);

Clazz.newMeth(C$, 'c$$ThreadGroup$Runnable', function (group, target) {
C$.$init$.apply(this);
p$.init$ThreadGroup$Runnable$S$J.apply(this, [group, target, "Thread-" + Thread.nextThreadNum(), 0]);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (name) {
C$.$init$.apply(this);
p$.init$ThreadGroup$Runnable$S$J.apply(this, [null, null, name, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$ThreadGroup$S', function (group, name) {
C$.$init$.apply(this);
p$.init$ThreadGroup$Runnable$S$J.apply(this, [group, null, name, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$Runnable$S', function (target, name) {
C$.$init$.apply(this);
p$.init$ThreadGroup$Runnable$S$J.apply(this, [null, target, name, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$ThreadGroup$Runnable$S', function (group, target, name) {
C$.$init$.apply(this);
p$.init$ThreadGroup$Runnable$S$J.apply(this, [group, target, name, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$ThreadGroup$Runnable$S$J', function (group, target, name, stackSize) {
C$.$init$.apply(this);
p$.init$ThreadGroup$Runnable$S$J.apply(this, [group, target, name, stackSize]);
}, 1);

Clazz.newMeth(C$, 'start', function () {
if (this.threadStatus != 0 || this !== this.me  ) throw Clazz.new_(Clazz.load('java.lang.IllegalThreadStateException'));
this.group.add$Thread(this);
p$.start0.apply(this, []);
if (this.stopBeforeStart) {
p$.stop0$O.apply(this, [this.throwableFromStop]);
}});

Clazz.newMeth(C$, 'start0', function () {
this.started = true;
(I$[2]||$incl$(2)).dispatch$O$I$I(this, 0, 0);
});

Clazz.newMeth(C$, 'run', function () {
if (this.target != null ) {
this.target.run();
}});

Clazz.newMeth(C$, 'stop', function () {
if ((this.threadStatus != 0) && !this.isAlive() ) {
return;
}p$.stop1$Throwable.apply(this, [Clazz.new_((I$[3]||$incl$(3)))]);
});

Clazz.newMeth(C$, 'stop$Throwable', function (obj) {
p$.stop1$Throwable.apply(this, [obj]);
});

Clazz.newMeth(C$, 'stop1$Throwable', function (th) {
if (this.threadStatus != 0) {
this.resume();
p$.stop0$O.apply(this, [th]);
} else {
if (th == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.stopBeforeStart = true;
this.throwableFromStop = th;
}});

Clazz.newMeth(C$, 'interrupt', function () {
p$.interrupt0.apply(this, []);
});

Clazz.newMeth(C$, 'interrupted', function () {
return Thread.currentThread().isInterruptedB$Z(true);
}, 1);

Clazz.newMeth(C$, 'isInterrupted', function () {
return p$.isInterruptedB$Z.apply(this, [false]);
});

Clazz.newMeth(C$, 'isInterruptedB$Z', function (clearInterrupted) {
var wasInt = this.$interrupted;
if (clearInterrupted) this.$interrupted = false;
return wasInt;
});

Clazz.newMeth(C$, 'destroy', function () {
throw Clazz.new_((I$[4]||$incl$(4)));
});

Clazz.newMeth(C$, 'isAlive', function () {
return this.started && !this.stopped ;
});

Clazz.newMeth(C$, 'suspend', function () {
this.checkAccess();
p$.suspend0.apply(this, []);
});

Clazz.newMeth(C$, 'resume', function () {
this.checkAccess();
p$.resume0.apply(this, []);
});

Clazz.newMeth(C$, 'setPriority$I', function (newPriority) {
var g;
this.checkAccess();
if (newPriority > 10 || newPriority < 1 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException'));
}if ((g = this.getThreadGroup()) != null ) {
if (newPriority > g.getMaxPriority()) {
newPriority = g.getMaxPriority();
}p$.setPriority0$I.apply(this, [this.priority = newPriority]);
}});

Clazz.newMeth(C$, 'getPriority', function () {
return this.priority;
});

Clazz.newMeth(C$, 'setName$S', function (name) {
this.checkAccess();
this.name = name;
});

Clazz.newMeth(C$, 'getName', function () {
return this.name;
});

Clazz.newMeth(C$, 'getThreadGroup', function () {
return this.group;
});

Clazz.newMeth(C$, 'activeCount', function () {
return Thread.currentThread().getThreadGroup().activeCount();
}, 1);

Clazz.newMeth(C$, 'enumerate$ThreadA', function (tarray) {
return Thread.currentThread().getThreadGroup().enumerate$ThreadA(tarray);
}, 1);

Clazz.newMeth(C$, 'countStackFrames', function () {
return 0;
});

Clazz.newMeth(C$, 'join$J', function (millis) {
var base = System.currentTimeMillis();
var now = 0;
if (millis < 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["timeout value is negative"]);
}(I$[5]||$incl$(5)).warn$S("Cannot wait in Thread");
if (millis == 0) {
while (this.isAlive()){
this.wait$J(0);
}
} else {
while (this.isAlive()){
var delay = millis - now;
if (delay <= 0) {
break;
}this.wait$J(delay);
now = System.currentTimeMillis() - base;
}
}});

Clazz.newMeth(C$, 'join$J$I', function (millis, nanos) {
if (millis < 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["timeout value is negative"]);
}if (nanos < 0 || nanos > 999999 ) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["nanosecond timeout value out of range"]);
}if (nanos >= 500000 || (nanos != 0 && millis == 0 ) ) {
millis++;
}this.join$J(millis);
});

Clazz.newMeth(C$, 'join', function () {
this.join$J(0);
});

Clazz.newMeth(C$, 'dumpStack', function () {
Clazz.new_(Clazz.load('java.lang.Exception').c$$S,["Stack trace"]).printStackTrace();
}, 1);

Clazz.newMeth(C$, 'setDaemon$Z', function (on) {
this.checkAccess();
if (this.isAlive()) {
throw Clazz.new_(Clazz.load('java.lang.IllegalThreadStateException'));
}this.daemon = on;
});

Clazz.newMeth(C$, 'isDaemon', function () {
return this.daemon;
});

Clazz.newMeth(C$, 'checkAccess', function () {
});

Clazz.newMeth(C$, 'toString', function () {
var group = this.getThreadGroup();
if (group != null ) {
return "Thread[" + this.getName() + "," + this.getPriority() + "," + group.getName() + "]" ;
} else {
return "Thread[" + this.getName() + "," + this.getPriority() + "," + "" + "]" ;
}});

Clazz.newMeth(C$, 'getContextClassLoader', function () {
return Clazz.getClass((I$[5]||$incl$(5))).getClassLoader();
});

Clazz.newMeth(C$, 'setContextClassLoader$ClassLoader', function (cl) {
});

Clazz.newMeth(C$, 'holdsLock$O', function (obj) {
return false;
}, 1);

Clazz.newMeth(C$, 'getStackTrace', function () {
return (Clazz.new_(Clazz.load('java.lang.Exception'))).getStackTrace();
});

Clazz.newMeth(C$, 'getAllStackTraces', function () {
var threads = Thread.getThreads();
var traces = Thread.dumpThreads$ThreadA(threads);
var m = Clazz.new_((I$[6]||$incl$(6)).c$$I,[threads.length]);
for (var i = 0; i < threads.length; i++) {
var stackTrace = traces[i];
if (stackTrace != null ) {
m.put$TK$TV(threads[i], stackTrace);
}}
return m;
}, 1);

Clazz.newMeth(C$, 'dumpThreads$ThreadA', function (threads) {
return null;
}, 1);

Clazz.newMeth(C$, 'getThreads', function () {
return null;
}, 1);

Clazz.newMeth(C$, 'getId', function () {
return this.tid;
});

Clazz.newMeth(C$, 'getState', function () {
switch (this.threadStatus) {
case 0:
return (I$[7]||$incl$(7)).NEW;
case 1:
return (I$[7]||$incl$(7)).RUNNABLE;
case 2:
default:
return (I$[7]||$incl$(7)).TERMINATED;
case 3:
return (I$[7]||$incl$(7)).TIMED_WAITING;
case 4:
return (I$[7]||$incl$(7)).WAITING;
}
});

Clazz.newMeth(C$, 'setDefaultUncaughtExceptionHandler$Thread_UncaughtExceptionHandler', function (eh) {
Thread.defaultUncaughtExceptionHandler = eh;
}, 1);

Clazz.newMeth(C$, 'getDefaultUncaughtExceptionHandler', function () {
return Thread.defaultUncaughtExceptionHandler;
}, 1);

Clazz.newMeth(C$, 'getUncaughtExceptionHandler', function () {
return this.uncaughtExceptionHandler != null  ? this.uncaughtExceptionHandler : this.group;
});

Clazz.newMeth(C$, 'setUncaughtExceptionHandler$Thread_UncaughtExceptionHandler', function (eh) {
this.checkAccess();
this.uncaughtExceptionHandler = eh;
});

Clazz.newMeth(C$, 'setPriority0$I', function (newPriority) {
});

Clazz.newMeth(C$, 'stop0$O', function (o) {
this.stopped = true;
});

Clazz.newMeth(C$, 'suspend0', function () {
});

Clazz.newMeth(C$, 'resume0', function () {
});

Clazz.newMeth(C$, 'interrupt0', function () {
this.$interrupted = true;
});
;
(function(){var C$=Clazz.newClass(Thread, "State", null, 'Enum');

C$.$clinit$ = function() {Clazz.load(C$, 1);
$vals=Clazz.array(C$,[0]);
Clazz.newEnumConst($vals, C$.c$, "NEW", 0, []);
Clazz.newEnumConst($vals, C$.c$, "RUNNABLE", 1, []);
Clazz.newEnumConst($vals, C$.c$, "BLOCKED", 2, []);
Clazz.newEnumConst($vals, C$.c$, "WAITING", 3, []);
Clazz.newEnumConst($vals, C$.c$, "TIMED_WAITING", 4, []);
Clazz.newEnumConst($vals, C$.c$, "TERMINATED", 5, []);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$);
var $vals=[];
Clazz.newMeth(C$, 'values', function() { return $vals }, 1);
})()
;
(function(){var C$=Clazz.newInterface(Thread, "UncaughtExceptionHandler", function(){
});
})()
})();
//Created 2018-02-06 08:58:38
