(function(){var P$=Clazz.newPackage("sun.awt"),I$=[];
var C$=Clazz.newClass(P$, "MostRecentKeyValue");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key = null;
this.value = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O$O', function (k, v) {
C$.$init$.apply(this);
this.key = k;
this.value = v;
}, 1);

Clazz.newMeth(C$, 'setPair$O$O', function (k, v) {
this.key = k;
this.value = v;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:05
