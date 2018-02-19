(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[];
var C$=Clazz.newClass(P$, "StringTooLongException", null, 'Exception');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.msg = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (msg) {
Clazz.super_(C$, this,1);
this.msg = msg;
}, 1);

Clazz.newMeth(C$, 'toString', function () {
return this.msg;
});

Clazz.newMeth(C$, 'getMessage', function () {
return this.msg;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:45
