(function(){var P$=Clazz.newPackage("java.awt.image"),I$=[];
var C$=Clazz.newClass(P$, "LookupTable");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.numComponents = 0;
this.offset = 0;
this.numEntries = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (offset, numComponents) {
C$.$init$.apply(this);
if (offset < 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Offset must be greater than 0"]);
}if (numComponents < 1) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Number of components must  be at least 1"]);
}this.numComponents=numComponents;
this.offset=offset;
}, 1);

Clazz.newMeth(C$, 'getNumComponents', function () {
return this.numComponents;
});

Clazz.newMeth(C$, 'getOffset', function () {
return this.offset;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:26
