(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(P$, "IllegalStateException", null, 'RuntimeException');

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

Clazz.newMeth(C$, 'c$$S$Throwable', function (message, cause) {
C$.superclazz.c$$S$Throwable.apply(this, [message, cause]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$Throwable', function (cause) {
C$.superclazz.c$$S$Throwable.apply(this, [(cause == null  ? null : cause.toString()), cause]);
C$.$init$.apply(this);
}, 1);
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:46:55 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
