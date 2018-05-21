(function(){var P$=Clazz.newPackage("java.net"),I$=[];
var C$=Clazz.newClass(P$, "URLDecoder");

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'decode$S', function (s) {

return decodeURIComponent(arguments[0]);
}, 1);

Clazz.newMeth(C$, 'decode$S$S', function (s, enc) {
if (enc == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}{
return decodeURIComponent(arguments[0]);
}
return null;
}, 1);

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:10
