(function(){var P$=Clazz.newPackage("sun.misc"),I$=[];
var C$=Clazz.newClass(P$, "QueueElement");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.next = null;
this.prev = null;
this.obj = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.next = null;
this.prev = null;
this.obj = null;
}, 1);

Clazz.newMeth(C$, 'c$$O', function (obj) {
C$.$init$.apply(this);
this.obj = obj;
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return "QueueElement[obj=" + this.obj + (this.prev == null  ? " null" : " prev") + (this.next == null  ? " null" : " next") + "]" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:12
