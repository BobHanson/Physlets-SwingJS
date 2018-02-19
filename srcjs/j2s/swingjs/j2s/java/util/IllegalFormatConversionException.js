(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "IllegalFormatConversionException", null, 'java.util.IllegalFormatException', 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.c = '\0';
this.arg = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$C$Class', function (c, arg) {
Clazz.super_(C$, this,1);
this.c = c;
if (arg == null ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.arg = arg;
}, 1);

Clazz.newMeth(C$, 'getArgumentClass', function () {
return this.arg;
});

Clazz.newMeth(C$, 'getConversion', function () {
return this.c;
});

Clazz.newMeth(C$, 'getMessage', function () {
return "" + this.c + " is incompatible with " + this.arg.getName() ;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:13
