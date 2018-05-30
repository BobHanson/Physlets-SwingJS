(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "ExceptionInInitializerError", null, 'LinkageError');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.exception = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.initCause$Throwable(null);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (detailMessage) {
C$.superclazz.c$$S.apply(this, [detailMessage]);
C$.$init$.apply(this);
this.initCause$Throwable(null);
}, 1);

Clazz.newMeth(C$, 'c$$Throwable', function (exception) {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
this.exception=exception;
this.initCause$Throwable(exception);
}, 1);

Clazz.newMeth(C$, 'getException', function () {
return this.exception;
});

Clazz.newMeth(C$, 'getCause', function () {
return this.exception;
});
})();
//Created 2018-05-24 08:45:37
