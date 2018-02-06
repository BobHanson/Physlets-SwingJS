(function(){var P$=Clazz.newPackage("java.awt"),I$=[];
var C$=Clazz.newClass(P$, "AttributeValue");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.value = 0;
this.names = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$SA', function (value, names) {
C$.$init$.apply(this);
this.value = value;
this.names = names;
}, 1);

Clazz.newMeth(C$, 'hashCode', function () {
return this.value;
});

Clazz.newMeth(C$, 'toString', function () {
return this.names[this.value];
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:07
