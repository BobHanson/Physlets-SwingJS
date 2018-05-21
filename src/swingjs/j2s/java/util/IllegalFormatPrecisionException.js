(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "IllegalFormatPrecisionException", null, 'java.util.IllegalFormatException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.p = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (p) {
Clazz.super_(C$, this,1);
this.p = p;
}, 1);

Clazz.newMeth(C$, 'getPrecision', function () {
return this.p;
});

Clazz.newMeth(C$, 'getMessage', function () {
return String.valueOf(this.p);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:02:13
