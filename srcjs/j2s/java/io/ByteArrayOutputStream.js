(function(){var P$=java.io,I$=[['java.lang.OutOfMemoryError']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "ByteArrayOutputStream", null, 'java.io.OutputStream');
var p$=C$.prototype;

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.buf = null;
this.count = 0;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
}, 1);

Clazz.newMeth(C$, 'c$', function () {
C$.c$$I.apply(this, [32]);
}, 1);

Clazz.newMeth(C$, 'c$$I', function (size) {
Clazz.super_(C$, this,1);
if (size < 0) {
throw Clazz.new_(Clazz.load('java.lang.IllegalArgumentException').c$$S,["Negative initial size: " + size]);
}this.buf = Clazz.array(Byte.TYPE, [size]);
}, 1);

Clazz.newMeth(C$, 'ensureCapacity$I', function (minCapacity) {
if (minCapacity - this.buf.length > 0) p$.grow$I.apply(this, [minCapacity]);
});

Clazz.newMeth(C$, 'grow$I', function (minCapacity) {
var oldCapacity = this.buf.length;
var newCapacity = oldCapacity << 1;
if (newCapacity - minCapacity < 0) newCapacity = minCapacity;
if (newCapacity < 0) {
if (minCapacity < 0) throw Clazz.new_((I$[1]||$incl$(1)));
newCapacity = minCapacity;
}this.buf = C$.arrayCopyByte$BA$I(this.buf, newCapacity);
});

Clazz.newMeth(C$, 'arrayCopyByte$BA$I', function (array, newLength) {
var t = Clazz.array(Byte.TYPE, [newLength]);
System.arraycopy(array, 0, t, 0, array.length < newLength ? array.length : newLength);
return t;
}, 1);

Clazz.newMeth(C$, 'write$I', function (b) {
p$.ensureCapacity$I.apply(this, [this.count + 1]);
this.buf[this.count] = ((b|0)|0);
this.count = this.count+(1);
});

Clazz.newMeth(C$, 'write$BA$I$I', function (b, off, len) {
if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) - b.length > 0)  ) {
throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
}p$.ensureCapacity$I.apply(this, [this.count + len]);
System.arraycopy(b, off, this.buf, this.count, len);
this.count = this.count+(len);
});

Clazz.newMeth(C$, 'writeTo$java_io_OutputStream', function (out) {
out.write$BA$I$I(this.buf, 0, this.count);
});

Clazz.newMeth(C$, 'reset', function () {
this.count = 0;
});

Clazz.newMeth(C$, 'toByteArray', function () {
return (this.count == this.buf.length ? this.buf : C$.arrayCopyByte$BA$I(this.buf, this.count));
});

Clazz.newMeth(C$, 'size', function () {
return this.count;
});

Clazz.newMeth(C$, 'toString', function () {
return  String.instantialize(this.buf, 0, this.count);
});

Clazz.newMeth(C$, 'close', function () {
});
})();
//Created 2018-02-06 08:58:29
