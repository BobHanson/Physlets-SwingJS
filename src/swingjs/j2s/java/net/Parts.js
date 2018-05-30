(function(){var P$=Clazz.newPackage("java.net"),I$=[];
var C$=Clazz.newClass(P$, "Parts");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.path = null;
this.query = null;
this.ref = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (file) {
C$.$init$.apply(this);
var ind = file.indexOf("#");
this.ref=ind < 0 ? null : file.substring(ind + 1);
file=ind < 0 ? file : file.substring(0, ind);
var q = file.lastIndexOf("?");
if (q != -1) {
this.query=file.substring(q + 1);
this.path=file.substring(0, q);
} else {
this.path=file;
}}, 1);

Clazz.newMeth(C$, 'getPath', function () {
return this.path;
});

Clazz.newMeth(C$, 'getQuery', function () {
return this.query;
});

Clazz.newMeth(C$, 'getRef', function () {
return this.ref;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:41
