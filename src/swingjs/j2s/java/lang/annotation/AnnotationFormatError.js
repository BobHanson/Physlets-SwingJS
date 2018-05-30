(function(){var P$=java.lang.annotation,I$=[];
var C$=Clazz.newClass(P$, "AnnotationFormatError", null, 'Error');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (message) {
C$.superclazz.c$$S.apply(this, [message]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Throwable', function (message, cause) {
C$.superclazz.c$$S$Throwable.apply(this, [message, cause]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$Throwable', function (cause) {
C$.superclazz.c$$S$Throwable.apply(this, [cause == null  ? null : cause.toString(), cause]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:39
