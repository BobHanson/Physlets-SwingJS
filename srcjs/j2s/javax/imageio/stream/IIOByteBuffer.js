(function(){var P$=Clazz.newPackage("javax.imageio.stream"),I$=[];
var C$=Clazz.newClass(P$, "IIOByteBuffer");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.data = null;
this.offset = 0;
this.length = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$BA$I$I', function (data, offset, length) {
C$.$init$.apply(this);
this.data = data;
this.offset = offset;
this.length = length;
}, 1);

Clazz.newMeth(C$, 'getData', function () {
return this.data;
});

Clazz.newMeth(C$, 'setData$BA', function (data) {
this.data = data;
});

Clazz.newMeth(C$, 'getOffset', function () {
return this.offset;
});

Clazz.newMeth(C$, 'setOffset$I', function (offset) {
this.offset = offset;
});

Clazz.newMeth(C$, 'getLength', function () {
return this.length;
});

Clazz.newMeth(C$, 'setLength$I', function (length) {
this.length = length;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:59:10