(function(){var P$=Clazz.newPackage("java.awt.geom"),I$=[['java.lang.InternalError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "Dimension2D", null, null, 'Cloneable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'setSize$java_awt_geom_Dimension2D', function (d) {
this.setSize$D$D(d.getWidth(), d.getHeight());
});

Clazz.newMeth(C$, 'clone', function () {
try {
return Clazz.clone(this);
} catch (e) {
if (Clazz.exceptionOf(e, "java.lang.CloneNotSupportedException")){
throw Clazz.new_((I$[1]||$incl$(1)));
} else {
throw e;
}
}
});
})();
//Created 2018-02-08 10:01:58
