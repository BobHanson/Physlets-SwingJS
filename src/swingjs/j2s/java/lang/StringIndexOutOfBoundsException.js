(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "StringIndexOutOfBoundsException", null, 'IndexOutOfBoundsException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (index) {
C$.superclazz.c$$S.apply(this, ["String index out of range: " + index]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (detailMessage) {
C$.superclazz.c$$S.apply(this, [detailMessage]);
C$.$init$.apply(this);
}, 1);
})();
//Created 2018-05-24 08:45:38
