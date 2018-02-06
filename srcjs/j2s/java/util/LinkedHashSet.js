(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "LinkedHashSet", null, 'java.util.HashSet', ['java.util.Set', 'Cloneable', 'java.io.Serializable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$F', function (initialCapacity, loadFactor) {
C$.superclazz.c$$I$F$Z.apply(this, [initialCapacity, loadFactor, true]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (initialCapacity) {
C$.superclazz.c$$I$F$Z.apply(this, [initialCapacity, 0.75, true]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$I$F$Z.apply(this, [16, 0.75, true]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_util_Collection', function (c) {
C$.superclazz.c$$I$F$Z.apply(this, [Math.max(2 * c.size(), 11), 0.75, true]);
C$.$init$.apply(this);
this.addAll$java_util_Collection(c);
}, 1);
})();
//Created 2018-02-06 08:58:51
