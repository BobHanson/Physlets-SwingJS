(function(){var P$=Clazz.newPackage("java.awt.dnd"),I$=[];
var C$=Clazz.newClass(P$, "InvalidDnDOperationException", null, 'IllegalStateException');
C$.dft_msg = null;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.dft_msg = "The operation requested cannot be performed by the DnD system since it is not in the appropriate state";
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$S.apply(this, [C$.dft_msg]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$S', function (msg) {
C$.superclazz.c$$S.apply(this, [msg]);
C$.$init$.apply(this);
}, 1);
})();
//Created 2018-02-06 08:58:17
