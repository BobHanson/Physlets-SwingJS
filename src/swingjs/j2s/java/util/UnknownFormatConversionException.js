(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "UnknownFormatConversionException", null, 'java.util.IllegalFormatException');

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
this.s = s;
}, 1);

Clazz.newMeth(C$, 'getConversion', function () {
return this.s;
});

Clazz.newMeth(C$, 'getMessage', function () {
return "Conversion = '" + this.s + "'" ;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:15
