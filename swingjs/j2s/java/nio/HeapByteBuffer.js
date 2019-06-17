(function(){var P$=Clazz.newPackage("java.nio"),I$=[[0,'java.nio.HeapByteBufferR','java.nio.Bits','java.nio.ByteBufferAsCharBufferB','java.nio.ByteBufferAsCharBufferL','java.nio.ByteBufferAsShortBufferB','java.nio.ByteBufferAsShortBufferL','java.nio.ByteBufferAsIntBufferB','java.nio.ByteBufferAsIntBufferL','java.nio.ByteBufferAsLongBufferB','java.nio.ByteBufferAsLongBufferL','java.nio.ByteBufferAsFloatBufferB','java.nio.ByteBufferAsFloatBufferL','java.nio.ByteBufferAsDoubleBufferB','java.nio.ByteBufferAsDoubleBufferL']],$I$=function(i){return I$[i]||(I$[i]=Clazz.load(I$[0][i]))};
var C$=Clazz.newClass(P$, "HeapByteBuffer", null, 'java.nio.ByteBuffer');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$$I$I', function (cap, lim) {
C$.superclazz.c$$I$I$I$I$BA$I.apply(this, [-1, 0, lim, cap, Clazz.array(Byte.TYPE, [cap]), 0]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$BA$I$I', function (buf, off, len) {
C$.superclazz.c$$I$I$I$I$BA$I.apply(this, [-1, off, off + len, buf.length, buf, 0]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'c$$BA$I$I$I$I$I', function (buf, mark, pos, lim, cap, off) {
C$.superclazz.c$$I$I$I$I$BA$I.apply(this, [mark, pos, lim, cap, buf, off]);
C$.$init$.apply(this);
}, 1);

Clazz.newMeth(C$, 'slice$', function () {
return Clazz.new_(C$.c$$BA$I$I$I$I$I,[this.hb, -1, 0, this.remaining$(), this.remaining$(), this.position$() + this.offset]);
});

Clazz.newMeth(C$, 'duplicate$', function () {
return Clazz.new_(C$.c$$BA$I$I$I$I$I,[this.hb, this.markValue$(), this.position$(), this.limit$(), this.capacity$(), this.offset]);
});

Clazz.newMeth(C$, 'asReadOnlyBuffer$', function () {
return Clazz.new_($I$(1).c$$BA$I$I$I$I$I,[this.hb, this.markValue$(), this.position$(), this.limit$(), this.capacity$(), this.offset]);
});

Clazz.newMeth(C$, 'ix$I', function (i) {
return i + this.offset;
});

Clazz.newMeth(C$, 'get$', function () {
return $b$[0] = this.hb[this.ix$I(this.nextGetIndex$())], $b$[0];
});

Clazz.newMeth(C$, 'get$I', function (i) {
return $b$[0] = this.hb[this.ix$I(this.checkIndex$I(i))], $b$[0];
});

Clazz.newMeth(C$, 'get$BA$I$I', function (dst, offset, length) {
P$.Buffer.checkBounds$I$I$I(offset, length, dst.length);
if (length > this.remaining$()) throw Clazz.new_(Clazz.load('java.nio.BufferUnderflowException'));
System.arraycopy$O$I$O$I$I(this.hb, this.ix$I(this.position$()), dst, offset, length);
this.position$I(this.position$() + length);
return this;
});

Clazz.newMeth(C$, 'isDirect$', function () {
return false;
});

Clazz.newMeth(C$, 'isReadOnly$', function () {
return false;
});

Clazz.newMeth(C$, 'put$B', function (x) {
this.hb[this.ix$I(this.nextPutIndex$())]=(x|0);
return this;
});

Clazz.newMeth(C$, 'put$I$B', function (i, x) {
this.hb[this.ix$I(this.checkIndex$I(i))]=(x|0);
return this;
});

Clazz.newMeth(C$, 'put$BA$I$I', function (src, offset, length) {
P$.Buffer.checkBounds$I$I$I(offset, length, src.length);
if (length > this.remaining$()) throw Clazz.new_(Clazz.load('java.nio.BufferOverflowException'));
System.arraycopy$O$I$O$I$I(src, offset, this.hb, this.ix$I(this.position$()), length);
this.position$I(this.position$() + length);
return this;
});

Clazz.newMeth(C$, 'put$java_nio_ByteBuffer', function (src) {
if (Clazz.instanceOf(src, "java.nio.HeapByteBuffer")) {
if (src === this ) throw Clazz.new_(Clazz.load('IllegalArgumentException'));
var sb=src;
var n=sb.remaining$();
if (n > this.remaining$()) throw Clazz.new_(Clazz.load('java.nio.BufferOverflowException'));
System.arraycopy$O$I$O$I$I(sb.hb, sb.ix$I(sb.position$()), this.hb, this.ix$I(this.position$()), n);
sb.position$I(sb.position$() + n);
this.position$I(this.position$() + n);
} else if (src.isDirect$()) {
var n=src.remaining$();
if (n > this.remaining$()) throw Clazz.new_(Clazz.load('java.nio.BufferOverflowException'));
src.get$BA$I$I(this.hb, this.ix$I(this.position$()), n);
this.position$I(this.position$() + n);
} else {
C$.superclazz.prototype.put$java_nio_ByteBuffer.apply(this, [src]);
}return this;
});

Clazz.newMeth(C$, 'compact$', function () {
System.arraycopy$O$I$O$I$I(this.hb, this.ix$I(this.position$()), this.hb, this.ix$I(0), this.remaining$());
this.position$I(this.remaining$());
this.limit$I(this.capacity$());
this.discardMark$();
return this;
});

Clazz.newMeth(C$, '_get$I', function (i) {
return $b$[0] = this.hb[i], $b$[0];
});

Clazz.newMeth(C$, '_put$I$B', function (i, b) {
this.hb[i]=(b|0);
});

Clazz.newMeth(C$, 'getChar$', function () {
return $I$(2).getChar$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.nextGetIndex$I(2)), this.bigEndian);
});

Clazz.newMeth(C$, 'getChar$I', function (i) {
return $I$(2).getChar$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.checkIndex$I$I(i, 2)), this.bigEndian);
});

