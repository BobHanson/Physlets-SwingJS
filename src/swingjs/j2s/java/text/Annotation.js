(function(){var P$=Clazz.newPackage("java.text"),I$=[];
var C$=Clazz.newClass(P$, "Annotation");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.value = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$O', function (value) {
C$.$init$.apply(this);
this.value=value;
}, 1);

Clazz.newMeth(C$, 'getValue', function () {
return this.value;
});

Clazz.newMeth(C$, 'toString', function () {
return this.getClass().getName() + "[value=" + this.value + "]" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:41
