(function(){var P$=Clazz.newPackage("java.awt"),I$=[['java.lang.InternalError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ImageCapabilities", null, null, 'Cloneable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.accelerated = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.accelerated = false;
}, 1);

Clazz.newMeth(C$, 'c$$Z', function (accelerated) {
C$.$init$.apply(this);
this.accelerated = accelerated;
}, 1);

Clazz.newMeth(C$, 'isAccelerated', function () {
return this.accelerated;
});

Clazz.newMeth(C$, 'isTrueVolatile', function () {
return false;
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

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:01:52
