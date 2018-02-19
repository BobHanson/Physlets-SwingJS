(function(){var P$=java.lang,I$=[];
var C$=Clazz.newClass(java.lang, "ArrayIndexOutOfBoundsException", null, 'IndexOutOfBoundsException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (index) {
C$.superclazz.c$$S.apply(this, ["Array index out of range: " + index]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (detailMessage) {
C$.superclazz.c$$S.apply(this, [detailMessage]);
C$.$init$.apply(this);
}, 1);
})();
//Created 2018-02-08 10:02:05
