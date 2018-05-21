(function(){var P$=java.io,I$=[['org.apache.harmony.luni.util.Msg']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "OutputStream", null, null, ['java.io.Closeable', 'java.io.Flushable']);

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'close', function () {
});

Clazz.newMeth(C$, 'flush', function () {
});

Clazz.newMeth(C$, 'write$BA', function (buffer) {
this.write$BA$I$I(buffer, 0, buffer.length);
});

Clazz.newMeth(C$, 'write$BA$I$I', function (buffer, offset, count) {
if (offset <= buffer.length && 0 <= offset  && 0 <= count  && count <= buffer.length - offset ) {
for (var i = offset; i < offset + count; i++) this.write$I(buffer[i]);

} else throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException').c$$S,[(I$[1]||$incl$(1)).getString$S("K002f")]);
});
})();
//Created 2018-05-15 01:02:05
