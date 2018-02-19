(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "IllegalFormatCodePointException", null, 'java.util.IllegalFormatException', 'java.io.Serializable');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.c = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (c) {
Clazz.super_(C$, this,1);
this.c = c;
}, 1);

Clazz.newMeth(C$, 'getCodePoint', function () {
return this.c;
});

Clazz.newMeth(C$, 'getMessage', function () {
return "Code point is " + this.c;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:13
