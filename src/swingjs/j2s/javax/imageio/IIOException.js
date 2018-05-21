(function(){var P$=Clazz.newPackage("javax.imageio"),I$=[];
var C$=Clazz.newClass(P$, "IIOException", null, 'java.io.IOException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (message) {
C$.superclazz.c$$S.apply(this, [message]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Throwable', function (message, cause) {
C$.superclazz.c$$S.apply(this, [message]);
C$.$init$.apply(this);
this.initCause$Throwable(cause);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:20
