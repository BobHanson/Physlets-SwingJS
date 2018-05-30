(function(){var P$=java.io,I$=[];
var C$=Clazz.newClass(P$, "BufferedWriter", null, 'java.io.Writer');
C$.defaultCharBufferSize = 0;
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
C$.defaultCharBufferSize = 8192;
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.out = null;
this.cb = null;
this.nChars = 0;
this.nextChar = 0;
this.lineSeparator = null;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$java_io_Writer', function (out) {
C$.c$$java_io_Writer$I.apply(this, [out, C$.defaultCharBufferSize]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_Writer$I', function (out, sz) {
C$.superclazz.c$$O.apply(this, [out]);
C$.$init$.apply(this);
if (sz <= 0) throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Buffer size <= 0"]);
this.out=out;
this.cb=Clazz.array(Character.TYPE, [sz]);
this.nChars=sz;
this.nextChar=0;
this.lineSeparator=System.lineSeparator();
}, 1);

Clazz.newMeth(C$, 'ensureOpen', function () {
if (this.out == null ) throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,["Stream closed"]);
});

Clazz.newMeth(C$, 'flushBuffer', function () {
{
p$.ensureOpen.apply(this, []);
if (this.nextChar == 0) return;
this.out.write$CA$I$I(this.cb, 0, this.nextChar);
this.nextChar=0;
}});

Clazz.newMeth(C$, 'write$I', function (c) {
{
p$.ensureOpen.apply(this, []);
if (this.nextChar >= this.nChars) this.flushBuffer();
this.cb[this.nextChar++]=String.fromCharCode(c);
}});

Clazz.newMeth(C$, 'min$I$I', function (a, b) {
if (a < b) return a;
return b;
});

Clazz.newMeth(C$, 'write$CA$I$I', function (cbuf, off, len) {
{
p$.ensureOpen.apply(this, []);
if ((off < 0) || (off > cbuf.length) || (len < 0) || ((off + len) > cbuf.length) || ((off + len) < 0)  ) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
} else if (len == 0) {
return;
}if (len >= this.nChars) {
this.flushBuffer();
this.out.write$CA$I$I(cbuf, off, len);
return;
}var b = off;
var t = off + len;
while (b < t){
var d = p$.min$I$I.apply(this, [this.nChars - this.nextChar, t - b]);
System.arraycopy(cbuf, b, this.cb, this.nextChar, d);
b+=d;
this.nextChar+=d;
if (this.nextChar >= this.nChars) this.flushBuffer();
}
}});

Clazz.newMeth(C$, 'write$S$I$I', function (s, off, len) {
{
p$.ensureOpen.apply(this, []);
var b = off;
var t = off + len;
while (b < t){
var d = p$.min$I$I.apply(this, [this.nChars - this.nextChar, t - b]);
s.getChars$I$I$CA$I(b, b + d, this.cb, this.nextChar);
b+=d;
this.nextChar+=d;
if (this.nextChar >= this.nChars) this.flushBuffer();
}
}});

Clazz.newMeth(C$, 'newLine', function () {
this.write$S(this.lineSeparator);
});

Clazz.newMeth(C$, 'flush', function () {
{
this.flushBuffer();
this.out.flush();
}});

Clazz.newMeth(C$, 'close', function () {
{
if (this.out == null ) {
return;
}try {
this.flushBuffer();
} finally {
this.out.close();
this.out=null;
this.cb=null;
}
}});

Clazz.newMeth(C$);
})();
//Created 2018-05-24 08:45:32
