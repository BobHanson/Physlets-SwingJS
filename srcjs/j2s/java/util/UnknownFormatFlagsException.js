(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "UnknownFormatFlagsException", null, 'java.util.IllegalFormatException');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.flags = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (f) {
Clazz.super_(C$, this,1);
if (null == f ) {
throw Clazz.new_(Clazz.load('java.lang.NullPointerException'));
}this.flags = f;
}, 1);

Clazz.newMeth(C$, 'getFlags', function () {
return this.flags;
});

Clazz.newMeth(C$, 'getMessage', function () {
return "The flags are " + this.flags;
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:55