Clazz.newMeth(C$, 'putChar$C', function (x) {
$I$(2).putChar$java_nio_ByteBuffer$I$C$Z(this, this.ix$I(this.nextPutIndex$I(2)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'putChar$I$C', function (i, x) {
$I$(2).putChar$java_nio_ByteBuffer$I$C$Z(this, this.ix$I(this.checkIndex$I$I(i, 2)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'asCharBuffer$', function () {
var size=this.remaining$() >> 1;
var off=this.offset + this.position$();
return (this.bigEndian ? (Clazz.new_($I$(3).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])) : (Clazz.new_($I$(4).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])));
});

Clazz.newMeth(C$, 'getShort$', function () {
return $I$(2).getShort$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.nextGetIndex$I(2)), this.bigEndian);
});

Clazz.newMeth(C$, 'getShort$I', function (i) {
return $I$(2).getShort$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.checkIndex$I$I(i, 2)), this.bigEndian);
});

Clazz.newMeth(C$, 'putShort$H', function (x) {
$I$(2).putShort$java_nio_ByteBuffer$I$H$Z(this, this.ix$I(this.nextPutIndex$I(2)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'putShort$I$H', function (i, x) {
$I$(2).putShort$java_nio_ByteBuffer$I$H$Z(this, this.ix$I(this.checkIndex$I$I(i, 2)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'asShortBuffer$', function () {
var size=this.remaining$() >> 1;
var off=this.offset + this.position$();
return (this.bigEndian ? (Clazz.new_($I$(5).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])) : (Clazz.new_($I$(6).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])));
});

Clazz.newMeth(C$, 'getInt$', function () {
return $I$(2).getInt$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.nextGetIndex$I(4)), this.bigEndian);
});

Clazz.newMeth(C$, 'getInt$I', function (i) {
return $I$(2).getInt$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.checkIndex$I$I(i, 4)), this.bigEndian);
});

