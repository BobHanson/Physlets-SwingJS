(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "IllegalFormatWidthException", null, 'java.util.IllegalFormatException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.w = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I', function (w) {
Clazz.super_(C$, this,1);
this.w=w;
}, 1);

Clazz.newMeth(C$, 'getWidth', function () {
return this.w;
});

Clazz.newMeth(C$, 'getMessage', function () {
return String.valueOf(this.w);
});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:47
