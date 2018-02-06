(function(){var P$=java.io,I$=[['java.io.StringWriter','swingjs.api.Interface']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "OutputStreamWriter", null, 'java.io.Writer');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.charsetName = null;
this.stream = null;
this.writer = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream$S', function (out, charsetName) {
C$.superclazz.c$$O.apply(this, [out]);
C$.$init$.apply(this);
this.stream = out;
p$.setCharset$S.apply(this, [charsetName]);
}, 1);

Clazz.newMeth(C$, 'setCharset$S', function (charsetName) {
if (charsetName == null ) charsetName = "UTF-8";
if (!charsetName.equals$O("UTF-8")) throw Clazz.new_(Clazz.load('java.io.UnsupportedEncodingException'));
this.charsetName = "UTF-8";
this.writer = Clazz.new_((I$[1]||$incl$(1)));
});

Clazz.newMeth(C$, 'c$$java_io_OutputStream', function (out) {
C$.superclazz.c$$O.apply(this, [out]);
C$.$init$.apply(this);
this.stream = out;
try {
p$.setCharset$S.apply(this, [null]);
} catch (e) {
if (Clazz.exceptionOf(e, "java.io.UnsupportedEncodingException")){
} else {
throw e;
}
}
}, 1);

Clazz.newMeth(C$, 'getEncoding', function () {
return this.charsetName;
});

Clazz.newMeth(C$, 'flushBuffer', function () {
this.flush();
});

Clazz.newMeth(C$, 'write$I', function (c) {
var ch = "\u0000";

ch = String.fromCodePoint(c);
this.writer.write$I(ch.$c());
});

Clazz.newMeth(C$, 'write$CA$I$I', function (cbuf, off, len) {
this.writer.write$CA$I$I(cbuf, off, len);
});

Clazz.newMeth(C$, 'write$S$I$I', function (str, off, len) {
this.writer.write$S$I$I(str, off, len);
});

Clazz.newMeth(C$, 'flush', function () {
this.writer.flush();
var s = this.writer.getBuffer().toString();
if (s.length$() > 0) {
var buf = s.getBytes();
this.stream.write$BA$I$I(buf, 0, buf.length);
}this.writer = Clazz.new_((I$[1]||$incl$(1)));
});

Clazz.newMeth(C$, 'close', function () {
this.flush();
this.stream.close();
});

Clazz.newMeth(C$, 'getBufferedWriter', function () {
return (I$[2]||$incl$(2)).getInstanceWithParams$S$ClassA$OA("java.io.BufferedWriter", Clazz.array(java.lang.Class, -1, [Clazz.getClass(C$)]), Clazz.array(java.lang.Object, -1, [this]));
});

Clazz.newMeth(C$);
})();
//Created 2018-02-06 08:58:32
