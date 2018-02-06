(function(){var P$=java.lang.reflect,I$=[];
var C$=Clazz.newClass(P$, "InvocationTargetException", null, 'Exception');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.target = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$Throwable.apply(this, [null]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$Throwable', function (exception) {
C$.superclazz.c$$S$Throwable.apply(this, [null, exception]);
C$.$init$.apply(this);
this.target = exception;
}, 1);

Clazz.newMeth(C$, 'c$$Throwable$S', function (exception, detailMessage) {
C$.superclazz.c$$S$Throwable.apply(this, [detailMessage, exception]);
C$.$init$.apply(this);
this.target = exception;
}, 1);

Clazz.newMeth(C$, 'getTargetException', function () {
return this.target;
});

Clazz.newMeth(C$, 'getCause', function () {
return this.target;
});
})();
//Created 2018-02-06 08:58:40
