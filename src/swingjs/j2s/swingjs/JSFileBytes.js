(function(){var P$=Clazz.newPackage("swingjs"),I$=[];
var C$=Clazz.newClass(P$, "JSFileBytes", null, 'java.io.File');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.bytes = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$BA', function (name, data) {
C$.superclazz.c$$S.apply(this, [name]);
C$.$init$.apply(this);
this.bytes = data;
}, 1);

Clazz.newMeth(C$, 'getData', function () {
return this.bytes;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:14
