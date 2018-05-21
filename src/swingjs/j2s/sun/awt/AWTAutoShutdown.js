(function(){var P$=Clazz.newPackage("sun.awt"),I$=[['java.util.HashSet','java.util.IdentityHashMap','java.awt.AWTEvent']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "AWTAutoShutdown", null, null, 'Runnable');
C$.theInstance = null;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.busyThreadSet = null;
this.toolkitThreadBusy = false;
this.peerMap = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.busyThreadSet = Clazz.new_((I$[1]||$incl$(1)).c$$I,[7]);
this.toolkitThreadBusy = false;
this.peerMap = Clazz.new_((I$[2]||$incl$(2)));
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'getInstance', function () {
return (C$.theInstance == null  ? (C$.theInstance = Clazz.new_(C$)) : C$.theInstance);
}, 1);

Clazz.newMeth(C$, 'notifyToolkitThreadBusy', function () {
C$.getInstance().setToolkitBusy$Z(true);
}, 1);

Clazz.newMeth(C$, 'notifyToolkitThreadFree', function () {
C$.getInstance().setToolkitBusy$Z(false);
}, 1);

Clazz.newMeth(C$, 'notifyThreadBusy$Thread', function (thread) {
this.busyThreadSet.add$TE(thread);
});

Clazz.newMeth(C$, 'notifyThreadFree$Thread', function (thread) {
this.busyThreadSet.remove$O(thread);
});

Clazz.newMeth(C$, 'notifyPeerMapUpdated', function () {
if (!p$.isReadyToShutdown.apply(this, [])) {
p$.activateBlockerThread.apply(this, []);
}});

Clazz.newMeth(C$, 'isReadyToShutdown', function () {
return (!this.toolkitThreadBusy && this.peerMap.isEmpty() && this.busyThreadSet.isEmpty()  );
});

Clazz.newMeth(C$, 'setToolkitBusy$Z', function (busy) {
if (busy != this.toolkitThreadBusy ) {
if (busy != this.toolkitThreadBusy ) {
if (busy) {
this.toolkitThreadBusy = busy;
} else {
this.toolkitThreadBusy = busy;
}}}});

Clazz.newMeth(C$, 'run', function () {
});

Clazz.newMeth(C$, 'getShutdownEvent', function () {
return ((
(function(){var C$=Clazz.newClass(P$, "AWTAutoShutdown$1", function(){Clazz.newInstance(this, arguments[0],1,C$);}, Clazz.load('java.awt.AWTEvent'), null, 1);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);
})()
), Clazz.new_((I$[3]||$incl$(3)).c$$O$I, [this, null, C$.getInstance(), 0],P$.AWTAutoShutdown$1));
}, 1);

Clazz.newMeth(C$, 'activateBlockerThread', function () {
});

Clazz.newMeth(C$, 'registerPeer$O$O', function (target, peer) {
this.peerMap.put$TK$TV(target, peer);
this.notifyPeerMapUpdated();
});

Clazz.newMeth(C$, 'unregisterPeer$O$O', function (target, peer) {
if (this.peerMap.get$O(target) === peer ) {
this.peerMap.remove$O(target);
}});

Clazz.newMeth(C$, 'getPeer$O', function (target) {
return this.peerMap.get$O(target);
});

Clazz.newMeth(C$, 'dumpPeers$java_util_logging_Logger', function (aLog) {
aLog.fine$S("Mapped peers:");
for (var key, $key = this.peerMap.keySet().iterator(); $key.hasNext()&&((key=$key.next()),1);) {
aLog.fine$S(key + "->" + this.peerMap.get$O(key) );
}
});
})();
//Created 2018-05-15 01:03:05
