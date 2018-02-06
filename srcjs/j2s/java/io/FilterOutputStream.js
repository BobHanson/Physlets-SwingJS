(function(){var P$=java.io,I$=[['org.apache.harmony.luni.util.Msg']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "FilterOutputStream", null, 'java.io.OutputStream');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.out = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream', function (out) {
Clazz.super_(C$, this,1);
this.out = out;
}, 1);

Clazz.newMeth(C$, 'close', function () {
try {
this.flush();
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.IOException")){
} else {
throw e;
}
}
this.out.close();
});

Clazz.newMeth(C$, 'flush', function () {
this.out.flush();
});

Clazz.newMeth(C$, 'write$BA', function (buffer) {
this.write$BA$I$I(buffer, 0, buffer.length);
});

Clazz.newMeth(C$, 'write$BA$I$I', function (buffer, offset, count) {
if (offset <= buffer.length && 0 <= offset  && 0 <= count  && count <= buffer.length - offset ) {
for (var i = 0; i < count; i++) {
this.write$I(buffer[offset + i]);
}
} else {
throw Clazz.new_(Clazz.load('java.lang.ArrayIndexOutOfBoundsException').c$$S,[(I$[1]||$incl$(1)).getString$S("K002f")]);
}});

Clazz.newMeth(C$, 'write$I', function (oneByte) {
this.out.write$I(oneByte);
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:31
