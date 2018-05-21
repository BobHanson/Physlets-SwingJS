(function(){var P$=Clazz.newPackage("java.text"),I$=[];
var C$=Clazz.newClass(P$, "MessageFormat");

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.pattern = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (pattern) {
C$.$init$.apply(this);
this.pattern = pattern;
}, 1);

Clazz.newMeth(C$, 'c$$S$java_util_Locale', function (pattern, locale) {
C$.$init$.apply(this);
this.pattern = pattern;
}, 1);

Clazz.newMeth(C$, 'format$S$OA', function (pattern, args) {
{
return pattern.replace (/\{(\d+)\}/g, function ($0, $1) { var i = parseInt ($1);
if (args == null) return null;
return args[i];
});
}
}, 1);

Clazz.newMeth(C$, 'format$O', function (obj) {
return C$.format$S$OA(this.pattern, Clazz.array(java.lang.Object, -1, [obj]));
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:11
