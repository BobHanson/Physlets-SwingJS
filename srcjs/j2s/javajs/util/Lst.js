(function(){var P$=Clazz.newPackage("javajs.util"),I$=[];
var C$=Clazz.newClass(P$, "Lst", null, 'java.util.ArrayList');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, ['addLast$TV'], function (v) {
return C$.superclazz.prototype.add$TE.apply(this, [v]);
});

Clazz.newMeth(C$, 'removeItemAt$I', function (location) {
return C$.superclazz.prototype.remove$I.apply(this, [location]);
});

Clazz.newMeth(C$, 'removeObj$O', function (v) {
return C$.superclazz.prototype.remove$O.apply(this, [v]);
});
})();
//Created 2018-02-06 08:59:05
