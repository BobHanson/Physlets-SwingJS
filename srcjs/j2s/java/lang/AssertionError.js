(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "AssertionError", null, 'Error');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$O', function (detailMessage) {
C$.superclazz.c$$S$Throwable.apply(this, [String.valueOf(detailMessage), (Clazz.instanceOf(detailMessage, "java.lang.Throwable") ? detailMessage : null)]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$Z', function (detailMessage) {
C$.c$$O.apply(this, [String.valueOf(detailMessage)]);
}, 1);

Clazz.newMeth(C$, 'c$$C', function (detailMessage) {
C$.c$$O.apply(this, [String.valueOf(detailMessage)]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (detailMessage) {
C$.c$$O.apply(this, [Integer.toString(detailMessage)]);
}, 1);

Clazz.newMeth(C$, 'c$$J', function (detailMessage) {
C$.c$$O.apply(this, [Long.toString(detailMessage)]);
}, 1);

Clazz.newMeth(C$, 'c$$F', function (detailMessage) {
C$.c$$O.apply(this, [Float.toString(detailMessage)]);
}, 1);

Clazz.newMeth(C$, 'c$$D', function (detailMessage) {
C$.c$$O.apply(this, [Double.toString(detailMessage)]);
}, 1);
})();
//Created 2018-02-06 08:58:33
