(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newClass(P$, "AWTEvent", null, 'java.util.EventObject');
C$.idnum = 0;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.bdata = null;
this.id = 0;
this.num = 0;
this.consumed = false;
this.focusManagerIsDispatching = false;
this.isPosted = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.consumed = false;
this.focusManagerIsDispatching = false;
}, 1);

Clazz.newMeth(C$, 'getBData', function () {
return this.bdata;
});

Clazz.newMeth(C$, 'setBData$BA', function (bdata) {
this.bdata = bdata;
});

Clazz.newMeth(C$, 'c$$java_awt_Event', function (event) {
C$.c$$O$I.apply(this, [event.target, event.id]);
}, 1);

Clazz.newMeth(C$, 'c$$O$I', function (source, id) {
C$.superclazz.c$.apply(this, [source]);
C$.$init$.apply(this);
this.id = id;
this.num = ++C$.idnum;
switch (id) {
case 1001:
case 701:
case 601:
case 900:
this.consumed = true;
break;
default:
}
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, [null]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'setSource$O', function (newSource) {
if (this.source === newSource ) {
return;
}var comp = null;
if (Clazz.instanceOf(newSource, "java.awt.Component")) {
comp = newSource;
while (comp != null  && comp.peer != null   && (Clazz.instanceOf(comp.peer, "java.awt.peer.LightweightPeer")) ){
comp = comp.parent;
}
}this.source = newSource;
});

Clazz.newMeth(C$, 'getID', function () {
return this.id;
});

Clazz.newMeth(C$, 'toString', function () {
var srcName = null;
if (Clazz.instanceOf(this.source, "java.awt.Component")) {
srcName = (this.source).getName();
}return this.getClass().getName() + "[" + this.paramString() + "] on " + (srcName != null  ? srcName : this.source) ;
});

Clazz.newMeth(C$, 'paramString', function () {
return "";
});

Clazz.newMeth(C$, 'consume', function () {
switch (this.id) {
case 401:
case 402:
case 501:
case 502:
case 503:
case 506:
case 504:
case 505:
case 507:
case 1100:
case 1101:
this.consumed = true;
break;
default:
}
});

Clazz.newMeth(C$, 'isConsumed', function () {
return this.consumed;
});

Clazz.newMeth(C$, 'copyPrivateDataInto$java_awt_AWTEvent', function (that) {
that.bdata = this.bdata;
});

Clazz.newMeth(C$, 'dispatched', function () {
});
})();
//Created 2018-05-15 01:01:48
