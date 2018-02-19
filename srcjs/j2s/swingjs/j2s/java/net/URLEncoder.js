(function(){var P$=Clazz.newPackage("java.net"),I$=[];
var C$=Clazz.newClass(P$, "URLEncoder");
var p$=C$.prototype;

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'encode$S', function (s) {

return encodeURIComponent(arguments[0]);
}, 1);

Clazz.newMeth(C$, 'encode$S$S', function (s, enc) {

return encodeURIComponent(arguments[0]);
}, 1);
})();
//Created 2018-02-08 10:02:09
