(function(){var P$=Clazz.newPackage("sun.swing"),I$=[];
var C$=Clazz.newClass(P$, "StringUIClientPropertyKey", null, null, 'sun.swing.UIClientPropertyKey');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.key = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (key) {
C$.$init$.apply(this);
this.key = key;
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return this.key;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:03:13
