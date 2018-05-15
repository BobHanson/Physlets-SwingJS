(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "ClassNotFoundException", null, 'Exception');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.ex = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$Throwable.apply(this, [null]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (detailMessage) {
C$.superclazz.c$$S$Throwable.apply(this, [detailMessage, null]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Throwable', function (detailMessage, exception) {
C$.superclazz.c$$S.apply(this, [detailMessage]);
C$.$init$.apply(this);
this.ex = exception;
}, 1);

Clazz.newMeth(C$, 'getException', function () {
return this.ex;
});

Clazz.newMeth(C$, 'getCause', function () {
return this.ex;
});
})();
//Created 2018-05-15 01:02:07
