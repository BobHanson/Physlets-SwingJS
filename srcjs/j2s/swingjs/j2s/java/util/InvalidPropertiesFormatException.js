(function(){var P$=java.util,I$=[];
var C$=Clazz.newClass(P$, "InvalidPropertiesFormatException", null, 'java.io.IOException');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$S', function (m) {
C$.superclazz.c$$S.apply(this, [m]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$Throwable', function (c) {
Clazz.super_(C$, this,1);
this.initCause$Throwable(c);
}, 1);

Clazz.newMeth(C$, 'writeObject$java_io_ObjectOutputStream', function (out) {
throw Clazz.new_(Clazz.load('java.io.NotSerializableException'));
});

Clazz.newMeth(C$, 'readObject$java_io_ObjectInputStream', function ($in) {
throw Clazz.new_(Clazz.load('java.io.NotSerializableException'));
});

Clazz.newMeth(C$);
})();
//Created 2018-02-08 10:02:13
