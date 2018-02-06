(function(){var P$=Clazz.newPackage("javax.swing.text"),I$=[];
var C$=Clazz.newClass(P$, "ChangedCharSetException", null, 'java.io.IOException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.charSetSpec = null;
this.charSetKey = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$Z', function (charSetSpec, charSetKey) {
Clazz.super_(C$, this,1);
this.charSetSpec = charSetSpec;
this.charSetKey = charSetKey;
}, 1);

Clazz.newMeth(C$, 'getCharSetSpec', function () {
return this.charSetSpec;
});

Clazz.newMeth(C$, 'keyEqualsCharSet', function () {
return this.charSetKey;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:55
