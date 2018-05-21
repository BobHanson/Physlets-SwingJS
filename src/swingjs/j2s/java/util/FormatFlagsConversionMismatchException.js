(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "FormatFlagsConversionMismatchException", null, 'java.util.IllegalFormatException', 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.f = null;
this.c = '\0';
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S$C', function (f, c) {
Clazz.super_(C$, this,1);
if (null == f ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.f = f;
this.c = c;
}, 1);

Clazz.newMeth(C$, 'getFlags', function () {
return this.f;
});

Clazz.newMeth(C$, 'getConversion', function () {
return this.c;
});

Clazz.newMeth(C$, 'getMessage', function () {
return "Mismatched Convertor =" + this.c + ", Flags= " + this.f ;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:12
