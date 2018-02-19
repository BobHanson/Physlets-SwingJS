(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "UnsupportedOperationException", null, 'RuntimeException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
Clazz.super_(C$, this,1);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (detailMessage) {
C$.superclazz.c$$S.apply(this, [detailMessage]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Throwable', function (message, cause) {
C$.superclazz.c$$S$Throwable.apply(this, [message, cause]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$Throwable', function (cause) {
C$.superclazz.c$$S$Throwable.apply(this, [(cause == null  ? null : cause.toString()), cause]);
C$.$init$.apply(this);
}, 1);
})();
//Created 2018-02-08 10:02:08
