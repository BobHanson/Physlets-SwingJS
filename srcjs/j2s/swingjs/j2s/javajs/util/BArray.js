(function(){var P$=Clazz.newPackage("javajs.util"),I$=[];
var C$=Clazz.newClass(P$, "BArray");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.data = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$BA', function (data) {
C$.$init$.apply(this);
this.data = data;
}, 1);

Clazz.newMeth(C$, 'equals$O', function (o) {
if (Clazz.instanceOf(o, "javajs.util.BArray")) {
var d = (o).data;
if (d.length == this.data.length) {
for (var i = 0; i < d.length; i++) if (d[i] != this.data[i]) return false;

return true;
}}return false;
});

Clazz.newMeth(C$, 'hashCode', function () {
return this.data.hashCode();
});

Clazz.newMeth(C$, 'toString', function () {
return  String.instantialize(this.data);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:18
