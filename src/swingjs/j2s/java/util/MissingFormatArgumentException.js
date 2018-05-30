(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "MissingFormatArgumentException", null, 'java.util.IllegalFormatException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.s = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (s) {
Clazz.super_(C$, this,1);
if (null == s ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.s=s;
}, 1);

Clazz.newMeth(C$, 'getFormatSpecifier', function () {
return this.s;
});

Clazz.newMeth(C$, 'getMessage', function () {
return "Format specifier '" + this.s + "'" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:48
