(function(){var P$=Clazz.newPackage("gnu.jpdf"),I$=[];
var C$=Clazz.newClass(P$, "PDFXref");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.id = 0;
this.offset = 0;
this.generation = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (id, offset) {
C$.c$$I$I$I.apply(this, [id, offset, 0]);
}, 1);

Clazz.newMeth(C$, 'c$$I$I$I', function (id, offset, generation) {
C$.$init$.apply(this);
this.id = id;
this.offset = offset;
this.generation = generation;
}, 1);

Clazz.newMeth(C$, 'toString', function () {
var of = Integer.toString(this.offset);
var ge = Integer.toString(this.generation);
var rs = "0000000000".substring(0, 10 - of.length$()) + of + " " + "00000".substring(0, 5 - ge.length$()) + ge ;
if (this.generation == 65535) return rs + " f ";
return rs + " n ";
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:01:44
