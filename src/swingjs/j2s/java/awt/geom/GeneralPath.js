(function(){var P$=Clazz.newPackage("java.awt.geom"),I$=[];
var C$=Clazz.newClass(P$, "GeneralPath", null, ['java.awt.geom.Path2D','java.awt.geom.Path2D.Float']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.superclazz.c$$I$I.apply(this, [1, 20]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (rule) {
C$.superclazz.c$$I$I.apply(this, [rule, 20]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (rule, initialCapacity) {
C$.superclazz.c$$I$I.apply(this, [rule, initialCapacity]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_Shape', function (s) {
C$.superclazz.c$$java_awt_Shape$java_awt_geom_AffineTransform.apply(this, [s, null]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$I$BA$I$FA$I', function (windingRule, pointTypes, numTypes, pointCoords, numCoords) {
Clazz.super_(C$, this,1);
this.windingRule = windingRule;
this.pointTypes = pointTypes;
this.numTypes = numTypes;
this.floatCoords = pointCoords;
this.numCoords = numCoords;
}, 1);
})();
//Created 2018-05-15 01:01:59