Clazz.newMeth(C$, 'putInt$I', function (x) {
$I$(2).putInt$java_nio_ByteBuffer$I$I$Z(this, this.ix$I(this.nextPutIndex$I(4)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'putInt$I$I', function (i, x) {
$I$(2).putInt$java_nio_ByteBuffer$I$I$Z(this, this.ix$I(this.checkIndex$I$I(i, 4)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'asIntBuffer$', function () {
var size=this.remaining$() >> 2;
var off=this.offset + this.position$();
return (this.bigEndian ? (Clazz.new_($I$(7).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])) : (Clazz.new_($I$(8).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])));
});

Clazz.newMeth(C$, 'getLong$', function () {
return $I$(2).getLong$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.nextGetIndex$I(8)), this.bigEndian);
});

Clazz.newMeth(C$, 'getLong$I', function (i) {
return $I$(2).getLong$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.checkIndex$I$I(i, 8)), this.bigEndian);
});

Clazz.newMeth(C$, 'putLong$J', function (x) {
$I$(2).putLong$java_nio_ByteBuffer$I$J$Z(this, this.ix$I(this.nextPutIndex$I(8)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'putLong$I$J', function (i, x) {
$I$(2).putLong$java_nio_ByteBuffer$I$J$Z(this, this.ix$I(this.checkIndex$I$I(i, 8)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'asLongBuffer$', function () {
var size=this.remaining$() >> 3;
var off=this.offset + this.position$();
return (this.bigEndian ? (Clazz.new_($I$(9).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])) : (Clazz.new_($I$(10).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])));
});

Clazz.newMeth(C$, 'getFloat$', function () {
return $I$(2).getFloat$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.nextGetIndex$I(4)), this.bigEndian);
});

Clazz.newMeth(C$, 'getFloat$I', function (i) {
return $I$(2).getFloat$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.checkIndex$I$I(i, 4)), this.bigEndian);
});

Clazz.newMeth(C$, 'putFloat$F', function (x) {
$I$(2).putFloat$java_nio_ByteBuffer$I$F$Z(this, this.ix$I(this.nextPutIndex$I(4)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'putFloat$I$F', function (i, x) {
$I$(2).putFloat$java_nio_ByteBuffer$I$F$Z(this, this.ix$I(this.checkIndex$I$I(i, 4)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'asFloatBuffer$', function () {
var size=this.remaining$() >> 2;
var off=this.offset + this.position$();
return (this.bigEndian ? (Clazz.new_($I$(11).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])) : (Clazz.new_($I$(12).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])));
});

Clazz.newMeth(C$, 'getDouble$', function () {
return $I$(2).getDouble$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.nextGetIndex$I(8)), this.bigEndian);
});

Clazz.newMeth(C$, 'getDouble$I', function (i) {
return $I$(2).getDouble$java_nio_ByteBuffer$I$Z(this, this.ix$I(this.checkIndex$I$I(i, 8)), this.bigEndian);
});

Clazz.newMeth(C$, 'putDouble$D', function (x) {
$I$(2).putDouble$java_nio_ByteBuffer$I$D$Z(this, this.ix$I(this.nextPutIndex$I(8)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'putDouble$I$D', function (i, x) {
$I$(2).putDouble$java_nio_ByteBuffer$I$D$Z(this, this.ix$I(this.checkIndex$I$I(i, 8)), x, this.bigEndian);
return this;
});

Clazz.newMeth(C$, 'asDoubleBuffer$', function () {
var size=this.remaining$() >> 3;
var off=this.offset + this.position$();
return (this.bigEndian ? (Clazz.new_($I$(13).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])) : (Clazz.new_($I$(14).c$$java_nio_ByteBuffer$I$I$I$I$I,[this, -1, 0, size, size, off])));
});

Clazz.newMeth(C$, 'toArray$', function () {
var pos=this.position$();
var lim=this.limit$();
if (pos > 0) this.flip$();
var b=Clazz.new_(C$.c$$I$I,[this.remaining$(), this.remaining$()]);
b.put$java_nio_ByteBuffer(this);
this.position$I(pos);
this.limit$I(lim);
return b.array$();
});
var $b$ = new Int8Array(1);

Clazz.newMeth(C$);
})();
;Clazz.setTVer('3.2.4.07');//Created 2019-06-16 21:46:58 Java2ScriptVisitor version 3.2.4.07 net.sf.j2s.core.jar version 3.2.4.07
