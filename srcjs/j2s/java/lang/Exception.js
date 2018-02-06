(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "Exception", null, 'Throwable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (detailMessage) {
C$.superclazz.c$$S.apply(this, [detailMessage]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S$Throwable', function (detailMessage, throwable) {
C$.superclazz.c$$S$Throwable.apply(this, [detailMessage, throwable]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$Throwable', function (throwable) {
C$.superclazz.c$$Throwable.apply(this, [throwable]);
C$.$init$.apply(this);
}, 1);
})();
//Created 2018-02-06 08:58:36
