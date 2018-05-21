(function(){var P$=Clazz.newPackage("swingjs.jzlib"),I$=[['swingjs.jzlib.Deflater']],$incl$=function(i){return I$[i]=Clazz.load(I$[0][i-1])};
var C$=Clazz.newClass(P$, "DeflaterOutputStream", null, 'java.io.FilterOutputStream');

C$.$clinit$ = function() {Clazz.load(C$, 1);
}

Clazz.newMeth(C$, '$init0$', function () {
var c;if((c = C$.superclazz) && (c = c.$init0$))c.apply(this);
this.deflater = null;
this.buffer = null;
this.closed = false;
this.syncFlush = false;
this.buf1 = null;
this.mydeflater = false;
this.close_out = false;
}, 1);

Clazz.newMeth(C$, '$init$', function () {
this.closed = false;
this.syncFlush = false;
this.buf1 = Clazz.array(Byte.TYPE, [1]);
this.mydeflater = false;
this.close_out = true;
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream', function (out) {
C$.c$$java_io_OutputStream$swingjs_jzlib_Deflater$I$Z.apply(this, [out, Clazz.new_((I$[1]||$incl$(1))).init$I$I$Z(-1, 0, false), 512, true]);
this.mydeflater = true;
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream$swingjs_jzlib_Deflater', function (out, def) {
C$.c$$java_io_OutputStream$swingjs_jzlib_Deflater$I$Z.apply(this, [out, def, 0, true]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream$swingjs_jzlib_Deflater$I', function (out, deflater, size) {
C$.c$$java_io_OutputStream$swingjs_jzlib_Deflater$I$Z.apply(this, [out, deflater, size, true]);
}, 1);

Clazz.newMeth(C$, 'c$$java_io_OutputStream$swingjs_jzlib_Deflater$I$Z', function (out, deflater, size, close_out) {
C$.superclazz.c$$java_io_OutputStream.apply(this, [out]);
C$.$init$.apply(this);
this.jzSetDOS$java_io_OutputStream$swingjs_jzlib_Deflater$I$Z(out, deflater, size, close_out);
}, 1);

Clazz.newMeth(C$, 'jzSetDOS$java_io_OutputStream$swingjs_jzlib_Deflater$I$Z', function (out, deflater, size, close_out) {
this.out = out;
if (size == 0) size = 512;
this.deflater = deflater;
this.buffer = Clazz.array(Byte.TYPE, [size]);
this.close_out = close_out;
});

Clazz.newMeth(C$, 'write$I', function (b) {
this.buf1[0] = (((b & 255)|0)|0);
this.write$BA$I$I(this.buf1, 0, 1);
});

Clazz.newMeth(C$, 'write$BA$I$I', function (b, off, len) {
if (this.deflater.finished()) throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,["finished"]);
if (off < 0 || len < 0  || off + len > b.length ) throw Clazz.new_(Clazz.load('java.lang.IndexOutOfBoundsException'));
if (len == 0) return;
var flush = this.syncFlush ? 2 : 0;
this.deflater.setInput$BA$I$I$Z(b, off, len, true);
while (this.deflater.avail_in > 0){
var err = this.deflate$I(flush);
if (err == 1) break;
}
});

Clazz.newMeth(C$, 'finish', function () {
while (!this.deflater.finished()){
this.deflate$I(4);
}
});

Clazz.newMeth(C$, 'close', function () {
if (!this.closed) {
this.finish();
if (this.mydeflater) {
this.deflater.end();
}if (this.close_out) this.out.close();
this.closed = true;
}});

Clazz.newMeth(C$, 'deflate$I', function (flush) {
this.deflater.setOutput$BA$I$I(this.buffer, 0, this.buffer.length);
var err = this.deflater.deflate$I(flush);
switch (err) {
case 0:
case 1:
break;
case -5:
if (this.deflater.avail_in <= 0 && flush != 4 ) {
break;
}default:
throw Clazz.new_(Clazz.load('java.io.IOException').c$$S,["failed to deflate"]);
}
var len = this.deflater.next_out_index;
if (len > 0) {
this.out.write$BA$I$I(this.buffer, 0, len);
}return err;
});

Clazz.newMeth(C$, 'flush', function () {
if (this.syncFlush && !this.deflater.finished() ) {
while (true){
var err = this.deflate$I(2);
if (this.deflater.next_out_index < this.buffer.length) break;
if (err == 1) break;
}
}this.out.flush();
});

Clazz.newMeth(C$, 'getTotalIn', function () {
return this.deflater.getTotalIn();
});

Clazz.newMeth(C$, 'getTotalOut', function () {
return this.deflater.getTotalOut();
});

Clazz.newMeth(C$, 'setSyncFlush$Z', function (syncFlush) {
this.syncFlush = syncFlush;
});

Clazz.newMeth(C$, 'getSyncFlush', function () {
return this.syncFlush;
});

Clazz.newMeth(C$, 'getDeflater', function () {
return this.deflater;
});

Clazz.newMeth(C$);
})();
//Created 2018-05-15 01:03:18
