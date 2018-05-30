(function(){var P$=Clazz.newPackage("a2s"),I$=[];
var C$=Clazz.newClass(P$, "Menu", null, 'javax.swing.JMenu');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (title) {
C$.superclazz.c$$S.apply(this, [title]);
C$.$init$.apply(this);
title=null;
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$.apply(this, []);
C$.$init$.apply(this);
var s = null;
}, 1);

Clazz.newMeth(C$, 'countItems', function () {
return C$.superclazz.prototype.getComponentCount.apply(this, []);
});
})();
//Created 2018-05-24 08:45:00
