(function(){var P$=Clazz.newPackage("java.awt.geom"),I$=[];
var C$=Clazz.newClass(P$, "CubicIterator", null, null, 'java.awt.geom.PathIterator');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.cubic=null;
this.affine=null;
this.index=0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$java_awt_geom_CubicCurve2D$java_awt_geom_AffineTransform', function (q, at) {
C$.$init$.apply(this);
this.set$java_awt_geom_CubicCurve2D$java_awt_geom_AffineTransform(q, at);
}, 1);

Clazz.newMeth(C$, 'set$java_awt_geom_CubicCurve2D$java_awt_geom_AffineTransform', function (q, at) {
this.cubic=q;
this.affine=at;
this.index=0;
return this;
});

Clazz.newMeth(C$, 'getWindingRule$', function () {
return 1;
});

Clazz.newMeth(C$, 'isDone$', function () {
return (this.index > 1);
});

Clazz.newMeth(C$, 'next$', function () {
this.index++;
});

Clazz.newMeth(C$, 'currentSegment$FA', function (coords) {
if (this.isDone$()) {
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException').c$$S,["cubic iterator iterator out of bounds"]);
}var type;
if (this.index == 0) {
coords[0]=this.cubic.getX1$();
coords[1]=this.cubic.getY1$();
type=0;
} else {
coords[0]=this.cubic.getCtrlX1$();
coords[1]=this.cubic.getCtrlY1$();
coords[2]=this.cubic.getCtrlX2$();
coords[3]=this.cubic.getCtrlY2$();
coords[4]=this.cubic.getX2$();
coords[5]=this.cubic.getY2$();
type=3;
}if (this.affine != null ) {
this.affine.transform$FA$I$FA$I$I(coords, 0, coords, 0, this.index == 0 ? 1 : 3);
}return type;
});

Clazz.newMeth(C$, 'currentSegment$DA', function (coords) {
if (this.isDone$()) {
throw Clazz.new_(Clazz.load('java.util.NoSuchElementException').c$$S,["cubic iterator iterator out of bounds"]);
}var type;
if (this.index == 0) {
coords[0]=this.cubic.getX1$();
coords[1]=this.cubic.getY1$();
type=0;
} else {
coords[0]=this.cubic.getCtrlX1$();
coords[1]=this.cubic.getCtrlY1$();
coords[2]=this.cubic.getCtrlX2$();
coords[3]=this.cubic.getCtrlY2$();
coords[4]=this.cubic.getX2$();
coords[5]=this.cubic.getY2$();
type=3;
}if (this.affine != null ) {
this.affine.transform$DA$I$DA$I$I(coords, 0, coords, 0, this.index == 0 ? 1 : 3);
}return type;
});
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:46:50 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
