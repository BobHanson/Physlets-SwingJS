(function(){var P$=java.lang.reflect,I$=[];
var C$=Clazz.newClass(P$, "ReflectPermission", null, 'java.security.BasicPermission');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (permissionName) {
C$.superclazz.c$$S.apply(this, [permissionName]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$S', function (name, actions) {
C$.superclazz.c$$S$S.apply(this, [name, actions]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:40
