(function(){var P$=Clazz.newPackage("sun.misc"),I$=[];
var C$=Clazz.newClass(P$, "Ref");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.soft = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'get', function () {
var t = this.check();
if (t == null ) {
t = this.reconstitute();
this.setThing$O(t);
}return t;
});

Clazz.newMeth(C$, 'flush', function () {
});

Clazz.newMeth(C$, 'setThing$O', function (thing) {
this.flush();
this.soft = thing;
});

Clazz.newMeth(C$, 'check', function () {
return this.soft;
});

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$O', function (thing) {
C$.$init$.apply(this);
this.setThing$O(thing);
}, 1);
})();
//Created 2018-02-08 10:03:12
